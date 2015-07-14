package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.PaymentWendy;
import com.smi.travel.datalayer.service.PaymentTourHotelService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.PaymentWendytourView;
import com.smi.travel.master.controller.SMITravelController;
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
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String inputFromDate = request.getParameter("inputFromDate");
        String inputToDate = request.getParameter("inputToDate");
        String selectPvType = request.getParameter("selectPvType");
        String paymentID = request.getParameter("paymentID");
        List<PaymentWendytourView> paymentList = new ArrayList<PaymentWendytourView>();
        
        if("search".equalsIgnoreCase(action)){
            paymentList = getPaymentTourHotelService().getListPayment(inputFromDate, inputToDate, selectPvType);    
        } else if ("delete".equalsIgnoreCase(action)) {
            PaymentWendy paymentWendy = new PaymentWendy();
            paymentWendy.setId(paymentID);
            String result = getPaymentTourHotelService().deletePaymentWendy(paymentWendy);
            if ("success".equals(result)) {
                paymentList = getPaymentTourHotelService().getListPayment(inputFromDate, inputToDate, selectPvType);
            }
        }
        
        request.setAttribute(DATALIST,paymentList);
        request.setAttribute(TYPELIST,getUtilityService().getListMpaymentDocType());
        request.setAttribute("inputFromDate", inputFromDate);
        request.setAttribute("inputToDate", inputToDate);
        request.setAttribute("selectPvType", selectPvType);
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
