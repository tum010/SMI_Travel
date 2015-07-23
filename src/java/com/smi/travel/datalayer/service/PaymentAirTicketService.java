/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.PaymentAirTicketDao;
import com.smi.travel.datalayer.entity.PaymentAirticket;
import com.smi.travel.datalayer.entity.RefundAirticket;
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
    
    public String DeletePaymentAirFare(String airfareId){
        return paymentairticketdao.DeletePaymentAirFare(airfareId);
    }
    
    public String DeletePaymentAirRefund(String airRefundId){
        return paymentairticketdao.DeletePaymentAirRefund(airRefundId);
    }
    
    public PaymentAirticket getPaymentAirTicketFromPayno(String payNo){
        return paymentairticketdao.getPaymentAirTicketFromPayno(payNo);
    }
    
    public List<TicketFareView> getListTicketFare(String from,String to,String by,String airAgentId){
        return paymentairticketdao.getListTicketFare(from,to,by,airAgentId);
    }

    public String addRefundAirTicket(String refundNo){
        return paymentairticketdao.addRefundAirTicket(refundNo);
    }
    
    public String validateSavePaymentAir(PaymentAirticket payAir){
        return paymentairticketdao.validateSavePaymentAir(payAir);
    }
    
    
    
    
    public PaymentAirTicketDao getPaymentairticketdao() {
        return paymentairticketdao;
    }

    public void setPaymentairticketdao(PaymentAirTicketDao paymentairticketdao) {
        this.paymentairticketdao = paymentairticketdao;
    }
}
