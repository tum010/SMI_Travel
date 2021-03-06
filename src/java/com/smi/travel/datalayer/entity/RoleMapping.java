package com.smi.travel.datalayer.entity;
// Generated Dec 17, 2014 3:54:36 PM by Hibernate Tools 3.6.0


import java.util.Date;

/**
 * RoleMapping generated by hbm2java
 */
public class RoleMapping  {

     private String id;
     private Function function;
     private Role role;
     private String createBy;
     private Date createDate;
     private String updateBy;
     private Date updateDate;

    public RoleMapping() {
    }

	
    public RoleMapping(Function function, Role role) {
        this.function = function;
        this.role = role;
    }
    public RoleMapping(Function function, Role role, String createBy, Date createDate, String updateBy, Date updateDate) {
       this.function = function;
       this.role = role;
       this.createBy = createBy;
       this.createDate = createDate;
       this.updateBy = updateBy;
       this.updateDate = updateDate;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public Function getFunction() {
        return this.function;
    }
    
    public void setFunction(Function function) {
        this.function = function;
    }
    public Role getRole() {
        return this.role;
    }
    
    public void setRole(Role role) {
        this.role = role;
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
    public String getUpdateBy() {
        return this.updateBy;
    }
    
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
    public Date getUpdateDate() {
        return this.updateDate;
    }
    
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

}


