/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;
import com.smi.travel.datalayer.report.model.InvoiceReport;
import com.smi.travel.datalayer.view.dao.InvoiceDao;
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
public class InvoiceImpl implements InvoiceDao{
    private SessionFactory sessionFactory;
    private UtilityFunction utilityFunction;

    @Override
    public InvoiceReport getInvoice() {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();  
        InvoiceReport invoice = new InvoiceReport();
        invoice.setAccname("TestMan");
        invoice.setAccno("11111111");
        invoice.setAcctype("Test");
        invoice.setAmount("1000000000");
        invoice.setBank("KBank");
        invoice.setBranch("Bangkapi");
        invoice.setDescription("test ภาษาไทย");
        invoice.setGross("999999");
        int a = 1999999999;
        invoice.setGrtotal("1999999999");
        invoice.setInvdate("01-12-15");
        invoice.setInvno("123456789");
        invoice.setInvto("Iconext Thailand co.ltd");
        invoice.setPayment("Mr. Test Man");
        invoice.setRefno("11111111");
        invoice.setStaff("Minions");
        invoice.setTotal("9999999999");
        invoice.setUser("Mike Jr. Robert");
        invoice.setVat("79999");
        invoice.setTextmoney(utilityFunction.convert(a)+" baht");
        session.close();
        this.sessionFactory.close();
        return invoice;
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
