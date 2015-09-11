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
    public List getTicketFareReport(String ticketType,String ticketBuy,String airline,String airlineCode,String dateFrom,String dateTo,String department,String staff,String termPay,String printby){
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList<TicketFareReport>();

        String query = "SELECT * FROM `ticket_fare_airline_view` where";
        
        int checkQuery = 0;
        String prefix ="";
        
        if(((dateFrom != null) &&(!"".equalsIgnoreCase(dateFrom))) &&((dateTo != null) &&(!"".equalsIgnoreCase(dateTo)))){
            query += " issuedate >= '" +dateFrom +"' and issuedate <= '"+dateTo +"' ";
            checkQuery = 1;
        }else if((dateFrom != null) &&(!"".equalsIgnoreCase(dateFrom))){
            checkQuery = 1;
            query +=  " issuedate >= '" +dateFrom +"'";

        }else if((dateTo != null) &&(!"".equalsIgnoreCase(dateTo))){
            checkQuery = 1;
            query += " issuedate <= '" +dateTo +"'";
        }
        
        if((ticketType != null) &&(!"".equalsIgnoreCase(ticketType))){
            if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
            query += prefix+" tickettype = '"+ticketType+"'";
        }else{
            ticketType = "ALL";
        }

        if((ticketBuy != null) &&(!"".equalsIgnoreCase(ticketBuy))){
            if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
            query += prefix+ " ticketbuy = '"+ticketBuy+"'";
        }else{
            ticketBuy = "ALL";
        }
        
        if((airline != null) &&(!"".equalsIgnoreCase(airline))){
            if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
            query += prefix+" airagent = '"+airline+"'";
        }else{
            airline = "ALL";
        }

        if((airlineCode != null) &&(!"".equalsIgnoreCase(airlineCode))){
            if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
            query += prefix+ " air = '"+airlineCode+"'";
        }
        if((department != null) &&(!"".equalsIgnoreCase(department))){
            if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
            query += prefix+" department = '"+department+"'";
        }else{
            department = "ALL";
        }

        if((staff != null) &&(!"".equalsIgnoreCase(staff))){
            if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
            query += prefix+ " staff = '"+staff+"'";
        }else{
            staff = "ALL";
        }
        
        if((termPay != null) &&(!"".equalsIgnoreCase(termPay))){
            if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
            query += prefix+ " termpay = '"+termPay+"'";
        }else{
            termPay = "ALL";
        }
        
        if(checkQuery == 0){query = query.replaceAll("where", "");}
        System.out.println("query : "+query);
        
        List<Object[]> QueryList =  session.createSQLQuery(query)
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
                .addScalar("invdate",Hibernate.STRING)
                .list();
        
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("dd-MM-yyyy hh:mm");
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy");
        if("wendy".equalsIgnoreCase(department)){
            department = "WENDY";
        }else if("inbound".equalsIgnoreCase(department)){
            department = "INBOUND";
        }else if("outbound".equalsIgnoreCase(department)){
            department = "OUTBOUND";
        }
        
        for (Object[] B : QueryList) {
            TicketFareReport ticketFareReport = new TicketFareReport();
            //set header
            ticketFareReport.setTickettype(ticketType);
            ticketFareReport.setTicketbuy(ticketBuy);
            ticketFareReport.setAirline(airline);
            ticketFareReport.setFrom("".equals(String.valueOf(dateFrom)) ? "" : util.ConvertString(dateformat.format(util.convertStringToDate(dateFrom))));
            ticketFareReport.setTo("".equals(String.valueOf(dateTo)) ? "" : util.ConvertString(dateformat.format(util.convertStringToDate(dateTo))));
            ticketFareReport.setHeaddepartment(department);
            ticketFareReport.setHeadstaff(staff);
            ticketFareReport.setHeadtermpay(termPay);
            ticketFareReport.setPrintby(printby);
            ticketFareReport.setPrintondate(String.valueOf(df.format(new Date())));
            //set data
            ticketFareReport.setAir(util.ConvertString(B[0]));
            ticketFareReport.setDocno(util.ConvertString(B[1]));
            ticketFareReport.setIssuedate("null".equals(String.valueOf(B[2])) ? "" : util.ConvertString(dateformat.format(util.convertStringToDate(String.valueOf(B[2])))));
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
            ticketFareReport.setInvdate("null".equals(String.valueOf(B[20])) ? "" : util.ConvertString(dateformat.format(util.convertStringToDate(String.valueOf(B[20])))));
            data.add(ticketFareReport);
        }
        
        return data;
    }
    @Override
    public List getTicketFareSumAgentStaff(String ticketType, String ticketBuy, String airline, String airlineCode, String department, String staff, String termPay, String printby, String issuedateFrom, String issuedateTo, String invdateFrom, String invdateTo, String groupBy) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList<TicketFareReport>();
        
        String TicketSummary = "select `agt`.`name` AS `agentname`,`agt`.`id` AS `agentid`,`fare`.`owner` AS `owner`,(select sum(`fare`.`inv_amount`) from `invoice_detail` `invd` where (`invd`.`invoice_id` = `inv`.`id`)) AS `invamount`,`fare`.`department` AS `department`,sum(ifnull(`fare`.`ticket_commission`,0)) AS `ticcom`,sum(ifnull(`fare`.`sale_price`,0)) AS `saleprice`,sum(ifnull(`fare`.`agent_commission`,0)) AS `agentcom`,sum((ifnull(`fare`.`ticket_commission`,0) - ifnull(`fare`.`agent_commission`,0))) AS `profit`,count(`fare`.`ticket_commission`) AS `pax` from (((`ticket_fare_airline` `fare` join `agent` `agt` on((`agt`.`id` = `fare`.`agent_id`))) left join `ticket_fare_invoice` `finv` on((`finv`.`ticket_fare_id` = `fare`.`id`))) left join `invoice` `inv` on((`inv`.`id` = `finv`.`invoice_id`))) ";
        
        
        
        
        
        
        
        
        
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
