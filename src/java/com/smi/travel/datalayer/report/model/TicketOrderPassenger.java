package com.smi.travel.datalayer.report.model;

public class TicketOrderPassenger {
    private String nameAndTicket = null;
    
    public TicketOrderPassenger(){
        this.nameAndTicket = null;
    }
    
    public String getNameAndTicket() {
        return nameAndTicket;
    }

    public void setNameAndTicket(String nameAndTicket) {
        this.nameAndTicket = nameAndTicket;
    }
}
