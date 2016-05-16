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
     private MAccpay MAccpay;
     private MFinanceItemstatus MFinanceItemstatus;
     private MItemstatus MItemStatus;
     private String recNo;
     private String recFrom;
     private String recName;
     private String recAddress;
     private String arCode;
     private Date recDate;
     private Date receiveDate;
     private Integer payType;
     private String remark;
     private BigDecimal withTax;
     private BigDecimal cashAmount;
     private BigDecimal cashMinusAmount;
     private BigDecimal bankTransfer;
     private String chqBank1;
     private String chqBank2;
     private String chqNo1;
     private String chqNo2;
     private Date chqDate1;
     private Date chqDate2;
     private BigDecimal chqAmount1;
     private BigDecimal chqAmount2;
     private String recType;
     private String department;
     private Date createDate;
     private String createBy;
     private Integer isRef;
     private Date updateDate;
     private Date exportDate;
     private Integer isExport;
     private Date operationDate;
     private String operationUser;
     private String dataNo;
     private List receiptDetails = new LinkedList<ReceiptDetail>();
     private List receiptCredits = new LinkedList<ReceiptCredit>();

    public Receipt() {
    }

    public Receipt(MFinanceItemstatus MFinanceItemstatus, String recNo, String recFrom, String recName, String recAddress, String arCode, Date recDate, Integer payType, String remark, BigDecimal withTax, BigDecimal cashAmount, BigDecimal cashMinusAmount, BigDecimal bankTransfer, String chqBank1, String chqBank2, Date chqDate1, Date chqDate2, BigDecimal chqAmount1, BigDecimal chqAmount2, List receiptDetails, List receiptCredits,String chqNo1,String chqNo2,MItemstatus MItemStatus,String recType,String department,Date createDate,String createBy,MAccpay MAccpay,Date receiveDate, Integer isRef, Date updateDate, Date exportDate, Integer isExport,Date operationDate,String operationUser,String dataNo) {
       this.MFinanceItemstatus = MFinanceItemstatus;
       this.MItemStatus =  MItemStatus;
       this.recNo = recNo;
       this.recFrom = recFrom;
       this.recName = recName;
       this.recAddress = recAddress;
       this.arCode = arCode;
       this.recDate = recDate;
       this.receiveDate = receiveDate;
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
       this.chqNo1 = chqNo1;
       this.chqNo2 = chqNo2;
       this.receiptDetails = receiptDetails;
       this.receiptCredits = receiptCredits;
       this.recType = recType;
       this.department = department;
       this.createDate = createDate;
       this.createBy = createBy;
       this.MAccpay = MAccpay;
       this.isRef = isRef;
       this.updateDate = updateDate;
       this.exportDate = exportDate;
       this.isExport = isExport;
       this.operationDate = operationDate;
       this.operationUser = operationUser;
       this.dataNo = dataNo;
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

    public MItemstatus getMItemStatus() {
        return MItemStatus;
    }

    public void setMItemStatus(MItemstatus MItemStatus) {
        this.MItemStatus = MItemStatus;
    }

    public String getChqNo1() {
        return chqNo1;
    }

    public void setChqNo1(String chqNo1) {
        this.chqNo1 = chqNo1;
    }

    public String getChqNo2() {
        return chqNo2;
    }

    public void setChqNo2(String chqNo2) {
        this.chqNo2 = chqNo2;
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

    public MAccpay getMAccpay() {
        return MAccpay;
    }

    public void setMAccpay(MAccpay MAccpay) {
        this.MAccpay = MAccpay;
    }

    public String getRecType() {
        return recType;
    }

    public void setRecType(String recType) {
        this.recType = recType;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public Integer getIsRef() {
        return isRef;
    }

    public void setIsRef(Integer isRef) {
        this.isRef = isRef;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getExportDate() {
        return exportDate;
    }

    public void setExportDate(Date exportDate) {
        this.exportDate = exportDate;
    }

    public Integer getIsExport() {
        return isExport;
    }

    public void setIsExport(Integer isExport) {
        this.isExport = isExport;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public String getOperationUser() {
        return operationUser;
    }

    public void setOperationUser(String operationUser) {
        this.operationUser = operationUser;
    }

    public String getDataNo() {
        return dataNo;
    }

    public void setDataNo(String dataNo) {
        this.dataNo = dataNo;
    }


}


