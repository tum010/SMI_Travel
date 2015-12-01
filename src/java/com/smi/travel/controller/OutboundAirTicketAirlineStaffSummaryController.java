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
 * @author chonnasith
 */
public class OutboundAirTicketAirlineStaffSummaryController extends SMITravelController{
    private static final ModelAndView OutboundAirTicketAirlineStaffSummary = new ModelAndView("OutboundAirTicketAirlineStaffSummary");
    private static final ModelAndView OutboundAirTicketAirlineStaffSummary_REFRESH = new ModelAndView(new RedirectView("OutboundAirTicketAirlineStaffSummary.smi", true));

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        return OutboundAirTicketAirlineStaffSummary;
    }
    
}
