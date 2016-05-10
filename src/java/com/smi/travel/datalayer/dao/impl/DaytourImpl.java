/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.DaytourDao;
import com.smi.travel.datalayer.entity.Daytour;
import com.smi.travel.datalayer.entity.DaytourBooking;
import com.smi.travel.datalayer.entity.DaytourExpense;
import com.smi.travel.datalayer.entity.DaytourPrice;
import com.smi.travel.datalayer.entity.MDefaultData;
import java.util.ArrayList;
import java.util.LinkedList;
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
public class DaytourImpl implements DaytourDao{
    
    private SessionFactory sessionFactory;
    private Transaction transaction;
    private static final String UsabilityTour ="from DaytourBooking t where t.daytour.id = :tour";
    private static final String TOURPRICEQUERY ="from DaytourPrice p where p.daytour.id = :tour";
    private static final String TOUREXPENSEQUERY ="from DaytourExpense p where p.daytour.id = :tour";
    private static final String TOURACTIVEQUERY ="from Daytour d where d.status = 'active' order by d.code asc ";
    private static final String DELETE_PRICEQUERY = "DELETE FROM DaytourPrice p WHERE p.id = :priceid";
    private static final String DELETE_DAYTOUR = "DELETE FROM Daytour p WHERE p.id = :daytourid";
    private static final String DELETE_PRICEQUERY_BYDAYTOUR = "DELETE FROM DaytourPrice p WHERE p.daytour.id = :priceid";
    private static final String DELETE_EXPENSEQUERY = "DELETE FROM DaytourExpense e WHERE e.id = :expenseid";
    private static final String DELETE_EXPENSEQUERY_BYDAYTOUR = "DELETE FROM DaytourExpense e WHERE e.daytour.id = :expenseid";
    private static final String GET_STAFFTOUR_QUERY ="from MDefaultData d where d.name = 'staff tour'";
    
