/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.InvoiceDao;
import com.smi.travel.datalayer.entity.Invoice;
import com.smi.travel.datalayer.entity.InvoiceDetail;
import com.smi.travel.datalayer.view.entity.InvoiceView;
import java.util.List;

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
    
    public List<Invoice> SearchInvoice(String fromData,String toDate ,String department,String type){
       return  invoiceDao.getSearchInvoice(fromData, toDate , department,type);
    }
            
    public Invoice searchInvoiceNo(String invoiceId,String department,String invoiceType){
        return  invoiceDao.searchInvoiceNo(invoiceId, department, invoiceType);
    }
    
    public List<InvoiceView> SearchInvoiceView(List<Invoice> listInvoice){
       return  invoiceDao.setSearchInvoiceView(listInvoice);
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
    
    public Invoice getInvoiceFromInvoiceNumber(String InvoiceNumber){
        return  invoiceDao.getInvoiceFromInvoiceNumber(InvoiceNumber);
    }

    public void setInvoiceDao(InvoiceDao invoiceDao) {
        this.invoiceDao = invoiceDao;
    }

    public InvoiceDao getInvoiceDao() {
        return invoiceDao;
    }
    
    public String searchInvoiceNo(String department ,String invoiceType){
        return  invoiceDao.searchInvoiceNo(department, invoiceType);
    }
    
    public List<InvoiceDetail> getInvoiceDetailFromBillableDescId(String billableDescId){
        return  invoiceDao.getInvoiceDetailFromBillableDescId(billableDescId);
    }

    public String checkOverflowValueOfInvoice(List<InvoiceDetail> invoiceDetail) {
        return invoiceDao.checkOverflowValueOfInvoice(invoiceDetail);
    }
    
    public List<InvoiceDetail> getInvoiceDetailFromInvoiceId(String invoiceId){
        return  invoiceDao.getInvoiceDetailFromInvoiceId(invoiceId);
    }
}
