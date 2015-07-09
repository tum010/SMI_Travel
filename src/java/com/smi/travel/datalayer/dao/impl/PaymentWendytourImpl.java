/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.PaymentWendytourDao;
import com.smi.travel.datalayer.entity.PaymentDetailWendy;
import com.smi.travel.datalayer.entity.PaymentWendy;
import com.smi.travel.datalayer.view.entity.PaymentWendytourView;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Surachai
 */
public class PaymentWendytourImpl implements PaymentWendytourDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;
    
    @Override
    public String InsertPaymentWendy(PaymentWendy payment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String UpdatePaymentWendy(PaymentWendy payment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String DeletePaymentWendy(PaymentWendy payment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String DeletePaymentWendyDetail(String DetailID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaymentWendy SearchPaymentWendyFromPayno(String payno) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaymentWendytourView> SearchPaymentFromFilter(String DateFrom ,String Dateto,String PVType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
                    sum.add(detail.get(j).getAmount());
                }                
             }

             paymentview.setId(payment.getId());
             paymentview.setPayDate((payment.getPayDate()));
             paymentview.setPayType(payment.getMPaymentDoctype().getName());
             paymentview.setInvoiceSup(payment.getInvoiceSup());
             paymentview.setAccNo(payment.getAccount());
             paymentview.setTotal(sum);
             paymentview.setCurrency(payment.getCurrency());
             paymentview.setStatus(payment.getMItemstatus().getName());
             paymentviewList.add(paymentview);
         }
         
         return paymentviewList;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    
}
