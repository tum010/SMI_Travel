/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.report.model;

import java.math.BigDecimal;

/**
 *
 * @author Surachai
 */
public class InvoiceMonthly {
    private String invname;
    private String invno;
    private String invdate;
    private String detail;
    private BigDecimal thb;
    private BigDecimal jpy;
    private BigDecimal usd;
    private String department;
    private String recno;
    private BigDecimal recamt;
    private String type;
    private String payment;
    private String accno;
    private String billfrom;
    private String billto;
    private String systemdate;
    private String headdepartment;
    
    

    public String getInvname() {
        return invname;
    }

    public void setInvname(String invname) {
        this.invname = invname;
    }

    public String getInvno() {
        return invno;
    }

    public void setInvno(String invno) {
        this.invno = invno;
    }

    public String getInvdate() {
        return invdate;
    }

    public void setInvdate(String invdate) {
        this.invdate = invdate;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRecno() {
        return recno;
    }

    public void setRecno(String recno) {
        this.recno = recno;
    }

    public BigDecimal getThb() {
        return thb;
    }

    public void setThb(BigDecimal thb) {
        this.thb = thb;
    }

    public BigDecimal getJpy() {
        return jpy;
    }

    public void setJpy(BigDecimal jpy) {
        this.jpy = jpy;
    }

    public BigDecimal getUsd() {
        return usd;
    }

    public void setUsd(BigDecimal usd) {
        this.usd = usd;
    }

    public BigDecimal getRecamt() {
        return recamt;
    }

    public void setRecamt(BigDecimal recamt) {
        this.recamt = recamt;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getBillfrom() {
        return billfrom;
    }

    public void setBillfrom(String billfrom) {
        this.billfrom = billfrom;
    }

    public String getBillto() {
        return billto;
    }

    public void setBillto(String billto) {
        this.billto = billto;
    }

    public String getSystemdate() {
        return systemdate;
    }

    public void setSystemdate(String systemdate) {
        this.systemdate = systemdate;
    }

    public String getHeaddepartment() {
        return headdepartment;
    }

    public void setHeaddepartment(String headdepartment) {
        this.headdepartment = headdepartment;
    }

    
    
    
    
}
