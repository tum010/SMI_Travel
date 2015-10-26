/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.entity;

import java.math.BigDecimal;

/**
 *
 * @author Kanokporn
 */
public class HotelMonthlyDetail {
    private Integer qty;			
    private String room;			
    private String category;			
    private BigDecimal cost;			
    private String curcost;			
    private BigDecimal price;			
    private String curprice;

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getCurcost() {
        return curcost;
    }

    public void setCurcost(String curcost) {
        this.curcost = curcost;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurprice() {
        return curprice;
    }

    public void setCurprice(String curprice) {
        this.curprice = curprice;
    }
    
    
}
