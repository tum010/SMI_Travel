/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.entity;

/**
 *
 * @author Kanokporn
 */
public class RefundTicketDetail {
    private String refunddetailid;
    private String ticketno;
    private String sector;
    private String sectorRefund;
    private String charge;
    private String paycustomer;
    private String ticketid;
    private String clientcharge;

    public String getPaycustomer() {
        return paycustomer;
    }

    public void setPaycustomer(String paycustomer) {
        this.paycustomer = paycustomer;
    }

    public String getTicketid() {
        return ticketid;
    }

    public void setTicketid(String ticketid) {
        this.ticketid = ticketid;
    }

    public String getRefunddetailid() {
        return refunddetailid;
    }

    public void setRefunddetailid(String refunddetailid) {
        this.refunddetailid = refunddetailid;
    }

    
    public String getTicketno() {
        return ticketno;
    }

    public void setTicketno(String ticketno) {
        this.ticketno = ticketno;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getSectorRefund() {
        return sectorRefund;
    }

    public void setSectorRefund(String sectorRefund) {
        this.sectorRefund = sectorRefund;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getClientcharge() {
        return clientcharge;
    }

    public void setClientcharge(String clientcharge) {
        this.clientcharge = clientcharge;
    }

}
