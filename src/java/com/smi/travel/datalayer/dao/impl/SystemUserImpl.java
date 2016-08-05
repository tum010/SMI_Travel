/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.SystemUserDao;
import com.smi.travel.datalayer.entity.SystemUser;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Surachai
 */
public class SystemUserImpl implements SystemUserDao {

    private SessionFactory sessionFactory;
    private Transaction transaction;
    private static final String authenquery = "select u from SystemUser u   WHERE (u.username=:user) and (u.password=:pass) ";
    private static final String GUIDEQUERY = "select u from SystemUser u   WHERE u.position= 'GUIDE' and u.status = 'active' ";
    private static final String DRIVERQUERY = "select u from SystemUser u   WHERE u.position= 'DRIVER' and u.status = 'active' ";
    private static final String STAFFQUERY = "select u from SystemUser u   WHERE  u.status = 'active'";
    
    @Override
    public SystemUser getSystemUser(SystemUser user) {
        SystemUser myUser = new SystemUser();
        Session session = this.sessionFactory.openSession();
        List<SystemUser> list = session.createQuery(authenquery).setParameter("user", user.getUsername()).setParameter("pass", user.getPassword()).list();
        // List<SystemUser> list = getHibernateTemplate().find(, user.getUsername(), user.getPassword());
        if (list.isEmpty()) {
            return null;
        }
        myUser = list.get(0);
        return myUser;
    }

    @Override
    public int insertSystemUser(SystemUser user) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            session.close();
            // getHibernateTemplate().save(place);
            result = 1;
            result = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }
        return result;
    }

    @Override
    public int updateSystemUser(SystemUser user) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            session.close();
            //getHibernateTemplate().update(user);
            result = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }
        return result;

    }

    @Override
    public List<SystemUser> searchSystemUser(SystemUser user,int option) {
        String query = "from SystemUser u where ";
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

        if ((user.getName() != null) && (!"".equalsIgnoreCase(user.getName()))) {
            query += " u.name "+queryOperation+" '"+Prefix_Subfix + user.getName() + Prefix_Subfix+"'";
            check = 1;
        }
        
        if ((user.getUsername()!= null) && (!"".equalsIgnoreCase(user.getUsername()))) {
            if (check == 1) {query += " and ";}
            query += " u.username "+queryOperation+" '"+Prefix_Subfix + user.getUsername() + Prefix_Subfix+"'";
            check = 1;
        }
        if ((user.getStatus() != null) && (!"".equalsIgnoreCase(user.getStatus()))) {
            if (check == 1) {query += " and ";}
            query += " u.status = '" + user.getStatus() +"'";
            check = 1;
        }
        if ((user.getPosition() != null) && (!"".equalsIgnoreCase(user.getPosition()))) {
            if (check == 1) {query += " and ";}
            query += " u.position "+queryOperation+" '"+Prefix_Subfix + user.getPosition() + Prefix_Subfix+"'";
            check = 1;
        }
        if ((user.getRole() != null)) {
            if (check == 1) {query += " and ";}
            query += " u.role "+queryOperation+" '"+Prefix_Subfix + user.getRole().getId() + Prefix_Subfix+"'";
            check = 1;
        }
        if ((user.getMDepartment() != null)) {
            if (check == 1) {query += " and ";}
            query += " u.MDepartment.id "+queryOperation+" '"+Prefix_Subfix + user.getMDepartment().getId() + Prefix_Subfix+"'";
            check = 1;
        }
        
        
        if (check == 0) {
            query = query.replaceAll("where", " ");
        }
        System.out.println("query : " + query + "order by u.name");
        query = query + " order by u.name";
      //  List<SystemUser> list = getHibernateTemplate().find(query);
        Session session = this.sessionFactory.openSession();
        List<SystemUser> list = session.createQuery(query).list();
       
        if (list.isEmpty()) {
            return null;
        }else{
            if(list.get(0).getRole() != null)
            System.out.println(list.get(0).getRole().getName());
        }
        return list;

    }
    
    @Override
    public List<SystemUser> getGuildeList() {
        Session session = this.sessionFactory.openSession();
        List<SystemUser> list = session.createQuery(GUIDEQUERY).list();
        if (list.isEmpty()) {
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return list;
    }

    @Override
    public List<SystemUser> getDriverList() {
        Session session = this.sessionFactory.openSession();
        List<SystemUser> list = session.createQuery(DRIVERQUERY).list();
        if (list.isEmpty()) {
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return list;
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
    public List<SystemUser> getUserList() {
       Session session = this.sessionFactory.openSession();
        List<SystemUser> list = session.createQuery(STAFFQUERY).list();
        if (list.isEmpty()) {
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return list;
    }




    
    
}
