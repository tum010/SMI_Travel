/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao;

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
public interface BookingViewDao {
    public List<BookingView> getBookingList(String refno,String passFirst,String passLast,String username,String departmentID,String Bookdate,String status,String pnr,String ticketNo,String payBy,String bankTransfer,String transferDateFrom,String transferDateTo);
    public int cancelBook(String refNoEdit);
    public String checkBook(String refNoEdit);
    public int enableBook(String refNoEdit);
    public List<BookingHotelSummaryView> getListBookingHotelSummaryView(String bookRefNo, String bookLeader, String bookDate, String hotelName, String hotelCheckIn, String hotelCheckOut);
    public List<BookingAirSummaryView> getListBookingAirSummaryView(String bookRefNo, String bookLeader, String bookDate, String airPnr, String airDeptDate, String airFlight);
    public List<BookingPackageSummaryView> getListBookingPackageSummaryView(String bookRefNo, String bookLeader, String bookDate, String packageName, String packageAgent);
    public List<BookingDayTourSummaryView> getListBookingDayTourSummaryView(String bookRefNo, String bookLeader, String bookDate, String tourCode, String tourName, String tourDate, String tourPickUp);
    public List<BookingOtherSummaryView> getListBookingOtherSummaryView(String bookRefNo, String bookLeader, String bookDate, String otherCode, String otherName, String otherDate, String otherAgent);
    public List<BookingLandSummaryView> getListBookingLandSummaryView(String bookRefNo, String bookLeader, String bookDate, String landOkBy, String landAgent, String landCategory);
}
