/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.Invoice;
import com.smi.travel.datalayer.view.entity.InvoiceView;
import java.util.List;
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
    public String LockAndUnLockInvoice(String InvoiceId , int LockStatus);
    //UPDATE Invoice inv set inv.isLock = :LockStatus where inv.id = :InvoiceId
    public String DeleteInvoiceDetail(String InvoiceDetailId);
    public Invoice getInvoiceFromInvoiceNumber(String InvoiceNumber);
    public String searchInvoiceNo(String department,String invoiceType);
    public Invoice searchInvoiceNo(String invoiceId,String department,String invoiceType);
    public Invoice getInvoiceFromId(String invoiceId);
    public List<Invoice> getSearchInvoice(String fromData,String toDate ,String department,String type);
    public List<InvoiceView> setSearchInvoiceView(List<Invoice> listInvoice);
    public List<HashMap<String,Object>> getInvoiceDetailFromInvoiceNumber(String InvoiceNumber);
    

}
