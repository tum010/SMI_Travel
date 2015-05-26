/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Surachai
 */
public class Role {
     private String id;
     private String name;
     private String createBy;
     private Date updateBy;
     private String createDate;
     private Date updateDate;
     private Set<RoleMapping> roleMappings = new HashSet(0);
     private Set<SystemUser> staffs = new HashSet<SystemUser>(0);
    public Role() {
        
    }

	
    public Role(String name) {
        this.name = name;
    }
    
    public Role(String name, String createBy, Date updateBy, String createDate, Date updateDate,Set roleMappings , Set<SystemUser> staffs) {
       this.name = name;
       this.createBy = createBy;
       this.updateBy = updateBy;
       this.createDate = createDate;
       this.updateDate = updateDate;
       this.roleMappings = roleMappings;
       this.staffs = staffs;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getCreateBy() {
        return this.createBy;
    }
    
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    public Date getUpdateBy() {
        return this.updateBy;
    }
    
    public void setUpdateBy(Date updateBy) {
        this.updateBy = updateBy;
    }
    public String getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    public Date getUpdateDate() {
        return this.updateDate;
    }
    
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    
    public Set getRoleMappings() {
        return this.roleMappings;
    }
    
    public void setRoleMappings(Set roleMappings) {
        this.roleMappings = roleMappings;
    }
    
    
    public Set<SystemUser> getStaffs() {
        return this.staffs;
    }
    
    public void setStaffs(Set<SystemUser> staffs) {
        this.staffs = staffs;
    }
  
}
