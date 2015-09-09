/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.view.dao.ARNirvanaDao;
import com.smi.travel.datalayer.view.entity.ARNirvana;
import com.smi.travel.util.UtilityFunction;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Surachai
 */
public class ARNirvanaImpl implements  ARNirvanaDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;
    @Override
    public List<ARNirvana> SearchArNirvanaFromFilter(String invtype, String department, String billtype, String from, String to, String status) {
        System.out.println("Invoice Type : " + invtype + ":");
        System.out.println("Depart ment : " + department + ":");
        System.out.println("Bill Type : " + billtype + ":");
        System.out.println("From : " + from + ":");
        System.out.println("To : " + to + ":");
        System.out.println("Status : " + status + ":");
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        Date thisDate = new Date();
        List data = new ArrayList();
        String query = "";
        int AndQuery = 0;
        
        if("".equals(invtype)  && "".equals(department)  && "".equals(billtype)  && "".equals(from) && "".equals(to) && "".equals(status)){
            query = "FROM ap_nirvana ar " ; 
        }else{
            query = "FROM ap_nirvana ar where" ;
        }
        
        if ( department != null && (!"".equalsIgnoreCase(department)) ) {
            AndQuery = 1;
            query += " department = '" + department + "'";
        }
       
        if (invtype != null && (!"".equalsIgnoreCase(invtype)) ) {
           if(AndQuery == 1){
                query += " and invtype = '" + invtype + "'";
           }else{
               AndQuery = 1;
               query += " invtype = '" + invtype + "'";
           }
        }
        
        if(billtype != null && (!"".equalsIgnoreCase(billtype))){
            if(AndQuery == 1){
                query += " and producttype = '" + billtype + "'";
           }else{
               AndQuery = 1;
               query += " producttype = '" + billtype + "'";
           }
        }
        
        if ((from != null )&&(!"".equalsIgnoreCase(from))) {
            if ((to != null )&&(!"".equalsIgnoreCase(to))) {
                if(AndQuery == 1){
                     query += " and invdate  BETWEEN  '" + from + "' AND '" + to + "' ";
                }else{
                    AndQuery = 1;
                     query += " invdate  BETWEEN  '" + from + "' AND '" + to + "' ";
                }
                
               
            }
        }
        
        if(status != null && (!"".equalsIgnoreCase(status))){
            if(AndQuery == 1){
                query += " and status = '" + status + "'";
           }else{
               AndQuery = 1;
               query += " status = '" + status + "'";
           }
        }
        query += "  ORDER BY st.invDate DESC";
        System.out.println("query : " + query);
        List<Object[]> ARNirvanaList = session.createSQLQuery(query )
                .addScalar("intreference", Hibernate.STRING)
                .addScalar("salesmanid", Hibernate.STRING)
                .addScalar("customerid", Hibernate.STRING)
                .addScalar("customername", Hibernate.STRING)
                .addScalar("divisionid", Hibernate.STRING)
                .addScalar("projectid", Hibernate.STRING)
                .addScalar("transcode", Hibernate.STRING)
                .addScalar("transdate", Hibernate.STRING)
                .addScalar("duedate", Hibernate.STRING)
                .addScalar("currencyid", Hibernate.STRING)
                .addScalar("homerate", Hibernate.STRING)
                .addScalar("foreignrate", Hibernate.STRING)
                .addScalar("salesamt", Hibernate.STRING)
                .addScalar("saleshmamt", Hibernate.STRING)
                .addScalar("vatamt", Hibernate.STRING)
                .addScalar("vathmamt", Hibernate.STRING)
                .addScalar("aramt", Hibernate.STRING)
                .addScalar("arhmamt", Hibernate.STRING)
                .addScalar("vatflag", Hibernate.STRING)
                .addScalar("vatid", Hibernate.STRING)
                .addScalar("whtflag", Hibernate.STRING)
                .addScalar("whtid", Hibernate.STRING)
                .addScalar("basewhtamt", Hibernate.STRING)
                .addScalar("basewhthmamt", Hibernate.STRING)
                .addScalar("whtamt", Hibernate.STRING)
                .addScalar("whthmamt", Hibernate.STRING)
                .addScalar("year", Hibernate.STRING)
                .addScalar("period", Hibernate.STRING)
                .addScalar("note", Hibernate.STRING)
                .addScalar("salesaccount1", Hibernate.STRING)
                .addScalar("salesdivision1", Hibernate.STRING)
                .addScalar("salesproject1", Hibernate.STRING)
                .addScalar("salesamt1", Hibernate.STRING)
                .addScalar("saleshmamt1", Hibernate.STRING)
                .addScalar("salesaccount2", Hibernate.STRING)
                .addScalar("salesdivision2", Hibernate.STRING)
                .addScalar("salesproject2", Hibernate.STRING)
                .addScalar("salesamt2", Hibernate.STRING)
                .addScalar("saleshmamt2", Hibernate.STRING)
                .addScalar("salesaccount3", Hibernate.STRING)
                .addScalar("salesdivision3", Hibernate.STRING)
                .addScalar("salesproject3", Hibernate.STRING)
                .addScalar("salesamt3", Hibernate.STRING)
                .addScalar("saleshmamt3", Hibernate.STRING)
                .addScalar("service", Hibernate.STRING)
                .addScalar("araccount", Hibernate.STRING)
                .addScalar("prefix", Hibernate.STRING)
                .addScalar("documentno", Hibernate.STRING)
                .addScalar("artrans", Hibernate.STRING)
                .addScalar("cust_taxid", Hibernate.STRING)
                .addScalar("cust_branch", Hibernate.STRING)
                .addScalar("company_branch", Hibernate.STRING)
                       
                .list();
        int count = 1;
        for (Object[] B : ARNirvanaList) {
            ARNirvana ar = new ARNirvana();
//            sum.setNo(count);
//            if("N".equals(util.ConvertString(B[0]))){
//                sum.setInvtype("Invoice No Vat");
//            }else if("A".equals(util.ConvertString(B[0]))){
//                sum.setInvtype("Invoice Air Ticket");
//            }else if("T".equals(util.ConvertString(B[0]))){
//                sum.setInvtype("Temporary Invoice");
//            }else if("V".equals(util.ConvertString(B[0]))){
//                sum.setInvtype("Invoice Vat");
//            }else if("".equals(util.ConvertString(B[0]))){
//                sum.setInvtype("All");
//            }else{
//                sum.setInvtype("");
//            }
//            
//            sum.setInvno(util.ConvertString(B[1]));
//            if(!"".equals(util.ConvertString(B[2]))){
//                String dayy[] = util.ConvertString(B[2]).split("-");
//                System.out.println("Date : " + util.ConvertString(B[2]));
//                String date = ""+dayy[2]+"-"+dayy[1]+"-"+dayy[0];
//                try {
//                    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
//                    Date dateBefore = df.parse(date);
//                    sum.setInvdate(new SimpleDateFormat("dd/MM/yyyy", new Locale("us", "us")).format(dateBefore));
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//            }
//            
//            sum.setInvname(util.ConvertString(B[3]));
//            sum.setTermpay(util.ConvertString(B[4]));
//            sum.setDetail(util.ConvertString(B[5]));
//            if(B[6] != null){
//                Double gross = Double.parseDouble(util.ConvertString(B[6]));
//                sum.setGross(gross);
//            }
//            if(B[7] != null){
//                Double vat = Double.parseDouble(util.ConvertString(B[7]));
//                sum.setVat(vat);
//            }
//            if(B[8] != null){
//                Double amount = Double.parseDouble(util.ConvertString(B[8]));
//                sum.setAmount(amount);
//            }
//            
//            if(B[14] != null){
//                Double profit = Double.parseDouble(util.ConvertString(B[14]));
//                sum.setProfit(profit);
//            }
//            sum.setAmountcur(util.ConvertString(B[9]));
//            sum.setStaff(util.ConvertString(B[10]));
//            sum.setStatus(util.ConvertString(B[11]));
//            if(util.ConvertString(B[12]) != null && !"".equals(util.ConvertString(B[12]))){
//                sum.setInvdepartment(util.ConvertString(B[12]));
//            }else if("".equals(util.ConvertString(B[12]))){
//                sum.setInvdepartment("All");
//            }else{
//                sum.setInvdepartment("");
//            }
//            
//            sum.setTo(util.ConvertString(B[13]));
//            sum.setInvfrom(util.convertStringToDate(fromData));
//            sum.setInvto(util.convertStringToDate(toDate));
//            if(department != null && !"".equals(department)){
//                sum.setDepartment(department);     
//            }else if("".equals(department)){
//                sum.setDepartment("All");     
//            }
//                
//            sum.setSystemdate(util.convertDateToString(new Date()));
//            sum.setUsername(util.ConvertString(B[10]));
//
//            data.add(sum);
            count++;
        }
        return data;
    }

    @Override
    public String ExportARFileInterface(List<ARNirvana> APList) {
        return "success";
    }

    @Override
    public String UpdateStatusARInterface(List<ARNirvana> APList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Transaction getTransaction() {
        return transaction;
    }
    
}
