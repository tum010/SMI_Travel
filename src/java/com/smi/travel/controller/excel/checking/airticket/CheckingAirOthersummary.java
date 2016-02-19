/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller.excel.checking.airticket;

import com.smi.travel.controller.excel.master.UtilityExcelFunction;
import com.smi.travel.datalayer.view.entity.ListTicketSummaryCommission;
import com.smi.travel.datalayer.view.entity.TicketProfitLoss;
import com.smi.travel.datalayer.view.entity.TicketSummaryCommissionView;
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
 * @author Jittima
 */
public class CheckingAirOthersummary extends AbstractExcelView {
    private static final String TicketProfitLoss = "TicketProfitLoss"; 
    private static final String TicketSummaryCommission = "TicketSummaryCommission";
    
    @Override
    protected void buildExcelDocument(Map model, HSSFWorkbook workbook,HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = (String) model.get("name");
        System.out.println("name : " + name);
        response.setHeader("Content-disposition", "attachment; filename=" + name + ".xls");
        
        if(name.equalsIgnoreCase(TicketProfitLoss)){
            System.out.println("gen report TicketProfitLoss");
            genTicketProfitLossReport(workbook, (List) model.get(name));
        }else if(name.equalsIgnoreCase(TicketSummaryCommission)){
            System.out.println("gen report TicketSummaryCommission");
            getTicketSummaryCommission(workbook, (List) model.get(name));
        }
       
    }
    
    private void genTicketProfitLossReport(HSSFWorkbook wb, List ticketProfitLoss) {
        String sheetName = "Sheet1";// name of sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        
        TicketProfitLoss dataheader = new TicketProfitLoss();
        UtilityExcelFunction excelFunction = new UtilityExcelFunction();
        HSSFDataFormat currency = wb.createDataFormat();
        // Set align Text
        HSSFCellStyle styleC21 = wb.createCellStyle();
        styleC21.setAlignment(styleC21.ALIGN_RIGHT);
        HSSFCellStyle styleC22 = wb.createCellStyle();
        styleC22.setAlignment(styleC22.ALIGN_LEFT); 
        
        System.out.println(" ticketProfitLoss.size() " + ticketProfitLoss.size());
        if(ticketProfitLoss != null && ticketProfitLoss.size() != 0){
            dataheader = (TicketProfitLoss) ticketProfitLoss.get(0);
        
        
        // set Header Report (Row 1)
        HSSFCellStyle styleC1 = wb.createCellStyle();
        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cellStart = row1.createCell(0);
        cellStart.setCellValue("Ticket Profit Loss");
        styleC1.setFont(excelFunction.getHeaderFont(wb.createFont()));
        cellStart.setCellStyle(styleC1);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:F1"));
        
        // Row 2
        HSSFRow row2 = sheet.createRow(1);
        HSSFCell cell21 = row2.createCell(0);
        cell21.setCellValue("Invoice Date From : "+dataheader.getInvoicedatefrom()+" To : "+dataheader.getInvoicedateto());
        cell21.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A2:F2"));
        
        // Row 3
        HSSFRow row3 = sheet.createRow(2);
        HSSFCell cell31 = row3.createCell(0);
        cell31.setCellValue("Print by : "+dataheader.getPrintby());
        cell31.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A3:F3"));
//        HSSFCell cell32 = row3.createCell(4);
//        cell32.setCellValue("Print on : "+dataheader.getPrinton());
//        cell32.setCellStyle(styleC22);
//        sheet.addMergedRegion(CellRangeAddress.valueOf("C3:D3"));
//        HSSFCell cell33 = row3.createCell(4);
//        cell33.setCellValue("Print on : ");
//        cell33.setCellStyle(styleC21);
//        HSSFCell cell34 = row3.createCell(5);
//        cell34.setCellValue(dataheader.getPrinton());
//        cell34.setCellStyle(styleC22);
//        sheet.addMergedRegion(CellRangeAddress.valueOf("E3:F3"));
        
        //Row 4
        HSSFRow row4 = sheet.createRow(3);
        HSSFCell cell41 = row4.createCell(0);
        cell41.setCellValue("Print on : "+dataheader.getPrinton());
        cell41.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A4:F4"));
        
         // Header Table
        HSSFCellStyle styleC3Center = wb.createCellStyle();
        styleC3Center.setFont(excelFunction.getHeaderTable(wb.createFont()));
        styleC3Center.setAlignment(styleC3Center.ALIGN_CENTER);
        styleC3Center.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC3Center.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC3Center.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC3Center.setBorderTop(HSSFCellStyle.BORDER_THIN);
        HSSFRow row6 = sheet.createRow(5);
        HSSFCell cell61 = row6.createCell(0);
        cell61.setCellValue("");
        cell61.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(0);
        HSSFCell cell62 = row6.createCell(1);
        cell62.setCellValue("Inv No.");
        cell62.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(1);        
        HSSFCell cell63 = row6.createCell(2);
        cell63.setCellValue("Date");
        cell63.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(2);        
        HSSFCell cell64 = row6.createCell(3);
        cell64.setCellValue("Dep");
        cell64.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(3);       
        HSSFCell cell651 = row6.createCell(4);
        cell651.setCellValue("Agent Name");
        cell651.setCellStyle(styleC3Center);
        sheet.addMergedRegion(CellRangeAddress.valueOf("E6:H6"));
        HSSFCell cell652 = row6.createCell(5);
        cell652.setCellStyle(styleC3Center);
        HSSFCell cell653 = row6.createCell(6);
        cell653.setCellStyle(styleC3Center);
        HSSFCell cell654 = row6.createCell(7);
        cell654.setCellStyle(styleC3Center);
        HSSFCell cell66 = row6.createCell(8);
        cell66.setCellValue("Type");
        cell66.setCellStyle(styleC3Center);      
        sheet.autoSizeColumn(8);
        HSSFCell cell67 = row6.createCell(9);
        cell67.setCellValue("Rou");
        cell67.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(9);
        HSSFCell cell68 = row6.createCell(10);
        cell68.setCellValue("Pax");
        cell68.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(10);
        HSSFCell cell69 = row6.createCell(11);
        cell69.setCellValue("Air");
        cell69.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(11);
        HSSFCell cell610 = row6.createCell(12);
        cell610.setCellValue("Doc No.");
        cell610.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(12);
        HSSFCell cell611 = row6.createCell(13);
        cell611.setCellValue("Issue Date");
        cell611.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(13);
        HSSFCell cell612 = row6.createCell(14);
        cell612.setCellValue("Comm");
        cell612.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(14);
        HSSFCell cell613 = row6.createCell(15);
        cell613.setCellValue("Vat");
        cell613.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(15);
        HSSFCell cell614 = row6.createCell(16);
        cell614.setCellValue("Total");
        cell614.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(16);
        }
        //Detail of Table
        int count = 6 ;
        HSSFCellStyle styleC23 = wb.createCellStyle();
        styleC23.setAlignment(styleC22.ALIGN_CENTER);
        styleC23.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC23.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC23.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC23.setWrapText(true);
        styleC23.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFCellStyle styleC24 = wb.createCellStyle();
        styleC24.setAlignment(styleC24.ALIGN_LEFT);
        styleC24.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC24.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC24.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC24.setWrapText(true);
        styleC24.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFCellStyle styleC25 = wb.createCellStyle();
        styleC25.setAlignment(styleC25.ALIGN_RIGHT);
        styleC25.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC25.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC25.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC25.setDataFormat(currency.getFormat("#,##0.00"));
        styleC25.setWrapText(true);
        styleC25.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        for(int i=0;i<ticketProfitLoss.size();i++){
             TicketProfitLoss data = (TicketProfitLoss)ticketProfitLoss.get(i);
             HSSFRow row = sheet.createRow(count + i);            
             HSSFCell cell0 = row.createCell(0);
                cell0.setCellValue(data.getNo());
                cell0.setCellStyle(styleC23);
                sheet.autoSizeColumn(0); 
             HSSFCell cell1 = row.createCell(1);
                cell1.setCellValue(data.getInvno());
                cell1.setCellStyle(styleC23);
                sheet.autoSizeColumn(1); 
             HSSFCell cell2 = row.createCell(2);
                cell2.setCellValue(data.getDate());
                cell2.setCellStyle(styleC23);
                sheet.autoSizeColumn(2); 
             HSSFCell cell3 = row.createCell(3);
                cell3.setCellValue(data.getDepartment());
                cell3.setCellStyle(styleC23);
                sheet.autoSizeColumn(3); 
//             String air = data.getDocno().substring(0, 4);
//             String doc = data.getDocno().substring(4);
//             System.out.println("Air : " + air + "  Doc : " + doc);
             HSSFCell cell4 = row.createCell(4);
                cell4.setCellValue(data.getAgentname());
                cell4.setCellStyle(styleC24);
                sheet.addMergedRegion(CellRangeAddress.valueOf("E"+(count + i + 1)+":H"+(count + i + 1))); 
             HSSFCell cell5 = row.createCell(5);
                cell5.setCellStyle(styleC24);
             HSSFCell cell6 = row.createCell(6);
                cell6.setCellStyle(styleC24);
             HSSFCell cell7 = row.createCell(7);
                cell7.setCellStyle(styleC24);     
             HSSFCell cell18 = row.createCell(8);
                cell18.setCellValue(data.getType());
                cell18.setCellStyle(styleC23);                
                sheet.autoSizeColumn(8);
             HSSFCell cell9 = row.createCell(9);
                cell9.setCellValue(data.getRount());
                cell9.setCellStyle(styleC23);
                sheet.autoSizeColumn(9);
             HSSFCell cell10 = row.createCell(10);
                cell10.setCellValue(data.getPax());
                cell10.setCellStyle(styleC25);
                sheet.autoSizeColumn(10);
             HSSFCell cell11 = row.createCell(11);
                cell11.setCellValue(data.getAir());
                cell11.setCellStyle(styleC25);
                sheet.autoSizeColumn(11);
             HSSFCell cell12 = row.createCell(12);
                cell12.setCellValue((data.getDocno()).replaceAll(",", "\n"));
                cell12.setCellStyle(styleC23);
                sheet.autoSizeColumn(12);                
                row.setHeightInPoints((((((data.getDocno()).split(",")).length)+1)*sheet.getDefaultRowHeightInPoints()));
             HSSFCell cell13 = row.createCell(13);
                cell13.setCellValue(data.getIssuedate());
                cell13.setCellStyle(styleC23);  
                sheet.autoSizeColumn(13);
             HSSFCell cell14 = row.createCell(14);
                cell14.setCellValue(!"".equalsIgnoreCase(data.getLittlecomm()) ? new BigDecimal(data.getLittlecomm()).doubleValue() : 0);
                cell14.setCellStyle(styleC25);
                sheet.autoSizeColumn(14);
             HSSFCell cell15 = row.createCell(15);
                cell15.setCellValue(!"".equalsIgnoreCase(data.getVat()) ? new BigDecimal(data.getVat()).doubleValue() : 0);
                cell15.setCellStyle(styleC25);
                sheet.autoSizeColumn(15);
             HSSFCell cell16 = row.createCell(16);
                cell16.setCellValue(!"".equalsIgnoreCase(data.getTotal()) ? new BigDecimal(data.getTotal()).doubleValue() : 0);
                cell16.setCellStyle(styleC25);
                sheet.autoSizeColumn(16);
                
//            if(i == (TicketProfitLoss.size()-1)){
//                row = sheet.createRow(count + i + 1);
//                for(int k=0;k<16;k++){
//                    HSSFCellStyle styleSum = wb.createCellStyle();
//                    styleSum.setBorderTop(HSSFCellStyle.BORDER_THIN);
//                    HSSFCell cellSum = row.createCell(k);                   
//                    cellSum.setCellStyle(styleSum);
//                }
//            }    
               
//            for(int j =0;j<15;j++){
//                if(j==4){
//                    sheet.addMergedRegion(CellRangeAddress.valueOf("E"+(count + i)+":G"+(count + i))); 
//                }else{
//                    sheet.autoSizeColumn(j); 
//                }
//                 
//            }
        }     
        
    }
    
