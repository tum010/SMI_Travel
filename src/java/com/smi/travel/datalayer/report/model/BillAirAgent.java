/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.report.model;

/**
 *
 * @author Kanokporn
 */
public class BillAirAgent {
    private String agentname;
    private String agentid;
    private String invdate;
    private String invno;
    private String refunddate;
    private String issuedate;
    private String paymenttype;
    private String department;
    private String owner;
    private String customer;
    private String ticketno;
    private String rounting;
    private String saleprice;
    private String net;
    private String service;
    private String servicevat;
    private String compay;
    private String compayvat;
    private String amountair;
    private String receive;
    private String agentcom;
    private String agentcomrefund;
    private String paycusrefund;
    
    // header
    private String agentPage;
    private String issuedatePage;
    private String invoicedatePage;
    private String printbyPage;
    private String paymenttypePage;

    public String getPaymenttypePage() {
        return paymenttypePage;
    }

    public void setPaymenttypePage(String paymenttypePage) {
        this.paymenttypePage = paymenttypePage;
    }
    
    

    public String getAgentPage() {
        return agentPage;
    }

    public void setAgentPage(String agentPage) {
        this.agentPage = agentPage;
    }

    public String getIssuedatePage() {
        return issuedatePage;
    }

    public void setIssuedatePage(String issuedatePage) {
        this.issuedatePage = issuedatePage;
    }

    public String getInvoicedatePage() {
        return invoicedatePage;
    }

    public void setInvoicedatePage(String invoicedatePage) {
        this.invoicedatePage = invoicedatePage;
    }

    public String getPrintbyPage() {
        return printbyPage;
    }

    public void setPrintbyPage(String printbyPage) {
        this.printbyPage = printbyPage;
    }
   

    /**
     * @return the agentname
     */
    public String getAgentname() {
        return agentname;
    }

    /**
     * @param agentname the agentname to set
     */
    public void setAgentname(String agentname) {
        this.agentname = agentname;
    }

    /**
     * @return the agentid
     */
    public String getAgentid() {
        return agentid;
    }

    /**
     * @param agentid the agentid to set
     */
    public void setAgentid(String agentid) {
        this.agentid = agentid;
    }

    /**
     * @return the invdate
     */
    public String getInvdate() {
        return invdate;
    }

    /**
     * @param invdate the invdate to set
     */
    public void setInvdate(String invdate) {
        this.invdate = invdate;
    }

    /**
     * @return the invno
     */
    public String getInvno() {
        return invno;
    }

    /**
     * @param invno the invno to set
     */
    public void setInvno(String invno) {
        this.invno = invno;
    }

    /**
     * @return the refunddate
     */
    public String getRefunddate() {
        return refunddate;
    }

    /**
     * @param refunddate the refunddate to set
     */
    public void setRefunddate(String refunddate) {
        this.refunddate = refunddate;
    }

    /**
     * @return the issuedate
     */
    public String getIssuedate() {
        return issuedate;
    }

    /**
     * @param issuedate the issuedate to set
     */
    public void setIssuedate(String issuedate) {
        this.issuedate = issuedate;
    }

    /**
     * @return the paymenttype
     */
    public String getPaymenttype() {
        return paymenttype;
    }

    /**
     * @param paymenttype the paymenttype to set
     */
    public void setPaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
    }

    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * @return the customer
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    /**
     * @return the ticketno
     */
    public String getTicketno() {
        return ticketno;
    }

    /**
     * @param ticketno the ticketno to set
     */
    public void setTicketno(String ticketno) {
        this.ticketno = ticketno;
    }

    /**
     * @return the rounting
     */
    public String getRounting() {
        return rounting;
    }

    /**
     * @param rounting the rounting to set
     */
    public void setRounting(String rounting) {
        this.rounting = rounting;
    }

    /**
     * @return the saleprice
     */
    public String getSaleprice() {
        return saleprice;
    }

    /**
     * @param saleprice the saleprice to set
     */
    public void setSaleprice(String saleprice) {
        this.saleprice = saleprice;
    }

    /**
     * @return the net
     */
    public String getNet() {
        return net;
    }

    /**
     * @param net the net to set
     */
    public void setNet(String net) {
        this.net = net;
    }

    /**
     * @return the service
     */
    public String getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(String service) {
        this.service = service;
    }

    /**
     * @return the servicevat
     */
    public String getServicevat() {
        return servicevat;
    }

    /**
     * @param servicevat the servicevat to set
     */
    public void setServicevat(String servicevat) {
        this.servicevat = servicevat;
    }

    /**
     * @return the compay
     */
    public String getCompay() {
        return compay;
    }

    /**
     * @param compay the compay to set
     */
    public void setCompay(String compay) {
        this.compay = compay;
    }

    /**
     * @return the compayvat
     */
    public String getCompayvat() {
        return compayvat;
    }

    /**
     * @param compayvat the compayvat to set
     */
    public void setCompayvat(String compayvat) {
        this.compayvat = compayvat;
    }

    /**
     * @return the amountair
     */
    public String getAmountair() {
        return amountair;
    }

    /**
     * @param amountair the amountair to set
     */
    public void setAmountair(String amountair) {
        this.amountair = amountair;
    }

    /**
     * @return the receive
     */
    public String getReceive() {
        return receive;
    }

    /**
     * @param receive the receive to set
     */
    public void setReceive(String receive) {
        this.receive = receive;
    }

    /**
     * @return the agentcom
     */
    public String getAgentcom() {
        return agentcom;
    }

    /**
     * @param agentcom the agentcom to set
     */
    public void setAgentcom(String agentcom) {
        this.agentcom = agentcom;
    }

    /**
     * @return the agentcomrefund
     */
    public String getAgentcomrefund() {
        return agentcomrefund;
    }

    /**
     * @param agentcomrefund the agentcomrefund to set
     */
    public void setAgentcomrefund(String agentcomrefund) {
        this.agentcomrefund = agentcomrefund;
    }

    /**
     * @return the paycusrefund
     */
    public String getPaycusrefund() {
        return paycusrefund;
    }

    /**
     * @param paycusrefund the paycusrefund to set
     */
    public void setPaycusrefund(String paycusrefund) {
        this.paycusrefund = paycusrefund;
    }
    
    
}
