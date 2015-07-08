/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;
import com.smi.travel.datalayer.report.model.InvoiceReport;
import com.smi.travel.datalayer.view.dao.InvoiceEmailDao;
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
public class InvoiceEmailImpl implements InvoiceEmailDao{
    private SessionFactory sessionFactory;
    private UtilityFunction utilityFunction;

    @Override
    public InvoiceReport getInvoiceEmail() {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();  
        InvoiceReport invoiceEmail = new InvoiceReport();
        invoiceEmail.setAccname("TestMan");
        invoiceEmail.setAccno("11111111");
        invoiceEmail.setAcctype("Test");
        invoiceEmail.setAmount("1000000000");
        invoiceEmail.setBank("KBank");
        invoiceEmail.setBranch("Bangkapi");
        invoiceEmail.setDescription("test ภาษาไทย");
        invoiceEmail.setGross("999999");
        int a = 1999999999;
        invoiceEmail.setGrtotal("1999999999");
        invoiceEmail.setInvdate("01-12-15");
        invoiceEmail.setInvno("123456789");
        invoiceEmail.setInvto("Iconext Thailand co.ltd");
        invoiceEmail.setPayment("Mr. Test Man");
        invoiceEmail.setRefno("11111111");
        invoiceEmail.setStaff("Minions");
        invoiceEmail.setTotal("9999999999");
        invoiceEmail.setUser("Mike Jr. Robert");
        invoiceEmail.setVat("79999");
        invoiceEmail.setTextmoney(utilityFunction.convert(a)+" baht");
        session.close();
        this.sessionFactory.close();
        return invoiceEmail;
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
