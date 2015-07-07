/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.StockDao;
import com.smi.travel.datalayer.entity.Product;
import com.smi.travel.datalayer.entity.ProductComission;
import com.smi.travel.datalayer.entity.Stock;
import com.smi.travel.datalayer.entity.StockDetail;
import com.smi.travel.datalayer.view.entity.StockViewSummary;
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
public class StockImpl implements StockDao{
    
    private SessionFactory sessionFactory;
    private Transaction transaction;
    private static final String DELETEALL_STOCK_QUERY ="DELETE FROM stock st where st.id = :stockID";
    private static final String SELECT_STOCK_DETAIL = "FROM stock_detail std where st.stock_id = :stockID";
    
    @Override
    public String InsertStock(Stock ItemLot) {
        String result = "";
        Session session = this.sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            session.save(ItemLot);
            List<StockDetail> stockDetail =ItemLot.getStockDetails();
           
            for (int i = 0; i < stockDetail.size(); i++) {
                session.save(stockDetail.get(i));
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
    public String UpdateStock(Stock ItemLot) {
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            
            List<StockDetail> stockDetail = ItemLot.getStockDetails();
            for (int i = 0; i < stockDetail.size(); i++) {
                if (stockDetail.get(i).getId() == null) {
                    session.save(stockDetail.get(i));
                } else {
                    session.update(stockDetail.get(i));
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
    public String DeleteStock(Stock ItemLot) {
        List<StockDetail> stockDetailList = new LinkedList<StockDetail>();
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            stockDetailList = checkStockDetail(ItemLot.getId());
            if(stockDetailList == null){
                Query query = session.createQuery(DELETEALL_STOCK_QUERY);
                query.setParameter("stockID", ItemLot.getId());
                System.out.println("row delete : "+query.executeUpdate());
                transaction.commit();
                session.close();
                this.sessionFactory.close();
                result = "success";
            }else{
                result = "fail";
            }          
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            result = "fail";
        }
        return result;
    }

    @Override
    public String DeleteStockDetail(String DetailID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StockViewSummary SearchStockFromFilter(Stock stockData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private StockViewSummary mappingStockView(Stock stockData){
        StockViewSummary stockview = new StockViewSummary();
        
        return stockview;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    private List<StockDetail> checkStockDetail(String stockId) {
        Session session = this.sessionFactory.openSession();
        List<StockDetail> stockDetailList = session.createQuery(SELECT_STOCK_DETAIL)
                .setParameter("stockID", stockId)
                .list();
        if (stockDetailList.isEmpty()) {
            return null;
        }
        return stockDetailList;
    }

    @Override
    public List<Product> getListStockProduct() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}


