package com.smi.travel.datalayer.entity;
// Generated Sep 22, 2015 3:45:35 PM by Hibernate Tools 3.6.0



/**
 * PaymentWendyReference generated by hbm2java
 */
public class PaymentWendyReference {


     private String id;
     private Master master;
     private PaymentDetailWendy paymentDetailWendy;

    public PaymentWendyReference() {
    }

    public PaymentWendyReference(Master master, PaymentDetailWendy paymentDetailWendy) {
       this.master = master;
       this.paymentDetailWendy = paymentDetailWendy;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
   
    public Master getMaster() {
        return this.master;
    }
    
    public void setMaster(Master master) {
        this.master = master;
    }
    public PaymentDetailWendy getPaymentDetailWendy() {
        return this.paymentDetailWendy;
    }
    
    public void setPaymentDetailWendy(PaymentDetailWendy paymentDetailWendy) {
        this.paymentDetailWendy = paymentDetailWendy;
    }

}


