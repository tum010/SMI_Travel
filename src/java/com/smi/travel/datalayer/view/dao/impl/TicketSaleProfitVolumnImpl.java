/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.report.model.TicketSaleProfitVolumn;
import com.smi.travel.datalayer.view.dao.TicketSaleProfitVolumnDao;
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
 * @author Surachai
 */
public class TicketSaleProfitVolumnImpl implements TicketSaleProfitVolumnDao {

    private SessionFactory sessionFactory;
    private static final String[] month = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private static final String TicketSaleVolumn = "SELECT * FROM `airticket_saleprofit_volumn` where `YEAR(F.depart_date)` BETWEEN ";

    @Override
    public List getTicketProfitVolumn(String ticketFrom, String ticketType, String startDate, String endDate,String username) {
        Session session = this.sessionFactory.openSession();
        List data = new ArrayList();
        List<TicketSaleProfitVolumn> fromYear = new LinkedList<TicketSaleProfitVolumn>();
        List<TicketSaleProfitVolumn> toYear = new LinkedList<TicketSaleProfitVolumn>();
        UtilityFunction util = new UtilityFunction();
        
        List<Object[]> QuerySaleVolumn = session.createSQLQuery(TicketSaleVolumn + " '" + startDate + "' and '" + endDate +"' " )
                .addScalar("YEAR(F.depart_date)", Hibernate.STRING)
                .addScalar("MONTH(F.depart_date)", Hibernate.STRING)
                .addScalar("net_eco", Hibernate.STRING)
                .addScalar("net_bus", Hibernate.STRING)
                .addScalar("net_first", Hibernate.STRING)
                .addScalar("net_total", Hibernate.STRING)
                .addScalar("profit_eco", Hibernate.STRING)
                .addScalar("profit_bus", Hibernate.STRING)
                .addScalar("profit_first", Hibernate.STRING)
                .addScalar("profit_total", Hibernate.STRING)
                .list();

        for (Object[] B : QuerySaleVolumn) {
            TicketSaleProfitVolumn ticketVolumn = new TicketSaleProfitVolumn();
            ticketVolumn.setYear(util.ConvertString(B[0]));
            ticketVolumn.setMonth(util.ConvertString(B[1]));
            ticketVolumn.setNeteco(util.ConvertString(B[2]));
            ticketVolumn.setNetbus(util.ConvertString(B[3]));
            ticketVolumn.setNetfirst(util.ConvertString(B[4]));
            ticketVolumn.setNettotal(util.ConvertString(B[5]));
            ticketVolumn.setProfiteco(util.ConvertString(B[6]));
            ticketVolumn.setProfitbus(util.ConvertString(B[7]));
            ticketVolumn.setProfitfirst(util.ConvertString(B[8]));
            ticketVolumn.setProfittotal(util.ConvertString(B[9]));
            if(startDate.equalsIgnoreCase(util.ConvertString(B[0]))){
                fromYear.add(ticketVolumn);
            }else if(endDate.equalsIgnoreCase(util.ConvertString(B[0]))){
                toYear.add(ticketVolumn);
            }
        }
        int frommonth = 0 ;
        int tomonth = 0 ;
        
        for (int i = 1; i < 13; i++) {
            boolean checkmonthfrom = false;
            boolean checkmonthto = false;
            TicketSaleProfitVolumn ticketVolumn = new TicketSaleProfitVolumn();
                ticketVolumn.setPre2(startDate);
                ticketVolumn.setPre1(endDate);
                ticketVolumn.setMonth(mappingMonth(i));
                ticketVolumn.setTicketfrom(startDate);
                ticketVolumn.setTicketto(endDate);
                ticketVolumn.setSystemdate(util.ConvertString(new SimpleDateFormat("dd MMM yyyy hh:mm:ss", new Locale("us", "us")).format(new Date())));
                ticketVolumn.setUser(username);
            for(int j = 0 ; j < fromYear.size() ; j++){
                TicketSaleProfitVolumn ticketVolumnFrom = fromYear.get(j);
                frommonth = Integer.parseInt(fromYear.get(j).getMonth());
                if(i == frommonth){
                    ticketVolumn.setPre2_eco_value("".equalsIgnoreCase(ticketVolumnFrom.getProfiteco()) ? 0 : Integer.parseInt(ticketVolumnFrom.getProfiteco()));
                    ticketVolumn.setPre2_bus_value("".equalsIgnoreCase(ticketVolumnFrom.getProfitbus()) ? 0 : Integer.parseInt(ticketVolumnFrom.getProfitbus()));
                    ticketVolumn.setPre2_fst_value("".equalsIgnoreCase(ticketVolumnFrom.getProfitfirst()) ? 0 : Integer.parseInt(ticketVolumnFrom.getProfitfirst()));
                    ticketVolumn.setPre2_all_value("".equalsIgnoreCase(ticketVolumnFrom.getProfittotal()) ? 0 : Integer.parseInt(ticketVolumnFrom.getProfittotal()));
                    checkmonthfrom = true ;
                }
            }
            for(int k = 0 ; k < toYear.size() ; k++){
                TicketSaleProfitVolumn ticketVolumnTo = toYear.get(k);
                if(i == tomonth){
                    ticketVolumn.setPre1_eco_value("".equalsIgnoreCase(ticketVolumnTo.getProfiteco()) ? 0 : Integer.parseInt(ticketVolumnTo.getProfiteco()));
                    ticketVolumn.setPre1_bus_value("".equalsIgnoreCase(ticketVolumnTo.getProfitbus()) ? 0 : Integer.parseInt(ticketVolumnTo.getProfitbus()));
                    ticketVolumn.setPre1_fst_value("".equalsIgnoreCase(ticketVolumnTo.getProfitfirst()) ? 0 : Integer.parseInt(ticketVolumnTo.getProfitfirst()));
                    ticketVolumn.setPre1_all_value("".equalsIgnoreCase(ticketVolumnTo.getProfittotal()) ? 0 : Integer.parseInt(ticketVolumnTo.getProfittotal()));
                    checkmonthto = true ;
                }
            }
            if(!checkmonthfrom){
                ticketVolumn.setPre2_eco_value(0);
                ticketVolumn.setPre2_bus_value(0);
                ticketVolumn.setPre2_fst_value(0);
                ticketVolumn.setPre2_all_value(0);
            }
            if(!checkmonthto){
                ticketVolumn.setPre1_eco_value(0);
                ticketVolumn.setPre1_bus_value(0);
                ticketVolumn.setPre1_fst_value(0);
                ticketVolumn.setPre1_all_value(0);
            }
            int eco_end = ticketVolumn.getPre1_eco_value() ;
            int eco_from = ticketVolumn.getPre2_eco_value() ;
            int bus_end = ticketVolumn.getPre1_bus_value() ;
            int bus_from = ticketVolumn.getPre2_bus_value() ;
            int first_end = ticketVolumn.getPre1_fst_value() ;
            int first_from = ticketVolumn.getPre2_fst_value() ;
            int total_end = ticketVolumn.getPre1_all_value() ;
            int total_from = ticketVolumn.getPre2_all_value() ;
//            Growth Rate = (current - previous)/previous * 100
            int eco = (eco_end - eco_from == 0) ? 0 : ((eco_end - eco_from ) / eco_from ) * 100 ;
            int bus = (bus_end - bus_from == 0) ? 0 : ((bus_end - bus_from ) / bus_from ) * 100 ;
            int first = (first_end - first_from == 0) ? 0 : ((first_end - first_from ) / first_from ) * 100 ;
            int total = (total_end - total_from  == 0) ? 0 : ((total_end - total_from ) / total_from ) * 100 ;

            ticketVolumn.setEco_growth(eco);
            ticketVolumn.setBus_growth(bus);
            ticketVolumn.setFst_growth(first);
            ticketVolumn.setAll_growth(total);
            data.add(ticketVolumn);
        }
        session.close();
        this.sessionFactory.close();
        return data;
    }

