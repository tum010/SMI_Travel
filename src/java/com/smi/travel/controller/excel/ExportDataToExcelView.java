/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller.excel;

import com.smi.travel.datalayer.report.model.BillAirAgent;
import com.smi.travel.datalayer.report.model.TicketFareReport;
import com.smi.travel.datalayer.report.model.TicketFareSummaryByAgentStaff;
import com.smi.travel.datalayer.view.entity.APNirvana;
import com.smi.travel.datalayer.view.entity.ARNirvana;
import com.smi.travel.datalayer.view.entity.BillAirAgentRefund;
import com.smi.travel.datalayer.view.entity.CollectionNirvana;
import com.smi.travel.datalayer.view.entity.ListBillAirAgent;
import com.smi.travel.datalayer.view.entity.ListSummaryTicketAdjustCostAndIncome;
import com.smi.travel.datalayer.view.entity.ListTicketCommissionReceive;
import com.smi.travel.datalayer.view.entity.ListTicketSummaryCommission;
import com.smi.travel.datalayer.view.entity.RefundTicketView;
import com.smi.travel.datalayer.view.entity.SummaryAirline;
import com.smi.travel.datalayer.view.entity.SummaryAirlinePaxView;
import com.smi.travel.datalayer.view.entity.SummaryTicketAdjustCostAndIncome;
import com.smi.travel.datalayer.view.entity.SummaryTicketCostAndIncomeView;
import com.smi.travel.datalayer.view.entity.TicketCommissionReceive;
import com.smi.travel.datalayer.view.entity.TicketProfitLoss;
import com.smi.travel.datalayer.view.entity.TicketSummaryAirlineView;
import com.smi.travel.datalayer.view.entity.TicketSummaryCommissionView;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.taglibs.standard.tag.common.core.Util;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/**
 *
 * @author Jittima
 */
public class ExportDataToExcelView extends AbstractExcelView {
    private static final String TicketFareReport = "TicketFareAirlineReport";
    private static final String TicketFareInvoicReport = "TicketFareInvoicReport";
    private static final String TicketFareAgentReport = "TicketFareAgentReport";
    private static final String TicketFareSummaryByStaff = "TicketFareSummaryByStaff";
    private static final String TicketFareSummaryByAgent = "TicketFareSummaryByAgent";
    private static final String BillAirAgent = "BillAirAgent";
    private static final String ChangeARReport = "ChangeARReport";
    private static final String BillAirAgentSummary = "BillAirAgentSummary";
    private static final String CollectionReport = "CollectionReport";
    private static final String ApReport = "ApReport";
    private static final String SummaryAirline = "SummaryAirline";
    private static final String TicketFareSummaryAirline = "TicketFareSummaryAirline";
    private static final String SummaryTicketAdjustCostAndIncome = "SummaryTicketAdjustCostAndIncome";
    private static final String SummaryTicketCommissionReceive = "SummaryTicketCommissionReceive";
    private static final String SummaryTicketCostAndIncome = "SummaryTicketCostAndIncome";
    private static final String RefundTicketDetail = "RefundTicketDetail";
    private static final String SummaryAirlinePax = "SummaryAirlinePax";
    private static final String TicketProfitLoss = "TicketProfitLoss"; 
    private static final String TicketSummaryCommission = "TicketSummaryCommission"; 
    
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
        }else if(name.equalsIgnoreCase(TicketFareInvoicReport)){
            System.out.println("gen report invoice");
            genTicketFareInvoiceReport(workbook, (List) model.get(name));
        }else if(name.equalsIgnoreCase(TicketFareAgentReport)){
            System.out.println("gen report TicketFareAgentReport");
            genTicketFareAgentReport(workbook, (List) model.get(name));
        }else if(name.equalsIgnoreCase(TicketFareSummaryByStaff)){
            System.out.println("gen report TicketFareSummaryByStaff");
            genTicketFareSummaryByStaff(workbook, (List) model.get(name));
        }else if(name.equalsIgnoreCase(TicketFareSummaryByAgent)){
            System.out.println("gen report TicketFareSummaryByAgent");
            genTicketFareSummaryByAgent(workbook, (List) model.get(name));
        }else if(name.equalsIgnoreCase(BillAirAgent)){
            System.out.println("gen report BillAirAgent");
            getBillAirAgentReportSummary(workbook, (List) model.get(name));
        }else if(name.equalsIgnoreCase(ChangeARReport)){
            System.out.println("gen report ChangeARReport");
            getChangeARReport(workbook, (List) model.get(name));
        }else if(name.equalsIgnoreCase(BillAirAgentSummary)){
            System.out.println("gen report BillAirAgentSummary");
            getBillAirAgentReportSummary(workbook, (List) model.get(name));
        }else if(name.equalsIgnoreCase(CollectionReport)){
            System.out.println("gen report CollectionReport");
            genCollectionReport(workbook, (List) model.get(name));
        }else if(name.equalsIgnoreCase(ApReport)){
            System.out.println("gen report ApReport");
            getApReport(workbook, (List) model.get(name));
        }else if(name.equalsIgnoreCase(SummaryAirline)){
            System.out.println("gen report SummaryAirline");
            getSummaryAirline(workbook, (List) model.get(name));
        }else if(name.equalsIgnoreCase(TicketFareSummaryAirline)){
            System.out.println("gen report TicketFareSummaryAirline");
            genTicketFareSummaryAirlineReport(workbook, (List) model.get(name));
        }else if(name.equalsIgnoreCase(SummaryTicketCostAndIncome)){
            System.out.println("gen report SummaryTicketCostAndIncome");
            getSummaryTicketCostAndIncome(workbook, (List) model.get(name));
        }else if(name.equalsIgnoreCase(SummaryTicketAdjustCostAndIncome)){
            System.out.println("gen report SummaryTicketAdjustCostAndIncome");
            getSummaryTicketAdjustCostAndIncome(workbook, (List) model.get(name));
        }else if(name.equalsIgnoreCase(SummaryTicketCommissionReceive)){
            System.out.println("gen report SummaryTicketCommissionReceive");
            getTicketCommissionReceive(workbook, (List) model.get(name));
        }else if(name.equalsIgnoreCase(RefundTicketDetail)){
            System.out.println("gen report RefundTicketDetail");
            getRefundTicketDetail(workbook, (List) model.get(name));
        }else if(name.equalsIgnoreCase(SummaryAirlinePax)){
            System.out.println("gen report SummaryAirlinePax");
            genSummaryAirlinePaxReport(workbook, (List) model.get(name));
        }else if(name.equalsIgnoreCase(TicketProfitLoss)){
            System.out.println("gen report TicketProfitLoss");
            genTicketProfitLossReport(workbook, (List) model.get(name));
        }else if(name.equalsIgnoreCase(TicketSummaryCommission)){
            System.out.println("gen report TicketSummaryCommission");
            getTicketSummaryCommission(workbook, (List) model.get(name));
        }

    }

    private void genTicketProfitLossReport(HSSFWorkbook wb, List TicketProfitLoss) {
        String sheetName = "Sheet1";// name of sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        
        TicketProfitLoss dataheader = new TicketProfitLoss();
        
        HSSFDataFormat currency = wb.createDataFormat();
        // Set align Text
        HSSFCellStyle styleC21 = wb.createCellStyle();
        styleC21.setAlignment(styleC21.ALIGN_RIGHT);
        HSSFCellStyle styleC22 = wb.createCellStyle();
        styleC22.setAlignment(styleC22.ALIGN_LEFT); 
        
        if(TicketProfitLoss != null){
            dataheader = (TicketProfitLoss) TicketProfitLoss.get(0);
        }
        
        // set Header Report (Row 1)
        HSSFCellStyle styleC1 = wb.createCellStyle();
        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cellStart = row1.createCell(0);
        cellStart.setCellValue("Ticket Profit Loss");
        styleC1.setFont(getHeaderFont(wb.createFont()));
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
        styleC3Center.setFont(getHeaderTable(wb.createFont()));
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
        for(int i=0;i<TicketProfitLoss.size();i++){
             TicketProfitLoss data = (TicketProfitLoss)TicketProfitLoss.get(i);
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
    
    private void genTicketFareAgentReport(HSSFWorkbook wb, List TicketAgent) {
        String sheetName = "Sheet1";// name of sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        
        TicketFareReport dataheader = new TicketFareReport();
        
        HSSFDataFormat currency = wb.createDataFormat();
        // Set align Text
        HSSFCellStyle styleC21 = wb.createCellStyle();
        styleC21.setAlignment(styleC21.ALIGN_RIGHT);
        HSSFCellStyle styleC22 = wb.createCellStyle();
        styleC22.setAlignment(styleC22.ALIGN_LEFT);       
        
        if(TicketAgent != null){
            dataheader = (TicketFareReport)TicketAgent.get(0);
        }

        // set Header Report (Row 1)
        HSSFCellStyle styleC1 = wb.createCellStyle();
        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cellStart = row1.createCell(0);
        cellStart.setCellValue("List Ticket Payment Agent");
        styleC1.setFont(getHeaderFont(wb.createFont()));
        cellStart.setCellStyle(styleC1);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:F1"));

        // Row 2
        HSSFRow row2 = sheet.createRow(1);
        HSSFCell cell21 = row2.createCell(0);
        cell21.setCellValue("Print By : ");
        cell21.setCellStyle(styleC21);
        HSSFCell cell22 = row2.createCell(1);
        cell22.setCellValue(dataheader.getPrintby());
        cell22.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B2:D2"));
        HSSFCell cell23 = row2.createCell(4);
        cell23.setCellValue("Air Line : ");
        cell23.setCellStyle(styleC21);
        HSSFCell cell24 = row2.createCell(5);
        cell24.setCellValue(dataheader.getAirline());
        cell24.setCellStyle(styleC22);
        
        // Row 3
        HSSFRow row3 = sheet.createRow(2);
        HSSFCell cell31 = row3.createCell(0);
        cell31.setCellValue("Department : ");
        cell31.setCellStyle(styleC21);
        HSSFCell cell32 = row3.createCell(1);
        cell32.setCellValue(dataheader.getHeaddepartment());
        cell32.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B3:D3"));
        HSSFCell cell33 = row3.createCell(4);
        cell33.setCellValue("Ticket Type : ");
        cell33.setCellStyle(styleC21);
        HSSFCell cell34 = row3.createCell(5);
        cell34.setCellValue(dataheader.getTickettype());
        cell34.setCellStyle(styleC22);

        // Row 4
        HSSFRow row4 = sheet.createRow(3);
        HSSFCell cell41 = row4.createCell(0);
        cell41.setCellValue("Term Pay : ");
        cell41.setCellStyle(styleC21);
        HSSFCell cell42 = row4.createCell(1);
        cell42.setCellValue(dataheader.getHeadtermpay());
        cell42.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B4:D4"));
        HSSFCell cell43 = row4.createCell(4);
        cell43.setCellValue("Ticket Buy : ");
        cell43.setCellStyle(styleC21);
        HSSFCell cell44 = row4.createCell(5);
        cell44.setCellValue(dataheader.getTicketbuy());
        cell44.setCellStyle(styleC22);

        // Row 5
        HSSFRow row5 = sheet.createRow(4);
        HSSFCell cell51 = row5.createCell(0);
        cell51.setCellValue("Sale Staff : ");
        cell51.setCellStyle(styleC21);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A5:E5"));
        HSSFCell cell52 = row5.createCell(5);
        cell52.setCellValue(dataheader.getHeadstaff());
        cell52.setCellStyle(styleC22);
        
         // Header Table
        HSSFCellStyle styleC3Center = wb.createCellStyle();
        styleC3Center.setFont(getHeaderTable(wb.createFont()));
        styleC3Center.setAlignment(styleC3Center.ALIGN_CENTER);
        styleC3Center.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC3Center.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC3Center.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC3Center.setBorderTop(HSSFCellStyle.BORDER_THIN);
        HSSFRow row6 = sheet.createRow(8);
        HSSFCell cell61 = row6.createCell(0);
        cell61.setCellValue("Invoice No.");
        cell61.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(0);
        HSSFCell cell62 = row6.createCell(1);
        cell62.setCellValue("Issue Date");
        cell62.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(1);
        HSSFCell cell63 = row6.createCell(2);
        cell63.setCellValue("Agent");
        cell63.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(2);
        HSSFCell cell631 = row6.createCell(3);
        cell631.setCellValue("Air");
        cell631.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(3);
        HSSFCell cell632 = row6.createCell(4);
        cell632.setCellValue("Doc No");
        cell632.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(4);
        HSSFCell cell633 = row6.createCell(5);
        cell633.setCellValue("Ref No");
        cell633.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(5);
        cell63.setCellStyle(styleC3Center);
        HSSFCell cell65 = row6.createCell(6);
        cell65.setCellValue("Department");
        cell65.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(6);
        HSSFCell cell66 = row6.createCell(7);
        cell66.setCellValue("Staff");
        cell66.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(7);
        HSSFCell cell67 = row6.createCell(8);
        cell67.setCellValue("Term Pay");
        cell67.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(8);
        
        HSSFCellStyle styleC3Right = wb.createCellStyle();
        styleC3Right.setFont(getHeaderTable(wb.createFont()));
        styleC3Right.setAlignment(styleC3Right.ALIGN_RIGHT);
        styleC3Right.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC3Right.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC3Right.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC3Right.setBorderTop(HSSFCellStyle.BORDER_THIN);
        HSSFCell cell68 = row6.createCell(9);
        cell68.setCellValue("Inv. Amount");
        cell68.setCellStyle(styleC3Right);
        sheet.autoSizeColumn(9);
        HSSFCell cell661 = row6.createCell(10);
        cell661.setCellValue("Cost");
        cell661.setCellStyle(styleC3Right);
        sheet.autoSizeColumn(10);
        HSSFCell cell69 = row6.createCell(11);
        cell69.setCellValue("Ticket Comm");
        cell69.setCellStyle(styleC3Right);
        sheet.autoSizeColumn(11);
        HSSFCell cell70 = row6.createCell(12);
        cell70.setCellValue("Sale Price");
        cell70.setCellStyle(styleC3Right);
        sheet.autoSizeColumn(12);
        HSSFCell cell71 = row6.createCell(13);
        cell71.setCellValue("Agent Comm");
        cell71.setCellStyle(styleC3Right);
        sheet.autoSizeColumn(13);
        HSSFCell cell72 = row6.createCell(14);
        cell72.setCellValue("Profit");
        cell72.setCellStyle(styleC3Right);
        sheet.autoSizeColumn(14);
        
        //Detail of Table
        int count = 9 ;
        HSSFCellStyle styleC23 = wb.createCellStyle();
        styleC23.setAlignment(styleC22.ALIGN_CENTER);
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
        for(int i=0;i<TicketAgent.size();i++){
             TicketFareReport data = (TicketFareReport)TicketAgent.get(i);
             HSSFRow row = sheet.createRow(count + i);
             HSSFCell cell0 = row.createCell(0);
                cell0.setCellValue(data.getInvno());
                cell0.setCellStyle(styleC24);
             HSSFCell cell1 = row.createCell(1);
                cell1.setCellValue(data.getIssuedate());
                cell1.setCellStyle(styleC23);
             HSSFCell cell2 = row.createCell(2);
                cell2.setCellValue(data.getAgent());
                cell2.setCellStyle(styleC24);
             HSSFCell cell3 = row.createCell(3);
                cell3.setCellValue(data.getAir());
                cell3.setCellStyle(styleC23);
             HSSFCell cell14 = row.createCell(4);
                cell14.setCellValue(data.getDocno());
                cell14.setCellStyle(styleC24);
             HSSFCell cell15 = row.createCell(5);
                cell15.setCellValue(data.getRefno());
                cell15.setCellStyle(styleC24);
             HSSFCell cell4 = row.createCell(6);
                cell4.setCellValue(data.getDepartment());
                cell4.setCellStyle(styleC24);
             HSSFCell cell5 = row.createCell(7);
                cell5.setCellValue(data.getStaff());
                cell5.setCellStyle(styleC24);
             HSSFCell cell6 = row.createCell(8);
                cell6.setCellValue(data.getTermpay());
                cell6.setCellStyle(styleC24);
             HSSFCell cell7 = row.createCell(9);
                cell7.setCellValue(!"".equalsIgnoreCase(data.getInvamount()) ? new BigDecimal(data.getInvamount()).doubleValue() : 0);
                cell7.setCellStyle(styleC25);
             HSSFCell cell16 = row.createCell(10);
                cell16.setCellValue(!"".equalsIgnoreCase(data.getCost()) ? new BigDecimal(data.getCost()).doubleValue() : 0);
                cell16.setCellStyle(styleC25);
             HSSFCell cell8 = row.createCell(11);
                cell8.setCellValue(!"".equalsIgnoreCase(data.getTicketcom()) ? new BigDecimal(data.getTicketcom()).doubleValue() : 0);
                cell8.setCellStyle(styleC25);   
             HSSFCell cell9 = row.createCell(12);
                cell9.setCellValue(!"".equalsIgnoreCase(data.getSaleprice()) ? new BigDecimal(data.getSaleprice()).doubleValue() : 0);
                cell9.setCellStyle(styleC25);      
            HSSFCell cell10 = row.createCell(13);
                cell10.setCellValue(!"".equalsIgnoreCase(data.getAgentcom()) ? new BigDecimal(data.getAgentcom()).doubleValue() : 0);
                cell10.setCellStyle(styleC25);      
            HSSFCell cell11 = row.createCell(14);
                cell11.setCellValue(!"".equalsIgnoreCase(data.getProfit()) ? new BigDecimal(data.getProfit()).doubleValue() : 0);
                cell11.setCellStyle(styleC25); 
                sheet.autoSizeColumn(14);
             if(i == (TicketAgent.size()-1)){
                row = sheet.createRow(count + i + 1);
                for(int k=0;k<10;k++){
                    HSSFCellStyle styleSum = wb.createCellStyle();
                    styleSum.setAlignment(styleC24.ALIGN_RIGHT);
                    styleSum.setBorderTop(HSSFCellStyle.BORDER_THIN);
                    styleSum.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                    HSSFCell cellSum = row.createCell(k);                   
                    if(k == 0){styleSum.setBorderLeft(HSSFCellStyle.BORDER_THIN);}
                    cellSum.setCellStyle(styleSum);
                }
                HSSFCellStyle styleSum = wb.createCellStyle();
                styleSum.setAlignment(styleSum.ALIGN_RIGHT);
                styleSum.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                styleSum.setBorderRight(HSSFCellStyle.BORDER_THIN);
                styleSum.setBorderTop(HSSFCellStyle.BORDER_THIN);
                styleSum.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                styleSum.setDataFormat(currency.getFormat("#,##0.00"));
                
                String sumInvAmount = "SUM(J" + 10+":J"+(count + i + 1)+")";
                String sumCost = "SUM(K" + 10+":K"+(count + i + 1)+")";
                String sumTicketComm = "SUM(L" + 10+":L"+(count + i + 1)+")";
                String sumSalePrice = "SUM(M" + 10+":M"+(count + i + 1)+")";
                String sumAgentComm = "SUM(N" + 10+":N"+(count + i + 1)+")";
                String sumProfit = "SUM(O" + 10+":O"+(count + i + 1)+")";
                
                HSSFCell cell6Sum = row.createCell(8);
                    cell6Sum.setCellValue("Total");
                    cell6Sum.setCellStyle(styleSum);
                HSSFCell cell7Sum = row.createCell(9);
                    cell7Sum.setCellFormula(sumInvAmount);
                    cell7Sum.setCellStyle(styleSum);
                HSSFCell cell71Sum = row.createCell(10);
                    cell71Sum.setCellFormula(sumCost);
                    cell71Sum.setCellStyle(styleSum);
                HSSFCell cell8Sum = row.createCell(11);
                    cell8Sum.setCellFormula(sumTicketComm);
                    cell8Sum.setCellStyle(styleSum);
                HSSFCell cell9Sum = row.createCell(12);
                    cell9Sum.setCellFormula(sumSalePrice);
                    cell9Sum.setCellStyle(styleSum);
                HSSFCell cell10Sum = row.createCell(13);
                    cell10Sum.setCellFormula(sumAgentComm);
                    cell10Sum.setCellStyle(styleSum);
                HSSFCell cell11Sum = row.createCell(14);
                    cell11Sum.setCellFormula(sumProfit);
                    cell11Sum.setCellStyle(styleSum); 
                    sheet.autoSizeColumn(14);
             }
             for(int j =0;j<15;j++){
                 sheet.autoSizeColumn(j);
             }
        }     
    }
    
    public void genTicketFareInvoiceReport(HSSFWorkbook wb, List TicketFare) {
        String sheetName = "Sheet1";// name of sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        TicketFareReport dataheader = new TicketFareReport();
        
        HSSFDataFormat currency = wb.createDataFormat();
        // Set align Text
        HSSFCellStyle styleC21 = wb.createCellStyle();
        styleC21.setAlignment(styleC21.ALIGN_RIGHT);
        styleC21.setDataFormat(currency.getFormat("#,##0.00"));
        HSSFCellStyle styleC22 = wb.createCellStyle();
        styleC22.setAlignment(styleC22.ALIGN_LEFT);       
                     
        if(!TicketFare.isEmpty()){
            dataheader = (TicketFareReport)TicketFare.get(0);
        
        // set Header Report (Row 1)
        HSSFCellStyle styleC1 = wb.createCellStyle();
        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cell1 = row1.createCell(0);
        cell1.setCellValue("List Ticket Fare Invoice");
        styleC1.setFont(getHeaderFont(wb.createFont()));
        cell1.setCellStyle(styleC1);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:F1"));
        
        // Row 2
        HSSFRow row2 = sheet.createRow(1);
        HSSFCell cell21 = row2.createCell(0);
        cell21.setCellValue("Print By : ");
        cell21.setCellStyle(styleC21);
        HSSFCell cell22 = row2.createCell(1);
        cell22.setCellValue(dataheader.getPrintby());
        cell22.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B2:D2"));
        HSSFCell cell23 = row2.createCell(4);
        cell23.setCellValue("Air Line : ");
        cell23.setCellStyle(styleC21);
        HSSFCell cell24 = row2.createCell(5);
        cell24.setCellValue(dataheader.getAirline());
        cell24.setCellStyle(styleC22);
        
        // Row 3
        HSSFRow row3 = sheet.createRow(2);
        HSSFCell cell31 = row3.createCell(0);
        cell31.setCellValue("Department : ");
        cell31.setCellStyle(styleC21);
        HSSFCell cell32 = row3.createCell(1);
        cell32.setCellValue(dataheader.getHeaddepartment());
        cell32.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B3:D3"));
        HSSFCell cell33 = row3.createCell(4);
        cell33.setCellValue("Ticket Type : ");
        cell33.setCellStyle(styleC21);
        HSSFCell cell34 = row3.createCell(5);
        cell34.setCellValue(dataheader.getTickettype());
        cell34.setCellStyle(styleC22);

        // Row 4
        HSSFRow row4 = sheet.createRow(3);
        HSSFCell cell41 = row4.createCell(0);
        cell41.setCellValue("Term Pay : ");
        cell41.setCellStyle(styleC21);
        HSSFCell cell42 = row4.createCell(1);
        cell42.setCellValue(dataheader.getHeadtermpay());
        cell42.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B4:D4"));
        HSSFCell cell43 = row4.createCell(4);
        cell43.setCellValue("Ticket Buy : ");
        cell43.setCellStyle(styleC21);
        HSSFCell cell44 = row4.createCell(5);
        cell44.setCellValue(dataheader.getTicketbuy());
        cell44.setCellStyle(styleC22);

        // Row 5
        HSSFRow row5 = sheet.createRow(4);
        HSSFCell cell51 = row5.createCell(0);
        cell51.setCellValue("Sale Staff : ");
        cell51.setCellStyle(styleC21);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A5:E5"));
        HSSFCell cell52 = row5.createCell(5);
        cell52.setCellValue(dataheader.getStaff());
        cell52.setCellStyle(styleC22);
        
        // Row 6
        HSSFRow row06 = sheet.createRow(5);
        HSSFCell cell061 = row06.createCell(0);
        cell061.setCellValue("Report of : ");
        cell061.setCellStyle(styleC21);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A6:D6"));
        HSSFCell cell062 = row06.createCell(4);
        if(!"".equalsIgnoreCase(dataheader.getFrom())){
            cell062.setCellValue(dataheader.getFrom());
            cell062.setCellStyle(styleC22);
        }
        HSSFCell cell063 = row06.createCell(5);
        if(!"".equalsIgnoreCase(dataheader.getTo())){
        cell063.setCellValue("to  " + dataheader.getTo());
        cell063.setCellStyle(styleC22);
        }
        
        // Row 7
        HSSFRow row07 = sheet.createRow(6);
        HSSFCell cell071 = row07.createCell(0);
        cell071.setCellValue("Print on : ");
        cell071.setCellStyle(styleC21);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A7:D7"));
        HSSFCell cell072 = row07.createCell(4);
        cell072.setCellValue(dataheader.getPrintondate());
        cell072.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("E7:F7"));
        
        }
        // Header Table
        HSSFCellStyle styleC3 = wb.createCellStyle();
        styleC3.setFont(getHeaderTable(wb.createFont()));
        styleC3.setAlignment(styleC3.ALIGN_CENTER);
        styleC3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC3.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC3.setBorderTop(HSSFCellStyle.BORDER_THIN);
        
        HSSFRow row6 = sheet.createRow(8);
        HSSFCell cell61 = row6.createCell(0);
        cell61.setCellValue("Inv. No.");
        cell61.setCellStyle(styleC3);
        sheet.autoSizeColumn(0);
        HSSFCell cell62 = row6.createCell(1);
        cell62.setCellValue("Inv. Date");
        cell62.setCellStyle(styleC3);
        sheet.autoSizeColumn(1);
        HSSFCell cell63 = row6.createCell(2);
        cell63.setCellValue("Department");
        sheet.autoSizeColumn(2);
        cell63.setCellStyle(styleC3);
        HSSFCell cell64 = row6.createCell(3);
        cell64.setCellValue("Staff");
        cell64.setCellStyle(styleC3);
        sheet.autoSizeColumn(3);
        HSSFCell cell65 = row6.createCell(4);
        cell65.setCellValue("Term Pay");
        cell65.setCellStyle(styleC3);
        sheet.autoSizeColumn(4);
        HSSFCell cell66 = row6.createCell(5);
        cell66.setCellValue("Passenger");
        cell66.setCellStyle(styleC3);
        sheet.autoSizeColumn(5);
        HSSFCell cell67 = row6.createCell(6);
        cell67.setCellValue("Air");
        cell67.setCellStyle(styleC3);
        sheet.autoSizeColumn(6);
        HSSFCell cell68 = row6.createCell(7);
        cell68.setCellValue("Document Number");
        cell68.setCellStyle(styleC3);
        sheet.autoSizeColumn(7);
        HSSFCell cell688 = row6.createCell(8);
        cell688.setCellValue("Ref No");
        cell688.setCellStyle(styleC3);
        sheet.autoSizeColumn(8);
        HSSFCell cell69 = row6.createCell(9);
        cell69.setCellValue("Issue Date");
        cell69.setCellStyle(styleC3);
        sheet.autoSizeColumn(9);
        HSSFCell cell70 = row6.createCell(10);
        cell70.setCellValue("Net Sales");
        cell70.setCellStyle(styleC3);
        sheet.autoSizeColumn(10);
        HSSFCell cell71 = row6.createCell(11);
        cell71.setCellValue("Tax");
        cell71.setCellStyle(styleC3);
        sheet.autoSizeColumn(11);
        HSSFCell cell72 = row6.createCell(12);
        cell72.setCellValue("Insurance");
        cell72.setCellStyle(styleC3);
        sheet.autoSizeColumn(12);
        HSSFCell cell73 = row6.createCell(13);
        cell73.setCellValue("Actual Commission");
        cell73.setCellStyle(styleC3);
        sheet.autoSizeColumn(13);
        HSSFCell cell733 = row6.createCell(14);
        cell733.setCellValue("Wht");
        cell733.setCellStyle(styleC3);
        sheet.autoSizeColumn(14);
        HSSFCell cell74 = row6.createCell(15);
        cell74.setCellValue("Inv. Amount");
        cell74.setCellStyle(styleC3);
        sheet.autoSizeColumn(15);
        
        //Detail of Table
        int count = 9 ;
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
        for(int i=0;i<TicketFare.size();i++){
            TicketFareReport data = (TicketFareReport)TicketFare.get(i);
            HSSFRow row = sheet.createRow(count + i);
            
            HSSFCell celldata0 = row.createCell(0);
            celldata0.setCellValue(data.getInvno());
            celldata0.setCellStyle(styleC24);
            
            HSSFCell celldata1 = row.createCell(1);
            celldata1.setCellValue(data.getInvdate());
            celldata1.setCellStyle(styleC24);
            
            HSSFCell celldata2 = row.createCell(2);
            celldata2.setCellValue(data.getDepartment());
            celldata2.setCellStyle(styleC24);
            
            HSSFCell celldata3 = row.createCell(3);
            celldata3.setCellValue(data.getStaff());
            celldata3.setCellStyle(styleC24);
            
            HSSFCell celldata4 = row.createCell(4);
            celldata4.setCellValue(data.getTermpay());
            celldata4.setCellStyle(styleC24);
            
            HSSFCell celldata5 = row.createCell(5);
            celldata5.setCellValue(data.getPassenger());
            celldata5.setCellStyle(styleC24);
            
            HSSFCell celldata6 = row.createCell(6);
            celldata6.setCellValue(data.getAir());
            celldata6.setCellStyle(styleC24);
            
            HSSFCell celldata7 = row.createCell(7);
            celldata7.setCellValue(data.getDocno());
            celldata7.setCellStyle(styleC24);
            
            HSSFCell celldata14 = row.createCell(8);
            celldata14.setCellValue(data.getRefno());
            celldata14.setCellStyle(styleC24);
  
            //set data 
            HSSFCell celldata8 = row.createCell(9);
            celldata8.setCellValue(data.getIssuedate());
            celldata8.setCellStyle(styleC23);
            
            HSSFCell celldata9 = row.createCell(10);
            celldata9.setCellValue("".equalsIgnoreCase(String.valueOf(data.getNetsale())) ? 0 : new BigDecimal(data.getNetsale()).doubleValue());
            celldata9.setCellStyle(styleC25);
            
            HSSFCell celldata10 = row.createCell(11);
            celldata10.setCellValue("".equalsIgnoreCase(String.valueOf(data.getTax())) ? 0 : new BigDecimal(data.getTax()).doubleValue());
            celldata10.setCellStyle(styleC25);
            
            HSSFCell celldata11 = row.createCell(12);
            celldata11.setCellValue("".equalsIgnoreCase(String.valueOf(data.getIns())) ? 0 : new BigDecimal(data.getIns()).doubleValue());
            celldata11.setCellStyle(styleC25);
            
            HSSFCell celldata12 = row.createCell(13);
            celldata12.setCellValue("".equalsIgnoreCase(String.valueOf(data.getActcom())) ? 0 : new BigDecimal(data.getActcom()).doubleValue());
            celldata12.setCellStyle(styleC25);
            
            HSSFCell celldata15 = row.createCell(14);
            celldata15.setCellValue("".equalsIgnoreCase(String.valueOf(data.getWht())) ? 0 : new BigDecimal(data.getWht()).doubleValue());
            celldata15.setCellStyle(styleC25);
            
            HSSFCell celldata13 = row.createCell(15);
            celldata13.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInvamount())) ? 0 : new BigDecimal(data.getInvamount()).doubleValue());
            celldata13.setCellStyle(styleC25);
             
            if(i == (TicketFare.size()-1)){
                row = sheet.createRow(count + i + 1);
                for(int k=0;k<10;k++){
                    HSSFCellStyle styleSum = wb.createCellStyle();
                    styleSum.setAlignment(styleC24.ALIGN_RIGHT);
                    styleSum.setBorderTop(HSSFCellStyle.BORDER_THIN);
                    styleSum.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                    HSSFCell cellSum = row.createCell(k);                   
                    if(k == 0){styleSum.setBorderLeft(HSSFCellStyle.BORDER_THIN);}
                    cellSum.setCellStyle(styleSum);
                }
                HSSFCellStyle styleSum = wb.createCellStyle();
                styleSum.setAlignment(styleSum.ALIGN_RIGHT);
                styleSum.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                styleSum.setBorderRight(HSSFCellStyle.BORDER_THIN);
                styleSum.setBorderTop(HSSFCellStyle.BORDER_THIN);
                styleSum.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                styleSum.setDataFormat(currency.getFormat("#,##0.00"));
                
                String netsalesTotal = "SUM(K" + 10+":K"+(count + i + 1)+")";
                String taxTotal = "SUM(L" + 10+":L"+(count + i + 1)+")";
                String insTotal = "SUM(M" + 10+":M"+(count + i + 1)+")";
                String actcommTotal = "SUM(N" + 10+":N"+(count + i + 1)+")";
                String sumWht = "SUM(O" + 10+":O"+(count + i + 1)+")";
                String invamountTotal = "SUM(P" + 10+":P"+(count + i + 1)+")";

                HSSFCell cellTotal00 = row.createCell(9);
                cellTotal00.setCellValue("Total");
                cellTotal00.setCellStyle(styleSum);                
                HSSFCell cellTotal01 = row.createCell(10);
                cellTotal01.setCellFormula(netsalesTotal);
                cellTotal01.setCellStyle(styleSum);
                HSSFCell cellTotal02 = row.createCell(11);
                cellTotal02.setCellFormula(taxTotal);
                cellTotal02.setCellStyle(styleSum);
                HSSFCell cellTotal03 = row.createCell(12);
                cellTotal03.setCellFormula(insTotal);
                cellTotal03.setCellStyle(styleSum);
                HSSFCell cellTotal04 = row.createCell(13);
                cellTotal04.setCellFormula(actcommTotal);
                cellTotal04.setCellStyle(styleSum);
                HSSFCell cellTotal06 = row.createCell(14);
                cellTotal06.setCellFormula(sumWht);
                cellTotal06.setCellStyle(styleSum);
                HSSFCell cellTotal05 = row.createCell(15);
                cellTotal05.setCellFormula(invamountTotal);
                cellTotal05.setCellStyle(styleSum);
             }
             
             for(int j =0;j<15;j++){
                 sheet.autoSizeColumn(j);
             }
        }
    }
    
    private void variableTotal(int start, int end, int row1, int row2,HSSFSheet sheet,HSSFCellStyle styleNumber,HSSFCellStyle styleNumberBorderRight){
        // total
        String sumSaleprice = "SUM(F" + start+":F"+end+")";
        String sumNet = "SUM(G" + start+":G"+end+")";
        String sumService = "SUM(H" + start+":H"+end+")";
        String sumVat = "SUM(I" + start+":I"+end+")";
        String sumAmountAir = "SUM(J" + start+":J"+end+")";
        String sumCompay = "SUM(K" + start+":K"+end+")";
        String sumVatCompay = "SUM(L" + start+":L"+end+")";
        String sumReceive = "SUM(M" + start+":M"+end+")";

        HSSFRow row = sheet.createRow(row1);
        HSSFCell cell5 = row.createCell(5);
            cell5.setCellFormula(sumSaleprice);
            cell5.setCellStyle(styleNumber);     
        HSSFCell cell7 = row.createCell(7);
            cell7.setCellFormula(sumService);
            cell7.setCellStyle(styleNumber);
        HSSFCell cell9 = row.createCell(9);
            cell9.setCellFormula(sumAmountAir);
            cell9.setCellStyle(styleNumber);
        HSSFCell cell11 = row.createCell(11);
            cell11.setCellFormula(sumVatCompay);
            cell11.setCellStyle(styleNumber);
        row.createCell(12).setCellStyle(styleNumberBorderRight);
            
        HSSFRow row11 = sheet.createRow(row2);;
        HSSFCell cell6 = row11.createCell(6);
            cell6.setCellFormula(sumNet);
            cell6.setCellStyle(styleNumber);
        HSSFCell cell8 = row11.createCell(8);
            cell8.setCellFormula(sumVat);
            cell8.setCellStyle(styleNumber);
        HSSFCell cell10 = row11.createCell(10);
            cell10.setCellFormula(sumCompay);
            cell10.setCellStyle(styleNumber);
        HSSFCell cell12 = row11.createCell(12);
            cell12.setCellFormula(sumReceive);
            cell12.setCellStyle(styleNumberBorderRight);
    }
    
    private void createCell(HSSFRow row,List<BillAirAgent> listAgent,int num,HSSFCellStyle styleNumber,HSSFCellStyle styleDetail){
        HSSFCellStyle styleNum = styleNumber;
        HSSFCellStyle styleDe = styleDetail;
        HSSFCell cell1 = row.createCell(0);
            cell1.setCellValue(listAgent.get(num).getInvno());
            cell1.setCellStyle(styleDe);
        HSSFCell cell2 = row.createCell(1);
            cell2.setCellValue(listAgent.get(num).getInvdate());
            cell2.setCellStyle(styleDe);
        HSSFCell cell3 = row.createCell(2);
            cell3.setCellValue(listAgent.get(num).getCustomer());
            cell3.setCellStyle(styleDe);
        HSSFCell cell4 = row.createCell(3);
            cell4.setCellValue(listAgent.get(num).getTicketno());
            cell4.setCellStyle(styleDe);
        HSSFCell cell55 = row.createCell(4);
            cell55.setCellValue(listAgent.get(num).getRounting());
            cell55.setCellStyle(styleDe);
        HSSFCell cell5 = row.createCell(5);
            cell5.setCellValue(new BigDecimal(listAgent.get(num).getSaleprice()).longValue());
            cell5.setCellStyle(styleNum);
        HSSFCell cell6 = row.createCell(6);
            cell6.setCellValue(new BigDecimal(listAgent.get(num).getNet()).doubleValue());
            cell6.setCellStyle(styleNum);
        HSSFCell cell7 = row.createCell(7);
            cell7.setCellValue(new BigDecimal(listAgent.get(num).getService()).doubleValue());
            cell7.setCellStyle(styleNum);
        HSSFCell cell8 = row.createCell(8);
            cell8.setCellValue(new BigDecimal(listAgent.get(num).getServicevat()).doubleValue());
            cell8.setCellStyle(styleNum);
        HSSFCell cell9 = row.createCell(9);
            cell9.setCellValue(new BigDecimal(listAgent.get(num).getAmountair()).doubleValue());
            cell9.setCellStyle(styleNum);
        HSSFCell cell10 = row.createCell(10);
            cell10.setCellValue(new BigDecimal(listAgent.get(num).getCompay()).doubleValue());
            cell10.setCellStyle(styleNum);
        HSSFCell cell11 = row.createCell(11);
            cell11.setCellValue(new BigDecimal(listAgent.get(num).getCompayvat()).doubleValue());
            cell11.setCellStyle(styleNum);
        HSSFCell cell12 = row.createCell(12);
            cell12.setCellValue(new BigDecimal(listAgent.get(num).getReceive()).doubleValue());
            cell12.setCellStyle(styleNum);
    }
    
    public void genTicketFareAirlineReport(HSSFWorkbook wb, List TicketFare) {

        String sheetName = "Sheet1";// name of sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        HSSFDataFormat currency = wb.createDataFormat();
        
        TicketFareReport dataheader = new TicketFareReport();
        
        if((TicketFare != null)&&(TicketFare.size() != 0)){
            dataheader = (TicketFareReport)TicketFare.get(0);
        }

        // set Header Report (Row 1)
        HSSFCellStyle styleC1 = wb.createCellStyle();
        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cellStart = row1.createCell(0);
        cellStart.setCellValue("List Ticket Fare Airline");
        styleC1.setFont(getHeaderFont(wb.createFont()));
        cellStart.setCellStyle(styleC1);
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
        cell22.setCellValue(dataheader.getPrintby());
        cell22.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B2:D2"));
        HSSFCell cell23 = row2.createCell(4);
        cell23.setCellValue("Air Line : ");
        cell23.setCellStyle(styleC21);
        HSSFCell cell24 = row2.createCell(5);
        cell24.setCellValue(dataheader.getAirline());
        cell24.setCellStyle(styleC22);

        // Row 3
        HSSFRow row3 = sheet.createRow(2);
        HSSFCell cell31 = row3.createCell(0);
        cell31.setCellValue("Department : ");
        cell31.setCellStyle(styleC21);
        HSSFCell cell32 = row3.createCell(1);
        cell32.setCellValue(dataheader.getHeaddepartment());
        cell32.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B3:D3"));
        HSSFCell cell33 = row3.createCell(4);
        cell33.setCellValue("Ticket Buy : ");
        cell33.setCellStyle(styleC21);
        HSSFCell cell34 = row3.createCell(5);
        cell34.setCellValue(dataheader.getTickettype());
        cell34.setCellStyle(styleC22);

        // Row 4
        HSSFRow row4 = sheet.createRow(3);
        HSSFCell cell41 = row4.createCell(0);
        cell41.setCellValue("Term Pay : ");
        cell41.setCellStyle(styleC21);
        HSSFCell cell42 = row4.createCell(1);
        cell42.setCellValue(dataheader.getHeadtermpay());
        cell42.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B4:D4"));
        HSSFCell cell43 = row4.createCell(4);
        cell43.setCellValue("Ticket Type : ");
        cell43.setCellStyle(styleC21);
        HSSFCell cell44 = row4.createCell(5);
        cell44.setCellValue(dataheader.getTicketbuy());
        cell44.setCellStyle(styleC22);

        // Row 5
        HSSFRow row5 = sheet.createRow(4);
        HSSFCell cell51 = row5.createCell(0);
        cell51.setCellValue("Sale Staff : ");
        cell51.setCellStyle(styleC21);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A5:E5"));
        HSSFCell cell52 = row5.createCell(5);
        cell52.setCellValue(dataheader.getHeadstaff());
        cell52.setCellStyle(styleC22);

        // Header Table
        HSSFCellStyle styleC3Center = wb.createCellStyle();
        styleC3Center.setFont(getHeaderTable(wb.createFont()));
        styleC3Center.setAlignment(styleC3Center.ALIGN_CENTER);
        styleC3Center.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC3Center.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC3Center.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC3Center.setBorderTop(HSSFCellStyle.BORDER_THIN);
        HSSFRow row6 = sheet.createRow(8);
        HSSFCell cell61 = row6.createCell(0);
        cell61.setCellValue("Air");
        cell61.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(0);
        HSSFCell cell62 = row6.createCell(1);
        cell62.setCellValue("Document Number");
        cell62.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(1);
        HSSFCell cell622 = row6.createCell(2);
        cell622.setCellValue("Ref No");
        cell622.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(2);
        HSSFCell cell63 = row6.createCell(3);
        cell63.setCellValue("Issue Date");
        sheet.autoSizeColumn(3);
        cell63.setCellStyle(styleC3Center);
        HSSFCell cell64 = row6.createCell(4);
        cell64.setCellValue("Department");
        cell64.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(4);
        HSSFCell cell65 = row6.createCell(5);
        cell65.setCellValue("Staff");
        cell65.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(5);
        HSSFCell cell66 = row6.createCell(6);
        cell66.setCellValue("Term Pay");
        cell66.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(6);
        
        HSSFCellStyle styleC3Right = wb.createCellStyle();
        styleC3Right.setFont(getHeaderTable(wb.createFont()));
        styleC3Right.setAlignment(styleC3Right.ALIGN_RIGHT);
        styleC3Right.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC3Right.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC3Right.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC3Right.setBorderTop(HSSFCellStyle.BORDER_THIN);
        HSSFCell cell67 = row6.createCell(7);
        cell67.setCellValue("Tax");
        cell67.setCellStyle(styleC3Right);
        sheet.autoSizeColumn(7);
        HSSFCell cell68 = row6.createCell(8);
        cell68.setCellValue("Actual Commission");
        cell68.setCellStyle(styleC3Right);
        sheet.autoSizeColumn(8);
        HSSFCell cell69 = row6.createCell(9);
        cell69.setCellValue("Insurance");
        cell69.setCellStyle(styleC3Right);
        sheet.autoSizeColumn(9);
        HSSFCell cell70 = row6.createCell(10);
        cell70.setCellValue("Net Sales");
        cell70.setCellStyle(styleC3Right);
        sheet.autoSizeColumn(10);

        HSSFCell cell71 = row6.createCell(11);
        cell71.setCellValue("Vat");
        cell71.setCellStyle(styleC3Right);
        sheet.autoSizeColumn(11);
        
        HSSFCell cell711 = row6.createCell(12);
        cell711.setCellValue("Wht");
        cell711.setCellStyle(styleC3Right);
        sheet.autoSizeColumn(12);

        HSSFCell cell72 = row6.createCell(13);
        cell72.setCellValue("Invoice No.");
        cell72.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(13);

        HSSFCell cell73 = row6.createCell(14);
        cell73.setCellValue("Invoice Amount");
        cell73.setCellStyle(styleC3Right);
        sheet.autoSizeColumn(14);
        HSSFCell cell74 = row6.createCell(15);
        cell74.setCellValue("Balance Payable");
        cell74.setCellStyle(styleC3Right);
        sheet.autoSizeColumn(15);
        
        //Detail of Table
        int count = 9 ;
        HSSFCellStyle styleC23 = wb.createCellStyle();
        styleC23.setAlignment(styleC22.ALIGN_CENTER);
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
        for(int i=0;i<TicketFare.size();i++){
             TicketFareReport data = (TicketFareReport)TicketFare.get(i);
             HSSFRow row = sheet.createRow(count + i);
             HSSFCell cell0 = row.createCell(0);
                cell0.setCellValue(data.getAir());
                cell0.setCellStyle(styleC24);
             HSSFCell cell1 = row.createCell(1);
                cell1.setCellValue(data.getDocno());
                cell1.setCellStyle(styleC24);
             HSSFCell cell13 = row.createCell(2);
                cell13.setCellValue(data.getRefno());
                cell13.setCellStyle(styleC24);  
             HSSFCell cell2 = row.createCell(3);
                cell2.setCellValue(data.getIssuedate());
                cell2.setCellStyle(styleC23);   
             HSSFCell cell3= row.createCell(4);
                cell3.setCellValue(data.getDepartment());
                cell3.setCellStyle(styleC24);
             HSSFCell cell4 = row.createCell(5);
                cell4.setCellValue(data.getStaff());
                cell4.setCellStyle(styleC24);   
             HSSFCell cell5 = row.createCell(6);
                cell5.setCellValue(data.getTermpay());
                cell5.setCellStyle(styleC24);
             HSSFCell cell6 = row.createCell(7);
                cell6.setCellValue(!"".equalsIgnoreCase(data.getTax()) ? new BigDecimal(data.getTax()).doubleValue() : 0);
                cell6.setCellStyle(styleC25);
             HSSFCell cell7 = row.createCell(8);
                cell7.setCellValue(!"".equalsIgnoreCase(data.getTicketcom()) ? new BigDecimal(data.getTicketcom()).doubleValue() : 0);
                cell7.setCellStyle(styleC25);
             HSSFCell cell8 = row.createCell(9);
                cell8.setCellValue(!"".equalsIgnoreCase(data.getIns()) ? new BigDecimal(data.getIns()).doubleValue() : 0);
                cell8.setCellStyle(styleC25);
             HSSFCell cell9 = row.createCell(10);
                cell9.setCellValue(!"".equalsIgnoreCase(data.getNetsale()) ? new BigDecimal(data.getNetsale()).doubleValue() : 0);
                cell9.setCellStyle(styleC25);
             HSSFCell cell10 = row.createCell(11);
                cell10.setCellValue(!"".equalsIgnoreCase(data.getVat()) ? new BigDecimal(data.getVat()).doubleValue() : 0);
                cell10.setCellStyle(styleC25);
             HSSFCell cell14 = row.createCell(12);
                cell14.setCellValue(!"".equalsIgnoreCase(data.getWht()) ? new BigDecimal(data.getWht()).doubleValue() : 0);
                cell14.setCellStyle(styleC25);
             HSSFCell cell11 = row.createCell(13);
                cell11.setCellValue(data.getInvno());
                cell11.setCellStyle(styleC24);
             HSSFCell cell12 = row.createCell(14);
                cell12.setCellValue(!"".equalsIgnoreCase(data.getInvamount()) ? new BigDecimal(data.getInvamount()).doubleValue() : 0);
                cell12.setCellStyle(styleC25);
             HSSFCell cell15 = row.createCell(15);
                cell15.setCellValue(!"".equalsIgnoreCase(data.getBalance()) ? new BigDecimal(data.getBalance()).doubleValue() : 0);
                cell15.setCellStyle(styleC25);
             if(i == (TicketFare.size()-1)){
                 row = sheet.createRow(count + i + 1);
                 for(int k=0;k<8;k++){
                    HSSFCellStyle styleSum = wb.createCellStyle();
                    styleSum.setAlignment(styleC24.ALIGN_RIGHT);
                    styleSum.setBorderTop(HSSFCellStyle.BORDER_THIN);
                    styleSum.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                    HSSFCell cellSum = row.createCell(k);                   
                    if(k == 0){styleSum.setBorderLeft(HSSFCellStyle.BORDER_THIN);}
                    cellSum.setCellStyle(styleSum);
                }
                HSSFCellStyle styleSum = wb.createCellStyle();
                styleSum.setAlignment(styleSum.ALIGN_RIGHT);
                styleSum.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                styleSum.setBorderRight(HSSFCellStyle.BORDER_THIN);
                styleSum.setBorderTop(HSSFCellStyle.BORDER_THIN);
                styleSum.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                styleSum.setDataFormat(currency.getFormat("#,##0.00"));
                
                String sumTax = "SUM(H" + 10+":H"+(count + i + 1)+")";
                String sumActComm = "SUM(I" + 10+":I"+(count + i + 1)+")";
                String sumInsurance = "SUM(J" + 10+":J"+(count + i + 1)+")";
                String sumNetSales = "SUM(K" + 10+":K"+(count + i + 1)+")";
                String sumVat = "SUM(L" + 10+":L"+(count + i + 1)+")";
                String sumWht = "SUM(M" + 10+":M"+(count + i + 1)+")";
                String sumInvAmount = "SUM(O" + 10+":O"+(count + i + 1)+")";
                String sumBalance = "SUM(P" + 10+":P"+(count + i + 1)+")";
                
                HSSFCell cell5Sum = row.createCell(5);
                    cell5Sum.setCellValue("Total");
                    cell5Sum.setCellStyle(styleSum);
                HSSFCell cell111Sum = row.createCell(6);
                    cell111Sum.setCellStyle(styleSum); 
                HSSFCell cell6Sum = row.createCell(7);
                    cell6Sum.setCellFormula(sumTax);
                    cell6Sum.setCellStyle(styleSum);
                HSSFCell cell7Sum = row.createCell(8);
                    cell7Sum.setCellFormula(sumActComm);
                    cell7Sum.setCellStyle(styleSum);
                HSSFCell cell8Sum = row.createCell(9);
                    cell8Sum.setCellFormula(sumInsurance);
                    cell8Sum.setCellStyle(styleSum);
                HSSFCell cell9Sum = row.createCell(10);
                    cell9Sum.setCellFormula(sumNetSales);
                    cell9Sum.setCellStyle(styleSum);
                HSSFCell cell10Sum = row.createCell(11);
                    cell10Sum.setCellFormula(sumVat);
                    cell10Sum.setCellStyle(styleSum);
                HSSFCell cell101Sum = row.createCell(12);
                    cell101Sum.setCellFormula(sumWht);
                    cell101Sum.setCellStyle(styleSum);
                HSSFCell cell11Sum = row.createCell(13);
                    cell11Sum.setCellStyle(styleSum);    
                HSSFCell cell12Sum = row.createCell(14);
                    cell12Sum.setCellFormula(sumInvAmount);
                    cell12Sum.setCellStyle(styleSum);  
                HSSFCell cell13Sum = row.createCell(15);
                    cell13Sum.setCellFormula(sumBalance);
                    cell13Sum.setCellStyle(styleSum);      
             }
             for(int j =0;j<15;j++){
                 sheet.autoSizeColumn(j);
             }
        }
    }
    public void genTicketFareSummaryByStaff(HSSFWorkbook wb, List ticketSumByStaff) {
        String sheetName = "Sheet1";// name of sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        TicketFareSummaryByAgentStaff dataheader = new TicketFareSummaryByAgentStaff();
        
        HSSFDataFormat currency = wb.createDataFormat();
        // Set align Text
        HSSFCellStyle styleC21 = wb.createCellStyle();
        styleC21.setAlignment(styleC21.ALIGN_RIGHT);
        styleC21.setDataFormat(currency.getFormat("#,##0"));
        HSSFCellStyle styleC22 = wb.createCellStyle();
        styleC22.setAlignment(styleC22.ALIGN_LEFT);
        HSSFCellStyle styleC23 = wb.createCellStyle();
        styleC23.setAlignment(styleC22.ALIGN_CENTER);
        

        
        HSSFCellStyle styleC25 = wb.createCellStyle();
        styleC25.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC25.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC25.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleC25.setBorderBottom(HSSFCellStyle.BORDER_THIN);
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
        
        HSSFCellStyle styleC30 = wb.createCellStyle();
        styleC30.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC30.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC30.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleC30.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC30.setAlignment(styleC22.ALIGN_CENTER);
        
        if(!ticketSumByStaff.isEmpty()){
            dataheader = (TicketFareSummaryByAgentStaff)ticketSumByStaff.get(0);
        
        // set Header Report (Row 1)
        HSSFCellStyle styleC1 = wb.createCellStyle();
        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cell1 = row1.createCell(0);
        cell1.setCellValue("List Ticket Summary By Staff");
        styleC1.setFont(getHeaderFont(wb.createFont()));
        cell1.setCellStyle(styleC1);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:G1"));
 
        // Row 2
        HSSFRow row2 = sheet.createRow(1);
        HSSFCell cell21 = row2.createCell(0);
        cell21.setCellValue("Print By : ");
        cell21.setCellStyle(styleC21);
        HSSFCell cell22 = row2.createCell(1);
        cell22.setCellValue(dataheader.getPrintby());
        cell22.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B2:D2"));
        HSSFCell cell23 = row2.createCell(4);
        cell23.setCellValue("Air Line : ");
        cell23.setCellStyle(styleC21);
        HSSFCell cell24 = row2.createCell(5);
        cell24.setCellValue(dataheader.getAirline());
        cell24.setCellStyle(styleC22);
        
        // Row 3
        HSSFRow row3 = sheet.createRow(2);
        HSSFCell cell31 = row3.createCell(0);
        cell31.setCellValue("Department : ");
        cell31.setCellStyle(styleC21);
        HSSFCell cell32 = row3.createCell(1);
        cell32.setCellValue(dataheader.getHeaddepartment());
        cell32.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B3:D3"));
        HSSFCell cell33 = row3.createCell(4);
        cell33.setCellValue("Ticket Type : ");
        cell33.setCellStyle(styleC21);
        HSSFCell cell34 = row3.createCell(5);
        cell34.setCellValue(dataheader.getHeadtickettype());
        cell34.setCellStyle(styleC22);

        // Row 4
        HSSFRow row4 = sheet.createRow(3);
        HSSFCell cell41 = row4.createCell(0);
        cell41.setCellValue("Term Pay : ");
        cell41.setCellStyle(styleC21);
        HSSFCell cell42 = row4.createCell(1);
        cell42.setCellValue(dataheader.getTermpay());
        cell42.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B4:D4"));
        HSSFCell cell43 = row4.createCell(4);
        cell43.setCellValue("Ticket Buy : ");
        cell43.setCellStyle(styleC21);
        HSSFCell cell44 = row4.createCell(5);
        cell44.setCellValue(dataheader.getHeadticketbuy());
        cell44.setCellStyle(styleC22);

        // Row 5
        HSSFRow row5 = sheet.createRow(4);
        HSSFCell cell51 = row5.createCell(0);
        cell51.setCellValue("Sale Staff : ");
        cell51.setCellStyle(styleC21);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A5:E5"));
        HSSFCell cell52 = row5.createCell(5);
        cell52.setCellValue(dataheader.getHeadsale());
        cell52.setCellStyle(styleC22);
        
        // Row 6
        HSSFRow row6 = sheet.createRow(5);
        HSSFCell cell61 = row6.createCell(0);
        cell61.setCellValue("Issue Date : ");
        cell61.setCellStyle(styleC21);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A6:D6"));
        HSSFCell cell62 = row6.createCell(4);
        if(!"".equalsIgnoreCase(dataheader.getIssuefrom())){
        cell62.setCellValue(dataheader.getIssuefrom());
        cell62.setCellStyle(styleC22);
        }
        HSSFCell cell63 = row6.createCell(5);
        if(!"".equalsIgnoreCase(dataheader.getIssueto())){
        cell63.setCellValue("to  " + dataheader.getIssueto());
        cell63.setCellStyle(styleC22);
        }
        
        // Row 7
        HSSFRow row7 = sheet.createRow(6);
        HSSFCell cell71 = row7.createCell(0);
        cell71.setCellValue("Invoice Date : ");
        cell71.setCellStyle(styleC21);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A7:D7"));
        HSSFCell cell72 = row7.createCell(4);
        if(!"".equalsIgnoreCase(dataheader.getInvdatefrom())){
        cell72.setCellValue(dataheader.getInvdatefrom());
        cell72.setCellStyle(styleC22);
        }
        HSSFCell cell73 = row7.createCell(5);
        if(!"".equalsIgnoreCase(dataheader.getInvdateto())){
        cell73.setCellValue("to  " + dataheader.getInvdateto());
        cell73.setCellStyle(styleC22);
        }
        
        // Row 8
        HSSFRow row8 = sheet.createRow(7);
        HSSFCell cell81 = row8.createCell(0);
        cell81.setCellValue("Print on : ");
        cell81.setCellStyle(styleC21);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A8:D8"));
        HSSFCell cell82 = row8.createCell(4);
        cell82.setCellValue(dataheader.getOwner());
        cell82.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("E8:F8"));
        }
        // Header Table
        HSSFCellStyle styleC3 = wb.createCellStyle();
        styleC3.setFont(getHeaderTable(wb.createFont()));
        styleC3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC3.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC3.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleC3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC3.setAlignment(styleC3.ALIGN_CENTER);
        
        // Detail of Table
        String temp = "";
        int count = 9;
        int ktemp = 12 ;
        //Total Outbound
        int totalPaxOut = 0; 
        BigDecimal totalInvAmountOut = new BigDecimal("0.00");
        BigDecimal totalTiccomOut = new BigDecimal("0.00");
        BigDecimal totalSalePriceOut = new BigDecimal("0.00");
        BigDecimal totalAgentCommOut = new BigDecimal("0.00");
        BigDecimal totalProfitOut = new BigDecimal("0.00");
        BigDecimal totalCostOut = new BigDecimal("0.00");
//        Total Wendy
        int totalPaxWendy = 0 ;
        BigDecimal totalInvAmountWendy = new BigDecimal("0.00");
        BigDecimal totalTiccomWendy = new BigDecimal("0.00");
        BigDecimal totalSalePriceWendy = new BigDecimal("0.00");
        BigDecimal totalAgentCommWendy = new BigDecimal("0.00");
        BigDecimal totalProfitWendy = new BigDecimal("0.00");
        BigDecimal totalCostWendy = new BigDecimal("0.00");
        for(int i=0;i<ticketSumByStaff.size();i++){
            TicketFareSummaryByAgentStaff data = (TicketFareSummaryByAgentStaff)ticketSumByStaff.get(i);
            if(!temp.equalsIgnoreCase(data.getOwner())){
                if(!"".equalsIgnoreCase(temp)){
                   HSSFRow row = sheet.createRow(count + i);
                    String totalPax = "SUM(C" + ktemp+":C"+(count + i)+")";
                    String totalInvAmount = "SUM(D" + ktemp+":D"+(count + i )+")";
                    String totalSalePrice = "SUM(E" + ktemp+":E"+(count + i )+")";
                    String totalCost = "SUM(F" + ktemp+":F"+(count + i)+")";
                    String totalTiccom = "SUM(G" + ktemp+":G"+(count + i)+")";
                    String totalAgentComm = "SUM(H" + ktemp+":H"+(count + i)+")";
                    String totalProfit = "SUM(I" + ktemp+":I"+(count + i)+")";
                    // Set align Text
                    HSSFCell cellTotal0 = row.createCell(0);
                    cellTotal0.setCellStyle(styleC29);
                    HSSFCell cellTotal = row.createCell(1);
                    cellTotal.setCellValue("Total");
                    cellTotal.setCellStyle(styleC30);
                    HSSFCell cellTotal02 = row.createCell(2);
                    cellTotal02.setCellFormula(totalPax);
                    cellTotal02.setCellStyle(styleC26);
                    HSSFCell cellTotal03 = row.createCell(3);
                    cellTotal03.setCellFormula(totalInvAmount);
                    cellTotal03.setCellStyle(styleC25);
                    HSSFCell cellTotal04 = row.createCell(4);
                    cellTotal04.setCellFormula(totalSalePrice);
                    cellTotal04.setCellStyle(styleC25);
                    HSSFCell cellTotal05 = row.createCell(5);
                    cellTotal05.setCellFormula(totalCost);
                    cellTotal05.setCellStyle(styleC25);
                    HSSFCell cellTotal06 = row.createCell(6);
                    cellTotal06.setCellFormula(totalTiccom);
                    cellTotal06.setCellStyle(styleC25);
                    HSSFCell cellTotal07 = row.createCell(7);
                    cellTotal07.setCellFormula(totalAgentComm);
                    cellTotal07.setCellStyle(styleC25);
                    HSSFCell cellTotal08 = row.createCell(8);
                    cellTotal08.setCellFormula(totalProfit);
                    cellTotal08.setCellStyle(styleC25);
                    count = count+2;
                    ktemp = count+3+i;
                }
                
                int counts = count+i;
                int countss = count+1+i;

                // Row Agent Name
                HSSFRow row008 = sheet.createRow(counts);
                HSSFCell cell0081 = row008.createCell(1);
                cell0081.setCellValue("Staff name ");
                cell0081.setCellStyle(styleC23);
                HSSFCell cell0082 = row008.createCell(2);
                cell0082.setCellValue(data.getOwner());
                cell0082.setCellStyle(styleC22);
                temp = data.getOwner();

                // Header Table
                HSSFRow row09 = sheet.createRow(countss);
                HSSFCell cell091 = row09.createCell(0);
                cell091.setCellValue("Agent");
                cell091.setCellStyle(styleC3);
                sheet.autoSizeColumn(0);
                HSSFCell cell092 = row09.createCell(1);
                cell092.setCellValue("Department");
                cell092.setCellStyle(styleC3);
                sheet.autoSizeColumn(1);
                HSSFCell cell093 = row09.createCell(2);
                cell093.setCellValue("Pax");
                sheet.autoSizeColumn(2);
                cell093.setCellStyle(styleC3);
                HSSFCell cell094 = row09.createCell(3);
                cell094.setCellValue("Invoice Amount");
                cell094.setCellStyle(styleC3);
                sheet.autoSizeColumn(3);
                HSSFCell cell095 = row09.createCell(4);
                cell095.setCellValue("Sales Price"); 
                cell095.setCellStyle(styleC3);
                sheet.autoSizeColumn(4);
                HSSFCell cell096 = row09.createCell(5);
                cell096.setCellValue("Cost"); 
                cell096.setCellStyle(styleC3);
                sheet.autoSizeColumn(5);
                HSSFCell cell097 = row09.createCell(6);
                cell097.setCellValue("Ticket Comm");
                cell097.setCellStyle(styleC3);
                sheet.autoSizeColumn(6);
                HSSFCell cell098 = row09.createCell(7);
                cell098.setCellValue("Agent Comm");
                cell098.setCellStyle(styleC3);
                sheet.autoSizeColumn(7);
                HSSFCell cell099 = row09.createCell(8);
                cell099.setCellValue("Profit");
                cell099.setCellStyle(styleC3);
                sheet.autoSizeColumn(8);
                count = count+2;
            }  
            //set data 
            HSSFRow row = sheet.createRow(count + i);
            HSSFCell celldata01 = row.createCell(0);
            celldata01.setCellValue(data.getAgentname());
            celldata01.setCellStyle(styleC29);
            HSSFCell celldata02 = row.createCell(1);
            celldata02.setCellValue(data.getDepartment());
            celldata02.setCellStyle(styleC29);
            HSSFCell celldata03 = row.createCell(2);
            celldata03.setCellValue("".equalsIgnoreCase(String.valueOf(data.getPax())) ? 0 : new BigDecimal(data.getPax()).doubleValue()); 
            celldata03.setCellStyle(styleC26);
            HSSFCell celldata04 = row.createCell(3);
            celldata04.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInvamount())) ? 0 : new BigDecimal(data.getInvamount()).doubleValue());
            celldata04.setCellStyle(styleC25);
            HSSFCell celldata05 = row.createCell(4);
            celldata05.setCellValue("".equalsIgnoreCase(String.valueOf(data.getSaleprice())) ? 0: new BigDecimal(data.getSaleprice()).doubleValue());
            celldata05.setCellStyle(styleC25);
            HSSFCell celldata06 = row.createCell(5);
            celldata06.setCellValue("".equalsIgnoreCase(String.valueOf(data.getCost())) ? 0 : new BigDecimal(data.getCost()).doubleValue());
            celldata06.setCellStyle(styleC25);
            HSSFCell celldata07 = row.createCell(6);
            celldata07.setCellValue("".equalsIgnoreCase(String.valueOf(data.getTiccom())) ? 0 : new BigDecimal(data.getTiccom()).doubleValue()); 
            celldata07.setCellStyle(styleC25);
            HSSFCell celldata08 = row.createCell(7);
            celldata08.setCellValue("".equalsIgnoreCase(String.valueOf(data.getAgentcom())) ? 0 : new BigDecimal(data.getAgentcom()).doubleValue());
            celldata08.setCellStyle(styleC25);
            HSSFCell celldata09 = row.createCell(8);
            celldata09.setCellValue("".equalsIgnoreCase(String.valueOf(data.getProfit())) ? 0 : new BigDecimal(data.getProfit()).doubleValue());
            celldata09.setCellStyle(styleC25);
            
            
            if("outbound".equalsIgnoreCase(data.getDepartment())){
                TicketFareSummaryByAgentStaff sum = (TicketFareSummaryByAgentStaff)ticketSumByStaff.get(i);
                int pax = (!"".equalsIgnoreCase(sum.getPax()) ? Integer.parseInt(sum.getPax()) : 0);
                BigDecimal invamount = new BigDecimal(!"".equalsIgnoreCase(sum.getInvamount()) ? sum.getInvamount() : "0.00");
                BigDecimal ticcom = new BigDecimal(!"".equalsIgnoreCase(sum.getTiccom()) ? sum.getTiccom() : "0.00");
                BigDecimal saleprice = new BigDecimal(!"".equalsIgnoreCase(sum.getSaleprice()) ? sum.getSaleprice() : "0.00");
                BigDecimal agentcomm = new BigDecimal(!"".equalsIgnoreCase(sum.getAgentcom()) ? sum.getAgentcom() : "0.00");
                BigDecimal profit = new BigDecimal(!"".equalsIgnoreCase(sum.getProfit()) ? sum.getProfit() : "0.00");
                BigDecimal cost = new BigDecimal(!"".equalsIgnoreCase(sum.getCost()) ? sum.getCost(): "0.00");
                totalPaxOut = totalPaxOut+pax;
                totalInvAmountOut = totalInvAmountOut.add(invamount);
                totalTiccomOut = totalTiccomOut.add(ticcom);
                totalSalePriceOut = totalSalePriceOut.add(saleprice);
                totalAgentCommOut = totalAgentCommOut.add(agentcomm);
                totalProfitOut = totalProfitOut.add(profit);
                totalCostOut = totalCostOut.add(cost);
            }
            if("wendy".equalsIgnoreCase(data.getDepartment())){
                TicketFareSummaryByAgentStaff sum = (TicketFareSummaryByAgentStaff)ticketSumByStaff.get(i);
                int pax = (!"".equalsIgnoreCase(sum.getPax()) ? Integer.parseInt(sum.getPax()) : 0);
                BigDecimal invamount = new BigDecimal(!"".equalsIgnoreCase(sum.getInvamount()) ? sum.getInvamount() : "0.00");
                BigDecimal ticcom = new BigDecimal(!"".equalsIgnoreCase(sum.getTiccom()) ? sum.getTiccom() : "0.00");
                BigDecimal saleprice = new BigDecimal(!"".equalsIgnoreCase(sum.getSaleprice()) ? sum.getSaleprice() : "0.00");
                BigDecimal agentcomm = new BigDecimal(!"".equalsIgnoreCase(sum.getAgentcom()) ? sum.getAgentcom() : "0.00");
                BigDecimal profit = new BigDecimal(!"".equalsIgnoreCase(sum.getProfit()) ? sum.getProfit() : "0.00");
                BigDecimal cost = new BigDecimal(!"".equalsIgnoreCase(sum.getCost()) ? sum.getCost(): "0.00");
                totalPaxWendy = totalPaxWendy+pax;
                totalInvAmountWendy = totalInvAmountWendy.add(invamount);
                totalTiccomWendy = totalTiccomWendy.add(ticcom);
                totalSalePriceWendy = totalSalePriceWendy.add(saleprice);
                totalAgentCommWendy = totalAgentCommWendy.add(agentcomm);
                totalProfitWendy = totalProfitWendy.add(profit);
                totalCostWendy = totalCostWendy.add(cost);
            }
            
            // set total last row
            if(i == (ticketSumByStaff.size()-1)){
                HSSFRow rows = sheet.createRow(count + 1 + i);
                
                String totalPax = "SUM(C" + ktemp+":C"+(count + i + 1)+")";
                String totalInvAmount = "SUM(D" + ktemp+":D"+(count + i + 1)+")";
                String totalSalePrice  = "SUM(E" + ktemp+":E"+(count + i +1)+")";
                String totalCost = "SUM(F" + ktemp+":F"+(count + i + 1)+")";
                String totalTiccom  = "SUM(G" + ktemp+":G"+(count + i + 1)+")";
                String totalAgentComm = "SUM(H" + ktemp+":H"+(count + i + 1)+")";
                String totalProfit = "SUM(I" + ktemp+":I"+(count + i + 1)+")";
                
//                int totalPax = 0;
//                BigDecimal totalInvAmount = new BigDecimal("0.00");
//                BigDecimal totalTiccom = new BigDecimal("0.00");
//                BigDecimal totalSalePrice = new BigDecimal("0.00");
//                BigDecimal totalAgentComm = new BigDecimal("0.00");
//                BigDecimal totalProfit = new BigDecimal("0.00");
//                for(int k=ktemp;k<ticketSumByStaff.size();k++){
//                    TicketFareSummaryByAgentStaff sum = (TicketFareSummaryByAgentStaff)ticketSumByStaff.get(k);
//                    int pax = (!"".equalsIgnoreCase(sum.getPax()) ? Integer.parseInt(sum.getPax()) : 0);
//                    BigDecimal invamount = new BigDecimal(!"".equalsIgnoreCase(sum.getInvamount()) ? sum.getInvamount() : "0.00");
//                    BigDecimal ticcom = new BigDecimal(!"".equalsIgnoreCase(sum.getTiccom()) ? sum.getTiccom() : "0.00");
//                    BigDecimal saleprice = new BigDecimal(!"".equalsIgnoreCase(sum.getSaleprice()) ? sum.getSaleprice() : "0.00");
//                    BigDecimal agentcomm = new BigDecimal(!"".equalsIgnoreCase(sum.getAgentcom()) ? sum.getAgentcom() : "0.00");
//                    BigDecimal profit = new BigDecimal(!"".equalsIgnoreCase(sum.getProfit()) ? sum.getProfit() : "0.00");
//                    totalPax = totalPax+pax;
//                    totalInvAmount = totalInvAmount.add(invamount);
//                    totalTiccom = totalTiccom.add(ticcom);
//                    totalSalePrice = totalSalePrice.add(saleprice);
//                    totalAgentComm = totalAgentComm.add(agentcomm);
//                    totalProfit = totalProfit.add(profit);
//                }
                HSSFCell celldatas0 = rows.createCell(0);
                celldatas0.setCellStyle(styleC29);
                HSSFCell celldatas01 = rows.createCell(1);
                celldatas01.setCellValue("Total");
                celldatas01.setCellStyle(styleC30);
                HSSFCell celldatas02 = rows.createCell(2);
                celldatas02.setCellFormula(totalPax);
                celldatas02.setCellStyle(styleC26);
                HSSFCell celldatas03 = rows.createCell(3);
                celldatas03.setCellFormula(totalInvAmount);
                celldatas03.setCellStyle(styleC25);
                HSSFCell celldatas04 = rows.createCell(4);
                celldatas04.setCellFormula(totalSalePrice);
                celldatas04.setCellStyle(styleC25);
                HSSFCell celldatas05 = rows.createCell(5);
                celldatas05.setCellFormula(totalCost);
                celldatas05.setCellStyle(styleC25);
                HSSFCell celldatas06 = rows.createCell(6);
                celldatas06.setCellFormula(totalTiccom);
                celldatas06.setCellStyle(styleC25);
                HSSFCell celldatas07 = rows.createCell(7);
                celldatas07.setCellFormula(totalAgentComm);
                celldatas07.setCellStyle(styleC25);
                HSSFCell celldatas08 = rows.createCell(8);
                celldatas08.setCellFormula(totalProfit);
                celldatas08.setCellStyle(styleC25);
                count = count+3;

                rows = sheet.createRow(count+i);
                HSSFCell celldataOut01 = rows.createCell(1);
                celldataOut01.setCellValue("Summary Outbound");
                celldataOut01.setCellStyle(styleC22);
                HSSFCell celldataOut02 = rows.createCell(2);
                celldataOut02.setCellValue("".equalsIgnoreCase(String.valueOf(totalPaxOut)) ? 0 : new BigDecimal(totalPaxOut).doubleValue());
                celldataOut02.setCellStyle(styleC28);
                HSSFCell celldataOut03 = rows.createCell(3);
                celldataOut03.setCellValue("".equalsIgnoreCase(String.valueOf(totalInvAmountOut)) ? 0 : totalInvAmountOut.doubleValue());
                celldataOut03.setCellStyle(styleC27);
                HSSFCell celldataOut04 = rows.createCell(4);
                celldataOut04.setCellValue("".equalsIgnoreCase(String.valueOf(totalSalePriceOut)) ? 0 : totalSalePriceOut.doubleValue());
                celldataOut04.setCellStyle(styleC27);
                HSSFCell celldataOut05 = rows.createCell(5);
                celldataOut05.setCellValue("".equalsIgnoreCase(String.valueOf(totalCostOut)) ? 0 : totalCostOut.doubleValue());
                celldataOut05.setCellStyle(styleC27);
                HSSFCell celldataOut06 = rows.createCell(6);
                celldataOut06.setCellValue("".equalsIgnoreCase(String.valueOf(totalTiccomOut)) ? 0 : totalTiccomOut.doubleValue());
                celldataOut06.setCellStyle(styleC27);
                HSSFCell celldataOut07 = rows.createCell(7);
                celldataOut07.setCellValue("".equalsIgnoreCase(String.valueOf(totalAgentCommOut)) ? 0 : totalAgentCommOut.doubleValue());
                celldataOut07.setCellStyle(styleC27);
                HSSFCell celldataOut08 = rows.createCell(8);
                celldataOut08.setCellValue("".equalsIgnoreCase(String.valueOf(totalProfitOut)) ? 0 : totalProfitOut.doubleValue());
                celldataOut08.setCellStyle(styleC27);
                
                
                
                rows = sheet.createRow(count+1+i);
                HSSFCell celldataWen01 = rows.createCell(1);
                celldataWen01.setCellValue("Summary Wendy");
                celldataWen01.setCellStyle(styleC22);
                HSSFCell celldataWen02 = rows.createCell(2);
                celldataWen02.setCellValue("".equalsIgnoreCase(String.valueOf(totalPaxWendy)) ? 0 : new BigDecimal(totalPaxWendy).doubleValue());
                celldataWen02.setCellStyle(styleC28);
                HSSFCell celldataWen03 = rows.createCell(3);
                celldataWen03.setCellValue("".equalsIgnoreCase(String.valueOf(totalInvAmountWendy)) ? 0 : totalInvAmountWendy.doubleValue());
                celldataWen03.setCellStyle(styleC27);
                HSSFCell celldataWen04 = rows.createCell(4);
                celldataWen04.setCellValue("".equalsIgnoreCase(String.valueOf(totalSalePriceWendy)) ? 0 : totalSalePriceWendy.doubleValue());
                celldataWen04.setCellStyle(styleC27);
                HSSFCell celldataWen05 = rows.createCell(5);
                celldataWen05.setCellValue("".equalsIgnoreCase(String.valueOf(totalCostWendy)) ? 0 : totalCostWendy.doubleValue());
                celldataWen05.setCellStyle(styleC27);
                HSSFCell celldataWen06 = rows.createCell(6);
                celldataWen06.setCellValue("".equalsIgnoreCase(String.valueOf(totalTiccomWendy)) ? 0 : totalTiccomWendy.doubleValue());
                celldataWen06.setCellStyle(styleC27);
                HSSFCell celldataWen07 = rows.createCell(7);
                celldataWen07.setCellValue("".equalsIgnoreCase(String.valueOf(totalAgentCommWendy)) ? 0 : totalAgentCommWendy.doubleValue());
                celldataWen07.setCellStyle(styleC27);
                HSSFCell celldataWen08 = rows.createCell(8);
                celldataWen08.setCellValue("".equalsIgnoreCase(String.valueOf(totalProfitWendy)) ? 0 : totalProfitWendy.doubleValue());
                celldataWen08.setCellStyle(styleC27);

//                int totalPaxWI = 0;
//                BigDecimal totalInvAmountWI = new BigDecimal("0.00");
//                BigDecimal totalTiccomWI = new BigDecimal("0.00");
//                BigDecimal totalSalePriceWI = new BigDecimal("0.00");
//                BigDecimal totalAgentCommWI = new BigDecimal("0.00");
//                BigDecimal totalProfitWI = new BigDecimal("0.00");
//                totalPaxWI = totalPaxOut+totalPaxWendy;
//                totalInvAmountWI = totalInvAmountOut.add(totalInvAmountWendy);
//                totalTiccomWI = totalTiccomOut.add(totalTiccomWendy);
//                totalSalePriceWI = totalSalePriceOut.add(totalSalePriceWendy);         
//                totalAgentCommWI = totalAgentCommOut.add(totalAgentCommWendy);    
//                totalProfitWI = totalProfitOut.add(totalProfitWendy);
                        
                rows = sheet.createRow(count+2+i);
                String totalPaxWI = "SUM(C" +(count+i+1)+":C"+(count+i+2)+")";
                String totalInvAmountWI = "SUM(D" +(count+i+1)+":D"+(count+i+2)+")";
                String totalSalePriceWI = "SUM(E" + (count+i+1)+":E"+(count+i+2)+")";
                String totalCostWI = "SUM(F" + (count+i+1)+":F"+(count+i+2)+")";
                String totalTiccomWI = "SUM(G" + (count+i+1)+":G"+(count+i+2)+")";
                String totalAgentCommWI = "SUM(H" + (count+i+1)+":H"+(count+i+2)+")";
                String totalProfitWI = "SUM(I" + (count+i+1)+":I"+(count+i+2)+")";

                HSSFCell celldataWI01 = rows.createCell(1);
                celldataWI01.setCellValue("Total");
                celldataWI01.setCellStyle(styleC22);
                HSSFCell celldataWI02 = rows.createCell(2);
                celldataWI02.setCellFormula(totalPaxWI);
                celldataWI02.setCellStyle(styleC28);
                HSSFCell celldataWI03 = rows.createCell(3);
                celldataWI03.setCellFormula(totalInvAmountWI);
                celldataWI03.setCellStyle(styleC27);
                HSSFCell celldataWI04 = rows.createCell(4);
                celldataWI04.setCellFormula(totalSalePriceWI);
                celldataWI04.setCellStyle(styleC27);
                HSSFCell celldataWI05 = rows.createCell(5);
                celldataWI05.setCellFormula(totalCostWI);
                celldataWI05.setCellStyle(styleC27);
                HSSFCell celldataWI06 = rows.createCell(6);
                celldataWI06.setCellFormula(totalTiccomWI);
                celldataWI06.setCellStyle(styleC27);
                HSSFCell celldataWI07 = rows.createCell(7);
                celldataWI07.setCellFormula(totalAgentCommWI);
                celldataWI07.setCellStyle(styleC27);
                HSSFCell celldataWI08 = rows.createCell(8);
                celldataWI08.setCellFormula(totalProfitWI);
                celldataWI08.setCellStyle(styleC27);
                
            }
            for(int j =0;j<8;j++){
                sheet.autoSizeColumn(j);
            }
        }
    }
    
    public void genTicketFareSummaryByAgent(HSSFWorkbook wb, List ticketSumByAgent) {
        String sheetName = "Sheet1";// name of sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        TicketFareSummaryByAgentStaff dataheader = new TicketFareSummaryByAgentStaff();
        
        HSSFDataFormat currency = wb.createDataFormat();
        // Set align Text
        HSSFCellStyle styleC21 = wb.createCellStyle();
        styleC21.setAlignment(styleC21.ALIGN_RIGHT);
        HSSFCellStyle styleC22 = wb.createCellStyle();
        styleC22.setAlignment(styleC22.ALIGN_LEFT);
        HSSFCellStyle styleC23 = wb.createCellStyle();
        styleC23.setAlignment(styleC22.ALIGN_CENTER);
        

        
        HSSFCellStyle styleC25 = wb.createCellStyle();
        styleC25.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC25.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC25.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleC25.setBorderBottom(HSSFCellStyle.BORDER_THIN);
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
        
        HSSFCellStyle styleC30 = wb.createCellStyle();
        styleC30.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC30.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC30.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleC30.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC30.setAlignment(styleC22.ALIGN_CENTER);
        
        if(!ticketSumByAgent.isEmpty()){
            dataheader = (TicketFareSummaryByAgentStaff)ticketSumByAgent.get(0);
        
            // set Header Report (Row 1)
            HSSFCellStyle styleC1 = wb.createCellStyle();
            HSSFRow row1 = sheet.createRow(0);
            HSSFCell cell1 = row1.createCell(0);
            cell1.setCellValue("List Ticket Summary By Agent");
            styleC1.setFont(getHeaderFont(wb.createFont()));
            cell1.setCellStyle(styleC1);
            sheet.addMergedRegion(CellRangeAddress.valueOf("A1:G1"));


            // Row 2
            HSSFRow row2 = sheet.createRow(1);
            HSSFCell cell21 = row2.createCell(0);
            cell21.setCellValue("Print By : ");
            cell21.setCellStyle(styleC21);
            HSSFCell cell22 = row2.createCell(1);
            cell22.setCellValue(dataheader.getPrintby());
            cell22.setCellStyle(styleC22);
            sheet.addMergedRegion(CellRangeAddress.valueOf("B2:D2"));
            HSSFCell cell23 = row2.createCell(4);
            cell23.setCellValue("Air Line : ");
            cell23.setCellStyle(styleC21);
            HSSFCell cell24 = row2.createCell(5);
            cell24.setCellValue(dataheader.getAirline());
            cell24.setCellStyle(styleC22);

            // Row 3
            HSSFRow row3 = sheet.createRow(2);
            HSSFCell cell31 = row3.createCell(0);
            cell31.setCellValue("Department : ");
            cell31.setCellStyle(styleC21);
            HSSFCell cell32 = row3.createCell(1);
            cell32.setCellValue(dataheader.getHeaddepartment());
            cell32.setCellStyle(styleC22);
            sheet.addMergedRegion(CellRangeAddress.valueOf("B3:D3"));
            HSSFCell cell33 = row3.createCell(4);
            cell33.setCellValue("Ticket Type : ");
            cell33.setCellStyle(styleC21);
            HSSFCell cell34 = row3.createCell(5);
            cell34.setCellValue(dataheader.getHeadtickettype());
            cell34.setCellStyle(styleC22);

            // Row 4
            HSSFRow row4 = sheet.createRow(3);
            HSSFCell cell41 = row4.createCell(0);
            cell41.setCellValue("Term Pay : ");
            cell41.setCellStyle(styleC21);
            HSSFCell cell42 = row4.createCell(1);
            cell42.setCellValue(dataheader.getTermpay());
            cell42.setCellStyle(styleC22);
            sheet.addMergedRegion(CellRangeAddress.valueOf("B4:D4"));
            HSSFCell cell43 = row4.createCell(4);
            cell43.setCellValue("Ticket Buy : ");
            cell43.setCellStyle(styleC21);
            HSSFCell cell44 = row4.createCell(5);
            cell44.setCellValue(dataheader.getHeadticketbuy());
            cell44.setCellStyle(styleC22);

            // Row 5
            HSSFRow row5 = sheet.createRow(4);
            HSSFCell cell51 = row5.createCell(0);
            cell51.setCellValue("Sale Staff : ");
            cell51.setCellStyle(styleC21);
            sheet.addMergedRegion(CellRangeAddress.valueOf("A5:E5"));
            HSSFCell cell52 = row5.createCell(5);
            cell52.setCellValue(dataheader.getHeadsale());
            cell52.setCellStyle(styleC22);

            // Row 6
            HSSFRow row6 = sheet.createRow(5);
            HSSFCell cell61 = row6.createCell(0);
            cell61.setCellValue("Issue Date : ");
            cell61.setCellStyle(styleC21);
            sheet.addMergedRegion(CellRangeAddress.valueOf("A6:D6"));
            HSSFCell cell62 = row6.createCell(4);
            if(!"".equalsIgnoreCase(dataheader.getIssuefrom())){
            cell62.setCellValue(dataheader.getIssuefrom());
            cell62.setCellStyle(styleC22);
            }
            HSSFCell cell63 = row6.createCell(5);
            if(!"".equalsIgnoreCase(dataheader.getIssueto())){
            cell63.setCellValue("to  " + dataheader.getIssueto());
            cell63.setCellStyle(styleC22);
            }

            // Row 7
            HSSFRow row7 = sheet.createRow(6);
            HSSFCell cell71 = row7.createCell(0);
            cell71.setCellValue("Invoice Date : ");
            cell71.setCellStyle(styleC21);
            sheet.addMergedRegion(CellRangeAddress.valueOf("A7:D7"));
            HSSFCell cell72 = row7.createCell(4);
            if(!"".equalsIgnoreCase(dataheader.getInvdatefrom())){
            cell72.setCellValue(dataheader.getInvdatefrom());
            cell72.setCellStyle(styleC22);
            }
            HSSFCell cell73 = row7.createCell(5);
            if(!"".equalsIgnoreCase(dataheader.getInvdateto())){
            cell73.setCellValue("to  " + dataheader.getInvdateto());
            cell73.setCellStyle(styleC22);
            }

            // Row 8
            HSSFRow row8 = sheet.createRow(7);
            HSSFCell cell81 = row8.createCell(0);
            cell81.setCellValue("Print on : ");
            cell81.setCellStyle(styleC21);
            sheet.addMergedRegion(CellRangeAddress.valueOf("A8:D8"));
            HSSFCell cell82 = row8.createCell(4);
            cell82.setCellValue(dataheader.getPrinton());
            cell82.setCellStyle(styleC22);
            sheet.addMergedRegion(CellRangeAddress.valueOf("E8:F8"));
        }
        
        // Header Table style
        HSSFCellStyle styleC3 = wb.createCellStyle();
        styleC3.setFont(getHeaderTable(wb.createFont()));
        styleC3.setAlignment(styleC3.ALIGN_CENTER);
        styleC3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC3.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC3.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleC3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        // Detail of Table
        String temp = "";
        int count = 9;
        int ktemp = 12 ;
        //Total Outbound
        int totalPaxOut = 0; 
        BigDecimal totalInvAmountOut = new BigDecimal("0.00");
        BigDecimal totalTiccomOut = new BigDecimal("0.00");
        BigDecimal totalSalePriceOut = new BigDecimal("0.00");
        BigDecimal totalAgentCommOut = new BigDecimal("0.00");
        BigDecimal totalProfitOut = new BigDecimal("0.00");
        BigDecimal totalCostOut = new BigDecimal("0.00");
        //Total Wendy
        int totalPaxWendy = 0 ;
        BigDecimal totalInvAmountWendy = new BigDecimal("0.00");
        BigDecimal totalTiccomWendy = new BigDecimal("0.00");
        BigDecimal totalSalePriceWendy = new BigDecimal("0.00");
        BigDecimal totalAgentCommWendy = new BigDecimal("0.00");
        BigDecimal totalProfitWendy = new BigDecimal("0.00");
        BigDecimal totalCostWendy = new BigDecimal("0.00");
        for(int i=0;i<ticketSumByAgent.size();i++){
            TicketFareSummaryByAgentStaff data = (TicketFareSummaryByAgentStaff)ticketSumByAgent.get(i);
            if(!temp.equalsIgnoreCase(data.getAgentname())){
                if(!"".equalsIgnoreCase(temp)){
                    HSSFRow row = sheet.createRow(count + i);
                    String totalPax = "SUM(C" + ktemp+":C"+(count + i)+")";
                    String totalInvAmount = "SUM(D" + ktemp+":D"+(count + i )+")";
                    String totalSalePrice = "SUM(E" + ktemp+":E"+(count + i )+")";
                    String totalCost = "SUM(F" + ktemp+":F"+(count + i)+")";
                    String totalTiccom = "SUM(G" + ktemp+":G"+(count + i)+")";
                    String totalAgentComm = "SUM(H" + ktemp+":H"+(count + i)+")";
                    String totalProfit = "SUM(I" + ktemp+":I"+(count + i)+")";
                    // Set align Text
                    HSSFCell cellTotal0 = row.createCell(0);
                    cellTotal0.setCellStyle(styleC29);
                    HSSFCell cellTotal = row.createCell(1);
                    cellTotal.setCellValue("Total");
                    cellTotal.setCellStyle(styleC30);
                    HSSFCell cellTotal02 = row.createCell(2);
                    cellTotal02.setCellFormula(totalPax);
                    cellTotal02.setCellStyle(styleC26);
                    HSSFCell cellTotal03 = row.createCell(3);
                    cellTotal03.setCellFormula(totalInvAmount);
                    cellTotal03.setCellStyle(styleC25);
                    HSSFCell cellTotal04 = row.createCell(4);
                    cellTotal04.setCellFormula(totalSalePrice);
                    cellTotal04.setCellStyle(styleC25);
                    HSSFCell cellTotal05 = row.createCell(5);
                    cellTotal05.setCellFormula(totalCost);
                    cellTotal05.setCellStyle(styleC25);
                    HSSFCell cellTotal06 = row.createCell(6);
                    cellTotal06.setCellFormula(totalTiccom);
                    cellTotal06.setCellStyle(styleC25);
                    HSSFCell cellTotal07 = row.createCell(7);
                    cellTotal07.setCellFormula(totalAgentComm);
                    cellTotal07.setCellStyle(styleC25);
                    HSSFCell cellTotal08 = row.createCell(8);
                    cellTotal08.setCellFormula(totalProfit);
                    cellTotal08.setCellStyle(styleC25);
                    count = count+2;
                    ktemp = count+3+i;
                }
                
                int counts = count+i;
                int countss = count+1+i;

                // Row Agent Name
                HSSFRow row008 = sheet.createRow(counts);
                HSSFCell cell0081 = row008.createCell(1);
                cell0081.setCellValue("Agent name ");
                cell0081.setCellStyle(styleC23);
                HSSFCell cell0082 = row008.createCell(2);
                cell0082.setCellValue(data.getAgentname());
                cell0082.setCellStyle(styleC22);
                temp = data.getAgentname();

                // Header Table
                HSSFRow row09 = sheet.createRow(countss);
                HSSFCell cell091 = row09.createCell(0);
                cell091.setCellValue("Staff");
                cell091.setCellStyle(styleC3);
                sheet.autoSizeColumn(0);
                HSSFCell cell092 = row09.createCell(1);
                cell092.setCellValue("Department");
                cell092.setCellStyle(styleC3);
                sheet.autoSizeColumn(1);
                HSSFCell cell093 = row09.createCell(2);
                cell093.setCellValue("Pax");
                sheet.autoSizeColumn(2);
                cell093.setCellStyle(styleC3);
                HSSFCell cell094 = row09.createCell(3);
                cell094.setCellValue("Invoice Amount");
                cell094.setCellStyle(styleC3);
                sheet.autoSizeColumn(3);
                HSSFCell cell095 = row09.createCell(4);
                cell095.setCellValue("Sales Price"); 
                cell095.setCellStyle(styleC3);
                sheet.autoSizeColumn(4);
                HSSFCell cell096 = row09.createCell(5);
                cell096.setCellValue("Cost"); 
                cell096.setCellStyle(styleC3);
                sheet.autoSizeColumn(5);
                HSSFCell cell097 = row09.createCell(6);
                cell097.setCellValue("Ticket Comm");
                cell097.setCellStyle(styleC3);
                sheet.autoSizeColumn(6);
                HSSFCell cell098 = row09.createCell(7);
                cell098.setCellValue("Agent Comm");
                cell098.setCellStyle(styleC3);
                sheet.autoSizeColumn(7);
                HSSFCell cell099 = row09.createCell(8);
                cell099.setCellValue("Profit");
                cell099.setCellStyle(styleC3);
                sheet.autoSizeColumn(8);
                count = count+2;
            }  
            //set data 
            HSSFRow row = sheet.createRow(count + i);
            HSSFCell celldata01 = row.createCell(0);
            celldata01.setCellValue(data.getOwner());
            celldata01.setCellStyle(styleC29);
            HSSFCell celldata02 = row.createCell(1);
            celldata02.setCellValue(data.getDepartment());
            celldata02.setCellStyle(styleC29);
            HSSFCell celldata03 = row.createCell(2);
            celldata03.setCellValue("".equalsIgnoreCase(String.valueOf(data.getPax())) ? 0 : new BigDecimal(data.getPax()).doubleValue()); 
            celldata03.setCellStyle(styleC26);
            HSSFCell celldata04 = row.createCell(3);
            celldata04.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInvamount())) ? 0 : new BigDecimal(data.getInvamount()).doubleValue());
            celldata04.setCellStyle(styleC25);
            HSSFCell celldata05 = row.createCell(4);
            celldata05.setCellValue("".equalsIgnoreCase(String.valueOf(data.getSaleprice())) ? 0: new BigDecimal(data.getSaleprice()).doubleValue());
            celldata05.setCellStyle(styleC25);
            HSSFCell celldata06 = row.createCell(5);
            celldata06.setCellValue("".equalsIgnoreCase(String.valueOf(data.getCost())) ? 0 : new BigDecimal(data.getCost()).doubleValue());
            celldata06.setCellStyle(styleC25);
            HSSFCell celldata07 = row.createCell(6);
            celldata07.setCellValue("".equalsIgnoreCase(String.valueOf(data.getTiccom())) ? 0 : new BigDecimal(data.getTiccom()).doubleValue()); 
            celldata07.setCellStyle(styleC25);
            HSSFCell celldata08 = row.createCell(7);
            celldata08.setCellValue("".equalsIgnoreCase(String.valueOf(data.getAgentcom())) ? 0 : new BigDecimal(data.getAgentcom()).doubleValue());
            celldata08.setCellStyle(styleC25);
            HSSFCell celldata09 = row.createCell(8);
            celldata09.setCellValue("".equalsIgnoreCase(String.valueOf(data.getProfit())) ? 0 : new BigDecimal(data.getProfit()).doubleValue());
            celldata09.setCellStyle(styleC25);
            
            if("outbound".equalsIgnoreCase(data.getDepartment())){
                TicketFareSummaryByAgentStaff sum = (TicketFareSummaryByAgentStaff)ticketSumByAgent.get(i);
                int pax = (!"".equalsIgnoreCase(sum.getPax()) ? Integer.parseInt(sum.getPax()) : 0);
                BigDecimal invamount = new BigDecimal(!"".equalsIgnoreCase(sum.getInvamount()) ? sum.getInvamount() : "0.00");
                BigDecimal ticcom = new BigDecimal(!"".equalsIgnoreCase(sum.getTiccom()) ? sum.getTiccom() : "0.00");
                BigDecimal saleprice = new BigDecimal(!"".equalsIgnoreCase(sum.getSaleprice()) ? sum.getSaleprice() : "0.00");
                BigDecimal agentcomm = new BigDecimal(!"".equalsIgnoreCase(sum.getAgentcom()) ? sum.getAgentcom() : "0.00");
                BigDecimal profit = new BigDecimal(!"".equalsIgnoreCase(sum.getProfit()) ? sum.getProfit() : "0.00");
                BigDecimal cost = new BigDecimal(!"".equalsIgnoreCase(sum.getCost()) ? sum.getCost(): "0.00");
                totalPaxOut = totalPaxOut+pax;
                totalInvAmountOut = totalInvAmountOut.add(invamount);
                totalTiccomOut = totalTiccomOut.add(ticcom);
                totalSalePriceOut = totalSalePriceOut.add(saleprice);
                totalAgentCommOut = totalAgentCommOut.add(agentcomm);
                totalProfitOut = totalProfitOut.add(profit);
                totalCostOut = totalCostOut.add(cost);
            }
            if("wendy".equalsIgnoreCase(data.getDepartment())){
                TicketFareSummaryByAgentStaff sum = (TicketFareSummaryByAgentStaff)ticketSumByAgent.get(i);
                int pax = (!"".equalsIgnoreCase(sum.getPax()) ? Integer.parseInt(sum.getPax()) : 0);
                BigDecimal invamount = new BigDecimal(!"".equalsIgnoreCase(sum.getInvamount()) ? sum.getInvamount() : "0.00");
                BigDecimal ticcom = new BigDecimal(!"".equalsIgnoreCase(sum.getTiccom()) ? sum.getTiccom() : "0.00");
                BigDecimal saleprice = new BigDecimal(!"".equalsIgnoreCase(sum.getSaleprice()) ? sum.getSaleprice() : "0.00");
                BigDecimal agentcomm = new BigDecimal(!"".equalsIgnoreCase(sum.getAgentcom()) ? sum.getAgentcom() : "0.00");
                BigDecimal profit = new BigDecimal(!"".equalsIgnoreCase(sum.getProfit()) ? sum.getProfit() : "0.00");
                BigDecimal cost = new BigDecimal(!"".equalsIgnoreCase(sum.getCost()) ? sum.getCost(): "0.00");
                totalPaxWendy = totalPaxWendy+pax;
                totalInvAmountWendy = totalInvAmountWendy.add(invamount);
                totalTiccomWendy = totalTiccomWendy.add(ticcom);
                totalSalePriceWendy = totalSalePriceWendy.add(saleprice);
                totalAgentCommWendy = totalAgentCommWendy.add(agentcomm);
                totalProfitWendy = totalProfitWendy.add(profit);
                totalCostWendy = totalCostWendy.add(cost);
            }
            
            // set total last row
            if(i == (ticketSumByAgent.size()-1)){
                HSSFRow rows = sheet.createRow(count + 1 + i);
                
                String totalPax = "SUM(C" + ktemp+":C"+(count + i + 1)+")";
                String totalInvAmount = "SUM(D" + ktemp+":D"+(count + i + 1)+")";
                String totalSalePrice  = "SUM(E" + ktemp+":E"+(count + i +1)+")";
                String totalCost = "SUM(F" + ktemp+":F"+(count + i + 1)+")";
                String totalTiccom  = "SUM(G" + ktemp+":G"+(count + i + 1)+")";
                String totalAgentComm = "SUM(H" + ktemp+":H"+(count + i + 1)+")";
                String totalProfit = "SUM(I" + ktemp+":I"+(count + i + 1)+")";
                
                HSSFCell celldatas0 = rows.createCell(0);
                celldatas0.setCellStyle(styleC29);
                HSSFCell celldatas01 = rows.createCell(1);
                celldatas01.setCellValue("Total");
                celldatas01.setCellStyle(styleC30);
                HSSFCell celldatas02 = rows.createCell(2);
                celldatas02.setCellFormula(totalPax);
                celldatas02.setCellStyle(styleC26);
                HSSFCell celldatas03 = rows.createCell(3);
                celldatas03.setCellFormula(totalInvAmount);
                celldatas03.setCellStyle(styleC25);
                HSSFCell celldatas04 = rows.createCell(4);
                celldatas04.setCellFormula(totalSalePrice);
                celldatas04.setCellStyle(styleC25);
                HSSFCell celldatas05 = rows.createCell(5);
                celldatas05.setCellFormula(totalCost);
                celldatas05.setCellStyle(styleC25);
                HSSFCell celldatas06 = rows.createCell(6);
                celldatas06.setCellFormula(totalTiccom);
                celldatas06.setCellStyle(styleC25);
                HSSFCell celldatas07 = rows.createCell(7);
                celldatas07.setCellFormula(totalAgentComm);
                celldatas07.setCellStyle(styleC25);
                HSSFCell celldatas08 = rows.createCell(8);
                celldatas08.setCellFormula(totalProfit);
                celldatas08.setCellStyle(styleC25);
                count = count+3;

                rows = sheet.createRow(count+i);
                HSSFCell celldataOut01 = rows.createCell(1);
                celldataOut01.setCellValue("Summary Outbound");
                celldataOut01.setCellStyle(styleC22);
                HSSFCell celldataOut02 = rows.createCell(2);
                celldataOut02.setCellValue("".equalsIgnoreCase(String.valueOf(totalPaxOut)) ? 0 : new BigDecimal(totalPaxOut).doubleValue());
                celldataOut02.setCellStyle(styleC28);
                HSSFCell celldataOut03 = rows.createCell(3);
                celldataOut03.setCellValue("".equalsIgnoreCase(String.valueOf(totalInvAmountOut)) ? 0 : totalInvAmountOut.doubleValue());
                celldataOut03.setCellStyle(styleC27);
                HSSFCell celldataOut04 = rows.createCell(4);
                celldataOut04.setCellValue("".equalsIgnoreCase(String.valueOf(totalSalePriceOut)) ? 0 : totalSalePriceOut.doubleValue());
                celldataOut04.setCellStyle(styleC27);
                HSSFCell celldataOut05 = rows.createCell(5);
                celldataOut05.setCellValue("".equalsIgnoreCase(String.valueOf(totalCostOut)) ? 0 : totalCostOut.doubleValue());
                celldataOut05.setCellStyle(styleC27);
                HSSFCell celldataOut06 = rows.createCell(6);
                celldataOut06.setCellValue("".equalsIgnoreCase(String.valueOf(totalTiccomOut)) ? 0 : totalTiccomOut.doubleValue());
                celldataOut06.setCellStyle(styleC27);
                HSSFCell celldataOut07 = rows.createCell(7);
                celldataOut07.setCellValue("".equalsIgnoreCase(String.valueOf(totalAgentCommOut)) ? 0 : totalAgentCommOut.doubleValue());
                celldataOut07.setCellStyle(styleC27);
                HSSFCell celldataOut08 = rows.createCell(8);
                celldataOut08.setCellValue("".equalsIgnoreCase(String.valueOf(totalProfitOut)) ? 0 : totalProfitOut.doubleValue());
                celldataOut08.setCellStyle(styleC27);
                
                rows = sheet.createRow(count+1+i);
                HSSFCell celldataWen01 = rows.createCell(1);
                celldataWen01.setCellValue("Summary Wendy");
                celldataWen01.setCellStyle(styleC22);
                HSSFCell celldataWen02 = rows.createCell(2);
                celldataWen02.setCellValue("".equalsIgnoreCase(String.valueOf(totalPaxWendy)) ? 0 : new BigDecimal(totalPaxWendy).doubleValue());
                celldataWen02.setCellStyle(styleC28);
                HSSFCell celldataWen03 = rows.createCell(3);
                celldataWen03.setCellValue("".equalsIgnoreCase(String.valueOf(totalInvAmountWendy)) ? 0 : totalInvAmountWendy.doubleValue());
                celldataWen03.setCellStyle(styleC27);
                HSSFCell celldataWen04 = rows.createCell(4);
                celldataWen04.setCellValue("".equalsIgnoreCase(String.valueOf(totalSalePriceWendy)) ? 0 : totalSalePriceWendy.doubleValue());
                celldataWen04.setCellStyle(styleC27);
                HSSFCell celldataWen05 = rows.createCell(5);
                celldataWen05.setCellValue("".equalsIgnoreCase(String.valueOf(totalCostWendy)) ? 0 : totalCostWendy.doubleValue());
                celldataWen05.setCellStyle(styleC27);
                HSSFCell celldataWen06 = rows.createCell(6);
                celldataWen06.setCellValue("".equalsIgnoreCase(String.valueOf(totalTiccomWendy)) ? 0 : totalTiccomWendy.doubleValue());
                celldataWen06.setCellStyle(styleC27);
                HSSFCell celldataWen07 = rows.createCell(7);
                celldataWen07.setCellValue("".equalsIgnoreCase(String.valueOf(totalAgentCommWendy)) ? 0 : totalAgentCommWendy.doubleValue());
                celldataWen07.setCellStyle(styleC27);
                HSSFCell celldataWen08 = rows.createCell(8);
                celldataWen08.setCellValue("".equalsIgnoreCase(String.valueOf(totalProfitWendy)) ? 0 : totalProfitWendy.doubleValue());
                celldataWen08.setCellStyle(styleC27);

                 rows = sheet.createRow(count+2+i);
                String totalPaxWI = "SUM(C" +(count+i+1)+":C"+(count+i+2)+")";
                String totalInvAmountWI = "SUM(D" +(count+i+1)+":D"+(count+i+2)+")";
                String totalSalePriceWI = "SUM(E" + (count+i+1)+":E"+(count+i+2)+")";
                String totalCostWI = "SUM(F" + (count+i+1)+":F"+(count+i+2)+")";
                String totalTiccomWI = "SUM(G" + (count+i+1)+":G"+(count+i+2)+")";
                String totalAgentCommWI = "SUM(H" + (count+i+1)+":H"+(count+i+2)+")";
                String totalProfitWI = "SUM(I" + (count+i+1)+":I"+(count+i+2)+")";

                HSSFCell celldataWI01 = rows.createCell(1);
                celldataWI01.setCellValue("Total");
                celldataWI01.setCellStyle(styleC22);
                HSSFCell celldataWI02 = rows.createCell(2);
                celldataWI02.setCellFormula(totalPaxWI);
                celldataWI02.setCellStyle(styleC28);
                HSSFCell celldataWI03 = rows.createCell(3);
                celldataWI03.setCellFormula(totalInvAmountWI);
                celldataWI03.setCellStyle(styleC27);
                HSSFCell celldataWI04 = rows.createCell(4);
                celldataWI04.setCellFormula(totalSalePriceWI);
                celldataWI04.setCellStyle(styleC27);
                HSSFCell celldataWI05 = rows.createCell(5);
                celldataWI05.setCellFormula(totalCostWI);
                celldataWI05.setCellStyle(styleC27);
                HSSFCell celldataWI06 = rows.createCell(6);
                celldataWI06.setCellFormula(totalTiccomWI);
                celldataWI06.setCellStyle(styleC27);
                HSSFCell celldataWI07 = rows.createCell(7);
                celldataWI07.setCellFormula(totalAgentCommWI);
                celldataWI07.setCellStyle(styleC27);
                HSSFCell celldataWI08 = rows.createCell(8);
                celldataWI08.setCellFormula(totalProfitWI);
                celldataWI08.setCellStyle(styleC27);
                
            }
            for(int j =0;j<8;j++){
                sheet.autoSizeColumn(j);
            }
        }
    }
    
    public HSSFFont getHeaderFont(HSSFFont font) {

        font.setFontHeightInPoints((short) 30);
        font.setFontName("Calibri");
        font.setItalic(true);

        return font;
    }

    public HSSFFont getHeaderTable(HSSFFont font) {

        font.setFontHeightInPoints((short) 14);
        font.setFontName("Calibri");
        font.setBoldweight(font.BOLDWEIGHT_BOLD);

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
    
    public void getChangeARReport(HSSFWorkbook wb, List changeARReport){
        List<ARNirvana> listAR = changeARReport;
        String sheetName = "Sheet1";// name of sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
       
        // set Header Report (Row 1)
        HSSFCellStyle styleC1 = wb.createCellStyle();
        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cell1 = row1.createCell(1);
        cell1.setCellValue("CHANGE AR REPORT");
        styleC1.setFont(getHeaderFont(wb.createFont()));
        cell1.setCellStyle(styleC1);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B1:I1"));

        // Set align Text
        HSSFCellStyle styleC21 = wb.createCellStyle();
        styleC21.setAlignment(styleC21.ALIGN_RIGHT);
        HSSFCellStyle styleC22 = wb.createCellStyle();
        styleC22.setAlignment(styleC22.ALIGN_LEFT);
        HSSFDataFormat currency = wb.createDataFormat();
        // Set align Text
        HSSFCellStyle styleNumber = wb.createCellStyle();
        styleNumber.setAlignment(styleC21.ALIGN_RIGHT);
        styleNumber.setDataFormat(currency.getFormat("#,##0.00"));
//        styleNumber.setDataFormat(creationHelper.createDataFormat().getFormat("#,##0"));

        ARNirvana arTemp = new  ARNirvana();
        arTemp = (ARNirvana) listAR.get(0);
        // Row 2
        HSSFRow row2 = sheet.createRow(1);
        HSSFCell cell21 = row2.createCell(1);
            cell21.setCellValue("Print on : ");
            cell21.setCellStyle(styleC21);
        HSSFCell cell22 = row2.createCell(2);
            Date date = new Date();
            SimpleDateFormat sm = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = sm.format(date);
            cell22.setCellValue(strDate);
            cell22.setCellStyle(styleC22);
            sheet.addMergedRegion(CellRangeAddress.valueOf("C2:E2"));

        // Row 3
        HSSFRow row3 = sheet.createRow(2);
        HSSFCell cell31 = row3.createCell(1);
            cell31.setCellValue("Report Of : ");
            cell31.setCellStyle(styleC21);
        HSSFCell cell32 = row3.createCell(2);
            cell32.setCellValue(arTemp.getPrintofdatePage());
            cell32.setCellStyle(styleC22);
            sheet.addMergedRegion(CellRangeAddress.valueOf("C3:D3"));

        // Row 4
        HSSFRow row4 = sheet.createRow(3);
        HSSFCell cell41 = row4.createCell(1);
            cell41.setCellValue("Department : ");
            cell41.setCellStyle(styleC21);
        HSSFCell cell42 = row4.createCell(2);
            cell42.setCellValue(arTemp.getDepartmentPage());
            cell42.setCellStyle(styleC22);
            sheet.addMergedRegion(CellRangeAddress.valueOf("C4:E4"));


         // Header Table
        HSSFCellStyle styleHeader = wb.createCellStyle();
            styleHeader.setFont(getHeaderTable(wb.createFont()));
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
        
        HSSFRow row6 = sheet.createRow(8);
        HSSFCell cell61 = row6.createCell(1);
            cell61.setCellValue("No.");
            cell61.setCellStyle(styleHeader);
            sheet.autoSizeColumn(1);
        HSSFCell cell62 = row6.createCell(2);
            cell62.setCellValue("Inv No");
            cell62.setCellStyle(styleHeader);
            sheet.autoSizeColumn(2);
        HSSFCell cell63 = row6.createCell(3);
            cell63.setCellValue("AR Code");
            cell63.setCellStyle(styleHeader);
            sheet.autoSizeColumn(3);
        HSSFCell cell64 = row6.createCell(4);
            cell64.setCellValue("Inv To");
            cell64.setCellStyle(styleHeader);
            sheet.autoSizeColumn(4);
        HSSFCell cell65 = row6.createCell(5);
            cell65.setCellValue("Acc Code");
            cell65.setCellStyle(styleHeader);
            sheet.autoSizeColumn(5);
        HSSFCell cell66 = row6.createCell(6);
            cell66.setCellValue("Gross");
            cell66.setCellStyle(styleHeader);
            sheet.autoSizeColumn(6);
        HSSFCell cell67 = row6.createCell(7);
            cell67.setCellValue("Amount");
            cell67.setCellStyle(styleHeader);
            sheet.autoSizeColumn(7);
        HSSFCell cell68 = row6.createCell(8);
            cell68.setCellValue("Cur");
            cell68.setCellStyle(styleHeader);
            sheet.autoSizeColumn(8);
        
        int count = 9 + listAR.size();
        int start = 11;
        int end = 0;
        int num = 0;
        for (int r = 9 ; r < count; r++) {
             if(num <= (listAR.size()-1)){
                HSSFRow row = sheet.createRow(r);
                HSSFCell cell5 = row.createCell(1);
                    cell5.setCellValue((num+1));
                    cell5.setCellStyle(styleDetailTable);
                    sheet.autoSizeColumn(1);
                HSSFCell cell6 = row.createCell(2);
                    cell6.setCellValue(listAR.get(num).getIntreference());
                    cell6.setCellStyle(styleDetailTable);
                    sheet.autoSizeColumn(2);
                HSSFCell cell7 = row.createCell(3);
                    if(listAR.get(num).getCustomerid()!= null){
                        cell7.setCellValue(listAR.get(num).getCustomerid());
                        sheet.autoSizeColumn(3);
                    }else{
                        cell7.setCellValue("");
                        sheet.autoSizeColumn(3);
                    }
                    cell7.setCellStyle(styleDetailTable);
                    sheet.autoSizeColumn(3);
                HSSFCell cell8 = row.createCell(4);
                    if(listAR.get(num).getCustomername()!= null){
                        cell8.setCellValue(listAR.get(num).getCustomername());
                        sheet.autoSizeColumn(4);
                    }else{
                        cell8.setCellValue("");
                        sheet.autoSizeColumn(4);
                    }
                    cell8.setCellStyle(styleDetailTable);
                    sheet.autoSizeColumn(4);
                HSSFCell cell9 = row.createCell(5);
                    if(listAR.get(num).getSalesaccount1() != null){
                        cell9.setCellValue(listAR.get(num).getSalesaccount1());
                        sheet.autoSizeColumn(5);
                    }else{
                        cell9.setCellValue("");
                        sheet.autoSizeColumn(5);
                    }
                    cell9.setCellStyle(styleDetailTable);
                    sheet.autoSizeColumn(5);
                HSSFCell cell10 = row.createCell(6);
                    if(listAR.get(num).getSalesamt() != null){
                        cell10.setCellValue(listAR.get(num).getSalesamt().doubleValue());
                        sheet.autoSizeColumn(6);
                    }else{
                        cell10.setCellValue(0.00);
                        sheet.autoSizeColumn(6);
                    }
                    cell10.setCellStyle(styleDetailTableNumber);
                    sheet.autoSizeColumn(6);
                HSSFCell cell11 = row.createCell(7);
                    if(listAR.get(num).getAramt() != null){
                        cell11.setCellValue(listAR.get(num).getAramt().doubleValue());
                        sheet.autoSizeColumn(7);
                    }else{
                        cell11.setCellValue(0.00);
                        sheet.autoSizeColumn(7);
                    }
                    cell11.setCellStyle(styleDetailTableNumber);
                HSSFCell cell12 = row.createCell(8);
                    cell12.setCellValue(listAR.get(num).getCurrencyid());
                    cell12.setCellStyle(styleDetailTable);
                    sheet.autoSizeColumn(8);
                num++;
             }
        }
        
         HSSFRow row = sheet.createRow(count);
            row.createCell(1).setCellStyle(styleDetailTableBorderBottom);
            row.createCell(2).setCellStyle(styleDetailTableBorderBottom);
            row.createCell(3).setCellStyle(styleDetailTableBorderBottom);
            row.createCell(4).setCellStyle(styleDetailTableBorderBottom);
            row.createCell(5).setCellStyle(styleDetailTableBorderBottom);
            row.createCell(6).setCellStyle(styleDetailTableBorderBottom);
            row.createCell(7).setCellStyle(styleDetailTableBorderBottom);
            row.createCell(8).setCellStyle(styleDetailTableBorderBottom);
            
    }
    
    public void getBillAirAgentReportSummary(HSSFWorkbook wb, List BillAirAgent){
        List<ListBillAirAgent> listAirAgent = new ArrayList<ListBillAirAgent>();
        if(BillAirAgent != null && BillAirAgent.size() != 0){
            listAirAgent = BillAirAgent;
        }else{
            listAirAgent = null;
        }
        List<BillAirAgent> listAgent = new ArrayList<BillAirAgent>();
        List<BillAirAgentRefund> listAgentRefund = new ArrayList<BillAirAgentRefund>();
        if(listAirAgent != null && listAirAgent.size() != 0){
            listAgent = listAirAgent.get(0).getBillAirAgent();
            listAgentRefund = listAirAgent.get(0).getBillAirAgentRefund();
        }else{
            listAgent = null;
            listAgentRefund = null;
        }
        

        
        String sheetName = "Summary";// name of sheet
        String sheetName1 = "Detail";
        String sheetName2 = "Refund";
        HSSFSheet sheet = wb.createSheet(sheetName);
        HSSFSheet sheet1 = wb.createSheet(sheetName1);
        HSSFSheet sheet2 = wb.createSheet(sheetName2);
        
        HSSFRow row111 = sheet1.createRow(0);
        HSSFCell cell1111 = row111.createCell(0);
        cell1111.setCellValue("detail");
        
        
        // set Header Report (Row 1)
        HSSFCellStyle styleC1 = wb.createCellStyle();
        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cell1 = row1.createCell(0);
        cell1.setCellValue("Bill Agent Air Summary");
        styleC1.setFont(getHeaderFont(wb.createFont()));
        cell1.setCellStyle(styleC1);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:F1"));

        // Set align Text
        HSSFCellStyle styleAlignRight = wb.createCellStyle(); // use
                styleAlignRight.setAlignment(styleAlignRight.ALIGN_RIGHT);
        HSSFCellStyle styleAlignLeft = wb.createCellStyle(); // use
                styleAlignLeft.setAlignment(styleAlignLeft.ALIGN_LEFT);
        HSSFCellStyle styleAlignCenter = wb.createCellStyle();
                styleAlignCenter.setAlignment(styleAlignCenter.ALIGN_CENTER);
        HSSFCellStyle styleBorderLeft = wb.createCellStyle();
                styleBorderLeft.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        HSSFCellStyle styleBorderRight = wb.createCellStyle();
                styleBorderRight.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        HSSFDataFormat currency = wb.createDataFormat();
        HSSFCellStyle styleNumber = wb.createCellStyle();
                styleNumber.setAlignment(styleNumber.ALIGN_RIGHT);
                styleNumber.setDataFormat(currency.getFormat("#,##0.00"));
        HSSFCellStyle styleNumberBorderRight = wb.createCellStyle();
                styleNumberBorderRight.setAlignment(styleNumberBorderRight.ALIGN_RIGHT);
                styleNumberBorderRight.setDataFormat(currency.getFormat("#,##0.00"));
                styleNumberBorderRight.setBorderRight(styleNumberBorderRight.BORDER_THIN);

        // line table
        HSSFCellStyle styleAlignLeftBorderTopRight = wb.createCellStyle(); // use
                styleAlignLeftBorderTopRight.setAlignment(styleAlignLeftBorderTopRight.ALIGN_LEFT);
                styleAlignLeftBorderTopRight.setBorderTop(styleAlignLeftBorderTopRight.BORDER_THIN);
                styleAlignLeftBorderTopRight.setBorderRight(styleAlignLeftBorderTopRight.BORDER_THIN);
        HSSFCellStyle styleAlignLeftBorderTopLeft = wb.createCellStyle(); // use
                styleAlignLeftBorderTopLeft.setAlignment(styleAlignLeftBorderTopLeft.ALIGN_LEFT);
                styleAlignLeftBorderTopLeft.setBorderTop(styleAlignLeftBorderTopLeft.BORDER_THIN);
                styleAlignLeftBorderTopLeft.setBorderLeft(styleAlignLeftBorderTopLeft.BORDER_THIN);
        HSSFCellStyle styleBorderTop = wb.createCellStyle(); // use
                styleBorderTop.setBorderTop(styleBorderTop.BORDER_THIN);
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
        HSSFCellStyle styleAlignRightBorderTopBottom = wb.createCellStyle();
                styleAlignRightBorderTopBottom.setAlignment(styleAlignRightBorderTopBottom.ALIGN_RIGHT);
                styleAlignRightBorderTopBottom.setBorderTop(styleAlignRightBorderTopBottom.BORDER_THIN);
                styleAlignRightBorderTopBottom.setBorderBottom(styleAlignRightBorderTopBottom.BORDER_THIN);
        HSSFCellStyle styleAlignRightBorderTopBottomRight = wb.createCellStyle();
                styleAlignRightBorderTopBottomRight.setAlignment(styleAlignRightBorderTopBottomRight.ALIGN_RIGHT);
                styleAlignRightBorderTopBottomRight.setBorderTop(styleAlignRightBorderTopBottomRight.BORDER_THIN);
                styleAlignRightBorderTopBottomRight.setBorderBottom(styleAlignRightBorderTopBottomRight.BORDER_THIN);
                styleAlignRightBorderTopBottomRight.setBorderRight(styleAlignRightBorderTopBottomRight.BORDER_THIN);
        HSSFCellStyle styleAlignRightBorderAllHeaderTable = wb.createCellStyle();
                styleAlignRightBorderAllHeaderTable.setFont(getHeaderTable(wb.createFont()));
                styleAlignRightBorderAllHeaderTable.setAlignment(styleAlignRightBorderAllHeaderTable.ALIGN_CENTER);
                styleAlignRightBorderAllHeaderTable.setBorderTop(styleAlignRightBorderAllHeaderTable.BORDER_THIN);
                styleAlignRightBorderAllHeaderTable.setBorderBottom(styleAlignRightBorderAllHeaderTable.BORDER_THIN);
                styleAlignRightBorderAllHeaderTable.setBorderRight(styleAlignRightBorderAllHeaderTable.BORDER_THIN);
                styleAlignRightBorderAllHeaderTable.setBorderLeft(styleAlignRightBorderAllHeaderTable.BORDER_THIN);
        HSSFCellStyle styleAlignRightBorderAll = wb.createCellStyle();
                styleAlignRightBorderAll.setAlignment(styleAlignRightBorderAll.ALIGN_LEFT);
                styleAlignRightBorderAll.setBorderTop(styleAlignRightBorderAll.BORDER_THIN);
                styleAlignRightBorderAll.setBorderBottom(styleAlignRightBorderAll.BORDER_THIN);
                styleAlignRightBorderAll.setBorderRight(styleAlignRightBorderAll.BORDER_THIN);
                styleAlignRightBorderAll.setBorderLeft(styleAlignRightBorderAll.BORDER_THIN);
        HSSFCellStyle styleAlignRightBorderAllNumber = wb.createCellStyle();
                styleAlignRightBorderAllNumber.setAlignment(styleAlignRightBorderAllNumber.ALIGN_RIGHT);
                styleAlignRightBorderAllNumber.setDataFormat(currency.getFormat("#,##0.00"));
                styleAlignRightBorderAllNumber.setBorderTop(styleAlignRightBorderAllNumber.BORDER_THIN);
                styleAlignRightBorderAllNumber.setBorderBottom(styleAlignRightBorderAllNumber.BORDER_THIN);
                styleAlignRightBorderAllNumber.setBorderRight(styleAlignRightBorderAllNumber.BORDER_THIN);
                styleAlignRightBorderAllNumber.setBorderLeft(styleAlignRightBorderAllNumber.BORDER_THIN);
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
        
                
        BillAirAgent bil = new BillAirAgent();
        if((listAgent != null) && (listAgent.size() != 0)){
            bil = (BillAirAgent)listAgent.get(0);
        }
        // Row 2
        HSSFRow row2 = sheet.createRow(1);
        HSSFCell cell21 = row2.createCell(0);
        cell21.setCellValue("Agent All : ");
        cell21.setCellStyle(styleAlignRight);
        HSSFCell cell22 = row2.createCell(1);
        cell22.setCellValue(bil.getAgentPage());
        cell22.setCellStyle(styleAlignLeft);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B2:D2"));
        HSSFCell cell23 = row2.createCell(4);
        cell23.setCellValue("Print By : ");
        cell23.setCellStyle(styleAlignRight);
        HSSFCell cell24 = row2.createCell(5);
        cell24.setCellValue(bil.getPrintbyPage());
        cell24.setCellStyle(styleAlignLeft);

        // Row 3
        HSSFRow row3 = sheet.createRow(2);
        HSSFCell cell31 = row3.createCell(0);
        cell31.setCellValue("Issue Date : ");
        cell31.setCellStyle(styleAlignRight);
        HSSFCell cell32 = row3.createCell(1);
        cell32.setCellValue(bil.getIssuedatePage());
        cell32.setCellStyle(styleAlignLeft);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B3:D3"));
        HSSFCell cell33 = row3.createCell(4);
        cell33.setCellValue("Page : ");
        cell33.setCellStyle(styleAlignRight);
        HSSFCell cell34 = row3.createCell(5);
        cell34.setCellValue("1");
        cell34.setCellStyle(styleAlignLeft);

        // Row 4
        HSSFRow row4 = sheet.createRow(3);
        HSSFCell cell41 = row4.createCell(0);
        cell41.setCellValue("Invoice Date : ");
        cell41.setCellStyle(styleAlignRight);
        HSSFCell cell42 = row4.createCell(1);
        cell42.setCellValue(bil.getInvoicedatePage());
        cell42.setCellStyle(styleAlignLeft);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B4:D4"));

        // Row 5
        HSSFRow row5 = sheet.createRow(4);
        HSSFCell cell51 = row5.createCell(0);
        cell51.setCellValue("Payment Type : ");
        cell51.setCellStyle(styleAlignRight);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B5:D5"));
        HSSFCell cell52 = row5.createCell(1);
        cell52.setCellValue(bil.getPaymenttypePage());
        cell52.setCellStyle(styleAlignLeft);

        // Body Table
        BigDecimal sumSalePrice = new BigDecimal(0);
        BigDecimal sumAmountAir = new BigDecimal(0);
        BigDecimal sumComPay =  new BigDecimal(0);
        BigDecimal sumComReceive =  new BigDecimal(0);
        BigDecimal sumTotalComRefundReceive =  new BigDecimal(0);
        BigDecimal sumTotalPayment =  new BigDecimal(0);
        BigDecimal sumTotalCompay =  new BigDecimal(0);
        BigDecimal sumPayRefundAmount =  new BigDecimal(0);
        BigDecimal vatComPay =  new BigDecimal(0);
        BigDecimal vatReceive =  new BigDecimal(0);
        BigDecimal totalCom =  new BigDecimal(0);
        BigDecimal balancePayment =  new BigDecimal(0);
        BigDecimal checkResult =  new BigDecimal(0);
        BigDecimal midValue =  new BigDecimal(0);
        BigDecimal withHoldingTax =  new BigDecimal(0);
        
        for (int i = 0; i < listAgent.size(); i++) {
            sumSalePrice = sumSalePrice.add(new BigDecimal(listAgent.get(i).getSaleprice()));
            sumAmountAir = sumAmountAir.add(new BigDecimal(listAgent.get(i).getAmountair()));
            sumComPay = sumComPay.add(new BigDecimal(listAgent.get(i).getCompay()));
            sumComReceive = sumComReceive.add(new BigDecimal(listAgent.get(i).getAgentcom()));
            sumTotalComRefundReceive = sumTotalComRefundReceive.add(new BigDecimal(listAgent.get(i).getAgentcomrefund()));
            sumPayRefundAmount = sumPayRefundAmount.add(new BigDecimal(listAgent.get(i).getPaycusrefund()));
            System.out.println("Sale Price : " + listAgent.get(i).getSaleprice() + "  Sum Sale Price : " + sumSalePrice);
            System.out.println("Amount Air : " + listAgent.get(i).getAmountair() + "  Sum Amount Air : " + sumAmountAir);
            System.out.println("Com Pay : " + listAgent.get(i).getCompay() + "  Sum Com Pay : " + sumComPay);
            System.out.println("Com Receive : " + listAgent.get(i).getAgentcom() + "  Sum Com Receive : " + sumComReceive);
            System.out.println("Com Reefund Receive : " + listAgent.get(i).getAgentcomrefund() + "  Sum Reefund Receive : " + sumTotalComRefundReceive);
            System.out.println("Pay Refund Amount : " + listAgent.get(i).getPaycusrefund() + "  Sum Refund Amount : " + sumPayRefundAmount);
        }
        DecimalFormat df = new DecimalFormat("#,###.00");
        sumTotalPayment = sumSalePrice.subtract(sumTotalComRefundReceive);
        sumTotalCompay = sumComPay.subtract(sumTotalComRefundReceive);
        vatComPay = sumTotalCompay.multiply(new BigDecimal(0.07));
        vatReceive = sumComReceive.multiply(new BigDecimal(0.07));
        totalCom = sumComPay.subtract(sumComReceive);
        balancePayment = sumTotalPayment.add(vatComPay);
        balancePayment = balancePayment.add(vatReceive);
        balancePayment = balancePayment.subtract(sumPayRefundAmount);
        checkResult = sumTotalCompay.add(vatComPay);
        midValue =  checkResult.add(balancePayment);
        midValue = midValue.add(sumPayRefundAmount);
        withHoldingTax = sumTotalCompay.add(vatComPay);
        withHoldingTax = withHoldingTax.multiply(new BigDecimal(100));
        withHoldingTax = withHoldingTax.divide(new BigDecimal(107),MathContext.DECIMAL128);
        withHoldingTax = withHoldingTax.multiply(new BigDecimal(0.3));
        System.out.println(">>>>>>>>>>> sumTotalPayment : " + df.format(sumTotalPayment));
        System.out.println(">>>>>>>>>>> sumTotalCompay : " + df.format(sumTotalCompay));
        System.out.println(">>>>>>>>>>> vatComPay : " + df.format(vatComPay));
        System.out.println(">>>>>>>>>>> totalCom : " + df.format(totalCom));
        System.out.println(">>>>>>>>>>> balancePayment : " + df.format(balancePayment));
        System.out.println(">>>>>>>>>>> checkResult : " + df.format(checkResult));
        System.out.println(">>>>>>>>>>> midValue : " + df.format(midValue));
        System.out.println(">>>>>>>>>>> withHoldingTax : " + df.format(withHoldingTax));
        
        HSSFRow row8 = sheet.createRow(7);
                HSSFCell cell81 = row8.createCell(0);
                        row8.createCell(1).setCellStyle(styleBorderTop);
                        row8.createCell(2).setCellStyle(styleBorderTop);
                        row8.createCell(3).setCellStyle(styleBorderTop);
                        row8.createCell(4).setCellStyle(styleBorderTop);
                        row8.createCell(5).setCellStyle(styleBorderTop);
                        row8.createCell(6).setCellStyle(styleBorderTop);
                        row8.createCell(7).setCellStyle(styleAlignLeftBorderTopRight);
                        cell81.setCellStyle(styleBorderTop);
                        cell81.setCellValue("All");
                        sheet.autoSizeColumn(0);
                        sheet.addMergedRegion(CellRangeAddress.valueOf("A8:H8"));
                HSSFCell cell82 = row8.createCell(9);
                        cell82.setCellStyle(styleAlignLeftBorderTopLeft);
                        cell82.setCellValue("Amount Air Sale      ");
                        sheet.autoSizeColumn(9);
                        row8.createCell(10).setCellStyle(styleBorderTop);
                HSSFCell cell83 = row8.createCell(11);
                        cell83.setCellValue("Com Pay      ");
                        cell83.setCellStyle(styleAlignLeftBorderTopRight);
                        sheet.autoSizeColumn(11);
        HSSFRow row9 = sheet.createRow(8);
                HSSFCell cell91 = row9.createCell(0);
                        cell91.setCellValue("Total Sale Price : ");
                        cell91.setCellStyle(styleAlignRight);
                        sheet.addMergedRegion(CellRangeAddress.valueOf("A9:E9"));
                        sheet.autoSizeColumn(0);
                HSSFCell cell92 = row9.createCell(5);
                        cell92.setCellValue(df.format(sumSalePrice));
                        cell92.setCellStyle(styleAlignRight);
                        sheet.addMergedRegion(CellRangeAddress.valueOf("F9:H9"));
                        sheet.autoSizeColumn(5);
                        row9.createCell(7).setCellStyle(styleAlignRightBorderRight);
                HSSFCell cell93 = row9.createCell(9);
                        cell93.setCellStyle(styleAlignRightBorderLeft);
                        cell93.setCellValue(df.format(sumAmountAir));
                        sheet.autoSizeColumn(9);
                        row9.createCell(10).setCellValue("");
                HSSFCell cell94 = row9.createCell(11);
                        cell94.setCellValue(df.format(sumComPay));
                        cell94.setCellStyle(styleAlignRightBorderRight);
                        sheet.autoSizeColumn(11);
        HSSFRow row10 = sheet.createRow(9);
                HSSFCell cell101 = row10.createCell(0);
                        cell101.setCellValue("Total Com Refund Receive : ");
                        cell101.setCellStyle(styleAlignRight);
                        sheet.addMergedRegion(CellRangeAddress.valueOf("A10:E10"));
                        sheet.autoSizeColumn(0);
                HSSFCell cell102 = row10.createCell(5);
                        cell102.setCellValue(df.format(sumTotalComRefundReceive));
                        cell102.setCellStyle(styleAlignRight);
                        sheet.addMergedRegion(CellRangeAddress.valueOf("F10:H10"));
                        sheet.autoSizeColumn(5);
                        row10.createCell(7).setCellStyle(styleAlignRightBorderRight);
                HSSFCell cell103= row10.createCell(9);
                        cell103.setCellStyle(styleAlignRightBorderLeft);
                        cell103.setCellValue("");
                        sheet.autoSizeColumn(9);
                        row9.createCell(10).setCellValue("");
                HSSFCell cell104 = row10.createCell(11);
                        cell104.setCellValue("");
                        cell104.setCellStyle(styleAlignRightBorderRight);
                        sheet.autoSizeColumn(11);
        HSSFRow row11 = sheet.createRow(10);
                HSSFCell cell111 = row11.createCell(0);
                        cell111.setCellValue("Total Payment : ");
                        cell111.setCellStyle(styleAlignRight);
                        sheet.addMergedRegion(CellRangeAddress.valueOf("A11:E11"));
                        sheet.autoSizeColumn(0);
                HSSFCell cell112 = row11.createCell(5);
                        cell112.setCellValue(df.format(sumTotalPayment));
                        cell112.setCellStyle(styleAlignRight);
                        sheet.addMergedRegion(CellRangeAddress.valueOf("F11:H11"));
                        sheet.autoSizeColumn(5);
                        row11.createCell(7).setCellStyle(styleAlignRightBorderRight);
                HSSFCell cell113= row11.createCell(9);
                        cell113.setCellStyle(styleAlignRightBorderLeft);
                        cell113.setCellValue("");
                        sheet.autoSizeColumn(9);
                        row9.createCell(10).setCellValue("");
                HSSFCell cell114 = row11.createCell(11);
                        cell114.setCellValue("Com Receive       ");
                        cell114.setCellStyle(styleAlignLeftBorderRight);
                        sheet.autoSizeColumn(11);
        HSSFRow row12 = sheet.createRow(11);
                HSSFCell cell121 = row12.createCell(0);
                        row12.createCell(1).setCellStyle(styleBorderBottom);
                        row12.createCell(2).setCellStyle(styleBorderBottom);
                        row12.createCell(3).setCellStyle(styleBorderBottom);
                        row12.createCell(4).setCellStyle(styleBorderBottom);
                        row12.createCell(5).setCellStyle(styleBorderBottom);
                        row12.createCell(6).setCellStyle(styleBorderBottom);
                        row12.createCell(7).setCellStyle(styleAlignRightBorderBottomRight);
                        cell121.setCellStyle(styleBorderBottom);
                        sheet.autoSizeColumn(0);
                        sheet.addMergedRegion(CellRangeAddress.valueOf("A12:H12"));
                HSSFCell cell122= row12.createCell(9);
                        cell122.setCellStyle(styleAlignRightBorderLeft);
                        cell122.setCellValue("");
                        sheet.autoSizeColumn(9);
                        row9.createCell(10).setCellValue("");
                HSSFCell cell123 = row12.createCell(11);
                        cell123.setCellValue(df.format(sumComReceive));
                        cell123.setCellStyle(styleAlignRightBorderRight);
                        sheet.autoSizeColumn(11);
        HSSFRow row13 = sheet.createRow(12);
                HSSFCell cell131= row13.createCell(9);
                        cell131.setCellStyle(styleAlignRightBorderLeft);
                        cell131.setCellValue("");
                        sheet.autoSizeColumn(9);
                        row9.createCell(10).setCellValue("");
                HSSFCell cell132 = row13.createCell(11);
                        cell132.setCellValue("Total Com Pay    ");
                        cell132.setCellStyle(styleAlignLeftBorderRight);
                        sheet.autoSizeColumn(11);
        HSSFRow row14 = sheet.createRow(13);
                HSSFCell cell141 = row14.createCell(0);
                        row14.createCell(1).setCellStyle(styleBorderTop);
                        row14.createCell(2).setCellStyle(styleBorderTop);
                        row14.createCell(3).setCellStyle(styleBorderTop);
                        row14.createCell(4).setCellStyle(styleBorderTop);
                        row14.createCell(5).setCellStyle(styleBorderTop);
                        row14.createCell(6).setCellStyle(styleBorderTop);
                        row14.createCell(7).setCellStyle(styleAlignLeftBorderTopRight);
                        cell141.setCellStyle(styleBorderTop);
                        cell141.setCellValue("Less");
                        sheet.autoSizeColumn(0);
                        sheet.addMergedRegion(CellRangeAddress.valueOf("A12:H12"));
                HSSFCell cell143 = row14.createCell(9);
                        cell143.setCellStyle(styleAlignRightBorderLeft);
                        cell143.setCellValue("    ");
                        sheet.autoSizeColumn(9);
                        row14.createCell(10).setCellValue("");
                HSSFCell cell144 = row14.createCell(11);
                        cell144.setCellValue(df.format(sumTotalCompay));
                        cell144.setCellStyle(styleAlignRightBorderRight);
                        sheet.autoSizeColumn(11);
        HSSFRow row15 = sheet.createRow(14);
                HSSFCell cell151 = row15.createCell(0);
                        cell151.setCellValue("Com Pay : ");
                        cell151.setCellStyle(styleAlignRight);
                        sheet.autoSizeColumn(0);
                HSSFCell cell1511 = row15.createCell(1);
                        cell1511.setCellValue(df.format(sumComPay.subtract(BigDecimal.ONE)));
                        cell1511.setCellStyle(styleAlignRight);
                        sheet.addMergedRegion(CellRangeAddress.valueOf("B15:D15"));
                        sheet.autoSizeColumn(1);
                HSSFCell cell152 = row15.createCell(4);
                        cell152.setCellValue("Vat Pay : ");
                        cell152.setCellStyle(styleAlignRight);
                        sheet.autoSizeColumn(4);
                HSSFCell cell1521 = row15.createCell(5);
                        cell1521.setCellValue(df.format(vatComPay.subtract(BigDecimal.ONE)));
                        cell1521.setCellStyle(styleAlignRight);
                        row15.createCell(7).setCellStyle(styleAlignRightBorderRight);
                        sheet.autoSizeColumn(5);
                        sheet.addMergedRegion(CellRangeAddress.valueOf("F15:H15"));
                HSSFCell cell153 = row15.createCell(9);
                        cell153.setCellStyle(styleAlignRightBorderLeft);
                        cell153.setCellValue("    ");
                        sheet.autoSizeColumn(9);
                        row15.createCell(10).setCellValue("");
                HSSFCell cell154 = row15.createCell(11);
                        cell154.setCellValue("    ");
                        cell154.setCellStyle(styleAlignLeftBorderRight);
                        sheet.autoSizeColumn(11);
        HSSFRow row16 = sheet.createRow(15);
                HSSFCell cell161 = row16.createCell(0);
                        cell161.setCellValue("Com Receive : ");
                        cell161.setCellStyle(styleAlignRight);
                        sheet.autoSizeColumn(0);
                HSSFCell cell1611 = row16.createCell(1);
                        cell1611.setCellValue(df.format(sumComReceive));
                        cell1611.setCellStyle(styleAlignRight);
                        sheet.addMergedRegion(CellRangeAddress.valueOf("B16:D16"));
                        sheet.autoSizeColumn(1);
                HSSFCell cell162 = row16.createCell(4);
                        cell162.setCellValue("Vat Receive : ");
                        cell162.setCellStyle(styleAlignRight);
                        sheet.autoSizeColumn(4);
                HSSFCell cell1621 = row16.createCell(5);
                        cell1621.setCellValue(df.format(vatReceive));
                        cell1621.setCellStyle(styleAlignRight);
                        sheet.autoSizeColumn(5);
                        row16.createCell(7).setCellStyle(styleAlignRightBorderRight);
                        sheet.addMergedRegion(CellRangeAddress.valueOf("F16:H16"));
                HSSFCell cell163 = row16.createCell(9);
                        cell163.setCellStyle(styleAlignRightBorderLeft);
                        cell163.setCellValue("    ");
                        sheet.autoSizeColumn(9);
                        row16.createCell(10).setCellValue("");
                HSSFCell cell164 = row16.createCell(11);
                        cell164.setCellValue("Vat Com Pay    ");
                        cell164.setCellStyle(styleAlignLeftBorderRight);
                        sheet.autoSizeColumn(11);
        HSSFRow row17 = sheet.createRow(16);
                HSSFCell cell171 = row17.createCell(4);
                        cell171.setCellValue("Pay Refund Amount : ");
                        cell171.setCellStyle(styleAlignRight);
                        sheet.autoSizeColumn(4);
//					sheet.addMergedRegion(CellRangeAddress.valueOf("A17:E17"));
                HSSFCell cell172 = row17.createCell(5);
                        cell172.setCellValue(df.format(sumPayRefundAmount));
                        cell172.setCellStyle(styleAlignRight);
                        row17.createCell(7).setCellStyle(styleAlignRightBorderRight);
                        sheet.autoSizeColumn(5);
                        sheet.addMergedRegion(CellRangeAddress.valueOf("F17:H17"));
                HSSFCell cell173= row17.createCell(9);
                        cell173.setCellStyle(styleAlignRightBorderLeft);
                        cell173.setCellValue("");
                        sheet.autoSizeColumn(9);
                        row9.createCell(10).setCellValue("");
                HSSFCell cell174 = row17.createCell(11);
                        cell174.setCellValue(df.format(vatComPay));
                        cell174.setCellStyle(styleAlignRightBorderRight);
                        sheet.autoSizeColumn(11);
        HSSFRow row18 = sheet.createRow(17);
                HSSFCell cell181 = row18.createCell(0);
                        row18.createCell(1).setCellStyle(styleBorderBottom);
                        row18.createCell(2).setCellStyle(styleBorderBottom);
                        row18.createCell(3).setCellStyle(styleBorderBottom);
                        row18.createCell(4).setCellStyle(styleBorderBottom);
                        row18.createCell(5).setCellStyle(styleBorderBottom);
                        row18.createCell(6).setCellStyle(styleBorderBottom);
                        row18.createCell(7).setCellStyle(styleAlignRightBorderBottomRight);
                        cell181.setCellStyle(styleBorderBottom);
                        sheet.autoSizeColumn(0);
                        sheet.addMergedRegion(CellRangeAddress.valueOf("A18:H18"));
                HSSFCell cell182= row18.createCell(9);
                        cell182.setCellStyle(styleAlignRightBorderBottomLeft);
                        cell182.setCellValue("");
                        sheet.autoSizeColumn(9);
                        row18.createCell(10).setCellStyle(styleBorderBottom);
                HSSFCell cell183 = row18.createCell(11);
                        cell183.setCellValue("");
                        cell183.setCellStyle(styleAlignRightBorderBottomRight);
                        sheet.autoSizeColumn(11);
        HSSFRow row20 = sheet.createRow(19);
                HSSFCell cell201 = row20.createCell(0);
                        cell201.setCellValue(" Total Com : ");
                        cell201.setCellStyle(styleAlignRightBorderTopBottom);
                        sheet.autoSizeColumn(0);
                HSSFCell cell1202 = row20.createCell(1);
                        cell1202.setCellValue(df.format(totalCom));
                        cell1202.setCellStyle(styleAlignRightBorderTopBottom);
                        row20.createCell(2).setCellStyle(styleAlignRightBorderTopBottom);
                        row20.createCell(3).setCellStyle(styleAlignRightBorderTopBottom);
                        sheet.addMergedRegion(CellRangeAddress.valueOf("B20:D20"));
                        sheet.autoSizeColumn(1);
                HSSFCell cell1203 = row20.createCell(4);
                        cell1203.setCellValue("Balance Payment : ");
                        cell1203.setCellStyle(styleAlignRightBorderTopBottom);
                        sheet.autoSizeColumn(4);
                HSSFCell cell1204 = row20.createCell(5);
                        cell1204.setCellValue(df.format(balancePayment));
                        cell1204.setCellStyle(styleAlignRightBorderTopBottom);
                        sheet.autoSizeColumn(5);
                        row20.createCell(6).setCellStyle(styleAlignRightBorderTopBottom);
                        row20.createCell(7).setCellStyle(styleAlignRightBorderTopBottom);
                        sheet.addMergedRegion(CellRangeAddress.valueOf("F20:H20"));
                HSSFCell cell1205 = row20.createCell(8);
                        cell1205.setCellStyle(styleAlignRightBorderTopBottom);
                        cell1205.setCellValue(df.format(midValue));
                        sheet.autoSizeColumn(8);
                        row20.createCell(9).setCellStyle(styleAlignRightBorderTopBottom);
                        sheet.addMergedRegion(CellRangeAddress.valueOf("I20:J20"));
                HSSFCell cell206 = row20.createCell(10);
                        cell206.setCellValue(df.format(checkResult));
                        cell206.setCellStyle(styleAlignRightBorderTopBottom);
                        sheet.autoSizeColumn(10);
                        sheet.addMergedRegion(CellRangeAddress.valueOf("K20:L20"));
                        row20.createCell(11).setCellStyle(styleAlignRightBorderTopBottomRight);
        HSSFRow row21 = sheet.createRow(20);				
                HSSFCell cell1211 = row21.createCell(9);
                        cell1211.setCellStyle(styleAlignRight);
                        cell1211.setCellValue("Witholding Tax : ");
                        sheet.autoSizeColumn(9);
                HSSFCell cell212 = row21.createCell(10);
                        cell212.setCellValue(df.format(withHoldingTax));
                        cell212.setCellStyle(styleAlignRight);
                        sheet.autoSizeColumn(10);
                        sheet.addMergedRegion(CellRangeAddress.valueOf("K21:L21"));
            
     // Sheet Detail (2)************************************************************************************************
     // set Header Report (Row 1)
        HSSFCellStyle styleC11 = wb.createCellStyle();
        HSSFRow row01 = sheet1.createRow(0);
        HSSFCell cell01 = row01.createCell(0);
        cell01.setCellValue("Bill Agent Air");
        styleC11.setFont(getHeaderFont(wb.createFont()));
        cell01.setCellStyle(styleC11);
        sheet1.addMergedRegion(CellRangeAddress.valueOf("A1:F1"));

        // Set align Text
        HSSFCellStyle styleC21 = wb.createCellStyle();
        styleC21.setAlignment(styleC21.ALIGN_RIGHT);
        HSSFCellStyle styleC22 = wb.createCellStyle();
        styleC22.setAlignment(styleC22.ALIGN_LEFT);

        // Row 2
        HSSFRow row02 = sheet1.createRow(1);
        HSSFCell cell021 = row02.createCell(0);
            cell021.setCellValue("Agent All : ");
            cell021.setCellStyle(styleC21);
        HSSFCell cell022 = row02.createCell(1);
            cell022.setCellValue(bil.getAgentPage());
            cell022.setCellStyle(styleC22);
            sheet1.addMergedRegion(CellRangeAddress.valueOf("B2:D2"));
        HSSFCell cell023 = row02.createCell(4);
            cell023.setCellValue("Print By : ");
            cell023.setCellStyle(styleC21);
        HSSFCell cell024 = row02.createCell(5);
            cell024.setCellValue(bil.getPrintbyPage());
            cell024.setCellStyle(styleC22);

        // Row 3
        HSSFRow row03 = sheet1.createRow(2);
        HSSFCell cell031 = row03.createCell(0);
            cell031.setCellValue("Issue Date : ");
            cell031.setCellStyle(styleC21);
        HSSFCell cell032 = row03.createCell(1);
            cell032.setCellValue(bil.getIssuedatePage());
            cell032.setCellStyle(styleC22);
            sheet1.addMergedRegion(CellRangeAddress.valueOf("B3:D3"));
        HSSFCell cell033 = row03.createCell(4);
            cell033.setCellValue("Page : ");
            cell033.setCellStyle(styleC21);
        HSSFCell cell034 = row03.createCell(5);
            cell034.setCellValue("1");
            cell034.setCellStyle(styleC22);

        // Row 4
        HSSFRow row04 = sheet1.createRow(3);
        HSSFCell cell041 = row04.createCell(0);
            cell041.setCellValue("Invoice Date : ");
            cell041.setCellStyle(styleC21);
        HSSFCell cell042 = row04.createCell(1);
            cell042.setCellValue(bil.getInvoicedatePage());
            cell042.setCellStyle(styleC22);
            sheet1.addMergedRegion(CellRangeAddress.valueOf("B4:D4"));

        // Row 5
        HSSFRow row05 = sheet1.createRow(4);
        HSSFCell cell051 = row05.createCell(0);
            cell051.setCellValue("Payment Type : ");
            cell051.setCellStyle(styleC21);
            sheet1.addMergedRegion(CellRangeAddress.valueOf("B5:D5"));
        HSSFCell cell052 = row05.createCell(1);
            cell052.setCellValue(bil.getPaymenttypePage());
            cell052.setCellStyle(styleC22);

         // Header Table
        HSSFRow row6 = sheet1.createRow(8);
        HSSFCell cell61 = row6.createCell(0);
            cell61.setCellValue("Invoice No.");
            cell61.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(0);
        HSSFCell cell62 = row6.createCell(1);
            cell62.setCellValue("Invoice Date");
            cell62.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(1);
        HSSFCell cell63 = row6.createCell(2);
            cell63.setCellValue("Customer");
            sheet1.autoSizeColumn(2);
            cell63.setCellStyle(styleAlignRightBorderAllHeaderTable);
        HSSFCell cell64 = row6.createCell(3);
            cell64.setCellValue("Ticket No.");
            cell64.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(3);
        HSSFCell cell65 = row6.createCell(4);
            cell65.setCellValue("Rounting");
            cell65.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(4);
        HSSFCell cell66 = row6.createCell(5);
            cell66.setCellValue("Sale Price");
            cell66.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(5);
        HSSFCell cell67 = row6.createCell(6);
            cell67.setCellValue("Net");
            cell67.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(6);
        HSSFCell cell68 = row6.createCell(7);
            cell68.setCellValue("Service");
            cell68.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(7);
        HSSFCell cell69 = row6.createCell(8);
            cell69.setCellValue("Vat");
            cell69.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(8);
        HSSFCell cell70 = row6.createCell(9);
            cell70.setCellValue("Amount Air");
            cell70.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(9);
        HSSFCell cell71 = row6.createCell(10);
            cell71.setCellValue("Com pay");
            cell71.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(10);
        HSSFCell cell72 = row6.createCell(11);
            cell72.setCellValue("Vat");
            cell72.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(11);
        HSSFCell cell73 = row6.createCell(12);
            cell73.setCellValue("Receive");
            cell73.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(11);
            
        //Detail of Table
        for (int r = 0 ; r < listAgent.size(); r++) {
            System.out.println("Size " + (r)+" : " + listAgent.get(r).getAgentname() );
        }
        
        int count = 9 + listAgent.size();
        int start = 11;
        int end = 0;
        int num = 0;

	for (int r = 9 ; r < count; r++) {
            if(num <= (listAgent.size()-1)){
                if(num != 0){ // Check not row first
                    String temp = listAgent.get(num-1).getAgentname();
                    if(temp.equals(listAgent.get(num).getAgentname())){ // equal type	
                        System.out.println("Num : " + num + " Last Row : " + (listAgent.size()-1));
                        if(num  != (listAgent.size()-1)){ // check not last row
                            HSSFRow row = sheet1.createRow(r);
                            createCell(row,listAgent,num,styleAlignRightBorderAllNumber,styleAlignRightBorderAll);
                            sheet1.autoSizeColumn(13);
                            num++; 
                        }else{ // last row
                            end = r+1;					
                            System.out.println("Num : " + num + " Last Row : " + (listAgent.size()-1));
                            System.out.println("Start : " + start +  " End  : " + end);
                            System.out.println("Last");
                            HSSFRow row = sheet1.createRow(r);
                            createCell(row,listAgent,num,styleAlignRightBorderAllNumber,styleAlignRightBorderAll);                                                    
                            sheet1.autoSizeColumn(13);
                            num++;

                            // total
                            int rowstart = r+1;
                            int rowend = r+2;
                            variableTotal(start,end,rowstart,rowend,sheet1,styleNumber,styleNumberBorderRight);
                            HSSFRow rowT = sheet1.createRow(r+3);
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
                                rowT.createCell(12).setCellStyle(styleAlignRightBorderBottomRight);
                        }
                    }else{ // not equal type
                        if(num  == (listAgent.size()-1)){ // check  last row
                            end = r+1;					
                            System.out.println("Num : " + num + " Last Row : " + (listAgent.size()-1));
                            System.out.println("Start : " + start +  " End  : " + end);
                            System.out.println("Last");
                            HSSFRow row = sheet1.createRow(r);
                            createCell(row,listAgent,num,styleAlignRightBorderAllNumber,styleAlignRightBorderAll);                                                    
                            sheet1.autoSizeColumn(13);
                            num++;
                                // total
                                int rowstart = r+1;
                                int rowend = r+2;
                                variableTotal(start,end,rowstart,rowend,sheet1,styleNumber,styleNumberBorderRight);
                                end = r+1;
                                HSSFRow rowT = sheet1.createRow(r+3);
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
                                    rowT.createCell(12).setCellStyle(styleAlignRightBorderBottomRight);
                            }else{                                          
                                end = r;					 
                                System.out.println("Start : " + start +  " End  : " + end);
                                System.out.println("Num : " + num + " Last Row : " + (listAgent.size()-1));
                                // total
                                int rowstart = r;
                                int rowend = r+1;
                                variableTotal(start,end,rowstart,rowend,sheet1,styleNumber,styleNumberBorderRight);
                                HSSFRow rowT = sheet1.createRow(r+2);
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
                                    rowT.createCell(12).setCellStyle(styleAlignRightBorderBottomRight);
                                // Start New Row (Group)
                                start = end + 5;
                                HSSFRow row0 = sheet1.createRow(r+3);
                                HSSFCell cell = row0.createCell(0);
                                    cell.setCellValue(listAgent.get(num).getAgentname());
//                                    cell.setCellStyle(styleAlignRightBorderAll);
                                row0.createCell(12).setCellStyle(styleAlignRightBorderAll);
                                String add = "A"+(r+4)+":M"+(r+4)+"";
                                System.out.println("Add : " + add);
                                sheet1.addMergedRegion(CellRangeAddress.valueOf(add));
                                HSSFRow row122 = sheet1.createRow(r+4);
                                createCell(row122,listAgent,num,styleAlignRightBorderAllNumber,styleAlignRightBorderAll);
                                sheet1.autoSizeColumn(13);
                                num++;				 
                                count = count + 4;
                                r = r + 4;
                            }
                        }
                    }else{ // row first
                        System.out.println("Num : " + num + " Last Row : " + (listAgent.size()-1));
                        
                        HSSFRow row0 = sheet1.createRow(r);
                        HSSFCell cell = row0.createCell(0);
                            cell.setCellValue(listAgent.get(num).getAgentname());
//                            cell.setCellStyle(styleAlignRightBorderAll);
                        row0.createCell(12).setCellStyle(styleAlignRightBorderAll);
                        String add = "A"+(r+1)+":M"+(r+1)+"";
                        System.out.println("Add : " + add);
                        sheet1.addMergedRegion(CellRangeAddress.valueOf(add));
                        
                        HSSFRow row = sheet1.createRow(r+1);
                        createCell(row,listAgent,num,styleAlignRightBorderAllNumber,styleAlignRightBorderAll);                        
                        sheet1.autoSizeColumn(13);
                        num = num + 1;
                        count = count + 1;
                        r = r + 1;
                    }
            }
            for(int i =0 ; i < listAgent.size() ; i++){
                sheet1.autoSizeColumn(i);
            }
	}
        
        //****************************************************************************************************************		
	// set Header Report (Row 1)
                HSSFCellStyle styleHeader03 = wb.createCellStyle();
                HSSFRow row311 = sheet2.createRow(0);
                HSSFCell cell311 = row311.createCell(0);
                cell311.setCellValue("Refund");
                styleHeader03.setFont(getHeaderFont(wb.createFont()));
                cell311.setCellStyle(styleHeader03);
                sheet2.addMergedRegion(CellRangeAddress.valueOf("A1:M1"));

                BillAirAgentRefund bill = new BillAirAgentRefund();
                if((listAgentRefund != null) && (listAgentRefund.size() != 0)){
                    bill = (BillAirAgentRefund)listAgentRefund.get(0);
                }
                // Row 2
                HSSFRow row32 = sheet2.createRow(1);
                HSSFCell cell321 = row32.createCell(0);
                cell321.setCellValue("Agent Name : ");
                cell321.setCellStyle(styleAlignRight);
                        sheet2.autoSizeColumn(0);
                HSSFCell cell322 = row32.createCell(1);
                cell322.setCellValue(bill.getAgentPage());
                cell322.setCellStyle(styleAlignLeft);
                        sheet2.autoSizeColumn(1);
                HSSFCell cell323 = row32.createCell(2);
                cell323.setCellValue("Print : ");
                cell323.setCellStyle(styleAlignRight);
                        sheet2.autoSizeColumn(2);
                HSSFCell cell324 = row32.createCell(3);
                cell324.setCellValue(bill.getPrintbyPage());
                cell324.setCellStyle(styleAlignLeft);
                        sheet2.autoSizeColumn(3);
                
                // Row 3
                HSSFRow row33 = sheet2.createRow(2);
                HSSFCell cell331 = row33.createCell(0);
                cell331.setCellValue("Refund Payment Date : ");
                cell331.setCellStyle(styleAlignRight);
                        sheet2.autoSizeColumn(0);
                HSSFCell cell332 = row33.createCell(1);
                cell332.setCellValue("");
                cell332.setCellStyle(styleAlignLeft);
                        sheet2.autoSizeColumn(1);
                HSSFCell cell333 = row33.createCell(2);
                cell333.setCellValue("Page : ");
                cell333.setCellStyle(styleAlignRight);
                        sheet2.autoSizeColumn(2);
                HSSFCell cell334 = row33.createCell(3);
                cell334.setCellValue("1");
                cell334.setCellStyle(styleAlignLeft);
                        sheet2.autoSizeColumn(3);
                
                // Header Table
                HSSFRow row39 = sheet2.createRow(4);

                HSSFCell cell396 = row39.createCell(0);
                cell396.setCellValue("Refund No");
                cell396.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(0);

                HSSFCell cell399 = row39.createCell(1);
                cell399.setCellValue("Date Receive");
                cell399.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(1);

                HSSFCell cell3104 = row39.createCell(2);
                cell3104.setCellValue("Passenger");
                cell3104.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(2);

                HSSFCell cell3105 = row39.createCell(3);
                cell3105.setCellValue("Air");
                cell3105.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(3);

                HSSFCell cell3106 = row39.createCell(4);
                cell3106.setCellValue("Doc No");	
                cell3106.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(4);

                HSSFCell cell3107 = row39.createCell(5);
                cell3107.setCellValue("Ref No");
                cell3107.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(5);

                HSSFCell cell3108 = row39.createCell(6);
                cell3108.setCellValue("Amount Receive");
                cell3108.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(6);

                HSSFCell cell3109 = row39.createCell(7);
                cell3109.setCellValue("Refund Change");
                cell3109.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(7);

                HSSFCell cell3110 = row39.createCell(8);
                cell3110.setCellValue("Amount_pat");
                cell3110.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(8);

                HSSFCell cell3111 = row39.createCell(9);
                cell3111.setCellValue("Comm Rcc");
                cell3111.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(9);

                HSSFCell cell3116 = row39.createCell(10);
                cell3116.setCellValue("Vat");
                cell3116.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(10);             

            int count2 = 4 + listAgentRefund.size();
//              int count2 = 2;

        if(listAgentRefund != null){
            for (int r = 5; r < count2; r++) {
                HSSFRow row = sheet2.createRow(r);
                HSSFCell cell00 = row.createCell(0);
                    cell00.setCellValue(listAgentRefund.get(r-5).getRefundno());
                    cell00.setCellStyle(styleDetailTable);
                HSSFCell cell2 = row.createCell(1);
                    cell2.setCellValue(listAgentRefund.get(r-5).getReceivedate());
                    cell2.setCellStyle(styleDetailTable);
               HSSFCell cell13 = row.createCell(2);
                       cell13.setCellValue(listAgentRefund.get(r-5).getPassenger());
                       cell13.setCellStyle(styleDetailTable);
               HSSFCell cell14 = row.createCell(3);
                       cell14.setCellValue(listAgentRefund.get(r-5).getAir());
                       cell14.setCellStyle(styleDetailTable);
               HSSFCell cell15 = row.createCell(4);
                       cell15.setCellValue(listAgentRefund.get(r-5).getDocno());
                       cell15.setCellStyle(styleDetailTable);
               HSSFCell cell16 = row.createCell(5);
                       cell16.setCellValue(listAgentRefund.get(r-5).getRefno());
                       cell16.setCellStyle(styleDetailTable);
               HSSFCell cell17 = row.createCell(6);
                       BigDecimal amountreceive  = new BigDecimal("".equals(listAgentRefund.get(r-5).getAmount_receive()) ? "0" : listAgentRefund.get(r-5).getAmount_receive());
                       cell17.setCellValue((amountreceive != null) ? amountreceive.doubleValue() : new BigDecimal("0").doubleValue());
                       cell17.setCellStyle(styleDetailTableNumber);
               HSSFCell cell18 = row.createCell(7);
                       BigDecimal refundchange  = new BigDecimal("".equals(listAgentRefund.get(r-5).getRefundchange()) ? "0" : listAgentRefund.get(r-5).getRefundchange());
                       cell18.setCellValue((refundchange != null) ? refundchange.doubleValue() : new BigDecimal("0").doubleValue());
                       cell18.setCellStyle(styleDetailTableNumber);
               HSSFCell cell19 = row.createCell(8);
                       BigDecimal amountpay  = new BigDecimal("".equals(listAgentRefund.get(r-5).getAmountpay()) ? "0" : listAgentRefund.get(r-5).getAmountpay());
                       cell19.setCellValue((amountpay != null) ? amountpay.doubleValue() : new BigDecimal("0").doubleValue());
                       cell19.setCellStyle(styleDetailTableNumber);
               HSSFCell cell20 = row.createCell(9);
                       BigDecimal comrec  = new BigDecimal("".equals(listAgentRefund.get(r-5).getComm_rec()) ? "0" : listAgentRefund.get(r-5).getComm_rec());
                       cell20.setCellValue((comrec != null) ? comrec.doubleValue() : new BigDecimal("0").doubleValue());
                       cell20.setCellStyle(styleDetailTableNumber);
               HSSFCell cell2205 = row.createCell(10);
                       BigDecimal vat  = new BigDecimal("".equals(listAgentRefund.get(r-5).getVat()) ? "0" : listAgentRefund.get(r-5).getVat());
                       cell2205.setCellValue((vat != null) ? vat.doubleValue() : new BigDecimal("0").doubleValue());
                       cell2205.setCellStyle(styleDetailTableNumber);
                for (int i = 0; i < 20; i++) {
                    sheet2.autoSizeColumn(i);
                }
            }
      }else{
        HSSFRow row55 = sheet2.createRow(5);

        HSSFCell cell55 = row55.createCell(0);
        cell55.setCellValue("No Data");
        sheet2.autoSizeColumn(0);
      }

        System.out.println(count2);
        HSSFRow rowLLL = sheet2.createRow(count2);
         rowLLL.createCell(0).setCellStyle(styleBorderTop);
         rowLLL.createCell(1).setCellStyle(styleBorderTop);
         rowLLL.createCell(2).setCellStyle(styleBorderTop);
         rowLLL.createCell(3).setCellStyle(styleBorderTop);
         rowLLL.createCell(4).setCellStyle(styleBorderTop);
         rowLLL.createCell(5).setCellStyle(styleBorderTop);
         rowLLL.createCell(6).setCellStyle(styleBorderTop);
         rowLLL.createCell(7).setCellStyle(styleBorderTop);
         rowLLL.createCell(8).setCellStyle(styleBorderTop);
         rowLLL.createCell(9).setCellStyle(styleBorderTop);
         rowLLL.createCell(10).setCellStyle(styleBorderTop); 
    }
    
    
    public void genCollectionReport(HSSFWorkbook wb, List collectionNirvanaList) {
      String sheetName = "Sheet1";// name of sheet
      HSSFSheet sheet = wb.createSheet(sheetName);
      CollectionNirvana dataheader = new CollectionNirvana();

      HSSFDataFormat currency = wb.createDataFormat();
      // Set align Text
      HSSFCellStyle styleC21 = wb.createCellStyle();
      styleC21.setAlignment(styleC21.ALIGN_RIGHT);
      HSSFCellStyle styleC22 = wb.createCellStyle();
      styleC22.setAlignment(styleC22.ALIGN_LEFT);
      HSSFCellStyle styleC23 = wb.createCellStyle();
      styleC23.setAlignment(styleC22.ALIGN_CENTER);

      HSSFCellStyle styleC25 = wb.createCellStyle();
      styleC25.setBorderLeft(HSSFCellStyle.BORDER_THIN);
      styleC25.setBorderRight(HSSFCellStyle.BORDER_THIN);
      styleC25.setBorderTop(HSSFCellStyle.BORDER_THIN);
      styleC25.setBorderBottom(HSSFCellStyle.BORDER_THIN);
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

      HSSFCellStyle styleC30 = wb.createCellStyle();
      styleC30.setBorderLeft(HSSFCellStyle.BORDER_THIN);
      styleC30.setBorderRight(HSSFCellStyle.BORDER_THIN);
      styleC30.setBorderTop(HSSFCellStyle.BORDER_THIN);
      styleC30.setBorderBottom(HSSFCellStyle.BORDER_THIN);
      styleC30.setAlignment(styleC22.ALIGN_CENTER);

      if(!collectionNirvanaList.isEmpty()){
          dataheader = (CollectionNirvana)collectionNirvanaList.get(0);

          // set Header Report (Row 1)
          HSSFCellStyle styleC1 = wb.createCellStyle();
          HSSFRow row1 = sheet.createRow(0);
          HSSFCell cell1 = row1.createCell(0);
          cell1.setCellValue("COLLECTION REPORT");
          styleC1.setFont(getHeaderFont(wb.createFont()));
          cell1.setCellStyle(styleC1);
          sheet.addMergedRegion(CellRangeAddress.valueOf("A1:E1"));

          // Row 2
          HSSFRow row2 = sheet.createRow(1);
          HSSFCell cell21 = row2.createCell(0);
          cell21.setCellValue("Print On : ");
          cell21.setCellStyle(styleC21);
          HSSFCell cell22 = row2.createCell(1);
          cell22.setCellValue(dataheader.getSystemdate());
          cell22.setCellStyle(styleC22);
//            sheet.addMergedRegion(CellRangeAddress.valueOf("B2:C2"));
          HSSFCell cell23 = row2.createCell(2);
          cell23.setCellValue("By : " +dataheader.getUser());
          cell23.setCellStyle(styleC22);
//            HSSFCell cell24 = row2.createCell(3);
//            cell24.setCellValue(dataheader.getUser());
//            cell24.setCellStyle(styleC22);

          // Row 3
          HSSFRow row3 = sheet.createRow(2);
          HSSFCell cell31 = row3.createCell(0);
          cell31.setCellValue("Report of : ");
          cell31.setCellStyle(styleC21);
          HSSFCell cell32 = row3.createCell(1);
          if(!"".equalsIgnoreCase(dataheader.getFrom())){
          cell32.setCellValue(dataheader.getFrom());
          cell32.setCellStyle(styleC22);
//            sheet.addMergedRegion(CellRangeAddress.valueOf("B3:C3"));
          }
//            HSSFCell cell33 = row3.createCell(2);
//            cell33.setCellValue("To : ");
//            cell33.setCellStyle(styleC21);
          HSSFCell cell34 = row3.createCell(2);
          if(!"".equalsIgnoreCase(dataheader.getTo())){
          cell34.setCellValue("To : "+dataheader.getTo());
          cell34.setCellStyle(styleC22);
          }

          // Row 4
          HSSFRow row4 = sheet.createRow(3);
          HSSFCell cell41 = row4.createCell(0);
          cell41.setCellValue("Department : ");
          cell41.setCellStyle(styleC21);
          HSSFCell cell42 = row4.createCell(1);
          cell42.setCellValue(dataheader.getHeaderdepartment());
          cell42.setCellStyle(styleC22);
//            sheet.addMergedRegion(CellRangeAddress.valueOf("B4:C4"));
     

      // Header Table
      HSSFCellStyle styleC3 = wb.createCellStyle();
      styleC3.setFont(getHeaderTable(wb.createFont()));
      styleC3.setAlignment(styleC3.ALIGN_CENTER);
      styleC3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
      styleC3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
      styleC3.setBorderRight(HSSFCellStyle.BORDER_THIN);
      styleC3.setBorderTop(HSSFCellStyle.BORDER_THIN);

      HSSFRow row6 = sheet.createRow(5);
      HSSFCell cell61 = row6.createCell(0);
      cell61.setCellValue("No");
      cell61.setCellStyle(styleC3);
      sheet.autoSizeColumn(0);
      HSSFCell cell62 = row6.createCell(1);
      cell62.setCellValue("Inv No");
      cell62.setCellStyle(styleC3);
      sheet.autoSizeColumn(1);
      HSSFCell cell63 = row6.createCell(2);
      cell63.setCellValue("Receipt No");
      sheet.autoSizeColumn(2);
      cell63.setCellStyle(styleC3);
      HSSFCell cell64 = row6.createCell(3);
      cell64.setCellValue("Ar Code");
      cell64.setCellStyle(styleC3);
      sheet.autoSizeColumn(3);
      HSSFCell cell65 = row6.createCell(4);
      cell65.setCellValue("Inv To");
      cell65.setCellStyle(styleC3);
      sheet.autoSizeColumn(4);
      HSSFCell cell66 = row6.createCell(5);
      cell66.setCellValue("Acc Code");
      cell66.setCellStyle(styleC3);
      sheet.autoSizeColumn(5);
      HSSFCell cell67 = row6.createCell(6);
      cell67.setCellValue("Inv Amount");
      cell67.setCellStyle(styleC3);
      sheet.autoSizeColumn(6);
      HSSFCell cell68 = row6.createCell(7);
      cell68.setCellValue("Diff");
      cell68.setCellStyle(styleC3);
      sheet.autoSizeColumn(7);
      HSSFCell cell69 = row6.createCell(8);
      cell69.setCellValue("Rec Amount");
      cell69.setCellStyle(styleC3);
      sheet.autoSizeColumn(8);
      HSSFCell cell70 = row6.createCell(9);
      cell70.setCellValue("Cur");
      cell70.setCellStyle(styleC3);
      sheet.autoSizeColumn(9);
      HSSFCell cell71 = row6.createCell(10);
      cell71.setCellValue("Status");
      cell71.setCellStyle(styleC3);
      sheet.autoSizeColumn(10);
      }
      //Detail of Table
      int count = 6 ;
      int no = 1;
      for(int i=0;i<collectionNirvanaList.size();i++){
          CollectionNirvana data = (CollectionNirvana)collectionNirvanaList.get(i);
          HSSFRow row = sheet.createRow(count + i);

          HSSFCell celldata0 = row.createCell(0);
          celldata0.setCellValue(String.valueOf(no));
          celldata0.setCellStyle(styleC30);

          HSSFCell celldata1 = row.createCell(1);
          celldata1.setCellValue(String.valueOf(data.getInvno()));
          celldata1.setCellStyle(styleC29);

          HSSFCell celldata2 = row.createCell(2);
          celldata2.setCellValue(String.valueOf(data.getRecno()));
          celldata2.setCellStyle(styleC29);

          HSSFCell celldata3 = row.createCell(3);
          celldata3.setCellValue(String.valueOf(data.getArcode()));
          celldata3.setCellStyle(styleC29);

          HSSFCell celldata4 = row.createCell(4);
          celldata4.setCellValue(String.valueOf(data.getInvto()));
          celldata4.setCellStyle(styleC29);

          HSSFCell celldata5 = row.createCell(5);
          celldata5.setCellValue(String.valueOf(data.getAcccode()));
          celldata5.setCellStyle(styleC29);

          HSSFCell celldata6 = row.createCell(6);
          celldata6.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInvamount())) ? 0 : (data.getInvamount()).doubleValue());
          celldata6.setCellStyle(styleC25);

          HSSFCell celldata7 = row.createCell(7);
          celldata7.setCellValue("".equalsIgnoreCase(String.valueOf(data.getDiff())) ? 0 : (data.getDiff()).doubleValue());
          celldata7.setCellStyle(styleC25);

          HSSFCell celldata8 = row.createCell(8);
          celldata8.setCellValue("".equalsIgnoreCase(String.valueOf(data.getRecamount())) ? 0 : (data.getRecamount()).doubleValue());
          celldata8.setCellStyle(styleC25);

          HSSFCell celldata9 = row.createCell(9);
          celldata9.setCellValue(String.valueOf(data.getCur()));
          celldata9.setCellStyle(styleC30);

          HSSFCell celldata10 = row.createCell(10);
          celldata10.setCellValue(String.valueOf(data.getCollectionStatus()));
          celldata10.setCellStyle(styleC30);
           
//            if(i == (TicketFare.size()-1)){
//                row = sheet.createRow(count + i + 1);
//                for(int k=0;k<8;k++){
//                    HSSFCellStyle styleSum = wb.createCellStyle();
//                    styleSum.setAlignment(styleC24.ALIGN_RIGHT);
//                    styleSum.setBorderTop(HSSFCellStyle.BORDER_THIN);
//                    styleSum.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//                    HSSFCell cellSum = row.createCell(k);                   
//                    if(k == 0){styleSum.setBorderLeft(HSSFCellStyle.BORDER_THIN);}
//                    cellSum.setCellStyle(styleSum);
//                }
//                HSSFCellStyle styleSum = wb.createCellStyle();
//                styleSum.setAlignment(styleSum.ALIGN_RIGHT);
//                styleSum.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//                styleSum.setBorderRight(HSSFCellStyle.BORDER_THIN);
//                styleSum.setBorderTop(HSSFCellStyle.BORDER_THIN);
//                styleSum.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//                styleSum.setDataFormat(currency.getFormat("#,##0.00"));
//                
//                String netsalesTotal = "SUM(J" + 10+":J"+(count + i + 1)+")";
//                String taxTotal = "SUM(K" + 10+":K"+(count + i + 1)+")";
//                String insTotal = "SUM(L" + 10+":L"+(count + i + 1)+")";
//                String actcommTotal = "SUM(M" + 10+":M"+(count + i + 1)+")";
//                String invamountTotal = "SUM(N" + 10+":N"+(count + i + 1)+")";
//
//                HSSFCell cellTotal00 = row.createCell(8);
//                cellTotal00.setCellValue("Total");
//                cellTotal00.setCellStyle(styleSum);                
//                HSSFCell cellTotal01 = row.createCell(9);
//                cellTotal01.setCellFormula(netsalesTotal);
//                cellTotal01.setCellStyle(styleSum);
//                HSSFCell cellTotal02 = row.createCell(10);
//                cellTotal02.setCellFormula(taxTotal);
//                cellTotal02.setCellStyle(styleSum);
//                HSSFCell cellTotal03 = row.createCell(11);
//                cellTotal03.setCellFormula(insTotal);
//                cellTotal03.setCellStyle(styleSum);
//                HSSFCell cellTotal04 = row.createCell(12);
//                cellTotal04.setCellFormula(actcommTotal);
//                cellTotal04.setCellStyle(styleSum);
//                HSSFCell cellTotal05 = row.createCell(13);
//                cellTotal05.setCellFormula(invamountTotal);
//                cellTotal05.setCellStyle(styleSum);
//             }

           for(int j =0;j<13;j++){
               sheet.autoSizeColumn(j);
           }
           no ++;
      }
  }
    
   private void getApReport(HSSFWorkbook wb, List ApNirvana) {
        String sheetName = "Sheet1";// name of sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        
        APNirvana dataheader = new APNirvana();
        
        HSSFDataFormat currency = wb.createDataFormat();
        // Set align Text
        HSSFCellStyle styleC20 = wb.createCellStyle();
        styleC20.setAlignment(styleC20.ALIGN_CENTER);
        HSSFCellStyle styleC21 = wb.createCellStyle();
        styleC21.setAlignment(styleC21.ALIGN_RIGHT);
        HSSFCellStyle styleC22 = wb.createCellStyle();
        styleC22.setAlignment(styleC22.ALIGN_LEFT);       
        
        if(ApNirvana != null){
            dataheader = (APNirvana)ApNirvana.get(0);
        }
        
        // set Header Report (Row 1)
        HSSFCellStyle styleC1 = wb.createCellStyle();
        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cellStart = row1.createCell(0);
        cellStart.setCellValue("Change Ap Report");
        styleC1.setFont(getHeaderFont(wb.createFont()));
        cellStart.setCellStyle(styleC1);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:F1"));
        
        // Row 2
        HSSFRow row2 = sheet.createRow(1);
        HSSFCell cell21 = row2.createCell(0);
        cell21.setCellValue("Print On : ");
        cell21.setCellStyle(styleC21);
        HSSFCell cell22 = row2.createCell(1);
        cell22.setCellValue(dataheader.getSystemdate());
        cell22.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B2:D2"));
        HSSFCell cell23 = row2.createCell(4);
        cell23.setCellValue("By : ");
        cell23.setCellStyle(styleC21);
        HSSFCell cell24 = row2.createCell(5);
        cell24.setCellValue(dataheader.getUser());
        cell24.setCellStyle(styleC22);
        
        // Row 3
        HSSFRow row3 = sheet.createRow(2);
        HSSFCell cell31 = row3.createCell(0);
        cell31.setCellValue("Report of : ");
        cell31.setCellStyle(styleC21);
        HSSFCell cell32 = row3.createCell(1);
        cell32.setCellValue(dataheader.getDatefrom());
        cell32.setCellStyle(styleC22);
        if(!"".equalsIgnoreCase(dataheader.getDateto())){
            HSSFCell cell33 = row3.createCell(2);
            cell33.setCellValue("To");
            cell33.setCellStyle(styleC20);
            HSSFCell cell34 = row3.createCell(3);
            cell34.setCellValue(dataheader.getDateto());
            cell34.setCellStyle(styleC22);
        }    
//        sheet.addMergedRegion(CellRangeAddress.valueOf("B3:D3"));

        // Row 4
        HSSFRow row4 = sheet.createRow(3);
        HSSFCell cell41 = row4.createCell(0);
        cell41.setCellValue("Department : ");
        cell41.setCellStyle(styleC21);
        HSSFCell cell42 = row4.createCell(1);
        cell42.setCellValue(dataheader.getDepartmentheader());
        cell42.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B4:D4"));        

        // Row 5
        HSSFRow row5 = sheet.createRow(4);
        HSSFCell cell51 = row5.createCell(0);
        cell51.setCellValue("Page : ");
        cell51.setCellStyle(styleC21);       
        HSSFCell cell52 = row5.createCell(5);
        cell52.setCellValue("");
        cell52.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B5:D5"));
        
         // Header Table
        HSSFCellStyle styleC3Center = wb.createCellStyle();
        styleC3Center.setFont(getHeaderTable(wb.createFont()));
        styleC3Center.setAlignment(styleC3Center.ALIGN_CENTER);
        styleC3Center.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC3Center.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC3Center.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC3Center.setBorderTop(HSSFCellStyle.BORDER_THIN);
        HSSFRow row6 = sheet.createRow(8);
        HSSFCell cell61 = row6.createCell(0);
        cell61.setCellValue("No.");
        cell61.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(0);
        HSSFCell cell62 = row6.createCell(1);
        cell62.setCellValue("Pay No.");
        cell62.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(1);
        HSSFCell cell63 = row6.createCell(2);
        cell63.setCellValue("Ap Code");
        sheet.autoSizeColumn(2);
        cell63.setCellStyle(styleC3Center);
        HSSFCell cell64 = row6.createCell(3);
        cell64.setCellValue("Invoice Sup");
        cell64.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(3);
        HSSFCell cell65 = row6.createCell(4);
        cell65.setCellValue("Acc Code");
        cell65.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(4);
        HSSFCell cell66 = row6.createCell(5);
        cell66.setCellValue("Cur");
        cell66.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(5);
        
        HSSFCellStyle styleC3Right = wb.createCellStyle();
        styleC3Right.setFont(getHeaderTable(wb.createFont()));
        styleC3Right.setAlignment(styleC3Right.ALIGN_RIGHT);
        styleC3Right.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC3Right.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC3Right.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC3Right.setBorderTop(HSSFCellStyle.BORDER_THIN);
        HSSFCell cell67 = row6.createCell(6);
        cell67.setCellValue("Gross");
        cell67.setCellStyle(styleC3Right);
        sheet.autoSizeColumn(6);
        HSSFCell cell68 = row6.createCell(7);
        cell68.setCellValue("Amount");
        cell68.setCellStyle(styleC3Right);
        sheet.autoSizeColumn(7);
        
        //Detail of Table
        int count = 9 ;
        HSSFCellStyle styleC23 = wb.createCellStyle();
        styleC23.setAlignment(styleC22.ALIGN_CENTER);
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
        for(int i=0;i<ApNirvana.size();i++){
             APNirvana data = (APNirvana)ApNirvana.get(i);
             HSSFRow row = sheet.createRow(count + i);
             HSSFCell cell0 = row.createCell(0);
                cell0.setCellValue(i+1);
                cell0.setCellStyle(styleC23);
             HSSFCell cell1 = row.createCell(1);
                cell1.setCellValue(data.getIntreference());
                cell1.setCellStyle(styleC24);
             HSSFCell cell2 = row.createCell(2);
                cell2.setCellValue(data.getVendorid());
                cell2.setCellStyle(styleC24);
             HSSFCell cell3 = row.createCell(3);
                cell3.setCellValue(data.getVendorname());
                cell3.setCellStyle(styleC24);
             HSSFCell cell4 = row.createCell(4);
                cell4.setCellValue(data.getPuraccount1());
                cell4.setCellStyle(styleC24);
             HSSFCell cell5 = row.createCell(5);
                cell5.setCellValue(data.getCurrencyid());
                cell5.setCellStyle(styleC23);   
             HSSFCell cell6 = row.createCell(6);
                cell6.setCellValue((data.getVatamt() != null) ? data.getVatamt().doubleValue() : new BigDecimal("0").doubleValue());
                cell6.setCellStyle(styleC25);
             HSSFCell cell7 = row.createCell(7);
                cell7.setCellValue((data.getBasevatamt() != null) ? data.getBasevatamt().doubleValue() : new BigDecimal("0").doubleValue());
                cell7.setCellStyle(styleC25);
             
             
             if(i == (ApNirvana.size()-1)){
                row = sheet.createRow(count + i + 1);
                for(int k=0;k<5;k++){
                    HSSFCellStyle styleSum = wb.createCellStyle();
                    styleSum.setAlignment(styleC24.ALIGN_RIGHT);
                    styleSum.setBorderTop(HSSFCellStyle.BORDER_THIN);
                    styleSum.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                    HSSFCell cellSum = row.createCell(k);                   
                    if(k == 0){styleSum.setBorderLeft(HSSFCellStyle.BORDER_THIN);}
                    cellSum.setCellStyle(styleSum);
                }
                HSSFCellStyle styleSum = wb.createCellStyle();
                styleSum.setAlignment(styleSum.ALIGN_RIGHT);
                styleSum.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                styleSum.setBorderRight(HSSFCellStyle.BORDER_THIN);
                styleSum.setBorderTop(HSSFCellStyle.BORDER_THIN);
                styleSum.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                styleSum.setDataFormat(currency.getFormat("#,##0.00"));
                
                String sumGross = "SUM(G" + 10+":G"+(count + i + 1)+")";
                String sumAmount = "SUM(H" + 10+":H"+(count + i + 1)+")";
                
                HSSFCell cell5Sum = row.createCell(5);
                    cell5Sum.setCellValue("Total");
                    cell5Sum.setCellStyle(styleSum);
                HSSFCell cell6Sum = row.createCell(6);
                    cell6Sum.setCellFormula(sumGross);
                    cell6Sum.setCellStyle(styleSum);
                HSSFCell cell7Sum = row.createCell(7);
                    cell7Sum.setCellFormula(sumAmount);
                    cell7Sum.setCellStyle(styleSum);
                
             }
             for(int j =0;j<13;j++){
                 sheet.autoSizeColumn(j);
             }
        }     
    }
   
    private void getSummaryAirline(HSSFWorkbook wb, List summaryAirline){
        List<SummaryAirline> listAR = summaryAirline;
        String sheetName = "Sheet1";// name of sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        
        // Set align Text
        HSSFCellStyle styleAlignRight = wb.createCellStyle();
                styleAlignRight.setAlignment(styleAlignRight.ALIGN_RIGHT);
        HSSFCellStyle styleAlignLeft = wb.createCellStyle();
                styleAlignLeft.setAlignment(styleAlignLeft.ALIGN_LEFT);
        HSSFCellStyle styleAlignCenter = wb.createCellStyle();
                styleAlignCenter.setAlignment(styleAlignCenter.ALIGN_CENTER);
        HSSFCellStyle styleBorderLeft = wb.createCellStyle();
                styleBorderLeft.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        HSSFCellStyle styleBorderRight = wb.createCellStyle();
                styleBorderRight.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        HSSFDataFormat currency = wb.createDataFormat();
        HSSFCellStyle styleNumber = wb.createCellStyle();
                styleNumber.setAlignment(styleNumber.ALIGN_RIGHT);
                styleNumber.setDataFormat(currency.getFormat("#,##0.00"));
        HSSFCellStyle styleNumberBorderRight = wb.createCellStyle();
                styleNumberBorderRight.setAlignment(styleNumberBorderRight.ALIGN_RIGHT);
                styleNumberBorderRight.setDataFormat(currency.getFormat("#,##0.00"));
                styleNumberBorderRight.setBorderRight(styleNumberBorderRight.BORDER_THIN);

       
        // set Header Report (Row 1)
        HSSFCellStyle styleC1 = wb.createCellStyle();
        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cell0 = row1.createCell(0);
        cell0.setCellValue("LIST SUMMARY AIRLINE");
        styleC1.setFont(getHeaderFont(wb.createFont()));
        cell0.setCellStyle(styleC1);
        sheet.autoSizeColumn(0);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:I1"));

        // Row 2
        HSSFRow row2 = sheet.createRow(1);
        HSSFCell cell1 = row2.createCell(0);
            cell1.setCellValue("Invoice Date : ");
            cell1.setCellStyle(styleAlignRight);
            sheet.autoSizeColumn(0);
        HSSFCell cell2 = row2.createCell(1);
            Date date = new Date();
            SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
            String strDate = sm.format(date);
            cell2.setCellValue(strDate);
            cell2.setCellStyle(styleAlignLeft);
            sheet.autoSizeColumn(1);
        HSSFCell cell3 = row2.createCell(2);
            cell3.setCellValue(" To ");
            cell3.setCellStyle(styleAlignCenter);
            sheet.autoSizeColumn(2);
        HSSFCell cell4 = row2.createCell(3);
            cell4.setCellValue(strDate);
            cell4.setCellStyle(styleAlignLeft);
            sheet.autoSizeColumn(3);
        HSSFCell cell5 = row2.createCell(4);
            cell5.setCellValue("Print on : ");
            cell5.setCellStyle(styleAlignRight);
            sheet.autoSizeColumn(4);
        HSSFCell cell6 = row2.createCell(5);
            cell6.setCellValue(strDate);
            cell6.setCellStyle(styleAlignLeft);
            sheet.autoSizeColumn(5);

        // Row 3
        HSSFRow row3 = sheet.createRow(2);
        HSSFCell cell31 = row3.createCell(0);
            cell31.setCellValue("Print By : ");
            cell31.setCellStyle(styleAlignRight);
            sheet.autoSizeColumn(0);
        HSSFCell cell32 = row3.createCell(1);
            cell32.setCellValue("Adminstarator");
            cell32.setCellStyle(styleAlignLeft);
            sheet.autoSizeColumn(1);
            sheet.addMergedRegion(CellRangeAddress.valueOf("B3:D3"));
        HSSFCell cell33 = row3.createCell(4);
            cell33.setCellValue("Rounting Detail : ");
            cell33.setCellStyle(styleAlignRight);
            sheet.autoSizeColumn(4);
        HSSFCell cell34 = row3.createCell(5);
            cell34.setCellValue("XXXXXXXXXX");
            cell34.setCellStyle(styleAlignLeft);
            sheet.autoSizeColumn(5);

        // Row 4
        HSSFRow row4 = sheet.createRow(3);
        HSSFCell cell41 = row4.createCell(0);
            cell41.setCellValue("Type Rounting : ");
            cell41.setCellStyle(styleAlignRight);
            sheet.autoSizeColumn(0);
        HSSFCell cell42 = row4.createCell(1);
            cell42.setCellValue("XXXXXXXX");
            cell42.setCellStyle(styleAlignLeft);
            sheet.autoSizeColumn(1);
            sheet.addMergedRegion(CellRangeAddress.valueOf("B4:D4"));
        HSSFCell cell43 = row4.createCell(4);
            cell43.setCellValue("Passenger : ");
            cell43.setCellStyle(styleAlignRight);
            sheet.autoSizeColumn(4);
        HSSFCell cell44 = row4.createCell(5);
            cell44.setCellValue("XXXXXXXXXX");
            cell44.setCellStyle(styleAlignLeft);
            sheet.autoSizeColumn(5);
        
       // Row 5
        HSSFRow row5 = sheet.createRow(4);
        HSSFCell cell51 = row5.createCell(0);
            cell51.setCellValue("Air : ");
            cell51.setCellStyle(styleAlignRight);
            sheet.autoSizeColumn(0);
        HSSFCell cell52 = row5.createCell(1);
            cell52.setCellValue("XXXXXXXX");
            cell52.setCellStyle(styleAlignLeft);
            sheet.autoSizeColumn(1);
            sheet.addMergedRegion(CellRangeAddress.valueOf("B5:D5"));
        HSSFCell cell53 = row5.createCell(4);
            cell53.setCellValue("Sale Staff : ");
            cell53.setCellStyle(styleAlignRight);
            sheet.autoSizeColumn(4);
        HSSFCell cell54 = row5.createCell(5);
            cell54.setCellValue("XXXXXXXXXX");
            cell54.setCellStyle(styleAlignLeft);
            sheet.autoSizeColumn(5);
            
            
           // Row 6
        HSSFRow row6 = sheet.createRow(5);
        HSSFCell cell61 = row6.createCell(0);
            cell61.setCellValue("Agent Name : ");
            cell61.setCellStyle(styleAlignRight);
            sheet.autoSizeColumn(0);
        HSSFCell cell62 = row6.createCell(1);
            cell62.setCellValue("XXXXXXXX");
            cell62.setCellStyle(styleAlignLeft);
            sheet.autoSizeColumn(1);
            sheet.addMergedRegion(CellRangeAddress.valueOf("B6:D6"));
        HSSFCell cell63 = row6.createCell(4);
            cell63.setCellValue("Department : ");
            cell63.setCellStyle(styleAlignRight);
            sheet.autoSizeColumn(4);
        HSSFCell cell64 = row6.createCell(5);
            cell64.setCellValue("XXXXXXXXXX");
            cell64.setCellStyle(styleAlignLeft);
            sheet.autoSizeColumn(5);
            
            // Row 7
        HSSFRow row7 = sheet.createRow(6);
        HSSFCell cell71 = row7.createCell(0);
            cell71.setCellValue("Term Pay : ");
            cell71.setCellStyle(styleAlignRight);
            sheet.autoSizeColumn(0);
        HSSFCell cell72 = row7.createCell(1);
            cell72.setCellValue("XXXXXXXX");
            cell72.setCellStyle(styleAlignLeft);
            sheet.autoSizeColumn(1);
            sheet.addMergedRegion(CellRangeAddress.valueOf("B5:D5"));


         // Header Table
        HSSFCellStyle styleHeader = wb.createCellStyle();
            styleHeader.setFont(getHeaderTable(wb.createFont()));
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
            styleDetailTableNumber.setDataFormat(currency.getFormat("#,##0.00"));
            styleDetailTableNumber.setAlignment(styleDetailTableNumber.ALIGN_RIGHT);
            styleDetailTableNumber.setBorderLeft(styleDetailTableNumber.BORDER_THIN);
            styleDetailTableNumber.setBorderRight(styleDetailTableNumber.BORDER_THIN);
        HSSFCellStyle styleDetailTableBorderBottom = wb.createCellStyle();
            styleDetailTableBorderBottom.setBorderTop(styleDetailTableBorderBottom.BORDER_THIN);
        
            
        HSSFRow rowH = sheet.createRow(8);
        HSSFCell cellH1 = rowH.createCell(0);
            cellH1.setCellValue("Rounting");
            cellH1.setCellStyle(styleHeader);
            sheet.autoSizeColumn(0);
        HSSFCell cellH2 = rowH.createCell(1);
            cellH2.setCellValue("Pax");
            cellH2.setCellStyle(styleHeader);
            sheet.autoSizeColumn(1);
        HSSFCell cellH3 = rowH.createCell(2);
            cellH3.setCellValue("Net Sales");
            cellH3.setCellStyle(styleHeader);
            sheet.autoSizeColumn(2);
        HSSFCell cellH4 = rowH.createCell(3);
            cellH4.setCellValue("Tax");
            cellH4.setCellStyle(styleHeader);
            sheet.autoSizeColumn(3);
        HSSFCell cellH5 = rowH.createCell(4);
            cellH5.setCellValue("Ins");
            cellH5.setCellStyle(styleHeader);
            sheet.autoSizeColumn(4);
        HSSFCell cellH6 = rowH.createCell(5);
            cellH6.setCellValue("Comms");
            cellH6.setCellStyle(styleHeader);
            sheet.autoSizeColumn(5);
        HSSFCell cellH7 = rowH.createCell(6);
            cellH7.setCellValue("Amount Wendy");
            cellH7.setCellStyle(styleHeader);
            sheet.autoSizeColumn(6);
        HSSFCell cellH8 = rowH.createCell(7);
            cellH8.setCellValue("Amount Inbound");
            cellH8.setCellStyle(styleHeader);
            sheet.autoSizeColumn(7);
        HSSFCell cellH9 = rowH.createCell(8);
            cellH9.setCellValue("Diff");
            cellH9.setCellStyle(styleHeader);
            sheet.autoSizeColumn(8);
       if(listAR != null && listAR.size() != 0){ 
        int count = 9 + listAR.size();
        int start = 11;
        int end = 0;
        int num = 0;
        for (int r = 9 ; r < count; r++) {
             if(num <= (listAR.size()-1)){
                HSSFRow rowH1 = sheet.createRow(r);
                HSSFCell cellH11 = rowH1.createCell(0);
                    cellH11.setCellValue(listAR.get(num).getRouting());
                    cellH11.setCellStyle(styleDetailTable);
                    sheet.autoSizeColumn(0);
                HSSFCell cellH21 = rowH1.createCell(1);
                    cellH21.setCellValue((listAR.get(num).getPax() != null) ? listAR.get(num).getPax().doubleValue() : new BigDecimal("0").doubleValue());
                    cellH21.setCellStyle(styleDetailTableNumber);
                    sheet.autoSizeColumn(1);
                HSSFCell cellH31 = rowH1.createCell(2);
                    cellH31.setCellValue((listAR.get(num).getNetsale() != null) ? listAR.get(num).getNetsale().doubleValue() : new BigDecimal("0").doubleValue());
                    cellH31.setCellStyle(styleDetailTableNumber);
                    sheet.autoSizeColumn(2);
                HSSFCell cellH41 = rowH1.createCell(3);
                    cellH41.setCellValue((listAR.get(num).getTax() != null) ? listAR.get(num).getTax().doubleValue() : new BigDecimal("0").doubleValue());
                    cellH41.setCellStyle(styleDetailTableNumber);
                    sheet.autoSizeColumn(3);
                HSSFCell cellH51 = rowH1.createCell(4);
                    cellH51.setCellValue((listAR.get(num).getIns() != null) ? listAR.get(num).getIns().doubleValue() : new BigDecimal("0").doubleValue());
                    cellH51.setCellStyle(styleDetailTableNumber);
                    sheet.autoSizeColumn(4);
                HSSFCell cellH61 = rowH1.createCell(5);
                    cellH61.setCellValue((listAR.get(num).getComms() != null) ? listAR.get(num).getComms().doubleValue() : new BigDecimal("0").doubleValue());
                    cellH61.setCellStyle(styleDetailTableNumber);
                    sheet.autoSizeColumn(5);
                HSSFCell cellH71 = rowH1.createCell(6);
                    cellH71.setCellValue((listAR.get(num).getAmountwendy() != null) ? listAR.get(num).getAmountwendy().doubleValue() : new BigDecimal("0").doubleValue());
                    cellH71.setCellStyle(styleDetailTableNumber);
                    sheet.autoSizeColumn(6);
                HSSFCell cellH81 = rowH1.createCell(7);
                    cellH81.setCellValue((listAR.get(num).getAmountinbound() != null) ? listAR.get(num).getAmountinbound().doubleValue() : new BigDecimal("0").doubleValue());
                    cellH81.setCellStyle(styleDetailTableNumber);
                    sheet.autoSizeColumn(7);
                HSSFCell cellH91 = rowH1.createCell(8);
                    cellH91.setCellValue((listAR.get(num).getDiff() != null) ? listAR.get(num).getDiff().doubleValue() : new BigDecimal("0").doubleValue());
                    cellH91.setCellStyle(styleDetailTableNumber);
                    sheet.autoSizeColumn(8);
                num++;
             }
        }
        String sumPax = "SUM(B" + 10+":B"+(count- 1)+")";
        String sumNetSales = "SUM(C" + 10+":C"+(count- 1)+")";
        String sumTax = "SUM(D" + 10+":D"+(count - 1)+")";
        String sumIns = "SUM(E" + 10+":E"+(count- 1)+")";
        String sumComms = "SUM(F" + 10+":F"+(count -1 )+")";
        String sumAmountWendy = "SUM(G" + 10+":G"+(count - 1 )+")";
        String sumAmountInbound = "SUM(H" + 10+":H"+(count - 1 )+")";
        String sumDiff = "SUM(I" + 10+":I"+(count - 1 )+")";
        
        HSSFRow row = sheet.createRow(count);
        HSSFCell cell6Sum = row.createCell(1);
            cell6Sum.setCellFormula(sumPax);
            cell6Sum.setCellStyle(styleDetailTableNumber);
        HSSFCell cell7Sum = row.createCell(2);
            cell7Sum.setCellFormula(sumNetSales);
            cell7Sum.setCellStyle(styleDetailTableNumber);
        HSSFCell cell8Sum = row.createCell(3);
            cell8Sum.setCellFormula(sumTax);
            cell8Sum.setCellStyle(styleDetailTableNumber);
        HSSFCell cell9Sum = row.createCell(4);
            cell9Sum.setCellFormula(sumIns);
            cell9Sum.setCellStyle(styleDetailTableNumber);
        HSSFCell cell10Sum = row.createCell(5);
            cell10Sum.setCellFormula(sumComms);
            cell10Sum.setCellStyle(styleDetailTableNumber);
        HSSFCell cell11Sum = row.createCell(6);
            cell11Sum.setCellFormula(sumAmountWendy);
            cell11Sum.setCellStyle(styleDetailTableNumber);
        HSSFCell cell12Sum = row.createCell(7);
            cell12Sum.setCellFormula(sumAmountInbound);
            cell12Sum.setCellStyle(styleDetailTableNumber);
        HSSFCell cell13Sum = row.createCell(8);
            cell13Sum.setCellFormula(sumDiff);
            cell13Sum.setCellStyle(styleDetailTableNumber);
       HSSFRow rowL = sheet.createRow(count+1);
            rowL.createCell(0).setCellStyle(styleDetailTableBorderBottom);
            rowL.createCell(1).setCellStyle(styleDetailTableBorderBottom);
            rowL.createCell(2).setCellStyle(styleDetailTableBorderBottom);
            rowL.createCell(3).setCellStyle(styleDetailTableBorderBottom);
            rowL.createCell(4).setCellStyle(styleDetailTableBorderBottom);
            rowL.createCell(5).setCellStyle(styleDetailTableBorderBottom);
            rowL.createCell(6).setCellStyle(styleDetailTableBorderBottom);
            rowL.createCell(7).setCellStyle(styleDetailTableBorderBottom);
            rowL.createCell(8).setCellStyle(styleDetailTableBorderBottom);
        }  
    } 
    
    //Pax 
    public void genTicketFareSummaryAirlineReport(HSSFWorkbook wb, List ticketSummaryAirline) {
        String sheetNamePax = "Pax"; // name of sheet
        String sheetNameDetail = "Detail";
        String sheetNameRounting = "Rounting";
        HSSFSheet sheetPax = wb.createSheet(sheetNamePax);
        HSSFSheet sheetDetail = wb.createSheet(sheetNameDetail);
        HSSFSheet sheetRounting = wb.createSheet(sheetNameRounting);

//        HSSFRow row111 = sheet1.createRow(0);
//        HSSFCell cell1111 = row111.createCell(0);
//        cell1111.setCellValue("detail");
        
//        String sheetName = "Sheet1";// name of sheet
        TicketSummaryAirlineView dataheader = new TicketSummaryAirlineView();

        HSSFDataFormat currency = wb.createDataFormat();
        // Set align Text
        HSSFCellStyle styleC21 = wb.createCellStyle();
        styleC21.setAlignment(styleC21.ALIGN_RIGHT);
        HSSFCellStyle styleC22 = wb.createCellStyle();
        styleC22.setAlignment(styleC22.ALIGN_LEFT);
        HSSFCellStyle styleC23 = wb.createCellStyle();
        styleC23.setAlignment(styleC22.ALIGN_CENTER);

        HSSFCellStyle styleC25 = wb.createCellStyle();
        styleC25.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC25.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC25.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleC25.setBorderBottom(HSSFCellStyle.BORDER_THIN);
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

        HSSFCellStyle styleC30 = wb.createCellStyle();
        styleC30.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC30.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC30.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleC30.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC30.setAlignment(styleC22.ALIGN_CENTER);

        if(!ticketSummaryAirline.isEmpty()){
            dataheader = (TicketSummaryAirlineView)ticketSummaryAirline.get(0);
            for(int x = 1 ; x < 4 ; x++){
                if( x == 1){
                    // set Header Report (Row 1)
                    HSSFCellStyle styleC1 = wb.createCellStyle();
                    HSSFRow row1 = sheetPax.createRow(0);
                    HSSFCell cell1 = row1.createCell(0);
                    cell1.setCellValue("List Summary Airline");
                    styleC1.setFont(getHeaderFont(wb.createFont()));
                    cell1.setCellStyle(styleC1);
                    sheetPax.addMergedRegion(CellRangeAddress.valueOf("A1:E1"));

                    // Row 2
                    HSSFRow row2 = sheetPax.createRow(1);
                    HSSFCell cell21 = row2.createCell(0);
                    cell21.setCellValue("Invoice Date : ");
                    cell21.setCellStyle(styleC21);
                    HSSFCell cell22 = row2.createCell(1);
                    cell22.setCellValue(dataheader.getHeaderdatefrom());
                    cell22.setCellStyle(styleC22);
                    //sheetPax.addMergedRegion(CellRangeAddress.valueOf("B2:C2"));
                    HSSFCell cell23 = row2.createCell(2);
                    if(!"".equalsIgnoreCase(dataheader.getHeaderdateto())){
                        cell23.setCellValue(" to " +dataheader.getHeaderdateto());
                        cell23.setCellStyle(styleC22);
                    }
                    HSSFCell cell24 = row2.createCell(3);
                    cell24.setCellValue("Print on : ");
                    cell24.setCellStyle(styleC21);
                    HSSFCell cell25 = row2.createCell(4);
                    cell25.setCellValue(dataheader.getHeaderprinton());
                    cell25.setCellStyle(styleC22);

                    // Row 3
                    HSSFRow row3 = sheetPax.createRow(2);
                    HSSFCell cell31 = row3.createCell(0);
                    cell31.setCellValue("Print By : ");
                    cell31.setCellStyle(styleC21);
                    HSSFCell cell32 = row3.createCell(1);
                    cell32.setCellValue(dataheader.getHeaderprintby());
                    cell32.setCellStyle(styleC22);
                    HSSFCell cell33 = row3.createCell(3);
                    cell33.setCellValue("Routing Detail : ");
                    cell33.setCellStyle(styleC21);
                    HSSFCell cell34 = row3.createCell(4);
                    cell34.setCellValue(dataheader.getHeaderroutingdetail());
                    cell34.setCellStyle(styleC22);

                    // Row 4
                    HSSFRow row4 = sheetPax.createRow(3);
                    HSSFCell cell41 = row4.createCell(0);
                    cell41.setCellValue("Type Routing : ");
                    cell41.setCellStyle(styleC21);
                    HSSFCell cell42 = row4.createCell(1);
                    cell42.setCellValue(dataheader.getHeadertyperouting());
                    cell42.setCellStyle(styleC22);
                    HSSFCell cell43 = row4.createCell(3);
                    cell43.setCellValue("Passenger : ");
                    cell43.setCellStyle(styleC21);
                    HSSFCell cell44 = row4.createCell(4);
                    cell44.setCellValue(dataheader.getHeaderpassenger());
                    cell44.setCellStyle(styleC22);

                    // Row 5
                    HSSFRow row5 = sheetPax.createRow(4);
                    HSSFCell cell51 = row5.createCell(0);
                    cell51.setCellValue("Air : ");
                    cell51.setCellStyle(styleC21);
                    HSSFCell cell52 = row5.createCell(1);
                    cell52.setCellValue(dataheader.getHeaderair());
                    cell52.setCellStyle(styleC22);
                    HSSFCell cell53 = row5.createCell(3);
                    cell53.setCellValue("Sale Staff : ");
                    cell53.setCellStyle(styleC21);
                    HSSFCell cell54 = row5.createCell(4);
                    cell54.setCellValue(dataheader.getHeadersalestaff());
                    cell54.setCellStyle(styleC22);

                    // Row 6
                    HSSFRow row6 = sheetPax.createRow(5);
                    HSSFCell cell61 = row6.createCell(0);
                    cell61.setCellValue("Agent Name : ");
                    cell61.setCellStyle(styleC21);
                    HSSFCell cell62 = row6.createCell(1);
                    cell62.setCellValue(dataheader.getHeaderagentname());
                    cell62.setCellStyle(styleC22);
                    HSSFCell cell63 = row6.createCell(3);
                    cell63.setCellValue("Department : ");
                    cell63.setCellStyle(styleC21);
                    HSSFCell cell64 = row6.createCell(4);
                    cell64.setCellValue(dataheader.getHeaderdepartment());
                    cell64.setCellStyle(styleC22);

                    // Row 7
                    HSSFRow row7 = sheetPax.createRow(6);
                    HSSFCell cell71 = row7.createCell(0);
                    cell71.setCellValue("Term Pay : ");
                    cell71.setCellStyle(styleC21);
                    HSSFCell cell72 = row7.createCell(1);
                    cell72.setCellValue(dataheader.getHeadertermpay());
                    cell72.setCellStyle(styleC22);
                }
                if( x == 2){
                    // set Header Report (Row 1)
                    HSSFCellStyle styleC1 = wb.createCellStyle();
                    HSSFRow row1 = sheetDetail.createRow(0);
                    HSSFCell cell1 = row1.createCell(0);
                    cell1.setCellValue("List Summary Airline");
                    styleC1.setFont(getHeaderFont(wb.createFont()));
                    cell1.setCellStyle(styleC1);
                    sheetDetail.addMergedRegion(CellRangeAddress.valueOf("A1:E1"));

                    // Row 2
                    HSSFRow row2 = sheetDetail.createRow(1);
                    HSSFCell cell21 = row2.createCell(0);
                    cell21.setCellValue("Invoice Date : ");
                    cell21.setCellStyle(styleC21);
                    HSSFCell cell22 = row2.createCell(1);
                    cell22.setCellValue(dataheader.getHeaderdatefrom());
                    cell22.setCellStyle(styleC22);
                    //sheetDetail.addMergedRegion(CellRangeAddress.valueOf("B2:C2"));
                    HSSFCell cell23 = row2.createCell(2);
                    if(!"".equalsIgnoreCase(dataheader.getHeaderdateto())){
                        cell23.setCellValue(" to " +dataheader.getHeaderdateto());
                        cell23.setCellStyle(styleC22);
                    }
                    HSSFCell cell24 = row2.createCell(3);
                    cell24.setCellValue("Print on : ");
                    cell24.setCellStyle(styleC21);
                    HSSFCell cell25 = row2.createCell(4);
                    cell25.setCellValue(dataheader.getHeaderprinton());
                    cell25.setCellStyle(styleC22);

                    // Row 3
                    HSSFRow row3 = sheetDetail.createRow(2);
                    HSSFCell cell31 = row3.createCell(0);
                    cell31.setCellValue("Print By : ");
                    cell31.setCellStyle(styleC21);
                    HSSFCell cell32 = row3.createCell(1);
                    cell32.setCellValue(dataheader.getHeaderprintby());
                    cell32.setCellStyle(styleC22);
                    HSSFCell cell33 = row3.createCell(3);
                    cell33.setCellValue("Routing Detail : ");
                    cell33.setCellStyle(styleC21);
                    HSSFCell cell34 = row3.createCell(4);
                    cell34.setCellValue(dataheader.getHeaderroutingdetail());
                    cell34.setCellStyle(styleC22);

                    // Row 4
                    HSSFRow row4 = sheetDetail.createRow(3);
                    HSSFCell cell41 = row4.createCell(0);
                    cell41.setCellValue("Type Routing : ");
                    cell41.setCellStyle(styleC21);
                    HSSFCell cell42 = row4.createCell(1);
                    cell42.setCellValue(dataheader.getHeadertyperouting());
                    cell42.setCellStyle(styleC22);
                    HSSFCell cell43 = row4.createCell(3);
                    cell43.setCellValue("Passenger : ");
                    cell43.setCellStyle(styleC21);
                    HSSFCell cell44 = row4.createCell(4);
                    cell44.setCellValue(dataheader.getHeaderpassenger());
                    cell44.setCellStyle(styleC22);

                    // Row 5
                    HSSFRow row5 = sheetDetail.createRow(4);
                    HSSFCell cell51 = row5.createCell(0);
                    cell51.setCellValue("Air : ");
                    cell51.setCellStyle(styleC21);
                    HSSFCell cell52 = row5.createCell(1);
                    cell52.setCellValue(dataheader.getHeaderair());
                    cell52.setCellStyle(styleC22);
                    HSSFCell cell53 = row5.createCell(3);
                    cell53.setCellValue("Sale Staff : ");
                    cell53.setCellStyle(styleC21);
                    HSSFCell cell54 = row5.createCell(4);
                    cell54.setCellValue(dataheader.getHeadersalestaff());
                    cell54.setCellStyle(styleC22);

                    // Row 6
                    HSSFRow row6 = sheetDetail.createRow(5);
                    HSSFCell cell61 = row6.createCell(0);
                    cell61.setCellValue("Agent Name : ");
                    cell61.setCellStyle(styleC21);
                    HSSFCell cell62 = row6.createCell(1);
                    cell62.setCellValue(dataheader.getHeaderagentname());
                    cell62.setCellStyle(styleC22);
                    HSSFCell cell63 = row6.createCell(3);
                    cell63.setCellValue("Department : ");
                    cell63.setCellStyle(styleC21);
                    HSSFCell cell64 = row6.createCell(4);
                    cell64.setCellValue(dataheader.getHeaderdepartment());
                    cell64.setCellStyle(styleC22);

                    // Row 7
                    HSSFRow row7 = sheetDetail.createRow(6);
                    HSSFCell cell71 = row7.createCell(0);
                    cell71.setCellValue("Term Pay : ");
                    cell71.setCellStyle(styleC21);
                    HSSFCell cell72 = row7.createCell(1);
                    cell72.setCellValue(dataheader.getHeadertermpay());
                    cell72.setCellStyle(styleC22);
                }
                if( x == 3){
                    // set Header Report (Row 1)
                    HSSFCellStyle styleC1 = wb.createCellStyle();
                    HSSFRow row1 = sheetRounting.createRow(0);
                    HSSFCell cell1 = row1.createCell(0);
                    cell1.setCellValue("List Summary Airline");
                    styleC1.setFont(getHeaderFont(wb.createFont()));
                    cell1.setCellStyle(styleC1);
                    sheetRounting.addMergedRegion(CellRangeAddress.valueOf("A1:E1"));

                    // Row 2
                    HSSFRow row2 = sheetRounting.createRow(1);
                    HSSFCell cell21 = row2.createCell(0);
                    cell21.setCellValue("Invoice Date : ");
                    cell21.setCellStyle(styleC21);
                    HSSFCell cell22 = row2.createCell(1);
                    cell22.setCellValue(dataheader.getHeaderdatefrom());
                    cell22.setCellStyle(styleC22);
                    //sheetRounting.addMergedRegion(CellRangeAddress.valueOf("B2:C2"));
                    HSSFCell cell23 = row2.createCell(2);
                    if(!"".equalsIgnoreCase(dataheader.getHeaderdateto())){
                        cell23.setCellValue(" to " +dataheader.getHeaderdateto());
                        cell23.setCellStyle(styleC22);
                    }
                    HSSFCell cell24 = row2.createCell(3);
                    cell24.setCellValue("Print on : ");
                    cell24.setCellStyle(styleC21);
                    HSSFCell cell25 = row2.createCell(4);
                    cell25.setCellValue(dataheader.getHeaderprinton());
                    cell25.setCellStyle(styleC22);

                    // Row 3
                    HSSFRow row3 = sheetRounting.createRow(2);
                    HSSFCell cell31 = row3.createCell(0);
                    cell31.setCellValue("Print By : ");
                    cell31.setCellStyle(styleC21);
                    HSSFCell cell32 = row3.createCell(1);
                    cell32.setCellValue(dataheader.getHeaderprintby());
                    cell32.setCellStyle(styleC22);
                    HSSFCell cell33 = row3.createCell(3);
                    cell33.setCellValue("Routing Detail : ");
                    cell33.setCellStyle(styleC21);
                    HSSFCell cell34 = row3.createCell(4);
                    cell34.setCellValue(dataheader.getHeaderroutingdetail());
                    cell34.setCellStyle(styleC22);

                    // Row 4
                    HSSFRow row4 = sheetRounting.createRow(3);
                    HSSFCell cell41 = row4.createCell(0);
                    cell41.setCellValue("Type Routing : ");
                    cell41.setCellStyle(styleC21);
                    HSSFCell cell42 = row4.createCell(1);
                    cell42.setCellValue(dataheader.getHeadertyperouting());
                    cell42.setCellStyle(styleC22);
                    HSSFCell cell43 = row4.createCell(3);
                    cell43.setCellValue("Passenger : ");
                    cell43.setCellStyle(styleC21);
                    HSSFCell cell44 = row4.createCell(4);
                    cell44.setCellValue(dataheader.getHeaderpassenger());
                    cell44.setCellStyle(styleC22);

                    // Row 5
                    HSSFRow row5 = sheetRounting.createRow(4);
                    HSSFCell cell51 = row5.createCell(0);
                    cell51.setCellValue("Air : ");
                    cell51.setCellStyle(styleC21);
                    HSSFCell cell52 = row5.createCell(1);
                    cell52.setCellValue(dataheader.getHeaderair());
                    cell52.setCellStyle(styleC22);
                    HSSFCell cell53 = row5.createCell(3);
                    cell53.setCellValue("Sale Staff : ");
                    cell53.setCellStyle(styleC21);
                    HSSFCell cell54 = row5.createCell(4);
                    cell54.setCellValue(dataheader.getHeadersalestaff());
                    cell54.setCellStyle(styleC22);

                    // Row 6
                    HSSFRow row6 = sheetRounting.createRow(5);
                    HSSFCell cell61 = row6.createCell(0);
                    cell61.setCellValue("Agent Name : ");
                    cell61.setCellStyle(styleC21);
                    HSSFCell cell62 = row6.createCell(1);
                    cell62.setCellValue(dataheader.getHeaderagentname());
                    cell62.setCellStyle(styleC22);
                    HSSFCell cell63 = row6.createCell(3);
                    cell63.setCellValue("Department : ");
                    cell63.setCellStyle(styleC21);
                    HSSFCell cell64 = row6.createCell(4);
                    cell64.setCellValue(dataheader.getHeaderdepartment());
                    cell64.setCellStyle(styleC22);

                    // Row 7
                    HSSFRow row7 = sheetRounting.createRow(6);
                    HSSFCell cell71 = row7.createCell(0);
                    cell71.setCellValue("Term Pay : ");
                    cell71.setCellStyle(styleC21);
                    HSSFCell cell72 = row7.createCell(1);
                    cell72.setCellValue(dataheader.getHeadertermpay());
                    cell72.setCellStyle(styleC22);
                }
            }
            // Header Table
            HSSFCellStyle styleC3 = wb.createCellStyle();
            styleC3.setFont(getHeaderTable(wb.createFont()));
            styleC3.setAlignment(styleC3.ALIGN_CENTER);
            styleC3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            styleC3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            styleC3.setBorderRight(HSSFCellStyle.BORDER_THIN);
            styleC3.setBorderTop(HSSFCellStyle.BORDER_THIN);
            
            int count = 9;
            int tempcount = 0 ;
            int tempcount2 = 0 ;
            int rowdetail = 0;
            int rowrouting = 0;
            int x = 0;
            int y = 0;
            for(int i=0;i<ticketSummaryAirline.size();i++){
                TicketSummaryAirlineView data = (TicketSummaryAirlineView)ticketSummaryAirline.get(i);
                
                if("pax".equalsIgnoreCase(data.getPage())){
                    //pax
                    HSSFRow row8 = sheetPax.createRow(8);
                    HSSFCell cell81 = row8.createCell(0);
                    cell81.setCellValue("Pax");
                    cell81.setCellStyle(styleC3);
                    sheetPax.autoSizeColumn(0);
                    HSSFCell cell82 = row8.createCell(1);
                    cell82.setCellValue("Payment Type");
                    cell82.setCellStyle(styleC3);
                    sheetPax.autoSizeColumn(1);
                    HSSFCell cell83 = row8.createCell(2);
                    cell83.setCellValue("Type Routing");
                    sheetPax.autoSizeColumn(2);
                    cell83.setCellStyle(styleC3);
                    HSSFCell cell84 = row8.createCell(3);
                    cell84.setCellValue("Net Sales");
                    cell84.setCellStyle(styleC3);
                    sheetPax.autoSizeColumn(3);
                    HSSFCell cell85 = row8.createCell(4);
                    cell85.setCellValue("Tax");
                    cell85.setCellStyle(styleC3);
                    sheetPax.autoSizeColumn(4);
                    HSSFCell cell86 = row8.createCell(5);
                    cell86.setCellValue("Ins.");
                    cell86.setCellStyle(styleC3);
                    sheetPax.autoSizeColumn(5);
                    HSSFCell cell87 = row8.createCell(6);
                    cell87.setCellValue("Comms");
                    cell87.setCellStyle(styleC3);
                    sheetPax.autoSizeColumn(6);
                    HSSFCell cell88 = row8.createCell(7);
                    cell88.setCellValue("Amount Wendy");
                    cell88.setCellStyle(styleC3);
                    sheetPax.autoSizeColumn(7);
                    HSSFCell cell89 = row8.createCell(8);
                    cell89.setCellValue("Amount Inbound");
                    cell89.setCellStyle(styleC3);
                    sheetPax.autoSizeColumn(8);
                    
                    HSSFRow row = sheetPax.createRow(count + i);
                    HSSFCell celldata0 = row.createCell(0);
                    celldata0.setCellValue("".equalsIgnoreCase(String.valueOf(data.getPaxP())) ? 0 : (new BigDecimal(data.getPaxP())).doubleValue());
                    celldata0.setCellStyle(styleC26);

                    HSSFCell celldata1 = row.createCell(1);
                    celldata1.setCellValue(String.valueOf(data.getPaymenttypeP()));
                    celldata1.setCellStyle(styleC30);

                    HSSFCell celldata2 = row.createCell(2);
                    celldata2.setCellValue(String.valueOf(data.getTyperoutingP()));
                    celldata2.setCellStyle(styleC30);

                    HSSFCell celldata3 = row.createCell(3);
                    celldata3.setCellValue("".equalsIgnoreCase(String.valueOf(data.getNetsalesP())) ? 0 : (new BigDecimal(data.getNetsalesP())).doubleValue());
                    celldata3.setCellStyle(styleC25);

                    HSSFCell celldata4 = row.createCell(4);
                    celldata4.setCellValue("".equalsIgnoreCase(String.valueOf(data.getTaxP())) ? 0 : (new BigDecimal(data.getTaxP())).doubleValue());
                    celldata4.setCellStyle(styleC25);

                    HSSFCell celldata5 = row.createCell(5);
                    celldata5.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInsP())) ? 0 : (new BigDecimal(data.getInsP())).doubleValue());
                    celldata5.setCellStyle(styleC25);

                    HSSFCell celldata6 = row.createCell(6);
                    celldata6.setCellValue("".equalsIgnoreCase(String.valueOf(data.getCommsP())) ? 0 : (new BigDecimal(data.getCommsP())).doubleValue());
                    celldata6.setCellStyle(styleC25);

                    HSSFCell celldata7 = row.createCell(7);
                    celldata7.setCellValue("".equalsIgnoreCase(String.valueOf(data.getAmountwendyP())) ? 0 : (new BigDecimal(data.getAmountwendyP())).doubleValue());
                    celldata7.setCellStyle(styleC25);

                    HSSFCell celldata8 = row.createCell(8);
                    celldata8.setCellValue("".equalsIgnoreCase(String.valueOf(data.getAmountinboundP())) ? 0 : (new BigDecimal(data.getAmountinboundP())).doubleValue());
                    celldata8.setCellStyle(styleC25);
                    tempcount = count + i + 1;
                }
                
                //detail
                if("detail".equalsIgnoreCase(data.getPage())){
                    //Total Pax
                    HSSFRow rowtotal = sheetPax.createRow(tempcount);
                    String totalPax = "SUM(A" + 10+":A"+(tempcount)+")";
                    String totalNet = "SUM(D" + 10+":D"+(tempcount)+")";
                    String totalTax = "SUM(E" + 10+":E"+(tempcount)+")";
                    String totalIns = "SUM(F" + 10+":F"+(tempcount)+")";
                    String totalComms = "SUM(G" + 10+":G"+(tempcount)+")";
                    String totalAmountWen = "SUM(H" + 10+":H"+(tempcount)+")";
                    String totalAmountIn = "SUM(I" + 10+":I"+(tempcount)+")";

                    HSSFCellStyle styleTotal = wb.createCellStyle();
                    styleTotal.setFont(getHeaderTable(wb.createFont()));
                    styleTotal.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                    styleTotal.setBorderRight(HSSFCellStyle.BORDER_THIN);
                    styleTotal.setBorderTop(HSSFCellStyle.BORDER_THIN);
                    styleTotal.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                    styleTotal.setAlignment(styleC22.ALIGN_RIGHT);

                    HSSFCell cellTotal0 = rowtotal.createCell(0);
                    cellTotal0.setCellStyle(styleC29);
                    HSSFCell cellTotal00 = rowtotal.createCell(1);
                    cellTotal00.setCellValue("Total");
                    cellTotal00.setCellStyle(styleTotal);
                    HSSFCell cellTotal01 = rowtotal.createCell(2);
                    cellTotal01.setCellFormula(totalPax);
                    cellTotal01.setCellStyle(styleC26);
                    HSSFCell cellTotal02 = rowtotal.createCell(3);
                    cellTotal02.setCellFormula(totalNet);
                    cellTotal02.setCellStyle(styleC25);
                    HSSFCell cellTotal03 = rowtotal.createCell(4);
                    cellTotal03.setCellFormula(totalTax);
                    cellTotal03.setCellStyle(styleC25);
                    HSSFCell cellTotal04 = rowtotal.createCell(5);
                    cellTotal04.setCellFormula(totalIns);
                    cellTotal04.setCellStyle(styleC25);
                    HSSFCell cellTotal05 = rowtotal.createCell(6);
                    cellTotal05.setCellFormula(totalComms);
                    cellTotal05.setCellStyle(styleC25);
                    HSSFCell cellTotal06 = rowtotal.createCell(7);
                    cellTotal06.setCellFormula(totalAmountWen);
                    cellTotal06.setCellStyle(styleC25);
                    HSSFCell cellTotal07 = rowtotal.createCell(8);
                    cellTotal07.setCellFormula(totalAmountIn);
                    cellTotal07.setCellStyle(styleC25);
                    
//                    if(tempcount != 0){
//                        rowdetail = tempcount+3;
//                    }
                    HSSFRow row8 = sheetDetail.createRow(8);
                    HSSFCell cell81 = row8.createCell(0);
                    cell81.setCellValue("Invoice No");
                    cell81.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(0);
                    HSSFCell cell82 = row8.createCell(1);
                    cell82.setCellValue("Invoice Date");
                    cell82.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(1);
                    HSSFCell cell83 = row8.createCell(2);
                    cell83.setCellValue("Department");
                    sheetDetail.autoSizeColumn(2);
                    cell83.setCellStyle(styleC3);
                    HSSFCell cell84 = row8.createCell(3);
                    cell84.setCellValue("Staff");
                    cell84.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(3);
                    HSSFCell cell85 = row8.createCell(4);
                    cell85.setCellValue("Term Pay");
                    cell85.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(4);
                    HSSFCell cell86 = row8.createCell(5);
                    cell86.setCellValue("Passenger");
                    cell86.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(5);
                    HSSFCell cell87 = row8.createCell(6);
                    cell87.setCellValue("Type Payment");
                    cell87.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(6);
                    HSSFCell cell88 = row8.createCell(7);
                    cell88.setCellValue("Type Routing");
                    cell88.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(7);
                    HSSFCell cell89 = row8.createCell(8);
                    cell89.setCellValue("Routing");
                    cell89.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(8);
                    HSSFCell cell90 = row8.createCell(9);
                    cell90.setCellValue("Pax");
                    cell90.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(9);

                    HSSFCell cell91 = row8.createCell(10);
                    cell91.setCellValue("Air");
                    cell91.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(10);
                    HSSFCell cell92 = row8.createCell(11);
                    cell92.setCellValue("Ticket No");
                    cell92.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(11);
                    HSSFCell cell93 = row8.createCell(12);
                    cell93.setCellValue("Ref No.");
                    cell93.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(12);
                    HSSFCell cell94 = row8.createCell(13);
                    cell94.setCellValue("Issue Date");
                    cell94.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(13);
                    HSSFCell cell95 = row8.createCell(14);
                    cell95.setCellValue("Net Sales");
                    cell95.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(14);
                    HSSFCell cell96 = row8.createCell(15);
                    cell96.setCellValue("Tax");
                    cell96.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(15);
                    HSSFCell cell97 = row8.createCell(16);
                    cell97.setCellValue("Ins.");
                    cell97.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(16);
                    HSSFCell cell98 = row8.createCell(17);
                    cell98.setCellValue("Comms");
                    cell98.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(17);
                    HSSFCell cell99 = row8.createCell(18);
                    cell99.setCellValue("Amount Wendy");
                    cell99.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(18);
                    HSSFCell cell910 = row8.createCell(19);
                    cell910.setCellValue("Amount Inbound");
                    cell910.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(19);
                    HSSFCell cell911 = row8.createCell(20);
                    cell911.setCellValue("Amt No Invoice");
                    cell911.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(20);
                    HSSFCell cell912 = row8.createCell(21);
                    cell912.setCellValue("Amt Business Trip");
                    cell912.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(21);
                    HSSFCell cell913 = row8.createCell(22);
                    cell913.setCellValue("Amt Annual Leave");
                    cell913.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(22);
                    HSSFCell cell914 = row8.createCell(23);
                    cell914.setCellValue("Amt Refund");
                    cell914.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(23);
                    HSSFCell cell915 = row8.createCell(24);
                    cell915.setCellValue("Remarks");
                    cell915.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(24);
                    HSSFCell cell916 = row8.createCell(25);
                    cell916.setCellValue("Diff");
                    cell916.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(25);
                
                    HSSFRow row = sheetDetail.createRow(count + x);
                    HSSFCell celldata0 = row.createCell(0);

                    celldata0.setCellValue(String.valueOf(data.getInvnoD()));
                    celldata0.setCellStyle(styleC29);

                    HSSFCell celldata1 = row.createCell(1);
                    celldata1.setCellValue(String.valueOf(data.getInvdateD()));
                    celldata1.setCellStyle(styleC30);

                    HSSFCell celldata2 = row.createCell(2);
                    celldata2.setCellValue(String.valueOf(data.getDepartmentD()));
                    celldata2.setCellStyle(styleC29);

                    HSSFCell celldata3 = row.createCell(3);
                    celldata3.setCellValue(String.valueOf(data.getStaffD()));
                    celldata3.setCellStyle(styleC29);

                    HSSFCell celldata4 = row.createCell(4);
                    celldata4.setCellValue(String.valueOf(data.getTermpayD()));
                    celldata4.setCellStyle(styleC29);

                    HSSFCell celldata5 = row.createCell(5);
                    celldata5.setCellValue(String.valueOf(data.getPassengerD()));
                    celldata5.setCellStyle(styleC29);

                    HSSFCell celldata6 = row.createCell(6);
                    celldata6.setCellValue(String.valueOf(data.getTypepaymentD()));
                    celldata6.setCellStyle(styleC29);

                    HSSFCell celldata7 = row.createCell(7);
                    celldata7.setCellValue(String.valueOf(data.getTyperoutingD()));
                    celldata7.setCellStyle(styleC29);

                    HSSFCell celldata8 = row.createCell(8);
                    celldata8.setCellValue(String.valueOf(data.getRoutingD()));
                    celldata8.setCellStyle(styleC29);

                    HSSFCell celldata9 = row.createCell(9);
                    celldata9.setCellValue("".equalsIgnoreCase(String.valueOf(data.getPaxD())) ? 0 : (new BigDecimal(data.getPaxD())).doubleValue());
                    celldata9.setCellStyle(styleC26);

                    HSSFCell celldata10 = row.createCell(10);
                    celldata10.setCellValue(String.valueOf(data.getAirD()));
                    celldata10.setCellStyle(styleC30);

                    HSSFCell celldata11 = row.createCell(11);
                    celldata11.setCellValue(String.valueOf(data.getTicketnoD()));
                    celldata11.setCellStyle(styleC29);
                    
                    HSSFCell celldata12 = row.createCell(12);
                    celldata12.setCellValue(String.valueOf(data.getRefnoD()));
                    celldata12.setCellStyle(styleC29);

                    HSSFCell celldata13 = row.createCell(13);
                    celldata13.setCellValue(String.valueOf(data.getIssuedateD()));
                    celldata13.setCellStyle(styleC29);

                    HSSFCell celldata14 = row.createCell(14);
                    celldata14.setCellValue("".equalsIgnoreCase(String.valueOf(data.getNetsalesD())) ? 0 : (new BigDecimal(data.getNetsalesD())).doubleValue());
                    celldata14.setCellStyle(styleC25);

                    HSSFCell celldata15 = row.createCell(15);
                    celldata15.setCellValue("".equalsIgnoreCase(String.valueOf(data.getTaxD())) ? 0 : (new BigDecimal(data.getTaxD())).doubleValue());
                    celldata15.setCellStyle(styleC25);

                    HSSFCell celldata16 = row.createCell(16);
                    celldata16.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInsD())) ? 0 : (new BigDecimal(data.getInsD())).doubleValue());
                    celldata16.setCellStyle(styleC25);

                    HSSFCell celldata17 = row.createCell(17);
                    celldata17.setCellValue("".equalsIgnoreCase(String.valueOf(data.getCommsD())) ? 0 : (new BigDecimal(data.getCommsD())).doubleValue());
                    celldata17.setCellStyle(styleC25);

                    HSSFCell celldata18 = row.createCell(18);
                    celldata18.setCellValue("".equalsIgnoreCase(String.valueOf(data.getAmountwendyD())) ? 0 : (new BigDecimal(data.getAmountwendyD())).doubleValue());
                    celldata18.setCellStyle(styleC25);
                    
                    HSSFCell celldata19 = row.createCell(19);
                    celldata19.setCellValue("".equalsIgnoreCase(String.valueOf(data.getAmountinboundD())) ? 0 : (new BigDecimal(data.getAmountinboundD())).doubleValue());
                    celldata19.setCellStyle(styleC25);

                    HSSFCell celldata20 = row.createCell(20);
                    celldata20.setCellValue("".equalsIgnoreCase(String.valueOf(data.getAmtnoinvoiceD())) ? 0 : (new BigDecimal(data.getAmtnoinvoiceD())).doubleValue());
                    celldata20.setCellStyle(styleC25);
                    
                    HSSFCell celldata21 = row.createCell(21);
                    celldata21.setCellValue("".equalsIgnoreCase(String.valueOf(data.getAmtbusinesstripD())) ? 0 : (new BigDecimal(data.getAmtbusinesstripD())).doubleValue());
                    celldata21.setCellStyle(styleC25);
                    
                    HSSFCell celldata22 = row.createCell(22);
                    celldata22.setCellValue("".equalsIgnoreCase(String.valueOf(data.getAmtannualleaveD())) ? 0 : (new BigDecimal(data.getAmtannualleaveD())).doubleValue());
                    celldata22.setCellStyle(styleC25);
                    
                    HSSFCell celldata23 = row.createCell(23);
                    celldata23.setCellValue("".equalsIgnoreCase(String.valueOf(data.getAmtrefundD())) ? 0 : (new BigDecimal(data.getAmtrefundD())).doubleValue());
                    celldata23.setCellStyle(styleC25);

                    HSSFCell celldata24 = row.createCell(24);
                    celldata24.setCellValue(String.valueOf(data.getRemarksD()));
                    celldata24.setCellStyle(styleC29);

                    HSSFCell celldata25 = row.createCell(25);
                    celldata25.setCellValue("".equalsIgnoreCase(String.valueOf(data.getDiffD())) ? 0 : (new BigDecimal(data.getDiffD())).doubleValue());
                    celldata25.setCellStyle(styleC25);
                    x ++ ;
//                    tempcount2 = count + i + 4;
                }
                
                if("routing".equalsIgnoreCase(data.getPage())){
//                    if(tempcount2 != 0){
//                        rowrouting = tempcount2+3;
//                    }
                    
                    HSSFRow row8 = sheetRounting.createRow(8);
                    HSSFCell cell81 = row8.createCell(0);
                    cell81.setCellValue("Routing");
                    cell81.setCellStyle(styleC3);
                    sheetRounting.autoSizeColumn(0);
                    HSSFCell cell82 = row8.createCell(1);
                    cell82.setCellValue("Pax");
                    cell82.setCellStyle(styleC3);
                    sheetRounting.autoSizeColumn(1);
                    HSSFCell cell83 = row8.createCell(2);
                    cell83.setCellValue("Net Sales");
                    sheetRounting.autoSizeColumn(2);
                    cell83.setCellStyle(styleC3);
                    HSSFCell cell84 = row8.createCell(3);
                    cell84.setCellValue("Tax");
                    cell84.setCellStyle(styleC3);
                    sheetRounting.autoSizeColumn(3);
                    HSSFCell cell85 = row8.createCell(4);
                    cell85.setCellValue("Ins.");
                    cell85.setCellStyle(styleC3);
                    sheetRounting.autoSizeColumn(4);
                    HSSFCell cell86 = row8.createCell(5);
                    cell86.setCellValue("Comms");
                    cell86.setCellStyle(styleC3);
                    sheetRounting.autoSizeColumn(5);
                    HSSFCell cell87 = row8.createCell(6);
                    cell87.setCellValue("Amount Wendy");
                    cell87.setCellStyle(styleC3);
                    sheetRounting.autoSizeColumn(6);
                    HSSFCell cell88 = row8.createCell(7);
                    cell88.setCellValue("Amount Inbound");
                    cell88.setCellStyle(styleC3);
                    sheetRounting.autoSizeColumn(7);
                    HSSFCell cell89 = row8.createCell(8);
                    cell89.setCellValue("Diff");
                    cell89.setCellStyle(styleC3);
                    sheetRounting.autoSizeColumn(8);
                    
                    
                    HSSFRow row = sheetRounting.createRow(count + y);

                    HSSFCell celldata0 = row.createCell(0);
                    celldata0.setCellValue(String.valueOf(data.getRoutingR()));
                    celldata0.setCellStyle(styleC29);

                    HSSFCell celldata1 = row.createCell(1);
                    celldata1.setCellValue("".equalsIgnoreCase(String.valueOf(data.getPaxR())) ? 0 : (new BigDecimal(data.getPaxR())).doubleValue());
                    celldata1.setCellStyle(styleC26);

                    HSSFCell celldata2 = row.createCell(2);
                    celldata2.setCellValue("".equalsIgnoreCase(String.valueOf(data.getNetsalesR())) ? 0 : (new BigDecimal(data.getNetsalesR())).doubleValue());
                    celldata2.setCellStyle(styleC25);

                    HSSFCell celldata3 = row.createCell(3);
                    celldata3.setCellValue("".equalsIgnoreCase(String.valueOf(data.getTaxR())) ? 0 : (new BigDecimal(data.getTaxR())).doubleValue());
                    celldata3.setCellStyle(styleC25);

                    HSSFCell celldata4 = row.createCell(4);
                    celldata4.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInsR())) ? 0 : (new BigDecimal(data.getInsR())).doubleValue());
                    celldata4.setCellStyle(styleC25);

                    HSSFCell celldata5 = row.createCell(5);
                    celldata5.setCellValue("".equalsIgnoreCase(String.valueOf(data.getCommsR())) ? 0 : (new BigDecimal(data.getCommsR())).doubleValue());
                    celldata5.setCellStyle(styleC25);

                    HSSFCell celldata6 = row.createCell(6);
                    celldata6.setCellValue("".equalsIgnoreCase(String.valueOf(data.getAmountwendyR())) ? 0 : (new BigDecimal(data.getAmountwendyR())).doubleValue());
                    celldata6.setCellStyle(styleC25);

                    HSSFCell celldata7 = row.createCell(7);
                    celldata7.setCellValue("".equalsIgnoreCase(String.valueOf(data.getAmountinboundR())) ? 0 : (new BigDecimal(data.getAmountinboundR())).doubleValue());
                    celldata7.setCellStyle(styleC25);

                    HSSFCell celldata8 = row.createCell(8);
                    celldata8.setCellValue("".equalsIgnoreCase(String.valueOf(data.getDiffR())) ? 0 : (new BigDecimal(data.getDiffR())).doubleValue());
                    celldata8.setCellStyle(styleC25);
                    y++;
                }
            }
            for (int i = 0; i < 30; i++) {
                sheetPax.autoSizeColumn(i);
                sheetDetail.autoSizeColumn(i);
                sheetRounting.autoSizeColumn(i);
            }
        }
    }
    
     private void getSummaryTicketAdjustCostAndIncome(HSSFWorkbook wb, List summaryTicketCostIncome){
        List<ListSummaryTicketAdjustCostAndIncome> listTotal = summaryTicketCostIncome;

        String sheetName = "income";// name of sheet
        String sheetName1 = "income_summary";
        HSSFSheet sheet = wb.createSheet(sheetName);
        HSSFSheet sheet1 = wb.createSheet(sheetName1);
        HSSFDataFormat currency = wb.createDataFormat();
    
        // line table
        HSSFCellStyle styleBorderTop = wb.createCellStyle();// use
            styleBorderTop.setBorderTop(styleBorderTop.BORDER_THIN);
        HSSFCellStyle styleAlignRightBorderAllHeaderTable = wb .createCellStyle(); // use
            styleAlignRightBorderAllHeaderTable.setFont(getHeaderTable(wb.createFont()));
            styleAlignRightBorderAllHeaderTable.setAlignment(styleAlignRightBorderAllHeaderTable.ALIGN_CENTER);
            styleAlignRightBorderAllHeaderTable.setBorderTop(styleAlignRightBorderAllHeaderTable.BORDER_THIN);
            styleAlignRightBorderAllHeaderTable.setBorderBottom(styleAlignRightBorderAllHeaderTable.BORDER_THIN);
            styleAlignRightBorderAllHeaderTable.setBorderRight(styleAlignRightBorderAllHeaderTable.BORDER_THIN);
            styleAlignRightBorderAllHeaderTable.setBorderLeft(styleAlignRightBorderAllHeaderTable.BORDER_THIN);

        // Header Table
        HSSFCellStyle styleDetailTable = wb.createCellStyle(); // use
            styleDetailTable.setAlignment(styleDetailTable.ALIGN_LEFT);
            styleDetailTable.setBorderLeft(styleDetailTable.BORDER_THIN);
            styleDetailTable.setBorderRight(styleDetailTable.BORDER_THIN);
        HSSFCellStyle styleDetailTableNumber = wb.createCellStyle(); //use
            styleDetailTableNumber.setDataFormat(currency.getFormat("#,##0.00"));
            styleDetailTableNumber.setAlignment(styleDetailTableNumber.ALIGN_RIGHT);
            styleDetailTableNumber.setBorderLeft(styleDetailTableNumber.BORDER_THIN);
            styleDetailTableNumber.setBorderRight(styleDetailTableNumber.BORDER_THIN);
        HSSFCellStyle styleDetailTableBorderBottomTop = wb.createCellStyle(); // use
                styleDetailTableBorderBottomTop.setBorderTop(styleDetailTableBorderBottomTop.BORDER_THIN);
                styleDetailTableBorderBottomTop.setBorderRight(styleDetailTableBorderBottomTop.BORDER_THIN);

        // Sheet Detail (2)
        // set Header Report (Row 1)
        HSSFCellStyle styleC11 = wb.createCellStyle();
        HSSFRow row01 = sheet.createRow(0);
        HSSFCell cell01 = row01.createCell(0);
        cell01.setCellValue("Summary Ticket Adjust Cost & Income");
        styleC11.setFont(getHeaderFont(wb.createFont()));
        cell01.setCellStyle(styleC11);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:H1"));

        // Set align Text
        HSSFCellStyle styleC21 = wb.createCellStyle();
        styleC21.setAlignment(styleC21.ALIGN_RIGHT);
        HSSFCellStyle styleC22 = wb.createCellStyle();
        styleC22.setAlignment(styleC22.ALIGN_LEFT);

        List<SummaryTicketAdjustCostAndIncome> incomeTemp = listTotal.get(0).getSummaryTicketAdjustAndIncome();
        SummaryTicketAdjustCostAndIncome income = new SummaryTicketAdjustCostAndIncome();
        if((incomeTemp != null)&&(incomeTemp.size() != 0)){
            income = (SummaryTicketAdjustCostAndIncome)incomeTemp.get(0);
        }
        // Row 2
        HSSFRow row02 = sheet.createRow(1);
        HSSFCell cell021 = row02.createCell(0);
        cell021.setCellValue("Invoice Date : ");
        cell021.setCellStyle(styleC21);
        HSSFCell cell022 = row02.createCell(1);
        cell022.setCellValue(income.getInvoicedatePage());
        cell022.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B2:D2"));
        HSSFCell cell023 = row02.createCell(4);
        cell023.setCellValue("Print : ");
        cell023.setCellStyle(styleC21);
        HSSFCell cell024 = row02.createCell(5);
        cell024.setCellValue(income.getPrintbyPage());
        cell024.setCellStyle(styleC22);

        // Row 3
        HSSFRow row03 = sheet.createRow(2);
        HSSFCell cell031 = row03.createCell(0);
        cell031.setCellValue("Issue Date : ");
        cell031.setCellStyle(styleC21);
        HSSFCell cell032 = row03.createCell(1);
        cell032.setCellValue(income.getIssuedatePage());
        cell032.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B3:D3"));
        HSSFCell cell033 = row03.createCell(4);
        cell033.setCellValue("Page : ");
        cell033.setCellStyle(styleC21);
        HSSFCell cell034 = row03.createCell(5);
        cell034.setCellValue("1");
        cell034.setCellStyle(styleC22);

        // Row 4
        HSSFRow row04 = sheet.createRow(3);
        HSSFCell cell041 = row04.createCell(0);
        cell041.setCellValue("Department : ");
        cell041.setCellStyle(styleC21);
        HSSFCell cell042 = row04.createCell(1);
        cell042.setCellValue(income.getDepartmentPage());
        cell042.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B4:D4"));

        // Row 5
        HSSFRow row05 = sheet.createRow(4);
        HSSFCell cell051 = row05.createCell(0);
        cell051.setCellValue("Sale Staff : ");
        cell051.setCellStyle(styleC21);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B5:D5"));
        HSSFCell cell052 = row05.createCell(1);
        cell052.setCellValue(income.getSalsestaffPage());
        cell052.setCellStyle(styleC22);

        // Row 6
        HSSFRow row06 = sheet.createRow(5);
        HSSFCell cell611 = row06.createCell(0);
        cell611.setCellValue("Term Pay : ");
        cell611.setCellStyle(styleC21);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B6:D6"));
        HSSFCell cell621 = row06.createCell(1);
        cell621.setCellValue(income.getTermPayPage());
        cell621.setCellStyle(styleC22);

        // Header Table
        HSSFRow row6 = sheet.createRow(8);
        HSSFCell cell61 = row6.createCell(0);
        cell61.setCellValue("Type Pay");
        cell61.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(0);
        HSSFCell cell62 = row6.createCell(1);
        cell62.setCellValue("Type Route");
        cell62.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(1);
        HSSFCell cell63 = row6.createCell(2);
        cell63.setCellValue("Pax");
        sheet.autoSizeColumn(2);
        cell63.setCellStyle(styleAlignRightBorderAllHeaderTable);
        HSSFCell cell64 = row6.createCell(3);
        cell64.setCellValue("Air");
        cell64.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(3);
        HSSFCell cell65 = row6.createCell(4);
        cell65.setCellValue("Inv. No.");
        cell65.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(4);
        HSSFCell cell66 = row6.createCell(5);
        cell66.setCellValue("Cost Inv");
        cell66.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(5);
        HSSFCell cell67 = row6.createCell(6);
        cell67.setCellValue("Invoice Wendy");
        cell67.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(6);
        HSSFCell cell68 = row6.createCell(7);
        cell68.setCellValue("Invoice Outbound");
        cell68.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(7);
        HSSFCell cell69 = row6.createCell(8);
        cell69.setCellValue("Over");
        cell69.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(8);
        HSSFCell cell70 = row6.createCell(9);
        cell70.setCellValue("  Little  ");
        cell70.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(9);
        HSSFCell cell71 = row6.createCell(10);
        cell71.setCellValue("Discount");
        cell71.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(10);
        HSSFCell cell72 = row6.createCell(11);
        cell72.setCellValue("Cancel");
        cell72.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(11);
        HSSFCell cell73 = row6.createCell(12);
        cell73.setCellValue("Wait Pay");
        cell73.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(12);
        HSSFCell cell74 = row6.createCell(13);
        cell74.setCellValue("RC AG Com");
        cell74.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(13);
        HSSFCell cell75 = row6.createCell(14);
        cell75.setCellValue("Total Balance");
        cell75.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(14);
        
        List<SummaryTicketAdjustCostAndIncome> listSummaryTicketCostIncome = listTotal.get(0).getSummaryTicketAdjustAndIncome();
        
        int count = 9 + listSummaryTicketCostIncome.size();
        int start = 11;
        int end = 0;
        int num = 0;

        for (int r = 9; r < count; r++) {
            if(num < listSummaryTicketCostIncome.size()){
                HSSFRow row = sheet.createRow(r);
                HSSFCell cell1 = row.createCell(0);
                    cell1.setCellValue(listSummaryTicketCostIncome.get(num).getTypepayment());
                    cell1.setCellStyle(styleDetailTable);
                HSSFCell cell2 = row.createCell(1);
                    cell2.setCellValue(listSummaryTicketCostIncome.get(num).getTyperounting());
                    cell2.setCellStyle(styleDetailTable);
                HSSFCell cell3 = row.createCell(2);
                        BigDecimal pax = new BigDecimal(listSummaryTicketCostIncome.get(num).getPax());
                    cell3.setCellValue((pax != null) ? pax.doubleValue() : new BigDecimal("0").doubleValue() );
                    cell3.setCellStyle(styleDetailTableNumber);
                HSSFCell cell4 = row.createCell(3);
                    cell4.setCellValue(listSummaryTicketCostIncome.get(num).getAir());
                    cell4.setCellStyle(styleDetailTable);
                HSSFCell cell55 = row.createCell(4);
                    cell55.setCellValue(listSummaryTicketCostIncome.get(num).getInvno());
                    cell55.setCellStyle(styleDetailTable);
                HSSFCell cell5 = row.createCell(5);
                    System.out.println("Cost : " +listSummaryTicketCostIncome.get(num).getCostinv());
                    BigDecimal costinv = null;
                    if("".equals(listSummaryTicketCostIncome.get(num).getCostinv())){
                        costinv = new BigDecimal(0);
                    }else{
                        costinv = new BigDecimal(listSummaryTicketCostIncome.get(num).getCostinv());
                    }
                    cell5.setCellValue((costinv != null && !"0".equals(costinv)) ? costinv.doubleValue() : new BigDecimal("0").doubleValue() );
                    cell5.setCellStyle(styleDetailTableNumber);
                HSSFCell cell6 = row.createCell(6);
                    BigDecimal invwendy = null;
                    if("".equals(listSummaryTicketCostIncome.get(num).getInvoicewendy())){
                        invwendy = new BigDecimal(0);
                    }else{
                        invwendy = new BigDecimal(listSummaryTicketCostIncome.get(num).getInvoicewendy());
                    }
                    cell6.setCellValue((invwendy != null) ? invwendy.doubleValue() : new BigDecimal("0").doubleValue());
                    cell6.setCellStyle(styleDetailTableNumber);
                HSSFCell cell7 = row.createCell(7);
                    BigDecimal invoutbound = null;
                    if("".equals(listSummaryTicketCostIncome.get(num).getInvoiceoutbound())){
                        invoutbound = new BigDecimal(0);
                    }else{
                        invoutbound = new BigDecimal(listSummaryTicketCostIncome.get(num).getInvoiceoutbound());
                    }
                    cell7.setCellValue((invoutbound != null) ? invoutbound.doubleValue() : new BigDecimal("0").doubleValue());
                    cell7.setCellStyle(styleDetailTableNumber);
                HSSFCell cell8 = row.createCell(8);
                    cell8.setCellValue(listSummaryTicketCostIncome.get(num).getOver());
                    cell8.setCellStyle(styleDetailTable);
                HSSFCell cell9 = row.createCell(9);
                    BigDecimal little = null;
                    if("".equals(listSummaryTicketCostIncome.get(num).getLitter())){
                        little = new BigDecimal(0);
                    }else{
                        little = new BigDecimal(listSummaryTicketCostIncome.get(num).getLitter());
                    }
                    cell9.setCellValue((little != null) ? little.doubleValue() : new BigDecimal("0").doubleValue());
                    cell9.setCellStyle(styleDetailTableNumber);
                    sheet.autoSizeColumn(9);
                HSSFCell cell10 = row.createCell(10);
                    BigDecimal discount  = null;
                    if("".equals(listSummaryTicketCostIncome.get(num).getDiscount())){
                        discount = new BigDecimal(0);
                    }else{
                        discount = new BigDecimal(listSummaryTicketCostIncome.get(num).getDiscount());
                    }
                    cell10.setCellValue((discount != null) ? discount.doubleValue() : new BigDecimal("0").doubleValue());
                    cell10.setCellStyle(styleDetailTableNumber);
                HSSFCell cell11 = row.createCell(11);
                    BigDecimal cancel = null;
                    if("".equals(listSummaryTicketCostIncome.get(num).getCancel())){
                        cancel = new BigDecimal(0);
                    }else{
                        cancel = new BigDecimal(listSummaryTicketCostIncome.get(num).getCancel());
                    }
                    cell11.setCellValue((cancel != null) ? cancel.doubleValue() : new BigDecimal("0").doubleValue());
                    cell11.setCellStyle(styleDetailTableNumber);
                HSSFCell cell12 = row.createCell(12);
                    BigDecimal wait = null;
                    if("".equals(listSummaryTicketCostIncome.get(num).getWait_pay())){
                        wait = new BigDecimal(0);
                    }else{
                        wait = new BigDecimal(listSummaryTicketCostIncome.get(num).getWait_pay());
                    }
                    cell12.setCellValue((wait != null) ? wait.doubleValue() : new BigDecimal("0").doubleValue());
                    cell12.setCellStyle(styleDetailTableNumber);
                HSSFCell cell13 = row.createCell(13);
                    BigDecimal rc = null;
                    if("".equals(listSummaryTicketCostIncome.get(num).getRcagcom())){
                        rc = new BigDecimal(0);
                    }else{
                        rc = new BigDecimal(listSummaryTicketCostIncome.get(num).getRcagcom());
                    }
                    cell13.setCellValue((rc != null) ? rc.doubleValue() : new BigDecimal("0").doubleValue());
                    cell13.setCellStyle(styleDetailTableNumber);
                HSSFCell cell14 = row.createCell(14);
                    BigDecimal balance = null;
                    if("".equals(listSummaryTicketCostIncome.get(num).getTotal_balance())){
                        balance = new BigDecimal(0);
                    }else{
                        balance = new BigDecimal(listSummaryTicketCostIncome.get(num).getTotal_balance());
                    }
                    cell14.setCellValue((balance != null) ? balance.doubleValue() : new BigDecimal("0").doubleValue());
                    cell14.setCellStyle(styleDetailTableNumber);

                     num++;
            }
            for (int i = 0; i < listSummaryTicketCostIncome.size(); i++) {
                    sheet.autoSizeColumn(i);
            }
        }
        System.out.println(count);
        HSSFRow rowL = sheet.createRow(count);
        rowL.createCell(0).setCellStyle(styleDetailTableBorderBottomTop);
        rowL.createCell(1).setCellStyle(styleDetailTableBorderBottomTop);
        rowL.createCell(2).setCellStyle(styleDetailTableBorderBottomTop);
        rowL.createCell(3).setCellStyle(styleDetailTableBorderBottomTop);
        rowL.createCell(4).setCellStyle(styleDetailTableBorderBottomTop);
        rowL.createCell(5).setCellStyle(styleDetailTableBorderBottomTop);
        rowL.createCell(6).setCellStyle(styleDetailTableBorderBottomTop);
        rowL.createCell(7).setCellStyle(styleDetailTableBorderBottomTop);
        rowL.createCell(8).setCellStyle(styleDetailTableBorderBottomTop);
        rowL.createCell(9).setCellStyle(styleDetailTableBorderBottomTop);
        rowL.createCell(10).setCellStyle(styleDetailTableBorderBottomTop);
        rowL.createCell(11).setCellStyle(styleDetailTableBorderBottomTop);
        rowL.createCell(12).setCellStyle(styleDetailTableBorderBottomTop);
        rowL.createCell(13).setCellStyle(styleDetailTableBorderBottomTop);
        rowL.createCell(14).setCellStyle(styleDetailTableBorderBottomTop);

        String sumPax = "SUM(C" + 10+":C"+(count)+")";
        String sumCosInv = "SUM(F" + 10+":F"+(count)+")";
        String sumInvWendy = "SUM(G" + 10+":G"+(count)+")";
        String sumInvOutbound = "SUM(H" + 10+":H"+(count)+")";
        String sumLittle = "SUM(J" + 10+":J"+(count)+")";
        String sumDiscount = "SUM(K" + 10+":K"+(count)+")";
        String sumCancel = "SUM(L" + 10+":L"+(count)+")";
        String sumRC = "SUM(N" + 10+":N"+(count)+")";
        String sumWait = "SUM(M" + 10+":M"+(count)+")";
        String sumBlance = "SUM(O" + 10+":O"+(count)+")";

        HSSFRow row = sheet.createRow(count+1);
        HSSFCell cell60Sum = row.createCell(0);
                cell60Sum.setCellValue("");
                cell60Sum.setCellStyle(styleDetailTableNumber);
                sheet.autoSizeColumn(0);
        HSSFCell cell6Sum = row.createCell(1);
            cell6Sum.setCellValue("");
            cell6Sum.setCellStyle(styleDetailTableNumber);
            sheet.autoSizeColumn(1);
        HSSFCell cell7Sum = row.createCell(2);
            cell7Sum.setCellFormula(sumPax);
            sheet.autoSizeColumn(2);
            cell7Sum.setCellStyle(styleDetailTableNumber);
        HSSFCell cell8Sum = row.createCell(3);
            cell8Sum.setCellValue("");
            sheet.autoSizeColumn(3);
            cell8Sum.setCellStyle(styleDetailTableNumber);
        HSSFCell cell9Sum = row.createCell(4);
            cell9Sum.setCellValue("");
            cell9Sum.setCellStyle(styleDetailTableNumber);
            sheet.autoSizeColumn(4);
        HSSFCell cell10Sum = row.createCell(5);
            cell10Sum.setCellFormula(sumCosInv);
            cell10Sum.setCellStyle(styleDetailTableNumber);
            sheet.autoSizeColumn(5);
        HSSFCell cell11Sum = row.createCell(6);
            cell11Sum.setCellValue("");
            cell11Sum.setCellStyle(styleDetailTableNumber);
            sheet.autoSizeColumn(6);
        HSSFCell cell12Sum = row.createCell(7);
            cell12Sum.setCellFormula(sumInvOutbound);
            cell12Sum.setCellStyle(styleDetailTableNumber);
            sheet.autoSizeColumn(7);
        HSSFCell cell13Sum = row.createCell(8);
            cell13Sum.setCellValue("");
            cell13Sum.setCellStyle(styleDetailTableNumber);
            sheet.autoSizeColumn(8);
        HSSFCell cell14Sum = row.createCell(9);
               cell14Sum.setCellFormula(sumLittle);
               cell14Sum.setCellStyle(styleDetailTableNumber);
               sheet.autoSizeColumn(9);
        HSSFCell cell15Sum = row.createCell(10);
                cell15Sum.setCellValue("");
                cell15Sum.setCellStyle(styleDetailTableNumber);
                sheet.autoSizeColumn(10);
        HSSFCell cell16Sum = row.createCell(11);
                cell16Sum.setCellFormula(sumCancel);
                cell16Sum.setCellStyle(styleDetailTableNumber);
                sheet.autoSizeColumn(11);
        HSSFCell cell17Sum = row.createCell(12);
                cell17Sum.setCellValue("");
                cell17Sum.setCellStyle(styleDetailTableNumber);
                sheet.autoSizeColumn(12);
        HSSFCell cell18Sum = row.createCell(13);
                cell18Sum.setCellFormula(sumRC);
                cell18Sum.setCellStyle(styleDetailTableNumber);
                sheet.autoSizeColumn(13);
        HSSFCell cell19Sum = row.createCell(14);
                cell19Sum.setCellValue("");
                cell19Sum.setCellStyle(styleDetailTableNumber);
                sheet.autoSizeColumn(14);

        HSSFRow row2 = sheet.createRow(count+2);
        HSSFCell cellSum100 = row2.createCell(0);
                cellSum100.setCellValue("");
                cellSum100.setCellStyle(styleDetailTableNumber);
        HSSFCell cell11Sum101 = row2.createCell(1);
                cell11Sum101.setCellValue("");
                cell11Sum101.setCellStyle(styleDetailTableNumber);
        HSSFCell cell7Sum101 = row2.createCell(2);
                cell7Sum101.setCellValue("");
                cell7Sum101.setCellStyle(styleDetailTableNumber);
        HSSFCell cell8Sum102 = row2.createCell(3);
                cell8Sum102.setCellValue("");
                cell8Sum102.setCellStyle(styleDetailTableNumber);
        HSSFCell cell9Sum103 = row2.createCell(4);
                cell9Sum103.setCellValue("");
                cell9Sum103.setCellStyle(styleDetailTableNumber);
        HSSFCell cell10Sum104 = row2.createCell(5);
                cell10Sum104.setCellValue("");
                cell10Sum104.setCellStyle(styleDetailTableNumber);
        HSSFCell cell11Sum105 = row2.createCell(6);
                cell11Sum105.setCellFormula(sumInvWendy);
                cell11Sum105.setCellStyle(styleDetailTableNumber);
        HSSFCell cell12Sum106 = row2.createCell(7);
                cell12Sum106.setCellValue("");
                cell12Sum106.setCellStyle(styleDetailTableNumber);
        HSSFCell cell13Sum107 = row2.createCell(8);
                cell13Sum107.setCellValue("");
                cell13Sum107.setCellStyle(styleDetailTableNumber);
        HSSFCell cell14Sum108 = row2.createCell(9);
                cell14Sum108.setCellValue("");
                cell14Sum108.setCellStyle(styleDetailTableNumber);
        HSSFCell cell15Sum109 = row2.createCell(10);
                cell15Sum109.setCellFormula(sumDiscount);
                cell15Sum109.setCellStyle(styleDetailTableNumber);
        HSSFCell cell16Sum110 = row2.createCell(11);
                cell16Sum110.setCellValue("");
                cell16Sum110.setCellStyle(styleDetailTableNumber);
        HSSFCell cell17Sum111 = row2.createCell(12);
                cell17Sum111.setCellFormula(sumWait);
                cell17Sum111.setCellStyle(styleDetailTableNumber);
        HSSFCell cell18Sum112 = row2.createCell(13);
                cell18Sum112.setCellValue("");
                cell18Sum112.setCellStyle(styleDetailTableNumber);
        HSSFCell cell19Sum113 = row2.createCell(14);
                cell19Sum113.setCellFormula(sumBlance);
                cell19Sum113.setCellStyle(styleDetailTableNumber);

        HSSFRow rowLL = sheet.createRow(count+3);
        rowLL.createCell(0).setCellStyle(styleBorderTop);
        rowLL.createCell(1).setCellStyle(styleBorderTop);
        rowLL.createCell(2).setCellStyle(styleBorderTop);
        rowLL.createCell(3).setCellStyle(styleBorderTop);
        rowLL.createCell(4).setCellStyle(styleBorderTop);
        rowLL.createCell(5).setCellStyle(styleBorderTop);
        rowLL.createCell(6).setCellStyle(styleBorderTop);
        rowLL.createCell(7).setCellStyle(styleBorderTop);
        rowLL.createCell(8).setCellStyle(styleBorderTop);
        rowLL.createCell(9).setCellStyle(styleBorderTop);
        rowLL.createCell(10).setCellStyle(styleBorderTop);
        rowLL.createCell(11).setCellStyle(styleBorderTop);
        rowLL.createCell(12).setCellStyle(styleBorderTop);
        rowLL.createCell(13).setCellStyle(styleBorderTop);
        rowLL.createCell(14).setCellStyle(styleBorderTop);
        
        
        // Sheet
         // set Header Report (Row 1)
        HSSFCellStyle styleC110Sum = wb.createCellStyle();
        HSSFRow row010 = sheet1.createRow(0);
        HSSFCell cell010 = row010.createCell(0);
            cell010.setCellValue("Summary Ticket Adjust Cost & Income");
            styleC110Sum.setFont(getHeaderFont(wb.createFont()));
            cell010.setCellStyle(styleC110Sum);
            sheet1.addMergedRegion(CellRangeAddress.valueOf("A1:H1"));

        // Set align Text
        HSSFCellStyle styleC212 = wb.createCellStyle();
        styleC212.setAlignment(styleC21.ALIGN_RIGHT);
        HSSFCellStyle styleC222 = wb.createCellStyle();
        styleC222.setAlignment(styleC22.ALIGN_LEFT);
        
        List<SummaryTicketAdjustCostAndIncome> incomeSumTemp = listTotal.get(0).getSummaryTicketAdjustAndIncomeSum();
        SummaryTicketAdjustCostAndIncome incomeSum = new SummaryTicketAdjustCostAndIncome();
        if((incomeSumTemp != null)&&(incomeSumTemp.size() != 0)){
            incomeSum = (SummaryTicketAdjustCostAndIncome)incomeSumTemp.get(0);
        }
        // Row 2
        HSSFRow row022 = sheet1.createRow(1);
        HSSFCell cell0212 = row022.createCell(0);
        cell0212.setCellValue("Invoice Date : ");
        cell0212.setCellStyle(styleC21);
        HSSFCell cell0222 = row022.createCell(1);
        cell0222.setCellValue(incomeSum.getInvoicedatePage());
        cell0222.setCellStyle(styleC22);
        sheet1.addMergedRegion(CellRangeAddress.valueOf("B2:D2"));
        HSSFCell cell0232 = row022.createCell(4);
        cell0232.setCellValue("Print : ");
        cell0232.setCellStyle(styleC21);
        HSSFCell cell0242 = row022.createCell(5);
        cell0242.setCellValue(incomeSum.getPrintbyPage());
        cell0242.setCellStyle(styleC22);

        // Row 3
        HSSFRow row032 = sheet1.createRow(2);
        HSSFCell cell0312 = row032.createCell(0);
        cell0312.setCellValue("Issue Date : ");
        cell0312.setCellStyle(styleC21);
        HSSFCell cell0322 = row032.createCell(1);
        cell0322.setCellValue(incomeSum.getIssuedatePage());
        cell0322.setCellStyle(styleC22);
        sheet1.addMergedRegion(CellRangeAddress.valueOf("B3:D3"));
        HSSFCell cell0332 = row032.createCell(4);
        cell0332.setCellValue("Page : ");
        cell0332.setCellStyle(styleC21);
        HSSFCell cell0342 = row032.createCell(5);
        cell0342.setCellValue("1");
        cell0342.setCellStyle(styleC22);

        // Row 4
        HSSFRow row042 = sheet1.createRow(3);
        HSSFCell cell0412 = row042.createCell(0);
        cell0412.setCellValue("Department : ");
        cell0412.setCellStyle(styleC21);
        HSSFCell cell0422 = row042.createCell(1);
        cell0422.setCellValue(incomeSum.getDepartmentPage());
        cell0422.setCellStyle(styleC22);
        sheet1.addMergedRegion(CellRangeAddress.valueOf("B4:D4"));

        // Row 5
        HSSFRow row052 = sheet1.createRow(4);
        HSSFCell cell0512 = row052.createCell(0);
        cell0512.setCellValue("Sale Staff : ");
        cell0512.setCellStyle(styleC21);
        sheet1.addMergedRegion(CellRangeAddress.valueOf("B5:D5"));
        HSSFCell cell0522 = row052.createCell(1);
        cell0522.setCellValue(incomeSum.getSalsestaffPage());
        cell0522.setCellStyle(styleC22);

        // Row 6
        HSSFRow row062 = sheet1.createRow(5);
        HSSFCell cell6112 = row062.createCell(0);
        cell6112.setCellValue("Term Pay : ");
        cell6112.setCellStyle(styleC21);
        sheet1.addMergedRegion(CellRangeAddress.valueOf("B6:D6"));
        HSSFCell cell6212 = row062.createCell(1);
        cell6212.setCellValue(incomeSum.getTermPayPage());
        cell6212.setCellStyle(styleC22);

        // Header Table
        HSSFRow row62 = sheet1.createRow(8);
        HSSFCell cell612 = row62.createCell(0);
        cell612.setCellValue("Type Pay");
        cell612.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet1.autoSizeColumn(0);
        HSSFCell cell622 = row62.createCell(1);
        cell622.setCellValue("Type Route");
        cell622.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet1.autoSizeColumn(1);
        HSSFCell cell632 = row62.createCell(2);
        cell632.setCellValue("Pax");
        sheet1.autoSizeColumn(2);
        cell632.setCellStyle(styleAlignRightBorderAllHeaderTable);
        HSSFCell cell652 = row62.createCell(3);
        cell652.setCellValue("Inv. No.");
        cell652.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet1.autoSizeColumn(3);
        HSSFCell cell662 = row62.createCell(4);
        cell662.setCellValue("Cost Inv");
        cell662.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet1.autoSizeColumn(5);
        HSSFCell cell672 = row62.createCell(5);
        cell672.setCellValue("Invoice Wendy");
        cell672.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet1.autoSizeColumn(5);
        HSSFCell cell6722 = row62.createCell(6);
        cell6722.setCellValue("Invoice Inbound");
        cell6722.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet1.autoSizeColumn(6);
        HSSFCell cell682 = row62.createCell(7);
        cell682.setCellValue("Invoice Outbound");
        cell682.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet1.autoSizeColumn(7);
        HSSFCell cell692 = row62.createCell(8);
        cell692.setCellValue("Over");
        cell692.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet1.autoSizeColumn(8);
        HSSFCell cell712 = row62.createCell(9);
        cell712.setCellValue("Discount");
        cell712.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet1.autoSizeColumn(9);
        HSSFCell cell702 = row62.createCell(10);
        cell702.setCellValue("  Little  ");
        cell702.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet1.autoSizeColumn(10);
        HSSFCell cell722 = row62.createCell(11);
        cell722.setCellValue("Cancel");
        cell722.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet1.autoSizeColumn(11);
        HSSFCell cell732 = row62.createCell(12);
        cell732.setCellValue("Wait Pay");
        cell732.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet1.autoSizeColumn(12);
        HSSFCell cell742 = row62.createCell(13);
        cell742.setCellValue("RC AG Com");
        cell742.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet1.autoSizeColumn(13);
        HSSFCell cell752 = row62.createCell(14);
        cell752.setCellValue("Total Balance");
        cell752.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet1.autoSizeColumn(14);
        
        List<SummaryTicketAdjustCostAndIncome> listSummaryTicketCostIncomeSum = listTotal.get(0).getSummaryTicketAdjustAndIncomeSum();
        
        int count2 = 9 + listSummaryTicketCostIncomeSum.size();
        int start2 = 11;
        int end2 = 0;
        int num2 = 0;

        for (int r = 9; r < count2; r++) {
            if(num2 < listSummaryTicketCostIncomeSum.size()){
                HSSFRow row22 = sheet1.createRow(r);
                HSSFCell cell1 = row22.createCell(0);
                    cell1.setCellValue(listSummaryTicketCostIncomeSum.get(num2).getTypepayment());
                    cell1.setCellStyle(styleDetailTable);
                HSSFCell cell2 = row22.createCell(1);
                    cell2.setCellValue(listSummaryTicketCostIncomeSum.get(num2).getTyperounting());
                    cell2.setCellStyle(styleDetailTable);
                HSSFCell cell3 = row22.createCell(2);
                        BigDecimal pax = new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getPax());
                    cell3.setCellValue((pax != null) ? pax.doubleValue() : new BigDecimal("0").doubleValue() );
                    cell3.setCellStyle(styleDetailTableNumber);
                HSSFCell cell4 = row22.createCell(3);
                    cell4.setCellValue(listSummaryTicketCostIncomeSum.get(num2).getAir());
                    cell4.setCellStyle(styleDetailTable);
                HSSFCell cell55 = row22.createCell(4);
                    cell55.setCellValue(listSummaryTicketCostIncomeSum.get(num2).getInvno());
                    cell55.setCellStyle(styleDetailTable);
                HSSFCell cell5 = row22.createCell(5);
                    System.out.println("Cost : " +listSummaryTicketCostIncomeSum.get(num2).getCostinv());
                    BigDecimal costinv = null;
                    if("".equals(listSummaryTicketCostIncomeSum.get(num2).getCostinv())){
                        costinv = new BigDecimal(0);
                    }else{
                        costinv = new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getCostinv());
                    }
                    cell5.setCellValue((costinv != null && !"0".equals(costinv)) ? costinv.doubleValue() : new BigDecimal("0").doubleValue() );
                    cell5.setCellStyle(styleDetailTableNumber);
                HSSFCell cell6 = row22.createCell(6);
                    BigDecimal invwendy = null;
                    if("".equals(listSummaryTicketCostIncomeSum.get(num2).getInvoicewendy())){
                        invwendy = new BigDecimal(0);
                    }else{
                        invwendy = new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getInvoicewendy());
                    }
                    cell6.setCellValue((invwendy != null) ? invwendy.doubleValue() : new BigDecimal("0").doubleValue());
                    cell6.setCellStyle(styleDetailTableNumber);
                HSSFCell cell7 = row22.createCell(7);
                    BigDecimal invoutbound = null;
                    if("".equals(listSummaryTicketCostIncomeSum.get(num2).getInvoiceoutbound())){
                        invoutbound = new BigDecimal(0);
                    }else{
                        invoutbound = new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getInvoiceoutbound());
                    }
                    cell7.setCellValue((invoutbound != null) ? invoutbound.doubleValue() : new BigDecimal("0").doubleValue());
                    cell7.setCellStyle(styleDetailTableNumber);
                HSSFCell cell8 = row22.createCell(8);
                    cell8.setCellValue(listSummaryTicketCostIncomeSum.get(num2).getOver());
                    cell8.setCellStyle(styleDetailTable);
                HSSFCell cell9 = row22.createCell(9);
                    BigDecimal little = null;
                    if("".equals(listSummaryTicketCostIncomeSum.get(num2).getLitter())){
                        little = new BigDecimal(0);
                    }else{
                        little = new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getLitter());
                    }
                    cell9.setCellValue((little != null) ? little.doubleValue() : new BigDecimal("0").doubleValue());
                    cell9.setCellStyle(styleDetailTableNumber);
                    sheet.autoSizeColumn(9);
                HSSFCell cell10 = row22.createCell(10);
                    BigDecimal discount  = null;
                    if("".equals(listSummaryTicketCostIncomeSum.get(num2).getDiscount())){
                        discount = new BigDecimal(0);
                    }else{
                        discount = new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getDiscount());
                    }
                    cell10.setCellValue((discount != null) ? discount.doubleValue() : new BigDecimal("0").doubleValue());
                    cell10.setCellStyle(styleDetailTableNumber);
                HSSFCell cell11 = row22.createCell(11);
                    BigDecimal cancel = null;
                    if("".equals(listSummaryTicketCostIncomeSum.get(num2).getCancel())){
                        cancel = new BigDecimal(0);
                    }else{
                        cancel = new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getCancel());
                    }
                    cell11.setCellValue((cancel != null) ? cancel.doubleValue() : new BigDecimal("0").doubleValue());
                    cell11.setCellStyle(styleDetailTableNumber);
                HSSFCell cell12 = row22.createCell(12);
                    BigDecimal wait = null;
                    if("".equals(listSummaryTicketCostIncomeSum.get(num2).getWait_pay())){
                        wait = new BigDecimal(0);
                    }else{
                        wait = new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getWait_pay());
                    }
                    cell12.setCellValue((wait != null) ? wait.doubleValue() : new BigDecimal("0").doubleValue());
                    cell12.setCellStyle(styleDetailTableNumber);
                HSSFCell cell13 = row22.createCell(13);
                    BigDecimal rc = null;
                    if("".equals(listSummaryTicketCostIncomeSum.get(num2).getRcagcom())){
                        rc = new BigDecimal(0);
                    }else{
                        rc = new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getRcagcom());
                    }
                    cell13.setCellValue((rc != null) ? rc.doubleValue() : new BigDecimal("0").doubleValue());
                    cell13.setCellStyle(styleDetailTableNumber);
                HSSFCell cell14 = row22.createCell(14);
                    BigDecimal balance = null;
                    if("".equals(listSummaryTicketCostIncomeSum.get(num2).getTotal_balance())){
                        balance = new BigDecimal(0);
                    }else{
                        balance = new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getTotal_balance());
                    }
                    cell14.setCellValue((balance != null) ? balance.doubleValue() : new BigDecimal("0").doubleValue());
                    cell14.setCellStyle(styleDetailTableNumber);
                num2++;
            }
            for (int i = 0; i < listSummaryTicketCostIncomeSum.size(); i++) {
                    sheet1.autoSizeColumn(i);
            }
        }
        System.out.println(count2);
        HSSFRow rowL2 = sheet1.createRow(count2);
        rowL2.createCell(0).setCellStyle(styleDetailTableBorderBottomTop);
        rowL2.createCell(1).setCellStyle(styleDetailTableBorderBottomTop);
        rowL2.createCell(2).setCellStyle(styleDetailTableBorderBottomTop);
        rowL2.createCell(3).setCellStyle(styleDetailTableBorderBottomTop);
        rowL2.createCell(4).setCellStyle(styleDetailTableBorderBottomTop);
        rowL2.createCell(5).setCellStyle(styleDetailTableBorderBottomTop);
        rowL2.createCell(6).setCellStyle(styleDetailTableBorderBottomTop);
        rowL2.createCell(7).setCellStyle(styleDetailTableBorderBottomTop);
        rowL2.createCell(8).setCellStyle(styleDetailTableBorderBottomTop);
        rowL2.createCell(9).setCellStyle(styleDetailTableBorderBottomTop);
        rowL2.createCell(10).setCellStyle(styleDetailTableBorderBottomTop);
        rowL2.createCell(11).setCellStyle(styleDetailTableBorderBottomTop);
        rowL2.createCell(12).setCellStyle(styleDetailTableBorderBottomTop);
        rowL2.createCell(13).setCellStyle(styleDetailTableBorderBottomTop);
        rowL2.createCell(14).setCellStyle(styleDetailTableBorderBottomTop);

        String sumPax2 = "SUM(C" + 10+":C"+(count2)+")";
        String sumCosInv2 = "SUM(F" + 10+":F"+(count2)+")";
        String sumInvWendy2 = "SUM(G" + 10+":G"+(count2)+")";
        String sumInvOutbound2 = "SUM(H" + 10+":H"+(count2)+")";
        String sumLittle2 = "SUM(J" + 10+":J"+(count2)+")";
        String sumDiscount2 = "SUM(K" + 10+":K"+(count2)+")";
        String sumCancel2 = "SUM(L" + 10+":L"+(count2)+")";
        String sumRC2 = "SUM(N" + 10+":N"+(count2)+")";
        String sumWait2 = "SUM(M" + 10+":M"+(count2)+")";
        String sumBlance2 = "SUM(O" + 10+":O"+(count2)+")";

        HSSFRow row20 = sheet1.createRow(count2+1);
        HSSFCell cell60Sum2 = row20.createCell(0);
            cell60Sum2.setCellValue("");
            cell60Sum2.setCellStyle(styleDetailTableNumber);
            sheet1.autoSizeColumn(0);
        HSSFCell cell6Sum2 = row20.createCell(1);
            cell6Sum2.setCellValue("");
            cell6Sum2.setCellStyle(styleDetailTableNumber);
            sheet1.autoSizeColumn(1);
        HSSFCell cell7Sum2 = row20.createCell(2);
            cell7Sum2.setCellFormula(sumPax2);
            sheet1.autoSizeColumn(2);
            cell7Sum2.setCellStyle(styleDetailTableNumber);
        HSSFCell cell8Sum2 = row20.createCell(3);
            cell8Sum2.setCellValue("");
            sheet1.autoSizeColumn(3);
            cell8Sum2.setCellStyle(styleDetailTableNumber);
        HSSFCell cell9Sum2 = row20.createCell(4);
            cell9Sum2.setCellValue("");
            cell9Sum2.setCellStyle(styleDetailTableNumber);
            sheet1.autoSizeColumn(4);
        HSSFCell cell10Sum2 = row20.createCell(5);
            cell10Sum2.setCellFormula(sumCosInv2);
            cell10Sum2.setCellStyle(styleDetailTableNumber);
            sheet1.autoSizeColumn(5);
        HSSFCell cell11Sum2 = row20.createCell(6);
            cell11Sum2.setCellValue("");
            cell11Sum2.setCellStyle(styleDetailTableNumber);
            sheet1.autoSizeColumn(6);
        HSSFCell cell12Sum2 = row20.createCell(7);
            cell12Sum2.setCellFormula(sumInvOutbound2);
            cell12Sum2.setCellStyle(styleDetailTableNumber);
            sheet1.autoSizeColumn(7);
        HSSFCell cell13Sum2 = row20.createCell(8);
            cell13Sum2.setCellValue("");
            cell13Sum2.setCellStyle(styleDetailTableNumber);
            sheet1.autoSizeColumn(8);
        HSSFCell cell14Sum2 = row20.createCell(9);
            cell14Sum2.setCellFormula(sumLittle2);
            cell14Sum2.setCellStyle(styleDetailTableNumber);
            sheet1.autoSizeColumn(9);
        HSSFCell cell15Sum2 = row20.createCell(10);
            cell15Sum2.setCellValue("");
            cell15Sum2.setCellStyle(styleDetailTableNumber);
            sheet1.autoSizeColumn(10);
        HSSFCell cell16Sum2 = row20.createCell(11);
            cell16Sum2.setCellFormula(sumCancel2);
            cell16Sum2.setCellStyle(styleDetailTableNumber);
            sheet1.autoSizeColumn(11);
        HSSFCell cell17Sum2 = row20.createCell(12);
            cell17Sum2.setCellValue("");
            cell17Sum2.setCellStyle(styleDetailTableNumber);
            sheet1.autoSizeColumn(12);
        HSSFCell cell18Sum2 = row20.createCell(13);
            cell18Sum2.setCellFormula(sumRC2);
            cell18Sum2.setCellStyle(styleDetailTableNumber);
            sheet1.autoSizeColumn(13);
        HSSFCell cell19Sum2 = row20.createCell(14);
            cell19Sum2.setCellValue("");
            cell19Sum2.setCellStyle(styleDetailTableNumber);
            sheet1.autoSizeColumn(14);

        HSSFRow row200 = sheet1.createRow(count2+2);
        HSSFCell cellSum1002 = row200.createCell(0);
                cellSum1002.setCellValue("");
                cellSum1002.setCellStyle(styleDetailTableNumber);
        HSSFCell cell11Sum1012 = row200.createCell(1);
                cell11Sum1012.setCellValue("");
                cell11Sum1012.setCellStyle(styleDetailTableNumber);
        HSSFCell cell7Sum1012 = row200.createCell(2);
                cell7Sum1012.setCellValue("");
                cell7Sum1012.setCellStyle(styleDetailTableNumber);
        HSSFCell cell8Sum1022 = row200.createCell(3);
                cell8Sum1022.setCellValue("");
                cell8Sum1022.setCellStyle(styleDetailTableNumber);
        HSSFCell cell9Sum1032 = row200.createCell(4);
                cell9Sum1032.setCellValue("");
                cell9Sum1032.setCellStyle(styleDetailTableNumber);
        HSSFCell cell10Sum1042 = row200.createCell(5);
                cell10Sum1042.setCellValue("");
                cell10Sum1042.setCellStyle(styleDetailTableNumber);
        HSSFCell cell11Sum1052 = row200.createCell(6);
                cell11Sum1052.setCellFormula(sumInvWendy2);
                cell11Sum1052.setCellStyle(styleDetailTableNumber);
        HSSFCell cell12Sum1062 = row200.createCell(7);
                cell12Sum1062.setCellValue("");
                cell12Sum1062.setCellStyle(styleDetailTableNumber);
        HSSFCell cell13Sum1072 = row200.createCell(8);
                cell13Sum1072.setCellValue("");
                cell13Sum1072.setCellStyle(styleDetailTableNumber);
        HSSFCell cell14Sum1082 = row200.createCell(9);
                cell14Sum1082.setCellValue("");
                cell14Sum1082.setCellStyle(styleDetailTableNumber);
        HSSFCell cell15Sum1092 = row200.createCell(10);
                cell15Sum1092.setCellFormula(sumDiscount2);
                cell15Sum1092.setCellStyle(styleDetailTableNumber);
        HSSFCell cell16Sum1102 = row200.createCell(11);
                cell16Sum1102.setCellValue("");
                cell16Sum1102.setCellStyle(styleDetailTableNumber);
        HSSFCell cell17Sum1112 = row200.createCell(12);
                cell17Sum1112.setCellFormula(sumWait2);
                cell17Sum1112.setCellStyle(styleDetailTableNumber);
        HSSFCell cell18Sum1122 = row200.createCell(13);
                cell18Sum1122.setCellValue("");
                cell18Sum1122.setCellStyle(styleDetailTableNumber);
        HSSFCell cell19Sum1132 = row200.createCell(14);
                cell19Sum1132.setCellFormula(sumBlance2);
                cell19Sum1132.setCellStyle(styleDetailTableNumber);

        HSSFRow rowLL2 = sheet1.createRow(count2+3);
        rowLL2.createCell(0).setCellStyle(styleBorderTop);
        rowLL2.createCell(1).setCellStyle(styleBorderTop);
        rowLL2.createCell(2).setCellStyle(styleBorderTop);
        rowLL2.createCell(3).setCellStyle(styleBorderTop);
        rowLL2.createCell(4).setCellStyle(styleBorderTop);
        rowLL2.createCell(5).setCellStyle(styleBorderTop);
        rowLL2.createCell(6).setCellStyle(styleBorderTop);
        rowLL2.createCell(7).setCellStyle(styleBorderTop);
        rowLL2.createCell(8).setCellStyle(styleBorderTop);
        rowLL2.createCell(9).setCellStyle(styleBorderTop);
        rowLL2.createCell(10).setCellStyle(styleBorderTop);
        rowLL2.createCell(11).setCellStyle(styleBorderTop);
        rowLL2.createCell(12).setCellStyle(styleBorderTop);
        rowLL2.createCell(13).setCellStyle(styleBorderTop);
        rowLL2.createCell(14).setCellStyle(styleBorderTop);
    }
    
    private void getTicketCommissionReceive(HSSFWorkbook wb, List TicketCommissionReceive) {
        List<ListTicketCommissionReceive> listTotal = TicketCommissionReceive;

        String sheetName = "receive";// name of sheet
        String sheetName1 = "receive_summary";
        HSSFSheet sheet = wb.createSheet(sheetName);
        HSSFSheet sheet1 = wb.createSheet(sheetName1);
        HSSFDataFormat currency = wb.createDataFormat();
    
        // line table
        HSSFCellStyle styleBorderTop = wb.createCellStyle();// use
            styleBorderTop.setBorderTop(styleBorderTop.BORDER_THIN);
        HSSFCellStyle styleAlignRightBorderAllHeaderTable = wb .createCellStyle(); // use
            styleAlignRightBorderAllHeaderTable.setFont(getHeaderTable(wb.createFont()));
            styleAlignRightBorderAllHeaderTable.setAlignment(styleAlignRightBorderAllHeaderTable.ALIGN_CENTER);
            styleAlignRightBorderAllHeaderTable.setBorderTop(styleAlignRightBorderAllHeaderTable.BORDER_THIN);
            styleAlignRightBorderAllHeaderTable.setBorderBottom(styleAlignRightBorderAllHeaderTable.BORDER_THIN);
            styleAlignRightBorderAllHeaderTable.setBorderRight(styleAlignRightBorderAllHeaderTable.BORDER_THIN);
            styleAlignRightBorderAllHeaderTable.setBorderLeft(styleAlignRightBorderAllHeaderTable.BORDER_THIN);

        // Header Table
        HSSFCellStyle styleDetailTable = wb.createCellStyle(); // use
            styleDetailTable.setAlignment(styleDetailTable.ALIGN_LEFT);
            styleDetailTable.setBorderLeft(styleDetailTable.BORDER_THIN);
            styleDetailTable.setBorderRight(styleDetailTable.BORDER_THIN);
        HSSFCellStyle styleDetailTableNumber = wb.createCellStyle(); //use
            styleDetailTableNumber.setDataFormat(currency.getFormat("#,##0.00"));
            styleDetailTableNumber.setAlignment(styleDetailTableNumber.ALIGN_RIGHT);
            styleDetailTableNumber.setBorderLeft(styleDetailTableNumber.BORDER_THIN);
            styleDetailTableNumber.setBorderRight(styleDetailTableNumber.BORDER_THIN);
        HSSFCellStyle styleDetailTableBorderBottomTop = wb.createCellStyle(); // use
                styleDetailTableBorderBottomTop.setBorderTop(styleDetailTableBorderBottomTop.BORDER_THIN);
                styleDetailTableBorderBottomTop.setBorderRight(styleDetailTableBorderBottomTop.BORDER_THIN);

        // Sheet Detail (2)
        // set Header Report (Row 1)
        HSSFCellStyle styleC11 = wb.createCellStyle();
        HSSFRow row01 = sheet.createRow(0);
        HSSFCell cell01 = row01.createCell(0);
        cell01.setCellValue("Summary Ticket Commission Receive");
        styleC11.setFont(getHeaderFont(wb.createFont()));
        cell01.setCellStyle(styleC11);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:H1"));

        // Set align Text
        HSSFCellStyle styleC21 = wb.createCellStyle();
        styleC21.setAlignment(styleC21.ALIGN_RIGHT);
        HSSFCellStyle styleC22 = wb.createCellStyle();
        styleC22.setAlignment(styleC22.ALIGN_LEFT);

        List<TicketCommissionReceive>  receiveTemp = listTotal.get(0).getTicketCommmissionReceive();
        TicketCommissionReceive  receive = new TicketCommissionReceive();
        receive = (TicketCommissionReceive)receiveTemp.get(0);
        // Row 2
        HSSFRow row02 = sheet.createRow(1);
        HSSFCell cell021 = row02.createCell(0);
        cell021.setCellValue("Invoice Date : ");
        cell021.setCellStyle(styleC21);
        HSSFCell cell022 = row02.createCell(1);
        cell022.setCellValue(receive.getInvoicedatePage());
        cell022.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B2:D2"));
        HSSFCell cell023 = row02.createCell(4);
        cell023.setCellValue("Print : ");
        cell023.setCellStyle(styleC21);
        HSSFCell cell024 = row02.createCell(5);
        cell024.setCellValue(receive.getPrintbyPage());
        cell024.setCellStyle(styleC22);

        // Row 3
        HSSFRow row03 = sheet.createRow(2);
        HSSFCell cell031 = row03.createCell(0);
        cell031.setCellValue("Issue Date : ");
        cell031.setCellStyle(styleC21);
        HSSFCell cell032 = row03.createCell(1);
        cell032.setCellValue(receive.getIssuedatePage());
        cell032.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B3:D3"));
        HSSFCell cell033 = row03.createCell(4);
        cell033.setCellValue("Page : ");
        cell033.setCellStyle(styleC21);
        HSSFCell cell034 = row03.createCell(5);
        cell034.setCellValue("1");
        cell034.setCellStyle(styleC22);

        // Row 4
        HSSFRow row04 = sheet.createRow(3);
        HSSFCell cell041 = row04.createCell(0);
        cell041.setCellValue("Department : ");
        cell041.setCellStyle(styleC21);
        HSSFCell cell042 = row04.createCell(1);
        cell042.setCellValue(receive.getDepartmentPage());
        cell042.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B4:D4"));

        // Row 5
        HSSFRow row05 = sheet.createRow(4);
        HSSFCell cell051 = row05.createCell(0);
        cell051.setCellValue("Sale Staff : ");
        cell051.setCellStyle(styleC21);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B5:D5"));
        HSSFCell cell052 = row05.createCell(1);
        cell052.setCellValue(receive.getSalsestaffPage());
        cell052.setCellStyle(styleC22);

        // Row 6
        HSSFRow row06 = sheet.createRow(5);
        HSSFCell cell611 = row06.createCell(0);
        cell611.setCellValue("Term Pay : ");
        cell611.setCellStyle(styleC21);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B6:D6"));
        HSSFCell cell621 = row06.createCell(1);
        cell621.setCellValue(receive.getTermPayPage());
        cell621.setCellStyle(styleC22);

        // Header Table
        HSSFRow row6 = sheet.createRow(8);
        HSSFCell cell61 = row6.createCell(0);
        cell61.setCellValue("Type Pay");
        cell61.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(0);
        HSSFCell cell62 = row6.createCell(1);
        cell62.setCellValue("Type Route");
        cell62.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(1);
        HSSFCell cell63 = row6.createCell(2);
        cell63.setCellValue("Pax");
        sheet.autoSizeColumn(2);
        cell63.setCellStyle(styleAlignRightBorderAllHeaderTable);
        HSSFCell cell64 = row6.createCell(3);
        cell64.setCellValue("Air");
        cell64.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(3);
        HSSFCell cell65 = row6.createCell(4);
        cell65.setCellValue("Comm Airline");
        cell65.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(4);
        HSSFCell cell66 = row6.createCell(5);
        cell66.setCellValue("Little Com");
        cell66.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(5);
        HSSFCell cell67 = row6.createCell(6);
        cell67.setCellValue("Pay [Agent]");
        cell67.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(6);
        HSSFCell cell68 = row6.createCell(7);
        cell68.setCellValue("RC [Agent]");
        cell68.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(7);
        HSSFCell cell69 = row6.createCell(8);
        cell69.setCellValue("Pay Refund");
        cell69.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(8);
        HSSFCell cell70 = row6.createCell(9);
        cell70.setCellValue("Comm Receive");
        cell70.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(9);
        
        List<TicketCommissionReceive> listTicketCommissionReceive = listTotal.get(0).getTicketCommmissionReceive();
        
        int count = 9 + listTicketCommissionReceive.size();
        int start = 11;
        int end = 0;
        int num = 0;

        for (int r = 9; r < count; r++) {
            if(num < listTicketCommissionReceive.size()){
                HSSFRow row = sheet.createRow(r);
                HSSFCell cell1 = row.createCell(0);
                    cell1.setCellValue(listTicketCommissionReceive.get(num).getTypepayment());
                    cell1.setCellStyle(styleDetailTable);
                HSSFCell cell2 = row.createCell(1);
                    cell2.setCellValue(listTicketCommissionReceive.get(num).getTyperounting());
                    cell2.setCellStyle(styleDetailTable);
                HSSFCell cell3 = row.createCell(2);
                    BigDecimal pax = new BigDecimal(listTicketCommissionReceive.get(num).getPax());
                    cell3.setCellValue((pax != null) ? pax.doubleValue() : new BigDecimal("0").doubleValue() );
                    cell3.setCellStyle(styleDetailTableNumber);
                HSSFCell cell4 = row.createCell(3);
                    cell4.setCellValue(listTicketCommissionReceive.get(num).getAir());
                    cell4.setCellStyle(styleDetailTable);
                HSSFCell cell55 = row.createCell(4);
                    BigDecimal comair = null;
                    if("".equals(listTicketCommissionReceive.get(num).getComairline())){
                        comair = new BigDecimal(0);
                    }else{
                        comair = new BigDecimal(listTicketCommissionReceive.get(num).getComairline());
                    }
                    cell55.setCellValue((comair != null && !"0".equals(comair)) ? comair.doubleValue() : new BigDecimal("0").doubleValue() );
                    cell55.setCellStyle(styleDetailTableNumber);
                HSSFCell cell5 = row.createCell(5);
                    BigDecimal littlecom = null;
                    if("".equals(listTicketCommissionReceive.get(num).getLittlecom())){
                        littlecom = new BigDecimal(0);
                    }else{
                        littlecom = new BigDecimal(listTicketCommissionReceive.get(num).getLittlecom());
                    }
                    cell5.setCellValue((littlecom != null && !"0".equals(littlecom)) ? littlecom.doubleValue() : new BigDecimal("0").doubleValue());
                    cell5.setCellStyle(styleDetailTableNumber);
                HSSFCell cell6 = row.createCell(6);
                    BigDecimal payagent = null;
                    if("".equals(listTicketCommissionReceive.get(num).getPayagent())){
                        payagent = new BigDecimal(0);
                    }else{
                        payagent = new BigDecimal(listTicketCommissionReceive.get(num).getPayagent());
                    }
                    cell6.setCellValue((payagent != null) ? payagent.doubleValue() : new BigDecimal("0").doubleValue());
                    cell6.setCellStyle(styleDetailTableNumber);
                HSSFCell cell7 = row.createCell(7);
                    BigDecimal rcagent = null;
                    if("".equals(listTicketCommissionReceive.get(num).getRcagent())){
                        rcagent = new BigDecimal(0);
                    }else{
                        rcagent = new BigDecimal(listTicketCommissionReceive.get(num).getRcagent());
                    }
                    cell7.setCellValue((rcagent != null) ? rcagent.doubleValue() : new BigDecimal("0").doubleValue());
                    cell7.setCellStyle(styleDetailTableNumber);
                HSSFCell cell8 = row.createCell(8);
                    BigDecimal payrefund = null;
                    if("".equals(listTicketCommissionReceive.get(num).getPayrefund())){
                        payrefund = new BigDecimal(0);
                    }else{
                        payrefund = new BigDecimal(listTicketCommissionReceive.get(num).getPayrefund());
                    }
                    cell8.setCellValue((payrefund != null) ? payrefund.doubleValue() : new BigDecimal("0").doubleValue());
                    cell8.setCellStyle(styleDetailTableNumber);
                HSSFCell cell9 = row.createCell(9);
                    BigDecimal comreceive = null;
                    if("".equals(listTicketCommissionReceive.get(num).getComreceive())){
                        comreceive = new BigDecimal(0);
                    }else{
                        comreceive = new BigDecimal(listTicketCommissionReceive.get(num).getComreceive());
                    }
                    cell9.setCellValue((comreceive != null) ? comreceive.doubleValue() : new BigDecimal("0").doubleValue());
                    cell9.setCellStyle(styleDetailTableNumber);
                    sheet.autoSizeColumn(9);
                num++;
            }
            for (int i = 0; i < listTicketCommissionReceive.size(); i++) {
                    sheet.autoSizeColumn(i);
            }
        }
        System.out.println(count);
        HSSFRow rowL = sheet.createRow(count);
        rowL.createCell(0).setCellStyle(styleDetailTableBorderBottomTop);
        rowL.createCell(1).setCellStyle(styleDetailTableBorderBottomTop);
        rowL.createCell(2).setCellStyle(styleDetailTableBorderBottomTop);
        rowL.createCell(3).setCellStyle(styleDetailTableBorderBottomTop);
        rowL.createCell(4).setCellStyle(styleDetailTableBorderBottomTop);
        rowL.createCell(5).setCellStyle(styleDetailTableBorderBottomTop);
        rowL.createCell(6).setCellStyle(styleDetailTableBorderBottomTop);
        rowL.createCell(7).setCellStyle(styleDetailTableBorderBottomTop);
        rowL.createCell(8).setCellStyle(styleDetailTableBorderBottomTop);
        rowL.createCell(9).setCellStyle(styleDetailTableBorderBottomTop);
        
        String sumPax = "SUM(C" + 10+":C"+(count)+")";
        String sumComair = "SUM(E" + 10+":E"+(count)+")";
        String sumlitttlecom = "SUM(F" + 10+":F"+(count)+")";
        String sumPayagent = "SUM(G" + 10+":G"+(count)+")";
        String sumRcagent = "SUM(H" + 10+":H"+(count)+")";
        String sumPayRefund = "SUM(I" + 10+":I"+(count)+")";
        String sumComReceive = "SUM(J" + 10+":J"+(count)+")";

        HSSFRow row = sheet.createRow(count+1);
        HSSFCell cell60Sum = row.createCell(0);
                cell60Sum.setCellValue("");
                cell60Sum.setCellStyle(styleDetailTableNumber);
                sheet.autoSizeColumn(0);
        HSSFCell cell6Sum = row.createCell(1);
            cell6Sum.setCellValue("");
            cell6Sum.setCellStyle(styleDetailTableNumber);
            sheet.autoSizeColumn(1);
        HSSFCell cell7Sum = row.createCell(2);
            cell7Sum.setCellFormula(sumPax);
            sheet.autoSizeColumn(2);
            cell7Sum.setCellStyle(styleDetailTableNumber);
        HSSFCell cell8Sum = row.createCell(3);
            cell8Sum.setCellValue("");
            sheet.autoSizeColumn(3);
            cell8Sum.setCellStyle(styleDetailTableNumber);
        HSSFCell cell9Sum = row.createCell(4);
            cell9Sum.setCellFormula(sumComair);
            cell9Sum.setCellStyle(styleDetailTableNumber);
            sheet.autoSizeColumn(4);
        HSSFCell cell10Sum = row.createCell(5);
            cell10Sum.setCellFormula(sumlitttlecom);
            cell10Sum.setCellStyle(styleDetailTableNumber);
            sheet.autoSizeColumn(5);
        HSSFCell cell11Sum = row.createCell(6);
            cell11Sum.setCellFormula(sumPayagent);
            cell11Sum.setCellStyle(styleDetailTableNumber);
            sheet.autoSizeColumn(6);
        HSSFCell cell12Sum = row.createCell(7);
            cell12Sum.setCellFormula(sumRcagent);
            cell12Sum.setCellStyle(styleDetailTableNumber);
            sheet.autoSizeColumn(7);
        HSSFCell cell13Sum = row.createCell(8);
            cell13Sum.setCellFormula(sumPayRefund);
            cell13Sum.setCellStyle(styleDetailTableNumber);
            sheet.autoSizeColumn(8);
        HSSFCell cell14Sum = row.createCell(9);
               cell14Sum.setCellFormula(sumComReceive);
               cell14Sum.setCellStyle(styleDetailTableNumber);
               sheet.autoSizeColumn(9);

        HSSFRow rowLL = sheet.createRow(count+2);
        rowLL.createCell(0).setCellStyle(styleBorderTop);
        rowLL.createCell(1).setCellStyle(styleBorderTop);
        rowLL.createCell(2).setCellStyle(styleBorderTop);
        rowLL.createCell(3).setCellStyle(styleBorderTop);
        rowLL.createCell(4).setCellStyle(styleBorderTop);
        rowLL.createCell(5).setCellStyle(styleBorderTop);
        rowLL.createCell(6).setCellStyle(styleBorderTop);
        rowLL.createCell(7).setCellStyle(styleBorderTop);
        rowLL.createCell(8).setCellStyle(styleBorderTop);
        rowLL.createCell(9).setCellStyle(styleBorderTop);
        
        
        // Sheet
         // set Header Report (Row 1)
        HSSFCellStyle styleC110Sum = wb.createCellStyle();
        HSSFRow row010 = sheet1.createRow(0);
        HSSFCell cell010 = row010.createCell(0);
            cell010.setCellValue("Summary Ticket Commission Receive");
            styleC110Sum.setFont(getHeaderFont(wb.createFont()));
            cell010.setCellStyle(styleC110Sum);
            sheet1.addMergedRegion(CellRangeAddress.valueOf("A1:H1"));

        // Set align Text
        HSSFCellStyle styleC212 = wb.createCellStyle();
        styleC212.setAlignment(styleC21.ALIGN_RIGHT);
        HSSFCellStyle styleC222 = wb.createCellStyle();
        styleC222.setAlignment(styleC22.ALIGN_LEFT);
        
        List<TicketCommissionReceive>  receiveSumTemp = listTotal.get(0).getTicketCommmissionReceiveSum();
        TicketCommissionReceive  receiveSum = new TicketCommissionReceive();
        receiveSum = (TicketCommissionReceive)receiveSumTemp.get(0);
        // Row 2
        HSSFRow row022 = sheet1.createRow(1);
        HSSFCell cell0212 = row022.createCell(0);
        cell0212.setCellValue("Invoice Date : ");
        cell0212.setCellStyle(styleC21);
        HSSFCell cell0222 = row022.createCell(1);
        cell0222.setCellValue(receiveSum.getInvoicedatePage());
        cell0222.setCellStyle(styleC22);
        sheet1.addMergedRegion(CellRangeAddress.valueOf("B2:D2"));
        HSSFCell cell0232 = row022.createCell(4);
        cell0232.setCellValue("Print : ");
        cell0232.setCellStyle(styleC21);
        HSSFCell cell0242 = row022.createCell(5);
        cell0242.setCellValue(receiveSum.getPrintbyPage());
        cell0242.setCellStyle(styleC22);

        // Row 3
        HSSFRow row032 = sheet1.createRow(2);
        HSSFCell cell0312 = row032.createCell(0);
        cell0312.setCellValue("Issue Date : ");
        cell0312.setCellStyle(styleC21);
        HSSFCell cell0322 = row032.createCell(1);
        cell0322.setCellValue(receiveSum.getInvoicedatePage());
        cell0322.setCellStyle(styleC22);
        sheet1.addMergedRegion(CellRangeAddress.valueOf("B3:D3"));
        HSSFCell cell0332 = row032.createCell(4);
        cell0332.setCellValue("Page : ");
        cell0332.setCellStyle(styleC21);
        HSSFCell cell0342 = row032.createCell(5);
        cell0342.setCellValue("1");
        cell0342.setCellStyle(styleC22);

        // Row 4
        HSSFRow row042 = sheet1.createRow(3);
        HSSFCell cell0412 = row042.createCell(0);
        cell0412.setCellValue("Department : ");
        cell0412.setCellStyle(styleC21);
        HSSFCell cell0422 = row042.createCell(1);
        cell0422.setCellValue(receiveSum.getDepartmentPage());
        cell0422.setCellStyle(styleC22);
        sheet1.addMergedRegion(CellRangeAddress.valueOf("B4:D4"));

        // Row 5
        HSSFRow row052 = sheet1.createRow(4);
        HSSFCell cell0512 = row052.createCell(0);
        cell0512.setCellValue("Sale Staff : ");
        cell0512.setCellStyle(styleC21);
        sheet1.addMergedRegion(CellRangeAddress.valueOf("B5:D5"));
        HSSFCell cell0522 = row052.createCell(1);
        cell0522.setCellValue(receiveSum.getSalsestaffPage());
        cell0522.setCellStyle(styleC22);

        // Row 6
        HSSFRow row062 = sheet1.createRow(5);
        HSSFCell cell6112 = row062.createCell(0);
        cell6112.setCellValue("Term Pay : ");
        cell6112.setCellStyle(styleC21);
        sheet1.addMergedRegion(CellRangeAddress.valueOf("B6:D6"));
        HSSFCell cell6212 = row062.createCell(1);
        cell6212.setCellValue(receiveSum.getTermPayPage());
        cell6212.setCellStyle(styleC22);

        // Header Table
        HSSFRow row62 = sheet1.createRow(8);
        HSSFCell cell612 = row62.createCell(0);
        cell612.setCellValue("Type Pay");
        cell612.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet1.autoSizeColumn(0);
        HSSFCell cell622 = row62.createCell(1);
        cell622.setCellValue("Type Route");
        cell622.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet1.autoSizeColumn(1);
        HSSFCell cell632 = row62.createCell(2);
        cell632.setCellValue("Pax");
        sheet1.autoSizeColumn(2);
        cell632.setCellStyle(styleAlignRightBorderAllHeaderTable);
        HSSFCell cell652 = row62.createCell(3);
        cell652.setCellValue("Air");
        cell652.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet1.autoSizeColumn(3);
        HSSFCell cell662 = row62.createCell(4);
        cell662.setCellValue("Comm Airline");
        cell662.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet1.autoSizeColumn(5);
        HSSFCell cell672 = row62.createCell(5);
        cell672.setCellValue("Little Com");
        cell672.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet1.autoSizeColumn(5);
        HSSFCell cell6722 = row62.createCell(6);
        cell6722.setCellValue("Pay [Agent]");
        cell6722.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet1.autoSizeColumn(6);
        HSSFCell cell682 = row62.createCell(7);
        cell682.setCellValue("RC [Agent]");
        cell682.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet1.autoSizeColumn(7);
        HSSFCell cell692 = row62.createCell(8);
        cell692.setCellValue("Pay Refund");
        cell692.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet1.autoSizeColumn(8);
        HSSFCell cell712 = row62.createCell(9);
        cell712.setCellValue("Comm Receive");
        cell712.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet1.autoSizeColumn(9);
        
        List<TicketCommissionReceive> listTicketCommissionReceiveSum = listTotal.get(0).getTicketCommmissionReceiveSum();
        
        int count2 = 9 + listTicketCommissionReceiveSum.size();
        int start2 = 11;
        int end2 = 0;
        int num2 = 0;
        System.out.println("Size : " + listTicketCommissionReceiveSum.size());
        for (int r = 9; r < count2; r++) {
            if(num2 < listTicketCommissionReceiveSum.size()){
                HSSFRow row2 = sheet1.createRow(r);
                HSSFCell cell1 = row2.createCell(0);
                    cell1.setCellValue(listTicketCommissionReceiveSum.get(num2).getTypepayment());
                    cell1.setCellStyle(styleDetailTable);
                HSSFCell cell2 = row2.createCell(1);
                    cell2.setCellValue(listTicketCommissionReceiveSum.get(num2).getTyperounting());
                    cell2.setCellStyle(styleDetailTable);
                HSSFCell cell3 = row2.createCell(2);
                    BigDecimal pax = new BigDecimal(listTicketCommissionReceiveSum.get(num2).getPax());
                    cell3.setCellValue((pax != null) ? pax.doubleValue() : new BigDecimal("0").doubleValue() );
                    cell3.setCellStyle(styleDetailTableNumber);
                HSSFCell cell4 = row2.createCell(3);
                    cell4.setCellValue(listTicketCommissionReceiveSum.get(num2).getAir());
                    cell4.setCellStyle(styleDetailTable);
                HSSFCell cell55 = row2.createCell(4);
                    BigDecimal comair = null;
                    if("".equals(listTicketCommissionReceiveSum.get(num2).getComairline())){
                        comair = new BigDecimal(0);
                    }else{
                        comair = new BigDecimal(listTicketCommissionReceiveSum.get(num2).getComairline());
                    }
                    cell55.setCellValue((comair != null && !"0".equals(comair)) ? comair.doubleValue() : new BigDecimal("0").doubleValue() );
                    cell55.setCellStyle(styleDetailTableNumber);
                HSSFCell cell5 = row2.createCell(5);
                    BigDecimal littlecom = null;
                    if("".equals(listTicketCommissionReceiveSum.get(num2).getLittlecom())){
                        littlecom = new BigDecimal(0);
                    }else{
                        littlecom = new BigDecimal(listTicketCommissionReceiveSum.get(num2).getLittlecom());
                    }
                    cell5.setCellValue((littlecom != null && !"0".equals(littlecom)) ? littlecom.doubleValue() : new BigDecimal("0").doubleValue());
                    cell5.setCellStyle(styleDetailTableNumber);
                HSSFCell cell6 = row2.createCell(6);
                    BigDecimal payagent = null;
                    if("".equals(listTicketCommissionReceiveSum.get(num2).getPayagent())){
                        payagent = new BigDecimal(0);
                    }else{
                        payagent = new BigDecimal(listTicketCommissionReceiveSum.get(num2).getPayagent());
                    }
                    cell6.setCellValue((payagent != null) ? payagent.doubleValue() : new BigDecimal("0").doubleValue());
                    cell6.setCellStyle(styleDetailTableNumber);
                HSSFCell cell7 = row2.createCell(7);
                    BigDecimal rcagent = null;
                    if("".equals(listTicketCommissionReceiveSum.get(num2).getRcagent())){
                        rcagent = new BigDecimal(0);
                    }else{
                        rcagent = new BigDecimal(listTicketCommissionReceiveSum.get(num2).getRcagent());
                    }
                    cell7.setCellValue((rcagent != null) ? rcagent.doubleValue() : new BigDecimal("0").doubleValue());
                    cell7.setCellStyle(styleDetailTableNumber);
                HSSFCell cell8 = row2.createCell(8);
                    BigDecimal payrefund = null;
                    if("".equals(listTicketCommissionReceiveSum.get(num2).getPayrefund())){
                        payrefund = new BigDecimal(0);
                    }else{
                        payrefund = new BigDecimal(listTicketCommissionReceiveSum.get(num2).getPayrefund());
                    }
                    cell8.setCellValue((payrefund != null) ? payrefund.doubleValue() : new BigDecimal("0").doubleValue());
                    cell8.setCellStyle(styleDetailTableNumber);
                HSSFCell cell9 = row2.createCell(9);
                    BigDecimal comreceive = null;
                    if("".equals(listTicketCommissionReceiveSum.get(num2).getComreceive())){
                        comreceive = new BigDecimal(0);
                    }else{
                        comreceive = new BigDecimal(listTicketCommissionReceiveSum.get(num2).getComreceive());
                    }
                    cell9.setCellValue((comreceive != null) ? comreceive.doubleValue() : new BigDecimal("0").doubleValue());
                    cell9.setCellStyle(styleDetailTableNumber);
                    sheet1.autoSizeColumn(9);
                num2++;
            }
            for (int i = 0; i < listTicketCommissionReceiveSum.size(); i++) {
                    sheet1.autoSizeColumn(i);
            }
        }
        System.out.println(count2);
        HSSFRow rowL2 = sheet1.createRow(count2);
        rowL2.createCell(0).setCellStyle(styleDetailTableBorderBottomTop);
        rowL2.createCell(1).setCellStyle(styleDetailTableBorderBottomTop);
        rowL2.createCell(2).setCellStyle(styleDetailTableBorderBottomTop);
        rowL2.createCell(3).setCellStyle(styleDetailTableBorderBottomTop);
        rowL2.createCell(4).setCellStyle(styleDetailTableBorderBottomTop);
        rowL2.createCell(5).setCellStyle(styleDetailTableBorderBottomTop);
        rowL2.createCell(6).setCellStyle(styleDetailTableBorderBottomTop);
        rowL2.createCell(7).setCellStyle(styleDetailTableBorderBottomTop);
        rowL2.createCell(8).setCellStyle(styleDetailTableBorderBottomTop);
        rowL2.createCell(9).setCellStyle(styleDetailTableBorderBottomTop);

        String sumPax2 = "SUM(C" + 10+":C"+(count2)+")";
        String sumComair2 = "SUM(E" + 10+":E"+(count2)+")";
        String sumlitttlecom2 = "SUM(F" + 10+":F"+(count2)+")";
        String sumPayagent2 = "SUM(G" + 10+":G"+(count2)+")";
        String sumRcagent2 = "SUM(H" + 10+":H"+(count2)+")";
        String sumPayRefund2 = "SUM(I" + 10+":I"+(count2)+")";
        String sumComReceive2 = "SUM(J" + 10+":J"+(count2)+")";

        HSSFRow row20 = sheet1.createRow(count2+1);
        HSSFCell cell60Sum2 = row20.createCell(0);
            cell60Sum2.setCellValue("");
            cell60Sum2.setCellStyle(styleDetailTableNumber);
            sheet1.autoSizeColumn(0);
        HSSFCell cell6Sum2 = row20.createCell(1);
            cell6Sum2.setCellValue("");
            cell6Sum2.setCellStyle(styleDetailTableNumber);
            sheet1.autoSizeColumn(1);
        HSSFCell cell7Sum2 = row20.createCell(2);
            cell7Sum2.setCellFormula(sumPax2);
            sheet1.autoSizeColumn(2);
            cell7Sum2.setCellStyle(styleDetailTableNumber);
        HSSFCell cell8Sum2 = row20.createCell(3);
            cell8Sum2.setCellValue("");
            sheet1.autoSizeColumn(3);
            cell8Sum2.setCellStyle(styleDetailTableNumber);
        HSSFCell cell9Sum2 = row20.createCell(4);
            cell9Sum2.setCellFormula(sumComair2);
            cell9Sum2.setCellStyle(styleDetailTableNumber);
            sheet1.autoSizeColumn(4);
        HSSFCell cell10Sum2 = row20.createCell(5);
            cell10Sum2.setCellFormula(sumlitttlecom2);
            cell10Sum2.setCellStyle(styleDetailTableNumber);
            sheet1.autoSizeColumn(5);
        HSSFCell cell11Sum2 = row20.createCell(6);
            cell11Sum2.setCellFormula(sumPayagent2);
            cell11Sum2.setCellStyle(styleDetailTableNumber);
            sheet1.autoSizeColumn(6);
        HSSFCell cell12Sum2 = row20.createCell(7);
            cell12Sum2.setCellFormula(sumRcagent2);
            cell12Sum2.setCellStyle(styleDetailTableNumber);
            sheet1.autoSizeColumn(7);
        HSSFCell cell13Sum2 = row20.createCell(8);
            cell13Sum2.setCellFormula(sumPayRefund2);
            cell13Sum2.setCellStyle(styleDetailTableNumber);
            sheet1.autoSizeColumn(8);
        HSSFCell cell14Sum2 = row20.createCell(9);
            cell14Sum2.setCellFormula(sumComReceive2);
            cell14Sum2.setCellStyle(styleDetailTableNumber);
            sheet1.autoSizeColumn(9);

        HSSFRow rowLL2 = sheet1.createRow(count2+2);
        rowLL2.createCell(0).setCellStyle(styleBorderTop);
        rowLL2.createCell(1).setCellStyle(styleBorderTop);
        rowLL2.createCell(2).setCellStyle(styleBorderTop);
        rowLL2.createCell(3).setCellStyle(styleBorderTop);
        rowLL2.createCell(4).setCellStyle(styleBorderTop);
        rowLL2.createCell(5).setCellStyle(styleBorderTop);
        rowLL2.createCell(6).setCellStyle(styleBorderTop);
        rowLL2.createCell(7).setCellStyle(styleBorderTop);
        rowLL2.createCell(8).setCellStyle(styleBorderTop);
        rowLL2.createCell(9).setCellStyle(styleBorderTop);
    }
    
    private void getRefundTicketDetail(HSSFWorkbook wb, List refundTicket) {
        String sheetName = "refund ticket detail";// name of sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        // set Header Report (Row 1)
        HSSFCellStyle styleC11 = wb.createCellStyle();
        HSSFRow row01 = sheet.createRow(0);
        HSSFCell cell01 = row01.createCell(0);
        cell01.setCellValue("List Refund Ticket Detail");
        styleC11.setFont(getHeaderFont(wb.createFont()));
        cell01.setCellStyle(styleC11);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:G1"));

        
        // Set align Text
        HSSFDataFormat currency = wb.createDataFormat();
        HSSFCellStyle styleC21 = wb.createCellStyle();
        styleC21.setAlignment(styleC21.ALIGN_RIGHT);
        HSSFCellStyle styleC22 = wb.createCellStyle();
        styleC22.setAlignment(styleC22.ALIGN_LEFT);
        HSSFCellStyle styleAlignRightBorderAllHeaderTable = wb
                        .createCellStyle();
        styleAlignRightBorderAllHeaderTable.setFont(getHeaderTable(wb
                        .createFont()));
        styleAlignRightBorderAllHeaderTable
                        .setAlignment(styleAlignRightBorderAllHeaderTable.ALIGN_CENTER);
        styleAlignRightBorderAllHeaderTable
                        .setBorderTop(styleAlignRightBorderAllHeaderTable.BORDER_THIN);
        styleAlignRightBorderAllHeaderTable
                        .setBorderBottom(styleAlignRightBorderAllHeaderTable.BORDER_THIN);
        styleAlignRightBorderAllHeaderTable
                        .setBorderRight(styleAlignRightBorderAllHeaderTable.BORDER_THIN);
        styleAlignRightBorderAllHeaderTable
                        .setBorderLeft(styleAlignRightBorderAllHeaderTable.BORDER_THIN);
        HSSFCellStyle styleDetailTable = wb.createCellStyle();
            styleDetailTable.setAlignment(styleDetailTable.ALIGN_LEFT);
            styleDetailTable.setBorderLeft(styleDetailTable.BORDER_THIN);
            styleDetailTable.setBorderRight(styleDetailTable.BORDER_THIN);
        HSSFCellStyle styleDetailTableNumber = wb.createCellStyle();
            styleDetailTableNumber.setDataFormat(currency.getFormat("#,##0.00"));
            styleDetailTableNumber.setAlignment(styleDetailTableNumber.ALIGN_RIGHT);
            styleDetailTableNumber.setBorderLeft(styleDetailTableNumber.BORDER_THIN);
            styleDetailTableNumber.setBorderRight(styleDetailTableNumber.BORDER_THIN);
        HSSFCellStyle styleBorderTop = wb.createCellStyle();
            styleBorderTop.setBorderTop(styleBorderTop.BORDER_THIN);
        
        List<RefundTicketView> list = null;
       
        if(refundTicket != null && refundTicket.size() != 0 ){
            System.out.println("Size list : " + refundTicket.size());
            list = refundTicket;
        }else{
            list = null;
        }
        
        if(refundTicket != null && refundTicket.size() != 0 ){
        RefundTicketView refund = new RefundTicketView();
        if(list != null && list.size() != 0){
            System.out.println("Size list  refund : " + list.size());
            refund = (RefundTicketView) list.get(0);
        }else{
            refund = null;
        }
        
        // Row 2
        HSSFRow row02 = sheet.createRow(1);
        HSSFCell cell021 = row02.createCell(0);
        cell021.setCellValue("Refund Agent : ");
        cell021.setCellStyle(styleC21);
        HSSFCell cell022 = row02.createCell(1);
        cell022.setCellValue(refund.getRefundagentPage());
        cell022.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B2:D2"));
        HSSFCell cell023 = row02.createCell(4);
        cell023.setCellValue("Print By : ");
        cell023.setCellStyle(styleC21);
        HSSFCell cell024 = row02.createCell(5);
        cell024.setCellValue(refund.getPrintbyPage());
        cell024.setCellStyle(styleC22);

        // Row 3
        HSSFRow row03 = sheet.createRow(2);
        HSSFCell cell031 = row03.createCell(0);
        cell031.setCellValue("Refund By : ");
        cell031.setCellStyle(styleC21);
        HSSFCell cell032 = row03.createCell(1);
        cell032.setCellValue(refund.getRefundbyPage());
        cell032.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B3:D3"));
        HSSFCell cell033 = row03.createCell(4);
        cell033.setCellValue("Receive : ");
        cell033.setCellStyle(styleC21);
        HSSFCell cell034 = row03.createCell(5);
        cell034.setCellValue(refund.getReceivePage());
        cell034.setCellStyle(styleC22);

        // Row 4
        HSSFRow row04 = sheet.createRow(3);
        HSSFCell cell041 = row04.createCell(0);
        cell041.setCellValue("Passenger : ");
        cell041.setCellStyle(styleC21);
        HSSFCell cell042 = row04.createCell(1);
        cell042.setCellValue(refund.getPassengerPage());
        cell042.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B4:D4"));
        HSSFCell cell043 = row04.createCell(4);
        cell043.setCellValue("Paid : ");
        cell043.setCellStyle(styleC21);
        HSSFCell cell044 = row04.createCell(5);
        cell044.setCellValue(refund.getPaidPage());
        cell044.setCellStyle(styleC22);

        // Row 5
        HSSFRow row05 = sheet.createRow(4);
        HSSFCell cell051 = row05.createCell(0);
        cell051.setCellValue("Selector To Be Refund : ");
        cell051.setCellStyle(styleC21);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B5:D5"));
        HSSFCell cell052 = row05.createCell(1);
        cell052.setCellValue(refund.getSelectorrefundPage());
        cell052.setCellStyle(styleC22);
        HSSFCell cell053 = row05.createCell(4);
        cell053.setCellValue("Type Print : ");
        cell053.setCellStyle(styleC21);
        sheet.autoSizeColumn(4);
        HSSFCell cell054 = row05.createCell(5);
        cell054.setCellValue(refund.getTypeprintPage());
        cell054.setCellStyle(styleC22);

        // Row 6
        HSSFRow row06 = sheet.createRow(5);
        HSSFCell cell611 = row06.createCell(0);
        cell611.setCellValue("Print on : ");
        cell611.setCellStyle(styleC21);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B6:D6"));
        HSSFCell cell621 = row06.createCell(1);
        cell621.setCellValue(refund.getPrintondatePage());
        cell621.setCellStyle(styleC22);
        HSSFCell cell063 = row06.createCell(4);
        cell063.setCellValue("Page : ");
        cell063.setCellStyle(styleC21);
        HSSFCell cell064 = row06.createCell(5);
        cell064.setCellValue("1 ");
        cell064.setCellStyle(styleC22);

        // Header Table
        HSSFRow row6 = sheet.createRow(8);
        HSSFCell cell61 = row6.createCell(0);
        cell61.setCellValue("Refund No");
        cell61.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(0);
        HSSFCell cell62 = row6.createCell(1);
        cell62.setCellValue("Refund Date");
        cell62.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(1);
        HSSFCell cell63 = row6.createCell(2);
        cell63.setCellValue("Air");
        sheet.autoSizeColumn(2);
        cell63.setCellStyle(styleAlignRightBorderAllHeaderTable);
        HSSFCell cell65 = row6.createCell(3);
        cell65.setCellValue("Doc No");
        cell65.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(3);
        HSSFCell cell66 = row6.createCell(4);
        cell66.setCellValue("Airline Agent");
        cell66.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(5);
        HSSFCell cell67 = row6.createCell(5);
        cell67.setCellValue("Agent");
        cell67.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(5);
        HSSFCell cell677 = row6.createCell(6);
        cell677.setCellValue("Passenger");
        cell677.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(6);
        HSSFCell cell68 = row6.createCell(7);
        cell68.setCellValue("Sector Refund");
        cell68.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(7);
        HSSFCell cell69 = row6.createCell(8);
        cell69.setCellValue("Receive Airline");
        cell69.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(8);
        HSSFCell cell71 = row6.createCell(9);
        cell71.setCellValue("Receive Date");
        cell71.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(9);
        HSSFCell cell72 = row6.createCell(10);
        cell72.setCellValue("Pay No");
        cell72.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(10);
        HSSFCell cell73 = row6.createCell(11);
        cell73.setCellValue("Export");
        cell73.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(11);
        HSSFCell cell74 = row6.createCell(12);
        cell74.setCellValue("Pay Date");
        cell74.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(12);
        HSSFCell cell75 = row6.createCell(13);
        cell75.setCellValue("Pay Customer");
        cell75.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(13);
        HSSFCell cell76 = row6.createCell(14);
        cell76.setCellValue("Airline Comm");
        cell76.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(14);
        HSSFCell cell77 = row6.createCell(15);
        cell77.setCellValue("Profit");
        cell77.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(15);
        
        List<RefundTicketView> listRefund = refundTicket;
        
        for (int r = 0; r < listRefund.size(); r++) {
            System.out.println("Refund No: " + listRefund.get(r).getRefundno());
        }

        int count = 9 + listRefund.size();
        int startSum = 1;
        int endSum = 1;
        int numSum = 0;

        for (int r = 9; r < count; r++) {
            HSSFRow row = sheet.createRow(r);
            HSSFCell cell1 = row.createCell(0);
                cell1.setCellValue(listRefund.get(r-9).getRefundno());
                cell1.setCellStyle(styleDetailTable);
                sheet.autoSizeColumn(0);
            HSSFCell cell2 = row.createCell(1);
                cell2.setCellValue(listRefund.get(r-9).getRefunddate());
                cell2.setCellStyle(styleDetailTable);
                sheet.autoSizeColumn(1);
            HSSFCell cell3 = row.createCell(2);
                cell3.setCellValue(listRefund.get(r-9).getAir());
                cell3.setCellStyle(styleDetailTable);
                sheet.autoSizeColumn(2);
            HSSFCell cell4 = row.createCell(3);
                cell4.setCellValue(listRefund.get(r-9).getDocno());
                cell4.setCellStyle(styleDetailTable);
                sheet.autoSizeColumn(3);
            HSSFCell cell55 = row.createCell(4);
                cell55.setCellValue(listRefund.get(r-9).getAirlineagent());
                cell55.setCellStyle(styleDetailTable);
                sheet.autoSizeColumn(4);
            HSSFCell cell5 = row.createCell(5);
                cell5.setCellValue(listRefund.get(r-9).getAirlineagentname());
                cell5.setCellStyle(styleDetailTable);
                sheet.autoSizeColumn(5);
            HSSFCell cell6 = row.createCell(6);
                cell6.setCellValue(listRefund.get(r-9).getPassenger());
                cell6.setCellStyle(styleDetailTable);
                sheet.autoSizeColumn(6);
            HSSFCell cell7 = row.createCell(7);
                cell7.setCellValue(listRefund.get(r-9).getSectorrefund());
                cell7.setCellStyle(styleDetailTable);
                sheet.autoSizeColumn(7);
            HSSFCell cell8 = row.createCell(8);
                BigDecimal cancel = new BigDecimal(listRefund.get(r-9).getReceiveairline());
                cell8.setCellValue((cancel != null) ? cancel.doubleValue() : new BigDecimal("0").doubleValue());
                cell8.setCellStyle(styleDetailTableNumber);
                sheet.autoSizeColumn(8);
            HSSFCell cell9 = row.createCell(9);
                cell9.setCellValue(listRefund.get(r-9).getReceivedate());
                cell9.setCellStyle(styleDetailTable);
                sheet.autoSizeColumn(9);
            HSSFCell cell10 = row.createCell(10);
                cell10.setCellValue(listRefund.get(r-9).getPayno());
                cell10.setCellStyle(styleDetailTable);
                sheet.autoSizeColumn(10);
            HSSFCell cell11 = row.createCell(11);
                cell11.setCellValue(listRefund.get(r-9).getExport());
                cell11.setCellStyle(styleDetailTable);
                sheet.autoSizeColumn(11);
            HSSFCell cell12 = row.createCell(12);
                cell12.setCellValue(listRefund.get(r-9).getPaydate());
                cell12.setCellStyle(styleDetailTable);
                sheet.autoSizeColumn(12);
            HSSFCell cell13 = row.createCell(13);
                BigDecimal payc = new BigDecimal(listRefund.get(r-9).getPaycustomer());
                cell13.setCellValue((payc != null) ? payc.doubleValue() : new BigDecimal("0").doubleValue());
                cell13.setCellStyle(styleDetailTableNumber);
                sheet.autoSizeColumn(13);
            HSSFCell cell14 = row.createCell(14);
                BigDecimal wait = new BigDecimal(listRefund.get(r-9).getAirlinecomm());
                cell14.setCellValue((wait != null) ? wait.doubleValue() : new BigDecimal("0").doubleValue());
                cell14.setCellStyle(styleDetailTableNumber);
                sheet.autoSizeColumn(14);
            HSSFCell cell15 = row.createCell(15);
                BigDecimal profit = new BigDecimal(listRefund.get(r-9).getProfit());
                cell15.setCellValue((profit != null) ? profit.doubleValue() : new BigDecimal("0").doubleValue());
                cell15.setCellStyle(styleDetailTableNumber);
                sheet.autoSizeColumn(15);
        }
        
        System.out.println(count);
        HSSFRow rowL = sheet.createRow(count);
        rowL.createCell(0).setCellStyle(styleBorderTop);
        rowL.createCell(1).setCellStyle(styleBorderTop);
        rowL.createCell(2).setCellStyle(styleBorderTop);
        rowL.createCell(3).setCellStyle(styleBorderTop);
        rowL.createCell(4).setCellStyle(styleBorderTop);
        rowL.createCell(5).setCellStyle(styleBorderTop);
        rowL.createCell(6).setCellStyle(styleBorderTop);
        rowL.createCell(7).setCellStyle(styleBorderTop);
        rowL.createCell(8).setCellStyle(styleBorderTop);
        rowL.createCell(9).setCellStyle(styleBorderTop);
        rowL.createCell(10).setCellStyle(styleBorderTop);
        rowL.createCell(11).setCellStyle(styleBorderTop);
        rowL.createCell(12).setCellStyle(styleBorderTop);
        rowL.createCell(13).setCellStyle(styleBorderTop);
        rowL.createCell(14).setCellStyle(styleBorderTop);
        rowL.createCell(15).setCellStyle(styleBorderTop);
        }else{
            HSSFRow row02_2 = sheet.createRow(1);
            HSSFCell cell021 = row02_2.createCell(0);
            cell021.setCellValue("No Data");
            cell021.setCellStyle(styleC21);
            sheet.autoSizeColumn(0);
        }
    }
    
    public void genSummaryAirlinePaxReport(HSSFWorkbook wb, List summaryAirlinePax) {
        String sheetNameInv = "Invoice"; // name of sheet
        String sheetNameDetail = "Detail";
        HSSFSheet sheetInv = wb.createSheet(sheetNameInv);
        HSSFSheet sheetDetail = wb.createSheet(sheetNameDetail);
        
        SummaryAirlinePaxView dataheader = new SummaryAirlinePaxView();

        HSSFDataFormat currency = wb.createDataFormat();
        // Set align Text
        HSSFCellStyle styleC21 = wb.createCellStyle();
        styleC21.setAlignment(styleC21.ALIGN_RIGHT);
        HSSFCellStyle styleC22 = wb.createCellStyle();
        styleC22.setAlignment(styleC22.ALIGN_LEFT);
        HSSFCellStyle styleC23 = wb.createCellStyle();
        styleC23.setAlignment(styleC22.ALIGN_CENTER);

        HSSFCellStyle styleC25 = wb.createCellStyle();
        styleC25.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC25.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC25.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleC25.setBorderBottom(HSSFCellStyle.BORDER_THIN);
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

        HSSFCellStyle styleC30 = wb.createCellStyle();
        styleC30.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC30.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC30.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleC30.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC30.setAlignment(styleC22.ALIGN_CENTER);

        if(!summaryAirlinePax.isEmpty()){
            dataheader = (SummaryAirlinePaxView)summaryAirlinePax.get(0);
            for(int x = 1 ; x < 4 ; x++){
                if( x == 1){
                    // set Header Report (Row 1)
                    HSSFCellStyle styleC1 = wb.createCellStyle();
                    HSSFRow row1 = sheetInv.createRow(0);
                    HSSFCell cell1 = row1.createCell(0);
                    cell1.setCellValue("List Summary Airline (Pax)");
                    styleC1.setFont(getHeaderFont(wb.createFont()));
                    cell1.setCellStyle(styleC1);
                    sheetInv.addMergedRegion(CellRangeAddress.valueOf("A1:E1"));

                    // Row 2
                    HSSFRow row2 = sheetInv.createRow(1);
                    HSSFCell cell21 = row2.createCell(0);
                    cell21.setCellValue("Invoice Date : ");
                    cell21.setCellStyle(styleC21);
                    HSSFCell cell22 = row2.createCell(1);
                    cell22.setCellValue(dataheader.getHeaderinvdatefrom());
                    cell22.setCellStyle(styleC22);
                    HSSFCell cell23 = row2.createCell(2);
                    if(!"".equalsIgnoreCase(dataheader.getHeaderinvdateto())){
                        cell23.setCellValue(" to " +dataheader.getHeaderinvdateto());
                        cell23.setCellStyle(styleC22);
                    }
                    HSSFCell cell24 = row2.createCell(3);
                    cell24.setCellValue("Print By : ");
                    cell24.setCellStyle(styleC21);
                    HSSFCell cell25 = row2.createCell(4);
                    cell25.setCellValue(dataheader.getHeaderprintby());
                    cell25.setCellStyle(styleC22);

                    // Row 3
                    HSSFRow row3 = sheetInv.createRow(2);
                    HSSFCell cell31 = row3.createCell(0);
                    cell31.setCellValue("Issue Date : ");
                    cell31.setCellStyle(styleC21);
                    HSSFCell cell32 = row3.createCell(1);
                    cell32.setCellValue(dataheader.getHeaderissuedatefrom());
                    cell32.setCellStyle(styleC22);
                    HSSFCell cell33 = row3.createCell(2);
                    if(!"".equalsIgnoreCase(dataheader.getHeaderissuedateto())){
                        cell33.setCellValue(" to " +dataheader.getHeaderissuedateto());
                        cell33.setCellStyle(styleC22);
                    }

                    // Row 4
                    HSSFRow row4 = sheetInv.createRow(3);
                    HSSFCell cell41 = row4.createCell(0);
                    cell41.setCellValue("Type Routing : ");
                    cell41.setCellStyle(styleC21);
                    HSSFCell cell42 = row4.createCell(1);
                    cell42.setCellValue(dataheader.getHeadertyperouting());
                    cell42.setCellStyle(styleC22);
                    HSSFCell cell43 = row4.createCell(3);
                    cell43.setCellValue("Routing Detail : ");
                    cell43.setCellStyle(styleC21);
                    HSSFCell cell44 = row4.createCell(4);
                    cell44.setCellValue(dataheader.getHeaderroutingdetail());
                    cell44.setCellStyle(styleC22);

                    // Row 5
                    HSSFRow row5 = sheetInv.createRow(4);
                    HSSFCell cell51 = row5.createCell(0);
                    cell51.setCellValue("Air : ");
                    cell51.setCellStyle(styleC21);
                    HSSFCell cell52 = row5.createCell(1);
                    cell52.setCellValue(dataheader.getHeaderair());
                    cell52.setCellStyle(styleC22);
                    HSSFCell cell53 = row5.createCell(3);
                    cell53.setCellValue("Passenger : ");
                    cell53.setCellStyle(styleC21);
                    HSSFCell cell54 = row5.createCell(4);
                    cell54.setCellValue(dataheader.getHeaderpassenger());
                    cell54.setCellStyle(styleC22);

                    // Row 6
                    HSSFRow row6 = sheetInv.createRow(5);
                    HSSFCell cell61 = row6.createCell(0);
                    cell61.setCellValue("Agent Name : ");
                    cell61.setCellStyle(styleC21);
                    HSSFCell cell62 = row6.createCell(1);
                    cell62.setCellValue(dataheader.getHeaderagentname());
                    cell62.setCellStyle(styleC22);
                    
                    // Row 7
                    HSSFRow row7 = sheetInv.createRow(6);
                    HSSFCell cell71 = row7.createCell(0);
                    cell71.setCellValue("Print on : ");
                    cell71.setCellStyle(styleC21);
                    HSSFCell cell72 = row7.createCell(1);
                    cell72.setCellValue(dataheader.getHeaderprinton());
                    cell72.setCellStyle(styleC22);
                }
                if( x == 2){
                                        // set Header Report (Row 1)
                    HSSFCellStyle styleC1 = wb.createCellStyle();
                    HSSFRow row1 = sheetDetail.createRow(0);
                    HSSFCell cell1 = row1.createCell(0);
                    cell1.setCellValue("List Summary Airline (Pax)");
                    styleC1.setFont(getHeaderFont(wb.createFont()));
                    cell1.setCellStyle(styleC1);
                    sheetDetail.addMergedRegion(CellRangeAddress.valueOf("A1:E1"));

                    // Row 2
                    HSSFRow row2 = sheetDetail.createRow(1);
                    HSSFCell cell21 = row2.createCell(0);
                    cell21.setCellValue("Invoice Date : ");
                    cell21.setCellStyle(styleC21);
                    HSSFCell cell22 = row2.createCell(1);
                    cell22.setCellValue(dataheader.getHeaderinvdatefrom());
                    cell22.setCellStyle(styleC22);
                    HSSFCell cell23 = row2.createCell(2);
                    if(!"".equalsIgnoreCase(dataheader.getHeaderinvdateto())){
                        cell23.setCellValue(" to " +dataheader.getHeaderinvdateto());
                        cell23.setCellStyle(styleC22);
                    }
                    HSSFCell cell24 = row2.createCell(3);
                    cell24.setCellValue("Print By : ");
                    cell24.setCellStyle(styleC21);
                    HSSFCell cell25 = row2.createCell(4);
                    cell25.setCellValue(dataheader.getHeaderprintby());
                    cell25.setCellStyle(styleC22);

                    // Row 3
                    HSSFRow row3 = sheetDetail.createRow(2);
                    HSSFCell cell31 = row3.createCell(0);
                    cell31.setCellValue("Issue Date : ");
                    cell31.setCellStyle(styleC21);
                    HSSFCell cell32 = row3.createCell(1);
                    cell32.setCellValue(dataheader.getHeaderissuedatefrom());
                    cell32.setCellStyle(styleC22);
                    HSSFCell cell33 = row3.createCell(2);
                    if(!"".equalsIgnoreCase(dataheader.getHeaderissuedateto())){
                        cell33.setCellValue(" to " +dataheader.getHeaderissuedateto());
                        cell33.setCellStyle(styleC22);
                    }

                    // Row 4
                    HSSFRow row4 = sheetDetail.createRow(3);
                    HSSFCell cell41 = row4.createCell(0);
                    cell41.setCellValue("Type Routing : ");
                    cell41.setCellStyle(styleC21);
                    HSSFCell cell42 = row4.createCell(1);
                    cell42.setCellValue(dataheader.getHeadertyperouting());
                    cell42.setCellStyle(styleC22);
                    HSSFCell cell43 = row4.createCell(3);
                    cell43.setCellValue("Routing Detail : ");
                    cell43.setCellStyle(styleC21);
                    HSSFCell cell44 = row4.createCell(4);
                    cell44.setCellValue(dataheader.getHeaderroutingdetail());
                    cell44.setCellStyle(styleC22);

                    // Row 5
                    HSSFRow row5 = sheetDetail.createRow(4);
                    HSSFCell cell51 = row5.createCell(0);
                    cell51.setCellValue("Air : ");
                    cell51.setCellStyle(styleC21);
                    HSSFCell cell52 = row5.createCell(1);
                    cell52.setCellValue(dataheader.getHeaderair());
                    cell52.setCellStyle(styleC22);
                    HSSFCell cell53 = row5.createCell(3);
                    cell53.setCellValue("Passenger : ");
                    cell53.setCellStyle(styleC21);
                    HSSFCell cell54 = row5.createCell(4);
                    cell54.setCellValue(dataheader.getHeaderpassenger());
                    cell54.setCellStyle(styleC22);

                    // Row 6
                    HSSFRow row6 = sheetDetail.createRow(5);
                    HSSFCell cell61 = row6.createCell(0);
                    cell61.setCellValue("Agent Name : ");
                    cell61.setCellStyle(styleC21);
                    HSSFCell cell62 = row6.createCell(1);
                    cell62.setCellValue(dataheader.getHeaderagentname());
                    cell62.setCellStyle(styleC22);
                                        
                    // Row 7
                    HSSFRow row7 = sheetInv.createRow(6);
                    HSSFCell cell71 = row7.createCell(0);
                    cell71.setCellValue("Print on : ");
                    cell71.setCellStyle(styleC21);
                    HSSFCell cell72 = row7.createCell(1);
                    cell72.setCellValue(dataheader.getHeaderprinton());
                    cell72.setCellStyle(styleC22);
                }
            }
            // Header Table
            HSSFCellStyle styleC3 = wb.createCellStyle();
            styleC3.setFont(getHeaderTable(wb.createFont()));
            styleC3.setAlignment(styleC3.ALIGN_CENTER);
            styleC3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            styleC3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            styleC3.setBorderRight(HSSFCellStyle.BORDER_THIN);
            styleC3.setBorderTop(HSSFCellStyle.BORDER_THIN);
            
            int count = 9;
            int tempcount = 0 ;
            int tempcount2 = 0 ;
            int rowdetail = 0;
            int rowrouting = 0;
            int x = 0;
            int y = 0;
            for(int i=0;i<summaryAirlinePax.size();i++){
                SummaryAirlinePaxView data = (SummaryAirlinePaxView)summaryAirlinePax.get(i);
                
                if("inv".equalsIgnoreCase(data.getPage())){
                    //inv
                    HSSFRow row8 = sheetInv.createRow(8);
                    HSSFCell cell81 = row8.createCell(0);
                    cell81.setCellValue("INV No.");
                    cell81.setCellStyle(styleC3);
                    sheetInv.autoSizeColumn(0);
                    HSSFCell cell82 = row8.createCell(1);
                    cell82.setCellValue("Amount Wendy");
                    cell82.setCellStyle(styleC3);
                    sheetInv.autoSizeColumn(1);
                    HSSFCell cell83 = row8.createCell(2);
                    cell83.setCellValue("Amount Inbound");
                    sheetInv.autoSizeColumn(2);
                    cell83.setCellStyle(styleC3);
                    
                    HSSFRow row = sheetInv.createRow(count + i);
                    HSSFCell celldata0 = row.createCell(0);
                    celldata0.setCellValue(String.valueOf(data.getInvnoPax()));
                    celldata0.setCellStyle(styleC26);

                    HSSFCell celldata1 = row.createCell(1);
                    celldata1.setCellValue("".equalsIgnoreCase(String.valueOf(data.getAmountwendy())) ? 0 : (new BigDecimal(data.getAmountwendy())).doubleValue());
                    celldata1.setCellStyle(styleC25);

                    HSSFCell celldata2 = row.createCell(2);
                    celldata2.setCellValue("".equalsIgnoreCase(String.valueOf(data.getAmountinbound())) ? 0 : (new BigDecimal(data.getAmountinbound())).doubleValue());
                    celldata2.setCellStyle(styleC25);

                    tempcount = count + i + 1;
                }
                //detail
                if("detail".equalsIgnoreCase(data.getPage())){
                    //Total inv
                    HSSFRow rowtotal = sheetInv.createRow(tempcount);
                    String totalwendy = "SUM(B" + 10+":B"+(tempcount)+")";
                    String totalinbound = "SUM(C" + 10+":C"+(tempcount)+")";

                    HSSFCellStyle styleTotal = wb.createCellStyle();
                    styleTotal.setFont(getHeaderTable(wb.createFont()));
                    styleTotal.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                    styleTotal.setBorderRight(HSSFCellStyle.BORDER_THIN);
                    styleTotal.setBorderTop(HSSFCellStyle.BORDER_THIN);
                    styleTotal.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                    styleTotal.setAlignment(styleC22.ALIGN_RIGHT);

 
                    HSSFCell cellTotal00 = rowtotal.createCell(0);
                    cellTotal00.setCellValue("Total");
                    cellTotal00.setCellStyle(styleTotal);
                    HSSFCell cellTotal01 = rowtotal.createCell(1);
                    cellTotal01.setCellFormula(totalwendy);
                    cellTotal01.setCellStyle(styleC25);
                    HSSFCell cellTotal02 = rowtotal.createCell(2);
                    cellTotal02.setCellFormula(totalinbound);
                    cellTotal02.setCellStyle(styleC25);
                    
//                    if(tempcount != 0){
//                        rowdetail = tempcount+3;
//                    }
                    HSSFRow row8 = sheetDetail.createRow(8);
                    HSSFCell cell81 = row8.createCell(0);
                    cell81.setCellValue("Payment Type");
                    cell81.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(0);
                    HSSFCell cell82 = row8.createCell(1);
                    cell82.setCellValue("Type Routing");
                    cell82.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(1);
                    HSSFCell cell83 = row8.createCell(2);
                    cell83.setCellValue("Pax");
                    sheetDetail.autoSizeColumn(2);
                    cell83.setCellStyle(styleC3);
                    HSSFCell cell84 = row8.createCell(3);
                    cell84.setCellValue("Air");
                    cell84.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(3);
                    HSSFCell cell85 = row8.createCell(4);
                    cell85.setCellValue("Net Sales");
                    cell85.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(4);
                    HSSFCell cell86 = row8.createCell(5);
                    cell86.setCellValue("Tax");
                    cell86.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(5);
                    HSSFCell cell87 = row8.createCell(6);
                    cell87.setCellValue("Ins.");
                    cell87.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(6);
                    HSSFCell cell88 = row8.createCell(7);
                    cell88.setCellValue("Comm");
                    cell88.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(7);
                    HSSFCell cell89 = row8.createCell(8);
                    cell89.setCellValue("Cost");
                    cell89.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(8);
                    HSSFCell cell90 = row8.createCell(9);
                    cell90.setCellValue("PF[LOSS]");
                    cell90.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(9);
                    HSSFCell cell91 = row8.createCell(10);
                    cell91.setCellValue("Inv. Amt");
                    cell91.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(10);
                    HSSFCell cell92 = row8.createCell(11);
                    cell92.setCellValue("No.Inv Amt");
                    cell92.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(11);
                    HSSFCell cell93 = row8.createCell(12);
                    cell93.setCellValue("INV No.");
                    cell93.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(12);
                    HSSFCell cell94 = row8.createCell(13);
                    cell94.setCellValue("Diff Vat");
                    cell94.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(13);
                   
                    HSSFRow row = sheetDetail.createRow(count + x);
                    HSSFCell celldata0 = row.createCell(0);

                    celldata0.setCellValue(String.valueOf(data.getTypepayment()));
                    celldata0.setCellStyle(styleC29);

                    HSSFCell celldata1 = row.createCell(1);
                    celldata1.setCellValue(String.valueOf(data.getTyperounting()));
                    celldata1.setCellStyle(styleC29);

                    HSSFCell celldata2 = row.createCell(2);
                    celldata2.setCellValue("".equalsIgnoreCase(String.valueOf(data.getPax())) ? 0 : (new BigDecimal(data.getPax())).doubleValue());
                    celldata2.setCellStyle(styleC26);

                    HSSFCell celldata3 = row.createCell(3);
                    celldata3.setCellValue(String.valueOf(data.getAir()));
                    celldata3.setCellStyle(styleC26);

                    HSSFCell celldata4 = row.createCell(4);
                    celldata4.setCellValue("".equalsIgnoreCase(String.valueOf(data.getNetsales())) ? 0 : (new BigDecimal(data.getNetsales())).doubleValue());
                    celldata4.setCellStyle(styleC25);

                    HSSFCell celldata5 = row.createCell(5);
                    celldata5.setCellValue("".equalsIgnoreCase(String.valueOf(data.getTax())) ? 0 : (new BigDecimal(data.getTax())).doubleValue());
                    celldata5.setCellStyle(styleC25);

                    HSSFCell celldata6 = row.createCell(6);
                    celldata6.setCellValue("".equalsIgnoreCase(String.valueOf(data.getIns())) ? 0 : (new BigDecimal(data.getIns())).doubleValue());
                    celldata6.setCellStyle(styleC25);

                    HSSFCell celldata7 = row.createCell(7);
                    celldata7.setCellValue("".equalsIgnoreCase(String.valueOf(data.getComms())) ? 0 : (new BigDecimal(data.getComms())).doubleValue());
                    celldata7.setCellStyle(styleC25);

                    HSSFCell celldata8 = row.createCell(8);
                    celldata8.setCellValue("".equalsIgnoreCase(String.valueOf(data.getCost())) ? 0 : (new BigDecimal(data.getCost())).doubleValue());
                    celldata8.setCellStyle(styleC25);

                    HSSFCell celldata9 = row.createCell(9);
                    celldata9.setCellValue("".equalsIgnoreCase(String.valueOf(data.getPfloss())) ? 0 : (new BigDecimal(data.getPfloss())).doubleValue());
                    celldata9.setCellStyle(styleC25);

                    HSSFCell celldata10 = row.createCell(10);
                    celldata10.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInvamount())) ? 0 : (new BigDecimal(data.getInvamount())).doubleValue());
                    celldata10.setCellStyle(styleC25);

                    HSSFCell celldata11 = row.createCell(11); // No inv amount
//                    celldata11.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInvamount())) ? 0 : (new BigDecimal(data.getInvamount())).doubleValue());
                    celldata11.setCellStyle(styleC25);

                    HSSFCell celldata12 = row.createCell(12);
                    celldata12.setCellValue(String.valueOf(data.getInvno()));
                    celldata12.setCellStyle(styleC29);

                    HSSFCell celldata13 = row.createCell(13);
                    celldata13.setCellValue("".equalsIgnoreCase(String.valueOf(data.getDiff())) ? 0 : (new BigDecimal(data.getDiff())).doubleValue());
                    celldata13.setCellStyle(styleC25);
//                    tempcount2 = count + i + 4;
                     // set total last row
                    if(i == (summaryAirlinePax.size()-1)){
                        HSSFRow rows = sheetDetail.createRow(count + x + 1);

                        String totalPax = "SUM(C" + 10+":C"+(count + x + 1)+")";
                        String totalNet = "SUM(E" + 10+":E"+(count + x + 1)+")";
                        String totalTax = "SUM(F" + 10+":F"+(count + x + 1)+")";
                        String totalIns = "SUM(G" + 10+":G"+(count + x + 1)+")";
                        String totalComm = "SUM(H" + 10+":H"+(count + x + 1)+")";
                        String totalCost = "SUM(I" + 10+":I"+(count + x + 1)+")";
                        String totalPfloss = "SUM(J" + 10+":J"+(count + x + 1)+")";
                        String totalInvamt = "SUM(K" + 10+":K"+(count + x + 1)+")";
                        String totalNoInvamt= "SUM(L" + 10+":L"+(count + x + 1)+")";
                        String totalDiff = "SUM(N" + 10+":N"+(count + x + 1)+")";

                        HSSFCell celldatas0 = rows.createCell(0);
                        celldatas0.setCellStyle(styleC29);
                        HSSFCell celldatas01 = rows.createCell(1);
                        celldatas01.setCellStyle(styleC29);
                        HSSFCell celldatas02 = rows.createCell(2);
                        celldatas02.setCellFormula(totalPax);
                        celldatas02.setCellStyle(styleC26);
                     
                        HSSFCell celldatas03 = rows.createCell(4);
                        celldatas03.setCellFormula(totalNet);
                        celldatas03.setCellStyle(styleC25);
                        HSSFCell celldatas04 = rows.createCell(5);
                        celldatas04.setCellFormula(totalTax);
                        celldatas04.setCellStyle(styleC25);
                        HSSFCell celldatas05 = rows.createCell(6);
                        celldatas05.setCellFormula(totalIns);
                        celldatas05.setCellStyle(styleC25);
                        HSSFCell celldatas06 = rows.createCell(7);
                        celldatas06.setCellFormula(totalComm);
                        celldatas06.setCellStyle(styleC25);
                        HSSFCell celldatas07 = rows.createCell(8);
                        celldatas07.setCellFormula(totalCost);
                        celldatas07.setCellStyle(styleC25);
                        HSSFCell celldatas08 = rows.createCell(9);
                        celldatas08.setCellFormula(totalPfloss);
                        celldatas08.setCellStyle(styleC25);
                        HSSFCell celldatas09 = rows.createCell(10);
                        celldatas09.setCellFormula(totalInvamt);
                        celldatas09.setCellStyle(styleC25);
                        HSSFCell celldatas10 = rows.createCell(11);
                        celldatas10.setCellFormula(totalNoInvamt);
                        celldatas10.setCellStyle(styleC25);
                        HSSFCell celldatas11 = rows.createCell(13);
                        celldatas11.setCellFormula(totalDiff);
                        celldatas11.setCellStyle(styleC25);
                        
                        HSSFCell celldatas12 = rows.createCell(3);
                        celldatas12.setCellStyle(styleC29);
                        HSSFCell celldatas13 = rows.createCell(12);
                        celldatas13.setCellStyle(styleC29);
                    }
                    x ++ ;
            }
            for (int j = 0;j< 30;j++) {
                sheetInv.autoSizeColumn(j);
                sheetDetail.autoSizeColumn(j);
            }
        }
    }
}
    
    
    private void getSummaryTicketCostAndIncome(HSSFWorkbook wb, List sumCostIncome) {
        String sheetIncome = "CostIncome";// name of sheet
        String sheetIncomeSum = "CostIncomeSummary";
        
//        String sheetNameInv = "Invoice"; // name of sheet
//        String sheetNameDetail = "Detail";
        HSSFSheet sheetInc = wb.createSheet(sheetIncome);
        HSSFSheet sheetIncSum = wb.createSheet(sheetIncomeSum);
        
        SummaryTicketCostAndIncomeView dataheader = new SummaryTicketCostAndIncomeView();

        HSSFDataFormat currency = wb.createDataFormat();
        // Set align Text
        HSSFCellStyle styleC21 = wb.createCellStyle();
        styleC21.setAlignment(styleC21.ALIGN_RIGHT);
        HSSFCellStyle styleC22 = wb.createCellStyle();
        styleC22.setAlignment(styleC22.ALIGN_LEFT);
        HSSFCellStyle styleC23 = wb.createCellStyle();
        styleC23.setAlignment(styleC22.ALIGN_CENTER);

        HSSFCellStyle styleC25 = wb.createCellStyle();
        styleC25.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC25.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC25.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleC25.setBorderBottom(HSSFCellStyle.BORDER_THIN);
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

        HSSFCellStyle styleC30 = wb.createCellStyle();
        styleC30.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC30.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC30.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleC30.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC30.setAlignment(styleC22.ALIGN_CENTER);

        if(!sumCostIncome.isEmpty()){
            dataheader = (SummaryTicketCostAndIncomeView)sumCostIncome.get(0);
            for(int x = 1 ; x < 3 ; x++){
                if( x == 1){
                    // set Header Report (Row 1)
                    HSSFCellStyle styleC1 = wb.createCellStyle();
                    HSSFRow row1 = sheetInc.createRow(0);
                    HSSFCell cell1 = row1.createCell(0);
                    cell1.setCellValue("Summary Ticket Cost & Income");
                    styleC1.setFont(getHeaderFont(wb.createFont()));
                    cell1.setCellStyle(styleC1);
                    sheetInc.addMergedRegion(CellRangeAddress.valueOf("A1:E1"));

                    // Row 2
                    HSSFRow row2 = sheetInc.createRow(1);
                    HSSFCell cell21 = row2.createCell(0);
                    cell21.setCellValue("Invoice Date : ");
                    cell21.setCellStyle(styleC21);
                    HSSFCell cell22 = row2.createCell(1);
                    cell22.setCellValue(dataheader.getHeaderinvdatefrom());
                    cell22.setCellStyle(styleC22);
                    HSSFCell cell23 = row2.createCell(2);
                    if(!"".equalsIgnoreCase(dataheader.getHeaderinvdateto())){
                        cell23.setCellValue(" to " +dataheader.getHeaderinvdateto());
                        cell23.setCellStyle(styleC22);
                    }
                    HSSFCell cell24 = row2.createCell(3);
                    cell24.setCellValue("Print On : ");
                    cell24.setCellStyle(styleC21);
                    HSSFCell cell25 = row2.createCell(4);
                    cell25.setCellValue(dataheader.getHeaderprinton());
                    cell25.setCellStyle(styleC22);

                    // Row 3
                    HSSFRow row3 = sheetInc.createRow(2);
                    HSSFCell cell31 = row3.createCell(0);
                    cell31.setCellValue("Issue Date : ");
                    cell31.setCellStyle(styleC21);
                    HSSFCell cell32 = row3.createCell(1);
                    cell32.setCellValue(dataheader.getHeaderissuedatefrom());
                    cell32.setCellStyle(styleC22);
                    HSSFCell cell33 = row3.createCell(2);
                    if(!"".equalsIgnoreCase(dataheader.getHeaderissuedateto())){
                        cell33.setCellValue(" to " +dataheader.getHeaderissuedateto());
                        cell33.setCellStyle(styleC22);
                    }
                    HSSFCell cell34 = row3.createCell(3);
                    cell34.setCellValue("Page : ");
                    cell34.setCellStyle(styleC21);
                    HSSFCell cell35 = row3.createCell(4);
                    cell35.setCellValue("1");
                    cell35.setCellStyle(styleC22);
                    
                    // Row 4
                    HSSFRow row4 = sheetInc.createRow(3);
                    HSSFCell cell41 = row4.createCell(0);
                    cell41.setCellValue("Department : "); 
                    cell41.setCellStyle(styleC21);
                    HSSFCell cell42 = row4.createCell(1);
                    cell42.setCellValue(dataheader.getHeaderdepartment());
                    cell42.setCellStyle(styleC22);

                    // Row 5
                    HSSFRow row5 = sheetInc.createRow(4);
                    HSSFCell cell51 = row5.createCell(0);
                    cell51.setCellValue("Sale Staff : ");
                    cell51.setCellStyle(styleC21);
                    HSSFCell cell52 = row5.createCell(1);
                    cell52.setCellValue(dataheader.getHeadersalestaff());
                    cell52.setCellStyle(styleC22);

                    // Row 6
                    HSSFRow row6 = sheetInc.createRow(5);
                    HSSFCell cell61 = row6.createCell(0);
                    cell61.setCellValue("Term Pay : ");
                    cell61.setCellStyle(styleC21);
                    HSSFCell cell62 = row6.createCell(1);
                    cell62.setCellValue(dataheader.getHeadertermpay());
                    cell62.setCellStyle(styleC22);
                    
                }
                if( x == 2){
                    // set Header Report (Row 1)
                    HSSFCellStyle styleC1 = wb.createCellStyle();
                    HSSFRow row1 = sheetIncSum.createRow(0);
                    HSSFCell cell1 = row1.createCell(0);
                    cell1.setCellValue("Summary Ticket Cost & Income");
                    styleC1.setFont(getHeaderFont(wb.createFont()));
                    cell1.setCellStyle(styleC1);
                    sheetIncSum.addMergedRegion(CellRangeAddress.valueOf("A1:E1"));

                    // Row 2
                    HSSFRow row2 = sheetIncSum.createRow(1);
                    HSSFCell cell21 = row2.createCell(0);
                    cell21.setCellValue("Invoice Date : ");
                    cell21.setCellStyle(styleC21);
                    HSSFCell cell22 = row2.createCell(1);
                    cell22.setCellValue(dataheader.getHeaderinvdatefrom());
                    cell22.setCellStyle(styleC22);
                    HSSFCell cell23 = row2.createCell(2);
                    if(!"".equalsIgnoreCase(dataheader.getHeaderinvdateto())){
                        cell23.setCellValue(" to " +dataheader.getHeaderinvdateto());
                        cell23.setCellStyle(styleC22);
                    }
                    HSSFCell cell24 = row2.createCell(3);
                    cell24.setCellValue("Print On : ");
                    cell24.setCellStyle(styleC21);
                    HSSFCell cell25 = row2.createCell(4);
                    cell25.setCellValue(dataheader.getHeaderprinton());
                    cell25.setCellStyle(styleC22);

                    // Row 3
                    HSSFRow row3 = sheetIncSum.createRow(2);
                    HSSFCell cell31 = row3.createCell(0);
                    cell31.setCellValue("Issue Date : ");
                    cell31.setCellStyle(styleC21);
                    HSSFCell cell32 = row3.createCell(1);
                    cell32.setCellValue(dataheader.getHeaderissuedatefrom());
                    cell32.setCellStyle(styleC22);
                    HSSFCell cell33 = row3.createCell(2);
                    if(!"".equalsIgnoreCase(dataheader.getHeaderissuedateto())){
                        cell33.setCellValue(" to " +dataheader.getHeaderissuedateto());
                        cell33.setCellStyle(styleC22);
                    }
                    HSSFCell cell34 = row3.createCell(3);
                    cell34.setCellValue("Page : ");
                    cell34.setCellStyle(styleC21);
                    HSSFCell cell35 = row3.createCell(4);
                    cell35.setCellValue("1");
                    cell35.setCellStyle(styleC22);
                    
                    // Row 4
                    HSSFRow row4 = sheetIncSum.createRow(3);
                    HSSFCell cell41 = row4.createCell(0);
                    cell41.setCellValue("Department : "); 
                    cell41.setCellStyle(styleC21);
                    HSSFCell cell42 = row4.createCell(1);
                    cell42.setCellValue(dataheader.getHeaderdepartment());
                    cell42.setCellStyle(styleC22);

                    // Row 5
                    HSSFRow row5 = sheetIncSum.createRow(4);
                    HSSFCell cell51 = row5.createCell(0);
                    cell51.setCellValue("Sale Staff : ");
                    cell51.setCellStyle(styleC21);
                    HSSFCell cell52 = row5.createCell(1);
                    cell52.setCellValue(dataheader.getHeadersalestaff());
                    cell52.setCellStyle(styleC22);

                    // Row 6
                    HSSFRow row6 = sheetIncSum.createRow(5);
                    HSSFCell cell61 = row6.createCell(0);
                    cell61.setCellValue("Term Pay : ");
                    cell61.setCellStyle(styleC21);
                    HSSFCell cell62 = row6.createCell(1);
                    cell62.setCellValue(dataheader.getHeadertermpay());
                    cell62.setCellStyle(styleC22);
                }
            }
            // Header Table
            HSSFCellStyle styleC3 = wb.createCellStyle();
            styleC3.setFont(getHeaderTable(wb.createFont()));
            styleC3.setAlignment(styleC3.ALIGN_CENTER);
            styleC3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            styleC3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            styleC3.setBorderRight(HSSFCellStyle.BORDER_THIN);
            styleC3.setBorderTop(HSSFCellStyle.BORDER_THIN);
            
            int count = 8;
            int tempcount = 0 ;
            int x = 0;
            for(int i=0;i<sumCostIncome.size();i++){
                SummaryTicketCostAndIncomeView data = (SummaryTicketCostAndIncomeView)sumCostIncome.get(i);
                if("income".equalsIgnoreCase(data.getPage())){
                    //inv
                    HSSFRow row8 = sheetInc.createRow(7);
                    HSSFCell cell81 = row8.createCell(0);
                    cell81.setCellValue("Type Pay");
                    cell81.setCellStyle(styleC3);
                    HSSFCell cell82 = row8.createCell(1);
                    cell82.setCellValue("Type Route");
                    cell82.setCellStyle(styleC3);
                    HSSFCell cell83 = row8.createCell(2);
                    cell83.setCellValue("Pax");
                    cell83.setCellStyle(styleC3);
                    HSSFCell cell84 = row8.createCell(3);
                    cell84.setCellValue("Air");
                    cell84.setCellStyle(styleC3);
                    HSSFCell cell85 = row8.createCell(4);
                    cell85.setCellValue("Ticket Issue");
                    cell85.setCellStyle(styleC3);
                    HSSFCell cell86 = row8.createCell(5);
                    cell86.setCellValue("Inv. No");
                    cell86.setCellStyle(styleC3);
                    HSSFCell cell87 = row8.createCell(6);
                    cell87.setCellValue("Cost");
                    cell87.setCellStyle(styleC3);
                    HSSFCell cell88 = row8.createCell(7);
                    cell88.setCellValue("Inbound");
                    cell88.setCellStyle(styleC3);
                    HSSFCell cell89 = row8.createCell(8);
                    cell89.setCellValue("Wendy(N)");
                    cell89.setCellStyle(styleC3);
                    HSSFCell cell90 = row8.createCell(9);
                    cell90.setCellValue("Refund");
                    cell90.setCellStyle(styleC3);
                    HSSFCell cell91 = row8.createCell(10);
                    cell91.setCellValue("Business Trip");
                    cell91.setCellStyle(styleC3);
                    HSSFCell cell92 = row8.createCell(11);
                    cell92.setCellValue("Annual Leave");
                    cell92.setCellStyle(styleC3);
                    HSSFCell cell93 = row8.createCell(12);
                    cell93.setCellValue("No Inv");
                    cell93.setCellStyle(styleC3);
                    HSSFCell cell94 = row8.createCell(13);
                    cell94.setCellValue("Cost Inv");
                    cell94.setCellStyle(styleC3);
                    HSSFCell cell95 = row8.createCell(14);
                    cell95.setCellValue("Inv Wendy");
                    cell95.setCellStyle(styleC3);
                    HSSFCell cell96 = row8.createCell(15);
                    cell96.setCellValue("Inv Outbound");
                    cell96.setCellStyle(styleC3);
                    HSSFCell cell97 = row8.createCell(16);
                    cell97.setCellValue("Inv Refund");
                    cell97.setCellStyle(styleC3);
                    HSSFCell cell98 = row8.createCell(17);
                    cell98.setCellValue("Inv Business Trip");
                    cell98.setCellStyle(styleC3);
                    HSSFCell cell99 = row8.createCell(18);
                    cell99.setCellValue("Inv Annual Leave");
                    cell99.setCellStyle(styleC3);
                    HSSFCell cell100 = row8.createCell(19);
                    cell100.setCellValue("Inv No Inv");
                    cell100.setCellStyle(styleC3);

                    HSSFRow row = sheetInc.createRow(count + i);
                    
                    HSSFCell celldata0 = row.createCell(0);
                    celldata0.setCellValue(String.valueOf(data.getTypepayment()));
                    celldata0.setCellStyle(styleC29);

                    HSSFCell celldata1 = row.createCell(1);
                    celldata1.setCellValue(String.valueOf(data.getTyperounting()));
                    celldata1.setCellStyle(styleC30);

                    HSSFCell celldata2 = row.createCell(2);
                    celldata2.setCellValue("".equalsIgnoreCase(String.valueOf(data.getPax())) ? 0 : (new BigDecimal(data.getPax())).doubleValue());
                    celldata2.setCellStyle(styleC26);

                    HSSFCell celldata3 = row.createCell(3);
                    celldata3.setCellValue(String.valueOf(data.getAir()));
                    celldata3.setCellStyle(styleC26);

                    HSSFCell celldata4 = row.createCell(4);
                    celldata4.setCellValue("".equalsIgnoreCase(String.valueOf(data.getTicketissue())) ? 0 : (new BigDecimal(data.getTicketissue())).doubleValue());
                    celldata4.setCellStyle(styleC25);

                    HSSFCell celldata5 = row.createCell(5);
                    celldata5.setCellValue(String.valueOf(data.getInvno()));
                    celldata5.setCellStyle(styleC29);

                    HSSFCell celldata6 = row.createCell(6);
                    celldata6.setCellValue("".equalsIgnoreCase(String.valueOf(data.getCost())) ? 0 : (new BigDecimal(data.getCost())).doubleValue());
                    celldata6.setCellStyle(styleC25);

                    HSSFCell celldata7 = row.createCell(7);
                    celldata7.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInbound())) ? 0 : (new BigDecimal(data.getInbound())).doubleValue());
                    celldata7.setCellStyle(styleC25);

                    HSSFCell celldata8 = row.createCell(8);
                    celldata8.setCellValue("".equalsIgnoreCase(String.valueOf(data.getWendy())) ? 0 : (new BigDecimal(data.getWendy())).doubleValue());
                    celldata8.setCellStyle(styleC25);

                    HSSFCell celldata9 = row.createCell(9);
                    celldata9.setCellValue("".equalsIgnoreCase(String.valueOf(data.getRefund())) ? 0 : (new BigDecimal(data.getRefund())).doubleValue());
                    celldata9.setCellStyle(styleC25);

                    HSSFCell celldata10 = row.createCell(10);
                    celldata10.setCellValue("".equalsIgnoreCase(String.valueOf(data.getBusinesstrip())) ? 0 : (new BigDecimal(data.getBusinesstrip())).doubleValue());
                    celldata10.setCellStyle(styleC25);

                    HSSFCell celldata11 = row.createCell(11);
                    celldata11.setCellValue("".equalsIgnoreCase(String.valueOf(data.getAnnualleave())) ? 0 : (new BigDecimal(data.getAnnualleave())).doubleValue());
                    celldata11.setCellStyle(styleC25);

                    HSSFCell celldata12 = row.createCell(12);
                    celldata12.setCellValue("".equalsIgnoreCase(String.valueOf(data.getNoinvoice())) ? 0 : (new BigDecimal(data.getNoinvoice())).doubleValue());
                    celldata12.setCellStyle(styleC25);

                    HSSFCell celldata13 = row.createCell(13);
                    celldata13.setCellValue("".equalsIgnoreCase(String.valueOf(data.getCostinv())) ? 0 : (new BigDecimal(data.getCostinv())).doubleValue());
                    celldata13.setCellStyle(styleC25);
                    
                    HSSFCell celldata14 = row.createCell(14);
                    celldata14.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInvwendy())) ? 0 : (new BigDecimal(data.getInvwendy())).doubleValue());
                    celldata14.setCellStyle(styleC25);
                    
                    HSSFCell celldata15 = row.createCell(15);
                    celldata15.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInvoutbound())) ? 0 : (new BigDecimal(data.getInvoutbound())).doubleValue());
                    celldata15.setCellStyle(styleC25);
                    
                    HSSFCell celldata16 = row.createCell(16);
                    celldata16.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInvrefund())) ? 0 : (new BigDecimal(data.getInvrefund())).doubleValue());
                    celldata16.setCellStyle(styleC25);
                    
                    HSSFCell celldata17 = row.createCell(17);
                    celldata17.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInvbusinesstrip())) ? 0 : (new BigDecimal(data.getInvbusinesstrip())).doubleValue());
                    celldata17.setCellStyle(styleC25);
                    
                    HSSFCell celldata18 = row.createCell(18);
                    celldata18.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInvannualleave())) ? 0 : (new BigDecimal(data.getInvannualleave())).doubleValue());
                    celldata18.setCellStyle(styleC25);
                    
                    HSSFCell celldata19 = row.createCell(19);
                    celldata19.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInvnoinvoice())) ? 0 : (new BigDecimal(data.getInvnoinvoice())).doubleValue());
                    celldata19.setCellStyle(styleC25);
                    
                    tempcount = count + i + 1;
                }
                if("incomesum".equalsIgnoreCase(data.getPage())){
                    //Total inv
                    HSSFRow rowtotal = sheetInc.createRow(tempcount);
                    String totalpax = "SUM(C" + 9+":C"+(tempcount)+")";
                    String totalcost = "SUM(G" + 9+":G"+(tempcount)+")";
                    String totalinbound = "SUM(H" + 9+":H"+(tempcount)+")";
                    String totalwendy = "SUM(I" + 9+":I"+(tempcount)+")";
                    String totalrefund = "SUM(J" + 9+":J"+(tempcount)+")";
                    String totalbusinesstrip = "SUM(K" + 9+":K"+(tempcount)+")";
                    String totalannualleave = "SUM(L" + 9+":L"+(tempcount)+")";
                    String totalnoinv = "SUM(M" + 9+":M"+(tempcount)+")";
                    String totalcostinv = "SUM(N" + 9+":N"+(tempcount)+")";
                    String totalinvwendy = "SUM(O" + 9+":O"+(tempcount)+")";
                    String totalinvout = "SUM(P" + 9+":P"+(tempcount)+")";
                    String totalinvrefund = "SUM(Q" + 9+":Q"+(tempcount)+")";
                    String totalinvbusinesstrip = "SUM(R" + 9+":R"+(tempcount)+")";
                    String totalinvannualleave = "SUM(S" + 9+":S"+(tempcount)+")";
                    String totalinvnoinv = "SUM(T" + 9+":T"+(tempcount)+")";
                    
                    HSSFCellStyle styleTotal = wb.createCellStyle();
                    styleTotal.setFont(getHeaderTable(wb.createFont()));
                    styleTotal.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                    styleTotal.setBorderRight(HSSFCellStyle.BORDER_THIN);
                    styleTotal.setBorderTop(HSSFCellStyle.BORDER_THIN);
                    styleTotal.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                    styleTotal.setAlignment(styleC22.ALIGN_RIGHT);
                    
                    sheetInc.addMergedRegion(CellRangeAddress.valueOf("A"+(tempcount+1)+":B"+(tempcount+1)));
                    sheetInc.addMergedRegion(CellRangeAddress.valueOf("D"+(tempcount+1)+":F"+(tempcount+1)));
                    HSSFCell cellTotal00 = rowtotal.createCell(0);
                    cellTotal00.setCellValue("Total");
                    cellTotal00.setCellStyle(styleTotal);
                    HSSFCell cellTotal01 = rowtotal.createCell(2);
                    cellTotal01.setCellFormula(totalpax);
                    cellTotal01.setCellStyle(styleC26);
                    HSSFCell cellTotal02 = rowtotal.createCell(6);
                    cellTotal02.setCellFormula(totalcost);
                    cellTotal02.setCellStyle(styleC25);
                    HSSFCell cellTotal03 = rowtotal.createCell(7);
                    cellTotal03.setCellFormula(totalinbound);
                    cellTotal03.setCellStyle(styleC25);
                    HSSFCell cellTotal04 = rowtotal.createCell(8);
                    cellTotal04.setCellFormula(totalwendy);
                    cellTotal04.setCellStyle(styleC25);
                    HSSFCell cellTotal05 = rowtotal.createCell(9);
                    cellTotal05.setCellFormula(totalrefund);
                    cellTotal05.setCellStyle(styleC25);
                    HSSFCell cellTotal06 = rowtotal.createCell(10);
                    cellTotal06.setCellFormula(totalbusinesstrip);
                    cellTotal06.setCellStyle(styleC25);
                    HSSFCell cellTotal07 = rowtotal.createCell(11);
                    cellTotal07.setCellFormula(totalannualleave);
                    cellTotal07.setCellStyle(styleC25);
                    HSSFCell cellTotal08 = rowtotal.createCell(12);
                    cellTotal08.setCellFormula(totalnoinv);
                    cellTotal08.setCellStyle(styleC25);
                    HSSFCell cellTotal09 = rowtotal.createCell(13);
                    cellTotal09.setCellFormula(totalcostinv);
                    cellTotal09.setCellStyle(styleC25);
                    HSSFCell cellTotal10 = rowtotal.createCell(14);
                    cellTotal10.setCellFormula(totalinvwendy);
                    cellTotal10.setCellStyle(styleC25);
                    HSSFCell cellTotal11 = rowtotal.createCell(15);
                    cellTotal11.setCellFormula(totalinvout);
                    cellTotal11.setCellStyle(styleC25);
                    HSSFCell cellTotal12 = rowtotal.createCell(16);
                    cellTotal12.setCellFormula(totalinvrefund);
                    cellTotal12.setCellStyle(styleC25);
                    HSSFCell cellTotal13 = rowtotal.createCell(17);
                    cellTotal13.setCellFormula(totalinvbusinesstrip);
                    cellTotal13.setCellStyle(styleC25);
                    HSSFCell cellTotal14 = rowtotal.createCell(18);
                    cellTotal14.setCellFormula(totalinvannualleave);
                    cellTotal14.setCellStyle(styleC25);
                    HSSFCell cellTotal15 = rowtotal.createCell(19);
                    cellTotal15.setCellFormula(totalinvnoinv);
                    cellTotal15.setCellStyle(styleC25);
                    HSSFCell cellTotal16 = rowtotal.createCell(1);
                    cellTotal16.setCellStyle(styleTotal);
                    HSSFCell cellTotal17 = rowtotal.createCell(3);
                    cellTotal17.setCellStyle(styleTotal);
                    HSSFCell cellTotal18 = rowtotal.createCell(4);
                    cellTotal18.setCellStyle(styleTotal);
                    HSSFCell cellTotal19 = rowtotal.createCell(5);
                    cellTotal19.setCellStyle(styleTotal);
//                    if(tempcount != 0){
//                        rowdetail = tempcount+3;
//                    }
                    HSSFRow row8 = sheetIncSum.createRow(7);
                    HSSFCell cell81 = row8.createCell(0);
                    cell81.setCellValue("Type Pay");
                    cell81.setCellStyle(styleC3);
                    HSSFCell cell82 = row8.createCell(1);
                    cell82.setCellValue("Type Route");
                    cell82.setCellStyle(styleC3);
                    HSSFCell cell83 = row8.createCell(2);
                    cell83.setCellValue("Pax");
                    cell83.setCellStyle(styleC3);
                    HSSFCell cell84 = row8.createCell(3);
                    cell84.setCellValue("Ticket Issue");
                    cell84.setCellStyle(styleC3);
                    HSSFCell cell85 = row8.createCell(4);
                    cell85.setCellValue("Inv. No");
                    cell85.setCellStyle(styleC3);
                    HSSFCell cell86 = row8.createCell(5);
                    cell86.setCellValue("Cost");
                    cell86.setCellStyle(styleC3);
                    HSSFCell cell87 = row8.createCell(6);
                    cell87.setCellValue("Inbound");
                    cell87.setCellStyle(styleC3);
                    HSSFCell cell88 = row8.createCell(7);
                    cell88.setCellValue("Wendy(N)");
                    cell88.setCellStyle(styleC3);
                    HSSFCell cell89 = row8.createCell(8);
                    cell89.setCellValue("Refund");
                    cell89.setCellStyle(styleC3);
                    HSSFCell cell91 = row8.createCell(9);
                    cell91.setCellValue("Business Trip");
                    cell91.setCellStyle(styleC3);
                    HSSFCell cell92 = row8.createCell(10);
                    cell92.setCellValue("Annual Leave");
                    cell92.setCellStyle(styleC3);
                    HSSFCell cell93 = row8.createCell(11);
                    cell93.setCellValue("No Inv");
                    cell93.setCellStyle(styleC3);
                    HSSFCell cell94 = row8.createCell(12);
                    cell94.setCellValue("Cost Inv");
                    cell94.setCellStyle(styleC3);
                    HSSFCell cell95 = row8.createCell(13);
                    cell95.setCellValue("Inv Wendy");
                    cell95.setCellStyle(styleC3);
                    HSSFCell cell96 = row8.createCell(14);
                    cell96.setCellValue("Inv Outbound");
                    cell96.setCellStyle(styleC3);
                    HSSFCell cell97 = row8.createCell(15);
                    cell97.setCellValue("Inv Refund");
                    cell97.setCellStyle(styleC3);
                    HSSFCell cell98 = row8.createCell(16);
                    cell98.setCellValue("Inv Business Trip");
                    cell98.setCellStyle(styleC3);
                    HSSFCell cell99 = row8.createCell(17);
                    cell99.setCellValue("Inv Annual Leave");
                    cell99.setCellStyle(styleC3);
                    HSSFCell cell100 = row8.createCell(18);
                    cell100.setCellValue("Inv No Inv");
                    cell100.setCellStyle(styleC3);
                   
                    HSSFRow row = sheetIncSum.createRow(count + x);
                    
                    HSSFCell celldata0 = row.createCell(0);
                    celldata0.setCellValue(String.valueOf(data.getTypepaymentSum()));
                    celldata0.setCellStyle(styleC29);

                    HSSFCell celldata1 = row.createCell(1);
                    celldata1.setCellValue(String.valueOf(data.getTyperountingSum()));
                    celldata1.setCellStyle(styleC30);

                    HSSFCell celldata2 = row.createCell(2);
                    celldata2.setCellValue("".equalsIgnoreCase(String.valueOf(data.getPaxSum())) ? 0 : (new BigDecimal(data.getPaxSum())).doubleValue());
                    celldata2.setCellStyle(styleC26);

                    HSSFCell celldata3 = row.createCell(3);
                    celldata3.setCellValue("".equalsIgnoreCase(String.valueOf(data.getTicketissueSum())) ? 0 : (new BigDecimal(data.getTicketissueSum())).doubleValue());
                    celldata3.setCellStyle(styleC25);

                    HSSFCell celldata4 = row.createCell(4);
                    celldata4.setCellValue(String.valueOf(data.getInvnoSum()));
                    celldata4.setCellStyle(styleC29);

                    HSSFCell celldata5 = row.createCell(5);
                    celldata5.setCellValue("".equalsIgnoreCase(String.valueOf(data.getCostSum())) ? 0 : (new BigDecimal(data.getCostSum())).doubleValue());
                    celldata5.setCellStyle(styleC25);

                    HSSFCell celldata6 = row.createCell(6);
                    celldata6.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInboundSum())) ? 0 : (new BigDecimal(data.getInboundSum())).doubleValue());
                    celldata6.setCellStyle(styleC25);

                    HSSFCell celldata7 = row.createCell(7);
                    celldata7.setCellValue("".equalsIgnoreCase(String.valueOf(data.getWendySum())) ? 0 : (new BigDecimal(data.getWendySum())).doubleValue());
                    celldata7.setCellStyle(styleC25);

                    HSSFCell celldata8 = row.createCell(8);
                    celldata8.setCellValue("".equalsIgnoreCase(String.valueOf(data.getRefundSum())) ? 0 : (new BigDecimal(data.getRefundSum())).doubleValue());
                    celldata8.setCellStyle(styleC25);

                    HSSFCell celldata9 = row.createCell(9);
                    celldata9.setCellValue("".equalsIgnoreCase(String.valueOf(data.getBusinesstripSum())) ? 0 : (new BigDecimal(data.getBusinesstripSum())).doubleValue());
                    celldata9.setCellStyle(styleC25);

                    HSSFCell celldata10 = row.createCell(10);
                    celldata10.setCellValue("".equalsIgnoreCase(String.valueOf(data.getAnnualleaveSum())) ? 0 : (new BigDecimal(data.getAnnualleaveSum())).doubleValue());
                    celldata10.setCellStyle(styleC25);

                    HSSFCell celldata11 = row.createCell(11);
                    celldata11.setCellValue("".equalsIgnoreCase(String.valueOf(data.getNoinvoiceSum())) ? 0 : (new BigDecimal(data.getNoinvoiceSum())).doubleValue());
                    celldata11.setCellStyle(styleC25);

                    HSSFCell celldata12 = row.createCell(12);
                    celldata12.setCellValue("".equalsIgnoreCase(String.valueOf(data.getCostinvSum())) ? 0 : (new BigDecimal(data.getCostinvSum())).doubleValue());
                    celldata12.setCellStyle(styleC25);
                    
                    HSSFCell celldata13 = row.createCell(13);
                    celldata13.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInvwendySum())) ? 0 : (new BigDecimal(data.getInvwendySum())).doubleValue());
                    celldata13.setCellStyle(styleC25);
                    
                    HSSFCell celldata14 = row.createCell(14);
                    celldata14.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInvoutboundSum())) ? 0 : (new BigDecimal(data.getInvoutboundSum())).doubleValue());
                    celldata14.setCellStyle(styleC25);
                    
                    HSSFCell celldata15 = row.createCell(15);
                    celldata15.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInvrefundSum())) ? 0 : (new BigDecimal(data.getInvrefundSum())).doubleValue());
                    celldata15.setCellStyle(styleC25);
                    
                    HSSFCell celldata16 = row.createCell(16);
                    celldata16.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInvbusinesstripSum())) ? 0 : (new BigDecimal(data.getInvbusinesstripSum())).doubleValue());
                    celldata16.setCellStyle(styleC25);
                    
                    HSSFCell celldata17 = row.createCell(17);
                    celldata17.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInvannualleaveSum())) ? 0 : (new BigDecimal(data.getInvannualleaveSum())).doubleValue());
                    celldata17.setCellStyle(styleC25);
                    
                    HSSFCell celldata18 = row.createCell(18);
                    celldata18.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInvnoinvoiceSum())) ? 0 : (new BigDecimal(data.getInvnoinvoiceSum())).doubleValue());
                    celldata18.setCellStyle(styleC25);
//                    tempcount2 = count + i + 4;
                     // set total last row
                    if(i == (sumCostIncome.size()-1)){
                        HSSFRow rows = sheetIncSum.createRow(count + x + 1);
                        //Total inv
                        String sumtotalpax = "SUM(C" + 9+":C"+(count + x + 1)+")";
                        String sumtotalcost = "SUM(F" + 9+":F"+(count + x + 1)+")";
                        String sumtotalinbound = "SUM(G" + 9+":G"+(count + x + 1)+")";
                        String sumtotalwendy = "SUM(H" + 9+":H"+(count + x + 1)+")";
                        String sumtotalrefund = "SUM(I" + 9+":I"+(count + x + 1)+")";
                        String sumtotalbusinesstrip = "SUM(J" + 9+":J"+(count + x + 1)+")";
                        String sumtotalannualleave = "SUM(K" + 9+":K"+(count + x + 1)+")";
                        String sumtotalnoinv = "SUM(L" + 9+":L"+(count + x + 1)+")";
                        String sumtotalcostinv = "SUM(M" + 9+":M"+(count + x + 1)+")";
                        String sumtotalinvwendy = "SUM(N" + 9+":N"+(count + x + 1)+")";
                        String sumtotalinvout = "SUM(O" + 9+":O"+(count + x + 1)+")";
                        String sumtotalinvrefund = "SUM(P" + 9+":P"+(count + x + 1)+")";
                        String sumtotalinvbusinesstrip = "SUM(Q" + 9+":Q"+(count + x + 1)+")";
                        String sumtotalinvannualleave = "SUM(R" + 9+":R"+(count + x + 1)+")";
                        String sumtotalinvnoinv = "SUM(S" + 9+":S"+(count + x + 1)+")";
                        sheetIncSum.addMergedRegion(CellRangeAddress.valueOf("A"+(count + x + 2)+":B"+(count + x + 2)));
                        sheetIncSum.addMergedRegion(CellRangeAddress.valueOf("D"+(count + x + 2)+":E"+(count + x + 2)));
                        HSSFCell cellTotalSum00 = rows.createCell(0);
                        cellTotalSum00.setCellValue("Total");
                        cellTotalSum00.setCellStyle(styleTotal);
                        HSSFCell cellTotalSum01 = rows.createCell(2);
                        cellTotalSum01.setCellFormula(sumtotalpax);
                        cellTotalSum01.setCellStyle(styleC26);
                        HSSFCell cellTotalSum02 = rows.createCell(5);
                        cellTotalSum02.setCellFormula(sumtotalcost);
                        cellTotalSum02.setCellStyle(styleC25);
                        HSSFCell cellTotalSum03 = rows.createCell(6);
                        cellTotalSum03.setCellFormula(sumtotalinbound);
                        cellTotalSum03.setCellStyle(styleC25);
                        HSSFCell cellTotalSum04 = rows.createCell(7);
                        cellTotalSum04.setCellFormula(sumtotalwendy);
                        cellTotalSum04.setCellStyle(styleC25);
                        HSSFCell cellTotalSum05 = rows.createCell(8);
                        cellTotalSum05.setCellFormula(sumtotalrefund);
                        cellTotalSum05.setCellStyle(styleC25);
                        HSSFCell cellTotalSum06 = rows.createCell(9);
                        cellTotalSum06.setCellFormula(sumtotalbusinesstrip);
                        cellTotalSum06.setCellStyle(styleC25);
                        HSSFCell cellTotalSum07 = rows.createCell(10);
                        cellTotalSum07.setCellFormula(sumtotalannualleave);
                        cellTotalSum07.setCellStyle(styleC25);
                        HSSFCell cellTotalSum08 = rows.createCell(11);
                        cellTotalSum08.setCellFormula(sumtotalnoinv);
                        cellTotalSum08.setCellStyle(styleC25);
                        HSSFCell cellTotalSum09 = rows.createCell(12);
                        cellTotalSum09.setCellFormula(sumtotalcostinv);
                        cellTotalSum09.setCellStyle(styleC25);
                        HSSFCell cellTotalSum10 = rows.createCell(13);
                        cellTotalSum10.setCellFormula(sumtotalinvwendy);
                        cellTotalSum10.setCellStyle(styleC25);
                        HSSFCell cellTotalSum11 = rows.createCell(14);
                        cellTotalSum11.setCellFormula(sumtotalinvout);
                        cellTotalSum11.setCellStyle(styleC25);
                        HSSFCell cellTotalSum12 = rows.createCell(15);
                        cellTotalSum12.setCellFormula(sumtotalinvrefund);
                        cellTotalSum12.setCellStyle(styleC25);
                        HSSFCell cellTotalSum13 = rows.createCell(16);
                        cellTotalSum13.setCellFormula(sumtotalinvbusinesstrip);
                        cellTotalSum13.setCellStyle(styleC25);
                        HSSFCell cellTotalSum14 = rows.createCell(17);
                        cellTotalSum14.setCellFormula(sumtotalinvannualleave);
                        cellTotalSum14.setCellStyle(styleC25);
                        HSSFCell cellTotalSum15 = rows.createCell(18);
                        cellTotalSum15.setCellFormula(sumtotalinvnoinv);
                        cellTotalSum15.setCellStyle(styleC25);
                        HSSFCell cellTotalSum16 = rows.createCell(1);
                        cellTotalSum16.setCellStyle(styleTotal);
                        HSSFCell cellTotalSum17 = rows.createCell(3);
                        cellTotalSum17.setCellStyle(styleTotal);
                        HSSFCell cellTotalSum18 = rows.createCell(4);
                        cellTotalSum18.setCellStyle(styleTotal);
//                        HSSFCell cellTotalSum19 = rows.createCell(5);
//                        cellTotalSum19.setCellStyle(styleTotal);
                    }
                    x ++ ;
            }
            for (int j = 0;j< 30;j++) {
                sheetInc.autoSizeColumn(j);
                sheetIncSum.autoSizeColumn(j);
            }
        }
    }
}
     private void getTicketSummaryCommission(HSSFWorkbook wb, List listTicketummaryCommission) {
        String sheetName = "Ticket_commission_detail_summary";// name of sheet
        String sheetName1 = "Ticket_commission_air_summary";
        String sheetName2 = "Ticket_commission_agent_summary";
        HSSFSheet sheet = wb.createSheet(sheetName);
        HSSFSheet sheet1 = wb.createSheet(sheetName1);
        HSSFSheet sheet2 = wb.createSheet(sheetName2);


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
        styleAlignRightBorderAllHeaderTable.setFont(getHeaderTable(wb .createFont()));
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
        styleHeader01.setFont(getHeaderFont(wb.createFont()));
        cell01.setCellStyle(styleHeader01);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:M1"));
        
        List<ListTicketSummaryCommission> listSummaryCommission = listTicketummaryCommission;
        
        List<TicketSummaryCommissionView> listDetail = listSummaryCommission.get(0).getTicketCommissionDetailSummary();
        List<TicketSummaryCommissionView> listAir = listSummaryCommission.get(0).getTicketCommissionAirSummary();
        List<TicketSummaryCommissionView> listAgent = listSummaryCommission.get(0).getTicketCommissionAgentSummary();
        TicketSummaryCommissionView ticketDetail = listDetail.get(0);
        TicketSummaryCommissionView ticketAir = listAir.get(0);
        TicketSummaryCommissionView ticketAgent = listAgent.get(0);
        // Row 2
        HSSFRow row02 = sheet.createRow(1);
        HSSFCell cell021 = row02.createCell(0);
                cell021.setCellValue("Agent Name : ");
                cell021.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(0);
        HSSFCell cell022 = row02.createCell(1);
                cell022.setCellValue(ticketDetail.getAgentNamePage());
                cell022.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(1);
        HSSFCell cell023 = row02.createCell(2);
                cell023.setCellValue("Issue date : ");
                cell023.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(2);
        HSSFCell cell024 = row02.createCell(3);
                cell024.setCellValue(ticketDetail.getIssuefromdatePage());
                cell024.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(3);
        HSSFCell cell025 = row02.createCell(4);
                cell025.setCellValue("Print By : ");
                cell025.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(4);
        HSSFCell cell026 = row02.createCell(5);
                cell026.setCellValue(ticketDetail.getPrintbyPage());
                cell026.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(5);

        // Row 3
        HSSFRow row03 = sheet.createRow(2);
        HSSFCell cell031 = row03.createCell(0);
                cell031.setCellValue("Type Routing : ");
                cell031.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(0);
        HSSFCell cell032 = row03.createCell(1);
                cell032.setCellValue(ticketDetail.getTypeRoutingPage());
                cell032.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(1);
        HSSFCell cell033 = row03.createCell(2);
                cell033.setCellValue("Over Comm Date : ");
                cell033.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(2);
        HSSFCell cell034 = row03.createCell(3);
                cell034.setCellValue(ticketDetail.getOverfromdatePage());
                cell034.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(3);
        HSSFCell cell035 = row03.createCell(4);
                cell035.setCellValue("Add Pay Date : ");
                cell035.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(4);
        HSSFCell cell036 = row03.createCell(5);
                cell036.setCellValue(ticketDetail.getAddpayfromdatePage());
                cell036.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(5);

        // Row 4
        HSSFRow row04 = sheet.createRow(3);
        HSSFCell cell041 = row04.createCell(0);
                cell041.setCellValue("Air : ");
                cell041.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(0);
        HSSFCell cell042 = row04.createCell(1);
                cell042.setCellValue(ticketDetail.getAirlineCodePage());
                cell042.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(1);
        HSSFCell cell043 = row04.createCell(2);
                cell043.setCellValue("Little Comm date : ");
                cell043.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(2);
        HSSFCell cell044 = row04.createCell(3);
                cell044.setCellValue(ticketDetail.getLittlefromdatePage());
                cell044.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(3);
        HSSFCell cell045 = row04.createCell(4);
                cell045.setCellValue("Decrease Pay Date : ");
                cell045.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(4);
        HSSFCell cell046 = row04.createCell(5);
                cell046.setCellValue(ticketDetail.getDecreasepayfromdatePage());
                cell046.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(5);

        // Row 5
        HSSFRow row05 = sheet.createRow(4);
        HSSFCell cell051 = row05.createCell(0);
                cell051.setCellValue("Routing Detail : ");
                cell051.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(0);
        HSSFCell cell052 = row05.createCell(1);
                cell052.setCellValue(ticketDetail.getRoutingDetailPage());
                cell052.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(1);
        HSSFCell cell053 = row05.createCell(2);
                cell053.setCellValue("Agent Comm Rev Date : ");
                cell053.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(2);
        HSSFCell cell054 = row05.createCell(3);
                cell054.setCellValue(ticketDetail.getAgemtcomreceivefromdatePage());
                cell054.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(3);
        HSSFCell cell055 = row05.createCell(4);
                cell055.setCellValue("Ticket No : ");
                cell055.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(4);
        HSSFCell cell056 = row05.createCell(5);
                cell056.setCellValue(ticketDetail.getTicketnoPagePage());
                cell056.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(5);

        // Row 6
        HSSFRow row06 = sheet.createRow(5);
        HSSFCell cell061 = row06.createCell(0);
                cell061.setCellValue("Sale Staff : ");
                cell061.setCellStyle(styleAlignRight);
        HSSFCell cell062 = row06.createCell(1);
                cell062.setCellValue(ticketDetail.getSalebyNamePage());
                cell062.setCellStyle(styleAlignLeft);
        HSSFCell cell063 = row06.createCell(2);
                cell063.setCellValue("Refund Comm Date : ");
                cell063.setCellStyle(styleAlignRight);
        HSSFCell cell064 = row06.createCell(3);
                cell064.setCellValue(ticketDetail.getComrefundfromdatePage());
                cell064.setCellStyle(styleAlignLeft);
        HSSFCell cell065 = row06.createCell(4);
                cell065.setCellValue("Ticket Comm Date : ");
                cell065.setCellStyle(styleAlignRight);
        HSSFCell cell066 = row06.createCell(5);
                cell066.setCellValue(ticketDetail.getTicketcomfromdatePage());
                cell066.setCellStyle(styleAlignLeft);

        // Row 7
        HSSFRow row07 = sheet.createRow(6);
        HSSFCell cell071 = row07.createCell(0);
                cell071.setCellValue("Department : ");
                cell071.setCellStyle(styleAlignRight);
        HSSFCell cell072 = row07.createCell(1);
                cell072.setCellValue(ticketDetail.getDepartmentPage());
                cell072.setCellStyle(styleAlignLeft);
        HSSFCell cell073 = row07.createCell(2);
                cell073.setCellValue("Invoice Date : ");
                cell073.setCellStyle(styleAlignRight);
        HSSFCell cell074 = row07.createCell(3);
                cell074.setCellValue(ticketDetail.getInvoicefromdatePage());
                cell074.setCellStyle(styleAlignLeft);
        HSSFCell cell075 = row07.createCell(4);
                cell075.setCellValue("Print on : ");
                cell075.setCellStyle(styleAlignRight);
        HSSFCell cell076 = row07.createCell(5);
                cell076.setCellValue(ticketDetail.getPrintonPage());
                cell076.setCellStyle(styleAlignLeft);

        // Row 8
        HSSFRow row08 = sheet.createRow(7);
        HSSFCell cell081 = row08.createCell(0);
                cell081.setCellValue("Term Pay : ");
                cell081.setCellStyle(styleAlignRight);
        HSSFCell cell082 = row08.createCell(1);
                cell082.setCellValue(ticketDetail.getTermPayPage());
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
        cell0106.setCellValue("Sale");	
        cell0106.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(15);

        HSSFCell cell0107 = row09.createCell(16);
        cell0107.setCellValue("Cost");
        cell0107.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(16);

        HSSFCell cell0108 = row09.createCell(17);
        cell0108.setCellValue("Over");
        cell0108.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(17);

        HSSFCell cell0109 = row09.createCell(18);
        cell0109.setCellValue("Add");
        cell0109.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(18);

        HSSFCell cell0110 = row09.createCell(19);
        cell0110.setCellValue("Dres");
        cell0110.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(19);

        HSSFCell cell0111 = row09.createCell(20);
        cell0111.setCellValue("Profit");
        cell0111.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(20);
        
        HSSFCell cell0116 = row09.createCell(21);
        cell0116.setCellValue("Comm");
        cell0116.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(21);

        HSSFCell cell0112 = row09.createCell(22);
        cell0112.setCellValue("Little");
        cell0112.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(22);

        HSSFCell cell0113 = row09.createCell(23);
        cell0113.setCellValue("Agent[Comm] Pay");
        cell0113.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(23);

        HSSFCell cell0114 = row09.createCell(24);
        cell0114.setCellValue("Agent[Comm] Receive");
        cell0114.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(24);

        HSSFCell cell0115 = row09.createCell(25);
        cell0115.setCellValue("Pay");
        cell0115.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(25);
       
        HSSFCell cell0117 = row09.createCell(26);
        cell0117.setCellValue("Comm");
        cell0117.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(25);
        
        int count = 10 + listDetail.size();
        System.out.println(" listDetail.size() " + listDetail.size());
        System.out.println(" count " + count);
        if(listDetail != null){
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
                    cell3.setCellStyle(styleDetailTableNumber);
                HSSFCell cell4 = row.createCell(3);
                    cell4.setCellValue(listDetail.get(r-10).getOwner());
                    cell4.setCellStyle(styleDetailTable);
                HSSFCell cell55 = row.createCell(4);
                    cell55.setCellValue(listDetail.get(r-10).getTermpay());
                    cell55.setCellStyle(styleDetailTable);
                HSSFCell cell5 = row.createCell(5);
                    cell5.setCellValue(listDetail.get(r-10).getAgent());
                    cell5.setCellStyle(styleDetailTableNumber);
                HSSFCell cell6 = row.createCell(6);
                    cell6.setCellValue(listDetail.get(r-10).getType());
                    cell6.setCellStyle(styleDetailTableNumber);
                HSSFCell cell7 = row.createCell(7);
                    cell7.setCellValue(listDetail.get(r-10).getBuy());
                    cell7.setCellStyle(styleDetailTableNumber);
                HSSFCell cell8 = row.createCell(8);
                    cell8.setCellValue(listDetail.get(r-10).getPax());
                    cell8.setCellStyle(stylePaxNumber);
                HSSFCell cell9 = row.createCell(9);
                    cell9.setCellValue(listDetail.get(r-10).getAir());
                    cell9.setCellStyle(stylePaxNumber);
                HSSFCell cell10 = row.createCell(10);
                        cell10.setCellValue(listDetail.get(r-10).getDocno());
                        cell10.setCellStyle(styleDetailTableNumber);
               HSSFCell cell11 = row.createCell(11);
                       cell11.setCellValue(listDetail.get(r-10).getRefno());
                       cell11.setCellStyle(styleDetailTableNumber);
               HSSFCell cell12 = row.createCell(12);
                       cell12.setCellValue(listDetail.get(r-10).getIssuedate());
                       cell12.setCellStyle(styleDetailTableNumber);
               HSSFCell cell13 = row.createCell(13);
                       BigDecimal amountwendy = new BigDecimal("".equals(listDetail.get(r-10).getAmountwendy()) ? "0" : listDetail.get(r-10).getAmountwendy());
                       cell13.setCellValue((amountwendy != null) ? amountwendy.doubleValue() : new BigDecimal("0").doubleValue());
                       cell13.setCellStyle(styleDetailTableNumber);
               HSSFCell cell14 = row.createCell(14);
                       BigDecimal amountoutbound = new BigDecimal("".equals(listDetail.get(r-10).getAmountoutbound()) ? "0" : listDetail.get(r-10).getAmountoutbound());
                       cell14.setCellValue((amountoutbound != null) ? amountoutbound.doubleValue() : new BigDecimal("0").doubleValue());
                       cell14.setCellStyle(styleDetailTableNumber);
               HSSFCell cell15 = row.createCell(15);
                       BigDecimal sale = new BigDecimal("".equals(listDetail.get(r-10).getSale()) ? "0" : listDetail.get(r-10).getSale());
                       cell15.setCellValue((sale != null) ? sale.doubleValue() : new BigDecimal("0").doubleValue());
                       cell15.setCellStyle(styleDetailTableNumber);
               HSSFCell cell16 = row.createCell(16);
                       BigDecimal cost  = new BigDecimal("".equals(listDetail.get(r-10).getCost()) ? "0" : listDetail.get(r-10).getCost());
                       cell16.setCellValue((cost != null) ? cost.doubleValue() : new BigDecimal("0").doubleValue());
                       cell16.setCellStyle(styleDetailTableNumber);
               HSSFCell cell17 = row.createCell(17);
                       BigDecimal over  = new BigDecimal("".equals(listDetail.get(r-10).getOver()) ? "0" : listDetail.get(r-10).getOver());
                       cell17.setCellValue((over != null) ? over.doubleValue() : new BigDecimal("0").doubleValue());
                       cell17.setCellStyle(styleDetailTableNumber);
               HSSFCell cell18 = row.createCell(18);
                       BigDecimal add  = new BigDecimal("".equals(listDetail.get(r-10).getAdd()) ? "0" : listDetail.get(r-10).getAdd());
                       cell18.setCellValue((add != null) ? add.doubleValue() : new BigDecimal("0").doubleValue());
                       cell18.setCellStyle(styleDetailTableNumber);
               HSSFCell cell19 = row.createCell(19);
                       BigDecimal dres  = new BigDecimal("".equals(listDetail.get(r-10).getDres()) ? "0" : listDetail.get(r-10).getDres());
                       cell19.setCellValue((dres != null) ? dres.doubleValue() : new BigDecimal("0").doubleValue());
                       cell19.setCellStyle(styleDetailTableNumber);
               HSSFCell cell20 = row.createCell(20);
                       BigDecimal profit  = new BigDecimal("".equals(listDetail.get(r-10).getProfit()) ? "0" : listDetail.get(r-10).getProfit());
                       cell20.setCellValue((profit != null) ? profit.doubleValue() : new BigDecimal("0").doubleValue());
                       cell20.setCellStyle(styleDetailTableNumber);
               HSSFCell cell25 = row.createCell(21);
                       BigDecimal ticcom  = new BigDecimal("".equals(listDetail.get(r-10).getTiccomm()) ? "0" : listDetail.get(r-10).getTiccomm());
                       cell25.setCellValue((ticcom != null) ? ticcom.doubleValue() : new BigDecimal("0").doubleValue());
                       cell25.setCellStyle(styleDetailTableNumber);
               HSSFCell cell21 = row.createCell(22);
                       BigDecimal little  = new BigDecimal("".equals(listDetail.get(r-10).getLittle()) ? "0" : listDetail.get(r-10).getLittle());
                       cell21.setCellValue((little != null) ? little.doubleValue() : new BigDecimal("0").doubleValue());
                       cell21.setCellStyle(styleDetailTableNumber);
               HSSFCell cell22 = row.createCell(23);
                        BigDecimal agentcommpay = new BigDecimal("".equals(listDetail.get(r-10).getAgentcommpay()) ? "0" : listDetail.get(r-10).getAgentcommpay());
                        cell22.setCellValue((agentcommpay != null) ? agentcommpay.doubleValue() : new BigDecimal("0").doubleValue());
                        cell22.setCellStyle(styleDetailTableNumber);
                HSSFCell cell23 = row.createCell(24);
                        BigDecimal agentcommrec = new BigDecimal("".equals(listDetail.get(r-10).getAgentcommrec()) ? "0" : listDetail.get(r-10).getAgentcommrec());
                        cell23.setCellValue((agentcommrec != null) ? agentcommrec.doubleValue() : new BigDecimal("0").doubleValue());
                        cell23.setCellStyle(styleDetailTableNumber);        
               HSSFCell cell24 = row.createCell(25);
                        BigDecimal pay = new BigDecimal("".equals(listDetail.get(r-10).getPay()) ? "0" : listDetail.get(r-10).getPay());
                        cell24.setCellValue((pay != null) ? pay.doubleValue() : new BigDecimal("0").doubleValue());
                        cell24.setCellStyle(styleDetailTableNumber);
               HSSFCell cell26 = row.createCell(26);
                        BigDecimal comm = new BigDecimal("".equals(listDetail.get(r-10).getComm()) ? "0" : listDetail.get(r-10).getComm());
                        cell26.setCellValue((comm != null) ? comm.doubleValue() : new BigDecimal("0").doubleValue());
                        cell26.setCellStyle(styleDetailTableNumber);
                for (int i = 0; i < 25; i++) {
                    sheet.autoSizeColumn(i);
                }
            }
        }    
        
        System.out.println(count);
        HSSFRow rowL = sheet.createRow(count);
        rowL.createCell(0).setCellStyle(styleBorderTop);
        rowL.createCell(1).setCellStyle(styleBorderTop);
        rowL.createCell(2).setCellStyle(styleBorderTop);
        rowL.createCell(3).setCellStyle(styleBorderTop);
        rowL.createCell(4).setCellStyle(styleBorderTop);
        rowL.createCell(5).setCellStyle(styleBorderTop);
        rowL.createCell(6).setCellStyle(styleBorderTop);
        rowL.createCell(7).setCellStyle(styleBorderTop);
        rowL.createCell(8).setCellStyle(styleBorderTop);
        rowL.createCell(9).setCellStyle(styleBorderTop);
        rowL.createCell(10).setCellStyle(styleBorderTop);
        rowL.createCell(11).setCellStyle(styleBorderTop);
        rowL.createCell(12).setCellStyle(styleBorderTop);
        rowL.createCell(13).setCellStyle(styleBorderTop);
        rowL.createCell(14).setCellStyle(styleBorderTop);
        rowL.createCell(15).setCellStyle(styleBorderTop);
        rowL.createCell(16).setCellStyle(styleBorderTop);
        rowL.createCell(17).setCellStyle(styleBorderTop);
        rowL.createCell(18).setCellStyle(styleBorderTop);
        rowL.createCell(19).setCellStyle(styleBorderTop);
        rowL.createCell(20).setCellStyle(styleBorderTop);
        rowL.createCell(21).setCellStyle(styleBorderTop);
        rowL.createCell(22).setCellStyle(styleBorderTop);
        rowL.createCell(23).setCellStyle(styleBorderTop);
        rowL.createCell(24).setCellStyle(styleBorderTop);
        rowL.createCell(25).setCellStyle(styleBorderTop);
        rowL.createCell(26).setCellStyle(styleBorderTop);
            
//**********************************************************************************************************************
	// set Header Report (Row 1)
            HSSFCellStyle styleHeader02 = wb.createCellStyle();
            HSSFRow row21 = sheet1.createRow(0);
            HSSFCell cell21 = row21.createCell(0);
            cell21.setCellValue("List summary commission");
            styleHeader02.setFont(getHeaderFont(wb.createFont()));
            cell21.setCellStyle(styleHeader02);
            sheet1.addMergedRegion(CellRangeAddress.valueOf("A1:M1"));
            
            // Row 2
            HSSFRow row22 = sheet1.createRow(1);
            HSSFCell cell221 = row22.createCell(0);
                    cell221.setCellValue("Agent Name : ");
                    cell221.setCellStyle(styleAlignRight);
                    sheet1.autoSizeColumn(0);
            HSSFCell cell222 = row22.createCell(1);
                    cell222.setCellValue(ticketAir.getAgentNamePage());
                    cell222.setCellStyle(styleAlignLeft);
                    sheet1.autoSizeColumn(1);
            HSSFCell cell223 = row22.createCell(2);
                    cell223.setCellValue("Issue date : ");
                    cell223.setCellStyle(styleAlignRight);
                    sheet1.autoSizeColumn(2);
            HSSFCell cell224 = row22.createCell(3);
                    cell224.setCellValue(ticketAir.getIssuefromdatePage());
                    cell224.setCellStyle(styleAlignLeft);
                    sheet1.autoSizeColumn(3);
            HSSFCell cell225 = row22.createCell(4);
                    cell225.setCellValue("Print By : ");
                    cell225.setCellStyle(styleAlignRight);
                    sheet1.autoSizeColumn(4);
            HSSFCell cell226 = row22.createCell(5);
                    cell226.setCellValue(ticketAir.getPrintbyPage());
                    cell226.setCellStyle(styleAlignLeft);
                    sheet1.autoSizeColumn(5);

            // Row 3
            HSSFRow row23 = sheet1.createRow(2);
            HSSFCell cell231 = row23.createCell(0);
                    cell231.setCellValue("Type Routing : ");
                    cell231.setCellStyle(styleAlignRight);
                    sheet1.autoSizeColumn(0);
            HSSFCell cell232 = row23.createCell(1);
                    cell232.setCellValue(ticketAir.getTypeRoutingPage());
                    cell232.setCellStyle(styleAlignLeft);
                    sheet1.autoSizeColumn(1);
            HSSFCell cell233 = row23.createCell(2);
                    cell233.setCellValue("Over Comm Date : ");
                    cell233.setCellStyle(styleAlignRight);
                    sheet1.autoSizeColumn(2);
            HSSFCell cell234 = row23.createCell(3);
                    cell234.setCellValue(ticketAir.getOverfromdatePage());
                    cell234.setCellStyle(styleAlignLeft);
                    sheet1.autoSizeColumn(3);
            HSSFCell cell235 = row23.createCell(4);
                    cell235.setCellValue("Add Pay Date : ");
                    cell235.setCellStyle(styleAlignRight);
                    sheet1.autoSizeColumn(4);
            HSSFCell cell236 = row23.createCell(5);
                    cell236.setCellValue(ticketAir.getAddpayfromdatePage());
                    cell236.setCellStyle(styleAlignLeft);
                    sheet1.autoSizeColumn(5);

            // Row 4
            HSSFRow row24 = sheet1.createRow(3);
            HSSFCell cell241 = row24.createCell(0);
                    cell241.setCellValue("Air : ");
                    cell241.setCellStyle(styleAlignRight);
                    sheet1.autoSizeColumn(0);
            HSSFCell cell242 = row24.createCell(1);
                    cell242.setCellValue(ticketAir.getAirlineCodePage());
                    cell242.setCellStyle(styleAlignLeft);
                    sheet1.autoSizeColumn(1);
            HSSFCell cell243 = row24.createCell(2);
                    cell243.setCellValue("Little Comm date : ");
                    cell243.setCellStyle(styleAlignRight);
                    sheet1.autoSizeColumn(2);
            HSSFCell cell244 = row24.createCell(3);
                    cell244.setCellValue(ticketAir.getLittlefromdatePage());
                    cell244.setCellStyle(styleAlignLeft);
                    sheet1.autoSizeColumn(3);
            HSSFCell cell245 = row24.createCell(4);
                    cell245.setCellValue("Decrease Pay Date : ");
                    cell245.setCellStyle(styleAlignRight);
                    sheet1.autoSizeColumn(4);
            HSSFCell cell246 = row24.createCell(5);
                    cell246.setCellValue(ticketAir.getDecreasepayfromdatePage());
                    cell246.setCellStyle(styleAlignLeft);
                    sheet1.autoSizeColumn(5);

            // Row 5
            HSSFRow row25 = sheet1.createRow(4);
            HSSFCell cell251 = row25.createCell(0);
                    cell251.setCellValue("Routing Detail : ");
                    cell251.setCellStyle(styleAlignRight);
                    sheet1.autoSizeColumn(0);
            HSSFCell cell252 = row25.createCell(1);
                    cell252.setCellValue(ticketAir.getRoutingDetailPage());
                    cell252.setCellStyle(styleAlignLeft);
                    sheet1.autoSizeColumn(1);
            HSSFCell cell253 = row25.createCell(2);
                    cell253.setCellValue("Agent Comm Rev Date : ");
                    cell253.setCellStyle(styleAlignRight);
                    sheet1.autoSizeColumn(2);
            HSSFCell cell254 = row25.createCell(3);
                    cell254.setCellValue(ticketAir.getAgemtcomreceivefromdatePage());
                    cell254.setCellStyle(styleAlignLeft);
                    sheet1.autoSizeColumn(3);
            HSSFCell cell255 = row25.createCell(4);
                    cell255.setCellValue("Ticket No : ");
                    cell255.setCellStyle(styleAlignRight);
                    sheet1.autoSizeColumn(4);
            HSSFCell cell256 = row25.createCell(5);
                    cell256.setCellValue(ticketAir.getTicketcomfromdatePage());
                    cell256.setCellStyle(styleAlignLeft);
                    sheet1.autoSizeColumn(5);

            // Row 6
            HSSFRow row26 = sheet1.createRow(5);
            HSSFCell cell261 = row26.createCell(0);
                    cell261.setCellValue("Sale Staff : ");
                    cell261.setCellStyle(styleAlignRight);
            HSSFCell cell262 = row26.createCell(1);
                    cell262.setCellValue(ticketAir.getSalebyNamePage());
                    cell262.setCellStyle(styleAlignLeft);
            HSSFCell cell263 = row26.createCell(2);
                    cell263.setCellValue("Refund Comm Date : ");
                    cell263.setCellStyle(styleAlignRight);
            HSSFCell cell264 = row26.createCell(3);
                    cell264.setCellValue(ticketAir.getComrefundfromdatePage());
                    cell264.setCellStyle(styleAlignLeft);
            HSSFCell cell265 = row26.createCell(4);
                    cell265.setCellValue("Ticket Comm Date : ");
                    cell265.setCellStyle(styleAlignRight);
            HSSFCell cell266 = row26.createCell(5);
                    cell266.setCellValue(ticketAir.getTicketcomfromdatePage());
                    cell266.setCellStyle(styleAlignLeft);

            // Row 7
            HSSFRow row27 = sheet1.createRow(6);
            HSSFCell cell271 = row27.createCell(0);
                    cell271.setCellValue("Department : ");
                    cell271.setCellStyle(styleAlignRight);
            HSSFCell cell272 = row27.createCell(1);
                    cell272.setCellValue(ticketAir.getDepartmentPage());
                    cell272.setCellStyle(styleAlignLeft);
            HSSFCell cell273 = row27.createCell(2);
                    cell273.setCellValue("Invoice Date : ");
                    cell273.setCellStyle(styleAlignRight);
            HSSFCell cell274 = row27.createCell(3);
                    cell274.setCellValue(ticketAir.getInvoicefromdatePage());
                    cell274.setCellStyle(styleAlignLeft);
            HSSFCell cell275 = row27.createCell(4);
                    cell275.setCellValue("Print on : ");
                    cell275.setCellStyle(styleAlignRight);
            HSSFCell cell276 = row27.createCell(5);
                    cell276.setCellValue(ticketAir.getPrintonPage());
                    cell276.setCellStyle(styleAlignLeft);

            // Row 8
            HSSFRow row28 = sheet1.createRow(7);
            HSSFCell cell281 = row28.createCell(0);
                    cell281.setCellValue("Term Pay : ");
                    cell281.setCellStyle(styleAlignRight);
            HSSFCell cell282 = row28.createCell(1);
                    cell282.setCellValue(ticketAir.getTermPayPage());
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
            cell296.setCellValue("Amount Inbound");
            cell296.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(5);

            HSSFCell cell297 = row29.createCell(6);
            cell297.setCellValue("Sale Price");
            cell297.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(6);

            HSSFCell cell298 = row29.createCell(7);
            cell298.setCellValue("Cost");
            cell298.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(7);

            HSSFCell cell299 = row29.createCell(8);
            cell299.setCellValue("Over Comm");
            cell299.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(8);

            HSSFCell cell2100 = row29.createCell(9);
            cell2100.setCellValue("Add Pay");
            cell2100.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(9);

            HSSFCell cell2101 = row29.createCell(10);
            cell2101.setCellValue("Drecrease Pay");
            cell2101.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(10);

            HSSFCell cell2103 = row29.createCell(11);
            cell2103.setCellValue("Profit");
            cell2103.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(11);

            HSSFCell cell2104 = row29.createCell(12);
            cell2104.setCellValue("Comm");
            cell2104.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(12);

            HSSFCell cell2105 = row29.createCell(13);
            cell2105.setCellValue("Little Comm");
            cell2105.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(13);

            HSSFCell cell2106 = row29.createCell(14);
            cell2106.setCellValue("Agent[Comm] Pay");	
            cell2106.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(14);

            HSSFCell cell2107 = row29.createCell(15);
            cell2107.setCellValue("Agent[Comm] Receive");
            cell2107.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(15);

            HSSFCell cell2108 = row29.createCell(16);
            cell2108.setCellValue("Pay Comm Refund");
            cell2108.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(16);

            HSSFCell cell2109 = row29.createCell(17);
            cell2109.setCellValue("Comm Receive");
            cell2109.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(17);
           
        int count1 = 10 + listAir.size();
	if(listAir  != null){
            for (int r = 10; r < count1; r++) {
                    HSSFRow row = sheet1.createRow(r);
                    HSSFCell cell1 = row.createCell(0);
                        cell1.setCellValue(listAir.get(r-10).getTypepayment());
                        cell1.setCellStyle(styleDetailTable);
                    HSSFCell cell2 = row.createCell(1);
                        cell2.setCellValue(listAir.get(r-10).getTyperounting());
                        cell2.setCellStyle(styleDetailTable);
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
                   HSSFCell cell6 = row.createCell(5);
                           BigDecimal amountinbound = new BigDecimal("null".equals(String.valueOf(listAir.get(r-10).getAmountinbound())) ? "0" : listAir.get(r-10).getAmountinbound());
                           cell6.setCellValue((amountinbound != null) ? amountinbound.doubleValue() : new BigDecimal("0").doubleValue());
                           cell6.setCellStyle(styleDetailTableNumber);
                   HSSFCell cell7 = row.createCell(6);
                           BigDecimal sale = new BigDecimal("".equals(listAir.get(r-10).getSale()) ? "0" : listAir.get(r-10).getSale());
                           cell7.setCellValue((sale != null) ? sale.doubleValue() : new BigDecimal("0").doubleValue());
                           cell7.setCellStyle(styleDetailTableNumber);
                   HSSFCell cell8 = row.createCell(7);
                           BigDecimal cost  = new BigDecimal("".equals(listAir.get(r-10).getCost()) ? "0" : listAir.get(r-10).getCost());
                           cell8.setCellValue((cost != null) ? cost.doubleValue() : new BigDecimal("0").doubleValue());
                           cell8.setCellStyle(styleDetailTableNumber);
                   HSSFCell cell9 = row.createCell(8);
                           BigDecimal over  = new BigDecimal("".equals(listAir.get(r-10).getOver()) ? "0" : listAir.get(r-10).getOver());
                           cell9.setCellValue((over != null) ? over.doubleValue() : new BigDecimal("0").doubleValue());
                           cell9.setCellStyle(styleDetailTableNumber);
                   HSSFCell cell10 = row.createCell(9);
                           BigDecimal add  = new BigDecimal("".equals(listAir.get(r-10).getAdd()) ? "0" : listAir.get(r-10).getAdd());
                           cell10.setCellValue((add != null) ? add.doubleValue() : new BigDecimal("0").doubleValue());
                           cell10.setCellStyle(styleDetailTableNumber);
                   HSSFCell cell11 = row.createCell(10);
                           BigDecimal dres  = new BigDecimal("".equals(listAir.get(r-10).getDres()) ? "0" : listAir.get(r-10).getDres());
                           cell11.setCellValue((dres != null) ? dres.doubleValue() : new BigDecimal("0").doubleValue());
                           cell11.setCellStyle(styleDetailTableNumber);
                   HSSFCell cell12 = row.createCell(11);
                           BigDecimal profit  = new BigDecimal("".equals(listAir.get(r-10).getProfit()) ? "0" : listAir.get(r-10).getProfit());
                           cell12.setCellValue((profit != null) ? profit.doubleValue() : new BigDecimal("0").doubleValue());
                           cell12.setCellStyle(styleDetailTableNumber);
                   HSSFCell cell13 = row.createCell(12);
                           BigDecimal ticcom  = new BigDecimal("".equals(listAir.get(r-10).getTiccomm()) ? "0" : listAir.get(r-10).getTiccomm());
                           cell13.setCellValue((ticcom != null) ? ticcom.doubleValue() : new BigDecimal("0").doubleValue());
                           cell13.setCellStyle(styleDetailTableNumber);
                   HSSFCell cell14 = row.createCell(13);
                           BigDecimal little  = new BigDecimal("".equals(listAir.get(r-10).getLittle()) ? "0" : listAir.get(r-10).getLittle());
                           cell14.setCellValue((little != null) ? little.doubleValue() : new BigDecimal("0").doubleValue());
                           cell14.setCellStyle(styleDetailTableNumber);
                   HSSFCell cell15 = row.createCell(14);
                            BigDecimal agentcommpay = new BigDecimal("".equals(listAir.get(r-10).getAgentcommpay()) ? "0" : listAir.get(r-10).getAgentcommpay());
                            cell15.setCellValue((agentcommpay != null) ? agentcommpay.doubleValue() : new BigDecimal("0").doubleValue());
                            cell15.setCellStyle(styleDetailTableNumber);
                   HSSFCell cell16 = row.createCell(15);
                            BigDecimal agentcommrec = new BigDecimal("".equals(listAir.get(r-10).getAgentcommrec()) ? "0" : listAir.get(r-10).getAgentcommrec());
                            cell16.setCellValue((agentcommrec != null) ? agentcommrec.doubleValue() : new BigDecimal("0").doubleValue());
                            cell16.setCellStyle(styleDetailTableNumber);
                   HSSFCell cell17 = row.createCell(16);
                            BigDecimal pay = new BigDecimal("".equals(listAir.get(r-10).getPay()) ? "0" : listAir.get(r-10).getPay());
                            cell17.setCellValue((pay != null) ? pay.doubleValue() : new BigDecimal("0").doubleValue());
                            cell17.setCellStyle(styleDetailTableNumber);
                   HSSFCell cell18 = row.createCell(17);
                            BigDecimal comm = new BigDecimal("".equals(listAir.get(r-10).getComm()) ? "0" : listAir.get(r-10).getComm());
                            cell18.setCellValue((comm != null) ? comm.doubleValue() : new BigDecimal("0").doubleValue());
                            cell18.setCellStyle(styleDetailTableNumber);
                for (int i = 0; i < 25; i++) {
                    sheet1.autoSizeColumn(i);
                }
            }
        }
        
        HSSFRow rowtotal = sheet1.createRow(count1);
        String sumtotalpax = "SUM(C" + 10+":C"+(count1)+")";
        String sumtotalamountwen = "SUM(E" + 10+":E"+(count1)+")";
        String sumtotalamountin = "SUM(F" + 10+":F"+(count1)+")";
        String sumtotalsale = "SUM(G" + 10+":G"+(count1)+")";
        String sumtotalcost = "SUM(H" + 10+":H"+(count1)+")";
        String sumtotalover = "SUM(I" + 10+":I"+(count1)+")";
        String sumtotaladd = "SUM(J" + 10+":J"+(count1)+")";
        String sumtotaldres = "SUM(K" + 10+":K"+(count1)+")";
        String sumtotalprofit = "SUM(L" + 10+":L"+(count1)+")";
        String sumtotalticketcomm = "SUM(M" + 10+":M"+(count1)+")";
        String sumtotallittle = "SUM(N" + 10+":N"+(count1)+")";
        String sumtotalagentcommpay = "SUM(O" + 10+":O"+(count1)+")";
        String sumtotalagentcommrec = "SUM(P" + 10+":P"+(count1)+")";
        String sumtotalpay = "SUM(Q" + 10+":Q"+(count1)+")";
        String sumtotalcomm = "SUM(R" + 10+":R"+(count1)+")";
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
            cellTotalSum02.setCellFormula(sumtotalamountin);
            cellTotalSum02.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSum03 = rowtotal.createCell(6);
            cellTotalSum03.setCellFormula(sumtotalsale);
            cellTotalSum03.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSum04 = rowtotal.createCell(7);
            cellTotalSum04.setCellFormula(sumtotalcost);
            cellTotalSum04.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSum05 = rowtotal.createCell(8);
            cellTotalSum05.setCellFormula(sumtotalover);
            cellTotalSum05.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSum06 = rowtotal.createCell(9);
            cellTotalSum06.setCellFormula(sumtotaladd);
            cellTotalSum06.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSum07 = rowtotal.createCell(10);
            cellTotalSum07.setCellFormula(sumtotaldres);
            cellTotalSum07.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSum08 = rowtotal.createCell(11);
            cellTotalSum08.setCellFormula(sumtotalprofit);
            cellTotalSum08.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSum09 = rowtotal.createCell(12);
            cellTotalSum09.setCellFormula(sumtotalticketcomm);
            cellTotalSum09.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSum10 = rowtotal.createCell(13);
            cellTotalSum10.setCellFormula(sumtotallittle);
            cellTotalSum10.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSum11 = rowtotal.createCell(14);
            cellTotalSum11.setCellFormula(sumtotalagentcommpay);
            cellTotalSum11.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSum12 = rowtotal.createCell(15);
            cellTotalSum12.setCellFormula(sumtotalagentcommrec);
            cellTotalSum12.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSum13 = rowtotal.createCell(16);
            cellTotalSum13.setCellFormula(sumtotalpay);
            cellTotalSum13.setCellStyle(styleTotalTableNumber);
            HSSFCell cellTotalSum14 = rowtotal.createCell(17);
            cellTotalSum14.setCellFormula(sumtotalcomm);
            cellTotalSum14.setCellStyle(styleTotalTableNumber);


        
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
                styleHeader03.setFont(getHeaderFont(wb.createFont()));
                cell31.setCellStyle(styleHeader03);
                sheet2.addMergedRegion(CellRangeAddress.valueOf("A1:M1"));

                // Row 2
                HSSFRow row32 = sheet2.createRow(1);
                HSSFCell cell321 = row32.createCell(0);
                cell321.setCellValue("Agent Name : ");
                cell321.setCellStyle(styleAlignRight);
                        sheet2.autoSizeColumn(0);
                HSSFCell cell322 = row32.createCell(1);
                cell322.setCellValue(ticketAgent.getAgentNamePage());
                cell322.setCellStyle(styleAlignLeft);
                        sheet2.autoSizeColumn(1);
                HSSFCell cell323 = row32.createCell(2);
                cell323.setCellValue("Issue date : ");
                cell323.setCellStyle(styleAlignRight);
                        sheet2.autoSizeColumn(2);
                HSSFCell cell324 = row32.createCell(3);
                cell324.setCellValue(ticketAgent.getIssuefromdatePage());
                cell324.setCellStyle(styleAlignLeft);
                        sheet2.autoSizeColumn(3);
                HSSFCell cell325 = row32.createCell(4);
                cell325.setCellValue("Print By : ");
                cell325.setCellStyle(styleAlignRight);
                        sheet2.autoSizeColumn(4);
                HSSFCell cell326 = row32.createCell(5);
                cell326.setCellValue(ticketAgent.getPrintbyPage());
                cell326.setCellStyle(styleAlignLeft);
                        sheet2.autoSizeColumn(5);

                // Row 3
                HSSFRow row33 = sheet2.createRow(2);
                HSSFCell cell331 = row33.createCell(0);
                cell331.setCellValue("Type Routing : ");
                cell331.setCellStyle(styleAlignRight);
                        sheet2.autoSizeColumn(0);
                HSSFCell cell332 = row33.createCell(1);
                cell332.setCellValue(ticketAgent.getTypeRoutingPage());
                cell332.setCellStyle(styleAlignLeft);
                        sheet2.autoSizeColumn(1);
                HSSFCell cell333 = row33.createCell(2);
                cell333.setCellValue("Over Comm Date : ");
                cell333.setCellStyle(styleAlignRight);
                        sheet2.autoSizeColumn(2);
                HSSFCell cell334 = row33.createCell(3);
                cell334.setCellValue(ticketAgent.getOverfromdatePage());
                cell334.setCellStyle(styleAlignLeft);
                        sheet2.autoSizeColumn(3);
                HSSFCell cell335 = row33.createCell(4);
                cell335.setCellValue("Add Pay Date : ");
                cell335.setCellStyle(styleAlignRight);
                        sheet2.autoSizeColumn(4);
                HSSFCell cell336 = row33.createCell(5);
                cell336.setCellValue(ticketAgent.getAddpayfromdatePage());
                cell336.setCellStyle(styleAlignLeft);
                        sheet2.autoSizeColumn(5);

                // Row 4
                HSSFRow row34 = sheet2.createRow(3);
                HSSFCell cell341 = row34.createCell(0);
                cell341.setCellValue("Air : ");
                cell341.setCellStyle(styleAlignRight);
                        sheet2.autoSizeColumn(0);
                HSSFCell cell342 = row34.createCell(1);
                cell342.setCellValue(ticketAgent.getAirlineCodePage());
                cell342.setCellStyle(styleAlignLeft);
                        sheet2.autoSizeColumn(1);
                HSSFCell cell343 = row34.createCell(2);
                cell343.setCellValue("Little Comm date : ");
                cell343.setCellStyle(styleAlignRight);
                        sheet2.autoSizeColumn(2);
                HSSFCell cell344 = row34.createCell(3);
                cell344.setCellValue(ticketAgent.getLittlefromdatePage());
                cell344.setCellStyle(styleAlignLeft);
                        sheet2.autoSizeColumn(3);
                HSSFCell cell345 = row34.createCell(4);
                cell345.setCellValue("Decrease Pay Date : ");
                cell345.setCellStyle(styleAlignRight);
                        sheet2.autoSizeColumn(4);
                HSSFCell cell346 = row34.createCell(5);
                cell346.setCellValue(ticketAgent.getDecreasepayfromdatePage());
                cell346.setCellStyle(styleAlignLeft);
                        sheet2.autoSizeColumn(5);

                // Row 5
                HSSFRow row35 = sheet2.createRow(4);
                HSSFCell cell351 = row35.createCell(0);
                cell351.setCellValue("Routing Detail : ");
                cell351.setCellStyle(styleAlignRight);
                        sheet2.autoSizeColumn(0);
                HSSFCell cell352 = row35.createCell(1);
                cell352.setCellValue(ticketAgent.getRoutingDetailPage());
                cell352.setCellStyle(styleAlignLeft);
                        sheet2.autoSizeColumn(1);
                HSSFCell cell353 = row35.createCell(2);
                cell353.setCellValue("Agent Comm Rev Date : ");
                cell353.setCellStyle(styleAlignRight);
                        sheet2.autoSizeColumn(2);
                HSSFCell cell354 = row35.createCell(3);
                cell354.setCellValue(ticketAgent.getAgentcomfromdatePage());
                cell354.setCellStyle(styleAlignLeft);
                        sheet2.autoSizeColumn(3);
                HSSFCell cell355 = row35.createCell(4);
                cell355.setCellValue("Ticket No : ");
                cell355.setCellStyle(styleAlignRight);
                        sheet2.autoSizeColumn(4);
                HSSFCell cell356 = row35.createCell(5);
                cell356.setCellValue(ticketAgent.getTicketnoPagePage());
                cell356.setCellStyle(styleAlignLeft);
                        sheet2.autoSizeColumn(5);

                // Row 6
                HSSFRow row36 = sheet2.createRow(5);
                HSSFCell cell361 = row36.createCell(0);
                cell361.setCellValue("Sale Staff : ");
                cell361.setCellStyle(styleAlignRight);
                HSSFCell cell362 = row36.createCell(1);
                cell362.setCellValue(ticketAgent.getSalebyNamePage());
                cell362.setCellStyle(styleAlignLeft);
                HSSFCell cell363 = row36.createCell(2);
                cell363.setCellValue("Refund Comm Date : ");
                cell363.setCellStyle(styleAlignRight);
                HSSFCell cell364 = row36.createCell(3);
                cell364.setCellValue(ticketAgent.getComrefundfromdatePage());
                cell364.setCellStyle(styleAlignLeft);
                HSSFCell cell365 = row36.createCell(4);
                cell365.setCellValue("Ticket Comm Date : ");
                cell365.setCellStyle(styleAlignRight);
                HSSFCell cell366 = row36.createCell(5);
                cell366.setCellValue(ticketAgent.getTicketcomfromdatePage());
                cell366.setCellStyle(styleAlignLeft);

                // Row 7
                HSSFRow row37 = sheet2.createRow(6);
                HSSFCell cell371 = row37.createCell(0);
                cell371.setCellValue("Department : ");
                cell371.setCellStyle(styleAlignRight);
                HSSFCell cell372 = row37.createCell(1);
                cell372.setCellValue(ticketAgent.getDepartmentPage());
                cell372.setCellStyle(styleAlignLeft);
                HSSFCell cell373 = row37.createCell(2);
                cell373.setCellValue("Invoice Date : ");
                cell373.setCellStyle(styleAlignRight);
                HSSFCell cell374 = row37.createCell(3);
                cell374.setCellValue(ticketAgent.getInvoicefromdatePage());
                cell374.setCellStyle(styleAlignLeft);
                HSSFCell cell375 = row37.createCell(4);
                cell375.setCellValue("Print on : ");
                cell375.setCellStyle(styleAlignRight);
                HSSFCell cell376 = row37.createCell(5);
                cell376.setCellValue(ticketAgent.getPrintonPage());
                cell376.setCellStyle(styleAlignLeft);

                // Row 8
                HSSFRow row38 = sheet2.createRow(7);
                HSSFCell cell381 = row38.createCell(0);
                cell381.setCellValue("Term Pay : ");
                cell381.setCellStyle(styleAlignRight);
                HSSFCell cell382 = row38.createCell(1);
                cell382.setCellValue(ticketAgent.getTermPayPage());
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
                cell3105.setCellValue("Amount Inbound");
                cell3105.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(3);

                HSSFCell cell3106 = row39.createCell(4);
                cell3106.setCellValue("Sale");	
                cell3106.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(4);

                HSSFCell cell3107 = row39.createCell(5);
                cell3107.setCellValue("Cost");
                cell3107.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(5);

                HSSFCell cell3108 = row39.createCell(6);
                cell3108.setCellValue("Over Comm");
                cell3108.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(6);

                HSSFCell cell3109 = row39.createCell(7);
                cell3109.setCellValue("Add Pay");
                cell3109.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(7);

                HSSFCell cell3110 = row39.createCell(8);
                cell3110.setCellValue("Drecrease Pay");
                cell3110.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(8);

                HSSFCell cell3111 = row39.createCell(9);
                cell3111.setCellValue("Profit");
                cell3111.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(9);

                HSSFCell cell3116 = row39.createCell(10);
                cell3116.setCellValue("Comm");
                cell3116.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(10);
                
                HSSFCell cell3112 = row39.createCell(11);
                cell3112.setCellValue("Little Comm");
                cell3112.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(11);

                HSSFCell cell3113 = row39.createCell(12);
                cell3113.setCellValue("Agent[Comm] Pay");
                cell3113.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(12);

                HSSFCell cell3114 = row39.createCell(13);
                cell3114.setCellValue("Agent[Comm] Receive");
                cell3114.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(13);

                HSSFCell cell3115 = row39.createCell(14);
                cell3115.setCellValue("Pay");
                cell3115.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(14);
                
                HSSFCell cell3117 = row39.createCell(15);
                cell3117.setCellValue("Comm");
                cell3117.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(14);

            int count2 = 10 + listAgent.size();

            if(listAgent != null){
            for (int r = 10; r < count2; r++) {
                HSSFRow row = sheet2.createRow(r);
                HSSFCell cell1 = row.createCell(0);
                    cell1.setCellValue(listAgent.get(r-10).getAgentname());
                    cell1.setCellStyle(styleDetailTable);
                HSSFCell cell2 = row.createCell(1);
                    cell2.setCellValue(listAgent.get(r-10).getPax());
                    cell2.setCellStyle(stylePaxNumber);
               HSSFCell cell13 = row.createCell(2);
                       BigDecimal amountwendy = new BigDecimal("".equals(listAgent.get(r-10).getAmountwendy()) ? "0" : listAgent.get(r-10).getAmountwendy());
                       cell13.setCellValue((amountwendy != null) ? amountwendy.doubleValue() : new BigDecimal("0").doubleValue());
                       cell13.setCellStyle(styleDetailTableNumber);
               HSSFCell cell14 = row.createCell(3);
                       BigDecimal amountin = new BigDecimal("".equals(listAgent.get(r-10).getAmountinbound()) ? "0" : listAgent.get(r-10).getAmountinbound());
                       cell14.setCellValue((amountin != null) ? amountin.doubleValue() : new BigDecimal("0").doubleValue());
                       cell14.setCellStyle(styleDetailTableNumber);
               HSSFCell cell15 = row.createCell(4);
                       BigDecimal sale = new BigDecimal("".equals(listAgent.get(r-10).getSale()) ? "0" : listAgent.get(r-10).getSale());
                       cell15.setCellValue((sale != null) ? sale.doubleValue() : new BigDecimal("0").doubleValue());
                       cell15.setCellStyle(styleDetailTableNumber);
               HSSFCell cell16 = row.createCell(5);
                       BigDecimal cost  = new BigDecimal("".equals(listAgent.get(r-10).getCost()) ? "0" : listAgent.get(r-10).getCost());
                       cell16.setCellValue((cost != null) ? cost.doubleValue() : new BigDecimal("0").doubleValue());
                       cell16.setCellStyle(styleDetailTableNumber);
               HSSFCell cell17 = row.createCell(6);
                       BigDecimal over  = new BigDecimal("".equals(listAgent.get(r-10).getOver()) ? "0" : listAgent.get(r-10).getOver());
                       cell17.setCellValue((over != null) ? over.doubleValue() : new BigDecimal("0").doubleValue());
                       cell17.setCellStyle(styleDetailTableNumber);
               HSSFCell cell18 = row.createCell(7);
                       BigDecimal add  = new BigDecimal("".equals(listAgent.get(r-10).getAdd()) ? "0" : listAgent.get(r-10).getAdd());
                       cell18.setCellValue((add != null) ? add.doubleValue() : new BigDecimal("0").doubleValue());
                       cell18.setCellStyle(styleDetailTableNumber);
               HSSFCell cell19 = row.createCell(8);
                       BigDecimal dres  = new BigDecimal("".equals(listAgent.get(r-10).getDres()) ? "0" : listAgent.get(r-10).getDres());
                       cell19.setCellValue((dres != null) ? dres.doubleValue() : new BigDecimal("0").doubleValue());
                       cell19.setCellStyle(styleDetailTableNumber);
               HSSFCell cell20 = row.createCell(9);
                       BigDecimal profit  = new BigDecimal("".equals(listAgent.get(r-10).getProfit()) ? "0" : listAgent.get(r-10).getProfit());
                       cell20.setCellValue((profit != null) ? profit.doubleValue() : new BigDecimal("0").doubleValue());
                       cell20.setCellStyle(styleDetailTableNumber);
               HSSFCell cell2205 = row.createCell(10);
                       BigDecimal ticcom  = new BigDecimal("".equals(listAgent.get(r-10).getTiccomm()) ? "0" : listAgent.get(r-10).getTiccomm());
                       cell2205.setCellValue((ticcom != null) ? ticcom.doubleValue() : new BigDecimal("0").doubleValue());
                       cell2205.setCellStyle(styleDetailTableNumber);
               HSSFCell cell212 = row.createCell(11);
                       BigDecimal little  = new BigDecimal("".equals(listAgent.get(r-10).getLittle()) ? "0" : listAgent.get(r-10).getLittle());
                       cell212.setCellValue((little != null) ? little.doubleValue() : new BigDecimal("0").doubleValue());
                       cell212.setCellStyle(styleDetailTableNumber);
               HSSFCell cell22 = row.createCell(12);
                        BigDecimal agentcommpay = new BigDecimal("".equals(listAgent.get(r-10).getAgentcommpay()) ? "0" : listAgent.get(r-10).getAgentcommpay());
                        cell22.setCellValue((agentcommpay != null) ? agentcommpay.doubleValue() : new BigDecimal("0").doubleValue());
                        cell22.setCellStyle(styleDetailTableNumber);
                HSSFCell cell23 = row.createCell(13);
                        BigDecimal agentcommrec = new BigDecimal("".equals(listAgent.get(r-10).getAgentcommrec()) ? "0" : listAgent.get(r-10).getAgentcommrec());
                        cell23.setCellValue((agentcommrec != null) ? agentcommrec.doubleValue() : new BigDecimal("0").doubleValue());
                        cell23.setCellStyle(styleDetailTableNumber);        
               HSSFCell cell24 = row.createCell(14);
                        BigDecimal pay = new BigDecimal("".equals(listAgent.get(r-10).getPay()) ? "0" : listAgent.get(r-10).getPay());
                        cell24.setCellValue((pay != null) ? pay.doubleValue() : new BigDecimal("0").doubleValue());
                        cell24.setCellStyle(styleDetailTableNumber);
               HSSFCell cell25 = row.createCell(15);
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
            
            
        HSSFRow rowLLL = sheet2.createRow(count2);
         rowLLL.createCell(0).setCellStyle(styleBorderTop);
         rowLLL.createCell(1).setCellStyle(styleBorderTop);
         rowLLL.createCell(2).setCellStyle(styleBorderTop);
         rowLLL.createCell(3).setCellStyle(styleBorderTop);
         rowLLL.createCell(4).setCellStyle(styleBorderTop);
         rowLLL.createCell(5).setCellStyle(styleBorderTop);
         rowLLL.createCell(6).setCellStyle(styleBorderTop);
         rowLLL.createCell(7).setCellStyle(styleBorderTop);
         rowLLL.createCell(8).setCellStyle(styleBorderTop);
         rowLLL.createCell(9).setCellStyle(styleBorderTop);
         rowLLL.createCell(10).setCellStyle(styleBorderTop);
         rowLLL.createCell(11).setCellStyle(styleBorderTop);
         rowLLL.createCell(12).setCellStyle(styleBorderTop);
         rowLLL.createCell(13).setCellStyle(styleBorderTop);
	 rowLLL.createCell(14).setCellStyle(styleBorderTop);    
         rowLLL.createCell(15).setCellStyle(styleBorderTop);            
    }
}