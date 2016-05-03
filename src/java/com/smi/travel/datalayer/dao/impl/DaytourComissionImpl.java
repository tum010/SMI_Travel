/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.DaytourComissionDao;
import com.smi.travel.datalayer.entity.AgentTourComission;
import com.smi.travel.datalayer.entity.Daytour;
import com.smi.travel.datalayer.entity.DaytourBooking;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Surachai
 */
public class DaytourComissionImpl implements DaytourComissionDao {

    private SessionFactory sessionFactory;
    private Transaction transaction;
    private static final String GET_BOOKCOMISSION_QUERY = "from DaytourBooking DB where DB.tourDate >= :startdate and DB.tourDate <= :enddate ";
    private static final String SAVE_COMISSION = "UPDATE DaytourBooking DB set DB.guide.id = :guide , DB.agent.id = :agent, DB.agentComission = :agentcom"
            + " , DB.guideCommission = :guidecom , DB.remarkGuideCom = :remarkguide , DB.remarkAgentCom = :remarkagent "
            + " Where DB.id = :bookdaytourid";
    //private static final String UPDATE_PICKUP_ORDER = " UPDATE DaytourBooking DB set DB.pickupOrder = :order Where DB.id = :bookid";
    private static final String GET_GUIDE_COMMISSION = "From Daytour dt where dt.code = :code";
    public static final String GET_AGENT_COMMISSION = "from AgentTourComission ac where (ac.from <= :tourDate and ac.to >= :tourDate) and ac.daytour.code = :tourCode and ac.agentComission.agent.id = :agentId  ";
    
    @Override
    public List<DaytourBooking> getListBookingDaytourComission(String StartDate, String EndDate,String agentID,String guideID) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        String query = GET_BOOKCOMISSION_QUERY;
        System.out.println("agentID + "+agentID);
        System.out.println("guideID + "+guideID);
        if((agentID != null) &&(!"".equalsIgnoreCase(agentID))){
            query += " and DB.agent.id = "+agentID;
        }
        if((guideID != null) &&(!"".equalsIgnoreCase(guideID))){
            query += " and DB.guide.id = "+guideID;
        }
        query += " order by DB.tourDate,DB.daytour.code,DB.master.referenceNo";
        System.out.println("query : "+query);
        List<DaytourBooking> list = session.createQuery(query)
                .setParameter("startdate", util.convertStringToDate(StartDate))
                .setParameter("enddate", util.convertStringToDate(EndDate))
                .list();
        if (list.isEmpty()) {
            return null;
        }

        return list;
    }

    @Override
    public String SaveDaytourComission(List<DaytourBooking> BookList) {
        String result = "";
        String queryupdate = "UPDATE DaytourBooking DB Set";
        
        int checkQuery = 0;
        String prefix = "";

           
        Session session = this.sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            for (int i = 0; i < BookList.size(); i++) {
                queryupdate = "UPDATE DaytourBooking DB Set";
                prefix = "";
                checkQuery = 0;
                
                DaytourBooking book = BookList.get(i);
                if ((book.getGuide() != null) && (book.getGuide().getId() != null)&& (!"- - SELECT - -".equalsIgnoreCase(book.getGuide().getId()) )) {
                    queryupdate += " DB.guide.id = " + book.getGuide().getId();
                    if (checkQuery == 0) {
                        checkQuery = 1;
                        prefix = ",";
                    }
                }
                if ((book.getAgent() != null) && (book.getAgent().getId() != null)&& (!"- - SELECT - -".equalsIgnoreCase(book.getAgent().getId()) )) {

                    queryupdate += prefix + " DB.agent.id = " + book.getAgent().getId();
                    if (checkQuery == 0) {
                        checkQuery = 1;
                        prefix = ",";
                    }
                }

                if ((book.getAgentComission() != null)) {
                    queryupdate += prefix + " DB.agentComission = " + book.getAgentComission();
                    if (checkQuery == 0) {
                        checkQuery = 1;
                        prefix = ",";
                    }
                }

                if ((book.getGuideCommission() != null)) {
                    queryupdate += prefix + " DB.guideCommission = " + book.getGuideCommission();
                    if (checkQuery == 0) {
                        checkQuery = 1;
                        prefix = ",";
                    }
                }

                if ((book.getRemarkGuideCom() != null)) {
                    queryupdate += prefix + " DB.remarkGuideCom = '" + book.getRemarkGuideCom() + "' ";
                    if (checkQuery == 0) {
                        checkQuery = 1;
                        prefix = ",";
                    }
                }

                if ((book.getRemarkAgentCom() != null)) {
                    queryupdate += prefix + " DB.remarkAgentCom = '" + book.getRemarkAgentCom() + "' ";
                    if (checkQuery == 0) {
                        checkQuery = 1;
                        prefix = ",";
                    }
                }

                if (checkQuery == 1) {
                    queryupdate += " Where DB.id = " + book.getId();
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

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public BigDecimal GetGuideComissionFromTour(String Tourcode) {
        Session session = this.sessionFactory.openSession();
        BigDecimal commission = new BigDecimal(0);
        List<Daytour> list = session.createQuery(GET_GUIDE_COMMISSION)
                .setParameter("code", Tourcode)
                .list();
        
        if (list.isEmpty()) {
            return null;
        }else{
            commission = list.get(0).getGuideComission();
        }
        this.sessionFactory.close();
        session.close();
        return commission;
    }

    @Override
    public BigDecimal GetAgentComissionFromTour(String Agentcode, String Tourcode, String Tourdate) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        BigDecimal commission = new BigDecimal(0);
        List<AgentTourComission> list = session.createQuery(GET_AGENT_COMMISSION)
                .setParameter("tourDate", util.convertStringToDate(Tourdate))
                .setParameter("tourCode", Tourcode)
                .setParameter("agentId", Agentcode)
                .list();
        
        if (list.isEmpty()) {
            System.out.println("commission : is zero");
            return commission;
        }else{
            System.out.println("commission : "+commission);
            commission = list.get(0).getComission();
        }
        this.sessionFactory.close();
        session.close();
        return commission;
    }
    
    

}
