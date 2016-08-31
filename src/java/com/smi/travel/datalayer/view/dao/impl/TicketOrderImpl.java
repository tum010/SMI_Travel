/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.report.model.TicketOrder;
import com.smi.travel.datalayer.report.model.TicketOrderFlight;
import com.smi.travel.datalayer.report.model.TicketOrderPassenger;
import com.smi.travel.datalayer.view.dao.TicketOrderDao;
import com.smi.travel.datalayer.view.entity.TicketOrderDescription;
import com.smi.travel.datalayer.view.entity.TicketOrderInfo;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Surachai
 */
public class TicketOrderImpl implements TicketOrderDao{
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public TicketOrder getTicketOrder(String refno,String pnrID) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        TicketOrder ticket = new TicketOrder();
//        int price =0;
//        int priceTax = 0;
//        int cost =0;
//        int costtax =0;
        BigDecimal price = new BigDecimal(BigInteger.ZERO);
        BigDecimal priceTax = new BigDecimal(BigInteger.ZERO);
        BigDecimal cost = new BigDecimal(BigInteger.ZERO);
        BigDecimal costtax = new BigDecimal(BigInteger.ZERO);
         List<Object[]> QueryTicketInfo = session.createSQLQuery(" SELECT concat( substr(`mt`.`Reference No`, 1, 2), '-', substr(`mt`.`Reference No`, 3, 6)) AS `ref_no`, `ap`.`first_name` AS `first_name`, `GET_LEADER_NAME` (`mt`.`id`) AS `leader_name`, `ab`.`issue_date` AS `bill_date`, `ab`.`is_pickup` AS `pickup`, `bb`.`bill_name` AS `company_name`, `bb`.`bill_address` AS `company_address`, '' AS `tel`, ( CASE WHEN (`ar`.`sub_pnr` IS NOT NULL) THEN concat( `ar`.`pnr`, ',', `ar`.`sub_pnr` ) ELSE `ar`.`pnr` END ) AS `pnr`, group_concat(`inv`.`inv_no` SEPARATOR ',') AS `inv`, ( CASE WHEN (`ap`.`passenger_type` = '1') THEN `af`.`ad_price` WHEN (`ap`.`passenger_type` = '2') THEN `af`.`ch_price` WHEN (`ap`.`passenger_type` = '3') THEN `af`.`in_price` ELSE 0 END ) AS `sell`, ( CASE WHEN (`ap`.`passenger_type` = '1') THEN `af`.`ad_tax` WHEN (`ap`.`passenger_type` = '2') THEN `af`.`ch_tax` WHEN (`ap`.`passenger_type` = '3') THEN `af`.`in_tax` ELSE 0 END ) AS `sell_tax`, ( CASE WHEN (`ap`.`passenger_type` = '1') THEN `af`.`ad_cost` WHEN (`ap`.`passenger_type` = '2') THEN `af`.`ch_cost` WHEN (`ap`.`passenger_type` = '3') THEN `af`.`in_cost` ELSE 0 END ) AS `net`, ifnull(( CASE WHEN (`ap`.`passenger_type` = '1') THEN `af`.`ad_tax_cost` WHEN (`ap`.`passenger_type` = '2') THEN `af`.`ch_tax_cost` WHEN (`ap`.`passenger_type` = '3') THEN `af`.`in_tax_cost` ELSE 0 END ), 0 ) AS `net_tax`, `mat`.`name` AS `term_of_payment`, `ab`.`remark` AS `remark`, `st`.`name` AS `prepare_by`, `aa`.`pnr_id` AS `pnr_id`, `st2`.`name` AS `issue_by` FROM (((((((((((((( `master` `mt` JOIN `airticket_booking` `ab` ON ((`mt`.`id` = `ab`.`master_id`))) JOIN `staff` `st` ON ((`ab`.`owner_by` = `st`.`id`))) JOIN `airticket_pnr` `ar` ON (( `ab`.`id` = `ar`.`booking_id` ))) JOIN `airticket_airline` `aa` ON ((`ar`.`id` = `aa`.`pnr_id`))) JOIN `airticket_passenger` `ap` ON (( `aa`.`id` = `ap`.`airline_id` ))) LEFT JOIN `m_initialname` `mi` ON (( `ap`.`initial_name` = `mi`.`id` ))) JOIN `billable` `bb` ON ((`mt`.`id` = `bb`.`master_id`))) LEFT JOIN `m_accterm` `mat` ON ((`bb`.`term_pay` = `mat`.`id`))) JOIN `airticket_flight` `af` ON (( `aa`.`id` = `af`.`airline_id` ))) LEFT JOIN `staff` `st2` ON ((`ab`.`issue_by` = `st2`.`id`))) LEFT JOIN `m_pricecategory` `mp` ON (( `ap`.`passenger_type` = `mp`.`id` ))) JOIN `billable_desc` `bde` ON (( `bde`.`billable_id` = `bb`.`id` ))) LEFT JOIN `invoice_detail` `invd` ON (( `invd`.`bill_desc_id` = `bde`.`id` ))) LEFT JOIN `invoice` `inv` ON ((( `inv`.`id` = `invd`.`invoice_id` ) AND (`inv`.`status` <> 2)))) WHERE (((`af`.`ad_cost` <> 0) OR (`af`.`ad_price` <> 0) OR (`af`.`ch_cost` <> 0) OR (`af`.`ch_price` <> 0) OR (`af`.`in_cost` <> 0) OR (`af`.`in_price` <> 0) OR (`af`.`ad_tax` <> 0) OR (`af`.`ch_tax` <> 0) OR (`af`.`in_tax` <> 0) OR (`af`.`ad_tax_cost` <> 0) OR (`af`.`ch_tax_cost` <> 0) OR (`af`.`in_tax_cost` <> 0)) AND (`mp`.`code` <> 'NON') AND (`bde`.`bill_type` = 1) AND (`mp`.`code` <> 'NON') AND (`bde`.`bill_type` = 1) AND (`af`.`status` <> 3) AND (`af`.`status` <> 2)) AND `aa`.`pnr_id` = "+pnrID+" GROUP BY `ap`.`id` ")
                .addScalar("ref_no", Hibernate.STRING)
                .addScalar("leader_name", Hibernate.STRING)
                .addScalar("bill_date", Hibernate.DATE)
                .addScalar("company_name", Hibernate.STRING)
                .addScalar("tel", Hibernate.STRING)
                .addScalar("pnr", Hibernate.STRING)
                .addScalar("inv", Hibernate.STRING)
                .addScalar("sell", Hibernate.STRING)
                .addScalar("sell_tax", Hibernate.STRING)
                .addScalar("net", Hibernate.STRING)
                .addScalar("net_tax", Hibernate.STRING)
                .addScalar("term_of_payment", Hibernate.STRING)
                .addScalar("remark", Hibernate.STRING)
                .addScalar("prepare_by", Hibernate.STRING) 
                .addScalar("issue_by", Hibernate.STRING)  
                .addScalar("pickup", Hibernate.STRING)
                .addScalar("company_address", Hibernate.STRING)
                .list();
         int check =1;
         List info = new ArrayList();
         for (Object[] B : QueryTicketInfo) {
            ticket.setRefno(util.ConvertString(B[0]));
            ticket.setLeadername(util.ConvertString(B[1]));
            ticket.setPrepareby(util.ConvertString(B[13]));
            ticket.setIssueby(util.ConvertString(B[14]));
                       
//            price += (!"".equalsIgnoreCase(String.valueOf(util.ConvertString(B[7])))? (int)B[7] : 0);
//            priceTax += (!"".equalsIgnoreCase(String.valueOf(util.ConvertString(B[8])))? (int)B[8] : 0);
//            cost += (!"".equalsIgnoreCase(String.valueOf(util.ConvertString(B[9])))? (int)B[9] : 0);
//            costtax += (!"".equalsIgnoreCase(String.valueOf(util.ConvertString(B[10])))? (int)B[10] : 0);
            
            price = price.add((B[7]) == null ? new BigDecimal(BigInteger.ZERO) : new BigDecimal(util.ConvertString(B[7])));
            priceTax = priceTax.add((B[8]) == null ? new BigDecimal(BigInteger.ZERO) : new BigDecimal(util.ConvertString(B[8])));
            cost = cost.add((B[9]) == null ? new BigDecimal(BigInteger.ZERO) : new BigDecimal(util.ConvertString(B[9])));
            costtax = costtax.add((B[10]) == null ? new BigDecimal(BigInteger.ZERO) : new BigDecimal(util.ConvertString(B[10])));
                        
            if(check == QueryTicketInfo.size()){
               TicketOrderInfo ticketOrderInfo = new TicketOrderInfo();
                if(B[2] != null){
                    ticketOrderInfo.setIssuedate(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(((Date)B[2])));
                }else{
                    ticketOrderInfo.setIssuedate("");
                }
                
                ticketOrderInfo.setCompanyname(!"".equalsIgnoreCase(String.valueOf(util.ConvertString(B[3]))) ? util.ConvertString(B[3]) : "");
                ticketOrderInfo.setTel(!"".equalsIgnoreCase(String.valueOf(util.ConvertString(B[4]))) ? util.ConvertString(B[4]) : "");
                ticketOrderInfo.setPnr(!"".equalsIgnoreCase(String.valueOf(util.ConvertString(B[5]))) ? util.ConvertString(B[5]) : "");
                ticketOrderInfo.setInv(!"".equalsIgnoreCase(String.valueOf(util.ConvertString(B[6]))) ? util.ConvertString(B[3]) : "");
                ticketOrderInfo.setTermpay(!"".equalsIgnoreCase(String.valueOf(util.ConvertString(B[11]))) ? util.ConvertString(B[11]) : "");
                ticketOrderInfo.setRemark(!"".equalsIgnoreCase(String.valueOf(util.ConvertString(B[12]))) ? util.ConvertString(B[12]) : "");
                ticketOrderInfo.setPrice(util.ConvertString(price));
                ticketOrderInfo.setPricetax(util.ConvertString(priceTax));
                ticketOrderInfo.setCost(util.ConvertString(cost));
                ticketOrderInfo.setCosttax(util.ConvertString(costtax));
                ticketOrderInfo.setIspickup(!"".equalsIgnoreCase(String.valueOf(util.ConvertString(B[15]))) ? util.ConvertString(B[15]) : "");
                ticketOrderInfo.setCompanyaddress(!"".equalsIgnoreCase(String.valueOf(util.ConvertString(B[16]))) ? util.ConvertString(B[16]) : "");
                info.add(ticketOrderInfo);
            }
            check++;                        
//            if(B[2] != null){
//                ticket.setIssuedate(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(((Date)B[2])));
//            }
//            ticket.setCompanyname(util.ConvertString(B[3]));
//            ticket.setTel(util.ConvertString(B[4]));
//            ticket.setPnr(util.ConvertString(B[5]));
//            ticket.setInv(util.ConvertString(B[6]));
//            price += (int)B[7];
//            priceTax += (int)B[8];
//            cost += (int)B[9];
//            costtax += (int)B[10];
//            ticket.setPrice(util.ConvertString(B[7]));
//            ticket.setPricetax(util.ConvertString(B[8]));
//            ticket.setCost(util.ConvertString(B[9]));
//            ticket.setCosttax(util.ConvertString(B[10]));
//            ticket.setTermpay(util.ConvertString(B[11]));
//            ticket.setRemark(util.ConvertString(B[12]));
//            ticket.setPrepareby(util.ConvertString(B[13]));
//            ticket.setIssueby(util.ConvertString(B[14]));
        }
//        ticket.setPrice(util.ConvertString(price));
//        ticket.setPricetax(util.ConvertString(priceTax));
//        ticket.setCost(util.ConvertString(cost));
//        ticket.setCosttax(util.ConvertString(costtax));
        
