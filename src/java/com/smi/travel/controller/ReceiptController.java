package com.smi.travel.controller;
import com.smi.travel.master.controller.SMITravelController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class ReceiptController extends SMITravelController {
    private static final ModelAndView Receipt = new ModelAndView("Receipt");
    private static final ModelAndView Receipt_REFRESH = new ModelAndView(new RedirectView("Receipt.smi", true));
    private static final String PVList = "PVList";
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String callPageFrom = request.getParameter("type");
        String paymentNo = request.getParameter("paymentNo");

        if(callPageFrom != null){
           String[] type = callPageFrom.split("\\?");
           request.setAttribute("typeInvoice", type[0]);  
        }
        
        if ("new".equalsIgnoreCase(action)) {

        } else if ("edit".equalsIgnoreCase(action)) {
            System.out.print("paymentNo : "+ paymentNo);
        }    
            
        return Receipt;
    }
}
