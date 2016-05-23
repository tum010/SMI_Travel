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
public class DaytourOther {
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
    private String passenger1;
    private String passenger2;
    private String passenger3;
    private String passenger4;
    private String passenger5;
    private String passenger6;
    private String passenger7;
    private String passenger8;
    private String remark;
    private String printby;

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

    public String getPassenger1() {
        return passenger1;
    }

    public void setPassenger1(String passenger1) {
        this.passenger1 = passenger1;
    }

    public String getPassenger2() {
        return passenger2;
    }

    public void setPassenger2(String passenger2) {
        this.passenger2 = passenger2;
    }

    public String getPassenger3() {
        return passenger3;
    }

    public void setPassenger3(String passenger3) {
        this.passenger3 = passenger3;
    }

    public String getPassenger4() {
        return passenger4;
    }

    public void setPassenger4(String passenger4) {
        this.passenger4 = passenger4;
    }

    public String getPassenger5() {
        return passenger5;
    }

    public void setPassenger5(String passenger5) {
        this.passenger5 = passenger5;
    }

    public String getPassenger6() {
        return passenger6;
    }

    public void setPassenger6(String passenger6) {
        this.passenger6 = passenger6;
    }

    public String getPrintby() {
        return printby;
    }

    public void setPrintby(String printby) {
        this.printby = printby;
    }

    public String getPassenger7() {
        return passenger7;
    }

    public void setPassenger7(String passenger7) {
        this.passenger7 = passenger7;
    }

    public String getPassenger8() {
        return passenger8;
    }

    public void setPassenger8(String passenger8) {
        this.passenger8 = passenger8;
    }
}
