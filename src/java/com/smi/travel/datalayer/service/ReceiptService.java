/*
 * To change this license header, choose License Headers in Project Properties. 
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.BillableDao;
import com.smi.travel.datalayer.dao.ReceiptDao;
import com.smi.travel.datalayer.entity.Receipt;
import com.smi.travel.datalayer.entity.ReceiptCredit;
import com.smi.travel.datalayer.entity.ReceiptDetail;
import com.smi.travel.datalayer.view.entity.ReceiptDetailView;
import com.smi.travel.datalayer.view.entity.ReceiptSearchView;
import java.util.List;

/**
 *
 * @author Jittima
 */
public class ReceiptService {
    private ReceiptDao receiptDao;
    private BillableDao billableDao;
    
    public Receipt getReceiptfromReceiptNo(String receiptNo,String department,String recType){
        return getReceiptDao().getReceiptfromReceiptNo(receiptNo,department,recType);
    }
    public String UpdateFinanceStatusReceipt(String receiptId,int status){
        return getReceiptDao().UpdateFinanceStatusReceipt(receiptId,status);
    }
    public String insertReceipt(Receipt receipt){
        return getReceiptDao().insertReceipt(receipt);
    }
    public String updateReceipt(Receipt receipt){
        return getReceiptDao().updateReceipt(receipt);
    }
    public String deleteReceipt(Receipt receipt){
        return getReceiptDao().deleteReceipt(receipt);
    }
    public String DeleteReceiptDetail(String receiptDetailId , String receiptId){
        return getReceiptDao().DeleteReceiptDetail(receiptDetailId,receiptId);
    }
    public String DeleteReceiptBank(String receiptId,int index){
        return getReceiptDao().DeleteReceiptBank(receiptId,index);
    }
    public String DeleteReceiptChq(String receiptCreditId , String receiptId){
        return getReceiptDao().DeleteReceiptChq(receiptCreditId,receiptId);
    }
    
    public List<ReceiptDetail> getReceiptDetailFromReceiptId(String receiptId){
        return getReceiptDao().getReceiptDetailFromReceiptId(receiptId);
    }
        
    public List<ReceiptCredit> getReceiptCreditFromReceiptId(String receiptId){
        return getReceiptDao().getReceiptCreditFromReceiptId(receiptId);
    }
    public List<ReceiptSearchView> getReceiptViewFromFilter(String from ,String to,String Department,String type,String status){
        return getReceiptDao().getReceiptViewFromFilter(from, to, Department, type, status);
    }
    
    public Receipt getReceiptfromReceiptId(String recId){
       return getReceiptDao().getReceiptfromReceiptId(recId);
    }
    
    public String getRefitemidFromBillableDescId(String billabledescId){
        return receiptDao.getRefitemidFromBillableDescId(billabledescId);
    }
//    public List<ReceiptDetail> getReceiptDetailFromInvDetailId(String invDetailId){
//        return receiptDao.getReceiptDetailFromInvDetailId(invDetailId);
//    }
    
    public List<ReceiptDetailView> getReceiptDetailViewFromInvDetailId(String invDetailId){
        return getReceiptDao().getReceiptDetailViewFromInvDetailId(invDetailId);
    }
    
    public List<ReceiptDetailView> getReceiptDetailViewFromBillableId(String billableId){
        return getReceiptDao().getReceiptDetailViewFromBillableId(billableId);
    }
    
    public String getDescriptionInvoiceAirTicket(String refno){
        return billableDao.getDescriptionInvoiceAirTicket(refno,2);
    }
    public String getDescriptionInvoiceOthers(String refno){
        return billableDao.getDescriptionInvoiceOthers(refno,2);
    }
    public String getDescriptionInvoiceLand(String refno){
        return billableDao.getDescriptionInvoiceLand(refno,2);
    }
    public String getDescriptionInvoiceHotel(String refno){
        return billableDao.getDescriptionInvoiceHotel(refno,2);
    }
    public String getDescriptionInvoiceDayTour(String refno){
        return billableDao.getDescriptionInvoiceDayTour(refno,2);
    }
    public String getDescriptionInvoiceAirAdditional(String refno){
        return billableDao.getDescriptionInvoiceAirAdditional(refno,2);
    }   

    public ReceiptDao getReceiptDao() {
        return receiptDao;
    }

    public void setReceiptDao(ReceiptDao receiptDao) {
        this.receiptDao = receiptDao;
    }

    public Receipt getReceiptByWildCardSearch(String receiveId, String receiveNo, String wildCardSearch, String keyCode, String InputDepartment, String InputReceiptType) {
        return getReceiptDao().getReceiptByWildCardSearch(receiveId, receiveNo, wildCardSearch, keyCode, InputDepartment, InputReceiptType);
    }

    public BillableDao getBillableDao() {
        return billableDao;
    }

    public void setBillableDao(BillableDao billableDao) {
        this.billableDao = billableDao;
    }

    public Receipt getSaleVatData(String id) {
        return receiptDao.getSaleVatData(id);
    }
    
}
