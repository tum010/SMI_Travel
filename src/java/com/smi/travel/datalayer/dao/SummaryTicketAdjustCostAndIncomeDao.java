/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.view.entity.SummaryTicketAdjustCostAndIncome;
import java.util.List;

/**
 *
 * @author Kanokporn
 */
public interface SummaryTicketAdjustCostAndIncomeDao {
    public List<SummaryTicketAdjustCostAndIncome> getSummaryTicketAdjustCostAndIncome(String reportType,String invoiceFromDate,String invoiceToDate,String issueFrom,String issueTo,String paymentType,String departmentt,String salebyUser,String termPayt);
}
