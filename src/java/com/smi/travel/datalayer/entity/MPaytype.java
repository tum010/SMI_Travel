package com.smi.travel.datalayer.entity;
// Generated Jul 7, 2015 10:08:38 AM by Hibernate Tools 3.6.0

import java.util.LinkedList;
import java.util.List;



/**
 * MPaytype generated by hbm2java
 */
public class MPaytype   {


     private String id;
     private String name;
     private String accCode;
     private String accName;
     private List paymentDetailWendies = new LinkedList<PaymentDetailWendy>();

    public MPaytype() {
    }

	
    public MPaytype(String name) {
        this.name = name;
    }
    public MPaytype(String name, List paymentDetailWendies) {
       this.name = name;
       this.paymentDetailWendies = paymentDetailWendies;
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
    public List getPaymentDetailWendies() {
        return this.paymentDetailWendies;
    }
    
    public void setPaymentDetailWendies(List paymentDetailWendies) {
        this.paymentDetailWendies = paymentDetailWendies;
    }

    public String getAccCode() {
        return accCode;
    }

    public void setAccCode(String accCode) {
        this.accCode = accCode;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

}


