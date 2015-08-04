package com.smi.travel.datalayer.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Surachai
 */
public class SystemUser {
    
     private String id;
     private MDepartment MDepartment;
     private Role role;
     private String name;
     private String username;
     private String password;
     private String position;
     private String tel;
     private String car;
     private String status;
     private String createBy;
     private Date createDate;
     private String apCode;
     private String arCode;
     private Set tourOperationDrivers = new HashSet(0);
     private Set masters = new HashSet(0);
     private Set tourOperationDescsForGuide2 = new HashSet(0);
     private Set transferJobsForDriverId = new HashSet(0);
     private Set transferJobsForGuildeId = new HashSet(0);
     private Set tourOperationDescsForGuide1 = new HashSet(0);
     private Set daytourBookings = new HashSet(0);
     private Set airticketBookingsForIssueBy = new HashSet(0);
     private Set airticketBookingsForOwnerBy = new HashSet(0);

    public SystemUser() {
    }

	
    public SystemUser(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }
    public SystemUser(MDepartment MDepartment, Role role, String name, String username, String password, String position, String tel, String car,String apCode,String arCode, String status, String createBy, Date createDate, Set tourOperationDrivers, Set masters, Set tourOperationDescsForGuide2, Set transferJobsForDriverId, Set transferJobsForGuildeId, Set tourOperationDescsForGuide1, Set daytourBookings, Set airticketBookingsForIssueBy, Set airticketBookingsForOwnerBy) {
       this.MDepartment = MDepartment;
       this.role = role;
       this.name = name;
       this.username = username;
       this.password = password;
       this.position = position;
       this.tel = tel;
       this.car = car;
       this.status = status;
       this.createBy = createBy;
       this.createDate = createDate;
       this.tourOperationDrivers = tourOperationDrivers;
       this.masters = masters;
       this.apCode = apCode;
       this.arCode = arCode;
       this.tourOperationDescsForGuide2 = tourOperationDescsForGuide2;
       this.transferJobsForDriverId = transferJobsForDriverId;
       this.transferJobsForGuildeId = transferJobsForGuildeId;
       this.tourOperationDescsForGuide1 = tourOperationDescsForGuide1;
       this.daytourBookings = daytourBookings;
       this.airticketBookingsForIssueBy = airticketBookingsForIssueBy;
       this.airticketBookingsForOwnerBy = airticketBookingsForOwnerBy;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public MDepartment getMDepartment() {
        return this.MDepartment;
    }
    
    public void setMDepartment(MDepartment MDepartment) {
        this.MDepartment = MDepartment;
    }
    public Role getRole() {
        return this.role;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }
    public String getName() {
        return this.name;
    }

    public String getApCode() {
        return apCode;
    }

    public void setApCode(String apCode) {
        this.apCode = apCode;
    }

    public String getArCode() {
        return arCode;
    }

    public void setArCode(String arCode) {
        this.arCode = arCode;
    }
    
    
    
    public void setName(String name) {
        this.name = name;
    }
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPosition() {
        return this.position;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }
    public String getTel() {
        return this.tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getCar() {
        return this.car;
    }
    
    public void setCar(String car) {
        this.car = car;
    }
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
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
    public Set getTourOperationDrivers() {
        return this.tourOperationDrivers;
    }
    
    public void setTourOperationDrivers(Set tourOperationDrivers) {
        this.tourOperationDrivers = tourOperationDrivers;
    }
    public Set getMasters() {
        return this.masters;
    }
    
    public void setMasters(Set masters) {
        this.masters = masters;
    }
    public Set getTourOperationDescsForGuide2() {
        return this.tourOperationDescsForGuide2;
    }
    
    public void setTourOperationDescsForGuide2(Set tourOperationDescsForGuide2) {
        this.tourOperationDescsForGuide2 = tourOperationDescsForGuide2;
    }
    public Set getTransferJobsForDriverId() {
        return this.transferJobsForDriverId;
    }
    
    public void setTransferJobsForDriverId(Set transferJobsForDriverId) {
        this.transferJobsForDriverId = transferJobsForDriverId;
    }
    public Set getTransferJobsForGuildeId() {
        return this.transferJobsForGuildeId;
    }
    
    public void setTransferJobsForGuildeId(Set transferJobsForGuildeId) {
        this.transferJobsForGuildeId = transferJobsForGuildeId;
    }
    public Set getTourOperationDescsForGuide1() {
        return this.tourOperationDescsForGuide1;
    }
    
    public void setTourOperationDescsForGuide1(Set tourOperationDescsForGuide1) {
        this.tourOperationDescsForGuide1 = tourOperationDescsForGuide1;
    }
    public Set getDaytourBookings() {
        return this.daytourBookings;
    }
    
    public void setDaytourBookings(Set daytourBookings) {
        this.daytourBookings = daytourBookings;
    }
    public Set getAirticketBookingsForIssueBy() {
        return this.airticketBookingsForIssueBy;
    }
    
    public void setAirticketBookingsForIssueBy(Set airticketBookingsForIssueBy) {
        this.airticketBookingsForIssueBy = airticketBookingsForIssueBy;
    }
    public Set getAirticketBookingsForOwnerBy() {
        return this.airticketBookingsForOwnerBy;
    }
    
    public void setAirticketBookingsForOwnerBy(Set airticketBookingsForOwnerBy) {
        this.airticketBookingsForOwnerBy = airticketBookingsForOwnerBy;
    }

    
    
}
