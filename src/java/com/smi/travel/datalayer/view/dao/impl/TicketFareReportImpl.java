/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.entity.Agent;
import com.smi.travel.datalayer.report.model.TicketFareReport;
import com.smi.travel.datalayer.report.model.TicketFareSummaryByAgentStaff;
import com.smi.travel.datalayer.view.dao.TicketFareReportDao;
import com.smi.travel.datalayer.view.entity.TicketProfitLoss;
import com.smi.travel.datalayer.view.entity.TicketSummaryAirlineView;
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
    public List getTicketFareReport(String ticketType,String ticketBuy,String airline,String airlineCode,String dateFrom,String dateTo,String department,String staff,String termPay,String printby,String invdateFrom,String invdateTo,String reportType){
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList<TicketFareReport>();
        System.out.println(" ticketType " + ticketType);
        System.out.println(" ticketBuy " + ticketBuy);
        System.out.println(" airline " + airline);
        System.out.println(" airlineCode " + airlineCode);
        System.out.println(" dateFrom " + dateFrom);
        System.out.println(" dateTo " + dateTo);
        System.out.println(" department " + department);
        System.out.println(" staff " + staff);
        System.out.println(" termPay " + termPay);
        System.out.println(" printby " + printby);
        System.out.println(" invdateFrom " + invdateFrom);
        System.out.println(" invdateTo " + invdateTo);
        
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
        
        if(((invdateFrom != null) &&(!"".equalsIgnoreCase(invdateFrom))) &&((invdateTo != null) &&(!"".equalsIgnoreCase(invdateTo)))){
            if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
            query +=  prefix+" invdate >= '" +invdateFrom +"' and invdate <= '"+invdateTo +"' ";
        }else if((invdateFrom != null) &&(!"".equalsIgnoreCase(invdateFrom))){
            if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
            query +=  prefix+ " invdate >= '" +invdateFrom +"'";
        }else if((invdateTo != null) &&(!"".equalsIgnoreCase(invdateTo))){
            if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
            query +=  prefix+" invdate <= '" +invdateTo +"'";
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
        
        if("TicketFareAirline".equalsIgnoreCase(reportType)){
            query += " ORDER BY docno  ";
        }else if("TicketFareInvoice".equalsIgnoreCase(reportType) || "TicketFareAgent".equalsIgnoreCase(reportType)){
            query += " ORDER BY invno ";
        }
        
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
                .addScalar("balance_pay",Hibernate.STRING)
                .addScalar("passenger",Hibernate.STRING)
                .addScalar("agent",Hibernate.STRING)
                .addScalar("ticketcom",Hibernate.STRING)
                .addScalar("saleprice",Hibernate.STRING)
                .addScalar("agentcom",Hibernate.STRING)
                .addScalar("profit",Hibernate.STRING)
                .addScalar("invdate",Hibernate.STRING)
                .addScalar("refno",Hibernate.STRING)
                .addScalar("wht",Hibernate.STRING)
                .addScalar("cost",Hibernate.STRING)
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
        
        if("1".equalsIgnoreCase(airline)){
            airline = "IATA";
        }else if("2".equalsIgnoreCase(airline)){
            airline = "TG";
        }
			
	if("C".equalsIgnoreCase(ticketBuy)){
            ticketBuy = "IN";
        }else if("O".equalsIgnoreCase(ticketBuy)){
            ticketBuy = "OUT";
        }
			
	if("B".equalsIgnoreCase(ticketType)){
            ticketType = "BSP";
        }else if("D".equalsIgnoreCase(ticketType)){
            ticketType = "DOMESTIC";
        }else if("A".equalsIgnoreCase(ticketType)){
            ticketType = "AGENT";
        }else if("TI".equalsIgnoreCase(ticketType)){
            ticketType = "TG INTER";
        }else if("TD".equalsIgnoreCase(ticketType)){
            ticketType = "TG DOMESTIC";
        }
        
        for (Object[] B : QueryList) {
            TicketFareReport ticketFareReport = new TicketFareReport();
            //set header
            ticketFareReport.setTickettype(ticketType);
            ticketFareReport.setTicketbuy(ticketBuy);
            ticketFareReport.setAirline(airline);
            ticketFareReport.setFrom("".equals(String.valueOf(dateFrom)) || dateFrom == null ? "" : util.ConvertString(dateformat.format(util.convertStringToDate(dateFrom))));
            ticketFareReport.setTo("".equals(String.valueOf(dateTo)) || dateTo == null ? "" : util.ConvertString(dateformat.format(util.convertStringToDate(dateTo))));
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
            ticketFareReport.setRefno(util.ConvertString(B[21]));
            ticketFareReport.setWht(util.ConvertString(B[22]));
            ticketFareReport.setCost(util.ConvertString(B[23]));
            data.add(ticketFareReport);
        }
        
        return data;
    }
    @Override
    public List getTicketFareSumAgentStaff(String ticketType, String ticketBuy, String airline, String airlineCode, String department, String staff, String termPay, String printby, String issuedateFrom, String issuedateTo, String invdateFrom, String invdateTo, String groupBy) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList<TicketFareSummaryByAgentStaff>();
        String query = "";
        
        
        if("agent".equalsIgnoreCase(groupBy)){
            // by Agent
            query = "select `agt`.`name` AS `agentname`,`agt`.`id` AS `agentid`,`fare`.`owner` AS `owner`,sum(ifnull(`fare`.`inv_amount`,0)) AS `invamount`,`fare`.`department` AS `department`,sum(ifnull(`fare`.`ticket_commission`,0)) AS `ticcom`,sum(ifnull(`fare`.`sale_price`,0)) AS `saleprice`,sum(ifnull(`fare`.`agent_commission`,0)) AS `agentcom`,round(sum(`GET_REAL_COST_TICKET_FARE`(`fare`.`id`)),2) AS `cost`,round(sum((ifnull(`fare`.`sale_price`,0) - `GET_REAL_COST_TICKET_FARE`(`fare`.`id`))),2) AS `profit`, count((case when (`fare`.`is_temp_ticket` = 1) then `fare`.`ticket_fare` else NULL end)) AS `pax` from (((`ticket_fare_airline` `fare` join `agent` `agt` on((`agt`.`id` = `fare`.`agent_id`))) left join `ticket_fare_invoice` `finv` on((`finv`.`ticket_fare_id` = `fare`.`id`))) left join `invoice` `inv` on((`inv`.`id` = `finv`.`invoice_id`)))  TEMPS";
        }else if("staff".equalsIgnoreCase(groupBy)){
            // by Staff
            query = "select `fare`.`owner` AS `owner`,`agt`.`name` AS `agentname`,`agt`.`id` AS `agentid`,sum(ifnull(`fare`.`inv_amount`,0)) AS `invamount`,`fare`.`department` AS `department`,sum(ifnull(`fare`.`ticket_commission`,0)) AS `ticcom`,sum(ifnull(`fare`.`sale_price`,0)) AS `saleprice`,sum(ifnull(`fare`.`agent_commission`,0)) AS `agentcom`,round(sum(`GET_REAL_COST_TICKET_FARE`(`fare`.`id`)),2) AS `cost`,round(sum((ifnull(`fare`.`sale_price`,0) - `GET_REAL_COST_TICKET_FARE`(`fare`.`id`))),2) AS `profit`,count((case when (`fare`.`is_temp_ticket` = 1) then `fare`.`ticket_fare` else NULL end)) AS `pax` from (((`ticket_fare_airline` `fare` join `agent` `agt` on((`agt`.`id` = `fare`.`agent_id`))) left join `ticket_fare_invoice` `finv` on((`finv`.`ticket_fare_id` = `fare`.`id`))) left join `invoice` `inv` on((`inv`.`id` = `finv`.`invoice_id`))) TEMPS";
        }
//        String query = "select `agt`.`name` AS `agentname`,`agt`.`id` AS `agentid`,`fare`.`owner` AS `owner`,(select sum(`fare`.`inv_amount`) from `invoice_detail` `invd` where (`invd`.`invoice_id` = `inv`.`id`)) AS `invamount`,`fare`.`department` AS `department`,sum(ifnull(`fare`.`ticket_commission`,0)) AS `ticcom`,sum(ifnull(`fare`.`sale_price`,0)) AS `saleprice`,sum(ifnull(`fare`.`agent_commission`,0)) AS `agentcom`,sum((ifnull(`fare`.`ticket_commission`,0) - ifnull(`fare`.`agent_commission`,0))) AS `profit`,count(`fare`.`ticket_commission`) AS `pax` from (((`ticket_fare_airline` `fare` join `agent` `agt` on((`agt`.`id` = `fare`.`agent_id`))) left join `ticket_fare_invoice` `finv` on((`finv`.`ticket_fare_id` = `fare`.`id`))) left join `invoice` `inv` on((`inv`.`id` = `finv`.`invoice_id`))) TEMPS";
        
        int checkQuery = 0;
        String prefix ="";
        
        if(((issuedateFrom != null) &&(!"".equalsIgnoreCase(issuedateFrom))) &&((issuedateTo != null) &&(!"".equalsIgnoreCase(issuedateTo)))){
            query += " `fare`.`issue_date` >= '" +issuedateFrom +"' and `fare`.`issue_date` <= '"+issuedateTo +"' ";
            checkQuery = 1;
        }else if((issuedateFrom != null) &&(!"".equalsIgnoreCase(issuedateFrom))){
            checkQuery = 1;
            query +=  " `fare`.`issue_date` >= '" +issuedateFrom +"'";
        }else if((issuedateTo != null) &&(!"".equalsIgnoreCase(issuedateTo))){
            checkQuery = 1;
            query += " `fare`.`issue_date` <= '" +issuedateTo +"'";
        }
        
        if(((invdateFrom != null) &&(!"".equalsIgnoreCase(invdateFrom))) &&((invdateTo != null) &&(!"".equalsIgnoreCase(invdateTo)))){
            if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
            query += prefix+ " `inv`.`inv_date` >= '" +invdateFrom +"' and `inv`.`inv_date` <= '"+invdateTo +"' ";
        }else if((invdateFrom != null) &&(!"".equalsIgnoreCase(invdateFrom))){
            if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
            query += prefix+ " `inv`.`inv_date` >= '" +invdateFrom +"'";
        }else if((invdateTo != null) &&(!"".equalsIgnoreCase(invdateTo))){
            if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
            query += prefix+ " `inv`.`inv_date` <= '" +invdateTo +"'";
        }
        
        if((ticketType != null) &&(!"".equalsIgnoreCase(ticketType))){
            if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
            query += prefix+" `fare`.`ticket_type` = '"+ticketType+"'";
        }else{
            ticketType = "ALL";
        }

        if((ticketBuy != null) &&(!"".equalsIgnoreCase(ticketBuy))){
            if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
            query += prefix+ " `fare`.`ticket_buy` = '"+ticketBuy+"'";
        }else{
            ticketBuy = "ALL";
        }
        
        if((airline != null) &&(!"".equalsIgnoreCase(airline))){
            if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
            query += prefix+" `fare`.`airline_agent_id` = '"+airline+"'";
        }else{
            airline = "ALL";
        }

        if((airlineCode != null) &&(!"".equalsIgnoreCase(airlineCode))){
            if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
            query += prefix+ " SUBSTR(`fare`.`ticket_no`,1,3) = '"+airlineCode+"'";
        }
        
        if((department != null) &&(!"".equalsIgnoreCase(department))){
            if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
            query += prefix+" `fare`.`department` = '"+department+"'";
        }else{
            department = "ALL";
        }

        if((staff != null) &&(!"".equalsIgnoreCase(staff))){
            if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
            query += prefix+ " `fare`.`owner` = '"+staff+"'";
        }else{
            staff = "ALL";
        }
        
        if((termPay != null) &&(!"".equalsIgnoreCase(termPay))){
            if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
            query += prefix+ " `inv`.`term_pay` = '"+termPay+"'";
        }else{
            termPay = "ALL";
        }
        
        if(checkQuery == 0){
            query = query.replaceAll("TEMPS", "");
        }else{
            query = query.replaceAll("TEMPS", "WHERE");
        }
        
        if("agent".equalsIgnoreCase(groupBy)){
            // by Agent
            query += "group by `agt`.`id`,`fare`.`owner`,`fare`.`department`";
        }else if("staff".equalsIgnoreCase(groupBy)){
            // by Staff
            query += "group by `fare`.`owner`,`agt`.`id`,`fare`.`department`";
        }

        System.out.println("query : "+query);
        
        List<Object[]> QueryList =  session.createSQLQuery(query)
                .addScalar("agentname",Hibernate.STRING)
                .addScalar("agentid",Hibernate.STRING)
                .addScalar("owner",Hibernate.STRING)
                .addScalar("invamount",Hibernate.STRING)
                .addScalar("department",Hibernate.STRING)
                .addScalar("ticcom",Hibernate.STRING)
                .addScalar("saleprice",Hibernate.STRING)
                .addScalar("agentcom",Hibernate.STRING)
                .addScalar("profit",Hibernate.STRING)
                .addScalar("pax",Hibernate.STRING)
                .addScalar("cost",Hibernate.STRING)
                .list();
        
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("dd-MM-yyyy hh:mm");

        if("wendy".equalsIgnoreCase(department)){
            department = "WENDY";
        }else if("inbound".equalsIgnoreCase(department)){
            department = "INBOUND";
        }else if("outbound".equalsIgnoreCase(department)){
            department = "OUTBOUND";
        }
        
        if("1".equalsIgnoreCase(termPay)){
            termPay = "cash on demand";
        }else if("2".equalsIgnoreCase(termPay)){
            termPay = "credit 7 days";
        }else if("3".equalsIgnoreCase(termPay)){
            termPay = "credit 14 days";
        }else if("4".equalsIgnoreCase(termPay)){
            termPay = "credit card";
        }else if("5".equalsIgnoreCase(termPay)){
            termPay = "credit 30 days";
        }else if("6".equalsIgnoreCase(termPay)){
            termPay = "post date cheque";
        }else if("7".equalsIgnoreCase(termPay)){
            termPay = "credit 15 days";
        }else{
            termPay = "ALL";
        }
			
        if("1".equalsIgnoreCase(airline)){
            airline = "IATA";
        }else if("2".equalsIgnoreCase(airline)){
            airline = "TG";
        }
			
	if("C".equalsIgnoreCase(ticketBuy)){
            ticketBuy = "IN";
        }else if("O".equalsIgnoreCase(ticketBuy)){
            ticketBuy = "OUT";
        }
			
	if("B".equalsIgnoreCase(ticketType)){
            ticketType = "BSP";
        }else if("D".equalsIgnoreCase(ticketType)){
            ticketType = "DOMESTIC";
        }else if("A".equalsIgnoreCase(ticketType)){
            ticketType = "AGENT";
        }else if("TI".equalsIgnoreCase(ticketType)){
            ticketType = "TG INTER";
        }else if("TD".equalsIgnoreCase(ticketType)){
            ticketType = "TG DOMESTIC";
        }
        
        for (Object[] B : QueryList) {
            TicketFareSummaryByAgentStaff ticket = new TicketFareSummaryByAgentStaff();
           //set header
           ticket.setHeadtickettype(ticketType);
           ticket.setHeadticketbuy(ticketBuy);
           ticket.setHeaddepartment(department);
           ticket.setAirline(airline);
           ticket.setHeadsale(staff);
           ticket.setPrintby(printby);
           ticket.setIssuefrom(issuedateFrom);
           ticket.setIssueto(issuedateTo);
           ticket.setInvdatefrom(invdateFrom);
           ticket.setInvdateto(invdateTo);
           ticket.setTermpay(termPay);
           ticket.setPrinton(String.valueOf(df.format(new Date())));
           
           ticket.setAgentname(util.ConvertString(B[0]));
           ticket.setAgentid(util.ConvertString(B[1]));
           ticket.setOwner(util.ConvertString(B[2]));
           ticket.setInvamount(util.ConvertString(B[3]));
           ticket.setDepartment(util.ConvertString(B[4]));
           ticket.setTiccom(util.ConvertString(B[5]));
           ticket.setSaleprice(util.ConvertString(B[6]));
           ticket.setAgentcom(util.ConvertString(B[7]));
           ticket.setProfit(util.ConvertString(B[8]));
           ticket.setPax(util.ConvertString(B[9]));
           ticket.setCost(util.ConvertString(B[10]));
           data.add(ticket);
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

    @Override
    public List getTicketFareSumAirline(String typeRouting, String routingDetail, String dateFrom, String dateTo,String invdateForm,String invdateTo, String airlineCode, String passenger, String agentId, String department, String saleBy, String termPay, String printby) {
        
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList<TicketSummaryAirlineView>();
        String query = "";
        String departmenttemp = "";
        String termPaytemp = "";
        String typeRoutingtemp = "";
        String routingDetailtemp = "";
        String agentIdtemp = "";
        String saleBytemp = "";
        
        for(int i = 0 ; i < 3 ; i++){
            Session session = this.sessionFactory.openSession();
            if(i == 0){ //pax
                query = "select count((case when (`fare`.`is_temp_ticket` = 1) then `fare`.`ticket_fare` else NULL end)) AS `pax`,(case when (`fare`.`ticket_type` = 'B') then 'BSP' when (`fare`.`ticket_type` = 'A') then 'AGENT' when (`fare`.`ticket_type` = 'D') then 'DOMESTIC' when (`fare`.`ticket_type` = 'TI') then 'TG INTER' when (`fare`.`ticket_type` = 'TD') then 'TG DOMESTIC' else `fare`.`ticket_type` end) AS `paymenttype`,(case when (`fare`.`ticket_rounting` = 'I') then 'INTER' when (`fare`.`ticket_rounting` = 'D') then 'DOMESTIC' when (`fare`.`ticket_rounting` = 'C') then 'CANCELLED' end) AS `typerounting`,sum(ifnull(`fare`.`ticket_fare`,0)) AS `netsales`,sum(ifnull(`fare`.`ticket_tax`,0)) AS `tax`,sum(ifnull(`fare`.`ticket_ins`,0)) AS `ins`,sum(ifnull(`fare`.`ticket_commission`,0)) AS `comms`,sum((case when (`fare`.`department` = 'wendy') then `fare`.`inv_amount` else NULL end)) AS `amountwendy`,sum((case when (`fare`.`department` = 'inbound') then `fare`.`inv_amount` else NULL end)) AS `amountinbound`,sum((case when (`fare`.`department` = 'outbound') then `fare`.`inv_amount` else NULL end)) AS `amountoutbound` from ((((`ticket_fare_airline` `fare` left join `ticket_fare_invoice` `finv` on((`finv`.`ticket_fare_id` = `fare`.`id`))) left join `invoice` `inv` on((`inv`.`id` = `finv`.`invoice_id`))) left join `m_accterm` `term` on((`term`.`id` = `inv`.`term_pay`))) left join `staff` `st` on((`st`.`name` = `fare`.`owner`))) TEMPS ";
            }else if(i == 1){ //detail
                query = "SELECT `inv`.`inv_no` AS `invno`, `inv`.`inv_date` AS `invdate`, `fare`.`department` AS `department`, `fare`.`owner` AS `staff`, `term`.`name` AS `termpay`, `fare`.`passenger` AS `passenger`, ( CASE WHEN (`fare`.`ticket_type` = 'B') THEN 'BSP' WHEN (`fare`.`ticket_type` = 'A') THEN 'AGENT' WHEN (`fare`.`ticket_type` = 'D') THEN 'DOMESTIC' WHEN (`fare`.`ticket_type` = 'TI') THEN 'TG INTER' WHEN (`fare`.`ticket_type` = 'TD') THEN 'TG DOMESTIC' ELSE `fare`.`ticket_type` END ) AS `typepayment`, ( CASE WHEN ( `fare`.`ticket_rounting` = 'I' ) THEN 'INTER' WHEN ( `fare`.`ticket_rounting` = 'D' ) THEN 'DOMESTIC' WHEN ( `fare`.`ticket_rounting` = 'C' ) THEN 'CANCELLED' END ) AS `typerounting`, `fare`.`routing_detail` AS `rounting`, ( CASE WHEN (`fare`.`is_temp_ticket` = 1) THEN 1 ELSE 0 END ) AS `pax`, substr(`fare`.`ticket_no`, 1, 3) AS `air`, substr(`fare`.`ticket_no`, 4) AS `ticketno`, `mt`.`Reference No` AS `refno`, `fare`.`issue_date` AS `issuedate`, ifnull(`fare`.`ticket_fare`, 0) AS `netsales`, ifnull(`fare`.`ticket_tax`, 0) AS `tax`, ifnull(`fare`.`ticket_ins`, 0) AS `ins`, ifnull( `fare`.`ticket_commission`, 0 ) AS `comms`, ifnull(`fare`.`diff_vat`, 0) AS `diff`, ( CASE WHEN (( `fare`.`department` = 'wendy' ) AND isnull(`fare`.`pv_type`)) THEN `fare`.`inv_amount` ELSE NULL END ) AS `amountwendy`, ( CASE WHEN (( `fare`.`department` = 'inbound' ) AND isnull(`fare`.`pv_type`)) THEN `fare`.`inv_amount` ELSE NULL END ) AS `amountinbound`, ( CASE WHEN (( `fare`.`department` = 'outbound' ) AND isnull(`fare`.`pv_type`)) THEN `fare`.`inv_amount` ELSE NULL END ) AS `amountoutbound`, ( CASE WHEN (`fare`.`pv_type` = 10) THEN `fare`.`inv_amount` ELSE NULL END ) AS `amount_noinvoice`, ( CASE WHEN (`fare`.`pv_type` = 7) THEN `fare`.`inv_amount` ELSE NULL END ) AS `amount_businesstrip`, ( CASE WHEN (`fare`.`pv_type` = 8) THEN `fare`.`inv_amount` ELSE NULL END ) AS `amount_annualleave`, ( CASE WHEN (`fare`.`pv_type` = 9) THEN `fare`.`inv_amount` ELSE NULL END ) AS `amount_refund`, `fare`.`remark` AS `remark` FROM (((( `ticket_fare_airline` `fare` LEFT JOIN `ticket_fare_invoice` `finv` ON (( `finv`.`ticket_fare_id` = `fare`.`id` ))) LEFT JOIN `invoice` `inv` ON (( `inv`.`id` = `finv`.`invoice_id` ))) LEFT JOIN `m_accterm` `term` ON (( `term`.`id` = `inv`.`term_pay` ))) LEFT JOIN `master` `mt` ON (( `mt`.`id` = `fare`.`master_id` )) LEFT JOIN `staff` `st` ON ((`st`.`name` = `fare`.`owner`))) TEMPS ";
            }else if(i == 2){ //routing
                query = "SELECT `fare`.`routing_detail` AS `routing`, count(( CASE WHEN (`fare`.`is_temp_ticket` = 1) THEN `fare`.`ticket_fare` ELSE NULL END )) AS `pax`, sum(`fare`.`ticket_fare`) AS `netsales`, sum(`fare`.`ticket_tax`) AS `tax`, sum(`fare`.`ticket_ins`) AS `ins`, sum(`fare`.`ticket_commission`) AS `comms`, sum(( CASE WHEN ( `fare`.`department` = 'wendy' ) THEN `fare`.`sale_price` ELSE 0 END )) AS `amountwendy`, sum(( CASE WHEN ( `fare`.`department` = 'inbound' ) THEN `fare`.`sale_price` ELSE 0 END )) AS `amountinbound`, sum(( CASE WHEN ( `fare`.`department` = 'outbound' ) THEN `fare`.`sale_price` ELSE 0 END )) AS `amountoutbound`, sum(( CASE WHEN ( `fare`.`department` = 'outbound' ) THEN `fare`.`sale_price` ELSE 0 END )) AS `amountoutbound`, sum(`fare`.`diff_vat`) AS `diff` FROM (((( `ticket_fare_airline` `fare` LEFT JOIN `ticket_fare_invoice` `finv` ON (( `finv`.`ticket_fare_id` = `fare`.`id` ))) LEFT JOIN `invoice` `inv` ON (( `inv`.`id` = `finv`.`invoice_id` ))) LEFT JOIN `m_accterm` `term` ON (( `term`.`id` = `inv`.`term_pay` ))) LEFT JOIN `staff` `st` ON ((`st`.`name` = `fare`.`owner`)))  TEMPS";
            }

            int checkQuery = 0;
            String prefix ="";

            if(((dateFrom != null) &&(!"".equalsIgnoreCase(dateFrom))) &&((dateTo != null) &&(!"".equalsIgnoreCase(dateTo)))){
                query += " `fare`.`issue_date` >= '" +dateFrom +"' and `fare`.`issue_date` <= '"+dateTo +"' ";
                checkQuery = 1;
            }else if((dateFrom != null) &&(!"".equalsIgnoreCase(dateFrom))){
                checkQuery = 1;
                query +=  " `fare`.`issue_date` >= '" +dateFrom +"'";
            }else if((dateTo != null) &&(!"".equalsIgnoreCase(dateTo))){
                checkQuery = 1;
                query += " `fare`.`issue_date` <= '" +dateTo +"'";
            }
            
            if(((invdateForm != null) &&(!"".equalsIgnoreCase(invdateForm))) &&((invdateTo != null) &&(!"".equalsIgnoreCase(invdateTo)))){
                if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
                query += prefix+" `inv`.`inv_date` >= '" +invdateForm +"' and `inv`.`inv_date`  <= '"+invdateTo +"' ";
            }else if((invdateForm != null) &&(!"".equalsIgnoreCase(invdateForm))){
                if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
                query +=  prefix+" `inv`.`inv_date` >= '" +invdateForm +"'";
            }else if((invdateTo != null) &&(!"".equalsIgnoreCase(invdateTo))){
                if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
                query += prefix+" `inv`.`inv_date` <= '" +invdateTo +"'";
            }
            
            if((typeRouting != null) &&(!"".equalsIgnoreCase(typeRouting))){
                if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
                query += prefix+" fare.ticket_rounting = '"+typeRouting+"'";
                typeRoutingtemp  = typeRouting;
            }else{
                typeRoutingtemp = "ALL";
            }

            if((routingDetail != null) &&(!"".equalsIgnoreCase(routingDetail))){
                if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
                query += prefix+ " `fare`.`routing_detail` = '"+routingDetail+"'";
                routingDetailtemp = routingDetail;
            }else{
                routingDetailtemp = "ALL";
            }

            if((airlineCode != null) &&(!"".equalsIgnoreCase(airlineCode))){
                if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
                query += prefix+ " substr(`fare`.`ticket_no`, 1, 3) = '"+airlineCode+"'";
            }

            if((passenger != null) &&(!"".equalsIgnoreCase(passenger))){
                if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
                query += prefix+ " `fare`.`passenger` = '"+passenger+"'";
            }

            if((agentId != null) &&(!"".equalsIgnoreCase(agentId))){
                if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
                query += prefix+ " fare.agent_id = '"+agentId+"'";
                agentIdtemp = agentId;
            }else{
                agentIdtemp = "ALL";
            }

            if((department != null) &&(!"".equalsIgnoreCase(department))){
                if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
                query += prefix+" fare.department = '"+department+"'";
                departmenttemp = department;
            }else{
                departmenttemp = "ALL";
            }

            if((saleBy != null) &&(!"".equalsIgnoreCase(saleBy))){
                if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
                query += prefix+ " `st`.`username` = '"+saleBy+"'";
                saleBytemp = saleBy;
            }else{
                saleBytemp = "ALL";
            }

            if((termPay != null) &&(!"".equalsIgnoreCase(termPay))){
                if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
                query += prefix+ " `term`.`name` = '"+termPay+"'";
                termPaytemp = termPay;
            }else{
                termPaytemp = "ALL";
            }

            if(checkQuery == 0){
                query = query.replaceAll("TEMPS", "");
            }else{
                query = query.replaceAll("TEMPS", "WHERE");
            }

            List<Object[]> QueryList = new ArrayList<Object[]>();
            if(i == 0){ //pax
                System.out.println(" PAXXXXXXXXXXXXXXXXXXXXXXXXXXX ");
                query += "group by `fare`.`ticket_type`,`fare`.`ticket_rounting`";
                QueryList = session.createSQLQuery(query)
                            .addScalar("pax",Hibernate.STRING)
                            .addScalar("paymenttype",Hibernate.STRING)
                            .addScalar("typerounting",Hibernate.STRING)
                            .addScalar("netsales",Hibernate.STRING)
                            .addScalar("tax",Hibernate.STRING)
                            .addScalar("ins",Hibernate.STRING)
                            .addScalar("comms",Hibernate.STRING)
                            .addScalar("amountwendy",Hibernate.STRING)
                            .addScalar("amountinbound",Hibernate.STRING)
                            .addScalar("amountoutbound",Hibernate.STRING)
                            .list();        
            }else if(i == 1){ //detail
                System.out.println(" DETAILLLLLLLLLLLLLLLLL ");
                query += " group by `fare`.`id` ";
                QueryList = session.createSQLQuery(query)
                            .addScalar("invno",Hibernate.STRING)
                            .addScalar("invdate",Hibernate.STRING)
                            .addScalar("department",Hibernate.STRING)
                            .addScalar("staff",Hibernate.STRING)
                            .addScalar("termpay",Hibernate.STRING)
                            .addScalar("passenger",Hibernate.STRING)
                            .addScalar("typepayment",Hibernate.STRING)
                            .addScalar("typerounting",Hibernate.STRING)
                            .addScalar("rounting",Hibernate.STRING)
                            .addScalar("pax",Hibernate.STRING)
                            .addScalar("air",Hibernate.STRING)
                            .addScalar("ticketno",Hibernate.STRING)
                            .addScalar("issuedate",Hibernate.STRING)
                            .addScalar("netsales",Hibernate.STRING)
                            .addScalar("tax",Hibernate.STRING)
                            .addScalar("ins",Hibernate.STRING)
                            .addScalar("comms",Hibernate.STRING)
                            .addScalar("diff",Hibernate.STRING)
                            .addScalar("amountwendy",Hibernate.STRING)
                            .addScalar("amountinbound",Hibernate.STRING)
                            .addScalar("amountoutbound",Hibernate.STRING)
                            .addScalar("remark",Hibernate.STRING)
                            .addScalar("amount_noinvoice",Hibernate.STRING)
                            .addScalar("amount_businesstrip",Hibernate.STRING)
                            .addScalar("amount_annualleave",Hibernate.STRING)
                            .addScalar("amount_refund",Hibernate.STRING)
                            .addScalar("refno",Hibernate.STRING)
                            .list();
            }else if(i == 2){ //routing
                System.out.println(" ROUTINGGGGGGGGGGGGGGG ");
                query += "group by `fare`.`routing_detail`";
                QueryList = session.createSQLQuery(query)
                            .addScalar("routing",Hibernate.STRING)
                            .addScalar("pax",Hibernate.STRING)
                            .addScalar("netsales",Hibernate.STRING)
                            .addScalar("tax",Hibernate.STRING)
                            .addScalar("ins",Hibernate.STRING)
                            .addScalar("comms",Hibernate.STRING)
                            .addScalar("amountwendy",Hibernate.STRING)
                            .addScalar("amountinbound",Hibernate.STRING)
                            .addScalar("diff",Hibernate.STRING)
                            .addScalar("amountoutbound",Hibernate.STRING)
                            .list();        
            }

            System.out.println("query : "+query);

            SimpleDateFormat df = new SimpleDateFormat();
            df.applyPattern("dd-MM-yyyy hh:mm");
            SimpleDateFormat dateformat = new SimpleDateFormat();
            dateformat.applyPattern("dd-MM-yyyy");

            if("wendy".equalsIgnoreCase(department)){
                departmenttemp = "WENDY";
            }else if("inbound".equalsIgnoreCase(department)){
                departmenttemp = "INBOUND";
            }else if("outbound".equalsIgnoreCase(department)){
                departmenttemp = "OUTBOUND";
            }

            if("1".equalsIgnoreCase(termPay)){
                termPaytemp = "cash on demand";
            }else if("2".equalsIgnoreCase(termPay)){
                termPaytemp = "credit 7 days";
            }else if("3".equalsIgnoreCase(termPay)){
                termPaytemp = "credit 14 days";
            }else if("4".equalsIgnoreCase(termPay)){
                termPaytemp = "credit card";
            }else if("5".equalsIgnoreCase(termPay)){
                termPaytemp = "credit 30 days";
            }else if("6".equalsIgnoreCase(termPay)){
                termPaytemp = "post date cheque";
            }else if("7".equalsIgnoreCase(termPay)){
                termPaytemp = "credit 15 days";
            }

            if("I".equalsIgnoreCase(typeRouting)){
                typeRoutingtemp = "INTER";
            }else if("D".equalsIgnoreCase(typeRouting)){
                typeRoutingtemp = "DOMESTIC";
            }else if("C".equalsIgnoreCase(typeRouting)){
                typeRoutingtemp = "CANCEL";
            }
                        
            Agent agent = new Agent();
            String queryAgent = "from Agent a where a.id= :agentid";
            List<Agent> agentList = session.createQuery(queryAgent).setParameter("agentid", agentIdtemp).list();
            session.close();
            if (!agentList.isEmpty()) {
                agent =  agentList.get(0);
            }
            
            for (Object[] B : QueryList) {
                TicketSummaryAirlineView ticket = new TicketSummaryAirlineView();
                //set header
                ticket.setHeaderdatefrom("".equals(String.valueOf(invdateForm)) ? "" : util.ConvertString(dateformat.format(util.convertStringToDate(invdateForm))));
                ticket.setHeaderdateto("".equals(String.valueOf(invdateTo)) ? "" : util.ConvertString(dateformat.format(util.convertStringToDate(invdateTo))));
                ticket.setHeaderprinton(String.valueOf(df.format(new Date())));
                ticket.setHeaderprintby(printby);
                ticket.setHeadertyperouting(typeRoutingtemp);
                ticket.setHeaderroutingdetail(routingDetailtemp);
                ticket.setHeaderair(airlineCode);
                ticket.setHeaderpassenger(passenger);
                ticket.setHeaderagentname(agent.getName());
                ticket.setHeaderdepartment(departmenttemp);
                ticket.setHeadersalestaff(saleBytemp);
                ticket.setHeadertermpay(termPaytemp);

                if(i == 0){ // pax
                    ticket.setPaxP(util.ConvertString(B[0]));
                    ticket.setPaymenttypeP(util.ConvertString(B[1]));
                    ticket.setTyperoutingP(util.ConvertString(B[2]));
                    ticket.setNetsalesP(util.ConvertString(B[3]));
                    ticket.setTaxP(util.ConvertString(B[4]));
                    ticket.setInsP(util.ConvertString(B[5]));
                    ticket.setCommsP(util.ConvertString(B[6]));
                    ticket.setAmountwendyP(util.ConvertString(B[7]));
                    ticket.setAmountinboundP(util.ConvertString(B[8]));
                    ticket.setAmountoutboundP(util.ConvertString(B[9]));
                    ticket.setPage("pax");
                }else if(i == 1){ //detail
                    ticket.setInvnoD(util.ConvertString(B[0]));
                    ticket.setInvdateD("null".equals(String.valueOf(B[1])) ? "" : util.ConvertString(dateformat.format(util.convertStringToDate(String.valueOf(B[1])))));
                    ticket.setDepartmentD(util.ConvertString(B[2]));
                    ticket.setStaffD(util.ConvertString(B[3]));
                    ticket.setTermpayD(util.ConvertString(B[4]));
                    ticket.setPassengerD(util.ConvertString(B[5]));
                    ticket.setTypepaymentD(util.ConvertString(B[6]));
                    ticket.setTyperoutingD(util.ConvertString(B[7]));
                    ticket.setRoutingD(util.ConvertString(B[8]));
                    ticket.setPaxD(util.ConvertString(B[9]));
                    ticket.setAirD(util.ConvertString(B[10]));
                    ticket.setTicketnoD(util.ConvertString(B[11]));
                    ticket.setIssuedateD(util.ConvertString(B[12]));
                    ticket.setNetsalesD(util.ConvertString(B[13]));
                    ticket.setTaxD(util.ConvertString(B[14]));
                    ticket.setInsD(util.ConvertString(B[15]));
                    ticket.setCommsD(util.ConvertString(B[16]));
                    ticket.setDiffD(util.ConvertString(B[17]));
                    ticket.setAmountwendyD(util.ConvertString(B[18]));
                    ticket.setAmountinboundD(util.ConvertString(B[19]));
                    ticket.setAmountoutboundD(util.ConvertString(B[20]));
                    ticket.setRemarksD(util.ConvertString(B[21]));
                    ticket.setAmtnoinvoiceD(util.ConvertString(B[22]));
                    ticket.setAmtbusinesstripD(util.ConvertString(B[23]));
                    ticket.setAmtannualleaveD(util.ConvertString(B[24]));
                    ticket.setAmtrefundD(util.ConvertString(B[25]));
                    ticket.setRefnoD(util.ConvertString(B[26]));
                    ticket.setPage("detail");
                }else if(i == 2){
                    ticket.setRoutingR(util.ConvertString(B[0]));
                    ticket.setPaxR(util.ConvertString(B[1]));
                    ticket.setNetsalesR(util.ConvertString(B[2]));
                    ticket.setTaxR(util.ConvertString(B[3]));
                    ticket.setInsR(util.ConvertString(B[4]));
                    ticket.setCommsR(util.ConvertString(B[5]));
                    ticket.setAmountwendyR(util.ConvertString(B[6]));
                    ticket.setAmountinboundR(util.ConvertString(B[7]));
                    ticket.setDiffR(util.ConvertString(B[8]));
                    ticket.setAmountoutboundR(util.ConvertString(B[9]));
                    ticket.setPage("routing");
                }
                data.add(ticket);
            }
        }
        return data;
    }

    @Override
    public List getTicketProfitLoss(String invoiceFromDate, String invoiceToDate, String printby) {
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList<TicketProfitLoss>();
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("dd-MM-yyyy hh:mm");
        boolean cause = true;
        
        Session session = this.sessionFactory.openSession();
        String query = " select `inv`.`inv_no` AS `invno`,`inv`.`inv_date` AS `invdate`,(case when (`inv`.`department` = 'Wendy') then 'W' when (`inv`.`department` = 'Outbound') then 'O' when (`inv`.`department` = 'Inbound') then 'I' end) AS `department`,`inv`.`inv_name` AS `agentname`,`fare`.`ticket_type` AS `type`,`fare`.`ticket_rounting` AS `rount`,count(`fare`.`ticket_no`) AS `pax`,substr(`fare`.`ticket_no`,1,3) AS `air`,group_concat(substr(`fare`.`ticket_no`,4) separator ',') AS `docno`,`fare`.`issue_date` AS `issuedate`,round(sum(ifnull(`fare`.`litter_commission`,0)),2) AS `littlecomm`,round(sum(((ifnull(`fare`.`litter_commission`,0) * `fare`.`vat`) / 100)),2) AS `vat`,round(sum((((ifnull(`fare`.`litter_commission`,0) * `fare`.`vat`) / 100) + ifnull(`fare`.`litter_commission`,0))),2) AS `total` from ((`invoice` `inv` join `ticket_fare_invoice` `finv` on((`finv`.`invoice_id` = `inv`.`id`))) join `ticket_fare_airline` `fare` on((`fare`.`id` = `finv`.`ticket_fare_id`))) ";
        if((!"".equalsIgnoreCase(invoiceFromDate)) && (invoiceFromDate != null)){
            query += (cause ? " where " : "");
            query += " `inv`.`inv_date` >= '" + invoiceFromDate + "'";
            cause = false;        
        }
        if((!"".equalsIgnoreCase(invoiceToDate)) && (invoiceToDate != null)){
            query += (cause ? " where " : " and ");
            query += " `inv`.`inv_date` <= '" + invoiceToDate + "'";
            cause = false;        
        }
        query += " group by `inv`.`inv_no` having round(sum(ifnull(`fare`.`litter_commission`,0)),2) <> 0";
        List<Object[]> QueryList = new ArrayList<Object[]>();
        QueryList = session.createSQLQuery(query)
                        .addScalar("invno",Hibernate.STRING)
                        .addScalar("invdate",Hibernate.STRING)
                        .addScalar("department",Hibernate.STRING)
                        .addScalar("agentname",Hibernate.STRING)
                        .addScalar("type",Hibernate.STRING)
                        .addScalar("rount",Hibernate.STRING)
                        .addScalar("pax",Hibernate.STRING)
                        .addScalar("air",Hibernate.STRING)
                        .addScalar("docno",Hibernate.STRING)
                        .addScalar("issuedate",Hibernate.STRING)
                        .addScalar("littlecomm",Hibernate.STRING)
                        .addScalar("vat",Hibernate.STRING)
                        .addScalar("total",Hibernate.STRING)
                        .list();
        
        int i = 1;
        for (Object[] B : QueryList) {
            TicketProfitLoss ticketProfitLoss = new TicketProfitLoss();
            ticketProfitLoss.setNo(String.valueOf(i));
            ticketProfitLoss.setInvno(util.ConvertString(B[0]));
            ticketProfitLoss.setDate(util.ConvertString(B[1]));
            ticketProfitLoss.setDepartment(util.ConvertString(B[2]));
            ticketProfitLoss.setAgentname(util.ConvertString(B[3]));
            ticketProfitLoss.setType(util.ConvertString(B[4]));
            ticketProfitLoss.setRount(util.ConvertString(B[5]));
            ticketProfitLoss.setPax(util.ConvertString(B[6]));
            ticketProfitLoss.setAir(util.ConvertString(B[7]));
            ticketProfitLoss.setDocno(util.ConvertString(B[8]));
            ticketProfitLoss.setIssuedate(util.ConvertString(B[9]));
            ticketProfitLoss.setLittlecomm(util.ConvertString(B[10]));
            ticketProfitLoss.setVat(util.ConvertString(B[11]));
            ticketProfitLoss.setTotal(util.ConvertString(B[12]));
            ticketProfitLoss.setInvoicedatefrom(!"".equalsIgnoreCase(invoiceFromDate) ? invoiceFromDate : "");
            ticketProfitLoss.setInvoicedateto(!"".equalsIgnoreCase(invoiceToDate) ? invoiceToDate : "");
            ticketProfitLoss.setHeadreport("Ticket Profit Loss");
            ticketProfitLoss.setPrintby(printby);
            ticketProfitLoss.setPrinton(String.valueOf(df.format(new Date())));
            data.add(ticketProfitLoss);
            i++;
        }        
        
        return data;
    }
   
}
