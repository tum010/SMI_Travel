/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.dao;

import java.util.List;

/**
 *
 * @author Kanokporn
 */
public interface InvoiceSummaryDao {
//    public List getInvoiceSummary(String ticketfrom,String tickettype,String startdate,String enddate,String billto,String  passenger,String username);
    public List getInvoiceSummary(String from, String to, String department, String type,String agent,String statusInvoice,String printBy);
}
