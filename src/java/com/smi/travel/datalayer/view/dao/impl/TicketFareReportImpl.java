/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.view.dao.TicketFareReportDao;

import com.smi.travel.datalayer.report.model.TicketFareReport;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author chonnasith
 */
public class TicketFareReportImpl implements TicketFareReportDao {
    private SessionFactory sessionFactory;
    private UtilityFunction utilityFunction;
    
    @Override
    public List getTicketFareReport() {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList<TicketFareReport>();
        List<Object[]> QueryList =  session.createSQLQuery("select * from `ticket_fare_airline_view` ")
                .addScalar("air",Hibernate.STRING)
                .addScalar("docno",Hibernate.STRING)
                .addScalar("issuedate",Hibernate.STRING)
                .addScalar("department",Hibernate.STRING)
                .addScalar("staff",Hibernate.STRING)
                .addScalar("termpay",Hibernate.STRING)
                .addScalar("tax",Hibernate.STRING)
                .addScalar("actcom",Hibernate.STRING)
                .addScalar("ins",Hibernate.STRING)
                .addScalar("netsale",Hibernate.STRING)
                .addScalar("vat",Hibernate.STRING)
                .addScalar("invno",Hibernate.STRING)
                .addScalar("invamount",Hibernate.STRING)
                .addScalar("balance",Hibernate.STRING)
                .addScalar("passenger",Hibernate.STRING)
                .addScalar("agent",Hibernate.STRING)
                .addScalar("ticketcom",Hibernate.STRING)
                .addScalar("saleprice",Hibernate.STRING)
                .addScalar("agentcom",Hibernate.STRING)
                .addScalar("profit",Hibernate.STRING)
                .list();
        
        for (Object[] B : QueryList) {
            TicketFareReport ticketFareReport = new TicketFareReport();
            ticketFareReport.setAir(util.ConvertString(B[0]));
            ticketFareReport.setDocno(util.ConvertString(B[1]));
            ticketFareReport.setIssuedate(util.ConvertString(B[2]));
            ticketFareReport.setDepartment(util.ConvertString(B[3]));
            ticketFareReport.setStaff(util.ConvertString(B[4]));
            ticketFareReport.setTermpay(util.ConvertString(B[5]));
            ticketFareReport.setTax(util.ConvertString(B[6]));
            ticketFareReport.setActcom(util.ConvertString(B[7]));
            ticketFareReport.setIns(util.ConvertString(B[8]));
            ticketFareReport.setNetsale(util.ConvertString(B[9]));
            ticketFareReport.setVat(util.ConvertString(B[10]));
            ticketFareReport.setInvno(util.ConvertString(B[11]));
            ticketFareReport.setInvamount(util.ConvertString(B[12]));
            ticketFareReport.setBalance(util.ConvertString(B[13]));
            ticketFareReport.setPassenger(util.ConvertString(B[14]));
            ticketFareReport.setAgent(util.ConvertString(B[15]));
            ticketFareReport.setTicketcom(util.ConvertString(B[16]));
            ticketFareReport.setSaleprice(util.ConvertString(B[17]));
            ticketFareReport.setAgentcom(util.ConvertString(B[18]));
            ticketFareReport.setProfit(util.ConvertString(B[19]));
            data.add(ticketFareReport);
        }
        
        return data;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public UtilityFunction getUtilityFunction() {
        return utilityFunction;
    }

    public void setUtilityFunction(UtilityFunction utilityFunction) {
        this.utilityFunction = utilityFunction;
    }
   
}
