package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.Agent;
import com.smi.travel.datalayer.entity.DaytourBooking;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.DaytourCommissionService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author sumeta
 */
public class DaytourComissionController extends SMITravelController {

    private static final Logger log = Logger.getLogger(DaytourComissionController.class.getName());
    private static final ModelAndView DaytourCommission = new ModelAndView("DaytourCommission");
    private UtilityService utilservice;
    private DaytourCommissionService daytourCommissionService;
    private static final String GUIDELIST = "GuideList";
    private static final String AGENTLIST = "AgentList";
    private static final String BookingList = "BookingList";
    private static final String DateFrom = "DateFrom";
    private static final String DateTo = "DateTo";
    private static final String SelectGuide = "SelectGuide";
    private static final String SelectAgent = "SelectAgent";
    private static final String TransactionResult = "TransactionResult";

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");

        String dateFromS = request.getParameter("InputDateFrom");
        String dateToS = request.getParameter("InputDateTo");
        String selectGuideId = request.getParameter("SelectGuide");
        String selectAgentId = request.getParameter("SelectAgent");
        String dayCommRows = request.getParameter("dayCommRows");
    
        
        log.info("Action " + action);
        log.info("search DaytourCommission dateFrom: " + dateFromS + ", dateTo:" + dateToS);
        log.info("Filter by AgentId(" + selectAgentId + ") guideId(" + selectGuideId + ").");
        
       
       
        
        if ("save".equalsIgnoreCase(action)) {
            List<DaytourBooking> saveDaytourBookingList = buildeListBookingDaytourCommission(request);
            String result = daytourCommissionService.SaveDaytourComission(saveDaytourBookingList);
            request.setAttribute(TransactionResult, result);

            List<DaytourBooking> dBookingList = daytourCommissionService.getListBookingDaytourComission(dateFromS, dateToS, selectAgentId, selectGuideId);
            request.setAttribute(BookingList, dBookingList);
        } else if ("search".equalsIgnoreCase(action)) {
           
            if (StringUtils.isNotEmpty(dateFromS) && StringUtils.isNotEmpty(dateToS)) {
                List<DaytourBooking> dBookingList = daytourCommissionService.getListBookingDaytourComission(dateFromS, dateToS, selectAgentId, selectGuideId);
                daytourCommissionService.CalculateDaytourPrice(dBookingList);
                request.setAttribute(BookingList, dBookingList);
                
                if (dBookingList!=null) {
                    log.info("DaytourBooking size(" + dBookingList.size() + ")");
                } else {
                    log.info("Search not found any matched DaytourBooking!!");
                }
            } else {
                return DaytourCommission;
            }

        } else {
            setGeneralResponseAttribute(request);
        }

        request.setAttribute(DateFrom, dateFromS);
        request.setAttribute(DateTo, dateToS);
        request.setAttribute(SelectGuide, selectGuideId);
        request.setAttribute(SelectAgent, selectAgentId);
        setGeneralResponseAttribute(request);

        return DaytourCommission;
    }

    public void setGeneralResponseAttribute(HttpServletRequest request) {

        List<SystemUser> guideList = utilservice.getGuildeList();
        List<Agent> agentList = utilservice.getListAgent();
        request.setAttribute(GUIDELIST, guideList);
        request.setAttribute(AGENTLIST, agentList);
    }

    public DaytourCommissionService getDaytourCommissionService() {
        return daytourCommissionService;
    }

    public void setDaytourCommissionService(DaytourCommissionService daytourCommissionService) {
        this.daytourCommissionService = daytourCommissionService;
    }

    public UtilityService getUtilservice() {
        return utilservice;
    }

    public void setUtilservice(UtilityService utilservice) {
        this.utilservice = utilservice;
    }

    private List<DaytourBooking> buildeListBookingDaytourCommission(HttpServletRequest request) {
        String rows = request.getParameter("dayCommRows");
        log.info("rows = " + rows);

        if (!StringUtils.isNotEmpty(rows)) {
            return null;
        }

        UtilityFunction utilFunc = new UtilityFunction();

        int row = Integer.parseInt(rows);
        ArrayList<DaytourBooking> updateBooking = new ArrayList<DaytourBooking>();
        for (int i = 1; i <= row; i++) {
            String daytourBookingId = request.getParameter("daytourBookingId-" + i);
            String guideId = request.getParameter("selectGuide-" + i);
            String guideComm = request.getParameter("guideComm-" + i);
            String guideRemark = request.getParameter("guideRemark-" + i);
            String agentId = request.getParameter("AgentName-" + i);
            String agentComm = request.getParameter("agentComm-" + i);
            String agentRemark = request.getParameter("agentRemark-" + i);
            DaytourBooking booking = new DaytourBooking();
            booking.setId(daytourBookingId);

            log.info("daytourBookingId(" + i + ")-" + daytourBookingId);
            log.info("guideId(" + i + ")-" + guideId);
            log.info("guideComm(" + i + ")-" + guideComm);
            log.info("guideRemark(" + i + ")-" + guideRemark);
            log.info("agentId(" + i + ")-" + agentId);
            log.info("agentComm(" + i + ")-" + agentComm);
            log.info("agentRemark(" + i + ")-" + agentRemark);

            if (StringUtils.isNotEmpty(guideId)) {
                SystemUser guide = new SystemUser();
                guide.setId(guideId);
                booking.setGuide(guide);
            }

            Integer guideCommission = null;
            if (StringUtils.isNotEmpty(guideComm)) {
                guideCommission = utilFunc.convertStringToInteger(guideComm);
                booking.setGuideCommission(guideCommission);
            }
            booking.setRemarkGuideCom(guideRemark);

            if (StringUtils.isNotEmpty(agentId)) {
                Agent agent = new Agent();
                agent.setId(agentId);
                booking.setAgent(agent);
            }

            if (StringUtils.isNotEmpty(agentComm)) {
                Integer agentCommission = null;
                agentCommission = utilFunc.convertStringToInteger(agentComm);
                booking.setAgentComission(agentCommission);
            }
            booking.setRemarkAgentCom(agentRemark);

            updateBooking.add(booking);
            log.info("DaytourBookingId = " + daytourBookingId);
        }
        return updateBooking;
    }
}
