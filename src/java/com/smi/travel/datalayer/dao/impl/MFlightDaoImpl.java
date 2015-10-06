/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.MFilghtDao;
import com.smi.travel.datalayer.entity.MFlight;
import com.smi.travel.datalayer.entity.MFlightservice;
import java.util.LinkedList;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Surachai
 */
public class MFlightDaoImpl extends HibernateDaoSupport implements MFilghtDao {
//    private static final String GET_MFLIGHTSERVICE = " fs.Mflight.id = :flightid";
    
    @Override
    public List<MFlight> getListFlight(MFlight filght,int option) {
        List<MFlight> listFlight = new LinkedList<MFlight>();
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
            List<MFlightservice> mflightService = filght.getmFlightservice();
            if(mflightService != null){
                for (int i = 0; i < mflightService.size(); i++) {
                    getHibernateTemplate().save(mflightService.get(i));
                }
            }
            result = 1;
        }catch(Exception e){
            result = 0;
        }
        return result;
    }

    @Override
    public int updateFlight(MFlight filght,List<String> id) {
        int result = 0;
        try{
            getHibernateTemplate().update(filght);
            List<MFlightservice> mflightService = filght.getmFlightservice();
            List<MFlightservice> mflightServiceTemp = getListFlightService(filght.getId());
            System.out.println("Size : " + mflightService.size());
            if(mflightService != null){
                if(id != null){
                    for (int i = 0; i < id.size(); i++) {
                        for (int j = 0; j < mflightServiceTemp.size(); j++) {
                            String del = id.get(i);
                            if(del.equals(mflightServiceTemp.get(j).getId())){
                                getHibernateTemplate().delete(mflightServiceTemp.get(j));
//                                mflightService.remove(j);    
                            }
                        }
                    }
                }
                for (int i = 0; i < mflightService.size(); i++) {
                    if(mflightService.get(i).getId() != null && !"".equals(mflightService.get(i).getId())){
                        System.out.println("id : " + mflightService.get(i).getId());
                        getHibernateTemplate().update(mflightService.get(i));
                    }else{
                        getHibernateTemplate().save(mflightService.get(i));
                    }
                    
                    
                }
            }
            result = 1;
        }catch(Exception e){
            e.printStackTrace();
            result = 0;
        }
        return result;
    }

    @Override
    public int DeleteFlight(MFlight filght) {
        int result = 0;
        try{
           
            List<MFlightservice> mflightService = getListFlightService(filght.getId());
            if(mflightService != null){
                for (int i = 0; i < mflightService.size(); i++) {
                    getHibernateTemplate().delete(mflightService.get(i));
                }
            }
             getHibernateTemplate().delete(filght);
            result = 1;
        }catch(Exception e){
            result = 0;
        }
        return result;
    }

    @Override
    public List<MFlightservice> getListFlightService(String mFlightId) {
//        String query = "from MFlightservice fs  ";
        String query = "from MFlightservice fs where ";
        if ((mFlightId != null) && (!"".equalsIgnoreCase(mFlightId))) {
            query += " fs.MFlight.id = '"+mFlightId +"'";
        }
        
        System.out.println("query : " + query);
        List<MFlightservice> mFlightserviceList = getHibernateTemplate().find(query);
        System.out.println("Size Flight Service : " + mFlightserviceList.size());
        if(mFlightserviceList.isEmpty()){
            
            for (int i = 1; i <= mFlightserviceList.size() ; i++) {
                System.out.println("Flight code " + i+" : " + mFlightserviceList.get(i).getClassCode());
                MFlightservice mFlightservice = new MFlightservice();
                mFlightservice.setId(mFlightserviceList.get(i).getId());
                mFlightservice.setClassCode(mFlightserviceList.get(i).getClassCode());
                mFlightservice.setClassName(mFlightserviceList.get(i).getClassName());
                mFlightserviceList.add(mFlightservice);
            }       
            return null;
        }       
        return mFlightserviceList;
    }
}
