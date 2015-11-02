/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.entity;

import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;

/**
 *
 * @author Kanokporn
 */
public class HotelMonthly {
    //Header
    private String frompage;
    private String topage;
    private String departmentpage;
    
    private String fromto;		
    private String systemdate;
    private String printby;					
    private String hotel;			
    private String refno;			
    private String leader;			
    private String checkin;			
    private String checkout;			
    private String adult;			
    private String child;			
    private String infant;			
    private String hotelid; 
    private String createdate;
    private String department;
    private List hotelMonthlyDetail;
    private String subReportDir;
    private JRDataSource hotelMonthlyDetailSubReportDataSource;

    public String getSubReportDir() {
        return subReportDir;
    }

    public void setSubReportDir(String subReportDir) {
        this.subReportDir = subReportDir;
    }

    public JRDataSource getHotelMonthlyDetailSubReportDataSource() {
        return hotelMonthlyDetailSubReportDataSource;
    }

    public void setHotelMonthlyDetailSubReportDataSource(JRDataSource hotelMonthlyDetailSubReportDataSource) {
        this.hotelMonthlyDetailSubReportDataSource = hotelMonthlyDetailSubReportDataSource;
    }
    
    

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFrompage() {
        return frompage;
    }

    public void setFrompage(String frompage) {
        this.frompage = frompage;
    }

    public String getTopage() {
        return topage;
    }

    public void setTopage(String topage) {
        this.topage = topage;
    }

    public String getDepartmentpage() {
        return departmentpage;
    }

    public void setDepartmentpage(String departmentpage) {
        this.departmentpage = departmentpage;
    }

    public String getFromto() {
        return fromto;
    }

    public void setFromto(String fromto) {
        this.fromto = fromto;
    }

    public String getSystemdate() {
        return systemdate;
    }

    public void setSystemdate(String systemdate) {
        this.systemdate = systemdate;
    }

    public String getPrintby() {
        return printby;
    }

    public void setPrintby(String printby) {
        this.printby = printby;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getRefno() {
        return refno;
    }

    public void setRefno(String refno) {
        this.refno = refno;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
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

    public String getHotelid() {
        return hotelid;
    }

    public void setHotelid(String hotelid) {
        this.hotelid = hotelid;
    }

    public List getHotelMonthlyDetail() {
        return hotelMonthlyDetail;
    }

    public void setHotelMonthlyDetail(List hotelMonthlyDetail) {
        this.hotelMonthlyDetail = hotelMonthlyDetail;
    }
    
    
}
