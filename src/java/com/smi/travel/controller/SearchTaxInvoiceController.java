package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.MFinanceItemstatus;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.TaxInvoiceService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.TaxInvoiceView;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class SearchTaxInvoiceController extends SMITravelController {
    private static final ModelAndView SearchTaxInvoice = new ModelAndView("SearchTaxInvoice");
    private static final ModelAndView SearchTaxInvoice_REFRESH = new ModelAndView(new RedirectView("SearchTaxInvoice.smi", true));
    private TaxInvoiceService taxInvoiceService;
    private static final String DATALIST = "taxInvoiceView_List";
    private static final String MFinanceItemstatusList = "mFinanceItemStatus_List";
    private UtilityService utilservice;
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        UtilityFunction util = new UtilityFunction();
        List<MFinanceItemstatus> mFinanceItemstatusList = getUtilservice().getListMFinanceItemstatus();
        request.setAttribute(MFinanceItemstatusList, mFinanceItemstatusList);
        String action = request.getParameter("action");
        String inputFromDate = request.getParameter("InputFromDate");
        String inputToDate = request.getParameter("InputToDate");
        String department = request.getParameter("Department");
        String status = request.getParameter("Status");
        
        SystemUser user = (SystemUser) session.getAttribute("USER");
        String idRole = user.getRole().getId();
        String username = user.getUsername();
        request.setAttribute("idRole", idRole);
        String roleName = user.getRole().getName();
        request.setAttribute("user", username+" - "+roleName);
        
        //Set Date Format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        inputFromDate = (!"".equalsIgnoreCase(inputFromDate) && inputFromDate != null ? sdf.format(util.convertStringToDate(inputFromDate)) : "");
        inputToDate = (!"".equalsIgnoreCase(inputToDate) && inputToDate != null ? sdf.format(util.convertStringToDate(inputToDate)) : "");
        List<TaxInvoiceView> taxInvoiceViewList = new ArrayList<TaxInvoiceView>();
        if("search".equalsIgnoreCase(action)){
            taxInvoiceViewList = taxInvoiceService.SearchTaxInvoiceFromFilter(inputFromDate, inputToDate, department, status);
        }
        
        request.setAttribute(DATALIST, taxInvoiceViewList);
        request.setAttribute("inputFromDate", inputFromDate);
        request.setAttribute("inputToDate", inputToDate);
        request.setAttribute("department", department);
        request.setAttribute("status", status);
        
        return SearchTaxInvoice;
    }

    public TaxInvoiceService getTaxInvoiceService() {
        return taxInvoiceService;
    }

    public void setTaxInvoiceService(TaxInvoiceService taxInvoiceService) {
        this.taxInvoiceService = taxInvoiceService;
    }

    public UtilityService getUtilservice() {
        return utilservice;
    }

    public void setUtilservice(UtilityService utilservice) {
        this.utilservice = utilservice;
    }
}
