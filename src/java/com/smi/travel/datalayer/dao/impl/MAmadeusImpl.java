/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.MAmadeusDao;
import com.smi.travel.datalayer.entity.MAmadeus;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Surachai
 */
public class MAmadeusImpl implements MAmadeusDao{
    
    private SessionFactory sessionFactory;
    private Transaction transaction;
    private static final String GETAMADEUSLIST = "from MAmadeus";
    
    @Override
    public List<MAmadeus> getAmadeusList() {
        Session session = this.sessionFactory.openSession();
        List<MAmadeus> AmadeusList = session.createQuery(GETAMADEUSLIST).list();
        return AmadeusList;
    }

    @Override
    public int EditAmadeus(MAmadeus amadeus) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(amadeus);
            transaction.commit();
            session.close();
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
    
    
    
}
