/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.MFilghtDao;
import com.smi.travel.datalayer.entity.MFlight;
import java.util.List;

/**
 *
 * @author Surachai
 */
public class MFlightService {
    private MFilghtDao mFlightDao;

   public List<MFlight> searchFlight(MFlight currency,int option) {
        return mFlightDao.getListFlight(currency,option);
    }
    
    public String validateFlight(MFlight  Vflight,String operation){
        String validate = "";
        MFlight flight = new MFlight();
        flight.setCode(Vflight.getCode());
        List<MFlight> list = mFlightDao.getListFlight(flight,1);
        if(list != null){
            if("update".equalsIgnoreCase(operation)){
                if(!(list.get(0).getId().equalsIgnoreCase(Vflight.getId()))){
                    validate = "flight code already exist";
                }
            }else{
                 validate = "flight code already exist";
            }
            
        }
        flight.setName(Vflight.getName());
        flight.setCode(null);
        list = mFlightDao.getListFlight(flight,1);
        if(list != null){      
            if("update".equalsIgnoreCase(operation)){
                if(!(list.get(0).getId().equalsIgnoreCase(Vflight.getId()))){
                    validate = "flight name already exist";  
                }
            }else{
                 validate = "flight name already exist";  
            }
            
        }
        return validate;
    }

    public int insertFlight(MFlight flight) {
        return mFlightDao.insertFlight(flight);
    }

    public int UpdateFlight(MFlight flight) {
        return mFlightDao.updateFlight(flight);
    }

    public int DeleteFlight(MFlight flight) {
        return mFlightDao.DeleteFlight(flight);
    }   
    
    public MFilghtDao getmFlightDao() {
        return mFlightDao;
    }

    public void setmFlightDao(MFilghtDao mFlightDao) {
        this.mFlightDao = mFlightDao;
    }
    
    
    
}
