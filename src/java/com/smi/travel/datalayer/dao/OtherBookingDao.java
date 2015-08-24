/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.Customer;
import com.smi.travel.datalayer.entity.OtherBooking;
import com.smi.travel.datalayer.entity.Product;
import com.smi.travel.datalayer.entity.StockDetail;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.view.entity.OtherTicketView;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface OtherBookingDao {
    public List<OtherBooking> getListBookingOtherFromRefno(String refno);
    public List<OtherBooking> getListBookingAll();
    public OtherBooking getBookDetailOtherFromID(String OtherBookingID);
    public List<OtherBooking> getListBookingOtherComission(String StartDate, String EndDate,String agentID,String guideID);
    public List<String> insertBookDetailOther(OtherBooking otherbook, SystemUser user);
    public List<String> updateBookDetailOther(OtherBooking otherbook);
    public int cancelBookDetailOther(String otherID);
    public int enableBookDetailOther(String otherID);
    public String saveOtherBookCommission(List<OtherBooking> BookList);
    public Boolean CheckUsabilityCoupon(String CouponId);
    public  List<OtherBooking> searchOtherBooking(Customer customer, int filter);
    public String saveStockDetailOther(OtherBooking Other, SystemUser user);
    public List<OtherTicketView> getListStockDetail(String otherBookingId);
    public String updateStockTicketStatus(String stockTicketId,String status);
}
