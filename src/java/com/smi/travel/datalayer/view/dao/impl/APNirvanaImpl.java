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
import java.util.List;
import org.hibernate.Hibernate;
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
        String result = "";
//        try {
//            Session session = this.getSessionFactory().openSession();
//            setTransaction(session.beginTransaction());
//            
//            for (int i = 0; i < APList.size(); i++) {
//                APNirvana apNirvana = APList.get(i);
//                if(true){
//                    
//                } else {
//                    
//                }
//                session.update(APList.get(i));
//            }
//
//            getTransaction().commit();
//            session.close();
//            this.getSessionFactory().close();
//            result = "success";
//        } catch (Exception ex) {
//            getTransaction().rollback();
//            ex.printStackTrace();
//            result = "fail";
//        }
        return  result;
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
            .list();
        
        for (Object[] B : QueryList) {
            APNirvana apNirvana = new APNirvana();
            apNirvana.setIntreference(util.ConvertString(B[1]));
            apNirvana.setCurrencyid(util.ConvertString(B[9]));
            apNirvana.setVendorid(util.ConvertString(B[2]));
            apNirvana.setVendorname(util.ConvertString(B[3]));
            apNirvana.setPuraccount1(util.ConvertString(B[29]));
            apNirvana.setBasevatamt((B[12])!=null ? new BigDecimal(util.ConvertString(B[12])) : new BigDecimal("0.00"));
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
