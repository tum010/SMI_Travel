/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.entity;

/**
 *
 * @author Jittima
 */
public class PackageMonthlyView {
    // PackageMonthlyListReport
    private String packdate;
    private String course;
    private String tour;
    private String pax;
    private String sell;
    private String net;
    private String balance;

    public String getPackdate() {
        return packdate;
    }

    public void setPackdate(String packdate) {
        this.packdate = packdate;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTour() {
        return tour;
    }

    public void setTour(String tour) {
        this.tour = tour;
    }

    public String getPax() {
        return pax;
    }

    public void setPax(String pax) {
        this.pax = pax;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
    
}
