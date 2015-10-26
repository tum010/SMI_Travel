/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller;

import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.SMITravelController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Kanokporn
 */
public class PackageMonthlyController extends SMITravelController {
    private static final ModelAndView PackageMonthly = new ModelAndView("PackageMonthly");
    private static final ModelAndView PackageMonthly_REFRESH = new ModelAndView(new RedirectView("PackageMonthly.smi", true));
    private UtilityService utilityService;

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        
        return PackageMonthly;
    }
    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }
}
