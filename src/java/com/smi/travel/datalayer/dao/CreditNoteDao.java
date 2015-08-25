/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.CreditNote;

/**
 *
 * @author Surachai
 */
public interface CreditNoteDao {
    public String insertCreditNote(CreditNote note); // insert table CreditNote
    public String updateCreditNote(CreditNote note); // update table CreditNote
    public String deleteCreditNote(CreditNote note); // delete table CreditNote
    public CreditNote getCreditNoteFromCNNo(String CNNo);//from CreditNote cn where cn.cnNo = :CNNo
    public String DeleteCreditNoteDetail(String CreditNoteDetailId);//Delete from CreditNoteDetail cn where cn.id = :CreditNoteDetailId
    public String UpdateFinanceStatusCreditNote(String CNId,int status);
    public String gennarateTaxInvoiceNo();
    // UPDATE CreditNote cn set cn.MFinanceItemstatus.id = :status  WHERE cn.id = :CNId
    //public TaxInvoiceView SearchTaxInvoiceFromFilter(String from,String To,String Department);
}
