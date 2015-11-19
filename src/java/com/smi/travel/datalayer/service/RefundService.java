/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.MRunningCodeDao;
import com.smi.travel.datalayer.dao.RefundDao;
import com.smi.travel.datalayer.entity.AirticketRefund;
import com.smi.travel.datalayer.entity.RefundAirticket;
import java.util.List;

/**
 *
 * @author Kanokporn
 */
public class RefundService {
    private RefundDao refundDao;
    private MRunningCodeDao mRunningCodeDao;
    
    public List searchRefundTicket(String airbookingid){
        return refundDao.searchRefundTicket(airbookingid);
    }
    
    public List searchRefundTicket(String airbookingid,String refundid){
        return refundDao.searchRefundTicket(airbookingid,refundid);
    }
    
    public List listRefundDetail(String refundid){
        return refundDao.listRefundDetail(refundid);
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
    
    public String saveRefund(AirticketRefund airticketrefund){
        String refundNo = "";
        if(airticketrefund.getId() != null && !"".equals(airticketrefund.getId())){
            return refundDao.updateRefund(airticketrefund);
        }else{
            refundNo = getmRunningCodeDao().gennarateRunningCode("RA", 6);
            airticketrefund.getRefundAirticket().setRefundNo(refundNo);
            return refundDao.saveRefund(airticketrefund);
        }  
    }
    
    public List searchRefund(RefundAirticket refund){
        return refundDao.searchRefund(refund);
    }
    
    public String deleteAirticketRefund(String airticketRefund,String refundid){
        return refundDao.deleteAirticketRefund(airticketRefund,refundid);
    }
    
    public String deleteAirticketRefundDetail(String airticketRefund,String refundid,String refunddetailid){
        return refundDao.deleteAirticketRefundDetail(airticketRefund, refundid, refunddetailid);
    }

    public MRunningCodeDao getmRunningCodeDao() {
        return mRunningCodeDao;
    }

    public void setmRunningCodeDao(MRunningCodeDao mRunningCodeDao) {
        this.mRunningCodeDao = mRunningCodeDao;
    }
    
}
