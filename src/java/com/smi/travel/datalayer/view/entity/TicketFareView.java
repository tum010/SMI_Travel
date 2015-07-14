/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.entity;

import java.math.BigDecimal;

/**
 *
 * @author Surachai
 */
public class TicketFareView {
    private String Type;
    private String Buy;
    private String Airline;
    private String TicketNo;
    private String IssueDate;
    private String InvoiceNo;
    private String Department;
    private BigDecimal Fare;
    private BigDecimal Tax;
    private BigDecimal TicketCommission;
    private BigDecimal AgentCommission;
    private BigDecimal DiffVat;
    
    public TicketFareView(){
        
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getBuy() {
        return Buy;
    }

    public void setBuy(String Buy) {
        this.Buy = Buy;
    }

    public String getAirline() {
        return Airline;
    }

    public void setAirline(String Airline) {
        this.Airline = Airline;
    }

    public String getTicketNo() {
        return TicketNo;
    }

    public void setTicketNo(String TicketNo) {
        this.TicketNo = TicketNo;
    }

    public String getIssueDate() {
        return IssueDate;
    }

    public void setIssueDate(String IssueDate) {
        this.IssueDate = IssueDate;
    }

    public String getInvoiceNo() {
        return InvoiceNo;
    }

    public void setInvoiceNo(String InvoiceNo) {
        this.InvoiceNo = InvoiceNo;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String Department) {
        this.Department = Department;
    }

    public BigDecimal getFare() {
        return Fare;
    }

    public void setFare(BigDecimal Fare) {
        this.Fare = Fare;
    }

    public BigDecimal getTax() {
        return Tax;
    }

    public void setTax(BigDecimal Tax) {
        this.Tax = Tax;
    }

    public BigDecimal getTicketCommission() {
        return TicketCommission;
    }

    public void setTicketCommission(BigDecimal TicketCommission) {
        this.TicketCommission = TicketCommission;
    }

    public BigDecimal getAgentCommission() {
        return AgentCommission;
    }

    public void setAgentCommission(BigDecimal AgentCommission) {
        this.AgentCommission = AgentCommission;
    }

    public BigDecimal getDiffVat() {
        return DiffVat;
    }

    public void setDiffVat(BigDecimal DiffVat) {
        this.DiffVat = DiffVat;
    }
    
    
    
    
    
}
