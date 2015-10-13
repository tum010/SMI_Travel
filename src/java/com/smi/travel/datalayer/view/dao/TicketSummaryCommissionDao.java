/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.dao;

import com.smi.travel.datalayer.view.entity.TicketSummaryCommissionView;
import java.util.List;

/**
 *
 * @author Kanokporn
 */
public interface TicketSummaryCommissionDao {
    public List<TicketSummaryCommissionView> searchTicketSummaryCommission(String invoicefromdatePage,String invoicetodatePage,String issuefromdatePage,String issuetodatePage
            ,String agentcomfromdatePage,String agentcomtodatePage,String ticketcomfromdatePage ,String ticketcomtodatePage,String overfromdatePage ,String overtodatePage
            ,String littlefromdatePage,String littletodatePage ,String agemtcomreceivefromdatePage,String agemtcomreceivetodatePage ,String comrefundfromdatePage 
            ,String comrefundtodatePage ,String addpayfromdatePage ,String addpaytodatePage ,String decreasepayfromdatePage,String decreasepaytodatePage
            ,String typeRoutingPage ,String routingDetailPage , String airlineCodePage ,String agentCodePage ,String agentNamePage ,String ticketnoPagePage 
            ,String departmentPage ,String salebyUserPage , String salebyNamePage , String termPayPage,String printby);
    
}
