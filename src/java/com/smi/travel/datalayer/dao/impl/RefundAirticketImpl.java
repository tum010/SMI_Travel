/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.RefundAirticketDao;
import com.smi.travel.datalayer.entity.PaymentAirticketRefund;
import com.smi.travel.datalayer.entity.RefundAirticket;
import com.smi.travel.datalayer.entity.RefundAirticketDetail;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Surachai
 */
public class RefundAirticketImpl implements RefundAirticketDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;

    @Override
    public String InsertRefundAirticket(RefundAirticket refund) {
        
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(refund);
            for (int i = 0; i < refund.getRefundAirticketDetails().size(); i++) {
                RefundAirticketDetail detail = (RefundAirticketDetail) refund.getRefundAirticketDetails().get(i);
                detail.setRefundAirticket(refund);
                session.save(detail);
            }
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
    public String UpdateRefundAirticket(RefundAirticket refund) {
        
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            String refundNo = refund.getRefundNo();
            List<RefundAirticket> refunAirList = session.createQuery("from  RefundAirticket refund where  refund.refundNo = :refundNo").setParameter("refundNo", refundNo).list();
            if (refunAirList.isEmpty()) {
                return "";
            }
            RefundAirticket dbRefund = refunAirList.get(0);
            dbRefund.setRefundDate(refund.getRefundDate());
            dbRefund.setRefundBy(refund.getRefundBy());
            dbRefund.setRemark(refund.getRemark());
            dbRefund.setAgent(refund.getAgent());
            dbRefund.setReceiveBy(refund.getReceiveBy());
            dbRefund.setReceiveDate(refund.getReceiveDate());
            session.update(dbRefund);
            for (int i = 0; i < refund.getRefundAirticketDetails().size(); i++) {
                RefundAirticketDetail detail = (RefundAirticketDetail) refund.getRefundAirticketDetails().get(i);
                detail.setRefundAirticket(dbRefund);
                if(detail.getId() == null || "".equals(detail.getId().trim())){
                    session.save(detail);
                }else{
                    session.update(detail);
                }
            }
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
    public String DeleteRefundAirticket(RefundAirticket refund) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean DeleteRefundAirticketDetail(String refundDetailID) {
        Boolean result = false;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            RefundAirticketDetail detail = new RefundAirticketDetail();
            detail.setId(refundDetailID);
            session.delete(detail);
            
            transaction.commit();
            session.close();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            result = false;
        }
        return result;
    }

    @Override
    public RefundAirticket getRefundAirTicketFromRefundNo(String refundNo) {
        
        String query = "from  RefundAirticket refund where  refund.refundNo = :refundNo";
        Session session = this.sessionFactory.openSession();
        RefundAirticket result = new RefundAirticket();
        List<RefundAirticket> List = session.createQuery(query).setParameter("refundNo", refundNo).list();
        if (List.isEmpty()) {
            return null;
        }

        result = List.get(0);
        return result;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Boolean checkPaymentAirticketRefund(String refundDetailId) {
        boolean result = false;
        String query = "from PaymentAirticketRefund  air where air.refundAirticketDetail.id = :refunddetailid";
        Session session = this.sessionFactory.openSession();
        List<PaymentAirticketRefund> airticketRefunds = session.createQuery(query).setParameter("refunddetailid", refundDetailId).list();
        if (airticketRefunds.isEmpty()) {
            result = true;
        }else{
            result = false;
        }
        System.out.println("=== result === " + result + "===");
        session.close();
        this.sessionFactory.close();
        return result;   
    }
    
    
    
}
