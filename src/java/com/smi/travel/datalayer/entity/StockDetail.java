package com.smi.travel.datalayer.entity;
// Generated Jul 7, 2015 11:22:27 AM by Hibernate Tools 3.6.0


import java.util.Date;

/**
 * StockDetail generated by hbm2java
 */
public class StockDetail {


     private String id;
     private SystemUser staff;
     private Stock stock;
     private MPricecategory typeId;
     private MStockStatus MStockStatus;
     private OtherBooking otherBooking;
     private String code;
     private Date pickupDate;
     private Integer payStatus;

    public StockDetail() {
    }

	
    public StockDetail(Stock stock) {
        this.stock = stock;
    }
    public StockDetail(SystemUser staff, Stock stock, MStockStatus MStockStatus, OtherBooking otherBooking, String code, Date pickupDate, Integer payStatus,MPricecategory typeId) {
       this.staff = staff;
       this.stock = stock;
       this.MStockStatus = MStockStatus;
       this.otherBooking = otherBooking;
       this.code = code;
       this.pickupDate = pickupDate;
       this.payStatus = payStatus;
       this.typeId = typeId;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public SystemUser getStaff() {
        return this.staff;
    }
    
    public void setStaff(SystemUser staff) {
        this.staff = staff;
    }
    public Stock getStock() {
        return this.stock;
    }
    
    public void setStock(Stock stock) {
        this.stock = stock;
    }
    public MStockStatus getMStockStatus() {
        return this.MStockStatus;
    }
    
    public void setMStockStatus(MStockStatus MStockStatus) {
        this.MStockStatus = MStockStatus;
    }
    public OtherBooking getOtherBooking() {
        return this.otherBooking;
    }
    
    public void setOtherBooking(OtherBooking otherBooking) {
        this.otherBooking = otherBooking;
    }
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    public Date getPickupDate() {
        return this.pickupDate;
    }
    
    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }
    public Integer getPayStatus() {
        return this.payStatus;
    }
    
    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public MPricecategory getTypeId() {
        return typeId;
    }

    public void setTypeId(MPricecategory typeId) {
        this.typeId = typeId;
    }




}


