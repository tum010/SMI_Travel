package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.MAirlineAgent;
import com.smi.travel.datalayer.entity.TicketFareAirline;
import com.smi.travel.datalayer.service.TicketFareAirlineService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.TicketFareView;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class SearchTicketFareController extends SMITravelController {
    private static final ModelAndView SearchTicketFare = new ModelAndView("SearchTicketFare");
    private static final ModelAndView SearchTicketFare_REFRESH = new ModelAndView(new RedirectView("SearchTicketFare.smi", true));
    private static final String AIRLINELIST = "airlineList";
    private static final String TICKETFARE = "ticketFare";
    private static final String TICKETROUNTING = "TicketRounting";
    private static final String TICKETTYPE = "TicketType";
    private static final String ISSUEDATE = "issueDate";
    private static final String INVOICENO = "invoiceNo";
    private static final String DEPARTMENT = "department";
    private static final String DATALIST = "Ticket_List";
    private static final String TransactionResult = "result";
    private UtilityService utilityService;
    private TicketFareAirlineService ticketFareAirlineService;
    UtilityFunction util;
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String ticketNo = request.getParameter("ticketNo");
        String ticketType = request.getParameter("ticketType");
        String ticketRounting = request.getParameter("ticketRounting");
        String ticketAirline = request.getParameter("ticketAirline");
        String issueDate = request.getParameter("issueDate");
        String invoiceNo = request.getParameter("invoiceNo");
        String department = request.getParameter("department");
        String ticketId = request.getParameter("ticketId");
        List<MAirlineAgent> mAirlineAgentsList = utilityService.getListMAirLineAgent();
        request.setAttribute(AIRLINELIST,mAirlineAgentsList);
        util = new UtilityFunction();
        int result = 0;
        TicketFareView ticketFareView = new TicketFareView();
        ticketFareView.setType(ticketType);
        ticketFareView.setRounting(ticketRounting);
        ticketFareView.setAirline(ticketAirline);
        ticketFareView.setTicketNo(ticketNo);
        if(StringUtils.isNotEmpty(issueDate)){
            ticketFareView.setIssueDate(util.convertStringToDate(issueDate));
            request.setAttribute(ISSUEDATE,issueDate);
        }
        ticketFareView.setInvoiceNo(invoiceNo);
        ticketFareView.setDepartment(department);
        
        if ("search".equalsIgnoreCase(action)) {
            List<TicketFareView> listTicket = new ArrayList<TicketFareView>();
            listTicket = ticketFareAirlineService.getListTicketFare(ticketFareView,2);
            request.setAttribute(DATALIST, listTicket);
            request.setAttribute(TICKETFARE,ticketFareView);
        }else if ("delete".equalsIgnoreCase(action)) {
            System.out.println("ticketId : "+ ticketId);
            TicketFareAirline ticketFareAirline = new TicketFareAirline();
            ticketFareAirline.setId(ticketId);
            ticketFareAirline.setTicketNo(ticketNo);
            result = ticketFareAirlineService.DeleteTicketFare(ticketFareAirline);
            if (result == 1) {
                request.setAttribute(TransactionResult, "delete successful");
            } else {
                request.setAttribute(TransactionResult, "delete unsuccessful");
            }
        } 

        request.setAttribute(TICKETROUNTING,ticketRounting);
        request.setAttribute(TICKETTYPE,ticketType);
        request.setAttribute(INVOICENO,invoiceNo);
        request.setAttribute(DEPARTMENT,department);
        return SearchTicketFare;
    }


    public UtilityService getUtilityService() {
        return utilityService;
    }


    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }

    public TicketFareAirlineService getTicketFareAirlineService() {
        return ticketFareAirlineService;
    }

    public void setTicketFareAirlineService(TicketFareAirlineService ticketFareAirlineService) {
        this.ticketFareAirlineService = ticketFareAirlineService;
    }
}
