/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.entity;

import java.util.Date;

/**
 *
 * @author Surachai
 */
public class StockView {
    private String code;
    private String RefNo;
    private String Pickup;
    private String PickupDate;
    private String PayStatusName;
    private String ItemStatus;
    private String TypeId;
    private String TypeName;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRefNo() {
        return RefNo;
    }

    public void setRefNo(String RefNo) {
        this.RefNo = RefNo;
    }

    public String getPickup() {
        return Pickup;
    }

    public void setPickup(String Pickup) {
        this.Pickup = Pickup;
    }

    public String getPickupDate() {
        return PickupDate;
    }

    public void setPickupDate(String PickupDate) {
        this.PickupDate = PickupDate;
    }

    public String getPayStatusName() {
        return PayStatusName;
    }

    public void setPayStatusName(String PayStatusName) {
        this.PayStatusName = PayStatusName;
    }

    public String getItemStatus() {
        return ItemStatus;
    }

    public void setItemStatus(String ItemStatus) {
        this.ItemStatus = ItemStatus;
    }

    public String getTypeId() {
        return TypeId;
    }

    public void setTypeId(String TypeId) {
        this.TypeId = TypeId;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String TypeName) {
        this.TypeName = TypeName;
    }
    
    
}
