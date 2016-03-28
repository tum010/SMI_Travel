/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.CheckDuplicateUserDao;
import com.smi.travel.datalayer.entity.Invoice;
import com.smi.travel.datalayer.view.entity.CheckDuplicateUser;
import com.smi.travel.util.UtilityFunction;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Jittima
 */
public class CheckDuplicateUserImpl implements CheckDuplicateUserDao {
    private SessionFactory sessionFactory;
    private Transaction transaction;
    
    @Override
    public CheckDuplicateUser CheckAndUpdateOperationDetail(CheckDuplicateUser checkDuplicateUser,int step) {
        CheckDuplicateUser cdu = new CheckDuplicateUser();
        UtilityFunction util = new UtilityFunction();
        Session session = this.sessionFactory.openSession();
        String query = "select * from "+checkDuplicateUser.getOperationTable()+" t where t.id = '"+checkDuplicateUser.getTableId()+"' ";
        List<Object[]> QueryList =  session.createSQLQuery(query)
                .addScalar("operation_date",Hibernate.DATE)
                .addScalar("operation_user",Hibernate.STRING)
                .list();
        
        if(QueryList.isEmpty()){
            return null;
        }else{
            if(step == 1) { //when search or edit action 
                for (Object[] B : QueryList) {
                    cdu.setTableId(checkDuplicateUser.getTableId());
                    cdu.setOperationTable(checkDuplicateUser.getOperationTable());
                    if(B[1] != null && B[0] != null){
                        cdu.setIsDuplicateUser(1);
                        cdu.setOperationDate(util.convertStringToDateTime(String.valueOf(B[0])));
                        cdu.setOperationUser(util.ConvertString(B[1]));
                    }else{
                        cdu.setIsDuplicateUser(0);
                        cdu.setOperationDate(checkDuplicateUser.getOperationDate());
                        cdu.setOperationUser(checkDuplicateUser.getOperationUser());
                        int result = 0;
                        String hql = "update "+checkDuplicateUser.getOperationTable()+" t set t.operationDate = :operationDate , t.operationUser = :operationUser where t.id = :id";
                        try {
                            Query queryupdate = session.createQuery(hql);
                            queryupdate.setParameter("operationDate", checkDuplicateUser.getOperationDate());
                            queryupdate.setParameter("operationUser", checkDuplicateUser.getOperationUser());
                            queryupdate.setParameter("id", checkDuplicateUser.getTableId());
                            System.out.println(" queryupdate : " + queryupdate);
                            result = queryupdate.executeUpdate();
                            System.out.println("Rows affected: " + result);

                        } catch (Exception ex) {
                            ex.printStackTrace();
                            result = 0;
                        }
                    }
                }
            }else if(step == 2){
                for (Object[] B : QueryList) {
                    cdu.setTableId(checkDuplicateUser.getTableId());
                    cdu.setOperationTable(checkDuplicateUser.getOperationTable());
                    cdu.setOperationDate(util.convertStringToDateTime(String.valueOf(B[0])));
                    cdu.setOperationUser(util.ConvertString(B[1]));
                    if((checkDuplicateUser.getOperationUser()).equalsIgnoreCase(util.ConvertString(B[1])) 
                        && (String.valueOf(checkDuplicateUser.getOperationDate())).equalsIgnoreCase(util.ConvertString(B[0]))){
                        cdu.setIsSave(0);
                    }else{
                        cdu.setIsSave(1); // not save
                    }
                }
            }
        } 
        session.close();
        this.sessionFactory.close();
        return cdu;
    }

    @Override
    public int updateOperationNull(CheckDuplicateUser checkDuplicateUser) {
        Session session = this.sessionFactory.openSession();
        int result = 0;
        String hql = "update "+checkDuplicateUser.getOperationTable()+" t set t.operationDate = :operationDate , t.operationUser = :operationUser where t.id = :id";
        try {
            Query queryupdate = session.createQuery(hql);
            queryupdate.setParameter("operationDate", checkDuplicateUser.getOperationDate());
            queryupdate.setParameter("operationUser", checkDuplicateUser.getOperationUser());
            queryupdate.setParameter("id", checkDuplicateUser.getTableId());
            System.out.println(" query : " + queryupdate);
            result = queryupdate.executeUpdate();
            System.out.println("Rows affected: " + result);
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }
        session.close();
        this.sessionFactory.close();
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
    
}
