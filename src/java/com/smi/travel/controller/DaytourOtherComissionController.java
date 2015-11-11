/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.Agent;
import com.smi.travel.datalayer.entity.OtherBooking;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.BookingOtherService;
import com.smi.travel.datalayer.service.DaytourCommissionService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Kanokporn
 */

public class DaytourOtherComissionController extends SMITravelController {
    private static final Logger log = Logger.getLogger(DaytourComissionController.class.getName());
    private static final ModelAndView DaytourOtherCommission = new ModelAndView("DaytourOtherCommission");
    private UtilityService utilservice;
    private DaytourCommissionService daytourCommissionService;
    private BookingOtherService bookingOtherService;
    private static final String GUIDELIST = "GuideList";
    private static final String AGENTLIST = "AgentList";
    private static final String BookingList = "BookingList";
    private static final String DateFrom = "DateFrom";
    private static final String DateTo = "DateTo";
    private static final String SelectGuide = "SelectGuide";
    private static final String SelectAgent = "SelectAgent";
    private static final String TransactionResult = "TransactionResult";
    UtilityFunction utility = new UtilityFunction();

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String actionAddGuide = request.getParameter("addGuideAction");
        
        if("addGuide".equalsIgnoreCase(actionAddGuide)){
            action = actionAddGuide;
        }
        
        String dateFromS = request.getParameter("InputDateFrom");
        String dateToS = request.getParameter("InputDateTo");
        String selectGuideId = request.getParameter("SelectGuide");
        String selectAgentId = request.getParameter("SelectAgent");
        String dayCommRows = request.getParameter("dayCommRows");
    
        
        log.info("Action " + action);
        log.info("search DaytourCommission dateFrom: " + dateFromS + ", dateTo:" + dateToS);
        log.info("Filter by AgentId(" + selectAgentId + ") guideId(" + selectGuideId + ").");
        
        if ("save".equalsIgnoreCase(action)) {
            List<OtherBooking> saveDaytourBookingList = buildeListBookingDaytourCommission(request);
            String result = bookingOtherService.saveOtherBookCommission(saveDaytourBookingList);
            request.setAttribute(TransactionResult, result);

            List<OtherBooking> dBookingList = bookingOtherService.getListBookingDaytourComission(dateFromS, dateToS, selectAgentId, selectGuideId);
            request.setAttribute(BookingList, dBookingList);
        } else if ("search".equalsIgnoreCase(action)) {
           
            if (StringUtils.isNotEmpty(dateFromS) && StringUtils.isNotEmpty(dateToS)) {
                List<OtherBooking> dBookingList = bookingOtherService.getListBookingDaytourComission(dateFromS, dateToS, selectAgentId, selectGuideId);
                
                if(dBookingList != null){
                    request.setAttribute(BookingList, dBookingList);
                }else{
                    request.setAttribute(BookingList, dBookingList);
                }
                
                if (dBookingList!=null) {
                    log.info("DaytourBooking size(" + dBookingList.size() + ")");
                } else {
                    log.info("Search not found any matched DaytourBooking!!");
                }
            } else {
                return DaytourOtherCommission;
            }

        }else if("addGuide".equalsIgnoreCase(action)){
            SystemUser user = new SystemUser();
            String name = request.getParameter("guideName");
            String detail = request.getParameter("guideDetail");
            String tel = request.getParameter("guideTel");
            // Search Booking
            String fromdateAdd =  request.getParameter("fromdateAdd");
            String todateAdd =  request.getParameter("todateAdd");
            String agentAdd =  request.getParameter("agentAdd");
            String guideAdd =  request.getParameter("guideAdd");
            if (StringUtils.isNotEmpty(fromdateAdd) && StringUtils.isNotEmpty(todateAdd)) {
                List<OtherBooking> dBookingList = bookingOtherService.getListBookingDaytourComission(fromdateAdd, todateAdd, agentAdd, guideAdd);
                if(dBookingList != null){
                    request.setAttribute(BookingList, dBookingList);
                    dateFromS = fromdateAdd;
                    dateToS = todateAdd;
                    selectAgentId = agentAdd;
                    selectGuideId = guideAdd;
                }else{
                    request.setAttribute(BookingList, dBookingList);
                }
                
                if (dBookingList!=null) {
                    log.info("DaytourBooking size(" + dBookingList.size() + ")");
                } else {
                    log.info("Search not found any matched DaytourBooking!!");
                }
            }
            
            user.setName(name + " " + detail);
            user.setUsername(name);
            user.setTel(tel);
            user.setPassword("MD5"+name);
            SystemUser username = (SystemUser) session.getAttribute("USER");
            user.setCreateBy(username.getName());
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            System.out.println(dateFormat.format(cal.getTime()));
            String createDate = dateFormat.format(cal.getTime());
            Date date = new Date();
            date = utility.convertStringToDate(createDate);
            user.setCreateDate(date);
            
            user.setPosition("GUIDE");
            user.setIsExGuide(1);
            user.setStatus("active");
            int result = 0;
            String resultTest = "";
            result  = bookingOtherService.insertSystemUser(user);
            System.out.println("Result Add Guide : " + result);
            if(result == 0){
                resultTest = "guideunsuccess";
            }else{
                
                resultTest = "guidesuccess";
            }
            request.setAttribute("TransactionResult", resultTest);
        } else {
            setGeneralResponseAttribute(request);
        }

