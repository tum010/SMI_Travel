package com.smi.travel.controller;
import com.smi.travel.datalayer.service.CreditNoteService;
import com.smi.travel.datalayer.view.entity.CreditNoteView;
import com.smi.travel.master.controller.SMITravelController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class SearchCreditNoteController extends SMITravelController {
    private static final ModelAndView SearchCreditNote = new ModelAndView("SearchCreditNote");
    private static final ModelAndView SearchCreditNote_REFRESH = new ModelAndView(new RedirectView("SearchCreditNote.smi", true));
    private CreditNoteService creditNoteService;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String dateFrom = request.getParameter("iDateFrom");
        String dateTo = request.getParameter("iDateTo");
        String department = request.getParameter("department");
        String action = request.getParameter("action");
        if("search".equalsIgnoreCase(action)){
            List<CreditNoteView> creditNoteList = creditNoteService.getCreditNoteFromFilter(dateFrom, dateTo, department);
            request.setAttribute("creditNoteList", creditNoteList);
        }
        request.setAttribute("iDateFrom", dateFrom);
        request.setAttribute("iDateTo", dateTo);
        request.setAttribute("department", department);
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
}
