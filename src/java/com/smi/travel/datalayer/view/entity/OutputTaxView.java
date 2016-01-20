/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.entity;

import java.math.BigDecimal;

/**
 *
 * @author Jittima
 */
public class OutputTaxView {
    private String headerMonth;
    private String headerYear;
    private String headerDepartment;
    private String order;
    private String taxid;
    private String taxno;
    private String taxdate;
    private String arcode;
    private String taxinvname;
    private BigDecimal gross;
    private BigDecimal vat;
    private BigDecimal amount;
    private String department;
    private String description;
    private String status;
    private String agttaxno;
    private String main;
    private String branchno;
    private int countWendy;
    private int countOutbound;
    private int countInbound;
    private String type;
    
    public String getTaxid() {
        return taxid;
    }

    public void setTaxid(String taxid) {
        this.taxid = taxid;
    }

    public String getTaxno() {
        return taxno;
    }

    public void setTaxno(String taxno) {
        this.taxno = taxno;
    }

    public String getArcode() {
        return arcode;
    }

    public void setArcode(String arcode) {
        this.arcode = arcode;
    }

    public String getTaxinvname() {
        return taxinvname;
    }

    public void setTaxinvname(String taxinvname) {
        this.taxinvname = taxinvname;
    }

    public BigDecimal getGross() {
        return gross;
    }

    public void setGross(BigDecimal gross) {
        this.gross = gross;
    }

    public BigDecimal getVat() {
        return vat;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAgttaxno() {
        return agttaxno;
    }

    public void setAgttaxno(String agttaxno) {
        this.agttaxno = agttaxno;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getBranchno() {
        return branchno;
    }

    public void setBranchno(String branchno) {
        this.branchno = branchno;
    }

    public String getTaxdate() {
        return taxdate;
    }

    public void setTaxdate(String taxdate) {
        this.taxdate = taxdate;
    }

    public String getHeaderMonth() {
        return headerMonth;
    }

    public void setHeaderMonth(String headerMonth) {
        this.headerMonth = headerMonth;
    }

    public String getHeaderYear() {
        return headerYear;
    }

    public void setHeaderYear(String headerYear) {
        this.headerYear = headerYear;
    }

    public String getHeaderDepartment() {
        return headerDepartment;
    }

    public void setHeaderDepartment(String headerDepartment) {
        this.headerDepartment = headerDepartment;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getCountWendy() {
        return countWendy;
    }

    public void setCountWendy(int countWendy) {
        this.countWendy = countWendy;
    }

    public int getCountOutbound() {
        return countOutbound;
    }

    public void setCountOutbound(int countOutbound) {
        this.countOutbound = countOutbound;
    }

    public int getCountInbound() {
        return countInbound;
    }

    public void setCountInbound(int countInbound) {
        this.countInbound = countInbound;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
