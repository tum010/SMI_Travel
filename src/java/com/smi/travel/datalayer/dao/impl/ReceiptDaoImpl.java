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
import com.smi.travel.datalayer.view.entity.ReceiptDetailView;
import com.smi.travel.datalayer.view.entity.ReceiptSearchView;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
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
    UtilityFunction util;
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
            result = gennarateReceiptNo(receipt.getDepartment() , receipt.getRecType() , receipt.getRecDate());
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
    
    private String gennarateReceiptNo(String department, String receiptType,Date recDate){
        String recNo = "";
        Session session = this.sessionFactory.openSession();
        List<String> list = new LinkedList<String>();
//        Date thisdate = new Date();
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("yyMM");
        Query query = session.createSQLQuery("SELECT RIGHT(rec_no, 4) as recnum  FROM receipt where department = :department and rec_type = :recType and rec_no Like :recno  ORDER BY RIGHT(rec_no, 4) desc");
        query.setParameter("recno", "%"+ df.format(recDate) + "%");
        query.setParameter("department", department);
        query.setParameter("recType", receiptType);

        query.setMaxResults(1);
        list = query.list();
        if (list.isEmpty()) {
            recNo = df.format(recDate) + "-" + "0001";
        } else {
            recNo = String.valueOf(list.get(0));
            if (!recNo.equalsIgnoreCase("")) {
                int running = Integer.parseInt(recNo) + 1;
                String temp = String.valueOf(running);
                for (int i = temp.length(); i < 4; i++) {
                    temp = "0" + temp;
                }
                recNo = df.format(recDate) + "-" + temp;
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
        List<ReceiptDetail> list = session.createQuery("from ReceiptDetail d WHERE d.invoiceDetail.id = :invDetailId and d.receipt.MFinanceItemstatus = '1'").setParameter("invDetailId", invDetailId).list();
        if(list.isEmpty()){
            System.out.println("ReceiptDetail empty ");
            return null;
        }
        System.out.println("ReceiptDetail size " + list.size());
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
    
    @Override
    public List<ReceiptSearchView> getReceiptViewFromFilter(String from, String to, String Department, String type) {
         Session session = this.sessionFactory.openSession();
         String query = "from Receipt rec Where";
         int checkQuery = 0;
         String prefix ="";
         if(((from != null) &&(!"".equalsIgnoreCase(from))) &&((to != null) &&(!"".equalsIgnoreCase(to)))){
             query += " rec.recDate >= '" +from +"' and rec.recDate <= '"+to +"' ";
             checkQuery = 1;
         }else if((from != null) &&(!"".equalsIgnoreCase(from))){
             checkQuery = 1;
             query +=  " rec.recDate >= '" +from +"'";
             
         }else if((to != null) &&(!"".equalsIgnoreCase(to))){
             checkQuery = 1;
             query += " rec.recDate <= '" +to +"'";
         }
         
         if((Department != null) &&(!"".equalsIgnoreCase(Department))){
             if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
             query += prefix+" rec.department = '"+Department+"'";
         }
         
         if((type != null) &&(!"".equalsIgnoreCase(type))){
             if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
             query += prefix+ " rec.recType = '"+type+"'";
         }
         
         if(checkQuery == 0){query = query.replaceAll("Where", "");}
         System.out.println("query : "+query);
         List<ReceiptSearchView> viewList = new LinkedList<ReceiptSearchView>();
         List<Receipt> receiptList = session.createQuery(query)
                .list();
         if(receiptList.isEmpty()){
             return null;
         }else{
             for(int i=0;i<receiptList.size();i++){
                 viewList.add(mappingReceiptView(receiptList.get(i)));
             }
         }
         this.sessionFactory.close();
         session.close();
         return viewList;
    }
    
    public ReceiptSearchView mappingReceiptView(Receipt detail){
        ReceiptSearchView view = new ReceiptSearchView();
        UtilityFunction util = new UtilityFunction();
        view.setRecId(detail.getId());
        view.setRecNo(detail.getRecNo());
        view.setRecTo(detail.getRecFrom());
        view.setRecName(detail.getRecName());
        view.setRecType(detail.getRecType());
        view.setDepartment(detail.getDepartment());
        view.setRecDate(detail.getRecDate());
        String InvoiceNo ="";
        BigDecimal amount = new BigDecimal(0);
        for(int i=0;i<detail.getReceiptDetails().size();i++){
            ReceiptDetail recd = (ReceiptDetail)detail.getReceiptDetails().get(i);
            if(recd.getAmount() != null)
            amount = amount.add(recd.getAmount());
            if(recd.getInvoiceDetail() != null){
                String invoice = recd.getInvoiceDetail().getInvoice().getInvNo();
                int checkLap =0;
                if(InvoiceNo.length() ==0){
                    InvoiceNo += invoice;
                }else{
                    String path[] = InvoiceNo.split(",");
                    for(int j=0;j<path.length;j++){
                        if(path[j].equalsIgnoreCase(invoice)){
                            checkLap = 1;
                        }
                    }
                    if(checkLap == 0) InvoiceNo += ","+invoice;
                }
            }
            view.setInvoiceNo(InvoiceNo);
            if(detail.getMAccpay() != null)
            view.setTermPay(detail.getMAccpay().getName());
        }
        
        amount = amount.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        String amounts = String.valueOf(amount);
        view.setAmount(amounts);
        return view;
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
    public Receipt getReceiptfromReceiptId(String recId) {
        Receipt receipt = new Receipt();
        String query = "from Receipt r where r.id =:recId";
        Session session = this.sessionFactory.openSession();
        List<Receipt> receiptList = session.createQuery(query)
                .setParameter("recId", recId)
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
    public List<ReceiptDetailView> getReceiptDetailViewFromInvDetailId(String invDetailId) {
        Session session = this.sessionFactory.openSession();
        List<ReceiptDetailView> receiptDetailViewList = new ArrayList<ReceiptDetailView>();
        List<ReceiptDetail> list = session.createQuery("from ReceiptDetail d WHERE d.invoiceDetail.id = :invDetailId GROUP BY d.receipt.id").setParameter("invDetailId", invDetailId).list();
        if(list.isEmpty()){
            System.out.println("ReceiptDetail empty ");
            return null;
        }
        
        for (int i = 0; i < list.size() ; i++) {
            ReceiptDetailView receiptDetailView = new ReceiptDetailView();
            receiptDetailView.setReceiptNo(list.get(i).getReceipt() != null ? list.get(i).getReceipt().getRecNo(): "");
            receiptDetailView.setReceiptDate(list.get(i).getReceipt() != null ? list.get(i).getReceipt().getRecDate() : util.convertStringToDate(""));
            receiptDetailView.setReceiveDate(list.get(i).getReceipt() != null ? list.get(i).getReceipt().getReceiveDate() : util.convertStringToDate(""));
            receiptDetailView.setMbillTypeStatus(list.get(i).getMBilltype() != null ? list.get(i).getMBilltype().getName() : "");
            
            if(list.get(i).getInvoiceDetail() != null && list.get(i).getInvoiceDetail().getInvoice() != null){
                receiptDetailView.setInvoiceNo(list.get(i).getInvoiceDetail().getInvoice().getInvNo());
            }
            
            receiptDetailViewList.add(receiptDetailView);
        }
        
        session.close();
        this.sessionFactory.close();
        return receiptDetailViewList;
    }

    
}
