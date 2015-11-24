/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller;

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
public class OutboundProductSummaryController  extends SMITravelController{
    private static final ModelAndView OutboundProductSummary = new ModelAndView("OutboundProductSummary");
    private static final ModelAndView OutboundProductSummary_REFRESH = new ModelAndView(new RedirectView("OutboundProductSummary.smi", true));

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        return OutboundProductSummary;
    }
    
}
