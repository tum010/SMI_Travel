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
public class CollectionNirvanaExpenseReceipt {
    
    private String projectid;
    private String divisionid;
    private String glaccountid;
    private BigDecimal amount;
    private String note;
    private String recid;
    
    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getDivisionid() {
        return divisionid;
    }

    public void setDivisionid(String divisionid) {
        this.divisionid = divisionid;
    }

    public String getGlaccountid() {
        return glaccountid;
    }

    public void setGlaccountid(String glaccountid) {
        this.glaccountid = glaccountid;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRecid() {
        return recid;
    }

    public void setRecid(String recid) {
        this.recid = recid;
    }

    
}
