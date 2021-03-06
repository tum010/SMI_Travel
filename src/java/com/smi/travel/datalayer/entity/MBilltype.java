package com.smi.travel.datalayer.entity;
// Generated Jan 5, 2015 11:54:29 AM by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * MBilltype generated by hbm2java
 */
public class MBilltype  {


     private String id;
     private String name;
     private String accCode;
     private String department;
     private Integer accNo;
     private String otAccCode;
     private String onAccCode;
     private Set billableDescs = new HashSet(0);

    public MBilltype() {
    }

	
    public MBilltype(String name) {
        this.name = name;
    }
    public MBilltype(String name, Set billableDescs) {
       this.name = name;
       this.billableDescs = billableDescs;
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
    public Set getBillableDescs() {
        return this.billableDescs;
    }
    
    public void setBillableDescs(Set billableDescs) {
        this.billableDescs = billableDescs;
    }

    public String getAccCode() {
        return accCode;
    }

    public void setAccCode(String accCode) {
        this.accCode = accCode;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getAccNo() {
        return accNo;
    }

    public void setAccNo(Integer accNo) {
        this.accNo = accNo;
    }

    public String getOtAccCode() {
        return otAccCode;
    }

    public void setOtAccCode(String otAccCode) {
        this.otAccCode = otAccCode;
    }

    public String getOnAccCode() {
        return onAccCode;
    }

    public void setOnAccCode(String onAccCode) {
        this.onAccCode = onAccCode;
    }




}


