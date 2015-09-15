/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller.excel;

import com.smi.travel.datalayer.report.model.BillAirAgent;
import com.smi.travel.datalayer.report.model.TicketFareReport;
import com.smi.travel.datalayer.report.model.TicketFareSummaryByAgentStaff;
import java.math.BigDecimal;
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
            System.out.println("gen report TicketFareSummaryByAgent");
            getBillAirAgentReport(workbook, (List) model.get(name));
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
    
    private void getBillAirAgentReport(HSSFWorkbook wb, List BillAirAgent) {
        String sheetName = "Sheet1";// name of sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
       
        // set Header Report (Row 1)
        HSSFCellStyle styleC1 = wb.createCellStyle();
        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cell1 = row1.createCell(0);
        cell1.setCellValue("Bill Agent Air");
        styleC1.setFont(getHeaderFont(wb.createFont()));
        cell1.setCellStyle(styleC1);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:F1"));

        // Set align Text
        HSSFCellStyle styleC21 = wb.createCellStyle();
        styleC21.setAlignment(styleC21.ALIGN_RIGHT);
        HSSFCellStyle styleC22 = wb.createCellStyle();
        styleC22.setAlignment(styleC22.ALIGN_LEFT);
        HSSFCellStyle styleNumber = wb.createCellStyle();
        styleNumber.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
        

        // Row 2
        HSSFRow row2 = sheet.createRow(1);
        HSSFCell cell21 = row2.createCell(0);
            cell21.setCellValue("Agent All : ");
            cell21.setCellStyle(styleC21);
        HSSFCell cell22 = row2.createCell(1);
            cell22.setCellValue("");
            cell22.setCellStyle(styleC22);
            sheet.addMergedRegion(CellRangeAddress.valueOf("B2:D2"));
        HSSFCell cell23 = row2.createCell(4);
            cell23.setCellValue("Print By : ");
            cell23.setCellStyle(styleC21);
        HSSFCell cell24 = row2.createCell(5);
            cell24.setCellValue("");
            cell24.setCellStyle(styleC22);

        // Row 3
        HSSFRow row3 = sheet.createRow(2);
        HSSFCell cell31 = row3.createCell(0);
            cell31.setCellValue("Issue Date : ");
            cell31.setCellStyle(styleC21);
        HSSFCell cell32 = row3.createCell(1);
            cell32.setCellValue("");
            cell32.setCellStyle(styleC22);
            sheet.addMergedRegion(CellRangeAddress.valueOf("B3:D3"));
        HSSFCell cell33 = row3.createCell(4);
            cell33.setCellValue("Page : ");
            cell33.setCellStyle(styleC21);
        HSSFCell cell34 = row3.createCell(5);
            cell34.setCellValue("");
            cell34.setCellStyle(styleC22);

        // Row 4
        HSSFRow row4 = sheet.createRow(3);
        HSSFCell cell41 = row4.createCell(0);
            cell41.setCellValue("Invoice Date : ");
            cell41.setCellStyle(styleC21);
        HSSFCell cell42 = row4.createCell(1);
            cell42.setCellValue("");
            cell42.setCellStyle(styleC22);
            sheet.addMergedRegion(CellRangeAddress.valueOf("B4:D4"));

        // Row 5
        HSSFRow row5 = sheet.createRow(4);
        HSSFCell cell51 = row5.createCell(0);
            cell51.setCellValue("Payment Type : ");
            cell51.setCellStyle(styleC21);
            sheet.addMergedRegion(CellRangeAddress.valueOf("B5:D5"));
        HSSFCell cell52 = row5.createCell(1);
            cell52.setCellValue("");
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
            cell62.setCellValue("Invoice Date");
            cell62.setCellStyle(styleC3);
            sheet.autoSizeColumn(1);
        HSSFCell cell63 = row6.createCell(2);
            cell63.setCellValue("Customer");
            sheet.autoSizeColumn(2);
            cell63.setCellStyle(styleC3);
        HSSFCell cell64 = row6.createCell(3);
            cell64.setCellValue("Ticket No.");
            cell64.setCellStyle(styleC3);
            sheet.autoSizeColumn(3);
        HSSFCell cell65 = row6.createCell(4);
            cell65.setCellValue("Rounting");
            cell65.setCellStyle(styleC3);
            sheet.autoSizeColumn(4);
        HSSFCell cell66 = row6.createCell(5);
            cell66.setCellValue("Sale Price");
            cell66.setCellStyle(styleC3);
            sheet.autoSizeColumn(5);
        HSSFCell cell67 = row6.createCell(6);
            cell67.setCellValue("Net");
            cell67.setCellStyle(styleC3);
            sheet.autoSizeColumn(6);
        HSSFCell cell68 = row6.createCell(7);
            cell68.setCellValue("Service");
            cell68.setCellStyle(styleC3);
            sheet.autoSizeColumn(7);
        HSSFCell cell69 = row6.createCell(8);
            cell69.setCellValue("Vat");
            cell69.setCellStyle(styleC3);
            sheet.autoSizeColumn(8);
        HSSFCell cell70 = row6.createCell(9);
            cell70.setCellValue("Amount Air");
            cell70.setCellStyle(styleC3);
            sheet.autoSizeColumn(9);
        HSSFCell cell71 = row6.createCell(10);
            cell71.setCellValue("Com pay");
            cell71.setCellStyle(styleC3);
            sheet.autoSizeColumn(10);
        HSSFCell cell72 = row6.createCell(11);
            cell72.setCellValue("Vat");
            cell72.setCellStyle(styleC3);
            sheet.autoSizeColumn(11);
        HSSFCell cell73 = row6.createCell(12);
            cell73.setCellValue("Receive");
            cell73.setCellStyle(styleC3);
            sheet.autoSizeColumn(11);
            
        //Detail of Table
        List<BillAirAgent> listAgent = BillAirAgent;
        for (int r = 0 ; r < listAgent.size(); r++) {
            System.out.println("Size " + (r)+" : " + listAgent.get(r).getAgentname() );
        }
        
        int count = 9 + listAgent.size();
        int start = 10;
        int end = 0;
        int num = 0;

	for (int r = 9 ; r < count; r++) {
            if(num <= (listAgent.size()-1)){
                if(num != 0){ // Check not row first
                    String temp = listAgent.get(num-1).getAgentname();
                    if(temp.equals(listAgent.get(num).getAgentname())){ // equal type	
                        System.out.println("Num : " + num + " Last Row : " + (listAgent.size()-1));
                        if(num  != (listAgent.size()-1)){ // check not last row
                            HSSFRow row = sheet.createRow(r);
                            createCell(row,listAgent,num);
                            sheet.autoSizeColumn(13);
                            num++; 
                        }else{ // last row
                            end = r+1;					
                            System.out.println("Num : " + num + " Last Row : " + (listAgent.size()-1));
                            System.out.println("Start : " + start +  " End  : " + end);
                            System.out.println("Last");
                            HSSFRow row = sheet.createRow(r);
                            createCell(row,listAgent,num);                                                    
                            sheet.autoSizeColumn(13);
                            num++;

                            // total
                            int rowstart = r+1;
                            int rowend = r+2;
                            variableTotal(start,end,rowstart,rowend,sheet);
                        }
                    }else{ // not equal type
                        if(num  == (listAgent.size()-1)){ // check  last row
                            end = r+1;					
                            System.out.println("Num : " + num + " Last Row : " + (listAgent.size()-1));
                            System.out.println("Start : " + start +  " End  : " + end);
                            System.out.println("Last");
                            HSSFRow row = sheet.createRow(r);
                            createCell(row,listAgent,num);                                                    
                            sheet.autoSizeColumn(13);
                            num++;
                                // total
                                int rowstart = r+1;
                                int rowend = r+2;
                                variableTotal(start,end,rowstart,rowend,sheet);
                                end = r+1;
                            }else{                                          
                                end = r;					 
                                System.out.println("Start : " + start +  " End  : " + end);
                                System.out.println("Num : " + num + " Last Row : " + (listAgent.size()-1));
                                // total
                                int rowstart = r;
                                int rowend = r+1;
                                variableTotal(start,end,rowstart,rowend,sheet);
                                
                                // Start New Row (Group)
                                start = end + 5;
                                HSSFRow row0 = sheet.createRow(r+3);
                                row0.createCell(0).setCellValue(listAgent.get(num).getAgentname());
                                String add = "A"+(r+4)+":M"+(r+4)+"";
                                System.out.println("Add : " + add);
                                sheet.addMergedRegion(CellRangeAddress.valueOf(add));
                                HSSFRow row12 = sheet.createRow(r+4);
                                createCell(row12,listAgent,num);
                                sheet.autoSizeColumn(13);
                                num++;				 
                                count = count + 4;
                                r = r + 4;
                            }
                        }
                    }else{ // row first
                        System.out.println("Num : " + num + " Last Row : " + (listAgent.size()-1));
                        
                        HSSFRow row0 = sheet.createRow(r);
                        row0.createCell(0).setCellValue(listAgent.get(num).getAgentname());
                        String add = "A"+(r+1)+":M"+(r+1)+"";
                        System.out.println("Add : " + add);
                        sheet.addMergedRegion(CellRangeAddress.valueOf(add));
                        
                        HSSFRow row = sheet.createRow(r+1);
                        createCell(row,listAgent,num); 
                        sheet.autoSizeColumn(13);
                        num = num + 1;
                        count = count + 1;
                        r = r + 1;
                    }
            }
            for(int i =0 ; i < listAgent.size() ; i++){
                sheet.autoSizeColumn(i);
            }
	}
    }
    
    private void variableTotal(int start, int end, int row1, int row2,HSSFSheet sheet){
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
            row.createCell(5).setCellFormula(sumSaleprice);
            row.createCell(7).setCellFormula(sumService);
            row.createCell(9).setCellFormula(sumAmountAir);
            row.createCell(11).setCellFormula(sumVatCompay);
        HSSFRow row11 = sheet.createRow(row2);
            row11.createCell(6).setCellFormula(sumNet);
            row11.createCell(8).setCellFormula(sumVat);
            row11.createCell(10).setCellFormula(sumCompay);
            row11.createCell(12).setCellFormula(sumReceive);
    }
    
    private void createCell(HSSFRow row,List<BillAirAgent> listAgent,int num){
        row.createCell(0).setCellValue(listAgent.get(num).getInvno());
        row.createCell(1).setCellValue(listAgent.get(num).getInvdate());
        row.createCell(2).setCellValue(listAgent.get(num).getCustomer());
        row.createCell(3).setCellValue(listAgent.get(num).getTicketno());
        row.createCell(4).setCellValue(listAgent.get(num).getRounting());
        row.createCell(5).setCellValue(new BigDecimal(listAgent.get(num).getSaleprice()).doubleValue());
        row.createCell(6).setCellValue(new BigDecimal(listAgent.get(num).getNet()).doubleValue());
        row.createCell(7).setCellValue(new BigDecimal(listAgent.get(num).getService()).doubleValue());
        row.createCell(8).setCellValue("");
        row.createCell(9).setCellValue(new BigDecimal(listAgent.get(num).getAmountair()).doubleValue());
        row.createCell(10).setCellValue(new BigDecimal(listAgent.get(num).getCompay()).doubleValue());
        row.createCell(11).setCellValue(new BigDecimal(listAgent.get(num).getCompayvat()).doubleValue());
        row.createCell(12).setCellValue(new BigDecimal(listAgent.get(num).getReceive()).doubleValue()); 
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
        // Set align Text
        HSSFCellStyle styleC21 = wb.createCellStyle();
        styleC21.setAlignment(styleC21.ALIGN_RIGHT);
        HSSFCellStyle styleC22 = wb.createCellStyle();
        styleC22.setAlignment(styleC22.ALIGN_LEFT);
        HSSFCellStyle styleC23 = wb.createCellStyle();
        styleC23.setAlignment(styleC22.ALIGN_CENTER);
        
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
        styleC3.setAlignment(styleC3.ALIGN_CENTER);
        
        // Detail of Table
        String temp = "";
        int count = 9;
        int ktemp = 0 ;
        //Total Outbound
        int totalPaxOut = 0; 
        BigDecimal totalInvAmountOut = new BigDecimal("0.00");
        BigDecimal totalTiccomOut = new BigDecimal("0.00");
        BigDecimal totalSalePriceOut = new BigDecimal("0.00");
        BigDecimal totalAgentCommOut = new BigDecimal("0.00");
        BigDecimal totalProfitOut = new BigDecimal("0.00");
        //Total Wendy
        int totalPaxWendy = 9 ;
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
                    int totalPax = 0;
                    BigDecimal totalInvAmount = new BigDecimal("0.00");
                    BigDecimal totalTiccom = new BigDecimal("0.00");
                    BigDecimal totalSalePrice = new BigDecimal("0.00");
                    BigDecimal totalAgentComm = new BigDecimal("0.00");
                    BigDecimal totalProfit = new BigDecimal("0.00");
                    for(int k=ktemp;k<i;k++){
                        TicketFareSummaryByAgentStaff sum = (TicketFareSummaryByAgentStaff)ticketSumByStaff.get(k);
                        int pax = (!"".equalsIgnoreCase(sum.getPax()) ? Integer.parseInt(sum.getPax()) : 0);
                        BigDecimal invamount = new BigDecimal(!"".equalsIgnoreCase(sum.getInvamount()) ? sum.getInvamount() : "0.00");
                        BigDecimal ticcom = new BigDecimal(!"".equalsIgnoreCase(sum.getTiccom()) ? sum.getTiccom() : "0.00");
                        BigDecimal saleprice = new BigDecimal(!"".equalsIgnoreCase(sum.getSaleprice()) ? sum.getSaleprice() : "0.00");
                        BigDecimal agentcomm = new BigDecimal(!"".equalsIgnoreCase(sum.getAgentcom()) ? sum.getAgentcom() : "0.00");
                        BigDecimal profit = new BigDecimal(!"".equalsIgnoreCase(sum.getProfit()) ? sum.getProfit() : "0.00");
                        totalPax = totalPax+pax;
                        totalInvAmount = totalInvAmount.add(invamount);
                        totalTiccom = totalTiccom.add(ticcom);
                        totalSalePrice = totalSalePrice.add(saleprice);
                        totalAgentComm = totalAgentComm.add(agentcomm);
                        totalProfit = totalProfit.add(profit);
                    }
                    ktemp = i;
                    // Set align Text
                    HSSFCell cellTotal = row.createCell(1);
                    cellTotal.setCellValue("Total");
                    cellTotal.setCellStyle(styleC23);
                    HSSFCell cellTotal02 = row.createCell(2);
                    cellTotal02.setCellValue(String.valueOf(totalPax));
                    cellTotal02.setCellStyle(styleC23);
                    HSSFCell cellTotal03 = row.createCell(3);
                    cellTotal03.setCellValue(String.valueOf(totalInvAmount));
                    cellTotal03.setCellStyle(styleC21);
                    HSSFCell cellTotal04 = row.createCell(4);
                    cellTotal04.setCellValue(String.valueOf(totalTiccom));
                    cellTotal04.setCellStyle(styleC21);
                    HSSFCell cellTotal05 = row.createCell(5);
                    cellTotal05.setCellValue(String.valueOf(totalSalePrice));
                    cellTotal05.setCellStyle(styleC21);
                    HSSFCell cellTotal06 = row.createCell(6);
                    cellTotal06.setCellValue(String.valueOf(totalAgentComm));
                    cellTotal06.setCellStyle(styleC21);
                    HSSFCell cellTotal07 = row.createCell(7);
                    cellTotal07.setCellValue(String.valueOf(totalProfit));
                    cellTotal07.setCellStyle(styleC21);
                    count = count+2;
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
            celldata01.setCellStyle(styleC22);
            HSSFCell celldata02 = row.createCell(1);
            celldata02.setCellValue(data.getDepartment());
            celldata02.setCellStyle(styleC22);
            HSSFCell celldata03 = row.createCell(2);
            celldata03.setCellValue(data.getPax());
            celldata03.setCellStyle(styleC23);
            HSSFCell celldata04 = row.createCell(3);
            celldata04.setCellValue(data.getInvamount());
            celldata04.setCellStyle(styleC21);
            HSSFCell celldata05 = row.createCell(4);
            celldata05.setCellValue(data.getTiccom());
            celldata05.setCellStyle(styleC21);
            HSSFCell celldata06 = row.createCell(5);
            celldata06.setCellValue(data.getSaleprice());
            celldata06.setCellStyle(styleC21);
            HSSFCell celldata07 = row.createCell(6);
            celldata07.setCellValue(data.getAgentcom());
            celldata07.setCellStyle(styleC21);
            HSSFCell celldata08 = row.createCell(7);
            celldata08.setCellValue(data.getProfit());
            celldata08.setCellStyle(styleC21);
            
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
                int totalPax = 0;
                BigDecimal totalInvAmount = new BigDecimal("0.00");
                BigDecimal totalTiccom = new BigDecimal("0.00");
                BigDecimal totalSalePrice = new BigDecimal("0.00");
                BigDecimal totalAgentComm = new BigDecimal("0.00");
                BigDecimal totalProfit = new BigDecimal("0.00");
                for(int k=ktemp;k<ticketSumByStaff.size();k++){
                    TicketFareSummaryByAgentStaff sum = (TicketFareSummaryByAgentStaff)ticketSumByStaff.get(k);
                    int pax = (!"".equalsIgnoreCase(sum.getPax()) ? Integer.parseInt(sum.getPax()) : 0);
                    BigDecimal invamount = new BigDecimal(!"".equalsIgnoreCase(sum.getInvamount()) ? sum.getInvamount() : "0.00");
                    BigDecimal ticcom = new BigDecimal(!"".equalsIgnoreCase(sum.getTiccom()) ? sum.getTiccom() : "0.00");
                    BigDecimal saleprice = new BigDecimal(!"".equalsIgnoreCase(sum.getSaleprice()) ? sum.getSaleprice() : "0.00");
                    BigDecimal agentcomm = new BigDecimal(!"".equalsIgnoreCase(sum.getAgentcom()) ? sum.getAgentcom() : "0.00");
                    BigDecimal profit = new BigDecimal(!"".equalsIgnoreCase(sum.getProfit()) ? sum.getProfit() : "0.00");
                    totalPax = totalPax+pax;
                    totalInvAmount = totalInvAmount.add(invamount);
                    totalTiccom = totalTiccom.add(ticcom);
                    totalSalePrice = totalSalePrice.add(saleprice);
                    totalAgentComm = totalAgentComm.add(agentcomm);
                    totalProfit = totalProfit.add(profit);
                }
                
                HSSFCell celldatas01 = rows.createCell(1);
                celldatas01.setCellValue("Total");
                celldatas01.setCellStyle(styleC23);
                HSSFCell celldatas02 = rows.createCell(2);
                celldatas02.setCellValue(String.valueOf(totalPax));
                celldatas02.setCellStyle(styleC23);
                HSSFCell celldatas03 = rows.createCell(3);
                celldatas03.setCellValue(String.valueOf(totalInvAmount));
                celldatas03.setCellStyle(styleC21);
                HSSFCell celldatas04 = rows.createCell(4);
                celldatas04.setCellValue(String.valueOf(totalTiccom));
                celldatas04.setCellStyle(styleC21);
                HSSFCell celldatas05 = rows.createCell(5);
                celldatas05.setCellValue(String.valueOf(totalSalePrice));
                celldatas05.setCellStyle(styleC21);
                HSSFCell celldatas06 = rows.createCell(6);
                celldatas06.setCellValue(String.valueOf(totalAgentComm));
                celldatas06.setCellStyle(styleC21);
                HSSFCell celldatas07 = rows.createCell(7);
                celldatas07.setCellValue(String.valueOf(totalProfit));
                celldatas07.setCellStyle(styleC21);
                count = count+3;

                rows = sheet.createRow(count+i);
                HSSFCell celldataOut01 = rows.createCell(1);
                celldataOut01.setCellValue("Summary Outbound");
                celldataOut01.setCellStyle(styleC22);
                HSSFCell celldataOut02 = rows.createCell(2);
                celldataOut02.setCellValue(String.valueOf(totalPaxOut));
                celldataOut02.setCellStyle(styleC23);
                HSSFCell celldataOut03 = rows.createCell(3);
                celldataOut03.setCellValue(String.valueOf(totalInvAmountOut));
                celldataOut03.setCellStyle(styleC21);
                HSSFCell celldataOut04 = rows.createCell(4);
                celldataOut04.setCellValue(String.valueOf(totalTiccomOut));
                celldataOut04.setCellStyle(styleC21);
                HSSFCell celldataOut05 = rows.createCell(5);
                celldataOut05.setCellValue(String.valueOf(totalSalePriceOut));
                celldataOut05.setCellStyle(styleC21);
                HSSFCell celldataOut06 = rows.createCell(6);
                celldataOut06.setCellValue(String.valueOf(totalAgentCommOut));
                celldataOut06.setCellStyle(styleC21);
                HSSFCell celldataOut07 = rows.createCell(7);
                celldataOut07.setCellValue(String.valueOf(totalProfitOut));
                celldataOut07.setCellStyle(styleC21);

                rows = sheet.createRow(count+1+i);
                HSSFCell celldataWen01 = rows.createCell(1);
                celldataWen01.setCellValue("Summary Wendy");
                celldataWen01.setCellStyle(styleC22);
                HSSFCell celldataWen02 = rows.createCell(2);
                celldataWen02.setCellValue(String.valueOf(totalPaxWendy));
                celldataWen02.setCellStyle(styleC23);
                HSSFCell celldataWen03 = rows.createCell(3);
                celldataWen03.setCellValue(String.valueOf(totalInvAmountWendy));
                celldataWen03.setCellStyle(styleC21);
                HSSFCell celldataWen04 = rows.createCell(4);
                celldataWen04.setCellValue(String.valueOf(totalTiccomWendy));
                celldataWen04.setCellStyle(styleC21);
                HSSFCell celldataWen05 = rows.createCell(5);
                celldataWen05.setCellValue(String.valueOf(totalSalePriceWendy));
                celldataWen05.setCellStyle(styleC21);
                HSSFCell celldataWen06 = rows.createCell(6);
                celldataWen06.setCellValue(String.valueOf(totalAgentCommWendy));
                celldataWen06.setCellStyle(styleC21);
                HSSFCell celldataWen07 = rows.createCell(7);
                celldataWen07.setCellValue(String.valueOf(totalProfitWendy));
                celldataWen07.setCellStyle(styleC21);

                int totalPaxWI = 0;
                BigDecimal totalInvAmountWI = new BigDecimal("0.00");
                BigDecimal totalTiccomWI = new BigDecimal("0.00");
                BigDecimal totalSalePriceWI = new BigDecimal("0.00");
                BigDecimal totalAgentCommWI = new BigDecimal("0.00");
                BigDecimal totalProfitWI = new BigDecimal("0.00");
                totalPaxWI = totalPaxOut+totalPaxWendy;
                totalInvAmountWI = totalInvAmountOut.add(totalInvAmountWendy);
                totalTiccomWI = totalTiccomOut.add(totalTiccomWendy);
                totalSalePriceWI = totalSalePriceOut.add(totalSalePriceWendy);         
                totalAgentCommWI = totalAgentCommOut.add(totalAgentCommWendy);    
                totalProfitWI = totalProfitOut.add(totalProfitWendy);
                        
                rows = sheet.createRow(count+2+i);
                HSSFCell celldataWI01 = rows.createCell(1);
                celldataWI01.setCellValue("Total");
                celldataWI01.setCellStyle(styleC22);
                HSSFCell celldataWI02 = rows.createCell(2);
                celldataWI02.setCellValue(String.valueOf(totalPaxWI));
                celldataWI02.setCellStyle(styleC23);
                HSSFCell celldataWI03 = rows.createCell(3);
                celldataWI03.setCellValue(String.valueOf(totalInvAmountWI));
                celldataWI03.setCellStyle(styleC21);
                HSSFCell celldataWI04 = rows.createCell(4);
                celldataWI04.setCellValue(String.valueOf(totalTiccomWI));
                celldataWI04.setCellStyle(styleC21);
                HSSFCell celldataWI05 = rows.createCell(5);
                celldataWI05.setCellValue(String.valueOf(totalSalePriceWI));
                celldataWI05.setCellStyle(styleC21);
                HSSFCell celldataWI06 = rows.createCell(6);
                celldataWI06.setCellValue(String.valueOf(totalAgentCommWI));
                celldataWI06.setCellStyle(styleC21);
                HSSFCell celldataWI07 = rows.createCell(7);
                celldataWI07.setCellValue(String.valueOf(totalProfitWI));
                celldataWI07.setCellStyle(styleC21);
                
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
        
        // Set align Text
        HSSFCellStyle styleC21 = wb.createCellStyle();
        styleC21.setAlignment(styleC21.ALIGN_RIGHT);
        HSSFCellStyle styleC22 = wb.createCellStyle();
        styleC22.setAlignment(styleC22.ALIGN_LEFT);
        HSSFCellStyle styleC23 = wb.createCellStyle();
        styleC23.setAlignment(styleC22.ALIGN_CENTER);
        
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
        // Header Table
        HSSFCellStyle styleC3 = wb.createCellStyle();
        styleC3.setFont(getHeaderTable(wb.createFont()));
        styleC3.setAlignment(styleC3.ALIGN_CENTER);
        
        // Detail of Table
        String temp = "";
        int count = 9;
        int ktemp = 0 ;
        //Total Outbound
        int totalPaxOut = 0; 
        BigDecimal totalInvAmountOut = new BigDecimal("0.00");
        BigDecimal totalTiccomOut = new BigDecimal("0.00");
        BigDecimal totalSalePriceOut = new BigDecimal("0.00");
        BigDecimal totalAgentCommOut = new BigDecimal("0.00");
        BigDecimal totalProfitOut = new BigDecimal("0.00");
        //Total Wendy
        int totalPaxWendy = 9 ;
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
                    int totalPax = 0;
                    BigDecimal totalInvAmount = new BigDecimal("0.00");
                    BigDecimal totalTiccom = new BigDecimal("0.00");
                    BigDecimal totalSalePrice = new BigDecimal("0.00");
                    BigDecimal totalAgentComm = new BigDecimal("0.00");
                    BigDecimal totalProfit = new BigDecimal("0.00");
                    for(int k=ktemp;k<i;k++){
                        TicketFareSummaryByAgentStaff sum = (TicketFareSummaryByAgentStaff)ticketSumByAgent.get(k);
                        int pax = (!"".equalsIgnoreCase(sum.getPax()) ? Integer.parseInt(sum.getPax()) : 0);
                        BigDecimal invamount = new BigDecimal(!"".equalsIgnoreCase(sum.getInvamount()) ? sum.getInvamount() : "0.00");
                        BigDecimal ticcom = new BigDecimal(!"".equalsIgnoreCase(sum.getTiccom()) ? sum.getTiccom() : "0.00");
                        BigDecimal saleprice = new BigDecimal(!"".equalsIgnoreCase(sum.getSaleprice()) ? sum.getSaleprice() : "0.00");
                        BigDecimal agentcomm = new BigDecimal(!"".equalsIgnoreCase(sum.getAgentcom()) ? sum.getAgentcom() : "0.00");
                        BigDecimal profit = new BigDecimal(!"".equalsIgnoreCase(sum.getProfit()) ? sum.getProfit() : "0.00");
                        totalPax = totalPax+pax;
                        totalInvAmount = totalInvAmount.add(invamount);
                        totalTiccom = totalTiccom.add(ticcom);
                        totalSalePrice = totalSalePrice.add(saleprice);
                        totalAgentComm = totalAgentComm.add(agentcomm);
                        totalProfit = totalProfit.add(profit);
                    }
                    ktemp = i;
                    // Set align Text
                    HSSFCell cellTotal = row.createCell(1);
                    cellTotal.setCellValue("Total");
                    cellTotal.setCellStyle(styleC23);
                    HSSFCell cellTotal02 = row.createCell(2);
                    cellTotal02.setCellValue(String.valueOf(totalPax));
                    cellTotal02.setCellStyle(styleC23);
                    HSSFCell cellTotal03 = row.createCell(3);
                    cellTotal03.setCellValue(String.valueOf(totalInvAmount));
                    cellTotal03.setCellStyle(styleC21);
                    HSSFCell cellTotal04 = row.createCell(4);
                    cellTotal04.setCellValue(String.valueOf(totalTiccom));
                    cellTotal04.setCellStyle(styleC21);
                    HSSFCell cellTotal05 = row.createCell(5);
                    cellTotal05.setCellValue(String.valueOf(totalSalePrice));
                    cellTotal05.setCellStyle(styleC21);
                    HSSFCell cellTotal06 = row.createCell(6);
                    cellTotal06.setCellValue(String.valueOf(totalAgentComm));
                    cellTotal06.setCellStyle(styleC21);
                    HSSFCell cellTotal07 = row.createCell(7);
                    cellTotal07.setCellValue(String.valueOf(totalProfit));
                    cellTotal07.setCellStyle(styleC21);
                    count = count+2;
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
            celldata01.setCellStyle(styleC22);
            HSSFCell celldata02 = row.createCell(1);
            celldata02.setCellValue(data.getDepartment());
            celldata02.setCellStyle(styleC22);
            HSSFCell celldata03 = row.createCell(2);
            celldata03.setCellValue(data.getPax());
            celldata03.setCellStyle(styleC23);
            HSSFCell celldata04 = row.createCell(3);
            celldata04.setCellValue(data.getInvamount());
            celldata04.setCellStyle(styleC21);
            HSSFCell celldata05 = row.createCell(4);
            celldata05.setCellValue(data.getTiccom());
            celldata05.setCellStyle(styleC21);
            HSSFCell celldata06 = row.createCell(5);
            celldata06.setCellValue(data.getSaleprice());
            celldata06.setCellStyle(styleC21);
            HSSFCell celldata07 = row.createCell(6);
            celldata07.setCellValue(data.getAgentcom());
            celldata07.setCellStyle(styleC21);
            HSSFCell celldata08 = row.createCell(7);
            celldata08.setCellValue(data.getProfit());
            celldata08.setCellStyle(styleC21);
            
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
                int totalPax = 0;
                BigDecimal totalInvAmount = new BigDecimal("0.00");
                BigDecimal totalTiccom = new BigDecimal("0.00");
                BigDecimal totalSalePrice = new BigDecimal("0.00");
                BigDecimal totalAgentComm = new BigDecimal("0.00");
                BigDecimal totalProfit = new BigDecimal("0.00");
                for(int k=ktemp;k<ticketSumByAgent.size();k++){
                    TicketFareSummaryByAgentStaff sum = (TicketFareSummaryByAgentStaff)ticketSumByAgent.get(k);
                    int pax = (!"".equalsIgnoreCase(sum.getPax()) ? Integer.parseInt(sum.getPax()) : 0);
                    BigDecimal invamount = new BigDecimal(!"".equalsIgnoreCase(sum.getInvamount()) ? sum.getInvamount() : "0.00");
                    BigDecimal ticcom = new BigDecimal(!"".equalsIgnoreCase(sum.getTiccom()) ? sum.getTiccom() : "0.00");
                    BigDecimal saleprice = new BigDecimal(!"".equalsIgnoreCase(sum.getSaleprice()) ? sum.getSaleprice() : "0.00");
                    BigDecimal agentcomm = new BigDecimal(!"".equalsIgnoreCase(sum.getAgentcom()) ? sum.getAgentcom() : "0.00");
                    BigDecimal profit = new BigDecimal(!"".equalsIgnoreCase(sum.getProfit()) ? sum.getProfit() : "0.00");
                    totalPax = totalPax+pax;
                    totalInvAmount = totalInvAmount.add(invamount);
                    totalTiccom = totalTiccom.add(ticcom);
                    totalSalePrice = totalSalePrice.add(saleprice);
                    totalAgentComm = totalAgentComm.add(agentcomm);
                    totalProfit = totalProfit.add(profit);
                }
                
                HSSFCell celldatas01 = rows.createCell(1);
                celldatas01.setCellValue("Total");
                celldatas01.setCellStyle(styleC23);
                HSSFCell celldatas02 = rows.createCell(2);
                celldatas02.setCellValue(String.valueOf(totalPax));
                celldatas02.setCellStyle(styleC23);
                HSSFCell celldatas03 = rows.createCell(3);
                celldatas03.setCellValue(String.valueOf(totalInvAmount));
                celldatas03.setCellStyle(styleC21);
                HSSFCell celldatas04 = rows.createCell(4);
                celldatas04.setCellValue(String.valueOf(totalTiccom));
                celldatas04.setCellStyle(styleC21);
                HSSFCell celldatas05 = rows.createCell(5);
                celldatas05.setCellValue(String.valueOf(totalSalePrice));
                celldatas05.setCellStyle(styleC21);
                HSSFCell celldatas06 = rows.createCell(6);
                celldatas06.setCellValue(String.valueOf(totalAgentComm));
                celldatas06.setCellStyle(styleC21);
                HSSFCell celldatas07 = rows.createCell(7);
                celldatas07.setCellValue(String.valueOf(totalProfit));
                celldatas07.setCellStyle(styleC21);
                count = count+3;

                rows = sheet.createRow(count+i);
                HSSFCell celldataOut01 = rows.createCell(1);
                celldataOut01.setCellValue("Summary Outbound");
                celldataOut01.setCellStyle(styleC22);
                HSSFCell celldataOut02 = rows.createCell(2);
                celldataOut02.setCellValue(String.valueOf(totalPaxOut));
                celldataOut02.setCellStyle(styleC23);
                HSSFCell celldataOut03 = rows.createCell(3);
                celldataOut03.setCellValue(String.valueOf(totalInvAmountOut));
                celldataOut03.setCellStyle(styleC21);
                HSSFCell celldataOut04 = rows.createCell(4);
                celldataOut04.setCellValue(String.valueOf(totalTiccomOut));
                celldataOut04.setCellStyle(styleC21);
                HSSFCell celldataOut05 = rows.createCell(5);
                celldataOut05.setCellValue(String.valueOf(totalSalePriceOut));
                celldataOut05.setCellStyle(styleC21);
                HSSFCell celldataOut06 = rows.createCell(6);
                celldataOut06.setCellValue(String.valueOf(totalAgentCommOut));
                celldataOut06.setCellStyle(styleC21);
                HSSFCell celldataOut07 = rows.createCell(7);
                celldataOut07.setCellValue(String.valueOf(totalProfitOut));
                celldataOut07.setCellStyle(styleC21);

                rows = sheet.createRow(count+1+i);
                HSSFCell celldataWen01 = rows.createCell(1);
                celldataWen01.setCellValue("Summary Wendy");
                celldataWen01.setCellStyle(styleC22);
                HSSFCell celldataWen02 = rows.createCell(2);
                celldataWen02.setCellValue(String.valueOf(totalPaxWendy));
                celldataWen02.setCellStyle(styleC23);
                HSSFCell celldataWen03 = rows.createCell(3);
                celldataWen03.setCellValue(String.valueOf(totalInvAmountWendy));
                celldataWen03.setCellStyle(styleC21);
                HSSFCell celldataWen04 = rows.createCell(4);
                celldataWen04.setCellValue(String.valueOf(totalTiccomWendy));
                celldataWen04.setCellStyle(styleC21);
                HSSFCell celldataWen05 = rows.createCell(5);
                celldataWen05.setCellValue(String.valueOf(totalSalePriceWendy));
                celldataWen05.setCellStyle(styleC21);
                HSSFCell celldataWen06 = rows.createCell(6);
                celldataWen06.setCellValue(String.valueOf(totalAgentCommWendy));
                celldataWen06.setCellStyle(styleC21);
                HSSFCell celldataWen07 = rows.createCell(7);
                celldataWen07.setCellValue(String.valueOf(totalProfitWendy));
                celldataWen07.setCellStyle(styleC21);

                int totalPaxWI = 0;
                BigDecimal totalInvAmountWI = new BigDecimal("0.00");
                BigDecimal totalTiccomWI = new BigDecimal("0.00");
                BigDecimal totalSalePriceWI = new BigDecimal("0.00");
                BigDecimal totalAgentCommWI = new BigDecimal("0.00");
                BigDecimal totalProfitWI = new BigDecimal("0.00");
                totalPaxWI = totalPaxOut+totalPaxWendy;
                totalInvAmountWI = totalInvAmountOut.add(totalInvAmountWendy);
                totalTiccomWI = totalTiccomOut.add(totalTiccomWendy);
                totalSalePriceWI = totalSalePriceOut.add(totalSalePriceWendy);         
                totalAgentCommWI = totalAgentCommOut.add(totalAgentCommWendy);    
                totalProfitWI = totalProfitOut.add(totalProfitWendy);
                        
                rows = sheet.createRow(count+2+i);
                HSSFCell celldataWI01 = rows.createCell(1);
                celldataWI01.setCellValue("Total");
                celldataWI01.setCellStyle(styleC22);
                HSSFCell celldataWI02 = rows.createCell(2);
                celldataWI02.setCellValue(String.valueOf(totalPaxWI));
                celldataWI02.setCellStyle(styleC23);
                HSSFCell celldataWI03 = rows.createCell(3);
                celldataWI03.setCellValue(String.valueOf(totalInvAmountWI));
                celldataWI03.setCellStyle(styleC21);
                HSSFCell celldataWI04 = rows.createCell(4);
                celldataWI04.setCellValue(String.valueOf(totalTiccomWI));
                celldataWI04.setCellStyle(styleC21);
                HSSFCell celldataWI05 = rows.createCell(5);
                celldataWI05.setCellValue(String.valueOf(totalSalePriceWI));
                celldataWI05.setCellStyle(styleC21);
                HSSFCell celldataWI06 = rows.createCell(6);
                celldataWI06.setCellValue(String.valueOf(totalAgentCommWI));
                celldataWI06.setCellStyle(styleC21);
                HSSFCell celldataWI07 = rows.createCell(7);
                celldataWI07.setCellValue(String.valueOf(totalProfitWI));
                celldataWI07.setCellStyle(styleC21);
                
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
