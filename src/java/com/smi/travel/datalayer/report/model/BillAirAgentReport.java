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
public class BillAirAgentReport {
    private JRDataSource billAirAgentSummaryDataSource;
    private JRDataSource billAirAgentDetailDataSource;
    private JRDataSource billAirAgentRefundDataSource;
    private String subReportDir;
    
    public BillAirAgentReport(){
        this.billAirAgentSummaryDataSource = null;
        this.billAirAgentDetailDataSource = null;
        this.billAirAgentRefundDataSource = null;
    }

    public JRDataSource getBillAirAgentSummaryDataSource() {
        return billAirAgentSummaryDataSource;
    }

    public void setBillAirAgentSummaryDataSource(JRDataSource billAirAgentSummaryDataSource) {
        this.billAirAgentSummaryDataSource = billAirAgentSummaryDataSource;
    }

    public JRDataSource getBillAirAgentDetailDataSource() {
        return billAirAgentDetailDataSource;
    }

    public void setBillAirAgentDetailDataSource(JRDataSource billAirAgentDetailDataSource) {
        this.billAirAgentDetailDataSource = billAirAgentDetailDataSource;
    }

    public JRDataSource getBillAirAgentRefundDataSource() {
        return billAirAgentRefundDataSource;
    }

    public void setBillAirAgentRefundDataSource(JRDataSource billAirAgentRefundDataSource) {
        this.billAirAgentRefundDataSource = billAirAgentRefundDataSource;
    }

    public String getSubReportDir() {
        return subReportDir;
    }

    public void setSubReportDir(String subReportDir) {
        this.subReportDir = subReportDir;
    }
    
}
