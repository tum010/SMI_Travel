package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.AirticketPnr;
import com.smi.travel.datalayer.entity.Customer;
import com.smi.travel.datalayer.entity.HistoryBooking;
import com.smi.travel.datalayer.entity.MInitialname;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.OtherBooking;
import com.smi.travel.datalayer.entity.Passenger;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.BookingAirticketService;
import com.smi.travel.datalayer.service.BookingOtherService;
import com.smi.travel.datalayer.service.PassengerService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.SMITravelController;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

public class OtherController extends SMITravelController {

    private static final ModelAndView Other = new ModelAndView("Other" );
    private static final ModelAndView Daytour = new ModelAndView("Daytour");
    private BookingAirticketService bookingAirticketService;
    private static final String Booking_Size = "BookingSize";
    private static final String DATALIST = "OtherList";
    private static final String CURRENCY = "Currency";
    private static final String TransectionResult = "result";
    private static final String TOTALCOST = "totalcost";
    private static final String TOTALPRICE = "totalprice";
    private static final String AMOUNT = "amount";
    private static final String MARKUP = "markup";
    private static final String LockUnlockBooking = "LockUnlockBooking";
    private static final String ISBILLSTATUS = "IsBillStatus";
    private UtilityService utilservice;
    private BookingOtherService OtherService;

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String refno = request.getParameter("referenceNo");
        String action = request.getParameter("action");
        String OtherID = request.getParameter("OtherID");
        String callPageFrom = request.getParameter("callPageFrom");
        String pattern = "###,###.###";
        DecimalFormat dF = new DecimalFormat(pattern);
        SystemUser user = (SystemUser) session.getAttribute("USER");
        long TotalCost = 0;
        long TotalPrice = 0;
        long Amount = 0;
        double Markup = 0;
        
        if ("delete".equalsIgnoreCase(action)) {
            System.out.println("delete booking other");
            OtherService.cancelBookDetailOther(OtherID);
        } else if ("enable".equalsIgnoreCase(action)) {
            System.out.println("enable booking other");
            OtherService.enableBookDetailOther(OtherID);
        }
        List<OtherBooking> OtherList = OtherService.getListBookingOtherFromRefno(refno);

        String currency = "THB";
        System.out.println("acion : " + action);
        System.out.println("OtherID : " + OtherID);
        
        if (OtherList != null) {
            for (int i = 0; i < OtherList.size(); i++) {
                OtherBooking other = OtherList.get(i);
                TotalCost += (other.getAdCost() * other.getAdQty()) + (other.getChCost() * other.getChQty()) + (other.getInCost() * other.getInQty());
                TotalPrice += (other.getAdPrice() * other.getAdQty()) + (other.getChPrice() * other.getChQty()) + (other.getInPrice() * other.getInQty());
                Amount = TotalPrice - TotalCost;
                Markup = 130.65;
            }
        }
        System.out.println("refno :"+refno);
        int[] booksize = utilservice.getCountItemFromBooking(refno);
        
        Master master = utilservice.getbookingFromRefno(refno);
        request.setAttribute("Master", master);
        for(int i=0;i<booksize.length;i++){
            System.out.println("book : "+booksize[i]);
        }
        request.setAttribute(Booking_Size, booksize);
        request.setAttribute(DATALIST, OtherList);
        request.setAttribute(CURRENCY, currency);

        request.setAttribute(TOTALCOST, dF.format(TotalCost));
        request.setAttribute(TOTALPRICE, dF.format(TotalPrice));
        request.setAttribute(AMOUNT, dF.format(Amount));
        request.setAttribute(MARKUP, "0");
        System.out.println("OtherController");
        if (request.getParameter(TransectionResult) != null) {
            if (request.getParameter(TransectionResult).equalsIgnoreCase("1")) {
                request.setAttribute(TransectionResult, "save successful");
            }
        }
        
        if(String.valueOf(callPageFrom).equalsIgnoreCase("FromDayTour")){
            return new ModelAndView("redirect:Daytour.smi?referenceNo=" + refno + "&action=edit");
        }
        if(("1").equals(String.valueOf(master.getFlagOther())) 
            || ("2").equals(String.valueOf(master.getMBookingstatus().getId()))
            || ("5").equals(String.valueOf(master.getMBookingstatus().getId()))){
            request.setAttribute(LockUnlockBooking,1);
        }else{
            request.setAttribute(LockUnlockBooking,0);
        }

        HistoryBooking historyBooking = new HistoryBooking();
        historyBooking.setHistoryDate(new Date());
        historyBooking.setAction("VIEW OTHER BOOKING");
        String detail = "";
        historyBooking.setDetail(detail);
        historyBooking.setMaster(master);
        historyBooking.setStaff(user);
        int resultsave = utilservice.insertHistoryBooking(historyBooking);
        System.out.println(" resultsave " + resultsave);
        
        return Other;
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

    public BookingOtherService getOtherService() {
        return OtherService;
    }

    public void setOtherService(BookingOtherService OtherService) {
        this.OtherService = OtherService;
    }

}
