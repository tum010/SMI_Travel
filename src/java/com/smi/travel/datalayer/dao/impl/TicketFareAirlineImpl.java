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
            setTransaction(session.beginTransaction());
           
            session.save(ticket);
            getTransaction().commit();
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
                 result = "AirticketPassenger" + ","  
                         + ticketPassList.get(i).getTicketFare() + "," 
                         + ticketPassList.get(i).getTicketTax() + "," 
                         + ticketPassList.get(i).getAirticketAirline().getAirticketPnr().getAirticketBooking().getMaster().getBookingType() + "," //department 
                         + ticketPassList.get(i).getAirticketAirline().getMAirline().getCode() //airline
                         ;
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

        for(int i = 0 ; i < airPassengerList.size() ; i++ ){
            System.out.println(" airPassengerList.get(i).getId() "+airPassengerList.get(i).getId());
            System.out.println(" airPassengerList.get(i).getSeries1() "+airPassengerList.get(i).getSeries1());
            System.out.println(" aairPassengerList.get(i).getLastName() "+airPassengerList.get(i).getLastName());
            String id = airPassengerList.get(i).getId();
            String ticket = airPassengerList.get(i).getSeries1() 
                            + airPassengerList.get(i).getSeries2() 
                            + airPassengerList.get(i).getSeries3();
            String name = airPassengerList.get(i).getMInitialname().getName() 
                            + airPassengerList.get(i).getLastName() + " "  
                            + airPassengerList.get(i).getFirstName();
            List<AirticketFlight> airlines = new ArrayList<AirticketFlight>(airPassengerList.get(i).getAirticketAirline().getAirticketFlights());
            String ticketClass = airlines.get(i).getMFlight().getName();
            String departDate = String.valueOf(airlines.get(i).getDepartDate());
            String ticketFare = String.valueOf(airPassengerList.get(i).getTicketFare());
            String ticketTax = String.valueOf(airPassengerList.get(i).getTicketTax());

            String newrow
                    = "<tr>"
                    + "<td>" + ticket + "</td>"
                    + "<td>" + name + "</td>"
                    + "<td>" + ticketClass + "</td>"
                    + "<td>" + departDate + "</td>"
                    + "<td>" + ticketFare + "</td>"
                    + "<td>" + ticketTax + "</td>"
                    + "<td class=\"text-center\" onclick=\"setTicketDetail('" + ticket + "','" + name + "','" + ticketClass + "','" + departDate + "','" + ticketFare + "','" + ticketTax + "')\">"
                    + "<a href=\"\"><span class=\"glyphicon glyphicon-check\"></span></a>" + "</td>"
                    + "</tr>";
            html.append(newrow);
        }

        return html.toString();
    }

    @Override
    public List<BookingFlight> getListFlightFromTicketNo(String TicketNo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int validateTicket(String TicketNo) {
        int result = 0;
        String query = "from TicketFareAirline t where t.ticketNo = :TicketNo";
        Session session = this.sessionFactory.openSession();
        List<TicketFareAirline> ticketFareList = session.createQuery(query).setParameter("TicketNo",TicketNo).list();
        System.out.println("query : "+query );
        if (ticketFareList.isEmpty()) {
            result = 0;
        }else{
            result = 1;
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
        if((ticket.getRounting()!= null) &&(!"".equalsIgnoreCase(ticket.getRounting()))){
            if(check == 1){query += " and ";}
            query += " t.ticketRounting "+queryOperation+" '"+Prefix_Subfix+ticket.getRounting()+Prefix_Subfix+"'";
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
//        if((ticket.getDepartment() != null) &&(!"".equalsIgnoreCase(ticket.getDepartment()))){
//            if(check == 1){query += " and ";}
//            query += " t.ticketNo "+queryOperation+" '"+Prefix_Subfix+ticket.getDepartment()+Prefix_Subfix+"'";
//            check =1;
//        } 
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
                ticketFareView.setRounting(String.valueOf(listAirline.get(i).getTicketRounting()));
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

   
    
}
