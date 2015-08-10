package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.MBilltype;
import com.smi.travel.datalayer.entity.MCreditBank;
import com.smi.travel.datalayer.entity.MCurrency;
import com.smi.travel.datalayer.entity.MDefaultData;
import com.smi.travel.datalayer.entity.MItemstatus;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.CustomerAgentInfo;
import com.smi.travel.master.controller.SMITravelController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class ReceiptController extends SMITravelController {
    private static final ModelAndView Receipt = new ModelAndView("Receipt");
    private static final ModelAndView Receipt_REFRESH = new ModelAndView(new RedirectView("Receipt.smi", true));
    private static final String PVList = "PVList";
    private static final String CUSTOMERAGENT = "customerAgent";
    private static final String MBILLTYPELIST = "billTypeList";
    private static final String VAT = "vat";
    private static final String MCURRENCYLIST = "currencyList";
    private static final String MSTATUSLIST = "statusList";
    private static final String MCREDITBANKLIST = "creditBankList";
    private static final String PRODUCTROWCOUNT = "productRowCount";
    private UtilityService utilityService;

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String callPageFrom = request.getParameter("type");
        String paymentNo = request.getParameter("paymentNo");

        if(callPageFrom != null){
           String[] type = callPageFrom.split("\\?");
           request.setAttribute("typeInvoice", type[0]);  
        }
        
        if ("new".equalsIgnoreCase(action)) {

        } else if ("edit".equalsIgnoreCase(action)) {
            System.out.print("paymentNo : "+ paymentNo);
        }    
        setResponseAttribute(request);
        return Receipt;
    }
    
    public void setResponseAttribute(HttpServletRequest request) {
        List<CustomerAgentInfo> customerAgentInfo = utilityService.getListCustomerAgentInfo();
        request.setAttribute(CUSTOMERAGENT, customerAgentInfo);
        List<MBilltype> mBilltypes = utilityService.getListMBilltype();
        request.setAttribute(MBILLTYPELIST, mBilltypes); //receiveProduct
        MDefaultData mDefaultData = utilityService.getMDefaultDataFromType("vat");
        request.setAttribute(VAT,mDefaultData.getValue()); //vat
        List<MCurrency> mCurrencys = utilityService.getListMCurrency();
        request.setAttribute(MCURRENCYLIST, mCurrencys); //receiveCurrency
        List<MCreditBank> mCreditBanks = utilityService.getListCreditBank();
        request.setAttribute(MCREDITBANKLIST, mCreditBanks); //creditBankList
        List<MItemstatus> mItemstatuses = utilityService.getListMItemstatus();
        request.setAttribute(MSTATUSLIST, mItemstatuses); //statusList

        
        
        request.setAttribute(PRODUCTROWCOUNT, "0");
        
    }

    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }
}
