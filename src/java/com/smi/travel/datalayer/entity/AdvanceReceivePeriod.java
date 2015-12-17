/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author chonnasith
 */
public class AdvanceReceivePeriod {
     private String  id;
     private Date receiveFrom;
     private Date receiveTo;
     private String detail;
     private String vatType;
     private String department;
     private BigDecimal cashAmount;
     private BigDecimal cashMinusAmount;
     private BigDecimal bankTransfer;
     private BigDecimal chqAmount;
     private BigDecimal creditAmount;

    public AdvanceReceivePeriod(String id, Date receiveFrom, Date receiveTo, String detail) {
       this.id = id;
       this.receiveFrom = receiveFrom;
       this.receiveTo = receiveTo;
       this.detail = detail;
    }

    public AdvanceReceivePeriod() {
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getReceiveFrom() {
        return receiveFrom;
    }

    public void setReceiveFrom(Date receiveFrom) {
        this.receiveFrom = receiveFrom;
    }

    public Date getReceiveTo() {
        return receiveTo;
    }

    public void setReceiveTo(Date receiveTo) {
        this.receiveTo = receiveTo;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getVatType() {
        return vatType;
    }

    public void setVatType(String vatType) {
        this.vatType = vatType;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public BigDecimal getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(BigDecimal cashAmount) {
        this.cashAmount = cashAmount;
    }

    public BigDecimal getCashMinusAmount() {
        return cashMinusAmount;
    }

    public void setCashMinusAmount(BigDecimal cashMinusAmount) {
        this.cashMinusAmount = cashMinusAmount;
    }

    public BigDecimal getBankTransfer() {
        return bankTransfer;
    }

    public void setBankTransfer(BigDecimal bankTransfer) {
        this.bankTransfer = bankTransfer;
    }

    public BigDecimal getChqAmount() {
        return chqAmount;
    }

    public void setChqAmount(BigDecimal chqAmount) {
        this.chqAmount = chqAmount;
    }

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }
}
