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
import com.smi.travel.datalayer.view.entity.ConfirmSlipDetailReport;
import com.smi.travel.datalayer.view.entity.ConfirmSlipHeaderReport;
import com.smi.travel.datalayer.view.entity.OutboundStaffSummaryReport;
import com.smi.travel.datalayer.view.entity.OutboundStaffSummarySubReport;
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
                .addScalar("departmentNo", Hibernate.STRING)
                .list();
        
        for (Object[] B : QueryStaffList) {
            booksummary.setSystemdate(String.valueOf(dateformat.format(new Date())));
            String refnum = "";
            if(B[0] != null){ 
                refnum = util.ConvertString(B[0]);
            }
            if(B[9] != null){
                refnum += "/"+util.ConvertString(B[9]);
            }
            
            booksummary.setRefno(refnum);
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

    @Override
    public ConfirmSlipHeaderReport getConfirmSlipHeaderReport(String refno,String user) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        ConfirmSlipHeaderReport confirmSlip = new ConfirmSlipHeaderReport();
        
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy hh:mm");
        
        String query = "SELECT * FROM `booking_header_summary` where  refno = '"+refno+"'";
        
        System.out.println(" query :::: " +query);
        
        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("refno", Hibernate.STRING)
                .addScalar("agent", Hibernate.STRING)
                .addScalar("leader", Hibernate.STRING)
                .addScalar("address", Hibernate.STRING)
                .addScalar("telfax", Hibernate.STRING)
                .addScalar("bookstatus", Hibernate.STRING)
                .addScalar("sale", Hibernate.STRING)
                .addScalar("firstdept", Hibernate.STRING)
                .addScalar("package", Hibernate.STRING)
                .list();

        for (Object[] B : QueryStaffList) {
            confirmSlip.setUser(user);
            System.out.println("confirmSlip " +confirmSlip.getUser());
            confirmSlip.setSystemdate(String.valueOf(dateformat.format(new Date())));
            confirmSlip.setRefno(B[0]== null ? "" :util.ConvertString(B[0]));
            confirmSlip.setAgent(B[1]== null ? "" :util.ConvertString(B[1]));
            confirmSlip.setLeader(B[2]== null ? "" :util.ConvertString(B[2]));
            confirmSlip.setAddress(B[3]== null ? "" :util.ConvertString(B[3]));
            confirmSlip.setTelfax(B[4]== null ? "" :util.ConvertString(B[4]));
            confirmSlip.setBookstatus(B[5]== null ? "" :util.ConvertString(B[5]));
            confirmSlip.setIncharge(B[6]== null ? "" :util.ConvertString(B[6]));
            confirmSlip.setFirstdept(B[7]== null ? "" :util.ConvertString(B[7]));
            confirmSlip.setPackages(B[8]== null ? "" :util.ConvertString(B[8]));
        }
        
        confirmSlip.setConfirmSlipFlightSubReportDataSource(new JRBeanCollectionDataSource(getConfirmSlipFlightSubReport(refno)));
        confirmSlip.setConfirmSlipHotelSubReportDataSource(new JRBeanCollectionDataSource(getConfirmSlipHotelSubReport(refno)));
        confirmSlip.setConfirmSlipDaytourSubReportDataSource(new JRBeanCollectionDataSource(getConfirmSlipDaytourSubReport(refno))); 
        confirmSlip.setConfirmSlipOtherSubReportDataSource(new JRBeanCollectionDataSource(getConfirmSlipOtherSubReport(refno)));
        confirmSlip.setConfirmSlipLandSubReportDataSource(new JRBeanCollectionDataSource(getConfirmSlipLandSubReport(refno)));
        confirmSlip.setConfirmSlipPassengerSubReportDataSource(new JRBeanCollectionDataSource(getConfirmSlipPassengerSubReport(refno)));
        
        this.sessionFactory.close();
        session.close();
        return confirmSlip;    
    }

    public List getConfirmSlipFlightSubReport(String refno) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        
        String query = "SELECT * FROM `airticket_booking_view` a WHERE a.refno = '"+refno+"'";

        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("airline", Hibernate.STRING)
                .addScalar("pnr", Hibernate.STRING)
                .addScalar("flight", Hibernate.STRING)
                .addScalar("dept", Hibernate.STRING)
                .addScalar("deptname", Hibernate.STRING)
                .addScalar("depart_time", Hibernate.STRING)
                .addScalar("arrv", Hibernate.STRING)
                .addScalar("arrvname", Hibernate.STRING)
                .addScalar("arrive_time", Hibernate.STRING)
                .addScalar("price", Hibernate.STRING)
                .list();
        
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd MMM yyyy");
        int order = 1 ;
        for (Object[] B : QueryStaffList) {
            ConfirmSlipDetailReport confirmdetail = new ConfirmSlipDetailReport();
            confirmdetail.setOrder(String.valueOf(order));
            order ++ ;
            
            confirmdetail.setAirline(B[0]== null ? "" :util.ConvertString(B[0]));
            confirmdetail.setPnr(B[1]== null ? "" :util.ConvertString(B[1]));
            confirmdetail.setFlight(B[2]== null ? "" :util.ConvertString(B[2]));
            confirmdetail.setDepart(B[3]== null ? "" :util.ConvertString(B[3]));
            confirmdetail.setDepartname(B[4]== null ? "" :util.ConvertString(B[4]));
            String deptime1 = util.ConvertString(B[5]).substring(0,2);
            String deptime2 = util.ConvertString(B[5]).substring(2,4);
            String arrtime1 = util.ConvertString(B[8]).substring(0,2);
            String arrtime2 = util.ConvertString(B[8]).substring(2,4);
            confirmdetail.setDeparttime(deptime1+":"+deptime2 + " - " +arrtime1+":"+arrtime2 );
            confirmdetail.setArrive(B[6]== null ? "" :util.ConvertString(B[6]));
            confirmdetail.setArrivename(B[7]== null ? "" :util.ConvertString(B[7]));

            confirmdetail.setArrivetime(B[8]== null ? "" :util.ConvertString(B[8]));
            confirmdetail.setPrice(B[9]== null ? "" :util.ConvertString(B[9]));
            data.add(confirmdetail);
        }
        this.sessionFactory.close();
        session.close();
        return data;
    }
    
    public List getConfirmSlipHotelSubReport(String refno) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();

        String query = "SELECT * FROM `hotel_booking_view` a where a.refno = '"+refno+"' ";
        
        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("name", Hibernate.STRING)
                .addScalar("checkin", Hibernate.STRING)
                .addScalar("checkout", Hibernate.STRING)
                .addScalar("room", Hibernate.STRING)
                .addScalar("sell", Hibernate.STRING)
                .addScalar("cur", Hibernate.STRING)
                .list();
        
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MMM-yyyy");
        String checkinout = "";
        int order = 1 ;
        for (Object[] B : QueryStaffList) {
            ConfirmSlipDetailReport confirmdetail = new ConfirmSlipDetailReport();
            confirmdetail.setOrder(String.valueOf(order));
            order ++ ;
            
            confirmdetail.setHotel(B[0]== null ? "" :util.ConvertString(B[0]));
            if(B[1] != null && B[2] != null){
                checkinout = util.ConvertString(dateformat.format(util.convertStringToDate(util.ConvertString(B[1]))))
                        + " - " + util.ConvertString(dateformat.format(util.convertStringToDate(util.ConvertString(B[2]))));
            }
            
            confirmdetail.setCheckinout(checkinout);
            confirmdetail.setCategory(B[3]== null ? "" :util.ConvertString(B[3]));
            confirmdetail.setAmount(B[4]== null ? "" :util.ConvertString(B[4]));
            confirmdetail.setCuramount(B[5]== null ? "" :util.ConvertString(B[5]));
            data.add(confirmdetail);
        }
        
        String queryAdditional = "SELECT * FROM `hotel_booking_additional_view` a where a.refno = '"+refno+"' ";
        
        List<Object[]> QueryList = session.createSQLQuery(queryAdditional)
                .addScalar("description", Hibernate.STRING)
                .addScalar("add_price", Hibernate.STRING)
                .addScalar("refno", Hibernate.STRING)
                .list();
        
        for (Object[] B : QueryList) {
            ConfirmSlipDetailReport confirmdetail = new ConfirmSlipDetailReport();
            confirmdetail.setOrder(String.valueOf(order));
            order ++ ;
            confirmdetail.setHotel(B[0]== null ? "" :util.ConvertString(B[0]));
            confirmdetail.setAmount(B[1]== null ? "" :util.ConvertString(B[1]));
            confirmdetail.setCuramount("THB");
            data.add(confirmdetail);
        }

        this.sessionFactory.close();
        session.close();
        return data;
    }
    
    public List getConfirmSlipDaytourSubReport(String refno) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        
        String query = "SELECT * FROM `daytour_booking_view` WHERE refno = '"+refno+"'";
        
        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("code", Hibernate.STRING)
                .addScalar("name", Hibernate.STRING)
                .addScalar("tour_date", Hibernate.STRING)
                .addScalar("qty", Hibernate.STRING)
                .addScalar("total", Hibernate.STRING)
                .addScalar("curamount", Hibernate.STRING)
                .addScalar("pickup", Hibernate.STRING)
                .addScalar("pickup_time", Hibernate.STRING)
                .addScalar("guide", Hibernate.STRING)
                .addScalar("carno", Hibernate.STRING)
                .list();
        
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy");
        
        int order = 1 ;
        for (Object[] B : QueryStaffList) {
            ConfirmSlipDetailReport confirmdetail = new ConfirmSlipDetailReport();
            confirmdetail.setOrder(String.valueOf(order));
            order ++ ;
            
            confirmdetail.setCode(B[0]== null ? "" :util.ConvertString(B[0]));
            confirmdetail.setDescription(B[1]== null ? "" :util.ConvertString(B[1]));
            
            String datetime = "" ;
            if(B[2] != null){
                datetime = util.ConvertString(dateformat.format(util.convertStringToDate(util.ConvertString(B[2]))));
            }

            confirmdetail.setDate(datetime);
            confirmdetail.setConfirmQty(B[3]== null ? "" :util.ConvertString(B[3]));
            confirmdetail.setAmount(B[4]== null ? "" :util.ConvertString(B[4]));
            confirmdetail.setCuramount(B[5]== null ? "" :util.ConvertString(B[5]));
            confirmdetail.setPickup(B[6]== null ? "" :util.ConvertString(B[6]));
            confirmdetail.setPickuptime(B[7]== null ? "" :util.ConvertString(B[7]));
            confirmdetail.setGuide(B[8]== null ? "" :util.ConvertString(B[8]));
            confirmdetail.setCarno(B[9]== null ? "" :util.ConvertString(B[9]));
            data.add(confirmdetail);
        }
        
        this.sessionFactory.close();
        session.close();
        return data;
    }
    
    public List getConfirmSlipOtherSubReport(String refno) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        
        String query = "SELECT * FROM `other_booking_view` WHERE refno = '"+refno+"'";
        
        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("code", Hibernate.STRING)
                .addScalar("name", Hibernate.STRING)
                .addScalar("other_date", Hibernate.STRING)
                .addScalar("other_time", Hibernate.STRING)
                .addScalar("qty", Hibernate.STRING)
                .addScalar("sell", Hibernate.STRING)
                .addScalar("cur_amount", Hibernate.STRING)
                .list();
        
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy");
        
         for (Object[] B : QueryStaffList) {
            ConfirmSlipDetailReport confirmdetail = new ConfirmSlipDetailReport();
            confirmdetail.setCode(B[0]== null ? "" :util.ConvertString(B[0]));
            confirmdetail.setDescription(B[1]== null ? "" :util.ConvertString(B[1]));
            String datetime = "" ;
            if(B[2] != null){
                datetime = util.ConvertString(dateformat.format(util.convertStringToDate(util.ConvertString(B[2]))));
            }
            if(B[3] != null){
                datetime +=  " " + util.ConvertString(B[3]);
            }
            confirmdetail.setOtherdate(datetime);
            confirmdetail.setQty(B[4]== null ? "" :util.ConvertString(B[4]));
            confirmdetail.setTotalsell(B[5]== null ? "" :util.ConvertString(B[5]));
            confirmdetail.setCuramount(B[6]== null ? "" :util.ConvertString(B[6]));
            data.add(confirmdetail);
        }
        
        this.sessionFactory.close();
        session.close();
        return data;
    }
    public List getConfirmSlipLandSubReport(String refno) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        
        String query = "SELECT * FROM `land_booking_view` where refno = '"+refno+"'";
        
        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("category", Hibernate.STRING)
                .addScalar("okby", Hibernate.STRING)
                .addScalar("sell", Hibernate.STRING)
                .addScalar("cur_amount", Hibernate.STRING)
                .list();
        
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd MMM yyyy");
        
        for (Object[] B : QueryStaffList) {
            ConfirmSlipDetailReport confirmdetail = new ConfirmSlipDetailReport();
            confirmdetail.setCategory(B[0]== null ? "" :util.ConvertString(B[0]));
            confirmdetail.setOkby(B[1]== null ? "" :util.ConvertString(B[1]));
            confirmdetail.setSell(B[2]== null ? "" : util.ConvertString(B[2]));
            confirmdetail.setCursell(B[3]== null ? "" :util.ConvertString(B[3]));
            data.add(confirmdetail);
        }
        
        this.sessionFactory.close();
        session.close();
        return data;
    }
    public List getConfirmSlipPassengerSubReport(String refno) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        
        
        String query = "SELECT * FROM `booking_passenger_view` where refno = '"+refno+"'";
        
        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("tel", Hibernate.STRING)
                .addScalar("name", Hibernate.STRING)
                .addScalar("age", Hibernate.STRING)
                .addScalar("isleader", Hibernate.STRING)
                .list();
        
        int order = 1 ;
        for (Object[] B : QueryStaffList) {
            ConfirmSlipDetailReport confirmdetail = new ConfirmSlipDetailReport();
            confirmdetail.setOrder(String.valueOf(order));
            order ++ ;
            confirmdetail.setTel(B[0]== null ? "" :util.ConvertString(B[0]));
            confirmdetail.setName(B[1]== null ? "" :util.ConvertString(B[1]));
            confirmdetail.setAge(B[2]== null ? "" : util.ConvertString(B[2]));
            confirmdetail.setLeader(B[3]== null ? "" : util.ConvertString(B[3]));
            data.add(confirmdetail);
        }
        this.sessionFactory.close();
        session.close();
        return data;
    }

    @Override
    public OutboundStaffSummaryReport getOutboundStaffSummaryReport(String from, String to, String saleby, String currency, String detail, String user) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy hh:mm");
        
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("dd MMM yyyy");
        
        OutboundStaffSummaryReport ossr = new OutboundStaffSummaryReport();
        
        String fromto = "";
        if(!"".equalsIgnoreCase(from) && !"".equalsIgnoreCase(to)){
            fromto = String.valueOf(df.format(util.convertStringToDate(from))) 
                    + " to " + String.valueOf(df.format(util.convertStringToDate(to))) ;
        }
        ossr.setUser(user);
        ossr.setSystemdate(String.valueOf(dateformat.format(new Date())));
        ossr.setFromto(fromto);

        ossr.setOutboundStaffSummaryListReportDataSource(new JRBeanCollectionDataSource(getOutboundStaffSummaryList(from,to,saleby,currency)));
        
        if("1".equalsIgnoreCase(detail)){
            ossr.setOutboundStaffSummaryDetailReportDataSource(new JRBeanCollectionDataSource(getOutboundStaffSummaryDetail(from,to,saleby,currency)));
        }else{
            ossr.setOutboundStaffSummaryDetailReportDataSource(null);
        }
        
        this.sessionFactory.close();
        session.close();
        return ossr;        
    }
    
    public List getOutboundStaffSummaryList(String from, String to, String saleby, String currency) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        
        String query = "SELECT st.`name` AS staff, pt. NAME AS productType, sum(bd.cost) AS cost, sum(bd.price) AS price, sum(bd.price - bd.cost) AS profit FROM `billable` bi INNER JOIN billable_desc bd ON bi.id = bd.billable_id INNER JOIN `master` mt ON mt.id = bi.master_id INNER JOIN m_billtype pt ON pt.id = bd.bill_type INNER JOIN staff st ON st.id = mt.Staff_id WHERE mt.booking_type = 'O' and mt.Create_date BETWEEN '"+from+"' and  '"+to+"' ";
        
        if(saleby != null && !"".equalsIgnoreCase(saleby)){
            query += " and st.id = '"+saleby+"'";
        }
        
        if(currency != null && !"".equalsIgnoreCase(currency)){
            query += " and bd.currency = '"+currency+"'";
        }
        
        query += " GROUP BY mt.Staff_id , pt.id ORDER BY st.`name`";

        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("staff", Hibernate.STRING)
                .addScalar("productType", Hibernate.STRING)
                .addScalar("cost", Hibernate.STRING)
                .addScalar("price", Hibernate.STRING)
                .addScalar("profit", Hibernate.STRING)
                .list();
        
        for (Object[] B : QueryStaffList) {
            OutboundStaffSummarySubReport osssr = new OutboundStaffSummarySubReport();
            osssr.setStaff(B[0]== null ? "" :util.ConvertString(B[0]));
            osssr.setProducttype(B[1]== null ? "" :util.ConvertString(B[1]));
            osssr.setNet(B[2]== null ? "" : util.ConvertString(B[2]));
            osssr.setSale(B[3]== null ? "" : util.ConvertString(B[3]));
            osssr.setProfit(B[4]== null ? "" : util.ConvertString(B[4]));
            data.add(osssr);
        }
        
        this.sessionFactory.close();
        session.close();
        return data;
    }
     
    public List getOutboundStaffSummaryDetail(String from, String to, String saleby, String currency) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        
        String query = "SELECT * FROM `outbound_staff_detail` where bookdate BETWEEN '"+from+"' and  '"+to+"' ";
        
        if(saleby != null && !"".equalsIgnoreCase(saleby)){
            query += " and staffid = '"+saleby+"'";
        }
        
        if(currency != null && !"".equalsIgnoreCase(currency)){
            query += " and currency = '"+currency+"'";
        }
        
        query += " ORDER BY staff , departmentno ";
        
        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("staff", Hibernate.STRING)
                .addScalar("productType", Hibernate.STRING)
                .addScalar("productname", Hibernate.STRING)
                .addScalar("cost", Hibernate.STRING)
                .addScalar("price", Hibernate.STRING)
                .addScalar("profit", Hibernate.STRING)
                .addScalar("departmentno", Hibernate.STRING)
                .list();

        for (Object[] B : QueryStaffList) {
            OutboundStaffSummarySubReport osssr = new OutboundStaffSummarySubReport();
            osssr.setStaff(B[0]== null ? "" :util.ConvertString(B[0]));
            osssr.setType(B[1]== null ? "" :util.ConvertString(B[1]));
            osssr.setName(B[2]== null ? "" : util.ConvertString(B[2]));
            osssr.setNet(B[3]== null ? "" : util.ConvertString(B[3]));
            osssr.setSale(B[4]== null ? "" : util.ConvertString(B[4]));
            osssr.setProfit(B[5]== null ? "" : util.ConvertString(B[5]));
            osssr.setDepartmentno(B[6]== null ? "" : util.ConvertString(B[6]));
            data.add(osssr);
        }
        this.sessionFactory.close();
        session.close();
        return data;
    }
    
}