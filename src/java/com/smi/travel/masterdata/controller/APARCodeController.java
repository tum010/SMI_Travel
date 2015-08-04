package com.smi.travel.masterdata.controller;
import com.smi.travel.master.controller.SMITravelController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class APARCodeController extends SMITravelController {
    private static final ModelAndView APARCode = new ModelAndView("APARCode");
    private static final ModelAndView APARCode_REFRESH = new ModelAndView(new RedirectView("APARCode.smi", true));
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        return APARCode;
    }
}
