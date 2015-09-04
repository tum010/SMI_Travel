/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.view.dao.ReceiptDao;
import com.smi.travel.datalayer.view.entity.ReceiptView;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public List getReceipt(String receiptId,int option) {
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
        String invdescTemp = "";
        ReceiptView receiptViewTemp  = new ReceiptView();
        for(Object[] T : QueryList){
            ReceiptView receiptView = new ReceiptView();
            receiptView.setId((("null".equals(String.valueOf(T[0])) ? "" : String.valueOf(T[0]))));
            receiptView.setRecto((("null".equals(String.valueOf(T[1])) ? "" : String.valueOf(T[1]))));
            receiptView.setRecadd((("null".equals(String.valueOf(T[2])) ? "" : String.valueOf(T[2]))));
            receiptView.setTel((("null".equals(String.valueOf(T[3])) ? "" : String.valueOf(T[3]))));
            receiptView.setFax((("null".equals(String.valueOf(T[4])) ? "" : String.valueOf(T[4]))));
            receiptView.setRecno((("null".equals(String.valueOf(T[5])) ? "" : String.valueOf(T[5]))));
            receiptView.setRecdate((("null".equals(String.valueOf(T[6])) ? "" : String.valueOf(T[6]))));
            receiptView.setPaidby((("null".equals(String.valueOf(T[7]))? "" : String.valueOf(T[7]))));
            receiptView.setDescription((("null".equals(String.valueOf(T[8])) ? "" : String.valueOf(T[8]))));
            receiptView.setNondescription((("null".equals(String.valueOf(T[9])) ? "" : String.valueOf(T[9]))));
            receiptView.setInvdesc((("null".equals(String.valueOf(T[10])) ? "" : String.valueOf(T[10]))));
            receiptView.setAmount((("0.00".equals(String.valueOf(T[11])) ? "" : String.valueOf(T[11]))));
            receiptView.setTotalamount((("0.00".equals(String.valueOf(T[12]))? "" : String.valueOf(T[12]))));
            receiptView.setCash((("0.00".equals(String.valueOf(T[13])) ? "" : String.valueOf(T[13]))));
            receiptView.setCashflag((("0.00".equals(String.valueOf(T[14])) ? "" : String.valueOf(T[14]))));
            receiptView.setTransfer((("0.00".equals(String.valueOf(T[15]))? "" : String.valueOf(T[15]))));
            receiptView.setTransferflag((("0.00".equals(String.valueOf(T[16])) ? "" : String.valueOf(T[16]))));
            receiptView.setTax((("0.00".equals(String.valueOf(T[17])) ? "" : String.valueOf(T[17]))));
            receiptView.setTaxflag((("0.00".equals(String.valueOf(T[18])) ? "" : String.valueOf(T[18]))));
            receiptView.setChqbank((("null".equals(String.valueOf(T[19])) ? "" : String.valueOf(T[19]))));
            receiptView.setChqno((("null".equals(String.valueOf(T[20])) ? "" : String.valueOf(T[20]))));
            receiptView.setChqdate((("null".equals(String.valueOf(T[21])) ? "" : String.valueOf(T[21]))));
            receiptView.setChqvalue((("0.00".equals(String.valueOf(T[22])) ? "" : String.valueOf(T[22]))));
            receiptView.setChqbankflag((("0.00".equals(String.valueOf(T[23]))? "" : String.valueOf(T[23]))));
            receiptView.setCredit((("null".equals(String.valueOf(T[24])) ? "" : String.valueOf(T[24]))));
            receiptView.setCreditflag((("0.00".equals(String.valueOf(T[25])) ? "" : String.valueOf(T[25]))));
            System.err.println("receiptView cash " +receiptView.getCash());
            String total = (receiptView.getTotalamount()).replaceAll("\\.", ",");
            String[] totals = total.split(",");
            int totalWord = 0;
            totalWord = Integer.parseInt(String.valueOf(totals[0]));
            receiptView.setTextmoney(utilityFunction.convert(totalWord)+" BAHT");

            if(option == 1){
                receiptView.setDescription(receiptView.getNondescription());
                System.out.println(" receiptView.getDes " +receiptView.getDescription());
                data.add(receiptView);
            }else if(option == 2 ){
                receiptView.setInvdesc("");
                System.out.println(" receiptView.getDes " +receiptView.getDescription());
                data.add(receiptView);
            }
            else if(option == 3 ){
                receiptView.setAmount(receiptView.getTotalamount());
                invdescTemp += receiptView.getInvdesc() + ",";
                receiptViewTemp = receiptView;
            }
            
           
        }
        if(option == 3){
            System.out.println(" invdesc " + invdescTemp);
            String[] invdescs = invdescTemp.split(",");
            String invdesc = invdescTemp;
            for (int j=0;j<invdescs.length;j++){
                for (int k=j+1;k<invdescs.length;k++){
                    if (k!=j && invdescs[k].equals(invdescs[j])){
                        invdesc = invdescs[k];
                    }
                }
            }
            receiptViewTemp.setDescription("TOUR" + " " + invdesc);
            receiptViewTemp.setInvdesc("");
            data.add(receiptViewTemp);
        }
        
        if(data.isEmpty()) {
            return null;
        }
        
        session.close();
        return data;
    }

    @Override
    public List getReceiptSummary(String receiptId, int option) {
        
        List data = new ArrayList();
        ReceiptView receiptView = new ReceiptView();
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("dd-MM-yyyy");
        receiptView.setSystemdate(String.valueOf(df.format(new Date())));
        receiptView.setUser("PJ-ADMINISTRATOR");
        receiptView.setFrom(String.valueOf(df.format(new Date())));
        receiptView.setTo(String.valueOf(df.format(new Date())));
        receiptView.setDepartment("OUTBOUND");
        
        receiptView.setRecdate(String.valueOf(df.format(new Date())));
        receiptView.setReceivedate(String.valueOf(df.format(new Date())));
        receiptView.setRecfrom("S00001");
        receiptView.setRecname("AAAAAAAAAAAAAAAAAAAAAA");
        receiptView.setRecdetail("TEST TEST");
        receiptView.setInvno("WN01010101");
        receiptView.setInvamount("100.01");
        receiptView.setDiff("200.02");
        receiptView.setRecamount("300.03");
        receiptView.setPayby("Customer");
        receiptView.setCash("100000");
        receiptView.setChq("100000");
        receiptView.setCreditcard("10000");
        receiptView.setBanktransfer("12000");
        receiptView.setWt("10000");
        receiptView.setCashminus("5000");
        for(int i= 0 ; i<30 ; i++){
            receiptView.setNo(String.valueOf(i));
            receiptView.setRecno("1509000"+i);
            data.add(receiptView);
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
