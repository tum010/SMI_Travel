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
public class TicketSummaryAirlineView {
    //header
    private String headerdatefrom ;
    private String headerdateto;
    private String headerprintby;
    private String headertyperouting;
    private String headerair;
    private String headeragentname;
    private String headertermpay;
    private String headerprinton;
    private String headerroutingdetail;
    private String headerpassenger;
    private String headersalestaff;
    private String headerdepartment;
    
    //routing
    private String routing;
    private String pax;
    private String netsales;
    private String tax;
    private String ins;
    private String comms;
    private String amountwendy;
    private String amountinbound;
    private String diff;
    
    //pax
    private String paymenttype;
    private String typerouting;
    private String amountoutbound;

    //detail
    private String invno;
    private String invdate;
    private String department;
    private String staff;
    private String termpay;
    private String passenger;
    private String typepayment;
    private String air;
    private String ticketno;
    private String issuedate;
    private String remark;
    
    public String getHeaderdatefrom() {
        return headerdatefrom;
    }

    public void setHeaderdatefrom(String headerdatefrom) {
        this.headerdatefrom = headerdatefrom;
    }

    public String getHeaderdateto() {
        return headerdateto;
    }

    public void setHeaderdateto(String headerdateto) {
        this.headerdateto = headerdateto;
    }

    public String getHeaderprintby() {
        return headerprintby;
    }

    public void setHeaderprintby(String headerprintby) {
        this.headerprintby = headerprintby;
    }

    public String getHeadertyperouting() {
        return headertyperouting;
    }

    public void setHeadertyperouting(String headertyperouting) {
        this.headertyperouting = headertyperouting;
    }

    public String getHeaderair() {
        return headerair;
    }

    public void setHeaderair(String headerair) {
        this.headerair = headerair;
    }

    public String getHeaderagentname() {
        return headeragentname;
    }

    public void setHeaderagentname(String headeragentname) {
        this.headeragentname = headeragentname;
    }

    public String getHeadertermpay() {
        return headertermpay;
    }

    public void setHeadertermpay(String headertermpay) {
        this.headertermpay = headertermpay;
    }

    public String getHeaderprinton() {
        return headerprinton;
    }

    public void setHeaderprinton(String headerprinton) {
        this.headerprinton = headerprinton;
    }

    public String getHeaderroutingdetail() {
        return headerroutingdetail;
    }

    public void setHeaderroutingdetail(String headerroutingdetail) {
        this.headerroutingdetail = headerroutingdetail;
    }

    public String getHeaderpassenger() {
        return headerpassenger;
    }

    public void setHeaderpassenger(String headerpassenger) {
        this.headerpassenger = headerpassenger;
    }

    public String getHeadersalestaff() {
        return headersalestaff;
    }

    public void setHeadersalestaff(String headersalestaff) {
        this.headersalestaff = headersalestaff;
    }

    public String getHeaderdepartment() {
        return headerdepartment;
    }

    public void setHeaderdepartment(String headerdepartment) {
        this.headerdepartment = headerdepartment;
    }

    public String getRouting() {
        return routing;
    }

    public void setRouting(String routing) {
        this.routing = routing;
    }

    public String getPax() {
        return pax;
    }

    public void setPax(String pax) {
        this.pax = pax;
    }

    public String getNetsales() {
        return netsales;
    }

    public void setNetsales(String netsales) {
        this.netsales = netsales;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getIns() {
        return ins;
    }

    public void setIns(String ins) {
        this.ins = ins;
    }

    public String getComms() {
        return comms;
    }

    public void setComms(String comms) {
        this.comms = comms;
    }

    public String getAmountwendy() {
        return amountwendy;
    }

    public void setAmountwendy(String amountwendy) {
        this.amountwendy = amountwendy;
    }

    public String getAmountinbound() {
        return amountinbound;
    }

    public void setAmountinbound(String amountinbound) {
        this.amountinbound = amountinbound;
    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }

    public String getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
    }

    public String getTyperouting() {
        return typerouting;
    }

    public void setTyperouting(String typerouting) {
        this.typerouting = typerouting;
    }

    public String getAmountoutbound() {
        return amountoutbound;
    }

    public void setAmountoutbound(String amountoutbound) {
        this.amountoutbound = amountoutbound;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public String getTermpay() {
        return termpay;
    }

    public void setTermpay(String termpay) {
        this.termpay = termpay;
    }

    public String getPassenger() {
        return passenger;
    }

    public void setPassenger(String passenger) {
        this.passenger = passenger;
    }

    public String getTypepayment() {
        return typepayment;
    }

    public void setTypepayment(String typepayment) {
        this.typepayment = typepayment;
    }

    public String getAir() {
        return air;
    }

    public void setAir(String air) {
        this.air = air;
    }

    public String getTicketno() {
        return ticketno;
    }

    public void setTicketno(String ticketno) {
        this.ticketno = ticketno;
    }

    public String getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(String issuedate) {
        this.issuedate = issuedate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
