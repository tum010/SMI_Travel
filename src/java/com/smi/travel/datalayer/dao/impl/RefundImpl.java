/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.RefundDao;
import com.smi.travel.datalayer.entity.AirticketFlight;
import com.smi.travel.datalayer.entity.AirticketPassenger;
import com.smi.travel.datalayer.entity.AirticketRefund;
import com.smi.travel.datalayer.entity.RefundAirticket;
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
import org.hibernate.Query;
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
    private static final String SELECT_REFUND_DETAIL = "FROM AirticketRefund ar where ar.id = :refundid";
    private static final String SELECT_TICKETNO = "From AirticketPassenger psg where psg.airticketAirline.airticketPnr.airticketBooking.master.referenceNo = :refno";
    private static final String SELECT_SECTOR = "From AirticketPassenger psg where psg.id = :ticid";
    private static final String GET_REFUND = "FROM AirticketRefund ar where ar.refundAirticket.refundDate = :refundDate and ar.refundAirticket.refundBy = :refundBy and ar.refundAirticket.receiveBy = :receiveBy and ar.refundAirticket.receiveDate = :receiveDate" ;
    private static final String DELETE_AIRTICKET_REFUND = "DELETE FROM AirticketRefund ar where ar.id = :airticketrefundid";
    
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
        else{
            List<RefundTicket> listRefundTicket = new LinkedList<RefundTicket>();
            listRefundTicket = setRefundAndRefundDetail(airbookingidList);
            return listRefundTicket;
        }   
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
    
    @Override
    public List listRefundDetail(String refundid) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();  
        List<AirticketRefund> airbookingidList = session.createQuery(SELECT_REFUND_DETAIL)
                .setParameter("refundid", refundid)
                .list();
        if (airbookingidList.isEmpty()) {
            return null;
        }else{
            List<RefundTicket> listRefundTicket = new LinkedList<RefundTicket>();
            listRefundTicket = setRefundAndRefundDetail(airbookingidList);
            return listRefundTicket;
        }       
    }

    @Override
    public String saveRefund(AirticketRefund airticketrefund) {
        String result = "";
        Session session = this.sessionFactory.openSession();
        try { 
            transaction = session.beginTransaction();
            
            RefundAirticket refund = airticketrefund.getRefundAirticket();
            if(refund != null){
                session.save(refund);
                List<RefundAirticketDetail> refundDetail = refund.getRefundAirticketDetails();
                if(refundDetail != null){
                    for (int i = 0; i < refundDetail.size(); i++) {
                        session.save(refundDetail.get(i));
                    }
                }
                session.save(airticketrefund);
                result = "success";
            }
            transaction.commit();
            session.close();
            this.sessionFactory.close();
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            session.close();
            this.sessionFactory.close();
            result = "fail";
        }
        return result;
    }
    
     @Override
    public String updateRefund(AirticketRefund airticketrefund) {
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            RefundAirticket refund = airticketrefund.getRefundAirticket();
            if(refund != null){
                session.update(refund);
                List<RefundAirticketDetail> refundDetail = refund.getRefundAirticketDetails();
                if(refundDetail != null){
                    for (int i = 0; i < refundDetail.size(); i++) {
                        if (refundDetail.get(i).getId() == null) {
                            session.save(refundDetail.get(i));
                        } else {
                            session.update(refundDetail.get(i));
                        }
                    }
                }
                session.update(airticketrefund);
                result = "success";
            }
            transaction.commit();
            session.close();
            this.sessionFactory.close();
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            result = "fail";
        }
        return  result;
    }
    

    @Override
    public List searchRefund(RefundAirticket refund) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();  
        List<AirticketRefund> airbookingidList = session.createQuery(GET_REFUND)
                .setParameter("refundDate", refund.getRefundDate())
                .setParameter("refundBy", refund.getRefundBy())
                .setParameter("receiveBy", refund.getReceiveBy())
                .setParameter("receiveDate", refund.getReceiveDate())
                .list();
        if (airbookingidList.isEmpty()) {
            return null;
        }else{
            List<RefundTicket> listRefundTicket = new LinkedList<RefundTicket>();
            listRefundTicket = setRefundAndRefundDetail(airbookingidList);
       
            return listRefundTicket;
        }
    }
    
    private List<RefundTicket> setRefundAndRefundDetail(List<AirticketRefund> airbookingidList){
        List<RefundTicket> listRefundTicket = new LinkedList<RefundTicket>();
        List<RefundTicketDetail> listRefundTicketDetail = new LinkedList<RefundTicketDetail>();
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();  
         for(int i = 0; i< airbookingidList.size() ; i++){
                RefundTicket refundTicket = new RefundTicket();
                refundTicket.setId(airbookingidList.get(i).getRefundAirticket().getId());
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
                refundTicket.setRefundcode(airbookingidList.get(i).getRefundAirticket().getRefundBy());
                refundTicket.setRefunddate(airbookingidList.get(i).getRefundAirticket().getRefundDate());
                refundTicket.setReceivedate(airbookingidList.get(i).getRefundAirticket().getReceiveDate());
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
                    // Refund Detail
                    RefundTicketDetail refundTicketDetail = new RefundTicketDetail();
                        refundTicketDetail.setRefunddetailid(rRefundAirticketDetail.get(j).getId());
                        //Ticket
                        refundTicketDetail.setTicketid(rRefundAirticketDetail.get(j).getAirticketPassenger().getId());
                        refundTicketDetail.setTicketno(
                                rRefundAirticketDetail.get(j).getAirticketPassenger().getSeries1()+""+
                                rRefundAirticketDetail.get(j).getAirticketPassenger().getSeries2()+""+
                                rRefundAirticketDetail.get(j).getAirticketPassenger().getSeries3());
                        // Sector
                        List<AirticketPassenger> ticketnoList = session.createQuery(SELECT_SECTOR)
                            .setParameter("ticid", rRefundAirticketDetail.get(j).getAirticketPassenger().getId())
                            .list();
                        List<AirticketFlight> list = new ArrayList<AirticketFlight>(ticketnoList.get(0).getAirticketAirline().getAirticketFlights());
                        String sector = util.GetRounting(list);
                        refundTicketDetail.setSector(sector);
                        //Sector Refund
                        refundTicketDetail.setSectorRefund(rRefundAirticketDetail.get(j).getSectorRefund());

                        //Charge
                        if(rRefundAirticketDetail.get(j).getPayCustomer() != null ){
                            refundTicketDetail.setCharge(df.format(rRefundAirticketDetail.get(j).getPayCustomer()));
                        }else{
                            refundTicketDetail.setCharge("");
                        }
                    listRefundTicketDetail.add(refundTicketDetail);
                }
                refundTicket.setChange(df.format(sumCharge));
                refundTicket.setDetail(airbookingidList.get(i).getRefundAirticket().getRemark());
                refundTicket.setRefundTicketDetail(listRefundTicketDetail);

                listRefundTicket.add(refundTicket);
            }
         return listRefundTicket;
    }

    @Override
    public String deleteAirticketRefund(String airticketRefund) {
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
                Query query = session.createQuery(DELETE_AIRTICKET_REFUND);
                query.setParameter("airticketrefundid", airticketRefund);
                System.out.println("Delete  airticket refund: "+query.executeUpdate());
                transaction.commit();
                session.close();
                this.sessionFactory.close();
                result = "delete success";      
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            result = "delete fail";
        }
        return result;
    }

   
}
