package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.Invoice;
import com.smi.travel.datalayer.service.InvoiceService;
import com.smi.travel.datalayer.view.entity.InvoiceView;
import com.smi.travel.master.controller.SMITravelController;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class SearchInvoiceController extends SMITravelController {
    private static final ModelAndView SearchInvoice = new ModelAndView("SearchInvoice");
    private static final ModelAndView SearchInvoice_REFRESH = new ModelAndView(new RedirectView("SearchInvoice.smi", true));
    private InvoiceService invoiceService;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String fromdate = request.getParameter("FromDate");
        String todate = request.getParameter("ToDate");
        String department = request.getParameter("Department");
        String type = request.getParameter("Type");
        String action = request.getParameter("action");
        
        if("search".equals(action)){
            List<Invoice> listInvoice = new LinkedList<Invoice>();
            List<InvoiceView> listView = new LinkedList<InvoiceView>();
            listInvoice = invoiceService.SearchInvoice(fromdate, todate, department, type);
            if(listInvoice != null){
                listView = invoiceService.SearchInvoiceView(listInvoice);
                if (listView != null) {
                    request.setAttribute("listInvoice", listView);
                    request.setAttribute("fromdate", fromdate);
                    request.setAttribute("todate", todate);
                    request.setAttribute("department", department);
                    request.setAttribute("type", type);
                }else{
                    request.setAttribute("listInvoice", null);
                }
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
     
}
