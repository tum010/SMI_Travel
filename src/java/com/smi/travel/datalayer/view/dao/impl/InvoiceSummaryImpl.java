/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.report.model.TicketSummary;
import com.smi.travel.datalayer.view.dao.InvoiceSummaryDao;
import com.smi.travel.util.UtilityFunction;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
//        List<Object[]> QueryTicketList = session.createSQLQuery(Query )
//                .addScalar("air", Hibernate.STRING)
//                .addScalar("ticket_no", Hibernate.STRING)
//                .addScalar("passenger_name", Hibernate.STRING)
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
//        
//        for (Object[] B : QueryTicketList) {
//            TicketSummary sum = new TicketSummary();
//            no +=1;
//            sum.setNo(no);
//            sum.setSystemdate(new SimpleDateFormat("dd MMM yyyy hh:mm:ss", new Locale("us", "us")).format(thisDate));
//            sum.setUsername(username);
//            
//            sum.setStartdate(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(startdate)));
//            sum.setEnddate(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(enddate)));
//            sum.setAir(util.ConvertString(B[0]));
//            sum.setTicketno(util.ConvertString(B[1]));
//            sum.setPassengername(util.ConvertString(B[2]));
//            sum.setBillto(util.ConvertString(B[3]));
//            sum.setRouting(util.ConvertString(B[4]));
//            sum.setSalefare( B[5]== null ? 0:(Integer)B[5]);
//            sum.setNetfare( B[6]== null ? 0:(Integer)B[6]);
//            sum.setTax( B[7]== null ? 0:(Integer)B[7]);
//            sum.setProfit( B[8]== null ? 0:(Integer)B[8]);
//            sum.setOwner(util.ConvertString(B[9]));
//            sum.setRefno(util.ConvertString(B[10]));
//            if(B[13] != null)
//            sum.setCreatedate(util.convertStringToDate(B[13].toString()));
//            if(B[14] != null)
//            sum.setTicketdate(util.convertStringToDate(B[14].toString()));
//            sum.setInvoiceno(util.ConvertString(B[15]));
//            data.add(sum);
//            System.out.println("sum data :");
            
//        }
        
        session.close();
        this.sessionFactory.close();
        return data;
    }
    
}
