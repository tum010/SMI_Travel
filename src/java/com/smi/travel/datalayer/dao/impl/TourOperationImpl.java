/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.TourOperationDao;
import com.smi.travel.datalayer.entity.DaytourBooking;
import com.smi.travel.datalayer.entity.DaytourBookingPrice;
import com.smi.travel.datalayer.entity.TourOperationDesc;
import com.smi.travel.datalayer.entity.TourOperationDriver;
import com.smi.travel.datalayer.entity.TourOperationExpense;
import com.smi.travel.util.UtilityFunction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Surachai
 */
public class TourOperationImpl implements TourOperationDao {

    private SessionFactory sessionFactory;
    private Transaction transaction;
    private static final int MAX_JOB = 100;
    private static final String TOURJOB_QUERY = "from DaytourBooking DB Where DB.tourDate >= :thisdate and (DB.master.MBookingstatus.id = '1' or DB.master.MBookingstatus.id = '2' ) Group By DB.daytour.code , DB.tourDate order by DB.tourDate asc , DB.daytour.code asc  ";
    private static final String TOURDETAIL_QUERY = "from DaytourBooking DB where DB.daytour.id = :tourid and DB.tourDate = :date and ( DB.master.MBookingstatus.id = '1' or DB.master.MBookingstatus.id = '2' ) and DB.MItemstatus.id = '1' ";
    private static final String TOUROPERATIONDETAIL_QUERY = "from TourOperationDesc T where T.daytour.id = :tourid and T.tourDate = :date";
    private static final String UPDATE_PICKUP_ORDER = "UPDATE DaytourBooking DB set DB.pickupOrder = :order Where DB.id = :bookid";
    private static final String DELETE_BOOKDRIVERQUERY = "DELETE FROM TourOperationDriver bp WHERE bp.id = :bookpriceid";
    private static final String DELETE_BOOKPRICEQUERY = "DELETE FROM DaytourBookingPrice bp WHERE bp.id = :bookpriceid";

    @Override
    public List<DaytourBooking> getTourJob() {
        Session session = this.sessionFactory.openSession();
        Date thisdate = new Date();
        Query q = session.createQuery(TOURJOB_QUERY).setParameter("thisdate", thisdate);
        q.setMaxResults(MAX_JOB);
        List<DaytourBooking> list = q.list();
        System.out.println(" list ================ " + list.size());
        if (list.isEmpty()) {
            return null;
        } else {
            return list;
        }
    }

    @Override
    public List<DaytourBooking> getTourDetail(String TourID, String TourDate) {
        UtilityFunction util = new UtilityFunction();
        Session session = this.sessionFactory.openSession();
        List<DaytourBooking> list = session.createQuery(TOURDETAIL_QUERY)
                .setParameter("tourid", TourID)
                .setParameter("date", util.convertStringToDate(TourDate))
                .list();
        if (list.isEmpty()) {
            return null;
        } else {
            return list;
        }
    }

    @Override
    public TourOperationDesc getTouroperation(String TourID, String TourDate) {
        UtilityFunction util = new UtilityFunction();
        Session session = this.sessionFactory.openSession();
        List<TourOperationDesc> list = session.createQuery(TOUROPERATIONDETAIL_QUERY)
                .setParameter("tourid", TourID)
                .setParameter("date", util.convertStringToDate(TourDate))
                .list();
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public String insertTourOperation(TourOperationDesc TourInfo, List<DaytourBooking> BookList,List<TourOperationDriver> DriverList) {
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(TourInfo);

           // List<TourOperationDriver> DriverList = new ArrayList<TourOperationDriver>(TourInfo.getTourOperationDrivers());
            if(DriverList != null){
                for (int i = 0; i < DriverList.size(); i++) {
                    session.save(DriverList.get(i));
                }                
            }


            List<TourOperationExpense> ExpenseList = new ArrayList<TourOperationExpense>(TourInfo.getTourOperationExpenses());
            for (int i = 0; i < ExpenseList.size(); i++) {
                if (!"".equals(ExpenseList.get(i).getDescription())) {
                    session.save(ExpenseList.get(i));
                }
            }

            for (int i = 0; i < BookList.size(); i++) {
                Query query = session.createQuery(UPDATE_PICKUP_ORDER);
                query.setParameter("order", BookList.get(i).getPickupOrder());
                query.setParameter("bookid", BookList.get(i).getId());
                int UpdateResult = query.executeUpdate();
                if (UpdateResult == 0) {
                    result = "fail : not found book record :" + BookList.get(i).getId();
                    transaction.rollback();
                    session.close();
                    this.sessionFactory.close();
                    return result;
                }
            }

            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = "success";
        } catch (Exception ex) {
            ex.printStackTrace();
            result = "fail";
        }
        return result;
    }

