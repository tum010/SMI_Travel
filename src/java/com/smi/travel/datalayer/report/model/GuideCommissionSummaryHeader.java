/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.report.model;

import java.math.BigDecimal;

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
    private int pax;
    private BigDecimal commission;

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

    public int getPax() {
        return pax;
    }

    public void setPax(int pax) {
        this.pax = pax;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

}
