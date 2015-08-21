package com.smi.travel.controller;
import com.smi.travel.datalayer.service.TaxInvoiceService;
import com.smi.travel.datalayer.view.entity.TaxInvoiceView;
import com.smi.travel.master.controller.SMITravelController;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class SearchTaxInvoiceController extends SMITravelController {
    private static final ModelAndView SearchTaxInvoice = new ModelAndView("SearchTaxInvoice");
    private static final ModelAndView SearchTaxInvoice_REFRESH = new ModelAndView(new RedirectView("SearchTaxInvoice.smi", true));
    private TaxInvoiceService taxInvoiceService;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String inputFromDate = request.getParameter("InputFromDate");
        String inputToDate = request.getParameter("InputToDate");
        String department = request.getParameter("Department");
        
        List<TaxInvoiceView> taxInvoiceViewList = new ArrayList<TaxInvoiceView>();
        if("search".equalsIgnoreCase(action)){
            taxInvoiceViewList = taxInvoiceService.SearchTaxInvoiceFromFilter(inputFromDate, inputToDate, department);
        }
        
        return SearchTaxInvoice;
    }

    public TaxInvoiceService getTaxInvoiceService() {
        return taxInvoiceService;
    }

    public void setTaxInvoiceService(TaxInvoiceService taxInvoiceService) {
        this.taxInvoiceService = taxInvoiceService;
    }
}
