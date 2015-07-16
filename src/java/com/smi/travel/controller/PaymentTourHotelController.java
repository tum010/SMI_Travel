package com.smi.travel.controller;


import com.smi.travel.datalayer.entity.MAccpay;
import com.smi.travel.datalayer.entity.MCurrency;
import com.smi.travel.datalayer.entity.MItemstatus;
import com.smi.travel.datalayer.entity.MPaymentDoctype;
import com.smi.travel.datalayer.entity.MPaytype;
import com.smi.travel.datalayer.entity.PaymentDetailWendy;
import com.smi.travel.datalayer.entity.PaymentWendy;
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
    private static final String PAYMENHOTELTLIST ="paymentHotel_list";
    private static final String PAYMENHOTELTCOUNT ="paymenthotelcount";
    private static final String PAYMENTLIST ="payment_list";
    private static final String PRODUCTLIST ="product_list";
    private static final String CURRENCYLIST ="currency_list";
    private UtilityService utilservice;
    UtilityFunction util;
    private PaymentTourHotelService paymentTourHotelService;
    private static final String TransectionResult = "result";
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        String action = request.getParameter("action");
        System.out.println("action : " + action);
        String InputPayNo = request.getParameter("InputPayNo");
        System.out.println("InputPayNo : " + InputPayNo);
        String account = request.getParameter("account");
        System.out.println("account : " + account);
        String InputPayDate = request.getParameter("InputPayDate");
        System.out.println("InputPayDate : " + InputPayDate);
        String itemPvType = request.getParameter("itemPvType");
        System.out.println("itemPvType : " + itemPvType);
        String itemStatus = request.getParameter("itemStatus");
        System.out.println("itemStatus : " + itemStatus);
        String InputInvoiceSupId = request.getParameter("InputInvoiceSupId");
        System.out.println("InputInvoiceSupId : " + InputInvoiceSupId);        
        String InputInvoiceSupCode = request.getParameter("InputInvoiceSupCode");
        System.out.println("InputInvoiceSupCode : " + InputInvoiceSupCode);
        String InputInvoiceSupName = request.getParameter("InputInvoiceSupName");
        System.out.println("InputInvoiceSupName : " + InputInvoiceSupName);
        String InputAPCode = request.getParameter("InputAPCode");
        System.out.println("InputAPCode : " + InputAPCode);
        String Detail = request.getParameter("Detail");
        System.out.println("Detail : " + Detail);
        String itemPayment = request.getParameter("itemPayment");
        System.out.println("itemPayment : " + itemPayment);    
        String InputRemark = request.getParameter("InputRemark");
        System.out.println("InputRemark : " + InputRemark);   
        String InputCash = request.getParameter("InputCash");
        System.out.println("InputCash : " + InputCash);
        String InputChqNo = request.getParameter("InputChqNo");
        System.out.println("InputChqNo : " + InputChqNo);   
        String InputChqAmount = request.getParameter("InputChqAmount");
        System.out.println("InputChqAmount : " + InputChqAmount);
        String counter = request.getParameter("counter");
        System.out.println("counter : " + counter);
        
        if ("add".equalsIgnoreCase(action)) {
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
            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String createDate = sdf.format(date);
            paymentWendy.setCreateDate(utilfunction.convertStringToDate(createDate));
             
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
            
            String result = paymentTourHotelService.InsertPaymentWendy(paymentWendy);
            System.out.println("result : " + result);
            
        } else if("deleteProductDetail".equalsIgnoreCase(action)) {
            System.out.println("deleteProductDetail");
            String ProductDetail = request.getParameter("ProductDetail");
            String result = paymentTourHotelService.DeletePaymentWendyDetail(ProductDetail);
            System.out.println(result);
        }
        
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
        
        if (Rows == 1) {
            return;
        }
        
        for (int i = 0; i < Rows  ; i++) {
            PaymentDetailWendy paymentDetailWendy = new PaymentDetailWendy();
            String product = request.getParameter("select-product" + i);
            System.out.println("select-product" + i + ":" + product);
            String refNo = request.getParameter("refNo" + i);
            System.out.println("refNo" + i + ":" + refNo);
            String invNo = request.getParameter("invNo" + i);
            System.out.println("invNo" + i + ":" + invNo);
            String code = request.getParameter("code" + i);
            System.out.println("code" + i + ":" + code);
            String type = request.getParameter("type" + i);
            System.out.println("type" + i + ":" + type);
            String amount = request.getParameter("amount" + i);           
            String selectCurrency = request.getParameter("select-currency" + i);
            System.out.println("select-currency" + i + ":" + selectCurrency);
            String description = request.getParameter("description" + i);
            System.out.println("description" + i + ":" + description);
            String ac = request.getParameter("ac" + i);
            System.out.println("ac" + i + ":" + ac);
            
            if((product!="" && product!=null) && (refNo!="" && refNo!=null) && (invNo!="" && invNo!=null) && (code!="" && code!=null) && (type!="" && type!=null) && (amount!="" && amount!=null) && (selectCurrency!="" && selectCurrency!=null) && (description!="" && description!=null) && (ac!="" && ac!=null)){
                if(amount!="" && amount!=null){
                    BigDecimal amountRe = new BigDecimal(amount.replaceAll(",",""));
                    System.out.println("amount" + i + ":" + amountRe);
                    paymentDetailWendy.setAmount(amountRe);
                }    

                paymentDetailWendy.setAccCode(ac);           
                paymentDetailWendy.setAmountType(type);
                paymentDetailWendy.setCurrency(selectCurrency);
                paymentDetailWendy.setDescription(description);
                paymentDetailWendy.setInvoiceCreditor(invNo);
                paymentDetailWendy.setPaymentWendy(paymentWendy);
                MPaytype mpayType = new MPaytype();
                mpayType.setId(product);
                paymentDetailWendy.setMPaytype(mpayType);
                paymentDetailWendy.setMaster(null);


                paymentWendy.getPaymentDetailWendies().add(paymentDetailWendy);
            }                        
        }
    }
    
}
