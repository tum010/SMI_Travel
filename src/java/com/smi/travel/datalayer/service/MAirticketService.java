/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.service;
import com.smi.travel.datalayer.dao.MAirlineDao;
import com.smi.travel.datalayer.entity.MAirline;
import java.util.List;
/**
 *
 * @author Surachai
 */
public class MAirticketService {
    private MAirlineDao mAirlineDao;
    
    public List<MAirline> searchAirline(MAirline airline,int option ) {
        return mAirlineDao.getListAirLine(airline,option);
    }
    
    public int InsertAirline(MAirline airline){
        return mAirlineDao.insertAirLine(airline);
    }
    
    public String validateAirline(MAirline air,String operation){
        String validate = "";
        MAirline airline = new MAirline();
        airline.setCode(air.getCode());
        List<MAirline> list = mAirlineDao.getListAirLine(airline,1);
        if(list != null){
            if("update".equalsIgnoreCase(operation)){
                if(!(list.get(0).getId().equalsIgnoreCase(air.getId()))){
                    validate = "airline code already exist";
                }
            }else{
                validate = "airline code already exist";
            }
        }
        airline.setName(air.getName());
        airline.setCode(null);
        list = mAirlineDao.getListAirLine(airline,1);
        if(list != null){
            if("update".equalsIgnoreCase(operation)){
                if(!(list.get(0).getId().equalsIgnoreCase(air.getId()))){
                    validate = "airline name already exist";
                }
            }else{
                validate = "airline name already exist";  
            }
        }
        return validate;
    }

    public int UpdateAirline(MAirline airline){
        return mAirlineDao.updateAirLine(airline);
    }
    
    public int DeleteAirline(MAirline airline){
        return mAirlineDao.DeleteAirLine(airline);
    }

    public MAirlineDao getmAirlineDao() {
        return mAirlineDao;
    }

    public void setmAirlineDao(MAirlineDao mAirlineDao) {
        this.mAirlineDao = mAirlineDao;
    }
    
    
}
