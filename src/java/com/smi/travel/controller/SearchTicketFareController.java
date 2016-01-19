package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.MAirlineAgent;
import com.smi.travel.datalayer.entity.TicketFareAirline;
import com.smi.travel.datalayer.service.TicketFareAirlineService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.TicketFareView;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.text.SimpleDateFormat;
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
    private static final String TICKETROUTING = "TicketRouting";
    private static final String TICKETTYPE = "TicketType";
    private static final String ISSUEDATEFROM = "issueDateFrom";
    private static final String ISSUEDATETO = "issueDateTo";
    private static final String INVOICENO = "invoiceNo";
    private static final String DEPARTMENT = "department";
    private static final String DATALIST = "Ticket_List";
    private static final String TransactionResult = "result";
    private static final String TICKETALREADYUSE = "TicketAlreadyUse";
    private static final String REFNO = "refNumber";
    private UtilityService utilityService;
    private TicketFareAirlineService ticketFareAirlineService;
    UtilityFunction util;
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String ticketNo = request.getParameter("ticketNo");
        String ticketType = request.getParameter("ticketType");
        String ticketRouting = request.getParameter("ticketRouting");
        String ticketAirline = request.getParameter("ticketAirline");
        String issueDateFrom = request.getParameter("issueDateFrom");
        String issueDateTo = request.getParameter("issueDateTo");
        String invoiceNo = request.getParameter("invoiceNo");
        String department = request.getParameter("department");
        String ticketId = request.getParameter("ticketId");
        String deleteTicketNo = request.getParameter("deleteTicketNo");
        String deleteTicketId = request.getParameter("deleteTicketId");
        String refNumber = request.getParameter("refNumber");
        
        List<MAirlineAgent> mAirlineAgentsList = utilityService.getListMAirLineAgent();
        request.setAttribute(AIRLINELIST,mAirlineAgentsList);
        util = new UtilityFunction();
        int result = 0;
        TicketFareView ticketFareView = new TicketFareView();
        ticketFareView.setType(ticketType);
        ticketFareView.setRouting(ticketRouting);
        ticketFareView.setAirline(ticketAirline);
        ticketFareView.setTicketNo(ticketNo);
        
        
        
        if(StringUtils.isNotEmpty(issueDateFrom)){
            ticketFareView.setIssueDateFrom(util.convertStringToDate(issueDateFrom));
            request.setAttribute(ISSUEDATEFROM,issueDateFrom);
        }
        if(StringUtils.isNotEmpty(issueDateTo)){
            ticketFareView.setIssueDateTo(util.convertStringToDate(issueDateTo));
            request.setAttribute(ISSUEDATETO,issueDateTo);
        }
        
        ticketFareView.setInvoiceNo(invoiceNo);
        ticketFareView.setReferenceNo(refNumber);
        ticketFareView.setDepartment(department);
        
        if ("search".equalsIgnoreCase(action)) {
            List<TicketFareView> listTicket = new ArrayList<TicketFareView>();
            listTicket = ticketFareAirlineService.getListTicketFare(ticketFareView,1);
            request.setAttribute(DATALIST, listTicket);
            request.setAttribute(TICKETFARE,ticketFareView);
        }
        else if ("delete".equalsIgnoreCase(action)) {
            System.out.println("ticketId : "+ ticketId);
            TicketFareAirline ticketFareAirline = new TicketFareAirline();
            ticketFareAirline.setId(ticketId);
            ticketFareAirline.setTicketNo(ticketNo);
            
            int deletePayment = ticketFareAirlineService.checkDeletePaymentFromTicketNo(ticketNo); // 0 = not use , 1 = use
            int deleteRefund = ticketFareAirlineService.checkDeleteRefundFromTicketNo(ticketNo);
           
            if(deletePayment == 0 && deleteRefund == 0){  //Delete
                result = ticketFareAirlineService.DeleteTicketFare(ticketFareAirline);
                if (result == 1) {
                    request.setAttribute(TransactionResult, "delete successful");
                }else{
                    request.setAttribute(TransactionResult, "delete unsuccessful");
                }
            }
            if(deletePayment == 1){  //alert already use payment
                if(deleteRefund == 1){ 
                    request.setAttribute(TransactionResult, "already use all"); //alert already use payment & refund
                    request.setAttribute(TICKETALREADYUSE, ticketNo);
                }else{
                request.setAttribute(TransactionResult, "already use payment"); //alert already use payment
                request.setAttribute(TICKETALREADYUSE, ticketNo);
                }
            }
            if(deleteRefund == 1){ //alert already use refund
                if(deletePayment == 1){
                    request.setAttribute(TransactionResult, "already use all"); //alert already use payment & refund
                    request.setAttribute(TICKETALREADYUSE, ticketNo);
                }else{
                    request.setAttribute(TransactionResult, "already use refund"); //alert already use refund
                    request.setAttribute(TICKETALREADYUSE, ticketNo);
                }
            }
        } 

        request.setAttribute(TICKETROUTING,ticketRouting);
        request.setAttribute(TICKETTYPE,ticketType);
        request.setAttribute(INVOICENO,invoiceNo);
        request.setAttribute(REFNO,refNumber);
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
