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
public class TicketSummaryList {
    private String systemdate;
    private String username;
    private String datefrom;
    private String startdate;
    private String enddate;
    private String from;
    private String type;
    private JRDataSource ticketSummaryDataSource;
    private JRDataSource ticketSummaryAirlineDataSource;
    private String subReportDir;

    public String getSystemdate() {
        return systemdate;
    }

    public void setSystemdate(String systemdate) {
        this.systemdate = systemdate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDatefrom() {
        return datefrom;
    }

    public void setDatefrom(String datefrom) {
        this.datefrom = datefrom;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JRDataSource getTicketSummaryDataSource() {
        return ticketSummaryDataSource;
    }

    public void setTicketSummaryDataSource(JRDataSource ticketSummaryDataSource) {
        this.ticketSummaryDataSource = ticketSummaryDataSource;
    }

    public JRDataSource getTicketSummaryAirlineDataSource() {
        return ticketSummaryAirlineDataSource;
    }

    public void setTicketSummaryAirlineDataSource(JRDataSource ticketSummaryAirlineDataSource) {
        this.ticketSummaryAirlineDataSource = ticketSummaryAirlineDataSource;
    }

    public String getSubReportDir() {
        return subReportDir;
    }

    public void setSubReportDir(String subReportDir) {
        this.subReportDir = subReportDir;
    }
    
}
