/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. test
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.InvoiceDao;
import com.smi.travel.datalayer.entity.BillableDesc;
import com.smi.travel.datalayer.entity.Invoice;
import com.smi.travel.datalayer.entity.InvoiceDetail;
import com.smi.travel.datalayer.entity.ReceiptDetail;
import com.smi.travel.datalayer.entity.TaxInvoiceDetail;
import com.smi.travel.datalayer.view.entity.InvoiceView;
import com.smi.travel.model.NonBillableView;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
public class InvoiceImpl implements InvoiceDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;
    private static final String DELETEALL_INVOICE_QUERY ="DELETE FROM Invoice in where in.id = :invouceID";
    private static final String GET_INVOICE = "FROM Invoice inv where inv.invNo = :invoiceNo";
    private static final String GET_INVOICE_FROMID = "FROM Invoice inv where inv.id = :invoiceId";
    private static final String SEARCH_INVOICE = "FROM Invoice inv where inv.id = :invoiceId and inv.invType = :invoiceType and inv.department = :invoiceDepartment ";
    private static final String SELECT_INVOICE_DETAIL = "FROM InvoiceDetail ind where ind.invoice.id = :invoiceID";
    private static final String DELETE_INVOICEDETAIL_QUERY ="DELETE FROM InvoiceDetail ind where ind.id = :invoiceDetailID";
    private static final String SEARCH_INVOICE_TYPE = "FROM Invoice inv where inv.department = :invoiceDepartment and inv.invType = :invoiceType  and inv.invNo  LIKE :invoiceNo  ORDER BY inv.invNo DESC LIMIT 1";
    private static final String GET_INVOICE_FROMNO = "FROM Invoice inv where inv.invNo = :invoiceNo and inv.department = :department and inv.invType = :invType and inv.MFinanceItemstatus = '1'";
    private static final String GET_INVOICE_FOR_TAX_INVOICE = "FROM Invoice inv where inv.invNo = :invoiceNo and inv.department = :department and inv.invType = 'V' and inv.MFinanceItemstatus.name = 'NORMAL'";
    private static final String GET_BILLDESC = "from InvoiceDetail inv WHERE inv.billableDesc.id = :billableDescId  and inv.invoice.MFinanceItemstatus.id != 2";
    private static final String GET_BILLDESC_FILTER = "from InvoiceDetail inv WHERE inv.billableDesc.id = :billableDescId and inv.id != :invdID";
    private static final String GET_BILL_AMOUNT = "from BillableDesc bill where bill.id = :descid";
    private static final String GET_BILLDESC_FLAG = "From  BillableDesc  billd  where billd.id = :billddesid";
    private static final String UPDATE_FILG17 = "UPDATE Master mas set  mas.flagAir = 1  WHERE id = :masterid";
    private static final String UPDATE_FILG28 = "UPDATE Master mas set  mas.flagOther = 1  WHERE id = :masterid";
    private static final String UPDATE_FILG3 = "UPDATE Master mas set  mas.flagLand = 1  WHERE id = :masterid";
    private static final String UPDATE_FILG4 = "UPDATE Master mas set  mas.flagHotel = 1  WHERE id = :masterid";
    private static final String UPDATE_FILG6 = "UPDATE Master mas set  mas.flagDaytour = 1  WHERE id = :masterid";
    private static final String UPDATE_BOOKING_STATUS = "UPDATE Master mas set  mas.Mbookingstatus.id = 5  WHERE id = :masterbillid";
    
    
    @Override
    public synchronized String insertInvoice(Invoice invoice) {
        String result = "";
        String invNo = "";
        Session session = this.sessionFactory.openSession();
        try { 
            transaction = session.beginTransaction();
            invNo = generateInvoiceNo(invoice.getDepartment() , invoice.getInvType() , invoice.getInvDate());
//            result = invoice.getInvNo();
            invoice.setInvNo(invNo);
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
            System.out.println("ss result : "+ invoice.getInvNo());
            result = invoice.getInvNo();
//            result = "success";
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            session.close();
            this.sessionFactory.close();
            result = "fail";
        }
        return result;
    }

    private String generateInvoiceNo(String department, String invoiceType, Date invoiceDate){
        String invNo = "";
        String invType = "";
        Session session = this.sessionFactory.openSession();
        List<String> list = new LinkedList<String>();
        Date thisdate = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyMM",Locale.US);
        System.out.println("===== invoice date ===== : " + df.format(invoiceDate));
        Query query = session.createSQLQuery("SELECT RIGHT(inv_no, 4) as invnum  FROM invoice where department = :department and inv_type = :invoiceType and inv_no Like :invno  ORDER BY RIGHT(inv_no, 4) desc");
        query.setParameter("invno", "%"+ df.format(invoiceDate) + "%");
        query.setParameter("department", department);
        query.setParameter("invoiceType", invoiceType);
        query.setMaxResults(1);
        list = query.list();
        String departtype = "";
        if("Wendy".equals(department)){
            if("T".equals(invoiceType)){
                departtype = "W";
            }else if("V".equals(invoiceType)){
                departtype = "WV";
            }else if("N".equals(invoiceType)){
                departtype = "WN";
            }else if("A".equals(invoiceType)){
                departtype = "WT";
            }
        }else if("Inbound".equals(department)){
            if("T".equals(invoiceType)){
                departtype = "I";
            }else if("V".equals(invoiceType)){
                departtype = "IV";
            }else if("N".equals(invoiceType)){
                departtype = "IN";
            }
        }else if("Outbound".equals(department)){
            if("T".equals(invoiceType)){
                departtype = "O";    
            }else if("V".equals(invoiceType)){
                departtype = "OV";
            }else if("N".equals(invoiceType)){
                departtype = "ON";
            }else if("A".equals(invoiceType)){
                departtype = "OT";
            }
        }
        
        if (list.isEmpty()) {
            invNo = departtype + df.format(invoiceDate) + "-" + "0001";
        } else {
            invNo = list.get(0);
            System.out.println("invNo === " + invNo + " === ");
            if (!invNo.equalsIgnoreCase("")) {
                System.out.println("invNo type" + invNo.substring(1,2) + "/////");
                invType = invNo.substring(1,2);
                if(!"V".equals(invType) && !"N".equals(invType)){
                    int running = Integer.parseInt(invNo) + 1;
                    String temp = String.valueOf(running);
                    for (int i = temp.length(); i < 4; i++) {
                        temp = "0" + temp;
                    }
                    invNo = departtype + "-" + df.format(invoiceDate) + "-" + temp;
                }else{
                    int running = Integer.parseInt(invNo) + 1;
                    String temp = String.valueOf(running);
                    for (int i = temp.length(); i < 4; i++) {
                        temp = "0" + temp;
                    }
                    invNo = departtype + "-" + df.format(invoiceDate) + "-" + temp;
                }
            }
        }
        System.out.println("invNo ::: " +invNo);
        session.close();
        this.sessionFactory.close();
        return invNo.replace("-","");
    }
        
    @Override
    public synchronized String updateInvoice(Invoice invoice) {
        String result = "";
        Invoice mInvoice = getExportData(invoice);
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            invoice.setIsExport(mInvoice.getIsExport() != null ? mInvoice.getIsExport() : 0);
            invoice.setExportDate(mInvoice.getExportDate());
            invoice.setDataNo(mInvoice.getDataNo() != null && !"".equalsIgnoreCase(mInvoice.getDataNo()) ? mInvoice.getDataNo() : null);
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
    public synchronized String deleteInvoice(Invoice invoice) {
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
    public  synchronized String DeleteInvoiceDetail(String InvoiceDetailId) {
        String result = "";
        int checkReciptAndTaxInvoice = 0;
        boolean isTaxInvoice = false;
        boolean isReceipt = false;
        if("yesTaxinvoice".equals(checkTaxInvoiceDelete(InvoiceDetailId))){
            checkReciptAndTaxInvoice++;
            isTaxInvoice = true;
        }else{
            checkReciptAndTaxInvoice = (isTaxInvoice ? checkReciptAndTaxInvoice : 0);
        }
        // check Recipt
        if("yesReceipt".equals(checkReciptDelete(InvoiceDetailId))){
            checkReciptAndTaxInvoice++;
            isReceipt = true;
        }else{
            checkReciptAndTaxInvoice = (isTaxInvoice ? checkReciptAndTaxInvoice : 0);
        }
        if(checkReciptAndTaxInvoice == 0){
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
        }else{
            if(isTaxInvoice && isReceipt){
                result = "notDeleteReciptAndTax";
                
            } else if(isTaxInvoice){
                result = "notDeleteTax";
                
            } else if(isReceipt){
                result = "notDeleteRecipt";
                
            }           
        }
        return result;
    }
   
    @Override
    public Invoice getInvoiceFromInvoiceNumber(String InvoiceNumber, String department, String invType) {
        Session session = this.sessionFactory.openSession();
        Invoice invoice = new Invoice();
        String query = "";

        if( InvoiceNumber != null && (InvoiceNumber.indexOf("%")) >= 0){
            query = " FROM Invoice inv where inv.invNo LIKE '" + InvoiceNumber + "'";
            query += " AND inv.department = :department ";
            query += " AND inv.invType = :invType ORDER BY inv.id desc ";
            
            if("W".equalsIgnoreCase(department)){
                department = "Wendy";
            }else if("O".equalsIgnoreCase(department)){
                department = "Outbound";
            }else if("R".equalsIgnoreCase(department) || "P".equalsIgnoreCase(department)){
                department = "Inbound";
            }
            
            List<Invoice> invoiceList = session.createQuery(query.toString())
                .setParameter("department", department)
                .setParameter("invType", invType)
                .setMaxResults(1)
                .list();
            if(invoiceList.isEmpty()){
                return invoice;
            }        
            invoice = invoiceList.get(0);
            
        }else{
            List<Invoice> invoiceList = session.createQuery(GET_INVOICE)
                .setParameter("invoiceNo", InvoiceNumber)
                .list();
            if(!invoiceList.isEmpty()){
                invoice.setId(invoiceList.get(0).getId());
                invoice.setInvNo(invoiceList.get(0).getInvNo());
                invoice.setInvTo(invoiceList.get(0).getInvTo());
                invoice.setInvName(invoiceList.get(0).getInvName());
                invoice.setInvType(invoiceList.get(0).getInvType());
                invoice.setInvDate(invoiceList.get(0).getInvDate());
                invoice.setInvAddress(invoiceList.get(0).getInvAddress());
                invoice.setInvoiceDetails(invoiceList.get(0).getInvoiceDetails());
                invoice.setArcode(invoiceList.get(0).getArcode());
                invoice.setCreateBy(invoiceList.get(0).getCreateBy());
                invoice.setCreateDate(invoiceList.get(0).getCreateDate());
                invoice.setDepartment(invoiceList.get(0).getDepartment());
                invoice.setDueDate(invoiceList.get(0).getDueDate());
                invoice.setIsGroup(invoiceList.get(0).getIsGroup());
                invoice.setIsLock(invoiceList.get(0).getIsLock());
                invoice.setMAccTerm(invoiceList.get(0).getMAccTerm());
                invoice.setMFinanceItemstatus(invoiceList.get(0).getMFinanceItemstatus());
                invoice.setRemark(invoiceList.get(0).getRemark());
                invoice.setStaff(invoiceList.get(0).getStaff());
                invoice.setSubDepartment(invoiceList.get(0).getSubDepartment());
                invoice.setExportDate(invoiceList.get(0).getExportDate());
                invoice.setIsExport(invoiceList.get(0).getIsExport());
            }           
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
    public String searchInvoiceNum(String department,String invoiceType,String invoiceNo) {
        String invoiceNoLast = "";
        Session session = this.sessionFactory.openSession();
        System.out.println("department : "+department);
        System.out.println("invoiceType : "+invoiceType);
        System.out.println("invoiceNo : "+invoiceNo);
        List<Invoice> invoiceList = session.createQuery(SEARCH_INVOICE_TYPE)
                .setParameter("invoiceDepartment", department)
                .setParameter("invoiceType", invoiceType)
                .setParameter("invoiceNo", "%"+invoiceNo+"%")
                .list();
        if (invoiceList.isEmpty()) {
            invoiceNoLast = "";
        }
        for(int i=0;i<invoiceList.size();i++){
           invoiceNoLast = invoiceList.get(0).getInvNo();
        }    
        System.out.println(" Invoice Inbound Last : " + invoiceNoLast);
        return invoiceNoLast;
    }

//    @Override
    public Invoice getInvoiceFromId(String invoiceId) {
       Session session = this.sessionFactory.openSession();
        Invoice invoice = new Invoice();
        List<Invoice> invoiceList = session.createQuery(GET_INVOICE_FROMID)
                .setParameter("invoiceId", invoiceId)
                .list();
        if(!invoiceList.isEmpty()){
            invoice.setId(invoiceList.get(0).getId());
            invoice.setInvNo(invoiceList.get(0).getInvNo());
            invoice.setInvTo(invoiceList.get(0).getInvTo());
            invoice.setInvName(invoiceList.get(0).getInvName());
            invoice.setInvDate(invoiceList.get(0).getInvDate());
            invoice.setInvType(invoiceList.get(0).getInvType());
            invoice.setInvAddress(invoiceList.get(0).getInvAddress());
            invoice.setInvoiceDetails(invoiceList.get(0).getInvoiceDetails());
            invoice.setArcode(invoiceList.get(0).getArcode());
            invoice.setCreateBy(invoiceList.get(0).getCreateBy());
            invoice.setCreateDate(invoiceList.get(0).getCreateDate());
            invoice.setDepartment(invoiceList.get(0).getDepartment());
            invoice.setDueDate(invoiceList.get(0).getDueDate());
            invoice.setIsGroup(invoiceList.get(0).getIsGroup());
            invoice.setIsLock(invoiceList.get(0).getIsLock());
            invoice.setMAccTerm(invoiceList.get(0).getMAccTerm());
            invoice.setMFinanceItemstatus(invoiceList.get(0).getMFinanceItemstatus());
            invoice.setRemark(invoiceList.get(0).getRemark());
            invoice.setStaff(invoiceList.get(0).getStaff());
            invoice.setSubDepartment(invoiceList.get(0).getSubDepartment());
        }
        
        return invoice;
    }

    @Override
    public List<Invoice> getSearchInvoice(String fromData, String toDate, String department, String type,String agent,String status,String airticketWendy) {
        System.out.println("From Date : " + fromData + ":");
        System.out.println("To Date : " + toDate + ":");
        System.out.println("Department : " + department + ":");
        System.out.println("Type : " + type + ":");
        System.out.println("Status : " + status + ":");
        Session session = this.sessionFactory.openSession();
        String query = "";
        int AndQuery = 0;
        if("".equals(department)  && "".equals(type)  && "".equals(fromData)  && "".equals(toDate) && "".equals(agent)){
            query = "FROM Invoice st " ; 
        }else{
            query = "FROM Invoice st where" ;
        }
        
        if ( department != null && (!"".equalsIgnoreCase(department)) ) {
            AndQuery = 1;
            if("WendyOutbound".equalsIgnoreCase(department)){
                query += " st.department in ('Wendy' , 'Outbound') ";
            }else{
                query += " st.department = '" + department + "'";
            }
        }
       
        if (type != null && (!"".equalsIgnoreCase(type)) ) {
           if(AndQuery == 1){
                query += " and st.invType = '" + type + "'";
           }else{
               AndQuery = 1;
               query += " st.invType = '" + type + "'";
           }
        }else{
            if(AndQuery == 1){
                query += " and st.invType != 'T'";
           }else{
               AndQuery = 1;
               query += " st.invType != 'T'";
           }
        }
        
        
        
        if(agent != null && (!"".equalsIgnoreCase(agent))){
            if(AndQuery == 1){
                query += " and st.invTo = '" + agent + "'";
           }else{
               AndQuery = 1;
               query += " st.invTo = '" + agent + "'";
           }
        }
        
        if(status != null && (!"".equalsIgnoreCase(status))){
            if(AndQuery == 1){
                query += " and st.MFinanceItemstatus.id = " + status + "";
           }else{
               AndQuery = 1;
               query += " st.MFinanceItemstatus.id = " + status + "";
           }
        }
        
        if ((fromData != null )&&(!"".equalsIgnoreCase(fromData))) {
            if ((toDate != null )&&(!"".equalsIgnoreCase(toDate))) {
                if(AndQuery == 1){
                     query += " and st.invDate  BETWEEN  '" + fromData + "' AND '" + toDate + "' ";
                }else{
                    AndQuery = 1;
                     query += " st.invDate  BETWEEN  '" + fromData + "' AND '" + toDate + "' ";
                }
                
               
            }
        }
        
        if(airticketWendy != null && (!"".equalsIgnoreCase(airticketWendy))){
            if(AndQuery == 1){
                query += " and st.subDepartment = '" + airticketWendy + "'";
           }else{
               AndQuery = 1;
               query += " st.subDepartment = '" + airticketWendy + "'";
           }
        }
        
        query += "  ORDER BY st.invDate DESC";
        System.out.println("query : " + query);
        List<Invoice> list = session.createQuery(query).list();
        return list;
    }
    
    @Override
    public List<InvoiceView> setSearchInvoiceView(List<Invoice> listInvoice){
        List<InvoiceView> list = new LinkedList<InvoiceView>();
        UtilityFunction utility = new UtilityFunction();
        if(list != null){
            
            for (int i = 0; i < listInvoice.size(); i++) {
                InvoiceView invoiceView = new InvoiceView();
                BigDecimal sumAmount = new BigDecimal(0.0);
                List<InvoiceDetail> invoiceDetail = listInvoice.get(i).getInvoiceDetails();
                    invoiceView.setInvoiceId(listInvoice.get(i).getId());
                    invoiceView.setInvoiceNo(listInvoice.get(i).getInvNo());
                    invoiceView.setDepartment(listInvoice.get(i).getDepartment());
                    invoiceView.setType(listInvoice.get(i).getInvType());
                    String invoiceDate = utility.convertDateToString(listInvoice.get(i).getInvDate());
                    invoiceView.setInvoiceDate(invoiceDate);
                    invoiceView.setName(listInvoice.get(i).getInvName());
                    invoiceView.setAddress(listInvoice.get(i).getInvAddress());
                    if(listInvoice.get(i).getMAccTerm() != null){
                        invoiceView.setTermPayName(listInvoice.get(i).getMAccTerm().getName()); 
                    }
                                   
                
                    for (int j = 0; j < invoiceDetail.size(); j++) {
                        invoiceView.setCurrency(invoiceDetail.get(0).getCurAmount());
                        if(invoiceDetail.get(j).getAmount() != null ){
                            sumAmount =  sumAmount.add(invoiceDetail.get(j).getAmount()) ;
                        }else{
                            sumAmount =  sumAmount.add(new BigDecimal(0.0)) ;
                        }
                    }
                    
                    invoiceView.setTotalPrice(sumAmount);
                    list.add(invoiceView);
            }
        }
        
        return  list;
    }

    @Override
    public Invoice searchInvoiceNo(String invoiceId, String department, String invoiceType) {
        Session session = this.sessionFactory.openSession();
        Invoice invoice = new Invoice();
        List<Invoice> invoiceList = session.createQuery(SEARCH_INVOICE)
                .setParameter("invoiceId", invoiceId)
                .setParameter("invoiceType", invoiceType)
                .setParameter("invoiceDepartment", department)
                .list();
        if(!invoiceList.isEmpty()){
            invoice.setId(invoiceList.get(0).getId());
            invoice.setInvNo(invoiceList.get(0).getInvNo());
            invoice.setInvTo(invoiceList.get(0).getInvTo());
            invoice.setInvName(invoiceList.get(0).getInvName());
            invoice.setInvType(invoiceList.get(0).getInvType());
            invoice.setInvDate(invoiceList.get(0).getInvDate());
            invoice.setInvAddress(invoiceList.get(0).getInvAddress());
            invoice.setInvoiceDetails(invoiceList.get(0).getInvoiceDetails());
            invoice.setArcode(invoiceList.get(0).getArcode());
            invoice.setCreateBy(invoiceList.get(0).getCreateBy());
            invoice.setCreateDate(invoiceList.get(0).getCreateDate());
            invoice.setDepartment(invoiceList.get(0).getDepartment());
            invoice.setDueDate(invoiceList.get(0).getDueDate());
            invoice.setIsGroup(invoiceList.get(0).getIsGroup());
            invoice.setIsLock(invoiceList.get(0).getIsLock());
            invoice.setMAccTerm(invoiceList.get(0).getMAccTerm());
            invoice.setMFinanceItemstatus(invoiceList.get(0).getMFinanceItemstatus());
            invoice.setRemark(invoiceList.get(0).getRemark());
            invoice.setStaff(invoiceList.get(0).getStaff());
            invoice.setSubDepartment(invoiceList.get(0).getSubDepartment());
        }
        
        return invoice;
    }

    @Override
    public List<HashMap<String, Object>> getInvoiceDetailFromInvoiceNumber(String InvoiceNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String LockAndUnLockInvoice(String InvoiceId, int LockStatus) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<InvoiceDetail> getInvoiceDetailFromBillableDescId(String billableDescId) {
        Session session = this.sessionFactory.openSession();
        List<InvoiceDetail> list = session.createQuery("from InvoiceDetail inv WHERE inv.billableDesc.id = :billableDescId")
                .setParameter("billableDescId", billableDescId)
                .list();
        if(list.isEmpty()){
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return list;
    }

    @Override
    public Invoice searchInvoiceFromInvoiceNumber(String InvoiceNumber, String department, String invType) {
        Session session = this.sessionFactory.openSession();
        Invoice invoice = new Invoice();
        List<Invoice> invoiceList = session.createQuery(GET_INVOICE_FROMNO)
                .setParameter("invoiceNo", InvoiceNumber)
                .setParameter("department", department)
                .setParameter("invType", invType)
                .list(); 
        if(!invoiceList.isEmpty()){
            invoice.setId(invoiceList.get(0).getId());
            invoice.setInvNo(invoiceList.get(0).getInvNo());
            invoice.setInvTo(invoiceList.get(0).getInvTo());
            invoice.setInvName(invoiceList.get(0).getInvName());
            invoice.setInvType(invoiceList.get(0).getInvType());
            invoice.setInvDate(invoiceList.get(0).getInvDate());
            invoice.setInvAddress(invoiceList.get(0).getInvAddress());
            invoice.setInvoiceDetails(invoiceList.get(0).getInvoiceDetails());
            invoice.setArcode(invoiceList.get(0).getArcode());
            invoice.setCreateBy(invoiceList.get(0).getCreateBy());
            invoice.setCreateDate(invoiceList.get(0).getCreateDate());
            invoice.setDepartment(invoiceList.get(0).getDepartment());
            invoice.setDueDate(invoiceList.get(0).getDueDate());
            invoice.setIsGroup(invoiceList.get(0).getIsGroup());
            invoice.setIsLock(invoiceList.get(0).getIsLock());
            invoice.setMAccTerm(invoiceList.get(0).getMAccTerm());
            invoice.setMFinanceItemstatus(invoiceList.get(0).getMFinanceItemstatus());
            invoice.setRemark(invoiceList.get(0).getRemark());
            invoice.setStaff(invoiceList.get(0).getStaff());
            invoice.setSubDepartment(invoiceList.get(0).getSubDepartment());
        }
        
        return invoice;    
    }

    @Override
    public Invoice searchInvoiceForTaxInvoice(String InvoiceNumber, String department) {       
        Session session = this.sessionFactory.openSession();
        Invoice invoice = new Invoice();
        String query = "FROM Invoice inv where inv.invNo = :invoiceNo and inv.department = :department and inv.invType = 'V' and inv.MFinanceItemstatus.name = 'NORMAL'";
        List<Invoice> invoiceList = session.createQuery(query)
                .setParameter("invoiceNo", InvoiceNumber)
                .setParameter("department", department)
                .list(); 
        
        if(!invoiceList.isEmpty()){
            invoice.setId(invoiceList.get(0).getId());
            invoice.setInvNo(invoiceList.get(0).getInvNo());
            invoice.setInvTo(invoiceList.get(0).getInvTo());
            invoice.setInvName(invoiceList.get(0).getInvName());
            invoice.setInvType(invoiceList.get(0).getInvType());
            invoice.setInvDate(invoiceList.get(0).getInvDate());
            invoice.setInvAddress(invoiceList.get(0).getInvAddress());
            invoice.setInvoiceDetails(invoiceList.get(0).getInvoiceDetails());
            invoice.setArcode(invoiceList.get(0).getArcode());
            invoice.setCreateBy(invoiceList.get(0).getCreateBy());
            invoice.setCreateDate(invoiceList.get(0).getCreateDate());
            invoice.setDepartment(invoiceList.get(0).getDepartment());
            invoice.setDueDate(invoiceList.get(0).getDueDate());
            invoice.setIsGroup(invoiceList.get(0).getIsGroup());
            invoice.setIsLock(invoiceList.get(0).getIsLock());
            invoice.setMAccTerm(invoiceList.get(0).getMAccTerm());
            invoice.setMFinanceItemstatus(invoiceList.get(0).getMFinanceItemstatus());
            invoice.setRemark(invoiceList.get(0).getRemark());
            invoice.setStaff(invoiceList.get(0).getStaff());
            invoice.setSubDepartment(invoiceList.get(0).getSubDepartment());
        
        }else{
            invoice = null;
        }
   
        return invoice;    
    }
    
     @Override
    public BigDecimal[] checkBillDescInuse(String billdesc,String cost,String amount) {
        BigDecimal[] value = new BigDecimal[2];
        BigDecimal costInt =  new BigDecimal(cost.replaceAll(",", ""));
        BigDecimal amountInt =  new BigDecimal(amount.replaceAll(",", ""));
        BigDecimal amountTemp = new BigDecimal(0);
        BigDecimal costTemp = new BigDecimal(0);
        Session session = this.sessionFactory.openSession();
        Invoice invoice = new Invoice();
        System.out.println("billdesc : "+billdesc);
        System.out.println("cost : "+cost);
        System.out.println("amount : "+amount);
        List<InvoiceDetail> invoiceList = session.createQuery(GET_BILLDESC)
                .setParameter("billableDescId", billdesc)
                .list();
        if(invoiceList != null){
            for (int i = 0; i < invoiceList.size(); i++) {
                amountInt = amountInt.subtract(invoiceList.get(i).getAmountLocal()== null ? BigDecimal.ZERO : invoiceList.get(i).getAmountLocal());
                costInt = costInt.subtract(invoiceList.get(i).getCost() == null ? BigDecimal.ZERO : invoiceList.get(i).getCost());
            }
        }
        
        System.out.println("costint : "+costInt);
         System.out.println("amountInt : "+amountInt);
        value[0] = costInt;
        value[1] = amountInt;
        session.close();
        this.sessionFactory.close();
        return value;
    }

    @Override
    public String checkOverflowValueOfInvoice(List<InvoiceDetail> invoiceDetail) {
        Session session = this.sessionFactory.openSession();
        String result = "okMoney";
        boolean isOver = false;
        if(invoiceDetail != null && invoiceDetail.size() != 0){
            for(int i=0;i<invoiceDetail.size();i++){
                BigDecimal cost = new BigDecimal(0);
                BigDecimal price = new BigDecimal(0);
                BigDecimal exRate = new BigDecimal(0);
                String curPrice = "";
                
                BigDecimal InvoiceCost = new BigDecimal(0);
                BigDecimal InvoicePrice  = new BigDecimal(0);
                InvoiceDetail  detail  = invoiceDetail.get(i);
                
                if(detail.getBillableDesc()  != null){
                    
                    if(detail.getBillableDesc().getId() != null){
                        List<BillableDesc> Billdesc = session.createQuery(GET_BILL_AMOUNT)
                            .setParameter("descid", detail.getBillableDesc().getId())
                            .list();
                        cost = (Billdesc.get(0).getCost() != null ? Billdesc.get(0).getCost() : new BigDecimal(BigInteger.ZERO));
                        curPrice = (Billdesc.get(0).getCurrency() != null && !"".equals(Billdesc.get(0).getCurrency()) ? Billdesc.get(0).getCurrency() : "");
                        
                        if("THB".equals(curPrice)){
                            price = (Billdesc.get(0).getPrice() != null ? Billdesc.get(0).getPrice() : new BigDecimal(BigInteger.ZERO));
                        
                        } else {
                            exRate = (detail.getExRate() != null ? detail.getExRate() : new BigDecimal(0));
                            price = (Billdesc.get(0).getPrice() != null ? Billdesc.get(0).getPrice() : new BigDecimal(BigInteger.ZERO)).multiply(exRate);
                            price = price.setScale(2, BigDecimal.ROUND_HALF_UP);
                        }
                        
                        System.out.println("cost : "+cost +"price : "+price);
                        List<InvoiceDetail> invoiceList;
                        
                        if(detail.getId() != null){
                            invoiceList = session.createQuery(GET_BILLDESC_FILTER )
                            .setParameter("billableDescId", detail.getBillableDesc().getId())
                            .setParameter("invdID", detail.getId())
                            .list();
                            
                        }else{
                            invoiceList = session.createQuery(GET_BILLDESC )
                            .setParameter("billableDescId", detail.getBillableDesc().getId())
                            .list();
                        }

                        for(int j=0;j<invoiceList.size();j++){
                            if(invoiceList.get(j).getCost() != null && !"".equalsIgnoreCase(String.valueOf(invoiceList.get(j).getCost()))){
                                InvoiceCost = InvoiceCost.add(invoiceList.get(j).getCost());
                            }else{
                                InvoiceCost = InvoiceCost.add(BigDecimal.ZERO);
                            }

                            if(invoiceList.get(j).getAmountLocal()!= null && !"".equalsIgnoreCase(String.valueOf(invoiceList.get(j).getAmountLocal()))){
                                InvoicePrice = InvoicePrice.add(invoiceList.get(j).getAmountLocal());
                            }else{
                                InvoicePrice = InvoicePrice.add(BigDecimal.ZERO);
                            }

                        }
                        System.out.println("InvoiceCost : "+InvoiceCost +"InvoicePrice : "+InvoicePrice);

                        if(detail.getAmountLocal()!= null && !"".equals(detail.getAmountLocal())){
                            InvoicePrice = InvoicePrice.add(detail.getAmountLocal());
                        }
                        if(detail.getCost() != null && !"".equals(detail.getCost())){
                            InvoiceCost = InvoiceCost.add(detail.getCost());
                        }

                        System.out.println("SumInvoiceCost : "+InvoiceCost +"SumInvoicePrice : "+InvoicePrice);
                        System.out.println("Compare price : "+price.compareTo(InvoicePrice));
                        
                        if((price.compareTo(InvoicePrice) == -1)){
                            result = "moreMoney";
                            isOver = true;
                        }else{
                            result = "okMoney";
                        }
                        
                    }else{
                        result = "okMoney";
                    }
                    
                }else{
                    result = "okMoney";
                }
            }
        }else{
            result = "okMoney";
        }
        
        this.sessionFactory.close();
        session.close();
        
        if(isOver){
            result = "moreMoney";
        }
        
        return result;
    }
    
    @Override
    public String insertInvoiceDetail(Invoice invoice) {
        String result = "";
        Session session = this.sessionFactory.openSession();
        try { 
            transaction = session.beginTransaction();
            System.out.println("generateInvoiceNo : "+invoice.getDepartment() +" : "+invoice.getInvType());
            result = generateInvoiceNo(invoice.getDepartment() , invoice.getInvType() , invoice.getInvDate());
//            result = invoice.getInvNo();
            invoice.setInvNo(result);
            session.save(invoice);
            List<InvoiceDetail> invoiceDetail = invoice.getInvoiceDetails();
            if(invoiceDetail != null){
                for (int i = 0; i < invoiceDetail.size(); i++) {
                    if (invoiceDetail.get(i).getId() != null && !"".equalsIgnoreCase(invoiceDetail.get(i).getId())) {
                        session.update(invoiceDetail.get(i));
                    } else {
                        session.save(invoiceDetail.get(i));
                    }
                }
            }
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            System.out.println("ss result : "+ invoice.getInvNo());
//            result = "success";
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
    public String updateInvoiceDetail(Invoice invoice) {
        String result = "";
        Session session = this.sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            session.update(invoice);
            
            List<InvoiceDetail> invoiceDetail = invoice.getInvoiceDetails();
            for (int i = 0; i < invoiceDetail.size(); i++) {
                if (invoiceDetail.get(i).getId() != null && !"".equalsIgnoreCase(invoiceDetail.get(i).getId())) {
                    session.update(invoiceDetail.get(i));
                } else {
                    session.save(invoiceDetail.get(i));
                }
            }

            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = invoice.getInvNo();
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            this.sessionFactory.close();
            session.close();
            result = "update fail";
            
        }
        return  result;
    }

    @Override
    public List<InvoiceDetail> getInvoiceDetailFromBillDescId(String billableDescId) {
        Session session = this.sessionFactory.openSession();
        List<InvoiceDetail> listInvDetail = new ArrayList<InvoiceDetail>();
        List<InvoiceDetail> list = session.createQuery("from InvoiceDetail inv WHERE inv.billableDesc.id = :billableDescId and inv.invoice.MFinanceItemstatus = '1' ")
                .setParameter("billableDescId", billableDescId)
                .list();
        if(list.isEmpty()){
            return null;
        }else{
            for(int i = 0 ; i < list.size() ; i++){
                List<ReceiptDetail> listrec = session.createQuery("from ReceiptDetail d WHERE d.invoiceDetail.id = :invDetailId and d.receipt.MFinanceItemstatus = '1'").setParameter("invDetailId", list.get(i).getId()).list();
                if(!listrec.isEmpty()){
                    listInvDetail.add(list.get(i));
                }
            }
        }
        session.close();
        this.sessionFactory.close();
        return listInvDetail;
    }

    @Override
    public String checkRecipt(String refNo) {
        String isCheck ="";
        Session session = this.sessionFactory.openSession();
        List<ReceiptDetail> list = session.createQuery("from ReceiptDetail rec where rec.invoiceDetail.invoice.invNo =:invno  and   rec.receipt.MFinanceItemstatus.id  != 2")
                .setParameter("invno", refNo)
                .list();
        if(list.isEmpty()){
            isCheck = "noReceipt";
        }else{
            isCheck = "yesReceipt";
        }
        session.close();
        this.sessionFactory.close();
        return isCheck;
    }

    @Override
    public String taxInvoice(String refNo) {
         String isCheck ="";
        Session session = this.sessionFactory.openSession();
        List<TaxInvoiceDetail> list = session.createQuery("from TaxInvoiceDetail tax where tax.invoiceDetail.invoice.invNo =:invno and   tax.taxInvoice.MFinanceItemstatus.id  != 2")
                .setParameter("invno", refNo)
                .list();
        if(list.isEmpty()){
            isCheck = "noTaxinvoice";
        }else{
            isCheck = "yesTaxinvoice";
        }
        session.close();
        this.sessionFactory.close();
        return isCheck;
    }
    
    
    private String checkReciptDelete(String id) {
        String isCheck ="";
        Session session = this.sessionFactory.openSession();
        List<ReceiptDetail> list = session.createQuery("from ReceiptDetail rec where rec.invoiceDetail.id =:id  and   rec.receipt.MFinanceItemstatus.id  != 2")
                .setParameter("id", id)
                .list();
        if(list.isEmpty()){
            isCheck = "noReceipt";
        }else{
            isCheck = "yesReceipt";
        }
        session.close();
        this.sessionFactory.close();
        return isCheck;
    }

    private String checkTaxInvoiceDelete(String id) {
         String isCheck ="";
        Session session = this.sessionFactory.openSession();
        List<TaxInvoiceDetail> list = session.createQuery("from TaxInvoiceDetail tax where tax.invoiceDetail.id =:id and  tax.taxInvoice.MFinanceItemstatus.id  != 2")
                .setParameter("id", id)
                .list();
        if(list.isEmpty()){
            isCheck = "noTaxinvoice";
        }else{
            isCheck = "yesTaxinvoice";
        }
        session.close();
        this.sessionFactory.close();
        return isCheck;
    }

    @Override
    public Invoice getInvoiceByWildCardSearch(String invoiceId, String invoiceNo, String wildCardSearch, String keyCode, String department, String invType) {
        StringBuffer query = new StringBuffer(" from Invoice inv ");
        //Up
        if ("38".equalsIgnoreCase(keyCode)) {
            if("".equalsIgnoreCase(wildCardSearch)){
                query.append(" where ");
                query.append(" inv.id > '" + invoiceId + "' and inv.department = '" + department + "' and inv.invType = '" + invType + "' ORDER BY inv.id asc ");
            }else{
                query.append(" where ");
                query.append(" inv.id > '" + invoiceId + "' and inv.department = '" + department + "' and inv.invType = '" + invType + "' and inv.invNo like '" + wildCardSearch + "' ORDER BY inv.id asc ");
            }    
        }
        //Down
        if ("40".equalsIgnoreCase(keyCode)) {
            if("".equalsIgnoreCase(wildCardSearch)){               
                query.append(" where ");
                query.append(" inv.id < '" + invoiceId + "' and inv.department = '" + department + "' and inv.invType = '" + invType + "' ORDER BY inv.id desc ");
            }else{
                query.append(" where ");
                query.append(" inv.id < '" + invoiceId + "' and inv.department = '" + department + "' and inv.invType = '" + invType + "' and inv.invNo like '" + wildCardSearch + "' ORDER BY inv.id desc ");
            }           
        }
        //Lastest
        if ("119".equalsIgnoreCase(keyCode)) {
            query.append(" where ");
            query.append(" inv.department = '" + department + "' and inv.invType = '" + invType + "' ORDER BY inv.id desc ");
        }
        
        Session session = this.sessionFactory.openSession();
        Query HqlQuery = session.createQuery(query.toString());
        System.out.println(HqlQuery.toString());
        HqlQuery.setMaxResults(1);
        List<Invoice> invoiceList = HqlQuery.list();
        if (invoiceList.isEmpty()) {
            StringBuffer queryTemp = new StringBuffer(" from Invoice inv ");
            queryTemp.append(" where ");
            queryTemp.append(" inv.id = '" + invoiceId + "' and inv.department = '" + department + "' and inv.invType = '" + invType + "' ");
            HqlQuery = session.createQuery(queryTemp.toString());
            HqlQuery.setMaxResults(1);
            List<Invoice> invoiceListTemp = HqlQuery.list();
            return (!invoiceListTemp.isEmpty() ? invoiceListTemp.get(0) : null);
        }
        
        this.sessionFactory.close();
        session.close();
        return invoiceList.get(0);
    }

    @Override
    public String checkFlagBooking(Invoice invoice) {
        String result = "";
        Session session = this.sessionFactory.openSession();
        List<InvoiceDetail> invoiceDetail = invoice.getInvoiceDetails();
        for (int i = 0; i < invoiceDetail.size(); i++) {
            if(invoiceDetail.get(i).getBillableDesc() != null){
                String billid = invoiceDetail.get(i).getBillableDesc().getId();
                List<BillableDesc> billDescList = session.createQuery(GET_BILLDESC_FLAG)
                    .setParameter("billddesid", billid)
                    .list();
                if(billDescList != null){
                    String masterid = billDescList.get(0).getBillable().getMaster().getId();
                    String mBillTypeId =  invoiceDetail.get(i).getMbillType().getId();

                    if("1".equals(mBillTypeId) || "7".equals(mBillTypeId)){
                        Query query = session.createQuery(UPDATE_FILG17);
                        query.setParameter("masterid", masterid);
                        System.out.println("update master 17: "+query.executeUpdate());  
                        result  = "update success";
                    }else if("2".equals(mBillTypeId) || "8".equals(mBillTypeId)){
                        Query query = session.createQuery(UPDATE_FILG28);
                        query.setParameter("masterid", masterid);
                        System.out.println("update master 28: "+query.executeUpdate());
                        result  = "update success";
                    }else if("3".equals(mBillTypeId)){
                        Query query = session.createQuery(UPDATE_FILG3);
                        query.setParameter("masterid", masterid);
                        System.out.println("update master 3: "+query.executeUpdate());
                        result  = "update success";
                    }else if("4".equals(mBillTypeId)){
                        Query query = session.createQuery(UPDATE_FILG4);
                        query.setParameter("masterid", masterid);
                        System.out.println("update master 4: "+query.executeUpdate());
                        result  = "update success";
                    }else if("6".equals(mBillTypeId)){
                        Query query = session.createQuery(UPDATE_FILG6);
                        query.setParameter("masterid", masterid);
                        System.out.println("update master 6: "+query.executeUpdate());
                        result  = "update success";
                    }else{
                        result  = "update unsuccess";
                    }
                }else{
                    result  = "update unsuccess";
                }
            }else{
                result  = "update success";
            }
        }
        return result;
    }

    @Override
    public String setBookingStatus(Invoice invoice) {
        String result = "";
        Session session = this.sessionFactory.openSession();
        List<InvoiceDetail> invoiceDetail = invoice.getInvoiceDetails();
        for (int i = 0; i < invoiceDetail.size(); i++) {
            if(invoiceDetail.get(i).getBillableDesc() != null){
                String billid = invoiceDetail.get(i).getBillableDesc().getId();
                // Get Bill ID
                List<BillableDesc> billDescList = session.createQuery(GET_BILLDESC_FLAG)
                    .setParameter("billddesid", billid)
                    .list();
                if(billDescList != null){
                    String masterid = billDescList.get(0).getBillable().getMaster().getId();
                    if(masterid != null && !"".equals(masterid)){
                        String queryText = "select * from  `non_billable_view`  non  where non.masterid = " + masterid;
                        List<Object[]> nonBillableViews = session.createSQLQuery(queryText)
                            .addScalar("masterid", Hibernate.STRING)
                            .addScalar("invoice_detail_id", Hibernate.STRING)
                            .list();
                        if(nonBillableViews == null){
                            Query query = session.createQuery(UPDATE_BOOKING_STATUS);
                            query.setParameter("masterbillid", masterid);
                            System.out.println("update booking status: "+query.executeUpdate());
                        }
                        result = "update booking success";
                    }else{
                        result = "update booking unsuccess";
                    }
                }else{
                    result = "update booking unsuccess";
                }
            }else{
                result = "update booking success";
            }
        }
        return result;
    }
    
    @Override
    public List<InvoiceDetail> getInvoiceDetailFromBillDescIdAndRecDetailId(String billableDescId,String receiptDetailId) {
        Session session = this.sessionFactory.openSession();
        String invdetailIdTemp = "";
        if(receiptDetailId != null && !"".equalsIgnoreCase(receiptDetailId)){
            List<ReceiptDetail> listRec = session.createQuery("from ReceiptDetail rec WHERE rec.id = '"+receiptDetailId+"'")
                    .list();
            if(!listRec.isEmpty()){
                invdetailIdTemp = listRec.get(0).getInvoiceDetail().getId();
            }
        }
        String query = "from InvoiceDetail inv WHERE inv.billableDesc.id = :billableDescId and inv.invoice.MFinanceItemstatus = '1' ";
        if(!"".equalsIgnoreCase(invdetailIdTemp)){
            query += " and inv.id <> '"+invdetailIdTemp+"' ";
        }
        List<InvoiceDetail> list = session.createQuery(query)
                .setParameter("billableDescId", billableDescId)
                .list();
        if(list.isEmpty()){
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return list;
    }

    @Override
    public InvoiceDetail getInvoiceDetailFromId(String invoiceDetailId) {
        Session session = this.sessionFactory.openSession();
        String query = "from InvoiceDetail inv WHERE inv.id = '"+invoiceDetailId+"' ";

        List<InvoiceDetail> list = session.createQuery(query)
                .list();
        if(list.isEmpty()){
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return list.get(0);
    
    }

    private Invoice getExportData(Invoice invoice) {
        Session session = this.sessionFactory.openSession();
        List<Invoice> invoiceList = session.createQuery("FROM Invoice inv where inv.id = :invoiceId ")
            .setParameter("invoiceId", invoice.getId())
            .list();
        if (!invoiceList.isEmpty()) {
            Invoice mInvoice = invoiceList.get(0);
            invoice.setIsExport(mInvoice.getIsExport());
            invoice.setExportDate(mInvoice.getExportDate());
            invoice.setDataNo(mInvoice.getDataNo());
        }
        session.close();
        return invoiceList.get(0);
    }
}
