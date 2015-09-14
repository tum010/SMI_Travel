/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.view.dao.APNirvanaDao;
import com.smi.travel.datalayer.view.entity.APNirvana;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Surachai
 */
public class APNirvanaImpl implements APNirvanaDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;

    @Override
    public String ExportAPFileInterface(List<APNirvana> APList) {
        
        return "success";
    }

    @Override
    public String UpdateStatusAPInterface(List<APNirvana> APList) {
        int result = 0;
        try {
            Session session = this.getSessionFactory().openSession();
            setTransaction(session.beginTransaction());
            
            for (int i = 0; i < APList.size(); i++) {
                APNirvana apNirvana = APList.get(i);
                String paymentId = apNirvana.getPayment_id();
                String paymentType = apNirvana.getPaymenttype();
                Date date = new Date();
                if("W".equalsIgnoreCase(paymentType)){
                    String hql = "update PaymentWendy pay set pay.isExport = 1 , pay.exportDate = :date where pay.id = :paymentId";
                    try {
                        Query query = session.createQuery(hql);
                        query.setParameter("paymentId", paymentId);
                        query.setParameter("date", date);
                        System.out.println(" query " + query);
                        result = query.executeUpdate();
                        System.out.println("Rows affected: " + result);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        result = 0;
                    }                                   
                } else if("A".equalsIgnoreCase(paymentType)){
                    String hql = "update PaymentAirticket air set air.isExport = 1 , air.exportDate = :date where air.id = :paymentId";
                    try {
                        Query query = session.createQuery(hql);
                        query.setParameter("paymentId", paymentId);
                        query.setParameter("date", date);
                        System.out.println(" query " + query);
                        result = query.executeUpdate();
                        System.out.println("Rows affected: " + result);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        result = 0;
                    }
                }
            }                    
            getTransaction().commit();
            session.close();
            this.getSessionFactory().close();
            result = 1;
        } catch (Exception ex) {
            getTransaction().rollback();
            ex.printStackTrace();
            result = 0;
        }
        return  String.valueOf(result);
    }

    @Override
    public List<APNirvana> SearchApNirvanaFromFilter(String paymentType, String productType, String status, String from, String to) {
       UtilityFunction util = new UtilityFunction();
       List<APNirvana> apNirvanaList = new ArrayList<APNirvana>();
       Session session = this.getSessionFactory().openSession();
       StringBuffer query = new StringBuffer(" SELECT * FROM `ar_nirvana` ");
        boolean haveCondition = false;
        if ((paymentType != null) && (!"".equalsIgnoreCase(paymentType))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" `ar_nirvana`.paymenttype = '" + paymentType + "'");
            haveCondition = true;
        }
        if ((productType != null) && (!"".equalsIgnoreCase(productType))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" `ar_nirvana`.producttype = '" + productType + "'");
            haveCondition = true;
        }
        if ((status != null) && (!"".equalsIgnoreCase(status))) {
            if("N".equalsIgnoreCase(status)){status = "New";}
            if("E".equalsIgnoreCase(status)){status = "Export";}
            if("C".equalsIgnoreCase(status)){status = "Change";}
            query.append(haveCondition ? " and" : " where");
            query.append(" `ar_nirvana`.itf_status = '" + status + "'");
            haveCondition = true;
        }
        if ((from != null) && (!"".equalsIgnoreCase(from))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" `ar_nirvana`.create_date >= '" + from + "'");
            haveCondition = true;
        }
        if ((to != null) && (!"".equalsIgnoreCase(to))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" `ar_nirvana`.create_date <= '" + to + "'");
            haveCondition = true;
        }
        
        List<Object[]> QueryList =  session.createSQLQuery(query.toString())      
            .addScalar("refinvoiceno",Hibernate.STRING)
            .addScalar("intreference",Hibernate.STRING)
            .addScalar("vendorid",Hibernate.STRING)
            .addScalar("vendorname",Hibernate.STRING)
            .addScalar("divisionid",Hibernate.STRING)
            .addScalar("projectid",Hibernate.STRING)
            .addScalar("transcode",Hibernate.STRING)
