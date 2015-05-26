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
 * @author wleenavo
 */
public class AirTicketMonthReportController extends SMITravelController {
    
    private static final ModelAndView AirTicketMonthReport = new ModelAndView("AirTicketMonthReport");
    private static final ModelAndView AirTicketMonthReport_REFRESH = new ModelAndView(new RedirectView("AirTicketMonthReport.smi", true));
    private static final String BILLTOLIST = "customerAgent";
    private AirTicketReportService AirreportService;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        
        request.setAttribute(BILLTOLIST, AirreportService.getListCustomerAgentInfo());
        return AirTicketMonthReport;
    }

    public AirTicketReportService getAirreportService() {
        return AirreportService;
    }

    public void setAirreportService(AirTicketReportService AirreportService) {
        this.AirreportService = AirreportService;
    }
    
    
}
