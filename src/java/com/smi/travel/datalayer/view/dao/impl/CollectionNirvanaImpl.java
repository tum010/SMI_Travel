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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
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
    public List<CollectionNirvana> getCollectionNirvanaFromFilter(String department, String type, String status, String dateFrom, String dateTo, String invno,String printby) {
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
        }else{
            department = "ALL";
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
         query += " ORDER BY invno desc  ";
         
        if(checkQuery == 0){query = query.replaceAll("where", "");}
        System.out.println("query : "+query);
        
        List<Object[]> QueryList =  session.createSQLQuery(query)
                                .addScalar("invno",Hibernate.STRING)
                                .addScalar("invto",Hibernate.STRING)
                                .addScalar("arcode",Hibernate.STRING)
                                .addScalar("acccode",Hibernate.STRING)
                                .addScalar("suminvamount",Hibernate.BIG_DECIMAL)
                                .addScalar("recno",Hibernate.STRING)
                                .addScalar("sumrecamount",Hibernate.BIG_DECIMAL)
                                .addScalar("cur",Hibernate.STRING)
                                .addScalar("diff",Hibernate.BIG_DECIMAL)
                                .addScalar("collection_status",Hibernate.STRING)
                                .addScalar("department",Hibernate.STRING)
                                .addScalar("type",Hibernate.STRING)
                                .addScalar("invdate",Hibernate.DATE)
                                .addScalar("invamount",Hibernate.STRING)
                                .addScalar("wth",Hibernate.BIG_DECIMAL)
                                .addScalar("cash",Hibernate.BIG_DECIMAL)
                                .addScalar("chq",Hibernate.BIG_DECIMAL)
                                .addScalar("credit",Hibernate.BIG_DECIMAL)
                                .addScalar("banktransfer",Hibernate.BIG_DECIMAL)
                                .addScalar("wt",Hibernate.BIG_DECIMAL)
                                .addScalar("cashminus",Hibernate.BIG_DECIMAL)
                                .addScalar("pay_by",Hibernate.STRING)
                                .addScalar("itf_status",Hibernate.STRING)
                                .addScalar("rowid",Hibernate.STRING)
                                .list();

        List data = new ArrayList();
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("dd-MM-yyyy");
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy hh:mm");
        int count = 1;
        BigDecimal cash = new BigDecimal(BigInteger.ZERO);
        BigDecimal chq  = new BigDecimal(BigInteger.ZERO);
        BigDecimal credit = new BigDecimal(BigInteger.ZERO);
        BigDecimal banktransfer = new BigDecimal(BigInteger.ZERO);
        BigDecimal wt = new BigDecimal(BigInteger.ZERO);
        BigDecimal cashminus = new BigDecimal(BigInteger.ZERO);
        
        BigDecimal totalamount = new BigDecimal(BigInteger.ZERO);
        BigDecimal totalamountwait  = new BigDecimal(BigInteger.ZERO);
        BigDecimal totalamountvoid = new BigDecimal(BigInteger.ZERO);
        BigDecimal totalamountinvoice = new BigDecimal(BigInteger.ZERO);
        BigDecimal totalamountdiff = new BigDecimal(BigInteger.ZERO);
        
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
            collectionNirvana.setInvoiceamount(util.ConvertString(CN[13]));
            collectionNirvana.setWithtax((BigDecimal) CN[14]);
            
            cash = cash.add((BigDecimal) CN[15]);
            collectionNirvana.setCash(cash);
            
            chq = chq.add((BigDecimal) CN[16]);
            collectionNirvana.setChq(chq);
            
            credit = credit.add((BigDecimal) CN[17]);
            collectionNirvana.setCredit(credit);
            
            banktransfer = banktransfer.add((BigDecimal) CN[18]);
            collectionNirvana.setBanktransfer(banktransfer);
            
            wt = wt.add((BigDecimal) CN[19]);
            collectionNirvana.setWt(wt);
            
            cashminus = cashminus.add((BigDecimal) CN[20]);
            collectionNirvana.setCashminus(cashminus);
            
            String paybuy = util.ConvertString(CN[21]);
            collectionNirvana.setPayby(util.ConvertString(CN[21]));
            if(!"Wait".equalsIgnoreCase(paybuy) && !"Void".equalsIgnoreCase(paybuy)){
                totalamount = totalamount.add((BigDecimal) CN[6]);
            }
            if("Wait".equalsIgnoreCase(paybuy)){
                totalamountwait = totalamountwait.add((BigDecimal) CN[6]);
            }
            if("Void".equalsIgnoreCase(paybuy)){
                totalamountvoid = totalamountvoid.add((BigDecimal) CN[6]);
            }
            totalamountinvoice = totalamountinvoice.add((BigDecimal) CN[4]);
            totalamountdiff = totalamountdiff.add((BigDecimal) CN[8]);
            collectionNirvana.setTotalamount(totalamount);
            collectionNirvana.setTotalamountwait(totalamountwait);
            collectionNirvana.setTotalamountvoid(totalamountvoid);
            collectionNirvana.setTotalamountinvoice(totalamountinvoice);
            collectionNirvana.setTotalamountdiff(totalamountdiff);
            
            collectionNirvana.setSystemdate(String.valueOf(dateformat.format(new Date())));
            collectionNirvana.setUser(printby);
            if((dateFrom != null) && (!"".equalsIgnoreCase(dateFrom))){
                collectionNirvana.setFrom(String.valueOf(df.format(util.convertStringToDate(dateFrom))));
            }else{
                collectionNirvana.setFrom("");
            }
            if((dateTo != null) && (!"".equalsIgnoreCase(dateTo))){
                collectionNirvana.setTo(String.valueOf(df.format(util.convertStringToDate(dateTo))));
            }else{
                collectionNirvana.setTo("");
            }
            collectionNirvana.setHeaderdepartment(department);
            
            collectionNirvana.setStatus(util.ConvertString(CN[22]));
            collectionNirvana.setRowid(util.ConvertString(CN[23]));
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

    @Override
    public String UpdateStatusCollection(List<CollectionNirvana> cnList) {
        UtilityFunction utilty =  new UtilityFunction();
        String isUpdate ="";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            for (int i = 0; i < cnList.size(); i++) {
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String strDate = sdf.format(cal.getTime());
                Date date = new Date();
                
                String hql = "";
                String id = "";
                if(cnList.get(i).getRowid() != null && !"".equalsIgnoreCase(cnList.get(i).getRowid())){
                    id = cnList.get(i).getRowid();
                    hql = "update Receipt rec set rec.isExport = 1 , rec.exportDate = :date where rec.id = :recid";
                }
                Query query = session.createQuery(hql);
                query.setParameter("recid", String.valueOf(id));
                query.setParameter("date", date);
                int result = query.executeUpdate();
                System.out.println("Query Update : " + result + ":" + query);
            }

            transaction.commit();
            session.close();
            this.sessionFactory.close();
            isUpdate = "updatesuccess";
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            isUpdate = "updatefail";
        }
        return isUpdate;
    }


}
