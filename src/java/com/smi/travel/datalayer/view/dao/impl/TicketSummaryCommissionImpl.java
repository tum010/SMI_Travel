/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.view.dao.TicketSummaryCommissionDao;
import com.smi.travel.datalayer.view.entity.ListTicketSummaryCommission;
import com.smi.travel.datalayer.view.entity.TicketSummaryCommissionView;
import com.smi.travel.util.UtilityFunction;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Kanokporn
 */
public class TicketSummaryCommissionImpl implements TicketSummaryCommissionDao{
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<ListTicketSummaryCommission> searchTicketSummaryCommission(String invoicefromdatePage, String invoicetodatePage, String issuefromdatePage, String issuetodatePage, String agentcomfromdatePage, String agentcomtodatePage, String ticketcomfromdatePage, String ticketcomtodatePage, String overfromdatePage, String overtodatePage, String littlefromdatePage, String littletodatePage, String agemtcomreceivefromdatePage, String agemtcomreceivetodatePage, String comrefundfromdatePage, String comrefundtodatePage, String addpayfromdatePage, String addpaytodatePage, String decreasepayfromdatePage, String decreasepaytodatePage, String typeRoutingPage, String routingDetailPage, String airlineCodePage, String agentCodePage, String agentNamePage, String ticketnoPagePage, String departmentPage, String salebyUserPage, String salebyNamePage, String termPayPage,String printby) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();  
        Date thisdate = new Date();
        List<ListTicketSummaryCommission> listSummaryCommission = new LinkedList<ListTicketSummaryCommission>();
        ListTicketSummaryCommission summaryCommission = new ListTicketSummaryCommission();
        List<TicketSummaryCommissionView> listDetail =  new LinkedList<TicketSummaryCommissionView>();
        List<TicketSummaryCommissionView> listAir =  new LinkedList<TicketSummaryCommissionView>();
        List<TicketSummaryCommissionView> listAgent =  new LinkedList<TicketSummaryCommissionView>();
        String querydata = "";
        String query = "";
        int checkQuery = 0;
        if( invoicefromdatePage == null && issuefromdatePage == null && agentcomfromdatePage == null && ticketcomfromdatePage == null && overfromdatePage == null && littlefromdatePage == null && agemtcomreceivefromdatePage == null && comrefundfromdatePage == null && addpayfromdatePage == null && decreasepayfromdatePage == null && typeRoutingPage == null && routingDetailPage == null && airlineCodePage == null && agentCodePage == null && ticketnoPagePage == null && departmentPage == null && salebyUserPage == null && termPayPage == null ){
            query = "SELECT * FROM `ticket_commission_detail_summary` invm ";
        }else if("".equals(invoicefromdatePage) && "".equals(issuefromdatePage)  && "".equals(agentcomfromdatePage)  && "".equals(ticketcomfromdatePage)  && "".equals(overfromdatePage)  && "".equals(littlefromdatePage)  && "".equals(agemtcomreceivefromdatePage)  && "".equals(comrefundfromdatePage)  && "".equals(addpayfromdatePage)  && "".equals(decreasepayfromdatePage)  && "".equals(typeRoutingPage)  && "".equals(routingDetailPage)  && "".equals(airlineCodePage)  && "".equals(agentCodePage)  && "".equals(ticketnoPagePage)  && "".equals(departmentPage)  && "".equals(salebyUserPage)  && "".equals(termPayPage)){
            query = "SELECT * FROM `ticket_commission_detail_summary` invm ";
        }else{
            query = "SELECT * FROM `ticket_commission_detail_summary` invm  Where";
        }
        
