/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.TicketFareAirlineDao;
import com.smi.travel.datalayer.entity.AirticketFlight;
import com.smi.travel.datalayer.entity.AirticketFlightView;
import com.smi.travel.datalayer.entity.BookingFlight;
import com.smi.travel.datalayer.entity.InvoiceDetail;
import com.smi.travel.datalayer.entity.TicketFareAirline;
import com.smi.travel.datalayer.view.entity.TicketFareView;
import java.util.List;

/**
 *
 * @author Jittima
 */
public class TicketFareAirlineService {
    private TicketFareAirlineDao ticketFareAirlineDao;

    public String InsertTicketFare(TicketFareAirline ticket){
        return ticketFareAirlineDao.InsertTicketFare(ticket);
    }
    
    public String UpdateTicketFare(TicketFareAirline ticket){
        return ticketFareAirlineDao.UpdateTicketFare(ticket);
    }
    
    public int DeleteTicketFare(TicketFareAirline ticket){
        return ticketFareAirlineDao.DeleteTicketFare(ticket);
    }
    
    public TicketFareAirline getTicketFareFromTicketNo(String TicketNo){
        return ticketFareAirlineDao.getTicketFareFromTicketNo(TicketNo);
    }
    
    public List<TicketFareView> getListTicketFare(TicketFareView ticket,int option){
        return ticketFareAirlineDao.getListTicketFare(ticket, option);
    }
    
    public TicketFareAirline getTicketFareFromId(String id){
        return ticketFareAirlineDao.getTicketFareFromId(id);
    }
    
    public String validateSaveTicket(TicketFareAirline ticket){
        return ticketFareAirlineDao.validateSaveTicket(ticket);
    }
    
    public String getTicketFareBookingFromTicketNo(String TicketNo){
        return ticketFareAirlineDao.getTicketFareBookingFromTicketNo(TicketNo);
    }
    
    public String getListTicketFareFromRefno(String Refno){
        return ticketFareAirlineDao.getListTicketFareFromRefno(Refno);
    }
    public int checkDeletePaymentFromTicketNo(String ticketNo){
        return ticketFareAirlineDao.checkDeletePaymentFromTicketNo(ticketNo);
    }
    
    public int checkDeleteRefundFromTicketNo(String ticketNo){
        return ticketFareAirlineDao.checkDeleteRefundFromTicketNo(ticketNo);
    }
    public List<BookingFlight> getListFlightFromTicketNo(String ticketNo){
        return ticketFareAirlineDao.getListFlightFromTicketNo(ticketNo);
    }
    
    public List<AirticketFlightView> getListAirticketFlightFromTicketNo(String ticketNo){
        return ticketFareAirlineDao.getListAirticketFlightFromTicketNo(ticketNo);
    }
    
    public List<InvoiceDetail> getInvoiceDetailFromTicketNo(String ticketNo){
        return ticketFareAirlineDao.getInvoiceDetailFromTicketNo(ticketNo);
    }
    
    
    
    
    //------------------------ Getter Setter TicketFareAirlineDao ------------------------ 
    
    public TicketFareAirlineDao getTicketFareAirlineDao() {
        return ticketFareAirlineDao;
    }

    public void setTicketFareAirlineDao(TicketFareAirlineDao ticketFareAirlineDao) {
        this.ticketFareAirlineDao = ticketFareAirlineDao;
    }
}
