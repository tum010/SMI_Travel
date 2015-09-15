/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.entity;

import java.math.BigDecimal;
import java.util.Date;



/**
 *
 * @author Jittima
 */
public class ReceiptDetailView {
    private String id;
    private String receiptNo;
    private Date receiptDate;
    private String invoiceNo;
    private Date receiveDate;
    private String mbillTypeStatus;
    private String description;
    private String displayDescription;
    private BigDecimal cost;
    private String curCost;
    private Integer isVat;
    private BigDecimal vat;
    private BigDecimal amount;
    private String curAmount;
    private String airlineCode;
    private String remark;
    private String payment;
    private BigDecimal billAmount;
    private BigDecimal recAmount;
    private String currency;
    private String billDescription;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getMbillTypeStatus() {
        return mbillTypeStatus;
    }

    public void setMbillTypeStatus(String mbillTypeStatus) {
        this.mbillTypeStatus = mbillTypeStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayDescription() {
        return displayDescription;
    }

    public void setDisplayDescription(String displayDescription) {
        this.displayDescription = displayDescription;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getCurCost() {
        return curCost;
    }

    public void setCurCost(String curCost) {
        this.curCost = curCost;
    }

    public Integer getIsVat() {
        return isVat;
    }

    public void setIsVat(Integer isVat) {
        this.isVat = isVat;
    }

    public BigDecimal getVat() {
        return vat;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurAmount() {
        return curAmount;
    }

    public void setCurAmount(String curAmount) {
        this.curAmount = curAmount;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public BigDecimal getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(BigDecimal billAmount) {
        this.billAmount = billAmount;
    }

    public BigDecimal getRecAmount() {
        return recAmount;
    }

    public void setRecAmount(BigDecimal recAmount) {
        this.recAmount = recAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBillDescription() {
        return billDescription;
    }

    public void setBillDescription(String billDescription) {
        this.billDescription = billDescription;
    }


    
    
}
