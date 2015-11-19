package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.MCurrency;
import com.smi.travel.datalayer.entity.MDefaultData;
import com.smi.travel.datalayer.entity.MItemstatus;
import com.smi.travel.datalayer.entity.MPaytype;
import com.smi.travel.datalayer.service.PaymentTourHotelService;
import com.smi.travel.datalayer.service.PaymentOutboundService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.InvoiceSupplier;
import com.smi.travel.master.controller.SMITravelController;
import java.math.BigDecimal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class PaymentOutboundController extends SMITravelController {
    private static final ModelAndView PaymentOutbound = new ModelAndView("PaymentOutbound");
    private static final ModelAndView PaymentOutbound_REFRESH = new ModelAndView(new RedirectView("PaymentOutbound.smi", true));
    private static final String STATUS = "statusList";
    private static final String PAYTYPE = "payTypeList";
    private static final String CURRENCY = "currencyList";
    private static final String MVAT = "mVat";
    private static final String INVOICESUPLIST = "invSupList";        
    private UtilityService utilservice;
    private PaymentTourHotelService paymentTourHotelService;
    private PaymentOutboundService paymentOutboundService;        
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        List<MItemstatus> mItemstatusList = utilservice.getListMItemstatus();
        request.setAttribute(STATUS, mItemstatusList);
        List<MPaytype> mPayTypeList = utilservice.getListMPayType();
        request.setAttribute(PAYTYPE, mPayTypeList);
        List<MCurrency> mCurrencyList = utilservice.getListMCurrency();
        request.setAttribute(CURRENCY, mCurrencyList);
        MDefaultData mDefaultData = utilservice.getMDefaultDataFromType("vat");
        BigDecimal mVat = new BigDecimal(mDefaultData.getValue());
        request.setAttribute(MVAT, mVat);
        List<InvoiceSupplier> invoiceSupplierList = getPaymentTourHotelService().getListInvoiceSuppiler();
        request.setAttribute(INVOICESUPLIST, invoiceSupplierList);
        return PaymentOutbound;
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

    public PaymentOutboundService getPaymentOutboundService() {
        return paymentOutboundService;
    }

    public void setPaymentOutboundService(PaymentOutboundService paymentOutboundService) {
        this.paymentOutboundService = paymentOutboundService;
    }
}
