/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.dao;

import com.smi.travel.datalayer.view.entity.PaymentTourCommissionView;
import com.smi.travel.datalayer.view.entity.TicketAircommissionView;
import java.util.List;

/**
 *
 * @author Jittima
 */
public interface TicketAircommissionViewDao {
     public List<TicketAircommissionView> getListTicketAircommissionView(String paymentNo);
     public List<PaymentTourCommissionView> getListPaymentTourCommissionView(String paymentNo);
     public PaymentTourCommissionView getPaymentTourCommissionView(String paymentId);
}
