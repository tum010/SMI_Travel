package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.MItemstatus;
import com.smi.travel.datalayer.entity.PaymentDetailWendy;
import com.smi.travel.datalayer.entity.PaymentWendy;
import com.smi.travel.datalayer.service.PaymentTourHotelService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.InvoiceSupplier;
import com.smi.travel.datalayer.view.entity.PaymentWendytourView;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class SearchPaymentTourHotelController extends SMITravelController {
    private static final ModelAndView SearchPaymentTourHotel = new ModelAndView("SearchPaymentTourHotel");
    private static final ModelAndView SearchPaymentTourHotel_REFRESH = new ModelAndView(new RedirectView("SearchPaymentTourHotel.smi", true));
    
    private UtilityService utilityService;
    private PaymentTourHotelService paymentTourHotelService;
    private static final String TYPELIST = "payment_type";
    private static final String DATALIST = "payment_list";
    private static final String INVOICESUPLIST = "invoiceSup_list";
    private static final String STATUS = "status_list";
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        UtilityFunction util = new UtilityFunction();
        List<InvoiceSupplier> invoiceSupplierList = paymentTourHotelService.getListInvoiceSuppiler();
        request.setAttribute(INVOICESUPLIST, invoiceSupplierList);
        List<MItemstatus> mItemstatusList = utilityService.getListMItemstatus();
        request.setAttribute(STATUS, mItemstatusList);
        
        String action = request.getParameter("action");
        String InputFromDate = util.covertStringDateToFormatYMD(request.getParameter("InputFromDate"));
        String InputToDate = util.covertStringDateToFormatYMD(request.getParameter("InputToDate"));
        String selectPvType = request.getParameter("selectPvType");
        String selectStatus = request.getParameter("selectStatus");
        String paymentID = request.getParameter("paymentID");
        String InputInvoiceSupCode = request.getParameter("InputInvoiceSupCode");
        String InputInvoiceSupName = request.getParameter("InputInvoiceSupName");
        String InputAPCode = request.getParameter("InputAPCode");
        String InputPayNo = request.getParameter("InputPayNo");
        String result = "";
                
        List<PaymentWendytourView> paymentList = new ArrayList<PaymentWendytourView>();
        
        if("search".equalsIgnoreCase(action)){
            paymentList = getPaymentTourHotelService().getListPayment(InputFromDate, InputToDate, selectPvType, InputInvoiceSupCode, selectStatus);    
        } else if ("delete".equalsIgnoreCase(action)) {
            PaymentWendy paymentWendy = new PaymentWendy();
            paymentWendy.setId(paymentID);
            paymentWendy.setPayNo(InputPayNo);
            
            PaymentWendy paymentWendyCheck = paymentTourHotelService.getPaymentWendyFromID(InputPayNo);
            List<PaymentDetailWendy> paymentDetailWendy = paymentWendyCheck.getPaymentDetailWendies();
            
            if((paymentDetailWendy.size() != 0) && (paymentWendyCheck.getIsExport() != null) && (paymentWendyCheck.getIsExport() != 0)){
                if(paymentWendyCheck.getIsExport() == 1){
                    result = "fail already used";
                } else {
                    result = "fail isExport";
                }
                paymentList = getPaymentTourHotelService().getListPayment(InputFromDate, InputToDate, selectPvType, InputInvoiceSupCode, selectStatus);
            } else {
                result = getPaymentTourHotelService().deletePaymentWendy(paymentWendy);
                if ("success".equals(result)) {
                    paymentList = getPaymentTourHotelService().getListPayment(InputFromDate, InputToDate, selectPvType, InputInvoiceSupCode, selectStatus);
                }              
            }
                      
        }
        
        request.setAttribute(DATALIST,paymentList);
        request.setAttribute(TYPELIST,getUtilityService().getListMpaymentDocType("tourhotel"));
        request.setAttribute("InputFromDate", InputFromDate);
        request.setAttribute("InputToDate", InputToDate);
        request.setAttribute("selectPvType", selectPvType);
        request.setAttribute("selectStatus", selectStatus);
        request.setAttribute("InputInvoiceSupCode", InputInvoiceSupCode);
        request.setAttribute("InputInvoiceSupName", InputInvoiceSupName);
        request.setAttribute("InputAPCode", InputAPCode);
        request.setAttribute("result", result);
        return SearchPaymentTourHotel;
    }

    /**
     * @return the utilityService
     */
    public UtilityService getUtilityService() {
        return utilityService;
    }

    /**
     * @param utilityService the utilityService to set
     */
    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
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
