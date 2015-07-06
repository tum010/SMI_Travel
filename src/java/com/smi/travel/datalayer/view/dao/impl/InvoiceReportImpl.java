/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;
import com.smi.travel.datalayer.report.model.InvoiceReport;
import com.smi.travel.datalayer.view.dao.InvoiceReportDao;
import com.smi.travel.util.UtilityFunction;
import java.text.SimpleDateFormat;
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
public class InvoiceReportImpl implements InvoiceReportDao{
    private SessionFactory sessionFactory;
     private UtilityFunction utilityFunction;

    @Override
    public InvoiceReport getInvoiceReport() {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();  
        InvoiceReport invoiceReport = new InvoiceReport();
        invoiceReport.setAccname("TestMan");
        invoiceReport.setAccno("11111111");
        invoiceReport.setAcctype("Test");
        invoiceReport.setAmount("1000000000");
        invoiceReport.setBank("KBank");
        invoiceReport.setBranch("Bangkapi");
        invoiceReport.setDescription("test ภาษาไทย");
        invoiceReport.setGross("999999");
        int a = 1999999999;
        invoiceReport.setGrtotal("1999999999");
        invoiceReport.setInvdate("01-12-15");
        invoiceReport.setInvno("123456789");
        invoiceReport.setInvto("Iconext Thailand co.ltd");
        invoiceReport.setPayment("Mr. Test Man");
        invoiceReport.setRefno("11111111");
        invoiceReport.setStaff("Minions");
        invoiceReport.setTotal("9999999999");
        invoiceReport.setUser("Mike Jr. Robert");
        invoiceReport.setVat("79999");
        invoiceReport.setTextmoney(utilityFunction.convert(a)+" baht");
        session.close();
        this.sessionFactory.close();
        return invoiceReport;
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
