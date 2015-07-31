/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.InvoiceDao;
import com.smi.travel.datalayer.entity.Invoice;

/**
 *
 * @author Kanokporn
 */
public class InvoiceService {
    private InvoiceDao invoiceDao;
    
    public String saveInvoice(Invoice invoice){
        if(invoice.getId() != null){
            return invoiceDao.updateInvoice(invoice);
        }else{
            return invoiceDao.insertInvoice(invoice);
        }
    }
    
    public String deleteInvoice(Invoice invoice){
        return invoiceDao.deleteInvoice(invoice);
    }
    
    public String UpdateInvoiceStatus(int StatusId){
        return invoiceDao.UpdateInvoiceStatus(StatusId);
    }
    
    public String DeleteInvoiceDetail(String InvoiceDetailId){
        return  invoiceDao.DeleteInvoiceDetail(InvoiceDetailId);
    }
    
    public String getInvoiceNumber(Invoice invoice){
        return invoiceDao.getInvoiceNumber(invoice);
    }
    
    public Invoice getInvoiceFromInvoiceNumber(String InvoiceNumber){
        return  invoiceDao.getInvoiceFromInvoiceNumber(InvoiceNumber);
    }

    public void setInvoiceDao(InvoiceDao invoiceDao) {
        this.invoiceDao = invoiceDao;
    }

    public InvoiceDao getInvoiceDao() {
        return invoiceDao;
    }
    
}
