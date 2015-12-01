/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.entity;

import java.math.BigDecimal;

/**
 *
 * @author Kanokporn
 */
public class OutboundProductSummaryExcel {
    private String saledate;	
    private String recordno;
    private String travoxno;
    private String passtype;
    private String passno;
    private String dulation;
    private String invno;
    private String customername;
    private Integer paxad;
    private Integer paxch;
    private Integer paxin;
    private BigDecimal totalnettadult;
    private BigDecimal totalnettchild;
    private BigDecimal totalnettinfant;
    private BigDecimal totalsaleadult;
    private BigDecimal totalsalechild;
    private BigDecimal totalsaleinfant;
    private BigDecimal profittotal;
    private String  payby;
    private String  datetrsf;
    private String seller;
    
    //Header
    private String productname;
    private String productnamepage;
    private String saledatepage;
    private String salebypage;
    private String paybypage;
    private String bankpage;
    private String statuspage;

    public String getSaledate() {
        return saledate;
    }

    public void setSaledate(String saledate) {
        this.saledate = saledate;
    }

    public String getRecordno() {
        return recordno;
    }

    public void setRecordno(String recordno) {
        this.recordno = recordno;
    }

    public String getTravoxno() {
        return travoxno;
    }

    public void setTravoxno(String travoxno) {
        this.travoxno = travoxno;
    }

    public String getPasstype() {
        return passtype;
    }

    public void setPasstype(String passtype) {
        this.passtype = passtype;
    }

    public String getPassno() {
        return passno;
    }

    public void setPassno(String passno) {
        this.passno = passno;
    }

    public String getDulation() {
        return dulation;
    }

    public void setDulation(String dulation) {
        this.dulation = dulation;
    }

    public String getInvno() {
        return invno;
    }

    public void setInvno(String invno) {
        this.invno = invno;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public Integer getPaxad() {
        return paxad;
    }

    public void setPaxad(Integer paxad) {
        this.paxad = paxad;
    }

    public Integer getPaxch() {
        return paxch;
    }

    public void setPaxch(Integer paxch) {
        this.paxch = paxch;
    }

    public Integer getPaxin() {
        return paxin;
    }

    public void setPaxin(Integer paxin) {
        this.paxin = paxin;
    }

    public BigDecimal getTotalnettadult() {
        return totalnettadult;
    }

    public void setTotalnettadult(BigDecimal totalnettadult) {
        this.totalnettadult = totalnettadult;
    }

    public BigDecimal getTotalnettchild() {
        return totalnettchild;
    }

    public void setTotalnettchild(BigDecimal totalnettchild) {
        this.totalnettchild = totalnettchild;
    }

    public BigDecimal getTotalnettinfant() {
        return totalnettinfant;
    }

    public void setTotalnettinfant(BigDecimal totalnettinfant) {
        this.totalnettinfant = totalnettinfant;
    }

    public BigDecimal getTotalsaleadult() {
        return totalsaleadult;
    }

    public void setTotalsaleadult(BigDecimal totalsaleadult) {
        this.totalsaleadult = totalsaleadult;
    }

    public BigDecimal getTotalsalechild() {
        return totalsalechild;
    }

    public void setTotalsalechild(BigDecimal totalsalechild) {
        this.totalsalechild = totalsalechild;
    }

    public BigDecimal getTotalsaleinfant() {
        return totalsaleinfant;
    }

    public void setTotalsaleinfant(BigDecimal totalsaleinfant) {
        this.totalsaleinfant = totalsaleinfant;
    }

    public BigDecimal getProfittotal() {
        return profittotal;
    }

    public void setProfittotal(BigDecimal profittotal) {
        this.profittotal = profittotal;
    }

    public String getPayby() {
        return payby;
    }

    public void setPayby(String payby) {
        this.payby = payby;
    }

    public String getDatetrsf() {
        return datetrsf;
    }

    public void setDatetrsf(String datetrsf) {
        this.datetrsf = datetrsf;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductnamepage() {
        return productnamepage;
    }

    public void setProductnamepage(String productnamepage) {
        this.productnamepage = productnamepage;
    }

    public String getSaledatepage() {
        return saledatepage;
    }

    public void setSaledatepage(String saledatepage) {
        this.saledatepage = saledatepage;
    }

    public String getSalebypage() {
        return salebypage;
    }

    public void setSalebypage(String salebypage) {
        this.salebypage = salebypage;
    }

    public String getPaybypage() {
        return paybypage;
    }

    public void setPaybypage(String paybypage) {
        this.paybypage = paybypage;
    }

    public String getBankpage() {
        return bankpage;
    }

    public void setBankpage(String bankpage) {
        this.bankpage = bankpage;
    }

    public String getStatuspage() {
        return statuspage;
    }

    public void setStatuspage(String statuspage) {
        this.statuspage = statuspage;
    }

}
