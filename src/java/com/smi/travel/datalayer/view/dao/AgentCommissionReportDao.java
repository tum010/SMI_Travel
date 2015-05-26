/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao;

import com.smi.travel.datalayer.report.model.AgentCommission;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface AgentCommissionReportDao {
    public List getAgentReportSummary(String datefrom,String dateto,String user,String agentid);
    public List getAgentReportInfo(String datefrom,String dateto,String user,String agentid);
    public AgentCommission getAgentCommissionReport(String datefrom,String dateto,String user,String agentid);
}
