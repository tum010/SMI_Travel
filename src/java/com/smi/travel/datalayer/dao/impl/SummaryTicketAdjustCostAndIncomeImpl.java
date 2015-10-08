/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.SummaryTicketAdjustCostAndIncomeDao;
import com.smi.travel.datalayer.view.entity.ListSummaryTicketAdjustCostAndIncome;
import com.smi.travel.datalayer.view.entity.ListTicketCommissionReceive;
import com.smi.travel.datalayer.view.entity.SummaryTicketAdjustCostAndIncome;
import com.smi.travel.datalayer.view.entity.TicketCommissionReceive;
import com.smi.travel.util.UtilityFunction;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Kanokporn
 */
public class SummaryTicketAdjustCostAndIncomeImpl implements SummaryTicketAdjustCostAndIncomeDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public List<ListSummaryTicketAdjustCostAndIncome> getSummaryTicketAdjustCostAndIncome(String reportType, String invoiceFromDate, String invoiceToDate, String issueFrom, String issueTo, String paymentType, String departmentt, String salebyUser, String termPayt,String printby) {
         List<ListSummaryTicketAdjustCostAndIncome> listTotal = new LinkedList<ListSummaryTicketAdjustCostAndIncome>();
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        List dataSum = new ArrayList();
        String query = "";
        String querySum = "";
        int AndQuery = 0;
        int AndQuerySum = 0;
        System.out.println("Term Pay : " + termPayt);
        if( invoiceFromDate == null  &&  invoiceToDate == null  && issueFrom == null && issueTo == null && paymentType == null && departmentt == null && salebyUser == null && termPayt == null){
            query = "SELECT ( CASE WHEN (`fare`.`ticket_type` = 'B') THEN 'BSP' WHEN (`fare`.`ticket_type` = 'A') THEN 'AGENT' WHEN (`fare`.`ticket_type` = 'D') THEN 'DOMESTIC' WHEN (`fare`.`ticket_type` = 'T') THEN 'TG' ELSE `fare`.`ticket_type` END ) AS `typepayment`, `fare`.`ticket_rounting` AS `typerounting`, count(`fare`.`ticket_no`) AS `pax`, substr(`fare`.`ticket_no`, 1, 3) AS `air`, ( CASE WHEN (`inv`.`inv_type` = 'T') THEN substr(`inv`.`inv_no`, 1, 5) ELSE substr(`inv`.`inv_no`, 1, 6) END ) AS `invno`, ( SELECT sum(`invd`.`cur_amount`) FROM `invoice_detail` `invd` WHERE ( `invd`.`invoice_id` = `inv`.`id` )) AS `costinv`, ( CASE WHEN ( `fare`.`department` = 'wendy' ) THEN sum(`fare`.`inv_amount`) ELSE NULL END ) AS `invoicewendy`, ( CASE WHEN ( `fare`.`department` = 'inbound' ) THEN sum(`fare`.`inv_amount`) ELSE NULL END ) AS `invoiceinbound`, ( CASE WHEN ( `fare`.`department` = 'outbound' ) THEN sum(`fare`.`inv_amount`) ELSE NULL END ) AS `invoiceoutbound`, sum( ifnull( `fare`.`agent_commission`, 0 )) AS `discount`, ( SELECT sum( ifnull( `fare`.`agent_commission`, 0 )) FROM `ticket_fare_airline` `farec` WHERE ((`farec`.`id` = `farec`.`id`) AND ( `farec`.`ticket_rounting` = 'C' ))) AS `cancel`, 0 AS `wait_pay`, sum( ifnull(`fare`.`diff_vat`, 0)) AS `rcagcom`, sum( ifnull(`fare`.`over_commission`, 0)) AS `over`, sum( ifnull( `fare`.`litter_commission`, 0 )) AS `litter`, sum((((( ifnull(`fare`.`inv_amount`, 0) + ifnull(`fare`.`over_commission`, 0)) - ifnull( `fare`.`litter_commission`, 0 )) + ifnull( `fare`.`agent_commission`, 0 )) - ifnull(`fare`.`diff_vat`, 0))) AS `total_balance` FROM (((( `ticket_fare_airline` `fare` LEFT JOIN `ticket_fare_invoice` `finv` ON (( `finv`.`ticket_fare_id` = `fare`.`id` ))) LEFT JOIN `invoice` `inv` ON (( `inv`.`id` = `finv`.`invoice_id` ))) LEFT JOIN `m_accterm` `term` ON (( `term`.`id` = `inv`.`term_pay` ))) LEFT JOIN `staff` `st` ON ((`st`.`name` = `fare`.`owner`)))" ; 
            querySum = "select (case when (`fare`.`ticket_type` = 'B') then 'BSP' when (`fare`.`ticket_type` = 'A') then 'AGENT' when (`fare`.`ticket_type` = 'D') then 'DOMESTIC' when (`fare`.`ticket_type` = 'T') then 'TG' else `fare`.`ticket_type` end) AS `typepayment`,`fare`.`ticket_rounting` AS `typerounting`,count(`fare`.`ticket_no`) AS `pax`,(case when (`inv`.`inv_type` = 'T') then substr(`inv`.`inv_no`,1,5) else substr(`inv`.`inv_no`,1,6) end) AS `invno`,(select sum(`invd`.`cur_amount`) from `invoice_detail` `invd` where (`invd`.`invoice_id` = `inv`.`id`)) AS `costinv`,(case when (`fare`.`department` = 'wendy') then sum(`fare`.`inv_amount`) else NULL end) AS `invoicewendy`,(case when (`fare`.`department` = 'inbound') then sum(`fare`.`inv_amount`) else NULL end) AS `invoiceinbound`,(case when (`fare`.`department` = 'outbound') then sum(`fare`.`inv_amount`) else NULL end) AS `invoiceoutbound`,sum(ifnull(`fare`.`agent_commission`,0)) AS `discount`,(select sum(ifnull(`fare`.`agent_commission`,0)) from `ticket_fare_airline` `farec` where ((`farec`.`id` = `farec`.`id`) and (`farec`.`ticket_rounting` = 'C'))) AS `cancel`,0 AS `wait_pay`,sum(ifnull(`fare`.`diff_vat`,0)) AS `rcagcom`,sum(ifnull(`fare`.`over_commission`,0)) AS `over`,sum(ifnull(`fare`.`litter_commission`,0)) AS `litter`,sum(((((ifnull(`fare`.`inv_amount`,0) + ifnull(`fare`.`over_commission`,0)) - ifnull(`fare`.`litter_commission`,0)) + ifnull(`fare`.`agent_commission`,0)) - ifnull(`fare`.`diff_vat`,0))) AS `total_balance` from ((((`ticket_fare_airline` `fare` left join `ticket_fare_invoice` `finv` on((`finv`.`ticket_fare_id` = `fare`.`id`))) left join `invoice` `inv` on((`inv`.`id` = `finv`.`invoice_id`))) left join `m_accterm` `term` on((`term`.`id` = `inv`.`term_pay`))) left join `staff` `st` on((`st`.`name` = `fare`.`owner`)))  ";
        }else{
            query = "SELECT ( CASE WHEN (`fare`.`ticket_type` = 'B') THEN 'BSP' WHEN (`fare`.`ticket_type` = 'A') THEN 'AGENT' WHEN (`fare`.`ticket_type` = 'D') THEN 'DOMESTIC' WHEN (`fare`.`ticket_type` = 'T') THEN 'TG' ELSE `fare`.`ticket_type` END ) AS `typepayment`, `fare`.`ticket_rounting` AS `typerounting`, count(`fare`.`ticket_no`) AS `pax`, substr(`fare`.`ticket_no`, 1, 3) AS `air`, ( CASE WHEN (`inv`.`inv_type` = 'T') THEN substr(`inv`.`inv_no`, 1, 5) ELSE substr(`inv`.`inv_no`, 1, 6) END ) AS `invno`, ( SELECT sum(`invd`.`cur_amount`) FROM `invoice_detail` `invd` WHERE ( `invd`.`invoice_id` = `inv`.`id` )) AS `costinv`, ( CASE WHEN ( `fare`.`department` = 'wendy' ) THEN sum(`fare`.`inv_amount`) ELSE NULL END ) AS `invoicewendy`, ( CASE WHEN ( `fare`.`department` = 'inbound' ) THEN sum(`fare`.`inv_amount`) ELSE NULL END ) AS `invoiceinbound`, ( CASE WHEN ( `fare`.`department` = 'outbound' ) THEN sum(`fare`.`inv_amount`) ELSE NULL END ) AS `invoiceoutbound`, sum( ifnull( `fare`.`agent_commission`, 0 )) AS `discount`, ( SELECT sum( ifnull( `fare`.`agent_commission`, 0 )) FROM `ticket_fare_airline` `farec` WHERE ((`farec`.`id` = `farec`.`id`) AND ( `farec`.`ticket_rounting` = 'C' ))) AS `cancel`, 0 AS `wait_pay`, sum( ifnull(`fare`.`diff_vat`, 0)) AS `rcagcom`, sum( ifnull(`fare`.`over_commission`, 0)) AS `over`, sum( ifnull( `fare`.`litter_commission`, 0 )) AS `litter`, sum((((( ifnull(`fare`.`inv_amount`, 0) + ifnull(`fare`.`over_commission`, 0)) - ifnull( `fare`.`litter_commission`, 0 )) + ifnull( `fare`.`agent_commission`, 0 )) - ifnull(`fare`.`diff_vat`, 0))) AS `total_balance` FROM (((( `ticket_fare_airline` `fare` LEFT JOIN `ticket_fare_invoice` `finv` ON (( `finv`.`ticket_fare_id` = `fare`.`id` ))) LEFT JOIN `invoice` `inv` ON (( `inv`.`id` = `finv`.`invoice_id` ))) LEFT JOIN `m_accterm` `term` ON (( `term`.`id` = `inv`.`term_pay` ))) LEFT JOIN `staff` `st` ON ((`st`.`name` = `fare`.`owner`)))  where " ;
            querySum = "select (case when (`fare`.`ticket_type` = 'B') then 'BSP' when (`fare`.`ticket_type` = 'A') then 'AGENT' when (`fare`.`ticket_type` = 'D') then 'DOMESTIC' when (`fare`.`ticket_type` = 'T') then 'TG' else `fare`.`ticket_type` end) AS `typepayment`,`fare`.`ticket_rounting` AS `typerounting`,count(`fare`.`ticket_no`) AS `pax`,(case when (`inv`.`inv_type` = 'T') then substr(`inv`.`inv_no`,1,5) else substr(`inv`.`inv_no`,1,6) end) AS `invno`,(select sum(`invd`.`cur_amount`) from `invoice_detail` `invd` where (`invd`.`invoice_id` = `inv`.`id`)) AS `costinv`,(case when (`fare`.`department` = 'wendy') then sum(`fare`.`inv_amount`) else NULL end) AS `invoicewendy`,(case when (`fare`.`department` = 'inbound') then sum(`fare`.`inv_amount`) else NULL end) AS `invoiceinbound`,(case when (`fare`.`department` = 'outbound') then sum(`fare`.`inv_amount`) else NULL end) AS `invoiceoutbound`,sum(ifnull(`fare`.`agent_commission`,0)) AS `discount`,(select sum(ifnull(`fare`.`agent_commission`,0)) from `ticket_fare_airline` `farec` where ((`farec`.`id` = `farec`.`id`) and (`farec`.`ticket_rounting` = 'C'))) AS `cancel`,0 AS `wait_pay`,sum(ifnull(`fare`.`diff_vat`,0)) AS `rcagcom`,sum(ifnull(`fare`.`over_commission`,0)) AS `over`,sum(ifnull(`fare`.`litter_commission`,0)) AS `litter`,sum(((((ifnull(`fare`.`inv_amount`,0) + ifnull(`fare`.`over_commission`,0)) - ifnull(`fare`.`litter_commission`,0)) + ifnull(`fare`.`agent_commission`,0)) - ifnull(`fare`.`diff_vat`,0))) AS `total_balance` from ((((`ticket_fare_airline` `fare` left join `ticket_fare_invoice` `finv` on((`finv`.`ticket_fare_id` = `fare`.`id`))) left join `invoice` `inv` on((`inv`.`id` = `finv`.`invoice_id`))) left join `m_accterm` `term` on((`term`.`id` = `inv`.`term_pay`))) left join `staff` `st` on((`st`.`name` = `fare`.`owner`)))   where ";
        }
        
