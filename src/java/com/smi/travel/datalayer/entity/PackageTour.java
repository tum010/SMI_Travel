/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.entity;

import java.util.List;


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
    private List packageItineraries;
    private List packagePrices;
    private List packageCities;
    
    public PackageTour(){
         
    }
    
    public PackageTour(String code) {
        this.code = code;
    }
    
    public PackageTour(String code, String name, String detail, String remark, String status, List packageItineraries, List packagePrices,List packageCities) {
       this.code = code;
       this.name = name;
       this.detail = detail;
       this.remark = remark;
       this.status = status;
       this.packageItineraries = packageItineraries;
       this.packagePrices = packagePrices;
       this.packageCities = packageCities;
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

    public List getPackageItineraries() {
        return packageItineraries;
    }

    public void setPackageItineraries(List packageItineraries) {
        this.packageItineraries = packageItineraries;
    }

    public List getPackagePrices() {
        return packagePrices;
    }

    public void setPackagePrices(List packagePrices) {
        this.packagePrices = packagePrices;
    }

    public List getPackageCities() {
        return packageCities;
    }

    public void setPackageCities(List packageCities) {
        this.packageCities = packageCities;
    }
    
    
    
    
}
