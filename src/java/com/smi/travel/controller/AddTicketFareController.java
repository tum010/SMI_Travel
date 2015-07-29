package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.Agent;
import com.smi.travel.datalayer.entity.AirticketFlight;
import com.smi.travel.datalayer.entity.AirticketPassenger;
import com.smi.travel.datalayer.entity.BookingFlight;
import com.smi.travel.datalayer.entity.MAirlineAgent;
import com.smi.travel.datalayer.entity.MPaymentDoctype;
import com.smi.travel.datalayer.entity.TicketFareAirline;
import com.smi.travel.datalayer.service.AgentService;
import com.smi.travel.datalayer.service.TicketFareAirlineService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private static final String TICKETROUTING = "TicketRouting";
    private static final String TICKETBUY = "TicketBuy";
    private static final String TICKETTYPE = "TicketType";
    private static final String TICKETLIST = "ticketList";
    private static final String DEPARTMENT = "department";
    private static final String FLIGHTDETAIL = "Flight_Detail";
    private static final String PVTYPELIST = "pvTypeList";
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
        String ticketRouting = request.getParameter("ticketRouting");
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
        String ticketId = request.getParameter("ticketId");
        String invoiceAmount = request.getParameter("invoiceAmount");
        String department = request.getParameter("department");
        String pvType = request.getParameter("pvType");
        String pvCode = request.getParameter("pvCode");
        
        String result = "";
        List<MAirlineAgent> mAirlineAgentsList = utilityService.getListMAirLineAgent();
        request.setAttribute(AIRLINELIST,mAirlineAgentsList);
        List<Agent> agent = utilityService.getListAgent();
        request.setAttribute(Agent, agent);
        List<MPaymentDoctype> mPaymentDoctypeList = utilityService.getListMpaymentDocType("airticket");
        request.setAttribute(PVTYPELIST, mPaymentDoctypeList);
        
        Agent agents = new Agent();

        util = new UtilityFunction();
        System.out.print("action :" + action + "//");
        TicketFareAirline ticketFareAirline = new TicketFareAirline();
        AirticketPassenger airticketPassenger = new AirticketPassenger();
        MAirlineAgent mAirlineAgent = new MAirlineAgent();
        MPaymentDoctype mPaymentDoctype = new MPaymentDoctype();
        if ("save".equalsIgnoreCase(action)){
            System.out.println("ticketId : "+ ticketId);
            mAirlineAgent.setId(ticketAirline);
            if(StringUtils.isNotEmpty(ticketAirline)){
                ticketFareAirline.setMAirlineAgent(mAirlineAgent);
            }
            mPaymentDoctype.setId(pvType);
            if(StringUtils.isNotEmpty(pvType)){
                ticketFareAirline.setMPaymentDoctype(mPaymentDoctype);
            }
            
            if(StringUtils.isNotEmpty(pvCode)){
                ticketFareAirline.setPvCode(pvCode);
            }
            
            if(StringUtils.isNotEmpty(ticketId)){
                ticketFareAirline.setId(ticketId);
            }
            ticketFareAirline.setTicketNo(ticketNo);
            ticketFareAirline.setTicketType(ticketType);
            request.setAttribute(TICKETTYPE, ticketType);
            ticketFareAirline.setTicketBuy(ticketBuy);
            request.setAttribute(TICKETBUY, ticketBuy);
            ticketFareAirline.setTicketRouting(ticketRouting);
            request.setAttribute(TICKETROUTING, ticketRouting);

            ticketFareAirline.setPassenger(passenger);
            if(StringUtils.isNotEmpty(issueDate)){
                ticketFareAirline.setIssueDate(util.convertStringToDate(issueDate));
                request.setAttribute(ISSUEDATE, issueDate);
            }
            
            if(StringUtils.isNotEmpty(ticketFare)){
                ticketFareAirline.setTicketFare(new BigDecimal(String.valueOf(ticketFare.replaceAll(",","")))); 
            }else{
                ticketFareAirline.setTicketFare(new BigDecimal(0)); 
            }
            
            if(StringUtils.isNotEmpty(ticketTax)){
                ticketFareAirline.setTicketTax(new BigDecimal(String.valueOf(ticketTax.replaceAll(",",""))));
            }else{
                ticketFareAirline.setTicketTax(new BigDecimal(0)); 
            }
           
            if(StringUtils.isNotEmpty(ticketIns)){
                ticketFareAirline.setTicketIns(new BigDecimal(String.valueOf(ticketIns.replaceAll(",",""))));
            }else{
                ticketFareAirline.setTicketIns(new BigDecimal(0)); 
            }
            if(StringUtils.isNotEmpty(ticketCommission)){
                ticketFareAirline.setTicketCommission(new BigDecimal(String.valueOf(ticketCommission.replaceAll(",",""))));
            }else{
                ticketFareAirline.setTicketCommission(new BigDecimal(0)); 
            }
            if(StringUtils.isNotEmpty(agentCommission)){
                ticketFareAirline.setAgentCommission(new BigDecimal(String.valueOf(agentCommission.replaceAll(",",""))));
            }else{
                ticketFareAirline.setAgentCommission(new BigDecimal(0)); 
            }
            if(StringUtils.isNotEmpty(salePrice)){
                ticketFareAirline.setSalePrice(new BigDecimal(String.valueOf(salePrice.replaceAll(",",""))));
            }else{
                ticketFareAirline.setSalePrice(new BigDecimal(0)); 
            }
            if(StringUtils.isNotEmpty(diffVat)){
                ticketFareAirline.setDiffVat(new BigDecimal(String.valueOf(diffVat.replaceAll(",",""))));
            }else{
                ticketFareAirline.setDiffVat(new BigDecimal(0)); 
            }
            if (StringUtils.isNotEmpty(agentId)){
                ticketFareAirline.setAgentId(util.convertStringToInteger(agentId));
                agents = getAgentService().getAgentFromID(String.valueOf(agentId));
                request.setAttribute(SELECTEDAGENT, agents);
            }

            ticketFareAirline.setRemark(remark);
            if(StringUtils.isNotEmpty(overCommission)){
                ticketFareAirline.setOverCommission(new BigDecimal(String.valueOf(overCommission.replaceAll(",",""))));
            }else{
                ticketFareAirline.setOverCommission(new BigDecimal(0)); 
            }
            if(StringUtils.isNotEmpty(litterCommission)){
                ticketFareAirline.setLitterCommission(new BigDecimal(String.valueOf(litterCommission.replaceAll(",",""))));
            }else{
                ticketFareAirline.setLitterCommission(new BigDecimal(0)); 
            }
            if(StringUtils.isNotEmpty(decPay)){
                ticketFareAirline.setDecPay(new BigDecimal(String.valueOf(decPay.replaceAll(",",""))));
            }else{
                ticketFareAirline.setDecPay(new BigDecimal(0)); 
            }
            if(StringUtils.isNotEmpty(addPay)){
                ticketFareAirline.setAddPay(new BigDecimal(String.valueOf(addPay.replaceAll(",",""))));
            }else{
                ticketFareAirline.setAddPay(new BigDecimal(0)); 
            }
            if(StringUtils.isNotEmpty(agentComPay)){
                ticketFareAirline.setAgentComPay(new BigDecimal(String.valueOf(agentComPay.replaceAll(",",""))));
            }else{
                ticketFareAirline.setAgentComPay(new BigDecimal(0)); 
            }
            if(StringUtils.isNotEmpty(agentComReceive)){
                ticketFareAirline.setAgentComReceive(new BigDecimal(String.valueOf(agentComReceive.replaceAll(",",""))));
            }else{
                ticketFareAirline.setAgentComReceive(new BigDecimal(0)); 
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
            if (StringUtils.isNotEmpty(department)){
                ticketFareAirline.setDepartment(department);
                request.setAttribute(DEPARTMENT, department);
            }
            result = ticketFareAirlineService.validateSaveTicket(ticketFareAirline);
            System.out.print("result :" + result + " =================== ");
            if (result == "fail") {
                request.setAttribute(SAVERESULT, "save unsuccessful");
            } else if (result == "success"){
                request.setAttribute(SAVERESULT, "save successful");
            } else{
                ticketFareAirline.setPvCode(result);
                request.setAttribute(SAVERESULT, "save successful");
            }
           
            request.setAttribute(TICKETFARE,ticketFareAirline); 
            
        } else if ("edit".equalsIgnoreCase(action)) {
            System.out.print("ticketId : " +ticketId);
            ticketFareAirline = ticketFareAirlineService.getTicketFareFromId(ticketId);
            request.setAttribute(TICKETFARE,ticketFareAirline);
            request.setAttribute(TICKETTYPE, ticketFareAirline.getTicketType());
            request.setAttribute(TICKETBUY, ticketFareAirline.getTicketBuy());
            request.setAttribute(TICKETROUTING, ticketFareAirline.getTicketRouting());
            request.setAttribute(ISSUEDATE, ticketFareAirline.getIssueDate());
            agents = getAgentService().getAgentFromID(String.valueOf(ticketFareAirline.getAgentId()));
            request.setAttribute(SELECTEDAGENT, agents);
            request.setAttribute(OVERDATE, ticketFareAirline.getOverDate());
            request.setAttribute(LITTERDATE, ticketFareAirline.getLitterDate());
            request.setAttribute(DECPAYDATE, ticketFareAirline.getDecPayDate());
            request.setAttribute(ADDPAYDATE, ticketFareAirline.getAddPayDate());
            request.setAttribute(AGENTPAYDATE, ticketFareAirline.getAgentPayDate());
            request.setAttribute(AGENTRECEIVEDATE, ticketFareAirline.getAgentReceiveDate());
            request.setAttribute(DEPARTMENT, ticketFareAirline.getDepartment());
        }else if ("search".equalsIgnoreCase(action)) {
            System.out.print("ticketNo : " +ticketNo);
            if(ticketNo == null){
                System.out.print("ticketNo is null");
            }else{
                ticketFareAirline = ticketFareAirlineService.getTicketFareFromTicketNo(ticketNo);
                List<BookingFlight> bookingFlights = new ArrayList<BookingFlight>();
                bookingFlights = ticketFareAirlineService.getListFlightFromTicketNo(ticketNo);
                request.setAttribute(FLIGHTDETAIL, bookingFlights);
                if(ticketFareAirline == null){
                    String airticketPass = ticketFareAirlineService.getTicketFareBookingFromTicketNo(ticketNo);
                    TicketFareAirline ticketFareAirlines = new TicketFareAirline();
                    if(StringUtils.isNotEmpty(airticketPass)){
                        String[] parts = airticketPass.split(",");
                        String TicketFare = parts[0].trim(); 
                        String TicketTax = parts[1].trim(); 
                        String IssueDate = parts[2].trim(); 
                        String TicketRouting = parts[3].trim(); 
                        String Airline = parts[4].trim(); 
                        String TicketBy = parts[5].trim(); 
                        String Passenger = parts[6].trim(); 
                        String Department = parts[7].trim(); 

                        ticketFareAirlines.setTicketNo(ticketNo);
                        if(StringUtils.isNotEmpty(TicketFare)){
                            ticketFareAirlines.setTicketFare(new BigDecimal(TicketFare));
                        }else{
                            ticketFareAirlines.setTicketFare(new BigDecimal(0));
                        }
                        if(StringUtils.isNotEmpty(TicketTax)){
                            ticketFareAirlines.setTicketTax(new BigDecimal(TicketTax));
                        }else{
                            ticketFareAirlines.setTicketTax(new BigDecimal(0));
                        }

                        if(Department.equalsIgnoreCase("I")){
                            Department = "wendy";
                        }else if(Department.equalsIgnoreCase("O")){
                            Department = "outbound";
                        }
                        mAirlineAgent.setId(Airline);
                        if(StringUtils.isNotEmpty(Airline)){
                            ticketFareAirlines.setMAirlineAgent(mAirlineAgent);
                        }
                        ticketFareAirlines.setPassenger(Passenger);
                        request.setAttribute(TICKETBUY, TicketBy);
                        request.setAttribute(TICKETROUTING, TicketRouting);
                        request.setAttribute(ISSUEDATE, IssueDate);
                        request.setAttribute(DEPARTMENT, Department);
                    }
                    ticketFareAirlines.setTicketNo(ticketNo);
                    request.setAttribute(TICKETFARE,ticketFareAirlines);
                }else{
                    if(StringUtils.isEmpty(String.valueOf(ticketFareAirline.getTicketFare()))){
                        ticketFareAirline.setTicketFare(new BigDecimal(0)); 
                    }
                    if(StringUtils.isEmpty(String.valueOf(ticketFareAirline.getTicketTax()))){
                        ticketFareAirline.setTicketTax(new BigDecimal(0)); 
                    }
                    if(StringUtils.isEmpty(String.valueOf(ticketFareAirline.getTicketIns()))){
                        ticketFareAirline.setTicketIns(new BigDecimal(0)); 
                    }
                    if(StringUtils.isEmpty(String.valueOf(ticketFareAirline.getTicketCommission()))){
                        ticketFareAirline.setTicketCommission(new BigDecimal(0)); 
                    }
                    if(StringUtils.isEmpty(String.valueOf(ticketFareAirline.getAgentCommission()))){
                        ticketFareAirline.setAgentCommission(new BigDecimal(0)); 
                    }                    
                    if(StringUtils.isEmpty(String.valueOf(ticketFareAirline.getSalePrice()))){
                        ticketFareAirline.setSalePrice(new BigDecimal(0)); 
                    }
                    if(StringUtils.isEmpty(String.valueOf(ticketFareAirline.getDiffVat()))){
                        ticketFareAirline.setDiffVat(new BigDecimal(0)); 
                    }             
                    if(StringUtils.isEmpty(String.valueOf(ticketFareAirline.getOverCommission()))){
                        ticketFareAirline.setOverCommission(new BigDecimal(0)); 
                    }                    
                    if(StringUtils.isEmpty(String.valueOf(ticketFareAirline.getAddPay()))){
                        ticketFareAirline.setAddPay(new BigDecimal(0)); 
                    }                    
                    if(StringUtils.isEmpty(String.valueOf(ticketFareAirline.getAgentComPay()))){
                        ticketFareAirline.setAgentComPay(new BigDecimal(0)); 
                    }                    
                    if(StringUtils.isEmpty(String.valueOf(ticketFareAirline.getLitterCommission()))){
                        ticketFareAirline.setLitterCommission(new BigDecimal(0)); 
                    }
                    if(StringUtils.isEmpty(String.valueOf(ticketFareAirline.getDecPay()))){
                        ticketFareAirline.setDecPay(new BigDecimal(0)); 
                    }
                    if(StringUtils.isEmpty(String.valueOf(ticketFareAirline.getAgentComReceive()))){
                        ticketFareAirline.setAgentComReceive(new BigDecimal(0)); 
                    }                    

                    request.setAttribute(TICKETFARE,ticketFareAirline);
                    request.setAttribute(TICKETTYPE, ticketFareAirline.getTicketType());
                    request.setAttribute(TICKETBUY, ticketFareAirline.getTicketBuy());
                    request.setAttribute(TICKETROUTING, ticketFareAirline.getTicketRouting());
                    request.setAttribute(ISSUEDATE, ticketFareAirline.getIssueDate());
                    agents = getAgentService().getAgentFromID(String.valueOf(ticketFareAirline.getAgentId()));
                    request.setAttribute(SELECTEDAGENT, agents);
                    request.setAttribute(OVERDATE, ticketFareAirline.getOverDate());
                    request.setAttribute(LITTERDATE, ticketFareAirline.getLitterDate());
                    request.setAttribute(DECPAYDATE, ticketFareAirline.getDecPayDate());
                    request.setAttribute(ADDPAYDATE, ticketFareAirline.getAddPayDate());
                    request.setAttribute(AGENTPAYDATE, ticketFareAirline.getAgentPayDate());
                    request.setAttribute(AGENTRECEIVEDATE, ticketFareAirline.getAgentReceiveDate());
                    request.setAttribute(DEPARTMENT, ticketFareAirline.getDepartment());
                }	
            }
        }

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
