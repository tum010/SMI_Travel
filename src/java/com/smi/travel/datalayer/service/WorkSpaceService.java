/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.MListItemDao;
import com.smi.travel.datalayer.dao.MasterDao;
import com.smi.travel.datalayer.entity.MBookingstatus;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.view.dao.BookingViewDao;
import com.smi.travel.datalayer.view.entity.BookingAirSummaryView;
import com.smi.travel.datalayer.view.entity.BookingDayTourSummaryView;
import com.smi.travel.datalayer.view.entity.BookingHotelSummaryView;
import com.smi.travel.datalayer.view.entity.BookingLandSummaryView;
import com.smi.travel.datalayer.view.entity.BookingOtherSummaryView;
import com.smi.travel.datalayer.view.entity.BookingPackageSummaryView;
import com.smi.travel.datalayer.view.entity.BookingView;
import java.util.List;

/**
 *
 * @author Surachai
 */
public class WorkSpaceService {
    private MasterDao masterDao;
    private BookingViewDao bookingviewdao;
    private MListItemDao listitemdao;
    
    public List<BookingView> getListBooking(String refno,String passFirst,String passLast,String username,String departmentID,String Bookdate,String status,String pnr,String ticketNo,String payBy,String bankTransfer,String transferDateFrom,String transferDateTo){
        return bookingviewdao.getBookingList(refno, passFirst, passLast, username, departmentID, Bookdate, status, pnr, ticketNo, payBy, bankTransfer, transferDateFrom, transferDateTo);
    }
    
    public List<MBookingstatus> getListBookStatus(){
        return listitemdao.getListMBookingstatus();
    }

    public MasterDao getMasterDao() {
        return masterDao;
    }

    public void setMasterDao(MasterDao masterDao) {
        this.masterDao = masterDao;
    }

    public BookingViewDao getBookingviewdao() {
        return bookingviewdao;
    }

    public void setBookingviewdao(BookingViewDao bookingviewdao) {
        this.bookingviewdao = bookingviewdao;
    }

    public MListItemDao getListitemdao() {
        return listitemdao;
    }

    public void setListitemdao(MListItemDao listitemdao) {
        this.listitemdao = listitemdao;
    }

    public int cancelBook(String refNoEdit) {
        return bookingviewdao.cancelBook(refNoEdit);
    }

    public String checkBook(String refNoEdit) {
        return bookingviewdao.checkBook(refNoEdit);
    }   

    public int enableBook(String refNoEdit) {
        return bookingviewdao.enableBook(refNoEdit);
    }

    public List<BookingHotelSummaryView> getListBookingHotelSummaryView(String bookRefNo, String bookLeader, String bookDate, String hotelName, String hotelCheckIn, String hotelCheckOut) {
        return bookingviewdao.getListBookingHotelSummaryView(bookRefNo, bookLeader, bookDate, hotelName, hotelCheckIn, hotelCheckOut);
    }

    public List<BookingAirSummaryView> getListBookingAirSummaryView(String bookRefNo, String bookLeader, String bookDate, String airPnr, String airDeptDate, String airFlight) {
        return bookingviewdao.getListBookingAirSummaryView(bookRefNo, bookLeader, bookDate, airPnr, airDeptDate, airFlight);
    }

    public List<BookingPackageSummaryView> getListBookingPackageSummaryView(String bookRefNo, String bookLeader, String bookDate, String packageName, String packageAgent) {
        return bookingviewdao.getListBookingPackageSummaryView(bookRefNo, bookLeader, bookDate, packageName, packageAgent);
    }

    public List<BookingDayTourSummaryView> getListBookingDayTourSummaryView(String bookRefNo, String bookLeader, String bookDate, String tourCode, String tourName, String tourDate, String tourPickUp) {
        return bookingviewdao.getListBookingDayTourSummaryView(bookRefNo, bookLeader, bookDate, tourCode, tourName, tourDate, tourPickUp);
    }

    public List<BookingOtherSummaryView> getListBookingOtherSummaryView(String bookRefNo, String bookLeader, String bookDate, String otherCode, String otherName, String otherDate, String otherAgent) {
        return bookingviewdao.getListBookingOtherSummaryView(bookRefNo, bookLeader, bookDate, otherCode, otherName, otherDate, otherAgent);
    }

    public List<BookingLandSummaryView> getListBookingLandSummaryView(String bookRefNo, String bookLeader, String bookDate, String landOkBy, String landAgent, String landCategory) {
        return bookingviewdao.getListBookingLandSummaryView(bookRefNo, bookLeader, bookDate, landOkBy, landAgent, landCategory);
    }
}
