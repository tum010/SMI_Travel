/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.PackageTourHotelDao;
import com.smi.travel.datalayer.view.entity.HotelMonthly;
import com.smi.travel.datalayer.view.entity.HotelSummary;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Kanokporn
 */
public class PackageTourHotelImpl implements PackageTourHotelDao {
    private SessionFactory sessionFactory;
    private Transaction transaction;

    @Override
    public List getHotelSummary(String from, String to, String department) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList<HotelSummary>();
        HotelSummary listHotelSummary = new HotelSummary();
        
        String query = "";
        int checkQuery = 0;
        if( from == null  && to == null  && department == null ){
            query = "SELECT " +
                "ci.name as city, " +
                "ht.`name` as hotel," +
                "sum(DATEDIFF(hb.checkout,hb.checkin)) as night, " +
                "sum((select sum(IFNULL(hr.price,0)* DATEDIFF(hb.checkout,hb.checkin)  ) from hotel_room hr where hr.booking_hotel_id = hb.id)) + " +
                "ifnull(sum((select sum(IFNULL(hre.price,0) ) from hotel_request hre where hre.booking_hotel_id = hb.id)),0)" +
                " as sell, " +
                "sum((select sum(IFNULL(hr.cost,0) * DATEDIFF(hb.checkout,hb.checkin)) from hotel_room hr where hr.booking_hotel_id = hb.id)) +	" +
                "ifnull(sum((select sum(IFNULL(hre.cost,0)) from hotel_request hre where hre.booking_hotel_id = hb.id)),0) " +
                "as net, " +
                "sum((select sum( (IFNULL(hr.price,0)-IFNULL(hr.cost,0))  * DATEDIFF(hb.checkout,hb.checkin)) from hotel_room hr where hr.booking_hotel_id = hb.id)) as profit	" +
                "FROM `hotel_booking` hb " +
                "INNER JOIN hotel ht on  hb.hotel_id = ht.id	" +
                "left JOIN m_city ci on ci.id = ht.city	" +
                "INNER JOIN master mt on mt.id = hb.master_id	" ;
        }else{
            query = "SELECT " +
                "ci.name as city, " +
                "ht.`name` as hotel," +
                "sum(DATEDIFF(hb.checkout,hb.checkin)) as night, " +
                "sum((select sum(IFNULL(hr.price,0)* DATEDIFF(hb.checkout,hb.checkin)  ) from hotel_room hr where hr.booking_hotel_id = hb.id)) + " +
                "ifnull(sum((select sum(IFNULL(hre.price,0) ) from hotel_request hre where hre.booking_hotel_id = hb.id)),0)" +
                " as sell, " +
                "sum((select sum(IFNULL(hr.cost,0) * DATEDIFF(hb.checkout,hb.checkin)) from hotel_room hr where hr.booking_hotel_id = hb.id)) +	" +
                "ifnull(sum((select sum(IFNULL(hre.cost,0)) from hotel_request hre where hre.booking_hotel_id = hb.id)),0) " +
                "as net, " +
                "sum((select sum( (IFNULL(hr.price,0)-IFNULL(hr.cost,0))  * DATEDIFF(hb.checkout,hb.checkin)) from hotel_room hr where hr.booking_hotel_id = hb.id)) as profit	" +
                "FROM `hotel_booking` hb " +
                "INNER JOIN hotel ht on  hb.hotel_id = ht.id	" +
                "left JOIN m_city ci on ci.id = ht.city	" +
                "INNER JOIN master mt on mt.id = hb.master_id	where  " ;
        }
        
        if ((from != null )&&(!"".equalsIgnoreCase(from))) {
            if ((to != null )&&(!"".equalsIgnoreCase(to))) {
                if(checkQuery == 1){
                     query += " and  mt.Create_date  BETWEEN  '" + from + "' AND '" + to + "' ";
                }else{
                    checkQuery = 1;
                     query += " mt.Create_date  BETWEEN  '" + from + "' AND '" + to + "' ";
                }
            }
        }
        
        if ((department != null )&&(!"".equalsIgnoreCase(department))) {
            if(checkQuery == 1){
                 query += " and mt.booking_type  = '" + department + "' ";
            }else{
                checkQuery = 1;
                 query += " mt.booking_type  = '" + department + "' ";
            }
        }
        
        query += "GROUP BY ht.id " +
                 "having  sum((select sum(IFNULL(hr.price,0)) from hotel_room hr where hr.booking_hotel_id = hb.id))  is not null " +
                 "and sum((select sum(IFNULL(hr.cost,0)) from hotel_room hr where hr.booking_hotel_id = hb.id)) is not null  ";
        
