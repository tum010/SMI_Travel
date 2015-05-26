/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.AirticketBooking;
import com.smi.travel.datalayer.entity.AirticketPnr;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.BookingAirticketService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.service.MStaffService;
import com.smi.travel.master.controller.SMITravelController;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author sumeta
 */
public class HistoryDetailController extends SMITravelController {

    private static final ModelAndView HistoryDetail = new ModelAndView("HistoryDetail");
    private static final ModelAndView HistoryDetail_REFRESH = new ModelAndView(new RedirectView("HistoryDetail.smi", true));
    private BookingAirticketService bookingAirticketService;
    private static final String Bookiing_Size = "BookingSize";
    private static final String StaffList = "StaffList";
    private UtilityService utilservice;
    private MStaffService mStaffService;
    private static final String Master = "Master";

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        System.out.println("HistoryDetailController");

        String refNo = request.getParameter("referenceNo");
        String action = request.getParameter("action");
        String id = request.getParameter("id");

        int result = 0;

        //History history = new History();
        if ("add".equalsIgnoreCase(action)) {
            System.out.println("action add ");
            //result = hotelBookingService.insertHotel(hotelBooking);
            // return new ModelAndView("redirect:History.smi?referenceNo=" + refNo + "&result=" + result);
        } else if ("insert".equalsIgnoreCase(action)) {
            System.out.println("acion insert");
            //result = hotelBookingService.updateHotel(hotelBooking);
            //return new ModelAndView("redirect:HistoryDetail.smi?referenceNo=" + refNo + "&id=" + id + "&action=edit&result=" + result);
        } else if ("edit".equalsIgnoreCase(action)) {
            System.out.println("action edit");
            SystemUser systemUser = new SystemUser();
            List<SystemUser> systemUserList = mStaffService.searchSystemUser(systemUser, 1);
            request.setAttribute(StaffList, systemUserList);
            setResponseAttribute(request, refNo);
        } else if ("update".equalsIgnoreCase(action)) {
            System.out.println("action update");
            //result = hotelBookingService.updateHotel(hotelBooking);
            //return new ModelAndView("redirect:HistoryDetail.smi?referenceNo=" + refNo + "&id=" + id + "&action=edit&result=" + result);
        } else if ("delete".equalsIgnoreCase(action)) {
            System.out.println("action delete");
            //return new ModelAndView("redirect:History.smi?referenceNo=" + refNo + "&result=" + result);
        } else {
            System.out.println("no action");
            if ("".equalsIgnoreCase(refNo) || (refNo == null)) {
                System.out.println("******* No refNo ****** !!!!");
            } else {
                System.out.println("******* have refNo ************");

            }
        }
        return HistoryDetail;
    }

    public void setResponseAttribute(HttpServletRequest request, String refNo) {
        int[] booksize = utilservice.getCountItemFromBooking(refNo);
        request.setAttribute(Bookiing_Size, booksize);
        Master master = utilservice.getMasterdao().getBookingFromRefno(refNo);
        request.setAttribute(Master, master);

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

    public MStaffService getmStaffService() {
        return mStaffService;
    }

    public void setmStaffService(MStaffService mStaffService) {
        this.mStaffService = mStaffService;
    }

}
