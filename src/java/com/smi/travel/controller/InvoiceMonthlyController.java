package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.MBank;
import com.smi.travel.datalayer.entity.MDefaultData;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.CustomerAgentInfo;
import com.smi.travel.master.controller.SMITravelController;
import java.awt.Window;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class InvoiceMonthlyController extends SMITravelController {
    private static final ModelAndView InvoiceMonthly = new ModelAndView("InvoiceMonthly");
    private static final ModelAndView InvoiceMonthly_REFRESH = new ModelAndView(new RedirectView("InvoiceMonthly.smi", true));
    private UtilityService utilityService;
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
//        UtilityService utilityService = new UtilityService();
        //List Client 
        List<CustomerAgentInfo> listClient = new LinkedList<CustomerAgentInfo>();
        listClient = utilityService.getListCustomerAgentInfo();
        if(listClient != null){
            request.setAttribute("listClient", listClient);
        }else{
            request.setAttribute("listClient", null);
        }
        
        List<MBank> listAccno = new LinkedList<MBank>();
        listAccno = utilityService.getListBank();
        if(listAccno != null){
            request.setAttribute("listAccno", listAccno);
        }else{
            request.setAttribute("listAccno", null);
        }
        
        List<SystemUser> listStaff = new LinkedList<SystemUser>();
        listStaff = utilityService.getUserList();
        if(listStaff != null){
            request.setAttribute("listStaff", listStaff);
        }else{
            request.setAttribute("listStaff", null);
        }
        
        MDefaultData billingAttn = new MDefaultData();
        billingAttn = utilityService.getMDefaultDataFromType("billing_attn");
        request.setAttribute("billingAttn", billingAttn.getValue());
        
        MDefaultData billingFrom = new MDefaultData();
        billingFrom = utilityService.getMDefaultDataFromType("billing_from");
        request.setAttribute("billingFrom", billingFrom.getValue());
        
        MDefaultData billingTel = new MDefaultData();
        billingTel = utilityService.getMDefaultDataFromType("billing_tel");
        request.setAttribute("billingTel", billingTel.getValue());
        
        MDefaultData billingFax = new MDefaultData();
        billingFax = utilityService.getMDefaultDataFromType("billing_fax");
        request.setAttribute("billingFax", billingFax.getValue());
        
        MDefaultData billingMail = new MDefaultData();
        billingMail = utilityService.getMDefaultDataFromType("billing_mail");
        request.setAttribute("billingMail", billingMail.getValue());
        
        return InvoiceMonthly;
    }

    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }
}
