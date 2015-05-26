/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.MAirlineDao;
import com.smi.travel.datalayer.entity.MAirline;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Surachai
 */
public class MAirlineImpl extends HibernateDaoSupport implements MAirlineDao{

    @Override
    public List<MAirline> getListAirLine(MAirline airline,int option) {
       
       String query ="from MAirline m where ";
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
       if((airline.getCode() != null) &&(!"".equalsIgnoreCase(airline.getCode()))){
           query += " m.code "+queryOperation+" '"+Prefix_Subfix+airline.getCode()+Prefix_Subfix+"'";
           check =1;
       }
       if((airline.getName()!= null) &&(!"".equalsIgnoreCase(airline.getName()))){
           if(check == 1){query += " and ";}
           query += " m.name "+queryOperation+" '"+Prefix_Subfix+airline.getName()+Prefix_Subfix+"'";
           check =1;
       }
       if((airline.getCode3Letter()!= null) &&(!"".equalsIgnoreCase(airline.getCode3Letter()))){
           if(check == 1){query += " and ";}
           query += " m.code3Letter "+queryOperation+" '"+Prefix_Subfix+airline.getCode3Letter()+Prefix_Subfix+"'";
           check =1;
       }
       if(check == 0){
           query = query.replaceAll("where", " ");
       }
       System.out.println("query : "+query);
       query = query +" order by m.name";
       List<MAirline> list = getHibernateTemplate().find(query);
       
       if(list.isEmpty()){
           return null;
       }
       return list;  
    }

    @Override
    public int insertAirLine(MAirline airline) {
        int result =0;
        try{
            getHibernateTemplate().save(airline);
            result = 1;
        }catch(Exception ex){
            result =0;
        }
        return result;
    }

    @Override
    public int updateAirLine(MAirline airline) {
        int result =0;
        try{
            getHibernateTemplate().update(airline);
            result = 1;
        }catch(Exception ex){
            result =0;
        }
        return result;
    }

    @Override
    public int DeleteAirLine(MAirline airline) {
        int result =0;
        try{
            getHibernateTemplate().delete(airline);
            result = 1;
        }catch(Exception ex){
            result =0;
        }
        return result;
    }
    
}
