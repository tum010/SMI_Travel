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
public class TaxInvoiceView {
    private String taxId;
    private String taxNo;
    private String taxDate;
    private String taxTo;
    private String name;
    private String address;
    private String detail;
    private String InvoiceNo;
    private String ReceiptNo;
    private BigDecimal TotalGross;
    private BigDecimal TotalAmount;
    private BigDecimal Totalvat;
    private String status;
    private String deparement;

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public String getTaxDate() {
        return taxDate;
    }

    public void setTaxDate(String taxDate) {
        this.taxDate = taxDate;
    }

    public String getTaxTo() {
        return taxTo;
    }

    public void setTaxTo(String taxTo) {
        this.taxTo = taxTo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getInvoiceNo() {
        return InvoiceNo;
    }

    public void setInvoiceNo(String InvoiceNo) {
        this.InvoiceNo = InvoiceNo;
    }

    public String getReceiptNo() {
        return ReceiptNo;
    }

    public void setReceiptNo(String ReceiptNo) {
        this.ReceiptNo = ReceiptNo;
    }

    public BigDecimal getTotalGross() {
        return TotalGross;
    }

    public void setTotalGross(BigDecimal TotalGross) {
        this.TotalGross = TotalGross;
    }

    public BigDecimal getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(BigDecimal TotalAmount) {
        this.TotalAmount = TotalAmount;
    }

    public BigDecimal getTotalvat() {
        return Totalvat;
    }

    public void setTotalvat(BigDecimal Totalvat) {
        this.Totalvat = Totalvat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeparement() {
        return deparement;
    }

    public void setDeparement(String deparement) {
        this.deparement = deparement;
    }

    
    
    
    
}
