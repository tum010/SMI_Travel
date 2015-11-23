/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller.excel.account;

import com.smi.travel.controller.excel.master.UtilityExcelFunction;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.APNirvana;
import com.smi.travel.datalayer.view.entity.ARNirvana;
import com.smi.travel.datalayer.view.entity.CollectionNirvana;
import com.smi.travel.datalayer.view.entity.OutputTaxView;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/**
 *
 * @author Jittima
 */
public class AccountReportSummary extends AbstractExcelView {
    private static final String ChangeARReport = "ChangeARReport";
    private static final String CollectionReport = "CollectionReport";
    private static final String ApReport = "ApReport";
    private static final String SaleVatReport = "SaleVatReport";
//    private UtilityService utilityService;
    
    @Override
    protected void buildExcelDocument(Map model, HSSFWorkbook workbook,HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = (String) model.get("name");
        System.out.println("name : " + name);
        response.setHeader("Content-disposition", "attachment; filename=" + name + ".xls");
        
        if(name.equalsIgnoreCase(ChangeARReport)){
            System.out.println("gen report ChangeARReport");
            getChangeARReport(workbook, (List) model.get(name));
        }else if(name.equalsIgnoreCase(CollectionReport)){
            System.out.println("gen report CollectionReport");
            genCollectionReport(workbook, (List) model.get(name));
        }else if(name.equalsIgnoreCase(ApReport)){
            System.out.println("gen report ApReport");
            getApReport(workbook, (List) model.get(name));
        }else if(name.equalsIgnoreCase(SaleVatReport)){
            System.out.println("gen report SaleVatReport");
            genSaleVatReport(workbook, (List) model.get(name));
        }
    
    
    }
    
    public void getChangeARReport(HSSFWorkbook wb, List changeARReport){
        List<ARNirvana> listAR = changeARReport;
        String sheetName = "Sheet1";// name of sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        UtilityExcelFunction excelFunction = new UtilityExcelFunction();
        // set Header Report (Row 1)
        HSSFCellStyle styleC1 = wb.createCellStyle();
        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cell1 = row1.createCell(1);
        cell1.setCellValue("CHANGE AR REPORT");
        styleC1.setFont(excelFunction.getHeaderFont(wb.createFont()));
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
    
    public void genCollectionReport(HSSFWorkbook wb, List collectionNirvanaList) {
      String sheetName = "Sheet1";// name of sheet
      HSSFSheet sheet = wb.createSheet(sheetName);
      CollectionNirvana dataheader = new CollectionNirvana();
      UtilityExcelFunction excelFunction = new UtilityExcelFunction();
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
      styleC25.setWrapText(true);
      styleC25.setAlignment(styleC25.ALIGN_RIGHT);
      styleC25.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
      
      HSSFCellStyle styleC26 = wb.createCellStyle();
      styleC26.setBorderLeft(HSSFCellStyle.BORDER_THIN);
      styleC26.setBorderRight(HSSFCellStyle.BORDER_THIN);
      styleC26.setBorderTop(HSSFCellStyle.BORDER_THIN);
      styleC26.setBorderBottom(HSSFCellStyle.BORDER_THIN);
      styleC26.setDataFormat(currency.getFormat("#,##0"));
      styleC26.setAlignment(styleC26.ALIGN_CENTER);
      styleC26.setWrapText(true);
      styleC26.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
      
      HSSFCellStyle styleC27 = wb.createCellStyle();
      styleC27.setAlignment(styleC27.ALIGN_RIGHT);
      styleC27.setDataFormat(currency.getFormat("#,##0.00"));
      styleC27.setWrapText(true);
      styleC27.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
      
      HSSFCellStyle styleC28 = wb.createCellStyle();
      styleC28.setAlignment(styleC28.ALIGN_CENTER);
      styleC28.setDataFormat(currency.getFormat("#,##0"));
      styleC28.setWrapText(true);
      styleC28.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
      
      HSSFCellStyle styleC29 = wb.createCellStyle();
      styleC29.setBorderLeft(HSSFCellStyle.BORDER_THIN);
      styleC29.setBorderRight(HSSFCellStyle.BORDER_THIN);
      styleC29.setBorderTop(HSSFCellStyle.BORDER_THIN);
      styleC29.setBorderBottom(HSSFCellStyle.BORDER_THIN);
      styleC29.setWrapText(true);
      styleC29.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

      HSSFCellStyle styleC30 = wb.createCellStyle();
      styleC30.setBorderLeft(HSSFCellStyle.BORDER_THIN);
      styleC30.setBorderRight(HSSFCellStyle.BORDER_THIN);
      styleC30.setBorderTop(HSSFCellStyle.BORDER_THIN);
      styleC30.setBorderBottom(HSSFCellStyle.BORDER_THIN);
      styleC30.setAlignment(styleC30.ALIGN_CENTER);
      styleC30.setWrapText(true);
      styleC30.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

      if(!collectionNirvanaList.isEmpty()){
          dataheader = (CollectionNirvana)collectionNirvanaList.get(0);

          // set Header Report (Row 1)
          HSSFCellStyle styleC1 = wb.createCellStyle();
          HSSFRow row1 = sheet.createRow(0);
          HSSFCell cell1 = row1.createCell(0);
          cell1.setCellValue("COLLECTION REPORT");
          styleC1.setFont(excelFunction.getHeaderFont(wb.createFont()));
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
      styleC3.setFont(excelFunction.getHeaderTable(wb.createFont()));
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
      cell62.setCellValue("Receipt No");
      cell62.setCellStyle(styleC3);
      sheet.autoSizeColumn(1);
      HSSFCell cell63 = row6.createCell(2);
      cell63.setCellValue("Inv No");
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
      cell68.setCellValue("Sum Inv");
      cell68.setCellStyle(styleC3);
      sheet.autoSizeColumn(7);
      HSSFCell cell69 = row6.createCell(8);
      cell69.setCellValue("Diff");
      cell69.setCellStyle(styleC3);
      sheet.autoSizeColumn(8);
      HSSFCell cell70 = row6.createCell(9);
      cell70.setCellValue("Sum Rec");
      cell70.setCellStyle(styleC3);
      sheet.autoSizeColumn(9);
      HSSFCell cell71 = row6.createCell(10);
      cell71.setCellValue("Cur");
      cell71.setCellStyle(styleC3);
      sheet.autoSizeColumn(10);
      HSSFCell cell72 = row6.createCell(11);
      cell72.setCellValue("Withholding Tax");
      cell72.setCellStyle(styleC3);
      sheet.autoSizeColumn(11);
      HSSFCell cell73 = row6.createCell(12);
      cell73.setCellValue("Pay By");
      cell73.setCellStyle(styleC3);
      sheet.autoSizeColumn(12);
      HSSFCell cell74 = row6.createCell(13);
      cell74.setCellValue("Status");
      cell74.setCellStyle(styleC3);
      sheet.autoSizeColumn(13);
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
          celldata1.setCellValue(String.valueOf(data.getRecno()));
          celldata1.setCellStyle(styleC29);

          HSSFCell celldata2 = row.createCell(2);
          celldata2.setCellValue(String.valueOf(data.getInvno()));
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
          celldata6.setCellValue(String.valueOf(data.getInvoiceamount()));
          celldata6.setCellStyle(styleC25);

          HSSFCell celldata7 = row.createCell(7);
          celldata7.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInvamount())) ? 0 : (data.getInvamount()).doubleValue());
          celldata7.setCellStyle(styleC25);

          HSSFCell celldata8 = row.createCell(8);
          celldata8.setCellValue("".equalsIgnoreCase(String.valueOf(data.getDiff())) ? 0 : (data.getDiff()).doubleValue());
          celldata8.setCellStyle(styleC25);

          HSSFCell celldata9 = row.createCell(9);
          celldata9.setCellValue("".equalsIgnoreCase(String.valueOf(data.getRecamount())) ? 0 : (data.getRecamount()).doubleValue());
          celldata9.setCellStyle(styleC25);

          HSSFCell celldata10 = row.createCell(10);
          celldata10.setCellValue(String.valueOf(data.getCur()));
          celldata10.setCellStyle(styleC30);
           
          HSSFCell celldata11 = row.createCell(11);
          celldata11.setCellValue("".equalsIgnoreCase(String.valueOf(data.getWithtax())) ? 0 : (data.getWithtax()).doubleValue());
          celldata11.setCellStyle(styleC25);
          
          HSSFCell celldata12 = row.createCell(12);
          celldata12.setCellValue(String.valueOf(data.getPayby()));
          celldata12.setCellStyle(styleC29);
          
          HSSFCell celldata13 = row.createCell(13);
          celldata13.setCellValue(String.valueOf(data.getCollectionStatus()));
          celldata13.setCellStyle(styleC30);
          
