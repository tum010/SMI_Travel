/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.entity;

/**
 *
 * @author Jittima
 */
public class PaymentOutboundInvSummaryView {
    
    private String billdescid;
    private String refitemid;
    private String billtypeid;
    private String invno;
    private String invdate;
    private String detail;
    private String gross;
    private String amount;
    private String vat;    

    public String getBilldescid() {
        return billdescid;
    }

    public void setBilldescid(String billdescid) {
        this.billdescid = billdescid;
    }

    public String getRefitemid() {
        return refitemid;
    }

    public void setRefitemid(String refitemid) {
        this.refitemid = refitemid;
    }

    public String getInvno() {
        return invno;
    }

    public void setInvno(String invno) {
        this.invno = invno;
    }

    public String getInvdate() {
        return invdate;
    }

    public void setInvdate(String invdate) {
        this.invdate = invdate;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getGross() {
        return gross;
    }

    public void setGross(String gross) {
        this.gross = gross;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getBilltypeid() {
        return billtypeid;
    }

    public void setBilltypeid(String billtypeid) {
        this.billtypeid = billtypeid;
    }
    
}
