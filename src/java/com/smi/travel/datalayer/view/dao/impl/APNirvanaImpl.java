/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.view.dao.APNirvanaDao;
import com.smi.travel.datalayer.view.entity.APNirvana;
import com.smi.travel.util.UtilityFunction;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFSheetConditionalFormatting;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Surachai
 */
public class APNirvanaImpl implements APNirvanaDao {

    private SessionFactory sessionFactory;
    private Transaction transaction;

    //Delimiter used in CSV file
    private static final String NEW_LINE_SEPARATOR = "\n";

    //CSV file header
    private static final String[] FILE_HEADER
            = {"refinvoiceno", "intreference", "vendorid", "vendorname", "divisionid",
                "projectid", "transcode", "transdate", "duedate", "currencyid",
                "homerate", "foreignrate", "basevatamt", "basevathmamt", "vatamt",
                "vathmamt", "transamt", "transhmamt", "vatflag", "vatid",
                "whtflag", "whtid", "basewhtamt", "basewhthmamt", "whtamt",
                "whthmamt", "year", "period", "note", "puraccount1",
                "purdivision1", "purproject1", "puramt1", "purhmamt1", "puraccount2",
                "purdivision2", "purproject2", "puramt2", "purhmamt2", "puraccount3",
                "purdivision3", "purproject3", "puramt3", "purhmamt3", "puraccount4",
                "purdivision4", "purproject4", "puramt4", "purhmamt4", "puraccount5",
                "purdivision5", "purproject5", "puramt5", "purhmamt5", "puraccount6",
                "purdivision6", "purproject6", "puramt6", "purhmamt6", "puraccount7",
                "purdivision7", "purproject7", "puramt7", "purhmamt7", "puraccount8",
                "purdivision8", "purproject8", "puramt8", "purhmamt8", "puraccount9",
                "purdivision9", "purproject9", "puramt9", "purhmamt9", "puraccount10",
                "purdivision10", "purproject10", "puramt10", "purhmamt10", "service",
                "apaccount", "prefix", "voucherno", "Taxid", "vendor_branch",
                "company_branch"};

