/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.RefundDao;
import com.smi.travel.datalayer.entity.AirticketPassenger;
import com.smi.travel.datalayer.entity.AirticketRefund;
import com.smi.travel.datalayer.entity.RefundAirticketDetail;
import com.smi.travel.datalayer.entity.RefundTicketDetail;
import com.smi.travel.datalayer.view.entity.RefundTicket;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.text.DecimalFormat;
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
    private static final String SELECT_TICKETNO = "From AirticketPassenger psg where psg.airticketAirline.airticketPnr.airticketBooking.master.referenceNo = :refno";
    private static final String SELECT_SECTOR = "From AirticketPassenger psg where psg.id = ticid";
    
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
        List<RefundTicketDetail> listRefundTicketDetail = new LinkedList<RefundTicketDetail>();
        for(int i = 0; i< airbookingidList.size() ; i++){
            RefundTicket refundTicket = new RefundTicket();
            refundTicket.setAirticketrefundid(airbookingidList.get(i).getId());
            refundTicket.setRefundno(airbookingidList.get(i).getRefundAirticket().getRefundNo());
            // Refund By Name
            if(airbookingidList.get(i).getRefundAirticket().getRefundBy() != null && !"".equals(airbookingidList.get(i).getRefundAirticket().getRefundNo())){
                List<Object[]> QueryRefundbyName = session.createSQLQuery("SELECT * FROM `customer_agent_info` cgi where cgi.bill_to = '" + airbookingidList.get(i).getRefundAirticket().getRefundBy()+"'")
                    .addScalar("bill_name", Hibernate.STRING)
                    .list();
                String refundbyname = "";
                if(QueryRefundbyName  != null){
                        refundbyname = util.ConvertString(QueryRefundbyName.get(0));
                        System.out.println("Refund Name : " + refundbyname);
                        refundTicket.setRefundby(refundbyname);
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
            DecimalFormat df = new DecimalFormat("#,###.00");
            for (int j = 0; j < rRefundAirticketDetail.size(); j++) {
                if(rRefundAirticketDetail.get(j).getPayCustomer() != null ){
                    sumCharge = sumCharge.add(rRefundAirticketDetail.get(j).getPayCustomer());
                }else{
                    sumCharge = sumCharge.add(new BigDecimal(0.0));
                }
            }
            refundTicket.setChange(df.format(sumCharge));
            refundTicket.setDetail(airbookingidList.get(i).getRefundAirticket().getRemark());
            
            // Refund Ticket Detail 
            listRefundTicketDetail = getRefundTicketDetail(airbookingidList);
            
            listRefundTicket.add(refundTicket);
        }
           
        return listRefundTicket;
    }
    
    private List getRefundTicketDetail(List airbookingidList){
        List<RefundTicketDetail> listRefundTicketDetail = new LinkedList<RefundTicketDetail>();
        for(int i = 0; i< airbookingidList.size() ; i++){
            
        }
        
        return listRefundTicketDetail;
    }

    @Override
    public List selectTicketNo(String refno) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();  
        List<AirticketPassenger> ticketnoList = session.createQuery(SELECT_TICKETNO)
                .setParameter("refno", refno)
                .list();
        if (ticketnoList.isEmpty()) {
            return null;
        }     
        return ticketnoList;
    }

    @Override
    public List listSector(String ticketid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
