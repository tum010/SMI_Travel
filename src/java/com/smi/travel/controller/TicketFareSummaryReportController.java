package com.smi.travel.controller;
import com.smi.travel.master.controller.SMITravelController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class TicketFareSummaryReportController extends SMITravelController {
    private static final ModelAndView TicketFareSummaryReport = new ModelAndView("TicketFareSummaryReport");
    private static final ModelAndView TicketFareSummaryReport_REFRESH = new ModelAndView(new RedirectView("TicketFareSummaryReport.smi", true));
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        return TicketFareSummaryReport;
    }
}
