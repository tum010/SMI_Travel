/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.ReceiptDao;
import com.smi.travel.datalayer.entity.Receipt;

/**
 *
 * @author Jittima
 */
public class ReceiptService {
    private ReceiptDao receiptDao;

    public Receipt getReceiptfromReceiptNo(String receiptNo){
        return receiptDao.getReceiptfromReceiptNo(receiptNo);
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
    
    
    
    public ReceiptDao getReceiptDao() {
        return receiptDao;
    }

    public void setReceiptDao(ReceiptDao receiptDao) {
        this.receiptDao = receiptDao;
    }
    
}