/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.view.dao.ReceiveListDao;
import java.util.List;
import org.hibernate.SessionFactory;

/**
 *
 * @author Kanokporn
 */
public class ReceiveListImpl implements ReceiveListDao{
private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public List getReceiveList(String ticketfrom, String tickettype, String startdate, String enddate, String billto, String passenger, String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
