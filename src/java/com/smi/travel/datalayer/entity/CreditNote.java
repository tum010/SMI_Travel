package com.smi.travel.datalayer.entity;
// Generated Aug 15, 2015 12:01:47 AM by Hibernate Tools 3.6.0


import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * CreditNote generated by hbm2java
 */
public class CreditNote  {


     private String id;
     private String createBy;
     private Date createDate;
     private String cnNo;
     private Date cnDate;
     private String cnName;
     private String cnAddress;
     private String cnRemark;
     private String apCode;
     private String invoiceType;
     private String department;
     private List creditNoteDetails = new LinkedList<CreditNoteDetail>();

    public CreditNote() {
    }

    public CreditNote(String createBy, Date createDate, String cnNo, Date cnDate, String cnName, String cnAddress, String cnRemark, String apCode, String invoiceType, List creditNoteDetails,String department) {
       this.createBy = createBy;
       this.createDate = createDate;
       this.cnNo = cnNo;
       this.cnDate = cnDate;
       this.cnName = cnName;
       this.cnAddress = cnAddress;
       this.cnRemark = cnRemark;
       this.apCode = apCode;
       this.invoiceType = invoiceType;
       this.creditNoteDetails = creditNoteDetails;
       this.department = department;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
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
    public String getCnNo() {
        return this.cnNo;
    }
    
    public void setCnNo(String cnNo) {
        this.cnNo = cnNo;
    }
    public Date getCnDate() {
        return this.cnDate;
    }
    
    public void setCnDate(Date cnDate) {
        this.cnDate = cnDate;
    }
    public String getCnName() {
        return this.cnName;
    }
    
    public void setCnName(String cnName) {
        this.cnName = cnName;
    }
    public String getCnAddress() {
        return this.cnAddress;
    }
    
    public void setCnAddress(String cnAddress) {
        this.cnAddress = cnAddress;
    }
    public String getCnRemark() {
        return this.cnRemark;
    }
    
    public void setCnRemark(String cnRemark) {
        this.cnRemark = cnRemark;
    }
    public String getApCode() {
        return this.apCode;
    }
    
    public void setApCode(String apCode) {
        this.apCode = apCode;
    }
    public String getInvoiceType() {
        return this.invoiceType;
    }
    
    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }
    public List getCreditNoteDetails() {
        return this.creditNoteDetails;
    }
    
    public void setCreditNoteDetails(List creditNoteDetails) {
        this.creditNoteDetails = creditNoteDetails;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }




}

