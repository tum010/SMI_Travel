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
public class BillAirAgentDetailReport {
    private String invoiceno;
    private String invoicedate;
    private String customer;
    private String ticketno;
    private String rounting;
    private BigDecimal saleprice;
    private BigDecimal net;
    private BigDecimal service;
    private BigDecimal vatamount;
    private BigDecimal amountair;
    private BigDecimal compay;
    private BigDecimal vatreceive;
    private BigDecimal receive;
    
    // Header 
    private String agenthead; 
    private String issuedatehead; 
    private String invoicedatehead; 
    private String paymenttypehead; 
    private String printby;

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

    public String getInvoiceno() {
        return invoiceno;
    }

    public void setInvoiceno(String invoiceno) {
        this.invoiceno = invoiceno;
    }

    public String getInvoicedate() {
        return invoicedate;
    }

    public void setInvoicedate(String invoicedate) {
        this.invoicedate = invoicedate;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getTicketno() {
        return ticketno;
    }

    public void setTicketno(String ticketno) {
        this.ticketno = ticketno;
    }

    public String getRounting() {
        return rounting;
    }

    public void setRounting(String rounting) {
        this.rounting = rounting;
    }

    public BigDecimal getSaleprice() {
        return saleprice;
    }

    public void setSaleprice(BigDecimal saleprice) {
        this.saleprice = saleprice;
    }

    public BigDecimal getNet() {
        return net;
    }

    public void setNet(BigDecimal net) {
        this.net = net;
    }

    public BigDecimal getService() {
        return service;
    }

    public void setService(BigDecimal service) {
        this.service = service;
    }

    public BigDecimal getVatamount() {
        return vatamount;
    }

    public void setVatamount(BigDecimal vatamount) {
        this.vatamount = vatamount;
    }

    public BigDecimal getAmountair() {
        return amountair;
    }

    public void setAmountair(BigDecimal amountair) {
        this.amountair = amountair;
    }

    public BigDecimal getCompay() {
        return compay;
    }

    public void setCompay(BigDecimal compay) {
        this.compay = compay;
    }

    public BigDecimal getVatreceive() {
        return vatreceive;
    }

    public void setVatreceive(BigDecimal vatreceive) {
        this.vatreceive = vatreceive;
    }

    public BigDecimal getReceive() {
        return receive;
    }

    public void setReceive(BigDecimal receive) {
        this.receive = receive;
    }
    
    
}
