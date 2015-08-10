/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
    public List<HashMap<String,Object>> getInvoiceFromInvoiceNumber(String InvoiceNumber);
    public Receipt getReceiptfromReceiptNo(String ReceiptNo);
    public String UpdateFinanceStatusReceipt(String ReceiptId,int status);
    // from Receipt rec where rec.MFinanceItemstatus.id = :status and rec.id = :ReceiptId
}
