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
 * @author Surachai
 */
public class TicketFareView {
    private String id;
    private String type;
    private String buy;
    private String rounting;
    private String airline;
    private String ticketNo;
    private Date issueDate;
    private String invoiceNo;
    private String department;
    private BigDecimal fare;
    private BigDecimal tax;
    private BigDecimal ticketCommission;
    private BigDecimal agentCommission;
    private BigDecimal diffVat;
    
    public TicketFareView(){
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getRounting() {
        return rounting;
    }

    public void setRounting(String rounting) {
        this.rounting = rounting;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public BigDecimal getFare() {
        return fare;
    }

    public void setFare(BigDecimal fare) {
        this.fare = fare;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTicketCommission() {
        return ticketCommission;
    }

    public void setTicketCommission(BigDecimal ticketCommission) {
        this.ticketCommission = ticketCommission;
    }

    public BigDecimal getAgentCommission() {
        return agentCommission;
    }

    public void setAgentCommission(BigDecimal agentCommission) {
        this.agentCommission = agentCommission;
    }

    public BigDecimal getDiffVat() {
        return diffVat;
    }

    public void setDiffVat(BigDecimal diffVat) {
        this.diffVat = diffVat;
    }


    
    
    
}
