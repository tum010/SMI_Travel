/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.PaymentOutboundDao;
import com.smi.travel.util.UtilityFunction;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author chonnasith
 */
public class PaymentOutboundImpl implements PaymentOutboundDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;
    private UtilityFunction utilityFunction;

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
