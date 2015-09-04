/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.view.dao.TaxInvoiceSummaryReportDao;
import com.smi.travel.datalayer.report.model.TaxInvoiceSummaryReport;
import com.smi.travel.datalayer.view.entity.TaxInvoiceView;
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
public class TaxInvoiceSummaryReportImpl implements TaxInvoiceSummaryReportDao {
    private SessionFactory sessionFactory;
    private UtilityFunction utilityFunction;
    
    @Override
    public List getTaxInvoiceSummaryReport() {
        List data = new ArrayList<TaxInvoiceSummaryReport>();
        BigDecimal a = new BigDecimal(1000);
        DecimalFormat df = new DecimalFormat("###,###.00");
        for(int i=0;i<25;i++){
            TaxInvoiceSummaryReport taxInvoiceSummaryReport = new TaxInvoiceSummaryReport();
            taxInvoiceSummaryReport.setDepartment("Inbound");
            taxInvoiceSummaryReport.setNo(String.valueOf(i));
            taxInvoiceSummaryReport.setAmount("1000");
            taxInvoiceSummaryReport.setGross("1000");
            taxInvoiceSummaryReport.setVat("10");
            data.add(taxInvoiceSummaryReport);
        }
        for(int i=0;i<25;i++){
            TaxInvoiceSummaryReport taxInvoiceSummaryReport = new TaxInvoiceSummaryReport();
            taxInvoiceSummaryReport.setDepartment("Outbound");
            taxInvoiceSummaryReport.setNo(String.valueOf(i));
            taxInvoiceSummaryReport.setAmount("1000");
            data.add(taxInvoiceSummaryReport);
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
    
}
