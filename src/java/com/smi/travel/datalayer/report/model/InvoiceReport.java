/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.report.model;

/**
 *
 * @author chonnasith
 */
public class InvoiceReport {
    private String invto;
    private String invno;
    private String invdate;
    private String refno;
    private String staff;
    private String payment;
    private String description;
    private String gross;
    private String vat;
    private String amount;
    private String total;
    private String totalvat;
    private String grtotal;
    private String accname;
    private String bank1;
    private String branch1;
    private String accno1;
    private String bank2;
    private String branch2;
    private String accno2;
    private String acctype;
    private String user;
    private String textmoney;
    private String bankid;
    private String showleader;
    private String showstaff;
    private String co;
    private String taxid;
    private String taxbranch;
    private String sign;
    private String signname;
    private String duedate;
    private String address;
    private String remark;
    private String currency;
    private String vatpercent;
    private String printby;
    private String currencytotal;

    public String getCurrencytotal() {
        return currencytotal;
    }

    public void setCurrencytotal(String currencytotal) {
        this.currencytotal = currencytotal;
    }

    public String getPrintby() {
        return printby;
    }

    public void setPrintby(String printby) {
        this.printby = printby;
    }

    public String getVatpercent() {
        return vatpercent;
    }

    public void setVatpercent(String vatpercent) {
        this.vatpercent = vatpercent;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getInvto() {
        return invto;
    }

    public void setInvto(String invto) {
        this.invto = invto;
    }

    public String getInvno() {
        return invno;
    }

    public void setInvno(String invno) {
        this.invno = invno;
    }

    public String getInvdate() {
        return invdate;
    }

    public void setInvdate(String invdate) {
        this.invdate = invdate;
    }

    public String getRefno() {
        return refno;
    }

    public void setRefno(String refno) {
        this.refno = refno;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGross() {
        return gross;
    }

    public void setGross(String gross) {
        this.gross = gross;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getGrtotal() {
        return grtotal;
    }

    public void setGrtotal(String grtotal) {
        this.grtotal = grtotal;
    }

    public String getAccname() {
        return accname;
    }

    public void setAccname(String accname) {
        this.accname = accname;
    }

    public String getAcctype() {
        return acctype;
    }

    public void setAcctype(String acctype) {
        this.acctype = acctype;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    
    public String getTextmoney() {
        return textmoney;
    }

    public void setTextmoney(String textmoney) {
        this.textmoney = textmoney;
    }    

    public String getTotalvat() {
        return totalvat;
    }

    public void setTotalvat(String totalvat) {
        this.totalvat = totalvat;
    }

    public String getBankid() {
        return bankid;
    }

    public void setBankid(String bankid) {
        this.bankid = bankid;
    }

    public String getShowleader() {
        return showleader;
    }

    public void setShowleader(String showleader) {
        this.showleader = showleader;
    }

    public String getShowstaff() {
        return showstaff;
    }

    public void setShowstaff(String showstaff) {
        this.showstaff = showstaff;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public String getTaxbranch() {
        return taxbranch;
    }

    public String getTaxid() {
        return taxid;
    }

    public void setTaxbranch(String taxbranch) {
        this.taxbranch = taxbranch;
    }

    public void setTaxid(String taxid) {
        this.taxid = taxid;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    public String getDuedate() {
        return duedate;
    }

    public String getSignname() {
        return signname;
    }

    public void setSignname(String signname) {
        this.signname = signname;
    }

    public String getBank1() {
        return bank1;
    }

    public void setBank1(String bank1) {
        this.bank1 = bank1;
    }

    public String getBranch1() {
        return branch1;
    }

    public void setBranch1(String branch1) {
        this.branch1 = branch1;
    }

    public String getAccno1() {
        return accno1;
    }

    public void setAccno1(String accno1) {
        this.accno1 = accno1;
    }

    public String getBank2() {
        return bank2;
    }

    public void setBank2(String bank2) {
        this.bank2 = bank2;
    }

    public String getBranch2() {
        return branch2;
    }

    public void setBranch2(String branch2) {
        this.branch2 = branch2;
    }

    public String getAccno2() {
        return accno2;
    }

    public void setAccno2(String accno2) {
        this.accno2 = accno2;
    }
    
}
