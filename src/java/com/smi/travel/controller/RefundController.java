/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.service.MStaffService;
import com.smi.travel.datalayer.service.RefundService;
import com.smi.travel.datalayer.view.entity.CustomerAgentInfo;
import com.smi.travel.master.controller.SMITravelController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author top
 */
public class RefundController extends SMITravelController {

    private static final ModelAndView Refund = new ModelAndView("Refund");
    private UtilityService utilservice;
    private MStaffService mStaffService;
    private RefundService refundService;
    private static final String BookingSize = "BookingSize";
    private static final String Master = "Master";
    private static final String Staff = "Staff";
    private static final String Action = "Action";

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        int result = 0;
        String action = request.getParameter("action");
        String refNo = request.getParameter("referenceNo");

        if ("new".equalsIgnoreCase(action)) {
        } else if ("edit".equalsIgnoreCase(action)) {
            request.setAttribute(Action, "update");
        } else if ("update".equalsIgnoreCase(action)) {

        }
        // Refund By
        List<CustomerAgentInfo> listRefundBy = utilservice.getListCustomerAgentInfo();
        if(listRefundBy != null){
            request.setAttribute("listRefundBy", listRefundBy);
        }else{
            request.setAttribute("listRefundBy", null);
        }
        
        // Receive by
        List<SystemUser> listReceiveBy = utilservice.getUserList();
        if(listReceiveBy != null){
            request.setAttribute("listReceiveBy", listReceiveBy);
        }else{
            request.setAttribute("listReceiveBy", null);
        }
        
        
        setGeneralResponseAttribute(request, refNo);
        return Refund;
    }

    private void setGeneralResponseAttribute(HttpServletRequest request, String refNo) {
        Master master = utilservice.getMasterdao().getBookingFromRefno(refNo);
        request.setAttribute(Master, master);
        int[] booksize = utilservice.getCountItemFromBooking(refNo);
        request.setAttribute(BookingSize, booksize);
        SystemUser staff = new SystemUser();
        List<SystemUser> staffList = mStaffService.searchSystemUser(staff, 2);
        request.setAttribute(Staff, staffList);
    }

    public UtilityService getUtilservice() {
        return utilservice;
    }

    public void setUtilservice(UtilityService utilservice) {
        this.utilservice = utilservice;
    }

    public MStaffService getmStaffService() {
        return mStaffService;
    }

    public void setmStaffService(MStaffService mStaffService) {
        this.mStaffService = mStaffService;
    }

    public RefundService getRefundService() {
        return refundService;
    }

    public void setRefundService(RefundService refundService) {
        this.refundService = refundService;
    }

}
