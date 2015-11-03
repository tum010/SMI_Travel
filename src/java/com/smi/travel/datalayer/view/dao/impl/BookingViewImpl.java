/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.entity.InvoiceDetail;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.view.dao.BookingViewDao;
import com.smi.travel.datalayer.view.entity.BookingAirSummaryView;
import com.smi.travel.datalayer.view.entity.BookingDayTourSummaryView;
import com.smi.travel.datalayer.view.entity.BookingHotelSummaryView;
import com.smi.travel.datalayer.view.entity.BookingLandSummaryView;
import com.smi.travel.datalayer.view.entity.BookingOtherSummaryView;
import com.smi.travel.datalayer.view.entity.BookingPackageSummaryView;
import com.smi.travel.datalayer.view.entity.BookingView;
import com.smi.travel.util.UtilityFunction;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


/**
 *
 * @author Surachai
 */
public class BookingViewImpl implements BookingViewDao{
    private SessionFactory sessionFactory;
    private static final int MAX_ROW = 200;
    @Override
    public List<BookingView> getBookingList(String refno,String passFirst,String passLast,String username,String departmentID,String Bookdate,String status,String pnr,String ticketNo,String payBy, String bankTransfer, String transferDateFrom, String transferDateTo) {
        String query = "from BookingView book where ";
        String subquery = " book.refno in (select master.referenceNo from Passenger p ";
        Session session = this.sessionFactory.openSession();
        int check = 0;
        int checksub = 0;
        System.out.println("passLast : "+passLast);
        if ((refno != null) && (!"".equalsIgnoreCase(refno))) {
            query += " book.refno like '%" +refno +"%'";
            check = 1;
        }
        
        if ((departmentID != null) && (!"".equalsIgnoreCase(departmentID))) {
            if (check == 1) {query += " and ";}
            query += " book.departmentId = '" +departmentID +"'";
            check = 1;
        }
        
        if ((username != null) && (!"".equalsIgnoreCase(username))) {
            if (check == 1) {query += " and ";}
            query += " book.createBy = '" +username +"'";
            check = 1;
        }
        
        if ((Bookdate != null) && (!"".equalsIgnoreCase(Bookdate))) {
            if (check == 1) {query += " and ";}
            query += " book.createDate = '" +Bookdate +"'";
            check = 1;
        }
        
        if ((status != null) && (!"".equalsIgnoreCase(status))) {
            if (check == 1) {query += " and ";}
            query += " book.statusId = '" +status +"'";
            check = 1;
        }
        
        if ((pnr != null) && (!"".equalsIgnoreCase(pnr))) {
            if (check == 1) {query += " and ";}
            query += " book.pnr Like '%" +pnr +"%'";
            check = 1;
        }
        
        if ((ticketNo != null) && (!"".equalsIgnoreCase(ticketNo))) {
            if (check == 1) {query += " and ";}
            query += " book.ticketNo like '%" +ticketNo +"%'";
            check = 1;
        }
        
        if ((passFirst != null) && (!"".equalsIgnoreCase(passFirst))) {
            if (check == 1) {query += " and ";}
            subquery += " where p.customer.firstName like '%"+passFirst+"%'";
            checksub = 1;
            check = 1;
        }

        if ((passLast != null) && (!"".equalsIgnoreCase(passLast))) {
            if ((check == 1)&&(checksub != 1)) {query += " and ";}
            check = 1;
            if(checksub == 1){
                subquery += " and ";
            }else{
                 subquery += " where "; 
            }
            checksub = 1;
            subquery += "  p.customer.lastName like '%"+passLast+"%'";
        }
        
        if ((payBy != null) && (!"".equalsIgnoreCase(payBy))) {
            if (check == 1) {query += " and ";}
            query += " book.payBy = '" +payBy +"'";
            check = 1;
        }
        
        if ((bankTransfer != null) && (!"".equalsIgnoreCase(bankTransfer))) {
            if (check == 1) {query += " and ";}
            query += " book.accId = '" +bankTransfer +"'";
            check = 1;
        }
        
        if ((transferDateFrom != null) && (!"".equalsIgnoreCase(transferDateFrom))) {
            if (check == 1) {query += " and ";}
            query += " book.transferDate >= '" +transferDateFrom +"'";
            check = 1;
        }
        
        if ((transferDateTo != null) && (!"".equalsIgnoreCase(transferDateTo))) {
            if (check == 1) {query += " and ";}
            query += " book.transferDate <= '" +transferDateTo +"'";
            check = 1;
        }
                
        if (check == 0) {
            query = query.replaceAll("where", " ");
        }
        if(checksub == 1){
            query += subquery +")";
        }
       
        query += " order by  ref_no desc ";
         System.out.println("query book view  : "+query);
       // List<BookingView> BookingList = session.createQuery(query).list();
        Query HqlQuery = session.createQuery(query);
        HqlQuery.setMaxResults(MAX_ROW);
        List<BookingView> BookingList = HqlQuery.list();
        
        if (BookingList.isEmpty()) {
            return null;
        }
        return BookingList;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int cancelBook(String refNoEdit) {
        int result = 0;
        String hql = "UPDATE Master m set m.MBookingstatus.id = 3 WHERE m.referenceNo = :refNo";
        try {
            org.hibernate.classic.Session session = this.sessionFactory.openSession();
            Query query = session.createQuery(hql);
            query.setParameter("refNo", refNoEdit);
            result = query.executeUpdate();
            System.out.println("Rows affected: " + result);
         } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }

        return result;
    }

