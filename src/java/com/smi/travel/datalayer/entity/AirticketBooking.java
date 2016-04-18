package com.smi.travel.datalayer.entity;
// Generated Jan 13, 2015 9:49:49 AM by Hibernate Tools 3.6.0


import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * AirticketBooking generated by hbm2java
 */
public class AirticketBooking {


     private String id;
     private SystemUser staffByIssueBy;
     private Master master;
     private SystemUser staffByOwnerBy;
     private Date deadline;
     private String reConfirm;
     private String remark;
     private Integer groupPax;
     private Date issuedate;
     private Integer isPickup;
     private List airticketDescs = new LinkedList<AirticketDesc>();
     private List airticketPnrs = new LinkedList<AirticketPnr>();

    public AirticketBooking() {
    }

	
    public AirticketBooking(Master master) {
        this.master = master;
    }
    public AirticketBooking(SystemUser staffByIssueBy, Master master, SystemUser staffByOwnerBy, Date deadline, String reConfirm, List airticketDescs, List airticketPnrs) {
       this.staffByIssueBy = staffByIssueBy;
       this.master = master;
       this.staffByOwnerBy = staffByOwnerBy;
       this.deadline = deadline;
       this.reConfirm = reConfirm;
       this.airticketDescs = airticketDescs;
       this.airticketPnrs = airticketPnrs;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public SystemUser getStaffByIssueBy() {
        return this.staffByIssueBy;
    }
    
    public void setStaffByIssueBy(SystemUser staffByIssueBy) {
        this.staffByIssueBy = staffByIssueBy;
    }
    public Master getMaster() {
        return this.master;
    }
    
    public void setMaster(Master master) {
        this.master = master;
    }
    public SystemUser getStaffByOwnerBy() {
        return this.staffByOwnerBy;
    }
    
    public void setStaffByOwnerBy(SystemUser staffByOwnerBy) {
        this.staffByOwnerBy = staffByOwnerBy;
    }

    public Date getDeadline() {
        return this.deadline;
    }
    
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
    public String getReConfirm() {
        return this.reConfirm;
    }
    
    public void setReConfirm(String reConfirm) {
        this.reConfirm = reConfirm;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public List getAirticketDescs() {
        return this.airticketDescs;
    }
    
    public void setAirticketDescs(List airticketDescs) {
        this.airticketDescs = airticketDescs;
    }
    public List getAirticketPnrs() {
        return this.airticketPnrs;
    }
    
    public void setAirticketPnrs(List airticketPnrs) {
        this.airticketPnrs = airticketPnrs;
    }

    public Integer getGroupPax() {
        return groupPax;
    }

    public void setGroupPax(Integer groupPax) {
        this.groupPax = groupPax;
    }

    public Date getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(Date issuedate) {
        this.issuedate = issuedate;
    }

    public Integer getIsPickup() {
        return isPickup;
    }

    public void setIsPickup(Integer isPickup) {
        this.isPickup = isPickup;
    }




}


