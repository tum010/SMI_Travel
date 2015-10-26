/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.PackageTourHotelDao;
import java.util.List;

/**
 *
 * @author Kanokporn
 */
public class PackageTourHotelService {
    private PackageTourHotelDao packageTourHotelDao;
    
    public List getHotelSummary(String from,String to ,String department){
        return packageTourHotelDao.getHotelSummary(from, to, department);
    }

    public PackageTourHotelDao getPackageTourHotelDao() {
        return packageTourHotelDao;
    }

    public void setPackageTourHotelDao(PackageTourHotelDao packageTourHotelDao) {
        this.packageTourHotelDao = packageTourHotelDao;
    }
    
    
}
