/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.view.dao.ReceiptDao;
import com.smi.travel.datalayer.view.entity.ReceiptView;
import com.smi.travel.util.UtilityFunction;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Hibernate;
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
    public List getReceipt(String receiptId,String receiptNo,int option) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List<Object[]> QueryList =  session.createSQLQuery("SELECT * FROM `receipt_view` where `receipt_view`.id =  "+receiptId)
                .addScalar("id",Hibernate.STRING)
                .addScalar("recto",Hibernate.STRING)
                .addScalar("recadd",Hibernate.STRING)
                .addScalar("tel",Hibernate.STRING)
                .addScalar("fax",Hibernate.STRING)
                .addScalar("recno",Hibernate.STRING)
                .addScalar("recdate",Hibernate.STRING)
                .addScalar("paidby",Hibernate.STRING)
                .addScalar("description",Hibernate.STRING)
                .addScalar("non_description",Hibernate.STRING)
                .addScalar("invdesc",Hibernate.STRING)
                .addScalar("amount",Hibernate.STRING)
                .addScalar("totalamount",Hibernate.STRING)
                .addScalar("cash",Hibernate.STRING)
                .addScalar("cashflag",Hibernate.STRING)
                .addScalar("transfer",Hibernate.STRING)
                .addScalar("transferflag",Hibernate.STRING)
                .addScalar("tax",Hibernate.STRING)
                .addScalar("taxflag",Hibernate.STRING)
                .addScalar("chqbank",Hibernate.STRING)
                .addScalar("chqno",Hibernate.STRING)
                .addScalar("chqdate",Hibernate.STRING)
                .addScalar("chqvalue",Hibernate.STRING)
                .addScalar("chqflag",Hibernate.STRING)
                .addScalar("credit",Hibernate.STRING)
                .addScalar("creditflag",Hibernate.STRING)
                .list();
        
        List data = new ArrayList();
        for(Object[] T : QueryList){
            ReceiptView receiptView = new ReceiptView();
            receiptView.setId(((String.valueOf(T[0]) == "null" ? "" : String.valueOf(T[0]))));
            receiptView.setRecto(((String.valueOf(T[1]) == "null" ? "" : String.valueOf(T[1]))));
            receiptView.setRecadd(((String.valueOf(T[2]) == "null" ? "" : String.valueOf(T[2]))));
            receiptView.setTel(((String.valueOf(T[3]) == "null" ? "" : String.valueOf(T[3]))));
            receiptView.setFax(((String.valueOf(T[4]) == "null" ? "" : String.valueOf(T[4]))));
            receiptView.setRecno(((String.valueOf(T[5]) == "null" ? "" : String.valueOf(T[5]))));
            receiptView.setRecdate(((String.valueOf(T[6]) == "null" ? "" : String.valueOf(T[6]))));
            receiptView.setPaidby(((String.valueOf(T[7]) == "null" ? "" : String.valueOf(T[7]))));
            receiptView.setDescription(((String.valueOf(T[8]) == "null" ? "" : String.valueOf(T[8]))));
            receiptView.setNondescription(((String.valueOf(T[9]) == "null" ? "" : String.valueOf(T[9]))));
            receiptView.setInvdesc(((String.valueOf(T[10]) == "null" ? "" : String.valueOf(T[10]))));
            receiptView.setAmount(((String.valueOf(T[11]) == "0" ? "" : String.valueOf(T[11]))));
            receiptView.setTotalamount(((String.valueOf(T[12]) == "0" ? "" : String.valueOf(T[12]))));
            receiptView.setCash(((String.valueOf(T[13]) == "0" ? "" : String.valueOf(T[13]))));
            receiptView.setCashflag(((String.valueOf(T[14]) == "0" ? "" : String.valueOf(T[14]))));
            receiptView.setTransfer(((String.valueOf(T[15]) == "0" ? "" : String.valueOf(T[15]))));
            receiptView.setTransferflag(((String.valueOf(T[16]) == "0" ? "" : String.valueOf(T[16]))));
            receiptView.setTax(((String.valueOf(T[17]) == "0" ? "" : String.valueOf(T[17]))));
            receiptView.setTaxflag(((String.valueOf(T[18]) == "0" ? "" : String.valueOf(T[18]))));
            receiptView.setChqbank(((String.valueOf(T[19]) == "null" ? "" : String.valueOf(T[19]))));
            receiptView.setChqno(((String.valueOf(T[20]) == "null" ? "" : String.valueOf(T[20]))));
            receiptView.setChqdate(((String.valueOf(T[21]) == "null" ? "" : String.valueOf(T[21]))));
            receiptView.setChqvalue(((String.valueOf(T[22]) == "null" ? "" : String.valueOf(T[22]))));
            receiptView.setChqbankflag(((String.valueOf(T[23]) == "0" ? "" : String.valueOf(T[23]))));
            receiptView.setCredit(((String.valueOf(T[24]) == "null" ? "" : String.valueOf(T[24]))));
            receiptView.setCreditflag(((String.valueOf(T[25]) == "0" ? "" : String.valueOf(T[25]))));
            
            int totalWord = 0;
            totalWord = Integer.parseInt(String.valueOf("24710"));
            receiptView.setTextmoney(utilityFunction.convert(totalWord)+" baht");

            if(option == 1 ){
                receiptView.setDescription(receiptView.getNondescription());
            }else if(option == 2 ){
                receiptView.setInvdesc("");
            }
//            else if(option == 3 ){
//                receiptView.setAmount(receiptView.getTotalamount());
//                receiptView.setDescription(receiptView.getDescription() + " " + receiptView.getInvdesc());
//                receiptView.setInvdesc("");
//            }
            
            data.add(receiptView);  
        }
        if(option == 3 ){
            ReceiptView receiptView = new ReceiptView();
            String desc = "" ;
            String invdesc = "";
            for(Object[] T : QueryList){
                receiptView.setId(((String.valueOf(T[0]) == "null" ? "" : String.valueOf(T[0]))));
                receiptView.setRecto(((String.valueOf(T[1]) == "null" ? "" : String.valueOf(T[1]))));
                receiptView.setRecadd(((String.valueOf(T[2]) == "null" ? "" : String.valueOf(T[2]))));
                receiptView.setTel(((String.valueOf(T[3]) == "null" ? "" : String.valueOf(T[3]))));
                receiptView.setFax(((String.valueOf(T[4]) == "null" ? "" : String.valueOf(T[4]))));
                receiptView.setRecno(((String.valueOf(T[5]) == "null" ? "" : String.valueOf(T[5]))));
                receiptView.setRecdate(((String.valueOf(T[6]) == "null" ? "" : String.valueOf(T[6]))));
                receiptView.setPaidby(((String.valueOf(T[7]) == "null" ? "" : String.valueOf(T[7]))));
                receiptView.setDescription(((String.valueOf(T[8]) == "null" ? "" : String.valueOf(T[8]))));
                receiptView.setNondescription(((String.valueOf(T[9]) == "null" ? "" : String.valueOf(T[9]))));
                receiptView.setInvdesc(((String.valueOf(T[10]) == "null" ? "" : String.valueOf(T[10]))));
                receiptView.setAmount(((String.valueOf(T[11]) == "0" ? "" : String.valueOf(T[11]))));
                receiptView.setTotalamount(((String.valueOf(T[12]) == "0" ? "" : String.valueOf(T[12]))));
                receiptView.setCash(((String.valueOf(T[13]) == "0" ? "" : String.valueOf(T[13]))));
                receiptView.setCashflag(((String.valueOf(T[14]) == "0" ? "" : String.valueOf(T[14]))));
                receiptView.setTransfer(((String.valueOf(T[15]) == "0" ? "" : String.valueOf(T[15]))));
                receiptView.setTransferflag(((String.valueOf(T[16]) == "0" ? "" : String.valueOf(T[16]))));
                receiptView.setTax(((String.valueOf(T[17]) == "0" ? "" : String.valueOf(T[17]))));
                receiptView.setTaxflag(((String.valueOf(T[18]) == "0" ? "" : String.valueOf(T[18]))));
                receiptView.setChqbank(((String.valueOf(T[19]) == "null" ? "" : String.valueOf(T[19]))));
                receiptView.setChqno(((String.valueOf(T[20]) == "null" ? "" : String.valueOf(T[20]))));
                receiptView.setChqdate(((String.valueOf(T[21]) == "null" ? "" : String.valueOf(T[21]))));
                receiptView.setChqvalue(((String.valueOf(T[22]) == "null" ? "" : String.valueOf(T[22]))));
                receiptView.setChqbankflag(((String.valueOf(T[23]) == "0" ? "" : String.valueOf(T[23]))));
                receiptView.setCredit(((String.valueOf(T[24]) == "null" ? "" : String.valueOf(T[24]))));
                receiptView.setCreditflag(((String.valueOf(T[25]) == "0" ? "" : String.valueOf(T[25]))));

                int totalWord = 0;
                totalWord = Integer.parseInt(String.valueOf("24710"));
                receiptView.setTextmoney(utilityFunction.convert(totalWord)+" baht");

                receiptView.setAmount(receiptView.getTotalamount());
                
                desc += receiptView.getDescription() + " ";
                invdesc += receiptView.getInvdesc() + " ";
                receiptView.setInvdesc("");
            }
            receiptView.setInvdesc("");
            receiptView.setDescription(desc + " " + invdesc);
            data.add(receiptView); 
        }
        System.err.println(" ReceiptViewList size " + data.size());
                
        if(data.isEmpty()) {
            return null;
        }
        
        session.close();
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
