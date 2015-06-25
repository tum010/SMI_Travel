package com.smi.travel.controller;
import com.smi.travel.master.controller.SMITravelController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class SearchReceiptController extends SMITravelController {
    private static final ModelAndView SearchReceipt = new ModelAndView("SearchReceipt");
    private static final ModelAndView SearchReceipt_REFRESH = new ModelAndView(new RedirectView("SearchReceipt.smi", true));
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        return SearchReceipt;
    }
}
