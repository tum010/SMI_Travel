/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.MTourCommissionDao;
import com.smi.travel.datalayer.entity.Agent;
import com.smi.travel.datalayer.entity.AgentComission;
import com.smi.travel.datalayer.entity.AgentTourComission;
import com.smi.travel.datalayer.entity.Daytour;
import java.util.ArrayList;
import java.util.LinkedList;
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
public class MTourCommissionImpl implements MTourCommissionDao {

    private SessionFactory sessionFactory;
    private Transaction transaction;
    public static final String DELETE_AGENTCOMMISSION_QUERY = "DELETE FROM AgentTourComission ac where ac.agentComission.id = :comID";
    public static final String DELETE_AGENTCOMPRICE_QUERY = "";
    public static final String SEARCH_AGENTTOURCOM_QUERY = "from AgentTourComission ac where ac.agentComission.id = :comID ";
    public static final String SEARCH_Last_AGENTTOURCOM_QUERY = "from AgentTourComission ac where ac.id IN  ";
    //public static final String GET_AGENTCOM_QUERY = "from AgentTourComission ac where ac.agentComission.id = :comID and ac.daytour.id = :tourID ";
    public static final String GET_AGENTCOM_QUERY = "from AgentTourComission ac where ac.agentComission.agent.id = :agentID and ac.daytour.id = :tourID ";
                                                                                         
    
    @Override
    public List<AgentTourComission> SearchComission(Agent AgentCom, Daytour daytour, int option) {
      Session session = this.sessionFactory.openSession();
        String query = "SELECT max(atc.id) as lastTourCom FROM `agent_tour_comission` atc " 
                     + "INNER JOIN agent_comission ac on ac.id = atc.agent_com_id "
                     + "INNER JOIN daytour dt on dt.id = atc.tour_id "
                     + "INNER JOIN agent agt on agt.id = ac.agent_id  where ";
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

        if ((AgentCom.getCode() != null) && (!"".equalsIgnoreCase(AgentCom.getCode()))) {
            query += " agt.`code` " + queryOperation + " '" + Prefix_Subfix + AgentCom.getCode() + Prefix_Subfix + "'";
            check = 1;
        }
        if ((AgentCom.getName() != null) && (!"".equalsIgnoreCase(AgentCom.getName()))) {
            if (check == 1) {
                query += " and ";
            }
            query += " agt.`name` " + queryOperation + " '" + Prefix_Subfix + AgentCom.getName() + Prefix_Subfix + "'";
            check = 1;
        }
        if ((daytour.getCode() != null) && (!"".equalsIgnoreCase(daytour.getCode()))) {
            if (check == 1) {
                query += " and ";
            }
            query += " dt.`code` " + queryOperation + " '" + Prefix_Subfix +daytour.getCode() + Prefix_Subfix + "'";
            check = 1;
        }
        if ((daytour.getName() != null) && (!"".equalsIgnoreCase(daytour.getName()))) {
            if (check == 1) {
                query += " and ";
            }
            query += " dt.`name` " + queryOperation + " '" + Prefix_Subfix +daytour.getName() + Prefix_Subfix + "'";
            check = 1;
        }

        if (check == 0) {
            query = query.replaceAll("where", " ");
        }
       
        query += "  group by ac.agent_id , atc.tour_id  ";
        System.out.println("query : " + query);
        
        List<String> QueryComList = session.createSQLQuery(query)
                .addScalar("lastTourCom", Hibernate.STRING)
                .list();
        String IDList = "(";
        for(int i=0;i<QueryComList.size();i++){
            IDList += QueryComList.get(i) +",";
            if(i == QueryComList.size()-1){
                IDList = IDList.substring(0, IDList.length()-1)+" )";
            }
        }
        if(QueryComList.size() == 0){
            return null;
        }
        String QueryLastComm = SEARCH_Last_AGENTTOURCOM_QUERY + IDList +"order by ac.agentComission.agent.name asc ,ac.daytour.name asc";
        System.out.println("QueryLastComm : "+QueryLastComm);
        List<AgentTourComission> list = session.createQuery(QueryLastComm).list();
        
        //SEARCH_Last_AGENTTOURCOM_QUERY
        if (list.isEmpty()) {
            return null;
        } else {
            
            return list;
        }

    }
    

    
    @Override
    public String insertTourCommission(AgentComission AgentCom) {
        String result = "";
        Session session = this.sessionFactory.openSession();
        try {
           
            transaction = session.beginTransaction();
            session.save(AgentCom);
            
            
            List<AgentTourComission> commissions = new ArrayList<AgentTourComission>(AgentCom.getAgentTourComissions());
            
            for (int i = 0; i < commissions.size(); i++) {
                session.save(commissions.get(i));
            }

            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = "success";
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            session.close();
            this.sessionFactory.close();
            result = "fail";
        }
        return result;
    }

    @Override
    public String updateTourCommission(AgentComission AgentCom) {
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            //session.update(AgentCom);

            List<AgentTourComission> commissions = new ArrayList<AgentTourComission>(AgentCom.getAgentTourComissions());
            for (int i = 0; i < commissions.size(); i++) {
                if (commissions.get(i).getId() == null) {
                    session.save(commissions.get(i));
                } else {
                    session.update(commissions.get(i));
                }
            }

            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = "success";
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            result = "fail";
        }
        return result;
    }

  

    @Override
    public String DeleteAgentTourComission(AgentComission AgentCom) {
        String result = "";
        /*
        if(IsExistComPrice(AgentCom.getId())){
            result = "delete unsuccessful.Please delete all commission in this agent and tour";
            return result;
        }*/
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            Agent agent = new Agent();
            agent.setId("1");
            AgentCom.setAgent(agent);
            Query query = session.createQuery(DELETE_AGENTCOMMISSION_QUERY);
            query.setParameter("comID", AgentCom.getId());
            System.out.println("row delete : "+query.executeUpdate());
            session.delete(AgentCom);
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = "success";
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            result = "fail";
        }
        return result;
    }

    @Override
    public String DeleteComissionPrice(AgentTourComission AgentPrice) {
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(AgentPrice);
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
    
    private boolean IsExistComPrice(String AgentPriceID){
        boolean result ;
        Session session = this.sessionFactory.openSession();
        List<AgentTourComission> list = session.createQuery(SEARCH_AGENTTOURCOM_QUERY)
                .setParameter("comID", AgentPriceID).list();
        
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
    public List<AgentTourComission> getTourCommissionFromID(String ComID ,String TourID) {
        System.out.println("ComID = "+ComID+", TourId = "+TourID);
        List<AgentTourComission> result = new LinkedList<AgentTourComission>();
        Session session = this.sessionFactory.openSession();
        List<AgentTourComission> AgentTourList = session.createQuery(GET_AGENTCOM_QUERY)
                .setParameter("agentID", ComID)
                .setParameter("tourID", TourID)
                .list();
        if (AgentTourList.isEmpty()) {
            return null;
        }
        return AgentTourList;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }





}
