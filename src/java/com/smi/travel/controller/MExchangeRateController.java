/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller;

import com.smi.travel.datalayer.service.MExchangeRateService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.SMITravelController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Kanokporn
 */
public class MExchangeRateController  extends SMITravelController{
    private static final ModelAndView MExchangeRate = new ModelAndView("MExchangeRate");
    private MExchangeRateService mExchangeRateService;
    private UtilityService  utilityService;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        
        
        return MExchangeRate;
    }

    public MExchangeRateService getmExchangeRateService() {
        return mExchangeRateService;
    }

    public void setmExchangeRateService(MExchangeRateService mExchangeRateService) {
        this.mExchangeRateService = mExchangeRateService;
    }

    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }
    
}
