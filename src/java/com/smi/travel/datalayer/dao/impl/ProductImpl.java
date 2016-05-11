/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.common.HibernateSession;
import com.smi.travel.datalayer.dao.ProductDao;
import com.smi.travel.datalayer.entity.Product;
import com.smi.travel.datalayer.view.entity.ProductPriceDetail;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Surachai
 */
public class ProductImpl implements ProductDao {

    private SessionFactory sessionFactory;
    private Transaction transaction;

    

    @Override
    public List<Product> getListProduct() {
        String query = "from Product p";
        Session session = this.sessionFactory.openSession();
        List<Product> ProductList = session.createQuery(query).list();
        List<Product> ProductLists = new LinkedList<Product>();
        if (ProductList.isEmpty()) {
            return null;
        }else{
            for(int i=0 ; i<ProductList.size();i++){
                Product pro = ProductList.get(i);
                pro.setCode(ProductList.get(i).getCode() == null ? null : ProductList.get(i).getCode().replaceAll("'", "\\\\\'"));
                pro.setName(ProductList.get(i).getName() == null ? null : ProductList.get(i).getName().replaceAll("'", "\\\\\'"));
                pro.setDescription(ProductList.get(i).getDescription() == null ? null : ProductList.get(i).getDescription().replaceAll("'", "\\\\\'"));
                ProductLists.add(pro);
            }
        }   
        return ProductLists;
    }

    public Product getProductFromID(String ProductID) {
        String query = "from Product p where p.id = :productid";
        Product product = new Product();
        Session session = this.sessionFactory.openSession();
        List<Product> ProductList = session.createQuery(query).setParameter("productid", ProductID).list();
        if (ProductList.isEmpty()) {
            return null;
        } else {
            product = ProductList.get(0);
        }
       
        
        return product;
    }
    
    @Override
    public List<Product> validateProduct(Product product) {
        String queryCode = "from Product p where p.code = '" + product.getCode() + "'"; 
        Session session = this.sessionFactory.openSession();
        System.out.println("query : " + queryCode);
        List<Product> ProductListCode = session.createQuery(queryCode).list();
        if (ProductListCode.isEmpty()) {

        } else {
            return ProductListCode;
        }
        
        String queryName = "from Product p where p.code = '" + product.getName()+ "'"; 
        System.out.println("query : " + queryName);
        List<Product> ProductListName = session.createQuery(queryName).list();
        if (ProductListCode.isEmpty()) {
            return null;
        } else {
            return ProductListName;
        }
    }

    @Override
    public List<Product> searchProduct(Product product, int option) {
        String query = "from Product p where ";
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
        if ((product.getCode() != null) && (!"".equalsIgnoreCase(product.getCode()))) {
            query += " p.code " + queryOperation + " '" + Prefix_Subfix + product.getCode() + Prefix_Subfix + "'";
            check = 1;
        }
        if ((product.getName() != null) && (!"".equalsIgnoreCase(product.getName()))) {
            if (check == 1) {
                query += " and ";
            }
            query += " p.name " + queryOperation + " '" + Prefix_Subfix + product.getName() + Prefix_Subfix + "'";
            check = 1;
        }

        if (check == 0) {
            query = query.replaceAll("where", " ");
        }
        System.out.println("query : " + query);
        List<Product> ProductList = session.createQuery(query).list();
        if (ProductList.isEmpty()) {
            return null;
        }
        return ProductList;
    }

    @Override
    public int insertProduct(Product product) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(product);
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
    public int updateProduct(Product product) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(product);
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
    public int DeleteProduct(Product product) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(product);
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

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public List<ProductPriceDetail> getListProductPriceDetail() {
        String query = "from ProductPriceDetail p";
        Session session = this.sessionFactory.openSession();
        List<ProductPriceDetail> ProductPriceList = session.createQuery(query).list();
        if (ProductPriceList.isEmpty()) {
            return null;
        }
        return ProductPriceList;
    }

    @Override
    public Product getProductFromCode(String ProductCode) {
        String query = "from Product p where p.code = :code";
        Product product = new Product();
        Session session = this.sessionFactory.openSession();
        List<Product> ProductList = session.createQuery(query).setParameter("code", ProductCode).list();
        if (ProductList.isEmpty()) {
            return null;
        } else {
            product = ProductList.get(0);
        }
        return product;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    

    
}
