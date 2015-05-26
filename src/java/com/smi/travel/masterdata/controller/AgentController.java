package com.smi.travel.masterdata.controller;
import com.smi.travel.controller.*;
import com.smi.travel.datalayer.entity.Agent;
import com.smi.travel.datalayer.service.AgentService;
import com.smi.travel.master.controller.SMITravelController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class AgentController extends SMITravelController {
    private static final ModelAndView Agent = new ModelAndView("Agent");
    private static final String DATALIST = "agent_list";
    private static final String TransectionResult = "result";
    private AgentService agentservice;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        String AgentID = request.getParameter("agentID");
        int result = 1;
        Agent agent = new Agent();
        agent.setCode(code);
        agent.setName(name);
        agent.setId(AgentID);
        if ("search".equalsIgnoreCase(action)) {
            List<Agent> list = agentservice.getListAgent(agent, 2);
            request.setAttribute(DATALIST, list);
        } else if ("delete".equalsIgnoreCase(action)) {
            result = agentservice.DeleteAgent(agent);
            if (result == 1) {
                request.setAttribute(TransectionResult, "delete successful");
            } else {
                request.setAttribute(TransectionResult, "delete unsuccessful");
            }
        }
        
        request.setAttribute("agentCode", code);
        request.setAttribute("agentName", name);
        
        return Agent;
    }

    public AgentService getAgentservice() {
        return agentservice;
    }

    public void setAgentservice(AgentService agentservice) {
        this.agentservice = agentservice;
    }
    
    
}
