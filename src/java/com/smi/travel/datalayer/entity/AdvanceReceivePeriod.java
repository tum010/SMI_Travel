/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.entity;

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
}
