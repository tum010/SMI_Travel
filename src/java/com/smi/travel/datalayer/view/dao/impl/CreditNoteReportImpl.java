/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.report.model.CreditNoteReport;
import com.smi.travel.datalayer.view.dao.CreditNoteReportDao;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
 * @author Surachai
 */
public class CreditNoteReportImpl implements  CreditNoteReportDao{
    private SessionFactory sessionFactory;
    private UtilityFunction utilityFunction;
    
    @Override
    public List getCreditNoteReport(String creditId) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();  
        List data = new ArrayList();
        DecimalFormat df = new DecimalFormat("###,###.00");
        BigDecimal Subtotal = new BigDecimal(0);
        BigDecimal Grandtotal = new BigDecimal(0);
        BigDecimal Difsubtotal = new BigDecimal(0);
        BigDecimal sumvat = new BigDecimal(0);
        BigDecimal Realsubtotal = new BigDecimal(0);
        BigDecimal percent = new BigDecimal(100);        
            
        List<Object[]> QueryCNList = session.createSQLQuery("SELECT * FROM `creditnote_view` where id= " + creditId)
                 .addScalar("customer", Hibernate.STRING)
                 .addScalar("address", Hibernate.STRING)
                 .addScalar("cnno", Hibernate.STRING)
                 .addScalar("cndate", Hibernate.DATE)
                 .addScalar("description", Hibernate.STRING)
                 .addScalar("amount", Hibernate.BIG_DECIMAL)   
                 .addScalar("remark", Hibernate.STRING)
                 .addScalar("user", Hibernate.STRING)
                 .addScalar("vat", Hibernate.BIG_DECIMAL)
                 .addScalar("realamount", Hibernate.BIG_DECIMAL)
                 .addScalar("realvat", Hibernate.BIG_DECIMAL)
                 .list();
        for (Object[] B : QueryCNList) {
            CreditNoteReport cn = new CreditNoteReport();
            cn.setCustomer(util.ConvertString(B[0]));
            cn.setAddress(util.ConvertString(B[1]));
            cn.setCnno(util.ConvertString(B[2]));
            if(B[3] != null){
                cn.setCndate(new SimpleDateFormat("dd-MM-yyyy", new Locale("us", "us")).format((Date)B[3]));
            }else{
                cn.setCndate("");
            }
            cn.setDescription(util.ConvertString(B[4]));
            cn.setAmount(util.setFormatMoney(B[5]));
            BigDecimal amount = (BigDecimal) B[5] == null ? new BigDecimal(0):(BigDecimal) B[5];
            Subtotal= Subtotal.add(amount);
            Grandtotal = Grandtotal.add((BigDecimal) B[9] == null ? new BigDecimal(0):(BigDecimal) B[9]);
            BigDecimal realamount = (BigDecimal) B[9] == null ? new BigDecimal(0):(BigDecimal) B[9];
            BigDecimal vat = (BigDecimal) B[8] == null ? new BigDecimal(0):(BigDecimal) B[8];
            //
            BigDecimal subtotal = realamount.multiply(percent).divide(percent.add((BigDecimal) B[10]),4,RoundingMode.HALF_UP);
            Difsubtotal = Difsubtotal.add(subtotal);
            sumvat = sumvat.add(vat);
            System.out.println("amount : "+amount);
            System.out.println("subtotal : "+subtotal);
            BigDecimal RealTotal = amount.subtract(subtotal);
            System.out.println("RealTotal : "+RealTotal);
            Realsubtotal = Realsubtotal.add(RealTotal);
            cn.setRemark(util.ConvertString(B[6]));
            cn.setUser(util.ConvertString(B[7]));
            data.add(cn);
        }
        
        for(int i =0;i<data.size();i++){
            CreditNoteReport re = (CreditNoteReport) data.get(i);
            re.setSubtotal(util.setFormatMoney(Subtotal));
            re.setGrandtotal(util.setFormatMoney(Grandtotal));
            re.setDifsubtotal(util.setFormatMoney(Difsubtotal));
            re.setVat(util.setFormatMoney(sumvat));
            re.setReadsubtotal(util.setFormatMoney(Realsubtotal));
            String total = re.getGrandtotal().replaceAll(",", "");
            total = total.replaceAll("\\.", ",");
            String[] totals = total.split(",");
            int totalWord = 0;
            if(!"".equalsIgnoreCase(totals[0])){
                totalWord = Integer.parseInt(String.valueOf(totals[0]));
            }  
            re.setTextamount(utilityFunction.convert(totalWord)+" BATH");
            data.set(i, re);
        }
        
        
        
        return data;
    }
    

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
}
