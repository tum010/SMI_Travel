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
 * @author Surachai
 */
public class APNirvana {
    private String rowid;
    private String systemdate;
    private String user;
    private String datefrom;
    private String dateto;
    private String departmentheader;
    private String refinvoiceno;
    private String intreference;
    private String vendorid;
    private String vendorname;
    private String divisionid;
    private String projectid;
    private String transcode;
    private Date transdate;
    private Date duedate;
    private String currencyid;
    private BigDecimal homerate;
    private BigDecimal foreignrate;
    private BigDecimal basevatamt;
    private BigDecimal basevathmamt;
    private BigDecimal vatamt;
    private BigDecimal vathmamt;
    private BigDecimal transamt;
    private BigDecimal transhmamt;
    private String vatflag;
    private String vatid;
    private String whtflag;
    private String whtid;
    private BigDecimal basewhtamt;
    private BigDecimal basewhthmamt;
    private BigDecimal whtamt;
    private BigDecimal whthmamt;
    private Integer year;
    private Integer period;
    private String note;
    private String puraccount1;
    private String purdivision1;
    private String purproject1;
    private BigDecimal puramt1;
    private BigDecimal purhmamt1;
    private String puraccount2;
    private String purdivision2;
    private String purproject2;
    private BigDecimal puramt2;
    private BigDecimal purhmamt2;
    private String puraccount3;
    private String purdivision3;       
    private String purproject3;
    private BigDecimal puramt3;
    private BigDecimal purhmamt3;
    private String puraccount4;
    private String purdivision4;       
    private String purproject4;
    private BigDecimal puramt4;
    private BigDecimal purhmamt4;
    private String puraccount5;
    private String purdivision5;       
    private String purproject5;
    private BigDecimal puramt5;
    private BigDecimal purhmamt5;
    private String puraccount6;
    private String purdivision6;       
    private String purproject6;
    private BigDecimal puramt6;
    private BigDecimal purhmamt6;
    private String puraccount7;
    private String purdivision7;       
    private String purproject7;
    private BigDecimal puramt7;
    private BigDecimal purhmamt7;
    private String puraccount8;
    private String purdivision8;       
    private String purproject8;
    private BigDecimal puramt8;
    private BigDecimal purhmamt8;
    private String puraccount9;
    private String purdivision9;       
    private String purproject9;
    private BigDecimal puramt9;
    private BigDecimal purhmamt9;
    private String puraccount10;
    private String purdivision10;       
    private String purproject10;
    private BigDecimal puramt10;
    private BigDecimal purhmamt10;
    private String service;
    private String apaccount;
    private String prefix;
    private Integer voucherno;
    private String taxid;
    private Integer vendor_branch;
    private Integer company_branch;
    private String itf_status;
    private String payment_id;
    private String paymenttype;
    private String payment_detail_id;

    public String getRefinvoiceno() {
        return refinvoiceno;
    }

    public void setRefinvoiceno(String refinvoiceno) {
        this.refinvoiceno = refinvoiceno;
    }

    public String getIntreference() {
        return intreference;
    }

    public void setIntreference(String intreference) {
        this.intreference = intreference;
    }

    public String getVendorid() {
        return vendorid;
    }

    public void setVendorid(String vendorid) {
        this.vendorid = vendorid;
    }

    public String getVendorname() {
        return vendorname;
    }

    public void setVendorname(String vendorname) {
        this.vendorname = vendorname;
    }

    public String getDivisionid() {
        return divisionid;
    }

