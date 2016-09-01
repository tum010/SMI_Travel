/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.report.model.TaxInvoiceSummaryReport;
import com.smi.travel.datalayer.view.dao.TaxInvoiceSummaryReportDao;
import com.smi.travel.datalayer.view.entity.TaxInvoiceView;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author chonnasith
 */
public class TaxInvoiceSummaryReportImpl implements TaxInvoiceSummaryReportDao {
    private SessionFactory sessionFactory;
    private UtilityFunction utilityFunction;
    
    @Override
    public List getTaxInvoiceSummaryReport(String from, String to, String department, String status, String systemuser) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList<TaxInvoiceSummaryReport>();
        
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.US);        
        
        String departmentshow = "ALL";
        
        //query taxinvoice_view
        String query = "SELECT `tax`.`id` AS `id`, `tax`.`tax_no` AS `taxno`, `tax`.`tax_inv_date` AS `taxdate`, `tax`.`tax_inv_to` AS `taxto`, `tax`.`tax_inv_name` AS `taxname`, `tax`.`department` AS `department`, group_concat(`bt`.`name` SEPARATOR ',') AS `detail`, group_concat(`inv`.`inv_no` SEPARATOR ',') AS `invoiceno`, `GET_RECIPTDETAILFROMTAXINVOICE` (`tax`.`id`) AS `receiptno`, round( sum((( ifnull(`taxd`.`amount`, 0) * 100 ) / (100 + `taxd`.`vat`))), 2 ) AS `gross`, round( sum(( ifnull(`taxd`.`amount`, 0) - (( ifnull(`taxd`.`amount`, 0) * 100 ) / (100 + `taxd`.`vat`)))), 2 ) AS `vat`, round(sum(`taxd`.`amount`), 2) AS `amount`, ( CASE WHEN (`fi`.`name` = 'NORMAL') THEN '' WHEN (`fi`.`name` = 'VOID') THEN 'VOID' ELSE '' END ) AS `status` FROM ((((( `tax_invoice` `tax` LEFT JOIN `tax_invoice_detail` `taxd` ON (( `taxd`.`tax_invoice_id` = `tax`.`id` ))) LEFT JOIN `invoice_detail` `invd` ON (( `invd`.`id` = `taxd`.`invoice_detail_id` ))) LEFT JOIN `invoice` `inv` ON (( `inv`.`id` = `invd`.`invoice_id` ))) LEFT JOIN `m_billtype` `bt` ON (( `bt`.`id` = `taxd`.`item_type_id` ))) LEFT JOIN `m_finance_itemstatus` `fi` ON ((`fi`.`id` = `tax`.`status`))) subQuery GROUP BY `tax`.`id` ORDER BY `tax`.`tax_no`";
        String subQuery = "";
//        StringBuffer query = new StringBuffer(" SELECT `tax`.`id` AS `id`, `tax`.`tax_no` AS `taxno`, `tax`.`tax_inv_date` AS `taxdate`, `tax`.`tax_inv_to` AS `taxto`, `tax`.`tax_inv_name` AS `taxname`, `tax`.`department` AS `department`, group_concat(`bt`.`name` SEPARATOR ',') AS `detail`, group_concat(`inv`.`inv_no` SEPARATOR ',') AS `invoiceno`, `GET_RECIPTDETAILFROMTAXINVOICE` (`tax`.`id`) AS `receiptno`, round( sum((( ifnull(`taxd`.`amount`, 0) * 100 ) / (100 + `taxd`.`vat`))), 2 ) AS `gross`, round( sum(( ifnull(`taxd`.`amount`, 0) - (( ifnull(`taxd`.`amount`, 0) * 100 ) / (100 + `taxd`.`vat`)))), 2 ) AS `vat`, round(sum(`taxd`.`amount`), 2) AS `amount`, ( CASE WHEN (`fi`.`name` = 'NORMAL') THEN '' WHEN (`fi`.`name` = 'VOID') THEN 'VOID' ELSE '' END ) AS `status` FROM ((((( `tax_invoice` `tax` LEFT JOIN `tax_invoice_detail` `taxd` ON (( `taxd`.`tax_invoice_id` = `tax`.`id` ))) LEFT JOIN `invoice_detail` `invd` ON (( `invd`.`id` = `taxd`.`invoice_detail_id` ))) LEFT JOIN `invoice` `inv` ON (( `inv`.`id` = `invd`.`invoice_id` ))) LEFT JOIN `m_billtype` `bt` ON (( `bt`.`id` = `taxd`.`item_type_id` ))) LEFT JOIN `m_finance_itemstatus` `fi` ON ((`fi`.`id` = `tax`.`status`))) GROUP BY `tax`.`id` subQuery ORDER BY `tax`.`department`, `tax`.`tax_no` ");
        boolean haveCondition = false;
        if ((from != null) && (!"".equalsIgnoreCase(from))) {
            subQuery += (haveCondition ? " and" : " where");
            subQuery += (" `tax`.`tax_inv_date` >= '" + util.covertStringDateToFormatYMD(from) + "'");
            haveCondition = true;
        }
        if ((to != null) && (!"".equalsIgnoreCase(to))) {
            subQuery += (haveCondition ? " and" : " where");
            subQuery +=(" `tax`.`tax_inv_date` <= '" + util.covertStringDateToFormatYMD(to) + "'");
            haveCondition = true;
        }
        if ((status != null) && (!"".equalsIgnoreCase(status))) {
            if("NORMAL".equalsIgnoreCase(status)){status = "";}
            subQuery += (haveCondition ? " and" : " where");
            subQuery += (" ( CASE WHEN (`fi`.`name` = 'NORMAL') THEN '' WHEN (`fi`.`name` = 'VOID') THEN 'VOID' ELSE '' END ) = '" + status + "'");
            haveCondition = true;
        }
        if ((department != null) && (!"".equalsIgnoreCase(department))) {
            subQuery += (haveCondition ? " and" : " where");
            if(department.indexOf(",") == -1){
                subQuery += (" `tax`.`department` = '" + department + "'");
                departmentshow = department;
            }else{
                String[] departmentTemp = department.split(",");
                subQuery += (" `tax`.`department` in ('" + departmentTemp[0] + "','" + departmentTemp[1] + "') ");
                departmentshow = "WO";
            }           
            haveCondition = true;          
        } else {
            subQuery += (haveCondition ? " and" : " where");
            subQuery += (" `tax`.`department` in ('Wendy','Outbound','Inbound') ");
        }
        
