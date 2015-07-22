package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.MAccpay;
import com.smi.travel.datalayer.entity.MAirlineAgent;
import com.smi.travel.datalayer.entity.PaymentAirticket;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.CustomerAgentInfo;
import com.smi.travel.datalayer.view.entity.InvoiceSupplier;
import com.smi.travel.master.controller.SMITravelController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class PaymentAirlineController extends SMITravelController {
    private static final ModelAndView PaymentAirline = new ModelAndView("PaymentAirline");
    private static final ModelAndView PaymentAirline_REFRESH = new ModelAndView(new RedirectView("PaymentAirline.smi", true));
    private static final String AIRLINELIST = "airlineList";
    private static final String PAYBYLIST = "payByList";
    private static final String INVOICESUPLIST = "invoiceSupList";
    private static final String PAYMENTAIRLINE = "paymentAirline";
    private UtilityService utilityService;
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        PaymentAirticket paymentAirticket = new PaymentAirticket();
        request.setAttribute(PAYMENTAIRLINE,paymentAirticket);
        setResponseAttribute(request);
        return PaymentAirline;
    }
    
    public void setResponseAttribute(HttpServletRequest request) {
        List<MAirlineAgent> mAirlineAgentsList = utilityService.getListMAirLineAgent();
        request.setAttribute(AIRLINELIST,mAirlineAgentsList);
        List<MAccpay> mAccpayList = utilityService.getListMAccpay();
        request.setAttribute(PAYBYLIST,mAccpayList);
        List<InvoiceSupplier> invoiceSupplierList = utilityService.getListInvoiceSuppiler();
        
        request.setAttribute(INVOICESUPLIST,invoiceSupplierList);
        System.out.println(" invoiceSupplierList.get(0).getApcode() ::"+ invoiceSupplierList.get(0).getCode());
        System.out.println(" invoiceSupplierList.get(0).getApcode() ::"+ invoiceSupplierList.get(0).getName());
        System.out.println(" invoiceSupplierList.get(0).getApcode() ::"+ invoiceSupplierList.get(0).getApcode());
    }

    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }
}
