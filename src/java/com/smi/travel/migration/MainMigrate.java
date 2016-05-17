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
import java.util.Date;
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
    private static final String AgentReport = " SELECT * FROM TRAVOX3.agent where  code is not null and name is not  null and length(code) <> 1 ";
    private static final String StaffReport = " SELECT * FROM TRAVOX3.STAFF ";
    private static final String ExportFilePath = "C:\\Users\\Jittima\\Desktop\\ExcelFile\\";
    
    private static final String sqlAirline = " SELECT * FROM TRAVOX3.AIRLINE ";
    private static final String sqlProduct = " SELECT * FROM TRAVOX3.PRODUCT where code <> '' or code is not null ";
    private static final String sqlPackageTour = " SELECT * FROM TRAVOX3.PACKAGE_TOUR ";
    private static final String sqlHotel = " SELECT * FROM TRAVOX3.HOTEL where code is not null and name is not null ";
    private static final String sqlCurrency = " SELECT * FROM TRAVOX3.CURRENCY ";
    private static final String sqlCountry = " SELECT * FROM TRAVOX3.COUNTRY ";
    private static final String sqlCity = " SELECT * FROM TRAVOX3.CITY ";
    private static final String sqlCustomer = " SELECT * FROM \"TRAVOX3\".\"CUSTOMER\" WHERE FIRST_NAME IS NOT NULL AND LENGTH (code) = 8 AND code IN ( '10000001', '¹0000001', '10000003', '¹0000003', '10000004', '¹0000004', '10000005', '¹0000005', '10000006', '¹0000006', '¹0000007', '10000007', '¹0000008', '10000008', '¹0000009', '10000009', '10000010', '¹0000010', '¹0000011', '10000011', '10000012', '¹0000012', '10000013', '¹0000013', '¹0000014', '10000014', '¹0000015', '10000015', '¹0000016', '10000016', '¹0000017', '10000017', '¹0000018', '10000018', '¹0000019', '10000019', '¹0000020', '10000020', '30000001', '³0000001', '30000002', '³0000002', '30000003', '³0000003', '30000004', '³0000004', 'A0000001', 'Â0000001', 'Ã0000001', 'Ä0000001', 'Á0000001', 'å0000001', 'à0000001', 'À0000001', 'ª0000001', 'Å0000001', 'A0000002', 'Â0000002', 'á0000002', 'Ã0000002', 'ä0000002', 'Ä0000002', 'Á0000002', 'à0000002', 'ª0000002', 'Å0000002', 'À0000002', 'A0000003', 'Â0000003', 'Ã0000003', 'ä0000003', 'Ä0000003', 'á0000003', 'Å0000003', 'ª0000003', 'Á0000003', 'à0000003', 'À0000003', 'A0000004', 'Â0000004', 'Ã0000004', 'ä0000004', 'Ä0000004', 'ª0000004', 'á0000004', 'Å0000004', 'Á0000004', 'à0000004', 'A0000005', 'Â0000005', 'Ã0000005', 'ä0000005', 'ª0000005', 'Å0000005', 'Ä0000005', 'á0000005', 'Á0000005', 'A0000006', 'Â0000006', 'Ã0000006', 'ä0000006', 'Å0000006', 'ª0000006', 'A0000007', 'Â0000007', 'Ã0000007', 'Å0000007', 'ª0000007', 'A0000008', 'Â0000008', 'Ã0000008', 'Å0000008', 'A0000009', 'Â0000009', 'Ã0000009', 'Å0000009', 'A0000010', 'Â0000010', 'Ã0000010', 'A0000011', 'Â0000011', 'Ã0000011', 'A0000012', 'Â0000012', 'Ã0000012', 'A0000013', 'Â0000013', 'Ã0000013', 'A0000014', 'ã0000014', 'Ã0000014', 'Â0000014', 'A0000015', 'ã0000015', 'Ã0000015', 'â0000015', 'Â0000015', 'A0000016', 'ã0000016', 'Ã0000016', 'C0000001', 'ç0000001', 'Ç0000001', 'C0000002', 'Ç0000002', 'C0000003', 'Ç0000003', 'C0000004', 'Ç0000004', 'C0000005', 'Ç0000005', 'C0000006', 'Ç0000006', 'C0000007', 'Ç0000007', 'C0000008', 'Ç0000008', 'C0000009', 'Ç0000009', 'C0000010', 'Ç0000010', 'C0000011', 'Ç0000011', 'C0000012', 'Ç0000012', 'E0000001', 'É0000001', 'Ë0000001', 'Ê0000001', 'È0000001', 'E0000002', 'É0000002', 'Ë0000002', 'Ê0000002', 'È0000002', 'E0000003', 'É0000003', 'Ë0000003', 'Ê0000003', 'È0000003', 'E0000004', 'É0000004', 'Ë0000004', 'Ê0000004', 'è0000004', 'È0000004', 'E0000005', 'Ë0000005', 'É0000005', 'Ê0000005', 'È0000005', 'E0000006', 'Ë0000006', 'É0000006', 'Ê0000006', 'È0000006', 'E0000007', 'Ë0000007', 'é0000007', 'Ê0000007', 'E0000008', 'Ë0000008', 'Ê0000008', 'E0000009', 'Ë0000009', 'Ê0000009', 'E0000010', 'Ë0000010', 'Ê0000010', 'E0000011', 'Ë0000011', 'Ê0000011', 'E0000012', 'Ë0000012', 'Ê0000012', 'E0000013', 'Ë0000013', 'Ê0000013', 'E0000014', 'Ë0000014', 'Ê0000014', 'E0000015', 'Ë0000015', 'Ê0000015', 'E0000016', 'Ë0000016', 'Ê0000016', 'E0000017', 'Ë0000017', 'Ê0000017', 'E0000018', 'Ë0000018', 'Ê0000018', 'E0000019', 'Ë0000019', 'Ê0000019', 'E0000020', 'Ë0000020', 'Ê0000020', 'E0000021', 'Ë0000021', 'I0000001', 'Í0000001', 'Ï0000001', 'ì0000001', 'I0000002', 'Í0000002', 'Ï0000002', 'I0000003', 'Í0000003', 'I0000004', 'Í0000004', 'I0000005', 'Í0000005', 'I0000006', 'Í0000006', 'I0000007', 'Í0000007', 'I0000008', 'í0000008', 'Í0000008', 'I0000009', 'Í0000009', 'I0000010', 'Í0000010', 'I0000011', 'Í0000011', 'I0000012', 'Í0000012', 'I0000013', 'Í0000013', 'I0000014', 'Í0000014', 'N0000001', 'Ñ0000001', 'N0000002', 'Ñ0000002', 'N0000003', 'Ñ0000003', 'O0000001', 'º0000001', 'Ò0000001', 'Õ0000001', 'O0000002', 'Ò0000002', 'Õ0000002', 'º0000002', 'O0000003', 'Ò0000003', 'º0000003', 'O0000004', 'Ò0000004', 'º0000004', 'O0000005', 'Ò0000005', 'º0000005', 'O0000006', 'Ò0000006', 'O0000007', 'Ò0000007', 'O0000008', 'Ò0000008', 'O0000009', 'Ò0000009', 'O0000010', 'Ò0000010', 'O0000011', 'Ò0000011', 'O0000012', 'Ò0000012', 'O0000013', 'Ò0000013', 'O0000014', 'Ò0000014', 'O0000015', 'Ò0000015', 'O0000016', 'Ò0000016', 'O0000017', 'Ò0000017', 'O0000018', 'Ò0000018', 'O0000019', 'Ò0000019', 'O0000020', 'Ò0000020', 'O0000021', 'Ò0000021', 'O0000022', 'Ò0000022', 'O0000023', 'Ò0000023', 'O0000024', 'Ò0000024', 'O0000025', 'Ò0000025', 'O0000026', 'Ò0000026', 'O0000027', 'Ò0000027' ) ";
    private static final String sqlAR = " SELECT CASE WHEN (agt.code IS NULL) THEN 'DUMMY' ELSE agt.code END AS CODE, inv.inv_name, INV.INV_NO, TO_CHAR (inv.inv_date, 'DD-MM-YYYY') AS inv_date FROM \"TRAVOX3\".\"AC_INVOICE\" inv INNER JOIN \"TRAVOX3\".AC_INVOICE_DETAIL invd ON INVD.AC_INVOICE_ID = INV.\"ID\" LEFT JOIN ( SELECT NAME, MIN (code) AS code FROM \"TRAVOX3\".AGENT GROUP BY NAME ) agt ON agt. NAME = inv.INV_NAME WHERE TO_CHAR (inv.inv_date, 'MMYYYY') IN ('022016','032016','042016') and inv.status is null  ORDER BY   inv.inv_no , inv.inv_date  " ;
    private static final String sqlAP = " SELECT payd. ID AS payid, (pay.PAY_NO) AS PAY_NO, (PAY.INVOICE_SUP) AS AP_CODE, ap. NAME AS NAME, TO_CHAR (pay.pay_DATE, 'DD-MM-YYYY') AS pay_date, (PAY.REF_DEPARTMENT) AS DEPARTMENT, CASE WHEN pay.VAT_TYPE = 'X' THEN 'TEMP' WHEN pay.VAT_TYPE = 'V' THEN 'VAT' ELSE 'NO VAT' END AS vattype FROM travox3.AC_PAYMENT pay INNER JOIN travox3.AC_PAYMENT_DETAIL payd ON payd.AC_PAYMENT_ID = pay. ID INNER JOIN ACCSMI3.AP_CODE ap ON ap.code = pay.INVOICE_SUP WHERE TO_CHAR (pay.pay_DATE, 'MMYYYY') IN ( '112015','122015','012016', '022016', '032016') ORDER BY pay.pay_DATE , PAY.PAY_NO ";
    private static final String sqlInv1 = " SELECT inv3. ID AS ID, INV3.inv_no AS invno, INV3.\"NAME\" AS NAME, TO_CHAR (INV3.INV_DATE, 'DD-MM-YYYY') AS invdate, SUM (invd3.price) AS grand_total, SUM (invd3.price) - SUM ( ROUND ( INVD3.price - INVD3.price * 100 / (100 + INV3.vat), 2 )) AS grand_total_gross, SUM ( ROUND ( INVD3.price - INVD3.price * 100 / (100 + INV3.vat), 2 )) AS grand_total_vat, MIN (INVD3.CUR) AS cur, 'INBOUND' AS department, '1' AS acc_no FROM \"INBOUND\".\"INVOICE3\" inv3 INNER JOIN INBOUND.INVOICE3_DETAIL invd3 ON inv3. ID = invd3.INVOICE3_ID LEFT JOIN ( SELECT NAME, MIN (code) AS code FROM \"TRAVOX3\".AGENT GROUP BY NAME ) agt ON agt. NAME = inv3. NAME WHERE \"TO_CHAR\" (inv3.INV_DATE, 'MMYYYY') IN ('102015', '112015', '122015', '012016', '022016', '032016' ) GROUP BY inv3. ID, INV3.inv_no, INV3.\"NAME\", INV3.INV_DATE ORDER BY INV3. ID  ";
    private static final String sqlInv2 = " SELECT inv2. ID AS ID, INV2.inv_no AS invno, INV2.\"NAME\" AS NAME, TO_CHAR (INV2.INV_DATE, 'DD-MM-YYYY') AS invdate, SUM (invd2.price) AS grand_total, SUM (invd2.price) AS grand_total_gross, 0 AS grand_total_vat, MIN (INVD2.CUR) AS cur, 'INBOUND' AS department, '2' AS acc_no FROM \"INBOUND\".\"INVOICE2\" inv2 INNER JOIN INBOUND.INVOICE2_DETAIL invd2 ON inv2. ID = invd2.INVOICE2_ID LEFT JOIN ( SELECT NAME, MIN (code) AS code FROM \"TRAVOX3\".AGENT GROUP BY NAME ) agt ON agt. NAME = inv2. NAME WHERE \"TO_CHAR\" (inv2.INV_DATE, 'MMYYYY') IN ('102015', '112015', '122015', '012016', '022016', '032016' ) GROUP BY inv2. ID, INV2.inv_no, INV2.\"NAME\", INV2.INV_DATE ORDER BY INV2. ID ";
    private static final String sqlTravoxProduction = " SELECT gj.gj_no AS gj, '' AS PAY_NO, ( SELECT ap. NAME FROM ACCTSMI3.ap_code ap WHERE ap.code = ( SELECT MIN (GJD1.code_ap) FROM ACCTSMI3.GENERAL_JOURNAL_DETAIL1 GJD1 WHERE GJD1.general_journal1_ID = gj. ID AND GJD1.code_ap IS NOT NULL GROUP BY gj. ID )) AS NAME, ( SELECT MIN (GJD1.code_ap) FROM ACCTSMI3.GENERAL_JOURNAL_DETAIL1 GJD1 WHERE GJD1.general_journal1_ID = gj. ID AND GJD1.code_ap IS NOT NULL GROUP BY gj. ID ) AS AP_CODE, gj.ref_doc_no AS REFDOC, TO_CHAR ( gj.SYSTEM_DATE, 'DD-MM-YYYY' ) AS system_date, TO_CHAR (gj.DUE_DATE, 'DD-MM-YYYY') AS due_date, '' AS INVOICE_NUM, GJ.DESCRIPTION AS Main_Description, CASE WHEN act.code IS NULL THEN act1.code ELSE act.code END AS code, CASE WHEN act.code IS NULL THEN act1.detail ELSE act.detail END AS type_product, act.code AS code11, act1.code AS code22, GJD.DESCRIPTION AS description, ( SELECT SUM ( CASE WHEN SUBSTR (act2.code, 0, 2) = '21' OR act3.code = '22021' THEN NVL (GJD1.cr_amount, 0) ELSE 0 END ) FROM ACCTSMI3.GENERAL_JOURNAL_DETAIL1 GJD1 LEFT JOIN ACCTSMI3.ACCT_CODE act2 ON act2. ID = GJD1.ap_acct_code_id LEFT JOIN ACCTSMI3.ACCT_CODE act3 ON act3. ID = GJD1.acct_code_id WHERE GJD1.general_journal1_ID = gj. ID GROUP BY gj. ID ) AS TOTAL_AMOUNT, GJD.ACCT_CODE_ID, ( SELECT SUM (NVL(GJD1.dr_amount, 0)) FROM ACCTSMI3.GENERAL_JOURNAL_DETAIL1 GJD1 WHERE GJD1.general_journal1_ID = gj. ID AND GJD1.acct_code_id = - 10 GROUP BY gj. ID ) AS TOTAL_VAT, 'THB' AS cur, CASE WHEN gjd.cr_amount IS NULL THEN gjd.dr_amount ELSE gjd.cr_amount * - 1 END AS amount, CASE WHEN GJD.department = 'I' THEN 'Inbound' WHEN GJD.department = 'O' THEN 'Outbound' WHEN GJD.department = 'W' THEN 'Wendy' ELSE '' END AS DEPARTMENT, SUBSTR (gj.gj_no, 0, 1) AS acc_no, TO_CHAR (gj.book_DATE, 'DD-MM-YYYY') AS EXPENSE_DATE, gv.voucher_no AS voucher_no, gvd.amount AS voucher_amount FROM ACCTSMI3.GENERAL_JOURNAL1 gj INNER JOIN ACCTSMI3.GENERAL_JOURNAL_DETAIL1 GJD ON gj. ID = gjd.general_journal1_ID LEFT JOIN ACCTSMI3.ap_code ap ON ap.code = gjd.code_AP LEFT JOIN ACCTSMI3.ACCT_CODE act ON act. ID = gjd.acct_code_id LEFT JOIN ACCTSMI3.ACCT_CODE act1 ON act1. ID = gjd.ap_acct_code_id LEFT JOIN ACCTSMI3.GENERAL_VOUCHER_DETAIL gvd ON gvd.general_journal_id = gj. ID LEFT JOIN ACCTSMI3.GENERAL_VOUCHER gv ON gv. ID = gvd.general_voucher_id WHERE TO_CHAR (gj.book_DATE, 'MMYYYY') IN ( '042016' ) AND ( gj.book_type1 = 1 OR gj.book_type1 = 2 ) AND gj.book_type = 'E' ORDER BY gj.GJ_NO, gj.book_DATE ASC ";
    private static final String sqlDaytourExpense = " SELECT * FROM `daytour_expense` ";
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
                getARData(s,stmt);
