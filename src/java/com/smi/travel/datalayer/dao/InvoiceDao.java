/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.Invoice;
import com.smi.travel.datalayer.view.entity.InvoiceView;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface InvoiceDao {
    public String insertInvoice(Invoice invoice);
    public String updateInvoice(Invoice invoice);
    public String deleteInvoice(Invoice invoice);
    public String UpdateInvoiceStatus(int StatusId);
    public String DeleteInvoiceDetail(String InvoiceDetailId);
    public Invoice getInvoiceFromInvoiceNumber(String InvoiceNumber);
    public String searchInvoiceNo(String department,String invoiceType);
    public InvoiceView SearchInvoiceView(String DateFrom,String DateTo,String Department,String Type);
    public List<HashMap<String,Object>> getInvoiceDetailFromInvoiceNumber(String InvoiceNumber);
    

}
