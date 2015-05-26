/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.view.dao.ProductPriceDetailDao;
import com.smi.travel.datalayer.view.entity.ProductPriceDetail;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Surachai
 */
public class ProductPriceDetailImpl implements ProductPriceDetailDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;
  
    
    @Override
    public List<ProductPriceDetail> getListProductPriceDetail(ProductPriceDetail productprice, int option) {
        String query = "from ProductPriceDetail p where ";
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
        if ((productprice.getCode() != null) && (!"".equalsIgnoreCase(productprice.getCode()))) {
            query += " p.code " + queryOperation + " '" + Prefix_Subfix + productprice.getCode() + Prefix_Subfix + "'";
            check = 1;
        }
        if ((productprice.getName() != null) && (!"".equalsIgnoreCase(productprice.getName()))) {
            if (check == 1) {
                query += " and ";
            }
            query += " p.name " + queryOperation + " '" + Prefix_Subfix + productprice.getName() + Prefix_Subfix + "'";
            check = 1;
        }
        
        if ((productprice.getProductTypeId() != null) && (!"".equalsIgnoreCase(productprice.getProductTypeId()))) {
            if (check == 1) {
                query += " and ";
            }
            query += " p.ProductTypeId = '"  + productprice.getProductTypeId()  + "'";
            check = 1;
        }
       
        if (check == 0) {
            query = query.replaceAll("where", " ");
        }
        System.out.println("query : " + query);
        List<ProductPriceDetail> ProductList = session.createQuery(query).list();
       
        if (ProductList.isEmpty()) {
            return null;
        }
        return ProductList;
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
    public ProductPriceDetail getListProductPriceDetailFromID(String ProductID) {
        ProductPriceDetail productPrice = new ProductPriceDetail();
        String query = "from ProductPriceDetail p where p.id= :productid";
        Session session = this.sessionFactory.openSession();
        
        List<ProductPriceDetail> productPriceDetailListList = session.createQuery(query).setParameter("productid", ProductID).list();
        session.close();
        if (productPriceDetailListList.isEmpty()) {
            return null;
        }else{
            productPrice =  productPriceDetailListList.get(0);
        }
        return productPrice;
    }
    
    
    
}
