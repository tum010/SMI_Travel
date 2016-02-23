/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller.excel.booking;

import com.smi.travel.controller.excel.master.UtilityExcelFunction;
import com.smi.travel.datalayer.entity.PaymentOutbound;
import com.smi.travel.datalayer.report.model.BillAirAgent;
import com.smi.travel.datalayer.report.model.TicketFareSummaryByAgentStaff;
import com.smi.travel.datalayer.view.entity.BookingInvoiceView;
import com.smi.travel.datalayer.view.entity.BookingNonInvoiceView;
import com.smi.travel.datalayer.view.entity.OutboundProductSummaryExcel;
import com.smi.travel.datalayer.view.entity.PaymentOutboundAllDetail;
import com.smi.travel.datalayer.view.entity.PaymentOutboundSummary;
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
public class BookingSummary extends AbstractExcelView  {
    private static final String BookingNonInvoiceSummary = "BookingNonInvoiceSummary";
    private static final String BookingInvoiceSummary = "BookingInvoiceSummary";  
    
    protected void buildExcelDocument(Map model, HSSFWorkbook workbook,HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = (String) model.get("name");
        System.out.println("name : " + name);
        response.setHeader("Content-disposition", "attachment; filename=" + name + ".xls");
        
        if(name.equalsIgnoreCase(BookingInvoiceSummary)){
            System.out.println("gen report BookingInvoice");
            genBookingInvoice(workbook, (List) model.get(name));
        }else if(name.equalsIgnoreCase(BookingNonInvoiceSummary)){
            System.out.println("gen report BookingNonInvoice");
            genBookingNonInvoice(workbook, (List) model.get(name));
        }
    }
    
