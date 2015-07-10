/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.service;
import com.smi.travel.datalayer.view.entity.InvoiceSupplier;
import com.smi.travel.datalayer.view.dao.InvoiceSuppilerDao;
import java.util.List;
/**
 *
 * @author chonnasith
 */
public class PaymentTourHotelService {
    
    private InvoiceSuppilerDao invoiceSuppilerDao;

    public InvoiceSuppilerDao getInvoiceSuppilerDao() {
        return invoiceSuppilerDao;
    }

    public void setInvoiceSuppilerDao(InvoiceSuppilerDao invoiceSuppilerDao) {
        this.invoiceSuppilerDao = invoiceSuppilerDao;
    }
    
    

    public List<InvoiceSupplier> getListInvoiceSuppiler() {
        return invoiceSuppilerDao.getListInvoiceSupplier();
    }

}
