/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.OverdueSummaryDao;
import com.smi.travel.datalayer.entity.OverdueSummartExcel;
import com.smi.travel.util.UtilityFunction;
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
public class OverdueSummaryImpl implements OverdueSummaryDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;
    
    @Override
    public List listOverdueSummary(String clientcode, String clientname, String staffcode, String staffname, String vattype, String from, String to, String depart, String group, String view, String printby) {
        List<OverdueSummartExcel> data = new ArrayList<OverdueSummartExcel>();
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        String query = "";
        int checkQuery = 0;
        if( clientcode == null  && staffcode == null  && vattype == null  && from == null  && to == null  && depart  == null && group == null && view == null ){
            query = "SELECT * FROM `overdue_summary`  ovs ";
        }else{
            if("".equals(clientcode)  && "".equals(staffcode) && "".equals(vattype)  && "".equals(from)  && "".equals(to)  && "".equals(depart) && "".equals(group) && "".equals(view)){
                query = "SELECT * FROM `overdue_summary`  ovs  ";
            }else{
                query = "SELECT * FROM `overdue_summary`  ovs  Where ";
            }
        }
        
        if ((from != null )&&(!"".equalsIgnoreCase(from))) {
            if ((to != null )&&(!"".equalsIgnoreCase(to))) {
                if(checkQuery == 1){
                     query += " and ovs.invdate  BETWEEN  '" + from + "' AND '" + to + "' ";
                }else{
                    checkQuery = 1;
                     query += " ovs.invdate  BETWEEN  '" + from + "' AND '" + to + "' ";
                }
            }
        }     
        
        if ((clientcode != null )&&(!"".equalsIgnoreCase(clientcode))) {
            if(checkQuery == 1){
                 query += " and ovs.invto  = '" + clientcode + "' ";
            }else{
                checkQuery = 1;
                 query += " ovs.invto  = '" + clientcode + "' ";
            }
        }
        
        if ((staffname != null )&&(!"".equalsIgnoreCase(staffname))) {
            if(checkQuery == 1){
                 query += " and ovs.ownername  = '" + staffname + "' ";
            }else{
                checkQuery = 1;
                 query += " ovs.ownername  = '" + staffname + "' ";
            }
        }
        
        if ((vattype != null )&&(!"".equalsIgnoreCase(vattype))) {
            if(checkQuery == 1){
                 query += " and ovs.type  = '" + vattype + "' ";
            }else{
                checkQuery = 1;
                 query += " ovs.type  = '" + vattype + "' ";
            }
        }
        
        if ((depart != null )&&(!"".equalsIgnoreCase(depart))) {
            if(checkQuery == 1){
                 query += " and ovs.department  = '" + depart + "' ";
            }else{
                checkQuery = 1;
                 query += " ovs.department  = '" + depart + "' ";
            }
        }
        
        if ((view != null )&&(!"".equalsIgnoreCase(view))) {
            if(checkQuery == 1){
                 query += " and ovs.overduestatus  = '" + view + "' ";
            }else{
                checkQuery = 1;
                 query += " ovs.overduestatus  = '" + view + "' ";
            }
        }
        
        if ((group != null )&&(!"".equalsIgnoreCase(group))) {
            if("1".equals(group)){// agent
                 query += " ORDER BY  ovs.invto DESC ";
            }else if("2".equals(group)){
                checkQuery = 1;
                 query += " ORDER BY  ovs.ownername  DESC ";
            }
        }
        
        System.out.println("query : " + query);
        List<Object[]> overdueList = session.createSQLQuery(query )
                .addScalar("invno", Hibernate.STRING)
                .addScalar("invdate", Hibernate.STRING)
                .addScalar("detail", Hibernate.STRING)
                .addScalar("thb", Hibernate.STRING)
                .addScalar("jpy", Hibernate.STRING)
                .addScalar("usd", Hibernate.STRING)
                .addScalar("recno", Hibernate.STRING)
                .addScalar("recamt", Hibernate.STRING)
                .addScalar("department", Hibernate.STRING)
                .addScalar("creditterm", Hibernate.STRING)
                .addScalar("refno", Hibernate.STRING)
                .addScalar("duedate", Hibernate.STRING)
                .addScalar("overduestatus", Hibernate.STRING)
                .addScalar("ownername", Hibernate.STRING)
                .addScalar("invto", Hibernate.STRING)
                .list();
        for (Object[] B : overdueList) {
            OverdueSummartExcel overdue = new OverdueSummartExcel();
            //Header 
            if(clientname != null && !"".equals(clientname)){
                overdue.setClientname_page(clientname);
            }else{
                overdue.setClientname_page("ALL");
            }
            
            if(staffname != null && !"".equals(staffname)){
                overdue.setStaffname_page(clientname);
            }else{
                overdue.setStaffname_page("ALL");
            }
            
            System.out.println(" Date Over : " + from + " : " + to );
            if(from != null && !"".equals(from)){
                overdue.setFrom_page(from + " To " + to);
            }else{
                overdue.setFrom_page("ALL");
            }
           
            if("V".equals(vattype)){
                overdue.setVattype_page("Vat");
            }else if("N".equals(vattype)){
                overdue.setVattype_page("No Vat");
            }else if("T".equals(vattype)){
                overdue.setVattype_page("Temp");
            }else if("A".equals(vattype)){
                overdue.setVattype_page("Air");
            }else{
                overdue.setVattype_page("ALL");
            }
            
            if(depart != null && !"".equals(depart)){
                overdue.setDepart_page(depart);
            }else{
                overdue.setDepart_page("ALL");
            }
            
            if(group != null && !"".equals(group)){
                if("1".equals(group)){
                    overdue.setGroup_page("Agent");
                    overdue.setGroup("Agent");
                }else{
                    overdue.setGroup_page("Owner");
                    overdue.setGroup("Owner");
                }
            }
            
            if(view != null && !"".equals(view)){
                overdue.setView_page(view);
            }else{
                overdue.setView_page("ALL");
            }
            
            overdue.setInvno(util.ConvertString(B[0]) != null && !"".equals(util.ConvertString(B[0])) ? util.ConvertString(B[0]) :"");
            overdue.setDate(util.ConvertString(B[1]) != null && !"".equals(util.ConvertString(B[1])) ? util.ConvertString(B[1]) :"");
            overdue.setDetail(util.ConvertString(B[2]) != null && !"".equals(util.ConvertString(B[2])) ? util.ConvertString(B[2]) :"");
            overdue.setBath(util.ConvertString(B[3]) != null && !"".equals(util.ConvertString(B[3])) ? util.ConvertString(B[3]) :"");
            overdue.setJpy(util.ConvertString(B[4]) != null && !"".equals(util.ConvertString(B[4])) ? util.ConvertString(B[4]) :"");
            overdue.setUsd(util.ConvertString(B[5]) != null && !"".equals(util.ConvertString(B[5])) ? util.ConvertString(B[5]) :"");
            overdue.setRecno(util.ConvertString(B[6]) != null && !"".equals(util.ConvertString(B[6])) ? util.ConvertString(B[6]) :"");
            overdue.setRecamt(util.ConvertString(B[7]) != null && !"".equals(util.ConvertString(B[7])) ? util.ConvertString(B[7]) :"");
            overdue.setDepartment(util.ConvertString(B[8]) != null && !"".equals(util.ConvertString(B[8])) ? util.ConvertString(B[8]) :"");
            overdue.setCredit(util.ConvertString(B[9]) != null && !"".equals(util.ConvertString(B[9])) ? util.ConvertString(B[9]) :"");
            overdue.setRefno(util.ConvertString(B[10]) != null && !"".equals(util.ConvertString(B[10])) ? util.ConvertString(B[10]) :"");
            overdue.setDuedate(util.ConvertString(B[11]) != null && !"".equals(util.ConvertString(B[11])) ? util.ConvertString(B[11]) :"");
            overdue.setOverduesstatus(util.ConvertString(B[12]) != null && !"".equals(util.ConvertString(B[12])) ? util.ConvertString(B[12]) :"");
            overdue.setOwnername(util.ConvertString(B[13]) != null && !"".equals(util.ConvertString(B[13])) ? util.ConvertString(B[13]) :"ALL");
            overdue.setInvto(util.ConvertString(B[14]) != null && !"".equals(util.ConvertString(B[14])) ? util.ConvertString(B[14]) :"");
            data.add(overdue);
        }
        session.close();
        this.sessionFactory.close();
        return data;
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
