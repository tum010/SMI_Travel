/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.OtherBookingDao;
import com.smi.travel.datalayer.entity.DaytourBooking;
import com.smi.travel.datalayer.entity.OtherBooking;
import com.smi.travel.datalayer.entity.Product;
import com.smi.travel.util.UtilityFunction;
import java.util.Date;
import java.util.List;
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
    public int insertBookDetailOther(OtherBooking otherbook) {
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
        String query = "from OtherBooking other";
        Session session = this.sessionFactory.openSession();

        Query OtherList = session.createQuery(query);  
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
