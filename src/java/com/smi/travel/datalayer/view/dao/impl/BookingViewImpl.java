/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.view.dao.BookingViewDao;
import com.smi.travel.datalayer.view.entity.BookingView;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


/**
 *
 * @author Surachai
 */
public class BookingViewImpl implements BookingViewDao{
    private SessionFactory sessionFactory;
    private static final int MAX_ROW = 200;
    @Override
    public List<BookingView> getBookingList(String refno,String passFirst,String passLast,String username,String departmentID,String Bookdate,String status,String pnr,String ticketNo) {
        String query = "from BookingView book where ";
        String subquery = " book.refno in (select master.referenceNo from Passenger p ";
        Session session = this.sessionFactory.openSession();
        int check = 0;
        int checksub = 0;
        System.out.println("passLast : "+passLast);
        if ((refno != null) && (!"".equalsIgnoreCase(refno))) {
            query += " book.refno like '%" +refno +"%'";
            check = 1;
        }
        
        if ((departmentID != null) && (!"".equalsIgnoreCase(departmentID))) {
            if (check == 1) {query += " and ";}
            query += " book.departmentId = '" +departmentID +"'";
            check = 1;
        }
        
        if ((username != null) && (!"".equalsIgnoreCase(username))) {
            if (check == 1) {query += " and ";}
            query += " book.createBy = '" +username +"'";
            check = 1;
        }
        
        if ((Bookdate != null) && (!"".equalsIgnoreCase(Bookdate))) {
            if (check == 1) {query += " and ";}
            query += " book.createDate = '" +Bookdate +"'";
            check = 1;
        }
        
        if ((status != null) && (!"".equalsIgnoreCase(status))) {
            if (check == 1) {query += " and ";}
            query += " book.statusId = '" +status +"'";
            check = 1;
        }
        
        if ((pnr != null) && (!"".equalsIgnoreCase(pnr))) {
            if (check == 1) {query += " and ";}
            query += " book.pnr Like '%" +pnr +"%'";
            check = 1;
        }
        
        if ((ticketNo != null) && (!"".equalsIgnoreCase(ticketNo))) {
            if (check == 1) {query += " and ";}
            query += " book.ticketNo like '%" +ticketNo +"%'";
            check = 1;
        }
        
        if ((passFirst != null) && (!"".equalsIgnoreCase(passFirst))) {
            if (check == 1) {query += " and ";}
            subquery += " where p.customer.firstName like '%"+passFirst+"%'";
            checksub = 1;
            check = 1;
        }

        if ((passLast != null) && (!"".equalsIgnoreCase(passLast))) {
            if ((check == 1)&&(checksub != 1)) {query += " and ";}
            check = 1;
            if(checksub == 1){
                subquery += " and ";
            }else{
                 subquery += " where "; 
            }
            checksub = 1;
            subquery += "  p.customer.lastName like '%"+passLast+"%'";
        }
        
        
        if (check == 0) {
            query = query.replaceAll("where", " ");
        }
        if(checksub == 1){
            query += subquery +")";
        }
       
        query += " order by  ref_no desc ";
         System.out.println("query book view  : "+query);
       // List<BookingView> BookingList = session.createQuery(query).list();
        Query HqlQuery = session.createQuery(query);
        HqlQuery.setMaxResults(MAX_ROW);
        List<BookingView> BookingList = HqlQuery.list();
        
        for(BookingView bv :BookingList){
            System.out.println("Refno : "+bv.getRefno());
        }
        if (BookingList.isEmpty()) {
            return null;
        }
        return BookingList;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    
}
