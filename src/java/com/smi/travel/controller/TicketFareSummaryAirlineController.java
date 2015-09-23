package com.smi.travel.controller;
import com.smi.travel.master.controller.SMITravelController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class TicketFareSummaryAirlineController extends SMITravelController {
    private static final ModelAndView TicketFareSummaryAirline = new ModelAndView("TicketFareSummaryAirline");
    private static final ModelAndView TicketFareSummaryAirline_REFRESH = new ModelAndView(new RedirectView("TicketFareSummaryAirline.smi", true));
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        return TicketFareSummaryAirline;
    }
}
