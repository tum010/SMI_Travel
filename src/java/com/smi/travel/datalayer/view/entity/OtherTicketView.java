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
    private Date addDate;
    private String ticketCode;
    private String typeName;
    
    public OtherTicketView() {
        
    }
    
    public OtherTicketView(Date addDate, String ticketCode, String typeName) {
        this.addDate = addDate;
        this.ticketCode = ticketCode;
        this.typeName = typeName;
    }
    /**
     * @return the addDate
     */
    public Date getAddDate() {
        return addDate;
    }

    /**
     * @param addDate the addDate to set
     */
    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    /**
     * @return the ticketCode
     */
    public String getTicketCode() {
        return ticketCode;
    }

    /**
     * @param ticketCode the ticketCode to set
     */
    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    /**
     * @return the typeName
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * @param typeName the typeName to set
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
