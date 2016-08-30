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
import com.smi.travel.datalayer.view.entity.PackageSummaryAirlineView;
import com.smi.travel.datalayer.view.entity.PackageSummaryDetailView;
import com.smi.travel.datalayer.view.entity.PackageSummaryHotelView;
import com.smi.travel.datalayer.view.entity.PackageSummaryLandView;
import com.smi.travel.datalayer.view.entity.PackageSummaryOthersView;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
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
        dateformat.applyPattern("dd-MMM-yyyy hh:mm");
        
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
        
        String query = " SELECT ( SELECT min(fdb.depart_date) FROM first_depart_booking fdb WHERE fdb.refno = mt.`Reference No` ) AS date, pt.`code` AS course, pt.`name` AS tourname, ( ifnull(sum((mt.Adult)), 0) + ifnull(sum((mt.Child)), 0) + ifnull(sum((mt.Infant)), 0)) AS pax, ifnull( sum(( SELECT sum(billd.cost) FROM billable_desc billd WHERE billd.billable_id = bill.id )), 0 ) AS net, ifnull( sum(( SELECT sum(billd.price) FROM billable_desc billd WHERE billd.billable_id = bill.id )), 0 ) AS sell, ifnull( sum(( SELECT sum(billd.price) FROM billable_desc billd WHERE billd.billable_id = bill.id )), 0 ) - ifnull( sum(( SELECT sum(billd.cost) FROM billable_desc billd WHERE billd.billable_id = bill.id )), 0 ) AS balance FROM `master` mt INNER JOIN package_tour pt ON mt.package_id = pt.id LEFT JOIN billable bill ON bill.master_id = mt.id WHERE mt.package_id IS NOT NULL ";
        
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
            packageMonthly.setNet(B[4]== null ? "" :util.ConvertString(B[4]));
            packageMonthly.setSell(B[5]== null ? "" :util.ConvertString(B[5]));
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
        String grouptour = "";
        BigDecimal pax = new BigDecimal(BigInteger.ZERO);
        BigDecimal net = new BigDecimal(BigInteger.ZERO);
        BigDecimal sell = new BigDecimal(BigInteger.ZERO);
        BigDecimal balance = new BigDecimal(BigInteger.ZERO);
        int index = 0;
        int indexset = 0;
        for (Object[] B : QueryStaffList) {
            PackageSummaryDetailView packageSum = new PackageSummaryDetailView();
            packageSum.setPackagedate(B[0]== null ? "" :util.ConvertString(dateformat.format(util.convertStringToDate(util.ConvertString(B[0])))));
            packageSum.setCourse(B[1]== null ? "" :util.ConvertString(B[1]));
            packageSum.setPackagename(B[2]== null ? "" :util.ConvertString(B[2]));
            packageSum.setPax(B[3]== null ? "" :util.ConvertString(B[3]));
//            packageSum.setNet(B[4]== null ? "" :util.ConvertString(B[4]));
//            packageSum.setSell(B[5]== null ? "" :util.ConvertString(B[5]));
//            packageSum.setBalance(B[6]== null ? "" :util.ConvertString(B[6]));
            packageSum.setRefno(B[7]== null ? "" :util.ConvertString(B[7]));
            packageSum.setLeadername(B[8]== null ? "" :util.ConvertString(B[8]));
            packageSum.setBookpax(B[9]== null ? "" :util.ConvertString(B[9]));
            packageSum.setGrouptour(B[10]== null ? "" :util.ConvertString(B[10]));
            packageSum.setSubReportDir(url);
            packageSum.setPackageHotelSubReportDataSource(new JRBeanCollectionDataSource(getPackageHotel(util.ConvertString(B[7]))));
            packageSum.setPackageLandSubReportDataSource(new JRBeanCollectionDataSource(getPackageLand(util.ConvertString(B[7]))));
            packageSum.setPackageOthersSubReportDataSource(new JRBeanCollectionDataSource(getPackageOthers(util.ConvertString(B[7]))));
            packageSum.setPackageAirlineSubReportDataSource(new JRBeanCollectionDataSource(getPackageAirline(util.ConvertString(B[7]))));
            
            if("".equalsIgnoreCase(grouptour) || (grouptour).equalsIgnoreCase(util.ConvertString(B[10]))){
                pax = pax.add(B[3]== null ?  new BigDecimal(BigInteger.ZERO) :  new BigDecimal(util.ConvertString(B[3])));
                net = net.add(B[4]== null ?  new BigDecimal(BigInteger.ZERO) :  new BigDecimal(util.ConvertString(B[4])));
                sell = sell.add(B[5]== null ?  new BigDecimal(BigInteger.ZERO) :  new BigDecimal(util.ConvertString(B[5])));
                balance = balance.add(B[6]== null ?  new BigDecimal(BigInteger.ZERO) :  new BigDecimal(util.ConvertString(B[6])));
            }else{
                indexset = index ;
                pax = new BigDecimal(BigInteger.ZERO);
                net = new BigDecimal(BigInteger.ZERO);
                sell = new BigDecimal(BigInteger.ZERO);
                balance = new BigDecimal(BigInteger.ZERO);
                
                pax = pax.add(B[3]== null ?  new BigDecimal(BigInteger.ZERO) :  new BigDecimal(util.ConvertString(B[3])));
                net = net.add(B[4]== null ?  new BigDecimal(BigInteger.ZERO) :  new BigDecimal(util.ConvertString(B[4])));
                sell = sell.add(B[5]== null ?  new BigDecimal(BigInteger.ZERO) :  new BigDecimal(util.ConvertString(B[5])));
                balance = balance.add(B[6]== null ?  new BigDecimal(BigInteger.ZERO) :  new BigDecimal(util.ConvertString(B[6])));
            }
            
            grouptour = B[10]== null ? "" :util.ConvertString(B[10]);
            
            index ++ ;
            data.add(packageSum);
            
            PackageSummaryDetailView psdv = (PackageSummaryDetailView) data.get(indexset);
            psdv.setPaxsum(util.ConvertString(pax));
            psdv.setNet(util.ConvertString(net));
            psdv.setSell(util.ConvertString(sell));
            psdv.setBalance(util.ConvertString(balance));
        }
        
        
    
        this.sessionFactory.close();
        session.close();
        return data;
    }

    private List getPackageHotel(String refNo) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        
        String query = "SELECT * FROM `hotel_booking_view` hb WHERE hb.refno = '"+ refNo +"'";
        
        List<Object[]> QueryHotel = session.createSQLQuery(query)
                .addScalar("refno", Hibernate.STRING)
                .addScalar("name", Hibernate.STRING)
                .addScalar("checkin", Hibernate.STRING)
                .addScalar("checkout", Hibernate.STRING)
                .addScalar("room", Hibernate.STRING)
                .addScalar("net", Hibernate.STRING)
                .addScalar("sell", Hibernate.STRING)
                .list();
        
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd MMM yy");
        
        for (Object[] B : QueryHotel) {
            PackageSummaryHotelView packageHotel = new PackageSummaryHotelView();
            packageHotel.setRefno(refNo);
            packageHotel.setName(B[1]== null ? "" : util.ConvertString(B[1]));           
            packageHotel.setRoom(B[4]== null ? "" : util.ConvertString(B[4]));
            packageHotel.setNet(B[5]== null ? "" : util.ConvertString(B[5]));
            packageHotel.setSell(B[6]== null ? "" : util.ConvertString(B[6]));
            
            String checkinTemp = (B[2]== null ? "" : util.ConvertString(dateformat.format(util.convertStringToDate(util.ConvertString(B[2])))));
            String checkuotTemp = (B[3]== null ? "" : util.ConvertString(dateformat.format(util.convertStringToDate(util.ConvertString(B[3])))));
            String[] checkin = checkinTemp.split(" ");
            String[] checkout = checkuotTemp.split(" ");
            if(!checkin[2].equalsIgnoreCase(checkout[2])){
                packageHotel.setCheckin(checkin[0]+" "+checkin[1]+" "+checkin[2]+" - "+checkout[0]+" "+checkout[1]+" "+checkout[2]);
            }else{
                if(checkin[1].equalsIgnoreCase(checkout[1])){
                    packageHotel.setCheckin(checkin[0]+" - "+checkout[0]+" "+checkout[1]+" "+checkout[2]);
                }else{
                    packageHotel.setCheckin(checkin[0]+" "+checkin[1]+" - "+checkout[0]+" "+checkout[1]+" "+checkout[2]);
                }
            }
            
//            packageHotel.setCheckin(B[2]== null ? "" : util.ConvertString(dateformat.format(util.convertStringToDate(util.ConvertString(B[2])))));
//            packageHotel.setCheckout(B[3]== null ? "" : util.ConvertString(dateformat.format(util.convertStringToDate(util.ConvertString(B[3])))));
                    
            data.add(packageHotel);
        }     
        
        return data;
    }

    private List getPackageLand(String refNo) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        
        String query = "SELECT * FROM `land_booking_view` lb WHERE lb.refno = '"+ refNo +"'";
        
        List<Object[]> QueryLand = session.createSQLQuery(query)
                .addScalar("refno", Hibernate.STRING)
                .addScalar("agent", Hibernate.STRING)
                .addScalar("okby", Hibernate.STRING)
                .addScalar("category", Hibernate.STRING)
                .addScalar("description", Hibernate.STRING)
                .addScalar("qty", Hibernate.STRING)
                .addScalar("net", Hibernate.STRING)
                .addScalar("sell", Hibernate.STRING)
                .list();
        
        for (Object[] B : QueryLand) {
            PackageSummaryLandView packageLand = new PackageSummaryLandView();
            packageLand.setAgent(B[1]== null ? "" : util.ConvertString(B[1]));
            packageLand.setOkby(B[2]== null ? "" : util.ConvertString(B[2]));
            packageLand.setCategory(B[3]== null ? "" : util.ConvertString(B[3]));
            packageLand.setDescription(B[4]== null ? "" : util.ConvertString(B[4]));
            packageLand.setQty(B[5]== null ? "" : util.ConvertString(B[5]));
            packageLand.setNet(B[6]== null ? "" : util.ConvertString(B[6]));
            packageLand.setSell(B[7]== null ? "" : util.ConvertString(B[7]));
            data.add(packageLand);
        }
        
        return data;
    }

    private List getPackageOthers(String refNo) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        
        String query = "SELECT * FROM `other_booking_view` ob WHERE ob.refno = '"+ refNo +"'";
        
        List<Object[]> QueryOther = session.createSQLQuery(query)
                .addScalar("refno", Hibernate.STRING)
                .addScalar("code", Hibernate.STRING)
                .addScalar("name", Hibernate.STRING)
                .addScalar("other_date", Hibernate.STRING)
                .addScalar("other_time", Hibernate.STRING)
                .addScalar("adult", Hibernate.STRING)
                .addScalar("child", Hibernate.STRING)
                .addScalar("infant", Hibernate.STRING)
                .addScalar("sell", Hibernate.STRING)
                .addScalar("net", Hibernate.STRING)
                .list();
        
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd MMM yy");
        SimpleDateFormat timeformat = new SimpleDateFormat();
        timeformat.applyPattern("HH:mm");
        
        for (Object[] B : QueryOther) {
            PackageSummaryOthersView packageOther = new PackageSummaryOthersView();
            packageOther.setCode(B[1]== null ? "" : util.ConvertString(B[1]));
            packageOther.setDescription(B[2]== null ? "" : util.ConvertString(B[2]));           
            packageOther.setAdult(B[5]== null ? "" : util.ConvertString(B[5]));
            packageOther.setChild(B[6]== null ? "" : util.ConvertString(B[6]));
            packageOther.setInfant(B[7]== null ? "" : util.ConvertString(B[7]));
            packageOther.setSell(B[8]== null ? "" : util.ConvertString(B[8]));
            packageOther.setNet(B[9]== null ? "" : util.ConvertString(B[9]));
            
            String dateTemp = (B[3]== null ? "" : util.ConvertString(B[3]));
            String timeTemp = (B[4]== null ? "" : util.ConvertString(B[4]));
            String date = (!"".equalsIgnoreCase(dateTemp) ? String.valueOf(dateformat.format(util.convertStringToDate(dateTemp))) : ""); 
            String time = (!"".equalsIgnoreCase(timeTemp) ? String.valueOf(timeformat.format(util.convertStringToTime(timeTemp))) : ""); 
            packageOther.setDate(date+" "+time);
            
            data.add(packageOther);
        }
        
        return data;
    }

    private List getPackageAirline(String refNo) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        
        String query = "SELECT * FROM `airticket_booking_view` ab WHERE ab.refno = '"+ refNo +"'";
        
        List<Object[]> QueryAirline = session.createSQLQuery(query)
                .addScalar("refno", Hibernate.STRING)
                .addScalar("pnr", Hibernate.STRING)
                .addScalar("airline", Hibernate.STRING)
                .addScalar("flight", Hibernate.STRING)
                .addScalar("dept", Hibernate.STRING)
                .addScalar("arrv", Hibernate.STRING)
                .addScalar("class", Hibernate.STRING)
                .addScalar("depart_date", Hibernate.STRING)
                .addScalar("net", Hibernate.STRING)
                .addScalar("sell", Hibernate.STRING)
                .list();
        
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd MMM yy");
        
        for (Object[] B : QueryAirline) {
            PackageSummaryAirlineView packageAirline = new PackageSummaryAirlineView();
            packageAirline.setPnr(B[1]== null ? "" : util.ConvertString(B[1]));
            packageAirline.setAirline(B[2]== null ? "" : util.ConvertString(B[2]));
            packageAirline.setFlight(B[3]== null ? "" : util.ConvertString(B[3]));
            packageAirline.setDept(B[4]== null ? "" : util.ConvertString(B[4]));
            packageAirline.setArrv(B[5]== null ? "" : util.ConvertString(B[5]));
            packageAirline.setClassflight(B[6]== null ? "" : util.ConvertString(B[6]));
            packageAirline.setDeptdate(B[7]== null ? "" : util.ConvertString(dateformat.format(util.convertStringToDate(util.ConvertString(B[7])))));
            packageAirline.setNet(B[8]== null ? "" : util.ConvertString(B[8]));
            packageAirline.setSell(B[9]== null ? "" : util.ConvertString(B[9]));
            data.add(packageAirline);
        }
        
        return data;
    }
}
