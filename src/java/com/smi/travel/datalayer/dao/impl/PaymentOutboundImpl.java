/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.PaymentOutboundDao;
import com.smi.travel.datalayer.entity.MRunningCode;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.PaymentOutbound;
import com.smi.travel.datalayer.entity.PaymentOutboundDetail;
import com.smi.travel.datalayer.entity.PaymentOutboundDetailView;
import com.smi.travel.datalayer.view.entity.BookingOutboundView;
import com.smi.travel.util.UtilityFunction;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Hibernate;
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
        String result = "";
        Session session = this.sessionFactory.openSession();
        paymentOutbound.setUpdateDate(new Date());
        try { 
            setTransaction(session.beginTransaction());         
            session.update(paymentOutbound);
            List<PaymentOutboundDetail> paymentOutboundDetail = paymentOutbound.getPaymentOutboundDetails();
            if(paymentOutboundDetail != null){
                for (int i = 0; i < paymentOutboundDetail.size(); i++) {
                    if(!"".equalsIgnoreCase(paymentOutboundDetail.get(i).getId()) && paymentOutboundDetail.get(i).getId() != null){
                        session.update(paymentOutboundDetail.get(i));
                    }else{
                        session.save(paymentOutboundDetail.get(i));
                    }
                    
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
        query.setParameter("type", "PO");
        int result = query.executeUpdate();
        
        session.close();
        this.sessionFactory.close();
        return code;
    }

    @Override
    public List<PaymentOutboundDetailView> getPaymentOutboundDetail(String paymentOutboundId) {
        UtilityFunction utilfunction = new UtilityFunction();      
        Session session = this.sessionFactory.openSession();
        String sql = "from PaymentOutboundDetail pd where pd.paymentOutbound.id = :paymentOutboundId";
        List<PaymentOutboundDetail> paymentOutboundDetailList = session.createQuery(sql).setParameter("paymentOutboundId", paymentOutboundId).list();
        if (paymentOutboundDetailList.isEmpty()) {
            return null;
        }
        List<PaymentOutboundDetailView> paymentOutboundDetailViewList = new ArrayList<PaymentOutboundDetailView>();
        for(int i=0; i<paymentOutboundDetailList.size(); i++){
            PaymentOutboundDetail paymentOutboundDetail = new PaymentOutboundDetail();
            paymentOutboundDetail = paymentOutboundDetailList.get(i);
            PaymentOutboundDetailView paymentOutboundDetailView = new PaymentOutboundDetailView();
            paymentOutboundDetailView.setDetailId(paymentOutboundDetail.getId());
            paymentOutboundDetailView.setPayId(paymentOutboundDetail.getPaymentOutbound().getId());
            paymentOutboundDetailView.setRefNo(paymentOutboundDetail.getMaster() != null ? paymentOutboundDetail.getMaster().getReferenceNo() : "");
            paymentOutboundDetailView.setBookDetailId(paymentOutboundDetail.getBookDetailId() != null ? paymentOutboundDetail.getBookDetailId() : null);
            paymentOutboundDetailView.setType(paymentOutboundDetail.getMPaytype() != null ? paymentOutboundDetail.getMPaytype().getId() : "");
            paymentOutboundDetailView.setDescription(!"".equalsIgnoreCase(paymentOutboundDetail.getDescription()) ? paymentOutboundDetail.getDescription() : "");
            paymentOutboundDetailView.setInvoice(!"".equalsIgnoreCase(paymentOutboundDetail.getInvoiceCreditor()) ? paymentOutboundDetail.getInvoiceCreditor() : "");
            paymentOutboundDetailView.setCost(paymentOutboundDetail.getCost() != null ? paymentOutboundDetail.getCost() : null);
            paymentOutboundDetailView.setGross(paymentOutboundDetail.getGross() != null ? paymentOutboundDetail.getGross() : null);
            paymentOutboundDetailView.setVat(paymentOutboundDetail.getVat() != null ? paymentOutboundDetail.getVat() : null);
            paymentOutboundDetailView.setIsVat(paymentOutboundDetail.getIsVat() != null ? paymentOutboundDetail.getIsVat() : null);
            paymentOutboundDetailView.setAmount(paymentOutboundDetail.getAmount() != null ? paymentOutboundDetail.getAmount() : null);
            paymentOutboundDetailView.setCur(!"".equalsIgnoreCase(paymentOutboundDetail.getCurrency()) ? paymentOutboundDetail.getCurrency() : "");
            paymentOutboundDetailView.setComm(paymentOutboundDetail.getRecCom() != null ? paymentOutboundDetail.getRecCom() : null);
            paymentOutboundDetailView.setValue(paymentOutboundDetail.getValue() != null ? paymentOutboundDetail.getRecCom() : null);
            paymentOutboundDetailView.setAccCode(!"".equalsIgnoreCase(paymentOutboundDetail.getAccCode()) ? paymentOutboundDetail.getAccCode() : "");
            paymentOutboundDetailView.setBookDetailType(!"".equalsIgnoreCase(paymentOutboundDetail.getBookDetailType()) ? paymentOutboundDetail.getBookDetailType() : "");
            paymentOutboundDetailView.setPayStock(paymentOutboundDetail.getPayStockId() != null ? paymentOutboundDetail.getPayStockId() : null);
            paymentOutboundDetailView.setExportDate(paymentOutboundDetail.getExportDate() != null ? paymentOutboundDetail.getExportDate() : null);
            paymentOutboundDetailView.setIsExport(paymentOutboundDetail.getIsExport() != null ? paymentOutboundDetail.getIsExport() : null);
            paymentOutboundDetailViewList.add(paymentOutboundDetailView);
        }
        session.close();
        return paymentOutboundDetailViewList;
    }

    @Override
    public PaymentOutbound searchPaymentOutbound(String payNo) {
        Session session = this.sessionFactory.openSession();
        String sql = "from PaymentOutbound p where p.payNo = :payNo";
        List<PaymentOutbound> paymentOutboundList = session.createQuery(sql).setParameter("payNo", payNo).list();
        session.close();
        if(paymentOutboundList.isEmpty()){
            return null;
        }
        return paymentOutboundList.get(0);
    }

    @Override
    public List<String> getRefNoOutbound() {
        Session session = this.sessionFactory.openSession();
        String sql = "from Master m where m.bookingType = :bookingType";
        List<Master> list = session.createQuery(sql).setParameter("bookingType", "O").list();       
        if (list.isEmpty()) {
            return null;
        }
        
        List<String> refNoList = new ArrayList<String>();
        for (int i = 0;i<list.size();i++) {
            refNoList.add(list.get(i).getReferenceNo());
        }

        session.close();
        return refNoList;
    }

    @Override
    public String deletePaymentOutboundDetail(String paymentOutboundDetailId) {       
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            String sql = "delete from PaymentOutboundDetail pd where pd.id = :paymentOutboundDetailId";
            Query query = session.createQuery(sql);
            query.setParameter("paymentOutboundDetailId", paymentOutboundDetailId);
            query.executeUpdate();
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = "success";
        }catch (Exception ex){
            transaction.rollback();
            ex.printStackTrace();
            result = "fail";
        }
                      
        return result;
    }

    @Override
    public List<BookingOutboundView> getBookingOutboundView(String searchRefNo) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List<BookingOutboundView> bookingOutboundViewList = new ArrayList<BookingOutboundView>();
        List<Object[]> QueryList =  session.createSQLQuery("SELECT * FROM `booking_outbound_view` where `booking_outbound_view`.refno =  '" + searchRefNo + "'")
                .addScalar("refno",Hibernate.STRING)
                .addScalar("type",Hibernate.STRING)
                .addScalar("description",Hibernate.STRING)
                .addScalar("billtype",Hibernate.STRING)
                .addScalar("cost",Hibernate.STRING)
                .addScalar("cur",Hibernate.STRING)
                .addScalar("bookid",Hibernate.STRING) 
                .list();

        for (Object[] B : QueryList) {
            BookingOutboundView bookingOutboundView = new BookingOutboundView();
            bookingOutboundView.setRefNo(B[0] != null ? util.ConvertString(B[0]) : "");
            bookingOutboundView.setType(B[1] != null ? util.ConvertString(B[1]) : "");
            bookingOutboundView.setDescription(B[2] != null ? util.ConvertString(B[2]) : "");
            bookingOutboundView.setBilltype(B[3] != null ? util.ConvertString(B[3]) : "");
            bookingOutboundView.setCost(B[4] != null ? util.ConvertString(B[4]) : "");
            bookingOutboundView.setCur(B[5] != null ? util.ConvertString(B[5]) : "");
            bookingOutboundView.setBookid(B[6] != null ? util.ConvertString(B[6]) : "");          
            bookingOutboundViewList.add(bookingOutboundView);
        }
        return bookingOutboundViewList;
    }
}
