/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.dao;

import com.smi.travel.datalayer.report.model.BillAirAgentReport;
import java.util.List;

/**
 *
 * @author Kanokporn
 */
public interface BillAirAgentDao {
    public List getBillAirAgentReport();
    public List getBillAirAgentReportSummary(String agentCode,String invoiceFromDate,String InvoiceToDate,String issueFrom,String issueTo,String refundFrom,String refundTo,String department,String salebyUser,String termPay,String printby,String paymentType,String vat,String wht);
    public BillAirAgentReport getBillAirAgentReportPdf(String agentCode,String invoiceFromDate,String InvoiceToDate,String issueFrom,String issueTo,String refundFrom,String refundTo,String department,String salebyUser,String termPay,String printby,String paymentType,String vat,String wht);
}
