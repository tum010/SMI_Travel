/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.PaymentWendytourDao;
import com.smi.travel.datalayer.entity.Daytour;
import com.smi.travel.datalayer.entity.DaytourBooking;
import com.smi.travel.datalayer.entity.MPaytype;
import com.smi.travel.datalayer.entity.MRunningCode;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.PaymentDetailWendy;
import com.smi.travel.datalayer.entity.PaymentStock;
import com.smi.travel.datalayer.entity.PaymentWendy;
import com.smi.travel.datalayer.entity.PaymentWendyReference;
import com.smi.travel.datalayer.entity.TourOperationDesc;
import com.smi.travel.datalayer.view.entity.CustomerAgentInfo;
import com.smi.travel.datalayer.view.entity.InvoiceSupplier;
import com.smi.travel.datalayer.view.entity.PaymentTourHotelSummary;
import com.smi.travel.datalayer.view.entity.PaymentWendytourView;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
 * @author Surachai
 */
public class PaymentWendytourImpl implements PaymentWendytourDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;
    private static final int MAX_ROW = 200;
    
    @Override
    public String InsertPaymentWendy(PaymentWendy payment,String option) {
        
        String result = "fail";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();    
            String runningCode = gennaratePaymentRunning(payment.getPayDate());           
            payment.setPayNo(runningCode);
            if("daytour".equalsIgnoreCase(option)){
                Date date = Calendar.getInstance().getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                String refCode = sdf.format(date);
                refCode = refCode.substring(2);
                List<PaymentDetailWendy> paymentDetailWendyList = new ArrayList<PaymentDetailWendy>();
                paymentDetailWendyList = payment.getPaymentDetailWendies();
                PaymentDetailWendy paymentDetailWendy = new PaymentDetailWendy();
                paymentDetailWendy = paymentDetailWendyList.get(0);
//                paymentDetailWendy.setInvoiceCreditor("PW"+refCode+runningCode);
            }
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
    public String UpdatePaymentWendy(PaymentWendy payment,String option) {
        String result = "fail";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            payment.setUpdateDate(new Date());
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
//            transaction.rollback();
//            session.close();
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
            String query = "from PaymentWendy p where p.id = :id";
            List<PaymentWendy> List = session.createQuery(query).setParameter("id", payment.getId()).list();
            if (List.isEmpty()) {
                return "fail";
            }
            for(int i = 0 ; i < List.get(0).getPaymentDetailWendies().size() ; i++){
                session.delete(List.get(0).getPaymentDetailWendies().get(i));    
            }
            session.delete(List.get(0));
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
    public List<PaymentWendytourView> SearchPaymentFromFilter(String dateFrom, String dateTo, String payType , String InvoiceSupCode, String selectStatus) {
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
        if ((InvoiceSupCode != null) && (!"".equalsIgnoreCase(InvoiceSupCode))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" payment.invoiceSup = '" + InvoiceSupCode + "'");
            haveCondition = true;
        }
        if ((selectStatus != null) && (!"".equalsIgnoreCase(selectStatus))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" payment.MItemstatus.id = '" + selectStatus + "'");
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
             
             String InvoiceSupName = getInvoiceSupName(payment.getInvoiceSup());
             paymentview.setInvoiceSup(InvoiceSupName);
             if(payment.getAccount() != null){
                paymentview.setAccNo(payment.getAccount());
             } else {
                paymentview.setAccNo(null);
             }
             
             paymentview.setTotal(sum);
           //  paymentview.setCurrency(payment.getCurrency());
             if(payment.getMItemstatus() != null){
                 paymentview.setStatus(payment.getMItemstatus().getName());
             }
            
            BigDecimal total = new BigDecimal(0);
            List<PaymentDetailWendy> paymentDetailWendyList = new ArrayList<PaymentDetailWendy>(payment.getPaymentDetailWendies()); 
            for(int j=0; j< paymentDetailWendyList.size(); j++){
                PaymentDetailWendy paymentDetailWendy = new PaymentDetailWendy();
                if(paymentDetailWendyList.get(j).getAmount() != null){
                    BigDecimal amount = paymentDetailWendyList.get(j).getAmount();
                    total = total.add(amount);
                }                                         
            }
            paymentview.setTotal(total);
             
             paymentviewList.add(paymentview);
         }
         
         return paymentviewList;
    }
    
    private String getInvoiceSupName(String invoiceSupCode) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List<Object[]> invoiceSupplierList = session.createSQLQuery(" SELECT * FROM `invoice_supplier` WHERE `invoice_supplier`.code = '" + invoiceSupCode + "'")
                .addScalar("id", Hibernate.STRING)
                .addScalar("code", Hibernate.STRING)
                .addScalar("name", Hibernate.STRING)
                .addScalar("apcode", Hibernate.STRING)
                .list();
        
        String invoiceSupName = "";
        List<InvoiceSupplier> result = new ArrayList<InvoiceSupplier>();
        for (Object[] A : invoiceSupplierList) {
            InvoiceSupplier invoiceSupplier = new InvoiceSupplier();
            invoiceSupplier.setId(util.ConvertString(A[0]));
            invoiceSupplier.setCode(util.ConvertString(A[1]));
            invoiceSupplier.setName(util.ConvertString(A[2]));
            invoiceSupplier.setApcode(util.ConvertString(A[3]));
            invoiceSupName = invoiceSupplier.getName();
        }       
        return invoiceSupName;
    }
    
    public String gennaratePaymentRunning(Date payDate){       
        String payNo = "";
        Session session = this.sessionFactory.openSession();
        List<String> list = new LinkedList<String>();
        SimpleDateFormat df = new SimpleDateFormat("yyMM", Locale.US);
        String querysql = "";

        querysql = "SELECT RIGHT(pay_no, 4) as paynum  FROM payment_wendy where pay_no Like :payno ORDER BY RIGHT(pay_no, 4) desc";
        Query query = session.createSQLQuery(querysql);
        query.setParameter("payno", "%"+ df.format(payDate) + "%");
        query.setMaxResults(1);
        list = query.list();
        if (list.isEmpty()) {
                payNo = "PW"+df.format(payDate) + "-" + "0001";
        } else {
                payNo = String.valueOf(list.get(0));
                if (!payNo.equalsIgnoreCase("")) {
                        int running = Integer.parseInt(payNo) + 1;
                        String temp = String.valueOf(running);
                        for (int i = temp.length(); i < 4; i++) {
                                temp = "0" + temp;
                        }
                        payNo = "PW"+df.format(payDate) + "-" + temp;
                }
        }
        session.close();
        this.sessionFactory.close();
        return payNo.replace("-","");
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
    
    public List<String> getMasterAll(){
        String query = "from Master M";
        Session session = this.sessionFactory.openSession();
        List<Master> list = session.createQuery(query).list();
        
        if (list.isEmpty()) {
            return null;
        }
        
        List<String> RefNoList = new ArrayList<String>();
        for (int i = 0;i<list.size();i++) {
            RefNoList.add(list.get(i).getReferenceNo());
        }
       
        
        return RefNoList;   
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

    @Override
    public MPaytype getAccountCode(String PayType) {
        String query = "from MPaytype m where m.name = :payType";
        Session session = this.sessionFactory.openSession();
        List<MPaytype> List = session.createQuery(query).setParameter("payType", PayType).list();
        if (List.isEmpty()) {
            session.close();
            return null;
        }
        
//        if("1".equalsIgnoreCase(PayType)){
//            acc_code = "51010";
//            
//        } else if ("2".equalsIgnoreCase(PayType)){
//            acc_code = "51040";
//                    
//        } else if ("3".equalsIgnoreCase(PayType)){
//            acc_code = "51020";
//            
//        } else if ("4".equalsIgnoreCase(PayType)){
//            acc_code = "51023";
//            
//        } else if ("5".equalsIgnoreCase(PayType)){
//            acc_code = "51030";
//            
//        } else if ("6".equalsIgnoreCase(PayType)){
//            acc_code = "51035";
//        }
        session.close();
        return List.get(0);
    }
    
    @Override
    public PaymentWendy getInvoiceSupCodeByGuideName(String guideName) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List<Object[]> guideList = session.createSQLQuery(" SELECT * FROM `invoice_supplier` WHERE `invoice_supplier`.name = '" + guideName + "'")
                .addScalar("id", Hibernate.STRING)
                .addScalar("code", Hibernate.STRING)
                .addScalar("name", Hibernate.STRING)
                .addScalar("apcode", Hibernate.STRING)
                .list();
        
        PaymentWendy code = new PaymentWendy();
        for (Object[] A : guideList) {
            InvoiceSupplier invoiceSupplier = new InvoiceSupplier();
            invoiceSupplier.setId(util.ConvertString(A[0]));
            invoiceSupplier.setCode(util.ConvertString(A[1]));
            invoiceSupplier.setName(util.ConvertString(A[2]));
            invoiceSupplier.setApcode(util.ConvertString(A[3]));
            code.setInvoiceSup(invoiceSupplier.getCode());
            code.setApCode(invoiceSupplier.getApcode());
        }
               
        return code;
    }
    
    @Override
    public String getGuideName(PaymentWendy paymentWendy) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        String invoiceSupCode = paymentWendy.getInvoiceSup();
        List<Object[]> guideList = session.createSQLQuery(" SELECT * FROM `invoice_supplier` WHERE `invoice_supplier`.code = '" + invoiceSupCode + "'")
                .addScalar("id", Hibernate.STRING)
                .addScalar("code", Hibernate.STRING)
                .addScalar("name", Hibernate.STRING)
                .addScalar("apcode", Hibernate.STRING)
                .list();
        
        String guideName = "";
        for (Object[] A : guideList) {
            InvoiceSupplier invoiceSupplier = new InvoiceSupplier();
            invoiceSupplier.setId(util.ConvertString(A[0]));
            invoiceSupplier.setCode(util.ConvertString(A[1]));
            invoiceSupplier.setName(util.ConvertString(A[2]));
            invoiceSupplier.setApcode(util.ConvertString(A[3]));
            guideName = invoiceSupplier.getName();
        }
               
        return guideName;
    }


    @Override
    public String getPaymentRefernenceCode(String from, String to, List<String> tour) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTourListFromDate(String from, String to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaymentWendy getPaymentWendyFromDayTourOperation(TourOperationDesc tourOperationDesc) {
        String id = tourOperationDesc.getId();
        String query = "from PaymentWendy p where p.tourOperationDesc.id = :id";
        Session session = this.sessionFactory.openSession();
        PaymentWendy result = new PaymentWendy();
        List<PaymentWendy> List = session.createQuery(query).setParameter("id", id).list();
        if (List.isEmpty()) {
            return null;
        }

        result = List.get(0);
        return result;
    }

    @Override
    public String InsertPaymentWendyReference(PaymentWendyReference paymentWendyReference) {
        String result = "fail";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();                
            session.save(paymentWendyReference);                
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
    public List getPaymentTourHotelSummary(String from, String to, String pvtype, String status, String invSupCode, String printBy) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList<PaymentTourHotelSummary>();
        
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MMM-yyyy HH:mm:ss");         
        
        String pvtypeshow = "ALL";
        StringBuffer query = new StringBuffer(" SELECT * FROM `payment_tour_hotel_summary` ");
        boolean haveCondition = false;
        if ((from != null) && (!"".equalsIgnoreCase(from))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" `payment_tour_hotel_summary`.paydate >= '" + from + "'");
            haveCondition = true;
        }
        if ((to != null) && (!"".equalsIgnoreCase(to))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" `payment_tour_hotel_summary`.paydate <= '" + to + "'");
            haveCondition = true;
        }
        if ((status != null) && (!"".equalsIgnoreCase(status))) {
            if("NORMAL".equalsIgnoreCase(status)){status = "";}
            query.append(haveCondition ? " and" : " where");
            query.append(" `payment_tour_hotel_summary`.idstatus = '" + status + "'");
            haveCondition = true;
        }
        if ((pvtype != null) && (!"".equalsIgnoreCase(pvtype))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" `payment_tour_hotel_summary`.idpvtype = '" + pvtype + "'");
            haveCondition = true;
            pvtypeshow = pvtype;
        }
        if ((invSupCode != null) && (!"".equalsIgnoreCase(invSupCode))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" `payment_tour_hotel_summary`.supcode = '" + invSupCode + "'");
            haveCondition = true;
        }
        query.append(" ORDER BY `supplier` ");

        List<Object[]> QueryList =  session.createSQLQuery(query.toString())
                .addScalar("payno",Hibernate.STRING)
                .addScalar("paydate",Hibernate.STRING)
                .addScalar("paymenttype",Hibernate.STRING)
                .addScalar("supplier",Hibernate.STRING)
                .addScalar("refno",Hibernate.STRING)
                .addScalar("invno",Hibernate.STRING)
                .addScalar("amount",Hibernate.STRING)
                .addScalar("cur",Hibernate.STRING)
                .addScalar("status",Hibernate.STRING)
                .addScalar("description",Hibernate.STRING)
                .addScalar("commission",Hibernate.STRING)
                .list();
        
        int no = 1;
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("dd-MM-yyyy");
        for (Object[] B : QueryList) {
            PaymentTourHotelSummary paymentTourHotelSummary = new PaymentTourHotelSummary();
            paymentTourHotelSummary.setNo(String.valueOf(no));
            paymentTourHotelSummary.setPayno(util.ConvertString(B[0]));
            paymentTourHotelSummary.setPaydate(!"".equalsIgnoreCase(util.ConvertString(B[1])) ? String.valueOf(df.format(util.convertStringToDate(util.ConvertString(B[1])))) : "");
            paymentTourHotelSummary.setPvtype(util.ConvertString(B[2]));
            paymentTourHotelSummary.setSupplier(util.ConvertString(B[3]));
            paymentTourHotelSummary.setRefno(util.ConvertString(B[4]));                           
            paymentTourHotelSummary.setInvno(util.ConvertString(B[5]));
            paymentTourHotelSummary.setAmount(B[6] != null ? util.ConvertString(B[6]) : "0");
            paymentTourHotelSummary.setCur(util.ConvertString(B[7]));
            paymentTourHotelSummary.setStatus(util.ConvertString(B[8]));
            paymentTourHotelSummary.setDescription(util.ConvertString(B[9]));
            paymentTourHotelSummary.setCom(util.ConvertString(B[10]) != null ? util.ConvertString(B[10]) : "0");
            paymentTourHotelSummary.setSystemdate(String.valueOf(dateformat.format(new Date())));
            paymentTourHotelSummary.setPaydatefrom(!"".equalsIgnoreCase(from) ? String.valueOf(df.format(util.convertStringToDate(from))) : "");
            paymentTourHotelSummary.setPaydateto(!"".equalsIgnoreCase(to) ? String.valueOf(df.format(util.convertStringToDate(to))) : "");          
            paymentTourHotelSummary.setUser(printBy);
            paymentTourHotelSummary.setPvtypeheader(pvtypeshow);
            
            data.add(paymentTourHotelSummary);
            no++;
        }
        
        return data;
    }

    @Override
    public String checkDayTourOperationDetail(String tourId, String tourDate) {
        String result = "fail";
        Session session = this.sessionFactory.openSession();
        List<DaytourBooking> list = new LinkedList<DaytourBooking>();
        String querysql = "SELECT * FROM daytour_booking DB Where DB.tour_id = :tourId and DB.tour_date = :tourDate";
        Query query = session.createSQLQuery(querysql);
        query.setParameter("tourId", tourId);
        query.setParameter("tourDate", tourDate);
        list = query.list();
        if (!list.isEmpty()) {
            result = "success";
        }else{
            result = "fail";
        }    
        session.close();
        this.sessionFactory.close();
        return result;
    }

    @Override
    public List<Daytour> searchListTourCode(String name) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        String sql ="SELECT * FROM `daytour` where name like '%"+name+"%' or code like '"+name+"%' and status = 'active' limit 200";
        List<Object[]> QueryList =  session.createSQLQuery(sql)
                .addScalar("id",Hibernate.STRING)
                .addScalar("code",Hibernate.STRING)
                .addScalar("name",Hibernate.STRING)
                .list();
        
        List<Daytour> daytourList =  new LinkedList<Daytour>();
        for(Object[] B : QueryList){
            Daytour daytour = new Daytour();
            daytour.setId(B[0].toString());
            daytour.setCode(B[1].toString());
            daytour.setName(B[2].toString());
            daytourList.add(daytour);
        }       
        if (daytourList.isEmpty()) {
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return daytourList;
    }

    @Override
    public MPaytype getMPayTypeFromPayTypeId(String payTypeId) {
        String query = "from MPaytype mp where mp.id =  :payTypeId";
        Session session = this.sessionFactory.openSession();
        List<MPaytype> list = session.createQuery(query).setParameter("payTypeId", payTypeId).list();
        if (list.isEmpty()) {
            return null;
        }
        session.close();
        return list.get(0);
    }

    @Override
    public PaymentStock getPaymentStockFromPayStockNo(String payStockNo) {
        String query = "from PaymentStock p where p.payStockNo =  :payStockNo";
        Session session = this.sessionFactory.openSession();
        List<PaymentStock> paymentStockList = session.createQuery(query).setParameter("payStockNo", payStockNo).list();
        if (paymentStockList.isEmpty()) {
            return null;
        }
        return paymentStockList.get(0);   
    }

    @Override
    public List<InvoiceSupplier> getListInvoiceSuppilerBySQLQuery(String name) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        
        String query = " SELECT * FROM `invoice_supplier` s ";
        boolean hasCondition = false;
        if(name != null && !"".equalsIgnoreCase(name)){
            query += (hasCondition ? " AND " : " WHERE ");
            query += " (s.code LIKE '%" + name + "%' OR s.name LIKE '%" + name + "%')";
        }
