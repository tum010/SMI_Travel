/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.MCityDao;
import com.smi.travel.datalayer.entity.MCity;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Surachai
 */
public class MCityImpl extends HibernateDaoSupport implements MCityDao{

    @Override
    public List<MCity> getListCity(MCity city ,int option) {
       String query ="from MCity c where ";
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
       if((city.getCode() != null) &&(!"".equalsIgnoreCase(city.getCode()))){
           query += " c.code "+queryOperation+" '"+Prefix_Subfix+city.getCode()+Prefix_Subfix+"'";
           check =1;
       }
       if((city.getName()!= null) &&(!"".equalsIgnoreCase(city.getName()))){
           if(check == 1){query += " and ";}
           query += " c.name "+queryOperation+" '"+Prefix_Subfix+city.getName()+Prefix_Subfix+"'";
           check =1;
       }
       if(check == 0){
           query = query.replaceAll("where", " ");
       }
       System.out.println("query : "+query );
       List<MCity> list = getHibernateTemplate().find(query);
       if(list.isEmpty()){
           return null;
       }
       return list;  
    }

    @Override
    public int insertCity(MCity city) {
        int result =0;
        try{
            getHibernateTemplate().save(city);
            result = 1;
        }catch(Exception e){
            result = 0;
        }
        
        return result;
    }

    @Override
    public int updateCity(MCity city) {
        int result =0;
        try{
            getHibernateTemplate().update(city);
            result = 1;
        }catch(Exception e){
            result = 0;
        }
        return result;
    }

    @Override
    public int DeleteCity(MCity city) {
        int result =0;
        try{
            getHibernateTemplate().delete(city);
            result = 1;
        }catch(Exception e){
            result = 0;
        }
        return result;
    }

    @Override
    public List<MCity> getListCity() {
         String query = "from MCity c ORDER BY c.name";
        List<MCity> list = getHibernateTemplate().find(query);
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

 
    
}