//        if("WO".equalsIgnoreCase(departmentshow) || "ALL".equalsIgnoreCase(departmentshow)){
//            subQuery += (" order by taxinvoice_summary.taxno ");
//        }
        
        query = query.replace("subQuery", subQuery);

        List<Object[]> QueryList =  session.createSQLQuery(query)
                .addScalar("id",Hibernate.STRING)
                .addScalar("taxno",Hibernate.STRING)
                .addScalar("taxdate",Hibernate.STRING)
                .addScalar("taxto",Hibernate.STRING)
                .addScalar("taxname",Hibernate.STRING)
                .addScalar("detail",Hibernate.STRING)
                .addScalar("invoiceno",Hibernate.STRING)
                .addScalar("receiptno",Hibernate.STRING)
                .addScalar("gross",Hibernate.STRING)
                .addScalar("vat",Hibernate.STRING)
                .addScalar("amount",Hibernate.STRING)
                .addScalar("status",Hibernate.STRING)
                .addScalar("department",Hibernate.STRING)
                .list();
        
        int no = 1;
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        String[] departmentList = {"Wendy,Outbound","Inbound"};
        for(String departmentListValue : departmentList){
            for (Object[] B : QueryList) {
                String departmentTemp = util.ConvertString(B[12]); 
//                System.out.println(departmentListValue + " : " + departmentTemp);
                if(departmentListValue.indexOf(departmentTemp) != -1){
                    TaxInvoiceSummaryReport taxInvoiceSummaryReport = new TaxInvoiceSummaryReport();
                    taxInvoiceSummaryReport.setNo(String.valueOf(no));
                    taxInvoiceSummaryReport.setId(util.ConvertString(B[0]));
                    taxInvoiceSummaryReport.setTaxno(util.ConvertString(B[1]));
                    taxInvoiceSummaryReport.setTaxdate((("null".equals(String.valueOf(B[2])) ? "" : String.valueOf(df.format(util.convertStringToDate(String.valueOf(B[2])))))));
                    taxInvoiceSummaryReport.setTaxto(util.ConvertString(B[3]));
                    taxInvoiceSummaryReport.setTaxname(util.ConvertString(B[4]));                           
                    taxInvoiceSummaryReport.setStatus(util.ConvertString(B[11]));

//                    System.out.println("departmentTemp : "+departmentTemp);
                    if(("Wendy".equalsIgnoreCase(departmentTemp) || "Outbound".equalsIgnoreCase(departmentTemp))&&(("".equalsIgnoreCase(department)||("Wendy,Outbound".equalsIgnoreCase(department))))){
                        taxInvoiceSummaryReport.setDepartment("Wendy + Outbound");

                    } else {
                        taxInvoiceSummaryReport.setDepartment(departmentTemp);
                    }            
        //            taxInvoiceSummaryReport.setDepartment(util.ConvertString(B[12]));
        //            taxInvoiceSummaryReport.setDepartment("WO".equalsIgnoreCase(departmentshow) ? "Wendy + Outbound" : util.ConvertString(B[12]));
                    taxInvoiceSummaryReport.setSystemdate(String.valueOf(dateformat.format(new Date())));
                    taxInvoiceSummaryReport.setFrom(!"".equalsIgnoreCase(from) ? String.valueOf(df.format(util.convertStringToDate(from))) : "");
                    taxInvoiceSummaryReport.setTo(!"".equalsIgnoreCase(to) ? String.valueOf(df.format(util.convertStringToDate(to))) : "");
                    taxInvoiceSummaryReport.setUser(systemuser);
                    taxInvoiceSummaryReport.setDepartmentshow(departmentshow);

                    if(!"".equalsIgnoreCase(util.ConvertString(B[8]))){
                        taxInvoiceSummaryReport.setGross(util.ConvertString(B[8]));
                    } else {
                        taxInvoiceSummaryReport.setGross("0");
                    }
                    if(!"".equalsIgnoreCase(util.ConvertString(B[9]))){
                        taxInvoiceSummaryReport.setVat(util.ConvertString(B[9]));
                    } else {
                        taxInvoiceSummaryReport.setVat("0");
                    }
                    if(!"".equalsIgnoreCase(util.ConvertString(B[10]))){
                        taxInvoiceSummaryReport.setAmount(util.ConvertString(B[10]));
                    } else {
                        taxInvoiceSummaryReport.setAmount("0");
                    }

                    String invoiceNo = checkInvoiceNo((util.ConvertString(B[6])));
                    taxInvoiceSummaryReport.setInvoiceno(invoiceNo);
                    String receipt = checkReceiptNo((util.ConvertString(B[7])));
                    taxInvoiceSummaryReport.setReceiptno(receipt);
                    String taxDetail = checkTaxDetail(util.ConvertString(B[5]));
                    taxInvoiceSummaryReport.setTaxdetail(taxDetail);      

                    data.add(taxInvoiceSummaryReport);
                    no++;
                }
            }
        }
        
        return data;
    }
    
    private String checkInvoiceNo(String invoiceNoList) {
        String result = "";
        String[] invNoList = invoiceNoList.split("\\,");
        List<String> invNoChkList = new ArrayList<String>();
        for(int i=0;i<invNoList.length;i++){
            String invNo1 = invNoList[i];
            int match = 0;
            if(!invNoChkList.isEmpty()){
                for(int j=0;j<invNoChkList.size();j++){
                    String invNo2 = invNoChkList.get(j);
                    if(invNo1.equalsIgnoreCase(invNo2)){
                        match++;
                        j = invNoChkList.size();
                    }
                }
                if(match == 0){
                    result += "\n";
                    invNoChkList.add(invNo1);
                    result += invNo1;
                }
            } else {
                invNoChkList.add(invNo1);
                result += invNo1;
            }           
        }
        
        return result;
    }

    private String checkReceiptNo(String receiptNoList) {
        String result = "";
        String[] recNoList = receiptNoList.split("\\,");
        List<String> recNoChkList = new ArrayList<String>();
        for(int i=0;i<recNoList.length;i++){
            String recNo1 = recNoList[i];
            int match = 0;
            if(!recNoChkList.isEmpty()){
                for(int j=0;j<recNoChkList.size();j++){
                    String recNo2 = recNoChkList.get(j);
                    if(recNo1.equalsIgnoreCase(recNo2)){
                        match++;
                        j = recNoChkList.size();
                    }
                }
                if(match == 0){
                    result += "\n";
                    recNoChkList.add(recNo1);
                    result += recNo1;
                }
            } else {
                recNoChkList.add(recNo1);
                result += recNo1;
            }           
        }
        
        return result;
    }
    
    private String checkTaxDetail(String taxDetailList) {
        String result = "";
        String[] taxDeList = taxDetailList.split("\\,");
        List<String> taxDeChkList = new ArrayList<String>();
        for(int i=0;i<taxDeList.length;i++){
            String detail11 = taxDeList[i];
            int match = 0;
            if(!taxDeChkList.isEmpty()){
                for(int j=0;j<taxDeChkList.size();j++){
                    String detail2 = taxDeChkList.get(j);
                    if(detail11.equalsIgnoreCase(detail2)){
                        match++;
                        j = taxDeChkList.size();
                    }
                }
                if(match == 0){
                    result += ",";
                    taxDeChkList.add(detail11);
                    result += detail11;
                }
            } else {
                taxDeChkList.add(detail11);
                result += detail11;
            }           
        }
        
        return result;
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
