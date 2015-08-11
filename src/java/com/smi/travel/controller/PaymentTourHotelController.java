package com.smi.travel.controller;


import com.smi.travel.datalayer.entity.MAccpay;
import com.smi.travel.datalayer.entity.MCurrency;
import com.smi.travel.datalayer.entity.MDefaultData;
import com.smi.travel.datalayer.entity.MItemstatus;
import com.smi.travel.datalayer.entity.MPaymentDoctype;
import com.smi.travel.datalayer.entity.MPaytype;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.PaymentDetailWendy;
import com.smi.travel.datalayer.entity.PaymentWendy;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.entity.TourOperationDesc;
import com.smi.travel.datalayer.service.PaymentTourHotelService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.InvoiceSupplier;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

public class PaymentTourHotelController extends SMITravelController {
    
    private static final ModelAndView PaymentTourHotel = new ModelAndView("PaymentTourHotel");
    private static final ModelAndView PaymentTourHotel_REFRESH = new ModelAndView(new RedirectView("PaymentTourHotel.smi", true));
    private static final String PVTYPE ="pvType_list";
    private static final String STATUS ="status_list";
    private static final String INVOICESUPLIST ="invoiceSup_list";
    private static final String APCODELIST ="APcode_list";
    private static final String PAYMENHOTELTCOUNT ="paymenthotelcount";
    private static final String PAYMENTLIST ="payment_list";
    private static final String PRODUCTLIST ="product_list";
    private static final String CURRENCYLIST ="currency_list";
    private static final String PRODUCTDETAILLIST ="productDetail_list";
    private static final String REFNOLIST ="refNo_list";
    private UtilityService utilservice;
    UtilityFunction util;
    private PaymentTourHotelService paymentTourHotelService;
    private static final String TransectionResult = "result";
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
    
        List<MItemstatus> mItemstatusList = utilservice.getListMItemstatus();
        request.setAttribute(STATUS, mItemstatusList);
        List<MPaymentDoctype> mPaymentList = utilservice.getListMpaymentDocType("tourhotel");
        request.setAttribute(PVTYPE, mPaymentList);
        List<MAccpay> mAccpayList = utilservice.getListMAccpay();
        request.setAttribute(PAYMENTLIST, mAccpayList);
        List<InvoiceSupplier> invoiceSupplierList = paymentTourHotelService.getListInvoiceSuppiler();
        request.setAttribute(INVOICESUPLIST, invoiceSupplierList);
        List<MPaytype> mProdictList = utilservice.getListMPayType();
        request.setAttribute(PRODUCTLIST, mProdictList);
        List<MCurrency> mCurrencyList = utilservice.getListMCurrency();
        request.setAttribute(CURRENCYLIST, mCurrencyList);
        request.setAttribute(PAYMENHOTELTCOUNT, "0");
        //request.setAttribute("btnSave", "save");       
        
        String action = request.getParameter("action");
        String InputPayNo = request.getParameter("InputPayNo");
        String account = request.getParameter("account");
        String InputPayDate = request.getParameter("InputPayDate");
        String itemPvType = request.getParameter("itemPvType");
        String itemStatus = request.getParameter("itemStatus");
        String InputInvoiceSupId = request.getParameter("InputInvoiceSupId");      
        String InputInvoiceSupCode = request.getParameter("InputInvoiceSupCode");
        String InputInvoiceSupName = request.getParameter("InputInvoiceSupName");
        String InputAPCode = request.getParameter("InputAPCode");
        String Detail = request.getParameter("Detail");
        String itemPayment = request.getParameter("itemPayment");   
        String InputRemark = request.getParameter("InputRemark"); 
        String InputCurrency = request.getParameter("InputCurrency");
        String InputCash = "";
        String InputChqNo = "";
        String InputChqAmount = "";
        String counter = request.getParameter("counter");
        String crateDate = request.getParameter("crateDate");
        String paymentId = request.getParameter("paymentId");
        String tourDescId = request.getParameter("tourDescId");
        
