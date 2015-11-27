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
public class BillAirAgentRefundReport {
    private String refundno;
    private String datereceive;
    private String passenger;
    private String air;
    private String docno;
    private String refno;
    private BigDecimal amountreceive;
    private BigDecimal refundchange;
    private BigDecimal amountpay;
    private BigDecimal commrac;
    private BigDecimal vat;
    
    // Header
    private String agenthead;
    private String refundpaymentdatehead;
    private String printby; 

    public String getRefundno() {
        return refundno;
    }

    public void setRefundno(String refundno) {
        this.refundno = refundno;
    }

    public String getDatereceive() {
        return datereceive;
    }

    public void setDatereceive(String datereceive) {
        this.datereceive = datereceive;
    }

    public String getPassenger() {
        return passenger;
    }

    public void setPassenger(String passenger) {
        this.passenger = passenger;
    }

    public String getAir() {
        return air;
    }

    public void setAir(String air) {
        this.air = air;
    }

    public String getDocno() {
        return docno;
    }

    public void setDocno(String docno) {
        this.docno = docno;
    }

    public String getRefno() {
        return refno;
    }

    public void setRefno(String refno) {
        this.refno = refno;
    }

    public BigDecimal getAmountreceive() {
        return amountreceive;
    }

    public void setAmountreceive(BigDecimal amountreceive) {
        this.amountreceive = amountreceive;
    }

    public BigDecimal getRefundchange() {
        return refundchange;
    }

    public void setRefundchange(BigDecimal refundchange) {
        this.refundchange = refundchange;
    }

    public BigDecimal getAmountpay() {
        return amountpay;
    }

    public void setAmountpay(BigDecimal amountpay) {
        this.amountpay = amountpay;
    }

    public BigDecimal getCommrac() {
        return commrac;
    }

    public void setCommrac(BigDecimal commrac) {
        this.commrac = commrac;
    }

    public BigDecimal getVat() {
        return vat;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    public String getAgenthead() {
        return agenthead;
    }

    public void setAgenthead(String agenthead) {
        this.agenthead = agenthead;
    }

    public String getRefundpaymentdatehead() {
        return refundpaymentdatehead;
    }

    public void setRefundpaymentdatehead(String refundpaymentdatehead) {
        this.refundpaymentdatehead = refundpaymentdatehead;
    }

    public String getPrintby() {
        return printby;
    }

    public void setPrintby(String printby) {
        this.printby = printby;
    }
    

}
