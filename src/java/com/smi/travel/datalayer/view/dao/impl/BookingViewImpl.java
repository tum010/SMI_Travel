/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.entity.InvoiceDetail;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.view.dao.BookingViewDao;
import com.smi.travel.datalayer.view.entity.BookingHotelSummaryView;
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
        
        if((!"".equalsIgnoreCase(bookRefNo)) && (bookRefNo != null)){
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
        if((hotelName != null) &&(!"".equalsIgnoreCase(hotelName))){
            query += (condition ? " and " : " where ");
            query += " hotel LIKE '%" + hotelName + "%' " ;
            condition = true;
        }
        if((hotelCheckIn != null) &&(!"".equalsIgnoreCase(hotelCheckIn))){
            query += (condition ? " and " : " where ");
            query += " checkin = '" + hotelCheckIn + "' " ;
            condition = true;
        }
        if((hotelCheckOut != null) &&(!"".equalsIgnoreCase(hotelCheckOut))){
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
            bookingHotelSummaryViewList.add(bookingHotelSummaryView);
        }
        
        this.sessionFactory.close();
        session.close();
        return bookingHotelSummaryViewList;
    }
   
}
