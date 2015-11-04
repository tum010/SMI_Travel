/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.RefundDao;
import com.smi.travel.datalayer.entity.AirticketRefund;
import com.smi.travel.datalayer.entity.RefundAirticketDetail;
import com.smi.travel.datalayer.view.entity.RefundTicket;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Kanokporn
 */
public class RefundImpl implements RefundDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;
    private static final String SELECT_REFUND_AIRTICKET = "FROM AirticketRefund ar where ar.airticketBooking.id = :airbookingid";
    
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
    public List searchRefundTicket(String airbookingid) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();  
        List<AirticketRefund> airbookingidList = session.createQuery(SELECT_REFUND_AIRTICKET)
                .setParameter("airbookingid", airbookingid)
                .list();
        if (airbookingidList.isEmpty()) {
            return null;
        }
        List<RefundTicket> listRefundTicket = new LinkedList<RefundTicket>();
        for(int i = 0; i< airbookingidList.size() ; i++){
            RefundTicket refundTicket = new RefundTicket();
            refundTicket.setRefundno(airbookingidList.get(i).getRefundAirticket().getRefundNo());
            // Refund By Name
            if(airbookingidList.get(i).getRefundAirticket().getRefundBy() != null && !"".equals(airbookingidList.get(i).getRefundAirticket().getRefundNo())){
                List<Object[]> QueryRefundbyName = session.createSQLQuery("SELECT * FROM `customer_agent_info` cgi where cgi.bill_to = '" + airbookingidList.get(i).getRefundAirticket().getRefundBy()+"'")
                    .addScalar("bill_name", Hibernate.STRING)
                    .list();
                String refundbyname = "";
                if(QueryRefundbyName  != null){
//                    for (Object[] B : QueryRefundbyName ) {
                        refundbyname = util.ConvertString(QueryRefundbyName.get(0));
                        System.out.println("Refund Name : " + refundbyname);
                        refundTicket.setRefundby(refundbyname);
//                    }
                }else{
                    refundTicket.setRefundby("");
                }
            }
           
            refundTicket.setRefunddate(airbookingidList.get(i).getRefundAirticket().getRefundDate());
            refundTicket.setReceiveby(airbookingidList.get(i).getRefundAirticket().getReceiveBy());
            // Sum Charge
            List<RefundAirticketDetail> rRefundAirticketDetail = new LinkedList<RefundAirticketDetail>();
            rRefundAirticketDetail =  airbookingidList.get(i).getRefundAirticket().getRefundAirticketDetails();
            BigDecimal sumCharge = new BigDecimal(0);
            for (int j = 0; j < rRefundAirticketDetail.size(); j++) {
                if(rRefundAirticketDetail.get(j).getPayCustomer() != null ){
                    sumCharge = sumCharge.add(rRefundAirticketDetail.get(j).getPayCustomer());
                }else{
                    sumCharge = sumCharge.add(new BigDecimal(0.0));
                }
            }
            refundTicket.setChange(sumCharge);
            listRefundTicket.add(refundTicket);
        }
           
        return listRefundTicket;
    }
    
    
}
