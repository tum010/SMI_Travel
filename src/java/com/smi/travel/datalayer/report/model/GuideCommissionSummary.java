/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.report.model;

import java.math.BigDecimal;

/**
 *
 * @author Surachai
 */
public class GuideCommissionSummary {
    private String systemdate;
    private String user;
    private String tourdate;
    private String code;
    private String customer;
    private int pax;
    private BigDecimal comission;
    private BigDecimal selling;
    private String guide;
    private String remark;
    private String datefrom;
    private String dateto;

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

    public String getTourdate() {
        return tourdate;
    }

    public void setTourdate(String tourdate) {
        this.tourdate = tourdate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getPax() {
        return pax;
    }

    public void setPax(int pax) {
        this.pax = pax;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public BigDecimal getComission() {
        return comission;
    }

    public void setComission(BigDecimal comission) {
        this.comission = comission;
    }

    public BigDecimal getSelling() {
        return selling;
    }

    public void setSelling(BigDecimal selling) {
        this.selling = selling;
    }
       
}