    public void genBookingNonInvoice(HSSFWorkbook wb, List listBooking) {
        UtilityExcelFunction excelFunction = new UtilityExcelFunction();
        String sheetName = "Sheet1";// name of sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        BookingNonInvoiceView dataheader = new BookingNonInvoiceView();
        
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
        styleC25.setAlignment(styleC25.ALIGN_RIGHT);
 
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
        styleC30.setAlignment(styleC30.ALIGN_CENTER);
        
        if(!listBooking.isEmpty()){
            dataheader = (BookingNonInvoiceView)listBooking.get(0);
        
        // set Header Report (Row 1)
        HSSFCellStyle styleC1 = wb.createCellStyle();
        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cell1 = row1.createCell(0);
        cell1.setCellValue("Booking Non Invoice Summary");
        styleC1.setFont(excelFunction.getHeaderFont(wb.createFont()));
        cell1.setCellStyle(styleC1);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:G1"));
 
        // Row 2
        HSSFRow row2 = sheet.createRow(1);
        HSSFCell cell21 = row2.createCell(0);
        cell21.setCellValue("Owner : ");
        cell21.setCellStyle(styleC21);
        HSSFCell cell22 = row2.createCell(1);
        cell22.setCellValue(dataheader.getHeaderowner());
        cell22.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B2:D2"));
        HSSFCell cell23 = row2.createCell(4);
        cell23.setCellValue("Invoice Sup : ");
        cell23.setCellStyle(styleC21);
        HSSFCell cell24 = row2.createCell(5);
        cell24.setCellValue(dataheader.getHeaderinvoicesup());
        cell24.setCellStyle(styleC22);
        
        // Row 3
        HSSFRow row3 = sheet.createRow(2);
        HSSFCell cell31 = row3.createCell(0);
        cell31.setCellValue("Booking Date : ");
        cell31.setCellStyle(styleC21);
        HSSFCell cell32 = row3.createCell(1);
        cell32.setCellValue(dataheader.getHeaderbookingdate());
        cell32.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B3:D3"));
        HSSFCell cell33 = row3.createCell(4);
        cell33.setCellValue("Pay Date : ");
        cell33.setCellStyle(styleC21);
        HSSFCell cell34 = row3.createCell(5);
        cell34.setCellValue(dataheader.getHeaderpaydate());
        cell34.setCellStyle(styleC22);

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
        String temp = "";
        int count = 4;
        int ktemp = 7;

        for(int i=0;i<listBooking.size();i++){
            BookingNonInvoiceView data = (BookingNonInvoiceView)listBooking.get(i);
            if(!temp.equalsIgnoreCase(data.getInvoicesup())){
                if(!"".equalsIgnoreCase(temp)){
                    count = count+2;
                    ktemp = count+3+i;
                }
                
                int counts = count+i;
                int countss = count+1+i;
                
                // Row Inv Sup
                HSSFRow row000 = sheet.createRow(counts);
                HSSFCell cell001 = row000.createCell(0);
                cell001.setCellValue("Invoice Sup ");
                cell001.setCellStyle(styleC3);
                HSSFCell cell002 = row000.createCell(1);
                cell002.setCellValue(data.getInvoicesup());
                cell002.setCellStyle(styleC29);
                
                HSSFCell cell003 = row000.createCell(2);
                cell003.setCellStyle(styleC29);
                HSSFCell cell004 = row000.createCell(3);
                cell004.setCellStyle(styleC29);
                
                sheet.addMergedRegion(CellRangeAddress.valueOf("B"+(counts+1)+":D"+(counts+1)));
                
                temp = data.getInvoicesup();

                // Header Table
                HSSFRow row09 = sheet.createRow(countss);
                HSSFCell cell091 = row09.createCell(0);
                cell091.setCellValue("Pay No");
                cell091.setCellStyle(styleC3);
                sheet.autoSizeColumn(0);
                HSSFCell cell092 = row09.createCell(1);
                cell092.setCellValue("Pay Date");
                cell092.setCellStyle(styleC3);
                sheet.autoSizeColumn(1);
                HSSFCell cell093 = row09.createCell(2);
                cell093.setCellValue("Ref No");
                sheet.autoSizeColumn(2);
                cell093.setCellStyle(styleC3);
                HSSFCell cell094 = row09.createCell(3);
                cell094.setCellValue("Booking Date");
                cell094.setCellStyle(styleC3);
                sheet.autoSizeColumn(3);
                HSSFCell cell095 = row09.createCell(4);
                cell095.setCellValue("Owner"); 
                cell095.setCellStyle(styleC3);
                sheet.autoSizeColumn(4);
                HSSFCell cell096 = row09.createCell(5);
                cell096.setCellValue("Description"); 
                cell096.setCellStyle(styleC3);
                sheet.autoSizeColumn(5);
                HSSFCell cell097 = row09.createCell(6);
                cell097.setCellValue("Pay Amount");
                cell097.setCellStyle(styleC3);
                sheet.autoSizeColumn(6);
                HSSFCell cell098 = row09.createCell(7);
                cell098.setCellValue("Currency");
                cell098.setCellStyle(styleC3);
                sheet.autoSizeColumn(7);
                HSSFCell cell099 = row09.createCell(8);
                cell099.setCellValue("Price From Billable");
                cell099.setCellStyle(styleC3);
                sheet.autoSizeColumn(8);
                HSSFCell cell100 = row09.createCell(9);
                cell100.setCellValue("Currency");
                cell100.setCellStyle(styleC3);
                sheet.autoSizeColumn(9);
                count = count+2;
            }  
            
            //set data 
            HSSFRow row = sheet.createRow(count + i);
            HSSFCell celldata01 = row.createCell(0);
            celldata01.setCellValue(data.getPayno());
            celldata01.setCellStyle(styleC29);
            HSSFCell celldata02 = row.createCell(1);
            celldata02.setCellValue(data.getPaydate());
            celldata02.setCellStyle(styleC30);
            HSSFCell celldata03 = row.createCell(2);
            celldata03.setCellValue(data.getRefno());
            celldata03.setCellStyle(styleC29);
            HSSFCell celldata04 = row.createCell(3);
            celldata04.setCellValue(data.getBookdate());
            celldata04.setCellStyle(styleC30);
            HSSFCell celldata05 = row.createCell(4);
            celldata05.setCellValue(data.getOwner());
            celldata05.setCellStyle(styleC29);
            HSSFCell celldata06 = row.createCell(5);
            celldata06.setCellValue(data.getDescription());
            celldata06.setCellStyle(styleC29);
            HSSFCell celldata07 = row.createCell(6);
            celldata07.setCellValue("".equalsIgnoreCase(String.valueOf(data.getPayamount())) ? 0 : data.getPayamount().doubleValue()); 
            celldata07.setCellStyle(styleC25);
            HSSFCell celldata08 = row.createCell(7);
            celldata08.setCellValue(data.getCurrency());
            celldata08.setCellStyle(styleC30);
            HSSFCell celldata09 = row.createCell(8);
            celldata09.setCellValue("".equalsIgnoreCase(String.valueOf(data.getSale())) ? 0 : data.getSale().doubleValue()); 
            celldata09.setCellStyle(styleC25);
            HSSFCell celldata10 = row.createCell(9);
            celldata10.setCellValue(data.getSalecurrency());
            celldata10.setCellStyle(styleC30);

        }
        
                
        for(int j =0;j<10;j++){
            sheet.autoSizeColumn(j);
        }
    }
    
    public void genBookingInvoice(HSSFWorkbook wb, List listBooking) {
        UtilityExcelFunction excelFunction = new UtilityExcelFunction();
        String sheetName = "Sheet1";// name of sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        BookingInvoiceView dataheader = new BookingInvoiceView();
        
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
        styleC25.setAlignment(styleC25.ALIGN_RIGHT);
        styleC25.setVerticalAlignment(styleC25.VERTICAL_CENTER);
        
        HSSFCellStyle styleC26 = wb.createCellStyle();
        styleC26.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC26.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC26.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleC26.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC26.setDataFormat(currency.getFormat("#,##0"));
        styleC26.setAlignment(styleC22.ALIGN_CENTER);
        styleC26.setVerticalAlignment(styleC26.VERTICAL_CENTER);
        
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
        styleC29.setWrapText(true);
        styleC29.setVerticalAlignment(styleC29.VERTICAL_CENTER);
        
        HSSFCellStyle styleC30 = wb.createCellStyle();
        styleC30.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC30.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC30.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleC30.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC30.setAlignment(styleC30.ALIGN_CENTER);
        styleC30.setWrapText(true);
        styleC30.setVerticalAlignment(styleC30.VERTICAL_CENTER);
        
        if(!listBooking.isEmpty()){
        dataheader = (BookingInvoiceView)listBooking.get(0);
        
        // set Header Report (Row 1)
        HSSFCellStyle styleC1 = wb.createCellStyle();
        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cell1 = row1.createCell(0);
        cell1.setCellValue("Booking Invoice Summary");
        styleC1.setFont(excelFunction.getHeaderFont(wb.createFont()));
        cell1.setCellStyle(styleC1);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:G1"));
 
        // Row 2
        HSSFRow row2 = sheet.createRow(1);
        HSSFCell cell21 = row2.createCell(0);
        cell21.setCellValue("Owner : ");
        cell21.setCellStyle(styleC21);
        HSSFCell cell22 = row2.createCell(1);
        cell22.setCellValue(dataheader.getHeaderowner());
        cell22.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B2:C2"));
        HSSFCell cell23 = row2.createCell(3);
        cell23.setCellValue("Invoice To : ");
        cell23.setCellStyle(styleC21);
        HSSFCell cell24 = row2.createCell(4);
        cell24.setCellValue(dataheader.getHeaderinvto());
        cell24.setCellStyle(styleC22);
        
        // Row 3
        HSSFRow row3 = sheet.createRow(2);
        HSSFCell cell31 = row3.createCell(0);
        cell31.setCellValue("Booking Date : ");
        cell31.setCellStyle(styleC21);
        HSSFCell cell32 = row3.createCell(1);
        cell32.setCellValue(dataheader.getHeaderbookingdate());
        cell32.setCellStyle(styleC22);
        sheet.addMergedRegion(CellRangeAddress.valueOf("B3:C3"));
        HSSFCell cell33 = row3.createCell(3);
        cell33.setCellValue("Invoice Date : ");
        cell33.setCellStyle(styleC21);
        HSSFCell cell34 = row3.createCell(4);
        cell34.setCellValue(dataheader.getHeaderinvdate());
        cell34.setCellStyle(styleC22);

        }

        // Header Table
        HSSFCellStyle styleC3 = wb.createCellStyle();
        styleC3.setFont(excelFunction.getHeaderTable(wb.createFont()));
        styleC3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC3.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC3.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleC3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC3.setAlignment(styleC3.ALIGN_CENTER);
        
        // Header Table
        HSSFRow row09 = sheet.createRow(4);
        HSSFCell cell091 = row09.createCell(0);
        cell091.setCellValue("Ref No");
        cell091.setCellStyle(styleC3);
        sheet.autoSizeColumn(0);
        HSSFCell cell092 = row09.createCell(1);
        cell092.setCellValue("Booking Date");
        cell092.setCellStyle(styleC3);
        sheet.autoSizeColumn(1);
        HSSFCell cell093 = row09.createCell(2);
        cell093.setCellValue("Owner");
        sheet.autoSizeColumn(2);
        cell093.setCellStyle(styleC3);
        HSSFCell cell094 = row09.createCell(3);
        cell094.setCellValue("Description");
        cell094.setCellStyle(styleC3);
        sheet.autoSizeColumn(3);
        HSSFCell cell095 = row09.createCell(4);
        cell095.setCellValue("Inv No"); 
        cell095.setCellStyle(styleC3);
        sheet.autoSizeColumn(4);
        HSSFCell cell096 = row09.createCell(5);
        cell096.setCellValue("Inv Date"); 
        cell096.setCellStyle(styleC3);
        sheet.autoSizeColumn(5);
        HSSFCell cell097 = row09.createCell(6);
        cell097.setCellValue("Inv To");
        cell097.setCellStyle(styleC3);
        sheet.autoSizeColumn(6);
        HSSFCell cell098 = row09.createCell(7);
        cell098.setCellValue("Cost From Billable");
        cell098.setCellStyle(styleC3);
        sheet.autoSizeColumn(7);
        HSSFCell cell099 = row09.createCell(8);
        cell099.setCellValue("Currency");
        cell099.setCellStyle(styleC3);
        sheet.autoSizeColumn(8);     
        
        
        // Detail of Table
        int count = 5;
        for(int i=0;i<listBooking.size();i++){
            BookingInvoiceView data = (BookingInvoiceView)listBooking.get(i);
            //set data 
            HSSFRow row = sheet.createRow(count + i);
            HSSFCell celldata01 = row.createCell(0);
            celldata01.setCellValue(data.getRefno());
            celldata01.setCellStyle(styleC29);
            HSSFCell celldata02 = row.createCell(1);
            celldata02.setCellValue(data.getBookdate());
            celldata02.setCellStyle(styleC30);
            HSSFCell celldata03 = row.createCell(2);
            celldata03.setCellValue(data.getOwner());
            celldata03.setCellStyle(styleC29);
            HSSFCell celldata04 = row.createCell(3);
            celldata04.setCellValue(data.getDescription());
            celldata04.setCellStyle(styleC29);
            HSSFCell celldata05 = row.createCell(4);
            celldata05.setCellValue(data.getInvno());
            celldata05.setCellStyle(styleC29);
            HSSFCell celldata06 = row.createCell(5);
            celldata06.setCellValue(data.getInvdate());
            celldata06.setCellStyle(styleC29);
            HSSFCell celldata07 = row.createCell(6);
            celldata07.setCellValue(data.getInvto()); 
            celldata07.setCellStyle(styleC29);
            HSSFCell celldata08 = row.createCell(7);
            celldata08.setCellValue("".equalsIgnoreCase(String.valueOf(data.getCost())) ? 0 : data.getCost().doubleValue()); 
            celldata08.setCellStyle(styleC25);
            HSSFCell celldata09 = row.createCell(8);
            celldata09.setCellValue(data.getCurrency());
            celldata09.setCellStyle(styleC30);

        }
        for(int j =0;j<10;j++){
            sheet.autoSizeColumn(j);
        }
        sheet.setColumnWidth(4, 256*15);
        sheet.setColumnWidth(5, 256*15);
    }
    
}
