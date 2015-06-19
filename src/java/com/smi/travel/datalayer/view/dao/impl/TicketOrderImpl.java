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
import com.smi.travel.util.UtilityFunction;
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
        int price =0;
        int priceTax = 0;
        int cost =0;
        int costtax =0;
         List<Object[]> QueryTicketInfo = session.createSQLQuery(" SELECT * FROM `ticket_order_info`  where  `ticket_order_info`.pnr_id =  " + pnrID)
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
                .list();
        
         for (Object[] B : QueryTicketInfo) {
            ticket.setRefno(util.ConvertString(B[0]));
            ticket.setLeadername(util.ConvertString(B[1]));
            if(B[2] != null){
                ticket.setIssuedate(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(((Date)B[2])));
            }
            ticket.setCompanyname(util.ConvertString(B[3]));
            ticket.setTel(util.ConvertString(B[4]));
            ticket.setPnr(util.ConvertString(B[5]));
            ticket.setInv(util.ConvertString(B[6]));
            ticket.setPrice(util.ConvertString(B[7]));
            ticket.setPricetax(util.ConvertString(B[8]));
            ticket.setCost(util.ConvertString(B[9]));
            ticket.setCosttax(util.ConvertString(B[10]));
            ticket.setTermpay(util.ConvertString(B[11]));
            ticket.setRemark(util.ConvertString(B[12]));
            ticket.setPrepareby(util.ConvertString(B[13]));
            ticket.setIssueby(util.ConvertString(B[14]));
        }

        List<Object[]> QueryFlightList = session.createSQLQuery("SELECT * FROM `ticket_order_flight`  Where `ticket_order_flight`.pnr_id = " + pnrID)
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
            f.setDepartdate(util.ConvertString(flightInfo[2]));
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

        for (int i = 0; i < QueryDescList.size(); i++) {
            Object[] data = QueryDescList.get(i);
            if (i == 0) {
                ticket.setDescription1(util.ConvertString(data[0]));
                ticket.setNet1(util.ConvertString(data[1]));
                ticket.setSell1(util.ConvertString(data[2]));
            } else if (i == 1) {
                ticket.setDescription2(util.ConvertString(data[0]));
                ticket.setNet2(util.ConvertString(data[1]));
                ticket.setSell2(util.ConvertString(data[2]));
            }
        }
          
          
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
    
}