        request.setAttribute(DateFrom, dateFromS);
        request.setAttribute(DateTo, dateToS);
        request.setAttribute(SelectGuide, selectGuideId);
        request.setAttribute(SelectAgent, selectAgentId);
        setGeneralResponseAttribute(request);

        return DaytourOtherCommission;
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

    public void setBookingOtherService(BookingOtherService bookingOtherService) {
        this.bookingOtherService = bookingOtherService;
    }

    public BookingOtherService getBookingOtherService() {
        return bookingOtherService;
    }
    
    private List<OtherBooking> buildeListBookingDaytourCommission(HttpServletRequest request) {
        String rows = request.getParameter("dayCommRows");
        log.info("rows = " + rows);

        if (!StringUtils.isNotEmpty(rows)) {
            return null;
        }

        UtilityFunction utilFunc = new UtilityFunction();

        int row = Integer.parseInt(rows);
        ArrayList<OtherBooking> updateBooking = new ArrayList<OtherBooking>();
        for (int i = 1; i <= row; i++) {
            String daytourBookingId = request.getParameter("daytourBookingId-" + i);
            String guideId = request.getParameter("selectGuide-" + i);
            String guideComm = request.getParameter("guideComm-" + i);
            String guideRemark = request.getParameter("guideRemark-" + i);
            String agentId = request.getParameter("AgentName-" + i);
            String agentComm = request.getParameter("agentComm-" + i);
            String agentRemark = request.getParameter("agentRemark-" + i);
            OtherBooking booking = new OtherBooking();
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

            Long guideCommission = null;
             
            if (StringUtils.isNotEmpty(guideComm)) {
                guideCommission = utilFunc.convertStringTolong(guideComm);
                booking.setGuideCommission(guideCommission);
            }
            booking.setRemarkGuideCommission(guideRemark);

            if (StringUtils.isNotEmpty(agentId)) {
                Agent agent = new Agent();
                agent.setId(agentId);
                booking.setAgent(agent);
            }

            if (StringUtils.isNotEmpty(agentComm)) {
                Long agentCommission = null;
                agentCommission = utilFunc.convertStringTolong(agentComm);
                booking.setAgentCommission(agentCommission);
            }
            booking.setRemarkAgentCommission(agentRemark);

            updateBooking.add(booking);
            log.info("DaytourBookingId = " + daytourBookingId);
        }
        return updateBooking;
    }
}
