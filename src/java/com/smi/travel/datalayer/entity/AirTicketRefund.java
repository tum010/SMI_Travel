/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.entity;

import java.util.List;

/**
 *
 * @author Kanokporn
 */
public class AirTicketRefund {
    private List AirticketBooking;
    private List RefundAirticket;

    public List getAirticketBooking() {
        return AirticketBooking;
    }

    public void setAirticketBooking(List AirticketBooking) {
        this.AirticketBooking = AirticketBooking;
    }

    public List getRefundAirticket() {
        return RefundAirticket;
    }

    public void setRefundAirticket(List RefundAirticket) {
        this.RefundAirticket = RefundAirticket;
    }
    
    
}
