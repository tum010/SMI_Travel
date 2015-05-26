/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.MFilghtDao;
import com.smi.travel.datalayer.entity.MFlight;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Surachai
 */
public class MFlightDaoImpl extends HibernateDaoSupport implements MFilghtDao {

    @Override
    public List<MFlight> getListFlight(MFlight filght,int option) {
        String query = "from MFlight f where ";
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
        if ((filght.getCode() != null) && (!"".equalsIgnoreCase(filght.getCode()))) {
            query += " f.code "+queryOperation+" '"+Prefix_Subfix+filght.getCode()+Prefix_Subfix+"'";
            check = 1;
        }
        if ((filght.getName() != null) && (!"".equalsIgnoreCase(filght.getName()))) {
            if (check == 1) {query += " and ";}
            query += " f.name "+queryOperation+" '"+Prefix_Subfix+filght.getName()+Prefix_Subfix+"'";
            check = 1;
        }
        if (check == 0) {
            query = query.replaceAll("where", " ");
        }
        System.out.println("query : " + query);
        List<MFlight> list = getHibernateTemplate().find(query);
        if (list.isEmpty()){
            return null;
        }
        return list;
    }

    @Override
    public int insertFlight(MFlight filght) {
        int result = 0;
        try{
            getHibernateTemplate().save(filght);
            result = 1;
        }catch(Exception e){
            result = 0;
        }
        return result;
    }

    @Override
    public int updateFlight(MFlight filght) {
        int result = 0;
        try{
            getHibernateTemplate().update(filght);
            result = 1;
        }catch(Exception e){
            result = 0;
        }
        return result;
    }

    @Override
    public int DeleteFlight(MFlight filght) {
        int result = 0;
        try{
            getHibernateTemplate().delete(filght);
            result = 1;
        }catch(Exception e){
            result = 0;
        }
        return result;
    }

}
