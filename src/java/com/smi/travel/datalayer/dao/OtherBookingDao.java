/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.OtherBooking;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.report.model.OtherAgentCommission;
import com.smi.travel.datalayer.report.model.OtherGuideCommissionInfo;
import com.smi.travel.datalayer.view.entity.OtherBookingView;
import com.smi.travel.datalayer.view.entity.OtherBookingViewMin;
import com.smi.travel.datalayer.view.entity.OtherTicketView;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface OtherBookingDao {
    public List<OtherBooking> getListBookingOtherFromRefno(String refno);
    public List<OtherBookingView> getListBookingAllView(String name);
    public List<OtherBooking> getListBookingAll();
    public OtherBooking getBookDetailOtherFromID(String OtherBookingID);
    public List<OtherBookingViewMin> getListBookingOtherComission(String StartDate, String EndDate,String agentID,String guideID);
    public List<String> insertBookDetailOther(OtherBooking otherbook, SystemUser user);
    public List<String> updateBookDetailOther(OtherBooking otherbook);
    public int cancelBookDetailOther(String otherID);
    public int enableBookDetailOther(String otherID);
    public String saveOtherBookCommission(List<OtherBooking> BookList);
    public Boolean CheckUsabilityCoupon(String CouponId);
    public List<OtherBooking> searchOtherBooking(String name);
    public String saveStockDetailOther(OtherBooking Other, SystemUser user, String addticket, String adTicket, String chTicket, String infTicket, String itemid);
    public List<OtherTicketView> getListStockDetail(String otherBookingId);
    public String updateStockTicketStatus(String stockTicketId,String status);
    public String checkStock(String productID, String otherdate);
    public OtherGuideCommissionInfo getOtherGuideCommissionInfoReport(String datefrom,String dateto,String username,String guideid);
    public OtherAgentCommission getOtherAgentCommissionReport(String datefrom,String dateto,String user,String agentid);
    
    public String getAgentCommission(String otherDate,String row,String agentId,String price);
    public String getGuideCommission(String otherDate,String row,String price);
}
