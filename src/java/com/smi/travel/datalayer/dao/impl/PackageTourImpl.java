/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.PackageTourDao;
import com.smi.travel.datalayer.entity.DaytourPrice;
import com.smi.travel.datalayer.entity.PackageCity;
import com.smi.travel.datalayer.entity.PackageItinerary;
import com.smi.travel.datalayer.entity.PackagePrice;
import com.smi.travel.datalayer.entity.PackageTour;
import com.smi.travel.util.UtilityFunction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Surachai
 */
public class PackageTourImpl implements PackageTourDao {

    private SessionFactory sessionFactory;
    private Transaction transaction;
    public static final String DELETE_PACKITINERARY_QUERY = "DELETE FROM PackageItinerary p WHERE p.id = :itineraryid";
    public static final String DELETE_PACKPRICE_QUERY = "DELETE FROM PackagePrice p WHERE p.id = :priceid";
    private static final String SEARCH_PACKPRICE_QUERY = "from PackagePrice p WHERE p.packageTour.id = :packageid";
    private static final String SEARCH_PACKITINERARY_QUERY = "from PackageItinerary p WHERE p.packageTour.id = :packageid";
    private static final String SEARCH_PACKAGE_QUERY = "from PackageTour p WHERE p.id = :packageid";
    private static final String SEARCH_PACKCITY_QUERY = "from PackageCity p WHERE p.packageTour.id = :packageid";
    private static final String DELETE_PACKCITY_QUERY = "DELETE FROM PackageCity c WHERE c.id = :cityid";
    
    
    @Override
    public List<PackageTour> SearchPackage(PackageTour mpackage, int option) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        String query = "from PackageTour pt where ";
        String queryOperation = "";
        String Prefix_Subfix = "";
        int check = 0;
        if (option == 1) {
            queryOperation = " = ";
            Prefix_Subfix = "";
        } else if (option == 2) {
            queryOperation = " Like ";
            Prefix_Subfix = "%";
        }
        
        if ((mpackage.getCode() != null) && (!"".equalsIgnoreCase(mpackage.getCode()))) {
            query += " pt.code " + queryOperation + " '" + Prefix_Subfix + util.StringUtilReplaceChar(mpackage.getCode()) + Prefix_Subfix + "'";
            check = 1;
        }
        if ((mpackage.getName() != null) && (!"".equalsIgnoreCase(mpackage.getName()))) {
            if (check == 1) {
                query += " and ";
            }
            query += " pt.name " + queryOperation + " '" + Prefix_Subfix + util.StringUtilReplaceChar(mpackage.getName()) + Prefix_Subfix + "'";
            check = 1;
        }
        if ((mpackage.getStatus() != null) && (!"".equalsIgnoreCase(mpackage.getStatus()))) {
            if (check == 1) {
                query += " and ";
            }
            query += " pt.status " + queryOperation + " '" + Prefix_Subfix + util.StringUtilReplaceChar(mpackage.getStatus()) + Prefix_Subfix + "'";
            check = 1;
        }

