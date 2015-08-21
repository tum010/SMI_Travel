package com.smi.travel.datalayer.entity;
// Generated Aug 15, 2015 12:01:47 AM by Hibernate Tools 3.6.0


import java.math.BigDecimal;
import java.util.Date;

/**
 * TaxInvoiceDetail generated by hbm2java
 */
public class TaxInvoiceDetail  {


     private String id;
     private TaxInvoice taxInvoice;
     private InvoiceDetail invoiceDetail;
     private Character vatType;
     private BigDecimal vat;
     private BigDecimal amount;
     private BigDecimal cost;
     private String createBy;
     private Date createDate;
     private Integer isVat;
     private String curCost;
     private String curAmount;
     private MBilltype mbillType;
     private Master master;
     private String description;

    public TaxInvoiceDetail() {
    }

    public TaxInvoiceDetail(TaxInvoice taxInvoice, InvoiceDetail invoiceDetail, Character vatType, BigDecimal vat, BigDecimal amount, BigDecimal cost, String createBy, Date createDate, Integer isVat, String curCost, String curAmount, MBilltype mbillType, Master master, String description) {
       this.taxInvoice = taxInvoice;
       this.invoiceDetail = invoiceDetail;
       this.vatType = vatType;
       this.vat = vat;
       this.amount = amount;
       this.cost = cost;
       this.createBy = createBy;
       this.createDate = createDate;
       this.isVat = isVat;
       this.curCost = curCost;
       this.curAmount = curAmount;
       this.mbillType = mbillType;
       this.master = master;
       this.description = description;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public TaxInvoice getTaxInvoice() {
        return this.taxInvoice;
    }
    
    public void setTaxInvoice(TaxInvoice taxInvoice) {
        this.taxInvoice = taxInvoice;
    }
    public InvoiceDetail getInvoiceDetail() {
        return this.invoiceDetail;
    }
    
    public void setInvoiceDetail(InvoiceDetail invoiceDetail) {
        this.invoiceDetail = invoiceDetail;
    }
   
    public Character getVatType() {
        return this.vatType;
    }
    
    public void setVatType(Character vatType) {
        this.vatType = vatType;
    }
    public BigDecimal getVat() {
        return this.vat;
    }
    
    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }
    public BigDecimal getAmount() {
        return this.amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public BigDecimal getCost() {
        return this.cost;
    }
    
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
    public String getCreateBy() {
        return this.createBy;
    }
    
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    public Date getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getIsVat() {
        return isVat;
    }

    public void setIsVat(Integer isVat) {
        this.isVat = isVat;
    }

    public String getCurCost() {
        return curCost;
    }

    public void setCurCost(String curCost) {
        this.curCost = curCost;
    }

    public String getCurAmount() {
        return curAmount;
    }

    public void setCurAmount(String curAmount) {
        this.curAmount = curAmount;
    }

    public MBilltype getMbillType() {
        return mbillType;
    }

    public void setMbillType(MBilltype mbillType) {
        this.mbillType = mbillType;
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

