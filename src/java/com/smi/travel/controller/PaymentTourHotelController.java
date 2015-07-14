package com.smi.travel.controller;


import com.smi.travel.datalayer.entity.MAccpay;
import com.smi.travel.datalayer.entity.MCurrency;
import com.smi.travel.datalayer.entity.MItemstatus;
import com.smi.travel.datalayer.entity.MPaymentDoctype;
import com.smi.travel.datalayer.entity.MPaytype;
import com.smi.travel.datalayer.service.PaymentTourHotelService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.InvoiceSupplier;
import com.smi.travel.master.controller.SMITravelController;
import java.util.Currency;
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
    private PaymentTourHotelService paymentTourHotelService;
    
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
          
        if ("add".equalsIgnoreCase(action)) {
            
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

    
 
    
    
}
