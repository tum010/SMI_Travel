/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.AirticketRefund;
import java.util.List;

/**
 *
 * @author Kanokporn
 */
public interface RefundDao {
    public List searchRefundTicket(String airbookingid);
    public List selectTicketNo(String refno);
    public List listSector(String ticketid);
    public List listRefundDetail(String refundid);
    public String saveRefund(AirticketRefund airticketrefund);
}
