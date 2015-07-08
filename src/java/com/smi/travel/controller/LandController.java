package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.AirticketPnr;
import com.smi.travel.datalayer.entity.LandBooking;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.service.BookingAirticketService;
import com.smi.travel.datalayer.service.BookingLandService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.SMITravelController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

public class LandController extends SMITravelController {

    private static final ModelAndView Land = new ModelAndView("Land");
    private BookingAirticketService bookingAirticketService;
    private static final String TransectionResult = "result";
    private static final String Booking_Size = "BookingSize";
    private static final String DATALIST = "Land_list";
    private static final String LockUnlockBooking = "LockUnlockBooking";
    private UtilityService utilservice;
    private BookingLandService landservice;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String refno = request.getParameter("referenceNo");
        String action = request.getParameter("action");
        String LandID = request.getParameter("LandID");
        System.out.println("LandController : "+refno);
    
        if("delete".equalsIgnoreCase(action)){
            System.out.println("delete booking land");
            landservice.cancelBookDetailLand(LandID);
        }else if("enable".equalsIgnoreCase(action)){
            System.out.println("enable booking land");
            landservice.enableBookDetailLand(LandID);
        }
        List<LandBooking> landlist = landservice.getListBookingLandFromRefno(refno);
        int[] booksize = utilservice.getCountItemFromBooking(refno);
        request.setAttribute(Booking_Size, booksize);
        request.setAttribute(DATALIST, landlist);
        Master master = utilservice.getbookingFromRefno(refno);
        request.setAttribute("Master", master);
        String BookType = master.getBookingType();
        if("I".equalsIgnoreCase(BookType)){
            request.setAttribute("BOOKING_TYPE","i");
        }else{
            request.setAttribute("BOOKING_TYPE","o");
        }

        if(request.getParameter(TransectionResult) != null){
            if(request.getParameter(TransectionResult).equalsIgnoreCase("1")){
                request.setAttribute(TransectionResult, "save successful");
            }
            
        }
        request.setAttribute(LockUnlockBooking,master.getFlagLand());
        return Land;
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

    public BookingLandService getLandservice() {
        return landservice;
    }

    public void setLandservice(BookingLandService landservice) {
        this.landservice = landservice;
    }
    
    
    
    
}
