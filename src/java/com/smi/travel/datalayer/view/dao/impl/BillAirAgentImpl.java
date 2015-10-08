/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.report.model.BillAirAgent;
import com.smi.travel.datalayer.report.model.TicketFareReport;
import com.smi.travel.datalayer.view.dao.BillAirAgentDao;
import com.smi.travel.util.UtilityFunction;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    public List getBillAirAgentReportSummary(String agentCode,String invoiceFromDate,String InvoiceToDate,String issueFrom,String issueTo,String refundFrom,String refundTo,String department,String salebyUser,String termPay,String printby) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList<BillAirAgent>();
        
        String query = "";
        int checkQuery = 0;
        if( !"".equals(agentCode)  || !"".equals(invoiceFromDate) || !"".equals(InvoiceToDate) || !"".equals(issueFrom) || !"".equals(issueTo)  || !"".equals(department)){
            query = "SELECT * FROM `bill_air_agent  invm  Where ";
        }else{
            query = "SELECT * FROM `bill_air_agent  invm ";
        }
        
        if ((invoiceFromDate != null )&&(!"".equalsIgnoreCase(InvoiceToDate))) {
            if ((InvoiceToDate != null )&&(!"".equalsIgnoreCase(InvoiceToDate))) {
                if(checkQuery == 1){
                     query += " and invm.invdate  BETWEEN  '" + invoiceFromDate + "' AND '" + InvoiceToDate + "' ";
                }else{
                    checkQuery = 1;
                     query += " invm.invdate  BETWEEN  '" + invoiceFromDate + "' AND '" + InvoiceToDate + "' ";
                }
            }
        }
        
        if ((issueFrom != null )&&(!"".equalsIgnoreCase(issueTo))) {
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
                 query += " and invm.agentid  = " + agentCode + "' ";
            }else{
                checkQuery = 1;
                 query += " invm.agentid  = " + agentCode + "' ";
            }
        }
        
        if ((department != null )&&(!"".equalsIgnoreCase(department))) {
            if(checkQuery == 1){
                 query += " and invm.department  = " + department + "' ";
            }else{
                checkQuery = 1;
                 query += " invm.department  = " + department + "' ";
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
            data.add(bil);
        }
        return data;
    }
    
}
