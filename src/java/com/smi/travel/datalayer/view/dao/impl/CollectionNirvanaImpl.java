/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.view.dao.CollectionNirvanaDao;
import com.smi.travel.datalayer.view.entity.CollectionNirvana;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Jittima
 */
public class CollectionNirvanaImpl implements CollectionNirvanaDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;
    private UtilityFunction utilityFunction;
    @Override
    public List<CollectionNirvana> SearchCollectionNirvanaFromFilter(String department, String type, String status, String dateFrom, String dateTo, String invno) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        
        List<CollectionNirvana> collectionNirvanaList = new ArrayList<CollectionNirvana>();
        
        String query = "SELECT * FROM `collection_nirvana` where";
        int checkQuery = 0;
        String prefix ="";
        
        if(((dateFrom != null) &&(!"".equalsIgnoreCase(dateFrom))) &&((dateTo != null) &&(!"".equalsIgnoreCase(dateTo)))){
            query += " invdate >= '" +dateFrom +"' and invdate <= '"+dateTo +"' ";
            checkQuery = 1;
        }else if((dateFrom != null) &&(!"".equalsIgnoreCase(dateFrom))){
            checkQuery = 1;
            query +=  " invdate >= '" +dateFrom +"'";

        }else if((dateTo != null) &&(!"".equalsIgnoreCase(dateTo))){
            checkQuery = 1;
            query += " invdate <= '" +dateTo +"'";
        }

        if((department != null) &&(!"".equalsIgnoreCase(department))){
            if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
            query += prefix+" department = '"+department+"'";
        }

        if((type != null) &&(!"".equalsIgnoreCase(type))){
            if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
            query += prefix+ " type = '"+type+"'";
        }
        
        if((status != null) &&(!"".equalsIgnoreCase(status))){
            if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
            query += prefix+ " collection_status = '"+status+"'";
        }
        
        if((invno != null) &&(!"".equalsIgnoreCase(invno))){
            if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
            query += prefix+ " invno = '"+invno+"'";
        }
        
        if(checkQuery == 0){query = query.replaceAll("where", "");}
        System.out.println("query : "+query);
        
        List<Object[]> QueryList =  session.createSQLQuery(query)
                                .addScalar("invno",Hibernate.STRING)
                                .addScalar("invto",Hibernate.STRING)
                                .addScalar("arcode",Hibernate.STRING)
                                .addScalar("acccode",Hibernate.STRING)
                                .addScalar("invamount",Hibernate.BIG_DECIMAL)
                                .addScalar("recno",Hibernate.STRING)
                                .addScalar("recamount",Hibernate.BIG_DECIMAL)
                                .addScalar("cur",Hibernate.STRING)
                                .addScalar("diff",Hibernate.BIG_DECIMAL)
                                .addScalar("collection_status",Hibernate.STRING)
                                .addScalar("department",Hibernate.STRING)
                                .addScalar("type",Hibernate.STRING)
                                .addScalar("invdate",Hibernate.DATE)
                                .list();
        
        for(Object[] CN : QueryList){
            CollectionNirvana collectionNirvana = new CollectionNirvana();
            collectionNirvana.setInvno(util.ConvertString(CN[0]));
            collectionNirvana.setInvto(util.ConvertString(CN[1]));
            collectionNirvana.setArcode(util.ConvertString(CN[2]));
            collectionNirvana.setAcccode(util.ConvertString(CN[3]));
            collectionNirvana.setInvamount((BigDecimal) CN[4]);
            
            String receipt = util.ConvertString(CN[5]);
            String[] parts = receipt.split(",");
            String recno = parts[0];
            for(int i = 1; i < parts.length; i++) {
                if(!(String.valueOf(parts[i])).equalsIgnoreCase(String.valueOf(parts[i-1])) ) {
                    recno += "," +parts[i] ;
                }
            }
            collectionNirvana.setRecno(recno);
            collectionNirvana.setRecamount((BigDecimal) CN[6]);
            collectionNirvana.setCur(util.ConvertString(CN[7]));
            collectionNirvana.setDiff((BigDecimal) CN[8]);
            collectionNirvana.setCollectionStatus(util.ConvertString(CN[9]));
            collectionNirvana.setDepartment(util.ConvertString(CN[10]));
            collectionNirvana.setType(util.ConvertString(CN[11]));
            collectionNirvana.setInvdate(util.convertStringToDate(util.ConvertString(CN[12])));
            collectionNirvanaList.add(collectionNirvana);
        }
        
        session.close();
        this.sessionFactory.close();
        return collectionNirvanaList;
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

    public UtilityFunction getUtilityFunction() {
        return utilityFunction;
    }

    public void setUtilityFunction(UtilityFunction utilityFunction) {
        this.utilityFunction = utilityFunction;
    }


}
