/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.migration;

import com.smi.travel.datalayer.entity.MCity;
import com.smi.travel.datalayer.entity.MCountry;
import com.smi.travel.datalayer.entity.MInitialname;
import com.smi.travel.datalayer.entity.MProductType;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Jittima
 */
public class MainMigrateModel {
//    Customer
    private String code;
    private MInitialname initialname;
    private String firstName;
    private String lastName;
    private String nationality;
    private Date birthDate;
    private String age;
    private String agemonth;
    private String sex;
    private String formalAddress;
    private String formalTel;
    private String formalFax;
    private String formalEmail;
    private String postalAddress;
    private String postalTel;
    private String postalFax;
    private String postalEmail;
    private String idno;
    private String height;
    private String plaseBirth;
    private Date dateIssue;
    private String passportType;
    private MCountry country;
    private String passportNo;
    private String adAccept;
    private String warning;
    private String status;
    private String customerType;
    private String citizenNo;
    private String postalCode;
    private String mobileNo;
    private String webmemberNo;
    private String wendywebNo;
    private String firstNameJapan;
    private String lastNameJapan;
    private String countryCode;
    
    //Airline
    private String id;
    private String name;
    private String code3Letter;
    private String arcode;
    
    //packageTour
    private String detail;
    private String remark;
    private String supplier;
    private String serial;
    private String guideStaffId;
    private int paxMin;
    private int paxMax;
    
    //Product
    private MProductType productType;
    private String description;
    private Integer isStock;
    private String listItemId;
    private String condition;
    private String include;
    private String instruction;
    private String isUpdate;
    private BigDecimal cost;
    
    //City
    private MCity city;
    private String telNo;
    private String fax;
    private String email;
    private String web;
    private String address;
    private String apCode;
    private String updateHotel;
    private String refId;

    
    //AR
    private String invname;
    private String invno;
    private String invdate;
    private String taxno;
    private String branch;
    private String branchno;
    
    //AP
    private String payid;
    private String payno;
    private String apcode;
    private String paydate;
    private String apname;
    private String department;
    private String vattype;
    
    private String invoiceno;
    private String invoicedate;
    private String invoicename;
    private String invoicedetail;
    private String invoiceamount;
    private String receiveno;
    private String receiveamount;
    private String remainamount;
    private String grandtotal;
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public MInitialname getInitialname() {
        return initialname;
    }

