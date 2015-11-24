/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller.excel.checking.airticket;

import com.smi.travel.controller.excel.master.UtilityExcelFunction;
import com.smi.travel.datalayer.view.entity.SummaryAirline;
import com.smi.travel.datalayer.view.entity.SummaryAirlinePaxView;
import com.smi.travel.datalayer.view.entity.TicketSummaryAirlineView;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class AirlineSummary extends AbstractExcelView {
    private static final String SummaryAirline = "SummaryAirline";
    private static final String TicketFareSummaryAirline = "TicketFareSummaryAirline";
    private static final String SummaryAirlinePax = "SummaryAirlinePax";
    
    @Override
    protected void buildExcelDocument(Map model, HSSFWorkbook workbook,HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = (String) model.get("name");
        System.out.println("name : " + name);
        response.setHeader("Content-disposition", "attachment; filename=" + name + ".xls");
        
        if(name.equalsIgnoreCase(SummaryAirline)){
            System.out.println("gen report SummaryAirline");
            getSummaryAirline(workbook, (List) model.get(name));
        }else if(name.equalsIgnoreCase(TicketFareSummaryAirline)){
            System.out.println("gen report TicketFareSummaryAirline");
            genTicketFareSummaryAirlineReport(workbook, (List) model.get(name));
        }else if(name.equalsIgnoreCase(SummaryAirlinePax)){
            System.out.println("gen report SummaryAirlinePax");
            genSummaryAirlinePaxReport(workbook, (List) model.get(name));
        }
        
    }
    
    private void getSummaryAirline(HSSFWorkbook wb, List summaryAirline){
        List<SummaryAirline> listAR = summaryAirline;
        String sheetName = "Sheet1";// name of sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        UtilityExcelFunction excelFunction = new UtilityExcelFunction();
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
        styleC1.setFont(excelFunction.getHeaderFont(wb.createFont()));
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
            styleHeader.setFont(excelFunction.getHeaderTable(wb.createFont()));
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
    
    public void genTicketFareSummaryAirlineReport(HSSFWorkbook wb, List ticketSummaryAirline) {
        String sheetNamePax = "Pax"; // name of sheet
        String sheetNameDetail = "Detail";
        String sheetNameRounting = "Rounting";
        HSSFSheet sheetPax = wb.createSheet(sheetNamePax);
        HSSFSheet sheetDetail = wb.createSheet(sheetNameDetail);
        HSSFSheet sheetRounting = wb.createSheet(sheetNameRounting);
        
        UtilityExcelFunction excelFunction = new UtilityExcelFunction();

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

        
        HSSFCellStyle stylebordertotal = wb.createCellStyle();
        stylebordertotal.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        
        HSSFCellStyle stylebordertotalleft = wb.createCellStyle();
        stylebordertotalleft.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        stylebordertotalleft.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        
        if(!ticketSummaryAirline.isEmpty()){
            dataheader = (TicketSummaryAirlineView)ticketSummaryAirline.get(0);
            for(int x = 1 ; x < 4 ; x++){
                if( x == 1){
                    // set Header Report (Row 1)
                    HSSFCellStyle styleC1 = wb.createCellStyle();
                    HSSFRow row1 = sheetPax.createRow(0);
                    HSSFCell cell1 = row1.createCell(0);
                    cell1.setCellValue("List Summary Airline");
                    styleC1.setFont(excelFunction.getHeaderFont(wb.createFont()));
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
                    styleC1.setFont(excelFunction.getHeaderFont(wb.createFont()));
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
                    styleC1.setFont(excelFunction.getHeaderFont(wb.createFont()));
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
            styleC3.setFont(excelFunction.getHeaderTable(wb.createFont()));
            styleC3.setAlignment(styleC3.ALIGN_CENTER);
            styleC3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            styleC3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            styleC3.setBorderRight(HSSFCellStyle.BORDER_THIN);
            styleC3.setBorderTop(HSSFCellStyle.BORDER_THIN);
            
            int count = 9;
            int tempcount = 0 ;
            int tempcount2 = 0 ;
            int tempcount3 = 0 ;
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
                    cell89.setCellValue("Amount Outbound");
                    cell89.setCellStyle(styleC3);
                    sheetPax.autoSizeColumn(8);
                    HSSFCell cell90 = row8.createCell(9);
                    cell90.setCellValue("Amount Inbound");
                    cell90.setCellStyle(styleC3);
                    sheetPax.autoSizeColumn(9);
                    
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
                    celldata8.setCellValue("".equalsIgnoreCase(String.valueOf(data.getAmountoutboundP())) ? 0 : (new BigDecimal(data.getAmountoutboundP())).doubleValue());
                    celldata8.setCellStyle(styleC25);
                    
                    HSSFCell celldata9= row.createCell(9);
                    celldata9.setCellValue("".equalsIgnoreCase(String.valueOf(data.getAmountinboundP())) ? 0 : (new BigDecimal(data.getAmountinboundP())).doubleValue());
                    celldata9.setCellStyle(styleC25);
                    
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
                    String totalAmountOut = "SUM(I" + 10+":I"+(tempcount)+")";
                    String totalAmountIn = "SUM(J" + 10+":J"+(tempcount)+")";
                    
                    HSSFCellStyle styleTotal = wb.createCellStyle();
                    styleTotal.setFont(excelFunction.getHeaderTable(wb.createFont()));
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
                    cellTotal07.setCellFormula(totalAmountOut);
                    cellTotal07.setCellStyle(styleC25);
                    HSSFCell cellTotal08 = rowtotal.createCell(9);
                    cellTotal08.setCellFormula(totalAmountIn);
                    cellTotal08.setCellStyle(styleC25);
                    
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
                    cell910.setCellValue("Amount Outbound");
                    cell910.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(19);
                    HSSFCell cell911 = row8.createCell(20);
                    cell911.setCellValue("Amount Inbound");
                    cell911.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(20);
                    HSSFCell cell912 = row8.createCell(21);
                    cell912.setCellValue("Amt No Invoice");
                    cell912.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(21);
                    HSSFCell cell913 = row8.createCell(22);
                    cell913.setCellValue("Amt Business Trip");
                    cell913.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(22);
                    HSSFCell cell914 = row8.createCell(23);
                    cell914.setCellValue("Amt Annual Leave");
                    cell914.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(23);
                    HSSFCell cell915 = row8.createCell(24);
                    cell915.setCellValue("Amt Refund");
                    cell915.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(24);
                    HSSFCell cell916 = row8.createCell(25);
                    cell916.setCellValue("Remarks");
                    cell916.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(25);
                    HSSFCell cell917 = row8.createCell(26);
                    cell917.setCellValue("Diff");
                    cell917.setCellStyle(styleC3);
                    sheetDetail.autoSizeColumn(26);
                    
                    
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
                    celldata19.setCellValue("".equalsIgnoreCase(String.valueOf(data.getAmountoutboundD())) ? 0 : (new BigDecimal(data.getAmountoutboundD())).doubleValue());
                    celldata19.setCellStyle(styleC25);

                    HSSFCell celldata20 = row.createCell(20);
                    celldata20.setCellValue("".equalsIgnoreCase(String.valueOf(data.getAmountinboundD())) ? 0 : (new BigDecimal(data.getAmountinboundD())).doubleValue());
                    celldata20.setCellStyle(styleC25);
                    
                    HSSFCell celldata21 = row.createCell(21);
                    celldata21.setCellValue("".equalsIgnoreCase(String.valueOf(data.getAmtnoinvoiceD())) ? 0 : (new BigDecimal(data.getAmtnoinvoiceD())).doubleValue());
                    celldata21.setCellStyle(styleC25);
                    
                    HSSFCell celldata22 = row.createCell(22);
                    celldata22.setCellValue("".equalsIgnoreCase(String.valueOf(data.getAmtbusinesstripD())) ? 0 : (new BigDecimal(data.getAmtbusinesstripD())).doubleValue());
                    celldata22.setCellStyle(styleC25);
                    
                    HSSFCell celldata23 = row.createCell(23);
                    celldata23.setCellValue("".equalsIgnoreCase(String.valueOf(data.getAmtannualleaveD())) ? 0 : (new BigDecimal(data.getAmtannualleaveD())).doubleValue());
                    celldata23.setCellStyle(styleC25);

                    HSSFCell celldata24 = row.createCell(24);
                    celldata24.setCellValue("".equalsIgnoreCase(String.valueOf(data.getAmtrefundD())) ? 0 : (new BigDecimal(data.getAmtrefundD())).doubleValue());
                    celldata24.setCellStyle(styleC25);

                    HSSFCell celldata25 = row.createCell(25);
                    celldata25.setCellValue(String.valueOf(data.getRemarksD()));
                    celldata25.setCellStyle(styleC29);
                    
                    HSSFCell celldata26 = row.createCell(26);
                    celldata26.setCellValue("".equalsIgnoreCase(String.valueOf(data.getDiffD())) ? 0 : (new BigDecimal(data.getDiffD())).doubleValue());
                    celldata26.setCellStyle(styleC25);
                    x ++ ;
                }
                
                if("routing".equalsIgnoreCase(data.getPage())){
                    tempcount2 = count+x;
                    HSSFRow rowtotal = sheetDetail.createRow(tempcount2);
                    String totalPax = "SUM(J" + 10+":J"+(tempcount2)+")";
                    String totalNet = "SUM(O" + 10+":O"+(tempcount2)+")";
                    String totalTax = "SUM(P" + 10+":P"+(tempcount2)+")";
                    String totalIns = "SUM(Q" + 10+":Q"+(tempcount2)+")";
                    String totalComms = "SUM(R" + 10+":R"+(tempcount2)+")";
                    String totalAmountWen = "SUM(S" + 10+":S"+(tempcount2)+")";
                    String totalAmountOut = "SUM(T" + 10+":T"+(tempcount2)+")";
                    String totalAmountIn = "SUM(U" + 10+":U"+(tempcount2)+")";
                    String totalAmountNoInv = "SUM(V" + 10+":V"+(tempcount2)+")";
                    String totalAmountBuss = "SUM(W" + 10+":W"+(tempcount2)+")";
                    String totalAmountAnn = "SUM(X" + 10+":X"+(tempcount2)+")";
                    String totalAmountRef = "SUM(Z" + 10+":Z"+(tempcount2)+")";
                    String totalAmountDiff = "SUM(AA" + 10+":AA"+(tempcount2)+")";
                    
                    HSSFCellStyle styleTotal = wb.createCellStyle();
                    styleTotal.setFont(excelFunction.getHeaderTable(wb.createFont()));
                    styleTotal.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                    styleTotal.setBorderRight(HSSFCellStyle.BORDER_THIN);
                    styleTotal.setBorderTop(HSSFCellStyle.BORDER_THIN);
                    styleTotal.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                    styleTotal.setAlignment(styleC22.ALIGN_RIGHT);

                    HSSFCell cellTotal000 = rowtotal.createCell(0);
                    cellTotal000.setCellStyle(stylebordertotalleft);
                    
                    HSSFCell cellTotal001 = rowtotal.createCell(1);
                    cellTotal001.setCellStyle(stylebordertotal);
                    HSSFCell cellTotal002 = rowtotal.createCell(2);
                    cellTotal002.setCellStyle(stylebordertotal);
                    HSSFCell cellTotal003 = rowtotal.createCell(3);
                    cellTotal003.setCellStyle(stylebordertotal);
                    HSSFCell cellTotal004 = rowtotal.createCell(4);
                    cellTotal004.setCellStyle(stylebordertotal);
                    HSSFCell cellTotal005 = rowtotal.createCell(5);
                    cellTotal005.setCellStyle(stylebordertotal);
                    HSSFCell cellTotal006 = rowtotal.createCell(6);
                    cellTotal006.setCellStyle(stylebordertotal);
                    HSSFCell cellTotal007 = rowtotal.createCell(7);
                    cellTotal007.setCellStyle(stylebordertotal);
                    HSSFCell cellTotal008 = rowtotal.createCell(10);
                    cellTotal008.setCellStyle(stylebordertotal);
                    HSSFCell cellTotal009 = rowtotal.createCell(11);
                    cellTotal009.setCellStyle(stylebordertotal);
                    HSSFCell cellTotal010 = rowtotal.createCell(12);
                    cellTotal010.setCellStyle(stylebordertotal);
                    HSSFCell cellTotal011 = rowtotal.createCell(13);
                    cellTotal011.setCellStyle(stylebordertotal);
                    HSSFCell cellTotal012 = rowtotal.createCell(25);
                    cellTotal012.setCellStyle(stylebordertotal);
                   

                    HSSFCell cellTotal00 = rowtotal.createCell(8);
                    cellTotal00.setCellValue("Total");
                    cellTotal00.setCellStyle(styleTotal);
                    HSSFCell cellTotal01 = rowtotal.createCell(9);
                    cellTotal01.setCellFormula(totalPax);
                    cellTotal01.setCellStyle(styleC26);
                    HSSFCell cellTotal02 = rowtotal.createCell(14);
                    cellTotal02.setCellFormula(totalNet);
                    cellTotal02.setCellStyle(styleC25);
                    HSSFCell cellTotal03 = rowtotal.createCell(15);
                    cellTotal03.setCellFormula(totalTax);
                    cellTotal03.setCellStyle(styleC25);
                    HSSFCell cellTotal04 = rowtotal.createCell(16);
                    cellTotal04.setCellFormula(totalIns);
                    cellTotal04.setCellStyle(styleC25);
                    HSSFCell cellTotal05 = rowtotal.createCell(17);
                    cellTotal05.setCellFormula(totalComms);
                    cellTotal05.setCellStyle(styleC25);
                    HSSFCell cellTotal06 = rowtotal.createCell(18);
                    cellTotal06.setCellFormula(totalAmountWen);
                    cellTotal06.setCellStyle(styleC25);
                    HSSFCell cellTotal07 = rowtotal.createCell(19);
                    cellTotal07.setCellFormula(totalAmountOut);
                    cellTotal07.setCellStyle(styleC25);
                    HSSFCell cellTotal08 = rowtotal.createCell(20);
                    cellTotal08.setCellFormula(totalAmountIn);
                    cellTotal08.setCellStyle(styleC25);
                    HSSFCell cellTotal09 = rowtotal.createCell(21);
                    cellTotal09.setCellFormula(totalAmountNoInv);
                    cellTotal09.setCellStyle(styleC25);
                    HSSFCell cellTotal10 = rowtotal.createCell(22);
                    cellTotal10.setCellFormula(totalAmountBuss);
                    cellTotal10.setCellStyle(styleC25);
                    HSSFCell cellTotal11 = rowtotal.createCell(23);
                    cellTotal11.setCellFormula(totalAmountAnn);
                    cellTotal11.setCellStyle(styleC25);
                    HSSFCell cellTotal12 = rowtotal.createCell(24);
                    cellTotal12.setCellFormula(totalAmountRef);
                    cellTotal12.setCellStyle(styleC25);
                    HSSFCell cellTotal13 = rowtotal.createCell(26);
                    cellTotal13.setCellFormula(totalAmountDiff);
                    cellTotal13.setCellStyle(styleC25);
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
                    cell88.setCellValue("Amount Outbound");
                    cell88.setCellStyle(styleC3);
                    sheetRounting.autoSizeColumn(7);
                    HSSFCell cell89 = row8.createCell(8);
                    cell89.setCellValue("Amount Inbound");
                    cell89.setCellStyle(styleC3);
                    sheetRounting.autoSizeColumn(8);
                    HSSFCell cell90 = row8.createCell(9);
                    cell90.setCellValue("Diff");
                    cell90.setCellStyle(styleC3);
                    sheetRounting.autoSizeColumn(9);
                    
                    
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
                    celldata7.setCellValue("".equalsIgnoreCase(String.valueOf(data.getAmountoutboundR())) ? 0 : (new BigDecimal(data.getAmountoutboundR())).doubleValue());
                    celldata7.setCellStyle(styleC25);

                    HSSFCell celldata8 = row.createCell(8);
                    celldata8.setCellValue("".equalsIgnoreCase(String.valueOf(data.getAmountinboundR())) ? 0 : (new BigDecimal(data.getAmountinboundR())).doubleValue());
                    celldata8.setCellStyle(styleC25);
                    
                    HSSFCell celldata9 = row.createCell(9);
                    celldata9.setCellValue("".equalsIgnoreCase(String.valueOf(data.getDiffR())) ? 0 : (new BigDecimal(data.getDiffR())).doubleValue());
                    celldata9.setCellStyle(styleC25);
                    y++;
                }
                
                if(i==(ticketSummaryAirline.size()-1)){
                    tempcount3 = count+y;
                    HSSFRow rowtotal = sheetRounting.createRow(tempcount3);
                    String totalPax = "SUM(B" + 10+":B"+(tempcount3)+")";
                    String totalNet = "SUM(C" + 10+":C"+(tempcount3)+")";
                    String totalTax = "SUM(D" + 10+":D"+(tempcount3)+")";
                    String totalIns = "SUM(E" + 10+":E"+(tempcount3)+")";
                    String totalComms = "SUM(F" + 10+":F"+(tempcount3)+")";
                    String totalAmountWen = "SUM(G" + 10+":G"+(tempcount3)+")";
                    String totalAmountOut = "SUM(H" + 10+":H"+(tempcount3)+")";
                    String totalAmountIn = "SUM(I" + 10+":I"+(tempcount3)+")";
                    String totalAmountDiff = "SUM(J" + 10+":J"+(tempcount3)+")";
                    
                    HSSFCellStyle styleTotal = wb.createCellStyle();
                    styleTotal.setFont(excelFunction.getHeaderTable(wb.createFont()));
                    styleTotal.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                    styleTotal.setBorderRight(HSSFCellStyle.BORDER_THIN);
                    styleTotal.setBorderTop(HSSFCellStyle.BORDER_THIN);
                    styleTotal.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                    styleTotal.setAlignment(styleC22.ALIGN_RIGHT);

                    HSSFCell cellTotal00 = rowtotal.createCell(0);
                    cellTotal00.setCellValue("Total");
                    cellTotal00.setCellStyle(styleTotal);
                    HSSFCell cellTotal01 = rowtotal.createCell(1);
                    cellTotal01.setCellFormula(totalPax);
                    cellTotal01.setCellStyle(styleC26);
                    HSSFCell cellTotal02 = rowtotal.createCell(2);
                    cellTotal02.setCellFormula(totalNet);
                    cellTotal02.setCellStyle(styleC25);
                    HSSFCell cellTotal03 = rowtotal.createCell(3);
                    cellTotal03.setCellFormula(totalTax);
                    cellTotal03.setCellStyle(styleC25);
                    HSSFCell cellTotal04 = rowtotal.createCell(4);
                    cellTotal04.setCellFormula(totalIns);
                    cellTotal04.setCellStyle(styleC25);
                    HSSFCell cellTotal05 = rowtotal.createCell(5);
                    cellTotal05.setCellFormula(totalComms);
                    cellTotal05.setCellStyle(styleC25);
                    HSSFCell cellTotal06 = rowtotal.createCell(6);
                    cellTotal06.setCellFormula(totalAmountWen);
                    cellTotal06.setCellStyle(styleC25);
                    HSSFCell cellTotal07 = rowtotal.createCell(7);
                    cellTotal07.setCellFormula(totalAmountOut);
                    cellTotal07.setCellStyle(styleC25);
                    HSSFCell cellTotal08 = rowtotal.createCell(8);
                    cellTotal08.setCellFormula(totalAmountIn);
                    cellTotal08.setCellStyle(styleC25);
                    HSSFCell cellTotal09 = rowtotal.createCell(9);
                    cellTotal09.setCellFormula(totalAmountDiff);
                    cellTotal09.setCellStyle(styleC25);
                }
            }
            for (int i = 0; i < 30; i++) {
                sheetPax.autoSizeColumn(i);
                sheetDetail.autoSizeColumn(i);
                sheetRounting.autoSizeColumn(i);
            }
            
            sheetDetail.setColumnWidth(15, 256*12);
            sheetDetail.setColumnWidth(16, 256*12);
            
            sheetPax.setColumnWidth(5, 256*12);
            
            sheetRounting.setColumnWidth(5, 256*12);
            sheetRounting.setColumnWidth(9, 256*12);
        }
    }
    
    public void genSummaryAirlinePaxReport(HSSFWorkbook wb, List summaryAirlinePax) {
        String sheetNameInv = "Invoice"; // name of sheet
        String sheetNameDetail = "Detail";
        HSSFSheet sheetInv = wb.createSheet(sheetNameInv);
        HSSFSheet sheetDetail = wb.createSheet(sheetNameDetail);
        UtilityExcelFunction excelFunction = new UtilityExcelFunction();

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
                    styleC1.setFont(excelFunction.getHeaderFont(wb.createFont()));
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
                    styleC1.setFont(excelFunction.getHeaderFont(wb.createFont()));
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
            styleC3.setFont(excelFunction.getHeaderTable(wb.createFont()));
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
                    cell83.setCellValue("Amount Outbound");
                    sheetInv.autoSizeColumn(2);
                    cell83.setCellStyle(styleC3);
                    HSSFCell cell84 = row8.createCell(3);
                    cell84.setCellValue("Amount Inbound");
                    sheetInv.autoSizeColumn(3);
                    cell84.setCellStyle(styleC3);
                    
                    HSSFRow row = sheetInv.createRow(count + i);
                    HSSFCell celldata0 = row.createCell(0);
                    celldata0.setCellValue(String.valueOf(data.getInvnoPax()));
                    celldata0.setCellStyle(styleC26);

                    HSSFCell celldata1 = row.createCell(1);
                    celldata1.setCellValue("".equalsIgnoreCase(String.valueOf(data.getAmountwendy())) ? 0 : (new BigDecimal(data.getAmountwendy())).doubleValue());
                    celldata1.setCellStyle(styleC25);

                    HSSFCell celldata2 = row.createCell(2);
                    celldata2.setCellValue("".equalsIgnoreCase(String.valueOf(data.getAmountoutbound())) ? 0 : (new BigDecimal(data.getAmountoutbound())).doubleValue());
                    celldata2.setCellStyle(styleC25);
                    
                    HSSFCell celldata3 = row.createCell(3);
                    celldata3.setCellValue("".equalsIgnoreCase(String.valueOf(data.getAmountinbound())) ? 0 : (new BigDecimal(data.getAmountinbound())).doubleValue());
                    celldata3.setCellStyle(styleC25);
                    
                    tempcount = count + i + 1;
                }
                //detail
                if("detail".equalsIgnoreCase(data.getPage())){
                    //Total inv
                    HSSFRow rowtotal = sheetInv.createRow(tempcount);
                    String totalwendy = "SUM(B" + 10+":B"+(tempcount)+")";
                    String totaloutbound = "SUM(C" + 10+":C"+(tempcount)+")";
                    String totalinbound = "SUM(D" + 10+":D"+(tempcount)+")";
                    
                    HSSFCellStyle styleTotal = wb.createCellStyle();
                    styleTotal.setFont(excelFunction.getHeaderTable(wb.createFont()));
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
                    cellTotal02.setCellFormula(totaloutbound);
                    cellTotal02.setCellStyle(styleC25);
                    HSSFCell cellTotal03 = rowtotal.createCell(3);
                    cellTotal03.setCellFormula(totalinbound);
                    cellTotal03.setCellStyle(styleC25);
                    
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
                    celldata11.setCellValue("".equalsIgnoreCase(String.valueOf(data.getNoinvamount())) ? 0 : (new BigDecimal(data.getNoinvamount())).doubleValue());
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
            
            sheetDetail.setColumnWidth(5, 256*12);
            sheetDetail.setColumnWidth(6, 256*12);
            sheetDetail.setColumnWidth(7, 256*12);
            sheetDetail.setColumnWidth(8, 256*12);
            sheetDetail.setColumnWidth(9, 256*12);
            sheetDetail.setColumnWidth(10, 256*12);
            sheetDetail.setColumnWidth(13, 256*12);
        }
    }
}

    

}
