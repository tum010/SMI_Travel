/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.report.model;

/**
 *
 * @author Kanokporn
 */
public class TicketSummaryAirline {
    private String billingname;
    private String airline;
    private String ticketnum;
    private String totalsalefare;
    private String totalnetfare;
    private String totaltax;
    private String profit;
    private String profitavg;

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

    public String getTicketnum() {
        return ticketnum;
    }

    public void setTicketnum(String ticketnum) {
        this.ticketnum = ticketnum;
    }

    public String getTotalsalefare() {
        return totalsalefare;
    }

    public void setTotalsalefare(String totalsalefare) {
        this.totalsalefare = totalsalefare;
    }

    public String getTotalnetfare() {
        return totalnetfare;
    }

    public void setTotalnetfare(String totalnetfare) {
        this.totalnetfare = totalnetfare;
    }

    public String getTotaltax() {
        return totaltax;
    }

    public void setTotaltax(String totaltax) {
        this.totaltax = totaltax;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public String getProfitavg() {
        return profitavg;
    }

    public void setProfitavg(String profitavg) {
        this.profitavg = profitavg;
    }
    
}
