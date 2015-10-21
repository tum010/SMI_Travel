/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.TaxInvoiceDao;
import com.smi.travel.datalayer.entity.CreditNoteDetail;
import com.smi.travel.datalayer.entity.ReceiptDetail;
import com.smi.travel.datalayer.entity.TaxInvoice;
import com.smi.travel.datalayer.entity.TaxInvoiceDetail;
import com.smi.travel.datalayer.view.entity.TaxInvoiceView;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    private static final int MAX_ROW = 200;

    @Override
    public String insertTaxInvoice(TaxInvoice tax) {
        String result = "";
        Session session = this.getSessionFactory().openSession();
        try { 
            setTransaction(session.beginTransaction());
            String taxNo = gennarateTaxInvoiceNo(tax.getTaxInvDate(),tax.getDepartment());
            tax.setTaxNo(taxNo);            
            session.save(tax);
            System.out.println("Tax Invoice Id : "+tax.getId());
            List<TaxInvoiceDetail> taxInvoiceDetail = tax.getTaxInvoiceDetails();
            if(taxInvoiceDetail != null){
                for (int i = 0; i < taxInvoiceDetail.size(); i++) {
                    System.out.println("Tax Invoice Detail Id : "+taxInvoiceDetail.get(i).getInvoiceDetail().getId());
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
    
    private String gennarateTaxInvoiceNo(Date taxInvDate,String department){
        String taxNo = "";
        Session session = this.sessionFactory.openSession();
        List<TaxInvoice> list = new LinkedList<TaxInvoice>();
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("yyMM");
        Query query = session.createQuery("from TaxInvoice t where t.department = :department and t.taxNo Like :taxNo Order by t.taxNo desc");
        query.setParameter("taxNo", "%"+ df.format(taxInvDate) + "%");
        query.setParameter("department", department);
        query.setMaxResults(1);
        list = query.list();
        if (list.isEmpty()) {
            taxNo = df.format(taxInvDate) + "-" + "0001";
        } else {
            taxNo = String.valueOf(list.get(0).getTaxNo());
            if (!taxNo.equalsIgnoreCase("")) {
                System.out.println("taxNo" + taxNo.substring(4,8) + "/////");
                int running = Integer.parseInt(taxNo.substring(4,8)) + 1;
                String temp = String.valueOf(running);
                for (int i = temp.length(); i < 4; i++) {
                    temp = "0" + temp;
                }
                taxNo = df.format(taxInvDate) + "-" + temp;
            }
        }
        session.close();
        this.sessionFactory.close();
        return taxNo.replace("-","");
    }

    @Override
    public String updateTaxInvoice(TaxInvoice tax) {
        UtilityFunction utilfunction = new UtilityFunction();
        String result = "";
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("yyyy-MM-dd HH:mm:ss");
        String updateDate = dateformat.format(date);
        tax.setUpdateDate(utilfunction.convertStringToDate(updateDate));
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
    public List<TaxInvoiceView> SearchTaxInvoiceFromFilter(String from, String To, String Department, String Status) {
        StringBuffer query = new StringBuffer("from TaxInvoice taxInv ");
        boolean haveCondition = false;
        if ((from != null) && (!"".equalsIgnoreCase(from))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" taxInv.taxInvDate >= '" + from + "'");
            haveCondition = true;
        }
        if ((To != null) && (!"".equalsIgnoreCase(To))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" taxInv.taxInvDate <= '" + To + "'");
            haveCondition = true;
        }
        if ((Department != null) && (!"".equalsIgnoreCase(Department))) {
            query.append(haveCondition ? " and" : " where");
            if("WendyOutbound".equalsIgnoreCase(Department)){
                query.append(" taxInv.department in ('Wendy' , 'Outbound') ");
            }else{
                query.append(" taxInv.department = '" + Department + "'");
            }
            
            haveCondition = true;
        }
        if ((Status != null) && (!"".equalsIgnoreCase(Status))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" taxInv.MFinanceItemstatus.id = '" + Status + "'");
            haveCondition = true;
        }
        
        Session session = this.sessionFactory.openSession();
        Query HqlQuery = session.createQuery(query.toString());
        System.out.println(HqlQuery.toString());
        HqlQuery.setMaxResults(MAX_ROW);
        List<TaxInvoice> taxInvoiceList = HqlQuery.list();
        if (taxInvoiceList.isEmpty()) {
            return null;
        }
        
        List<TaxInvoiceView> taxInvoiceViewList = mappingTaxInvoice(taxInvoiceList);
        this.sessionFactory.close();
        session.close();
        return taxInvoiceViewList;
    }
    
    public List<TaxInvoiceView> mappingTaxInvoice(List<TaxInvoice> taxInvoiceList){
        List<TaxInvoiceView> taxInvoiceViewList = new ArrayList<TaxInvoiceView>();
        
        for(int i=0;i<taxInvoiceList.size();i++){
            TaxInvoiceView taxInvoiceView = new TaxInvoiceView();
            TaxInvoice taxInvoice = new TaxInvoice();
            taxInvoice = taxInvoiceList.get(i);
            
            taxInvoiceView.setAddress(taxInvoice.getTaxInvAddr());
            taxInvoiceView.setDepartment(taxInvoice.getDepartment());
            taxInvoiceView.setDetail(taxInvoice.getRemark());
            taxInvoiceView.setName(taxInvoice.getTaxInvName());
            taxInvoiceView.setTaxDate(String.valueOf(taxInvoice.getTaxInvDate()));
            taxInvoiceView.setTaxId(taxInvoice.getId());
            taxInvoiceView.setTaxNo(taxInvoice.getTaxNo());
            taxInvoiceView.setTaxTo(taxInvoice.getTaxInvTo());
            
            if("1".equalsIgnoreCase(taxInvoice.getMFinanceItemstatus().getId())){
                taxInvoiceView.setStatus("Normal");
            } else {
                taxInvoiceView.setStatus("Void");
            }
//            taxInvoiceView.setStatus(taxInvoice.getMFinanceItemstatus().getId());
            
            BigDecimal totalAmount = new BigDecimal(0);
            BigDecimal totalGross = new BigDecimal(0);
            BigDecimal totalVat = new BigDecimal(0);
            String totalInvoiceNo = "";
            String totalReceiptNo = "";
            List<String> invNoChkList = new ArrayList<String>();
            List<String> recNoChkList = new ArrayList<String>();
            List<TaxInvoiceDetail> taxInvoiceDetailList = new ArrayList<TaxInvoiceDetail>();
            taxInvoiceDetailList = taxInvoice.getTaxInvoiceDetails();
            for(int j=0;j<taxInvoiceDetailList.size();j++){
                if(!"".equalsIgnoreCase(totalInvoiceNo)){
                    totalInvoiceNo += " ";
                } else {
                    totalInvoiceNo += "";
                }
                
                TaxInvoiceDetail taxInvoiceDetail = new TaxInvoiceDetail();
                taxInvoiceDetail = taxInvoiceDetailList.get(j);
                
                BigDecimal amount = new BigDecimal(0);
                BigDecimal vat = new BigDecimal(0);
                BigDecimal onehundred = new BigDecimal(100);
                BigDecimal gross = new BigDecimal(0);
                if(taxInvoiceDetail.getIsVat() == 1){
                    if(taxInvoiceDetail.getAmount() != null){
                        amount = taxInvoiceDetail.getAmount();           
                        vat = taxInvoiceDetail.getVat();

                        gross = ((amount.multiply(onehundred)).divide((vat.add(onehundred)), 4, RoundingMode.HALF_UP));                       
                        vat = amount.subtract(gross);

                        totalAmount = totalAmount.add(amount);
                        totalGross = totalGross.add(gross);
                        totalVat = totalVat.add(vat);
                    }
                } else {
                    if(taxInvoiceDetail.getAmount() != null){
                        amount = taxInvoiceDetail.getAmount(); 
                        totalAmount = totalAmount.add(amount);
                    }
                }
               
                String invoiceNo = "";
                if(taxInvoiceDetail.getInvoiceDetail() != null){
                    invoiceNo = taxInvoiceDetail.getInvoiceDetail().getInvoice().getInvNo();
                    if(invNoChkList.isEmpty()){
                        invNoChkList.add(invoiceNo);
                        totalInvoiceNo += invoiceNo;
                    } else {
                        int matchInv = 0;
                        invNoChkList.add(invoiceNo);
                        for(int a=0;a<invNoChkList.size();a++){
                            String invNoChk = invNoChkList.get(a);
                            if(invoiceNo.equalsIgnoreCase(invNoChk)){
                                matchInv++;
                                a = invNoChkList.size();
                            }
                        }
                        if(matchInv == 0){
                            totalInvoiceNo += invoiceNo;
                        }
                    }                                     
                    if(!"".equalsIgnoreCase(totalReceiptNo)){
                        totalReceiptNo += " ";
                    } else {
                        totalReceiptNo += "";
                    }
                    List<ReceiptDetail> receiptDetailList = taxInvoiceDetail.getInvoiceDetail().getReceiptDetails();
                    for(int k=0;k<receiptDetailList.size();k++){
                        ReceiptDetail receiptDetail = new ReceiptDetail();
                        receiptDetail = receiptDetailList.get(k);
                        String receiptNo = "";
                        if(receiptDetail.getReceipt() != null){
                            receiptNo = receiptDetail.getReceipt().getRecNo();
                            if(recNoChkList.isEmpty()){
                                recNoChkList.add(receiptNo);
                                totalReceiptNo += receiptNo;
                            } else {
                                int matchRec = 0;
                                recNoChkList.add(receiptNo);
                                for(int b=0;b<recNoChkList.size();b++){
                                    String recNoChk = recNoChkList.get(b);
                                    if(receiptNo.equalsIgnoreCase(recNoChk)){
                                        matchRec++;
                                        b = recNoChkList.size();
                                    }
                                }
                                if(matchRec == 0){
                                    totalReceiptNo += receiptNo;           
                                }
                            }                                                            
                        }                       
                    }                
                }                           
            }
            taxInvoiceView.setTotalAmount(totalAmount.setScale(2, RoundingMode.HALF_UP));
            taxInvoiceView.setTotalGross(totalGross.setScale(2, RoundingMode.HALF_UP));
            taxInvoiceView.setTotalVat(totalVat.setScale(2, RoundingMode.HALF_UP));           
            taxInvoiceView.setInvoiceNo(totalInvoiceNo);
            taxInvoiceView.setReceiptNo(totalReceiptNo);            
            taxInvoiceViewList.add(taxInvoiceView);
        }
        
        return taxInvoiceViewList;
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
    

    @Override
    public TaxInvoice getTaxInvoiceByTaxNo(String taxNo) {
        Session session = this.sessionFactory.openSession();
        TaxInvoice taxInvoice = new TaxInvoice();
        String query = "FROM TaxInvoice t where t.taxNo = :TaxInvNo";
        List<TaxInvoice> taxInvoiceList = session.createQuery(query).setParameter("TaxInvNo", taxNo).list();
        if(taxInvoiceList.isEmpty()){
            return null;
        } 
        
        taxInvoice = taxInvoiceList.get(0);
               
        return taxInvoice;
    }

    @Override
    public List<TaxInvoiceDetail> getTaxInvoiceDetailFromInvDetailId(String invDetailId) {
        Session session = this.sessionFactory.openSession();
        String query = " from TaxInvoiceDetail t WHERE t.invoiceDetail.id = :invDetailId and t.taxInvoice.MFinanceItemstatus.name = 'NORMAL'";
        List<TaxInvoiceDetail> taxInvoiceDetailList = session.createQuery(query).setParameter("invDetailId", invDetailId).list();
        if(taxInvoiceDetailList.isEmpty()){
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return taxInvoiceDetailList;
    }

    @Override
    public String checkInvoiceDetailValue(String id, BigDecimal cost, BigDecimal amount) {
        String result = "";
        Session session = this.sessionFactory.openSession();
        String query = " from InvoiceDetail i WHERE t.invoiceDetail.id = :invDetailId";
        
        return result;
    }

    @Override
    public String checkCreditNote(String id) {
        String result = "";
        System.out.println("Tax Invoice Id : "+id);
        Session session = this.sessionFactory.openSession();
        String query = " from CreditNoteDetail c WHERE c.taxInvoice.id = :taxInvId and c.creditNote.MFinanceItemstatus.name = 'NORMAL'";
        List<CreditNoteDetail> creditNoteDetailList = session.createQuery(query).setParameter("taxInvId", id).list();
        if(creditNoteDetailList.isEmpty()){
            result = "success";
            return result;
        }       
        
        List<String> cnNoChkList = new ArrayList<String>();
        for(int i=0;i<creditNoteDetailList.size();i++){
            int match = 0;
            CreditNoteDetail creditNoteDetail = new CreditNoteDetail();
            creditNoteDetail = creditNoteDetailList.get(i);
            String cnNo1 = creditNoteDetail.getCreditNote().getCnNo();
            System.out.println("cnNo1 : "+cnNo1);
            if(!cnNoChkList.isEmpty()){
                for(int j=0;j<cnNoChkList.size();j++){
                    String cnNo2 = cnNoChkList.get(j);
                    if(cnNo1.equalsIgnoreCase(cnNo2)){
                        match++;
                        j = cnNoChkList.size();
                    }
                }
                if(match == 0){
                    result += ",";
                    result += cnNo1;
                    cnNoChkList.add(cnNo1);
                }
            } else {
                result = cnNo1;
                cnNoChkList.add(cnNo1);
            }          
        }
        session.close();
        this.sessionFactory.close();
        System.out.println("Result : "+result);
        return result;
    }

    @Override
    public List<TaxInvoiceDetail> getTaxInvoiceDetailFromBillDescId(String invoiceDetailId) {
        Session session = this.sessionFactory.openSession();
        List<TaxInvoiceDetail> list = session.createQuery("from TaxInvoiceDetail tax WHERE tax.invoiceDetail.id = :invoiceDetailId and tax.taxInvoice.MFinanceItemstatus.id = 1")
                .setParameter("invoiceDetailId", invoiceDetailId)
                .list();
        if(list.isEmpty()){
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return list;
    }
}
