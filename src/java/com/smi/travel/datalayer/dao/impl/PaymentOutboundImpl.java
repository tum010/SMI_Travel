/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.PaymentOutboundDao;
import com.smi.travel.datalayer.entity.MRunningCode;
import com.smi.travel.datalayer.entity.PaymentOutbound;
import com.smi.travel.datalayer.entity.PaymentOutboundDetail;
import com.smi.travel.util.UtilityFunction;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
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

    @Override
    public String updatePaymentOutbound(PaymentOutbound paymentOutbound) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String insertPaymentOutbound(PaymentOutbound paymentOutbound) {
        String result = "";
        Session session = this.sessionFactory.openSession();
        try { 
            setTransaction(session.beginTransaction());
            String payNo = gennaratePaymentOutboundNo("PO");
            paymentOutbound.setPayNo(payNo);            
            session.save(paymentOutbound);
            List<PaymentOutboundDetail> paymentOutboundDetail = paymentOutbound.getPaymentOutboundDetails();
            if(paymentOutboundDetail != null){
                for (int i = 0; i < paymentOutboundDetail.size(); i++) {
                    session.save(paymentOutboundDetail.get(i));
                }
            }
            getTransaction().commit();
            session.close();
            this.getSessionFactory().close();
            result = "success";
        } catch (Exception ex) {
            getTransaction().rollback();
            ex.printStackTrace();
            session.close();
            this.getSessionFactory().close();
            result = "fail";
        }
        return result;
    }
    
    private String gennaratePaymentOutboundNo(String type){
        String hql = "from MRunningCode run where run.type = :type";
        Session session = this.sessionFactory.openSession();
        List<MRunningCode> list = session.createQuery(hql).setParameter("type", type).list();
        if (list.isEmpty()) {
            return null;
        }
        
        String code = String.valueOf(list.get(0).getRunning()+1);
        for(int i=code.length();i<5;i++){
            code = "0"+code;
        }
        code = "O"+code;
        
        Query query = session.createQuery("update MRunningCode run set run.running = :running" +
    				" where run.type = :type");
        query.setParameter("running", list.get(0).getRunning()+1);
        query.setParameter("type", "PW");
        int result = query.executeUpdate();
        
        session.close();
        this.sessionFactory.close();
        return code;
    }
}
