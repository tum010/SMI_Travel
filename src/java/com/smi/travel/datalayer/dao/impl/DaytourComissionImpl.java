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
import com.smi.travel.datalayer.view.entity.DaytourBookingViewMin;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Hibernate;
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
    private static final String GET_BOOKCOMISSION_QUERY = "from DaytourBooking DB where DB.tourDate >= :startdate and DB.tourDate <= :enddate and DB.MItemstatus.id = 1 and DB.master.MBookingstatus.id != 3 and DB.master.MBookingstatus.id != 4 ";
    private static final String SAVE_COMISSION = "UPDATE DaytourBooking DB set DB.guide.id = :guide , DB.agent.id = :agent, DB.agentComission = :agentcom"
            + " , DB.guideCommission = :guidecom , DB.remarkGuideCom = :remarkguide , DB.remarkAgentCom = :remarkagent "
            + " Where DB.id = :bookdaytourid";
    //private static final String UPDATE_PICKUP_ORDER = " UPDATE DaytourBooking DB set DB.pickupOrder = :order Where DB.id = :bookid";
    private static final String GET_GUIDE_COMMISSION = "From Daytour dt where dt.code = :code";
    public static final String GET_AGENT_COMMISSION = "from AgentTourComission ac where (ac.from <= :tourDate and ac.to >= :tourDate) and ac.daytour.code = :tourCode and ac.agentComission.agent.id = :agentId  ";
    
    @Override
    public List<DaytourBookingViewMin> getListBookingDaytourComission(String StartDate, String EndDate,String agentID,String guideID) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
