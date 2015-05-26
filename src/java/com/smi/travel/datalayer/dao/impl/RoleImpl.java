/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;
import com.smi.travel.datalayer.dao.RoleDao;
import com.smi.travel.datalayer.entity.Role;
import com.smi.travel.datalayer.entity.RoleMapping;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Surachai
 */
public class RoleImpl   implements RoleDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;
    private static final String roleQuery = "from Role r" ;
    private static final String UsabilityRole = "from Role r where r.id=:role" ;

    @Override
    public List<Role> getRole() {
       
        Session session = this.sessionFactory.openSession();
        List<Role> list = session.createQuery(roleQuery).list();
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }
    
    @Override
    public List<Role> SerchRole(String rolename) {
        String query ="from Role r";
        Session session = this.sessionFactory.openSession();
        if ((rolename != null) && (!"".equalsIgnoreCase(rolename))) {
            query += " where r.name Like '%"+rolename+"%'";
            
        }
        List<Role> list = session.createQuery(query).list();
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }
    
    public Role getRoleFromID(String roleid){
        Session session = this.sessionFactory.openSession();
        List<Role> list = session.createQuery(UsabilityRole).setParameter("role", roleid).list();
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public int insertRole(Role role) {
        int result =0;  
        try{
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
            session.close();
            //getHibernateTemplate().save(role);
            result =1;
        }catch(Exception ex){
            result =0;    
        }
        return result;
    }

    @Override
    public int updateRole(Role role) {
        int result =0;
        try{
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(role);
            transaction.commit();
            session.close();
            //getHibernateTemplate().update(role);
            result =1;
        }catch(Exception ex){
            result =0;
        }
        return result;
    }

    @Override
    public int DeleteRole(Role role) {
        int result =0;
        try{
                Session session = this.sessionFactory.openSession();
                transaction = session.beginTransaction();
                session.delete(role);
                transaction.commit();
                session.close();
                //getHibernateTemplate().delete(role);
                result =1; 
        }catch(Exception ex){
            result =0;
        }
        return result;
    }

    @Override
    public boolean CheckUsabilityRole(Role role) {
        Session session = this.sessionFactory.openSession();
        List<Role> list = session.createQuery(UsabilityRole).setParameter("role", role.getId()).list();
        if (list.isEmpty()) {
                return false;
        }else{
            if(list.get(0).getStaffs().size() == 0){
                return false;
            }else{
                return true;
            }
        }

    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    
}
