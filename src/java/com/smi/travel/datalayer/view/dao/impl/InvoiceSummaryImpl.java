/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.report.model.InvoiceSummary;
import com.smi.travel.datalayer.report.model.TicketSummary;
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
//            sum.setDepartment("Wendy");
            sum.setDetail("Test");
            sum.setGross(200.40);
//            sum.setInvFrom(new Date());
            sum.setInvdate(new Date());
            sum.setInvdepartment("Wendy");
            sum.setInvname("SATO");
            sum.setInvno("A0000030");
//            sum.setInvto(new Date());
//            sum.setInvtype("Temp");
            sum.setStaff("PJ");
            sum.setStatus("VOID");
            sum.setSumamount(200.40);
            sum.setSumnet(200.40);
            sum.setSumprofit(200.40);
            sum.setSumvat(3200.50);
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
    public List getInvoiceSummary(String ticketfrom, String tickettype, String startdate, String enddate) {
        List<InvoiceSummary> listInovicSummary = new LinkedList<InvoiceSummary>();
        InvoiceSummary sum = new InvoiceSummary();
//            no +=1;
            sum.setAmount(20.50);
            sum.setAmountcur("THB");
//            sum.setDepartment("Wendy");
            sum.setDetail("Test");
            sum.setGross(200.40);
//            sum.setInvFrom(new Date());
//            sum.setInvdate(new Date());
            sum.setInvdepartment("Wendy");
            sum.setInvname("SATO");
            sum.setInvno("A0000030");
//            sum.setInvto(new Date());
//            sum.setInvtype("Temp");
            sum.setStaff("PJ");
            sum.setStatus("VOID");
            sum.setSumamount(200.40);
            sum.setSumnet(200.40);
            sum.setSumprofit(200.40);
            sum.setSumvat(3200.50);
            sum.setSystemdate("3/09/2015");
            sum.setTermpay("14");
            sum.setTo("C000030");
            sum.setUsername("PJ");
            sum.setVat(2000.50);
            listInovicSummary.add(sum);
            
            return listInovicSummary;
    }
    
}
