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
public class BookingHeaderSummaryView {
    private String systemdate;
    private String refno;
    private String agent;
    private String leader;
    private String pax;
    private String sale;
    private String billto;
    private String termpay;
    private String payby;
    private String address;
    private String subReportDir;
    private JRDataSource bookingSummaryFlightSubReportDataSource;
    private JRDataSource bookingSummaryHotelSubReportDataSource;
    private JRDataSource bookingSummaryOtherSubReportDataSource;
    private JRDataSource bookingSummaryLandSubReportDataSource;
    private JRDataSource bookingSummaryPassengerSubReportDataSource;

    public String getSystemdate() {
        return systemdate;
    }

    public void setSystemdate(String systemdate) {
        this.systemdate = systemdate;
    }

    public String getRefno() {
        return refno;
    }

    public void setRefno(String refno) {
        this.refno = refno;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getPax() {
        return pax;
    }

    public void setPax(String pax) {
        this.pax = pax;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public String getBillto() {
        return billto;
    }

    public void setBillto(String billto) {
        this.billto = billto;
    }

    public String getTermpay() {
        return termpay;
    }

    public void setTermpay(String termpay) {
        this.termpay = termpay;
    }

    public String getPayby() {
        return payby;
    }

    public void setPayby(String payby) {
        this.payby = payby;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSubReportDir() {
        return subReportDir;
    }

    public void setSubReportDir(String subReportDir) {
        this.subReportDir = subReportDir;
    }

    public JRDataSource getBookingSummaryFlightSubReportDataSource() {
        return bookingSummaryFlightSubReportDataSource;
    }

    public void setBookingSummaryFlightSubReportDataSource(JRDataSource bookingSummaryFlightSubReportDataSource) {
        this.bookingSummaryFlightSubReportDataSource = bookingSummaryFlightSubReportDataSource;
    }

    public JRDataSource getBookingSummaryHotelSubReportDataSource() {
        return bookingSummaryHotelSubReportDataSource;
    }

    public void setBookingSummaryHotelSubReportDataSource(JRDataSource bookingSummaryHotelSubReportDataSource) {
        this.bookingSummaryHotelSubReportDataSource = bookingSummaryHotelSubReportDataSource;
    }

    public JRDataSource getBookingSummaryOtherSubReportDataSource() {
        return bookingSummaryOtherSubReportDataSource;
    }

    public void setBookingSummaryOtherSubReportDataSource(JRDataSource bookingSummaryOtherSubReportDataSource) {
        this.bookingSummaryOtherSubReportDataSource = bookingSummaryOtherSubReportDataSource;
    }

    public JRDataSource getBookingSummaryLandSubReportDataSource() {
        return bookingSummaryLandSubReportDataSource;
    }

    public void setBookingSummaryLandSubReportDataSource(JRDataSource bookingSummaryLandSubReportDataSource) {
        this.bookingSummaryLandSubReportDataSource = bookingSummaryLandSubReportDataSource;
    }

    public JRDataSource getBookingSummaryPassengerSubReportDataSource() {
        return bookingSummaryPassengerSubReportDataSource;
    }

    public void setBookingSummaryPassengerSubReportDataSource(JRDataSource bookingSummaryPassengerSubReportDataSource) {
        this.bookingSummaryPassengerSubReportDataSource = bookingSummaryPassengerSubReportDataSource;
    }

}
