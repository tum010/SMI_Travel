/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.mysql.jdbc.StringUtils;
import com.smi.travel.datalayer.dao.OtherBookingDao;
import com.smi.travel.datalayer.entity.Customer;
import com.smi.travel.datalayer.entity.MStockStatus;
import com.smi.travel.datalayer.entity.OtherBooking;
import com.smi.travel.datalayer.entity.Product;
import com.smi.travel.datalayer.entity.Stock;
import com.smi.travel.datalayer.entity.StockDetail;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.view.entity.OtherTicketView;
import com.smi.travel.util.UtilityFunction;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
/**
 *
 * @author Surachai
 */
public class OtherBookingImpl implements OtherBookingDao{
    
    private SessionFactory sessionFactory;
    private Transaction transaction;
    private UtilityFunction util;
    private static final String GET_BOOKOTHER_QUERY = "from OtherBooking ot where ot.otherDate >= :startdate and ot.otherDate <= :enddate ";
    
    @Override
    public List<OtherBooking> getListBookingOtherFromRefno(String refno) {
        String query = "from OtherBooking other where other.master.referenceNo = :refno";
        Session session = this.sessionFactory.openSession();

        List<OtherBooking> OtherList = session.createQuery(query).setParameter("refno", refno).list();  
        if (OtherList.isEmpty()) {
            return null;
        }
        return OtherList;
    }

    @Override
    public OtherBooking getBookDetailOtherFromID(String OtherBookingID) {
        String query = "from OtherBooking other where other.id = :otherID";
        Session session = this.sessionFactory.openSession();
        OtherBooking result = new OtherBooking();
        List<OtherBooking> OtherList = session.createQuery(query).setParameter("otherID", OtherBookingID).list();
        if (OtherList.isEmpty()) {
            return null;
        }
        result = OtherList.get(0);
        return result;
    }

