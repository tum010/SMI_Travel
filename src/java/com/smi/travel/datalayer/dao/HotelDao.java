/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.Hotel;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface HotelDao {
    public List<Hotel> getListHotel(Hotel hotel,int option);
    public int insertHotel(Hotel hotel);
    public int updateHotel(Hotel hotel);
    public int DeleteHotel(Hotel hotel);
    public Hotel getHotelFromID(String HotelID);
}
