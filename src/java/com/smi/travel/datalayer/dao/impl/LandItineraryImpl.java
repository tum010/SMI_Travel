/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.LandItineraryDao;
import com.smi.travel.datalayer.entity.LandItinerary;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Surachai
 */
public class LandItineraryImpl implements LandItineraryDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;
    
    @Override
    public List<LandItinerary> getListItinerary(String landID) {
        String query = "from LandItinerary l where l.landBooking.id = :landID";
        Session session = this.sessionFactory.openSession();
        List<LandItinerary> ItineraryList = session.createQuery(query).setParameter("landID", landID).list();
        if (ItineraryList.isEmpty()) {
            return null;
        }
        session.close();
        return ItineraryList;
    }

    @Override
    public int InsertItinerary(LandItinerary land) {
        int result = 0;
        try{
            Session session = this.sessionFactory.openSession();
            session.save(land);
            session.close();  
            result = 1;
        }catch(Exception e){
            result = 0;
        }
        return result;
    }

    @Override
    public int UpdateItinerary(LandItinerary land) {
        int result = 0;
        try{
            Session session = this.sessionFactory.openSession();
            session.update(land);
            session.close();  
            result = 1;
        }catch(Exception e){
            result = 0;
        }
        return result;
    }

    @Override
    public int DeleteItinerary(LandItinerary land) {
        int result = 0;
        try{
            Session session = this.sessionFactory.openSession();
            session.delete(land);
            session.close();  
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
    
    
    
}