        if (paymentType != null && (!"".equalsIgnoreCase(paymentType)) ) {
           if(AndQuery == 1){
                query += " and fare.ticket_type = '" + paymentType + "'";
           }else{
               AndQuery = 1;
               query += " fare.ticket_type = '" + paymentType + "'";
           }
        }
        
        if(departmentt != null && (!"".equalsIgnoreCase(departmentt))){
            if(AndQuery == 1){
                query += " and fare.department = '" + departmentt + "'";
           }else{
               AndQuery = 1;
               query += " fare.department = '" + departmentt + "'";
           }
        }
        
        if(salebyUser != null && (!"".equalsIgnoreCase(salebyUser))){
            if(AndQuery == 1){
                query += " and fare.`owner` = '" + salebyUser + "'";
           }else{
               AndQuery = 1;
               query += " fare.`owner` = '" + salebyUser + "'";
           }
        }
        
        if(termPayt != null && (!"".equalsIgnoreCase(termPayt))){
            if(AndQuery == 1){
                query += " and inv.term_pay = " + termPayt + "";
           }else{
               AndQuery = 1;
               query += " inv.term_pay = " + termPayt + "";
           }
        }
        
        if ((invoiceFromDate != null )&&(!"".equalsIgnoreCase(invoiceFromDate))) {
            if ((invoiceToDate != null )&&(!"".equalsIgnoreCase(invoiceToDate))) {
                if(AndQuery == 1){
                     query += " and inv.inv_date  BETWEEN  '" + invoiceFromDate + "' AND '" + invoiceToDate + "' ";
                }else{
                    AndQuery = 1;
                     query += " inv.inv_date  BETWEEN  '" + invoiceFromDate + "' AND '" + invoiceToDate + "' ";
                } 
            }
        }
        
