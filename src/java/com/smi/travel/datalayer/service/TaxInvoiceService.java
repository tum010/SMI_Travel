/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.TaxInvoiceDao;
import com.smi.travel.datalayer.entity.TaxInvoice;
import java.util.List;

/**
 *
 * @author chonnasith
 */
public class TaxInvoiceService {
    private TaxInvoiceDao taxInvoiceDao;
    
    public String saveInvoice(TaxInvoice taxInvoice){
        if((taxInvoice.getId() != null) && (taxInvoice.getId() != "")){
            return getTaxInvoiceDao().updateTaxInvoice(taxInvoice);
        }else{
            return getTaxInvoiceDao().insertTaxInvoice(taxInvoice);
        }
    }

    public TaxInvoiceDao getTaxInvoiceDao() {
        return taxInvoiceDao;
    }

    public void setTaxInvoiceDao(TaxInvoiceDao taxInvoiceDao) {
        this.taxInvoiceDao = taxInvoiceDao;
    }
    
}
