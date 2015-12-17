/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.PlaceDao;
import com.smi.travel.datalayer.entity.MPlacestatus;
import com.smi.travel.datalayer.entity.Place;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
/**
 *
 * @author Surachai
 */
public class PlaceImpl   implements PlaceDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;
    private static final String GET_PLACE_FROM_STATUS_QUERY = "from Place p where  p.MPlacestatus.name = :status";
    @Override
    public List<Place> getListPlace(Place place, int option) {
        if(place.getPlace() != null)
        System.out.println("getPlace :"+place.getPlace());
        if(place.getMPlacestatus() == null)
        System.out.println("getMPlacestatus :"+null);
        String query = "from Place p where ";
        String queryOperation = "";
        String Prefix_Subfix ="";
        int check =0;
        if(option == 1){
           queryOperation = " = ";
           Prefix_Subfix = "";
        }else if(option == 2){
           queryOperation = " Like ";
           Prefix_Subfix = "%";
        }
        
        if ((place.getPlace() != null) && (!"".equalsIgnoreCase(place.getPlace()))) {
            query += " p.place "+queryOperation+" '"+place.getPlace()+Prefix_Subfix+"'";
            check = 1;
        }
        if ((place.getMPlacestatus() != null) && !("null".equalsIgnoreCase(place.getMPlacestatus().getId())) ){
            if (check == 1) {query += " and ";}
            query += " p.MPlacestatus "+queryOperation+" '"+place.getMPlacestatus().getId()+Prefix_Subfix+"'";
            check = 1;
        }
        if (check == 0) {
            query = query.replaceAll("where", " ");
        }
        System.out.println("query : " + query);
       //List<Place> list = getHibernateTemplate().find(query);
        Session session= this.sessionFactory.openSession();
        List<Place> placeList = session.createQuery(query).list();
        if(placeList != null){
            System.out.println("size : "+placeList.size());
        }
      //session.close();
        return placeList;
    }
    
    @Override
    public List<Place> getListPlaceFromStatus(String status) {
        Session session= this.sessionFactory.openSession();
        List<Place> placeList = session.createQuery(GET_PLACE_FROM_STATUS_QUERY)
                .setParameter("status", status)
                .list();
        if(placeList.isEmpty()){
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return placeList;
    }

    @Override
    public int insertPlace(Place place) {
        int result = 0;
        try{
            Session session = this.sessionFactory.openSession();
            session.save(place);
            session.close();
           // getHibernateTemplate().save(place);
            result = 1;
        }catch(Exception e){
            result = 0;
        }
        return result;
    }

    @Override
    public int updatePlace(Place place) {
        int result = 0;
        
        try{
            
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(place);
            transaction.commit();
            session.close();
            //getHibernateTemplate().update(place);
           
            result = 1;
        }catch(Exception e){
            result = 0;
        }
        return result;
    }

    @Override
    public int DeletePlace(Place place) {
        int result = 0;
        try{
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(place);
            transaction.commit();
            session.close();
            //getHibernateTemplate().delete(place);
            result = 1;
        }catch(Exception e){
            result = 0;
        }
        return result;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Place getPlaceFromId(String placeId){
        String query = "from Place p where  p.id = :placeId";
        Session session = this.sessionFactory.openSession();
        List<Place> places = session.createQuery(query).setParameter("placeId", placeId).list();
        if (places.isEmpty()) {
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return places.get(0);
    }
    
}