        if ((invoicefromdatePage != null )&&(!"".equalsIgnoreCase(invoicefromdatePage))) {
            if ((invoicetodatePage != null )&&(!"".equalsIgnoreCase(invoicetodatePage))) {
                if(checkQuery == 1){
                     query += " and invm.invdate  BETWEEN  '" + invoicefromdatePage + "' AND '" + invoicetodatePage + "' ";
                }else{
                    checkQuery = 1;
                     query += " invm.invdate  BETWEEN  '" + invoicefromdatePage + "' AND '" + invoicetodatePage + "' ";
                }
            }
        }
        if ((issuefromdatePage != null )&&(!"".equalsIgnoreCase(issuefromdatePage))) {
            if ((issuetodatePage != null )&&(!"".equalsIgnoreCase(issuetodatePage))) {
                if(checkQuery == 1){
                     query += " and invm.issuedate  BETWEEN  '" + issuefromdatePage + "' AND '" + issuetodatePage + "' ";
                }else{
                    checkQuery = 1;
                     query += " invm.issuedate  BETWEEN  '" + issuefromdatePage + "' AND '" + issuetodatePage + "' ";
                }
            }
        }
        if ((agentcomfromdatePage != null )&&(!"".equalsIgnoreCase(agentcomfromdatePage))) {
            if ((agentcomtodatePage != null )&&(!"".equalsIgnoreCase(agentcomtodatePage))) {
                if(checkQuery == 1){
                     query += " and invm.agentcomdate  BETWEEN  '" + agentcomfromdatePage + "' AND '" + agentcomtodatePage + "' ";
                }else{
                    checkQuery = 1;
                     query += " invm.agentcomdate  BETWEEN  '" + agentcomfromdatePage + "' AND '" + agentcomtodatePage + "' ";
                }
            }
        }
        if ((ticketcomfromdatePage != null )&&(!"".equalsIgnoreCase(ticketcomfromdatePage))) {
            if ((ticketcomtodatePage != null )&&(!"".equalsIgnoreCase(ticketcomtodatePage))) {
                if(checkQuery == 1){
                     query += " and invm.ticketcomdate  BETWEEN  '" + ticketcomfromdatePage + "' AND '" + ticketcomtodatePage + "' ";
                }else{
                    checkQuery = 1;
                     query += " invm.ticketcomdate  BETWEEN  '" + ticketcomfromdatePage + "' AND '" + ticketcomtodatePage + "' ";
                }
            }
        }
        if ((overfromdatePage != null )&&(!"".equalsIgnoreCase(overfromdatePage))) {
            if ((overtodatePage != null )&&(!"".equalsIgnoreCase(overtodatePage))) {
                if(checkQuery == 1){
                     query += " and invm.overdate  BETWEEN  '" + overfromdatePage + "' AND '" + overtodatePage + "' ";
                }else{
                    checkQuery = 1;
                     query += " invm.overdate  BETWEEN  '" + overfromdatePage + "' AND '" + overtodatePage + "' ";
                }
            }
        }
        if ((littlefromdatePage != null )&&(!"".equalsIgnoreCase(littlefromdatePage))) {
            if ((littletodatePage != null )&&(!"".equalsIgnoreCase(littletodatePage))) {
                if(checkQuery == 1){
                     query += " and invm.littledate  BETWEEN  '" + littlefromdatePage + "' AND '" + littletodatePage + "' ";
                }else{
                    checkQuery = 1;
                     query += " invm.littledate  BETWEEN  '" + littlefromdatePage + "' AND '" + littletodatePage + "' ";
                }
            }
        }
        if ((agemtcomreceivefromdatePage != null )&&(!"".equalsIgnoreCase(agemtcomreceivefromdatePage))) {
            if ((agemtcomreceivetodatePage != null )&&(!"".equalsIgnoreCase(agemtcomreceivetodatePage))) {
                if(checkQuery == 1){
                     query += " and invm.agentcomreceivedate  BETWEEN  '" + agemtcomreceivefromdatePage + "' AND '" + agemtcomreceivetodatePage + "' ";
                }else{
                    checkQuery = 1;
                     query += " invm.agentcomreceivedate  BETWEEN  '" + agemtcomreceivefromdatePage + "' AND '" + agemtcomreceivetodatePage + "' ";
                }
            }
        }
        if ((comrefundfromdatePage != null )&&(!"".equalsIgnoreCase(comrefundfromdatePage))) {
            if ((comrefundtodatePage != null )&&(!"".equalsIgnoreCase(comrefundtodatePage))) {
                if(checkQuery == 1){
                     query += " and invm.comrefunddate  BETWEEN  '" + comrefundfromdatePage + "' AND '" + comrefundtodatePage + "' ";
                }else{
                    checkQuery = 1;
                     query += " invm.comrefunddate  BETWEEN  '" + comrefundfromdatePage + "' AND '" + comrefundtodatePage + "' ";
                }
            }
        }
        if ((addpayfromdatePage != null )&&(!"".equalsIgnoreCase(addpayfromdatePage))) {
            if ((addpaytodatePage != null )&&(!"".equalsIgnoreCase(addpaytodatePage))) {
                if(checkQuery == 1){
                     query += " and invm.addpaydate  BETWEEN  '" + addpayfromdatePage + "' AND '" + addpaytodatePage + "' ";
                }else{
                    checkQuery = 1;
                     query += " invm.addpaydate  BETWEEN  '" + addpayfromdatePage + "' AND '" + addpaytodatePage + "' ";
                }
            }
        }
        if ((decreasepayfromdatePage != null )&&(!"".equalsIgnoreCase(decreasepayfromdatePage))) {
            if ((decreasepaytodatePage != null )&&(!"".equalsIgnoreCase(decreasepaytodatePage))) {
                if(checkQuery == 1){
                     query += " and invm.decreasepaydate  BETWEEN  '" + decreasepayfromdatePage + "' AND '" + decreasepaytodatePage + "' ";
                }else{
                    checkQuery = 1;
                     query += " invm.decreasepaydate  BETWEEN  '" + decreasepayfromdatePage + "' AND '" + decreasepaytodatePage + "' ";
                }
            }
        }
        if((ticketnoPagePage != null) &&(!"".equalsIgnoreCase(ticketnoPagePage))){
            if(checkQuery == 1){
                query += " and invm.ticketno  = " + ticketnoPagePage + "' ";
            }else{
                checkQuery = 1;
                query += " invm.ticketno  = " + ticketnoPagePage + "' ";
            }
        }
         
