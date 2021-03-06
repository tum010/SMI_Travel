package com.smi.travel.datalayer.entity;
// Generated Jan 14, 2015 11:52:54 AM by Hibernate Tools 3.6.0

import java.math.BigDecimal;




/**
 * HotelRoom generated by hbm2java
 */
public class HotelRoom   {


     private String id;
     private HotelBooking hotelBooking;
     private int qty;
     private String category;
     private String room;
     private BigDecimal cost;
     private BigDecimal price;
     private String currency;

    public HotelRoom() {
        
    }

	
    public HotelRoom(HotelBooking hotelBooking, int qty, String room, BigDecimal cost, BigDecimal price,String currency) {
        this.hotelBooking = hotelBooking;
        this.qty = qty;
        this.room = room;
        this.cost = cost;
        this.price = price;
        this.currency = currency;
    }
    public HotelRoom(HotelBooking hotelBooking, int qty, String category, String room, BigDecimal cost, BigDecimal price,String currency) {
       this.hotelBooking = hotelBooking;
       this.qty = qty;
       this.category = category;
       this.room = room;
       this.cost = cost;
       this.price = price;
       this.currency = currency;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public HotelBooking getHotelBooking() {
        return this.hotelBooking;
    }
    
    public void setHotelBooking(HotelBooking hotelBooking) {
        this.hotelBooking = hotelBooking;
    }
    public int getQty() {
        return this.qty;
    }
    
    public void setQty(int qty) {
        this.qty = qty;
    }
    public String getCategory() {
        return this.category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    public String getRoom() {
        return this.room;
    }
    
    public void setRoom(String room) {
        this.room = room;
    }
    
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}


