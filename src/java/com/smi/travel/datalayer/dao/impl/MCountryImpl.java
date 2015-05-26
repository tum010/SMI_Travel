/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.MCountryDao;
import com.smi.travel.datalayer.entity.MCountry;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Surachai
 */
public class MCountryImpl extends HibernateDaoSupport implements MCountryDao {

    @Override
    public List<MCountry> getListCountry(MCountry country,int option) {
        String query = "from MCountry c where ";
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
        if ((country.getCode() != null) && (!"".equalsIgnoreCase(country.getCode()))) {
            query += " c.code "+queryOperation+" '"+Prefix_Subfix+country.getCode()+Prefix_Subfix+"'";
            check = 1;
        }
        if ((country.getName() != null) && (!"".equalsIgnoreCase(country.getName()))) {
            if (check == 1) {query += " and ";}
            query += " c.name "+queryOperation+" '"+Prefix_Subfix+country.getName()+Prefix_Subfix+"'";
            check = 1;
        }
        if (check == 0) {
            query = query.replaceAll("where", " ");
        }
        System.out.println("query : " + query);
        List<MCountry> list = getHibernateTemplate().find(query);
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public int insertCountry(MCountry country) {
        int result =0;
        try{
            getHibernateTemplate().save(country);
            result = 1;
        }catch(Exception e){
            result = 0;
        }
        return result;
    }

    @Override
    public int updateCountry(MCountry country) {
        int result =0;
        try{
            getHibernateTemplate().update(country);
            result = 1;
        }catch(Exception e){
            result = 0;
        }
        return result;
    }

    @Override
    public int DeleteCountry(MCountry country) {
       int result =0;
        try{
            getHibernateTemplate().delete(country);
            result = 1;
        }catch(Exception e){
            result = 0;
        }
        return result;
    }

    @Override
    public List<MCountry> getListCountry() {
        String query = "from MCountry c ORDER BY c.name";
        List<MCountry> list = getHibernateTemplate().find(query);
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

}
