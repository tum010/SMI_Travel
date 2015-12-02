/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.AgentDao;
import com.smi.travel.datalayer.dao.MListItemDao;
import com.smi.travel.datalayer.entity.Agent;
import com.smi.travel.datalayer.entity.MAccpay;
import com.smi.travel.datalayer.entity.MAccterm;
import com.smi.travel.datalayer.entity.MBranch;
import java.util.List;

/**
 *
 * @author Surachai
 */
public class AgentService {

    private AgentDao agentDao;
    private MListItemDao listItemDao;

    public List<Agent> getListAgent(Agent agent, int option) {
        
        return agentDao.getListAgent(agent, option);
    }
    
    public Agent getAgentFromID(String agentID){
        return agentDao.getAgentFromID(agentID);
    }
    
    public Agent getAgentFromCode(String code){
        return agentDao.getAgentFromCode(code);
    }
    
    public String validateAgent(Agent Vagent, String operation){
        String validate = "";
        Agent agent = new Agent();
        agent.setCode(Vagent.getCode());
        List<Agent> list = agentDao.getListAgent(agent, 1);
        if (list != null) {
            if ("update".equalsIgnoreCase(operation)) {
                if (!(list.get(0).getId().equalsIgnoreCase(Vagent.getId()))) {
                    validate = "agent code already exist";
                }
            } else {
                validate = "agent code already exist";
            }

        }
        agent.setName(Vagent.getName());
        agent.setCode(null);
        list = agentDao.getListAgent(agent, 1);
        if (list != null) {
            if ("update".equalsIgnoreCase(operation)) {
                if (!(list.get(0).getId().equalsIgnoreCase(Vagent.getId()))) {
                    validate = "agent name already exist";
                }
            } else {
                validate = "agent name already exist";
            }
        }
        return validate;
    }
    
     public List<MAccpay> getListMAccpay() {
         return listItemDao.getListMAccpay();
     }
     
     public List<MAccterm> getListMAccterm() {
        return listItemDao.getListMAccterm();
    }
     
     public List<MBranch> getListMBranch() {
        return listItemDao.getListMBranch();
    }

    public int insertAgent(Agent agent) {
        return agentDao.insertAgent(agent);
    }

    public int updateAgent(Agent agent) {
        return agentDao.updateAgent(agent);
    }

    public int DeleteAgent(Agent agent) {
        return agentDao.DeleteAgent(agent);
    }

    public AgentDao getAgentDao() {
        return agentDao;
    }

    public void setAgentDao(AgentDao agentDao) {
        this.agentDao = agentDao;
    }

    public MListItemDao getListItemDao() {
        return listItemDao;
    }

    public void setListItemDao(MListItemDao listItemDao) {
        this.listItemDao = listItemDao;
    }
    
    

}
