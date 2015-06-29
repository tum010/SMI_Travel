/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.MasterDao;
import com.smi.travel.datalayer.entity.Customer;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.Passenger;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
/**
 *
 * @author Surachai
 */
public class MasterImpl implements MasterDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;
    private static final String TODAYBOOKINGLIST = "from Master m";
    private static final String BOOKINGLISTFROMID = "from Master m where m.id = :masterid";
    private static final String BOOKINGLISTFROMREFNO = "from Master m where m.referenceNo = :refno";
    private static final String MAXREFNO = "select max(mas.referenceNo) from Master mas";

    @Override
    public List<Master> getListBooking() {
       Session session = this.sessionFactory.openSession();
       Date This_date = new Date();
       System.out.println("This_date :"+This_date);
       List<Master> BookingList = session.createQuery(TODAYBOOKINGLIST).list();
       return BookingList; 
    }
    
    @Override
    public List<Master> getListBookingFromID(String masterid) {
       Session session = this.sessionFactory.openSession();
       List<Master> BookingList = session.createQuery(BOOKINGLISTFROMID).setParameter("masterid", masterid).list();
       return BookingList; 
    }
    
    
    @Override
    public Master getBookingFromRefno(String refno) {
       Session session = this.sessionFactory.openSession();
       List<Master> BookingList = session.createQuery(BOOKINGLISTFROMREFNO).setParameter("refno", refno).list();
       if(BookingList.isEmpty()){
           return null;
       }
       return BookingList.get(0); 
    }

    @Override
    public int insertBooking(Master master,Passenger passenger) {
        int result = 0;
        Date thisDate=  new Date();
        master.setCreateDate(thisDate);
        
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            //save customer

            if (master.getCustomer() != null) {
                if (master.getCustomer().getId() == null) {
                    session.save(master.getCustomer());
                } else {
                    session.update(master.getCustomer());
                }
            }
            //save master
         
            session.save(master);
         
            
           
            //save passenger
            if(passenger.getId() == null){
                session.save(passenger);
            }else{
                session.update(passenger);
            }
            
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
    public int updateBooking(Master master,Passenger passenger) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            //save customer
            if (master.getCustomer() != null) {
                if (master.getCustomer().getId() == null) {
                    System.out.println("save master");
                    Customer cus  = master.getCustomer();       
                    session.save(master.getCustomer());
                } else {
                    System.out.println("update master");
                    session.update(master.getCustomer());
                }
            }
            System.out.println("master id :"+master.getId());
            session.merge(master);
           
            //save passenger
           
            
            if(passenger.getId() == null){
                System.out.println("save passenger");
                session.save(passenger);
            }else{
                System.out.println("update passenger : " + passenger.getId());
               // System.out.println("update customer id : " +;
                passenger.setCustomer(master.getCustomer());
                session.merge(passenger);
            }
            
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = 1;
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            result = 0;
        }
        return result;
    }

    
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int getMaxRefno() {
       Session session = this.sessionFactory.openSession();
       Integer result = 0;
       Query query = session.createQuery(MAXREFNO);
       System.out.println("query.uniqueResult() : "+query.uniqueResult());
       if(query.uniqueResult() != null){
           result =  Integer.parseInt(String.valueOf(query.uniqueResult()));
       }else{
           result = 250000;
       }
       System.out.println("Max refno :"+result);
       session.close();
       this.sessionFactory.close();
       return result; 
    }

    @Override
    public int LockAndUnLockBooking(String MasterID, int[] Flag) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

   
    
    
    
}
