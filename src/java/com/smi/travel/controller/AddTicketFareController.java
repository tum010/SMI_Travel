package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.Agent;
import com.smi.travel.datalayer.entity.MAirlineAgent;
import com.smi.travel.datalayer.entity.TicketFareAirline;
import com.smi.travel.datalayer.service.TicketFareAirlineService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.SMITravelController;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class AddTicketFareController extends SMITravelController {
    private static final ModelAndView AddTicketFare = new ModelAndView("AddTicketFare");
    private static final ModelAndView AddTicketFare_REFRESH = new ModelAndView(new RedirectView("AddTicketFare.smi", true));
    private static final String AIRLINELIST = "airlineList";
    private static final String TICKETFARE = "ticketFare";
    private static final String Agent = "Agent";
    
    private UtilityService utilityService;
    private TicketFareAirlineService ticketFareAirlineService;
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String ticketNo = request.getParameter("ticketNo");
        String ticketType = request.getParameter("ticketType");
        String ticketBuy = request.getParameter("ticketBuy");
        String ticketRounting = request.getParameter("ticketRounting");
        String ticketAirline = request.getParameter("ticketAirline");
        String passenger = request.getParameter("passenger");
        String issueDate = request.getParameter("issueDate");
        String ticketDate = request.getParameter("ticketDate");
        String ticketFare = request.getParameter("ticketFare");
        String ticketTax = request.getParameter("ticketTax");
        String ticketIns = request.getParameter("ticketIns");
        String ticketCommission = request.getParameter("ticketCommission");
        String agentCommission = request.getParameter("agentCommission");
        String salePrice = request.getParameter("salePrice");
        String diffVat = request.getParameter("diffVat");
        String agentId = request.getParameter("agent_id");
        String remark = request.getParameter("remark");
        String overCommission = request.getParameter("overCommission");
        String litterCommission = request.getParameter("litterCommission");
        String decPay = request.getParameter("decPay");
        String addPay = request.getParameter("addPay");
        String agentComPay = request.getParameter("agentComPay");
        String agentComReceive = request.getParameter("agentComReceive");
        String overDate = request.getParameter("overDate");
        String litterDate = request.getParameter("litterDate");
        String decPayDate = request.getParameter("decPayDate");
        String addPayDate = request.getParameter("addPayDate");
        String agentPayDate = request.getParameter("agentPayDate");
        String agentReceiveDate = request.getParameter("agentReceiveDate");
        int result = 0;
        List<MAirlineAgent> mAirlineAgentsList = utilityService.getListMAirLineAgent();
        request.setAttribute(AIRLINELIST,mAirlineAgentsList);
        List<Agent> agent = utilityService.getListAgent();
        request.setAttribute(Agent, agent);
       
        if ("save".equalsIgnoreCase(action)) {
            MAirlineAgent mAirlineAgent = new MAirlineAgent();
            mAirlineAgent.setId(ticketAirline);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println("issueDate ++++++++++ "+issueDate);
            Date issuedate = null;
            Date ticketdate = null;
            Date overdate = null;
            Date litterdate = null;
            Date decpaydate = null;
            Date addpaydate = null;
            Date agentpaydate = null;
            Date agentreceivedate = null;
            try {
                if(("").equals(issueDate)){
                    issuedate = formatter.parse(issueDate);
                }
                overdate = formatter.parse(overDate);
                litterdate = formatter.parse(String.valueOf(litterDate));
                decpaydate = formatter.parse(String.valueOf(decPayDate));
                addpaydate = formatter.parse(String.valueOf(addPayDate));
                agentpaydate = formatter.parse(String.valueOf(agentPayDate));
                agentreceivedate = formatter.parse(String.valueOf(agentReceiveDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            TicketFareAirline ticketFareAirline = new TicketFareAirline();
            ticketFareAirline.setMAirlineAgent(mAirlineAgent);
            ticketFareAirline.setTicketNo(ticketNo);
            ticketFareAirline.setTicketType(ticketType);
            ticketFareAirline.setTicketBuy(ticketBuy);
            ticketFareAirline.setTicketRounting(ticketRounting);
            ticketFareAirline.setPassenger(passenger);
            ticketFareAirline.setIssueDate(issuedate);
//            ticketFareAirline.setTicketDate(ticketdate);
            ticketFareAirline.setTicketFare(new BigDecimal(String.valueOf(ticketFare)));
            ticketFareAirline.setTicketTax(new BigDecimal(String.valueOf(ticketTax)));
            ticketFareAirline.setTicketIns(new BigDecimal(String.valueOf(ticketIns)));
            ticketFareAirline.setTicketCommission(new BigDecimal(String.valueOf(ticketCommission)));
            ticketFareAirline.setAgentCommission(new BigDecimal(String.valueOf(agentCommission)));
            ticketFareAirline.setSalePrice(new BigDecimal(String.valueOf(salePrice)));
            ticketFareAirline.setDiffVat(new BigDecimal(String.valueOf(diffVat)));
            ticketFareAirline.setAgentId(Integer.parseInt(String.valueOf(agentId)));
            ticketFareAirline.setRemark(remark);
            ticketFareAirline.setOverCommission(new BigDecimal(String.valueOf(overCommission)));
            ticketFareAirline.setLitterCommission(new BigDecimal(String.valueOf(litterCommission)));
            ticketFareAirline.setDecPay(new BigDecimal(String.valueOf(decPay)));
            ticketFareAirline.setAddPay(new BigDecimal(String.valueOf(addPay)));
            ticketFareAirline.setAgentComPay(new BigDecimal(String.valueOf(agentComPay)));
            ticketFareAirline.setAgentComReceive(new BigDecimal(String.valueOf(agentComReceive)));
            ticketFareAirline.setOverDate(overdate);
            ticketFareAirline.setLitterDate(litterdate);
            ticketFareAirline.setDecPayDate(decpaydate);
            ticketFareAirline.setAddPayDate(addpaydate);
            ticketFareAirline.setAgentPayDate(agentpaydate);
            ticketFareAirline.setAgentReceiveDate(agentreceivedate);
            System.out.println("ticketFareAirline Big " +ticketFareAirline.getTicketFare());
            request.setAttribute(TICKETFARE,ticketFareAirline);
        } 
        
        
        
        
        
        
        
//        MAirlineAgent mAirlineAgent = new MAirlineAgent();
//        mAirlineAgent.setId(ticketAirline);
//        TicketFareAirline ticketFareAirline = new TicketFareAirline();
//        request.setAttribute(TICKETFARE,ticketFareAirline);
        return AddTicketFare;
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
