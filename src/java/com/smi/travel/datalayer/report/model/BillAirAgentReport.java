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
public class BillAirAgentReport {
    private String agenthead; 
    private String issuedatehead; 
    private String invoicedatehead; 
    private String paymenttypehead; 
    private String refundpaymentdate;
    private String printby;
    private String systemdate;
    private JRDataSource billAirAgentSummaryDataSource;
    private JRDataSource billAirAgentDetailDataSource;
    private JRDataSource billAirAgentRefundDataSource;
    private String subReportDir;
    
    public BillAirAgentReport(){
        this.billAirAgentSummaryDataSource = null;
        this.billAirAgentDetailDataSource = null;
        this.billAirAgentRefundDataSource = null;
    }

    public String getSystemdate() {
        return systemdate;
    }

    public void setSystemdate(String systemdate) {
        this.systemdate = systemdate;
    }

    public String getAgenthead() {
        return agenthead;
    }

    public void setAgenthead(String agenthead) {
        this.agenthead = agenthead;
    }

    public String getIssuedatehead() {
        return issuedatehead;
    }

    public void setIssuedatehead(String issuedatehead) {
        this.issuedatehead = issuedatehead;
    }

    public String getInvoicedatehead() {
        return invoicedatehead;
    }

    public void setInvoicedatehead(String invoicedatehead) {
        this.invoicedatehead = invoicedatehead;
    }

    public String getPaymenttypehead() {
        return paymenttypehead;
    }

    public void setPaymenttypehead(String paymenttypehead) {
        this.paymenttypehead = paymenttypehead;
    }

    public String getRefundpaymentdate() {
        return refundpaymentdate;
    }

    public void setRefundpaymentdate(String refundpaymentdate) {
        this.refundpaymentdate = refundpaymentdate;
    }

    public String getPrintby() {
        return printby;
    }

    public void setPrintby(String printby) {
        this.printby = printby;
    }

    public JRDataSource getBillAirAgentSummaryDataSource() {
        return billAirAgentSummaryDataSource;
    }

    public void setBillAirAgentSummaryDataSource(JRDataSource billAirAgentSummaryDataSource) {
        this.billAirAgentSummaryDataSource = billAirAgentSummaryDataSource;
    }

    public JRDataSource getBillAirAgentDetailDataSource() {
        return billAirAgentDetailDataSource;
    }

    public void setBillAirAgentDetailDataSource(JRDataSource billAirAgentDetailDataSource) {
        this.billAirAgentDetailDataSource = billAirAgentDetailDataSource;
    }

    public JRDataSource getBillAirAgentRefundDataSource() {
        return billAirAgentRefundDataSource;
    }

    public void setBillAirAgentRefundDataSource(JRDataSource billAirAgentRefundDataSource) {
        this.billAirAgentRefundDataSource = billAirAgentRefundDataSource;
    }

    public String getSubReportDir() {
        return subReportDir;
    }

    public void setSubReportDir(String subReportDir) {
        this.subReportDir = subReportDir;
    }
    
}
