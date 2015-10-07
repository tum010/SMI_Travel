package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.Agent;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.SMITravelController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class TicketRefundSummaryController extends SMITravelController {
    private static final ModelAndView TicketRefundSummary = new ModelAndView("TicketRefundSummary");
    private static final ModelAndView TicketRefundSummary_REFRESH = new ModelAndView(new RedirectView("TicketRefundSummary.smi", true));
    private UtilityService utilityService;
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        
        List<Agent> agent = utilityService.getListAgent();
        request.setAttribute("agent", agent);
        List customer = utilityService.getListCustomerAgentInfo();
        request.setAttribute("cust", customer);
        
        return TicketRefundSummary;
    }

    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }
}
