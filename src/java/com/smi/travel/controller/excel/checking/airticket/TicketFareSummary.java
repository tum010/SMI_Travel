/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller.excel.checking.airticket;

import com.smi.travel.controller.excel.master.UtilityExcelFunction;
import com.smi.travel.datalayer.report.model.TicketFareReport;
import com.smi.travel.datalayer.report.model.TicketFareSummaryByAgentStaff;
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
public class TicketFareSummary extends AbstractExcelView {
    private static final String TicketFareReport = "TicketFareAirlineReport";
    private static final String TicketFareInvoicReport = "TicketFareInvoicReport";
    private static final String TicketFareAgentReport = "TicketFareAgentReport";
    private static final String TicketFareSummaryByStaff = "TicketFareSummaryByStaff";
    private static final String TicketFareSummaryByAgent = "TicketFareSummaryByAgent";
    
    @Override
    protected void buildExcelDocument(Map model, HSSFWorkbook workbook,HttpServletRequest request, HttpServletResponse response) throws Exception {
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
    
    public void genTicketFareAirlineReport(HSSFWorkbook wb, List TicketFare) {
        UtilityExcelFunction excelFunction = new UtilityExcelFunction();
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
        styleC1.setFont(excelFunction.getHeaderFont(wb.createFont()));
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
        HSSFCell cell52 = row5.createCell(1);
        cell52.setCellValue(dataheader.getHeadstaff());
        cell52.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B5:D5"));
        HSSFCell cell53 = row5.createCell(4);
        cell53.setCellValue("Invoice Date : ");
        cell53.setCellStyle(styleC21);
        HSSFCell cell54 = row5.createCell(5);
        cell54.setCellValue(dataheader.getInvoicedatefrom() + " to " + dataheader.getInvoicedateto());
        cell54.setCellStyle(styleC22);

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
        HSSFCellStyle styleC3Center = wb.createCellStyle();
        styleC3Center.setFont(excelFunction.getHeaderTable(wb.createFont()));
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
        styleC3Right.setFont(excelFunction.getHeaderTable(wb.createFont()));
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
            
//            System.out.println("get size : "+ i);
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
                
                String sumTax =  "SUM(H" + 10+":H"+(count + i + 1)+")";
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

        }
        for(int j =0;j<15;j++){
           sheet.autoSizeColumn(j);
        }
        
        
    }
    
    public void genTicketFareInvoiceReport(HSSFWorkbook wb, List TicketFare) {
        UtilityExcelFunction excelFunction = new UtilityExcelFunction();
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
        styleC1.setFont(excelFunction.getHeaderFont(wb.createFont()));
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
        HSSFCell cell52 = row5.createCell(1);
        cell52.setCellValue(dataheader.getHeadstaff());
        cell52.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B5:D5"));
        HSSFCell cell53 = row5.createCell(4);
        cell53.setCellValue("Invoice Date : ");
        cell53.setCellStyle(styleC21);
        HSSFCell cell54 = row5.createCell(5);
        cell54.setCellValue(dataheader.getInvoicedatefrom() + " to " + dataheader.getInvoicedateto());
        cell54.setCellStyle(styleC22);
        
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
        styleC3.setFont(excelFunction.getHeaderTable(wb.createFont()));
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
        }
        for(int j =0;j<15;j++){
            sheet.autoSizeColumn(j);
        }
    }
    
    private void genTicketFareAgentReport(HSSFWorkbook wb, List TicketAgent) {
        UtilityExcelFunction excelFunction = new UtilityExcelFunction();
        String sheetName = "Sheet1";// name of sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        
        TicketFareReport dataheader = new TicketFareReport();
        
        HSSFDataFormat currency = wb.createDataFormat();
        // Set align Text
        HSSFCellStyle styleC21 = wb.createCellStyle();
        styleC21.setAlignment(styleC21.ALIGN_RIGHT);
        HSSFCellStyle styleC22 = wb.createCellStyle();
        styleC22.setAlignment(styleC22.ALIGN_LEFT);       
        
        if(TicketAgent.size() > 0){
            dataheader = (TicketFareReport)TicketAgent.get(0);
        }

        // set Header Report (Row 1)
        HSSFCellStyle styleC1 = wb.createCellStyle();
        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cellStart = row1.createCell(0);
        cellStart.setCellValue("List Ticket Payment Agent");
        styleC1.setFont(excelFunction.getHeaderFont(wb.createFont()));
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
        HSSFCell cell52 = row5.createCell(1);
        cell52.setCellValue(dataheader.getHeadstaff());
        cell52.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B5:D5"));
        HSSFCell cell53 = row5.createCell(4);
        cell53.setCellValue("Invoice Date : ");
        cell53.setCellStyle(styleC21);
        HSSFCell cell54 = row5.createCell(5);
        cell54.setCellValue(dataheader.getInvoicedatefrom() + " to " + dataheader.getInvoicedateto());
        cell54.setCellStyle(styleC22);
        
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
        HSSFCellStyle styleC3Center = wb.createCellStyle();
        styleC3Center.setFont(excelFunction.getHeaderTable(wb.createFont()));
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
        styleC3Right.setFont(excelFunction.getHeaderTable(wb.createFont()));
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
        }
        for(int j =0;j<15;j++){
            sheet.autoSizeColumn(j);
        }
    }
    
    public void genTicketFareSummaryByStaff(HSSFWorkbook wb, List ticketSumByStaff) {
        UtilityExcelFunction excelFunction = new UtilityExcelFunction();
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
        styleC23.setAlignment(styleC23.ALIGN_CENTER);
        

        
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
        styleC1.setFont(excelFunction.getHeaderFont(wb.createFont()));
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
        styleC3.setFont(excelFunction.getHeaderTable(wb.createFont()));
        styleC3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC3.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC3.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleC3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC3.setAlignment(styleC3.ALIGN_CENTER);
        
        // Detail of Table
        String temp = "XXXXXXXX";
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
                if(!"XXXXXXXX".equalsIgnoreCase(temp)){
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
        }
        for(int j =0;j<8;j++){
            sheet.autoSizeColumn(j);
        }
    }
    
    public void genTicketFareSummaryByAgent(HSSFWorkbook wb, List ticketSumByAgent) {
        UtilityExcelFunction excelFunction = new UtilityExcelFunction();
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
        styleC23.setAlignment(styleC23.ALIGN_CENTER);
        

        
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
            styleC1.setFont(excelFunction.getHeaderFont(wb.createFont()));
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
        styleC3.setFont(excelFunction.getHeaderTable(wb.createFont()));
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
        }
        for(int j =0;j<8;j++){
            sheet.autoSizeColumn(j);
        }
    }
}
