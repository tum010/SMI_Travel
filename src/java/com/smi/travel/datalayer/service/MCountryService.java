/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.MCountryDao;
import com.smi.travel.datalayer.entity.MCountry;
import java.util.List;

/**
 *
 * @author Surachai
 */
public class MCountryService {
    private MCountryDao mCountryDao;
    
    public List<MCountry> searchCountry(MCountry city,int option) {
        return mCountryDao.getListCountry(city,option);
    }
    
    public List<MCountry> getListCountry() {
        return mCountryDao.getListCountry();
    }
    
    public String validateCountry(MCountry  Vcountry,String operation){
        String validate = "";
        MCountry country = new MCountry();
        country.setCode(Vcountry.getCode());
        List<MCountry> list = mCountryDao.getListCountry(country,1);
        if(list != null){
            if("update".equalsIgnoreCase(operation)){
                if(!(list.get(0).getId().equalsIgnoreCase(Vcountry.getId()))){
                    validate = "country code already exist";
                }
            }else{
                 validate = "country code already exist";
            }
            
        }
        country.setName(Vcountry.getName());
        country.setCode(null);
        list = mCountryDao.getListCountry(country,1);
        if(list != null){   
            if("update".equalsIgnoreCase(operation)){
                if(!(list.get(0).getId().equalsIgnoreCase(Vcountry.getId()))){
                    validate = "country name already exist";
                }
            }else{
                 validate = "country name already exist";
            }
        }
        return validate;
    }

    public int insertCountry(MCountry country) {

        return mCountryDao.insertCountry(country);
    }

    public int UpdateCountry(MCountry country) {
        return mCountryDao.updateCountry(country);
    }

    public int DeleteCountry(MCountry country) {
        return mCountryDao.DeleteCountry(country);
    }

    public MCountryDao getmCountryDao() {
        return mCountryDao;
    }

    public void setmCountryDao(MCountryDao mCountryDao) {
        this.mCountryDao = mCountryDao;
    }

    
}
