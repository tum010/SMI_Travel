/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.MExchangeRateDao;
import com.smi.travel.datalayer.entity.MExchangeRate;
import java.util.List;

/**
 *
 * @author Kanokporn
 */
public class MExchangeRateService {
    private MExchangeRateDao mExchangeRateDao;
    
    public List searchExchangeRate(String from, String to, String currency){
        return mExchangeRateDao.searchExchangeRate(from, to, currency);
    }
    
    public List searchExchangeRateById(String exchangedate, String currency){
        return mExchangeRateDao.searchExchangeRateById(exchangedate,currency);
    }
    
    public String findExchangeDuplicate(String exchangedate, String currency){
        return mExchangeRateDao.findExchangeDuplicate(exchangedate, currency);
    }
    
    public String insertExchange(MExchangeRate mExchangeRate){
        if(mExchangeRate.getId() != null && !"".equals(mExchangeRate.getId())){
            return mExchangeRateDao.updateExchange(mExchangeRate);
        }else{
            return mExchangeRateDao.insertExchange(mExchangeRate);
        }
    }
    
    public String deleteExchange(MExchangeRate mExchangeRate){
        return mExchangeRateDao.deleteExchange(mExchangeRate);
    }
    
    public MExchangeRateDao getmExchangeRateDao() {
        return mExchangeRateDao;
    }

    public void setmExchangeRateDao(MExchangeRateDao mExchangeRateDao) {
        this.mExchangeRateDao = mExchangeRateDao;
    }
    
}
