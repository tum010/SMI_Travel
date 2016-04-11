package com.smi.travel.datalayer.entity;
// Generated Dec 22, 2014 5:59:06 PM by Hibernate Tools 3.6.0


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Master generated by hbm2java
 */
public class Master  {

     private String id;
     private MBookingstatus MBookingstatus;
     private PackageTour packageTour;
     private Customer customer;
     private SystemUser staff;
     private Agent agent;
     private String referenceNo;
     private Integer adult;
     private Integer child;
     private Integer infant;
     private Integer isPackage;
     private String agentRef;
     private Integer flagAir;
     private Integer flagHotel;
     private Integer flagDaytour;
     private Integer flagOther;
     private Integer flagLand;
     private String revisedBy;
     private Date revisedDate;
     private String bookingType;
     private String createBy;
     private String currency;
     private Date createDate;
     private String departmentNo;
     private Date operationDate;
     private String operationUser;
     private Set hotelBookings = new HashSet(0);
     private Set otherBookings = new HashSet(0);
     private Set billables = new HashSet(0);
     private Set airticketBookings = new HashSet(0);
     private Set passengers = new HashSet(0);
     private Set landBookings = new HashSet(0);
     private Set daytourBookings = new HashSet(0);
     private Set historyBookings = new HashSet(0);
     
    public Master() {
    }

	
    public Master(SystemUser staff, Agent agent, String referenceNo) {
       this.staff = staff;
       this.agent = agent;
       this.referenceNo = referenceNo;
    }
    public Master(MBookingstatus MBookingstatus,PackageTour packageTour, Customer customer, SystemUser staff, Agent agent, String referenceNo, Integer adult, Integer child, Integer infant, Integer isPackage,String agentRef, Integer flagAir, Integer flagHotel, Integer flagDaytour, Integer flagOther, Integer flagLand, String revisedBy, Date revisedDate, String bookingType, String createBy,String currency, Date createDate, Set hotelBookings, Set otherBookings, Set billables, Set airticketBookings, Set passengers, Set landBookings,Set daytourBookings, Set historyBookings,Date operationDate,String operationUser) {
       this.MBookingstatus = MBookingstatus;
       this.packageTour = packageTour;
       this.customer = customer;
       this.staff = staff;
       this.agent = agent;
       this.referenceNo = referenceNo;
       this.adult = adult;
       this.child = child;
       this.infant = infant;
       this.isPackage = isPackage;
       this.agentRef = agentRef;
       this.flagAir = flagAir;
       this.flagHotel = flagHotel;
       this.flagDaytour = flagDaytour;
       this.flagOther = flagOther;
       this.flagLand = flagLand;
       this.revisedBy = revisedBy;
       this.revisedDate = revisedDate;
       this.bookingType = bookingType;
       this.createBy = createBy;
       this.currency =currency;
       this.createDate = createDate;
       this.hotelBookings = hotelBookings;
       this.otherBookings = otherBookings;
       this.billables = billables;
       this.airticketBookings = airticketBookings;
       this.passengers = passengers;
       this.landBookings = landBookings;
       this.operationDate = operationDate;
       this.operationUser = operationUser;
       this.daytourBookings = daytourBookings;
       this.historyBookings = historyBookings;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public MBookingstatus getMBookingstatus() {
        return this.MBookingstatus;
    }
    
    public void setMBookingstatus(MBookingstatus MBookingstatus) {
        this.MBookingstatus = MBookingstatus;
    }

    public PackageTour getPackageTour() {
        return packageTour;
    }

    public void setPackageTour(PackageTour packageTour) {
        this.packageTour = packageTour;
    }
    
    
    
    public Customer getCustomer() {
        return this.customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public SystemUser getStaff() {
        return this.staff;
    }
    
    public void setStaff(SystemUser staff) {
        this.staff = staff;
    }
    public Agent getAgent() {
        return this.agent;
    }
    
    public void setAgent(Agent agent) {
        this.agent = agent;
    }
    public String getReferenceNo() {
        return this.referenceNo;
    }
    
    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
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
    public Integer getIsPackage() {
        return this.isPackage;
    }
    
    public void setIsPackage(Integer isPackage) {
        this.isPackage = isPackage;
    }

    public String getAgentRef() {
        return agentRef;
    }

    public void setAgentRef(String agentRef) {
        this.agentRef = agentRef;
    }
    
    
    public Integer getFlagAir() {
        return this.flagAir;
    }
    
    public void setFlagAir(Integer flagAir) {
        this.flagAir = flagAir;
    }
    public Integer getFlagHotel() {
        return this.flagHotel;
    }
    
    public void setFlagHotel(Integer flagHotel) {
        this.flagHotel = flagHotel;
    }
    public Integer getFlagDaytour() {
        return this.flagDaytour;
    }
    
    public void setFlagDaytour(Integer flagDaytour) {
        this.flagDaytour = flagDaytour;
    }
    public Integer getFlagOther() {
        return this.flagOther;
    }
    
    public void setFlagOther(Integer flagOther) {
        this.flagOther = flagOther;
    }
    public Integer getFlagLand() {
        return this.flagLand;
    }
    
    public void setFlagLand(Integer flagLand) {
        this.flagLand = flagLand;
    }
    public String getRevisedBy() {
        return this.revisedBy;
    }
    
    public void setRevisedBy(String revisedBy) {
        this.revisedBy = revisedBy;
    }
    public Date getRevisedDate() {
        return this.revisedDate;
    }
    
    public void setRevisedDate(Date revisedDate) {
        this.revisedDate = revisedDate;
    }
    public String getBookingType() {
        return this.bookingType;
    }
    
    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }
    public String getCreateBy() {
        return this.createBy;
    }
    
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    public Date getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Set getHotelBookings() {
        return this.hotelBookings;
    }
    
    public void setHotelBookings(Set hotelBookings) {
        this.hotelBookings = hotelBookings;
    }
    public Set getOtherBookings() {
        return this.otherBookings;
    }
    
    public void setOtherBookings(Set otherBookings) {
        this.otherBookings = otherBookings;
    }
    public Set getBillables() {
        return this.billables;
    }
    
    public void setBillables(Set billables) {
        this.billables = billables;
    }
    public Set getAirticketBookings() {
        return this.airticketBookings;
    }
    
    public void setAirticketBookings(Set airticketBookings) {
        this.airticketBookings = airticketBookings;
    }
    public Set getPassengers() {
        return this.passengers;
    }
    
    public void setPassengers(Set passengers) {
        this.passengers = passengers;
    }
    public Set getLandBookings() {
        return this.landBookings;
    }
    
    public void setLandBookings(Set landBookings) {
        this.landBookings = landBookings;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Set getDaytourBookings() {
        return daytourBookings;
    }

    public void setDaytourBookings(Set daytourBookings) {
        this.daytourBookings = daytourBookings;
    }

    public Set getHistoryBookings() {
        return historyBookings;
    }

    public void setHistoryBookings(Set historyBookings) {
        this.historyBookings = historyBookings;
    }

    public String getDepartmentNo() {
        return departmentNo;
    }

    public void setDepartmentNo(String departmentNo) {
        this.departmentNo = departmentNo;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public String getOperationUser() {
        return operationUser;
    }

    public void setOperationUser(String operationUser) {
        this.operationUser = operationUser;
    }



    


}


