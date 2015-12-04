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
        if(invoice.getId() != null && !"".equals(invoice.getId())){
            return invoiceDao.updateInvoice(invoice);
        }else{
            return invoiceDao.insertInvoice(invoice);
        }
    }
    
    public List<Invoice> SearchInvoice(String fromData,String toDate ,String department,String type,String agent,String status,String airticketWendy){
       return  invoiceDao.getSearchInvoice(fromData, toDate , department,type,agent,status,airticketWendy);
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
    
    public Invoice getInvoiceFromInvoiceNumber(String InvoiceNumber,String department,String invType){
        return  invoiceDao.getInvoiceFromInvoiceNumber(InvoiceNumber, department, invType);
    }

    public void setInvoiceDao(InvoiceDao invoiceDao) {
        this.invoiceDao = invoiceDao;
    }

    public InvoiceDao getInvoiceDao() {
        return invoiceDao;
    }
    
    public String searchInvoiceNum(String department ,String invoiceType,String invoiceNo){
        return  invoiceDao.searchInvoiceNum(department, invoiceType,invoiceNo);
    }
    
    public List<InvoiceDetail> getInvoiceDetailFromBillableDescId(String billableDescId){
        return  invoiceDao.getInvoiceDetailFromBillableDescId(billableDescId);
    }

    public String checkOverflowValueOfInvoice(List<InvoiceDetail> invoiceDetail) {
        return invoiceDao.checkOverflowValueOfInvoice(invoiceDetail);
    }
    
    public String saveInvoiceDetail(Invoice invoice){
        if(invoice.getId() != null){
            return invoiceDao.updateInvoiceDetail(invoice);
        }else{
            return invoiceDao.insertInvoiceDetail(invoice);
        }
    }
    
    public String checkReceipt(String refno){
        return  invoiceDao.checkRecipt(refno);
    }
    
    public String checkTaxInvoice(String refno){
        return  invoiceDao.taxInvoice(refno);
    }

    public Invoice getInvoiceByWildCardSearch(String invoiceId, String invoiceNo, String wildCardSearch, String keyCode, String department, String invoiceType) {
        return invoiceDao.getInvoiceByWildCardSearch(invoiceId, invoiceNo, wildCardSearch, keyCode, department, invoiceType);
    }
    
    public String checkFlagBooking(Invoice invoice){
        return invoiceDao.checkFlagBooking(invoice);
    }
    
    public String setBookingStatus(Invoice invoice){
        return invoiceDao.setBookingStatus(invoice);
    }
    
    public Invoice getInvoiceFromId(String invoiceId){
        return invoiceDao.getInvoiceFromId(invoiceId);
    }
}