        if (check == 0) {
            query = query.replaceAll("where", " ");
        }
        System.out.println("query : " + query);
        List<PackageTour> list = session.createQuery(query).list();
        if (list.isEmpty()) {
            return null;
        } else {
            return list;
        }

    }

    @Override
    public String insertPackage(PackageTour mpackage) {
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(mpackage);

            List<PackageItinerary> ItineraryList = mpackage.getPackageItineraries();

            for (int i = 0; i < ItineraryList.size(); i++) {
                session.save(ItineraryList.get(i));
            }

            List<PackagePrice> PriceList = mpackage.getPackagePrices();
            if(PriceList != null){
                for (int i = 0; i < PriceList.size(); i++) {
                    session.save(PriceList.get(i));
                }
            }
           
            
            List<PackageCity> CityList = mpackage.getPackageCities();
            for (int i = 0; i < CityList.size(); i++) {
                session.save(CityList.get(i));
            }
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = "success";
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            result = "fail";
        }
        return result;
    }

    @Override
    public String updatePackage(PackageTour mpackage) {
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            if(mpackage.getId() == null){
                 session.save(mpackage);
            }else{
                 session.update(mpackage);
            }
           
            
            System.out.println("PackageTour : "+mpackage.getId());
            List<PackageCity> itylist = mpackage.getPackageCities();
            for(int i=0;i<itylist.size();i++){
                System.out.println("ID :"+itylist.get(i).getId());
                System.out.println("PAckage ID :"+itylist.get(i).getPackageTour().getId());
            }
            
            List<PackageItinerary> ItineraryList = mpackage.getPackageItineraries();
            if (ItineraryList.isEmpty()) {
                System.out.println("empty list");
            }
            for (int i = 0; i < ItineraryList.size(); i++) {
                if (ItineraryList.get(i).getId() == null) {
                    System.out.println("save itinerary");
                    session.save(ItineraryList.get(i));
                } else {
                    System.out.println("update itinerary");
                    session.update(ItineraryList.get(i));
                }
            }

            List<PackagePrice> PriceList = mpackage.getPackagePrices();
            if(PriceList != null){
            for (int i = 0; i < PriceList.size(); i++) {
                if (PriceList.get(i).getId() == null) {
                    session.save(PriceList.get(i));
                } else {
                    session.update(PriceList.get(i));
                }
            }
            }
            List<PackageCity> CityList = mpackage.getPackageCities();
            for (int i = 0; i < CityList.size(); i++) {
                System.out.println(CityList.get(i).getPackageTour().getId());
                if (CityList.get(i).getId() == null) {
                    session.save(CityList.get(i));
                } else {
                    session.update(CityList.get(i));
                }
            }

            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = "success";
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            result = "fail";
        }
        return result;
    }

    @Override
    public String DeletePackage(PackageTour mpackage) {
        String result = "";
        System.out.println("mpackage.getId() :" + mpackage.getId());
        if (IsExistPackageItinerary(mpackage.getId())) {
            result = "delete unsuccessful.Please delete all itinerary in this package";
            return result;
        }
        if (IsExistPackagePrice(mpackage.getId())) {
            result = "delete unsuccessful.Please delete all price in this package";
            return result;
        }
        
        if(IsExistPackageCity(mpackage.getId())){
            result = "delete unsuccessful.Please delete all city in this package";
            return result;
        }
        
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(mpackage);
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = "success";
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            result = "fail";
        }
        return result;
    }

    @Override
    public String DeletePackageItinerary(String ItineraryID) {
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(DELETE_PACKITINERARY_QUERY)
                    .setParameter("itineraryid", ItineraryID);
            System.out.println("row result : " + query.executeUpdate());
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = "success";
        } catch (Exception ex) {
            ex.printStackTrace();
            result = "fail";
        }
        return result;
    }

    private boolean IsExistPackagePrice(String PriceID) {
        boolean result;
        Session session = this.sessionFactory.openSession();
        List<DaytourPrice> list = session.createQuery(SEARCH_PACKPRICE_QUERY).setParameter("packageid", PriceID).list();
        if (list.isEmpty()) {

            result = false;
        } else {
            result = true;
        }
        session.close();
        this.sessionFactory.close();
        return result;

    }

    private boolean IsExistPackageItinerary(String ItineraryID) {
        boolean result;
        Session session = this.sessionFactory.openSession();
        List<DaytourPrice> list = session.createQuery(SEARCH_PACKITINERARY_QUERY).setParameter("packageid", ItineraryID).list();
        if (list.isEmpty()) {
            result = false;
        } else {
            result = true;
        }
        session.close();
        this.sessionFactory.close();
        return result;

    }
    
    private boolean IsExistPackageCity(String PriceID) {
        boolean result;
        Session session = this.sessionFactory.openSession();
        List<DaytourPrice> list = session.createQuery(SEARCH_PACKCITY_QUERY).setParameter("packageid", PriceID).list();
        if (list.isEmpty()) {
            result = false;
        } else {
            result = true;
        }
        session.close();
        this.sessionFactory.close();
        return result;

    }

    @Override
    public String DeletePackagePrice(String PriceID) {
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(DELETE_PACKPRICE_QUERY)
                    .setParameter("priceid", PriceID);
            System.out.println("row result : " + query.executeUpdate());
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = "success";
        } catch (Exception ex) {
            ex.printStackTrace();
            result = "fail";
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
    public PackageTour getPackageFromID(String packageID) {
        Session session = this.sessionFactory.openSession();
        List<PackageTour> list = session.createQuery(SEARCH_PACKAGE_QUERY).setParameter("packageid", packageID).list();
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public PackagePrice getValueFromPackage(String PackageID,String DepartDate) {
       // Date thisDate = new Date();
        System.out.println("PackageID : " + PackageID);

       // UtilityFunction util = new UtilityFunction();
        String query = "from PackagePrice pp where pp.packageTour.id = " + PackageID + " and  ('" + DepartDate + "' >= pp.effectiveFrom) and  ('" + DepartDate + "' <= pp.effectiveTo)";
        System.out.println("query : " + query);
        PackagePrice product = new PackagePrice();
        Session session = this.sessionFactory.openSession();
        List<PackagePrice> PriceList = session.createQuery(query).list();
        if (PriceList.isEmpty()) {
            return null;
        } else {
            product = PriceList.get(0);
        }
        return product;
    }

    @Override
    public List<PackageItinerary> SortItineraryList(List<PackageItinerary> data) {
        List<PackageItinerary> sortItinerary = new ArrayList<PackageItinerary>();
        if(data == null){
            return data;
        }else if(data.size() == 0){
            return data;
        }
        List Dataindex = new ArrayList();
        for (int i = 0; i < data.size(); i++) {
            System.out.println("data id : " + data.get(i).getOrderNo());
            if (data.get(i).getOrderNo() == null) {
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
                    sortItinerary.add(data.get(j));
                }
            }
        }

        return sortItinerary;
    }

    @Override
    public List<PackagePrice> SortPriceList(List<PackagePrice> data) {
        List<PackagePrice> sortPrice = new ArrayList<PackagePrice>();
        if(data == null){
            return data;
        }else if(data.size() == 0){
            return data;
        }
        List Dataindex = new ArrayList();
        for (int i = 0; i < data.size(); i++) {
            System.out.println("data id : " + data.get(i).getId());
            if (data.get(i).getId() == null) {
                System.out.println("data id : null ");
                return data;
            }
        }
        for (int i = 0; i < data.size(); i++) {
            Dataindex.add(data.get(i).getId());
        }

        Collections.sort(Dataindex);
        for (int i = 0; i < Dataindex.size(); i++) {
            for (int j = 0; j < data.size(); j++) {
                if (Dataindex.get(i).equals(data.get(j).getId())) {
                    System.out.println("order no : " + data.get(j).getId());
                    sortPrice.add(data.get(j));
                }
            }
        }

        return sortPrice;
    }

    @Override
    public String DeletePackageCity(String CityID) {
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(DELETE_PACKCITY_QUERY)
                    .setParameter("cityid", CityID);
            System.out.println("row result : " + query.executeUpdate());
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = "success";
        } catch (Exception ex) {
            ex.printStackTrace();
            result = "fail";
        }
        return result;
    }

}
