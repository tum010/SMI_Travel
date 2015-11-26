/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;
import com.smi.travel.datalayer.dao.TicketFareAirlineDao;
import com.smi.travel.datalayer.entity.Agent;
import com.smi.travel.datalayer.entity.AirticketAirline;
import com.smi.travel.datalayer.entity.AirticketFlight;
import com.smi.travel.datalayer.entity.AirticketFlightView;
import com.smi.travel.datalayer.entity.AirticketPassenger;
import com.smi.travel.datalayer.entity.BookingFlight;
import com.smi.travel.datalayer.entity.BookingPassenger;
import com.smi.travel.datalayer.entity.InvoiceDetail;
import com.smi.travel.datalayer.entity.MAirlineAgent;
import com.smi.travel.datalayer.entity.MInitialname;
import com.smi.travel.datalayer.entity.MRunningCode;
import com.smi.travel.datalayer.entity.PaymentAirticketFare;
import com.smi.travel.datalayer.entity.PaymentAirticketRefund;
import com.smi.travel.datalayer.entity.RefundAirticketDetail;
import com.smi.travel.datalayer.entity.TicketFareAirline;
import com.smi.travel.datalayer.entity.TicketFareInvoice;
import com.smi.travel.datalayer.view.entity.InvoiceDetailView;
import com.smi.travel.datalayer.view.entity.TicketFareView;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Surachai
 */

