/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.report.model.ReceiptEmail;
import com.smi.travel.datalayer.view.dao.ReceiptEmailDao;
import com.smi.travel.util.UtilityFunction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Jittima
 */
public class ReceiptEmailImpl implements ReceiptEmailDao{
    private SessionFactory sessionFactory;
    private UtilityFunction utilityFunction;
    
    
    @Override
    public ReceiptEmail getReceiptEmail() {
        Session session = this.sessionFactory.openSession();
       
        ReceiptEmail receiptEmail = new ReceiptEmail();
        
        receiptEmail.setRecto("Jittima S");
        receiptEmail.setRecadd("Addressssssssssssssssssssssssssssssssssssssssssssssss");
        receiptEmail.setTel("0812345678");
        receiptEmail.setFax("0212345678");
        receiptEmail.setRecno("15070012");
        receiptEmail.setRecdate("01-07-2015");
        receiptEmail.setPaidby("Bank Transfer");
        receiptEmail.setDescription("Hello World Hello World Hello World Hello World Hello World Hello World "
                + "Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World "
                + "Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World "
                + "Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World "
                + "Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World "
                + "Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World "
                + "Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World "
                + "Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World "
                + "Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World "
                + "Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World "
                + "Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World "
                + "Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World "
                + "Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World "
                + "Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World "
                + "Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World "
                + "Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World "
                + "Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World "
                + "Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World "
                + "Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World "
                + "Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World "
                + "Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World "
                + "Hello World Hello World Hello World Hello World Hello World Hello World");
        receiptEmail.setAmount("5200");
        receiptEmail.setCashflag("");
        receiptEmail.setCash("1000");
        receiptEmail.setChqbankflag("");
        receiptEmail.setChqbank("0");
        receiptEmail.setCredit("3200");
        receiptEmail.setCreditflag("");
        receiptEmail.setTransfer("1000");
        receiptEmail.setTransferflag("");
        receiptEmail.setTax("7%");
        receiptEmail.setTaxflag("");
        receiptEmail.setTotal(5200);
        receiptEmail.setChqno("12312314233");
        receiptEmail.setChqdate("01-07-2015");
        receiptEmail.setTextmoney(utilityFunction.convert(receiptEmail.getTotal())+" baht");
        session.close();
        this.sessionFactory.close();
        return receiptEmail;
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
