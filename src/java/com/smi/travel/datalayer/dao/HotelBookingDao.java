/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.HotelBooking;
import java.util.List;

/**
 *
 * @author sumeta
 */
public interface HotelBookingDao {
    public List<HotelBooking> getListBookingHotel(HotelBooking hotelBooking,int option);
    public List<HotelBooking> getListBookingHotelFromRefNo(String RefNo);
    public int insertBookingHotel(HotelBooking hotelBooking);
    public int updateBookingHotel(HotelBooking hotelBooking);
    public int DeleteBookingHotel(HotelBooking hotelBooking);
    public HotelBooking getBookingHotelFromID(String HotelBookingID);
    public int cancelBookHotel(String HotelBookingID);
    public int enableBookHotel(String HotelBookingID);
    public int DeleteRoom(HotelBooking hotel,String roomId);
    public int DeleteRequest(HotelBooking hotel,String requestId);
    public int DeletePassenger(HotelBooking hotel,String passengerId);
}
