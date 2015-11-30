package com.smi.travel.datalayer.view.entity;


import net.sf.jasperreports.engine.JRDataSource;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jittima
 */
public class OutboundStaffSummaryReport {
    private String fromto;
    private String user;
    private String systemdate;
    private String subReportDir;
    private JRDataSource outboundStaffSummaryListReportDataSource;
    private JRDataSource outboundStaffSummaryDetailReportDataSource;

    public String getFromto() {
        return fromto;
    }

    public void setFromto(String fromto) {
        this.fromto = fromto;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSystemdate() {
        return systemdate;
    }

    public void setSystemdate(String systemdate) {
        this.systemdate = systemdate;
    }

    public String getSubReportDir() {
        return subReportDir;
    }

    public void setSubReportDir(String subReportDir) {
        this.subReportDir = subReportDir;
    }

    public JRDataSource getOutboundStaffSummaryListReportDataSource() {
        return outboundStaffSummaryListReportDataSource;
    }

    public void setOutboundStaffSummaryListReportDataSource(JRDataSource outboundStaffSummaryListReportDataSource) {
        this.outboundStaffSummaryListReportDataSource = outboundStaffSummaryListReportDataSource;
    }

    public JRDataSource getOutboundStaffSummaryDetailReportDataSource() {
        return outboundStaffSummaryDetailReportDataSource;
    }

    public void setOutboundStaffSummaryDetailReportDataSource(JRDataSource outboundStaffSummaryDetailReportDataSource) {
        this.outboundStaffSummaryDetailReportDataSource = outboundStaffSummaryDetailReportDataSource;
    }
    
    
    
}