        if ((issueFrom != null )&&(!"".equalsIgnoreCase(issueFrom))) {
            if ((issueTo != null )&&(!"".equalsIgnoreCase(issueTo))) {
                if(AndQuery == 1){
                     query += " and fare.issue_date  BETWEEN  '" + issueFrom + "' AND '" + issueTo + "' ";
                }else{
                    AndQuery = 1;
                     query += " fare.issue_date  BETWEEN  '" + issueFrom + "' AND '" + issueTo + "' ";
                } 
            }
        }
        
        // Query Summary Income
        if (paymentType != null && (!"".equalsIgnoreCase(paymentType)) ) {
           if(AndQuerySum == 1){
                querySum += " and fare.ticket_type = '" + paymentType + "'";
           }else{
               AndQuerySum = 1;
               querySum += " fare.ticket_type = '" + paymentType + "'";
           }
        }
        
        if(departmentt != null && (!"".equalsIgnoreCase(departmentt))){
            if(AndQuerySum == 1){
                querySum += " and fare.department = '" + departmentt + "'";
           }else{
               AndQuerySum = 1;
               querySum += " fare.department = '" + departmentt + "'";
           }
        }
        
        if(salebyUser != null && (!"".equalsIgnoreCase(salebyUser))){
            if(AndQuerySum == 1){
                querySum += " and fare.`owner` = '" + salebyUser + "'";
           }else{
               AndQuerySum = 1;
               querySum += " fare.`owner` = '" + salebyUser + "'";
           }
        }
        
        if(termPayt != null && (!"".equalsIgnoreCase(termPayt))){
            if(AndQuerySum == 1){
                querySum += " and inv.term_pay = " + termPayt + "";
           }else{
               AndQuerySum = 1;
               querySum += " inv.term_pay = " + termPayt + "";
           }
        }
        
