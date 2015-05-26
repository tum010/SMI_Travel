/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.MProductTypeDao;
import com.smi.travel.datalayer.entity.MProductType;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Thitikul
 */
public class MProductTypeImpl extends HibernateDaoSupport implements MProductTypeDao{
    
    @Override
    public List<MProductType> getListProductType(MProductType productType ,int option) {
       String query ="from MProductType c where ";
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
//       if((productType.getId()!= null) &&(!"".equalsIgnoreCase(productType.getId()))){
//           query += " c.id "+queryOperation+" '"+Prefix_Subfix+productType.getId()+Prefix_Subfix+"'";
//           check =1;
//       }
       if((productType.getName()!= null) &&(!"".equalsIgnoreCase(productType.getName()))){
           if(check == 1){query += " and ";}
           query += " c.name "+queryOperation+" '"+Prefix_Subfix+productType.getName()+Prefix_Subfix+"'";
           check =1;
       }
       if(check == 0){
           query = query.replaceAll("where", " ");
       }
       System.out.println("query : "+query );
       List<MProductType> list = getHibernateTemplate().find(query);
       if(list.isEmpty()){
           return null;
       }
       System.out.println("rows : "+list.size() );
       return list;  
    }

    @Override
    public int insertProductType(MProductType productType) {
        int result =0;
        try{
            getHibernateTemplate().save(productType);
            result = 1;
        }catch(Exception e){
            result = 0;
        }
        
        return result;
    }

    @Override
    public int updateProductType(MProductType productType) {
        int result =0;
        try{
            getHibernateTemplate().update(productType);
            result = 1;
        }catch(Exception e){
            result = 0;
        }
        return result;
    }

    @Override
    public int DeleteProductType(MProductType productType) {
        int result =0;
        try{
            getHibernateTemplate().delete(productType);
            result = 1;
        }catch(Exception e){
            result = 0;
        }
        return result;
    }

    @Override
    public List<MProductType> getListProductType() {
         String query = "from MProductType c ORDER BY c.name";
        List<MProductType> list = getHibernateTemplate().find(query);
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }
    
}
