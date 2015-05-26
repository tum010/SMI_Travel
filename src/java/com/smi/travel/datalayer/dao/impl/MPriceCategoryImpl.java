/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.MPriceCategoryDao;
import com.smi.travel.datalayer.entity.MPricecategory;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Surachai
 */
public class MPriceCategoryImpl extends HibernateDaoSupport implements MPriceCategoryDao{

    @Override
    public List<MPricecategory> getListPrice(MPricecategory Price, int option) {
       String query ="from MPricecategory p where ";
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
       if((Price.getCode() != null) &&(!"".equalsIgnoreCase(Price.getCode()))){
           query += " p.code "+queryOperation+" '"+Prefix_Subfix+Price.getCode()+Prefix_Subfix+"'";
           check =1;
       }
       if((Price.getName()!= null) &&(!"".equalsIgnoreCase(Price.getName()))){
           if(check == 1){query += " and ";}
           query += " p.name "+queryOperation+" '"+Prefix_Subfix+Price.getName()+Prefix_Subfix+"'";
           check =1;
       }
       if(check == 0){
           query = query.replaceAll("where", " ");
       }
       System.out.println("query : "+query);
       List<MPricecategory> list = getHibernateTemplate().find(query);
       if(list.isEmpty()){
           return null;
       }
       return list;  
    }

    @Override
    public int insertPricecategory(MPricecategory Price) {
        int result =0;
        try{
            getHibernateTemplate().save(Price);
            result = 1;
        }catch(Exception e){
            result = 0;
        }
        
        return result;
    }

    @Override
    public int updatePricecategory(MPricecategory Price) {
        int result =0;
        try{
            getHibernateTemplate().update(Price);
            result = 1;
        }catch(Exception e){
            result = 0;
        }
        
        return result;
    }

    @Override
    public int DeletePricecategory(MPricecategory Price) {
        int result =0;
        try{
            getHibernateTemplate().delete(Price);
            result = 1;
        }catch(Exception e){
            result = 0;
        }
        
        return result;
    }
    
}
