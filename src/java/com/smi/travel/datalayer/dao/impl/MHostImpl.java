/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.MHostDao;
import com.smi.travel.datalayer.entity.MHost;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Kanokporn
 */
public class MHostImpl implements MHostDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;
    private static final String SEARCH_HOST = "FROM MHost host where host.code =:hostCode and host.name =:hostName";
    private static final String SEARCH_HOST_CODE = "FROM MHost host where host.code =:hostCode ";
    private static final String SEARCH_HOST_NAME = "FROM MHost host where host.name =:hostName";
    private static final String SEARCH_HOST_All = "FROM MHost host ";
    private static final String DELETE_HOST ="DELETE FROM MHost host where host.id = :hostID";
    
    @Override
    public List<MHost> getListHost(MHost hos) {
        Session session = this.sessionFactory.openSession();
        List<MHost> hostt = new LinkedList<MHost>();
        List<MHost> hostList = new LinkedList<MHost>();
        if(!"".equals(hos.getCode()) && !"".equals(hos.getName())){
            hostList = session.createQuery(SEARCH_HOST)
                    .setParameter("hostCode", hos.getCode())
                    .setParameter("hostName", hos.getName())
                    .list();
            System.out.println("1");
        }else if(!"".equals(hos.getCode()) || !"".equals(hos.getName())){
            if("".equals(hos.getCode())){
                hostList = session.createQuery(SEARCH_HOST_NAME)
                    .setParameter("hostName", hos.getName())
                    .list();
                System.out.println("2");
            }else if("".equals(hos.getName())){
                hostList = session.createQuery(SEARCH_HOST_CODE)
                    .setParameter("hostCode", hos.getCode())
                    .list();
                System.out.println("3");
            }
        }if("".equals(hos.getCode()) && "".equals(hos.getName())){
            hostList = session.createQuery(SEARCH_HOST_All)
                    .list();
            System.out.println("4");
        }
        System.out.println("Size Host : " + hostList.size());
        if(!hostList.isEmpty()){
            for (int i = 0; i < hostList.size(); i++) {
                MHost host = new MHost();
                host.setId(hostList.get(i).getId());
                host.setCode(hostList.get(i).getCode());
                host.setName(hostList.get(i).getName());
                host.setAddress(hostList.get(i).getAddress());
                host.setTel(hostList.get(i).getTel());
                host.setFax(hostList.get(i).getFax());
                hostt.add(host); 
            }
        }        
        return hostt;
    }

    @Override
    public String insertHost(MHost host) {
        String result = "";
        Session session = this.sessionFactory.openSession();
        try { 
            transaction = session.beginTransaction();
            System.out.println("ID : " + host.getId());
            session.save(host);
            result = "success";
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            System.out.println("Result: "+ result);
//            result = "success";
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            session.close();
            this.sessionFactory.close();
            result = "unsuccess";
        }
        return result;
    }
   
    @Override
    public String updateHost(MHost host) {
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            System.out.println("ID : " + host.getId());
            session.update(host);
            result = "success";
            transaction.commit();
            session.close();
            this.sessionFactory.close();
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            result = "unsuccess";
        }
        return  result;
    }

    @Override
    public String DeleteHost(MHost host) {
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            if(host != null){
                Query query = session.createQuery(DELETE_HOST);
                query.setParameter("hostID", host.getId());
                System.out.println("row delete : "+query.executeUpdate());
                transaction.commit();
                session.close();
                this.sessionFactory.close();
                result = "success";
            }else{
                result = "unsuccess";
            }          
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            result = "unsuccess";
        }
        return result;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public List<MHost> searchListHost() {
        Session session = this.sessionFactory.openSession();
        List<MHost> hostt = new LinkedList<MHost>();
        List<MHost> hostList = session.createQuery(SEARCH_HOST_All)
                .list();
        System.out.println("Size Host : " + hostList.size());
        if(!hostList.isEmpty()){
            for (int i = 0; i < hostList.size(); i++) {
                MHost host = new MHost();
                host.setId(hostList.get(i).getId());
                host.setCode(hostList.get(i).getCode());
                host.setName(hostList.get(i).getName());
                host.setAddress(hostList.get(i).getAddress());
                host.setTel(hostList.get(i).getTel());
                host.setFax(hostList.get(i).getFax());
                hostt.add(host); 
            }
        }        
        return hostt;
    }

    
}
