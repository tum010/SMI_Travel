/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.CustomerAgentInfo;
import com.smi.travel.master.controller.SMITravelController;
import java.util.ArrayList;
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
public class OverdueSummaryController extends SMITravelController{
    private static final ModelAndView OverdueSummary = new ModelAndView("OverdueSummary");
    private static final ModelAndView OverdueSummary_REFRESH = new ModelAndView(new RedirectView("OverdueSummary.smi", true));
    private UtilityService utilityService;
    
    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        //List Agent
        List<CustomerAgentInfo> listCustomerAgentInfo = new ArrayList<CustomerAgentInfo>();
        listCustomerAgentInfo = utilityService.getListCustomerAgentInfo();
        System.out.println(" Client Size : " + listCustomerAgentInfo.size());
        if(listCustomerAgentInfo != null){
            request.setAttribute("customerAgentList", listCustomerAgentInfo);
        }else{
            request.setAttribute("customerAgentList", null);
        } 
        
        // List Sale 
        List<SystemUser> listSale = utilityService.getUserList();
        if(listSale != null && listSale.size() != 0){
            request.setAttribute("listSale", listSale);
        }else{
            request.setAttribute("listSale", listSale);
        }
        
        return OverdueSummary;
    }
    
}
