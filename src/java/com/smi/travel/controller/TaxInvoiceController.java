package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.InvoiceDetail;
import com.smi.travel.datalayer.entity.MBilltype;
import com.smi.travel.datalayer.entity.MCurrency;
import com.smi.travel.datalayer.entity.MDefaultData;
import com.smi.travel.datalayer.entity.MFinanceItemstatus;
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
        String roleName = user.getRole().getName();
        if("Finance Manager".equalsIgnoreCase(roleName)){
            roleName = "YES";
            request.setAttribute("roleName", roleName);
        }else{
            roleName = "NO";
            request.setAttribute("roleName", roleName);
        }
        
        List<String> RefNoList =  paymentTourHotelService.getMasterAll();       
        request.setAttribute(REFNOLIST, RefNoList);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        System.out.println(dateFormat.format(cal.getTime()));
//        String dateData = dateFormat.format(cal.getTime());
//        Date date = new Date();
//        date = utilty.convertStringToDate(dateData);
        
        String action = request.getParameter("action");
        String taxInvNo = request.getParameter("TaxInvNo");
        String taxInvId = request.getParameter("TaxInvId");
        String taxInvTo = request.getParameter("TaxInvTo");
        String invToDate = request.getParameter("InvToDate");
        String invToName = request.getParameter("InvToName");
        String invToAddress = request.getParameter("InvToAddress");
        String arCode = request.getParameter("ARCode");
        String remark = request.getParameter("Remark");
        String taxInvStatus = request.getParameter("TaxInvStatus");
        String createDate = request.getParameter("createDate");
        String createBy = request.getParameter("createBy");
        String count = request.getParameter("countTaxInvoice");
        String vatDefault = request.getParameter("vatDefault");
        String department = request.getParameter("department");
        String checkInvoiceDetail = "";
        String page = "";
        String result = "";
        if("W".equalsIgnoreCase(callPageFrom)){
            page = "Wendy";
        } else if("O".equalsIgnoreCase(callPageFrom)){
            page = "Outbound";
        } else if("I".equalsIgnoreCase(callPageFrom)){
            page = "Inbound";
        }
        
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
            
            MFinanceItemstatus mFinanceItemstatus = new MFinanceItemstatus();
            if(taxInvId=="" || taxInvId==null){
                taxInvoice.setCreateBy(username);
                
                mFinanceItemstatus.setId("1");
                taxInvoice.setMFinanceItemstatus(mFinanceItemstatus);
                taxInvoice.setDepartment(page);
                
                createDate = dateFormat.format(cal.getTime());              
                date = utilty.convertStringToDate(createDate);
                taxInvoice.setCreateDate(date);
                
            } else {
                taxInvoice.setTaxNo(taxInvNo);               
                taxInvoice.setCreateBy(createBy);
                mFinanceItemstatus.setId(taxInvStatus);
                taxInvoice.setMFinanceItemstatus(mFinanceItemstatus);
                
                invToDateConvert = utilty.convertStringToDate(createDate);
                taxInvoice.setCreateDate(invToDateConvert);
                taxInvoice.setDepartment(department);
            }
            
            if(Integer.parseInt(count) > 1){
                setTaxInvoiceDetails(request, count, taxInvoice, username, date, vatDefault, createBy, invToDateConvert);
                checkInvoiceDetail = checkInvoiceDetail(taxInvoice);
            }
            
            if("success".equalsIgnoreCase(checkInvoiceDetail)){
                result = taxInvoiceService.saveInvoice(taxInvoice);
            } else {
                result = checkInvoiceDetail;
            }
           
            List<TaxInvoiceDetail> taxInvoiceList = new ArrayList<TaxInvoiceDetail>();
            taxInvoiceList = taxInvoice.getTaxInvoiceDetails();
            request.setAttribute(TAXINVOICE, taxInvoice);
            request.setAttribute("invToDate", invToDate);
            request.setAttribute("createDate", createDate);
            request.setAttribute(TAXINVOICEDETAILLIST, taxInvoiceList);
            request.setAttribute(RESULTTEXT, result);
            
        } else if("search".equalsIgnoreCase(action)){
            TaxInvoice taxInvoice = new TaxInvoice();
            taxInvoice = taxInvoiceService.getTaxInvoiceFromTaxInvNo(taxInvNo,page);
            if(taxInvoice==null){
                request.setAttribute(RESULTTEXT, "not found");
                return new ModelAndView(LINKNAME+callPageFrom);
            }
            List<TaxInvoiceDetail> taxInvoiceList = new ArrayList<TaxInvoiceDetail>();
            taxInvoiceList = taxInvoice.getTaxInvoiceDetails();
            request.setAttribute(TAXINVOICE, taxInvoice);
            request.setAttribute("invToDate", taxInvoice.getTaxInvDate());
            request.setAttribute("createDate", taxInvoice.getCreateDate());
            request.setAttribute(TAXINVOICEDETAILLIST, taxInvoiceList);
            
        } else if("deleteTaxInvoiceDetail".equalsIgnoreCase(action)){
            String taxInvoiceDetailId = request.getParameter("taxInvoiceDetailId");
            TaxInvoiceDetail taxInvoiceDetail = new TaxInvoiceDetail();
            taxInvoiceDetail.setId(taxInvoiceDetailId);
            result = taxInvoiceService.DeleteTaxInvoiceInvoiceDetail(taxInvoiceDetail);
            System.out.println(result);
            
        } else if("enableVoid".equalsIgnoreCase(action)){
            TaxInvoice taxInvoice = new TaxInvoice();
            taxInvoice.setId(taxInvId);
            taxInvoice.setTaxInvTo(taxInvTo);
            taxInvoice.setTaxInvName(invToName);
            taxInvoice.setTaxInvAddr(invToAddress);
            taxInvoice.setArCode(arCode);
            taxInvoice.setRemark(remark); 
            taxInvoice.setTaxNo(taxInvNo);               
            taxInvoice.setCreateBy(createBy);
            
            Date invToDateConvert = new Date();
            invToDateConvert = utilty.convertStringToDate(invToDate);
            taxInvoice.setTaxInvDate(invToDateConvert);
            
            MFinanceItemstatus mFinanceItemstatus = new MFinanceItemstatus();
            mFinanceItemstatus.setId("1");
            mFinanceItemstatus.setName("NORMAL");
            taxInvoice.setMFinanceItemstatus(mFinanceItemstatus);
            
            invToDateConvert = utilty.convertStringToDate(createDate);
            taxInvoice.setCreateDate(invToDateConvert);
            
            if(Integer.parseInt(count) > 1){
                setTaxInvoiceDetails(request, count, taxInvoice, username, date, vatDefault, createBy, invToDateConvert);
            }
            
            result = taxInvoiceService.saveInvoice(taxInvoice);
            List<TaxInvoiceDetail> taxInvoiceList = new ArrayList<TaxInvoiceDetail>();
            taxInvoiceList = taxInvoice.getTaxInvoiceDetails();
            request.setAttribute(TAXINVOICE, taxInvoice);
            request.setAttribute("invToDate", invToDate);
            request.setAttribute("createDate", createDate);
            request.setAttribute(TAXINVOICEDETAILLIST, taxInvoiceList);
            request.setAttribute(RESULTTEXT, result);
            
        } else if("disableVoid".equalsIgnoreCase(action)){
            TaxInvoice taxInvoice = new TaxInvoice();
            taxInvoice.setId(taxInvId);
            taxInvoice.setTaxInvTo(taxInvTo);
            taxInvoice.setTaxInvName(invToName);
            taxInvoice.setTaxInvAddr(invToAddress);
            taxInvoice.setArCode(arCode);
            taxInvoice.setRemark(remark); 
            taxInvoice.setTaxNo(taxInvNo);               
            taxInvoice.setCreateBy(createBy);
            
            Date invToDateConvert = new Date();
            invToDateConvert = utilty.convertStringToDate(invToDate);
            taxInvoice.setTaxInvDate(invToDateConvert);
            
            MFinanceItemstatus mFinanceItemstatus = new MFinanceItemstatus();
            mFinanceItemstatus.setId("2");
            mFinanceItemstatus.setName("VOID");
            taxInvoice.setMFinanceItemstatus(mFinanceItemstatus);
            
            invToDateConvert = utilty.convertStringToDate(createDate);
            taxInvoice.setCreateDate(invToDateConvert);
            
            if(Integer.parseInt(count) > 1){
                setTaxInvoiceDetails(request, count, taxInvoice, username, date, vatDefault, createBy, invToDateConvert);               
            }
            
            result = taxInvoiceService.saveInvoice(taxInvoice);
            List<TaxInvoiceDetail> taxInvoiceList = new ArrayList<TaxInvoiceDetail>();
            taxInvoiceList = taxInvoice.getTaxInvoiceDetails();
            request.setAttribute(TAXINVOICE, taxInvoice);
            request.setAttribute("invToDate", invToDate);
            request.setAttribute("createDate", createDate);
            request.setAttribute(TAXINVOICEDETAILLIST, taxInvoiceList);
            if("success".equalsIgnoreCase(result)){
                request.setAttribute(RESULTTEXT, "disableVoid success");
            } else {
                request.setAttribute(RESULTTEXT, "disableVoid success");
            }
                       
        } else if("edit".equalsIgnoreCase(action)){
            if((!"".equalsIgnoreCase(taxInvId)) && (taxInvId != null)){
                TaxInvoice taxInvoice = new TaxInvoice();
                taxInvoice = taxInvoiceService.getTaxInvoiceFromTaxInvNo(taxInvNo,department);            
                List<TaxInvoiceDetail> taxInvoiceList = new ArrayList<TaxInvoiceDetail>();
                taxInvoiceList = taxInvoice.getTaxInvoiceDetails();
                request.setAttribute(TAXINVOICE, taxInvoice);
                request.setAttribute("invToDate", taxInvoice.getTaxInvDate());
                request.setAttribute("createDate", taxInvoice.getCreateDate());
                request.setAttribute(TAXINVOICEDETAILLIST, taxInvoiceList);
            }         
        }
        
               
        return new ModelAndView(LINKNAME+callPageFrom);
    }
    
    private void setTaxInvoiceDetails(HttpServletRequest request, String count, TaxInvoice taxInvoice, String username, Date date, String vat, String createBy, Date createDate) {
        util = new UtilityFunction();
        int rows = Integer.parseInt(count);
        if(taxInvoice.getTaxInvoiceDetails() == null){
            taxInvoice.setTaxInvoiceDetails(new ArrayList<TaxInvoiceDetail>());
        }
        for(int i=1;i<=rows;i++){
            String taxDetailId = request.getParameter("taxDetailId" + i);
            String invoiceDetailId = request.getParameter("invoiceDetailId" + i);
            String invoiceDetailCost = request.getParameter("invoiceDetailCost" + i);
            String invoiceDetailAmount = request.getParameter("invoiceDetailAmount" + i);
            String product = request.getParameter("product" + i);
            String refNo = request.getParameter("refNo" + i);
            String description = request.getParameter("description" + i);
            String cost = request.getParameter("cost" + i);
            String currencyCost = request.getParameter("currencyCost" + i);
            String isVat = request.getParameter("isVat" + i);
//            String vat = request.getParameter("vat" + i);
            String amount = request.getParameter("amount" + i);
            String currencyAmount = request.getParameter("currencyAmount" + i);
            
            TaxInvoiceDetail taxInvoiceDetail = new TaxInvoiceDetail();
            MBilltype mBillType = new MBilltype();
            InvoiceDetail invoiceDetail = new InvoiceDetail();
            
            if((product!="" && product!=null) || (refNo!="" && refNo!=null) || (description!="" && description!=null) || (cost!="" && cost!=null) || (currencyCost!="" && currencyCost!=null) || (isVat!="" && isVat!=null) || (amount!="" && amount!=null) || (currencyAmount!="" && currencyAmount!=null)){                               
                
                if(taxDetailId!="" && taxDetailId!=null){
                    taxInvoiceDetail.setId(taxDetailId);
                    taxInvoiceDetail.setCreateDate(createDate);
                    taxInvoiceDetail.setCreateBy(createBy);
                } else {
                    taxInvoiceDetail.setCreateDate(date);
                    taxInvoiceDetail.setCreateBy(username);
                }
                
                if(invoiceDetailId!="" && invoiceDetailId!=null){
                    invoiceDetail.setId(invoiceDetailId);
                    BigDecimal invoiceDetailCostRe = new BigDecimal(invoiceDetailCost.replaceAll(",",""));
                    invoiceDetail.setCost(invoiceDetailCostRe);
                    BigDecimal invoiceDetailAmountRe = new BigDecimal(invoiceDetailAmount.replaceAll(",",""));
                    invoiceDetail.setAmount(invoiceDetailAmountRe);
                    taxInvoiceDetail.setInvoiceDetail(invoiceDetail);
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
                    BigDecimal vatRe = new BigDecimal(vat);
                    taxInvoiceDetail.setVat(vatRe);
                } else {
                    taxInvoiceDetail.setIsVat(0);
                }
                
//                if(vat!="" && vat!=null){
//                    BigDecimal vatRe = new BigDecimal(vat);
//                    taxInvoiceDetail.setVat(vatRe);
//                }        
                
                if(amount!="" && amount!=null){
                    BigDecimal amountRe = new BigDecimal(amount.replaceAll(",",""));
                    taxInvoiceDetail.setAmount(amountRe);
                }
                
                if(currencyAmount!="" && currencyAmount!=null){
                    taxInvoiceDetail.setCurAmount(currencyAmount);
                }
                
                taxInvoiceDetail.setTaxInvoice(taxInvoice);
                taxInvoice.getTaxInvoiceDetails().add(taxInvoiceDetail);
            }                        
            
        }
    }
    
    private String checkInvoiceDetail(TaxInvoice taxInvoice) {
        String result = "";
        List<String> idList = new ArrayList<String>();
        List<BigDecimal> costList = new ArrayList<BigDecimal>();
        List<BigDecimal> amountList = new ArrayList<BigDecimal>();
        List<BigDecimal> costLocalList = new ArrayList<BigDecimal>();
        List<BigDecimal> amountLocalList = new ArrayList<BigDecimal>();
        List<TaxInvoiceDetail> taxInvDetailList = new ArrayList<TaxInvoiceDetail>();
        taxInvDetailList = taxInvoice.getTaxInvoiceDetails();
        
        for(int i=0;i<taxInvDetailList.size();i++){
            if(taxInvDetailList.get(i).getInvoiceDetail() != null){
                int check = 0;
                InvoiceDetail invoiceDetail1 = taxInvDetailList.get(i).getInvoiceDetail();
                TaxInvoiceDetail taxInvoiceDetail1 = taxInvDetailList.get(i);
                BigDecimal costTotal = new BigDecimal(0);
                BigDecimal amountTotal = new BigDecimal(0);               
                String id1 = invoiceDetail1.getId();
                
//                if(!idList.isEmpty()){
                    for(int a=0;a<idList.size();a++){
                        String idCheck = idList.get(a);
                        if(id1.equalsIgnoreCase(idCheck)){
                            check++;
                        }
                    }
                    
                    if(check == 0){
                        BigDecimal cost1 = taxInvoiceDetail1.getCost();
                        BigDecimal amount1 = taxInvoiceDetail1.getAmount();

                        costTotal = costTotal.add(cost1);
                        amountTotal = amountTotal.add(amount1);

                        for(int j=i+1;j<taxInvDetailList.size();j++){
                            if(taxInvDetailList.get(j).getInvoiceDetail() != null){
                                InvoiceDetail invoiceDetail2 = taxInvDetailList.get(j).getInvoiceDetail();
                                TaxInvoiceDetail taxInvoiceDetail2 = taxInvDetailList.get(i);
                                String id2 = invoiceDetail2.getId();
                                if(id2.equalsIgnoreCase(id1)){
                                    BigDecimal cost2 = taxInvoiceDetail2.getCost();
                                    BigDecimal amount2 = taxInvoiceDetail2.getAmount();
                                    costTotal = costTotal.add(cost2);
                                    amountTotal = amountTotal.add(amount2);
                                }
                            }    
                        }
                        idList.add(id1);
                        costList.add(costTotal);
                        amountList.add(amountTotal);
                        costLocalList.add(invoiceDetail1.getCost());
                        amountLocalList.add(invoiceDetail1.getAmount());
                    }
//                }               
            }    
        }
        
        for(int i=0;i<idList.size();i++){
            String id = idList.get(i);
            BigDecimal costTotal = new BigDecimal(0);
            BigDecimal amountTotal = new BigDecimal(0);
            BigDecimal cost = costList.get(i);
            BigDecimal amount = amountList.get(i);
            BigDecimal costLocal = costLocalList.get(i);
            BigDecimal amountLocal = amountLocalList.get(i);
            costTotal = costLocal.subtract(cost);
            amountTotal = amountLocal.subtract(amount);
            if((costTotal.compareTo(BigDecimal.ZERO) < 0)){
                result = "cost much over";
                return result;
            } else if((amountTotal.compareTo(BigDecimal.ZERO) < 0)){
                result = "amount much over";
                return result;
            }
//            result = taxInvoiceService.checkInvoiceDetailValue(id,cost,amount);
        }
        result = "success";
        
        return result;
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
