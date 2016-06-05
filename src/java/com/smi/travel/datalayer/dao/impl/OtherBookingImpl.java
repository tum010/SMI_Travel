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
import com.smi.travel.datalayer.entity.ProductComission;
import com.smi.travel.datalayer.entity.Stock;
import com.smi.travel.datalayer.entity.StockDetail;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.report.model.OtherAgentCommission;
import com.smi.travel.datalayer.report.model.OtherAgentCommissionReport;
import com.smi.travel.datalayer.report.model.OtherAgentCommissionSummaryReport;
import com.smi.travel.datalayer.report.model.OtherGuideCommissionInfo;
import com.smi.travel.datalayer.report.model.OtherGuideCommissionSummary;
import com.smi.travel.datalayer.report.model.OtherGuideCommissionSummaryHeader;
import com.smi.travel.datalayer.view.entity.OtherBookingView;
import com.smi.travel.datalayer.view.entity.OtherTicketView;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
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
    private static final String GET_BOOKOTHER_QUERY = "from OtherBooking ot where ot.otherDate >= :startdate and ot.otherDate <= :enddate and ot.master.bookingType = 'I' ";
    private final  String GUIDECOM_SUMMARY_QUERY = "SELECT  concat(`st`.`name`, ' ', `st`.`tel`) AS `name` , " +
                                                        "sum(ot.ad_qty + ot.ch_qty + ot.in_qty) as pax, " +
                                                        "sum(ifnull(ot.guide_commission,0)) AS commission , " +
                                                        "ot.guide_id " +
                                                        "FROM `other_booking` ot " +
                                                        "INNER JOIN staff st on st.id = ot.guide_id " ;
    private static final String AGENTCOM_SUMMARY_QUERY = "SELECT " +
                                                        " `agt`.`code` AS `code`, " +
                                                        " `agt`.`name` AS `name`, " +
                                                        " count(DISTINCT `mt`.`id`) AS `count_booking`, " +
                                                        " sum(`ot`.agent_commission) AS `commission`, " +
                                                        " agt.id " +
                                                        "FROM " +
                                                        "`other_booking` `ot` " +
                                                        "JOIN `agent` `agt` ON (`ot`.agent_id = `agt`.`id`) " +
                                                        "JOIN `master` `mt` ON (`mt`.`id` = `ot`.`master_id`)";
    
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
        adTicket = (!"".equalsIgnoreCase(adTicket) ? adTicket : "0");
        chTicket = (!"".equalsIgnoreCase(chTicket) ? chTicket : "0");
        infTicket = (!"".equalsIgnoreCase(infTicket) ? infTicket : "0");
        System.out.println("=====adTicket===== :"+adTicket);
        System.out.println("=====chTicket===== :"+chTicket);
        System.out.println("=====infTicket===== :"+infTicket);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
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
                queryDate = "from StockDetail sd where sd.stock.product.id = " + Id + " and sd.stock.product.isStock = 1 and sd.MStockStatus.id = 1 order by sd.stock.createDate asc ";
                stockDetailList =  session.createQuery(queryDate).list();
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
                query = "from Stock s where s.product.id = " + id + " and s.product.isStock = 1 order by s.effectiveFrom asc ";
                stockList = session.createQuery(query).list();     
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
    public List<OtherBookingView> getListBookingAllView(String name) {
        UtilityFunction util = new UtilityFunction();
        Session session = this.sessionFactory.openSession();
        List<OtherBookingView> data = new LinkedList<OtherBookingView>();
        String query = "SELECT * FROM `other_operation_view` ";
        if(!"".equalsIgnoreCase(name) && name != null){
            query += " WHERE bookingtype = 'I' and refno LIKE '%" + name + "%' OR leader LIKE '%" + name + "%' OR product_code LIKE '%" + name + "%' OR "
                    + "product LIKE '%" + name + "%' OR other_date LIKE '%" + name + "%'";
        }
        query += " ORDER BY create_date DESC , refno ASC";
        List<Object[]> listBookingAll = session.createSQLQuery(query)
                .addScalar("refno", Hibernate.STRING)
                .addScalar("create_date", Hibernate.STRING)
                .addScalar("other_date", Hibernate.STRING)
                .addScalar("leader", Hibernate.STRING)
                .addScalar("product", Hibernate.STRING)
                .addScalar("status", Hibernate.STRING)
                .list();
        System.out.println("Query Booking List : " + query);
        for (Object[] B : listBookingAll) {
             OtherBookingView otherBooking = new  OtherBookingView(); 
             otherBooking.setRefno(!"".equals(util.ConvertString(B[0])) ? util.ConvertString(B[0]) : "");
             otherBooking.setCreatedate(!"".equals(util.ConvertString(B[1]))  ? util.ConvertString(B[1]) : "");
             otherBooking.setOtherdate(!"".equals(util.ConvertString(B[2]))  ? util.ConvertString(B[2]) : "");
             otherBooking.setLeader(!"".equals(util.ConvertString(B[3]))  ? util.ConvertString(B[3]) : "");
             otherBooking.setProduct(!"".equals(util.ConvertString(B[4]))  ? util.ConvertString(B[4]) : "");
             otherBooking.setStatus(!"".equals(util.ConvertString(B[5]))  ? util.ConvertString(B[5]) : "");
             data.add(otherBooking);
        }              
        return data;
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
                }else{
                     queryupdate += " ot.guide = " + null;
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
                }else{
                    queryupdate += prefix + " ot.agent = " + null;
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
    public List<OtherBooking> searchOtherBooking(String name) {
        String query = "from OtherBooking ob where ob.master.customer.firstName Like '%"+name+"%' OR ob.master.customer.lastName Like '%"+name+"%' "
                + "OR ob.product.code Like '%"+name+"%' OR  ob.product.name  Like '%"+name+"%' OR ob.master.referenceNo Like '%"+name+"%' "
                + "OR ob.otherDate Like '%"+name+"%' ORDER BY ob.master.createDate DESC , ob.master.referenceNo";
        System.out.println("searchOtherBooking query : " + query);
        org.hibernate.Session session = this.sessionFactory.openSession();
        List<OtherBooking> list = session.createQuery(query).list();
        System.out.println(" list.size()  +++++ ==== " +list.size());
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
            System.out.println("===== Stock Detail List Size ===== : " +stockDetailList.size());
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
        guideCommissionInfo.setDatefrom(!"".equalsIgnoreCase(datefrom) && datefrom != null ? new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(datefrom)) : "");
        guideCommissionInfo.setDateto(!"".equalsIgnoreCase(dateto) && dateto != null ? new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(dateto)) : "");
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
                .addScalar("commission", Hibernate.BIG_DECIMAL)

                .list();
        
        for (Object[] B : QueryGuideComList) {
             OtherGuideCommissionSummaryHeader guidecom = new OtherGuideCommissionSummaryHeader();
             guidecom.setSystemdate(new SimpleDateFormat("dd MMM yy hh:mm", new Locale("us", "us")).format(thisdate));
             guidecom.setUser(username);
             guidecom.setDatefrom(!"".equalsIgnoreCase(datefrom)&& datefrom != null  ? new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(datefrom)) : "");
             guidecom.setDateto(!"".equalsIgnoreCase(dateto) && dateto != null ? new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(dateto)) : "");
             guidecom.setGuidename(util.ConvertString(B[0]));
             guidecom.setPax(B[1]== null ? 0:(Integer)B[1]);
             guidecom.setCommission(B[2] == null ? new BigDecimal("0.00") : (BigDecimal) B[2]);
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
                .addScalar("commission", Hibernate.BIG_DECIMAL)
                .addScalar("selling", Hibernate.BIG_DECIMAL)
                .addScalar("guidename", Hibernate.STRING)
                .addScalar("remark", Hibernate.STRING)
                .list();
        
        for (Object[] B : QueryGuideComList) {
             OtherGuideCommissionSummary guidecom = new OtherGuideCommissionSummary();
             guidecom.setSystemdate(new SimpleDateFormat("dd MMM yy hh:mm", new Locale("us", "us")).format(thisdate));
             guidecom.setUser(username);
             guidecom.setDatefrom(!"".equalsIgnoreCase(datefrom) && datefrom != null  ? new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(datefrom)) : "");
             guidecom.setDateto(!"".equalsIgnoreCase(dateto) && dateto != null ? new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(dateto)) : "");
             guidecom.setTourdate(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(B[0]));
             guidecom.setCode(util.ConvertString(B[1]));
             guidecom.setCustomer(util.ConvertString(B[2]));
             guidecom.setPax(B[3]== null ? 0:(Integer)B[3]);
             guidecom.setComission(B[4]== null ? new BigDecimal("0.00"):(BigDecimal)B[4]);
             guidecom.setSelling(B[5]== null ? new BigDecimal("0.00"):(BigDecimal)B[5]);
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
        agentCommission.setDatefrom(!"".equalsIgnoreCase(datefrom) && datefrom != null ? new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(datefrom)) : "");
        agentCommission.setDateto(!"".equalsIgnoreCase(dateto) && dateto != null  ? new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(dateto)) : "");
        agentCommission.setOtherAgentCommissionInfoDataSource(new JRBeanCollectionDataSource(getOtherAgentReportInfo(datefrom, dateto, user,agentid)));
        agentCommission.setOtherAgentCommissionSummaryDataSource(new JRBeanCollectionDataSource(getOtherAgentReportSummary(datefrom, dateto, user,agentid)));
        return agentCommission;
    }
    
    private List getOtherAgentReportInfo(String datefrom, String dateto, String user,String agentid) {
        Session session = this.sessionFactory.openSession();
        List data = new ArrayList();
        Date thisdate = new Date();
        String query = "";
        UtilityFunction util = new UtilityFunction();
        int checkQuery = 0;
        if( "".equals(datefrom)  && "".equals(dateto) && "".equals(agentid)){
            query = "SELECT * FROM `agent_commission_other` gc ";
        }else{  
            if( datefrom == null  && dateto == null && agentid == null){
                query = "SELECT * FROM `agent_commission_other` gc ";
            }else{
                query = "SELECT * FROM `agent_commission_other` gc  where ";
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
        if ((agentid != null )&&(!"".equalsIgnoreCase(agentid))) {
            if(checkQuery == 1){
                 query += " and gc.id = "+agentid;
            }else{
                checkQuery = 1;
                 query += " gc.id = "+agentid;
            }
        }
        List<Object[]> QueryAgentComList = session.createSQLQuery(query)
                .addScalar("otherdate", Hibernate.DATE)
                .addScalar("code", Hibernate.STRING)
                .addScalar("customer", Hibernate.STRING)
                .addScalar("pax", Hibernate.STRING)
                .addScalar("commission", Hibernate.BIG_DECIMAL)
                .addScalar("selling", Hibernate.BIG_DECIMAL)
                .addScalar("name", Hibernate.STRING)
                .list();
                
        for (Object[] B : QueryAgentComList) {
             OtherAgentCommissionReport   report = new  OtherAgentCommissionReport(); 
             report.setTourdate(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(B[0]));
             report.setTourcode(util.ConvertString(B[1]));
             report.setClient(util.ConvertString(B[2]));
             report.setPax("".equals(util.ConvertString(B[3])) ? 0 : Integer.parseInt(util.ConvertString(B[3])));
             report.setCommission(B[4]== null ? new BigDecimal("0.00"):(BigDecimal)B[4]);
             report.setSell(B[5]== null ? new BigDecimal("0.00"):(BigDecimal)B[5]);
             report.setAgent(util.ConvertString(B[6]));
             report.setSystemdate(new SimpleDateFormat("dd MMM yy hh:mm", new Locale("us", "us")).format(thisdate));
             report.setDatefrom(!"".equalsIgnoreCase(datefrom) && datefrom != null ? new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(datefrom)) : "");
             report.setDateto(!"".equalsIgnoreCase(dateto) && dateto != null ? new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(dateto)) : "");
             report.setUser(user);
             data.add(report);
        }              
                
        return data;
    }
    
    private List getOtherAgentReportSummary(String datefrom, String dateto, String user,String agentid) {
        Session session = this.sessionFactory.openSession();
        List data = new ArrayList();
        Date thisdate = new Date();
        String query = "";
        UtilityFunction util = new UtilityFunction();
        int checkQuery = 0;
        
        if( "".equals(datefrom)  && "".equals(dateto) && "".equals(agentid)){
            query = AGENTCOM_SUMMARY_QUERY + " ";
        }else{  
            if( datefrom == null  && dateto == null && agentid == null){
                query = AGENTCOM_SUMMARY_QUERY + " ";
            }else{
                query = AGENTCOM_SUMMARY_QUERY + "  where ";
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
        if ((agentid != null )&&(!"".equalsIgnoreCase(agentid))) {
            if(checkQuery == 1){
                 query += " and agt.id  = "+agentid;
            }else{
                checkQuery = 1;
                 query += " agt.id  = "+agentid;
            }
        }
        query += " GROUP BY " +
        " `agt`.`code`, " +
        " `agt`.`name` " +
        " HAVING " +
        " ( " +
        " sum(`ot`.`agent_commission`) <> 0 " +
        " )";
        System.out.println("sql :" +query);
        List<Object[]> QueryAgentComSummaryList = session.createSQLQuery(query)
                .addScalar("code", Hibernate.STRING)
                .addScalar("name", Hibernate.STRING)
                .addScalar("count_booking", Hibernate.STRING)
                .addScalar("commission", Hibernate.BIG_DECIMAL)
                .list();
  
        System.out.println("QueryAgentComSummaryList.size : "+QueryAgentComSummaryList.size());
        for (Object[] B : QueryAgentComSummaryList) {
             OtherAgentCommissionSummaryReport report = new OtherAgentCommissionSummaryReport(); 
             report.setCode(util.ConvertString(B[0]));
             report.setName(util.ConvertString(B[1]));
             report.setCountbook("".equals(util.ConvertString(B[2])) ? 0 : Integer.parseInt(util.ConvertString(B[2])));
             report.setCommission(B[3]== null ? new BigDecimal("0.00"):(BigDecimal)B[3]);
             report.setSystemdate(new SimpleDateFormat("dd MMM yy hh:mm", new Locale("us", "us")).format(thisdate));
             report.setDatefrom(!"".equalsIgnoreCase(datefrom) && datefrom != null ? new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(datefrom)) : "");
             report.setDateto(!"".equalsIgnoreCase(dateto) && dateto != null ? new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(dateto)) : "");
             report.setUser(user);
             data.add(report);
        }      
        return data;  
    }

    @Override
    public String getAgentCommission(String otherDate, String row,String agentId,String price) {
        String guideComm = "";
        String guideCommTemp1 = "";
        String guideCommTemp2 = "";
        UtilityFunction util = new UtilityFunction();
        Session session = this.sessionFactory.openSession();
        List<ProductComission> list = session.createQuery("from ProductComission pro where pro.effectiveFrom <= :otherdate and pro.effectiveTo >= :otherdate")
                .setParameter("otherdate", util.convertStringToDate(otherDate)).list();
        if (list.isEmpty()) {
            System.out.println(" list.isEmpty() ");
            return "";
        }else{
            for (int i=0 ; i< list.size() ; i++){
                if(list.get(i).getAgent() != null){
                    if((String.valueOf(list.get(i).getAgent().getId())).equalsIgnoreCase(agentId)){
                        guideCommTemp1 = String.valueOf(list.get(i).getAgentCommission());
                        if(!"".equalsIgnoreCase(String.valueOf(list.get(i).getAgentCommissionPercent())) && !"0.0".equalsIgnoreCase(String.valueOf(list.get(i).getAgentCommissionPercent())) && !"null".equalsIgnoreCase(String.valueOf(list.get(i).getAgentCommissionPercent()))){
                            Double guidecommpercent = new Double(0);
                            guidecommpercent = (Double.parseDouble(price) * ((list.get(i).getAgentCommissionPercent().doubleValue())/100));
                            guideCommTemp1 = String.valueOf(guidecommpercent);
//                            BigDecimal guidecommpercent = new BigDecimal(BigInteger.ZERO);
//                            guidecommpercent = guidecommpercent.add((new BigDecimal(price)).multiply((new BigDecimal(10)).divide(new BigDecimal(100)))) ;
//                            guideCommTemp1 = String.valueOf(guidecommpercent);
                        }
                        
                    }
                }else{
                    guideCommTemp2 = String.valueOf(list.get(i).getAgentCommission());
                    if(!"".equalsIgnoreCase(String.valueOf(list.get(i).getAgentCommissionPercent())) && !"0.0".equalsIgnoreCase(String.valueOf(list.get(i).getAgentCommissionPercent())) && !"null".equalsIgnoreCase(String.valueOf(list.get(i).getAgentCommissionPercent()))){
                        Double guidecommpercent = new Double(0);
                        guidecommpercent = (Double.parseDouble(price) * ((list.get(i).getAgentCommissionPercent().doubleValue())/100));
                        guideCommTemp2 = String.valueOf(guidecommpercent);
//                        BigDecimal guidecommpercent = new BigDecimal(BigInteger.ZERO);
//                        guidecommpercent = guidecommpercent.add((new BigDecimal(price)).multiply((new BigDecimal(10)).divide(new BigDecimal(100)))) ;
//                        guideCommTemp2 = String.valueOf(guidecommpercent);

                    }
                }
            }
        }
        System.out.println("guideCommTemp1  " +guideCommTemp1);
        System.out.println("guideCommTemp2  " +guideCommTemp2);
        if(!"".equalsIgnoreCase(guideCommTemp1)){
            guideComm = guideCommTemp1;
        }else{
            guideComm = guideCommTemp2;
        }
        this.sessionFactory.close();
        session.close();
        return guideComm;
    }
    
    @Override
    public String getGuideCommission(String otherDate,String row,String price) {
        UtilityFunction util = new UtilityFunction();
        String guideComm = "";
        Session session = this.sessionFactory.openSession();
        List<ProductComission> list = session.createQuery("from ProductComission pro where pro.effectiveFrom <= :otherdate and pro.effectiveTo >= :otherdate")
                .setParameter("otherdate", util.convertStringToDate(otherDate)).list();

        if (list.isEmpty()) {
            return "";
        }else{
            ProductComission productComission = list.get(0);
            guideComm = String.valueOf(productComission.getComission());
            
            if(!"0.0".equalsIgnoreCase(String.valueOf(productComission.getComissionPercent())) && !"".equalsIgnoreCase(String.valueOf(productComission.getComissionPercent())) && !"null".equalsIgnoreCase(String.valueOf(productComission.getComissionPercent()))){
                Double guidecomm = new Double(0);
                System.out.println(" price " + price + " ++++ productComission.getComissionPercent() " + productComission.getComissionPercent());
                guidecomm = (Double.parseDouble(price) * (productComission.getComissionPercent()/100));
                guideComm = String.valueOf(guidecomm);
            }

        }
        System.out.println("guideComm  " +guideComm);
        this.sessionFactory.close();
        session.close();
        return guideComm;
    }
}