    @Override
    public List getTicketSaleVolumn(String ticketFrom, String ticketType, String startDate, String endDate,String username) {
        Session session = this.sessionFactory.openSession();
        List data = new ArrayList();
        List<TicketSaleProfitVolumn> fromYear = new LinkedList<TicketSaleProfitVolumn>();
        List<TicketSaleProfitVolumn> toYear = new LinkedList<TicketSaleProfitVolumn>();
        UtilityFunction util = new UtilityFunction();
        
        List<Object[]> QuerySaleVolumn = session.createSQLQuery(TicketSaleVolumn + " '" + startDate + "' and '" + endDate +"' " )
                .addScalar("YEAR(F.depart_date)", Hibernate.STRING)
                .addScalar("MONTH(F.depart_date)", Hibernate.STRING)
                .addScalar("net_eco", Hibernate.STRING)
                .addScalar("net_bus", Hibernate.STRING)
                .addScalar("net_first", Hibernate.STRING)
                .addScalar("net_total", Hibernate.STRING)
                .addScalar("profit_eco", Hibernate.STRING)
                .addScalar("profit_bus", Hibernate.STRING)
                .addScalar("profit_first", Hibernate.STRING)
                .addScalar("profit_total", Hibernate.STRING)
                .list();

        for (Object[] B : QuerySaleVolumn) {
            TicketSaleProfitVolumn ticketVolumn = new TicketSaleProfitVolumn();
            ticketVolumn.setYear(util.ConvertString(B[0]));
            ticketVolumn.setMonth(util.ConvertString(B[1]));
            ticketVolumn.setNeteco(util.ConvertString(B[2]));
            ticketVolumn.setNetbus(util.ConvertString(B[3]));
            ticketVolumn.setNetfirst(util.ConvertString(B[4]));
            ticketVolumn.setNettotal(util.ConvertString(B[5]));
            ticketVolumn.setProfiteco(util.ConvertString(B[6]));
            ticketVolumn.setProfitbus(util.ConvertString(B[7]));
            ticketVolumn.setProfitfirst(util.ConvertString(B[8]));
            ticketVolumn.setProfittotal(util.ConvertString(B[9]));
            if(startDate.equalsIgnoreCase(util.ConvertString(B[0]))){
                fromYear.add(ticketVolumn);
            }else if(endDate.equalsIgnoreCase(util.ConvertString(B[0]))){
                toYear.add(ticketVolumn);
            }
        }
        
        int frommonth = 0 ;
        int tomonth = 0 ;
        
        for (int i = 1; i < 13; i++) {
            boolean checkmonthfrom = false;
            boolean checkmonthto = false;
            TicketSaleProfitVolumn ticketVolumn = new TicketSaleProfitVolumn();
                ticketVolumn.setPre2(startDate);
                ticketVolumn.setPre1(endDate);
                ticketVolumn.setMonth(mappingMonth(i));
                ticketVolumn.setTicketfrom(startDate);
                ticketVolumn.setTicketto(endDate);
                ticketVolumn.setSystemdate(util.ConvertString(new SimpleDateFormat("dd MMM yyyy hh:mm:ss", new Locale("us", "us")).format(new Date())));
                ticketVolumn.setUser(username);
            for(int j = 0 ; j < fromYear.size() ; j++){
                TicketSaleProfitVolumn ticketVolumnFrom = fromYear.get(j);
                frommonth = Integer.parseInt(fromYear.get(j).getMonth());
                if(i == frommonth){
                    ticketVolumn.setPre2_eco_value("".equalsIgnoreCase(ticketVolumnFrom.getNeteco()) ? 0 : Integer.parseInt(ticketVolumnFrom.getNeteco()));
                    ticketVolumn.setPre2_bus_value("".equalsIgnoreCase(ticketVolumnFrom.getNetbus()) ? 0 : Integer.parseInt(ticketVolumnFrom.getNetbus()));
                    ticketVolumn.setPre2_fst_value("".equalsIgnoreCase(ticketVolumnFrom.getNetfirst()) ? 0 : Integer.parseInt(ticketVolumnFrom.getNetfirst()));
                    ticketVolumn.setPre2_all_value("".equalsIgnoreCase(ticketVolumnFrom.getNettotal()) ? 0 : Integer.parseInt(ticketVolumnFrom.getNettotal()));
                    checkmonthfrom = true ;
                }
            }
            for(int k = 0 ; k < toYear.size() ; k++){
                TicketSaleProfitVolumn ticketVolumnTo = toYear.get(k);
                if(i == tomonth){
                    ticketVolumn.setPre1_eco_value("".equalsIgnoreCase(ticketVolumnTo.getNeteco()) ? 0 : Integer.parseInt(ticketVolumnTo.getNeteco()));
                    ticketVolumn.setPre1_bus_value("".equalsIgnoreCase(ticketVolumnTo.getNetbus()) ? 0 : Integer.parseInt(ticketVolumnTo.getNetbus()));
                    ticketVolumn.setPre1_fst_value("".equalsIgnoreCase(ticketVolumnTo.getNetfirst()) ? 0 : Integer.parseInt(ticketVolumnTo.getNetfirst()));
                    ticketVolumn.setPre1_all_value("".equalsIgnoreCase(ticketVolumnTo.getNettotal()) ? 0 : Integer.parseInt(ticketVolumnTo.getNettotal()));
                    checkmonthto = true ;
                }
            }
            if(!checkmonthfrom){
                ticketVolumn.setPre2_eco_value(0);
                ticketVolumn.setPre2_bus_value(0);
                ticketVolumn.setPre2_fst_value(0);
                ticketVolumn.setPre2_all_value(0);
            }
            if(!checkmonthto){
                ticketVolumn.setPre1_eco_value(0);
                ticketVolumn.setPre1_bus_value(0);
                ticketVolumn.setPre1_fst_value(0);
                ticketVolumn.setPre1_all_value(0);
            }
            int eco_end = ticketVolumn.getPre1_eco_value() ;
            int eco_from = ticketVolumn.getPre2_eco_value() ;
            int bus_end = ticketVolumn.getPre1_bus_value() ;
            int bus_from = ticketVolumn.getPre2_bus_value() ;
            int first_end = ticketVolumn.getPre1_fst_value() ;
            int first_from = ticketVolumn.getPre2_fst_value() ;
            int total_end = ticketVolumn.getPre1_all_value() ;
            int total_from = ticketVolumn.getPre2_all_value() ;
//            Growth Rate = (current - previous)/previous * 100
            int eco = (eco_end - eco_from == 0) ? 0 : ((eco_end - eco_from ) / eco_from ) * 100 ;
            int bus = (bus_end - bus_from == 0) ? 0 : ((bus_end - bus_from ) / bus_from ) * 100 ;
            int first = (first_end - first_from == 0) ? 0 : ((first_end - first_from ) / first_from ) * 100 ;
            int total = (total_end - total_from  == 0) ? 0 : ((total_end - total_from ) / total_from ) * 100 ;

            ticketVolumn.setEco_growth(eco);
            ticketVolumn.setBus_growth(bus);
            ticketVolumn.setFst_growth(first);
            ticketVolumn.setAll_growth(total);
            data.add(ticketVolumn);
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

    public String mappingMonth(int monthinput) {
        String value = "";
        switch (monthinput) {
            case 1:
                value = month[0];
                break;
            case 2:
                value = month[1];
                break;
            case 3:
                value = month[2];
                break;
            case 4:
                value = month[3];
                break;
            case 5:
                value = month[4];
                break;
            case 6:
                value = month[5];
                break;
            case 7:
                value = month[6];
                break;
            case 8:
                value = month[7];
                break;
            case 9:
                value = month[8];
                break;
            case 10:
                value = month[9];
                break;
            case 11:
                value = month[10];
                break;
            case 12:
                value = month[11];
                break;
            default:

        }
        return value;
    }
   
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
