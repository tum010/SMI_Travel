/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.entity;

import java.util.Date;

/**
 *
 * @author Surachai
 */
public class TicketOrderInfo {
    private String refno;
    private String firstname;
    private String leadername;
    private Date billDate;
    private String companyname;
    private String tel;
    private String pnr;
    private String inv;
    private String sell;
    private String sell_tax;
    private String net;
    private String net_tax;
    private String teamOfPayment;
    private String remark;
    private String prepareBy;

    public String getRefno() {
        return refno;
    }

    public void setRefno(String refno) {
        this.refno = refno;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLeadername() {
        return leadername;
    }

    public void setLeadername(String leadername) {
        this.leadername = leadername;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getInv() {
        return inv;
    }

    public void setInv(String inv) {
        this.inv = inv;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    public String getSell_tax() {
        return sell_tax;
    }

    public void setSell_tax(String sell_tax) {
        this.sell_tax = sell_tax;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public String getNet_tax() {
        return net_tax;
    }

    public void setNet_tax(String net_tax) {
        this.net_tax = net_tax;
    }

    public String getTeamOfPayment() {
        return teamOfPayment;
    }

    public void setTeamOfPayment(String teamOfPayment) {
        this.teamOfPayment = teamOfPayment;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPrepareBy() {
        return prepareBy;
    }

    public void setPrepareBy(String prepareBy) {
        this.prepareBy = prepareBy;
    }
    
    
    
    
    
}
