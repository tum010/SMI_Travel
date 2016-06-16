package com.smi.travel.datalayer.entity;
// Generated Mar 18, 2015 10:03:25 AM by Hibernate Tools 3.6.0


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * DaytourBooking generated by hbm2java
 */
public class DaytourBooking   {


     private String id;
     private Place place;
     private MItemstatus MItemstatus;
     private SystemUser guide;
     private Daytour daytour;
     private Master master;
     private Agent agent;
     private Date tourDate;
     private String pickupDetail;
     private String pickupRoom;
     private Date pickupTime;
     private String requirement;
     private String remark;
     private String memo;
     private Integer isPay;
     private Integer adult;
     private Integer child;
     private Integer infant;
     private Integer isBill;
     private BigDecimal agentComission;
     private BigDecimal guideCommission;
     private String remarkGuideCom;
     private String remarkAgentCom;
     private Integer pickupOrder;
     private String guideTour;
     private Set coupons = new HashSet(0);
     private Set daytourBookingPrices = new HashSet(0);

    public DaytourBooking() {
    }

	
    public DaytourBooking(Daytour daytour, Master master) {
        this.daytour = daytour;
        this.master = master;
    }
    public DaytourBooking(Place place, MItemstatus MItemstatus, SystemUser guide, Daytour daytour, Master master, Agent agent, Date tourDate, String pickupDetail, String pickupRoom, Date pickupTime, String requirement, String remark, String memo, Integer isPay, Integer adult, Integer child, Integer infant, Integer isBill, BigDecimal agentComission, BigDecimal guideCommission, String remarkGuideCom, String remarkAgentCom,Integer pickupOrder, Set coupons, Set daytourBookingPrices) {
       this.place = place;
       this.MItemstatus = MItemstatus;
       this.guide = guide;
       this.daytour = daytour;
       this.master = master;
       this.agent = agent;
       this.tourDate = tourDate;
       this.pickupDetail = pickupDetail;
       this.pickupRoom = pickupRoom;
       this.pickupTime = pickupTime;
       this.requirement = requirement;
       this.remark = remark;
       this.memo = memo;
       this.isPay = isPay;
       this.adult = adult;
       this.child = child;
       this.infant = infant;
       this.isBill = isBill;
       this.agentComission = agentComission;
       this.guideCommission = guideCommission;
       this.remarkGuideCom = remarkGuideCom;
       this.remarkAgentCom = remarkAgentCom;
       this.pickupOrder  = pickupOrder;
       this.coupons = coupons;
       this.daytourBookingPrices = daytourBookingPrices;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public Place getPlace() {
        return this.place;
    }
    
    public void setPlace(Place place) {
        this.place = place;
    }
    public MItemstatus getMItemstatus() {
        return this.MItemstatus;
    }
    
    public void setMItemstatus(MItemstatus MItemstatus) {
        this.MItemstatus = MItemstatus;
    }

    public SystemUser getGuide() {
        return guide;
    }

    public void setGuide(SystemUser guide) {
        this.guide = guide;
    }
    
    
    
    public Daytour getDaytour() {
        return this.daytour;
    }
    
    public void setDaytour(Daytour daytour) {
        this.daytour = daytour;
    }
    public Master getMaster() {
        return this.master;
    }
    
    public void setMaster(Master master) {
        this.master = master;
    }
    public Agent getAgent() {
        return this.agent;
    }
    
    public void setAgent(Agent agent) {
        this.agent = agent;
    }
    public Date getTourDate() {
        return this.tourDate;
    }
    
    public void setTourDate(Date tourDate) {
        this.tourDate = tourDate;
    }
    public String getPickupDetail() {
        return this.pickupDetail;
    }
    
    public void setPickupDetail(String pickupDetail) {
        this.pickupDetail = pickupDetail;
    }
    public String getPickupRoom() {
        return this.pickupRoom;
    }
    
    public void setPickupRoom(String pickupRoom) {
        this.pickupRoom = pickupRoom;
    }
    public Date getPickupTime() {
        return this.pickupTime;
    }
    
    public void setPickupTime(Date pickupTime) {
        this.pickupTime = pickupTime;
    }
    public String getRequirement() {
        return this.requirement;
    }
    
    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getMemo() {
        return this.memo;
    }
    
    public void setMemo(String memo) {
        this.memo = memo;
    }
    public Integer getIsPay() {
        return this.isPay;
    }
    
    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }
    public Integer getAdult() {
        return this.adult;
    }
    
    public void setAdult(Integer adult) {
        this.adult = adult;
    }
    public Integer getChild() {
        return this.child;
    }
    
    public void setChild(Integer child) {
        this.child = child;
    }
    public Integer getInfant() {
        return this.infant;
    }
    
    public void setInfant(Integer infant) {
        this.infant = infant;
    }
    public Integer getIsBill() {
        return this.isBill;
    }
    
    public void setIsBill(Integer isBill) {
        this.isBill = isBill;
    }
   
    public String getRemarkGuideCom() {
        return this.remarkGuideCom;
    }
    
    public void setRemarkGuideCom(String remarkGuideCom) {
        this.remarkGuideCom = remarkGuideCom;
    }
    public String getRemarkAgentCom() {
        return this.remarkAgentCom;
    }
    
    public void setRemarkAgentCom(String remarkAgentCom) {
        this.remarkAgentCom = remarkAgentCom;
    }
    public Set getCoupons() {
        return this.coupons;
    }
    
    public void setCoupons(Set coupons) {
        this.coupons = coupons;
    }
    public Set getDaytourBookingPrices() {
        return this.daytourBookingPrices;
    }
    
    public void setDaytourBookingPrices(Set daytourBookingPrices) {
        this.daytourBookingPrices = daytourBookingPrices;
    }

    public Integer getPickupOrder() {
        return pickupOrder;
    }

    public void setPickupOrder(Integer pickupOrder) {
        this.pickupOrder = pickupOrder;
    }

    public BigDecimal getAgentComission() {
        return agentComission;
    }

    public void setAgentComission(BigDecimal agentComission) {
        this.agentComission = agentComission;
    }

    public BigDecimal getGuideCommission() {
        return guideCommission;
    }

    public void setGuideCommission(BigDecimal guideCommission) {
        this.guideCommission = guideCommission;
    }

    public String getGuideTour() {
        return guideTour;
    }

    public void setGuideTour(String guideTour) {
        this.guideTour = guideTour;
    }

    


}