    @Override
    public String updateTourOperation(TourOperationDesc TourInfo, List<DaytourBooking> BookList,List<TourOperationDriver> DriverList) {
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();

            TourOperationDesc tourInfoToUpdate = (TourOperationDesc) BeanUtils.cloneBean(TourInfo);
            tourInfoToUpdate.setTourOperationDrivers(null);
            tourInfoToUpdate.setTourOperationExpenses(null);

            session.update(tourInfoToUpdate);

            //List<TourOperationDriver> DriverList = new ArrayList<TourOperationDriver>(TourInfo.getTourOperationDrivers());
            if(DriverList != null){
                for (int i = 0; i < DriverList.size(); i++) {
                    System.out.println("staff name : "+DriverList.get(i).getStaff().getId());
                    if (DriverList.get(i).getId() == null) {
                        session.save(DriverList.get(i));
                    } else {
                        session.update(DriverList.get(i));
                    }
                }
            }

            List<TourOperationExpense> ExpenseList = new ArrayList<TourOperationExpense>(TourInfo.getTourOperationExpenses());
            for (int i = 0; i < ExpenseList.size(); i++) {
                if (ExpenseList.get(i).getId() == null) {
                    if (!"".equals(ExpenseList.get(i).getDescription())) {
                        session.save(ExpenseList.get(i));
                    }
                } else {
                    session.update(ExpenseList.get(i));
                }
            }

            for (int i = 0; i < BookList.size(); i++) {
                Query query = session.createQuery(UPDATE_PICKUP_ORDER);
                query.setParameter("order", BookList.get(i).getPickupOrder());
                query.setParameter("bookid", BookList.get(i).getId());
                int UpdateResult = query.executeUpdate();
                if (UpdateResult == 0) {
                    result = "fail : not found book record :" + BookList.get(i).getId();
                    transaction.rollback();
                    session.close();
                    this.sessionFactory.close();
                    return result;
                }
            }

            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = "success";
        } catch (Exception ex) {
            ex.printStackTrace();
            result = "fail";
        }
        return result;
    }

    private void updatePickup(List<DaytourBooking> BookList) {
        Session session = this.sessionFactory.openSession();
        transaction = session.beginTransaction();
        for (int i = 0; i < BookList.size(); i++) {
            session.update(BookList.get(i));
            transaction.commit();
            session.close();
            this.sessionFactory.close();

        }
    }

    @Override
    public String deleteBookDriver(String driverId) {
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(DELETE_BOOKDRIVERQUERY)
                    .setParameter("bookpriceid", driverId);
            System.out.println("row result : " + query.executeUpdate());
            transaction.commit();
            session.close();
            result = "success";
        } catch (Exception ex) {
            ex.printStackTrace();
            result = "fail";
        }
        return result;
    }

    @Override
    public String deleteBookExpen(String expenId) {
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            TourOperationExpense expense = new TourOperationExpense();
            expense.setId(expenId);
            session.delete(expense);
            transaction.commit();
            session.close();
            result = "success";
        } catch (Exception ex) {
            ex.printStackTrace();
            result = "fail";
        }
        return result;
    }
    
    @Override
    public List<DaytourBooking> SortBookOrder(List<DaytourBooking> data) {
        List<DaytourBooking> sortBooking = new ArrayList<DaytourBooking>();
        if(data == null){
            return data;
        }else if(data.size() == 0){
            return data;
        }
        List Dataindex = new ArrayList();
        
        for (int i = 0; i < data.size(); i++) {
            if(data.get(i).getPickupOrder() == null){
                Dataindex.add(0);
            }else{
                Dataindex.add(data.get(i).getPickupOrder());
            }
            
        }
        
        Collections.sort(Dataindex);
        for (int i = 0; i < Dataindex.size(); i++) {
            for (int j = 0; j < data.size(); j++) {
                int order = data.get(j).getPickupOrder() == null ? 0:data.get(j).getPickupOrder();
                if (Dataindex.get(i).equals(order)) {
//                    System.out.println("order no : " + data.get(j).getPickupOrder());
                   if(!CheckDupilate(sortBooking,data.get(j))){
                        sortBooking.add(data.get(j));
                   }
                }
            }
        }

        return sortBooking;
    }
    
    @Override
    public List<TourOperationDriver> SortDriver(List<TourOperationDriver> data) {
        List<TourOperationDriver> sortDriver = new ArrayList<TourOperationDriver>();
        if(data == null){
            return data;
        }else if(data.size() == 0){
            return data;
        }
        List Dataindex = new ArrayList();
        
        for (int i = 0; i < data.size(); i++) {
            Dataindex.add(Integer.parseInt(data.get(i).getId()));
            
        }
        
        Collections.sort(Dataindex);
        for(int i=0;i<Dataindex.size();i++){
            System.out.println("index : "+Dataindex.get(i));
        }
        for (int i = 0; i < Dataindex.size(); i++) {
            for (int j = 0; j < data.size(); j++) {
                if (Dataindex.get(i).equals(Integer.parseInt(data.get(j).getId()))) {
//                    System.out.println("order no : " + data.get(j).getId());
                    sortDriver.add(data.get(j));  
                }
            }
        }

        return sortDriver;
    }
    
    @Override
    public List<TourOperationExpense> SortExpense(List<TourOperationExpense> data) {
        List<TourOperationExpense> sortDriver = new ArrayList<TourOperationExpense>();
        if(data == null){
            return data;
        }else if(data.size() == 0){
            return data;
        }
        List Dataindex = new ArrayList();
        
        for (int i = 0; i < data.size(); i++) {
            if(data.get(i).getDescription() == null){
                Dataindex.add("");
            }else{
                Dataindex.add(data.get(i).getDescription());
            }
            
        }
        
       
        
        Collections.sort(Dataindex);
        for(int i=0;i<Dataindex.size();i++){
             System.out.println("dataindex : "+Dataindex.get(i));
        }
        for (int i = 0; i < Dataindex.size(); i++) {
            for (int j = 0; j < data.size(); j++) {
                System.out.println("compare : "+Dataindex.get(i)+" , "+data.get(j).getDescription());
                if (Dataindex.get(i).equals(data.get(j).getDescription())) {
//                    System.out.println("order no : " + data.get(j).getId());
                    sortDriver.add(data.get(j));  
                }
            }
        }

        return sortDriver;
    }
    
    @Override
    public String[] calculatePassengerDaytour(List<DaytourBookingPrice> DriverList) {
        String result[] = new String[3];
        int adult = 0;
        int child = 0;
        int infant = 0;
        UtilityFunction util = new UtilityFunction();
        int Pricesum = 0;
        for (int i = 0; i < DriverList.size(); i++) {
            DaytourBookingPrice price = DriverList.get(i);

            if (price.getMPricecategory() != null) {
                String passType = price.getMPricecategory().getName();
                if ("ADULT".equalsIgnoreCase(passType)) {
                    adult += (price.getQty() == null ? 0 : price.getQty());
                } else if ("CHILD".equalsIgnoreCase(passType)) {
                    child += (price.getQty() == null ? 0 : price.getQty());
                } else if ("INFANT".equalsIgnoreCase(passType)) {
                    infant += (price.getQty() == null ? 0 : price.getQty());
                }
            }

        }
        result[0] = String.valueOf(adult);
        result[1] = String.valueOf(child);
        result[2] = String.valueOf(infant);
        return result;
    }
    
    public boolean CheckDupilate(List<DaytourBooking> data , DaytourBooking newdata){
        for(int i=0;i<data.size();i++){
            DaytourBooking compare = data.get(i);
            if(compare.getId().equalsIgnoreCase(newdata.getId())){
//                System.out.println("id : "+compare.getId() +" is dup");
                return true;
            } 
        }
        return false;
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

}
