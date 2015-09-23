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
 * @author Kanokporn
 */
public class SummaryAirline {
    private Date invoicedatefrom ;
    private Date invoicedateto;
    private String printby;
    private String typerouting;
    private String air;
    private String agentname;
    private String termpay;
    private String printon;
    private String routingdetail;
    private String passenger;
    private String salestaff;
    private String department;
    
    private String routing;
    private Integer pax;
    private BigDecimal netsale;
    private BigDecimal tax;
    private BigDecimal ins;
    private BigDecimal comms;
    private BigDecimal amountwendy;
    private BigDecimal amountinbound;
    private BigDecimal diff;

    /**
     * @return the invoicedatefrom
     */
    public Date getInvoicedatefrom() {
        return invoicedatefrom;
    }

    /**
     * @param invoicedatefrom the invoicedatefrom to set
     */
    public void setInvoicedatefrom(Date invoicedatefrom) {
        this.invoicedatefrom = invoicedatefrom;
    }

    /**
     * @return the invoicedateto
     */
    public Date getInvoicedateto() {
        return invoicedateto;
    }

    /**
     * @param invoicedateto the invoicedateto to set
     */
    public void setInvoicedateto(Date invoicedateto) {
        this.invoicedateto = invoicedateto;
    }

    /**
     * @return the printby
     */
    public String getPrintby() {
        return printby;
    }

    /**
     * @param printby the printby to set
     */
    public void setPrintby(String printby) {
        this.printby = printby;
    }

    /**
     * @return the typerouting
     */
    public String getTyperouting() {
        return typerouting;
    }

    /**
     * @param typerouting the typerouting to set
     */
    public void setTyperouting(String typerouting) {
        this.typerouting = typerouting;
    }

    /**
     * @return the air
     */
    public String getAir() {
        return air;
    }

    /**
     * @param air the air to set
     */
    public void setAir(String air) {
        this.air = air;
    }

    /**
     * @return the agentname
     */
    public String getAgentname() {
        return agentname;
    }

    /**
     * @param agentname the agentname to set
     */
    public void setAgentname(String agentname) {
        this.agentname = agentname;
    }

    /**
     * @return the termpay
     */
    public String getTermpay() {
        return termpay;
    }

    /**
     * @param termpay the termpay to set
     */
    public void setTermpay(String termpay) {
        this.termpay = termpay;
    }

    /**
     * @return the printon
     */
    public String getPrinton() {
        return printon;
    }

    /**
     * @param printon the printon to set
     */
    public void setPrinton(String printon) {
        this.printon = printon;
    }

    /**
     * @return the routingdetail
     */
    public String getRoutingdetail() {
        return routingdetail;
    }

    /**
     * @param routingdetail the routingdetail to set
     */
    public void setRoutingdetail(String routingdetail) {
        this.routingdetail = routingdetail;
    }

    /**
     * @return the passenger
     */
    public String getPassenger() {
        return passenger;
    }

    /**
     * @param passenger the passenger to set
     */
    public void setPassenger(String passenger) {
        this.passenger = passenger;
    }

    /**
     * @return the salestaff
     */
    public String getSalestaff() {
        return salestaff;
    }

    /**
     * @param salestaff the salestaff to set
     */
    public void setSalestaff(String salestaff) {
        this.salestaff = salestaff;
    }

    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * @return the routing
     */
    public String getRouting() {
        return routing;
    }

    /**
     * @param routing the routing to set
     */
    public void setRouting(String routing) {
        this.routing = routing;
    }

    /**
     * @return the pax
     */
    public Integer getPax() {
        return pax;
    }

    /**
     * @param pax the pax to set
     */
    public void setPax(Integer pax) {
        this.pax = pax;
    }

    /**
     * @return the netsale
     */
    public BigDecimal getNetsale() {
        return netsale;
    }

    /**
     * @param netsale the netsale to set
     */
    public void setNetsale(BigDecimal netsale) {
        this.netsale = netsale;
    }

    /**
     * @return the tax
     */
    public BigDecimal getTax() {
        return tax;
    }

    /**
     * @param tax the tax to set
     */
    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    /**
     * @return the ins
     */
    public BigDecimal getIns() {
        return ins;
    }

    /**
     * @param ins the ins to set
     */
    public void setIns(BigDecimal ins) {
        this.ins = ins;
    }

    /**
     * @return the comms
     */
    public BigDecimal getComms() {
        return comms;
    }

    /**
     * @param comms the comms to set
     */
    public void setComms(BigDecimal comms) {
        this.comms = comms;
    }

    /**
     * @return the amountwendy
     */
    public BigDecimal getAmountwendy() {
        return amountwendy;
    }

    /**
     * @param amountwendy the amountwendy to set
     */
    public void setAmountwendy(BigDecimal amountwendy) {
        this.amountwendy = amountwendy;
    }

    /**
     * @return the amountinbound
     */
    public BigDecimal getAmountinbound() {
        return amountinbound;
    }

    /**
     * @param amountinbound the amountinbound to set
     */
    public void setAmountinbound(BigDecimal amountinbound) {
        this.amountinbound = amountinbound;
    }

    /**
     * @return the diff
     */
    public BigDecimal getDiff() {
        return diff;
    }

    /**
     * @param diff the diff to set
     */
    public void setDiff(BigDecimal diff) {
        this.diff = diff;
    }

   
}
