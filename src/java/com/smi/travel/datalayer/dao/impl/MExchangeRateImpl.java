/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.MExchangeRateDao;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Kanokporn
 */
public class MExchangeRateImpl implements MExchangeRateDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;

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
    public List searchExchangeRate(String from, String to, String currency) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