    public void setInitialname(MInitialname initialname) {
        this.initialname = initialname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAgemonth() {
        return agemonth;
    }

    public void setAgemonth(String agemonth) {
        this.agemonth = agemonth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getFormalAddress() {
        return formalAddress;
    }

    public void setFormalAddress(String formalAddress) {
        this.formalAddress = formalAddress;
    }

    public String getFormalTel() {
        return formalTel;
    }

    public void setFormalTel(String formalTel) {
        this.formalTel = formalTel;
    }

    public String getFormalFax() {
        return formalFax;
    }

    public void setFormalFax(String formalFax) {
        this.formalFax = formalFax;
    }

    public String getFormalEmail() {
        return formalEmail;
    }

    public void setFormalEmail(String formalEmail) {
        this.formalEmail = formalEmail;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public String getPostalTel() {
        return postalTel;
    }

    public void setPostalTel(String postalTel) {
        this.postalTel = postalTel;
    }

    public String getPostalFax() {
        return postalFax;
    }

    public void setPostalFax(String postalFax) {
        this.postalFax = postalFax;
    }

    public String getPostalEmail() {
        return postalEmail;
    }

    public void setPostalEmail(String postalEmail) {
        this.postalEmail = postalEmail;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getPlaseBirth() {
        return plaseBirth;
    }

    public void setPlaseBirth(String plaseBirth) {
        this.plaseBirth = plaseBirth;
    }

    public Date getDateIssue() {
        return dateIssue;
    }

    public void setDateIssue(Date dateIssue) {
        this.dateIssue = dateIssue;
    }

    public String getPassportType() {
        return passportType;
    }

    public void setPassportType(String passportType) {
        this.passportType = passportType;
    }

    public MCountry getCountry() {
        return country;
    }

    public void setCountry(MCountry country) {
        this.country = country;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getAdAccept() {
        return adAccept;
    }

    public void setAdAccept(String adAccept) {
        this.adAccept = adAccept;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getCitizenNo() {
        return citizenNo;
    }

    public void setCitizenNo(String citizenNo) {
        this.citizenNo = citizenNo;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getWebmemberNo() {
        return webmemberNo;
    }

    public void setWebmemberNo(String webmemberNo) {
        this.webmemberNo = webmemberNo;
    }

    public String getWendywebNo() {
        return wendywebNo;
    }

    public void setWendywebNo(String wendywebNo) {
        this.wendywebNo = wendywebNo;
    }

    public String getFirstNameJapan() {
        return firstNameJapan;
    }

    public void setFirstNameJapan(String firstNameJapan) {
        this.firstNameJapan = firstNameJapan;
    }

    public String getLastNameJapan() {
        return lastNameJapan;
    }

    public void setLastNameJapan(String lastNameJapan) {
        this.lastNameJapan = lastNameJapan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode3Letter() {
        return code3Letter;
    }

    public void setCode3Letter(String code3Letter) {
        this.code3Letter = code3Letter;
    }

    public String getArcode() {
        return arcode;
    }

    public void setArcode(String arcode) {
        this.arcode = arcode;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getGuideStaffId() {
        return guideStaffId;
    }

    public void setGuideStaffId(String guideStaffId) {
        this.guideStaffId = guideStaffId;
    }

    public int getPaxMin() {
        return paxMin;
    }

    public void setPaxMin(int paxMin) {
        this.paxMin = paxMin;
    }

    public int getPaxMax() {
        return paxMax;
    }

    public void setPaxMax(int paxMax) {
        this.paxMax = paxMax;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIsStock() {
        return isStock;
    }

    public void setIsStock(Integer isStock) {
        this.isStock = isStock;
    }

    public String getListItemId() {
        return listItemId;
    }

    public void setListItemId(String listItemId) {
        this.listItemId = listItemId;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getInclude() {
        return include;
    }

    public void setInclude(String include) {
        this.include = include;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(String isUpdate) {
        this.isUpdate = isUpdate;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public MCity getCity() {
        return city;
    }

    public void setCity(MCity city) {
        this.city = city;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getApCode() {
        return apCode;
    }

    public void setApCode(String apCode) {
        this.apCode = apCode;
    }

    public String getUpdateHotel() {
        return updateHotel;
    }

    public void setUpdateHotel(String updateHotel) {
        this.updateHotel = updateHotel;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public MProductType getProductType() {
        return productType;
    }

    public void setProductType(MProductType productType) {
        this.productType = productType;
    }

    public String getInvname() {
        return invname;
    }

    public void setInvname(String invname) {
        this.invname = invname;
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

    public String getTaxno() {
        return taxno;
    }

    public void setTaxno(String taxno) {
        this.taxno = taxno;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBranchno() {
        return branchno;
    }

    public void setBranchno(String branchno) {
        this.branchno = branchno;
    }

    public String getPayid() {
        return payid;
    }

    public void setPayid(String payid) {
        this.payid = payid;
    }

    public String getPayno() {
        return payno;
    }

    public void setPayno(String payno) {
        this.payno = payno;
    }

    public String getApcode() {
        return apcode;
    }

    public void setApcode(String apcode) {
        this.apcode = apcode;
    }

    public String getPaydate() {
        return paydate;
    }

    public void setPaydate(String paydate) {
        this.paydate = paydate;
    }

    public String getApname() {
        return apname;
    }

    public void setApname(String apname) {
        this.apname = apname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getVattype() {
        return vattype;
    }

    public void setVattype(String vattype) {
        this.vattype = vattype;
    }

    public String getInvoiceno() {
        return invoiceno;
    }

    public void setInvoiceno(String invoiceno) {
        this.invoiceno = invoiceno;
    }

    public String getInvoicedate() {
        return invoicedate;
    }

    public void setInvoicedate(String invoicedate) {
        this.invoicedate = invoicedate;
    }

    public String getInvoicename() {
        return invoicename;
    }

    public void setInvoicename(String invoicename) {
        this.invoicename = invoicename;
    }

    public String getInvoicedetail() {
        return invoicedetail;
    }

    public void setInvoicedetail(String invoicedetail) {
        this.invoicedetail = invoicedetail;
    }

    public String getInvoiceamount() {
        return invoiceamount;
    }

    public void setInvoiceamount(String invoiceamount) {
        this.invoiceamount = invoiceamount;
    }

    public String getReceiveno() {
        return receiveno;
    }

    public void setReceiveno(String receiveno) {
        this.receiveno = receiveno;
    }

    public String getReceiveamount() {
        return receiveamount;
    }

    public void setReceiveamount(String receiveamount) {
        this.receiveamount = receiveamount;
    }

    public String getRemainamount() {
        return remainamount;
    }

    public void setRemainamount(String remainamount) {
        this.remainamount = remainamount;
    }

    public String getGrandtotal() {
        return grandtotal;
    }

    public void setGrandtotal(String grandtotal) {
        this.grandtotal = grandtotal;
    }

    
}
