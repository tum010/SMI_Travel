/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.entity.MRunningCode;
import com.smi.travel.datalayer.view.dao.ARNirvanaDao;
import com.smi.travel.datalayer.view.entity.APNirvana;
import com.smi.travel.datalayer.view.entity.ARNirvana;
import com.smi.travel.model.nirvana.SsDataexch;
import com.smi.travel.model.nirvana.SsDataexchTr;
import com.smi.travel.util.UtilityFunction;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
//import org.joda.time.format.DateTimeFormat;
//import java.time.format.DateTimeFormatter;

/**
 *
 * @author Surachai
 */
public class ARNirvanaImpl implements  ARNirvanaDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;
    private static final String[] FILE_HEADER
            = {"intreference", "salesmanid", "customerid", "customername", "divisionid", 
                "projectid", "transcode", "transdate", "duedate", "currencyid", 
                "homerate", "foreignrate", "salesamt", "saleshmamt", "vatamt", 
                "vathmamt", "aramt", "arhmamt", "vatflag", "vatid", 
                "whtflag", "whtid", "basewhtamt", "basewhthmamt", "whtamt", 
                "whthmamt", "year", "period", "note", "salesaccount1", 
                "salesdivision1", "salesproject1", "salesamt1", "saleshmamt1", "salesaccount2", 
                "salesdivision2", "salesproject2", "salesamt2", "saleshmamt2", "salesaccount3", 
                "salesdivision3", "salesproject3", "salesamt3", "saleshmamt3", "service", 
                "araccount", "prefix", "documentno", "artrans", "cust_taxid", 
                "cust_branch", "company_branch"};
    
    @Override
    public List<ARNirvana> SearchArNirvanaFromFilter(String invtype, String department, String billtype, String from, String to, String status,String accno) {
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
        
        if("undefined".equals(invtype)){
            invtype = null;
        }
        if("undefined".equals(department)){
            department = null;
        }
        if("undefined".equals(billtype)){
            billtype = null;
        }
        if("undefined".equals(from)){
            from = null;
        }
        if("undefined".equals(to)){
            to = null;
        }
        if("undefined".equals(status)){
            status = null;
        }
        
        if(invtype == null  && department == null  &&  billtype == null  && from == null && to == null && status == null){
            query = "SELECT * FROM ar_nirvana ar " ; 
        }else{
            query = "SELECT * FROM ar_nirvana ar  where " ;
        }
        
        if ( department != null && (!"".equalsIgnoreCase(department)) ) {
            AndQuery = 1;
            query += " ar.department = '" + department + "'";
        }
       
        if (invtype != null && (!"".equalsIgnoreCase(invtype)) ) {
           if(AndQuery == 1){
                query += " and ar.invtype = '" + invtype + "'";
           }else{
               AndQuery = 1;
               query += " ar.invtype = '" + invtype + "'";
           }
        }
        
        if(billtype != null && (!"".equalsIgnoreCase(billtype))){
            if(AndQuery == 1){
                query += " and ar.producttype = '" + billtype + "'";
           }else{
               AndQuery = 1;
               query += " ar.producttype = '" + billtype + "'";
           }
        }
        
        if ((from != null )&&(!"".equalsIgnoreCase(from))) {
            if ((to != null )&&(!"".equalsIgnoreCase(to))) {
                if(AndQuery == 1){
                     query += " and ar.invdate  BETWEEN  '" + from + "' AND '" + to + "' ";
                }else{
                    AndQuery = 1;
                     query += " ar.invdate  BETWEEN  '" + from + "' AND '" + to + "' ";
                }
                
               
            }
        }
        
        if(status != null && (!"".equalsIgnoreCase(status))){
            if(AndQuery == 1){
                query += " and ar.itf_status = '" + status + "'";
           }else{
               AndQuery = 1;
               query += " ar.itf_status = '" + status + "'";
           }
        }
        
        if(accno != null && (!"".equalsIgnoreCase(accno))){
            if(AndQuery == 1){
                query += " and ar.accno = '" + accno + "'";
           }else{
               AndQuery = 1;
               query += " ar.accno = '" + accno + "'";
           }
        }
        
        query += " ORDER BY ar.intreference desc , ar.invdate  DESC";
        
        System.out.println("query : " + query);
        List<Object[]> ARNirvanaList = session.createSQLQuery(query )
                .addScalar("invtype", Hibernate.STRING)
                .addScalar("department", Hibernate.STRING)
                .addScalar("producttype", Hibernate.STRING)
                .addScalar("invdate", Hibernate.DATE)
                .addScalar("itf_status", Hibernate.STRING)
                .addScalar("intreference", Hibernate.STRING)
                .addScalar("salesmanid", Hibernate.STRING)
                .addScalar("customerid", Hibernate.STRING)
                .addScalar("customername", Hibernate.STRING)
                .addScalar("divisionid", Hibernate.STRING)
                .addScalar("projectid", Hibernate.STRING)
                .addScalar("transcode", Hibernate.STRING)
                .addScalar("transdate", Hibernate.DATE)
                .addScalar("duedate", Hibernate.DATE)
                .addScalar("currencyid", Hibernate.STRING)
                .addScalar("homerate", Hibernate.BIG_DECIMAL)
                .addScalar("foreignrate", Hibernate.BIG_DECIMAL)
                .addScalar("salesamt", Hibernate.BIG_DECIMAL)
                .addScalar("saleshmamt", Hibernate.BIG_DECIMAL)
                .addScalar("vatamt", Hibernate.BIG_DECIMAL)
                .addScalar("vathmamt", Hibernate.BIG_DECIMAL)
                .addScalar("aramt", Hibernate.BIG_DECIMAL)
                .addScalar("arhmamt", Hibernate.BIG_DECIMAL)
                .addScalar("vatflag", Hibernate.STRING)
                .addScalar("vatid", Hibernate.STRING)
                .addScalar("whtflag", Hibernate.STRING)
                .addScalar("whtid", Hibernate.STRING)
                .addScalar("basewhtamt", Hibernate.BIG_DECIMAL)
                .addScalar("basewhthmamt", Hibernate.BIG_DECIMAL)
                .addScalar("whtamt", Hibernate.BIG_DECIMAL)
                .addScalar("whthmamt", Hibernate.BIG_DECIMAL)
                .addScalar("period", Hibernate.INTEGER)
                .addScalar("period", Hibernate.INTEGER)
                .addScalar("note", Hibernate.STRING)
                .addScalar("salesaccount1", Hibernate.STRING)
                .addScalar("salesdivision1", Hibernate.STRING)
                .addScalar("salesproject1", Hibernate.STRING)
                .addScalar("salesamt1", Hibernate.BIG_DECIMAL)
                .addScalar("saleshmamt1", Hibernate.BIG_DECIMAL)
                .addScalar("salesaccount2", Hibernate.STRING)
                .addScalar("salesdivision2", Hibernate.STRING)
                .addScalar("salesproject2", Hibernate.STRING)
                .addScalar("salesamt2", Hibernate.BIG_DECIMAL)
                .addScalar("saleshmamt2", Hibernate.BIG_DECIMAL)
                .addScalar("salesaccount3", Hibernate.STRING)
                .addScalar("salesdivision3", Hibernate.STRING)
                .addScalar("salesproject3", Hibernate.STRING)
                .addScalar("salesamt3", Hibernate.BIG_DECIMAL)
                .addScalar("saleshmamt3", Hibernate.BIG_DECIMAL)
                .addScalar("service", Hibernate.STRING)
                .addScalar("araccount", Hibernate.STRING)
                .addScalar("prefix", Hibernate.STRING)
                .addScalar("documentno", Hibernate.STRING)
                .addScalar("artrans", Hibernate.STRING)
                .addScalar("cust_taxid", Hibernate.STRING)
                .addScalar("cust_branch", Hibernate.INTEGER)
                .addScalar("company_branch", Hibernate.INTEGER)
                .addScalar("inv_id", Hibernate.INTEGER)
                .addScalar("receive_detail_id", Hibernate.INTEGER)
                .addScalar("rowid", Hibernate.STRING)
                .list();
        for (Object[] B : ARNirvanaList) {
            ARNirvana ar = new ARNirvana();
            //header
            if(from != null && !"".equals(from)){
                String fromm = ""+ from + " To " + to;
                ar.setPrintofdatePage(fromm);
            }else{
                ar.setPrintofdatePage("");
            }
            
            if(department != null && !"".equals(department)){
               ar.setDepartmentPage(department);
            }else{
                ar.setDepartmentPage("");
            }         
            
            ar.setInvtype(util.ConvertString(B[0]));
            ar.setDepartment(util.ConvertString(B[1]));
            ar.setProducttype(util.ConvertString(B[2]));
            
            ar.setInvdate(util.convertStringToDate(util.ConvertString(B[3])));
            ar.setStatus(util.ConvertString(B[4]));
            ar.setIntreference(util.ConvertString(B[5]));
            ar.setSalesmanid(util.ConvertString(B[6]));
            ar.setCustomerid(util.ConvertString(B[7]));
            ar.setCustomername(util.ConvertString(B[8]));
            ar.setDivisionid(util.ConvertString(B[9]));
            ar.setProjectid(util.ConvertString(B[10]));
            ar.setTranscode(util.ConvertString(B[11]));
            ar.setTransdate(util.convertStringToDate(util.ConvertString(B[12])));
            ar.setDuedate(util.convertStringToDate(util.ConvertString(B[13])));
            ar.setCurrencyid(util.ConvertString(B[14]));
            ar.setHomerate((BigDecimal) B[15]);
            ar.setForeignrate((BigDecimal) B[16]);
            ar.setSalesamt((BigDecimal) B[17]);
            ar.setSaleshmamt((BigDecimal) B[18]);
            ar.setVatamt((BigDecimal) B[19]);
            ar.setVathmamt((BigDecimal) B[20]);
            ar.setAramt((BigDecimal) B[21]);
            ar.setArhmamt((BigDecimal) B[22]);
            ar.setVatflag(util.ConvertString(B[23]));
            ar.setVatid(util.ConvertString(B[24]));
            ar.setWhtflag(util.ConvertString(B[25]));
            ar.setWhtid(util.ConvertString(B[26]));
            ar.setBasewhtamt((BigDecimal) B[27]);
            ar.setBasewhthmamt((BigDecimal) B[28]);
            ar.setWhtamt((BigDecimal) B[29]);
            ar.setWhthmamt((BigDecimal) B[30]);
            ar.setYear((Integer) B[31]);
            ar.setPeriod((Integer) B[32]);
            ar.setNote(util.ConvertString(B[33]));
            ar.setSalesaccount1(util.ConvertString(B[34]));
            ar.setSalesdivision1(util.ConvertString(B[35]));
            ar.setSalesproject1(util.ConvertString(B[36]));
            ar.setSalesamt1((BigDecimal) B[37]);
            ar.setSaleshmamt((BigDecimal) B[38]);
            ar.setSalesaccount2(util.ConvertString(B[39]));
            ar.setSalesdivision2(util.ConvertString(B[40]));
            ar.setSalesproject2(util.ConvertString(B[41]));
            ar.setSalesamt2((BigDecimal) B[42]);
            ar.setSaleshmamt2((BigDecimal) B[43]);
            ar.setSalesaccount3(util.ConvertString(B[44]));
            ar.setSalesdivision3(util.ConvertString(B[45]));
            ar.setSalesproject3(util.ConvertString(B[46]));
            ar.setSalesamt3((BigDecimal) B[47]);
            ar.setSaleshmamt3((BigDecimal) B[48]);
            ar.setService(util.ConvertString(B[49]));
            ar.setAraccount(util.ConvertString(B[50]));
            ar.setPrefix(util.ConvertString(B[51]));
            ar.setDocumentno(util.ConvertString(B[52]));
            ar.setArtrans(util.ConvertString(B[53]));
            ar.setCust_taxid(util.ConvertString(B[54]));
            ar.setCust_branch((Integer) B[55]);
            ar.setCompany_branch((Integer) B[56]);
            ar.setInvid((Integer) B[57]);
            ar.setId((Integer) B[58]);
            ar.setRowid(util.ConvertString(B[59]));
            data.add(ar);
        }
        session.close();
        this.sessionFactory.close();
        return data;
    }

    @Override
    public String ExportARFileInterface(List<ARNirvana> ARList,String pathfile) {
        
        String status = "";
        List<ARNirvana> arDataList = this.SearchArNirvanaFromPaymentDetailId(ARList);
        SimpleDateFormat folderName = new SimpleDateFormat("yyMMdd");
        SimpleDateFormat fileName = new SimpleDateFormat("HHmmss");
//        File folder = new File(pathfile + folderName.format(Calendar.getInstance().getTime()));
//        if (!folder.exists() && !folder.isDirectory()) {
//            folder.mkdirs();
//        }
//        String fullFileName = folder.getAbsolutePath() + "\\AR" + fileName.format(Calendar.getInstance().getTime());
        int accno = 1 ;
            List<ARNirvana> arNirvanaList = new ArrayList<ARNirvana>();
            String fullFileName = "";
            for (int i=0 ; i< arDataList.size() ; i++) {
                ARNirvana ar = arDataList.get(i);
                File folder = new File(pathfile+"\\accno"+accno+"\\ar\\" + folderName.format(Calendar.getInstance().getTime()));
                if(accno == Integer.parseInt(ar.getAccno())){
                    arNirvanaList.add(ar);
                    accno = Integer.parseInt(ar.getAccno());
                }else{
                    folder = new File(pathfile+"\\accno"+accno+"\\ar\\" + folderName.format(Calendar.getInstance().getTime()));
                    if (!folder.exists() && !folder.isDirectory()) {
                        folder.mkdirs();
                    }
                    fullFileName = folder.getAbsolutePath() +"\\AR" + fileName.format(Calendar.getInstance().getTime());
                    status = genReport(arNirvanaList,fullFileName,ARList);
                    System.out.println(" status " + status);
                    
                    arNirvanaList = new ArrayList<ARNirvana>();
                    arNirvanaList.add(ar);
                    accno = Integer.parseInt(ar.getAccno());
                }
                if(i ==  (arDataList.size() - 1)){
                    folder = new File(pathfile+"\\accno"+accno+"\\ar\\" + folderName.format(Calendar.getInstance().getTime()));
                    if (!folder.exists() && !folder.isDirectory()) {
                        folder.mkdirs();
                    }
                    fullFileName = folder.getAbsolutePath() +"\\AR" + fileName.format(Calendar.getInstance().getTime());
                    status = genReport(arNirvanaList,fullFileName,ARList);
                    System.out.println(" status " + status);
                }
            }
        
        return status;
    }

    @Override
    public String UpdateStatusARInterface(List<ARNirvana> APList) {
        UtilityFunction utilty =  new UtilityFunction();
        String isUpdate ="";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
           
            for (int i = 0; i < APList.size(); i++) {
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String strDate = sdf.format(cal.getTime());
                Date date = new Date();
                
                String hql = "";
                String id = "";
                if(APList.get(i).getRowid().indexOf("TAX") != -1){
                    id = APList.get(i).getRowid().substring(3);
                    hql = "update TaxInvoiceDetail taxd set taxd.isExport = 1 , taxd.exportDate = :date where taxd.id = :invDetailId";
                
                }else{
                    id = APList.get(i).getRowid().substring(1);
                    hql = "update Invoice inv set inv.isExport = 1 , inv.exportDate = :date where inv.id = :invDetailId";
                
                }
                
                Query query = session.createQuery(hql);
                query.setParameter("invDetailId", String.valueOf(id));
                query.setParameter("date", date);
                int result = query.executeUpdate();
                System.out.println("Query Update : " + result + ":" + query);
                
            }

            transaction.commit();
            session.close();
            this.sessionFactory.close();
            isUpdate = "updatesuccess";
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            isUpdate = "updatefail";
        }
        return isUpdate;
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

    private List<ARNirvana> SearchArNirvanaFromPaymentDetailId(List<ARNirvana> ARList) {
//           Session session = this.getSessionFactory().openSession();
//        StringBuffer query = new StringBuffer(" SELECT '' as rowid, ar.* FROM `ar_nirvana` ar WHERE  receive_detail_id in (");
//        for (int i = 0; i < ARList.size(); i++) {
//            query.append(i == 0 ? "" : ",");
//            query.append(ARList.get(i).getReceive_detail_id());
//        }
//        query.append(")");
//
//        SQLQuery sQLQuery = session.createSQLQuery(query.toString()).addEntity(ARNirvana.class);
//        List result = sQLQuery.list();
//
//        this.sessionFactory.close();
//        session.close();
//        return result;
        UtilityFunction util = new UtilityFunction(); 
        Session session = this.getSessionFactory().openSession();
        String query = "from ARNirvana ar where ar.rowid in (";
        for (int i = 0; i < ARList.size(); i++) {
            query += (i == 0 ? "" : ",");
            query += ("'"+ARList.get(i).getRowid()+"'");
        } 
        query += ") order by accno , intreference asc " ;
        System.out.println(" query :: " + query);
        Query HqlQuery = session.createQuery(query);
        List<ARNirvana> result = HqlQuery.list();
        if(result != null){
            for (int i = 0; i < result.size() ; i++) {
                String rowid = result.get(i).getRowid();
                String type = rowid.substring(0, 1);
                String row = rowid.substring(1);
                System.out.println("Type :" + type +" Row : " + row);
                String queryDetail = "SELECT * FROM `ar_nirvana_sale_detail` where id='"+row+"'";
                List<Object[]> detail = session.createSQLQuery(queryDetail)
                 .addScalar("salesaccount", Hibernate.STRING)
                 .addScalar("salesdivision", Hibernate.STRING)
                 .addScalar("salesproject", Hibernate.STRING)
                 .addScalar("salesamt", Hibernate.BIG_DECIMAL)
                 .addScalar("saleshmamt", Hibernate.BIG_DECIMAL)
                 .list();
                int count = 0;
                for (Object[] B : detail) {
                    count++;
                    if(count == 1){
                        result.get(i).setSalesaccount1(util.ConvertString(B[0]));
                        result.get(i).setSalesdivision1(util.ConvertString(B[1]));
                        result.get(i).setSalesproject1(util.ConvertString(B[2]));
                        result.get(i).setSalesamt1((BigDecimal) B[3]);
                        result.get(i).setSaleshmamt1((BigDecimal) B[4]);
                    }else if(count == 2){
                        result.get(i).setSalesaccount2(util.ConvertString(B[0]));
                        result.get(i).setSalesdivision2(util.ConvertString(B[1]));
                        result.get(i).setSalesproject2(util.ConvertString(B[2]));
                        result.get(i).setSalesamt2((BigDecimal) B[3]);
                        result.get(i).setSaleshmamt2((BigDecimal) B[4]);
                    }else if(count == 3){
                        result.get(i).setSalesaccount3(util.ConvertString(B[0]));
                        result.get(i).setSalesdivision3(util.ConvertString(B[1]));
                        result.get(i).setSalesproject3(util.ConvertString(B[2]));
                        result.get(i).setSalesamt3((BigDecimal) B[3]);
                        result.get(i).setSaleshmamt3((BigDecimal) B[4]);
                    }                   
                }
            }
        }
       
        this.sessionFactory.close();
        session.close();
        return result;
    }
    
    
    private String genReport(List<ARNirvana> arDataList , String fullFileName , List<ARNirvana> ARList){
        UtilityFunction util = new UtilityFunction();
        String status ="";
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("dd/MM/yyyy");
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
            int rownum = 0;
            for (ARNirvana ar : arDataList) {
                HSSFRow dataRow = sheet.createRow(rownum++);
                int cellnum = 0;
                HSSFCell cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ar.getIntreference());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ar.getSalesmanid());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ar.getCustomerid());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ar.getCustomername());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ar.getDivisionid());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ar.getProjectid());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ar.getTranscode());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ar.getTransdate() == null ? "" : util.ConvertString(df.format(util.convertStringToDate(String.valueOf(ar.getTransdate())))));
                cell = dataRow.createCell(cellnum++);
                if(ar.getDuedate() == null){
                    cell.setCellValue("");
                }else{
                    cell.setCellValue(util.ConvertString(df.format(util.convertStringToDate(String.valueOf(ar.getDuedate())))));
                }
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ar.getCurrencyid());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ar.getHomerate()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ar.getForeignrate()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ar.getSalesamt()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ar.getSaleshmamt()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ar.getVatamt()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ar.getVathmamt()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ar.getAramt()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ar.getArhmamt()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ar.getVatflag());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ar.getVatid());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ar.getWhtflag());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ar.getWhtid());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ar.getBasewhtamt()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ar.getBasewhthmamt()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ar.getWhtamt()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ar.getWhthmamt()));
                cell = dataRow.createCell(cellnum++);
                if(ar.getYear() == null){
                    cell.setCellValue("");
                }else{
                    cell.setCellValue(ar.getYear());
                }
                cell = dataRow.createCell(cellnum++);
                if(ar.getPeriod() == null){
                    cell.setCellValue("");
                }else{
                    cell.setCellValue(ar.getPeriod());
                }
                
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ar.getNote());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ar.getSalesaccount1());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ar.getSalesdivision1());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ar.getSalesproject1());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ar.getSalesamt1()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ar.getSaleshmamt1()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ar.getSalesaccount2());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ar.getSalesdivision2());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ar.getSalesproject2());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ar.getSalesamt2()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ar.getSaleshmamt2()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ar.getSalesaccount3());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ar.getSalesdivision3());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ar.getSalesproject3());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ar.getSalesamt3()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ar.getSaleshmamt3()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ar.getService());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ar.getAraccount());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ar.getPrefix());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ar.getDocumentno());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ar.getArtrans());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ar.getCust_taxid());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ar.getCust_branch() == null ? "":ar.getCust_branch().toString());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ar.getCompany_branch());
//                for(int j =0;j<100;j++){
//                    sheet.autoSizeColumn(j);
//                }
            }
            FileOutputStream out = new FileOutputStream(new File(fullFileName + ".xls"));
            workbook.write(out);
            out.close();
            status = "success";
        } catch (Exception e) {
            e.printStackTrace();
            for (ARNirvana ar : ARList) {
                if(!"".equals(status)){
                    status += ", ";
                }
                status += ar.getReceive_detail_id();
            }
        }
        return status;
    }

    @Override
    public String MappingARNirvana(List<ARNirvana> ARList) {
        String result = "fail";
        SimpleDateFormat sf = new SimpleDateFormat("DD/MM/YYYY");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd.HHmmss");
        UtilityFunction util = new UtilityFunction();
        List<ARNirvana> arDataList = this.SearchArNirvanaFromPaymentDetailId(ARList);
        SsDataexch ssDataexch = new SsDataexch();
        for(int i=0; i<arDataList.size(); i++){
            ARNirvana arNirvana = arDataList.get(i);
            SsDataexch ssDataexchTemp = new SsDataexch();
            ssDataexchTemp.setDataCd("160010");
            String apNirvanaNo = gennarateARNirvanaNo("AR");
            ssDataexchTemp.setDataNo(apNirvanaNo);
            ssDataexchTemp.setEntSysCd("SMI");
            Date date = new Date();
            ssDataexchTemp.setEntSysDate(sdf.format(date));
            ssDataexchTemp.setEntDataNo(apNirvanaNo);
            ssDataexchTemp.setEntComment("");
            ssDataexchTemp.setRcvSysCd("NIRVANA");
            ssDataexchTemp.setRcvSysCd("1");
            ssDataexchTemp.setRcvSysDate("00000000.000000");
            ssDataexchTemp.setRcvComment("");
            ssDataexchTemp.setTraNesCd("1");
            ssDataexchTemp.setTraStaCd("1");
            ssDataexchTemp.setTraSysDate("00000000.000000");
            
            String dataArea = "";
            String companyId = "";
            dataArea += util.generateDataAreaNirvana(companyId,21);
            
//            String documentPrefix = (arNirvana.getPrefix() != null && !"".equalsIgnoreCase(arNirvana.getPrefix()) ? arNirvana.getPrefix() : "");
//            dataArea += generateDataAreaARNirvana(documentPrefix.length(),6);
            
//            String documentNo = (arNirvana.getDocumentno() != null && !"".equalsIgnoreCase(arNirvana.getDocumentno()) ? arNirvana.getDocumentno() : "");
            
            String intreference = (arNirvana.getIntreference() != null && !"".equalsIgnoreCase(arNirvana.getIntreference()) ? arNirvana.getIntreference() : "");
            dataArea += util.generateDataAreaNirvana(intreference,21);
            
            String customerId = (arNirvana.getCustomerid() != null && !"".equalsIgnoreCase(arNirvana.getCustomerid()) ? arNirvana.getCustomerid() : "");
            dataArea += util.generateDataAreaNirvana(customerId,21);
            
            String customerName = (arNirvana.getCustomername() != null && !"".equalsIgnoreCase(arNirvana.getCustomername()) ? arNirvana.getCustomername() : "");
            dataArea += util.generateDataAreaNirvana(customerName,100);
            
            String chargeCustomerId = "";
            dataArea += util.generateDataAreaNirvana(intreference,21);
            
            String divisionId = (arNirvana.getDivisionid() != null && !"".equalsIgnoreCase(arNirvana.getDivisionid()) ? arNirvana.getDivisionid() : "");
            dataArea += util.generateDataAreaNirvana(intreference,21);
            
            String projectId = "0";
            dataArea += util.generateDataAreaNirvana(projectId,21);
            
            String transCode = (arNirvana.getTranscode() != null && !"".equalsIgnoreCase(arNirvana.getTranscode()) ? arNirvana.getTranscode() : "");
            dataArea += util.generateDataAreaNirvana(transCode,2);
            
            String transDate = (arNirvana.getTransdate() != null ? sf.format(arNirvana.getTransdate()) : "");
            
            String dueDate = (arNirvana.getDuedate()!= null ? sf.format(arNirvana.getDuedate()) : "");
            
            String salesmanId = "00";
            dataArea += util.generateDataAreaNirvana(intreference,6);
            
            String vatFlag = (arNirvana.getVatflag()!= null && !"".equalsIgnoreCase(arNirvana.getVatflag()) ? arNirvana.getVatflag() : "");
            dataArea += util.generateDataAreaNirvana(vatFlag,1);
            
            String vatId = (arNirvana.getVatid()!= null && !"".equalsIgnoreCase(arNirvana.getVatid()) ? arNirvana.getVatid() : "");
            dataArea += util.generateDataAreaNirvana(vatId,6);
            
            String salesAmt = (arNirvana.getSalesamt()!= null ? String.valueOf(arNirvana.getSalesamt()) : "");
            dataArea += util.generateDataAreaNirvana(salesAmt,23);
            
            String salesHmAmt = (arNirvana.getSaleshmamt()!= null ? String.valueOf(arNirvana.getSaleshmamt()) : "");
            dataArea += util.generateDataAreaNirvana(salesHmAmt,23);
            
            String totBaseVatAmt = "";
            dataArea += util.generateDataAreaNirvana(totBaseVatAmt,23);
            
            String totBaseVatHmAmt = "";
            dataArea += util.generateDataAreaNirvana(totBaseVatHmAmt,23);
            
            String totVatAmt = (arNirvana.getVatamt()!= null ? String.valueOf(arNirvana.getVatamt()) : "");
            dataArea += util.generateDataAreaNirvana(totVatAmt,23);
            
            String totVatHmAmt = (arNirvana.getVathmamt()!= null ? String.valueOf(arNirvana.getVathmamt()) : "");
            dataArea += util.generateDataAreaNirvana(totVatHmAmt,23);
            
            BigDecimal salesAmtTemp = (arNirvana.getSalesamt() != null ? arNirvana.getSalesamt() : new BigDecimal(BigInteger.ZERO));
            BigDecimal vatAmtTemp = (arNirvana.getVatamt()!= null ? arNirvana.getVatamt(): new BigDecimal(BigInteger.ZERO));
            String arAmt = String.valueOf(salesAmtTemp.add(vatAmtTemp));
            dataArea += util.generateDataAreaNirvana(arAmt,23);
            
            BigDecimal salesHAmtTemp = (arNirvana.getSaleshmamt()!= null ? arNirvana.getSaleshmamt(): new BigDecimal(BigInteger.ZERO));
            BigDecimal vatHAmtTemp = (arNirvana.getVathmamt()!= null ? arNirvana.getVathmamt(): new BigDecimal(BigInteger.ZERO));
            String arHmAmt = String.valueOf(salesHAmtTemp.add(vatHAmtTemp));
            dataArea += util.generateDataAreaNirvana(arHmAmt,23);
            
            String costAmt = "0.00";
            dataArea += util.generateDataAreaNirvana(costAmt,23);
            
            String currencyId = (arNirvana.getCurrencyid()!= null && !"".equalsIgnoreCase(arNirvana.getCurrencyid()) ? arNirvana.getCurrencyid(): "");
            dataArea += util.generateDataAreaNirvana(currencyId,6);
            
            String homeRate = (arNirvana.getHomerate() != null ? String.valueOf(arNirvana.getHomerate()) : "");
            dataArea += util.generateDataAreaNirvana(homeRate,25);
            
            String foreignrate = (arNirvana.getForeignrate()!= null ? String.valueOf(arNirvana.getForeignrate()) : "");
            dataArea += util.generateDataAreaNirvana(foreignrate,25);
            
            String cancelFlag = "Y";
            dataArea += util.generateDataAreaNirvana(cancelFlag,1);
            
            String note = (arNirvana.getNote()!= null && !"".equalsIgnoreCase(arNirvana.getNote()) ? arNirvana.getNote(): "");
            dataArea += util.generateDataAreaNirvana(note,61);
            
            String year = (arNirvana.getYear()!= null ? String.valueOf(arNirvana.getYear()) : "");
            dataArea += util.generateDataAreaNirvana(year,4);
            
            String period = (arNirvana.getPeriod()!= null ? String.valueOf(arNirvana.getPeriod()) : "");
            dataArea += util.generateDataAreaNirvana(period,2);
            
            String prepareBillingDate = "";
            dataArea += util.generateDataAreaNirvana(prepareBillingDate,10);
            
            String exReference = "";
            dataArea += util.generateDataAreaNirvana(exReference,21);
            
            String companyBranch = (arNirvana.getCompany_branch()!= null ? String.valueOf(arNirvana.getCompany_branch()) : "");
            dataArea += util.generateDataAreaNirvana(companyBranch,6);
            
            String custTaxId = (arNirvana.getCust_taxid() != null && !"".equalsIgnoreCase(arNirvana.getCust_taxid()) ? arNirvana.getCust_taxid(): "");
            dataArea += util.generateDataAreaNirvana(custTaxId,21);
            
            String cusBranch = (arNirvana.getCust_branch()!= null ? String.valueOf(arNirvana.getCust_branch()) : "");
            dataArea += util.generateDataAreaNirvana(cusBranch,6);
            
            String service = (arNirvana.getService()!= null && !"".equalsIgnoreCase(arNirvana.getService()) ? arNirvana.getService(): "");
            dataArea += util.generateDataAreaNirvana(service,1);
        }
        
        return result;
    }

    private String gennarateARNirvanaNo(String type) {
        String hql = "from MRunningCode run where run.type =  :type";
        Session session = this.sessionFactory.openSession();
        List<MRunningCode> list = session.createQuery(hql).setParameter("type", type).list();
        if (list.isEmpty()) {
            return null;
        }
        
        String code = String.valueOf(list.get(0).getRunning()+1);
        for(int i=code.length();i<6;i++){
            code = "0"+code;
        }
        
        Query query = session.createQuery("update MRunningCode run set run.running = :running" +
    				" where run.type = :type");
        query.setParameter("running", list.get(0).getRunning()+1);
        query.setParameter("type", type);
        int result = query.executeUpdate();
        
        session.close();
        this.sessionFactory.close();
        return code;
    }

    public SsDataexchTr setApNirvanaDetail(ARNirvana ar,String datano){
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        String invId = String.valueOf(ar.getInvid());
        String query = " SELECT * FROM `ar_nirvana_sale_detail` where id = '"+invId+"'" ;
        String dataArea = "";
        List<Object[]> ARNirvanaList = session.createSQLQuery(query)
                .addScalar("id", Hibernate.STRING)
                .addScalar("inv_no", Hibernate.STRING)
                .addScalar("salesaccount", Hibernate.STRING)
                .addScalar("salesdivision", Hibernate.STRING)
                .addScalar("salesproject", Hibernate.STRING)
                .addScalar("salesamt", Hibernate.BIG_DECIMAL)
                .addScalar("saleshmamt", Hibernate.BIG_DECIMAL)
                .addScalar("detail", Hibernate.STRING)
                .addScalar("cur_amount", Hibernate.STRING)
                .addScalar("is_vat", Hibernate.STRING)
                .list();
        
        for (Object[] B : ARNirvanaList) {
            String salesaccount = (B[2] != null && !"".equalsIgnoreCase(String.valueOf(B[2])) ? String.valueOf(B[2]) : "");
            String salesdivision = (B[3] != null && !"".equalsIgnoreCase(String.valueOf(B[3])) ? String.valueOf(B[3]) : "");
            String salesproject = (B[4] != null && !"".equalsIgnoreCase(String.valueOf(B[4])) ? String.valueOf(B[4]) : "");
            String salesamt = (B[5] != null && !"".equalsIgnoreCase(String.valueOf(B[5])) ? String.valueOf((BigDecimal)B[5]) : "0.00");
            String saleshmamt = (B[6] != null && !"".equalsIgnoreCase(String.valueOf(B[6])) ? String.valueOf((BigDecimal)B[6]) : "0.00");
            String detail = (B[7] != null && !"".equalsIgnoreCase(String.valueOf(B[7])) ? String.valueOf(B[7]) : "");

            dataArea += salesaccount + util.generateDataAreaNirvana(salesaccount,21);
            dataArea += salesdivision + util.generateDataAreaNirvana(salesdivision,21);
            dataArea += salesproject + util.generateDataAreaNirvana(salesproject,21);
            dataArea += salesamt + util.generateDataAreaNirvana(salesamt,23);
            dataArea += saleshmamt + util.generateDataAreaNirvana(saleshmamt,23);
            dataArea += detail + util.generateDataAreaNirvana(detail,61);
           
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd.HHmmss");
        SsDataexchTr ssdtr = new SsDataexchTr();
        ssdtr.setDataCd("240010");
        ssdtr.setDataNo(datano);
        ssdtr.setDataSeq(String.valueOf(ARNirvanaList.size()));
        ssdtr.setEntSysCd("SMI");
        ssdtr.setEntSysDate(sdf.format(new Date()));
        ssdtr.setEntDataNo(datano);
        ssdtr.setEntComment("");
        ssdtr.setRcvSysCd("NIRVANA");
        ssdtr.setRcvStaCd("1");
        ssdtr.setCvSysDate("00000000.000000");
        ssdtr.setRcvComment("");
        ssdtr.setTraNesCd("1");
        ssdtr.setTraStaCd("1");
        ssdtr.setTraSysDate("00000000.000000");
        ssdtr.setDataArea(dataArea);  

        session.close();
        this.sessionFactory.close();
        return ssdtr;
    }
    
}
