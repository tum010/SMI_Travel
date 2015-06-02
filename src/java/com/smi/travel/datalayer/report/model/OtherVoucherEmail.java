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
public class OtherVoucherEmail {
    private String refno;
    private String createdate;
    private String leadername;
    private String code;
    private String description;
    private String date;
    private String time;
    private int adult;
    private int child;
    private int infant;
    private String passenger;
    private String remark;

    public int getAdult() {
        return adult;
    }

    public String getCode() {
        return code;
    }

    public String getCreatedate() {
        return createdate;
    }

    public String getDescription() {
        return description;
    }

    public int getInfant() {
        return infant;
    }

    public String getLeadername() {
        return leadername;
    }

    public String getPassenger() {
        return passenger;
    }

    public String getRefno() {
        return refno;
    }

    public String getRemark() {
        return remark;
    }
    
    public int getChild() {
        return child;
    }

    public void setAdult(int adult) {
        this.adult = adult;
    }

    public void setChild(int child) {
        this.child = child;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setInfant(int infant) {
        this.infant = infant;
    }

    public void setLeadername(String leadername) {
        this.leadername = leadername;
    }

    public void setPassenger(String passenger) {
        this.passenger = passenger;
    }

    public void setRefno(String refno) {
        this.refno = refno;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
