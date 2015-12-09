/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.MExchangeRateDao;
import com.smi.travel.datalayer.entity.MExchangeRate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Kanokporn
 */
public class MExchangeRateImpl extends HibernateDaoSupport implements MExchangeRateDao{

    @Override
    public List searchExchangeRate(String from, String to, String currency) {
        System.out.println("From Date : " + from );
        System.out.println("To Date : " + to);
        System.out.println("Currency : " + currency);
        String query = "";
        int AndQuery = 0;
        
        if(from == null  && to == null  &&  currency == null ){
            query = " FROM MExchangeRate  mg " ; 
        }else{
            if("".equals(from) && "".equals(to) && "".equals(currency)){
                query = " FROM MExchangeRate  mg " ; 
            }else{
                query = " FROM MExchangeRate  mg  where " ;
            }
        }
        if ((from != null )&&(!"".equalsIgnoreCase(from))) {
            if ((to != null )&&(!"".equalsIgnoreCase(to))) {
                if(AndQuery == 1){
                     query += " and mg.exdate  BETWEEN  '" + from + "' AND '" + to + "' ";
                }else{
                    AndQuery = 1;
                     query += " mg.exdate  BETWEEN  '" + from + "' AND '" + to + "' ";
                }
            }
        }
        if(currency != null && (!"".equalsIgnoreCase(currency))){
            if(AndQuery == 1){
                query += " and mg.currency = '" + currency + "'";
           }else{
               AndQuery = 1;
               query += " mg.currency = '" + currency + "'";
           }
        }
        System.out.println("query exchange: "+query );
        List<MExchangeRate> list = getHibernateTemplate().find(query);
        if(list.isEmpty()){
            return null;
        }
        return list;
    }

    @Override
    public String insertExchange(MExchangeRate mExchangeRate) {
        String result = "";
        try{
            getHibernateTemplate().save(mExchangeRate);
            result = "save success";
        }catch(Exception e){
            result = "save unsuccess";
        }
        return result;
    }

    @Override
    public String updateExchange(MExchangeRate mExchangeRate) {
        String result = "";
        try{
            getHibernateTemplate().update(mExchangeRate);
            result = "update success";
        }catch(Exception e){
            result = "update unsuccess";
        }
        return result;
    }

    @Override
    public String deleteExchange(MExchangeRate mExchangeRate) {
        String result = "";
        try{
            getHibernateTemplate().delete(mExchangeRate);
            result = "delete success";
        }catch(Exception e){
            result = "delete unsuccess";
        }
        return result;
    }

    @Override
    public List searchExchangeRateById(String exchangedate, String currency) {
        String query = "";
        int AndQuery = 0;
        
        if(exchangedate == null  &&  currency == null ){
            query = " FROM MExchangeRate  mg " ; 
        }else{
           
            query = " FROM MExchangeRate  mg  where " ;
        }
        if (exchangedate != null ) {
            if(AndQuery == 1){
                 query += " and mg.exdate  = '" + exchangedate + "' ";
            }else{
                AndQuery = 1;
                 query += " mg.exdate  = '" + exchangedate + "' ";
            }
        }
        if(currency != null && (!"".equalsIgnoreCase(currency))){
            if(AndQuery == 1){
                query += " and mg.currency = '" + currency + "'";
           }else{
               AndQuery = 1;
               query += " mg.currency = '" + currency + "'";
           }
        }
        System.out.println("query exchange: "+query );
        List<MExchangeRate> list = getHibernateTemplate().find(query);
        if(list.isEmpty()){
            return null;
        }
        return list;
    }
    
    
}
