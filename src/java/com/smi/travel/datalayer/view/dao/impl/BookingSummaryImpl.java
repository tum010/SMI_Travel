/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.view.dao.BookingSummaryDao;
import com.smi.travel.datalayer.view.entity.BookSummary;
import com.smi.travel.datalayer.view.entity.BookingHeaderSummaryView;
import com.smi.travel.datalayer.view.entity.BookingSummaryDetailView;
import com.smi.travel.datalayer.view.entity.OtherMonthlyView;
import com.smi.travel.util.UtilityFunction;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Surachai
 */
public class BookingSummaryImpl implements BookingSummaryDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;
    private UtilityFunction utilityFunction;
    
    @Override
    public List<BookSummary> getListBookSummary(String refno) {
        Session session = this.sessionFactory.openSession();
        List<Object[]> QueryList =  session.createSQLQuery("SELECT * FROM `booking_summary_view` where ref_no = "+refno)
                .addScalar("type",Hibernate.STRING)
                .addScalar("description",Hibernate.STRING)
                .addScalar("date_tour",Hibernate.DATE)
                .addScalar("price",Hibernate.INTEGER)
                .addScalar("book_date",Hibernate.DATE)
                .list();
                
        List<BookSummary> BookSummaryList =  new LinkedList<BookSummary>();
        for(Object[] B : QueryList){
            BookSummary book = new BookSummary();
            book.setRefNo(refno);
            book.setType(String.valueOf(B[0]));
            book.setDescription(String.valueOf(B[1]));
            book.setDateTour((Date) B[2]);
            book.setPrice(String.valueOf(B[3]));
            book.setBookdate((Date) B[4]);
            BookSummaryList.add(book);
            
        }
       
        if (BookSummaryList.isEmpty()) {
            return null;
        }
        session.close();
        return BookSummaryList;
    }
    
    @Override
    public BookingHeaderSummaryView getBookingSummaryReport(String refno) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        BookingHeaderSummaryView booksummary = new BookingHeaderSummaryView();
        
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MMM-yyyy hh:mm");
        
        String query = "SELECT * FROM `booking_header_summary` where  refno = '"+refno+"'";
        
        System.out.println(" query :::: " +query);
        
        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("refno", Hibernate.STRING)
                .addScalar("agent", Hibernate.STRING)
                .addScalar("leader", Hibernate.STRING)
                .addScalar("pax", Hibernate.STRING)
                .addScalar("sale", Hibernate.STRING)
                .addScalar("billname", Hibernate.STRING)
                .addScalar("address", Hibernate.STRING)
                .addScalar("termpay", Hibernate.STRING)
                .addScalar("payby", Hibernate.STRING)
                .list();
        
        for (Object[] B : QueryStaffList) {
            booksummary.setSystemdate(String.valueOf(dateformat.format(new Date())));
            booksummary.setRefno(B[0]== null ? "" :util.ConvertString(B[0]));
            booksummary.setAgent(B[1]== null ? "" :util.ConvertString(B[1]));
            booksummary.setLeader(B[2]== null ? "" :util.ConvertString(B[2]));
            booksummary.setPax(B[3]== null ? "" :util.ConvertString(B[3]));
            booksummary.setSale(B[4]== null ? "" :util.ConvertString(B[4]));
            booksummary.setBillto(B[5]== null ? "" :util.ConvertString(B[5]));
            booksummary.setAddress(B[6]== null ? "" :util.ConvertString(B[6]));
            booksummary.setTermpay(B[7]== null ? "" :util.ConvertString(B[7]));
            booksummary.setPayby(B[8]== null ? "" :util.ConvertString(B[8]));
        }
        
        booksummary.setBookingSummaryFlightSubReportDataSource(new JRBeanCollectionDataSource(getBookingSummaryFlightSubReport(refno)));
        booksummary.setBookingSummaryHotelSubReportDataSource(new JRBeanCollectionDataSource(getBookingSummaryHotelSubReport(refno)));
        booksummary.setBookingSummaryOtherSubReportDataSource(new JRBeanCollectionDataSource(getBookingSummaryOtherSubReport(refno))); //package
        booksummary.setBookingSummaryLandSubReportDataSource(new JRBeanCollectionDataSource(getBookingSummaryLandSubReport(refno)));
        booksummary.setBookingSummaryPassengerSubReportDataSource(new JRBeanCollectionDataSource(getBookingSummaryPassengerSubReport(refno)));

        this.sessionFactory.close();
        session.close();
        return booksummary;
    }
    
    public List getBookingSummaryFlightSubReport(String refno) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        
        String query = "SELECT * FROM `booking_air_summary` a JOIN booking_air_value_summary b ON a.refno = b.ref_no WHERE a.refno = '"+refno+"'";
        
        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("flight", Hibernate.STRING)
                .addScalar("dept", Hibernate.STRING)
                .addScalar("depart_date", Hibernate.STRING)
                .addScalar("depart_time", Hibernate.STRING)
                .addScalar("arrv", Hibernate.STRING)
                .addScalar("arrive_date", Hibernate.STRING)
                .addScalar("arrive_time", Hibernate.STRING)
                .addScalar("class", Hibernate.STRING)
                .addScalar("cost", Hibernate.STRING)
                .addScalar("cost_tax", Hibernate.STRING)
                .addScalar("cur_cost", Hibernate.STRING)
                .addScalar("price", Hibernate.STRING)
                .addScalar("price_tax", Hibernate.STRING)
                .addScalar("cur_amount", Hibernate.STRING)
                .list();
        
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd MMM yyyy");
        
        for (Object[] B : QueryStaffList) {
            BookingSummaryDetailView booksumdetail = new BookingSummaryDetailView();
            booksumdetail.setFlight(B[0]== null ? "" :util.ConvertString(B[0]));
            booksumdetail.setDepart(B[1]== null ? "" :util.ConvertString(B[1]));
            booksumdetail.setDepartdate(B[2]== null ? "" : util.ConvertString(dateformat.format(util.convertStringToDate(util.ConvertString(B[2])))));
            booksumdetail.setDeparttime(B[3]== null ? "" :util.ConvertString(B[3]));
            booksumdetail.setArrive(B[4]== null ? "" :util.ConvertString(B[4]));
            booksumdetail.setArrivedate(B[5]== null ? "" :util.ConvertString(dateformat.format(util.convertStringToDate(util.ConvertString(B[5])))));
            booksumdetail.setArrivetime(B[6]== null ? "" :util.ConvertString(B[6]));
            booksumdetail.setFlightclass(B[7]== null ? "" :util.ConvertString(B[7]));
            booksumdetail.setCost(B[8]== null ? "" :util.ConvertString(B[8]));
            booksumdetail.setCosttax(B[9]== null ? "" :util.ConvertString(B[9]));
            booksumdetail.setCurcost(B[10]== null ? "" :util.ConvertString(B[10]));
            booksumdetail.setPrice(B[11]== null ? "" :util.ConvertString(B[11]));
            booksumdetail.setPricetax(B[12]== null ? "" :util.ConvertString(B[12]));
            booksumdetail.setCuramount(B[13]== null ? "" :util.ConvertString(B[13]));
            data.add(booksumdetail);
        }
        this.sessionFactory.close();
        session.close();
        return data;
    }
    
    public List getBookingSummaryHotelSubReport(String refno) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();

        String query = "SELECT * FROM `booking_hotel_summary` where refno = '"+refno+"'";
        
        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("hotel", Hibernate.STRING)
                .addScalar("checkin", Hibernate.STRING)
                .addScalar("checkout", Hibernate.STRING)
                .addScalar("night", Hibernate.STRING)
                .addScalar("Total_cost", Hibernate.STRING)
                .addScalar("curcost", Hibernate.STRING)
                .addScalar("Total_price", Hibernate.STRING)
                .addScalar("curamount", Hibernate.STRING)
                .list();
        
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd MMM yyyy");
        
        for (Object[] B : QueryStaffList) {
            BookingSummaryDetailView booksumdetail = new BookingSummaryDetailView();
            String checkinout = "" ;
            booksumdetail.setHotel(B[0]== null ? "" :util.ConvertString(B[0]));
            if(B[1] != null && B[2] != null){
                checkinout = util.ConvertString(dateformat.format(util.convertStringToDate(util.ConvertString(B[1]))))
                        + " - " + util.ConvertString(dateformat.format(util.convertStringToDate(util.ConvertString(B[2]))));
            }
            booksumdetail.setCheckinout(checkinout);
            booksumdetail.setNight(B[3]== null ? "" :util.ConvertString(B[3]));
            booksumdetail.setTotalcost(B[4]== null ? "" :util.ConvertString(B[4]));
            booksumdetail.setCurcost(B[5]== null ? "" :util.ConvertString(B[5]));
            booksumdetail.setTotalprice(B[6]== null ? "" :util.ConvertString(B[6]));
            booksumdetail.setCuramount(B[7]== null ? "" :util.ConvertString(B[7]));
            data.add(booksumdetail);
        }
        this.sessionFactory.close();
        session.close();
        return data;
    }
    
    public List getBookingSummaryOtherSubReport(String refno) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        
        String query = "SELECT * FROM `other_booking_view` WHERE refno = '"+refno+"'";
        
        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("name", Hibernate.STRING)
                .addScalar("other_date", Hibernate.STRING)
                .addScalar("other_time", Hibernate.STRING)
                .addScalar("adult", Hibernate.STRING)
                .addScalar("child", Hibernate.STRING)
                .addScalar("infant", Hibernate.STRING)
                .addScalar("net", Hibernate.STRING)
                .addScalar("cur_cost", Hibernate.STRING)
                .addScalar("sell", Hibernate.STRING)
                .addScalar("cur_amount", Hibernate.STRING)
                .list();
        
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd MMM yyyy");
        
        for (Object[] B : QueryStaffList) {
            BookingSummaryDetailView booksumdetail = new BookingSummaryDetailView();
            booksumdetail.setName(B[0]== null ? "" :util.ConvertString(B[0]));
            String datetime = "" ;
            if(B[1] != null){
                datetime = util.ConvertString(dateformat.format(util.convertStringToDate(util.ConvertString(B[1]))));
            }
            if(B[2] != null){
                datetime +=  " " + util.ConvertString(B[2]);
            }
            booksumdetail.setOtherdatetime(datetime);
            booksumdetail.setAdult(B[3]== null ? "" :util.ConvertString(B[3]));
            booksumdetail.setChild(B[4]== null ? "" :util.ConvertString(B[4]));
            booksumdetail.setInfant(B[5]== null ? "" :util.ConvertString(B[5]));
            booksumdetail.setNet(B[6]== null ? "" :util.ConvertString(B[6]));
            booksumdetail.setCurcost(B[7]== null ? "" :util.ConvertString(B[7]));
            booksumdetail.setSell(B[8]== null ? "" :util.ConvertString(B[8]));
            booksumdetail.setCuramount(B[9]== null ? "" :util.ConvertString(B[9]));
            data.add(booksumdetail);
        }
        
        this.sessionFactory.close();
        session.close();
        return data;
    }
    public List getBookingSummaryLandSubReport(String refno) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        
        String query = "SELECT * FROM `land_booking_view` where refno = '"+refno+"'";
        
        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("agent", Hibernate.STRING)
                .addScalar("category", Hibernate.STRING)
                .addScalar("description", Hibernate.STRING)
                .addScalar("net", Hibernate.STRING)
                .addScalar("cur_cost", Hibernate.STRING)
                .addScalar("sell", Hibernate.STRING)
                .addScalar("cur_amount", Hibernate.STRING)
                .list();
        
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd MMM yyyy");
        
        for (Object[] B : QueryStaffList) {
            BookingSummaryDetailView booksumdetail = new BookingSummaryDetailView();
            booksumdetail.setAgent(B[0]== null ? "" :util.ConvertString(B[0]));
            booksumdetail.setCategory(B[1]== null ? "" :util.ConvertString(B[1]));
            booksumdetail.setDescription(B[2]== null ? "" : util.ConvertString(B[2]));
            booksumdetail.setNet(B[3]== null ? "" :util.ConvertString(B[3]));
            booksumdetail.setCurcost(B[4]== null ? "" :util.ConvertString(B[4]));
            booksumdetail.setSell(B[5]== null ? "" :util.ConvertString(B[5]));
            booksumdetail.setCuramount(B[6]== null ? "" :util.ConvertString(B[6]));
            data.add(booksumdetail);
        }
        
        this.sessionFactory.close();
        session.close();
        return data;
    }
    public List getBookingSummaryPassengerSubReport(String refno) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        
        
        String query = "SELECT * FROM `booking_passenger_view` where refno = '"+refno+"'";
        
        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("name", Hibernate.STRING)
                .addScalar("age", Hibernate.STRING)
                .addScalar("remark", Hibernate.STRING)
                .list();
        
        int no = 1 ;
        for (Object[] B : QueryStaffList) {
            BookingSummaryDetailView booksumdetail = new BookingSummaryDetailView();
            booksumdetail.setNo(String.valueOf(no));
            booksumdetail.setName(B[0]== null ? "" :util.ConvertString(B[0]));
            booksumdetail.setAge(B[1]== null ? "" :util.ConvertString(B[1]));
            booksumdetail.setRemark(B[2]== null ? "" : util.ConvertString(B[2]));
            data.add(booksumdetail);
            no++; 
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
