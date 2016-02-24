/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.entity;

import java.math.BigDecimal;

/**
 *
 * @author Jittima
 */
public class BookingInvoiceView {
    private String headerowner;
    private String headerinvto;
    private String headerbookingdate;
    private String headerinvdate;
    
    private String refno;
    private String owner;
    private String bookdate;
    private String invno;
    private String invdate;
    private String invto;
    private BigDecimal cost;
    private String currency;
    private String description;

    public String getRefno() {
        return refno;
    }

    public void setRefno(String refno) {
        this.refno = refno;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getBookdate() {
        return bookdate;
    }

    public void setBookdate(String bookdate) {
        this.bookdate = bookdate;
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

    public String getInvto() {
        return invto;
    }

    public void setInvto(String invto) {
        this.invto = invto;
    }



    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getHeaderowner() {
        return headerowner;
    }

    public void setHeaderowner(String headerowner) {
        this.headerowner = headerowner;
    }

    public String getHeaderinvto() {
        return headerinvto;
    }

    public void setHeaderinvto(String headerinvto) {
        this.headerinvto = headerinvto;
    }

    public String getHeaderbookingdate() {
        return headerbookingdate;
    }

    public void setHeaderbookingdate(String headerbookingdate) {
        this.headerbookingdate = headerbookingdate;
    }

    public String getHeaderinvdate() {
        return headerinvdate;
    }

    public void setHeaderinvdate(String headerinvdate) {
        this.headerinvdate = headerinvdate;
    }
    

}
