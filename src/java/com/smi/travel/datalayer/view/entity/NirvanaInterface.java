/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.entity;

/**
 *
 * @author chonnasith
 */
public class NirvanaInterface {
    //AP
    private String datano;
    private String payment_detail_id;
    private String paymenttype;
    
    //AR
    private String rowid;

    public String getDatano() {
        return datano;
    }

    public void setDatano(String datano) {
        this.datano = datano;
    }

    public String getPayment_detail_id() {
        return payment_detail_id;
    }

    public void setPayment_detail_id(String payment_detail_id) {
        this.payment_detail_id = payment_detail_id;
    }

    public String getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
    }

    public String getRowid() {
        return rowid;
    }

    public void setRowid(String rowid) {
        this.rowid = rowid;
    }
    
}
