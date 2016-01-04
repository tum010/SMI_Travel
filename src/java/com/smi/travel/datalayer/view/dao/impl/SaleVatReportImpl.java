/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.view.dao.SaleVatReportDao;
import com.smi.travel.datalayer.view.entity.OutputTaxView;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class SaleVatReportImpl implements SaleVatReportDao{
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
    public List<OutputTaxView> SearchOutputTaxViewFromFilter(String from, String to, String department) {
        UtilityFunction util = new UtilityFunction();
        List<OutputTaxView> outputTaxViewList = new ArrayList<OutputTaxView>();
        Session session = this.getSessionFactory().openSession();
        StringBuffer query = new StringBuffer(" SELECT * FROM `output_tax_view` tax  where `tax`.status = 'Post' ");
        boolean haveCondition = true;
                
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
                .list();
         
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy");
        
        for (Object[] B : QueryList) {
            OutputTaxView otv = new OutputTaxView();
            otv.setTaxid(util.ConvertString(B[0]));
            otv.setTaxno(util.ConvertString(B[1]));
            otv.setTaxdate("null".equals(String.valueOf(B[2])) ? "" : util.ConvertString(dateformat.format(util.convertStringToDate(String.valueOf(B[2])))));
            otv.setArcode(util.ConvertString(B[3]));
            otv.setTaxinvname(util.ConvertString(B[4]));
            otv.setGross((B[5]) != null ? new BigDecimal(util.ConvertString(B[5])) : new BigDecimal("0.00"));
            otv.setVat((B[6]) != null ? new BigDecimal(util.ConvertString(B[6])) : new BigDecimal("0.00"));
            otv.setAmount((B[7]) != null ? new BigDecimal(util.ConvertString(B[7])) : new BigDecimal("0.00"));
            otv.setDepartment(util.ConvertString(B[8]));
            otv.setDescription(util.ConvertString(B[9]));
            otv.setStatus(util.ConvertString(B[10]));
            outputTaxViewList.add(otv);
        }
        
        this.sessionFactory.close();
        session.close();
        return outputTaxViewList;
    }

    @Override
    public String UpdateOutputTaxStatusCancel(List<OutputTaxView> outputTaxViewList) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            setTransaction(session.beginTransaction());

            for (int i = 0; i < outputTaxViewList.size(); i++) {
                OutputTaxView otv = outputTaxViewList.get(i);
                String taxId = otv.getTaxid();
                String hql = "update TaxInvoice tax set tax.postDate = null , tax.outputTaxStatus = 0 where tax.id = '"+taxId+"'";
                try {
                    Query query = session.createQuery(hql);
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

    @Override
    public List getSaleVatReportList(String month, String year, String department) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList<OutputTaxView>();
        String query = "SELECT * FROM `output_tax_view` where month(taxdate) = '"+month+"' and YEAR(taxdate) = '"+year+"' and status = 'Post'";
        
        if ((department != null) && (!"".equalsIgnoreCase(department))) {
            query += " and department = '" + department + "'" ;
        }
        query += " ORDER BY department desc , taxno , taxdate";
        
        System.out.println("query : "+query);
        
         List<Object[]> QueryList = session.createSQLQuery(query)
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
                .list();
         
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy");
        String monthThai = "";
        String monthMM = "";
        int yearThai = 0;
        
        if("1".equalsIgnoreCase(month)){
            monthThai = "มกราคม";
            monthMM = "01";
        }else if("2".equalsIgnoreCase(month)){
            monthThai = "กุมภาพันธ์";
            monthMM = "02";
        }else if("3".equalsIgnoreCase(month)){
            monthThai = "มีนาคม";
            monthMM = "03";
        }else if("4".equalsIgnoreCase(month)){
            monthThai = "เมษายน";
            monthMM = "04";
        }else if("5".equalsIgnoreCase(month)){
            monthThai = "พฤษภาคม";
            monthMM = "05";
        }else if("6".equalsIgnoreCase(month)){
            monthThai = "มิถุนายน";
            monthMM = "06";
        }else if("7".equalsIgnoreCase(month)){
            monthThai = "กรกฎาคม";
            monthMM = "07";
        }else if("8".equalsIgnoreCase(month)){
            monthThai = "สิงหาคม";
            monthMM = "08";
        }else if("9".equalsIgnoreCase(month)){
            monthThai = "กันยายน";
            monthMM = "09";
        }else if("10".equalsIgnoreCase(month)){
            monthThai = "ตุลาคม";
            monthMM = "10";
        }else if("11".equalsIgnoreCase(month)){
            monthThai = "พฤศจิกายน";
            monthMM = "11";
        }else if("12".equalsIgnoreCase(month)){
            monthThai = "ธันวาคม";
            monthMM = "12";
        }
        
        yearThai = Integer.parseInt(year) + 543 ;
        String orderNo = "";
        String departmentTemp = "";
        for (Object[] B : QueryList){
            OutputTaxView otv = new OutputTaxView();
            
            if("".equalsIgnoreCase(departmentTemp) || !departmentTemp.equalsIgnoreCase(util.ConvertString(B[8]))){
                orderNo = "0001";
            }else{
                int running = Integer.parseInt(orderNo) + 1;
                String temp = String.valueOf(running);
                for (int i = temp.length(); i < 4; i++) {
                    temp = "0" + temp;
                }
                orderNo = temp;
            }
            
            if("Wendy".equalsIgnoreCase(util.ConvertString(B[8]))){
                departmentTemp = "Wendy" ;
            }
            if("Outbound".equalsIgnoreCase(util.ConvertString(B[8]))){
                departmentTemp = "Outbound" ; 
            }
            if("Inbound".equalsIgnoreCase(util.ConvertString(B[8]))){
                departmentTemp = "Inbound" ;
            }
            
            
            otv.setOrder(monthMM+"/"+orderNo);
            otv.setHeaderMonth(monthThai);
            otv.setHeaderYear(String.valueOf(yearThai));
            otv.setHeaderDepartment(department);
            otv.setTaxid(util.ConvertString(B[0]));
            otv.setTaxno(util.ConvertString(B[1]));
            otv.setTaxdate("null".equals(String.valueOf(B[2])) ? "" : util.ConvertString(dateformat.format(util.convertStringToDate(String.valueOf(B[2])))));
            otv.setArcode(util.ConvertString(B[3]));
            otv.setTaxinvname(util.ConvertString(B[4]));
            otv.setGross((B[5]) != null ? new BigDecimal(util.ConvertString(B[5])) : new BigDecimal("0.00"));
            otv.setVat((B[6]) != null ? new BigDecimal(util.ConvertString(B[6])) : new BigDecimal("0.00"));
            otv.setAmount((B[7]) != null ? new BigDecimal(util.ConvertString(B[7])) : new BigDecimal("0.00"));
            otv.setDepartment(util.ConvertString(B[8]));
            otv.setDescription(util.ConvertString(B[9]));
            otv.setStatus(util.ConvertString(B[10]));
            otv.setAgttaxno(util.ConvertString(B[11]));
            otv.setMain(util.ConvertString(B[12]));
            otv.setBranchno(util.ConvertString(B[13]));
            data.add(otv);
        }
        
        this.sessionFactory.close();
        session.close();
        return data;
    }
}
