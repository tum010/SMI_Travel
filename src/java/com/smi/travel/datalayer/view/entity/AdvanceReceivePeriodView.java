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
public class AdvanceReceivePeriodView {
    private String cashamount;
    private String bankamount;
    private String cashminusamount;
    private String cheque;
    private String creditcard;

    public String getCashamount() {
        return cashamount;
    }

    public void setCashamount(String cashamount) {
        this.cashamount = cashamount;
    }

    public String getBankamount() {
        return bankamount;
    }

    public void setBankamount(String bankamount) {
        this.bankamount = bankamount;
    }

    public String getCashminusamount() {
        return cashminusamount;
    }

    public void setCashminusamount(String cashminusamount) {
        this.cashminusamount = cashminusamount;
    }

    public String getCheque() {
        return cheque;
    }

    public void setCheque(String cheque) {
        this.cheque = cheque;
    }

    public String getCreditcard() {
        return creditcard;
    }

    public void setCreditcard(String creditcard) {
        this.creditcard = creditcard;
    }
}
