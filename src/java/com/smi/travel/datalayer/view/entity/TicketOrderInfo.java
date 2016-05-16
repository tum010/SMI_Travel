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
    private String issuedate;
    private String companyname;
    private String tel;
    private String pnr;
    private String inv;
    private String price;
    private String cost;
    private String pricetax;
    private String costtax;
    private String termpay;
    private String remark;
    private String ispickup;
    private String companyaddress;

    public String getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(String issuedate) {
        this.issuedate = issuedate;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getPricetax() {
        return pricetax;
    }

    public void setPricetax(String pricetax) {
        this.pricetax = pricetax;
    }

    public String getCosttax() {
        return costtax;
    }

    public void setCosttax(String costtax) {
        this.costtax = costtax;
    }

    public String getTermpay() {
        return termpay;
    }

    public void setTermpay(String termpay) {
        this.termpay = termpay;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIspickup() {
        return ispickup;
    }

    public void setIspickup(String ispickup) {
        this.ispickup = ispickup;
    }

    public String getCompanyaddress() {
        return companyaddress;
    }

    public void setCompanyaddress(String companyaddress) {
        this.companyaddress = companyaddress;
    }

}
