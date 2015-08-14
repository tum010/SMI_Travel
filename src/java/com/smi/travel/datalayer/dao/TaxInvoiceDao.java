/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.TaxInvoice;
import com.smi.travel.datalayer.view.entity.TaxInvoiceView;

/**
 *
 * @author Surachai
 */
public interface TaxInvoiceDao {
    public String insertTaxInvoice(TaxInvoice tax); // insert table TaxInvoice
    public String updateTaxInvoice(TaxInvoice tax); // update table TaxInvoice
    public String deleteTaxInvoice(TaxInvoice tax); // delete table TaxInvoice
    public TaxInvoice getTaxInvoiceFromTaxInvNo(String TaxInvNo);//from TaxInvoice tax where tax.taxNo = :TaxInvNo
    public String DeleteTaxInvoiceInvoiceDetail(String TaxInvoiceDetailId);//Delete from TaxInvoiceDetail tax where tax.id = :TaxInvoiceDetailId
    public String UpdateFinanceStatusTaxInvoice(String TaxId,int status);
    // UPDATE TaxInvoice tax set tax.MFinanceItemstatus.id = :status  WHERE tax.id = :TaxId
    public TaxInvoiceView SearchTaxInvoiceFromFilter(String from,String To,String Department);
}
