/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Jittima
 */
public class CollectionNirvana {
    private String invno;
    private String invto;
    private String arcode;
    private String acccode;
    private String invoiceamount;
    private BigDecimal invamount; //sum
    private String recno;
    private BigDecimal recamount; //sum
    private String cur;
    private BigDecimal diff;
    private String collectionStatus;
    private String department;
    private String type;
    private Date invdate;
    private BigDecimal withtax;
    private String payby;
    private String status;
    private String rowid;
    
    private String systemdate;
    private String user;
    private String from;
    private String to;
    private String no;
    private String headerdepartment;
    private BigDecimal cash;
    private BigDecimal chq;
    private BigDecimal credit;
    private BigDecimal banktransfer;
    private BigDecimal wt;
    private BigDecimal cashminus;
    
    private BigDecimal totalamount;
    private BigDecimal totalamountwait;
    private BigDecimal totalamountvoid;
    private BigDecimal totalamountinvoice;
    private BigDecimal totalamountdiff;
    
    private String trancode;
    private String prefix;
    private String docno;
    private String refvoucher;
    private String year;
    private String period;
    private String arcur;
    private String recur;
    private String homerate;
    private String foreignrate;
    private BigDecimal bankamt;
    private String note;
    private String is_void;
    private String chequeno;
    private Date chequedate;
    private String comid;
    
    private String invname;
    private Date transdate;
    private String bankid;
    
    public String getInvno() {
        return invno;
    }

    public void setInvno(String invno) {
        this.invno = invno;
    }

    public String getInvto() {
        return invto;
    }

    public void setInvto(String invto) {
        this.invto = invto;
    }

    public String getArcode() {
        return arcode;
    }

    public void setArcode(String arcode) {
        this.arcode = arcode;
    }

    public String getAcccode() {
        return acccode;
    }

    public void setAcccode(String acccode) {
        this.acccode = acccode;
    }

    public BigDecimal getInvamount() {
        return invamount;
    }

    public void setInvamount(BigDecimal invamount) {
        this.invamount = invamount;
    }

    public String getRecno() {
        return recno;
    }

    public void setRecno(String recno) {
        this.recno = recno;
    }

    public BigDecimal getRecamount() {
        return recamount;
    }

    public void setRecamount(BigDecimal recamount) {
        this.recamount = recamount;
    }

    public String getCur() {
        return cur;
    }

    public void setCur(String cur) {
        this.cur = cur;
    }

    public BigDecimal getDiff() {
        return diff;
    }

    public void setDiff(BigDecimal diff) {
        this.diff = diff;
    }

    public String getCollectionStatus() {
        return collectionStatus;
    }

    public void setCollectionStatus(String collectionStatus) {
        this.collectionStatus = collectionStatus;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getInvdate() {
        return invdate;
    }

    public void setInvdate(Date invdate) {
        this.invdate = invdate;
    }

    public String getSystemdate() {
        return systemdate;
    }

    public void setSystemdate(String systemdate) {
        this.systemdate = systemdate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getHeaderdepartment() {
        return headerdepartment;
    }

    public void setHeaderdepartment(String headerdepartment) {
        this.headerdepartment = headerdepartment;
    }

    public String getInvoiceamount() {
        return invoiceamount;
    }

    public void setInvoiceamount(String invoiceamount) {
        this.invoiceamount = invoiceamount;
    }

    public BigDecimal getWithtax() {
        return withtax;
    }

    public void setWithtax(BigDecimal withtax) {
        this.withtax = withtax;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public BigDecimal getChq() {
        return chq;
    }

    public void setChq(BigDecimal chq) {
        this.chq = chq;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public BigDecimal getBanktransfer() {
        return banktransfer;
    }

    public void setBanktransfer(BigDecimal banktransfer) {
        this.banktransfer = banktransfer;
    }

    public BigDecimal getWt() {
        return wt;
    }

    public void setWt(BigDecimal wt) {
        this.wt = wt;
    }

    public BigDecimal getCashminus() {
        return cashminus;
    }

    public void setCashminus(BigDecimal cashminus) {
        this.cashminus = cashminus;
    }

    public BigDecimal getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(BigDecimal totalamount) {
        this.totalamount = totalamount;
    }

    public BigDecimal getTotalamountwait() {
        return totalamountwait;
    }

    public void setTotalamountwait(BigDecimal totalamountwait) {
        this.totalamountwait = totalamountwait;
    }

    public BigDecimal getTotalamountvoid() {
        return totalamountvoid;
    }

    public void setTotalamountvoid(BigDecimal totalamountvoid) {
        this.totalamountvoid = totalamountvoid;
    }

    public BigDecimal getTotalamountinvoice() {
        return totalamountinvoice;
    }

    public void setTotalamountinvoice(BigDecimal totalamountinvoice) {
        this.totalamountinvoice = totalamountinvoice;
    }

    public BigDecimal getTotalamountdiff() {
        return totalamountdiff;
    }

    public void setTotalamountdiff(BigDecimal totalamountdiff) {
        this.totalamountdiff = totalamountdiff;
    }

    public String getPayby() {
        return payby;
    }

    public void setPayby(String payby) {
        this.payby = payby;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRowid() {
        return rowid;
    }

    public void setRowid(String rowid) {
        this.rowid = rowid;
    }

    public String getTrancode() {
        return trancode;
    }

    public void setTrancode(String trancode) {
        this.trancode = trancode;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getDocno() {
        return docno;
    }

    public void setDocno(String docno) {
        this.docno = docno;
    }

    public String getRefvoucher() {
        return refvoucher;
    }

    public void setRefvoucher(String refvoucher) {
        this.refvoucher = refvoucher;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getArcur() {
        return arcur;
    }

    public void setArcur(String arcur) {
        this.arcur = arcur;
    }

    public String getRecur() {
        return recur;
    }

    public void setRecur(String recur) {
        this.recur = recur;
    }

    public String getHomerate() {
        return homerate;
    }

    public void setHomerate(String homerate) {
        this.homerate = homerate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getIs_void() {
        return is_void;
    }

    public void setIs_void(String is_void) {
        this.is_void = is_void;
    }

    public String getChequeno() {
        return chequeno;
    }

    public void setChequeno(String chequeno) {
        this.chequeno = chequeno;
    }

    public BigDecimal getBankamt() {
        return bankamt;
    }

    public void setBankamt(BigDecimal bankamt) {
        this.bankamt = bankamt;
    }

    public String getForeignrate() {
        return foreignrate;
    }

    public void setForeignrate(String foreignrate) {
        this.foreignrate = foreignrate;
    }

    public String getComid() {
        return comid;
    }

    public void setComid(String comid) {
        this.comid = comid;
    }

    public String getInvname() {
        return invname;
    }

    public void setInvname(String invname) {
        this.invname = invname;
    }

    public String getBankid() {
        return bankid;
    }

    public void setBankid(String bankid) {
        this.bankid = bankid;
    }

    public Date getChequedate() {
        return chequedate;
    }

    public void setChequedate(Date chequedate) {
        this.chequedate = chequedate;
    }

    public Date getTransdate() {
        return transdate;
    }

    public void setTransdate(Date transdate) {
        this.transdate = transdate;
    }
    
    
}
