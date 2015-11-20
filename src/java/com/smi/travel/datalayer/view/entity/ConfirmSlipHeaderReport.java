/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.entity;

import net.sf.jasperreports.engine.JRDataSource;

/**
 *
 * @author Jittima
 */
public class ConfirmSlipHeaderReport {
    private String refno;
    private String user;
    private String systemdate;
    private String leader;
    private String address;
    private String telfax;
    private String bookstatus;
    private String agent;
    private String incharge;
    private String firstdept;
    private String packages;
    private String subReportDir;
    private JRDataSource confirmSlipFlightSubReportDataSource;
    private JRDataSource confirmSlipHotelSubReportDataSource;
    private JRDataSource confirmSlipDaytourSubReportDataSource;
    private JRDataSource confirmSlipOtherSubReportDataSource;
    private JRDataSource confirmSlipLandSubReportDataSource;
    private JRDataSource confirmSlipPassengerSubReportDataSource;

    public String getRefno() {
        return refno;
    }

    public void setRefno(String refno) {
        this.refno = refno;
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

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelfax() {
        return telfax;
    }

    public void setTelfax(String telfax) {
        this.telfax = telfax;
    }

    public String getBookstatus() {
        return bookstatus;
    }

    public void setBookstatus(String bookstatus) {
        this.bookstatus = bookstatus;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getIncharge() {
        return incharge;
    }

    public void setIncharge(String incharge) {
        this.incharge = incharge;
    }

    public String getFirstdept() {
        return firstdept;
    }

    public void setFirstdept(String firstdept) {
        this.firstdept = firstdept;
    }

    public String getPackages() {
        return packages;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }

    public String getSubReportDir() {
        return subReportDir;
    }

    public void setSubReportDir(String subReportDir) {
        this.subReportDir = subReportDir;
    }

    public JRDataSource getConfirmSlipFlightSubReportDataSource() {
        return confirmSlipFlightSubReportDataSource;
    }

    public void setConfirmSlipFlightSubReportDataSource(JRDataSource confirmSlipFlightSubReportDataSource) {
        this.confirmSlipFlightSubReportDataSource = confirmSlipFlightSubReportDataSource;
    }

    public JRDataSource getConfirmSlipHotelSubReportDataSource() {
        return confirmSlipHotelSubReportDataSource;
    }

    public void setConfirmSlipHotelSubReportDataSource(JRDataSource confirmSlipHotelSubReportDataSource) {
        this.confirmSlipHotelSubReportDataSource = confirmSlipHotelSubReportDataSource;
    }

    public JRDataSource getConfirmSlipDaytourSubReportDataSource() {
        return confirmSlipDaytourSubReportDataSource;
    }

    public void setConfirmSlipDaytourSubReportDataSource(JRDataSource confirmSlipDaytourSubReportDataSource) {
        this.confirmSlipDaytourSubReportDataSource = confirmSlipDaytourSubReportDataSource;
    }

    public JRDataSource getConfirmSlipOtherSubReportDataSource() {
        return confirmSlipOtherSubReportDataSource;
    }

    public void setConfirmSlipOtherSubReportDataSource(JRDataSource confirmSlipOtherSubReportDataSource) {
        this.confirmSlipOtherSubReportDataSource = confirmSlipOtherSubReportDataSource;
    }

    public JRDataSource getConfirmSlipLandSubReportDataSource() {
        return confirmSlipLandSubReportDataSource;
    }

    public void setConfirmSlipLandSubReportDataSource(JRDataSource confirmSlipLandSubReportDataSource) {
        this.confirmSlipLandSubReportDataSource = confirmSlipLandSubReportDataSource;
    }

    public JRDataSource getConfirmSlipPassengerSubReportDataSource() {
        return confirmSlipPassengerSubReportDataSource;
    }

    public void setConfirmSlipPassengerSubReportDataSource(JRDataSource confirmSlipPassengerSubReportDataSource) {
        this.confirmSlipPassengerSubReportDataSource = confirmSlipPassengerSubReportDataSource;
    }
    
}
