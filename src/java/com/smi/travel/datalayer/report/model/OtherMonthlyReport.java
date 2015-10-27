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
public class OtherMonthlyReport {
    private JRDataSource otherMonthlyListReportDataSource;
    private JRDataSource otherMonthlyDetailReportDataSource;
    private String subReportDir;
    private String fromto;
    private String systemdate;
    private String user;

    public OtherMonthlyReport(){
        this.otherMonthlyListReportDataSource = null;
        this.otherMonthlyDetailReportDataSource = null;
    }
    
    public JRDataSource getOtherMonthlyListReportDataSource() {
        return otherMonthlyListReportDataSource;
    }

    public void setOtherMonthlyListReportDataSource(JRDataSource otherMonthlyListReportDataSource) {
        this.otherMonthlyListReportDataSource = otherMonthlyListReportDataSource;
    }

    public JRDataSource getOtherMonthlyDetailReportDataSource() {
        return otherMonthlyDetailReportDataSource;
    }

    public void setOtherMonthlyDetailReportDataSource(JRDataSource otherMonthlyDetailReportDataSource) {
        this.otherMonthlyDetailReportDataSource = otherMonthlyDetailReportDataSource;
    }

    public String getSubReportDir() {
        return subReportDir;
    }

    public void setSubReportDir(String subReportDir) {
        this.subReportDir = subReportDir;
    }

    public String getFromto() {
        return fromto;
    }

    public void setFromto(String fromto) {
        this.fromto = fromto;
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
