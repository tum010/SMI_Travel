/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.HotelDao;
import com.smi.travel.datalayer.entity.Hotel;
import java.util.List;

/**
 *
 * @author Surachai
 */
public class HotelService {

    private HotelDao hotelDao;

    public List<Hotel> getListHotel(Hotel hotel, int option) {
        return hotelDao.getListHotel(hotel, option);
    }
    
    public Hotel getHotelFromID(String HotelID){
        return hotelDao.getHotelFromID(HotelID);
    }

    public String validateHotel(Hotel Vhotel, String operation) {
        String validate = "";
        Hotel hotel = new Hotel();
        hotel.setCode(Vhotel.getCode());
        List<Hotel> list = hotelDao.getListHotel(hotel, 1);
        if (list != null) {
            if ("update".equalsIgnoreCase(operation)) {
                if (!(list.get(0).getId().equalsIgnoreCase(Vhotel.getId()))) {
                    validate = "hotel code already exist";
                }
            } else {
                validate = "hotel code already exist";
            }

        }
        hotel.setName(Vhotel.getName());
        hotel.setCode(null);
        list = hotelDao.getListHotel(hotel, 1);
        if (list != null) {
            if ("update".equalsIgnoreCase(operation)) {
                if (!(list.get(0).getId().equalsIgnoreCase(Vhotel.getId()))) {
                    validate = "hotel name already exist";
                }
            } else {
                validate = "hotel name already exist";
            }
        }
        return validate;
    }

    public int insertHotel(Hotel hotel) {
        return hotelDao.insertHotel(hotel);
    }

    public int updateHotel(Hotel hotel) {
        return hotelDao.updateHotel(hotel);
    }

    public int DeleteHotel(Hotel hotel) {
        return hotelDao.DeleteHotel(hotel);
    }

    public HotelDao getHotelDao() {
        return hotelDao;
    }

    public void setHotelDao(HotelDao hotelDao) {
        this.hotelDao = hotelDao;
    }

}
