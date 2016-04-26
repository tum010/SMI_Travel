/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.migration;

import com.smi.travel.controller.excel.master.UtilityExcelFunction;
import com.smi.travel.datalayer.entity.MCity;
import com.smi.travel.datalayer.entity.MCountry;
import com.smi.travel.datalayer.entity.MCurrency;
import com.smi.travel.datalayer.entity.MInitialname;
import com.smi.travel.datalayer.entity.MProductType;
import com.smi.travel.util.UtilityFunction;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;

/**
 *
 * @author Surachai
 */
public class MainMigrate {
    
    private static final String TaxInvoiceReport = " SELECT SALE.*, agt.tax_no AS TAXNO, agt. BRANCH, agt.branch_no FROM ACCTSMI3.REPORT_TAX_INVOICE sale INNER JOIN \"TRAVOX3\".\"AC_TAX_INVOICE\" tax ON tax.\"ID\" = sale.TAX_ID LEFT JOIN TRAVOX3.AGENT AGT ON AGT.code = tax.TAX_INV_TO AND AGT.\"NAME\" = TAX.TAX_INV_NAME WHERE TO_CHAR (sale.TAX_DATE, 'mm') = '11' AND TO_CHAR (sale.TAX_DATE, 'yyyy') = '2014' ORDER BY sale.invoice_type, sale.TAX_NO, sale.TAX_DATE ";
    private static final String AgentReport = " SELECT * FROM TRAVOX3.AGENT ";
    private static final String StaffReport = " SELECT * FROM TRAVOX3.STAFF ";
    private static final String ExportFilePath = "C:\\Users\\Jittima\\Desktop\\ExcelFile\\";
    
    private static final String sqlAirline = " SELECT * FROM TRAVOX3.AIRLINE ";
    private static final String sqlProduct = " SELECT * FROM TRAVOX3.PRODUCT ";
    private static final String sqlPackageTour = " SELECT * FROM TRAVOX3.PACKAGE_TOUR ";
    private static final String sqlHotel = " SELECT * FROM TRAVOX3.HOTEL ";
    private static final String sqlCurrency = " SELECT * FROM TRAVOX3.CURRENCY ";
    private static final String sqlCountry = " SELECT * FROM TRAVOX3.COUNTRY ";
    private static final String sqlCity = " SELECT * FROM TRAVOX3.CITY ";
    private static final String sqlCustomer = " SELECT * FROM TRAVOX3.CUSTOMER ";
    private static final String sqlAR = " SELECT CASE WHEN (agt.code IS NULL) THEN 'DUMMY' ELSE agt.code END AS CODE, inv.inv_name, INV.INV_NO, TO_CHAR (inv.inv_date, 'DD-MM-YYYY') AS inv_date FROM \"TRAVOX3\".\"AC_INVOICE\" inv INNER JOIN \"TRAVOX3\".AC_INVOICE_DETAIL invd ON INVD.AC_INVOICE_ID = INV.\"ID\" LEFT JOIN ( SELECT NAME, MIN (code) AS code FROM \"TRAVOX3\".AGENT GROUP BY NAME ) agt ON agt. NAME = inv.INV_NAME WHERE TO_CHAR (inv.inv_date, 'MMYYYY') IN ('112015','122015','012016', '022016', '032016') ORDER BY  inv.inv_date , inv.inv_no " ;
    private static final String sqlAP = " SELECT payd. ID AS payid, (pay.PAY_NO) AS PAY_NO, (PAY.INVOICE_SUP) AS AP_CODE, ap. NAME AS NAME, TO_CHAR (pay.pay_DATE, 'DD-MM-YYYY') AS pay_date, (PAY.REF_DEPARTMENT) AS DEPARTMENT, CASE WHEN pay.VAT_TYPE = 'X' THEN 'TEMP' WHEN pay.VAT_TYPE = 'V' THEN 'VAT' ELSE 'NO VAT' END AS vattype FROM travox3.AC_PAYMENT pay INNER JOIN travox3.AC_PAYMENT_DETAIL payd ON payd.AC_PAYMENT_ID = pay. ID INNER JOIN ACCSMI3.AP_CODE ap ON ap.code = pay.INVOICE_SUP WHERE TO_CHAR (pay.pay_DATE, 'MMYYYY') IN ( '112015','122015','012016', '022016', '032016') ORDER BY pay.pay_DATE , PAY.PAY_NO ";
    private static final String sqlInv1 = " SELECT inv3. ID AS ID, INV3.inv_no AS invno, INV3.\"NAME\" AS NAME, TO_CHAR (INV3.INV_DATE, 'DD-MM-YYYY') AS invdate, SUM (invd3.price) AS grand_total, SUM (invd3.price) - SUM ( ROUND ( INVD3.price - INVD3.price * 100 / (100 + INV3.vat), 2 )) AS grand_total_gross, SUM ( ROUND ( INVD3.price - INVD3.price * 100 / (100 + INV3.vat), 2 )) AS grand_total_vat, MIN (INVD3.CUR) AS cur, 'INBOUND' AS department, '1' AS acc_no FROM \"INBOUND\".\"INVOICE3\" inv3 INNER JOIN INBOUND.INVOICE3_DETAIL invd3 ON inv3. ID = invd3.INVOICE3_ID LEFT JOIN ( SELECT NAME, MIN (code) AS code FROM \"TRAVOX3\".AGENT GROUP BY NAME ) agt ON agt. NAME = inv3. NAME WHERE \"TO_CHAR\" (inv3.INV_DATE, 'MMYYYY') IN ( '112015', '122015', '012016', '022016', '032016' ) GROUP BY inv3. ID, INV3.inv_no, INV3.\"NAME\", INV3.INV_DATE ORDER BY INV3. ID  ";
    private static final String sqlInv2 = " SELECT inv2. ID AS ID, INV2.inv_no AS invno, INV2.\"NAME\" AS NAME, TO_CHAR (INV2.INV_DATE, 'DD-MM-YYYY') AS invdate, SUM (invd2.price) AS grand_total, SUM (invd2.price) AS grand_total_gross, 0 AS grand_total_vat, MIN (INVD2.CUR) AS cur, 'INBOUND' AS department, '2' AS acc_no FROM \"INBOUND\".\"INVOICE2\" inv2 INNER JOIN INBOUND.INVOICE2_DETAIL invd2 ON inv2. ID = invd2.INVOICE2_ID LEFT JOIN ( SELECT NAME, MIN (code) AS code FROM \"TRAVOX3\".AGENT GROUP BY NAME ) agt ON agt. NAME = inv2. NAME WHERE \"TO_CHAR\" (inv2.INV_DATE, 'MMYYYY') IN ( '112015', '122015', '012016', '022016', '032016' ) GROUP BY inv2. ID, INV2.inv_no, INV2.\"NAME\", INV2.INV_DATE ORDER BY INV2. ID ";
    
