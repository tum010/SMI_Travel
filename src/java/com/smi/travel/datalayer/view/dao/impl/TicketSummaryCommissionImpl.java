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
    public List<ListTicketSummaryCommission> searchTicketSummaryCommission(String invoicefromdatePage, String invoicetodatePage, String issuefromdatePage, String issuetodatePage,String overfromdatePage, String overtodatePage, String littlefromdatePage, String littletodatePage, String agemtcomreceivefromdatePage, String agemtcomreceivetodatePage, String comrefundfromdatePage, String comrefundtodatePage, String addpayfromdatePage, String addpaytodatePage, String decreasepayfromdatePage, String decreasepaytodatePage, String typeRoutingPage, String routingDetailPage, String airlineCodePage, String agentCodePage, String agentNamePage, String ticketnoPagePage, String departmentPage, String salebyUserPage, String salebyNamePage, String termPayPage,String printby) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();  
        Date thisdate = new Date();
        List<ListTicketSummaryCommission> listSummaryCommission = new LinkedList<ListTicketSummaryCommission>();
        ListTicketSummaryCommission summaryCommission = new ListTicketSummaryCommission();
        List<TicketSummaryCommissionView> listDetail =  new LinkedList<TicketSummaryCommissionView>();
        List<TicketSummaryCommissionView> listAir =  new LinkedList<TicketSummaryCommissionView>();
        List<TicketSummaryCommissionView> listAgent =  new LinkedList<TicketSummaryCommissionView>();
        String querydata = "";
        String querydetail = "";
        String queryair = "";
        String queryagent = "";
        int checkQuery = 0;
        if( invoicefromdatePage == null && issuefromdatePage == null && overfromdatePage == null && littlefromdatePage == null && agemtcomreceivefromdatePage == null && comrefundfromdatePage == null && addpayfromdatePage == null && decreasepayfromdatePage == null && typeRoutingPage == null && routingDetailPage == null && airlineCodePage == null && agentCodePage == null && ticketnoPagePage == null && departmentPage == null && salebyUserPage == null && termPayPage == null ){
            // detail    
            querydetail = "select `inv`.`inv_no` AS `invno`,`inv`.`inv_date` AS `invdate`,`fare`.`department` AS `department`,`fare`.`owner` AS `owner`,`inv`.`term_pay` AS `termpay`,`fare`.`agent_id` AS `agent`,`fare`.`ticket_type` AS `type`,`fare`.`ticket_buy` AS `buy`,`fare`.`ticket_no` AS `pax`,substr(`fare`.`ticket_no`,1,3) AS `air`,substr(`fare`.`ticket_no`,4) AS `docno`,`mt`.`Reference No` AS `refno`,`fare`.`issue_date` AS `issuedate`,(case when (`fare`.`department` = 'Wendy') then `fare`.`inv_amount` else NULL end) AS `amountwendy`,(case when (`fare`.`department` = 'Outbound') then `fare`.`inv_amount` else NULL end) AS `amountoutbound`,(case when (`fare`.`department` = 'Inbound') then `fare`.`inv_amount` else NULL end) AS `amountinbound`,`fare`.`sale_price` AS `sale`,`GET_COST_TICKETFARE`(`fare`.`id`) AS `cost`,`fare`.`over_commission` AS `over`,`fare`.`add_pay` AS `add`,`fare`.`dec_pay` AS `dres`,(`fare`.`inv_amount` - `GET_COST_TICKETFARE`(`fare`.`id`)) AS `profit`,`fare`.`ticket_commission` AS `ticcomm`,`fare`.`litter_commission` AS `little`,`fare`.`agent_com_pay` AS `agentcommpay`,`fare`.`agent_com_receive` AS `agentcommrec`,`fare`.`agent_commission` AS `pay`,(((`fare`.`ticket_commission` + `fare`.`litter_commission`) - `fare`.`over_commission`) - `fare`.`agent_commission`) AS `comm`,`term`.`name` AS `termpayname`,`agt`.`name` AS `agentname` from ((((((`ticket_fare_airline` `fare` left join `ticket_fare_invoice` `finv` on((`finv`.`ticket_fare_id` = `fare`.`id`))) left join `invoice` `inv` on((`inv`.`id` = `finv`.`invoice_id`))) left join `m_accterm` `term` on((`term`.`id` = `inv`.`term_pay`))) left join `staff` `st` on((`st`.`name` = `fare`.`owner`))) left join `master` `mt` on((`mt`.`id` = `fare`.`master_id`))) left join `agent` `agt` on((`agt`.`id` = `fare`.`agent_id`)))"; 
            // air
            queryair = "SELECT ( CASE WHEN (`fare`.`ticket_type` = 'B') THEN 'BSP' WHEN (`fare`.`ticket_type` = 'A') THEN 'AGENT' WHEN (`fare`.`ticket_type` = 'D') THEN 'DOMESTIC' WHEN (`fare`.`ticket_type` = 'T') THEN 'TG' ELSE `fare`.`ticket_type` END ) AS `typepayment`, `fare`.`ticket_rounting` AS `typerounting`, count(`fare`.`ticket_no`) AS `pax`, substr(`fare`.`ticket_no`, 1, 3) AS `air`, '' AS `refno`, `fare`.`issue_date` AS `issuedate`, ( CASE WHEN ( `fare`.`department` = 'Wendy' ) THEN sum(`fare`.`inv_amount`) ELSE NULL END ) AS `amountwendy`, ( CASE WHEN ( `fare`.`department` = 'Outbound' ) THEN sum(`fare`.`inv_amount`) ELSE NULL END ) AS `amountoutbound`, ( CASE WHEN ( `fare`.`department` = 'Inbound' ) THEN sum(`fare`.`inv_amount`) ELSE NULL END ) AS `amountinbound`, sum(`fare`.`sale_price`) AS `sale`, sum( `GET_COST_TICKETFARE` (`fare`.`id`)) AS `cost`, sum(`fare`.`over_commission`) AS `over`, sum(`fare`.`add_pay`) AS `add`, sum(`fare`.`dec_pay`) AS `dres`, sum(( `fare`.`inv_amount` - `GET_COST_TICKETFARE` (`fare`.`id`))) AS `profit`, sum(`fare`.`ticket_commission`) AS `ticcomm`, sum(`fare`.`litter_commission`) AS `little`, sum(`fare`.`agent_com_pay`) AS `agentcommpay`, sum(`fare`.`agent_com_receive`) AS `agentcommrec`, sum(`fare`.`agent_commission`) AS `pay`, sum(((( `fare`.`ticket_commission` + `fare`.`litter_commission` ) - `fare`.`over_commission` ) - `fare`.`agent_commission` )) AS `comm` FROM (((( `ticket_fare_airline` `fare` LEFT JOIN `ticket_fare_invoice` `finv` ON (( `finv`.`ticket_fare_id` = `fare`.`id` ))) LEFT JOIN `invoice` `inv` ON (( `inv`.`id` = `finv`.`invoice_id` ))) LEFT JOIN `m_accterm` `term` ON (( `term`.`id` = `inv`.`term_pay` ))) LEFT JOIN `staff` `st` ON ((`st`.`name` = `fare`.`owner`))) "; 
            //agent
            queryagent = "select `inv`.`inv_name` AS `agentname`,count(`fare`.`ticket_no`) AS `pax`,`mt`.`Reference No` AS `refno`,`fare`.`issue_date` AS `issuedate`,(case when (`fare`.`department` = 'Wendy') then sum(`fare`.`inv_amount`) else NULL end) AS `amountwendy`,(case when (`fare`.`department` = 'Outbound') then sum(`fare`.`inv_amount`) else NULL end) AS `amountoutbound`,(case when (`fare`.`department` = 'Inbound') then sum(`fare`.`inv_amount`) else NULL end) AS `amountinbound`,sum(`fare`.`sale_price`) AS `sale`,sum(`GET_COST_TICKETFARE`(`fare`.`id`)) AS `cost`,sum(`fare`.`over_commission`) AS `over`,sum(`fare`.`add_pay`) AS `add`,sum(`fare`.`dec_pay`) AS `dres`,sum((`fare`.`inv_amount` - `GET_COST_TICKETFARE`(`fare`.`id`))) AS `profit`,sum(`fare`.`ticket_commission`) AS `ticcomm`,sum(`fare`.`litter_commission`) AS `little`,sum(`fare`.`agent_com_pay`) AS `agentcommpay`,sum(`fare`.`agent_com_receive`) AS `agentcommrec`,sum(`fare`.`agent_commission`) AS `pay`,sum((((`fare`.`ticket_commission` + `fare`.`litter_commission`) - `fare`.`over_commission`) - `fare`.`agent_commission`)) AS `comm` from (((((`ticket_fare_airline` `fare` left join `ticket_fare_invoice` `finv` on((`finv`.`ticket_fare_id` = `fare`.`id`))) left join `invoice` `inv` on((`inv`.`id` = `finv`.`invoice_id`))) left join `m_accterm` `term` on((`term`.`id` = `inv`.`term_pay`))) left join `staff` `st` on((`st`.`name` = `fare`.`owner`))) left join `master` `mt` on((`mt`.`id` = `fare`.`master_id`))) "; 
        }else if("".equals(invoicefromdatePage) && "".equals(issuefromdatePage) && "".equals(overfromdatePage)  && "".equals(littlefromdatePage)  && "".equals(agemtcomreceivefromdatePage)  && "".equals(comrefundfromdatePage)  && "".equals(addpayfromdatePage)  && "".equals(decreasepayfromdatePage)  && "".equals(typeRoutingPage)  && "".equals(routingDetailPage)  && "".equals(airlineCodePage)  && "".equals(agentCodePage)  && "".equals(ticketnoPagePage)  && "".equals(departmentPage)  && "".equals(salebyUserPage)  && "".equals(termPayPage)){
            querydetail = "select `inv`.`inv_no` AS `invno`,`inv`.`inv_date` AS `invdate`,`fare`.`department` AS `department`,`fare`.`owner` AS `owner`,`inv`.`term_pay` AS `termpay`,`fare`.`agent_id` AS `agent`,`fare`.`ticket_type` AS `type`,`fare`.`ticket_buy` AS `buy`,`fare`.`ticket_no` AS `pax`,substr(`fare`.`ticket_no`,1,3) AS `air`,substr(`fare`.`ticket_no`,4) AS `docno`,`mt`.`Reference No` AS `refno`,`fare`.`issue_date` AS `issuedate`,(case when (`fare`.`department` = 'Wendy') then `fare`.`inv_amount` else NULL end) AS `amountwendy`,(case when (`fare`.`department` = 'Outbound') then `fare`.`inv_amount` else NULL end) AS `amountoutbound`,(case when (`fare`.`department` = 'Inbound') then `fare`.`inv_amount` else NULL end) AS `amountinbound`,`fare`.`sale_price` AS `sale`,`GET_COST_TICKETFARE`(`fare`.`id`) AS `cost`,`fare`.`over_commission` AS `over`,`fare`.`add_pay` AS `add`,`fare`.`dec_pay` AS `dres`,(`fare`.`inv_amount` - `GET_COST_TICKETFARE`(`fare`.`id`)) AS `profit`,`fare`.`ticket_commission` AS `ticcomm`,`fare`.`litter_commission` AS `little`,`fare`.`agent_com_pay` AS `agentcommpay`,`fare`.`agent_com_receive` AS `agentcommrec`,`fare`.`agent_commission` AS `pay`,(((`fare`.`ticket_commission` + `fare`.`litter_commission`) - `fare`.`over_commission`) - `fare`.`agent_commission`) AS `comm`,`term`.`name` AS `termpayname`,`agt`.`name` AS `agentname` from ((((((`ticket_fare_airline` `fare` left join `ticket_fare_invoice` `finv` on((`finv`.`ticket_fare_id` = `fare`.`id`))) left join `invoice` `inv` on((`inv`.`id` = `finv`.`invoice_id`))) left join `m_accterm` `term` on((`term`.`id` = `inv`.`term_pay`))) left join `staff` `st` on((`st`.`name` = `fare`.`owner`))) left join `master` `mt` on((`mt`.`id` = `fare`.`master_id`))) left join `agent` `agt` on((`agt`.`id` = `fare`.`agent_id`)))";
            queryair = "SELECT ( CASE WHEN (`fare`.`ticket_type` = 'B') THEN 'BSP' WHEN (`fare`.`ticket_type` = 'A') THEN 'AGENT' WHEN (`fare`.`ticket_type` = 'D') THEN 'DOMESTIC' WHEN (`fare`.`ticket_type` = 'T') THEN 'TG' ELSE `fare`.`ticket_type` END ) AS `typepayment`, `fare`.`ticket_rounting` AS `typerounting`, count(`fare`.`ticket_no`) AS `pax`, substr(`fare`.`ticket_no`, 1, 3) AS `air`, '' AS `refno`, `fare`.`issue_date` AS `issuedate`, ( CASE WHEN ( `fare`.`department` = 'Wendy' ) THEN sum(`fare`.`inv_amount`) ELSE NULL END ) AS `amountwendy`, ( CASE WHEN ( `fare`.`department` = 'Outbound' ) THEN sum(`fare`.`inv_amount`) ELSE NULL END ) AS `amountoutbound`, ( CASE WHEN ( `fare`.`department` = 'Inbound' ) THEN sum(`fare`.`inv_amount`) ELSE NULL END ) AS `amountinbound`, sum(`fare`.`sale_price`) AS `sale`, sum( `GET_COST_TICKETFARE` (`fare`.`id`)) AS `cost`, sum(`fare`.`over_commission`) AS `over`, sum(`fare`.`add_pay`) AS `add`, sum(`fare`.`dec_pay`) AS `dres`, sum(( `fare`.`inv_amount` - `GET_COST_TICKETFARE` (`fare`.`id`))) AS `profit`, sum(`fare`.`ticket_commission`) AS `ticcomm`, sum(`fare`.`litter_commission`) AS `little`, sum(`fare`.`agent_com_pay`) AS `agentcommpay`, sum(`fare`.`agent_com_receive`) AS `agentcommrec`, sum(`fare`.`agent_commission`) AS `pay`, sum(((( `fare`.`ticket_commission` + `fare`.`litter_commission` ) - `fare`.`over_commission` ) - `fare`.`agent_commission` )) AS `comm` FROM (((( `ticket_fare_airline` `fare` LEFT JOIN `ticket_fare_invoice` `finv` ON (( `finv`.`ticket_fare_id` = `fare`.`id` ))) LEFT JOIN `invoice` `inv` ON (( `inv`.`id` = `finv`.`invoice_id` ))) LEFT JOIN `m_accterm` `term` ON (( `term`.`id` = `inv`.`term_pay` ))) LEFT JOIN `staff` `st` ON ((`st`.`name` = `fare`.`owner`))) ";
            queryagent = "select `inv`.`inv_name` AS `agentname`,count(`fare`.`ticket_no`) AS `pax`,`mt`.`Reference No` AS `refno`,`fare`.`issue_date` AS `issuedate`,(case when (`fare`.`department` = 'Wendy') then sum(`fare`.`inv_amount`) else NULL end) AS `amountwendy`,(case when (`fare`.`department` = 'Outbound') then sum(`fare`.`inv_amount`) else NULL end) AS `amountoutbound`,(case when (`fare`.`department` = 'Inbound') then sum(`fare`.`inv_amount`) else NULL end) AS `amountinbound`,sum(`fare`.`sale_price`) AS `sale`,sum(`GET_COST_TICKETFARE`(`fare`.`id`)) AS `cost`,sum(`fare`.`over_commission`) AS `over`,sum(`fare`.`add_pay`) AS `add`,sum(`fare`.`dec_pay`) AS `dres`,sum((`fare`.`inv_amount` - `GET_COST_TICKETFARE`(`fare`.`id`))) AS `profit`,sum(`fare`.`ticket_commission`) AS `ticcomm`,sum(`fare`.`litter_commission`) AS `little`,sum(`fare`.`agent_com_pay`) AS `agentcommpay`,sum(`fare`.`agent_com_receive`) AS `agentcommrec`,sum(`fare`.`agent_commission`) AS `pay`,sum((((`fare`.`ticket_commission` + `fare`.`litter_commission`) - `fare`.`over_commission`) - `fare`.`agent_commission`)) AS `comm` from (((((`ticket_fare_airline` `fare` left join `ticket_fare_invoice` `finv` on((`finv`.`ticket_fare_id` = `fare`.`id`))) left join `invoice` `inv` on((`inv`.`id` = `finv`.`invoice_id`))) left join `m_accterm` `term` on((`term`.`id` = `inv`.`term_pay`))) left join `staff` `st` on((`st`.`name` = `fare`.`owner`))) left join `master` `mt` on((`mt`.`id` = `fare`.`master_id`))) ";
        }else{
            querydetail = "select `inv`.`inv_no` AS `invno`,`inv`.`inv_date` AS `invdate`,`fare`.`department` AS `department`,`fare`.`owner` AS `owner`,`inv`.`term_pay` AS `termpay`,`fare`.`agent_id` AS `agent`,`fare`.`ticket_type` AS `type`,`fare`.`ticket_buy` AS `buy`,`fare`.`ticket_no` AS `pax`,substr(`fare`.`ticket_no`,1,3) AS `air`,substr(`fare`.`ticket_no`,4) AS `docno`,`mt`.`Reference No` AS `refno`,`fare`.`issue_date` AS `issuedate`,(case when (`fare`.`department` = 'Wendy') then `fare`.`inv_amount` else NULL end) AS `amountwendy`,(case when (`fare`.`department` = 'Outbound') then `fare`.`inv_amount` else NULL end) AS `amountoutbound`,(case when (`fare`.`department` = 'Inbound') then `fare`.`inv_amount` else NULL end) AS `amountinbound`,`fare`.`sale_price` AS `sale`,`GET_COST_TICKETFARE`(`fare`.`id`) AS `cost`,`fare`.`over_commission` AS `over`,`fare`.`add_pay` AS `add`,`fare`.`dec_pay` AS `dres`,(`fare`.`inv_amount` - `GET_COST_TICKETFARE`(`fare`.`id`)) AS `profit`,`fare`.`ticket_commission` AS `ticcomm`,`fare`.`litter_commission` AS `little`,`fare`.`agent_com_pay` AS `agentcommpay`,`fare`.`agent_com_receive` AS `agentcommrec`,`fare`.`agent_commission` AS `pay`,(((`fare`.`ticket_commission` + `fare`.`litter_commission`) - `fare`.`over_commission`) - `fare`.`agent_commission`) AS `comm`,`term`.`name` AS `termpayname`,`agt`.`name` AS `agentname` from ((((((`ticket_fare_airline` `fare` left join `ticket_fare_invoice` `finv` on((`finv`.`ticket_fare_id` = `fare`.`id`))) left join `invoice` `inv` on((`inv`.`id` = `finv`.`invoice_id`))) left join `m_accterm` `term` on((`term`.`id` = `inv`.`term_pay`))) left join `staff` `st` on((`st`.`name` = `fare`.`owner`))) left join `master` `mt` on((`mt`.`id` = `fare`.`master_id`))) left join `agent` `agt` on((`agt`.`id` = `fare`.`agent_id`))) Where ";
            queryair = "SELECT ( CASE WHEN (`fare`.`ticket_type` = 'B') THEN 'BSP' WHEN (`fare`.`ticket_type` = 'A') THEN 'AGENT' WHEN (`fare`.`ticket_type` = 'D') THEN 'DOMESTIC' WHEN (`fare`.`ticket_type` = 'T') THEN 'TG' ELSE `fare`.`ticket_type` END ) AS `typepayment`, `fare`.`ticket_rounting` AS `typerounting`, count(`fare`.`ticket_no`) AS `pax`, substr(`fare`.`ticket_no`, 1, 3) AS `air`, '' AS `refno`, `fare`.`issue_date` AS `issuedate`, ( CASE WHEN ( `fare`.`department` = 'Wendy' ) THEN sum(`fare`.`inv_amount`) ELSE NULL END ) AS `amountwendy`, ( CASE WHEN ( `fare`.`department` = 'Outbound' ) THEN sum(`fare`.`inv_amount`) ELSE NULL END ) AS `amountoutbound`, ( CASE WHEN ( `fare`.`department` = 'Inbound' ) THEN sum(`fare`.`inv_amount`) ELSE NULL END ) AS `amountinbound`, sum(`fare`.`sale_price`) AS `sale`, sum( `GET_COST_TICKETFARE` (`fare`.`id`)) AS `cost`, sum(`fare`.`over_commission`) AS `over`, sum(`fare`.`add_pay`) AS `add`, sum(`fare`.`dec_pay`) AS `dres`, sum(( `fare`.`inv_amount` - `GET_COST_TICKETFARE` (`fare`.`id`))) AS `profit`, sum(`fare`.`ticket_commission`) AS `ticcomm`, sum(`fare`.`litter_commission`) AS `little`, sum(`fare`.`agent_com_pay`) AS `agentcommpay`, sum(`fare`.`agent_com_receive`) AS `agentcommrec`, sum(`fare`.`agent_commission`) AS `pay`, sum(((( `fare`.`ticket_commission` + `fare`.`litter_commission` ) - `fare`.`over_commission` ) - `fare`.`agent_commission` )) AS `comm` FROM (((( `ticket_fare_airline` `fare` LEFT JOIN `ticket_fare_invoice` `finv` ON (( `finv`.`ticket_fare_id` = `fare`.`id` ))) LEFT JOIN `invoice` `inv` ON (( `inv`.`id` = `finv`.`invoice_id` ))) LEFT JOIN `m_accterm` `term` ON (( `term`.`id` = `inv`.`term_pay` ))) LEFT JOIN `staff` `st` ON ((`st`.`name` = `fare`.`owner`))) Where ";
            queryagent = "select `inv`.`inv_name` AS `agentname`,count(`fare`.`ticket_no`) AS `pax`,`mt`.`Reference No` AS `refno`,`fare`.`issue_date` AS `issuedate`,(case when (`fare`.`department` = 'Wendy') then sum(`fare`.`inv_amount`) else NULL end) AS `amountwendy`,(case when (`fare`.`department` = 'Outbound') then sum(`fare`.`inv_amount`) else NULL end) AS `amountoutbound`,(case when (`fare`.`department` = 'Inbound') then sum(`fare`.`inv_amount`) else NULL end) AS `amountinbound`,sum(`fare`.`sale_price`) AS `sale`,sum(`GET_COST_TICKETFARE`(`fare`.`id`)) AS `cost`,sum(`fare`.`over_commission`) AS `over`,sum(`fare`.`add_pay`) AS `add`,sum(`fare`.`dec_pay`) AS `dres`,sum((`fare`.`inv_amount` - `GET_COST_TICKETFARE`(`fare`.`id`))) AS `profit`,sum(`fare`.`ticket_commission`) AS `ticcomm`,sum(`fare`.`litter_commission`) AS `little`,sum(`fare`.`agent_com_pay`) AS `agentcommpay`,sum(`fare`.`agent_com_receive`) AS `agentcommrec`,sum(`fare`.`agent_commission`) AS `pay`,sum((((`fare`.`ticket_commission` + `fare`.`litter_commission`) - `fare`.`over_commission`) - `fare`.`agent_commission`)) AS `comm` from (((((`ticket_fare_airline` `fare` left join `ticket_fare_invoice` `finv` on((`finv`.`ticket_fare_id` = `fare`.`id`))) left join `invoice` `inv` on((`inv`.`id` = `finv`.`invoice_id`))) left join `m_accterm` `term` on((`term`.`id` = `inv`.`term_pay`))) left join `staff` `st` on((`st`.`name` = `fare`.`owner`))) left join `master` `mt` on((`mt`.`id` = `fare`.`master_id`)))  Where ";
        }
        
        if ((invoicefromdatePage != null )&&(!"".equalsIgnoreCase(invoicefromdatePage))) {
            if ((invoicetodatePage != null )&&(!"".equalsIgnoreCase(invoicetodatePage))) {
                if(checkQuery == 1){
                     querydetail += " and `inv`.`inv_date` BETWEEN  '" + invoicefromdatePage + "' AND '" + invoicetodatePage + "' ";
                     queryair += " and `inv`.`inv_date` BETWEEN  '" + invoicefromdatePage + "' AND '" + invoicetodatePage + "' ";
                     queryagent += " and `inv`.`inv_date` BETWEEN  '" + invoicefromdatePage + "' AND '" + invoicetodatePage + "' ";
                }else{
                    checkQuery = 1;
                     querydetail += " `inv`.`inv_date` BETWEEN  '" + invoicefromdatePage + "' AND '" + invoicetodatePage + "' ";
                     queryair += " `inv`.`inv_date` BETWEEN  '" + invoicefromdatePage + "' AND '" + invoicetodatePage + "' ";
                     queryagent += " `inv`.`inv_date` BETWEEN  '" + invoicefromdatePage + "' AND '" + invoicetodatePage + "' ";
                }
            }
        }
        if ((issuefromdatePage != null )&&(!"".equalsIgnoreCase(issuefromdatePage))) {
            if ((issuetodatePage != null )&&(!"".equalsIgnoreCase(issuetodatePage))) {
                if(checkQuery == 1){
                    querydetail += " and `fare`.`issue_date` BETWEEN  '" + issuefromdatePage + "' AND '" + issuetodatePage + "' ";
                    queryair += " and `fare`.`issue_date` BETWEEN  '" + issuefromdatePage + "' AND '" + issuetodatePage + "' ";
                    queryagent += " and `fare`.`issue_date` BETWEEN  '" + issuefromdatePage + "' AND '" + issuetodatePage + "' ";
                }else{
                    checkQuery = 1;
                    querydetail += " `fare`.`issue_date` BETWEEN  '" + issuefromdatePage + "' AND '" + issuetodatePage + "' ";
                    queryair += " `fare`.`issue_date` BETWEEN  '" + issuefromdatePage + "' AND '" + issuetodatePage + "' ";
                    queryagent += " `fare`.`issue_date` BETWEEN  '" + issuefromdatePage + "' AND '" + issuetodatePage + "' ";
                }
            }
        }
