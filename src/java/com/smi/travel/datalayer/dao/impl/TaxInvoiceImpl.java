/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.TaxInvoiceDao;
import com.smi.travel.datalayer.entity.TaxInvoice;
import com.smi.travel.datalayer.entity.TaxInvoiceDetail;
import com.smi.travel.datalayer.view.entity.TaxInvoiceView;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Surachai
 */
public class TaxInvoiceImpl implements TaxInvoiceDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;
    private UtilityFunction utilityFunction;
    private static final String GET_TAXINVOICE = "FROM TaxInvoice t where t.taxNo = :TaxInvNo and t.department = :Page";

    @Override
    public String insertTaxInvoice(TaxInvoice tax) {
        String result = "";
        Session session = this.getSessionFactory().openSession();
        try { 
            setTransaction(session.beginTransaction());
            String taxNo = gennarateTaxInvoiceNo();
            tax.setTaxNo(taxNo);
            session.save(tax);
            List<TaxInvoiceDetail> taxInvoiceDetail = tax.getTaxInvoiceDetails();
            if(taxInvoiceDetail != null){
                for (int i = 0; i < taxInvoiceDetail.size(); i++) {
                    session.save(taxInvoiceDetail.get(i));
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
    
    private String gennarateTaxInvoiceNo(){
        String taxNo = "";
        Session session = this.sessionFactory.openSession();
        List<TaxInvoice> list = new LinkedList<TaxInvoice>();
        Date thisdate = new Date();
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("MMyy");
        Query query = session.createQuery("from TaxInvoice t where t.taxNo Like :taxNo Order by t.taxNo desc");
        query.setParameter("taxNo", "%"+ df.format(thisdate) + "%");
        query.setMaxResults(1);
        list = query.list();
        if (list.isEmpty()) {
            taxNo = df.format(thisdate) + "-" + "0001";
        } else {
            taxNo = String.valueOf(list.get(0).getTaxNo());
            if (!taxNo.equalsIgnoreCase("")) {
                System.out.println("taxNo" + taxNo.substring(4,8) + "/////");
                int running = Integer.parseInt(taxNo.substring(4,8)) + 1;
                String temp = String.valueOf(running);
                for (int i = temp.length(); i < 4; i++) {
                    temp = "0" + temp;
                }
                taxNo = df.format(thisdate) + "-" + temp;
            }
        }
        session.close();
        this.sessionFactory.close();
        return taxNo.replace("-","");
    }

    @Override
    public String updateTaxInvoice(TaxInvoice tax) {
        String result = "";
        try {
            Session session = this.getSessionFactory().openSession();
            setTransaction(session.beginTransaction());
            session.update(tax);
            
            List<TaxInvoiceDetail> taxInvoiceDetail = tax.getTaxInvoiceDetails();
            for (int i = 0; i < taxInvoiceDetail.size(); i++) {
                if (taxInvoiceDetail.get(i).getId() == null) {
                    session.save(taxInvoiceDetail.get(i));
                } else {
                    session.update(taxInvoiceDetail.get(i));
                }
            }

            getTransaction().commit();
            session.close();
            this.getSessionFactory().close();
            result = "success";
        } catch (Exception ex) {
            getTransaction().rollback();
            ex.printStackTrace();
            result = "fail";
        }
        return  result;
    }

    @Override
    public String deleteTaxInvoice(TaxInvoice tax) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TaxInvoice getTaxInvoiceFromTaxInvNo(String TaxInvNo, String Page) {
        Session session = this.sessionFactory.openSession();
        TaxInvoice taxInvoice = new TaxInvoice();
        List<TaxInvoice> taxInvoiceList = session.createQuery(GET_TAXINVOICE).setParameter("TaxInvNo", TaxInvNo).setParameter("Page", Page).list();
        if(taxInvoiceList.isEmpty()){
            return null;
        } 
        
        taxInvoice = taxInvoiceList.get(0);
               
        return taxInvoice;
    }

    @Override
    public String DeleteTaxInvoiceInvoiceDetail(TaxInvoiceDetail taxInvoiceDetail) {
        String result = "fail";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(taxInvoiceDetail);
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
    public String UpdateFinanceStatusTaxInvoice(String TaxId, int status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TaxInvoiceView SearchTaxInvoiceFromFilter(String from, String To, String Department) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public TaxInvoiceView mappingTaxInvoice(TaxInvoice tax){
        TaxInvoiceView taxview = new TaxInvoiceView();
        
        return taxview;
    }

    @Override
    public TaxInvoiceView getTaxInvoiceViewFromTaxNo(String TaxNo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
