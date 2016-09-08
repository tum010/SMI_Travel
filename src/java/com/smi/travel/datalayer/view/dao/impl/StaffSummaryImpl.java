/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.report.model.StaffSummary;
import com.smi.travel.datalayer.view.dao.StaffSummaryDao;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
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
public class StaffSummaryImpl implements StaffSummaryDao {

    private SessionFactory sessionFactory;
    private static final String StaffQuery = "select st.name AS staff "
            + ",count(at.ref_no) AS passenger "
            + ",sum(at.net_fare) AS net "
            + ",sum(at.sale_fare) AS selling "
            + ",sum(at.tax) AS tax "
            + ",(sum(at.sale_fare) - sum(at.net_fare)) AS profit  "
            + ", sum(at.refund) AS refund "
            + ",AT.booktype as booktype "
            + "from staff st "
            + ", airticket_ticket at "
            + "where st.username = at.owner ";
 

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List getStaffSummary(String ticketfrom, String tickettype, String startdate, String enddate, String username, String department) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        Date thisDate = new Date();
        List data = new ArrayList();
        String Query = StaffQuery;
        Query += CreateStaffSummaryQuery(ticketfrom, tickettype, startdate, enddate, department);
        System.out.println("Query : " + Query);

        

        List<Object[]> QueryStaffList = session.createSQLQuery(Query)
                .addScalar("staff", Hibernate.STRING)
                .addScalar("passenger", Hibernate.INTEGER)
                .addScalar("net", Hibernate.STRING)
                .addScalar("selling", Hibernate.STRING)
                .addScalar("tax", Hibernate.STRING)
                .addScalar("profit", Hibernate.STRING)
                .addScalar("refund", Hibernate.STRING)
                .list();
        for (Object[] B : QueryStaffList) {
            StaffSummary sum = new StaffSummary();
            sum.setStaff(util.ConvertString(B[0]));
            sum.setTicketnum(B[1]== null ? 0:(Integer)B[1]);
            
            if(B[2] != null){
                BigDecimal fare = new BigDecimal(util.ConvertString(B[2]));
                sum.setFare(fare);
            }else{
                sum.setFare(new BigDecimal("0.0"));
            }
            if(B[3] != null){
                BigDecimal sale = new BigDecimal(util.ConvertString(B[3]));
                sum.setSale(sale);
            }else{
                sum.setSale(new BigDecimal("0.0"));
            }
            if(B[4] != null){
                BigDecimal tax = new BigDecimal(util.ConvertString(B[4]));
                sum.setTax(tax);
            }else{
                sum.setTax(new BigDecimal("0.0"));
            }
            if(B[5] != null){
                BigDecimal profit = new BigDecimal(util.ConvertString(B[5]));
                sum.setProfit(profit);
            }else{
                sum.setProfit(new BigDecimal("0.0"));
            }
//            sum.setFare(B[2]== null ? 0:(Integer)B[2]);
//            sum.setSale(B[3]== null ? 0:(Integer)B[3]);
//            sum.setTax(B[4]== null ? 0:(Integer)B[4]);
//            sum.setProfit(B[5]== null ? 0:(Integer)B[5]);
            sum.setSystemdate(new SimpleDateFormat("dd MMM yyyy hh:mm:ss", new Locale("us", "us")).format(thisDate));
            sum.setUsername(username);
            sum.setStartdate(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(startdate)));
            sum.setEnddate(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(enddate)));
            sum.setFrom(ticketfrom);
            sum.setType(tickettype);
            sum.setRefund((("null".equals(String.valueOf(B[6])) ? "0" : String.valueOf(B[6]))));
            data.add(sum);
        }

        session.close();
        this.sessionFactory.close();
        return data;
    }

    public String CreateStaffSummaryQuery(String ticketfrom, String tickettype, String startdate, String enddate, String department) {
        String Query = "";
        if((ticketfrom != null) && (!"".equalsIgnoreCase(ticketfrom))) {
            Query += " and `at`.ticket_from = '"+ticketfrom+"' ";
        }
        if((tickettype != null) && (!"".equalsIgnoreCase(tickettype))) {
            Query += " and `at`.ticket_type = '"+tickettype+"' ";
        }
        
        if (((startdate != null) && (!"".equalsIgnoreCase(startdate))) && ((enddate != null) && (!"".equalsIgnoreCase(enddate)))) {
            Query += "and at.create_date between '"+startdate+"' and '"+enddate+"' ";
        }
        if (((department != null) && (!"".equalsIgnoreCase(department)))) {
            Query += "and at.booktype = '" + department + "' ";
        }
        Query += " group by st.name;";
        return Query;
    }
}
