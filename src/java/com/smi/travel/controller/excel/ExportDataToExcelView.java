/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller.excel;

import com.smi.travel.datalayer.report.model.TicketFareReport;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/**
 *
 * @author Jittima
 */
public class ExportDataToExcelView extends AbstractExcelView {
    private static final String TicketFareReport = "TicketFareAirlineReport";
    @Override
    protected void buildExcelDocument(Map model, HSSFWorkbook workbook,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String name = (String) model.get("name");
        System.out.println("name : " + name);
        response.setHeader("Content-disposition", "attachment; filename=" + name + ".xls");

        if (name.equalsIgnoreCase(TicketFareReport)) {
            System.out.println("gen report");
            genTicketFareAirlineReport(workbook, (List) model.get(name));
        }

    }

    public void genTicketFareAirlineReport(HSSFWorkbook wb, List Ticket) {
        List<TicketFareReport> TicketFare = Ticket;
        String sheetName = "Sheet1";// name of sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        // set Header Report (Row 1)
        HSSFCellStyle styleC1 = wb.createCellStyle();
        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cell1 = row1.createCell(0);
        cell1.setCellValue("List Ticket Fare Airline");
        styleC1.setFont(getHeaderFont(wb.createFont()));
        cell1.setCellStyle(styleC1);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:F1"));

        // Set align Text
        HSSFCellStyle styleC21 = wb.createCellStyle();
        styleC21.setAlignment(styleC21.ALIGN_RIGHT);
        HSSFCellStyle styleC22 = wb.createCellStyle();
        styleC22.setAlignment(styleC22.ALIGN_LEFT);

        // Row 2
        HSSFRow row2 = sheet.createRow(1);
        HSSFCell cell21 = row2.createCell(0);
            cell21.setCellValue("Print By : ");
            cell21.setCellStyle(styleC21);
        HSSFCell cell22 = row2.createCell(1);
            cell22.setCellValue("MS.BUSABA SUEBN ");
            cell22.setCellStyle(styleC22);
            sheet.addMergedRegion(CellRangeAddress.valueOf("B2:D2"));
        HSSFCell cell23 = row2.createCell(4);
            cell23.setCellValue("Air Line : ");
            cell23.setCellStyle(styleC21);
        HSSFCell cell24 = row2.createCell(5);
            cell24.setCellValue("ALL");
            cell24.setCellStyle(styleC22);

        // Row 3
        HSSFRow row3 = sheet.createRow(2);
        HSSFCell cell31 = row3.createCell(0);
            cell31.setCellValue("Department : ");
            cell31.setCellStyle(styleC21);
        HSSFCell cell32 = row3.createCell(1);
            cell32.setCellValue("ALL ");
            cell32.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B3:D3"));
        HSSFCell cell33 = row3.createCell(4);
            cell33.setCellValue("Ticket Buy : ");
            cell33.setCellStyle(styleC21);
        HSSFCell cell34 = row3.createCell(5);
            cell34.setCellValue("Compa");
            cell34.setCellStyle(styleC22);

        // Row 4
        HSSFRow row4 = sheet.createRow(3);
        HSSFCell cell41 = row4.createCell(0);
            cell41.setCellValue("Term Pay : ");
            cell41.setCellStyle(styleC21);
        HSSFCell cell42 = row4.createCell(1);
            cell42.setCellValue("ALL ");
            cell42.setCellStyle(styleC22);
            sheet.addMergedRegion(CellRangeAddress.valueOf("B4:D4"));
        HSSFCell cell43 = row4.createCell(4);
            cell43.setCellValue("Ticket Type : ");
            cell43.setCellStyle(styleC21);
        HSSFCell cell44 = row4.createCell(5);
            cell44.setCellValue("Domes");
            cell44.setCellStyle(styleC22);

        // Row 5
        HSSFRow row5 = sheet.createRow(4);
        HSSFCell cell51 = row5.createCell(0);
            cell51.setCellValue("Sale Staff : ");
            cell51.setCellStyle(styleC21);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A5:E5"));
        HSSFCell cell52 = row5.createCell(5);
            cell52.setCellValue("ALL ");
            cell52.setCellStyle(styleC22);
        
        // Row 6
        HSSFRow row66 = sheet.createRow(5);
        HSSFCell cell616 = row66.createCell(0);
        cell616.setCellValue("Report Of : 16-08-2015 to 31-08-2015");
        cell616.setCellStyle(styleC21);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A6:F6"));
        
        // Row 7
        HSSFRow row77 = sheet.createRow(6);
        HSSFCell cell77 = row77.createCell(0);
            cell77.setCellValue("Print on : 16-08-2015 17:15  Page : ");
            cell77.setCellStyle(styleC21);
            sheet.addMergedRegion(CellRangeAddress.valueOf("A7:E7"));
        HSSFCell cell737 = row77.createCell(5);
            cell737.setCellValue("8 of 8 ");
            cell737.setCellStyle(styleC22);

        // Header Table
        HSSFCellStyle styleC3 = wb.createCellStyle();
        styleC3.setFont(getHeaderTable(wb.createFont()));
        HSSFRow row6 = sheet.createRow(8);
        HSSFCell cell61 = row6.createCell(0);
            cell61.setCellValue("Air");
            cell61.setCellStyle(styleC3);
            sheet.autoSizeColumn(0);
        HSSFCell cell62 = row6.createCell(1);
            cell62.setCellValue("Document Number");
            cell62.setCellStyle(styleC3);
            sheet.autoSizeColumn(1);
        HSSFCell cell63 = row6.createCell(2);
            cell63.setCellValue("Issue Date");
            sheet.autoSizeColumn(2);
            cell63.setCellStyle(styleC3);
        HSSFCell cell64 = row6.createCell(3);
            cell64.setCellValue("Department");
            cell64.setCellStyle(styleC3);
            sheet.autoSizeColumn(3);
        HSSFCell cell65 = row6.createCell(4);
            cell65.setCellValue("Staff");
            cell65.setCellStyle(styleC3);
            sheet.autoSizeColumn(4);
        HSSFCell cell66 = row6.createCell(5);
            cell66.setCellValue("Term Pay");
            cell66.setCellStyle(styleC3);
            sheet.autoSizeColumn(5);
        HSSFCell cell67 = row6.createCell(6);
            cell67.setCellValue("Tax");
            cell67.setCellStyle(styleC3);
            sheet.autoSizeColumn(6);
        HSSFCell cell68 = row6.createCell(7);
            cell68.setCellValue("Actual Commission");
            cell68.setCellStyle(styleC3);
            sheet.autoSizeColumn(7);
        HSSFCell cell69 = row6.createCell(8);
            cell69.setCellValue("Insurance");
            cell69.setCellStyle(styleC3);
            sheet.autoSizeColumn(8);
        HSSFCell cell70 = row6.createCell(9);
            cell70.setCellValue("Net Sales");
            cell70.setCellStyle(styleC3);
            sheet.autoSizeColumn(9);
        HSSFCell cell71 = row6.createCell(10);
            cell71.setCellValue("Vat");
            cell71.setCellStyle(styleC3);
            sheet.autoSizeColumn(10);
        HSSFCell cell72 = row6.createCell(11);
            cell72.setCellValue("Invoice No.");
            cell72.setCellStyle(styleC3);
            sheet.autoSizeColumn(11);
        HSSFCell cell73 = row6.createCell(12);
            cell73.setCellValue("Invoice Amount");
            cell73.setCellStyle(styleC3);
            sheet.autoSizeColumn(12);
        HSSFCell cell74 = row6.createCell(13);
            cell74.setCellValue("Balance Payable");
            cell74.setCellStyle(styleC3);
            sheet.autoSizeColumn(13);
        
        //Detail of Table
        int count = 9 ;
        for(int i=0;i<TicketFare.size();i++){
             TicketFareReport data = (TicketFareReport)TicketFare.get(i);
             HSSFRow row = sheet.createRow(count + i);
             row.createCell(0).setCellValue(data.getDocno());
             row.createCell(1).setCellValue(data.getAirline());
             row.createCell(2).setCellValue(data.getIssuedate());
             row.createCell(3).setCellValue(data.getDepartment());
             row.createCell(4).setCellValue(data.getStaff());
             row.createCell(5).setCellValue(data.getTermpay());
             if(!"".equals(data.getTax()) && data.getTax() != null){
                row.createCell(6).setCellValue(data.getTax());
             }else{
                row.createCell(6).setCellValue(0.00);
             }
             if(!"".equals(data.getTicketcom()) && data.getTicketcom() != null){
                row.createCell(7).setCellValue(data.getTicketcom());
             }else{
                row.createCell(7).setCellValue(0.00);
             }
             if(!"".equals(data.getIns()) && data.getIns() != null){
                row.createCell(8).setCellValue(data.getIns());
             }else{
                row.createCell(8).setCellValue(0.00);
             }
             if(!"".equals(data.getSaleprice()) && data.getSaleprice() != null){
                   row.createCell(9).setCellValue(data.getSaleprice());
             }else{
                 row.createCell(9).setCellValue(0.00);
             }
             if(!"".equals(data.getVat()) && data.getVat() != null){
                   row.createCell(10).setCellValue(data.getVat());
             }else{
                 row.createCell(10).setCellValue(0.00);
             }
             row.createCell(11).setCellValue(data.getInvno());
             if(!"".equals(data.getInvamount()) && data.getInvamount() != null){
                  row.createCell(12).setCellValue(data.getInvamount());
             }else{
                  row.createCell(12).setCellValue(0.00);
             }
             if(!"".equals(data.getBalance()) && data.getBalance() != null){
                  row.createCell(13).setCellValue(data.getBalance());
             }else{
                  row.createCell(13).setCellValue(0.00);
             }
             
             for(int j =0;j<13;j++){
                 sheet.autoSizeColumn(j);
             }
        }
        
        // Total 
        int countRowTotal = 9 + (TicketFare.size() + 1);
        int countRowSum = 9;
        HSSFRow rowTotal = sheet.createRow(countRowTotal);
        HSSFCell cellTotal1 = rowTotal.createCell(6);
            String sumTax = "SUM(G10";
            for(int i= 11 ;i <= (TicketFare.size()+9);i++){
                 sumTax += "+G"+(i);
            }
            sumTax += ")";
            cellTotal1.setCellFormula(sumTax);
            cellTotal1.setCellStyle(styleC21);
        HSSFCell cellTotal2 = rowTotal.createCell(7);
            String sumActual = "SUM(H10";
            for(int i= 11 ;i <= (TicketFare.size()+9);i++){
                 sumActual += "+H"+(i);
            }
            sumActual += ")";
            cellTotal2.setCellFormula(sumActual);
            cellTotal2.setCellStyle(styleC21);
        HSSFCell cellTotal3 = rowTotal.createCell(8);
            String sumInsurance = "SUM(I10";
            for(int i= 11 ;i <= (TicketFare.size()+9);i++){
                 sumInsurance += "+I"+(i);
            }
            sumInsurance += ")";
            cellTotal3.setCellFormula(sumInsurance);
            cellTotal3.setCellStyle(styleC22);
        HSSFCell cellTotal4 = rowTotal.createCell(9);
            String sumNet = "SUM(J10";
            for(int i= 11 ;i <= (TicketFare.size()+9);i++){
//                if(!"".equals(TicketFare.get(i-11).getNetsale()) && TicketFare.get(i-11).getNetsale() != null){
                    sumNet += "+J"+(i);
//                }
            }
            sumNet += ")";
            cellTotal4.setCellFormula(sumNet);
            cellTotal4.setCellStyle(styleC22);
        HSSFCell cellTotal5 = rowTotal.createCell(10);
            String sumVat = "SUM(K10";
            for(int i= 11 ;i <= (TicketFare.size()+9);i++){
                 sumVat += "+K"+(i);
            }
            sumVat += ")";
            cellTotal5.setCellFormula(sumVat);
            cellTotal5.setCellStyle(styleC22);
        HSSFCell cellTotal6 = rowTotal.createCell(12);
            String sumAmount = "SUM(M10";
            for(int i= 11 ;i <= (TicketFare.size()+9);i++){
                 sumAmount += "+M"+(i);
            }
            sumAmount += ")";
            cellTotal6.setCellFormula(sumAmount);
            cellTotal6.setCellStyle(styleC22);
        HSSFCell cellTotal7 = rowTotal.createCell(13);
            String sumBalance = "SUM(N10";
            for(int i= 11 ;i <= (TicketFare.size()+9);i++){
                 sumBalance += "+N"+(i);
            }
            sumBalance += ")";
            cellTotal7.setCellFormula(sumBalance);
            cellTotal7.setCellStyle(styleC22);
       
        
      
    }

    public HSSFFont getHeaderFont(HSSFFont font) {

        font.setFontHeightInPoints((short) 30);
        font.setFontName("Calibri");
        font.setItalic(true);

        return font;
    }

    public HSSFFont getHeaderTable(HSSFFont font) {

        font.setFontHeightInPoints((short) 10);
        font.setFontName("Calibri");
        font.setBoldweight((short) 3);

        return font;
    }

    public HSSFFont getDetailFont(HSSFFont font) {

        font.setFontHeightInPoints((short) 10);
        font.setFontName("Calibri");
        font.setItalic(true);

        return font;
    }

    public HSSFFont getHeadDetailFont(HSSFFont font) {

        font.setFontHeightInPoints((short) 10);
        font.setFontName("Calibri");
        font.setBoldweight((short) 8);

        return font;
    }
}
