/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.controller.excel.booking;

import com.smi.travel.controller.excel.master.UtilityExcelFunction;
import com.smi.travel.datalayer.view.entity.OutboundProductSummaryExcel;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/**
 *
 * @author Surachai
 */
public class OutboundProductSummary extends AbstractExcelView  {
    private static final String OutboundProductSummary = "OutboundProductSummary";

    @Override
    protected void buildExcelDocument(Map model, HSSFWorkbook workbook,HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = (String) model.get("name");
        System.out.println("name : " + name);
        response.setHeader("Content-disposition", "attachment; filename=" + name + ".xls");
        
        if(name.equalsIgnoreCase(OutboundProductSummary)){
            System.out.println("gen report OutboundProductSummary");
            getOutboundProductSummary(workbook, (List) model.get(name));
        }
    }

    private void getOutboundProductSummary(HSSFWorkbook wb, List listOutboundProductSummary) {
        List<OutboundProductSummaryExcel> listOutboundProduct = listOutboundProductSummary;
        String sheetName = "Sheet1";// name of sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        UtilityExcelFunction excelFunction = new UtilityExcelFunction();
        // Set align Text
        HSSFCellStyle styleAlignRight = wb.createCellStyle();
        styleAlignRight.setAlignment(styleAlignRight.ALIGN_RIGHT);
        HSSFCellStyle styleAlignLeft = wb.createCellStyle();
        styleAlignLeft.setAlignment(styleAlignLeft.ALIGN_LEFT);
        HSSFDataFormat currency = wb.createDataFormat();
        // Set align Text
        HSSFCellStyle styleNumber = wb.createCellStyle();
        styleNumber.setAlignment(styleNumber.ALIGN_RIGHT);
        styleNumber.setDataFormat(currency.getFormat("#,##0.00"));
        
         // set Header Report (Row 1)
        HSSFCellStyle styleHeader1 = wb.createCellStyle();
        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cell1 = row1.createCell(1);
        cell1.setCellValue("Outbound Product Summary");
        styleHeader1.setFont(excelFunction.getHeaderFont(wb.createFont()));
        cell1.setCellStyle(styleHeader1);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B1:I1"));
        
        if(listOutboundProduct != null && listOutboundProduct.size() != 0){
            OutboundProductSummaryExcel outboundProduct = new OutboundProductSummaryExcel();
            outboundProduct = listOutboundProduct.get(0);
            
            HSSFRow row2 = sheet.createRow(1);
            HSSFCell cell21 = row2.createCell(0);
                cell21.setCellValue("Product : ");
                cell21.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(0);
            HSSFCell cell22 = row2.createCell(1);
                cell22.setCellValue(outboundProduct.getProductname());
                cell22.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(1);
            HSSFCell cell23 = row2.createCell(2);
                cell23.setCellValue("Pay By : ");
                cell23.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(2);
            HSSFCell cell24 = row2.createCell(3);
                cell24.setCellValue(outboundProduct.getPayby());
                cell24.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(3);
                
            HSSFRow row3 = sheet.createRow(2);
            HSSFCell cell31 = row3.createCell(0);
                cell31.setCellValue("Sale Date : ");
                cell31.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(0);
            HSSFCell cell32 = row3.createCell(1);
                cell32.setCellValue(outboundProduct.getSaledate());
                cell32.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(1);
            HSSFCell cell33 = row3.createCell(2);
                cell33.setCellValue("Bank Transfer : ");
                cell33.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(2);
            HSSFCell cell34 = row3.createCell(3);
                cell34.setCellValue(outboundProduct.getBankpage());
                cell34.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(3);
                
            HSSFRow row4 = sheet.createRow(3);
            HSSFCell cell41 = row4.createCell(0);
                cell41.setCellValue("Sale By : ");
                cell41.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(0);
            HSSFCell cell42 = row4.createCell(1);
                cell42.setCellValue(outboundProduct.getSeller());
                cell42.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(1);
            HSSFCell cell43 = row4.createCell(2);
                cell43.setCellValue("Status : ");
                cell43.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(2);
            HSSFCell cell44 = row4.createCell(3);
                cell44.setCellValue(outboundProduct.getStatuspage());
                cell44.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(3);
        }
        
        // Header Table
        HSSFCellStyle styleHeader = wb.createCellStyle();
            styleHeader.setFont(excelFunction.getHeaderTable(wb.createFont()));
            styleHeader.setAlignment(styleHeader.ALIGN_CENTER);
            styleHeader.setBorderTop(styleHeader.BORDER_THIN);
            styleHeader.setBorderLeft(styleHeader.BORDER_THIN);
            styleHeader.setBorderBottom(styleHeader.BORDER_THIN);
            styleHeader.setBorderRight(styleHeader.BORDER_THIN);
        HSSFCellStyle styleDetailTable = wb.createCellStyle();
            styleDetailTable.setAlignment(styleDetailTable.ALIGN_LEFT);
            styleDetailTable.setBorderLeft(styleDetailTable.BORDER_THIN);
            styleDetailTable.setBorderRight(styleDetailTable.BORDER_THIN);
        HSSFCellStyle styleDetailTableNumber = wb.createCellStyle();
            styleDetailTableNumber.setAlignment(styleDetailTableNumber.ALIGN_RIGHT);
            styleDetailTableNumber.setBorderLeft(styleDetailTableNumber.BORDER_THIN);
            styleDetailTableNumber.setBorderRight(styleDetailTableNumber.BORDER_THIN);
            styleDetailTableNumber.setDataFormat(currency.getFormat("#,##0.00"));
        HSSFCellStyle styleDetailTableBorderBottom = wb.createCellStyle();
            styleDetailTableBorderBottom.setBorderTop(styleDetailTableBorderBottom.BORDER_THIN);
        
        HSSFRow row5 = sheet.createRow(7);
        HSSFCell cell51 = row5.createCell(9);
            cell51.setCellValue("PAX");
            cell51.setCellStyle(styleHeader);
            sheet.autoSizeColumn(9);
            sheet.addMergedRegion(CellRangeAddress.valueOf("J8:L8"));
        HSSFCell cell52 = row5.createCell(12);
            cell52.setCellValue("TOTAL NETT");
            cell52.setCellStyle(styleHeader);
            sheet.autoSizeColumn(12);
            sheet.addMergedRegion(CellRangeAddress.valueOf("M8:O8"));
        HSSFCell cell53 = row5.createCell(15);
            cell53.setCellValue("TOTAL SALE");
            cell53.setCellStyle(styleHeader);
            sheet.autoSizeColumn(15);
            sheet.addMergedRegion(CellRangeAddress.valueOf("P8:R8"));
            
        HSSFRow row6 = sheet.createRow(8);
        HSSFCell cell61 = row6.createCell(1);
            cell61.setCellValue("SALE DATE");
            cell61.setCellStyle(styleHeader);
            sheet.autoSizeColumn(1);
        HSSFCell cell62 = row6.createCell(2);
            cell62.setCellValue("RECORD NO");
            cell62.setCellStyle(styleHeader);
            sheet.autoSizeColumn(2);
        HSSFCell cell63 = row6.createCell(3);
            cell63.setCellValue("TRAVOX NO");
            cell63.setCellStyle(styleHeader);
            sheet.autoSizeColumn(3);
        HSSFCell cell64 = row6.createCell(4);
            cell64.setCellValue("PASS TYPE");
            cell64.setCellStyle(styleHeader);
            sheet.autoSizeColumn(4);
        HSSFCell cell65 = row6.createCell(5);
            cell65.setCellValue("PASS NO");
            cell65.setCellStyle(styleHeader);
            sheet.autoSizeColumn(5);
        HSSFCell cell66 = row6.createCell(6);
            cell66.setCellValue("DULATION");
            cell66.setCellStyle(styleHeader);
            sheet.autoSizeColumn(6);
        HSSFCell cell67 = row6.createCell(7);
            cell67.setCellValue("INV NO");
            cell67.setCellStyle(styleHeader);
            sheet.autoSizeColumn(7);
        HSSFCell cell68 = row6.createCell(8);
            cell68.setCellValue("CUSTOMER NAME");
            cell68.setCellStyle(styleHeader);
            sheet.autoSizeColumn(8);
        HSSFCell cell69 = row6.createCell(9);
            cell69.setCellValue("AD");
            cell69.setCellStyle(styleHeader);
            sheet.autoSizeColumn(1);
        HSSFCell cell70 = row6.createCell(10);
            cell70.setCellValue("CH");
            cell70.setCellStyle(styleHeader);
            sheet.autoSizeColumn(2);
        HSSFCell cell71 = row6.createCell(11);
            cell71.setCellValue("IN");
            cell71.setCellStyle(styleHeader);
            sheet.autoSizeColumn(3);
        HSSFCell cell72 = row6.createCell(12);
            cell72.setCellValue("ADULT");
            cell72.setCellStyle(styleHeader);
            sheet.autoSizeColumn(4);
        HSSFCell cell73 = row6.createCell(13);
            cell73.setCellValue("CHILD");
            cell73.setCellStyle(styleHeader);
            sheet.autoSizeColumn(5);
        HSSFCell cell74 = row6.createCell(14);
            cell74.setCellValue("INFANT");
            cell74.setCellStyle(styleHeader);
            sheet.autoSizeColumn(6);
        HSSFCell cell75 = row6.createCell(15);
            cell75.setCellValue("ADULT");
            cell75.setCellStyle(styleHeader);
            sheet.autoSizeColumn(7);
        HSSFCell cell76 = row6.createCell(16);
            cell76.setCellValue("CHILD");
            cell76.setCellStyle(styleHeader);
            sheet.autoSizeColumn(8);
        HSSFCell cell77 = row6.createCell(17);
            cell77.setCellValue("INFANT");
            cell77.setCellStyle(styleHeader);
            sheet.autoSizeColumn(1);
        HSSFCell cell78 = row6.createCell(18);
            cell78.setCellValue("PROFIT TOTAL");
            cell78.setCellStyle(styleHeader);
            sheet.autoSizeColumn(2);
        HSSFCell cell79 = row6.createCell(19);
            cell79.setCellValue("PAY BY");
            cell79.setCellStyle(styleHeader);
            sheet.autoSizeColumn(3);
        HSSFCell cell80 = row6.createCell(20);
            cell80.setCellValue("DATE TRSF");
            cell80.setCellStyle(styleHeader);
            sheet.autoSizeColumn(4);
        HSSFCell cell81 = row6.createCell(21);
            cell81.setCellValue("SALLER");
            cell81.setCellStyle(styleHeader);
            sheet.autoSizeColumn(5);
            
        sheet.addMergedRegion(new CellRangeAddress(
            7, //first row (0-based)
            8, //last row  (0-based)
            1, //first column (0-based)
            8  //last column  (0-based)
        ));
    }
}
