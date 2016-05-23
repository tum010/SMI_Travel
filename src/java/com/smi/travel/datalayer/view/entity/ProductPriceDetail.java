/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.entity;

import java.math.BigDecimal;
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
     private BigDecimal adCost;
     private BigDecimal chCost;
     private BigDecimal inCost;
     private BigDecimal adPrice;
     private BigDecimal chPrice;
     private BigDecimal inPrice;
     private String updateBy;
     private String ProductTypeId;
     private String ProductTypeName;
     private String ProductDepartment;
     
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

    public BigDecimal getAdCost() {
        return adCost;
    }

    public void setAdCost(BigDecimal adCost) {
        this.adCost = adCost;
    }

    public BigDecimal getChCost() {
        return chCost;
    }

    public void setChCost(BigDecimal chCost) {
        this.chCost = chCost;
    }

    public BigDecimal getInCost() {
        return inCost;
    }

    public void setInCost(BigDecimal inCost) {
        this.inCost = inCost;
    }

    public BigDecimal getAdPrice() {
        return adPrice;
    }

    public void setAdPrice(BigDecimal adPrice) {
        this.adPrice = adPrice;
    }

    public BigDecimal getChPrice() {
        return chPrice;
    }

    public void setChPrice(BigDecimal chPrice) {
        this.chPrice = chPrice;
    }

    public BigDecimal getInPrice() {
        return inPrice;
    }

    public void setInPrice(BigDecimal inPrice) {
        this.inPrice = inPrice;
    }

    public String getProductDepartment() {
        return ProductDepartment;
    }

    public void setProductDepartment(String ProductDepartment) {
        this.ProductDepartment = ProductDepartment;
    }
          
}
