package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.Agent;
import com.smi.travel.datalayer.entity.MAccterm;
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
public class TicketFareSummaryAirlineController extends SMITravelController {
    private static final ModelAndView TicketFareSummaryAirline = new ModelAndView("TicketFareSummaryAirline");
    private static final ModelAndView TicketFareSummaryAirline_REFRESH = new ModelAndView(new RedirectView("TicketFareSummaryAirline.smi", true));
    
    private static final String AIRLINELIST = "airlineList";
    private static final String AIRLINECODELIST = "airlineCodeList";
    private static final String USERLIST = "userList";
    private static final String TERMPAYLIST = "termPayList";
    private static final String AGENTLIST = "listAgent";

    
    private UtilityService utilityService;
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        
        List<SystemUser> userList = utilityService.getUserList();
        request.setAttribute(USERLIST,userList);
        
        //List Agent
        List<Agent> listAgent = utilityService.getListAgent();
        request.setAttribute(AGENTLIST,listAgent);
        
        //List Term pay
        List<MAccterm> mAcctermList = utilityService.getListMAccterm();
        request.setAttribute(TERMPAYLIST,mAcctermList);
        
//      request.setAttribute(AIRLINECODELIST,);
        return TicketFareSummaryAirline;
    }

    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }
}
