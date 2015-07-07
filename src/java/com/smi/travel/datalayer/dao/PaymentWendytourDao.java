/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.PaymentWendy;
import com.smi.travel.datalayer.view.entity.PaymentWendytourView;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface PaymentWendytourDao {
    public String InsertPaymentWendy(PaymentWendy payment);
    public String UpdatePaymentWendy(PaymentWendy payment);
    public String DeletePaymentWendy(PaymentWendy payment);
    public String DeletePaymentWendyDetail(String DetailID);
    public PaymentWendy SearchPaymentWendyFromPayno(String payno);
    public List<PaymentWendytourView> SearchPaymentFromFilter(PaymentWendy payment);
    
}
