/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author chonnasith
 */
public class PaymentOutboundDetailView {
    private String detailId;
    private String payId;
    private String refNo;
    private Integer bookDetailId;
    private String type;
    private String description;
    private String invoice;
    private BigDecimal cost;
    private BigDecimal gross;
    private BigDecimal vat;
    private Integer isVat;
    private BigDecimal amount;
    private String cur;
    private BigDecimal comm;
    private BigDecimal value;
    private String accCode;
    private String bookDetailType;
    private String payStockId;
    private String payStock;
    private Date exportDate;
    private Integer isExport;
    private Date invoiceDate;
    private Integer isVatRecCom;
    private BigDecimal vatRecCom;
    private BigDecimal wht;
    private BigDecimal payExRate;
    private BigDecimal realExRate;
    private BigDecimal saleAmount;
    private BigDecimal vatRecComAmount;
    private BigDecimal whtAmount;
    private Integer isWht;
    private String saleCurrency;

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }
   
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getGross() {
        return gross;
    }

    public void setGross(BigDecimal gross) {
        this.gross = gross;
    }

    public BigDecimal getVat() {
        return vat;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    public Integer getIsVat() {
        return isVat;
    }

    public void setIsVat(Integer isVat) {
        this.isVat = isVat;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCur() {
        return cur;
    }

    public void setCur(String cur) {
        this.cur = cur;
    }

    public BigDecimal getComm() {
        return comm;
    }

    public void setComm(BigDecimal comm) {
        this.comm = comm;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getAccCode() {
        return accCode;
    }

    public void setAccCode(String accCode) {
        this.accCode = accCode;
    }

    public String getBookDetailType() {
        return bookDetailType;
    }

    public void setBookDetailType(String bookDetailType) {
        this.bookDetailType = bookDetailType;
    }

    public Date getExportDate() {
        return exportDate;
    }

    public void setExportDate(Date exportDate) {
        this.exportDate = exportDate;
    }

    public Integer getBookDetailId() {
        return bookDetailId;
    }

    public void setBookDetailId(Integer bookDetailId) {
        this.bookDetailId = bookDetailId;
    }

    public Integer getIsExport() {
        return isExport;
    }

    public void setIsExport(Integer isExport) {
        this.isExport = isExport;
    }

    public String getPayStockId() {
        return payStockId;
    }

    public void setPayStockId(String payStockId) {
        this.payStockId = payStockId;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Integer getIsVatRecCom() {
        return isVatRecCom;
    }

    public void setIsVatRecCom(Integer isVatRecCom) {
        this.isVatRecCom = isVatRecCom;
    }

    public BigDecimal getVatRecCom() {
        return vatRecCom;
    }

    public void setVatRecCom(BigDecimal vatRecCom) {
        this.vatRecCom = vatRecCom;
    }

    public BigDecimal getWht() {
        return wht;
    }

    public void setWht(BigDecimal wht) {
        this.wht = wht;
    }

    public BigDecimal getPayExRate() {
        return payExRate;
    }

    public void setPayExRate(BigDecimal payExRate) {
        this.payExRate = payExRate;
    }

    public BigDecimal getRealExRate() {
        return realExRate;
    }

    public void setRealExRate(BigDecimal realExRate) {
        this.realExRate = realExRate;
    }

    public BigDecimal getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(BigDecimal saleAmount) {
        this.saleAmount = saleAmount;
    }

    public BigDecimal getVatRecComAmount() {
        return vatRecComAmount;
    }

    public void setVatRecComAmount(BigDecimal vatRecComAmount) {
        this.vatRecComAmount = vatRecComAmount;
    }

    public BigDecimal getWhtAmount() {
        return whtAmount;
    }

    public void setWhtAmount(BigDecimal whtAmount) {
        this.whtAmount = whtAmount;
    }

    public Integer getIsWht() {
        return isWht;
    }

    public void setIsWht(Integer isWht) {
        this.isWht = isWht;
    }

    public String getSaleCurrency() {
        return saleCurrency;
    }

    public void setSaleCurrency(String saleCurrency) {
        this.saleCurrency = saleCurrency;
    }

    public String getPayStock() {
        return payStock;
    }

    public void setPayStock(String payStock) {
        this.payStock = payStock;
    }
  
}
