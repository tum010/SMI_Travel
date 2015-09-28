/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao;

import com.smi.travel.datalayer.report.model.TicketFareReport;
import java.util.List;
/**
 *
 * @author chonnasith
 */
public interface TicketFareReportDao {
    public List getTicketFareReport(String ticketType,String ticketBuy,String airline,String airlineCode,String dateFrom,String dateTo,String department,String staff,String termPay,String printby);
    public List getTicketFareSumAgentStaff(String ticketType,String ticketBuy,String airline,String airlineCode,String department,String staff,String termPay,String printby,String issuedateFrom,String issuedateTo,String invdateFrom,String invdateTo,String groupBy);
    public List getTicketFareSumAirline(String typeRouting,String routingDetail,String dateFrom,String dateTo,String invdateForm,String invdateTo,String airlineCode,String passenger,String agentId,String department,String saleBy,String termPay,String printby,String groupBy);
}
