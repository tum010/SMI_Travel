/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.OtherBooking;
import com.smi.travel.datalayer.entity.Product;
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
    public int insertBookDetailOther(OtherBooking otherbook);
    public int updateBookDetailOther(OtherBooking otherbook);
    public int cancelBookDetailOther(String otherID);
    public int enableBookDetailOther(String otherID);
    public String saveOtherBookCommission(List<OtherBooking> BookList);
}
