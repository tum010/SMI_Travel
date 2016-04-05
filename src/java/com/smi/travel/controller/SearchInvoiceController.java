package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.Invoice;
import com.smi.travel.datalayer.service.InvoiceService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.CustomerAgentInfo;
import com.smi.travel.datalayer.view.entity.InvoiceView;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class SearchInvoiceController extends SMITravelController {
    private static final ModelAndView SearchInvoice = new ModelAndView("SearchInvoice");
    private static final ModelAndView SearchInvoice_REFRESH = new ModelAndView(new RedirectView("SearchInvoice.smi", true));
    private static final String LINKNAME = "SearchInvoice";
    private InvoiceService invoiceService;
    private UtilityService utilityService;
    UtilityFunction utilty = new UtilityFunction();
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        UtilityFunction util = new UtilityFunction();
        String callPageFrom = utilty.getAddressUrl(request.getRequestURI()).replaceAll(LINKNAME, "");//request.getParameter("type");
        String fromdate = util.covertStringDateToFormatYMD(request.getParameter("FromDate"));
        String todate = util.covertStringDateToFormatYMD(request.getParameter("ToDate"));
        String department = request.getParameter("Department");
        String type = request.getParameter("Type");
        String action = request.getParameter("action");
        String agent = request.getParameter("InvTo");
        String agentName = request.getParameter("InvToName");
        String status = request.getParameter("status");
        String airticketWendy = request.getParameter("airticketWendy");
        
        if("ST".equalsIgnoreCase(callPageFrom)){
            request.setAttribute("showtemp", "show");
        }else{
            request.setAttribute("showtemp", "notshow");
        }
        //List Agent
        List<CustomerAgentInfo> listCustomerAgentInfo = new ArrayList<CustomerAgentInfo>();
        listCustomerAgentInfo = utilityService.getListCustomerAgentInfo();
        if(listCustomerAgentInfo != null){
            request.setAttribute("listAgent", listCustomerAgentInfo);
        }else{
            request.setAttribute("listAgent", null);
        } 
        
        if("search".equals(action)){
            System.out.println(" type " + type);
            List<Invoice> listInvoice = new LinkedList<Invoice>();
            List<InvoiceView> listView = new LinkedList<InvoiceView>();
            listInvoice = invoiceService.SearchInvoice(fromdate, todate, department, type,agent,status,airticketWendy);
            if(listInvoice != null){
                listView = invoiceService.SearchInvoiceView(listInvoice);
                if (listView != null) {
                    request.setAttribute("listInvoice", listView);
                }else{
                    request.setAttribute("listInvoice", null);
                }
                request.setAttribute("fromdate", fromdate);
                request.setAttribute("todate", todate);
                request.setAttribute("department", department);
                request.setAttribute("type", type);
                request.setAttribute("agent", agent);
                request.setAttribute("agentName", agentName);
                request.setAttribute("status", status);
                request.setAttribute("airticketWendy", airticketWendy);
            }
        
        }        
        return SearchInvoice;
    }

    public InvoiceService getInvoiceService() {
        return invoiceService;
    }

    public void setInvoiceService(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }
     
}
