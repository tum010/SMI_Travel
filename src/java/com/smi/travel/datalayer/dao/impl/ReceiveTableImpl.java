/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.ReceiveTableDao;
import com.smi.travel.datalayer.entity.AdvanceReceive;
import com.smi.travel.datalayer.entity.AdvanceReceiveCredit;
import com.smi.travel.datalayer.entity.MAccpay;
import com.smi.travel.datalayer.entity.MCreditBank;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author chonnasith
 */
public class ReceiveTableImpl implements ReceiveTableDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;
    private static int MAX_ROW = 200;
    
    public static int getMAX_ROW() {
        return MAX_ROW;
    }

    public static void setMAX_ROW(int aMAX_ROW) {
        MAX_ROW = aMAX_ROW;
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
    public List<AdvanceReceive> searchAdvanceReceive(String inputDate, String selectStatus, String option) {
        StringBuffer query = new StringBuffer("from AdvanceReceive ad ");
        boolean haveCondition = false;
        if ((inputDate != null) && (!"".equalsIgnoreCase(inputDate))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" ad.recDate = '" + inputDate + "'");
            haveCondition = true;
        }
        if ((selectStatus != null) && (!"".equalsIgnoreCase(selectStatus))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" ad.vatType = '" + selectStatus + "'");
            haveCondition = true;
        }
        // option search mean order by id and option success mean order by id desc
        if ("success".equalsIgnoreCase(option)) {
            query.append(" ORDER BY ad.id DESC ");
        }
        // edit data
        if ((!"success".equalsIgnoreCase(option)) && (!"search".equalsIgnoreCase(option))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" ad.id = '" + option + "'");
        }
        
        Session session = this.sessionFactory.openSession();
        Query HqlQuery = session.createQuery(query.toString());
        System.out.println(HqlQuery.toString());
        HqlQuery.setMaxResults(MAX_ROW);
        List<AdvanceReceive> advanceReceiveList = HqlQuery.list();
        if (advanceReceiveList.isEmpty()) {
            return null;
        }
        List<AdvanceReceive> mappingAdvanceReceiveList = mappingAdvanceReceive(advanceReceiveList,option);
        this.sessionFactory.close();
        session.close();
        return mappingAdvanceReceiveList;
    }
    
    private List<AdvanceReceive> mappingAdvanceReceive(List<AdvanceReceive> advanceReceiveList, String option) {
        List<AdvanceReceive> mappingAdvanceReceiveList = new ArrayList<AdvanceReceive>();
        for(int i=0;i<advanceReceiveList.size();i++){
            AdvanceReceive advanceReceive = new AdvanceReceive();
            advanceReceive = advanceReceiveList.get(i);
            MAccpay mAccpay = new MAccpay();
            mAccpay.setId(advanceReceiveList.get(i).getMAccpay().getId());
            mAccpay.setCode(advanceReceiveList.get(i).getMAccpay().getCode());
            mAccpay.setName(advanceReceiveList.get(i).getMAccpay().getName());
            advanceReceive.setMAccpay(mAccpay);
            if ((!"success".equalsIgnoreCase(option)) && (!"search".equalsIgnoreCase(option))) {              
                List<AdvanceReceiveCredit> advanceReceiveCreditList = new ArrayList<AdvanceReceiveCredit>(); 
                advanceReceiveCreditList = advanceReceive.getAdvanceReceiveCredits();
                advanceReceive.setAdvanceReceiveCredits(new ArrayList<AdvanceReceiveCredit>());
                for(int j=0;j<advanceReceiveCreditList.size();j++){
                    AdvanceReceiveCredit advanceReceiveCredit = new AdvanceReceiveCredit();
                    AdvanceReceiveCredit map = advanceReceiveCreditList.get(j) ;
                    advanceReceiveCredit.setId(map.getId());               
                    advanceReceiveCredit.setCreditAmount(map.getCreditAmount() != null ? map.getCreditAmount() : null);
                    advanceReceiveCredit.setCreditExpire(map.getCreditExpire() != null ? map.getCreditExpire() : null);
                    advanceReceiveCredit.setCreditNo(map.getCreditNo() != null ? map.getCreditNo() : null);
                    if(map.getMCreditBank() != null){
                        MCreditBank mCreditBank = new MCreditBank();
                        mCreditBank.setId(map.getMCreditBank().getId());
                        mCreditBank.setName(map.getMCreditBank().getName());
                        advanceReceiveCredit.setMCreditBank(mCreditBank);
                    }                   
                    advanceReceiveCredit.setAdvanceReceive(advanceReceive);
                    advanceReceive.getAdvanceReceiveCredits().add(advanceReceiveCredit);
                }    
            }
            mappingAdvanceReceiveList.add(advanceReceive);
        }
        return mappingAdvanceReceiveList;
    }

    @Override
    public String deleteAdvanceReceive(AdvanceReceive advanceReceive) {
        String result = "delete fail";      
//        try {
//            Session session = this.sessionFactory.openSession();
//            transaction = session.beginTransaction();
//            AdvanceReceiveCredit  advanceReceiveCredit = new AdvanceReceiveCredit();
//            advanceReceiveCredit = (AdvanceReceiveCredit) advanceReceive.getAdvanceReceiveCredits().get(0);
//            session.delete(advanceReceiveCredit);
//            transaction.commit();          
//            session.close();
//            this.sessionFactory.close();
//            result = "delete success";
//        } catch (Exception ex) {
//            transaction.rollback();
//            this.sessionFactory.close();
//            ex.printStackTrace();
//            result = "delete fail";
//        }
        AdvanceReceiveCredit  advanceReceiveCredit = new AdvanceReceiveCredit();
        advanceReceiveCredit = (AdvanceReceiveCredit) advanceReceive.getAdvanceReceiveCredits().get(0);
        String a = deleteAdvanceReceiveCredit(advanceReceiveCredit,"parent");
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(advanceReceive);
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = "delete success";
        } catch (Exception ex) {
            transaction.rollback();
            this.sessionFactory.close();
            ex.printStackTrace();
            result = "delete fail";
        }
        return result;
    }

    @Override
    public String insertAdvanceReceive(AdvanceReceive advanceReceive) {
        String result = "fail";
        Session session = this.sessionFactory.openSession();
        try {            
            transaction = session.beginTransaction();              
            session.save(advanceReceive);    
            List<AdvanceReceiveCredit> advanceReceiveCredit = advanceReceive.getAdvanceReceiveCredits();          
            if(advanceReceiveCredit != null){
                for (int i = 0; i < advanceReceiveCredit.size(); i++) {
                    session.save(advanceReceiveCredit.get(i));
                }
            }            
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = "success";            
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            ex.printStackTrace();
            result = "fail";
        }         
        return result;    
    }

    @Override
    public String updateAdvanceReceive(AdvanceReceive advanceReceive) {
        String result = "fail";
        Session session = this.sessionFactory.openSession();
        try {            
            transaction = session.beginTransaction();
            session.update(advanceReceive);            
            List<AdvanceReceiveCredit> advanceReceiveCredit = advanceReceive.getAdvanceReceiveCredits();           
            if(advanceReceiveCredit != null){
                for (int i = 0; i < advanceReceiveCredit.size(); i++) {                   
                    if(("".equalsIgnoreCase(advanceReceiveCredit.get(i).getId())) || (advanceReceiveCredit.get(i).getId() == null)){
                        session.save(advanceReceiveCredit.get(i));
                    } else {
                        session.update(advanceReceiveCredit.get(i));
                    }                   
                }
            }           
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = "success";
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
//            this.sessionFactory.close();
            System.out.println("Fail !!!!!");
            ex.printStackTrace();
            result = "fail";
        }
        return result;    
    }

    @Override
    public String deleteAdvanceReceiveCredit(AdvanceReceiveCredit advanceReceiveCredit, String option) {
        String result = "delete fail";      
//        try {
//            Session session = this.sessionFactory.openSession();
//            transaction = session.beginTransaction();
//            session.delete(advanceReceiveCredit);
//            transaction.commit();          
//            session.close();
//            this.sessionFactory.close();
//            result = "delete success";
//        } catch (Exception ex) {
//            transaction.rollback();
//            this.sessionFactory.close();
//            ex.printStackTrace();
//            result = "delete fail";
//        }

        try {
            String sql = " delete from AdvanceReceiveCredit adrc where ";
            String id = "";
            if("child".equalsIgnoreCase(option)){
                sql += " adrc.id = :id ";
                id = advanceReceiveCredit.getId();
            } else if("parent".equalsIgnoreCase(option)){
                sql += " adrc.advanceReceive.id = :id ";
                id = advanceReceiveCredit.getAdvanceReceive().getId();
            }
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(sql);
            query.setParameter("id", id);
            System.out.println("row delete : "+query.executeUpdate());
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = "success";       
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            result = "fail";
        }
        return result;
    }
       
}
