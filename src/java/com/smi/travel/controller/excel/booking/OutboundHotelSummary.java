/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.controller.excel.booking;

import com.smi.travel.controller.excel.master.UtilityExcelFunction;
import com.smi.travel.datalayer.view.entity.OutboundHotelSummaryView;
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
import org.apache.poi.ss.util.CellRangeAddress;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/**
 *
 * @author Surachai
 */
public class OutboundHotelSummary extends AbstractExcelView{
    private static final String OutboundHotelSummary = "OutboundHotelSummary";

    @Override
    protected void buildExcelDocument(Map model, HSSFWorkbook workbook,HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = (String) model.get("name");
        System.out.println("name : " + name);
        response.setHeader("Content-disposition", "attachment; filename=" + name + ".xls");
        
        if(name.equalsIgnoreCase(OutboundHotelSummary)){
            System.out.println("gen report OutboundHotelSummary");
            genOutboundHotelSummaryReport(workbook, (List) model.get(name));
        }
    }

    private void genOutboundHotelSummaryReport(HSSFWorkbook wb, List list) {
        UtilityExcelFunction excelFunction = new UtilityExcelFunction();
        String sheetName = "Sheet1";// name of sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        
        OutboundHotelSummaryView dataheader = new OutboundHotelSummaryView();
        
        HSSFDataFormat currency = wb.createDataFormat();
        // Set align Text
        HSSFCellStyle styleC21 = wb.createCellStyle();
        styleC21.setAlignment(styleC21.ALIGN_RIGHT);
        styleC21.setDataFormat(currency.getFormat("#,##0.00"));
        HSSFCellStyle styleC22 = wb.createCellStyle();
        styleC22.setAlignment(styleC22.ALIGN_LEFT);       
                     
        if(!list.isEmpty()){
            dataheader = (OutboundHotelSummaryView)list.get(0);
        
            // set Header Report (Row 1)
            HSSFCellStyle styleC1 = wb.createCellStyle();
            HSSFRow row1 = sheet.createRow(0);
            HSSFCell cell1 = row1.createCell(0);
            cell1.setCellValue("Hotel Summary");
            styleC1.setFont(excelFunction.getHeaderFont(wb.createFont()));
            cell1.setCellStyle(styleC1);
            sheet.addMergedRegion(CellRangeAddress.valueOf("A1:F1"));

            // Row 2
            HSSFRow row2 = sheet.createRow(1);
            HSSFCell cell21 = row2.createCell(0);
            cell21.setCellValue("Country : ");
            cell21.setCellStyle(styleC21);
            HSSFCell cell22 = row2.createCell(1);
            cell22.setCellValue(dataheader.getHeadcountry());
            cell22.setCellStyle(styleC22);
            sheet.addMergedRegion(CellRangeAddress.valueOf("B2:D2"));
            HSSFCell cell23 = row2.createCell(4);
            cell23.setCellValue("Pay By : ");
            cell23.setCellStyle(styleC21);
            HSSFCell cell24 = row2.createCell(5);
            cell24.setCellValue(dataheader.getHeadpayby());
            cell24.setCellStyle(styleC22);

            // Row 3
            HSSFRow row3 = sheet.createRow(2);
            HSSFCell cell31 = row3.createCell(0);
            cell31.setCellValue("City : ");
            cell31.setCellStyle(styleC21);
            HSSFCell cell32 = row3.createCell(1);
            cell32.setCellValue(dataheader.getHeadcity());
            cell32.setCellStyle(styleC22);
            sheet.addMergedRegion(CellRangeAddress.valueOf("B3:D3"));
            HSSFCell cell33 = row3.createCell(4);
            cell33.setCellValue("Bank Transfer : ");
            cell33.setCellStyle(styleC21);
            HSSFCell cell34 = row3.createCell(5);
            cell34.setCellValue(dataheader.getHeadbanktransfer());
            cell34.setCellStyle(styleC22);

            // Row 4
            HSSFRow row4 = sheet.createRow(3);
            HSSFCell cell41 = row4.createCell(0);
            cell41.setCellValue("Hotel : ");
            cell41.setCellStyle(styleC21);
            HSSFCell cell42 = row4.createCell(1);
            cell42.setCellValue(dataheader.getHeadhotel());
            cell42.setCellStyle(styleC22);
            sheet.addMergedRegion(CellRangeAddress.valueOf("B4:D4"));
            HSSFCell cell43 = row4.createCell(4);
            cell43.setCellValue("Status : ");
            cell43.setCellStyle(styleC21);
            HSSFCell cell44 = row4.createCell(5);
            cell44.setCellValue(dataheader.getHeadstatus());
            cell44.setCellStyle(styleC22);

            // Row 5
            HSSFRow row5 = sheet.createRow(4);
            HSSFCell cell51 = row5.createCell(0);
            cell51.setCellValue("Date : ");
            cell51.setCellStyle(styleC21);
            HSSFCell cell52 = row5.createCell(1);
            cell52.setCellValue(dataheader.getHeaddate());
            cell52.setCellStyle(styleC22);
            sheet.addMergedRegion(CellRangeAddress.valueOf("B5:D5"));
        
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
        
        HSSFRow row6 = sheet.createRow(6);
        HSSFCell cell61 = row6.createCell(0);
        cell61.setCellValue("SALE DATE");
        cell61.setCellStyle(styletop);
        HSSFCell cell62 = row6.createCell(1);
        cell62.setCellValue("RECORD NO");
        cell62.setCellStyle(styletop);
        HSSFCell cell63 = row6.createCell(2);
        cell63.setCellValue("TRAVOX NO");
        cell63.setCellStyle(styletop);
        HSSFCell cell64 = row6.createCell(3);
        cell64.setCellValue("HOTEL NAME");
        cell64.setCellStyle(styletop);
        HSSFCell cell65 = row6.createCell(4);
        cell65.setCellValue("CUSTOMER NAME");
        cell65.setCellStyle(styletop);
        HSSFCell cell66 = row6.createCell(5);
        cell66.setCellValue("PERIOD");
        cell66.setCellStyle(styletop);
        sheet.autoSizeColumn(5);
        HSSFCell cell67 = row6.createCell(6);
        cell67.setCellValue("NUMBER");
        cell67.setCellStyle(styletop);
        HSSFCell cell68 = row6.createCell(7);
        cell68.setCellValue("NETT");
        cell68.setCellStyle(styletop);
        HSSFCell cell69 = row6.createCell(8);
        cell69.setCellValue("SELLING");
        cell69.setCellStyle(styletop);
        HSSFCell cell610 = row6.createCell(9);
        cell610.setCellValue("TOTAL");
        cell610.setCellStyle(styletop);
        HSSFCell cell611 = row6.createCell(10);
        cell611.setCellValue("SALE");
        cell611.setCellStyle(styletop);
        HSSFCell cell612 = row6.createCell(11);
        cell612.setCellValue("PROFIT");
        cell612.setCellStyle(styletop);
        HSSFCell cell613 = row6.createCell(12);
        cell613.setCellValue("BANK");
        cell613.setCellStyle(styletop);
        HSSFCell cell614 = row6.createCell(13);
        cell614.setCellValue("DATE");
        cell614.setCellStyle(styletop);
        HSSFCell cell615 = row6.createCell(14);
        cell615.setCellValue("SUPPLIER");
        cell615.setCellStyle(styletop);
        HSSFCell cell616 = row6.createCell(15);
        cell616.setCellValue("STATUS");
        cell616.setCellStyle(styletop);
        HSSFCell cell617 = row6.createCell(16);
        cell617.setCellValue("REMARK");
        cell617.setCellStyle(styletop);
        HSSFCell cell618 = row6.createCell(17);
        cell618.setCellValue("SALLER");
        cell618.setCellStyle(styletop);
        
        sheet.addMergedRegion(CellRangeAddress.valueOf("A7:A8"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("B7:B8"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("C7:C8"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("D7:D8"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("E7:E8"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("F7:F8"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("O7:O8"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("P7:P8"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("Q7:Q8"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("R7:R8"));
        
        HSSFRow row7 = sheet.createRow(7);
        HSSFCell cell71 = row7.createCell(0);
        cell71.setCellStyle(stylebottom);
        HSSFCell cell72 = row7.createCell(1);
        cell72.setCellStyle(stylebottom);
        HSSFCell cell73 = row7.createCell(2);
        cell73.setCellStyle(stylebottom);
        HSSFCell cell74 = row7.createCell(3);
        cell74.setCellStyle(stylebottom);
        HSSFCell cell75 = row7.createCell(4);
        cell75.setCellStyle(stylebottom);
        HSSFCell cell76 = row7.createCell(5);
        cell76.setCellStyle(stylebottom);
        HSSFCell cell77 = row7.createCell(6);
        cell77.setCellValue("OF PAX");
        cell77.setCellStyle(stylebottom);
        HSSFCell cell78 = row7.createCell(7);
        cell78.setCellValue("ROOM/NIGHT");
        cell78.setCellStyle(stylebottom);
        HSSFCell cell79 = row7.createCell(8);
        cell79.setCellValue("ROOM/NIGHT");
        cell79.setCellStyle(stylebottom);
        HSSFCell cell710 = row7.createCell(9);
        cell710.setCellValue("NETT");
        cell710.setCellStyle(stylebottom);
        HSSFCell cell711 = row7.createCell(10);
        cell711.setCellValue("TOTAL");
        cell711.setCellStyle(stylebottom);
        HSSFCell cell712 = row7.createCell(11);
        cell712.setCellValue("TOTAL");
        cell712.setCellStyle(stylebottom);
        HSSFCell cell713 = row7.createCell(12);
        cell713.setCellValue("TRFS");
        cell713.setCellStyle(stylebottom);
        HSSFCell cell714 = row7.createCell(13);
        cell714.setCellValue("TRFS");
        cell714.setCellStyle(stylebottom);
        HSSFCell cell715 = row7.createCell(14);
        cell715.setCellStyle(stylebottom);
        HSSFCell cell716 = row7.createCell(15);
        cell716.setCellStyle(stylebottom);
        HSSFCell cell717 = row7.createCell(16);
        cell717.setCellStyle(stylebottom);
        HSSFCell cell718 = row7.createCell(17);
        cell718.setCellStyle(stylebottom);
        
        //Detail of Table
        int count = 8 ;
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

        for(int i=0;i<list.size();i++){
            OutboundHotelSummaryView data = (OutboundHotelSummaryView)list.get(i);
            HSSFRow row = sheet.createRow(count + i);
            
            HSSFCell celldata0 = row.createCell(0);
            celldata0.setCellValue(data.getHoteldate());
            celldata0.setCellStyle(styleC23);
            
            HSSFCell celldata1 = row.createCell(1);
            celldata1.setCellValue(data.getRecordno());
            celldata1.setCellStyle(styleC24);
            
            HSSFCell celldata2 = row.createCell(2);
            celldata2.setCellValue(data.getRefno());
            celldata2.setCellStyle(styleC24);
            
            HSSFCell celldata3 = row.createCell(3);
            celldata3.setCellValue(data.getHotel());
            celldata3.setCellStyle(styleC24);
            
            HSSFCell celldata4 = row.createCell(4);
            celldata4.setCellValue(data.getLeader());
            celldata4.setCellStyle(styleC24);
            
            HSSFCell celldata5 = row.createCell(5);
            celldata5.setCellValue(data.getPeriod());
            celldata5.setCellStyle(styleC24);
            
            HSSFCell celldata6 = row.createCell(6);
            celldata6.setCellValue("".equalsIgnoreCase(String.valueOf(data.getPax())) ? 0 : new BigDecimal(data.getPax()).doubleValue());
            celldata6.setCellStyle(styleC23);
            
            HSSFCell celldata7 = row.createCell(7);
            celldata7.setCellValue("".equalsIgnoreCase(String.valueOf(data.getNet())) ? 0 : new BigDecimal(data.getNet()).doubleValue());
            celldata7.setCellStyle(styleC25);
            
            HSSFCell celldata14 = row.createCell(8);
            celldata14.setCellValue("".equalsIgnoreCase(String.valueOf(data.getSale())) ? 0 : new BigDecimal(data.getSale()).doubleValue());
            celldata14.setCellStyle(styleC25);
  
            //set data 
            HSSFCell celldata8 = row.createCell(9);
            celldata8.setCellValue("".equalsIgnoreCase(String.valueOf(data.getTotelnet())) ? 0 : new BigDecimal(data.getTotelnet()).doubleValue());
            celldata8.setCellStyle(styleC25);
            
            HSSFCell celldata9 = row.createCell(10);
            celldata9.setCellValue("".equalsIgnoreCase(String.valueOf(data.getTotalsell())) ? 0 : new BigDecimal(data.getTotalsell()).doubleValue());
            celldata9.setCellStyle(styleC25);
            
            HSSFCell celldata10 = row.createCell(11);
            celldata10.setCellValue("".equalsIgnoreCase(String.valueOf(data.getTotalprofit())) ? 0 : new BigDecimal(data.getTotalprofit()).doubleValue());
            celldata10.setCellStyle(styleC25);
            
            HSSFCell celldata11 = row.createCell(12);
            celldata11.setCellValue(data.getBank());
            celldata11.setCellStyle(styleC24);
            
            HSSFCell celldata12 = row.createCell(13);
            celldata12.setCellValue(data.getTransferdate());
            celldata12.setCellStyle(styleC24);
            
            HSSFCell celldata15 = row.createCell(14);
            celldata15.setCellValue(data.getReference());
            celldata15.setCellStyle(styleC24);
            
            HSSFCell celldata13 = row.createCell(15);
            celldata13.setCellValue(data.getStatus());
            celldata13.setCellStyle(styleC23);
            
            HSSFCell celldata16 = row.createCell(16);
            celldata16.setCellValue(data.getRemark());
            celldata16.setCellStyle(styleC24);
             
            HSSFCell celldata17 = row.createCell(17);
            celldata17.setCellValue(data.getSeller());
            celldata17.setCellStyle(styleC24);
                      
        }
        
        for(int j =0;j<21;j++){
            sheet.autoSizeColumn(j);
        }
        
        sheet.setColumnWidth(0, 256*15);
        sheet.setColumnWidth(1, 256*15);
        sheet.setColumnWidth(2, 256*15);
        sheet.setColumnWidth(3, 256*15);
        sheet.setColumnWidth(4, 256*25);
        sheet.setColumnWidth(5, 256*25);
        sheet.setColumnWidth(6, 256*15);
        sheet.setColumnWidth(10, 256*15);
        sheet.setColumnWidth(11, 256*15);
        sheet.setColumnWidth(12, 256*15);
        sheet.setColumnWidth(13, 256*15);
        sheet.setColumnWidth(14, 256*15);
        sheet.setColumnWidth(15, 256*15);
        sheet.setColumnWidth(16, 256*15);
        sheet.setColumnWidth(17, 256*15);
        sheet.setColumnWidth(18, 256*15);
        sheet.setColumnWidth(19, 256*15);
        sheet.setColumnWidth(20, 256*15);
        
        
    }
    
}