         if(QueryTicketInfo.size() == 0){
            TicketOrderInfo ticketOrderInfo = new TicketOrderInfo();
            ticketOrderInfo.setCompanyname("");
            ticketOrderInfo.setCost("");
            ticketOrderInfo.setCosttax("");
            ticketOrderInfo.setInv("");
            ticketOrderInfo.setIssuedate("");
            ticketOrderInfo.setPnr("");
            ticketOrderInfo.setPrice("");
            ticketOrderInfo.setPricetax("");
            ticketOrderInfo.setRemark("");
            ticketOrderInfo.setTel("");
            ticketOrderInfo.setTermpay("");
            info.add(ticketOrderInfo);
        }
        ticket.setInfoDataSource(new JRBeanCollectionDataSource(info));
         
        List<Object[]> QueryFlightList = session.createSQLQuery("SELECT * FROM `ticket_order_flight`  Where `ticket_order_flight`.pnr_id = " + pnrID + " AND `ticket_order_flight`.flight_status <> 'CANCEL' ")
                .addScalar("flight_no", Hibernate.STRING)
                .addScalar("filght_class", Hibernate.STRING)
                .addScalar("depart_date", Hibernate.STRING)
                .addScalar("from", Hibernate.STRING)
                .addScalar("to", Hibernate.STRING)
                .addScalar("depart_time", Hibernate.STRING)
                .addScalar("arrive_time", Hibernate.STRING)
                .addScalar("flight_status", Hibernate.STRING)
                .list();

