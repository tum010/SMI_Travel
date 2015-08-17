/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates sss
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.Receipt;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface ReceiptDao {
    
    public List<HashMap<String,Object>> getAirlineComFromPaymentNo(String invoiceNumber);
    public Receipt getReceiptfromReceiptNo(String receiptNo); // search ReceiveNo --> table Receipt
    public String UpdateFinanceStatusReceipt(String receiptId,int status); //status 1 normal , 2 void
    // UPDATE Receipt rec set rec.MFinanceItemstatus.id = :status  WHERE rec.id = :ReceiptId
    public String insertReceipt(Receipt receipt); // insert table Receipt
    public String updateReceipt(Receipt receipt); // update table Receipt
    public String deleteReceipt(Receipt receipt); // delete table Receipt
    public String DeleteReceiptDetail(String receiptDetailId , String receiptId); // delete table ReceiptDetail
    public String DeleteReceiptBank(String receiptId,int index); // delete table Receipt ===> chqNo ???
    public String DeleteReceiptChq(String receiptCreditId , String receiptId); // delete table ReceiptCredit
    
}