        SystemUser user = (SystemUser) session.getAttribute("USER");
        String idRole = user.getRole().getId();
        String idRoleName = user.getRole().getName();
        request.setAttribute("idRole", idRole);
        
        MDefaultData mDefaultData = utilservice.getMDefaultDataFromType("vat");
        String nameDefaultData = mDefaultData.getName();
        BigDecimal vatDefaultData = new BigDecimal(mDefaultData.getValue());
        request.setAttribute("vatDefaultData", vatDefaultData);
        
        List<String> RefNoList =  paymentTourHotelService.getMasterAll();       
        request.setAttribute(REFNOLIST, RefNoList);

        if ("add".equalsIgnoreCase(action)) {
            
            if(paymentId == "" || paymentId == null){
                UtilityFunction utilfunction = new UtilityFunction();
                PaymentWendy paymentWendy = new PaymentWendy();
                               
                paymentWendy.setPayDate(utilfunction.convertStringToDate(InputPayDate));
                MItemstatus mitemStatus = new MItemstatus();
                mitemStatus.setId(itemStatus);
                paymentWendy.setMItemstatus(mitemStatus);
                paymentWendy.setInvoiceSup(InputInvoiceSupCode);
                paymentWendy.setApCode(InputAPCode);
                paymentWendy.setDetail(Detail);                
                paymentWendy.setRemark(InputRemark);
                paymentWendy.setCurrency(InputCurrency);           
                paymentWendy.setChqNo(InputChqNo);
                Date date = Calendar.getInstance().getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String createDate = sdf.format(date);
                paymentWendy.setCreateDate(utilfunction.convertStringToDate(createDate));
                
                if(account != null){
                    paymentWendy.setAccount(utilfunction.convertStringToInteger(account));
                } else {
                    paymentWendy.setAccount(null);
                }

                MPaymentDoctype mpaymentDoctype = new MPaymentDoctype();
                if(itemPvType==""){               
                    paymentWendy.setMPaymentDoctype(null);
                } else {
                    mpaymentDoctype.setId(itemPvType);
                    paymentWendy.setMPaymentDoctype(mpaymentDoctype);
                }

                MAccpay maccpay = new MAccpay();
                if(itemPayment==""){                        
                    paymentWendy.setMAccpay(null);
                } else {
                    maccpay.setId(itemPayment);          
                    paymentWendy.setMAccpay(maccpay);
                }

                if(InputCash!=""){
                    BigDecimal cash = new BigDecimal(InputCash.replaceAll(",",""));
                    paymentWendy.setCash(cash);                           
                }
                if(InputChqAmount!=""){
                    BigDecimal chqAmount = new BigDecimal(InputChqAmount.replaceAll(",",""));
                    paymentWendy.setChqAmount(chqAmount); 
                }                     
                if(utilfunction.convertStringToInteger(counter) != 0){
                    setPaymentDetailWendy(request, counter, paymentWendy);
                }
     
                paymentWendy.setCreateBy(user.getUsername());
                paymentWendy.setIsExport(0);

                String result = paymentTourHotelService.InsertPaymentWendy(paymentWendy);
                System.out.println("result : " + result);

                if(result == "fail"){
                    request.setAttribute("resultText", result);
                } else {
                    request.setAttribute("resultText", "success");
                }

                getPaymentDetailWendy(request, result);

                PaymentWendy paymentWendyId = paymentTourHotelService.getPaymentWendyFromID(result);
                request.setAttribute("paymentId", paymentWendyId.getId());
                request.setAttribute("payNo", paymentWendyId.getPayNo());
                request.setAttribute("crateDate", paymentWendyId.getCreateDate());
                if(paymentWendyId.getTourOperationDesc() != null){
                    request.setAttribute("tourDescId", paymentWendyId.getTourOperationDesc().getId());
                }
                
                
            } else {
                InputPayNo = request.getParameter("payNo");
                crateDate = request.getParameter("crateDate");
                UtilityFunction utilfunction = new UtilityFunction();
                PaymentWendy paymentWendy = new PaymentWendy();
                paymentWendy.setId(paymentId);
                paymentWendy.setPayNo(InputPayNo);
                paymentWendy.setPayDate(utilfunction.convertStringToDate(InputPayDate));
                MItemstatus mitemStatus = new MItemstatus();
                mitemStatus.setId(itemStatus);
                paymentWendy.setMItemstatus(mitemStatus);
                paymentWendy.setInvoiceSup(InputInvoiceSupCode);
                paymentWendy.setApCode(InputAPCode);
                paymentWendy.setDetail(Detail);
                paymentWendy.setCurrency(InputCurrency);        
                paymentWendy.setRemark(InputRemark);
                paymentWendy.setChqNo(InputChqNo);
                paymentWendy.setCreateDate(utilfunction.convertStringToDate(crateDate));
                
                if(account != null){
                    paymentWendy.setAccount(utilfunction.convertStringToInteger(account));
                } else {
                    paymentWendy.setAccount(null);
                }

                MPaymentDoctype mpaymentDoctype = new MPaymentDoctype();
                if(itemPvType==""){               
                    paymentWendy.setMPaymentDoctype(null);
                } else {
                    mpaymentDoctype.setId(itemPvType);
                    paymentWendy.setMPaymentDoctype(mpaymentDoctype);
                }

                MAccpay maccpay = new MAccpay();
                if(itemPayment==""){                        
                    paymentWendy.setMAccpay(null);
                } else {
                    maccpay.setId(itemPayment);          
                    paymentWendy.setMAccpay(maccpay);
                }            

                if(InputCash!=""){
                    BigDecimal cash = new BigDecimal(InputCash.replaceAll(",",""));
                    paymentWendy.setCash(cash);                           
                }
                if(InputChqAmount!=""){
                    BigDecimal chqAmount = new BigDecimal(InputChqAmount.replaceAll(",",""));
                    paymentWendy.setChqAmount(chqAmount); 
                }                     
                if(utilfunction.convertStringToInteger(counter) != 0){
                    setPaymentDetailWendy(request, counter, paymentWendy);
                }
                
                TourOperationDesc tourOperationDesc = new TourOperationDesc();
                tourOperationDesc.setId(tourDescId);
                paymentWendy.setTourOperationDesc(tourOperationDesc);
                
                paymentWendy.setCreateBy(user.getUsername());                              
                paymentWendy.setIsExport(0);
                
                String result = paymentTourHotelService.UpdatePaymentWendy(paymentWendy);
                request.setAttribute("paymentId", paymentId);
                request.setAttribute("payNo", InputPayNo);
                request.setAttribute("resultText", result);
                request.setAttribute("crateDate", crateDate);
                request.setAttribute("tourDescId", tourDescId);
                getPaymentDetailWendy(request, InputPayNo);
            }
          
        } else if("edit".equalsIgnoreCase(action)) {
            InputPayNo = request.getParameter("InputPayNo");
            System.out.println("InputPayNo : " + InputPayNo);
            
            PaymentWendy paymentWendy = paymentTourHotelService.getPaymentWendyFromID(InputPayNo);
            
            if(paymentWendy==null){
                request.setAttribute("resultText", "not found");
                return PaymentTourHotel;
            }
            
            paymentId = paymentWendy.getId();
            InputPayNo = paymentWendy.getPayNo();
            itemStatus = paymentWendy.getMItemstatus().getId();
            InputPayDate = String.valueOf(paymentWendy.getPayDate());                       
            InputInvoiceSupCode = paymentWendy.getInvoiceSup();
            Detail = paymentWendy.getDetail();
            InputCurrency = paymentWendy.getCurrency();
            InputRemark = paymentWendy.getRemark();
            InputCash = String.valueOf(paymentWendy.getCash());
            InputChqNo = paymentWendy.getChqNo();
            InputChqAmount = String.valueOf(paymentWendy.getChqAmount());
            crateDate = String.valueOf(paymentWendy.getCreateDate());
            tourDescId = String.valueOf(paymentWendy.getTourOperationDesc().getId());
            
            if(paymentWendy.getAccount() != null){
                account = String.valueOf(paymentWendy.getAccount()); 
            } else {
                account = "";
            }
            
            if(paymentWendy.getMPaymentDoctype()==null){
                itemPvType = "";
            } else {
                itemPvType = paymentWendy.getMPaymentDoctype().getId();
            }
            
             if(paymentWendy.getMAccpay()==null){                        
                itemPayment = "";
            } else {
                itemPayment = paymentWendy.getMAccpay().getId();
            }
                      
            InvoiceSupplier invoiceSupplierData = paymentTourHotelService.getDataInvoiceSuppiler(InputInvoiceSupCode);
            InputInvoiceSupId = invoiceSupplierData.getId();
            InputInvoiceSupName = invoiceSupplierData.getName();
            InputAPCode = invoiceSupplierData.getApcode();
            List<PaymentDetailWendy> paymentDetailWendyList = new ArrayList<PaymentDetailWendy>(paymentWendy.getPaymentDetailWendies());
            int i = 1;
            String size = String.valueOf(paymentDetailWendyList.size()+1);
            request.setAttribute("paymentId", paymentId);
            request.setAttribute("InputPayNo", InputPayNo);
            request.setAttribute("payNo", InputPayNo);
            request.setAttribute("account", account);
            request.setAttribute("InputPayDate", InputPayDate);
            request.setAttribute("itemPvType", itemPvType);
            request.setAttribute("itemStatus", itemStatus);
            request.setAttribute("InputInvoiceSupId", InputInvoiceSupId);
            request.setAttribute("InputInvoiceSupCode", InputInvoiceSupCode);
            request.setAttribute("InputInvoiceSupName", InputInvoiceSupName);
            request.setAttribute("InputAPCode", InputAPCode);
            request.setAttribute("Detail", Detail);
            request.setAttribute("itemPayment", itemPayment);
            request.setAttribute("InputCurrency", InputCurrency);        
            request.setAttribute("InputRemark", InputRemark);
            request.setAttribute("InputCash", InputCash);
            request.setAttribute("InputChqNo", InputChqNo);
            request.setAttribute("InputChqAmount", InputChqAmount);
            request.setAttribute("crateDate", crateDate);
            request.setAttribute("tourDescId", tourDescId);
            request.setAttribute(PRODUCTDETAILLIST, paymentDetailWendyList);
            request.setAttribute(PAYMENHOTELTCOUNT, size);
            //request.setAttribute("btnSave", "update");
           
        }else if("deleteProductDetail".equalsIgnoreCase(action)) {
            System.out.println("deleteProductDetail");
            String ProductDetail = request.getParameter("ProductDetail");
            PaymentDetailWendy paymentDetailWendy = new PaymentDetailWendy();
            paymentDetailWendy.setId(ProductDetail);
            String result = paymentTourHotelService.DeletePaymentWendyDetail(paymentDetailWendy);
            System.out.println(result);
        }else if("getVat".equalsIgnoreCase(action)) {
            
        }
        
