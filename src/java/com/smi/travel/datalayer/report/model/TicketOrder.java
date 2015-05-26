/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.report.model;

import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;


public class TicketOrder {
    private String leadername;
    private String companyname;
    private String issuedate;
    private String flightNo;
    private String flightClass;
    private String departdate;
    private String from;
    private String to;
    private String depttime;
    private String arrvtime;
    private String status;
    private String description1;
    private String description2;
    private String net1;
    private String net2;
    private String sell1;
    private String sell2;
    private String refno;
    private String billname;
    private String tel;
    private String pnr;
    private String inv;
    private String price;
    private String cost;
    private String pricetax;
    private String costtax;
    private String termpay;
    private String remark;
    private String prepareby;
    private String issueby;
    private JRDataSource passengerNameAndTicketDataSource;
    private JRDataSource flightDataSource;
    private String subReportDir;

    public TicketOrder(){
        this.leadername = "";
        this.companyname = "";
        this.issuedate = "";
        this.description1 = "";
        this.description2 = "";
        this.net1 = "";
        this.net2 = "";
        this.sell1 = "";
        this.sell2 = "";
        this.refno = "";
        this.billname = "";
        this.tel = "";
        this.pnr = "";
        this.inv = "";
        this.price = "";
        this.cost = "";
        this.pricetax = "";
        this.costtax = "";
        this.termpay = "";
        this.remark = "";
        this.prepareby = "";
        this.issueby = "";
        this.passengerNameAndTicketDataSource = null;
    }
    
    public String getSubReportDir() {
        return subReportDir;
    }

    public void setSubReportDir(String subReportDir) {
        this.subReportDir = subReportDir;
    }
    
    public JRDataSource getPassengerNameAndTicketDataSource() {
        return passengerNameAndTicketDataSource;
    }

    public void setPassengerNameAndTicketDataSource(JRDataSource passengerNameAndTicketDataSource) {
        this.passengerNameAndTicketDataSource = passengerNameAndTicketDataSource;
    }
    
    public JRDataSource getFlightDataSource() {
        return flightDataSource;
    }

    public void setFlightDataSource(JRDataSource flightDataSource) {
        this.flightDataSource = flightDataSource;
    }    
    
    public String getLeadername() {
        return leadername;
    }

    public void setLeadername(String leadername) {
        this.leadername = leadername;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }

   
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription1() {
        return description1;
    }

    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    public String getDescription2() {
        return description2;
    }

    public void setDescription2(String description2) {
        this.description2 = description2;
    }

    public String getNet1() {
        return net1;
    }

    public void setNet1(String net1) {
        this.net1 = net1;
    }

    public String getNet2() {
        return net2;
    }

    public void setNet2(String net2) {
        this.net2 = net2;
    }

    public String getSell1() {
        return sell1;
    }

    public void setSell1(String sell1) {
        this.sell1 = sell1;
    }

    public String getSell2() {
        return sell2;
    }

    public void setSell2(String sell2) {
        this.sell2 = sell2;
    }

    public String getRefno() {
        return refno;
    }

    public void setRefno(String refno) {
        this.refno = refno;
    }

    public String getBillname() {
        return billname;
    }

    public void setBillname(String billname) {
        this.billname = billname;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
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

    public String getPrepareby() {
        return prepareby;
    }

    public void setPrepareby(String prepareby) {
        this.prepareby = prepareby;
    }

    public String getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(String issuedate) {
        this.issuedate = issuedate;
    }

    public String getDepartdate() {
        return departdate;
    }

    public void setDepartdate(String departdate) {
        this.departdate = departdate;
    }

    public String getDepttime() {
        return depttime;
    }

    public void setDepttime(String depttime) {
        this.depttime = depttime;
    }

    public String getArrvtime() {
        return arrvtime;
    }

    public void setArrvtime(String arrvtime) {
        this.arrvtime = arrvtime;
    }

    public String getIssueby() {
        return issueby;
    }

    public void setIssueby(String issueby) {
        this.issueby = issueby;
    }
    
}
