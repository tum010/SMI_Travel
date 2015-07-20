/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.TicketFareAirlineDao;
import com.smi.travel.datalayer.entity.AirticketFlight;
import com.smi.travel.datalayer.entity.AirticketPassenger;
import com.smi.travel.datalayer.entity.BookingFlight;
import com.smi.travel.datalayer.entity.BookingPassenger;
import com.smi.travel.datalayer.entity.MAirlineAgent;
import com.smi.travel.datalayer.entity.PaymentAirticketFare;
import com.smi.travel.datalayer.entity.RefundAirticketDetail;
import com.smi.travel.datalayer.entity.TicketFareAirline;
import com.smi.travel.datalayer.view.entity.TicketFareView;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public int InsertTicketFare(TicketFareAirline ticket) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }
        return result;    
    }

    @Override
    public int UpdateTicketFare(TicketFareAirline ticket) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(ticket);
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }
        return result;    
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
    public TicketFareAirline getTicketFareFromTicketNo(String TicketNo) {
        TicketFareAirline ticketFare = new TicketFareAirline();
        String query = "from TicketFareAirline t where t.ticketNo = :ticketNo";
        Session session = this.sessionFactory.openSession();
        List<TicketFareAirline> ticketFareList = session.createQuery(query).setParameter("ticketNo", TicketNo).list();
        session.close();
        if (ticketFareList.isEmpty()) {
            return null;
        }else{
            ticketFare =  ticketFareList.get(0);
        }
        return ticketFare;
    }

    @Override
    public String getTicketFareBookingFromTicketNo(String TicketNo) {
        String result ="";
        AirticketPassenger ticketPass = new AirticketPassenger();
        String query = "from AirticketPassenger pass where pass.series1||pass.series2||pass.series3 = :ticketNo";
        Session session = this.sessionFactory.openSession();
        List<AirticketPassenger> ticketPassList = session.createQuery(query).setParameter("ticketNo", TicketNo).list();
        if (ticketPassList.isEmpty()) {
            return null;
        }else{ 
             for(int i = 0 ; i < ticketPassList.size() ; i++ ){
                 System.out.println("airline" + ticketPassList.get(i).getAirticketAirline().getMAirline().getCode());
                 String ticketAirline = getMAirlineAgentIdFromCode(ticketPassList.get(i).getAirticketAirline().getMAirline().getCode());
                 result = ticketPassList.get(i).getTicketFare() + "," 
                         + ticketPassList.get(i).getTicketTax() + "," 
                         + ticketPassList.get(i).getAirticketAirline().getTicketDate() + "," 
                         + ticketPassList.get(i).getTicketType() + "," 
                         + ticketAirline + "," 
                         + ticketPassList.get(i).getTicketFrom() + "," 
                         + ticketPassList.get(i).getMInitialname().getName()+ticketPassList.get(i).getLastName()+ " "+ticketPassList.get(i).getFirstName()+ ","   
                         + ticketPassList.get(i).getAirticketAirline().getAirticketPnr().getAirticketBooking().getMaster().getBookingType() //department 
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
        
        if (ticketPassList.isEmpty()) {
            return null;
        }else{
            result =  buildTicketListHTML(ticketPassList);
        }
        session.close();
        this.sessionFactory.close();
        return result;
    }
    
    public String buildTicketListHTML(List<AirticketPassenger> airPassengerList) {
        StringBuffer html = new StringBuffer();
        if (airPassengerList == null || airPassengerList.size() == 0) {
            return html.toString();
        } 
        String id = "" ;
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
        for(int i = 0 ; i < airPassengerList.size() ; i++ ){
            id = airPassengerList.get(i).getId();
            ticket = airPassengerList.get(i).getSeries1() 
                            + airPassengerList.get(i).getSeries2() 
                            + airPassengerList.get(i).getSeries3();
            ticketFare = String.valueOf(airPassengerList.get(i).getTicketFare());
            ticketTax = String.valueOf(airPassengerList.get(i).getTicketTax());
            ticketRouting = String.valueOf(airPassengerList.get(i).getTicketType());
            ticketBy = String.valueOf(airPassengerList.get(i).getTicketFrom());
            if(airPassengerList.get(i).getMInitialname() != null){
                name = airPassengerList.get(i).getMInitialname().getName() 
                                + airPassengerList.get(i).getLastName() + " "  
                                + airPassengerList.get(i).getFirstName();
            }
            List<AirticketFlight> airlines = new ArrayList<AirticketFlight>(airPassengerList.get(i).getAirticketAirline().getAirticketFlights());
            departDate = String.valueOf(airlines.get(i).getDepartDate());
            if(airlines.get(i).getMFlight() != null){
                ticketClass = airlines.get(i).getMFlight().getName();
            }
            if(airPassengerList.get(i).getAirticketAirline() != null){
                issueDate = String.valueOf(airPassengerList.get(i).getAirticketAirline().getTicketDate());
                if(airPassengerList.get(i).getAirticketAirline().getMAirline() != null){
                    airline = String.valueOf(airPassengerList.get(i).getAirticketAirline().getMAirline().getCode());
                }
                if(airPassengerList.get(i).getAirticketAirline().getAirticketPnr().getAirticketBooking().getMaster() != null){
                    department = String.valueOf(airPassengerList.get(i).getAirticketAirline().getAirticketPnr().getAirticketBooking().getMaster().getBookingType());
                }
            }

            String newrow
                    = "<tr>"
                    + "<td>" + ticket + "</td>"
                    + "<td>" + name + "</td>"
                    + "<td>" + ticketClass + "</td>"
                    + "<td>" + departDate + "</td>"
                    + "<td>" + ticketFare + "</td>"
                    + "<td>" + ticketTax + "</td>"
                    + "<td class=\"text-center\" onclick=\"setTicketDetail('" + ticket + "','" + ticketFare + "','" + ticketTax + "','" + issueDate + "','" + ticketRouting + "','" + airline + "','" + ticketBy + "','" + name + "','" + department + "')\">"
                    + "<a href=\"\"><span class=\"glyphicon glyphicon-check\"></span></a>" + "</td>"
                    + "</tr>";
            System.out.println("newrow [[[[[[[ "+newrow +" ]]]]");
            html.append(newrow);
        }

        return html.toString();
    }

    @Override
    public List<BookingFlight> getListFlightFromTicketNo(String ticketNo) {
        String result ="";
        System.out.println(" ticketNo ::: "+ticketNo);
        
        String Ticketquery = " from BookingPassenger  pass where pass.ticketnoS1||pass.ticketnoS2||pass.ticketnoS3 = :ticketNo";
        String Flightquery = " from BookingFlight  flight where  flight.bookingAirline.id = :airlineid";
        Session session = this.sessionFactory.openSession();
        List<BookingPassenger> ticketPassList = session.createQuery(Ticketquery).setParameter("ticketNo", ticketNo).list();
        
        if (ticketPassList.isEmpty()) {
            return null;
        }
        List<BookingFlight> FlightList = session.createQuery(Flightquery).setParameter("airlineid", ticketPassList.get(0).getBookingAirline().getId()).list();
        if (FlightList.isEmpty()) {
            return null;
        }
        return FlightList;
    }

    @Override
    public int validateSaveTicket(TicketFareAirline ticket) {
        int result = 0;
        String query = "from TicketFareAirline t where t.ticketNo = :TicketNo";
        Session session = this.sessionFactory.openSession();
        List<TicketFareAirline> ticketFareList = session.createQuery(query).setParameter("TicketNo",ticket.getTicketNo()).list();
        System.out.println("query : "+query );
        if (ticketFareList.isEmpty()){
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
        Session session = this.sessionFactory.openSession();
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
        if((ticket.getIssueDate() != null) &&(!"".equalsIgnoreCase(String.valueOf(ticket.getIssueDate())))){
            if(check == 1){query += " and ";}
            query += " t.issueDate "+queryOperation+" '"+Prefix_Subfix+ticket.getIssueDate()+Prefix_Subfix+"'";
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
//                ticketFareView.setInvoiceNo(listAirline.get(i).get);
//                ticketFareView.setDepartment(listAirline.get(i).get);
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

   
    
}
