/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.report.model;

/**
 *
 * @author chonnasith
 */
public class PaymentOutboundReport {
    private String department;
    private String payno;
    private String paydate;
    private String invoicesup;
    private String gross;
    private String product;
    private String vat;
    private String orderno;
    private String description;
    private String amount;
    private String wht;
    private String sumamount;
    private String sumpayment;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPayno() {
        return payno;
    }

    public void setPayno(String payno) {
        this.payno = payno;
    }

    public String getPaydate() {
        return paydate;
    }

    public void setPaydate(String paydate) {
        this.paydate = paydate;
    }

    public String getInvoicesup() {
        return invoicesup;
    }

    public void setInvoicesup(String invoicesup) {
        this.invoicesup = invoicesup;
    }

    public String getGross() {
        return gross;
    }

    public void setGross(String gross) {
        this.gross = gross;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getWht() {
        return wht;
    }

    public void setWht(String wht) {
        this.wht = wht;
    }

    public String getSumamount() {
        return sumamount;
    }

    public void setSumamount(String sumamount) {
        this.sumamount = sumamount;
    }

    public String getSumpayment() {
        return sumpayment;
    }

    public void setSumpayment(String sumpayment) {
        this.sumpayment = sumpayment;
    }
}
