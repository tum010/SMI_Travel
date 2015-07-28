package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.MAccterm;
import com.smi.travel.datalayer.entity.MCurrency;
import com.smi.travel.datalayer.entity.MDefaultData;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.CustomerAgentInfo;
import com.smi.travel.master.controller.SMITravelController;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class InvoiceController extends SMITravelController {
    private static final ModelAndView Invoice = new ModelAndView("Invoice");
    private static final ModelAndView Invoice_REFRESH = new ModelAndView(new RedirectView("Invoice.smi", true));
    private UtilityService utilityService;
    
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
              
        //List Currency
        List<MCurrency> listCurrency = new ArrayList<MCurrency>();
        listCurrency = utilityService.getListMCurrency();
        if(listCurrency != null){
            request.setAttribute("listCurrency", listCurrency);
        }else{
            request.setAttribute("listCurrency", null);
        }
        //List Default Data
        MDefaultData defaultData = new MDefaultData();
        defaultData = utilityService.getMDefaultDataFromType("vat");
        if(defaultData != null){
            request.setAttribute("defaultData", defaultData);
        }else{
            request.setAttribute("defaultData", null);
        }     
        // List Agent feild Invoice To
        List<CustomerAgentInfo> listCustomerAgentInfo = new ArrayList<CustomerAgentInfo>();
        listCustomerAgentInfo = utilityService.getListCustomerAgentInfo();
        if(listCustomerAgentInfo != null){
            request.setAttribute("listCustomerAgentInfo", listCustomerAgentInfo);
        }else{
            request.setAttribute("listCustomerAgentInfo", null);
        } 
        
        //List Staff
        List<SystemUser> listStaff = new ArrayList<SystemUser>();
        listStaff = utilityService.getUserList();
        if(listStaff != null){
            request.setAttribute("listStaff", listStaff);
        }else{
            request.setAttribute("listStaff", null);
        } 
        //List Term Pay
        List<MAccterm> listTermPay = new ArrayList<MAccterm>();
        listTermPay = utilityService.getListMAccterm();
        if(listTermPay != null){
            request.setAttribute("listTermPay", listTermPay);
        }else{
            request.setAttribute("listTermPay", null);
        } 
        
        return Invoice;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }

    public UtilityService getUtilityService() {
        return utilityService;
    }
    
    
}
