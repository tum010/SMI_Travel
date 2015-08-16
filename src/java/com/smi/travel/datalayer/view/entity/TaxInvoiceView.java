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
    private String TaxId;
    private String TaxNo;
    private String TaxDate;
    private String TaxTo;
    private String Name;
    private String Address;
    private String detail;
    private String InvoiceNo;
    private String ReceiptNo;
    private BigDecimal TotalGross;
    private BigDecimal TotalAmount;
    private BigDecimal Totalvat;

    public String getTaxId() {
        return TaxId;
    }

    public void setTaxId(String TaxId) {
        this.TaxId = TaxId;
    }

    public String getTaxNo() {
        return TaxNo;
    }

    public void setTaxNo(String TaxNo) {
        this.TaxNo = TaxNo;
    }

    public String getTaxDate() {
        return TaxDate;
    }

    public void setTaxDate(String TaxDate) {
        this.TaxDate = TaxDate;
    }

    public String getTaxTo() {
        return TaxTo;
    }

    public void setTaxTo(String TaxTo) {
        this.TaxTo = TaxTo;
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
    
    
    
    
    
    
}
