package com.smi.travel.datalayer.service;


import com.smi.travel.datalayer.dao.PaymentOutboundDao;

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
}
