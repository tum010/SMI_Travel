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
import com.smi.travel.datalayer.entity.Stock;
import com.smi.travel.datalayer.entity.StockDetail;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.report.model.OtherAgentCommission;
import com.smi.travel.datalayer.report.model.OtherAgentCommissionReport;
import com.smi.travel.datalayer.report.model.OtherAgentCommissionSummaryReport;
import com.smi.travel.datalayer.report.model.OtherGuideCommissionInfo;
import com.smi.travel.datalayer.report.model.OtherGuideCommissionSummary;
import com.smi.travel.datalayer.report.model.OtherGuideCommissionSummaryHeader;
import com.smi.travel.datalayer.view.entity.OtherTicketView;
import com.smi.travel.util.UtilityFunction;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
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
    private final  String GUIDECOM_SUMMARY_QUERY = "SELECT st.name , " +
                                                        "sum(ot.ad_qty + ot.ch_qty + ot.in_qty) as pax, " +
                                                        "sum(ifnull(ot.guide_commission,0)) AS commission , " +
                                                        "ot.guide_id " +
                                                        "FROM `other_booking` ot " +
                                                        "INNER JOIN staff st on st.id = ot.guide_id " ;
    private static final String AGENTCOM_SUMMARY_QUERY = "SELECT agt.`code` AS `code`,"
            + "agt.`name` AS `name`,"
            + "count((mt.id)) AS count_booking,"
            + "SUM(db.agent_comission) AS comission "
            + "FROM daytour_booking db "
            + "INNER JOIN agent agt ON db.agent_id = agt.id "
