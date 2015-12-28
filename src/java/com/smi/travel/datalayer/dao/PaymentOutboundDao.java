/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.PaymentOutbound;
import com.smi.travel.datalayer.entity.PaymentOutboundDetailView;
import com.smi.travel.datalayer.entity.PaymentStock;
import com.smi.travel.datalayer.view.entity.BookingOutboundView;
import com.smi.travel.datalayer.view.entity.PaymentOutboundView;
import java.util.List;

/**
 *
 * @author chonnasith
 */
public interface PaymentOutboundDao {
    
    public String updatePaymentOutbound(PaymentOutbound paymentOutbound);
    public String insertPaymentOutbound(PaymentOutbound paymentOutbound);
    public List<PaymentOutboundDetailView> getPaymentOutboundDetail(String paymentOutboundId);
    public PaymentOutbound searchPaymentOutbound(String payNo);
    public List<String> getRefNoOutbound();
    public String deletePaymentOutboundDetail(String paymentOutboundDetailId);
    public List<BookingOutboundView> getBookingOutboundView(String searchRefNo);
    public List<PaymentStock> getPaymentStock(String payStockNo);
    public List<PaymentOutboundView> searchPaymentOutboundByFilter(String fromDate, String toDate, String status, String invSupCode, String invSupName, String refNo);
    public String deletePaymentOutbound(String paymentId);
    
}
