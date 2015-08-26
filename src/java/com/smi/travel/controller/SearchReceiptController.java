package com.smi.travel.controller;
import com.smi.travel.datalayer.service.ReceiptService;
import com.smi.travel.datalayer.view.entity.ReceiptSearchView;
import com.smi.travel.master.controller.SMITravelController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class SearchReceiptController extends SMITravelController {
    private static final ModelAndView SearchReceipt = new ModelAndView("SearchReceipt");
    private static final ModelAndView SearchReceipt_REFRESH = new ModelAndView(new RedirectView("SearchReceipt.smi", true));
    
    private static final String RECTYPE = "recType";
    private static final String DEPARTMENT = "department";
    private static final String FROMDATE = "inputFromDate";
    private static final String TODATE = "inputToDate";
    private static final String RECEIPTSEARCHLIST = "receiptSearchList";
    private static final String PAGE = "callPage";
    private ReceiptService receiptService;
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String recType = request.getParameter("recType");
        String department = request.getParameter("department");
        String inputFromDate = request.getParameter("inputFromDate");
        String inputToDate = request.getParameter("inputToDate");
        String departtemp = "";
        System.out.println("action  " + action);
        System.out.println("recType  " + recType);
        System.out.println("department  " + department);
        System.out.println("inputFromDate  " + inputFromDate);
        System.out.println("inputToDate  " + inputToDate);
        if ("search".equalsIgnoreCase(action)) {
            List<ReceiptSearchView>  receiptSearchViews = receiptService.getReceiptViewFromFilter(inputFromDate, inputToDate, department, recType);
            if(receiptSearchViews != null){
                request.setAttribute(RECEIPTSEARCHLIST,receiptSearchViews);
            } 
            if("Wendy".equals(department)){
                departtemp = "W";
            }else if("Inbound".equals(department)){
                departtemp = "I";
            }else if("Outbound".equals(department)){
                departtemp = "O";
            }
            
            String page = "";
            page = "Receipt"+departtemp+recType+".smi";
            System.out.println(" page " + page);
            request.setAttribute(PAGE,page);
        }

        request.setAttribute(RECTYPE,recType);
        request.setAttribute(DEPARTMENT,department);
        request.setAttribute(FROMDATE,inputFromDate);
        request.setAttribute(TODATE,inputToDate);
        return SearchReceipt;
    }

    public ReceiptService getReceiptService() {
        return receiptService;
    }

    public void setReceiptService(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }
}
