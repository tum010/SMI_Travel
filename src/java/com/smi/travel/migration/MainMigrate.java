/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.migration;

import com.smi.travel.controller.excel.master.UtilityExcelFunction;
import com.smi.travel.util.UtilityFunction;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;

/**
 *
 * @author Surachai
 */
public class MainMigrate {
    
    public static void main(String[] args) {
        String name = "TaxInvoiceReport";
        Connection connect = null;
        Statement s = null;  
        Statement stmt = null;
        
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy");
        UtilityFunction util = new UtilityFunction();
        
        List reptax = new ArrayList<ReportTaxInvoice>();
         try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         connect = DriverManager.getConnection("jdbc:oracle:thin:@192.168.99.48:1521/ORCL","travox3","oracle");
         s = connect.createStatement();
      
         if (connect != null) {
         System.out.println("Database Connected.");
//         String sql = "SELECT SALE.*, agt.tax_no, agt. BRANCH, agt.branch_no FROM ACCTSMI3.REPORT_TAX_INVOICE sale INNER JOIN \"TRAVOX3\".\"AC_TAX_INVOICE\" tax ON tax.\"ID\" = sale.TAX_ID LEFT JOIN TRAVOX3.AGENT AGT ON AGT.code = tax.TAX_INV_TO AND AGT.\"NAME\" = TAX.TAX_INV_NAME WHERE TO_CHAR (sale.TAX_DATE, 'mm') = '11' AND TO_CHAR (sale.TAX_DATE, 'yyyy') = '2014' ORDER BY sale.invoice_type, sale.TAX_NO, sale.TAX_DATE ;" ;		
            String sql = " SELECT SALE.*, agt.tax_no AS TAXNO, agt. BRANCH, agt.branch_no FROM ACCTSMI3.REPORT_TAX_INVOICE sale INNER JOIN \"TRAVOX3\".\"AC_TAX_INVOICE\" tax ON tax.\"ID\" = sale.TAX_ID LEFT JOIN TRAVOX3.AGENT AGT ON AGT.code = tax.TAX_INV_TO AND AGT.\"NAME\" = TAX.TAX_INV_NAME WHERE TO_CHAR (sale.TAX_DATE, 'mm') = '11' AND TO_CHAR (sale.TAX_DATE, 'yyyy') = '2014' ORDER BY sale.invoice_type, sale.TAX_NO, sale.TAX_DATE " ;
//            " and to_char(tax.TAX_DATE,'yyyy') = '2014'   order by tax.invoice_type,tax.TAX_NO, tax.TAX_DATE ";
		          
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
            try {
                FileOutputStream out = 
                        new FileOutputStream(new File("C:\\Users\\Jittima\\Desktop\\ExcelFile\\TaxInvoiceReport.xls"));
                wb.write(out);
                out.close();
                System.out.println("Excel written successfully..");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            
        } catch (SQLException e ) {
//            JDBCTutorialUtilities.printSQLException(e);
        } finally {
            if (stmt != null) { stmt.close(); }
        }

         } else {
            System.out.println("Database Connect Failed.");
         }
     //    writer.close();

         } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         }

         // Close
         try {
         if (connect != null) {
         connect.close();
         }
         } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         }
    }
}
