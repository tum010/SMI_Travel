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
public class PackageMonthlyReport {
    private JRDataSource packageMonthlyListReportDataSource;
    private JRDataSource packageMonthlyDetailReportDataSource;
    private String subReportDir;
    private String fromto;
    private String systemdate;
    private String user;
    
    public PackageMonthlyReport(){
        this.packageMonthlyListReportDataSource = null;
        this.packageMonthlyDetailReportDataSource = null;
    }

    public JRDataSource getPackageMonthlyListReportDataSource() {
        return packageMonthlyListReportDataSource;
    }

    public void setPackageMonthlyListReportDataSource(JRDataSource packageMonthlyListReportDataSource) {
        this.packageMonthlyListReportDataSource = packageMonthlyListReportDataSource;
    }

    public JRDataSource getPackageMonthlyDetailReportDataSource() {
        return packageMonthlyDetailReportDataSource;
    }

    public void setPackageMonthlyDetailReportDataSource(JRDataSource packageMonthlyDetailReportDataSource) {
        this.packageMonthlyDetailReportDataSource = packageMonthlyDetailReportDataSource;
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