//            .addScalar("transdate",Hibernate.DATE)
            .addScalar("duedate",Hibernate.DATE)
            .addScalar("currencyid",Hibernate.STRING)
            .addScalar("homerate",Hibernate.BIG_DECIMAL)
            .addScalar("foreignrate",Hibernate.BIG_DECIMAL)
            .addScalar("basevatamt",Hibernate.BIG_DECIMAL)
            .addScalar("basevathmamt",Hibernate.BIG_DECIMAL)
            .addScalar("vatamt",Hibernate.BIG_DECIMAL)
            .addScalar("vathmamt",Hibernate.BIG_DECIMAL)
            .addScalar("transamt",Hibernate.BIG_DECIMAL)
            .addScalar("transhmamt",Hibernate.BIG_DECIMAL)
            .addScalar("vatflag",Hibernate.STRING)
            .addScalar("vatid",Hibernate.STRING)
            .addScalar("whtflag",Hibernate.STRING)
            .addScalar("whtid",Hibernate.STRING)
            .addScalar("basewhtamt",Hibernate.BIG_DECIMAL)
            .addScalar("basewhthmamt",Hibernate.BIG_DECIMAL)
            .addScalar("whtamt",Hibernate.BIG_DECIMAL)
            .addScalar("whthmamt",Hibernate.BIG_DECIMAL)
            .addScalar("year",Hibernate.INTEGER)
            .addScalar("period",Hibernate.INTEGER)
            .addScalar("note",Hibernate.STRING)
            .addScalar("puraccount1",Hibernate.STRING)
            .addScalar("purdivision1",Hibernate.STRING)
            .addScalar("purproject1",Hibernate.STRING)
            .addScalar("puramt1",Hibernate.BIG_DECIMAL)
            .addScalar("purhmamt1",Hibernate.BIG_DECIMAL)
            .addScalar("puraccount2",Hibernate.STRING)
            .addScalar("purdivision2",Hibernate.STRING)
            .addScalar("purproject2",Hibernate.STRING)
            .addScalar("puramt2",Hibernate.BIG_DECIMAL)
            .addScalar("purhmamt2",Hibernate.BIG_DECIMAL)
            .addScalar("puraccount3",Hibernate.STRING)
            .addScalar("purdivision3",Hibernate.STRING)       
            .addScalar("purproject3",Hibernate.STRING)
            .addScalar("puramt3",Hibernate.BIG_DECIMAL)
            .addScalar("purhmamt3",Hibernate.BIG_DECIMAL)
            .addScalar("puraccount4",Hibernate.STRING)
            .addScalar("purdivision4",Hibernate.STRING)       
            .addScalar("purproject4",Hibernate.STRING)
            .addScalar("puramt4",Hibernate.BIG_DECIMAL)
            .addScalar("purhmamt4",Hibernate.BIG_DECIMAL)
            .addScalar("puraccount5",Hibernate.STRING)
            .addScalar("purdivision5",Hibernate.STRING)       
            .addScalar("purproject5",Hibernate.STRING)
            .addScalar("puramt5",Hibernate.BIG_DECIMAL)
            .addScalar("purhmamt5",Hibernate.BIG_DECIMAL)
            .addScalar("puraccount6",Hibernate.STRING)
            .addScalar("purdivision6",Hibernate.STRING)       
            .addScalar("purproject6",Hibernate.STRING)
            .addScalar("puramt6",Hibernate.BIG_DECIMAL)
            .addScalar("purhmamt6",Hibernate.BIG_DECIMAL)
            .addScalar("puraccount7",Hibernate.STRING)
            .addScalar("purdivision7",Hibernate.STRING)       
            .addScalar("purproject7",Hibernate.STRING)
            .addScalar("puramt7",Hibernate.BIG_DECIMAL)
            .addScalar("purhmamt7",Hibernate.BIG_DECIMAL)
            .addScalar("puraccount8",Hibernate.STRING)
            .addScalar("purdivision8",Hibernate.STRING)       
            .addScalar("purproject8",Hibernate.STRING)
            .addScalar("puramt8",Hibernate.BIG_DECIMAL)
            .addScalar("purhmamt8",Hibernate.BIG_DECIMAL)
            .addScalar("puraccount9",Hibernate.STRING)
            .addScalar("purdivision9",Hibernate.STRING)       
            .addScalar("purproject9",Hibernate.STRING)
            .addScalar("puramt9",Hibernate.BIG_DECIMAL)
            .addScalar("purhmamt9",Hibernate.BIG_DECIMAL)
            .addScalar("puraccount10",Hibernate.STRING)
            .addScalar("purdivision10",Hibernate.STRING)       
            .addScalar("purproject10",Hibernate.STRING)
            .addScalar("puramt10",Hibernate.BIG_DECIMAL)
            .addScalar("purhmamt10",Hibernate.BIG_DECIMAL)
            .addScalar("service",Hibernate.STRING)
            .addScalar("apaccount",Hibernate.STRING)
            .addScalar("prefix",Hibernate.STRING)
            .addScalar("voucherno",Hibernate.INTEGER)
            .addScalar("taxid",Hibernate.STRING)
            .addScalar("vendor_branch",Hibernate.INTEGER)
            .addScalar("company_branch",Hibernate.INTEGER)
            .addScalar("itf_status",Hibernate.STRING)
            .addScalar("payment_id",Hibernate.STRING)
            .addScalar("paymenttype",Hibernate.STRING)    //87
            .list();
        
        for (Object[] B : QueryList) {
            APNirvana apNirvana = new APNirvana();
            apNirvana.setIntreference(util.ConvertString(B[1]));
            apNirvana.setVendorid(util.ConvertString(B[2]));
            apNirvana.setVendorname(util.ConvertString(B[3]));
            apNirvana.setCurrencyid(util.ConvertString(B[8]));
            apNirvana.setBasevatamt((B[11])!=null ? new BigDecimal(util.ConvertString(B[11])) : new BigDecimal("0.00"));
            apNirvana.setVatamt((B[13])!=null ? new BigDecimal(util.ConvertString(B[13])) : new BigDecimal("0.00"));            
            apNirvana.setPuraccount1(util.ConvertString(B[28]));
            apNirvana.setItf_status(util.ConvertString(B[85]));
            apNirvana.setPayment_id(util.ConvertString(B[86]));
            apNirvana.setPaymenttype(util.ConvertString(B[87]));
            apNirvanaList.add(apNirvana);
        }    
        
        this.sessionFactory.close();
        session.close();
        return apNirvanaList;
        
    }
    
//    private List<APNirvana> mappingAPNirvana(List<APNirvana> apNirvanaList) {
//        List<APNirvana> mappingData = new ArrayList<APNirvana>();
//        for(int i=0;i<apNirvanaList.size();i++){
//            APNirvana data = new APNirvana();
//            data = apNirvanaList.get(i);
//            APNirvana apNirvana = new APNirvana();
//            apNirvana.setIntreference(data.getIntreference());
//            apNirvana.setVendorid(data.getVatid());
//            apNirvana.setVendorname(data.getVendorname());
//            apNirvana.setPuraccount1(data.getPuraccount1());
//            apNirvana.setBasevatamt(data.getBasevatamt() != null ? data.getBasevatamt() : new BigDecimal("0.00"));
//            apNirvana.setCurrencyid(data.getCurrencyid());
//            mappingData.add(apNirvana);
//        }
//        
//        return mappingData;
//    }

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
   
}
