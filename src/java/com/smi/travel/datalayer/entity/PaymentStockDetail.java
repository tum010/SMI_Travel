/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.entity;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author chonnasith
 */
public class PaymentStockDetail {
    private String id;
    private Stock stock;
    private PaymentStock paymentStock;
    private List paymentStockItems = new LinkedList<PaymentStockItem>();

    public PaymentStockDetail() {
    }

    public PaymentStockDetail(Stock stock, PaymentStock paymentStock, List paymentStockItems) {
       this.stock = stock;
       this.paymentStock = paymentStock;
       this.paymentStockItems = paymentStockItems;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public PaymentStock getPaymentStock() {
        return paymentStock;
    }

    public void setPaymentStock(PaymentStock paymentStock) {
        this.paymentStock = paymentStock;
    }

    public List getPaymentStockItems() {
        return paymentStockItems;
    }

    public void setPaymentStockItems(List paymentStockItems) {
        this.paymentStockItems = paymentStockItems;
    }
}
