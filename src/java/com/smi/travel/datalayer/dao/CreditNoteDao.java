/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.CreditNote;
import com.smi.travel.datalayer.view.entity.CreditNoteView;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface CreditNoteDao {
    public String insertCreditNote(CreditNote note); // insert table CreditNote
    public String updateCreditNote(CreditNote note); // update table CreditNote
    public String deleteCreditNote(CreditNote note); // delete table CreditNote
    public CreditNote getCreditNoteFromCNNo(String CNNo, String department);//from CreditNote cn where cn.cnNo = :CNNo
    public String DeleteCreditNoteDetail(String CreditNoteDetailId);//Delete from CreditNoteDetail cn where cn.id = :CreditNoteDetailId
    public String UpdateFinanceStatusCreditNote(String CNId, String status);
    public String gennarateTaxInvoiceNo(Date createDate,String department);
    public List<CreditNoteView> getCreditNoteFromFilter(String from,String to,String Department,String status);
    // UPDATE CreditNote cn set cn.MFinanceItemstatus.id = :status  WHERE cn.id = :CNId
    //public TaxInvoiceView SearchTaxInvoiceFromFilter(String from,String To,String Department);
}
