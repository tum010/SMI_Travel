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
import com.smi.travel.datalayer.service.MDaytourService;
import com.smi.travel.datalayer.service.MTourCommissionService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Admin
 */
public class MCommissionDetailController extends SMITravelController {

    private static final Logger log = Logger.getLogger(MCommissionDetailController.class.getName());
    private static final ModelAndView MCommissionDetail = new ModelAndView("MCommissionDetail");
    private static final ModelAndView MCommission = new ModelAndView("MCommission");
    private static final ModelAndView MCommissionDetail_REFRESH = new ModelAndView(new RedirectView("MCommissionDetail.smi", true));
    private MTourCommissionService  mtourCommissionService;
    private UtilityFunction util;
    private UtilityService utilservice;
    private MDaytourService daytourservice;
    private static final String AgentList = "AgentList";
    private static final String TourList = "TourList";
    private static final String AgentCommissions = "AgentCommissions";
    private static final String AgentTourCommissions = "AgentTourCommissions";
    private static final String AgentComID = "AgentComID";
    private static final String AgentTours = "AgentTours";
    private static final String ResultSave = "ResultSave";
    private static final String COMMISSIONDELETE= "COMMISSIONDELETE";
    private static final String VALIDATE = "VALIDATE";
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        String action = request.getParameter("action");
        String actionDelete = request.getParameter("actionDelete");
        String counter = request.getParameter("counterCommission");
        String currentAgentCommissionId = StringUtils.isNoneEmpty(request.getParameter("agentCommissionId")) ? request.getParameter("agentCommissionId") : null;
        String agentId = request.getParameter("InputAgentId");
        String agentCode = request.getParameter("InputAgentCode");
        String agentName = request.getParameter("InputAgentName");
        String tourId = StringUtils.isNotEmpty(request.getParameter("InputTourId")) ? request.getParameter("InputTourId") : null;
        String tourCode = request.getParameter("InputTourCode");
        String tourName = request.getParameter("InputTourName");
        String checkPay = request.getParameter("CheckPay");
        String agentTourComId = request.getParameter("agentTourComId");
        String AgentCommissionID = request.getParameter("AgentComID");
        String operation = "";

        log.info("action=" + action + ",counter="+counter+"," 
                + "currentAgentCommissionId="+currentAgentCommissionId+","
                + "agentId="+agentId+",tourId="+tourId+",checkPay="+checkPay+"");
        log.info("agentCode="+agentCode+" , agentName="+agentName);
       
        Integer checkPayInt = 0;
        if(checkPay != null){
            checkPayInt = 1;
        }
        
        Agent agent = new Agent();
        agent.setId(agentId);
        agent.setCode(agentCode);
        agent.setName(agentName);
        
        Daytour daytour = new Daytour();
        daytour.setId(tourId);
        daytour.setCode(tourCode);
        daytour.setName(tourName);

        AgentComission agentcommission = new AgentComission();
        List<AgentTourComission> agentTourCommissions = new <AgentTourComission>ArrayList();
            if(StringUtils.isEmpty(currentAgentCommissionId)) {
                System.out.println("No id");
                operation = "insert";
                agentcommission = new AgentComission();
            } else {
                System.out.println("Has id");
                operation = "update";
                agentTourCommissions = mtourCommissionService.getTourCommissionFromID(currentAgentCommissionId, tourId);
                
                if(agentTourCommissions != null){
                  if(!agentTourCommissions.isEmpty()) {
                    System.out.println("agentTourCommissions = "+agentTourCommissions);
                    agentcommission = agentTourCommissions.get(0).getAgentComission();
                    daytour = agentTourCommissions.get(0).getDaytour();
                    System.out.println("agentcommission = "+agentcommission.getId());
                  }  
                }
                
            }

