/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.lowagie.text.pdf.PdfName;
import com.smi.travel.datalayer.dao.ReceiptDao;
import com.smi.travel.datalayer.entity.MRunningCode;
import com.smi.travel.datalayer.entity.Receipt;
import com.smi.travel.datalayer.entity.ReceiptCredit;
import com.smi.travel.datalayer.entity.ReceiptDetail;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
 public class ReceiptDaoImpl implements ReceiptDao{
     
    private SessionFactory sessionFactory;
    private Transaction transaction;

    @Override
    public List<HashMap<String, Object>> getAirlineComFromPaymentNo(String invoiceNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Receipt getReceiptfromReceiptNo(String receiptNo) {
        Receipt receipt = new Receipt();
        String query = "from Receipt r where r.recNo =:recNo";
        Session session = this.sessionFactory.openSession();
        List<Receipt> receiptList = session.createQuery(query).setParameter("recNo", receiptNo).list();
        if (receiptList.isEmpty()) {
            return null;
        }else{
            receipt =  receiptList.get(0);
        }
        session.close();
        this.sessionFactory.close();
        return receipt;   
    }

    @Override
    public String UpdateFinanceStatusReceipt(String receiptId, int status) {
        String result = "";
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("UPDATE Receipt rec set rec.MFinanceItemstatus.id = :status  WHERE rec.id = :receiptId");
        query.setParameter("receiptId", receiptId);
        query.setParameter("status", status);
        int results = query.executeUpdate();    
        System.out.println("results :: " + results + " ///");
        if(results == 1){
            result = "";
        }else{
            result = "";
        }
        session.close();
        this.sessionFactory.close();
        return result;
    }

    @Override
    public String insertReceipt(Receipt receipt) {
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            result = gennarateReceiptNo();
            receipt.setRecNo(result);
            session.save(receipt);
            
            List<ReceiptDetail> receiptDetails = receipt.getReceiptDetails();
            if(receiptDetails != null){
                for(int i = 0; i < receiptDetails.size(); i++){
                   session.save(receiptDetails.get(i));
                }
            }
            
            List<ReceiptCredit> receiptCredit = receipt.getReceiptCredits();
            if(receiptCredit != null){
                for(int i = 0; i < receiptCredit.size(); i++){
                   session.save(receiptCredit.get(i));
                }
            }
            transaction.commit();
            session.close();
            this.sessionFactory.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            result = null;
        }
        System.out.println("result::"+result);
        return result;
    }
    
    private String gennarateReceiptNo(){
        String recNo = "";
        Session session = this.sessionFactory.openSession();
        List<Receipt> list = new LinkedList<Receipt>();
        Date thisdate = new Date();
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("MMyy");
        Query query = session.createQuery("from Receipt r where r.recNo Like :recNo Order by r.recNo desc");
        query.setParameter("recNo", "%"+ df.format(thisdate) + "%");
        query.setMaxResults(1);
        list = query.list();
        if (list.isEmpty()) {
            recNo = df.format(thisdate) + "-" + "0001";
        } else {
            recNo = String.valueOf(list.get(0).getRecNo());
            if (!recNo.equalsIgnoreCase("")) {
                System.out.println("recNo" + recNo.substring(4,8) + "/////");
                int running = Integer.parseInt(recNo.substring(4,8)) + 1;
                String temp = String.valueOf(running);
                for (int i = temp.length(); i < 4; i++) {
                    temp = "0" + temp;
                }
                recNo = df.format(thisdate) + "-" + temp;
            }
        }
        session.close();
        this.sessionFactory.close();
        return recNo.replace("-","");
    }
    
    @Override
    public String updateReceipt(Receipt receipt) {
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(receipt);
            
            List<ReceiptDetail> receiptDetails = receipt.getReceiptDetails();
            if(receiptDetails != null){
                for(int i = 0; i < receiptDetails.size(); i++){
                    if(receiptDetails.get(i).getId() == null){
                        session.save(receiptDetails.get(i));
                    } else {
                        session.update(receiptDetails.get(i));
                    }             
                }
            }
            
            List<ReceiptCredit> receiptCredit = receipt.getReceiptCredits();
            if(receiptCredit != null){
                for(int i = 0; i < receiptCredit.size(); i++){
                    if(receiptCredit.get(i).getId() == null){
                        session.save(receiptCredit.get(i));
                    } else {
                        session.update(receiptCredit.get(i));
                    }             
                }
            }
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = "success";
        } catch (Exception ex) {
            ex.printStackTrace();
            result = "fail";
        }
        System.out.println("result::"+result);
        return result;  
    }

    @Override
    public String deleteReceipt(Receipt receipt) {
        String result = "";
        System.out.println("receipt.getId() :" + receipt.getId());
        if (IsExistReceiptDetail(receipt.getId())) {
            result = "delete unsuccessful.Please delete all Receipt in this Receipt Detail";
            return result;
        }
        if (IsExistReceiptCredit(receipt.getId())) {
            result = "delete unsuccessful.Please delete all Receipt in this Receipt Credit";
            return result;
        }
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(receipt);
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = "success";
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
            result = "fail";
        }
        System.out.println("result::"+result);
        return result;
    }
    
    private boolean IsExistReceiptDetail(String receiptId) {
        boolean result;
        Session session = this.sessionFactory.openSession();
        List<ReceiptDetail> list = session.createQuery("from ReceiptDetail d WHERE d.receipt.id = :receiptId").setParameter("receiptId", receiptId).list();
        if (list.isEmpty()) {
            result = false;
        } else {
            result = true;
        }
        session.close();
        this.sessionFactory.close();
        return result;
    }
    
    private boolean IsExistReceiptCredit(String receiptId) {
        boolean result;
        Session session = this.sessionFactory.openSession();
        List<ReceiptCredit> list = session.createQuery("from ReceiptCredit c WHERE c.receipt.id = :receiptId").setParameter("receiptId", receiptId).list();
        if(list.isEmpty()){
            result = false;
        }else{
            result = true;
        }
        session.close();
        this.sessionFactory.close();
        return result;
    }
    
    @Override
    public String DeleteReceiptDetail(String receiptDetailId , String receiptId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String DeleteReceiptBank(String receiptId, int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String DeleteReceiptChq(String receiptCreditId , String receiptId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
    
}
