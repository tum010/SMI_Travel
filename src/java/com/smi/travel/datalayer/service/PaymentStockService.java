/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.PaymentStockDao;
import com.smi.travel.datalayer.entity.PaymentStock;
import com.smi.travel.datalayer.entity.PaymentStockDetail;
import com.smi.travel.datalayer.entity.PaymentStockItem;
import com.smi.travel.datalayer.entity.Stock;
import java.util.List;

/**
 *
 * @author Jittima
 */
public class PaymentStockService {
    private PaymentStockDao paymentStockDao;
    
    public List<Stock> getListStock(){
        return paymentStockDao.getListStock();
    }
    
    public PaymentStock getPaymentStockFromPayNo(String PayNo){
        return paymentStockDao.getPaymentStockFromPayNo(PayNo);
    }
    public List<PaymentStockDetail> getListPaymentStockDetailFromPaymentStockId(String paymentStockId){
        return paymentStockDao.getListPaymentStockDetailFromPaymentStockId(paymentStockId);
    }
    
    public List<PaymentStockItem> getListPaymentStockItemFromPaymentStockId(String paymentStockId){
        return paymentStockDao.getListPaymentStockItemFromPaymentStockId(paymentStockId);
    }
    
    public String deletePaymentStock(String paymentStockDetailId){
        return paymentStockDao.deletePaymentStock(paymentStockDetailId);
    }
    
    public PaymentStockDao getPaymentStockDao() {
        return paymentStockDao;
    }

    public void setPaymentStockDao(PaymentStockDao paymentStockDao) {
        this.paymentStockDao = paymentStockDao;
    }
    
}
