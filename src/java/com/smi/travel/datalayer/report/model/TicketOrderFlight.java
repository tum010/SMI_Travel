package com.smi.travel.datalayer.report.model;

public class TicketOrderFlight {

    private String flightNo;
    private String flightClass;
    private String departdate;
    private String from;
    private String to;
    private String depttime;
    private String arrvtime;
    private String status;
    
    public TicketOrderFlight(){

    }
 
    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }

    public String getDepartdate() {
        return departdate;
    }

    public void setDepartdate(String departdate) {
        this.departdate = departdate;
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

    public String getDepttime() {
        return depttime;
    }

    public void setDepttime(String depttime) {
        this.depttime = depttime;
    }

    public String getArrvtime() {
        return arrvtime;
    }

    public void setArrvtime(String arrvtime) {
        this.arrvtime = arrvtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }    
    
}
