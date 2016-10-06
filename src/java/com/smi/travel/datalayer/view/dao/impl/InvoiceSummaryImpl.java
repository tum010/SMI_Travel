/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.report.model.InvoiceSummary;
import com.smi.travel.datalayer.view.dao.InvoiceSummaryDao;
import com.smi.travel.util.UtilityFunction;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Kanokporn
 */
public class InvoiceSummaryImpl implements InvoiceSummaryDao{
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
//    

    @Override
    public List getInvoiceSummary(String fromData, String toDate, String department, String type,String agent,String statusInvoice,String printBy,String subdepartment) {
        List<InvoiceSummary> listInovicSummary = new LinkedList<InvoiceSummary>();
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        Date thisDate = new Date();
        List data = new ArrayList();
        String query = "";
        int AndQuery = 0;
        if("1".equals(statusInvoice)){
            statusInvoice = "NORMAL";
        }else if("2".equals(statusInvoice)){
            statusInvoice = "VOID";
        }
        if("".equals(department)  && "".equals(type)  && "".equals(fromData)  && "".equals(toDate) && "".equals(agent)){
            query = "SELECT `inv`.`inv_no` AS `invno`, `inv`.`inv_date` AS `invdate`, `inv`.`inv_to` AS `to`, `inv`.`inv_name` AS `invname`, `term`.`name` AS `termpay`, group_concat( DISTINCT `bt`.`name` SEPARATOR ',' ) AS `detail`, round( sum(( CASE WHEN (`invd`.`is_vat` = 0) OR inv.inv_type = 'T' THEN `invd`.`amount_local` ELSE ((`invd`.`amount_local` * 100) / (100 + `invd`.`vat`)) END )), 2 ) AS `gross`, round( sum(( CASE WHEN (`invd`.`is_vat` = 0) OR inv.inv_type = 'T' THEN 0 ELSE ( `invd`.`amount_local` - ((`invd`.`amount_local` * 100) / (100 + `invd`.`vat`))) END )), 2 ) AS `vat`, round(sum(`invd`.`amount`), 2) AS `amount`, round(sum(`invd`.`cost_local`), 2) AS `cost`, round( sum(( `invd`.`amount_local` - ( CASE WHEN inv.inv_type = 'T' THEN 0 ELSE `invd`.`cost_local` END ))), 2 ) AS `profit`, round( sum(`invd`.`amount_local`), 2 ) AS `amountlocal`, `invd`.`cur_amount` AS `cur`, `st`.`username` AS `staff`, ( CASE WHEN (`fi`.`name` = 'NORMAL') THEN 'NORMAL' WHEN (`fi`.`name` = 'VOID') THEN 'VOID' ELSE 'VOID' END ) AS `status`, `inv`.`department` AS `department`, `inv`.`inv_type` AS `type`, `inv`.`sub_department` AS `subdepartment` FROM ((((( `invoice` `inv` JOIN `invoice_detail` `invd` ON (( `invd`.`invoice_id` = `inv`.`id` ))) LEFT JOIN `m_accterm` `term` ON (( `term`.`id` = `inv`.`term_pay` ))) LEFT JOIN `m_billtype` `bt` ON (( `bt`.`id` = `invd`.`item_type_id` ))) LEFT JOIN `staff` `st` ON ((`st`.`id` = `inv`.`staff_id`))) LEFT JOIN `m_finance_itemstatus` `fi` ON ((`fi`.`id` = `inv`.`status`))) " ; 
        }else{
            query = "SELECT `inv`.`inv_no` AS `invno`, `inv`.`inv_date` AS `invdate`, `inv`.`inv_to` AS `to`, `inv`.`inv_name` AS `invname`, `term`.`name` AS `termpay`, group_concat( DISTINCT `bt`.`name` SEPARATOR ',' ) AS `detail`, round( sum(( CASE WHEN (`invd`.`is_vat` = 0) OR inv.inv_type = 'T' THEN `invd`.`amount_local` ELSE ((`invd`.`amount_local` * 100) / (100 + `invd`.`vat`)) END )), 2 ) AS `gross`, round( sum(( CASE WHEN (`invd`.`is_vat` = 0) OR inv.inv_type = 'T' THEN 0 ELSE ( `invd`.`amount_local` - ((`invd`.`amount_local` * 100) / (100 + `invd`.`vat`))) END )), 2 ) AS `vat`, round(sum(`invd`.`amount`), 2) AS `amount`, round(sum(`invd`.`cost_local`), 2) AS `cost`, round( sum(( `invd`.`amount_local` - ( CASE WHEN inv.inv_type = 'T' THEN 0 ELSE `invd`.`cost_local` END ))), 2 ) AS `profit`, round( sum(`invd`.`amount_local`), 2 ) AS `amountlocal`, `invd`.`cur_amount` AS `cur`, `st`.`username` AS `staff`, ( CASE WHEN (`fi`.`name` = 'NORMAL') THEN 'NORMAL' WHEN (`fi`.`name` = 'VOID') THEN 'VOID' ELSE 'VOID' END ) AS `status`, `inv`.`department` AS `department`, `inv`.`inv_type` AS `type`, `inv`.`sub_department` AS `subdepartment` FROM ((((( `invoice` `inv` JOIN `invoice_detail` `invd` ON (( `invd`.`invoice_id` = `inv`.`id` ))) LEFT JOIN `m_accterm` `term` ON (( `term`.`id` = `inv`.`term_pay` ))) LEFT JOIN `m_billtype` `bt` ON (( `bt`.`id` = `invd`.`item_type_id` ))) LEFT JOIN `staff` `st` ON ((`st`.`id` = `inv`.`staff_id`))) LEFT JOIN `m_finance_itemstatus` `fi` ON ((`fi`.`id` = `inv`.`status`))) where" ;
        }
        
        System.out.println("Attribute : " + fromData + " : " + toDate + " : " + department + " : " + type + " : " + agent);
        if ( department != null && (!"".equalsIgnoreCase(department)) ) {
            AndQuery = 1;
            if(department.indexOf(",") == -1){
                query += " `inv`.`department` = '" + department + "'";
            }else{
                String[] departmentTemp = department.split(",");
                query += " `inv`.`department` in ('" + departmentTemp[0] + "','" + departmentTemp[1] + "') ";
            }          
        }
            
        if (subdepartment != null && (!"".equalsIgnoreCase(subdepartment)) ) {
            if(AndQuery == 1){
                 query += " and `inv`.`sub_department` = '" + subdepartment + "'";
            }else{
                AndQuery = 1;
                query += " `inv`.`sub_department` = '" + subdepartment + "'";
            }
        }
       
        if (type != null && (!"".equalsIgnoreCase(type)) ) {
           if(AndQuery == 1){
                query += " and `inv`.`inv_type` = '" + type + "'";
           }else{
               AndQuery = 1;
               query += " `inv`.`inv_type` = '" + type + "'";
           }
        }else{
            if(AndQuery == 1){
                query += " and `inv`.`inv_type`!= 'T'";
           }else{
               AndQuery = 1;
               query += " `inv`.`inv_type` != 'T'";
           }
        }
        
        if(agent != null && (!"".equalsIgnoreCase(agent))){
            if(AndQuery == 1){
                query += " and `inv`.`inv_to` = '" + agent + "'";
           }else{
               AndQuery = 1;
               query += " `inv`.`inv_to` = '" + agent + "'";
           }
        }
        
        if(statusInvoice != null && (!"".equalsIgnoreCase(statusInvoice))){
            if(AndQuery == 1){
                query += " and `fi`.`name` = '" + statusInvoice + "'";
           }else{
               AndQuery = 1;
               query += " `fi`.`name`  = '" + statusInvoice + "'";
           }
        }
        
        if ((fromData != null )&&(!"".equalsIgnoreCase(fromData))) {
            if ((toDate != null )&&(!"".equalsIgnoreCase(toDate))) {
                if(AndQuery == 1){
                     query += " and `inv`.`inv_date`  BETWEEN  '" + (fromData) + "' AND '" + (toDate) + "' ";
                }else{
                    AndQuery = 1;
                     query += " `inv`.`inv_date` BETWEEN  '" + (fromData) + "' AND '" + (toDate) + "' ";
                }
                
               
            }
        }
        
        query += " GROUP BY `inv`.`id` ORDER BY `inv`.`inv_type`, `inv`.`inv_no` " ;
      //  query += "  ORDER BY st.invdate DESC";
        System.out.println("Query : "+query);
        int no = 0;
        List<Object[]> InvoiceSummaryList = session.createSQLQuery(query )
                .addScalar("type", Hibernate.STRING)
                .addScalar("invno", Hibernate.STRING)
                .addScalar("invdate", Hibernate.DATE)
                .addScalar("invname", Hibernate.STRING)
                .addScalar("termpay", Hibernate.STRING)
                .addScalar("detail", Hibernate.STRING)
                .addScalar("gross", Hibernate.DOUBLE)
                .addScalar("vat", Hibernate.DOUBLE)
                .addScalar("amount", Hibernate.DOUBLE)
                .addScalar("cur", Hibernate.STRING)
                .addScalar("staff", Hibernate.STRING)
                .addScalar("status", Hibernate.STRING)
                .addScalar("department", Hibernate.STRING)
                .addScalar("to", Hibernate.STRING)
                .addScalar("profit", Hibernate.DOUBLE)
                .addScalar("amountlocal", Hibernate.DOUBLE)
                .addScalar("cost", Hibernate.DOUBLE)
                .list();
        int count = 1;
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        for (Object[] B : InvoiceSummaryList) {
            InvoiceSummary sum = new InvoiceSummary();
            sum.setNo(count);
            sum.setInvtype(util.ConvertString(B[0]));
            
            sum.setInvno(util.ConvertString(B[1]));
            if(!"".equals(util.ConvertString(B[2]))){
                String dayy[] = util.ConvertString(B[2]).split("-");
//                System.out.println("Date : " + util.ConvertString(B[2]));
                String date = ""+dayy[2]+"-"+dayy[1]+"-"+dayy[0];
                try {
                    Date dateBefore = df.parse(date);
                    sum.setInvdate(new SimpleDateFormat("dd/MM/yyyy", new Locale("us", "us")).format(dateBefore));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            
            sum.setInvname(util.ConvertString(B[3]));
            sum.setTermpay(util.ConvertString(B[4]));
            sum.setDetail(util.ConvertString(B[5]));
            if(B[6] != null){
                Double gross = Double.parseDouble(util.ConvertString(B[6]));
                sum.setGross(gross);
            }
            if(B[7] != null){
                Double vat = Double.parseDouble(util.ConvertString(B[7]));
                sum.setVat(vat);
            }
            if(B[8] != null){
                Double amount = Double.parseDouble(util.ConvertString(B[8]));
                sum.setAmount(amount);
            }           
            if(B[14] != null){
                Double profit = Double.parseDouble(util.ConvertString(B[14]));
                sum.setProfit(profit);
            }
            if(B[15] != null){
                Double amountlocal = Double.parseDouble(util.ConvertString(B[15]));
                sum.setAmountlocal(amountlocal);
            }
            sum.setAmountcur(util.ConvertString(B[9]));
            sum.setStaff(util.ConvertString(B[10]));
            sum.setStatus(util.ConvertString(B[11]));
            if(util.ConvertString(B[12]) != null && !"".equals(util.ConvertString(B[12]))){
                sum.setInvdepartment(util.ConvertString(B[12]));
            }else if("".equals(util.ConvertString(B[12]))){
                sum.setInvdepartment("All");
            }else{
                sum.setInvdepartment("");
            }
            
            sum.setTo(util.ConvertString(B[13]));

            if(!"".equals(fromData) && fromData != null){
                String dayy[] = fromData.split("-");
//                System.out.println("Date From : " + fromData);
                String date = ""+dayy[2]+"-"+dayy[1]+"-"+dayy[0];
                try {
                    Date dateBefore = df.parse(date);
                    sum.setInvfrom(new SimpleDateFormat("dd/MM/yyyy", new Locale("us", "us")).format(dateBefore));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if(!"".equals(toDate) && toDate != null){
                String dayy[] = toDate.split("-");
//                System.out.println("Date From : " + toDate);
                String date = ""+dayy[2]+"-"+dayy[1]+"-"+dayy[0];
                try {

                    Date dateBefore = df.parse(date);
                    sum.setInvto(new SimpleDateFormat("dd/MM/yyyy", new Locale("us", "us")).format(dateBefore));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if(department != null && !"".equals(department)){
                sum.setDepartment(department);     
            }else if("".equals(department)){
                sum.setDepartment("All");     
            }
                
            sum.setSystemdate(util.convertDateToString(new Date()));
//            sum.setUsername(util.ConvertString(B[10]));
            sum.setUsername(printBy);
            if("N".equals(type)){
                sum.setHeadertype("Invoice No Vat");
            }else if("A".equals(type)){
                sum.setHeadertype("Invoice Air Ticket");
            }else if("T".equals(type)){
                sum.setHeadertype("Temporary Invoice");
            }else if("V".equals(type)){
                sum.setHeadertype("Invoice Vat");
            }else if("".equals(type)){
                sum.setHeadertype("All");
            }
//            sum.setHeadertype(type);
            data.add(sum);
            count++;
        }
        
        session.close();
        this.sessionFactory.close();
        return data;
    }
    
}
