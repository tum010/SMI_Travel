/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.PaymentOutbound;
import com.smi.travel.datalayer.entity.PaymentOutboundDetail;
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
    public List getPaymentOutboundSummaryReport(String fromDate,String toDate,String status,String invSupCode,String refNo,String username);
    public PaymentOutbound getPaymentOutbound(String payId);
    public List<PaymentOutboundDetail> checkDuplicatePaymentStock(String payStockNo);
    
    //Payment Summry Report
    public List getPaymentSummaryReport(String fromDate,String toDate,String saleby,String invSupCode,String refNo,String username);
    public List getStockInvoiceSummaryReport(String product, String invTo, String effectiveDateFrom, String effectiveDateTo, String invoiceDateFrom, String invoiceDateTo, String addDate, String username);
    public List getStockNonInvoiceSummaryReport(String product, String invoiceSup, String effectiveDateFrom, String effectiveDateTo, String payDateFrom, String payDateTo, String addDate, String username);
    
    
    public List getPaymentProfitLossReport(String departFromDate,String departToDate,String invFromDate,String invToDate,String ownercode,String city,String producttypeid,String invsupcode,
            String payFromDate,String payToDate,String groupby);
    
}
