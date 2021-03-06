package com.smi.travel.datalayer.entity;
// Generated Mar 18, 2015 10:03:25 AM by Hibernate Tools 3.6.0

import java.math.BigDecimal;




/**
 * TourOperationDriver generated by hbm2java
 */
public class TourOperationDriver {


     private String id;
     private TourOperationDesc tourOperationDesc;
     private SystemUser staff;
     private String carNo;
     private String gasFee;
     private BigDecimal gasValue;
     private String tipFee;
     private BigDecimal tipValue;

    public TourOperationDriver() {
    }

	
    public TourOperationDriver(TourOperationDesc tourOperationDesc, SystemUser staff) {
        this.tourOperationDesc = tourOperationDesc;
        this.staff = staff;
    }
    public TourOperationDriver(TourOperationDesc tourOperationDesc, SystemUser staff, String carNo, String gasFee, BigDecimal gasValue, String tipFee, BigDecimal tipValue) {
       this.tourOperationDesc = tourOperationDesc;
       this.staff = staff;
       this.carNo = carNo;
       this.gasFee = gasFee;
       this.gasValue = gasValue;
       this.tipFee = tipFee;
       this.tipValue = tipValue;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public TourOperationDesc getTourOperationDesc() {
        return this.tourOperationDesc;
    }
    
    public void setTourOperationDesc(TourOperationDesc tourOperationDesc) {
        this.tourOperationDesc = tourOperationDesc;
    }
    public SystemUser getStaff() {
        return this.staff;
    }
    
    public void setStaff(SystemUser staff) {
        this.staff = staff;
    }
    public String getCarNo() {
        return this.carNo;
    }
    
    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }
    public String getGasFee() {
        return this.gasFee;
    }
    
    public void setGasFee(String gasFee) {
        this.gasFee = gasFee;
    }

    public String getTipFee() {
        return this.tipFee;
    }
    
    public void setTipFee(String tipFee) {
        this.tipFee = tipFee;
    }

    public BigDecimal getGasValue() {
        return gasValue;
    }

    public void setGasValue(BigDecimal gasValue) {
        this.gasValue = gasValue;
    }

    public BigDecimal getTipValue() {
        return tipValue;
    }

    public void setTipValue(BigDecimal tipValue) {
        this.tipValue = tipValue;
    }

}