    @Override
    public String ExportAPFileInterface(List<APNirvana> APList, String pathFile) {
        
        String status = "";
        List<APNirvana> apDataList = this.SearchApNirvanaFromPaymentDetailId(APList);
        SimpleDateFormat folderName = new SimpleDateFormat("yyMMdd");
        SimpleDateFormat fileName = new SimpleDateFormat("HHmmss");
//        File folder = new File(pathFile);
//        if (!folder.exists() && !folder.isDirectory()) {
//            folder.mkdirs();
//        }
//        String fullFileName = folder.getAbsolutePath() + "\\AP" + fileName.format(Calendar.getInstance().getTime());

//        FileWriter fileWriter = null;
//        CSVPrinter csvFilePrinter = null;
//        //Create the CSVFormat object with "\n" as a record delimiter
//        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
//        
//
//        try {
//            //initialize FileWriter object
//            fileWriter = new FileWriter(fullFileName + ".csv");
//
//            //initialize CSVPrinter object 
//            csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
//
//            //Create CSV file header
//            csvFilePrinter.printRecord(FILE_HEADER);
//
//            for (Iterator<APNirvana> it = apDataList.iterator(); it.hasNext();) {
//                APNirvana ap = it.next();
//                List apRecord = new ArrayList();
//                apRecord.add(ap.getRefinvoiceno());
//                apRecord.add(ap.getIntreference());
//                apRecord.add(ap.getVendorid());
//                apRecord.add(ap.getVendorname());
//                apRecord.add(ap.getDivisionid());
//                apRecord.add(ap.getProjectid());
//                apRecord.add(ap.getTranscode());
//                apRecord.add(ap.getTransdate());
//                apRecord.add(ap.getDuedate());
//                apRecord.add(ap.getCurrencyid());
//                apRecord.add(ap.getHomerate());
//                apRecord.add(ap.getForeignrate());
//                apRecord.add(ap.getBasevatamt());
//                apRecord.add(ap.getBasevathmamt());
//                apRecord.add(ap.getVatamt());
//                apRecord.add(ap.getVathmamt());
//                apRecord.add(ap.getTransamt());
//                apRecord.add(ap.getTranshmamt());
//                apRecord.add(ap.getVatflag());
//                apRecord.add(ap.getVatid());
//                apRecord.add(ap.getWhtflag());
//                apRecord.add(ap.getWhtid());
//                apRecord.add(ap.getBasewhtamt());
//                apRecord.add(ap.getBasewhthmamt());
//                apRecord.add(ap.getWhtamt());
//                apRecord.add(ap.getWhthmamt());
//                apRecord.add(ap.getYear());
//                apRecord.add(ap.getPeriod());
//                apRecord.add(ap.getNote());
//                apRecord.add(ap.getPuraccount1());
//                apRecord.add(ap.getPurdivision1());
//                apRecord.add(ap.getPurproject1());
//                apRecord.add(ap.getPuramt1());
//                apRecord.add(ap.getPurhmamt1());
//                apRecord.add(ap.getPuraccount2());
//                apRecord.add(ap.getPurdivision2());
//                apRecord.add(ap.getPurproject2());
//                apRecord.add(ap.getPuramt2());
//                apRecord.add(ap.getPurhmamt2());
//                apRecord.add(ap.getPuraccount3());
//                apRecord.add(ap.getPurdivision3());
//                apRecord.add(ap.getPurproject3());
//                apRecord.add(ap.getPuramt3());
//                apRecord.add(ap.getPurhmamt3());
//                apRecord.add(ap.getPuraccount4());
//                apRecord.add(ap.getPurdivision4());
//                apRecord.add(ap.getPurproject4());
//                apRecord.add(ap.getPuramt4());
//                apRecord.add(ap.getPurhmamt4());
//                apRecord.add(ap.getPuraccount5());
//                apRecord.add(ap.getPurdivision5());
//                apRecord.add(ap.getPurproject5());
//                apRecord.add(ap.getPuramt5());
//                apRecord.add(ap.getPurhmamt5());
//                apRecord.add(ap.getPuraccount6());
//                apRecord.add(ap.getPurdivision6());
//                apRecord.add(ap.getPurproject6());
//                apRecord.add(ap.getPuramt6());
//                apRecord.add(ap.getPurhmamt6());
//                apRecord.add(ap.getPuraccount7());
//                apRecord.add(ap.getPurdivision7());
//                apRecord.add(ap.getPurproject7());
//                apRecord.add(ap.getPuramt7());
//                apRecord.add(ap.getPurhmamt7());
//                apRecord.add(ap.getPuraccount8());
//                apRecord.add(ap.getPurdivision8());
//                apRecord.add(ap.getPurproject8());
//                apRecord.add(ap.getPuramt8());
//                apRecord.add(ap.getPurhmamt8());
//                apRecord.add(ap.getPuraccount9());
//                apRecord.add(ap.getPurdivision9());
//                apRecord.add(ap.getPurproject9());
//                apRecord.add(ap.getPuramt9());
//                apRecord.add(ap.getPurhmamt9());
//                apRecord.add(ap.getPuraccount10());
//                apRecord.add(ap.getPurdivision10());
//                apRecord.add(ap.getPurproject10());
//                apRecord.add(ap.getPuramt10());
//                apRecord.add(ap.getPurhmamt10());
//                apRecord.add(ap.getService());
//                apRecord.add(ap.getApaccount());
//                apRecord.add(ap.getPrefix());
//                apRecord.add(ap.getVoucherno());
//                apRecord.add(ap.getTaxid());
//                apRecord.add(ap.getVendor_branch());
//                apRecord.add(ap.getCompany_branch());
//                csvFilePrinter.printRecord(apRecord);
//            }
//
//            System.out.println("CSV file was created successfully !!!");
//
//        } catch (Exception e) {
//            System.out.println("Error in CsvFileWriter !!!");
//            e.printStackTrace();
//        } finally {
//            try {
//                fileWriter.flush();
//                fileWriter.close();
//                csvFilePrinter.close();
//            } catch (IOException e) {
//                System.out.println("Error while flushing/closing fileWriter/csvPrinter !!!");
//                e.printStackTrace();
//            }
//        }
            int accno = 1 ;
            List<APNirvana> apNirvanaList = new ArrayList<APNirvana>();
            String fullFileName = "";
            for (int i=0 ; i< apDataList.size() ; i++) {
                APNirvana ap = apDataList.get(i);
                File folder = new File(pathFile+"\\accno"+accno+"\\ap\\" + folderName.format(Calendar.getInstance().getTime()));
                if(accno == Integer.parseInt(ap.getAccno())){
                    apNirvanaList.add(ap);
                    accno = Integer.parseInt(ap.getAccno());
                }else{
                    folder = new File(pathFile+"\\accno"+accno+"\\ap\\" + folderName.format(Calendar.getInstance().getTime()));
                    if (!folder.exists() && !folder.isDirectory()) {
                        folder.mkdirs();
                    }
                    fullFileName = folder.getAbsolutePath() +"\\AP" + fileName.format(Calendar.getInstance().getTime());
                    status = genReport(apNirvanaList,fullFileName,APList);
                    System.out.println(" status " + status);
                    
                    apNirvanaList = new ArrayList<APNirvana>();
                    apNirvanaList.add(ap);
                    accno = Integer.parseInt(ap.getAccno());
                }
                if(i ==  (apDataList.size() - 1)){
                    folder = new File(pathFile+"\\accno"+accno+"\\ap\\" + folderName.format(Calendar.getInstance().getTime()));
                    if (!folder.exists() && !folder.isDirectory()) {
                        folder.mkdirs();
                    }
                    fullFileName = folder.getAbsolutePath() +"\\AP" + fileName.format(Calendar.getInstance().getTime());
                    status = genReport(apNirvanaList,fullFileName,APList);
                    System.out.println(" status " + status);
                }
            }
        
//        try {
//            HSSFWorkbook workbook = new HSSFWorkbook();
//            HSSFSheet sheet = workbook.createSheet();
//            int rownum = 0;
//            int accno = 0 ;
//            for (APNirvana ap : apDataList) {
//                HSSFRow dataRow = sheet.createRow(rownum++);
//                int cellnum = 0;
//                HSSFCell cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getRefinvoiceno());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getIntreference());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getVendorid());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getVendorname());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getDivisionid());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getProjectid());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getTranscode());
//                cell = dataRow.createCell(cellnum++);
////                cell.setCellValue(ap.getTransdate());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getDuedate());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getCurrencyid());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(UtilityFunction.getObjectString(ap.getHomerate()));
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(UtilityFunction.getObjectString(ap.getForeignrate()));
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(UtilityFunction.getObjectString(ap.getBasevatamt()));
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(UtilityFunction.getObjectString(ap.getBasevathmamt()));
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(UtilityFunction.getObjectString(ap.getVatamt()));
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(UtilityFunction.getObjectString(ap.getVathmamt()));
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(UtilityFunction.getObjectString(ap.getTransamt()));
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(UtilityFunction.getObjectString(ap.getTranshmamt()));
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getVatflag());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getVatid());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getWhtflag());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getWhtid());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(UtilityFunction.getObjectString(ap.getBasewhtamt()));
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(UtilityFunction.getObjectString(ap.getBasewhthmamt()));
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(UtilityFunction.getObjectString(ap.getWhtamt()));
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(UtilityFunction.getObjectString(ap.getWhthmamt()));
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getYear());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getPeriod());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getNote());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getPuraccount1());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getPurdivision1());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getPurproject1());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(UtilityFunction.getObjectString(ap.getPuramt1()));
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(UtilityFunction.getObjectString(ap.getPurhmamt1()));
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getPuraccount2());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getPurdivision2());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getPurproject2());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(UtilityFunction.getObjectString(ap.getPuramt2()));
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(UtilityFunction.getObjectString(ap.getPurhmamt2()));
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getPuraccount3());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getPurdivision3());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getPurproject3());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(UtilityFunction.getObjectString(ap.getPuramt3()));
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(UtilityFunction.getObjectString(ap.getPurhmamt3()));
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getPuraccount4());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getPurdivision4());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getPurproject4());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(UtilityFunction.getObjectString(ap.getPuramt4()));
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(UtilityFunction.getObjectString(ap.getPurhmamt4()));
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getPuraccount5());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getPurdivision5());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getPurproject5());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(UtilityFunction.getObjectString(ap.getPuramt5()));
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(UtilityFunction.getObjectString(ap.getPurhmamt5()));
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getPuraccount6());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getPurdivision6());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getPurproject6());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(UtilityFunction.getObjectString(ap.getPuramt6()));
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(UtilityFunction.getObjectString(ap.getPurhmamt6()));
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getPuraccount7());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getPurdivision7());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getPurproject7());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(UtilityFunction.getObjectString(ap.getPuramt7()));
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(UtilityFunction.getObjectString(ap.getPurhmamt7()));
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getPuraccount8());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getPurdivision8());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getPurproject8());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(UtilityFunction.getObjectString(ap.getPuramt8()));
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(UtilityFunction.getObjectString(ap.getPurhmamt8()));
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getPuraccount9());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getPurdivision9());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getPurproject9());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(UtilityFunction.getObjectString(ap.getPuramt9()));
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(UtilityFunction.getObjectString(ap.getPurhmamt9()));
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getPuraccount10());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getPurdivision10());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getPurproject10());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(UtilityFunction.getObjectString(ap.getPuramt10()));
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(UtilityFunction.getObjectString(ap.getPurhmamt10()));
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getService());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getApaccount());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getPrefix());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getVoucherno());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getTaxid());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getVendor_branch()== null? "0":ap.getVendor_branch().toString());
//                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getCompany_branch());
//            }
//
//            FileOutputStream out = new FileOutputStream(new File(fullFileName + ".xls"));
//            workbook.write(out);
//            out.close();
//            status = "success";
//        } catch (Exception e) {
//            e.printStackTrace();
//            for (APNirvana ap : APList) {
//                if(!"".equals(status)){
//                    status += ", ";
//                }
//                status += ap.getPayment_detail_id();
//            }
//        }
        return status;
    }

    @Override
    public String UpdateStatusAPInterface(List<APNirvana> APList) {
        int result = 0;
        try {
            Session session = this.getSessionFactory().openSession();
            setTransaction(session.beginTransaction());

            for (int i = 0; i < APList.size(); i++) {
                APNirvana apNirvana = APList.get(i);
                String paymentDetailId = apNirvana.getPayment_detail_id();
                String paymentType = apNirvana.getPaymenttype();
                Date date = new Date();
                if ("W".equalsIgnoreCase(paymentType)) {
                    String hql = "update PaymentDetailWendy pay set pay.isExport = 1 , pay.exportDate = :date where pay.id = :paymentDetailId";
                    try {
                        Query query = session.createQuery(hql);
                        query.setParameter("paymentDetailId", paymentDetailId);
                        query.setParameter("date", date);
                        System.out.println(" query " + query);
                        result = query.executeUpdate();
                        System.out.println("Rows affected: " + result);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        result = 0;
                    }
                } else if ("A".equalsIgnoreCase(paymentType)) {
                    String hql = "update PaymentAirticket air set air.isExport = 1 , air.exportDate = :date where air.id = :paymentDetailId";
                    try {
                        Query query = session.createQuery(hql);
                        query.setParameter("paymentDetailId", paymentDetailId);
                        query.setParameter("date", date);
                        System.out.println(" query " + query);
                        result = query.executeUpdate();
                        System.out.println("Rows affected: " + result);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        result = 0;
                    }
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
    public List<APNirvana> SearchApNirvanaFromFilter(String paymentType, String productType, String status, String from, String to, String accno) {
        UtilityFunction util = new UtilityFunction();
        List<APNirvana> apNirvanaList = new ArrayList<APNirvana>();
        Session session = this.getSessionFactory().openSession();
        StringBuffer query = new StringBuffer(" SELECT * FROM `ap_nirvana` ");
        boolean haveCondition = false;
        if ((paymentType != null) && (!"".equalsIgnoreCase(paymentType))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" `ap_nirvana`.paymenttype = '" + paymentType + "'");
            haveCondition = true;
        }
        if ((productType != null) && (!"".equalsIgnoreCase(productType))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" `ap_nirvana`.producttype = '" + productType + "'");
            haveCondition = true;
        }
        if ((status != null) && (!"".equalsIgnoreCase(status))) {
            if ("N".equalsIgnoreCase(status)) {
                status = "New";
            }
            if ("E".equalsIgnoreCase(status)) {
                status = "Export";
            }
            if ("C".equalsIgnoreCase(status)) {
                status = "Change";
            }
            query.append(haveCondition ? " and" : " where");
            query.append(" `ap_nirvana`.itf_status = '" + status + "'");
            haveCondition = true;
        }
        if ((from != null) && (!"".equalsIgnoreCase(from))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" `ap_nirvana`.createdate >= '" + from + "'");
            haveCondition = true;
        }
        if ((to != null) && (!"".equalsIgnoreCase(to))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" `ap_nirvana`.createdate <= '" + to + "'");
            haveCondition = true;
        }
        if ((accno != null) && (!"".equalsIgnoreCase(accno))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" `ap_nirvana`.accno = '" + accno + "'");
            haveCondition = true;
        }
        

        List<Object[]> QueryList = session.createSQLQuery(query.toString())
                .addScalar("refinvoiceno", Hibernate.STRING)
                .addScalar("intreference", Hibernate.STRING)
                .addScalar("vendorid", Hibernate.STRING)
                .addScalar("vendorname", Hibernate.STRING)
                .addScalar("divisionid", Hibernate.STRING)
                .addScalar("projectid", Hibernate.STRING)
                .addScalar("transcode", Hibernate.STRING)
                //            .addScalar("transdate",Hibernate.DATE)
                .addScalar("duedate", Hibernate.DATE)
                .addScalar("currencyid", Hibernate.STRING)
                .addScalar("homerate", Hibernate.BIG_DECIMAL)
                .addScalar("foreignrate", Hibernate.BIG_DECIMAL)
                .addScalar("basevatamt", Hibernate.BIG_DECIMAL)
                .addScalar("basevathmamt", Hibernate.BIG_DECIMAL)
                .addScalar("vatamt", Hibernate.BIG_DECIMAL)
                .addScalar("vathmamt", Hibernate.BIG_DECIMAL)
                .addScalar("transamt", Hibernate.BIG_DECIMAL)
                .addScalar("transhmamt", Hibernate.BIG_DECIMAL)
                .addScalar("vatflag", Hibernate.STRING)
                .addScalar("vatid", Hibernate.STRING)
                .addScalar("whtflag", Hibernate.STRING)
                .addScalar("whtid", Hibernate.STRING)
                .addScalar("basewhtamt", Hibernate.BIG_DECIMAL)
                .addScalar("basewhthmamt", Hibernate.BIG_DECIMAL)
                .addScalar("whtamt", Hibernate.BIG_DECIMAL)
                .addScalar("whthmamt", Hibernate.BIG_DECIMAL)
                .addScalar("year", Hibernate.INTEGER)
                .addScalar("period", Hibernate.INTEGER)
                .addScalar("note", Hibernate.STRING)
                .addScalar("puraccount1", Hibernate.STRING)
                .addScalar("purdivision1", Hibernate.STRING)
                .addScalar("purproject1", Hibernate.STRING)
                .addScalar("puramt1", Hibernate.BIG_DECIMAL)
                .addScalar("purhmamt1", Hibernate.BIG_DECIMAL)
                .addScalar("puraccount2", Hibernate.STRING)
                .addScalar("purdivision2", Hibernate.STRING)
                .addScalar("purproject2", Hibernate.STRING)
                .addScalar("puramt2", Hibernate.BIG_DECIMAL)
                .addScalar("purhmamt2", Hibernate.BIG_DECIMAL)
                .addScalar("puraccount3", Hibernate.STRING)
                .addScalar("purdivision3", Hibernate.STRING)
                .addScalar("purproject3", Hibernate.STRING)
                .addScalar("puramt3", Hibernate.BIG_DECIMAL)
                .addScalar("purhmamt3", Hibernate.BIG_DECIMAL)
                .addScalar("puraccount4", Hibernate.STRING)
                .addScalar("purdivision4", Hibernate.STRING)
                .addScalar("purproject4", Hibernate.STRING)
                .addScalar("puramt4", Hibernate.BIG_DECIMAL)
                .addScalar("purhmamt4", Hibernate.BIG_DECIMAL)
                .addScalar("puraccount5", Hibernate.STRING)
                .addScalar("purdivision5", Hibernate.STRING)
                .addScalar("purproject5", Hibernate.STRING)
                .addScalar("puramt5", Hibernate.BIG_DECIMAL)
                .addScalar("purhmamt5", Hibernate.BIG_DECIMAL)
                .addScalar("puraccount6", Hibernate.STRING)
                .addScalar("purdivision6", Hibernate.STRING)
                .addScalar("purproject6", Hibernate.STRING)
                .addScalar("puramt6", Hibernate.BIG_DECIMAL)
                .addScalar("purhmamt6", Hibernate.BIG_DECIMAL)
                .addScalar("puraccount7", Hibernate.STRING)
                .addScalar("purdivision7", Hibernate.STRING)
                .addScalar("purproject7", Hibernate.STRING)
                .addScalar("puramt7", Hibernate.BIG_DECIMAL)
                .addScalar("purhmamt7", Hibernate.BIG_DECIMAL)
                .addScalar("puraccount8", Hibernate.STRING)
                .addScalar("purdivision8", Hibernate.STRING)
                .addScalar("purproject8", Hibernate.STRING)
                .addScalar("puramt8", Hibernate.BIG_DECIMAL)
                .addScalar("purhmamt8", Hibernate.BIG_DECIMAL)
                .addScalar("puraccount9", Hibernate.STRING)
                .addScalar("purdivision9", Hibernate.STRING)
                .addScalar("purproject9", Hibernate.STRING)
                .addScalar("puramt9", Hibernate.BIG_DECIMAL)
                .addScalar("purhmamt9", Hibernate.BIG_DECIMAL)
                .addScalar("puraccount10", Hibernate.STRING)
                .addScalar("purdivision10", Hibernate.STRING)
                .addScalar("purproject10", Hibernate.STRING)
                .addScalar("puramt10", Hibernate.BIG_DECIMAL)
                .addScalar("purhmamt10", Hibernate.BIG_DECIMAL)
                .addScalar("service", Hibernate.STRING)
                .addScalar("apaccount", Hibernate.STRING)
                .addScalar("prefix", Hibernate.STRING)
                .addScalar("voucherno", Hibernate.INTEGER)
                .addScalar("taxid", Hibernate.STRING)
                .addScalar("vendor_branch", Hibernate.INTEGER)
                .addScalar("company_branch", Hibernate.INTEGER)
                .addScalar("itf_status", Hibernate.STRING)
                .addScalar("payment_id", Hibernate.STRING)
                .addScalar("paymenttype", Hibernate.STRING)
                .addScalar("payment_detail_id", Hibernate.STRING) //88
                .addScalar("rowid", Hibernate.STRING) //89
                .addScalar("payno", Hibernate.STRING) //89
                .list();

        for (Object[] B : QueryList) {
            APNirvana apNirvana = new APNirvana();
            apNirvana.setIntreference(util.ConvertString(B[1]));
            apNirvana.setVendorid(util.ConvertString(B[2]));
            apNirvana.setVendorname(util.ConvertString(B[3]));
            apNirvana.setCurrencyid(util.ConvertString(B[8]));
            apNirvana.setBasevatamt((B[11]) != null ? new BigDecimal(util.ConvertString(B[11])) : new BigDecimal("0.00"));
            apNirvana.setVatamt((B[13]) != null ? new BigDecimal(util.ConvertString(B[13])) : new BigDecimal("0.00"));
            apNirvana.setPuraccount1(util.ConvertString(B[28]));
            apNirvana.setItf_status(util.ConvertString(B[85]));
            apNirvana.setPaymenttype(util.ConvertString(B[87]));
            apNirvana.setPayment_detail_id(util.ConvertString(B[88]));
            apNirvana.setRowid(util.ConvertString(B[89]));
            apNirvana.setPayno(util.ConvertString(B[90]));
            apNirvanaList.add(apNirvana);
        }

        this.sessionFactory.close();
        session.close();
        return apNirvanaList;

    }

