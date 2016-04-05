package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.MFinanceItemstatus;
import com.smi.travel.datalayer.service.CreditNoteService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.CreditNoteView;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class SearchCreditNoteController extends SMITravelController {
    private static final ModelAndView SearchCreditNote = new ModelAndView("SearchCreditNote");
    private static final ModelAndView SearchCreditNote_REFRESH = new ModelAndView(new RedirectView("SearchCreditNote.smi", true));
    private static final String MFinanceItemstatusList = "mFinanceItemStatus_List";
    private CreditNoteService creditNoteService;
    private UtilityService utilservice;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        UtilityFunction util = new UtilityFunction();
        List<MFinanceItemstatus> mFinanceItemstatusList = getUtilservice().getListMFinanceItemstatus();
        request.setAttribute(MFinanceItemstatusList, mFinanceItemstatusList);
        String dateFrom = request.getParameter("iDateFrom");
        String dateTo = request.getParameter("iDateTo");
        String department = request.getParameter("department");
        String action = request.getParameter("action");
        String status = request.getParameter("status");
        
        //Set Date Format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        dateFrom = (!"".equalsIgnoreCase(dateFrom) && dateFrom != null ? sdf.format(util.convertStringToDate(dateFrom)) : "");
        dateTo = (!"".equalsIgnoreCase(dateTo) && dateTo != null ? sdf.format(util.convertStringToDate(dateTo)) : "");
        
        if("search".equalsIgnoreCase(action)){
            List<CreditNoteView> creditNoteList = creditNoteService.getCreditNoteFromFilter(dateFrom, dateTo, department, status);
            request.setAttribute("creditNoteList", creditNoteList);
        }
        
        sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        request.setAttribute("iDateFrom", (!"".equalsIgnoreCase(dateFrom) && dateFrom != null ? sdf.format(util.convertStringToDate(dateFrom)) : ""));
        request.setAttribute("iDateTo", (!"".equalsIgnoreCase(dateTo) && dateTo != null ? sdf.format(util.convertStringToDate(dateTo)) : ""));
        request.setAttribute("department", department);
        request.setAttribute("status", status);
        return SearchCreditNote;
    }

    /**
     * @return the creditNoteService
     */
    public CreditNoteService getCreditNoteService() {
        return creditNoteService;
    }

    /**
     * @param creditNoteService the creditNoteService to set
     */
    public void setCreditNoteService(CreditNoteService creditNoteService) {
        this.creditNoteService = creditNoteService;
    }

    public UtilityService getUtilservice() {
        return utilservice;
    }

    public void setUtilservice(UtilityService utilservice) {
        this.utilservice = utilservice;
    }
}
