/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.service;
import com.smi.travel.datalayer.dao.PaymentWendytourDao;
import com.smi.travel.datalayer.entity.MPaytype;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.PaymentDetailWendy;
import com.smi.travel.datalayer.entity.PaymentStock;
import com.smi.travel.datalayer.entity.PaymentWendy;
import com.smi.travel.datalayer.entity.PaymentWendyReference;
import com.smi.travel.datalayer.entity.TourOperationDesc;
import com.smi.travel.datalayer.view.dao.InvoiceSuppilerDao;
import com.smi.travel.datalayer.view.entity.InvoiceSupplier;
import com.smi.travel.datalayer.view.entity.PaymentWendytourView;
import java.util.List;
/**
 *
 * @author chonnasith
 */
public class PaymentTourHotelService {
    
    private InvoiceSuppilerDao invoiceSuppilerDao;
    private PaymentWendytourDao paymentWendytourDao;

    public InvoiceSuppilerDao getInvoiceSuppilerDao() {
        return invoiceSuppilerDao;
    }

    public void setInvoiceSuppilerDao(InvoiceSuppilerDao invoiceSuppilerDao) {
        this.invoiceSuppilerDao = invoiceSuppilerDao;
    }
    
    public List<InvoiceSupplier> getListInvoiceSuppiler() {
        return getInvoiceSuppilerDao().getListInvoiceSupplier();
    }
    
    public InvoiceSupplier getDataInvoiceSuppiler(String InputInvoiceSupCode) {
        return getInvoiceSuppilerDao().getDataInvoiceSuppiler(InputInvoiceSupCode);
    }

    public List<PaymentWendytourView> getListPayment(String inputFromDate, String inputToDate, String selectPvType, String InvoiceSupCode, String selectStatus) {
        return getPaymentWendytourDao().SearchPaymentFromFilter(inputFromDate, inputToDate, selectPvType, InvoiceSupCode, selectStatus);
    }

    /**
     * @return the paymentWendytourDao
     */
    public PaymentWendytourDao getPaymentWendytourDao() {
        return paymentWendytourDao;
    }

    /**
     * @param paymentWendytourDao the paymentWendytourDao to set
     */
    public void setPaymentWendytourDao(PaymentWendytourDao paymentWendytourDao) {
        this.paymentWendytourDao = paymentWendytourDao;
    }

    public String deletePaymentWendy(PaymentWendy paymentWendy) {
        return this.paymentWendytourDao.DeletePaymentWendy(paymentWendy);
    }

    public String InsertPaymentWendy(PaymentWendy payment,String option) {
        return this.paymentWendytourDao.InsertPaymentWendy(payment,option);
    }    
    
    public String DeletePaymentWendyDetail(PaymentDetailWendy DetailID) {
        return this.paymentWendytourDao.DeletePaymentWendyDetail(DetailID);
    }   
    
    public PaymentWendy getPaymentWendyFromID(String paymentId) {
        return this.paymentWendytourDao.getPaymentWendyFromID(paymentId);
    }
       
    public Master getMasterFromRefno(String refno) {
        return this.paymentWendytourDao.getMasterFromRefno(refno);
    }
    
    public List<String> getMasterAll(){
        return this.paymentWendytourDao.getMasterAll();
    }

    public List<PaymentDetailWendy> getPaymentDetailWendyList(String paymentId) {
        return this.paymentWendytourDao.getPaymentDetailWendyList(paymentId);
    }
    
    public String UpdatePaymentWendy(PaymentWendy payment,String option) {
        return this.paymentWendytourDao.UpdatePaymentWendy(payment,option);
    }

    public MPaytype getAccountCode(String PayType) {
        return this.paymentWendytourDao.getAccountCode(PayType);
    }

    public PaymentWendy getInvoiceSupCodeByGuideName(String guideName) {
        return this.paymentWendytourDao.getInvoiceSupCodeByGuideName(guideName);
    }

    public String getGuideName(PaymentWendy paymentWendy) {
        return this.paymentWendytourDao.getGuideName(paymentWendy);
    }
            
    public PaymentWendy getPaymentWendyFromDayTourOperation(TourOperationDesc paymentWendy) {
        return this.paymentWendytourDao.getPaymentWendyFromDayTourOperation(paymentWendy);
    }        

    public String InsertPaymentWendyReference(PaymentWendyReference paymentWendyReference) {
        return this.paymentWendytourDao.InsertPaymentWendyReference(paymentWendyReference);
    }

    public MPaytype getMPayTypeFromPayTypeId(String payTypeId) {
        return this.paymentWendytourDao.getMPayTypeFromPayTypeId(payTypeId);
    }

    public PaymentStock getPaymentStockFromPayStockNo(String payStock) {
        return this.paymentWendytourDao.getPaymentStockFromPayStockNo(payStock);
    }

    public List<InvoiceSupplier> getListInvoiceSuppilerBySQLQuery(String name) {
        return this.paymentWendytourDao.getListInvoiceSuppilerBySQLQuery(name);
    }
}
