/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. test
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.InvoiceDao;
import com.smi.travel.datalayer.entity.Invoice;
import com.smi.travel.datalayer.entity.InvoiceDetail;
import com.smi.travel.datalayer.view.entity.InvoiceView;
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
    private static final String GET_INVOICE = "FROM Invoice inv where inv.invNo = :invoiceNo";
    private static final String SELECT_INVOICE_DETAIL = "FROM InvoiceDetail ind where ind.invoice.id = :invoiceID";
    private static final String DELETE_INVOICEDETAIL_QUERY ="DELETE FROM InvoiceDetail ind where ind.id = :invoiceDetailID";
    private static final String SEARCH_INVOICE_TYPE = "FROM Invoice inv where inv.deparement = :invoiceDepartment and inv.invType = :invoiceType ORDER BY inv.invNo DESC LIMIT 1";
    
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
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(invoice);
            
            List<InvoiceDetail> invoiceDetail = invoice.getInvoiceDetails();
            for (int i = 0; i < invoiceDetail.size(); i++) {
                if (invoiceDetail.get(i).getId() == null) {
                    session.save(invoiceDetail.get(i));
                } else {
                    session.update(invoiceDetail.get(i));
                }
            }

            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = "update success";
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            result = "update fail";
        }
        return  result;
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
        Session session = this.sessionFactory.openSession();
        Invoice invoice = new Invoice();
        List<Invoice> invoiceList = session.createQuery(GET_INVOICE)
                .setParameter("invoiceNo", InvoiceNumber)
                .list();
        if(!invoiceList.isEmpty()){
            invoice.setId(invoiceList.get(0).getId());
            invoice.setInvNo(invoiceList.get(0).getInvNo());
            invoice.setInvTo(invoiceList.get(0).getInvTo());
            invoice.setInvName(invoiceList.get(0).getInvName());
            invoice.setInvType(invoiceList.get(0).getInvType());
            invoice.setInvAddress(invoiceList.get(0).getInvAddress());
            invoice.setInvoiceDetails(invoiceList.get(0).getInvoiceDetails());
            invoice.setArcode(invoiceList.get(0).getArcode());
            invoice.setCreateBy(invoiceList.get(0).getCreateBy());
            invoice.setCreateDate(invoiceList.get(0).getCreateDate());
            invoice.setDeparement(invoiceList.get(0).getDeparement());
            invoice.setDueDate(invoiceList.get(0).getDueDate());
            invoice.setIsGroup(invoiceList.get(0).getIsGroup());
            invoice.setIsLock(invoiceList.get(0).getIsLock());
            invoice.setMAccpay(invoiceList.get(0).getMAccpay());
            invoice.setMFinanceItemstatus(invoiceList.get(0).getMFinanceItemstatus());
            invoice.setRemark(invoiceList.get(0).getRemark());
            invoice.setStaff(invoiceList.get(0).getStaff());
            invoice.setSubDepartment(invoiceList.get(0).getSubDepartment());
        }
        
        return invoice;
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
    public String searchInvoiceNo(String department,String invoiceType) {
        String invoiceNoLast = "";
        Session session = this.sessionFactory.openSession();
        List<Invoice> invoiceList = session.createQuery(SEARCH_INVOICE_TYPE)
                .setParameter("invoiceDepartment", department)
                .setParameter("invoiceType", invoiceType)
                .list();
        if (invoiceList.isEmpty()) {
            return null;
        }
        for(int i=0;i<invoiceList.size();i++){
           invoiceNoLast = invoiceList.get(0).getInvNo();
        }    
        return invoiceNoLast;
    }

    @Override
    public InvoiceView SearchInvoiceView(String DateFrom, String DateTo, String Department, String Type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
