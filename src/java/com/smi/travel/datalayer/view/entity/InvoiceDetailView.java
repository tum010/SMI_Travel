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
 * @author Jittima
 */
public class InvoiceDetailView {
    private String id;
    private String invNo;
    private Date invDate;
    private String department;
    private Date dueDate;
    private String staffName;
    private BigDecimal invAmount;
    private String credit;
    private String owner;
    private String routing;
    private String invoiceId;
    private BigDecimal amountInvoice;
    private String creditValue;
    private String invTo;
    private String invName;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInvNo() {
        return invNo;
    }

    public void setInvNo(String invNo) {
        this.invNo = invNo;
    }

    public Date getInvDate() {
        return invDate;
    }

    public void setInvDate(Date invDate) {
        this.invDate = invDate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public BigDecimal getInvAmount() {
        return invAmount;
    }

    public void setInvAmount(BigDecimal invAmount) {
        this.invAmount = invAmount;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getRouting() {
        return routing;
    }

    public void setRouting(String routing) {
        this.routing = routing;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public BigDecimal getAmountInvoice() {
        return amountInvoice;
    }

    public void setAmountInvoice(BigDecimal amountInvoice) {
        this.amountInvoice = amountInvoice;
    }

    public String getCreditValue() {
        return creditValue;
    }

    public void setCreditValue(String creditValue) {
        this.creditValue = creditValue;
    }     

    public String getInvTo() {
        return invTo;
    }

    public void setInvTo(String invTo) {
        this.invTo = invTo;
    }

    public String getInvName() {
        return invName;
    }

    public void setInvName(String invName) {
        this.invName = invName;
    }
}
