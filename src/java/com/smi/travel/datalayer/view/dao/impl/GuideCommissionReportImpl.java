/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.report.model.GuideCommissionInfo;
import com.smi.travel.datalayer.report.model.GuideCommissionSummary;
import com.smi.travel.datalayer.view.dao.GuideCommissionReportDao;
import com.smi.travel.util.UtilityFunction;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Surachai
 */
public class GuideCommissionReportImpl implements GuideCommissionReportDao{
    
     private SessionFactory sessionFactory;
     
    @Override
    public GuideCommissionInfo getGuideCommissionInfoReport(String datefrom, String dateto, String username, String guideid) {
        GuideCommissionInfo guideCommissionInfo = new GuideCommissionInfo();
        guideCommissionInfo.setGuideCommissionDataSource(new JRBeanCollectionDataSource(getGuideComissionReport(datefrom, dateto, username, guideid)));
//        guideCommissionInfo.setGuideCommissionSummaryDataSource(new JRBeanCollectionDataSource(getAgentReportInfo(datefrom, dateto, username, guideid)));
        return guideCommissionInfo;
    }
    
    @Override
    public List getGuideComissionReport(String datefrom, String dateto, String username,String guideid) {
       Session session = this.sessionFactory.openSession();
        List data = new ArrayList();
        Date thisdate = new Date();
        UtilityFunction util = new UtilityFunction();
        String query = "SELECT * FROM `guide_commission` gc where gc.tourdate >= '"+datefrom+"' and gc.tourdate <= '"+dateto+"'";
        if((guideid != null)&&(!"".equalsIgnoreCase(guideid))){
            query += " and gc.guideid = "+guideid;
        }
        query += " ORDER BY gc.tourdate , gc.tourcode";
        List<Object[]> QueryGuideComList = session.createSQLQuery(query)
                .addScalar("tourdate", Hibernate.DATE)
                .addScalar("tourcode", Hibernate.STRING)
                .addScalar("customer", Hibernate.STRING)
                .addScalar("pax", Hibernate.INTEGER)
                .addScalar("comission", Hibernate.INTEGER)
                .addScalar("sell", Hibernate.INTEGER)
                .addScalar("guide", Hibernate.STRING)
                .addScalar("remark", Hibernate.STRING)
                .list();
        
        for (Object[] B : QueryGuideComList) {
             GuideCommissionSummary guidecom = new GuideCommissionSummary();
             guidecom.setSystemdate(new SimpleDateFormat("dd MMM yy hh:mm", new Locale("us", "us")).format(thisdate));
             guidecom.setUser(username);
             guidecom.setDatefrom(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(datefrom)));
             guidecom.setDateto(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(dateto)));
             guidecom.setTourdate(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(B[0]));
             guidecom.setCode(util.ConvertString(B[1]));
             guidecom.setCustomer(util.ConvertString(B[2]));
             guidecom.setPax(B[3]== null ? 0:(Integer)B[3]);
             guidecom.setComission(B[4]== null ? 0:(Integer)B[4]);
             guidecom.setSelling(B[5]== null ? 0:(Integer)B[5]);
             guidecom.setGuide(util.ConvertString(B[6]));
             guidecom.setRemark(util.ConvertString(B[7]));
             data.add(guidecom);
             
           
        }
        
        this.sessionFactory.close();
        session.close();
        return data;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    
    
}
