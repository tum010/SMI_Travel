/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.DaytourBooking;
import com.smi.travel.datalayer.report.model.DailyTourReport;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface DaytourBookingDao {
    public List<DaytourBooking> getTourReference(String TourCode,String TourDate);
    public String InsertBookingDaytour(DaytourBooking DayTourBook);
    public String UpdateBookingDaytour(DaytourBooking DayTourBook);
    public List<DaytourBooking> getListBookingDaytourFromRefno(String refno);
    public DaytourBooking getBookDetailDaytourFromID(String DaytourBookingID);
    public List<DaytourBooking> getTourJob();
    public int cancelBookDetailDaytour(String DaytourID);
    public int enableBookDetailDaytour(String DaytourID);
    public String DeleteBookingDaytourPrice(String DayTourPriceId);
    public String DeleteBookingDaytourPriceNotMatch(String TourID,String DaytourBookID);
    public DailyTourReport getDailyTourReport(String from, String to, String department, String detail, String user);
}
