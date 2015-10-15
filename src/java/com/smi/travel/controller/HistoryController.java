/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.AirticketBooking;
import com.smi.travel.datalayer.entity.AirticketPnr;
import com.smi.travel.datalayer.entity.HistoryBooking;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.BookingAirticketService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.MainBooking;
import com.smi.travel.master.controller.SMITravelController;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author sumeta
 */
public class HistoryController extends SMITravelController {

    private static final ModelAndView History = new ModelAndView("History");
    private static final ModelAndView History_REFRESH = new ModelAndView(new RedirectView("History.smi", true));
    private BookingAirticketService bookingAirticketService;
    private static final String Bookiing_Size = "BookingSize";
    private UtilityService utilservice;

    private static final String ACTION = "action";
    private static final String Master = "Master";
    private static final String HistoryBookingList = "HistoryBookingList";
    private static final String Staff_List = "Staff_List";
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        String refNo = request.getParameter("referenceNo");
        String action = request.getParameter("action");
        request.setAttribute("action", action);
        System.out.println("HistoryController action = "+action + " refNo = "+refNo);

        if ("add".equalsIgnoreCase(action)) {
            System.out.println("action add");
            request.setAttribute(ACTION, "insert");
        } else if ("insert".equalsIgnoreCase(action)) {
            System.out.println("action insert");
        } else if ("edit".equalsIgnoreCase(action)) {
            request.setAttribute("action", action);
            setResponseAttribute(request, refNo);
        } else if ("update".equalsIgnoreCase(action)) {
            System.out.println("action update");
        } else if ("delete".equalsIgnoreCase(action)) {
        } else {
            System.out.println("no action");
            if ("".equalsIgnoreCase(refNo) || (refNo == null)) {
                System.out.println("******* No refNo !!!!");
            } else {
                System.out.println("******* have refNo ************");
                List<HistoryBooking> historyBookings = utilservice.getHistoryFromRefno(refNo);
                request.setAttribute(HistoryBookingList, historyBookings);
                Master master = utilservice.getMasterdao().getBookingFromRefno(refNo);
                request.setAttribute(Master, master);
                int[] booksize = utilservice.getCountItemFromBooking(refNo);
                request.setAttribute(Bookiing_Size, booksize);
                List<SystemUser> userList = utilservice.getUserList();
                request.setAttribute(Staff_List,userList);
            }
        }

        return History;
    }

    public void setResponseAttribute(HttpServletRequest request, String refNo) {
        int[] booksize = utilservice.getCountItemFromBooking(refNo);
        request.setAttribute(Bookiing_Size, booksize);
        Master master = utilservice.getMasterdao().getBookingFromRefno(refNo);
        request.setAttribute(Master, master);
        List<HistoryBooking> historyBookings = utilservice.getHistoryFromRefno(refNo);
        request.setAttribute(HistoryBookingList, historyBookings);
        List<SystemUser> userList = utilservice.getUserList();
        request.setAttribute(Staff_List,userList);
    }

    public BookingAirticketService getBookingAirticketService() {
        return bookingAirticketService;
    }

    public void setBookingAirticketService(BookingAirticketService bookingAirticketService) {
        this.bookingAirticketService = bookingAirticketService;
    }

    public UtilityService getUtilservice() {
        return utilservice;
    }

    public void setUtilservice(UtilityService utilservice) {
        this.utilservice = utilservice;
    }

}
