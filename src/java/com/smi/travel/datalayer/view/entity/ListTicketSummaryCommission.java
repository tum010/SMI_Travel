/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.entity;

import java.util.List;

/**
 *
 * @author Kanokporn
 */
public class ListTicketSummaryCommission {
    private List TicketCommissionDetailSummary;
    private List TicketCommissionAirSummary;
    private List TicketCommissionAgentSummary;

    public List getTicketCommissionDetailSummary() {
        return TicketCommissionDetailSummary;
    }

    public void setTicketCommissionDetailSummary(List TicketCommissionDetailSummary) {
        this.TicketCommissionDetailSummary = TicketCommissionDetailSummary;
    }

    public List getTicketCommissionAirSummary() {
        return TicketCommissionAirSummary;
    }

    public void setTicketCommissionAirSummary(List TicketCommissionAirSummary) {
        this.TicketCommissionAirSummary = TicketCommissionAirSummary;
    }

    public List getTicketCommissionAgentSummary() {
        return TicketCommissionAgentSummary;
    }

    public void setTicketCommissionAgentSummary(List TicketCommissionAgentSummary) {
        this.TicketCommissionAgentSummary = TicketCommissionAgentSummary;
    }
    
}
