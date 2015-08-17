/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.view.dao.TicketAircommissionViewDao;
import com.smi.travel.datalayer.view.entity.TicketAircommissionView;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Jittima
 */
public class TicketAircommissionViewImpl implements TicketAircommissionViewDao{
    private SessionFactory sessionFactory;
    
    public List<TicketAircommissionView> getListTicketAircommissionView(String paymentNo) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List<Object[]> QueryList =  session.createSQLQuery("SELECT * FROM `ticket_air_comission_view` where `ticket_air_comission_view`.pay_no =  "+paymentNo)
                .addScalar("payment_id",Hibernate.STRING)
                .addScalar("pay_no",Hibernate.STRING)
                .addScalar("detail",Hibernate.STRING)
                .addScalar("airline",Hibernate.STRING)
                .addScalar("commission",Hibernate.BIG_DECIMAL)
                .addScalar("is_use",Hibernate.STRING)
                .list();
              
        List<TicketAircommissionView> ticketAircommissionViewList =  new LinkedList<TicketAircommissionView>();
        for(Object[] T : QueryList){
            TicketAircommissionView TicketAirView = new TicketAircommissionView();
            TicketAirView.setPaymentId((T[0].toString()));
            TicketAirView.setPayNo((T[1].toString()));
            TicketAirView.setDetail((T[2].toString()));
            TicketAirView.setAirline((T[3].toString()));
            TicketAirView.setCommision(new BigDecimal(String.valueOf(T[4])));
            TicketAirView.setIsUse((T[5].toString()));
            ticketAircommissionViewList.add(TicketAirView);  
        }
       
        if (ticketAircommissionViewList.isEmpty()) {
            return null;
        }
        
        session.close();
        return ticketAircommissionViewList;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
}
