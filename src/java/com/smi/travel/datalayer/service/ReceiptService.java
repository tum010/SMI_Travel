/*
 * To change this license header, choose License Headers in Project Properties. 
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.ReceiptDao;
import com.smi.travel.datalayer.entity.Receipt;
import com.smi.travel.datalayer.entity.ReceiptCredit;
import com.smi.travel.datalayer.entity.ReceiptDetail;
import com.smi.travel.datalayer.view.entity.ReceiptSearchView;
import java.util.List;

/**
 *
 * @author Jittima
 */
public class ReceiptService {
    private ReceiptDao receiptDao;

    public Receipt getReceiptfromReceiptNo(String receiptNo,String department,String recType){
        return receiptDao.getReceiptfromReceiptNo(receiptNo,department,recType);
    }
    public String UpdateFinanceStatusReceipt(String receiptId,int status){
        return receiptDao.UpdateFinanceStatusReceipt(receiptId,status);
    }
    public String insertReceipt(Receipt receipt){
        return receiptDao.insertReceipt(receipt);
    }
    public String updateReceipt(Receipt receipt){
        return receiptDao.updateReceipt(receipt);
    }
    public String deleteReceipt(Receipt receipt){
        return receiptDao.deleteReceipt(receipt);
    }
    public String DeleteReceiptDetail(String receiptDetailId , String receiptId){
        return receiptDao.DeleteReceiptDetail(receiptDetailId,receiptId);
    }
    public String DeleteReceiptBank(String receiptId,int index){
        return receiptDao.DeleteReceiptBank(receiptId,index);
    }
    public String DeleteReceiptChq(String receiptCreditId , String receiptId){
        return receiptDao.DeleteReceiptChq(receiptCreditId,receiptId);
    }
    
    public List<ReceiptDetail> getReceiptDetailFromReceiptId(String receiptId){
        return receiptDao.getReceiptDetailFromReceiptId(receiptId);
    }
        
    public List<ReceiptCredit> getReceiptCreditFromReceiptId(String receiptId){
        return receiptDao.getReceiptCreditFromReceiptId(receiptId);
    }
    public List<ReceiptSearchView> getReceiptViewFromFilter(String from ,String to,String Department,String type){
        return receiptDao.getReceiptViewFromFilter(from, to, Department, type);
    }
    
    public Receipt getReceiptfromReceiptId(String recId){
       return receiptDao.getReceiptfromReceiptId(recId);
    }
    
    public List<ReceiptDetail> getReceiptDetailFromInvDetailId(String invDetailId){
        return receiptDao.getReceiptDetailFromInvDetailId(invDetailId);
    }
    
    public ReceiptDao getReceiptDao() {
        return receiptDao;
    }

    public void setReceiptDao(ReceiptDao receiptDao) {
        this.receiptDao = receiptDao;
    }
    
}
