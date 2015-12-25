/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author chonnasith
 */
public class PaymentStock {
    private String id;
    private String payStockNo;
    private String createBy;
    private Date createDate;
    private String updateBy;
    private Date updateDate;
    private BigDecimal costAmount;
    private BigDecimal saleAmount;
    private String curCost;
    private String curSale;
    private List paymentStockDetails = new LinkedList<PaymentStockDetail>();
    private List paymentOutboundDetails = new LinkedList<PaymentOutboundDetail>();

    public PaymentStock() {
    }

    public PaymentStock(String payStockNo, String createBy, Date createDate, String updateBy, Date updateDate, BigDecimal costAmount, BigDecimal saleAmount, String curCost, String curSale, List paymentStockDetails, List paymentOutboundDetails) {
       this.payStockNo = payStockNo;
       this.createBy = createBy;
       this.createDate = createDate;
       this.updateBy = updateBy;
       this.updateDate = updateDate;
       this.costAmount = costAmount;
       this.saleAmount = saleAmount;
       this.curCost = curCost;
       this.curSale = curSale;
       this.paymentStockDetails = paymentStockDetails;
       this.paymentOutboundDetails = paymentOutboundDetails;
    }   

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPayStockNo() {
        return payStockNo;
    }

    public void setPayStockNo(String payStockNo) {
        this.payStockNo = payStockNo;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public BigDecimal getCostAmount() {
        return costAmount;
    }

    public void setCostAmount(BigDecimal costAmount) {
        this.costAmount = costAmount;
    }

    public BigDecimal getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(BigDecimal saleAmount) {
        this.saleAmount = saleAmount;
    }

    public String getCurCost() {
        return curCost;
    }

    public void setCurCost(String curCost) {
        this.curCost = curCost;
    }

    public String getCurSale() {
        return curSale;
    }

    public void setCurSale(String curSale) {
        this.curSale = curSale;
    }

    public List getPaymentStockDetails() {
        return paymentStockDetails;
    }

    public void setPaymentStockDetails(List paymentStockDetails) {
        this.paymentStockDetails = paymentStockDetails;
    }

    public List getPaymentOutboundDetails() {
        return paymentOutboundDetails;
    }

    public void setPaymentOutboundDetails(List paymentOutboundDetails) {
        this.paymentOutboundDetails = paymentOutboundDetails;
    }
}
