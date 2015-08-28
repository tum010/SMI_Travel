package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.MBank;
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
        return InvoiceMonthly;
    }

    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }
}
