/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.entity;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Surachai
 */
public class PackageTour {
    private String id;
    private String code;
    private String name;
    private String detail;
    private String remark;
    private String status;
    private Set packageItineraries = new HashSet(0);
    private Set packagePrices = new HashSet(0);
    
    public PackageTour(){
         
    }
    
    public PackageTour(String code) {
        this.code = code;
    }
    
    public PackageTour(String code, String name, String detail, String remark, String status, Set packageItineraries, Set packagePrices) {
       this.code = code;
       this.name = name;
       this.detail = detail;
       this.remark = remark;
       this.status = status;
       this.packageItineraries = packageItineraries;
       this.packagePrices = packagePrices;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set getPackageItineraries() {
        return packageItineraries;
    }

    public void setPackageItineraries(Set packageItineraries) {
        this.packageItineraries = packageItineraries;
    }

    public Set getPackagePrices() {
        return packagePrices;
    }

    public void setPackagePrices(Set packagePrices) {
        this.packagePrices = packagePrices;
    }
    
    
    
    
}
