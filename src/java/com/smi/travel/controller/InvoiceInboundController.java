package com.smi.travel.controller;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class InvoiceInboundController extends SMITravelController {
    private static final ModelAndView InvoiceInbound = new ModelAndView("InvoiceInbound");
    private static final ModelAndView InvoiceInbound_REFRESH = new ModelAndView(new RedirectView("InvoiceInbound.smi", true));
    private UtilityService utilityService;
    UtilityFunction utilty = new UtilityFunction();
    private static final String LINKNAME = "InvoiceInbound";
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        System.out.println("request.getRequestURI() :"+request.getRequestURI());
        String callPageFrom = utilty.getAddressUrl(request.getRequestURI()).replaceAll(LINKNAME, "");//request.getParameter("type");
        //Attribute Invoice
        System.out.println("callPageFrom : "+callPageFrom);
        if(callPageFrom != null){
           //String[] type = callPageFrom.split("\\?");
           request.setAttribute("typeInvoice", callPageFrom.substring(1));  
        }
        String action = request.getParameter("action");
        
        request.setAttribute("page", callPageFrom);
        return new ModelAndView(LINKNAME+callPageFrom);
    }

    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }
    
    
}
