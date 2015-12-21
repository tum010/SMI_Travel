/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller;

import com.smi.travel.datalayer.service.AirTicketReportService;
import com.smi.travel.master.controller.SMITravelController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Jittima
 */
public class RefundTicketSummaryReportController extends SMITravelController {
    
    private static final ModelAndView RefundTicketSummary = new ModelAndView("RefundTicketSummary");
    private static final ModelAndView RefundTicketSummary_REFRESH = new ModelAndView(new RedirectView("RefundTicketSummary.smi", true));
    private static final String BILLTOLIST = "customerAgent";
    private AirTicketReportService airreportService;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        request.setAttribute(BILLTOLIST, getAirreportService().getListCustomerAgentInfo());
        return RefundTicketSummary;
    }

    public AirTicketReportService getAirreportService() {
        return airreportService;
    }

    public void setAirreportService(AirTicketReportService airreportService) {
        this.airreportService = airreportService;
    }


    
}
