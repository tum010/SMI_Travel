/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.TaxInvoiceDao;
import com.smi.travel.datalayer.entity.TaxInvoice;
import com.smi.travel.datalayer.view.entity.TaxInvoiceView;

/**
 *
 * @author Surachai
 */
public class TaxInvoiceImpl implements TaxInvoiceDao{

    @Override
    public String insertTaxInvoice(TaxInvoice tax) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String updateTaxInvoice(TaxInvoice tax) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String deleteTaxInvoice(TaxInvoice tax) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TaxInvoice getTaxInvoiceFromTaxInvNo(String TaxInvNo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String DeleteTaxInvoiceInvoiceDetail(String TaxInvoiceDetailId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String UpdateFinanceStatusTaxInvoice(String TaxId, int status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TaxInvoiceView SearchTaxInvoiceFromFilter(String from, String To, String Department) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public TaxInvoiceView mappingTaxInvoice(TaxInvoice tax){
        TaxInvoiceView taxview = new TaxInvoiceView();
        
        return taxview;
    }

    @Override
    public TaxInvoiceView getTaxInvoiceViewFromTaxNo(String TaxNo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
