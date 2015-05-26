/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;
import com.smi.travel.datalayer.dao.RoleMappingDao;
import com.smi.travel.datalayer.entity.Function;
import com.smi.travel.datalayer.entity.Role;
import com.smi.travel.datalayer.entity.RoleMapping;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
/**
 *
 * @author Surachai
 */
public class RoleMappingImpl implements RoleMappingDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;
    private static final String DeleteMap = "DELETE  from RoleMapping r where r.role.id=:roleid ";
    private static final String SearchRolePrivilege = " from RoleMapping r where r.role.id=:roleid ";
    @Override
    public int insertRolePrivilege(RoleMapping RoleMap,List<Function> functionList) {
       int result =0;  
        try{
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query  = session.createQuery(DeleteMap); 
            query.setString("roleid", RoleMap.getRole().getId());
            query.executeUpdate();
            for(Function f :functionList){
                RoleMap.setFunction(f);
                session.merge(RoleMap);
            }
            transaction.commit();
            session.close();
            result =1;
        }catch(Exception ex){
            ex.printStackTrace();
            transaction.rollback();
            result =0;    
        }
        return result;
    }
    
    @Override
    public List<Function> getRolePrivilege(String RoleID) {
        Session session = this.sessionFactory.openSession();
        List<Function> FunctionList = new LinkedList<Function>();
        List<RoleMapping> RoleMappingList = session.createQuery(SearchRolePrivilege).setParameter("roleid", RoleID).list();
        if((RoleMappingList != null) && (!RoleMappingList.isEmpty())){
            for(int i=0;i<RoleMappingList.size();i++){
                FunctionList.add(RoleMappingList.get(i).getFunction());
            }
        }
        return FunctionList; 
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int DeleteRolePrivilege(Role role) {
        int result =0;  
        try{
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query  = session.createQuery(DeleteMap); 
            query.setString("roleid",role.getId());
            query.executeUpdate();
            transaction.commit();
            session.close();
            result =1;
        }catch(Exception ex){
            ex.printStackTrace();
            transaction.rollback();
            result =0;    
        }
        return result;
    }



    
    
    
}
