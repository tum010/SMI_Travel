/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.MAccpay;
import com.smi.travel.datalayer.entity.MBank;
import com.smi.travel.datalayer.entity.MCity;
import com.smi.travel.datalayer.entity.MItemstatus;
import com.smi.travel.datalayer.entity.PackageTour;
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
public class OutboundPackageSummaryController  extends SMITravelController{
    private static final ModelAndView OutboundPackageSummary = new ModelAndView("OutboundPackageSummary");
    private static final ModelAndView OutboundPackageSummary_REFRESH = new ModelAndView(new RedirectView("OutboundPackageSummary.smi", true));
    private UtilityService utilityService;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        // List Status 
        List<MItemstatus> listStatus = utilityService.getListMItemstatus();
        request.setAttribute("listStatus", listStatus);

        //List City
        List<MCity> listCity = utilityService.getListMCity();
        request.setAttribute("listCity", listCity);
        
        //List Package
        List<PackageTour> listPackage = utilityService.getListPackageTour();
        request.setAttribute("listPackage", listPackage);
        
        // List Pay by
        List<MAccpay> listPayby = utilityService.getListMAccpay();
        request.setAttribute("listPayby", listPayby);
        
        // List Bank
        List<MBank> listBank = utilityService.getListBank();
        request.setAttribute("listBank", listBank);
        
        // List Sale 
        List<SystemUser> listSale = utilityService.getUserOutboundList();
        request.setAttribute("listSale", listSale);
        
        return OutboundPackageSummary;
    }

    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }
    
}
