package com.smi.travel.masterdata.controller;
import com.smi.travel.master.controller.SMITravelController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class DefineVarController extends SMITravelController {
    private static final ModelAndView DefineVar = new ModelAndView("DefineVar");
    private static final ModelAndView DefineVar_REFRESH = new ModelAndView(new RedirectView("DefineVar.smi", true));
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        return DefineVar;
    }
}
