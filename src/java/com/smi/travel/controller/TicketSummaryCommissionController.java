/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.Agent;
import com.smi.travel.datalayer.entity.MAccterm;
import com.smi.travel.datalayer.entity.MAirline;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.TicketSummaryCommissionService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.SMITravelController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Kanokporn
 */
public class TicketSummaryCommissionController extends SMITravelController {
    private static final ModelAndView TicketSummaryCommission = new ModelAndView("TicketSummaryCommission");
    private static final ModelAndView TicketSummaryCommission_REFRESH = new ModelAndView(new RedirectView("TicketSummaryCommission.smi", true));
    private static final String AIRLINECODELIST = "airlineCodeList";
    private static final String USERLIST = "userList";
    private static final String TERMPAYLIST = "termPayList";
    private static final String AGENTLIST = "listAgent";
    private UtilityService utilityService;
    private  TicketSummaryCommissionService ticketSummaryCommissionService;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        // List Air
        List<MAirline> mAirlines = utilityService.getListMAirlineCode();
        if(mAirlines != null){
            request.setAttribute(AIRLINECODELIST,mAirlines);
        }else{
            request.setAttribute(AIRLINECODELIST,null);
        }
        // List Sale by
        List<SystemUser> userList = utilityService.getUserList();
        if(userList != null){
            request.setAttribute(USERLIST,userList);
        }else{
            request.setAttribute(USERLIST,null);
        }
        
         //List Term pay
        List<MAccterm> mAcctermList = utilityService.getListMAccterm();
        if(mAcctermList != null){
            request.setAttribute(TERMPAYLIST,mAcctermList);
        }else{
            request.setAttribute(TERMPAYLIST,null);
        }
        
        //List Agent
        List<Agent> listAgent = utilityService.getListAgent();
        if(listAgent != null){
            request.setAttribute(AGENTLIST,listAgent);
        }else{
            request.setAttribute(AGENTLIST,null);
        }
        
        String invoicefromdate = request.getParameter("invoiceFromDate");
        String invoicetodate = request.getParameter("invoiceToDate");
        String issuefromdate = request.getParameter("issueFrom");
        String issuetodate = request.getParameter("issueTo");
        String agentcomfromdate = request.getParameter("agentcomFrom");
        String agentcomtodate = request.getParameter("agentcomTo");
        String ticketcomfromdate = request.getParameter("ticketcomFrom");
        String ticketcomtodate = request.getParameter("ticketcomTo");
        String overfromdate = request.getParameter("overFrom");
        String overtodate = request.getParameter("overTo");
        String littlefromdate = request.getParameter("littleFrom");
        String littletodate = request.getParameter("littleTo");
        String agemtcomreceivefromdate = request.getParameter("agentcomreceiveFrom");
        String agemtcomreceivetodate = request.getParameter("agentcomreceiveTo");
        String comrefundfromdate = request.getParameter("comrefundFrom");
        String comrefundtodate = request.getParameter("comrefundTo");
        String addpayfromdate = request.getParameter("addpayFrom");
        String addpaytodate = request.getParameter("addpayTo");
        String decreasepayfromdate = request.getParameter("decreasepayFrom");
        String decreasepaytodate = request.getParameter("decreasepayTo");
        String typeRouting = request.getParameter("typeRouting");
        String routingDetail = request.getParameter("routingDetail");
        String airlineCode = request.getParameter("airlineCode");
        String agentCode = request.getParameter("agentCode");
        String agentName = request.getParameter("agentName");
        String ticketno = request.getParameter("ticketno");
        String department = request.getParameter("department");
        String salebyUser = request.getParameter("salebyUser");
        String salebyName = request.getParameter("salebyName");
        String termPay    = request.getParameter("termPay");     

        return TicketSummaryCommission;
    }

    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }

    public TicketSummaryCommissionService getTicketSummaryCommissionService() {
        return ticketSummaryCommissionService;
    }

    public void setTicketSummaryCommissionService(TicketSummaryCommissionService ticketSummaryCommissionService) {
        this.ticketSummaryCommissionService = ticketSummaryCommissionService;
    }
    
    
}
