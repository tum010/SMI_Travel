/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.report.model.OtherMonthlyReport;
import com.smi.travel.datalayer.view.dao.OtherMonthlyDao;
import com.smi.travel.datalayer.view.entity.OtherMonthlyView;
import com.smi.travel.util.UtilityFunction;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Jittima
 */
public class OtherMonthlyImpl implements OtherMonthlyDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;
    private UtilityFunction utilityFunction;

    @Override
    public OtherMonthlyReport getOtherMonthlyReport(String datefrom,String dateto,String department,String detail,String user){
        UtilityFunction util = new UtilityFunction();
        OtherMonthlyReport otherMonthlyReport = new OtherMonthlyReport();
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MMM-yyyy hh:mm");
        
        SimpleDateFormat datefromto = new SimpleDateFormat();
        datefromto.applyPattern("dd-MM-yyyy");
        otherMonthlyReport.setFromto(util.ConvertString(datefromto.format(util.convertStringToDate(datefrom))) + "  to  " + 
                                     util.ConvertString(datefromto.format(util.convertStringToDate(dateto))));
        otherMonthlyReport.setSystemdate(String.valueOf(dateformat.format(new Date())));
        otherMonthlyReport.setUser(user);
        otherMonthlyReport.setOtherMonthlyListReportDataSource(new JRBeanCollectionDataSource(getOtherMonthlyList(datefrom,dateto,department,detail)));
        if("1".equalsIgnoreCase(detail)){
            otherMonthlyReport.setOtherMonthlyDetailReportDataSource(new JRBeanCollectionDataSource(getOtherMonthlyDetail(datefrom,dateto,department,detail)));
        }else{
            otherMonthlyReport.setOtherMonthlyDetailReportDataSource(null);
        }
        return otherMonthlyReport;
    }
    
    public List getOtherMonthlyList(String datefrom,String dateto,String department,String detail) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        
        String query = "SELECT pro.`code` AS CODE, pro.`name` AS tour, sum(ot.ad_qty) AS paxad, sum(ot.ch_qty) AS paxch, sum(ot.in_qty) AS paxinf, sum(ot.ad_price) AS sellad, sum(ot.ch_price) AS sellch, sum(ot.in_price) AS sellinf, sum(( ifnull((ot.ad_qty * ot.ad_price), 0) + ifnull((ot.ch_qty * ot.ch_price), 0) + ifnull((ot.in_qty * ot.in_price), 0))) AS totalsell, sum(ot.ad_cost) AS netad, sum(ot.ch_cost) AS netch, sum(ot.in_cost) AS netinf, sum( ifnull((ot.ad_qty * ot.ad_cost), 0) + ifnull((ot.ch_qty * ot.ch_cost), 0) + ifnull((ot.in_qty * ot.in_cost), 0)) AS totalnet, sum(( ifnull((ot.ad_qty * ot.ad_price), 0) + ifnull((ot.ch_qty * ot.ch_price), 0) + ifnull((ot.in_qty * ot.in_price), 0))) - sum(( ifnull((ot.ad_qty * ot.ad_cost), 0) + ifnull((ot.ch_qty * ot.ch_cost), 0) + ifnull((ot.in_qty * ot.in_cost), 0))) AS balance FROM `other_booking` ot INNER JOIN product pro ON pro.id = ot.product_id JOIN `master` `mt` ON (`mt`.`id` = `ot`.`master_id`) WHERE ";
        int check = 0;
        if(((datefrom != null) &&(!"".equalsIgnoreCase(datefrom))) && ((dateto != null) &&(!"".equalsIgnoreCase(dateto)))){
             query += " mt.Create_date BETWEEN '"+ datefrom +"' and '" + dateto +"'" ;
             check =1;
        }
        if((department != null) &&(!"".equalsIgnoreCase(department))){
            if(check == 1){query += " and ";}
            query += " mt.booking_type = '"+department+"'";
            check = 1;
        }
        if(check == 0){
            query = query.replaceAll("WHERE", " ");
        }
        query += " GROUP BY pro.`code` having sum((ifnull((ot.ad_qty * ot.ad_price), 0) + ifnull((ot.ch_qty * ot.ch_price), 0) + ifnull((ot.in_qty * ot.in_price), 0)))  ORDER BY pro.`code` ";
        
        System.out.println(" query :::: " +query);
               
        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("code", Hibernate.STRING)
                .addScalar("tour", Hibernate.STRING)
                .addScalar("paxad", Hibernate.STRING)
                .addScalar("paxch", Hibernate.STRING)
                .addScalar("paxinf", Hibernate.STRING)
                .addScalar("sellad", Hibernate.STRING)
                .addScalar("sellch", Hibernate.STRING)
                .addScalar("sellinf", Hibernate.STRING)
                .addScalar("totalsell", Hibernate.STRING)
                .addScalar("netad", Hibernate.STRING)
                .addScalar("netch", Hibernate.STRING)
                .addScalar("netinf", Hibernate.STRING)
                .addScalar("totalnet", Hibernate.STRING)
                .addScalar("balance", Hibernate.STRING)
                .list();
        
        for (Object[] B : QueryStaffList) {
            OtherMonthlyView other = new OtherMonthlyView();
            other.setCode(B[0]== null ? "" :util.ConvertString(B[0]));
            other.setTour(B[1]== null ? "" :util.ConvertString(B[1]));
            other.setAdult(B[2]== null ? "" :util.ConvertString(B[2]));
            other.setChild(B[3]== null ? "" :util.ConvertString(B[3]));
            other.setInfant(B[4]== null ? "" :util.ConvertString(B[4]));
            other.setSellad(B[5]== null ? "" :util.ConvertString(B[5]));
            other.setSellch(B[6]== null ? "" :util.ConvertString(B[6]));
            other.setSellinf(B[7]== null ? "" :util.ConvertString(B[7]));
            other.setTotalsell(B[8]== null ? "" :util.ConvertString(B[8]));
            other.setNetad(B[9]== null ? "" :util.ConvertString(B[9]));
            other.setNetch(B[10]== null ? "" :util.ConvertString(B[10]));
            other.setNetinf(B[11]== null ? "" :util.ConvertString(B[11]));
            other.setTotalnet(B[12]== null ? "" :util.ConvertString(B[12]));
            other.setBalance(B[13]== null ? "" :util.ConvertString(B[13]));
            data.add(other);
        }
        
        this.sessionFactory.close();
        session.close();
        return data;
    }
    
    public List getOtherMonthlyDetail(String datefrom,String dateto,String department,String detail) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        
        String query = "SELECT * FROM `other_monthly_detail` ot WHERE ";
        int check = 0;
        if(((datefrom != null) &&(!"".equalsIgnoreCase(datefrom))) && ((dateto != null) &&(!"".equalsIgnoreCase(dateto)))){
             query += " ot.create_date BETWEEN '"+ datefrom +"' and '" + dateto +"'" ;
             check =1;
        }
        if((department != null) &&(!"".equalsIgnoreCase(department))){
            if(check == 1){query += " and ";}
            query += " ot.department = '"+department+"'";
            check = 1;
        }
        if(check == 0){
            query = query.replaceAll("WHERE", " ");
            query += query +" where totalsell <> 0 ";    
        }else{
            query += query +" and totalsell <> 0 ";
        }
        
        System.out.println(" query :::: " +query);
               
        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("code", Hibernate.STRING)
                .addScalar("tour", Hibernate.STRING) //name
                .addScalar("refno", Hibernate.STRING)
                .addScalar("other_date", Hibernate.STRING)
                .addScalar("other_time", Hibernate.STRING)
                .addScalar("paxad", Hibernate.STRING)
                .addScalar("paxch", Hibernate.STRING)
                .addScalar("paxinf", Hibernate.STRING)
                .addScalar("totalsell", Hibernate.STRING)
                .addScalar("totalnet", Hibernate.STRING)
                .list();
        
       
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd MMM ''yy");
        String otherdate = "";
        for (Object[] B : QueryStaffList) {
            OtherMonthlyView other = new OtherMonthlyView();
            other.setCode(B[0]== null ? "" :util.ConvertString(B[0]));
            other.setName(B[1]== null ? "" :util.ConvertString(B[1]));
            other.setRefno(B[2]== null ? "" :util.ConvertString(B[2]));
            if(B[3] != null){
                otherdate = util.ConvertString(dateformat.format(util.convertStringToDate(util.ConvertString(B[3]))));
            }
            other.setOtherdate(otherdate +" "+ util.ConvertString(B[4]));
            other.setAdult(B[5]== null ? "" :util.ConvertString(B[5]));
            other.setChild(B[6]== null ? "" :util.ConvertString(B[6]));
            other.setInfant(B[7]== null ? "" :util.ConvertString(B[7]));
            other.setSell(B[8]== null ? "" :util.ConvertString(B[8]));
            other.setNet(B[9]== null ? "" :util.ConvertString(B[9]));
            data.add(other);
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
    
    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
