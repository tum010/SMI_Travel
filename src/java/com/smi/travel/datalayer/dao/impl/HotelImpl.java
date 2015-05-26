/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.common.HibernateSession;
import com.smi.travel.datalayer.dao.HotelDao;
import com.smi.travel.datalayer.entity.Hotel;
import com.smi.travel.util.UtilityFunction;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Surachai
 */
public class HotelImpl   implements HotelDao {
    
    private SessionFactory sessionFactory;
    private Transaction transaction;

    
    @Override
    public List<Hotel> getListHotel(Hotel hotel, int option) {
        String query = "from Hotel h where ";
        String queryOperation = "";
        String Prefix_Subfix = "";
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        int check = 0;
        if (option == 1) {
            queryOperation = " = ";
            Prefix_Subfix = "";
        } else if (option == 2) {
            queryOperation = " Like ";
            Prefix_Subfix = "%";
        }
        if ((hotel.getCode() != null) && (!"".equalsIgnoreCase(hotel.getCode()))) {
            query += " h.code " + queryOperation + " '"  + hotel.getCode() + Prefix_Subfix + "'";
            check = 1;
        }
        if ((hotel.getName() != null) && (!"".equalsIgnoreCase(hotel.getName()))) {
            if (check == 1) {
                query += " and ";
            }
            query += " h.name " + queryOperation + " '"  + hotel.getName() + Prefix_Subfix + "'";
            check = 1;
        }
       
        if (check == 0) {
            query = query.replaceAll("where", " ");
        }
        System.out.println("query : " + query);
        List<Hotel> HotelList = session.createQuery(query).list();
        for(int i=0;i<HotelList.size();i++){
            HotelList.get(i).setName(util.ReplaceEnterKey(HotelList.get(i).getName()));
        }
        if (HotelList.isEmpty()) {
            return null;
        }
        return HotelList;
    }
    
    public Hotel getHotelFromID(String HotelID){
        String query = "from Hotel h where h.id = :HotelID";
        Hotel result = new Hotel();
        Session session = this.sessionFactory.openSession();
        List<Hotel> HotelList = session.createQuery(query).setParameter("HotelID", HotelID).list();
        if (HotelList.isEmpty()) {
            return null;
        }else{
            result = HotelList.get(0);
        }
        return result;
    }

    @Override
    public int insertHotel(Hotel hotel) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
           
            session.save(hotel);
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
    public int updateHotel(Hotel hotel) {
       int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(hotel);
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
    public int DeleteHotel(Hotel hotel) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(hotel);
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
            result = 0;
        }
        return result;
    }


    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    

}
