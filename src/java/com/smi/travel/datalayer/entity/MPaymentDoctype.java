package com.smi.travel.datalayer.entity;
// Generated Jul 7, 2015 10:08:38 AM by Hibernate Tools 3.6.0

import java.util.LinkedList;
import java.util.List;



public class MPaymentDoctype   {


     private String id;
     private String name;
     private String department;
     private List paymentWendies = new LinkedList<PaymentWendy>();

    public MPaymentDoctype() {
        
    }

    public MPaymentDoctype(String name) {
        this.name = name;
    }
    
    public MPaymentDoctype(String name, List paymentWendies,String department) {
       this.name = name;
       this.paymentWendies = paymentWendies;
       this.department = department;
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
    public List getPaymentWendies() {
        return this.paymentWendies;
    }
    
    public void setPaymentWendies(List paymentWendies) {
        this.paymentWendies = paymentWendies;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

}


