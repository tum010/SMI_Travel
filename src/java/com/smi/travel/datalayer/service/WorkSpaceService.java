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
import com.smi.travel.datalayer.view.entity.BookingHotelSummaryView;
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
}
