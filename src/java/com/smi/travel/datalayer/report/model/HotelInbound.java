/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.report.model;

import java.util.Date;

/**
 *
 * @author Surachai
 */
public class HotelInbound {
    private String night;
    private String checkin;
    private String checkout;
    private String room1;
    private String room2;
    private String room3;
    private String request1;
    private String request2;
    private String request3;
    private String meal;
    private String remark;
    private String paxname;
    private String rooming;
    private String psgremark;
    private String status;
    private String hotelref;
    private String adult;
    private String child;
    private String infant;
    private String total;
    private String age;
    private String systemdate;
    
    public HotelInbound(){
        this.request1 = "";
        this.request2 = "";
        this.request3 = "";
        this.room1 ="";
        this.room2 ="";
        this.room3 ="";
    }

    public String getNight() {
        return night;
    }

    public void setNight(String night) {
        this.night = night;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public String getRoom1() {
        return room1;
    }

    public void setRoom1(String room1) {
        this.room1 = room1;
    }

    public String getRoom2() {
        return room2;
    }

    public void setRoom2(String room2) {
        this.room2 = room2;
    }

    public String getRoom3() {
        return room3;
    }

    public void setRoom3(String room3) {
        this.room3 = room3;
    }

    public String getRequest1() {
        return request1;
    }

    public void setRequest1(String request1) {
        this.request1 = request1;
    }

    public String getRequest2() {
        return request2;
    }

    public void setRequest2(String request2) {
        this.request2 = request2;
    }

    public String getRequest3() {
        return request3;
    }

    public void setRequest3(String request3) {
        this.request3 = request3;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPaxname() {
        return paxname;
    }

    public void setPaxname(String paxname) {
        this.paxname = paxname;
    }

    public String getRooming() {
        return rooming;
    }

    public void setRooming(String rooming) {
        this.rooming = rooming;
    }

    public String getPsgremark() {
        return psgremark;
    }

    public void setPsgremark(String psgremark) {
        this.psgremark = psgremark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHotelref() {
        return hotelref;
    }

    public void setHotelref(String hotelref) {
        this.hotelref = hotelref;
    }

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }

    public String getInfant() {
        return infant;
    }

    public void setInfant(String infant) {
        this.infant = infant;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSystemdate() {
        return systemdate;
    }

    public void setSystemdate(String systemdate) {
        this.systemdate = systemdate;
    }
    
    
    
}
