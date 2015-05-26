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
    private static final String TicketSaleVolumn = "";
    private static final String TicketProfitVolumn = "select month(`tsl`.`ticket_date`) AS `mth` ,GET_SUM_PROFIT_BY_MONTH(sysdate(),2,1,month(`tsl`.`ticket_date`),`tsl`.`ticket_type`,tsl.ticket_from ) AS `pre2_eco_cost` ,GET_SUM_PROFIT_BY_MONTH(sysdate(),1,1,month(`tsl`.`ticket_date`),`tsl`.`ticket_type`,tsl.ticket_from) AS `pre1_eco_cost` ,GET_SUM_PROFIT_BY_MONTH(sysdate(),0,1,month(`tsl`.`ticket_date`),`tsl`.`ticket_type`,tsl.ticket_from) AS `curr_eco_cost` ,	concat( 		ifnull(((( 			GET_SUM_PROFIT_BY_MONTH(sysdate(),1,1,month(`tsl`.`ticket_date`),`tsl`.`ticket_type`,tsl.ticket_from) 			-  			GET_SUM_PROFIT_BY_MONTH(sysdate(),2,1,month(`tsl`.`ticket_date`),`tsl`.`ticket_type`,tsl.ticket_from) 		) /  		GET_SUM_PROFIT_BY_MONTH(sysdate(),2,1,month(`tsl`.`ticket_date`),`tsl`.`ticket_type`,tsl.ticket_from) 		) * 100),0) 		,'/' 		,ifnull(((( 			GET_SUM_PROFIT_BY_MONTH(sysdate(),0,1,month(`tsl`.`ticket_date`),`tsl`.`ticket_type`,tsl.ticket_from) 			-  			GET_SUM_PROFIT_BY_MONTH(sysdate(),1,1,month(`tsl`.`ticket_date`),`tsl`.`ticket_type`,tsl.ticket_from) 		) /  		GET_SUM_PROFIT_BY_MONTH(sysdate(),1,1,month(`tsl`.`ticket_date`),`tsl`.`ticket_type`,tsl.ticket_from) 		) * 100),0)) AS `eco_growth` ,GET_SUM_PROFIT_BY_MONTH(sysdate(),2,2,month(`tsl`.`ticket_date`),`tsl`.`ticket_type`,tsl.ticket_from) AS `pre2_bus_cost` ,GET_SUM_PROFIT_BY_MONTH(sysdate(),1,2,month(`tsl`.`ticket_date`),`tsl`.`ticket_type`,tsl.ticket_from) AS `pre1_bus_cost` ,GET_SUM_PROFIT_BY_MONTH(sysdate(),0,2,month(`tsl`.`ticket_date`),`tsl`.`ticket_type`,tsl.ticket_from) AS `curr_bus_cost` ,	concat( 		ifnull(((( 			GET_SUM_PROFIT_BY_MONTH(sysdate(),1,2,month(`tsl`.`ticket_date`),`tsl`.`ticket_type`,tsl.ticket_from) 			-  			GET_SUM_PROFIT_BY_MONTH(sysdate(),2,2,month(`tsl`.`ticket_date`),`tsl`.`ticket_type`,tsl.ticket_from) 		) /  		GET_SUM_PROFIT_BY_MONTH(sysdate(),2,2,month(`tsl`.`ticket_date`),`tsl`.`ticket_type`,tsl.ticket_from) 		) * 100),0) 		,'/' 		,ifnull(((( 			GET_SUM_PROFIT_BY_MONTH(sysdate(),0,2,month(`tsl`.`ticket_date`),`tsl`.`ticket_type`,tsl.ticket_from) 			-  			GET_SUM_PROFIT_BY_MONTH(sysdate(),1,2,month(`tsl`.`ticket_date`),`tsl`.`ticket_type`,tsl.ticket_from) 		) /  		GET_SUM_PROFIT_BY_MONTH(sysdate(),1,2,month(`tsl`.`ticket_date`),`tsl`.`ticket_type`,tsl.ticket_from) 		) * 100),0)) AS `bus_growth` ,GET_SUM_PROFIT_BY_MONTH(sysdate(),2,3,month(`tsl`.`ticket_date`),`tsl`.`ticket_type`,tsl.ticket_from) AS `pre2_fst_cost` ,GET_SUM_PROFIT_BY_MONTH(sysdate(),1,3,month(`tsl`.`ticket_date`),`tsl`.`ticket_type`,tsl.ticket_from) AS `pre1_fst_cost` ,GET_SUM_PROFIT_BY_MONTH(sysdate(),0,3,month(`tsl`.`ticket_date`),`tsl`.`ticket_type`,tsl.ticket_from) AS `curr_fst_cost` ,	concat( 		ifnull(((( 			GET_SUM_PROFIT_BY_MONTH(sysdate(),1,3,month(`tsl`.`ticket_date`),`tsl`.`ticket_type`,tsl.ticket_from) 			-  			GET_SUM_PROFIT_BY_MONTH(sysdate(),2,3,month(`tsl`.`ticket_date`),`tsl`.`ticket_type`,tsl.ticket_from) 		) /  		GET_SUM_PROFIT_BY_MONTH(sysdate(),2,3,month(`tsl`.`ticket_date`),`tsl`.`ticket_type`,tsl.ticket_from) 		) * 100),0) 		,'/' 		,ifnull(((( 			GET_SUM_PROFIT_BY_MONTH(sysdate(),0,3,month(`tsl`.`ticket_date`),`tsl`.`ticket_type`,tsl.ticket_from) 			-  			GET_SUM_PROFIT_BY_MONTH(sysdate(),1,3,month(`tsl`.`ticket_date`),`tsl`.`ticket_type`,tsl.ticket_from) 		) /  		GET_SUM_PROFIT_BY_MONTH(sysdate(),1,3,month(`tsl`.`ticket_date`),`tsl`.`ticket_type`,tsl.ticket_from) 		) * 100),0)) AS `fst_growth` ,GET_ALL_PROFIT_BY_MONTH(sysdate(),2,month(`tsl`.`ticket_date`),`tsl`.`ticket_type`,tsl.ticket_from) AS `pre2_all_cost` ,GET_ALL_PROFIT_BY_MONTH(sysdate(),1,month(`tsl`.`ticket_date`),`tsl`.`ticket_type`,tsl.ticket_from) AS `pre1_all_cost` ,GET_ALL_PROFIT_BY_MONTH(sysdate(),0,month(`tsl`.`ticket_date`),`tsl`.`ticket_type`,tsl.ticket_from) AS `curr_all_cost` ,	concat( 		ifnull(((( 			GET_ALL_PROFIT_BY_MONTH(sysdate(),1,month(`tsl`.`ticket_date`),`tsl`.`ticket_type`,tsl.ticket_from) 			-  			GET_ALL_PROFIT_BY_MONTH(sysdate(),2,month(`tsl`.`ticket_date`),`tsl`.`ticket_type`,tsl.ticket_from) 		) /  		GET_ALL_PROFIT_BY_MONTH(sysdate(),2,month(`tsl`.`ticket_date`),`tsl`.`ticket_type`,tsl.ticket_from) 		) * 100),0) 		,'/' 		,ifnull(((( 			GET_ALL_PROFIT_BY_MONTH(sysdate(),0,month(`tsl`.`ticket_date`),`tsl`.`ticket_type`,tsl.ticket_from) 			-  			GET_ALL_PROFIT_BY_MONTH(sysdate(),1,month(`tsl`.`ticket_date`),`tsl`.`ticket_type`,tsl.ticket_from) 		) /  		GET_ALL_PROFIT_BY_MONTH(sysdate(),1,month(`tsl`.`ticket_date`),`tsl`.`ticket_type`,tsl.ticket_from) 		) * 100),0)) AS `all_growth` ,`tsl`.`ticket_type` ,`tsl`.ticket_from from `ticket_sale_list` `tsl`  group by month(`tsl`.`ticket_date`) ,`tsl`.`ticket_type` ,`tsl`.ticket_from order by month(`tsl`.`ticket_date`)";

    @Override
    public List getTicketSaleVolumn(String ticketFrom, String ticketType, String startDate, String endDate) {
        Session session = this.sessionFactory.openSession();
        List data = new ArrayList();
        List<TicketSaleProfitVolumn> temp = new LinkedList<TicketSaleProfitVolumn>();
        UtilityFunction util = new UtilityFunction();

        List<Object[]> QuerySaleVolumn = session.createSQLQuery(TicketSaleVolumn)
                .addScalar("pre2_eco_cost", Hibernate.INTEGER)
                .addScalar("pre1_eco_cost", Hibernate.INTEGER)
                .addScalar("curr_eco_cost", Hibernate.INTEGER)
                .addScalar("pre2_bus_cost", Hibernate.INTEGER)
                .addScalar("pre1_bus_cost", Hibernate.INTEGER)
                .addScalar("curr_bus_cost", Hibernate.INTEGER)
                .addScalar("pre2_fst_cost", Hibernate.INTEGER)
                .addScalar("pre1_fst_cost", Hibernate.INTEGER)
                .addScalar("curr_fst_cost", Hibernate.INTEGER)
                .addScalar("pre2_all_cost", Hibernate.INTEGER)
                .addScalar("pre1_all_cost", Hibernate.INTEGER)
                .addScalar("curr_all_cost", Hibernate.INTEGER)
                .addScalar("eco_growth", Hibernate.STRING)
                .addScalar("bus_growth", Hibernate.STRING)
                .addScalar("fst_growth", Hibernate.STRING)
                .addScalar("all_growth", Hibernate.STRING)
                .addScalar("mth", Hibernate.INTEGER)
                .list();

        for (Object[] B : QuerySaleVolumn) {
            if (B[16] != null) {
                TicketSaleProfitVolumn TicketVolumn = new TicketSaleProfitVolumn();
                TicketVolumn.setPre2_eco_value(B[0] == null ? 0 : (Integer) B[0]);
                TicketVolumn.setPre1_eco_value(B[1] == null ? 0 : (Integer) B[1]);
                TicketVolumn.setCurr_eco_value(B[2] == null ? 0 : (Integer) B[2]);

                TicketVolumn.setPre2_bus_value(B[3] == null ? 0 : (Integer) B[3]);
                TicketVolumn.setPre1_bus_value(B[4] == null ? 0 : (Integer) B[4]);
                TicketVolumn.setCurr_bus_value(B[5] == null ? 0 : (Integer) B[5]);

                TicketVolumn.setPre2_fst_value(B[6] == null ? 0 : (Integer) B[6]);
                TicketVolumn.setPre1_fst_value(B[7] == null ? 0 : (Integer) B[7]);
                TicketVolumn.setCurr_fst_value(B[8] == null ? 0 : (Integer) B[8]);

                TicketVolumn.setPre2_all_value(B[9] == null ? 0 : (Integer) B[9]);
                TicketVolumn.setPre1_all_value(B[10] == null ? 0 : (Integer) B[10]);
                TicketVolumn.setCurr_all_value(B[11] == null ? 0 : (Integer) B[11]);

                TicketVolumn.setEco_growth(util.ConvertString(B[12]));
                TicketVolumn.setBus_growth(util.ConvertString(B[13]));
                TicketVolumn.setFst_growth(util.ConvertString(B[14]));
                TicketVolumn.setAll_growth(util.ConvertString(B[15]));
                TicketVolumn.setMonth(B[16] == null ? "0" : String.valueOf(B[12]));
                temp.add(TicketVolumn);
            }

        }
        for (int i = 0; i < 12; i++) {
            int check = 0;
            for (int j = 0; j < temp.size(); j++) {
                if (i == Integer.parseInt(temp.get(j).getMonth())) {
                    temp.get(j).setMonth(mappingMonth(Integer.parseInt(temp.get(j).getMonth())));
                    data.add(temp.get(j));
                    check = 1;
                    break;
                }
            }
            if (check == 0) {
                data.add(SetBlankTicketVolumn(i));
            }
        }
        session.close();
        this.sessionFactory.close();
        return data;
    }

    @Override
    public List getTicketProfitVolumn(String ticketFrom, String ticketType, String startDate, String endDate) {
        Session session = this.sessionFactory.openSession();
        List data = new ArrayList();
        UtilityFunction util = new UtilityFunction();
        List<TicketSaleProfitVolumn> temp = new LinkedList<TicketSaleProfitVolumn>();
        List<Object[]> QueryProfitVolumn = session.createSQLQuery(TicketProfitVolumn)
                .addScalar("pre2_eco_cost", Hibernate.INTEGER)
                .addScalar("pre1_eco_cost", Hibernate.INTEGER)
                .addScalar("curr_eco_cost", Hibernate.INTEGER)
                .addScalar("pre2_bus_cost", Hibernate.INTEGER)
                .addScalar("pre1_bus_cost", Hibernate.INTEGER)
                .addScalar("curr_bus_cost", Hibernate.INTEGER)
                .addScalar("pre2_fst_cost", Hibernate.INTEGER)
                .addScalar("pre1_fst_cost", Hibernate.INTEGER)
                .addScalar("curr_fst_cost", Hibernate.INTEGER)
                .addScalar("pre2_all_cost", Hibernate.INTEGER)
                .addScalar("pre1_all_cost", Hibernate.INTEGER)
                .addScalar("curr_all_cost", Hibernate.INTEGER)
                .addScalar("eco_growth", Hibernate.STRING)
                .addScalar("bus_growth", Hibernate.STRING)
                .addScalar("fst_growth", Hibernate.STRING)
                .addScalar("all_growth", Hibernate.STRING)
                .addScalar("mth", Hibernate.STRING)
                .list();
        for (Object[] B : QueryProfitVolumn) {
            if (B[16] != null) {
                TicketSaleProfitVolumn TicketVolumn = new TicketSaleProfitVolumn();
                TicketVolumn.setPre2_eco_value(B[0] == null ? 0 : (Integer) B[0]);
                TicketVolumn.setPre1_eco_value(B[1] == null ? 0 : (Integer) B[1]);
                TicketVolumn.setCurr_eco_value(B[2] == null ? 0 : (Integer) B[2]);

                TicketVolumn.setPre2_bus_value(B[3] == null ? 0 : (Integer) B[3]);
                TicketVolumn.setPre1_bus_value(B[4] == null ? 0 : (Integer) B[4]);
                TicketVolumn.setCurr_bus_value(B[5] == null ? 0 : (Integer) B[5]);

                TicketVolumn.setPre2_fst_value(B[6] == null ? 0 : (Integer) B[6]);
                TicketVolumn.setPre1_fst_value(B[7] == null ? 0 : (Integer) B[7]);
                TicketVolumn.setCurr_fst_value(B[8] == null ? 0 : (Integer) B[8]);

                TicketVolumn.setPre2_all_value(B[9] == null ? 0 : (Integer) B[9]);
                TicketVolumn.setPre1_all_value(B[10] == null ? 0 : (Integer) B[10]);
                TicketVolumn.setCurr_all_value(B[11] == null ? 0 : (Integer) B[11]);

                TicketVolumn.setEco_growth(util.ConvertString(B[12]));
                TicketVolumn.setBus_growth(util.ConvertString(B[13]));
                TicketVolumn.setFst_growth(util.ConvertString(B[14]));
                TicketVolumn.setAll_growth(util.ConvertString(B[15]));
                TicketVolumn.setMonth(B[16] == null ? "0" : String.valueOf(B[16]));
                System.out.println("B16 : "+String.valueOf(B[16]));
                temp.add(TicketVolumn);
            }

        }
        for (int i = 0; i < 12; i++) {
            int check = 0;
            for (int j = 0; j < temp.size(); j++) {
                System.out.println("temp.get(j).getMonth() : "+temp.get(j).getMonth());
                if (i == Integer.parseInt(temp.get(j).getMonth())) {
                    temp.get(j).setMonth(temp.get(j).getMonth());
                    data.add(temp.get(j));
                    check = 1;
                    break;
                }
            }
            if (check == 0) {
                data.add(SetBlankTicketVolumn(i));
            }
        }

        session.close();
        this.sessionFactory.close();
        return data;
    }

    public TicketSaleProfitVolumn SetBlankTicketVolumn(int month) {
        String DefaultGrowth = "0.00/0.00";
        TicketSaleProfitVolumn TicketVolumn = new TicketSaleProfitVolumn();
        TicketVolumn.setPre2_eco_value(0);
        TicketVolumn.setPre1_eco_value(0);
        TicketVolumn.setCurr_eco_value(0);

        TicketVolumn.setPre2_bus_value(0);
        TicketVolumn.setPre1_bus_value(0);
        TicketVolumn.setCurr_bus_value(0);

        TicketVolumn.setPre2_fst_value(0);
        TicketVolumn.setPre1_fst_value(0);
        TicketVolumn.setCurr_fst_value(0);

        TicketVolumn.setPre2_all_value(0);
        TicketVolumn.setPre1_all_value(0);
        TicketVolumn.setCurr_all_value(0);

        TicketVolumn.setEco_growth(DefaultGrowth);
        TicketVolumn.setBus_growth(DefaultGrowth);
        TicketVolumn.setFst_growth(DefaultGrowth);
        TicketVolumn.setAll_growth(DefaultGrowth);
        TicketVolumn.setMonth(mappingMonth(month));
        return TicketVolumn;
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
