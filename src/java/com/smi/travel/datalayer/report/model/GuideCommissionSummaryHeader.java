/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.report.model;

/**
 *
 * @author Jittima
 */
public class GuideCommissionSummaryHeader {
    
    private String systemdate;
    private String user;
    private String datefrom;
    private String dateto;
    private String guidename;
    private String pax;
    private String commission;
    private String sumpax;
    private String sumcommission;

    public String getSystemdate() {
        return systemdate;
    }

    public void setSystemdate(String systemdate) {
        this.systemdate = systemdate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDatefrom() {
        return datefrom;
    }

    public void setDatefrom(String datefrom) {
        this.datefrom = datefrom;
    }

    public String getDateto() {
        return dateto;
    }

    public void setDateto(String dateto) {
        this.dateto = dateto;
    }

    public String getGuidename() {
        return guidename;
    }

    public void setGuidename(String guidename) {
        this.guidename = guidename;
    }

    public String getPax() {
        return pax;
    }

    public void setPax(String pax) {
        this.pax = pax;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getSumpax() {
        return sumpax;
    }

    public void setSumpax(String sumpax) {
        this.sumpax = sumpax;
    }

    public String getSumcommission() {
        return sumcommission;
    }

    public void setSumcommission(String sumcommission) {
        this.sumcommission = sumcommission;
    }
    
}
