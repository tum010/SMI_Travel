package com.smi.travel.controller;


import com.smi.travel.datalayer.entity.MAccpay;
import com.smi.travel.datalayer.entity.MCurrency;
import com.smi.travel.datalayer.entity.MItemstatus;
import com.smi.travel.datalayer.entity.MPaymentDoctype;
import com.smi.travel.datalayer.entity.MPaytype;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.PaymentDetailWendy;
import com.smi.travel.datalayer.entity.PaymentWendy;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.PaymentTourHotelService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.InvoiceSupplier;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
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
    private UtilityService utilservice;
    UtilityFunction util;
    private PaymentTourHotelService paymentTourHotelService;
    private static final String TransectionResult = "result";
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
    
        List<MItemstatus> mItemstatusList = utilservice.getListMItemstatus();
        request.setAttribute(STATUS, mItemstatusList);
        List<MPaymentDoctype> mPaymentList = utilservice.getListMpaymentDocType();
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
        request.setAttribute("btnSave", "save");
        
        
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
        String InputCash = request.getParameter("InputCash");
        String InputChqNo = request.getParameter("InputChqNo");
        String InputChqAmount = request.getParameter("InputChqAmount");
        String counter = request.getParameter("counter");

        
        if ("add".equalsIgnoreCase(action)) {
            UtilityFunction utilfunction = new UtilityFunction();
            PaymentWendy paymentWendy = new PaymentWendy();
            paymentWendy.setPayNo(InputPayNo);
            paymentWendy.setAccount(utilfunction.convertStringToInteger(account));
            paymentWendy.setPayDate(utilfunction.convertStringToDate(InputPayDate));
            
            MItemstatus mitemStatus = new MItemstatus();
            mitemStatus.setId(itemStatus);
            paymentWendy.setMItemstatus(mitemStatus);
            paymentWendy.setInvoiceSup(InputInvoiceSupId);
            paymentWendy.setApCode(InputAPCode);
            paymentWendy.setDetail(Detail);
                 
            paymentWendy.setRemark(InputRemark);
            paymentWendy.setChqNo(InputChqNo);
            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String createDate = sdf.format(date);
            paymentWendy.setCreateDate(utilfunction.convertStringToDate(createDate));
            
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
            
            SystemUser user = (SystemUser) session.getAttribute("USER");
            paymentWendy.setCreateBy(user.getUsername());
            
            String result = paymentTourHotelService.InsertPaymentWendy(paymentWendy);
            System.out.println("result : " + result);
            request.setAttribute("resultText", result);
            getPaymentDetailWendy(request, InputPayNo);
            
            
        } else if("edit".equalsIgnoreCase(action)) {
            String payNo = request.getParameter("payNo");
            System.out.println("payNo : " + payNo);
            
            PaymentWendy paymentWendy = paymentTourHotelService.getPaymentWendyFromID(payNo);
            InputPayNo = paymentWendy.getPayNo();
            account = String.valueOf(paymentWendy.getAccount());
            InputPayDate = String.valueOf(paymentWendy.getPayDate());            
            itemPvType = paymentWendy.getMPaymentDoctype().getId();
            itemStatus = paymentWendy.getMItemstatus().getId();
            InputInvoiceSupId = paymentWendy.getInvoiceSup();
            Detail = paymentWendy.getDetail();
            itemPayment = paymentWendy.getMAccpay().getId();
            InputRemark = paymentWendy.getRemark();
            InputCash = String.valueOf(paymentWendy.getCash());
            InputChqNo = paymentWendy.getChqNo();
            InputChqAmount = String.valueOf(paymentWendy.getChqAmount());
            
            InvoiceSupplier invoiceSupplierData = paymentTourHotelService.getDataInvoiceSuppiler(InputInvoiceSupId);
            InputInvoiceSupCode = invoiceSupplierData.getCode();
            InputInvoiceSupName = invoiceSupplierData.getName();
            InputAPCode = invoiceSupplierData.getApcode();
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
            request.setAttribute("itemPayment", itemPayment);
            request.setAttribute("InputRemark", InputRemark);
            request.setAttribute("InputCash", InputCash);
            request.setAttribute("InputChqNo", InputChqNo);
            request.setAttribute("InputChqAmount", InputChqAmount);
            request.setAttribute(PRODUCTDETAILLIST, paymentDetailWendyList);
            request.setAttribute(PAYMENHOTELTCOUNT, size);
            request.setAttribute("btnSave", "update");
        
        }else if("update".equalsIgnoreCase(action)) {
            String payNo = request.getParameter("InputPayNo");
            UtilityFunction utilfunction = new UtilityFunction();
            PaymentWendy paymentWendy = new PaymentWendy();
            paymentWendy.setPayNo(InputPayNo);
            paymentWendy.setAccount(utilfunction.convertStringToInteger(account));
            paymentWendy.setPayDate(utilfunction.convertStringToDate(InputPayDate));
            MPaymentDoctype mpaymentDoctype = new MPaymentDoctype();
            mpaymentDoctype.setId(itemPvType);
            paymentWendy.setMPaymentDoctype(mpaymentDoctype);
            MItemstatus mitemStatus = new MItemstatus();
            mitemStatus.setId(itemStatus);
            paymentWendy.setMItemstatus(mitemStatus);
            paymentWendy.setInvoiceSup(InputInvoiceSupId);
            paymentWendy.setApCode(InputAPCode);
            paymentWendy.setDetail(Detail);
            MAccpay maccpay = new MAccpay();
            maccpay.setId(itemPayment);          
            paymentWendy.setMAccpay(maccpay);
            paymentWendy.setRemark(InputRemark);
            paymentWendy.setChqNo(InputChqNo);
             
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
            
            SystemUser user = (SystemUser) session.getAttribute("USER");
            paymentWendy.setCreateBy(user.getUsername());
            String result = paymentTourHotelService.UpdatePaymentWendy(paymentWendy);
            request.setAttribute("resultText", result);
            getPaymentDetailWendy(request, payNo);
            
        }else if("deleteProductDetail".equalsIgnoreCase(action)) {
            System.out.println("deleteProductDetail");
            String ProductDetail = request.getParameter("ProductDetail");
            PaymentDetailWendy paymentDetailWendy = new PaymentDetailWendy();
            paymentDetailWendy.setId(ProductDetail);
            String result = paymentTourHotelService.DeletePaymentWendyDetail(paymentDetailWendy);
            System.out.println(result);
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
            String selectCurrency = request.getParameter("select-currency" + i);
            String description = request.getParameter("description" + i);
            String ac = request.getParameter("ac" + i);
            
            if((product!="" && product!=null) || (refNo!="" && refNo!=null) || (invNo!="" && invNo!=null) || (code!="" && code!=null) || (type!="" && type!=null) || (amount!="" && amount!=null) || (selectCurrency!="" && selectCurrency!=null) || (description!="" && description!=null) || (ac!="" && ac!=null)){
                if(amount!="" && amount!=null){
                    BigDecimal amountRe = new BigDecimal(amount.replaceAll(",",""));
                    System.out.println("amount" + i + ":" + amountRe);
                    paymentDetailWendy.setAmount(amountRe);
                }    
                
                if(paymentDetailWendyId!=null && paymentDetailWendyId!=""){
                    paymentDetailWendy.setId(paymentDetailWendyId);
                    paymentDetailWendy.setAccCode(ac);           
                    paymentDetailWendy.setAmountType(type);
                    paymentDetailWendy.setCurrency(selectCurrency);
                    paymentDetailWendy.setDescription(description);
                    paymentDetailWendy.setInvoiceCreditor(invNo);
                    paymentDetailWendy.setPaymentWendy(paymentWendy);
                   
                    MPaytype mpayType = new MPaytype();                   
                    if(product==""){
                        paymentDetailWendy.setMPaytype(null);
                    } else {
                        mpayType.setId(product);
                        paymentDetailWendy.setMPaytype(mpayType);
                    }
                  
//                    Master master = paymentTourHotelService.getMasterFromRefno(refNo);
//                    paymentDetailWendy.setMaster(master);
                    paymentDetailWendy.setMaster(null);
                    paymentWendy.getPaymentDetailWendies().add(paymentDetailWendy);
                    
                } else {
                    paymentDetailWendy.setAccCode(ac);           
                    paymentDetailWendy.setAmountType(type);
                    paymentDetailWendy.setCurrency(selectCurrency);
                    paymentDetailWendy.setDescription(description);
                    paymentDetailWendy.setInvoiceCreditor(invNo);
                    paymentDetailWendy.setPaymentWendy(paymentWendy);
                    
                    MPaytype mpayType = new MPaytype();
                    if(product==""){
                        paymentDetailWendy.setMPaytype(null);
                    } else {
                        mpayType.setId(product);
                        paymentDetailWendy.setMPaytype(mpayType);
                    }
                    
                    Master master = paymentTourHotelService.getMasterFromRefno(refNo);
                    paymentDetailWendy.setMaster(master);
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
        String account = String.valueOf(paymentWendy.getAccount());
        String InputPayDate = String.valueOf(paymentWendy.getPayDate());                   
        String itemStatus = paymentWendy.getMItemstatus().getId();
        String InputInvoiceSupId = paymentWendy.getInvoiceSup();
        String Detail = paymentWendy.getDetail();
        String InputRemark = paymentWendy.getRemark();
        String InputCash = String.valueOf(paymentWendy.getCash());
        String InputChqNo = paymentWendy.getChqNo();
        String InputChqAmount = String.valueOf(paymentWendy.getChqAmount());
        
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
            
        InvoiceSupplier invoiceSupplierData = paymentTourHotelService.getDataInvoiceSuppiler(InputInvoiceSupId);
        String InputInvoiceSupCode = invoiceSupplierData.getCode();
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
        request.setAttribute("itemPayment", itemPayment);
        request.setAttribute("InputRemark", InputRemark);
        request.setAttribute("InputCash", InputCash);
        request.setAttribute("InputChqNo", InputChqNo);
        request.setAttribute("InputChqAmount", InputChqAmount);
        request.setAttribute(PRODUCTDETAILLIST, paymentDetailWendyList);
        request.setAttribute(PAYMENHOTELTCOUNT, size);
        request.setAttribute("btnSave", "update");
    }

}
