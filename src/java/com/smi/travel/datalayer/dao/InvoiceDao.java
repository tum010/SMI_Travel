/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.Invoice;
import com.smi.travel.datalayer.entity.InvoiceDetail;
import com.smi.travel.datalayer.view.entity.InvoiceView;
import java.math.BigDecimal;
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
    public Invoice getInvoiceFromInvoiceNumber(String InvoiceNumber,String department,String invType);
    public String searchInvoiceNum(String department,String invoiceType,String invoiceNo);
    public Invoice searchInvoiceNo(String invoiceId,String department,String invoiceType);
    public Invoice getInvoiceFromId(String invoiceId);
    public List<Invoice> getSearchInvoice(String fromData,String toDate ,String department,String type,String agent,String status,String airticketWendy);
    public List<InvoiceView> setSearchInvoiceView(List<Invoice> listInvoice);
    public List<HashMap<String,Object>> getInvoiceDetailFromInvoiceNumber(String InvoiceNumber);
    public BigDecimal[] checkBillDescInuse(String billdesc,String cost,String amount);
    public List<InvoiceDetail> getInvoiceDetailFromBillableDescId(String billableDescId);
    public Invoice searchInvoiceFromInvoiceNumber(String InvoiceNumber,String department,String invType);
    public Invoice searchInvoiceForTaxInvoice(String InvoiceNumber,String department);
    public String checkOverflowValueOfInvoice(List<InvoiceDetail> invoiceDetail);
    public String checkRecipt(String refNo);
    public String taxInvoice(String refNo);
    public String checkFlagBooking(Invoice invoice);
    public String setBookingStatus(Invoice invoice);
    
    //for save receipt 
    public String insertInvoiceDetail(Invoice invoice);
    public String updateInvoiceDetail(Invoice invoice);
    public List<InvoiceDetail> getInvoiceDetailFromBillDescId(String billableDescId); //for ajax check invoice detail 
    public List<InvoiceDetail> getInvoiceDetailFromBillDescIdAndRecDetailId(String billableDescId,String receiptDetailId); //for ajax check invoice detail 
    
    public Invoice getInvoiceByWildCardSearch(String invoiceId, String invoiceNo, String wildCardSearch, String keyCode, String department, String invoiceType);
    public InvoiceDetail getInvoiceDetailFromId(String invoiceDetailId);

}
