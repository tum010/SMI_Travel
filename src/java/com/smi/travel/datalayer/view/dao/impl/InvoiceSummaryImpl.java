/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.report.model.InvoiceSummary;
import com.smi.travel.datalayer.view.dao.InvoiceSummaryDao;
import com.smi.travel.util.UtilityFunction;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Kanokporn
 */
public class InvoiceSummaryImpl implements InvoiceSummaryDao{
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public List getInvoiceSummary(String ticketfrom, String tickettype, String startdate, String enddate, String billto, String passenger, String username) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        Date thisDate = new Date();
        List data = new ArrayList();
        String Query ="";
//        Query += createTicketSummaryQuery(ticketfrom,tickettype,startdate,enddate,billto,passenger);
        System.out.println("Query : "+Query);
        int no = 0;
//        List<Object[]> InvoiceSummaryList = session.createSQLQuery(Query )
//                .addScalar("air", Hibernate.STRING)
//                .addScalar("ticket_no", Hibernate.STRING)
//                .addScalar("passenger_name", Hibernate.DOUBLE)
//                .addScalar("bill_to", Hibernate.STRING)
//                .addScalar("routing", Hibernate.STRING)
//                .addScalar("sale_fare", Hibernate.INTEGER)
//                .addScalar("net_fare", Hibernate.INTEGER)
//                .addScalar("tax", Hibernate.INTEGER)
//                .addScalar("profit", Hibernate.INTEGER)
//                .addScalar("owner", Hibernate.STRING)
//                .addScalar("ref_no", Hibernate.STRING)
//                .addScalar("ticket_from", Hibernate.STRING)
//                .addScalar("ticket_type", Hibernate.STRING)
//                .addScalar("Create_date", Hibernate.DATE)
//                .addScalar("ticket_date", Hibernate.DATE)
//                .addScalar("invoice_no", Hibernate.STRING)
//                .list();
        
//        for (Object[] B : InvoiceSummaryList) {
            InvoiceSummary sum = new InvoiceSummary();
            no +=1;
            sum.setAmount(20.50);
            sum.setAmountcur("THB");
            sum.setDepartment("Wendy");
            sum.setDetail("Test");
            sum.setGross(200.40);
            sum.setInvfrom(new Date());
            sum.setInvdate(new Date());
            sum.setInvdepartment("Wendy");
            sum.setInvname("SATO");
            sum.setInvno("A0000030");
            sum.setInvto(new Date());
            sum.setInvtype("Temp");
            sum.setStaff("PJ");
            sum.setStatus("VOID");
//            sum.setSumamount(200.40);
//            sum.setSumnet(200.40);
//            sum.setSumprofit(200.40);
//            sum.setSumvat(3200.50);
            sum.setSystemdate("3/09/2015");
            sum.setTermpay("14");
            sum.setTo("C000030");
            sum.setUsername("PJ");
            sum.setVat(2000.50);
//            if(B[13] != null)
//            sum.setCreatedate(util.convertStringToDate(B[13].toString()));
//            if(B[14] != null)
//            sum.setTicketdate(util.convertStringToDate(B[14].toString()));
//            sum.setInvoiceno(util.ConvertString(B[15]));
//            data.add(sum);
//          System.out.println("sum data :");
//            
//        }
        
        session.close();
        this.sessionFactory.close();
        return data;
    }

    @Override
    public List getInvoiceSummary(String fromData, String toDate, String department, String type,String agent) {
        List<InvoiceSummary> listInovicSummary = new LinkedList<InvoiceSummary>();
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        Date thisDate = new Date();
        List data = new ArrayList();
        String query = "";
        int AndQuery = 0;
        if("".equals(department)  && "".equals(type)  && "".equals(fromData)  && "".equals(toDate) && "".equals(agent)){
            query = "SELECT * FROM invoice_summary st " ; 
        }else{
            query = "SELECT * FROM invoice_summary st where" ;
        }
        
        if ( department != null && (!"".equalsIgnoreCase(department)) ) {
            AndQuery = 1;
            query += " department = '" + department + "'";
        }
       
        if (type != null && (!"".equalsIgnoreCase(type)) ) {
           if(AndQuery == 1){
                query += " and type = '" + type + "'";
           }else{
               AndQuery = 1;
               query += " type = '" + type + "'";
           }
        }
        
        if(agent != null && (!"".equalsIgnoreCase(agent))){
            if(AndQuery == 1){
                query += " and to = '" + agent + "'";
           }else{
               AndQuery = 1;
               query += " to = '" + agent + "'";
           }
        }
        
        if ((fromData != null )&&(!"".equalsIgnoreCase(fromData))) {
            if ((toDate != null )&&(!"".equalsIgnoreCase(toDate))) {
                if(AndQuery == 1){
                     query += " and invdate  BETWEEN  '" + fromData + "' AND '" + toDate + "' ";
                }else{
                    AndQuery = 1;
                     query += " invdate  BETWEEN  '" + fromData + "' AND '" + toDate + "' ";
                }
                
               
            }
        }
        query += "  ORDER BY invdate DESC";
        System.out.println("Query : "+query);
        int no = 0;
        List<Object[]> InvoiceSummaryList = session.createSQLQuery(query )
                .addScalar("type", Hibernate.STRING)
                .addScalar("invno", Hibernate.STRING)
                .addScalar("invdate", Hibernate.DATE)
                .addScalar("invname", Hibernate.STRING)
                .addScalar("termpay", Hibernate.STRING)
                .addScalar("detail", Hibernate.STRING)
                .addScalar("gross", Hibernate.DOUBLE)
                .addScalar("vat", Hibernate.DOUBLE)
                .addScalar("amount", Hibernate.DOUBLE)
                .addScalar("cur", Hibernate.STRING)
                .addScalar("staff", Hibernate.STRING)
                .addScalar("status", Hibernate.STRING)
                .addScalar("department", Hibernate.STRING)
                .addScalar("to", Hibernate.STRING)
                .list();
        
        for (Object[] B : InvoiceSummaryList) {
            InvoiceSummary sum = new InvoiceSummary();
            sum.setInvtype(util.ConvertString(B[0]));
            sum.setInvno(util.ConvertString(B[1]));
            sum.setInvdate((Date)B[2]);
            sum.setInvname(util.ConvertString(B[3]));
            sum.setTermpay(util.ConvertString(B[4]));
            sum.setDetail(util.ConvertString(B[5]));
            if(B[6] != null){
                Double gross = Double.parseDouble(util.ConvertString(B[6]));
                sum.setGross(gross);
            }
            if(B[7] != null){
                Double vat = Double.parseDouble(util.ConvertString(B[7]));
                sum.setVat(vat);
            }
            if(B[8] != null){
                Double amount = Double.parseDouble(util.ConvertString(B[8]));
                sum.setAmount(amount);
            }
            sum.setAmountcur(util.ConvertString(B[9]));
            sum.setStaff(util.ConvertString(B[10]));
            sum.setStatus(util.ConvertString(B[11]));
            sum.setInvdepartment(util.ConvertString(B[12]));
            sum.setTo(util.ConvertString(B[13]));
            
            sum.setInvfrom(util.convertStringToDate(fromData));
            sum.setInvto(util.convertStringToDate(toDate));
            sum.setDepartment(department);         
            sum.setSystemdate(util.convertDateToString(new Date()));
            sum.setUsername(util.ConvertString(B[10]));
          
            data.add(sum);
        }
        
        session.close();
        this.sessionFactory.close();
        return data;
    }
    
}
