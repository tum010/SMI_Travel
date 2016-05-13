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
public class ARNirvanaSaleDetail {
    private String salesaccount ;
    private String salesdivision;
    private String salesproject;
    private String salesamt;
    private String saleshmamt;
    private String detail;

    public String getSalesaccount() {
        return salesaccount;
    }

    public void setSalesaccount(String salesaccount) {
        this.salesaccount = salesaccount;
    }

    public String getSalesdivision() {
        return salesdivision;
    }

    public void setSalesdivision(String salesdivision) {
        this.salesdivision = salesdivision;
    }

    public String getSalesproject() {
        return salesproject;
    }

    public void setSalesproject(String salesproject) {
        this.salesproject = salesproject;
    }

    public String getSalesamt() {
        return salesamt;
    }

    public void setSalesamt(String salesamt) {
        this.salesamt = salesamt;
    }

    public String getSaleshmamt() {
        return saleshmamt;
    }

    public void setSaleshmamt(String saleshmamt) {
        this.saleshmamt = saleshmamt;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
