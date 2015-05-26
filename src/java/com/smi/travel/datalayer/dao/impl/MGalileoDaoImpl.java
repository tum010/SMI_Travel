/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.MGalileoDao;
import com.smi.travel.datalayer.entity.MGalileo;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Surachai
 */
public class MGalileoDaoImpl implements MGalileoDao {

    private SessionFactory sessionFactory;
    private Transaction transaction;
    private static final String GETGALILEOLIST = "from MGalileo";

    @Override
    public List<MGalileo> getGalileoList() {
        Session session = this.sessionFactory.openSession();
        List<MGalileo> GalileoList = session.createQuery(GETGALILEOLIST).list();
        return GalileoList;
    }

    @Override
    public int EditGalileo(MGalileo galileo) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(galileo);
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