    @Override
    public String checkBook(String refNoEdit) {
        String result = "fail";
        Session session = this.sessionFactory.openSession();
        List<InvoiceDetail> invoiceDetailList = session.createQuery("from InvoiceDetail inv where inv.billableDesc.billable.master.referenceNo = :refNo").setParameter("refNo", refNoEdit).list();
        if(!invoiceDetailList.isEmpty()){
            return result;
        } 
        result = "success";       
        return result;
    }

    @Override
    public int enableBook(String refNoEdit) {
        int result = 0;
        String hql = "UPDATE Master m set m.MBookingstatus.id = 1 WHERE m.referenceNo = :refNo";
        try {
            org.hibernate.classic.Session session = this.sessionFactory.openSession();
            Query query = session.createQuery(hql);
            query.setParameter("refNo", refNoEdit);
            result = query.executeUpdate();
            System.out.println("Rows affected: " + result);
         } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }

        return result;
    }

    @Override
    public List<BookingHotelSummaryView> getListBookingHotelSummaryView(String bookRefNo, String bookLeader, String bookDate, String hotelName, String hotelCheckIn, String hotelCheckOut) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List<BookingHotelSummaryView> bookingHotelSummaryViewList = new ArrayList<BookingHotelSummaryView>();
        
        String query = " SELECT * FROM `booking_hotel_summary` ";
        boolean condition = false;
        
        if((bookRefNo != null) && (!"".equalsIgnoreCase(bookRefNo))){
            query += (condition ? " and " : " where ");
            query += " refno = '" + bookRefNo + "' " ;
            condition = true;
        }
        if((bookLeader != null) && (!"".equalsIgnoreCase(bookLeader))){
            query += (condition ? " and " : " where ");
            query += " leader LIKE '%" + bookLeader + "%' " ;
            condition = true;
        }
        if((bookDate != null) && (!"".equalsIgnoreCase(bookDate))){
            query += (condition ? " and " : " where ");
            query += " refdate = '" + bookDate + "' " ;
            condition = true;
        }
        if((hotelName != null) && (!"".equalsIgnoreCase(hotelName))){
            query += (condition ? " and " : " where ");
            query += " hotel LIKE '%" + hotelName + "%' " ;
            condition = true;
        }
        if((hotelCheckIn != null) && (!"".equalsIgnoreCase(hotelCheckIn))){
            query += (condition ? " and " : " where ");
            query += " checkin = '" + hotelCheckIn + "' " ;
            condition = true;
        }
        if((hotelCheckOut != null) && (!"".equalsIgnoreCase(hotelCheckOut))){
            query += (condition ? " and " : " where ");
            query += " checkout = '" + hotelCheckOut + "' " ;
            condition = true;
        }

        List<Object[]> QueryHotel = session.createSQLQuery(query)
                .addScalar("refno", Hibernate.STRING)
                .addScalar("refdate", Hibernate.STRING)
                .addScalar("agent", Hibernate.STRING)
                .addScalar("leader", Hibernate.STRING)
                .addScalar("hotel", Hibernate.STRING)
                .addScalar("checkin", Hibernate.STRING)
                .addScalar("checkout", Hibernate.STRING)
                .addScalar("Total_cost", Hibernate.STRING)
                .addScalar("curcost", Hibernate.STRING)
                .addScalar("Total_price", Hibernate.STRING)
                .addScalar("curamount", Hibernate.STRING)
                .addScalar("invoice", Hibernate.STRING)
                .addScalar("receipt", Hibernate.STRING)
                .addScalar("id", Hibernate.STRING)
                .list();
        
        for (Object[] B : QueryHotel) {
            BookingHotelSummaryView bookingHotelSummaryView = new BookingHotelSummaryView();
            bookingHotelSummaryView.setRefno(B[0]== null ? "" : util.ConvertString(B[0]));
            bookingHotelSummaryView.setRefdate(B[1]== null ? "" : util.ConvertString(B[1]));
            bookingHotelSummaryView.setAgent(B[2]== null ? "" :util.ConvertString(B[2]));
            bookingHotelSummaryView.setLeader(B[3]== null ? "" :util.ConvertString(B[3]));
            bookingHotelSummaryView.setHotel(B[4]== null ? "" :util.ConvertString(B[4]));
            bookingHotelSummaryView.setCheckin(B[5]== null ? "" :util.ConvertString(B[5]));
            bookingHotelSummaryView.setCheckout(B[6]== null ? "" :util.ConvertString(B[6]));
            bookingHotelSummaryView.setTotalcost(B[7]== null ? "" :util.ConvertString(B[7]));
            bookingHotelSummaryView.setCurcost(B[8]== null ? "" :util.ConvertString(B[8]));
            bookingHotelSummaryView.setTotalprice(B[9]== null ? "" :util.ConvertString(B[9]));
            bookingHotelSummaryView.setCuramount(B[10]== null ? "" :util.ConvertString(B[10]));
            bookingHotelSummaryView.setInvoice(B[11]== null ? "" :util.ConvertString(B[11]));
            bookingHotelSummaryView.setReceipt(B[12]== null ? "" :util.ConvertString(B[12]));
            bookingHotelSummaryView.setId(B[13]== null ? "" :util.ConvertString(B[13]));
            bookingHotelSummaryViewList.add(bookingHotelSummaryView);
        }
        
        this.sessionFactory.close();
        session.close();
        return bookingHotelSummaryViewList;
    }

    @Override
    public List<BookingAirSummaryView> getListBookingAirSummaryView(String bookRefNo, String bookLeader, String bookDate, String airPnr, String airDeptDate, String airFlight) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List<BookingAirSummaryView> bookingAirSummaryViewList = new ArrayList<BookingAirSummaryView>();
        
        String query = " SELECT * FROM `booking_air_summary` ";
        boolean condition = false;
        
        if((bookRefNo != null) && (!"".equalsIgnoreCase(bookRefNo))){
            query += (condition ? " and " : " where ");
            query += " refno = '" + bookRefNo + "' " ;
            condition = true;
        }
        if((bookLeader != null) &&(!"".equalsIgnoreCase(bookLeader))){
            query += (condition ? " and " : " where ");
            query += " leader LIKE '%" + bookLeader + "%' " ;
            condition = true;
        }
        if((bookDate != null) &&(!"".equalsIgnoreCase(bookDate))){
            query += (condition ? " and " : " where ");
            query += " refdate = '" + bookDate + "' " ;
            condition = true;
        }
        if((airPnr != null) &&(!"".equalsIgnoreCase(airPnr))){
            query += (condition ? " and " : " where ");
            query += " pnr = '" + airPnr + "' " ;
            condition = true;
        }
        if((airDeptDate != null) &&(!"".equalsIgnoreCase(airDeptDate))){
            query += (condition ? " and " : " where ");
            query += " depart_date = '" + airDeptDate + "' " ;
            condition = true;
        }
        if((airFlight != null) &&(!"".equalsIgnoreCase(airFlight))){
            query += (condition ? " and " : " where ");
            query += " flight = '" + airFlight + "' " ;
            condition = true;
        }

        List<Object[]> QueryAir = session.createSQLQuery(query)
                .addScalar("refno", Hibernate.STRING)
                .addScalar("refdate", Hibernate.STRING)
                .addScalar("agent", Hibernate.STRING)
                .addScalar("leader", Hibernate.STRING)
                .addScalar("pax", Hibernate.STRING)
                .addScalar("pnr", Hibernate.STRING)
                .addScalar("dept", Hibernate.STRING)
                .addScalar("arrv", Hibernate.STRING)
                .addScalar("depart_date", Hibernate.STRING)
                .addScalar("flight", Hibernate.STRING)
                .addScalar("invoice", Hibernate.STRING)
                .addScalar("receipt", Hibernate.STRING)
                .list();
        
        for (Object[] B : QueryAir) {
            BookingAirSummaryView bookingAirSummaryView = new BookingAirSummaryView();
            bookingAirSummaryView.setRefno(B[0]== null ? "" : util.ConvertString(B[0]));
            bookingAirSummaryView.setRefdate(B[1]== null ? "" : util.ConvertString(B[1]));
            bookingAirSummaryView.setAgent(B[2]== null ? "" :util.ConvertString(B[2]));
            bookingAirSummaryView.setLeader(B[3]== null ? "" :util.ConvertString(B[3]));
            bookingAirSummaryView.setPax(B[4]== null ? "" :util.ConvertString(B[4]));
            bookingAirSummaryView.setPnr(B[5]== null ? "" :util.ConvertString(B[5]));
            bookingAirSummaryView.setDept(B[6]== null ? "" :util.ConvertString(B[6]));
            bookingAirSummaryView.setArrv(B[7]== null ? "" :util.ConvertString(B[7]));
            bookingAirSummaryView.setDepartdate(B[8]== null ? "" :util.ConvertString(B[8]));
            bookingAirSummaryView.setFlight(B[9]== null ? "" :util.ConvertString(B[9]));
            bookingAirSummaryView.setInvoice(B[10]== null ? "" :util.ConvertString(B[10]));
            bookingAirSummaryView.setReceipt(B[11]== null ? "" :util.ConvertString(B[11]));
            bookingAirSummaryViewList.add(bookingAirSummaryView);
        }
        
        this.sessionFactory.close();
        session.close();
        return bookingAirSummaryViewList;
    }

    @Override
    public List<BookingPackageSummaryView> getListBookingPackageSummaryView(String bookRefNo, String bookLeader, String bookDate, String packageName, String packageAgent) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List<BookingPackageSummaryView> bookingPackageSummaryViewList = new ArrayList<BookingPackageSummaryView>();
        
        String query = " SELECT * FROM `booking_package_summary` ";
        boolean condition = false;
        
        if((bookRefNo != null) && (!"".equalsIgnoreCase(bookRefNo))){
            query += (condition ? " and " : " where ");
            query += " refno = '" + bookRefNo + "' " ;
            condition = true;
        }
        if((bookLeader != null) &&(!"".equalsIgnoreCase(bookLeader))){
            query += (condition ? " and " : " where ");
            query += " leader LIKE '%" + bookLeader + "%' " ;
            condition = true;
        }
        if((bookDate != null) &&(!"".equalsIgnoreCase(bookDate))){
            query += (condition ? " and " : " where ");
            query += " refdate = '" + bookDate + "' " ;
            condition = true;
        }
        if((packageName != null) &&(!"".equalsIgnoreCase(packageName))){
            query += (condition ? " and " : " where ");
            query += " code LIKE '%" + packageName + "%' and name LIKE '%" + packageName + "%' " ;
            condition = true;
        }
        if((packageAgent != null) &&(!"".equalsIgnoreCase(packageAgent))){
            query += (condition ? " and " : " where ");
            query += " agent = '" + packageAgent + "' " ;
            condition = true;
        }

        List<Object[]> QueryPackage = session.createSQLQuery(query)
                .addScalar("refno", Hibernate.STRING)
                .addScalar("refdate", Hibernate.STRING)
                .addScalar("agent", Hibernate.STRING)
                .addScalar("leader", Hibernate.STRING)
                .addScalar("code", Hibernate.STRING)
                .addScalar("name", Hibernate.STRING)
                .addScalar("invoice", Hibernate.STRING)
                .addScalar("receipt", Hibernate.STRING)
                .list();
        
        for (Object[] B : QueryPackage) {
            BookingPackageSummaryView bookingPackageSummaryView = new BookingPackageSummaryView();
            bookingPackageSummaryView.setRefno(B[0]== null ? "" : util.ConvertString(B[0]));
            bookingPackageSummaryView.setRefdate(B[1]== null ? "" : util.ConvertString(B[1]));
            bookingPackageSummaryView.setAgent(B[2]== null ? "" :util.ConvertString(B[2]));
            bookingPackageSummaryView.setLeader(B[3]== null ? "" :util.ConvertString(B[3]));
            bookingPackageSummaryView.setCode(B[4]== null ? "" :util.ConvertString(B[4]));
            bookingPackageSummaryView.setName(B[5]== null ? "" :util.ConvertString(B[5]));
            bookingPackageSummaryView.setInvoice(B[6]== null ? "" :util.ConvertString(B[6]));
            bookingPackageSummaryView.setReceipt(B[7]== null ? "" :util.ConvertString(B[7]));
            bookingPackageSummaryViewList.add(bookingPackageSummaryView);
        }
        
        this.sessionFactory.close();
        session.close();
        return bookingPackageSummaryViewList;
    }

    @Override
    public List<BookingDayTourSummaryView> getListBookingDayTourSummaryView(String bookRefNo, String bookLeader, String bookDate, String tourCode, String tourName, String tourDate, String tourPickUp) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List<BookingDayTourSummaryView> bookingDayTourSummaryViewList = new ArrayList<BookingDayTourSummaryView>();
        
        String query = " SELECT * FROM `booking_daytour_summary` ";
        boolean condition = false;
        
        if((bookRefNo != null) && (!"".equalsIgnoreCase(bookRefNo))){
            query += (condition ? " and " : " where ");
            query += " refno = '" + bookRefNo + "' " ;
            condition = true;
        }
        if((bookLeader != null) &&(!"".equalsIgnoreCase(bookLeader))){
            query += (condition ? " and " : " where ");
            query += " leader LIKE '%" + bookLeader + "%' " ;
            condition = true;
        }
        if((bookDate != null) &&(!"".equalsIgnoreCase(bookDate))){
            query += (condition ? " and " : " where ");
            query += " refdate = '" + bookDate + "' " ;
            condition = true;
        }
        if((tourCode != null) &&(!"".equalsIgnoreCase(tourCode))){
            query += (condition ? " and " : " where ");
            query += " tour_code LIKE '%" + tourCode + "%' " ;
            condition = true;
        }
        if((tourName != null) &&(!"".equalsIgnoreCase(tourName))){
            query += (condition ? " and " : " where ");
            query += " tour_name LIKE '%" + tourName + "%' " ;
            condition = true;
        }
        if((tourDate != null) &&(!"".equalsIgnoreCase(tourDate))){
            query += (condition ? " and " : " where ");
            query += " tour_date = '" + tourDate + "' " ;
            condition = true;
        }
        if((tourPickUp != null) &&(!"".equalsIgnoreCase(tourPickUp))){
            query += (condition ? " and " : " where ");
            query += " pickup LIKE '%" + tourPickUp + "%' " ;
            condition = true;
        }

        List<Object[]> QueryDayTour = session.createSQLQuery(query)
                .addScalar("refno", Hibernate.STRING)
                .addScalar("refdate", Hibernate.STRING)
                .addScalar("agent", Hibernate.STRING)
                .addScalar("leader", Hibernate.STRING)
                .addScalar("pax", Hibernate.STRING)
                .addScalar("tour_code", Hibernate.STRING)
                .addScalar("tour_name", Hibernate.STRING)
                .addScalar("tour_date", Hibernate.STRING)
                .addScalar("pickup", Hibernate.STRING)
                .addScalar("time", Hibernate.STRING)
                .addScalar("adult", Hibernate.STRING)
                .addScalar("child", Hibernate.STRING)
                .addScalar("infant", Hibernate.STRING)
                .addScalar("remark", Hibernate.STRING)
                .addScalar("invoice", Hibernate.STRING)
                .addScalar("receipt", Hibernate.STRING)
                .addScalar("id", Hibernate.STRING)
                .list();
        
        for (Object[] B : QueryDayTour) {
            BookingDayTourSummaryView bookingDayTourSummaryView = new BookingDayTourSummaryView();
            bookingDayTourSummaryView.setRefno(B[0]== null ? "" : util.ConvertString(B[0]));
            bookingDayTourSummaryView.setRefdate(B[1]== null ? "" : util.ConvertString(B[1]));
            bookingDayTourSummaryView.setAgent(B[2]== null ? "" :util.ConvertString(B[2]));
            bookingDayTourSummaryView.setLeader(B[3]== null ? "" :util.ConvertString(B[3]));
            bookingDayTourSummaryView.setPax(B[4]== null ? "" :util.ConvertString(B[4]));
            bookingDayTourSummaryView.setTourcode(B[5]== null ? "" :util.ConvertString(B[5]));
            bookingDayTourSummaryView.setTourname(B[6]== null ? "" :util.ConvertString(B[6]));
            bookingDayTourSummaryView.setTourdate(B[7]== null ? "" :util.ConvertString(B[7]));
            bookingDayTourSummaryView.setPickup(B[8]== null ? "" :util.ConvertString(B[8]));
            bookingDayTourSummaryView.setTime(B[9]== null ? "" :util.ConvertString(B[9]));
            bookingDayTourSummaryView.setAdult(B[10]== null ? "" :util.ConvertString(B[10]));
            bookingDayTourSummaryView.setChild(B[11]== null ? "" :util.ConvertString(B[11]));
            bookingDayTourSummaryView.setInfant(B[12]== null ? "" :util.ConvertString(B[12]));
            bookingDayTourSummaryView.setRemark(B[13]== null ? "" :util.ConvertString(B[13]));
            bookingDayTourSummaryView.setInvoice(B[14]== null ? "" :util.ConvertString(B[14]));
            bookingDayTourSummaryView.setReceipt(B[15]== null ? "" :util.ConvertString(B[15]));
            bookingDayTourSummaryView.setId(B[16]== null ? "" :util.ConvertString(B[16]));
            bookingDayTourSummaryViewList.add(bookingDayTourSummaryView);
        }
        
        this.sessionFactory.close();
        session.close();
        return bookingDayTourSummaryViewList;
    }

    @Override
    public List<BookingOtherSummaryView> getListBookingOtherSummaryView(String bookRefNo, String bookLeader, String bookDate, String otherCode, String otherName, String otherDate, String otherAgent) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List<BookingOtherSummaryView> bookingOtherSummaryViewList = new ArrayList<BookingOtherSummaryView>();
        
        String query = " SELECT * FROM `booking_other_summary` ";
        boolean condition = false;
        
        if((bookRefNo != null) && (!"".equalsIgnoreCase(bookRefNo))){
            query += (condition ? " and " : " where ");
            query += " refno = '" + bookRefNo + "' " ;
            condition = true;
        }
        if((bookLeader != null) &&(!"".equalsIgnoreCase(bookLeader))){
            query += (condition ? " and " : " where ");
            query += " leader LIKE '%" + bookLeader + "%' " ;
            condition = true;
        }
        if((bookDate != null) &&(!"".equalsIgnoreCase(bookDate))){
            query += (condition ? " and " : " where ");
            query += " refdate = '" + bookDate + "' " ;
            condition = true;
        }
        if((otherCode != null) &&(!"".equalsIgnoreCase(otherCode))){
            query += (condition ? " and " : " where ");
            query += " code LIKE '%" + otherCode + "%' " ;
            condition = true;
        }
        if((otherName != null) &&(!"".equalsIgnoreCase(otherName))){
            query += (condition ? " and " : " where ");
            query += " name LIKE '%" + otherName + "%' " ;
            condition = true;
        }
        if((otherDate != null) &&(!"".equalsIgnoreCase(otherDate))){
            query += (condition ? " and " : " where ");
            query += " other_date = '" + otherDate + "' " ;
            condition = true;
        }
        if((otherAgent != null) &&(!"".equalsIgnoreCase(otherAgent))){
            query += (condition ? " and " : " where ");
            query += " agent LIKE '%" + otherAgent + "%' " ;
            condition = true;
        }

        List<Object[]> QueryOther = session.createSQLQuery(query)
                .addScalar("refno", Hibernate.STRING)
                .addScalar("refdate", Hibernate.STRING)
                .addScalar("agent", Hibernate.STRING)
                .addScalar("leader", Hibernate.STRING)
                .addScalar("code", Hibernate.STRING)
                .addScalar("name", Hibernate.STRING)
                .addScalar("other_date", Hibernate.STRING)
                .addScalar("invoice", Hibernate.STRING)
                .addScalar("receipt", Hibernate.STRING)
                .addScalar("id", Hibernate.STRING)
                .list();
        
        for (Object[] B : QueryOther) {
            BookingOtherSummaryView bookingOtherSummaryView = new BookingOtherSummaryView();
            bookingOtherSummaryView.setRefno(B[0]== null ? "" : util.ConvertString(B[0]));
            bookingOtherSummaryView.setRefdate(B[1]== null ? "" : util.ConvertString(B[1]));
            bookingOtherSummaryView.setAgent(B[2]== null ? "" :util.ConvertString(B[2]));
            bookingOtherSummaryView.setLeader(B[3]== null ? "" :util.ConvertString(B[3]));
            bookingOtherSummaryView.setCode(B[4]== null ? "" :util.ConvertString(B[4]));
            bookingOtherSummaryView.setName(B[5]== null ? "" :util.ConvertString(B[5]));
            bookingOtherSummaryView.setOtherdate(B[6]== null ? "" :util.ConvertString(B[6]));
            bookingOtherSummaryView.setInvoice(B[7]== null ? "" :util.ConvertString(B[7]));
            bookingOtherSummaryView.setReceipt(B[8]== null ? "" :util.ConvertString(B[8]));
            bookingOtherSummaryView.setId(B[9]== null ? "" :util.ConvertString(B[9])); 
            bookingOtherSummaryViewList.add(bookingOtherSummaryView);
        }
        
        this.sessionFactory.close();
        session.close();
        return bookingOtherSummaryViewList;
    }

    @Override
    public List<BookingLandSummaryView> getListBookingLandSummaryView(String bookRefNo, String bookLeader, String bookDate, String landOkBy, String landAgent, String landCategory) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List<BookingLandSummaryView> bookingLandSummaryViewList = new ArrayList<BookingLandSummaryView>();
        
        String query = " SELECT * FROM `booking_land_summary` ";
        boolean condition = false;
        
        if((bookRefNo != null) && (!"".equalsIgnoreCase(bookRefNo))){
            query += (condition ? " and " : " where ");
            query += " refno = '" + bookRefNo + "' " ;
            condition = true;
        }
        if((bookLeader != null) &&(!"".equalsIgnoreCase(bookLeader))){
            query += (condition ? " and " : " where ");
            query += " leader LIKE '%" + bookLeader + "%' " ;
            condition = true;
        }
        if((bookDate != null) &&(!"".equalsIgnoreCase(bookDate))){
            query += (condition ? " and " : " where ");
            query += " refdate = '" + bookDate + "' " ;
            condition = true;
        }
        if((landOkBy != null) &&(!"".equalsIgnoreCase(landOkBy))){
            query += (condition ? " and " : " where ");
            query += " ok_by = '" + landOkBy + "' " ;
            condition = true;
        }
        if((landAgent != null) &&(!"".equalsIgnoreCase(landAgent))){
            query += (condition ? " and " : " where ");
            query += " agent LIKE '%" + landAgent + "%' " ;
            condition = true;
        }
        if((landCategory != null) &&(!"".equalsIgnoreCase(landCategory))){
            query += (condition ? " and " : " where ");
            query += " category = '" + landCategory + "' " ;
            condition = true;
        }

        List<Object[]> QueryLand = session.createSQLQuery(query)
                .addScalar("refno", Hibernate.STRING)
                .addScalar("refdate", Hibernate.STRING)
                .addScalar("agent", Hibernate.STRING)
                .addScalar("leader", Hibernate.STRING)
                .addScalar("ok_by", Hibernate.STRING)
                .addScalar("description", Hibernate.STRING)
                .addScalar("category", Hibernate.STRING)
                .addScalar("qty", Hibernate.STRING)
                .addScalar("invoice", Hibernate.STRING)
                .addScalar("receipt", Hibernate.STRING)
                .addScalar("id", Hibernate.STRING)
                .list();
        
        for (Object[] B : QueryLand) {
            BookingLandSummaryView bookingLandSummaryView = new BookingLandSummaryView();
            bookingLandSummaryView.setRefno(B[0]== null ? "" : util.ConvertString(B[0]));
            bookingLandSummaryView.setRefdate(B[1]== null ? "" : util.ConvertString(B[1]));
            bookingLandSummaryView.setAgent(B[2]== null ? "" :util.ConvertString(B[2]));
            bookingLandSummaryView.setLeader(B[3]== null ? "" :util.ConvertString(B[3]));
            bookingLandSummaryView.setOkby(B[4]== null ? "" :util.ConvertString(B[4]));
            bookingLandSummaryView.setDescription(B[5]== null ? "" :util.ConvertString(B[5]));
            bookingLandSummaryView.setCategory(B[6]== null ? "" :util.ConvertString(B[6]));
            bookingLandSummaryView.setQty(B[7]== null ? "" :util.ConvertString(B[7]));
            bookingLandSummaryView.setInvoice(B[8]== null ? "" :util.ConvertString(B[8]));     
            bookingLandSummaryView.setReceipt(B[9]== null ? "" :util.ConvertString(B[9]));
            bookingLandSummaryView.setId(B[10]== null ? "" :util.ConvertString(B[10])); 
            bookingLandSummaryViewList.add(bookingLandSummaryView);
        }
        
        this.sessionFactory.close();
        session.close();
        return bookingLandSummaryViewList;
    }
   
}
