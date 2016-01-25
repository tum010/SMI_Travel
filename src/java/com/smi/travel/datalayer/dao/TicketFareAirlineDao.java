/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.AirticketFlightView;
import com.smi.travel.datalayer.entity.BookingFlight;
import com.smi.travel.datalayer.entity.InvoiceDetail;
import com.smi.travel.datalayer.entity.MAirlineAgent;
import com.smi.travel.datalayer.entity.RefundAirticketDetail;
import com.smi.travel.datalayer.entity.TicketFareAirline;
import com.smi.travel.datalayer.view.entity.InvoiceDetailView;
import com.smi.travel.datalayer.view.entity.TicketFareView;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface TicketFareAirlineDao {
    public String InsertTicketFare(TicketFareAirline ticket);
    public String UpdateTicketFare(TicketFareAirline ticket);
    public int DeleteTicketFare(TicketFareAirline ticket);
    public TicketFareAirline getTicketFareFromTicketNo(String ticketNo,String ticketId);
    public String getTicketFareBookingFromTicketNo(String ticketNo);
    public String getListTicketFareFromRefno(String Refno);
    public String getListTicketFareFromInvno(String invNo); //get Ticket By Invoice no.
    public List<BookingFlight> getListFlightFromTicketNo(String ticketNo);
    public List<AirticketFlightView> getListAirticketFlightFromTicketNo(String ticketNo);
    public String validateSaveTicket(TicketFareAirline ticket);
    public List<TicketFareView> getListTicketFare(TicketFareView ticket,int option);
    public TicketFareAirline getTicketFareFromId(String id);
    public int checkDeletePaymentFromTicketNo(String ticketNo);
    public int checkDeleteRefundFromTicketNo(String ticketNo);
    public HashMap<String,Object> getDetailTicketFareAirline(String TicketNo);
    public HashMap<String,Object> getDetailTicketFareAirline(String TicketNo,String AirBookid);
    public List<InvoiceDetailView> getInvoiceDetailFromTicketNo(String ticketNo);
    public String getListTicketFareFromTicketNo(String ticketNo);
    public List<RefundAirticketDetail> getRefundAirticketDetailFromTicketNo(String ticketNo);
    
    public String getMAirlineAgentFromAirlineCode(String airlineCode);
}
