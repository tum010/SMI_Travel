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
    
}
