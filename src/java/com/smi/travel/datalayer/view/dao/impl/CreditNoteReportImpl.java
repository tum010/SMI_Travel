/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.report.model.CreditNoteReport;
import com.smi.travel.datalayer.view.dao.CreditNoteReportDao;
import com.smi.travel.util.UtilityFunction;
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
        
        List<Object[]> QueryCNList = session.createSQLQuery("SELECT * FROM `creditnote_view` where id= " + creditId)
                 .addScalar("customer", Hibernate.STRING)
                 .addScalar("address", Hibernate.STRING)
                 .addScalar("cnno", Hibernate.STRING)
                 .addScalar("cndate", Hibernate.DATE)
                 .addScalar("description", Hibernate.STRING)
                 .addScalar("amount", Hibernate.BIG_DECIMAL)
                 .addScalar("subtotal", Hibernate.BIG_DECIMAL)
                 .addScalar("grandtotal", Hibernate.BIG_DECIMAL)
                 .addScalar("difsubtotal", Hibernate.BIG_DECIMAL)
                 .addScalar("vat", Hibernate.BIG_DECIMAL)
                 .addScalar("realsubtotal", Hibernate.BIG_DECIMAL)
                 .addScalar("remark", Hibernate.STRING)
                 .addScalar("user", Hibernate.STRING)
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
            cn.setSubtotal(util.setFormatMoney(B[6]));
            cn.setGrandtotal(util.setFormatMoney(B[7]));
            cn.setDifsubtotal(util.setFormatMoney(B[8]));
            cn.setVat(util.setFormatMoney(B[9]));
            cn.setReadsubtotal(util.setFormatMoney(B[10]));
            cn.setRemark(util.ConvertString(B[11]));
            cn.setUser(util.ConvertString(B[12]));
            
            String total = cn.getGrandtotal().replaceAll(",", "");
            total = total.replaceAll("\\.", ",");
            String[] totals = total.split(",");
            int totalWord = 0;
            totalWord = Integer.parseInt(String.valueOf(totals[0]));
            cn.setTextamount(utilityFunction.convert(totalWord)+" baht");
            
            data.add(cn);
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
