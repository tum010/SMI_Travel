/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao;

import java.util.List;

/**
 *
 * @author chonnasith
 */
public interface ReceiptDao {
    public List getReceipt(String receiptId,int option);
    public List getReceiptSummary(String dateFrom,String dateTo,String departmentRec,String recType,String status,String username);
}
