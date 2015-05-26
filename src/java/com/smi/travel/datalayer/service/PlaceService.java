/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.MplaceStatusDao;
import com.smi.travel.datalayer.dao.PlaceDao;
import com.smi.travel.datalayer.entity.MPlacestatus;
import com.smi.travel.datalayer.entity.Place;
import java.util.List;

/**
 *
 * @author Surachai
 */
public class PlaceService {

    private PlaceDao placeDao;
    private MplaceStatusDao placeStatusDao;

    public List<Place> searchPlace(Place place, int option) {
        return placeDao.getListPlace(place, option);
    }
    
     public List<MPlacestatus> getListStatus() {
        return placeStatusDao.getListStatus();
    }

    public String validatePlace(Place place, String operation) {
        String validate = "";
        List<Place> list = placeDao.getListPlace(place, 1);
        if ((list != null)&&(list.size() != 0)) {
            if ("update".equalsIgnoreCase(operation)) {
                System.out.println("id : "+place.getId());
                System.out.println("list : "+(list.get(0).getId()));
                if (!(list.get(0).getId().equalsIgnoreCase(place.getId()))) {
                    validate = "place name already exist";
                }
            } else {
                validate = "place name code already exist";
            }

        }
        return validate;
    }

    public int insertPlace(Place place) {
        return placeDao.insertPlace(place);
    }

    public int UpdatePlace(Place place) {
        return placeDao.updatePlace(place);
    }

    public int DeletePlace(Place place) {
        return placeDao.DeletePlace(place);
    }

    public PlaceDao getPlaceDao() {
        return placeDao;
    }

    public void setPlaceDao(PlaceDao placeDao) {
        this.placeDao = placeDao;
    }

    public MplaceStatusDao getPlaceStatusDao() {
        return placeStatusDao;
    }

    public void setPlaceStatusDao(MplaceStatusDao placeStatusDao) {
        this.placeStatusDao = placeStatusDao;
    }

    
}
