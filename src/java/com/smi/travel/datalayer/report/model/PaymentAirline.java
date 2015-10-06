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
public class PaymentAirline {
    private JRDataSource paymentAirlineReportDataSource;
    private JRDataSource paymentAirlineListReportDataSource;
    private JRDataSource paymentAirlineRefundReportDataSource;
    private String subReportDir;
    
    public PaymentAirline(){
        this.paymentAirlineReportDataSource = null;
        this.paymentAirlineListReportDataSource = null;
        this.paymentAirlineRefundReportDataSource = null;
    }

    public JRDataSource getPaymentAirlineReportDataSource() {
        return paymentAirlineReportDataSource;
    }

    public void setPaymentAirlineReportDataSource(JRDataSource paymentAirlineReportDataSource) {
        this.paymentAirlineReportDataSource = paymentAirlineReportDataSource;
    }

    public JRDataSource getPaymentAirlineListReportDataSource() {
        return paymentAirlineListReportDataSource;
    }

    public void setPaymentAirlineListReportDataSource(JRDataSource paymentAirlineListReportDataSource) {
        this.paymentAirlineListReportDataSource = paymentAirlineListReportDataSource;
    }

    public JRDataSource getPaymentAirlineRefundReportDataSource() {
        return paymentAirlineRefundReportDataSource;
    }

    public void setPaymentAirlineRefundReportDataSource(JRDataSource paymentAirlineRefundReportDataSource) {
        this.paymentAirlineRefundReportDataSource = paymentAirlineRefundReportDataSource;
    }

    public String getSubReportDir() {
        return subReportDir;
    }

    public void setSubReportDir(String subReportDir) {
        this.subReportDir = subReportDir;
    }


    
}
