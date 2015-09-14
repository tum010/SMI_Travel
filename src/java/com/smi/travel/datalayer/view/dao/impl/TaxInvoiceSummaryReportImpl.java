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
        
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MMM-yyyy HH:mm:ss");         
        
        String departmentshow = "ALL";
        StringBuffer query = new StringBuffer(" SELECT * FROM `taxinvoice_summary` ");
        boolean haveCondition = false;
        if ((from != null) && (!"".equalsIgnoreCase(from))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" `taxinvoice_summary`.taxdate >= '" + from + "'");
            haveCondition = true;
        }
        if ((to != null) && (!"".equalsIgnoreCase(to))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" `taxinvoice_summary`.taxdate <= '" + to + "'");
            haveCondition = true;
        }
        if ((status != null) && (!"".equalsIgnoreCase(status))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" `taxinvoice_summary`.status = '" + status + "'");
            haveCondition = true;
        }
        if ((department != null) && (!"".equalsIgnoreCase(department))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" `taxinvoice_summary`.department = '" + department + "'");
            haveCondition = true;
            departmentshow = department;
        }

        List<Object[]> QueryList =  session.createSQLQuery(query.toString())
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
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("dd-MM-yyyy");
        for (Object[] B : QueryList) {
            TaxInvoiceSummaryReport taxInvoiceSummaryReport = new TaxInvoiceSummaryReport();
            taxInvoiceSummaryReport.setNo(String.valueOf(no));
            taxInvoiceSummaryReport.setId(util.ConvertString(B[0]));
            taxInvoiceSummaryReport.setTaxno(util.ConvertString(B[1]));
            taxInvoiceSummaryReport.setTaxdate((("null".equals(String.valueOf(B[2])) ? "" : String.valueOf(df.format(util.convertStringToDate(String.valueOf(B[2])))))));
            taxInvoiceSummaryReport.setTaxto(util.ConvertString(B[3]));
            taxInvoiceSummaryReport.setTaxname(util.ConvertString(B[4]));                           
            taxInvoiceSummaryReport.setStatus(util.ConvertString(B[11]));
            taxInvoiceSummaryReport.setDepartment(util.ConvertString(B[12]));
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
