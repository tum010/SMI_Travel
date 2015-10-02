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

    public String saveHost(MHost host) {
        if(host.getId() != null && !"".equals(host.getId())){
            return mHostDao.updateHost(host);
        }else{
            return mHostDao.insertHost(host);
        }
    }

    public String UpdateHost(MHost currency) {
        return mHostDao.updateHost(currency);
    }

    public String DeleteHost(MHost currency) {
        return mHostDao.DeleteHost(currency);
    }

    public MHostDao getmHostDao() {
        return mHostDao;
    }

    public void setmHostDao(MHostDao mHostDao) {
        this.mHostDao = mHostDao;
    }
    
    
    
    public String validateHost(MHost  Vhost){
        String validate = "";
        MHost host = new MHost();
        int num = 0;
        List<MHost> list = mHostDao.searchListHost();
        if(list != null){
            if(Vhost.getId() == null){
                for (int i = 0; i < list.size(); i++) {
                    if(list.get(i).getCode().equals(Vhost.getCode())){
                        validate = "host code already exist";
                        num = 1;
                    }
                    if(list.get(i).getName().equals(Vhost.getName())){
                        if(num == 0){
                            validate = "host name already exist";
                        }else{
                            validate = "host code and name already exist";
                        }
                    }
                }
            }
        }
        return validate;
    }
    
}
