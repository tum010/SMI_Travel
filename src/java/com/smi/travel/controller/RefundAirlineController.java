package com.smi.travel.controller;
import com.smi.travel.master.controller.SMITravelController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class RefundAirlineController extends SMITravelController {
    private static final ModelAndView RefundAirline = new ModelAndView("RefundAirline");
    private static final ModelAndView RefundAirline_REFRESH = new ModelAndView(new RedirectView("RefundAirline.smi", true));
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        return RefundAirline;
    }
}
