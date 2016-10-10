/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.OverdueSummaryDao;
import com.smi.travel.datalayer.entity.OverdueSummartExcel;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.view.entity.CustomerAgentInfo;
import com.smi.travel.util.UtilityFunction;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        System.out.println(" group " + group);
        System.out.println(" view " + view);
        System.out.println(" clientname " + clientname);
        System.out.println(" staffname " + staffname);
        System.out.println(" vattype " + vattype);
        List<OverdueSummartExcel> data = new ArrayList<OverdueSummartExcel>();
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        String query = "";
        String clientnameTemp = "ALL";
        String staffnameTemp = "ALL";
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
            
//            String sql = "SELECT * FROM `customer_agent_info` where bill_to = '" +clientcode+ "' ";
            String sql = "SELECT concat( ifnull(concat(`mi`.`name`, ' '), ''), ifnull( concat(`cm`.`last_name`, ' '), '' ), ifnull( concat(' ', `cm`.`first_name`), '' )) AS `bill_name`, "
                    + "`cm`.`code` AS `bill_to`, `cm`.`tel` AS `tel`, `cm`.`address` AS `address`, NULL AS `fax`, NULL AS `term`, NULL AS `pay`, 'C' AS `type` "
                    + "FROM ( `customer` `cm` "
                    + "LEFT JOIN `m_initialname` `mi` ON (( `mi`.`id` = `cm`.`initial_name` ))) "
                    + "WHERE `cm`.`code` = '" + clientcode + "' "
                    + "UNION ALL "
                    + "SELECT `ag`.`name` AS `bill_name`, `ag`.`code` AS `bill_to`, `ag`.`tel` AS `tel`, `ag`.`address` AS `address`, "
                    + "`ag`.`fax` AS `fax`, `ag`.`term_id` AS `term`, `ag`.`pay_id` AS `pay`, 'A' AS `agent` "
                    + "FROM `agent` `ag` "
                    + "WHERE `ag`.`code` = '" + clientcode + "' "
                    + "UNION ALL "
                    + "SELECT concat('G.', `st`.`name`) AS `bill_name`, `st`.`ar_code` AS `bill_to`, `st`.`tel` AS `tel`, NULL AS `address`, "
                    + "NULL AS `fax`, NULL AS `term`, NULL AS `pay`, 'S' AS `type` "
                    + "FROM `staff` `st` "
                    + "WHERE (`st`.`position` = 'GUIDE') AND ( `st`.`ar_code` = '" + clientcode + "' )";
            List<Object[]> QueryList =  session.createSQLQuery(sql)
                .addScalar("bill_To",Hibernate.STRING)
                .addScalar("bill_Name",Hibernate.STRING)
                .list();
            
            for(Object[] B : QueryList){
                clientnameTemp = String.valueOf(B[1]);
            }
        }
        
        if ((staffcode != null )&&(!"".equalsIgnoreCase(staffcode))) {
            if(checkQuery == 1){
                 query += " and ovs.owner = '" + staffcode + "' ";
            }else{
                checkQuery = 1;
                 query += " ovs.owner  = '" + staffcode + "' ";
            }
            List<SystemUser> list = session.createQuery("select u from SystemUser u WHERE  u.username = '" +staffcode+ "' ").list();
            if (!list.isEmpty()) {
                staffnameTemp = list.get(0).getName();
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
                 query += " ORDER BY  ovs.invto DESC , ovs.invno ASC  ";
            }else if("2".equals(group)){
                checkQuery = 1;
                 query += " ORDER BY  ovs.ownername  DESC , ovs.invno ASC  ";
            }
        }
        
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("dd-MM-yyyy");
        
        SimpleDateFormat dft = new SimpleDateFormat();
        dft.applyPattern("dd-MM-yyyy HH:mm:ss");
        
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
                .addScalar("invname", Hibernate.STRING)
                .addScalar("invstatus", Hibernate.STRING)
                .list();
        for (Object[] B : overdueList) {
            OverdueSummartExcel overdue = new OverdueSummartExcel();
            //Header 
            if(clientname != null && !"".equals(clientname)){
                overdue.setClientname_page(clientnameTemp);
            }else{
                overdue.setClientname_page("ALL");
            }
            
            if(staffcode != null && !"".equals(staffcode)){
                overdue.setStaffname_page(staffnameTemp);
            }else{
                overdue.setStaffname_page("ALL");
            }
            
//            System.out.println(" Date Over : " + from + " : " + to );
            if(from != null && !"".equals(from)){
                overdue.setFrom_page(df.format(util.convertStringToDate(from)) + " To " + df.format(util.convertStringToDate(to)));
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
                    String invname = util.ConvertString(B[15]) != null && !"".equals(util.ConvertString(B[15])) ? util.ConvertString(B[15]) :"";
                    overdue.setGroupBy(util.ConvertString(B[14]) != null && !"".equals(util.ConvertString(B[14])) ? util.ConvertString(B[14]) + "   " +invname : "");
                }else{
                    overdue.setGroup_page("Owner");
                    overdue.setGroup("Owner");
                    overdue.setGroupBy(util.ConvertString(B[13]) != null && !"".equals(util.ConvertString(B[13])) ? util.ConvertString(B[13]) :"ALL");
                }
            }
            
            if(view != null && !"".equals(view)){
                overdue.setView_page(view);
            }else{
                overdue.setView_page("ALL");
            }
            
            if(printby != null && !"".equals(printby)){
                overdue.setSignname(printby);
                overdue.setPrintby_page(printby);
            }else{
                overdue.setSignname("");
                overdue.setPrintby_page("");
            }        
            
            overdue.setPrintdate_page(dft.format(new Date()));
            
            overdue.setInvno(util.ConvertString(B[0]) != null && !"".equals(util.ConvertString(B[0])) ? util.ConvertString(B[0]) :"");
            overdue.setDate(util.ConvertString(B[1]) != null && !"".equals(util.ConvertString(B[1])) ? df.format(util.convertStringToDate(util.ConvertString(B[1]))) :"");
            overdue.setDetail(util.ConvertString(B[2]) != null && !"".equals(util.ConvertString(B[2])) ? util.ConvertString(B[2]) :"");
            overdue.setBath(util.ConvertString(B[3]) != null && !"".equals(util.ConvertString(B[3])) ? util.ConvertString(B[3]) :"");
            overdue.setJpy(util.ConvertString(B[4]) != null && !"".equals(util.ConvertString(B[4])) ? util.ConvertString(B[4]) :"");
            overdue.setUsd(util.ConvertString(B[5]) != null && !"".equals(util.ConvertString(B[5])) ? util.ConvertString(B[5]) :"");
            overdue.setRecno(util.ConvertString(B[6]) != null && !"".equals(util.ConvertString(B[6])) ? util.ConvertString(B[6]) :"");
            overdue.setRecamt(util.ConvertString(B[7]) != null && !"".equals(util.ConvertString(B[7])) ? util.ConvertString(B[7]) :"");
            overdue.setDepartment(util.ConvertString(B[8]) != null && !"".equals(util.ConvertString(B[8])) ? util.ConvertString(B[8]) :"");
            overdue.setCredit(util.ConvertString(B[9]) != null && !"".equals(util.ConvertString(B[9])) ? util.ConvertString(B[9]) :"");
            overdue.setRefno(util.ConvertString(B[10]) != null && !"".equals(util.ConvertString(B[10])) ? util.ConvertString(B[10]) :"");
            overdue.setDuedate(util.ConvertString(B[11]) != null && !"".equals(util.ConvertString(B[11])) ? df.format(util.convertStringToDate(util.ConvertString(B[11]))) :"");
            overdue.setOverduesstatus(util.ConvertString(B[12]) != null && !"".equals(util.ConvertString(B[12])) ? util.ConvertString(B[12]) :"");
            overdue.setOwnername(util.ConvertString(B[13]) != null && !"".equals(util.ConvertString(B[13])) ? util.ConvertString(B[13]) :"ALL");
            String invname = util.ConvertString(B[15]) != null && !"".equals(util.ConvertString(B[15])) ? util.ConvertString(B[15]) :"";
            overdue.setInvto(util.ConvertString(B[14]) != null && !"".equals(util.ConvertString(B[14])) ? util.ConvertString(B[14]) + "   " +invname : "");
            
            if(!"2".equalsIgnoreCase(util.ConvertString(B[16]))){
                data.add(overdue);
            }
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
