package com.smi.travel.datalayer.entity;
// Generated Jul 9, 2015 9:38:50 AM by Hibernate Tools 4.3.1


import com.smi.travel.datalayer.entity.MAccpay;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * PaymentAirticket generated by hbm2java
 */
public class PaymentAirticket  {


     private String id;
     private MAccpay MAccpay;
     private String payNo;
     private Date payDate;
     private Date dueDate;
     private String payTo;
     private String invoiceSup;
     private String apCode;
     private String detail;
     private BigDecimal agentAmount;
     private BigDecimal cash;
     private BigDecimal witholdingTax;
     private String chqNo;
     private BigDecimal chqAmount;
     private BigDecimal ticketInsurance;
//     private String creditNote;
     private String debitNote;
     private String createBy;
     private String department;
     private Date createDate;
     private Integer isExport;
     private BigDecimal totalAmount;
     private BigDecimal debitAmount;
     private Date updateDate;
     private Date exportDate;
     private BigDecimal wht;
     private BigDecimal vat;
//     private BigDecimal creditAmount;
    private List paymentAirticketRefunds = new LinkedList<PaymentAirticketRefund>();
    private List paymentAirticketFares = new LinkedList<PaymentAirticketFare>();
    private List paymentAirCredits = new LinkedList<PaymentAirCredit>();
    
    public PaymentAirticket() {
    }

    public PaymentAirticket(MAccpay MAccpay,String payTo ,String payNo, Date payDate, Date dueDate, String invoiceSup, String apCode, String detail, BigDecimal agentAmount, BigDecimal cash, BigDecimal witholdingTax, String chqNo, BigDecimal chqAmount, BigDecimal ticketInsurance, String creditNote, String debitNote, String createBy, Date createDate, List paymentAirticketRefunds, List paymentAirticketFares,Integer isExport,String department,BigDecimal totalAmount,BigDecimal debitAmount,BigDecimal creditAmount,BigDecimal wht , BigDecimal vat) {
       this.MAccpay = MAccpay;
       this.payNo = payNo;
       this.payDate = payDate;
       this.payTo = payTo;
       this.dueDate = dueDate;
       this.invoiceSup = invoiceSup;
       this.apCode = apCode;
       this.detail = detail;
       this.agentAmount = agentAmount;
       this.cash = cash;
       this.witholdingTax = witholdingTax;
       this.chqNo = chqNo;
       this.chqAmount = chqAmount;
       this.ticketInsurance = ticketInsurance;
//       this.creditNote = creditNote;
       this.debitNote = debitNote;
       this.createBy = createBy;
       this.createDate = createDate;
       this.paymentAirticketRefunds = paymentAirticketRefunds;
       this.paymentAirticketFares = paymentAirticketFares;
       this.isExport = isExport;
       this.department = department;
       this.totalAmount = totalAmount; 
       this.debitAmount = debitAmount;
       this.wht = wht;
       this.vat = vat;
//       this.creditAmount = creditAmount;
    }
   
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public MAccpay getMAccpay() {
        return this.MAccpay;
    }
    
    public void setMAccpay(MAccpay MAccpay) {
        this.MAccpay = MAccpay;
    }
    public String getPayNo() {
        return this.payNo;
    }
    
    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }
    public Date getPayDate() {
        return this.payDate;
    }
    
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public String getPayTo() {
        return payTo;
    }

    public void setPayTo(String payTo) {
        this.payTo = payTo;
    }
    
    
    public Date getDueDate() {
        return this.dueDate;
    }
    
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getInvoiceSup() {
        return invoiceSup;
    }

    public void setInvoiceSup(String invoiceSup) {
        this.invoiceSup = invoiceSup;
    }

    public String getApCode() {
        return apCode;
    }

    public void setApCode(String apCode) {
        this.apCode = apCode;
    }
    
    public String getDetail() {
        return this.detail;
    }
    
    public void setDetail(String detail) {
        this.detail = detail;
    }
    public BigDecimal getAgentAmount() {
        return this.agentAmount;
    }
    
    public void setAgentAmount(BigDecimal agentAmount) {
        this.agentAmount = agentAmount;
    }
    public BigDecimal getCash() {
        return this.cash;
    }
    
    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }
    public BigDecimal getWitholdingTax() {
        return this.witholdingTax;
    }
    
    public void setWitholdingTax(BigDecimal witholdingTax) {
        this.witholdingTax = witholdingTax;
    }
    public String getChqNo() {
        return this.chqNo;
    }
    
    public void setChqNo(String chqNo) {
        this.chqNo = chqNo;
    }
    public BigDecimal getChqAmount() {
        return this.chqAmount;
    }
    
    public void setChqAmount(BigDecimal chqAmount) {
        this.chqAmount = chqAmount;
    }
    public BigDecimal getTicketInsurance() {
        return this.ticketInsurance;
    }
    
    public void setTicketInsurance(BigDecimal ticketInsurance) {
        this.ticketInsurance = ticketInsurance;
    }
//    public String getCreditNote() {
//        return this.creditNote;
//    }
//    
//    public void setCreditNote(String creditNote) {
//        this.creditNote = creditNote;
//    }
    public String getDebitNote() {
        return this.debitNote;
    }
    
    public void setDebitNote(String debitNote) {
        this.debitNote = debitNote;
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

    public List getPaymentAirticketRefunds() {
        return paymentAirticketRefunds;
    }

    public void setPaymentAirticketRefunds(List paymentAirticketRefunds) {
        this.paymentAirticketRefunds = paymentAirticketRefunds;
    }

    public List getPaymentAirticketFares() {
        return paymentAirticketFares;
    }

    public void setPaymentAirticketFares(List paymentAirticketFares) {
        this.paymentAirticketFares = paymentAirticketFares;
    }

    public Integer getIsExport() {
        return isExport;
    }

    public void setIsExport(Integer isExport) {
        this.isExport = isExport;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(BigDecimal debitAmount) {
        this.debitAmount = debitAmount;
    }

//    public BigDecimal getCreditAmount() {
//        return creditAmount;
//    }
//
//    public void setCreditAmount(BigDecimal creditAmount) {
//        this.creditAmount = creditAmount;
//    }

    public List getPaymentAirCredits() {
        return paymentAirCredits;
    }

    public void setPaymentAirCredits(List paymentAirCredits) {
        this.paymentAirCredits = paymentAirCredits;
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

    public BigDecimal getWht() {
        return wht;
    }

    public void setWht(BigDecimal wht) {
        this.wht = wht;
    }

    public BigDecimal getVat() {
        return vat;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    
    
}


