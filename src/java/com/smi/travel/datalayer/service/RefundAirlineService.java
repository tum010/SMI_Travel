/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.MRunningCodeDao;
import com.smi.travel.datalayer.dao.RefundAirticketDao;
import com.smi.travel.datalayer.dao.TicketFareAirlineDao;
import com.smi.travel.datalayer.entity.RefundAirticket;
import com.smi.travel.datalayer.entity.RefundAirticketDetail;
import com.smi.travel.datalayer.entity.TicketFareAirline;
import java.util.Iterator;

/**
 *
 * @author Surachai
 */
public class RefundAirlineService {

    public RefundAirticketDao refundAirticketDao;
    public TicketFareAirlineDao ticketFareAirlineDao;
    private MRunningCodeDao mRunningCodeDao;

    public RefundAirticketDao getRefundAirticketDao() {
        return refundAirticketDao;
    }

    public void setRefundAirticketDao(RefundAirticketDao refundAirticketDao) {
        this.refundAirticketDao = refundAirticketDao;
    }

    public TicketFareAirlineDao getTicketFareAirlineDao() {
        return ticketFareAirlineDao;
    }

    public void setTicketFareAirlineDao(TicketFareAirlineDao ticketFareAirlineDao) {
        this.ticketFareAirlineDao = ticketFareAirlineDao;
    }

    public TicketFareAirline getTicketFareBookingFromTicketNo(String ticketNo,String ticketId) {
        return this.ticketFareAirlineDao.getTicketFareFromTicketNo(ticketNo,ticketId);
    }

    public RefundAirticket getRefundAirTicketFromRefundNo(String refundNo) {
        RefundAirticket refund = this.refundAirticketDao.getRefundAirTicketFromRefundNo(refundNo);
        if (refund != null) {
            for (int i = 0; i < refund.getRefundAirticketDetails().size(); i++) {
                RefundAirticketDetail detail = (RefundAirticketDetail) refund.getRefundAirticketDetails().get(i);
                String ticketNo = "";
                if(detail.getAirticketPassenger() != null){
                    ticketNo = detail.getAirticketPassenger().getSeries1()
                            + detail.getAirticketPassenger().getSeries2()
                            + detail.getAirticketPassenger().getSeries3();
                    detail.setTicketFareAirline(ticketFareAirlineDao.getDetailTicketFareAirline(ticketNo));
                    detail.getTicketFareAirline().put("TicketNo", ticketNo);
                }else{
                    ticketNo = detail.getTicketNo();
                }
            }
        }
        return refund;
    }

    public String saveRefundAirTicket(RefundAirticket refund) {
        String status = "fail";
        String refundNo = "";
        try {
            if (refund.getRefundNo() == null || "".equals(refund.getRefundNo().trim())) {
                refundNo = getmRunningCodeDao().gennarateRunningCode("RA", 6);
                refund.setRefundNo(refundNo);
                status = this.refundAirticketDao.InsertRefundAirticket(refund);
            } else {
                refundNo = refund.getRefundNo();
                status = this.refundAirticketDao.UpdateRefundAirticket(refund);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if ("fail".equals(status)) {
            return status;
        } else {
            return refundNo;
        }
        
    }

    /**
     * @return the mRunningCodeDao
     */
    public MRunningCodeDao getmRunningCodeDao() {
        return mRunningCodeDao;
    }

    /**
     * @param mRunningCodeDao the mRunningCodeDao to set
     */
    public void setmRunningCodeDao(MRunningCodeDao mRunningCodeDao) {
        this.mRunningCodeDao = mRunningCodeDao;
    }

}
