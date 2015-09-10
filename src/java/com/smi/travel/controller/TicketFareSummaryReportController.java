package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.MAccterm;
import com.smi.travel.datalayer.entity.MAirlineAgent;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.SMITravelController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

public class TicketFareSummaryReportController extends SMITravelController {
    private static final ModelAndView TicketFareSummaryReport = new ModelAndView("TicketFareSummaryReport");
    private static final ModelAndView TicketFareSummaryReport_REFRESH = new ModelAndView(new RedirectView("TicketFareSummaryReport.smi", true));
    private static final String AIRLINELIST = "airlineList";
    private static final String AIRLINECODELIST = "airlineCodeList";
    private static final String USERLIST = "userList";
    private static final String TERMPAYLIST = "termPayList";
    
    private UtilityService utilityService;
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        
        
        
        setResponseAttribute(request);
        return TicketFareSummaryReport;
    }
    
    private void setResponseAttribute(HttpServletRequest request) {
        List<MAirlineAgent> mAirlineAgentsList = utilityService.getListMAirLineAgent();
        request.setAttribute(AIRLINELIST,mAirlineAgentsList);
        
        request.setAttribute(AIRLINECODELIST,mAirlineAgentsList);
        
        List<SystemUser> userList = utilityService.getUserList();
        request.setAttribute(USERLIST,userList);
        
        List<MAccterm> mAcctermList = utilityService.getListMAccterm();
        request.setAttribute(TERMPAYLIST,mAcctermList);
    }

    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }
}