        if ("new".equalsIgnoreCase(action)) {
            List<Agent> listAgent = utilservice.getListAgent();
            List<Daytour> listTour = daytourservice.searchTourList(daytour, 2);
            request.setAttribute(AgentList, listAgent);
            request.setAttribute(TourList, listTour);
            request.setAttribute(AgentTourCommissions, agentTourCommissions);
            request.setAttribute(AgentCommissions, agentcommission);
            request.setAttribute(AgentTours, daytour);
            return MCommissionDetail;
        }else if("edit".equalsIgnoreCase(action)) {
            
        }else if("save".equalsIgnoreCase(action)){
            System.out.println("1111");
            AgentTourComission agenttourcommission = new AgentTourComission();
            agentcommission.setAgent(agent);
            agentcommission.setIsPay(checkPayInt);
            
            setAgentTourCommissionRows(request, counter, agentcommission, daytour);
            
            agenttourcommission.setAgentComission(agentcommission);
            agenttourcommission.setDaytour(daytour);
            String validateValue = mtourCommissionService.ValidateTourCommission(agenttourcommission, operation);
            System.out.println("validateValue :"+validateValue);
            if(StringUtils.isNotEmpty(validateValue)){
                request.setAttribute(VALIDATE, validateValue);
            }else{
                if (StringUtils.isEmpty(currentAgentCommissionId)) {
                    System.out.println("2222");
                    String saveTransaction = mtourCommissionService.SaveTourCommission(agentcommission);
                    request.setAttribute(ResultSave,"save : "+ saveTransaction);
                    List<AgentTourComission> list = mtourCommissionService.SearchAgentTourComission(agenttourcommission, 1);
                    request.setAttribute(AgentTourCommissions, list);
                    return MCommission; 
                }else{
                    System.out.println("3333");
                    String transactionUpdate = mtourCommissionService.SaveTourCommission(agentcommission);
                    //agentTourCommissions = mtourCommissionService.getTourCommissionFromID(currentAgentCommissionId, tourId);
                    if(transactionUpdate.equalsIgnoreCase("success")){
                        request.setAttribute(ResultSave,"update successful");
                        agentTourCommissions = mtourCommissionService.getTourCommissionFromID(currentAgentCommissionId, tourId);
                    }else{
                        request.setAttribute(ResultSave,"update unsuccessful");
                    }
                    
                    List<AgentTourComission> list = mtourCommissionService.SearchAgentTourComission(agenttourcommission, 1);
                    request.setAttribute(AgentTourCommissions, list);
                    return MCommission; 
//                    return new ModelAndView("redirect:MCommissionDetail.smi?agentCommissionId="+currentAgentCommissionId+"&AgentComID="+AgentCommissionID+"&InputTourId="+tourId+"&action=edit&result="+transactionUpdate+"");
                    //return new ModelAndView("redirect:MCommissionDetail.smi?agentCommissionId="+currentAgentCommissionId+"&AgentComID="+AgentCommissionID+"&InputTourId="+tourId+"&action=edit&result="+transactionUpdate+"");
                }
            }
            
        }
        
        if("delete".equalsIgnoreCase(actionDelete)){
            log.info("agentTourComId = "+agentTourComId);
            AgentTourComission agenttourcom = new AgentTourComission();
            agenttourcom.setId(agentTourComId);
            String transactionDelete = mtourCommissionService.DeleteComissionPrice(agenttourcom);           
            request.setAttribute(COMMISSIONDELETE,"delete "+transactionDelete);

        }
        
        Daytour daytours = new Daytour();
        List<Agent> listAgent = utilservice.getListAgent();
        List<Daytour> listTour = daytourservice.searchTourList(daytours, 2);
        
