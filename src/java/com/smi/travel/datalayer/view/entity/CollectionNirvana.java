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
public class CollectionNirvana {
    private String invno;
    private String invto;
    private String arcode;
    private String acccode;
    private String invoiceamount;
    private BigDecimal invamount; //sum
    private String recno;
    private BigDecimal recamount; //sum
    private String cur;
    private BigDecimal diff;
    private String collectionStatus;
    private String department;
    private String type;
    private Date invdate;
    
    private String systemdate;
    private String user;
    private String from;
    private String to;
    private String no;
    private String headerdepartment;
//    private String status;
    
    
    public String getInvno() {
        return invno;
    }

    public void setInvno(String invno) {
        this.invno = invno;
    }

    public String getInvto() {
        return invto;
    }

    public void setInvto(String invto) {
        this.invto = invto;
    }

    public String getArcode() {
        return arcode;
    }

    public void setArcode(String arcode) {
        this.arcode = arcode;
    }

    public String getAcccode() {
        return acccode;
    }

    public void setAcccode(String acccode) {
        this.acccode = acccode;
    }

    public BigDecimal getInvamount() {
        return invamount;
    }

    public void setInvamount(BigDecimal invamount) {
        this.invamount = invamount;
    }

    public String getRecno() {
        return recno;
    }

    public void setRecno(String recno) {
        this.recno = recno;
    }

    public BigDecimal getRecamount() {
        return recamount;
    }

    public void setRecamount(BigDecimal recamount) {
        this.recamount = recamount;
    }

    public String getCur() {
        return cur;
    }

    public void setCur(String cur) {
        this.cur = cur;
    }

    public BigDecimal getDiff() {
        return diff;
    }

    public void setDiff(BigDecimal diff) {
        this.diff = diff;
    }

    public String getCollectionStatus() {
        return collectionStatus;
    }

    public void setCollectionStatus(String collectionStatus) {
        this.collectionStatus = collectionStatus;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getInvdate() {
        return invdate;
    }

    public void setInvdate(Date invdate) {
        this.invdate = invdate;
    }

    public String getSystemdate() {
        return systemdate;
    }

    public void setSystemdate(String systemdate) {
        this.systemdate = systemdate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getHeaderdepartment() {
        return headerdepartment;
    }

    public void setHeaderdepartment(String headerdepartment) {
        this.headerdepartment = headerdepartment;
    }

    public String getInvoiceamount() {
        return invoiceamount;
    }

    public void setInvoiceamount(String invoiceamount) {
        this.invoiceamount = invoiceamount;
    }
    
    
}
