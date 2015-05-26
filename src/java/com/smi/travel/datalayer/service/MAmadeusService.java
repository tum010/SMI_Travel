/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.service;
import java.util.List;
import com.smi.travel.datalayer.dao.MAmadeusDao;
import com.smi.travel.datalayer.entity.MAmadeus;
/**
 *
 * @author Surachai
 */
public class MAmadeusService {
     private MAmadeusDao amadeusDao;
    public List<MAmadeus> getAmadeusList() {
        return amadeusDao.getAmadeusList();
    }
    
    public int EditGalileo(MAmadeus amadeus) {
         return amadeusDao.EditAmadeus(amadeus);
    }

    public MAmadeusDao getAmadeusDao() {
        return amadeusDao;
    }

    public void setAmadeusDao(MAmadeusDao amadeusDao) {
        this.amadeusDao = amadeusDao;
    }
    
    
}
