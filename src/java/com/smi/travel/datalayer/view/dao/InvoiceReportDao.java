/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao;

import com.smi.travel.datalayer.report.model.InvoiceReport;
import java.util.List;
/**
 *
 * @author chonnasith
 */
public interface InvoiceReportDao {
    public List getInvoice(String InvoiceId,String BankId,String showStaff,String showLeader,String sign);
    public List getInvoiceMonthly(String BillTo,String ClientName,String Accno,String vattype,String from,String to,String department,String billingAttn,String billingFrom,String billingTel,String billingFax,String billingMail,String billingDate);
}
