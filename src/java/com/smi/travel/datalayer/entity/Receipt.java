package com.smi.travel.datalayer.entity;
// Generated Jul 20, 2015 5:52:41 PM by Hibernate Tools 4.3.1


import com.smi.travel.datalayer.entity.MFinanceItemstatus;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Receipt generated by hbm2java
 */
public class Receipt {


     private String id;
     private MFinanceItemstatus MFinanceItemstatus;
     private String recNo;
     private String recFrom;
     private String recName;
     private String recAddress;
     private String arCode;
     private Date recDate;
     private Integer payType;
     private String remark;
     private BigDecimal withTax;
     private BigDecimal cashAmount;
     private BigDecimal cashMinusAmount;
     private BigDecimal bankTransfer;
     private String chqBank1;
     private String chqBank2;
     private Date chqDate1;
     private Date chqDate2;
     private BigDecimal chqAmount1;
     private BigDecimal chqAmount2;
     private List receiptDetails = new LinkedList<ReceiptDetail>();
     private List receiptCredits = new LinkedList<ReceiptCredit>();

    public Receipt() {
    }

    public Receipt(MFinanceItemstatus MFinanceItemstatus, String recNo, String recFrom, String recName, String recAddress, String arCode, Date recDate, Integer payType, String remark, BigDecimal withTax, BigDecimal cashAmount, BigDecimal cashMinusAmount, BigDecimal bankTransfer, String chqBank1, String chqBank2, Date chqDate1, Date chqDate2, BigDecimal chqAmount1, BigDecimal chqAmount2, List receiptDetails, List receiptCredits) {
       this.MFinanceItemstatus = MFinanceItemstatus;
       this.recNo = recNo;
       this.recFrom = recFrom;
       this.recName = recName;
       this.recAddress = recAddress;
       this.arCode = arCode;
       this.recDate = recDate;
       this.payType = payType;
       this.remark = remark;
       this.withTax = withTax;
       this.cashAmount = cashAmount;
       this.cashMinusAmount = cashMinusAmount;
       this.bankTransfer = bankTransfer;
       this.chqBank1 = chqBank1;
       this.chqBank2 = chqBank2;
       this.chqDate1 = chqDate1;
       this.chqDate2 = chqDate2;
       this.chqAmount1 = chqAmount1;
       this.chqAmount2 = chqAmount2;
       this.receiptDetails = receiptDetails;
       this.receiptCredits = receiptCredits;
    }
   
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public MFinanceItemstatus getMFinanceItemstatus() {
        return this.MFinanceItemstatus;
    }
    
    public void setMFinanceItemstatus(MFinanceItemstatus MFinanceItemstatus) {
        this.MFinanceItemstatus = MFinanceItemstatus;
    }
    public String getRecNo() {
        return this.recNo;
    }
    
    public void setRecNo(String recNo) {
        this.recNo = recNo;
    }
    public String getRecFrom() {
        return this.recFrom;
    }
    
    public void setRecFrom(String recFrom) {
        this.recFrom = recFrom;
    }
    public String getRecName() {
        return this.recName;
    }
    
    public void setRecName(String recName) {
        this.recName = recName;
    }
    public String getRecAddress() {
        return this.recAddress;
    }
    
    public void setRecAddress(String recAddress) {
        this.recAddress = recAddress;
    }
    public String getArCode() {
        return this.arCode;
    }
    
    public void setArCode(String arCode) {
        this.arCode = arCode;
    }
    public Date getRecDate() {
        return this.recDate;
    }
    
    public void setRecDate(Date recDate) {
        this.recDate = recDate;
    }
    public Integer getPayType() {
        return this.payType;
    }
    
    public void setPayType(Integer payType) {
        this.payType = payType;
    }
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public BigDecimal getWithTax() {
        return this.withTax;
    }
    
    public void setWithTax(BigDecimal withTax) {
        this.withTax = withTax;
    }
    public BigDecimal getCashAmount() {
        return this.cashAmount;
    }
    
    public void setCashAmount(BigDecimal cashAmount) {
        this.cashAmount = cashAmount;
    }
    public BigDecimal getCashMinusAmount() {
        return this.cashMinusAmount;
    }
    
    public void setCashMinusAmount(BigDecimal cashMinusAmount) {
        this.cashMinusAmount = cashMinusAmount;
    }
    public BigDecimal getBankTransfer() {
        return this.bankTransfer;
    }
    
    public void setBankTransfer(BigDecimal bankTransfer) {
        this.bankTransfer = bankTransfer;
    }
    public String getChqBank1() {
        return this.chqBank1;
    }
    
    public void setChqBank1(String chqBank1) {
        this.chqBank1 = chqBank1;
    }
    public String getChqBank2() {
        return this.chqBank2;
    }
    
    public void setChqBank2(String chqBank2) {
        this.chqBank2 = chqBank2;
    }
    public Date getChqDate1() {
        return this.chqDate1;
    }
    
    public void setChqDate1(Date chqDate1) {
        this.chqDate1 = chqDate1;
    }
    public Date getChqDate2() {
        return this.chqDate2;
    }
    
    public void setChqDate2(Date chqDate2) {
        this.chqDate2 = chqDate2;
    }
    public BigDecimal getChqAmount1() {
        return this.chqAmount1;
    }
    
    public void setChqAmount1(BigDecimal chqAmount1) {
        this.chqAmount1 = chqAmount1;
    }
    public BigDecimal getChqAmount2() {
        return this.chqAmount2;
    }
    
    public void setChqAmount2(BigDecimal chqAmount2) {
        this.chqAmount2 = chqAmount2;
    }

    public List getReceiptDetails() {
        return receiptDetails;
    }

    public void setReceiptDetails(List receiptDetails) {
        this.receiptDetails = receiptDetails;
    }

    public List getReceiptCredits() {
        return receiptCredits;
    }

    public void setReceiptCredits(List receiptCredits) {
        this.receiptCredits = receiptCredits;
    }





}


