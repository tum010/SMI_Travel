/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.MProductCommissionDao;
import com.smi.travel.datalayer.entity.Product;
import com.smi.travel.datalayer.entity.ProductComission;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Surachai
 */
public class MProductCommissionImpl implements MProductCommissionDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;
    public static final String SEARCH_Last_PRODUCTCOM_QUERY = "from AgentTourComission ac where ac.id IN  ";
    
    @Override
    public List<ProductComission> SearchProductComission(String code, String name, int option) {
        Session session = this.sessionFactory.openSession();
        String query = "FROM ProductComission  pc where" ;
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

        if ((code != null) && (!"".equalsIgnoreCase(code))) {
            query += " pc.productId.code " + queryOperation + " '" + Prefix_Subfix + code + Prefix_Subfix + "'";
            check = 1;
        }
        if ((name != null) && (!"".equalsIgnoreCase(name))) {
            if (check == 1) {
                query += " and ";
            }
            query += " pc.productId.name " + queryOperation + " '" + Prefix_Subfix + name + Prefix_Subfix + "'";
            check = 1;
        }

        if (check == 0) {
            query = query.replaceAll("where", " ");
        }
       
        query += "  group by ac.agent_id , atc.tour_id  ";
        System.out.println("query : " + query);
     /*   
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
        } */
     //   String QueryLastComm = SEARCH_Last_PRODUCTCOM_QUERY + IDList +"order by ac.agentComission.agent.name asc ,ac.daytour.name asc";
       // System.out.println("QueryLastComm : "+QueryLastComm);
        List<ProductComission> list = session.createQuery(query).list();

        if (list.isEmpty()) {
            return null;
        } else {
            
            return list;
        }
    }

    @Override
    public Product getListProductCommissionFromID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String insertProductCommission(ProductComission ProCom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String updateProductCommission(ProductComission ProCom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String DeleteProductComission(ProductComission AgentCom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
}
