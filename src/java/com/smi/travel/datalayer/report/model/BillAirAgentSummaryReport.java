/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.report.model;

import java.math.BigDecimal;

/**
 *
 * @author Kanokporn
 */
public class BillAirAgentSummaryReport {
    private BigDecimal totalsaleprice;
    private BigDecimal totalcomrefundreceive;
    private BigDecimal totalpayment;
    private BigDecimal compay;
    private BigDecimal comreceive;
    private BigDecimal totalcom;
    private BigDecimal vatpay;
    private BigDecimal vatreceive;
    private BigDecimal payrefundamount;
    private BigDecimal balancepayment;
    private BigDecimal amountairsale;
    private BigDecimal midvalue;
    private BigDecimal compaydivide;
    private BigDecimal comreceivedivide;
    private BigDecimal totalcompay;
    private BigDecimal vatcompay;
    private BigDecimal checkresult;
    private BigDecimal withholdingtax;

    // Header 
    private String agenthead; 
    private String issuedatehead; 
    private String invoicedatehead; 
    private String paymenttypehead; 
    private String printby;

    public BigDecimal getTotalsaleprice() {
        return totalsaleprice;
    }

    public void setTotalsaleprice(BigDecimal totalsaleprice) {
        this.totalsaleprice = totalsaleprice;
    }

    public BigDecimal getTotalcomrefundreceive() {
        return totalcomrefundreceive;
    }

    public void setTotalcomrefundreceive(BigDecimal totalcomrefundreceive) {
        this.totalcomrefundreceive = totalcomrefundreceive;
    }

    public BigDecimal getTotalpayment() {
        return totalpayment;
    }

    public void setTotalpayment(BigDecimal totalpayment) {
        this.totalpayment = totalpayment;
    }

    public BigDecimal getCompay() {
        return compay;
    }

    public void setCompay(BigDecimal compay) {
        this.compay = compay;
    }

    public BigDecimal getComreceive() {
        return comreceive;
    }

    public void setComreceive(BigDecimal comreceive) {
        this.comreceive = comreceive;
    }

    public BigDecimal getTotalcom() {
        return totalcom;
    }

    public void setTotalcom(BigDecimal totalcom) {
        this.totalcom = totalcom;
    }

    public BigDecimal getVatpay() {
        return vatpay;
    }

    public void setVatpay(BigDecimal vatpay) {
        this.vatpay = vatpay;
    }

    public BigDecimal getVatreceive() {
        return vatreceive;
    }

    public void setVatreceive(BigDecimal vatreceive) {
        this.vatreceive = vatreceive;
    }

    public BigDecimal getPayrefundamount() {
        return payrefundamount;
    }

    public void setPayrefundamount(BigDecimal payrefundamount) {
        this.payrefundamount = payrefundamount;
    }

    public BigDecimal getBalancepayment() {
        return balancepayment;
    }

    public void setBalancepayment(BigDecimal balancepayment) {
        this.balancepayment = balancepayment;
    }

    public BigDecimal getAmountairsale() {
        return amountairsale;
    }

    public void setAmountairsale(BigDecimal amountairsale) {
        this.amountairsale = amountairsale;
    }

    public BigDecimal getMidvalue() {
        return midvalue;
    }

    public void setMidvalue(BigDecimal midvalue) {
        this.midvalue = midvalue;
    }

    public BigDecimal getCompaydivide() {
        return compaydivide;
    }

    public void setCompaydivide(BigDecimal compaydivide) {
        this.compaydivide = compaydivide;
    }

    public BigDecimal getComreceivedivide() {
        return comreceivedivide;
    }

    public void setComreceivedivide(BigDecimal comreceivedivide) {
        this.comreceivedivide = comreceivedivide;
    }

    public BigDecimal getTotalcompay() {
        return totalcompay;
    }

    public void setTotalcompay(BigDecimal totalcompay) {
        this.totalcompay = totalcompay;
    }

    public BigDecimal getVatcompay() {
        return vatcompay;
    }

    public void setVatcompay(BigDecimal vatcompay) {
        this.vatcompay = vatcompay;
    }

    public BigDecimal getCheckresult() {
        return checkresult;
    }

    public void setCheckresult(BigDecimal checkresult) {
        this.checkresult = checkresult;
    }

    public BigDecimal getWithholdingtax() {
        return withholdingtax;
    }

    public void setWithholdingtax(BigDecimal withholdingtax) {
        this.withholdingtax = withholdingtax;
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

    public String getPrintby() {
        return printby;
    }

    public void setPrintby(String printby) {
        this.printby = printby;
    }
    
}
