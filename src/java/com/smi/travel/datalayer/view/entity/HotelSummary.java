/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.entity;

/**
 *
 * @author Kanokporn
 */
public class HotelSummary {
    private String fromto;		
    private String systemdate;		
    private String city;		
    private String hotel;		
    private String night;		
    private String sell	;	
    private String net;		
    private String profit;
    
    //Header
    private String frompage;
    private String topage;
    private String departmentpage;

    public String getFrompage() {
        return frompage;
    }

    public void setFrompage(String frompage) {
        this.frompage = frompage;
    }

    public String getTopage() {
        return topage;
    }

    public void setTopage(String topage) {
        this.topage = topage;
    }

    public String getDepartmentpage() {
        return departmentpage;
    }

    public void setDepartmentpage(String departmentpage) {
        this.departmentpage = departmentpage;
    }

   
    public String getFromto() {
        return fromto;
    }

    public void setFromto(String fromto) {
        this.fromto = fromto;
    }

    public String getSystemdate() {
        return systemdate;
    }

    public void setSystemdate(String systemdate) {
        this.systemdate = systemdate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getNight() {
        return night;
    }

    public void setNight(String night) {
        this.night = night;
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

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }
    
    
}
