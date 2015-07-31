/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.InvoiceDao;
import com.smi.travel.datalayer.entity.Invoice;
import com.smi.travel.datalayer.entity.InvoiceDetail;
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
public class InvoiceImpl implements InvoiceDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;
    private static final String DELETEALL_INVOICE_QUERY ="DELETE FROM Invoice in where in.id = :invouceID";
    private static final String SELECT_INVOICE_DETAIL = "FROM InvoiceDetail ind where ind.invoice.id = :invoiceID";
    private static final String DELETE_INVOICEDETAIL_QUERY ="DELETE FROM InvoiceDetail ind where ind.id = :invoiceDetailID";
    
    @Override
    public String insertInvoice(Invoice invoice) {
        String result = "";
        Session session = this.sessionFactory.openSession();
        try { 
            transaction = session.beginTransaction();
            session.save(invoice);
            List<InvoiceDetail> invoiceDetail = invoice.getInvoiceDetails();
            if(invoiceDetail != null){
                for (int i = 0; i < invoiceDetail.size(); i++) {
                    session.save(invoiceDetail.get(i));
                }
            }
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = "success";
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
    public String updateInvoice(Invoice invoice) {
        String result = "";
        Session session = this.sessionFactory.openSession();
        try { 
            transaction = session.beginTransaction();
            session.save(invoice);
            List<InvoiceDetail> invoiceDetail = invoice.getInvoiceDetails();
            if(invoiceDetail != null){
                for (int i = 0; i < invoiceDetail.size(); i++) {
                    session.save(invoiceDetail.get(i));
                }
            }
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = "update success";
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            session.close();
            this.sessionFactory.close();
            result = "update fail";
        }
        return result;
    }

    @Override
    public String deleteInvoice(Invoice invoice) {
        List<InvoiceDetail> invoiceDetailList = new LinkedList<InvoiceDetail>();
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            invoiceDetailList = checkInvoiceDetail(invoice.getId());
            if(invoiceDetailList == null){
                Query query = session.createQuery(DELETEALL_INVOICE_QUERY);
                query.setParameter("invoiceID", invoice.getId());
                System.out.println("row delete : "+query.executeUpdate());
                transaction.commit();
                session.close();
                this.sessionFactory.close();
                result = "success";
            }else{
                result = "fail";
            }          
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            result = "fail";
        }
        return result;
    }
    
    public List<InvoiceDetail> checkInvoiceDetail(String invoiceId) {
        Session session = this.sessionFactory.openSession();
        List<InvoiceDetail> invoiceDetailList = session.createQuery(SELECT_INVOICE_DETAIL)
                .setParameter("invoiceID", invoiceId)
                .list();
        if (invoiceDetailList.isEmpty()) {
            return null;
        }
        for(int i=0;i<invoiceDetailList.size();i++){
           System.out.println("invoiceDetailList : "+invoiceDetailList.get(i).getId());
        }
        
       
        return invoiceDetailList;
    }

    @Override
    public String UpdateInvoiceStatus(int StatusId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String DeleteInvoiceDetail(String InvoiceDetailId) {
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(DELETE_INVOICEDETAIL_QUERY);
            query.setParameter("invoiceDetailID", InvoiceDetailId);
            System.out.println("row delete : "+query.executeUpdate());
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = "success";
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            result = "fail";
        }
        return result;
    }
    
    private String GenerateInvoiceCode(){
        String code = "";
        
        return code;
    }

    @Override
    public Invoice getInvoiceFromInvoiceNumber(String InvoiceNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    @Override
    public String getInvoiceNumber(Invoice invoice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
