/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.PackageTourHotelDao;
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
//                SimpleDateFormat sm = new SimpleDateFormat("dd-mm-yyyy");
//                String strDate = sm.format(from);
                hotelSummary.setFrompage(from);
            }else{
                hotelSummary.setFrompage("");
            }
            if(to != null && !"".equals(to)){
//                SimpleDateFormat sm = new SimpleDateFormat("dd-mm-yyyy");
//                String strDate = sm.format(to);
                hotelSummary.setTopage(to);
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
    public List getHotelMonthly(String from, String to, String department, String detail) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
