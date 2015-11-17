package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.MCurrency;
import com.smi.travel.datalayer.entity.MDefaultData;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.CustomerAgentInfo;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        String department = request.getParameter("Department");
        String invoiceType = request.getParameter("InputInvoiceType");
         String action = request.getParameter("action");
         
        System.out.println("callPageFrom : "+callPageFrom);
        if(callPageFrom != null){
           request.setAttribute("typeInvoice", callPageFrom.substring(1));
           department =  callPageFrom.substring(0,1);
           invoiceType   =  callPageFrom.substring(1);
        }
        System.out.println("invoiceType : "+invoiceType);
        System.out.println("department : "+department);
       
        //Role User
        SystemUser  user = (SystemUser) session.getAttribute("USER");
        String roleName = user.getRole().getName();
        if("Finance Manager".equals(roleName)){
            roleName = "YES";
            request.setAttribute("roleName", roleName);
        }else{
            roleName = "NO";
            request.setAttribute("roleName", roleName);
        }
              
        //List Currency
        List<MCurrency> listCurrency = new ArrayList<MCurrency>();
        listCurrency = utilityService.getListMCurrency();
        if(listCurrency != null){
            request.setAttribute("listCurrency", listCurrency);
        }else{
            request.setAttribute("listCurrency", null);
        }
        //Get Vat
        MDefaultData mDefaultVat = utilityService.getMDefaultDataFromType("vat");
        String vat = mDefaultVat.getValue();
        if(vat != null){
            request.setAttribute("vat", vat);
        }else{
            request.setAttribute("vat", null);
        }    
        // List Agent feild Invoice To
        List<CustomerAgentInfo> listCustomerAgentInfo = new ArrayList<CustomerAgentInfo>();
        listCustomerAgentInfo = utilityService.getListCustomerAgentInfo();
        if(listCustomerAgentInfo != null){
            request.setAttribute("listCustomerAgentInfo", listCustomerAgentInfo);
        }else{
            request.setAttribute("listCustomerAgentInfo", null);
        } 
        
        request.setAttribute("thisdate", utilty.convertDateToString(new Date()));
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
