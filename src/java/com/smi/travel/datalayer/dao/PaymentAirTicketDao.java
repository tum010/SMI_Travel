/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.PaymentAirticket;
import com.smi.travel.datalayer.entity.RefundAirticket;
import com.smi.travel.datalayer.entity.TicketFareAirline;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface PaymentAirTicketDao {
    public String InsertPaymentAir(PaymentAirticket PayAir);
    public String UpdatePaymentAir(PaymentAirticket PayAir);
    public String DeletePaymentAir(PaymentAirticket PayAir);
    //not delete if isExport == 1 or have data in PaymentAirticketFare and PaymentAirticketRefund
    public String DeletePaymentAirFare(String AirfareId);
    //delete from PaymentAirticketFare payair where payair.id = AirfareId
    public String DeletePaymentAirRefund(String AirRefundId);
    //delete from PaymentAirticketRefund payrefund where payrefund.id = AirRefundId
    public PaymentAirticket getPaymentAirTicketFromPayno(String Payno);
    public List<TicketFareAirline> getListTicketFare(String From,String to,String By,String AitAgentId);
    //from TicketFareAirline
    //By = ticketBuy 
    //From , to  = issueDate
    //AirAgentId = MAirlineAgent.id
    public RefundAirticket addRefundAirTicket(String RefundID);
    //from RefundAirticket
    //RefundID = id
}
