/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.report.model.BillAirAgent;
import com.smi.travel.datalayer.report.model.BillAirAgentDetailReport;
import com.smi.travel.datalayer.report.model.BillAirAgentRefundReport;
import com.smi.travel.datalayer.report.model.BillAirAgentReport;
import com.smi.travel.datalayer.report.model.BillAirAgentSummaryReport;
import com.smi.travel.datalayer.view.dao.BillAirAgentDao;
import com.smi.travel.datalayer.view.entity.BillAirAgentRefund;
import com.smi.travel.datalayer.view.entity.ListBillAirAgent;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
        SimpleDateFormat sf = new SimpleDateFormat();
        sf.applyPattern("dd-MM-yyyy");
        
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
        }else{
            paymentTypeTemp = "ALL";
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
                bil.setAgentPage(util.ConvertString(B[0]));
            }else{
                bil.setAgentPage("ALL");
            }
            if(issueFrom != null && !"".equals(issueFrom)){
                String issue = "" + String.valueOf(sf.format(util.convertStringToDate(issueFrom)))  + " To " + String.valueOf(sf.format(util.convertStringToDate(issueTo))) ;
                bil.setIssuedatePage(issue);
            }else{
                bil.setIssuedatePage("ALL");
            }
            System.out.println("Invoice Date : " + invoiceFromDate);
            if(invoiceFromDate != null && !"".equals(invoiceFromDate)){
                String invoice = ""+ String.valueOf(sf.format(util.convertStringToDate(invoiceFromDate)))  + " To " + String.valueOf(sf.format(util.convertStringToDate(InvoiceToDate))) ;
                bil.setInvoicedatePage(invoice);
            }else{
                bil.setInvoicedatePage("ALL");
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
                .addScalar("agent_name",Hibernate.STRING)
                .list();
        for (Object[] B : QueryList2) {
            BillAirAgentRefund bil = new BillAirAgentRefund();
            //header
          
            if(agentCode != null && !"".equals(agentCode)){
                bil.setAgentPage(util.ConvertString(B[12]));
            }else{
                bil.setAgentPage("ALL");
            }
            if(issueFrom != null && !"".equals(issueFrom)){
                String issue = "" + String.valueOf(sf.format(util.convertStringToDate(issueFrom)))  + " To " + String.valueOf(sf.format(util.convertStringToDate(issueTo)));
                bil.setIssuedatePage(issue);
            }else{
                bil.setIssuedatePage("");
            }
            System.out.println("Invoice Date : " + invoiceFromDate);
            if(invoiceFromDate != null && !"".equals(invoiceFromDate)){
                String invoice = ""+ String.valueOf(sf.format(util.convertStringToDate(invoiceFromDate)))  + " To " +String.valueOf(sf.format(util.convertStringToDate(InvoiceToDate)));
                bil.setInvoicedatePage(invoice);
            }else{
                bil.setInvoicedatePage("");
            }
            
            if(refundFrom != null && !"".equals(refundTo)){
                String refund = ""+ String.valueOf(sf.format(util.convertStringToDate(refundFrom)))  + " To " + String.valueOf(sf.format(util.convertStringToDate(refundTo)));
                bil.setRefunddatePage(refund);
            }else{
                bil.setRefunddatePage("");
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
    public BillAirAgentReport getBillAirAgentReportPdf(String agentCode, String invoiceFromDate, String InvoiceToDate, String issueFrom, String issueTo, String refundFrom, String refundTo, String department, String salebyUser, String termPay, String printby, String paymentType, String vat, String wht) {
        BillAirAgentReport billAirAgentReport = new BillAirAgentReport();
        List<ListBillAirAgent> data = new ArrayList<ListBillAirAgent>();
        data = getBillAirAgentReportSummary(agentCode, invoiceFromDate, InvoiceToDate, issueFrom, issueTo, refundFrom, refundTo, department, salebyUser, termPay, printby, paymentType, vat, wht);
        
        billAirAgentReport.setAgenthead(agentCode);
        billAirAgentReport.setIssuedatehead(issueFrom + " - " + issueTo );
        billAirAgentReport.setInvoicedatehead(invoiceFromDate + " - " + InvoiceToDate);
        billAirAgentReport.setPaymenttypehead(refundFrom + " - " + refundTo);
        billAirAgentReport.setPrintby(printby);
        billAirAgentReport.setSystemdate(new SimpleDateFormat("dd MMM yy hh:mm", new Locale("us", "us")).format(new Date()));
        billAirAgentReport.setBillAirAgentSummaryDataSource(new JRBeanCollectionDataSource(getBillAirAgentSummaryReport(data,agentCode, invoiceFromDate, InvoiceToDate, issueFrom, issueTo, refundFrom, refundTo, department, salebyUser, termPay, printby, paymentType, vat, wht)));
        billAirAgentReport.setBillAirAgentDetailDataSource(new JRBeanCollectionDataSource(getBillAirAgentDetailReport(data,agentCode, invoiceFromDate, InvoiceToDate, issueFrom, issueTo, refundFrom, refundTo, department, salebyUser, termPay, printby, paymentType, vat, wht)));
        billAirAgentReport.setBillAirAgentRefundDataSource(new JRBeanCollectionDataSource(getBillAirAgentRefundReport(data,agentCode, invoiceFromDate, InvoiceToDate, issueFrom, issueTo, refundFrom, refundTo, department, salebyUser, termPay, printby, paymentType, vat, wht)));
        return billAirAgentReport;
    }
    
    private List getBillAirAgentSummaryReport(List<ListBillAirAgent> dataTemp,String agentCode, String invoiceFromDate, String InvoiceToDate, String issueFrom, String issueTo, String refundFrom, String refundTo, String department, String salebyUser, String termPay, String printby, String paymentType, String vat, String wht){
        List<BillAirAgentSummaryReport> data = new ArrayList<BillAirAgentSummaryReport>();
        List<BillAirAgent> listAgent = new ArrayList<BillAirAgent>();
        List<BillAirAgentRefund> listAgentRefund = new ArrayList<BillAirAgentRefund>();
        if(dataTemp != null && dataTemp.size() != 0){
            listAgent = dataTemp.get(0).getBillAirAgent();
            listAgentRefund = dataTemp.get(0).getBillAirAgentRefund();
        }else{
            listAgent = null;
            listAgentRefund = null;
        }
        
        if(listAgent != null && listAgent.size() != 0){
        BigDecimal sumSalePrice = new BigDecimal(0);
        BigDecimal sumAmountAir = new BigDecimal(0);
        BigDecimal sumComPay =  new BigDecimal(0);
        BigDecimal sumComReceive =  new BigDecimal(0);
        BigDecimal sumTotalComRefundReceive =  new BigDecimal(0);
        BigDecimal sumTotalPayment =  new BigDecimal(0);
        BigDecimal sumTotalCompay =  new BigDecimal(0);
        BigDecimal sumTotalCompaySub =  new BigDecimal(0);
        BigDecimal sumPayRefundAmount =  new BigDecimal(0);
        BigDecimal sumVatComPay =  new BigDecimal(0);
        BigDecimal SumVatReceive =  new BigDecimal(0);
        BigDecimal vatComPay =  new BigDecimal(0);
        BigDecimal vatPay =  new BigDecimal(0);
        BigDecimal vatReceive =  new BigDecimal(0);
        BigDecimal totalCom =  new BigDecimal(0);
        BigDecimal balancePayment =  new BigDecimal(0);
        BigDecimal checkResult =  new BigDecimal(0);
        BigDecimal midValue =  new BigDecimal(0);
        BigDecimal withHoldingTax =  new BigDecimal(0);
        String vatMDE = "";
        String whtMDE = "";
        for (int i = 0; i < listAgent.size(); i++) {
            sumSalePrice = sumSalePrice.add(new BigDecimal(listAgent.get(i).getSaleprice()));
            sumAmountAir = sumAmountAir.add(new BigDecimal(listAgent.get(i).getAmountair()));
            sumComPay = sumComPay.add(new BigDecimal(listAgent.get(i).getCompay()));
            sumVatComPay = sumVatComPay.add(new BigDecimal(listAgent.get(i).getCompayvat()));
            sumTotalComRefundReceive = sumTotalComRefundReceive.add(new BigDecimal(listAgent.get(i).getAgentcomrefund()));
            
            System.out.println("Sale Price : " + listAgent.get(i).getSaleprice() + "  Sum Sale Price : " + sumSalePrice);
            System.out.println("Amount Air : " + listAgent.get(i).getAmountair() + "  Sum Amount Air : " + sumAmountAir);
            System.out.println("Com Pay : " + listAgent.get(i).getCompay() + "  Sum Com Pay : " + sumComPay);
            System.out.println("Com Reefund Receive : " + listAgent.get(i).getAgentcomrefund() + "  Sum Reefund Receive : " + sumTotalComRefundReceive);
            System.out.println("Pay Refund Amount : " + listAgent.get(i).getPaycusrefund() + "  Sum Refund Amount : " + sumPayRefundAmount);
        }
        for (int i = 0; i < listAgentRefund.size(); i++) {
            sumComReceive = sumComReceive.add(new BigDecimal(listAgentRefund.get(i).getComm_rec()));
            sumPayRefundAmount = sumPayRefundAmount.add(new BigDecimal(listAgentRefund.get(i).getAmountpay()));
            SumVatReceive = SumVatReceive.add(new BigDecimal(listAgentRefund.get(i).getVat()));
        }
        vatMDE = listAgent.get(0).getVattemp();
        whtMDE = listAgent.get(0).getWhttemp();
        System.out.println("Vat : " + vatMDE + "Wht  :" + whtMDE);
        
        DecimalFormat df = new DecimalFormat("#,###.00");
        sumTotalPayment = sumSalePrice.add(sumComReceive);
        sumTotalCompay = sumComPay.subtract(sumComReceive);
        sumTotalCompaySub = sumComPay.multiply((BigDecimal.ZERO).subtract(BigDecimal.ONE));
        
        BigDecimal vatPa =  new BigDecimal(vatMDE );
        vatComPay = sumTotalCompay.multiply(vatPa);
        vatComPay = vatComPay.divide(new BigDecimal(100),MathContext.DECIMAL128);
        vatPay =  (vatComPay.add(SumVatReceive)).multiply((BigDecimal.ZERO).subtract(BigDecimal.ONE));
        
        totalCom = sumTotalCompaySub.add(sumComReceive);
        sumPayRefundAmount = sumPayRefundAmount.multiply((BigDecimal.ZERO).subtract(BigDecimal.ONE));
        
        balancePayment = sumTotalPayment.add(vatPay);
        balancePayment = balancePayment.add(SumVatReceive);
        balancePayment = balancePayment.subtract(sumPayRefundAmount.multiply((BigDecimal.ZERO).subtract(BigDecimal.ONE)));
        
        checkResult = sumTotalCompay.add(vatComPay);
        
        midValue =  checkResult.add(balancePayment);
        midValue = midValue.add(sumPayRefundAmount.multiply((BigDecimal.ZERO).subtract(BigDecimal.ONE)));
        
        withHoldingTax = sumTotalCompay.add(vatComPay);
        withHoldingTax = withHoldingTax.multiply(new BigDecimal(100));
        System.out.println("Vat :::: " + vatMDE + "Wht :::: " + whtMDE);
        BigDecimal vatTemp =  new BigDecimal(vatMDE );
        BigDecimal whtTemp =  new BigDecimal(whtMDE );
        vatTemp = vatTemp.add(new BigDecimal(100));
        whtTemp = whtTemp.divide(new BigDecimal(100),MathContext.DECIMAL128);
        withHoldingTax = withHoldingTax.divide(vatTemp,MathContext.DECIMAL128);
        withHoldingTax = withHoldingTax.multiply(whtTemp);
        
        BillAirAgentSummaryReport billAirAgentSummaryReport  =  new BillAirAgentSummaryReport();
        billAirAgentSummaryReport.setTotalsaleprice(sumSalePrice);
        billAirAgentSummaryReport.setTotalcomrefundreceive(sumComReceive);
        billAirAgentSummaryReport.setTotalpayment(sumTotalPayment);
        billAirAgentSummaryReport.setCompay(sumTotalCompaySub);
        billAirAgentSummaryReport.setComreceive(sumComReceive);
        billAirAgentSummaryReport.setTotalcom(totalCom);
        billAirAgentSummaryReport.setVatpay(vatPay);
        billAirAgentSummaryReport.setVatreceive(SumVatReceive);
        billAirAgentSummaryReport.setPayrefundamount(sumPayRefundAmount);
        billAirAgentSummaryReport.setBalancepayment(balancePayment);
        billAirAgentSummaryReport.setAmountairsale(sumAmountAir);
        billAirAgentSummaryReport.setWithholdingtax(withHoldingTax);
        billAirAgentSummaryReport.setCompaydivide(sumComPay);
        billAirAgentSummaryReport.setComreceivedivide(sumComReceive);
        billAirAgentSummaryReport.setTotalcompay(sumTotalCompay);
        billAirAgentSummaryReport.setVatcompay(vatComPay);
        billAirAgentSummaryReport.setMidvalue(midValue);
        billAirAgentSummaryReport.setCheckresult(checkResult);
        data.add(billAirAgentSummaryReport);
     }  
        return data;
    }
    
    private List getBillAirAgentDetailReport(List<ListBillAirAgent> dataTemp,String agentCode, String invoiceFromDate, String InvoiceToDate, String issueFrom, String issueTo, String refundFrom, String refundTo, String department, String salebyUser, String termPay, String printby, String paymentType, String vat, String wht){
        List<BillAirAgentDetailReport> data = new ArrayList<BillAirAgentDetailReport>();
        List<BillAirAgent> listAgent = new ArrayList<BillAirAgent>();
        if(dataTemp != null && dataTemp.size() != 0){
            listAgent = dataTemp.get(0).getBillAirAgent();
        }else{
            listAgent = null;
        }
        if(listAgent != null && listAgent.size() != 0){
            for (int i = 0; i < listAgent.size(); i++) {
                BillAirAgentDetailReport billAirAgentDetailReport = new BillAirAgentDetailReport();
                billAirAgentDetailReport.setAgentname(listAgent.get(i).getAgentname());
                billAirAgentDetailReport.setInvoiceno(listAgent.get(i).getInvno());
                billAirAgentDetailReport.setInvoicedate(listAgent.get(i).getInvdate());
                billAirAgentDetailReport.setCustomer(listAgent.get(i).getCustomer());
                billAirAgentDetailReport.setTicketno(listAgent.get(i).getTicketno());
                billAirAgentDetailReport.setRounting(listAgent.get(i).getRounting());
                billAirAgentDetailReport.setSaleprice(new BigDecimal(listAgent.get(i).getSaleprice()));
                billAirAgentDetailReport.setNet(new BigDecimal(listAgent.get(i).getNet()));
                billAirAgentDetailReport.setService(new BigDecimal(listAgent.get(i).getService()));
                billAirAgentDetailReport.setVatamount(new BigDecimal(listAgent.get(i).getServicevat()));
                billAirAgentDetailReport.setAmountair(new BigDecimal(listAgent.get(i).getAmountair()));
                billAirAgentDetailReport.setCompay(new BigDecimal(listAgent.get(i).getCompay()));
                billAirAgentDetailReport.setVatreceive(new BigDecimal(listAgent.get(i).getCompayvat()));
                billAirAgentDetailReport.setReceive(new BigDecimal(listAgent.get(i).getReceive()));
                data.add(billAirAgentDetailReport);
            }
        }
        return data;
    }
    
    private List getBillAirAgentRefundReport(List<ListBillAirAgent> dataTemp,String agentCode, String invoiceFromDate, String InvoiceToDate, String issueFrom, String issueTo, String refundFrom, String refundTo, String department, String salebyUser, String termPay, String printby, String paymentType, String vat, String wht){
        List<BillAirAgentRefundReport> data = new ArrayList<BillAirAgentRefundReport>();
        List<BillAirAgentRefund> listAgentRefund = new ArrayList<BillAirAgentRefund>();
        if(dataTemp != null && dataTemp.size() != 0){
            listAgentRefund = dataTemp.get(0).getBillAirAgentRefund();
        }else{
            listAgentRefund = null;
        }
        if(listAgentRefund != null && listAgentRefund.size() != 0){
            for (int i = 0; i < listAgentRefund.size(); i++) {
                BillAirAgentRefundReport billAirAgentRefundReport = new BillAirAgentRefundReport();
                billAirAgentRefundReport.setRefundno(listAgentRefund.get(i).getRefundno());
                billAirAgentRefundReport.setDatereceive(listAgentRefund.get(i).getReceivedate());
                billAirAgentRefundReport.setPassenger(listAgentRefund.get(i).getPassenger());
                billAirAgentRefundReport.setAir(listAgentRefund.get(i).getAir());
                billAirAgentRefundReport.setDocno(listAgentRefund.get(i).getDocno());
                billAirAgentRefundReport.setRefno(listAgentRefund.get(i).getRefno());
                billAirAgentRefundReport.setAmountreceive(new BigDecimal(listAgentRefund.get(i).getAmount_receive()));
                billAirAgentRefundReport.setRefundchange(new BigDecimal(listAgentRefund.get(i).getRefundchange()));
                billAirAgentRefundReport.setAmountpay(new BigDecimal(listAgentRefund.get(i).getAmountpay()));
                billAirAgentRefundReport.setCommrac(new BigDecimal(listAgentRefund.get(i).getComm_rec()));
                billAirAgentRefundReport.setVat(new BigDecimal(listAgentRefund.get(i).getVat()));
                data.add(billAirAgentRefundReport);
            }
        }
        return data;
    }
    
}
