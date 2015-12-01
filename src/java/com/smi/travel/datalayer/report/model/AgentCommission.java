/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.report.model;

import net.sf.jasperreports.engine.JRDataSource;

public class AgentCommission {
    private String systemdate;
    private String user;
    private String datefrom;
    private String dateto;
    private JRDataSource agentCommissionSummaryDataSource;
    private JRDataSource agentCommissionInfoDataSource;
    private String subReportDir;
    
    public AgentCommission(){
        this.agentCommissionSummaryDataSource = null;
        this.agentCommissionInfoDataSource = null;
    }

    public String getSystemdate() {
        return systemdate;
    }

    public void setSystemdate(String systemdate) {
        this.systemdate = systemdate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDatefrom() {
        return datefrom;
    }

    public void setDatefrom(String datefrom) {
        this.datefrom = datefrom;
    }

    public String getDateto() {
        return dateto;
    }

    public void setDateto(String dateto) {
        this.dateto = dateto;
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
