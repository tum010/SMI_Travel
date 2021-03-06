/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.Billable;
import com.smi.travel.datalayer.entity.BillableDesc;
import com.smi.travel.datalayer.entity.Invoice;
import com.smi.travel.datalayer.entity.InvoiceDetail;
import com.smi.travel.datalayer.entity.TaxInvoice;
import com.smi.travel.datalayer.entity.TaxInvoiceDetail;
import com.smi.travel.datalayer.view.entity.TaxInvoiceView;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface TaxInvoiceDao {
    public String insertTaxInvoice(TaxInvoice tax); // insert table TaxInvoice
    public String updateTaxInvoice(TaxInvoice tax); // update table TaxInvoice
    public String deleteTaxInvoice(TaxInvoice tax); // delete table TaxInvoice
    public TaxInvoice getTaxInvoiceFromTaxInvNo(String TaxInvNo, String Page);//from TaxInvoice tax where tax.taxNo = :TaxInvNo
    public String DeleteTaxInvoiceInvoiceDetail(TaxInvoiceDetail taxInvoiceDetail);//Delete from TaxInvoiceDetail tax where tax.id = :TaxInvoiceDetailId
    public String UpdateFinanceStatusTaxInvoice(String TaxId,int status);
    // UPDATE TaxInvoice tax set tax.MFinanceItemstatus.id = :status  WHERE tax.id = :TaxId
    public List<TaxInvoiceDetail> getTaxInvoiceDetailFromInvDetailId(String invDetailId);
    public TaxInvoiceView getTaxInvoiceViewFromTaxNo(String TaxNo);
    public List<TaxInvoiceView> SearchTaxInvoiceFromFilter(String From,String To,String Department,String Status);
    public TaxInvoice getTaxInvoiceByTaxNo(String invoiceNo,String department);
    public String checkInvoiceDetailValue(String id, BigDecimal cost, BigDecimal amount);
    public String checkCreditNote(String id);
    public List<TaxInvoiceDetail> getTaxInvoiceDetailFromBillDescId(String invoiceDetailId);
    public TaxInvoice getTaxInvoiceByWildCardSearch(String taxInvId, String taxInvNo, String wildCardSearch, String keyCode, String department);
    public TaxInvoice getPostVatData(String taxInvId);
    public BigDecimal getTaxInvoiceAmountTotal(TaxInvoice taxInv,String creditNoteDetailId);
    public String checkIsProfitForSearchInvoice(String invDetailId);
    public String checkIsProfitForSearchRefNo(String invoiceDetailId);
    public String getInvoiceNoByInvoiceDetailId(String invoiceDetailId);
    public BillableDesc checkBillabledesc(String invoiceDetailId);
    public BigDecimal getProfitFromTaxInvoice(String invoiceDetailId, String taxInvoiceDetailId);
    public BigDecimal getExRateFromInvoiceDetail(String invoiceDetailId);
    public boolean checkInvNoProfit(String invoiceDetailId);
    public List<TaxInvoice> checkInvoiceAlready(String billableDescId);
    public List<TaxInvoice> checkRefNoAlready(String billableDescId);
    public BigDecimal getCostLocalFromTaxInvoiceDetailByInvoiceDetail(String invoiceDetailId, String taxDetailId);
    public BigDecimal getAmountLocalFromTaxInvoiceDetailByInvoiceDetail(String invoiceDetailId, String taxDetailId);
}
