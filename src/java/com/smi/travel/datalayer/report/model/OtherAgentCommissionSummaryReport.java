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
public class OtherAgentCommissionSummaryReport {
    private String systemdate;
    private String user;
    private String datefrom;
    private String dateto;
    private String code;
    private String name;
    private int countbook;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountbook() {
        return countbook;
    }

    public void setCountbook(int countbook) {
        this.countbook = countbook;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }
    
}
