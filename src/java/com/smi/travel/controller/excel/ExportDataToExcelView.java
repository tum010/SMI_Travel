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
import com.smi.travel.datalayer.view.entity.CollectionNirvana;
import com.smi.travel.datalayer.view.entity.ListSummaryTicketAdjustCostAndIncome;
import com.smi.travel.datalayer.view.entity.ListTicketCommissionReceive;
import com.smi.travel.datalayer.view.entity.RefundTicketView;
import com.smi.travel.datalayer.view.entity.SummaryAirline;
import com.smi.travel.datalayer.view.entity.SummaryTicketAdjustCostAndIncome;
import com.smi.travel.datalayer.view.entity.TicketCommissionReceive;
import com.smi.travel.datalayer.view.entity.TicketSummaryAirlineView;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
    private static final String RefundTicketDetail = "RefundTicketDetail";
    
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
        }else if(name.equalsIgnoreCase(SummaryTicketAdjustCostAndIncome)){
            System.out.println("gen report SummaryTicketAdjustCostAndIncome");
            getSummaryTicketAdjustCostAndIncome(workbook, (List) model.get(name));
        }else if(name.equalsIgnoreCase(SummaryTicketCommissionReceive)){
            System.out.println("gen report SummaryTicketCommissionReceive");
            getTicketCommissionReceive(workbook, (List) model.get(name));
        }else if(name.equalsIgnoreCase(RefundTicketDetail)){
            System.out.println("gen report RefundTicketDetail");
            getRefundTicketDetail(workbook, (List) model.get(name));
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
        sheet.autoSizeColumn(2);
        cell63.setCellStyle(styleC3Center);
        HSSFCell cell64 = row6.createCell(3);
        cell64.setCellValue("Ticket No.");
        cell64.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(3);
        HSSFCell cell65 = row6.createCell(4);
        cell65.setCellValue("Department");
        cell65.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(4);
        HSSFCell cell66 = row6.createCell(5);
        cell66.setCellValue("Staff");
        cell66.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(5);
        HSSFCell cell67 = row6.createCell(6);
        cell67.setCellValue("Term Pay");
        cell67.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(6);
        
        HSSFCellStyle styleC3Right = wb.createCellStyle();
        styleC3Right.setFont(getHeaderTable(wb.createFont()));
        styleC3Right.setAlignment(styleC3Right.ALIGN_RIGHT);
        styleC3Right.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC3Right.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC3Right.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC3Right.setBorderTop(HSSFCellStyle.BORDER_THIN);
        HSSFCell cell68 = row6.createCell(7);
        cell68.setCellValue("Inv. Amount");
        cell68.setCellStyle(styleC3Right);
        sheet.autoSizeColumn(7);
        HSSFCell cell69 = row6.createCell(8);
        cell69.setCellValue("Ticket Comm");
        cell69.setCellStyle(styleC3Right);
        sheet.autoSizeColumn(8);
        HSSFCell cell70 = row6.createCell(9);
        cell70.setCellValue("Sale Price");
        cell70.setCellStyle(styleC3Right);
        sheet.autoSizeColumn(9);
        HSSFCell cell71 = row6.createCell(10);
        cell71.setCellValue("Agent Comm");
        cell71.setCellStyle(styleC3Right);
        sheet.autoSizeColumn(10);
        HSSFCell cell72 = row6.createCell(11);
        cell72.setCellValue("Profit");
        cell72.setCellStyle(styleC3Right);
        sheet.autoSizeColumn(11);
        
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
                cell3.setCellValue(data.getDocno());
                cell3.setCellStyle(styleC24);
             HSSFCell cell4 = row.createCell(4);
                cell4.setCellValue(data.getDepartment());
                cell4.setCellStyle(styleC24);
             HSSFCell cell5 = row.createCell(5);
                cell5.setCellValue(data.getStaff());
                cell5.setCellStyle(styleC24);
             HSSFCell cell6 = row.createCell(6);
                cell6.setCellValue(data.getTermpay());
                cell6.setCellStyle(styleC24);
             HSSFCell cell7 = row.createCell(7);
                cell7.setCellValue(!"".equalsIgnoreCase(data.getInvamount()) ? new BigDecimal(data.getInvamount()).doubleValue() : 0);
                cell7.setCellStyle(styleC25);
             HSSFCell cell8 = row.createCell(8);
                cell8.setCellValue(!"".equalsIgnoreCase(data.getTicketcom()) ? new BigDecimal(data.getTicketcom()).doubleValue() : 0);
                cell8.setCellStyle(styleC25);   
             HSSFCell cell9 = row.createCell(9);
                cell9.setCellValue(!"".equalsIgnoreCase(data.getSaleprice()) ? new BigDecimal(data.getSaleprice()).doubleValue() : 0);
                cell9.setCellStyle(styleC25);      
            HSSFCell cell10 = row.createCell(10);
                cell10.setCellValue(!"".equalsIgnoreCase(data.getAgentcom()) ? new BigDecimal(data.getAgentcom()).doubleValue() : 0);
                cell10.setCellStyle(styleC25);      
            HSSFCell cell11 = row.createCell(11);
                cell11.setCellValue(!"".equalsIgnoreCase(data.getProfit()) ? new BigDecimal(data.getProfit()).doubleValue() : 0);
                cell11.setCellStyle(styleC25);          
             if(i == (TicketAgent.size()-1)){
                row = sheet.createRow(count + i + 1);
                for(int k=0;k<6;k++){
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
                
                String sumInvAmount = "SUM(H" + 10+":H"+(count + i + 1)+")";
                String sumTicketComm = "SUM(I" + 10+":I"+(count + i + 1)+")";
                String sumSalePrice = "SUM(J" + 10+":J"+(count + i + 1)+")";
                String sumAgentComm = "SUM(K" + 10+":K"+(count + i + 1)+")";
                String sumProfit = "SUM(L" + 10+":L"+(count + i + 1)+")";
                
                HSSFCell cell6Sum = row.createCell(6);
                    cell6Sum.setCellValue("Total");
                    cell6Sum.setCellStyle(styleSum);
                HSSFCell cell7Sum = row.createCell(7);
                    cell7Sum.setCellFormula(sumInvAmount);
                    cell7Sum.setCellStyle(styleSum);
                HSSFCell cell8Sum = row.createCell(8);
                    cell8Sum.setCellFormula(sumTicketComm);
                    cell8Sum.setCellStyle(styleSum);
                HSSFCell cell9Sum = row.createCell(9);
                    cell9Sum.setCellFormula(sumSalePrice);
                    cell9Sum.setCellStyle(styleSum);
                HSSFCell cell10Sum = row.createCell(10);
                    cell10Sum.setCellFormula(sumAgentComm);
                    cell10Sum.setCellStyle(styleSum);
                HSSFCell cell11Sum = row.createCell(11);
                    cell11Sum.setCellFormula(sumProfit);
                    cell11Sum.setCellStyle(styleSum);  
             }
             for(int j =0;j<13;j++){
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
        HSSFCell cell69 = row6.createCell(8);
        cell69.setCellValue("Issue Date");
        cell69.setCellStyle(styleC3);
        sheet.autoSizeColumn(8);
        HSSFCell cell70 = row6.createCell(9);
        cell70.setCellValue("Net Sales");
        cell70.setCellStyle(styleC3);
        sheet.autoSizeColumn(9);
        HSSFCell cell71 = row6.createCell(10);
        cell71.setCellValue("Tax");
        cell71.setCellStyle(styleC3);
        sheet.autoSizeColumn(10);
        HSSFCell cell72 = row6.createCell(11);
        cell72.setCellValue("Insurance");
        cell72.setCellStyle(styleC3);
        sheet.autoSizeColumn(11);
        HSSFCell cell73 = row6.createCell(12);
        cell73.setCellValue("Actual Commission");
        cell73.setCellStyle(styleC3);
        sheet.autoSizeColumn(12);
        HSSFCell cell74 = row6.createCell(13);
        cell74.setCellValue("Inv. Amount");
        cell74.setCellStyle(styleC3);
        sheet.autoSizeColumn(13);
        
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
  
            //set data 
            HSSFCell celldata8 = row.createCell(8);
            celldata8.setCellValue(data.getIssuedate());
            celldata8.setCellStyle(styleC23);
            
            HSSFCell celldata9 = row.createCell(9);
            celldata9.setCellValue("".equalsIgnoreCase(String.valueOf(data.getNetsale())) ? 0 : new BigDecimal(data.getNetsale()).doubleValue());
            celldata9.setCellStyle(styleC25);
            
            HSSFCell celldata10 = row.createCell(10);
            celldata10.setCellValue("".equalsIgnoreCase(String.valueOf(data.getTax())) ? 0 : new BigDecimal(data.getTax()).doubleValue());
            celldata10.setCellStyle(styleC25);
            
            HSSFCell celldata11 = row.createCell(11);
            celldata11.setCellValue("".equalsIgnoreCase(String.valueOf(data.getIns())) ? 0 : new BigDecimal(data.getIns()).doubleValue());
            celldata11.setCellStyle(styleC25);
            
            HSSFCell celldata12 = row.createCell(12);
            celldata12.setCellValue("".equalsIgnoreCase(String.valueOf(data.getActcom())) ? 0 : new BigDecimal(data.getActcom()).doubleValue());
            celldata12.setCellStyle(styleC25);
            
            HSSFCell celldata13 = row.createCell(13);
            celldata13.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInvamount())) ? 0 : new BigDecimal(data.getInvamount()).doubleValue());
            celldata13.setCellStyle(styleC25);
             
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
                
                String netsalesTotal = "SUM(J" + 10+":J"+(count + i + 1)+")";
                String taxTotal = "SUM(K" + 10+":K"+(count + i + 1)+")";
                String insTotal = "SUM(L" + 10+":L"+(count + i + 1)+")";
                String actcommTotal = "SUM(M" + 10+":M"+(count + i + 1)+")";
                String invamountTotal = "SUM(N" + 10+":N"+(count + i + 1)+")";

                HSSFCell cellTotal00 = row.createCell(8);
                cellTotal00.setCellValue("Total");
                cellTotal00.setCellStyle(styleSum);                
                HSSFCell cellTotal01 = row.createCell(9);
                cellTotal01.setCellFormula(netsalesTotal);
                cellTotal01.setCellStyle(styleSum);
                HSSFCell cellTotal02 = row.createCell(10);
                cellTotal02.setCellFormula(taxTotal);
                cellTotal02.setCellStyle(styleSum);
                HSSFCell cellTotal03 = row.createCell(11);
                cellTotal03.setCellFormula(insTotal);
                cellTotal03.setCellStyle(styleSum);
                HSSFCell cellTotal04 = row.createCell(12);
                cellTotal04.setCellFormula(actcommTotal);
                cellTotal04.setCellStyle(styleSum);
                HSSFCell cellTotal05 = row.createCell(13);
                cellTotal05.setCellFormula(invamountTotal);
                cellTotal05.setCellStyle(styleSum);
             }
             
             for(int j =0;j<13;j++){
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
            cell8.setCellValue("");
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
        HSSFCell cell63 = row6.createCell(2);
        cell63.setCellValue("Issue Date");
        sheet.autoSizeColumn(2);
        cell63.setCellStyle(styleC3Center);
        HSSFCell cell64 = row6.createCell(3);
        cell64.setCellValue("Department");
        cell64.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(3);
        HSSFCell cell65 = row6.createCell(4);
        cell65.setCellValue("Staff");
        cell65.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(4);
        HSSFCell cell66 = row6.createCell(5);
        cell66.setCellValue("Term Pay");
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
        cell67.setCellValue("Tax");
        cell67.setCellStyle(styleC3Right);
        sheet.autoSizeColumn(6);
        HSSFCell cell68 = row6.createCell(7);
        cell68.setCellValue("Actual Commission");
        cell68.setCellStyle(styleC3Right);
        sheet.autoSizeColumn(7);
        HSSFCell cell69 = row6.createCell(8);
        cell69.setCellValue("Insurance");
        cell69.setCellStyle(styleC3Right);
        sheet.autoSizeColumn(8);
        HSSFCell cell70 = row6.createCell(9);
        cell70.setCellValue("Net Sales");
        cell70.setCellStyle(styleC3Right);
        sheet.autoSizeColumn(9);

        HSSFCell cell71 = row6.createCell(10);
        cell71.setCellValue("Vat");
        cell71.setCellStyle(styleC3Right);
        sheet.autoSizeColumn(10);

        HSSFCell cell72 = row6.createCell(11);
        cell72.setCellValue("Invoice No.");
        cell72.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(11);

        HSSFCell cell73 = row6.createCell(12);
        cell73.setCellValue("Invoice Amount");
        cell73.setCellStyle(styleC3Right);
        sheet.autoSizeColumn(12);
        HSSFCell cell74 = row6.createCell(13);
        cell74.setCellValue("Balance Payable");
        cell74.setCellStyle(styleC3Right);
        sheet.autoSizeColumn(13);
        
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
             HSSFCell cell2 = row.createCell(2);
                cell2.setCellValue(data.getIssuedate());
                cell2.setCellStyle(styleC23);   
             HSSFCell cell3= row.createCell(3);
                cell3.setCellValue(data.getDepartment());
                cell3.setCellStyle(styleC24);
             HSSFCell cell4 = row.createCell(4);
                cell4.setCellValue(data.getStaff());
                cell4.setCellStyle(styleC24);   
             HSSFCell cell5 = row.createCell(5);
                cell5.setCellValue(data.getTermpay());
                cell5.setCellStyle(styleC24);
             HSSFCell cell6 = row.createCell(6);
                cell6.setCellValue(!"".equalsIgnoreCase(data.getTax()) ? new BigDecimal(data.getTax()).doubleValue() : 0);
                cell6.setCellStyle(styleC25);
             HSSFCell cell7 = row.createCell(7);
                cell7.setCellValue(!"".equalsIgnoreCase(data.getTicketcom()) ? new BigDecimal(data.getTicketcom()).doubleValue() : 0);
                cell7.setCellStyle(styleC25);
             HSSFCell cell8 = row.createCell(8);
                cell8.setCellValue(!"".equalsIgnoreCase(data.getIns()) ? new BigDecimal(data.getIns()).doubleValue() : 0);
                cell8.setCellStyle(styleC25);
             HSSFCell cell9 = row.createCell(9);
                cell9.setCellValue(!"".equalsIgnoreCase(data.getSaleprice()) ? new BigDecimal(data.getSaleprice()).doubleValue() : 0);
                cell9.setCellStyle(styleC25);
             HSSFCell cell10 = row.createCell(10);
                cell10.setCellValue(!"".equalsIgnoreCase(data.getVat()) ? new BigDecimal(data.getVat()).doubleValue() : 0);
                cell10.setCellStyle(styleC25);
             HSSFCell cell11 = row.createCell(11);
                cell11.setCellValue(data.getInvno());
                cell11.setCellStyle(styleC24);
             HSSFCell cell12 = row.createCell(12);
                cell12.setCellValue(!"".equalsIgnoreCase(data.getInvamount()) ? new BigDecimal(data.getInvamount()).doubleValue() : 0);
                cell12.setCellStyle(styleC25);
             HSSFCell cell13 = row.createCell(13);
                cell13.setCellValue(!"".equalsIgnoreCase(data.getBalance()) ? new BigDecimal(data.getBalance()).doubleValue() : 0);
                cell13.setCellStyle(styleC25);
             if(i == (TicketFare.size()-1)){
                 row = sheet.createRow(count + i + 1);
                 for(int k=0;k<6;k++){
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
                
                String sumTax = "SUM(G" + 10+":G"+(count + i + 1)+")";
                String sumActComm = "SUM(H" + 10+":H"+(count + i + 1)+")";
                String sumInsurance = "SUM(I" + 10+":I"+(count + i + 1)+")";
                String sumNetSales = "SUM(J" + 10+":J"+(count + i + 1)+")";
                String sumVat = "SUM(K" + 10+":K"+(count + i + 1)+")";
                String sumInvAmount = "SUM(M" + 10+":M"+(count + i + 1)+")";
                String sumBalance = "SUM(N" + 10+":N"+(count + i + 1)+")";
                
                HSSFCell cell5Sum = row.createCell(5);
                    cell5Sum.setCellValue("Total");
                    cell5Sum.setCellStyle(styleSum);
                HSSFCell cell6Sum = row.createCell(6);
                    cell6Sum.setCellFormula(sumTax);
                    cell6Sum.setCellStyle(styleSum);
                HSSFCell cell7Sum = row.createCell(7);
                    cell7Sum.setCellFormula(sumActComm);
                    cell7Sum.setCellStyle(styleSum);
                HSSFCell cell8Sum = row.createCell(8);
                    cell8Sum.setCellFormula(sumInsurance);
                    cell8Sum.setCellStyle(styleSum);
                HSSFCell cell9Sum = row.createCell(9);
                    cell9Sum.setCellFormula(sumNetSales);
                    cell9Sum.setCellStyle(styleSum);
                HSSFCell cell10Sum = row.createCell(10);
                    cell10Sum.setCellFormula(sumVat);
                    cell10Sum.setCellStyle(styleSum);
                HSSFCell cell11Sum = row.createCell(11);
                    cell11Sum.setCellStyle(styleSum);    
                HSSFCell cell12Sum = row.createCell(12);
                    cell12Sum.setCellFormula(sumInvAmount);
                    cell12Sum.setCellStyle(styleSum);  
                HSSFCell cell13Sum = row.createCell(13);
                    cell13Sum.setCellFormula(sumBalance);
                    cell13Sum.setCellStyle(styleSum);      
             }
             for(int j =0;j<13;j++){
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
//        Total Wendy
        int totalPaxWendy = 0 ;
        BigDecimal totalInvAmountWendy = new BigDecimal("0.00");
        BigDecimal totalTiccomWendy = new BigDecimal("0.00");
        BigDecimal totalSalePriceWendy = new BigDecimal("0.00");
        BigDecimal totalAgentCommWendy = new BigDecimal("0.00");
        BigDecimal totalProfitWendy = new BigDecimal("0.00");
        for(int i=0;i<ticketSumByStaff.size();i++){
            TicketFareSummaryByAgentStaff data = (TicketFareSummaryByAgentStaff)ticketSumByStaff.get(i);
            if(!temp.equalsIgnoreCase(data.getOwner())){
                if(!"".equalsIgnoreCase(temp)){
                   HSSFRow row = sheet.createRow(count + i);
                    String totalPax = "SUM(C" + ktemp+":C"+(count + i)+")";
                    String totalInvAmount = "SUM(D" + ktemp+":D"+(count + i )+")";
                    String totalTiccom = "SUM(E" + ktemp+":E"+(count + i )+")";
                    String totalSalePrice = "SUM(F" + ktemp+":F"+(count + i)+")";
                    String totalAgentComm = "SUM(G" + ktemp+":G"+(count + i)+")";
                    String totalProfit = "SUM(H" + ktemp+":H"+(count + i)+")";
                    
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
                    cellTotal04.setCellFormula(totalTiccom);
                    cellTotal04.setCellStyle(styleC25);
                    HSSFCell cellTotal05 = row.createCell(5);
                    cellTotal05.setCellFormula(totalSalePrice);
                    cellTotal05.setCellStyle(styleC25);
                    HSSFCell cellTotal06 = row.createCell(6);
                    cellTotal06.setCellFormula(totalAgentComm);
                    cellTotal06.setCellStyle(styleC25);
                    HSSFCell cellTotal07 = row.createCell(7);
                    cellTotal07.setCellFormula(totalProfit);
                    cellTotal07.setCellStyle(styleC25);
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
                cell095.setCellValue("Ticket Comm");
                cell095.setCellStyle(styleC3);
                sheet.autoSizeColumn(4);
                HSSFCell cell096 = row09.createCell(5);
                cell096.setCellValue("Sales Price");
                cell096.setCellStyle(styleC3);
                sheet.autoSizeColumn(5);
                HSSFCell cell097 = row09.createCell(6);
                cell097.setCellValue("Agent Comm");
                cell097.setCellStyle(styleC3);
                sheet.autoSizeColumn(6);
                HSSFCell cell098 = row09.createCell(7);
                cell098.setCellValue("Profit");
                cell098.setCellStyle(styleC3);
                sheet.autoSizeColumn(7);
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
            celldata05.setCellValue("".equalsIgnoreCase(String.valueOf(data.getTiccom())) ? 0: new BigDecimal(data.getTiccom()).doubleValue());
            celldata05.setCellStyle(styleC25);
            HSSFCell celldata06 = row.createCell(5);
            celldata06.setCellValue("".equalsIgnoreCase(String.valueOf(data.getSaleprice())) ? 0 : new BigDecimal(data.getSaleprice()).doubleValue());
            celldata06.setCellStyle(styleC25);
            HSSFCell celldata07 = row.createCell(6);
            celldata07.setCellValue("".equalsIgnoreCase(String.valueOf(data.getAgentcom())) ? 0 : new BigDecimal(data.getAgentcom()).doubleValue()); 
            celldata07.setCellStyle(styleC25);
            HSSFCell celldata08 = row.createCell(7);
            celldata08.setCellValue("".equalsIgnoreCase(String.valueOf(data.getProfit())) ? 0 : new BigDecimal(data.getProfit()).doubleValue());
            celldata08.setCellStyle(styleC25);
            
            if("outbound".equalsIgnoreCase(data.getDepartment())){
                TicketFareSummaryByAgentStaff sum = (TicketFareSummaryByAgentStaff)ticketSumByStaff.get(i);
                int pax = (!"".equalsIgnoreCase(sum.getPax()) ? Integer.parseInt(sum.getPax()) : 0);
                BigDecimal invamount = new BigDecimal(!"".equalsIgnoreCase(sum.getInvamount()) ? sum.getInvamount() : "0.00");
                BigDecimal ticcom = new BigDecimal(!"".equalsIgnoreCase(sum.getTiccom()) ? sum.getTiccom() : "0.00");
                BigDecimal saleprice = new BigDecimal(!"".equalsIgnoreCase(sum.getSaleprice()) ? sum.getSaleprice() : "0.00");
                BigDecimal agentcomm = new BigDecimal(!"".equalsIgnoreCase(sum.getAgentcom()) ? sum.getAgentcom() : "0.00");
                BigDecimal profit = new BigDecimal(!"".equalsIgnoreCase(sum.getProfit()) ? sum.getProfit() : "0.00");
                totalPaxOut = totalPaxOut+pax;
                totalInvAmountOut = totalInvAmountOut.add(invamount);
                totalTiccomOut = totalTiccomOut.add(ticcom);
                totalSalePriceOut = totalSalePriceOut.add(saleprice);
                totalAgentCommOut = totalAgentCommOut.add(agentcomm);
                totalProfitOut = totalProfitOut.add(profit);
            }
            if("wendy".equalsIgnoreCase(data.getDepartment())){
                TicketFareSummaryByAgentStaff sum = (TicketFareSummaryByAgentStaff)ticketSumByStaff.get(i);
                int pax = (!"".equalsIgnoreCase(sum.getPax()) ? Integer.parseInt(sum.getPax()) : 0);
                BigDecimal invamount = new BigDecimal(!"".equalsIgnoreCase(sum.getInvamount()) ? sum.getInvamount() : "0.00");
                BigDecimal ticcom = new BigDecimal(!"".equalsIgnoreCase(sum.getTiccom()) ? sum.getTiccom() : "0.00");
                BigDecimal saleprice = new BigDecimal(!"".equalsIgnoreCase(sum.getSaleprice()) ? sum.getSaleprice() : "0.00");
                BigDecimal agentcomm = new BigDecimal(!"".equalsIgnoreCase(sum.getAgentcom()) ? sum.getAgentcom() : "0.00");
                BigDecimal profit = new BigDecimal(!"".equalsIgnoreCase(sum.getProfit()) ? sum.getProfit() : "0.00");
                totalPaxWendy = totalPaxWendy+pax;
                totalInvAmountWendy = totalInvAmountWendy.add(invamount);
                totalTiccomWendy = totalTiccomWendy.add(ticcom);
                totalSalePriceWendy = totalSalePriceWendy.add(saleprice);
                totalAgentCommWendy = totalAgentCommWendy.add(agentcomm);
                totalProfitWendy = totalProfitWendy.add(profit);
            }
            
            // set total last row
            if(i == (ticketSumByStaff.size()-1)){
                HSSFRow rows = sheet.createRow(count + 1 + i);
                
                String totalPax = "SUM(C" + ktemp+":C"+(count + i + 1)+")";
                String totalInvAmount = "SUM(D" + ktemp+":D"+(count + i + 1)+")";
                String totalTiccom = "SUM(E" + ktemp+":E"+(count + i +1)+")";
                String totalSalePrice = "SUM(F" + ktemp+":F"+(count + i + 1)+")";
                String totalAgentComm = "SUM(G" + ktemp+":G"+(count + i + 1)+")";
                String totalProfit = "SUM(H" + ktemp+":H"+(count + i + 1)+")";

                
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
                celldatas04.setCellFormula(totalTiccom);
                celldatas04.setCellStyle(styleC25);
                HSSFCell celldatas05 = rows.createCell(5);
                celldatas05.setCellFormula(totalSalePrice);
                celldatas05.setCellStyle(styleC25);
                HSSFCell celldatas06 = rows.createCell(6);
                celldatas06.setCellFormula(totalAgentComm);
                celldatas06.setCellStyle(styleC25);
                HSSFCell celldatas07 = rows.createCell(7);
                celldatas07.setCellFormula(totalProfit);
                celldatas07.setCellStyle(styleC25);
                count = count+3;

                rows = sheet.createRow(count+i);
                HSSFCell celldataOut01 = rows.createCell(1);
                celldataOut01.setCellValue("Summary Outbound");
                celldataOut01.setCellStyle(styleC22);
                HSSFCell celldataOut02 = rows.createCell(2);
                celldataOut02.setCellValue("".equalsIgnoreCase(String.valueOf(totalPaxOut)) ? 0 : new BigDecimal(totalPaxOut).doubleValue());
                celldataOut02.setCellStyle(styleC27);
                HSSFCell celldataOut03 = rows.createCell(3);
                celldataOut03.setCellValue("".equalsIgnoreCase(String.valueOf(totalInvAmountOut)) ? 0 : totalInvAmountOut.doubleValue());
                celldataOut03.setCellStyle(styleC28);
                HSSFCell celldataOut04 = rows.createCell(4);
                celldataOut04.setCellValue("".equalsIgnoreCase(String.valueOf(totalTiccomOut)) ? 0 : totalTiccomOut.doubleValue());
                celldataOut04.setCellStyle(styleC28);
                HSSFCell celldataOut05 = rows.createCell(5);
                celldataOut05.setCellValue("".equalsIgnoreCase(String.valueOf(totalSalePriceOut)) ? 0 : totalSalePriceOut.doubleValue());
                celldataOut05.setCellStyle(styleC28);
                HSSFCell celldataOut06 = rows.createCell(6);
                celldataOut06.setCellValue("".equalsIgnoreCase(String.valueOf(totalAgentCommOut)) ? 0 : totalAgentCommOut.doubleValue());
                celldataOut06.setCellStyle(styleC28);
                HSSFCell celldataOut07 = rows.createCell(7);
                celldataOut07.setCellValue("".equalsIgnoreCase(String.valueOf(totalProfitOut)) ? 0 : totalProfitOut.doubleValue());
                celldataOut07.setCellStyle(styleC28);

                rows = sheet.createRow(count+1+i);
                HSSFCell celldataWen01 = rows.createCell(1);
                celldataWen01.setCellValue("Summary Wendy");
                celldataWen01.setCellStyle(styleC22);
                HSSFCell celldataWen02 = rows.createCell(2);
                celldataWen02.setCellValue("".equalsIgnoreCase(String.valueOf(totalPaxWendy)) ? 0 : new BigDecimal(totalPaxWendy).doubleValue());
                celldataWen02.setCellStyle(styleC27);
                HSSFCell celldataWen03 = rows.createCell(3);
                celldataWen03.setCellValue("".equalsIgnoreCase(String.valueOf(totalInvAmountWendy)) ? 0 : totalInvAmountWendy.doubleValue());
                celldataWen03.setCellStyle(styleC28);
                HSSFCell celldataWen04 = rows.createCell(4);
                celldataWen04.setCellValue("".equalsIgnoreCase(String.valueOf(totalTiccomWendy)) ? 0 : totalTiccomWendy.doubleValue());
                celldataWen04.setCellStyle(styleC28);
                HSSFCell celldataWen05 = rows.createCell(5);
                celldataWen05.setCellValue("".equalsIgnoreCase(String.valueOf(totalSalePriceWendy)) ? 0 : totalSalePriceWendy.doubleValue());
                celldataWen05.setCellStyle(styleC28);
                HSSFCell celldataWen06 = rows.createCell(6);
                celldataWen06.setCellValue("".equalsIgnoreCase(String.valueOf(totalAgentCommWendy)) ? 0 : totalAgentCommWendy.doubleValue());
                celldataWen06.setCellStyle(styleC28);
                HSSFCell celldataWen07 = rows.createCell(7);
                celldataWen07.setCellValue("".equalsIgnoreCase(String.valueOf(totalProfitWendy)) ? 0 : totalProfitWendy.doubleValue());
                celldataWen07.setCellStyle(styleC28);
                

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
                String totalTiccomWI = "SUM(E" + (count+i+1)+":E"+(count+i+2)+")";
                String totalSalePriceWI = "SUM(F" + (count+i+1)+":F"+(count+i+2)+")";
                String totalAgentCommWI = "SUM(G" + (count+i+1)+":G"+(count+i+2)+")";
                String totalProfitWI = "SUM(H" + (count+i+1)+":H"+(count+i+2)+")";

                HSSFCell celldataWI01 = rows.createCell(1);
                celldataWI01.setCellValue("Total");
                celldataWI01.setCellStyle(styleC22);
                HSSFCell celldataWI02 = rows.createCell(2);
                celldataWI02.setCellFormula(totalPaxWI);
                celldataWI02.setCellStyle(styleC27);
                HSSFCell celldataWI03 = rows.createCell(3);
                celldataWI03.setCellFormula(totalInvAmountWI);
                celldataWI03.setCellStyle(styleC28);
                HSSFCell celldataWI04 = rows.createCell(4);
                celldataWI04.setCellFormula(totalTiccomWI);
                celldataWI04.setCellStyle(styleC28);
                HSSFCell celldataWI05 = rows.createCell(5);
                celldataWI05.setCellFormula(totalSalePriceWI);
                celldataWI05.setCellStyle(styleC28);
                HSSFCell celldataWI06 = rows.createCell(6);
                celldataWI06.setCellFormula(totalAgentCommWI);
                celldataWI06.setCellStyle(styleC28);
                HSSFCell celldataWI07 = rows.createCell(7);
                celldataWI07.setCellFormula(totalProfitWI);
                celldataWI07.setCellStyle(styleC28);
                
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
        //Total Wendy
        int totalPaxWendy = 0 ;
        BigDecimal totalInvAmountWendy = new BigDecimal("0.00");
        BigDecimal totalTiccomWendy = new BigDecimal("0.00");
        BigDecimal totalSalePriceWendy = new BigDecimal("0.00");
        BigDecimal totalAgentCommWendy = new BigDecimal("0.00");
        BigDecimal totalProfitWendy = new BigDecimal("0.00");
        for(int i=0;i<ticketSumByAgent.size();i++){
            TicketFareSummaryByAgentStaff data = (TicketFareSummaryByAgentStaff)ticketSumByAgent.get(i);
            if(!temp.equalsIgnoreCase(data.getAgentname())){
                if(!"".equalsIgnoreCase(temp)){
                    HSSFRow row = sheet.createRow(count + i);
                    String totalPax = "SUM(C" + ktemp+":C"+(count + i)+")";
                    String totalInvAmount = "SUM(D" + ktemp+":D"+(count + i )+")";
                    String totalTiccom = "SUM(E" + ktemp+":E"+(count + i )+")";
                    String totalSalePrice = "SUM(F" + ktemp+":F"+(count + i)+")";
                    String totalAgentComm = "SUM(G" + ktemp+":G"+(count + i)+")";
                    String totalProfit = "SUM(H" + ktemp+":H"+(count + i)+")";
                    
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
                    cellTotal04.setCellFormula(totalTiccom);
                    cellTotal04.setCellStyle(styleC25);
                    HSSFCell cellTotal05 = row.createCell(5);
                    cellTotal05.setCellFormula(totalSalePrice);
                    cellTotal05.setCellStyle(styleC25);
                    HSSFCell cellTotal06 = row.createCell(6);
                    cellTotal06.setCellFormula(totalAgentComm);
                    cellTotal06.setCellStyle(styleC25);
                    HSSFCell cellTotal07 = row.createCell(7);
                    cellTotal07.setCellFormula(totalProfit);
                    cellTotal07.setCellStyle(styleC25);
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
                cell095.setCellValue("Ticket Comm");
                cell095.setCellStyle(styleC3);
                sheet.autoSizeColumn(4);
                HSSFCell cell096 = row09.createCell(5);
                cell096.setCellValue("Sales Price");
                cell096.setCellStyle(styleC3);
                sheet.autoSizeColumn(5);
                HSSFCell cell097 = row09.createCell(6);
                cell097.setCellValue("Agent Comm");
                cell097.setCellStyle(styleC3);
                sheet.autoSizeColumn(6);
                HSSFCell cell098 = row09.createCell(7);
                cell098.setCellValue("Profit");
                cell098.setCellStyle(styleC3);
                sheet.autoSizeColumn(7);
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
            celldata05.setCellValue("".equalsIgnoreCase(String.valueOf(data.getTiccom())) ? 0: new BigDecimal(data.getTiccom()).doubleValue());
            celldata05.setCellStyle(styleC25);
            HSSFCell celldata06 = row.createCell(5);
            celldata06.setCellValue("".equalsIgnoreCase(String.valueOf(data.getSaleprice())) ? 0 : new BigDecimal(data.getSaleprice()).doubleValue());
            celldata06.setCellStyle(styleC25);
            HSSFCell celldata07 = row.createCell(6);
            celldata07.setCellValue("".equalsIgnoreCase(String.valueOf(data.getAgentcom())) ? 0 : new BigDecimal(data.getAgentcom()).doubleValue()); 
            celldata07.setCellStyle(styleC25);
            HSSFCell celldata08 = row.createCell(7);
            celldata08.setCellValue("".equalsIgnoreCase(String.valueOf(data.getProfit())) ? 0 : new BigDecimal(data.getProfit()).doubleValue());
            celldata08.setCellStyle(styleC25);
            
            if("outbound".equalsIgnoreCase(data.getDepartment())){
                TicketFareSummaryByAgentStaff sum = (TicketFareSummaryByAgentStaff)ticketSumByAgent.get(i);
                int pax = (!"".equalsIgnoreCase(sum.getPax()) ? Integer.parseInt(sum.getPax()) : 0);
                BigDecimal invamount = new BigDecimal(!"".equalsIgnoreCase(sum.getInvamount()) ? sum.getInvamount() : "0.00");
                BigDecimal ticcom = new BigDecimal(!"".equalsIgnoreCase(sum.getTiccom()) ? sum.getTiccom() : "0.00");
                BigDecimal saleprice = new BigDecimal(!"".equalsIgnoreCase(sum.getSaleprice()) ? sum.getSaleprice() : "0.00");
                BigDecimal agentcomm = new BigDecimal(!"".equalsIgnoreCase(sum.getAgentcom()) ? sum.getAgentcom() : "0.00");
                BigDecimal profit = new BigDecimal(!"".equalsIgnoreCase(sum.getProfit()) ? sum.getProfit() : "0.00");
                totalPaxOut = totalPaxOut+pax;
                totalInvAmountOut = totalInvAmountOut.add(invamount);
                totalTiccomOut = totalTiccomOut.add(ticcom);
                totalSalePriceOut = totalSalePriceOut.add(saleprice);
                totalAgentCommOut = totalAgentCommOut.add(agentcomm);
                totalProfitOut = totalProfitOut.add(profit);
            }
            if("wendy".equalsIgnoreCase(data.getDepartment())){
                TicketFareSummaryByAgentStaff sum = (TicketFareSummaryByAgentStaff)ticketSumByAgent.get(i);
                int pax = (!"".equalsIgnoreCase(sum.getPax()) ? Integer.parseInt(sum.getPax()) : 0);
                BigDecimal invamount = new BigDecimal(!"".equalsIgnoreCase(sum.getInvamount()) ? sum.getInvamount() : "0.00");
                BigDecimal ticcom = new BigDecimal(!"".equalsIgnoreCase(sum.getTiccom()) ? sum.getTiccom() : "0.00");
                BigDecimal saleprice = new BigDecimal(!"".equalsIgnoreCase(sum.getSaleprice()) ? sum.getSaleprice() : "0.00");
                BigDecimal agentcomm = new BigDecimal(!"".equalsIgnoreCase(sum.getAgentcom()) ? sum.getAgentcom() : "0.00");
                BigDecimal profit = new BigDecimal(!"".equalsIgnoreCase(sum.getProfit()) ? sum.getProfit() : "0.00");
                totalPaxWendy = totalPaxWendy+pax;
                totalInvAmountWendy = totalInvAmountWendy.add(invamount);
                totalTiccomWendy = totalTiccomWendy.add(ticcom);
                totalSalePriceWendy = totalSalePriceWendy.add(saleprice);
                totalAgentCommWendy = totalAgentCommWendy.add(agentcomm);
                totalProfitWendy = totalProfitWendy.add(profit);
            }
            
            // set total last row
            if(i == (ticketSumByAgent.size()-1)){
                HSSFRow rows = sheet.createRow(count + 1 + i);
                
                String totalPax = "SUM(C" + ktemp+":C"+(count + i + 1)+")";
                String totalInvAmount = "SUM(D" + ktemp+":D"+(count + i + 1)+")";
                String totalTiccom = "SUM(E" + ktemp+":E"+(count + i +1)+")";
                String totalSalePrice = "SUM(F" + ktemp+":F"+(count + i + 1)+")";
                String totalAgentComm = "SUM(G" + ktemp+":G"+(count + i + 1)+")";
                String totalProfit = "SUM(H" + ktemp+":H"+(count + i + 1)+")";
                
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
                celldatas04.setCellFormula(totalTiccom);
                celldatas04.setCellStyle(styleC25);
                HSSFCell celldatas05 = rows.createCell(5);
                celldatas05.setCellFormula(totalSalePrice);
                celldatas05.setCellStyle(styleC25);
                HSSFCell celldatas06 = rows.createCell(6);
                celldatas06.setCellFormula(totalAgentComm);
                celldatas06.setCellStyle(styleC25);
                HSSFCell celldatas07 = rows.createCell(7);
                celldatas07.setCellFormula(totalProfit);
                celldatas07.setCellStyle(styleC25);
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
                celldataOut04.setCellValue("".equalsIgnoreCase(String.valueOf(totalTiccomOut)) ? 0 : totalTiccomOut.doubleValue());
                celldataOut04.setCellStyle(styleC27);
                HSSFCell celldataOut05 = rows.createCell(5);
                celldataOut05.setCellValue("".equalsIgnoreCase(String.valueOf(totalSalePriceOut)) ? 0 : totalSalePriceOut.doubleValue());
                celldataOut05.setCellStyle(styleC27);
                HSSFCell celldataOut06 = rows.createCell(6);
                celldataOut06.setCellValue("".equalsIgnoreCase(String.valueOf(totalAgentCommOut)) ? 0 : totalAgentCommOut.doubleValue());
                celldataOut06.setCellStyle(styleC27);
                HSSFCell celldataOut07 = rows.createCell(7);
                celldataOut07.setCellValue("".equalsIgnoreCase(String.valueOf(totalProfitOut)) ? 0 : totalProfitOut.doubleValue());
                celldataOut07.setCellStyle(styleC27);

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
                celldataWen04.setCellValue("".equalsIgnoreCase(String.valueOf(totalTiccomWendy)) ? 0 : totalTiccomWendy.doubleValue());
                celldataWen04.setCellStyle(styleC27);
                HSSFCell celldataWen05 = rows.createCell(5);
                celldataWen05.setCellValue("".equalsIgnoreCase(String.valueOf(totalSalePriceWendy)) ? 0 : totalSalePriceWendy.doubleValue());
                celldataWen05.setCellStyle(styleC27);
                HSSFCell celldataWen06 = rows.createCell(6);
                celldataWen06.setCellValue("".equalsIgnoreCase(String.valueOf(totalAgentCommWendy)) ? 0 : totalAgentCommWendy.doubleValue());
                celldataWen06.setCellStyle(styleC27);
                HSSFCell celldataWen07 = rows.createCell(7);
                celldataWen07.setCellValue("".equalsIgnoreCase(String.valueOf(totalProfitWendy)) ? 0 : totalProfitWendy.doubleValue());
                celldataWen07.setCellStyle(styleC27);

                rows = sheet.createRow(count+2+i);
                String totalPaxWI = "SUM(C" +(count+i+1)+":C"+(count+i+2)+")";
                String totalInvAmountWI = "SUM(D" +(count+i+1)+":D"+(count+i+2)+")";
                String totalTiccomWI = "SUM(E" + (count+i+1)+":E"+(count+i+2)+")";
                String totalSalePriceWI = "SUM(F" + (count+i+1)+":F"+(count+i+2)+")";
                String totalAgentCommWI = "SUM(G" + (count+i+1)+":G"+(count+i+2)+")";
                String totalProfitWI = "SUM(H" + (count+i+1)+":H"+(count+i+2)+")";

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
                celldataWI04.setCellFormula(totalTiccomWI);
                celldataWI04.setCellStyle(styleC27);
                HSSFCell celldataWI05 = rows.createCell(5);
                celldataWI05.setCellFormula(totalSalePriceWI);
                celldataWI05.setCellStyle(styleC27);
                HSSFCell celldataWI06 = rows.createCell(6);
                celldataWI06.setCellFormula(totalAgentCommWI);
                celldataWI06.setCellStyle(styleC27);
                HSSFCell celldataWI07 = rows.createCell(7);
                celldataWI07.setCellFormula(totalProfitWI);
                celldataWI07.setCellStyle(styleC27);
                
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
        List<BillAirAgent> listAgent = BillAirAgent;
        String sheetName = "Summary";// name of sheet
        String sheetName1 = "Detail";
        HSSFSheet sheet = wb.createSheet(sheetName);
        HSSFSheet sheet1 = wb.createSheet(sheetName1);
        
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
                
        BillAirAgent bil = new BillAirAgent();
        if((BillAirAgent != null)&&(BillAirAgent.size() != 0)){
            bil = (BillAirAgent)BillAirAgent.get(0);
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
                        cell1511.setCellValue(df.format(sumComPay));
                        cell1511.setCellStyle(styleAlignRight);
                        sheet.addMergedRegion(CellRangeAddress.valueOf("B15:D15"));
                        sheet.autoSizeColumn(1);
                HSSFCell cell152 = row15.createCell(4);
                        cell152.setCellValue("Vat Pay : ");
                        cell152.setCellStyle(styleAlignRight);
                        sheet.autoSizeColumn(4);
                HSSFCell cell1521 = row15.createCell(5);
                        cell1521.setCellValue(df.format(vatComPay));
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
                        
     // Sheet Detail (2)
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
                    cell93.setCellValue("Issue Date");
                    cell93.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(12);
                    HSSFCell cell94 = row8.createCell(13);
                    cell94.setCellValue("Net Sales");
                    cell94.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(13);
                    HSSFCell cell95 = row8.createCell(14);
                    cell95.setCellValue("Tax");
                    cell95.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(14);
                    HSSFCell cell96 = row8.createCell(15);
                    cell96.setCellValue("Ins.");
                    cell96.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(15);
                    HSSFCell cell97 = row8.createCell(16);
                    cell97.setCellValue("Comms");
                    cell97.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(16);
                    HSSFCell cell98 = row8.createCell(17);
                    cell98.setCellValue("Amount Wendy");
                    cell98.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(17);
                    HSSFCell cell99 = row8.createCell(18);
                    cell99.setCellValue("Amount Inbound");
                    cell99.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(18);
                    HSSFCell cell100 = row8.createCell(19);
                    cell100.setCellValue("Remarks");
                    cell100.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(19);
                    HSSFCell cell101 = row8.createCell(20);
                    cell101.setCellValue("Diff");
                    cell101.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(20);
                
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
                    celldata12.setCellValue(String.valueOf(data.getIssuedateD()));
                    celldata12.setCellStyle(styleC29);

                    HSSFCell celldata13 = row.createCell(13);
                    celldata13.setCellValue("".equalsIgnoreCase(String.valueOf(data.getNetsalesD())) ? 0 : (new BigDecimal(data.getNetsalesD())).doubleValue());
                    celldata13.setCellStyle(styleC25);

                    HSSFCell celldata14 = row.createCell(14);
                    celldata14.setCellValue("".equalsIgnoreCase(String.valueOf(data.getTaxD())) ? 0 : (new BigDecimal(data.getTaxD())).doubleValue());
                    celldata14.setCellStyle(styleC25);

                    HSSFCell celldata15 = row.createCell(15);
                    celldata15.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInsD())) ? 0 : (new BigDecimal(data.getInsD())).doubleValue());
                    celldata15.setCellStyle(styleC25);

                    HSSFCell celldata16 = row.createCell(16);
                    celldata16.setCellValue("".equalsIgnoreCase(String.valueOf(data.getCommsD())) ? 0 : (new BigDecimal(data.getCommsD())).doubleValue());
                    celldata16.setCellStyle(styleC25);

                    HSSFCell celldata17 = row.createCell(17);
                    celldata17.setCellValue("".equalsIgnoreCase(String.valueOf(data.getAmountwendyD())) ? 0 : (new BigDecimal(data.getAmountwendyD())).doubleValue());
                    celldata17.setCellStyle(styleC25);

                    HSSFCell celldata18 = row.createCell(18);
                    celldata18.setCellValue("".equalsIgnoreCase(String.valueOf(data.getAmountinboundD())) ? 0 : (new BigDecimal(data.getAmountinboundD())).doubleValue());
                    celldata18.setCellStyle(styleC25);

                    HSSFCell celldata19 = row.createCell(19);
                    celldata19.setCellValue(String.valueOf(data.getRemarksD()));
                    celldata19.setCellStyle(styleC29);

                    HSSFCell celldata20 = row.createCell(20);
                    celldata20.setCellValue("".equalsIgnoreCase(String.valueOf(data.getDiffD())) ? 0 : (new BigDecimal(data.getDiffD())).doubleValue());
                    celldata20.setCellStyle(styleC25);
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
        
        List<RefundTicketView> list = refundTicket;
        RefundTicketView refund = new RefundTicketView();
        refund = (RefundTicketView) list.get(0);
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
        cell62.setCellValue("Pay Date");
        cell62.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(1);
        HSSFCell cell63 = row6.createCell(2);
        cell63.setCellValue("Ticket No");
        sheet.autoSizeColumn(2);
        cell63.setCellStyle(styleAlignRightBorderAllHeaderTable);
        HSSFCell cell65 = row6.createCell(3);
        cell65.setCellValue("Refund To");
        cell65.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(3);
        HSSFCell cell66 = row6.createCell(4);
        cell66.setCellValue("Refund By");
        cell66.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(5);
        HSSFCell cell67 = row6.createCell(5);
        cell67.setCellValue("Sector Refund");
        cell67.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(5);
        HSSFCell cell68 = row6.createCell(6);
        cell68.setCellValue("Receive Airline");
        cell68.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(6);
        HSSFCell cell69 = row6.createCell(7);
        cell69.setCellValue("Refund Date");
        cell69.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(7);
        HSSFCell cell71 = row6.createCell(8);
        cell71.setCellValue("Pay Customer");
        cell71.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(8);
        HSSFCell cell72 = row6.createCell(9);
        cell72.setCellValue("Pay Date");
        cell72.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(9);
        HSSFCell cell73 = row6.createCell(10);
        cell73.setCellValue("Profit");
        cell73.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(10);
        HSSFCell cell74 = row6.createCell(11);
        cell74.setCellValue("Receive Airline");
        cell74.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(11);
        HSSFCell cell75 = row6.createCell(12);
        cell75.setCellValue("Airline Comm");
        cell75.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(12);
        
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
            HSSFCell cell2 = row.createCell(1);
                cell2.setCellValue(listRefund.get(r-9).getPaydate());
                cell2.setCellStyle(styleDetailTable);
            HSSFCell cell3 = row.createCell(2);
                cell3.setCellValue(listRefund.get(r-9).getTicketno());
                cell3.setCellStyle(styleDetailTableNumber);
            HSSFCell cell4 = row.createCell(3);
                cell4.setCellValue(listRefund.get(r-9).getRefundto());
                cell4.setCellStyle(styleDetailTable);
            HSSFCell cell55 = row.createCell(4);
                cell55.setCellValue(listRefund.get(r-9).getRefundby());
                cell55.setCellStyle(styleDetailTable);
            HSSFCell cell5 = row.createCell(5);
                cell5.setCellValue(listRefund.get(r-9).getSectorrefund());
                cell5.setCellStyle(styleDetailTableNumber);
            HSSFCell cell6 = row.createCell(6);
                cell6.setCellValue(listRefund.get(r-9).getReceiveairline());
                cell6.setCellStyle(styleDetailTableNumber);
            HSSFCell cell7 = row.createCell(7);
                cell7.setCellValue(listRefund.get(r-9).getRefunddate());
                cell7.setCellStyle(styleDetailTableNumber);
            HSSFCell cell8 = row.createCell(8);
                cell8.setCellValue(listRefund.get(r-9).getPaycustomer());
                cell8.setCellStyle(styleDetailTable);
            HSSFCell cell9 = row.createCell(9);
                cell9.setCellValue(listRefund.get(r-9).getPaydate2());
                cell9.setCellStyle(styleDetailTableNumber);
            HSSFCell cell10 = row.createCell(10);
                BigDecimal discount = new BigDecimal(listRefund.get(r-9).getProfit());
                cell10.setCellValue((discount != null) ? discount.doubleValue() : new BigDecimal("0").doubleValue());
                cell10.setCellStyle(styleDetailTableNumber);
                sheet.autoSizeColumn(10);
            HSSFCell cell11 = row.createCell(11);
                BigDecimal cancel = new BigDecimal(listRefund.get(r-9).getReceiveairline2());
                cell11.setCellValue((cancel != null) ? cancel.doubleValue() : new BigDecimal("0").doubleValue());
                cell11.setCellStyle(styleDetailTableNumber);
            HSSFCell cell12 = row.createCell(12);
                BigDecimal wait = new BigDecimal(listRefund.get(r-9).getAirlinecomm());
                cell12.setCellValue((wait != null) ? wait.doubleValue() : new BigDecimal("0").doubleValue());
                cell12.setCellStyle(styleDetailTableNumber);
            for (int i = 0; i < listRefund.size(); i++) {
                sheet.autoSizeColumn(i);
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
    }
}