/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.PaymentStockDao;
import com.smi.travel.datalayer.entity.PaymentStock;
import com.smi.travel.datalayer.entity.PaymentStockDetail;
import com.smi.travel.datalayer.entity.PaymentStockItem;
import com.smi.travel.datalayer.entity.Stock;
import com.smi.travel.datalayer.entity.StockDetail;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Jittima
 */
public class PaymentStockImpl implements PaymentStockDao {
    private SessionFactory sessionFactory;
    private Transaction transaction;

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
    public List<Stock> getListStock() {
        Session session = this.sessionFactory.openSession();
        List<Stock> stocks = session.createQuery("From Stock st Order By st.id DESC ")
                .list();
        if (stocks.isEmpty()) {
            return null;
        }
        return stocks;
    }

    @Override
    public List<StockDetail> getListStockDetailFromStockId(String stockId) {
        Session session = this.sessionFactory.openSession();
        List<StockDetail> stockDetails = session.createQuery("From StockDetail st where st.stock = '"+stockId+"'")
                .list();
        if (stockDetails.isEmpty()) {
            return null;
        }
        return stockDetails;
    }

    @Override
    public PaymentStock getPaymentStockFromPayNo(String payNo) {
        Session session = this.sessionFactory.openSession();
        List<PaymentStock> paymentStocks = session.createQuery("From PaymentStock ps where ps.payStockNo = '"+payNo+"'")
                .list();
        if (paymentStocks.isEmpty()) {
            return null;
        }
        return paymentStocks.get(0);
    }

    @Override
    public List<PaymentStockDetail> getListPaymentStockDetailFromPaymentStockId(String paymentStockId) {
        Session session = this.sessionFactory.openSession();
        List<PaymentStockDetail> paymentStockDetails = session.createQuery("From PaymentStockDetail pd where pd.paymentStock.id = '"+paymentStockId+"'")
                .list();
        if (paymentStockDetails.isEmpty()) {
            return null;
        }
        return paymentStockDetails;
    }

    @Override
    public List<PaymentStockItem> getListPaymentStockItemFromPaymentStockId(String paymentStockId) {
        Session session = this.sessionFactory.openSession();
        List<PaymentStockItem> paymentStockItems = session.createQuery("From PaymentStockItem pi where pi.paymentStockDetail.paymentStock.id = '"+paymentStockId+"'")
                .list();
        if (paymentStockItems.isEmpty()) {
            return null;
        }
        return paymentStockItems;
    }

    @Override
    public String deletePaymentStock(String paymentStockDetailId) {
        String result = "";
        PaymentStockDetail paymentStockDetail = new PaymentStockDetail();
        Session session = this.sessionFactory.openSession();
        transaction = session.beginTransaction();
        try {
            System.out.println(" paymentStockDetailId +++++++++++++= " + paymentStockDetailId);
            // DELETE PAYMENT STOCK DETAIL
            Query querydeleteitem = session.createQuery("Delete From PaymentStockItem psi where psi.paymentStockDetail.id = :paymentStockDetailId");
            querydeleteitem.setParameter("paymentStockDetailId", paymentStockDetailId);
            querydeleteitem.executeUpdate();
            
            Query querydeletedetail = session.createQuery("Delete From PaymentStockDetail psd where psd.id = :paymentStockDetailId");
            querydeletedetail.setParameter("paymentStockDetailId", paymentStockDetailId);
            querydeletedetail.executeUpdate();
            
            transaction.commit();
            result = "success";
        } catch (Exception ex) {
            ex.printStackTrace();
            result = "fail";
        } finally {
            session.close();
            this.sessionFactory.close();
        }
        return result;
    }
    
