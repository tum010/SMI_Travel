package com.smi.travel.datalayer.service;


import com.smi.travel.datalayer.dao.PaymentOutboundDao;
import com.smi.travel.datalayer.entity.PaymentOutbound;

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
}
