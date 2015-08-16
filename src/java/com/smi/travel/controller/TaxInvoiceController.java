package com.smi.travel.controller;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class TaxInvoiceController extends SMITravelController {
    private static final ModelAndView TaxInvoice = new ModelAndView("TaxInvoice");
    private static final String LINKNAME = "TaxInvoice";
    private static final ModelAndView TaxInvoice_REFRESH = new ModelAndView(new RedirectView("TaxInvoice.smi", true));
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        UtilityFunction utilty = new UtilityFunction();
        System.out.println("request.getRequestURI() :"+request.getRequestURI());
        String callPageFrom = utilty.getAddressUrl(request.getRequestURI()).replaceAll(LINKNAME, "");//request.getParameter("type");
        String Department = "";
        if(callPageFrom != null){        
           Department =  callPageFrom;
        }
        
        request.setAttribute("page", callPageFrom);
        return new ModelAndView(LINKNAME+callPageFrom);
    }
}
