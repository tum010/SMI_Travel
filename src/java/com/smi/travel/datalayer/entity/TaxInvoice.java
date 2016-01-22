package com.smi.travel.datalayer.entity;
// Generated Aug 15, 2015 12:01:47 AM by Hibernate Tools 3.6.0


import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * TaxInvoice generated by hbm2java
 */
public class TaxInvoice   {


     private String id;
     private MFinanceItemstatus MFinanceItemstatus;
     private Passenger passenger;
     private String taxNo;
     private Date taxInvDate;
     private String taxInvTo;
     private String taxInvName;
     private String taxInvAddr;
     private String arCode;
     private String invoiceType;
     private String createBy;
     private String remark;
     private Date createDate;
     private String department;
     private Date updateDate;
     private Date postDate;
     private Integer outputTaxStatus;
     private List creditNoteDetails = new LinkedList<CreditNoteDetail>();
     private List taxInvoiceDetails = new LinkedList<TaxInvoiceDetail>();

    public TaxInvoice() {
    }

    public TaxInvoice(MFinanceItemstatus MFinanceItemstatus, Passenger passenger, String taxNo, Date taxInvDate, String taxInvTo, String taxInvName, String taxInvAddr, String arCode, String invoiceType, String createBy, Date createDate, String remark, String department, List creditNoteDetails, List taxInvoiceDetails, Date updateDate , Date postDate , Integer outputTaxStatus) {
       this.MFinanceItemstatus = MFinanceItemstatus;
       this.passenger = passenger;
       this.taxNo = taxNo;
       this.taxInvDate = taxInvDate;
       this.taxInvTo = taxInvTo;
       this.taxInvName = taxInvName;
       this.taxInvAddr = taxInvAddr;
       this.arCode = arCode;
       this.invoiceType = invoiceType;
       this.createBy = createBy;
       this.createDate = createDate;
       this.remark = remark;
       this.department = department;
       this.creditNoteDetails = creditNoteDetails;
       this.taxInvoiceDetails = taxInvoiceDetails;
       this.updateDate = updateDate;
       this.postDate = postDate;
       this.outputTaxStatus = outputTaxStatus;
    }
   
    public String getId() {
        return this.id;
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
    public Passenger getPassenger() {
        return this.passenger;
    }
    
    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
    public String getTaxNo() {
        return this.taxNo;
    }
    
    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }
    public Date getTaxInvDate() {
        return this.taxInvDate;
    }
    
    public void setTaxInvDate(Date taxInvDate) {
        this.taxInvDate = taxInvDate;
    }
    public String getTaxInvTo() {
        return this.taxInvTo;
    }
    
    public void setTaxInvTo(String taxInvTo) {
        this.taxInvTo = taxInvTo;
    }
    public String getTaxInvName() {
        return this.taxInvName;
    }
    
    public void setTaxInvName(String taxInvName) {
        this.taxInvName = taxInvName;
    }
    public String getTaxInvAddr() {
        return this.taxInvAddr;
    }
    
    public void setTaxInvAddr(String taxInvAddr) {
        this.taxInvAddr = taxInvAddr;
    }
    public String getArCode() {
        return this.arCode;
    }
    
    public void setArCode(String arCode) {
        this.arCode = arCode;
    }
    public String getInvoiceType() {
        return this.invoiceType;
    }
    
    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }
    public String getCreateBy() {
        return this.createBy;
    }
    
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    public Date getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public List getCreditNoteDetails() {
        return this.creditNoteDetails;
    }
    
    public void setCreditNoteDetails(List creditNoteDetails) {
        this.creditNoteDetails = creditNoteDetails;
    }
    public List getTaxInvoiceDetails() {
        return this.taxInvoiceDetails;
    }
    
    public void setTaxInvoiceDetails(List taxInvoiceDetails) {
        this.taxInvoiceDetails = taxInvoiceDetails;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Object getAmountExcludeVat() {
        BigDecimal sumAmount = new BigDecimal("0.0000");
        for (Iterator detailList = this.getTaxInvoiceDetails().iterator(); detailList.hasNext();) {
            TaxInvoiceDetail detail = (TaxInvoiceDetail)detailList.next();
            BigDecimal amount = detail.getAmount();
            BigDecimal vat = new BigDecimal("0.0000");
            if(detail.getIsVat() == 1){
                if(detail.getVat() != null){
                    vat = detail.getVat();
                }
                if(amount != null){
                    BigDecimal hundred = new BigDecimal("100.0000");
                    sumAmount = sumAmount.add(amount.multiply(hundred).divide(vat.add(hundred), 8,RoundingMode.HALF_UP));
                }
                
            }else if(detail.getIsVat() == 0){
                if(amount != null){
                    vat = new BigDecimal(BigInteger.ZERO);
                    BigDecimal hundred = new BigDecimal("100.0000");
                    sumAmount = sumAmount.add(amount.multiply(hundred).divide(vat.add(hundred), 8,RoundingMode.HALF_UP));
                }
            }    
//            if(detail.getIsVat() == 1){
//                sumAmount = sumAmount.add(detail.getGross() != null ? detail.getGross() : new BigDecimal(BigInteger.ZERO));
//            
//            }else if(detail.getIsVat() == 0){
//                sumAmount = sumAmount.add(detail.getAmount() != null ? detail.getAmount() : new BigDecimal(BigInteger.ZERO));
//            }
        }
        return sumAmount;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Integer getOutputTaxStatus() {
        return outputTaxStatus;
    }

    public void setOutputTaxStatus(Integer outputTaxStatus) {
        this.outputTaxStatus = outputTaxStatus;
    }

}


