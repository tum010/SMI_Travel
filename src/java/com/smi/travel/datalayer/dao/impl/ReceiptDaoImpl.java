/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates 
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.ReceiptDao;
import com.smi.travel.datalayer.entity.Receipt;
import com.smi.travel.datalayer.entity.ReceiptCredit;
import com.smi.travel.datalayer.entity.ReceiptDetail;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    public Receipt getReceiptfromReceiptNo(String receiptNo,String department,String recType) {
        Receipt receipt = new Receipt();
        String query = "from Receipt r where r.recNo =:recNo and r.department =:department and r.recType =:recType";
        Session session = this.sessionFactory.openSession();
        List<Receipt> receiptList = session.createQuery(query)
                .setParameter("recNo", receiptNo)
                .setParameter("department", department)
                .setParameter("recType", recType)
                .list();
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
        int resulttemp = 0 ;
        Session session = this.sessionFactory.openSession();
        
        try {
            Query query = session.createQuery("UPDATE Receipt rec set rec.MFinanceItemstatus.id = :status  WHERE rec.id = :receiptId");
            query.setParameter("receiptId", receiptId);
            query.setParameter("status", String.valueOf(status));
            resulttemp = query.executeUpdate();    
        } catch (Exception ex) {
            ex.printStackTrace();
            resulttemp = 0;
        }
        System.out.println("resulttemp :: " + resulttemp + " ///");
        if(resulttemp == 1){
            result = "success";
        }else{
            result = "fail";
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
        df.applyPattern("yyMM");
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
                    System.out.println(" receiptDetails.get(i).getId() " + receiptDetails.get(i).getId());
                    if(receiptDetails.get(i).getId() == null || "".equals(receiptDetails.get(i).getId())){
                        session.save(receiptDetails.get(i));
                    } else {
                        session.update(receiptDetails.get(i));
                    }             
                }
            }
            
            List<ReceiptCredit> receiptCredit = receipt.getReceiptCredits();
            if(receiptCredit != null){
                for(int i = 0; i < receiptCredit.size(); i++){
                    System.out.println(" receiptCredit.get(i).getId() " + receiptCredit.get(i).getId());
                    if(receiptCredit.get(i).getId() == null || "".equals(receiptCredit.get(i).getId())){
                        session.save(receiptCredit.get(i));
                    } else {
                        System.out.println(" session.update(receiptCredit.get(i)) ");
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
        String result = "";
        ReceiptDetail receiptDetail = new ReceiptDetail();
        List<ReceiptDetail> receiptDetailList = new ArrayList<ReceiptDetail>();
        Session session = this.sessionFactory.openSession();
        if(receiptId.isEmpty()){
            String query = "from ReceiptDetail d where d.id = :receiptDetailId";
            receiptDetailList = session.createQuery(query).setParameter("receiptDetailId", receiptDetailId).list();
            System.out.println(" Delete ReceiptDetailList size (1) "+receiptDetailList.size());
            if (receiptDetailList.isEmpty()) {
                return null;
            }else{
                receiptDetail =  receiptDetailList.get(0);
                try {
                    transaction = session.beginTransaction();
                    session.delete(receiptDetail);
                    transaction.commit();
                    result = "success";
                } catch (Exception ex) {
                    ex.printStackTrace();
                    result = "fail";
                }
            }
        } else { 
            String query = "from ReceiptDetail d where d.id = :receiptDetailId and d.receipt.id =:receiptId ";
            receiptDetailList = session.createQuery(query).setParameter("receiptDetailId", receiptDetailId).setParameter("receiptId", receiptId).list();
            System.out.println(" Delete ReceiptDetailList size "+receiptDetailList.size());
            if (receiptDetailList.isEmpty()) {
                return null;
            }else{
                for(int i = 0; i < receiptDetailList.size(); i++){
                    receiptDetail = receiptDetailList.get(i);
                    try {
                        transaction = session.beginTransaction();
                        session.delete(receiptDetail);
                        transaction.commit();
                        result = "success";
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        result = "fail";
                    }
                }
            }
        }
        session.close();
        this.sessionFactory.close();
        return result;
    }

    @Override
    public String DeleteReceiptBank(String receiptId, int index) {
        String result = "";
        String query = "";
        Receipt receipt = new Receipt();
        List<Receipt> receiptList = new ArrayList<Receipt>();
        Session session = this.sessionFactory.openSession();
        query = "from Receipt rec where rec.id = :receiptId";
        receiptList = session.createQuery(query).setParameter("receiptId", receiptId).list();
        if (receiptList.isEmpty()) {
            return null;
        }else{
            receipt =  receiptList.get(0);
            if(index == 1){
                receipt.setChqBank1(null);
                receipt.setChqAmount1(null);
                receipt.setChqDate1(null);
                receipt.setChqNo1(null);
            }else if(index == 2){
                receipt.setChqBank2(null);
                receipt.setChqAmount2(null);
                receipt.setChqDate2(null);
                receipt.setChqNo2(null);
            }
            try {
                transaction = session.beginTransaction();
                session.update(receipt);
                transaction.commit();
                result = "success";
            } catch (Exception ex) {
                ex.printStackTrace();
                result = "fail";
            }
        }
        session.close();
        this.sessionFactory.close();
        return result;
    }

    @Override
    public String DeleteReceiptChq(String receiptCreditId , String receiptId) {
        String result = "";
        ReceiptCredit receiptCredit = new ReceiptCredit();
        List<ReceiptCredit> receiptCreditList = new ArrayList<ReceiptCredit>();
        Session session = this.sessionFactory.openSession();
        if(receiptId.isEmpty() || "".equals(receiptId)){
            String query = "from ReceiptCredit c where c.id = :receiptCreditId";
            receiptCreditList = session.createQuery(query).setParameter("receiptCreditId", receiptCreditId).list();
            System.out.println(" Delete ReceiptDetailList size (1) "+receiptCreditList.size());
            if (receiptCreditList.isEmpty()) {
                return null;
            }else{
                receiptCredit =  receiptCreditList.get(0);
                try {
                    transaction = session.beginTransaction();
                    session.delete(receiptCredit);
                    transaction.commit();
                    result = "success";
                } catch (Exception ex) {
                    ex.printStackTrace();
                    result = "fail";
                }
            }
        } else { 
            String query = "from ReceiptCredit c where c.id = :receiptCreditId and c.receipt.id =:receiptId ";
            receiptCreditList = session.createQuery(query).setParameter("receiptCreditId", receiptCreditId).setParameter("receiptId", receiptId).list();
            System.out.println(" Delete ReceiptDetailList size "+receiptCreditList.size());
            if (receiptCreditList.isEmpty()) {
                return null;
            }else{
                for(int i = 0; i < receiptCreditList.size(); i++){
                    receiptCredit = receiptCreditList.get(i);
                    try {
                        transaction = session.beginTransaction();
                        session.delete(receiptCredit);
                        transaction.commit();
                        result = "success";
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        result = "fail";
                    }
                }
            }
        }
        session.close();
        this.sessionFactory.close();
        return result;
    }

    @Override
    public List<ReceiptDetail> getReceiptDetailFromInvDetailId(String invDetailId) {
        Session session = this.sessionFactory.openSession();
        List<ReceiptDetail> list = session.createQuery("from ReceiptDetail d WHERE d.invoiceDetail.id = :invDetailId").setParameter("invDetailId", invDetailId).list();
        if(list.isEmpty()){
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return list;
    }
    
    @Override
    public List<ReceiptDetail> getReceiptDetailFromReceiptId(String receiptId) {
        Session session = this.sessionFactory.openSession();
        List<ReceiptDetail> list = session.createQuery("from ReceiptDetail d WHERE d.receipt.id = :receiptId").setParameter("receiptId", receiptId).list();
        if(list.isEmpty()){
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return list;
    }

    @Override
    public List<ReceiptCredit> getReceiptCreditFromReceiptId(String receiptId) {
        Session session = this.sessionFactory.openSession();
        List<ReceiptCredit> list = session.createQuery("from ReceiptCredit c WHERE c.receipt.id = :receiptId").setParameter("receiptId", receiptId).list();
        if(list.isEmpty()){
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return list;
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
