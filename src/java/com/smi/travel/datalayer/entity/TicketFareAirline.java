package com.smi.travel.datalayer.entity;
// Generated Jul 9, 2015 9:38:50 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * TicketFareAirline generated by hbm2java
 */
public class TicketFareAirline {

     private String id;
     private MAirlineAgent MAirlineAgent;
     private String ticketNo;
     private String ticketType;
     private String ticketBuy;
     private String ticketRouting;
     private String passenger;
     private Date issueDate;
     private Date ticketDate;
     private BigDecimal ticketFare;
     private BigDecimal ticketTax;
     private BigDecimal ticketIns;
     private BigDecimal ticketCommission;
     private BigDecimal agentCommission;
     private BigDecimal salePrice;
     private Integer agentId;
     private String remark;
     private BigDecimal overCommission;
     private BigDecimal litterCommission;
     private BigDecimal decPay;
     private BigDecimal addPay;
     private BigDecimal agentComPay;
     private BigDecimal agentComReceive;
     private BigDecimal diffVat;
     private Date overDate;
     private Date litterDate;
     private Date decPayDate;
     private Date addPayDate;
     private Date agentPayDate;
     private Date agentReceiveDate;
     private String department;
     private Master Master;
     private String pvCode;
     private String owner;
     private String routingDetail;
     private BigDecimal airlineCharge;
     private BigDecimal invAmount;
     private Date dueDate;
     private MPaymentDoctype MPaymentDoctype;
     private Date ticketCommissionDate;
     private Date agentCommissionDate;
     private BigDecimal vat;
     private Integer isWaitPay;
     private BigDecimal wht;
     private Integer isTempTicket;
     private String otherAirAgent;
     private List paymentAirticketFares = new LinkedList<PaymentAirticketFare>();
     private List refundAirticketDetails = new LinkedList<RefundAirticketDetail>();
     private List ticketFareInvoices = new LinkedList<TicketFareInvoice>();
     
     
    public TicketFareAirline() {
    }

    public TicketFareAirline(MAirlineAgent MAirlineAgent, String ticketNo, String ticketType, String ticketBuy, String ticketRouting, String passenger, Date issueDate, Date ticketDate, BigDecimal ticketFare, BigDecimal ticketTax, BigDecimal ticketIns, BigDecimal ticketCommission, BigDecimal agentCommission, BigDecimal salePrice, Integer agentId, String remark, BigDecimal overCommission, BigDecimal litterCommission, BigDecimal decPay, BigDecimal addPay, BigDecimal agentComPay, BigDecimal agentComReceive, Date overDate, Date litterDate, Date decPayDate, Date addPayDate, Date agentPayDate, Date agentReceiveDate, List paymentAirticketFares, List refundAirticketDetails,BigDecimal diffVat,String department,Master master,String pvCode,MPaymentDoctype MPaymentDoctype, List ticketFareInvoices) {
       this.MAirlineAgent = MAirlineAgent;
       this.ticketNo = ticketNo;
       this.ticketType = ticketType;
       this.ticketBuy = ticketBuy;
       this.ticketRouting = ticketRouting;
       this.passenger = passenger;
       this.issueDate = issueDate;
       this.ticketDate = ticketDate;
       this.ticketFare = ticketFare;
       this.ticketTax = ticketTax;
       this.ticketIns = ticketIns;
       this.ticketCommission = ticketCommission;
       this.agentCommission = agentCommission;
       this.salePrice = salePrice;
       this.agentId = agentId;
       this.remark = remark;
       this.overCommission = overCommission;
       this.litterCommission = litterCommission;
       this.decPay = decPay;
       this.addPay = addPay;
       this.agentComPay = agentComPay;
       this.agentComReceive = agentComReceive;
       this.overDate = overDate;
       this.litterDate = litterDate;
       this.decPayDate = decPayDate;
       this.addPayDate = addPayDate;
       this.agentPayDate = agentPayDate;
       this.agentReceiveDate = agentReceiveDate;
       this.diffVat = diffVat;
       this.department = department;
       this.Master = master;
       this.pvCode = pvCode;
       this.MPaymentDoctype = MPaymentDoctype;
       this.paymentAirticketFares = paymentAirticketFares;
       this.refundAirticketDetails = refundAirticketDetails;
       this.ticketFareInvoices = ticketFareInvoices;
    }
   
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    
    
    
    public MAirlineAgent getMAirlineAgent() {
        return this.MAirlineAgent;
    }
    
