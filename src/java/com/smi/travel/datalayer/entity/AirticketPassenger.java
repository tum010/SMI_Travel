package com.smi.travel.datalayer.entity;
// Generated Dec 22, 2014 5:59:06 PM by Hibernate Tools 3.6.0

import java.math.BigDecimal;




/**
 * AirticketPassenger generated by hbm2java
 */
public class AirticketPassenger {
     private String id;
     private AirticketAirline airticketAirline;
     private MPricecategory MPricecategory;
     private MInitialname MInitialname;
     private String firstName;
     private String lastName;
     private String series1;
     private String series2;
     private String series3;
     private BigDecimal ticketFare;
     private BigDecimal ticketTax;
     private String ticketFrom;
     private String ticketType;

    public AirticketPassenger() {
    }

	
    public AirticketPassenger(String firstName, String lastName, String series1, String series2, String series3, BigDecimal ticketFare, BigDecimal ticketTax, String ticketFrom, String ticketType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.series1 = series1;
        this.series2 = series2;
        this.series3 = series3;
        this.ticketFare = ticketFare;
        this.ticketTax = ticketTax;
        this.ticketFrom = ticketFrom;
        this.ticketType = ticketType;
    }
    public AirticketPassenger(AirticketAirline airticketAirline, MPricecategory MPricecategory, MInitialname MInitialname, String firstName, String lastName, String series1, String series2, String series3, String from, String to, BigDecimal ticketFare, BigDecimal ticketTax, String ticketFrom, String ticketType) {
       this.airticketAirline = airticketAirline;
       this.MPricecategory = MPricecategory;
       this.MInitialname = MInitialname;
       this.firstName = firstName;
       this.lastName = lastName;
       this.series1 = series1;
       this.series2 = series2;
       this.series3 = series3;
       this.ticketFare = ticketFare;
       this.ticketTax = ticketTax;
       this.ticketFrom = ticketFrom;
       this.ticketType = ticketType;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public AirticketAirline getAirticketAirline() {
        return this.airticketAirline;
    }
    
    public void setAirticketAirline(AirticketAirline airticketAirline) {
        this.airticketAirline = airticketAirline;
    }
    public MPricecategory getMPricecategory() {
        return this.MPricecategory;
    }
    
    public void setMPricecategory(MPricecategory MPricecategory) {
        this.MPricecategory = MPricecategory;
    }
    public MInitialname getMInitialname() {
        return this.MInitialname;
    }
    
    public void setMInitialname(MInitialname MInitialname) {
        this.MInitialname = MInitialname;
    }
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getSeries1() {
        return this.series1;
    }
    
    public void setSeries1(String series1) {
        this.series1 = series1;
    }
    public String getSeries2() {
        return this.series2;
    }
    
    public void setSeries2(String series2) {
        this.series2 = series2;
    }
    public String getSeries3() {
        return this.series3;
    }
    
    public void setSeries3(String series3) {
        this.series3 = series3;
    }

    public String getTicketFrom() {
        return this.ticketFrom;
    }
    
    public void setTicketFrom(String ticketFrom) {
        this.ticketFrom = ticketFrom;
    }
    public String getTicketType() {
        return this.ticketType;
    }
    
    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public BigDecimal getTicketFare() {
        return ticketFare;
    }

    public void setTicketFare(BigDecimal ticketFare) {
        this.ticketFare = ticketFare;
    }

    public BigDecimal getTicketTax() {
        return ticketTax;
    }

    public void setTicketTax(BigDecimal ticketTax) {
        this.ticketTax = ticketTax;
    }

}


