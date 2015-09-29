/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.Agent;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface AgentDao {
    public List<Agent> getListAgent(Agent agent,int option);
    public Agent getAgentByName(String name); 
    public int insertAgent(Agent agent);
    public int updateAgent(Agent agent);
    public int DeleteAgent(Agent agent);
    public Agent getAgentFromID(String agentID);
    public List<Agent> getListAgent();
    public List<Agent> getListAgentForBookingDetail();
}
