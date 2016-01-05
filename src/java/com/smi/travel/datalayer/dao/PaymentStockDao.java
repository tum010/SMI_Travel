/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.dao.impl.PaymentStockImpl;
import com.smi.travel.datalayer.entity.PaymentStock;
import com.smi.travel.datalayer.entity.PaymentStockDetail;
import com.smi.travel.datalayer.entity.PaymentStockItem;
import com.smi.travel.datalayer.entity.Stock;
import com.smi.travel.datalayer.entity.StockDetail;
import java.util.List;

/**
 *
 * @author Jittima
 */
public interface PaymentStockDao {
    public List<Stock> getListStock();
    public List<StockDetail> getListStockDetailFromStockId(String stockId);
    public PaymentStock getPaymentStockFromPayNo(String PayNo);
    public List<PaymentStockDetail> getListPaymentStockDetailFromPaymentStockId(String paymentStockId);
    public List<PaymentStockItem> getListPaymentStockItemFromPaymentStockId(String paymentStockId);
    public String deletePaymentStock(String paymentStockDetailId);
}