        return PaymentTourHotel;
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

    /**
     * @return the paymentTourHotelService
     */
    public PaymentTourHotelService getPaymentTourHotelService() {
        return paymentTourHotelService;
    }

    /**
     * @param paymentTourHotelService the paymentTourHotelService to set
     */
    public void setPaymentTourHotelService(PaymentTourHotelService paymentTourHotelService) {
        this.paymentTourHotelService = paymentTourHotelService;
    }

    private void setPaymentDetailWendy(HttpServletRequest request, String counter, PaymentWendy paymentWendy) {
        util = new UtilityFunction();
        int Rows = Integer.parseInt(counter);
        if(paymentWendy.getPaymentDetailWendies() == null){
            paymentWendy.setPaymentDetailWendies(new ArrayList<PaymentDetailWendy>());
        }
       
        for (int i = 0; i <= Rows  ; i++) {
            PaymentDetailWendy paymentDetailWendy = new PaymentDetailWendy();
            String paymentDetailWendyId = request.getParameter("tableId" + i);
            String product = request.getParameter("select-product" + i);
            String refNo = request.getParameter("refNo" + i);
            String invNo = request.getParameter("invNo" + i);
            String code = request.getParameter("code" + i);
            String type = request.getParameter("type" + i);
            String amount = request.getParameter("amount" + i);           
            String description = request.getParameter("description" + i);
//            String ac = request.getParameter("ac" + i);
            String isVat = request.getParameter("isVat" + i);
            String vat = request.getParameter("vat" + i);
            String gross = request.getParameter("gross" + i);
            
            if((product!="" && product!=null) || (refNo!="" && refNo!=null) || (invNo!="" && invNo!=null) || (code!="" && code!=null) || (type!="" && type!=null) || (amount!="" && amount!=null) || (description!="" && description!=null)){
                
                if(amount!="" && amount!=null){
                    BigDecimal amountRe = new BigDecimal(amount.replaceAll(",",""));
                    System.out.println("amount" + i + ":" + amountRe);
                    paymentDetailWendy.setAmount(amountRe);
                }
                
                if("check".equalsIgnoreCase(isVat)){
                    paymentDetailWendy.setIsVat(1);
                } else {
                    paymentDetailWendy.setIsVat(0);
                }
                
                if(vat!="" && vat!=null){
                    Double vatRe = new Double(vat);
                    System.out.println("amount" + i + ":" + vatRe);
                    paymentDetailWendy.setVat(vatRe);
                }
                
                if(gross!="" && gross!=null){
                    BigDecimal grossRe = new BigDecimal(gross.replaceAll(",",""));
                    System.out.println("amount" + i + ":" + grossRe);
                    paymentDetailWendy.setGross(grossRe);
                }
                
                if(paymentDetailWendyId!=null && paymentDetailWendyId!=""){
                    paymentDetailWendy.setId(paymentDetailWendyId);                              
                    paymentDetailWendy.setAmountType(type);
                    paymentDetailWendy.setDescription(description);
                    paymentDetailWendy.setInvoiceCreditor(invNo);
                    paymentDetailWendy.setPaymentWendy(paymentWendy);
                    paymentDetailWendy.setRefCode(code);
                   
                    MPaytype mpayType = new MPaytype();                   
                    if(product==""){
                        paymentDetailWendy.setMPaytype(null);
                    } else {
                        mpayType.setId(product);
                        paymentDetailWendy.setMPaytype(mpayType);
                        String acc_code = paymentTourHotelService.getAccountCode(product);
                        paymentDetailWendy.setAccCode(acc_code);
                    }
                  
                    Master master = paymentTourHotelService.getMasterFromRefno(refNo);
                    if(master == null){
                        paymentDetailWendy.setMaster(null);
                    } else {
                        paymentDetailWendy.setMaster(master);                                          
                    }                  
                    
                    paymentWendy.getPaymentDetailWendies().add(paymentDetailWendy);
                    
                } else {         
                    paymentDetailWendy.setAmountType(type);
                    paymentDetailWendy.setDescription(description);
                    paymentDetailWendy.setInvoiceCreditor(invNo);
                    paymentDetailWendy.setPaymentWendy(paymentWendy);
                    paymentDetailWendy.setRefCode(code);
                    
                    MPaytype mpayType = new MPaytype();
                    if(product==""){
                        paymentDetailWendy.setMPaytype(null);
                    } else {
                        mpayType.setId(product);
                        paymentDetailWendy.setMPaytype(mpayType);
                        String acc_code = paymentTourHotelService.getAccountCode(product);
                        paymentDetailWendy.setAccCode(acc_code);
                    }                                     
                    
                    Master master = paymentTourHotelService.getMasterFromRefno(refNo);
                    if(master == null){
                        paymentDetailWendy.setMaster(null);
                    } else {
                        paymentDetailWendy.setMaster(master);                                          
                    }         
                    
                    paymentWendy.getPaymentDetailWendies().add(paymentDetailWendy);
                    
                }               
            }                        
        }
    }
    
  
    private void getPaymentDetailWendy(HttpServletRequest request, String InputPayNo) {
        String payNo = InputPayNo;
        System.out.println("payNo : " + payNo);
            
        PaymentWendy paymentWendy = paymentTourHotelService.getPaymentWendyFromID(payNo);
        InputPayNo = paymentWendy.getPayNo();
        String InputPayDate = String.valueOf(paymentWendy.getPayDate());                   
        String itemStatus = paymentWendy.getMItemstatus().getId();
//        String InputInvoiceSupId = paymentWendy.getInvoiceSup();
        String InputInvoiceSupCode = paymentWendy.getInvoiceSup();
        String Detail = paymentWendy.getDetail();
        String InputRemark = paymentWendy.getRemark();
        String InputCurrency = paymentWendy.getCurrency();
//        String InputCash = String.valueOf(paymentWendy.getCash());
//        String InputChqNo = paymentWendy.getChqNo();
//        String InputChqAmount = String.valueOf(paymentWendy.getChqAmount());
        String account = "";
        if(paymentWendy.getAccount() != null){
            account = String.valueOf(paymentWendy.getAccount());
        } else {
            account = "";
        }
        
        
        String itemPvType;
        if(paymentWendy.getMPaymentDoctype()==null){               
            itemPvType = "";
        } else {
            itemPvType = paymentWendy.getMPaymentDoctype().getId();
        }
        
        String itemPayment;
        if(paymentWendy.getMAccpay()==null){                        
            itemPayment = "";
        } else {
            itemPayment = paymentWendy.getMAccpay().getId();
        }
            
        InvoiceSupplier invoiceSupplierData = paymentTourHotelService.getDataInvoiceSuppiler(InputInvoiceSupCode);
        String InputInvoiceSupId = invoiceSupplierData.getId();
        String InputInvoiceSupName = invoiceSupplierData.getName();
        String InputAPCode = invoiceSupplierData.getApcode();
        
        List<PaymentDetailWendy> paymentDetailWendyList = new ArrayList<PaymentDetailWendy>(paymentWendy.getPaymentDetailWendies());
        int i = 1;
        String size = String.valueOf(paymentDetailWendyList.size()+1);
        
        request.setAttribute("InputPayNo", InputPayNo);
        request.setAttribute("account", account);
        request.setAttribute("InputPayDate", InputPayDate);
        request.setAttribute("itemPvType", itemPvType);
        request.setAttribute("itemStatus", itemStatus);
        request.setAttribute("InputInvoiceSupId", InputInvoiceSupId);
        request.setAttribute("InputInvoiceSupCode", InputInvoiceSupCode);
        request.setAttribute("InputInvoiceSupName", InputInvoiceSupName);
        request.setAttribute("InputAPCode", InputAPCode);
        request.setAttribute("Detail", Detail);
        request.setAttribute("InputCurrency", InputCurrency);
        request.setAttribute("itemPayment", itemPayment);
        request.setAttribute("InputRemark", InputRemark);
//        request.setAttribute("InputCash", InputCash);
//        request.setAttribute("InputChqNo", InputChqNo);
//        request.setAttribute("InputChqAmount", InputChqAmount);
        request.setAttribute(PRODUCTDETAILLIST, paymentDetailWendyList);
        request.setAttribute(PAYMENHOTELTCOUNT, size);
//        request.setAttribute("btnSave", "update");
    }

}