    private void getTicketSummaryCommission(HSSFWorkbook wb, List listTicketummaryCommission) {
        String sheetName = "Ticket_commission_detail_summary";// name of sheet
        String sheetName1 = "Ticket_commission_air_summary";
        String sheetName2 = "Ticket_commission_agent_summary";
        HSSFSheet sheet = wb.createSheet(sheetName);
        HSSFSheet sheet1 = wb.createSheet(sheetName1);
        HSSFSheet sheet2 = wb.createSheet(sheetName2);

        UtilityExcelFunction excelFunction = new UtilityExcelFunction();
        // Set align Text
        HSSFCellStyle styleAlignRight = wb.createCellStyle();
        styleAlignRight.setAlignment(styleAlignRight.ALIGN_RIGHT);
        HSSFCellStyle styleAlignLeft = wb.createCellStyle();
        styleAlignLeft.setAlignment(styleAlignLeft.ALIGN_LEFT);

        HSSFDataFormat currency = wb.createDataFormat();
        HSSFCellStyle styleNumber = wb.createCellStyle();
        styleNumber.setAlignment(styleNumber.ALIGN_RIGHT);
        styleNumber.setDataFormat(currency.getFormat("#,##0.00"));

        HSSFCellStyle styleNumberBorderRight = wb.createCellStyle();
        styleNumberBorderRight.setAlignment(styleNumberBorderRight.ALIGN_RIGHT);
        styleNumberBorderRight.setDataFormat(currency.getFormat("#,##0.00"));
        styleNumberBorderRight.setBorderRight(styleNumberBorderRight.BORDER_THIN);

        HSSFCellStyle styleBorderTop = wb.createCellStyle();
        styleBorderTop.setBorderTop(styleBorderTop.BORDER_THIN);

        HSSFCellStyle styleAlignRightBorderAllHeaderTable = wb .createCellStyle();
        styleAlignRightBorderAllHeaderTable.setFont(excelFunction.getHeaderTable(wb .createFont()));
        styleAlignRightBorderAllHeaderTable.setAlignment(styleAlignRightBorderAllHeaderTable.ALIGN_CENTER);
        styleAlignRightBorderAllHeaderTable.setBorderTop(styleAlignRightBorderAllHeaderTable.BORDER_THIN);
        styleAlignRightBorderAllHeaderTable.setBorderBottom(styleAlignRightBorderAllHeaderTable.BORDER_THIN);
        styleAlignRightBorderAllHeaderTable.setBorderRight(styleAlignRightBorderAllHeaderTable.BORDER_THIN);
        styleAlignRightBorderAllHeaderTable.setBorderLeft(styleAlignRightBorderAllHeaderTable.BORDER_THIN);
        // Header Table
        HSSFCellStyle styleDetailTable = wb.createCellStyle();
            styleDetailTable.setAlignment(styleDetailTable.ALIGN_LEFT);
            styleDetailTable.setBorderLeft(styleDetailTable.BORDER_THIN);
            styleDetailTable.setBorderRight(styleDetailTable.BORDER_THIN);

        HSSFCellStyle styleDetailTableNumber = wb.createCellStyle();
            styleDetailTableNumber.setDataFormat(currency.getFormat("#,##0.00"));
            styleDetailTableNumber.setAlignment(styleDetailTableNumber.ALIGN_RIGHT);
            styleDetailTableNumber.setBorderLeft(styleDetailTableNumber.BORDER_THIN);
            styleDetailTableNumber.setBorderRight(styleDetailTableNumber.BORDER_THIN);
            
        HSSFCellStyle styleTotalTableNumber = wb.createCellStyle();
            styleTotalTableNumber.setDataFormat(currency.getFormat("#,##0.00"));
            styleTotalTableNumber.setAlignment(styleTotalTableNumber.ALIGN_RIGHT);
            styleTotalTableNumber.setBorderTop(styleTotalTableNumber.BORDER_THIN);
            styleTotalTableNumber.setBorderBottom(styleTotalTableNumber.BORDER_THIN);
            styleTotalTableNumber.setBorderRight(styleTotalTableNumber.BORDER_THIN);
            styleTotalTableNumber.setBorderLeft(styleTotalTableNumber.BORDER_THIN);
            
        HSSFCellStyle stylePaxNumber = wb.createCellStyle();
            stylePaxNumber.setDataFormat(currency.getFormat("#,##0"));
            stylePaxNumber.setAlignment(stylePaxNumber.ALIGN_CENTER);
            stylePaxNumber.setBorderRight(stylePaxNumber.BORDER_THIN);
            stylePaxNumber.setBorderLeft(stylePaxNumber.BORDER_THIN);
            
                        
        HSSFCellStyle stylePaxNumberTotal = wb.createCellStyle();
            stylePaxNumberTotal.setDataFormat(currency.getFormat("#,##0"));
            stylePaxNumberTotal.setAlignment(stylePaxNumberTotal.ALIGN_CENTER);
            stylePaxNumberTotal.setBorderTop(stylePaxNumberTotal.BORDER_THIN);
            stylePaxNumberTotal.setBorderBottom(stylePaxNumberTotal.BORDER_THIN);
            stylePaxNumberTotal.setBorderRight(stylePaxNumberTotal.BORDER_THIN);
            stylePaxNumberTotal.setBorderLeft(stylePaxNumberTotal.BORDER_THIN);
            
        // set Header Report (Row 1)
        HSSFCellStyle styleHeader01 = wb.createCellStyle();
        HSSFRow row01 = sheet.createRow(0);
        HSSFCell cell01 = row01.createCell(0);
        cell01.setCellValue("List summary commission");
        styleHeader01.setFont(excelFunction.getHeaderFont(wb.createFont()));
        cell01.setCellStyle(styleHeader01);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:M1"));
        
        List<ListTicketSummaryCommission> listSummaryCommission = listTicketummaryCommission;
        
        List<TicketSummaryCommissionView> listDetail = listSummaryCommission.get(0).getTicketCommissionDetailSummary();
        List<TicketSummaryCommissionView> listAir = listSummaryCommission.get(0).getTicketCommissionAirSummary();
        List<TicketSummaryCommissionView> listAgent = listSummaryCommission.get(0).getTicketCommissionAgentSummary();
        TicketSummaryCommissionView ticketDetail = (listDetail.size() > 0 ? listDetail.get(0) : new TicketSummaryCommissionView());
        TicketSummaryCommissionView ticketAir = (listAir.size() > 0 ? listAir.get(0) : new TicketSummaryCommissionView());
        TicketSummaryCommissionView ticketAgent = (listAgent.size() > 0 ? listAgent.get(0) : new TicketSummaryCommissionView());
        // Row 2
        HSSFRow row02 = sheet.createRow(1);
        HSSFCell cell021 = row02.createCell(0);
                cell021.setCellValue("Agent Name : ");
                cell021.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(0);
        HSSFCell cell022 = row02.createCell(1);
                cell022.setCellValue(ticketDetail.getAgentNamePage() != null && !"".equals(ticketDetail.getAgentNamePage())? ticketDetail.getAgentNamePage() :"ALL");
                cell022.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(1);
        HSSFCell cell023 = row02.createCell(2);
                cell023.setCellValue("Issue date : ");
                cell023.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(2);
        HSSFCell cell024 = row02.createCell(3);
                cell024.setCellValue(ticketDetail.getIssuefromdatePage() != null && !"".equals(ticketDetail.getIssuefromdatePage())? ticketDetail.getIssuefromdatePage() :"ALL");
                cell024.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(3);
        HSSFCell cell025 = row02.createCell(4);
                cell025.setCellValue("Print By : ");
                cell025.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(4);
        HSSFCell cell026 = row02.createCell(5);
                cell026.setCellValue(ticketDetail.getPrintbyPage() != null && !"".equals(ticketDetail.getPrintbyPage())? ticketDetail.getPrintbyPage() :"ALL");
                cell026.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(5);

        // Row 3
        HSSFRow row03 = sheet.createRow(2);
        HSSFCell cell031 = row03.createCell(0);
                cell031.setCellValue("Type Routing : ");
                cell031.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(0);
        HSSFCell cell032 = row03.createCell(1);
                cell032.setCellValue(ticketDetail.getTypeRoutingPage() != null && !"".equals(ticketDetail.getTypeRoutingPage())? ticketDetail.getTypeRoutingPage() :"ALL");
                cell032.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(1);
        HSSFCell cell033 = row03.createCell(2);
                cell033.setCellValue("Over Comm Date : ");
                cell033.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(2);
        HSSFCell cell034 = row03.createCell(3);
                cell034.setCellValue(ticketDetail.getOverfromdatePage() != null && !"".equals(ticketDetail.getOverfromdatePage())? ticketDetail.getOverfromdatePage() :"ALL");
                cell034.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(3);
        HSSFCell cell035 = row03.createCell(4);
                cell035.setCellValue("Add Pay Date : ");
                cell035.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(4);
        HSSFCell cell036 = row03.createCell(5);
                cell036.setCellValue(ticketDetail.getAddpayfromdatePage() != null && !"".equals(ticketDetail.getAddpayfromdatePage())? ticketDetail.getAddpayfromdatePage() :"ALL");
                cell036.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(5);

        // Row 4
        HSSFRow row04 = sheet.createRow(3);
        HSSFCell cell041 = row04.createCell(0);
                cell041.setCellValue("Air : ");
                cell041.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(0);
        HSSFCell cell042 = row04.createCell(1);
                cell042.setCellValue(ticketDetail.getAirlineCodePage() != null && !"".equals(ticketDetail.getAirlineCodePage())? ticketDetail.getAirlineCodePage() :"ALL");
                cell042.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(1);
        HSSFCell cell043 = row04.createCell(2);
                cell043.setCellValue("Little Comm date : ");
                cell043.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(2);
        HSSFCell cell044 = row04.createCell(3);
                cell044.setCellValue(ticketDetail.getLittlefromdatePage() != null && !"".equals(ticketDetail.getLittlefromdatePage())? ticketDetail.getLittlefromdatePage() :"ALL");
                cell044.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(3);
        HSSFCell cell045 = row04.createCell(4);
                cell045.setCellValue("Decrease Pay Date : ");
                cell045.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(4);
        HSSFCell cell046 = row04.createCell(5);
                cell046.setCellValue(ticketDetail.getDecreasepayfromdatePage() != null && !"".equals(ticketDetail.getDecreasepayfromdatePage())? ticketDetail.getDecreasepayfromdatePage() :"ALL");
                cell046.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(5);

        // Row 5
        HSSFRow row05 = sheet.createRow(4);
        HSSFCell cell051 = row05.createCell(0);
                cell051.setCellValue("Routing Detail : ");
                cell051.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(0);
        HSSFCell cell052 = row05.createCell(1);
                cell052.setCellValue(ticketDetail.getRoutingDetailPage() != null && !"".equals(ticketDetail.getRoutingDetailPage())? ticketDetail.getRoutingDetailPage() :"ALL");
                cell052.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(1);
        HSSFCell cell053 = row05.createCell(2);
                cell053.setCellValue("Agent Comm Rev Date : ");
                cell053.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(2);
        HSSFCell cell054 = row05.createCell(3);
                cell054.setCellValue(ticketDetail.getAgemtcomreceivefromdatePage() != null && !"".equals(ticketDetail.getAgemtcomreceivefromdatePage())? ticketDetail.getAgemtcomreceivefromdatePage() :"ALL");
                cell054.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(3);
        HSSFCell cell055 = row05.createCell(4);
                cell055.setCellValue("Ticket No : ");
                cell055.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(4);
        HSSFCell cell056 = row05.createCell(5);
                cell056.setCellValue(ticketDetail.getTicketnoPagePage() != null && !"".equals(ticketDetail.getTicketnoPagePage())? ticketDetail.getTicketnoPagePage() :"ALL");
                cell056.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(5);

        // Row 6
        HSSFRow row06 = sheet.createRow(5);
        HSSFCell cell061 = row06.createCell(0);
                cell061.setCellValue("Sale Staff : ");
                cell061.setCellStyle(styleAlignRight);
        HSSFCell cell062 = row06.createCell(1);
                cell062.setCellValue(ticketDetail.getSalebyNamePage() != null && !"".equals(ticketDetail.getSalebyNamePage())? ticketDetail.getSalebyNamePage() :"ALL");
                cell062.setCellStyle(styleAlignLeft);
        HSSFCell cell063 = row06.createCell(2);
                cell063.setCellValue("Refund Comm Date : ");
                cell063.setCellStyle(styleAlignRight);
        HSSFCell cell064 = row06.createCell(3);
                cell064.setCellValue(ticketDetail.getComrefundfromdatePage() != null && !"".equals(ticketDetail.getComrefundfromdatePage())? ticketDetail.getComrefundfromdatePage() :"ALL");
                cell064.setCellStyle(styleAlignLeft);
        HSSFCell cell065 = row06.createCell(4);
                cell065.setCellValue("Ticket Comm Date : ");
                cell065.setCellStyle(styleAlignRight);
        HSSFCell cell066 = row06.createCell(5);
                cell066.setCellValue(ticketDetail.getTicketcomfromdatePage()!= null && !"".equals(ticketDetail.getTicketcomfromdatePage())? ticketDetail.getTicketcomfromdatePage() :"ALL");
                cell066.setCellStyle(styleAlignLeft);

        // Row 7
        HSSFRow row07 = sheet.createRow(6);
        HSSFCell cell071 = row07.createCell(0);
                cell071.setCellValue("Department : ");
                cell071.setCellStyle(styleAlignRight);
        HSSFCell cell072 = row07.createCell(1);
                cell072.setCellValue(ticketDetail.getDepartmentPage() != null && !"".equals(ticketDetail.getDepartmentPage())? ticketDetail.getDepartmentPage() :"ALL");
                cell072.setCellStyle(styleAlignLeft);
        HSSFCell cell073 = row07.createCell(2);
                cell073.setCellValue("Invoice Date : ");
                cell073.setCellStyle(styleAlignRight);
        HSSFCell cell074 = row07.createCell(3);
                cell074.setCellValue(ticketDetail.getInvoicefromdatePage() != null && !"".equals(ticketDetail.getInvoicefromdatePage())? ticketDetail.getInvoicefromdatePage() :"ALL");
                cell074.setCellStyle(styleAlignLeft);
        HSSFCell cell075 = row07.createCell(4);
                cell075.setCellValue("Print on : ");
                cell075.setCellStyle(styleAlignRight);
        HSSFCell cell076 = row07.createCell(5);
                cell076.setCellValue(ticketDetail.getPrintonPage() != null && !"".equals(ticketDetail.getPrintonPage())? ticketDetail.getPrintonPage() :"ALL");
                cell076.setCellStyle(styleAlignLeft);

        // Row 8
        HSSFRow row08 = sheet.createRow(7);
        HSSFCell cell081 = row08.createCell(0);
                cell081.setCellValue("Term Pay : ");
                cell081.setCellStyle(styleAlignRight);
        HSSFCell cell082 = row08.createCell(1);
                cell082.setCellValue(ticketDetail.getTermPayPage() != null && !"".equals(ticketDetail.getTermPayPage())? ticketDetail.getTermPayPage() :"ALL");
                cell082.setCellStyle(styleAlignLeft);
        HSSFCell cell083 = row08.createCell(2);
                cell083.setCellValue("Page : ");
                cell083.setCellStyle(styleAlignRight);
        HSSFCell cell084 = row08.createCell(3);
                cell084.setCellValue("1 ");
                cell084.setCellStyle(styleAlignLeft);


        // Header Table
        HSSFRow row09 = sheet.createRow(9);
        HSSFCell cell091 = row09.createCell(0);
        cell091.setCellValue("Inv No");
        cell091.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(0);

        HSSFCell cell092 = row09.createCell(1);
        cell092.setCellValue("Inv Date");
        cell092.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(1);

        HSSFCell cell093 = row09.createCell(2);
        cell093.setCellValue("Department");
        cell093.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(2);

        HSSFCell cell094 = row09.createCell(3);
        cell094.setCellValue("Staff");
        cell094.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(3);

        HSSFCell cell095 = row09.createCell(4);
        cell095.setCellValue("Term Pay");
        cell095.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(4);

        HSSFCell cell096 = row09.createCell(5);
        cell096.setCellValue("Agent");
        cell096.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(5);

        HSSFCell cell097 = row09.createCell(6);
        cell097.setCellValue("Type");
        cell097.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(6);

        HSSFCell cell098 = row09.createCell(7);
        cell098.setCellValue("Buy");
        cell098.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(7);

        HSSFCell cell099 = row09.createCell(8);
        cell099.setCellValue("Pax");
        cell099.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(8);

        HSSFCell cell0100 = row09.createCell(9);
        cell0100.setCellValue("air");
        cell0100.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(9);

        HSSFCell cell0101 = row09.createCell(10);
        cell0101.setCellValue("Doc No");
        cell0101.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(10);

        HSSFCell cell0102 = row09.createCell(11);
        cell0102.setCellValue("Ref No");
        cell0102.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(11);

        HSSFCell cell103 = row09.createCell(12);
        cell103.setCellValue("Issue Date");
        cell103.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(12);

        HSSFCell cell0104 = row09.createCell(13);
        cell0104.setCellValue("Amount Wendy");
        cell0104.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(13);

        HSSFCell cell0105 = row09.createCell(14);
        cell0105.setCellValue("Amount Outbound");
        cell0105.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(14);

        HSSFCell cell0106 = row09.createCell(15);
        cell0106.setCellValue("Amount Inbound");	
        cell0106.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(15);

        HSSFCell cell0107 = row09.createCell(16);
        cell0107.setCellValue("Amount Refund");
        cell0107.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(16);

        HSSFCell cell0108 = row09.createCell(17);
        cell0108.setCellValue("Amount Business Trip");
        cell0108.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(17);

        HSSFCell cell0109 = row09.createCell(18);
        cell0109.setCellValue("Amount Annual Leave");
        cell0109.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(18);

        HSSFCell cell0110 = row09.createCell(19);
        cell0110.setCellValue("Amount No Inv");
        cell0110.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(19);

        HSSFCell cell0111 = row09.createCell(20);
        cell0111.setCellValue("Sale");
        cell0111.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(20);
        
        HSSFCell cell0116 = row09.createCell(21);
        cell0116.setCellValue("Cost");
        cell0116.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(21);

        HSSFCell cell0112 = row09.createCell(22);
        cell0112.setCellValue("Over");
        cell0112.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(22);

        HSSFCell cell0113 = row09.createCell(23);
        cell0113.setCellValue("Add");
        cell0113.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(23);

        HSSFCell cell0114 = row09.createCell(24);
        cell0114.setCellValue("Dres");
        cell0114.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(24);

        HSSFCell cell0115 = row09.createCell(25);
        cell0115.setCellValue("Profit");
        cell0115.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(25);
       
        HSSFCell cell0117 = row09.createCell(26);
        cell0117.setCellValue("Comm");
        cell0117.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(25);
        
        HSSFCell cell0118 = row09.createCell(27);
        cell0118.setCellValue("Little");
        cell0118.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(27);

        HSSFCell cell0119 = row09.createCell(28);
        cell0119.setCellValue("Agent[Comm] Pay");
        cell0119.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(28);

        HSSFCell cell0130 = row09.createCell(29);
        cell0130.setCellValue("Agent[Comm] Receive");
        cell0130.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(29);

        HSSFCell cell0131 = row09.createCell(30);
        cell0131.setCellValue("Pay");
        cell0131.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(30);
       
        HSSFCell cell0132 = row09.createCell(31);
        cell0132.setCellValue("Net Comm Receive");
        cell0132.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(31);

        
        int count = 10 + listDetail.size();
        System.out.println(" listDetail.size() " + listDetail.size());
        System.out.println(" count " + count);
        if(listDetail != null && listDetail.size() != 0){
            for (int r = 10; r < count; r++) {
                HSSFRow row = sheet.createRow(r);
                HSSFCell cell1 = row.createCell(0);
                    cell1.setCellValue(listDetail.get(r-10).getInvno());
                    cell1.setCellStyle(styleDetailTable);
                HSSFCell cell2 = row.createCell(1);
                    cell2.setCellValue(listDetail.get(r-10).getInvdate());
                    cell2.setCellStyle(styleDetailTable);
                HSSFCell cell3 = row.createCell(2);
                    cell3.setCellValue(listDetail.get(r-10).getDepartment());
                    cell3.setCellStyle(styleDetailTable);
                HSSFCell cell4 = row.createCell(3);
                    cell4.setCellValue(listDetail.get(r-10).getOwner());
                    cell4.setCellStyle(styleDetailTable);
                HSSFCell cell55 = row.createCell(4);
                    cell55.setCellValue(listDetail.get(r-10).getTermpay());
                    cell55.setCellStyle(styleDetailTable);
                HSSFCell cell5 = row.createCell(5);
                    cell5.setCellValue(listDetail.get(r-10).getAgent());
                    cell5.setCellStyle(styleDetailTable);
                HSSFCell cell6 = row.createCell(6);
                    cell6.setCellValue(listDetail.get(r-10).getType());
                    cell6.setCellStyle(stylePaxNumber);
                HSSFCell cell7 = row.createCell(7);
                    cell7.setCellValue(listDetail.get(r-10).getBuy());
                    cell7.setCellStyle(stylePaxNumber);
                HSSFCell cell8 = row.createCell(8);
                    BigDecimal pax = new BigDecimal("".equals(listDetail.get(r-10).getPax()) ? "0" : listDetail.get(r-10).getPax());
                    cell8.setCellValue((pax != null) ? pax.doubleValue() : new BigDecimal("0").doubleValue());
                    cell8.setCellStyle(stylePaxNumber);
                HSSFCell cell9 = row.createCell(9);
                    cell9.setCellValue(listDetail.get(r-10).getAir());
                    cell9.setCellStyle(stylePaxNumber);
                HSSFCell cell10 = row.createCell(10);
                        cell10.setCellValue(listDetail.get(r-10).getDocno());
                        cell10.setCellStyle(styleDetailTable);
               HSSFCell cell11 = row.createCell(11);
                       cell11.setCellValue(listDetail.get(r-10).getRefno());
                       cell11.setCellStyle(styleDetailTable);
               HSSFCell cell12 = row.createCell(12);
                       cell12.setCellValue(listDetail.get(r-10).getIssuedate());
                       cell12.setCellStyle(stylePaxNumber);
               HSSFCell cell13 = row.createCell(13);
                       BigDecimal amountwendy = new BigDecimal("".equals(listDetail.get(r-10).getAmountwendy()) ? "0" : listDetail.get(r-10).getAmountwendy());
                       cell13.setCellValue((amountwendy != null) ? amountwendy.doubleValue() : new BigDecimal("0").doubleValue());
                       cell13.setCellStyle(styleDetailTableNumber);
               HSSFCell cell14 = row.createCell(14);
                       BigDecimal amountoutbound = new BigDecimal("".equals(listDetail.get(r-10).getAmountoutbound()) ? "0" : listDetail.get(r-10).getAmountoutbound());
                       cell14.setCellValue((amountoutbound != null) ? amountoutbound.doubleValue() : new BigDecimal("0").doubleValue());
                       cell14.setCellStyle(styleDetailTableNumber);
               HSSFCell cell27 = row.createCell(15);
                       BigDecimal amountinbound = new BigDecimal("".equals(listDetail.get(r-10).getAmountinbound()) ? "0" : listDetail.get(r-10).getAmountinbound());
                       cell27.setCellValue((amountinbound != null) ? amountinbound.doubleValue() : new BigDecimal("0").doubleValue());
                       cell27.setCellStyle(styleDetailTableNumber);     
                HSSFCell cell28 = row.createCell(16);
                       BigDecimal amountref = new BigDecimal("".equals(listDetail.get(r-10).getAmountrefund()) ? "0" : listDetail.get(r-10).getAmountrefund());
                       cell28.setCellValue((amountref != null) ? amountref.doubleValue() : new BigDecimal("0").doubleValue());
                       cell28.setCellStyle(styleDetailTableNumber);  
                HSSFCell cell29 = row.createCell(17);
                       BigDecimal amountbus = new BigDecimal("".equals(listDetail.get(r-10).getAmountbusinesstrip()) ? "0" : listDetail.get(r-10).getAmountbusinesstrip());
                       cell29.setCellValue((amountbus != null) ? amountbus.doubleValue() : new BigDecimal("0").doubleValue());
                       cell29.setCellStyle(styleDetailTableNumber);   
                HSSFCell cell30 = row.createCell(18);
                       BigDecimal amountann = new BigDecimal("".equals(listDetail.get(r-10).getAmountannualleave()) ? "0" : listDetail.get(r-10).getAmountannualleave());
                       cell30.setCellValue((amountann != null) ? amountann.doubleValue() : new BigDecimal("0").doubleValue());
                       cell30.setCellStyle(styleDetailTableNumber);   
                HSSFCell cell31 = row.createCell(19);
                       BigDecimal amountnoinv= new BigDecimal("".equals(listDetail.get(r-10).getAmountnoinvoice()) ? "0" : listDetail.get(r-10).getAmountnoinvoice());
                       cell31.setCellValue((amountnoinv != null) ? amountnoinv.doubleValue() : new BigDecimal("0").doubleValue());
                       cell31.setCellStyle(styleDetailTableNumber);   
               HSSFCell cell15 = row.createCell(20);
                       BigDecimal sale = new BigDecimal("".equals(listDetail.get(r-10).getSale()) ? "0" : listDetail.get(r-10).getSale());
                       cell15.setCellValue((sale != null) ? sale.doubleValue() : new BigDecimal("0").doubleValue());
                       cell15.setCellStyle(styleDetailTableNumber);
               HSSFCell cell16 = row.createCell(21);
                       BigDecimal cost  = new BigDecimal("".equals(listDetail.get(r-10).getCost()) ? "0" : listDetail.get(r-10).getCost());
                       cell16.setCellValue((cost != null) ? cost.doubleValue() : new BigDecimal("0").doubleValue());
                       cell16.setCellStyle(styleDetailTableNumber);
               HSSFCell cell17 = row.createCell(22);
                       BigDecimal over  = new BigDecimal("".equals(listDetail.get(r-10).getOver()) ? "0" : listDetail.get(r-10).getOver());
                       cell17.setCellValue((over != null) ? over.doubleValue() : new BigDecimal("0").doubleValue());
                       cell17.setCellStyle(styleDetailTableNumber);
               HSSFCell cell18 = row.createCell(23);
                       BigDecimal add  = new BigDecimal("".equals(listDetail.get(r-10).getAdd()) ? "0" : listDetail.get(r-10).getAdd());
                       cell18.setCellValue((add != null) ? add.doubleValue() : new BigDecimal("0").doubleValue());
                       cell18.setCellStyle(styleDetailTableNumber);
               HSSFCell cell19 = row.createCell(24);
                       BigDecimal dres  = new BigDecimal("".equals(listDetail.get(r-10).getDres()) ? "0" : listDetail.get(r-10).getDres());
                       cell19.setCellValue((dres != null) ? dres.doubleValue() : new BigDecimal("0").doubleValue());
                       cell19.setCellStyle(styleDetailTableNumber);
               HSSFCell cell20 = row.createCell(25);
                       BigDecimal profit  = new BigDecimal("".equals(listDetail.get(r-10).getProfit()) ? "0" : listDetail.get(r-10).getProfit());
                       cell20.setCellValue((profit != null) ? profit.doubleValue() : new BigDecimal("0").doubleValue());
                       cell20.setCellStyle(styleDetailTableNumber);
               HSSFCell cell25 = row.createCell(26);
                       BigDecimal ticcom  = new BigDecimal("".equals(listDetail.get(r-10).getTiccomm()) ? "0" : listDetail.get(r-10).getTiccomm());
                       cell25.setCellValue((ticcom != null) ? ticcom.doubleValue() : new BigDecimal("0").doubleValue());
                       cell25.setCellStyle(styleDetailTableNumber);
               HSSFCell cell21 = row.createCell(27);
                       BigDecimal little  = new BigDecimal("".equals(listDetail.get(r-10).getLittle()) ? "0" : listDetail.get(r-10).getLittle());
                       cell21.setCellValue((little != null) ? little.doubleValue() : new BigDecimal("0").doubleValue());
                       cell21.setCellStyle(styleDetailTableNumber);
               HSSFCell cell22 = row.createCell(28);
                        BigDecimal agentcommpay = new BigDecimal("".equals(listDetail.get(r-10).getAgentcommpay()) ? "0" : listDetail.get(r-10).getAgentcommpay());
                        cell22.setCellValue((agentcommpay != null) ? agentcommpay.doubleValue() : new BigDecimal("0").doubleValue());
                        cell22.setCellStyle(styleDetailTableNumber);
                HSSFCell cell23 = row.createCell(29);
                        BigDecimal agentcommrec = new BigDecimal("".equals(listDetail.get(r-10).getAgentcommrec()) ? "0" : listDetail.get(r-10).getAgentcommrec());
                        cell23.setCellValue((agentcommrec != null) ? agentcommrec.doubleValue() : new BigDecimal("0").doubleValue());
                        cell23.setCellStyle(styleDetailTableNumber);        
               HSSFCell cell24 = row.createCell(30);
                        BigDecimal pay = new BigDecimal("".equals(listDetail.get(r-10).getPay()) ? "0" : listDetail.get(r-10).getPay());
                        cell24.setCellValue((pay != null) ? pay.doubleValue() : new BigDecimal("0").doubleValue());
                        cell24.setCellStyle(styleDetailTableNumber);
               HSSFCell cell26 = row.createCell(31);
                        BigDecimal comm = new BigDecimal("".equals(listDetail.get(r-10).getComm()) ? "0" : listDetail.get(r-10).getComm());
                        cell26.setCellValue((comm != null) ? comm.doubleValue() : new BigDecimal("0").doubleValue());
                        cell26.setCellStyle(styleDetailTableNumber);

                for (int i = 0; i < 32; i++) {
                    sheet.autoSizeColumn(i);
                }
            }
        }else{
            HSSFRow row = sheet.createRow(12);
            HSSFCell cell1 = row.createCell(0);
                cell1.setCellValue("No Data");
        }
        
        HSSFRow rowtotalDetail = sheet.createRow(count);
        String sumtotalpaxDetail = "SUM(I" + 11+":I"+(count)+")";
        String sumtotalamountwenDetail = "SUM(N" + 11+":N"+(count)+")";
        String sumtotalamountoutDetail = "SUM(O" + 11+":O"+(count)+")";
        String sumtotalamountinDetail = "SUM(P" + 11+":P"+(count)+")";
        String sumtotalamountreDetail = "SUM(Q" + 11+":Q"+(count)+")";
        String sumtotalamountbusDetail = "SUM(R" + 11+":R"+(count)+")";
        String sumtotalamountanDetail = "SUM(S" + 11+":S"+(count)+")";
        String sumtotalamountinvDetail = "SUM(T" + 11+":T"+(count)+")";
        String sumtotalsaleDetail = "SUM(U" + 11+":U"+(count)+")";
        String sumtotalcostDetail = "SUM(V" + 11+":V"+(count)+")";
        String sumtotaloverDetail = "SUM(W" + 11+":W"+(count)+")";
        String sumtotaladdDetail = "SUM(X" + 11+":X"+(count)+")";
        String sumtotaldresDetail = "SUM(Y" + 11+":Y"+(count)+")";
        String sumtotalprofitDetail = "SUM(Z" + 11+":Z"+(count)+")";
        String sumtotalticketcommDetail = "SUM(AA" + 11+":AA"+(count)+")";
        String sumtotallittleDetail = "SUM(AB" + 11+":AB"+(count)+")";
        String sumtotalagentcommpayDetail = "SUM(AC" + 11+":AC"+(count)+")";
        String sumtotalagentcommrecDetail = "SUM(AD" + 11+":AD"+(count)+")";
        String sumtotalpayDetail = "SUM(AE" + 11+":AE"+(count)+")";
        String sumtotalnetcommDetail = "SUM(AF" + 11+":AF"+(count)+")";
//            sheetIncSum.addMergedRegion(CellRangeAddress.valueOf("A"+(count + x + 2)+":B"+(count + x + 2)));
//            sheetIncSum.addMergedRegion(CellRangeAddress.valueOf("D"+(count + x + 2)+":E"+(count + x + 2)));
        HSSFCell cellTotalSumDetail0 = rowtotalDetail.createCell(0);
            cellTotalSumDetail0.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumDetail1 = rowtotalDetail.createCell(1);
            cellTotalSumDetail1.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumDetail2 = rowtotalDetail.createCell(3);
            cellTotalSumDetail2.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumDetail3 = rowtotalDetail.createCell(2);
            cellTotalSumDetail3.setCellStyle(stylePaxNumberTotal);
            HSSFCell cellTotalSumDetail4 = rowtotalDetail.createCell(4);
            cellTotalSumDetail4.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumDetail5 = rowtotalDetail.createCell(5);
            cellTotalSumDetail5.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumDetail6 = rowtotalDetail.createCell(6);
            cellTotalSumDetail6.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumDetail7 = rowtotalDetail.createCell(7);
            cellTotalSumDetail7.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumDetail8 = rowtotalDetail.createCell(8);
            cellTotalSumDetail8.setCellFormula(sumtotalpaxDetail);
            cellTotalSumDetail8.setCellStyle(stylePaxNumberTotal);
            HSSFCell cellTotalSumDetail9 = rowtotalDetail.createCell(9);
            cellTotalSumDetail9.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumDetail10 = rowtotalDetail.createCell(10);
            cellTotalSumDetail10.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumDetail11 = rowtotalDetail.createCell(11);
            cellTotalSumDetail11.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumDetail12 = rowtotalDetail.createCell(12);
            cellTotalSumDetail12.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumDetail13 = rowtotalDetail.createCell(13);
            cellTotalSumDetail13.setCellFormula(sumtotalamountwenDetail);
            cellTotalSumDetail13.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumDetail14 = rowtotalDetail.createCell(14);
            cellTotalSumDetail14.setCellFormula(sumtotalamountoutDetail);
            cellTotalSumDetail14.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumDetail15 = rowtotalDetail.createCell(15);
            cellTotalSumDetail15.setCellFormula(sumtotalamountinDetail);
            cellTotalSumDetail15.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumDetail16 = rowtotalDetail.createCell(16);
            cellTotalSumDetail16.setCellFormula(sumtotalamountreDetail);
            cellTotalSumDetail16.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumDetail17 = rowtotalDetail.createCell(17);
            cellTotalSumDetail17.setCellFormula(sumtotalamountbusDetail);
            cellTotalSumDetail17.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumDetail18 = rowtotalDetail.createCell(18);
            cellTotalSumDetail18.setCellFormula(sumtotalamountanDetail);
            cellTotalSumDetail18.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumDetail19 = rowtotalDetail.createCell(19);
            cellTotalSumDetail19.setCellFormula(sumtotalamountinvDetail);
            cellTotalSumDetail19.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumDetail20 = rowtotalDetail.createCell(20);
            cellTotalSumDetail20.setCellFormula(sumtotalsaleDetail);
            cellTotalSumDetail20.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumDetail21 = rowtotalDetail.createCell(21);
            cellTotalSumDetail21.setCellFormula(sumtotalcostDetail);
            cellTotalSumDetail21.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumDetail22 = rowtotalDetail.createCell(22);
            cellTotalSumDetail22.setCellFormula(sumtotaloverDetail);
            cellTotalSumDetail22.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumDetail23 = rowtotalDetail.createCell(23);
            cellTotalSumDetail23.setCellFormula(sumtotaladdDetail);
            cellTotalSumDetail23.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumDetail24 = rowtotalDetail.createCell(24);
            cellTotalSumDetail24.setCellFormula(sumtotaldresDetail);
            cellTotalSumDetail24.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumDetail25 = rowtotalDetail.createCell(25);
            cellTotalSumDetail25.setCellFormula(sumtotalprofitDetail);
            cellTotalSumDetail25.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumDetail26 = rowtotalDetail.createCell(26);
            cellTotalSumDetail26.setCellFormula(sumtotalticketcommDetail);
            cellTotalSumDetail26.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumDetail27 = rowtotalDetail.createCell(27);
            cellTotalSumDetail27.setCellFormula(sumtotallittleDetail);
            cellTotalSumDetail27.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumDetail28 = rowtotalDetail.createCell(28);
            cellTotalSumDetail28.setCellFormula(sumtotalagentcommpayDetail);
            cellTotalSumDetail28.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumDetail29 = rowtotalDetail.createCell(29);
            cellTotalSumDetail29.setCellFormula(sumtotalagentcommrecDetail);
            cellTotalSumDetail29.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumDetail30 = rowtotalDetail.createCell(30);
            cellTotalSumDetail30.setCellFormula(sumtotalpayDetail);
            cellTotalSumDetail30.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumDetail31 = rowtotalDetail.createCell(31);
            cellTotalSumDetail31.setCellFormula(sumtotalnetcommDetail);
            cellTotalSumDetail31.setCellStyle(styleTotalTableNumber);
        
//        System.out.println(count);
//        HSSFRow rowL = sheet.createRow(count);
//        rowL.createCell(0).setCellStyle(styleBorderTop);
//        rowL.createCell(1).setCellStyle(styleBorderTop);
//        rowL.createCell(2).setCellStyle(styleBorderTop);
//        rowL.createCell(3).setCellStyle(styleBorderTop);
//        rowL.createCell(4).setCellStyle(styleBorderTop);
//        rowL.createCell(5).setCellStyle(styleBorderTop);
//        rowL.createCell(6).setCellStyle(styleBorderTop);
//        rowL.createCell(7).setCellStyle(styleBorderTop);
//        rowL.createCell(8).setCellStyle(styleBorderTop);
//        rowL.createCell(9).setCellStyle(styleBorderTop);
//        rowL.createCell(10).setCellStyle(styleBorderTop);
//        rowL.createCell(11).setCellStyle(styleBorderTop);
//        rowL.createCell(12).setCellStyle(styleBorderTop);
//        rowL.createCell(13).setCellStyle(styleBorderTop);
//        rowL.createCell(14).setCellStyle(styleBorderTop);
//        rowL.createCell(15).setCellStyle(styleBorderTop);
//        rowL.createCell(16).setCellStyle(styleBorderTop);
//        rowL.createCell(17).setCellStyle(styleBorderTop);
//        rowL.createCell(18).setCellStyle(styleBorderTop);
//        rowL.createCell(19).setCellStyle(styleBorderTop);
//        rowL.createCell(20).setCellStyle(styleBorderTop);
//        rowL.createCell(21).setCellStyle(styleBorderTop);
//        rowL.createCell(22).setCellStyle(styleBorderTop);
//        rowL.createCell(23).setCellStyle(styleBorderTop);
//        rowL.createCell(24).setCellStyle(styleBorderTop);
//        rowL.createCell(25).setCellStyle(styleBorderTop);
//        rowL.createCell(26).setCellStyle(styleBorderTop);
//        rowL.createCell(27).setCellStyle(styleBorderTop);
//        rowL.createCell(28).setCellStyle(styleBorderTop);
//        rowL.createCell(29).setCellStyle(styleBorderTop);
//        rowL.createCell(30).setCellStyle(styleBorderTop);
//        rowL.createCell(31).setCellStyle(styleBorderTop);
//**********************************************************************************************************************
	// set Header Report (Row 1)
            HSSFCellStyle styleHeader02 = wb.createCellStyle();
            HSSFRow row21 = sheet1.createRow(0);
            HSSFCell cell21 = row21.createCell(0);
            cell21.setCellValue("List summary commission");
            styleHeader02.setFont(excelFunction.getHeaderFont(wb.createFont()));
            cell21.setCellStyle(styleHeader02);
            sheet1.addMergedRegion(CellRangeAddress.valueOf("A1:M1"));
            
            // Row 2
            HSSFRow row22 = sheet1.createRow(1);
            HSSFCell cell221 = row22.createCell(0);
                    cell221.setCellValue("Agent Name : ");
                    cell221.setCellStyle(styleAlignRight);
                    sheet1.autoSizeColumn(0);
            HSSFCell cell222 = row22.createCell(1);
                    cell222.setCellValue(ticketAir.getAgentNamePage() != null && !"".equals(ticketDetail.getAgentNamePage())? ticketDetail.getAgentNamePage() :"ALL");
                    cell222.setCellStyle(styleAlignLeft);
                    sheet1.autoSizeColumn(1);
            HSSFCell cell223 = row22.createCell(2);
                    cell223.setCellValue("Issue date : ");
                    cell223.setCellStyle(styleAlignRight);
                    sheet1.autoSizeColumn(2);
            HSSFCell cell224 = row22.createCell(3);
                    cell224.setCellValue(ticketAir.getIssuefromdatePage() != null && !"".equals(ticketDetail.getIssuefromdatePage())? ticketDetail.getIssuefromdatePage() :"ALL");
                    cell224.setCellStyle(styleAlignLeft);
                    sheet1.autoSizeColumn(3);
            HSSFCell cell225 = row22.createCell(4);
                    cell225.setCellValue("Print By : ");
                    cell225.setCellStyle(styleAlignRight);
                    sheet1.autoSizeColumn(4);
            HSSFCell cell226 = row22.createCell(5);
                    cell226.setCellValue(ticketAir.getPrintbyPage() != null && !"".equals(ticketDetail.getPrintbyPage())? ticketDetail.getPrintbyPage() :"ALL");
                    cell226.setCellStyle(styleAlignLeft);
                    sheet1.autoSizeColumn(5);

            // Row 3
            HSSFRow row23 = sheet1.createRow(2);
            HSSFCell cell231 = row23.createCell(0);
                    cell231.setCellValue("Type Routing : ");
                    cell231.setCellStyle(styleAlignRight);
                    sheet1.autoSizeColumn(0);
            HSSFCell cell232 = row23.createCell(1);
                    cell232.setCellValue(ticketAir.getTypeRoutingPage() != null && !"".equals(ticketDetail.getTypeRoutingPage())? ticketDetail.getTypeRoutingPage() :"ALL");
                    cell232.setCellStyle(styleAlignLeft);
                    sheet1.autoSizeColumn(1);
            HSSFCell cell233 = row23.createCell(2);
                    cell233.setCellValue("Over Comm Date : ");
                    cell233.setCellStyle(styleAlignRight);
                    sheet1.autoSizeColumn(2);
            HSSFCell cell234 = row23.createCell(3);
                    cell234.setCellValue(ticketAir.getOverfromdatePage() != null && !"".equals(ticketDetail.getOverfromdatePage())? ticketDetail.getOverfromdatePage() :"ALL");
                    cell234.setCellStyle(styleAlignLeft);
                    sheet1.autoSizeColumn(3);
            HSSFCell cell235 = row23.createCell(4);
                    cell235.setCellValue("Add Pay Date : ");
                    cell235.setCellStyle(styleAlignRight);
                    sheet1.autoSizeColumn(4);
            HSSFCell cell236 = row23.createCell(5);
                    cell236.setCellValue(ticketAir.getAddpayfromdatePage() != null && !"".equals(ticketDetail.getAddpayfromdatePage())? ticketDetail.getAddpayfromdatePage() :"ALL");
                    cell236.setCellStyle(styleAlignLeft);
                    sheet1.autoSizeColumn(5);

            // Row 4
            HSSFRow row24 = sheet1.createRow(3);
            HSSFCell cell241 = row24.createCell(0);
                    cell241.setCellValue("Air : ");
                    cell241.setCellStyle(styleAlignRight);
                    sheet1.autoSizeColumn(0);
            HSSFCell cell242 = row24.createCell(1);
                    cell242.setCellValue(ticketAir.getAirlineCodePage() != null && !"".equals(ticketDetail.getAirlineCodePage())? ticketDetail.getAirlineCodePage() :"ALL");
                    cell242.setCellStyle(styleAlignLeft);
                    sheet1.autoSizeColumn(1);
            HSSFCell cell243 = row24.createCell(2);
                    cell243.setCellValue("Little Comm date : ");
                    cell243.setCellStyle(styleAlignRight);
                    sheet1.autoSizeColumn(2);
            HSSFCell cell244 = row24.createCell(3);
                    cell244.setCellValue(ticketAir.getLittlefromdatePage() != null && !"".equals(ticketDetail.getLittlefromdatePage())? ticketDetail.getLittlefromdatePage() :"ALL");
                    cell244.setCellStyle(styleAlignLeft);
                    sheet1.autoSizeColumn(3);
            HSSFCell cell245 = row24.createCell(4);
                    cell245.setCellValue("Decrease Pay Date : ");
                    cell245.setCellStyle(styleAlignRight);
                    sheet1.autoSizeColumn(4);
            HSSFCell cell246 = row24.createCell(5);
                    cell246.setCellValue(ticketAir.getDecreasepayfromdatePage() != null && !"".equals(ticketDetail.getDecreasepayfromdatePage())? ticketDetail.getDecreasepayfromdatePage() :"ALL");
                    cell246.setCellStyle(styleAlignLeft);
                    sheet1.autoSizeColumn(5);

            // Row 5
            HSSFRow row25 = sheet1.createRow(4);
            HSSFCell cell251 = row25.createCell(0);
                    cell251.setCellValue("Routing Detail : ");
                    cell251.setCellStyle(styleAlignRight);
                    sheet1.autoSizeColumn(0);
            HSSFCell cell252 = row25.createCell(1);
                    cell252.setCellValue(ticketAir.getRoutingDetailPage() != null && !"".equals(ticketDetail.getRoutingDetailPage())? ticketDetail.getRoutingDetailPage() :"ALL");
                    cell252.setCellStyle(styleAlignLeft);
                    sheet1.autoSizeColumn(1);
            HSSFCell cell253 = row25.createCell(2);
                    cell253.setCellValue("Agent Comm Rev Date : ");
                    cell253.setCellStyle(styleAlignRight);
                    sheet1.autoSizeColumn(2);
            HSSFCell cell254 = row25.createCell(3);
                    cell254.setCellValue(ticketAir.getAgemtcomreceivefromdatePage() != null && !"".equals(ticketDetail.getAgemtcomreceivefromdatePage())? ticketDetail.getAgemtcomreceivefromdatePage() :"ALL");
                    cell254.setCellStyle(styleAlignLeft);
                    sheet1.autoSizeColumn(3);
            HSSFCell cell255 = row25.createCell(4);
                    cell255.setCellValue("Ticket No : ");
                    cell255.setCellStyle(styleAlignRight);
                    sheet1.autoSizeColumn(4);
            HSSFCell cell256 = row25.createCell(5);
                    cell256.setCellValue(ticketAir.getTicketcomfromdatePage() != null && !"".equals(ticketDetail.getTicketcomfromdatePage())? ticketDetail.getTicketcomfromdatePage() :"ALL");
                    cell256.setCellStyle(styleAlignLeft);
                    sheet1.autoSizeColumn(5);

            // Row 6
            HSSFRow row26 = sheet1.createRow(5);
            HSSFCell cell261 = row26.createCell(0);
                    cell261.setCellValue("Sale Staff : ");
                    cell261.setCellStyle(styleAlignRight);
            HSSFCell cell262 = row26.createCell(1);
                    cell262.setCellValue(ticketAir.getSalebyNamePage() != null && !"".equals(ticketDetail.getSalebyNamePage())? ticketDetail.getSalebyNamePage() :"ALL");
                    cell262.setCellStyle(styleAlignLeft);
            HSSFCell cell263 = row26.createCell(2);
                    cell263.setCellValue("Refund Comm Date : ");
                    cell263.setCellStyle(styleAlignRight);
            HSSFCell cell264 = row26.createCell(3);
                    cell264.setCellValue(ticketAir.getComrefundfromdatePage() != null && !"".equals(ticketDetail.getComrefundfromdatePage())? ticketDetail.getComrefundfromdatePage() :"ALL");
                    cell264.setCellStyle(styleAlignLeft);
            HSSFCell cell265 = row26.createCell(4);
                    cell265.setCellValue("Ticket Comm Date : ");
                    cell265.setCellStyle(styleAlignRight);
            HSSFCell cell266 = row26.createCell(5);
                    cell266.setCellValue(ticketAir.getTicketcomfromdatePage() != null && !"".equals(ticketDetail.getTicketcomfromdatePage())? ticketDetail.getTicketcomfromdatePage() :"ALL");
                    cell266.setCellStyle(styleAlignLeft);

            // Row 7
            HSSFRow row27 = sheet1.createRow(6);
            HSSFCell cell271 = row27.createCell(0);
                    cell271.setCellValue("Department : ");
                    cell271.setCellStyle(styleAlignRight);
            HSSFCell cell272 = row27.createCell(1);
                    cell272.setCellValue(ticketAir.getDepartmentPage() != null && !"".equals(ticketDetail.getDepartmentPage())? ticketDetail.getDepartmentPage() :"ALL");
                    cell272.setCellStyle(styleAlignLeft);
            HSSFCell cell273 = row27.createCell(2);
                    cell273.setCellValue("Invoice Date : ");
                    cell273.setCellStyle(styleAlignRight);
            HSSFCell cell274 = row27.createCell(3);
                    cell274.setCellValue(ticketAir.getInvoicefromdatePage() != null && !"".equals(ticketDetail.getInvoicefromdatePage())? ticketDetail.getInvoicefromdatePage() :"ALL");
                    cell274.setCellStyle(styleAlignLeft);
            HSSFCell cell275 = row27.createCell(4);
                    cell275.setCellValue("Print on : ");
                    cell275.setCellStyle(styleAlignRight);
            HSSFCell cell276 = row27.createCell(5);
                    cell276.setCellValue(ticketAir.getPrintonPage() != null && !"".equals(ticketDetail.getPrintonPage())? ticketDetail.getPrintonPage() :"ALL");
                    cell276.setCellStyle(styleAlignLeft);

            // Row 8
            HSSFRow row28 = sheet1.createRow(7);
            HSSFCell cell281 = row28.createCell(0);
                    cell281.setCellValue("Term Pay : ");
                    cell281.setCellStyle(styleAlignRight);
            HSSFCell cell282 = row28.createCell(1);
                    cell282.setCellValue(ticketAir.getTermPayPage() != null && !"".equals(ticketDetail.getTermPayPage())? ticketDetail.getTermPayPage() :"ALL");
                    cell282.setCellStyle(styleAlignLeft);
            HSSFCell cell283 = row28.createCell(2);
                    cell283.setCellValue("Page : ");
                    cell283.setCellStyle(styleAlignRight);
            HSSFCell cell284 = row28.createCell(3);
                    cell284.setCellValue("1 ");
                    cell284.setCellStyle(styleAlignLeft);


            // Header Table
            HSSFRow row29 = sheet1.createRow(9);
            HSSFCell cell291 = row29.createCell(0);
            cell291.setCellValue("Type Pay");
            cell291.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(0);

            HSSFCell cell292 = row29.createCell(1);
            cell292.setCellValue("Type Route");
            cell292.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(1);

            HSSFCell cell293 = row29.createCell(2);
            cell293.setCellValue("Pax");
            cell293.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(2);

            HSSFCell cell294 = row29.createCell(3);
            cell294.setCellValue("Air");
            cell294.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(3);

            HSSFCell cell295 = row29.createCell(4);
            cell295.setCellValue("Amount Wendy");
            cell295.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(4);
            
            HSSFCell cell296 = row29.createCell(5);
            cell296.setCellValue("Amount Outbound");
            cell296.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(5);
            
            HSSFCell cell2110 = row29.createCell(6);
            cell2110.setCellValue("Amount Inbound");
            cell2110.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(6);

            HSSFCell cell297 = row29.createCell(7);
            cell297.setCellValue("Sale Price");
            cell297.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(7);

            HSSFCell cell298 = row29.createCell(8);
            cell298.setCellValue("Cost");
            cell298.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(8);

            HSSFCell cell299 = row29.createCell(9);
            cell299.setCellValue("Over Comm");
            cell299.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(9);

            HSSFCell cell2100 = row29.createCell(10);
            cell2100.setCellValue("Add Pay");
            cell2100.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(10);

            HSSFCell cell2101 = row29.createCell(11);
            cell2101.setCellValue("Drecrease Pay");
            cell2101.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(11);

            HSSFCell cell2103 = row29.createCell(12);
            cell2103.setCellValue("Profit");
            cell2103.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(12);

            HSSFCell cell2104 = row29.createCell(13);
            cell2104.setCellValue("Comm");
            cell2104.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(13);

            HSSFCell cell2105 = row29.createCell(14);
            cell2105.setCellValue("Little Comm");
            cell2105.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(14);

            HSSFCell cell2106 = row29.createCell(15);
            cell2106.setCellValue("Agent[Comm] Pay");	
            cell2106.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(15);

            HSSFCell cell2107 = row29.createCell(16);
            cell2107.setCellValue("Agent[Comm] Receive");
            cell2107.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(16);

            HSSFCell cell2108 = row29.createCell(17);
            cell2108.setCellValue("Pay Comm Refund");
            cell2108.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(17);

            HSSFCell cell2109 = row29.createCell(18);
            cell2109.setCellValue("Comm Receive");
            cell2109.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(18);
           
        int count1 = 10 + listAir.size();
	if(listAir  != null){
            for (int r = 10; r < count1; r++) {
                    HSSFRow row = sheet1.createRow(r);
                    HSSFCell cell1 = row.createCell(0);
                        cell1.setCellValue(listAir.get(r-10).getTypepayment());
                        cell1.setCellStyle(styleDetailTable);
                    HSSFCell cell2 = row.createCell(1);
                        cell2.setCellValue(listAir.get(r-10).getTyperounting());
                        cell2.setCellStyle(stylePaxNumber);
                    HSSFCell cell3 = row.createCell(2);
                       BigDecimal pax = new BigDecimal("".equals(listAir.get(r-10).getPax()) ? "0" : listAir.get(r-10).getPax());
                       cell3.setCellValue((pax != null) ? pax.doubleValue() : new BigDecimal("0").doubleValue());
                       cell3.setCellStyle(stylePaxNumber);
                    HSSFCell cell4 = row.createCell(3);
                        cell4.setCellValue(listAir.get(r-10).getAir());
                        cell4.setCellStyle(stylePaxNumber);
                    HSSFCell cell5 = row.createCell(4);
                        BigDecimal amountwendy = new BigDecimal("".equals(listAir.get(r-10).getAmountwendy()) ? "0" : listAir.get(r-10).getAmountwendy());
                        cell5.setCellValue((amountwendy != null) ? amountwendy.doubleValue() : new BigDecimal("0").doubleValue());
                        cell5.setCellStyle(styleDetailTableNumber);
                    HSSFCell cell19 = row.createCell(5);
                            BigDecimal amountoutbound = new BigDecimal("null".equals(String.valueOf(listAir.get(r-10).getAmountoutbound())) ? "0" : listAir.get(r-10).getAmountoutbound());
                            cell19.setCellValue((amountoutbound != null) ? amountoutbound.doubleValue() : new BigDecimal("0").doubleValue());
                            cell19.setCellStyle(styleDetailTableNumber);
                   HSSFCell cell6 = row.createCell(6);
                           BigDecimal amountinbound = new BigDecimal("null".equals(String.valueOf(listAir.get(r-10).getAmountinbound())) ? "0" : listAir.get(r-10).getAmountinbound());
                           cell6.setCellValue((amountinbound != null) ? amountinbound.doubleValue() : new BigDecimal("0").doubleValue());
                           cell6.setCellStyle(styleDetailTableNumber);
                   HSSFCell cell7 = row.createCell(7);
                           BigDecimal sale = new BigDecimal("".equals(listAir.get(r-10).getSale()) ? "0" : listAir.get(r-10).getSale());
                           cell7.setCellValue((sale != null) ? sale.doubleValue() : new BigDecimal("0").doubleValue());
                           cell7.setCellStyle(styleDetailTableNumber);
                   HSSFCell cell8 = row.createCell(8);
                           BigDecimal cost  = new BigDecimal("".equals(listAir.get(r-10).getCost()) ? "0" : listAir.get(r-10).getCost());
                           cell8.setCellValue((cost != null) ? cost.doubleValue() : new BigDecimal("0").doubleValue());
                           cell8.setCellStyle(styleDetailTableNumber);
                   HSSFCell cell9 = row.createCell(9);
                           BigDecimal over  = new BigDecimal("".equals(listAir.get(r-10).getOver()) ? "0" : listAir.get(r-10).getOver());
                           cell9.setCellValue((over != null) ? over.doubleValue() : new BigDecimal("0").doubleValue());
                           cell9.setCellStyle(styleDetailTableNumber);
                   HSSFCell cell10 = row.createCell(10);
                           BigDecimal add  = new BigDecimal("".equals(listAir.get(r-10).getAdd()) ? "0" : listAir.get(r-10).getAdd());
                           cell10.setCellValue((add != null) ? add.doubleValue() : new BigDecimal("0").doubleValue());
                           cell10.setCellStyle(styleDetailTableNumber);
                   HSSFCell cell11 = row.createCell(11);
                           BigDecimal dres  = new BigDecimal("".equals(listAir.get(r-10).getDres()) ? "0" : listAir.get(r-10).getDres());
                           cell11.setCellValue((dres != null) ? dres.doubleValue() : new BigDecimal("0").doubleValue());
                           cell11.setCellStyle(styleDetailTableNumber);
                   HSSFCell cell12 = row.createCell(12);
                           BigDecimal profit  = new BigDecimal("".equals(listAir.get(r-10).getProfit()) ? "0" : listAir.get(r-10).getProfit());
                           cell12.setCellValue((profit != null) ? profit.doubleValue() : new BigDecimal("0").doubleValue());
                           cell12.setCellStyle(styleDetailTableNumber);
                   HSSFCell cell13 = row.createCell(13);
                           BigDecimal ticcom  = new BigDecimal("".equals(listAir.get(r-10).getTiccomm()) ? "0" : listAir.get(r-10).getTiccomm());
                           cell13.setCellValue((ticcom != null) ? ticcom.doubleValue() : new BigDecimal("0").doubleValue());
                           cell13.setCellStyle(styleDetailTableNumber);
                   HSSFCell cell14 = row.createCell(14);
                           BigDecimal little  = new BigDecimal("".equals(listAir.get(r-10).getLittle()) ? "0" : listAir.get(r-10).getLittle());
                           cell14.setCellValue((little != null) ? little.doubleValue() : new BigDecimal("0").doubleValue());
                           cell14.setCellStyle(styleDetailTableNumber);
                   HSSFCell cell15 = row.createCell(15);
                            BigDecimal agentcommpay = new BigDecimal("".equals(listAir.get(r-10).getAgentcommpay()) ? "0" : listAir.get(r-10).getAgentcommpay());
                            cell15.setCellValue((agentcommpay != null) ? agentcommpay.doubleValue() : new BigDecimal("0").doubleValue());
                            cell15.setCellStyle(styleDetailTableNumber);
                   HSSFCell cell16 = row.createCell(16);
                            BigDecimal agentcommrec = new BigDecimal("".equals(listAir.get(r-10).getAgentcommrec()) ? "0" : listAir.get(r-10).getAgentcommrec());
                            cell16.setCellValue((agentcommrec != null) ? agentcommrec.doubleValue() : new BigDecimal("0").doubleValue());
                            cell16.setCellStyle(styleDetailTableNumber);
                   HSSFCell cell17 = row.createCell(17);
                            BigDecimal pay = new BigDecimal("".equals(listAir.get(r-10).getPay()) ? "0" : listAir.get(r-10).getPay());
                            cell17.setCellValue((pay != null) ? pay.doubleValue() : new BigDecimal("0").doubleValue());
                            cell17.setCellStyle(styleDetailTableNumber);
                   HSSFCell cell18 = row.createCell(18);
                            BigDecimal comm = new BigDecimal("".equals(listAir.get(r-10).getComm()) ? "0" : listAir.get(r-10).getComm());
                            cell18.setCellValue((comm != null) ? comm.doubleValue() : new BigDecimal("0").doubleValue());
                            cell18.setCellStyle(styleDetailTableNumber);
                for (int i = 0; i < 25; i++) {
                    sheet1.autoSizeColumn(i);
                }
            }
        }
        
        HSSFRow rowtotal = sheet1.createRow(count1);
        String sumtotalpax = "SUM(C" + 11+":C"+(count1)+")";
        String sumtotalamountwen = "SUM(E" + 11+":E"+(count1)+")";
        String sumtotalamountout = "SUM(F" + 11+":F"+(count1)+")";
        String sumtotalamountin = "SUM(G" + 11+":G"+(count1)+")";
        String sumtotalsale = "SUM(H" + 11+":H"+(count1)+")";
        String sumtotalcost = "SUM(I" + 11+":I"+(count1)+")";
        String sumtotalover = "SUM(J" + 11+":J"+(count1)+")";
        String sumtotaladd = "SUM(K" + 11+":K"+(count1)+")";
        String sumtotaldres = "SUM(L" + 11+":L"+(count1)+")";
        String sumtotalprofit = "SUM(M" + 11+":M"+(count1)+")";
        String sumtotalticketcomm = "SUM(N" + 11+":N"+(count1)+")";
        String sumtotallittle = "SUM(O" + 11+":O"+(count1)+")";
        String sumtotalagentcommpay = "SUM(P" + 11+":P"+(count1)+")";
        String sumtotalagentcommrec = "SUM(Q" + 11+":Q"+(count1)+")";
        String sumtotalpay = "SUM(R" + 11+":R"+(count1)+")";
        String sumtotalcomm = "SUM(S" + 11+":S"+(count1)+")";
//            sheetIncSum.addMergedRegion(CellRangeAddress.valueOf("A"+(count + x + 2)+":B"+(count + x + 2)));
//            sheetIncSum.addMergedRegion(CellRangeAddress.valueOf("D"+(count + x + 2)+":E"+(count + x + 2)));
        HSSFCell cellTotalSum00 = rowtotal.createCell(0);
            cellTotalSum00.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSum16 = rowtotal.createCell(1);
            cellTotalSum16.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSum17 = rowtotal.createCell(3);
            cellTotalSum17.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSum01 = rowtotal.createCell(2);
            cellTotalSum01.setCellFormula(sumtotalpax);
            cellTotalSum01.setCellStyle(stylePaxNumberTotal);
            HSSFCell cellTotalSum18 = rowtotal.createCell(4);
            cellTotalSum18.setCellFormula(sumtotalamountwen);
            cellTotalSum18.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSum02 = rowtotal.createCell(5);
            cellTotalSum02.setCellFormula(sumtotalamountout);
            cellTotalSum02.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSum03 = rowtotal.createCell(6);
            cellTotalSum03.setCellFormula(sumtotalamountin);
            cellTotalSum03.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSum04 = rowtotal.createCell(7);
            cellTotalSum04.setCellFormula(sumtotalsale);
            cellTotalSum04.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSum05 = rowtotal.createCell(8);
            cellTotalSum05.setCellFormula(sumtotalcost);
            cellTotalSum05.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSum06 = rowtotal.createCell(9);
            cellTotalSum06.setCellFormula(sumtotalover);
            cellTotalSum06.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSum07 = rowtotal.createCell(10);
            cellTotalSum07.setCellFormula(sumtotaladd);
            cellTotalSum07.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSum08 = rowtotal.createCell(11);
            cellTotalSum08.setCellFormula(sumtotaldres);
            cellTotalSum08.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSum09 = rowtotal.createCell(12);
            cellTotalSum09.setCellFormula(sumtotalprofit);
            cellTotalSum09.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSum10 = rowtotal.createCell(13);
            cellTotalSum10.setCellFormula(sumtotalticketcomm);
            cellTotalSum10.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSum11 = rowtotal.createCell(14);
            cellTotalSum11.setCellFormula(sumtotallittle);
            cellTotalSum11.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSum12 = rowtotal.createCell(15);
            cellTotalSum12.setCellFormula(sumtotalagentcommpay);
            cellTotalSum12.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSum13 = rowtotal.createCell(16);
            cellTotalSum13.setCellFormula(sumtotalagentcommrec);
            cellTotalSum13.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSum14 = rowtotal.createCell(17);
            cellTotalSum14.setCellFormula(sumtotalpay);
            cellTotalSum14.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSum15 = rowtotal.createCell(18);
            cellTotalSum15.setCellFormula(sumtotalcomm);
            cellTotalSum15.setCellStyle(styleTotalTableNumber);

        
//                       
//        HSSFRow rowLL = sheet1.createRow(count1+1);
//         rowLL.createCell(0).setCellStyle(styleBorderTop);
//         rowLL.createCell(1).setCellStyle(styleBorderTop);
//         rowLL.createCell(2).setCellStyle(styleBorderTop);
//         rowLL.createCell(3).setCellStyle(styleBorderTop);
//         rowLL.createCell(4).setCellStyle(styleBorderTop);
//         rowLL.createCell(5).setCellStyle(styleBorderTop);
//         rowLL.createCell(6).setCellStyle(styleBorderTop);
//         rowLL.createCell(7).setCellStyle(styleBorderTop);
//         rowLL.createCell(8).setCellStyle(styleBorderTop);
//         rowLL.createCell(9).setCellStyle(styleBorderTop);
//         rowLL.createCell(10).setCellStyle(styleBorderTop);
//         rowLL.createCell(11).setCellStyle(styleBorderTop);
//         rowLL.createCell(12).setCellStyle(styleBorderTop);
//         rowLL.createCell(13).setCellStyle(styleBorderTop);
//         rowLL.createCell(14).setCellStyle(styleBorderTop);
//         rowLL.createCell(15).setCellStyle(styleBorderTop);
//         rowLL.createCell(16).setCellStyle(styleBorderTop);
//         rowLL.createCell(17).setCellStyle(styleBorderTop);

	         
//****************************************************************************************************************		
	// set Header Report (Row 1)
                HSSFCellStyle styleHeader03 = wb.createCellStyle();
                HSSFRow row31 = sheet2.createRow(0);
                HSSFCell cell31 = row31.createCell(0);
                cell31.setCellValue("List summary commission");
                styleHeader03.setFont(excelFunction.getHeaderFont(wb.createFont()));
                cell31.setCellStyle(styleHeader03);
                sheet2.addMergedRegion(CellRangeAddress.valueOf("A1:M1"));

                // Row 2
                HSSFRow row32 = sheet2.createRow(1);
                HSSFCell cell321 = row32.createCell(0);
                cell321.setCellValue("Agent Name : ");
                cell321.setCellStyle(styleAlignRight);
                        sheet2.autoSizeColumn(0);
                HSSFCell cell322 = row32.createCell(1);
                cell322.setCellValue(ticketAgent.getAgentNamePage() != null && !"".equals(ticketAgent.getAgentNamePage()) ? ticketAgent.getAgentNamePage() : "ALL");
                cell322.setCellStyle(styleAlignLeft);
                        sheet2.autoSizeColumn(1);
                HSSFCell cell323 = row32.createCell(2);
                cell323.setCellValue("Issue date : ");
                cell323.setCellStyle(styleAlignRight);
                        sheet2.autoSizeColumn(2);
                HSSFCell cell324 = row32.createCell(3);
                cell324.setCellValue(ticketAgent.getIssuefromdatePage() != null && !"".equals(ticketAgent.getIssuefromdatePage()) ? ticketAgent.getIssuefromdatePage() : "ALL");
                cell324.setCellStyle(styleAlignLeft);
                        sheet2.autoSizeColumn(3);
                HSSFCell cell325 = row32.createCell(4);
                cell325.setCellValue("Print By : ");
                cell325.setCellStyle(styleAlignRight);
                        sheet2.autoSizeColumn(4);
                HSSFCell cell326 = row32.createCell(5);
                cell326.setCellValue(ticketAgent.getPrintbyPage() != null && !"".equals(ticketAgent.getPrintbyPage()) ? ticketAgent.getPrintbyPage() : "ALL");
                cell326.setCellStyle(styleAlignLeft);
                        sheet2.autoSizeColumn(5);

                // Row 3
                HSSFRow row33 = sheet2.createRow(2);
                HSSFCell cell331 = row33.createCell(0);
                cell331.setCellValue("Type Routing : ");
                cell331.setCellStyle(styleAlignRight);
                        sheet2.autoSizeColumn(0);
                HSSFCell cell332 = row33.createCell(1);
                cell332.setCellValue(ticketAgent.getTypeRoutingPage() != null && !"".equals(ticketAgent.getTypeRoutingPage()) ? ticketAgent.getTypeRoutingPage() : "ALL");
                cell332.setCellStyle(styleAlignLeft);
                        sheet2.autoSizeColumn(1);
                HSSFCell cell333 = row33.createCell(2);
                cell333.setCellValue("Over Comm Date : ");
                cell333.setCellStyle(styleAlignRight);
                        sheet2.autoSizeColumn(2);
                HSSFCell cell334 = row33.createCell(3);
                cell334.setCellValue(ticketAgent.getOverfromdatePage() != null && !"".equals(ticketAgent.getOverfromdatePage()) ? ticketAgent.getOverfromdatePage() : "ALL");
                cell334.setCellStyle(styleAlignLeft);
                        sheet2.autoSizeColumn(3);
                HSSFCell cell335 = row33.createCell(4);
                cell335.setCellValue("Add Pay Date : ");
                cell335.setCellStyle(styleAlignRight);
                        sheet2.autoSizeColumn(4);
                HSSFCell cell336 = row33.createCell(5);
                cell336.setCellValue(ticketAgent.getAddpayfromdatePage() != null && !"".equals(ticketAgent.getAddpayfromdatePage()) ? ticketAgent.getAddpayfromdatePage() : "ALL");
                cell336.setCellStyle(styleAlignLeft);
                        sheet2.autoSizeColumn(5);

                // Row 4
                HSSFRow row34 = sheet2.createRow(3);
                HSSFCell cell341 = row34.createCell(0);
                cell341.setCellValue("Air : ");
                cell341.setCellStyle(styleAlignRight);
                        sheet2.autoSizeColumn(0);
                HSSFCell cell342 = row34.createCell(1);
                cell342.setCellValue(ticketAgent.getAirlineCodePage() != null && !"".equals(ticketAgent.getAirlineCodePage()) ? ticketAgent.getAirlineCodePage() : "ALL");
                cell342.setCellStyle(styleAlignLeft);
                        sheet2.autoSizeColumn(1);
                HSSFCell cell343 = row34.createCell(2);
                cell343.setCellValue("Little Comm date : ");
                cell343.setCellStyle(styleAlignRight);
                        sheet2.autoSizeColumn(2);
                HSSFCell cell344 = row34.createCell(3);
                cell344.setCellValue(ticketAgent.getLittlefromdatePage() != null && !"".equals(ticketAgent.getLittlefromdatePage()) ? ticketAgent.getLittlefromdatePage() : "ALL");
                cell344.setCellStyle(styleAlignLeft);
                        sheet2.autoSizeColumn(3);
                HSSFCell cell345 = row34.createCell(4);
                cell345.setCellValue("Decrease Pay Date : ");
                cell345.setCellStyle(styleAlignRight);
                        sheet2.autoSizeColumn(4);
                HSSFCell cell346 = row34.createCell(5);
                cell346.setCellValue(ticketAgent.getDecreasepayfromdatePage() != null && !"".equals(ticketAgent.getDecreasepayfromdatePage()) ? ticketAgent.getDecreasepayfromdatePage() : "ALL");
                cell346.setCellStyle(styleAlignLeft);
                        sheet2.autoSizeColumn(5);

                // Row 5
                HSSFRow row35 = sheet2.createRow(4);
                HSSFCell cell351 = row35.createCell(0);
                cell351.setCellValue("Routing Detail : ");
                cell351.setCellStyle(styleAlignRight);
                        sheet2.autoSizeColumn(0);
                HSSFCell cell352 = row35.createCell(1);
                cell352.setCellValue(ticketAgent.getRoutingDetailPage() != null && !"".equals(ticketAgent.getRoutingDetailPage()) ? ticketAgent.getRoutingDetailPage() : "ALL");
                cell352.setCellStyle(styleAlignLeft);
                        sheet2.autoSizeColumn(1);
                HSSFCell cell353 = row35.createCell(2);
                cell353.setCellValue("Agent Comm Rev Date : ");
                cell353.setCellStyle(styleAlignRight);
                        sheet2.autoSizeColumn(2);
                HSSFCell cell354 = row35.createCell(3);
                cell354.setCellValue(ticketAgent.getAgentcomfromdatePage() != null && !"".equals(ticketAgent.getAgentcomfromdatePage()) ? ticketAgent.getAgentcomfromdatePage() : "ALL");
                cell354.setCellStyle(styleAlignLeft);
                        sheet2.autoSizeColumn(3);
                HSSFCell cell355 = row35.createCell(4);
                cell355.setCellValue("Ticket No : ");
                cell355.setCellStyle(styleAlignRight);
                        sheet2.autoSizeColumn(4);
                HSSFCell cell356 = row35.createCell(5);
                cell356.setCellValue(ticketAgent.getTicketnoPagePage() != null && !"".equals(ticketAgent.getTicketnoPagePage()) ? ticketAgent.getTicketnoPagePage() : "ALL");
                cell356.setCellStyle(styleAlignLeft);
                        sheet2.autoSizeColumn(5);

                // Row 6
                HSSFRow row36 = sheet2.createRow(5);
                HSSFCell cell361 = row36.createCell(0);
                cell361.setCellValue("Sale Staff : ");
                cell361.setCellStyle(styleAlignRight);
                HSSFCell cell362 = row36.createCell(1);
                cell362.setCellValue(ticketAgent.getSalebyNamePage() != null && !"".equals(ticketAgent.getSalebyNamePage()) ? ticketAgent.getSalebyNamePage() : "ALL");
                cell362.setCellStyle(styleAlignLeft);
                HSSFCell cell363 = row36.createCell(2);
                cell363.setCellValue("Refund Comm Date : ");
                cell363.setCellStyle(styleAlignRight);
                HSSFCell cell364 = row36.createCell(3);
                cell364.setCellValue(ticketAgent.getComrefundfromdatePage() != null && !"".equals(ticketAgent.getComrefundfromdatePage()) ? ticketAgent.getComrefundfromdatePage() : "ALL");
                cell364.setCellStyle(styleAlignLeft);
                HSSFCell cell365 = row36.createCell(4);
                cell365.setCellValue("Ticket Comm Date : ");
                cell365.setCellStyle(styleAlignRight);
                HSSFCell cell366 = row36.createCell(5);
                cell366.setCellValue(ticketAgent.getTicketcomfromdatePage() != null && !"".equals(ticketAgent.getTicketcomfromdatePage()) ? ticketAgent.getTicketcomfromdatePage() : "ALL");
                cell366.setCellStyle(styleAlignLeft);

                // Row 7
                HSSFRow row37 = sheet2.createRow(6);
                HSSFCell cell371 = row37.createCell(0);
                cell371.setCellValue("Department : ");
                cell371.setCellStyle(styleAlignRight);
                HSSFCell cell372 = row37.createCell(1);
                cell372.setCellValue(ticketAgent.getDepartmentPage() != null && !"".equals(ticketAgent.getDepartmentPage()) ? ticketAgent.getDepartmentPage() : "ALL");
                cell372.setCellStyle(styleAlignLeft);
                HSSFCell cell373 = row37.createCell(2);
                cell373.setCellValue("Invoice Date : ");
                cell373.setCellStyle(styleAlignRight);
                HSSFCell cell374 = row37.createCell(3);
                cell374.setCellValue(ticketAgent.getInvoicefromdatePage() != null && !"".equals(ticketAgent.getInvoicefromdatePage()) ? ticketAgent.getInvoicefromdatePage() : "ALL");
                cell374.setCellStyle(styleAlignLeft);
                HSSFCell cell375 = row37.createCell(4);
                cell375.setCellValue("Print on : ");
                cell375.setCellStyle(styleAlignRight);
                HSSFCell cell376 = row37.createCell(5);
                cell376.setCellValue(ticketAgent.getPrintonPage() != null && !"".equals(ticketAgent.getPrintonPage()) ? ticketAgent.getPrintonPage() : "ALL");
                cell376.setCellStyle(styleAlignLeft);

                // Row 8
                HSSFRow row38 = sheet2.createRow(7);
                HSSFCell cell381 = row38.createCell(0);
                cell381.setCellValue("Term Pay : ");
                cell381.setCellStyle(styleAlignRight);
                HSSFCell cell382 = row38.createCell(1);
                cell382.setCellValue(ticketAgent.getTermPayPage() != null && !"".equals(ticketAgent.getTermPayPage()) ? ticketAgent.getTermPayPage() : "ALL");
                cell382.setCellStyle(styleAlignLeft);
                HSSFCell cell383 = row38.createCell(2);
                cell383.setCellValue("Page : ");
                cell383.setCellStyle(styleAlignRight);
                HSSFCell cell384 = row38.createCell(3);
                cell384.setCellValue("1 ");
                cell384.setCellStyle(styleAlignLeft);


                // Header Table
                HSSFRow row39 = sheet2.createRow(9);

                HSSFCell cell396 = row39.createCell(0);
                cell396.setCellValue("Agent");
                cell396.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(0);

                HSSFCell cell399 = row39.createCell(1);
                cell399.setCellValue("Pax");
                cell399.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(1);

                HSSFCell cell3104 = row39.createCell(2);
                cell3104.setCellValue("Amount Wendy");
                cell3104.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(2);

                HSSFCell cell3105 = row39.createCell(3);
                cell3105.setCellValue("Amount Outbound");
                cell3105.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(3);

                HSSFCell cell3106 = row39.createCell(4);
                cell3106.setCellValue("Amount Inbound");	
                cell3106.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(4);

                HSSFCell cell3107 = row39.createCell(5);
                cell3107.setCellValue("Sale");
                cell3107.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(5);

                HSSFCell cell3108 = row39.createCell(6);
                cell3108.setCellValue("Cost");
                cell3108.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(6);

                HSSFCell cell3109 = row39.createCell(7);
                cell3109.setCellValue("Over Comm");
                cell3109.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(7);

                HSSFCell cell3110 = row39.createCell(8);
                cell3110.setCellValue("Add Pay");
                cell3110.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(8);

                HSSFCell cell3111 = row39.createCell(9);
                cell3111.setCellValue("Drecrease Pay");
                cell3111.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(9);

                HSSFCell cell3116 = row39.createCell(10);
                cell3116.setCellValue("Profit");
                cell3116.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(10);
                
                HSSFCell cell3112 = row39.createCell(11);
                cell3112.setCellValue("Comm");
                cell3112.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(11);

                HSSFCell cell3113 = row39.createCell(12);
                cell3113.setCellValue("Little Comm");
                cell3113.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(12);

                HSSFCell cell3114 = row39.createCell(13);
                cell3114.setCellValue("Agent[Comm] Pay");
                cell3114.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(13);

                HSSFCell cell3115 = row39.createCell(14);
                cell3115.setCellValue("Agent[Comm] Receive");
                cell3115.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(14);
                
                HSSFCell cell3117 = row39.createCell(15);
                cell3117.setCellValue("Pay");
                cell3117.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(15);
                
                HSSFCell cell3118 = row39.createCell(16);
                cell3118.setCellValue("Net Comm Receive");
                cell3118.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(16);

            int count2 = 10 + listAgent.size();

            if(listAgent != null){
            for (int r = 10; r < count2; r++) {
                HSSFRow row = sheet2.createRow(r);
                HSSFCell cell1 = row.createCell(0);
                    cell1.setCellValue(listAgent.get(r-10).getAgentname());
                    cell1.setCellStyle(styleDetailTable);
                HSSFCell cell2 = row.createCell(1);
                    BigDecimal pax = new BigDecimal("".equals(listAgent.get(r-10).getPax()) ? "0" : listAgent.get(r-10).getPax());
                    cell2.setCellValue((pax != null) ? pax.doubleValue() : new BigDecimal("0").doubleValue());
                    cell2.setCellStyle(stylePaxNumber);
               HSSFCell cell13 = row.createCell(2);
                       BigDecimal amountwendy = new BigDecimal("".equals(listAgent.get(r-10).getAmountwendy()) ? "0" : listAgent.get(r-10).getAmountwendy());
                       cell13.setCellValue((amountwendy != null) ? amountwendy.doubleValue() : new BigDecimal("0").doubleValue());
                       cell13.setCellStyle(styleDetailTableNumber);
               HSSFCell cell26 = row.createCell(3);
                       BigDecimal amountout = new BigDecimal("".equals(listAgent.get(r-10).getAmountoutbound()) ? "0" : listAgent.get(r-10).getAmountoutbound());
                       cell26.setCellValue((amountout != null) ? amountout.doubleValue() : new BigDecimal("0").doubleValue());
                       cell26.setCellStyle(styleDetailTableNumber);        
               HSSFCell cell14 = row.createCell(4);
                       BigDecimal amountin = new BigDecimal("".equals(listAgent.get(r-10).getAmountinbound()) ? "0" : listAgent.get(r-10).getAmountinbound());
                       cell14.setCellValue((amountin != null) ? amountin.doubleValue() : new BigDecimal("0").doubleValue());
                       cell14.setCellStyle(styleDetailTableNumber);
               HSSFCell cell15 = row.createCell(5);
                       BigDecimal sale = new BigDecimal("".equals(listAgent.get(r-10).getSale()) ? "0" : listAgent.get(r-10).getSale());
                       cell15.setCellValue((sale != null) ? sale.doubleValue() : new BigDecimal("0").doubleValue());
                       cell15.setCellStyle(styleDetailTableNumber);
               HSSFCell cell16 = row.createCell(6);
                       BigDecimal cost  = new BigDecimal("".equals(listAgent.get(r-10).getCost()) ? "0" : listAgent.get(r-10).getCost());
                       cell16.setCellValue((cost != null) ? cost.doubleValue() : new BigDecimal("0").doubleValue());
                       cell16.setCellStyle(styleDetailTableNumber);
               HSSFCell cell17 = row.createCell(7);
                       BigDecimal over  = new BigDecimal("".equals(listAgent.get(r-10).getOver()) ? "0" : listAgent.get(r-10).getOver());
                       cell17.setCellValue((over != null) ? over.doubleValue() : new BigDecimal("0").doubleValue());
                       cell17.setCellStyle(styleDetailTableNumber);
               HSSFCell cell18 = row.createCell(8);
                       BigDecimal add  = new BigDecimal("".equals(listAgent.get(r-10).getAdd()) ? "0" : listAgent.get(r-10).getAdd());
                       cell18.setCellValue((add != null) ? add.doubleValue() : new BigDecimal("0").doubleValue());
                       cell18.setCellStyle(styleDetailTableNumber);
               HSSFCell cell19 = row.createCell(9);
                       BigDecimal dres  = new BigDecimal("".equals(listAgent.get(r-10).getDres()) ? "0" : listAgent.get(r-10).getDres());
                       cell19.setCellValue((dres != null) ? dres.doubleValue() : new BigDecimal("0").doubleValue());
                       cell19.setCellStyle(styleDetailTableNumber);
               HSSFCell cell20 = row.createCell(10);
                       BigDecimal profit  = new BigDecimal("".equals(listAgent.get(r-10).getProfit()) ? "0" : listAgent.get(r-10).getProfit());
                       cell20.setCellValue((profit != null) ? profit.doubleValue() : new BigDecimal("0").doubleValue());
                       cell20.setCellStyle(styleDetailTableNumber);
               HSSFCell cell2205 = row.createCell(11);
                       BigDecimal ticcom  = new BigDecimal("".equals(listAgent.get(r-10).getTiccomm()) ? "0" : listAgent.get(r-10).getTiccomm());
                       cell2205.setCellValue((ticcom != null) ? ticcom.doubleValue() : new BigDecimal("0").doubleValue());
                       cell2205.setCellStyle(styleDetailTableNumber);
               HSSFCell cell212 = row.createCell(12);
                       BigDecimal little  = new BigDecimal("".equals(listAgent.get(r-10).getLittle()) ? "0" : listAgent.get(r-10).getLittle());
                       cell212.setCellValue((little != null) ? little.doubleValue() : new BigDecimal("0").doubleValue());
                       cell212.setCellStyle(styleDetailTableNumber);
               HSSFCell cell22 = row.createCell(13);
                        BigDecimal agentcommpay = new BigDecimal("".equals(listAgent.get(r-10).getAgentcommpay()) ? "0" : listAgent.get(r-10).getAgentcommpay());
                        cell22.setCellValue((agentcommpay != null) ? agentcommpay.doubleValue() : new BigDecimal("0").doubleValue());
                        cell22.setCellStyle(styleDetailTableNumber);
                HSSFCell cell23 = row.createCell(14);
                        BigDecimal agentcommrec = new BigDecimal("".equals(listAgent.get(r-10).getAgentcommrec()) ? "0" : listAgent.get(r-10).getAgentcommrec());
                        cell23.setCellValue((agentcommrec != null) ? agentcommrec.doubleValue() : new BigDecimal("0").doubleValue());
                        cell23.setCellStyle(styleDetailTableNumber);        
               HSSFCell cell24 = row.createCell(15);
                        BigDecimal pay = new BigDecimal("".equals(listAgent.get(r-10).getPay()) ? "0" : listAgent.get(r-10).getPay());
                        cell24.setCellValue((pay != null) ? pay.doubleValue() : new BigDecimal("0").doubleValue());
                        cell24.setCellStyle(styleDetailTableNumber);
               HSSFCell cell25 = row.createCell(16);
                        BigDecimal comm = new BigDecimal("".equals(listAgent.get(r-10).getComm()) ? "0" : listAgent.get(r-10).getComm());
                        cell25.setCellValue((comm != null) ? comm.doubleValue() : new BigDecimal("0").doubleValue());
                        cell25.setCellStyle(styleDetailTableNumber);
     
                        
            for (int i = 0; i < 25; i++) {
                sheet2.autoSizeColumn(i);
            }
        }
      }
         
        for (int i = 0; i < 30; i++) {
            sheet.autoSizeColumn(i);
            sheet1.autoSizeColumn(i);
            sheet2.autoSizeColumn(i);
        }
        
        HSSFRow rowtotalAgent = sheet2.createRow(count2);
        String sumtotalpaxAgent = "SUM(B" + 11+":B"+(count2)+")";
        String sumtotalamountwenAgent = "SUM(C" + 11+":C"+(count2)+")";
        String sumtotalamountoutAgent = "SUM(D" + 11+":D"+(count2)+")";
        String sumtotalamountinAgent = "SUM(E" + 11+":E"+(count2)+")";
        String sumtotalsaleAgent = "SUM(F" + 11+":F"+(count2)+")";
        String sumtotalcostAgent = "SUM(G" + 11+":G"+(count2)+")";
        String sumtotaloverAgent = "SUM(H" + 11+":H"+(count2)+")";
        String sumtotaladdAgent = "SUM(I" + 11+":I"+(count2)+")";
        String sumtotaldresAgent = "SUM(J" + 11+":J"+(count2)+")";
        String sumtotalprofitAgent = "SUM(K" + 11+":K"+(count2)+")";
        String sumtotalticketcommAgent = "SUM(L" + 11+":L"+(count2)+")";
        String sumtotallittleAgent = "SUM(M" + 11+":M"+(count2)+")";
        String sumtotalagentcommpayAgent = "SUM(N" + 11+":N"+(count2)+")";
        String sumtotalagentcommrecAgent = "SUM(O" + 11+":O"+(count2)+")";
        String sumtotalpayAgent = "SUM(P" + 11+":P"+(count2)+")";
        String sumtotalcommAgent = "SUM(Q" + 11+":Q"+(count2)+")";
//            sheetIncSum.addMergedRegion(CellRangeAddress.valueOf("A"+(count + x + 2)+":B"+(count + x + 2)));
//            sheetIncSum.addMergedRegion(CellRangeAddress.valueOf("D"+(count + x + 2)+":E"+(count + x + 2)));
        HSSFCell cellTotalSumAgent0 = rowtotalAgent.createCell(0);
            cellTotalSumAgent0.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumAgent1 = rowtotalAgent.createCell(1);
            cellTotalSumAgent1.setCellFormula(sumtotalpaxAgent);
            cellTotalSumAgent1.setCellStyle(stylePaxNumberTotal);
            HSSFCell cellTotalSumAgent2 = rowtotalAgent.createCell(2);
            cellTotalSumAgent2.setCellFormula(sumtotalamountwenAgent);
            cellTotalSumAgent2.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumAgent3 = rowtotalAgent.createCell(3);
            cellTotalSumAgent3.setCellFormula(sumtotalamountoutAgent);
            cellTotalSumAgent3.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumAgent4 = rowtotalAgent.createCell(4);
            cellTotalSumAgent4.setCellFormula(sumtotalamountinAgent);
            cellTotalSumAgent4.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumAgent5 = rowtotalAgent.createCell(5);
            cellTotalSumAgent5.setCellFormula(sumtotalsaleAgent);
            cellTotalSumAgent5.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumAgent6 = rowtotalAgent.createCell(6);
            cellTotalSumAgent6.setCellFormula(sumtotalcostAgent);
            cellTotalSumAgent6.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumAgent7 = rowtotalAgent.createCell(7);
            cellTotalSumAgent7.setCellFormula(sumtotaloverAgent);
            cellTotalSumAgent7.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumAgent8 = rowtotalAgent.createCell(8);
            cellTotalSumAgent8.setCellFormula(sumtotaladdAgent);
            cellTotalSumAgent8.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumAgent9 = rowtotalAgent.createCell(9);
            cellTotalSumAgent9.setCellFormula(sumtotaldresAgent);
            cellTotalSumAgent9.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumAgent10 = rowtotalAgent.createCell(10);
            cellTotalSumAgent10.setCellFormula(sumtotalprofitAgent);
            cellTotalSumAgent10.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumAgent11 = rowtotalAgent.createCell(11);
            cellTotalSumAgent11.setCellFormula(sumtotalticketcommAgent);
            cellTotalSumAgent11.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumAgent12 = rowtotalAgent.createCell(12);
            cellTotalSumAgent12.setCellFormula(sumtotallittleAgent);
            cellTotalSumAgent12.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumAgent13 = rowtotalAgent.createCell(13);
            cellTotalSumAgent13.setCellFormula(sumtotalagentcommpayAgent);
            cellTotalSumAgent13.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumAgent14 = rowtotalAgent.createCell(14);
            cellTotalSumAgent14.setCellFormula(sumtotalagentcommrecAgent);
            cellTotalSumAgent14.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumAgent15 = rowtotalAgent.createCell(15);
            cellTotalSumAgent15.setCellFormula(sumtotalpayAgent);
            cellTotalSumAgent15.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSumAgent16 = rowtotalAgent.createCell(16);
            cellTotalSumAgent16.setCellFormula(sumtotalcommAgent);
            cellTotalSumAgent16.setCellStyle(styleTotalTableNumber);

            
            
//        HSSFRow rowLLL = sheet2.createRow(count2);
//         rowLLL.createCell(0).setCellStyle(styleBorderTop);
//         rowLLL.createCell(1).setCellStyle(styleBorderTop);
//         rowLLL.createCell(2).setCellStyle(styleBorderTop);
//         rowLLL.createCell(3).setCellStyle(styleBorderTop);
//         rowLLL.createCell(4).setCellStyle(styleBorderTop);
//         rowLLL.createCell(5).setCellStyle(styleBorderTop);
//         rowLLL.createCell(6).setCellStyle(styleBorderTop);
//         rowLLL.createCell(7).setCellStyle(styleBorderTop);
//         rowLLL.createCell(8).setCellStyle(styleBorderTop);
//         rowLLL.createCell(9).setCellStyle(styleBorderTop);
//         rowLLL.createCell(10).setCellStyle(styleBorderTop);
//         rowLLL.createCell(11).setCellStyle(styleBorderTop);
//         rowLLL.createCell(12).setCellStyle(styleBorderTop);
//         rowLLL.createCell(13).setCellStyle(styleBorderTop);
//	 rowLLL.createCell(14).setCellStyle(styleBorderTop);    
//         rowLLL.createCell(15).setCellStyle(styleBorderTop);  
//         rowLLL.createCell(16).setCellStyle(styleBorderTop); 
         
        sheet.setColumnWidth(5, 256*40);
        sheet.setColumnWidth(30, 256*12);
        sheet1.setColumnWidth(13, 256*12);
        sheet2.setColumnWidth(0, 256*40);
    }
    
}
