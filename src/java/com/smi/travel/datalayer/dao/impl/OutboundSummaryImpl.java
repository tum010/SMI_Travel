/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.OutboundSummaryDao;
import com.smi.travel.datalayer.view.entity.OutboundProductSummaryExcel;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Kanokporn
 */
public class OutboundSummaryImpl implements OutboundSummaryDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;

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
    public List getOutboundProductSummary(String productid, String from, String to, String saleby, String payby, String bank,String printby) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        String query ="";
        int checkQuery = 0;
        if(productid == null && from == null && to == null && saleby == null && payby == null && bank == null){
            query = "SELECT *  FROM  `outbound_product_summary` opm ";
        }else{
            if("".equals(productid) && "".equals(from) && "".equals(to) && "".equals(saleby) && "".equals(payby) && "".equals(bank)){
                query = "SELECT *  FROM  `outbound_product_summary` opm ";
            }else{
                query = "SELECT *  FROM  `outbound_product_summary` opm  where ";
            }
        }
        
        
       if ((from != null )&&(!"".equalsIgnoreCase(from))) {
            if ((to != null )&&(!"".equalsIgnoreCase(to))) {
                if(checkQuery == 1){
                     query += " and opm.otherdate  BETWEEN  '" + from + "' AND '" + to + "' ";
                }else{
                    checkQuery = 1;
                     query += " opm.otherdate  BETWEEN  '" + from + "' AND '" + to + "' ";
                }
            }
        }
        if ((productid != null )&&(!"".equalsIgnoreCase(productid))) {
            if(checkQuery == 1){
                 query += " and opm.productid  = " + productid;
            }else{
                checkQuery = 1;
                 query += " opm.productid = " + productid;
            }
        }
        if ((saleby != null )&&(!"".equalsIgnoreCase(saleby))) {
            if(checkQuery == 1){
                 query += " and opm.saleby  = " + saleby;
            }else{
                checkQuery = 1;
                 query += " opm.saleby = " + saleby;
            }
        }
        if ((payby != null )&&(!"".equalsIgnoreCase(payby))) {
            if(checkQuery == 1){
                 query += " and opm.payid  = " + payby;
            }else{
                checkQuery = 1;
                 query += " opm.payid = " + payby;
            }
        }
        if ((bank != null )&&(!"".equalsIgnoreCase(bank))) {
            if(checkQuery == 1){
                 query += " and opm.bank  = " + bank;
            }else{
                checkQuery = 1;
                 query += " opm.bank = " + bank;
            }
        }
        
        System.out.println(" query :::: " +query);
               
        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("otherdate", Hibernate.STRING)//0
                .addScalar("refno", Hibernate.STRING)
                .addScalar("recondno", Hibernate.STRING)
                .addScalar("productname", Hibernate.STRING)
                .addScalar("leader", Hibernate.STRING)
                .addScalar("passno", Hibernate.STRING)// 5
                .addScalar("pax_adult", Hibernate.INTEGER)
                .addScalar("pax_child", Hibernate.INTEGER)
                .addScalar("pax_infant", Hibernate.INTEGER)
                .addScalar("net_adult", Hibernate.BIG_DECIMAL)
                .addScalar("net_child", Hibernate.BIG_DECIMAL)// 10
                .addScalar("net_infant", Hibernate.BIG_DECIMAL)
                .addScalar("sell_adult", Hibernate.BIG_DECIMAL)
                .addScalar("sell_child", Hibernate.BIG_DECIMAL)
                .addScalar("sell_infant", Hibernate.BIG_DECIMAL)
                .addScalar("profit", Hibernate.BIG_DECIMAL)//15
                .addScalar("payby", Hibernate.STRING)
                .addScalar("transferdate", Hibernate.STRING)
                .addScalar("seller", Hibernate.STRING)
                .addScalar("dulation", Hibernate.STRING)
                .addScalar("invno", Hibernate.STRING)// 20
                .addScalar("saleby", Hibernate.STRING)
                .addScalar("productid", Hibernate.STRING)
                .addScalar("payid", Hibernate.STRING)
                .addScalar("bank", Hibernate.STRING)
                .list();
        
        for (Object[] B : QueryStaffList) {
            OutboundProductSummaryExcel other = new OutboundProductSummaryExcel();
            other.setSaledate(B[0]== null ? "" :util.ConvertString(B[0]));
            other.setRecordno(B[2]== null ? "" :util.ConvertString(B[2]));
            other.setTravoxno(B[1]== null ? "" :util.ConvertString(B[1])); // 
            other.setPasstype(B[3]== null ? "" :util.ConvertString(B[3])); // 
            other.setPassno(B[5]== null ? "" :util.ConvertString(B[5]));
            other.setDulation(B[19]== null ? "" :util.ConvertString(B[19]));
            other.setInvno(B[20]== null ? "" :util.ConvertString(B[20]));
            other.setCustomername(B[4]== null ? "" :util.ConvertString(B[4]));//
            other.setPaxad((Integer)B[6]);
            other.setPaxch((Integer)B[7]);
            other.setPaxin((Integer)B[8]);
            other.setTotalnettadult((BigDecimal)B[9]);
            other.setTotalnettchild((BigDecimal)B[10]);
            other.setTotalnettinfant((BigDecimal)B[11]);
            other.setTotalsaleadult((BigDecimal)B[12]);
            other.setTotalsalechild((BigDecimal)B[13]);
            other.setTotalsaleinfant((BigDecimal)B[14]);
            other.setProfittotal((BigDecimal)B[15]);
            other.setPayby(B[16]== null ? "" :util.ConvertString(B[16]));
            other.setDatetrsf(B[17]== null ? "" :util.ConvertString(B[17]));
            other.setSeller(B[21]== null ? "" :util.ConvertString(B[21]));
            // Set Header
            other.setProductname(B[3]== null ? "" :util.ConvertString(B[3]));
            other.setProductnamepage(B[3]== null ? "" :util.ConvertString(B[3]));
            other.setSalebypage(B[21]== null ? "" :util.ConvertString(B[21]));
            other.setBankpage("");
            other.setSaledate("");
            other.setPaybypage("");
            other.setStatuspage("");           
            data.add(other);
        }
        
        this.sessionFactory.close();
        session.close();
        return data;
    }
    
}
