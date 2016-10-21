/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.PaymentAirCredit;
import com.smi.travel.datalayer.entity.PaymentAirDebit;
import com.smi.travel.datalayer.entity.PaymentAirticket;
import com.smi.travel.datalayer.entity.PaymentAirticketAccount;
import com.smi.travel.datalayer.entity.RefundAirticketDetailView;
import com.smi.travel.datalayer.report.model.PaymentAirline;
import com.smi.travel.datalayer.view.entity.PaymentAirView;
import com.smi.travel.datalayer.view.entity.PaymentAirlineList;
import com.smi.travel.datalayer.view.entity.TicketFareView;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Surachai
 */
public interface PaymentAirTicketDao {
    public String InsertPaymentAir(PaymentAirticket payAir);
    public String UpdatePaymentAir(PaymentAirticket payAir);
    public String DeletePaymentAir(PaymentAirticket payAir);
    //not delete if isExport == 1 or have data in PaymentAirticketFare and PaymentAirticketRefund
    public String DeletePaymentAirFare(String paymentAirId , String ticketFareId , int option); //1 = delete ajax , 2 = delete all when cf search
    
    //delete from PaymentAirticketFare payair where payair.id = AirfareId
    public String DeletePaymentAirRefund(String paymentAirId,String refundDetailId , int option);
    public String DeletePaymentAirCredit(String paymentAirId,String paymentCreditId);
    public String DeletePaymentAirDebit(String paymentAirId,String paymentDebitId);
    public String DeletePaymentAccount(String paymentAirId,String paymentAccountId);
    //delete from PaymentAirticketRefund payrefund where payrefund.id = AirRefundId
    public PaymentAirticket getPaymentAirTicketFromPayno(String payNo);
    public List<TicketFareView> getListTicketFare(String from,String to,String by,String airAgentId,String invoiceSubCode,String typeAirlineOther,String ticketType,String TicketNum);
    //from TicketFareAirline
    //By = ticketBuy 
    //From , to  = issueDate
    //AirAgentId = MAirlineAgent.id
    public String addRefundAirTicket(String refundNo,String rowCount,String ticketNoList);
    //from RefundAirticket
    //RefundID = id
    public String validateSavePaymentAir(PaymentAirticket payAir);
    public List<TicketFareView> getTicketFareViewsByPaymentAirId(String paymentAirId);
    public List<RefundAirticketDetailView> getRefundDetailByPaymentAirId(String paymentAirId);
    public List<PaymentAirCredit> getPaymentAirCreditByPaymentAirId(String paymentAirId);
    public List<PaymentAirDebit> getPaymentAirDebitByPaymentAirId(String paymentAirId);
    public List<PaymentAirticketAccount> getPaymentAirticketAccountByPaymentAirId(String paymentAirId);
    public List<PaymentAirView> getPaymentAirViewReport(String payno);
    public PaymentAirline getPaymentAirlineReport(String payno,String printby);
    
//    public List getPaymentAirline(String payno,String printby);
    public List getPaymentAirlineList(String payno,String printby);
//    public List getPaymentAirlineRefund(String payno,String printby) ;
    
    public PaymentAirticket getPaymentAirTicketByWildCardSearch(String paymentId, String paymentNo, String wildCardSearch, String keyCode);
    public List<PaymentAirlineList> getPaymentAirlineList();
    
    public Map<String, Object> getTotalAmountAndTotalCommission(String paymentId);
}
