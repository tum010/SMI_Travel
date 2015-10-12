/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.report.model.TicketSummary;
import com.smi.travel.datalayer.view.dao.TicketSummaryDao;
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
 * @author Surachai
 */
public class TicketSummaryImpl implements TicketSummaryDao {
    
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public List getTicketSummary(String ticketfrom, String tickettype, String startdate, String enddate, String billto, String passenger,String username) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        Date thisDate = new Date();
        List data = new ArrayList();
        String Query ="SELECT * FROM `airticket_ticket` ";
        Query += createTicketSummaryQuery(ticketfrom,tickettype,startdate,enddate,billto,passenger);
        System.out.println("Query : "+Query);
        int no = 0;
        List<Object[]> QueryTicketList = session.createSQLQuery(Query )
                .addScalar("air", Hibernate.STRING)
                .addScalar("ticket_no", Hibernate.STRING)
                .addScalar("passenger_name", Hibernate.STRING)
                .addScalar("bill_to", Hibernate.STRING)
                .addScalar("routing", Hibernate.STRING)
                .addScalar("sale_fare", Hibernate.INTEGER)
                .addScalar("net_fare", Hibernate.INTEGER)
                .addScalar("tax", Hibernate.INTEGER)
                .addScalar("profit", Hibernate.INTEGER)
                .addScalar("owner", Hibernate.STRING)
                .addScalar("ref_no", Hibernate.STRING)
                .addScalar("ticket_from", Hibernate.STRING)
                .addScalar("ticket_type", Hibernate.STRING)
                .addScalar("Create_date", Hibernate.DATE)
                .addScalar("ticket_date", Hibernate.DATE)
                .addScalar("invoice_no", Hibernate.STRING)
                .list();
                    SimpleDateFormat dateformat = new SimpleDateFormat();
            dateformat.applyPattern("dd-MM-yyyy");
        for (Object[] B : QueryTicketList) {
            TicketSummary sum = new TicketSummary();
            no +=1;
            sum.setNo(no);
            sum.setSystemdate(new SimpleDateFormat("dd MMM yyyy hh:mm:ss", new Locale("us", "us")).format(thisDate));
            sum.setUsername(username);
            
            sum.setStartdate(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(startdate)));
            sum.setEnddate(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(enddate)));
            sum.setAir(util.ConvertString(B[0]));
            sum.setTicketno(util.ConvertString(B[1]));
            sum.setPassengername(util.ConvertString(B[2]));
            sum.setBillto(util.ConvertString(B[3]));
            sum.setRouting(util.ConvertString(B[4]));
            sum.setSalefare( B[5]== null ? 0:(Integer)B[5]);
            sum.setNetfare( B[6]== null ? 0:(Integer)B[6]);
            sum.setTax( B[7]== null ? 0:(Integer)B[7]);
            sum.setProfit( B[8]== null ? 0:(Integer)B[8]);
            sum.setOwner(util.ConvertString(B[9]));
            sum.setRefno(util.ConvertString(B[10]));
            sum.setFrom(setDisplayValueTicketFrom(ticketfrom));
            sum.setType(setDisplayValueTicketType(tickettype));
            if(B[13] != null)
            sum.setCreatedate(util.convertStringToDate(B[13].toString()));
            sum.setTicketdate("null".equals(String.valueOf(B[14])) ? "" : util.ConvertString(dateformat.format(util.convertStringToDate(String.valueOf(B[14])))));
//            sum.setTicketdate(util.convertStringToDate(util.ConvertString(B[14])));
            sum.setInvoiceno(util.ConvertString(B[15]));
            data.add(sum);
            System.out.println("sum data :");
            
        }
        
        session.close();
        this.sessionFactory.close();
        return data;
    }
    
    
    private String setDisplayValueTicketType(String tickettype){
        String input = tickettype;
        System.out.println("tickettype : "+tickettype);
        if("I".equalsIgnoreCase(tickettype)){
            input = "Inter";
        }else if("D".equalsIgnoreCase(tickettype)){
            input = "Domestic";
        }else if("".equalsIgnoreCase(tickettype)){
            input = "All";
        }
        return input;
    }
    
    private String setDisplayValueTicketFrom(String ticketfrom){
        String input = ticketfrom;
        System.out.println("ticketfrom : "+ticketfrom);
        if("C".equalsIgnoreCase(ticketfrom)){
            input = "In";
        }else if("O".equalsIgnoreCase(ticketfrom)){
            input = "Out";
        }else if("".equalsIgnoreCase(ticketfrom)){
            input = "All";
        }
        return input;
    }

    public String createTicketSummaryQuery(String ticketfrom, String tickettype, String startdate, String enddate, String billto, String passenger) {
        String query = " where ";
        int check = 0;
        System.out.println(startdate);
        System.out.println(enddate);
        if ((ticketfrom != null) && (!"".equalsIgnoreCase(ticketfrom))) {
            query += " ticket_from ='" + ticketfrom + "'";
            check = 1;
        }
        if ((tickettype != null) && (!"".equalsIgnoreCase(tickettype))) {
            if (check == 1) {query += " and"; }
            query += "  ticket_type = '" + tickettype + "'";
            query += "";
            check = 1;
        }

        if (((startdate != null) && (!"".equalsIgnoreCase(startdate))) && ((enddate != null) && (!"".equalsIgnoreCase(enddate)))) {
            if (check == 1) {query += " and"; }
            query += "  create_date between '" + startdate + "' and '" + enddate + "'";
            query += "";
            check = 1;
        }
        if ((billto != null) && (!"".equalsIgnoreCase(billto))) {
            if (check == 1) {query += " and"; }
            query += "  bill_code = '" + billto + "'";
            query += "";
            check = 1;
        }

        if (check == 0) {
            query = query.replaceAll("where", " ");
        }
        System.out.println("query : " + query);
        return query;
    }

}
