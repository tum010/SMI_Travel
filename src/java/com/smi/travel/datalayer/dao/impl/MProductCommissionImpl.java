/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.MProductCommissionDao;
import com.smi.travel.datalayer.entity.Product;
import com.smi.travel.datalayer.entity.ProductComission;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Query;
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
    private static final String DELETEALL_PRODUCTCOMMISSION_QUERY ="DELETE FROM ProductComission pc where pc.productId.id = :proID";
    private static final String DELETE_PRODUCTCOMMISSION_QUERY ="DELETE FROM ProductComission pc where pc.id = :proID";
    private static final String GET_PROCOM_QUERY = "from ProductComission pc where pc.productId.id = :ProID";
    
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
       
        query += " group by pc.productId.id  ";
        System.out.println("query : " + query);
        List<ProductComission> list = session.createQuery(query).list();

        if (list.isEmpty()) {
            return null;
        } else {
            
            return list;
        }
    }

    @Override
    public List<ProductComission> getListProductCommissionFromID(String ProductID) {

        List<ProductComission> result = new LinkedList<ProductComission>();
        Session session = this.sessionFactory.openSession();
        List<ProductComission> ProCommissionList = session.createQuery(GET_PROCOM_QUERY)
                .setParameter("ProID", ProductID)
                .list();
        if (ProCommissionList.isEmpty()) {
            return null;
        }
        return ProCommissionList;
    }

    @Override
    public String insertProductCommission(Product ProCom) {
        String result = "";
        Session session = this.sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            session.save(ProCom);
            List<ProductComission> commissions =ProCom.getProductComissions();
           
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
    public String updateProductCommission(Product ProCom) {
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            
            List<ProductComission> commissions = ProCom.getProductComissions();
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
    public String DeleteProductComission(ProductComission ProCom) {
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(DELETE_PRODUCTCOMMISSION_QUERY);
            String id = ProCom.getId();
            query.setParameter("proID", ProCom.getId());
            int update = query.executeUpdate(); 
            System.out.println("row delete : " + update);
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result += "success";
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            result += "fail";
        }
        return result;
    }
    
    
    @Override
    public String DeleteAllProductComission(Product ProCom) {
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(DELETEALL_PRODUCTCOMMISSION_QUERY);
            query.setParameter("comID", ProCom.getId());
            System.out.println("row delete : "+query.executeUpdate());
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

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    
    
    
}
