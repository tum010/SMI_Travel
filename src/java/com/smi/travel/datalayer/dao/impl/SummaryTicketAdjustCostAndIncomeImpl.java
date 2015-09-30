/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.SummaryTicketAdjustCostAndIncomeDao;
import com.smi.travel.datalayer.view.entity.SummaryTicketAdjustCostAndIncome;
import com.smi.travel.util.UtilityFunction;
import java.util.ArrayList;
import java.util.Date;
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
    @Override
    public List<SummaryTicketAdjustCostAndIncome> getSummaryTicketAdjustCostAndIncome(String reportType, String invoiceFromDate, String invoiceToDate, String issueFrom, String issueTo, String paymentType, String departmentt, String salebyUser, String termPayt) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        Date thisDate = new Date();
        List data = new ArrayList();
        String query = "";
        int AndQuery = 0;
        System.out.println("Term Pay : " + termPayt);
        if( invoiceFromDate == null  &&  invoiceToDate == null  && issueFrom == null && issueTo == null && paymentType == null && departmentt == null && salebyUser == null && termPayt == null){
            query = "select (case when (`fare`.`ticket_type` = 'B') then 'BSP' when (`fare`.`ticket_type` = 'A') then 'AGENT' when (`fare`.`ticket_type` = 'D') then 'DOMESTIC' when (`fare`.`ticket_type` = 'T') then 'TG' else `fare`.`ticket_type` end) AS `typepayment`,`fare`.`ticket_rounting` AS `typerounting`,count(`fare`.`ticket_no`) AS `pax`,substr(`fare`.`ticket_no`,1,3) AS `air`,(case when (`inv`.`inv_type` = 'T') then substr(`inv`.`inv_no`,1,5) else substr(`inv`.`inv_no`,1,6) end) AS `invno`,(select sum(`invd`.`cur_amount`) from `invoice_detail` `invd` where (`invd`.`invoice_id` = `inv`.`id`)) AS `costinv`,(case when (`fare`.`department` = 'wendy') then sum(`fare`.`inv_amount`) else NULL end) AS `invoicewendy`,(case when (`fare`.`department` = 'inbound') then sum(`fare`.`inv_amount`) else NULL end) AS `invoiceinbound`,(case when (`fare`.`department` = 'outbound') then sum(`fare`.`inv_amount`) else NULL end) AS `invoiceoutbound`,sum(ifnull(`fare`.`agent_commission`,0)) AS `discount`,(select sum(ifnull(`fare`.`agent_commission`,0)) from `ticket_fare_airline` `farec` where ((`farec`.`id` = `farec`.`id`) and (`farec`.`ticket_rounting` = 'C'))) AS `cancel`,0 AS `wait_pay`,sum(ifnull(`fare`.`diff_vat`,0)) AS `rcagcom`,sum(ifnull(`fare`.`over_commission`,0)) AS `over`,sum(ifnull(`fare`.`litter_commission`,0)) AS `litter`,sum(((((ifnull(`fare`.`inv_amount`,0) + ifnull(`fare`.`over_commission`,0)) - ifnull(`fare`.`litter_commission`,0)) + ifnull(`fare`.`agent_commission`,0)) - ifnull(`fare`.`diff_vat`,0))) AS `total_balance` from ((((`ticket_fare_airline` `fare` left join `ticket_fare_invoice` `finv` on((`finv`.`ticket_fare_id` = `fare`.`id`))) left join `invoice` `inv` on((`inv`.`id` = `finv`.`invoice_id`))) left join `m_accterm` `term` on((`term`.`id` = `inv`.`term_pay`))) left join `staff` `st` on((`st`.`name` = `fare`.`owner`))) " ; 
        }else{
            query = "select (case when (`fare`.`ticket_type` = 'B') then 'BSP' when (`fare`.`ticket_type` = 'A') then 'AGENT' when (`fare`.`ticket_type` = 'D') then 'DOMESTIC' when (`fare`.`ticket_type` = 'T') then 'TG' else `fare`.`ticket_type` end) AS `typepayment`,`fare`.`ticket_rounting` AS `typerounting`,count(`fare`.`ticket_no`) AS `pax`,substr(`fare`.`ticket_no`,1,3) AS `air`,(case when (`inv`.`inv_type` = 'T') then substr(`inv`.`inv_no`,1,5) else substr(`inv`.`inv_no`,1,6) end) AS `invno`,(select sum(`invd`.`cur_amount`) from `invoice_detail` `invd` where (`invd`.`invoice_id` = `inv`.`id`)) AS `costinv`,(case when (`fare`.`department` = 'wendy') then sum(`fare`.`inv_amount`) else NULL end) AS `invoicewendy`,(case when (`fare`.`department` = 'inbound') then sum(`fare`.`inv_amount`) else NULL end) AS `invoiceinbound`,(case when (`fare`.`department` = 'outbound') then sum(`fare`.`inv_amount`) else NULL end) AS `invoiceoutbound`,sum(ifnull(`fare`.`agent_commission`,0)) AS `discount`,(select sum(ifnull(`fare`.`agent_commission`,0)) from `ticket_fare_airline` `farec` where ((`farec`.`id` = `farec`.`id`) and (`farec`.`ticket_rounting` = 'C'))) AS `cancel`,0 AS `wait_pay`,sum(ifnull(`fare`.`diff_vat`,0)) AS `rcagcom`,sum(ifnull(`fare`.`over_commission`,0)) AS `over`,sum(ifnull(`fare`.`litter_commission`,0)) AS `litter`,sum(((((ifnull(`fare`.`inv_amount`,0) + ifnull(`fare`.`over_commission`,0)) - ifnull(`fare`.`litter_commission`,0)) + ifnull(`fare`.`agent_commission`,0)) - ifnull(`fare`.`diff_vat`,0))) AS `total_balance` from ((((`ticket_fare_airline` `fare` left join `ticket_fare_invoice` `finv` on((`finv`.`ticket_fare_id` = `fare`.`id`))) left join `invoice` `inv` on((`inv`.`id` = `finv`.`invoice_id`))) left join `m_accterm` `term` on((`term`.`id` = `inv`.`term_pay`))) left join `staff` `st` on((`st`.`name` = `fare`.`owner`))) where " ;
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
        session.close();
        this.sessionFactory.close();
        return data;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Transaction getTransaction() {
        return transaction;
    }
    
}
