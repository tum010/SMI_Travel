package com.smi.travel.masterdata.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.smi.travel.master.controller.SMITravelController;

public class MAccountCodeController extends SMITravelController {
    private static final ModelAndView MAccountCode = new ModelAndView("MAccountCode");
    private static final ModelAndView MAccountCode_REFRESH = new ModelAndView(new RedirectView("MAccountCode.smi", true));
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        return MAccountCode;
    }
}
