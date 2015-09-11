/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller.excel;

import com.smi.travel.datalayer.report.model.TicketFareReport;
import com.smi.travel.datalayer.report.model.TicketFareSummaryByAgentStaff;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
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
    private static final String TicketFareInvoicReport = "TicketFareInvoicReport";
    private static final String TicketFareAgentReport = "TicketFareAgentReport";
    private static final String TicketFareSummaryByStaff = "TicketFareSummaryByStaff";
    private static final String TicketFareSummaryByAgent = "TicketFareSummaryByAgent";
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
        }

    }
    
    private void genTicketFareAgentReport(HSSFWorkbook wb, List TicketAgent) {
        String sheetName = "Sheet1";// name of sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        
        TicketFareReport dataheader = new TicketFareReport();
        
        if(TicketAgent != null){
            dataheader = (TicketFareReport)TicketAgent.get(0);
        }

        // set Header Report (Row 1)
        HSSFCellStyle styleC1 = wb.createCellStyle();
        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cell1 = row1.createCell(0);
        cell1.setCellValue("List Ticket Payment Agent");
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
        HSSFCellStyle styleC3 = wb.createCellStyle();
        styleC3.setFont(getHeaderTable(wb.createFont()));
        HSSFRow row6 = sheet.createRow(8);
        HSSFCell cell61 = row6.createCell(0);
        cell61.setCellValue("Invoice No.");
        cell61.setCellStyle(styleC3);
        sheet.autoSizeColumn(0);
        HSSFCell cell62 = row6.createCell(1);
        cell62.setCellValue("Issue Date");
        cell62.setCellStyle(styleC3);
        sheet.autoSizeColumn(1);
        HSSFCell cell63 = row6.createCell(2);
        cell63.setCellValue("Agent");
        sheet.autoSizeColumn(2);
        cell63.setCellStyle(styleC3);
        HSSFCell cell64 = row6.createCell(3);
        cell64.setCellValue("Ticket No.");
        cell64.setCellStyle(styleC3);
        sheet.autoSizeColumn(3);
        HSSFCell cell65 = row6.createCell(4);
        cell65.setCellValue("Department");
        cell65.setCellStyle(styleC3);
        sheet.autoSizeColumn(4);
        HSSFCell cell66 = row6.createCell(5);
        cell66.setCellValue("Staff");
        cell66.setCellStyle(styleC3);
        sheet.autoSizeColumn(5);
        HSSFCell cell67 = row6.createCell(6);
        cell67.setCellValue("Term Pay");
        cell67.setCellStyle(styleC3);
        sheet.autoSizeColumn(6);
        HSSFCell cell68 = row6.createCell(7);
        cell68.setCellValue("Inv. Amount");
        cell68.setCellStyle(styleC3);
        sheet.autoSizeColumn(7);
        HSSFCell cell69 = row6.createCell(8);
        cell69.setCellValue("Ticket Comm");
        cell69.setCellStyle(styleC3);
        sheet.autoSizeColumn(8);
        HSSFCell cell70 = row6.createCell(9);
        cell70.setCellValue("Sale Price");
        cell70.setCellStyle(styleC3);
        sheet.autoSizeColumn(9);

        HSSFCell cell71 = row6.createCell(10);
        cell71.setCellValue("Agent Comm");
        cell71.setCellStyle(styleC3);
        sheet.autoSizeColumn(10);

        HSSFCell cell72 = row6.createCell(11);
        cell72.setCellValue("Profit");
        cell72.setCellStyle(styleC3);
        sheet.autoSizeColumn(11);

        //Detail of Table
        int count = 9 ;
        for(int i=0;i<TicketAgent.size();i++){
             TicketFareReport data = (TicketFareReport)TicketAgent.get(i);
             HSSFRow row = sheet.createRow(count + i);
             row.createCell(0).setCellValue(data.getInvno());
             row.createCell(1).setCellValue(data.getIssuedate());
             row.createCell(2).setCellValue(data.getAgent());
             row.createCell(3).setCellValue(data.getDocno());
             row.createCell(4).setCellValue(data.getDepartment());
             row.createCell(5).setCellValue(data.getStaff());
             row.createCell(6).setCellValue(data.getTermpay());
             row.createCell(7).setCellValue(data.getInvamount());
             row.createCell(8).setCellValue(data.getTicketcom());
             row.createCell(9).setCellValue(data.getSaleprice());
             row.createCell(10).setCellValue(data.getAgentcom());
             row.createCell(11).setCellValue(data.getProfit());
             if(i == (TicketAgent.size()-1)){
                row = sheet.createRow(count + i + 1);
                row.createCell(6).setCellValue("Total");               
                BigDecimal invAmountTotal = new BigDecimal("0.00");
                BigDecimal ticketCommTotal = new BigDecimal("0.00");
                BigDecimal salePriceTotal = new BigDecimal("0.00");
                BigDecimal agentCommTotal = new BigDecimal("0.00");
                BigDecimal profitTotal = new BigDecimal("0.00");
                for(int k=0;k<TicketAgent.size();k++){
                    TicketFareReport sum = (TicketFareReport)TicketAgent.get(k);
                    BigDecimal invAmount = new BigDecimal(!"".equalsIgnoreCase(sum.getInvamount()) ? sum.getInvamount() : "0.00");
                    BigDecimal ticketComm = new BigDecimal(!"".equalsIgnoreCase(sum.getTicketcom()) ? sum.getTicketcom() : "0.00");
                    BigDecimal salePrice = new BigDecimal(!"".equalsIgnoreCase(sum.getSaleprice()) ? sum.getSaleprice() : "0.00");
                    BigDecimal agentComm = new BigDecimal(!"".equalsIgnoreCase(sum.getAgentcom()) ? sum.getAgentcom() : "0.00");
                    BigDecimal profit = new BigDecimal(!"".equalsIgnoreCase(sum.getProfit()) ? sum.getProfit() : "0.00");
                    invAmountTotal = invAmountTotal.add(invAmount);
                    ticketCommTotal = ticketCommTotal.add(ticketComm);
                    salePriceTotal = salePriceTotal.add(salePrice);
                    agentCommTotal = agentCommTotal.add(agentComm);
                    profitTotal = profitTotal.add(profit);
                }  
                row.createCell(7).setCellValue(String.valueOf(invAmountTotal));
                row.createCell(8).setCellValue(String.valueOf(ticketCommTotal));
                row.createCell(9).setCellValue(String.valueOf(salePriceTotal));
                row.createCell(10).setCellValue(String.valueOf(agentCommTotal));
                row.createCell(11).setCellValue(String.valueOf(profitTotal));
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
        
        if(TicketFare != null){
            dataheader = (TicketFareReport)TicketFare.get(0);
        }
        // set Header Report (Row 1)
        HSSFCellStyle styleC1 = wb.createCellStyle();
        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cell1 = row1.createCell(0);
        cell1.setCellValue("List Ticket Fare Invoice");
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
        
        // Header Table
        HSSFCellStyle styleC3 = wb.createCellStyle();
        styleC3.setFont(getHeaderTable(wb.createFont()));
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
        for(int i=0;i<TicketFare.size();i++){
            TicketFareReport data = (TicketFareReport)TicketFare.get(i);
            HSSFRow row = sheet.createRow(count + i);
            row.createCell(0).setCellValue(data.getInvno());
            row.createCell(1).setCellValue(data.getInvdate());
            row.createCell(2).setCellValue(data.getDepartment());
            row.createCell(3).setCellValue(data.getStaff());
            row.createCell(4).setCellValue(data.getTermpay());
            row.createCell(5).setCellValue(data.getPassenger());
            row.createCell(6).setCellValue(data.getAir());
            row.createCell(7).setCellValue(data.getDocno());
            row.createCell(8).setCellValue(data.getIssuedate());
            row.createCell(9).setCellValue(data.getNetsale());
            row.createCell(10).setCellValue(data.getTax());
            row.createCell(11).setCellValue(data.getIns());
            row.createCell(12).setCellValue(data.getActcom());
            row.createCell(13).setCellValue(data.getInvamount());
             
             
            if(i == (TicketFare.size()-1)){
                row = sheet.createRow(count + i + 1);
                BigDecimal netsalesTotal = new BigDecimal("0.00");
                BigDecimal taxTotal = new BigDecimal("0.00");
                BigDecimal insTotal = new BigDecimal("0.00");
                BigDecimal actcommTotal = new BigDecimal("0.00");
                BigDecimal invamountTotal = new BigDecimal("0.00");
                for(int k=0;k<TicketFare.size();k++){
                    TicketFareReport sum = (TicketFareReport)TicketFare.get(k);
                    BigDecimal netsales = new BigDecimal(!"".equalsIgnoreCase(sum.getNetsale()) ? sum.getNetsale() : "0.00");
                    BigDecimal tax = new BigDecimal(!"".equalsIgnoreCase(sum.getTax()) ? sum.getTax() : "0.00");
                    BigDecimal ins = new BigDecimal(!"".equalsIgnoreCase(sum.getIns()) ? sum.getIns() : "0.00");
                    BigDecimal actcomm = new BigDecimal(!"".equalsIgnoreCase(sum.getActcom()) ? sum.getActcom() : "0.00");
                    BigDecimal invamount = new BigDecimal(!"".equalsIgnoreCase(sum.getInvamount()) ? sum.getInvamount() : "0.00");
                    netsalesTotal = netsalesTotal.add(netsales);
                    taxTotal = taxTotal.add(tax);
                    insTotal = insTotal.add(ins);
                    actcommTotal = actcommTotal.add(actcomm);
                    invamountTotal = invamountTotal.add(invamount);
                }  
                row.createCell(9).setCellValue(String.valueOf(netsalesTotal));
                row.createCell(10).setCellValue(String.valueOf(taxTotal));
                row.createCell(11).setCellValue(String.valueOf(insTotal));
                row.createCell(12).setCellValue(String.valueOf(actcommTotal));
                row.createCell(13).setCellValue(String.valueOf(invamountTotal));
             }
             
             for(int j =0;j<13;j++){
                 sheet.autoSizeColumn(j);
             }
        }
    }
    
    public void genTicketFareAirlineReport(HSSFWorkbook wb, List TicketFare) {

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
             row.createCell(6).setCellValue(data.getTax());
             row.createCell(7).setCellValue(data.getTicketcom());
             row.createCell(8).setCellValue(data.getIns());
             row.createCell(9).setCellValue(data.getSaleprice());
             row.createCell(10).setCellValue(data.getVat());
             row.createCell(11).setCellValue(data.getInvno());
             row.createCell(12).setCellValue(data.getInvamount());
             row.createCell(13).setCellValue(data.getBalance());
             for(int j =0;j<13;j++){
                 sheet.autoSizeColumn(j);
             }
        }
    }
    public void genTicketFareSummaryByStaff(HSSFWorkbook wb, List ticketSumByStaff) {
        String sheetName = "Sheet1";// name of sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        TicketFareSummaryByAgentStaff dataheader = new TicketFareSummaryByAgentStaff();
        
        if(ticketSumByStaff != null){
            dataheader = (TicketFareSummaryByAgentStaff)ticketSumByStaff.get(0);
        }
        // set Header Report (Row 1)
        HSSFCellStyle styleC1 = wb.createCellStyle();
        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cell1 = row1.createCell(0);
        cell1.setCellValue("List Ticket Summary By Staff");
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
        HSSFRow row06 = sheet.createRow(5);
        HSSFCell cell061 = row06.createCell(0);
        cell061.setCellValue("Issue Date : ");
        cell061.setCellStyle(styleC21);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A6:D6"));
        HSSFCell cell062 = row06.createCell(4);
        if(!"".equalsIgnoreCase(dataheader.getIssuefrom())){
        cell062.setCellValue(dataheader.getIssuefrom());
        cell062.setCellStyle(styleC22);
        }
        HSSFCell cell063 = row06.createCell(5);
        if(!"".equalsIgnoreCase(dataheader.getIssueto())){
        cell063.setCellValue("to  " + dataheader.getIssueto());
        cell063.setCellStyle(styleC22);
        }
        
        // Row 7
        HSSFRow row07 = sheet.createRow(6);
        HSSFCell cell071 = row07.createCell(0);
        cell071.setCellValue("Invoice Date : ");
        cell071.setCellStyle(styleC21);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A7:D7"));
        HSSFCell cell072 = row07.createCell(4);
        if(!"".equalsIgnoreCase(dataheader.getInvdatefrom())){
        cell072.setCellValue(dataheader.getInvdatefrom());
        cell072.setCellStyle(styleC22);
        }
        HSSFCell cell073 = row07.createCell(5);
        if(!"".equalsIgnoreCase(dataheader.getInvdateto())){
        cell073.setCellValue("to  " + dataheader.getInvdateto());
        cell073.setCellStyle(styleC22);
        }
        
        // Row 7
        HSSFRow row08 = sheet.createRow(7);
        HSSFCell cell081 = row08.createCell(0);
        cell081.setCellValue("Print on : ");
        cell081.setCellStyle(styleC21);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A8:D8"));
        HSSFCell cell082 = row08.createCell(4);
        cell082.setCellValue(dataheader.getPrinton());
        cell082.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("E8:F8"));
        
//        // Header Table
//        HSSFCellStyle styleC3 = wb.createCellStyle();
//        styleC3.setFont(getHeaderTable(wb.createFont()));
//        HSSFRow row6 = sheet.createRow(8);
//        HSSFCell cell61 = row6.createCell(0);
//        cell61.setCellValue("Inv. No.");
//        cell61.setCellStyle(styleC3);
//        sheet.autoSizeColumn(0);
//        HSSFCell cell62 = row6.createCell(1);
//        cell62.setCellValue("Inv. Date");
//        cell62.setCellStyle(styleC3);
//        sheet.autoSizeColumn(1);
//        HSSFCell cell63 = row6.createCell(2);
//        cell63.setCellValue("Department");
//        sheet.autoSizeColumn(2);
//        cell63.setCellStyle(styleC3);
//        HSSFCell cell64 = row6.createCell(3);
//        cell64.setCellValue("Staff");
//        cell64.setCellStyle(styleC3);
//        sheet.autoSizeColumn(3);
//        HSSFCell cell65 = row6.createCell(4);
//        cell65.setCellValue("Term Pay");
//        cell65.setCellStyle(styleC3);
//        sheet.autoSizeColumn(4);
//        HSSFCell cell66 = row6.createCell(5);
//        cell66.setCellValue("Passenger");
//        cell66.setCellStyle(styleC3);
//        sheet.autoSizeColumn(5);
//        HSSFCell cell67 = row6.createCell(6);
//        cell67.setCellValue("Air");
//        cell67.setCellStyle(styleC3);
//        sheet.autoSizeColumn(6);
//        HSSFCell cell68 = row6.createCell(7);
//        cell68.setCellValue("Document Number");
//        cell68.setCellStyle(styleC3);
//        sheet.autoSizeColumn(7);
//        HSSFCell cell69 = row6.createCell(8);
//        cell69.setCellValue("Issue Date");
//        cell69.setCellStyle(styleC3);
//        sheet.autoSizeColumn(8);
//        HSSFCell cell70 = row6.createCell(9);
//        cell70.setCellValue("Net Sales");
//        cell70.setCellStyle(styleC3);
//        sheet.autoSizeColumn(9);
//        HSSFCell cell71 = row6.createCell(10);
//        cell71.setCellValue("Tax");
//        cell71.setCellStyle(styleC3);
//        sheet.autoSizeColumn(10);
//        HSSFCell cell72 = row6.createCell(11);
//        cell72.setCellValue("Insurance");
//        cell72.setCellStyle(styleC3);
//        sheet.autoSizeColumn(11);
//        HSSFCell cell73 = row6.createCell(12);
//        cell73.setCellValue("Actual Commission");
//        cell73.setCellStyle(styleC3);
//        sheet.autoSizeColumn(12);
//        HSSFCell cell74 = row6.createCell(13);
//        cell74.setCellValue("Inv. Amount");
//        cell74.setCellStyle(styleC3);
//        sheet.autoSizeColumn(13);
        
//        //Detail of Table
//        int count = 9 ;
//        for(int i=0;i<TicketFare.size();i++){
//            TicketFareReport data = (TicketFareReport)TicketFare.get(i);
//            HSSFRow row = sheet.createRow(count + i);
//            row.createCell(0).setCellValue(data.getInvno());
//            row.createCell(1).setCellValue(data.getInvdate());
//            row.createCell(2).setCellValue(data.getDepartment());
//            row.createCell(3).setCellValue(data.getStaff());
//            row.createCell(4).setCellValue(data.getTermpay());
//            row.createCell(5).setCellValue(data.getPassenger());
//            row.createCell(6).setCellValue(data.getAir());
//            row.createCell(7).setCellValue(data.getDocno());
//            row.createCell(8).setCellValue(data.getIssuedate());
//            row.createCell(9).setCellValue(data.getNetsale());
//            row.createCell(10).setCellValue(data.getTax());
//            row.createCell(11).setCellValue(data.getIns());
//            row.createCell(12).setCellValue(data.getActcom());
//            row.createCell(13).setCellValue(data.getInvamount());
//             
//             
//            if(i == (TicketFare.size()-1)){
//                row = sheet.createRow(count + i + 1);
//                BigDecimal netsalesTotal = new BigDecimal("0.00");
//                BigDecimal taxTotal = new BigDecimal("0.00");
//                BigDecimal insTotal = new BigDecimal("0.00");
//                BigDecimal actcommTotal = new BigDecimal("0.00");
//                BigDecimal invamountTotal = new BigDecimal("0.00");
//                for(int k=0;k<TicketFare.size();k++){
//                    TicketFareReport sum = (TicketFareReport)TicketFare.get(k);
//                    BigDecimal netsales = new BigDecimal(!"".equalsIgnoreCase(sum.getNetsale()) ? sum.getNetsale() : "0.00");
//                    BigDecimal tax = new BigDecimal(!"".equalsIgnoreCase(sum.getTax()) ? sum.getTax() : "0.00");
//                    BigDecimal ins = new BigDecimal(!"".equalsIgnoreCase(sum.getIns()) ? sum.getIns() : "0.00");
//                    BigDecimal actcomm = new BigDecimal(!"".equalsIgnoreCase(sum.getActcom()) ? sum.getActcom() : "0.00");
//                    BigDecimal invamount = new BigDecimal(!"".equalsIgnoreCase(sum.getInvamount()) ? sum.getInvamount() : "0.00");
//                    netsalesTotal = netsalesTotal.add(netsales);
//                    taxTotal = taxTotal.add(tax);
//                    insTotal = insTotal.add(ins);
//                    actcommTotal = actcommTotal.add(actcomm);
//                    invamountTotal = invamountTotal.add(invamount);
//                }  
//                row.createCell(9).setCellValue(String.valueOf(netsalesTotal));
//                row.createCell(10).setCellValue(String.valueOf(taxTotal));
//                row.createCell(11).setCellValue(String.valueOf(insTotal));
//                row.createCell(12).setCellValue(String.valueOf(actcommTotal));
//                row.createCell(13).setCellValue(String.valueOf(invamountTotal));
//             }
//             
//             for(int j =0;j<13;j++){
//                 sheet.autoSizeColumn(j);
//             }
//        }
    }
    
    public void genTicketFareSummaryByAgent(HSSFWorkbook wb, List TicketFare) {

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
