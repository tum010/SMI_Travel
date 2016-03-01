/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.LandBooking;
import com.smi.travel.datalayer.entity.LandItinerary;
import com.smi.travel.datalayer.entity.PackageTour;
import com.smi.travel.datalayer.entity.Product;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface LandBookingDao {
    public List<LandBooking> getListBookingLandFromRefno(String refno);
    public LandBooking getBookDetailLandFromID(String LandID);
    public List<Product> getListLandProduct();
    public List<PackageTour> getListLandPackage();
    public int insertBookDetailLand(LandBooking land,List<LandItinerary> Itinerary );
    public int updateBookDetailLand(LandBooking land,List<LandItinerary> Itinerary,String DelItenarary,String delCity);
    public int cancelBookDetailLand(String landID);
    public int enableBookDetailLand(String landID);
    public String DeleteLandCity(String landCityID);
}