        List<Object[]> QueryList =  session.createSQLQuery(query)
                .addScalar("city",Hibernate.STRING)		
                .addScalar("hotel",Hibernate.STRING)		
                .addScalar("night",Hibernate.INTEGER)		
                .addScalar("sell",Hibernate.BIG_DECIMAL)		
                .addScalar("net",Hibernate.BIG_DECIMAL)	
                .addScalar("profit",Hibernate.BIG_DECIMAL)
                .list();
        for (Object[] B : QueryList) {
            HotelSummary hotelSummary = new HotelSummary();
            //header    
            if(from != null && !"".equals(from)){
                String array[] = from.split("-");
                String fromdate = array[2] + "-"+array[1] + "-" + array[0];
                System.out.println("From Date : " + fromdate);
                hotelSummary.setFrompage(fromdate);
            }else{
                hotelSummary.setFrompage("");
            }
            if(to != null && !"".equals(to)){
                String array[] = to.split("-");
                String todate = array[2] + "-"+array[1] + "-" + array[0];
                System.out.println("To Date : " + todate);
                hotelSummary.setTopage(todate);
            }else{
                hotelSummary.setTopage("");
            }
            if(department != null && !"".equals(department)){
                hotelSummary.setDepartmentpage(department);
            }else{
                hotelSummary.setDepartmentpage("");
            }
            
            hotelSummary.setSystemdate(new SimpleDateFormat("dd-MM-yyyy", new Locale("us", "us")).format(new Date()));
            
            hotelSummary.setCity(util.ConvertString(B[0]));
            hotelSummary.setHotel(util.ConvertString(B[1]));
            System.out.println("Night : "+B[2]);
            if(B[2] != null && !"".equals(B[2])){
                hotelSummary.setNight((Integer) B[2]);
            }else{
                hotelSummary.setNight(0);
            }
            if(B[3] != null && !"".equals(B[3])){
                hotelSummary.setSell((BigDecimal) B[3]);
            }else{
                hotelSummary.setSell(new BigDecimal(0));
            }
            if(B[4] != null && !"".equals(B[4])){
                hotelSummary.setNet((BigDecimal) B[4]);
            }else{
                hotelSummary.setNet(new BigDecimal(0));
            }
            if(B[5] != null && !"".equals(B[5])){
                hotelSummary.setProfit((BigDecimal) B[5]);
            }else{
                hotelSummary.setProfit(new BigDecimal(0));
            }           
            data.add(hotelSummary);
        }

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

    @Override
    public List getHotelMonthly(String from, String to, String department, String detail,String systemuser) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList<HotelSummary>();
        HotelSummary listHotelSummary = new HotelSummary();
        
        String query = "";
        String query2 = "";
        int checkQuery = 0;
        if( from == null  && to == null  && department == null ){
            query2 = "select * from (" +
                    "SELECT  " +
                    "ht.`name` as hotel, " +
                    "ht.`id` as hotelid, " +
                    "CONCAT(hr.qty,' ',hr.category) as room_type, " +
                    "(DATEDIFF(hb.checkout,hb.checkin)) as night, " +
                    "sum(IFNULL(hr.cost,0)) as net, " +
                    "sum(IFNULL(hr.price,0)) as sell,  " +
                    "sum(IFNULL(hr.price,0) - IFNULL(hr.cost,0) ) as profit  " +
                    "FROM `hotel_booking` hb  " +
                    "INNER JOIN hotel ht on  hb.hotel_id = ht.id  " +
                    "INNER JOIN master mt on mt.id = hb.master_id " +
                    "left JOIN hotel_room hr on hr.booking_hotel_id = hb.id  ";
        }else{
            query2 = "select * from (" +
                    "SELECT  " +
                    "ht.`name` as hotel, " +
                    "ht.`id` as hotelid," +
                    "CONCAT(hr.qty,' ',hr.category) as room_type, " +
                    "(DATEDIFF(hb.checkout,hb.checkin)) as night, " +
                    "sum(IFNULL(hr.cost,0)) as net, " +
                    "sum(IFNULL(hr.price,0)) as sell, " +
                    "sum(IFNULL(hr.price,0) - IFNULL(hr.cost,0) ) as profit  " +
                    "FROM `hotel_booking` hb  " +
                    "INNER JOIN hotel ht on  hb.hotel_id = ht.id  " +
                    "INNER JOIN master mt on mt.id = hb.master_id   " +
                    "left JOIN hotel_room hr on hr.booking_hotel_id = hb.id  where   ";
            
        }
        
