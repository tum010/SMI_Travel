package com.smi.travel.controller;
import com.smi.travel.master.controller.SMITravelController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class SearchPaymentOutboundController extends SMITravelController {
    private static final ModelAndView SearchPaymentOutbound = new ModelAndView("SearchPaymentOutbound");
    private static final ModelAndView SearchPaymentOutbound_REFRESH = new ModelAndView(new RedirectView("SearchPaymentOutbound.smi", true));
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        return SearchPaymentOutbound;
    }
}
