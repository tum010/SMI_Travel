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
import com.smi.travel.datalayer.entity.PaymentStock;
import com.smi.travel.datalayer.view.entity.BookingOutboundView;
import com.smi.travel.datalayer.view.entity.PaymentOutboundAllDetail;
import com.smi.travel.datalayer.view.entity.PaymentOutboundSummary;
import com.smi.travel.datalayer.view.entity.PaymentOutboundView;
import com.smi.travel.util.UtilityFunction;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
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
            paymentOutboundDetailView.setValue(paymentOutboundDetail.getValue() != null ? paymentOutboundDetail.getValue() : null);
            paymentOutboundDetailView.setAccCode(!"".equalsIgnoreCase(paymentOutboundDetail.getAccCode()) ? paymentOutboundDetail.getAccCode() : "");
            paymentOutboundDetailView.setBookDetailType(!"".equalsIgnoreCase(paymentOutboundDetail.getBookDetailType()) ? paymentOutboundDetail.getBookDetailType() : "");
            paymentOutboundDetailView.setPayStockId(paymentOutboundDetail.getPaymentStock()!= null ? paymentOutboundDetail.getPaymentStock().getId() : "");
            paymentOutboundDetailView.setPayStock(paymentOutboundDetail.getPaymentStock()!= null ? paymentOutboundDetail.getPaymentStock().getPayStockNo(): "");
            paymentOutboundDetailView.setExportDate(paymentOutboundDetail.getExportDate() != null ? paymentOutboundDetail.getExportDate() : null);
            paymentOutboundDetailView.setIsExport(paymentOutboundDetail.getIsExport() != null ? paymentOutboundDetail.getIsExport() : null);
            paymentOutboundDetailView.setIsVatRecCom(paymentOutboundDetail.getIsVatRecCom()!= null ? paymentOutboundDetail.getIsVatRecCom(): null);
            paymentOutboundDetailView.setVatRecCom(paymentOutboundDetail.getVatRecCom()!= null ? paymentOutboundDetail.getVatRecCom(): null);
            paymentOutboundDetailView.setWht(paymentOutboundDetail.getWht()!= null ? paymentOutboundDetail.getWht(): null);
            paymentOutboundDetailView.setPayExRate(paymentOutboundDetail.getPayExRate()!= null ? paymentOutboundDetail.getPayExRate(): null);
            paymentOutboundDetailView.setRealExRate(paymentOutboundDetail.getRealExRate()!= null ? paymentOutboundDetail.getRealExRate(): null);
            paymentOutboundDetailView.setSaleAmount(paymentOutboundDetail.getSaleAmount()!= null ? paymentOutboundDetail.getSaleAmount(): null);
            paymentOutboundDetailView.setVatRecComAmount(paymentOutboundDetail.getVatRecComAmount()!= null ? paymentOutboundDetail.getVatRecComAmount(): null);
            paymentOutboundDetailView.setWhtAmount(paymentOutboundDetail.getWhtAmount()!= null ? paymentOutboundDetail.getWhtAmount(): null);
            paymentOutboundDetailView.setIsWht(paymentOutboundDetail.getIsWht()!= null ? paymentOutboundDetail.getIsWht() : null);
            paymentOutboundDetailView.setSaleCurrency(!"".equalsIgnoreCase(paymentOutboundDetail.getSaleCurrency()) ? paymentOutboundDetail.getSaleCurrency() : "");
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
                .addScalar("curcost",Hibernate.STRING)
                .addScalar("bookid",Hibernate.STRING)
                .addScalar("price",Hibernate.STRING) 
                .addScalar("cur",Hibernate.STRING) 
                .list();

        for (Object[] B : QueryList) {
            BookingOutboundView bookingOutboundView = new BookingOutboundView();
            bookingOutboundView.setRefNo(B[0] != null ? util.ConvertString(B[0]) : "");
            bookingOutboundView.setType(B[1] != null ? util.ConvertString(B[1]) : "");
            bookingOutboundView.setDescription(B[2] != null ? util.ConvertString(B[2]) : "");
            bookingOutboundView.setBilltype(B[3] != null ? util.ConvertString(B[3]) : "");
            bookingOutboundView.setCost(B[4] != null ? util.ConvertString(B[4]) : "");
            bookingOutboundView.setCurcost(B[5] != null ? util.ConvertString(B[5]) : "");
            bookingOutboundView.setBookid(B[6] != null ? util.ConvertString(B[6]) : "");
            bookingOutboundView.setSale(B[7] != null ? util.ConvertString(B[7]) : "");    
            bookingOutboundView.setCursale(B[8] != null ? util.ConvertString(B[8]) : "");    
            bookingOutboundViewList.add(bookingOutboundView);
        }
        return bookingOutboundViewList;
    }

    @Override
    public List<PaymentStock> getPaymentStock(String payStockNo) {
        String query = "from PaymentStock p where p.payStockNo = :payStockNo ";       
        Session session = this.sessionFactory.openSession();
        Query HqlQuery = session.createQuery(query);
        HqlQuery.setParameter("payStockNo", payStockNo);
//        HqlQuery.setMaxResults(MAX_ROW);
        List<PaymentStock> paymentStockList = HqlQuery.list();
        if (paymentStockList.isEmpty()) {
            this.sessionFactory.close();
            session.close();
            return null;
        }
        this.sessionFactory.close();
        session.close();
        return paymentStockList;
    }

    @Override
    public List<PaymentOutboundView> searchPaymentOutboundByFilter(String fromDate, String toDate, String status, String invSupCode, String invSupName, String refNo) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List<PaymentOutboundView> paymentOutboundViewList = new ArrayList<PaymentOutboundView>();
        String query = " SELECT * FROM `payment_outbound_view` p ";
        boolean haveCondition = false;
        if(!"".equalsIgnoreCase(fromDate) && fromDate != null && !"".equalsIgnoreCase(toDate) && toDate != null){
            query += (haveCondition ? " AND " : " WHERE ");
            query += " (p.paydate BETWEEN '" + fromDate + "' AND '" + toDate + "') ";
            haveCondition = true;
        }
        if(!"".equalsIgnoreCase(status) && status != null){
            query += (haveCondition ? " AND " : " WHERE ");
            query += " p.status = '" + status + "' ";
            haveCondition = true;
        }
        if(!"".equalsIgnoreCase(invSupCode) && invSupCode != null){
            query += (haveCondition ? " AND " : " WHERE ");
            query += " p.suppliercode = '" + invSupCode + "' ";
            haveCondition = true;
        }
        if(!"".equalsIgnoreCase(refNo) && refNo != null){
            query += (haveCondition ? " AND " : " WHERE ");
            query += " p.refno LIKE '%" + refNo + "%' ";
            haveCondition = true;
        }
        
        List<Object[]> QueryList =  session.createSQLQuery(query)
                .addScalar("paymentid",Hibernate.STRING)
                .addScalar("payno",Hibernate.STRING)
                .addScalar("paydate",Hibernate.STRING)
                .addScalar("duepaymentdate",Hibernate.STRING)
                .addScalar("invoicesup",Hibernate.STRING)
                .addScalar("suppliercode",Hibernate.STRING)
                .addScalar("refno",Hibernate.STRING)
                .addScalar("invoiceno",Hibernate.STRING) 
                .addScalar("amount",Hibernate.STRING)
                .addScalar("sale",Hibernate.STRING) 
                .addScalar("status",Hibernate.STRING)
                .addScalar("diff",Hibernate.STRING)
                .addScalar("curamount",Hibernate.STRING) 
                .addScalar("cursale",Hibernate.STRING) 
                .list();

        for (Object[] B : QueryList) {
            PaymentOutboundView paymentOutboundView = new PaymentOutboundView();
            paymentOutboundView.setPaymentid(B[0] != null ? util.ConvertString(B[0]) : "");
            paymentOutboundView.setPayno(B[1] != null ? util.ConvertString(B[1]) : "");
            paymentOutboundView.setPaydate(B[2] != null ? util.ConvertString(B[2]) : "");
            paymentOutboundView.setDuepaymentdate(B[3] != null ? util.ConvertString(B[3]) : "");
            paymentOutboundView.setInvoicesup(B[4] != null ? util.ConvertString(B[4]) : "");
            paymentOutboundView.setSuppliercode(B[5] != null ? util.ConvertString(B[5]) : "");
            paymentOutboundView.setRefno(B[6] != null ? (util.ConvertString(B[6])).replaceAll(",", " ") : "");
            paymentOutboundView.setInvoiceno(B[7] != null ? (util.ConvertString(B[7])).replaceAll(",", " ") : "");
            paymentOutboundView.setAmount(B[8] != null ? util.ConvertString(B[8]) : "");
            paymentOutboundView.setSale(B[9] != null ? util.ConvertString(B[9]) : "");
            paymentOutboundView.setStatus(B[10] != null ? util.ConvertString(B[10]) : "");
            paymentOutboundView.setDiff(B[11] != null ? util.ConvertString(B[11]) : "");
            paymentOutboundView.setCuramount(B[12] != null ? util.ConvertString(B[12]) : ""); 
            paymentOutboundView.setCursale(B[13] != null ? util.ConvertString(B[13]) : ""); 
            paymentOutboundViewList.add(paymentOutboundView);
        }
        return paymentOutboundViewList;
    }

    @Override
    public String deletePaymentOutbound(String paymentId) {
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            String sql = "delete from PaymentOutboundDetail pd where pd.paymentOutbound.id = :paymentId";
            Query query = session.createQuery(sql);
            query.setParameter("paymentId", paymentId);
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
        
        if("success".equalsIgnoreCase(result)){
            try {
                Session session = this.sessionFactory.openSession();
                transaction = session.beginTransaction();
                String sql = "delete from PaymentOutbound po where po.id = :paymentId";
                Query query = session.createQuery(sql);
                query.setParameter("paymentId", paymentId);
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
        }
        return result;
    }

    @Override
    public List getPaymentOutboundSummaryReport(String fromDate, String toDate, String status, String invSupCode, String refNo, String username) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        Date thisDate = new Date();
        List data = new ArrayList();
        String invSupCodeTemp = "ALL";
        String refNoTemp = "ALL";
        String Query = "SELECT * FROM `payment_outbound_summary` where paydate BETWEEN '"+fromDate+"' and '"+toDate+"' ";

        if ((status != null) && (!"".equalsIgnoreCase(status))) {
            Query += "  and status = '" + status + "'";
        } 
        
        if ((invSupCode != null) && (!"".equalsIgnoreCase(invSupCode))) {
            Query += "  and suppliercode = '" + invSupCode + "'";
        }
        
        if ((refNo != null) && (!"".equalsIgnoreCase(refNo))) {
            Query += "  and refno = '" + refNo + "'";
            refNoTemp = refNo;
        }      
        
        Query += " order by payno ";        
        System.out.println("Query : "+Query);
        
        List<Object[]> QueryTicketList = session.createSQLQuery(Query)
                .addScalar("paymentid", Hibernate.STRING)
                .addScalar("payno", Hibernate.STRING)
                .addScalar("paydate", Hibernate.STRING)
                .addScalar("duepaymentdate", Hibernate.STRING)
                .addScalar("invoicesup", Hibernate.STRING)
                .addScalar("suppliercode", Hibernate.STRING)
                .addScalar("refno", Hibernate.STRING)
                .addScalar("leader", Hibernate.STRING)
                .addScalar("invoiceno", Hibernate.STRING)
                .addScalar("detail", Hibernate.STRING) 
                .addScalar("amount", Hibernate.STRING)
                .addScalar("sale", Hibernate.STRING)
                .addScalar("export", Hibernate.STRING)
                .addScalar("status", Hibernate.STRING)
                .addScalar("diff", Hibernate.STRING)
                .addScalar("owner", Hibernate.STRING)
                .addScalar("amount_cur", Hibernate.STRING)
                .addScalar("sale_cur", Hibernate.STRING)
                .addScalar("value", Hibernate.STRING)
                .list();
            
            SimpleDateFormat dateformat = new SimpleDateFormat();
            dateformat.applyPattern("dd-MM-yyyy");
            
        for (Object[] B : QueryTicketList) {
            PaymentOutboundSummary sum = new PaymentOutboundSummary();

            sum.setSystemdate(util.ConvertString(new SimpleDateFormat("dd MMM yyyy hh:mm:ss", new Locale("us", "us")).format(thisDate)));
            sum.setUser(username);
            sum.setHeaderfromdate(util.ConvertString(new SimpleDateFormat("dd-MM-yyyy", new Locale("us", "us")).format(util.convertStringToDate(fromDate))));
            sum.setHeadertodate(util.ConvertString(new SimpleDateFormat("dd-MM-yyyy", new Locale("us", "us")).format(util.convertStringToDate(toDate))));
            sum.setHeaderstatus(status);
            sum.setHeaderrefno(refNoTemp);
            sum.setHeaderinvoicesupcode(invSupCodeTemp);
            sum.setDatefromto(sum.getHeaderfromdate() + " to " + sum.getHeadertodate());
            sum.setPaymentid(util.ConvertString(B[0]));
            sum.setPayno(util.ConvertString(B[1]));
            sum.setPaydate("null".equals(String.valueOf(B[2])) ? "" : util.ConvertString(new SimpleDateFormat("dd/MM/yyyy", new Locale("us", "us")).format(util.convertStringToDate(util.ConvertString(B[2])))));
            sum.setDuepaymentdate("null".equals(String.valueOf(B[3])) ? "" : util.ConvertString(new SimpleDateFormat("dd/MM/yyyy", new Locale("us", "us")).format(util.convertStringToDate(util.ConvertString(B[3])))));
            sum.setInvoicesup(util.ConvertString(B[4]));
            sum.setSuppliercode(util.ConvertString(B[5]));
            sum.setRefno(util.ConvertString(B[6]));
            sum.setLeader(util.ConvertString(B[7]));
            sum.setInvoiceno(B[8] != null ? (util.ConvertString(B[8])).replaceAll(",", "\n") : "");
            sum.setDetail(util.ConvertString(B[9])); 
            sum.setAmount(!"null".equalsIgnoreCase(String.valueOf(B[10])) ? util.ConvertString(B[10]) : "0.00");
            sum.setSale(!"null".equalsIgnoreCase(String.valueOf(B[11])) ? util.ConvertString(B[11]) : "0.00");
            sum.setExport(util.ConvertString(B[12]));
            sum.setStatus(util.ConvertString(B[13]));
            sum.setDiff(!"null".equalsIgnoreCase(String.valueOf(B[14])) ? util.ConvertString(B[14]) : "");
            sum.setOwner(util.ConvertString(B[15]));
            sum.setAmountcur(util.ConvertString(B[16]));
            sum.setSalecur(util.ConvertString(B[17]));
            sum.setValue(util.ConvertString(B[18]));
            
            if ((invSupCode != null) && (!"".equalsIgnoreCase(invSupCode)) && (util.ConvertString(B[4]) != null) && (!"".equalsIgnoreCase(util.ConvertString(B[4])))) {
                sum.setHeaderinvoicesupcode(util.ConvertString(B[4]));
            }
            
            data.add(sum);            
        }
        session.close();
        this.sessionFactory.close();
        return data;
    }

    @Override
    public PaymentOutbound getPaymentOutbound(String payId) {
        String query = "from PaymentOutbound p where p.id = :payId ";       
        Session session = this.sessionFactory.openSession();
        Query HqlQuery = session.createQuery(query);
        HqlQuery.setParameter("payId", payId);
//        HqlQuery.setMaxResults(MAX_ROW);
        List<PaymentOutbound> paymentOutboundList = HqlQuery.list();
        if (paymentOutboundList.isEmpty()) {
            return null;
        }
        this.sessionFactory.close();
        session.close();
        return paymentOutboundList.get(0);
    }

    @Override
    public List getPaymentSummaryReport(String fromDate, String toDate, String saleby, String invSupCode, String refNo, String username) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        Date thisDate = new Date();
        List data = new ArrayList();
        String invSupCodeTemp = "ALL";
        String refNoTemp = "ALL";
        String saleByTemp = "ALL";
        String Query = "SELECT * FROM `payment_outbound_alldetail` where paydate BETWEEN '"+fromDate+"' and '"+toDate+"' ";
        
        if ((invSupCode != null) && (!"".equalsIgnoreCase(invSupCode))) {
            Query += "  and invsupcode = '" + invSupCode + "'";
            invSupCodeTemp = invSupCode;
        }
        
        if ((refNo != null) && (!"".equalsIgnoreCase(refNo))) {
            Query += "  and refno = '" + refNo + "'";
            refNoTemp = refNo;
        } 
        
        if ((saleby != null) && (!"".equalsIgnoreCase(saleby))) {
            Query += "  and staffusername = '" + saleby + "'";
            saleByTemp = saleby;
        } 
        
        Query += " order by refno ";        
        System.out.println("Query : "+Query);
        
        List<Object[]> QueryTicketList = session.createSQLQuery(Query)
                .addScalar("refno", Hibernate.STRING)
                .addScalar("issuedate", Hibernate.STRING)
                .addScalar("tourcode", Hibernate.STRING)
                .addScalar("invno", Hibernate.STRING)
                .addScalar("invdate", Hibernate.STRING)
                .addScalar("department", Hibernate.STRING)
                .addScalar("staff", Hibernate.STRING)
                .addScalar("term", Hibernate.STRING)
                .addScalar("invto", Hibernate.STRING)
                .addScalar("adult", Hibernate.STRING)
                .addScalar("child", Hibernate.STRING)
                .addScalar("infant", Hibernate.STRING)
                .addScalar("country", Hibernate.STRING)
                .addScalar("city", Hibernate.STRING)
                .addScalar("producttype", Hibernate.STRING)
                .addScalar("departdate", Hibernate.STRING)
                .addScalar("price", Hibernate.STRING)
                .addScalar("curprice", Hibernate.STRING)
                .addScalar("acc", Hibernate.STRING)
                .addScalar("pvno", Hibernate.STRING)
                .addScalar("paydate", Hibernate.STRING)
                .addScalar("invsup", Hibernate.STRING)
                .addScalar("invsupcode", Hibernate.STRING)
                .addScalar("payinvno", Hibernate.STRING)
                .addScalar("amount", Hibernate.STRING)
                .addScalar("paycur", Hibernate.STRING)
                .addScalar("payrate", Hibernate.STRING)
                .addScalar("realrate", Hibernate.STRING)
                .addScalar("amountlocalp", Hibernate.STRING)
                .addScalar("amountlocalr", Hibernate.STRING)
                .addScalar("vat", Hibernate.STRING)
                .addScalar("wht", Hibernate.STRING)
                .addScalar("grosspay", Hibernate.STRING)
                .addScalar("grossreal", Hibernate.STRING)
                .addScalar("paycomdate", Hibernate.STRING)
                .addScalar("paycompvno", Hibernate.STRING)
                .addScalar("paycomstaff", Hibernate.STRING)
                .addScalar("paycommission", Hibernate.STRING)
                .addScalar("balanceprofit", Hibernate.STRING)
                .addScalar("receiptno", Hibernate.STRING)
                .addScalar("receiptdate", Hibernate.STRING)
                .list();
            
            SimpleDateFormat dateformat = new SimpleDateFormat();
            dateformat.applyPattern("dd-MM-yyyy");
            
            SimpleDateFormat dateformat1 = new SimpleDateFormat();
            dateformat1.applyPattern("dd-MM-yyyy");
            
        for (Object[] B : QueryTicketList) {
            PaymentOutboundAllDetail sum = new PaymentOutboundAllDetail();
            sum.setSystemdate(util.ConvertString(new SimpleDateFormat("dd MMM yyyy hh:mm:ss", new Locale("us", "us")).format(thisDate)));
            sum.setUser(username);
            sum.setHeaderfromdate(util.ConvertString(dateformat.format(util.convertStringToDate(fromDate))));
            sum.setHeadertodate(util.ConvertString(dateformat.format(util.convertStringToDate(toDate))));
            sum.setHeaderrefno(refNoTemp);
            sum.setHeaderinvoicesupcode(invSupCodeTemp);
            sum.setDatefromto(sum.getHeaderfromdate() + " To " + sum.getHeadertodate());
            sum.setHeaderstaff(saleByTemp);
            
            sum.setRefno(util.ConvertString(B[0]));
            if((!"null".equalsIgnoreCase(String.valueOf(B[1])) && B[1] != null) && !"".equalsIgnoreCase(String.valueOf(B[1]))){
                sum.setIssuedate(util.ConvertString(dateformat.format(util.convertStringToDate(String.valueOf(B[1])))));
            }else{
                sum.setIssuedate("");
            }
            sum.setTourcode(util.ConvertString(B[2]));
            sum.setInvno(util.ConvertString(B[3]));
            
            if((!"null".equalsIgnoreCase(String.valueOf(B[4])) && B[4] != null) && !"".equalsIgnoreCase(String.valueOf(B[4]))){
                String invdatemp="";
                String invDate[] = String.valueOf(B[4]).split("\r\n");
                if(invDate.length > 1 ){
                   for(int x= 0; x<invDate.length;x++){
                       if(invDate[x] != null && !"".equalsIgnoreCase(invDate[x].trim())){
                            invdatemp += util.ConvertString(dateformat1.format(util.convertStringToDate(String.valueOf(invDate[x]).trim())))+"\r\n";
                            sum.setInvdate(invdatemp);
                       }
                   }
                }else{
                  sum.setInvdate(util.ConvertString(dateformat1.format(util.convertStringToDate(String.valueOf(B[4]).trim()))));
                }
            }else{
                sum.setInvdate("");
            }
            
            sum.setDepartment(util.ConvertString(B[5]));
            sum.setStaff(util.ConvertString(B[6]));
            sum.setTerm(util.ConvertString(B[7]));
            sum.setInvto(util.ConvertString(B[8]));
            sum.setAdult(util.ConvertString(B[9]));
            sum.setChild(util.ConvertString(B[10]));
            sum.setInfant(util.ConvertString(B[11]));
            sum.setCountry(util.ConvertString(B[12]));
            sum.setCity(util.ConvertString(B[13]));
            sum.setProducttype(util.ConvertString(B[14]));
            if((!"null".equalsIgnoreCase(String.valueOf(B[15])) && B[15] != null) && !"".equalsIgnoreCase(String.valueOf(B[15]))){
                String datetemp="";
                String date[] = String.valueOf(B[15]).split("\r\n");
                if(date.length > 1 ){
                   for(int x= 0; x<date.length;x++){
                       if(date[x] != null && !"".equalsIgnoreCase(date[x].trim())){
                            datetemp += util.ConvertString(dateformat1.format(util.convertStringToDate(String.valueOf(date[x]).trim())))+"\r\n";
                            sum.setDepartdate(datetemp);
                       }
                   }
                }else{
                  sum.setDepartdate(util.ConvertString(dateformat1.format(util.convertStringToDate(String.valueOf(B[15]).trim()))));
                }
            }else{
                sum.setDepartdate("");
            }
            sum.setPrice(!"null".equalsIgnoreCase(String.valueOf(B[16])) ? util.ConvertString(B[16]) : "0.00");
            sum.setCurprice(util.ConvertString(B[17]));
            sum.setAcc(util.ConvertString(B[18]));
            sum.setPvno(util.ConvertString(B[19]));
            if((!"null".equalsIgnoreCase(String.valueOf(B[20])) && B[20] != null) && !"".equalsIgnoreCase(String.valueOf(B[20]))){
                String datetemp="";
                String date[] = String.valueOf(B[20]).split("\r\n");
                if(date.length > 1 ){
                   for(int x= 0; x<date.length;x++){
                       if(date[x] != null && !"".equalsIgnoreCase(date[x].trim())){
                       datetemp += util.ConvertString(dateformat1.format(util.convertStringToDate(String.valueOf(date[x]).trim())))+"\r\n";
                       sum.setPaydate(datetemp);
                       }
                   }
                }else{
                  sum.setPaydate(util.ConvertString(dateformat1.format(util.convertStringToDate(String.valueOf(B[20]).trim()))));
                }
            }else{
                sum.setPaydate("");
            }
            sum.setInvsup(util.ConvertString(B[21]));
            sum.setInvsupcode(util.ConvertString(B[22]));
            sum.setPayinvno(util.ConvertString(B[23]));
            sum.setAmount(!"null".equalsIgnoreCase(String.valueOf(B[24])) ? util.ConvertString(B[24]) : "0.00");
            sum.setPaycur(util.ConvertString(B[25]));
            sum.setPayrate(!"null".equalsIgnoreCase(String.valueOf(B[26])) ? util.ConvertString(B[26]) : "0.00");
            sum.setRealrate(!"null".equalsIgnoreCase(String.valueOf(B[27])) ? util.ConvertString(B[27]) : "0.00");
            sum.setAmountlocalp(!"null".equalsIgnoreCase(String.valueOf(B[28])) ? util.ConvertString(B[28]) : "0.00");
            sum.setAmountlocalr(!"null".equalsIgnoreCase(String.valueOf(B[29])) ? util.ConvertString(B[29]) : "0.00");
            sum.setVat(!"null".equalsIgnoreCase(String.valueOf(B[30])) ? util.ConvertString(B[30]) : "0.00");
            sum.setWht(!"null".equalsIgnoreCase(String.valueOf(B[31])) ? util.ConvertString(B[31]) : "0.00");
            sum.setGrosspay(!"null".equalsIgnoreCase(String.valueOf(B[32])) ? util.ConvertString(B[32]) : "0.00");
            sum.setGrossreal(!"null".equalsIgnoreCase(String.valueOf(B[33])) ? util.ConvertString(B[33]) : "0.00");
            if((!"null".equalsIgnoreCase(String.valueOf(B[34])) && B[34] != null) && !"".equalsIgnoreCase(String.valueOf(B[34]))){
                String datetemp="";
                String date[] = String.valueOf(B[34]).split("\r\n");
                if(date.length > 1 ){
                   for(int x= 0; x<date.length;x++){
                       if(date[x] != null && !"".equalsIgnoreCase(date[x].trim())){
                            datetemp += util.ConvertString(dateformat1.format(util.convertStringToDate(String.valueOf(date[x]).trim())))+"\r\n";
                            sum.setPaycomdate(datetemp);
                       }
                   }
                }else{
                  sum.setPaycomdate(util.ConvertString(dateformat1.format(util.convertStringToDate(String.valueOf(B[34]).trim()))));
                }
            }else{
                sum.setPaycomdate("");
            }
            sum.setPaycompvno(util.ConvertString(B[35]));
            sum.setPaycomstaff(util.ConvertString(B[36]));
            sum.setPaycommission(!"null".equalsIgnoreCase(String.valueOf(B[37])) ? util.ConvertString(B[37]) : "0.00");
            sum.setBalanceprofit(!"null".equalsIgnoreCase(String.valueOf(B[38])) ? util.ConvertString(B[38]) : "0.00");
            sum.setReceiptno(util.ConvertString(B[39]));
            if((!"null".equalsIgnoreCase(String.valueOf(B[40])) && B[40] != null) && !"".equalsIgnoreCase(String.valueOf(B[40]))){
                String datetemp="";
                String date[] = String.valueOf(B[40]).split("\r\n");
                if(date.length > 1 ){
                   for(int x= 0; x<date.length;x++){
                       if(date[x] != null && !"".equalsIgnoreCase(date[x].trim())){
                            datetemp += util.ConvertString(dateformat1.format(util.convertStringToDate(String.valueOf(date[x]).trim())))+"\r\n";
                            sum.setReceiptdate(datetemp);
                       }
                   }
                }else{
                    sum.setReceiptdate(util.ConvertString(dateformat1.format(util.convertStringToDate(String.valueOf(B[40]).trim()))));
                }
            }else{
                sum.setReceiptdate("");
            }
           
            if ((invSupCode != null) && (!"".equalsIgnoreCase(invSupCode)) && (util.ConvertString(B[21]) != null) && (!"".equalsIgnoreCase(util.ConvertString(B[21])))) {
                sum.setHeaderinvoicesupcode(util.ConvertString(B[21]));
            }
            
            if ((saleby != null) && (!"".equalsIgnoreCase(saleby)) && (util.ConvertString(B[6]) != null) && (!"".equalsIgnoreCase(util.ConvertString(B[6])))) {
                sum.setHeaderstaff(util.ConvertString(B[6]));
            }
            
            data.add(sum);            
        }
        session.close();
        this.sessionFactory.close();
        return data;   
    }

    @Override
    public List<PaymentOutboundDetail> checkDuplicatePaymentStock(String payStockNo) {
        String query = "from PaymentOutboundDetail p where p.paymentStock.payStockNo = :payStockNo ";       
        Session session = this.sessionFactory.openSession();
        Query HqlQuery = session.createQuery(query);
        HqlQuery.setParameter("payStockNo", payStockNo);
        List<PaymentOutboundDetail> paymentOutboundDetailList = HqlQuery.list();
        if (paymentOutboundDetailList.isEmpty()) {
            this.sessionFactory.close();
            session.close();
            return null;
        }
        this.sessionFactory.close();
        session.close();
        return paymentOutboundDetailList;
    }

}