//            + "INNER JOIN daytour_booking_price dp ON dp.daytour_booking_id = db.id "
            + "INNER JOIN `master` mt ON mt.id = db.master_id ";
    
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
    public List<String> insertBookDetailOther(OtherBooking otherbook, SystemUser user) {
        List<String> result = new ArrayList<String>();
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(otherbook);
            transaction.commit();
            session.close();
            result.add("1");
            result.add(otherbook.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
            result.add("0");
        }

        return result;
    }
    
    @Override
    public String saveStockDetailOther(OtherBooking otherbook, SystemUser user, String addticket, String adTicket, String chTicket, String infTicket, String itemid) {
        util = new UtilityFunction();
        String result = "";
        try {       
            Session session = this.sessionFactory.openSession();
//            transaction = session.beginTransaction();
            String date = util.convertDateToString(otherbook.getOtherDate());
            String productId = otherbook.getProduct().getId();
            List<Stock> stockList = getIsStock(productId, date, session);
            if(stockList.isEmpty()){
                result = "notStock";
                session.close();
                return result;
            }                       
            
//            List<StockDetail> stockDetailList = new ArrayList<StockDetail>();
//            String productIdChk = stockList.get(0).getProduct().getId();
//            int isStock = getIsStock(productIdChk, session);
//            if(isStock == 1){
//                result = "isStock";                              
//            } else {
//                result = "notStock";
//                return result;
//            }
//                                 
            int adultQty = otherbook.getAdQty();
            int childQty = otherbook.getChQty();
            int infantQty = otherbook.getInQty();
            
            int ad = 0;
            int adCancel = 0;
            int ch = 0;
            int chCancel = 0;
            int inf = 0;
            int infCancel = 0;
            
            List<Integer> stockNum = getStockNumFromOtherBookID(otherbook.getId(),session);
            if(stockNum != null){
                ad = stockNum.get(0);
                ch = stockNum.get(1);
                inf = stockNum.get(2);
                
            }
            System.out.println("adult : "+ ad );
            System.out.println("child : "+ ch );
            System.out.println("infant : "+ inf );
                                   
//            String stockDetailId = stockList.get(0).getId();
            List<StockDetail> stockDetailList = getStockByDate(productId, date, session);
            List<StockDetail> stockDetailTicketList = new ArrayList<StockDetail>();
            if("addTicket".equalsIgnoreCase(addticket)){
                stockDetailTicketList = getStockDetail(stockDetailList, Integer.parseInt(adTicket), Integer.parseInt(chTicket), Integer.parseInt(infTicket), session);
                if(stockDetailTicketList.isEmpty()) {                                                  
                    String adStr = String.valueOf(adTicket);
                    String chStr = String.valueOf(chTicket);
                    String infStr = String.valueOf(infTicket);
                    result = adStr+"||"+chStr+"||"+infStr;
                    session.close();
                    return result;
                }
            } else {
                stockDetailTicketList = getStockDetail(stockDetailList, adultQty, childQty, infantQty, session);
                if(stockDetailTicketList.isEmpty()) {                               
                    String adStr = String.valueOf(adultQty);
                    String chStr = String.valueOf(childQty);
                    String infStr = String.valueOf(infantQty);
                    result = adStr+"||"+chStr+"||"+infStr;
                    session.close();
                    return result;
                }
            }          
//            List<StockDetail> stockDetailTicketList = getStockDetail(stockDetailList, adultQty, childQty, infantQty, session);
            
//            if(stockDetailTicketList.isEmpty()) {                               
//                if((ad != adultQty) && (ad < adultQty)){
//                    adCancel = adultQty - ad;
//                }
//                if((ch != childQty) && (ch < childQty)){
//                    chCancel = childQty - ch;
//                }
//                if((inf != infantQty) && (inf < infantQty)){
//                    infCancel = infantQty - inf;
//                }
//
//                String adStr = String.valueOf(adCancel);
//                String chStr = String.valueOf(chCancel);
//                String infStr = String.valueOf(infCancel);
//                result = adStr+"||"+chStr+"||"+infStr;
//                return result;
//            }
            
            if("addTicket".equalsIgnoreCase(addticket)){
                result = addStockTicket(otherbook,user,stockDetailList,adultQty,childQty,infantQty,ad,ch,inf,adTicket,chTicket,infTicket);
            } else {
                result = saveStockTicket(otherbook,user,itemid,stockDetailList,adultQty,childQty,infantQty,ad,ch,inf);
            }
            
//            OtherBooking otherBooking = new OtherBooking();
//            String otherbookId = otherbook.getId();
//            otherBooking.setId(otherbookId);
//           
//            MStockStatus mstockStatus = new MStockStatus();
//            mstockStatus.setId("2");
//            // pickupDate must use current date
//            String pickupDate = util.convertDateToString(new Date());
//            for (int i = 0; i < stockDetailList.size(); i++){
//                StockDetail stockDetail = stockDetailList.get(i);
//                String typeName = stockDetail.getTypeId().getName();
//                if("ADULT".equalsIgnoreCase(typeName)){
//                    if(ad < adultQty){
//                        stockDetail.setPickupDate(util.convertStringToDate(pickupDate));
//                        stockDetail.setOtherBooking(otherbook);                   
//                        stockDetail.setMStockStatus(mstockStatus);
//                        stockDetail.setStaff(user);
//                        session.update(stockDetailList.get(i));
//                        ad++;
//                    }
//                    
//                } else if("CHILD".equalsIgnoreCase(typeName)){
//                    if(ch < childQty){
//                        stockDetail.setPickupDate(util.convertStringToDate(pickupDate));
//                        stockDetail.setOtherBooking(otherbook);                   
//                        stockDetail.setMStockStatus(mstockStatus);
//                        stockDetail.setStaff(user);
//                        session.update(stockDetailList.get(i));
//                        ch++;
//                    }
//                    
//                } else if("INFANT".equalsIgnoreCase(typeName)){
//                    if(inf < infantQty){
//                        stockDetail.setPickupDate(util.convertStringToDate(pickupDate));
//                        stockDetail.setOtherBooking(otherbook);                   
//                        stockDetail.setMStockStatus(mstockStatus);
//                        stockDetail.setStaff(user);
//                        session.update(stockDetailList.get(i));
//                        inf++;
//                    }
//                } 
//            }
//                            
//            transaction.commit();
            session.close();
//            this.sessionFactory.close();
//            
//            if(ad != adultQty){
//                adCancel = adultQty - ad;
//            }
//            if(ch != childQty){
//                chCancel = childQty - ch;
//            }
//            if(inf != infantQty){
//                infCancel = infantQty - inf;
//            }
//            
//            String adStr = String.valueOf(adCancel);
//            String chStr = String.valueOf(chCancel);
//            String infStr = String.valueOf(infCancel);
//            result = adStr+"||"+chStr+"||"+infStr;
            
            //result = stockDetailList.get(0).getStock().getId();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            result = "fail";
        }
        
        return result;
    }
    
    private List<StockDetail> getStockByDate(String Id, String date, Session session) {
        List<StockDetail> stockDetailList = new ArrayList<StockDetail>();
        try {       
            String queryDate = "from StockDetail sd where sd.stock.effectiveFrom <= '" + date + "' and sd.stock.effectiveTo >= '" + date + "' and sd.stock.product.id = " + Id + " and sd.stock.product.isStock = 1 and sd.MStockStatus.id = 1 order by sd.stock.effectiveFrom asc ";
            stockDetailList =  session.createQuery(queryDate).list();
            if(stockDetailList.isEmpty()){                
                return stockDetailList;
            }
            return stockDetailList;                       
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return stockDetailList;
    }
    
    private List<Integer> getStockNumFromOtherBookID(String Id , Session session) {
        List<Integer> stockNum = new ArrayList<Integer>();
        int ad = 0;
        int ch  = 0;
        int inf = 0;
        try {       
            String queryDate = "from StockDetail s where s.otherBooking.id = :BookId";
            List<StockDetail> stockList =  session.createQuery(queryDate).setParameter("BookId", Id).list();
            if(stockList.isEmpty()){
                return null;
            }else{
                for(int i=0;i<stockList.size();i++){
                     if(stockList.get(i).getTypeId() == null){
                        
                     }else if(stockList.get(i).getTypeId().getName().equalsIgnoreCase("ADULT")){
                        ad += 1;
                     }else if(stockList.get(i).getTypeId().getName().equalsIgnoreCase("CHILD")){
                        ch += 1;
                     }else if(stockList.get(i).getTypeId().getName().equalsIgnoreCase("INFANT")){
                        inf += 1;
                     }
                }
            }
            stockNum.add(ad);
            stockNum.add(ch);
            stockNum.add(inf);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return stockNum;
    }
    
    private List<Stock> getIsStock(String id, String date, Session session) {
        List<Stock> stockList = new ArrayList<Stock>();
        try {
            String query = "from Stock s where s.effectiveFrom <= '" + date + "' and s.effectiveTo >= '" + date + "' and s.product.id = " + id + " and s.product.isStock = 1 order by s.effectiveFrom asc ";
            stockList = session.createQuery(query).list();
            if(stockList.isEmpty()){
                return stockList;     
            }    
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return stockList;
    }
    
    private List<StockDetail> getStockDetail(List<StockDetail> stockDetailList, int adultQty, int childQty, int infantQty, Session session) {
        List<StockDetail> result = new ArrayList<StockDetail>();
        if(stockDetailList.isEmpty()){
            return result;
        }
        int ad = 0;
        int ch = 0;
        int inf = 0;
        for(int i=0;i<stockDetailList.size();i++){
            StockDetail stockDetail = new StockDetail();
            stockDetail = stockDetailList.get(i);
            String typeName = stockDetail.getTypeId().getName();
            if("ADULT".equalsIgnoreCase(typeName)){
                if(ad < adultQty){
                    result.add(stockDetail);
                    ad++;
                }
            } else if("CHILD".equalsIgnoreCase(typeName)){
                if(ch < childQty){
                    result.add(stockDetail);                        
                    ch++;
                }
            } else if("INFANT".equalsIgnoreCase(typeName)){
                if(inf < infantQty){
                    result.add(stockDetail);
                    inf++;
                }
            }
            if((ad == adultQty) && (ch == childQty) && (inf == infantQty)){
                i = stockDetailList.size();
            }
        }

        return result;
    }
    
    private String addStockTicket(OtherBooking otherbook, SystemUser user, List<StockDetail> stockDetailList, int adultQty, int childQty, int infantQty, int ad, int ch, int inf, String adT, String chT, String infT) {
        util = new UtilityFunction();
        String result = "";
        try {       
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            
            int adTicket = 0;
            int chTicket = 0;
            int infTicket = 0;
            int adNew = 0;
            int chNew = 0;
            int infNew = 0;
            if(!"".equalsIgnoreCase(adT)){
                adTicket = Integer.parseInt(adT);
            }
            if(!"".equalsIgnoreCase(chT)){
                chTicket = Integer.parseInt(chT);
            }
            if(!"".equalsIgnoreCase(infT)){
                infTicket = Integer.parseInt(infT);
            }
           
            OtherBooking otherBooking = new OtherBooking();
            String otherbookId = otherbook.getId();
            otherBooking.setId(otherbookId);

            MStockStatus mstockStatus = new MStockStatus();
            mstockStatus.setId("2");
            // pickupDate must use current date
            String pickupDate = util.convertDateToString(new Date());
            for (int i = 0; i < stockDetailList.size(); i++){
                StockDetail stockDetail = stockDetailList.get(i);
                String typeName = stockDetail.getTypeId().getName();
                if("ADULT".equalsIgnoreCase(typeName)){
                    if(adNew < adTicket){
                        stockDetail.setPickupDate(util.convertStringToDate(pickupDate));
                        stockDetail.setOtherBooking(otherbook);                   
                        stockDetail.setMStockStatus(mstockStatus);
                        stockDetail.setStaff(user);
                        session.update(stockDetailList.get(i));
                        adNew++;
                    }

                } else if("CHILD".equalsIgnoreCase(typeName)){
                    if(chNew < chTicket){
                        stockDetail.setPickupDate(util.convertStringToDate(pickupDate));
                        stockDetail.setOtherBooking(otherbook);                   
                        stockDetail.setMStockStatus(mstockStatus);
                        stockDetail.setStaff(user);
                        session.update(stockDetailList.get(i));
                        chNew++;
                    }

                } else if("INFANT".equalsIgnoreCase(typeName)){
                    if(infNew < infTicket){
                        stockDetail.setPickupDate(util.convertStringToDate(pickupDate));
                        stockDetail.setOtherBooking(otherbook);                   
                        stockDetail.setMStockStatus(mstockStatus);
                        stockDetail.setStaff(user);
                        session.update(stockDetailList.get(i));
                        infNew++;
                    }
                } 
            }
            
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            
            int adCancel = 0;
            int chCancel = 0;
            int infCancel = 0;
            
//            if(((adNew+ad) != adultQty) && ((adNew+ad) < adultQty)){
//                adCancel = adultQty - (adNew+ad);
//            }
//            if(((chNew+ch) != childQty) && ((chNew+ch) < childQty)){
//                chCancel = childQty - (chNew+ch);
//            }
//            if(((infNew+inf) != infantQty) && ((infNew+inf) < infantQty)){
//                infCancel = infantQty - (infNew+inf);
//            }
            
            if(adNew<adTicket){
                adCancel = adTicket - adNew;
            }
            if(chNew<chTicket){
                chCancel = chTicket - chNew;
            }
            if(adNew<adTicket){
                infCancel = infTicket - infNew;
            }
            
            String adStr = String.valueOf(adCancel);
            String chStr = String.valueOf(chCancel);
            String infStr = String.valueOf(infCancel);
            result = adStr+"||"+chStr+"||"+infStr;
            
        } catch (Exception ex) {
            ex.printStackTrace();
            result = "fail";
        }
        
        return result;
    }

    private String saveStockTicket(OtherBooking otherbook, SystemUser user, String itemid, List<StockDetail> stockDetailList, int adultQty, int childQty, int infantQty, int ad, int ch, int inf) {
        util = new UtilityFunction();
        String result = "";
        try {
            if("".equalsIgnoreCase(itemid)){               
                Session session = this.sessionFactory.openSession();
                transaction = session.beginTransaction();

                OtherBooking otherBooking = new OtherBooking();
                String otherbookId = otherbook.getId();
                otherBooking.setId(otherbookId);

                MStockStatus mstockStatus = new MStockStatus();
                mstockStatus.setId("2");
                // pickupDate must use current date
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
            }    
            
            int adCancel = 0;
            int chCancel = 0;
            int infCancel = 0;
            
            if((ad != adultQty) && (ad < adultQty)){
                adCancel = adultQty - ad;
            }
            if((ch != childQty) && (ch < childQty)){
                chCancel = childQty - ch;
            }
            if((inf != infantQty) && (inf < infantQty)){
                infCancel = infantQty - inf;
            }
            
            String adStr = String.valueOf(adCancel);
            String chStr = String.valueOf(chCancel);
            String infStr = String.valueOf(infCancel);
            result = adStr+"||"+chStr+"||"+infStr;
            
         } catch (Exception ex) {
            ex.printStackTrace();
            result = "fail";
        }
        
        return result;    
    }
    
    @Override
    public List<OtherTicketView> getListStockDetail(String otherBookingId) {
        List<OtherTicketView> ticketList = new ArrayList<OtherTicketView>();
        try{
            Session session = this.sessionFactory.openSession();
            String query = "from StockDetail s where s.otherBooking.id = " + otherBookingId + " order by s.typeId.id , s.id , s.code";
            List<StockDetail> stockDetailList = session.createQuery(query).list();            
            if(stockDetailList.isEmpty()){
                return ticketList;  
            }
            
            for(int i=0;i<stockDetailList.size();i++){
                StockDetail stockDetail = stockDetailList.get(i);
                OtherTicketView otherTicketView = new OtherTicketView();
                otherTicketView.setId(stockDetail.getId());
                otherTicketView.setAddDate(stockDetail.getPickupDate());
                otherTicketView.setTicketCode(stockDetail.getCode());
                otherTicketView.setTypeName(stockDetail.getTypeId().getName());
                otherTicketView.setStatus(stockDetail.getMStockStatus().getName());
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
    public List<String> updateBookDetailOther(OtherBooking otherbook) {
        List<String> result = new ArrayList<String>();
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(otherbook);
            transaction.commit();
            session.close();
            result.add("1");
            result.add(otherbook.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
            result.add("0");
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
        String query = "from OtherBooking other order by other.master.referenceNo DESC";
        Session session = this.sessionFactory.openSession();
        System.out.println("Query Booking List : " + query);
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

                if ((book.getRemarkGuideCommission() != null) && !"".equals(book.getRemarkGuideCommission())) {
                    queryupdate += prefix + " ot.remarkGuideCommission = '" + book.getRemarkGuideCommission() + "' ";
                    if (checkQuery == 0) {
                        checkQuery = 1;
                        prefix = ",";
                    }
                }

                if ((book.getRemarkAgentCommission() != null) && !"".equals(book.getRemarkAgentCommission())) {
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

    @Override
    public String updateStockTicketStatus(String stockdetailid,String statusTicket) {       
        String result = "";
        try {           
            String status = "";
            String hql = "";
            if("reuse".equalsIgnoreCase(statusTicket)){
                status = "1";
                hql = "UPDATE StockDetail stock set stock.MStockStatus.id = :status , stock.otherBooking.id = :productid , stock.staff.id = :staffId , stock.pickupDate = :pickupDate where stock.id  = :stockdetailid ";
            } else if("refund".equalsIgnoreCase(statusTicket)){
                status = "4";
                hql = "UPDATE StockDetail stock set stock.MStockStatus.id = :status where stock.id  = :stockdetailid ";
            } else {
                status = "3";
                hql = "UPDATE StockDetail stock set stock.MStockStatus.id = :status where stock.id  = :stockdetailid ";
            }
            Session session = this.sessionFactory.openSession();
            Query query = session.createQuery(hql);
            query.setParameter("status", status);
            query.setParameter("stockdetailid", stockdetailid);
            if("reuse".equalsIgnoreCase(statusTicket)){
                query.setParameter("productid", null);
                query.setParameter("staffId", null);
                query.setParameter("pickupDate", null);
            }          
            query.executeUpdate();
            result = "stock success";
            session.close();
            this.sessionFactory.close();
         } catch (Exception ex) {
            ex.printStackTrace();
            result = "stock fail";
        }
        return result;
    }   

    @Override
    public String checkStock(String productId, String date) {
        util = new UtilityFunction();
        String result = "notStock";
        try {       
            Session session = this.sessionFactory.openSession();
            List<Stock> stockList = getIsStock(productId, date, session);
            if(stockList.isEmpty()){
                result = "notStock";
                session.close();
                return result;
            }                       
            
            int ad = 0;
            int ch = 0;
            int inf = 0;
            
            List<StockDetail> stockDetailList = getStockByDate(productId, date, session);
            for(int i=0;i<stockDetailList.size();i++){
                String typeName = stockDetailList.get(i).getTypeId().getName();
                if("ADULT".equalsIgnoreCase(typeName)){
                    ad++;
                    
                } else if("CHILD".equalsIgnoreCase(typeName)){
                    ch++;
                    
                } else if("INFANT".equalsIgnoreCase(typeName)){
                    inf++;
                } 
            }

            session.close();
            result = ad+","+ch+","+inf;
            
        } catch (Exception ex) {
            ex.printStackTrace();
            result = "notStock";
        }
        
        return result;
    }

    @Override
    public OtherGuideCommissionInfo getOtherGuideCommissionInfoReport(String datefrom, String dateto, String username, String guideid) {
        OtherGuideCommissionInfo guideCommissionInfo = new OtherGuideCommissionInfo();
        Date thisdate = new Date();
        UtilityFunction util = new UtilityFunction();
        System.out.println(" From Date : " + datefrom +  " To Date : " + dateto);
        guideCommissionInfo.setSystemdate(new SimpleDateFormat("dd MMM yy hh:mm", new Locale("us", "us")).format(thisdate));
        guideCommissionInfo.setUser(username);
        guideCommissionInfo.setDatefrom(!"".equalsIgnoreCase(datefrom) ? new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(datefrom)) : "");
        guideCommissionInfo.setDateto(!"".equalsIgnoreCase(dateto)  ? new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(dateto)) : "");
        guideCommissionInfo.setOtherGuideCommissionSummaryDataSource(new JRBeanCollectionDataSource(getOtherGuideComissionSummaryReport(datefrom, dateto, username, guideid)));
        guideCommissionInfo.setOtherGuideCommissionDataSource(new JRBeanCollectionDataSource(getOtherGuideComissionReport(datefrom, dateto, username, guideid)));
        return guideCommissionInfo;
    }
    
    private List getOtherGuideComissionSummaryReport(String datefrom, String dateto, String username,String guideid) {
       org.hibernate.Session session = this.sessionFactory.openSession();
        List data = new ArrayList();
        Date thisdate = new Date();
        String query = "";
        UtilityFunction util = new UtilityFunction();
        int checkQuery = 0;
        
        if( "".equals(datefrom)  && "".equals(dateto) && "".equals(guideid)){
            query = GUIDECOM_SUMMARY_QUERY + " ";
        }else{  
            if( datefrom == null  && dateto == null && guideid == null){
                query = GUIDECOM_SUMMARY_QUERY + " ";
            }else{
                query = GUIDECOM_SUMMARY_QUERY + "  where ";
            }
        }
        
        if ((datefrom != null )&&(!"".equalsIgnoreCase(datefrom))) {
            if ((dateto != null )&&(!"".equalsIgnoreCase(dateto))) {
                if(checkQuery == 1){
                     query += " and ot.other_date   BETWEEN  '" + datefrom + "' AND '" + dateto + "' ";
                }else{
                    checkQuery = 1;
                     query += " ot.other_date  BETWEEN  '" + datefrom + "' AND '" + dateto + "' ";
                }
            }
        }
        if ((guideid != null )&&(!"".equalsIgnoreCase(guideid))) {
            if(checkQuery == 1){
                 query += " and guide_id = "+guideid;
            }else{
                checkQuery = 1;
                 query += " guide_id = "+guideid;
            }
        }
        
        query += "  GROUP BY ot.guide_id ";     
        query += "  having  sum(ifnull(ot.guide_commission,0))  <> 0 ";
        System.out.println("query : "+ query);
        List<Object[]> QueryGuideComList = session.createSQLQuery(query)
                .addScalar("name", Hibernate.STRING)
                .addScalar("pax", Hibernate.INTEGER)
                .addScalar("commission", Hibernate.INTEGER)

                .list();
        
        for (Object[] B : QueryGuideComList) {
             OtherGuideCommissionSummaryHeader guidecom = new OtherGuideCommissionSummaryHeader();
             guidecom.setSystemdate(new SimpleDateFormat("dd MMM yy hh:mm", new Locale("us", "us")).format(thisdate));
             guidecom.setUser(username);
             guidecom.setDatefrom(!"".equalsIgnoreCase(datefrom) ? new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(datefrom)) : "");
             guidecom.setDateto(!"".equalsIgnoreCase(dateto) ? new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(dateto)) : "");
             guidecom.setGuidename(util.ConvertString(B[0]));
             guidecom.setPax(B[1]== null ? 0:(Integer)B[1]);
             guidecom.setCommission(B[2]== null ? 0:(Integer)B[2]);
             data.add(guidecom);
        }
        
        this.sessionFactory.close();
        session.close();
        return data;
    }
    
    private List getOtherGuideComissionReport(String datefrom, String dateto, String username,String guideid) {
        Session session = this.sessionFactory.openSession();
        List data = new ArrayList();
        Date thisdate = new Date();
        String query = "";
        UtilityFunction util = new UtilityFunction();
        int checkQuery = 0;
        if( "".equals(datefrom)  && "".equals(dateto) && "".equals(guideid)){
            query = "SELECT * FROM `guide_commission_other` gc ";
        }else{  
            if( datefrom == null  && dateto == null && guideid == null){
                query = "SELECT * FROM `guide_commission_other` gc ";
            }else{
                query = "SELECT * FROM `guide_commission_other` gc  where ";
            }
        }
        
        if ((datefrom != null )&&(!"".equalsIgnoreCase(datefrom))) {
            if ((dateto != null )&&(!"".equalsIgnoreCase(dateto))) {
                if(checkQuery == 1){
                     query += " and gc.otherdate BETWEEN  '" + datefrom + "' AND '" + dateto + "' ";
                }else{
                    checkQuery = 1;
                     query += " gc.otherdate  BETWEEN  '" + datefrom + "' AND '" + dateto + "' ";
                }
            }
        }
        if ((guideid != null )&&(!"".equalsIgnoreCase(guideid))) {
            if(checkQuery == 1){
                 query += " and gc.guideId = "+guideid;
            }else{
                checkQuery = 1;
                 query += " gc.guideId = "+guideid;
            }
        }
        
        query += " ORDER BY gc.guidename , gc.otherdate , gc.tour";
        System.out.println(" Query GuideCommission : " + query );
        List<Object[]> QueryGuideComList = session.createSQLQuery(query)
                .addScalar("otherdate", Hibernate.DATE)
                .addScalar("tour", Hibernate.STRING)
                .addScalar("leder", Hibernate.STRING)
                .addScalar("pax", Hibernate.INTEGER)
                .addScalar("commission", Hibernate.INTEGER)
                .addScalar("selling", Hibernate.INTEGER)
                .addScalar("guidename", Hibernate.STRING)
                .addScalar("remark", Hibernate.STRING)
                .list();
        
        for (Object[] B : QueryGuideComList) {
             OtherGuideCommissionSummary guidecom = new OtherGuideCommissionSummary();
             guidecom.setSystemdate(new SimpleDateFormat("dd MMM yy hh:mm", new Locale("us", "us")).format(thisdate));
             guidecom.setUser(username);
             guidecom.setDatefrom(!"".equalsIgnoreCase(datefrom) ? new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(datefrom)) : "");
             guidecom.setDateto(!"".equalsIgnoreCase(dateto) ? new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(dateto)) : "");
             guidecom.setTourdate(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(B[0]));
             guidecom.setCode(util.ConvertString(B[1]));
             guidecom.setCustomer(util.ConvertString(B[2]));
             guidecom.setPax(B[3]== null ? 0:(Integer)B[3]);
             guidecom.setComission(B[4]== null ? 0:(Integer)B[4]);
             guidecom.setSelling(B[5]== null ? 0:(Integer)B[5]);
             guidecom.setGuide(util.ConvertString(B[6]));
             guidecom.setRemark(util.ConvertString(B[7]));
             data.add(guidecom);
        }
         this.sessionFactory.close();
        session.close();
        return data;
    }

    @Override
    public OtherAgentCommission getOtherAgentCommissionReport(String datefrom, String dateto, String user, String agentid) {
        OtherAgentCommission agentCommission = new OtherAgentCommission();
        Date thisdate = new Date();
        UtilityFunction util = new UtilityFunction();
        System.out.println(" From Date : " + datefrom +  " To Date : " + dateto);
        agentCommission.setSystemdate(new SimpleDateFormat("dd MMM yy hh:mm", new Locale("us", "us")).format(thisdate));
        agentCommission.setUser(user);
        agentCommission.setDatefrom(!"".equalsIgnoreCase(datefrom) ? new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(datefrom)) : "");
        agentCommission.setDateto(!"".equalsIgnoreCase(dateto)  ? new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(dateto)) : "");
        agentCommission.setOtherAgentCommissionInfoDataSource(new JRBeanCollectionDataSource(getOtherAgentReportInfo(datefrom, dateto, user,agentid)));
        agentCommission.setOtherAgentCommissionSummaryDataSource(new JRBeanCollectionDataSource(getOtherAgentReportSummary(datefrom, dateto, user,agentid)));
        return agentCommission;
    }
    
    private List getOtherAgentReportInfo(String datefrom, String dateto, String user,String agentid) {
        Session session = this.sessionFactory.openSession();
        List data = new ArrayList();
        Date thisdate = new Date();
        UtilityFunction util = new UtilityFunction();
        String query ="SELECT * FROM `agent_commission_info` where tourdate >= '"+datefrom+"' and tourdate <= '"+dateto+"'";
        if((agentid != null)&&(!"".equalsIgnoreCase(agentid))){
            query += " and agentid = "+agentid;
        }
        List<Object[]> QueryAgentComList = session.createSQLQuery(query)
                .addScalar("tourdate", Hibernate.DATE)
                .addScalar("tourcode", Hibernate.STRING)
                .addScalar("customer", Hibernate.STRING)
                .addScalar("pax", Hibernate.STRING)
                .addScalar("comission", Hibernate.INTEGER)
                .addScalar("sell", Hibernate.INTEGER)
                .addScalar("name", Hibernate.STRING)
                .list();
                
        for (Object[] B : QueryAgentComList) {
             OtherAgentCommissionReport   report = new  OtherAgentCommissionReport(); 
             report.setTourdate(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(B[0]));
             report.setTourcode(util.ConvertString(B[1]));
             report.setClient(util.ConvertString(B[2]));
             report.setPax("".equals(util.ConvertString(B[3])) ? 0 : Integer.parseInt(util.ConvertString(B[3])));
             report.setCommission(B[4]== null ? 0:(Integer)B[4]);
             report.setSell(B[5]== null ? 0:(Integer)B[5]);
             report.setAgent(util.ConvertString(B[6]));
             report.setSystemdate(new SimpleDateFormat("dd MMM yy hh:mm", new Locale("us", "us")).format(thisdate));
             report.setDatefrom(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(datefrom)));
             report.setDateto(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(dateto)));
             report.setUser(user);
             data.add(report);
        }              
                
        return data;
    }
    
    private List getOtherAgentReportSummary(String datefrom, String dateto, String user,String agentid) {
        Session session = this.sessionFactory.openSession();
        List data = new ArrayList();
        Date thisdate = new Date();
        UtilityFunction util = new UtilityFunction();
        String sql = AGENTCOM_SUMMARY_QUERY+" where db.tour_date >= '"+datefrom+"' and db.tour_date <= '"+dateto+"'" ;
        
        if((agentid != null)&&(!"".equalsIgnoreCase(agentid))){
            sql += " and agt.id ="+agentid;
        }
        sql += " GROUP BY agt.`code`,agt.`name` HAVING  comission <> 0 ORDER BY `agt`.`name`";
        System.out.println("sql :" +sql);
        List<Object[]> QueryAgentComSummaryList = session.createSQLQuery(sql)
                .addScalar("code", Hibernate.STRING)
                .addScalar("name", Hibernate.STRING)
                .addScalar("count_booking", Hibernate.STRING)
                .addScalar("comission", Hibernate.INTEGER)
                .list();
  
        System.out.println("QueryAgentComSummaryList.size : "+QueryAgentComSummaryList.size());
        for (Object[] B : QueryAgentComSummaryList) {
             OtherAgentCommissionSummaryReport   report = new  OtherAgentCommissionSummaryReport(); 
             report.setCode(util.ConvertString(B[0]));
             report.setName(util.ConvertString(B[1]));
             report.setCountbook("".equals(util.ConvertString(B[2])) ? 0 : Integer.parseInt(util.ConvertString(B[2])));
             report.setCommission(B[3]== null ? 0:(Integer)B[3]);
             report.setSystemdate(new SimpleDateFormat("dd MMM yy hh:mm", new Locale("us", "us")).format(thisdate));
             report.setDatefrom(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(datefrom)));
             report.setDateto(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(dateto)));
             report.setUser(user);
             data.add(report);
        }      
        return data;  
    }
}
