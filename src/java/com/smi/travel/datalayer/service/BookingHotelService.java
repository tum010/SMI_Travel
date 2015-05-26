/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.HotelBookingDao;
import com.smi.travel.datalayer.entity.HotelBooking;
import java.util.List;

/**
 *
 * @author sumeta
 */
public class BookingHotelService {
    
    private HotelBookingDao hotelBookingDao;

    public List<HotelBooking> getListHotel(HotelBooking hotelBooking, int option) {
        return hotelBookingDao.getListBookingHotel(hotelBooking, option);
    }
    
    public List<HotelBooking> getHotelBookFromRefNo(String Refno){
        return hotelBookingDao.getListBookingHotelFromRefNo(Refno);
    }
    
    public HotelBooking getHotelFromID(String HotelBookingID){
        return hotelBookingDao.getBookingHotelFromID(HotelBookingID);
    }

    public int insertHotel(HotelBooking hotelBooking) {
        return hotelBookingDao.insertBookingHotel(hotelBooking);
    }

    public int updateHotel(HotelBooking hotelBooking) {
        return hotelBookingDao.updateBookingHotel(hotelBooking);
    }

    public int DeleteHotel(HotelBooking hotel) {
        return hotelBookingDao.DeleteBookingHotel(hotel);
    }

    public HotelBookingDao getHotelBookingDao() {
        return hotelBookingDao;
    }

    public void setHotelBookingDao(HotelBookingDao hotelBookingDao) {
        this.hotelBookingDao = hotelBookingDao;
    }

    public int cancelBookHotel(String HotelBookingID){
        return hotelBookingDao.cancelBookHotel(HotelBookingID);
    }
    
    public int enableBookHotel(String HotelBookingID){
        return hotelBookingDao.enableBookHotel(HotelBookingID);
    }
    
    public int deleteRoom(HotelBooking hotelBooking ,String roomId){
    return hotelBookingDao.DeleteRoom(hotelBooking, roomId);
    }
    
    public int deleteRequest(HotelBooking hotelBooking ,String requestId){
    return hotelBookingDao.DeleteRequest(hotelBooking, requestId);
    }
    
    public int deletePassenger(HotelBooking hotelBooking ,String passengerId){
    return hotelBookingDao.DeletePassenger(hotelBooking, passengerId);
    }

   
}
