/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.report.model;

import net.sf.jasperreports.engine.JRDataSource;

/**
 *
 * @author Jittima
 */
public class GuideCommissionInfo {
    private JRDataSource guideCommissionDataSource;
    private JRDataSource guideCommissionSummaryDataSource;
    private String subReportDir;
    private String datefrom;
    private String dateto;
    private String systemdate;
    private String user;

    public GuideCommissionInfo(){
        this.guideCommissionDataSource = null;
        this.guideCommissionSummaryDataSource = null;
    }
    
    
    public JRDataSource getGuideCommissionDataSource() {
        return guideCommissionDataSource;
    }

    public void setGuideCommissionDataSource(JRDataSource guideCommissionDataSource) {
        this.guideCommissionDataSource = guideCommissionDataSource;
    }

    public JRDataSource getGuideCommissionSummaryDataSource() {
        return guideCommissionSummaryDataSource;
    }

    public void setGuideCommissionSummaryDataSource(JRDataSource guideCommissionSummaryDataSource) {
        this.guideCommissionSummaryDataSource = guideCommissionSummaryDataSource;
    }

    public String getSubReportDir() {
        return subReportDir;
    }

    public void setSubReportDir(String subReportDir) {
        this.subReportDir = subReportDir;
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
    
}
