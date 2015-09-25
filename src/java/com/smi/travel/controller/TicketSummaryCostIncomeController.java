package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.MAccterm;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.SMITravelController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class TicketSummaryCostIncomeController extends SMITravelController {
    private static final ModelAndView TicketSummaryCostIncome = new ModelAndView("TicketSummaryCostIncome");
    private static final ModelAndView TicketSummaryCostIncome_REFRESH = new ModelAndView(new RedirectView("TicketSummaryCostIncome.smi", true));
    private static final String USERLIST = "userList";
    private static final String TERMPAYLIST = "termPayList";
    private UtilityService utilityService;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        List<SystemUser> userList = utilityService.getUserList();
        request.setAttribute(USERLIST,userList);
        List<MAccterm> mAcctermList = utilityService.getListMAccterm();
        request.setAttribute(TERMPAYLIST,mAcctermList);
        
        return TicketSummaryCostIncome;
    }

    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }
}
