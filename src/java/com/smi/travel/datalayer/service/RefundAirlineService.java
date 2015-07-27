/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.RefundAirticketDao;
import com.smi.travel.datalayer.dao.TicketFareAirlineDao;

/**
 *
 * @author Surachai
 */
public class RefundAirlineService {
    public RefundAirticketDao refundAirticketDao;
    public TicketFareAirlineDao ticketFareAirlineDao;

    
    
    public RefundAirticketDao getRefundAirticketDao() {
        return refundAirticketDao;
    }

    public void setRefundAirticketDao(RefundAirticketDao refundAirticketDao) {
        this.refundAirticketDao = refundAirticketDao;
    }

    public TicketFareAirlineDao getTicketFareAirlineDao() {
        return ticketFareAirlineDao;
    }

    public void setTicketFareAirlineDao(TicketFareAirlineDao ticketFareAirlineDao) {
        this.ticketFareAirlineDao = ticketFareAirlineDao;
    }
    
    
}
