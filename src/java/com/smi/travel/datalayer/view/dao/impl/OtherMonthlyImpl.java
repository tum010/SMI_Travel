/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.report.model.OtherMonthlyReport;
import com.smi.travel.datalayer.view.dao.OtherMonthlyDao;
import com.smi.travel.util.UtilityFunction;
import java.util.List;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.hibernate.SessionFactory;

/**
 *
 * @author Jittima
 */
public class OtherMonthlyImpl implements OtherMonthlyDao{
    private SessionFactory sessionFactory;
    private UtilityFunction utilityFunction;

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

    @Override
    public OtherMonthlyReport getOtherMonthlyReport(String datefrom,String dateto,String department,String detail,String user){
        OtherMonthlyReport otherMonthlyReport = new OtherMonthlyReport();
        otherMonthlyReport.setFromto(datefrom + "to" + dateto);
        otherMonthlyReport.setSystemdate(user);
        otherMonthlyReport.setUser(user);
        
        otherMonthlyReport.setOtherMonthlyListReportDataSource(new JRBeanCollectionDataSource(getOtherMonthlyList(datefrom,dateto,department,detail)));
        otherMonthlyReport.setOtherMonthlyDetailReportDataSource(new JRBeanCollectionDataSource(getOtherMonthlyDetail(datefrom,dateto,department,detail)));
        
        return otherMonthlyReport;
    }
    
    public List getOtherMonthlyList(String datefrom,String dateto,String department,String detail) {
        return null;
    }
    
    public List getOtherMonthlyDetail(String datefrom,String dateto,String department,String detail) {
        return null;
    }
}
