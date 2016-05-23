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
import com.smi.travel.datalayer.report.model.DailyTourReport;
import com.smi.travel.datalayer.view.entity.DailyTourDetailView;
import com.smi.travel.datalayer.view.entity.DailyTourListView;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Hibernate;
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
    private UtilityFunction utilityFunction;
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
        boolean isACnull = false;
        boolean isGCnull = false;
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
            if(DayTourBook.getAgentComission() == null){
                isACnull = true;
                DayTourBook.setAgentComission(new BigDecimal(0));
            }
            if(DayTourBook.getGuideCommission() == null){
                isGCnull = true;
                DayTourBook.setGuideCommission(new BigDecimal(0));
            }
            BeanUtils.copyProperties(dbBooking, DayTourBook);
            if(isACnull){ 
                dbBooking.setAgentComission(null);
                DayTourBook.setAgentComission(null);
            }
            if(isGCnull){
                dbBooking.setGuideCommission(null);
                DayTourBook.setGuideCommission(null);
            }
            
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
    public DailyTourReport getDailyTourReport(String from, String to, String department, String detail, String user) {
        UtilityFunction util = new UtilityFunction();
        DailyTourReport dailyTourReport = new DailyTourReport();
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MMM-yyyy HH:mm"); 
        SimpleDateFormat datefromto = new SimpleDateFormat();
        datefromto.applyPattern("dd-MM-yyyy");
        dailyTourReport.setFromto(util.ConvertString(datefromto.format(util.convertStringToDate(from))) + "  to  " + 
                                     util.ConvertString(datefromto.format(util.convertStringToDate(to))));
        dailyTourReport.setSystemdate(String.valueOf(dateformat.format(new Date())));
        dailyTourReport.setUser(user);
        dailyTourReport.setDailyTourListReportDataSource(new JRBeanCollectionDataSource(getDailyTourList(from,to,department)));
        if("1".equalsIgnoreCase(detail)){
            dailyTourReport.setDailyTourDetailReportDataSource(new JRBeanCollectionDataSource(getDailyTourDetail(from,to,department)));
            dailyTourReport.setNewpage("1");
        }else{
            dailyTourReport.setDailyTourDetailReportDataSource(new JRBeanCollectionDataSource(null));
            dailyTourReport.setNewpage("0");
        }       
        return dailyTourReport; 
    }

    private List getDailyTourList(String from, String to, String department) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        
        boolean condition = false;
        String query = "SELECT db.tour_id AS tourid, dt.`code` AS tourcode, dt.`name` AS tourname, db.tour_date AS tourdate, sum(db.adult) AS ad, "
                + "sum(db.child) AS ch, sum(db.infant) AS Inf, GET_PRICE_DAYTOUR_BOOK (db.id) AS sell, "
                + "sum( ifnull( GET_COST_DAYTOUR_FROM_TOURDATE (db.tour_id, db.tour_date), 0 )) AS net, "
                + "GET_PRICE_DAYTOUR_BOOK (db.id) - sum( ifnull( GET_COST_DAYTOUR_FROM_TOURDATE (db.tour_id, db.tour_date), 0 )) AS balance, "
                + "CASE WHEN ifnull(sum(db.adult), 0) + ifnull(sum(db.child), 0) + ifnull(sum(db.infant), 0) = 0 THEN 0 ELSE round(( GET_PRICE_DAYTOUR_BOOK (db.id) - sum( ifnull( GET_COST_DAYTOUR_FROM_TOURDATE (db.tour_id, db.tour_date), 0 ))) / ( ifnull(sum(db.adult), 0) + ifnull(sum(db.child), 0) + ifnull(sum(db.infant), 0)), 2 ) END AS average  "
                + "FROM `daytour_booking` db INNER JOIN daytour dt ON dt.id = db.tour_id JOIN `master` `mt` ON (`mt`.`id` = `db`.`master_id`) ";
               
        if((!"".equalsIgnoreCase(from)) && (from != null)){
            query += (condition ? " AND " : " WHERE "); 
            query += " db.tour_date >= '" + from + "' ";
            condition = true;
        }
        if((!"".equalsIgnoreCase(to)) && (to != null)){
            query += (condition ? " AND " : " WHERE "); 
            query += " db.tour_date <= '" + to + "' ";
            condition = true;
        }
        if((!"".equalsIgnoreCase(department)) && (department != null)){
            query += (condition ? " AND " : " WHERE "); 
            query += " mt.booking_type >= '" + department + "' ";
            condition = true;
        }
        
        query += " and  GET_PRICE_DAYTOUR_BOOK (db.id) <> 0 ";
        query += " GROUP BY db.tour_id ORDER BY dt.`code`";
        
        List<Object[]> QueryList =  session.createSQLQuery(query)
                .addScalar("tourcode",Hibernate.STRING)
                .addScalar("tourname",Hibernate.STRING)
                .addScalar("ad",Hibernate.STRING)
                .addScalar("ch",Hibernate.STRING)
                .addScalar("inf",Hibernate.STRING)
                .addScalar("sell",Hibernate.STRING)
                .addScalar("net",Hibernate.STRING)
                .addScalar("balance",Hibernate.STRING)
                .addScalar("average",Hibernate.STRING)
                .list();
        
        for (Object[] B : QueryList) {
            DailyTourListView dailyTourListView = new DailyTourListView();
            dailyTourListView.setCode(B[0]== null ? "" : util.ConvertString(B[0]));
            dailyTourListView.setTour(B[1]== null ? "" : util.ConvertString(B[1]));
            dailyTourListView.setAdult(B[2]== null ? "" : util.ConvertString(B[2]));
            dailyTourListView.setChild(B[3]== null ? "" : util.ConvertString(B[3]));
            dailyTourListView.setInfant(B[4]== null ? "" : util.ConvertString(B[4]));
            dailyTourListView.setSell(B[5]== null ? "" : util.ConvertString(B[5]));
            dailyTourListView.setNet(B[6]== null ? "" : util.ConvertString(B[6]));
            dailyTourListView.setBalance(B[7]== null ? "" : util.ConvertString(B[7]));
            dailyTourListView.setEverage(B[8]== null ? "" : util.ConvertString(B[8]));
            data.add(dailyTourListView);
        }    
        
        this.sessionFactory.close();
        session.close();
        return data;
    }

    private List getDailyTourDetail(String from, String to, String department) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        
        boolean condition = false;
        String query = "SELECT db.tour_id AS tourid, dt.`code` AS tourcode, dt.`name` AS tourname, db.tour_date AS tourdate, db.adult AS ad, db.child AS ch, "
                + "db.infant AS Inf, sum(dp.price * dp.qty) AS sell, sum( ifnull( GET_COST_DAYTOUR_FROM_TOURDATE (db.tour_id, db.tour_date), 0 )) AS net, "
                + "sum(dp.price * dp.qty) - sum( ifnull( GET_COST_DAYTOUR_FROM_TOURDATE (db.tour_id, db.tour_date), 0 )) AS balance, "
                + " CASE WHEN db.adult + db.child + db.infant = 0 THEN 0 ELSE round(( sum(dp.price * dp.qty) - sum( ifnull( GET_COST_DAYTOUR_FROM_TOURDATE (db.tour_id, db.tour_date), 0 ))) / ( db.adult + db.child + db.infant ), 2 ) END AS average  "
                + "FROM `daytour_booking` db INNER JOIN daytour dt ON dt.id = db.tour_id INNER JOIN daytour_booking_price dp ON dp.daytour_booking_id = db.id "
                + "JOIN `master` `mt` ON (`mt`.`id` = `db`.`master_id`) ";
               
        if((!"".equalsIgnoreCase(from)) && (from != null)){
            query += (condition ? " AND " : " WHERE "); 
            query += " db.tour_date >= '" + from + "' ";
            condition = true;
        }
        if((!"".equalsIgnoreCase(to)) && (to != null)){
            query += (condition ? " AND " : " WHERE "); 
            query += " db.tour_date <= '" + to + "' ";
            condition = true;
        }
        if((!"".equalsIgnoreCase(department)) && (department != null)){
            query += (condition ? " AND " : " WHERE "); 
            query += " mt.booking_type >= '" + department + "' ";
            condition = true;
        }
        
        query += " GROUP BY db.tour_id, db.tour_date having sum(dp.price * dp.qty) <>0 ORDER BY dt.`code`";
        
        List<Object[]> QueryList =  session.createSQLQuery(query)
                .addScalar("tourcode",Hibernate.STRING)
                .addScalar("tourname",Hibernate.STRING)
                .addScalar("ad",Hibernate.STRING)
                .addScalar("ch",Hibernate.STRING)
                .addScalar("inf",Hibernate.STRING)
                .addScalar("sell",Hibernate.STRING)
                .addScalar("net",Hibernate.STRING)
                .addScalar("balance",Hibernate.STRING)
                .addScalar("average",Hibernate.STRING)
                .addScalar("tourdate",Hibernate.STRING)
                .list();
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy");
        boolean start = true;
        int paxAd = 0;
        int paxCh = 0;
        int paxInf = 0;
        String code = "";
        String tourCode = "";
        String tourDate = "";
        for (Object[] B : QueryList) {
            DailyTourDetailView dailyTourDetailView = new DailyTourDetailView();
            dailyTourDetailView.setCode(B[0]== null ? "" : util.ConvertString(B[0]));
            dailyTourDetailView.setTour(B[1]== null ? "" : util.ConvertString(B[1]));
            dailyTourDetailView.setAdult(B[2]== null ? "" : util.ConvertString(B[2]));
            dailyTourDetailView.setChild(B[3]== null ? "" : util.ConvertString(B[3]));
            dailyTourDetailView.setInfant(B[4]== null ? "" : util.ConvertString(B[4]));
            dailyTourDetailView.setPrice(B[5]== null ? "" : util.ConvertString(B[5]));
            dailyTourDetailView.setNet(B[6]== null ? "" : util.ConvertString(B[6]));
            dailyTourDetailView.setProfit(B[7]== null ? "" : util.ConvertString(B[7]));
//            dailyTourDetailView.setProfit(B[8]== null ? "" : util.ConvertString(B[8]));
            if(B[9] != null){
                tourDate = util.ConvertString(dateformat.format(util.convertStringToDate(util.ConvertString(B[9]))));
            }
            dailyTourDetailView.setTourdate(tourDate);
            
            int ad = (B[2]== null ? 0 : Integer.parseInt(util.ConvertString(B[2])));
            int ch = (B[3]== null ? 0 : Integer.parseInt(util.ConvertString(B[3])));
            int inf = (B[4]== null ? 0 : Integer.parseInt(util.ConvertString(B[4])));
            dailyTourDetailView.setQty(String.valueOf(ad+ch+inf));
            
            code = ((B[0] == null) ? "" : util.ConvertString(B[0]));
            tourCode = ((!code.equalsIgnoreCase(tourCode)) || ("".equalsIgnoreCase(tourCode)) ? util.ConvertString(B[0]) : tourCode);
            if(!code.equalsIgnoreCase(tourCode)){
//                dailyTourDetailView.setPax(paxAd+" ADT - "+ paxCh +" CHD - "+ paxInf +" INF");
                paxAd = 0;
                paxCh = 0;
                paxInf = 0;        
            }else{
                paxAd += ad;
                paxCh += ch;
                paxInf += inf;
                dailyTourDetailView.setPax(paxAd+" ADT - "+ paxCh +" CHD - "+ paxInf +" INF");
            }
            data.add(dailyTourDetailView);
        }    
        
        this.sessionFactory.close();
        session.close();
        return data;
    }

}
