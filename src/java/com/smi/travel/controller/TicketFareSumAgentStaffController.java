/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.MAccterm;
import com.smi.travel.datalayer.entity.MAirline;
import com.smi.travel.datalayer.entity.MAirlineAgent;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.SMITravelController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Jittima
 */
public class TicketFareSumAgentStaffController extends SMITravelController {
    private static final ModelAndView TicketFareSumAgentStaff = new ModelAndView("TicketFareSumAgentStaff");
    private static final ModelAndView TicketFareSumAgentStaff_REFRESH = new ModelAndView(new RedirectView("TicketFareSumAgentStaff.smi", true));
    private static final String AIRLINELIST = "airlineList";
    private static final String AIRLINECODELIST = "airlineCodeList";
    private static final String USERLIST = "userList";
    private static final String TERMPAYLIST = "termPayList";
    
    private UtilityService utilityService;
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        setResponseAttribute(request);
        return TicketFareSumAgentStaff;    
    }
    
    private void setResponseAttribute(HttpServletRequest request) {
        List<MAirlineAgent> mAirlineAgentsList = utilityService.getListMAirLineAgent();
        request.setAttribute(AIRLINELIST,mAirlineAgentsList);

        
        List<SystemUser> userList = utilityService.getUserList();
        request.setAttribute(USERLIST,userList);
        
        List<MAccterm> mAcctermList = utilityService.getListMAccterm();
        request.setAttribute(TERMPAYLIST,mAcctermList);
        
        List<MAirline> mAirlines = utilityService.getListMAirlineCode();
        request.setAttribute(AIRLINECODELIST,mAirlines);
    }
    
    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }

    
}
