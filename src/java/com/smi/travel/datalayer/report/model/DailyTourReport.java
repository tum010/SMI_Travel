/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.report.model;

import net.sf.jasperreports.engine.JRDataSource;
/**
 *
 * @author chonnasith
 */
public class DailyTourReport {
    private JRDataSource dailyTourListReportDataSource;
    private JRDataSource dailyTourDetailReportDataSource;
    private String subReportDir;
    private String fromto;
    private String systemdate;
    private String user;
    
    public DailyTourReport(){
        this.dailyTourListReportDataSource = null;
        this.dailyTourDetailReportDataSource = null;
    }

    public JRDataSource getDailyTourListReportDataSource() {
        return dailyTourListReportDataSource;
    }

    public void setDailyTourListReportDataSource(JRDataSource dailyTourListReportDataSource) {
        this.dailyTourListReportDataSource = dailyTourListReportDataSource;
    }

    public JRDataSource getDailyTourDetailReportDataSource() {
        return dailyTourDetailReportDataSource;
    }

    public void setDailyTourDetailReportDataSource(JRDataSource dailyTourDetailReportDataSource) {
        this.dailyTourDetailReportDataSource = dailyTourDetailReportDataSource;
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
