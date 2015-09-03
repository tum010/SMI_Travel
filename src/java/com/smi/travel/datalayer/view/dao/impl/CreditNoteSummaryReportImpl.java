/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.view.dao.CreditNoteSummaryReportDao;

import com.smi.travel.datalayer.report.model.CreditNoteSummaryReport;

import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
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
 * @author chonnasith
 */
public class CreditNoteSummaryReportImpl implements CreditNoteSummaryReportDao{
    private SessionFactory sessionFactory;
    private UtilityFunction utilityFunction;
    
    @Override
    public List getCreditNoteSummaryReport(String from,String to,String department) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();  
        List data = new ArrayList();
        DecimalFormat df = new DecimalFormat("###,###.00");
         
            
        String query = "SELECT * FROM `creditnote_summary` where ";
        
        
        
        List<Object[]> QueryCNList = session.createSQLQuery(query)
                 //add list
                 .list();
        for (Object[] B : QueryCNList) {
            CreditNoteSummaryReport cn = new CreditNoteSummaryReport();
//        
    
          
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

    public UtilityFunction getUtilityFunction() {
        return utilityFunction;
    }

    public void setUtilityFunction(UtilityFunction utilityFunction) {
        this.utilityFunction = utilityFunction;
    }
    
}
