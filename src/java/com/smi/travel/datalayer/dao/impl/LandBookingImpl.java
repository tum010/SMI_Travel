/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.LandBookingDao;
import com.smi.travel.datalayer.entity.LandBooking;
import com.smi.travel.datalayer.entity.LandItinerary;
import com.smi.travel.datalayer.entity.PackageItinerary;
import com.smi.travel.datalayer.entity.PackageTour;
import com.smi.travel.datalayer.entity.Product;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Surachai
 */
public class LandBookingImpl implements LandBookingDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;
    
    @Override
    public List<LandBooking> getListBookingLandFromRefno(String refno) {
        String query = "from LandBooking land where land.master.referenceNo = :refno";
        Session session = this.sessionFactory.openSession();

        List<LandBooking> LandList = session.createQuery(query).setParameter("refno", refno).list();  
        if (LandList.isEmpty()) {
            return null;
        }
        return LandList;
    }

    @Override
    public LandBooking getBookDetailLandFromID(String LandID) {
        String query = "from LandBooking land where land.id = :LandID";
        Session session = this.sessionFactory.openSession();
        LandBooking result = new LandBooking();
        List<LandBooking> LandList = session.createQuery(query).setParameter("LandID", LandID).list();
        if (LandList.isEmpty()) {
            return null;
        }
        LandList.get(0).setLandItineraries(SortItineraryList(LandList.get(0).getLandItineraries()));
        result = LandList.get(0);
        return result;
    }

    @Override
    public List<Product> getListLandProduct() {
        String query = "from Product p where p.MProductType.id = 2";
        Session session = this.sessionFactory.openSession();
        List<Product> ProductList = session.createQuery(query).list();
        if (ProductList.isEmpty()) {
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return ProductList;
    }

    @Override
    public int insertBookDetailLand(LandBooking land,List<LandItinerary> Itinerary ) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            
            session.save(land);
            for(int i=0;i<Itinerary.size();i++){
                LandItinerary data = Itinerary.get(i);
                data.setLandBooking(land);
                session.save(data);
            }
            
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }
        return result;
    }

    @Override
    public int updateBookDetailLand(LandBooking land,List<LandItinerary> Itinerary,String DelItenarary) {
        int result = 0;
         Session session = this.sessionFactory.openSession();
        try {
           
            transaction = session.beginTransaction();
            System.out.println("land ID :"+land.getMItemstatus().getId());
            System.out.println("land name : "+land.getMItemstatus().getName());
            session.update(land);
            if(!"".equalsIgnoreCase(DelItenarary)){
                DelItenarary = DelItenarary.substring(0, DelItenarary.length()-1);
                String[] ListDelete = DelItenarary.split(",");
                for(int i=0;i<ListDelete.length;i++){
                    LandItinerary del = new LandItinerary();
                    System.out.println("delete id : "+ListDelete[i]);
                    if(!"".equalsIgnoreCase(ListDelete[i])){
                        del.setId(ListDelete[i]);
                        del.setLandBooking(land);
                        del.setDescription("deletedata");
                        System.out.println("delete it :"+del.getId());                        
                    }

                    session.delete(del);
                }
            }
            for(int i=0;i<Itinerary.size();i++){
                LandItinerary data = Itinerary.get(i);
                data.setLandBooking(land);
                if(data.getId() == null){
                    session.save(data);
                }else{
                    session.update(data);
                }
            }
            transaction.commit();
            
            result = 1;
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            result = 0;
        }
        session.close();
        this.sessionFactory.close();
        return result;
    }
    
    public List<LandItinerary> SortItineraryList(List<LandItinerary> data) {
        List<LandItinerary> sortItinerary = new ArrayList<LandItinerary>();
        if(data == null){
            return data;
        }else if(data.size() == 0){
            return data;
        }
        List Dataindex = new ArrayList();
        for (int i = 0; i < data.size(); i++) {
            System.out.println("data id : " + data.get(i).getOrderNo());
            if (data.get(i).getOrderNo() == 0) {
                System.out.println("data id : null ");
                return data;
            }
        }
        for (int i = 0; i < data.size(); i++) {
            Dataindex.add(data.get(i).getOrderNo());
        }

        Collections.sort(Dataindex);
        for (int i = 0; i < Dataindex.size(); i++) {
            for (int j = 0; j < data.size(); j++) {
                if (Dataindex.get(i).equals(data.get(j).getOrderNo())) {
                    System.out.println("order no : " + data.get(j).getOrderNo());
                    if(!IsItineraryDupicate(sortItinerary,data.get(j))){
                        sortItinerary.add(data.get(j));
                    }   
                }
            }
        }

        return sortItinerary;
    }
    
    public boolean IsItineraryDupicate(List<LandItinerary> Listdata,LandItinerary newData){
        boolean isDup = false;
        for(int i=0;i<Listdata.size();i++){
            if(Listdata.get(i).getId().equalsIgnoreCase(newData.getId())){
                isDup = true;
                break;
            }
        }
        return isDup;
    }
    
    /*
    public List<String> checkListItinerary(String Landid,List<LandItinerary> Itinerary) {
        String query = "from LandItinerary l where l.landBooking.id = :landID";
        List<String> IDList = new LinkedList<String>();
  
        for(int i=0;i<Itinerary.size();i++){
            IDList.add(Itinerary.get(i).getId());
        }
        Session session = this.sessionFactory.openSession();
        List<LandItinerary> ItineraryList = session.createQuery(query).setParameter("landID", Landid).list();
        if (ItineraryList.isEmpty()) {
            return null;
        }else{
           for(int j=0;j<ItineraryList.size();j++){
                for(int i=0;i<Itinerary.size();i++){
                    if(){
                        
                    }
                }
            }
        }
        
        session.close();
        this.sessionFactory.close();
        return IDList;
    }*/

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public int cancelBookDetailLand(String landID) {
        int result = 0;
     //   Date thisDate = new Date();
        String hql = "UPDATE LandBooking land set   land.MItemstatus.id = 2"  + 
             "WHERE land.id = :landID";
        try {
            Session session = this.sessionFactory.openSession();
             Query query = session.createQuery(hql);
        //     query.setParameter("thisdate", thisDate);
             query.setParameter("landID", landID);
             result = query.executeUpdate();
             System.out.println("Rows affected: " + result);
         } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }

        return result;
    }

    @Override
    public int enableBookDetailLand(String landID) {
        int result = 0;
     //   Date thisDate = new Date();
        String hql = "UPDATE LandBooking land set   land.MItemstatus.id = 1"  + 
             "WHERE land.id = :landID";
        try {
            Session session = this.sessionFactory.openSession();
             Query query = session.createQuery(hql);
        //     query.setParameter("thisdate", thisDate);
             query.setParameter("landID", landID);
             result = query.executeUpdate();
             System.out.println("Rows affected: " + result);
             this.sessionFactory.close();
             session.close();
         } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }

        return result;
    }

    @Override
    public List<PackageTour> getListLandPackage() {
       String query = "from PackageTour p where p.status != 'inactive'";
        Session session = this.sessionFactory.openSession();
        List<PackageTour> PackageList = session.createQuery(query).list();
        if (PackageList.isEmpty()) {
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return PackageList;
    }
    
    
    
}
