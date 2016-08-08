/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.entity;

/**
 *
 * @author chonnasith
 */
public class BookingAirSummaryView {
    private String refno;
    private String refdate;
    private String agent;
    private String leader;
    private String pax;
    private String pnr;
    private String dept;
    private String arrv;
    private String departdate;
    private String flight;
    private String invoice;
    private String receipt;
    private String billid;

    public String getRefno() {
        return refno;
    }

    public void setRefno(String refno) {
        this.refno = refno;
    }

    public String getRefdate() {
        return refdate;
    }

    public void setRefdate(String refdate) {
        this.refdate = refdate;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getPax() {
        return pax;
    }

    public void setPax(String pax) {
        this.pax = pax;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getArrv() {
        return arrv;
    }

    public void setArrv(String arrv) {
        this.arrv = arrv;
    }

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getDepartdate() {
        return departdate;
    }

    public void setDepartdate(String departdate) {
        this.departdate = departdate;
    }

    public String getBillid() {
        return billid;
    }

    public void setBillid(String billid) {
        this.billid = billid;
    }
}
