/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.view.dao.TicketAircommissionViewDao;
import com.smi.travel.datalayer.view.entity.PaymentTourCommissionView;
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
        List<Object[]> QueryList =  session.createSQLQuery("SELECT * FROM `ticket_air_comission_view` where `ticket_air_comission_view`.pay_no = '"+paymentNo+"'")
                .addScalar("payment_id",Hibernate.STRING)
                .addScalar("pay_no",Hibernate.STRING)
                .addScalar("detail",Hibernate.STRING)
                .addScalar("airline",Hibernate.STRING)
                .addScalar("commission",Hibernate.BIG_DECIMAL)
                .addScalar("is_use",Hibernate.STRING)
                .addScalar("agentcode",Hibernate.STRING)
                .addScalar("agentname",Hibernate.STRING)
                .addScalar("agentaddress",Hibernate.STRING)
                .addScalar("termid",Hibernate.STRING)
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
            TicketAirView.setAgentcode(String.valueOf(T[6]));
            TicketAirView.setAgentname(String.valueOf(T[7]));
            TicketAirView.setAgentaddress(String.valueOf(T[8]));
            TicketAirView.setTermid(String.valueOf(T[9]));
            ticketAircommissionViewList.add(TicketAirView);  
        }
       
        if (ticketAircommissionViewList.isEmpty()) {
            return null;
        }
        
        session.close();
        return ticketAircommissionViewList;
    }
    
    @Override
    public List<PaymentTourCommissionView> getListPaymentTourCommissionView(String paymentNo) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List<Object[]> QueryList =  session.createSQLQuery("SELECT * FROM `payment_tour_commission_view` pay where pay.payno = '"+paymentNo+"'")
                .addScalar("paymentid",Hibernate.STRING)
                .addScalar("payno",Hibernate.STRING)
                .addScalar("detail",Hibernate.STRING)
                .addScalar("commission",Hibernate.BIG_DECIMAL)
                .addScalar("is_use",Hibernate.STRING)
                .addScalar("supcode",Hibernate.STRING)
                .addScalar("supname",Hibernate.STRING)
                .addScalar("supaddress",Hibernate.STRING)
                .list();
              
        List<PaymentTourCommissionView> paymentTourCommissionViewList =  new LinkedList<PaymentTourCommissionView>();
        for(Object[] T : QueryList){
            PaymentTourCommissionView payment = new PaymentTourCommissionView();
            payment.setPaymentId(util.ConvertString(T[0]));
            payment.setPayNo(util.ConvertString(T[1]));
            payment.setDetail(util.ConvertString(T[2]));
            payment.setCommision(new BigDecimal(String.valueOf(T[3])));
            payment.setIsUse(util.ConvertString(T[4]));
            payment.setSupcode(util.ConvertString(T[5]));
            payment.setSupname(util.ConvertString(T[6]));
            payment.setSupaddress(util.ConvertString(T[7]));
            paymentTourCommissionViewList.add(payment); 
        }
       
        if (paymentTourCommissionViewList.isEmpty()) {
            return null;
        }
        
        session.close();
        return paymentTourCommissionViewList;
    }
    
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public PaymentTourCommissionView getPaymentTourCommissionView(String paymentId) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List<Object[]> QueryList =  session.createSQLQuery("SELECT * FROM `payment_tour_commission_view` pay where pay.paymentid = '"+paymentId+"'")
                .addScalar("paymentid",Hibernate.STRING)
                .addScalar("payno",Hibernate.STRING)
                .addScalar("detail",Hibernate.STRING)
                .addScalar("commission",Hibernate.BIG_DECIMAL)
                .addScalar("is_use",Hibernate.STRING)
                .addScalar("supcode",Hibernate.STRING)
                .addScalar("supname",Hibernate.STRING)
                .addScalar("supaddress",Hibernate.STRING)
                .list();
              
        List<PaymentTourCommissionView> paymentTourCommissionViewList =  new LinkedList<PaymentTourCommissionView>();
        for(Object[] T : QueryList){
            PaymentTourCommissionView payment = new PaymentTourCommissionView();
            payment.setPaymentId(util.ConvertString(T[0]));
            payment.setPayNo(util.ConvertString(T[1]));
            payment.setDetail(util.ConvertString(T[2]));
            payment.setCommision(new BigDecimal(String.valueOf(T[3])));
            payment.setIsUse(util.ConvertString(T[4]));
            payment.setSupcode(util.ConvertString(T[5]));
            payment.setSupname(util.ConvertString(T[6]));
            payment.setSupaddress(util.ConvertString(T[7]));
            paymentTourCommissionViewList.add(payment); 
        }
       
        if (paymentTourCommissionViewList.isEmpty()) {
            return null;
        }
        
        session.close();
        return paymentTourCommissionViewList.get(0);   
    }
}
