/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.Place;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface PlaceDao {
    public List<Place> getListPlace(Place place,int option);
    public List<Place> getListPlaceFromStatus(String status);
    public int insertPlace(Place place);
    public int updatePlace(Place place);
    public int DeletePlace(Place place);
    public Place getPlaceFromId(String placeId);
}
