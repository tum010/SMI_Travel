package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.MBilltype;
import com.smi.travel.datalayer.entity.MCurrency;
import com.smi.travel.datalayer.entity.MDefaultData;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.entity.TaxInvoice;
import com.smi.travel.datalayer.service.PaymentTourHotelService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.CustomerAgentInfo;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class TaxInvoiceController extends SMITravelController {
    private static final ModelAndView TaxInvoice = new ModelAndView("TaxInvoice");
    private static final String LINKNAME = "TaxInvoice";
    private static final ModelAndView TaxInvoice_REFRESH = new ModelAndView(new RedirectView("TaxInvoice.smi", true));
    private UtilityService utilservice;
    UtilityFunction util;
    private static final String PRODUCTLIST = "productList";
    private static final String CURRENCYLIST = "currencyList";
    private static final String CUSTOMERAGENTLIST = "customerAgentList";
    private static final String REFNOLIST ="refNo_list";
    private static final String VATDEFAULT ="vatDefault";
    private PaymentTourHotelService paymentTourHotelService;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        UtilityFunction utilty = new UtilityFunction();
        System.out.println("request.getRequestURI() :"+request.getRequestURI());
        String callPageFrom = utilty.getAddressUrl(request.getRequestURI()).replaceAll(LINKNAME, "");//request.getParameter("type");
        String Department = "";
        if(callPageFrom != null){        
           Department =  callPageFrom;
        }
        request.setAttribute("page", callPageFrom);
        
        List<MBilltype> billTypeList = utilservice.getListMBilltype();
        request.setAttribute(PRODUCTLIST, billTypeList);
        List<MCurrency> currencyList = utilservice.getListMCurrency();
        request.setAttribute(CURRENCYLIST, currencyList);
        List<CustomerAgentInfo> customerAgentList = utilservice.getListCustomerAgentInfo();
        request.setAttribute(CUSTOMERAGENTLIST, customerAgentList);       
        MDefaultData mDefaultData = utilservice.getMDefaultDataFromType("vat");
        String nameDefault = mDefaultData.getName();
        BigDecimal vatDefault = new BigDecimal(mDefaultData.getValue());
        request.setAttribute(VATDEFAULT, vatDefault);
        
        SystemUser user = (SystemUser) session.getAttribute("USER");
        String idRole = user.getRole().getId();
        String idRoleName = user.getRole().getName();
        request.setAttribute("idRole", idRole);
        
        List<String> RefNoList =  paymentTourHotelService.getMasterAll();       
        request.setAttribute(REFNOLIST, RefNoList);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        System.out.println(dateFormat.format(cal.getTime()));
        String createDate = dateFormat.format(cal.getTime());
        Date date = new Date();
        date = utilty.convertStringToDate(createDate);
        
        String action = request.getParameter("action");
        String taxInvId = request.getParameter("TaxInvId");
        String taxInvTo = request.getParameter("TaxInvTo");
        String invToDate = request.getParameter("InvToDate");
        String invToName = request.getParameter("InvToName");
        String invToAddress = request.getParameter("InvToAddress");
        String arCode = request.getParameter("ARCode");
        String remark = request.getParameter("Remark");
        
        if("save".equalsIgnoreCase(action)){
            TaxInvoice taxInvoice = new TaxInvoice();
            taxInvoice.setId(taxInvId);
            taxInvoice.setTaxInvTo(taxInvTo);
            taxInvoice.setTaxInvName(invToName);
            taxInvoice.setTaxInvAddr(invToAddress);
            taxInvoice.setArCode(arCode);
//            taxInvoice.set
//            Date invToDateConvert = new Date();
//            invToDateConvert = utilty.convertStringToDate(invToDate);
//            taxInvoice.setTaxInvDate(invToDateConvert);
        }
        
               
        return new ModelAndView(LINKNAME+callPageFrom);
    }

    /**
     * @return the utilservice
     */
    public UtilityService getUtilservice() {
        return utilservice;
    }

    /**
     * @param utilservice the utilservice to set
     */
    public void setUtilservice(UtilityService utilservice) {
        this.utilservice = utilservice;
    }


}
