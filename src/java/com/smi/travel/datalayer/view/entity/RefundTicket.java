/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Kanokporn
 */
public class RefundTicket {
    private String id;
    private String airticketrefundid;
    private String refundno;
    private String refundby;
    private Date refunddate;
    private String receiveby;
    private String ownerby;
    private String refundcode;
    private Date receivedate;
    private String change;
    private String paycustomer;
    private String detail;
    private String address;
    private String airbookingid;
    private List RefundTicketDetail;

    public String getOwnerby() {
        return ownerby;
    }

    public void setOwnerby(String ownerby) {
        this.ownerby = ownerby;
    }

    public String getPaycustomer() {
        return paycustomer;
    }

    public void setPaycustomer(String paycustomer) {
        this.paycustomer = paycustomer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAirbookingid() {
        return airbookingid;
    }

    public void setAirbookingid(String airbookingid) {
        this.airbookingid = airbookingid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRefundcode() {
        return refundcode;
    }

    public void setRefundcode(String refundcode) {
        this.refundcode = refundcode;
    }

    public Date getReceivedate() {
        return receivedate;
    }

    public void setReceivedate(Date receivedate) {
        this.receivedate = receivedate;
    }

    public List getRefundTicketDetail() {
        return RefundTicketDetail;
    }

    public void setRefundTicketDetail(List RefundTicketDetail) {
        this.RefundTicketDetail = RefundTicketDetail;
    }
    
    

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
