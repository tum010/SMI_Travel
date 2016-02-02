/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.report.model.RefundTicketSummaryReport;
import com.smi.travel.datalayer.report.model.TicketSummary;
import com.smi.travel.datalayer.report.model.TicketSummaryAirline;
import com.smi.travel.datalayer.report.model.TicketSummaryList;
import com.smi.travel.datalayer.view.dao.TicketSummaryDao;
import com.smi.travel.util.UtilityFunction;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.math.BigDecimal;
import java.math.MathContext;
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
 * @author Surachai
 */
public class TicketSummaryImpl implements TicketSummaryDao {
    
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public List getTicketSummary(String ticketfrom, String tickettype, String startdate, String enddate, String billto, String passenger,String username,String department) {
        System.out.println(" +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ ");
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        Date thisDate = new Date();
        List data = new ArrayList();
        String Query ="SELECT * FROM `airticket_ticket` ";
        Query += createTicketSummaryQuery(ticketfrom,tickettype,startdate,enddate,billto,passenger,department);
        System.out.println("Query : "+Query);
        int no = 0;
        List<Object[]> QueryTicketList = session.createSQLQuery(Query )
                .addScalar("air", Hibernate.STRING)
                .addScalar("ticket_no", Hibernate.STRING)
                .addScalar("passenger_name", Hibernate.STRING)
                .addScalar("bill_to", Hibernate.STRING)
                .addScalar("routing", Hibernate.STRING)
                .addScalar("sale_fare", Hibernate.STRING)
                .addScalar("net_fare", Hibernate.STRING)
                .addScalar("tax", Hibernate.STRING)
                .addScalar("profit", Hibernate.STRING)
                .addScalar("owner", Hibernate.STRING)
                .addScalar("ref_no", Hibernate.STRING)
                .addScalar("ticket_from", Hibernate.STRING)
                .addScalar("ticket_type", Hibernate.STRING)
                .addScalar("Create_date", Hibernate.DATE)
                .addScalar("ticket_date", Hibernate.DATE)
                .addScalar("invoice_no", Hibernate.STRING)
                .list();
                    SimpleDateFormat dateformat = new SimpleDateFormat();
            dateformat.applyPattern("dd-MM-yyyy");
        for (Object[] B : QueryTicketList) {
            TicketSummary sum = new TicketSummary();
            no +=1;
            sum.setNo(no);
            sum.setSystemdate(new SimpleDateFormat("dd MMM yyyy hh:mm:ss", new Locale("us", "us")).format(thisDate));
            sum.setUsername(username);
            
            sum.setStartdate(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(startdate)));
            sum.setEnddate(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(enddate)));
            sum.setAir(util.ConvertString(B[0]));
            sum.setTicketno(util.ConvertString(B[1]));
            sum.setPassengername(util.ConvertString(B[2]));
            sum.setBillto(util.ConvertString(B[3]));
            sum.setRouting(util.ConvertString(B[4]));
            sum.setSalefare(!"null".equalsIgnoreCase(String.valueOf(B[5])) ? util.ConvertString(B[5]) : "0.00");
            sum.setNetfare(!"null".equalsIgnoreCase(String.valueOf(B[6])) ? util.ConvertString(B[6]) : "0.00");
            sum.setTax(!"null".equalsIgnoreCase(String.valueOf(B[7])) ? util.ConvertString(B[7]) : "0.00");
            sum.setProfit(!"null".equalsIgnoreCase(String.valueOf(B[8])) ? util.ConvertString(B[8]) : "0.00");
            sum.setOwner(util.ConvertString(B[9]));
            sum.setRefno(util.ConvertString(B[10]));
            sum.setFrom(setDisplayValueTicketFrom(ticketfrom));
            sum.setType(setDisplayValueTicketType(tickettype));
            if(B[13] != null)
            sum.setCreatedate(util.convertStringToDate(B[13].toString()));
            sum.setTicketdate("null".equals(String.valueOf(B[14])) ? "" : util.ConvertString(dateformat.format(util.convertStringToDate(String.valueOf(B[14])))));
//            sum.setTicketdate(util.convertStringToDate(util.ConvertString(B[14])));
            sum.setInvoiceno(util.ConvertString(B[15]));
            data.add(sum);
            System.out.println("sum data :");
            
        }
        
        session.close();
        this.sessionFactory.close();
        return data;
    }
    
    
    private String setDisplayValueTicketType(String tickettype){
        String input = tickettype;
        System.out.println("tickettype : "+tickettype);
        if("I".equalsIgnoreCase(tickettype)){
            input = "Inter";
        }else if("D".equalsIgnoreCase(tickettype)){
            input = "Domestic";
        }else if("".equalsIgnoreCase(tickettype)){
            input = "All";
        }
        return input;
    }
    
    private String setDisplayValueTicketFrom(String ticketfrom){
        String input = ticketfrom;
        System.out.println("ticketfrom : "+ticketfrom);
        if("C".equalsIgnoreCase(ticketfrom)){
            input = "In";
        }else if("O".equalsIgnoreCase(ticketfrom)){
            input = "Out";
        }else if("".equalsIgnoreCase(ticketfrom)){
            input = "All";
        }
        return input;
    }

    public String createTicketSummaryQuery(String ticketfrom, String tickettype, String startdate, String enddate, String billto, String passenger,String department) {
        String query = " where ";
        int check = 0;
        System.out.println(startdate);
        System.out.println(enddate);
        if ((ticketfrom != null) && (!"".equalsIgnoreCase(ticketfrom))) {
            query += " ticket_from ='" + ticketfrom + "'";
            check = 1;
        }
        if ((tickettype != null) && (!"".equalsIgnoreCase(tickettype))) {
            if (check == 1) {query += " and"; }
            query += "  ticket_type = '" + tickettype + "'";
            query += "";
            check = 1;
        }

        if (((startdate != null) && (!"".equalsIgnoreCase(startdate))) && ((enddate != null) && (!"".equalsIgnoreCase(enddate)))) {
            if (check == 1) {query += " and"; }
            query += "  create_date between '" + startdate + "' and '" + enddate + "'";
            query += "";
            check = 1;
        }
        if ((billto != null) && (!"".equalsIgnoreCase(billto))) {
            if (check == 1) {query += " and"; }
            query += "  bill_code = '" + billto + "'";
            query += "";
            check = 1;
        }
        if ((department != null) && (!"".equalsIgnoreCase(department))) {
            if (check == 1) {query += " and"; }
            query += "  booktype = '" + department + "'";
            query += "";
            check = 1;
        }

        if (check == 0) {
            query = query.replaceAll("where", " ");
        }
        System.out.println("query : " + query);
        return query;
    }

    @Override
    public TicketSummaryList getTicketSummaryReport(String ticketfrom, String tickettype, String startdate, String enddate, String billto, String passenger, String username,String department) {
        TicketSummaryList guideCommissionInfo = new TicketSummaryList();
        UtilityFunction util = new UtilityFunction();
        guideCommissionInfo.setSystemdate(new SimpleDateFormat("dd MMM yy hh:mm", new Locale("us", "us")).format(new Date()));
        guideCommissionInfo.setUsername(username);
        guideCommissionInfo.setStartdate(startdate);
        guideCommissionInfo.setEnddate(enddate);
        guideCommissionInfo.setFrom(ticketfrom);
        guideCommissionInfo.setType(tickettype);
        guideCommissionInfo.setTicketSummaryDataSource(new JRBeanCollectionDataSource(getTicketSummary(ticketfrom, tickettype, startdate, enddate, billto, passenger, username, department)));
        guideCommissionInfo.setTicketSummaryAirlineDataSource(new JRBeanCollectionDataSource(getTicketSummaryAirline(ticketfrom, tickettype, startdate, enddate, billto, passenger, username, department)));
        return guideCommissionInfo;
    }
    
    private List getTicketSummaryAirline(String ticketfrom, String tickettype, String startdate, String enddate, String billto, String passenger,String username,String department){
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        Date thisDate = new Date();
        List data = new ArrayList();
        String Query ="SELECT bill_to as billingname, air as airline , COUNT(ticket_no)  as ticketnum , SUM(sale_fare) as totalsalefare ,SUM(net_fare) as totalnetfare ,SUM(tax) as totaltax " +
                      ",SUM(profit) as totalprofit  FROM `airticket_ticket` ";
        Query += createTicketSummaryQuery(ticketfrom,tickettype,startdate,enddate,billto,passenger,department);
        Query += "GROUP BY bill_to,air " +
                 "ORDER BY bill_to ";
        System.out.println("Query Ticket Summary Airline : "+Query);
        int no = 0;
        List<Object[]> QueryTicketList = session.createSQLQuery(Query )
                .addScalar("airline", Hibernate.STRING)
                .addScalar("ticketnum", Hibernate.STRING)
                .addScalar("totalsalefare", Hibernate.STRING)
                .addScalar("totalnetfare", Hibernate.STRING)
                .addScalar("totaltax", Hibernate.STRING)
                .addScalar("totalprofit", Hibernate.STRING)
                .addScalar("billingname", Hibernate.STRING)
                .list();
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy");
        for (Object[] B : QueryTicketList) {
            TicketSummaryAirline sum = new TicketSummaryAirline();
            no +=1;
            sum.setBillingname(util.ConvertString(B[6]));
            sum.setAirline(util.ConvertString(B[0]));
            if(B[1] != null){
                BigDecimal ticknum = new BigDecimal(util.ConvertString(B[1]));
                sum.setTicketnum(ticknum);
            }else{
                sum.setTicketnum(new BigDecimal("0.0"));
            }
            if(B[2] != null){
                BigDecimal salefare = new BigDecimal(util.ConvertString(B[2]));
                sum.setTotalsalefare(salefare);
            }else{
                sum.setTotalsalefare(new BigDecimal("0.0"));
            }
            if(B[3] != null){
                BigDecimal netfare = new BigDecimal(util.ConvertString(B[3]));
                sum.setTotalnetfare(netfare);
            }else{
                sum.setTotalnetfare(new BigDecimal("0.0"));
            }
            if(B[4] != null){
                BigDecimal tax = new BigDecimal(util.ConvertString(B[4]));
                sum.setTotaltax(tax);
            }else{
                sum.setTotaltax(new BigDecimal("0.0"));
            }
            if(B[5] != null){
                BigDecimal profit = new BigDecimal(util.ConvertString(B[5]));
                sum.setProfit(profit);
            }else{
                sum.setProfit(new BigDecimal("0.0"));
            }
            
            if(B[5] != null && B[1] != null){
                BigDecimal profit = new BigDecimal(util.ConvertString(B[5]));
                BigDecimal ticketnum = new BigDecimal(util.ConvertString(B[1]));
                System.out.println("Profit : " + profit  + "  Ticket Num : " + ticketnum);
                BigDecimal sumProfitAvg = profit.divide(ticketnum, MathContext.DECIMAL128);
                System.out.println("Sum Total Profit Avg : " + sumProfitAvg);
                sum.setProfitavg(sumProfitAvg);
            }else{
                sum.setProfitavg(new BigDecimal("0.0"));
            }
            
            data.add(sum);
            System.out.println("sum data :");
            
        }
        
        session.close();
        this.sessionFactory.close();
        return data;
    }

    @Override
    public List getRefundTicketSummary(String refundFrom, String refundTo, String ticketFrom, String ticketTo, String refundBy, String printBy) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        Date thisDate = new Date();
        List data = new ArrayList();
        String Query = "SELECT * FROM `refund_airticket_sale_summary` where refunddate BETWEEN '"+refundFrom+"' and '"+refundTo+"' ";

        if (((ticketFrom != null) && (!"".equalsIgnoreCase(ticketFrom))) && ((ticketTo != null) && (!"".equalsIgnoreCase(ticketTo)))) {
            Query += "  and ticket_date BETWEEN '"+ticketFrom+"' and '"+ticketTo+"' ";
        }
        
        if ((refundBy != null) && (!"".equalsIgnoreCase(refundBy))) {
            Query += "  and refundby = '" + refundBy + "'";
        }        
        
        System.out.println("Query : "+Query);
        
        List<Object[]> QueryTicketList = session.createSQLQuery(Query)
                .addScalar("refundno", Hibernate.STRING)
                .addScalar("refunddate", Hibernate.STRING)
                .addScalar("ticketno", Hibernate.STRING)
                .addScalar("refno", Hibernate.STRING)
                .addScalar("refundby", Hibernate.STRING)
                .addScalar("sectorRefund", Hibernate.STRING)
                .addScalar("sale_fare", Hibernate.STRING)
                .addScalar("net_fare", Hibernate.STRING)
                .addScalar("tax", Hibernate.STRING)
                .addScalar("profit", Hibernate.STRING)
                .addScalar("refund", Hibernate.STRING)
                .addScalar("ticket_date", Hibernate.STRING)
                .list();
            
            SimpleDateFormat dateformat = new SimpleDateFormat();
            dateformat.applyPattern("dd-MM-yyyy");
            
        for (Object[] B : QueryTicketList) {
            RefundTicketSummaryReport sum = new RefundTicketSummaryReport();

            sum.setSystemdate(util.ConvertString(new SimpleDateFormat("dd MMM yyyy hh:mm:ss", new Locale("us", "us")).format(thisDate)));
            sum.setUser(printBy);
            sum.setRefundfrom(util.ConvertString(new SimpleDateFormat("dd-MM-yyyy", new Locale("us", "us")).format(util.convertStringToDate(refundFrom))));
            sum.setRefundto(util.ConvertString(new SimpleDateFormat("dd-MM-yyyy", new Locale("us", "us")).format(util.convertStringToDate(refundTo))));
            if((!"".equalsIgnoreCase(ticketFrom) && !ticketFrom.isEmpty()) && (!"".equalsIgnoreCase(ticketTo) && !ticketTo.isEmpty())){
                sum.setTicketfrom(util.ConvertString(new SimpleDateFormat("dd-MM-yyyy", new Locale("us", "us")).format(util.convertStringToDate(ticketFrom))));
                sum.setTicketto(util.ConvertString(new SimpleDateFormat("dd-MM-yyyy", new Locale("us", "us")).format(util.convertStringToDate(ticketTo))));
            }
            
            sum.setRefundno(util.ConvertString(B[0]));
            sum.setRefunddate("null".equals(String.valueOf(B[1])) ? "" : util.ConvertString(new SimpleDateFormat("dd/MM/yyyy", new Locale("us", "us")).format(util.convertStringToDate(util.ConvertString(B[1])))));
            sum.setTicketno(util.ConvertString(B[2]));
            sum.setRefno(util.ConvertString(B[3]));
            sum.setRefundby(util.ConvertString(B[4]));
            sum.setSectorrefund(B[5] != null ? util.ConvertString(B[5]) : "");
            sum.setSalefare(B[6] != null ? util.ConvertString(B[6]) : "0.00");
            sum.setNetfare(B[7] != null ? util.ConvertString(B[7]) : "0.00");
            sum.setTax(B[8] != null ? util.ConvertString(B[8]) : "0.00");
            sum.setProfit(B[9] != null ? util.ConvertString(B[9]) : "0.00");
            sum.setRefund(B[10] != null ? util.ConvertString(B[10]) : "0.00");
            data.add(sum);            
        }
        session.close();
        this.sessionFactory.close();
        return data;
    }

}
