/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.CheckDuplicateUserDao;
import com.smi.travel.datalayer.view.entity.CheckDuplicateUser;
import com.smi.travel.util.UtilityFunction;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jittima
 */
public class CheckDuplicateUserImpl implements CheckDuplicateUserDao {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private SessionFactory sessionFactory;
    private Transaction transaction;
    private String ExpireTime;
    private String opUser;
    private Date opDate;
    private String tableId;
    private String tableName;
    
    @Override
    public CheckDuplicateUser CheckAndUpdateOperationDetail(CheckDuplicateUser checkDuplicateUser,int step) {
        logger.info("============= Check Duplicate User ==============");
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("yyyy-MM-dd HH:mm:ss");
        CheckDuplicateUser cdu = new CheckDuplicateUser();
        UtilityFunction util = new UtilityFunction();
        Session session = this.sessionFactory.openSession();
        String query = "select * from "+checkDuplicateUser.getOperationTable().toLowerCase()+" t where t.id = '"+checkDuplicateUser.getTableId()+"' ";
        List<Object[]> QueryList =  session.createSQLQuery(query)
                .addScalar("operation_date",Hibernate.STRING)
                .addScalar("operation_user",Hibernate.STRING)
                .list();
        
        if(QueryList.isEmpty()){
            return null;
        }else{
            if(step == 1) { //when search or edit action 
                logger.info("================ Search OR Edit =================");
                for (Object[] B : QueryList) {
                    cdu.setTableId(checkDuplicateUser.getTableId());
                    cdu.setOperationTable(checkDuplicateUser.getOperationTable());
                    logger.info("Table Id :: "+checkDuplicateUser.getTableId());
                    logger.info("Operation Table :: "+checkDuplicateUser.getOperationTable());
                    logger.info("Operation User :: "+checkDuplicateUser.getOperationUser());
                    logger.info("Operation Time :: "+checkDuplicateUser.getOperationDate());
                    System.out.println(" B[0] " + B[0]);
                    System.out.println(" B[1] " + B[1]);
                    if(B[1] != null && B[0] != null){
                        if((checkDuplicateUser.getOperationUser()).equalsIgnoreCase(util.ConvertString(B[1]))){
                            logger.info(" Not duplicate ");
                            cdu.setIsDuplicateUser(0);
                            cdu.setOperationDate(String.valueOf(df.format(new Date())));
                            int result = updateDateAndUser(checkDuplicateUser.getOperationTable(),checkDuplicateUser.getTableId(),checkDuplicateUser.getOperationUser(),String.valueOf(df.format(new Date())));
                        }else{
                            logger.info(" Duplicate : User " + util.ConvertString(B[1]) + " is using this information ");
                            cdu.setIsDuplicateUser(1);
                            cdu.setOperationDate(String.valueOf(B[0]));
                            System.out.println(" cdu.getOperationDate() " + cdu.getOperationDate());
                        }
                        cdu.setOperationUser(util.ConvertString(B[1]));
                    }else{
                        logger.info(" Not duplicate ");
                        cdu.setIsDuplicateUser(0);
                        cdu.setOperationDate(checkDuplicateUser.getOperationDate());
                        cdu.setOperationUser(checkDuplicateUser.getOperationUser());
                        int result = updateDateAndUser(checkDuplicateUser.getOperationTable(),checkDuplicateUser.getTableId(),checkDuplicateUser.getOperationUser(),checkDuplicateUser.getOperationDate());
                    }
                }
            }else if(step == 2){
                logger.info("=================== Save ========================");
//                for (Object[] B : QueryList) {
//                    cdu.setTableId(checkDuplicateUser.getTableId());
//                    cdu.setOperationTable(checkDuplicateUser.getOperationTable());
//                    cdu.setOperationDate(util.convertStringToDateTime(String.valueOf(B[0])));
//                    cdu.setOperationUser(util.ConvertString(B[1]));
//                    System.out.println("data 1 : "+(checkDuplicateUser.getOperationDate()));
//                    System.out.println("data 2 : "+util.ConvertString(B[0]));
//                    if((checkDuplicateUser.getOperationUser()).equalsIgnoreCase(util.ConvertString(B[1])) 
//                        ){
//                        logger.info(" Not duplicate ");
//                        cdu.setIsSave(0);
//                        cdu.setIsDuplicateUser(0);
//                    }else{
//                        logger.info(" Duplicate : User " + util.ConvertString(B[1]) + " is using this information ");
//                        cdu.setIsSave(1); // not save
//                        cdu.setIsDuplicateUser(1);
//                    }
//                }
            }else if (step == 3) { // update new user when click ok
                logger.info("================ Update New User =================");
                int result = updateDateAndUser(checkDuplicateUser.getOperationTable(),checkDuplicateUser.getTableId(),checkDuplicateUser.getOperationUser(),String.valueOf(df.format(new Date())));
            }
        }
        session.close();
        this.sessionFactory.close();
        return cdu;
    }

    @Override
    public int updateOperationNull(CheckDuplicateUser checkDuplicateUser) {
        logger.info("================= Update Null User ===================");
        
        Session session = this.sessionFactory.openSession();
        int result = 0;
        String hql = "update "+checkDuplicateUser.getOperationTable()+" t set t.operationDate = :operationDate , t.operationUser = :operationUser where t.id = :id";
        try {
            logger.info(" Table :: " + checkDuplicateUser.getOperationTable());
            logger.info(" Table id :: " + checkDuplicateUser.getTableId());
            Query queryupdate = session.createQuery(hql);
            queryupdate.setParameter("operationDate", null);
            queryupdate.setParameter("operationUser", null);
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
    
    
    public int updateDateAndUser(String table,String tableid,String username , String date){
        logger.info("================= Update User ===================");
        UtilityFunction util = new UtilityFunction();
        Session session = this.sessionFactory.openSession();
        int result = 0;
        String hql = "update "+table+" t set t.operationDate = :operationDate , t.operationUser = :operationUser where t.id = :id";
        System.out.println(" hql : " + hql);
        try {
            logger.info(" Table :: " + table);
            logger.info(" Table id :: " + tableid);
            logger.info(" Username :: " + username);
            logger.info(" Date :: " + date);
            Query queryupdate = session.createQuery(hql);
            queryupdate.setParameter("operationDate", new Date());
            queryupdate.setParameter("operationUser", username);
            queryupdate.setParameter("id", tableid);
            System.out.println(" queryupdate : " + queryupdate);
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

    public String getExpireTime() {
        return ExpireTime;
    }

    public void setExpireTime(String ExpireTime) {
        this.ExpireTime = ExpireTime;
    }
    
}