//    private List<APNirvana> mappingAPNirvana(List<APNirvana> apNirvanaList) {
//        List<APNirvana> mappingData = new ArrayList<APNirvana>();
//        for(int i=0;i<apNirvanaList.size();i++){
//            APNirvana data = new APNirvana();
//            data = apNirvanaList.get(i);
//            APNirvana apNirvana = new APNirvana();
//            apNirvana.setIntreference(data.getIntreference());
//            apNirvana.setVendorid(data.getVatid());
//            apNirvana.setVendorname(data.getVendorname());
//            apNirvana.setPuraccount1(data.getPuraccount1());
//            apNirvana.setBasevatamt(data.getBasevatamt() != null ? data.getBasevatamt() : new BigDecimal("0.00"));
//            apNirvana.setCurrencyid(data.getCurrencyid());
//            mappingData.add(apNirvana);
//        }
//        
//        return mappingData;
//    }
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

    public List<APNirvana> SearchApNirvanaFromPaymentDetailId(List<APNirvana> APList) {
        Session session = this.getSessionFactory().openSession();
//        StringBuffer query = new StringBuffer("");
//        for (int i = 0; i < APList.size(); i++) {
//            APNirvana ap = APList.get(i);
//            if (i != 0) {
//                query.append(" OR ");
//            }
//            query.append("( payment_detail_id = " + ap.getPayment_detail_id() + " AND paymenttype = '" + ap.getPaymenttype() + "' )");
//        }

      //  SQLQuery sQLQuery = session.createSQLQuery(query.toString()).addEntity(APNirvana.class);
      //  List result = sQLQuery.list();
        String query = "from APNirvana ap where ap.rowid in (";
        for (int i = 0; i < APList.size(); i++) {
            query += (i == 0 ? "" : ",");
            query += ("'"+APList.get(i).getRowid()+"'");
        }
        query += ") order by accno , intreference asc " ;
        System.out.println(" query :: " + query);
        Query HqlQuery = session.createQuery(query);
        List<APNirvana> result = HqlQuery.list();
       
        this.sessionFactory.close();
        session.close();
        return result;
    }

    @Override
    public List getApNirvanaReport(String paymentType, String productType, String status, String from, String to, String printby) {
        UtilityFunction util = new UtilityFunction();
        Session session = this.getSessionFactory().openSession();
        StringBuffer query = new StringBuffer(" SELECT `ap_nirvana`.* FROM `ap_nirvana` ");
        boolean haveCondition = false;
        if ((paymentType != null) && (!"".equalsIgnoreCase(paymentType))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" `ap_nirvana`.paymenttype = '" + paymentType + "'");
            haveCondition = true;
        }
        if ((productType != null) && (!"".equalsIgnoreCase(productType))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" `ap_nirvana`.producttype = '" + productType + "'");
            haveCondition = true;
        }
        if ((status != null) && (!"".equalsIgnoreCase(status))) {
            if ("N".equalsIgnoreCase(status)) {
                status = "New";
            }
            if ("E".equalsIgnoreCase(status)) {
                status = "Export";
            }
            if ("C".equalsIgnoreCase(status)) {
                status = "Change";
            }
            query.append(haveCondition ? " and" : " where");
            query.append(" `ap_nirvana`.itf_status = '" + status + "'");
            haveCondition = true;
        }
        if ((from != null) && (!"".equalsIgnoreCase(from))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" `ap_nirvana`.createdate >= '" + from + "'");
            haveCondition = true;
        }
        if ((to != null) && (!"".equalsIgnoreCase(to))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" `ap_nirvana`.createdate <= '" + to + "'");
            haveCondition = true;
        }
        
