/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.CreditNoteDao;
import com.smi.travel.datalayer.entity.CreditNote;
import com.smi.travel.datalayer.entity.CreditNoteDetail;
import com.smi.travel.datalayer.entity.MFinanceItemstatus;
import com.smi.travel.datalayer.view.entity.CreditNoteView;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
 * @author Surachai
 */
public class CreditNoteImpl implements CreditNoteDao {

    private SessionFactory sessionFactory;
    private Transaction transaction;

    @Override
    public String insertCreditNote(CreditNote note) {

        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(note);
            for (int i = 0; i < note.getCreditNoteDetails().size(); i++) {
                CreditNoteDetail detail = (CreditNoteDetail) note.getCreditNoteDetails().get(i);
                session.save(detail);
            }
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = "success";
        } catch (Exception ex) {
            ex.printStackTrace();
            result = "fail";
        }
        return result;
    }

    @Override
    public String updateCreditNote(CreditNote note) {

        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            String cnNo = note.getCnNo();
            String department = note.getDepartment();
            List<CreditNote> cnList = session.createQuery("from  CreditNote c where  c.cnNo = :cnNo and c.department = :department ")
                    .setParameter("cnNo", cnNo)
                    .setParameter("department", department)
                    .list();
            if (cnList.isEmpty()) {
                return "";
            }
            CreditNote dbCreditNote = cnList.get(0);
//            dbCreditNote.setCreateDate(note.getCreateDate());
            dbCreditNote.setApCode(note.getApCode());
            dbCreditNote.setCnName(note.getCnName());
            dbCreditNote.setCnAddress(note.getCnAddress());
            dbCreditNote.setCnRemark(note.getCnRemark());
            dbCreditNote.setUpdateDate(new Date());
            session.update(dbCreditNote);
            for (int i = 0; i < note.getCreditNoteDetails().size(); i++) {
                CreditNoteDetail detail = (CreditNoteDetail) note.getCreditNoteDetails().get(i);
                detail.setCreditNote(dbCreditNote);
                if (detail.getId() == null || "".equals(detail.getId().trim())) {
                    session.save(detail);
                } else {
                    session.update(detail);
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
        return result;
    }

    @Override
    public String deleteCreditNote(CreditNote note) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CreditNote getCreditNoteFromCNNo(String cnNo, String department) {

        String query = "from  CreditNote crditNote ";
        Session session = this.getSessionFactory().openSession();
        CreditNote result = new CreditNote();
        if((cnNo.indexOf("%")) >= 0){
            query += " where ";
            query += " crditNote.cnNo like '" + cnNo + "' and crditNote.department = :department ORDER BY crditNote.id desc ";
            
            List<CreditNote> creditNoteList = session.createQuery(query.toString())
                .setParameter("department", department)
                .setMaxResults(1)
                .list();
            if(creditNoteList.isEmpty()){
                return null;
            }        
            result = creditNoteList.get(0);
            
        }else{
            query += " where ";
            query += " crditNote.cnNo = :cnNo and crditNote.department = :department ";
            
            List<CreditNote> creditNoteList = session.createQuery(query.toString())
                .setParameter("cnNo", cnNo)
                .setParameter("department", department)
                .setMaxResults(1)
                .list();
            if(creditNoteList.isEmpty()){
                return null;
            }        
            result = creditNoteList.get(0);
        }
        
        return result;
        
    }

    @Override
    public String DeleteCreditNoteDetail(String CreditNoteDetailId) {
        String result = "false";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            CreditNoteDetail detail = new CreditNoteDetail();
            detail.setId(CreditNoteDetailId);
            session.delete(detail);

            transaction.commit();
            session.close();
            result = "true";
        } catch (Exception ex) {
            ex.printStackTrace();
            result = "false";
        }
        return result;
    }

    @Override
    public String UpdateFinanceStatusCreditNote(String CNId, String status) {

        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            List<CreditNote> cnList = session.createQuery("from  CreditNote c where  c.id = :CNId").setParameter("CNId", CNId).list();
            if (cnList.isEmpty()) {
                return "";
            }
            CreditNote dbCreditNote = cnList.get(0);
            MFinanceItemstatus itemStatus = new MFinanceItemstatus();
            itemStatus.setId(status);
            dbCreditNote.setMFinanceItemstatus(itemStatus);
            session.update(dbCreditNote);
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = "success";
        } catch (Exception ex) {
            ex.printStackTrace();
            result = "fail";
        }
        return result;
    }

    /**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public String gennarateTaxInvoiceNo(Date createDate,String department) {
        String cnNo = "";
        Session session = this.sessionFactory.openSession();
        List<String> list = new LinkedList<String>();
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("yyMM");
        String querysql = "";
        if("Wendy".equalsIgnoreCase(department) ||  "Outbound".equalsIgnoreCase(department)){
            querysql = "SELECT RIGHT(cn_no, 4) as cnnum  FROM credit_note where department in ('Wendy','Outbound') and cn_no Like :cnno ORDER BY RIGHT(cn_no, 4) desc";
        }else{
            querysql = "SELECT RIGHT(cn_no, 4) as cnnum  FROM credit_note where department = :department and cn_no Like :cnno ORDER BY RIGHT(cn_no, 4) desc";
        }       
//        Query query = session.createQuery("from CreditNote c where c.cnNo Like :cnNo Order by c.cnNo desc  LIMIT 1");
        Query query = session.createSQLQuery(querysql);
        query.setParameter("cnno", "%" + df.format(createDate) + "%");
        if(!"Wendy".equalsIgnoreCase(department) && !"Outbound".equalsIgnoreCase(department)){
            query.setParameter("department", department);
        }
        query.setMaxResults(1);
        list = query.list();
        if (list.isEmpty()) {
            cnNo = "C-"+df.format(createDate) + "-" + "0001";
        } else {
            cnNo = String.valueOf(list.get(0));
            if (!cnNo.equalsIgnoreCase("")) {
                int running = Integer.parseInt(cnNo) + 1;
                String temp = String.valueOf(running);
                for (int i = temp.length(); i < 4; i++) {
                    temp = "0" + temp;
                }
                cnNo = "C-"+df.format(createDate) + "-" + temp;
            }
        }
        session.close();
        this.sessionFactory.close();
        return cnNo.replace("-", "");
    }

    @Override
    public List<CreditNoteView> getCreditNoteFromFilter(String from, String to, String Department, String status) {
         UtilityFunction util = new UtilityFunction();
         String query = "from CreditNote cn Where";
         int checkQuery = 0;
         String prefix ="";
         if(((from != null) &&(!"".equalsIgnoreCase(from))) &&((to != null) &&(!"".equalsIgnoreCase(to)))){
             query += " cn.cnDate >= '" + util.covertStringDateToFormatYMD(from) + "' and cn.cnDate <= '" + util.covertStringDateToFormatYMD(to) + "' ";
             checkQuery = 1;
         }else if((from != null) &&(!"".equalsIgnoreCase(from))){
             if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
             query += prefix+ " cn.cnDate >= '" + util.covertStringDateToFormatYMD(from) + "'";
             
         }else if((to != null) &&(!"".equalsIgnoreCase(to))){
             if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
             query += prefix+" cn.cnDate <= '" + util.covertStringDateToFormatYMD(to) + "'";
         }
         
         if((Department != null) &&(!"".equalsIgnoreCase(Department))){
             if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
            if("WendyOutbound".equalsIgnoreCase(Department)){
                query += prefix+" cn.department in ('Wendy' , 'Outbound') ";
            }else{
                query += prefix+" cn.department = '"+Department+"'";
            }
         }
         
         if((status != null) &&(!"".equalsIgnoreCase(status))){
             if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
             query += prefix+" cn.MFinanceItemstatus.id = '"+status+"'";
         }
         System.out.println("===== Credit Note Query ===== : "+query);
         if(checkQuery == 0){query = query.replaceAll("Where", "");}
         System.out.println("query : "+query);
         Session session = this.sessionFactory.openSession();
         List<CreditNoteView> viewList = new LinkedList<CreditNoteView>();
         List<CreditNote> NoteList = session.createQuery(query)
                .list();
         if(NoteList.isEmpty()){
             return null;
         }else{
             for(int i=0;i<NoteList.size();i++){
                 viewList.add(mappingCreditNoteView(NoteList.get(i)));
             }
         }
         
        return viewList;
    }
    
    private CreditNoteView mappingCreditNoteView(CreditNote notedetail){
        CreditNoteView noteview = new CreditNoteView();
        UtilityFunction util = new UtilityFunction();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        noteview.setId(notedetail.getId());
        noteview.setApCode(notedetail.getApCode());
        noteview.setCnno(notedetail.getCnNo());
        noteview.setCnname(notedetail.getCnName());
        noteview.setCndate(sdf.format(notedetail.getCnDate()));
        noteview.setDepartment(notedetail.getDepartment());
        noteview.setStatus(notedetail.getMFinanceItemstatus().getName());
        List<CreditNoteDetail> detaillist =  notedetail.getCreditNoteDetails();
        BigDecimal subtotal = new BigDecimal(0);
        BigDecimal grandtotal = new BigDecimal(0);
        for(int i=0;i<detaillist.size();i++){
            CreditNoteDetail detail = detaillist.get(i);
            if(detail.getAmount() != null){
                subtotal = subtotal.add(detail.getAmount());
            }
            if(detail.getRealamount() != null){
                grandtotal = grandtotal.add(detail.getRealamount());
            }
        }
        noteview.setSubtotal(util.setFormatMoney(subtotal));
        noteview.setGrandTotal(util.setFormatMoney(grandtotal));
        return noteview;
    }

    @Override
    public CreditNote getCreditNoteByWildCardSearch(String cnId, String cnNo, String wildCardSearch, String keyCode, String department) {
        StringBuffer query = new StringBuffer(" from CreditNote cn ");
        //Up
        if ("38".equalsIgnoreCase(keyCode)) {
            if("".equalsIgnoreCase(wildCardSearch)){
                query.append(" where ");
                query.append(" cn.id > '" + cnId + "' and cn.department = '" + department + "' ORDER BY cn.id asc ");
            }else{
                query.append(" where ");
                query.append(" cn.id > '" + cnId + "' and cn.department = '" + department + "' and cn.cnNo like '" + wildCardSearch + "' ORDER BY cn.id asc ");
            }    
        }
        //Down
        if ("40".equalsIgnoreCase(keyCode)) {
            if("".equalsIgnoreCase(wildCardSearch)){
                query.append(" where ");
                query.append(" cn.id < '" + cnId + "' and cn.department = '" + department + "' ORDER BY cn.id desc ");
            }else{
                query.append(" where ");
                query.append(" cn.id < '" + cnId + "' and cn.department = '" + department + "' and cn.cnNo like '" + wildCardSearch + "' ORDER BY cn.id desc ");
            }           
        }
        //Lastest
        if ("119".equalsIgnoreCase(keyCode)) {
            query.append(" where ");
            query.append(" cn.department = '" + department + "' ORDER BY cn.id desc ");
        }
        
        Session session = this.sessionFactory.openSession();
        Query HqlQuery = session.createQuery(query.toString());
        System.out.println(HqlQuery.toString());
        HqlQuery.setMaxResults(1);
        List<CreditNote> creditNoteList = HqlQuery.list();
        if (creditNoteList.isEmpty()) {
            StringBuffer queryTemp = new StringBuffer(" from CreditNote cn ");
            queryTemp.append(" where ");
            queryTemp.append(" cn.id = '" + cnId + "' and cn.department = '" + department + "' ");
            HqlQuery = session.createQuery(queryTemp.toString());
            HqlQuery.setMaxResults(1);
            List<CreditNote> creditNoteListTemp = HqlQuery.list();
            return (!creditNoteListTemp.isEmpty() ? creditNoteListTemp.get(0) : null);
        }
        
//        this.sessionFactory.close();
//        session.close();
        return creditNoteList.get(0);
    }

}
