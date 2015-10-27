/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.DaytourBookingDao;
import com.smi.travel.datalayer.entity.Coupon;
import com.smi.travel.datalayer.entity.Daytour;
import com.smi.travel.datalayer.entity.DaytourBooking;
import com.smi.travel.datalayer.entity.DaytourBookingPrice;
import com.smi.travel.util.UtilityFunction;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Surachai
 */
public class DaytourBookingImpl implements DaytourBookingDao {

    private SessionFactory sessionFactory;
    private Transaction transaction;
    private static final String TOURREFNO_QUERY = "from DaytourBooking DB where DB.daytour.id = :tourid and DB.tourDate = :date";
    private static final String TOURBOOK_FROM_REFNO_QUERY = "from DaytourBooking DB where DB.master.referenceNo = :refno";
    private static final String TOURBOOK_FROM_ID_QUERY = "from DaytourBooking DB where DB.id = :BookID";
    private static final String DELETE_COUPON_FROM_BOOKID_QUERY = "DELETE from Coupon c where c.daytourBooking.id = :BookID";
    private static final String DELETE_TOURPRICE_FROM_ID_QUERY = "DELETE from DaytourBookingPrice bp where bp.id = :PriceID";
    private static final String DELETE_TOURPRICE_NOTMATCH_QUERY = "DELETE from DaytourBookingPrice bp where bp.id  IN ";
    private static final String PRICE_FROM_TOUR_BOOK_QUERY = "from DaytourBookingPrice dp where dp.daytourBooking.id = :BookID and dp.daytourBooking.daytour.code != :TourCode";
    private static final String TOURJOB_QUERY = "from DaytourBooking DB Where DB.tourDate >= :date GROUP BY DB.daytour  , DB.tourDate ";

