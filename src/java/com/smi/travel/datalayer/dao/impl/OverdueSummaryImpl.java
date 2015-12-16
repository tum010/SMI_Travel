/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.OverdueSummaryDao;
import com.smi.travel.datalayer.entity.OverdueSummartExcel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Kanokporn
 */
public class OverdueSummaryImpl implements OverdueSummaryDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;
    
    @Override
    public List listOverdueSummary(String clientcode, String clientname, String staffcode, String staffname, String vattype, String from, String to, String depart, String group, String view, String printby) {
        List<OverdueSummartExcel> data = new ArrayList<OverdueSummartExcel>();
        OverdueSummartExcel  over = new OverdueSummartExcel();
        data.add(over);
        return data;
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
