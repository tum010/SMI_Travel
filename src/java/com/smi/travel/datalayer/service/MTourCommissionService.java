/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.MTourCommissionDao;
import com.smi.travel.datalayer.entity.Agent;
import com.smi.travel.datalayer.entity.AgentComission;
import com.smi.travel.datalayer.entity.AgentTourComission;
import com.smi.travel.datalayer.entity.Daytour;
import java.util.List;

/**
 *
 * @author Surachai
 */
public class MTourCommissionService {
    private MTourCommissionDao mTourCommissiondao;
    
    
    public List<AgentTourComission> SearchAgentTourComission(AgentTourComission AgentCom, int option){
        return mTourCommissiondao.SearchComission(AgentCom.getAgentComission().getAgent(), AgentCom.getDaytour(), option);
    }
    
    public String ValidateTourCommission(AgentTourComission AgentCom,String operation){
        String validate ="";
       // System.out.println("ID : "+AgentCom.getAgentComission().getId());
        List<AgentTourComission> agentList = mTourCommissiondao.SearchComission(AgentCom.getAgentComission().getAgent(), AgentCom.getDaytour(), 1);
       // System.out.println("ID : "+AgentCom.getAgentComission().getId());
        if(agentList != null){
            if("update".equalsIgnoreCase(operation)){
                if(!(agentList.get(0).getAgentComission().getId().equalsIgnoreCase(AgentCom.getAgentComission().getId()))){
                    validate = "Agent and tour commission already exist";
                }
            }else{
                 validate = "Agent and tour commission already exist";
            }
        }
        return validate;
    }
    
    public List<AgentTourComission> getTourCommissionFromID(String ComID ,String TourID) {
        return mTourCommissiondao.getTourCommissionFromID(ComID,TourID);
    }
    
    public String SaveTourCommission(AgentComission AgentCom){
        String result ="";
        if(AgentCom.getId() == null){
           
            result = mTourCommissiondao.insertTourCommission(AgentCom);
        }else{
            result = mTourCommissiondao.updateTourCommission(AgentCom);
        }
        return result;
    }
    
    public String DeleteTourComission(AgentComission AgentCom){
        
        return mTourCommissiondao.DeleteAgentTourComission(AgentCom);
    }
    
    public String DeleteComissionPrice(AgentTourComission AgentPrice){
        return mTourCommissiondao.DeleteComissionPrice(AgentPrice);
    }

    public MTourCommissionDao getmTourCommissiondao() {
        return mTourCommissiondao;
    }

    public void setmTourCommissiondao(MTourCommissionDao mTourCommissiondao) {
        this.mTourCommissiondao = mTourCommissiondao;
    }
    
    
}
