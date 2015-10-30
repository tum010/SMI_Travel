/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.PaymentDetailWendy;
import com.smi.travel.datalayer.entity.PaymentWendy;
import com.smi.travel.datalayer.entity.PaymentWendyReference;
import com.smi.travel.datalayer.entity.TourOperationDesc;
import com.smi.travel.datalayer.view.entity.PaymentWendytourView;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface PaymentWendytourDao {
    public String InsertPaymentWendy(PaymentWendy payment,String option);
    public String UpdatePaymentWendy(PaymentWendy payment,String option);
    public String DeletePaymentWendy(PaymentWendy payment);
    public String DeletePaymentWendyDetail(PaymentDetailWendy DetailID);
    public PaymentWendy SearchPaymentWendyFromPayno(String payno);
    public PaymentWendy getPaymentWendyFromID(String payNo);
    public List<PaymentWendytourView> SearchPaymentFromFilter(String DateFrom ,String Dateto,String PVType ,String InvoiceSupCode, String selectStatus);
    public Master getMasterFromRefno(String refno);
    public List<String> getMasterAll();
    public List<PaymentDetailWendy> getPaymentDetailWendyList(String paymentId);
    public String getAccountCode(String PayType);
    public String getPaymentRefernenceCode(String from,String to,List<String> tour);
    public String getTourListFromDate(String from,String to);
    public PaymentWendy getInvoiceSupCodeByGuideName(String guideName);
    public String getGuideName(PaymentWendy paymentWendy);
    public PaymentWendy getPaymentWendyFromDayTourOperation(TourOperationDesc paymentWendy);
    public String InsertPaymentWendyReference(PaymentWendyReference paymentWendyReference);
    public List getPaymentTourHotelSummary(String from, String to, String pvtype, String status, String invSupCode, String printBy);
    public String checkDayTourOperationDetail(String tourId,String tourDate);
}
