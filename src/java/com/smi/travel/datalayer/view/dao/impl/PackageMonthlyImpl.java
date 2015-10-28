/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.report.model.PackageMonthlyReport;
import com.smi.travel.datalayer.view.dao.PackageMonthlyDao;
import com.smi.travel.datalayer.view.entity.OtherMonthlyView;
import com.smi.travel.datalayer.view.entity.PackageMonthlyView;
import com.smi.travel.datalayer.view.entity.PackageSummaryDetailView;
import com.smi.travel.datalayer.view.entity.PackageSummaryHotelView;
import com.smi.travel.util.UtilityFunction;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
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
public class PackageMonthlyImpl implements PackageMonthlyDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;
    private UtilityFunction utilityFunction;

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

    @Override
    public PackageMonthlyReport getPackageMonthlyReport(String datefrom, String dateto, String department, String detail, String user,String url) {
        UtilityFunction util = new UtilityFunction();
        PackageMonthlyReport packageMonthly = new PackageMonthlyReport();
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy hh:mm");
        
        SimpleDateFormat datefromto = new SimpleDateFormat();
        datefromto.applyPattern("dd-MM-yyyy");
        
        packageMonthly.setFromto(util.ConvertString(datefromto.format(util.convertStringToDate(datefrom))) + "  to  " + 
                                     util.ConvertString(datefromto.format(util.convertStringToDate(dateto))));
        packageMonthly.setSystemdate(String.valueOf(dateformat.format(new Date())));
        packageMonthly.setUser(user);
        packageMonthly.setPackageMonthlyListReportDataSource(new JRBeanCollectionDataSource(getPackageMonthlyList(datefrom,dateto,department,detail)));
        
        if("1".equalsIgnoreCase(detail)){
            packageMonthly.setPackageMonthlyDetailReportDataSource(new JRBeanCollectionDataSource(getPackageMonthlyDetail(datefrom,dateto,department,detail,url)));
        }else{
            packageMonthly.setPackageMonthlyDetailReportDataSource(null);
        }
        return packageMonthly;
    }
     
    public List getPackageMonthlyList(String datefrom,String dateto,String department,String detail) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        
        String query = "SELECT ( SELECT min(fdb.depart_date) FROM first_depart_booking fdb WHERE fdb.refno = mt.`Reference No` ) AS date, pt.`code` AS course, pt.`name` AS tourname, count(pa.id) AS pax, ifnull( sum(( SELECT sum(billd.cost) FROM billable_desc billd WHERE billd.billable_id = bill.id )), 0 ) AS net, ifnull( sum(( SELECT sum(billd.price) FROM billable_desc billd WHERE billd.billable_id = bill.id )), 0 ) AS sell, ifnull( sum(( SELECT sum(billd.price) FROM billable_desc billd WHERE billd.billable_id = bill.id )), 0 ) - ifnull( sum(( SELECT sum(billd.cost) FROM billable_desc billd WHERE billd.billable_id = bill.id )), 0 ) AS balance FROM `master` mt INNER JOIN package_tour pt ON mt.package_id = pt.id INNER JOIN passenger pa ON pa.master_id = mt.id LEFT JOIN billable bill ON bill.master_id = mt.id WHERE mt.package_id IS NOT NULL ";
        
        if(((datefrom != null) &&(!"".equalsIgnoreCase(datefrom))) && ((dateto != null) &&(!"".equalsIgnoreCase(dateto)))){
            query += " and mt.Create_date BETWEEN '"+ datefrom +"' and '" + dateto +"'" ;
        }
        if((department != null) &&(!"".equalsIgnoreCase(department))){
            query += " and mt.booking_type = '"+department+"'";
        }

        query += " GROUP BY pt.id, ( SELECT min(fdb.depart_date) FROM first_depart_booking fdb WHERE fdb.refno = mt.`Reference No` ) ";
        
        System.out.println(" query :::: " +query);

        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("date", Hibernate.STRING)
                .addScalar("course", Hibernate.STRING)
                .addScalar("tourname", Hibernate.STRING)
                .addScalar("pax", Hibernate.STRING)
                .addScalar("net", Hibernate.STRING)
                .addScalar("sell", Hibernate.STRING)
                .addScalar("balance", Hibernate.STRING)
                .list();
        
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd/MM/yy");
        
        for (Object[] B : QueryStaffList) {
            PackageMonthlyView packageMonthly = new PackageMonthlyView();
            packageMonthly.setPackdate(B[0]== null ? "" : util.ConvertString(dateformat.format(util.convertStringToDate(util.ConvertString(B[0])))));
            packageMonthly.setCourse(B[1]== null ? "" :util.ConvertString(B[1]));
            packageMonthly.setTour(B[2]== null ? "" :util.ConvertString(B[2]));
            packageMonthly.setPax(B[3]== null ? "" :util.ConvertString(B[3]));
            packageMonthly.setSell(B[4]== null ? "" :util.ConvertString(B[4]));
            packageMonthly.setNet(B[5]== null ? "" :util.ConvertString(B[5]));
            packageMonthly.setBalance(B[6]== null ? "" :util.ConvertString(B[6]));
            data.add(packageMonthly);
        }
        
        this.sessionFactory.close();
        session.close();
        return data;
    }
    
        
    public List getPackageMonthlyDetail(String datefrom,String dateto,String department,String detail,String url) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        
        String query = "SELECT * FROM `package_summary_detail` pa WHERE ";
        int check = 0;
        if(((datefrom != null) &&(!"".equalsIgnoreCase(datefrom))) && ((dateto != null) &&(!"".equalsIgnoreCase(dateto)))){
             query += " pa.createdate BETWEEN '"+ datefrom +"' and '" + dateto +"'" ;
             check =1;
        }
        
        if((department != null) &&(!"".equalsIgnoreCase(department))){
            if(check == 1){query += " and ";}
            query += " pa.department = '"+department+"'";
            check = 1;
        }
        
        if(check == 0){
            query = query.replaceAll("WHERE", " ");
        }
        
        System.out.println(" query :::: " +query);
               
        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("date", Hibernate.STRING)
                .addScalar("code", Hibernate.STRING)
                .addScalar("name", Hibernate.STRING)
                .addScalar("pax", Hibernate.STRING)
                .addScalar("net", Hibernate.STRING)
                .addScalar("sell", Hibernate.STRING)
                .addScalar("balance", Hibernate.STRING)
                .addScalar("refno", Hibernate.STRING)
                .addScalar("leadername", Hibernate.STRING)
                .addScalar("bookpax", Hibernate.STRING)
                .addScalar("grouptour", Hibernate.STRING)
                .addScalar("department", Hibernate.STRING)
                .addScalar("createdate", Hibernate.STRING)
                .list();
        
       
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yy");
        
        for (Object[] B : QueryStaffList) {
            PackageSummaryDetailView packageSum = new PackageSummaryDetailView();
            packageSum.setPackagedate(B[0]== null ? "" :util.ConvertString(dateformat.format(util.convertStringToDate(util.ConvertString(B[0])))));
            packageSum.setCourse(B[1]== null ? "" :util.ConvertString(B[1]));
            packageSum.setPackagename(B[2]== null ? "" :util.ConvertString(B[2]));
            packageSum.setPax(B[3]== null ? "" :util.ConvertString(B[3]));
            packageSum.setNet(B[4]== null ? "" :util.ConvertString(B[4]));
            packageSum.setSell(B[5]== null ? "" :util.ConvertString(B[5]));
            packageSum.setBalance(B[6]== null ? "" :util.ConvertString(B[6]));
            packageSum.setRefno(B[7]== null ? "" :util.ConvertString(B[7]));
            packageSum.setLeadername(B[8]== null ? "" :util.ConvertString(B[8]));
            packageSum.setBookpax(B[9]== null ? "" :util.ConvertString(B[9]));
            packageSum.setGrouptour(B[10]== null ? "" :util.ConvertString(B[10]));
            packageSum.setSubReportDir("C:\\Users\\chonnasith\\Documents\\NetBeansProjects\\SMI_Travel\\build\\web\\WEB-INF\\report");
            packageSum.setPackageHotelSubReportDataSource(new JRBeanCollectionDataSource(test()));
            data.add(packageSum);
        }
        
        
    
        this.sessionFactory.close();
        session.close();
        return data;
    }

    private List test() {
        List data = new ArrayList();
        PackageSummaryHotelView packageSum = new PackageSummaryHotelView();
        packageSum.setName("250001");
        data.add(packageSum);
        return data;
    }
}
