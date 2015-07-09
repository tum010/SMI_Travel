package com.smi.travel.datalayer.entity;
// Generated Jul 9, 2015 9:38:50 AM by Hibernate Tools 4.3.1


import java.util.LinkedList;
import java.util.List;


/**
 * RefundAirticketDetail generated by hbm2java
 */
public class RefundAirticketDetail {


     private String id;
     private RefundAirticket refundAirticket;
     private TicketFareAirline ticketFareAirline;
     private String sectorRefund;
     private Long receiveAirline;
     private Long payCustomer;
     private Long commission;
     private List paymentAirticketRefunds = new LinkedList<PaymentAirticketRefund>();

    public RefundAirticketDetail() {
    }

    public RefundAirticketDetail(RefundAirticket refundAirticket, TicketFareAirline ticketFareAirline, String sectorRefund, Long receiveAirline, Long payCustomer, Long commission, List paymentAirticketRefunds) {
       this.refundAirticket = refundAirticket;
       this.ticketFareAirline = ticketFareAirline;
       this.sectorRefund = sectorRefund;
       this.receiveAirline = receiveAirline;
       this.payCustomer = payCustomer;
       this.commission = commission;
       this.paymentAirticketRefunds = paymentAirticketRefunds;
    }
   
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public RefundAirticket getRefundAirticket() {
        return this.refundAirticket;
    }
    
    public void setRefundAirticket(RefundAirticket refundAirticket) {
        this.refundAirticket = refundAirticket;
    }
    public TicketFareAirline getTicketFareAirline() {
        return this.ticketFareAirline;
    }
    
    public void setTicketFareAirline(TicketFareAirline ticketFareAirline) {
        this.ticketFareAirline = ticketFareAirline;
    }
    public String getSectorRefund() {
        return this.sectorRefund;
    }
    
    public void setSectorRefund(String sectorRefund) {
        this.sectorRefund = sectorRefund;
    }
    public Long getReceiveAirline() {
        return this.receiveAirline;
    }
    
    public void setReceiveAirline(Long receiveAirline) {
        this.receiveAirline = receiveAirline;
    }
    public Long getPayCustomer() {
        return this.payCustomer;
    }
    
    public void setPayCustomer(Long payCustomer) {
        this.payCustomer = payCustomer;
    }
    public Long getCommission() {
        return this.commission;
    }
    
    public void setCommission(Long commission) {
        this.commission = commission;
    }

    public List getPaymentAirticketRefunds() {
        return paymentAirticketRefunds;
    }

    public void setPaymentAirticketRefunds(List paymentAirticketRefunds) {
        this.paymentAirticketRefunds = paymentAirticketRefunds;
    }

}


