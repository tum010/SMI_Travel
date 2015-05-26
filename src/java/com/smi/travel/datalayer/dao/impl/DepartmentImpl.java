/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;
import com.smi.travel.datalayer.dao.DepartmentDao;
import com.smi.travel.datalayer.entity.MDepartment;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Surachai
 */
public class DepartmentImpl   implements DepartmentDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;
    private static final String departmentQuery = "from MDepartment d" ;
    private static final String departmentQueryById = "from MDepartment d where d.id=:department" ;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<MDepartment> getDepartment() {
        Session session = this.sessionFactory.openSession();
        List<MDepartment> list = session.createQuery(departmentQuery).list();
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public MDepartment getDepartmentFromId(String id) {
        
        Session session = this.sessionFactory.openSession();
        List<MDepartment> list = session.createQuery(departmentQueryById).setParameter("department", id).list();
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }


    
}
