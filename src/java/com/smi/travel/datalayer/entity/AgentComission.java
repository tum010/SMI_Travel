package com.smi.travel.datalayer.entity;
// Generated Mar 18, 2015 10:03:25 AM by Hibernate Tools 3.6.0


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * AgentComission generated by hbm2java
 */
public class AgentComission {


     private String id;
     private Agent agent;
     private Integer isPay;
     private List agentTourComissions;

    public AgentComission() {
    }

	
    public AgentComission(Agent agent) {
        this.agent = agent;
    }
    public AgentComission(Agent agent, Integer isPay, List agentTourComissions) {
       this.agent = agent;
       this.isPay = isPay;
       this.agentTourComissions = agentTourComissions;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public Agent getAgent() {
        return this.agent;
    }
    
    public void setAgent(Agent agent) {
        this.agent = agent;
    }
    public Integer getIsPay() {
        return this.isPay;
    }
    
    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }

    public List getAgentTourComissions() {
        return agentTourComissions;
    }

    public void setAgentTourComissions(List agentTourComissions) {
        this.agentTourComissions = agentTourComissions;
    }
    
    




}


