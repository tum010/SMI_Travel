/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.TicketFareAirlineDao;
import com.smi.travel.datalayer.entity.TicketFareAirline;
import com.smi.travel.datalayer.view.entity.TicketFareView;
import java.util.List;

/**
 *
 * @author Jittima
 */
public class TicketFareAirlineService {
    private TicketFareAirlineDao ticketFareAirlineDao;

    public int InsertTicketFare(TicketFareAirline ticket){
        return ticketFareAirlineDao.InsertTicketFare(ticket);
    }
    
    public int UpdateTicketFare(TicketFareAirline ticket){
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
    
    public TicketFareAirlineDao getTicketFareAirlineDao() {
        return ticketFareAirlineDao;
    }

    public void setTicketFareAirlineDao(TicketFareAirlineDao ticketFareAirlineDao) {
        this.ticketFareAirlineDao = ticketFareAirlineDao;
    }
}