        List flights = new ArrayList();
        for (int i = 0; i < QueryFlightList.size(); i++) {
            Object[] flightInfo = QueryFlightList.get(i);
            TicketOrderFlight f = new TicketOrderFlight();
            f.setFlightNo(util.ConvertString(flightInfo[0]));
            f.setFlightClass(util.ConvertString(flightInfo[1]));
            if(flightInfo[2] != null){
                System.out.println(" flightInfo[2] " + flightInfo[2] );
                f.setDepartdate(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format((util.convertStringToDate(String.valueOf(flightInfo[2])))));
            }else{
                f.setDepartdate("");
            }
            f.setFrom(util.ConvertString(flightInfo[3]));
            f.setTo(util.ConvertString(flightInfo[4]));
            f.setDepttime(util.ConvertString(flightInfo[5]));
            f.setArrvtime(util.ConvertString(flightInfo[6]));
            f.setStatus(util.ConvertString(flightInfo[7]));
            flights.add(f);
            
        }

        ticket.setFlightDataSource(new JRBeanCollectionDataSource(flights));

        List<Object[]> QueryDescList = session.createSQLQuery("SELECT * FROM `ticket_order_desc` where `ticket_order_desc`.ref_no = " + refno)
                .addScalar("description", Hibernate.STRING)
                .addScalar("net", Hibernate.STRING)
                .addScalar("sell", Hibernate.STRING)
                .list();
        
