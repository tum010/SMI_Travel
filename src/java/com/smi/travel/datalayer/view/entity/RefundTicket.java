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
 * @author Kanokporn
 */
public class RefundTicket {
    private String airticketrefundid;
    private String refundno;
    private String refundby;
    private Date refunddate;
    private String receiveby;
    private String change;
    private String detail;

    public String getAirticketrefundid() {
        return airticketrefundid;
    }

    public void setAirticketrefundid(String airticketrefundid) {
        this.airticketrefundid = airticketrefundid;
    }

    
    public String getRefundno() {
        return refundno;
    }

    public void setRefundno(String refundno) {
        this.refundno = refundno;
    }

    public String getRefundby() {
        return refundby;
    }

    public void setRefundby(String refundby) {
        this.refundby = refundby;
    }

    public Date getRefunddate() {
        return refunddate;
    }

    public void setRefunddate(Date refunddate) {
        this.refunddate = refunddate;
    }

    public String getReceiveby() {
        return receiveby;
    }

    public void setReceiveby(String receiveby) {
        this.receiveby = receiveby;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

  

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
  
}
