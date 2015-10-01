package com.smi.travel.masterdata.controller;
import com.smi.travel.master.controller.SMITravelController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class MHostController extends SMITravelController {
    private static final ModelAndView MHost = new ModelAndView("MHost");
    private static final ModelAndView MHost_REFRESH = new ModelAndView(new RedirectView("MHost.smi", true));
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        return MHost;
    }
}
