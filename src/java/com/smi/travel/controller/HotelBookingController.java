/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.HotelBooking;
import com.smi.travel.datalayer.entity.HotelRequest;
import com.smi.travel.datalayer.entity.HotelRoom;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.service.BookingAirticketService;
import com.smi.travel.datalayer.service.BookingHotelService;
import com.smi.travel.datalayer.service.LockUnlockBookingService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author sumeta
 */
public class HotelBookingController extends SMITravelController {

    private static final ModelAndView HotelBooking = new ModelAndView("HotelBooking");
    private static final ModelAndView HotelBooking_REFRESH = new ModelAndView(new RedirectView("HotelBooking.smi", true));
    private BookingAirticketService bookingAirticketService;
    private BookingHotelService bookingHotelService;
    private static final String Bookiing_Size = "BookingSize";
    private static final String HotelBookingList = "HotelBookingList";
    private static final String CURRENCY ="Currency";
    private UtilityService utilservice;
    private static final String Master = "Master"; 
    private static final String BookType = "BOOKING_TYPE";
    private static final String LockUnlockBooking = "LockUnlockBooking";
    private static final String ISBILLSTATUS = "IsBillStatus";

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String refNo = request.getParameter("referenceNo");
        String hotelId = request.getParameter("HotelID");
        System.out.println("HotelBookingController action=" + action);
        if ("delete".equalsIgnoreCase(action)) {
            bookingHotelService.cancelBookHotel(hotelId);
            return new ModelAndView("redirect:HotelBooking.smi?referenceNo=" + refNo + "&action=edit");
        } else if ("enable".equalsIgnoreCase(action)) {
            bookingHotelService.enableBookHotel(hotelId);
            return new ModelAndView("redirect:HotelBooking.smi?referenceNo=" + refNo + "&action=edit");
        } else {
            System.out.println("HotelBookingController");
            List<HotelBooking> hotelBookingList = bookingHotelService.getHotelBookFromRefNo(refNo);
            if (null != hotelBookingList) {
                sumPrice(hotelBookingList);;
            }
            request.setAttribute(HotelBookingList, hotelBookingList);
            setGeneralResponseAttribute(request, refNo);
        }
        
        return HotelBooking;
    }
    
   


    private void sumPrice(List<HotelBooking> hotelBookingList) {
        UtilityFunction util = new UtilityFunction();
        for (int i = 0; i < hotelBookingList.size(); i++) {
            int sumRoom = 0, sumReuest = 0;
            int sumRoomCost = 0, sumReuestCost = 0;
            int Nonight = 0;
            HotelBooking hotelList = hotelBookingList.get(i);
            Nonight =util.getDateDiff(hotelList.getCheckin(), hotelList.getCheckout());
            List<HotelRoom> hotelRoom = new ArrayList<HotelRoom>(hotelList.getHotelRooms());
            for (int j = 0; j < hotelRoom.size(); j++) {
                HotelRoom room = hotelRoom.get(j);
                sumRoom += (room.getPrice() *room.getQty() * Nonight);
                sumRoomCost += (room.getCost()*room.getQty() * Nonight);
            }
            hotelBookingList.get(i).setAdult(sumRoom);
            List<HotelRequest> hotelRequests = new ArrayList<HotelRequest>(hotelList.getHotelRequests());
            for (int j = 0; j < hotelRequests.size(); j++) {
                HotelRequest hr = hotelRequests.get(j);
                sumReuest += hr.getPrice();
                sumReuestCost += hr.getCost();
            }
            hotelBookingList.get(i).setChild(sumReuest);
            hotelBookingList.get(i).setTotalcost(sumRoomCost + sumReuestCost);

        }
    }

    public void setGeneralResponseAttribute(HttpServletRequest request, String refNo) {
        int[] booksize = utilservice.getCountItemFromBooking(refNo);
        request.setAttribute(Bookiing_Size, booksize);
        Master master = utilservice.getMasterdao().getBookingFromRefno(refNo);
        String BookingType = master.getBookingType();
        request.setAttribute(BookType,BookingType);
        request.setAttribute(Master, master);
        request.setAttribute(CURRENCY, master.getCurrency());

        // Mbookstatus ==> 2 Finish , 5 Finish by Finance
        if(("1").equals(String.valueOf(master.getFlagHotel())) 
            || ("2").equals(String.valueOf(master.getMBookingstatus().getId()))
            || ("5").equals(String.valueOf(master.getMBookingstatus().getId()))){
            request.setAttribute(LockUnlockBooking,1);
        }else{
            request.setAttribute(LockUnlockBooking,0);
        }
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
