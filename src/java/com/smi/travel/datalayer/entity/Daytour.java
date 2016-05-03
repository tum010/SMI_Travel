/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Surachai
 */
public class Daytour {
     private String id;
     private String code;
     private String name;
     private Integer min;
     private Integer max;
     private String remark;
     private String condition;
     private String createBy;
     private String updateBy;
     private String status = "active";
     private BigDecimal guideComission;
     private Set agentTourComissions = new HashSet(0);
     private Set daytourBookings = new HashSet(0);
     private Set daytourPrices = new HashSet(0);
     private Set daytourExpenses = new HashSet(0);
     private Set tourOperationDescs = new HashSet(0);

    public Daytour() {
    }

	
    public Daytour(String code, String name) {
        this.code = code;
        this.name = name;
    }
    public Daytour(String code, String name, Integer min, Integer max, String remark, String condition, String createBy, String updateBy, String status, BigDecimal guideComission, Set agentTourComissions, Set daytourBookings, Set daytourPrices, Set daytourExpenses, Set tourOperationDescs) {
       this.code = code;
       this.name = name;
       this.min = min;
       this.max = max;
       this.remark = remark;
       this.condition = condition;
       this.createBy = createBy;
       this.updateBy = updateBy;
       this.status = status;
       this.guideComission = guideComission;
       this.agentTourComissions = agentTourComissions;
       this.daytourBookings = daytourBookings;
       this.daytourPrices = daytourPrices;
       this.daytourExpenses = daytourExpenses;
       this.tourOperationDescs = tourOperationDescs;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public Integer getMin() {
        return this.min;
    }
    
    public void setMin(Integer min) {
        this.min = min;
    }
    public Integer getMax() {
        return this.max;
    }
    
    public void setMax(Integer max) {
        this.max = max;
    }
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getCondition() {
        return this.condition;
    }
    
    public void setCondition(String condition) {
        this.condition = condition;
    }
    public String getCreateBy() {
        return this.createBy;
    }
    
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    public String getUpdateBy() {
        return this.updateBy;
    }
    
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public Set getAgentTourComissions() {
        return this.agentTourComissions;
    }
    
    public void setAgentTourComissions(Set agentTourComissions) {
        this.agentTourComissions = agentTourComissions;
    }
    public Set getDaytourBookings() {
        return this.daytourBookings;
    }
    
    public void setDaytourBookings(Set daytourBookings) {
        this.daytourBookings = daytourBookings;
    }
    public Set getDaytourPrices() {
        return this.daytourPrices;
    }
    
    public void setDaytourPrices(Set daytourPrices) {
        this.daytourPrices = daytourPrices;
    }
    public Set getDaytourExpenses() {
        return this.daytourExpenses;
    }
    
    public void setDaytourExpenses(Set daytourExpenses) {
        this.daytourExpenses = daytourExpenses;
    }
    public Set getTourOperationDescs() {
        return this.tourOperationDescs;
    }
    
    public void setTourOperationDescs(Set tourOperationDescs) {
        this.tourOperationDescs = tourOperationDescs;
    }

    public Object get(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public BigDecimal getGuideComission() {
        return guideComission;
    }

    public void setGuideComission(BigDecimal guideComission) {
        this.guideComission = guideComission;
    }

}
