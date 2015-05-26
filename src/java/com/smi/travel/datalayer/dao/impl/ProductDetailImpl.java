/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.common.HibernateSession;
import com.smi.travel.datalayer.dao.ProductDetailDao;
import com.smi.travel.datalayer.entity.Product;
import com.smi.travel.datalayer.entity.ProductDetail;
import com.smi.travel.util.UtilityFunction;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Surachai
 */
public class ProductDetailImpl   implements ProductDetailDao{

    private SessionFactory sessionFactory;
    private Transaction transaction;
    private UtilityFunction util;
    
    
    @Override
    public List<ProductDetail> getListProductDetail(String ProductId) {
        String query = "from ProductDetail pd where pd.id = :=productid";
        Session session = this.sessionFactory.openSession();
       
        List<ProductDetail> DetaillList = session.createQuery(query).setParameter("productid", ProductId).list();

        if (DetaillList.isEmpty()) {
            return null;
        }
        
        return DetaillList;
    }

    @Override
    public int insertProductDetail(ProductDetail productDetail) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(productDetail);
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
    public int updateProductDetail(ProductDetail productDetail) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(productDetail);
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
    public int DeleteProductDetail(ProductDetail productDetail) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(productDetail);
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
    public ProductDetail getValueFromProduct(String productID) {
        Date thisDate = new Date();
        System.out.println("productID : "+productID);
       
        util = new UtilityFunction();
        String query = "from ProductDetail p where p.product.id = "+productID+" and  ('"+util.convertDateToString(thisDate)+"' >= p.effectiveFrom) and  ('"+util.convertDateToString(thisDate)+"' <= p.effectiveTo)";
        System.out.println("query : "+query);
        ProductDetail product = new ProductDetail();
        Session session = this.sessionFactory.openSession();
        List<ProductDetail> ProductList = session.createQuery(query).list();
        if (ProductList.isEmpty()) {
            return null;
        }else{
            product = ProductList.get(0);
        }
        return product;
    }


    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    
    
    
}
