/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.Invoice;

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
    
}
