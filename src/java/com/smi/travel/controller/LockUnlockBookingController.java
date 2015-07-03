package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.Agent;
import com.smi.travel.datalayer.entity.MBookingstatus;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.LockUnlockBookingService;
import com.smi.travel.datalayer.service.UtilityService;
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
        if ("save".equalsIgnoreCase(action)) {
            if(flagAir == null){
                flagAir = "0";
            }
            if(flagHotel == null){
                flagHotel = "0";
            }
            if(flagDaytour == null){
                flagDaytour = "0";
            }
            if(flagLand == null){
                flagLand = "0";
            }
            if(flagOther == null){
                flagOther = "0";
            }
            
            Flag[1] = Integer.parseInt(String.valueOf(flagAir));
            Flag[2] = Integer.parseInt(String.valueOf(flagHotel));
            Flag[3] = Integer.parseInt(String.valueOf(flagDaytour));
            Flag[4] = Integer.parseInt(String.valueOf(flagLand));
            Flag[5] = Integer.parseInt(String.valueOf(flagOther));
            
            MBookingstatus mbookstatus = new MBookingstatus();
            mbookstatus.setId(String.valueOf(bookStatus));
            Master masterlist = lockUnlockBookingService.getbookingFromRefno(referenceNo);
            Master master = new Master();
            master.setId(masterlist.getId());
            master.setReferenceNo(masterlist.getReferenceNo());
            master.setStaff(masterlist.getStaff());
            master.setAgent(masterlist.getAgent());
            master.setCustomer(masterlist.getCustomer());
            master.setAdult(masterlist.getAdult());
            master.setChild(masterlist.getChild());
            master.setInfant(masterlist.getInfant());
            master.setIsPackage(masterlist.getIsPackage());
            master.setAgentRef(masterlist.getAgentRef());
            master.setRevisedBy(masterlist.getRevisedBy());
            master.setRevisedDate(masterlist.getRevisedDate());
            master.setBookingType(masterlist.getBookingType());
            master.setCreateBy(masterlist.getCreateBy());
            master.setCreateDate(masterlist.getCreateDate());
            master.setCurrency(masterlist.getCurrency());
            master.setMBookingstatus(mbookstatus);
            master.setFlagAir(Flag[1]);
            master.setFlagHotel(Flag[2]);
            master.setFlagDaytour(Flag[3]);
            master.setFlagLand(Flag[4]);
            master.setFlagOther(Flag[5]);

            result = lockUnlockBookingService.LockAndUnLockBooking(master);
            System.out.print("result :"+result);
            request.setAttribute(SAVESUCCESS,result);
            request.setAttribute(BOOKSTATUSFROMREFNO,master);

        }
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
