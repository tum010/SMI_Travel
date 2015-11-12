/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.model;

/**
 *
 * @author Kanokporn
 */
public class NonBillableView {
    private String masterid;
    private String invoicedetailid;

    public String getMasterid() {
        return masterid;
    }

    public void setMasterid(String masterid) {
        this.masterid = masterid;
    }

    public String getInvoicedetailid() {
        return invoicedetailid;
    }

    public void setInvoicedetailid(String invoicedetailid) {
        this.invoicedetailid = invoicedetailid;
    }
    
}
