/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.PaymentWendytourDao;
import com.smi.travel.datalayer.entity.MRunningCode;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.PaymentDetailWendy;
import com.smi.travel.datalayer.entity.PaymentWendy;
import com.smi.travel.datalayer.view.entity.PaymentWendytourView;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Surachai
 */
public class PaymentWendytourImpl implements PaymentWendytourDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;
    private static final int MAX_ROW = 200;
    
    @Override
    public String InsertPaymentWendy(PaymentWendy payment) {
        
        String result = "fail";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();    
            String runningCode = gennaratePaymentRunning("PW");
            payment.setPayNo(runningCode);
            session.save(payment);    
            List<PaymentDetailWendy> paymentDetailWendy = payment.getPaymentDetailWendies();
            
            if(paymentDetailWendy != null){
                for (int i = 0; i < paymentDetailWendy.size(); i++) {
                    session.save(paymentDetailWendy.get(i));
                }
            }
            
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = runningCode;
            
        } catch (Exception ex) {
            ex.printStackTrace();
            result = "fail";
        }
         
        return result;    
    }

    @Override
    public String UpdatePaymentWendy(PaymentWendy payment) {
        String result = "fail";
        Session session = this.sessionFactory.openSession();
        try {
            
            transaction = session.beginTransaction();
            session.update(payment);
            
            List<PaymentDetailWendy> paymentDetailWendy = payment.getPaymentDetailWendies();
            
            if(paymentDetailWendy != null){
                for (int i = 0; i < paymentDetailWendy.size(); i++) {
                    
                    if(paymentDetailWendy.get(i).getId() == null){
                        session.save(paymentDetailWendy.get(i));
                    } else {
                        session.update(paymentDetailWendy.get(i));
                    }                   
                }
            }
            
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = "success";
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            this.sessionFactory.close();
            System.out.println("Fail !!!!!");
            ex.printStackTrace();
            result = "fail";
        }
        return result;    
    }

    @Override
    public String DeletePaymentWendy(PaymentWendy payment) {

        String result = "fail";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(payment);
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = "success";
        } catch (Exception ex) {
            ex.printStackTrace();
            result = "fail";
        }
        return result;
    }

    @Override
    public String DeletePaymentWendyDetail(PaymentDetailWendy DetailID) {
        String result = "fail";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(DetailID);
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = "success";
        } catch (Exception ex) {
            ex.printStackTrace();
            result = "fail";
        }
        return result;
    }

    @Override
    public PaymentWendy SearchPaymentWendyFromPayno(String payno) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaymentWendytourView> SearchPaymentFromFilter(String dateFrom ,String dateTo,String payType) {
        StringBuffer query = new StringBuffer("from PaymentWendy payment ");
        boolean haveCondition = false;
        if ((dateFrom != null) && (!"".equalsIgnoreCase(dateFrom))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" payment.payDate >= '" + dateFrom + "'");
            haveCondition = true;
        }
        if ((dateTo != null) && (!"".equalsIgnoreCase(dateTo))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" payment.payDate <= '" + dateTo + "'");
            haveCondition = true;
        }
        if ((payType != null) && (!"".equalsIgnoreCase(payType))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" payment.MPaymentDoctype = " + payType);
            haveCondition = true;
        }
        
        Session session = this.sessionFactory.openSession();
        Query HqlQuery = session.createQuery(query.toString());
        System.out.println(HqlQuery.toString());
        HqlQuery.setMaxResults(MAX_ROW);
        List<PaymentWendy> paymentList = HqlQuery.list();
        if (paymentList.isEmpty()) {
            return null;
        }
        
        List<PaymentWendytourView> paymentviewList = mappingPaymentWendytourView(paymentList);
        this.sessionFactory.close();
        session.close();
        return paymentviewList;
    }
    
    
    private List<PaymentWendytourView> mappingPaymentWendytourView(List<PaymentWendy> paymentList){
         List<PaymentWendytourView> paymentviewList = new LinkedList<PaymentWendytourView>();
         if(paymentList == null ){
             return null;
         }
         for(int i=0;i<paymentList.size();i++){
             PaymentWendy payment = paymentList.get(i);
             PaymentWendytourView paymentview = new PaymentWendytourView();

             BigDecimal sum = new BigDecimal(0);
             if(payment.getPaymentDetailWendies() != null){
                List<PaymentDetailWendy> detail = payment.getPaymentDetailWendies();         
                for(int j=0;j<detail.size();j++){
                    if(detail.get(j).getAmount() != null){
                        sum.add(detail.get(j).getAmount());
                    }
                    
                }                
             }

             paymentview.setId(payment.getId());
             paymentview.setPayNo(payment.getPayNo());
             paymentview.setPayDate((payment.getPayDate()));
             if(payment.getMPaymentDoctype() != null){
                 paymentview.setPayType(payment.getMPaymentDoctype().getName());
             }
             
             paymentview.setInvoiceSup(payment.getInvoiceSup());
             paymentview.setAccNo(payment.getAccount());
             paymentview.setTotal(sum);
           //  paymentview.setCurrency(payment.getCurrency());
             if(payment.getMItemstatus() != null){
                 paymentview.setStatus(payment.getMItemstatus().getName());
             }
             
             paymentviewList.add(paymentview);
         }
         
         return paymentviewList;
    }
    
    public String gennaratePaymentRunning(String type){
        String query = "from MRunningCode run where run.type =  :type";
        Session session = this.sessionFactory.openSession();
        List<MRunningCode> list = session.createQuery(query).setParameter("type", type).list();
        if (list.isEmpty()) {
            return null;
        }
        String code = String.valueOf(list.get(0).getRunning()+1);
        for(int i=code.length();i<6;i++){
            code = "0"+code;
        }
        return code;
    }

    
    public Master getMasterFromRefno(String refno){
        String query = "from Master M where M.referenceNo =  :refno";
        Session session = this.sessionFactory.openSession();
        List<Master> list = session.createQuery(query).setParameter("refno", refno).list();
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);   
    }


    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public PaymentWendy getPaymentWendyFromID(String payNo) {
        String query = "from PaymentWendy p where p.payNo = :payNo";
        Session session = this.sessionFactory.openSession();
        PaymentWendy result = new PaymentWendy();
        List<PaymentWendy> List = session.createQuery(query).setParameter("payNo", payNo).list();
        if (List.isEmpty()) {
            return null;
        }

        result = List.get(0);
        return result;
    }

    @Override
    public List<PaymentDetailWendy> getPaymentDetailWendyList(String paymentId) {
        String query = "from PaymentDetailWendy p where p.paymentWendy = :paymentId";
        Session session = this.sessionFactory.openSession();
        List<PaymentDetailWendy> List = session.createQuery(query).setParameter("paymentId", paymentId).list();
        if (List.isEmpty()) {
            return null;
        }

        return List;
    }


    
}