        if((salebyNamePage != null) &&(!"".equalsIgnoreCase(salebyNamePage))){
            if(checkQuery == 1){
                query += " and invm.owner  = " + salebyNamePage + "' ";
            }else{
                checkQuery = 1;
                query += " invm.owner  = " + salebyNamePage + "' ";
            }
        }
         
        if((termPayPage != null) &&(!"".equalsIgnoreCase(termPayPage))){
            if(checkQuery == 1){
                query += " and invm.termpay  = " + termPayPage + "' ";
            }else{
                checkQuery = 1;
                query += " invm.termpay  = " + termPayPage + "' ";
            }
        }
        
        if((typeRoutingPage != null) &&(!"".equalsIgnoreCase(typeRoutingPage))){
            if(checkQuery == 1){
                query += " and invm.type  = " + typeRoutingPage + "' ";
            }else{
                checkQuery = 1;
                query += " invm.type  = " + typeRoutingPage + "' ";
            }
        }
        
        if((routingDetailPage != null) &&(!"".equalsIgnoreCase(routingDetailPage))){
            if(checkQuery == 1){
                query += " and invm.routingdetail  = " + routingDetailPage + "' ";
            }else{
                checkQuery = 1;
                query += " invm.routingdetail  = " + routingDetailPage + "' ";
            }
        }
        
        if((airlineCodePage != null) &&(!"".equalsIgnoreCase(airlineCodePage))){
            if(checkQuery == 1){
                query += " and invm.air  = " + airlineCodePage + "' ";
            }else{
                checkQuery = 1;
                query += " invm.air  = " + airlineCodePage + "' ";
            }
        }
         
        System.out.println("query : "+query);
        
        List<Object[]> ticketSummaryCommissionList = session.createSQLQuery(query)
                .addScalar("invno", Hibernate.STRING)					
                .addScalar("invdate", Hibernate.STRING)					
                .addScalar("department", Hibernate.STRING)				
                .addScalar("owner", Hibernate.STRING)					
                .addScalar("termPay", Hibernate.STRING)					
                .addScalar("agent", Hibernate.STRING)					
                .addScalar("type", Hibernate.STRING)					
                .addScalar("buy", Hibernate.STRING)					
                .addScalar("pax", Hibernate.STRING)					
                .addScalar("air", Hibernate.STRING)					
                .addScalar("docno", Hibernate.STRING)
                .addScalar("refno", Hibernate.STRING)					
                .addScalar("issuedate", Hibernate.STRING)					
                .addScalar("amountwendy", Hibernate.STRING)					
                .addScalar("amountoutbound", Hibernate.STRING)					
                .addScalar("sale", Hibernate.STRING)					
                .addScalar("cost", Hibernate.STRING)					
                .addScalar("over", Hibernate.STRING)					
                .addScalar("add", Hibernate.STRING)					
                .addScalar("dres", Hibernate.STRING)					
                .addScalar("profit", Hibernate.STRING)					
                .addScalar("ticcomm", Hibernate.STRING)					
                .addScalar("little", Hibernate.STRING)					
                .addScalar("agentcomm", Hibernate.STRING)					
                .addScalar("pay", Hibernate.STRING)					
                .addScalar("comm", Hibernate.STRING)
                .addScalar("routingdetail", Hibernate.STRING)
                .addScalar("overdate", Hibernate.STRING)
                .addScalar("littledate", Hibernate.STRING)
                .addScalar("agentcomdate", Hibernate.STRING)
                .addScalar("comrefunddate", Hibernate.STRING)
                .addScalar("ticketcomdate", Hibernate.STRING)
                .addScalar("agentcomreceivedate", Hibernate.STRING)
                .addScalar("addpaydate", Hibernate.STRING)
                .addScalar("decreasepaydate", Hibernate.STRING)
                .addScalar("ticketno", Hibernate.STRING)
                .list();
        
