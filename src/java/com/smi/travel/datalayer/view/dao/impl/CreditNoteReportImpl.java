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
                 .addScalar("cddate", Hibernate.DATE)
                 .addScalar("description", Hibernate.STRING)
                 .addScalar("amount", Hibernate.BIG_DECIMAL)
                 .addScalar("subtotal", Hibernate.BIG_DECIMAL)
                 .addScalar("grandtotal", Hibernate.BIG_DECIMAL)
                 .addScalar("difsubtotal", Hibernate.BIG_DECIMAL)
                 .addScalar("vat", Hibernate.BIG_DECIMAL)
                 .addScalar("realsubtotal", Hibernate.BIG_DECIMAL)
                 .addScalar("remark", Hibernate.STRING)
                 .list();
        for (Object[] B : QueryCNList) {
            CreditNoteReport cn = new CreditNoteReport();
            cn.setCustomer(util.ConvertString(B[0]));
            cn.setAddress(util.ConvertString(B[1]));
            cn.setCnno(util.ConvertString(B[2]));
            cn.setCndate(new SimpleDateFormat("dd-MM-yyyy", new Locale("us", "us")).format((Date)B[3]));
            cn.setDescription(util.ConvertString(B[4]));
            cn.setAmount(df.format(B[5]));
            cn.setSubtotal(df.format(B[6]));
            cn.setGrandtotal(df.format(B[7]));
            cn.setDifsubtotal(df.format(B[8]));
            cn.setVat(df.format(B[9]));
            cn.setReadsubtotal(df.format(B[10]));
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
