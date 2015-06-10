/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.entity;

import java.util.Date;

/**
 *
 * @author Thitikul
 */
public class BookingView {
    
    private String refno;
    private String agentCode;
    private String leaderName;
    private String statusName;
    private String pnr;
    private String hotelName;
    private Date firstDepartDate;
    private Date firstCheckinDate;
    private Date createDate;
    private String createBy;
    private String departmentName;
    private String departmentId;
    private String statusId;
    private String tel;
    private String remark;
    private String ticketno;
    
    public BookingView(){
        
    }

    public String getRefno() {
        return refno;
    }

    public void setRefno(String refno) {
        this.refno = refno;
    }

    
    
    

    public String getAgentCode() {
        return this.agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }
    
    public String getLeaderName() {
        return this.leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }
    
    public String getStatusName() {
        return this.statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
    
    public String getPnr() {
        return this.pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }
    
    public String getHotelName() {
        return this.hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
    
    public String getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    
    public String getDepartmentName() {
        return this.departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    
    public Date getFirstDepartDate() {
        return this.firstDepartDate;
    }

    public void setFirstDepartDate(Date firstDepartDate) {
        this.firstDepartDate = firstDepartDate;
    }
    
    public Date getFirstCheckinDate() {
        return this.firstCheckinDate;
    }

    public void setFirstCheckinDate(Date firstCheckinDate) {
        this.firstCheckinDate = firstCheckinDate;
    }
    
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTicketno() {
        return ticketno;
    }

    public void setTicketno(String ticketno) {
        this.ticketno = ticketno;
    }
    
    
    
}
