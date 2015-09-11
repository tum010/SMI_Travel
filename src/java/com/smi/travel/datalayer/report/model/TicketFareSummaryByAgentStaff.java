/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.report.model;

/**
 *
 * @author chonnasith
 */
public class TicketFareSummaryByAgentStaff {
    private String agentname;
    private String agentid;
    private String owner;
    private String invamount;
    private String department;
    private String ticcom;
    private String saleprice;
    private String agentcom;
    private String profit;
    private String pax;

    public String getAgentname() {
        return agentname;
    }

    public void setAgentname(String agentname) {
        this.agentname = agentname;
    }

    public String getAgentid() {
        return agentid;
    }

    public void setAgentid(String agentid) {
        this.agentid = agentid;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getInvamount() {
        return invamount;
    }

    public void setInvamount(String invamount) {
        this.invamount = invamount;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTiccom() {
        return ticcom;
    }

    public void setTiccom(String ticcom) {
        this.ticcom = ticcom;
    }

    public String getSaleprice() {
        return saleprice;
    }

    public void setSaleprice(String saleprice) {
        this.saleprice = saleprice;
    }

    public String getAgentcom() {
        return agentcom;
    }

    public void setAgentcom(String agentcom) {
        this.agentcom = agentcom;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public String getPax() {
        return pax;
    }

    public void setPax(String pax) {
        this.pax = pax;
    }
}
