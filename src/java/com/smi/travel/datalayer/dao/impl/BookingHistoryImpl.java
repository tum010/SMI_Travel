/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.BookingHistoryDao;
import com.smi.travel.datalayer.entity.HistoryBooking;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Jittima
 */
public class BookingHistoryImpl implements BookingHistoryDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;
    
    private static final String HISTORYFROMREFNO = "from HistoryBooking h where h.master.referenceNo = :refno";
    
    @Override
    public List<HistoryBooking> getHistoryFromRefno(String refno) {
        Session session = this.getSessionFactory().openSession();
        List<HistoryBooking> historyBookings = session.createQuery(HISTORYFROMREFNO).setParameter("refno", refno).list();
        if(historyBookings.isEmpty()){
            return null;
        }
       return historyBookings;    
    }

    @Override
    public int insertHistoryBooking(HistoryBooking historyBooking) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(historyBooking);
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }
        return result;    }

    @Override
    public int UpdateHistoryBooking(HistoryBooking historyBooking) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(historyBooking);
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

}
