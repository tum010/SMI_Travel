/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.view.dao.TicketSummaryCommissionDao;

/**
 *
 * @author Kanokporn
 */
public class TicketSummaryCommissionService {
    
    private TicketSummaryCommissionDao ticketSummaryCommissionDao;

    public TicketSummaryCommissionDao getTicketSummaryCommissionDao() {
        return ticketSummaryCommissionDao;
    }

    public void setTicketSummaryCommissionDao(TicketSummaryCommissionDao ticketSummaryCommissionDao) {
        this.ticketSummaryCommissionDao = ticketSummaryCommissionDao;
    }
    
    
    
}