        if ((from != null )&&(!"".equalsIgnoreCase(from))) {
            if ((to != null )&&(!"".equalsIgnoreCase(to))) {
                if(checkQuery == 1){
                     query += " and  mt.Create_date  BETWEEN  '" + from + "' AND '" + to + "' ";
                }else{
                    checkQuery = 1;
                     query += " mt.Create_date  BETWEEN  '" + from + "' AND '" + to + "' ";
                }
            }
        }
        
        if ((department != null )&&(!"".equalsIgnoreCase(department))) {
            if(checkQuery == 1){
                 query += " and mt.booking_type  = '" + department + "' ";
            }else{
                checkQuery = 1;
                 query += " mt.booking_type  = '" + department + "' ";
            }
        }
  
        query2 += query + "group by ht.id ,CONCAT(hr.qty,' ',hr.category) ";

        query2 += " UNION all  " + 
                "SELECT  " +
                "ht.`name` as hotel, " +
                "ht.`id` as hotelid, " +
                "'Other' as room_type, " +
                "null as night, " +
                "sum(IFNULL(hr.cost,0)) as net, " +
                "sum(IFNULL(hr.price,0)) as sell, " +
                "sum(IFNULL(hr.price,0) - IFNULL(hr.cost,0) ) as profit " +
                "FROM `hotel_booking` hb  " +
                "INNER JOIN hotel ht on  hb.hotel_id = ht.id  " +
                "INNER JOIN master mt on mt.id = hb.master_id  " +
                "left JOIN hotel_request hr on hr.booking_hotel_id = hb.id  " ;
        if(checkQuery == 1){
             query2 += " where ";
        }else{
            checkQuery = 1;
             query2 += " ";
        }
        
        query2 += query + " group by ht.id )x ORDER BY x.hotel , x.night desc ";
        System.out.println("Query : " + query2);
        List<Object[]> QueryList =  session.createSQLQuery(query2)
                .addScalar("room_type",Hibernate.STRING)	
                .addScalar("hotelid",Hibernate.INTEGER)		
                .addScalar("hotel",Hibernate.STRING)		
                .addScalar("night",Hibernate.INTEGER)		
                .addScalar("sell",Hibernate.BIG_DECIMAL)		
                .addScalar("net",Hibernate.BIG_DECIMAL)	
                .addScalar("profit",Hibernate.BIG_DECIMAL)
                .list();
        for (Object[] B : QueryList) {
            HotelSummary hotelSummary = new HotelSummary();
            //header    
            if(from != null && !"".equals(from)){
                String array[] = from.split("-");
                String fromdate = array[2] + "-"+array[1] + "-" + array[0];
                System.out.println("From Date : " + fromdate);
                hotelSummary.setFrompage(fromdate);
            }else{
                hotelSummary.setFrompage("");
            }
            if(to != null && !"".equals(to)){
                String array[] = to.split("-");
                String todate = array[2] + "-"+array[1] + "-" + array[0];
                System.out.println("To Date : " + todate);
                hotelSummary.setTopage(todate);
            }else{
                hotelSummary.setTopage("");
            }
            if(department != null && !"".equals(department)){
                hotelSummary.setDepartmentpage(department);
            }else{
                hotelSummary.setDepartmentpage("");
            }
            
            hotelSummary.setSystemdate(new SimpleDateFormat("dd-MM-yyyy", new Locale("us", "us")).format(new Date()));
            
            hotelSummary.setRoomtype(util.ConvertString(B[0]));
            if(B[1] != null && !"".equals(B[1])){
                hotelSummary.setHotelid((Integer) B[1]);
            }else{
                hotelSummary.setHotelid(0);
            }
            hotelSummary.setHotel(util.ConvertString(B[2]));
            System.out.println("Night : "+B[3]);
            if(B[3] != null && !"".equals(B[3])){
                hotelSummary.setNight((Integer) B[3]);
            }else{
                hotelSummary.setNight(0);
            }
            if(B[4] != null && !"".equals(B[4])){
                hotelSummary.setSell((BigDecimal) B[4]);
            }else{
                hotelSummary.setSell(new BigDecimal(0));
            }
            if(B[5] != null && !"".equals(B[5])){
                hotelSummary.setNet((BigDecimal) B[5]);
            }else{
                hotelSummary.setNet(new BigDecimal(0));
            }
            if(B[6] != null && !"".equals(B[6])){
                hotelSummary.setProfit((BigDecimal) B[6]);
            }else{
                hotelSummary.setProfit(new BigDecimal(0));
            } 
             
            data.add(hotelSummary);
        }

