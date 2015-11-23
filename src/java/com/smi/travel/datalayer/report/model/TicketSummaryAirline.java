/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.report.model;

import java.math.BigDecimal;

/**
 *
 * @author Kanokporn
 */
public class TicketSummaryAirline {
    private String billingname;
    private String airline;
    private BigDecimal ticketnum;
    private BigDecimal totalsalefare;
    private BigDecimal totalnetfare;
    private BigDecimal totaltax;
    private BigDecimal profit;
    private BigDecimal profitavg;

    public String getBillingname() {
        return billingname;
    }

    public void setBillingname(String billingname) {
        this.billingname = billingname;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public BigDecimal getTicketnum() {
        return ticketnum;
    }

    public void setTicketnum(BigDecimal ticketnum) {
        this.ticketnum = ticketnum;
    }

    public BigDecimal getTotalsalefare() {
        return totalsalefare;
    }

    public void setTotalsalefare(BigDecimal totalsalefare) {
        this.totalsalefare = totalsalefare;
    }

    public BigDecimal getTotalnetfare() {
        return totalnetfare;
    }

    public void setTotalnetfare(BigDecimal totalnetfare) {
        this.totalnetfare = totalnetfare;
    }

    public BigDecimal getTotaltax() {
        return totaltax;
    }

    public void setTotaltax(BigDecimal totaltax) {
        this.totaltax = totaltax;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public BigDecimal getProfitavg() {
        return profitavg;
    }

    public void setProfitavg(BigDecimal profitavg) {
        this.profitavg = profitavg;
    }
    
}
