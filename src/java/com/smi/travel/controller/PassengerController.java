package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.AirticketBooking;
import com.smi.travel.datalayer.entity.AirticketPnr;
import com.smi.travel.master.controller.SMITravelController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import com.smi.travel.datalayer.service.BookingAirticketService;
import com.smi.travel.datalayer.service.PassengerService;
import com.smi.travel.datalayer.entity.BookingPassenger;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.Customer;
import com.smi.travel.datalayer.entity.Passenger;
import com.smi.travel.datalayer.service.UtilityService;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class PassengerController extends SMITravelController {

    private static final ModelAndView Passenger = new ModelAndView("Passenger");

    private BookingAirticketService bookingAirticketService;
    private UtilityService utilservice;
    private PassengerService passengerService;

    private static final String ACTION = "action";
    private static final String Bookiing_Size = "BookingSize";
    private static final String Master = "Master";
    private static final String PassengerList = "PassengerList";
    private static final String TransactionResult = "result";
    private static final String LockUnlockBooking = "LockUnlockBooking";

    private static final String[] resultText
            = {"Save unsuccessful",
                "Save successful",
                "Cannot Delete Family Leader"
            };

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        int result = 0;
        System.out.println("PassengerControler");
        String refNo = request.getParameter("referenceNo");
        String action = request.getParameter("action");

        if ("add".equalsIgnoreCase(action)) {
            System.out.println("action add");
            request.setAttribute(ACTION, "insert");
        } else if ("insert".equalsIgnoreCase(action)) {
            System.out.println("action insert");
        } else if ("edit".equalsIgnoreCase(action)) {
            System.out.println("action edit");
            List<Passenger> passengerList = passengerService.getPassengerFromRefno(refNo);
            request.setAttribute(PassengerList, passengerList);

            setResponseAttribute(request, refNo);
        } else if ("update".equalsIgnoreCase(action)) {
            System.out.println("action update");
        } else if ("delete".equalsIgnoreCase(action)) {
            String id = request.getParameter("PassengerID");
            Passenger passenger = passengerService.getPassengerFromID(id);
            if (passenger.getIsLeader() == 1) {
                result = 2;
                return new ModelAndView("redirect:Passenger.smi?referenceNo=" + refNo + "&action=edit&result=" + result);
            }
            result = passengerService.DeletePassenger(passenger);
            if (result == 1) {
                request.setAttribute(TransactionResult, "delete successful");
            } else {
                request.setAttribute(TransactionResult, "delete unsuccessful");
            }
            return new ModelAndView("redirect:Passenger.smi?referenceNo=" + refNo + "&action=edit&result=" + result);

        } else {
            System.out.println("no action");
            if ("".equalsIgnoreCase(refNo) || (refNo == null)) {
                System.out.println("******* No refNo !!!!");
            } else {
                System.out.println("******* have refNo ************");

            }
        }

        return Passenger;
    }

    public void setResponseAttribute(HttpServletRequest request, String refNo) {

        int[] booksize = utilservice.getCountItemFromBooking(refNo);
        request.setAttribute(Bookiing_Size, booksize);

        Master master = utilservice.getMasterdao().getBookingFromRefno(refNo);
        request.setAttribute(Master, master);
        if(("2").equals(String.valueOf(master.getMBookingstatus().getId())) || ("5").equals(String.valueOf(master.getMBookingstatus().getId()))){
            request.setAttribute(LockUnlockBooking,1);
        }else{
            request.setAttribute(LockUnlockBooking,0);
        }
        String resultS = request.getParameter("result");
        if (StringUtils.isNotEmpty(resultS)) {
            request.setAttribute(TransactionResult, resultText[Integer.parseInt(resultS)]);
        }

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

    public PassengerService getPassengerService() {
        return passengerService;
    }

    public void setPassengerService(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

}
