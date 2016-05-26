/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.MasterDao;
import com.smi.travel.datalayer.entity.Customer;
import com.smi.travel.datalayer.entity.HistoryBooking;
import com.smi.travel.datalayer.entity.MBookingstatus;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.Passenger;
import com.smi.travel.datalayer.entity.ReceiptDetail;
import com.smi.travel.util.UtilityFunction;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
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
    private static final String BOOKSTATUSFROMREFNO = "from Master m where m.referenceNo = :refno";
    UtilityFunction util; 
    @Override
    public List<Master> getListBooking() {
       Session session = this.getSessionFactory().openSession();
       Date This_date = new Date();
       System.out.println("This_date :"+This_date);
       List<Master> BookingList = session.createQuery(TODAYBOOKINGLIST).list();
       return BookingList; 
    }
    
    @Override
    public List<Master> getListBookingFromID(String masterid) {
       Session session = this.getSessionFactory().openSession();
       List<Master> BookingList = session.createQuery(BOOKINGLISTFROMID).setParameter("masterid", masterid).list();
       return BookingList; 
    }
    
    
    @Override
    public Master getBookingFromRefno(String refno) {
       Session session = this.getSessionFactory().openSession();
       List<Master> BookingList = session.createQuery(BOOKINGLISTFROMREFNO).setParameter("refno", refno).list();
       if(BookingList.isEmpty()){
           return null;
       }
       return BookingList.get(0); 
    }

    @Override
    public int insertBooking(Master master,Passenger passenger ,HistoryBooking historyBooking) {
        int result = 0;
        Date thisDate=  new Date();
        master.setCreateDate(thisDate);
        
        try {
            Session session = this.getSessionFactory().openSession();
            setTransaction(session.beginTransaction());
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
            UtilityFunction utilty = new UtilityFunction();
            String detail = "Refno : " + master.getReferenceNo() + "\r\n"
                            + "FL : " + utilty.getCustomerName(master.getCustomer()) + "\r\n"
                            + "Agent : " + master.getAgent().getCode() + " : " + master.getAgent().getName();
            historyBooking.setDetail(detail);
            
            //save historyBooking
            session.save(historyBooking);
            
            getTransaction().commit();
            session.close();
            this.getSessionFactory().close();
            result = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }
        return result;
    }
    
     
    
    @Override
    public int updateBooking(Master master,Passenger passenger,HistoryBooking historyBooking) {
        int result = 0;
        try {
            Session session = this.getSessionFactory().openSession();
            setTransaction(session.beginTransaction());
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
            
            //save history
            session.save(historyBooking);
                        
            getTransaction().commit();
            session.close();
            this.getSessionFactory().close();
            result = 1;
        } catch (Exception ex) {
            getTransaction().rollback();
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
       Session session = this.getSessionFactory().openSession();
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
       this.getSessionFactory().close();
       return result; 
    }

    @Override
    public int LockAndUnLockBooking(Master master) {
       int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(master);
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
    public int[] getBookStatusFromRefno(String Refno) {
       Session session = this.getSessionFactory().openSession();
       int[] result = new int[7];
       List<Master> query= session.createQuery(BOOKSTATUSFROMREFNO).setParameter("refno", Refno).list();
       result[0] = Integer.parseInt(String.valueOf(query.get(0).getMBookingstatus().getId()));
       result[1] = Integer.parseInt(String.valueOf(query.get(0).getFlagAir()));
       result[2] = Integer.parseInt(String.valueOf(query.get(0).getFlagHotel()));
       result[3] = Integer.parseInt(String.valueOf(query.get(0).getFlagDaytour()));
       result[4] = Integer.parseInt(String.valueOf(query.get(0).getFlagLand()));
       result[5] = Integer.parseInt(String.valueOf(query.get(0).getFlagOther()));
       System.out.print("Status : "+result[0]);
       System.out.print("Air : "+result[1]);
       System.out.print("Hotel : "+result[2]);
       System.out.print("Daytour : "+result[3]);
       System.out.print("Land : "+result[4]);
       System.out.print("Other : "+result[5]);
       session.close();
       this.getSessionFactory().close();
       return result;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public String gennarateRefnoFromBookType(String bookingType) {
        String refno = "";
        Session session = this.sessionFactory.openSession();
        List<String> list = new LinkedList<String>();
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("yyMM");
        String querysql = "";
        System.out.println(" bookingType " + bookingType);
        if("O".equalsIgnoreCase(bookingType)){
            querysql = "select RIGHT(mas.DepartmentNo, 4) from master mas where mas.booking_type = 'O' and mas.DepartmentNo Like :departmentNo  ORDER BY RIGHT(mas.DepartmentNo, 4) desc";
        }else{
            querysql = "select RIGHT(mas.DepartmentNo, 4) from master mas where mas.booking_type = 'I' and mas.DepartmentNo Like :departmentNo  ORDER BY RIGHT(mas.DepartmentNo, 4) desc";
        }
        System.out.println(" querysql :: " + querysql);

        Query query = session.createSQLQuery(querysql);
        query.setParameter("departmentNo", "%"+ df.format(new Date()) + "%");
        query.setMaxResults(1);
        list = query.list();
        if (list.isEmpty() || list == null) {
            refno = df.format(new Date()) + "-" + "0001";
        } else {
            refno = String.valueOf(list.get(0));
            if (!refno.equalsIgnoreCase("") && !refno.equalsIgnoreCase("null")){
                int running = Integer.parseInt(refno) + 1;
                String temp = String.valueOf(running);
                for (int i = temp.length(); i < 4; i++) {
                    temp = "0" + temp;
                }
                refno = df.format(new Date()) + "-" + temp;
            }
        }
        if("O".equalsIgnoreCase(bookingType)){
            refno = "O"+refno;
        }else{
            refno = "W"+refno;
        }
        session.close();
        this.sessionFactory.close();
        return refno.replace("-","");    
    }

    @Override
    public List<ReceiptDetail> getReceiptDetailFromRefno(String refNo) {
        Session session = this.getSessionFactory().openSession();
        String query = "from ReceiptDetail rd where rd.invoiceDetail.billableDesc.billable.master.referenceNo = :refNo ";
        List<ReceiptDetail> receiptDetailList = session.createQuery(query)
                .setParameter("refNo", refNo)
                .list();

        session.close();
        this.getSessionFactory().close();
        return receiptDetailList;
    }

}
