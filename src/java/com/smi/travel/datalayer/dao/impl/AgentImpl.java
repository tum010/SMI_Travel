/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.AgentDao;
import com.smi.travel.datalayer.entity.Agent;
import com.smi.travel.util.UtilityFunction;
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
public class AgentImpl implements AgentDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;
    
    
    
    @Override
    public List<Agent> getListAgent(Agent agent, int option) {
        String query = "from Agent a where ";
        String queryOperation = "";
        String Prefix_Subfix = "";
        Session session = this.sessionFactory.openSession();
      
        int check = 0;
        if (option == 1) {
            queryOperation = " = ";
            Prefix_Subfix = "";
        } else if (option == 2) {
            queryOperation = " Like ";
            Prefix_Subfix = "%";
        }
        if ((agent.getCode() != null) && (!"".equalsIgnoreCase(agent.getCode()))) {
            query += " a.code " + queryOperation + " '" + Prefix_Subfix + agent.getCode() + Prefix_Subfix + "'";
            check = 1;
        }
        if ((agent.getName() != null) && (!"".equalsIgnoreCase(agent.getName()))) {
            if (check == 1) {
                query += " and ";
            }
            query += " a.name " + queryOperation + " '" + Prefix_Subfix + agent.getName() + Prefix_Subfix + "'";
            check = 1;
        }
       
        if (check == 0) {
            query = query.replaceAll("where", " ");
        }
        System.out.println("query : " + query);
        List<Agent> HotelList = session.createQuery(query).list();
       
        if (HotelList.isEmpty()) {
            return null;
        }

        return HotelList;
    }

    @Override
    public int insertAgent(Agent agent) {
        int result = 0;
        String agentCode = generateAgentCode(agent.getName());
        agent.setCode(agentCode);
        agent.setApCode(agentCode);
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(agent);
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }
        return result;
    }
    
    private String generateAgentCode(String name) {
        String agentCode = "";
        String no = "";
        Session session = this.sessionFactory.openSession();
        List<String> listAgentCode = new LinkedList<String>();
        String firstChar = name.substring(0, 1);
        Query query = session.createSQLQuery("SELECT code FROM `agent` WHERE CHARACTER_LENGTH(code) = 6 AND code LIKE '" + firstChar + "%' ORDER BY code DESC");
        query.setMaxResults(1);
        listAgentCode = query.list();   
        
        if (listAgentCode.isEmpty()) {
            agentCode = firstChar + "00001";
        } else {
            agentCode = listAgentCode.get(0).substring(0, 1);
            no = listAgentCode.get(0).substring(1);
            int running = Integer.parseInt(no) + 1;
            String temp = String.valueOf(running);
            for (int i = temp.length(); i < 5; i++) {
                temp = "0" + temp;
            }
            agentCode = agentCode + "" + temp;
                
        }
        session.close();
        this.sessionFactory.close();
        return agentCode;
    }

    @Override
    public int updateAgent(Agent agent) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(agent);
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }
        return result;
    }

    @Override
    public int DeleteAgent(Agent agent) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(agent);
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }
        return result;
    }
    
    @Override
    public Agent getAgentFromID(String agentID){
        Agent agent = new Agent();
        String query = "from Agent a where a.id= :agentid";
        Session session = this.sessionFactory.openSession();
        
        List<Agent> agentList = session.createQuery(query).setParameter("agentid", agentID).list();
        session.close();
        if (agentList.isEmpty()) {
            return null;
        }else{
            agent =  agentList.get(0);
        }
        
        return agent;
    }
    
    @Override
    public List<Agent> getListAgent(){
        Session session = this.sessionFactory.openSession();
        String query = "from Agent a ";
        Query q = session.createQuery(query);
        List<Agent> agentList = q.list();
        session.close();
        if (agentList.isEmpty()) {
            return null;
        }
        return agentList;
    }
    
    @Override
    public List<Agent> getListAgentForBookingDetail() {
        int result = 0;
        UtilityFunction util = new UtilityFunction();
        Session session = this.sessionFactory.openSession();
        String hql = "select a.* , REPLACE(REPLACE(a.address, CHAR(13), ''), CHAR(10), '') as addressRe from agent a ";
        List<Object[]> queryList = session.createSQLQuery(hql)
                .addScalar("id", Hibernate.STRING)
                .addScalar("code", Hibernate.STRING)
                .addScalar("name", Hibernate.STRING)
                .addScalar("addressRe", Hibernate.STRING)
                .addScalar("tel", Hibernate.STRING)
                .addScalar("fax", Hibernate.STRING)
                .list();
        
        List<Agent> agentList = new ArrayList<Agent>();
        for (Object[] A : queryList) {
            Agent agent = new Agent();
            agent.setId(util.ConvertString(A[0]));
            agent.setCode(util.ConvertString(A[1]));
            agent.setName(util.ConvertString(A[2]));
            agent.setAddress(util.ConvertString(A[3]));
            agent.setTel(util.ConvertString(A[4]));
            agent.setFax(util.ConvertString(A[5]));
            agentList.add(agent);
        }
       
        session.close();
        if (agentList.isEmpty()) {
            return null;
        }
        return agentList;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public Agent getAgentByName(String name) {
        Agent agent = null;
        String query = "from Agent a where a.name = :name";
        Session session = this.sessionFactory.openSession();
        List<Agent> AgentList = session.createQuery(query).setParameter("name", name).list();
       
        if (AgentList.isEmpty()) {
            return null;
        }else{
           agent =  AgentList.get(0);
        }

        return agent;
    }

    @Override
    public Agent getAgentFromCode(String code) {
        Agent agent = null;
        String query = "from Agent a where a.code = :code";
        Session session = this.sessionFactory.openSession();
        List<Agent> AgentList = session.createQuery(query).setParameter("code", code).list();
       
        if (AgentList.isEmpty()) {
            return null;
        }else{
           agent =  AgentList.get(0);
        }

        return agent;
    }
       
}