        if(ticketSummaryCommissionList != null && ticketSummaryCommissionList.size() != 0){
            for (Object[] B : ticketSummaryCommissionList) {
                TicketSummaryCommissionView ticket = new TicketSummaryCommissionView();
                //header
                if(invoicefromdatePage != null && !"".equals(invoicefromdatePage)){
                   String date = ""+ invoicefromdatePage + " To " + invoicetodatePage;
                   ticket.setInvoicefromdatePage(date);
                }else{
                    ticket.setInvoicefromdatePage("");
                }
                if(issuefromdatePage != null && !"".equals(issuefromdatePage)){
                   String date = ""+ issuefromdatePage + " To " + issuetodatePage;
                   ticket.setIssuefromdatePage(date);
                }else{
                    ticket.setIssuefromdatePage("");
                }
                if(agentcomfromdatePage != null && !"".equals(agentcomfromdatePage)){
                   String date = ""+ agentcomfromdatePage + " To " + agentcomtodatePage;
                   ticket.setAgentcomfromdatePage(date);
                }else{
                    ticket.setAgentcomfromdatePage("");
                }
                if(ticketcomfromdatePage != null && !"".equals(ticketcomfromdatePage)){
                   String date = ""+ ticketcomfromdatePage + " To " + ticketcomtodatePage;
                   ticket.setTicketcomfromdatePage(date);
                }else{
                    ticket.setTicketcomfromdatePage("");
                }
                if(overfromdatePage != null && !"".equals(overfromdatePage)){
                   String date = ""+ overfromdatePage + " To " + overtodatePage;
                   ticket.setOverfromdatePage(date);
                }else{
                    ticket.setOverfromdatePage("");
                }
                if(littlefromdatePage != null && !"".equals(littlefromdatePage)){
                   String date = ""+ littlefromdatePage + " To " + littletodatePage;
                   ticket.setLittlefromdatePage(date);
                }else{
                    ticket.setLittlefromdatePage("");
                }
                if(agemtcomreceivefromdatePage != null && !"".equals(agemtcomreceivefromdatePage)){
                   String date = ""+ agemtcomreceivefromdatePage + " To " + agemtcomreceivetodatePage;
                   ticket.setAgemtcomreceivefromdatePage(date);
                }else{
                    ticket.setAgemtcomreceivefromdatePage("");
                }
                if(comrefundfromdatePage != null && !"".equals(comrefundfromdatePage)){
                   String date = ""+ comrefundfromdatePage + " To " + comrefundtodatePage;
                   ticket.setComrefundfromdatePage(date);
                }else{
                    ticket.setComrefundfromdatePage("");
                }
                if(addpayfromdatePage != null && !"".equals(addpayfromdatePage)){
                   String date = ""+ addpayfromdatePage + " To " + addpaytodatePage;
                   ticket.setAddpayfromdatePage(date);
                }else{
                    ticket.setAddpayfromdatePage("");
                }
                if(decreasepayfromdatePage != null && !"".equals(decreasepayfromdatePage)){
                   String date = ""+ decreasepayfromdatePage + " To " + decreasepaytodatePage;
                   ticket.setDecreasepayfromdatePage(date);
                }else{
                    ticket.setDecreasepayfromdatePage("");
                }
                if(typeRoutingPage != null && !"".equals(typeRoutingPage)){
                    ticket.setTypeRoutingPage(typeRoutingPage);
                }else{
                    ticket.setTypeRoutingPage("");
                }
                if(routingDetailPage != null && !"".equals(routingDetailPage)){
                    ticket.setRoutingDetailPage(routingDetailPage);
                }else{
                    ticket.setRoutingDetailPage("");
                }
                if(airlineCodePage != null && !"".equals(airlineCodePage)){
                    ticket.setAirlineCodePage(airlineCodePage);
                }else{
                    ticket.setAirlineCodePage("");
                }
                if(agentNamePage != null && !"".equals(agentNamePage)){
                    ticket.setAgentNamePage(agentNamePage);
                }else{
                    ticket.setAgentNamePage("");
                }
                if(departmentPage != null && !"".equals(departmentPage)){
                    ticket.setDepartmentPage(departmentPage);
                }else{
                    ticket.setDepartmentPage("");
                }
                if(salebyNamePage != null && !"".equals(salebyNamePage)){
                    ticket.setSalebyNamePage(salebyNamePage);
                }else{
                    ticket.setSalebyNamePage("");
                }
                if(termPayPage != null && !"".equals(termPayPage)){
                    ticket.setTermPayPage(termPayPage);
                }else{
                    ticket.setTermPayPage("");
                }

                ticket.setPrintbyPage(printby);
                Date date = new Date();
                SimpleDateFormat sm = new SimpleDateFormat("dd/MM/yyyy");
                String strDate = sm.format(date);
                ticket.setPrintonPage(strDate);

                // set data  detail
                ticket.setInvno(util.ConvertString(B[0]) == "" ? "" : util.ConvertString(B[0]));
                ticket.setInvdate(util.ConvertString(B[1]) == "" ? "" : util.ConvertString(B[1]));
                ticket.setDepartment((util.ConvertString(B[2])) == "" ? "" : util.ConvertString(B[2]));
                ticket.setOwner((util.ConvertString(B[3])) == "" ? "" : util.ConvertString(B[3]));
                ticket.setTermpay((util.ConvertString(B[4])) == "" ? "" : util.ConvertString(B[4]));
                ticket.setAgent((util.ConvertString(B[5])) == "" ? "" : util.ConvertString(B[5]));
                ticket.setType((util.ConvertString(B[6])) == "" ? "" : util.ConvertString(B[6]));
                ticket.setBuy((util.ConvertString(B[7])) == "" ? "" : util.ConvertString(B[7]));
                ticket.setPax((util.ConvertString(B[8])) == "" ? "" : util.ConvertString(B[8]));
                ticket.setAir((util.ConvertString(B[9])) == "" ? "" : util.ConvertString(B[9]));
                ticket.setDocno((util.ConvertString(B[10])) == "" ? "" : util.ConvertString(B[10]));
                ticket.setRefno((util.ConvertString(B[11])) == "" ? "" : util.ConvertString(B[11]));
                ticket.setIssuedate((util.ConvertString(B[12])) == "" ? "" : util.ConvertString(B[12]));
                ticket.setAmountwendy((util.ConvertString(B[13])) == "" ? "0.00" : util.ConvertString(B[13]));
                ticket.setAmountoutbound((util.ConvertString(B[14])) == "" ? "0.00" : util.ConvertString(B[14]));
                ticket.setSale((util.ConvertString(B[15])) == "" ? "0.00" : util.ConvertString(B[15]));
                ticket.setCost((util.ConvertString(B[16])) == "" ? "0.00" : util.ConvertString(B[16]));
                ticket.setOver((util.ConvertString(B[17])) == "" ? "0.00" : util.ConvertString(B[17]));
                ticket.setAdd((util.ConvertString(B[18])) == "" ? "0.00" : util.ConvertString(B[18]));
                ticket.setDres((util.ConvertString(B[19])) == "" ? "0.00" : util.ConvertString(B[19]));
                ticket.setProfit((util.ConvertString(B[20])) == "" ? "0.00" : util.ConvertString(B[20]));
                ticket.setTiccomm((util.ConvertString(B[21])) == "" ? "0.00" : util.ConvertString(B[21]));
                ticket.setLittle((util.ConvertString(B[22])) == "" ? "0.00" : util.ConvertString(B[22]));
                ticket.setAgentcomm((util.ConvertString(B[23])) == "" ? "0.00" : util.ConvertString(B[23]));
                ticket.setPay((util.ConvertString(B[24])) == "" ? "0.00" : util.ConvertString(B[24]));
                ticket.setComm((util.ConvertString(B[25])) == "" ? "0.00" : util.ConvertString(B[25]));

                listDetail.add(ticket);
            }    
        }
        summaryCommission.setTicketCommissionDetailSummary(listDetail);
        summaryCommission.setTicketCommissionAirSummary(listDetail);
        summaryCommission.setTicketCommissionAgentSummary(listDetail);
        listSummaryCommission.add(summaryCommission);
    return listSummaryCommission;
    }   
}
