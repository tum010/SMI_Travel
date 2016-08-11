package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.MItemstatus;
import com.smi.travel.datalayer.service.PaymentOutboundService;
import com.smi.travel.datalayer.service.PaymentTourHotelService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.InvoiceSupplier;
import com.smi.travel.datalayer.view.entity.PaymentOutboundView;
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
    private static final String PAYMENTOUTBOUNDVIEWLIST = "paymentOutboundViewList";
    private static final String RESULT = "result"; 
    private UtilityService utilservice;
    private PaymentTourHotelService paymentTourHotelService;
    private PaymentOutboundService paymentOutboundService;        
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        List<MItemstatus> mItemstatusList = utilservice.getListMItemstatus();
        request.setAttribute(STATUS, mItemstatusList);
//        List<InvoiceSupplier> invoiceSupplierList = paymentTourHotelService.getListInvoiceSuppiler();
//        request.setAttribute(INVOICESUPLIST, invoiceSupplierList);
        
        String action = request.getParameter("action");
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        String status = request.getParameter("status");
        String invSupCode = request.getParameter("invSupCode");
        String invSupName = request.getParameter("invSupName");
        String refNo = request.getParameter("refNo");
        String dueDateFrom = request.getParameter("dueDateFrom");
        String dueDateTo = request.getParameter("dueDateTo");
        String payNo = request.getParameter("payNo");
        
        if("search".equalsIgnoreCase(action)){
            List<PaymentOutboundView> paymentOutboundViewList = paymentOutboundService.searchPaymentOutboundByFilter(fromDate,toDate,status,invSupCode,invSupName,refNo,dueDateFrom,dueDateTo,payNo);
            request.setAttribute(PAYMENTOUTBOUNDVIEWLIST, paymentOutboundViewList);
        
        }else if("deletePaymentOutbound".equalsIgnoreCase(action)){
            String paymentId = request.getParameter("paymentId");
            String result = paymentOutboundService.deletePaymentOutbound(paymentId);
            request.setAttribute(RESULT, result);
            List<PaymentOutboundView> paymentOutboundViewList = paymentOutboundService.searchPaymentOutboundByFilter(fromDate,toDate,status,invSupCode,invSupName,refNo,dueDateFrom,dueDateTo,payNo);
            request.setAttribute(PAYMENTOUTBOUNDVIEWLIST, paymentOutboundViewList);
        }
        
        request.setAttribute("fromDate", fromDate);
        request.setAttribute("toDate", toDate);
        request.setAttribute("status", status);
        request.setAttribute("invSupCode", invSupCode);
        request.setAttribute("invSupName", invSupName);
        request.setAttribute("refNo", refNo);
        request.setAttribute("dueDateFrom", dueDateFrom);
        request.setAttribute("dueDateTo", dueDateTo);
        request.setAttribute("payNo", payNo);
        
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

    public PaymentOutboundService getPaymentOutboundService() {
        return paymentOutboundService;
    }

    public void setPaymentOutboundService(PaymentOutboundService paymentOutboundService) {
        this.paymentOutboundService = paymentOutboundService;
    }
}