    @Override
    public List<Daytour> searchTourList(Daytour tour, int option) {
        String query = "from Daytour t where ";
        String queryOperation = "";
        String Prefix_Subfix = "";
        int check = 0;
        if (option == 1) {
            queryOperation = " = ";
            Prefix_Subfix = "";
        } else if (option == 2) {
            queryOperation = " Like ";
            Prefix_Subfix = "%";
        }
        if ((tour.getCode() != null) && (!"".equalsIgnoreCase(tour.getCode()))) {
            query += " t.code " + queryOperation + " '"  + tour.getCode() + Prefix_Subfix + "'";
            check = 1;
        }
        if ((tour.getName() != null) && (!"".equalsIgnoreCase(tour.getName()))) {
            if (check == 1) {
                query += " and ";
            }
            query += " t.name " + queryOperation + " '"  + tour.getName() + Prefix_Subfix + "'";
            check = 1;
        }
        if (check == 0) {
            query = query.replaceAll("where", " ");
        }
        System.out.println("query : " + query);
        query = query +" order by t.name";
        Session session = this.sessionFactory.openSession();
        List<Daytour> list = session.createQuery(query).list();

        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public String InsertTour(Daytour tour, List<DaytourPrice> tourPrice) {
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(tour);
            //List<DaytourPrice> prices = new ArrayList<DaytourPrice>(tour.getDaytourPrices());
            List<DaytourExpense> expenses = new ArrayList<DaytourExpense>(tour.getDaytourExpenses());
            if(tourPrice != null){
                for(int i=0;i<tourPrice.size();i++){
                    session.save(tourPrice.get(i));
                }
            }
           
            for(int i=0;i<expenses.size();i++){
                session.save(expenses.get(i));
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
    public String UpdateTour(Daytour tour,String DelPriceID,String DelExpenseID, List<DaytourPrice> tourPrice) {
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            //update tour
            
            Daytour daytourToUpdate = (Daytour) BeanUtils.cloneBean(tour);
            daytourToUpdate.setDaytourPrices(null);
            daytourToUpdate.setDaytourExpenses(null);
            daytourToUpdate.setDaytourBookings(null);
            daytourToUpdate.setTourOperationDescs(null);
            daytourToUpdate.setAgentTourComissions(null);
            
            session.update(daytourToUpdate);
            //List<DaytourPrice> prices = new ArrayList<DaytourPrice>(tour.getDaytourPrices());
            List<DaytourExpense> expenses = new ArrayList<DaytourExpense>(tour.getDaytourExpenses());
            //update price
            if(tourPrice != null){
                for(int i=0;i<tourPrice.size();i++){
                    if(tourPrice.get(i).getId() == null){
                        session.save(tourPrice.get(i));
                    }else{
                        session.update(tourPrice.get(i));
                    }
                } 
            }
            
            //update expense
            for(int i=0;i<expenses.size();i++){
                if(expenses.get(i).getId() == null){
                    session.save(expenses.get(i));
                }else{
                    session.update(expenses.get(i));
                }
                
            }
/*
            //delete price
            if((DelPriceID != null) &&(!DelPriceID.equalsIgnoreCase(""))){
                String[] PriceList = DelPriceID.split(",");
                for(int i=0;i<PriceList.length;i++){         
                    Query query = session.createQuery("DELETE FROM DaytourPrice p WHERE p.id = "+PriceList[i]);
                    System.out.println("row result : "+query.executeUpdate());     
                }
            }
            //delete expense
            if((DelExpenseID != null) &&(!DelExpenseID.equalsIgnoreCase(""))){
                String[] ExpenseList = DelExpenseID.split(",");
                for(int i=0;i<ExpenseList.length;i++){       
                    Query query = session.createQuery("DELETE FROM DaytourExpense e WHERE e.id = "+ExpenseList[i]);
                    System.out.println("row result : "+query.executeUpdate());
                }
            }
 */           
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
    public String DeleteTour(Daytour tour) {
        String textResult = "";
        String result = "";
        String resultTourPrice = "";
        String resultTourExpense = "";
        
        if(CheckUsabilityTour(tour)){
            result = "Use Booking";
        }else{
            result = "";
        }
//        if(IsExistTourPrice(tour.getId())){
//            resultTourPrice = "Use Tour Price";
//        }else{
//            resultTourPrice = "";
//        }
//        if(IsExistTourExpense(tour.getId())){
//            resultTourExpense = "Use Tour Expense";
//        }else{
//            resultTourExpense = "";
//        }
        
        if("".equals(result) && "".equals(resultTourExpense) && "".equals(resultTourPrice)){
            try {
                Session session = this.sessionFactory.openSession();
                transaction = session.beginTransaction();
//                DeleteTourPriceByDaytourId(tour.getId());
//                DeleteTourExpenseByDaytourId(tour.getId());
                Query query1 = session.createQuery(DELETE_PRICEQUERY_BYDAYTOUR)
                            .setParameter("priceid", tour.getId());
                System.out.println("row result price : "+query1.executeUpdate());  
                Query query2 = session.createQuery(DELETE_EXPENSEQUERY_BYDAYTOUR)
                            .setParameter("expenseid", tour.getId());
                System.out.println("row result expence: "+query2.executeUpdate()); 
                Query query = session.createQuery(DELETE_DAYTOUR)
                            .setParameter("daytourid", tour.getId());
                System.out.println("row result tour : "+query.executeUpdate());  
                transaction.commit();
                session.close();
                this.sessionFactory.close();
                textResult = "delete successful"; 
            } catch (Exception ex) {
                ex.printStackTrace();
                textResult = "delete unsuccessful";
            }
        }else{
            if("Use Booking".equals(result)){
                textResult =  result;
            }
//            if("Use Tour Price".equals(resultTourPrice)){
//                textResult =  resultTourPrice;
//            } 
//            if("Use Tour Expense".equals(resultTourExpense)){
//                textResult =  resultTourExpense;
//            } 
        }
        return textResult;
    }
    
    private String DeleteTourPriceByDaytourId(String daytourID) {
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(DELETE_PRICEQUERY_BYDAYTOUR)
                            .setParameter("priceid", daytourID);
            System.out.println("row result price : "+query.executeUpdate());  
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = "success";
        } catch (Exception ex){
            ex.printStackTrace();
            result = "fail";
        }
        return result;
    }
    private String DeleteTourExpenseByDaytourId(String daytourID) {
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(DELETE_EXPENSEQUERY_BYDAYTOUR)
                            .setParameter("expenseid", daytourID);
            System.out.println("row result expence: "+query.executeUpdate());  
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = "success";
        } catch (Exception ex){
            ex.printStackTrace();
            result = "fail";
        }
        return result;
    }
    
    private boolean IsExistTourPrice(String TourID){
        boolean result ;
        Session session = this.sessionFactory.openSession();
        List<DaytourPrice> list = session.createQuery(TOURPRICEQUERY).setParameter("tour", TourID).list();
        
        if (list.isEmpty()) {
           
            result = false; 
        }else{      
            result = true; 
        }
        session.close();
        this.sessionFactory.close();
        return result;
    }
    
    private boolean IsExistTourExpense(String TourID){
        boolean result ;
        Session session = this.sessionFactory.openSession();
        List<DaytourPrice> list = session.createQuery(TOUREXPENSEQUERY).setParameter("tour", TourID).list();
        if (list.isEmpty()) {
            
            result = false; 
        }else{      
            result = true; 
        }
        session.close();
        this.sessionFactory.close();
        return result;
        
    }

    @Override
    public Boolean CheckUsabilityTour(Daytour tour) {
        Boolean  result;
        Session session = this.sessionFactory.openSession();
        List<DaytourBooking> list = session.createQuery(UsabilityTour).setParameter("tour", tour.getId()).list();
        if (list.isEmpty()) {
                result = false;
        }else{
            if(list.size() == 0){
                result = false;
            }else{
               result  = true;
            }
        }
        session.close();
        this.sessionFactory.close();
        return result;
    }
    
    @Override
    public List<Daytour> getTourListActive() {
        Session session = this.sessionFactory.openSession();
        List<Daytour> list = session.createQuery(TOURACTIVEQUERY).list();
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }
    
    @Override
    public List<DaytourPrice> getDaytourPrice(String TourID) {
        Session session = this.sessionFactory.openSession();
        List<DaytourPrice> list = session.createQuery(TOURPRICEQUERY).setParameter("tour", TourID).list();
        if (list.isEmpty()) {
                return null;
        }else{      
            return list;
        }
    }
    


    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public String DeleteTourPrice(String TourPriceID) {
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(DELETE_PRICEQUERY)
                            .setParameter("priceid", TourPriceID);
            System.out.println("row result : "+query.executeUpdate());  
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = "success";
        } catch (Exception ex){
            ex.printStackTrace();
            result = "fail";
        }
        return result;
    }

    @Override
    public String DeleteTourExpense(String TourExpenseID) {
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(DELETE_EXPENSEQUERY)
                            .setParameter("expenseid", TourExpenseID);
            System.out.println("row result : "+query.executeUpdate());  
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
    public Daytour getTourFromID(String TourID) {
        String query = "from Daytour t where t.id = :tourid";
        Daytour tout = null;
        Session session = this.sessionFactory.openSession();
        List<Daytour> ToutList = session.createQuery(query).setParameter("tourid", TourID).list();
        if (ToutList.isEmpty()) {
            return null;
        } else {
            tout = ToutList.get(0);
        }
        return tout;
        
    }
    
    public List<DaytourPrice> sortPriceList(List<DaytourPrice> priceList){
        List<DaytourPrice> sortPrice = new LinkedList<DaytourPrice>();
        
        return sortPrice;
    }

    @Override
    public String saveStafftour(String stafftourname) {
        
        String result = "";
        try {
            MDefaultData  stafftour = new MDefaultData();
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            stafftour.setId("1");
            stafftour.setName("staff tour");
            stafftour.setValue(stafftourname);
            session.update(stafftour);
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
    public String getStafftour() {
        String result ;
        Session session = this.sessionFactory.openSession();
        List<MDefaultData> list = session.createQuery(GET_STAFFTOUR_QUERY).list();
        
        if (list.isEmpty()) {
           
            result = ""; 
        }else{      
            result = list.get(0).getValue(); 
        }
        session.close();
        this.sessionFactory.close();
        return result;
    }




    
    
    
}
