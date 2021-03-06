/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.RefundAirticket;
import java.util.HashMap;

/**
 *
 * @author Surachai
 */
public interface RefundAirticketDao {
    public String InsertRefundAirticket(RefundAirticket refund);
    public String UpdateRefundAirticket(RefundAirticket refund);
    public String DeleteRefundAirticket(RefundAirticket refund);
    public Boolean DeleteRefundAirticketDetail(String refundDetailID); 
    public RefundAirticket getRefundAirTicketFromRefundNo(String refundNo);
    public Boolean checkPaymentAirticketRefund(String refundDetailId);
   
    
}
