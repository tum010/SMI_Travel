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
public class ReceiveList {
    private  Date systemdate;
    private  String printby;
    private  Date datefrom;
    private  Date dateto;
    private  String department;
    private  String recno;
    private  Date recdate;
    private  Date recrev;
    private  String recto;
    private  String recname;
    private  String detail;
    private  String invno;
    private  Double invamount;
    private  Double diff;
    private  Double recamount;
    private  String payby;
    private  Double cash;
    private  Double chq;
    private  Double creditcard;
    private  Double tranfer;
    private  Double sumamount;
    private  Double sumwait;
    private  Double sumvoid;
    private  Double suminvamount;
    private  Double sumdiffamount;

    public void setCash(Double cash) {
        this.cash = cash;
    }

    public void setChq(Double chq) {
        this.chq = chq;
    }

    public void setSumamount(Double sumamount) {
        this.sumamount = sumamount;
    }

    public void setCreditcard(Double creditcard) {
        this.creditcard = creditcard;
    }

    public void setDatefrom(Date datefrom) {
        this.datefrom = datefrom;
    }

    public void setDateto(Date dateto) {
        this.dateto = dateto;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setDiff(Double diff) {
        this.diff = diff;
    }

    public void setInvamount(Double invamount) {
        this.invamount = invamount;
    }

    public void setInvno(String invno) {
        this.invno = invno;
    }

    public void setPayby(String payby) {
        this.payby = payby;
    }

    public void setPrintby(String printby) {
        this.printby = printby;
    }

    public void setRecamount(Double recamount) {
        this.recamount = recamount;
    }

    public void setRecdate(Date recdate) {
        this.recdate = recdate;
    }

    public void setRecname(String recname) {
        this.recname = recname;
    }

    public void setRecno(String recno) {
        this.recno = recno;
    }

    public void setRecrev(Date recrev) {
        this.recrev = recrev;
    }

    public void setRecto(String recto) {
        this.recto = recto;
    }

    public void setSumdiffamount(Double sumdiffamount) {
        this.sumdiffamount = sumdiffamount;
    }

    public void setSuminvamount(Double suminvamount) {
        this.suminvamount = suminvamount;
    }

    public void setSumvoid(Double sumvoid) {
        this.sumvoid = sumvoid;
    }

    public void setSumwait(Double sumwait) {
        this.sumwait = sumwait;
    }

    public void setSystemdate(Date systemdate) {
        this.systemdate = systemdate;
    }

    public void setTranfer(Double tranfer) {
        this.tranfer = tranfer;
    }

    public Double getCash() {
        return cash;
    }

    public Double getChq() {
        return chq;
    }

    public Double getCreditcard() {
        return creditcard;
    }

    public Date getDatefrom() {
        return datefrom;
    }

    public Date getDateto() {
        return dateto;
    }

    public String getDepartment() {
        return department;
    }

    public String getDetail() {
        return detail;
    }

    public Double getDiff() {
        return diff;
    }

    public Double getInvamount() {
        return invamount;
    }

    public String getInvno() {
        return invno;
    }

    public String getPayby() {
        return payby;
    }

    public String getPrintby() {
        return printby;
    }

    public Double getRecamount() {
        return recamount;
    }

    public Date getRecdate() {
        return recdate;
    }

    public String getRecname() {
        return recname;
    }

    public String getRecno() {
        return recno;
    }

    public Date getRecrev() {
        return recrev;
    }

    public String getRecto() {
        return recto;
    }

    public Double getSumamount() {
        return sumamount;
    }

    public Double getSumdiffamount() {
        return sumdiffamount;
    }

    public Double getSuminvamount() {
        return suminvamount;
    }

    public Double getSumvoid() {
        return sumvoid;
    }

    public Double getSumwait() {
        return sumwait;
    }

    public Date getSystemdate() {
        return systemdate;
    }

    public Double getTranfer() {
        return tranfer;
    }
}