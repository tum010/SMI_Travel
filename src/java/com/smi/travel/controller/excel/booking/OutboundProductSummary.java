/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.controller.excel.booking;

import com.smi.travel.controller.excel.master.UtilityExcelFunction;
import com.smi.travel.datalayer.report.model.BillAirAgent;
import com.smi.travel.datalayer.view.entity.OutboundProductSummaryExcel;
import java.math.BigDecimal;
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
    private static final String OutboundProduct = "OutboundProduct";

    @Override
    protected void buildExcelDocument(Map model, HSSFWorkbook workbook,HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = (String) model.get("name");
        System.out.println("name : " + name);
        response.setHeader("Content-disposition", "attachment; filename=" + name + ".xls");
        
        if(name.equalsIgnoreCase(OutboundProduct)){
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
        HSSFCell cell1 = row1.createCell(0);
        cell1.setCellValue("Outbound Product Summary");
        styleHeader1.setFont(excelFunction.getHeaderFont(wb.createFont()));
        cell1.setCellStyle(styleHeader1);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:I1"));
        
        if(listOutboundProduct != null && listOutboundProduct.size() != 0){
            OutboundProductSummaryExcel outboundProduct = new OutboundProductSummaryExcel();
            outboundProduct = listOutboundProduct.get(0);
            
            HSSFRow row2 = sheet.createRow(1);
            HSSFCell cell21 = row2.createCell(0);
                cell21.setCellValue("Product : ");
                cell21.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(0);
            HSSFCell cell22 = row2.createCell(1);
                cell22.setCellValue(outboundProduct.getProductnamepage());
                cell22.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(1);
            HSSFCell cell23 = row2.createCell(2);
                cell23.setCellValue("Pay By : ");
                cell23.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(2);
            HSSFCell cell24 = row2.createCell(3);
                cell24.setCellValue(outboundProduct.getPaybypage());
                cell24.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(3);
                
            HSSFRow row3 = sheet.createRow(2);
            HSSFCell cell31 = row3.createCell(0);
                cell31.setCellValue("Sale Date : ");
                cell31.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(0);
            HSSFCell cell32 = row3.createCell(1);
                cell32.setCellValue(outboundProduct.getSaledatepage());
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
                cell42.setCellValue(outboundProduct.getSalebypage());
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
        HSSFCellStyle styleDetailTableCenter = wb.createCellStyle();
            styleDetailTableCenter.setAlignment(styleDetailTableCenter.ALIGN_CENTER);
            styleDetailTableCenter.setBorderTop(styleDetailTableCenter.BORDER_THIN);
            styleDetailTableCenter.setBorderBottom(styleDetailTableCenter.BORDER_THIN);
            styleDetailTableCenter.setBorderRight(styleDetailTableCenter.BORDER_THIN);
            styleDetailTableCenter.setBorderLeft(styleDetailTableCenter.BORDER_THIN);
            
        HSSFCellStyle styleDetailTableNumber = wb.createCellStyle();
            styleDetailTableNumber.setAlignment(styleDetailTableNumber.ALIGN_RIGHT);
            styleDetailTableNumber.setBorderLeft(styleDetailTableNumber.BORDER_THIN);
            styleDetailTableNumber.setBorderRight(styleDetailTableNumber.BORDER_THIN);
            styleDetailTableNumber.setDataFormat(currency.getFormat("#,##0.00"));
        HSSFCellStyle styleDetailTableBorderBottom = wb.createCellStyle();
            styleDetailTableBorderBottom.setBorderTop(styleDetailTableBorderBottom.BORDER_THIN);
        HSSFCellStyle styleBorderTop =  wb.createCellStyle();
            styleBorderTop.setBorderBottom(styleBorderTop.BORDER_THIN);
            styleBorderTop.setFont(excelFunction.getHeaderTable(wb.createFont()));
            styleBorderTop.setAlignment(styleBorderTop.ALIGN_CENTER);
        HSSFCellStyle styleBorderRight =  wb.createCellStyle();
            styleBorderRight.setBorderRight(styleBorderRight.BORDER_THIN);
            styleBorderRight.setAlignment(styleBorderRight.ALIGN_CENTER);
        HSSFCellStyle styleBorderBottomAndRight =  wb.createCellStyle();
            styleBorderBottomAndRight.setBorderRight(styleBorderBottomAndRight.BORDER_THIN);
            styleBorderBottomAndRight.setBorderBottom(styleBorderBottomAndRight.BORDER_THIN);
            styleBorderBottomAndRight.setAlignment(styleBorderBottomAndRight.ALIGN_CENTER);
        
            
            HSSFCellStyle styleAlignLeftBorderTopRight = wb.createCellStyle(); // use
                styleAlignLeftBorderTopRight.setAlignment(styleAlignLeftBorderTopRight.ALIGN_LEFT);
                styleAlignLeftBorderTopRight.setBorderTop(styleAlignLeftBorderTopRight.BORDER_THIN);
                styleAlignLeftBorderTopRight.setBorderRight(styleAlignLeftBorderTopRight.BORDER_THIN);
        HSSFCellStyle styleAlignLeftBorderTopLeft = wb.createCellStyle(); // use
                styleAlignLeftBorderTopLeft.setAlignment(styleAlignLeftBorderTopLeft.ALIGN_LEFT);
                styleAlignLeftBorderTopLeft.setBorderTop(styleAlignLeftBorderTopLeft.BORDER_THIN);
                styleAlignLeftBorderTopLeft.setBorderLeft(styleAlignLeftBorderTopLeft.BORDER_THIN);
        HSSFCellStyle styleBorderTopP = wb.createCellStyle(); // use
                styleBorderTopP.setBorderTop(styleBorderTopP.BORDER_THIN);
        HSSFCellStyle styleAlignRightBorderBottomRight = wb.createCellStyle();//use
                styleAlignRightBorderBottomRight.setAlignment(styleAlignRightBorderBottomRight.ALIGN_LEFT);
                styleAlignRightBorderBottomRight.setBorderBottom(styleAlignRightBorderBottomRight.BORDER_THIN);
                styleAlignRightBorderBottomRight.setBorderRight(styleAlignRightBorderBottomRight.BORDER_THIN);
        HSSFCellStyle styleAlignRightBorderBottomLeft = wb.createCellStyle();
                styleAlignRightBorderBottomLeft.setAlignment(styleAlignRightBorderBottomLeft.ALIGN_LEFT);
                styleAlignRightBorderBottomLeft.setBorderBottom(styleAlignRightBorderBottomLeft.BORDER_THIN);
                styleAlignRightBorderBottomLeft.setBorderLeft(styleAlignRightBorderBottomLeft.BORDER_THIN);
        HSSFCellStyle styleBorderBottom = wb.createCellStyle(); //use
                styleBorderBottom.setBorderBottom(styleBorderBottom.BORDER_THIN);
        HSSFCellStyle styleAlignRightBorderRight = wb.createCellStyle(); //use
                styleAlignRightBorderRight.setAlignment(styleAlignRightBorderRight.ALIGN_RIGHT);
                styleAlignRightBorderRight.setBorderRight(styleAlignRightBorderRight.BORDER_THIN);
        HSSFCellStyle styleAlignLeftBorderRight = wb.createCellStyle();
                styleAlignLeftBorderRight.setAlignment(styleAlignLeftBorderRight.ALIGN_LEFT);
                styleAlignLeftBorderRight.setBorderRight(styleAlignLeftBorderRight.BORDER_THIN);
        HSSFCellStyle styleAlignRightBorderLeft = wb.createCellStyle();//use
                styleAlignRightBorderLeft.setAlignment(styleAlignRightBorderLeft.ALIGN_RIGHT);
                styleAlignRightBorderLeft.setBorderLeft(styleAlignRightBorderLeft.BORDER_THIN);
        HSSFCellStyle styleAlignRightBorderAllNumber = wb.createCellStyle();
                styleAlignRightBorderAllNumber.setAlignment(styleAlignRightBorderAllNumber.ALIGN_RIGHT);
                styleAlignRightBorderAllNumber.setDataFormat(currency.getFormat("#,##0.00"));
                styleAlignRightBorderAllNumber.setBorderTop(styleAlignRightBorderAllNumber.BORDER_THIN);
                styleAlignRightBorderAllNumber.setBorderBottom(styleAlignRightBorderAllNumber.BORDER_THIN);
                styleAlignRightBorderAllNumber.setBorderRight(styleAlignRightBorderAllNumber.BORDER_THIN);
                styleAlignRightBorderAllNumber.setBorderLeft(styleAlignRightBorderAllNumber.BORDER_THIN);
        HSSFCellStyle styleAlignRightBorderAll = wb.createCellStyle();
                styleAlignRightBorderAll.setAlignment(styleAlignRightBorderAll.ALIGN_LEFT);
                styleAlignRightBorderAll.setBorderTop(styleAlignRightBorderAll.BORDER_THIN);
                styleAlignRightBorderAll.setBorderBottom(styleAlignRightBorderAll.BORDER_THIN);
                styleAlignRightBorderAll.setBorderRight(styleAlignRightBorderAll.BORDER_THIN);
                styleAlignRightBorderAll.setBorderLeft(styleAlignRightBorderAll.BORDER_THIN);
        
        HSSFRow row4 = sheet.createRow(6);
            row4.createCell(7).setCellStyle(styleBorderTop);
            row4.createCell(8).setCellStyle(styleBorderTop);
            row4.createCell(9).setCellStyle(styleBorderTop);
            row4.createCell(10).setCellStyle(styleBorderTop);
            row4.createCell(11).setCellStyle(styleBorderTop);
            row4.createCell(12).setCellStyle(styleBorderTop);
            row4.createCell(13).setCellStyle(styleBorderTop);
            row4.createCell(14).setCellStyle(styleBorderTop);
            row4.createCell(15).setCellStyle(styleBorderTop);
        HSSFRow row5 = sheet.createRow(7);
        HSSFCell cell61 = row5.createCell(0);
            cell61.setCellValue("SALE DATE");
            cell61.setCellStyle(styleHeader);
            sheet.autoSizeColumn(0);
        HSSFCell cell62 = row5.createCell(1);
            cell62.setCellValue("RECORD NO");
            cell62.setCellStyle(styleHeader);
            sheet.autoSizeColumn(1);
        HSSFCell cell63 = row5.createCell(2);
            cell63.setCellValue("REF NO");
            cell63.setCellStyle(styleHeader);
            sheet.autoSizeColumn(2);
//        HSSFCell cell64 = row5.createCell(3);
//            cell64.setCellValue("PRODUCT NAME");
//            cell64.setCellStyle(styleHeader);
//            sheet.autoSizeColumn(3);
        HSSFCell cell65 = row5.createCell(3);
            cell65.setCellValue("PASS NO");
            cell65.setCellStyle(styleHeader);
            sheet.autoSizeColumn(3);
        HSSFCell cell66 = row5.createCell(4);
            cell66.setCellValue("DULATION");
            cell66.setCellStyle(styleHeader);
            sheet.autoSizeColumn(4);
        HSSFCell cell67 = row5.createCell(5);
            cell67.setCellValue("INV NO");
            cell67.setCellStyle(styleHeader);
            sheet.autoSizeColumn(5);
        HSSFCell cell68 = row5.createCell(6);
            cell68.setCellValue("CUSTOMER NAME");
            cell68.setCellStyle(styleHeader);
            sheet.autoSizeColumn(6);
        HSSFCell cell59 = row5.createCell(7);
            cell59.setCellValue("PAX");
            cell59.setCellStyle(styleBorderTop);
            sheet.autoSizeColumn(7);
            sheet.addMergedRegion(CellRangeAddress.valueOf("H8:J8"));
        row5.createCell(9).setCellStyle(styleBorderRight);         
        HSSFCell cell627 = row5.createCell(10);
            cell627.setCellValue("TOTAL NETT");
            cell627.setCellStyle(styleBorderTop);
            sheet.autoSizeColumn(10);
            sheet.addMergedRegion(CellRangeAddress.valueOf("K8:M8"));
//        row5.createCell(12).setCellStyle(styleBorderTop);
        row5.createCell(12).setCellStyle(styleBorderRight);
        HSSFCell cell657 = row5.createCell(13);
            cell657.setCellValue("TOTAL SALE");
            cell657.setCellStyle(styleBorderTop);
            sheet.autoSizeColumn(13);
            sheet.addMergedRegion(CellRangeAddress.valueOf("N8:P8"));
//        row5.createCell(15).setCellStyle(styleBorderTop);
        row5.createCell(15).setCellStyle(styleBorderRight);
        HSSFCell cell78 = row5.createCell(16);
            cell78.setCellValue("PROFIT TOTAL");
            cell78.setCellStyle(styleHeader);
            sheet.autoSizeColumn(16);
        HSSFCell cell79 = row5.createCell(17);
            cell79.setCellValue("PAY BY");
            cell79.setCellStyle(styleHeader);
            sheet.autoSizeColumn(17);
        HSSFCell cell80 = row5.createCell(18);
            cell80.setCellValue("DATE TRSF");
            cell80.setCellStyle(styleHeader);
            sheet.autoSizeColumn(18);
        HSSFCell cell81 = row5.createCell(19);
            cell81.setCellValue("SALLER");
            cell81.setCellStyle(styleHeader);
            sheet.autoSizeColumn(19);
        
        sheet.addMergedRegion(CellRangeAddress.valueOf("A8:A9"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("B8:B9"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("C8:C9"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("D8:D9"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("E8:E9"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("F8:F9"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("G8:G9"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("Q8:Q9"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("R8:R9"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("S8:S9"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("T8:T9"));
            
        HSSFRow row6 = sheet.createRow(8);
        row6.createCell(0).setCellStyle(styleBorderBottomAndRight);
        row6.createCell(1).setCellStyle(styleBorderBottomAndRight);
        row6.createCell(2).setCellStyle(styleBorderBottomAndRight);
        row6.createCell(3).setCellStyle(styleBorderBottomAndRight);
        row6.createCell(4).setCellStyle(styleBorderBottomAndRight);
        row6.createCell(5).setCellStyle(styleBorderBottomAndRight);
        row6.createCell(6).setCellStyle(styleBorderBottomAndRight);
        HSSFCell cell69 = row6.createCell(7);
            cell69.setCellValue("AD");
            cell69.setCellStyle(styleHeader);
            sheet.autoSizeColumn(7);
        HSSFCell cell70 = row6.createCell(8);
            cell70.setCellValue("CH");
            cell70.setCellStyle(styleHeader);
            sheet.autoSizeColumn(8);
        HSSFCell cell71 = row6.createCell(9);
            cell71.setCellValue("IN");
            cell71.setCellStyle(styleHeader);
            sheet.autoSizeColumn(9);
        HSSFCell cell72 = row6.createCell(10);
            cell72.setCellValue("ADULT");
            cell72.setCellStyle(styleHeader);
            sheet.autoSizeColumn(10);
        HSSFCell cell73 = row6.createCell(11);
            cell73.setCellValue("CHILD");
            cell73.setCellStyle(styleHeader);
            sheet.autoSizeColumn(11);
        HSSFCell cell74 = row6.createCell(12);
            cell74.setCellValue("INFANT");
            cell74.setCellStyle(styleHeader);
            sheet.autoSizeColumn(12);
        HSSFCell cell75 = row6.createCell(13);
            cell75.setCellValue("ADULT");
            cell75.setCellStyle(styleHeader);
            sheet.autoSizeColumn(13);
        HSSFCell cell76 = row6.createCell(14);
            cell76.setCellValue("CHILD");
            cell76.setCellStyle(styleHeader);
            sheet.autoSizeColumn(14);
        HSSFCell cell77 = row6.createCell(15);
            cell77.setCellValue("INFANT");
            cell77.setCellStyle(styleHeader);
            sheet.autoSizeColumn(15);
        row6.createCell(16).setCellStyle(styleBorderBottomAndRight);
        row6.createCell(17).setCellStyle(styleBorderBottomAndRight);
        row6.createCell(18).setCellStyle(styleBorderBottomAndRight);
        row6.createCell(19).setCellStyle(styleBorderBottomAndRight);
        
        
        int count = 9 + listOutboundProduct.size();
        int num = 0;
        int end = 0;
        if(listOutboundProduct != null && listOutboundProduct.size() != 0){
            for (int r = 9 ; r < count; r++) {
             if(num <= (listOutboundProduct.size()-1)){
                if(num != 0){ // Check not row first
                    String temp = listOutboundProduct.get(num-1).getProductname();
                    if(temp.equals(listOutboundProduct.get(num).getProductname())){ // equal type	
                        System.out.println("Num : " + num + " Last Row : " + (listOutboundProduct.size()-1));
                        if(num  != (listOutboundProduct.size()-1)){ // check not last row
                            HSSFRow row = sheet.createRow(r);
                            createCell(row,listOutboundProduct,num,styleAlignRightBorderAllNumber,styleAlignRightBorderAll,styleDetailTableCenter);
                            if(listOutboundProduct.get(num).getInvno() != null && !"".equals(listOutboundProduct.get(num).getInvno())){
                                sheet.autoSizeColumn(5);
                            }
                            num++; 
                        }else{ // last row				
                            System.out.println("Num : " + num + " Last Row : " + (listOutboundProduct.size()-1));
                            HSSFRow row = sheet.createRow(r);
                            createCell(row,listOutboundProduct,num,styleAlignRightBorderAllNumber,styleAlignRightBorderAll,styleDetailTableCenter);                                                    
                            if(listOutboundProduct.get(num).getInvno() != null && !"".equals(listOutboundProduct.get(num).getInvno())){
                                sheet.autoSizeColumn(5);
                            }
                            num++;

                            // total
                            HSSFRow rowT = sheet.createRow(r+1);
                                rowT.createCell(0).setCellStyle(styleBorderBottom);
                                rowT.createCell(1).setCellStyle(styleBorderBottom);
                                rowT.createCell(2).setCellStyle(styleBorderBottom);
                                rowT.createCell(3).setCellStyle(styleBorderBottom);
                                rowT.createCell(4).setCellStyle(styleBorderBottom);
                                rowT.createCell(5).setCellStyle(styleBorderBottom);
                                rowT.createCell(6).setCellStyle(styleBorderBottom);
                                rowT.createCell(7).setCellStyle(styleBorderBottom);
                                rowT.createCell(8).setCellStyle(styleBorderBottom);
                                rowT.createCell(9).setCellStyle(styleBorderBottom);
                                rowT.createCell(10).setCellStyle(styleBorderBottom);
                                rowT.createCell(11).setCellStyle(styleBorderBottom);
                                rowT.createCell(12).setCellStyle(styleBorderBottom);
                                rowT.createCell(13).setCellStyle(styleBorderBottom);
                                rowT.createCell(14).setCellStyle(styleBorderBottom);
                                rowT.createCell(15).setCellStyle(styleBorderBottom);
                                rowT.createCell(16).setCellStyle(styleBorderBottom);
                                rowT.createCell(17).setCellStyle(styleBorderBottom);
                                rowT.createCell(18).setCellStyle(styleBorderBottom);
                                rowT.createCell(19).setCellStyle(styleAlignRightBorderBottomRight);
                        }
                    }else{ // not equal type
                        if(num  == (listOutboundProduct.size()-1)){ // check  last row				
                            System.out.println("Num : " + num + " Last Row : " + (listOutboundProduct.size()-1));
                            HSSFRow row = sheet.createRow(r);
                            createCell(row,listOutboundProduct,num,styleAlignRightBorderAllNumber,styleAlignRightBorderAll,styleDetailTableCenter);                                                    
                            if(listOutboundProduct.get(num).getInvno() != null && !"".equals(listOutboundProduct.get(num).getInvno())){
                                sheet.autoSizeColumn(5);
                            }
                            num++;
                                // total
                                HSSFRow rowT = sheet.createRow(r+1);
                                    rowT.createCell(0).setCellStyle(styleBorderBottom);
                                    rowT.createCell(1).setCellStyle(styleBorderBottom);
                                    rowT.createCell(2).setCellStyle(styleBorderBottom);
                                    rowT.createCell(3).setCellStyle(styleBorderBottom);
                                    rowT.createCell(4).setCellStyle(styleBorderBottom);
                                    rowT.createCell(5).setCellStyle(styleBorderBottom);
                                    rowT.createCell(6).setCellStyle(styleBorderBottom);
                                    rowT.createCell(7).setCellStyle(styleBorderBottom);
                                    rowT.createCell(8).setCellStyle(styleBorderBottom);
                                    rowT.createCell(9).setCellStyle(styleBorderBottom);
                                    rowT.createCell(10).setCellStyle(styleBorderBottom);
                                    rowT.createCell(11).setCellStyle(styleBorderBottom);
                                    rowT.createCell(12).setCellStyle(styleBorderBottom);
                                    rowT.createCell(13).setCellStyle(styleBorderBottom);
                                    rowT.createCell(14).setCellStyle(styleBorderBottom);
                                    rowT.createCell(15).setCellStyle(styleBorderBottom);
                                    rowT.createCell(16).setCellStyle(styleBorderBottom);
                                    rowT.createCell(17).setCellStyle(styleBorderBottom);
                                    rowT.createCell(18).setCellStyle(styleBorderBottom);
                                    rowT.createCell(19).setCellStyle(styleAlignRightBorderBottomRight);
                            }else{                                          
                                System.out.println("Num : " + num + " Last Row : " + (listOutboundProduct.size()-1));
                                // total
                                HSSFRow rowT = sheet.createRow(r);
                                    rowT.createCell(0).setCellStyle(styleBorderBottom);
                                    rowT.createCell(1).setCellStyle(styleBorderBottom);
                                    rowT.createCell(2).setCellStyle(styleBorderBottom);
                                    rowT.createCell(3).setCellStyle(styleBorderBottom);
                                    rowT.createCell(4).setCellStyle(styleBorderBottom);
                                    rowT.createCell(5).setCellStyle(styleBorderBottom);
                                    rowT.createCell(6).setCellStyle(styleBorderBottom);
                                    rowT.createCell(7).setCellStyle(styleBorderBottom);
                                    rowT.createCell(8).setCellStyle(styleBorderBottom);
                                    rowT.createCell(9).setCellStyle(styleBorderBottom);
                                    rowT.createCell(10).setCellStyle(styleBorderBottom);
                                    rowT.createCell(11).setCellStyle(styleBorderBottom);
                                    rowT.createCell(12).setCellStyle(styleBorderBottom);
                                    rowT.createCell(13).setCellStyle(styleBorderBottom);
                                    rowT.createCell(14).setCellStyle(styleBorderBottom);
                                    rowT.createCell(15).setCellStyle(styleBorderBottom);
                                    rowT.createCell(16).setCellStyle(styleBorderBottom);
                                    rowT.createCell(17).setCellStyle(styleBorderBottom);
                                    rowT.createCell(18).setCellStyle(styleBorderBottom);
                                    rowT.createCell(19).setCellStyle(styleAlignRightBorderBottomRight);
                                // Start New Row (Group)
                                HSSFRow row0 = sheet.createRow(r+1);
                                HSSFCell cell = row0.createCell(0);
                                    cell.setCellValue(listOutboundProduct.get(num).getProductname());
//                                    cell.setCellStyle(styleAlignRightBorderAll);
                                row0.createCell(19).setCellStyle(styleAlignRightBorderAll);
                                if(listOutboundProduct.get(num).getInvno() != null && !"".equals(listOutboundProduct.get(num).getInvno())){
                                    sheet.autoSizeColumn(5);
                                }
                                String add = "A"+(r+2)+":M"+(r+2)+"";
                                System.out.println("Add : " + add);
                                sheet.addMergedRegion(CellRangeAddress.valueOf(add));
                                HSSFRow row122 = sheet.createRow(r+2);
                                createCell(row122,listOutboundProduct,num,styleAlignRightBorderAllNumber,styleAlignRightBorderAll,styleDetailTableCenter);
                                num++;				 
                                count = count + 2;
                                r = r + 2;
                            }
                        }
                    }else{ // row first
                        System.out.println("Num : " + num + " Last Row : " + (listOutboundProduct.size()-1));
                        
                        HSSFRow row0 = sheet.createRow(r);
                        HSSFCell cell = row0.createCell(0);
                            cell.setCellValue(listOutboundProduct.get(num).getProductname());
//                            cell.setCellStyle(styleAlignRightBorderAll);
                        row0.createCell(19).setCellStyle(styleAlignRightBorderAll);
                        String add = "A"+(r+1)+":T"+(r+1)+"";
                        System.out.println("Add : " + add);
                        sheet.addMergedRegion(CellRangeAddress.valueOf(add));
                        
                        HSSFRow row = sheet.createRow(r+1);
                        createCell(row,listOutboundProduct,num,styleAlignRightBorderAllNumber,styleAlignRightBorderAll,styleDetailTableCenter);
                        if(listOutboundProduct.get(num).getInvno() != null && !"".equals(listOutboundProduct.get(num).getInvno())){
                            sheet.autoSizeColumn(5);
                        }
//                        sheet.autoSizeColumn(20);
                        num = num + 1;
                        count = count + 1;
                        r = r + 1;
                    }
                    sheet.autoSizeColumn(6);
                    sheet.autoSizeColumn(9);
                }
            }
        }
    }
    
    private void createCell(HSSFRow row,List<OutboundProductSummaryExcel> listOutboundProduct,int num,HSSFCellStyle styleNumber,HSSFCellStyle styleDetail,HSSFCellStyle styleDetailCenter){
        
        HSSFCellStyle styleDetailTableNumber = styleNumber;
        HSSFCellStyle styleDetailTable = styleDetail;
        HSSFCellStyle styleDetailTableCenter = styleDetailCenter;
        HSSFCell cell5 = row.createCell(0);
            if(listOutboundProduct.get(num).getSaledate() != null ){
                cell5.setCellValue(listOutboundProduct.get(num).getSaledate());
            }else{
                cell5.setCellValue("");
            }
            cell5.setCellStyle(styleDetailTable);
        HSSFCell cell6 = row.createCell(1);
            if(listOutboundProduct.get(num).getRecordno() != null ){
                cell6.setCellValue(listOutboundProduct.get(num).getRecordno());
            }else{
                cell6.setCellValue("");
            }
            cell6.setCellStyle(styleDetailTable);
        HSSFCell cell7 = row.createCell(2);
            if(listOutboundProduct.get(num).getTravoxno()!= null ){
                cell7.setCellValue(listOutboundProduct.get(num).getTravoxno());
            }else{
                cell7.setCellValue("");
            }
            cell7.setCellStyle(styleDetailTable);
//        HSSFCell cell8 = row.createCell(3);
//            if(listOutboundProduct.get(num).getPasstype()!= null ){
//                cell8.setCellValue(listOutboundProduct.get(num).getPasstype());
//            }else{
//                cell8.setCellValue("");
//            }
//            cell8.setCellStyle(styleDetailTable);
        HSSFCell cell9 = row.createCell(3);
            if(listOutboundProduct.get(num).getPassno()!= null ){
                cell9.setCellValue(listOutboundProduct.get(num).getPassno());
            }else{
                cell9.setCellValue("");
            }
            cell9.setCellStyle(styleDetailTable);
        HSSFCell cell10 = row.createCell(4);
            if(listOutboundProduct.get(num).getDulation()!= null ){
                cell10.setCellValue(listOutboundProduct.get(num).getDulation());
            }else{
                cell10.setCellValue("");
            }
            cell10.setCellStyle(styleDetailTableCenter);

        HSSFCell cell11 = row.createCell(5);
            if(listOutboundProduct.get(num).getInvno()!= null ){
                cell11.setCellValue(listOutboundProduct.get(num).getInvno());
            }else{
                cell11.setCellValue("");
            }
            cell11.setCellStyle(styleDetailTable);
        HSSFCell cell12 = row.createCell(6);
            if(listOutboundProduct.get(num).getInvno()!= null ){
                cell12.setCellValue(listOutboundProduct.get(num).getCustomername());
            }else{
                cell12.setCellValue("");
            }
            cell12.setCellStyle(styleDetailTable);
        HSSFCell cell13 = row.createCell(7);
            if(listOutboundProduct.get(num).getPaxad()!= null ){
                cell13.setCellValue(listOutboundProduct.get(num).getPaxad().doubleValue());
            }else{
                cell13.setCellValue("");
            }
            cell13.setCellStyle(styleDetailTableCenter);
        HSSFCell cell14 = row.createCell(8);
            if(listOutboundProduct.get(num).getPaxch() != null){
                cell14.setCellValue(listOutboundProduct.get(num).getPaxch().doubleValue());
            }else{
                cell14.setCellValue(0.00);
            }

            cell14.setCellStyle(styleDetailTableCenter);

        HSSFCell cell15 = row.createCell(9);
            if(listOutboundProduct.get(num).getPaxin() != null){
                cell15.setCellValue(listOutboundProduct.get(num).getPaxin().doubleValue());
            }else{
                cell15.setCellValue(0.00);
            }

            cell15.setCellStyle(styleDetailTableCenter);
        HSSFCell cell16 = row.createCell(10);
            if(listOutboundProduct.get(num).getTotalnettadult() != null){
                cell16.setCellValue(listOutboundProduct.get(num).getTotalnettadult().doubleValue());
            }else{
                cell16.setCellValue(0.00);
            }
            cell16.setCellStyle(styleDetailTableNumber);
        HSSFCell cell17 = row.createCell(11);
            if(listOutboundProduct.get(num).getTotalnettchild() != null){
                cell17.setCellValue(listOutboundProduct.get(num).getTotalnettchild().doubleValue());
            }else{
                cell17.setCellValue(0.00);
            }
            cell17.setCellStyle(styleDetailTableNumber);
        HSSFCell cell18 = row.createCell(12);
            if(listOutboundProduct.get(num).getTotalnettinfant() != null){
                cell18.setCellValue(listOutboundProduct.get(num).getTotalnettinfant().doubleValue());
            }else{
                cell18.setCellValue(0.00);
            }
            cell18.setCellStyle(styleDetailTableNumber);
        HSSFCell cell19 = row.createCell(13);
            if(listOutboundProduct.get(num).getTotalsaleadult() != null){
                cell19.setCellValue(listOutboundProduct.get(num).getTotalsaleadult().doubleValue());
            }else{
                cell19.setCellValue(0.00);
            }
            cell19.setCellStyle(styleDetailTableNumber);
        HSSFCell cell20 = row.createCell(14);
            if(listOutboundProduct.get(num).getTotalsalechild() != null){
                cell20.setCellValue(listOutboundProduct.get(num).getTotalsalechild().doubleValue());
            }else{
                cell20.setCellValue(0.00);
            }
            cell20.setCellStyle(styleDetailTableNumber);
        HSSFCell cell21 = row.createCell(15);
            if(listOutboundProduct.get(num).getTotalsaleinfant() != null){
                cell21.setCellValue(listOutboundProduct.get(num).getTotalsaleinfant().doubleValue());
            }else{
                cell21.setCellValue(0.00);
            }
            cell21.setCellStyle(styleDetailTableNumber);
        HSSFCell cell22 = row.createCell(16);
            if(listOutboundProduct.get(num).getProfittotal() != null){
                cell22.setCellValue(listOutboundProduct.get(num).getProfittotal().doubleValue());
            }else{
                cell22.setCellValue(0.00);
            }
            cell22.setCellStyle(styleDetailTableNumber);
        HSSFCell cell23 = row.createCell(17);
            if(listOutboundProduct.get(num).getPayby()!= null ){
                cell23.setCellValue(listOutboundProduct.get(num).getPayby());
            }else{
                cell23.setCellValue("");
            }
            cell23.setCellStyle(styleDetailTable);
        HSSFCell cell24 = row.createCell(18);
            if(listOutboundProduct.get(num).getDatetrsf()!= null ){
                cell24.setCellValue(listOutboundProduct.get(num).getDatetrsf());
            }else{
                cell24.setCellValue("");
            }
            cell24.setCellStyle(styleDetailTable);
        HSSFCell cell25 = row.createCell(19);
            if(listOutboundProduct.get(num).getSeller()!= null ){
                cell25.setCellValue(listOutboundProduct.get(num).getSeller());
            }else{
                cell25.setCellValue("");
            }
            cell25.setCellStyle(styleDetailTable);
    }
}
