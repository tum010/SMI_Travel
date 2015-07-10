package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.MBookingstatus;
import com.smi.travel.datalayer.service.LockUnlockBookingService;
import com.smi.travel.master.controller.SMITravelController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class LockUnlockBookingController extends SMITravelController {
    private static final ModelAndView LockUnlockBooking = new ModelAndView("LockUnlockBooking");
    private static final ModelAndView LockUnlockBooking_REFRESH = new ModelAndView(new RedirectView("LockUnlockBooking.smi", true));
    private static final String STATUSLIST = "status_list";
    private static final String BOOKSTATUSFROMREFNO = "bookStatusFromRefNo";
    private static final String SAVESUCCESS = "save_success";
    
    private LockUnlockBookingService lockUnlockBookingService;
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        int result = 0;
        int []Flag = new int[6];
        String action = request.getParameter("action");
        String referenceNo = request.getParameter("referenceNo");
        String bookStatus = request.getParameter("SelectStatus");
        String flagAir = request.getParameter("flagAir");
        String flagHotel = request.getParameter("flagHotel");
        String flagDaytour = request.getParameter("flagDaytour");
        String flagLand = request.getParameter("flagLand");
        String flagOther = request.getParameter("flagOther");
        
        List<MBookingstatus> bookList = lockUnlockBookingService.getListMBookingstatus();
        request.setAttribute(STATUSLIST,bookList);
        return LockUnlockBooking;
    }
  
    public LockUnlockBookingService getLockUnlockBookingService() {
        return lockUnlockBookingService;
    }

    public void setLockUnlockBookingService(LockUnlockBookingService lockUnlockBookingService) {
        this.lockUnlockBookingService = lockUnlockBookingService;
    }

   
}