        List description = new ArrayList();
        
        for (int i = 0; i < QueryDescList.size(); i++) {
            Object[] data = QueryDescList.get(i);
            TicketOrderDescription ticketOrderDescription = new TicketOrderDescription();
            ticketOrderDescription.setDescription1(!"".equalsIgnoreCase(String.valueOf(util.ConvertString(data[0]))) ? util.ConvertString(data[0]) : "");
            ticketOrderDescription.setNet1(!"".equalsIgnoreCase(String.valueOf(util.ConvertString(data[1]))) ? util.ConvertString(data[1]) : "");
            ticketOrderDescription.setSell1(!"".equalsIgnoreCase(String.valueOf(util.ConvertString(data[2]))) ? util.ConvertString(data[2]) : "");
            description.add(ticketOrderDescription);
        }
//        if(QueryDescList.size() == 0){
//            TicketOrderDescription ticketOrderDescription = new TicketOrderDescription();
//            ticketOrderDescription.setDescription1("");
//            ticketOrderDescription.setNet1("");
//            ticketOrderDescription.setSell1("");
//            ticketOrderDescription.setDescription2("");
//            ticketOrderDescription.setNet2("");
//            ticketOrderDescription.setSell2("");
//        }
        
        ticket.setDescriptionDataSource(new JRBeanCollectionDataSource(description));
        