    private String generatePayNo(){
        String payNo = "";
        Session session = this.sessionFactory.openSession();
        List<String> list = new LinkedList<String>();
        Date thisdate = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyMM", Locale.US);
        Query query = session.createSQLQuery("SELECT RIGHT(pay_stock_no, 4) as payno  FROM payment_stock where pay_stock_no like :payno ORDER BY RIGHT(pay_stock_no, 4) desc");
        query.setParameter("payno", "%"+ df.format(thisdate) + "%");

        query.setMaxResults(1);
        list = query.list();
        
        if (list.isEmpty()) {
            payNo = "PS" + "-" + df.format(thisdate) + "-" + "0001";
        } else {
            payNo = list.get(0);
            System.out.println("payNo === " + payNo + " === ");
            if (!payNo.equalsIgnoreCase("")) {
                int running = Integer.parseInt(payNo) + 1;
                String temp = String.valueOf(running);
                for (int i = temp.length(); i < 4; i++) {
                    temp = "0" + temp;
                }
                payNo = "PS" + "-" + df.format(thisdate) + "-" + temp;
            }
        }
        System.out.println("payNo ::: " +payNo);
        session.close();
        this.sessionFactory.close();
        return payNo.replace("-","");
    }
    
    @Override
    public String insertOrUpdatePaymentStock(PaymentStock paymentStock) {
        String result = "";
        boolean checkupdate = false;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            PaymentStock payment = (PaymentStock) session.load(PaymentStock.class, paymentStock.getId());
            session.refresh(payment);
            if("".equalsIgnoreCase(paymentStock.getId()) || paymentStock.getId() == null){
                result = generatePayNo();
                paymentStock.setPayStockNo(result);
                session.save(paymentStock);

                List<PaymentStockDetail> paymentStockDetails = paymentStock.getPaymentStockDetails();
                if(paymentStockDetails != null){
                    for(int i = 0; i < paymentStockDetails.size(); i++){
                        session.save(paymentStockDetails.get(i));
                        List<PaymentStockItem> paymentStockItems = paymentStockDetails.get(i).getPaymentStockItems();
                        if(paymentStockItems != null){
                            for(int j = 0; j < paymentStockItems.size(); j++){
                                session.save(paymentStockItems.get(j));
                            }
                        }
                    }
                }
            }else{
                System.out.println("+++++++++++++++++++++ UPDATE +++++++++++++++++++++ ");
                session.update(paymentStock);
                List<PaymentStockDetail> paymentStockDetails = paymentStock.getPaymentStockDetails();
                
                if(paymentStockDetails != null && paymentStockDetails.size() != 0){
                    System.out.println(" paymentStockDetails.size()  " + paymentStockDetails.size());
                    for(int i = 0; i < paymentStockDetails.size(); i++){
                        if(!"".equalsIgnoreCase(paymentStockDetails.get(i).getId()) && paymentStockDetails.get(i).getId() != null){
                            session.update(paymentStockDetails.get(i));
                        }else{
                            session.save(paymentStockDetails.get(i));
                        }
                        
                        List<PaymentStockItem> paymentStockItems = paymentStockDetails.get(i).getPaymentStockItems();
                        if(paymentStockItems != null && paymentStockItems.size() != 0){
                            System.out.println("  paymentStockItems.size()  " +  paymentStockItems.size());
                            for(int j = 0; j < paymentStockItems.size(); j++){
                                if(!"".equalsIgnoreCase(paymentStockItems.get(j).getId()) && paymentStockItems.get(j).getId() != null){
                                    session.update(paymentStockItems.get(j));
                                }else{
                                    session.save(paymentStockItems.get(j));
                                }
                            }
                        }
                    }
                }
                checkupdate = true ;
            }
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            if(checkupdate){
                result = "success";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            result = "fail";
        }
        System.out.println("result::"+result);
        return result;
    }

    @Override
    public List<PaymentStockItem> getListPaymentStockItemFromPaymentStockDetailId(String psdId) {
        Session session = this.sessionFactory.openSession();
//        String psId = "";
//        List<PaymentStockDetail> psdlist = session.createQuery("From PaymentStockDetail psd where psd.id = '"+psdId+"'")
//                .list();
//        if (psdlist.isEmpty()) {
//            return null;
//        }else{
//            psId = psdlist.get(0).getPaymentStock().getId();
//        }
        
        List<PaymentStockItem> paymentStockItems = session.createQuery("From PaymentStockItem psi where psi.paymentStockDetail.id = '"+psdId+"'")
                .list();
        if (paymentStockItems.isEmpty()) {
            return null;
        }
        return paymentStockItems;
    }
    
}
