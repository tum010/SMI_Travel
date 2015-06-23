/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.MBankDao;
import com.smi.travel.datalayer.entity.MBank;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Surachai
 */
public class MBankImpl  implements MBankDao{
    
    private SessionFactory sessionFactory;
    private Transaction transaction;
    @Override 
    public List<MBank> getListBank(MBank bank, int option) {
       Session session = this.sessionFactory.openSession();
       String query ="from MBank b where ";
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
       if((bank.getCode() != null) &&(!"".equalsIgnoreCase(bank.getCode()))){
           query += " b.code "+queryOperation+" '"+Prefix_Subfix+bank.getCode()+Prefix_Subfix+"'";
           check =1;
       }
       if((bank.getName()!= null) &&(!"".equalsIgnoreCase(bank.getName()))){
           if(check == 1){query += " and ";}
           query += " b.name "+queryOperation+" '"+Prefix_Subfix+bank.getName()+Prefix_Subfix+"'";
           check =1;
       }
       if(check == 0){
           query = query.replaceAll("where", " ");
       }
       System.out.println("query : "+query );
       List<MBank> list =  session.createQuery(query).list();
       if(list.isEmpty()){
           return null;
       }
       session.close();
       this.sessionFactory.close();
       return list;  
    }

    
    @Override
    public List<MBank> getListBank() {
        String query = "from MBank bank";
        Session session = this.sessionFactory.openSession();
        List<MBank> bankList = session.createQuery(query).list();
        System.out.println("query : "+query );
        if (bankList.isEmpty()) {
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return bankList;
    }

    @Override
    public int insertBank(MBank bank) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
           
            session.save(bank);
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }
        return result;    
    }

    @Override
    public int updateBank(MBank bank) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(bank);
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }
        return result;    
    }

    @Override
    public int DeleteBank(MBank bank) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(bank);
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
            result = 0;
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
}