public class TicketFareAirlineImpl implements TicketFareAirlineDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;
    private UtilityFunction util;
    @Override
    public String InsertTicketFare(TicketFareAirline ticket) {
        String result = "success";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            if(ticket.getMPaymentDoctype() != null){
                if((ticket.getMPaymentDoctype().getId() != null || !"".equals(ticket.getMPaymentDoctype().getId())) 
                    && ((ticket.getPvCode() == null)||("".equalsIgnoreCase(ticket.getPvCode())))){
                    result = generatePVCode();
                    ticket.setPvCode(result);
                }
            }
            session.save(ticket);
            
            List<TicketFareInvoice> ticketFareInvoices = ticket.getTicketFareInvoices();
            
            if(ticketFareInvoices != null && !ticketFareInvoices.isEmpty()){
                for(int i = 0; i < ticketFareInvoices.size(); i++){
                   session.save(ticketFareInvoices.get(i));
                }
            }
            
            
            
            transaction.commit();
            session.close();
            this.sessionFactory.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            result = "fail";
        }
        return result;    
    }
   
    @Override
    public String UpdateTicketFare(TicketFareAirline ticket) {
        String result = "success";
        Session session = this.sessionFactory.openSession();
        try {
            
            transaction = session.beginTransaction();
            if(ticket.getMPaymentDoctype() != null){
                if((ticket.getMPaymentDoctype().getId() != null || !"".equals(ticket.getMPaymentDoctype().getId())) 
                    && ((ticket.getPvCode() == null)||("".equalsIgnoreCase(ticket.getPvCode())))){
                    result = generatePVCode();
                    ticket.setPvCode(result);
                }
            }
            session.update(ticket);
            
            List<TicketFareInvoice> ticketFareInvoices = ticket.getTicketFareInvoices();
            if(ticketFareInvoices != null && !ticketFareInvoices.isEmpty()){
                for(int i = 0; i < ticketFareInvoices.size(); i++){
                    if(ticketFareInvoices.get(i).getId() != null && !ticketFareInvoices.get(i).getId().isEmpty() && !"".equalsIgnoreCase(String.valueOf(ticketFareInvoices.get(i).getId()))){
                        session.update(ticketFareInvoices.get(i));
                    } else {
                        session.save(ticketFareInvoices.get(i));
                    }             
                }
            }
            transaction.commit();
            session.close();
            this.sessionFactory.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            result = "fail";
            transaction.rollback();
            session.close();
            this.sessionFactory.close();
            
        }
        return result;    
    }
    
    public String generatePVCode() {
        String PVCode = "";
        Session session = this.sessionFactory.openSession();
        List<TicketFareAirline> list = new LinkedList<TicketFareAirline>();
        Date thisdate = new Date();
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("yyMM");
        Query query = session.createQuery("from TicketFareAirline tr where tr.pvCode Like :pvCode Order by tr.pvCode desc");
        query.setParameter("pvCode", "%"+ df.format(thisdate) + "%");
        query.setMaxResults(1);
        list = query.list();
        if (list.isEmpty()) {
            PVCode = "IB"+ df.format(thisdate) + "-" + "0001";
        } else {
            PVCode = String.valueOf(list.get(0).getPvCode());
            if (!PVCode.equalsIgnoreCase("")) {
                System.out.println("PVCode.substring(6,10) " + PVCode.substring(6,10) + "/////");
                int running = Integer.parseInt(PVCode.substring(6,10)) + 1;
                String temp = String.valueOf(running);
                for (int i = temp.length(); i < 4; i++) {
                    temp = "0" + temp;
                }
                PVCode = "IB"+ df.format(thisdate) + "-" + temp;
            }
        }
        session.close();
        this.sessionFactory.close();
        return PVCode.replace("-","");
    }
        
    @Override
    public int DeleteTicketFare(TicketFareAirline ticket) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(ticket);
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
            result = 0;
        }
        return result;        
    }

    @Override
    public TicketFareAirline getTicketFareFromTicketNo(String TicketNo,String TicketId) {
        TicketFareAirline ticketFare = new TicketFareAirline();
        String query = "from TicketFareAirline t where t.ticketNo = :ticketNo ";
        if(!"".equalsIgnoreCase(TicketId)){
            query = "from TicketFareAirline t where t.id = :ticketId";
        }
        Session session = this.sessionFactory.openSession();
        List<TicketFareAirline> ticketFareList = new ArrayList<TicketFareAirline>();
         if(!"".equalsIgnoreCase(TicketId)){
             ticketFareList = session.createQuery(query).setParameter("ticketId", TicketId).list();
         }else{
            ticketFareList = session.createQuery(query).setParameter("ticketNo", TicketNo).list();
         }        

        if (ticketFareList.isEmpty()) {
            return null;
        }else{
            ticketFare =  ticketFareList.get(0);
        }
//        session.close();
//        this.sessionFactory.close();
        return ticketFare;
    }

    @Override
    public String getTicketFareBookingFromTicketNo(String TicketNo) {
        String result ="";
        AirticketPassenger ticketPass = new AirticketPassenger();
        String query = "from AirticketPassenger pass where pass.series1||pass.series2||pass.series3 = :ticketNo";
        Session session = this.sessionFactory.openSession();
        List<AirticketPassenger> ticketPassList = session.createQuery(query).setParameter("ticketNo", TicketNo).list();
        String ticketAirline = "";
        String initialname = "";
        String department = "";
        String masterId = "";
        String ticketType = "";
        String mpricecategoryname = "";
        if (ticketPassList.isEmpty()) {
            System.out.println(" ticketPassList.isEmpty() ");
            return null;
        }else{ 
            for(int i = 0 ; i < ticketPassList.size() ; i++ ){
                ticketType = ticketPassList.get(i).getTicketType();
                
                AirticketAirline airticketAirline = ticketPassList.get(i).getAirticketAirline();
                MInitialname mInitialname = ticketPassList.get(i).getMInitialname();
                if(airticketAirline.getMAirline() != null){
                    ticketAirline = getMAirlineAgentIdFromCode(ticketPassList.get(i).getAirticketAirline().getMAirline().getCode());
                }
                if(mInitialname != null){
                    initialname = mInitialname.getName();
                }
                if(airticketAirline.getAirticketPnr().getAirticketBooking().getMaster() != null){
                    if(! "null".equals(airticketAirline.getAirticketPnr().getAirticketBooking().getMaster().getBookingType())
                    || airticketAirline.getAirticketPnr().getAirticketBooking().getMaster().getBookingType() != null){
                        department = airticketAirline.getAirticketPnr().getAirticketBooking().getMaster().getBookingType();
                    }
                    if(!"null".equals(airticketAirline.getAirticketPnr().getAirticketBooking().getMaster().getId())
                    || airticketAirline.getAirticketPnr().getAirticketBooking().getMaster().getId() != null){
                        masterId = airticketAirline.getAirticketPnr().getAirticketBooking().getMaster().getId();
                    }
//                    department = airticketAirline.getAirticketPnr().getAirticketBooking().getMaster().getBookingType();
//                    masterId = airticketAirline.getAirticketPnr().getAirticketBooking().getMaster().getId();
                }
                
                if(ticketPassList.get(i).getMPricecategory() != null ){
                    mpricecategoryname = ticketPassList.get(i).getMPricecategory().getName();
                }
                int ticketfare = 0;
                int tickettax = 0;
                if("I".equalsIgnoreCase(ticketType)){
                    if(airticketAirline.getAirticketFlights() != null){
                        List<AirticketFlight> flightList = new ArrayList<AirticketFlight>(airticketAirline.getAirticketFlights());
                        for(int j = 0 ; j < flightList.size() ; j++ ){
                            if("ADULT".equals(mpricecategoryname)){
                                if(flightList.get(j).getAdCost() != null){
                                   ticketfare = ticketfare + flightList.get(j).getAdCost();
                                }
                                if(flightList.get(j).getAdTaxCost() != null){
                                    tickettax = tickettax + flightList.get(j).getAdTaxCost();
                                }
                            }else if("CHILD".equals(mpricecategoryname)){
                                if(flightList.get(j).getChCost()!= null){
                                   ticketfare = ticketfare + flightList.get(j).getChCost();
                                }
                                if(flightList.get(j).getChTaxCost() != null){
                                    tickettax = tickettax + flightList.get(j).getChTaxCost();
                                }
                            }else if("INFANT".equals(mpricecategoryname)){
                                if(flightList.get(j).getInCost() != null){
                                   ticketfare = ticketfare + flightList.get(j).getInCost();
                                }
                                if(flightList.get(j).getInTaxCost() != null){
                                    tickettax = tickettax + flightList.get(j).getInTaxCost();
                                }
                            }
                        }
                    }
                }else if("D".equalsIgnoreCase(ticketType)){
                    ticketfare = ticketPassList.get(i).getTicketFare();
                    tickettax  = ticketPassList.get(i).getTicketTax();
                }
                
                result = ticketfare + "," 
                        + tickettax + "," 
                        + airticketAirline.getTicketDate() + "," 
                        + ticketPassList.get(i).getTicketType() + "," 
                        + ticketAirline + "," 
                        + ticketPassList.get(i).getTicketFrom() + "," 
                        + initialname + ticketPassList.get(i).getLastName()+ " "+ticketPassList.get(i).getFirstName()+ ","   
                        + department + ","//department 
                        + masterId
                        ;
                System.err.println("getTicketFareBookingFromTicketNo " + i + " result ::: "+result);
             }
        }
        session.close();
        this.sessionFactory.close();
        return result;
    }

    @Override
    public String getListTicketFareFromRefno(String Refno) {
        String result ="";
        System.out.println(" Refno ::: "+Refno);
        String query = " from AirticketPassenger  airP where airP.airticketAirline.airticketPnr.airticketBooking.master.referenceNo = :refno";
        Session session = this.sessionFactory.openSession();
        List<AirticketPassenger> ticketPassList = session.createQuery(query).setParameter("refno", Refno).list();
        
        if (ticketPassList.isEmpty()){
            return null;
        }else{
            result =  buildTicketListHTML(ticketPassList,Refno,"");
        }
        session.close();
        this.sessionFactory.close();
        return result;
    }
    
    public String buildTicketListHTML(List<AirticketPassenger> airPassengerList,String refno,String invno){
        StringBuffer html = new StringBuffer();
        if (airPassengerList == null || airPassengerList.size() == 0) {
            return html.toString();
        } 
        String ticketId = "" ;
        String name = "";
        String ticket = "";
        String ticketClass = "";
        String departDate = "";
        String ticketFare = "";
        String ticketTax = "";
        String issueDate = "";
        String ticketRouting = "";
        String airline = "";
        String ticketBy = "";
        String department = "";
        String masterId = "";
        String mpricecategoryname = "";
        String referno = "";
        for(int i = 0 ; i < airPassengerList.size() ; i++ ){
            if(airPassengerList.get(i).getSeries1() != null && airPassengerList.get(i).getSeries2() != null && airPassengerList.get(i).getSeries3() != null){
                ticket = airPassengerList.get(i).getSeries1() 
                                + airPassengerList.get(i).getSeries2() 
                                + airPassengerList.get(i).getSeries3();
            }
            if(airPassengerList.get(i).getMPricecategory() != null ){
                mpricecategoryname = airPassengerList.get(i).getMPricecategory().getName();
            }
            
            List<AirticketFlight> airlines = new ArrayList<AirticketFlight>(airPassengerList.get(i).getAirticketAirline().getAirticketFlights());
            int ticketfare = 0;
            int tickettax = 0;
            for(int j=0;j<airlines.size();j++){
                departDate = String.valueOf(airlines.get(j).getDepartDate());
                if(airlines.get(j).getMFlight() != null){
                    ticketClass = airlines.get(j).getMFlight().getName();
                }
                if("ADULT".equals(mpricecategoryname)){
                    if(airlines.get(j).getAdCost() != null){
                       ticketfare = ticketfare + airlines.get(j).getAdCost();
                    }
                    if(airlines.get(j).getAdTaxCost() != null){
                        tickettax = tickettax +  airlines.get(j).getAdTaxCost();
                    }
                }else if("CHILD".equals(mpricecategoryname)){
                    if(airlines.get(j).getChCost()!= null){
                        ticketfare = ticketfare + airlines.get(j).getChCost();
                    }    
                    if(airlines.get(j).getChTaxCost() != null){
                        tickettax = tickettax +  airlines.get(j).getChTaxCost();
                    }
                }else if("INFANT".equals(mpricecategoryname)){
                    if(airlines.get(j).getInCost() != null){
                       ticketfare = ticketfare + airlines.get(j).getInCost();
                    }
                    if(airlines.get(j).getInTaxCost() != null){
                        tickettax = tickettax +  airlines.get(j).getInTaxCost();
                    }
                }
//                if(airlines.get(j).getAdTaxCost() != null){
//                    tickettax = tickettax +  airlines.get(j).getAdTaxCost();
//                }
//                if(airlines.get(j).getChTaxCost() != null){
//                    tickettax = tickettax +  airlines.get(j).getChTaxCost();
//                }
//                if(airlines.get(j).getInTaxCost() != null){
//                    tickettax = tickettax +  airlines.get(j).getInTaxCost();
//                }
            }
            
            TicketFareAirline ticketFareAirline = new TicketFareAirline();
            String query = "from TicketFareAirline t where t.ticketNo = :ticketNo";
            Session session = this.sessionFactory.openSession();
            List<TicketFareAirline> ticketFareList = session.createQuery(query).setParameter("ticketNo", ticket).list();
            if (! ticketFareList.isEmpty()){
                System.out.println("ticketFareList.is not Empty");
                ticketFareAirline =  ticketFareList.get(0);
            }
            if("".equals(String.valueOf(ticketFareAirline.getId())) || "null".equals(String.valueOf(ticketFareAirline.getId()))){
                BigDecimal fare = new BigDecimal(ticketfare);
                fare = fare.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                ticketFare = String.valueOf(fare);
                
                BigDecimal tax = new BigDecimal(tickettax);
                tax = tax.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                ticketTax = String.valueOf(tax);
                
                ticketRouting = String.valueOf(airPassengerList.get(i).getTicketType());
                ticketBy = String.valueOf(airPassengerList.get(i).getTicketFrom());
                if(airPassengerList.get(i).getMInitialname() != null){
                    name = airPassengerList.get(i).getMInitialname().getName() 
                            + airPassengerList.get(i).getLastName() + " "  
                            + airPassengerList.get(i).getFirstName();
                }
                if(airPassengerList.get(i).getAirticketAirline() != null){
                    issueDate = String.valueOf(airPassengerList.get(i).getAirticketAirline().getTicketDate());
                    if(airPassengerList.get(i).getAirticketAirline().getMAirline() != null){
                        airline = String.valueOf(airPassengerList.get(i).getAirticketAirline().getMAirline().getCode());
                    }
                    if(airPassengerList.get(i).getAirticketAirline().getAirticketPnr().getAirticketBooking().getMaster() != null){
                        if(!"null".equals(airPassengerList.get(i).getAirticketAirline().getAirticketPnr().getAirticketBooking().getMaster().getBookingType())
                        || airPassengerList.get(i).getAirticketAirline().getAirticketPnr().getAirticketBooking().getMaster().getBookingType() != null){
                            department = String.valueOf(airPassengerList.get(i).getAirticketAirline().getAirticketPnr().getAirticketBooking().getMaster().getBookingType());
                        }
                        if(!"null".equals(airPassengerList.get(i).getAirticketAirline().getAirticketPnr().getAirticketBooking().getMaster().getId())
                        || airPassengerList.get(i).getAirticketAirline().getAirticketPnr().getAirticketBooking().getMaster().getId() != null){
                            masterId = String.valueOf(airPassengerList.get(i).getAirticketAirline().getAirticketPnr().getAirticketBooking().getMaster().getId());
                            referno = String.valueOf(airPassengerList.get(i).getAirticketAirline().getAirticketPnr().getAirticketBooking().getMaster().getReferenceNo());
                        }
    //                    department = String.valueOf(airPassengerList.get(i).getAirticketAirline().getAirticketPnr().getAirticketBooking().getMaster().getBookingType());
    //                    masterId = String.valueOf(airPassengerList.get(i).getAirticketAirline().getAirticketPnr().getAirticketBooking().getMaster().getId());
                    }
                }
            }else{
                ticketId = ticketFareAirline.getId();
                name = ticketFareAirline.getPassenger();
                ticketFare = String.valueOf(ticketFareAirline.getTicketFare());
                ticketTax = String.valueOf(ticketFareAirline.getTicketTax());
                issueDate = String.valueOf(ticketFareAirline.getIssueDate());
                ticketRouting = ticketFareAirline.getTicketRouting();
                airline = ticketFareAirline.getMAirlineAgent().getCode();
                ticketBy = ticketFareAirline.getTicketBuy();
                department = ticketFareAirline.getDepartment();
                masterId =  ticketFareAirline.getMaster().getId();
                referno = ticketFareAirline.getMaster().getReferenceNo();
            }   
            
            if(!"".equalsIgnoreCase(refno)){
                String newrow
                    = "<tr>"
                    + "<td>" + (ticket == "null" ? "" : ticket ) + "</td>"
                    + "<td>" + (name == "null" ? "" : name ) + "</td>"
                    + "<td>" + (ticketClass == "null" ? "" : ticketClass ) + "</td>"
                    + "<td>" + (departDate == "null" ? "" : departDate ) + "</td>"
                    + "<td class='money'>"+ (ticketFare == "null" ? "" : ticketFare )+"</td>" 
                    + "<td class='money'>" + (ticketTax == "null" ? "" : ticketTax ) +"</td>" 
    //                    + "<td class=\"text-center\" onclick=\"setTicketDetail('" + ticket + "','" + ticketFare + "','" + ticketTax + "','" + issueDate + "','" + ticketRouting + "','" + airline + "','" + ticketBy + "','" + name + "','" + department + "','" + masterId + "','" + ticketId + "')\">"
                    + "<td class=\"text-center\" onclick=\"setTicketFareDetail('" + ticket + "','" + refno + "','')\">"
                    + "<a href=\"\"><span class=\"glyphicon glyphicon-check\"></span></a>" + "</td>"
                    + "</tr>";
                System.out.println("newrow [[[[[[[ "+newrow +" ]]]]");
                html.append(newrow);
            }
            
            if(!"".equalsIgnoreCase(invno)){
                String newrow
                    = "<tr>"
                    + "<td>" + (referno == "null" ? "" : referno ) + "</td>"
                    + "<td>" + (ticket == "null" ? "" : ticket ) + "</td>"
                    + "<td>" + (name == "null" ? "" : name ) + "</td>"
                    + "<td>" + (ticketClass == "null" ? "" : ticketClass ) + "</td>"
                    + "<td>" + (departDate == "null" ? "" : departDate ) + "</td>"
                    + "<td class='money'>"+ (ticketFare == "null" ? "" : ticketFare )+"</td>" 
                    + "<td class='money'>" + (ticketTax == "null" ? "" : ticketTax ) +"</td>" 
    //                    + "<td class=\"text-center\" onclick=\"setTicketDetail('" + ticket + "','" + ticketFare + "','" + ticketTax + "','" + issueDate + "','" + ticketRouting + "','" + airline + "','" + ticketBy + "','" + name + "','" + department + "','" + masterId + "','" + ticketId + "')\">"
                    + "<td class=\"text-center\" onclick=\"setTicketFareDetail('" + ticket + "','','" + invno + "')\">"
                    + "<a href=\"\"><span class=\"glyphicon glyphicon-check\"></span></a>" + "</td>"
                    + "</tr>";
                System.out.println("newrow [[[[[[[ "+newrow +" ]]]]");
                html.append(newrow);
            }
        }
        return html.toString();
    }

    @Override
    public List<BookingFlight> getListFlightFromTicketNo(String ticketNo) {
       List<BookingFlight> FlightList = new ArrayList<BookingFlight>();
        String Ticketquery = " from BookingPassenger  pass where pass.ticketnoS1||pass.ticketnoS2||pass.ticketnoS3 = :ticketNo";
        String Flightquery = " from BookingFlight  flight where  flight.bookingAirline.id = :airlineid";

        Session session = this.sessionFactory.openSession();
        List<BookingPassenger> ticketPassList = session.createQuery(Ticketquery).setParameter("ticketNo", ticketNo).list();
        
        if (ticketPassList.isEmpty()) {
            return null;
        }
        if(ticketPassList.get(0).getBookingAirline() != null) {
            FlightList = session.createQuery(Flightquery).setParameter("airlineid", ticketPassList.get(0).getBookingAirline().getId()).list();
        }
        if (FlightList.isEmpty()) {
            return null;
        }
        return FlightList;
    }

    @Override
    public String validateSaveTicket(TicketFareAirline ticket) {
        String result = "";
        String query = "from TicketFareAirline t where t.ticketNo = :TicketNo";
        Session session = this.sessionFactory.openSession();
//        List<TicketFareAirline> ticketFareList = session.createQuery(query).setParameter("TicketNo",ticket.getTicketNo()).list();
        System.out.println("query : "+query );
        if ("".equalsIgnoreCase(ticket.getId())){
            System.out.println("+++++++++++ InsertTicketFare ++++++++++++");
            result = InsertTicketFare(ticket);
        }else{
            System.out.println("+++++++++++ UpdateTicketFare ++++++++++++");
            result = UpdateTicketFare(ticket);
        }        
        session.close();
        this.sessionFactory.close();
        return result;
    }

    public List<TicketFareView> getListTicketFare(TicketFareView ticket, int option) {
        List<TicketFareInvoice> list = new ArrayList<TicketFareInvoice>();
        String ticketfareid = "";
        Session session = this.sessionFactory.openSession();
        if((ticket.getInvoiceNo() != null) &&(!"".equalsIgnoreCase(ticket.getInvoiceNo())) 
            && (ticket.getReferenceNo()!= null) &&(!"".equalsIgnoreCase(ticket.getReferenceNo()))){
            String queryInvoice = "from TicketFareInvoice tk where tk.invoice.invNo = :invNo and tk.ticketFareAirline.Master.referenceNo = :referenceNo";
            list = session.createQuery(queryInvoice)
                    .setParameter("invNo",ticket.getInvoiceNo())
                    .setParameter("referenceNo",ticket.getReferenceNo())
                    .list();
        }else if((ticket.getInvoiceNo() != null) &&(!"".equalsIgnoreCase(ticket.getInvoiceNo())) 
            && ((ticket.getReferenceNo()!= null) || (!"".equalsIgnoreCase(ticket.getReferenceNo())))){
            String queryInvoice = "from TicketFareInvoice tk where tk.invoice.invNo = :invNo";
            list = session.createQuery(queryInvoice)
                    .setParameter("invNo",ticket.getInvoiceNo())
                    .list();
        }else if( ((ticket.getInvoiceNo() == null) || ("".equalsIgnoreCase(ticket.getInvoiceNo()))) 
            && (ticket.getReferenceNo()!= null) &&(!"".equalsIgnoreCase(ticket.getReferenceNo()))){
            String queryInvoice = "from TicketFareInvoice tk where tk.ticketFareAirline.Master.referenceNo = :referenceNo";
            list = session.createQuery(queryInvoice)
                    .setParameter("referenceNo",ticket.getReferenceNo())
                    .list();
        }
        
        int temp = list.size()-1;
        if(!list.isEmpty()){
            for(int i = 0 ; i < list.size() ; i++ ){
                if(i != temp){
                    ticketfareid += "'" + list.get(i).getTicketFareAirline().getId() + "',";
                }else{
                    ticketfareid += "'" + list.get(i).getTicketFareAirline().getId() + "'";
                }
                
            }
        }
        String query ="from TicketFareAirline t where";
        String queryOperation = "";
        String Prefix_Subfix ="";
        int check =0;
        if(option == 1){
             queryOperation = " = ";
             Prefix_Subfix = "";
        }else if(option == 2){
             queryOperation = " Like ";
             Prefix_Subfix = "%";
        }
        if((ticket.getType() != null) &&(!"".equalsIgnoreCase(ticket.getType()))){
             query += " t.ticketType "+queryOperation+" '"+Prefix_Subfix+ticket.getType()+Prefix_Subfix+"'";
             check =1;
        }
        if(!"".equalsIgnoreCase(ticketfareid)){
            if(check == 1){query += " and ";}
            query += " t.id in ("+ticketfareid.trim()+")";
            check =1;
        }
        if((ticket.getRouting()!= null) &&(!"".equalsIgnoreCase(ticket.getRouting()))){
            if(check == 1){query += " and ";}
            query += " t.ticketRouting "+queryOperation+" '"+Prefix_Subfix+ticket.getRouting()+Prefix_Subfix+"'";
            check =1;
        }
        if((ticket.getAirline() != null) &&(!"".equalsIgnoreCase(ticket.getAirline()))){
            if(check == 1){query += " and ";}
            query += " t.MAirlineAgent "+queryOperation+" '"+Prefix_Subfix+ticket.getAirline()+Prefix_Subfix+"'";
            check =1;
        } 
        if((ticket.getTicketNo() != null) &&(!"".equalsIgnoreCase(ticket.getTicketNo()))){
            if(check == 1){query += " and ";}
            query += " t.ticketNo "+queryOperation+" '"+Prefix_Subfix+ticket.getTicketNo()+Prefix_Subfix+"'";
            check =1;
        } 
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("yyyy-MM-dd");
//        if((ticket.getIssueDate() != null) &&(!"".equalsIgnoreCase(String.valueOf(ticket.getIssueDate())))){
//            if(check == 1){query += " and ";}
//            query += " t.issueDate "+queryOperation+" '"+Prefix_Subfix+(dateformat.format(ticket.getIssueDate()))+Prefix_Subfix+"'";
//            check =1;
//        } 
        
        if( ((ticket.getIssueDateFrom()!= null) &&(!"".equalsIgnoreCase(String.valueOf(ticket.getIssueDateFrom())))) &&((ticket.getIssueDateTo()!= null) &&(!"".equalsIgnoreCase(String.valueOf(ticket.getIssueDateTo()))))){
            if(check == 1){query += " and ";}
            query += " t.issueDate >= '" + dateformat.format(ticket.getIssueDateFrom()) +"' and t.issueDate  <= '"+dateformat.format(ticket.getIssueDateTo()) +"' ";
            check =1;
        }else if((ticket.getIssueDateFrom() != null) &&(!"".equalsIgnoreCase(String.valueOf(ticket.getIssueDateFrom())))){
            if(check == 1){query += " and ";}
            query += " t.issueDate` >= '" + dateformat.format(ticket.getIssueDateFrom()) +"'";
            check =1;
        }else if((ticket.getIssueDateTo() != null) &&(!"".equalsIgnoreCase(String.valueOf(ticket.getIssueDateTo())))){
            if(check == 1){query += " and ";}
            query += " t.issueDate <= '" + dateformat.format(ticket.getIssueDateTo()) +"'";
            check =1;
        }
        
//        if((ticket.getInvoiceNo() != null) &&(!"".equalsIgnoreCase(ticket.getInvoiceNo()))){
//            if(check == 1){query += " and ";}
//            query += " t.ticketNo "+queryOperation+" '"+Prefix_Subfix+ticket.getInvoiceNo()+Prefix_Subfix+"'";
//            check =1;
//        } 
        
        if((ticket.getDepartment() != null) &&(!"".equalsIgnoreCase(ticket.getDepartment()))){
            if(check == 1){query += " and ";}
            query += " t.department "+queryOperation+" '"+Prefix_Subfix+ticket.getDepartment()+Prefix_Subfix+"'";
            check =1;
        } 
        if(check == 0){
            query = query.replaceAll("where", " ");
        }
        System.out.println("query : "+query );
        List<TicketFareAirline> listAirline =  session.createQuery(query).list();
        List<TicketFareView> listView = new ArrayList<TicketFareView>();
        System.out.println("listAirline.size() "+listAirline.size());
        
        if(listAirline.isEmpty()){
            System.out.println("List null ");
            return null;
        }else{
            for(int i=0;i<listAirline.size();i++){
                TicketFareView ticketFareView = new TicketFareView();
                ticketFareView.setId(String.valueOf(listAirline.get(i).getId()));
                ticketFareView.setType(String.valueOf(listAirline.get(i).getTicketType()));
                ticketFareView.setBuy(String.valueOf(listAirline.get(i).getTicketBuy()));
                ticketFareView.setRouting(String.valueOf(listAirline.get(i).getTicketRouting()));
                MAirlineAgent mAirlineAgent = new MAirlineAgent();
                if(listAirline.get(i).getMAirlineAgent() != null){
                    mAirlineAgent.setId(listAirline.get(i).getMAirlineAgent().getId());
                    mAirlineAgent.setCode(listAirline.get(i).getMAirlineAgent().getCode());
                    ticketFareView.setAirline(String.valueOf(mAirlineAgent.getCode()));
                }
                ticketFareView.setTicketNo(String.valueOf(listAirline.get(i).getTicketNo()));
                ticketFareView.setIssueDate(listAirline.get(i).getIssueDate());
                
                List<TicketFareInvoice> ticketFareInvoices = new ArrayList<TicketFareInvoice>(listAirline.get(i).getTicketFareInvoices());
                if(ticketFareInvoices != null && ticketFareInvoices.size() != 0 ){
                    ticketFareView.setInvoiceNo(ticketFareInvoices.get(0).getInvoice() != null ? ticketFareInvoices.get(0).getInvoice().getInvNo() : "");
                }
                ticketFareView.setDepartment(listAirline.get(i).getDepartment());
                ticketFareView.setFare(listAirline.get(i).getTicketFare());
                ticketFareView.setTax(listAirline.get(i).getTicketTax());
                ticketFareView.setTicketCommission(listAirline.get(i).getTicketCommission());
                ticketFareView.setAgentCommission(listAirline.get(i).getAgentCommission());
                ticketFareView.setDiffVat(listAirline.get(i).getDiffVat());
                listView.add(ticketFareView);
            }
        }
        session.close();
        this.sessionFactory.close();
        return listView; 
    }
        
    public TicketFareAirline getTicketFareFromId(String id) {
        Session session = this.sessionFactory.openSession();
        String query ="from TicketFareAirline t where t.id = :ticketId"; 
        List<TicketFareAirline> list = session.createQuery(query).setParameter("ticketId", id).list();
        
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }
    
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public int checkDeletePaymentFromTicketNo(String ticketNo) {
        int result = 0;
        String query = "from PaymentAirticketFare air where air.ticketFareAirline.ticketNo =:ticketNo";
        Session session = this.sessionFactory.openSession();
        List<PaymentAirticketFare> paymentList = session.createQuery(query).setParameter("ticketNo", ticketNo).list();
        if (paymentList.isEmpty()) {
            result = 0;
        }else{
            result = 1;
        }
        System.out.println("=== result === " + result + "===");
        session.close();
        this.sessionFactory.close();
        return result;
    }

    @Override
    public int checkDeleteRefundFromTicketNo(String ticketNo) {
        int result = 0;
        String query = "from RefundAirticketDetail  refund where refund.ticketFareAirline.ticketNo =:ticketNo";
        Session session = this.sessionFactory.openSession();
        List<RefundAirticketDetail> refundList = session.createQuery(query).setParameter("ticketNo", ticketNo).list();
        if (refundList.isEmpty()) {
            result = 0;
        }else{
            result = 1;
        }
        System.out.println("=== result === " + result + "===");
        session.close();
        this.sessionFactory.close();
        return result;    
    }
    
    public String getMAirlineAgentIdFromCode(String code) {
        String result = "";
        String query = "from MAirlineAgent a where a.code =:code";
        Session session = this.sessionFactory.openSession();
        List<MAirlineAgent> mAirlineAgentList = session.createQuery(query).setParameter("code", code).list();
        if (mAirlineAgentList.isEmpty()) {
            result = "1";
        }else{
            result = mAirlineAgentList.get(0).getId();
        }
        System.out.println("=== result === " + result + "===");
        session.close();
        this.sessionFactory.close();
        return result;   
    }

    @Override
    public HashMap<String, Object> getDetailTicketFareAirline(String TicketNo) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        AirticketPassenger ticketFare = new AirticketPassenger();
        String query = "from AirticketPassenger t where t.series1||t.series2||t.series3 = :ticketNo";
        Session session = this.sessionFactory.openSession();
        List<AirticketPassenger> ticketFareList = session.createQuery(query).setParameter("ticketNo", TicketNo).list();
        if (ticketFareList.isEmpty()) {
            return null;
        }else{
            ticketFare =  ticketFareList.get(0);
        }
        String Initialname ="";
        if(ticketFare.getMInitialname() != null ){
            Initialname = ticketFare.getMInitialname().getName();
        }
        //AirticketPassenger.airticketAirline.airticketPnr.airticketBooking.master.bookingType
