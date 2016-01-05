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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
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
        List<StockDetail> stockDetails = session.createQuery("From StockDetail st where st.stock.id = '"+stockId+"'")
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
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            // DELETE PAYMENT STOCK DETAIL
            Query querydeleteitem = session.createQuery("Delete From PaymentStockItem psi where psi.paymentStockDetail.id = :paymentStockDetailId");
            querydeleteitem.setParameter("paymentStockDetailId", paymentStockDetailId);
            querydeleteitem.executeUpdate();
            
            Query querydeletedetail = session.createQuery("Delete From PayStockDetail psd where psd.id = :paymentStockDetailId");
            querydeletedetail.setParameter("paymentStockDetailId", paymentStockDetailId);
            querydeletedetail.executeUpdate();
            
            transaction.commit();
            session.close();
            result = "success";
        } catch (Exception ex) {
            ex.printStackTrace();
            result = "fail";
        }
        return result;
    }
    
    private String generatePayNo(){
        String payNo = "";
        Session session = this.sessionFactory.openSession();
        List<String> list = new LinkedList<String>();
        Date thisdate = new Date();
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("yyMM");
        Query query = session.createSQLQuery("SELECT RIGHT(pay_stock_no, 4) as payno  FROM payment_stock where pay_stock_no like :payno ORDER BY RIGHT(pay_stock_no, 4) desc");
        query.setParameter("payno", "%"+ df.format(thisdate) + "%");

        query.setMaxResults(1);
        list = query.list();
        
//        if (list.isEmpty()) {
//            payNo = "PS" + df.format(thisdate) + "-" + "0001";
//        } else {
//            payNo = list.get(0);
//            System.out.println("payNo === " + payNo + " === ");
//            if (!payNo.equalsIgnoreCase("")) {
//                System.out.println("invNo type" + invNo.substring(1,2) + "/////");
//                invType = invNo.substring(1,2);
//                if(!"V".equals(invType) && !"N".equals(invType)){
//                    int running = Integer.parseInt(invNo) + 1;
//                    String temp = String.valueOf(running);
//                    for (int i = temp.length(); i < 4; i++) {
//                        temp = "0" + temp;
//                    }
//                    invNo = departtype + "-" + df.format(thisdate) + "-" + temp;
//                }else{
//                    int running = Integer.parseInt(invNo) + 1;
//                    String temp = String.valueOf(running);
//                    for (int i = temp.length(); i < 4; i++) {
//                        temp = "0" + temp;
//                    }
//                    invNo = departtype + "-" + df.format(thisdate) + "-" + temp;
//                }
//            }
//        }
//        System.out.println("invNo ::: " +invNo);
//        session.close();
//        this.sessionFactory.close();
        return payNo.replace("-","");
    }
    
}
