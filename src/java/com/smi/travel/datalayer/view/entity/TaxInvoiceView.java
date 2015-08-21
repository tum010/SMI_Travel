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
    private String invoiceNo;
    private String receiptNo;
    private BigDecimal totalGross;
    private BigDecimal totalAmount;
    private BigDecimal totalVat;
    private String status;
    private String department;

    public TaxInvoiceView(){
        
    }
    
    public TaxInvoiceView(String taxId, String taxNo, String taxDate, String taxTo, String name, String address, String detail, String invoiceNo, String receiptNo, BigDecimal totalGross, BigDecimal totalAmount, BigDecimal totalVat, String status, String department) {
        this.taxId = taxId;
        this.taxNo = taxNo;
        this.taxDate = taxDate;
        this.taxTo = taxTo;
        this.name = name;
        this.address = address;
        this.detail = detail;
        this.invoiceNo = invoiceNo;
        this.receiptNo = receiptNo;
        this.totalGross = totalGross;
        this.totalAmount = totalAmount;
        this.totalVat = totalVat;
        this.department = department;       
    }

    /**
     * @return the taxId
     */
    public String getTaxId() {
        return taxId;
    }

    /**
     * @param taxId the taxId to set
     */
    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    /**
     * @return the taxNo
     */
    public String getTaxNo() {
        return taxNo;
    }

    /**
     * @param taxNo the taxNo to set
     */
    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    /**
     * @return the taxDate
     */
    public String getTaxDate() {
        return taxDate;
    }

    /**
     * @param taxDate the taxDate to set
     */
    public void setTaxDate(String taxDate) {
        this.taxDate = taxDate;
    }

    /**
     * @return the taxTo
     */
    public String getTaxTo() {
        return taxTo;
    }

    /**
     * @param taxTo the taxTo to set
     */
    public void setTaxTo(String taxTo) {
        this.taxTo = taxTo;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * @param detail the detail to set
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * @return the invoiceNo
     */
    public String getInvoiceNo() {
        return invoiceNo;
    }

    /**
     * @param invoiceNo the invoiceNo to set
     */
    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    /**
     * @return the receiptNo
     */
    public String getReceiptNo() {
        return receiptNo;
    }

    /**
     * @param receiptNo the receiptNo to set
     */
    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    /**
     * @return the totalGross
     */
    public BigDecimal getTotalGross() {
        return totalGross;
    }

    /**
     * @param totalGross the totalGross to set
     */
    public void setTotalGross(BigDecimal totalGross) {
        this.totalGross = totalGross;
    }

    /**
     * @return the totalAmount
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * @param totalAmount the totalAmount to set
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * @return the totalVat
     */
    public BigDecimal getTotalVat() {
        return totalVat;
    }

    /**
     * @param totalVat the totalVat to set
     */
    public void setTotalVat(BigDecimal totalVat) {
        this.totalVat = totalVat;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }
    
   
}
