/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.RefundDao;
import java.util.List;

/**
 *
 * @author Kanokporn
 */
public class RefundService {
    private RefundDao refundDao;
    
    public List searchRefundTicket(String airbookingid){
        return refundDao.searchRefundTicket(airbookingid);
    }
    
    public List selectTicketNo(String refno){
        return refundDao.selectTicketNo(refno);
    }

    public RefundDao getRefundDao() {
        return refundDao;
    }

    public void setRefundDao(RefundDao refundDao) {
        this.refundDao = refundDao;
    }
    
}
