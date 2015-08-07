package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.Agent;
import com.smi.travel.datalayer.entity.AirticketPassenger;
import com.smi.travel.datalayer.entity.RefundAirticket;
import com.smi.travel.datalayer.entity.RefundAirticketDetail;
import com.smi.travel.datalayer.service.RefundAirlineService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.SMITravelController;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
                refundAirticket.getId();
                request.setAttribute("refundAirline", refundAirticket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("save".equalsIgnoreCase(action)) {
            try {
                RefundAirticket refundAirticket = maprequest(request);
                request.setAttribute("refundAirline", refundAirticket);
                refundNo = getRefundAirlineService().saveRefundAirTicket(refundAirticket);
                if (!"fail".equals(refundNo)) {
                    refundAirticket = getRefundAirlineService().getRefundAirTicketFromRefundNo(refundNo);
                    request.setAttribute("refundAirline", refundAirticket);
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
        RefundAirticket airticket = new RefundAirticket();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
            airticket.setRefundDate(sdf.parse(refundDate));
            airticket.setRefundBy(refundBy);
            airticket.setRemark(remark);
            Agent agent = new Agent();
            agent.setId(agentId);
            agent.setCode(agentCode);
            agent.setName(agenName);
            airticket.setAgent(agent);
            airticket.setRefundAirticketDetails(new ArrayList<RefundAirticketDetail>());
            try {
                int counter = Integer.parseInt(request.getParameter("counter"));
                for (int i = 1; i < counter; i++) {
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
                    detail.setReceiveAirline(new BigDecimal(receive));
                    detail.setPayCustomer(new BigDecimal(pay));
                    detail.setProfit(new BigDecimal(profit));
                    detail.setAirComission(new BigDecimal(airCom));
                    detail.setAgentComission(new BigDecimal(agentCom));
                    detail.setReceiveDate(sdf.parse(receivedate));
                    detail.setExpenseDate(sdf.parse(paydate));
                    airticket.getRefundAirticketDetails().add(detail);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return airticket;
    }
}
