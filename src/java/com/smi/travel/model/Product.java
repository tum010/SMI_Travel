/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.model;

/**
 *
 * @author Surachai
 */
public class Product {


     private Integer productId;
     private Integer productType;
     private String productName;

    public Product() {
    }

    public Product(Integer productType, String productName) {
       this.productType = productType;
       this.productName = productName;
    }
   
    public Integer getProductId() {
        return this.productId;
    }
    
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public Integer getProductType() {
        return this.productType;
    }
    
    public void setProductType(Integer productType) {
        this.productType = productType;
    }
    public String getProductName() {
        return this.productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }




}

