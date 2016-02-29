/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller.excel.checking;

import com.smi.travel.controller.excel.master.UtilityExcelFunction;
import com.smi.travel.datalayer.entity.OverdueSummartExcel;
import com.smi.travel.datalayer.report.model.BillAirAgent;
import com.smi.travel.datalayer.view.entity.StockInvoiceSummaryView;
import com.smi.travel.datalayer.view.entity.StockNonInvoiceSummaryView;
import java.awt.Color;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/**
 *
 * @author Kanokporn
 */
public class OverdueSummaryExcel extends AbstractExcelView{
    private static final String Overdue = "Overdue";
    private static final String StockInvoiceSummary = "StockInvoiceSummary";
    private static final String StockNonInvoiceSummary = "StockNonInvoiceSummary";

    @Override
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest hsr, HttpServletResponse response) throws Exception {
        String name = (String) model.get("name");
        System.out.println("name : " + name);
        response.setHeader("Content-disposition", "attachment; filename=" + name + ".xls");
        
        if(name.equalsIgnoreCase(Overdue)){
            System.out.println("gen report OverdueSummary");
            getOverdueSummary(workbook, (List) model.get(name));
        }else if(name.equalsIgnoreCase(StockInvoiceSummary)){
            System.out.println("gen report StockInvoiceSummary");
            getStockInvoiceSummary(workbook, (List) model.get(name));
        }else if(name.equalsIgnoreCase(StockNonInvoiceSummary)){
            System.out.println("gen report StockNonInvoiceSummary");
            getStockNonInvoiceSummary(workbook, (List) model.get(name));
        }
    }
    
    private void getOverdueSummary(HSSFWorkbook wb, List refundTicket) {
        String sheetName = "overdueSummary";// name of sheet
        HSSFSheet sheet1 = wb.createSheet(sheetName);
        UtilityExcelFunction excelFunction = new UtilityExcelFunction();
        HSSFDataFormat currency = wb.createDataFormat();
        HSSFCellStyle styleAlignRightBorderAllHeaderTable = wb.createCellStyle();
                styleAlignRightBorderAllHeaderTable.setFont(excelFunction.getHeaderTable(wb.createFont()));
                styleAlignRightBorderAllHeaderTable.setAlignment(styleAlignRightBorderAllHeaderTable.ALIGN_CENTER);
                styleAlignRightBorderAllHeaderTable.setBorderTop(styleAlignRightBorderAllHeaderTable.BORDER_THIN);
                styleAlignRightBorderAllHeaderTable.setBorderBottom(styleAlignRightBorderAllHeaderTable.BORDER_THIN);
                styleAlignRightBorderAllHeaderTable.setBorderRight(styleAlignRightBorderAllHeaderTable.BORDER_THIN);
                styleAlignRightBorderAllHeaderTable.setBorderLeft(styleAlignRightBorderAllHeaderTable.BORDER_THIN);
        HSSFCellStyle styleAlignRightBorderAllNumber = wb.createCellStyle();
                styleAlignRightBorderAllNumber.setAlignment(styleAlignRightBorderAllNumber.ALIGN_RIGHT);
                styleAlignRightBorderAllNumber.setDataFormat(currency.getFormat("#,##0.00"));
                styleAlignRightBorderAllNumber.setBorderTop(styleAlignRightBorderAllNumber.BORDER_THIN);
                styleAlignRightBorderAllNumber.setBorderBottom(styleAlignRightBorderAllNumber.BORDER_THIN);
                styleAlignRightBorderAllNumber.setBorderRight(styleAlignRightBorderAllNumber.BORDER_THIN);
        HSSFCellStyle styleAlignRightBorderAll = wb.createCellStyle();
                styleAlignRightBorderAll.setAlignment(styleAlignRightBorderAll.ALIGN_LEFT);
                styleAlignRightBorderAll.setBorderTop(styleAlignRightBorderAll.BORDER_THIN);
                styleAlignRightBorderAll.setBorderBottom(styleAlignRightBorderAll.BORDER_THIN);
                styleAlignRightBorderAll.setBorderRight(styleAlignRightBorderAll.BORDER_THIN);
                styleAlignRightBorderAll.setBorderLeft(styleAlignRightBorderAll.BORDER_THIN);
        HSSFCellStyle styleNumber = wb.createCellStyle();
                        styleNumber.setAlignment(styleNumber.ALIGN_RIGHT);
                        styleNumber.setDataFormat(currency.getFormat("#,##0.00"));
        HSSFCellStyle styleNumberBorderRight = wb.createCellStyle();
                        styleNumberBorderRight.setAlignment(styleNumberBorderRight.ALIGN_RIGHT);
                        styleNumberBorderRight.setDataFormat(currency.getFormat("#,##0.00"));
                        styleNumberBorderRight.setBorderRight(styleNumberBorderRight.BORDER_THIN);
        HSSFCellStyle styleBorderBottom = wb.createCellStyle(); //use
                        styleBorderBottom.setBorderBottom(styleBorderBottom.BORDER_THIN);
                        styleAlignRightBorderAllNumber.setBorderLeft(styleAlignRightBorderAllNumber.BORDER_THIN);
        HSSFCellStyle styleAlignRightBorderBottomRight = wb.createCellStyle();//use
                styleAlignRightBorderBottomRight.setAlignment(styleAlignRightBorderBottomRight.ALIGN_LEFT);
                styleAlignRightBorderBottomRight.setBorderBottom(styleAlignRightBorderBottomRight.BORDER_THIN);
                styleAlignRightBorderBottomRight.setBorderRight(styleAlignRightBorderBottomRight.BORDER_THIN);
        HSSFCellStyle styleAlignRightBorderAllColor = wb.createCellStyle();
                styleAlignRightBorderAllColor.setFont(excelFunction.getHeaderTable(wb.createFont()));
                styleAlignRightBorderAllColor.setAlignment(styleAlignRightBorderAllColor.ALIGN_LEFT);
                styleAlignRightBorderAllColor.setBorderTop(styleAlignRightBorderAllColor.BORDER_THIN);
                styleAlignRightBorderAllColor.setBorderBottom(styleAlignRightBorderAllColor.BORDER_THIN);
                styleAlignRightBorderAllColor.setBorderRight(styleAlignRightBorderAllColor.BORDER_THIN);
                styleAlignRightBorderAllColor.setBorderLeft(styleAlignRightBorderAllColor.BORDER_THIN);
                styleAlignRightBorderAllColor.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
                
        HSSFCellStyle styleAlignRightBorderAllDetailTable = wb.createCellStyle();
                styleAlignRightBorderAllDetailTable.setFont(excelFunction.getHeadDetailBoldFont(wb.createFont()));
                styleAlignRightBorderAllDetailTable.setAlignment(styleAlignRightBorderAllDetailTable.ALIGN_LEFT);
                styleAlignRightBorderAllDetailTable.setBorderTop(styleAlignRightBorderAllDetailTable.BORDER_THIN);
                styleAlignRightBorderAllDetailTable.setBorderBottom(styleAlignRightBorderAllDetailTable.BORDER_THIN);
                styleAlignRightBorderAllDetailTable.setBorderRight(styleAlignRightBorderAllDetailTable.BORDER_THIN);
                styleAlignRightBorderAllDetailTable.setBorderLeft(styleAlignRightBorderAllDetailTable.BORDER_THIN);        
                
        // set Header Report (Row 1)
        HSSFCellStyle styleC11 = wb.createCellStyle();
        HSSFRow row01 = sheet1.createRow(0);
        HSSFCell cell01 = row01.createCell(0);
        cell01.setCellValue("Overdue Summary");
        styleC11.setFont(excelFunction.getHeaderFont(wb.createFont()));
        cell01.setCellStyle(styleC11);
        sheet1.addMergedRegion(CellRangeAddress.valueOf("A1:G1"));
        
        List<OverdueSummartExcel> listOver = new ArrayList<OverdueSummartExcel>();
        if(refundTicket != null && refundTicket.size() != 0){
            listOver = refundTicket;
        }else{
            listOver = null;
        }
        OverdueSummartExcel over = new OverdueSummartExcel();
        if((refundTicket != null) && (refundTicket.size() != 0)){
            over = (OverdueSummartExcel)refundTicket.get(0);
        }
        // Set align Text
        HSSFCellStyle styleC21 = wb.createCellStyle();
        styleC21.setAlignment(styleC21.ALIGN_RIGHT);
        HSSFCellStyle styleC22 = wb.createCellStyle();
        styleC22.setAlignment(styleC22.ALIGN_LEFT);

        // Row 2
        HSSFRow row02 = sheet1.createRow(1);
        HSSFCell cell021 = row02.createCell(0);
            cell021.setCellValue("Client : ");
            cell021.setCellStyle(styleC21);
        HSSFCell cell022 = row02.createCell(1);
            cell022.setCellValue(over.getClientname_page());
            cell022.setCellStyle(styleC22);
            sheet1.addMergedRegion(CellRangeAddress.valueOf("B2:D2"));
        HSSFCell cell023 = row02.createCell(4);
            cell023.setCellValue("Agent : ");
            cell023.setCellStyle(styleC21);
        HSSFCell cell024 = row02.createCell(5);
            cell024.setCellValue(over.getStaffname_page());
            cell024.setCellStyle(styleC22);

        // Row 3
        HSSFRow row03 = sheet1.createRow(2);
        HSSFCell cell031 = row03.createCell(0);
            cell031.setCellValue("Date : ");
            cell031.setCellStyle(styleC21);
        HSSFCell cell032 = row03.createCell(1);
            cell032.setCellValue(over.getFrom_page());
            cell032.setCellStyle(styleC22);
            sheet1.addMergedRegion(CellRangeAddress.valueOf("B3:D3"));
        HSSFCell cell033 = row03.createCell(4);
            cell033.setCellValue("Vat Type : ");
            cell033.setCellStyle(styleC21);
        HSSFCell cell034 = row03.createCell(5);
            cell034.setCellValue(over.getVattype_page());
            cell034.setCellStyle(styleC22);

        // Row 4
        HSSFRow row04 = sheet1.createRow(3);
        HSSFCell cell041 = row04.createCell(0);
            cell041.setCellValue("Department : ");
            cell041.setCellStyle(styleC21);
        HSSFCell cell042 = row04.createCell(1);
            cell042.setCellValue(over.getDepart_page());
            cell042.setCellStyle(styleC22);
            sheet1.addMergedRegion(CellRangeAddress.valueOf("B4:D4"));
        HSSFCell cell043 = row03.createCell(4);
            cell043.setCellValue("View : ");
            cell043.setCellStyle(styleC21);
        HSSFCell cell044 = row03.createCell(5);
            cell044.setCellValue(over.getView_page());
            cell044.setCellStyle(styleC22);

        // Row 5
        HSSFRow row05 = sheet1.createRow(4);
        HSSFCell cell051 = row05.createCell(0);
            cell051.setCellValue("Group : ");
            cell051.setCellStyle(styleC21);
            sheet1.addMergedRegion(CellRangeAddress.valueOf("B5:D5"));
        HSSFCell cell052 = row05.createCell(1);
            cell052.setCellValue(over.getGroup_page());
            cell052.setCellStyle(styleC22);

         // Header Table
        HSSFRow row6 = sheet1.createRow(8);
        HSSFCell cell61 = row6.createCell(0);
            cell61.setCellValue("Invoice No.");
            cell61.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(0);
        HSSFCell cell62 = row6.createCell(1);
            cell62.setCellValue("Date");
            cell62.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(1);
        HSSFCell cell63 = row6.createCell(2);
            cell63.setCellValue("Detail");
            sheet1.autoSizeColumn(2);
            cell63.setCellStyle(styleAlignRightBorderAllHeaderTable);
        HSSFCell cell64 = row6.createCell(3);
            cell64.setCellValue("Bath");
            cell64.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(3);
        HSSFCell cell65 = row6.createCell(4);
            cell65.setCellValue("JPY");
            cell65.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(4);
        HSSFCell cell66 = row6.createCell(5);
            cell66.setCellValue("USD");
            cell66.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(5);
        HSSFCell cell67 = row6.createCell(6);
            cell67.setCellValue("Rec No");
            cell67.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(6);
        HSSFCell cell68 = row6.createCell(7);
            cell68.setCellValue("Rec Amt");
            cell68.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(7);
        HSSFCell cell69 = row6.createCell(8);
            cell69.setCellValue("Department");
            cell69.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(8);
        HSSFCell cell70 = row6.createCell(9);
            cell70.setCellValue("Credit");
            cell70.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(9);
        HSSFCell cell71 = row6.createCell(10);
            cell71.setCellValue("Ref No");
            cell71.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(10);
        HSSFCell cell72 = row6.createCell(11);
            cell72.setCellValue("Due Date");
            cell72.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(11);
        HSSFCell cell73 = row6.createCell(12);
            cell73.setCellValue("Overdue Status");
            cell73.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(12);
            

        
        int count = 9 ;
                //Detail of Table
        if(listOver != null){
            for (int r = 0 ; r < listOver.size(); r++) {
//                System.out.println("Size " + (r)+" : " + listOver.get(r).getStaffname_page() );
                
                count = 9 + listOver.size();
            }
        }
        
        int start = 11;
        int end = 0;
        int num = 0;
        if(listOver != null && listOver.size() != 0){
            for (int r = 9 ; r < count; r++) {          
                if(num <= (listOver.size()-1)){
//                    if("Owner".equals(listOver.get(num-1).getGroup())){
                        if(num != 0){ // Check not row first
                            if("Owner".equals(listOver.get(num-1).getGroup())){
                                String temp = listOver.get(num-1).getOwnername();
                                if(temp.equals(listOver.get(num).getOwnername())){ // equal type	
                                    if(num  != (listOver.size()-1)){ // check not last row
                                        HSSFRow row = sheet1.createRow(r);
                                        createCell(row,listOver,num,styleAlignRightBorderAllNumber,styleAlignRightBorderAll);
                                        sheet1.autoSizeColumn(13);
                                        num++; 
                                    }else{ // last row
                                        end = r+1;					
                                        HSSFRow row = sheet1.createRow(r);
                                        createCell(row,listOver,num,styleAlignRightBorderAllNumber,styleAlignRightBorderAll);                                                    
                                        sheet1.autoSizeColumn(13);
                                        num++;
                                    }
                                }else{ // not equal type
                                    if(num  == (listOver.size()-1)){ // check  last row
                                        end = r+1;					
                                        HSSFRow row = sheet1.createRow(r);
                                        createCell(row,listOver,num,styleAlignRightBorderAllNumber,styleAlignRightBorderAll);                                                    
                                        sheet1.autoSizeColumn(13);
                                        num++;                           
                                    }else{                                                                       
                                        // Start New Row (Group)
                                        HSSFRow row0 = sheet1.createRow(r);
                                        HSSFCell cell = row0.createCell(0);
                                            cell.setCellValue(listOver.get(num).getOwnername());
                                            cell.setCellStyle(styleAlignRightBorderAllDetailTable);
                                        
                                        String add = "A"+(r+1)+":M"+(r+1)+"";
                                        sheet1.addMergedRegion(CellRangeAddress.valueOf(add));
                                        row0.createCell(12).setCellStyle(styleAlignRightBorderAllColor);
                                        HSSFRow row122 = sheet1.createRow(r+1);
                                        createCell(row122,listOver,num,styleAlignRightBorderAllNumber,styleAlignRightBorderAll);
                                        sheet1.autoSizeColumn(13);
                                        num++;				 
                                        count = count + 1;
                                        r = r + 1;
                                    }
                                }
                            }else if("Agent".equals(listOver.get(num-1).getGroup())){
                                String temp = listOver.get(num-1).getInvto();
                                if(temp.equals(listOver.get(num).getInvto())){ // equal type	
                                    if(num  != (listOver.size()-1)){ // check not last row
                                        HSSFRow row = sheet1.createRow(r);
                                        createCell(row,listOver,num,styleAlignRightBorderAllNumber,styleAlignRightBorderAll);
                                        sheet1.autoSizeColumn(13);
                                        num++; 
                                    }else{ // last row
                                        end = r+1;					
                                        HSSFRow row = sheet1.createRow(r);
                                        createCell(row,listOver,num,styleAlignRightBorderAllNumber,styleAlignRightBorderAll);                                                    
                                        sheet1.autoSizeColumn(13);
                                        num++;
                                    }
                                }else{ // not equal type
                                    if(num  == (listOver.size()-1)){ // check  last row
                                        end = r+1;					
//                                        System.out.println("Num : " + num + " Last Row : " + (listOver.size()-1));
//                                        System.out.println("Start : " + start +  " End  : " + end);
//                                        System.out.println("Last");
                                        HSSFRow row = sheet1.createRow(r);
                                        createCell(row,listOver,num,styleAlignRightBorderAllNumber,styleAlignRightBorderAll);                                                    
                                        sheet1.autoSizeColumn(13);
                                        num++;                           
                                    }else{                                                                       
                                        // Start New Row (Group)
                                        HSSFRow row0 = sheet1.createRow(r);
                                        HSSFCell cell = row0.createCell(0);
                                        cell.setCellValue(listOver.get(num).getInvto());
                                        cell.setCellStyle(styleAlignRightBorderAllDetailTable);
                                        String add = "A"+(r+1)+":M"+(r+1)+"";
//                                        System.out.println("Add : " + add);
                                        sheet1.addMergedRegion(CellRangeAddress.valueOf(add));
                                        row0.createCell(12).setCellStyle(styleAlignRightBorderAllColor);
                                        HSSFRow row122 = sheet1.createRow(r+1);
                                        createCell(row122,listOver,num,styleAlignRightBorderAllNumber,styleAlignRightBorderAll);
                                        sheet1.autoSizeColumn(13);
                                        num++;				 
                                        count = count + 1;
                                        r = r + 1;
                                    }
                                }
                            }
                        }else{ // row first
//                            System.out.println("Num : " + num + " Last Row : " + (listOver.size()-1));
                            HSSFRow row0 = sheet1.createRow(r);
                            if("Owner".equals(listOver.get(num).getGroup())){  
                                HSSFCell cell = row0.createCell(0);
                                cell.setCellValue(listOver.get(num).getOwnername());  
                                cell.setCellStyle(styleAlignRightBorderAllDetailTable);
                            }else if("Agent".equals(listOver.get(num).getGroup())){
                                HSSFCell cell = row0.createCell(0);
                                cell.setCellValue(listOver.get(num).getInvto());
                                cell.setCellStyle(styleAlignRightBorderAllDetailTable);
                            }
                            
                            String add = "A"+(r+1)+":M"+(r+1)+"";
//                            System.out.println("Add : " + add);
                            sheet1.addMergedRegion(CellRangeAddress.valueOf(add));
                            row0.createCell(12).setCellStyle(styleAlignRightBorderAllColor);
                            HSSFRow row = sheet1.createRow(r+1);
                            createCell(row,listOver,num,styleAlignRightBorderAllNumber,styleAlignRightBorderAll);                        
                            sheet1.autoSizeColumn(13);
                            num = num + 1;
                            count = count + 1;
                            r = r + 1;
                        }
//                    }// Owner
//                    else if ("Agent".equals(listOver.get(num-1).getInvto())){
//                        if(num != 0){ // Check not row first
//                            String temp = listOver.get(num-1).getInvto();
//                            if(temp.equals(listOver.get(num).getInvto())){ // equal type	
//                                System.out.println("Num : " + num + " Last Row : " + (listOver.size()-1));
//                                if(num  != (listOver.size()-1)){ // check not last row
//                                    HSSFRow row = sheet1.createRow(r);
//                                    createCell(row,listOver,num,styleAlignRightBorderAllNumber,styleAlignRightBorderAll);
//                                    sheet1.autoSizeColumn(13);
//                                    num++; 
//                                }else{ // last row
//                                    end = r+1;					
//                                    System.out.println("Num : " + num + " Last Row : " + (listOver.size()-1));
//                                    System.out.println("Start : " + start +  " End  : " + end);
//                                    System.out.println("Last");
//                                    HSSFRow row = sheet1.createRow(r);
//                                    createCell(row,listOver,num,styleAlignRightBorderAllNumber,styleAlignRightBorderAll);                                                    
//                                    sheet1.autoSizeColumn(13);
//                                    num++;
//                                }
//                            }else{ // not equal type
//                                if(num  == (listOver.size()-1)){ // check  last row
//                                    end = r+1;					
//                                    System.out.println("Num : " + num + " Last Row : " + (listOver.size()-1));
//                                    System.out.println("Start : " + start +  " End  : " + end);
//                                    System.out.println("Last");
//                                    HSSFRow row = sheet1.createRow(r);
//                                    createCell(row,listOver,num,styleAlignRightBorderAllNumber,styleAlignRightBorderAll);                                                    
//                                    sheet1.autoSizeColumn(13);
//                                    num++;                           
//                                }else{                                                                       
//                                    // Start New Row (Group)
//                                    HSSFRow row0 = sheet1.createRow(r);
//                                    HSSFCell cell = row0.createCell(0);
//                                        cell.setCellValue(listOver.get(num).getInvto());
//                                    row0.createCell(12).setCellStyle(styleAlignRightBorderAll);
//                                    String add = "A"+(r+1)+":M"+(r+1)+"";
//                                    System.out.println("Add : " + add);
//                                    sheet1.addMergedRegion(CellRangeAddress.valueOf(add));
//                                    HSSFRow row122 = sheet1.createRow(r+1);
//                                    createCell(row122,listOver,num,styleAlignRightBorderAllNumber,styleAlignRightBorderAll);
//                                    sheet1.autoSizeColumn(13);
//                                    num++;				 
//                                    count = count + 1;
//                                    r = r + 1;
//                                }
//                            }
//                        }else{ // row first
//                            System.out.println("Num : " + num + " Last Row : " + (listOver.size()-1));
//
//                            HSSFRow row0 = sheet1.createRow(r);
//                            HSSFCell cell = row0.createCell(0);
//                                cell.setCellValue(listOver.get(num).getInvto());
//                            row0.createCell(12).setCellStyle(styleAlignRightBorderAll);
//                            String add = "A"+(r+1)+":M"+(r+1)+"";
//                            System.out.println("Add : " + add);
//                            sheet1.addMergedRegion(CellRangeAddress.valueOf(add));
//
//                            HSSFRow row = sheet1.createRow(r+1);
//                            createCell(row,listOver,num,styleAlignRightBorderAllNumber,styleAlignRightBorderAll);                        
//                            sheet1.autoSizeColumn(13);
//                            num = num + 1;
//                            count = count + 1;
//                            r = r + 1;
//                        }
//                    }                
                }
                for(int i = 0 ; i < 30 ; i++){
                    sheet1.autoSizeColumn(i);
                }
            }
        }
    }
    
    private void createCell(HSSFRow row,List<OverdueSummartExcel> listAgent,int num,HSSFCellStyle styleNumber,HSSFCellStyle styleDetail){
        UtilityExcelFunction excelFunction = new UtilityExcelFunction();
        
        HSSFCellStyle styleNum = styleNumber;
        HSSFCellStyle styleDe = styleDetail;
        HSSFCell cell1 = row.createCell(0);
            cell1.setCellValue(listAgent.get(num).getInvno());
            cell1.setCellStyle(styleDe);
        HSSFCell cell2 = row.createCell(1);
            cell2.setCellValue(listAgent.get(num).getDate());
            cell2.setCellStyle(styleDe);
        HSSFCell cell3 = row.createCell(2);
            cell3.setCellValue(listAgent.get(num).getDetail());
            cell3.setCellStyle(styleDe);
        HSSFCell cell4 = row.createCell(3);
//            cell4.setCellValue(listAgent.get(num).getBath());
            cell4.setCellValue("".equalsIgnoreCase(String.valueOf(listAgent.get(num).getBath())) ? 0 : (new BigDecimal(listAgent.get(num).getBath())).doubleValue());
            cell4.setCellStyle(styleNum);
        HSSFCell cell55 = row.createCell(4);
//            cell55.setCellValue(listAgent.get(num).getJpy());
            cell55.setCellValue("".equalsIgnoreCase(String.valueOf(listAgent.get(num).getJpy())) ? 0 : (new BigDecimal(listAgent.get(num).getJpy())).doubleValue());
            cell55.setCellStyle(styleNum);
        HSSFCell cell5 = row.createCell(5);
//            cell5.setCellValue(listAgent.get(num).getUsd());
            cell5.setCellValue("".equalsIgnoreCase(String.valueOf(listAgent.get(num).getUsd())) ? 0 : (new BigDecimal(listAgent.get(num).getUsd())).doubleValue());
            cell5.setCellStyle(styleNum);
        HSSFCell cell6 = row.createCell(6);
            cell6.setCellValue(listAgent.get(num).getRecno());
            cell6.setCellStyle(styleNum);
        HSSFCell cell7 = row.createCell(7);
//            cell7.setCellValue(listAgent.get(num).getRecamt());
            cell7.setCellValue("".equalsIgnoreCase(String.valueOf(listAgent.get(num).getRecamt())) ? 0 : (new BigDecimal(listAgent.get(num).getRecamt())).doubleValue());
            cell7.setCellStyle(styleNum);
        HSSFCell cell8 = row.createCell(8);
            cell8.setCellValue(listAgent.get(num).getDepartment());
            cell8.setCellStyle(styleDe);
        HSSFCell cell9 = row.createCell(9);
            cell9.setCellValue(listAgent.get(num).getCredit());
            cell9.setCellStyle(styleDe);
        HSSFCell cell10 = row.createCell(10);
            cell10.setCellValue(listAgent.get(num).getRefno());
            cell10.setCellStyle(styleDe);
        HSSFCell cell11 = row.createCell(11);
            cell11.setCellValue(listAgent.get(num).getDuedate());
            cell11.setCellStyle(styleDe);
        HSSFCell cell12 = row.createCell(12);
            cell12.setCellValue(listAgent.get(num).getOverduesstatus());
            cell12.setCellStyle(styleDe);
    }

    private void getStockInvoiceSummary(HSSFWorkbook wb, List stockInvoiceSummary) {
        String sheetStockInvoiceSummary = "StockInvoiceSummary";// name of sheet

        UtilityExcelFunction excelFunction = new UtilityExcelFunction();

        HSSFSheet sheet = wb.createSheet(sheetStockInvoiceSummary);

        HSSFDataFormat currency = wb.createDataFormat();
        // Set align Text
        HSSFCellStyle styleC21 = wb.createCellStyle();
        styleC21.setAlignment(styleC21.ALIGN_RIGHT);
        HSSFCellStyle styleC22 = wb.createCellStyle();
        styleC22.setAlignment(styleC22.ALIGN_LEFT);
        HSSFCellStyle styleC23 = wb.createCellStyle();
        styleC23.setAlignment(styleC23.ALIGN_CENTER);

        HSSFCellStyle styleC25 = wb.createCellStyle();
        styleC25.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC25.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC25.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleC25.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC25.setVerticalAlignment(styleC25.VERTICAL_CENTER);
        styleC25.setDataFormat(currency.getFormat("#,##0.00"));

        HSSFCellStyle styleC26 = wb.createCellStyle();
        styleC26.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC26.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC26.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleC26.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC26.setDataFormat(currency.getFormat("#,##0"));
        styleC26.setAlignment(styleC22.ALIGN_CENTER);

        HSSFCellStyle styleC27 = wb.createCellStyle();
        styleC27.setAlignment(styleC27.ALIGN_RIGHT);
        styleC27.setDataFormat(currency.getFormat("#,##0.00"));

        HSSFCellStyle styleC28 = wb.createCellStyle();
        styleC28.setAlignment(styleC28.ALIGN_CENTER);
        styleC28.setDataFormat(currency.getFormat("#,##0"));

        HSSFCellStyle styleC29 = wb.createCellStyle();
        styleC29.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC29.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC29.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleC29.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC29.setVerticalAlignment(styleC29.VERTICAL_CENTER);

        HSSFCellStyle styleC30 = wb.createCellStyle();
        styleC30.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC30.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC30.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleC30.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC30.setAlignment(styleC30.ALIGN_CENTER);
        styleC30.setVerticalAlignment(styleC30.VERTICAL_CENTER);

        HSSFCellStyle styleC31 = wb.createCellStyle();
        styleC31.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC31.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC31.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleC31.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC31.setDataFormat(currency.getFormat("#,##0.00"));
        
        HSSFCellStyle styleC32 = wb.createCellStyle();
        styleC32.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC32.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC32.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleC32.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC32.setAlignment(styleC32.ALIGN_CENTER);
        styleC32.setVerticalAlignment(styleC32.VERTICAL_CENTER);
        styleC32.setWrapText(true);
        
        HSSFCellStyle styleC33 = wb.createCellStyle();
        styleC33.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC33.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC33.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleC33.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC33.setAlignment(styleC33.ALIGN_LEFT);
        styleC33.setVerticalAlignment(styleC33.VERTICAL_CENTER);
        styleC33.setWrapText(true);

        StockInvoiceSummaryView dataheader = (StockInvoiceSummaryView)stockInvoiceSummary.get(0);

        // set Header Report (Row 1)
        HSSFCellStyle styleC1 = wb.createCellStyle();
        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cell1 = row1.createCell(0);
        cell1.setCellValue("Stock Invoice Summary");
        styleC1.setFont(excelFunction.getHeaderFont(wb.createFont()));
        cell1.setCellStyle(styleC1);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:F1"));

        // Row 2
        HSSFRow row2 = sheet.createRow(1);
        HSSFCell cell21 = row2.createCell(0);
        cell21.setCellValue("Product : ");
        cell21.setCellStyle(styleC21);
        HSSFCell cell22 = row2.createCell(1);
        cell22.setCellValue(dataheader.getProductHeader());
        cell22.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B2:D2"));
        HSSFCell cell23 = row2.createCell(4);
        cell23.setCellValue("Inv To : ");
        cell23.setCellStyle(styleC21);
        HSSFCell cell24 = row2.createCell(5);
        cell24.setCellValue(dataheader.getInvtoHeader());
        cell24.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("F2:I2"));

        // Row 3
        HSSFRow row3 = sheet.createRow(2);
        HSSFCell cell31 = row3.createCell(0);
        cell31.setCellValue("Effective Date : ");
        cell31.setCellStyle(styleC21);
        HSSFCell cell32 = row3.createCell(1);
        cell32.setCellValue(dataheader.getEffectivedateHeader());
        cell32.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B3:D3"));
        HSSFCell cell33 = row3.createCell(4);
        cell33.setCellValue("Invoice Date : ");
        cell33.setCellStyle(styleC21);
        HSSFCell cell34 = row3.createCell(5);
        cell34.setCellValue(dataheader.getInvoicedateHeader());
        cell34.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("F3:I3"));

        // Row 4
        HSSFRow row4 = sheet.createRow(3);
        HSSFCell cell41 = row4.createCell(0);
        cell41.setCellValue("Add Date : ");
        cell41.setCellStyle(styleC21);
        HSSFCell cell42 = row4.createCell(1);
        cell42.setCellValue(dataheader.getAdddateHeader());
        cell42.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B4:D4"));       
        
        // Header Table
        HSSFCellStyle styleC3 = wb.createCellStyle();
        styleC3.setFont(excelFunction.getHeaderTable(wb.createFont()));
        styleC3.setAlignment(styleC3.ALIGN_CENTER);
        styleC3.setVerticalAlignment(styleC3.VERTICAL_CENTER);
        styleC3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC3.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC3.setBorderTop(HSSFCellStyle.BORDER_THIN);
        
        HSSFCellStyle styletop = wb.createCellStyle();
        styletop.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styletop.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styletop.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styletop.setAlignment(styletop.ALIGN_CENTER);
        styletop.setFont(excelFunction.getHeaderTable(wb.createFont()));
        styletop.setVerticalAlignment(styletop.VERTICAL_CENTER);
        
        HSSFCellStyle stylebottom = wb.createCellStyle();
        stylebottom.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        stylebottom.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        stylebottom.setBorderRight(HSSFCellStyle.BORDER_THIN);
        stylebottom.setAlignment(styletop.ALIGN_CENTER);
        stylebottom.setFont(excelFunction.getHeaderTable(wb.createFont()));
        stylebottom.setVerticalAlignment(styletop.VERTICAL_CENTER);
        
        HSSFRow row5 = sheet.createRow(5);
        HSSFCell cell51 = row5.createCell(0);
        cell51.setCellValue("Ref No");
        cell51.setCellStyle(styletop);
        HSSFCell cell62 = row5.createCell(1);
        cell62.setCellValue("Owner");
        cell62.setCellStyle(styletop);
        HSSFCell cell63 = row5.createCell(2);
        cell63.setCellValue("Inv No");
        cell63.setCellStyle(styletop);
        HSSFCell cell64 = row5.createCell(3);
        cell64.setCellValue("Inv Name");
        cell64.setCellStyle(styletop);
        HSSFCell cell65 = row5.createCell(4);
        cell65.setCellValue("Inv Date");
        cell65.setCellStyle(styletop);
        HSSFCell cell66 = row5.createCell(5);
        cell66.setCellValue("Item No");
        cell66.setCellStyle(styletop);
        sheet.autoSizeColumn(5);
        HSSFCell cell67 = row5.createCell(6);
        cell67.setCellValue("Item type");
        cell67.setCellStyle(styletop);
        HSSFCell cell68 = row5.createCell(7);
        cell68.setCellValue("Cost");
        cell68.setCellStyle(styletop);
        HSSFCell cell69 = row5.createCell(8);
        cell69.setCellValue("Sale Price");
        cell69.setCellStyle(styletop);
        HSSFCell cell610 = row5.createCell(9);
        cell610.setCellValue("Profit");
        cell610.setCellStyle(styletop);
        HSSFCell cell611 = row5.createCell(10);
        cell611.setCellValue("Stock No");
        cell611.setCellStyle(styletop);
        
        //Detail of Table
        int count = 6 ;
        for(int i=0;i<stockInvoiceSummary.size();i++){
            StockInvoiceSummaryView data = (StockInvoiceSummaryView) stockInvoiceSummary.get(i);
            HSSFRow row = sheet.createRow(count + i);
            
            HSSFCell celldata0 = row.createCell(0);
            celldata0.setCellValue(!"".equalsIgnoreCase(data.getRefno()) && data.getRefno() != null ? data.getRefno() : "");
            celldata0.setCellStyle(styleC30);
            
            HSSFCell celldata1 = row.createCell(1);
            celldata1.setCellValue(!"".equalsIgnoreCase(data.getOwner()) && data.getOwner() != null ? data.getOwner() : "");
            celldata1.setCellStyle(styleC30);
            
            HSSFCell celldata2 = row.createCell(2);
            celldata2.setCellValue(!"".equalsIgnoreCase(data.getInvno()) && data.getInvno() != null ? data.getInvno() : "");
            celldata2.setCellStyle(styleC33);
            
            HSSFCell celldata3 = row.createCell(3);
            celldata3.setCellValue(!"".equalsIgnoreCase(data.getInvname()) && data.getInvname() != null ? data.getInvname() : "");
            celldata3.setCellStyle(styleC29);
            
            HSSFCell celldata4 = row.createCell(4);
            celldata4.setCellValue(!"".equalsIgnoreCase(data.getInvdate()) && data.getInvdate() != null ? data.getInvdate() : "");
            celldata4.setCellStyle(styleC33);
            
            HSSFCell celldata5 = row.createCell(5);
            celldata5.setCellValue(!"".equalsIgnoreCase(data.getItemno()) && data.getItemno() != null ? data.getItemno() : "");
            celldata5.setCellStyle(styleC30);
            
            HSSFCell celldata6 = row.createCell(6);
            celldata6.setCellValue(!"".equalsIgnoreCase(data.getItemtype()) && data.getItemtype() != null ? data.getItemtype() : "");
            celldata6.setCellStyle(styleC30);
            
            HSSFCell celldata7 = row.createCell(7);
            celldata7.setCellValue(!"".equalsIgnoreCase(data.getCost()) && data.getCost() != null ? new BigDecimal(data.getCost()).doubleValue() : 0);
            celldata7.setCellStyle(styleC25);
  
            HSSFCell celldata8 = row.createCell(8);
            celldata8.setCellValue(!"".equalsIgnoreCase(data.getSaleprice()) && data.getSaleprice() != null ? new BigDecimal(data.getSaleprice()).doubleValue() : 0);
            celldata8.setCellStyle(styleC25);
            
            HSSFCell celldata9 = row.createCell(9);
            celldata9.setCellValue(!"".equalsIgnoreCase(data.getProfit()) && data.getProfit() != null ? new BigDecimal(data.getProfit()).doubleValue() : 0);
            celldata9.setCellStyle(styleC25);
            
            HSSFCell celldata10 = row.createCell(10);
            celldata10.setCellValue(!"".equalsIgnoreCase(data.getStockno()) && data.getStockno() != null ? data.getStockno() : "");
            celldata10.setCellStyle(styleC29);
                       
        }
        
        for(int j =0;j<11;j++){
            sheet.autoSizeColumn(j);
        }
        
