package com.smi.travel.datalayer.entity;
// Generated Mar 18, 2015 10:03:25 AM by Hibernate Tools 3.6.0

import java.math.BigDecimal;




/**
 * DaytourExpense generated by hbm2java
 */
public class DaytourExpense   {

     private String id;
     private Daytour daytour;
     private String description;
     private BigDecimal amount;
     private String currency;
     private String priceType;

    public DaytourExpense() {
    }

	
    public DaytourExpense(Daytour daytour) {
        this.daytour = daytour;
    }
    public DaytourExpense(Daytour daytour, String description, BigDecimal amount, String currency,String priceType) {
       this.daytour = daytour;
       this.description = description;
       this.amount = amount;
       this.currency = currency;
       this.priceType = priceType;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public Daytour getDaytour() {
        return this.daytour;
    }
    
    public void setDaytour(Daytour daytour) {
        this.daytour = daytour;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrency() {
        return this.currency;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}


