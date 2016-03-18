/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.model.nirvana;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author chonnasith
 */
public class SsDataexch {
    private String dataCd;
    private String dataNo;
    private String entSysCd;
    private String entSysDate;
    private String entDataNo;
    private String entComment;
    private String rcvSysCd;
    private String rcvStaCd;
    private String rcvSysDate;
    private String rcvComment;
    private String traNesCd;
    private String traStaCd;
    private String traSysDate;
    private String dataArea;
    private SsDataexchTr ssDataexchTr = new SsDataexchTr();

    public String getDataCd() {
        return dataCd;
    }

    public void setDataCd(String dataCd) {
        this.dataCd = dataCd;
    }

    public String getDataNo() {
        return dataNo;
    }

    public void setDataNo(String dataNo) {
        this.dataNo = dataNo;
    }

    public String getEntSysCd() {
        return entSysCd;
    }

    public void setEntSysCd(String entSysCd) {
        this.entSysCd = entSysCd;
    }

    public String getEntSysDate() {
        return entSysDate;
    }

    public void setEntSysDate(String entSysDate) {
        this.entSysDate = entSysDate;
    }

    public String getEntDataNo() {
        return entDataNo;
    }

    public void setEntDataNo(String entDataNo) {
        this.entDataNo = entDataNo;
    }

    public String getEntComment() {
        return entComment;
    }

    public void setEntComment(String entComment) {
        this.entComment = entComment;
    }

    public String getRcvSysCd() {
        return rcvSysCd;
    }

    public void setRcvSysCd(String rcvSysCd) {
        this.rcvSysCd = rcvSysCd;
    }

    public String getRcvStaCd() {
        return rcvStaCd;
    }

    public void setRcvStaCd(String rcvStaCd) {
        this.rcvStaCd = rcvStaCd;
    }

    public String getRcvSysDate() {
        return rcvSysDate;
    }

    public void setRcvSysDate(String rcvSysDate) {
        this.rcvSysDate = rcvSysDate;
    }

    public String getRcvComment() {
        return rcvComment;
    }

    public void setRcvComment(String rcvComment) {
        this.rcvComment = rcvComment;
    }

    public String getTraNesCd() {
        return traNesCd;
    }

    public void setTraNesCd(String traNesCd) {
        this.traNesCd = traNesCd;
    }

    public String getTraStaCd() {
        return traStaCd;
    }

    public void setTraStaCd(String traStaCd) {
        this.traStaCd = traStaCd;
    }

    public String getTraSysDate() {
        return traSysDate;
    }

    public void setTraSysDate(String traSysDate) {
        this.traSysDate = traSysDate;
    }

    public String getDataArea() {
        return dataArea;
    }

    public void setDataArea(String dataArea) {
        this.dataArea = dataArea;
    }

    public SsDataexchTr getSsDataexchTr() {
        return ssDataexchTr;
    }

    public void setSsDataexchTr(SsDataexchTr ssDataexchTr) {
        this.ssDataexchTr = ssDataexchTr;
    }

}
