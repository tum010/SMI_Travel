/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.RefundDao;

/**
 *
 * @author Kanokporn
 */
public class RefundService {
    private RefundDao refundDao;

    public RefundDao getRefundDao() {
        return refundDao;
    }

    public void setRefundDao(RefundDao refundDao) {
        this.refundDao = refundDao;
    }
    
    
}
