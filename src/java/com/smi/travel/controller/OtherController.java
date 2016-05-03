package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.HistoryBooking;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.OtherBooking;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.BookingAirticketService;
import com.smi.travel.datalayer.service.BookingOtherService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.SMITravelController;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;

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
        String pattern = "###,##0.00";
        DecimalFormat dF = new DecimalFormat(pattern);
        SystemUser user = (SystemUser) session.getAttribute("USER");
//        long TotalCost = 0;
//        long TotalPrice = 0;
//        long Amount = 0;
//        double Markup = 0;
//        float totalCostTemp = 0;
//        float totalPriceTemp = 0;
        
        BigDecimal TotalCost = new BigDecimal(0);
        BigDecimal TotalPrice = new BigDecimal(0);
        BigDecimal Amount = new BigDecimal(0);
        BigDecimal Markup = new BigDecimal(0);
        BigDecimal totalCostTemp = new BigDecimal(0);
        BigDecimal totalPriceTemp = new BigDecimal(0);
        
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
                TotalCost = TotalCost.add(((other.getAdCost().multiply(new BigDecimal(other.getAdQty()))).add(other.getChCost().multiply(new BigDecimal(other.getChQty())))).add(other.getInCost().multiply(new BigDecimal(other.getInQty()))));
                TotalPrice = TotalPrice.add(((other.getAdPrice().multiply(new BigDecimal(other.getAdQty()))).add(other.getChPrice().multiply(new BigDecimal(other.getChQty())))).add(other.getInPrice().multiply(new BigDecimal(other.getInQty()))));
                totalCostTemp = totalCostTemp.add(((other.getAdCost().multiply(new BigDecimal(other.getAdQty()))).add(other.getChCost().multiply(new BigDecimal(other.getChQty())))).add(other.getInCost().multiply(new BigDecimal(other.getInQty()))));
                totalPriceTemp = totalPriceTemp.add(((other.getAdPrice().multiply(new BigDecimal(other.getAdQty()))).add(other.getChPrice().multiply(new BigDecimal(other.getChQty())))).add(other.getInPrice().multiply(new BigDecimal(other.getInQty()))));
//                TotalPrice += (other.getAdPrice() * other.getAdQty()) + (other.getChPrice() * other.getChQty()) + (other.getInPrice() * other.getInQty());
//                totalCostTemp += (other.getAdCost() * other.getAdQty()) + (other.getChCost() * other.getChQty()) + (other.getInCost() * other.getInQty());
//                totalPriceTemp += (other.getAdPrice() * other.getAdQty()) + (other.getChPrice() * other.getChQty()) + (other.getInPrice() * other.getInQty());        
                Amount = TotalPrice.subtract(TotalCost);
                Markup = new BigDecimal(130.65);
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
        BigDecimal temp = new BigDecimal(BigInteger.ZERO);
        request.setAttribute(MARKUP, ((new BigDecimal(BigInteger.ZERO).compareTo(totalCostTemp) < 0 ) && (new BigDecimal(BigInteger.ZERO).compareTo(totalPriceTemp) < 0 ) ?  (((totalPriceTemp.divide(totalCostTemp,2, RoundingMode.HALF_UP))).subtract(BigDecimal.ONE)).multiply(new BigDecimal(100)) : "0"));
        

//        request.setAttribute(MARKUP, (totalCostTemp > 0 && totalPriceTemp > 0 ? dF.format((((totalPriceTemp/totalCostTemp)-1)*100)) : "0"));
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
