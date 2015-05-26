/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.MCurrencyDao;
import com.smi.travel.datalayer.entity.MCurrency;
import java.util.List;

/**
 *
 * @author Surachai
 */
public class MCurrencyService {
    private MCurrencyDao mCurrencyDao;

     public List<MCurrency> searchCurrency(MCurrency currency,int option) {
        return mCurrencyDao.getListCurrency(currency,option);
    }
    
    public String validateCurrency(MCurrency  Vcurrency,String operation){
        String validate = "";
        MCurrency currency = new MCurrency();
        currency.setCode(Vcurrency.getCode());
        List<MCurrency> list = mCurrencyDao.getListCurrency(currency,1);
        if(list != null){
            if("update".equalsIgnoreCase(operation)){
                if(!(list.get(0).getId().equalsIgnoreCase(Vcurrency.getId()))){
                    validate = "currency code already exist";
                }
            }else{
                 validate = "currency code already exist";
            }
            
        }
        currency.setDescription(Vcurrency.getDescription());
        currency.setCode(null);
        list = mCurrencyDao.getListCurrency(currency,1);
        if(list != null){      
            if("update".equalsIgnoreCase(operation)){
                if(!(list.get(0).getId().equalsIgnoreCase(Vcurrency.getId()))){
                      validate = "currency description already exist";  
                }
            }else{
                  validate = "currency description already exist";  
            }
           
        }
        return validate;
    }

    public int insertCurrency(MCurrency currency) {
        return mCurrencyDao.insertCurrency(currency);
    }

    public int UpdateCurrency(MCurrency currency) {
        return mCurrencyDao.updateCurrency(currency);
    }

    public int DeleteCurrency(MCurrency currency) {
        return mCurrencyDao.DeleteCurrency(currency);
    }

    public MCurrencyDao getmCurrencyDao() {
        return mCurrencyDao;
    }

    public void setmCurrencyDao(MCurrencyDao mCurrencyDao) {
        this.mCurrencyDao = mCurrencyDao;
    }
    
    
}
