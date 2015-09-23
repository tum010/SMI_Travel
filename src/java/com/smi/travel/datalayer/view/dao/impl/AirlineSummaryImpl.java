/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.report.model.AirlineSummary;
import com.smi.travel.datalayer.report.model.StaffSummary;
import com.smi.travel.datalayer.view.dao.AirlineSummaryDao;
import com.smi.travel.datalayer.view.entity.SummaryAirline;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;
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
public class AirlineSummaryImpl implements AirlineSummaryDao {

    private SessionFactory sessionFactory;
    private static final String AirLineSumQuery = "select at.air AS air "
            + ",count(at.ref_no) AS passenger "
            + ",sum(at.net_fare) AS net "
            + ",sum(at.sale_fare) AS selling "
            + ",sum(at.tax) AS tax "
            + ",(sum(at.sale_fare) - sum(at.net_fare)) AS profit  "
            + "from airticket_ticket at  ";

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List getAirlineSummary(String ticketfrom, String tickettype, String startdate, String enddate, String username) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        Date thisDate = new Date();
        List data = new ArrayList();
        String Query = AirLineSumQuery;
        Query += CreateAirlineSummaryQuery(ticketfrom, tickettype, startdate, enddate);
        System.out.println("Query : " + Query);

        List<Object[]> QueryStaffList = session.createSQLQuery(Query)
                .addScalar("air", Hibernate.STRING)
                .addScalar("passenger", Hibernate.INTEGER)
                .addScalar("net", Hibernate.INTEGER)
                .addScalar("selling", Hibernate.INTEGER)
                .addScalar("tax", Hibernate.INTEGER)
                .addScalar("profit", Hibernate.INTEGER)
                .list();
 
        
        for (Object[] B : QueryStaffList) {
            AirlineSummary sum = new AirlineSummary();
            System.out.println("sum data :");
            sum.setAir(util.ConvertString(B[0]));
            sum.setTicketnum(B[1]== null ? 0:(Integer)B[1]);
            sum.setFare(B[2]== null ? 0:(Integer)B[2]);
            sum.setSale(B[3]== null ? 0:(Integer)B[3]);
            sum.setTax(B[4]== null ? 0:(Integer)B[4]);
            sum.setProfit(B[5]== null ? 0:(Integer)B[5]);
            sum.setSystemdate(new SimpleDateFormat("dd MMM yyyy hh:mm:ss", new Locale("us", "us")).format(thisDate));
            sum.setUsername(username);
            
            sum.setStartdate(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(startdate)));
            sum.setEnddate(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(enddate)));
            sum.setFrom(ticketfrom);
            sum.setType(tickettype);

            data.add(sum);
        }
        
        session.close();
        this.sessionFactory.close();
        return data;
    }

    
    public String CreateAirlineSummaryQuery(String ticketfrom, String tickettype, String startdate, String enddate) {
        String Query = "Where ";
         int check =0;
        if((ticketfrom != null) && (!"".equalsIgnoreCase(ticketfrom))) {
            check =1;
            Query += "  `at`.ticket_from = '"+ticketfrom+"' ";
        }
        if((tickettype != null) && (!"".equalsIgnoreCase(tickettype))) {
            if(check == 1){Query += " and ";}
            Query += "  `at`.ticket_type = '"+tickettype+"' ";
            check =1;
        }
        
        if (((startdate != null) && (!"".equalsIgnoreCase(startdate))) && ((enddate != null) && (!"".equalsIgnoreCase(enddate)))) {
            if(check == 1){Query += " and ";}
            Query += " at.create_date between '"+startdate+"' and '"+enddate+"' ";
            check =1;
        }
        if(check == 0){
           Query = Query.replaceAll("Where", " ");
        }
        Query += " group by at.air;";
        return Query;
    }

    @Override
    public List listSummaryAirline() {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList<SummaryAirline>();
        String query = "SELECT * FROM `ticket_summary_rounting_detail`";
        
        List<Object[]> QueryList =  session.createSQLQuery(query)
                .addScalar("routing",Hibernate.STRING)
                .addScalar("pax",Hibernate.INTEGER)
                .addScalar("netsales",Hibernate.BIG_DECIMAL)
                .addScalar("tax",Hibernate.BIG_DECIMAL)
                .addScalar("ins",Hibernate.BIG_DECIMAL)
                .addScalar("comms",Hibernate.BIG_DECIMAL)
                .addScalar("amountwendy",Hibernate.BIG_DECIMAL)
                .addScalar("amountinbound",Hibernate.BIG_DECIMAL)
                .addScalar("diff",Hibernate.BIG_DECIMAL)             
                .list();
        for (Object[] B : QueryList) {
            SummaryAirline summaryAirline = new SummaryAirline();
            summaryAirline.setRouting(util.ConvertString(B[0]));
            
            summaryAirline.setInvoicedatefrom(new Date());
            summaryAirline.setInvoicedateto(new Date());
            summaryAirline.setPrinton("");
            summaryAirline.setPrintby("");
            summaryAirline.setRoutingdetail("");
            summaryAirline.setTyperouting("");
            summaryAirline.setPassenger("");
            summaryAirline.setAir("");
            summaryAirline.setSalestaff("");
            summaryAirline.setAgentname("");
            summaryAirline.setDepartment("");
            summaryAirline.setTermpay("");
            
            if(B[1] != null && !"".equals(B[1])){
                summaryAirline.setPax((Integer) B[1]);
            }else{
                summaryAirline.setPax(0);
            }
            if(B[2] != null && !"".equals(B[2])){
                summaryAirline.setNetsale((BigDecimal) B[2]);
            }else{
                summaryAirline.setNetsale(new BigDecimal(0.0));
            }
            if(B[3] != null && !"".equals(B[3])){
                summaryAirline.setTax((BigDecimal) B[3]);
            }else{
               summaryAirline.setTax(new BigDecimal(0.0));
            }
            if(B[4] != null && !"".equals(B[4])){
                summaryAirline.setIns((BigDecimal) B[4]);
            }else{
                summaryAirline.setIns(new BigDecimal(0.0));
            }
            if(B[5] != null && !"".equals(B[5])){
                summaryAirline.setComms((BigDecimal) B[5]);
            }else{
                summaryAirline.setComms(new BigDecimal(0.0));
            }
            if(B[6] != null && !"".equals(B[6])){
                summaryAirline.setAmountwendy((BigDecimal) B[6]);
            }else{
                summaryAirline.setAmountwendy(new BigDecimal(0.0));
            }
            if(B[7] != null && !"".equals(B[7])){
                summaryAirline.setAmountinbound((BigDecimal) B[7]);
            }else{
                summaryAirline.setAmountinbound(new BigDecimal(0.0));
            }
            if(B[8] != null && !"".equals(B[8])){
                summaryAirline.setDiff((BigDecimal) B[8]);
            }else{
                summaryAirline.setDiff(new BigDecimal(0.0));
            }
            data.add(summaryAirline);
        }
        return data;
    }
}
