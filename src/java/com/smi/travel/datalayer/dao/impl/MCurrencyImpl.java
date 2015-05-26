/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.MCurrencyDao;
import com.smi.travel.datalayer.entity.MCurrency;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Surachai
 */
public class MCurrencyImpl extends HibernateDaoSupport implements MCurrencyDao {

    @Override
    public List<MCurrency> getListCurrency(MCurrency currency,int option) {
        String query = "from MCurrency c where ";
        String queryOperation = "";
        String Prefix_Subfix ="";
        int check =0;
        if(option == 1){
           queryOperation = " = ";
           Prefix_Subfix = "";
        }else if(option == 2){
           queryOperation = " Like ";
           Prefix_Subfix = "%";
        }
        if ((currency.getCode() != null) && (!"".equalsIgnoreCase(currency.getCode()))) {
            query += " c.code "+queryOperation+" '"+Prefix_Subfix+currency.getCode()+Prefix_Subfix+"'";
            check = 1;
        }
        if ((currency.getDescription() != null) && (!"".equalsIgnoreCase(currency.getDescription()))) {
            if (check == 1) {query += " and ";}
            query += " c.description "+queryOperation+" '"+Prefix_Subfix+currency.getDescription()+Prefix_Subfix+"'";
            check = 1;
        }
        if (check == 0) {
            query = query.replaceAll("where", " ");
        }
        System.out.println("query : " + query);
        List<MCurrency> list = getHibernateTemplate().find(query);
        if (list.isEmpty()) {
            return null;
        }
        return list;      
    }

    @Override
    public int insertCurrency(MCurrency currency) {
        int result =0;
        try{
            getHibernateTemplate().save(currency);
            result = 1;
        }catch(Exception e){
            result = 0;
        }
        return result;
    }

    @Override
    public int updateCurrency(MCurrency currency) {
        int result =0;
        try{
            getHibernateTemplate().update(currency);
            result = 1;
        }catch(Exception e){
            result = 0;
        }
        return result;
    }

    @Override
    public int DeleteCurrency(MCurrency currency) {
         int result =0;
        try{
            getHibernateTemplate().delete(currency);
            result = 1;
        }catch(Exception e){
            result = 0;
        }
        return result;
    }
    
}
