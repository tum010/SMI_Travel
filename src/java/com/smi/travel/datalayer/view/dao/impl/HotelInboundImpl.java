/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.report.model.HotelInbound;
import com.smi.travel.datalayer.view.dao.HotelInboundDao;
import com.smi.travel.util.UtilityFunction;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.SerializationUtils;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Surachai
 */
public class HotelInboundImpl implements HotelInboundDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List getHotelInboundVoucher(String hotelID) {
        Session session = this.sessionFactory.openSession();
        List data = new ArrayList();
        Date thisdate = new Date();
        UtilityFunction util = new UtilityFunction();
        HotelInbound inbound = new HotelInbound();
        inbound.setSystemdate(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(thisdate));

        List<Object[]> QueryHotelList = session.createSQLQuery(" SELECT * FROM `hotel_inbound_info` where `hotel_inbound_info`.id = " + hotelID)
                .addScalar("hotel_ref", Hibernate.STRING)
                .addScalar("adult", Hibernate.STRING)
                .addScalar("child", Hibernate.STRING)
                .addScalar("infant", Hibernate.STRING)
                .addScalar("total", Hibernate.STRING)
                .addScalar("remark", Hibernate.STRING)
                .addScalar("name", Hibernate.STRING)
                .addScalar("checkin", Hibernate.DATE)
                .addScalar("checkout", Hibernate.DATE)
                .addScalar("total_date", Hibernate.STRING)
                .list();
        for (Object[] B : QueryHotelList) {
            inbound.setHotelref(util.ConvertString(B[0]));
            inbound.setAdult(util.ConvertString(B[1]));
            inbound.setChild(util.ConvertString(B[2]));
            inbound.setInfant(util.ConvertString(B[3]));
            inbound.setTotal(util.ConvertString(B[4]));
            inbound.setRemark(util.ConvertString(B[5]));
            inbound.setMeal(util.ConvertString(B[6]));
            inbound.setCheckin(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format((Date)B[7]));
            inbound.setCheckout(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format((Date)B[8]));
            inbound.setNight(util.ConvertString(B[9]));
        }
        //inbound.setCheckin((Date)B[7]);

        List<String> QueryRoomList = session.createSQLQuery(" SELECT * FROM `hotel_inbound_room` where `hotel_inbound_room`.booking_hotel_id = " + hotelID)
                .addScalar("room", Hibernate.STRING)
                .list();
        for (int i = 0; i < QueryRoomList.size(); i++) {
            if (i == 0) {
                inbound.setRoom1(util.CheckNullString(QueryRoomList.get(i)));
            } else if (i == 2) {
                inbound.setRoom2(util.CheckNullString(QueryRoomList.get(i)));
            } else if (i == 3) {
                inbound.setRoom3(util.CheckNullString(QueryRoomList.get(i)));
            }
        }

        List<String> QueryRequestList = session.createSQLQuery(" SELECT * FROM `hotel_inbound_category` where id=  " + hotelID)
                .addScalar("cate_desc", Hibernate.STRING)
                .list();
        for (int i = 0; i < QueryRequestList.size(); i++) {
            if (i == 0) {
                inbound.setRequest1(util.CheckNullString(QueryRequestList.get(i)));
            } else if (i == 2) {
                inbound.setRequest2(util.CheckNullString(QueryRequestList.get(i)));
            } else if (i == 3) {
                inbound.setRequest3(util.CheckNullString(QueryRequestList.get(i)));
            }
        }

        List<String> QueryPassengerList = session.createSQLQuery(" SELECT * FROM `hotel_inbound_payment` where id =  " + hotelID)
                .addScalar("pax_name", Hibernate.STRING)
                .list();
        for (String B : QueryPassengerList) {
            HotelInbound inboundPass = new HotelInbound();
            inboundPass = cloneObject(inbound);
            inboundPass.setPaxname(util.ConvertString(B));
            inboundPass.setPsgremark("");
            inboundPass.setRooming("");
            inboundPass.setStatus("ok");
            inboundPass.setAge("");
            data.add(inboundPass);
        }
        if(QueryPassengerList == null){
            data.add(inbound);
        }
        return data;
    }

    public HotelInbound cloneObject(HotelInbound clone) {
        HotelInbound inbound = new HotelInbound();
        inbound.setAdult(clone.getAdult());
        inbound.setSystemdate(clone.getSystemdate());
        inbound.setAge(clone.getAge());
        inbound.setCheckin(clone.getCheckin());
        inbound.setCheckout(clone.getCheckout());
        inbound.setChild(clone.getChild());
        inbound.setHotelref(clone.getHotelref());
        inbound.setInfant(clone.getInfant());
        inbound.setMeal(clone.getMeal());
        inbound.setNight(clone.getNight());
        inbound.setRemark(clone.getRemark());
        inbound.setRequest1(clone.getRequest1());
        inbound.setRequest2(clone.getRequest2());
        inbound.setRequest3(clone.getRequest3());
        inbound.setRoom1(clone.getRoom1());
        inbound.setRoom2(clone.getRoom2());
        inbound.setRoom3(clone.getRoom3());
        inbound.setTotal(clone.getTotal());
        return inbound;
    }

}
