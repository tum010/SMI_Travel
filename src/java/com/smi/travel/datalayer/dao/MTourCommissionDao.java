/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.Agent;
import com.smi.travel.datalayer.entity.AgentComission;
import com.smi.travel.datalayer.entity.AgentTourComission;
import com.smi.travel.datalayer.entity.Daytour;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface MTourCommissionDao {
    public List<AgentTourComission> SearchComission(Agent AgentCom,Daytour daytour, int option);
    public List<AgentTourComission> getTourCommissionFromID(String ComID ,String TourID);
    public String insertTourCommission(AgentComission AgentCom);
    public String updateTourCommission(AgentComission AgentCom);
    public String DeleteAgentTourComission(AgentComission AgentCom);
    public String DeleteComissionPrice(AgentTourComission AgentPrice);
}
