/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.report.model;

import net.sf.jasperreports.engine.JRDataSource;

/**
 *
 * @author Kanokporn
 */
public class OtherAgentCommission {
    private JRDataSource otherAgentCommissionSummaryDataSource;
    private JRDataSource otherAgentCommissionInfoDataSource;
    private String subReportDir;

    public JRDataSource getOtherAgentCommissionSummaryDataSource() {
        return otherAgentCommissionSummaryDataSource;
    }

    public void setOtherAgentCommissionSummaryDataSource(JRDataSource otherAgentCommissionSummaryDataSource) {
        this.otherAgentCommissionSummaryDataSource = otherAgentCommissionSummaryDataSource;
    }

    public JRDataSource getOtherAgentCommissionInfoDataSource() {
        return otherAgentCommissionInfoDataSource;
    }

    public void setOtherAgentCommissionInfoDataSource(JRDataSource otherAgentCommissionInfoDataSource) {
        this.otherAgentCommissionInfoDataSource = otherAgentCommissionInfoDataSource;
    }

    public String getSubReportDir() {
        return subReportDir;
    }

    public void setSubReportDir(String subReportDir) {
        this.subReportDir = subReportDir;
    }
    
}
