/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller.excel.checking.airticket;

import com.smi.travel.controller.excel.master.UtilityExcelFunction;
import com.smi.travel.datalayer.view.entity.RefundTicketView;
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
public class RefundAirsummary extends AbstractExcelView {
    private static final String RefundTicketDetail = "RefundTicketDetail";
    
    @Override
    protected void buildExcelDocument(Map model, HSSFWorkbook workbook,HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = (String) model.get("name");
        System.out.println("name : " + name);
        response.setHeader("Content-disposition", "attachment; filename=" + name + ".xls");
        
        if(name.equalsIgnoreCase(RefundTicketDetail)){
            System.out.println("gen report RefundTicketDetail");
            getRefundTicketDetail(workbook, (List) model.get(name));
        }
        
    }
    
    private void getRefundTicketDetail(HSSFWorkbook wb, List refundTicket) {
        String sheetName = "refund ticket detail";// name of sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        UtilityExcelFunction excelFunction = new UtilityExcelFunction();
        // set Header Report (Row 1)
        HSSFCellStyle styleC11 = wb.createCellStyle();
        HSSFRow row01 = sheet.createRow(0);
        HSSFCell cell01 = row01.createCell(0);
        cell01.setCellValue("List Refund Ticket Detail");
        styleC11.setFont(excelFunction.getHeaderFont(wb.createFont()));
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
        styleAlignRightBorderAllHeaderTable.setFont(excelFunction.getHeaderTable(wb
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
                System.out.println("(listRefund.get(r-9).getReceiveairline() : "+listRefund.get(r-9).getReceiveairline());
                BigDecimal cancel = new BigDecimal((listRefund.get(r-9).getReceiveairline()== null) || ("".equalsIgnoreCase(listRefund.get(r-9).getReceiveairline()))? "0":listRefund.get(r-9).getReceiveairline());
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
}
