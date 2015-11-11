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
public class OtherGuideCommissionInfo {
    private String systemdate;
    private String user;
    private String datefrom;
    private String dateto;
    private JRDataSource otherGuideCommissionDataSource;
    private JRDataSource otherGuideCommissionSummaryDataSource;
    private String subReportDir;

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
    
    public OtherGuideCommissionInfo(){
        this.otherGuideCommissionDataSource = null;
        this.otherGuideCommissionSummaryDataSource = null;
    }

    public JRDataSource getOtherGuideCommissionDataSource() {
        return otherGuideCommissionDataSource;
    }

    public void setOtherGuideCommissionDataSource(JRDataSource otherGuideCommissionDataSource) {
        this.otherGuideCommissionDataSource = otherGuideCommissionDataSource;
    }

    public JRDataSource getOtherGuideCommissionSummaryDataSource() {
        return otherGuideCommissionSummaryDataSource;
    }

    public void setOtherGuideCommissionSummaryDataSource(JRDataSource otherGuideCommissionSummaryDataSource) {
        this.otherGuideCommissionSummaryDataSource = otherGuideCommissionSummaryDataSource;
    }

    public String getSubReportDir() {
        return subReportDir;
    }

    public void setSubReportDir(String subReportDir) {
        this.subReportDir = subReportDir;
    }
    
}
