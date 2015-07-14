/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Surachai
 */
public class PaymentWendytourView {
    private String id;
    private String payNo;
    private Date payDate;
    private String payType;
    private String invoiceSup;
    private int accNo;
    private String department;
    private BigDecimal total;
    private String currency;
    private String status;
    
    public PaymentWendytourView(){
        
    }

    public PaymentWendytourView(String id, String payNo, Date payDate, String payType, String InvoiceSup, int accNo, String department, BigDecimal total, String currency, String status) {
        this.id = id;
        this.payNo = payNo;
        this.payDate = payDate;
        this.payType = payType;
        this.invoiceSup = InvoiceSup;
        this.accNo = accNo;
        this.department = department;
        this.total = total;
        this.currency = currency;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getInvoiceSup() {
        return invoiceSup;
    }

    public void setInvoiceSup(String InvoiceSup) {
        this.invoiceSup = InvoiceSup;
    }

    public int getAccNo() {
        return accNo;
    }

    public void setAccNo(int accNo) {
        this.accNo = accNo;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
    
    
    
}
