package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.MBilltype;
import com.smi.travel.datalayer.entity.MCurrency;
import com.smi.travel.datalayer.entity.MDefaultData;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.entity.TaxInvoice;
import com.smi.travel.datalayer.entity.TaxInvoiceDetail;
import com.smi.travel.datalayer.service.PaymentTourHotelService;
import com.smi.travel.datalayer.service.TaxInvoiceService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.CustomerAgentInfo;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
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
    private static final String TAXINVOICE ="taxInvoice";
    private static final String TAXINVOICEDETAILLIST ="taxInvoiceDetail_list";
    private static final String RESULTTEXT ="result_text";
    private PaymentTourHotelService paymentTourHotelService;
    private TaxInvoiceService taxInvoiceService;
    
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
        BigDecimal vatData = new BigDecimal(mDefaultData.getValue());
        request.setAttribute(VATDEFAULT, vatData);
        
        SystemUser user = (SystemUser) session.getAttribute("USER");
        String idRole = user.getRole().getId();
        String username = user.getUsername();
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
        String TaxInvNo = request.getParameter("TaxInvNo");
        String taxInvId = request.getParameter("TaxInvId");
        String taxInvTo = request.getParameter("TaxInvTo");
        String invToDate = request.getParameter("InvToDate");
        String invToName = request.getParameter("InvToName");
        String invToAddress = request.getParameter("InvToAddress");
        String arCode = request.getParameter("ARCode");
        String remark = request.getParameter("Remark");
        String count = request.getParameter("countTaxInvoice");
        String vatDefault = request.getParameter("vatDefault");
        
        if("save".equalsIgnoreCase(action)){
            TaxInvoice taxInvoice = new TaxInvoice();
            taxInvoice.setId(taxInvId);
            taxInvoice.setTaxInvTo(taxInvTo);
            taxInvoice.setTaxInvName(invToName);
            taxInvoice.setTaxInvAddr(invToAddress);
            taxInvoice.setArCode(arCode);
            taxInvoice.setRemark(remark);
            Date invToDateConvert = new Date();
            invToDateConvert = utilty.convertStringToDate(invToDate);
            taxInvoice.setTaxInvDate(invToDateConvert);
            
            if(taxInvId=="" || taxInvId==null){
                taxInvoice.setCreateBy(username);
                taxInvoice.setCreateDate(date);
            }
            
            if(Integer.parseInt(count) > 1){
                setTaxInvoiceDetails(request, count, taxInvoice, username, date);
            }
            
            String result = taxInvoiceService.saveInvoice(taxInvoice);
            List<TaxInvoiceDetail> taxInvoiceList = new ArrayList<TaxInvoiceDetail>();
            taxInvoiceList = taxInvoice.getTaxInvoiceDetails();
            request.setAttribute(TAXINVOICE, taxInvoice);
            request.setAttribute("invToDate", invToDate);
            request.setAttribute(TAXINVOICEDETAILLIST, taxInvoiceList);
            request.setAttribute(RESULTTEXT, result);
            
        } else if("search".equalsIgnoreCase(action)){
            TaxInvoice taxInvoice = new TaxInvoice();
            taxInvoice = taxInvoiceService.getTaxInvoiceFromTaxInvNo(TaxInvNo);
            if(taxInvoice==null){
                request.setAttribute(RESULTTEXT, "not found");
                return new ModelAndView(LINKNAME+callPageFrom);
            }
            
            
        } else if("edit".equalsIgnoreCase(action)){

        }
        
               
        return new ModelAndView(LINKNAME+callPageFrom);
    }
    
    private void setTaxInvoiceDetails(HttpServletRequest request, String count, TaxInvoice taxInvoice, String createBy, Date createDate) {
        util = new UtilityFunction();
        int rows = Integer.parseInt(count);
        if(taxInvoice.getTaxInvoiceDetails() == null){
            taxInvoice.setTaxInvoiceDetails(new ArrayList<TaxInvoiceDetail>());
        }
        for(int i=1;i<=rows;i++){
            String taxDetailId = request.getParameter("taxDetailId" + i);
            String product = request.getParameter("product" + i);
            String refNo = request.getParameter("refNo" + i);
            String description = request.getParameter("description" + i);
            String cost = request.getParameter("cost" + i);
            String currencyCost = request.getParameter("currencyCost" + i);
            String isVat = request.getParameter("isVat" + i);
            String vat = request.getParameter("vat" + i);
            String amount = request.getParameter("amount" + i);
            String currencyAmount = request.getParameter("currencyAmount" + i);
            
            TaxInvoiceDetail taxInvoiceDetail = new TaxInvoiceDetail();
            MBilltype mBillType = new MBilltype();
            
            if((product!="" && product!=null) || (refNo!="" && refNo!=null) || (description!="" && description!=null) || (cost!="" && cost!=null) || (currencyCost!="" && currencyCost!=null) || (isVat!="" && isVat!=null) || (vat!="" && vat!=null) || (amount!="" && amount!=null) || (currencyAmount!="" && currencyAmount!=null)){                               
                
                if(taxDetailId!="" && taxDetailId!=null){
                    taxInvoiceDetail.setId(taxDetailId);                                                 
                } else {
                    taxInvoiceDetail.setCreateDate(createDate);
                    taxInvoiceDetail.setCreateBy(createBy);
                }
                
                if(product!="" && product!=null){
                    mBillType.setId(product);
                    taxInvoiceDetail.setMbillType(mBillType);
                } else {
                    taxInvoiceDetail.setMbillType(null);
                }
                
                Master master = paymentTourHotelService.getMasterFromRefno(refNo);
                if(master == null){
                    taxInvoiceDetail.setMaster(null);
                } else {
                    taxInvoiceDetail.setMaster(master);                                          
                }
                
                if(description!="" && description!=null){
                    taxInvoiceDetail.setDescription(description);
                }
                
                if(cost!="" && cost!=null){
                    BigDecimal costRe = new BigDecimal(cost.replaceAll(",",""));
                    taxInvoiceDetail.setCost(costRe);
                }
                
                if(currencyCost!="" && currencyCost!=null){
                    taxInvoiceDetail.setCurCost(currencyCost);
                }
                
                if("1".equalsIgnoreCase(isVat)){
                    taxInvoiceDetail.setIsVat(1);
                } else {
                    taxInvoiceDetail.setIsVat(0);
                }
                
                if(vat!="" && vat!=null){
                    BigDecimal vatRe = new BigDecimal(vat);
                    taxInvoiceDetail.setVat(vatRe);
                }        
                
                if(amount!="" && amount!=null){
                    BigDecimal amountRe = new BigDecimal(amount.replaceAll(",",""));
                    taxInvoiceDetail.setAmount(amountRe);
                }
                
                if(currencyAmount!="" && currencyAmount!=null){
                    taxInvoiceDetail.setCurAmount(currencyAmount);
                }
                
                taxInvoiceDetail.setInvoiceDetail(null);
                
                taxInvoiceDetail.setTaxInvoice(taxInvoice);
                taxInvoice.getTaxInvoiceDetails().add(taxInvoiceDetail);
            }                        
            
        }
    }

    public UtilityService getUtilservice() {
        return utilservice;
    }

    public void setUtilservice(UtilityService utilservice) {
        this.utilservice = utilservice;
    }

    public PaymentTourHotelService getPaymentTourHotelService() {
        return paymentTourHotelService;
    }

    public void setPaymentTourHotelService(PaymentTourHotelService paymentTourHotelService) {
        this.paymentTourHotelService = paymentTourHotelService;
    }

    public TaxInvoiceService getTaxInvoiceService() {
        return taxInvoiceService;
    }

    public void setTaxInvoiceService(TaxInvoiceService taxInvoiceService) {
        this.taxInvoiceService = taxInvoiceService;
    }
}
