/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.controller.excel.booking;

import com.smi.travel.controller.excel.master.UtilityExcelFunction;
import com.smi.travel.datalayer.report.model.TicketFareReport;
import com.smi.travel.datalayer.view.entity.OutboundPackageSummaryView;
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
public class OutboundPackageSummary extends AbstractExcelView {
    private static final String OutboundPackageSummary = "OutboundPackageSummary";
    
    
    protected void buildExcelDocument(Map model, HSSFWorkbook workbook,HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = (String) model.get("name");
        System.out.println("name : " + name);
        response.setHeader("Content-disposition", "attachment; filename=" + name + ".xls");
        
        if(name.equalsIgnoreCase(OutboundPackageSummary)){
            System.out.println("gen report TicketProfitLoss");
            genOutboundPackageSummaryReport(workbook, (List) model.get(name));
        }
       
    }
    
    public void genOutboundPackageSummaryReport(HSSFWorkbook wb, List opslist) {
        UtilityExcelFunction excelFunction = new UtilityExcelFunction();
        String sheetName = "Sheet1";// name of sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        
        OutboundPackageSummaryView dataheader = new OutboundPackageSummaryView();
        
        HSSFDataFormat currency = wb.createDataFormat();
        // Set align Text
        HSSFCellStyle styleC21 = wb.createCellStyle();
        styleC21.setAlignment(styleC21.ALIGN_RIGHT);
        styleC21.setDataFormat(currency.getFormat("#,##0.00"));
        HSSFCellStyle styleC22 = wb.createCellStyle();
        styleC22.setAlignment(styleC22.ALIGN_LEFT);       
                     
        if(!opslist.isEmpty()){
            dataheader = (OutboundPackageSummaryView)opslist.get(0);
        
        // set Header Report (Row 1)
        HSSFCellStyle styleC1 = wb.createCellStyle();
        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cell1 = row1.createCell(0);
        cell1.setCellValue("Package Summary");
        styleC1.setFont(excelFunction.getHeaderFont(wb.createFont()));
        cell1.setCellStyle(styleC1);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:F1"));
        
        // Row 2
        HSSFRow row2 = sheet.createRow(1);
        HSSFCell cell21 = row2.createCell(0);
        cell21.setCellValue("City : ");
        cell21.setCellStyle(styleC21);
        HSSFCell cell22 = row2.createCell(1);
        cell22.setCellValue(dataheader.getHeadercity());
        cell22.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B2:D2"));
        HSSFCell cell23 = row2.createCell(4);
        cell23.setCellValue("Pay By : ");
        cell23.setCellStyle(styleC21);
        HSSFCell cell24 = row2.createCell(5);
        cell24.setCellValue(dataheader.getHeaderpayby());
        cell24.setCellStyle(styleC22);
        
        // Row 3
        HSSFRow row3 = sheet.createRow(2);
        HSSFCell cell31 = row3.createCell(0);
        cell31.setCellValue("Package : ");
        cell31.setCellStyle(styleC21);
        HSSFCell cell32 = row3.createCell(1);
        cell32.setCellValue(dataheader.getHeaderpackage());
        cell32.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B3:D3"));
        HSSFCell cell33 = row3.createCell(4);
        cell33.setCellValue("Bank Transfer : ");
        cell33.setCellStyle(styleC21);
        HSSFCell cell34 = row3.createCell(5);
        cell34.setCellValue(dataheader.getHeaderbank());
        cell34.setCellStyle(styleC22);

        // Row 4
        HSSFRow row4 = sheet.createRow(3);
        HSSFCell cell41 = row4.createCell(0);
        cell41.setCellValue("Date : ");
        cell41.setCellStyle(styleC21);
        HSSFCell cell42 = row4.createCell(1);
        cell42.setCellValue(dataheader.getHeaderdate());
        cell42.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B4:D4"));
        HSSFCell cell43 = row4.createCell(4);
        cell43.setCellValue("Status : ");
        cell43.setCellStyle(styleC21);
        HSSFCell cell44 = row4.createCell(5);
        cell44.setCellValue(dataheader.getHeaderstatus());
        cell44.setCellStyle(styleC22);

        }
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
//        
//        for(int i = 0 ; i < 20 ; i++){
//            HSSFRow row4 = sheet.createRow(4);
//            HSSFCell cell = row4.createCell(i);
//            cell.setCellStyle(stylebottom);
//        }
        
        HSSFRow row5 = sheet.createRow(5);
        HSSFCell cell61 = row5.createCell(0);
        cell61.setCellValue("SALE DATE");
        cell61.setCellStyle(styletop);
        HSSFCell cell62 = row5.createCell(1);
        cell62.setCellValue("CODE NO");
        cell62.setCellStyle(styletop);
        HSSFCell cell63 = row5.createCell(2);
        cell63.setCellValue("TRAVOX NO");
        cell63.setCellStyle(styletop);
        HSSFCell cell64 = row5.createCell(3);
        cell64.setCellValue("TOUR CODE");
        cell64.setCellStyle(styletop);
        HSSFCell cell65 = row5.createCell(4);
        cell65.setCellValue("TOUR NAME");
        cell65.setCellStyle(styletop);
        HSSFCell cell66 = row5.createCell(5);
        cell66.setCellValue("CUSTOMER NAME");
        cell66.setCellStyle(styletop);
        sheet.autoSizeColumn(5);
        HSSFCell cell67 = row5.createCell(6);
        cell67.setCellValue("PERIOD");
        cell67.setCellStyle(styletop);
        HSSFCell cell68 = row5.createCell(7);
        cell68.setCellValue("NUMBER OF PAX");
        cell68.setCellStyle(styletop);
        HSSFCell cell068 = row5.createCell(8);
        cell068.setCellValue("");
        cell068.setCellStyle(styletop);
        HSSFCell cell069 = row5.createCell(9);
        cell069.setCellValue("");
        cell069.setCellStyle(styletop);
        HSSFCell cell70 = row5.createCell(10);
        cell70.setCellValue("TOTAL");
        cell70.setCellStyle(styletop);
        HSSFCell cell71 = row5.createCell(11);
        cell71.setCellValue("TOTAL");
        cell71.setCellStyle(styletop);
        HSSFCell cell72 = row5.createCell(12);
        cell72.setCellValue("PROFIT");
        cell72.setCellStyle(styletop);
        HSSFCell cell73 = row5.createCell(13);
        cell73.setCellValue("BANK");
        cell73.setCellStyle(styletop);
        HSSFCell cell74 = row5.createCell(14);
        cell74.setCellValue("DATE");
        cell74.setCellStyle(styletop);
        HSSFCell cell75 = row5.createCell(15);
        cell75.setCellValue("STATUS");
        cell75.setCellStyle(styletop);
        HSSFCell cell76 = row5.createCell(16);
        cell76.setCellValue("REMARK");
        cell76.setCellStyle(styletop);
        HSSFCell cell77 = row5.createCell(17);
        cell77.setCellValue("SELLER");
        cell77.setCellStyle(styletop);
        
        sheet.addMergedRegion(CellRangeAddress.valueOf("A6:A7"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("B6:B7"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("C6:C7"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("D6:D7"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("E6:E7"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("F6:F7"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("G6:G7"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("H6:J6"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("P6:P7"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("R6:R7"));
        
        HSSFRow row6 = sheet.createRow(6);
        HSSFCell cell78 = row6.createCell(0);
        cell78.setCellStyle(stylebottom);
        HSSFCell cell79 = row6.createCell(1);
        cell79.setCellStyle(stylebottom);
        HSSFCell cell80 = row6.createCell(2);
        cell80.setCellValue("");
        cell80.setCellStyle(stylebottom);
        HSSFCell cell81 = row6.createCell(3);
        cell81.setCellValue("");
        cell81.setCellStyle(stylebottom);
        HSSFCell cell82 = row6.createCell(4);
        cell82.setCellValue("");
        cell82.setCellStyle(stylebottom);
        HSSFCell cell83 = row6.createCell(5);
        cell83.setCellValue("");
        cell83.setCellStyle(stylebottom);
        HSSFCell cell84 = row6.createCell(6);
        cell84.setCellValue("");
        cell84.setCellStyle(stylebottom);
        HSSFCell cell85 = row6.createCell(7);
        cell85.setCellValue("ADULT");
        cell85.setCellStyle(styleC3);
        HSSFCell cell86 = row6.createCell(8);
        cell86.setCellValue("CHILD");
        cell86.setCellStyle(styleC3);
        HSSFCell cell87 = row6.createCell(9);
        cell87.setCellValue("INFANT");
        cell87.setCellStyle(styleC3);       
        HSSFCell cell91 = row6.createCell(10);
        cell91.setCellValue("NETT");
        cell91.setCellStyle(stylebottom);
        HSSFCell cell92 = row6.createCell(11);
        cell92.setCellValue("SALE");
        cell92.setCellStyle(stylebottom);
        HSSFCell cell93 = row6.createCell(12);
        cell93.setCellValue("TOTAL");
        cell93.setCellStyle(stylebottom);
        HSSFCell cell94 = row6.createCell(13);
        cell94.setCellValue("TRSF");
        cell94.setCellStyle(stylebottom);
        HSSFCell cell95 = row6.createCell(14);
        cell95.setCellValue("TRSF");
        cell95.setCellStyle(stylebottom);
        HSSFCell cell96 = row6.createCell(15);
        cell96.setCellValue("");
        cell96.setCellStyle(stylebottom);
        HSSFCell cell97 = row6.createCell(16);
        cell97.setCellValue("SUPPLIER");
        cell97.setCellStyle(stylebottom);
        HSSFCell cell98 = row6.createCell(17);
        cell98.setCellValue("");
        cell98.setCellStyle(stylebottom);
        
        //Detail of Table
        int count = 7 ;
        HSSFCellStyle styleC23 = wb.createCellStyle();
        styleC23.setAlignment(styleC23.ALIGN_CENTER);
        styleC23.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC23.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC23.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleC23.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC23.setDataFormat(currency.getFormat("#,##0"));
        
        HSSFCellStyle styleC24 = wb.createCellStyle();
        styleC24.setAlignment(styleC24.ALIGN_LEFT);
        styleC24.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC24.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC24.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleC24.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        
        HSSFCellStyle styleC25 = wb.createCellStyle();
        styleC25.setAlignment(styleC25.ALIGN_RIGHT);
        styleC25.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC25.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC25.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleC25.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC25.setDataFormat(currency.getFormat("#,##0.00"));

        for(int i=0;i<opslist.size();i++){
            OutboundPackageSummaryView data = (OutboundPackageSummaryView)opslist.get(i);
            HSSFRow row = sheet.createRow(count + i);
            
            HSSFCell celldata0 = row.createCell(0);
            celldata0.setCellValue(data.getDepartdate());
            celldata0.setCellStyle(styleC23);
            
            HSSFCell celldata1 = row.createCell(1);
            celldata1.setCellValue(data.getRecondno());
            celldata1.setCellStyle(styleC24);
            
            HSSFCell celldata2 = row.createCell(2);
            celldata2.setCellValue(data.getRefno());
            celldata2.setCellStyle(styleC24);
            
            HSSFCell celldata3 = row.createCell(3);
            celldata3.setCellValue(data.getPackagecode());
            celldata3.setCellStyle(styleC24);
            
            HSSFCell celldata4 = row.createCell(4);
            celldata4.setCellValue(data.getPackagename());
            celldata4.setCellStyle(styleC24);
            
            HSSFCell celldata5 = row.createCell(5);
            celldata5.setCellValue(data.getLeader());
            celldata5.setCellStyle(styleC24);
            
            HSSFCell celldata6 = row.createCell(6);
            celldata6.setCellValue(data.getPeriod());
            celldata6.setCellStyle(styleC24);
            
            HSSFCell celldata7 = row.createCell(7);
            celldata7.setCellValue("".equalsIgnoreCase(String.valueOf(data.getPaxadult())) ? 0 : new BigDecimal(data.getPaxadult()).doubleValue());
            celldata7.setCellStyle(styleC23);
            
            HSSFCell celldata8 = row.createCell(8);
            celldata8.setCellValue("".equalsIgnoreCase(String.valueOf(data.getPaxchild())) ? 0 : new BigDecimal(data.getPaxchild()).doubleValue());
            celldata8.setCellStyle(styleC23);
  
            //set data 
            HSSFCell celldata9 = row.createCell(9);
            celldata9.setCellValue("".equalsIgnoreCase(String.valueOf(data.getPaxinfant())) ? 0 : new BigDecimal(data.getPaxinfant()).doubleValue());
            celldata9.setCellStyle(styleC23);
            
            HSSFCell celldata10 = row.createCell(10);
            celldata10.setCellValue("".equalsIgnoreCase(String.valueOf(data.getNet())) ? 0 : new BigDecimal(data.getNet()).doubleValue());
            celldata10.setCellStyle(styleC25);
            
            HSSFCell celldata11 = row.createCell(11);
            celldata11.setCellValue("".equalsIgnoreCase(String.valueOf(data.getSell())) ? 0 : new BigDecimal(data.getSell()).doubleValue());
            celldata11.setCellStyle(styleC25);
            
            HSSFCell celldata12 = row.createCell(12);
            celldata12.setCellValue("".equalsIgnoreCase(String.valueOf(data.getProfit())) ? 0 : new BigDecimal(data.getProfit()).doubleValue());
            celldata12.setCellStyle(styleC25);
            
            HSSFCell celldata13 = row.createCell(13);
            celldata13.setCellValue(data.getBanktransfer());
            celldata13.setCellStyle(styleC23);
             
            HSSFCell celldata14 = row.createCell(14);
            celldata14.setCellValue(data.getTransferdate());
            celldata14.setCellStyle(styleC23);
            
            HSSFCell celldata15 = row.createCell(15);
            celldata15.setCellValue(data.getStatusname());
            celldata15.setCellStyle(styleC23);
            
            HSSFCell celldata16 = row.createCell(16);
            celldata16.setCellValue(data.getRemark());
            celldata16.setCellStyle(styleC24);
            
            HSSFCell celldata17 = row.createCell(17);
            celldata17.setCellValue(data.getSeller());
            celldata17.setCellStyle(styleC23);           
           
        }
        
        for(int j =0;j<21;j++){
            sheet.autoSizeColumn(j);
        }
        
        sheet.setColumnWidth(0, 256*15);
        sheet.setColumnWidth(1, 256*15);
        sheet.setColumnWidth(2, 256*15);
        sheet.setColumnWidth(3, 256*15);
//        sheet.setColumnWidth(4, 256*25);
//        sheet.setColumnWidth(5, 256*25);
        sheet.setColumnWidth(6, 256*15);
        sheet.setColumnWidth(10, 256*15);
        sheet.setColumnWidth(11, 256*15);
        sheet.setColumnWidth(12, 256*15);
        sheet.setColumnWidth(13, 256*15);
        sheet.setColumnWidth(14, 256*15);
        sheet.setColumnWidth(15, 256*15);
        sheet.setColumnWidth(16, 256*15);
        sheet.setColumnWidth(17, 256*15);
//        sheet.setColumnWidth(18, 256*15);
//        sheet.setColumnWidth(19, 256*15);
//        sheet.setColumnWidth(20, 256*15);
        
        
    }
}

