/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.MExchangeRateDao;

/**
 *
 * @author Kanokporn
 */
public class MExchangeRateService {
    private MExchangeRateDao mExchangeRateDao;

    public MExchangeRateDao getmExchangeRateDao() {
        return mExchangeRateDao;
    }

    public void setmExchangeRateDao(MExchangeRateDao mExchangeRateDao) {
        this.mExchangeRateDao = mExchangeRateDao;
    }
    
}