    @Override
    public List<DaytourBooking> getTourReference(String TourID, String TourDate) {
        UtilityFunction util = new UtilityFunction();
        Session session = this.sessionFactory.openSession();
        List<DaytourBooking> list = session.createQuery(TOURREFNO_QUERY)
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
    public String InsertBookingDaytour(DaytourBooking DayTourBook) {
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(DayTourBook);
            List<DaytourBookingPrice> prices = new ArrayList<DaytourBookingPrice>(DayTourBook.getDaytourBookingPrices());
            for (int i = 0; i < prices.size(); i++) {
                session.save(prices.get(i));
            }
            List<Coupon> CouponList = new ArrayList<Coupon>(DayTourBook.getCoupons());
            for (int i = 0; i < CouponList.size(); i++) {
                session.save(CouponList.get(i));
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
    public String UpdateBookingDaytour(DaytourBooking DayTourBook) {
        String result = "";
        Session session = this.sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            int isTimenull =0;
            DaytourBooking dbBooking = (DaytourBooking) session.get(DaytourBooking.class, DayTourBook.getId());
            Set<Coupon> setCoupons = dbBooking.getCoupons();
            System.out.println(DayTourBook.getPickupTime());
            if(DayTourBook.getPickupTime() == null){
                DayTourBook.setPickupTime(new Date());
                isTimenull = 1;
            }
            BeanUtils.copyProperties(dbBooking, DayTourBook);
            if(isTimenull == 1){
                dbBooking.setPickupTime(null);
                isTimenull = 1;
            }
            dbBooking.setCoupons(null);
            dbBooking.setDaytourBookingPrices(null);
            session.update(dbBooking);

            List<DaytourBookingPrice> prices = new ArrayList<DaytourBookingPrice>(DayTourBook.getDaytourBookingPrices());
            for (int i = 0; i < prices.size(); i++) {
                if (prices.get(i).getId() == null) {
                    session.save(prices.get(i));
                } else {
                    session.update(prices.get(i));
                }
            }

            if (!setCoupons.isEmpty()) {
                for (Iterator it = setCoupons.iterator(); it.hasNext();) {
                    Coupon dbCoupon = (Coupon) it.next();
                    session.delete(dbCoupon);
                }
            }
            List<Coupon> CouponList = new ArrayList<Coupon>(DayTourBook.getCoupons());
            for (int i = 0; i < CouponList.size(); i++) {
                session.save(CouponList.get(i));
            }
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = "success";
        } catch (Exception ex) {
            ex.printStackTrace();
            result = "fail";
            transaction.rollback();
            session.close();
            this.sessionFactory.close();
        }
        return result;
    }

    @Override
    public List<DaytourBooking> getListBookingDaytourFromRefno(String refno) {
        Session session = this.sessionFactory.openSession();
        List<DaytourBooking> list = session.createQuery(TOURBOOK_FROM_REFNO_QUERY)
                .setParameter("refno", refno)
                .list();
        if (list.isEmpty()) {
            return null;
        } else {
            return list;
        }
    }

    @Override
    public DaytourBooking getBookDetailDaytourFromID(String DaytourBookingID) {
        Session session = this.sessionFactory.openSession();
        List<DaytourBooking> list = session.createQuery(TOURBOOK_FROM_ID_QUERY)
                .setParameter("BookID", DaytourBookingID)
                .list();
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

    @Override
    public List<DaytourBooking> getTourJob() {
        Session session = this.sessionFactory.openSession();

        List<DaytourBooking> list = session.createQuery(TOURJOB_QUERY)
                .setParameter("date", new Date())
                .list();
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public int cancelBookDetailDaytour(String DaytourID) {
        int result = 0;
        String hql = "UPDATE DaytourBooking daytour set   daytour.MItemstatus.id = 2"
                + "WHERE daytour.id = :daytourID";
        try {
            Session session = this.sessionFactory.openSession();
            Query query = session.createQuery(hql);
            query.setParameter("daytourID", DaytourID);
            result = query.executeUpdate();
            System.out.println("Rows affected: " + result);
            session.close();
            this.sessionFactory.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }

        return result;
    }

    @Override
    public int enableBookDetailDaytour(String DaytourID) {
        int result = 0;
        String hql = "UPDATE DaytourBooking daytour set   daytour.MItemstatus.id = 1"
                + "WHERE daytour.id = :daytourID";
        try {
            Session session = this.sessionFactory.openSession();
            Query query = session.createQuery(hql);
            query.setParameter("daytourID", DaytourID);
            result = query.executeUpdate();
            System.out.println("Rows affected: " + result);
            session.close();
            this.sessionFactory.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }

        return result;
    }

    @Override
    public String DeleteBookingDaytourPrice(String DayTourPriceId) {
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(DELETE_TOURPRICE_FROM_ID_QUERY)
                    .setParameter("PriceID", DayTourPriceId);
            System.out.println("row result : " + query.executeUpdate());
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
    public String DeleteBookingDaytourPriceNotMatch(String TourID, String DaytourBookID) {
        String ListPriceID = "";
        String ListID ="";
        //    private static final String PRICE_FROM_TOUR_BOOK_QUERY = "from DaytourBookingPrice dp where dp.daytourBooking.id = :BookID and dp.daytourBooking.daytour.code != :TourCode";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            List<DaytourBookingPrice> list = session.createQuery(PRICE_FROM_TOUR_BOOK_QUERY)
                    .setParameter("BookID", DaytourBookID)
                    .setParameter("TourCode", TourID).list();
            if(list.isEmpty()){
                System.out.println("List null ");
                return null;
            }else{
                for(int i=0;i<list.size();i++){
                    System.out.println("price id : "+ list.get(i).getId());
                    ListID += list.get(i).getId()+","; 
                }
                if(ListID.length() != 0){
                    ListID = ListID.substring(0, ListID.length()-1);
                }
            }
            if(ListID.length() != 0){
                Query query = session.createQuery(DELETE_TOURPRICE_NOTMATCH_QUERY+" ("+ListID+")");  
                System.out.println("row result : " + query.executeUpdate());
            }
            
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            ListPriceID = ListID;
        } catch (Exception ex) {
            ex.printStackTrace();
            ListPriceID = "fail";
        }
        return ListPriceID;
    }

    @Override
    public Object getDailyTourReport(String from, String to, String department, String detail, String user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