    public void setDivisionid(String divisionid) {
        this.divisionid = divisionid;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getTranscode() {
        return transcode;
    }

    public void setTranscode(String transcode) {
        this.transcode = transcode;
    }

    public Date getTransdate() {
        return transdate;
    }

    public void setTransdate(Date transdate) {
        this.transdate = transdate;
    }

    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    /**
     * @return the currencyid
     */
    public String getCurrencyid() {
        return currencyid;
    }

    /**
     * @param currencyid the currencyid to set
     */
    public void setCurrencyid(String currencyid) {
        this.currencyid = currencyid;
    }

    /**
     * @return the homerate
     */
    public BigDecimal getHomerate() {
        return homerate;
    }

    /**
     * @param homerate the homerate to set
     */
    public void setHomerate(BigDecimal homerate) {
        this.homerate = homerate;
    }

    /**
     * @return the foreignrate
     */
    public BigDecimal getForeignrate() {
        return foreignrate;
    }

    /**
     * @param foreignrate the foreignrate to set
     */
    public void setForeignrate(BigDecimal foreignrate) {
        this.foreignrate = foreignrate;
    }

    /**
     * @return the basevatamt
     */
    public BigDecimal getBasevatamt() {
        return basevatamt;
    }

    /**
     * @param basevatamt the basevatamt to set
     */
    public void setBasevatamt(BigDecimal basevatamt) {
        this.basevatamt = basevatamt;
    }

    /**
     * @return the basevathmamt
     */
    public BigDecimal getBasevathmamt() {
        return basevathmamt;
    }

    /**
     * @param basevathmamt the basevathmamt to set
     */
    public void setBasevathmamt(BigDecimal basevathmamt) {
        this.basevathmamt = basevathmamt;
    }

    /**
     * @return the vatamt
     */
    public BigDecimal getVatamt() {
        return vatamt;
    }

    /**
     * @param vatamt the vatamt to set
     */
    public void setVatamt(BigDecimal vatamt) {
        this.vatamt = vatamt;
    }

    /**
     * @return the vathmamt
     */
    public BigDecimal getVathmamt() {
        return vathmamt;
    }

    /**
     * @param vathmamt the vathmamt to set
     */
    public void setVathmamt(BigDecimal vathmamt) {
        this.vathmamt = vathmamt;
    }

    /**
     * @return the transamt
     */
    public BigDecimal getTransamt() {
        return transamt;
    }

    /**
     * @param transamt the transamt to set
     */
    public void setTransamt(BigDecimal transamt) {
        this.transamt = transamt;
    }

    /**
     * @return the transhmamt
     */
    public BigDecimal getTranshmamt() {
        return transhmamt;
    }

    /**
     * @param transhmamt the transhmamt to set
     */
    public void setTranshmamt(BigDecimal transhmamt) {
        this.transhmamt = transhmamt;
    }

    /**
     * @return the vatflag
     */
    public String getVatflag() {
        return vatflag;
    }

    /**
     * @param vatflag the vatflag to set
     */
    public void setVatflag(String vatflag) {
        this.vatflag = vatflag;
    }

    /**
     * @return the vatid
     */
    public String getVatid() {
        return vatid;
    }

    /**
     * @param vatid the vatid to set
     */
    public void setVatid(String vatid) {
        this.vatid = vatid;
    }

    /**
     * @return the whtflag
     */
    public String getWhtflag() {
        return whtflag;
    }

    /**
     * @param whtflag the whtflag to set
     */
    public void setWhtflag(String whtflag) {
        this.whtflag = whtflag;
    }

    /**
     * @return the whtid
     */
    public String getWhtid() {
        return whtid;
    }

    /**
     * @param whtid the whtid to set
     */
    public void setWhtid(String whtid) {
        this.whtid = whtid;
    }

    /**
     * @return the basewhtamt
     */
    public BigDecimal getBasewhtamt() {
        return basewhtamt;
    }

    /**
     * @param basewhtamt the basewhtamt to set
     */
    public void setBasewhtamt(BigDecimal basewhtamt) {
        this.basewhtamt = basewhtamt;
    }

    /**
     * @return the basewhthmamt
     */
    public BigDecimal getBasewhthmamt() {
        return basewhthmamt;
    }

    /**
     * @param basewhthmamt the basewhthmamt to set
     */
    public void setBasewhthmamt(BigDecimal basewhthmamt) {
        this.basewhthmamt = basewhthmamt;
    }

    /**
     * @return the whtamt
     */
    public BigDecimal getWhtamt() {
        return whtamt;
    }

    /**
     * @param whtamt the whtamt to set
     */
    public void setWhtamt(BigDecimal whtamt) {
        this.whtamt = whtamt;
    }

    /**
     * @return the whthmamt
     */
    public BigDecimal getWhthmamt() {
        return whthmamt;
    }

    /**
     * @param whthmamt the whthmamt to set
     */
    public void setWhthmamt(BigDecimal whthmamt) {
        this.whthmamt = whthmamt;
    }

    /**
     * @return the year
     */
    public Integer getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * @return the period
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     * @param period the period to set
     */
    public void setPeriod(Integer period) {
        this.period = period;
    }

    /**
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * @return the puraccount1
     */
    public String getPuraccount1() {
        return puraccount1;
    }

    /**
     * @param puraccount1 the puraccount1 to set
     */
    public void setPuraccount1(String puraccount1) {
        this.puraccount1 = puraccount1;
    }

    /**
     * @return the purdivision1
     */
    public String getPurdivision1() {
        return purdivision1;
    }

    /**
     * @param purdivision1 the purdivision1 to set
     */
    public void setPurdivision1(String purdivision1) {
        this.purdivision1 = purdivision1;
    }

    /**
     * @return the purproject1
     */
    public String getPurproject1() {
        return purproject1;
    }

    /**
     * @param purproject1 the purproject1 to set
     */
    public void setPurproject1(String purproject1) {
        this.purproject1 = purproject1;
    }

    /**
     * @return the puramt1
     */
    public BigDecimal getPuramt1() {
        return puramt1;
    }

    /**
     * @param puramt1 the puramt1 to set
     */
    public void setPuramt1(BigDecimal puramt1) {
        this.puramt1 = puramt1;
    }

    /**
     * @return the purhmamt1
     */
    public BigDecimal getPurhmamt1() {
        return purhmamt1;
    }

    /**
     * @param purhmamt1 the purhmamt1 to set
     */
    public void setPurhmamt1(BigDecimal purhmamt1) {
        this.purhmamt1 = purhmamt1;
    }

    /**
     * @return the puraccount2
     */
    public String getPuraccount2() {
        return puraccount2;
    }

    /**
     * @param puraccount2 the puraccount2 to set
     */
    public void setPuraccount2(String puraccount2) {
        this.puraccount2 = puraccount2;
    }

    /**
     * @return the purdivision2
     */
    public String getPurdivision2() {
        return purdivision2;
    }

    /**
     * @param purdivision2 the purdivision2 to set
     */
    public void setPurdivision2(String purdivision2) {
        this.purdivision2 = purdivision2;
    }

    /**
     * @return the purproject2
     */
    public String getPurproject2() {
        return purproject2;
    }

    /**
     * @param purproject2 the purproject2 to set
     */
    public void setPurproject2(String purproject2) {
        this.purproject2 = purproject2;
    }

    /**
     * @return the puramt2
     */
    public BigDecimal getPuramt2() {
        return puramt2;
    }

    /**
     * @param puramt2 the puramt2 to set
     */
    public void setPuramt2(BigDecimal puramt2) {
        this.puramt2 = puramt2;
    }

    /**
     * @return the purhmamt2
     */
    public BigDecimal getPurhmamt2() {
        return purhmamt2;
    }

    /**
     * @param purhmamt2 the purhmamt2 to set
     */
    public void setPurhmamt2(BigDecimal purhmamt2) {
        this.purhmamt2 = purhmamt2;
    }

    /**
     * @return the puraccount3
     */
    public String getPuraccount3() {
        return puraccount3;
    }

    /**
     * @param puraccount3 the puraccount3 to set
     */
    public void setPuraccount3(String puraccount3) {
        this.puraccount3 = puraccount3;
    }

    /**
     * @return the purdivision3
     */
    public String getPurdivision3() {
        return purdivision3;
    }

    /**
     * @param purdivision3 the purdivision3 to set
     */
    public void setPurdivision3(String purdivision3) {
        this.purdivision3 = purdivision3;
    }

    /**
     * @return the purproject3
     */
    public String getPurproject3() {
        return purproject3;
    }

    /**
     * @param purproject3 the purproject3 to set
     */
    public void setPurproject3(String purproject3) {
        this.purproject3 = purproject3;
    }

    /**
     * @return the puramt3
     */
    public BigDecimal getPuramt3() {
        return puramt3;
    }

    /**
     * @param puramt3 the puramt3 to set
     */
    public void setPuramt3(BigDecimal puramt3) {
        this.puramt3 = puramt3;
    }

    /**
     * @return the purhmamt3
     */
    public BigDecimal getPurhmamt3() {
        return purhmamt3;
    }

    /**
     * @param purhmamt3 the purhmamt3 to set
     */
    public void setPurhmamt3(BigDecimal purhmamt3) {
        this.purhmamt3 = purhmamt3;
    }

    /**
     * @return the puraccount4
     */
    public String getPuraccount4() {
        return puraccount4;
    }

    /**
     * @param puraccount4 the puraccount4 to set
     */
    public void setPuraccount4(String puraccount4) {
        this.puraccount4 = puraccount4;
    }

    /**
     * @return the purdivision4
     */
    public String getPurdivision4() {
        return purdivision4;
    }

    /**
     * @param purdivision4 the purdivision4 to set
     */
    public void setPurdivision4(String purdivision4) {
        this.purdivision4 = purdivision4;
    }

    /**
     * @return the purproject4
     */
    public String getPurproject4() {
        return purproject4;
    }

    /**
     * @param purproject4 the purproject4 to set
     */
    public void setPurproject4(String purproject4) {
        this.purproject4 = purproject4;
    }

    /**
     * @return the puramt4
     */
    public BigDecimal getPuramt4() {
        return puramt4;
    }

    /**
     * @param puramt4 the puramt4 to set
     */
    public void setPuramt4(BigDecimal puramt4) {
        this.puramt4 = puramt4;
    }

    /**
     * @return the purhmamt4
     */
    public BigDecimal getPurhmamt4() {
        return purhmamt4;
    }

    /**
     * @param purhmamt4 the purhmamt4 to set
     */
    public void setPurhmamt4(BigDecimal purhmamt4) {
        this.purhmamt4 = purhmamt4;
    }

    /**
     * @return the puraccount5
     */
    public String getPuraccount5() {
        return puraccount5;
    }

    /**
     * @param puraccount5 the puraccount5 to set
     */
    public void setPuraccount5(String puraccount5) {
        this.puraccount5 = puraccount5;
    }

    /**
     * @return the purdivision5
     */
    public String getPurdivision5() {
        return purdivision5;
    }

    /**
     * @param purdivision5 the purdivision5 to set
     */
    public void setPurdivision5(String purdivision5) {
        this.purdivision5 = purdivision5;
    }

    /**
     * @return the purproject5
     */
    public String getPurproject5() {
        return purproject5;
    }

    /**
     * @param purproject5 the purproject5 to set
     */
    public void setPurproject5(String purproject5) {
        this.purproject5 = purproject5;
    }

    /**
     * @return the puramt5
     */
    public BigDecimal getPuramt5() {
        return puramt5;
    }

    /**
     * @param puramt5 the puramt5 to set
     */
    public void setPuramt5(BigDecimal puramt5) {
        this.puramt5 = puramt5;
    }

    /**
     * @return the purhmamt5
     */
    public BigDecimal getPurhmamt5() {
        return purhmamt5;
    }

    /**
     * @param purhmamt5 the purhmamt5 to set
     */
    public void setPurhmamt5(BigDecimal purhmamt5) {
        this.purhmamt5 = purhmamt5;
    }

    /**
     * @return the puraccount6
     */
    public String getPuraccount6() {
        return puraccount6;
    }

    /**
     * @param puraccount6 the puraccount6 to set
     */
    public void setPuraccount6(String puraccount6) {
        this.puraccount6 = puraccount6;
    }

    /**
     * @return the purdivision6
     */
    public String getPurdivision6() {
        return purdivision6;
    }

    /**
     * @param purdivision6 the purdivision6 to set
     */
    public void setPurdivision6(String purdivision6) {
        this.purdivision6 = purdivision6;
    }

    /**
     * @return the purproject6
     */
    public String getPurproject6() {
        return purproject6;
    }

    /**
     * @param purproject6 the purproject6 to set
     */
    public void setPurproject6(String purproject6) {
        this.purproject6 = purproject6;
    }

    /**
     * @return the puramt6
     */
    public BigDecimal getPuramt6() {
        return puramt6;
    }

    /**
     * @param puramt6 the puramt6 to set
     */
    public void setPuramt6(BigDecimal puramt6) {
        this.puramt6 = puramt6;
    }

    /**
     * @return the purhmamt6
     */
    public BigDecimal getPurhmamt6() {
        return purhmamt6;
    }

    /**
     * @param purhmamt6 the purhmamt6 to set
     */
    public void setPurhmamt6(BigDecimal purhmamt6) {
        this.purhmamt6 = purhmamt6;
    }

    /**
     * @return the puraccount7
     */
    public String getPuraccount7() {
        return puraccount7;
    }

    /**
     * @param puraccount7 the puraccount7 to set
     */
    public void setPuraccount7(String puraccount7) {
        this.puraccount7 = puraccount7;
    }

    /**
     * @return the purdivision7
     */
    public String getPurdivision7() {
        return purdivision7;
    }

    /**
     * @param purdivision7 the purdivision7 to set
     */
    public void setPurdivision7(String purdivision7) {
        this.purdivision7 = purdivision7;
    }

    /**
     * @return the purproject7
     */
    public String getPurproject7() {
        return purproject7;
    }

    /**
     * @param purproject7 the purproject7 to set
     */
    public void setPurproject7(String purproject7) {
        this.purproject7 = purproject7;
    }

    /**
     * @return the puramt7
     */
    public BigDecimal getPuramt7() {
        return puramt7;
    }

    /**
     * @param puramt7 the puramt7 to set
     */
    public void setPuramt7(BigDecimal puramt7) {
        this.puramt7 = puramt7;
    }

    /**
     * @return the purhmamt7
     */
    public BigDecimal getPurhmamt7() {
        return purhmamt7;
    }

    /**
     * @param purhmamt7 the purhmamt7 to set
     */
    public void setPurhmamt7(BigDecimal purhmamt7) {
        this.purhmamt7 = purhmamt7;
    }

    /**
     * @return the puraccount8
     */
    public String getPuraccount8() {
        return puraccount8;
    }

    /**
     * @param puraccount8 the puraccount8 to set
     */
    public void setPuraccount8(String puraccount8) {
        this.puraccount8 = puraccount8;
    }

    /**
     * @return the purdivision8
     */
    public String getPurdivision8() {
        return purdivision8;
    }

    /**
     * @param purdivision8 the purdivision8 to set
     */
    public void setPurdivision8(String purdivision8) {
        this.purdivision8 = purdivision8;
    }

    /**
     * @return the purproject8
     */
    public String getPurproject8() {
        return purproject8;
    }

    /**
     * @param purproject8 the purproject8 to set
     */
    public void setPurproject8(String purproject8) {
        this.purproject8 = purproject8;
    }

    /**
     * @return the puramt8
     */
    public BigDecimal getPuramt8() {
        return puramt8;
    }

    /**
     * @param puramt8 the puramt8 to set
     */
    public void setPuramt8(BigDecimal puramt8) {
        this.puramt8 = puramt8;
    }

    /**
     * @return the purhmamt8
     */
    public BigDecimal getPurhmamt8() {
        return purhmamt8;
    }

    /**
     * @param purhmamt8 the purhmamt8 to set
     */
    public void setPurhmamt8(BigDecimal purhmamt8) {
        this.purhmamt8 = purhmamt8;
    }

    /**
     * @return the puraccount9
     */
    public String getPuraccount9() {
        return puraccount9;
    }

    /**
     * @param puraccount9 the puraccount9 to set
     */
    public void setPuraccount9(String puraccount9) {
        this.puraccount9 = puraccount9;
    }

    /**
     * @return the purdivision9
     */
    public String getPurdivision9() {
        return purdivision9;
    }

    /**
     * @param purdivision9 the purdivision9 to set
     */
    public void setPurdivision9(String purdivision9) {
        this.purdivision9 = purdivision9;
    }

    /**
     * @return the purproject9
     */
    public String getPurproject9() {
        return purproject9;
    }

    /**
     * @param purproject9 the purproject9 to set
     */
    public void setPurproject9(String purproject9) {
        this.purproject9 = purproject9;
    }

    /**
     * @return the puramt9
     */
    public BigDecimal getPuramt9() {
        return puramt9;
    }

    /**
     * @param puramt9 the puramt9 to set
     */
    public void setPuramt9(BigDecimal puramt9) {
        this.puramt9 = puramt9;
    }

    /**
     * @return the purhmamt9
     */
    public BigDecimal getPurhmamt9() {
        return purhmamt9;
    }

    /**
     * @param purhmamt9 the purhmamt9 to set
     */
    public void setPurhmamt9(BigDecimal purhmamt9) {
        this.purhmamt9 = purhmamt9;
    }

    /**
     * @return the puraccount10
     */
    public String getPuraccount10() {
        return puraccount10;
    }

    /**
     * @param puraccount10 the puraccount10 to set
     */
    public void setPuraccount10(String puraccount10) {
        this.puraccount10 = puraccount10;
    }

    /**
     * @return the purdivision10
     */
    public String getPurdivision10() {
        return purdivision10;
    }

    /**
     * @param purdivision10 the purdivision10 to set
     */
    public void setPurdivision10(String purdivision10) {
        this.purdivision10 = purdivision10;
    }

    /**
     * @return the purproject10
     */
    public String getPurproject10() {
        return purproject10;
    }

    /**
     * @param purproject10 the purproject10 to set
     */
    public void setPurproject10(String purproject10) {
        this.purproject10 = purproject10;
    }

    /**
     * @return the puramt10
     */
    public BigDecimal getPuramt10() {
        return puramt10;
    }

    /**
     * @param puramt10 the puramt10 to set
     */
    public void setPuramt10(BigDecimal puramt10) {
        this.puramt10 = puramt10;
    }

    /**
     * @return the purhmamt10
     */
    public BigDecimal getPurhmamt10() {
        return purhmamt10;
    }

    /**
     * @param purhmamt10 the purhmamt10 to set
     */
    public void setPurhmamt10(BigDecimal purhmamt10) {
        this.purhmamt10 = purhmamt10;
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
     * @return the apaccount
     */
    public String getApaccount() {
        return apaccount;
    }

    /**
     * @param apaccount the apaccount to set
     */
    public void setApaccount(String apaccount) {
        this.apaccount = apaccount;
    }

    /**
     * @return the prefix
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * @param prefix the prefix to set
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * @return the voucherno
     */
    public Integer getVoucherno() {
        return voucherno;
    }

    /**
     * @param voucherno the voucherno to set
     */
    public void setVoucherno(Integer voucherno) {
        this.voucherno = voucherno;
    }

    /**
     * @return the taxid
     */
    public String getTaxid() {
        return taxid;
    }

    /**
     * @param taxid the taxid to set
     */
    public void setTaxid(String taxid) {
        this.taxid = taxid;
    }

    /**
     * @return the vendor_branch
     */
    public Integer getVendor_branch() {
        return vendor_branch;
    }

    /**
     * @param vendor_branch the vendor_branch to set
     */
    public void setVendor_branch(Integer vendor_branch) {
        this.vendor_branch = vendor_branch;
    }

    /**
     * @return the company_branch
     */
    public Integer getCompany_branch() {
        return company_branch;
    }

    /**
     * @param company_branch the company_branch to set
     */
    public void setCompany_branch(Integer company_branch) {
        this.company_branch = company_branch;
    }

    /**
     * @return the itf_status
     */
    public String getItf_status() {
        return itf_status;
    }

    /**
     * @param itf_status the itf_status to set
     */
    public void setItf_status(String itf_status) {
        this.itf_status = itf_status;
    }

    /**
     * @return the payment_id
     */
    public String getPayment_id() {
        return payment_id;
    }

    /**
     * @param payment_id the payment_id to set
     */
    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
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
     * @return the payment_detail_id
     */
    public String getPayment_detail_id() {
        return payment_detail_id;
    }

    /**
     * @param payment_detail_id the payment_detail_id to set
     */
    public void setPayment_detail_id(String payment_detail_id) {
        this.payment_detail_id = payment_detail_id;
    }

    /**
     * @return the rowid
     */
    public String getRowid() {
        return rowid;
    }

    /**
     * @param rowid the rowid to set
     */
    public void setRowid(String rowid) {
        this.rowid = rowid;
    }

    /**
     * @return the systemdate
     */
    public String getSystemdate() {
        return systemdate;
    }

    /**
     * @param systemdate the systemdate to set
     */
    public void setSystemdate(String systemdate) {
        this.systemdate = systemdate;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the datefrom
     */
    public String getDatefrom() {
        return datefrom;
    }

    /**
     * @param datefrom the datefrom to set
     */
    public void setDatefrom(String datefrom) {
        this.datefrom = datefrom;
    }

    /**
     * @return the dateto
     */
    public String getDateto() {
        return dateto;
    }

    /**
     * @param dateto the dateto to set
     */
    public void setDateto(String dateto) {
        this.dateto = dateto;
    }

    /**
     * @return the departmentheader
     */
    public String getDepartmentheader() {
        return departmentheader;
    }

    /**
     * @param departmentheader the departmentheader to set
     */
    public void setDepartmentheader(String departmentheader) {
        this.departmentheader = departmentheader;
    }
}
