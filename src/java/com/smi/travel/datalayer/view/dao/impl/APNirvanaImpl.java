/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.view.dao.APNirvanaDao;
import com.smi.travel.datalayer.view.entity.APNirvana;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<APNirvana> SearchApNirvanaFromFilter(String paymentType, String productType, String status, String from, String to) {
       Session session = this.getSessionFactory().openSession();
       StringBuffer query = new StringBuffer(" SELECT * FROM `ap_nirvana` ");
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
            query.append(" `ap_nirvana`.create_date >= '" + from + "'");
            haveCondition = true;
        }
        if ((to != null) && (!"".equalsIgnoreCase(to))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" `ap_nirvana`.create_date <= '" + to + "'");
            haveCondition = true;
        }
        
        List<APNirvana> apNirvanaList =  session.createSQLQuery(query.toString()).list();
        if(apNirvanaList.isEmpty()){
            return apNirvanaList;
        }
        List<APNirvana> mappingData = mappingAPNirvana(apNirvanaList);
        this.sessionFactory.close();
        session.close();
        return mappingData;
        
    }
    
    private List<APNirvana> mappingAPNirvana(List<APNirvana> apNirvanaList) {
        List<APNirvana> mappingData = new ArrayList<APNirvana>();
        for(int i=0;i<apNirvanaList.size();i++){
            APNirvana data = new APNirvana();
            data = apNirvanaList.get(i);
            APNirvana apNirvana = new APNirvana();
            apNirvana.setIntreference(data.getIntreference());
            apNirvana.setVendorid(data.getVatid());
            apNirvana.setVendorname(data.getVendorname());
            apNirvana.setPuraccount1(data.getPuraccount1());
            apNirvana.setBasevatamt(data.getBasevatamt() == null ? data.getBasevatamt() : new BigDecimal("0.00"));
            apNirvana.setCurrencyid(data.getCurrencyid());
            mappingData.add(apNirvana);
        }
        
        return mappingData;
    }

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
