package com.smi.travel.datalayer.service;


import com.smi.travel.datalayer.dao.PaymentOutboundDao;
import com.smi.travel.datalayer.entity.PaymentOutbound;
import com.smi.travel.datalayer.entity.PaymentOutboundDetail;
import com.smi.travel.datalayer.entity.PaymentOutboundDetailView;
import com.smi.travel.datalayer.view.entity.PaymentOutboundInvSummaryView;
import com.smi.travel.datalayer.view.entity.PaymentOutboundView;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chonnasith
 */
public class PaymentOutboundService {
    private PaymentOutboundDao paymentOutboundDao;

    public PaymentOutboundDao getPaymentOutboundDao() {
        return paymentOutboundDao;
    }

    public void setPaymentOutboundDao(PaymentOutboundDao paymentOutboundDao) {
        this.paymentOutboundDao = paymentOutboundDao;
    }

    public String savePaymentOutbound(PaymentOutbound paymentOutbound) {
        if((!"".equalsIgnoreCase(paymentOutbound.getId())) && (paymentOutbound.getId() != null)){
            return paymentOutboundDao.updatePaymentOutbound(paymentOutbound);
        }else{
            return paymentOutboundDao.insertPaymentOutbound(paymentOutbound);
        }
    }

    public List<PaymentOutboundDetailView> getPaymentOutboundDetail(String paymentOutboundId) {
        return paymentOutboundDao.getPaymentOutboundDetail(paymentOutboundId); 
    }
    
    public List<PaymentOutboundInvSummaryView> getPaymentOutboundInvSummary(List<PaymentOutboundDetailView> paymentOutboundDetailView){
         return paymentOutboundDao.getPaymentOutboundInvSummary(paymentOutboundDetailView); 
    }

    public PaymentOutbound searchPaymentOutbound(String payNo) {
        return paymentOutboundDao.searchPaymentOutbound(payNo);
    }

    public List<String> getRefNoOutbound() {
        return paymentOutboundDao.getRefNoOutbound();
    }

    public String deletePaymentOutboundDetail(String paymentOutboundDetailId) {
        return paymentOutboundDao.deletePaymentOutboundDetail(paymentOutboundDetailId);
    }

    public List<PaymentOutboundView> searchPaymentOutboundByFilter(String fromDate, String toDate, String status, String invSupCode, String invSupName, String refNo, String dueDateFrom, String dueDateTo, String payNo) {
        return paymentOutboundDao.searchPaymentOutboundByFilter(fromDate, toDate, status, invSupCode, invSupName, refNo, dueDateFrom, dueDateTo, payNo);
    }

    public String deletePaymentOutbound(String paymentId) {
        return paymentOutboundDao.deletePaymentOutbound(paymentId);
    }

    public PaymentOutbound getPaymentOutbound(String payId) {
        return paymentOutboundDao.getPaymentOutbound(payId);
    }

    public PaymentOutboundDetail getAPNirvanaData(String detailId) {
        return paymentOutboundDao.getAPNirvanaData(detailId);
    }
}