        request.setAttribute(AgentList, listAgent);
        request.setAttribute(TourList, listTour);
        request.setAttribute(AgentTourCommissions, agentTourCommissions);
        request.setAttribute(AgentCommissions, agentcommission);
        request.setAttribute(AgentTours, daytour);
        request.setAttribute(AgentComID,AgentCommissionID);
        request.setAttribute("agentCommissionId", currentAgentCommissionId);
        return MCommissionDetail;
        
    }
    
    private void setAgentTourCommissionRows(HttpServletRequest request, String agentTourComCounter, AgentComission agentcommission , Daytour daytour){
        util = new UtilityFunction();
        int agentTourComRows =  0;
        if(agentTourComCounter != null){
             agentTourComRows =  Integer.parseInt(agentTourComCounter);
        }
        if(agentTourComRows == 1){return;}
        
        for(int i = 0 ; i < agentTourComRows; i++){
            String id = request.getParameter("InputId-"+i);
            String from = request.getParameter("InputFrom-"+i);
            String to = request.getParameter("InputTo-"+i);
            String commission = util.StringUtilReplaceChar(request.getParameter("InputCommission-"+i));
            
            System.out.println("from"+i+" : "+from);
            System.out.println("to"+i+" : "+to);
            
            
            Double commissionDouble = null;
            if(StringUtils.isNotEmpty(commission)){
                commissionDouble = Double.parseDouble(commission);
                System.out.println("commissionDouble = "+commissionDouble);
            }
            
            Date dateFrom = util.convertStringToDateS(from);
            Date dateTo = util.convertStringToDateS(to);
            
            System.out.println("dateFrom"+i+" : "+dateFrom);
            System.out.println("dateTo"+i+" : "+dateTo);
            
            
            AgentTourComission agenttourcommission = getAgentTourCommission(id, agentcommission);
           
            if(agenttourcommission == null){
                agenttourcommission = new AgentTourComission();
            }
            if(daytour == null){
                daytour = new Daytour();
            }

            agenttourcommission.setAgentComission(agentcommission);
            agenttourcommission.setDaytour(daytour);
            agenttourcommission.setTo(dateTo);
            agenttourcommission.setFrom(dateFrom);
            agenttourcommission.setComission(commissionDouble);
//            agenttourcommission.setCreateBy();
//            agenttourcommission.setUpdateBy();
            if(agenttourcommission.getFrom() != null){ 
                System.out.println("getFrom1 ="+agenttourcommission.getFrom());
                if(agenttourcommission.getId() == null){
                    agentcommission.getAgentTourComissions().add(agenttourcommission);
                    agenttourcommission.setAgentComission(agentcommission);
                }
            }else{
                System.out.println("Commission of AgentTourCommission  is null  ,Not update DB this object " + i);
            }
            
        }
        
    }
    
    private AgentTourComission getAgentTourCommission(String agentTourComissionId, AgentComission agentcommission ){
        System.out.println("get AgentTourComission Begin");
        if(agentTourComissionId == null){return null;}
        if(agentcommission.getAgentTourComissions().isEmpty()){return null;}
        
        Iterator<AgentTourComission> iterator = agentcommission.getAgentTourComissions().iterator();
        while(iterator.hasNext()){
            AgentTourComission agenttourcommission =  iterator.next();
            
            if(agentTourComissionId.equalsIgnoreCase(agenttourcommission.getId())){
                return agenttourcommission;
            }
        }
        return null;
    }
    
    

    public MTourCommissionService getMtourCommissionService() {
        return mtourCommissionService;
    }

    public void setMtourCommissionService(MTourCommissionService mtourCommissionService) {
        this.mtourCommissionService = mtourCommissionService;
    }

    public UtilityService getUtilservice() {
        return utilservice;
    }

    public void setUtilservice(UtilityService utilservice) {
        this.utilservice = utilservice;
    }

    public MDaytourService getDaytourservice() {
        return daytourservice;
    }

    public void setDaytourservice(MDaytourService daytourservice) {
        this.daytourservice = daytourservice;
    }

    public UtilityFunction getUtil() {
        return util;
    }

    public void setUtil(UtilityFunction util) {
        this.util = util;
    }

    

    
}
