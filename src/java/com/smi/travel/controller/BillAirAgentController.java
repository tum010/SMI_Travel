package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.Agent;
import com.smi.travel.datalayer.entity.MAccterm;
import com.smi.travel.datalayer.entity.MDefaultData;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.SMITravelController;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class BillAirAgentController extends SMITravelController {
    private static final ModelAndView BillAirAgent = new ModelAndView("BillAirAgent");
    private static final ModelAndView BillAirAgent_REFRESH = new ModelAndView(new RedirectView("BillAirAgent.smi", true));
    private UtilityService utilityService;
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        
        //List Agent
        List<Agent> listAgent = new LinkedList<Agent>();
        listAgent = utilityService.getListAgent();
        if(listAgent != null){
            request.setAttribute("listAgent", listAgent);
        }else{
            request.setAttribute("listAgent", null);
        }
        
        // List Sale
        List<SystemUser> userList = new LinkedList<SystemUser>();
        userList = utilityService.getUserList();
        if(userList != null){
            request.setAttribute("userList", userList);
        }else{
            request.setAttribute("userList", null);
        }
        
        // List Term Pay
        List<MAccterm> listTermPay = new LinkedList<MAccterm>();
        listTermPay = utilityService.getListMAccterm();
        if(listTermPay != null){
            request.setAttribute("listTermPay", listTermPay);
        }else{
            request.setAttribute("listTermPay", null);
        }
        
        MDefaultData mDE = utilityService.getMDefaultDataFromType("vat");
        MDefaultData mDE2 = utilityService.getMDefaultDataFromType("withholding tax");
        String vatMDE = mDE.getValue();
        String whtMDE = mDE2.getValue();
        if(vatMDE != null && !"".equals(vatMDE)){
            System.out.println("Vat Bill Air Agent : " + vatMDE);
            request.setAttribute("vatMDE", vatMDE);
        }else{
            request.setAttribute("vatMDE", null);
        }
        
        if(whtMDE != null && !"".equals(whtMDE)){
            System.out.println("Wht Bill Air Agent : " + whtMDE);
            request.setAttribute("whtMDE", whtMDE);
        }else{
            request.setAttribute("whtMDE", null);
        }
        
        return BillAirAgent;
    }

    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }
    
}
