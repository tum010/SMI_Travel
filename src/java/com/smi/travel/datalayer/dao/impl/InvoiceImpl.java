/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.InvoiceDao;
import com.smi.travel.datalayer.entity.Invoice;

/**
 *
 * @author Surachai
 */
public class InvoiceImpl implements InvoiceDao{

    @Override
    public String insertInvoice(Invoice invoice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String updateInvoice(Invoice invoice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String deleteInvoice(Invoice invoice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String UpdateInvoiceStatus(int StatusId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String DeleteInvoiceDetail(String InvoiceDetailId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private String GenerateInvoiceCode(){
        String code = "";
        
        return code;
    }
    
}
