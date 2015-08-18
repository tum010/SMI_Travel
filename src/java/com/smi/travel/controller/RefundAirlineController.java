package com.smi.travel.controller;

import com.smi.travel.common.SMIFormUtil;
import com.smi.travel.datalayer.entity.Agent;
import com.smi.travel.datalayer.entity.AirticketPassenger;
import com.smi.travel.datalayer.entity.RefundAirticket;
import com.smi.travel.datalayer.entity.RefundAirticketDetail;
import com.smi.travel.datalayer.service.RefundAirlineService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

public class RefundAirlineController extends SMITravelController {

    private static final ModelAndView RefundAirline = new ModelAndView("RefundAirline");
    private static final ModelAndView RefundAirline_REFRESH = new ModelAndView(new RedirectView("RefundAirline.smi", true));
    private RefundAirlineService refundAirlineService;
    private UtilityService utilityService;

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        String action = request.getParameter("action");
        String ticketNo = request.getParameter("ticketno");
        String refundNo = request.getParameter("refundNo");
        List<Agent> agent = getUtilityService().getListAgent();
        request.setAttribute("agent", agent);
        if ("search".equalsIgnoreCase(action)) {
            try {
                RefundAirticket refundAirticket = getRefundAirlineService().getRefundAirTicketFromRefundNo(refundNo);
                request.setAttribute("refundAirline", refundAirticket);
                if(refundAirticket == null){
                    request.setAttribute("failStatus", true);
                    request.setAttribute("failMessage", "Refund no. not available !");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("save".equalsIgnoreCase(action) || "saveAndNew".equalsIgnoreCase(action)) {
            try {
                RefundAirticket refundAirticket = maprequest(request);
                request.setAttribute("refundAirline", refundAirticket);
                refundNo = getRefundAirlineService().saveRefundAirTicket(refundAirticket);
                if (!"fail".equals(refundNo)) {
                    refundAirticket = getRefundAirlineService().getRefundAirTicketFromRefundNo(refundNo);
                    if("save".equalsIgnoreCase(action)){
                        request.setAttribute("refundAirline", refundAirticket);
                    }else{
                        request.removeAttribute("refundAirline");
                    }
                    request.setAttribute("successStatus", true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return RefundAirline;
    }

    /**
     * @return the refundAirlineService
     */
    public RefundAirlineService getRefundAirlineService() {
        return refundAirlineService;
    }

    /**
     * @param refundAirlineService the refundAirlineService to set
     */
    public void setRefundAirlineService(RefundAirlineService refundAirlineService) {
        this.refundAirlineService = refundAirlineService;
    }

    /**
     * @return the utilityService
     */
    public UtilityService getUtilityService() {
        return utilityService;
    }

    /**
     * @param utilityService the utilityService to set
     */
    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }

    private RefundAirticket maprequest(HttpServletRequest request) {
        UtilityFunction uf = new UtilityFunction();
        DecimalFormat df = new DecimalFormat("###,##0.00");
        RefundAirticket airticket = new RefundAirticket();
        try {
            String refundId = request.getParameter("refundId");
            String refundNo = request.getParameter("refundNo");
            String refundDate = request.getParameter("refundDate");
            String agentId = request.getParameter("agentId");
            String agentCode = request.getParameter("agentCode");
            String agenName = request.getParameter("agentName");
            String remark = request.getParameter("remark");
            String refundBy = request.getParameter("refundBy");

            airticket.setId(refundId);
            airticket.setRefundNo(refundNo);
            airticket.setRefundDate(uf.convertStringToDate(refundDate));
            airticket.setRefundBy(refundBy);
            airticket.setRemark(remark);
            Agent agent = new Agent();
            agent.setId(agentId);
            agent.setCode(agentCode);
            agent.setName(agenName);
            airticket.setAgent(agent);
            airticket.setRefundAirticketDetails(new ArrayList<RefundAirticketDetail>());
            int counter = Integer.parseInt(request.getParameter("counter"));
            for (int i = 1; i < counter; i++) {
                try {
                    RefundAirticketDetail detail = new RefundAirticketDetail();
                    String detailId = request.getParameter("detailId" + i);
                    String ticketId = request.getParameter("ticketId" + i);
                    String refund = request.getParameter("refund" + i);
                    String receive = request.getParameter("receive" + i);
                    String pay = request.getParameter("pay" + i);
                    String profit = request.getParameter("profit" + i);
                    String airCom = request.getParameter("airCom" + i);
                    String agentCom = request.getParameter("agentCom" + i);
                    String paydate = request.getParameter("paydate" + i);
                    String receivedate = request.getParameter("receivedate" + i);
                    detail.setId(detailId);
                    AirticketPassenger ticket = new AirticketPassenger();
                    ticket.setId(ticketId);
                    detail.setAirticketPassenger(ticket);
                    detail.setSectorRefund(refund);
                    detail.setReceiveAirline(new BigDecimal(df.parse(receive).toString()));
                    detail.setPayCustomer(new BigDecimal(df.parse(pay).toString()));
                    detail.setProfit(new BigDecimal(df.parse(profit).toString()));
                    detail.setAirComission(new BigDecimal(df.parse(airCom).toString()));
                    detail.setAgentComission(new BigDecimal(df.parse(agentCom).toString()));
                    detail.setReceiveDate(uf.convertStringToDate(receivedate));
                    detail.setExpenseDate(uf.convertStringToDate(paydate));
                    airticket.getRefundAirticketDetails().add(detail);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return airticket;
        
    }
}
