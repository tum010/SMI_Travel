/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.TaxInvoiceDao;
import com.smi.travel.datalayer.entity.TaxInvoice;
import com.smi.travel.datalayer.entity.TaxInvoiceDetail;
import com.smi.travel.datalayer.view.entity.TaxInvoiceView;
import java.math.BigDecimal;
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
    
    public TaxInvoice getTaxInvoiceFromTaxInvNo(String TaxInvNo, String Page) {
        return taxInvoiceDao.getTaxInvoiceFromTaxInvNo(TaxInvNo,Page);
    }
    
    public List<TaxInvoiceView> SearchTaxInvoiceFromFilter(String From,String To,String Department){
        return taxInvoiceDao.SearchTaxInvoiceFromFilter(From,To,Department);
    }

    public TaxInvoiceDao getTaxInvoiceDao() {
        return taxInvoiceDao;
    }

    public void setTaxInvoiceDao(TaxInvoiceDao taxInvoiceDao) {
        this.taxInvoiceDao = taxInvoiceDao;
    }

    public String DeleteTaxInvoiceInvoiceDetail(TaxInvoiceDetail taxInvoiceDetail) {
        return taxInvoiceDao.DeleteTaxInvoiceInvoiceDetail(taxInvoiceDetail);
    }

    public String checkInvoiceDetailValue(String id, BigDecimal cost, BigDecimal amount) {
        return taxInvoiceDao.checkInvoiceDetailValue(id,cost,amount);
    }

    public String checkCreditNote(String id) {
        return taxInvoiceDao.checkCreditNote(id);
    }
    
    
}
