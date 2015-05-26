/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.MTicketTypeDao;
import com.smi.travel.datalayer.entity.MTicketType;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Surachai
 */
public class MTicketTypeImpl extends HibernateDaoSupport implements MTicketTypeDao{

    @Override
    public List<MTicketType> getListTicketType(MTicketType tickettype,int option) {
        String query = "from MTicketType f where ";
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
        
        if ((tickettype.getCode() != null) && (!"".equalsIgnoreCase(tickettype.getCode()))) {
            query += " f.code "+queryOperation+" '"+Prefix_Subfix+tickettype.getCode()+Prefix_Subfix+"'";
            check = 1;
        }
        if ((tickettype.getName() != null) && (!"".equalsIgnoreCase(tickettype.getName()))) {
            if (check == 1) {query += " and ";}
            query += " f.name "+queryOperation+" '"+Prefix_Subfix+tickettype.getName()+Prefix_Subfix+"'";
            check = 1;
        }
        if (check == 0) {
            query = query.replaceAll("where", " ");
        }
        System.out.println("query : " + query);
        List<MTicketType> list = getHibernateTemplate().find(query);
        if (list.isEmpty()){
            return null;
        }
        return list;
    }

    @Override
    public int insertTicketType(MTicketType tickettype) {
        int result = 0;
        try{
            getHibernateTemplate().save(tickettype);
            result = 1;
        }catch(Exception e){
            result = 0;
        }
        return result;
    }

    @Override
    public int updateTicketType(MTicketType tickettype) {
        int result = 0;
        try{
            getHibernateTemplate().update(tickettype);
            result = 1;
        }catch(Exception e){
            result = 0;
        }
        return result;
    }

    @Override
    public int DeleteTicketType(MTicketType tickettype) {
        int result = 0;
        try{
            getHibernateTemplate().delete(tickettype);
            result = 1;
        }catch(Exception e){
            result = 0;
        }
        return result;
    }
    
}
