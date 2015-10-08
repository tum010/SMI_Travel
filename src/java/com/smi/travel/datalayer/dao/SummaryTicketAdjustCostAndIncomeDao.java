/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.view.entity.ListSummaryTicketAdjustCostAndIncome;
import com.smi.travel.datalayer.view.entity.ListTicketCommissionReceive;
import com.smi.travel.datalayer.view.entity.SummaryTicketAdjustCostAndIncome;
import java.util.List;

/**
 *
 * @author Kanokporn
 */
public interface SummaryTicketAdjustCostAndIncomeDao {
    public List<ListSummaryTicketAdjustCostAndIncome> getSummaryTicketAdjustCostAndIncome(String reportType,String invoiceFromDate,String invoiceToDate,String issueFrom,String issueTo,String paymentType,String departmentt,String salebyUser,String termPayt,String printby);
    public List<ListSummaryTicketAdjustCostAndIncome> getSummaryTicketCostAndIncome(String reportType,String invoiceFromDate,String invoiceToDate,String issueFrom,String issueTo,String paymentType,String departmentt,String salebyUser,String termPayt,String printby);
    public List<ListTicketCommissionReceive> getTicketCommissionReceive(String reportType,String invoiceFromDate,String invoiceToDate,String issueFrom,String issueTo,String paymentType,String departmentt,String salebyUser,String termPayt,String printby);
}
