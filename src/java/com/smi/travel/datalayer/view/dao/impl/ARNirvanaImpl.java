/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.view.dao.ARNirvanaDao;
import com.smi.travel.datalayer.view.entity.APNirvana;
import com.smi.travel.datalayer.view.entity.ARNirvana;
import com.smi.travel.util.UtilityFunction;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
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
        query += "  ORDER BY ar.invdate  DESC";
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
                    hql = "update InvoiceDetail inv set inv.isExport = 1 , inv.exportDate = :date where inv.id = :invDetailId";
                
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
       
        this.sessionFactory.close();
        session.close();
        return result;
    }
    
    
    private String genReport(List<ARNirvana> arDataList , String fullFileName , List<ARNirvana> ARList){
        String status ="";
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
                cell.setCellValue(ar.getTransdate());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ar.getDuedate());
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
                cell.setCellValue(ar.getYear());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ar.getPeriod());
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
}
