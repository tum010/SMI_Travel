/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.masterdata.controller;

import com.smi.travel.datalayer.entity.Agent;
import com.smi.travel.datalayer.entity.AgentComission;
import com.smi.travel.datalayer.entity.AgentTourComission;
import com.smi.travel.datalayer.entity.Daytour;
import com.smi.travel.datalayer.service.MTourCommissionService;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Admin
 */
public class MCommissionController extends SMITravelController {

    private static final Logger log = Logger.getLogger(MDaytourController.class.getName());
    private static final ModelAndView Commission = new ModelAndView("MCommission");
    private static final ModelAndView Commission_REFRESH = new ModelAndView(new RedirectView("MCommission", true));
    private static final String AgentTourCommissions = "AgentTourCommissions";
    private static final String DeleteMCommission = "DeleteMCommission";
    private MTourCommissionService  mtourCommissionService;

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        UtilityFunction util = new UtilityFunction();
        String action = request.getParameter("action");
        String commissionId = request.getParameter("commissionId");
        String agentcode = util.StringUtilReplaceChar(request.getParameter("AgentCodeSearch"));
        String agentname = util.StringUtilReplaceChar(request.getParameter("AgentNameSearch"));
        String tourcode = util.StringUtilReplaceChar(request.getParameter("TourCodeSearch"));
        String tourname = util.StringUtilReplaceChar(request.getParameter("TourNameSearch"));
        
        AgentTourComission agenttourcommission = new AgentTourComission();
        AgentComission agentcommission = new AgentComission();
        Agent agent = new Agent();
        agent.setCode(agentcode);
        agent.setName(agentname);
        agentcommission.setId(commissionId);
        agentcommission.setAgent(agent);
        
        Daytour daytour = new Daytour();
        daytour.setCode(tourcode);
        daytour.setName(tourname);
        
        agenttourcommission.setAgentComission(agentcommission);
        agenttourcommission.setDaytour(daytour);
        
       // AgentComission agentcom = mtourCommissionService.getTourCommissionFromID("1");
       // System.out.println(agentcom.getAgent().getId());
        log.info("action = " + action +", agentcode = "+agentcode+", agentname="+agentname+", tourcode="+tourcode+",tourname="+tourname+";");
        if ("search".equalsIgnoreCase(action)) {
            List<AgentTourComission> list = mtourCommissionService.SearchAgentTourComission(agenttourcommission, 2);
            request.setAttribute(AgentTourCommissions, list);
        } else if ("delete".equalsIgnoreCase(action)) {
            System.out.println("commissionId = "+commissionId);
            String transactionDelete  =  mtourCommissionService.DeleteTourComission(agentcommission);
            request.setAttribute(DeleteMCommission,"delete: "+ transactionDelete);
        }
        
        request.setAttribute("agentcode", agentcode);
        request.setAttribute("agentname", agentname);
        request.setAttribute("tourcode", tourcode);
        request.setAttribute("tourname", tourname);

        return Commission;
    }

    public MTourCommissionService getMtourCommissionService() {
        return mtourCommissionService;
    }

    public void setMtourCommissionService(MTourCommissionService mtourCommissionService) {
        this.mtourCommissionService = mtourCommissionService;
    }

   
    
}
