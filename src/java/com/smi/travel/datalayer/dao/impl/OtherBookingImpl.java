/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.OtherBookingDao;
import com.smi.travel.datalayer.entity.OtherBooking;
import com.smi.travel.datalayer.entity.Product;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

/**
 *
 * @author Surachai
 */
public class OtherBookingImpl implements OtherBookingDao{
    
    private SessionFactory sessionFactory;
    private Transaction transaction;
    
    @Override
    public List<OtherBooking> getListBookingOtherFromRefno(String refno) {
        String query = "from OtherBooking other where other.master.referenceNo = :refno";
        Session session = this.sessionFactory.openSession();

        List<OtherBooking> OtherList = session.createQuery(query).setParameter("refno", refno).list();  
        if (OtherList.isEmpty()) {
            return null;
        }
        return OtherList;
    }

    @Override
    public OtherBooking getBookDetailOtherFromID(String OtherBookingID) {
        String query = "from OtherBooking other where other.id = :otherID";
        Session session = this.sessionFactory.openSession();
        OtherBooking result = new OtherBooking();
        List<OtherBooking> OtherList = session.createQuery(query).setParameter("otherID", OtherBookingID).list();
        if (OtherList.isEmpty()) {
            return null;
        }
        result = OtherList.get(0);
        return result;
    }

    @Override
    public int insertBookDetailOther(OtherBooking otherbook) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(otherbook);
            transaction.commit();
            session.close();
            result = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }
        return result;
    }
    
    @Override
    public int updateBookDetailOther(OtherBooking otherbook) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(otherbook);
            transaction.commit();
            session.close();
            result = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }
        return result;
    }

    @Override
    public int cancelBookDetailOther(String otherID) {
        int result = 0;
        Date thisDate = new Date();
        String hql = "UPDATE OtherBooking other set other.cancelDate = :thisdate ,  other.status.id = 2"  + 
             "WHERE other.id = :otherbookID";
        try {
            Session session = this.sessionFactory.openSession();
             Query query = session.createQuery(hql);
             query.setParameter("thisdate", thisDate);
             query.setParameter("otherbookID", otherID);
             result = query.executeUpdate();
             System.out.println("Rows affected: " + result);
         } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }

        return result;
    }
    
    @Override
    public int enableBookDetailOther(String otherID) {
        int result = 0;
        String hql = "UPDATE OtherBooking other set other.cancelDate = :thisdate ,  other.status.id = 1"  + 
             "WHERE other.id = :otherbookID";
        try {
            Session session = this.sessionFactory.openSession();
             Query query = session.createQuery(hql);
             query.setParameter("thisdate", null);
             query.setParameter("otherbookID", otherID);
             result = query.executeUpdate();
             System.out.println("Rows affected: " + result);
         } catch (Exception ex) {
            ex.printStackTrace();
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
