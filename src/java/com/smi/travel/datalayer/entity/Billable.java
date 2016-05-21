package com.smi.travel.datalayer.entity;
// Generated Dec 24, 2014 10:24:56 AM by Hibernate Tools 3.6.0


import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 * Billable generated by hbm2java
 */
public class Billable {


     private String id;
     private MAccpay MAccpay;
     private Master master;
     private MAccterm MAccterm;
     private Passenger passenger;
     private String billTo;
     private String billName;
     private String billAddress;
     private Date transferDate;
     private Integer isPayYourself;
     private String remark;
     private MBank bankAccount;
     private String type;

     private List billableDescs = new LinkedList<BillableDesc>();
             
    public Billable() {
    }

	
    public Billable(Master master) {
        this.master = master;
    }
    public Billable(MAccpay MAccpay, Master master, MAccterm MAccterm, Passenger passenger, String billTo, String billName, String billAddress, Date transferDate, Integer isPayYourself, List billableDescs,String remark,MBank bankAccount) {
       this.MAccpay = MAccpay;
       this.master = master;
       this.MAccterm = MAccterm;
       this.passenger = passenger;
       this.billTo = billTo;
       this.billName = billName;
       this.billAddress = billAddress;
       this.transferDate = transferDate;
       this.isPayYourself = isPayYourself;
       this.billableDescs = billableDescs;
       this.bankAccount = bankAccount;
       this.remark = remark;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public MAccpay getMAccpay() {
        return this.MAccpay;
    }
    
    public void setMAccpay(MAccpay MAccpay) {
        this.MAccpay = MAccpay;
    }
    public Master getMaster() {
        return this.master;
    }
    
    public void setMaster(Master master) {
        this.master = master;
    }
    public MAccterm getMAccterm() {
        return this.MAccterm;
    }
    
    public void setMAccterm(MAccterm MAccterm) {
        this.MAccterm = MAccterm;
    }
    public Passenger getPassenger() {
        return this.passenger;
    }
    
    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
    public String getBillTo() {
        return this.billTo;
    }
    
    public void setBillTo(String billTo) {
        this.billTo = billTo;
    }
    public String getBillName() {
        return this.billName;
    }
    
    public void setBillName(String billName) {
        this.billName = billName;
    }
    public String getBillAddress() {
        return this.billAddress;
    }
    
    public void setBillAddress(String billAddress) {
        this.billAddress = billAddress;
    }

    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }
  
    
    
    public Integer getIsPayYourself() {
        return this.isPayYourself;
    }
    
    public void setIsPayYourself(Integer isPayYourself) {
        this.isPayYourself = isPayYourself;
    }
    
    public List getBillableDescs() {
        return this.billableDescs;
    }
    
    public void setBillableDescs(List billableDescs) {
        this.billableDescs = billableDescs;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public MBank getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(MBank bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    
    
}
