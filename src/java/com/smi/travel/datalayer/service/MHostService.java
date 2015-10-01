/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.MHostDao;
import com.smi.travel.datalayer.entity.MHost;
import java.util.List;

/**
 *
 * @author Kanokporn
 */
public class MHostService {
    private MHostDao mHostDao;

    public List<MHost> searchHost(MHost host) {
        return mHostDao.getListHost(host);
    }

    public int insertHost(MHost currency) {
        return mHostDao.insertHost(currency);
    }

    public int UpdateHost(MHost currency) {
        return mHostDao.updateHost(currency);
    }

    public int DeleteHost(MHost currency) {
        return mHostDao.DeleteHost(currency);
    }

    public MHostDao getmHostDao() {
        return mHostDao;
    }

    public void setmHostDao(MHostDao mHostDao) {
        this.mHostDao = mHostDao;
    }
    
    
    
    public String validateHost(MHost  Vhost,String operation){
        String validate = "";
        MHost currency = new MHost();
        currency.setCode(Vhost.getCode());
        List<MHost> list = mHostDao.getListHost(currency);
        if(list != null){
            if("update".equalsIgnoreCase(operation)){
                if(!(list.get(0).getId().equalsIgnoreCase(Vhost.getId()))){
                    validate = "currency code already exist";
                }
            }else{
                 validate = "currency code already exist";
            }
            
        }
        currency.setName(null);
        currency.setStatus(null);
        list = mHostDao.getListHost(currency);
        if(list != null){      
            if("update".equalsIgnoreCase(operation)){
                if(!(list.get(0).getId().equalsIgnoreCase(Vhost.getId()))){
                      validate = "host name already exist";  
                }
            }else{
                  validate = "host name already exist";  
            }
           
        }
        return validate;
    }
    
}
