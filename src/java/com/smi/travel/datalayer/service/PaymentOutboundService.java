package com.smi.travel.datalayer.service;


import com.smi.travel.datalayer.dao.PaymentOutboundDao;
import com.smi.travel.datalayer.entity.PaymentOutbound;
import com.smi.travel.datalayer.entity.PaymentOutboundDetailView;
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

    public PaymentOutbound searchPaymentOutbound(String payNo) {
        return paymentOutboundDao.searchPaymentOutbound(payNo);
    }

    public List<String> getRefNoOutbound() {
        return paymentOutboundDao.getRefNoOutbound();
    }

    public String deletePaymentOutboundDetail(String paymentOutboundDetailId) {
        return paymentOutboundDao.deletePaymentOutboundDetail(paymentOutboundDetailId);
    }
}