//        SQLQuery sQLQuery = session.createSQLQuery(query.toString()).addEntity(APNirvana.class);
//        List result = new ArrayList<APNirvana>();
//        List result = sQLQuery.list();
        List<Object[]> QueryList = session.createSQLQuery(query.toString())
                .addScalar("intreference", Hibernate.STRING)
                .addScalar("vendorid", Hibernate.STRING)
                .addScalar("vendorname", Hibernate.STRING)
                .addScalar("puraccount1", Hibernate.STRING)
                .addScalar("vatamt", Hibernate.BIG_DECIMAL)
                .addScalar("basevatamt", Hibernate.BIG_DECIMAL)
                .addScalar("currencyid", Hibernate.STRING)
                .list();
        List result = new ArrayList<APNirvana>();
        if(QueryList.isEmpty()){
            SimpleDateFormat dateformat = new SimpleDateFormat();
            dateformat.applyPattern("dd-MM-yyyy HH:mm:ss");
            APNirvana apNirvana = new APNirvana();
            apNirvana.setUser(printby);
            apNirvana.setSystemdate(String.valueOf(dateformat.format(new Date())));
            apNirvana.setDatefrom(from);
            apNirvana.setDateto(to);
            result.add(apNirvana);
            return result;
        }
        boolean header = true;
        for (Object[] B : QueryList) {
            APNirvana apNirvana = new APNirvana();
            apNirvana.setIntreference(util.ConvertString(B[0]));
            apNirvana.setVendorid(util.ConvertString(B[1]));
            apNirvana.setVendorname(util.ConvertString(B[2]));
            apNirvana.setPuraccount1(util.ConvertString(B[3]));
            apNirvana.setCurrencyid(util.ConvertString(B[6]));
            apNirvana.setVatamt((B[4]) != null ? new BigDecimal(util.ConvertString(B[4])) : new BigDecimal("0.00"));
            apNirvana.setBasevatamt((B[5]) != null ? new BigDecimal(util.ConvertString(B[5])) : new BigDecimal("0.00"));
            if(header){
                SimpleDateFormat dateformat = new SimpleDateFormat();
                dateformat.applyPattern("dd-MM-yyyy HH:mm:ss");         
                apNirvana.setUser(printby);
                apNirvana.setSystemdate(String.valueOf(dateformat.format(new Date())));
                apNirvana.setDatefrom(from);
                apNirvana.setDateto(to);
                header = false;
            }
            result.add(apNirvana);
        }
        
