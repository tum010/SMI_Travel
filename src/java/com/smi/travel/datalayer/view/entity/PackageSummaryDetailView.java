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
public class PackageSummaryDetailView {
    // PackageMonthlyDetailReport
    
    private String packagedate; //date
    private String course; //code
    private String packagename; //name
    private String pax;
    private String net;
    private String sell;
    private String balance;
    private String refno;
    private String leadername;
    private String bookpax;
    private String grouptour;
    private String department;
    private String createdate;
    private String subReportDir;
    private String paxsum;
    private JRDataSource packageHotelSubReportDataSource;
    private JRDataSource packageOthersSubReportDataSource;
    private JRDataSource packageAirlineSubReportDataSource;
    private JRDataSource packageLandSubReportDataSource;

    public String getPackagedate() {
        return packagedate;
    }

    public void setPackagedate(String packagedate) {
        this.packagedate = packagedate;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getPackagename() {
        return packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    public String getPax() {
        return pax;
    }

    public void setPax(String pax) {
        this.pax = pax;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getRefno() {
        return refno;
    }

    public void setRefno(String refno) {
        this.refno = refno;
    }

    public String getLeadername() {
        return leadername;
    }

    public void setLeadername(String leadername) {
        this.leadername = leadername;
    }

    public String getBookpax() {
        return bookpax;
    }

    public void setBookpax(String bookpax) {
        this.bookpax = bookpax;
    }

    public String getGrouptour() {
        return grouptour;
    }

    public void setGrouptour(String grouptour) {
        this.grouptour = grouptour;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getSubReportDir() {
        return subReportDir;
    }

    public void setSubReportDir(String subReportDir) {
        this.subReportDir = subReportDir;
    }

    public JRDataSource getPackageHotelSubReportDataSource() {
        return packageHotelSubReportDataSource;
    }

    public void setPackageHotelSubReportDataSource(JRDataSource packageHotelSubReportDataSource) {
        this.packageHotelSubReportDataSource = packageHotelSubReportDataSource;
    }

    public JRDataSource getPackageOthersSubReportDataSource() {
        return packageOthersSubReportDataSource;
    }

    public void setPackageOthersSubReportDataSource(JRDataSource packageOthersSubReportDataSource) {
        this.packageOthersSubReportDataSource = packageOthersSubReportDataSource;
    }

    public JRDataSource getPackageAirlineSubReportDataSource() {
        return packageAirlineSubReportDataSource;
    }

    public void setPackageAirlineSubReportDataSource(JRDataSource packageAirlineSubReportDataSource) {
        this.packageAirlineSubReportDataSource = packageAirlineSubReportDataSource;
    }

    public JRDataSource getPackageLandSubReportDataSource() {
        return packageLandSubReportDataSource;
    }

    public void setPackageLandSubReportDataSource(JRDataSource packageLandSubReportDataSource) {
        this.packageLandSubReportDataSource = packageLandSubReportDataSource;
    }

    public String getPaxsum() {
        return paxsum;
    }

    public void setPaxsum(String paxsum) {
        this.paxsum = paxsum;
    }
}
