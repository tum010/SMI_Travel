/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.view.dao.PostSaleVatDao;
import com.smi.travel.datalayer.view.entity.APNirvana;
import com.smi.travel.datalayer.view.entity.OutputTaxView;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Jittima
 */
public class PostSaleVatImpl implements PostSaleVatDao {
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
    public List<OutputTaxView> SearchOutputTaxViewFromFilter(String from, String to, String department,String status,String type) {
        UtilityFunction util = new UtilityFunction();
        List<OutputTaxView> outputTaxViewList = new ArrayList<OutputTaxView>();
        Session session = this.getSessionFactory().openSession();
        StringBuffer query = new StringBuffer(" SELECT * FROM `output_tax_view` tax");
        boolean haveCondition = false;
        
        if ((status != null) && (!"".equalsIgnoreCase(status))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" `tax`.status = '" + status + "'");
            haveCondition = true;
        }else{
            query.append(haveCondition ? " and" : " where");
            query.append(" (`tax`.status = 'Normal' or `tax`.status = 'Post' or `tax`.status = 'Change' or `tax`.status = 'Void') ");
            haveCondition = true;
        }
        if ((from != null) && (!"".equalsIgnoreCase(from))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" `tax`.taxdate >= '" + from + "'");
            haveCondition = true;
        }
        if ((to != null) && (!"".equalsIgnoreCase(to))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" `tax`.taxdate <= '" + to + "'");
            haveCondition = true;
        }
        if ((department != null) && (!"".equalsIgnoreCase(department))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" `tax`.department = '" + department + "'");
            haveCondition = true;
        }
        
        if ((type != null) && (!"".equalsIgnoreCase(type))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" `tax`.type = '" + type + "'");
            haveCondition = true;
        }
        
        query.append(" Order by `tax`.taxno desc");
         List<Object[]> QueryList = session.createSQLQuery(query.toString())
                .addScalar("taxid", Hibernate.STRING)
                .addScalar("taxno", Hibernate.STRING)
                .addScalar("taxdate", Hibernate.DATE)
                .addScalar("arcode", Hibernate.STRING)
                .addScalar("taxinvname", Hibernate.STRING)
                .addScalar("gross", Hibernate.BIG_DECIMAL)
                .addScalar("vat", Hibernate.BIG_DECIMAL)
                .addScalar("amount", Hibernate.BIG_DECIMAL)
                .addScalar("department", Hibernate.STRING)
                .addScalar("description", Hibernate.STRING)
                .addScalar("status", Hibernate.STRING)
                .addScalar("agttaxno", Hibernate.STRING)
                .addScalar("main", Hibernate.STRING)
                .addScalar("branchno", Hibernate.STRING)
                .addScalar("type", Hibernate.STRING)
                .list();
         
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy");
        DecimalFormat df = new DecimalFormat("#.00"); 
        for (Object[] B : QueryList) {
            OutputTaxView otv = new OutputTaxView();
            otv.setTaxid(util.ConvertString(B[0]));
            otv.setTaxno(util.ConvertString(B[1]));
            otv.setTaxdate("null".equals(String.valueOf(B[2])) ? "" : util.ConvertString(dateformat.format(util.convertStringToDate(String.valueOf(B[2])))));
            otv.setArcode(util.ConvertString(B[3]));
            otv.setTaxinvname(util.ConvertString(B[4]));
            otv.setGross((B[5]) != null ? new BigDecimal(util.ConvertString((df.format(new BigDecimal(util.ConvertString(B[5])))))) : new BigDecimal("0.00"));
            otv.setVat((B[6]) != null ? new BigDecimal(util.ConvertString((df.format(new BigDecimal(util.ConvertString(B[6])))))) : new BigDecimal("0.00"));
            otv.setAmount((B[7]) != null ? new BigDecimal(util.ConvertString((df.format(new BigDecimal(util.ConvertString(B[7])))))) : new BigDecimal("0.00"));
            otv.setDepartment(util.ConvertString(B[8]));
            otv.setDescription(util.ConvertString(B[9]));
            otv.setStatus(util.ConvertString(B[10]));
            otv.setType(util.ConvertString(B[14]));
            outputTaxViewList.add(otv);
        }
        
        this.sessionFactory.close();
        session.close();
        return outputTaxViewList;
    }

    @Override
    public String UpdateOutputTaxStatus(List<OutputTaxView> outputTaxViewList) {
        int result = 0;
        String hql = "" ;
        try {
            Session session = this.sessionFactory.openSession();
            setTransaction(session.beginTransaction());

            for (int i = 0; i < outputTaxViewList.size(); i++) {
                OutputTaxView otv = outputTaxViewList.get(i);
                String taxId = otv.getTaxid();
                String taxType = otv.getType();
                System.out.println("==== taxType ==== " + taxType);
                if("CN".equalsIgnoreCase(taxType)){
                    hql = "update CreditNote cn set cn.postDate = :thisdate , cn.outputTaxStatus = 1 where cn.id = '"+taxId+"'";
                }else{
                    hql = "update TaxInvoice tax set tax.postDate = :thisdate , tax.outputTaxStatus = 1 where tax.id = '"+taxId+"'";
                }
                Date thisdate = new Date();
                try {
                    Query query = session.createQuery(hql);
                    query.setParameter("thisdate", thisdate);
                    System.out.println(" query " + query);
                    result = query.executeUpdate();
                    System.out.println("Rows affected: " + result);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    result = 0;
                }
            }
            getTransaction().commit();
            session.close();
            this.getSessionFactory().close();
        } catch (Exception ex) {
            getTransaction().rollback();
            ex.printStackTrace();
            result = 0;
        }
        return String.valueOf(result);
    }
}
