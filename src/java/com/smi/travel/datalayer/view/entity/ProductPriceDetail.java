/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.entity;

import java.util.Date;


/**
 *
 * @author Surachai
 */

public class ProductPriceDetail {
    
     private String id;
     private String code;
     private String name;
     private String description;
     private Date effectiveFrom;
     private Date effectiveTo;
     private Integer adCost;
     private Integer chCost;
     private Integer inCost;
     private Integer adPrice;
     private Integer chPrice;
     private Integer inPrice;
     private String updateBy;
     private String ProductTypeId;
     private String ProductTypeName;
     
    public ProductPriceDetail(){
         
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    

    public Date getEffectiveFrom() {
        return effectiveFrom;
    }

    public void setEffectiveFrom(Date effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }

    public Date getEffectiveTo() {
        return effectiveTo;
    }

    public void setEffectiveTo(Date effectiveTo) {
        this.effectiveTo = effectiveTo;
    }

    public Integer getAdCost() {
        return adCost;
    }

    public void setAdCost(Integer adCost) {
        this.adCost = adCost;
    }

    public Integer getChCost() {
        return chCost;
    }

    public void setChCost(Integer chCost) {
        this.chCost = chCost;
    }

    public Integer getInCost() {
        return inCost;
    }

    public void setInCost(Integer inCost) {
        this.inCost = inCost;
    }

    public Integer getAdPrice() {
        return adPrice;
    }

    public void setAdPrice(Integer adPrice) {
        this.adPrice = adPrice;
    }

    public Integer getChPrice() {
        return chPrice;
    }

    public void setChPrice(Integer chPrice) {
        this.chPrice = chPrice;
    }

    public Integer getInPrice() {
        return inPrice;
    }

    public void setInPrice(Integer inPrice) {
        this.inPrice = inPrice;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getProductTypeId() {
        return ProductTypeId;
    }

    public void setProductTypeId(String ProductTypeId) {
        this.ProductTypeId = ProductTypeId;
    }

    public String getProductTypeName() {
        return ProductTypeName;
    }

    public void setProductTypeName(String ProductTypeName) {
        this.ProductTypeName = ProductTypeName;
    }
     
    
     
}
