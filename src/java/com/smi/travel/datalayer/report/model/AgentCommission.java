/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.report.model;

import net.sf.jasperreports.engine.JRDataSource;

public class AgentCommission {
    private JRDataSource agentCommissionSummaryDataSource;
    private JRDataSource agentCommissionInfoDataSource;
    private String subReportDir;
    
    public AgentCommission(){
        this.agentCommissionSummaryDataSource = null;
        this.agentCommissionInfoDataSource = null;
    }
    
    public JRDataSource getAgentCommissionSummaryDataSource() {
        return agentCommissionSummaryDataSource;
    }

    public void setAgentCommissionSummaryDataSource(JRDataSource agentCommissionSummaryDataSource) {
        this.agentCommissionSummaryDataSource = agentCommissionSummaryDataSource;
    }

    public JRDataSource getAgentCommissionInfoDataSource() {
        return agentCommissionInfoDataSource;
    }

    public void setAgentCommissionInfoDataSource(JRDataSource agentCommissionInfoDataSource) {
        this.agentCommissionInfoDataSource = agentCommissionInfoDataSource;
    }
    
    public String getSubReportDir() {
        return subReportDir;
    }

    public void setSubReportDir(String subReportDir) {
        this.subReportDir = subReportDir;
    }
    
}
