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
 * @author chonnasith
 */
public class OutboundAirTicketMonthReportController extends SMITravelController{
    private static final ModelAndView OutboundAirTicketMonthReport = new ModelAndView("OutboundAirTicketMonthReport");
    private static final ModelAndView OutboundAirTicketMonthReport_REFRESH = new ModelAndView(new RedirectView("OutboundAirTicketMonthReport.smi", true));
    private static final String BILLTOLIST = "customerAgent";
    private AirTicketReportService AirreportService;

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        request.setAttribute(BILLTOLIST, getAirreportService().getListCustomerAgentInfo());
        return OutboundAirTicketMonthReport;
    }

    public AirTicketReportService getAirreportService() {
        return AirreportService;
    }

    public void setAirreportService(AirTicketReportService AirreportService) {
        this.AirreportService = AirreportService;
    }
    
}
