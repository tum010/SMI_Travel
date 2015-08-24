/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.entity;

import java.util.Date;
/**
 *
 * @author chonnasith
 */
public class OtherTicketView {
    private String id;
    private Date addDate;
    private String ticketCode;
    private String typeName;
    private String status;
    
    public OtherTicketView() {
        
    }
    
    public OtherTicketView(String id, Date addDate, String ticketCode, String typeName) {
        this.id = id;
        this.addDate = addDate;
        this.ticketCode = ticketCode;
        this.typeName = typeName;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
