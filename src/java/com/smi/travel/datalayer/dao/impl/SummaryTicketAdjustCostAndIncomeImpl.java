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
import com.smi.travel.datalayer.view.entity.SummaryTicketCostAndIncomeView;
import com.smi.travel.datalayer.view.entity.TicketCommissionReceive;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
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
        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        List data = new ArrayList();
        List dataSum = new ArrayList();
        String query = "";
        String querySum = "";
        int AndQuery = 0;
        int AndQuerySum = 0;
        System.out.println("Term Pay : " + termPayt);
        if( invoiceFromDate == null  &&  invoiceToDate == null  && issueFrom == null && issueTo == null && paymentType == null && departmentt == null && salebyUser == null && termPayt == null){
            query = "SELECT ( CASE WHEN (`fare`.`ticket_type` = 'B') THEN 'BSP' WHEN (`fare`.`ticket_type` = 'A') THEN 'AGENT' WHEN (`fare`.`ticket_type` = 'D') THEN 'DOMESTIC' WHEN (`fare`.`ticket_type` = 'TI') THEN 'TG INTER' WHEN (`fare`.`ticket_type` = 'TD') THEN 'TG DOMESTIC' ELSE `fare`.`ticket_type` END ) AS `typepayment`, `fare`.`ticket_rounting` AS `typerounting`, count(`fare`.`ticket_no`) AS `pax`, substr(`fare`.`ticket_no`, 1, 3) AS `air`, ( CASE WHEN (`inv`.`inv_type` = 'T') THEN substr(`inv`.`inv_no`, 1, 5) WHEN fare.pv_code IS NOT NULL THEN substr(fare.pv_code, 1, 6) ELSE substr(`inv`.`inv_no`, 1, 6) END ) AS `invno`, sum(( `GET_COST_TICKETFARE` (`fare`.`id`) - CASE WHEN (`fare`.`pv_type` IS NOT NULL) THEN `fare`.`sale_price` ELSE 0 END )) AS `costinv`, ifnull( sum(( CASE WHEN ( `fare`.`department` = 'wendy' AND fare.pv_type IS NOT NULL ) THEN `fare`.`inv_amount` ELSE 0 END )), 0 ) AS `invoicewendy`, ifnull( sum(( CASE WHEN ( `fare`.`department` = 'inbound' AND fare.pv_type IS NOT NULL ) THEN `fare`.`inv_amount` ELSE 0 END )), 0 ) AS `invoiceinbound`, ifnull( sum(( CASE WHEN ( `fare`.`department` = 'outbound' AND fare.pv_type IS NOT NULL ) THEN `fare`.`inv_amount` ELSE 0 END )), 0 ) AS `invoiceoutbound`, sum( CASE WHEN fare.diff_vat < 0 AND fare.ticket_type = 'TD' THEN ifnull(`fare`.agent_commission, 0) ELSE 0 END ) AS `discount`, sum( CASE WHEN fare.ticket_rounting = 'C' AND `fare`.`is_wait_pay` != 1 THEN ifnull(`fare`.inv_amount, 0) ELSE 0 END ) AS `cancel`, sum(( CASE WHEN (`fare`.`is_wait_pay` = 1) THEN ifnull(`fare`.inv_amount, 0) ELSE 0 END )) AS `wait_pay`, sum( CASE WHEN fare.ticket_type = 'A' THEN ifnull(`fare`.diff_vat, 0) ELSE 0 END ) AS `rcagcom`, sum( CASE WHEN fare.diff_vat < 0 AND fare.ticket_type != 'A' THEN ifnull(`fare`.diff_vat *- 1, 0) ELSE 0 END ) AS `over`, sum( CASE WHEN fare.diff_vat > 0 AND fare.ticket_type != 'A' THEN ifnull(`fare`.diff_vat, 0) ELSE 0 END ) AS `litter`, sum( CASE WHEN (fare.pv_type IS NOT NULL) THEN `fare`.`inv_amount` ELSE 0 END + CASE WHEN fare.diff_vat < 0 AND fare.ticket_type != 'A' THEN ifnull(`fare`.diff_vat *- 1, 0) ELSE 0 END + CASE WHEN fare.diff_vat < 0 AND fare.ticket_type = 'TD' THEN ifnull(`fare`.agent_commission, 0) ELSE 0 END - CASE WHEN fare.ticket_rounting = 'C' AND `fare`.`is_wait_pay` != 1 THEN ifnull(`fare`.inv_amount, 0) ELSE 0 END - CASE WHEN (`fare`.`is_wait_pay` = 1) THEN ifnull(`fare`.diff_vat, 0) ELSE 0 END - CASE WHEN fare.ticket_type = 'A' THEN ifnull(`fare`.diff_vat, 0) ELSE 0 END - CASE WHEN fare.diff_vat > 0 AND fare.ticket_type != 'A' THEN ifnull(`fare`.diff_vat, 0) ELSE 0 END ) AS `total_balance` FROM (((( `ticket_fare_airline` `fare` LEFT JOIN `ticket_fare_invoice` `finv` ON (( `finv`.`ticket_fare_id` = `fare`.`id` ))) LEFT JOIN `invoice` `inv` ON (( `inv`.`id` = `finv`.`invoice_id` OR inv.inv_no = fare.remark ))) LEFT JOIN `m_accterm` `term` ON (( `term`.`id` = `inv`.`term_pay` ))) LEFT JOIN `staff` `st` ON ((`st`.`name` = `fare`.`owner`)) LEFT JOIN ticket_fare_airline fave ON fave.pv_code IS NOT NULL AND fare.id = fave.id )   " ; 
            querySum = "SELECT ( CASE WHEN (`fare`.`ticket_type` = 'B') THEN 'BSP' WHEN (`fare`.`ticket_type` = 'A') THEN 'AGENT' WHEN (`fare`.`ticket_type` = 'D') THEN 'DOMESTIC' WHEN (`fare`.`ticket_type` = 'TI') THEN 'TG INTER' WHEN (`fare`.`ticket_type` = 'TD') THEN 'TG DOMESTIC' ELSE `fare`.`ticket_type` END ) AS `typepayment`, `fare`.`ticket_rounting` AS `typerounting`, count(`fare`.`ticket_no`) AS `pax`, ( CASE WHEN (`inv`.`inv_type` = 'T') THEN substr(`inv`.`inv_no`, 1, 5) WHEN fare.pv_code IS NOT NULL THEN substr(fare.pv_code, 1, 6) ELSE substr(`inv`.`inv_no`, 1, 6) END ) AS `invno`, sum(( `GET_COST_TICKETFARE` (`fare`.`id`) - CASE WHEN (`fare`.`pv_type` IS NOT NULL) THEN `fare`.`sale_price` ELSE 0 END )) AS `costinv`, ifnull( sum(( CASE WHEN ( `fare`.`department` = 'wendy' AND fare.pv_type IS NOT NULL ) THEN `fare`.`inv_amount` ELSE 0 END )), 0 ) AS `invoicewendy`, ifnull( sum(( CASE WHEN ( `fare`.`department` = 'inbound' AND fare.pv_type IS NOT NULL ) THEN `fare`.`inv_amount` ELSE 0 END )), 0 ) AS `invoiceinbound`, ifnull( sum(( CASE WHEN ( `fare`.`department` = 'outbound' AND fare.pv_type IS NOT NULL ) THEN `fare`.`inv_amount` ELSE 0 END )), 0 ) AS `invoiceoutbound`, sum( CASE WHEN fare.diff_vat < 0 AND fare.ticket_type = 'TD' THEN ifnull(`fare`.agent_commission, 0) ELSE 0 END ) AS `discount`, sum( CASE WHEN fare.ticket_rounting = 'C' AND `fare`.`is_wait_pay` != 1 THEN ifnull(`fare`.inv_amount, 0) ELSE 0 END ) AS `cancel`, sum(( CASE WHEN (`fare`.`is_wait_pay` = 1) THEN ifnull(`fare`.inv_amount, 0) ELSE 0 END )) AS `wait_pay`, sum( CASE WHEN fare.ticket_type = 'A' THEN ifnull(`fare`.diff_vat, 0) ELSE 0 END ) AS `rcagcom`, sum( CASE WHEN fare.diff_vat < 0 AND fare.ticket_type != 'A' THEN ifnull(`fare`.diff_vat *- 1, 0) ELSE 0 END ) AS `over`, sum( CASE WHEN fare.diff_vat > 0 AND fare.ticket_type != 'A' THEN ifnull(`fare`.diff_vat, 0) ELSE 0 END ) AS `litter`, sum( CASE WHEN (fare.pv_type IS NOT NULL) THEN `fare`.`inv_amount` ELSE 0 END + CASE WHEN fare.diff_vat < 0 AND fare.ticket_type != 'A' THEN ifnull(`fare`.diff_vat *- 1, 0) ELSE 0 END + CASE WHEN fare.diff_vat < 0 AND fare.ticket_type = 'TD' THEN ifnull(`fare`.agent_commission, 0) ELSE 0 END - CASE WHEN fare.ticket_rounting = 'C' AND `fare`.`is_wait_pay` != 1 THEN ifnull(`fare`.inv_amount, 0) ELSE 0 END - CASE WHEN (`fare`.`is_wait_pay` = 1) THEN ifnull(`fare`.diff_vat, 0) ELSE 0 END - CASE WHEN fare.ticket_type = 'A' THEN ifnull(`fare`.diff_vat, 0) ELSE 0 END - CASE WHEN fare.diff_vat > 0 AND fare.ticket_type != 'A' THEN ifnull(`fare`.diff_vat, 0) ELSE 0 END ) AS `total_balance` FROM (((( `ticket_fare_airline` `fare` LEFT JOIN `ticket_fare_invoice` `finv` ON (( `finv`.`ticket_fare_id` = `fare`.`id` ))) LEFT JOIN `invoice` `inv` ON (( `inv`.`id` = `finv`.`invoice_id` OR inv.inv_no = fare.remark ))) LEFT JOIN `m_accterm` `term` ON (( `term`.`id` = `inv`.`term_pay` ))) LEFT JOIN `staff` `st` ON ((`st`.`name` = `fare`.`owner`)) LEFT JOIN ticket_fare_airline fave ON fave.pv_code IS NOT NULL AND fare.id = fave.id )   ";
        }else{
            query = "SELECT ( CASE WHEN (`fare`.`ticket_type` = 'B') THEN 'BSP' WHEN (`fare`.`ticket_type` = 'A') THEN 'AGENT' WHEN (`fare`.`ticket_type` = 'D') THEN 'DOMESTIC' WHEN (`fare`.`ticket_type` = 'TI') THEN 'TG INTER' WHEN (`fare`.`ticket_type` = 'TD') THEN 'TG DOMESTIC' ELSE `fare`.`ticket_type` END ) AS `typepayment`, `fare`.`ticket_rounting` AS `typerounting`, count(`fare`.`ticket_no`) AS `pax`, substr(`fare`.`ticket_no`, 1, 3) AS `air`, ( CASE WHEN (`inv`.`inv_type` = 'T') THEN substr(`inv`.`inv_no`, 1, 5) WHEN fare.pv_code IS NOT NULL THEN substr(fare.pv_code, 1, 6) ELSE substr(`inv`.`inv_no`, 1, 6) END ) AS `invno`, sum(( `GET_COST_TICKETFARE` (`fare`.`id`) - CASE WHEN (`fare`.`pv_type` IS NOT NULL) THEN `fare`.`sale_price` ELSE 0 END )) AS `costinv`, ifnull( sum(( CASE WHEN ( `fare`.`department` = 'wendy' AND fare.pv_type IS NOT NULL ) THEN `fare`.`inv_amount` ELSE 0 END )), 0 ) AS `invoicewendy`, ifnull( sum(( CASE WHEN ( `fare`.`department` = 'inbound' AND fare.pv_type IS NOT NULL ) THEN `fare`.`inv_amount` ELSE 0 END )), 0 ) AS `invoiceinbound`, ifnull( sum(( CASE WHEN ( `fare`.`department` = 'outbound' AND fare.pv_type IS NOT NULL ) THEN `fare`.`inv_amount` ELSE 0 END )), 0 ) AS `invoiceoutbound`, sum( CASE WHEN fare.diff_vat < 0 AND fare.ticket_type = 'TD' THEN ifnull(`fare`.agent_commission, 0) ELSE 0 END ) AS `discount`, sum( CASE WHEN fare.ticket_rounting = 'C' AND `fare`.`is_wait_pay` != 1 THEN ifnull(`fare`.inv_amount, 0) ELSE 0 END ) AS `cancel`, sum(( CASE WHEN (`fare`.`is_wait_pay` = 1) THEN ifnull(`fare`.inv_amount, 0) ELSE 0 END )) AS `wait_pay`, sum( CASE WHEN fare.ticket_type = 'A' THEN ifnull(`fare`.diff_vat, 0) ELSE 0 END ) AS `rcagcom`, sum( CASE WHEN fare.diff_vat < 0 AND fare.ticket_type != 'A' THEN ifnull(`fare`.diff_vat *- 1, 0) ELSE 0 END ) AS `over`, sum( CASE WHEN fare.diff_vat > 0 AND fare.ticket_type != 'A' THEN ifnull(`fare`.diff_vat, 0) ELSE 0 END ) AS `litter`, sum( CASE WHEN (fare.pv_type IS NOT NULL) THEN `fare`.`inv_amount` ELSE 0 END + CASE WHEN fare.diff_vat < 0 AND fare.ticket_type != 'A' THEN ifnull(`fare`.diff_vat *- 1, 0) ELSE 0 END + CASE WHEN fare.diff_vat < 0 AND fare.ticket_type = 'TD' THEN ifnull(`fare`.agent_commission, 0) ELSE 0 END - CASE WHEN fare.ticket_rounting = 'C' AND `fare`.`is_wait_pay` != 1 THEN ifnull(`fare`.inv_amount, 0) ELSE 0 END - CASE WHEN (`fare`.`is_wait_pay` = 1) THEN ifnull(`fare`.diff_vat, 0) ELSE 0 END - CASE WHEN fare.ticket_type = 'A' THEN ifnull(`fare`.diff_vat, 0) ELSE 0 END - CASE WHEN fare.diff_vat > 0 AND fare.ticket_type != 'A' THEN ifnull(`fare`.diff_vat, 0) ELSE 0 END ) AS `total_balance` FROM (((( `ticket_fare_airline` `fare` LEFT JOIN `ticket_fare_invoice` `finv` ON (( `finv`.`ticket_fare_id` = `fare`.`id` ))) LEFT JOIN `invoice` `inv` ON (( `inv`.`id` = `finv`.`invoice_id` OR inv.inv_no = fare.remark ))) LEFT JOIN `m_accterm` `term` ON (( `term`.`id` = `inv`.`term_pay` ))) LEFT JOIN `staff` `st` ON ((`st`.`name` = `fare`.`owner`)) LEFT JOIN ticket_fare_airline fave ON fave.pv_code IS NOT NULL AND fare.id = fave.id )  where " ;
            querySum = "SELECT ( CASE WHEN (`fare`.`ticket_type` = 'B') THEN 'BSP' WHEN (`fare`.`ticket_type` = 'A') THEN 'AGENT' WHEN (`fare`.`ticket_type` = 'D') THEN 'DOMESTIC' WHEN (`fare`.`ticket_type` = 'TI') THEN 'TG INTER' WHEN (`fare`.`ticket_type` = 'TD') THEN 'TG DOMESTIC' ELSE `fare`.`ticket_type` END ) AS `typepayment`, `fare`.`ticket_rounting` AS `typerounting`, count(`fare`.`ticket_no`) AS `pax`, ( CASE WHEN (`inv`.`inv_type` = 'T') THEN substr(`inv`.`inv_no`, 1, 5) WHEN fare.pv_code IS NOT NULL THEN substr(fare.pv_code, 1, 6) ELSE substr(`inv`.`inv_no`, 1, 6) END ) AS `invno`, sum(( `GET_COST_TICKETFARE` (`fare`.`id`) - CASE WHEN (`fare`.`pv_type` IS NOT NULL) THEN `fare`.`sale_price` ELSE 0 END )) AS `costinv`, ifnull( sum(( CASE WHEN ( `fare`.`department` = 'wendy' AND fare.pv_type IS NOT NULL ) THEN `fare`.`inv_amount` ELSE 0 END )), 0 ) AS `invoicewendy`, ifnull( sum(( CASE WHEN ( `fare`.`department` = 'inbound' AND fare.pv_type IS NOT NULL ) THEN `fare`.`inv_amount` ELSE 0 END )), 0 ) AS `invoiceinbound`, ifnull( sum(( CASE WHEN ( `fare`.`department` = 'outbound' AND fare.pv_type IS NOT NULL ) THEN `fare`.`inv_amount` ELSE 0 END )), 0 ) AS `invoiceoutbound`, sum( CASE WHEN fare.diff_vat < 0 AND fare.ticket_type = 'TD' THEN ifnull(`fare`.agent_commission, 0) ELSE 0 END ) AS `discount`, sum( CASE WHEN fare.ticket_rounting = 'C' AND `fare`.`is_wait_pay` != 1 THEN ifnull(`fare`.inv_amount, 0) ELSE 0 END ) AS `cancel`, sum(( CASE WHEN (`fare`.`is_wait_pay` = 1) THEN ifnull(`fare`.inv_amount, 0) ELSE 0 END )) AS `wait_pay`, sum( CASE WHEN fare.ticket_type = 'A' THEN ifnull(`fare`.diff_vat, 0) ELSE 0 END ) AS `rcagcom`, sum( CASE WHEN fare.diff_vat < 0 AND fare.ticket_type != 'A' THEN ifnull(`fare`.diff_vat *- 1, 0) ELSE 0 END ) AS `over`, sum( CASE WHEN fare.diff_vat > 0 AND fare.ticket_type != 'A' THEN ifnull(`fare`.diff_vat, 0) ELSE 0 END ) AS `litter`, sum( CASE WHEN (fare.pv_type IS NOT NULL) THEN `fare`.`inv_amount` ELSE 0 END + CASE WHEN fare.diff_vat < 0 AND fare.ticket_type != 'A' THEN ifnull(`fare`.diff_vat *- 1, 0) ELSE 0 END + CASE WHEN fare.diff_vat < 0 AND fare.ticket_type = 'TD' THEN ifnull(`fare`.agent_commission, 0) ELSE 0 END - CASE WHEN fare.ticket_rounting = 'C' AND `fare`.`is_wait_pay` != 1 THEN ifnull(`fare`.inv_amount, 0) ELSE 0 END - CASE WHEN (`fare`.`is_wait_pay` = 1) THEN ifnull(`fare`.diff_vat, 0) ELSE 0 END - CASE WHEN fare.ticket_type = 'A' THEN ifnull(`fare`.diff_vat, 0) ELSE 0 END - CASE WHEN fare.diff_vat > 0 AND fare.ticket_type != 'A' THEN ifnull(`fare`.diff_vat, 0) ELSE 0 END ) AS `total_balance` FROM (((( `ticket_fare_airline` `fare` LEFT JOIN `ticket_fare_invoice` `finv` ON (( `finv`.`ticket_fare_id` = `fare`.`id` ))) LEFT JOIN `invoice` `inv` ON (( `inv`.`id` = `finv`.`invoice_id` OR inv.inv_no = fare.remark ))) LEFT JOIN `m_accterm` `term` ON (( `term`.`id` = `inv`.`term_pay` ))) LEFT JOIN `staff` `st` ON ((`st`.`name` = `fare`.`owner`)) LEFT JOIN ticket_fare_airline fave ON fave.pv_code IS NOT NULL AND fare.id = fave.id )  where ";
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
                   //  query += " and inv.inv_date  BETWEEN  '" + invoiceFromDate + "' AND '" + invoiceToDate + "' ";
                     query += " and (( inv.inv_date  BETWEEN  '" + invoiceFromDate + "' AND '" + invoiceToDate + "' ) or (fave.issue_date BETWEEN  '" + invoiceFromDate + "' AND '" + invoiceToDate + "' ))";
                }else{
                    AndQuery = 1;
                    query += "  (( inv.inv_date  BETWEEN  '" + invoiceFromDate + "' AND '" + invoiceToDate + "' ) or (fave.issue_date BETWEEN  '" + invoiceFromDate + "' AND '" + invoiceToDate + "' ))";
                 //    query += " inv.inv_date  BETWEEN  '" + invoiceFromDate + "' AND '" + invoiceToDate + "' ";
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
                   //  querySum += " and inv.inv_date  BETWEEN  '" + invoiceFromDate + "' AND '" + invoiceToDate + "' ";
                     querySum += " and (( inv.inv_date  BETWEEN  '" + invoiceFromDate + "' AND '" + invoiceToDate + "' ) or (fave.issue_date BETWEEN  '" + invoiceFromDate + "' AND '" + invoiceToDate + "' ))";
                }else{
                    AndQuerySum = 1;
                    querySum += "  (( inv.inv_date  BETWEEN  '" + invoiceFromDate + "' AND '" + invoiceToDate + "' ) or (fave.issue_date BETWEEN  '" + invoiceFromDate + "' AND '" + invoiceToDate + "' ))";
                 //    querySum += " inv.inv_date  BETWEEN  '" + invoiceFromDate + "' AND '" + invoiceToDate + "' ";
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
        querySum += " GROUP BY `fare`.`ticket_type`, `fare`.`ticket_rounting`,   ( CASE WHEN (`inv`.`inv_type` = 'T') THEN substr(`inv`.`inv_no`, 1, 5)  WHEN fare.pv_code IS NOT NULL THEN substr(fare.pv_code, 1, 6)  ELSE substr(`inv`.`inv_no`, 1, 6) END )";
        query += " GROUP BY `fare`.`ticket_type`, `fare`.`ticket_rounting`, substr(`fare`.`ticket_no`, 1, 3),  ( CASE WHEN (`inv`.`inv_type` = 'T') THEN substr(`inv`.`inv_no`, 1, 5)  WHEN fare.pv_code IS NOT NULL THEN substr(fare.pv_code, 1, 6)  ELSE substr(`inv`.`inv_no`, 1, 6) END )";

        System.out.println("query sum : " + querySum);
        System.out.println("query : " + query);
        String termPaytemp = "";
        if("1".equalsIgnoreCase(termPayt)){
            termPaytemp = "cash on demand";
        }else if("2".equalsIgnoreCase(termPayt)){
            termPaytemp = "credit 7 days";
        }else if("3".equalsIgnoreCase(termPayt)){
            termPaytemp = "credit 14 days";
        }else if("4".equalsIgnoreCase(termPayt)){
            termPaytemp = "credit card";
        }else if("5".equalsIgnoreCase(termPayt)){
            termPaytemp = "credit 30 days";
        }else if("6".equalsIgnoreCase(termPayt)){
            termPaytemp = "post date cheque";
        }else if("7".equalsIgnoreCase(termPayt)){
            termPaytemp = "credit 15 days";
        }
        
        
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
                String  invoice = ""+ String.valueOf(sf.format(util.convertStringToDate(invoiceFromDate))) + " To " + String.valueOf(sf.format(util.convertStringToDate(invoiceToDate)));
                ar.setInvoicedatePage(invoice);
            }else{
                ar.setInvoicedatePage("");
            }
            if(issueFrom != null && !"".equals(issueFrom)){
                String issue = ""+ String.valueOf(sf.format(util.convertStringToDate(issueFrom)))  + " To " + String.valueOf(sf.format(util.convertStringToDate(issueTo)));
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
            ar.setTermPayPage(termPaytemp);
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
            //header 
            if(invoiceFromDate != null && !"".equals(invoiceFromDate)){
                String  invoice = ""+ String.valueOf(sf.format(util.convertStringToDate(invoiceFromDate))) + " To " + String.valueOf(sf.format(util.convertStringToDate(invoiceToDate)));
                ar.setInvoicedatePage(invoice);
            }else{
                ar.setInvoicedatePage("");
            }
            if(issueFrom != null && !"".equals(issueFrom)){
                String issue = ""+ String.valueOf(sf.format(util.convertStringToDate(issueFrom)))  + " To " + String.valueOf(sf.format(util.convertStringToDate(issueTo)));
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
            ar.setTermPayPage(termPaytemp);
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
    public List getSummaryTicketCostAndIncome(String reportType, String invoiceFromDate, String invoiceToDate, String issueFrom, String issueTo, String paymentType, String departmentt, String salebyUser, String termPayt,String printby) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        Date thisDate = new Date();
        List data = new ArrayList();
        String query = "";
        int AndQuery = 0;
        List dataSum = new ArrayList();
        String querySum = "";
        int AndQuerySum = 0;
        String Department = "ALL";
        String TermPay = "ALL";
        String Salestaff = "ALL";
        System.out.println("Term Pay : " + termPayt);
        if( invoiceFromDate == null  &&  invoiceToDate == null  && issueFrom == null && issueTo == null && paymentType == null && departmentt == null && salebyUser == null && termPayt == null){
            query = "SELECT ( CASE WHEN (`fare`.`ticket_type` = 'B') THEN 'BSP' WHEN (`fare`.`ticket_type` = 'A') THEN 'AGENT' WHEN (`fare`.`ticket_type` = 'D') THEN 'DOMESTIC' WHEN (`fare`.`ticket_type` = 'TI') THEN 'TG INTER' WHEN (`fare`.`ticket_type` = 'TD') THEN 'TG DOMESTIC' ELSE `fare`.`ticket_type` END ) AS `typepayment`, `fare`.`ticket_rounting` AS `typerounting`, count(`fare`.`ticket_no`) AS `pax`, substr(`fare`.`ticket_no`, 1, 3) AS `air`, `GET_TICKET_ISSUE` ( `fare`.`ticket_type`, `fare`.`ticket_rounting`, substr(`fare`.`ticket_no`, 1, 3)) AS `ticketissue`, ( CASE WHEN (`inv`.`inv_type` = 'T') THEN substr(`inv`.`inv_no`, 1, 5) WHEN fare.pv_code IS NOT NULL THEN substr(fare.pv_code, 1, 6) ELSE substr(`inv`.`inv_no`, 1, 6) END ) AS `invno`, sum( `GET_COST_TICKETFARE` (`fare`.`id`)) AS `cost`, sum(( CASE WHEN (( `fare`.`department` = 'inbound' ) AND (`fare`.`pv_type` = 10)) THEN `fare`.`sale_price` END )) AS `inbound`, sum(( CASE WHEN (( `fare`.`department` = 'wendy' AND (`fare`.`pv_type` = 10))) THEN `fare`.`sale_price` END )) AS `wendy`, sum(( CASE WHEN (( `fare`.`department` = 'outbound' AND (`fare`.`pv_type` = 10))) THEN `fare`.`sale_price` END )) AS `outbound`, sum(( CASE WHEN (`fare`.`pv_type` = 9) THEN `fare`.`sale_price` ELSE NULL END )) AS `refund`, sum(( CASE WHEN (`fare`.`pv_type` = 7) THEN `fare`.`sale_price` ELSE NULL END )) AS `businesstrip`, sum(( CASE WHEN (`fare`.`pv_type` = 8) THEN `fare`.`sale_price` ELSE NULL END )) AS `annualleave`, sum(( CASE WHEN (`fare`.`pv_type` = 10) AND fare.department IS NULL THEN `fare`.`sale_price` ELSE NULL END )) AS `noinvoice`, sum(( `GET_COST_TICKETFARE` (`fare`.`id`) - ( CASE WHEN (`fare`.`pv_type` IS NOT NULL) THEN `fare`.`sale_price` ELSE 0 END ))) AS `costinv`, sum(( CASE WHEN (( `fare`.`department` = 'Wendy' ) AND isnull(`fare`.`pv_type`)) THEN `fare`.`inv_amount` ELSE NULL END )) AS `invwendy`, sum(( CASE WHEN (( `fare`.`department` = 'Outbound' ) AND isnull(`fare`.`pv_type`)) THEN `fare`.`inv_amount` ELSE NULL END )) AS `invoutbound`, sum(( CASE WHEN (`fare`.`pv_type` = 9) THEN `fare`.`inv_amount` ELSE NULL END )) AS `invrefund`, sum(( CASE WHEN (`fare`.`pv_type` = 7) THEN `fare`.`inv_amount` ELSE NULL END )) AS `invbusinesstrip`, sum(( CASE WHEN (`fare`.`pv_type` = 8) THEN `fare`.`inv_amount` ELSE NULL END )) AS `invannualleave`, sum(( CASE WHEN (`fare`.`pv_type` = 10) THEN `fare`.`inv_amount` ELSE NULL END )) AS `invnoinvoice` FROM ((( `ticket_fare_airline` `fare` LEFT JOIN `ticket_fare_invoice` `finv` ON (( `finv`.`ticket_fare_id` = `fare`.`id` ))) LEFT JOIN `invoice` `inv` ON (( `inv`.`id` = `finv`.`invoice_id` ))) LEFT JOIN `m_accterm` `term` ON (( `term`.`id` = `inv`.`term_pay` )) LEFT JOIN ticket_fare_airline fave ON fave.pv_code IS NOT NULL AND fare.id = fave.id ) " ; 
            querySum = "SELECT ( CASE WHEN (`fare`.`ticket_type` = 'B') THEN 'BSP' WHEN (`fare`.`ticket_type` = 'A') THEN 'AGENT' WHEN (`fare`.`ticket_type` = 'D') THEN 'DOMESTIC' WHEN (`fare`.`ticket_type` = 'TI') THEN 'TG INTER' WHEN (`fare`.`ticket_type` = 'TD') THEN 'TG DOMESTIC' ELSE `fare`.`ticket_type` END ) AS `typepayment`, `fare`.`ticket_rounting` AS `typerounting`, count(`fare`.`ticket_no`) AS `pax`, `GET_TICKET_ISSUE_SUMMARY` ( `fare`.`ticket_type`, `fare`.`ticket_rounting` ) AS `ticketissue`, ( CASE WHEN (`inv`.`inv_type` = 'T') THEN substr(`inv`.`inv_no`, 1, 5) WHEN fare.pv_code IS NOT NULL THEN substr(fare.pv_code, 1, 6) ELSE substr(`inv`.`inv_no`, 1, 6) END ) AS `invno`, sum( `GET_COST_TICKETFARE` (`fare`.`id`)) AS `cost`, sum(( CASE WHEN (( `fare`.`department` = 'inbound' AND (`fare`.`pv_type` = 10))) THEN `fare`.`sale_price` END )) AS `inbound`, sum(( CASE WHEN (( `fare`.`department` = 'wendy' AND (`fare`.`pv_type` = 10))) THEN `fare`.`sale_price` END )) AS `wendy`, sum(( CASE WHEN (( `fare`.`department` = 'outbound' AND (`fare`.`pv_type` = 10))) THEN `fare`.`sale_price` END )) AS `outbound`, sum(( CASE WHEN (`fare`.`pv_type` = 9) THEN `fare`.`sale_price` ELSE NULL END )) AS `refund`, sum(( CASE WHEN (`fare`.`pv_type` = 7) THEN `fare`.`sale_price` ELSE NULL END )) AS `businesstrip`, sum(( CASE WHEN (`fare`.`pv_type` = 8) THEN `fare`.`sale_price` ELSE NULL END )) AS `annualleave`, sum(( CASE WHEN (`fare`.`pv_type` = 10) AND fare.department IS NULL THEN `fare`.`sale_price` ELSE NULL END )) AS `noinvoice`, sum(( `GET_COST_TICKETFARE` (`fare`.`id`) - CASE WHEN (`fare`.`pv_type` IS NOT NULL) THEN `fare`.`sale_price` ELSE 0 END )) AS `costinv`, sum(( CASE WHEN (( `fare`.`department` = 'Wendy' ) AND isnull(`fare`.`pv_type`)) THEN `fare`.`inv_amount` ELSE NULL END )) AS `invwendy`, sum(( CASE WHEN (( `fare`.`department` = 'Outbound' ) AND isnull(`fare`.`pv_type`)) THEN `fare`.`inv_amount` ELSE NULL END )) AS `invoutbound`, sum(( CASE WHEN (`fare`.`pv_type` = 9) THEN `fare`.`inv_amount` ELSE NULL END )) AS `invrefund`, sum(( CASE WHEN (`fare`.`pv_type` = 7) THEN `fare`.`inv_amount` ELSE NULL END )) AS `invbusinesstrip`, sum(( CASE WHEN (`fare`.`pv_type` = 8) THEN `fare`.`inv_amount` ELSE NULL END )) AS `invannualleave`, sum(( CASE WHEN (`fare`.`pv_type` = 10) THEN `fare`.`inv_amount` ELSE NULL END )) AS `invnoinvoice` FROM ((( `ticket_fare_airline` `fare` LEFT JOIN `ticket_fare_invoice` `finv` ON (( `finv`.`ticket_fare_id` = `fare`.`id` ))) LEFT JOIN `invoice` `inv` ON (( `inv`.`id` = `finv`.`invoice_id` ))) LEFT JOIN `m_accterm` `term` ON (( `term`.`id` = `inv`.`term_pay` )) LEFT JOIN ticket_fare_airline fave ON fave.pv_code IS NOT NULL AND fare.id = fave.id )  ";
        }else{
            query = "SELECT ( CASE WHEN (`fare`.`ticket_type` = 'B') THEN 'BSP' WHEN (`fare`.`ticket_type` = 'A') THEN 'AGENT' WHEN (`fare`.`ticket_type` = 'D') THEN 'DOMESTIC' WHEN (`fare`.`ticket_type` = 'TI') THEN 'TG INTER' WHEN (`fare`.`ticket_type` = 'TD') THEN 'TG DOMESTIC' ELSE `fare`.`ticket_type` END ) AS `typepayment`, `fare`.`ticket_rounting` AS `typerounting`, count(`fare`.`ticket_no`) AS `pax`, substr(`fare`.`ticket_no`, 1, 3) AS `air`, `GET_TICKET_ISSUE` ( `fare`.`ticket_type`, `fare`.`ticket_rounting`, substr(`fare`.`ticket_no`, 1, 3)) AS `ticketissue`, ( CASE WHEN (`inv`.`inv_type` = 'T') THEN substr(`inv`.`inv_no`, 1, 5) WHEN fare.pv_code IS NOT NULL THEN substr(fare.pv_code, 1, 6) ELSE substr(`inv`.`inv_no`, 1, 6) END ) AS `invno`, sum( `GET_COST_TICKETFARE` (`fare`.`id`)) AS `cost`, sum(( CASE WHEN (( `fare`.`department` = 'inbound' ) AND (`fare`.`pv_type` = 10)) THEN `fare`.`sale_price` END )) AS `inbound`, sum(( CASE WHEN (( `fare`.`department` = 'wendy' AND (`fare`.`pv_type` = 10))) THEN `fare`.`sale_price` END )) AS `wendy`, sum(( CASE WHEN (( `fare`.`department` = 'outbound' AND (`fare`.`pv_type` = 10))) THEN `fare`.`sale_price` END )) AS `outbound`, sum(( CASE WHEN (`fare`.`pv_type` = 9) THEN `fare`.`sale_price` ELSE NULL END )) AS `refund`, sum(( CASE WHEN (`fare`.`pv_type` = 7) THEN `fare`.`sale_price` ELSE NULL END )) AS `businesstrip`, sum(( CASE WHEN (`fare`.`pv_type` = 8) THEN `fare`.`sale_price` ELSE NULL END )) AS `annualleave`, sum(( CASE WHEN (`fare`.`pv_type` = 10) AND fare.department IS NULL THEN `fare`.`sale_price` ELSE NULL END )) AS `noinvoice`, sum(( `GET_COST_TICKETFARE` (`fare`.`id`) - ( CASE WHEN (`fare`.`pv_type` IS NOT NULL) THEN `fare`.`sale_price` ELSE 0 END ))) AS `costinv`, sum(( CASE WHEN (( `fare`.`department` = 'Wendy' ) AND isnull(`fare`.`pv_type`)) THEN `fare`.`inv_amount` ELSE NULL END )) AS `invwendy`, sum(( CASE WHEN (( `fare`.`department` = 'Outbound' ) AND isnull(`fare`.`pv_type`)) THEN `fare`.`inv_amount` ELSE NULL END )) AS `invoutbound`, sum(( CASE WHEN (`fare`.`pv_type` = 9) THEN `fare`.`inv_amount` ELSE NULL END )) AS `invrefund`, sum(( CASE WHEN (`fare`.`pv_type` = 7) THEN `fare`.`inv_amount` ELSE NULL END )) AS `invbusinesstrip`, sum(( CASE WHEN (`fare`.`pv_type` = 8) THEN `fare`.`inv_amount` ELSE NULL END )) AS `invannualleave`, sum(( CASE WHEN (`fare`.`pv_type` = 10) THEN `fare`.`inv_amount` ELSE NULL END )) AS `invnoinvoice` FROM ((( `ticket_fare_airline` `fare` LEFT JOIN `ticket_fare_invoice` `finv` ON (( `finv`.`ticket_fare_id` = `fare`.`id` ))) LEFT JOIN `invoice` `inv` ON (( `inv`.`id` = `finv`.`invoice_id` ))) LEFT JOIN `m_accterm` `term` ON (( `term`.`id` = `inv`.`term_pay` )) LEFT JOIN ticket_fare_airline fave ON fave.pv_code IS NOT NULL AND fare.id = fave.id )  where " ;
            querySum = "SELECT ( CASE WHEN (`fare`.`ticket_type` = 'B') THEN 'BSP' WHEN (`fare`.`ticket_type` = 'A') THEN 'AGENT' WHEN (`fare`.`ticket_type` = 'D') THEN 'DOMESTIC' WHEN (`fare`.`ticket_type` = 'TI') THEN 'TG INTER' WHEN (`fare`.`ticket_type` = 'TD') THEN 'TG DOMESTIC' ELSE `fare`.`ticket_type` END ) AS `typepayment`, `fare`.`ticket_rounting` AS `typerounting`, count(`fare`.`ticket_no`) AS `pax`, `GET_TICKET_ISSUE_SUMMARY` ( `fare`.`ticket_type`, `fare`.`ticket_rounting` ) AS `ticketissue`, ( CASE WHEN (`inv`.`inv_type` = 'T') THEN substr(`inv`.`inv_no`, 1, 5) WHEN fare.pv_code IS NOT NULL THEN substr(fare.pv_code, 1, 6) ELSE substr(`inv`.`inv_no`, 1, 6) END ) AS `invno`, sum( `GET_COST_TICKETFARE` (`fare`.`id`)) AS `cost`, sum(( CASE WHEN (( `fare`.`department` = 'inbound' AND (`fare`.`pv_type` = 10))) THEN `fare`.`sale_price` END )) AS `inbound`, sum(( CASE WHEN (( `fare`.`department` = 'wendy' AND (`fare`.`pv_type` = 10))) THEN `fare`.`sale_price` END )) AS `wendy`, sum(( CASE WHEN (( `fare`.`department` = 'outbound' AND (`fare`.`pv_type` = 10))) THEN `fare`.`sale_price` END )) AS `outbound`, sum(( CASE WHEN (`fare`.`pv_type` = 9) THEN `fare`.`sale_price` ELSE NULL END )) AS `refund`, sum(( CASE WHEN (`fare`.`pv_type` = 7) THEN `fare`.`sale_price` ELSE NULL END )) AS `businesstrip`, sum(( CASE WHEN (`fare`.`pv_type` = 8) THEN `fare`.`sale_price` ELSE NULL END )) AS `annualleave`, sum(( CASE WHEN (`fare`.`pv_type` = 10) AND fare.department IS NULL THEN `fare`.`sale_price` ELSE NULL END )) AS `noinvoice`, sum(( `GET_COST_TICKETFARE` (`fare`.`id`) - CASE WHEN (`fare`.`pv_type` IS NOT NULL) THEN `fare`.`sale_price` ELSE 0 END )) AS `costinv`, sum(( CASE WHEN (( `fare`.`department` = 'Wendy' ) AND isnull(`fare`.`pv_type`)) THEN `fare`.`inv_amount` ELSE NULL END )) AS `invwendy`, sum(( CASE WHEN (( `fare`.`department` = 'Outbound' ) AND isnull(`fare`.`pv_type`)) THEN `fare`.`inv_amount` ELSE NULL END )) AS `invoutbound`, sum(( CASE WHEN (`fare`.`pv_type` = 9) THEN `fare`.`inv_amount` ELSE NULL END )) AS `invrefund`, sum(( CASE WHEN (`fare`.`pv_type` = 7) THEN `fare`.`inv_amount` ELSE NULL END )) AS `invbusinesstrip`, sum(( CASE WHEN (`fare`.`pv_type` = 8) THEN `fare`.`inv_amount` ELSE NULL END )) AS `invannualleave`, sum(( CASE WHEN (`fare`.`pv_type` = 10) THEN `fare`.`inv_amount` ELSE NULL END )) AS `invnoinvoice` FROM ((( `ticket_fare_airline` `fare` LEFT JOIN `ticket_fare_invoice` `finv` ON (( `finv`.`ticket_fare_id` = `fare`.`id` ))) LEFT JOIN `invoice` `inv` ON (( `inv`.`id` = `finv`.`invoice_id` ))) LEFT JOIN `m_accterm` `term` ON (( `term`.`id` = `inv`.`term_pay` )) LEFT JOIN ticket_fare_airline fave ON fave.pv_code IS NOT NULL AND fare.id = fave.id )   where ";
        }
        
        if (paymentType != null && (!"".equalsIgnoreCase(paymentType)) ) {
           if(AndQuery == 1){
                query += " and fare.ticket_type = '" + paymentType + "'";
                querySum += " and fare.ticket_type = '" + paymentType + "'";
           }else{
               AndQuery = 1;
               query += " fare.ticket_type = '" + paymentType + "'";
               querySum += " fare.ticket_type = '" + paymentType + "'";
           }
        }
        
        if(departmentt != null && (!"".equalsIgnoreCase(departmentt))){
            if(AndQuery == 1){
                query += " and fare.department = '" + departmentt + "'";
                querySum += " and fare.department = '" + departmentt + "'";
           }else{
               AndQuery = 1;
               query += " fare.department = '" + departmentt + "'";
               querySum += " fare.department = '" + departmentt + "'";
           }
        }

        
        if(salebyUser != null && (!"".equalsIgnoreCase(salebyUser))){
            if(AndQuery == 1){
                query += " and fare.`owner` = '" + salebyUser + "'";
                querySum += " and fare.`owner` = '" + salebyUser + "'";
           }else{
               AndQuery = 1;
               query += " fare.`owner` = '" + salebyUser + "'";
               querySum += " fare.`owner` = '" + salebyUser + "'";
           }
           Salestaff = salebyUser;
        }else{
           Salestaff = "ALL";
        }
        
        if(termPayt != null && (!"".equalsIgnoreCase(termPayt))){
            if(AndQuery == 1){
                query += " and inv.term_pay = " + termPayt + "";
                querySum += " and inv.term_pay = " + termPayt + "";
           }else{
               AndQuery = 1;
               query += " inv.term_pay = " + termPayt + "";
               querySum += " inv.term_pay = " + termPayt + "";
           }
        }
        
        if ((invoiceFromDate != null )&&(!"".equalsIgnoreCase(invoiceFromDate))) {
            if ((invoiceToDate != null )&&(!"".equalsIgnoreCase(invoiceToDate))) {
                if(AndQuery == 1){
                     query += " and (( inv.inv_date  BETWEEN  '" + invoiceFromDate + "' AND '" + invoiceToDate + "' ) or (fave.issue_date BETWEEN  '" + invoiceFromDate + "' AND '" + invoiceToDate + "' ))";
                     querySum += " and (( inv.inv_date  BETWEEN  '" + invoiceFromDate + "' AND '" + invoiceToDate + "' ) or (fave.issue_date BETWEEN  '" + invoiceFromDate + "' AND '" + invoiceToDate + "' ))";
                }else{
                    AndQuery = 1;
                     query += "  (( inv.inv_date  BETWEEN  '" + invoiceFromDate + "' AND '" + invoiceToDate + "' ) or (fave.issue_date BETWEEN  '" + invoiceFromDate + "' AND '" + invoiceToDate + "' ))";
                     querySum += "  (( inv.inv_date  BETWEEN  '" + invoiceFromDate + "' AND '" + invoiceToDate + "' ) or (fave.issue_date BETWEEN  '" + invoiceFromDate + "' AND '" + invoiceToDate + "' ))";
                } 
            }
        }
        
        if ((issueFrom != null )&&(!"".equalsIgnoreCase(issueFrom))) {
            if ((issueTo != null )&&(!"".equalsIgnoreCase(issueTo))) {
                if(AndQuery == 1){
                     query += " and fare.issue_date  BETWEEN  '" + issueFrom + "' AND '" + issueTo + "' ";
                     querySum += " and fare.issue_date  BETWEEN  '" + issueFrom + "' AND '" + issueTo + "' ";
                }else{
                    AndQuery = 1;
                     query += " fare.issue_date  BETWEEN  '" + issueFrom + "' AND '" + issueTo + "' ";
                     querySum += " fare.issue_date  BETWEEN  '" + issueFrom + "' AND '" + issueTo + "' ";
                } 
            }
        }
        
        query += " GROUP BY `fare`.`ticket_type`, `fare`.`ticket_rounting`, substr(`fare`.`ticket_no`, 1, 3), ( CASE WHEN (`inv`.`inv_type` = 'T') THEN substr(`inv`.`inv_no`, 1, 5) WHEN fare.pv_code IS NOT NULL THEN substr(fare.pv_code, 1, 6) ELSE substr(`inv`.`inv_no`, 1, 6) END )";
        querySum += " GROUP BY `fare`.`ticket_type`, `fare`.`ticket_rounting`, ( CASE WHEN (`inv`.`inv_type` = 'T') THEN substr(`inv`.`inv_no`, 1, 5) WHEN fare.pv_code IS NOT NULL THEN substr(fare.pv_code, 1, 6) ELSE substr(`inv`.`inv_no`, 1, 6) END ) ";
        System.out.println("query : " + query);
        System.out.println("query sum : " + querySum);
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("dd-MM-yyyy hh:mm");
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy");
        

        
        if("wendy".equalsIgnoreCase(departmentt)){
            Department = "WENDY";
        }else if("inbound".equalsIgnoreCase(departmentt)){
            Department = "INBOUND";
        }else if("outbound".equalsIgnoreCase(departmentt)){
            Department = "OUTBOUND";
        }
        
        if("1".equalsIgnoreCase(termPayt)){
            TermPay = "cash on demand";
        }else if("2".equalsIgnoreCase(termPayt)){
            TermPay = "credit 7 days";
        }else if("3".equalsIgnoreCase(termPayt)){
            TermPay = "credit 14 days";
        }else if("4".equalsIgnoreCase(termPayt)){
            TermPay = "credit card";
        }else if("5".equalsIgnoreCase(termPayt)){
            TermPay = "credit 30 days";
        }else if("6".equalsIgnoreCase(termPayt)){
            TermPay = "post date cheque";
        }else if("7".equalsIgnoreCase(termPayt)){
            TermPay = "credit 15 days";
        }else{
            TermPay = "ALL";
        }
        
        List<Object[]> SummaryTicketCostAndIncome = session.createSQLQuery(query)
                .addScalar("typepayment", Hibernate.STRING)
                .addScalar("typerounting", Hibernate.STRING)
                .addScalar("pax", Hibernate.STRING)
                .addScalar("air", Hibernate.STRING)
                .addScalar("ticketissue", Hibernate.STRING)
                .addScalar("invno", Hibernate.STRING)
                .addScalar("cost", Hibernate.STRING)
                .addScalar("inbound", Hibernate.STRING)
                .addScalar("wendy", Hibernate.STRING)
                .addScalar("refund", Hibernate.STRING)
                .addScalar("businesstrip", Hibernate.STRING)
                .addScalar("annualleave", Hibernate.STRING)
                .addScalar("noinvoice", Hibernate.STRING)
                .addScalar("costinv", Hibernate.STRING)
                .addScalar("invwendy", Hibernate.STRING)
                .addScalar("invoutbound", Hibernate.STRING)
                .addScalar("invrefund", Hibernate.STRING)
                .addScalar("invbusinesstrip", Hibernate.STRING)
                .addScalar("invannualleave", Hibernate.STRING)
                .addScalar("invnoinvoice", Hibernate.STRING)
                .addScalar("outbound", Hibernate.STRING)
                .list();
        for (Object[] B : SummaryTicketCostAndIncome) {
            SummaryTicketCostAndIncomeView sumticket = new SummaryTicketCostAndIncomeView();
            //set header
            sumticket.setHeaderinvdatefrom("".equals(String.valueOf(invoiceFromDate)) ? "" : util.ConvertString(dateformat.format(util.convertStringToDate(invoiceFromDate))));
            sumticket.setHeaderinvdateto("".equals(String.valueOf(invoiceToDate)) ? "" : util.ConvertString(dateformat.format(util.convertStringToDate(invoiceToDate))));
            sumticket.setHeaderissuedatefrom("".equals(String.valueOf(issueFrom)) ? "" : util.ConvertString(dateformat.format(util.convertStringToDate(issueFrom))));
            sumticket.setHeaderissuedateto("".equals(String.valueOf(issueTo)) ? "" : util.ConvertString(dateformat.format(util.convertStringToDate(issueTo))));
            sumticket.setHeaderprintby(printby);
            sumticket.setHeaderprinton(String.valueOf(df.format(new Date())));
            sumticket.setHeaderdepartment(Department);
            sumticket.setHeadersalestaff(Salestaff);
            sumticket.setHeadertermpay(TermPay);
            //
            sumticket.setTypepayment(util.ConvertString(B[0]) == null ? "" : util.ConvertString(B[0]));
            sumticket.setTyperounting(util.ConvertString(B[1])== null ? "" : util.ConvertString(B[1]));
            sumticket.setPax(util.ConvertString(B[2])== null ? "" : util.ConvertString(B[2]));
            sumticket.setAir(util.ConvertString(B[3])== null ? "" : util.ConvertString(B[3]));
//            sumticket.setTicketissue(util.ConvertString(B[4])== null ? "" : util.ConvertString(B[4]));
            sumticket.setInvno(util.ConvertString(B[5])== null ? "" : util.ConvertString(B[5]));
            sumticket.setCost(util.ConvertString(B[6])== null ? "" : util.ConvertString(B[6]));
            sumticket.setInbound(util.ConvertString(B[7])== null ? "" : util.ConvertString(B[7]));
            sumticket.setWendy(util.ConvertString(B[8])== null ? "" : util.ConvertString(B[8]));
            sumticket.setRefund(util.ConvertString(B[9])== null ? "" : util.ConvertString(B[9]));
            sumticket.setBusinesstrip(util.ConvertString(B[10])== null ? "" : util.ConvertString(B[10]));
            sumticket.setAnnualleave(util.ConvertString(B[11])== null ? "" : util.ConvertString(B[11]));
            sumticket.setNoinvoice(util.ConvertString(B[12])== null ? "" : util.ConvertString(B[12]));
            sumticket.setCostinv(util.ConvertString(B[13])== null ? "" : util.ConvertString(B[13]));
            sumticket.setInvwendy(util.ConvertString(B[14])== null ? "" : util.ConvertString(B[14]));
            sumticket.setInvoutbound(util.ConvertString(B[15])== null ? "" : util.ConvertString(B[15]));
            sumticket.setInvrefund(util.ConvertString(B[16])== null ? "" : util.ConvertString(B[16]));
            sumticket.setInvbusinesstrip(util.ConvertString(B[17])== null ? "" : util.ConvertString(B[17]));
            sumticket.setInvannualleave(util.ConvertString(B[18])== null ? "" : util.ConvertString(B[18]));
            sumticket.setInvnoinvoice(util.ConvertString(B[19])== null ? "" : util.ConvertString(B[19]));
            sumticket.setOutbound(util.ConvertString(B[20])== null ? "" : util.ConvertString(B[20]));
            sumticket.setPage("income");
            data.add(sumticket);
        }
        
        int max = data.size();
        String typepayment = "";
        String typepaymentPast = "";
        String typeroute = "";
        String typeroutePast = "";
        String air = "";
        String airPast = "";
        BigDecimal cost = new BigDecimal(0);
        BigDecimal costPast = new BigDecimal(0);
        BigDecimal ticketissue = new BigDecimal(0);
        List<String> ticketissueList = new ArrayList<String>();
        for(int i=0; i<data.size(); i++){
            SummaryTicketCostAndIncomeView present = new SummaryTicketCostAndIncomeView();
            present = (SummaryTicketCostAndIncomeView) data.get(i);
            typepayment = (!"".equalsIgnoreCase(present.getTypepayment()) && present != null ? present.getTypepayment() : "");
            typeroute = (!"".equalsIgnoreCase(present.getTyperounting()) && present != null ? present.getTyperounting() : "");
            air = (!"".equalsIgnoreCase(present.getAir()) && present != null ? present.getAir() : "");
            cost = (!"".equalsIgnoreCase(present.getCost()) ? new BigDecimal(present.getCost()) : new BigDecimal(0));
            
            SummaryTicketCostAndIncomeView past = new SummaryTicketCostAndIncomeView();
            past = (i > 0 ? (SummaryTicketCostAndIncomeView) data.get(i-1) : null);
            typepaymentPast = (past != null ? past.getTypepayment() : "");
            typeroutePast = (past != null ? past.getTyperounting() : "");
            airPast = (past != null ? past.getAir() : "");
            costPast = (past != null ? new BigDecimal(past.getCost()) : new BigDecimal(0));
            
            if((typepayment.equalsIgnoreCase(typepaymentPast)) && (typeroute.equalsIgnoreCase(typeroutePast)) && (air.equalsIgnoreCase(airPast))){
                if(i == data.size()-1){
                    ticketissue = ticketissue.add(cost);
                    ticketissueList.add(String.valueOf(ticketissue));
                } else {
                    ticketissue = ticketissue.add(cost);   
                }
                            
            } else if((i > 0)){
                if(i == data.size()-1){
                    ticketissueList.add(String.valueOf(ticketissue));
                    ticketissue = new BigDecimal(0);
                    ticketissue = ticketissue.add(cost);
                    ticketissueList.add(String.valueOf(ticketissue));
                } else {
                    ticketissueList.add(String.valueOf(ticketissue));
                    ticketissue = new BigDecimal(0);
                    ticketissue = ticketissue.add(cost);
                }
                
            } else {
                if(i == data.size()-1){
                    ticketissue = new BigDecimal(0);
                    ticketissue = ticketissue.add(cost);
                    ticketissueList.add(String.valueOf(ticketissue));
                } else {
                    ticketissue = new BigDecimal(0);
                    ticketissue = ticketissue.add(cost);
                }
            }                        
        }
        
        int j = 0;
        for(int i=0; i<data.size(); i++){
            SummaryTicketCostAndIncomeView present = new SummaryTicketCostAndIncomeView();
            present = (SummaryTicketCostAndIncomeView) data.get(i);
            typepayment = (!"".equalsIgnoreCase(present.getTypepayment()) && present != null ? present.getTypepayment() : "");
            typeroute = (!"".equalsIgnoreCase(present.getTyperounting()) && present != null ? present.getTyperounting() : "");
            air = (!"".equalsIgnoreCase(present.getAir()) && present != null ? present.getAir() : "");
            
            SummaryTicketCostAndIncomeView past = new SummaryTicketCostAndIncomeView();
            past = (i > 0 ? (SummaryTicketCostAndIncomeView) data.get(i-1) : null);
            typepaymentPast = (past != null ? past.getTypepayment() : "");
            typeroutePast = (past != null ? past.getTyperounting() : "");
            airPast = (past != null ? past.getAir() : "");
            
            if((typepayment.equalsIgnoreCase(typepaymentPast)) && (typeroute.equalsIgnoreCase(typeroutePast)) && (air.equalsIgnoreCase(airPast))){
                present.setTicketissue(ticketissueList.get(j));
                
            } else if(i>0){
                j++;
                present.setTicketissue(ticketissueList.get(j));
                
            } else {
                present.setTicketissue(ticketissueList.get(j));
            }
                       
        }
        
        List<Object[]> SummaryTicketCostAndIncomeSummary = session.createSQLQuery(querySum)
                .addScalar("typepayment", Hibernate.STRING)
                .addScalar("typerounting", Hibernate.STRING)
                .addScalar("pax", Hibernate.STRING)
                .addScalar("ticketissue", Hibernate.STRING)
                .addScalar("invno", Hibernate.STRING)
                .addScalar("cost", Hibernate.STRING)
                .addScalar("inbound", Hibernate.STRING)
                .addScalar("wendy", Hibernate.STRING)
                .addScalar("refund", Hibernate.STRING)
                .addScalar("businesstrip", Hibernate.STRING)
                .addScalar("annualleave", Hibernate.STRING)
                .addScalar("noinvoice", Hibernate.STRING)
                .addScalar("costinv", Hibernate.STRING)
                .addScalar("invwendy", Hibernate.STRING)
                .addScalar("invoutbound", Hibernate.STRING)
                .addScalar("invrefund", Hibernate.STRING)
                .addScalar("invbusinesstrip", Hibernate.STRING)
                .addScalar("invannualleave", Hibernate.STRING)
                .addScalar("invnoinvoice", Hibernate.STRING)
                .addScalar("outbound", Hibernate.STRING)
                .list();
        for (Object[] B : SummaryTicketCostAndIncomeSummary) {
            SummaryTicketCostAndIncomeView sumticket = new SummaryTicketCostAndIncomeView();
            //set header
            sumticket.setHeaderinvdatefrom("".equals(String.valueOf(invoiceFromDate)) ? "" : util.ConvertString(dateformat.format(util.convertStringToDate(invoiceFromDate))));
            sumticket.setHeaderinvdateto("".equals(String.valueOf(invoiceToDate)) ? "" : util.ConvertString(dateformat.format(util.convertStringToDate(invoiceToDate))));
            sumticket.setHeaderissuedatefrom("".equals(String.valueOf(issueFrom)) ? "" : util.ConvertString(dateformat.format(util.convertStringToDate(issueFrom))));
            sumticket.setHeaderissuedateto("".equals(String.valueOf(issueTo)) ? "" : util.ConvertString(dateformat.format(util.convertStringToDate(issueTo))));
            sumticket.setHeaderprintby(printby);
            sumticket.setHeaderprinton(String.valueOf(df.format(new Date())));
            sumticket.setHeaderdepartment(Department);
            sumticket.setHeadersalestaff(Salestaff);
            sumticket.setHeadertermpay(TermPay);
            //
            sumticket.setTypepaymentSum(util.ConvertString(B[0]) == null ? "" : util.ConvertString(B[0]));
            sumticket.setTyperountingSum(util.ConvertString(B[1])== null ? "" : util.ConvertString(B[1]));
            sumticket.setPaxSum(util.ConvertString(B[2])== null ? "" : util.ConvertString(B[2]));
//            sumticket.setTicketissueSum(util.ConvertString(B[3])== null ? "" : util.ConvertString(B[3]));
            sumticket.setInvnoSum(util.ConvertString(B[4])== null ? "" : util.ConvertString(B[4]));
            sumticket.setCostSum(util.ConvertString(B[5])== null ? "" : util.ConvertString(B[5]));
            sumticket.setInboundSum(util.ConvertString(B[6])== null ? "" : util.ConvertString(B[6]));
            sumticket.setWendySum(util.ConvertString(B[7])== null ? "" : util.ConvertString(B[7]));
            sumticket.setRefundSum(util.ConvertString(B[8])== null ? "" : util.ConvertString(B[8]));
            sumticket.setBusinesstripSum(util.ConvertString(B[9])== null ? "" : util.ConvertString(B[9]));
            sumticket.setAnnualleaveSum(util.ConvertString(B[10])== null ? "" : util.ConvertString(B[10]));
            sumticket.setNoinvoiceSum(util.ConvertString(B[11])== null ? "" : util.ConvertString(B[11]));
            sumticket.setCostinvSum(util.ConvertString(B[12])== null ? "" : util.ConvertString(B[12]));
            sumticket.setInvwendySum(util.ConvertString(B[13])== null ? "" : util.ConvertString(B[13]));
            sumticket.setInvoutboundSum(util.ConvertString(B[14])== null ? "" : util.ConvertString(B[14]));
            sumticket.setInvrefundSum(util.ConvertString(B[15])== null ? "" : util.ConvertString(B[15]));
            sumticket.setInvbusinesstripSum(util.ConvertString(B[16])== null ? "" : util.ConvertString(B[16]));
            sumticket.setInvannualleaveSum(util.ConvertString(B[17])== null ? "" : util.ConvertString(B[17]));
            sumticket.setInvnoinvoiceSum(util.ConvertString(B[18])== null ? "" : util.ConvertString(B[18]));
            sumticket.setOutbound(util.ConvertString(B[19])== null ? "" : util.ConvertString(B[19]));
            sumticket.setPage("incomesum");
            data.add(sumticket);
        }
        
        typepayment = "";
        typepaymentPast = "";
        typeroute = "";
        typeroutePast = "";
        cost = new BigDecimal(0);
        costPast = new BigDecimal(0);
        ticketissue = new BigDecimal(0);
        List<String> ticketissueSumList = new ArrayList<String>();
        for(int i=max; i<data.size(); i++){
            SummaryTicketCostAndIncomeView present = new SummaryTicketCostAndIncomeView();
            present = (SummaryTicketCostAndIncomeView) data.get(i);
            typepayment = (!"".equalsIgnoreCase(present.getTypepaymentSum()) && present != null ? present.getTypepaymentSum() : "");
            typeroute = (!"".equalsIgnoreCase(present.getTyperountingSum()) && present != null ? present.getTyperountingSum() : "");
            cost = (!"".equalsIgnoreCase(present.getCostSum()) ? new BigDecimal(present.getCostSum()) : new BigDecimal(0));
            
            SummaryTicketCostAndIncomeView past = new SummaryTicketCostAndIncomeView();
            past = (i > max ? (SummaryTicketCostAndIncomeView) data.get(i-1) : null);
            typepaymentPast = (past != null ? past.getTypepaymentSum() : "");
            typeroutePast = (past != null ? past.getTyperountingSum() : "");
            costPast = (past != null ? new BigDecimal(past.getCostSum()) : new BigDecimal(0));
            
            if((typepayment.equalsIgnoreCase(typepaymentPast)) && (typeroute.equalsIgnoreCase(typeroutePast))){
                if(i == data.size()-1){
                    ticketissue = ticketissue.add(cost);
                    ticketissueSumList.add(String.valueOf(ticketissue));
                } else {
                    ticketissue = ticketissue.add(cost);   
                }
                            
            } else if((i > max)){
                if(i == data.size()-1){
                    ticketissueSumList.add(String.valueOf(ticketissue));
                    ticketissue = new BigDecimal(0);
                    ticketissue = ticketissue.add(cost);
                    ticketissueSumList.add(String.valueOf(ticketissue));
                } else {
                    ticketissueSumList.add(String.valueOf(ticketissue));
                    ticketissue = new BigDecimal(0);
                    ticketissue = ticketissue.add(cost);
                }
                
            } else {
                if(i == data.size()-1){
                    ticketissue = new BigDecimal(0);
                    ticketissue = ticketissue.add(cost);
                    ticketissueSumList.add(String.valueOf(ticketissue));
                } else {
                    ticketissue = new BigDecimal(0);
                    ticketissue = ticketissue.add(cost);
                }
            }                        
        }
        
        j = 0;
        for(int i=max; i<data.size(); i++){
            SummaryTicketCostAndIncomeView present = new SummaryTicketCostAndIncomeView();
            present = (SummaryTicketCostAndIncomeView) data.get(i);
            typepayment = (!"".equalsIgnoreCase(present.getTypepaymentSum()) && present != null ? present.getTypepaymentSum(): "");
            typeroute = (!"".equalsIgnoreCase(present.getTyperountingSum()) && present != null ? present.getTyperountingSum(): "");
            
            SummaryTicketCostAndIncomeView past = new SummaryTicketCostAndIncomeView();
            past = (i > max ? (SummaryTicketCostAndIncomeView) data.get(i-1) : null);
            typepaymentPast = (past != null ? past.getTypepaymentSum(): "");
            typeroutePast = (past != null ? past.getTyperountingSum(): "");
            
            if((typepayment.equalsIgnoreCase(typepaymentPast)) && (typeroute.equalsIgnoreCase(typeroutePast))){
                present.setTicketissueSum(ticketissueSumList.get(j));
                
            } else if(i>max){
                j++;
                present.setTicketissueSum(ticketissueSumList.get(j));
                
            } else {
                present.setTicketissueSum(ticketissueSumList.get(j));
            }
                       
        }
        
        session.close();
        this.sessionFactory.close();
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
            query = "SELECT ( CASE WHEN (`fare`.`ticket_type` = 'B') THEN 'BSP' WHEN (`fare`.`ticket_type` = 'A') THEN 'AGENT' WHEN (`fare`.`ticket_type` = 'D') THEN 'DOMESTIC' WHEN (`fare`.`ticket_type` = 'TI') THEN 'TG INTER' WHEN (`fare`.`ticket_type` = 'TD') THEN 'TG DOMESTIC' ELSE `fare`.`ticket_type` END ) AS `typepayment`, `fare`.`ticket_rounting` AS `typerounting`, count(`fare`.`ticket_no`) AS `pax`, substr(`fare`.`ticket_no`, 1, 3) AS `air`, sum( ifnull( `fare`.`ticket_commission`, 0 )) AS `comairline`, sum( ifnull( `fare`.`litter_commission`, 0 )) AS `littlecom`, sum( ifnull( `fare`.`agent_commission`, 0 )) AS `payagent`, sum( ifnull(`refd`.`agent_comission`, 0)) AS `rcagent`, sum( ifnull( `refd`.`airline_comission`, 0 )) AS `payrefund`, sum((((( ifnull( `fare`.`ticket_commission`, 0 ) + ifnull( `fare`.`litter_commission`, 0 )) - ifnull( `fare`.`agent_commission`, 0 )) + ifnull(`refd`.`agent_comission`, 0)) - ifnull( `refd`.`airline_comission`, 0 ))) AS `comreceive` FROM ((((( `ticket_fare_airline` `fare` LEFT JOIN `ticket_fare_invoice` `finv` ON (( `finv`.`ticket_fare_id` = `fare`.`id` ))) LEFT JOIN `invoice` `inv` ON (( `inv`.`id` = `finv`.`invoice_id` ))) LEFT JOIN `m_accterm` `term` ON (( `term`.`id` = `inv`.`term_pay` )))) LEFT JOIN `refund_airticket_detail` `refd` ON (( `refd`.`ticket_no` = `fare`.ticket_no ))) " ; 
            querySum = "SELECT ( CASE WHEN (`fare`.`ticket_type` = 'B') THEN 'BSP' WHEN (`fare`.`ticket_type` = 'A') THEN 'AGENT' WHEN (`fare`.`ticket_type` = 'D') THEN 'DOMESTIC' WHEN (`fare`.`ticket_type` = 'TI') THEN 'TG INTER' WHEN (`fare`.`ticket_type` = 'TD') THEN 'TG DOMESTIC' ELSE `fare`.`ticket_type` END ) AS `typepayment`, `fare`.`ticket_rounting` AS `typerounting`, count(`fare`.`ticket_no`) AS `pax`, substr(`fare`.`ticket_no`, 1, 3) AS `air`, sum( ifnull( `fare`.`ticket_commission`, 0 )) AS `comairline`, sum( ifnull( `fare`.`litter_commission`, 0 )) AS `littlecom`, sum( ifnull( `fare`.`agent_commission`, 0 )) AS `payagent`, sum( ifnull(`refd`.`agent_comission`, 0)) AS `rcagent`, sum( ifnull( `refd`.`airline_comission`, 0 )) AS `payrefund`, sum((((( ifnull( `fare`.`ticket_commission`, 0 ) + ifnull( `fare`.`litter_commission`, 0 )) - ifnull( `fare`.`agent_commission`, 0 )) + ifnull(`refd`.`agent_comission`, 0)) - ifnull( `refd`.`airline_comission`, 0 ))) AS `comreceive` FROM ((((( `ticket_fare_airline` `fare` LEFT JOIN `ticket_fare_invoice` `finv` ON (( `finv`.`ticket_fare_id` = `fare`.`id` ))) LEFT JOIN `invoice` `inv` ON (( `inv`.`id` = `finv`.`invoice_id` ))) LEFT JOIN `m_accterm` `term` ON (( `term`.`id` = `inv`.`term_pay` )))) LEFT JOIN `refund_airticket_detail` `refd` ON (( `refd`.`ticket_no` = `fare`.ticket_no ))) ";
        }else{
            query = "SELECT ( CASE WHEN (`fare`.`ticket_type` = 'B') THEN 'BSP' WHEN (`fare`.`ticket_type` = 'A') THEN 'AGENT' WHEN (`fare`.`ticket_type` = 'D') THEN 'DOMESTIC' WHEN (`fare`.`ticket_type` = 'TI') THEN 'TG INTER' WHEN (`fare`.`ticket_type` = 'TD') THEN 'TG DOMESTIC' ELSE `fare`.`ticket_type` END ) AS `typepayment`, `fare`.`ticket_rounting` AS `typerounting`, count(`fare`.`ticket_no`) AS `pax`, substr(`fare`.`ticket_no`, 1, 3) AS `air`, sum( ifnull( `fare`.`ticket_commission`, 0 )) AS `comairline`, sum( ifnull( `fare`.`litter_commission`, 0 )) AS `littlecom`, sum( ifnull( `fare`.`agent_commission`, 0 )) AS `payagent`, sum( ifnull(`refd`.`agent_comission`, 0)) AS `rcagent`, sum( ifnull( `refd`.`airline_comission`, 0 )) AS `payrefund`, sum((((( ifnull( `fare`.`ticket_commission`, 0 ) + ifnull( `fare`.`litter_commission`, 0 )) - ifnull( `fare`.`agent_commission`, 0 )) + ifnull(`refd`.`agent_comission`, 0)) - ifnull( `refd`.`airline_comission`, 0 ))) AS `comreceive` FROM ((((( `ticket_fare_airline` `fare` LEFT JOIN `ticket_fare_invoice` `finv` ON (( `finv`.`ticket_fare_id` = `fare`.`id` ))) LEFT JOIN `invoice` `inv` ON (( `inv`.`id` = `finv`.`invoice_id` ))) LEFT JOIN `m_accterm` `term` ON (( `term`.`id` = `inv`.`term_pay` )))) LEFT JOIN `refund_airticket_detail` `refd` ON (( `refd`.`ticket_no` = `fare`.ticket_no ))) where " ;
            querySum = "SELECT ( CASE WHEN (`fare`.`ticket_type` = 'B') THEN 'BSP' WHEN (`fare`.`ticket_type` = 'A') THEN 'AGENT' WHEN (`fare`.`ticket_type` = 'D') THEN 'DOMESTIC' WHEN (`fare`.`ticket_type` = 'TI') THEN 'TG INTER' WHEN (`fare`.`ticket_type` = 'TD') THEN 'TG DOMESTIC' ELSE `fare`.`ticket_type` END ) AS `typepayment`, `fare`.`ticket_rounting` AS `typerounting`, count(`fare`.`ticket_no`) AS `pax`, substr(`fare`.`ticket_no`, 1, 3) AS `air`, sum( ifnull( `fare`.`ticket_commission`, 0 )) AS `comairline`, sum( ifnull( `fare`.`litter_commission`, 0 )) AS `littlecom`, sum( ifnull( `fare`.`agent_commission`, 0 )) AS `payagent`, sum( ifnull(`refd`.`agent_comission`, 0)) AS `rcagent`, sum( ifnull( `refd`.`airline_comission`, 0 )) AS `payrefund`, sum((((( ifnull( `fare`.`ticket_commission`, 0 ) + ifnull( `fare`.`litter_commission`, 0 )) - ifnull( `fare`.`agent_commission`, 0 )) + ifnull(`refd`.`agent_comission`, 0)) - ifnull( `refd`.`airline_comission`, 0 ))) AS `comreceive` FROM ((((( `ticket_fare_airline` `fare` LEFT JOIN `ticket_fare_invoice` `finv` ON (( `finv`.`ticket_fare_id` = `fare`.`id` ))) LEFT JOIN `invoice` `inv` ON (( `inv`.`id` = `finv`.`invoice_id` ))) LEFT JOIN `m_accterm` `term` ON (( `term`.`id` = `inv`.`term_pay` )))) LEFT JOIN `refund_airticket_detail` `refd` ON (( `refd`.`ticket_no` = `fare`.ticket_no ))) where ";
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
        querySum += " group by `fare`.`ticket_type`,`fare`.`ticket_rounting` ";
        query += " group by `fare`.`ticket_type`,`fare`.`ticket_rounting`,substr(`fare`.`ticket_no`,1,3)";
        System.out.println("query sum : " + querySum);
        System.out.println("query : " + query);
        List<Object[]> TicketCommissionReceive = session.createSQLQuery(query)
                .addScalar("typepayment", Hibernate.STRING)
                .addScalar("typerounting", Hibernate.STRING)
                .addScalar("pax", Hibernate.STRING)
                .addScalar("air", Hibernate.STRING)
                .addScalar("comairline", Hibernate.STRING)
                .addScalar("payagent", Hibernate.STRING)
                .addScalar("rcagent", Hibernate.STRING)
                .addScalar("payrefund", Hibernate.STRING)
                .addScalar("comreceive", Hibernate.STRING)
                .addScalar("littlecom", Hibernate.STRING)
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
            ar.setPayagent(util.ConvertString(B[5])== null ? "" : util.ConvertString(B[5]));
            ar.setRcagent(util.ConvertString(B[6])== null ? "" : util.ConvertString(B[6]));
            ar.setPayrefund(util.ConvertString(B[7])== null ? "" : util.ConvertString(B[7]));
            ar.setComreceive(util.ConvertString(B[8])== null ? "" : util.ConvertString(B[8]));
            ar.setLittlecom(util.ConvertString(B[9])== null ? "" : util.ConvertString(B[9]));
            data.add(ar);
        }
        
        List<Object[]> TicketCommissionReceiveSum = session.createSQLQuery(querySum)
                .addScalar("typepayment", Hibernate.STRING)
                .addScalar("typerounting", Hibernate.STRING)
                .addScalar("pax", Hibernate.STRING)
                .addScalar("air", Hibernate.STRING)
                .addScalar("comairline", Hibernate.STRING)
                .addScalar("payagent", Hibernate.STRING)
                .addScalar("rcagent", Hibernate.STRING)
                .addScalar("payrefund", Hibernate.STRING)
                .addScalar("comreceive", Hibernate.STRING)
                .addScalar("littlecom", Hibernate.STRING)
                .list();
        
        for (Object[] B : TicketCommissionReceiveSum) {
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
            ar.setPayagent(util.ConvertString(B[5])== null ? "" : util.ConvertString(B[5]));
            ar.setRcagent(util.ConvertString(B[6])== null ? "" : util.ConvertString(B[6]));
            ar.setPayrefund(util.ConvertString(B[7])== null ? "" : util.ConvertString(B[7]));
            ar.setComreceive(util.ConvertString(B[8])== null ? "" : util.ConvertString(B[8]));
            ar.setLittlecom(util.ConvertString(B[9])== null ? "" : util.ConvertString(B[9]));
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