        if ((invoiceFromDate != null )&&(!"".equalsIgnoreCase(invoiceFromDate))) {
            if ((invoiceToDate != null )&&(!"".equalsIgnoreCase(invoiceToDate))) {
                if(AndQuerySum == 1){
                     querySum += " and inv.inv_date  BETWEEN  '" + invoiceFromDate + "' AND '" + invoiceToDate + "' ";
                }else{
                    AndQuerySum = 1;
                     querySum += " inv.inv_date  BETWEEN  '" + invoiceFromDate + "' AND '" + invoiceToDate + "' ";
                } 
            }
        }
        
        if ((issueFrom != null )&&(!"".equalsIgnoreCase(issueFrom))) {
            if ((issueTo != null )&&(!"".equalsIgnoreCase(issueTo))) {
                if(AndQuerySum == 1){
                     querySum += " and fare.issue_date  BETWEEN  '" + issueFrom + "' AND '" + issueTo + "' ";
                }else{
                    AndQuerySum = 1;
                     querySum += " fare.issue_date  BETWEEN  '" + issueFrom + "' AND '" + issueTo + "' ";
                } 
            }
        }
        querySum += " group by `fare`.`ticket_type`,`fare`.`ticket_rounting`,(case when (`inv`.`inv_type` = 'T') then substr(`inv`.`inv_no`,1,5) else substr(`inv`.`inv_no`,1,6) end)";
        query += " GROUP BY `fare`.`ticket_type`, `fare`.`ticket_rounting`, substr(`fare`.`ticket_no`, 1, 3), ( CASE WHEN (`inv`.`inv_type` = 'T') THEN substr(`inv`.`inv_no`, 1, 5) ELSE substr(`inv`.`inv_no`, 1, 6) END )";
        System.out.println("query sum : " + querySum);
        System.out.println("query : " + query);
        List<Object[]> SummaryTicketAdjustCostAndIncome = session.createSQLQuery(query )
                .addScalar("typepayment", Hibernate.STRING)
                .addScalar("typerounting", Hibernate.STRING)
                .addScalar("pax", Hibernate.STRING)
                .addScalar("air", Hibernate.STRING)
                .addScalar("invno", Hibernate.STRING)
                .addScalar("costinv", Hibernate.STRING)
                .addScalar("invoicewendy", Hibernate.STRING)
                .addScalar("invoiceinbound", Hibernate.STRING)
                .addScalar("invoiceoutbound", Hibernate.STRING)
                .addScalar("discount", Hibernate.STRING)
                .addScalar("cancel", Hibernate.STRING)
                .addScalar("wait_pay", Hibernate.STRING)
                .addScalar("rcagcom", Hibernate.STRING)	
                .addScalar("over", Hibernate.STRING)
                .addScalar("litter", Hibernate.STRING)
                .addScalar("total_balance", Hibernate.STRING)
                .list();
        for (Object[] B : SummaryTicketAdjustCostAndIncome) {
            SummaryTicketAdjustCostAndIncome ar = new SummaryTicketAdjustCostAndIncome();
            //header 
            if(invoiceFromDate != null && !"".equals(invoiceFromDate)){
                String  invoice = ""+ invoiceFromDate + " To " + invoiceToDate;
                ar.setInvoicedatePage(invoice);
            }else{
                ar.setInvoicedatePage("");
            }
            if(issueFrom != null && !"".equals(issueFrom)){
                String issue = ""+ issueFrom + " To " + issueTo;
                ar.setIssuedatePage(issue);
            }else{
                ar.setIssuedatePage("");
            }
            if(departmentt != null && !"".equals(departmentt)){
                ar.setDepartmentPage(departmentt);
            }else{
                ar.setDepartmentPage("");
            }
            if(salebyUser != null && !"".equals(salebyUser)){
               ar.setSalsestaffPage(salebyUser);
            }else{
                ar.setSalsestaffPage("");
            }
            
            ar.setPrintbyPage(printby);
            
            ar.setTypepayment(util.ConvertString(B[0]) == null ? "" : util.ConvertString(B[0]));
            ar.setTyperounting(util.ConvertString(B[1])== null ? "" : util.ConvertString(B[1]));
            ar.setPax(util.ConvertString(B[2])== null ? "" : util.ConvertString(B[2]));
            ar.setAir(util.ConvertString(B[3])== null ? "" : util.ConvertString(B[3]));
            ar.setInvno(util.ConvertString(B[4])== null ? "" : util.ConvertString(B[4]));
            ar.setCostinv(util.ConvertString(B[5])== null ? "" : util.ConvertString(B[5]));
            ar.setInvoicewendy(util.ConvertString(B[6])== null ? "" : util.ConvertString(B[6]));
            ar.setInvoiceinbound(util.ConvertString(B[7])== null ? "" : util.ConvertString(B[7]));
            ar.setInvoiceoutbound(util.ConvertString(B[8])== null ? "" : util.ConvertString(B[8]));
            ar.setDiscount(util.ConvertString(B[9])== null ? "" : util.ConvertString(B[9]));
            ar.setCancel(util.ConvertString(B[10])== null ? "" : util.ConvertString(B[10]));
            ar.setWait_pay(util.ConvertString(B[11])== null ? "" : util.ConvertString(B[11]));
            ar.setRcagcom(util.ConvertString(B[12])== null ? "" : util.ConvertString(B[12]));
            ar.setOver(util.ConvertString(B[13])== null ? "" : util.ConvertString(B[13]));
            ar.setLitter(util.ConvertString(B[14])== null ? "" : util.ConvertString(B[14]));
            ar.setTotal_balance(util.ConvertString(B[15])== null ? "" : util.ConvertString(B[15]));
            data.add(ar);
        }
        
