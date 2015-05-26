/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.LandItinerary;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface LandItineraryDao {
    public List<LandItinerary> getListItinerary(String LandID);
    public int InsertItinerary(LandItinerary land);
    public int UpdateItinerary(LandItinerary land);
    public int DeleteItinerary(LandItinerary land);
}
