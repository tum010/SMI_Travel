/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.PaymentAirTicketDao;
import com.smi.travel.datalayer.entity.PaymentAirticket;
import com.smi.travel.datalayer.entity.PaymentAirticketFare;
import com.smi.travel.datalayer.entity.PaymentAirticketRefund;
import com.smi.travel.datalayer.entity.RefundAirticket;
import com.smi.travel.datalayer.entity.RefundAirticketDetailView;
import com.smi.travel.datalayer.entity.TicketFareAirline;
import com.smi.travel.datalayer.view.entity.TicketFareView;
import java.util.List;

/**
 *
 * @author Jittima
 */
public class PaymentAirTicketService {
    private PaymentAirTicketDao paymentairticketdao;
    
    
    public String InsertPaymentAir(PaymentAirticket payAir){
        return paymentairticketdao.InsertPaymentAir(payAir);
    }
    
    public String UpdatePaymentAir(PaymentAirticket payAir){
        return paymentairticketdao.UpdatePaymentAir(payAir);
    }
    
    public String DeletePaymentAir(PaymentAirticket payAir){
        return paymentairticketdao.DeletePaymentAir(payAir);
    }
    
    public String DeletePaymentAirFare(String paymentAirId,String ticketFareId , int option){
        return paymentairticketdao.DeletePaymentAirFare(paymentAirId,ticketFareId,option);
    }
    
    public String DeletePaymentAirRefund(String paymentAirId,String refundDetailId , int option){
        return paymentairticketdao.DeletePaymentAirRefund(paymentAirId,refundDetailId,option);
    }
    
    public PaymentAirticket getPaymentAirTicketFromPayno(String payNo){
        return paymentairticketdao.getPaymentAirTicketFromPayno(payNo);
    }
    
    public List<TicketFareView> getListTicketFare(String from,String to,String by,String airAgentId,String invoiceSubCode){
        return paymentairticketdao.getListTicketFare(from,to,by,airAgentId,invoiceSubCode);
    }

    public String addRefundAirTicket(String refundNo,String rowCount){
        return paymentairticketdao.addRefundAirTicket(refundNo,rowCount);
    }
    
    public String validateSavePaymentAir(PaymentAirticket payAir){
        return paymentairticketdao.validateSavePaymentAir(payAir);
    }
    
    public List<TicketFareView> getTicketFareViewsByPaymentAirId(String paymentAirId){
        return paymentairticketdao.getTicketFareViewsByPaymentAirId(paymentAirId);
    }
    
    public List<RefundAirticketDetailView> getRefundDetailByPaymentAirId(String paymentAirId){
        return paymentairticketdao.getRefundDetailByPaymentAirId(paymentAirId);
    }
    
    public List<PaymentAirticketFare> searchTicketFare(String ticketfareId,String invoiceSubCode){
        return paymentairticketdao.searchTicketFare(ticketfareId,invoiceSubCode);
    }
    
    public PaymentAirTicketDao getPaymentairticketdao() {
        return paymentairticketdao;
    }

    public void setPaymentairticketdao(PaymentAirTicketDao paymentairticketdao) {
        this.paymentairticketdao = paymentairticketdao;
    }
}