        List<Object[]> SummaryTicketAdjustCostAndIncomeSum = session.createSQLQuery(querySum)
                .addScalar("typepayment", Hibernate.STRING)
                .addScalar("typerounting", Hibernate.STRING)
                .addScalar("pax", Hibernate.STRING)
                .addScalar("invno", Hibernate.STRING)
                .addScalar("costinv", Hibernate.STRING)
                .addScalar("invoicewendy", Hibernate.STRING)
                .addScalar("invoiceinbound", Hibernate.STRING)
                .addScalar("invoiceoutbound", Hibernate.STRING)
                .addScalar("discount", Hibernate.STRING)
                .addScalar("cancel", Hibernate.STRING)
                .addScalar("wait_pay", Hibernate.STRING)
                .addScalar("rcagcom", Hibernate.STRING)	
                .addScalar("over", Hibernate.STRING)
                .addScalar("litter", Hibernate.STRING)
                .addScalar("total_balance", Hibernate.STRING)
                .list();
        for (Object[] B : SummaryTicketAdjustCostAndIncomeSum) {
            SummaryTicketAdjustCostAndIncome ar = new SummaryTicketAdjustCostAndIncome();
            ar.setTypepayment(util.ConvertString(B[0]) == null ? "" : util.ConvertString(B[0]));
            ar.setTyperounting(util.ConvertString(B[1])== null ? "" : util.ConvertString(B[1]));
            ar.setPax(util.ConvertString(B[2])== null ? "" : util.ConvertString(B[2]));
            ar.setInvno(util.ConvertString(B[3])== null ? "" : util.ConvertString(B[3]));
            ar.setCostinv(util.ConvertString(B[4])== null ? "" : util.ConvertString(B[4]));
            ar.setInvoicewendy(util.ConvertString(B[5])== null ? "" : util.ConvertString(B[5]));
            ar.setInvoiceinbound(util.ConvertString(B[6])== null ? "" : util.ConvertString(B[6]));
            ar.setInvoiceoutbound(util.ConvertString(B[7])== null ? "" : util.ConvertString(B[7]));
            ar.setDiscount(util.ConvertString(B[8])== null ? "" : util.ConvertString(B[8]));
            ar.setCancel(util.ConvertString(B[9])== null ? "" : util.ConvertString(B[9]));
            ar.setWait_pay(util.ConvertString(B[10])== null ? "" : util.ConvertString(B[10]));
            ar.setRcagcom(util.ConvertString(B[11])== null ? "" : util.ConvertString(B[11]));
            ar.setOver(util.ConvertString(B[12])== null ? "" : util.ConvertString(B[12]));
            ar.setLitter(util.ConvertString(B[13])== null ? "" : util.ConvertString(B[13]));
            ar.setTotal_balance(util.ConvertString(B[14])== null ? "" : util.ConvertString(B[14]));
            dataSum.add(ar);
        }
        
