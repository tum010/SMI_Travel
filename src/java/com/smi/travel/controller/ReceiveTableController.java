package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.MAccpay;
import com.smi.travel.datalayer.entity.MAccterm;
import com.smi.travel.datalayer.entity.MCreditBank;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.CustomerAgentInfo;
import com.smi.travel.master.controller.SMITravelController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class ReceiveTableController extends SMITravelController {
    private static final ModelAndView ReceiveTable = new ModelAndView("ReceiveTable");
    private static final ModelAndView ReceiveTable_REFRESH = new ModelAndView(new RedirectView("ReceiveTable.smi", true));
    private static final String CUSTOMERAGENTINFOLIST = "customerAgentInfoList";
    private static final String MACCPAYLIST = "mAccpayList";
    private static final String MCREDITBANKLIST = "mCreditBankList";
    private UtilityService utilservice;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        List<CustomerAgentInfo> customerAgentInfoList = getUtilservice().getListCustomerAgentInfo();
        request.setAttribute(CUSTOMERAGENTINFOLIST, customerAgentInfoList);
        List<MAccpay> mAccpayList = getUtilservice().getListMAccpay();
        request.setAttribute(MACCPAYLIST, mAccpayList);
        List<MCreditBank> mCreditBankList = getUtilservice().getListCreditBank();
        request.setAttribute(MCREDITBANKLIST, mCreditBankList);
        return ReceiveTable;
    }

    public UtilityService getUtilservice() {
        return utilservice;
    }

    public void setUtilservice(UtilityService utilservice) {
        this.utilservice = utilservice;
    }
}
