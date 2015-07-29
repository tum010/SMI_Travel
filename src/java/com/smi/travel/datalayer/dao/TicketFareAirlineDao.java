/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.AirticketPassenger;
import com.smi.travel.datalayer.entity.BookingFlight;
import com.smi.travel.datalayer.entity.TicketFareAirline;
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
    public TicketFareAirline getTicketFareFromTicketNo(String ticketNo);
    public String getTicketFareBookingFromTicketNo(String ticketNo);
    public String getListTicketFareFromRefno(String Refno);
    public List<BookingFlight> getListFlightFromTicketNo(String ticketNo);
    public String validateSaveTicket(TicketFareAirline ticket);
    public List<TicketFareView> getListTicketFare(TicketFareView ticket,int option);
    public TicketFareAirline getTicketFareFromId(String id);
    public int checkDeletePaymentFromTicketNo(String ticketNo);
    public int checkDeleteRefundFromTicketNo(String ticketNo);
    public HashMap<String,Object> getDetailTicketFareAirline(String TicketNo);
}