        if(i == (collectionNirvanaList.size()-1)){
            row = sheet.createRow(count + i + 3);
            HSSFCellStyle styleSum = wb.createCellStyle();
            styleSum.setAlignment(styleSum.ALIGN_RIGHT);
            styleSum.setDataFormat(currency.getFormat("#,##0.00"));

            HSSFCell cellTotal00 = row.createCell(1);
            cellTotal00.setCellValue("Cash : ");
            cellTotal00.setCellStyle(styleSum);                
            HSSFCell cellTotal01 = row.createCell(2);
            cellTotal01.setCellValue("".equalsIgnoreCase(String.valueOf(data.getCash())) ? 0 : (data.getCash()).doubleValue());
            cellTotal01.setCellStyle(styleSum);
            HSSFCell cellTotal000 = row.createCell(11);
            cellTotal000.setCellValue("Total Amount : ");
            cellTotal000.setCellStyle(styleSum);                
            HSSFCell cellTotal001 = row.createCell(12);
            cellTotal001.setCellValue("".equalsIgnoreCase(String.valueOf(data.getTotalamount())) ? 0 : (data.getTotalamount()).doubleValue());
            cellTotal001.setCellStyle(styleSum);
            
            row = sheet.createRow(count + i + 4);
            HSSFCell cellTotal02 = row.createCell(1);
            cellTotal02.setCellValue("Chq : ");
            cellTotal02.setCellStyle(styleSum);
            HSSFCell cellTotal03 = row.createCell(2);
            cellTotal03.setCellValue("".equalsIgnoreCase(String.valueOf(data.getChq())) ? 0 : (data.getChq()).doubleValue());
            cellTotal03.setCellStyle(styleSum);
            HSSFCell cellTotal002 = row.createCell(11);
            cellTotal002.setCellValue("Total Amount (Wait) : ");
            cellTotal002.setCellStyle(styleSum);
            HSSFCell cellTotal003 = row.createCell(12);
            cellTotal003.setCellValue("".equalsIgnoreCase(String.valueOf(data.getTotalamountwait())) ? 0 : (data.getTotalamountwait()).doubleValue());
            cellTotal003.setCellStyle(styleSum);
            
            row = sheet.createRow(count + i + 5);
            HSSFCell cellTotal04 = row.createCell(1);
            cellTotal04.setCellValue("Credit Card : ");
            cellTotal04.setCellStyle(styleSum);
            HSSFCell cellTotal05 = row.createCell(2);
            cellTotal05.setCellValue("".equalsIgnoreCase(String.valueOf(data.getCredit())) ? 0 : (data.getCredit()).doubleValue());
            cellTotal05.setCellStyle(styleSum);
            HSSFCell cellTotal004 = row.createCell(11);
            cellTotal004.setCellValue("Total Amount (Void) : ");
            cellTotal004.setCellStyle(styleSum);
            HSSFCell cellTotal005 = row.createCell(12);
            cellTotal005.setCellValue("".equalsIgnoreCase(String.valueOf(data.getTotalamountvoid())) ? 0 : (data.getTotalamountvoid()).doubleValue());
            cellTotal005.setCellStyle(styleSum);
            
            
            row = sheet.createRow(count + i + 6);
            HSSFCell cellTotal06 = row.createCell(1);
            cellTotal06.setCellValue("Bank Transfer : ");
            cellTotal06.setCellStyle(styleSum);
            HSSFCell cellTotal07 = row.createCell(2);
            cellTotal07.setCellValue("".equalsIgnoreCase(String.valueOf(data.getBanktransfer())) ? 0 : (data.getBanktransfer()).doubleValue());
            cellTotal07.setCellStyle(styleSum);
            HSSFCell cellTotal006 = row.createCell(11);
            cellTotal006.setCellValue("Total Amount (Invoice) : ");
            cellTotal006.setCellStyle(styleSum);
            HSSFCell cellTotal007 = row.createCell(12);
            cellTotal007.setCellValue("".equalsIgnoreCase(String.valueOf(data.getTotalamountinvoice())) ? 0 : (data.getTotalamountinvoice()).doubleValue());
            cellTotal007.setCellStyle(styleSum);
            
            row = sheet.createRow(count + i + 8);
            HSSFCell cellTotal08 = row.createCell(1);
            cellTotal08.setCellValue("W/T : ");
            cellTotal08.setCellStyle(styleSum);
            HSSFCell cellTotal09 = row.createCell(2);
            cellTotal09.setCellValue("".equalsIgnoreCase(String.valueOf(data.getWt())) ? 0 : (data.getWt()).doubleValue());
            cellTotal09.setCellStyle(styleSum);
            HSSFCell cellTotal008 = row.createCell(11);
            cellTotal008.setCellValue("Total Amount (Diff) : ");
            cellTotal008.setCellStyle(styleSum);
            HSSFCell cellTotal009 = row.createCell(12);
            cellTotal009.setCellValue("".equalsIgnoreCase(String.valueOf(data.getTotalamountdiff())) ? 0 : (data.getTotalamountdiff()).doubleValue());
            cellTotal009.setCellStyle(styleSum);
            
            row = sheet.createRow(count + i + 9);
            HSSFCell cellTotal10 = row.createCell(1);
            cellTotal10.setCellValue("Cash (---) : ");
            cellTotal10.setCellStyle(styleSum);
            HSSFCell cellTotal11 = row.createCell(2);
            cellTotal11.setCellValue("".equalsIgnoreCase(String.valueOf(data.getCashminus())) ? 0 : (data.getCashminus()).doubleValue());
            cellTotal11.setCellStyle(styleSum);
         }

