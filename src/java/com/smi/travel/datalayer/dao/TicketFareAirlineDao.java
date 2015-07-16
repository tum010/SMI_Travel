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
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface TicketFareAirlineDao {
    public int InsertTicketFare(TicketFareAirline ticket);
    public int UpdateTicketFare(TicketFareAirline ticket);
    public int DeleteTicketFare(TicketFareAirline ticket);
    public TicketFareAirline getTicketFareFromTicketNo(String TicketNo);
    public String getTicketFareBookingFromTicketNo(String TicketNo);
    public String getListTicketFareFromRefno(String Refno);
    public List<BookingFlight> getListFlightFromTicketNo(String TicketNo);
    public int validateTicket(String TicketNo);
    public List<TicketFareView> getListTicketFare(TicketFareView ticket,int option);
    public TicketFareAirline getTicketFareFromId(String id);
}
