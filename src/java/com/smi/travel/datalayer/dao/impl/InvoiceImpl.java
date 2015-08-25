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
import com.smi.travel.datalayer.entity.Stock;
import com.smi.travel.datalayer.view.entity.InvoiceView;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.math.BigInteger;
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
public class InvoiceImpl implements InvoiceDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;
    private static final String DELETEALL_INVOICE_QUERY ="DELETE FROM Invoice in where in.id = :invouceID";
    private static final String GET_INVOICE = "FROM Invoice inv where inv.invNo = :invoiceNo";
    private static final String GET_INVOICE_FROMID = "FROM Invoice inv where inv.id = :invoiceId";
    private static final String SEARCH_INVOICE = "FROM Invoice inv where inv.id = :invoiceId and inv.invType = :invoiceType and inv.department = :invoiceDepartment";
    private static final String SELECT_INVOICE_DETAIL = "FROM InvoiceDetail ind where ind.invoice.id = :invoiceID";
    private static final String DELETE_INVOICEDETAIL_QUERY ="DELETE FROM InvoiceDetail ind where ind.id = :invoiceDetailID";
    private static final String SEARCH_INVOICE_TYPE = "FROM Invoice inv where inv.department = :invoiceDepartment and inv.invType = :invoiceType ORDER BY inv.invNo DESC LIMIT 1";
    private static final String GET_INVOICE_FROMNO = "FROM Invoice inv where inv.invNo = :invoiceNo and inv.department = :department and inv.invType = :invType";
    private static final String GET_INVOICE_FOR_TAX_INVOICE = "FROM Invoice inv where inv.invNo = :invoiceNo and inv.department = :department";
    private static final String GET_BILLDESC = "from InvoiceDetail inv WHERE inv.billableDesc.id = :billableDescId";
    private static final String GET_BILLDESC_FILTER = "from InvoiceDetail inv WHERE inv.billableDesc.id = :billableDescId and inv.id != :invdID";
    private static final String GET_BILL_AMOUNT = "from BillableDesc bill where bill.id = :descid";
    
    @Override
    public String insertInvoice(Invoice invoice) {
        String result = "";
        Session session = this.sessionFactory.openSession();
        try { 
            transaction = session.beginTransaction();
            result = generateInvoiceNo(invoice.getDepartment() , invoice.getInvType());
//            result = invoice.getInvNo();
            invoice.setInvNo(result);
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

    private String generateInvoiceNo(String department, String invoiceType){
        String invNo = "";
        String invType = "";
        Session session = this.sessionFactory.openSession();
        List<String> list = new LinkedList<String>();
        Date thisdate = new Date();
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("MMyy");
        Query query = session.createSQLQuery("SELECT RIGHT(inv_no, 4) as invnum  FROM invoice where department = :department and inv_type = :invoiceType and inv_no Like :invno  ORDER BY RIGHT(inv_no, 4) desc");
        query.setParameter("invno", "%"+ df.format(thisdate) + "%");
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
            }
        }
        
        if (list.isEmpty()) {
            invNo = departtype + df.format(thisdate) + "-" + "0001";
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
                    invNo = departtype + "-" + df.format(thisdate) + "-" + temp;
                }else{
                    int running = Integer.parseInt(invNo) + 1;
                    String temp = String.valueOf(running);
                    for (int i = temp.length(); i < 4; i++) {
                        temp = "0" + temp;
                    }
                    invNo = departtype + "-" + df.format(thisdate) + "-" + temp;
                }
            }
        }
        System.out.println("invNo ::: " +invNo);
        session.close();
        this.sessionFactory.close();
        return invNo.replace("-","");
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
            invoice.setMAccpay(invoiceList.get(0).getMAccpay());
            invoice.setMFinanceItemstatus(invoiceList.get(0).getMFinanceItemstatus());
            invoice.setRemark(invoiceList.get(0).getRemark());
            invoice.setStaff(invoiceList.get(0).getStaff());
            invoice.setSubDepartment(invoiceList.get(0).getSubDepartment());
        }
        
        return invoice;
    }

    @Override
    public List<Invoice> getSearchInvoice(String fromData, String toDate, String department, String type) {
        Session session = this.sessionFactory.openSession();
        String query = "";
        int AndQuery = 0;
        if("".equals(department) && department == null && "".equals(type) && type == null && fromData == null &&  toDate == null){
            query = "FROM Invoice st " ;
        }else{
            query = "FROM Invoice st where" ;
        }
        
        if ( department != null && (!"".equalsIgnoreCase(department)) ) {
            AndQuery = 1;
            query += " st.department = '" + department + "'";
        }
       
        if (type != null && (!"".equalsIgnoreCase(type)) ) {
           if(AndQuery == 1){
                query += " and st.invType = '" + type + "'";
           }else{
               AndQuery = 1;
               query += " st.invType = '" + type + "'";
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
                    String invoiceDate = utility.convertDateToString(listInvoice.get(i).getCreateDate());
                    invoiceView.setInvoiceDate(invoiceDate);
                    invoiceView.setName(listInvoice.get(i).getInvName());
                    invoiceView.setAddress(listInvoice.get(i).getInvAddress());
                    if(listInvoice.get(i).getMAccpay() != null){
                        invoiceView.setTermPayName(listInvoice.get(i).getMAccpay().getName()); 
                    }
                                   
                
                    for (int j = 0; j < invoiceDetail.size(); j++) {
                        invoiceView.setCurrency(invoiceDetail.get(0).getCurAmount());
                        sumAmount =  sumAmount.add(invoiceDetail.get(j).getAmount()) ;
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
            invoice.setMAccpay(invoiceList.get(0).getMAccpay());
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
            invoice.setMAccpay(invoiceList.get(0).getMAccpay());
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
        List<Invoice> invoiceList = session.createQuery(GET_INVOICE_FOR_TAX_INVOICE)
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
            invoice.setMAccpay(invoiceList.get(0).getMAccpay());
            invoice.setMFinanceItemstatus(invoiceList.get(0).getMFinanceItemstatus());
            invoice.setRemark(invoiceList.get(0).getRemark());
            invoice.setStaff(invoiceList.get(0).getStaff());
            invoice.setSubDepartment(invoiceList.get(0).getSubDepartment());
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
                amountInt = amountInt.subtract(invoiceList.get(i).getAmount());
                costInt = costInt.subtract(invoiceList.get(i).getCost());
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
        String result = "";
        for(int i=0;i<invoiceDetail.size();i++){
            BigDecimal cost;
            BigDecimal price;
            BigDecimal InvoiceCost = new BigDecimal(0);
            BigDecimal InvoicePrice  = new BigDecimal(0);
            InvoiceDetail  detail  = invoiceDetail.get(i);
            List<BillableDesc> Billdesc = session.createQuery(GET_BILL_AMOUNT)
                .setParameter("descid", detail.getBillableDesc().getId())
                .list();
            cost = new BigDecimal(Billdesc.get(0).getCost());
            price = new BigDecimal(Billdesc.get(0).getPrice());
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
                InvoiceCost = InvoiceCost.add(invoiceList.get(j).getCost());
                InvoicePrice = InvoicePrice.add(invoiceList.get(j).getAmount());
            }
            System.out.println("InvoiceCost : "+InvoiceCost +"InvoicePrice : "+InvoicePrice);
            
            InvoicePrice = InvoicePrice.add(detail.getAmount());
            InvoiceCost = InvoiceCost.add(detail.getCost());
            System.out.println("SumInvoiceCost : "+InvoiceCost +"SumInvoicePrice : "+InvoicePrice);
            System.out.println("Compare price : "+price.compareTo(InvoicePrice));
            if((price.compareTo(InvoicePrice) == -1)||(cost.compareTo(InvoiceCost) == -1)){
                result = "fail";
            }else{
                result = "success";
            }
            
        }
        this.sessionFactory.close();
        session.close();
        return result;
        
    }
}