    public void setMAirlineAgent(MAirlineAgent MAirlineAgent) {
        this.MAirlineAgent = MAirlineAgent;
    }
    public String getTicketNo() {
        return this.ticketNo;
    }
    
    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }
    public String getTicketType() {
        return this.ticketType;
    }
    
    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }
    public String getTicketBuy() {
        return this.ticketBuy;
    }
    
    public void setTicketBuy(String ticketBuy) {
        this.ticketBuy = ticketBuy;
    }
    public String getTicketRouting() {
        return this.ticketRouting;
    }
    
    public void setTicketRouting(String ticketRouting) {
        this.ticketRouting = ticketRouting;
    }
    public String getPassenger() {
        return this.passenger;
    }
    
    public void setPassenger(String passenger) {
        this.passenger = passenger;
    }
    public Date getIssueDate() {
        return this.issueDate;
    }
    
    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }
    public Date getTicketDate() {
        return this.ticketDate;
    }
    
    public void setTicketDate(Date ticketDate) {
        this.ticketDate = ticketDate;
    }
    public BigDecimal getTicketFare() {
        return this.ticketFare;
    }
    
    public void setTicketFare(BigDecimal ticketFare) {
        this.ticketFare = ticketFare;
    }
    public BigDecimal getTicketTax() {
        return this.ticketTax;
    }
    
    public void setTicketTax(BigDecimal ticketTax) {
        this.ticketTax = ticketTax;
    }
    public BigDecimal getTicketIns() {
        return this.ticketIns;
    }
    
    public void setTicketIns(BigDecimal ticketIns) {
        this.ticketIns = ticketIns;
    }
    public BigDecimal getTicketCommission() {
        return this.ticketCommission;
    }
    
    public void setTicketCommission(BigDecimal ticketCommission) {
        this.ticketCommission = ticketCommission;
    }
    public BigDecimal getAgentCommission() {
        return this.agentCommission;
    }
    
    public void setAgentCommission(BigDecimal agentCommission) {
        this.agentCommission = agentCommission;
    }
    public BigDecimal getSalePrice() {
        return this.salePrice;
    }
    
    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }
    public Integer getAgentId() {
        return this.agentId;
    }
    
    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public BigDecimal getOverCommission() {
        return this.overCommission;
    }
    
    public void setOverCommission(BigDecimal overCommission) {
        this.overCommission = overCommission;
    }
    public BigDecimal getLitterCommission() {
        return this.litterCommission;
    }
    
    public void setLitterCommission(BigDecimal litterCommission) {
        this.litterCommission = litterCommission;
    }
    public BigDecimal getDecPay() {
        return this.decPay;
    }
    
    public void setDecPay(BigDecimal decPay) {
        this.decPay = decPay;
    }
    public BigDecimal getAddPay() {
        return this.addPay;
    }
    
    public void setAddPay(BigDecimal addPay) {
        this.addPay = addPay;
    }
    public BigDecimal getAgentComPay() {
        return this.agentComPay;
    }
    
    public void setAgentComPay(BigDecimal agentComPay) {
        this.agentComPay = agentComPay;
    }
    public BigDecimal getAgentComReceive() {
        return this.agentComReceive;
    }
    
    public void setAgentComReceive(BigDecimal agentComReceive) {
        this.agentComReceive = agentComReceive;
    }
    public Date getOverDate() {
        return this.overDate;
    }
    
    public void setOverDate(Date overDate) {
        this.overDate = overDate;
    }
    public Date getLitterDate() {
        return this.litterDate;
    }
    
    public void setLitterDate(Date litterDate) {
        this.litterDate = litterDate;
    }
    public Date getDecPayDate() {
        return this.decPayDate;
    }
    
    public void setDecPayDate(Date decPayDate) {
        this.decPayDate = decPayDate;
    }
    public Date getAddPayDate() {
        return this.addPayDate;
    }
    
    public void setAddPayDate(Date addPayDate) {
        this.addPayDate = addPayDate;
    }
    public Date getAgentPayDate() {
        return this.agentPayDate;
    }
    
    public void setAgentPayDate(Date agentPayDate) {
        this.agentPayDate = agentPayDate;
    }
    public Date getAgentReceiveDate() {
        return this.agentReceiveDate;
    }
    
    public void setAgentReceiveDate(Date agentReceiveDate) {
        this.agentReceiveDate = agentReceiveDate;
    }

    public List getPaymentAirticketFares() {
        return paymentAirticketFares;
    }

    public void setPaymentAirticketFares(List paymentAirticketFares) {
        this.paymentAirticketFares = paymentAirticketFares;
    }

    public List getRefundAirticketDetails() {
        return refundAirticketDetails;
    }

    public void setRefundAirticketDetails(List refundAirticketDetails) {
        this.refundAirticketDetails = refundAirticketDetails;
    }

    public BigDecimal getDiffVat() {
        return diffVat;
    }

    public void setDiffVat(BigDecimal diffVat) {
        this.diffVat = diffVat;
    }

    public Master getMaster() {
        return Master;
    }

    public void setMaster(Master master) {
        this.Master = master;
    }

    public MPaymentDoctype getMPaymentDoctype() {
        return MPaymentDoctype;
    }

    public void setMPaymentDoctype(MPaymentDoctype MPaymentDoctype) {
        this.MPaymentDoctype = MPaymentDoctype;
    }

    public String getPvCode() {
        return pvCode;
    }

    public void setPvCode(String pvCode) {
        this.pvCode = pvCode;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getRoutingDetail() {
        return routingDetail;
    }

    public void setRoutingDetail(String routingDetail) {
        this.routingDetail = routingDetail;
    }

    public BigDecimal getAirlineCharge() {
        return airlineCharge;
    }

    public void setAirlineCharge(BigDecimal airlineCharge) {
        this.airlineCharge = airlineCharge;
    }

    public BigDecimal getInvAmount() {
        return invAmount;
    }

    public void setInvAmount(BigDecimal invAmount) {
        this.invAmount = invAmount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public List getTicketFareInvoices() {
        return ticketFareInvoices;
    }

    public void setTicketFareInvoices(List ticketFareInvoices) {
        this.ticketFareInvoices = ticketFareInvoices;
    }

    public Date getTicketCommissionDate() {
        return ticketCommissionDate;
    }

    public void setTicketCommissionDate(Date ticketCommissionDate) {
        this.ticketCommissionDate = ticketCommissionDate;
    }

    public Date getAgentCommissionDate() {
        return agentCommissionDate;
    }

    public void setAgentCommissionDate(Date agentCommissionDate) {
        this.agentCommissionDate = agentCommissionDate;
    }

    public BigDecimal getVat() {
        return vat;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    public Integer getIsWaitPay() {
        return isWaitPay;
    }

    public void setIsWaitPay(Integer isWaitPay) {
        this.isWaitPay = isWaitPay;
    }

    public BigDecimal getWht() {
        return wht;
    }

    public void setWht(BigDecimal wht) {
        this.wht = wht;
    }

    public Integer getIsTempTicket() {
        return isTempTicket;
    }

    public void setIsTempTicket(Integer isTempTicket) {
        this.isTempTicket = isTempTicket;
    }

    public String getOtherAirAgent() {
        return otherAirAgent;
    }

    public void setOtherAirAgent(String otherAirAgent) {
        this.otherAirAgent = otherAirAgent;
    }

    
    
}