//        APNirvana dataheader = new APNirvana();
//        dataheader = (APNirvana) result.get(0);
//        dataheader.setDatefrom(from);
//        dataheader.setDateto(to);
//        result.set(0, dataheader);
        
        this.sessionFactory.close();
        session.close();
        return result;
    }
    
    private String genReport(List<APNirvana> apDataList , String fullFileName , List<APNirvana> APList){
      String status ="";
        UtilityFunction util = new UtilityFunction();
     try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
            int rownum = 0;
            for (APNirvana ap : apDataList) {
                HSSFRow dataRow = sheet.createRow(rownum++);
                int cellnum = 0;
                HSSFCell cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getRefinvoiceno());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getIntreference());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getVendorid());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getVendorname());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getDivisionid());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getProjectid());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getTranscode());
                cell = dataRow.createCell(cellnum++);
//                cell.setCellValue(ap.getTransdate());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getDuedate() == null ? "":util.convertDateToString(ap.getDuedate()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getCurrencyid());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getHomerate()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getForeignrate()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getBasevatamt()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getBasevathmamt()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getVatamt()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getVathmamt()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getTransamt()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getTranshmamt()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getVatflag());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getVatid());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getWhtflag());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getWhtid());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getBasewhtamt()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getBasewhthmamt()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getWhtamt()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getWhthmamt()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getYear() == null ? "":ap.getYear().toString());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPeriod() == null ? "":ap.getPeriod().toString());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getNote());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPuraccount1());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurdivision1());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurproject1());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPuramt1()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPurhmamt1()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPuraccount2());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurdivision2());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurproject2());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPuramt2()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPurhmamt2()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPuraccount3());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurdivision3());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurproject3());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPuramt3()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPurhmamt3()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPuraccount4());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurdivision4());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurproject4());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPuramt4()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPurhmamt4()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPuraccount5());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurdivision5());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurproject5());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPuramt5()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPurhmamt5()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPuraccount6());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurdivision6());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurproject6());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPuramt6()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPurhmamt6()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPuraccount7());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurdivision7());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurproject7());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPuramt7()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPurhmamt7()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPuraccount8());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurdivision8());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurproject8());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPuramt8()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPurhmamt8()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPuraccount9());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurdivision9());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurproject9());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPuramt9()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPurhmamt9()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPuraccount10());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurdivision10());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurproject10());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPuramt10()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPurhmamt10()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getService());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getApaccount());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPrefix());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getVoucherno());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getTaxid());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getVendor_branch()== null? "0":ap.getVendor_branch().toString());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getCompany_branch());
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
            for (APNirvana ap : APList) {
                if(!"".equals(status)){
                    status += ", ";
                }
                status += ap.getPayment_detail_id();
            }
        }
     return status;
    }

}
