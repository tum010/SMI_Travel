package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.Agent;
import com.smi.travel.datalayer.entity.AirticketPassenger;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.RefundAirticket;
import com.smi.travel.datalayer.entity.RefundAirticketDetail;
import com.smi.travel.datalayer.service.AgentService;
import com.smi.travel.datalayer.service.RefundAirlineService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.CustomerAgentInfo;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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
    private AgentService agentservice;

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String action = request.getParameter("action");
        String ticketNo = request.getParameter("ticketno");
        String refundNo = request.getParameter("refundNo");
        List<Agent> agent = getUtilityService().getListAgent();
        request.setAttribute("agent", agent);
        List user = getUtilityService().getUserList();
        request.setAttribute("user", user);
        String refundByCode = "";
//        List customer = getUtilityService().getListCustomerAgentInfo();
//        request.setAttribute("cust", customer);
        if ("search".equalsIgnoreCase(action)) {
            try {
                RefundAirticket refundAirticket = getRefundAirlineService().getRefundAirTicketFromRefundNo(refundNo);
                request.setAttribute("refundAirlineReceiveDate", refundAirticket.getReceiveDate() != null ? formatter.format(refundAirticket.getReceiveDate()) : "");
                request.setAttribute("refundAirlineRefundDate", refundAirticket.getRefundDate() != null ? formatter.format(refundAirticket.getRefundDate()) : "");
                request.setAttribute("refundAirline", refundAirticket);
                refundByCode = refundAirticket.getRefundBy();
                if(refundAirticket == null){
                    request.setAttribute("failStatus", true);
                    request.setAttribute("failMessage", "Refund no." + refundNo + " not available !");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("save".equalsIgnoreCase(action) || "saveAndNew".equalsIgnoreCase(action)) {
            try {
                RefundAirticket refundAirticket = maprequest(request);
                refundByCode = refundAirticket.getRefundBy();
                request.setAttribute("refundAirline", refundAirticket);
                refundNo = getRefundAirlineService().saveRefundAirTicket(refundAirticket);
                if (!"fail".equals(refundNo)) {
                    refundAirticket = getRefundAirlineService().getRefundAirTicketFromRefundNo(refundNo);
                    if("save".equalsIgnoreCase(action)){
                        request.setAttribute("refundAirlineReceiveDate", refundAirticket.getReceiveDate() != null ? formatter.format(refundAirticket.getReceiveDate()) : "");
                        request.setAttribute("refundAirlineRefundDate", refundAirticket.getRefundDate() != null ? formatter.format(refundAirticket.getRefundDate()) : "");
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
//        if(null != refundByCode &&refundByCode != ""){
//            List<CustomerAgentInfo> refundByList = getUtilityService().SearchListCustomerAgentInfo(refundByCode);
//            if(null != refundByList && refundByList.size() > 0){
//                CustomerAgentInfo refundBy = refundByList.get(0);
//                request.setAttribute("refundByName", refundBy.getBillName());
//            }
//        }
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
            String refundDate = uf.covertStringDateToFormatYMD(request.getParameter("refundDate"));
            String agentId = request.getParameter("agentId");
            String agentCode = request.getParameter("agentCode");
            String agenName = request.getParameter("agentName");
            String remark = request.getParameter("remark");
            String refundBy = request.getParameter("refundBy");            
            String refundByName = request.getParameter("refundByName");
            String receiveBy = request.getParameter("receiveBy");
            String receiveDate = uf.covertStringDateToFormatYMD(request.getParameter("receiveDate"));
            String status = request.getParameter("status");
            String ownerby = request.getParameter("ownerby");
            String refundtype = request.getParameter("refundtype");
            String otherreason = request.getParameter("otherreason");
            String masterid = request.getParameter("masterid");
            System.out.println(" agentId " + agentId);
            System.out.println(" agentCode " + agentCode);
            System.out.println(" agenName " + agenName);
            System.out.println(" masterid " + masterid);
            airticket.setOwnerBy(ownerby);
            airticket.setRefundType(refundtype);
            airticket.setOtherReason(otherreason);
            
            Master master = new Master();
            if(!"".equalsIgnoreCase(masterid)){
                master.setId(masterid);
                airticket.setMaster(master);
            }else{
                airticket.setMaster(null);
            }
            
            
            airticket.setId(refundId);
            airticket.setRefundNo(refundNo);
            airticket.setRefundDate(uf.convertStringToDate(refundDate));
            airticket.setReceiveDate(uf.convertStringToDate(receiveDate));
            airticket.setRefundBy(refundBy);
            airticket.setRefundByName(refundByName);
            airticket.setReceiveBy(receiveBy);
            airticket.setRemark(remark);
            if(!"".equalsIgnoreCase(status) && !"null".equalsIgnoreCase(status)){
                airticket.setStatus(Integer.parseInt(status));
            }
            Agent agent = agentservice.getAgentFromCode(agentCode);
//            agent.setId(agentId);
//            agent.setCode(agentCode);
//            agent.setName(agenName);
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
                    String paydate = uf.covertStringDateToFormatYMD(request.getParameter("paydate" + i));
                    String receivedate = uf.covertStringDateToFormatYMD(request.getParameter("receivedate" + i));
                    String checkCharge = request.getParameter("checkCharge" + i);
                    String ticketNo = request.getParameter("ticketNo" + i);
                    String totalCharge = request.getParameter("totalCharge" + i);
                    String airlineCharge = request.getParameter("airlineCharge-" + i);
                    String clientCharge = request.getParameter("clientCharge-" + i);
                    String salePrice = request.getParameter("salePrice" + i);
                    detail.setTicketNo(ticketNo);
                    System.out.println(" checkCharge " + checkCharge);
                    if("1".equalsIgnoreCase(checkCharge)){
                        detail.setRefundCharge(1);
                    }else{
                        detail.setRefundCharge(0);
                    }
                    detail.setId(detailId);
                    
                    if(!"".equalsIgnoreCase(ticketId) && ticketId != null){
                        AirticketPassenger ticket = new AirticketPassenger();
                        ticket.setId(ticketId);
                        detail.setAirticketPassenger(ticket);
                    }else{
                        detail.setAirticketPassenger(null);
                    }
                    
                    detail.setSectorRefund(refund);
                    if(receive != null && !"".equals(receive)){
                        detail.setReceiveAirline(new BigDecimal(df.parse(receive).toString()));
                    }
                    if (pay != null && !"".equals(pay)) {
                        detail.setPayCustomer(new BigDecimal(df.parse(pay).toString()));
                    }
                    if (profit != null && !"".equals(profit)) {
                        detail.setProfit(new BigDecimal(df.parse(profit).toString()));
                    }
                    if (airCom != null && !"".equals(airCom)) {
                        detail.setAirComission(new BigDecimal(df.parse(airCom).toString()));
                    }
                    if (agentCom != null && !"".equals(agentCom)) {
                        detail.setAgentComission(new BigDecimal(df.parse(agentCom).toString()));
                    }
                    if (totalCharge != null && !"".equals(totalCharge)) {
                        detail.setTotalCharge(new BigDecimal(df.parse(totalCharge).toString()));
                    }
                    if (airlineCharge != null && !"".equals(airlineCharge)) {
                        detail.setAirlineCharge(new BigDecimal(df.parse(airlineCharge).toString()));
                    }
                    if (clientCharge != null && !"".equals(clientCharge)) {
                        detail.setClientCharge(new BigDecimal(df.parse(clientCharge).toString()));
                    }
                    if (salePrice != null && !"".equals(salePrice)) {
                        detail.setSalePrice(new BigDecimal(df.parse(salePrice).toString()));
                    }
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

    public AgentService getAgentservice() {
        return agentservice;
    }

    public void setAgentservice(AgentService agentservice) {
        this.agentservice = agentservice;
    }
}
