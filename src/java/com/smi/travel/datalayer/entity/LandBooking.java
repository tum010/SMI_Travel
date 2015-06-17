/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Surachai
 */
public class LandBooking {
     private String id;
     private Agent agent;
     private MItemstatus MItemstatus;
     private PackageTour packageTour;
     private Master master;
     private String okBy;
     private String category;
     private String description;
     private int inboundQty;
     private Long inboundCost;
     private Long inboundPrice;
     private Integer isBill;
     private Date createDate;
     private Integer outboundAdQty;
     private Long outboundAdCost;
     private Long outboundAdPrice;
     private Integer outboundChQty;
     private Long outboundChCost;
     private Long outboundChPrice;
     private Integer outboundInQty;
     private Long outboundInCost;
     private Long outboundInPrice;
     private String remark;
     private Integer inboundChQty;
     private Long inboundChCost;
     private Long inboundChPrice;
     private Integer inboundInQty;
     private Long inboundInCost;
     private Long inboundInPrice;
     private String currency;
     private Date outboundDepart;
     private Date outboundArrive;
     private String inboundHotel;
     private Set landItineraries = new HashSet(0);

    public LandBooking() {
        
    }

    public LandBooking(Agent agent, Master master, String okBy, int inboundQty) {
        this.agent = agent;
        this.master = master;
        this.okBy = okBy;
        this.inboundQty = inboundQty;
    }
    public LandBooking(Agent agent, MItemstatus MItemstatus, PackageTour packageTour, Master master, String okBy, String category, String description, int inboundQty, Long inboundCost, Long inboundPrice, Integer isBill, Date createDate, Integer outboundAdQty, Long outboundAdCost, Long outboundAdPrice, Integer outboundChQty, Long outboundChCost, Long outboundChPrice, Integer outboundInQty, Long outboundInCost, Long outboundInPrice,String remark,Integer inboundChQty, Long inboundChCost, Long inboundChPrice, Integer inboundInQty, Long inboundInCost, Long inboundInPrice, String currency,Set landItineraries,Date outboundDepart,Date outboundArrive,String inboundHotel) {
       this.agent = agent;
       this.MItemstatus = MItemstatus;
       this.packageTour = packageTour;
       this.master = master;
       this.okBy = okBy;
       this.category = category;
       this.description = description;
       this.inboundQty = inboundQty;
       this.inboundCost = inboundCost;
       this.inboundPrice = inboundPrice;
       this.isBill = isBill;
       this.createDate = createDate;
       this.outboundAdQty = outboundAdQty;
       this.outboundAdCost = outboundAdCost;
       this.outboundAdPrice = outboundAdPrice;
       this.outboundChQty = outboundChQty;
       this.outboundChCost = outboundChCost;
       this.outboundChPrice = outboundChPrice;
       this.outboundInQty = outboundInQty;
       this.outboundInCost = outboundInCost;
       this.outboundInPrice = outboundInPrice;
       this.inboundChQty = inboundChQty;
       this.inboundChCost = inboundChCost;
       this.inboundChPrice = inboundChPrice;
       this.inboundInQty = inboundInQty;
       this.inboundInCost = inboundInCost;
       this.inboundInPrice = inboundInPrice;
       this.currency = currency;
       this.landItineraries = landItineraries;
       this.outboundDepart = outboundDepart;
       this.outboundArrive = outboundArrive;
       this.remark = remark;
       this.inboundHotel = inboundHotel;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public Agent getAgent() {
        return this.agent;
    }
    
    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public MItemstatus getMItemstatus() {
        return MItemstatus;
    }

    public void setMItemstatus(MItemstatus MItemstatus) {
        this.MItemstatus = MItemstatus;
    }

    public PackageTour getPackageTour() {
        return packageTour;
    }

    public void setPackageTour(PackageTour packageTour) {
        this.packageTour = packageTour;
    }
    
    public Master getMaster() {
        return this.master;
    }
    
    public void setMaster(Master master) {
        this.master = master;
    }
    public String getOkBy() {
        return this.okBy;
    }
    
    public void setOkBy(String okBy) {
        this.okBy = okBy;
    }
    public String getCategory() {
        return this.category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public int getInboundQty() {
        return this.inboundQty;
    }
    
    public void setInboundQty(int inboundQty) {
        this.inboundQty = inboundQty;
    }
    public Long getInboundCost() {
        return this.inboundCost;
    }
    
    public void setInboundCost(Long inboundCost) {
        this.inboundCost = inboundCost;
    }
    public Long getInboundPrice() {
        return this.inboundPrice;
    }
    
    public void setInboundPrice(Long inboundPrice) {
        this.inboundPrice = inboundPrice;
    }
    public Integer getIsBill() {
        return this.isBill;
    }
    
    public void setIsBill(Integer isBill) {
        this.isBill = isBill;
    }
    public Date getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Integer getOutboundAdQty() {
        return this.outboundAdQty;
    }
    
    public void setOutboundAdQty(Integer outboundAdQty) {
        this.outboundAdQty = outboundAdQty;
    }
    public Long getOutboundAdCost() {
        return this.outboundAdCost;
    }
    
    public void setOutboundAdCost(Long outboundAdCost) {
        this.outboundAdCost = outboundAdCost;
    }
    public Long getOutboundAdPrice() {
        return this.outboundAdPrice;
    }
    
    public void setOutboundAdPrice(Long outboundAdPrice) {
        this.outboundAdPrice = outboundAdPrice;
    }
    public Integer getOutboundChQty() {
        return this.outboundChQty;
    }
    
    public void setOutboundChQty(Integer outboundChQty) {
        this.outboundChQty = outboundChQty;
    }
    public Long getOutboundChCost() {
        return this.outboundChCost;
    }
    
    public void setOutboundChCost(Long outboundChCost) {
        this.outboundChCost = outboundChCost;
    }
    public Long getOutboundChPrice() {
        return this.outboundChPrice;
    }
    
    public void setOutboundChPrice(Long outboundChPrice) {
        this.outboundChPrice = outboundChPrice;
    }
    public Integer getOutboundInQty() {
        return this.outboundInQty;
    }
    
    public void setOutboundInQty(Integer outboundInQty) {
        this.outboundInQty = outboundInQty;
    }
    public Long getOutboundInCost() {
        return this.outboundInCost;
    }
    
    public void setOutboundInCost(Long outboundInCost) {
        this.outboundInCost = outboundInCost;
    }
    public Long getOutboundInPrice() {
        return this.outboundInPrice;
    }
    
    public void setOutboundInPrice(Long outboundInPrice) {
        this.outboundInPrice = outboundInPrice;
    }

    public Set getLandItineraries() {
        return landItineraries;
    }

    public void setLandItineraries(Set landItineraries) {
        this.landItineraries = landItineraries;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getInboundChQty() {
        return inboundChQty;
    }

    public void setInboundChQty(Integer inboundChQty) {
        this.inboundChQty = inboundChQty;
    }

    public Long getInboundChCost() {
        return inboundChCost;
    }

    public void setInboundChCost(Long inboundChCost) {
        this.inboundChCost = inboundChCost;
    }

    public Long getInboundChPrice() {
        return inboundChPrice;
    }

    public void setInboundChPrice(Long inboundChPrice) {
        this.inboundChPrice = inboundChPrice;
    }

    public Integer getInboundInQty() {
        return inboundInQty;
    }

    public void setInboundInQty(Integer inboundInQty) {
        this.inboundInQty = inboundInQty;
    }

    public Long getInboundInCost() {
        return inboundInCost;
    }

    public void setInboundInCost(Long inboundInCost) {
        this.inboundInCost = inboundInCost;
    }

    public Long getInboundInPrice() {
        return inboundInPrice;
    }

    public void setInboundInPrice(Long inboundInPrice) {
        this.inboundInPrice = inboundInPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getOutboundDepart() {
        return outboundDepart;
    }

    public void setOutboundDepart(Date outboundDepart) {
        this.outboundDepart = outboundDepart;
    }

    public Date getOutboundArrive() {
        return outboundArrive;
    }

    public void setOutboundArrive(Date outboundArrive) {
        this.outboundArrive = outboundArrive;
    }

    public String getInboundHotel() {
        return inboundHotel;
    }

    public void setInboundHotel(String inboundHotel) {
        this.inboundHotel = inboundHotel;
    }

    
    
    
}
