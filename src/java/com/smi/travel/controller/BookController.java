package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.AirticketPnr;
import com.smi.travel.datalayer.entity.Customer;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.BookingAirticketService;
import com.smi.travel.datalayer.service.UtilityService;
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
    private static final String PAYBYLIST = "payby_list";
    private static final String BANKTRANSFERLIST = "banktrasfer_list";
    private UtilityService utilservice;
    

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        UtilityFunction util = new UtilityFunction();
        String action =request.getParameter("action");
        String refno = request.getParameter("refno");
        String PassFirst = request.getParameter("PassFirst");
        String PassLast = request.getParameter("PassLast");
        String section = request.getParameter("section");
        String Bookdate = request.getParameter("Bookdate");
        String status = request.getParameter("status");
        String pnr = request.getParameter("pnr");
        String ticketNo = request.getParameter("ticketNo");
        String payBy = request.getParameter("payBy");
        String transferDateFrom = request.getParameter("transferDateFrom");
        String transferDateTo = request.getParameter("transferDateTo");
        String refNoEdit = request.getParameter("refNoEdit");
        String bankTransfer = "";
        
        //Set Date dormat
        Bookdate = (!"".equalsIgnoreCase(Bookdate) && Bookdate != null ? util.convertDateToString(util.convertStringToDate(Bookdate)) : "");
        transferDateFrom = (!"".equalsIgnoreCase(transferDateFrom) && transferDateFrom != null ? util.convertDateToString(util.convertStringToDate(transferDateFrom)) : "");
        transferDateTo = (!"".equalsIgnoreCase(transferDateTo) && transferDateTo != null ? util.convertDateToString(util.convertStringToDate(transferDateTo)) : "");       
        
        if("4".equalsIgnoreCase(payBy)){
            bankTransfer = request.getParameter("bankTransfer");
        }
        SystemUser user = (SystemUser) session.getAttribute("USER");
        List<BookingView> bookinglist = new LinkedList<BookingView>();
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
            bookinglist = workspaceService.getListBooking(refno,PassFirst,PassLast,username,departmentid,Bookdate,status,pnr,ticketNo,payBy,bankTransfer,transferDateFrom,transferDateTo);
            request.setAttribute("Bookdate", Bookdate);
        }else if("cancelBook".equalsIgnoreCase(action)){
            String check = workspaceService.checkBook(refNoEdit);
            if("success".equalsIgnoreCase(check)){
                int result = workspaceService.cancelBook(refNoEdit);
                System.out.println("result : "+result);
            }else{
                request.setAttribute("result","fail");
            }          
            bookinglist = workspaceService.getListBooking(refno,PassFirst,PassLast,user.getUsername(),departmentid,Bookdate,status,pnr,ticketNo,payBy,bankTransfer,transferDateFrom,transferDateTo);
        }else if("enableBook".equalsIgnoreCase(action)){
            int result = workspaceService.enableBook(refNoEdit);
            System.out.println("result : "+result);
            bookinglist = workspaceService.getListBooking(refno,PassFirst,PassLast,user.getUsername(),departmentid,Bookdate,status,pnr,ticketNo,payBy,bankTransfer,transferDateFrom,transferDateTo);
        }else{
            bookinglist = workspaceService.getListBooking(refno,PassFirst,PassLast,user.getUsername(),departmentid,Bookdate,status,pnr,ticketNo,payBy,bankTransfer,transferDateFrom,transferDateTo);
            //request.setAttribute("Bookdate", util.convertDateToString(thisDate));
            
        }
      
        request.setAttribute("booking_date",util.convertDateToString(thisDate));
        if(user.getMDepartment() != null){
            request.setAttribute("userdepartment", user.getMDepartment().getId());
        }  
        request.setAttribute(DATALIST,bookinglist);
        request.setAttribute(STATUSLIST,workspaceService.getListBookStatus());
        request.setAttribute(PAYBYLIST, utilservice.getListMAccpay());
        request.setAttribute(BANKTRANSFERLIST, utilservice.getListBank());
        request.setAttribute("refno", refno);
        request.setAttribute("PassFirst", PassFirst);
        request.setAttribute("PassLast", PassLast);
        request.setAttribute("section", section);
        request.setAttribute("status", status);
        request.setAttribute("pnr", pnr);
        request.setAttribute("ticketNo", ticketNo);
        request.setAttribute("payBy", payBy);
        request.setAttribute("bankTransfer", bankTransfer);
        request.setAttribute("transferDateFrom", transferDateFrom);
        request.setAttribute("transferDateTo", transferDateTo);
        return Book;
    }

    public WorkSpaceService getWorkspaceService() {
        return workspaceService;
    }

    public void setWorkspaceService(WorkSpaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    public UtilityService getUtilservice() {
        return utilservice;
    }

    public void setUtilservice(UtilityService utilservice) {
        this.utilservice = utilservice;
    }
        
}
