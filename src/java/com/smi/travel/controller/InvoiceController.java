package com.smi.travel.controller;
import com.smi.travel.master.controller.SMITravelController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class InvoiceController extends SMITravelController {
    private static final ModelAndView Invoice = new ModelAndView("Invoice");
    private static final ModelAndView Invoice_REFRESH = new ModelAndView(new RedirectView("Invoice.smi", true));
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String callPageFrom = request.getParameter("type");
        String buttonVoid = request.getParameter("buttonVoid");
        
        
        if(buttonVoid != null){
            if(buttonVoid.equalsIgnoreCase("enable")){
                request.setAttribute("button", buttonVoid);     
            } else if (buttonVoid.equalsIgnoreCase("disable")){
                request.setAttribute("buttonVoid", buttonVoid);        
            }
        }
        
        if(callPageFrom != null){
           String[] type = callPageFrom.split("\\?");
           request.setAttribute("typeInvoice", type[0]);  
        }
              
    
        return Invoice;
    }
}
