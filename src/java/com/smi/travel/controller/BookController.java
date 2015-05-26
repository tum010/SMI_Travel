package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.AirticketPnr;
import com.smi.travel.datalayer.entity.Customer;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.BookingAirticketService;
import com.smi.travel.datalayer.service.WorkSpaceService;
import com.smi.travel.datalayer.view.entity.BookingView;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

public class BookController extends SMITravelController {

    private static final ModelAndView Book = new ModelAndView("Book");
    private static final Logger log = Logger.getLogger(LoginController.class.getName());
    private WorkSpaceService workspaceService;
    private static final String DATALIST = "booking_list";
    private static final String STATUSLIST = "booking_status";
    

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action =request.getParameter("action");
        String refno = request.getParameter("refno");
        String PassFirst = request.getParameter("PassFirst");
        String PassLast = request.getParameter("PassLast");
        String section = request.getParameter("section");
        String Bookdate = request.getParameter("Bookdate");
        String status = request.getParameter("status");
        SystemUser user = (SystemUser) session.getAttribute("USER");
        List<BookingView> bookinglist = new LinkedList<BookingView>();
        UtilityFunction util = new UtilityFunction();
        Date thisDate = new Date();
        String username ="";
        String departmentid="";
        if("1".equalsIgnoreCase(section)){
            username = user.getUsername();
        }else if("2".equalsIgnoreCase(section)){
            if(user.getMDepartment() != null){
                departmentid = user.getMDepartment().getId();
            }
        }
        
        if("search".equalsIgnoreCase(action)){
            bookinglist = workspaceService.getListBooking(refno,PassFirst,PassLast,username,departmentid,Bookdate,status);
            request.setAttribute("Bookdate", Bookdate);
        }else{
            bookinglist = workspaceService.getListBooking(refno,PassFirst,PassLast,user.getUsername(),departmentid,Bookdate,status);
            //request.setAttribute("Bookdate", util.convertDateToString(thisDate));
            
        }
      
        request.setAttribute("booking_date",util.convertDateToString(thisDate));
        if(user.getMDepartment() != null){
            request.setAttribute("userdepartment", user.getMDepartment().getId());
        }  
        request.setAttribute(DATALIST,bookinglist);
        request.setAttribute(STATUSLIST,workspaceService.getListBookStatus());
        request.setAttribute("refno", refno);
        request.setAttribute("PassFirst", PassFirst);
        request.setAttribute("PassLast", PassLast);
        request.setAttribute("section", section);
        request.setAttribute("status", status);

        return Book;
    }

    public WorkSpaceService getWorkspaceService() {
        return workspaceService;
    }

    public void setWorkspaceService(WorkSpaceService workspaceService) {
        this.workspaceService = workspaceService;
    }
    
    
}
