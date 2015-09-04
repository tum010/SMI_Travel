/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.report.model;

import java.util.Date;

/**
 *
 * @author Kanokporn
 */
public class InvoiceSummary {
    private Date invfrom;
    private Date invto;
    private String invtype;
    private String department;
    private String invno;
    private Date invdate;
    private String to;
    private String invname;
    private String termpay;
    private String detail;
    private Double gross;
    private Double vat;
    private Double amount;
    private String amountcur;
    private String staff;
    private String status;
    private String invdepartment;
//    private Double sumnet;
//    private Double sumvat;
//    private Double sumamount;
//    private Double sumprofit;
    private String username;
    private String systemdate;

    public void setSystemdate(String systemdate) {
        this.systemdate = systemdate;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public Double getSumvat() {
//        return sumvat;
//    }

    public String getSystemdate() {
        return systemdate;
    }

    public String getTermpay() {
        return termpay;
    }

    public String getTo() {
        return to;
    }

    public String getUsername() {
        return username;
    }

    public Double getVat() {
        return vat;
    }
   
   public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setAmountcur(String amountcur) {
        this.amountcur = amountcur;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setGross(Double gross) {
        this.gross = gross;
    }

    public void setInvdate(Date invdate) {
        this.invdate = invdate;
    }

    public void setInvdepartment(String invdepartment) {
        this.invdepartment = invdepartment;
    }

    public void setInvname(String invname) {
        this.invname = invname;
    }

    public void setInvno(String invno) {
        this.invno = invno;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public void setStatus(String status) {
        this.status = status;
    }

//    public void setSumamount(Double sumamount) {
//        this.sumamount = sumamount;
//    }
//
//    public void setSumnet(Double sumnet) {
//        this.sumnet = sumnet;
//    }
//
//    public void setSumprofit(Double sumprofit) {
//        this.sumprofit = sumprofit;
//    }
//
//    public void setSumvat(Double sumvat) {
//        this.sumvat = sumvat;
//    }

    public void setTermpay(String termpay) {
        this.termpay = termpay;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

    public Double getAmount() {
        return amount;
    }

    public String getAmountcur() {
        return amountcur;
    }

    public String getDetail() {
        return detail;
    }

    public Double getGross() {
        return gross;
    }

    public Date getInvdate() {
        return invdate;
    }

    public String getInvdepartment() {
        return invdepartment;
    }

    public String getInvname() {
        return invname;
    }

    public String getInvno() {
        return invno;
    }

    public String getStaff() {
        return staff;
    }

    public String getStatus() {
        return status;
    }

//    public Double getSumamount() {
//        return sumamount;
//    }
//
//    public Double getSumnet() {
//        return sumnet;
//    }
//
//    public Double getSumprofit() {
//        return sumprofit;
//    }

    public void setDepartment(String department) {
        this.department = department;
    }

 

    public void setInvto(Date invto) {
        this.invto = invto;
    }

    public void setInvtype(String invtype) {
        this.invtype = invtype;
    }

    public String getDepartment() {
        return department;
    }


    public Date getInvto() {
        return invto;
    }

    public String getInvtype() {
        return invtype;
    }

    /**
     * @return the invfrom
     */
    public Date getInvfrom() {
        return invfrom;
    }

    /**
     * @param invfrom the invfrom to set
     */
    public void setInvfrom(Date invfrom) {
        this.invfrom = invfrom;
    }

}