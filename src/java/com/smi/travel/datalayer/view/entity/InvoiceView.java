/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.entity;

import java.math.BigDecimal;

/**
 *
 * @author Surachai
 */
public class InvoiceView {
    private String InvoiceId;
    private String InvoiceNo;
    private String Department;
    private String Type;
    private String InvoiceDate;
    private String Name;
    private String Address;
    private String TermPayName;
//    private BigDecimal TotalCost;
    private BigDecimal TotalPrice; // Amount
    private String Currency;
    

    public String getInvoiceId() {
        return InvoiceId;
    }

    public void setInvoiceId(String InvoiceId) {
        this.InvoiceId = InvoiceId;
    }

    public String getInvoiceNo() {
        return InvoiceNo;
    }

    public void setInvoiceNo(String InvoiceNo) {
        this.InvoiceNo = InvoiceNo;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String Department) {
        this.Department = Department;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getInvoiceDate() {
        return InvoiceDate;
    }

    public void setInvoiceDate(String InvoiceDate) {
        this.InvoiceDate = InvoiceDate;
    }

    

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getTermPayName() {
        return TermPayName;
    }

    public void setTermPayName(String TermPayName) {
        this.TermPayName = TermPayName;
    }
//
//    public BigDecimal getTotalCost() {
//        return TotalCost;
//    }
//
//    public void setTotalCost(BigDecimal TotalCost) {
//        this.TotalCost = TotalCost;
//    }

    public BigDecimal getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(BigDecimal TotalPrice) {
        this.TotalPrice = TotalPrice;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String Currency) {
        this.Currency = Currency;
    }
}