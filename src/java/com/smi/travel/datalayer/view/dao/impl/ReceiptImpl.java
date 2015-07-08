/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.report.model.ReceiptEmail;
import com.smi.travel.datalayer.view.dao.ReceiptDao;
import com.smi.travel.util.UtilityFunction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author chonnasith
 */
public class ReceiptImpl implements ReceiptDao{
    private SessionFactory sessionFactory;
    private UtilityFunction utilityFunction;
    
    @Override
    public ReceiptEmail getReceipt() {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        ReceiptEmail receipt = new ReceiptEmail();
        
        receipt.setRecto("Jittima S");
        receipt.setRecadd("Addressssssssssssssssssssssssssssssssssssssssssssssss");
        receipt.setTel("0812345678");
        receipt.setFax("0212345678");
        receipt.setRecno("15070012");
        receipt.setRecdate("01-07-2015");
        receipt.setPaidby("Bank Transfer");
        receipt.setDescription("JR Area 5 DaysJR JR ");
        receipt.setAmount(util.ConvertString("5200"));
        receipt.setCashflag("");
        receipt.setCash("1000");
        receipt.setChqbankflag("");
        receipt.setChqbank("0");
        receipt.setCredit("3200");
        receipt.setCreditflag("");
        receipt.setTransfer("1000");
        receipt.setTransferflag("");
        receipt.setTax("7%");
        receipt.setTaxflag("");
        receipt.setTotal("5200");
        receipt.setChqno("12312314233");
        receipt.setChqdate("01-07-2015");
        int totalWord = 0;
        totalWord = Integer.parseInt(String.valueOf(receipt.getTotal()));
        receipt.setTextmoney(utilityFunction.convert(totalWord)+" baht");
        session.close();
        this.sessionFactory.close();
        return receipt;
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