    public static void main(String[] args) {
        Connection connect = null;
        Statement s = null;  
        Statement stmt = null;
        try {  
            connect = OracleConnection.getConnection();
            s = connect.createStatement();
            if (connect != null) {
//                getTaxInvoice(s,stmt);
//                getAgentReport(s, stmt);
//                getStaffReport(s, stmt);
//                getCity(s, stmt);
//                getCountry(s,stmt);
//                getCurrency(s, stmt);
//                getAirline(s, stmt);
//                getPackageTour(s, stmt);
//                getProduct(s, stmt);
//                getHotel(s, stmt);
//                getCustomer(s, stmt);
//                getARData(s,stmt);
//                getAPData(s,stmt);
//                getDeptorInvoiceData(s, stmt);
                getInvoiceData(s, stmt);
            } else {
                System.out.println("Database Connect Failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
           if (connect != null) {
               try {
                   connect.close();
               } catch (SQLException ex) {
                   Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
        } 
    }
    
    
    
    public static void getInvoiceData(Statement s,Statement stmt){
        List<MainMigrateModel> list = new ArrayList<MainMigrateModel>();
        UtilityFunction util = new UtilityFunction();
        try {
            ResultSet rs = s.executeQuery(sqlInv1);
            while (rs.next()){
                String id = rs.getString("ID") == null ? "" : new String(rs.getString("ID").getBytes("ISO8859_1"),"TIS-620");
                String invno = rs.getString("INVNO") == null ? "" : new String(rs.getString("INVNO").getBytes("ISO8859_1"),"TIS-620");
                String name = rs.getString("NAME") == null ? "" : new String(rs.getString("NAME").getBytes("ISO8859_1"),"TIS-620");
                String invdate = rs.getString("INVDATE") == null ? "" : new String(rs.getString("INVDATE").getBytes("ISO8859_1"),"TIS-620");            
                String grandtotal = rs.getString("GRAND_TOTAL") == null ? "" : new String(rs.getString("GRAND_TOTAL").getBytes("ISO8859_1"),"TIS-620");    
                String grandtotalgross = rs.getString("GRAND_TOTAL_GROSS") == null ? "" : new String(rs.getString("GRAND_TOTAL_GROSS").getBytes("ISO8859_1"),"TIS-620"); 
                String grandtotalvat = rs.getString("GRAND_TOTAL_VAT") == null ? "" : new String(rs.getString("GRAND_TOTAL_VAT").getBytes("ISO8859_1"),"TIS-620"); 
                String cur = rs.getString("CUR") == null ? "" : new String(rs.getString("CUR").getBytes("ISO8859_1"),"TIS-620"); 
                String department = rs.getString("DEPARTMENT") == null ? "" : new String(rs.getString("DEPARTMENT").getBytes("ISO8859_1"),"TIS-620"); 
                String accno = rs.getString("ACC_NO") == null ? "" : new String(rs.getString("ACC_NO").getBytes("ISO8859_1"),"TIS-620"); 

                MainMigrateModel migrateModel = new MainMigrateModel();
                migrateModel.setId(id);
                migrateModel.setInvno(invno);
                migrateModel.setName(name);
                migrateModel.setInvdate(invdate);
                migrateModel.setGrandtotal(grandtotal);
                migrateModel.setGrandtotalgross(grandtotalgross);
                migrateModel.setGrandtotalvat(grandtotalvat);
                migrateModel.setCur(cur);
                migrateModel.setDepartment(department);
                migrateModel.setAccno(accno);
                list.add(migrateModel);
            }
            
            ResultSet rs2 = s.executeQuery(sqlInv2);
            while (rs2.next()){
                String id = rs2.getString("ID") == null ? "" : new String(rs2.getString("ID").getBytes("ISO8859_1"),"TIS-620");
                String invno = rs2.getString("INVNO") == null ? "" : new String(rs2.getString("INVNO").getBytes("ISO8859_1"),"TIS-620");
                String name = rs2.getString("NAME") == null ? "" : new String(rs2.getString("NAME").getBytes("ISO8859_1"),"TIS-620");
                String invdate = rs2.getString("INVDATE") == null ? "" : new String(rs2.getString("INVDATE").getBytes("ISO8859_1"),"TIS-620");            
                String grandtotal = rs2.getString("GRAND_TOTAL") == null ? "" : new String(rs2.getString("GRAND_TOTAL").getBytes("ISO8859_1"),"TIS-620");    
                String grandtotalgross = rs2.getString("GRAND_TOTAL_GROSS") == null ? "" : new String(rs2.getString("GRAND_TOTAL_GROSS").getBytes("ISO8859_1"),"TIS-620"); 
                String grandtotalvat = rs2.getString("GRAND_TOTAL_VAT") == null ? "" : new String(rs2.getString("GRAND_TOTAL_VAT").getBytes("ISO8859_1"),"TIS-620"); 
                String cur = rs2.getString("CUR") == null ? "" : new String(rs2.getString("CUR").getBytes("ISO8859_1"),"TIS-620"); 
                String department = rs2.getString("DEPARTMENT") == null ? "" : new String(rs2.getString("DEPARTMENT").getBytes("ISO8859_1"),"TIS-620"); 
                String accno = rs2.getString("ACC_NO") == null ? "" : new String(rs2.getString("ACC_NO").getBytes("ISO8859_1"),"TIS-620"); 

                MainMigrateModel migrateModel = new MainMigrateModel();
                migrateModel.setId(id);
                migrateModel.setInvno(invno);
                migrateModel.setName(name);
                migrateModel.setInvdate(invdate);
                migrateModel.setGrandtotal(grandtotal);
                migrateModel.setGrandtotalgross(grandtotalgross);
                migrateModel.setGrandtotalvat(grandtotalvat);
                migrateModel.setCur(cur);
                migrateModel.setDepartment(department);
                migrateModel.setAccno(accno);
                list.add(migrateModel);
            }
            
            System.out.println(" list.size() ::: " + list.size());
        } catch (SQLException e ) {
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try { 
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        ExportInvoiceReport(list);
    }
    
    public static void ExportInvoiceReport(List<MainMigrateModel> listInv){
        UtilityExcelFunction excelFunction = new UtilityExcelFunction();
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFCellStyle styleC1 = wb.createCellStyle();
        // Set align Text
        HSSFCellStyle styleC21 = wb.createCellStyle();
        styleC21.setAlignment(styleC21.ALIGN_RIGHT);
        HSSFCellStyle styleC22 = wb.createCellStyle();
        styleC22.setAlignment(styleC22.ALIGN_LEFT);

        // Header Table
        HSSFCellStyle styleC3Center = wb.createCellStyle();
        styleC3Center.setFont(excelFunction.getHeaderTable(wb.createFont()));
        styleC3Center.setAlignment(styleC3Center.ALIGN_CENTER);
        styleC3Center.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        styleC3Center.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        
        HSSFDataFormat currency = wb.createDataFormat();
        HSSFCellStyle styleC23 = wb.createCellStyle();
        styleC23.setAlignment(styleC23.ALIGN_CENTER);
        HSSFCellStyle styleC24 = wb.createCellStyle();
        styleC24.setAlignment(styleC24.ALIGN_LEFT);
        HSSFCellStyle styleC25 = wb.createCellStyle();
        styleC25.setAlignment(styleC25.ALIGN_RIGHT);
        
        HSSFSheet sheet = wb.createSheet("Invoice");

        HSSFRow row2 = sheet.createRow(0);
        HSSFCell cell20 = row2.createCell(0);
        cell20.setCellValue("ID");
        cell20.setCellStyle(styleC3Center);
        HSSFCell cell21 = row2.createCell(1);
        cell21.setCellValue("INV NO");
        cell21.setCellStyle(styleC3Center);
        HSSFCell cell22 = row2.createCell(2);
        cell22.setCellValue("NAME");
        cell22.setCellStyle(styleC3Center);
        HSSFCell cell23 = row2.createCell(3);
        cell23.setCellValue("INV DATE");
        cell23.setCellStyle(styleC3Center);
        HSSFCell cell24 = row2.createCell(4);
        cell24.setCellValue("GRAND TOTAL");
        cell24.setCellStyle(styleC3Center);
        HSSFCell cell25 = row2.createCell(5);
        cell25.setCellValue("GRAND TOTAL GROSS");
        cell25.setCellStyle(styleC3Center);
        HSSFCell cell26 = row2.createCell(6);
        cell26.setCellValue("GRAND TOTAL VAT");
        cell26.setCellStyle(styleC3Center);
        HSSFCell cell27 = row2.createCell(7);
        cell27.setCellValue("CUR");
        cell27.setCellStyle(styleC3Center);
        HSSFCell cell28 = row2.createCell(8);
        cell28.setCellValue("DEPARTMENT");
        cell28.setCellStyle(styleC3Center);
        HSSFCell cell29 = row2.createCell(9);
        cell29.setCellValue("ACC NO");
        cell29.setCellStyle(styleC3Center);

        if(listInv != null){
            int count = 1 ;
            for(int i=0;i<listInv.size();i++){
                MainMigrateModel data = (MainMigrateModel)listInv.get(i);
                HSSFRow row = sheet.createRow(count + i);
                HSSFCell cell0 = row.createCell(0);
                cell0.setCellValue(data.getId());
                cell0.setCellStyle(styleC24);
             HSSFCell cell1 = row.createCell(1);
                cell1.setCellValue(data.getInvno());
                cell1.setCellStyle(styleC24);
             HSSFCell cell13 = row.createCell(2);
                cell13.setCellValue(data.getName());
                cell13.setCellStyle(styleC24);  
             HSSFCell cell2 = row.createCell(3);
                cell2.setCellValue(data.getInvdate());
                cell2.setCellStyle(styleC24);   
             HSSFCell cell3= row.createCell(4);
                cell3.setCellValue(data.getGrandtotal());
                cell3.setCellStyle(styleC25);
             HSSFCell cell4 = row.createCell(5);
                cell4.setCellValue(data.getGrandtotalgross());
                cell4.setCellStyle(styleC25);   
             HSSFCell cell5 = row.createCell(6);
                cell5.setCellValue(data.getGrandtotalvat());
                cell5.setCellStyle(styleC25);
            HSSFCell cell6 = row.createCell(7);
                cell6.setCellValue(data.getCur());
                cell6.setCellStyle(styleC23);
            HSSFCell cell7 = row.createCell(8);
                cell7.setCellValue(data.getDepartment());
                cell7.setCellStyle(styleC24);
            HSSFCell cell8 = row.createCell(9);
                cell8.setCellValue(data.getAccno());
                cell8.setCellStyle(styleC24);
            }
        }
        for(int x=0;x<10;x++){
            sheet.autoSizeColumn(x);
        }
        sheet.setColumnWidth(2, 256*30);
        exportFileExcel("Invoice",wb);
    }
    
    
    public static void getDeptorInvoiceData(Statement s,Statement stmt){
        List<MainMigrateModel> list = new ArrayList<MainMigrateModel>();
        List<MainMigrateModel> listInv = new ArrayList<MainMigrateModel>();
        UtilityFunction util = new UtilityFunction();
        
        BufferedReader br = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader("C:\\Users\\Jittima\\Desktop\\deptor_invoice_all.txt"));
            while((sCurrentLine = br.readLine()) != null) {
                String data[] = sCurrentLine.split("\\t");
                MainMigrateModel migrateModel = new MainMigrateModel();
                migrateModel.setInvoiceno(String.valueOf(data[8]));
                migrateModel.setInvoicedate(String.valueOf(data[9]));
//                migrateModel.setInvoicename(new String((data[10]).getBytes("ISO8859_1"),"TIS-620")); 
//                migrateModel.setInvoicename(String.valueOf(data[10]));
                migrateModel.setInvoicedetail(String.valueOf(data[11]));
                migrateModel.setInvoiceamount(String.valueOf(data[12]));
                migrateModel.setReceiveno(String.valueOf(data[13]));
                migrateModel.setReceiveamount(String.valueOf(data[14]));
                migrateModel.setRemainamount(String.valueOf(data[15]));
                list.add(migrateModel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if(list != null){
            String sql = "";
            for(int i = 0 ; i < list.size() ; i ++){ 
                sql = " SELECT INV.inv_no, inv.INV_NAME , CASE WHEN (agt.code IS NULL) THEN 'DUMMY' ELSE agt.code END AS CODE, INV.REF_DEPARTMENT AS department, ( SELECT SUM ( ROUND ( CASE WHEN INVD1.VAT IS NOT NULL THEN INVD1.AMOUNT - INVD1.AMOUNT * 100 / (100 + INVD1.VAT) ELSE 0 END, 2 )) FROM \"TRAVOX3\".AC_INVOICE_DETAIL invd1 WHERE invd1.AC_INVOICE_ID = INV.\"ID\" ) AS grand_total_vatamt FROM \"TRAVOX3\".\"AC_INVOICE\" inv INNER JOIN \"TRAVOX3\".AC_INVOICE_DETAIL invd ON INVD.AC_INVOICE_ID = INV.\"ID\" LEFT JOIN ( SELECT NAME, MIN (code) AS code FROM \"TRAVOX3\".AGENT GROUP BY NAME ) agt ON agt. NAME = inv.INV_NAME WHERE inv.inv_no = '" +list.get(i).getInvoiceno()+ "' " ;
                MainMigrateModel migrateModel = new MainMigrateModel();
                migrateModel.setInvoiceno(list.get(i).getInvoiceno());
                migrateModel.setInvoicedate(list.get(i).getInvoicedate());
//                migrateModel.setInvoicename(list.get(i).getInvoicename());
                migrateModel.setInvoicedetail(list.get(i).getInvoicedetail());
                migrateModel.setInvoiceamount(list.get(i).getInvoiceamount());
                migrateModel.setReceiveno(list.get(i).getReceiveno());
                migrateModel.setReceiveamount(list.get(i).getReceiveamount());
                migrateModel.setRemainamount(list.get(i).getRemainamount());
                try {
                    ResultSet rs = s.executeQuery(sql);
                    while (rs.next()){       
                        String code = rs.getString("CODE") == null ? "" : new String(rs.getString("CODE"));
                        String department = rs.getString("DEPARTMENT") == null ? "" : new String(rs.getString("DEPARTMENT"));
                        String grandtotal = rs.getString("GRAND_TOTAL_VATAMT") == null ? "" : new String(rs.getString("GRAND_TOTAL_VATAMT"));
                        String invname = rs.getString("INV_NAME") == null ? "" : new String(rs.getString("INV_NAME").getBytes("ISO8859_1"),"TIS-620");
                         
                        migrateModel.setCode(code);
                        migrateModel.setDepartment(department);
                        migrateModel.setGrandtotal(grandtotal);
                        migrateModel.setInvoicename(invname);
                    }
                    listInv.add(migrateModel);
                } catch (SQLException e ) {
            
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    if (stmt != null) {
                        try { 
                            stmt.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
            System.out.println(" listInv.size():: " + listInv.size());
            ExportDeptorInvoiceReport(listInv);
        }
    }
    
    public static void ExportDeptorInvoiceReport(List<MainMigrateModel> listInv){
        UtilityExcelFunction excelFunction = new UtilityExcelFunction();
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFCellStyle styleC1 = wb.createCellStyle();
        // Set align Text
        HSSFCellStyle styleC21 = wb.createCellStyle();
        styleC21.setAlignment(styleC21.ALIGN_RIGHT);
        HSSFCellStyle styleC22 = wb.createCellStyle();
        styleC22.setAlignment(styleC22.ALIGN_LEFT);

        // Header Table
        HSSFCellStyle styleC3Center = wb.createCellStyle();
        styleC3Center.setFont(excelFunction.getHeaderTable(wb.createFont()));
        styleC3Center.setAlignment(styleC3Center.ALIGN_CENTER);
        styleC3Center.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        styleC3Center.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        
        HSSFDataFormat currency = wb.createDataFormat();
        HSSFCellStyle styleC23 = wb.createCellStyle();
        styleC23.setAlignment(styleC23.ALIGN_CENTER);
        HSSFCellStyle styleC24 = wb.createCellStyle();
        styleC24.setAlignment(styleC24.ALIGN_LEFT);
        HSSFCellStyle styleC25 = wb.createCellStyle();
        styleC25.setAlignment(styleC25.ALIGN_RIGHT);
//        styleC25.setDataFormat(currency.getFormat("#,##0.00"));
        
        HSSFSheet sheet = wb.createSheet("DeptorInvoice");
        
        HSSFRow row2 = sheet.createRow(0);
        HSSFCell cell20 = row2.createCell(0);
        cell20.setCellValue("INV NO");
        cell20.setCellStyle(styleC3Center);
        HSSFCell cell21 = row2.createCell(1);
        cell21.setCellValue("DATE");
        cell21.setCellStyle(styleC3Center);
        HSSFCell cell22 = row2.createCell(2);
        cell22.setCellValue("NAME");
        cell22.setCellStyle(styleC3Center);
        HSSFCell cell23 = row2.createCell(3);
        cell23.setCellValue("DETAIL");
        cell23.setCellStyle(styleC3Center);
        HSSFCell cell24 = row2.createCell(4);
        cell24.setCellValue("INV AMOUNT");
        cell24.setCellStyle(styleC3Center);
        HSSFCell cell25 = row2.createCell(5);
        cell25.setCellValue("RECEIVE NO");
        cell25.setCellStyle(styleC3Center);
        HSSFCell cell26 = row2.createCell(6);
        cell26.setCellValue("RECEIVE AMOUNT");
        cell26.setCellStyle(styleC3Center);
        HSSFCell cell27 = row2.createCell(7);
        cell27.setCellValue("REMAIN AMOUNT");
        cell27.setCellStyle(styleC3Center);
        HSSFCell cell28 = row2.createCell(8);
        cell28.setCellValue("CODE");
        cell28.setCellStyle(styleC3Center);
        HSSFCell cell29 = row2.createCell(9);
        cell29.setCellValue("DEPARTMENT");
        cell29.setCellStyle(styleC3Center);
        HSSFCell cell30 = row2.createCell(10);
        cell30.setCellValue("GRAND TOTAL VATAMT");
        cell30.setCellStyle(styleC3Center);
        
        if(listInv != null){
            int count = 1 ;
            for(int i=0;i<listInv.size();i++){
                MainMigrateModel data = (MainMigrateModel)listInv.get(i);
                HSSFRow row = sheet.createRow(count + i);
                HSSFCell cell0 = row.createCell(0);
                cell0.setCellValue(data.getInvoiceno());
                cell0.setCellStyle(styleC24);
             HSSFCell cell1 = row.createCell(1);
                cell1.setCellValue(data.getInvoicedate());
                cell1.setCellStyle(styleC24);
             HSSFCell cell13 = row.createCell(2);
                cell13.setCellValue(data.getInvoicename());
                cell13.setCellStyle(styleC24);  
             HSSFCell cell2 = row.createCell(3);
                cell2.setCellValue(data.getInvoicedetail());
                cell2.setCellStyle(styleC24);   
             HSSFCell cell3= row.createCell(4);
                cell3.setCellValue(data.getInvoiceamount());
                cell3.setCellStyle(styleC25);
             HSSFCell cell4 = row.createCell(5);
                cell4.setCellValue(data.getReceiveno());
                cell4.setCellStyle(styleC24);   
             HSSFCell cell5 = row.createCell(6);
                cell5.setCellValue(data.getReceiveamount());
                cell5.setCellStyle(styleC25);
            HSSFCell cell6 = row.createCell(7);
                cell6.setCellValue(data.getRemainamount());
                cell6.setCellStyle(styleC25);
            HSSFCell cell7 = row.createCell(8);
                cell7.setCellValue(data.getCode());
                cell7.setCellStyle(styleC24);
            HSSFCell cell8 = row.createCell(9);
                cell8.setCellValue(data.getDepartment());
                cell8.setCellStyle(styleC24);
            HSSFCell cell9 = row.createCell(10);
                cell9.setCellValue(data.getGrandtotal());
                cell9.setCellStyle(styleC25);    
            }
        }
        for(int x=0;x<11;x++){
            sheet.autoSizeColumn(x);
        }
        sheet.setColumnWidth(2, 256*30);
        exportFileExcel("DeptorInvoice",wb);
    }
    
    
    public static void getAPData(Statement s,Statement stmt){
        List<MainMigrateModel> list = new ArrayList<MainMigrateModel>();
        List<MainMigrateModel> listAP = new ArrayList<MainMigrateModel>();
        UtilityFunction util = new UtilityFunction();
        try {
            ResultSet rs = s.executeQuery(sqlAP);
            while (rs.next()){       
                String payid = rs.getString("PAYID") == null ? "" : new String(rs.getString("PAYID").getBytes("ISO8859_1"),"TIS-620");
                String payno = rs.getString("PAY_NO") == null ? "" : new String(rs.getString("PAY_NO").getBytes("ISO8859_1"),"TIS-620");
                String apcode = rs.getString("AP_CODE") == null ? "" : new String(rs.getString("AP_CODE").getBytes("ISO8859_1"),"TIS-620");
                String name = rs.getString("NAME") == null ? "" : new String(rs.getString("NAME").getBytes("ISO8859_1"),"TIS-620");            
                String paydate = rs.getString("PAY_DATE") == null ? "" : new String(rs.getString("PAY_DATE").getBytes("ISO8859_1"),"TIS-620");    
                String department = rs.getString("DEPARTMENT") == null ? "" : new String(rs.getString("DEPARTMENT").getBytes("ISO8859_1"),"TIS-620"); 
                String vattype = rs.getString("VATTYPE") == null ? "" : new String(rs.getString("VATTYPE").getBytes("ISO8859_1"),"TIS-620"); 
                
                MainMigrateModel migrateModel = new MainMigrateModel();
                migrateModel.setPayid(payid);
                migrateModel.setPayno(payno);
                migrateModel.setApCode(apcode);
                migrateModel.setApname(name);
                migrateModel.setPaydate(paydate);
                migrateModel.setDepartment(department);
                migrateModel.setVattype(vattype);
                list.add(migrateModel);
            }
            System.out.println(" list.size() ::: " + list.size());
        } catch (SQLException e ) {
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try { 
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        Connection connect = null;
        Statement stm = null; 
        if(list != null){
            connect = MySqlConnection.getConnection();
            try {
                stm = connect.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
            }
            String sql = "";
            for(int i = 0 ; i < list.size() ; i ++){ 
                sql = " SELECT tax_no , branch , branch_no  FROM `agent` where `code` = '" +list.get(i).getApCode()+ "' " ;
                MainMigrateModel migrateModel = new MainMigrateModel();
                migrateModel.setPayid(list.get(i).getPayid());
                migrateModel.setPayno(list.get(i).getPayno());
                migrateModel.setApCode(list.get(i).getApCode());
                migrateModel.setApname(list.get(i).getApname());
                migrateModel.setPaydate(list.get(i).getPaydate());
                migrateModel.setDepartment(list.get(i).getDepartment());
                migrateModel.setVattype(list.get(i).getVattype());
                try {
                    if(!"DUMMMY".equalsIgnoreCase(list.get(i).getApCode())){
                        ResultSet rs = stm.executeQuery(sql);
                        while (rs.next()){       
                            String taxno = rs.getString("tax_no") == null ? "" : new String(rs.getString("tax_no"));
                            String branch = rs.getString("branch") == null ? "" : new String(rs.getString("branch"));
                            String branchno = rs.getString("branch_no") == null ? "" : new String(rs.getString("branch_no"));

                            migrateModel.setTaxno(taxno);
                            migrateModel.setBranch(branch);
                            migrateModel.setBranchno(branchno);

                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
                listAP.add(migrateModel);
            }
            System.out.println(" listAP.size() " + listAP.size());
            ExportAPReport(listAP);
        }
    }

    public static void ExportAPReport(List<MainMigrateModel> listAP){
        UtilityExcelFunction excelFunction = new UtilityExcelFunction();
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFCellStyle styleC1 = wb.createCellStyle();
        // Set align Text
        HSSFCellStyle styleC21 = wb.createCellStyle();
        styleC21.setAlignment(styleC21.ALIGN_RIGHT);
        HSSFCellStyle styleC22 = wb.createCellStyle();
        styleC22.setAlignment(styleC22.ALIGN_LEFT);

        // Header Table
        HSSFCellStyle styleC3Center = wb.createCellStyle();
        styleC3Center.setFont(excelFunction.getHeaderTable(wb.createFont()));
        styleC3Center.setAlignment(styleC3Center.ALIGN_CENTER);
        styleC3Center.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        styleC3Center.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        
        HSSFDataFormat currency = wb.createDataFormat();
        HSSFCellStyle styleC23 = wb.createCellStyle();
        styleC23.setAlignment(styleC23.ALIGN_CENTER);
        styleC23.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC23.setBorderRight(HSSFCellStyle.BORDER_THIN);
        HSSFCellStyle styleC24 = wb.createCellStyle();
        styleC24.setAlignment(styleC24.ALIGN_LEFT);
        HSSFCellStyle styleC25 = wb.createCellStyle();
        styleC25.setAlignment(styleC25.ALIGN_RIGHT);
        styleC25.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC25.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC25.setDataFormat(currency.getFormat("#,##0.00"));
        
        String datetemp = "";
        if(listAP != null){
            HSSFSheet sheet = wb.createSheet(listAP.get(0).getPaydate().substring(3,10).replaceAll("-", ""));
            int count = 1 ;
            for(int i=0;i<listAP.size();i++){
                MainMigrateModel data = (MainMigrateModel)listAP.get(i);
                if(!"".equalsIgnoreCase(datetemp) && !datetemp.equalsIgnoreCase(data.getPaydate().substring(3,10))){
                    sheet = wb.createSheet(data.getPaydate().substring(3,10).replaceAll("-", ""));
                    HSSFRow row2 = sheet.createRow(0);
                    HSSFCell cell20 = row2.createCell(0);
                    cell20.setCellValue("PAYID");
                    cell20.setCellStyle(styleC3Center);
                    HSSFCell cell21 = row2.createCell(1);
                    cell21.setCellValue("PAY NO");
                    cell21.setCellStyle(styleC3Center);
                    HSSFCell cell22 = row2.createCell(2);
                    cell22.setCellValue("AP CODE");
                    cell22.setCellStyle(styleC3Center);
                    HSSFCell cell23 = row2.createCell(3);
                    cell23.setCellValue("NAME");
                    cell23.setCellStyle(styleC3Center);
                    HSSFCell cell24 = row2.createCell(4);
                    cell24.setCellValue("PAY DATE");
                    cell24.setCellStyle(styleC3Center);
                    HSSFCell cell25 = row2.createCell(5);
                    cell25.setCellValue("DEPARTMENT");
                    cell25.setCellStyle(styleC3Center);
                    HSSFCell cell26 = row2.createCell(6);
                    cell26.setCellValue("VAT TYPE");
                    cell26.setCellStyle(styleC3Center);
                    HSSFCell cell27 = row2.createCell(7);
                    cell27.setCellValue("TAX NO");
                    cell27.setCellStyle(styleC3Center);
                    HSSFCell cell28 = row2.createCell(8);
                    cell28.setCellValue("BRANCH");
                    cell28.setCellStyle(styleC3Center);
                    HSSFCell cell29 = row2.createCell(9);
                    cell29.setCellValue("BRANCH NO");
                    cell29.setCellStyle(styleC3Center);
                    count = 1 ;
                    sheet.setColumnWidth(0, 256*15);
                    sheet.setColumnWidth(1, 256*15);
                    sheet.setColumnWidth(2, 256*15);
                    sheet.setColumnWidth(3, 256*25);
                    sheet.setColumnWidth(4, 256*15);
                    sheet.setColumnWidth(5, 256*15);
                    sheet.setColumnWidth(6, 256*15);
                    sheet.setColumnWidth(7, 256*15);
                    sheet.setColumnWidth(8, 256*15);
                    sheet.setColumnWidth(9, 256*15);
                }else if("".equalsIgnoreCase(datetemp)){
                    HSSFRow row2 = sheet.createRow(0);
                    HSSFCell cell20 = row2.createCell(0);
                    cell20.setCellValue("PAYID");
                    cell20.setCellStyle(styleC3Center);
                    HSSFCell cell21 = row2.createCell(1);
                    cell21.setCellValue("PAY NO");
                    cell21.setCellStyle(styleC3Center);
                    HSSFCell cell22 = row2.createCell(2);
                    cell22.setCellValue("AP CODE");
                    cell22.setCellStyle(styleC3Center);
                    HSSFCell cell23 = row2.createCell(3);
                    cell23.setCellValue("NAME");
                    cell23.setCellStyle(styleC3Center);
                    HSSFCell cell24 = row2.createCell(4);
                    cell24.setCellValue("PAY DATE");
                    cell24.setCellStyle(styleC3Center);
                    HSSFCell cell25 = row2.createCell(5);
                    cell25.setCellValue("DEPARTMENT");
                    cell25.setCellStyle(styleC3Center);
                    HSSFCell cell26 = row2.createCell(6);
                    cell26.setCellValue("VAT TYPE");
                    cell26.setCellStyle(styleC3Center);
                    HSSFCell cell27 = row2.createCell(7);
                    cell27.setCellValue("TAX NO");
                    cell27.setCellStyle(styleC3Center);
                    HSSFCell cell28 = row2.createCell(8);
                    cell28.setCellValue("BRANCH");
                    cell28.setCellStyle(styleC3Center);
                    HSSFCell cell29 = row2.createCell(9);
                    cell29.setCellValue("BRANCH NO");
                    cell29.setCellStyle(styleC3Center);

                    sheet.setColumnWidth(0, 256*15);
                    sheet.setColumnWidth(1, 256*15);
                    sheet.setColumnWidth(2, 256*15);
                    sheet.setColumnWidth(3, 256*25);
                    sheet.setColumnWidth(4, 256*15);
                    sheet.setColumnWidth(5, 256*15);
                    sheet.setColumnWidth(6, 256*15);
                    sheet.setColumnWidth(7, 256*15);
                    sheet.setColumnWidth(8, 256*15);
                    sheet.setColumnWidth(9, 256*15);

                }

                HSSFRow row = sheet.createRow(count);
                HSSFCell cell0 = row.createCell(0);
                cell0.setCellValue(data.getPayid());
                cell0.setCellStyle(styleC24);
             HSSFCell cell1 = row.createCell(1);
                cell1.setCellValue(data.getPayno());
                cell1.setCellStyle(styleC24);
             HSSFCell cell13 = row.createCell(2);
                cell13.setCellValue(data.getApCode());
                cell13.setCellStyle(styleC24);  
             HSSFCell cell2 = row.createCell(3);
                cell2.setCellValue(String.valueOf(data.getApname()));
                cell2.setCellStyle(styleC24);   
             HSSFCell cell3= row.createCell(4);
                cell3.setCellValue(String.valueOf(data.getPaydate()));
                cell3.setCellStyle(styleC24);
             HSSFCell cell4 = row.createCell(5);
                cell4.setCellValue(data.getDepartment());
                cell4.setCellStyle(styleC24);   
             HSSFCell cell5 = row.createCell(6);
                cell5.setCellValue(data.getVattype());
                cell5.setCellStyle(styleC24);
            HSSFCell cell6 = row.createCell(7);
                cell6.setCellValue(data.getTaxno());
                cell6.setCellStyle(styleC24);
            HSSFCell cell7 = row.createCell(8);
                cell7.setCellValue(data.getBranch());
                cell7.setCellStyle(styleC24);
            HSSFCell cell8 = row.createCell(9);
                cell8.setCellValue(data.getBranchno());
                cell8.setCellStyle(styleC24);
                datetemp = data.getPaydate().substring(3,10);
                count ++ ;
            }
        }
        exportFileExcel("APReport",wb);
    }
    
    public static void getARData(Statement s,Statement stmt){
        List<MainMigrateModel> list = new ArrayList<MainMigrateModel>();
        List<MainMigrateModel> listAR = new ArrayList<MainMigrateModel>();
        UtilityFunction util = new UtilityFunction();
        try {
            ResultSet rs = s.executeQuery(sqlAR);
            while (rs.next()){       
                String code = rs.getString("CODE") == null ? "" : new String(rs.getString("CODE").getBytes("ISO8859_1"),"TIS-620");
                String invname = rs.getString("INV_NAME") == null ? "" : new String(rs.getString("INV_NAME").getBytes("ISO8859_1"),"TIS-620");
                String invno = rs.getString("INV_NO") == null ? "" : new String(rs.getString("INV_NO").getBytes("ISO8859_1"),"TIS-620");
                String invdate = rs.getString("INV_DATE") == null ? "" : new String(rs.getString("INV_DATE").getBytes("ISO8859_1"),"TIS-620");            
                
                MainMigrateModel migrateModel = new MainMigrateModel();
                migrateModel.setCode(code);
                migrateModel.setInvname(invname);
                migrateModel.setInvno(invno);
                migrateModel.setInvdate(invdate);
                list.add(migrateModel);
            }
            
        } catch (SQLException e ) {
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try { 
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        Connection connect = null;
        Statement stm = null; 
        if(list != null){
            connect = MySqlConnection.getConnection();
            try {
                stm = connect.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
            }
            String sql = "";
            for(int i = 0 ; i < list.size() ; i ++){ 
                sql = " SELECT tax_no , branch , branch_no  FROM `agent` where `code` = '" +list.get(i).getCode()+ "' " ;
                MainMigrateModel migrateModel = new MainMigrateModel();
                migrateModel.setCode(list.get(i).getCode());
                migrateModel.setInvname(list.get(i).getInvname());
                migrateModel.setInvno(list.get(i).getInvno());
                migrateModel.setInvdate(list.get(i).getInvdate());
                try {
                    if(!"DUMMMY".equalsIgnoreCase(list.get(i).getCode())){
                        ResultSet rs = stm.executeQuery(sql);
                        while (rs.next()){       
                            String taxno = rs.getString("tax_no") == null ? "" : new String(rs.getString("tax_no"));
                            String branch = rs.getString("branch") == null ? "" : new String(rs.getString("branch"));
                            String branchno = rs.getString("branch_no") == null ? "" : new String(rs.getString("branch_no"));
                            migrateModel.setTaxno(taxno);
                            migrateModel.setBranch(branch);
                            migrateModel.setBranchno(branchno);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
                listAR.add(migrateModel);
            }
            System.out.println(" listAR.size() " + listAR.size());
            ExportARReport(listAR);
        }
    }

    public static void ExportARReport(List<MainMigrateModel> listAR){
        UtilityExcelFunction excelFunction = new UtilityExcelFunction();
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFCellStyle styleC1 = wb.createCellStyle();
        // Set align Text
        HSSFCellStyle styleC21 = wb.createCellStyle();
        styleC21.setAlignment(styleC21.ALIGN_RIGHT);
        HSSFCellStyle styleC22 = wb.createCellStyle();
        styleC22.setAlignment(styleC22.ALIGN_LEFT);
        // Header Table
        HSSFCellStyle styleC3Center = wb.createCellStyle();
        styleC3Center.setFont(excelFunction.getHeaderTable(wb.createFont()));
        styleC3Center.setAlignment(styleC3Center.ALIGN_CENTER);
        styleC3Center.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        styleC3Center.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);

        HSSFDataFormat currency = wb.createDataFormat();
        HSSFCellStyle styleC23 = wb.createCellStyle();
        styleC23.setAlignment(styleC23.ALIGN_CENTER);
        styleC23.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC23.setBorderRight(HSSFCellStyle.BORDER_THIN);
        HSSFCellStyle styleC24 = wb.createCellStyle();
        styleC24.setAlignment(styleC24.ALIGN_LEFT);
        HSSFCellStyle styleC25 = wb.createCellStyle();
        styleC25.setAlignment(styleC25.ALIGN_RIGHT);
        styleC25.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC25.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC25.setDataFormat(currency.getFormat("#,##0.00"));

        String datetemp = "";
        if(listAR != null){
            HSSFSheet sheet = wb.createSheet(listAR.get(0).getInvdate().substring(3,10).replaceAll("-", ""));
            int count = 1 ;
            for(int i=0;i<listAR.size();i++){
                MainMigrateModel data = (MainMigrateModel)listAR.get(i);
                if(!"".equalsIgnoreCase(datetemp) && !datetemp.equalsIgnoreCase(data.getInvdate().substring(3,10))){
                    sheet = wb.createSheet(data.getInvdate().substring(3,10).replaceAll("-", ""));
                    HSSFRow row2 = sheet.createRow(0);
                    HSSFCell cell20 = row2.createCell(0);
                    cell20.setCellValue("CODE");
                    cell20.setCellStyle(styleC3Center);
                    HSSFCell cell21 = row2.createCell(1);
                    cell21.setCellValue("INV NAME");
                    cell21.setCellStyle(styleC3Center);
                    HSSFCell cell22 = row2.createCell(2);
                    cell22.setCellValue("INV NO");
                    cell22.setCellStyle(styleC3Center);
                    HSSFCell cell23 = row2.createCell(3);
                    cell23.setCellValue("INV DATE");
                    cell23.setCellStyle(styleC3Center);
                    HSSFCell cell24 = row2.createCell(4);
                    cell24.setCellValue("TAX NO");
                    cell24.setCellStyle(styleC3Center);
                    HSSFCell cell25 = row2.createCell(5);
                    cell25.setCellValue("BRANCH");
                    cell25.setCellStyle(styleC3Center);
                    HSSFCell cell26 = row2.createCell(6);
                    cell26.setCellValue("BRANCH NO");
                    cell26.setCellStyle(styleC3Center);
                
                    count = 1 ;
                    sheet.setColumnWidth(0, 256*15);
                    sheet.setColumnWidth(1, 256*25);
                    sheet.setColumnWidth(2, 256*15);
                    sheet.setColumnWidth(3, 256*15);
                    sheet.setColumnWidth(4, 256*15);
                    sheet.setColumnWidth(5, 256*15);
                    sheet.setColumnWidth(6, 256*15);
                }else if("".equalsIgnoreCase(datetemp)){
                    HSSFRow row2 = sheet.createRow(0);
                    HSSFCell cell20 = row2.createCell(0);
                    cell20.setCellValue("CODE");
                    cell20.setCellStyle(styleC3Center);
                    HSSFCell cell21 = row2.createCell(1);
                    cell21.setCellValue("INV NAME");
                    cell21.setCellStyle(styleC3Center);
                    HSSFCell cell22 = row2.createCell(2);
                    cell22.setCellValue("INV NO");
                    cell22.setCellStyle(styleC3Center);
                    HSSFCell cell23 = row2.createCell(3);
                    cell23.setCellValue("INV DATE");
                    cell23.setCellStyle(styleC3Center);
                    HSSFCell cell24 = row2.createCell(4);
                    cell24.setCellValue("TAX NO");
                    cell24.setCellStyle(styleC3Center);
                    HSSFCell cell25 = row2.createCell(5);
                    cell25.setCellValue("BRANCH");
                    cell25.setCellStyle(styleC3Center);
                    HSSFCell cell26 = row2.createCell(6);
                    cell26.setCellValue("BRANCH NO");
                    cell26.setCellStyle(styleC3Center);
                    
                    sheet.setColumnWidth(0, 256*15);
                    sheet.setColumnWidth(1, 256*25);
                    sheet.setColumnWidth(2, 256*15);
                    sheet.setColumnWidth(3, 256*15);
                    sheet.setColumnWidth(4, 256*15);
                    sheet.setColumnWidth(5, 256*15);
                    sheet.setColumnWidth(6, 256*15);
                }
                
                HSSFRow row = sheet.createRow(count);
                HSSFCell cell0 = row.createCell(0);
                    cell0.setCellValue(data.getCode());
                    cell0.setCellStyle(styleC24);
                HSSFCell cell1 = row.createCell(1);
                    cell1.setCellValue(data.getInvname());
                    cell1.setCellStyle(styleC24);
                HSSFCell cell13 = row.createCell(2);
                    cell13.setCellValue(data.getInvno());
                    cell13.setCellStyle(styleC24);  
                HSSFCell cell2 = row.createCell(3);
                    cell2.setCellValue(String.valueOf(data.getInvdate()));
                    cell2.setCellStyle(styleC24);   
                HSSFCell cell3= row.createCell(4);
                    cell3.setCellValue(data.getTaxno());
                    cell3.setCellStyle(styleC24);
                HSSFCell cell4 = row.createCell(5);
                    cell4.setCellValue(data.getBranch());
                    cell4.setCellStyle(styleC24);   
                HSSFCell cell5 = row.createCell(6);
                    cell5.setCellValue(data.getBranchno());
                    cell5.setCellStyle(styleC24);
                    
                    datetemp = data.getInvdate().substring(3,10);
                    count ++ ;
            }
        }
        exportFileExcel("ARReport",wb);
    }
    
    public static void getCustomer(Statement s,Statement stmt){
        List<MainMigrateModel> list = new ArrayList<MainMigrateModel>();
        UtilityFunction util = new UtilityFunction();
        try {
            ResultSet rs = s.executeQuery(sqlCustomer);
            System.out.println(" Customer ");
            while (rs.next()) {       
                String code = rs.getString("CODE") == null ? "" : rs.getString("CODE");
                String initialname = rs.getString("INITIAL_NAME") == null ? "" : new String(rs.getString("INITIAL_NAME").getBytes("ISO8859_1"),"TIS-620");
                String firstName = rs.getString("FIRST_NAME") == null ? "" : new String(rs.getString("FIRST_NAME").getBytes("ISO8859_1"),"TIS-620");
                String lastName = rs.getString("LAST_NAME") == null ? "" : new String(rs.getString("LAST_NAME").getBytes("ISO8859_1"),"TIS-620");
                String nationality = rs.getString("NATIONALITY") == null ? "" : new String(rs.getString("NATIONALITY").getBytes("ISO8859_1"),"TIS-620");
                String birthDate = rs.getString("BIRTH") == null ? "" : rs.getString("BIRTH");
                String sex = rs.getString("SEX") == null ? "" : new String(rs.getString("SEX").getBytes("ISO8859_1"),"TIS-620");
                String postalAddress = rs.getString("POSTAL_ADDRESS") == null ? "" : new String(rs.getString("POSTAL_ADDRESS").getBytes("ISO8859_1"),"TIS-620");
                String postalTel = rs.getString("POSTAL_TEL") == null ? "" : new String(rs.getString("POSTAL_TEL").getBytes("ISO8859_1"),"TIS-620");
                String postalEmail = rs.getString("POSTAL_EMAIL") == null ? "" : rs.getString("POSTAL_EMAIL");
                String passportNo = rs.getString("PASSPORT_NO") == null ? "" : rs.getString("PASSPORT_NO");
                String warning = rs.getString("WARNING") == null ? "" : rs.getString("WARNING");
                String citizenNo = rs.getString("CITIZEN_NO") == null ? "" : rs.getString("CITIZEN_NO");
                String mobileNo = rs.getString("MOBILE_NO") == null ? "" : rs.getString("MOBILE_NO");
                String firstNameJapan = rs.getString("FIRST_NAME_JAPAN") == null ? "" : new String(rs.getString("FIRST_NAME_JAPAN").getBytes("ISO8859_1"),"TIS-620");
                String lastNameJapan= rs.getString("LAST_NAME_JAPAN") == null ? "" : new String(rs.getString("LAST_NAME_JAPAN").getBytes("ISO8859_1"),"TIS-620");

                MainMigrateModel migrateModel = new MainMigrateModel();
                migrateModel.setCode(code);
                String initial = null;
                if("-43".equalsIgnoreCase(initialname)){
                    initial = "1";
                }else if("-44".equalsIgnoreCase(initialname)){
                    initial = "2";
                }else if("-45".equalsIgnoreCase(initialname)){
                    initial = "3";
                }else if("-46".equalsIgnoreCase(initialname)){
                    initial = "4";
                }else if("-47".equalsIgnoreCase(initialname)){
                    initial = "5";
                }else if("-48".equalsIgnoreCase(initialname)){
                    initial = "6";
                }
                MInitialname mInitialname = new MInitialname();
                mInitialname.setId(initial);
                migrateModel.setInitialname(mInitialname);
                migrateModel.setFirstName(firstName);
                migrateModel.setLastName(lastName);
                migrateModel.setNationality(nationality);
                migrateModel.setBirthDate("".equalsIgnoreCase(birthDate) ? null : util.convertStringToDate(birthDate));
                migrateModel.setSex(sex);
                migrateModel.setPostalAddress(postalAddress);
                migrateModel.setPostalTel(postalTel);
                migrateModel.setPostalEmail(postalEmail);
                migrateModel.setPassportNo(passportNo);
                migrateModel.setWarning(warning);
                migrateModel.setCitizenNo(citizenNo);
                migrateModel.setMobileNo(mobileNo);
                migrateModel.setFirstNameJapan(firstNameJapan);
                migrateModel.setLastNameJapan(lastNameJapan);
                list.add(migrateModel);
            }
        } catch (SQLException e ) {
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try { 
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        Connection connect = null;
        Statement stm = null; 
        if(list != null){
            connect = MySqlConnection.getConnection();
            try {
                stm = connect.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
            }
            String sql = "";
            
            System.out.println(" customer size :: "+ list.size());
            for(int i = 0 ; i< list.size(); i ++){ 
                sql = " INSERT INTO `customer` ( `code`, `initial_name`, `first_name`, `last_name`, `nationality`, `birth_date`, `sex`, `address`, `tel`, `email`, `passport_no`, `remark`, `personal_id`, `phone`, `first_name_japan`, `last_name_japan` ) "
                    + "VALUES ('"+list.get(i).getCode().replaceAll("'", " ")+"',"
                    + list.get(i).getInitialname().getId() +",'"
                    + list.get(i).getFirstName().replaceAll("'", " ")+"','"
                    + list.get(i).getLastName().replaceAll("'", " ")+"','"
                    + list.get(i).getNationality().replaceAll("'", " ") +"',"
                    + list.get(i).getBirthDate()+",'"
                    + list.get(i).getSex().replaceAll("'", " ") +"','"
                    + list.get(i).getPostalAddress().replaceAll("'", " ") +"','"
                    + list.get(i).getPostalTel().replaceAll("'", " ") +"','"
                    + list.get(i).getPostalEmail().replaceAll("'", " ") +"','"
                    + list.get(i).getPassportNo().replaceAll("'", " ") +"','"
                    + list.get(i).getWarning().replaceAll("'", " ") +"','"
                    + list.get(i).getCitizenNo().replaceAll("'", " ") +"','"
                    + list.get(i).getMobileNo().replaceAll("'", " ") +"','"
                    + list.get(i).getFirstNameJapan().replaceAll("'", " ") +"','"
                    + list.get(i).getLastNameJapan().replaceAll("'", " ")+"' ) ";
//                System.out.println(" sql :: " + sql);
                try {
                    stm.executeUpdate(sql);
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static void getHotel(Statement s,Statement stmt){
        List<MainMigrateModel> list = new ArrayList<MainMigrateModel>();
        try {
            ResultSet rs = s.executeQuery(sqlHotel);
            while (rs.next()) {            
                String code = rs.getString("CODE") == null ? "" : rs.getString("CODE");
                String name = rs.getString("NAME") == null ? "" : new String(rs.getString("NAME").getBytes("ISO8859_1"),"TIS-620");
                String description = rs.getString("DESCRIPTION") == null ? "" : new String(rs.getString("DESCRIPTION").getBytes("ISO8859_1"),"TIS-620");
                String address = rs.getString("ADDRESS") == null ? "" : new String(rs.getString("ADDRESS").getBytes("ISO8859_1"),"TIS-620");
                String tel = rs.getString("TEL") == null ? "" : new String(rs.getString("TEL").getBytes("ISO8859_1"),"TIS-620");
                String fax = rs.getString("FAX") == null ? "" :rs.getString("FAX");
                String email = rs.getString("EMAIL") == null ? "" :rs.getString("EMAIL");
                String web = rs.getString("WEB") == null ? "" :rs.getString("WEB");
                String countrycode = rs.getString("COUNTRY_CODE") == null ? "" :rs.getString("COUNTRY_CODE");
                String citycode = rs.getString("CITY_CODE") == null ? "" :rs.getString("CITY_CODE");
                
                MainMigrateModel hotel = new MainMigrateModel();
                hotel.setCode(code);
                hotel.setName(name);
                hotel.setRemark(description);
                hotel.setAddress(address);
                hotel.setTelNo(tel);
                hotel.setFax(fax);
                hotel.setEmail(email);
                hotel.setWeb(web);
                MCountry mCountry = new MCountry();
                mCountry.setCode(countrycode);
                hotel.setCountry(mCountry);
                MCity mCity = new MCity();
                mCity.setCode(citycode);
                hotel.setCity(mCity);
                list.add(hotel);
            }
        } catch (SQLException e ) {
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try { 
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        Connection connect = null;
        Statement stm = null; 
        if(list != null){
            connect = MySqlConnection.getConnection();
            try {
                stm = connect.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
            }
            String sql = "";
            
            System.out.println(" hotel size :: "+ list.size());
            for(int i = 0 ; i< list.size() ; i ++){ 
                String city = null;
                String country = null;
                if(list.get(i).getCountry() != null && !"".equalsIgnoreCase(list.get(i).getCountry().getCode())){
                    country = " (select MAX(id) from m_country where `code` ='"+list.get(i).getCountry().getCode()+"') ";
                }
                if(list.get(i).getCity() != null && !"".equalsIgnoreCase(list.get(i).getCity().getCode())){
                    city = " (select MAX(id) from m_city where `code`='"+list.get(i).getCity().getCode()+"') ";
                }
                sql = " INSERT INTO  `hotel` (`code`,`name`,`remark`,`address`,`tel_no`,`fax`,`email`,`web`,`country`,`city`)  "
                    + "VALUES ('"+list.get(i).getCode()+"','"+list.get(i).getName().replaceAll("'", " ")+"','"+list.get(i).getRemark().replaceAll("'", " ")+"','"
                    + list.get(i).getAddress().replaceAll("'", " ")+"','"+list.get(i).getTelNo()+"','"+list.get(i).getFax()+"','"+list.get(i).getEmail()+"','"
                    + list.get(i).getWeb()+"',"+country+" , " +city+ "  ) ";
//                System.out.println(" sql ::" +sql );
                try {
                    stm.executeUpdate(sql);
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static void getProduct(Statement s,Statement stmt){
        List<MainMigrateModel> list = new ArrayList<MainMigrateModel>();
        try {
            ResultSet rs = s.executeQuery(sqlProduct);
            while (rs.next()) {
                String code = rs.getString("CODE") == null ? "" : rs.getString("CODE");
                String name = rs.getString("NAME") == null ? "" : new String(rs.getString("NAME").getBytes("ISO8859_1"),"TIS-620");
                String description = rs.getString("DESCRIPTION") == null ? "" : new String(rs.getString("DESCRIPTION").getBytes("ISO8859_1"),"TIS-620");
                String remarks = rs.getString("REMARKS") == null ? "" :rs.getString("REMARKS");
                String productype = rs.getString("PRODUCT_TYPE") == null ? null :rs.getString("PRODUCT_TYPE");

                MainMigrateModel product = new MainMigrateModel();
                product.setCode(code);
                product.setName(name);
                product.setDescription(description);
                product.setRemark(remarks);
                MProductType mProductType = new MProductType();
                mProductType.setId(productype);
                product.setProductType(mProductType);
                list.add(product);
            }
        } catch (SQLException e ) {
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try { 
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        Connection connect = null;
        Statement stm = null; 
        if(list != null){
            connect = MySqlConnection.getConnection();
            try {
                stm = connect.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
            }
            String sql = "";
            System.out.println(" product size :: "+ list.size());
            for(int i = 0 ; i< list.size() ; i ++){               
                sql = " INSERT INTO `product` (`code`,`name`,`description`,`remark`,`product_type`) "
                        + "VALUES ('"+list.get(i).getCode()+"','"+list.get(i).getName().replaceAll("'", " ")+"','"+list.get(i).getDescription().replaceAll("'", " ")+"','"
                        + list.get(i).getRemark().replaceAll("'", " ")+"',"+list.get(i).getProductType().getId()+") ";
                try {
                    stm.executeUpdate(sql);
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static void getPackageTour(Statement s,Statement stmt){
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy");
        UtilityFunction util = new UtilityFunction();
        List<MainMigrateModel> list = new ArrayList<MainMigrateModel>();
        try {
            ResultSet rs = s.executeQuery(sqlPackageTour);
            while (rs.next()) {
                String code = rs.getString("CODE") == null ? "" : rs.getString("CODE");
                String name = rs.getString("NAME") == null ? "" : new String(rs.getString("NAME").getBytes("ISO8859_1"),"TIS-620");
                String remarks = rs.getString("REMARKS") == null ? "" :rs.getString("REMARKS");
                String status = rs.getString("STATUS") == null ? "" :rs.getString("STATUS");
                MainMigrateModel packageTour = new MainMigrateModel();
                packageTour.setCode(code);
                packageTour.setName(name);
                packageTour.setStatus(status);
                packageTour.setRemark(remarks);
                list.add(packageTour);
            }
        } catch (SQLException e ) {
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try { 
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        Connection connect = null;
        Statement stm = null; 
        if(list != null){
            connect = MySqlConnection.getConnection();
            try {
                stm = connect.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
            }
            String sql = "";
            System.out.println(" package_tour size :: "+ list.size());
            for(int i = 0 ; i< list.size() ; i ++){
                sql = " INSERT INTO `package_tour` (`code`,`name`,`remark`,`status`) "
                        + "VALUES ('"+list.get(i).getCode()+"','"+list.get(i).getName().replaceAll("'", " ")+"','"
                        + list.get(i).getRemark()+"','"+list.get(i).getStatus()+"') ";
                try {
                    stm.executeUpdate(sql);
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static void getAirline(Statement s,Statement stmt){
        List<MainMigrateModel> list = new ArrayList<MainMigrateModel>();
        try {
            ResultSet rs = s.executeQuery(sqlAirline);
            while (rs.next()) {
                String code = rs.getString("CODE") == null ? "" : rs.getString("CODE");
                String description = rs.getString("DESCRIPTION") == null ? "" : new String(rs.getString("DESCRIPTION").getBytes("ISO8859_1"),"TIS-620");
                String code3 = rs.getString("CODE_3") == null ? "" :rs.getString("CODE_3");
                MainMigrateModel mAirline = new MainMigrateModel();
                mAirline.setCode(code);
                mAirline.setName(description);
                mAirline.setCode3Letter(code3);
                list.add(mAirline);
            }
        } catch (SQLException e ) {
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try { 
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        Connection connect = null;
        Statement stm = null; 
        if(list != null){
            connect = MySqlConnection.getConnection();
            try {
                stm = connect.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
            }
            String sql = "";
            System.out.println(" m_airline size :: "+ list.size());
            for(int i = 0 ; i< list.size() ; i ++){
                if(list.get(i).getCode().length() > 5 ){
                    sql = " INSERT INTO `m_airline` (`code`,`name`,`code_3_letter` ) VALUES ('"+list.get(i).getCode().substring(0,5)+"','"+list.get(i).getName().replaceAll("'", " ")+"','"+list.get(i).getCode3Letter().replaceAll("'", " ")+"'); " ;
                }else{
                    sql = " INSERT INTO `m_airline` (`code`,`name`,`code_3_letter` ) VALUES ('"+list.get(i).getCode()+"','"+list.get(i).getName().replaceAll("'", " ")+"','"+list.get(i).getCode3Letter().replaceAll("'", " ")+"'); " ;
                }
                try {
                    stm.executeUpdate(sql);
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static void getCurrency(Statement s,Statement stmt){
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy");
        UtilityFunction util = new UtilityFunction();
        List<MCurrency> list = new ArrayList<MCurrency>();
        try {
            ResultSet rs = s.executeQuery(sqlCurrency);
            while (rs.next()) {
                String code = rs.getString("CODE");
                String description = new String(rs.getString("DESCRIPTION").getBytes("ISO8859_1"),"TIS-620");
                MCurrency mCurrency = new MCurrency();
                mCurrency.setCode(code);
                mCurrency.setDescription(description);
                list.add(mCurrency);
            }
        } catch (SQLException e ) {
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try { 
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        Connection connect = null;
        Statement stm = null; 
        if(list != null){
            connect = MySqlConnection.getConnection();
            try {
                stm = connect.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
            }
            String sql = "";
            System.out.println(" m_currency size :: "+ list.size());
            for(int i = 0 ; i< list.size() ; i ++){
                if(list.get(i).getCode().length() > 3 ){
                    sql = " INSERT INTO `m_currency` (`CODE`,`DESCRIPTION`) VALUES ('"+list.get(i).getCode().substring(0,3)+"','"+list.get(i).getDescription().replaceAll("'", " ")+"'); " ;
                }else{
                    sql = " INSERT INTO `m_currency` (`CODE`,`DESCRIPTION`) VALUES ('"+list.get(i).getCode()+"','"+list.get(i).getDescription().replaceAll("'", " ")+"'); " ;
                }
                try {
                    stm.executeUpdate(sql);
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static void getCountry(Statement s,Statement stmt){
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy");
        UtilityFunction util = new UtilityFunction();
        List<MCountry> mCountrys = new ArrayList<MCountry>();
        try {
            ResultSet rs = s.executeQuery(sqlCountry);
            while (rs.next()) {
                String code = rs.getString("CODE");
                String description = new String(rs.getString("DESCRIPTION").getBytes("ISO8859_1"),"TIS-620");
                MCountry mCountry = new MCountry();
                mCountry.setCode(code);
                mCountry.setName(description);
                mCountrys.add(mCountry);
            }
        } catch (SQLException e ) {
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try { 
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        Connection connect = null;
        Statement stm = null; 
        if(mCountrys != null){
            connect = MySqlConnection.getConnection();
            try {
                stm = connect.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
            }
            String sql = "";
            System.out.println(" m_country size :: "+ mCountrys.size());
            for(int i = 0 ; i< mCountrys.size() ; i ++){
                if(mCountrys.get(i).getCode().length() > 3 ){
                    sql = " INSERT INTO `m_country` (`code`,`name`) VALUES ('"+mCountrys.get(i).getCode().substring(0,3)+"','"+mCountrys.get(i).getName().replaceAll("'", " ")+"'); " ;
                }else{
                    sql = " INSERT INTO `m_country` (`code`,`name`) VALUES ('"+mCountrys.get(i).getCode()+"','"+mCountrys.get(i).getName().replaceAll("'", " ")+"'); " ;
                }
                try {
                    stm.executeUpdate(sql);
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static void getCity(Statement s,Statement stmt){
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy");
        UtilityFunction util = new UtilityFunction();
        List<MCity> mCitys = new ArrayList<MCity>();
        try {
            ResultSet rs = s.executeQuery(sqlCity);
            while (rs.next()) {
                String code = rs.getString("CODE");
                String description = new String(rs.getString("DESCRIPTION").getBytes("ISO8859_1"),"TIS-620");
                MCity mCity = new MCity();
                mCity.setCode(code);
                mCity.setName(description);
                mCitys.add(mCity);
            }
        } catch (SQLException e ) {
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try { 
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        Connection connect = null;
        Statement stm = null; 
        if(mCitys != null){
            connect = MySqlConnection.getConnection();
            try {
                stm = connect.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
            }
            String sql = "";
            System.out.println(" City size :: "+ mCitys.size());
            for(int i = 0 ; i< mCitys.size() ; i ++){
                if(mCitys.get(i).getCode().length() > 3 ){
                    sql = " INSERT INTO `m_city` (`code`,`name`) VALUES ('"+mCitys.get(i).getCode().substring(0,3)+"','"+mCitys.get(i).getName().replaceAll("'", " ")+"'); " ;
                }else{
                    sql = " INSERT INTO `m_city` (`code`,`name`) VALUES ('"+mCitys.get(i).getCode()+"','"+mCitys.get(i).getName().replaceAll("'", " ")+"'); " ;
                }
                try {
                    stm.executeUpdate(sql);
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static void getTaxInvoice(Statement s,Statement stmt){
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy");
        List reptax = new ArrayList<ReportTaxInvoice>();
        String sql = TaxInvoiceReport;
        try {
            System.out.println(" sql "+ sql);	
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString("ID");
                String taxid = rs.getString("TAX_ID");
                String taxno = rs.getString("TAX_NO");
                String taxdate = String.valueOf(rs.getString("TAX_DATE"));
                String codeap = rs.getString("CODE_AP");
                String description = new String(rs.getString("DESCRIPTION").getBytes("ISO8859_1"),"TIS-620");
                BigDecimal grossamount = new BigDecimal(rs.getString("GROSS_AMOUNT"));
                BigDecimal vatamount = new BigDecimal(rs.getString("VAT_AMOUNT"));
                BigDecimal amount = new BigDecimal(rs.getString("AMOUNT"));
                String flagtype = rs.getString("FLAG_TYPE");
                String invoicetype = rs.getString("INVOICE_TYPE");
                String taxno1 = rs.getString("TAXNO");
                String branch = rs.getString("BRANCH");
                String branchno = rs.getString("BRANCH_NO");
                ReportTaxInvoice rti = new ReportTaxInvoice();
                rti.setId(id);
                rti.setTaxid(taxid);
                rti.setTaxno(taxno);
                rti.setTaxdate(taxdate);
                rti.setCodeap(codeap);
                rti.setDescription(description);
                rti.setGrossamount(grossamount);
                rti.setVatamount(vatamount);
                rti.setAmount(amount);
                rti.setFlagtype(flagtype);
                rti.setInvoicetype(invoicetype);
                rti.setTaxno1(taxno1);
                rti.setBranch(branch);
                rti.setBranchno(branchno);
                reptax.add(rti);
            }
            ExportTaxinvoiceReport(reptax);
        } catch (SQLException e ) {
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try { 
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void ExportTaxinvoiceReport(List reptax){
        UtilityExcelFunction excelFunction = new UtilityExcelFunction();
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("TaxInvoice");

        HSSFCellStyle styleC1 = wb.createCellStyle();
        // Set align Text
        HSSFCellStyle styleC21 = wb.createCellStyle();
        styleC21.setAlignment(styleC21.ALIGN_RIGHT);
        HSSFCellStyle styleC22 = wb.createCellStyle();
        styleC22.setAlignment(styleC22.ALIGN_LEFT);

        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cellStart = row1.createCell(0);
        cellStart.setCellValue("Tax Invoice Report");
        styleC1.setFont(excelFunction.getHeaderFont(wb.createFont()));
        cellStart.setCellStyle(styleC1);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:F1"));

        // Header Table
        HSSFCellStyle styleC3Center = wb.createCellStyle();
        styleC3Center.setFont(excelFunction.getHeaderTable(wb.createFont()));
        styleC3Center.setAlignment(styleC3Center.ALIGN_CENTER);
        styleC3Center.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC3Center.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC3Center.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC3Center.setBorderTop(HSSFCellStyle.BORDER_THIN);

        HSSFRow row2 = sheet.createRow(2);
        HSSFCell cell20 = row2.createCell(0);
        cell20.setCellValue("ID");
        cell20.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(0);
        HSSFCell cell21 = row2.createCell(1);
        cell21.setCellValue("TAX ID");
        cell21.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(1);
        HSSFCell cell22 = row2.createCell(2);
        cell22.setCellValue("TAX NO");
        cell22.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(2);
        HSSFCell cell23 = row2.createCell(3);
        cell23.setCellValue("TAX DATE");
        cell23.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(3);
        HSSFCell cell24 = row2.createCell(4);
        cell24.setCellValue("CODE AP");
        cell24.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(4);
        HSSFCell cell25 = row2.createCell(5);
        cell25.setCellValue("DESCRIPTION");
        cell25.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(5);
        HSSFCell cell26 = row2.createCell(6);
        cell26.setCellValue("GROSS AMOUNT");
        cell26.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(6);
        HSSFCell cell27 = row2.createCell(7);
        cell27.setCellValue("VAT AMOUNT");
        cell27.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(7);
        HSSFCell cell28 = row2.createCell(8);
        cell28.setCellValue("AMOUNT");
        cell28.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(8);
        HSSFCell cell29 = row2.createCell(9);
        cell29.setCellValue("FLAG TYPE");
        cell29.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(9);
        HSSFCell cell30 = row2.createCell(10);
        cell30.setCellValue("INVOICE TYPE");
        cell30.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(10);
        HSSFCell cell31 = row2.createCell(11);
        cell31.setCellValue("TAX NO 1");
        cell31.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(11);
        HSSFCell cell32 = row2.createCell(12);
        cell32.setCellValue("BRANCH");
        cell32.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(12);
        HSSFCell cell33 = row2.createCell(13);
        cell33.setCellValue("BRANCH NO");
        cell33.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(13);

        int count = 3 ;

        HSSFDataFormat currency = wb.createDataFormat();
        HSSFCellStyle styleC23 = wb.createCellStyle();
        styleC23.setAlignment(styleC23.ALIGN_CENTER);
        styleC23.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC23.setBorderRight(HSSFCellStyle.BORDER_THIN);
        HSSFCellStyle styleC24 = wb.createCellStyle();
        styleC24.setAlignment(styleC24.ALIGN_LEFT);
        styleC24.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC24.setBorderRight(HSSFCellStyle.BORDER_THIN);
        HSSFCellStyle styleC25 = wb.createCellStyle();
        styleC25.setAlignment(styleC25.ALIGN_RIGHT);
        styleC25.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC25.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC25.setDataFormat(currency.getFormat("#,##0.00"));

        for(int i=0;i<reptax.size();i++){
            ReportTaxInvoice data = (ReportTaxInvoice)reptax.get(i);
            HSSFRow row = sheet.createRow(count + i);
            HSSFCell cell0 = row.createCell(0);
            cell0.setCellValue(data.getId());
            cell0.setCellStyle(styleC23);
         HSSFCell cell1 = row.createCell(1);
            cell1.setCellValue(data.getTaxid());
            cell1.setCellStyle(styleC23);
         HSSFCell cell13 = row.createCell(2);
            cell13.setCellValue(data.getTaxno());
            cell13.setCellStyle(styleC23);  
         HSSFCell cell2 = row.createCell(3);
            cell2.setCellValue(String.valueOf(data.getTaxdate()));
            cell2.setCellStyle(styleC23);   
         HSSFCell cell3= row.createCell(4);
            cell3.setCellValue(data.getCodeap());
            cell3.setCellStyle(styleC24);
         HSSFCell cell4 = row.createCell(5);
            cell4.setCellValue(data.getDescription().trim());
            cell4.setCellStyle(styleC24);   
         HSSFCell cell5 = row.createCell(6);
            cell5.setCellValue(!"null".equalsIgnoreCase(String.valueOf(data.getGrossamount())) ? (data.getGrossamount()).doubleValue() : 0);
            cell5.setCellStyle(styleC25);
         HSSFCell cell6 = row.createCell(7);
            cell6.setCellValue(!"null".equalsIgnoreCase(String.valueOf(data.getVatamount())) ? (data.getVatamount()).doubleValue() : 0);
            cell6.setCellStyle(styleC25);
         HSSFCell cell7 = row.createCell(8);
            cell7.setCellValue(!"null".equalsIgnoreCase(String.valueOf(data.getAmount())) ? (data.getAmount()).doubleValue() : 0);
            cell7.setCellStyle(styleC25);
         HSSFCell cell8 = row.createCell(9);
            cell8.setCellValue(data.getFlagtype());
            cell8.setCellStyle(styleC23);
         HSSFCell cell9 = row.createCell(10);
            cell9.setCellValue(data.getInvoicetype());
            cell9.setCellStyle(styleC24);
            HSSFCell cell10 = row.createCell(11);
            cell10.setCellValue(data.getTaxno1());
            cell10.setCellStyle(styleC24);
            HSSFCell cell11 = row.createCell(12);
            cell11.setCellValue(data.getBranch());
            cell11.setCellStyle(styleC24);
            HSSFCell cell12 = row.createCell(13);
            cell12.setCellValue(data.getBranchno());
            cell12.setCellStyle(styleC23);

        }
        for(int j =0;j<15;j++){
            sheet.autoSizeColumn(j);
        }
        sheet.setColumnWidth(5, 256*40);//27
        exportFileExcel("TaxInvoiceReport",wb);
    }
    
    public static void getAgentReport(Statement s,Statement stmt){
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy");
        UtilityFunction util = new UtilityFunction();
        List repAgent = new ArrayList<ReportAgent>();
        String sql = AgentReport ;
        try {
            System.out.println(" sql "+ sql);	
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString("ID");
                String systemdate = rs.getString("SYSTEM_DATE") == null ? "" : String.valueOf(rs.getString("SYSTEM_DATE"));
                String systemstaff = rs.getString("SYSTEM_STAFF");
                String code = rs.getString("CODE") == null ? "" :  new String(rs.getString("CODE").getBytes("ISO8859_1"),"TIS-620");	
                String name = rs.getString("NAME") == null ? "" :  new String(rs.getString("NAME").getBytes("ISO8859_1"),"TIS-620");	
                String address = rs.getString("ADDRESS") == null ? "" :  new String(rs.getString("ADDRESS").getBytes("ISO8859_1"),"TIS-620");
                String tel = rs.getString("TEL") == null ? "" : new String(rs.getString("TEL").getBytes("ISO8859_1"),"TIS-620");
                String fax = rs.getString("FAX");
                String description = rs.getString("DESCRIPTION") == null ? "" : new String(rs.getString("DESCRIPTION").getBytes("ISO8859_1"),"TIS-620");
                String nameT = rs.getString("NAME_T") == null ? "" :  new String(rs.getString("NAME_T").getBytes("ISO8859_1"),"TIS-620");
                String descriptionT = rs.getString("DESCRIPTION_T") == null ? "" : new String(rs.getString("DESCRIPTION_T").getBytes("ISO8859_1"),"TIS-620");
                String addressT = rs.getString("ADDRESS_T") == null ? "" : new String(rs.getString("ADDRESS_T").getBytes("ISO8859_1"),"TIS-620");
                String email = rs.getString("EMAIL");
                String web = rs.getString("WEB");
                String remarks = rs.getString("REMARKS") == null ? "" : new String(rs.getString("REMARKS").getBytes("ISO8859_1"),"TIS-620").trim();
                String warning = rs.getString("WARNING");
                String refid = rs.getString("REF_ID");
                String branch = rs.getString("BRANCH");
                String branchno = rs.getString("BRANCH_NO");
                String taxno = rs.getString("TAX_NO");
                ReportAgent reportAgent = new ReportAgent();
                reportAgent.setId(id);
                reportAgent.setSystemdate(systemdate);
                reportAgent.setSystemstaff(systemstaff);
                reportAgent.setCode(code);
                reportAgent.setName(name);
                reportAgent.setAddress(address);
                reportAgent.setTel(tel);
                reportAgent.setFax(fax);
                reportAgent.setDescription(description);
                reportAgent.setNameT(nameT);
                reportAgent.setDescriptionT(descriptionT);
                reportAgent.setAddressT(addressT);
                reportAgent.setEmail(email);
                reportAgent.setWeb(web);
                reportAgent.setRemarks(remarks);
                reportAgent.setWarning(warning);
                reportAgent.setRefid(refid);
                reportAgent.setBranch(branch);
                reportAgent.setBranchno(branchno);
                reportAgent.setTaxno(taxno);
                repAgent.add(reportAgent);
            }

            ExportAgentReport(repAgent);
        } catch (SQLException e ) {
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try { 
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static void getStaffReport(Statement s,Statement stmt){
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy");
        UtilityFunction util = new UtilityFunction();
        List repStaff = new ArrayList<ReportStaff>();
        String sql = StaffReport ;
        try {
            System.out.println(" sql "+ sql);	
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString("ID");
                String systemdate = rs.getString("SYSTEM_DATE") == null ? "" : String.valueOf(rs.getString("SYSTEM_DATE"));
                String systemstaff = rs.getString("SYSTEM_STAFF");
                String code = rs.getString("CODE");
                String name = rs.getString("NAME") == null ? "" : new String(rs.getString("NAME").getBytes("ISO8859_1"),"TIS-620");	
                String passwd = rs.getString("PASSWD");
                String position = rs.getString("POSITION");
                String departmentid = rs.getString("DEPARTMENT_ID");
                String tel = rs.getString("TEL");
                String car = rs.getString("CAR") == null ? "" : new String(rs.getString("CAR").getBytes("ISO8859_1"),"TIS-620");
                String status = rs.getString("STATUS");
                String signature = rs.getString("SIGNATURE");                
                ReportStaff reportStaff = new ReportStaff();
                reportStaff.setId(id);
                reportStaff.setSystemdate(systemdate);
                reportStaff.setSystemstaff(systemstaff);
                reportStaff.setCode(code);
                reportStaff.setName(name);
                reportStaff.setPasswd(passwd);
                reportStaff.setPosition(position);
                reportStaff.setDepartmentid(departmentid);
                reportStaff.setTel(tel);
                reportStaff.setCar(car);
                reportStaff.setStatus(status);
                reportStaff.setSignature(signature);
                repStaff.add(reportStaff);
            }

            ExportStaffReport(repStaff);
        } catch (SQLException e ) {
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try { 
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static void ExportAgentReport(List repAgent){
        UtilityExcelFunction excelFunction = new UtilityExcelFunction();
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Agent");

        HSSFCellStyle styleC1 = wb.createCellStyle();
        // Set align Text
        HSSFCellStyle styleC21 = wb.createCellStyle();
        styleC21.setAlignment(styleC21.ALIGN_RIGHT);
        HSSFCellStyle styleC22 = wb.createCellStyle();
        styleC22.setAlignment(styleC22.ALIGN_LEFT);

        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cellStart = row1.createCell(0);
        cellStart.setCellValue("Agent Report");
        styleC1.setFont(excelFunction.getHeaderFont(wb.createFont()));
        cellStart.setCellStyle(styleC1);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:F1"));

        // Header Table
        HSSFCellStyle styleC3Center = wb.createCellStyle();
        styleC3Center.setFont(excelFunction.getHeaderTable(wb.createFont()));
        styleC3Center.setAlignment(styleC3Center.ALIGN_CENTER);
        styleC3Center.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC3Center.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC3Center.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC3Center.setBorderTop(HSSFCellStyle.BORDER_THIN);

        HSSFRow row2 = sheet.createRow(2);
        HSSFCell cell20 = row2.createCell(0);
        cell20.setCellValue("ID");
        cell20.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(0);
        HSSFCell cell21 = row2.createCell(1);
        cell21.setCellValue("SYSTEM DATE");
        cell21.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(1);
        HSSFCell cell22 = row2.createCell(2);
        cell22.setCellValue("SYSTEM STAFF");
        cell22.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(2);
        HSSFCell cell23 = row2.createCell(3);
        cell23.setCellValue("CODE");
        cell23.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(3);
        HSSFCell cell24 = row2.createCell(4);
        cell24.setCellValue("NAME");
        cell24.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(4);
        HSSFCell cell25 = row2.createCell(5);
        cell25.setCellValue("ADDRESS");
        cell25.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(5);
        HSSFCell cell26 = row2.createCell(6);
        cell26.setCellValue("TEL");
        cell26.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(6);
        HSSFCell cell27 = row2.createCell(7);
        cell27.setCellValue("FAX");
        cell27.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(7);
        HSSFCell cell28 = row2.createCell(8);
        cell28.setCellValue("DESCRIPTION");
        cell28.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(8);
        HSSFCell cell29 = row2.createCell(9);
        cell29.setCellValue("NAME T");
        cell29.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(9);
        HSSFCell cell30 = row2.createCell(10);
        cell30.setCellValue("DESCRIPTION T");
        cell30.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(10);
        HSSFCell cell31 = row2.createCell(11);
        cell31.setCellValue("ADDRESS T");
        cell31.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(11);
        HSSFCell cell32 = row2.createCell(12);
        cell32.setCellValue("EMAIL");
        cell32.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(12);
        HSSFCell cell33 = row2.createCell(13);
        cell33.setCellValue("WEB");
        cell33.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(13);
        HSSFCell cell34 = row2.createCell(14);
        cell34.setCellValue("REMARKS");
        cell34.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(14);
        HSSFCell cell35 = row2.createCell(15);
        cell35.setCellValue("WARNING");
        cell35.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(15);
        HSSFCell cell36 = row2.createCell(16);
        cell36.setCellValue("REF ID");
        cell36.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(16);
        HSSFCell cell37 = row2.createCell(17);
        cell37.setCellValue("BRANCH");
        cell37.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(17);
        HSSFCell cell38 = row2.createCell(18);
        cell38.setCellValue("BRANCH NO");
        cell38.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(18);
        HSSFCell cell39 = row2.createCell(19);
        cell39.setCellValue("TAX NO");
        cell39.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(19);

        int count = 3 ;

        HSSFDataFormat currency = wb.createDataFormat();
        HSSFCellStyle styleC23 = wb.createCellStyle();
        styleC23.setAlignment(styleC23.ALIGN_CENTER);
        styleC23.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC23.setBorderRight(HSSFCellStyle.BORDER_THIN);
        HSSFCellStyle styleC24 = wb.createCellStyle();
        styleC24.setAlignment(styleC24.ALIGN_LEFT);
        styleC24.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC24.setBorderRight(HSSFCellStyle.BORDER_THIN);
        HSSFCellStyle styleC25 = wb.createCellStyle();
        styleC25.setAlignment(styleC25.ALIGN_RIGHT);
        styleC25.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC25.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC25.setDataFormat(currency.getFormat("#,##0.00"));

        for(int i=0;i<repAgent.size();i++){
            ReportAgent data = (ReportAgent)repAgent.get(i);
            HSSFRow row = sheet.createRow(count + i);
            HSSFCell cell0 = row.createCell(0);
            cell0.setCellValue(data.getId());
            cell0.setCellStyle(styleC23);
         HSSFCell cell1 = row.createCell(1);
            cell1.setCellValue(String.valueOf(data.getSystemdate()));
            cell1.setCellStyle(styleC23);
         HSSFCell cell13 = row.createCell(2);
            cell13.setCellValue(data.getSystemstaff());
            cell13.setCellStyle(styleC24);  
         HSSFCell cell2 = row.createCell(3);
            cell2.setCellValue(data.getCode());
            cell2.setCellStyle(styleC24);   
         HSSFCell cell3= row.createCell(4);
            cell3.setCellValue(data.getName());
            cell3.setCellStyle(styleC24);
         HSSFCell cell4 = row.createCell(5);
            cell4.setCellValue(data.getAddress().trim());
            cell4.setCellStyle(styleC24);   
         HSSFCell cell5 = row.createCell(6);
            cell5.setCellValue(data.getTel());
            cell5.setCellStyle(styleC24);
         HSSFCell cell6 = row.createCell(7);
            cell6.setCellValue(data.getFax());
            cell6.setCellStyle(styleC24);
         HSSFCell cell7 = row.createCell(8);
            cell7.setCellValue(data.getDescription().trim());
            cell7.setCellStyle(styleC24);
         HSSFCell cell8 = row.createCell(9);
            cell8.setCellValue(data.getNameT());
            cell8.setCellStyle(styleC24);
         HSSFCell cell9 = row.createCell(10);
            cell9.setCellValue(data.getDescriptionT().trim());
            cell9.setCellStyle(styleC24);
        HSSFCell cell10 = row.createCell(11);
            cell10.setCellValue(data.getAddressT().trim());
            cell10.setCellStyle(styleC24);
        HSSFCell cell11 = row.createCell(12);
            cell11.setCellValue(data.getEmail());
            cell11.setCellStyle(styleC24);
        HSSFCell cell12 = row.createCell(13);
            cell12.setCellValue(data.getWeb());
            cell12.setCellStyle(styleC24);
        HSSFCell cell14 = row.createCell(14);
            cell14.setCellValue(data.getRemarks());
            cell14.setCellStyle(styleC24);
        HSSFCell cell15 = row.createCell(15);
            cell15.setCellValue(data.getWarning());
            cell15.setCellStyle(styleC24);
        HSSFCell cell16 = row.createCell(16);
            cell16.setCellValue(data.getRefid());
            cell16.setCellStyle(styleC24);    
        HSSFCell cell17 = row.createCell(17);
            cell17.setCellValue(data.getBranch());
            cell17.setCellStyle(styleC24);    
        HSSFCell cell18 = row.createCell(18);
            cell18.setCellValue(data.getBranchno());
            cell18.setCellStyle(styleC24);
        HSSFCell cell19 = row.createCell(19);
            cell19.setCellValue(data.getTaxno());
            cell19.setCellStyle(styleC23);
        }

        for(int j =0;j<20;j++){
            sheet.autoSizeColumn(j);
        }
        
        for(int k=4 ; k < 21 ; k++){
            if(k != 6 && k != 7 && k != 12&& k != 13&& k<16){
                sheet.setColumnWidth(k, 256*35);//27
            }else{
                sheet.setColumnWidth(k, 256*20);//27
            }
        }
        exportFileExcel("AgentReport",wb);
    }
    
    public static void ExportStaffReport(List repStaff){
        UtilityExcelFunction excelFunction = new UtilityExcelFunction();
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Staff");

        HSSFCellStyle styleC1 = wb.createCellStyle();
        // Set align Text
        HSSFCellStyle styleC21 = wb.createCellStyle();
        styleC21.setAlignment(styleC21.ALIGN_RIGHT);
        HSSFCellStyle styleC22 = wb.createCellStyle();
        styleC22.setAlignment(styleC22.ALIGN_LEFT);

        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cellStart = row1.createCell(0);
        cellStart.setCellValue("Staff Report");
        styleC1.setFont(excelFunction.getHeaderFont(wb.createFont()));
        cellStart.setCellStyle(styleC1);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:F1"));

        // Header Table
        HSSFCellStyle styleC3Center = wb.createCellStyle();
        styleC3Center.setFont(excelFunction.getHeaderTable(wb.createFont()));
        styleC3Center.setAlignment(styleC3Center.ALIGN_CENTER);
        styleC3Center.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC3Center.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC3Center.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC3Center.setBorderTop(HSSFCellStyle.BORDER_THIN);

        HSSFRow row2 = sheet.createRow(2);
        HSSFCell cell20 = row2.createCell(0);
        cell20.setCellValue("ID");
        cell20.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(0);
        HSSFCell cell21 = row2.createCell(1);
        cell21.setCellValue("SYSTEM DATE");
        cell21.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(1);
        HSSFCell cell22 = row2.createCell(2);
        cell22.setCellValue("SYSTEM STAFF");
        cell22.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(2);
        HSSFCell cell23 = row2.createCell(3);
        cell23.setCellValue("CODE");
        cell23.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(3);
        HSSFCell cell24 = row2.createCell(4);
        cell24.setCellValue("NAME");
        cell24.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(4);
        HSSFCell cell25 = row2.createCell(5);
        cell25.setCellValue("PASSWD");
        cell25.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(5);
        HSSFCell cell26 = row2.createCell(6);
        cell26.setCellValue("POSITION");
        cell26.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(6);
        HSSFCell cell27 = row2.createCell(7);
        cell27.setCellValue("DEPARTMENT ID");
        cell27.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(7);
        HSSFCell cell28 = row2.createCell(8);
        cell28.setCellValue("TEL");
        cell28.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(8);
        HSSFCell cell29 = row2.createCell(9);
        cell29.setCellValue("CAR");
        cell29.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(9);
        HSSFCell cell30 = row2.createCell(10);
        cell30.setCellValue("STATUS");
        cell30.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(10);
        HSSFCell cell31 = row2.createCell(11);
        cell31.setCellValue("SIGNATURE");
        cell31.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(11);
        int count = 3 ;

        HSSFDataFormat currency = wb.createDataFormat();
        HSSFCellStyle styleC23 = wb.createCellStyle();
        styleC23.setAlignment(styleC23.ALIGN_CENTER);
        styleC23.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC23.setBorderRight(HSSFCellStyle.BORDER_THIN);
        HSSFCellStyle styleC24 = wb.createCellStyle();
        styleC24.setAlignment(styleC24.ALIGN_LEFT);
        styleC24.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC24.setBorderRight(HSSFCellStyle.BORDER_THIN);
        HSSFCellStyle styleC25 = wb.createCellStyle();
        styleC25.setAlignment(styleC25.ALIGN_RIGHT);
        styleC25.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC25.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC25.setDataFormat(currency.getFormat("#,##0.00"));

        for(int i=0;i<repStaff.size();i++){
            ReportStaff data = (ReportStaff)repStaff.get(i);
            HSSFRow row = sheet.createRow(count + i);
            HSSFCell cell0 = row.createCell(0);
            cell0.setCellValue(data.getId());
            cell0.setCellStyle(styleC23);
         HSSFCell cell1 = row.createCell(1);
            cell1.setCellValue(String.valueOf(data.getSystemdate()));
            cell1.setCellStyle(styleC23);
         HSSFCell cell13 = row.createCell(2);
            cell13.setCellValue(data.getSystemstaff());
            cell13.setCellStyle(styleC24);  
         HSSFCell cell2 = row.createCell(3);
            cell2.setCellValue(data.getCode());
            cell2.setCellStyle(styleC24);   
         HSSFCell cell3= row.createCell(4);
            cell3.setCellValue(data.getName());
            cell3.setCellStyle(styleC24);
         HSSFCell cell4 = row.createCell(5);
            cell4.setCellValue(data.getPasswd());
            cell4.setCellStyle(styleC24);   
         HSSFCell cell5 = row.createCell(6);
            cell5.setCellValue(data.getPosition());
            cell5.setCellStyle(styleC24);
         HSSFCell cell6 = row.createCell(7);
            cell6.setCellValue(data.getDepartmentid());
            cell6.setCellStyle(styleC24);
         HSSFCell cell7 = row.createCell(8);
            cell7.setCellValue(data.getTel());
            cell7.setCellStyle(styleC24);
         HSSFCell cell8 = row.createCell(9);
            cell8.setCellValue(data.getCar());
            cell8.setCellStyle(styleC24);
         HSSFCell cell9 = row.createCell(10);
            cell9.setCellValue(data.getStatus());
            cell9.setCellStyle(styleC24);
        HSSFCell cell10 = row.createCell(11);
            cell10.setCellValue(data.getSignature());
            cell10.setCellStyle(styleC24);
        }
        for(int j =0;j<12;j++){
            sheet.autoSizeColumn(j);
        }
//        sheet.setColumnWidth(8, 256*40);//27
//        sheet.setColumnWidth(10, 256*40);//27
        exportFileExcel("StaffReport",wb);
    }
    
    static void exportFileExcel(String filename,HSSFWorkbook wb){
        try {
            FileOutputStream out = new FileOutputStream(new File(ExportFilePath+filename+".xls"));
            wb.write(out);
            out.close();
            System.out.println("Excel StaffReport written successfully..");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class OracleConnection{
    private static final String ip = "192.168.99.48";
    private static final String port = "1521";
    private static final String schema   = "ORCL";
    private static final String username = "travox3";
    private static final String password = "oracle";
    
     static{
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
     
    public static Connection getConnection(){
        Connection connect = null;
        try {   
            connect = DriverManager.getConnection("jdbc:oracle:thin:@"+ip+":"+port+"/"+schema+"",username,password);
            System.out.println("Database Connected.");
        } catch (SQLException ex) {
            
        }
        return connect;
    }
}

class MySqlConnection{
    private static final String ip = "192.168.99.48";
    private static final String port = "3306";
    private static final String schema   = "test";
    private static final String username = "root";
    private static final String password = "P@ssw0rd";
    
     static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
     
    public static Connection getConnection(){
        Connection connect = null;
        try {   
            connect = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+schema+"?characterEncoding=UTF-8&amp;useUnicode=yes",username,password);
            System.out.println("Database Connected.");
        } catch (SQLException ex) {
            
        }
        return connect;
    }
}