        List<Object[]> QueryPassengerList = session.createSQLQuery("SELECT * FROM `ticket_order_passenger` where `ticket_order_passenger`.pnr_id = " + pnrID)
                  .addScalar("passenger_name", Hibernate.STRING)
                  .addScalar("ticket_no", Hibernate.STRING)
                  .list();
          
        List passengers = new ArrayList();
        for (int i = 0; i < QueryPassengerList.size(); i++) {
            Object[] passname = QueryPassengerList.get(i);
            TicketOrderPassenger p = new TicketOrderPassenger();
            p.setNameAndTicket(util.ConvertString(passname[0]) + " " + util.ConvertString(passname[1]));
            passengers.add(p);
        }
        
        ticket.setPassengerNameAndTicketDataSource(new JRBeanCollectionDataSource(passengers));
        
        session.close();
        this.sessionFactory.close();
        return ticket;
    }

    @Override
    public TicketOrder getTicketOrderAddition(String refno) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        TicketOrder ticket = new TicketOrder();

        BigDecimal price = new BigDecimal(BigInteger.ZERO);
        BigDecimal priceTax = new BigDecimal(BigInteger.ZERO);
        BigDecimal cost = new BigDecimal(BigInteger.ZERO);
        BigDecimal costtax = new BigDecimal(BigInteger.ZERO);
         List<Object[]> QueryTicketInfo = session.createSQLQuery(" SELECT * FROM `ticket_order_addition`  where  `ticket_order_addition`.refno = '" + refno + "'")
                .addScalar("ref_no", Hibernate.STRING)
                .addScalar("leader_name", Hibernate.STRING)
                .addScalar("bill_date", Hibernate.DATE)
                .addScalar("company_name", Hibernate.STRING)
                .addScalar("tel", Hibernate.STRING)
                .addScalar("pnr", Hibernate.STRING)
                .addScalar("inv", Hibernate.STRING)
                .addScalar("sell", Hibernate.STRING)
                .addScalar("sell_tax", Hibernate.STRING)
                .addScalar("net", Hibernate.STRING)
                .addScalar("net_tax", Hibernate.STRING)
                .addScalar("term_of_payment", Hibernate.STRING)
                .addScalar("remark", Hibernate.STRING)
                .addScalar("prepare_by", Hibernate.STRING) 
                .addScalar("issue_by", Hibernate.STRING)  
                .addScalar("pickup", Hibernate.STRING)
                .addScalar("company_address", Hibernate.STRING)
                .list();
         int check =1;
         List info = new ArrayList();
         for (Object[] B : QueryTicketInfo) {
            ticket.setRefno(util.ConvertString(B[0]));
            ticket.setLeadername(util.ConvertString(B[1]));
            ticket.setPrepareby(util.ConvertString(B[13]));
            ticket.setIssueby(util.ConvertString(B[14]));
            
            price = price.add((B[7]) == null ? new BigDecimal(BigInteger.ZERO) : new BigDecimal(util.ConvertString(B[7])));
            priceTax = priceTax.add((B[8]) == null ? new BigDecimal(BigInteger.ZERO) : new BigDecimal(util.ConvertString(B[8])));
            cost = cost.add((B[9]) == null ? new BigDecimal(BigInteger.ZERO) : new BigDecimal(util.ConvertString(B[9])));
            costtax = costtax.add((B[10]) == null ? new BigDecimal(BigInteger.ZERO) : new BigDecimal(util.ConvertString(B[10])));
                        
            if(check == QueryTicketInfo.size()){
               TicketOrderInfo ticketOrderInfo = new TicketOrderInfo();
                if(B[2] != null){
                    ticketOrderInfo.setIssuedate(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(((Date)B[2])));
                }else{
                    ticketOrderInfo.setIssuedate("");
                }
                
                ticketOrderInfo.setCompanyname(!"".equalsIgnoreCase(String.valueOf(util.ConvertString(B[3]))) ? util.ConvertString(B[3]) : "");
                ticketOrderInfo.setTel(!"".equalsIgnoreCase(String.valueOf(util.ConvertString(B[4]))) ? util.ConvertString(B[4]) : "");
                ticketOrderInfo.setPnr(!"".equalsIgnoreCase(String.valueOf(util.ConvertString(B[5]))) ? util.ConvertString(B[5]) : "");
                ticketOrderInfo.setInv(!"".equalsIgnoreCase(String.valueOf(util.ConvertString(B[6]))) ? util.ConvertString(B[3]) : "");
                ticketOrderInfo.setTermpay(!"".equalsIgnoreCase(String.valueOf(util.ConvertString(B[11]))) ? util.ConvertString(B[11]) : "");
                ticketOrderInfo.setRemark(!"".equalsIgnoreCase(String.valueOf(util.ConvertString(B[12]))) ? util.ConvertString(B[12]) : "");
                ticketOrderInfo.setPrice(util.ConvertString(price));
                ticketOrderInfo.setPricetax(util.ConvertString(priceTax));
                ticketOrderInfo.setCost(util.ConvertString(cost));
                ticketOrderInfo.setCosttax(util.ConvertString(costtax));
                ticketOrderInfo.setIspickup(!"".equalsIgnoreCase(String.valueOf(util.ConvertString(B[15]))) ? util.ConvertString(B[15]) : "");
                ticketOrderInfo.setCompanyaddress(!"".equalsIgnoreCase(String.valueOf(util.ConvertString(B[16]))) ? util.ConvertString(B[16]) : "");
                info.add(ticketOrderInfo);
            }
            check++;                        

        }
