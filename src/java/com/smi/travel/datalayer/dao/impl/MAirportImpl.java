/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.MAirportDao;
import com.smi.travel.datalayer.entity.MAirport;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Surachai
 */
public class MAirportImpl extends HibernateDaoSupport implements MAirportDao {

    @Override
    public List<MAirport> getListAirport(MAirport Airport, int option) {
        String query = "from MAirport a where ";
        String queryOperation = "";
        String Prefix_Subfix = "";
        int check = 0;
        if (option == 1) {
            queryOperation = " = ";
            Prefix_Subfix = "";
        } else if (option == 2) {
            queryOperation = " Like ";
            Prefix_Subfix = "%";
        }
        if ((Airport.getCode() != null) && (!"".equalsIgnoreCase(Airport.getCode()))) {
            query += " a.code " + queryOperation + " '" + Prefix_Subfix + Airport.getCode() + Prefix_Subfix + "'";
            check = 1;
        }
        if ((Airport.getName() != null) && (!"".equalsIgnoreCase(Airport.getName()))) {
            if (check == 1) {
                query += " and ";
            }
            query += " a.name " + queryOperation + " '" + Prefix_Subfix + Airport.getName() + Prefix_Subfix + "'";
            check = 1;
        }
        if (check == 0) {
            query = query.replaceAll("where", " ");
        }
        System.out.println("query : " + query);
        query = query +" order by a.name";
        getHibernateTemplate().setMaxResults(1000);
        List<MAirport> list = getHibernateTemplate().find(query);
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public int insertAirport(MAirport Airport) {
        int result = 0;
        try {
            getHibernateTemplate().save(Airport);
            result = 1;
        } catch (Exception e) {
            result = 0;
        }

        return result;
    }

    @Override
    public int updateAirport(MAirport Airport) {
        int result = 0;
        try {
            getHibernateTemplate().update(Airport);
            result = 1;
        } catch (Exception e) {
            result = 0;
        }

        return result;
    }

    @Override
    public int DeleteAirport(MAirport Airport) {
        int result = 0;
        try {
            getHibernateTemplate().delete(Airport);
            result = 1;
        } catch (Exception e) {
            result = 0;
        }

        return result;
    }

    @Override
    public String getAirportName(String AirportCode) {
        String query = "from MAirport a where a.code = '" + AirportCode + "'";
        String result = "";
        try {
            List<MAirport> list = getHibernateTemplate().find(query);
            if (list.isEmpty()) {
                return "";
            }
            result = list.get(0).getName();
        } catch (Exception ex) {
            ex.printStackTrace();
            result = "";
        }
        return result;
    }

    @Override
    public List<MAirport> searchAirport(String Airportname) {
        String query = "from MAirport a  ";
        if ((Airportname != null) && (!"".equalsIgnoreCase(Airportname))) {
            query += "where a.code Like '%"+Airportname+"%' and a.name Like '%"+Airportname+"%'"; 
        }    
        System.out.println("query : " + query);
        query = query +" order by a.name";
        List<MAirport> list = getHibernateTemplate().find(query); 
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

}