//        sheet.setColumnWidth(0, 256*15);
//        sheet.setColumnWidth(1, 256*15);
        sheet.setColumnWidth(2, 256*15);
//        sheet.setColumnWidth(3, 256*15);
//        sheet.setColumnWidth(4, 256*25);
//        sheet.setColumnWidth(5, 256*25);
//        sheet.setColumnWidth(6, 256*15);
//        sheet.setColumnWidth(10, 256*15);
//        sheet.setColumnWidth(11, 256*15);
//        sheet.setColumnWidth(12, 256*15);
//        sheet.setColumnWidth(13, 256*15);
//        sheet.setColumnWidth(14, 256*15);
//        sheet.setColumnWidth(15, 256*15);
//        sheet.setColumnWidth(16, 256*15);
//        sheet.setColumnWidth(17, 256*15);
//        sheet.setColumnWidth(18, 256*15);
//        sheet.setColumnWidth(19, 256*15);

    }

    private void getStockNonInvoiceSummary(HSSFWorkbook wb, List stockNonInvoiceSummary) {
        String sheetStockNonInvoiceSummary = "StockNonInvoiceSummary";// name of sheet

        UtilityExcelFunction excelFunction = new UtilityExcelFunction();

        HSSFSheet sheet = wb.createSheet(sheetStockNonInvoiceSummary);

        HSSFDataFormat currency = wb.createDataFormat();
        // Set align Text
        HSSFCellStyle styleC21 = wb.createCellStyle();
        styleC21.setAlignment(styleC21.ALIGN_RIGHT);
        HSSFCellStyle styleC22 = wb.createCellStyle();
        styleC22.setAlignment(styleC22.ALIGN_LEFT);
        HSSFCellStyle styleC23 = wb.createCellStyle();
        styleC23.setAlignment(styleC23.ALIGN_CENTER);

        HSSFCellStyle styleC25 = wb.createCellStyle();
        styleC25.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC25.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC25.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleC25.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC25.setVerticalAlignment(styleC25.VERTICAL_CENTER);
        styleC25.setDataFormat(currency.getFormat("#,##0.00"));

        HSSFCellStyle styleC26 = wb.createCellStyle();
        styleC26.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC26.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC26.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleC26.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC26.setDataFormat(currency.getFormat("#,##0"));
        styleC26.setAlignment(styleC26.ALIGN_CENTER);

        HSSFCellStyle styleC27 = wb.createCellStyle();
        styleC27.setAlignment(styleC27.ALIGN_RIGHT);
        styleC27.setDataFormat(currency.getFormat("#,##0.00"));

        HSSFCellStyle styleC28 = wb.createCellStyle();
        styleC28.setAlignment(styleC28.ALIGN_CENTER);
        styleC28.setDataFormat(currency.getFormat("#,##0"));

        HSSFCellStyle styleC29 = wb.createCellStyle();
        styleC29.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC29.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC29.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleC29.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC29.setVerticalAlignment(styleC29.VERTICAL_CENTER);

        HSSFCellStyle styleC30 = wb.createCellStyle();
        styleC30.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC30.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC30.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleC30.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC30.setAlignment(styleC30.ALIGN_CENTER);
        styleC30.setVerticalAlignment(styleC30.VERTICAL_CENTER);

        HSSFCellStyle styleC31 = wb.createCellStyle();
        styleC31.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC31.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC31.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleC31.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC31.setDataFormat(currency.getFormat("#,##0.00"));
        
        HSSFCellStyle styleC32 = wb.createCellStyle();
        styleC32.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC32.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC32.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleC32.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC32.setAlignment(styleC32.ALIGN_CENTER);
        styleC32.setVerticalAlignment(styleC32.VERTICAL_CENTER);
        styleC32.setWrapText(true);

        StockNonInvoiceSummaryView dataheader = (StockNonInvoiceSummaryView)stockNonInvoiceSummary.get(0);

        // set Header Report (Row 1)
        HSSFCellStyle styleC1 = wb.createCellStyle();
        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cell1 = row1.createCell(0);
        cell1.setCellValue("Stock Non Invoice Summary");
        styleC1.setFont(excelFunction.getHeaderFont(wb.createFont()));
        cell1.setCellStyle(styleC1);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:F1"));

        // Row 2
        HSSFRow row2 = sheet.createRow(1);
        HSSFCell cell21 = row2.createCell(0);
        cell21.setCellValue("Product : ");
        cell21.setCellStyle(styleC21);
        HSSFCell cell22 = row2.createCell(1);
        cell22.setCellValue(dataheader.getProductHeader());
        cell22.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B2:D2"));
        HSSFCell cell23 = row2.createCell(4);
        cell23.setCellValue("Invoice Sup : ");
        cell23.setCellStyle(styleC21);
        HSSFCell cell24 = row2.createCell(5);
        cell24.setCellValue(dataheader.getInvoicesupHeader());
        cell24.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("F2:H2"));

        // Row 3
        HSSFRow row3 = sheet.createRow(2);
        HSSFCell cell31 = row3.createCell(0);
        cell31.setCellValue("Effective Date : ");
        cell31.setCellStyle(styleC21);
        HSSFCell cell32 = row3.createCell(1);
        cell32.setCellValue(dataheader.getEffectivedateHeader());
        cell32.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B3:D3"));
        HSSFCell cell33 = row3.createCell(4);
        cell33.setCellValue("Pay Date : ");
        cell33.setCellStyle(styleC21);
        HSSFCell cell34 = row3.createCell(5);
        cell34.setCellValue(dataheader.getPaydateHeader());
        cell34.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("F3:H3"));

        // Row 4
        HSSFRow row4 = sheet.createRow(3);
        HSSFCell cell41 = row4.createCell(0);
        cell41.setCellValue("Add Date : ");
        cell41.setCellStyle(styleC21);
        HSSFCell cell42 = row4.createCell(1);
        cell42.setCellValue(dataheader.getAdddateHeader());
        cell42.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B4:D4"));       
        
        // Header Table
        HSSFCellStyle styleC3 = wb.createCellStyle();
        styleC3.setFont(excelFunction.getHeaderTable(wb.createFont()));
        styleC3.setAlignment(styleC3.ALIGN_CENTER);
        styleC3.setVerticalAlignment(styleC3.VERTICAL_CENTER);
        styleC3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC3.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC3.setBorderTop(HSSFCellStyle.BORDER_THIN);
        
        HSSFCellStyle styletop = wb.createCellStyle();
        styletop.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styletop.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styletop.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styletop.setAlignment(styletop.ALIGN_CENTER);
        styletop.setFont(excelFunction.getHeaderTable(wb.createFont()));
        styletop.setVerticalAlignment(styletop.VERTICAL_CENTER);
        
        HSSFCellStyle stylebottom = wb.createCellStyle();
        stylebottom.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        stylebottom.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        stylebottom.setBorderRight(HSSFCellStyle.BORDER_THIN);
        stylebottom.setAlignment(styletop.ALIGN_CENTER);
        stylebottom.setFont(excelFunction.getHeaderTable(wb.createFont()));
        stylebottom.setVerticalAlignment(styletop.VERTICAL_CENTER);
        
        HSSFRow row5 = sheet.createRow(5);
        HSSFCell cell51 = row5.createCell(0);
        cell51.setCellValue("Item No");
        cell51.setCellStyle(styletop);
        HSSFCell cell62 = row5.createCell(1);
        cell62.setCellValue("Item Type");
        cell62.setCellStyle(styletop);
        HSSFCell cell63 = row5.createCell(2);
        cell63.setCellValue("Ref No");
        cell63.setCellStyle(styletop);
        HSSFCell cell64 = row5.createCell(3);
        cell64.setCellValue("Owner");
        cell64.setCellStyle(styletop);
        HSSFCell cell65 = row5.createCell(4);
        cell65.setCellValue("Pay No");
        cell65.setCellStyle(styletop);
        HSSFCell cell66 = row5.createCell(5);
        cell66.setCellValue("Pay Date");
        cell66.setCellStyle(styletop);
        HSSFCell cell67 = row5.createCell(6);
        cell67.setCellValue("Invoice Sup");
        cell67.setCellStyle(styletop);
        HSSFCell cell68 = row5.createCell(7);
        cell68.setCellValue("Cost");
        cell68.setCellStyle(styletop);
        HSSFCell cell69 = row5.createCell(8);
        cell69.setCellValue("Stock No");
        cell69.setCellStyle(styletop);
        
        //Detail of Table
        int count = 6 ;
        for(int i=0;i<stockNonInvoiceSummary.size();i++){
            StockNonInvoiceSummaryView data = (StockNonInvoiceSummaryView) stockNonInvoiceSummary.get(i);
            HSSFRow row = sheet.createRow(count + i);
            
            HSSFCell celldata0 = row.createCell(0);
            celldata0.setCellValue(!"".equalsIgnoreCase(data.getItemno()) && data.getItemno() != null ? data.getItemno() : "");
            celldata0.setCellStyle(styleC29);
            
            HSSFCell celldata1 = row.createCell(1);
            celldata1.setCellValue(!"".equalsIgnoreCase(data.getItemtype()) && data.getItemtype() != null ? data.getItemtype() : "");
            celldata1.setCellStyle(styleC30);
            
            HSSFCell celldata2 = row.createCell(2);
            celldata2.setCellValue(!"".equalsIgnoreCase(data.getRefno()) && data.getRefno() != null ? data.getRefno() : "");
            celldata2.setCellStyle(styleC30);
            
            HSSFCell celldata3 = row.createCell(3);
            celldata3.setCellValue(!"".equalsIgnoreCase(data.getOwner()) && data.getOwner() != null ? data.getOwner() : "");
            celldata3.setCellStyle(styleC29);                      
            
            HSSFCell celldata4 = row.createCell(4);
            celldata4.setCellValue(!"".equalsIgnoreCase(data.getPayno()) && data.getPayno() != null ? data.getPayno() : "");
            celldata4.setCellStyle(styleC32);
            
            HSSFCell celldata5 = row.createCell(5);
            celldata5.setCellValue(!"".equalsIgnoreCase(data.getPaydate()) && data.getPaydate() != null ? data.getPaydate() : "");
            celldata5.setCellStyle(styleC30);
            
            HSSFCell celldata6 = row.createCell(6);
            celldata6.setCellValue(!"".equalsIgnoreCase(data.getInvoicesup()) && data.getInvoicesup() != null ? data.getInvoicesup() : "");
            celldata6.setCellStyle(styleC29);
            
            HSSFCell celldata7 = row.createCell(7);
            celldata7.setCellValue(!"".equalsIgnoreCase(data.getCost()) && data.getCost() != null ? new BigDecimal(data.getCost()).doubleValue() : 0);
            celldata7.setCellStyle(styleC25);
  
            HSSFCell celldata8 = row.createCell(8);
            celldata8.setCellValue(!"".equalsIgnoreCase(data.getStockno()) && data.getStockno() != null ? data.getStockno() : "");
            celldata8.setCellStyle(styleC29);
                       
        }
        
        for(int j =0;j<9;j++){
            sheet.autoSizeColumn(j);
        }
        
//        sheet.setColumnWidth(0, 256*15);
//        sheet.setColumnWidth(1, 256*15);
//        sheet.setColumnWidth(2, 256*15);
//        sheet.setColumnWidth(3, 256*15);
//        sheet.setColumnWidth(4, 256*25);
//        sheet.setColumnWidth(5, 256*25);
//        sheet.setColumnWidth(6, 256*15);
//        sheet.setColumnWidth(10, 256*15);
//        sheet.setColumnWidth(11, 256*15);
//        sheet.setColumnWidth(12, 256*15);
//        sheet.setColumnWidth(13, 256*15);
//        sheet.setColumnWidth(14, 256*15);
//        sheet.setColumnWidth(15, 256*15);
//        sheet.setColumnWidth(16, 256*15);
//        sheet.setColumnWidth(17, 256*15);
//        sheet.setColumnWidth(18, 256*15);
//        sheet.setColumnWidth(19, 256*15);

    }
}
