/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.view.dao.ARNirvanaDao;
import com.smi.travel.datalayer.view.entity.ARNirvana;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import net.sourceforge.jtds.jdbc.DateTime;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
//import org.joda.time.format.DateTimeFormat;
//import java.time.format.DateTimeFormatter;

/**
 *
 * @author Surachai
 */
public class ARNirvanaImpl implements  ARNirvanaDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;
    @Override
    public List<ARNirvana> SearchArNirvanaFromFilter(String invtype, String department, String billtype, String from, String to, String status) {
        System.out.println("Invoice Type : " + invtype + ":");
        System.out.println("Depart ment : " + department + ":");
        System.out.println("Bill Type : " + billtype + ":");
        System.out.println("From : " + from + ":");
        System.out.println("To : " + to + ":");
        System.out.println("Status : " + status + ":");
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        Date thisDate = new Date();
        List data = new ArrayList();
        String query = "";
        int AndQuery = 0;
        
        if("".equals(invtype)  && "".equals(department)  && "".equals(billtype)  && "".equals(from) && "".equals(to) && "".equals(status)){
            query = "SELECT * FROM ap_nirvana ar " ; 
        }else{
            query = "SELECT * FROM ap_nirvana ar where" ;
        }
        
        if ( department != null && (!"".equalsIgnoreCase(department)) ) {
            AndQuery = 1;
            query += " department = '" + department + "'";
        }
       
        if (invtype != null && (!"".equalsIgnoreCase(invtype)) ) {
           if(AndQuery == 1){
                query += " and invtype = '" + invtype + "'";
           }else{
               AndQuery = 1;
               query += " invtype = '" + invtype + "'";
           }
        }
        
        if(billtype != null && (!"".equalsIgnoreCase(billtype))){
            if(AndQuery == 1){
                query += " and producttype = '" + billtype + "'";
           }else{
               AndQuery = 1;
               query += " producttype = '" + billtype + "'";
           }
        }
        
        if ((from != null )&&(!"".equalsIgnoreCase(from))) {
            if ((to != null )&&(!"".equalsIgnoreCase(to))) {
                if(AndQuery == 1){
                     query += " and invdate  BETWEEN  '" + from + "' AND '" + to + "' ";
                }else{
                    AndQuery = 1;
                     query += " invdate  BETWEEN  '" + from + "' AND '" + to + "' ";
                }
                
               
            }
        }
        
        if(status != null && (!"".equalsIgnoreCase(status))){
            if(AndQuery == 1){
                query += " and itf_status = '" + status + "'";
           }else{
               AndQuery = 1;
               query += " itf_status = '" + status + "'";
           }
        }
        query += "  ORDER BY invdate  DESC";
        System.out.println("query : " + query);
        List<Object[]> ARNirvanaList = session.createSQLQuery(query )
                .addScalar("invtype", Hibernate.STRING)
                .addScalar("department", Hibernate.STRING)
                .addScalar("producttype", Hibernate.STRING)
                .addScalar("invdate", Hibernate.DATE)
                .addScalar("itf_status", Hibernate.STRING)
                .addScalar("intreference", Hibernate.STRING)
                .addScalar("salesmanid", Hibernate.STRING)
                .addScalar("customerid", Hibernate.STRING)
                .addScalar("customername", Hibernate.STRING)
                .addScalar("divisionid", Hibernate.STRING)
                .addScalar("projectid", Hibernate.STRING)
                .addScalar("transcode", Hibernate.STRING)
                .addScalar("transdate", Hibernate.DATE)
                .addScalar("duedate", Hibernate.DATE)
                .addScalar("currencyid", Hibernate.STRING)
                .addScalar("homerate", Hibernate.BIG_DECIMAL)
                .addScalar("foreignrate", Hibernate.BIG_DECIMAL)
                .addScalar("salesamt", Hibernate.BIG_DECIMAL)
                .addScalar("saleshmamt", Hibernate.BIG_DECIMAL)
                .addScalar("vatamt", Hibernate.BIG_DECIMAL)
                .addScalar("vathmamt", Hibernate.BIG_DECIMAL)
                .addScalar("aramt", Hibernate.BIG_DECIMAL)
                .addScalar("arhmamt", Hibernate.BIG_DECIMAL)
                .addScalar("vatflag", Hibernate.STRING)
                .addScalar("vatid", Hibernate.STRING)
                .addScalar("whtflag", Hibernate.STRING)
                .addScalar("whtid", Hibernate.STRING)
                .addScalar("basewhtamt", Hibernate.BIG_DECIMAL)
                .addScalar("basewhthmamt", Hibernate.BIG_DECIMAL)
                .addScalar("whtamt", Hibernate.BIG_DECIMAL)
                .addScalar("whthmamt", Hibernate.BIG_DECIMAL)
                .addScalar("year", Hibernate.INTEGER)
                .addScalar("period", Hibernate.INTEGER)
                .addScalar("note", Hibernate.STRING)
                .addScalar("salesaccount1", Hibernate.STRING)
                .addScalar("salesdivision1", Hibernate.STRING)
                .addScalar("salesproject1", Hibernate.STRING)
                .addScalar("salesamt1", Hibernate.BIG_DECIMAL)
                .addScalar("saleshmamt1", Hibernate.BIG_DECIMAL)
                .addScalar("salesaccount2", Hibernate.STRING)
                .addScalar("salesdivision2", Hibernate.STRING)
                .addScalar("salesproject2", Hibernate.STRING)
                .addScalar("salesamt2", Hibernate.BIG_DECIMAL)
                .addScalar("saleshmamt2", Hibernate.BIG_DECIMAL)
                .addScalar("salesaccount3", Hibernate.STRING)
                .addScalar("salesdivision3", Hibernate.STRING)
                .addScalar("salesproject3", Hibernate.STRING)
                .addScalar("salesamt3", Hibernate.BIG_DECIMAL)
                .addScalar("saleshmamt3", Hibernate.BIG_DECIMAL)
                .addScalar("service", Hibernate.STRING)
                .addScalar("araccount", Hibernate.STRING)
                .addScalar("prefix", Hibernate.STRING)
                .addScalar("documentno", Hibernate.INTEGER)
                .addScalar("artrans", Hibernate.STRING)
                .addScalar("cust_taxid", Hibernate.STRING)
                .addScalar("cust_branch", Hibernate.INTEGER)
                .addScalar("company_branch", Hibernate.INTEGER)
                .addScalar("inv_id", Hibernate.INTEGER)
                .list();
        for (Object[] B : ARNirvanaList) {
            ARNirvana ar = new ARNirvana();
            ar.setInvtype(util.ConvertString(B[0]));
            ar.setDepartment(util.ConvertString(B[1]));
            ar.setProducttype(util.ConvertString(B[2]));
            
            ar.setInvdate(util.convertStringToDate(util.ConvertString(B[3])));
            ar.setStatus(util.ConvertString(B[4]));
            ar.setIntreference(util.ConvertString(B[5]));
            ar.setSalesmanid(util.ConvertString(B[6]));
            ar.setCustomerid(util.ConvertString(B[7]));
            ar.setCustomername(util.ConvertString(B[8]));
            ar.setDivisionid(util.ConvertString(B[9]));
            ar.setProjectid(util.ConvertString(B[10]));
            ar.setTranscode(util.ConvertString(B[11]));
            ar.setTransdate(util.convertStringToDate(util.ConvertString(B[12])));
            ar.setDuedate(util.convertStringToDate(util.ConvertString(B[13])));
            ar.setCurrencyid(util.ConvertString(B[14]));
            ar.setHomerate((BigDecimal) B[15]);
            ar.setForeignrate((BigDecimal) B[16]);
            ar.setSalesamt((BigDecimal) B[17]);
            ar.setSaleshmamt((BigDecimal) B[18]);
            ar.setVatamt((BigDecimal) B[19]);
            ar.setVathmamt((BigDecimal) B[20]);
            ar.setAramt((BigDecimal) B[21]);
            ar.setArhmamt((BigDecimal) B[22]);
            ar.setVatflag(util.ConvertString(B[23]));
            ar.setVatid(util.ConvertString(B[24]));
            ar.setWhtflag(util.ConvertString(B[25]));
            ar.setWhtid(util.ConvertString(B[26]));
            ar.setBasewhtamt((BigDecimal) B[27]);
            ar.setBasewhtmamt((BigDecimal) B[28]);
            ar.setWhtamt((BigDecimal) B[29]);
            ar.setWhthmamt((BigDecimal) B[30]);
            ar.setYear((Integer) B[31]);
            ar.setPeriod((Integer) B[32]);
            ar.setNote(util.ConvertString(B[33]));
            ar.setSalesaccount1(util.ConvertString(B[34]));
            ar.setSalesdivision1(util.ConvertString(B[35]));
            ar.setSalesproject1(util.ConvertString(B[36]));
            ar.setSalesamt1((BigDecimal) B[37]);
            ar.setSaleshmamt((BigDecimal) B[38]);
            ar.setSalesaccount2(util.ConvertString(B[39]));
            ar.setSalesdivision2(util.ConvertString(B[40]));
            ar.setSalesproject2(util.ConvertString(B[41]));
            ar.setSalesamt2((BigDecimal) B[42]);
            ar.setSaleshmamt2((BigDecimal) B[43]);
            ar.setSalesaccount3(util.ConvertString(B[44]));
            ar.setSalesdivision3(util.ConvertString(B[45]));
            ar.setSalesproject3(util.ConvertString(B[46]));
            ar.setSalesamt3((BigDecimal) B[47]);
            ar.setSaleshmamt3((BigDecimal) B[48]);
            ar.setService(util.ConvertString(B[49]));
            ar.setAraccount(util.ConvertString(B[50]));
            ar.setPrefix(util.ConvertString(B[51]));
            ar.setDocumentno((Integer) B[52]);
            ar.setArtrans(util.ConvertString(B[53]));
            ar.setCust_taxid(util.ConvertString(B[54]));
            ar.setCust_branch((Integer) B[55]);
            ar.setCompany_branch((Integer) B[56]);
            ar.setInvid((Integer) B[57]);
            data.add(ar);
        }
        session.close();
        this.sessionFactory.close();
        return data;
    }

    @Override
    public String ExportARFileInterface(List<ARNirvana> APList) {
        return "success";
    }

    @Override
    public String UpdateStatusARInterface(List<ARNirvana> APList) {
        UtilityFunction utilty =  new UtilityFunction();
        String isUpdate ="";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
           
            for (int i = 0; i < APList.size(); i++) {
                if (APList.get(i).getIntreference() == null) {
//                    session.save(APList.get(i));
                } else {
                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String strDate = sdf.format(cal.getTime());
                    String hql = "update Invoice inv set inv.isExport = 1,inv.exportDate = '"+ strDate+"'  where  inv.id = " + APList.get(i).getInvid();
                    Query query = session.createQuery(hql);
                    int result = query.executeUpdate();
                    System.out.println("Query Update : " + result + ":" + query);
                }
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

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Transaction getTransaction() {
        return transaction;
    }
    
}
