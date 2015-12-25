/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.entity;

import java.math.BigDecimal;

/**
 *
 * @author chonnasith
 */
public class PaymentStockItem {
    private String id;
    private StockDetail stockDetail;
    private PaymentStockDetail paymentStockDetail;
    private BigDecimal cost;
    private BigDecimal sale;

    public PaymentStockItem() {
    }

    public PaymentStockItem(StockDetail stockDetail, PaymentStockDetail paymentStockDetail, BigDecimal cost, BigDecimal sale) {
       this.stockDetail = stockDetail;
       this.paymentStockDetail = paymentStockDetail;
       this.cost = cost;
       this.sale = sale;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StockDetail getStockDetail() {
        return stockDetail;
    }

    public void setStockDetail(StockDetail stockDetail) {
        this.stockDetail = stockDetail;
    }

    public PaymentStockDetail getPaymentStockDetail() {
        return paymentStockDetail;
    }

    public void setPaymentStockDetail(PaymentStockDetail paymentStockDetail) {
        this.paymentStockDetail = paymentStockDetail;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getSale() {
        return sale;
    }

    public void setSale(BigDecimal sale) {
        this.sale = sale;
    }
}