        return data;
    }

    @Override
    public List getHotelMonthlyDetail(String from, String to, String department, String detail, String systemuser) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList<HotelMonthly>();
        HotelMonthly listHotelSummaryDetail = new HotelMonthly();
        
        String query = "";
        int checkQuery = 0;
        if( from == null  && to == null  && department == null ){
            query = "SELECT * FROM `hotel_monthly_detail_main` " ;
        }else{
            query = "SELECT * FROM `hotel_monthly_detail_main` where " ;
        }
        
        if ((from != null )&&(!"".equalsIgnoreCase(from))) {
            if ((to != null )&&(!"".equalsIgnoreCase(to))) {
                if(checkQuery == 1){
                     query += " and  mt.Create_date  BETWEEN  '" + from + "' AND '" + to + "' ";
                }else{
                    checkQuery = 1;
                     query += " mt.Create_date  BETWEEN  '" + from + "' AND '" + to + "' ";
                }
            }
        }
        
        if ((department != null )&&(!"".equalsIgnoreCase(department))) {
            if(checkQuery == 1){
                 query += " and mt.booking_type  = '" + department + "' ";
            }else{
                checkQuery = 1;
                 query += " mt.booking_type  = '" + department + "' ";
            }
        }
        
        query += "GROUP BY ht.id " +
                 "having  sum((select sum(IFNULL(hr.price,0)) from hotel_room hr where hr.booking_hotel_id = hb.id))  is not null " +
                 "and sum((select sum(IFNULL(hr.cost,0)) from hotel_room hr where hr.booking_hotel_id = hb.id)) is not null  ";
        
        List<Object[]> QueryList =  session.createSQLQuery(query)
                .addScalar("city",Hibernate.STRING)		
                .addScalar("hotel",Hibernate.STRING)		
                .addScalar("night",Hibernate.INTEGER)		
                .addScalar("sell",Hibernate.BIG_DECIMAL)		
                .addScalar("net",Hibernate.BIG_DECIMAL)	
                .addScalar("profit",Hibernate.BIG_DECIMAL)
                .list();
        for (Object[] B : QueryList) {
            HotelMonthly hotelSummary = new HotelMonthly();
            //header    
            if(from != null && !"".equals(from)){
                String array[] = from.split("-");
                String fromdate = array[2] + "-"+array[1] + "-" + array[0];
                System.out.println("From Date : " + fromdate);
                hotelSummary.setFrompage(fromdate);
            }else{
                hotelSummary.setFrompage("");
            }
            if(to != null && !"".equals(to)){
                String array[] = to.split("-");
                String todate = array[2] + "-"+array[1] + "-" + array[0];
                System.out.println("To Date : " + todate);
                hotelSummary.setTopage(todate);
            }else{
                hotelSummary.setTopage("");
            }
            if(department != null && !"".equals(department)){
                hotelSummary.setDepartmentpage(department);
            }else{
                hotelSummary.setDepartmentpage("");
            }
            
            hotelSummary.setSystemdate(new SimpleDateFormat("dd-MM-yyyy", new Locale("us", "us")).format(new Date()));
            
//            hotelSummary.setCity(util.ConvertString(B[0]));
//            hotelSummary.setHotel(util.ConvertString(B[1]));
//            System.out.println("Night : "+B[2]);
//            if(B[2] != null && !"".equals(B[2])){
//                hotelSummary.setNight((Integer) B[2]);
//            }else{
//                hotelSummary.setNight(0);
//            }
//            if(B[3] != null && !"".equals(B[3])){
//                hotelSummary.setSell((BigDecimal) B[3]);
//            }else{
//                hotelSummary.setSell(new BigDecimal(0));
//            }
//            if(B[4] != null && !"".equals(B[4])){
//                hotelSummary.setNet((BigDecimal) B[4]);
//            }else{
//                hotelSummary.setNet(new BigDecimal(0));
//            }
//            if(B[5] != null && !"".equals(B[5])){
//                hotelSummary.setProfit((BigDecimal) B[5]);
//            }else{
//                hotelSummary.setProfit(new BigDecimal(0));
//            }           
            data.add(hotelSummary);
        }

        return data;
    }
    
    
}
