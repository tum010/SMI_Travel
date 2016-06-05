/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.report.model.GuideCommissionInfo;
import com.smi.travel.datalayer.report.model.GuideCommissionSummary;
import com.smi.travel.datalayer.report.model.GuideCommissionSummaryHeader;
import com.smi.travel.datalayer.view.dao.GuideCommissionReportDao;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
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
    private final  String GUIDECOM_SUMMARY_QUERY = "SELECT" +
                                                    " concat(`st`.`name`, ' ', `st`.`tel`) AS `guide`," +
                                                    "GET_SUM_PAX_DAYTOUR(st.id,'datefrom','dateto')  AS `pax`," +
                                                    "sum(`db`.`guide_commission`) AS `comission`," +
                                                    "`st`.`id` AS `guideid`" +
                                                    "FROM  `daytour_booking` `db`  " +
                                                    "JOIN `master` `mt` ON (`mt`.`id` = `db`.`master_id`) " +
                                                    "JOIN `staff` `st` ON (`db`.`guide_id` = `st`.`id`) " +
                                                    "WHERE  (`db`.`guide_commission` <> 0) ";
    
    @Override
    public GuideCommissionInfo getGuideCommissionInfoReport(String datefrom, String dateto, String username, String guideid) {
        GuideCommissionInfo guideCommissionInfo = new GuideCommissionInfo();
        UtilityFunction util = new UtilityFunction();
        guideCommissionInfo.setSystemdate(new SimpleDateFormat("dd MMM yy hh:mm", new Locale("us", "us")).format(new Date()));
        guideCommissionInfo.setUser(username);
        guideCommissionInfo.setDatefrom(!"".equalsIgnoreCase(datefrom) ? new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(datefrom)) : "");
        guideCommissionInfo.setDateto(!"".equalsIgnoreCase(dateto) ? new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(dateto)) : "");
        String datefromtemp = !"".equalsIgnoreCase(datefrom) ? new SimpleDateFormat("yyyy-MM-dd", new Locale("us", "us")).format(util.convertStringToDate(datefrom)) : "" ;
        String datetotemp = !"".equalsIgnoreCase(dateto) ? new SimpleDateFormat("yyyy-MM-dd", new Locale("us", "us")).format(util.convertStringToDate(dateto)) : "" ;
        guideCommissionInfo.setGuideCommissionSummaryDataSource(new JRBeanCollectionDataSource(getGuideComissionSummaryReport(datefromtemp, datetotemp, username, guideid)));
        guideCommissionInfo.setGuideCommissionDataSource(new JRBeanCollectionDataSource(getGuideComissionReport(datefromtemp, datetotemp, username, guideid)));
        return guideCommissionInfo;
    }
    
    @Override
    public List getGuideComissionReport(String datefrom, String dateto, String username,String guideid) {
        Session session = this.sessionFactory.openSession();
        List data = new ArrayList();
        Date thisdate = new Date();
        String query = "";
        UtilityFunction util = new UtilityFunction();
        int checkQuery = 0;
        if( "".equals(datefrom)  && "".equals(dateto) && "".equals(guideid)){
            query = "SELECT * FROM `guide_commission` gc ";
        }else{  
            if( datefrom == null  && dateto == null && guideid == null){
                query = "SELECT * FROM `guide_commission` gc ";
            }else{
                query = "SELECT * FROM `guide_commission` gc  where ";
            }
        }
        
        if ((datefrom != null )&&(!"".equalsIgnoreCase(datefrom))) {
            if ((dateto != null )&&(!"".equalsIgnoreCase(dateto))) {
                if(checkQuery == 1){
                     query += " and gc.tourdate BETWEEN  '" + datefrom + "' AND '" + dateto + "' ";
                }else{
                    checkQuery = 1;
                     query += " gc.tourdate  BETWEEN  '" + datefrom + "' AND '" + dateto + "' ";
                }
            }
        }
        if ((guideid != null )&&(!"".equalsIgnoreCase(guideid))) {
            if(checkQuery == 1){
                 query += " and gc.guideid = "+guideid;
            }else{
                checkQuery = 1;
                 query += " gc.guideid = "+guideid;
            }
        }
        
        query += " ORDER BY gc.guide , gc.tourdate , gc.tourcode";
        System.out.println(" Query GuideCommission : " + query );
        List<Object[]> QueryGuideComList = session.createSQLQuery(query)
                .addScalar("tourdate", Hibernate.DATE)
                .addScalar("tourcode", Hibernate.STRING)
                .addScalar("customer", Hibernate.STRING)
                .addScalar("pax", Hibernate.INTEGER)
                .addScalar("comission", Hibernate.BIG_DECIMAL)
                .addScalar("sell", Hibernate.BIG_DECIMAL)
                .addScalar("guide", Hibernate.STRING)
                .addScalar("remark", Hibernate.STRING)
                .list();
        
        for (Object[] B : QueryGuideComList) {
             GuideCommissionSummary guidecom = new GuideCommissionSummary();
             guidecom.setSystemdate(new SimpleDateFormat("dd MMM yy hh:mm", new Locale("us", "us")).format(thisdate));
             guidecom.setUser(username);
             guidecom.setDatefrom(!"".equalsIgnoreCase(datefrom) ? new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(datefrom)) : "");
             guidecom.setDateto(!"".equalsIgnoreCase(dateto) ? new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(dateto)) : "");
             guidecom.setTourdate(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(B[0]));
             guidecom.setCode(util.ConvertString(B[1]));
             guidecom.setCustomer(util.ConvertString(B[2]));
             guidecom.setPax(B[3]== null ? 0:(Integer)B[3]);
             guidecom.setComission(B[4]== null ? new BigDecimal("0.00"):(BigDecimal)B[4]);
             guidecom.setSelling(B[5]== null ? new BigDecimal("0.00"):(BigDecimal)B[5]);
             guidecom.setGuide(util.ConvertString(B[6]));
             guidecom.setRemark(util.ConvertString(B[7]));
             data.add(guidecom);
             
           
        }
        
        this.sessionFactory.close();
        session.close();
        return data;
    }
    
    public List getGuideComissionSummaryReport(String datefrom, String dateto, String username,String guideid) {
       Session session = this.sessionFactory.openSession();
        List data = new ArrayList();
        Date thisdate = new Date();
        UtilityFunction util = new UtilityFunction();
        
        String query = GUIDECOM_SUMMARY_QUERY + "and db.tour_date >= '"+datefrom+"' and  db.tour_date <= '"+dateto+"'";
//        if((datefrom != null) && (!"".equalsIgnoreCase(datefrom))){
//            query += "and db.tour_date >= '"+datefrom+"'";
            query = query.replaceFirst("datefrom", datefrom.replaceAll(" ", ""));
//        }
//        if((dateto != null) && (!"".equalsIgnoreCase(dateto))){
//            query += "and db.tour_date <= '"+dateto+"'";
            query = query.replaceFirst("dateto", dateto.replaceAll(" ", ""));
//        }       
        if((guideid != null)&&(!"".equalsIgnoreCase(guideid))){
            query += " and  st.id = "+guideid;
        }
        query += " GROUP BY  `st`.`id`";
        query += " ORDER BY `st`.name ";
        System.out.println("query : "+ query);
        List<Object[]> QueryGuideComList = session.createSQLQuery(query)
                .addScalar("guide", Hibernate.STRING)
                .addScalar("pax", Hibernate.INTEGER)
                .addScalar("comission", Hibernate.BIG_DECIMAL)

                .list();
        
        for (Object[] B : QueryGuideComList) {
             GuideCommissionSummaryHeader guidecom = new GuideCommissionSummaryHeader();
             guidecom.setSystemdate(new SimpleDateFormat("dd MMM yy hh:mm", new Locale("us", "us")).format(thisdate));
             guidecom.setUser(username);
             guidecom.setDatefrom(!"".equalsIgnoreCase(datefrom) ? new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(datefrom)) : "");
             guidecom.setDateto(!"".equalsIgnoreCase(dateto) ? new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(dateto)) : "");
             guidecom.setGuidename(util.ConvertString(B[0]));
             guidecom.setPax(B[1]== null ? 0:(Integer)B[1]);
             guidecom.setCommission(B[2]== null ? new BigDecimal("0.00"):(BigDecimal)B[2]);
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
