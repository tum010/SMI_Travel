/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.entity;

import java.math.BigDecimal;

/**
 *
 * @author Jittima
 */
public class CollectionNirvanaCashReceipt {
    private String receiptid;
    private String transcode;
    private String prefix;
    private String documentno;
    private BigDecimal discountamt;
    private BigDecimal allowanceamt;
    private BigDecimal whtamt;
    private BigDecimal vatamount;
    private BigDecimal clearArAmt;
    private String note;
    private BigDecimal basewhtamt;
    private String intrecno;
    private BigDecimal basevatamt;
    private String vatflag;
    private String vatid;
    
    public String getReceiptid() {
        return receiptid;
    }

    public void setReceiptid(String receiptid) {
        this.receiptid = receiptid;
    }

    public String getTranscode() {
        return transcode;
    }

    public void setTranscode(String transcode) {
        this.transcode = transcode;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getDocumentno() {
        return documentno;
    }

    public void setDocumentno(String documentno) {
        this.documentno = documentno;
    }

    public BigDecimal getDiscountamt() {
        return discountamt;
    }

    public void setDiscountamt(BigDecimal discountamt) {
        this.discountamt = discountamt;
    }

    public BigDecimal getAllowanceamt() {
        return allowanceamt;
    }

    public void setAllowanceamt(BigDecimal allowanceamt) {
        this.allowanceamt = allowanceamt;
    }

    public BigDecimal getWhtamt() {
        return whtamt;
    }

    public void setWhtamt(BigDecimal whtamt) {
        this.whtamt = whtamt;
    }

    public BigDecimal getVatamount() {
        return vatamount;
    }

    public void setVatamount(BigDecimal vatamount) {
        this.vatamount = vatamount;
    }

    public BigDecimal getClearArAmt() {
        return clearArAmt;
    }

    public void setClearArAmt(BigDecimal clearArAmt) {
        this.clearArAmt = clearArAmt;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public BigDecimal getBasewhtamt() {
        return basewhtamt;
    }

    public void setBasewhtamt(BigDecimal basewhtamt) {
        this.basewhtamt = basewhtamt;
    }

    public String getIntrecno() {
        return intrecno;
    }

    public void setIntrecno(String intrecno) {
        this.intrecno = intrecno;
    }

    public BigDecimal getBasevatamt() {
        return basevatamt;
    }

    public void setBasevatamt(BigDecimal basevatamt) {
        this.basevatamt = basevatamt;
    }

    public String getVatflag() {
        return vatflag;
    }

    public void setVatflag(String vatflag) {
        this.vatflag = vatflag;
    }

    public String getVatid() {
        return vatid;
    }

    public void setVatid(String vatid) {
        this.vatid = vatid;
    }
}
