/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.report.model.HotelVoucher;
import com.smi.travel.datalayer.view.dao.HotelVoucherDao;
import com.smi.travel.util.UtilityFunction;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Surachai
 */
public class HotelVoucherImpl   implements  HotelVoucherDao{

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public HotelVoucher getHotelVoucher(String hotelID,String name) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        HotelVoucher voucher = new HotelVoucher();
        Date thisDate = new Date();
        voucher.setUser(name);
        System.out.println("name :" +name);
        voucher.setSystemdate(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(thisDate));
        List<Object[]> QueryHotelList = session.createSQLQuery(" SELECT * FROM `hotel_voucher_info` where hotel_id =  " + hotelID)
                .addScalar("ref_no", Hibernate.STRING)
                .addScalar("hotel_code", Hibernate.STRING)
                .addScalar("hotel_name", Hibernate.STRING)
                .addScalar("hotel_address", Hibernate.STRING)
                .addScalar("hotel_tel", Hibernate.STRING)
                .addScalar("hotel_fax", Hibernate.STRING)
                .addScalar("adult", Hibernate.STRING)
                .addScalar("child", Hibernate.STRING)
                .addScalar("infant", Hibernate.STRING)
                .addScalar("total_person", Hibernate.STRING)
                .addScalar("leader_name", Hibernate.STRING)
                .addScalar("remark", Hibernate.STRING)
                .addScalar("checkin", Hibernate.DATE)
                .addScalar("checkout", Hibernate.DATE)
                .addScalar("hotel_ref", Hibernate.STRING)
                .addScalar("night", Hibernate.STRING)
                .addScalar("meal_name", Hibernate.STRING)
                .addScalar("okby", Hibernate.STRING)
                .list();

        for (Object[] B : QueryHotelList) {
            voucher.setRefno(util.ConvertString(B[0]));
            voucher.setCode(B[1] == null ? "" : util.ConvertString(B[1]));
            voucher.setName(util.ConvertString(B[2]));
            voucher.setAddress(util.ConvertString(B[3]));
            voucher.setTel(util.ConvertString(B[4]));A
            voucher.setFax(util.ConvertString(B[5]));
            voucher.setAdult(util.ConvertString(B[6]));
            voucher.setChild(util.ConvertString(B[7]));
            voucher.setInfant(util.ConvertString(B[8]));
            voucher.setTotal(util.ConvertString(B[9]));
            voucher.setLeadername(util.ConvertString(B[10]));
            String remark = generateRemark(B,session);
            voucher.setRemark(remark);
            voucher.setCheckin(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format((Date)B[12]));
            voucher.setCheckout(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format((Date)B[13]));
            voucher.setHotelref(util.ConvertString(B[14]));
            voucher.setNight(util.ConvertString(B[15]));
            voucher.setMeal(util.ConvertString(B[16])); 
            voucher.setOkby(util.ConvertString(B[17]).equalsIgnoreCase("null")? "":util.ConvertString(B[17])); 
        }
        
         List<String> QueryRoomList = session.createSQLQuery(" SELECT * FROM `hotel_inbound_room` where `hotel_inbound_room`.booking_hotel_id = " + hotelID)
                .addScalar("room", Hibernate.STRING)
                .list();
        for (int i = 0; i < QueryRoomList.size(); i++) {
            if (i == 0) {
                voucher.setRoom1(util.CheckNullString(QueryRoomList.get(i)));
            } else if (i == 1) {
                voucher.setRoom2(util.CheckNullString(QueryRoomList.get(i)));
            } else if (i == 2) {
                voucher.setRoom3(util.CheckNullString(QueryRoomList.get(i)));
            } else if (i ==3 ) {
                voucher.setRoom4(util.CheckNullString(QueryRoomList.get(i)));
            }
        }

        List<String> QueryPassengerList = session.createSQLQuery(" SELECT * FROM `hotel_voucher_passenger` where hotel_id =  " + hotelID)
                .addScalar("passenger_name", Hibernate.STRING)
                .list();

        for (int i = 0; i < QueryPassengerList.size(); i++) {
            String data = QueryPassengerList.get(i);
            if (i == 0) {
                voucher.setPassenger1(util.CheckNullString(data));
            } else if (i == 1) {
                voucher.setPassenger2(util.CheckNullString(data));
            } else if (i == 2) {
                voucher.setPassenger3(util.CheckNullString(data));
            } else if (i == 3) {
                voucher.setPassenger4(util.CheckNullString(data));
            } else if (i == 4) {
                voucher.setPassenger5(util.CheckNullString(data));
            } else if (i == 5) {
                voucher.setPassenger6(util.CheckNullString(data));
            } else if (i == 6) {
                voucher.setPassenger7(util.CheckNullString(data));
            } else if (i == 7) {
                voucher.setPassenger8(util.CheckNullString(data));
            }
        }

        session.close();
        this.sessionFactory.close();
        return voucher;
    }

    private String generateRemark(Object[] B, Session session) {       
        UtilityFunction util = new UtilityFunction();
        String refNo = (B[0] != null ? (util.ConvertString(B[0])).replaceAll("-", "") : "");
        String remark = (B[11] != null ? util.ConvertString(B[11]) : "");
        String result = remark;
        
         List<String> queryList = session.createSQLQuery(" SELECT * FROM `invoice_view_hotel_additional` where ref_no =  '" + refNo + "'")
                .addScalar("cate_desc", Hibernate.STRING)
                .list();
         
         for(String A : queryList){
             String hotelAdditional = (A != null && !"".equalsIgnoreCase(A) ? A : "");
             System.out.println("Hotel Additional : "+hotelAdditional);
             result += (!"".equalsIgnoreCase(result) ? "\n" + hotelAdditional : hotelAdditional);
         }
         System.out.println("Result : "+result);
        
        return result;
    }

}