//        String query = GET_BOOKCOMISSION_QUERY;
        String query = " SELECT * FROM daytour_booking_view_min db WHERE db.tourdate >= '"+ StartDate +"' AND db.tourdate <= '"+ EndDate +"'  " ;
        if((agentID != null) &&(!"".equalsIgnoreCase(agentID))){
            query += " and db.agentid = "+agentID;
        }
        if((guideID != null) &&(!"".equalsIgnoreCase(guideID))){
            query += " and db.guideid = "+guideID;
        }
        query += " order by db.tourdate , db.`daytourcode` , db.refno ";
        System.out.println("query : "+query);
        
        List<DaytourBookingViewMin> list = new ArrayList<DaytourBookingViewMin>();

        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("id", Hibernate.STRING)
                .addScalar("masterid", Hibernate.STRING)
                .addScalar("tourid", Hibernate.STRING)
                .addScalar("tourdate", Hibernate.DATE)
                .addScalar("pickup", Hibernate.STRING)
                .addScalar("pickupdetail", Hibernate.STRING)
                .addScalar("pickuproom", Hibernate.STRING)
                .addScalar("pickuptime", Hibernate.STRING)
                .addScalar("requirement", Hibernate.STRING)
                .addScalar("remark", Hibernate.STRING)
                .addScalar("memo", Hibernate.STRING)
                .addScalar("guideid", Hibernate.STRING)
                .addScalar("agentid", Hibernate.STRING)
                .addScalar("ispay", Hibernate.STRING)
                .addScalar("adult", Hibernate.STRING)
                .addScalar("child", Hibernate.STRING)
                .addScalar("infant", Hibernate.STRING)
                .addScalar("isbill", Hibernate.STRING)
                .addScalar("status", Hibernate.STRING)
                .addScalar("agentcomission", Hibernate.STRING)
                .addScalar("guidecommission", Hibernate.STRING)
                .addScalar("remarkguidecom", Hibernate.STRING)
                .addScalar("remarkagentcom", Hibernate.STRING)
                .addScalar("pickuporder", Hibernate.STRING)
                .addScalar("tempid", Hibernate.STRING)
                .addScalar("bookingstatus", Hibernate.STRING)
                .addScalar("daytourcode", Hibernate.STRING)
                .addScalar("refno", Hibernate.STRING)
                .addScalar("agentname", Hibernate.STRING)
                .addScalar("initialname", Hibernate.STRING)
                .addScalar("lastname", Hibernate.STRING)
                .addScalar("firstname", Hibernate.STRING)
                .addScalar("guidename", Hibernate.STRING)
                .list();
        
        for (Object[] B : QueryStaffList) {
            DaytourBookingViewMin db = new DaytourBookingViewMin();
            db.setId(B[0]== null ? "" :util.ConvertString(B[0]));
            db.setMasterid(B[1]== null ? "" :util.ConvertString(B[1]));
            db.setTourid(B[2]== null ? "" :util.ConvertString(B[2]));
            db.setTourdate(B[3]== null ? null : util.convertStringToDate(util.ConvertString(B[3])));
            db.setPickup(B[4]== null ? "" :util.ConvertString(B[4]));
            db.setPickupdetail(B[5]== null ? "" :util.ConvertString(B[5]));
            db.setPickup_room(B[6]== null ? "" :util.ConvertString(B[6]));
            db.setPickup_time(B[7]== null ? "" :util.ConvertString(B[7]));
            db.setRequirement(B[8]== null ? "" :util.ConvertString(B[8]));
            db.setRemark(B[9]== null ? "" :util.ConvertString(B[9]));
            db.setMemo(B[10]== null ? "" :util.ConvertString(B[10]));
            db.setGuideid(B[11]== null ? "" :util.ConvertString(B[11]));
            db.setAgentid(B[12]== null ? "" :util.ConvertString(B[12]));
            db.setIspay(B[13]== null ? "" :util.ConvertString(B[13]));
            db.setAdult(B[14]== null ? "" :util.ConvertString(B[14]));
            db.setChild(B[15]== null ? "" :util.ConvertString(B[15]));
            db.setInfant(B[16]== null ? "" :util.ConvertString(B[16]));
            db.setIsbill(B[17]== null ? "" :util.ConvertString(B[17]));
            db.setStatus(B[18]== null ? "" :util.ConvertString(B[18]));
            db.setAgentcomission(B[19]== null ? "" :util.ConvertString(B[19]));
            db.setGuidecommission(B[20]== null ? "" :util.ConvertString(B[20]));
            db.setRemarkguidecom(B[21]== null ? "" :util.ConvertString(B[21]));
            db.setRemarkagentcom(B[22]== null ? "" :util.ConvertString(B[22]));
            db.setPickuporder(B[23]== null ? "" :util.ConvertString(B[23]));
            db.setTempid(B[24]== null ? "" :util.ConvertString(B[24]));
            db.setBookingstatus(B[25]== null ? "" :util.ConvertString(B[25]));
            db.setDaytourcode(B[26]== null ? "" :util.ConvertString(B[26]));
            db.setRefno(B[27]== null ? "" :util.ConvertString(B[27]));
            db.setAgentname(B[28]== null ? "" :util.ConvertString(B[28]));
            db.setInitialname(B[29]== null ? "" :util.ConvertString(B[29]));
            db.setLastname(B[30]== null ? "" :util.ConvertString(B[30]));
            db.setFirstname(B[31]== null ? "" :util.ConvertString(B[31]));
            db.setGuidename(B[32]== null ? "" :util.ConvertString(B[32]));
            list.add(db);
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
                if ((book.getGuide() != null) && (book.getGuide().getId() != null)&& (!"- - SELECT - -".equalsIgnoreCase(book.getGuide().getId()) ) ) {
                    queryupdate += " DB.guide.id = " + book.getGuide().getId();
                    if (checkQuery == 0) {
                        checkQuery = 1;
                        prefix = ",";
                    }
                }else{
                    queryupdate += " DB.guide = " + null ;
                    if (checkQuery == 0) {
                        checkQuery = 1;
                        prefix = ",";
                    }
                }
                
                if ((book.getAgent() != null) && (book.getAgent().getId() != null)&& (!"- - SELECT - -".equalsIgnoreCase(book.getAgent().getId()) ) ) {

                    queryupdate += prefix + " DB.agent.id = " + book.getAgent().getId();
                    if (checkQuery == 0) {
                        checkQuery = 1;
                        prefix = ",";
                    }
                }else{
                    queryupdate += prefix + " DB.agent = " + null ;
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
