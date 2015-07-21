/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao;

import com.smi.travel.datalayer.view.entity.InvoiceSupplier;
import java.util.List;
/**
 *
 * @author chonnasith
 */
public interface InvoiceSuppilerDao {
    public List<InvoiceSupplier> getListInvoiceSupplier();   
    public InvoiceSupplier getDataInvoiceSuppiler(String InputInvoiceSupCode);
}
