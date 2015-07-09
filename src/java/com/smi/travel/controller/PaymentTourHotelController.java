package com.smi.travel.controller;


import com.smi.travel.datalayer.entity.MAccpay;
import com.smi.travel.datalayer.entity.MItemstatus;
import com.smi.travel.datalayer.entity.MPaymentDoctype;
import com.smi.travel.datalayer.service.PaymentTourHotelService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.InvoiceSupplier;
import com.smi.travel.master.controller.SMITravelController;
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
    private static final String PAYMENTLIST ="payment_list";
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
        List<InvoiceSupplier> invoiceSupplierList = getPaymentTourHotelService().getListInvoiceSuppiler();
        request.setAttribute(INVOICESUPLIST, invoiceSupplierList);
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