//        String query = "SELECT `h`.`id` AS `id`, `h`.`code` AS `code`, `h`.`name` AS `name`, `h`.`code` AS `apcode`, '' AS `taxno`, '' AS `branchno`, "
//                + "'Hotel' AS `type` "
//                + "FROM `hotel` `h` ";               
//        if(name != null && !"".equalsIgnoreCase(name)){
//            query += "WHERE (`h`.`ap_code` IS NOT NULL) AND ( `h`.`code` LIKE '%" + name + "%' OR `h`.`name` LIKE '%" + name + "%' ) ";
//        }
//                
//        query += "UNION ALL "
//                + "SELECT `a`.`id` AS `id`, `a`.`code` AS `code`, `a`.`name` AS `name`, `a`.`code` AS `apcode`, `a`.`tax_no` AS `taxno`, "
//                + "`a`.`branch` AS `branchno`, 'Agent' AS `type` "
//                + "FROM `agent` `a` ";                
//        if(name != null && !"".equalsIgnoreCase(name)){
//            query += "WHERE (`a`.`ap_code` IS NOT NULL) AND ( `a`.`code` LIKE '%" + name + "%' OR `a`.`name` LIKE '%" + name + "%' ) ";
//        }
//        
//        query += "UNION ALL "
//                + "SELECT `s`.`id` AS `id`, `s`.`username` AS `code`, `s`.`name` AS `name`, `s`.`ap_code` AS `apcode`, '' AS `taxno`, '' AS `branchno`, "
//                + "'Staff' AS `type` "
//                + "FROM `staff` `s` where `s`.`status` = 'active' ";
//        if(name != null && !"".equalsIgnoreCase(name)){
//            query += " and (`s`.`ap_code` IS NOT NULL) AND ( `s`.`username` LIKE '%" + name + "%' OR `s`.`name` LIKE '%" + name + "%' ) ";
//        }        
//                
//        query += "UNION ALL "
//                + "SELECT `sup`.`id` AS `id`, `sup`.`ap_code` AS `code`, `sup`.`name` AS `name`, `sup`.`ap_code` AS `apcode`, '' AS `taxno`, "
//                + "'' AS `branchno`, 'Supplier' AS `type` "
//                + "FROM `supplier` `sup` ";
//        if(name != null && !"".equalsIgnoreCase(name)){
//            query += "WHERE ( `sup`.`ap_code` LIKE '%" + name + "%' OR `sup`.`name` LIKE '%" + name + "%' ) ";
//        }             
        
        List<Object[]> invoiceSupplierList = session.createSQLQuery(query)
                .setMaxResults(300)
                .list();
        
        List<InvoiceSupplier> result = new ArrayList<InvoiceSupplier>();
        for (Object[] A : invoiceSupplierList) {
            InvoiceSupplier invoiceSupplier = new InvoiceSupplier();
            invoiceSupplier.setId(util.ConvertString(A[0]));
            invoiceSupplier.setCode(util.ConvertString(A[1]));
            invoiceSupplier.setName(util.ConvertString(A[2]));
            invoiceSupplier.setApcode(util.ConvertString(A[3]));
            result.add(invoiceSupplier);
        }       
        return result;
    }
    
}
