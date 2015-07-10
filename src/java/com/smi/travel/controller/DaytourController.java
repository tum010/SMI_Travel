package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.DaytourBooking;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.OtherBooking;
import com.smi.travel.datalayer.service.BookingDaytourService;
import com.smi.travel.datalayer.service.BookingOtherService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.SMITravelController;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author sumeta
 */
public class DaytourController extends SMITravelController {

    private static final Logger log = Logger.getLogger(DaytourDetailController.class.getName());
    private static final ModelAndView Daytour = new ModelAndView("Daytour");
    private BookingDaytourService bookingDaytourService;
    private UtilityService utilservice;
    private static final String Bookiing_Size = "BookingSize";
    private static final String OtherLists = "OtherLists";
    private static final String Master = "Master";
    private static final String DaytourBookingList = "DaytourBookingList";
    private static final String LockUnlockBooking = "LockUnlockBooking";
    private static final String LockUnlockBookingOther = "lockUnlockBookingOther";
    private BookingOtherService OtherService;

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String refNo = request.getParameter("referenceNo");
        String daytourId = request.getParameter("daytourId");
        
        log.info("action="+action+ ", referenceNo="+refNo + " daytourId=" + daytourId);
        

        
        if ("disable".equalsIgnoreCase(action)) {
            int result = bookingDaytourService.cancelBookDetailDaytour(daytourId);
            log.info("disable result =" + result);
            return new ModelAndView("redirect:Daytour.smi?referenceNo=" + refNo + "&action=edit");
        } else if ("enable".equalsIgnoreCase(action)) {
            int result = bookingDaytourService.enableBookDetailDaytour(daytourId);
            log.info("enable result =" + result);
            return new ModelAndView("redirect:Daytour.smi?referenceNo=" + refNo + "&action=edit");
        } else {
            System.out.println("refNo := "+refNo);
            Master master = utilservice.getMasterdao().getBookingFromRefno(refNo);
            List<DaytourBooking> listBooking = new ArrayList<DaytourBooking>();
            Set daytourSets = master.getDaytourBookings();
            System.out.println("daytourSets = "+daytourSets);
            listBooking.addAll(daytourSets);
            request.setAttribute(DaytourBookingList, listBooking);
            
            List<OtherBooking> OtherList = OtherService.getListBookingOtherFromRefno(refNo);
            request.setAttribute(OtherLists, OtherList);
            
            setGeneralResponseAttribute(request, refNo);
        }
        
        return Daytour;
    }

    public void setGeneralResponseAttribute(HttpServletRequest request, String refNo) {
        int[] booksize = utilservice.getCountItemFromBooking(refNo);
        request.setAttribute(Bookiing_Size, booksize);
        Master master = utilservice.getMasterdao().getBookingFromRefno(refNo);
        request.setAttribute(Master, master);
        if(("1").equals(String.valueOf(master.getFlagDaytour())) 
            || ("2").equals(String.valueOf(master.getMBookingstatus().getId()))
            || ("5").equals(String.valueOf(master.getMBookingstatus().getId()))){
            request.setAttribute(LockUnlockBooking,1);
        }else{
            request.setAttribute(LockUnlockBooking,0);
        }
        
        if(("1").equals(String.valueOf(master.getFlagOther()))
            || ("2").equals(String.valueOf(master.getMBookingstatus().getId()))
            || ("5").equals(String.valueOf(master.getMBookingstatus().getId()))){
            request.setAttribute(LockUnlockBookingOther,1);
        }else{
            request.setAttribute(LockUnlockBookingOther,0);
        }
    }

    public UtilityService getUtilservice() {
        return utilservice;
    }

    public void setUtilservice(UtilityService utilservice) {
        this.utilservice = utilservice;
    }

    public BookingDaytourService getBookingDaytourService() {
        return bookingDaytourService;
    }

    public void setBookingDaytourService(BookingDaytourService bookingDaytourService) {
        this.bookingDaytourService = bookingDaytourService;
    }

    public BookingOtherService getOtherService() {
        return OtherService;
    }

    public void setOtherService(BookingOtherService OtherService) {
        this.OtherService = OtherService;
    }
    
    
    
    
}
