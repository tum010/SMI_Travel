/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.report.model.BillAirAgent;
import com.smi.travel.datalayer.report.model.BillAirAgentReport;
import com.smi.travel.datalayer.report.model.TicketFareReport;
import com.smi.travel.datalayer.view.dao.BillAirAgentDao;
import com.smi.travel.datalayer.view.entity.BillAirAgentRefund;
import com.smi.travel.datalayer.view.entity.ListBillAirAgent;
import com.smi.travel.util.UtilityFunction;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Kanokporn
 */
public class BillAirAgentImpl implements BillAirAgentDao{
    private SessionFactory sessionFactory;
    private UtilityFunction utilityFunction;
    @Override
    public List getBillAirAgentReport() {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList<BillAirAgent>();
        String query = "SELECT * FROM `bill_air_agent`";
        
        List<Object[]> QueryList =  session.createSQLQuery(query)
                .addScalar("agentname",Hibernate.STRING)
                .addScalar("agentid",Hibernate.STRING)
                .addScalar("invdate",Hibernate.STRING)
                .addScalar("invno",Hibernate.STRING)
                .addScalar("refunddate",Hibernate.STRING)
                .addScalar("issuedate",Hibernate.STRING)
                .addScalar("paymenttype",Hibernate.STRING)
                .addScalar("department",Hibernate.STRING)
                .addScalar("owner",Hibernate.STRING)
                .addScalar("customer",Hibernate.STRING)
                .addScalar("ticketno",Hibernate.STRING)
                .addScalar("rounting",Hibernate.STRING)
                .addScalar("saleprice",Hibernate.STRING)
                .addScalar("net",Hibernate.STRING)
                .addScalar("service",Hibernate.STRING)
                .addScalar("servicevat",Hibernate.STRING)
                .addScalar("compay",Hibernate.STRING)
                .addScalar("compayvat",Hibernate.STRING)
                .addScalar("amountair",Hibernate.STRING)
                .addScalar("receive",Hibernate.STRING)
                .list();
        for (Object[] B : QueryList) {
            BillAirAgent bil = new BillAirAgent();
            bil.setAgentname(util.ConvertString(B[0]));
            bil.setAgentid(util.ConvertString(B[1]));
            bil.setInvdate(util.ConvertString(B[2]));
            bil.setInvno(util.ConvertString(B[3]));
            bil.setRefunddate(util.ConvertString(B[4]));
            bil.setIssuedate(util.ConvertString(B[5]));
            bil.setPaymenttype(util.ConvertString(B[6]));
            bil.setDepartment(util.ConvertString(B[7]));
            bil.setOwner(util.ConvertString(B[8]));
            bil.setCustomer(util.ConvertString(B[9]));
            bil.setTicketno(util.ConvertString(B[10]));
            bil.setRounting(util.ConvertString(B[11]));
            if(util.ConvertString(B[12]) != null && !"".equals(util.ConvertString(B[12]))){
                bil.setSaleprice(util.ConvertString(B[12]));
            }else{
                bil.setSaleprice("0.00");
            }
            if(util.ConvertString(B[13]) != null && !"".equals(util.ConvertString(B[13]))){
                bil.setNet(util.ConvertString(B[13]));
            }else{
                bil.setNet("0.00");
            }
            if(util.ConvertString(B[14]) != null && !"".equals(util.ConvertString(B[14]))){
                bil.setService(util.ConvertString(B[14]));
            }else{
                bil.setService("0.00");
            }
            if(util.ConvertString(B[15]) != null && !"".equals(util.ConvertString(B[15]))){
                bil.setServicevat(util.ConvertString(B[15]));
            }else{
                bil.setServicevat("0.00");
            }
            if(util.ConvertString(B[16]) != null && !"".equals(util.ConvertString(B[16]))){
                bil.setCompay(util.ConvertString(B[16]));
            }else{
                bil.setCompay("0.00");
            }
            if(util.ConvertString(B[17]) != null && !"".equals(util.ConvertString(B[17]))){
                bil.setCompayvat(util.ConvertString(B[17]));
            }else{
                bil.setCompayvat("0.00");
            }
            if(util.ConvertString(B[18]) != null && !"".equals(util.ConvertString(B[18]))){
                bil.setAmountair(util.ConvertString(B[18]));
            }else{
                bil.setAmountair("0.00");
            }
            if(util.ConvertString(B[19]) != null && !"".equals(util.ConvertString(B[19]))){
                bil.setReceive(util.ConvertString(B[19]));
            }else{
                bil.setReceive("0.00");
            }
            data.add(bil);
        }
        return data;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public UtilityFunction getUtilityFunction() {
        return utilityFunction;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setUtilityFunction(UtilityFunction utilityFunction) {
        this.utilityFunction = utilityFunction;
    }

    @Override
    public List getBillAirAgentReportSummary(String agentCode,String invoiceFromDate,String InvoiceToDate,String issueFrom,String issueTo,String refundFrom,String refundTo,String department,String salebyUser,String termPay,String printby,String paymentType,String vat,String wht) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List<ListBillAirAgent> data = new ArrayList<ListBillAirAgent>();
        ListBillAirAgent listBillAirAgent = new ListBillAirAgent();
        List dataAgent = new ArrayList<BillAirAgent>();
        List dataRefund = new ArrayList<BillAirAgentRefund>();
        String paymentTypeTemp = "";
        
        if("B".equalsIgnoreCase(paymentType)){
            paymentTypeTemp = "BSP";
        }else if("D".equalsIgnoreCase(paymentType)){
            paymentTypeTemp = "DOMESTIC";
        }else if("A".equalsIgnoreCase(paymentType)){
            paymentTypeTemp = "AGENT";
        }else if("TI".equalsIgnoreCase(paymentType)){
            paymentTypeTemp = "TG INTER";
        }else if("TD".equalsIgnoreCase(paymentType)){
            paymentTypeTemp = "TG DOMESTIC";
        }
        
        String query = "";
        String query2 = "";
        int checkQuery = 0;
        int checkQuery2 = 0;
        if( agentCode == null  && invoiceFromDate == null  && InvoiceToDate== null  && issueFrom == null  && issueTo == null  && department == null && paymentType == null && refundFrom == null && refundTo == null ){
            query = "SELECT * FROM `bill_air_agent`  invm   ";
            query2 = "SELECT * FROM `bill_air_refund`  invm   ";
        }else{
            query = "SELECT * FROM `bill_air_agent`  invm  Where ";
            query2 = "SELECT * FROM `bill_air_refund`  invm  Where  ";
        }
        
        if ((invoiceFromDate != null )&&(!"".equalsIgnoreCase(invoiceFromDate))) {
            if ((InvoiceToDate != null )&&(!"".equalsIgnoreCase(InvoiceToDate))) {
                if(checkQuery == 1){
                     query += " and invm.invdate  BETWEEN  '" + invoiceFromDate + "' AND '" + InvoiceToDate + "' ";
                }else{
                    checkQuery = 1;
                     query += " invm.invdate  BETWEEN  '" + invoiceFromDate + "' AND '" + InvoiceToDate + "' ";
                }
            }
        }
        
      
        if ((issueFrom != null )&&(!"".equalsIgnoreCase(issueFrom))) {
            if ((issueTo != null )&&(!"".equalsIgnoreCase(issueTo))) {
                if(checkQuery == 1){
                     query += " and invm.issuedate  BETWEEN  '" + issueFrom + "' AND '" + issueTo + "' ";
                }else{
                    checkQuery = 1;
                     query += " invm.issuedate  BETWEEN  '" + issueFrom + "' AND '" + issueTo + "' ";
                }
            }
        }
      
        
        if ((agentCode != null )&&(!"".equalsIgnoreCase(agentCode))) {
            if(checkQuery == 1){
                 query += " and invm.agentid  = '" + agentCode + "' ";
            }else{
                checkQuery = 1;
                 query += " invm.agentid  = '" + agentCode + "' ";
            }
        }
        
        if ((department != null )&&(!"".equalsIgnoreCase(department))) {
            if(checkQuery == 1){
                 query += " and invm.department  = '" + department + "' ";
            }else{
                checkQuery = 1;
                 query += " invm.department  = '" + department + "' ";
            }
        }
        
           
        if ((refundFrom != null )&&(!"".equalsIgnoreCase(refundFrom))) {
            if ((refundTo != null )&&(!"".equalsIgnoreCase(refundTo))) {
                if(checkQuery2 == 1){
                     query2 += " and invm.refund_receive_date  BETWEEN  '" + refundFrom + "' AND '" + refundTo + "' ";
                }else{
                    checkQuery2 = 1;
                     query2 += " invm.refund_receive_date  BETWEEN  '" + refundFrom + "' AND '" + refundTo + "' ";
                }
            }
        }
        
        if ((issueFrom != null )&&(!"".equalsIgnoreCase(issueFrom))) {
            if ((issueTo != null )&&(!"".equalsIgnoreCase(issueTo))) {
                if(checkQuery2 == 1){
                     query2 += " and invm.issuedate  BETWEEN  '" + issueFrom + "' AND '" + issueTo + "' ";
                }else{
                    checkQuery2 = 1;
                     query2 += " invm.issuedate  BETWEEN  '" + issueFrom + "' AND '" + issueTo + "' ";
                }
            }
        }
      
        
        if ((agentCode != null )&&(!"".equalsIgnoreCase(agentCode))) {
            if(checkQuery2 == 1){
                 query2 += " and invm.agent_id  = '" + agentCode + "' ";
            }else{
                checkQuery2 = 1;
                 query2 += " invm.agent_id  = '" + agentCode + "' ";
            }
        }
        
        if ((department != null )&&(!"".equalsIgnoreCase(department))) {
            if(checkQuery2 == 1){
                 query2 += " and invm.department  = '" + department + "' ";
            }else{
                checkQuery2 = 1;
                 query2 += " invm.department  = '" + department + "' ";
            }
        }
        
        if ((termPay != null )&&(!"".equalsIgnoreCase(termPay))) {
            if(checkQuery2 == 1){
                 query2 += " and invm.term_pay  = '" + termPay + "' ";
            }else{
                checkQuery2 = 1;
                 query2 += " invm.term_pay  = '" + termPay + "' ";
            }
        }
        
        if ((paymentType != null )&&(!"".equalsIgnoreCase(paymentType))) {
            if(checkQuery2 == 1){
                 query2 += " and invm.payment_type  = '" + paymentType + "' ";
            }else{
                checkQuery2 = 1;
                 query2 += " invm.payment_type  = '" + paymentType + "' ";
            }
        }
       
        List<Object[]> QueryList =  session.createSQLQuery(query)
                .addScalar("agentname",Hibernate.STRING)
                .addScalar("agentid",Hibernate.STRING)
                .addScalar("invdate",Hibernate.STRING)
                .addScalar("invno",Hibernate.STRING)
                .addScalar("refunddate",Hibernate.STRING)
                .addScalar("issuedate",Hibernate.STRING)
                .addScalar("paymenttype",Hibernate.STRING)
                .addScalar("department",Hibernate.STRING)
                .addScalar("owner",Hibernate.STRING)
                .addScalar("customer",Hibernate.STRING)
                .addScalar("ticketno",Hibernate.STRING)
                .addScalar("rounting",Hibernate.STRING)
                .addScalar("saleprice",Hibernate.STRING)
                .addScalar("net",Hibernate.STRING)
                .addScalar("service",Hibernate.STRING)
                .addScalar("servicevat",Hibernate.STRING)
                .addScalar("compay",Hibernate.STRING)
                .addScalar("compayvat",Hibernate.STRING)
                .addScalar("amountair",Hibernate.STRING)
                .addScalar("receive",Hibernate.STRING)
                .addScalar("agentcom",Hibernate.STRING)
                .addScalar("agentcomrefund",Hibernate.STRING)
                .addScalar("paycusrefund",Hibernate.STRING)
                .list();
        for (Object[] B : QueryList) {
            BillAirAgent bil = new BillAirAgent();
            //header
          
            if(agentCode != null && !"".equals(agentCode)){
                bil.setAgentPage(agentCode);
            }else{
                bil.setAgentPage("");
            }
            if(issueFrom != null && !"".equals(issueFrom)){
                String issue = "" + issueFrom + " To " + issueTo;
                bil.setIssuedatePage(issue);
            }else{
                bil.setIssuedatePage("");
            }
            System.out.println("Invoice Date : " + invoiceFromDate);
            if(invoiceFromDate != null && !"".equals(invoiceFromDate)){
                String invoice = ""+ invoiceFromDate + " To " + InvoiceToDate;
                bil.setInvoicedatePage(invoice);
            }else{
                bil.setInvoicedatePage("");
            }
            bil.setPrintbyPage(printby);
            
            bil.setPaymenttypePage(paymentTypeTemp);
            
            bil.setAgentname(util.ConvertString(B[0]));
            bil.setAgentid(util.ConvertString(B[1]));
            bil.setInvdate(util.ConvertString(B[2]));
            bil.setInvno(util.ConvertString(B[3]));
            bil.setRefunddate(util.ConvertString(B[4]));
            bil.setIssuedate(util.ConvertString(B[5]));
            bil.setPaymenttype(util.ConvertString(B[6]));
            bil.setDepartment(util.ConvertString(B[7]));
            bil.setOwner(util.ConvertString(B[8]));
            bil.setCustomer(util.ConvertString(B[9]));
            bil.setTicketno(util.ConvertString(B[10]));
            bil.setRounting(util.ConvertString(B[11]));
            if(util.ConvertString(B[12]) != null && !"".equals(util.ConvertString(B[12]))){
                bil.setSaleprice(util.ConvertString(B[12]));
            }else{
                bil.setSaleprice("0.00");
            }
            if(util.ConvertString(B[13]) != null && !"".equals(util.ConvertString(B[13]))){
                bil.setNet(util.ConvertString(B[13]));
            }else{
                bil.setNet("0.00");
            }
            if(util.ConvertString(B[14]) != null && !"".equals(util.ConvertString(B[14]))){
                bil.setService(util.ConvertString(B[14]));
            }else{
                bil.setService("0.00");
            }
            if(util.ConvertString(B[15]) != null && !"".equals(util.ConvertString(B[15]))){
                bil.setServicevat(util.ConvertString(B[15]));
            }else{
                bil.setServicevat("0.00");
            }
            if(util.ConvertString(B[16]) != null && !"".equals(util.ConvertString(B[16]))){
                bil.setCompay(util.ConvertString(B[16]));
            }else{
                bil.setCompay("0.00");
            }
            if(util.ConvertString(B[17]) != null && !"".equals(util.ConvertString(B[17]))){
                bil.setCompayvat(util.ConvertString(B[17]));
            }else{
                bil.setCompayvat("0.00");
            }
            if(util.ConvertString(B[18]) != null && !"".equals(util.ConvertString(B[18]))){
                bil.setAmountair(util.ConvertString(B[18]));
            }else{
                bil.setAmountair("0.00");
            }
            if(util.ConvertString(B[19]) != null && !"".equals(util.ConvertString(B[19]))){
                bil.setReceive(util.ConvertString(B[19]));
            }else{
                bil.setReceive("0.00");
            }
            if(util.ConvertString(B[20]) != null && !"".equals(util.ConvertString(B[20]))){
                bil.setAgentcom(util.ConvertString(B[20]));
            }else{
                bil.setAgentcom("0.00");
            }
            if(util.ConvertString(B[21]) != null && !"".equals(util.ConvertString(B[21]))){
                bil.setAgentcomrefund(util.ConvertString(B[21]));
            }else{
                bil.setAgentcomrefund("0.00");
            }
            if(util.ConvertString(B[22]) != null && !"".equals(util.ConvertString(B[22]))){
                bil.setPaycusrefund(util.ConvertString(B[22]));
            }else{
                bil.setPaycusrefund("0.00");
            }
            System.out.println("Temp Vat : " +  vat  + " Wht : " + wht);
            bil.setVattemp(vat);
            bil.setWhttemp(wht);
            dataAgent.add(bil);
        }
        
        List<Object[]> QueryList2 =  session.createSQLQuery(query2)
                .addScalar("refundno",Hibernate.STRING)
                .addScalar("receivedate",Hibernate.STRING)
                .addScalar("passenger",Hibernate.STRING)
                .addScalar("air",Hibernate.STRING)
                .addScalar("docno",Hibernate.STRING)
                .addScalar("refno",Hibernate.STRING)
                .addScalar("amount_receive",Hibernate.STRING)
                .addScalar("refundchange",Hibernate.STRING)
                .addScalar("amountpay",Hibernate.STRING)
                .addScalar("comm_rec",Hibernate.STRING)
                .addScalar("vat",Hibernate.STRING)
                .addScalar("refund_receive_date",Hibernate.STRING)
                .list();
        for (Object[] B : QueryList2) {
            BillAirAgentRefund bil = new BillAirAgentRefund();
            //header
          
            if(agentCode != null && !"".equals(agentCode)){
                bil.setAgentPage(agentCode);
            }else{
                bil.setAgentPage("");
            }
            if(issueFrom != null && !"".equals(issueFrom)){
                String issue = "" + issueFrom + " To " + issueTo;
                bil.setIssuedatePage(issue);
            }else{
                bil.setIssuedatePage("");
            }
            System.out.println("Invoice Date : " + invoiceFromDate);
            if(invoiceFromDate != null && !"".equals(invoiceFromDate)){
                String invoice = ""+ invoiceFromDate + " To " + InvoiceToDate;
                bil.setInvoicedatePage(invoice);
            }else{
                bil.setInvoicedatePage("");
            }
            bil.setPrintbyPage(printby);
            bil.setPaymenttypePage(paymentTypeTemp);
            
            bil.setRefundno(util.ConvertString(B[0]));
            bil.setReceivedate(util.ConvertString(B[1]));
            bil.setPassenger(util.ConvertString(B[2]));
            bil.setAir(util.ConvertString(B[3]));
            bil.setDocno(util.ConvertString(B[4]));
            bil.setRefno(util.ConvertString(B[5]));
       
            if(util.ConvertString(B[6]) != null && !"".equals(util.ConvertString(B[6]))){
                bil.setAmount_receive(util.ConvertString(B[6]));
            }else{
                bil.setAmount_receive("0.00");
            }
            if(util.ConvertString(B[7]) != null && !"".equals(util.ConvertString(B[7]))){
                bil.setRefundchange(util.ConvertString(B[7]));
            }else{
                bil.setRefundchange("0.00");
            }
            if(util.ConvertString(B[8]) != null && !"".equals(util.ConvertString(B[8]))){
                bil.setAmountpay(util.ConvertString(B[8]));
            }else{
                bil.setAmountpay("0.00");
            }
            if(util.ConvertString(B[9]) != null && !"".equals(util.ConvertString(B[9]))){
                bil.setComm_rec(util.ConvertString(B[9]));
            }else{
                bil.setComm_rec("0.00");
            }
            if(util.ConvertString(B[10]) != null && !"".equals(util.ConvertString(B[10]))){
                bil.setVat(util.ConvertString(B[10]));
            }else{
                bil.setVat("0.00");
            }
            dataRefund.add(bil);
        }
        
        listBillAirAgent.setBillAirAgent(dataAgent);
        listBillAirAgent.setBillAirAgentRefund(dataRefund);
        data.add(listBillAirAgent);
        
        List<BillAirAgent> list2 = data.get(0).getBillAirAgent();
        for (int i = 0; i < list2.size(); i++) {
            System.out.println("Vat : " + list2.get(i).getVattemp() + " Wht : " + list2.get(i).getWhttemp());
        }
        return data;
    }

    @Override
    public BillAirAgentReport getBillAirAgentReport(String agentCode, String invoiceFromDate, String InvoiceToDate, String issueFrom, String issueTo, String refundFrom, String refundTo, String department, String salebyUser, String termPay, String printby, String paymentType, String vat, String wht) {
        BillAirAgentReport billAirAgentReport = new BillAirAgentReport();
        billAirAgentReport.setBillAirAgentSummaryDataSource(new JRBeanCollectionDataSource(getBillAirAgentSummaryReport(agentCode, invoiceFromDate, InvoiceToDate, issueFrom, issueTo, refundFrom, refundTo, department, salebyUser, termPay, printby, paymentType, vat, wht)));
        billAirAgentReport.setBillAirAgentDetailDataSource(new JRBeanCollectionDataSource(getBillAirAgentDetailReport(agentCode, invoiceFromDate, InvoiceToDate, issueFrom, issueTo, refundFrom, refundTo, department, salebyUser, termPay, printby, paymentType, vat, wht)));
         billAirAgentReport.setBillAirAgentRefundDataSource(new JRBeanCollectionDataSource(getBillAirAgentRefundReport(agentCode, invoiceFromDate, InvoiceToDate, issueFrom, issueTo, refundFrom, refundTo, department, salebyUser, termPay, printby, paymentType, vat, wht)));
        return billAirAgentReport;
    }
    
    private List getBillAirAgentSummaryReport(String agentCode, String invoiceFromDate, String InvoiceToDate, String issueFrom, String issueTo, String refundFrom, String refundTo, String department, String salebyUser, String termPay, String printby, String paymentType, String vat, String wht){
        Session session = this.sessionFactory.openSession();
        List data = new ArrayList();
        Date thisdate = new Date();
        UtilityFunction util = new UtilityFunction();
        
        return data;
    }
    
    private List getBillAirAgentDetailReport(String agentCode, String invoiceFromDate, String InvoiceToDate, String issueFrom, String issueTo, String refundFrom, String refundTo, String department, String salebyUser, String termPay, String printby, String paymentType, String vat, String wht){
        Session session = this.sessionFactory.openSession();
        List data = new ArrayList();
        Date thisdate = new Date();
        UtilityFunction util = new UtilityFunction();
        
        return data;
    }
    
    private List getBillAirAgentRefundReport(String agentCode, String invoiceFromDate, String InvoiceToDate, String issueFrom, String issueTo, String refundFrom, String refundTo, String department, String salebyUser, String termPay, String printby, String paymentType, String vat, String wht){
        Session session = this.sessionFactory.openSession();
        List data = new ArrayList();
        Date thisdate = new Date();
        UtilityFunction util = new UtilityFunction();
        
        return data;
    }
    
}
