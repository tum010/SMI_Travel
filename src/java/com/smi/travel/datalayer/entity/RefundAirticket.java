package com.smi.travel.datalayer.entity;
// Generated Jul 9, 2015 9:38:50 AM by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * RefundAirticket generated by hbm2java
 */
public class RefundAirticket {


     private String id;
     private Agent agent;
     private Master master;
     private String refundNo;
     private Date refundDate;
     private String refundBy;
     private String remark;
     private String receiveBy;
//     private String receiveByName;
     private Date receiveDate;
     private String address;
     private Integer status;
     private String ownerBy;
     private String refundType;
     private String otherReason;
     private String refundByName;

     private List<RefundAirticketDetail> refundAirticketDetails = new LinkedList<RefundAirticketDetail>();

    public RefundAirticket() {
    }

    public RefundAirticket(Agent agent, String refundNo, Date refundDate, String refundBy, String remark, List refundAirticketDetails) {
       this.agent = agent;
       this.refundNo = refundNo;
       this.refundDate = refundDate;
       this.refundBy = refundBy;
       this.remark = remark;
       this.refundAirticketDetails = refundAirticketDetails;
    }

    public String getId() {
        return id;
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
    public String getRefundNo() {
        return this.refundNo;
    }
    
    public void setRefundNo(String refundNo) {
        this.refundNo = refundNo;
    }
    public Date getRefundDate() {
        return this.refundDate;
    }
    
    public void setRefundDate(Date refundDate) {
        this.refundDate = refundDate;
    }
    public String getRefundBy() {
        return this.refundBy;
    }
    
    public void setRefundBy(String refundBy) {
        this.refundBy = refundBy;
    }
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List getRefundAirticketDetails() {
        return refundAirticketDetails;
    }

    public void setRefundAirticketDetails(List refundAirticketDetails) {
        this.refundAirticketDetails = refundAirticketDetails;
    }

    /**
     * @return the receiveDate
     */
    public Date getReceiveDate() {
        return receiveDate;
    }

    /**
     * @param receiveDate the receiveDate to set
     */
    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    /**
     * @return the receiveBy
     */
    public String getReceiveBy() {
        return receiveBy;
    }

    /**
     * @param receiveBy the receiveBy to set
     */
    public void setReceiveBy(String receiveBy) {
        this.receiveBy = receiveBy;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public String getOwnerBy() {
        return ownerBy;
    }

    public void setOwnerBy(String ownerBy) {
        this.ownerBy = ownerBy;
    }

    public String getRefundType() {
        return refundType;
    }

    public void setRefundType(String refundType) {
        this.refundType = refundType;
    }

    public String getOtherReason() {
        return otherReason;
    }

    public void setOtherReason(String otherReason) {
        this.otherReason = otherReason;
    }

    public String getRefundByName() {
        return refundByName;
    }

    public void setRefundByName(String refundByName) {
        this.refundByName = refundByName;
    }

}


