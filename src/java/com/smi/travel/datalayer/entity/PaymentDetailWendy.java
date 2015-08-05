package com.smi.travel.datalayer.entity;
// Generated Jul 7, 2015 10:08:38 AM by Hibernate Tools 3.6.0


import java.math.BigDecimal;

/**
 * PaymentDetailWendy generated by hbm2java
 */
public class PaymentDetailWendy   {


     private String id;
     private Master master;
     private MPaytype MPaytype;
     private PaymentWendy paymentWendy;
     private String invoiceCreditor;
     private BigDecimal amount;
     private String amountType;
     private String description;
     private String accCode;
     private Double vat;
     private BigDecimal gross;
     private Integer isVat;
     private String refCode;

    public PaymentDetailWendy() {
    }

    public PaymentDetailWendy(Master master, MPaytype MPaytype, PaymentWendy paymentWendy, String invoiceCreditor, BigDecimal amount, String amountType, String description ,String accCode,Double vat,BigDecimal gross,Integer isvat,String refCode) {
       this.master = master;
       this.MPaytype = MPaytype;
       this.paymentWendy = paymentWendy;
       this.invoiceCreditor = invoiceCreditor;
       this.amount = amount;
       this.amountType = amountType;
       this.description = description;
       this.vat = vat;
       this.gross = gross;
       this.isVat = isVat;
       this.accCode = accCode;
       this.refCode = refCode;
    }
   
    public String getId() {
        return this.id;
    }
      
    public void setId(String id) {
        this.id = id;
    }
    public Master getMaster() {
        return this.master;
    }
    
    public void setMaster(Master master) {
        this.master = master;
    }
    public MPaytype getMPaytype() {
        return this.MPaytype;
    }
    
    public void setMPaytype(MPaytype MPaytype) {
        this.MPaytype = MPaytype;
    }
    public PaymentWendy getPaymentWendy() {
        return this.paymentWendy;
    }
    
    public void setPaymentWendy(PaymentWendy paymentWendy) {
        this.paymentWendy = paymentWendy;
    }
    public String getInvoiceCreditor() {
        return this.invoiceCreditor;
    }
    
    public void setInvoiceCreditor(String invoiceCreditor) {
        this.invoiceCreditor = invoiceCreditor;
    }
    public BigDecimal getAmount() {
        return this.amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public String getAmountType() {
        return this.amountType;
    }
    
    public void setAmountType(String amountType) {
        this.amountType = amountType;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccCode() {
        return accCode;
    }

    public void setAccCode(String accCode) {
        this.accCode = accCode;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

    

    public BigDecimal getGross() {
        return gross;
    }

    public void setGross(BigDecimal gross) {
        this.gross = gross;
    }

    public Integer getIsVat() {
        return isVat;
    }

    public void setIsVat(Integer isVat) {
        this.isVat = isVat;
    }

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
    }






}