        ListSummaryTicketAdjustCostAndIncome total = new ListSummaryTicketAdjustCostAndIncome();
        total.setSummaryTicketAdjustAndIncome(data);
        total.setSummaryTicketAdjustAndIncomeSum(dataSum);
        listTotal.add(total);
        session.close();
        this.sessionFactory.close();
        return listTotal;
    }

    @Override
    public List<ListSummaryTicketAdjustCostAndIncome> getSummaryTicketCostAndIncome(String reportType, String invoiceFromDate, String invoiceToDate, String issueFrom, String issueTo, String paymentType, String departmentt, String salebyUser, String termPayt,String printby) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        Date thisDate = new Date();
        List data = new ArrayList();
        String query = "";
        int AndQuery = 0;
        
        return data;
    }

    @Override
    public List<ListTicketCommissionReceive> getTicketCommissionReceive(String reportType, String invoiceFromDate, String invoiceToDate, String issueFrom, String issueTo, String paymentType, String departmentt, String salebyUser, String termPayt,String printby) {
           List<ListTicketCommissionReceive> listTotal = new LinkedList<ListTicketCommissionReceive>();
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        List dataSum = new ArrayList();
        String query = "";
        String querySum = "";
        int AndQuery = 0;
        int AndQuerySum = 0;
        System.out.println("Term Pay : " + termPayt);
        if( invoiceFromDate == null  &&  invoiceToDate == null  && issueFrom == null && issueTo == null && paymentType == null && departmentt == null && salebyUser == null && termPayt == null){
            query = "select (case when (`fare`.`ticket_type` = 'B') then 'BSP' when (`fare`.`ticket_type` = 'A') then 'AGENT' when (`fare`.`ticket_type` = 'D') then 'DOMESTIC' when (`fare`.`ticket_type` = 'T') then 'TG' else `fare`.`ticket_type` end) AS `typepayment`,`fare`.`ticket_rounting` AS `typerounting`,count(`fare`.`ticket_no`) AS `pax`,substr(`fare`.`ticket_no`,1,3) AS `air`,sum(ifnull(`fare`.`ticket_commission`,0)) AS `comairline`,sum(ifnull(`fare`.`agent_commission`,0)) AS `payagent`,sum(ifnull(`refd`.`agent_comission`,0)) AS `rcagent`,sum(ifnull(`refd`.`airline_comission`,0)) AS `payrefund`,sum(((((ifnull(`fare`.`ticket_commission`,0) + ifnull(`fare`.`agent_commission`,0)) - ifnull(`fare`.`agent_commission`,0)) + ifnull(`refd`.`agent_comission`,0)) - ifnull(`refd`.`pay_customer`,0))) AS `comreceive` from ((((((`ticket_fare_airline` `fare` left join `ticket_fare_invoice` `finv` on((`finv`.`ticket_fare_id` = `fare`.`id`))) left join `invoice` `inv` on((`inv`.`id` = `finv`.`invoice_id`))) left join `m_accterm` `term` on((`term`.`id` = `inv`.`term_pay`))) left join `staff` `st` on((`st`.`name` = `fare`.`owner`))) left join `airticket_passenger` `air` on((`fare`.`ticket_no` = concat(`air`.`series1`,`air`.`series2`,`air`.`series3`)))) left join `refund_airticket_detail` `refd` on((`refd`.`ticket_passenger_id` = `air`.`id`)))" ; 
            querySum = "select (case when (`fare`.`ticket_type` = 'B') then 'BSP' when (`fare`.`ticket_type` = 'A') then 'AGENT' when (`fare`.`ticket_type` = 'D') then 'DOMESTIC' when (`fare`.`ticket_type` = 'T') then 'TG' else `fare`.`ticket_type` end) AS `typepayment`,`fare`.`ticket_rounting` AS `typerounting`,count(`fare`.`ticket_no`) AS `pax`,substr(`fare`.`ticket_no`,1,3) AS `air`,sum(ifnull(`fare`.`ticket_commission`,0)) AS `comairline`,sum(ifnull(`fare`.`agent_commission`,0)) AS `payagent`,sum(ifnull(`refd`.`agent_comission`,0)) AS `rcagent`,sum(ifnull(`refd`.`airline_comission`,0)) AS `payrefund`,sum(((((ifnull(`fare`.`ticket_commission`,0) + ifnull(`fare`.`agent_commission`,0)) - ifnull(`fare`.`agent_commission`,0)) + ifnull(`refd`.`agent_comission`,0)) - ifnull(`refd`.`pay_customer`,0))) AS `comreceive` from ((((((`ticket_fare_airline` `fare` left join `ticket_fare_invoice` `finv` on((`finv`.`ticket_fare_id` = `fare`.`id`))) left join `invoice` `inv` on((`inv`.`id` = `finv`.`invoice_id`))) left join `m_accterm` `term` on((`term`.`id` = `inv`.`term_pay`))) left join `staff` `st` on((`st`.`name` = `fare`.`owner`))) left join `airticket_passenger` `air` on((`fare`.`ticket_no` = concat(`air`.`series1`,`air`.`series2`,`air`.`series3`)))) left join `refund_airticket_detail` `refd` on((`refd`.`ticket_passenger_id` = `air`.`id`)))";
        }else{
            query = "select (case when (`fare`.`ticket_type` = 'B') then 'BSP' when (`fare`.`ticket_type` = 'A') then 'AGENT' when (`fare`.`ticket_type` = 'D') then 'DOMESTIC' when (`fare`.`ticket_type` = 'T') then 'TG' else `fare`.`ticket_type` end) AS `typepayment`,`fare`.`ticket_rounting` AS `typerounting`,count(`fare`.`ticket_no`) AS `pax`,substr(`fare`.`ticket_no`,1,3) AS `air`,sum(ifnull(`fare`.`ticket_commission`,0)) AS `comairline`,sum(ifnull(`fare`.`agent_commission`,0)) AS `payagent`,sum(ifnull(`refd`.`agent_comission`,0)) AS `rcagent`,sum(ifnull(`refd`.`airline_comission`,0)) AS `payrefund`,sum(((((ifnull(`fare`.`ticket_commission`,0) + ifnull(`fare`.`agent_commission`,0)) - ifnull(`fare`.`agent_commission`,0)) + ifnull(`refd`.`agent_comission`,0)) - ifnull(`refd`.`pay_customer`,0))) AS `comreceive` from ((((((`ticket_fare_airline` `fare` left join `ticket_fare_invoice` `finv` on((`finv`.`ticket_fare_id` = `fare`.`id`))) left join `invoice` `inv` on((`inv`.`id` = `finv`.`invoice_id`))) left join `m_accterm` `term` on((`term`.`id` = `inv`.`term_pay`))) left join `staff` `st` on((`st`.`name` = `fare`.`owner`))) left join `airticket_passenger` `air` on((`fare`.`ticket_no` = concat(`air`.`series1`,`air`.`series2`,`air`.`series3`)))) left join `refund_airticket_detail` `refd` on((`refd`.`ticket_passenger_id` = `air`.`id`)))  where " ;
            querySum = "select (case when (`fare`.`ticket_type` = 'B') then 'BSP' when (`fare`.`ticket_type` = 'A') then 'AGENT' when (`fare`.`ticket_type` = 'D') then 'DOMESTIC' when (`fare`.`ticket_type` = 'T') then 'TG' else `fare`.`ticket_type` end) AS `typepayment`,`fare`.`ticket_rounting` AS `typerounting`,count(`fare`.`ticket_no`) AS `pax`,substr(`fare`.`ticket_no`,1,3) AS `air`,sum(ifnull(`fare`.`ticket_commission`,0)) AS `comairline`,sum(ifnull(`fare`.`agent_commission`,0)) AS `payagent`,sum(ifnull(`refd`.`agent_comission`,0)) AS `rcagent`,sum(ifnull(`refd`.`airline_comission`,0)) AS `payrefund`,sum(((((ifnull(`fare`.`ticket_commission`,0) + ifnull(`fare`.`agent_commission`,0)) - ifnull(`fare`.`agent_commission`,0)) + ifnull(`refd`.`agent_comission`,0)) - ifnull(`refd`.`pay_customer`,0))) AS `comreceive` from ((((((`ticket_fare_airline` `fare` left join `ticket_fare_invoice` `finv` on((`finv`.`ticket_fare_id` = `fare`.`id`))) left join `invoice` `inv` on((`inv`.`id` = `finv`.`invoice_id`))) left join `m_accterm` `term` on((`term`.`id` = `inv`.`term_pay`))) left join `staff` `st` on((`st`.`name` = `fare`.`owner`))) left join `airticket_passenger` `air` on((`fare`.`ticket_no` = concat(`air`.`series1`,`air`.`series2`,`air`.`series3`)))) left join `refund_airticket_detail` `refd` on((`refd`.`ticket_passenger_id` = `air`.`id`)))  where ";
        }
        
        if (paymentType != null && (!"".equalsIgnoreCase(paymentType)) ) {
           if(AndQuery == 1){
                query += " and fare.ticket_type = '" + paymentType + "'";
           }else{
               AndQuery = 1;
               query += " fare.ticket_type = '" + paymentType + "'";
           }
        }
        
        if(departmentt != null && (!"".equalsIgnoreCase(departmentt))){
            if(AndQuery == 1){
                query += " and fare.department = '" + departmentt + "'";
           }else{
               AndQuery = 1;
               query += " fare.department = '" + departmentt + "'";
           }
        }
        
        if(salebyUser != null && (!"".equalsIgnoreCase(salebyUser))){
            if(AndQuery == 1){
                query += " and fare.`owner` = '" + salebyUser + "'";
           }else{
               AndQuery = 1;
               query += " fare.`owner` = '" + salebyUser + "'";
           }
        }
        
        if(termPayt != null && (!"".equalsIgnoreCase(termPayt))){
            if(AndQuery == 1){
                query += " and inv.term_pay = " + termPayt + "";
           }else{
               AndQuery = 1;
               query += " inv.term_pay = " + termPayt + "";
           }
        }
        
        if ((invoiceFromDate != null )&&(!"".equalsIgnoreCase(invoiceFromDate))) {
            if ((invoiceToDate != null )&&(!"".equalsIgnoreCase(invoiceToDate))) {
                if(AndQuery == 1){
                     query += " and inv.inv_date  BETWEEN  '" + invoiceFromDate + "' AND '" + invoiceToDate + "' ";
                }else{
                    AndQuery = 1;
                     query += " inv.inv_date  BETWEEN  '" + invoiceFromDate + "' AND '" + invoiceToDate + "' ";
                } 
            }
        }
        
        if ((issueFrom != null )&&(!"".equalsIgnoreCase(issueFrom))) {
            if ((issueTo != null )&&(!"".equalsIgnoreCase(issueTo))) {
                if(AndQuery == 1){
                     query += " and fare.issue_date  BETWEEN  '" + issueFrom + "' AND '" + issueTo + "' ";
                }else{
                    AndQuery = 1;
                     query += " fare.issue_date  BETWEEN  '" + issueFrom + "' AND '" + issueTo + "' ";
                } 
            }
        }
        
        // Query Summary Income
        if (paymentType != null && (!"".equalsIgnoreCase(paymentType)) ) {
           if(AndQuerySum == 1){
                querySum += " and fare.ticket_type = '" + paymentType + "'";
           }else{
               AndQuerySum = 1;
               querySum += " fare.ticket_type = '" + paymentType + "'";
           }
        }
        
        if(departmentt != null && (!"".equalsIgnoreCase(departmentt))){
            if(AndQuerySum == 1){
                querySum += " and fare.department = '" + departmentt + "'";
           }else{
               AndQuerySum = 1;
               querySum += " fare.department = '" + departmentt + "'";
           }
        }
        
        if(salebyUser != null && (!"".equalsIgnoreCase(salebyUser))){
            if(AndQuerySum == 1){
                querySum += " and fare.`owner` = '" + salebyUser + "'";
           }else{
               AndQuerySum = 1;
               querySum += " fare.`owner` = '" + salebyUser + "'";
           }
        }
        
        if(termPayt != null && (!"".equalsIgnoreCase(termPayt))){
            if(AndQuerySum == 1){
                querySum += " and inv.term_pay = " + termPayt + "";
           }else{
               AndQuerySum = 1;
               querySum += " inv.term_pay = " + termPayt + "";
           }
        }
        
        if ((invoiceFromDate != null )&&(!"".equalsIgnoreCase(invoiceFromDate))) {
            if ((invoiceToDate != null )&&(!"".equalsIgnoreCase(invoiceToDate))) {
                if(AndQuerySum == 1){
                     querySum += " and inv.inv_date  BETWEEN  '" + invoiceFromDate + "' AND '" + invoiceToDate + "' ";
                }else{
                    AndQuerySum = 1;
                     querySum += " inv.inv_date  BETWEEN  '" + invoiceFromDate + "' AND '" + invoiceToDate + "' ";
                } 
            }
        }
        
        if ((issueFrom != null )&&(!"".equalsIgnoreCase(issueFrom))) {
            if ((issueTo != null )&&(!"".equalsIgnoreCase(issueTo))) {
                if(AndQuerySum == 1){
                     querySum += " and fare.issue_date  BETWEEN  '" + issueFrom + "' AND '" + issueTo + "' ";
                }else{
                    AndQuerySum = 1;
                     querySum += " fare.issue_date  BETWEEN  '" + issueFrom + "' AND '" + issueTo + "' ";
                } 
            }
        }
        querySum += " group by `fare`.`ticket_type`,`fare`.`ticket_rounting`";
        query += " group by `fare`.`ticket_type`,`fare`.`ticket_rounting`,substr(`fare`.`ticket_no`,1,3)";
        System.out.println("query sum : " + querySum);
        System.out.println("query : " + query);
        List<Object[]> TicketCommissionReceive = session.createSQLQuery(query )
                .addScalar("typepayment", Hibernate.STRING)
                .addScalar("typerounting", Hibernate.STRING)
                .addScalar("pax", Hibernate.STRING)
                .addScalar("air", Hibernate.STRING)
                .addScalar("comairline", Hibernate.STRING)
//                .addScalar("littlecom", Hibernate.STRING)
                .addScalar("payagent", Hibernate.STRING)
                .addScalar("rcagent", Hibernate.STRING)
                .addScalar("payrefund", Hibernate.STRING)
                .addScalar("comreceive", Hibernate.STRING)
                .list();
        for (Object[] B : TicketCommissionReceive) {
            TicketCommissionReceive ar = new TicketCommissionReceive();
            
            if(invoiceFromDate != null && !"".equals(invoiceFromDate)){
                String  invoice = ""+ invoiceFromDate + " To " + invoiceToDate;
                ar.setInvoicedatePage(invoice);
            }else{
                ar.setInvoicedatePage("");
            }
            if(issueFrom != null && !"".equals(issueFrom)){
                String issue = ""+ issueFrom + " To " + issueTo;
                ar.setIssuedatePage(issue);
            }else{
                ar.setIssuedatePage("");
            }
            if(departmentt != null && !"".equals(departmentt)){
                ar.setDepartmentPage(departmentt);
            }else{
                ar.setDepartmentPage("");
            }
            if(salebyUser != null && !"".equals(salebyUser)){
               ar.setSalsestaffPage(salebyUser);
            }else{
                ar.setSalsestaffPage("");
            }
            
            ar.setPrintbyPage(printby);
            
            ar.setTypepayment(util.ConvertString(B[0]) == null ? "" : util.ConvertString(B[0]));
            ar.setTyperounting(util.ConvertString(B[1])== null ? "" : util.ConvertString(B[1]));
            ar.setPax(util.ConvertString(B[2])== null ? "" : util.ConvertString(B[2]));
            ar.setAir(util.ConvertString(B[3])== null ? "" : util.ConvertString(B[3]));
            ar.setComairline(util.ConvertString(B[4])== null ? "" : util.ConvertString(B[4]));
            ar.setLittlecom("");
            ar.setPayagent(util.ConvertString(B[5])== null ? "" : util.ConvertString(B[5]));
            ar.setRcagent(util.ConvertString(B[6])== null ? "" : util.ConvertString(B[6]));
            ar.setPayrefund(util.ConvertString(B[7])== null ? "" : util.ConvertString(B[7]));
            ar.setComreceive(util.ConvertString(B[8])== null ? "" : util.ConvertString(B[8]));
            data.add(ar);
        }
        
        List<Object[]> TicketCommissionReceiveSum = session.createSQLQuery(querySum)
                .addScalar("typepayment", Hibernate.STRING)
                .addScalar("typerounting", Hibernate.STRING)
                .addScalar("pax", Hibernate.STRING)
                .addScalar("air", Hibernate.STRING)
                .addScalar("comairline", Hibernate.STRING)
//                .addScalar("littlecom", Hibernate.STRING)
                .addScalar("payagent", Hibernate.STRING)
                .addScalar("rcagent", Hibernate.STRING)
                .addScalar("payrefund", Hibernate.STRING)
                .addScalar("comreceive", Hibernate.STRING)
                .list();
        
        for (Object[] B : TicketCommissionReceiveSum) {
            TicketCommissionReceive ar = new TicketCommissionReceive();
            ar.setTypepayment(util.ConvertString(B[0]) == null ? "" : util.ConvertString(B[0]));
            ar.setTyperounting(util.ConvertString(B[1])== null ? "" : util.ConvertString(B[1]));
            ar.setPax(util.ConvertString(B[2])== null ? "" : util.ConvertString(B[2]));
            ar.setAir(util.ConvertString(B[3])== null ? "" : util.ConvertString(B[3]));
            ar.setComairline(util.ConvertString(B[4])== null ? "" : util.ConvertString(B[4]));
            ar.setLittlecom("");
            ar.setPayagent(util.ConvertString(B[5])== null ? "" : util.ConvertString(B[5]));
            ar.setRcagent(util.ConvertString(B[6])== null ? "" : util.ConvertString(B[6]));
            ar.setPayrefund(util.ConvertString(B[7])== null ? "" : util.ConvertString(B[7]));
            ar.setComreceive(util.ConvertString(B[8])== null ? "" : util.ConvertString(B[8]));
            dataSum.add(ar);
        }
        
        ListTicketCommissionReceive total = new ListTicketCommissionReceive();
        total.setTicketCommmissionReceive(data);
        total.setTicketCommmissionReceiveSum(dataSum);
        listTotal.add(total);
        
        session.close();
        this.sessionFactory.close();
        return listTotal;
    }
    
   
}