//                getAPData(s,stmt);
//                getDeptorInvoiceData(s, stmt);
//                getInvoiceData(s, stmt);
//                getTravoxData(s, stmt);
//                getDaytourExpense(s, stmt);
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
    
    
    public static void getDaytourExpense(Statement s,Statement stmt){
        List<MainMigrateModel> list = new ArrayList<MainMigrateModel>();
        Connection connect = null;
        Statement stm = null; 
        connect = MySqlConnection.getConnection();
        try {
            stm = connect.createStatement();
            ResultSet rs = stm.executeQuery(sqlDaytourExpense);
            while (rs.next()) {
                String id = rs.getString("id") == null ? "" : rs.getString("id");
                String description = rs.getString("description") == null ? "" : new String(rs.getString("description").getBytes("ISO8859_1"),"TIS-620");
                MainMigrateModel day = new MainMigrateModel();
                day.setId(id);
                day.setDescription(description);
                list.add(day);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
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
            if (s != null) {
                try { 
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        System.out.println(" list daytour size :: "+ list.size());
        if(list != null){
            String sql = "";
            for(int i = 0 ; i< list.size() ; i ++){               
                sql = " UPDATE `daytour_expense` SET description = '"+list.get(i).getDescription()+"' WHERE id = '"+list.get(i).getId()+"' ";
                try {
                    stm.executeUpdate(sql);
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    
    public static void getTravoxData(Statement s,Statement stmt){
        List<MainMigrateModel> list = new ArrayList<MainMigrateModel>();
        UtilityFunction util = new UtilityFunction();
        try {
            ResultSet rs = s.executeQuery(sqlTravoxProduction);
            while (rs.next()){
                String gj = rs.getString("GJ") == null ? "" : new String(rs.getString("GJ").getBytes("ISO8859_1"),"TIS-620");
                String payno = rs.getString("PAY_NO") == null ? "" : new String(rs.getString("PAY_NO").getBytes("ISO8859_1"),"TIS-620");
                String name = rs.getString("NAME") == null ? "" : new String(rs.getString("NAME").getBytes("ISO8859_1"),"TIS-620");
                String apcode = rs.getString("AP_CODE") == null ? "" : new String(rs.getString("AP_CODE").getBytes("ISO8859_1"),"TIS-620");
                String systemdate = rs.getString("SYSTEM_DATE") == null ? "" : new String(rs.getString("SYSTEM_DATE").getBytes("ISO8859_1"),"TIS-620");
                String invoicenum = rs.getString("INVOICE_NUM") == null ? "" : new String(rs.getString("INVOICE_NUM").getBytes("ISO8859_1"),"TIS-620");
                String code = rs.getString("CODE") == null ? "" : new String(rs.getString("CODE").getBytes("ISO8859_1"),"TIS-620");
                String typeproduct = rs.getString("TYPE_PRODUCT") == null ? "" : new String(rs.getString("TYPE_PRODUCT").getBytes("ISO8859_1"),"TIS-620");
                String totalamount = rs.getString("TOTAL_AMOUNT") == null ? "" : new String(rs.getString("TOTAL_AMOUNT").getBytes("ISO8859_1"),"TIS-620");
                String totalvat = rs.getString("TOTAL_VAT") == null ? "" : new String(rs.getString("TOTAL_VAT").getBytes("ISO8859_1"),"TIS-620");
                String cur = rs.getString("CUR") == null ? "" : new String(rs.getString("CUR").getBytes("ISO8859_1"),"TIS-620");
                String amount = rs.getString("AMOUNT") == null ? "" : new String(rs.getString("AMOUNT").getBytes("ISO8859_1"),"TIS-620");
                String department = rs.getString("DEPARTMENT") == null ? "" : new String(rs.getString("DEPARTMENT").getBytes("ISO8859_1"),"TIS-620");
                String accno = rs.getString("ACC_NO") == null ? "" : new String(rs.getString("ACC_NO").getBytes("ISO8859_1"),"TIS-620");
                String expensedate = rs.getString("EXPENSE_DATE") == null ? "" : new String(rs.getString("EXPENSE_DATE").getBytes("ISO8859_1"),"TIS-620");
                
                String refdoc = rs.getString("REFDOC") == null ? "" : new String(rs.getString("REFDOC").getBytes("ISO8859_1"),"TIS-620");
                String duedate = rs.getString("DUE_DATE") == null ? "" : new String(rs.getString("DUE_DATE").getBytes("ISO8859_1"),"TIS-620");
                String maindescription = rs.getString("MAIN_DESCRIPTION") == null ? "" : new String(rs.getString("MAIN_DESCRIPTION").getBytes("ISO8859_1"),"TIS-620");
                String description = rs.getString("DESCRIPTION") == null ? "" : new String(rs.getString("DESCRIPTION").getBytes("ISO8859_1"),"TIS-620");
                String voucherno = rs.getString("VOUCHER_NO") == null ? "" : new String(rs.getString("VOUCHER_NO").getBytes("ISO8859_1"),"TIS-620");
                String voucheramount = rs.getString("VOUCHER_AMOUNT") == null ? "" : new String(rs.getString("VOUCHER_AMOUNT").getBytes("ISO8859_1"),"TIS-620");
                
                MainMigrateModel migrateModel = new MainMigrateModel();
                migrateModel.setGj(gj);
                migrateModel.setPayno(payno);
                migrateModel.setName(name);
                migrateModel.setApcode(apcode);
                migrateModel.setSystemdate(systemdate);
                migrateModel.setInvoicenum(invoicenum);
                migrateModel.setCode(code);
                migrateModel.setTypeproduct(typeproduct);
                migrateModel.setTotalamount(totalamount);
                migrateModel.setTotalvat(totalvat);
                migrateModel.setCur(cur);
                migrateModel.setAmount(amount);
                migrateModel.setDepartment(department);
                migrateModel.setAccno(accno);
                migrateModel.setExpensedate(expensedate);
                migrateModel.setRefdoc(refdoc);
                migrateModel.setDuedate(duedate);
                migrateModel.setMaindescription(maindescription);
                migrateModel.setDescription(description);
                migrateModel.setVoucherno(voucherno);
                migrateModel.setVoucheramount(voucheramount);
                list.add(migrateModel);
            }
            
            System.out.println(" list.size() ::: " + list.size());
        } catch (SQLException e ) {
            e.printStackTrace();
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
        ExportTravoxReport(list);
    }
    
    public static void ExportTravoxReport(List<MainMigrateModel> list){
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
        styleC25.setDataFormat(currency.getFormat("#,##0.00"));
        
        HSSFSheet sheet = wb.createSheet("TravoxReport");

        HSSFRow row2 = sheet.createRow(0);
        HSSFCell cell20 = row2.createCell(0);
        cell20.setCellValue("GJ");
        cell20.setCellStyle(styleC3Center);
        HSSFCell cell21 = row2.createCell(1);
        cell21.setCellValue("PAY NO");
        cell21.setCellStyle(styleC3Center);
        HSSFCell cell22 = row2.createCell(2);
        cell22.setCellValue("NAME");
        cell22.setCellStyle(styleC3Center);
        HSSFCell cell23 = row2.createCell(3);
        cell23.setCellValue("AP CODE");
        cell23.setCellStyle(styleC3Center);
        HSSFCell cell24 = row2.createCell(4);
        cell24.setCellValue("REFDOC");
        cell24.setCellStyle(styleC3Center);
        HSSFCell cell25 = row2.createCell(5);
        cell25.setCellValue("SYSTEM_DATE");
        cell25.setCellStyle(styleC3Center);
        HSSFCell cell26 = row2.createCell(6);
        cell26.setCellValue("DUE DATE");
        cell26.setCellStyle(styleC3Center);
        HSSFCell cell27 = row2.createCell(7);
        cell27.setCellValue("INVOICE NUM");
        cell27.setCellStyle(styleC3Center);
        HSSFCell cell28 = row2.createCell(8);
        cell28.setCellValue("MAIN DESCRIPTION");
        cell28.setCellStyle(styleC3Center);
        HSSFCell cell29 = row2.createCell(9);
        cell29.setCellValue("CODE");
        cell29.setCellStyle(styleC3Center);
        HSSFCell cell30 = row2.createCell(10);
        cell30.setCellValue("TYPE PRODUCT");
        cell30.setCellStyle(styleC3Center);
        HSSFCell cell31 = row2.createCell(11);
        cell31.setCellValue("DESCRIPTION");
        cell31.setCellStyle(styleC3Center);
        HSSFCell cell32 = row2.createCell(12);
        cell32.setCellValue("TOTAL AMOUNT");
        cell32.setCellStyle(styleC3Center);
        HSSFCell cell33 = row2.createCell(13);
        cell33.setCellValue("TOTAL VAT");
        cell33.setCellStyle(styleC3Center);
        HSSFCell cell34 = row2.createCell(14);
        cell34.setCellValue("CUR");
        cell34.setCellStyle(styleC3Center);
        HSSFCell cell35 = row2.createCell(15);
        cell35.setCellValue("AMOUNT");
        cell35.setCellStyle(styleC3Center);
        HSSFCell cell36 = row2.createCell(16);
        cell36.setCellValue("DEPARTMENT");
        cell36.setCellStyle(styleC3Center);
        HSSFCell cell37 = row2.createCell(17);
        cell37.setCellValue("ACC NO");
        cell37.setCellStyle(styleC3Center);
        HSSFCell cell38 = row2.createCell(18);
        cell38.setCellValue("EXPENSE DATE");
        cell38.setCellStyle(styleC3Center);
        HSSFCell cell39 = row2.createCell(19);
        cell39.setCellValue("VOUCHER NO");
        cell39.setCellStyle(styleC3Center);
        HSSFCell cell40 = row2.createCell(20);
        cell40.setCellValue("VOUCHER AMOUNT");
        cell40.setCellStyle(styleC3Center);

        if(list != null){
            int count = 1 ;
            for(int i=0;i<list.size();i++){
                MainMigrateModel data = (MainMigrateModel)list.get(i);
                HSSFRow row = sheet.createRow(count + i);
                HSSFCell cell0 = row.createCell(0);
                cell0.setCellValue(data.getGj());
                cell0.setCellStyle(styleC24);
             HSSFCell cell1 = row.createCell(1);
                cell1.setCellValue(data.getPayno());
                cell1.setCellStyle(styleC24);
             HSSFCell cell13 = row.createCell(2);
                cell13.setCellValue(data.getName());
                cell13.setCellStyle(styleC24);  
             HSSFCell cell2 = row.createCell(3);
                cell2.setCellValue(data.getApcode());
                cell2.setCellStyle(styleC24);   
             HSSFCell cell3= row.createCell(4);
                cell3.setCellValue(data.getRefdoc());
                cell3.setCellStyle(styleC24);
            HSSFCell cell4 = row.createCell(5);
                cell4.setCellValue(data.getSystemdate());
                cell4.setCellStyle(styleC24);   
            HSSFCell cell5 = row.createCell(6);
               cell5.setCellValue(data.getDuedate());
               cell5.setCellStyle(styleC24);  
            HSSFCell cell6 = row.createCell(7);
                cell6.setCellValue(data.getInvoicenum());
                cell6.setCellStyle(styleC24);
            HSSFCell cell7 = row.createCell(8);
                cell7.setCellValue(data.getMaindescription());
                cell7.setCellStyle(styleC24);
            HSSFCell cell8 = row.createCell(9);
                cell8.setCellValue(data.getCode());
                cell8.setCellStyle(styleC24);
            HSSFCell cell9 = row.createCell(10);
                cell9.setCellValue(data.getTypeproduct());
                cell9.setCellStyle(styleC24);   
            HSSFCell cell11 = row.createCell(11);
                cell11.setCellValue(data.getDescription());
                cell11.setCellStyle(styleC24);
            HSSFCell cell12 = row.createCell(12);
                cell12.setCellValue(!"".equalsIgnoreCase(String.valueOf(data.getTotalamount())) ? (new BigDecimal(data.getTotalamount())).doubleValue() : 0);
                cell12.setCellStyle(styleC25);  
            HSSFCell cell013 = row.createCell(13);
                cell013.setCellValue(!"".equalsIgnoreCase(String.valueOf(data.getTotalvat())) ? (new BigDecimal(data.getTotalvat())).doubleValue() : 0);
                cell013.setCellStyle(styleC25);  
            HSSFCell cell14 = row.createCell(14);
                cell14.setCellValue(data.getCur());
                cell14.setCellStyle(styleC23);  
            HSSFCell cell15 = row.createCell(15);
                cell15.setCellValue(!"".equalsIgnoreCase(String.valueOf(data.getAmount())) ? (new BigDecimal(data.getAmount())).doubleValue() : 0);
                cell15.setCellStyle(styleC25);
            HSSFCell cell16 = row.createCell(16);
                cell16.setCellValue(data.getDepartment());
                cell16.setCellStyle(styleC24);  
            HSSFCell cell17 = row.createCell(17);
                cell17.setCellValue(data.getAccno());
                cell17.setCellStyle(styleC24);  
            HSSFCell cell18 = row.createCell(18);
                cell18.setCellValue(data.getExpensedate());
                cell18.setCellStyle(styleC24);  
            HSSFCell cell19 = row.createCell(19);
                cell19.setCellValue(data.getVoucherno());
                cell19.setCellStyle(styleC24);  
            HSSFCell cell020 = row.createCell(20);
                cell020.setCellValue(!"".equalsIgnoreCase(String.valueOf(data.getVoucheramount())) ? (new BigDecimal(data.getVoucheramount())).doubleValue() : 0);
                cell020.setCellStyle(styleC25);  
            }
        }
        for(int x=0;x<21;x++){
            sheet.autoSizeColumn(x);
        }
        sheet.setColumnWidth(2, 256*30);
        sheet.setColumnWidth(8, 256*30);
        sheet.setColumnWidth(11, 256*30);
        exportFileExcel("TravoxReport",wb);
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
        styleC25.setDataFormat(currency.getFormat("#,##0.00"));
        
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
//                cell3.setCellValue(data.getGrandtotal());
                cell3.setCellValue(!"".equalsIgnoreCase(String.valueOf(data.getGrandtotal())) ? (new BigDecimal(data.getGrandtotal())).doubleValue() : 0);
                cell3.setCellStyle(styleC25);
             HSSFCell cell4 = row.createCell(5);
                cell4.setCellValue(!"".equalsIgnoreCase(String.valueOf(data.getGrandtotalgross())) ? (new BigDecimal(data.getGrandtotalgross())).doubleValue() : 0);
                cell4.setCellStyle(styleC25);   
             HSSFCell cell5 = row.createCell(6);
                cell5.setCellValue(!"".equalsIgnoreCase(String.valueOf(data.getGrandtotalvat())) ? (new BigDecimal(data.getGrandtotalvat())).doubleValue() : 0);
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
            br = new BufferedReader(new FileReader("C:\\Users\\Jittima\\Desktop\\deptor_invoice_3112_all.txt"));
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
//            HSSFSheet sheet = wb.createSheet(listAR.get(0).getInvdate().substring(3,10).replaceAll("-", ""));
            HSSFSheet sheet = wb.createSheet("ARReport");
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
                    
//                    datetemp = data.getInvdate().substring(3,10);
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
//                System.out.println(" code ::: " + code);
                MInitialname mInitialname = new MInitialname();
                mInitialname.setId(initial);
                migrateModel.setInitialname(mInitialname);
                migrateModel.setFirstName(firstName);
                migrateModel.setLastName(lastName);
                migrateModel.setNationality(nationality);
                migrateModel.setBirthDate("".equalsIgnoreCase(birthDate) ? null : "'"+birthDate+"'");
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
            for(int i = 0 ; i< list.size() ; i ++){ 
//                sql = " INSERT INTO `customer` ( `code`, `initial_name`, `first_name`, `last_name`, `nationality`, `birth_date`, `sex`, `address`, `tel`, `email`, `passport_no`, `remark`, `personal_id`, `phone`, `first_name_japan`, `last_name_japan` ) "
//                    + " VALUES ('"+list.get(i).getCode().replaceAll("'", "''")+"',"
//                    + list.get(i).getInitialname().getId() +",'"
//                    + list.get(i).getFirstName().replaceAll("'", "''").replaceAll("\\\\"," ").replaceAll("/","\\\\/")+"','"
//                    + list.get(i).getLastName().replaceAll("'", "''").replaceAll("\\\\"," ").replaceAll("/", "\\\\/")+"','"
//                    + list.get(i).getNationality().replaceAll("'", "''").replaceAll("\\\\"," ").replaceAll("/","\\\\/") +"',"
//                    + list.get(i).getBirthDate()+",'"
//                    + list.get(i).getSex().replaceAll("'", "''").replaceAll("\\\\"," ").replaceAll("/","\\\\/") +"','"
//                    + list.get(i).getPostalAddress().replaceAll("'", "''").replaceAll("\\\\"," ").replaceAll("/","\\\\/") +"','"
//                    + list.get(i).getPostalTel().replaceAll("'", "''").replaceAll("\\\\"," ").replaceAll("/","\\\\/") +"','"
//                    + list.get(i).getPostalEmail().replaceAll("'", "''").replaceAll("\\\\"," ").replaceAll("/","\\\\/") +"','"
//                    + list.get(i).getPassportNo().replaceAll("'", "''").replaceAll("\\\\"," ").replaceAll("/","\\\\/") +"','"
//                    + list.get(i).getWarning().replaceAll("'", "''").replaceAll("\\\\"," ").replaceAll("/","\\\\/") +"','"
//                    + list.get(i).getCitizenNo().replaceAll("'", "''").replaceAll("\\\\"," ").replaceAll("/","\\\\/") +"','"
//                    + list.get(i).getMobileNo().replaceAll("'", "''").replaceAll("\\\\"," ").replaceAll("/","\\\\/") +"','"
//                    + list.get(i).getFirstNameJapan().replaceAll("'", "''").replaceAll("\\\\"," ").replaceAll("/","\\\\/") +"','"
//                    + list.get(i).getLastNameJapan().replaceAll("'", "''").replaceAll("\\\\"," ").replaceAll("/","\\\\/") +"' ) ";
                try {
                    String sqlselect = " SELECT id FROM `customer` where code = '"+list.get(i).getCode()+"' and first_name = '"+list.get(i).getFirstName()+"' and last_name = '"+list.get(i).getLastName()+"' ";
                    String id = "";
                    ResultSet rs2 = stm.executeQuery(sqlselect);
                    while (rs2.next()){
                        id = rs2.getString("ID") == null ? "" : new String(rs2.getString("ID").getBytes("ISO8859_1"),"TIS-620");
                    }
                    String sqlupdate = " UPDATE `customer` SET code = '"+new String(list.get(i).getCode().getBytes("ISO8859_1"),"TIS-620")+"' WHERE id = '"+id+"'  ";
                    stm.executeUpdate(sqlupdate);
//                    stm.executeUpdate(sql);
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }catch (UnsupportedEncodingException ex) {
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
                    + "VALUES ('"+list.get(i).getCode()+"','"+list.get(i).getName().replaceAll("'", "''")+"','"+list.get(i).getRemark().replaceAll("'", "''")+"','"
                    + list.get(i).getAddress().replaceAll("'", "''")+"','"+list.get(i).getTelNo()+"','"+list.get(i).getFax()+"','"+list.get(i).getEmail()+"','"
                    + list.get(i).getWeb()+"',"+country+" , " +city+ "  ) ";
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
                String id = rs.getString("ID") == null ? "" : new String(rs.getString("ID").getBytes("ISO8859_1"),"TIS-620");
                String costad = rs.getString("COST") == null ? null : new String(rs.getString("COST").getBytes("ISO8859_1"),"TIS-620");
                String costch = rs.getString("COST_CHILD") == null ? null : new String(rs.getString("COST_CHILD").getBytes("ISO8859_1"),"TIS-620");
                String costin = rs.getString("COST_INFANT") == null ? null : new String(rs.getString("COST_INFANT").getBytes("ISO8859_1"),"TIS-620");
                String pricead = rs.getString("PRICE_ADULT") == null ? null : new String(rs.getString("PRICE_ADULT").getBytes("ISO8859_1"),"TIS-620");
                String pricech = rs.getString("PRICE_CHILD") == null ? null : new String(rs.getString("PRICE_CHILD").getBytes("ISO8859_1"),"TIS-620");
                String pricein = rs.getString("PRICE_INFANT") == null ? null : new String(rs.getString("PRICE_INFANT").getBytes("ISO8859_1"),"TIS-620");
                                        
                MainMigrateModel product = new MainMigrateModel();
                product.setCode(code);
                product.setName(name);
                product.setDescription(description);
                product.setRemark(remarks);
                MProductType mProductType = new MProductType();
                mProductType.setId(productype);
                product.setProductType(mProductType);
                product.setId(id);
                product.setCostad(costad);
                product.setCostch(costch);
                product.setCostin(costin);
                product.setPricead(pricead);
                product.setPricech(pricech);
                product.setPricein(pricein);
                list.add(product);
            }
        } catch (SQLException e ) {
            e.printStackTrace();
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
        Date date = new Date();
        String month = String.valueOf(date.getMonth() + 1);
        String day = String.valueOf(date.getDate());
        if (month.length() == 1) {
            month = "0" + month;
        }
        if (day.length() == 1) {
            day = "0" + day;
        }
        String datestring = String.valueOf(date.getYear() + 1900) + "-" + month + "-" + day;
        
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
                sql = " INSERT INTO `product` ( `code`,`name`,`description`,`remark`,`product_type` ) "
                        + "VALUES ('"+list.get(i).getCode()
                        + "','"+list.get(i).getName().replaceAll("'", "''")
                        + "','"+list.get(i).getDescription().replaceAll("'", "''")
                        + "','"+list.get(i).getRemark().replaceAll("'", "''")
                        + "',"+list.get(i).getProductType().getId()+") ";
                try {
                    stm.executeUpdate(sql);
                    String sqlselectproductid = " SELECT id FROM `product` where code = '" +list.get(i).getCode()+ "' ";
                    String id = "";
                    ResultSet rs2 = stm.executeQuery(sqlselectproductid);
                    while (rs2.next()){
                        id = rs2.getString("ID") == null ? "" : new String(rs2.getString("ID").getBytes("ISO8859_1"),"TIS-620");
                    }
                    String sqlinsertpd = " INSERT INTO product_detail ( product_id, effective_from, effective_to, ad_cost, ch_cost, in_cost, ad_price, ch_price, in_price, create_by, create_date )  "
                        + " VALUES ('"+id+ "','2016-05-01','2020-12-31',"
                        + list.get(i).getCostad() + ","
                        + list.get(i).getCostch() + ","
                        + list.get(i).getCostin() + ","
                        + list.get(i).getPricead() + ","
                        + list.get(i).getPricech() + ","
                        + list.get(i).getPricein() + ",'"
                        + "ADMIN" + "','"
                        + datestring + "' ) ";
                    
                    stm.executeUpdate(sqlinsertpd);
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }catch (UnsupportedEncodingException ex) {
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
                        + "VALUES ('"+list.get(i).getCode()+"','"+list.get(i).getName().replaceAll("'", "''")+"','"
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
                    sql = " INSERT INTO `m_airline` (`code`,`name`,`code_3_letter` ) VALUES ('"+list.get(i).getCode().substring(0,5)+"','"+list.get(i).getName().replaceAll("'", "''")+"','"+list.get(i).getCode3Letter().replaceAll("'", "''")+"'); " ;
                }else{
                    sql = " INSERT INTO `m_airline` (`code`,`name`,`code_3_letter` ) VALUES ('"+list.get(i).getCode()+"','"+list.get(i).getName().replaceAll("'", "''")+"','"+list.get(i).getCode3Letter().replaceAll("'", "''")+"'); " ;
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
                    sql = " INSERT INTO `m_currency` (`CODE`,`DESCRIPTION`) VALUES ('"+list.get(i).getCode().substring(0,3)+"','"+list.get(i).getDescription().replaceAll("'", "''")+"'); " ;
                }else{
                    sql = " INSERT INTO `m_currency` (`CODE`,`DESCRIPTION`) VALUES ('"+list.get(i).getCode()+"','"+list.get(i).getDescription().replaceAll("'", "''")+"'); " ;
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
                    sql = " INSERT INTO `m_country` (`code`,`name`) VALUES ('"+mCountrys.get(i).getCode().substring(0,3)+"','"+mCountrys.get(i).getName().replaceAll("'", "''")+"'); " ;
                }else{
                    sql = " INSERT INTO `m_country` (`code`,`name`) VALUES ('"+mCountrys.get(i).getCode()+"','"+mCountrys.get(i).getName().replaceAll("'", "''")+"'); " ;
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
                    sql = " INSERT INTO `m_city` (`code`,`name`) VALUES ('"+mCitys.get(i).getCode().substring(0,3)+"','"+mCitys.get(i).getName().replaceAll("'", "''")+"'); " ;
                }else{
                    sql = " INSERT INTO `m_city` (`code`,`name`) VALUES ('"+mCitys.get(i).getCode()+"','"+mCitys.get(i).getName().replaceAll("'", "''")+"'); " ;
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
    private static final String ip = "192.168.0.100";
    private static final String port = "1521";
    private static final String schema   = "ORCL";
    private static final String username = "travox3";
    private static final String password = "mik;v8";
    
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
    private static final String ip = "192.168.0.100";
    private static final String port = "3306";
    private static final String schema   = "smitravel_production";
    private static final String username = "DEV01";
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