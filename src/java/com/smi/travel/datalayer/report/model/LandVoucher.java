/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.report.model;

/**
 *
 * @author Surachai
 */
public class LandVoucher {
    private String refno;
    private String code;
    private String name;
    private String address;
    private String tel;
    private String fax;
    private String leadername;
    private String adult;
    private String child;
    private String infant;
    private String total;
    private String passenger1;
    private String passenger2;
    private String passenger3;
    private String passenger4;
    private String passenger5;
    private String category;
    private String description;
    private String Qty;
    private String okby;
    private String category1;
    private String description1;
    private String Qty1;
    private String category2;
    private String description2;
    private String Qty2;
    private String checkin;
    private String remark;
    private String passenger6;
    private String systemdate;
    private String user;
    private String hotel_name;
    private String package_code;
    
    public LandVoucher(){
        this.passenger1 = "";
        this.passenger2 = "";
        this.passenger3 = "";
        this.passenger4 = "";
        this.passenger5 = "";
        this.passenger6 = "";
        this.category = "";
        this.category1 = "";
        this.category2 = "";
        this.Qty = "";
        this.Qty1 = "";
        this.Qty2 = "";
        this.description = "";
        this.description1 = "";
        this.description2 = "";
        this.package_code ="";
    }

    public String getPassenger6() {
        return passenger6;
    }

    public void setPassenger6(String passenger6) {
        this.passenger6 = passenger6;
    }

    public String getSystemdate() {
        return systemdate;
    }

    public void setSystemdate(String systemdate) {
        this.systemdate = systemdate;
    }

    public String getOkby() {
        return okby;
    }

    public void setOkby(String okby) {
        this.okby = okby;
    }
    
    

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    

    public String getRefno() {
        return refno;
    }

    public void setRefno(String refno) {
        this.refno = refno;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getLeadername() {
        return leadername;
    }

    public void setLeadername(String leadername) {
        this.leadername = leadername;
    }

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }

    public String getInfant() {
        return infant;
    }

    public void setInfant(String infant) {
        this.infant = infant;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPassenger1() {
        return passenger1;
    }

    public void setPassenger1(String passenger1) {
        this.passenger1 = passenger1;
    }

    public String getPassenger2() {
        return passenger2;
    }

    public void setPassenger2(String passenger2) {
        this.passenger2 = passenger2;
    }

    public String getPassenger3() {
        return passenger3;
    }

    public void setPassenger3(String passenger3) {
        this.passenger3 = passenger3;
    }

    public String getPassenger4() {
        return passenger4;
    }

    public void setPassenger4(String passenger4) {
        this.passenger4 = passenger4;
    }

    public String getPassenger5() {
        return passenger5;
    }

    public void setPassenger5(String passenger5) {
        this.passenger5 = passenger5;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String Qty) {
        this.Qty = Qty;
    }

    public String getCategory1() {
        return category1;
    }

    public void setCategory1(String category1) {
        this.category1 = category1;
    }

    public String getDescription1() {
        return description1;
    }

    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    public String getQty1() {
        return Qty1;
    }

    public void setQty1(String Qty1) {
        this.Qty1 = Qty1;
    }

    public String getCategory2() {
        return category2;
    }

    public void setCategory2(String category2) {
        this.category2 = category2;
    }

    public String getDescription2() {
        return description2;
    }

    public void setDescription2(String description2) {
        this.description2 = description2;
    }

    public String getQty2() {
        return Qty2;
    }

    public void setQty2(String Qty2) {
        this.Qty2 = Qty2;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
    public void setPackage_code(String package_code) {
        this.package_code = package_code;
    }

    public String getPackage_code() {
        return package_code;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    
}
