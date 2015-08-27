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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
            List<CreditNote> cnList = session.createQuery("from  CreditNote c where  c.cnNo = :cnNo").setParameter("cnNo", cnNo).list();
            if (cnList.isEmpty()) {
                return "";
            }
            CreditNote dbCreditNote = cnList.get(0);
            dbCreditNote.setCreateDate(note.getCreateDate());
            dbCreditNote.setApCode(note.getApCode());
            dbCreditNote.setCnName(note.getCnName());
            dbCreditNote.setCnAddress(note.getCnAddress());
            dbCreditNote.setCnRemark(note.getCnRemark());
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
    public CreditNote getCreditNoteFromCNNo(String cnNo) {

        String query = "from  CreditNote crditNote where  crditNote.cnNo = :cnNo";
        Session session = this.getSessionFactory().openSession();
        CreditNote result = new CreditNote();
        List<CreditNote> List = session.createQuery(query).setParameter("cnNo", cnNo).list();
        if (List.isEmpty()) {
            return null;
        }

        result = List.get(0);
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

    public String gennarateTaxInvoiceNo(Date createDate) {
        String cnNo = "";
        Session session = this.sessionFactory.openSession();
        List<CreditNote> list = new ArrayList<CreditNote>();
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("yyMM");
        Query query = session.createQuery("from CreditNote c where c.cnNo Like :cnNo Order by c.cnNo desc  LIMIT 1");
        query.setParameter("cnNo", "%" + df.format(createDate) + "%");
        query.setMaxResults(1);
        list = query.list();
        if (list.isEmpty()) {
            cnNo = df.format(createDate) + "-" + "0001";
        } else {
            cnNo = String.valueOf(list.get(0).getCnNo());
            if (!cnNo.equalsIgnoreCase("")) {
                System.out.println("taxNo" + cnNo.substring(4, 8) + "/////");
                int running = Integer.parseInt(cnNo.substring(4, 8)) + 1;
                String temp = String.valueOf(running);
                for (int i = temp.length(); i < 4; i++) {
                    temp = "0" + temp;
                }
                cnNo = df.format(createDate) + "-" + temp;
            }
        }
        session.close();
        this.sessionFactory.close();
        return cnNo.replace("-", "");
    }

}
