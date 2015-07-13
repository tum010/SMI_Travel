package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.Agent;
import com.smi.travel.datalayer.entity.MAirlineAgent;
import com.smi.travel.datalayer.entity.TicketFareAirline;
import com.smi.travel.datalayer.service.AgentService;
import com.smi.travel.datalayer.service.TicketFareAirlineService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class AddTicketFareController extends SMITravelController {
    private static final ModelAndView AddTicketFare = new ModelAndView("AddTicketFare");
    private static final ModelAndView AddTicketFare_REFRESH = new ModelAndView(new RedirectView("AddTicketFare.smi", true));
    private static final String AIRLINELIST = "airlineList";
    private static final String TICKETFARE = "ticketFare";
    private static final String Agent = "Agent";
    private static final String SAVERESULT = "saveresult";
    private static final String SELECTEDAGENT = "SelectedAgent";
    private static final String ISSUEDATE = "issueDate";
    private static final String OVERDATE = "overDate";
    private static final String LITTERDATE = "litterDate";
    private static final String DECPAYDATE = "decPayDate";
    private static final String ADDPAYDATE = "addPayDate";
    private static final String AGENTPAYDATE = "agentPayDate";
    private static final String AGENTRECEIVEDATE = "agentReceiveDate";
    private static final String TICKETROUNTING = "TicketRounting";
    private static final String TICKETBUY = "TicketBuy";
    private static final String TICKETTYPE = "TicketType";

    private UtilityService utilityService;
    private TicketFareAirlineService ticketFareAirlineService;
    private AgentService agentService;
    UtilityFunction util;
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
//        String ticketDate = request.getParameter("ticketDate");
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
        Agent agents = new Agent();
        
        util = new UtilityFunction();
        if ("save".equalsIgnoreCase(action)) {
            MAirlineAgent mAirlineAgent = new MAirlineAgent();
            mAirlineAgent.setId(ticketAirline);
           
            TicketFareAirline ticketFareAirline = new TicketFareAirline();
            ticketFareAirline.setMAirlineAgent(mAirlineAgent);
            ticketFareAirline.setTicketNo(ticketNo);
            ticketFareAirline.setTicketType(ticketType);
            request.setAttribute(TICKETTYPE, ticketType);
            ticketFareAirline.setTicketBuy(ticketBuy);
            request.setAttribute(TICKETBUY, ticketBuy);
            ticketFareAirline.setTicketRounting(ticketRounting);
            request.setAttribute(TICKETROUNTING, ticketRounting);

            ticketFareAirline.setPassenger(passenger);
            if(StringUtils.isNotEmpty(issueDate)){
                ticketFareAirline.setIssueDate(util.convertStringToDate(issueDate));
                request.setAttribute(ISSUEDATE, issueDate);
            }
            if(StringUtils.isNotEmpty(ticketFare)){
                ticketFareAirline.setTicketFare(new BigDecimal(String.valueOf(ticketFare))); 
            }
            if(StringUtils.isNotEmpty(ticketTax)){
                ticketFareAirline.setTicketTax(new BigDecimal(String.valueOf(ticketTax)));
            }
            if(StringUtils.isNotEmpty(ticketIns)){
                ticketFareAirline.setTicketIns(new BigDecimal(String.valueOf(ticketIns)));
            }
            if(StringUtils.isNotEmpty(ticketCommission)){
                ticketFareAirline.setTicketCommission(new BigDecimal(String.valueOf(ticketCommission)));
            }
            if(StringUtils.isNotEmpty(agentCommission)){
                ticketFareAirline.setAgentCommission(new BigDecimal(String.valueOf(agentCommission)));
            }
            if(StringUtils.isNotEmpty(salePrice)){
                ticketFareAirline.setSalePrice(new BigDecimal(String.valueOf(salePrice)));
            }
            if(StringUtils.isNotEmpty(diffVat)){
                ticketFareAirline.setDiffVat(new BigDecimal(String.valueOf(diffVat)));
            }
            if (StringUtils.isNotEmpty(agentId)){
                ticketFareAirline.setAgentId(util.convertStringToInteger(agentId));
                agents = getAgentService().getAgentFromID(String.valueOf(agentId));
                request.setAttribute(SELECTEDAGENT, agents);
            }

            ticketFareAirline.setRemark(remark);
            if(StringUtils.isNotEmpty(overCommission)){
                ticketFareAirline.setOverCommission(new BigDecimal(String.valueOf(overCommission)));
            }
            if(StringUtils.isNotEmpty(litterCommission)){
                ticketFareAirline.setLitterCommission(new BigDecimal(String.valueOf(litterCommission)));
            }
            if(StringUtils.isNotEmpty(decPay)){
                ticketFareAirline.setDecPay(new BigDecimal(String.valueOf(decPay)));
            }
            if(StringUtils.isNotEmpty(addPay)){
                ticketFareAirline.setAddPay(new BigDecimal(String.valueOf(addPay)));
            }
            if(StringUtils.isNotEmpty(agentComPay)){
                ticketFareAirline.setAgentComPay(new BigDecimal(String.valueOf(agentComPay)));
            }
            if(StringUtils.isNotEmpty(agentComReceive)){
                ticketFareAirline.setAgentComReceive(new BigDecimal(String.valueOf(agentComReceive)));
            }
            if (StringUtils.isNotEmpty(overDate)){
                ticketFareAirline.setOverDate(util.convertStringToDate(overDate));
                request.setAttribute(OVERDATE, overDate);
            }
            if (StringUtils.isNotEmpty(litterDate)){
                ticketFareAirline.setLitterDate(util.convertStringToDate(litterDate));
                request.setAttribute(LITTERDATE, litterDate);
            }
            if (StringUtils.isNotEmpty(decPayDate)){
                ticketFareAirline.setDecPayDate(util.convertStringToDate(decPayDate));
                request.setAttribute(DECPAYDATE, decPayDate);
            }
            if (StringUtils.isNotEmpty(addPayDate)){
                ticketFareAirline.setAddPayDate(util.convertStringToDate(addPayDate));
                request.setAttribute(ADDPAYDATE, addPayDate);
            }
            if (StringUtils.isNotEmpty(agentPayDate)){
                ticketFareAirline.setAgentPayDate(util.convertStringToDate(agentPayDate));
                request.setAttribute(AGENTPAYDATE, agentPayDate);
            }
            if (StringUtils.isNotEmpty(agentReceiveDate)){
                ticketFareAirline.setAgentReceiveDate(util.convertStringToDate(agentReceiveDate));
                request.setAttribute(AGENTRECEIVEDATE, agentReceiveDate);
            }
            result = ticketFareAirlineService.InsertTicketFare(ticketFareAirline);
            if (result == 1) {
                request.setAttribute(SAVERESULT, "save successful");
            } else {
                request.setAttribute(SAVERESULT, "save unsuccessful");
            }
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

    public AgentService getAgentService() {
        return agentService;
    }

    public void setAgentService(AgentService agentService) {
        this.agentService = agentService;
    }

}