//        if ((agentcomfromdatePage != null )&&(!"".equalsIgnoreCase(agentcomfromdatePage))) {
//            if ((agentcomtodatePage != null )&&(!"".equalsIgnoreCase(agentcomtodatePage))) {
//                if(checkQuery == 1){
//                     querydetail += " and invm.agentcomdate  BETWEEN  '" + agentcomfromdatePage + "' AND '" + agentcomtodatePage + "' ";
//                }else{
//                    checkQuery = 1;
//                     querydetail += " invm.agentcomdate  BETWEEN  '" + agentcomfromdatePage + "' AND '" + agentcomtodatePage + "' ";
//                }
//            }
//        }
//        if ((ticketcomfromdatePage != null )&&(!"".equalsIgnoreCase(ticketcomfromdatePage))) {
//            if ((ticketcomtodatePage != null )&&(!"".equalsIgnoreCase(ticketcomtodatePage))) {
//                if(checkQuery == 1){
//                     querydetail += " and invm.ticketcomdate  BETWEEN  '" + ticketcomfromdatePage + "' AND '" + ticketcomtodatePage + "' ";
//                }else{
//                    checkQuery = 1;
//                     querydetail += " invm.ticketcomdate  BETWEEN  '" + ticketcomfromdatePage + "' AND '" + ticketcomtodatePage + "' ";
//                }
//            }
//        }
        if ((overfromdatePage != null )&&(!"".equalsIgnoreCase(overfromdatePage))) {
            if ((overtodatePage != null )&&(!"".equalsIgnoreCase(overtodatePage))) {
                if(checkQuery == 1){
                    querydetail += " and `fare`.`over_date`  BETWEEN  '" + overfromdatePage + "' AND '" + overtodatePage + "' ";
                    queryair += " and `fare`.`over_date`  BETWEEN  '" + overfromdatePage + "' AND '" + overtodatePage + "' ";
                    queryagent += " and `fare`.`over_date`  BETWEEN  '" + overfromdatePage + "' AND '" + overtodatePage + "' ";
                }else{
                    checkQuery = 1;
                    querydetail += " `fare`.`over_date`  BETWEEN  '" + overfromdatePage + "' AND '" + overtodatePage + "' ";
                    queryair += " `fare`.`over_date`  BETWEEN  '" + overfromdatePage + "' AND '" + overtodatePage + "' ";
                    queryagent += " `fare`.`over_date`  BETWEEN  '" + overfromdatePage + "' AND '" + overtodatePage + "' ";
                }
            }
        }
        if ((littlefromdatePage != null )&&(!"".equalsIgnoreCase(littlefromdatePage))) {
            if ((littletodatePage != null )&&(!"".equalsIgnoreCase(littletodatePage))) {
                if(checkQuery == 1){
                    querydetail += " and `fare`.`litter_date` BETWEEN  '" + littlefromdatePage + "' AND '" + littletodatePage + "' ";
                    queryair += " and `fare`.`litter_date` BETWEEN  '" + littlefromdatePage + "' AND '" + littletodatePage + "' ";
                    queryagent += " and `fare`.`litter_date` BETWEEN  '" + littlefromdatePage + "' AND '" + littletodatePage + "' ";
                }else{
                    checkQuery = 1;
                    querydetail += " `fare`.`litter_date`  BETWEEN  '" + littlefromdatePage + "' AND '" + littletodatePage + "' ";
                    queryair += " `fare`.`litter_date`  BETWEEN  '" + littlefromdatePage + "' AND '" + littletodatePage + "' ";
                    queryagent += " `fare`.`litter_date`  BETWEEN  '" + littlefromdatePage + "' AND '" + littletodatePage + "' ";
                     
                }
            }
        }
        if ((agemtcomreceivefromdatePage != null )&&(!"".equalsIgnoreCase(agemtcomreceivefromdatePage))) {
            if ((agemtcomreceivetodatePage != null )&&(!"".equalsIgnoreCase(agemtcomreceivetodatePage))) {
                if(checkQuery == 1){
                    querydetail += " and `fare`.`agent_receive_date` BETWEEN  '" + agemtcomreceivefromdatePage + "' AND '" + agemtcomreceivetodatePage + "' ";
                    queryair += " and `fare`.`agent_receive_date` BETWEEN  '" + agemtcomreceivefromdatePage + "' AND '" + agemtcomreceivetodatePage + "' ";
                    queryagent += " and `fare`.`agent_receive_date` BETWEEN  '" + agemtcomreceivefromdatePage + "' AND '" + agemtcomreceivetodatePage + "' ";
                }else{
                    checkQuery = 1;
                    querydetail += " `fare`.`agent_receive_date` BETWEEN  '" + agemtcomreceivefromdatePage + "' AND '" + agemtcomreceivetodatePage + "' ";
                    queryair += " `fare`.`agent_receive_date` BETWEEN  '" + agemtcomreceivefromdatePage + "' AND '" + agemtcomreceivetodatePage + "' ";
                    queryagent += " `fare`.`agent_receive_date` BETWEEN  '" + agemtcomreceivefromdatePage + "' AND '" + agemtcomreceivetodatePage + "' ";
                }
            }
        }
        if ((comrefundfromdatePage != null )&&(!"".equalsIgnoreCase(comrefundfromdatePage))) {
            if ((comrefundtodatePage != null )&&(!"".equalsIgnoreCase(comrefundtodatePage))) {
                if(checkQuery == 1){
                     querydetail += " and fare.agent_pay_date  BETWEEN  '" + comrefundfromdatePage + "' AND '" + comrefundtodatePage + "' ";
                     queryair += " and fare.agent_pay_date  BETWEEN  '" + comrefundfromdatePage + "' AND '" + comrefundtodatePage + "' ";
                     queryagent += " and fare.agent_pay_date  BETWEEN  '" + comrefundfromdatePage + "' AND '" + comrefundtodatePage + "' ";
                }else{
                    checkQuery = 1;
                     querydetail += " fare.agent_pay_date BETWEEN  '" + comrefundfromdatePage + "' AND '" + comrefundtodatePage + "' ";
                     queryair += " fare.agent_pay_date BETWEEN  '" + comrefundfromdatePage + "' AND '" + comrefundtodatePage + "' ";
                     queryagent += " fare.agent_pay_date BETWEEN  '" + comrefundfromdatePage + "' AND '" + comrefundtodatePage + "' ";
                }
            }
        }
        if ((addpayfromdatePage != null )&&(!"".equalsIgnoreCase(addpayfromdatePage))) {
            if ((addpaytodatePage != null )&&(!"".equalsIgnoreCase(addpaytodatePage))) {
                if(checkQuery == 1){
                     querydetail += " and `fare`.`add_pay_date` BETWEEN  '" + addpayfromdatePage + "' AND '" + addpaytodatePage + "' ";
                     queryair += " and `fare`.`add_pay_date` BETWEEN  '" + addpayfromdatePage + "' AND '" + addpaytodatePage + "' ";
                     queryagent += " and `fare`.`add_pay_date` BETWEEN  '" + addpayfromdatePage + "' AND '" + addpaytodatePage + "' ";
                }else{
                    checkQuery = 1;
                     querydetail += " `fare`.`add_pay_date` BETWEEN  '" + addpayfromdatePage + "' AND '" + addpaytodatePage + "' ";
                     queryair += " `fare`.`add_pay_date` BETWEEN  '" + addpayfromdatePage + "' AND '" + addpaytodatePage + "' ";
                     queryagent += " `fare`.`add_pay_date` BETWEEN  '" + addpayfromdatePage + "' AND '" + addpaytodatePage + "' ";
                     
                }
            }
        }
        if ((decreasepayfromdatePage != null )&&(!"".equalsIgnoreCase(decreasepayfromdatePage))) {
            if ((decreasepaytodatePage != null )&&(!"".equalsIgnoreCase(decreasepaytodatePage))) {
                if(checkQuery == 1){
                     querydetail += " and `fare`.`dec_pay_date`  BETWEEN  '" + decreasepayfromdatePage + "' AND '" + decreasepaytodatePage + "' ";
                     queryair += " and `fare`.`dec_pay_date`  BETWEEN  '" + decreasepayfromdatePage + "' AND '" + decreasepaytodatePage + "' ";
                     queryagent += " and `fare`.`dec_pay_date`  BETWEEN  '" + decreasepayfromdatePage + "' AND '" + decreasepaytodatePage + "' ";
                }else{
                    checkQuery = 1;
                     querydetail += " `fare`.`dec_pay_date` BETWEEN  '" + decreasepayfromdatePage + "' AND '" + decreasepaytodatePage + "' ";
                     queryair += " `fare`.`dec_pay_date` BETWEEN  '" + decreasepayfromdatePage + "' AND '" + decreasepaytodatePage + "' ";
                     queryagent += " `fare`.`dec_pay_date` BETWEEN  '" + decreasepayfromdatePage + "' AND '" + decreasepaytodatePage + "' ";
                     
                }
            }
        }
        if((ticketnoPagePage != null) &&(!"".equalsIgnoreCase(ticketnoPagePage))){
            if(checkQuery == 1){
                querydetail += " and `fare`.`ticket_no` = " + ticketnoPagePage + "' ";
                queryair += " and `fare`.`ticket_no` = " + ticketnoPagePage + "' ";
                queryagent += " and `fare`.`ticket_no` = " + ticketnoPagePage + "' ";
            }else{
                checkQuery = 1;
                querydetail += " `fare`.`ticket_no` = " + ticketnoPagePage + "' ";
                queryair += " `fare`.`ticket_no` = " + ticketnoPagePage + "' ";
                queryagent += " `fare`.`ticket_no` = " + ticketnoPagePage + "' ";
            }
        }
         
        if((salebyNamePage != null) &&(!"".equalsIgnoreCase(salebyNamePage))){
            if(checkQuery == 1){
                querydetail += " and fare.owner   = " + salebyNamePage + "' ";
                queryair += " and fare.owner   = " + salebyNamePage + "' ";
                queryagent += " and fare.owner   = " + salebyNamePage + "' ";
            }else{
                checkQuery = 1;
                querydetail += " fare.owner  = " + salebyNamePage + "' ";
                queryair += " fare.owner  = " + salebyNamePage + "' ";
                queryagent += " fare.owner  = " + salebyNamePage + "' ";
            }
        }
         
        if((termPayPage != null) &&(!"".equalsIgnoreCase(termPayPage))){
            if(checkQuery == 1){
                querydetail += " and inv.term_pay  = " + termPayPage + "' ";
                queryair += " and inv.term_pay  = " + termPayPage + "' ";
                queryagent += " and inv.term_pay  = " + termPayPage + "' ";
            }else{
                checkQuery = 1;
                querydetail += " inv.term_pay  = " + termPayPage + "' ";
                queryair += " inv.term_pay  = " + termPayPage + "' ";
                queryagent += " inv.term_pay  = " + termPayPage + "' ";
            }
        }
        
        if((typeRoutingPage != null) &&(!"".equalsIgnoreCase(typeRoutingPage))){
            if(checkQuery == 1){
                querydetail += " and `fare`.`ticket_type` = " + typeRoutingPage + "' ";
                queryair += " and `fare`.`ticket_type` = " + typeRoutingPage + "' ";
                queryagent += " and `fare`.`ticket_type` = " + typeRoutingPage + "' ";
            }else{
                checkQuery = 1;
                querydetail += " `fare`.`ticket_type`  = " + typeRoutingPage + "' ";
                queryair += " `fare`.`ticket_type`  = " + typeRoutingPage + "' ";
                queryagent += " `fare`.`ticket_type`  = " + typeRoutingPage + "' ";
            }
        }
        
        if((routingDetailPage != null) &&(!"".equalsIgnoreCase(routingDetailPage))){
            if(checkQuery == 1){
                querydetail += " and `fare`.`routing_detail` = " + routingDetailPage + "' ";
                queryair += " and `fare`.`routing_detail` = " + routingDetailPage + "' ";
                queryagent += " and `fare`.`routing_detail` = " + routingDetailPage + "' ";
            }else{
                checkQuery = 1;
                querydetail += " `fare`.`routing_detail` = " + routingDetailPage + "' ";
                queryair += " `fare`.`routing_detail` = " + routingDetailPage + "' ";
                queryagent += " `fare`.`routing_detail` = " + routingDetailPage + "' ";
            }
        }
        
        if((airlineCodePage != null) &&(!"".equalsIgnoreCase(airlineCodePage))){
            if(checkQuery == 1){
                querydetail += " and substr(`fare`.`ticket_no`, 1, 3)  = " + airlineCodePage + "' ";
                queryair += " and substr(`fare`.`ticket_no`, 1, 3)  = " + airlineCodePage + "' ";
                queryagent += " and substr(`fare`.`ticket_no`, 1, 3)  = " + airlineCodePage + "' ";
            }else{
                checkQuery = 1;
                querydetail += " substr(`fare`.`ticket_no`, 1, 3) = " + airlineCodePage + "' ";
                queryair += " substr(`fare`.`ticket_no`, 1, 3) = " + airlineCodePage + "' ";
                queryagent += " substr(`fare`.`ticket_no`, 1, 3) = " + airlineCodePage + "' ";
            }
        }
        
        if((agentCodePage != null) &&(!"".equalsIgnoreCase(agentCodePage))){
            if(checkQuery == 1){
                querydetail += " and `inv`.`inv_to`  = " + agentCodePage + "' ";
                queryair += " and `inv`.`inv_to`  = " + agentCodePage + "' ";
                queryagent += " and `inv`.`inv_to`  = " + agentCodePage + "' ";
            }else{
                checkQuery = 1;
                querydetail += " `inv`.`inv_to`  = " + agentCodePage + "' ";
                queryair += " `inv`.`inv_to`  = " + agentCodePage + "' ";
                queryagent += " `inv`.`inv_to`  = " + agentCodePage + "' ";
            }
        }
        if((departmentPage != null) &&(!"".equalsIgnoreCase(departmentPage))){
            if(checkQuery == 1){
                querydetail += " and fare.department = " + departmentPage + "' ";
                queryair += " and fare.department = " + departmentPage + "' ";
                queryagent += " and fare.department = " + departmentPage + "' ";
            }else{
                checkQuery = 1;
                querydetail += " fare.department  = " + departmentPage + "' ";
                queryair += " fare.department  = " + departmentPage + "' ";
                queryagent += " fare.department  = " + departmentPage + "' ";
            }
        }
        
        queryair += "GROUP BY `fare`.`ticket_type`, `fare`.`ticket_rounting`, substr(`fare`.`ticket_no`, 1, 3)";
        queryagent += " group by `inv`.`inv_to`";
        
        System.out.println("querydetail : "+querydetail);
        System.out.println("queryair : "+queryair);
        System.out.println("queryagent : "+queryagent);
        
        List<Object[]> ticketSummaryCommissionList = session.createSQLQuery(querydetail)
                .addScalar("invno", Hibernate.STRING)					
                .addScalar("invdate", Hibernate.STRING)					
                .addScalar("department", Hibernate.STRING)				
                .addScalar("owner", Hibernate.STRING)					
                .addScalar("termpay", Hibernate.STRING)					
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
                .addScalar("agentcommpay", Hibernate.STRING)					
                .addScalar("pay", Hibernate.STRING)					
                .addScalar("comm", Hibernate.STRING)
                .addScalar("agentcommrec", Hibernate.STRING)
                .addScalar("amountinbound", Hibernate.STRING)
                .addScalar("termpayname", Hibernate.STRING)
                .addScalar("agentname", Hibernate.STRING)
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
//                if(agentcomfromdatePage != null && !"".equals(agentcomfromdatePage)){
//                   String date = ""+ agentcomfromdatePage + " To " + agentcomtodatePage;
//                   ticket.setAgentcomfromdatePage(date);
//                }else{
//                    ticket.setAgentcomfromdatePage("");
//                }
//                if(ticketcomfromdatePage != null && !"".equals(ticketcomfromdatePage)){
//                   String date = ""+ ticketcomfromdatePage + " To " + ticketcomtodatePage;
//                   ticket.setTicketcomfromdatePage(date);
//                }else{
//                    ticket.setTicketcomfromdatePage("");
//                }
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
                ticket.setTermpay((util.ConvertString(B[28])) == "" ? "" : util.ConvertString(B[28]));
                ticket.setAgent((util.ConvertString(B[29])) == "" ? "" : util.ConvertString(B[29]));
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
                ticket.setAgentcommpay((util.ConvertString(B[23])) == "" ? "0.00" : util.ConvertString(B[23]));
                ticket.setPay((util.ConvertString(B[24])) == "" ? "0.00" : util.ConvertString(B[24]));
                ticket.setComm((util.ConvertString(B[25])) == "" ? "0.00" : util.ConvertString(B[25]));
                ticket.setAgentcommrec((util.ConvertString(B[26])) == "" ? "0.00" : util.ConvertString(B[26]));
                ticket.setAmountinbound((util.ConvertString(B[27])) == "" ? "0.00" : util.ConvertString(B[27]));
                
                listDetail.add(ticket);
            }    
        }
        
        // List Air ********************************************************************************************************
        List<Object[]> ticketSummaryCommissionList2 = session.createSQLQuery(queryair)
                .addScalar("typepayment", Hibernate.STRING)
                .addScalar("typerounting", Hibernate.STRING)
                .addScalar("pax", Hibernate.STRING)
                .addScalar("air", Hibernate.STRING)
                .addScalar("refno", Hibernate.STRING)
                .addScalar("issuedate", Hibernate.STRING)
                .addScalar("profit", Hibernate.STRING)
                .addScalar("amountwendy", Hibernate.STRING)
                .addScalar("ticcomm", Hibernate.STRING)
                .addScalar("amountoutbound", Hibernate.STRING)
                .addScalar("little", Hibernate.STRING)
                .addScalar("sale", Hibernate.STRING)
                .addScalar("agentcommpay", Hibernate.STRING)
                .addScalar("cost", Hibernate.STRING)
                .addScalar("agentcommrec", Hibernate.STRING)
                .addScalar("over", Hibernate.STRING)
                .addScalar("pay", Hibernate.STRING)
                .addScalar("add", Hibernate.STRING)
                .addScalar("comm", Hibernate.STRING)
                .addScalar("dres", Hibernate.STRING)
                .list();
        
        if(ticketSummaryCommissionList2 != null && ticketSummaryCommissionList2.size() != 0){
            for (Object[] B : ticketSummaryCommissionList2) {
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
//                if(agentcomfromdatePage != null && !"".equals(agentcomfromdatePage)){
//                   String date = ""+ agentcomfromdatePage + " To " + agentcomtodatePage;
//                   ticket.setAgentcomfromdatePage(date);
//                }else{
//                    ticket.setAgentcomfromdatePage("");
//                }
//                if(ticketcomfromdatePage != null && !"".equals(ticketcomfromdatePage)){
//                   String date = ""+ ticketcomfromdatePage + " To " + ticketcomtodatePage;
//                   ticket.setTicketcomfromdatePage(date);
//                }else{
//                    ticket.setTicketcomfromdatePage("");
//                }
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
                ticket.setTypepayment(util.ConvertString(B[0]) == "" ? "" : util.ConvertString(B[0]));
                ticket.setTyperounting(util.ConvertString(B[1]) == "" ? "" : util.ConvertString(B[1]));
                ticket.setPax((util.ConvertString(B[2])) == "" ? "" : util.ConvertString(B[2]));
                ticket.setAir((util.ConvertString(B[3])) == "" ? "" : util.ConvertString(B[3]));
                ticket.setRefno((util.ConvertString(B[4])) == "" ? "" : util.ConvertString(B[4]));
                ticket.setIssuedate((util.ConvertString(B[5])) == "" ? "" : util.ConvertString(B[5]));
                ticket.setProfit((util.ConvertString(B[6])) == "" ? "0.00" : util.ConvertString(B[6]));
                ticket.setAmountwendy((util.ConvertString(B[7])) == "" ? "0.00" : util.ConvertString(B[7]));
                ticket.setTiccomm((util.ConvertString(B[8])) == "" ? "0.00" : util.ConvertString(B[8]));
                ticket.setAmountoutbound((util.ConvertString(B[9])) == "" ? "0.00" : util.ConvertString(B[9]));
                ticket.setLittle((util.ConvertString(B[10])) == "" ? "0.00" : util.ConvertString(B[10]));
                ticket.setSale((util.ConvertString(B[11])) == "" ? "0.00" : util.ConvertString(B[11]));
                ticket.setAgentcommpay((util.ConvertString(B[12])) == "" ? "0.00" : util.ConvertString(B[12]));
                ticket.setCost((util.ConvertString(B[13])) == "" ? "0.00" : util.ConvertString(B[13]));
                ticket.setAgentcommrec((util.ConvertString(B[14])) == "" ? "0.00" : util.ConvertString(B[14]));
                ticket.setOver((util.ConvertString(B[15])) == "" ? "0.00" : util.ConvertString(B[15]));
                ticket.setPay((util.ConvertString(B[16])) == "" ? "0.00" : util.ConvertString(B[16]));
                ticket.setAdd((util.ConvertString(B[17])) == "" ? "0.00" : util.ConvertString(B[17]));
                ticket.setComm((util.ConvertString(B[18])) == "" ? "0.00" : util.ConvertString(B[18]));
                ticket.setDres((util.ConvertString(B[19])) == "" ? "0.00" : util.ConvertString(B[19]));
                listAir.add(ticket);
            }    
        }
        
        // List Agent*****************************************************************************************************
        List<Object[]> ticketSummaryCommissionList3 = session.createSQLQuery(queryagent)
                .addScalar("agentname", Hibernate.STRING)		
                .addScalar("pax", Hibernate.STRING)		
                .addScalar("refno", Hibernate.STRING)		
                .addScalar("issuedate", Hibernate.STRING)		
                .addScalar("amountwendy", Hibernate.STRING)		
                .addScalar("amountoutbound", Hibernate.STRING)		
                .addScalar("amountinbound", Hibernate.STRING)		
                .addScalar("sale", Hibernate.STRING)		
                .addScalar("cost", Hibernate.STRING)		
                .addScalar("over", Hibernate.STRING)		
                .addScalar("add", Hibernate.STRING)		
                .addScalar("dres", Hibernate.STRING)		
                .addScalar("profit", Hibernate.STRING)		
                .addScalar("ticcomm", Hibernate.STRING)		
                .addScalar("little", Hibernate.STRING)		
                .addScalar("agentcommpay", Hibernate.STRING)		
                .addScalar("agentcommrec", Hibernate.STRING)		
                .addScalar("pay", Hibernate.STRING)		
                .addScalar("comm", Hibernate.STRING)	
                .list();
        
        if(ticketSummaryCommissionList3 != null && ticketSummaryCommissionList3.size() != 0){
            for (Object[] B : ticketSummaryCommissionList3) {
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
//                if(agentcomfromdatePage != null && !"".equals(agentcomfromdatePage)){
//                   String date = ""+ agentcomfromdatePage + " To " + agentcomtodatePage;
//                   ticket.setAgentcomfromdatePage(date);
//                }else{
//                    ticket.setAgentcomfromdatePage("");
//                }
//                if(ticketcomfromdatePage != null && !"".equals(ticketcomfromdatePage)){
//                   String date = ""+ ticketcomfromdatePage + " To " + ticketcomtodatePage;
//                   ticket.setTicketcomfromdatePage(date);
//                }else{
//                    ticket.setTicketcomfromdatePage("");
//                }
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
                ticket.setAgentname(util.ConvertString(B[0]) == "" ? "" : util.ConvertString(B[0]));
                ticket.setPax(util.ConvertString(B[1]) == "" ? "" : util.ConvertString(B[1]));
                ticket.setRefno((util.ConvertString(B[2])) == "" ? "" : util.ConvertString(B[2]));
                ticket.setIssuedate((util.ConvertString(B[3])) == "" ? "" : util.ConvertString(B[3]));
                ticket.setAmountwendy((util.ConvertString(B[4])) == "" ? "0.00" : util.ConvertString(B[4]));
                ticket.setAmountoutbound((util.ConvertString(B[5])) == "" ? "0.00" : util.ConvertString(B[5]));
                ticket.setAmountinbound((util.ConvertString(B[6])) == "" ? "0.00" : util.ConvertString(B[6]));
                ticket.setSale((util.ConvertString(B[7])) == "" ? "0.00" : util.ConvertString(B[7]));
                ticket.setCost((util.ConvertString(B[8])) == "" ? "0.00" : util.ConvertString(B[8]));
                ticket.setOver((util.ConvertString(B[9])) == "" ? "0.00" : util.ConvertString(B[9]));
                ticket.setAdd((util.ConvertString(B[10])) == "" ? "0.00" :util.ConvertString(B[10]));
                ticket.setDres((util.ConvertString(B[11])) == "" ? "0.00" : util.ConvertString(B[11]));
                ticket.setProfit((util.ConvertString(B[12])) == "" ? "0.00" : util.ConvertString(B[12]));
                ticket.setTiccomm((util.ConvertString(B[13])) == "" ? "0.00" : util.ConvertString(B[13]));
                ticket.setLittle((util.ConvertString(B[14])) == "" ? "0.00" : util.ConvertString(B[14]));
                ticket.setAgentcommpay((util.ConvertString(B[15])) == "" ? "0.00" : util.ConvertString(B[15]));
                ticket.setAgentcommrec((util.ConvertString(B[16])) == "" ? "0.00" : util.ConvertString(B[16]));
                ticket.setPay((util.ConvertString(B[17])) == "" ? "0.00" : util.ConvertString(B[17]));
                ticket.setComm((util.ConvertString(B[18])) == "" ? "0.00" : util.ConvertString(B[18]));
                listAgent.add(ticket);
            }    
        }
        summaryCommission.setTicketCommissionDetailSummary(listDetail);
        summaryCommission.setTicketCommissionAirSummary(listAir);
        summaryCommission.setTicketCommissionAgentSummary(listAgent);
        listSummaryCommission.add(summaryCommission);
    return listSummaryCommission;
    }   
}
