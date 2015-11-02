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
public class HotelMonthlyReport {
    private JRDataSource hotelMonthlyReportDataSource;
    private JRDataSource hotelMonthlyDetailReportDataSource;
    private String subReportDir;
    private String frompage;
    private String topage;
    private String departmentpage;
    private String printby;
    private String systemdate;

    public String getSystemdate() {
        return systemdate;
    }

    public void setSystemdate(String systemdate) {
        this.systemdate = systemdate;
    }
    

    public String getPrintby() {
        return printby;
    }

    public void setPrintby(String printby) {
        this.printby = printby;
    }

    public JRDataSource getHotelMonthlyReportDataSource() {
        return hotelMonthlyReportDataSource;
    }

    public void setHotelMonthlyReportDataSource(JRDataSource hotelMonthlyReportDataSource) {
        this.hotelMonthlyReportDataSource = hotelMonthlyReportDataSource;
    }

    public JRDataSource getHotelMonthlyDetailReportDataSource() {
        return hotelMonthlyDetailReportDataSource;
    }

    public void setHotelMonthlyDetailReportDataSource(JRDataSource hotelMonthlyDetailReportDataSource) {
        this.hotelMonthlyDetailReportDataSource = hotelMonthlyDetailReportDataSource;
    }
    


    public String getSubReportDir() {
        return subReportDir;
    }

    public void setSubReportDir(String subReportDir) {
        this.subReportDir = subReportDir;
    }

    public String getFrompage() {
        return frompage;
    }

    public void setFrompage(String frompage) {
        this.frompage = frompage;
    }

    public String getTopage() {
        return topage;
    }

    public void setTopage(String topage) {
        this.topage = topage;
    }

    public String getDepartmentpage() {
        return departmentpage;
    }

    public void setDepartmentpage(String departmentpage) {
        this.departmentpage = departmentpage;
    }
    
}