           for(int j =0;j<13;j++){
               sheet.autoSizeColumn(j);
           }
           no ++;
      }
  }
    
    private void getApReport(HSSFWorkbook wb, List ApNirvana) {
        UtilityFunction util = new UtilityFunction();
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("dd/MM/yyyy");
        UtilityExcelFunction excelFunction = new UtilityExcelFunction();
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
        styleC1.setFont(excelFunction.getHeaderFont(wb.createFont()));
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
        styleC3Center.setFont(excelFunction.getHeaderTable(wb.createFont()));
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
        cell62.setCellValue("Ref InvNo");
        cell62.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(1);
        HSSFCell cell63 = row6.createCell(2);
        cell63.setCellValue("Invoice Date");
        sheet.autoSizeColumn(2);
        cell63.setCellStyle(styleC3Center);
        HSSFCell cell64 = row6.createCell(3);
        cell64.setCellValue("Pay No.");
        cell64.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(3);
        HSSFCell cell65 = row6.createCell(4);
        cell65.setCellValue("Ap Code");
        cell65.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(4);
        HSSFCell cell66 = row6.createCell(5);
        cell66.setCellValue("Invoice Sup");
        cell66.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(5);
        
        HSSFCellStyle styleC3Right = wb.createCellStyle();
        styleC3Right.setFont(excelFunction.getHeaderTable(wb.createFont()));
        styleC3Right.setAlignment(styleC3Right.ALIGN_RIGHT);
        styleC3Right.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC3Right.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC3Right.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC3Right.setBorderTop(HSSFCellStyle.BORDER_THIN);
        HSSFCell cell67 = row6.createCell(6);
        cell67.setCellValue("Acc Code");
        cell67.setCellStyle(styleC3Right);
        sheet.autoSizeColumn(6);
        HSSFCell cell68 = row6.createCell(7);
        cell68.setCellValue("Cur");
        cell68.setCellStyle(styleC3Right);
        sheet.autoSizeColumn(7);
        HSSFCell cell69 = row6.createCell(8);
        cell69.setCellValue("Gross");
        cell69.setCellStyle(styleC3Right);
        sheet.autoSizeColumn(8);
        HSSFCell cell70 = row6.createCell(9);
        cell70.setCellValue("Amount");
        cell70.setCellStyle(styleC3Right);
        sheet.autoSizeColumn(9);

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
                cell1.setCellValue(data.getRefinvoiceno());
                cell1.setCellStyle(styleC24);
             HSSFCell cell2 = row.createCell(2);
                cell2.setCellValue(data.getTransdate() == null ? "" : util.ConvertString(df.format(data.getTransdate())));
                cell2.setCellStyle(styleC24);
             HSSFCell cell3 = row.createCell(3);
                cell3.setCellValue(data.getPayno());
                cell3.setCellStyle(styleC24);
             HSSFCell cell4 = row.createCell(4);
                cell4.setCellValue(data.getVendorid());
                cell4.setCellStyle(styleC24);
             HSSFCell cell5 = row.createCell(5);
                cell5.setCellValue(data.getVendorname());
                cell5.setCellStyle(styleC24);   
             HSSFCell cell6 = row.createCell(6);
                cell6.setCellValue(data.getPuraccount1());
                cell6.setCellStyle(styleC24);
             HSSFCell cell7 = row.createCell(7);
                cell7.setCellValue(data.getCurrencyid());
                cell7.setCellStyle(styleC23);
             HSSFCell cell8 = row.createCell(8);
                cell8.setCellValue((data.getVatamt() != null) ? data.getVatamt().doubleValue() : new BigDecimal("0").doubleValue());
                cell8.setCellStyle(styleC25);
             HSSFCell cell9 = row.createCell(9);
                cell9.setCellValue((data.getBasevatamt() != null) ? data.getBasevatamt().doubleValue() : new BigDecimal("0").doubleValue());
                cell9.setCellStyle(styleC25);
             
             
             if(i == (ApNirvana.size()-1)){
                row = sheet.createRow(count + i + 1);
                for(int k=0;k<7;k++){
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
                
                String sumGross = "SUM(I" + 10+":I"+(count + i + 1)+")";
                String sumAmount = "SUM(J" + 10+":J"+(count + i + 1)+")";
                
                HSSFCell cell5Sum = row.createCell(7);
                    cell5Sum.setCellValue("Total");
                    cell5Sum.setCellStyle(styleSum);
                HSSFCell cell6Sum = row.createCell(8);
                    cell6Sum.setCellFormula(sumGross);
                    cell6Sum.setCellStyle(styleSum);
                HSSFCell cell7Sum = row.createCell(9);
                    cell7Sum.setCellFormula(sumAmount);
                    cell7Sum.setCellStyle(styleSum);
                
             }
             for(int j =0;j<13;j++){
                 sheet.autoSizeColumn(j);
             }
        }     
    }
    
    
    public void genSaleVatReport(HSSFWorkbook wb, List outputTaxViewList) {
        String sheetNameWendy = "Wendy"; // name of sheet
        String sheetNameInbound = "Inbound";
        String sheetNameOutbound = "Outbound";
        HSSFSheet sheetWendy = wb.createSheet(sheetNameWendy);
        HSSFSheet sheetOutbound  = wb.createSheet(sheetNameOutbound);
        HSSFSheet sheetInbound = wb.createSheet(sheetNameInbound);
        UtilityExcelFunction excelFunction = new UtilityExcelFunction();
//        OutputTaxView dataheader = new OutputTaxView();
        HSSFFont fontHeader = wb.createFont();
        fontHeader.setFontHeightInPoints((short) 14);
        fontHeader.setFontName("Arial");
        fontHeader.setBoldweight(fontHeader.BOLDWEIGHT_BOLD);
            
        HSSFFont fontHeaderDetail = wb.createFont();
        fontHeaderDetail.setFontName("Arial");
        fontHeaderDetail.setFontHeightInPoints((short) 12);
        
        HSSFFont fontHeaderDetailTotal = wb.createFont();
        fontHeaderDetailTotal.setFontName("Arial");
        fontHeaderDetailTotal.setFontHeightInPoints((short) 9);
        fontHeaderDetailTotal.setBoldweight(fontHeaderDetailTotal.BOLDWEIGHT_BOLD);
        
        HSSFFont fontDetail = wb.createFont();
        fontDetail.setFontName("Arial");
        fontDetail.setFontHeightInPoints((short) 8);
        
        HSSFFont fontHeaderTable = wb.createFont();
        fontHeaderTable.setFontName("Arial");
        fontHeaderTable.setFontHeightInPoints((short) 10);
        fontHeaderTable.setBoldweight(fontHeaderTable.BOLDWEIGHT_BOLD);
        
        HSSFFont symbolcheck = wb.createFont();
        symbolcheck.setFontName("Wingdings");
        symbolcheck.setFontHeightInPoints((short) 18);
        
        HSSFFont symbol = wb.createFont();
        symbol.setFontName("Wingdings");
        symbol.setFontHeightInPoints((short) 12);
        
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
        styleC25.setFont(fontDetail);

        HSSFCellStyle styleC26 = wb.createCellStyle();
        styleC26.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC26.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC26.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleC26.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC26.setDataFormat(currency.getFormat("#,##0"));
        styleC26.setAlignment(styleC22.ALIGN_CENTER);
        styleC26.setFont(fontDetail);
        
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
        styleC30.setFont(fontDetail);
        
        HSSFCellStyle stylebordertotal = wb.createCellStyle();
        stylebordertotal.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        
        HSSFCellStyle stylebordertotalleft = wb.createCellStyle();
        stylebordertotalleft.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        stylebordertotalleft.setBorderBottom(HSSFCellStyle.BORDER_THIN);

            // Header Table
            HSSFCellStyle styleC3 = wb.createCellStyle();
            styleC3.setAlignment(styleC3.ALIGN_CENTER);
            styleC3.setVerticalAlignment(styleC3.VERTICAL_CENTER);
            styleC3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            styleC3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            styleC3.setBorderRight(HSSFCellStyle.BORDER_THIN);
            styleC3.setBorderTop(HSSFCellStyle.BORDER_THIN);
            styleC3.setWrapText(true);
//            styleC3.setFont(fontHeaderTable);
            
            // Header Table
            HSSFCellStyle styleC4 = wb.createCellStyle();
            styleC4.setAlignment(styleC4.ALIGN_RIGHT);
            styleC4.setFont(fontHeaderDetailTotal);
            styleC4.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            styleC4.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            styleC4.setBorderRight(HSSFCellStyle.BORDER_THIN);
            styleC4.setBorderTop(HSSFCellStyle.BORDER_THIN);

            int count = 10;
            int tempcountW = 0 ;
            int tempcountI = 0 ;
            int tempcountO = 0 ;
            int x = 0;
            int y = 0;

            BigDecimal grossW = new BigDecimal(BigInteger.ZERO);
            BigDecimal grossO = new BigDecimal(BigInteger.ZERO);
            BigDecimal grossI = new BigDecimal(BigInteger.ZERO);
            BigDecimal vatW = new BigDecimal(BigInteger.ZERO);
            BigDecimal vatO = new BigDecimal(BigInteger.ZERO);
            BigDecimal vatI = new BigDecimal(BigInteger.ZERO);

            sheetWendy.setColumnWidth(0, 256*6);
            sheetWendy.setColumnWidth(1, 256*9);
            sheetWendy.setColumnWidth(2, 256*7);
            sheetWendy.setColumnWidth(3, 256*27);
            sheetWendy.setColumnWidth(4, 256*13);
            sheetWendy.setColumnWidth(5, 256*8);
            sheetWendy.setColumnWidth(6, 256*5);
            sheetWendy.setColumnWidth(7, 256*2);
            sheetWendy.setColumnWidth(8, 256*2);
            sheetWendy.setColumnWidth(9, 256*2);
            sheetWendy.setColumnWidth(10, 256*2);
            sheetWendy.setColumnWidth(11, 256*2);
            sheetWendy.setColumnWidth(12, 256*2);
            sheetWendy.setColumnWidth(13, 256*2);
            sheetWendy.setColumnWidth(14, 256*2);
            sheetWendy.setColumnWidth(15, 256*2);
            sheetWendy.setColumnWidth(16, 256*2);
            sheetWendy.setColumnWidth(17, 256*2);
            sheetWendy.setColumnWidth(18, 256*2);
            sheetWendy.setColumnWidth(19, 256*2);

            sheetOutbound.setColumnWidth(0, 256*6);
            sheetOutbound.setColumnWidth(1, 256*9);
            sheetOutbound.setColumnWidth(2, 256*7);
            sheetOutbound.setColumnWidth(3, 256*27);
            sheetOutbound.setColumnWidth(4, 256*13);
            sheetOutbound.setColumnWidth(5, 256*8);
            sheetOutbound.setColumnWidth(6, 256*5);
            sheetOutbound.setColumnWidth(7, 256*2);
            sheetOutbound.setColumnWidth(8, 256*2);
            sheetOutbound.setColumnWidth(9, 256*2);
            sheetOutbound.setColumnWidth(10, 256*2);
            sheetOutbound.setColumnWidth(11, 256*2);
            sheetOutbound.setColumnWidth(12, 256*2);
            sheetOutbound.setColumnWidth(13, 256*2);
            sheetOutbound.setColumnWidth(14, 256*2);
            sheetOutbound.setColumnWidth(15, 256*2);
            sheetOutbound.setColumnWidth(16, 256*2);
            sheetOutbound.setColumnWidth(17, 256*2);
            sheetOutbound.setColumnWidth(18, 256*2);
            sheetOutbound.setColumnWidth(19, 256*2);
            
            sheetInbound.setColumnWidth(0, 256*6);
            sheetInbound.setColumnWidth(1, 256*9);
            sheetInbound.setColumnWidth(2, 256*7);
            sheetInbound.setColumnWidth(3, 256*27);
            sheetInbound.setColumnWidth(4, 256*13);
            sheetInbound.setColumnWidth(5, 256*8);
            sheetInbound.setColumnWidth(6, 256*5);
            sheetInbound.setColumnWidth(7, 256*2);
            sheetInbound.setColumnWidth(8, 256*2);
            sheetInbound.setColumnWidth(9, 256*2);
            sheetInbound.setColumnWidth(10, 256*2);
            sheetInbound.setColumnWidth(11, 256*2);
            sheetInbound.setColumnWidth(12, 256*2);
            sheetInbound.setColumnWidth(13, 256*2);
            sheetInbound.setColumnWidth(14, 256*2);
            sheetInbound.setColumnWidth(15, 256*2);
            sheetInbound.setColumnWidth(16, 256*2);
            sheetInbound.setColumnWidth(17, 256*2);
            sheetInbound.setColumnWidth(18, 256*2);
            sheetInbound.setColumnWidth(19, 256*2);
            
            HSSFCellStyle styleSymbol = wb.createCellStyle();
            styleSymbol.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            styleSymbol.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            styleSymbol.setBorderRight(HSSFCellStyle.BORDER_THIN);
            styleSymbol.setBorderTop(HSSFCellStyle.BORDER_THIN);
            styleSymbol.setAlignment(styleSymbol.ALIGN_CENTER);
            styleSymbol.setVerticalAlignment(styleSymbol.VERTICAL_CENTER);
            styleSymbol.setFont(symbol);
            
            HSSFCellStyle styleSymbolCheck = wb.createCellStyle();
            styleSymbolCheck.setFont(symbolcheck);
            styleSymbolCheck.setAlignment(styleSymbolCheck.ALIGN_CENTER);
            
            for(int i=0;i<outputTaxViewList.size();i++){
                OutputTaxView data = (OutputTaxView)outputTaxViewList.get(i);
                if("Wendy".equalsIgnoreCase(data.getDepartment())){
                    // set Header Report (Row 1)
                    HSSFCellStyle styleC1 = wb.createCellStyle();
                    styleC1.setAlignment(styleC1.ALIGN_CENTER);
                    HSSFRow row1 = sheetWendy.createRow(0);
                    HSSFCell cell1 = row1.createCell(0);
                    cell1.setCellValue("รายงานภาษีขาย");
                    styleC1.setFont(fontHeader);
                    cell1.setCellStyle(styleC1);
                    sheetWendy.addMergedRegion(CellRangeAddress.valueOf("A1:T1"));

                    // Row 2
                    HSSFRow row2 = sheetWendy.createRow(1);
                    HSSFCell cell21 = row2.createCell(0);
                    cell21.setCellValue("เดือนภาษี  " + data.getHeaderMonth() + " ปี " +data.getHeaderYear());
                    styleC23.setFont(fontHeaderDetail);
                    cell21.setCellStyle(styleC23);
                    sheetWendy.addMergedRegion(CellRangeAddress.valueOf("A2:T2"));

                    // Row 3
                    HSSFRow row3 = sheetWendy.createRow(2);
                    HSSFCell cell31 = row3.createCell(0);
                    cell31.setCellValue("ชื่อผู้ประกอบการ บริษัท เอส.เอ็ม.ไอ แทรเวล จำกัด");
                    cell31.setCellStyle(styleC22);
                    sheetWendy.addMergedRegion(CellRangeAddress.valueOf("A3:D3"));
                    HSSFCell cell32 = row3.createCell(5);
                    cell32.setCellValue("สำนักงานใหญ่ ");
                    cell32.setCellStyle(styleC21);
                    sheetWendy.addMergedRegion(CellRangeAddress.valueOf("F3:J3"));
                    HSSFCell cell33 = row3.createCell(11);
                    cell33.setCellValue("");
                    cell33.setCellStyle(styleSymbol);
                    HSSFCell cell34 = row3.createCell(12);
                    cell34.setCellValue("สาขา");
                    cell34.setCellStyle(styleC21);
                    sheetWendy.addMergedRegion(CellRangeAddress.valueOf("M3:O3"));
                    HSSFCell cell35 = row3.createCell(16);
                    cell35.setCellStyle(styleSymbol);
                    HSSFCell cell36 = row3.createCell(17);
                    cell36.setCellStyle(styleSymbol);
                    HSSFCell cell37 = row3.createCell(18);
                    cell37.setCellStyle(styleSymbol);

                    HSSFRow rowX = sheetWendy.createRow(3);
                    rowX.setHeightInPoints((short) 2);
                    
                    // Row 4
                    HSSFRow row4 = sheetWendy.createRow(4);
                    HSSFCell cell41 = row4.createCell(0);
                    cell41.setCellValue("ชื่อสถานประกอบการ บริษัท เอส.เอ็ม.ไอ แทรเวล จำกัด");
                    cell41.setCellStyle(styleC22);
                    sheetWendy.addMergedRegion(CellRangeAddress.valueOf("A5:D5"));
                    HSSFCell cell42 = row4.createCell(4);
                    cell42.setCellValue("เลขประจำตัวผู้เสียภาษีอากร  ");
                    cell42.setCellStyle(styleC21);
                    sheetWendy.addMergedRegion(CellRangeAddress.valueOf("E5:G5"));
                    HSSFCell cell43 = row4.createCell(7);
                    cell43.setCellValue("0");
                    cell43.setCellStyle(styleC26);
                    HSSFCell cell44 = row4.createCell(8);
                    cell44.setCellValue("1");
                    cell44.setCellStyle(styleC26);
                    HSSFCell cell45 = row4.createCell(9);
                    cell45.setCellValue("0");
                    cell45.setCellStyle(styleC26);
                    HSSFCell cell46 = row4.createCell(10);
                    cell46.setCellValue("5");
                    cell46.setCellStyle(styleC26);
                    HSSFCell cell47 = row4.createCell(11);
                    cell47.setCellValue("5");
                    cell47.setCellStyle(styleC26);
                    HSSFCell cell48 = row4.createCell(12);
                    cell48.setCellValue("2");
                    cell48.setCellStyle(styleC26);
                    HSSFCell cell49 = row4.createCell(13);
                    cell49.setCellValue("3");
                    cell49.setCellStyle(styleC26);
                    HSSFCell cell50 = row4.createCell(14);
                    cell50.setCellValue("0");
                    cell50.setCellStyle(styleC26);
                    HSSFCell cell51 = row4.createCell(15);
                    cell51.setCellValue("2");
                    cell51.setCellStyle(styleC26);
                    HSSFCell cell52 = row4.createCell(16);
                    cell52.setCellValue("0");
                    cell52.setCellStyle(styleC26);
                    HSSFCell cell53 = row4.createCell(17);
                    cell53.setCellValue("1");
                    cell53.setCellStyle(styleC26);
                    HSSFCell cell54 = row4.createCell(18);
                    cell54.setCellValue("3");
                    cell54.setCellStyle(styleC26);
                    HSSFCell cell55 = row4.createCell(19);
                    cell55.setCellValue("2");
                    cell55.setCellStyle(styleC26);
                    
                    //wendy
                    HSSFRow row5 = sheetWendy.createRow(6);
                    HSSFCell cell81 = row5.createCell(0);
                    cell81.setCellValue("ลำดับที่");
                    cell81.setCellStyle(styleC3);
                    sheetWendy.addMergedRegion(CellRangeAddress.valueOf("A7:A10"));
                    HSSFCell cell82 = row5.createCell(1);
                    cell82.setCellValue("ใบกำกับภาษี");
                    cell82.setCellStyle(styleC3);
                    sheetWendy.addMergedRegion(CellRangeAddress.valueOf("B7:C8"));
                    HSSFCell cell83 = row5.createCell(3);
                    cell83.setCellValue("รายการ");
                    cell83.setCellStyle(styleC3);
                    sheetWendy.addMergedRegion(CellRangeAddress.valueOf("D7:D10"));
                    HSSFCell cell84 = row5.createCell(4);
                    cell84.setCellValue("เลขประจำตัวผู้เสียภาษีของผู้ซื้อสินค้า/ผู้รับบริการ");
                    cell84.setCellStyle(styleC3);
                    sheetWendy.addMergedRegion(CellRangeAddress.valueOf("E7:E10"));
                    HSSFCell cell85 = row5.createCell(5);
                    cell85.setCellValue("สถานประกอบการ");
                    cell85.setCellStyle(styleC3);
                    sheetWendy.addMergedRegion(CellRangeAddress.valueOf("F7:G8"));
                    HSSFCell cell86 = row5.createCell(7);
                    cell86.setCellValue("มูลค่าสินค้าหรือบริการ");
                    cell86.setCellStyle(styleC3);
                    sheetWendy.addMergedRegion(CellRangeAddress.valueOf("H7:N10"));
                    HSSFCell cell87 = row5.createCell(14);
                    cell87.setCellValue("จำนวนเงินภาษีมูลค่าเพิ่ม");
                    cell87.setCellStyle(styleC3);
                    sheetWendy.addMergedRegion(CellRangeAddress.valueOf("O7:T10"));
                                       
                    HSSFRow row7 = sheetWendy.createRow(8);
                    HSSFCell cell88 = row7.createCell(1);
                    cell88.setCellValue("วัน/เดือน/ปี");
                    cell88.setCellStyle(styleC3);
                    sheetWendy.addMergedRegion(CellRangeAddress.valueOf("B9:B10"));
                    HSSFCell cell89 = row7.createCell(2);
                    cell89.setCellValue("เลขที่");
                    cell89.setCellStyle(styleC3);
                    sheetWendy.addMergedRegion(CellRangeAddress.valueOf("C9:C10"));
                    HSSFCell cell90 = row7.createCell(5);
                    cell90.setCellValue("สำนักงานใหญ่");
                    cell90.setCellStyle(styleC3);
                    sheetWendy.addMergedRegion(CellRangeAddress.valueOf("F9:F10"));
                    HSSFCell cell91 = row7.createCell(6);
                    cell91.setCellValue("สาขาที่");
                    cell91.setCellStyle(styleC3);
                    sheetWendy.addMergedRegion(CellRangeAddress.valueOf("G9:G10"));
                    
                    HSSFCell cell92 = row5.createCell(2);
                    cell92.setCellStyle(styleC3);
                    HSSFCell cell93 = row5.createCell(6);
                    cell93.setCellStyle(styleC3);
                    HSSFCell cell008 = row5.createCell(8);
                    cell008.setCellStyle(styleC3);
                    HSSFCell cell009 = row5.createCell(9);
                    cell009.setCellStyle(styleC3);
                    HSSFCell cell010 = row5.createCell(10);
                    cell010.setCellStyle(styleC3);
                    HSSFCell cell011 = row5.createCell(11);
                    cell011.setCellStyle(styleC3);
                    HSSFCell cell012 = row5.createCell(12);
                    cell012.setCellStyle(styleC3);
                    HSSFCell cell013 = row5.createCell(13);
                    cell013.setCellStyle(styleC3);
                    HSSFCell cell014 = row5.createCell(15);
                    cell014.setCellStyle(styleC3);
                    HSSFCell cell015 = row5.createCell(16);
                    cell015.setCellStyle(styleC3);
                    HSSFCell cell016 = row5.createCell(17);
                    cell016.setCellStyle(styleC3);
                    HSSFCell cell017 = row5.createCell(18);
                    cell017.setCellStyle(styleC3);
                    HSSFCell cell018 = row5.createCell(19);
                    cell018.setCellStyle(styleC3);
                                        
                    HSSFCell cell94 = row7.createCell(0);
                    cell94.setCellStyle(styleC3);
                    HSSFCell cell95 = row7.createCell(3);
                    cell95.setCellStyle(styleC3);
                    HSSFCell cell96 = row7.createCell(4);
                    cell96.setCellStyle(styleC3);
                    HSSFCell cell97 = row7.createCell(7);
                    cell97.setCellStyle(styleC3);
                    HSSFCell cell98 = row7.createCell(8);
                    cell98.setCellStyle(styleC3);
                    HSSFCell cell99 = row7.createCell(13);
                    cell99.setCellStyle(styleC3);
                    HSSFCell cell100 = row7.createCell(19);
                    cell100.setCellStyle(styleC3);
                    
                    HSSFRow row6 = sheetWendy.createRow(7);
                    HSSFCell cell000 = row6.createCell(0);
                    cell000.setCellStyle(styleC3);
                    HSSFCell cell001 = row6.createCell(1);
                    cell001.setCellStyle(styleC3);
                    HSSFCell cell002 = row6.createCell(2);
                    cell002.setCellStyle(styleC3);
                    HSSFCell cell003 = row6.createCell(3);
                    cell003.setCellStyle(styleC3);
                    HSSFCell cell004 = row6.createCell(4);
                    cell004.setCellStyle(styleC3);
                    HSSFCell cell005 = row6.createCell(5);
                    cell005.setCellStyle(styleC3);
                    HSSFCell cell006 = row6.createCell(6);
                    cell006.setCellStyle(styleC3);
                    HSSFCell cell007 = row6.createCell(7);
                    cell007.setCellStyle(styleC3);
                    HSSFCell cell00008 = row6.createCell(13);
                    cell00008.setCellStyle(styleC3);
                    HSSFCell cell00009 = row6.createCell(19);
                    cell00009.setCellStyle(styleC3);
                    
                    HSSFRow row8 = sheetWendy.createRow(9);
                    for(int k = 0 ; k < 20 ; k++){
                        HSSFCell cell0000 = row8.createCell(k);
                        cell0000.setCellStyle(styleC3);
                    }
                    
                    HSSFRow row = sheetWendy.createRow(count + i);
                    HSSFCell celldata0 = row.createCell(0);
                    celldata0.setCellValue(String.valueOf(data.getOrder()));
                    celldata0.setCellStyle(styleC26);

                    HSSFCell celldata1 = row.createCell(1);
                    celldata1.setCellValue(String.valueOf(data.getTaxdate()));
                    celldata1.setCellStyle(styleC30);

                    HSSFCell celldata2 = row.createCell(2);
                    celldata2.setCellValue(String.valueOf(data.getTaxno()));
                    celldata2.setCellStyle(styleC30);

                    HSSFCell celldata3 = row.createCell(3);
                    celldata3.setCellValue(String.valueOf(data.getDescription()));
                    celldata3.setCellStyle(styleC25);

                    HSSFCell celldata4 = row.createCell(4);
                    celldata4.setCellValue(String.valueOf(data.getAgttaxno()));
                    celldata4.setCellStyle(styleC25);

                    HSSFCell celldata5 = row.createCell(5);
                    if("1".equalsIgnoreCase(String.valueOf(data.getMain()))){
                        celldata5.setCellValue("");
                    }else{
                        celldata5.setCellValue("");
                    }
                    celldata5.setCellStyle(styleSymbol);

                    HSSFCell celldata6 = row.createCell(6);
                    celldata6.setCellValue(String.valueOf(data.getBranchno()));
                    celldata6.setCellStyle(styleC30);

                    HSSFCell celldata7 = row.createCell(7);
                    celldata7.setCellValue("".equalsIgnoreCase(String.valueOf(data.getGross())) ? 0 : (data.getGross()).doubleValue());
                    celldata7.setCellStyle(styleC25);
                    sheetWendy.addMergedRegion(CellRangeAddress.valueOf("H"+(count+i+1)+":N"+(count+i+1)));
                    
                    HSSFCell celldata8 = row.createCell(14);
                    celldata8.setCellValue("".equalsIgnoreCase(String.valueOf(data.getVat())) ? 0 : (data.getVat()).doubleValue());
                    celldata8.setCellStyle(styleC25);
                    sheetWendy.addMergedRegion(CellRangeAddress.valueOf("O"+(count+i+1)+":T"+(count+i+1)));
                    
                    for(int k = 8 ; k < 20 ; k++){
                        if(k != 7 && k != 14){
                            HSSFCell cell0000 = row.createCell(k);
                            cell0000.setCellStyle(styleC25);
                        }
                    }
                    
                    if(data.getGross() != null){
                        grossW = grossW.add(data.getGross());
                    }else{
                        grossW = grossW.add(BigDecimal.ZERO);
                    }
                    
                    if(data.getVat() != null){
                        vatW = vatW.add(data.getVat());
                    }else{
                        vatW = vatW.add(BigDecimal.ZERO);
                    }
                   
                    tempcountW = count + i + 1;
                }
                
                //Outbound
                if("Outbound".equalsIgnoreCase(data.getDepartment())){
                    //Total Wendy
                    if(tempcountW != 0){
                        HSSFRow rowtotal = sheetWendy.createRow(tempcountW);
                        String totalGross = "SUM(H" +11+":H"+(tempcountW)+")";
                        String totalVat = "SUM(O" + 11+":O"+(tempcountW)+")";

                        HSSFCellStyle styleTotal = wb.createCellStyle();
                        styleTotal.setFont(excelFunction.getHeaderTable(wb.createFont()));
                        styleTotal.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                        styleTotal.setBorderRight(HSSFCellStyle.BORDER_THIN);
                        styleTotal.setBorderTop(HSSFCellStyle.BORDER_THIN);
                        styleTotal.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                        styleTotal.setAlignment(styleC22.ALIGN_RIGHT);

                        HSSFCell cellTotal0 = rowtotal.createCell(0);
                        cellTotal0.setCellStyle(stylebordertotalleft);
                        HSSFCell cellTotal00 = rowtotal.createCell(1);
                        cellTotal00.setCellStyle(stylebordertotal);
                        HSSFCell cellTotal01 = rowtotal.createCell(2);
                         cellTotal01.setCellStyle(stylebordertotal);
                        HSSFCell cellTotal02 = rowtotal.createCell(3);
                        cellTotal02.setCellStyle(stylebordertotal);
                        HSSFCell cellTotal03 = rowtotal.createCell(4);
                        cellTotal03.setCellStyle(stylebordertotal);
                        HSSFCell cellTotal04 = rowtotal.createCell(5);
                        cellTotal04.setCellStyle(stylebordertotal);
                        HSSFCell cellTotal05 = rowtotal.createCell(6);
                        cellTotal05.setCellValue("TOTAL WENDY : ");
                        cellTotal05.setCellStyle(styleC4);
                        HSSFCell cellTotal06 = rowtotal.createCell(7);
                        cellTotal06.setCellFormula(totalGross);
                        cellTotal06.setCellStyle(styleC25);
                        HSSFCell cellTotal07 = rowtotal.createCell(14);
                        cellTotal07.setCellFormula(totalVat);
                        cellTotal07.setCellStyle(styleC25);
                        sheetWendy.addMergedRegion(CellRangeAddress.valueOf("H"+(tempcountW+1)+":N"+(tempcountW+1)));
                        sheetWendy.addMergedRegion(CellRangeAddress.valueOf("O"+(tempcountW+1)+":T"+(tempcountW+1)));
                        
                        for(int k = 8 ; k < 20 ; k++){
                            if(k != 7 && k != 14){
                                HSSFCell cell0000 = rowtotal.createCell(k);
                                cell0000.setCellStyle(styleC25);
                            }
                        }
                    }
                    
                    // set Header Report (Row 1)
                    HSSFCellStyle styleC1 = wb.createCellStyle();
                    styleC1.setAlignment(styleC1.ALIGN_CENTER);
                    HSSFRow row1 = sheetOutbound.createRow(0);
                    HSSFCell cell1 = row1.createCell(0);
                    cell1.setCellValue("รายงานภาษีขาย");
                    styleC1.setFont(fontHeader);
                    cell1.setCellStyle(styleC1);
                    sheetOutbound.addMergedRegion(CellRangeAddress.valueOf("A1:T1"));

                    // Row 2
                    HSSFRow row2 = sheetOutbound.createRow(1);
                    HSSFCell cell21 = row2.createCell(0);
                    cell21.setCellValue("เดือนภาษี  " + data.getHeaderMonth() + " ปี " +data.getHeaderYear());
                    styleC23.setFont(fontHeaderDetail);
                    cell21.setCellStyle(styleC23);
                    sheetOutbound.addMergedRegion(CellRangeAddress.valueOf("A2:T2"));

                    // Row 3
                    HSSFRow row3 = sheetOutbound.createRow(2);
                    HSSFCell cell31 = row3.createCell(0);
                    cell31.setCellValue("ชื่อผู้ประกอบการ บริษัท เอส.เอ็ม.ไอ แทรเวล จำกัด");
                    cell31.setCellStyle(styleC22);
                    sheetOutbound.addMergedRegion(CellRangeAddress.valueOf("A3:D3"));
                    HSSFCell cell32 = row3.createCell(5);
                    cell32.setCellValue("สำนักงานใหญ่ ");
                    cell32.setCellStyle(styleC21);
                    sheetOutbound.addMergedRegion(CellRangeAddress.valueOf("F3:J3"));
                    HSSFCell cell33 = row3.createCell(11);
                    cell33.setCellValue("");
                    cell33.setCellStyle(styleSymbol);
                    HSSFCell cell34 = row3.createCell(12);
                    cell34.setCellValue("สาขา");
                    cell34.setCellStyle(styleC21);
                    sheetOutbound.addMergedRegion(CellRangeAddress.valueOf("M3:O3"));
                    HSSFCell cell35 = row3.createCell(16);
                    cell35.setCellStyle(styleSymbol);
                    HSSFCell cell36 = row3.createCell(17);
                    cell36.setCellStyle(styleSymbol);
                    HSSFCell cell37 = row3.createCell(18);
                    cell37.setCellStyle(styleSymbol);

                    HSSFRow rowX = sheetOutbound.createRow(3);
                    rowX.setHeightInPoints((short) 2);
                    
                    // Row 4
                    HSSFRow row4 = sheetOutbound.createRow(4);
                    HSSFCell cell41 = row4.createCell(0);
                    cell41.setCellValue("ชื่อสถานประกอบการ บริษัท เอส.เอ็ม.ไอ แทรเวล จำกัด");
                    cell41.setCellStyle(styleC22);
                    sheetOutbound.addMergedRegion(CellRangeAddress.valueOf("A5:D5"));
                    HSSFCell cell42 = row4.createCell(4);
                    cell42.setCellValue("เลขประจำตัวผู้เสียภาษีอากร  ");
                    cell42.setCellStyle(styleC21);
                    sheetOutbound.addMergedRegion(CellRangeAddress.valueOf("E5:G5"));
                    HSSFCell cell43 = row4.createCell(7);
                    cell43.setCellValue("0");
                    cell43.setCellStyle(styleC26);
                    HSSFCell cell44 = row4.createCell(8);
                    cell44.setCellValue("1");
                    cell44.setCellStyle(styleC26);
                    HSSFCell cell45 = row4.createCell(9);
                    cell45.setCellValue("0");
                    cell45.setCellStyle(styleC26);
                    HSSFCell cell46 = row4.createCell(10);
                    cell46.setCellValue("5");
                    cell46.setCellStyle(styleC26);
                    HSSFCell cell47 = row4.createCell(11);
                    cell47.setCellValue("5");
                    cell47.setCellStyle(styleC26);
                    HSSFCell cell48 = row4.createCell(12);
                    cell48.setCellValue("2");
                    cell48.setCellStyle(styleC26);
                    HSSFCell cell49 = row4.createCell(13);
                    cell49.setCellValue("3");
                    cell49.setCellStyle(styleC26);
                    HSSFCell cell50 = row4.createCell(14);
                    cell50.setCellValue("0");
                    cell50.setCellStyle(styleC26);
                    HSSFCell cell51 = row4.createCell(15);
                    cell51.setCellValue("2");
                    cell51.setCellStyle(styleC26);
                    HSSFCell cell52 = row4.createCell(16);
                    cell52.setCellValue("0");
                    cell52.setCellStyle(styleC26);
                    HSSFCell cell53 = row4.createCell(17);
                    cell53.setCellValue("1");
                    cell53.setCellStyle(styleC26);
                    HSSFCell cell54 = row4.createCell(18);
                    cell54.setCellValue("3");
                    cell54.setCellStyle(styleC26);
                    HSSFCell cell55 = row4.createCell(19);
                    cell55.setCellValue("2");
                    cell55.setCellStyle(styleC26);
                    
                    //wendy
                    HSSFRow row5 = sheetOutbound.createRow(6);
                    HSSFCell cell81 = row5.createCell(0);
                    cell81.setCellValue("ลำดับที่");
                    cell81.setCellStyle(styleC3);
                    sheetOutbound.addMergedRegion(CellRangeAddress.valueOf("A7:A10"));
                    HSSFCell cell82 = row5.createCell(1);
                    cell82.setCellValue("ใบกำกับภาษี");
                    cell82.setCellStyle(styleC3);
                    sheetOutbound.addMergedRegion(CellRangeAddress.valueOf("B7:C8"));
                    HSSFCell cell83 = row5.createCell(3);
                    cell83.setCellValue("รายการ");
                    cell83.setCellStyle(styleC3);
                    sheetOutbound.addMergedRegion(CellRangeAddress.valueOf("D7:D10"));
                    HSSFCell cell84 = row5.createCell(4);
                    cell84.setCellValue("เลขประจำตัวผู้เสียภาษีของผู้ซื้อสินค้า/ผู้รับบริการ");
                    cell84.setCellStyle(styleC3);
                    sheetOutbound.addMergedRegion(CellRangeAddress.valueOf("E7:E10"));
                    HSSFCell cell85 = row5.createCell(5);
                    cell85.setCellValue("สถานประกอบการ");
                    cell85.setCellStyle(styleC3);
                    sheetOutbound.addMergedRegion(CellRangeAddress.valueOf("F7:G8"));
                    HSSFCell cell86 = row5.createCell(7);
                    cell86.setCellValue("มูลค่าสินค้าหรือบริการ");
                    cell86.setCellStyle(styleC3);
                    sheetOutbound.addMergedRegion(CellRangeAddress.valueOf("H7:N10"));
                    HSSFCell cell87 = row5.createCell(14);
                    cell87.setCellValue("จำนวนเงินภาษีมูลค่าเพิ่ม");
                    cell87.setCellStyle(styleC3);
                    sheetOutbound.addMergedRegion(CellRangeAddress.valueOf("O7:T10"));
                                       
                    HSSFRow row7 = sheetOutbound.createRow(8);
                    HSSFCell cell88 = row7.createCell(1);
                    cell88.setCellValue("วัน/เดือน/ปี");
                    cell88.setCellStyle(styleC3);
                    sheetOutbound.addMergedRegion(CellRangeAddress.valueOf("B9:B10"));
                    HSSFCell cell89 = row7.createCell(2);
                    cell89.setCellValue("เลขที่");
                    cell89.setCellStyle(styleC3);
                    sheetOutbound.addMergedRegion(CellRangeAddress.valueOf("C9:C10"));
                    HSSFCell cell90 = row7.createCell(5);
                    cell90.setCellValue("สำนักงานใหญ่");
                    cell90.setCellStyle(styleC3);
                    sheetOutbound.addMergedRegion(CellRangeAddress.valueOf("F9:F10"));
                    HSSFCell cell91 = row7.createCell(6);
                    cell91.setCellValue("สาขาที่");
                    cell91.setCellStyle(styleC3);
                    sheetOutbound.addMergedRegion(CellRangeAddress.valueOf("G9:G10"));
                    
                    HSSFCell cell92 = row5.createCell(2);
                    cell92.setCellStyle(styleC3);
                    HSSFCell cell93 = row5.createCell(6);
                    cell93.setCellStyle(styleC3);
                    HSSFCell cell008 = row5.createCell(8);
                    cell008.setCellStyle(styleC3);
                    HSSFCell cell009 = row5.createCell(9);
                    cell009.setCellStyle(styleC3);
                    HSSFCell cell010 = row5.createCell(10);
                    cell010.setCellStyle(styleC3);
                    HSSFCell cell011 = row5.createCell(11);
                    cell011.setCellStyle(styleC3);
                    HSSFCell cell012 = row5.createCell(12);
                    cell012.setCellStyle(styleC3);
                    HSSFCell cell013 = row5.createCell(13);
                    cell013.setCellStyle(styleC3);
                    HSSFCell cell014 = row5.createCell(15);
                    cell014.setCellStyle(styleC3);
                    HSSFCell cell015 = row5.createCell(16);
                    cell015.setCellStyle(styleC3);
                    HSSFCell cell016 = row5.createCell(17);
                    cell016.setCellStyle(styleC3);
                    HSSFCell cell017 = row5.createCell(18);
                    cell017.setCellStyle(styleC3);
                    HSSFCell cell018 = row5.createCell(19);
                    cell018.setCellStyle(styleC3);
                                        
                    HSSFCell cell94 = row7.createCell(0);
                    cell94.setCellStyle(styleC3);
                    HSSFCell cell95 = row7.createCell(3);
                    cell95.setCellStyle(styleC3);
                    HSSFCell cell96 = row7.createCell(4);
                    cell96.setCellStyle(styleC3);
                    HSSFCell cell97 = row7.createCell(7);
                    cell97.setCellStyle(styleC3);
                    HSSFCell cell98 = row7.createCell(8);
                    cell98.setCellStyle(styleC3);
                    HSSFCell cell99 = row7.createCell(13);
                    cell99.setCellStyle(styleC3);
                    HSSFCell cell100 = row7.createCell(19);
                    cell100.setCellStyle(styleC3);
                    
                    HSSFRow row6 = sheetOutbound.createRow(7);
                    HSSFCell cell000 = row6.createCell(0);
                    cell000.setCellStyle(styleC3);
                    HSSFCell cell001 = row6.createCell(1);
                    cell001.setCellStyle(styleC3);
                    HSSFCell cell002 = row6.createCell(2);
                    cell002.setCellStyle(styleC3);
                    HSSFCell cell003 = row6.createCell(3);
                    cell003.setCellStyle(styleC3);
                    HSSFCell cell004 = row6.createCell(4);
                    cell004.setCellStyle(styleC3);
                    HSSFCell cell005 = row6.createCell(5);
                    cell005.setCellStyle(styleC3);
                    HSSFCell cell006 = row6.createCell(6);
                    cell006.setCellStyle(styleC3);
                    HSSFCell cell007 = row6.createCell(7);
                    cell007.setCellStyle(styleC3);
                    HSSFCell cell00008 = row6.createCell(13);
                    cell00008.setCellStyle(styleC3);
                    HSSFCell cell00009 = row6.createCell(19);
                    cell00009.setCellStyle(styleC3);
                    
                    HSSFRow row8 = sheetOutbound.createRow(9);
                    for(int k = 0 ; k < 20 ; k++){
                        HSSFCell cell0000 = row8.createCell(k);
                        cell0000.setCellStyle(styleC3);
                    }
                    
                    HSSFRow row = sheetOutbound.createRow(count + x);
                    HSSFCell celldata0 = row.createCell(0);
                    celldata0.setCellValue(String.valueOf(data.getOrder()));
                    celldata0.setCellStyle(styleC26);

                    HSSFCell celldata1 = row.createCell(1);
                    celldata1.setCellValue(String.valueOf(data.getTaxdate()));
                    celldata1.setCellStyle(styleC30);

                    HSSFCell celldata2 = row.createCell(2);
                    celldata2.setCellValue(String.valueOf(data.getTaxno()));
                    celldata2.setCellStyle(styleC30);

                    HSSFCell celldata3 = row.createCell(3);
                    celldata3.setCellValue(String.valueOf(data.getDescription()));
                    celldata3.setCellStyle(styleC25);

                    HSSFCell celldata4 = row.createCell(4);
                    celldata4.setCellValue(String.valueOf(data.getAgttaxno()));
                    celldata4.setCellStyle(styleC25);

                    HSSFCell celldata5 = row.createCell(5);
                    if("1".equalsIgnoreCase(String.valueOf(data.getMain()))){
                        celldata5.setCellValue("");
                    }else{
                        celldata5.setCellValue("");
                    }
                    celldata5.setCellStyle(styleSymbol);

                    HSSFCell celldata6 = row.createCell(6);
                    celldata6.setCellValue(String.valueOf(data.getBranchno()));
                    celldata6.setCellStyle(styleC30);

                    HSSFCell celldata7 = row.createCell(7);
                    celldata7.setCellValue("".equalsIgnoreCase(String.valueOf(data.getGross())) ? 0 : (data.getGross()).doubleValue());
                    celldata7.setCellStyle(styleC25);

                    HSSFCell celldata8 = row.createCell(14);
                    celldata8.setCellValue("".equalsIgnoreCase(String.valueOf(data.getVat())) ? 0 : (data.getVat()).doubleValue());
                    celldata8.setCellStyle(styleC25);
                    
                    sheetOutbound.addMergedRegion(CellRangeAddress.valueOf("H"+(count + x+1)+":N"+(count + x +1)));
                    sheetOutbound.addMergedRegion(CellRangeAddress.valueOf("O"+(count + x+1)+":T"+(count + x +1)));
                    for(int k = 8 ; k < 20 ; k++){
                        if(k != 7 && k != 14){
                            HSSFCell cell0000 = row.createCell(k);
                            cell0000.setCellStyle(styleC25);
                        }
                    }
                    
                    if(data.getGross() != null){
                        grossO = grossO.add(data.getGross());
                    }else{
                        grossO = grossO.add(BigDecimal.ZERO);
                    }
                    
                    if(data.getVat() != null){
                        vatO = vatO.add(data.getVat());
                    }else{
                        vatO = vatO.add(BigDecimal.ZERO);
                    }

                    x ++ ;
                }
                if("Inbound".equalsIgnoreCase(data.getDepartment())){
                    if(x != 0){
                        tempcountO = count+x;
                        HSSFRow rowtotal = sheetOutbound.createRow(tempcountO);
                        String totalGross = "SUM(H" + 11+":H"+(tempcountO)+")";
                        String totalVat = "SUM(O" + 11+":O"+(tempcountO)+")";

                        HSSFCellStyle styleTotal = wb.createCellStyle();
                        styleTotal.setFont(excelFunction.getHeaderTable(wb.createFont()));
                        styleTotal.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                        styleTotal.setBorderRight(HSSFCellStyle.BORDER_THIN);
                        styleTotal.setBorderTop(HSSFCellStyle.BORDER_THIN);
                        styleTotal.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                        styleTotal.setAlignment(styleC22.ALIGN_RIGHT);

                        HSSFCell cellTotal0 = rowtotal.createCell(0);
                        cellTotal0.setCellStyle(stylebordertotalleft);
                        HSSFCell cellTotal00 = rowtotal.createCell(1);
                        cellTotal00.setCellStyle(stylebordertotal);
                        HSSFCell cellTotal01 = rowtotal.createCell(2);
                         cellTotal01.setCellStyle(stylebordertotal);
                        HSSFCell cellTotal02 = rowtotal.createCell(3);
                        cellTotal02.setCellStyle(stylebordertotal);
                        HSSFCell cellTotal03 = rowtotal.createCell(4);
                        cellTotal03.setCellStyle(stylebordertotal);
                        HSSFCell cellTotal04 = rowtotal.createCell(5);
                        cellTotal04.setCellStyle(stylebordertotal);
                        HSSFCell cellTotal05 = rowtotal.createCell(6);
                        cellTotal05.setCellValue("TOTAL OUTBOUND : ");
                        cellTotal05.setCellStyle(styleC4);
                        HSSFCell cellTotal06 = rowtotal.createCell(7);
                        cellTotal06.setCellFormula(totalGross);
                        cellTotal06.setCellStyle(styleC25);
                        HSSFCell cellTotal07 = rowtotal.createCell(14);
                        cellTotal07.setCellFormula(totalVat);
                        cellTotal07.setCellStyle(styleC25);
                        
                        sheetOutbound.addMergedRegion(CellRangeAddress.valueOf("H"+(tempcountO+1)+":N"+(tempcountO+1)));
                        sheetOutbound.addMergedRegion(CellRangeAddress.valueOf("O"+(tempcountO+1)+":T"+(tempcountO+1)));
                        
                        for(int k = 8 ; k < 20 ; k++){
                            if(k != 7 && k != 14){
                                HSSFCell cell0000 = rowtotal.createCell(k);
                                cell0000.setCellStyle(styleC25);
                            }
                        }
                    }

                    // set Header Report (Row 1)
                    HSSFCellStyle styleC1 = wb.createCellStyle();
                    styleC1.setAlignment(styleC1.ALIGN_CENTER);
                    HSSFRow row1 = sheetInbound.createRow(0);
                    HSSFCell cell1 = row1.createCell(0);
                    cell1.setCellValue("รายงานภาษีขาย");
                    styleC1.setFont(fontHeader);
                    cell1.setCellStyle(styleC1);
                    sheetInbound.addMergedRegion(CellRangeAddress.valueOf("A1:T1"));

                    // Row 2
                    HSSFRow row2 = sheetInbound.createRow(1);
                    HSSFCell cell21 = row2.createCell(0);
                    cell21.setCellValue("เดือนภาษี  " + data.getHeaderMonth() + " ปี " +data.getHeaderYear());
                    styleC23.setFont(fontHeaderDetail);
                    cell21.setCellStyle(styleC23);
                    sheetInbound.addMergedRegion(CellRangeAddress.valueOf("A2:T2"));

                    // Row 3
                    HSSFRow row3 = sheetInbound.createRow(2);
                    HSSFCell cell31 = row3.createCell(0);
                    cell31.setCellValue("ชื่อผู้ประกอบการ บริษัท เอส.เอ็ม.ไอ แทรเวล จำกัด");
                    cell31.setCellStyle(styleC22);
                    sheetInbound.addMergedRegion(CellRangeAddress.valueOf("A3:D3"));
                    HSSFCell cell32 = row3.createCell(5);
                    cell32.setCellValue("สำนักงานใหญ่ ");
                    cell32.setCellStyle(styleC21);
                    sheetInbound.addMergedRegion(CellRangeAddress.valueOf("F3:J3"));
                    HSSFCell cell33 = row3.createCell(11);
                    cell33.setCellValue("");
                    cell33.setCellStyle(styleSymbol);
                    HSSFCell cell34 = row3.createCell(12);
                    cell34.setCellValue("สาขา");
                    cell34.setCellStyle(styleC21);
                    sheetInbound.addMergedRegion(CellRangeAddress.valueOf("M3:O3"));
                    HSSFCell cell35 = row3.createCell(16);
                    cell35.setCellStyle(styleSymbol);
                    HSSFCell cell36 = row3.createCell(17);
                    cell36.setCellStyle(styleSymbol);
                    HSSFCell cell37 = row3.createCell(18);
                    cell37.setCellStyle(styleSymbol);

                    HSSFRow rowX = sheetInbound.createRow(3);
                    rowX.setHeightInPoints((short) 2);
                    
                    // Row 4
                    HSSFRow row4 = sheetInbound.createRow(4);
                    HSSFCell cell41 = row4.createCell(0);
                    cell41.setCellValue("ชื่อสถานประกอบการ บริษัท เอส.เอ็ม.ไอ แทรเวล จำกัด");
                    cell41.setCellStyle(styleC22);
                    sheetInbound.addMergedRegion(CellRangeAddress.valueOf("A5:D5"));
                    HSSFCell cell42 = row4.createCell(4);
                    cell42.setCellValue("เลขประจำตัวผู้เสียภาษีอากร  ");
                    cell42.setCellStyle(styleC21);
                    sheetInbound.addMergedRegion(CellRangeAddress.valueOf("E5:G5"));
                    HSSFCell cell43 = row4.createCell(7);
                    cell43.setCellValue("0");
                    cell43.setCellStyle(styleC26);
                    HSSFCell cell44 = row4.createCell(8);
                    cell44.setCellValue("1");
                    cell44.setCellStyle(styleC26);
                    HSSFCell cell45 = row4.createCell(9);
                    cell45.setCellValue("0");
                    cell45.setCellStyle(styleC26);
                    HSSFCell cell46 = row4.createCell(10);
                    cell46.setCellValue("5");
                    cell46.setCellStyle(styleC26);
                    HSSFCell cell47 = row4.createCell(11);
                    cell47.setCellValue("5");
                    cell47.setCellStyle(styleC26);
                    HSSFCell cell48 = row4.createCell(12);
                    cell48.setCellValue("2");
                    cell48.setCellStyle(styleC26);
                    HSSFCell cell49 = row4.createCell(13);
                    cell49.setCellValue("3");
                    cell49.setCellStyle(styleC26);
                    HSSFCell cell50 = row4.createCell(14);
                    cell50.setCellValue("0");
                    cell50.setCellStyle(styleC26);
                    HSSFCell cell51 = row4.createCell(15);
                    cell51.setCellValue("2");
                    cell51.setCellStyle(styleC26);
                    HSSFCell cell52 = row4.createCell(16);
                    cell52.setCellValue("0");
                    cell52.setCellStyle(styleC26);
                    HSSFCell cell53 = row4.createCell(17);
                    cell53.setCellValue("1");
                    cell53.setCellStyle(styleC26);
                    HSSFCell cell54 = row4.createCell(18);
                    cell54.setCellValue("3");
                    cell54.setCellStyle(styleC26);
                    HSSFCell cell55 = row4.createCell(19);
                    cell55.setCellValue("2");
                    cell55.setCellStyle(styleC26);
                    
                    //wendy
                    HSSFRow row5 = sheetInbound.createRow(6);
                    HSSFCell cell81 = row5.createCell(0);
                    cell81.setCellValue("ลำดับที่");
                    cell81.setCellStyle(styleC3);
                    sheetInbound.addMergedRegion(CellRangeAddress.valueOf("A7:A10"));
                    HSSFCell cell82 = row5.createCell(1);
                    cell82.setCellValue("ใบกำกับภาษี");
                    cell82.setCellStyle(styleC3);
                    sheetInbound.addMergedRegion(CellRangeAddress.valueOf("B7:C8"));
                    HSSFCell cell83 = row5.createCell(3);
                    cell83.setCellValue("รายการ");
                    cell83.setCellStyle(styleC3);
                    sheetInbound.addMergedRegion(CellRangeAddress.valueOf("D7:D10"));
                    HSSFCell cell84 = row5.createCell(4);
                    cell84.setCellValue("เลขประจำตัวผู้เสียภาษีของผู้ซื้อสินค้า/ผู้รับบริการ");
                    cell84.setCellStyle(styleC3);
                    sheetInbound.addMergedRegion(CellRangeAddress.valueOf("E7:E10"));
                    HSSFCell cell85 = row5.createCell(5);
                    cell85.setCellValue("สถานประกอบการ");
                    cell85.setCellStyle(styleC3);
                    sheetInbound.addMergedRegion(CellRangeAddress.valueOf("F7:G8"));
                    HSSFCell cell86 = row5.createCell(7);
                    cell86.setCellValue("มูลค่าสินค้าหรือบริการ");
                    cell86.setCellStyle(styleC3);
                    sheetInbound.addMergedRegion(CellRangeAddress.valueOf("H7:N10"));
                    HSSFCell cell87 = row5.createCell(14);
                    cell87.setCellValue("จำนวนเงินภาษีมูลค่าเพิ่ม");
                    cell87.setCellStyle(styleC3);
                    sheetInbound.addMergedRegion(CellRangeAddress.valueOf("O7:T10"));
                                       
                    HSSFRow row7 = sheetInbound.createRow(8);
                    HSSFCell cell88 = row7.createCell(1);
                    cell88.setCellValue("วัน/เดือน/ปี");
                    cell88.setCellStyle(styleC3);
                    sheetInbound.addMergedRegion(CellRangeAddress.valueOf("B9:B10"));
                    HSSFCell cell89 = row7.createCell(2);
                    cell89.setCellValue("เลขที่");
                    cell89.setCellStyle(styleC3);
                    sheetInbound.addMergedRegion(CellRangeAddress.valueOf("C9:C10"));
                    HSSFCell cell90 = row7.createCell(5);
                    cell90.setCellValue("สำนักงานใหญ่");
                    cell90.setCellStyle(styleC3);
                    sheetInbound.addMergedRegion(CellRangeAddress.valueOf("F9:F10"));
                    HSSFCell cell91 = row7.createCell(6);
                    cell91.setCellValue("สาขาที่");
                    cell91.setCellStyle(styleC3);
                    sheetInbound.addMergedRegion(CellRangeAddress.valueOf("G9:G10"));
                    
                    HSSFCell cell92 = row5.createCell(2);
                    cell92.setCellStyle(styleC3);
                    HSSFCell cell93 = row5.createCell(6);
                    cell93.setCellStyle(styleC3);
                    HSSFCell cell008 = row5.createCell(8);
                    cell008.setCellStyle(styleC3);
                    HSSFCell cell009 = row5.createCell(9);
                    cell009.setCellStyle(styleC3);
                    HSSFCell cell010 = row5.createCell(10);
                    cell010.setCellStyle(styleC3);
                    HSSFCell cell011 = row5.createCell(11);
                    cell011.setCellStyle(styleC3);
                    HSSFCell cell012 = row5.createCell(12);
                    cell012.setCellStyle(styleC3);
                    HSSFCell cell013 = row5.createCell(13);
                    cell013.setCellStyle(styleC3);
                    HSSFCell cell014 = row5.createCell(15);
                    cell014.setCellStyle(styleC3);
                    HSSFCell cell015 = row5.createCell(16);
                    cell015.setCellStyle(styleC3);
                    HSSFCell cell016 = row5.createCell(17);
                    cell016.setCellStyle(styleC3);
                    HSSFCell cell017 = row5.createCell(18);
                    cell017.setCellStyle(styleC3);
                    HSSFCell cell018 = row5.createCell(19);
                    cell018.setCellStyle(styleC3);
                                        
                    HSSFCell cell94 = row7.createCell(0);
                    cell94.setCellStyle(styleC3);
                    HSSFCell cell95 = row7.createCell(3);
                    cell95.setCellStyle(styleC3);
                    HSSFCell cell96 = row7.createCell(4);
                    cell96.setCellStyle(styleC3);
                    HSSFCell cell97 = row7.createCell(7);
                    cell97.setCellStyle(styleC3);
                    HSSFCell cell98 = row7.createCell(8);
                    cell98.setCellStyle(styleC3);
                    HSSFCell cell99 = row7.createCell(13);
                    cell99.setCellStyle(styleC3);
                    HSSFCell cell100 = row7.createCell(19);
                    cell100.setCellStyle(styleC3);
                    
                    HSSFRow row6 = sheetInbound.createRow(7);
                    HSSFCell cell000 = row6.createCell(0);
                    cell000.setCellStyle(styleC3);
                    HSSFCell cell001 = row6.createCell(1);
                    cell001.setCellStyle(styleC3);
                    HSSFCell cell002 = row6.createCell(2);
                    cell002.setCellStyle(styleC3);
                    HSSFCell cell003 = row6.createCell(3);
                    cell003.setCellStyle(styleC3);
                    HSSFCell cell004 = row6.createCell(4);
                    cell004.setCellStyle(styleC3);
                    HSSFCell cell005 = row6.createCell(5);
                    cell005.setCellStyle(styleC3);
                    HSSFCell cell006 = row6.createCell(6);
                    cell006.setCellStyle(styleC3);
                    HSSFCell cell007 = row6.createCell(7);
                    cell007.setCellStyle(styleC3);
                    HSSFCell cell00008 = row6.createCell(13);
                    cell00008.setCellStyle(styleC3);
                    HSSFCell cell00009 = row6.createCell(19);
                    cell00009.setCellStyle(styleC3);
                    
                    HSSFRow row8 = sheetInbound.createRow(9);
                    for(int k = 0 ; k < 20 ; k++){
                        HSSFCell cell0000 = row8.createCell(k);
                        cell0000.setCellStyle(styleC3);
                    }
                    
                    HSSFRow row = sheetInbound.createRow(count + y);
                    HSSFCell celldata0 = row.createCell(0);
                    celldata0.setCellValue(String.valueOf(data.getOrder()));
                    celldata0.setCellStyle(styleC26);

                    HSSFCell celldata1 = row.createCell(1);
                    celldata1.setCellValue(String.valueOf(data.getTaxdate()));
                    celldata1.setCellStyle(styleC30);

                    HSSFCell celldata2 = row.createCell(2);
                    celldata2.setCellValue(String.valueOf(data.getTaxno()));
                    celldata2.setCellStyle(styleC30);

                    HSSFCell celldata3 = row.createCell(3);
                    celldata3.setCellValue(String.valueOf(data.getDescription()));
                    celldata3.setCellStyle(styleC25);

                    HSSFCell celldata4 = row.createCell(4);
                    celldata4.setCellValue(String.valueOf(data.getAgttaxno()));
                    celldata4.setCellStyle(styleC25);

                    HSSFCell celldata5 = row.createCell(5);
                    if("1".equalsIgnoreCase(String.valueOf(data.getMain()))){
                        celldata5.setCellValue("");
                    }else{
                        celldata5.setCellValue("");
                    }
                    celldata5.setCellStyle(styleSymbol);

                    HSSFCell celldata6 = row.createCell(6);
                    celldata6.setCellValue(String.valueOf(data.getBranchno()));
                    celldata6.setCellStyle(styleC30);

                    HSSFCell celldata7 = row.createCell(7);
                    celldata7.setCellValue("".equalsIgnoreCase(String.valueOf(data.getGross())) ? 0 : (data.getGross()).doubleValue());
                    celldata7.setCellStyle(styleC25);

                    HSSFCell celldata8 = row.createCell(14);
                    celldata8.setCellValue("".equalsIgnoreCase(String.valueOf(data.getVat())) ? 0 : (data.getVat()).doubleValue());
                    celldata8.setCellStyle(styleC25);
                    
                    sheetInbound.addMergedRegion(CellRangeAddress.valueOf("H"+(count+y+1)+":N"+(count+y+1)));
                    sheetInbound.addMergedRegion(CellRangeAddress.valueOf("O"+(count+y+1)+":T"+(count+y+1)));
                    
                    for(int k = 8 ; k < 20 ; k++){
                        if(k != 7 && k != 14){
                            HSSFCell cell0000 = row.createCell(k);
                            cell0000.setCellStyle(styleC25);
                        }
                    }
                    
                    if(data.getGross() != null){
                        grossI = grossI.add(data.getGross());
                    }else{
                        grossI = grossI.add(BigDecimal.ZERO);
                    }
                    
                    if(data.getVat() != null){
                        vatI = vatI.add(data.getVat());
                    }else{
                        vatI = vatI.add(BigDecimal.ZERO);
                    }
                    
                    y++;
                }
                
                if(i==(outputTaxViewList.size()-1)){
                    if( y != 0 ){
                        tempcountI = count+y;
                        HSSFRow rowtotal = sheetInbound.createRow(tempcountI);
                        
                        String totalGross = "SUM(H" + 11+":H"+(tempcountI)+")";
                        String totalVat = "SUM(O" + 11+":O"+(tempcountI)+")";

                        HSSFCellStyle styleTotal = wb.createCellStyle();
                        styleTotal.setFont(excelFunction.getHeaderTable(wb.createFont()));
                        styleTotal.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                        styleTotal.setBorderRight(HSSFCellStyle.BORDER_THIN);
                        styleTotal.setBorderTop(HSSFCellStyle.BORDER_THIN);
                        styleTotal.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                        styleTotal.setAlignment(styleC22.ALIGN_RIGHT);

                        HSSFCell cellTotal0 = rowtotal.createCell(0);
                        cellTotal0.setCellStyle(stylebordertotalleft);
                        HSSFCell cellTotal00 = rowtotal.createCell(1);
                        cellTotal00.setCellStyle(stylebordertotal);
                        HSSFCell cellTotal01 = rowtotal.createCell(2);
                         cellTotal01.setCellStyle(stylebordertotal);
                        HSSFCell cellTotal02 = rowtotal.createCell(3);
                        cellTotal02.setCellStyle(stylebordertotal);
                        HSSFCell cellTotal03 = rowtotal.createCell(4);
                        cellTotal03.setCellStyle(stylebordertotal);
                        HSSFCell cellTotal04 = rowtotal.createCell(5);
                        cellTotal04.setCellStyle(stylebordertotal);
                        HSSFCell cellTotal05 = rowtotal.createCell(6);
                        cellTotal05.setCellValue("TOTAL INBOUND : ");
                        cellTotal05.setCellStyle(styleC4);
                        HSSFCell cellTotal06 = rowtotal.createCell(7);
                        cellTotal06.setCellFormula(totalGross);
                        cellTotal06.setCellStyle(styleC25);
                        HSSFCell cellTotal07 = rowtotal.createCell(14);
                        cellTotal07.setCellFormula(totalVat);
                        cellTotal07.setCellStyle(styleC25);
                        
                        					
			sheetInbound.addMergedRegion(CellRangeAddress.valueOf("H"+(tempcountI+1)+":N"+(tempcountI+1)));
                        sheetInbound.addMergedRegion(CellRangeAddress.valueOf("O"+(tempcountI+1)+":T"+(tempcountI+1)));
                        
                        for(int k = 8 ; k < 20 ; k++){
                            if(k != 7 && k != 14){
                                HSSFCell cell0000 = rowtotal.createCell(k);
                                cell0000.setCellStyle(styleC25);
                            }
                        }
                        
                        HSSFRow rowtotalbor = sheetInbound.createRow(tempcountI+1);
                        HSSFCell cellTotalBor0 = rowtotalbor.createCell(0);
                        cellTotalBor0.setCellStyle(stylebordertotalleft);
                        HSSFCell cellTotalBor00 = rowtotalbor.createCell(1);
                        cellTotalBor00.setCellStyle(stylebordertotal);
                        HSSFCell cellTotalBor01 = rowtotalbor.createCell(2);
                        cellTotalBor01.setCellStyle(stylebordertotal);
                        HSSFCell cellTotalBor02 = rowtotalbor.createCell(3);
                        cellTotalBor02.setCellStyle(stylebordertotal);
                        HSSFCell cellTotalBor03 = rowtotalbor.createCell(4);
                        cellTotalBor03.setCellStyle(stylebordertotal);
                        HSSFCell cellTotalBor04 = rowtotalbor.createCell(5);
                        cellTotalBor04.setCellStyle(stylebordertotal);
                        
                        HSSFRow rowtotalwen = sheetInbound.createRow(tempcountI+2);
                        HSSFCell cellTotalWen0 = rowtotalwen.createCell(0);
                        cellTotalWen0.setCellStyle(stylebordertotalleft);
                        HSSFCell cellTotalWen00 = rowtotalwen.createCell(1);
                        cellTotalWen00.setCellStyle(stylebordertotal);
                        HSSFCell cellTotalWen01 = rowtotalwen.createCell(2);
                         cellTotalWen01.setCellStyle(stylebordertotal);
                        HSSFCell cellTotalWen02 = rowtotalwen.createCell(3);
                        cellTotalWen02.setCellStyle(stylebordertotal);
                        HSSFCell cellTotalWen03 = rowtotalwen.createCell(4);
                        cellTotalWen03.setCellStyle(stylebordertotal);
                        HSSFCell cellTotalWen04 = rowtotalwen.createCell(5);
                        cellTotalWen04.setCellStyle(stylebordertotal);
                        HSSFCell cellTotalWen05 = rowtotalwen.createCell(6);
                        cellTotalWen05.setCellValue("TOTAL WENDY : ");
                        cellTotalWen05.setCellStyle(styleC4);
                        HSSFCell cellTotalWen06 = rowtotalwen.createCell(7);
                        cellTotalWen06.setCellValue((grossW).doubleValue());
                        cellTotalWen06.setCellStyle(styleC25);
                        HSSFCell cellTotalWen07 = rowtotalwen.createCell(14);
                        cellTotalWen07.setCellValue((vatW).doubleValue());
                        cellTotalWen07.setCellStyle(styleC25);
                        
                        sheetInbound.addMergedRegion(CellRangeAddress.valueOf("H"+(tempcountI+3)+":N"+(tempcountI+3)));
                        sheetInbound.addMergedRegion(CellRangeAddress.valueOf("O"+(tempcountI+3)+":T"+(tempcountI+3)));
                        
                        for(int k = 8 ; k < 20 ; k++){
                            if(k != 7 && k != 14){
                                HSSFCell cell0000 = rowtotalwen.createCell(k);
                                cell0000.setCellStyle(styleC25);
                            }
                        }
                        
                        
                        HSSFRow rowtotalout = sheetInbound.createRow(tempcountI+3);
                        HSSFCell cellTotalOut0 = rowtotalout.createCell(0);
                        cellTotalOut0.setCellStyle(stylebordertotalleft);
                        HSSFCell cellTotalOut00 = rowtotalout.createCell(1);
                        cellTotalOut00.setCellStyle(stylebordertotal);
                        HSSFCell cellTotalOut01 = rowtotalout.createCell(2);
                         cellTotalOut01.setCellStyle(stylebordertotal);
                        HSSFCell cellTotalOut02 = rowtotalout.createCell(3);
                        cellTotalOut02.setCellStyle(stylebordertotal);
                        HSSFCell cellTotalOut03 = rowtotalout.createCell(4);
                        cellTotalOut03.setCellStyle(stylebordertotal);
                        HSSFCell cellTotalOut04 = rowtotalout.createCell(5);
                        cellTotalOut04.setCellStyle(stylebordertotal);
                        HSSFCell cellTotalOut05 = rowtotalout.createCell(6);
                        cellTotalOut05.setCellValue("TOTAL OUTBOUND : ");
                        cellTotalOut05.setCellStyle(styleC4);
                        HSSFCell cellTotalOut06 = rowtotalout.createCell(7);
                        cellTotalOut06.setCellValue((grossO).doubleValue());
                        cellTotalOut06.setCellStyle(styleC25);
                        HSSFCell cellTotalOut07 = rowtotalout.createCell(14);
                        cellTotalOut07.setCellValue((vatO).doubleValue());
                        cellTotalOut07.setCellStyle(styleC25);
                        
                        sheetInbound.addMergedRegion(CellRangeAddress.valueOf("H"+(tempcountI+4)+":N"+(tempcountI+4)));
                        sheetInbound.addMergedRegion(CellRangeAddress.valueOf("O"+(tempcountI+4)+":T"+(tempcountI+4)));
                        
                        for(int k = 8 ; k < 20 ; k++){
                            if(k != 7 && k != 14){
                                HSSFCell cell0000 = rowtotalout.createCell(k);
                                cell0000.setCellStyle(styleC25);
                            }
                        }
                        
                        HSSFRow rowtotalin = sheetInbound.createRow(tempcountI+4);
                        HSSFCell cellTotalIn0 = rowtotalin.createCell(0);
                        cellTotalIn0.setCellStyle(stylebordertotalleft);
                        HSSFCell cellTotalIn00 = rowtotalin.createCell(1);
                        cellTotalIn00.setCellStyle(stylebordertotal);
                        HSSFCell cellTotalIn01 = rowtotalin.createCell(2);
                         cellTotalIn01.setCellStyle(stylebordertotal);
                        HSSFCell cellTotalIn02 = rowtotalin.createCell(3);
                        cellTotalIn02.setCellStyle(stylebordertotal);
                        HSSFCell cellTotalIn03 = rowtotalin.createCell(4);
                        cellTotalIn03.setCellStyle(stylebordertotal);
                        HSSFCell cellTotalIn04 = rowtotalin.createCell(5);
                        cellTotalIn04.setCellStyle(stylebordertotal);
                        HSSFCell cellTotalIn05 = rowtotalin.createCell(6);
                        cellTotalIn05.setCellValue("TOTAL INBOUND : ");
                        cellTotalIn05.setCellStyle(styleC4);
                        HSSFCell cellTotalIn06 = rowtotalin.createCell(7);
                        cellTotalIn06.setCellValue((grossI).doubleValue());
                        cellTotalIn06.setCellStyle(styleC25);
                        HSSFCell cellTotalIn07 = rowtotalin.createCell(14);
                        cellTotalIn07.setCellValue((vatI).doubleValue());
                        cellTotalIn07.setCellStyle(styleC25);
                        
                        sheetInbound.addMergedRegion(CellRangeAddress.valueOf("H"+(tempcountI+5)+":N"+(tempcountI+5)));
                        sheetInbound.addMergedRegion(CellRangeAddress.valueOf("O"+(tempcountI+5)+":T"+(tempcountI+5)));
                        
                        for(int k = 8 ; k < 20 ; k++){
                            if(k != 7 && k != 14){
                                HSSFCell cell0000 = rowtotalin.createCell(k);
                                cell0000.setCellStyle(styleC25);
                            }
                        }
                        
                        String totalGrossAll = "SUM(H" + (tempcountI+3)+":H"+(tempcountI+5)+")";
                        String totalVatAll = "SUM(O" + (tempcountI+3)+":O"+(tempcountI+5)+")";
                        
                        HSSFRow rowtotalall = sheetInbound.createRow(tempcountI+5);
                        HSSFCell cellTotalAll0 = rowtotalall.createCell(0);
                        cellTotalAll0.setCellStyle(stylebordertotalleft);
                        HSSFCell cellTotalAll00 = rowtotalall.createCell(1);
                        cellTotalAll00.setCellStyle(stylebordertotal);
                        HSSFCell cellTotalAll01 = rowtotalall.createCell(2);
                         cellTotalAll01.setCellStyle(stylebordertotal);
                        HSSFCell cellTotalAll02 = rowtotalall.createCell(3);
                        cellTotalAll02.setCellStyle(stylebordertotal);
                        HSSFCell cellTotalAll03 = rowtotalall.createCell(4);
                        cellTotalAll03.setCellStyle(stylebordertotal);
                        HSSFCell cellTotalAll04 = rowtotalall.createCell(5);
                        cellTotalAll04.setCellStyle(stylebordertotal);
                        HSSFCell cellTotalAll05 = rowtotalall.createCell(6);
                        cellTotalAll05.setCellValue("TOTAL WENDY + OUTBOUND + INBOUND : ");
                        cellTotalAll05.setCellStyle(styleC4);
                        HSSFCell cellTotalAll06 = rowtotalall.createCell(7);
                        cellTotalAll06.setCellFormula(totalGrossAll);
                        cellTotalAll06.setCellStyle(styleC25);
                        HSSFCell cellTotalAll07 = rowtotalall.createCell(14);
                        cellTotalAll07.setCellFormula(totalVatAll);
                        cellTotalAll07.setCellStyle(styleC25);
                        
                        sheetInbound.addMergedRegion(CellRangeAddress.valueOf("H"+(tempcountI+6)+":N"+(tempcountI+6)));
                        sheetInbound.addMergedRegion(CellRangeAddress.valueOf("O"+(tempcountI+6)+":T"+(tempcountI+6)));
                        
                        for(int k = 8 ; k < 20 ; k++){
                            if(k != 7 && k != 14){
                                HSSFCell cell0000 = rowtotalall.createCell(k);
                                cell0000.setCellStyle(styleC25);
                            }
                        }
                        
                    }
                }
//                for (int j = 0; j < 4; j++) {
//                    sheetWendy.autoSizeColumn(j, true);
//                    sheetOutbound.autoSizeColumn(j, true);
//                    sheetInbound.autoSizeColumn(j, true);
//                }
        }
    }
    
    
}