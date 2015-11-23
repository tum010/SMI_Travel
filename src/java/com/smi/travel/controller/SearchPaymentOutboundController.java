package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.MItemstatus;
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
public class SearchPaymentOutboundController extends SMITravelController {
    private static final ModelAndView SearchPaymentOutbound = new ModelAndView("SearchPaymentOutbound");
    private static final ModelAndView SearchPaymentOutbound_REFRESH = new ModelAndView(new RedirectView("SearchPaymentOutbound.smi", true));
    private static final String STATUS = "statusList";
    private static final String INVOICESUPLIST = "invSupList";
    private UtilityService utilservice;
    private PaymentTourHotelService paymentTourHotelService;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        List<MItemstatus> mItemstatusList = utilservice.getListMItemstatus();
        request.setAttribute(STATUS, mItemstatusList);
        List<InvoiceSupplier> invoiceSupplierList = getPaymentTourHotelService().getListInvoiceSuppiler();
        request.setAttribute(INVOICESUPLIST, invoiceSupplierList);
        
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        String status = request.getParameter("status");
        String invSupCode = request.getParameter("invSupCode");
        String refNo = request.getParameter("refNo");
        
        return SearchPaymentOutbound;
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
}
