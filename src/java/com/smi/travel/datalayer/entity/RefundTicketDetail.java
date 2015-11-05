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
    private String ticketno;
    private String sector;
    private String sectorRefund;
    private String charge;

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

}
