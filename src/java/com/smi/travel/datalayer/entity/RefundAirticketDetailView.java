/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.entity;

import java.math.BigDecimal;

/**
 *
 * @author Jittima
 */
public class RefundAirticketDetailView {
    private String id;
    private String refundNo;
    private String ticketNo;
    private String department;
    private String route;
    private BigDecimal commisssion;
    private BigDecimal amount;
    private BigDecimal payCus;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRefundNo() {
        return refundNo;
    }

    public void setRefundNo(String refundNo) {
        this.refundNo = refundNo;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public BigDecimal getCommisssion() {
        return commisssion;
    }

    public void setCommisssion(BigDecimal commisssion) {
        this.commisssion = commisssion;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPayCus() {
        return payCus;
    }

    public void setPayCus(BigDecimal payCus) {
        this.payCus = payCus;
    }






}
