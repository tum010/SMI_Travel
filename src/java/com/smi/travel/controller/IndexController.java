package com.smi.travel.controller;
import com.smi.travel.master.controller.SMITravelController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
/**
 *
 * @author Surachai
 */
public class IndexController extends SMITravelController{
    private static final ModelAndView MAIN = new ModelAndView("main");
    private static final ModelAndView INDEX_REFRESH = new ModelAndView(new RedirectView("main.smi", true));
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        return MAIN;
    }
}
