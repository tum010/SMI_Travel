/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller;

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
public class DebtorSummaryController extends SMITravelController{
    private static final ModelAndView DebtorSummary = new ModelAndView("DebtorSummary");
    private static final ModelAndView DebtorSummary_REFRESH = new ModelAndView(new RedirectView("DebtorSummary.smi", true));
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
        if(listCustomerAgentInfo != null){
            request.setAttribute("listAgent", listCustomerAgentInfo);
        }else{
            request.setAttribute("listAgent", null);
        } 
        
        return DebtorSummary;
    }


}
