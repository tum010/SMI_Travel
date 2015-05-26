/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.MCityDao;
import com.smi.travel.datalayer.entity.MCity;
import java.util.List;

/**
 *
 * @author Surachai
 */
public class MCityService {

    private MCityDao mCityDao;

    public List<MCity> searchCity(MCity city,int option) {
        return mCityDao.getListCity(city,option);
    }
    
    public List<MCity> getlistCity() {
        return mCityDao.getListCity();
    }
    
    public String validateCity(MCity Vcity,String operation){
        String validate = "";
        MCity city = new MCity();
        city.setCode(Vcity.getCode());
        List<MCity> list = mCityDao.getListCity(city,1);
        if(list != null){
            if("update".equalsIgnoreCase(operation)){
                if(!(list.get(0).getId().equalsIgnoreCase(Vcity.getId()))){
                    validate = "city code already exist";
                }
            }else{
                 validate = "city code already exist";
            }
           
        }
        city.setName(Vcity.getName());
        city.setCode(null);
        list = mCityDao.getListCity(city,1);
        if(list != null){      
            if("update".equalsIgnoreCase(operation)){
                if(!(list.get(0).getId().equalsIgnoreCase(Vcity.getId()))){
                    validate = "city name already exist";
                }
            }else{
                 validate = "city name already exist";
            }
        }
        return validate;
    }

    public int insertCity(MCity airline) {

        return mCityDao.insertCity(airline);
    }

    public int UpdateCity(MCity airline) {
        return mCityDao.updateCity(airline);
    }

    public int DeleteCity(MCity airline) {
        return mCityDao.DeleteCity(airline);
    }

    public MCityDao getmCityDao() {
        return mCityDao;
    }

    public void setmCityDao(MCityDao mCityDao) {
        this.mCityDao = mCityDao;
    }

}