//        String queryrefundby = "from TicketFareInvoice finv where finv.ticketFareAirline.ticketNo = :ticketno";
//        List<TicketFareInvoice> ticketFareInvoices = session.createQuery(queryrefundby).setParameter("ticketno", TicketNo).list();
//        String invTo = "";
//        String invName = "";
//        if (ticketFareInvoices.isEmpty()){
//            System.out.println(" ticketFareInvoices isEmpty ");
//        }else{
//            if(ticketFareInvoices.get(0).getInvoice() != null){
//                invTo =  String.valueOf(ticketFareInvoices.get(0).getInvoice().getInvTo());
//                invName = String.valueOf(ticketFareInvoices.get(0).getInvoice().getInvName());
//            }
//        }
        
        String queryrefundby = "from TicketFareAirline ticket where ticket.ticketNo = :ticketno";
        List<TicketFareAirline> ticketFareAirlines = session.createQuery(queryrefundby).setParameter("ticketno", TicketNo).list();
        String agentId = "";
        String invTo = "";
        String invName = "";
        if (ticketFareAirlines.isEmpty()){
            System.out.println(" ticketFareInvoices isEmpty ");
        }else{
            if(ticketFareAirlines.get(0) != null){
                agentId =  String.valueOf(ticketFareAirlines.get(0).getAgentId());
                Agent agent = new Agent();
                String queryagent = "from Agent a where a.id= :agentid";
                List<Agent> agentList = session.createQuery(queryagent).setParameter("agentid", agentId).list();
                if (!agentList.isEmpty()) {
                    agent =  agentList.get(0);
                    invTo = String.valueOf(agent.getCode());
                    invName = String.valueOf(agent.getName());
                }
            }
        }
        
        //AirticketPassenger.airticketAirline.airticketPnr.airticketBooking.master.bookingType
        String queryticketno = "From RefundAirticketDetail ref where ( ref.airticketPassenger.series1 || ref.airticketPassenger.series2 || ref.airticketPassenger.series3) = :ticketno";
        List<RefundAirticketDetail> refundAirticketDetails = session.createQuery(queryticketno).setParameter("ticketno", TicketNo).list();
        String sizeData = "";
        if (refundAirticketDetails.isEmpty()){
            System.out.println(" refundAirticketDetails isEmpty ");
        }else{
            sizeData = String.valueOf(refundAirticketDetails.size());
        }
        
        String BookingType = "";
        if(ticketFare.getAirticketAirline().getAirticketPnr().getAirticketBooking().getMaster() != null){
            if(! "null".equals(ticketFare.getAirticketAirline().getAirticketPnr().getAirticketBooking().getMaster().getBookingType())
               || ticketFare.getAirticketAirline().getAirticketPnr().getAirticketBooking().getMaster().getBookingType() != null){
                BookingType = ticketFare.getAirticketAirline().getAirticketPnr().getAirticketBooking().getMaster().getBookingType();
            }
        }
        List<AirticketFlight> FlightList = new ArrayList<AirticketFlight>(ticketFare.getAirticketAirline().getAirticketFlights());
        String rounting = "";
        int price = 0;
        for(int i =0;i<FlightList.size();i++){
            System.out.println(FlightList.get(i).getSourceCode()+"-"+FlightList.get(i).getDesCode());
            String source = FlightList.get(i).getSourceCode();
            String des = FlightList.get(i).getDesCode();
            if(i == 0){
                rounting += source + "-" + des;
            }else{
                if (!rounting.substring(rounting.lastIndexOf("-") + 1).equalsIgnoreCase(source)) {
                    rounting += "," + source + "-" + des;
                } else {
                    rounting += "-" + des;
                }
            }
            if(ticketFare.getMPricecategory() != null){
                String passType = ticketFare.getMPricecategory().getName();
                if(passType.equalsIgnoreCase("ADULT")){
                    price += FlightList.get(i).getAdPrice();
                }else if(passType.equalsIgnoreCase("CHILD")){
                    price += FlightList.get(i).getChPrice();
                }else if(passType.equalsIgnoreCase("INFANT")){
                    price += FlightList.get(i).getInPrice();
                }
            }
        }
        //check price
        System.out.println("price : "+price);
        result.put("Id",ticketFare.getId()); 
        result.put("TicketNo",TicketNo); 
        result.put("TicketDate", ticketFare.getAirticketAirline().getTicketDate());
        result.put("Dept", BookingType.equalsIgnoreCase("O")? "Outbound":"Wendy");
        result.put("Passenger", Initialname+" " + ticketFare.getLastName() +" "+ticketFare.getFirstName());
        result.put("Total", price+ticketFare.getTicketTax());
        result.put("Sector", rounting);
        result.put("InvTo", invTo);
        result.put("InvName", invName);
        result.put("SizeData", sizeData);
        session.close();
        this.sessionFactory.close();
        return result;
    }

    @Override
    public List<AirticketFlightView> getListAirticketFlightFromTicketNo(String ticketNo) {
        List<AirticketFlight> flightList = new ArrayList<AirticketFlight>();
        List<AirticketFlightView> flightViews = new ArrayList<AirticketFlightView>(); 
        String AirticketPassengerQuery  = "from AirticketPassenger pass where pass.series1||pass.series2||pass.series3 = :ticketNo";
        String AirticketFlightQuery  = "from AirticketFlight flight where flight.airticketAirline.id = :airlineid";
        Session session = this.sessionFactory.openSession();
        List<AirticketPassenger> ticketPassList = session.createQuery(AirticketPassengerQuery).setParameter("ticketNo", ticketNo).list();
        
        if (ticketPassList.isEmpty()) {
            return null;
        }
        if(ticketPassList.get(0).getAirticketAirline() != null) {
            flightList = session.createQuery(AirticketFlightQuery).setParameter("airlineid", ticketPassList.get(0).getAirticketAirline().getId()).list();
        }
        if (flightList.isEmpty()) {
            return null;
        }else{
            for(int i = 0 ; i < flightList.size() ; i++ ){
                AirticketFlightView airView = new AirticketFlightView();
                if(flightList.get(i).getAirticketAirline().getMAirline() != null){
                    airView.setAirlineCode(flightList.get(i).getAirticketAirline().getMAirline().getCode());
                }
                
                airView.setFlightNo(flightList.get(i).getFlightNo());
                if(flightList.get(i).getMFlight() != null){
                    airView.setFlightClass(flightList.get(i).getMFlight().getName());
                }
                
                airView.setSourceCode(flightList.get(i).getSourceCode());
                airView.setDesCode(flightList.get(i).getDesCode());
                airView.setDepartDate(flightList.get(i).getDepartDate());
                airView.setArriveDate(flightList.get(i).getArriveDate());
                flightViews.add(airView);
            }
        }
        session.close();
        this.sessionFactory.close();
        return flightViews;
    }

    @Override
    public List<InvoiceDetailView> getInvoiceDetailFromTicketNo(String ticketNo) {
        util = new UtilityFunction();
        String priceType = "";
        String airticketAirline = "";
        String owner = "";
        String routing = "";
        int invamount = 0;
        int price = 0;
        int tax = 0;
        String invTo = "";
        String invName = "";
        List<InvoiceDetail> invoiceDetailList = new ArrayList<InvoiceDetail>();
        List<InvoiceDetail> invoiceDetailTempList = new ArrayList<InvoiceDetail>();
        List<InvoiceDetailView> invoiceDetailViewList = new ArrayList<InvoiceDetailView>();
        System.out.println("ticketNo : "+ticketNo);
        String AirticketPassengerQuery  = "from AirticketPassenger pass where pass.series1||pass.series2||pass.series3 = :ticketNo";
        String InvoiceDetailQuery  = "from InvoiceDetail invd where invd.billableDesc.billable.master.id = :masterId and invd.billableDesc.MBilltype.name = 'Air Ticket' GROUP BY invd.invoice.id";
        String InvDetailQuery  = "from InvoiceDetail invd where  invd.invoice.id = :invoiceId";
        Session session = this.sessionFactory.openSession();
        List<AirticketPassenger> airticketPassList = session.createQuery(AirticketPassengerQuery).setParameter("ticketNo", ticketNo).list();
        
        if (airticketPassList.isEmpty()) {
            System.out.println(" airticketPassList.isEmpty() ");
            return null;
        }
        
        if(airticketPassList.get(0).getMPricecategory() != null){
            priceType = airticketPassList.get(0).getMPricecategory().getName();
            System.out.println(" priceType " + priceType);
        }
        
        owner = airticketPassList.get(0).getAirticketAirline().getAirticketPnr().getAirticketBooking() != null ? airticketPassList.get(0).getAirticketAirline().getAirticketPnr().getAirticketBooking().getStaffByOwnerBy().getName() : "";
        if(airticketPassList.get(0).getAirticketAirline() != null){
            List<AirticketFlight> flightList = new ArrayList<AirticketFlight>(airticketPassList.get(0).getAirticketAirline().getAirticketFlights());
            if(!flightList.isEmpty()){
                if("ADULT".equals(priceType)){
                    price = (!"".equalsIgnoreCase(String.valueOf(flightList.get(0).getAdPrice())) ? flightList.get(0).getAdPrice() : 0 );
                    tax = (!"".equalsIgnoreCase(String.valueOf(flightList.get(0).getAdTax())) ? flightList.get(0).getAdTax() : 0 );
                    invamount = price + tax;
                }else if("CHILD".equals(priceType)){
                    price = (!"".equalsIgnoreCase(String.valueOf(flightList.get(0).getChPrice())) ? flightList.get(0).getChPrice() : 0 );
                    tax = (!"".equalsIgnoreCase(String.valueOf(flightList.get(0).getChTax())) ? flightList.get(0).getChTax() : 0 );
                    invamount = price + tax;
                }else if("INFANT".equals(priceType)){
                    price = (!"".equalsIgnoreCase(String.valueOf(flightList.get(0).getInPrice())) ? flightList.get(0).getInPrice() : 0 );
                    tax = (!"".equalsIgnoreCase(String.valueOf(flightList.get(0).getInTax())) ? flightList.get(0).getInTax() : 0 );
                    invamount = price + tax;
                }
                System.out.println(" invamount " + invamount);
                routing = util.GetRounting(flightList);
            }
        }
        if(airticketPassList.get(0).getAirticketAirline().getAirticketPnr().getAirticketBooking().getMaster() != null) {
            String masterId = airticketPassList.get(0).getAirticketAirline().getAirticketPnr().getAirticketBooking().getMaster().getId();
            System.out.println(" masterId " + masterId);
            invoiceDetailList = session.createQuery(InvoiceDetailQuery).setParameter("masterId", masterId).list();
        }
        
        if (invoiceDetailList.isEmpty()) {
            System.out.println(" invoiceDetailList.isEmpty() ");
            InvoiceDetailView invoiceDetailView = new InvoiceDetailView();
            invoiceDetailView.setInvoiceId("null");
            invoiceDetailView.setOwner(owner);
            invoiceDetailView.setRouting(routing);
            invoiceDetailView.setInvAmount(new BigDecimal(String.valueOf(invamount)));
            
//            invoiceDetailView.getInvTo();
            
            invoiceDetailViewList.add(invoiceDetailView);
            return invoiceDetailViewList;
        }
        for (int i = 0; i < invoiceDetailList.size() ; i++) {
            InvoiceDetailView invoiceDetailView = new InvoiceDetailView();
            invoiceDetailView.setOwner(owner);
            invoiceDetailView.setRouting(routing);
            invoiceDetailView.setInvAmount(new BigDecimal(String.valueOf(invamount)));
//            invoiceDetailView.setId(invoiceDetailList.get(i).getId());
            if(invoiceDetailList.get(i).getInvoice() != null){
                invTo = invoiceDetailList.get(i).getInvoice().getInvTo();
                if(!"".equalsIgnoreCase(invTo) && invTo != null){
                    List<Agent> agentlist = session.createQuery("from Agent agt where agt.code = :invTo").setParameter("invTo",invTo).list();
                    if(!agentlist.isEmpty()){
                        invTo = agentlist.get(0).getId();
                        invName = agentlist.get(0).getName();
                    }
                }
                BigDecimal invamounttemp = new BigDecimal(0);
                invoiceDetailTempList = session.createQuery(InvDetailQuery).setParameter("invoiceId", invoiceDetailList.get(i).getInvoice().getId()).list();
                for (int j = 0; j < invoiceDetailTempList.size() ; j++) {
                    if(invoiceDetailTempList.get(j).getAmount() != null){
                        invamounttemp = invamounttemp.add(invoiceDetailTempList.get(j).getAmount());
                        invoiceDetailView.setAmountInvoice(invamounttemp);
                    }
                }
                invoiceDetailView.setInvTo(invTo);
                invoiceDetailView.setInvName(invName);
                invoiceDetailView.setInvoiceId(invoiceDetailList.get(i).getInvoice().getId());
                invoiceDetailView.setInvNo(invoiceDetailList.get(i).getInvoice().getInvNo());
                invoiceDetailView.setInvDate(invoiceDetailList.get(i).getInvoice().getInvDate());
                invoiceDetailView.setDepartment(invoiceDetailList.get(i).getInvoice().getDepartment());
                invoiceDetailView.setDueDate(invoiceDetailList.get(i).getInvoice().getDueDate());
                invoiceDetailView.setStaffName(invoiceDetailList.get(i).getInvoice().getStaff() != null ? invoiceDetailList.get(i).getInvoice().getStaff().getName() : "");
                invoiceDetailView.setCredit(invoiceDetailList.get(i).getInvoice().getMAccTerm() != null ? invoiceDetailList.get(i).getInvoice().getMAccTerm().getName() : "");
                invoiceDetailView.setCreditValue(invoiceDetailList.get(i).getInvoice().getMAccTerm() != null ? String.valueOf(invoiceDetailList.get(i).getInvoice().getMAccTerm().getValue()) : "");
                invoiceDetailViewList.add(invoiceDetailView);
            }
        }
        session.close();
        this.sessionFactory.close();
        return invoiceDetailViewList;
    }

    public UtilityFunction getUtil() {
        return util;
    }

    public void setUtil(UtilityFunction util) {
        this.util = util;
    }

    @Override
    public String getListTicketFareFromInvno(String invNo) {
        String result ="";
        System.out.println(" invNo ::: "+invNo);
        String query = " From InvoiceDetail inv where inv.invoice.invNo = :invNo and inv.billableDesc.MBilltype.id = 1";
        Session session = this.sessionFactory.openSession();
        List<InvoiceDetail> invoiceDetails = session.createQuery(query).setParameter("invNo", invNo).list();
        System.out.println(" invoiceDetails.size() " + invoiceDetails.size());
        if (invoiceDetails.isEmpty()){
            return null;
        }else{
            String refitemid = invoiceDetails.get(0).getBillableDesc().getRefItemId();
            System.out.println(" refitemid ::: " + refitemid);
            String queryairline = " from AirticketAirline air where air.id in (:refItemId)";
            List<AirticketAirline> airticketAirlines = session.createQuery(queryairline).setParameter("refItemId", refitemid).list();
            System.out.println(" airticketAirlines.size() " + airticketAirlines.size());
            if (airticketAirlines.isEmpty()){
                return null;
            }else{
                String query2 = " from AirticketPassenger  airP where airP.airticketAirline.airticketPnr.airticketBooking.master.referenceNo = :refno";
                List<AirticketPassenger> ticketPassList = session.createQuery(query2).setParameter("refno", airticketAirlines.get(0).getAirticketPnr().getAirticketBooking().getMaster().getReferenceNo()).list();
                if (ticketPassList.isEmpty()){
                    return null;
                }else{
                    result =  buildTicketListHTML(ticketPassList,"",invNo);
                }
            }
        }
        session.close();
        this.sessionFactory.close();
        return result;
    }

    @Override
    public String getListTicketFareFromTicketNo(String ticketNo) {
        String result ="";
        System.out.println(" ticketNo ::: "+ticketNo);
        String query = " from TicketFareAirline t where t.ticketNo = :ticketNo";
        Session session = this.sessionFactory.openSession();
        List<TicketFareAirline> ticketFareAirlineList = session.createQuery(query).setParameter("ticketNo", ticketNo).list();
        
        if (ticketFareAirlineList.isEmpty()){
            return null;
        }else{
            result =  buildTicketNoListHTML(ticketFareAirlineList,ticketNo);
        }
        session.close();
        this.sessionFactory.close();
        return result;
    }
    
    public String buildTicketNoListHTML(List<TicketFareAirline> ticketFareAirlineList,String ticketNo){
        StringBuffer html = new StringBuffer();
        if (ticketFareAirlineList == null || ticketFareAirlineList.size() == 0) {
            return html.toString();
        } 
        int no = 1;
        String ticketid = "";
        String ticketno = "";
        String invamount = "";
        String invno = "";
        for(int i = 0 ; i < ticketFareAirlineList.size() ; i++ ){
            TicketFareAirline ticket = ticketFareAirlineList.get(i);
            ticketid = ticket.getId();
            ticketno = ticket.getTicketNo();
            invamount = String.valueOf(ticket.getInvAmount());
            List<TicketFareInvoice> ticketFareInvoices = ticket.getTicketFareInvoices();
            if(!ticketFareInvoices.isEmpty()){
                if(ticketFareInvoices.get(0).getInvoice() != null){
                    invno = ticketFareInvoices.get(0).getInvoice().getInvNo();
                }
            }
            if(!"".equalsIgnoreCase(ticketno)){
                String newrow
                    = "<tr>"
                    + "<td>" + no + "</td>"
                    + "<td>" + (ticketno == "null" ? "" : ticketno ) + "</td>"
                    + "<td>" + (invno == "null" ? "" : invno ) + "</td>"
                    + "<td class='money' >" + (invamount == "null" ? "" : invamount ) + "</td>"
                    + "<td class=\"text-center\" onclick=\"setSelectTicketNoDetail('" + ticketno + "','" + ticketid + "','" + invamount + "')\">"
                    + "<a href=\"\"><span class=\"glyphicon glyphicon-check\"></span></a>" + "</td>"
                    + "</tr>";
                System.out.println("newrow [[[[[[[ "+newrow +" ]]]]");
                html.append(newrow);
            }
            no++;
        }
        return html.toString();
    }

    @Override
    public List<RefundAirticketDetail> getRefundAirticketDetailFromTicketNo(String ticketNo) {
        String query = "From RefundAirticketDetail refd where refd.ticketNo = :ticketNo";
        Session session = this.sessionFactory.openSession();
        List<RefundAirticketDetail> refundAirticketDetails  = session.createQuery(query).setParameter("ticketNo", ticketNo).list();
        if (refundAirticketDetails.isEmpty()) {
            return null;
        }
//        session.close();
//        this.sessionFactory.close();
        return refundAirticketDetails;   
    }
    
    
    @Override
    public String getMAirlineAgentFromAirlineCode(String airlineCode) {
        String query = "From MAirlineAgent air where air.airlineCode = :aircode";
        Session session = this.sessionFactory.openSession();
        List<MAirlineAgent> MAirlineAgentList = session.createQuery(query).setParameter("aircode", airlineCode).list();
        if (MAirlineAgentList.isEmpty()) {
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return MAirlineAgentList.get(0).getId();
    }
}
