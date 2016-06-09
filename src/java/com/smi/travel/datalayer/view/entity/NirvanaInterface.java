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
    private String refinvoice;
    private String interference;
    private String recno;
    private String comment;
    private String result;
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

    public String getRefinvoice() {
        return refinvoice;
    }

    public void setRefinvoice(String refinvoice) {
        this.refinvoice = refinvoice;
    }

    public String getInterference() {
        return interference;
    }

    public void setInterference(String interference) {
        this.interference = interference;
    }

    public String getRecno() {
        return recno;
    }

    public void setRecno(String recno) {
        this.recno = recno;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
}
