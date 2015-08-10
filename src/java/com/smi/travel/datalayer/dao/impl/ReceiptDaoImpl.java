/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.ReceiptDao;
import com.smi.travel.datalayer.entity.Receipt;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Surachai
 */
public class ReceiptDaoImpl implements ReceiptDao{

    @Override
    public List<HashMap<String,Object>> getInvoiceFromInvoiceNumber(String InvoiceNumber) {
        List<HashMap<String,Object>> List = new LinkedList<HashMap<String, Object>>();
        HashMap<String, Object> result = new HashMap<String, Object>();
        
        result.put("invoiceId", null);
        result.put("product", null);
        result.put("Invoiceno", null);
        result.put("description", null);
        result.put("price", null);
        return List;
    }

    @Override
    public Receipt getReceiptfromReceiptNo(String ReceiptNo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public String UpdateFinanceStatusReceipt(String ReceiptNo, int status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

   
    
}