    @Override
    public int insertBookDetailOther(OtherBooking otherbook, SystemUser user) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(otherbook);
            transaction.commit();
            session.close();
            result = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }

        return result;
    }
    
    @Override
    public String saveStockDetailOther(OtherBooking otherbook, SystemUser user) {
        util = new UtilityFunction();
        String result = "";
        try {       
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            String date = util.convertDateToString(otherbook.getOtherDate());
            String productId = otherbook.getProduct().getId();
            List<StockDetail> stockDetailList = getStockByDate(productId, date, session);
            if(stockDetailList.isEmpty()){
                result = "notStock";
                return result;
            }
            int adultQty = otherbook.getAdQty();
            int childQty = otherbook.getChQty();
            int infantQty = otherbook.getInQty();
            int adultCount = 0;
            int childCount = 0;
            int infantCount = 0;
            int noneCount = 0;
            for(int i=0;i<stockDetailList.size();i++){
                String typeName = stockDetailList.get(i).getTypeId().getName();
                if("ADULT".equalsIgnoreCase(typeName)){
                    adultCount++;
                } else if("CHILD".equalsIgnoreCase(typeName)){
                    childCount++;
                } else if("INFANT".equalsIgnoreCase(typeName)){
                    infantCount++;
                } else if("NONE".equalsIgnoreCase(typeName)){
                    noneCount++;
                }
            }
            
            int ad = 0;
            int ch = 0;
            int inf = 0;
            OtherBooking otherBooking = new OtherBooking();
            String otherbookId = otherbook.getId();
            otherBooking.setId(otherbookId);
            
            MStockStatus mstockStatus = new MStockStatus();
            mstockStatus.setId("2");
            // pickupDate must use thisdate
            String pickupDate = util.convertDateToString(new Date());
            for (int i = 0; i < stockDetailList.size(); i++){
                StockDetail stockDetail = stockDetailList.get(i);
                String typeName = stockDetail.getTypeId().getName();
                if("ADULT".equalsIgnoreCase(typeName)){
                    if(ad < adultQty){
                        stockDetail.setPickupDate(util.convertStringToDate(pickupDate));
                        stockDetail.setOtherBooking(otherbook);                   
                        stockDetail.setMStockStatus(mstockStatus);
                        stockDetail.setStaff(user);
                        session.update(stockDetailList.get(i));
                        ad++;
                    }
                    
                } else if("CHILD".equalsIgnoreCase(typeName)){
                    if(ch < childQty){
                        stockDetail.setPickupDate(util.convertStringToDate(pickupDate));
                        stockDetail.setOtherBooking(otherbook);                   
                        stockDetail.setMStockStatus(mstockStatus);
                        stockDetail.setStaff(user);
                        session.update(stockDetailList.get(i));
                        ch++;
                    }
                    
                } else if("INFANT".equalsIgnoreCase(typeName)){
                    if(inf < infantQty){
                        stockDetail.setPickupDate(util.convertStringToDate(pickupDate));
                        stockDetail.setOtherBooking(otherbook);                   
                        stockDetail.setMStockStatus(mstockStatus);
                        stockDetail.setStaff(user);
                        session.update(stockDetailList.get(i));
                        inf++;
                    }
                } 
            }
                            
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = stockDetailList.get(0).getStock().getId();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            result = "fail";
        }
        
        return result;
    }
    
    private List<StockDetail> getStockByDate(String Id, String date, Session session) {
        List<StockDetail> fail = new ArrayList<StockDetail>();
        try {       
            String queryDate = "from Stock s where s.effectiveFrom <= '" + date + "' and s.effectiveTo >= '" + date + "' and s.product.id = " + Id;
            List<Stock> stockList =  session.createQuery(queryDate).list();
            if(stockList.isEmpty()){                
                return fail;
            }
            
            for(int i=0;i<=stockList.size();i++){
                String productId = stockList.get(i).getProduct().getId();
                int result = getIsStock(productId, session);
                if(result == 1){
                    String stockDetailId = stockList.get(i).getId();
                    List<StockDetail> stockDetailList = getStockDetail(stockDetailId, session);
                    return stockDetailList;
                }
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return fail;
    }
    
    private int getIsStock(String id, Session session) {
        int result = 0;
        try {
            String query = "from Product p where p.id = " + id + " and p.isStock = 1";
            List<Product> productList = session.createQuery(query).list();
            if(productList.isEmpty()){
                result = 0;
                return result;  
            } else {
                result = 1;
                return result;
            }         
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    private List<StockDetail> getStockDetail(String stockId, Session session) {
        List<StockDetail> fail = new ArrayList<StockDetail>();
        try {
            String query = "from StockDetail s where s.stock.id = " + stockId + " and s.MStockStatus.id = 1";
            List<StockDetail> stockDetailList = session.createQuery(query).list();
            if(stockDetailList.isEmpty()){
                return fail;  
            }
            return stockDetailList;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return fail;
    }
    
    @Override
    public List<OtherTicketView> getListStockDetail(String stockId) {
        List<OtherTicketView> ticketList = new ArrayList<OtherTicketView>();
        try{
            Session session = this.sessionFactory.openSession();
            String query = "from StockDetail s where s.stock.id = " + stockId + " and s.MStockStatus.id = 2";
            List<StockDetail> stockDetailList = session.createQuery(query).list();            
            if(stockDetailList.isEmpty()){
                return ticketList;  
            }
            
            for(int i=0;i<stockDetailList.size();i++){
                StockDetail stockDetail = stockDetailList.get(i);
                OtherTicketView otherTicketView = new OtherTicketView();
                otherTicketView.setAddDate(stockDetail.getPickupDate());
                otherTicketView.setTicketCode(stockDetail.getCode());
                otherTicketView.setTypeName(stockDetail.getTypeId().getName());
                ticketList.add(otherTicketView);
            }
           
            session.close();
            this.sessionFactory.close();
            return ticketList;
        } catch (Exception ex){
            ex.printStackTrace();
        }
        
        return ticketList;
    }
    
    @Override
    public int updateBookDetailOther(OtherBooking otherbook) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(otherbook);
            transaction.commit();
            session.close();
            result = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }
        return result;
    }

    @Override
    public int cancelBookDetailOther(String otherID) {
        int result = 0;
        Date thisDate = new Date();
        String hql = "UPDATE OtherBooking other set other.cancelDate = :thisdate ,  other.status.id = 2"  + 
             "WHERE other.id = :otherbookID";
        try {
            Session session = this.sessionFactory.openSession();
             Query query = session.createQuery(hql);
             query.setParameter("thisdate", thisDate);
             query.setParameter("otherbookID", otherID);
             result = query.executeUpdate();
             System.out.println("Rows affected: " + result);
         } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }

        return result;
    }
    
    @Override
    public int enableBookDetailOther(String otherID) {
        int result = 0;
        String hql = "UPDATE OtherBooking other set other.cancelDate = :thisdate ,  other.status.id = 1"  + 
             "WHERE other.id = :otherbookID";
        try {
            Session session = this.sessionFactory.openSession();
             Query query = session.createQuery(hql);
             query.setParameter("thisdate", null);
             query.setParameter("otherbookID", otherID);
             result = query.executeUpdate();
             System.out.println("Rows affected: " + result);
         } catch (Exception ex) {
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
    public List<OtherBooking> getListBookingAll() {
        String query = "from OtherBooking other order by other.id DESC";
        Session session = this.sessionFactory.openSession();

        Query OtherList = session.createQuery(query);  
        OtherList.setFirstResult(0);
        OtherList.setMaxResults(500);
        List list = OtherList.list();
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<OtherBooking> getListBookingOtherComission(String StartDate, String EndDate, String agentID, String guideID) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        String query = GET_BOOKOTHER_QUERY;
        System.out.println("agentID + "+agentID);
        System.out.println("guideID + "+guideID);
        if((agentID != null) &&(!"".equalsIgnoreCase(agentID))){
            query += " and ot.agent.id = "+agentID;
        }
        if((guideID != null) &&(!"".equalsIgnoreCase(guideID))){
            query += " and ot.guide.id = "+guideID;
        }
        query += " order by ot.otherDate,ot.product.code,ot.master.referenceNo";
        System.out.println("query : "+query);
        List<OtherBooking> list = session.createQuery(query)
                .setParameter("startdate", util.convertStringToDate(StartDate))
                .setParameter("enddate", util.convertStringToDate(EndDate))
                .list();
        if (list.isEmpty()) {
            return null;
        }

        return list;
    }

    @Override
    public String saveOtherBookCommission(List<OtherBooking> BookList) {
        String result = "";
        String queryupdate = "";
        
        int checkQuery = 0;
        String prefix = "";

           
        Session session = this.sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            for (int i = 0; i < BookList.size(); i++) {
                queryupdate = "UPDATE OtherBooking ot Set";
                prefix = "";
                checkQuery = 0;
                
                OtherBooking book = BookList.get(i);
                if ((book.getGuide() != null) && (book.getGuide().getId() != null) ) {
                    queryupdate += " ot.guide.id = " + book.getGuide().getId();
                    if (checkQuery == 0) {
                        checkQuery = 1;
                        prefix = ",";
                    }
                }
                if ((book.getAgent() != null) && (book.getAgent().getId() != null) ) {

                    queryupdate += prefix + " ot.agent.id = " + book.getAgent().getId();
                    if (checkQuery == 0) {
                        checkQuery = 1;
                        prefix = ",";
                    }
                }

                if ((book.getAgentCommission() != null)) {
                    queryupdate += prefix + " ot.agentCommission = " + book.getAgentCommission();
                    if (checkQuery == 0) {
                        checkQuery = 1;
                        prefix = ",";
                    }
                }

                if ((book.getGuideCommission() != null)) {
                    queryupdate += prefix + " ot.guideCommission = " + book.getGuideCommission();
                    if (checkQuery == 0) {
                        checkQuery = 1;
                        prefix = ",";
                    }
                }

                if ((book.getRemarkGuideCommission() != null)) {
                    queryupdate += prefix + " ot.remarkGuideCommission = '" + book.getRemarkGuideCommission() + "' ";
                    if (checkQuery == 0) {
                        checkQuery = 1;
                        prefix = ",";
                    }
                }

                if ((book.getRemarkAgentCommission() != null)) {
                    queryupdate += prefix + " ot.remarkAgentCommission = '" + book.getRemarkAgentCommission() + "' ";
                    if (checkQuery == 0) {
                        checkQuery = 1;
                        prefix = ",";
                    }
                }

                if (checkQuery == 1) {
                    queryupdate += " Where ot.id = " + book.getId();
                    System.out.println("queryupdate : "+queryupdate);
                    Query query = session.createQuery(queryupdate);
                    int UpdateResult = query.executeUpdate();
                    if (UpdateResult == 0) {
                        result = "fail : not found book record";
                        transaction.rollback();
                        session.close();
                        this.sessionFactory.close();
                        return result;
                    }
                }

            }
            transaction.commit();
            result = "success";
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();

            result = "fail";
        }
        session.close();
        this.sessionFactory.close();
        return result;
    }

    @Override
    public Boolean CheckUsabilityCoupon(String CouponId) {
        
        String result = "";
        String query = "from Coupon c where c.otherBooking = '" + CouponId + "'";
        
        Session session = this.sessionFactory.openSession();

        Query CouponList = session.createQuery(query); 
        List list = CouponList.list();
        if(list.isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public List<OtherBooking> searchOtherBooking(Customer customer, int option) {
        String query = "from OtherBooking ob where ";
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

        if (!StringUtils.isNullOrEmpty(customer.getFirstName())) {
            query += " ob.firstName " + queryOperation + " '" + Prefix_Subfix + customer.getFirstName() + Prefix_Subfix + "'";
            check = 1;
        }

        if (check == 0) {
            query = query.replaceAll("where", " ");
        }
        System.out.println("searchOtherBooking query : " + query);
        //  List<SystemUser> list = getHibernateTemplate().find(query);
        org.hibernate.Session session = this.sessionFactory.openSession();
        List<OtherBooking> list = session.createQuery(query).list();

        return list;

    }     
}
