/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.MCurrency;
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
 * @author Kanokporn
 */
public class OutboundStaffSummaryController extends SMITravelController{
    private static final ModelAndView OutboundStaffSummary = new ModelAndView("OutboundStaffSummary");
    private static final ModelAndView OutboundStaffSummary_REFRESH = new ModelAndView(new RedirectView("OutboundStaffSummary.smi", true));
    private static final String USERLIST = "userList";
    private static final String MCURRENCYLIST = "currencyList";
    private UtilityService utilityService;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        List<SystemUser> userList = utilityService.getUserList();
        request.setAttribute(USERLIST,userList);
        
         List<MCurrency> mCurrencys = utilityService.getListMCurrency();
        request.setAttribute(MCURRENCYLIST, mCurrencys);
        
        
        return OutboundStaffSummary;
    }

    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }
    
}
