/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.report.model.AgentCommission;
import com.smi.travel.datalayer.report.model.AgentCommissionReport;
import com.smi.travel.datalayer.report.model.AgentCommissionSummaryReport;
import com.smi.travel.datalayer.view.dao.AgentCommissionReportDao;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Surachai
 */
public class AgentCommissionReportImpl implements AgentCommissionReportDao {

    private SessionFactory sessionFactory;
    private static final String AGENTCOM_SUMMARY_QUERY = "SELECT agt.`code` AS `code`,"
            + "agt.`name` AS `name`,"
            + "count((mt.id)) AS count_booking,"
            + "SUM(db.agent_comission) AS comission "
            + "FROM daytour_booking db "
            + "INNER JOIN agent agt ON db.agent_id = agt.id "
//            + "INNER JOIN daytour_booking_price dp ON dp.daytour_booking_id = db.id "
            + "INNER JOIN `master` mt ON mt.id = db.master_id ";

    
    @Override
    public AgentCommission getAgentCommissionReport(String datefrom, String dateto, String user,String agentid) {
        UtilityFunction util = new UtilityFunction();
        AgentCommission agentCommission = new AgentCommission();
        agentCommission.setDatefrom(datefrom);
        agentCommission.setDateto(dateto);
        agentCommission.setSystemdate(new SimpleDateFormat("dd MMM yy hh:mm", new Locale("us", "us")).format(new Date()));
        agentCommission.setUser(user);
        String datefromtemp = !"".equalsIgnoreCase(datefrom) ? new SimpleDateFormat("yyyy-MM-dd", new Locale("us", "us")).format(util.convertStringToDate(datefrom)) : "" ;
        String datetotemp = !"".equalsIgnoreCase(dateto) ? new SimpleDateFormat("yyyy-MM-dd", new Locale("us", "us")).format(util.convertStringToDate(dateto)) : "" ;
        agentCommission.setAgentCommissionInfoDataSource(new JRBeanCollectionDataSource(getAgentReportInfo(datefromtemp, datetotemp, user,agentid)));
        agentCommission.setAgentCommissionSummaryDataSource(new JRBeanCollectionDataSource(getAgentReportSummary(datefromtemp, datetotemp, user,agentid)));
        return agentCommission;
    }
    
    @Override
    public List getAgentReportSummary(String datefrom, String dateto, String user,String agentid) {
        Session session = this.sessionFactory.openSession();
        List data = new ArrayList();
        Date thisdate = new Date();
        UtilityFunction util = new UtilityFunction();
        String sql = AGENTCOM_SUMMARY_QUERY+" where db.tour_date >= '"+datefrom+"' and db.tour_date <= '"+dateto+"' " ;
        
        if((agentid != null)&&(!"".equalsIgnoreCase(agentid))){
            sql += " and agt.id ="+agentid;
        }
        sql += " GROUP BY agt.`code`,agt.`name` HAVING  comission <> 0 ORDER BY `agt`.`name` , db.tour_date ";
        System.out.println("sql :" +sql);
        List<Object[]> QueryAgentComSummaryList = session.createSQLQuery(sql)
                .addScalar("code", Hibernate.STRING)
                .addScalar("name", Hibernate.STRING)
                .addScalar("count_booking", Hibernate.STRING)
                .addScalar("comission", Hibernate.BIG_DECIMAL)
                .list();
  
        System.out.println("QueryAgentComSummaryList.size : "+QueryAgentComSummaryList.size());
        for (Object[] B : QueryAgentComSummaryList) {
             AgentCommissionSummaryReport   report = new  AgentCommissionSummaryReport(); 
             report.setCode(util.ConvertString(B[0]));
             report.setName(util.ConvertString(B[1]));
             report.setCountbook(StringUtils.isEmpty(util.ConvertString(B[2])) ? 0 : Integer.parseInt(util.ConvertString(B[2])));
             report.setCommission(B[3]== null ? new BigDecimal("0.00"):(BigDecimal)B[3]);
             report.setSystemdate(new SimpleDateFormat("dd MMM yy hh:mm", new Locale("us", "us")).format(thisdate));
             report.setDatefrom(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(datefrom)));
             report.setDateto(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(dateto)));
             report.setUser(user);
             data.add(report);
        }      
        return data;  
    }

    @Override
    public List getAgentReportInfo(String datefrom, String dateto, String user,String agentid) {
        Session session = this.sessionFactory.openSession();
        List data = new ArrayList();
        Date thisdate = new Date();
        UtilityFunction util = new UtilityFunction();
        String query ="SELECT * FROM `agent_commission_info` where tourdate >= '"+datefrom+"' and tourdate <= '"+dateto+"' ";
        if((agentid != null)&&(!"".equalsIgnoreCase(agentid))){
            query += " and agentid = "+agentid;
        }
        query += " order by name , tourdate ";
        List<Object[]> QueryAgentComList = session.createSQLQuery(query)
                .addScalar("tourdate", Hibernate.DATE)
                .addScalar("tourcode", Hibernate.STRING)
                .addScalar("customer", Hibernate.STRING)
                .addScalar("pax", Hibernate.STRING)
                .addScalar("comission", Hibernate.BIG_DECIMAL)
                .addScalar("sell", Hibernate.BIG_DECIMAL)
                .addScalar("name", Hibernate.STRING)
                .list();
                
        for (Object[] B : QueryAgentComList) {
             AgentCommissionReport   report = new  AgentCommissionReport(); 
             report.setTourdate(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(B[0]));
             report.setTourcode(util.ConvertString(B[1]));
             report.setClient(util.ConvertString(B[2]));
             report.setPax(StringUtils.isEmpty(util.ConvertString(B[3])) ? 0 : Integer.parseInt(util.ConvertString(B[3])));
             report.setCommission(B[4]== null ? new BigDecimal("0.00"):(BigDecimal)B[4]);
             report.setSell(B[5]== null ? new BigDecimal("0.00"):(BigDecimal)B[5]);
             report.setAgent(util.ConvertString(B[6]));
             report.setSystemdate(new SimpleDateFormat("dd MMM yy hh:mm", new Locale("us", "us")).format(thisdate));
             report.setDatefrom(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(datefrom)));
             report.setDateto(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(dateto)));
             report.setUser(user);
             data.add(report);
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
