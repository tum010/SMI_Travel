/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.migration;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Jittima
 */
public class ReportTaxInvoice {
    private String id;
    private String taxid;
    private String taxno;
    private String taxdate;
    private String codeap;
    private String description;
    private BigDecimal grossamount;
    private BigDecimal vatamount;
    private BigDecimal amount;
    private String flagtype;
    private String invoicetype;
    private String taxno1;
    private String branch;
    private String branchno;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaxid() {
        return taxid;
    }

    public void setTaxid(String taxid) {
        this.taxid = taxid;
    }

    public String getTaxno() {
        return taxno;
    }

    public void setTaxno(String taxno) {
        this.taxno = taxno;
    }

 

    public String getCodeap() {
        return codeap;
    }

    public void setCodeap(String codeap) {
        this.codeap = codeap;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getGrossamount() {
        return grossamount;
    }

    public void setGrossamount(BigDecimal grossamount) {
        this.grossamount = grossamount;
    }

    public BigDecimal getVatamount() {
        return vatamount;
    }

    public void setVatamount(BigDecimal vatamount) {
        this.vatamount = vatamount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getFlagtype() {
        return flagtype;
    }

    public void setFlagtype(String flagtype) {
        this.flagtype = flagtype;
    }

    public String getInvoicetype() {
        return invoicetype;
    }

    public void setInvoicetype(String invoicetype) {
        this.invoicetype = invoicetype;
    }

    public String getTaxno1() {
        return taxno1;
    }

    public void setTaxno1(String taxno1) {
        this.taxno1 = taxno1;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBranchno() {
        return branchno;
    }

    public void setBranchno(String branchno) {
        this.branchno = branchno;
    }

    public String getTaxdate() {
        return taxdate;
    }

    public void setTaxdate(String taxdate) {
        this.taxdate = taxdate;
    }

}
