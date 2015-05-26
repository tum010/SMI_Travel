/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.MAirportDao;
import com.smi.travel.datalayer.entity.MAirport;
import java.util.List;

/**
 *
 * @author Surachai
 */
public class MAirportService {
    private MAirportDao mAirportDao;
     public List<MAirport> searchAirport(MAirport airport,int option) {
        return mAirportDao.getListAirport(airport,option);
    }
    
    public String validateAirport(MAirport Vairport,String operation){
        String validate = "";
        MAirport airport = new MAirport();
        airport.setCode(Vairport.getCode());
        List<MAirport> list = mAirportDao.getListAirport(airport,1);
        if(list != null){
            if("update".equalsIgnoreCase(operation)){
                if(!(list.get(0).getId().equalsIgnoreCase(Vairport.getId()))){
                    validate = "airport code already exist";
                }
            }else{
                 validate = "airport code already exist";
            }
           
        }
        airport.setName(Vairport.getName());
        airport.setCode(null);
        list = mAirportDao.getListAirport(airport,1);
        if(list != null){      
            if("update".equalsIgnoreCase(operation)){
                if(!(list.get(0).getId().equalsIgnoreCase(Vairport.getId()))){
                    validate = "airport name already exist";
                }
            }else{
                 validate = "airport name already exist";
            }
        }
        return validate;
    }

    public int insertAirport(MAirport airport) {

        return mAirportDao.insertAirport(airport);
    }

    public int UpdateAirport(MAirport airport) {
        return mAirportDao.updateAirport(airport);
    }

    public int DeleteAirport(MAirport airport) {
        return mAirportDao.DeleteAirport(airport);
    }

    public MAirportDao getmAirportDao() {
        return mAirportDao;
    }

    public void setmAirportDao(MAirportDao mAirportDao) {
        this.mAirportDao = mAirportDao;
    }
    
    
}
