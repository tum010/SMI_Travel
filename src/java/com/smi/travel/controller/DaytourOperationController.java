package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.service.BookingAirticketService;
import com.smi.travel.datalayer.service.BookingHotelService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.SMITravelController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author sumeta
 */
public class DaytourOperationController extends SMITravelController {

    private static final ModelAndView DaytourOperation = new ModelAndView("DaytourOperation");
    private BookingAirticketService bookingAirticketService;
    private BookingHotelService bookingHotelService;
    private UtilityService utilservice;
    private static final String Bookiing_Size = "BookingSize";
    private static final String Master = "Master";

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String refNo = request.getParameter("referenceNo");
        if ("delete".equalsIgnoreCase(action)) {
            return new ModelAndView("redirect:HotelBooking.smi?referenceNo=" + refNo + "&action=edit");
        } else if ("enable".equalsIgnoreCase(action)) {
            return new ModelAndView("redirect:HotelBooking.smi?referenceNo=" + refNo + "&action=edit");
        } else {
            setGeneralResponseAttribute(request, refNo);
        }

        return DaytourOperation;
    }

    public void setGeneralResponseAttribute(HttpServletRequest request, String refNo) {
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

    public BookingHotelService getBookingHotelService() {
        return bookingHotelService;
    }

    public void setBookingHotelService(BookingHotelService bookingHotelService) {
        this.bookingHotelService = bookingHotelService;
    }

    public UtilityService getUtilservice() {
        return utilservice;
    }

    public void setUtilservice(UtilityService utilservice) {
        this.utilservice = utilservice;
    }
}
