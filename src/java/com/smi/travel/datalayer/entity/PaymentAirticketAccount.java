package com.smi.travel.datalayer.entity;
// Generated May 30, 2016 4:19:28 PM by Hibernate Tools 4.3.1


import com.smi.travel.datalayer.entity.PaymentAirticket;
import java.math.BigDecimal;
import java.util.Date;

/**
 * PaymentAirticketAccount generated by hbm2java
 */
public class PaymentAirticketAccount  {


     private String id;
     private PaymentAirticket paymentAirticket;
     private String accNo;
     private String detail;
     private String department;
     private BigDecimal drAmount;
     private BigDecimal crAmount;
     private Date bookDate;
     
    public PaymentAirticketAccount() {
    }

    public PaymentAirticketAccount(PaymentAirticket paymentAirticket, String accNo, String detail, String department, BigDecimal drAmount, BigDecimal crAmount) {
       this.paymentAirticket = paymentAirticket;
       this.accNo = accNo;
       this.detail = detail;
       this.department = department;
       this.drAmount = drAmount;
       this.crAmount = crAmount;
    }
   
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public PaymentAirticket getPaymentAirticket() {
        return this.paymentAirticket;
    }
    
    public void setPaymentAirticket(PaymentAirticket paymentAirticket) {
        this.paymentAirticket = paymentAirticket;
    }
    public String getAccNo() {
        return this.accNo;
    }
    
    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }
    public String getDetail() {
        return this.detail;
    }
    
    public void setDetail(String detail) {
        this.detail = detail;
    }
    public String getDepartment() {
        return this.department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    public BigDecimal getDrAmount() {
        return this.drAmount;
    }
    
    public void setDrAmount(BigDecimal drAmount) {
        this.drAmount = drAmount;
    }
    public BigDecimal getCrAmount() {
        return this.crAmount;
    }
    
    public void setCrAmount(BigDecimal crAmount) {
        this.crAmount = crAmount;
    }

    public Date getBookDate() {
        return bookDate;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }


}


