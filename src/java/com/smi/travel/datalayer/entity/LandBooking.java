/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Surachai
 */
public class LandBooking {
     private String id;
     private Agent agent;
     private MItemstatus MItemstatus;
     private Master master;
     private PackageTour packageTour;
     private String okBy;
     private String category;
     private String description;
     private Integer inboundQty;
     private BigDecimal inboundCost;
     private BigDecimal inboundPrice;
     private BigDecimal inboundChCost;
     private BigDecimal inboundChPrice;
     private Integer inboundChQty;
     private BigDecimal inboundInCost;
     private BigDecimal inboundInPrice;
     private Integer inboundInQty;
     private Integer isBill;
     private Date createDate;
     private Integer outboundAdQty;
     private BigDecimal outboundAdCost;
     private BigDecimal outboundAdPrice;
     private Integer outboundChQty;
     private BigDecimal outboundChCost;
     private BigDecimal outboundChPrice;
     private Integer outboundInQty;
     private BigDecimal outboundInCost;
     private BigDecimal outboundInPrice;
     private String curCost;
     private String remark;
     private String inboundHotel;
     private Date outboundDepart;
     private Date outboundArrive;
     private String curAmount;
     private List landItineraries = new LinkedList<LandItinerary>();
     private List landCities = new LinkedList<LandCity>();

    public LandBooking() {
        
    }

    public LandBooking(Agent agent, Master master, String okBy, int inboundQty) {
        this.agent = agent;
        this.master = master;
        this.okBy = okBy;
        this.inboundQty = inboundQty;
    }
    public LandBooking(Agent agent, MItemstatus MItemstatus, PackageTour packageTour, Master master, String okBy, String category, String description, int inboundQty, BigDecimal inboundCost, BigDecimal inboundPrice, Integer isBill, Date createDate, Integer outboundAdQty, BigDecimal outboundAdCost, BigDecimal outboundAdPrice, Integer outboundChQty, BigDecimal outboundChCost, BigDecimal outboundChPrice, Integer outboundInQty, BigDecimal outboundInCost, BigDecimal outboundInPrice,String remark,Integer inboundChQty, BigDecimal inboundChCost, BigDecimal inboundChPrice, Integer inboundInQty, BigDecimal inboundInCost, BigDecimal inboundInPrice, String currency,List landItineraries,Date outboundDepart,Date outboundArrive,String inboundHotel,String curAmount,String curCost,List landCities) {
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
       this.curAmount = curAmount;
       this.curCost = curCost;
       this.landItineraries = landItineraries;
       this.outboundDepart = outboundDepart;
       this.outboundArrive = outboundArrive;
       this.remark = remark;
       this.inboundHotel = inboundHotel;
       this.landCities = landCities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Agent getAgent() {
        return agent;
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

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public PackageTour getPackageTour() {
        return packageTour;
    }

    public void setPackageTour(PackageTour packageTour) {
        this.packageTour = packageTour;
    }

    public String getOkBy() {
        return okBy;
    }

    public void setOkBy(String okBy) {
        this.okBy = okBy;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getInboundQty() {
        return inboundQty;
    }

    public void setInboundQty(Integer inboundQty) {
        this.inboundQty = inboundQty;
    }

    public BigDecimal getInboundCost() {
        return inboundCost;
    }

    public void setInboundCost(BigDecimal inboundCost) {
        this.inboundCost = inboundCost;
    }

    public BigDecimal getInboundPrice() {
        return inboundPrice;
    }

    public void setInboundPrice(BigDecimal inboundPrice) {
        this.inboundPrice = inboundPrice;
    }

    public BigDecimal getInboundChCost() {
        return inboundChCost;
    }

    public void setInboundChCost(BigDecimal inboundChCost) {
        this.inboundChCost = inboundChCost;
    }

    public BigDecimal getInboundChPrice() {
        return inboundChPrice;
    }

    public void setInboundChPrice(BigDecimal inboundChPrice) {
        this.inboundChPrice = inboundChPrice;
    }

    public Integer getInboundChQty() {
        return inboundChQty;
    }

    public void setInboundChQty(Integer inboundChQty) {
        this.inboundChQty = inboundChQty;
    }

    public BigDecimal getInboundInCost() {
        return inboundInCost;
    }

    public void setInboundInCost(BigDecimal inboundInCost) {
        this.inboundInCost = inboundInCost;
    }

    public BigDecimal getInboundInPrice() {
        return inboundInPrice;
    }

    public void setInboundInPrice(BigDecimal inboundInPrice) {
        this.inboundInPrice = inboundInPrice;
    }

    public Integer getInboundInQty() {
        return inboundInQty;
    }

    public void setInboundInQty(Integer inboundInQty) {
        this.inboundInQty = inboundInQty;
    }

    public Integer getIsBill() {
        return isBill;
    }

    public void setIsBill(Integer isBill) {
        this.isBill = isBill;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getOutboundAdQty() {
        return outboundAdQty;
    }

    public void setOutboundAdQty(Integer outboundAdQty) {
        this.outboundAdQty = outboundAdQty;
    }

    public BigDecimal getOutboundAdCost() {
        return outboundAdCost;
    }

    public void setOutboundAdCost(BigDecimal outboundAdCost) {
        this.outboundAdCost = outboundAdCost;
    }

    public BigDecimal getOutboundAdPrice() {
        return outboundAdPrice;
    }

    public void setOutboundAdPrice(BigDecimal outboundAdPrice) {
        this.outboundAdPrice = outboundAdPrice;
    }

    public Integer getOutboundChQty() {
        return outboundChQty;
    }

    public void setOutboundChQty(Integer outboundChQty) {
        this.outboundChQty = outboundChQty;
    }

    public BigDecimal getOutboundChCost() {
        return outboundChCost;
    }

    public void setOutboundChCost(BigDecimal outboundChCost) {
        this.outboundChCost = outboundChCost;
    }

    public BigDecimal getOutboundChPrice() {
        return outboundChPrice;
    }

    public void setOutboundChPrice(BigDecimal outboundChPrice) {
        this.outboundChPrice = outboundChPrice;
    }

    public Integer getOutboundInQty() {
        return outboundInQty;
    }

    public void setOutboundInQty(Integer outboundInQty) {
        this.outboundInQty = outboundInQty;
    }

    public BigDecimal getOutboundInCost() {
        return outboundInCost;
    }

    public void setOutboundInCost(BigDecimal outboundInCost) {
        this.outboundInCost = outboundInCost;
    }

    public BigDecimal getOutboundInPrice() {
        return outboundInPrice;
    }

    public void setOutboundInPrice(BigDecimal outboundInPrice) {
        this.outboundInPrice = outboundInPrice;
    }

    public String getCurCost() {
        return curCost;
    }

    public void setCurCost(String curCost) {
        this.curCost = curCost;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getInboundHotel() {
        return inboundHotel;
    }

    public void setInboundHotel(String inboundHotel) {
        this.inboundHotel = inboundHotel;
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

    public String getCurAmount() {
        return curAmount;
    }

    public void setCurAmount(String curAmount) {
        this.curAmount = curAmount;
    }

    public List getLandItineraries() {
        return landItineraries;
    }

    public void setLandItineraries(List landItineraries) {
        this.landItineraries = landItineraries;
    }

    public List getLandCities() {
        return landCities;
    }

    public void setLandCities(List landCities) {
        this.landCities = landCities;
    }
    
}
