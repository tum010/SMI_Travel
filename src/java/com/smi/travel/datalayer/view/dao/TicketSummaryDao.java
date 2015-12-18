/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao;

import com.smi.travel.datalayer.report.model.TicketSummaryList;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface TicketSummaryDao {
    public List getTicketSummary(String ticketfrom,String tickettype,String startdate,String enddate,String billto,String  passenger,String username,String department);
    public TicketSummaryList getTicketSummaryReport(String ticketfrom,String tickettype,String startdate,String enddate,String billto,String  passenger,String username,String department);
    
    public List getRefundTicketSummary(String refundFrom,String refundTo,String ticketFrom,String ticketTo,String refundBy,String printBy); //Refund Ticket Summary Report
}