//        
//        if(QueryTicketInfo.size() == 0){
//            TicketOrderInfo ticketOrderInfo = new TicketOrderInfo();
//            ticketOrderInfo.setCompanyname("");
//            ticketOrderInfo.setCost("");
//            ticketOrderInfo.setCosttax("");
//            ticketOrderInfo.setInv("");
//            ticketOrderInfo.setIssuedate("");
//            ticketOrderInfo.setPnr("");
//            ticketOrderInfo.setPrice("");
//            ticketOrderInfo.setPricetax("");
//            ticketOrderInfo.setRemark("");
//            ticketOrderInfo.setTel("");
//            ticketOrderInfo.setTermpay("");
//            info.add(ticketOrderInfo);
//        }
        ticket.setInfoDataSource(new JRBeanCollectionDataSource(info));
         
        List flights = new ArrayList();
        ticket.setFlightDataSource(new JRBeanCollectionDataSource(flights));

        List<Object[]> QueryDescList = session.createSQLQuery("SELECT * FROM `ticket_order_desc` where `ticket_order_desc`.ref_no = " + refno)
                .addScalar("description", Hibernate.STRING)
                .addScalar("net", Hibernate.STRING)
                .addScalar("sell", Hibernate.STRING)
                .list();
        
        List description = new ArrayList();
        
        for (int i = 0; i < QueryDescList.size(); i++) {
            Object[] data = QueryDescList.get(i);
            TicketOrderDescription ticketOrderDescription = new TicketOrderDescription();
            ticketOrderDescription.setDescription1(!"".equalsIgnoreCase(String.valueOf(util.ConvertString(data[0]))) ? util.ConvertString(data[0]) : "");
            ticketOrderDescription.setNet1(!"".equalsIgnoreCase(String.valueOf(util.ConvertString(data[1]))) ? util.ConvertString(data[1]) : "");
            ticketOrderDescription.setSell1(!"".equalsIgnoreCase(String.valueOf(util.ConvertString(data[2]))) ? util.ConvertString(data[2]) : "");
            description.add(ticketOrderDescription);
        }
        
        ticket.setDescriptionDataSource(new JRBeanCollectionDataSource(description));
                 
        List passengers = new ArrayList();      
        ticket.setPassengerNameAndTicketDataSource(new JRBeanCollectionDataSource(passengers));
        
        session.close();
        this.sessionFactory.close();
        return ticket;
    }
    
}
