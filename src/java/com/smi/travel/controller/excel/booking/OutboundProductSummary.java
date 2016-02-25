/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.controller.excel.booking;

import com.smi.travel.controller.excel.master.UtilityExcelFunction;
import com.smi.travel.datalayer.entity.PaymentOutbound;
import com.smi.travel.datalayer.report.model.BillAirAgent;
import com.smi.travel.datalayer.view.entity.OutboundProductSummaryExcel;
import com.smi.travel.datalayer.view.entity.PaymentOutboundAllDetail;
import com.smi.travel.datalayer.view.entity.PaymentOutboundSummary;
import com.smi.travel.datalayer.view.entity.PaymentProfitLossView;
import java.math.BigDecimal;
import java.math.BigInteger;
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
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/**
 *
 * @author Surachai
 */
public class OutboundProductSummary extends AbstractExcelView  {
    private static final String OutboundProduct = "OutboundProduct";
    private static final String PaymentSummaryReport = "PaymentSummaryReport";   
    private static final String PaymentProfitLossSummary = "PaymentProfitLossSummary";
    @Override
    protected void buildExcelDocument(Map model, HSSFWorkbook workbook,HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = (String) model.get("name");
        System.out.println("name : " + name);
        response.setHeader("Content-disposition", "attachment; filename=" + name + ".xls");
        
        if(name.equalsIgnoreCase(OutboundProduct)){
            System.out.println("gen report OutboundProductSummary");
            getOutboundProductSummary(workbook, (List) model.get(name));
        }else if(name.equalsIgnoreCase(PaymentSummaryReport)){
            System.out.println("gen report PaymentSummaryReport");
            getPaymentSummary(workbook, (List) model.get(name));
        }else if(name.equalsIgnoreCase(PaymentProfitLossSummary)){
            System.out.println("gen report PaymentProfitLossSummary");
            getPaymentProfitLossSummary(workbook, (List) model.get(name));
        }
    }
    
    public void getPaymentProfitLossSummary(HSSFWorkbook wb, List listPayment) {
        UtilityExcelFunction excelFunction = new UtilityExcelFunction();
        String sheetName = "Sheet1";// name of sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        
        PaymentProfitLossView dataheader = new PaymentProfitLossView();
        
        HSSFDataFormat currency = wb.createDataFormat();
        
        // Set align Text
        HSSFCellStyle styleC21 = wb.createCellStyle();
        styleC21.setAlignment(styleC21.ALIGN_RIGHT);
        styleC21.setDataFormat(currency.getFormat("#,##0"));
        HSSFCellStyle styleC22 = wb.createCellStyle();
        styleC22.setAlignment(styleC22.ALIGN_LEFT);
        
        HSSFCellStyle styleC23 = wb.createCellStyle();
        styleC23.setAlignment(styleC23.ALIGN_CENTER);
        
        HSSFCellStyle styleGroup = wb.createCellStyle();
        styleGroup.setAlignment(styleGroup.ALIGN_CENTER);
        styleGroup.setFont(excelFunction.getHeaderTable(wb.createFont()));
        
        HSSFCellStyle styleGroupdata = wb.createCellStyle();
        styleGroupdata.setAlignment(styleGroupdata.ALIGN_LEFT);
//        styleGroupdata.setFont(excelFunction.getHeaderTable(wb.createFont()));
        
        HSSFCellStyle styleC25 = wb.createCellStyle();
        styleC25.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC25.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC25.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleC25.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC25.setDataFormat(currency.getFormat("#,##0.00"));
        styleC25.setVerticalAlignment(styleC25.VERTICAL_CENTER);
        
        HSSFCellStyle styleC26 = wb.createCellStyle();
        styleC26.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC26.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC26.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleC26.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC26.setDataFormat(currency.getFormat("#,##0"));
        styleC26.setAlignment(styleC26.ALIGN_CENTER);
        styleC26.setVerticalAlignment(styleC26.VERTICAL_CENTER);
//        styleC26.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
//        styleC26.setFillPattern(styleC26.SOLID_FOREGROUND);
        
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
//        styleC29.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
//        styleC29.setFillPattern(styleC29.SOLID_FOREGROUND);
        
        HSSFCellStyle styleC30 = wb.createCellStyle();
        styleC30.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC30.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC30.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleC30.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC30.setAlignment(styleC22.ALIGN_CENTER);
        styleC30.setWrapText(true);
        styleC30.setVerticalAlignment(styleC30.VERTICAL_CENTER);
//        styleC30.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
//        styleC30.setFillPattern(styleC30.SOLID_FOREGROUND);
        
        HSSFCellStyle styleTotal = wb.createCellStyle();
        styleTotal.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleTotal.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleTotal.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleTotal.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleTotal.setWrapText(true);
        styleTotal.setVerticalAlignment(styleTotal.VERTICAL_CENTER);
        styleTotal.setAlignment(styleTotal.ALIGN_CENTER);
        styleTotal.setFont(excelFunction.getHeadDetailBoldFont(wb.createFont()));
        styleTotal.setDataFormat(currency.getFormat("#,##0"));
//        styleTotal.setFillForegroundColor(IndexedColors.BLUE.getIndex());
//        styleTotal.setFillPattern(styleTotal.SOLID_FOREGROUND);
        
        HSSFCellStyle styleTotalCurrency = wb.createCellStyle();
        styleTotalCurrency.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleTotalCurrency.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleTotalCurrency.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleTotalCurrency.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleTotalCurrency.setWrapText(true);
        styleTotalCurrency.setVerticalAlignment(styleTotalCurrency.VERTICAL_CENTER);
        styleTotalCurrency.setAlignment(styleTotalCurrency.ALIGN_RIGHT);
        styleTotalCurrency.setFont(excelFunction.getHeadDetailBoldFont(wb.createFont()));
        styleTotalCurrency.setDataFormat(currency.getFormat("#,##0.00"));
//        styleTotalCurrency.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
//        styleTotalCurrency.setFillPattern(styleTotalCurrency.SOLID_FOREGROUND);
        
        if(!listPayment.isEmpty()){
            dataheader = (PaymentProfitLossView)listPayment.get(0);
            // set Header Report (Row 1)
            HSSFCellStyle styleC1 = wb.createCellStyle();
            HSSFRow row1 = sheet.createRow(0);
            HSSFCell cell1 = row1.createCell(0);
            cell1.setCellValue("Profit & Loss Summary");
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
            cell24.setCellValue(dataheader.getHeaderinvsup());
            cell24.setCellStyle(styleC22);

            // Row 3
            HSSFRow row3 = sheet.createRow(2);
            HSSFCell cell31 = row3.createCell(0);
            cell31.setCellValue("City : ");
            cell31.setCellStyle(styleC21);
            HSSFCell cell32 = row3.createCell(1);
            cell32.setCellValue(dataheader.getHeadercity());
            cell32.setCellStyle(styleC22);
            sheet.addMergedRegion(CellRangeAddress.valueOf("B3:D3"));
            HSSFCell cell33 = row3.createCell(4);
            cell33.setCellValue("Group : ");
            cell33.setCellStyle(styleC21);
            HSSFCell cell34 = row3.createCell(5);
            cell34.setCellValue(dataheader.getHeadergroup());
            cell34.setCellStyle(styleC22);

            // Row 4
            HSSFRow row4 = sheet.createRow(3);
            HSSFCell cell41 = row4.createCell(0);
            cell41.setCellValue("Product Type : ");
            cell41.setCellStyle(styleC21);
            HSSFCell cell42 = row4.createCell(1);
            cell42.setCellValue(dataheader.getHeaderproducttype());
            cell42.setCellStyle(styleC22);
            sheet.addMergedRegion(CellRangeAddress.valueOf("B4:D4"));
            HSSFCell cell43 = row4.createCell(4);
            cell43.setCellValue("Pay Date : ");
            cell43.setCellStyle(styleC21);
            HSSFCell cell44 = row4.createCell(5);
            cell44.setCellValue(dataheader.getHeaderpaydate());
            cell44.setCellStyle(styleC22);

            // Row 5
            HSSFRow row5 = sheet.createRow(4);
            HSSFCell cell51 = row5.createCell(0);
            cell51.setCellValue("Departure Date : ");
            cell51.setCellStyle(styleC21);
            HSSFCell cell52 = row5.createCell(1);
            cell52.setCellValue(dataheader.getHeaderdepartdate());
            cell52.setCellStyle(styleC22);
            sheet.addMergedRegion(CellRangeAddress.valueOf("B5:D5"));
            HSSFCell cell53 = row5.createCell(4);
            cell53.setCellValue("Invoice Date : ");
            cell53.setCellStyle(styleC21);
            HSSFCell cell54 = row5.createCell(5);
            cell54.setCellValue(dataheader.getHeaderinvdate());
            cell54.setCellStyle(styleC22);
        }

//        errorOccured = true;
// XSSFCellStyle style = workbook.createCellStyle();
// style.setFillForegroundColor(IndexedColors.RED.getIndex());
// style.setFillPattern(CellStyle.SOLID_FOREGROUND);
// row.getCell(0).setCellStyle(style);
        // Header Table
        HSSFCellStyle styleC3 = wb.createCellStyle();
        styleC3.setFont(excelFunction.getHeaderTable(wb.createFont()));
        styleC3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC3.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC3.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleC3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC3.setAlignment(styleC3.ALIGN_CENTER);
//        styleC3.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
//        styleC3.setFillPattern(styleC3.SOLID_FOREGROUND);
        // Detail of Table
        String temp = "";
        int count = 6;
        int ktemp = 9 ;
        String temprefno = "";
        //Total
        BigDecimal totalpax = new BigDecimal("0.00");
        BigDecimal totalsale = new BigDecimal("0.00");
        BigDecimal totalcost = new BigDecimal("0.00");
        BigDecimal totalprofit = new BigDecimal("0.00");
        
        BigDecimal totalpaxAll = new BigDecimal("0.00");
        BigDecimal totalsaleAll = new BigDecimal("0.00");
        BigDecimal totalcostAll = new BigDecimal("0.00");
        BigDecimal totalprofitAll = new BigDecimal("0.00");

        for(int i=0;i<listPayment.size();i++){
            PaymentProfitLossView data = (PaymentProfitLossView)listPayment.get(i);
            String groupby = data.getHeadergroup();
            String groupbytemp = "";
            
            if("OWNER".equalsIgnoreCase(groupby)){
                groupbytemp = data.getOwner();
            }else if("PRODUCT TYPE".equalsIgnoreCase(groupby)){
                groupbytemp = data.getProducttype();
            }else if("CITY".equalsIgnoreCase(groupby)){
                groupbytemp = data.getCity();
            }else if("CLIENT NAME".equalsIgnoreCase(groupby)){
                groupbytemp = data.getInvto();
            }else if("DEPARTURE DATE".equalsIgnoreCase(groupby)){
                groupbytemp = data.getDepartdate();
            }     
            
            if(!temprefno.equalsIgnoreCase(data.getRefno())){
               if(!"".equalsIgnoreCase(temprefno)){
                    HSSFRow rowtotal = sheet.createRow(count + i);
                    // Set align Text
                    HSSFCell cellTotal = rowtotal.createCell(0);
                    cellTotal.setCellValue("Total");
                    cellTotal.setCellStyle(styleTotal);
                    if("CLIENT NAME".equalsIgnoreCase(groupby)){
                        HSSFCell cellTotal01 = rowtotal.createCell(5);
                        cellTotal01.setCellValue(totalpax.doubleValue());
                        cellTotal01.setCellStyle(styleC26);
                        sheet.addMergedRegion(CellRangeAddress.valueOf("B"+(count + i + 1)+":E"+(count + i + 1)));
                        sheet.addMergedRegion(CellRangeAddress.valueOf("G"+(count + i + 1)+":M"+(count + i + 1)));
                        for(int j = 1 ; j < 16 ; j ++){
                            if(j != 5 && j != 13 && j != 14 && j != 15 ){
                                HSSFCell cellTotal05 = rowtotal.createCell(j);
                                cellTotal05.setCellStyle(styleC29);
                            }
                        }
                    }else{
                        HSSFCell cellTotal01 = rowtotal.createCell(4);
                        cellTotal01.setCellValue(totalpax.doubleValue());
                        cellTotal01.setCellStyle(styleC26);
                        sheet.addMergedRegion(CellRangeAddress.valueOf("B"+(count + i + 1)+":D"+(count + i + 1)));
                        sheet.addMergedRegion(CellRangeAddress.valueOf("F"+(count + i + 1)+":M"+(count + i + 1)));
                        for(int j = 1 ; j < 16 ; j ++){
                            if(j != 4 && j != 13 && j != 14 && j != 15 ){
                                HSSFCell cellTotal05 = rowtotal.createCell(j);
                                cellTotal05.setCellStyle(styleC29);
                            }
                        }
                    }
                    HSSFCell cellTotal02 = rowtotal.createCell(13);
                    cellTotal02.setCellValue(totalsale.doubleValue());
                    cellTotal02.setCellStyle(styleC25);
                    HSSFCell cellTotal03 = rowtotal.createCell(14);
                    cellTotal03.setCellValue(totalcost.doubleValue());
                    cellTotal03.setCellStyle(styleC25);
                    HSSFCell cellTotal04 = rowtotal.createCell(15);
                    cellTotal04.setCellValue(totalprofit.doubleValue());
                    cellTotal04.setCellStyle(styleC25);
                    
                    
                    totalpaxAll = totalpaxAll.add(totalpax);
                    totalsaleAll = totalsaleAll.add(totalsale);
                    totalcostAll = totalcostAll.add(totalcost);
                    totalprofitAll = totalprofitAll.add(totalprofit);
                    
                    totalpax = new BigDecimal(BigInteger.ZERO) ;
                    totalsale = new BigDecimal(BigInteger.ZERO) ;
                    totalcost = new BigDecimal(BigInteger.ZERO) ;
                    totalprofit = new BigDecimal(BigInteger.ZERO) ;
                    
                    
                    count = count+1;
                    ktemp = count+2+i;
               }
            } 
            
            if(!temp.equalsIgnoreCase(groupbytemp)){
                if(!"".equalsIgnoreCase(temp)){
                    HSSFRow row = sheet.createRow(count + i);
                    // Set align Text
                    HSSFCell cellTotal = row.createCell(0);
                    cellTotal.setCellValue("");
                    cellTotal.setCellStyle(styleC30);
                    if("CLIENT NAME".equalsIgnoreCase(groupby)){
                        HSSFCell cellTotal01 = row.createCell(5);
                        cellTotal01.setCellValue(totalpaxAll.doubleValue());
                        cellTotal01.setCellStyle(styleTotal);
                        sheet.addMergedRegion(CellRangeAddress.valueOf("A"+(count + i + 1)+":E"+(count + i + 1)));
                        sheet.addMergedRegion(CellRangeAddress.valueOf("G"+(count + i + 1)+":M"+(count + i + 1)));
                        for(int j = 1 ; j < 16 ; j ++){
                            if(j != 5 && j != 13 && j != 14 && j != 15 ){
                                HSSFCell cellTotal05 = row.createCell(j);
                                cellTotal05.setCellStyle(styleC29);
                            }
                        }
                    }else{
                        HSSFCell cellTotal01 = row.createCell(4);
                        cellTotal01.setCellValue(totalpaxAll.doubleValue());
                        cellTotal01.setCellStyle(styleTotal);
                        sheet.addMergedRegion(CellRangeAddress.valueOf("A"+(count + i + 1)+":D"+(count + i + 1)));
                        sheet.addMergedRegion(CellRangeAddress.valueOf("F"+(count + i + 1)+":M"+(count + i + 1)));
                        for(int j = 1 ; j < 16 ; j ++){
                            if(j != 4 && j != 13 && j != 14 && j != 15 ){
                                HSSFCell cellTotal05 = row.createCell(j);
                                cellTotal05.setCellStyle(styleC29);
                            }
                        }
                    }
                    HSSFCell cellTotal02 = row.createCell(13);
                    cellTotal02.setCellValue(totalsaleAll.doubleValue());
                    cellTotal02.setCellStyle(styleTotalCurrency);
                    HSSFCell cellTotal03 = row.createCell(14);
                    cellTotal03.setCellValue(totalcostAll.doubleValue());
                    cellTotal03.setCellStyle(styleTotalCurrency);
                    HSSFCell cellTotal04 = row.createCell(15);
                    cellTotal04.setCellValue(totalprofitAll.doubleValue());
                    cellTotal04.setCellStyle(styleTotalCurrency);
                    
                    totalpaxAll = new BigDecimal(BigInteger.ZERO) ;
                    totalsaleAll = new BigDecimal(BigInteger.ZERO) ;
                    totalcostAll = new BigDecimal(BigInteger.ZERO) ;
                    totalprofitAll = new BigDecimal(BigInteger.ZERO) ;
                    
                    count = count+2;
                    ktemp = count+3+i;
                }

                int counts = count+i;
                int countss = count+1+i;
                System.out.println(" Print Header Table Group count ::  " + count + " ==== i ==== " + i);
                // Row Agent Name
                System.out.println(" Print Header Group counts ::  " + counts);
                HSSFRow row008 = sheet.createRow(counts);
                HSSFCell cell0081 = row008.createCell(0);
                cell0081.setCellValue(groupby);
                cell0081.setCellStyle(styleGroup);
                HSSFCell cell0082 = row008.createCell(1);
                cell0082.setCellValue(groupbytemp);
                cell0082.setCellStyle(styleGroupdata);
                temp = groupbytemp;
                
                System.out.println(" Print Header Table countss ::  " + countss);
                HSSFRow row09 = sheet.createRow(countss);
                HSSFCell cell091 = row09.createCell(0);
                cell091.setCellValue("REF NO");
                cell091.setCellStyle(styleC3);
                sheet.autoSizeColumn(0);
                    System.out.println(" groupby " + groupby);
                if("OWNER".equalsIgnoreCase(groupby)){
                    HSSFCell cell092 = row09.createCell(1);
                    cell092.setCellValue("TOUR NAME");
                    cell092.setCellStyle(styleC3);
                    sheet.autoSizeColumn(1);
                    HSSFCell cell093 = row09.createCell(2);
                    cell093.setCellValue("DEPARTURE DATE");
                    sheet.autoSizeColumn(2);
                    cell093.setCellStyle(styleC3);
                    HSSFCell cell094 = row09.createCell(3);
                    cell094.setCellValue("CITY");
                    cell094.setCellStyle(styleC3);
                    sheet.autoSizeColumn(3);
                    HSSFCell cell095 = row09.createCell(4);
                    cell095.setCellValue("PAX"); 
                    cell095.setCellStyle(styleC3);
                    sheet.autoSizeColumn(4);
                    HSSFCell cell096 = row09.createCell(5);
                    cell096.setCellValue("CLIENT NAME"); 
                    cell096.setCellStyle(styleC3);
                    sheet.autoSizeColumn(5);
                }else if("PRODUCT TYPE".equalsIgnoreCase(groupby)){
                   
                }else if("CITY".equalsIgnoreCase(groupby)){
                    HSSFCell cell092 = row09.createCell(1);
                    cell092.setCellValue("OWNER");
                    cell092.setCellStyle(styleC3);
                    sheet.autoSizeColumn(1);
                    HSSFCell cell093 = row09.createCell(2);
                    cell093.setCellValue("TOUR NAME"); 
                    sheet.autoSizeColumn(2);
                    cell093.setCellStyle(styleC3);
                    HSSFCell cell094 = row09.createCell(3);
                    cell094.setCellValue("DEPARTURE DATE");
                    cell094.setCellStyle(styleC3);
                    sheet.autoSizeColumn(3);
                    HSSFCell cell095 = row09.createCell(4);
                    cell095.setCellValue("PAX"); 
                    cell095.setCellStyle(styleC3);
                    sheet.autoSizeColumn(4);
                    HSSFCell cell096 = row09.createCell(5);
                    cell096.setCellValue("CLIENT NAME"); 
                    cell096.setCellStyle(styleC3);
                    sheet.autoSizeColumn(5);
                }else if("CLIENT NAME".equalsIgnoreCase(groupby)){
                    HSSFCell cell092 = row09.createCell(1);
                    cell092.setCellValue("OWNER");
                    cell092.setCellStyle(styleC3);
                    sheet.autoSizeColumn(1);
                    HSSFCell cell093 = row09.createCell(2);
                    cell093.setCellValue("TOUR NAME");
                    sheet.autoSizeColumn(2);
                    cell093.setCellStyle(styleC3);
                    HSSFCell cell094 = row09.createCell(3);
                    cell094.setCellValue("DEPARTURE DATE");
                    cell094.setCellStyle(styleC3);
                    sheet.autoSizeColumn(3);
                    HSSFCell cell095 = row09.createCell(4);
                    cell095.setCellValue("CITY"); 
                    cell095.setCellStyle(styleC3);
                    sheet.autoSizeColumn(4);
                    HSSFCell cell096 = row09.createCell(5);
                    cell096.setCellValue("PAX"); 
                    cell096.setCellStyle(styleC3);
                    sheet.autoSizeColumn(5);
                }else if("DEPARTURE DATE".equalsIgnoreCase(groupby)){
                    HSSFCell cell092 = row09.createCell(1);
                    cell092.setCellValue("OWNER");
                    cell092.setCellStyle(styleC3);
                    sheet.autoSizeColumn(1);
                    HSSFCell cell093 = row09.createCell(2);
                    cell093.setCellValue("TOUR NAME");
                    sheet.autoSizeColumn(2);
                    cell093.setCellStyle(styleC3);
                    HSSFCell cell094 = row09.createCell(3);
                    cell094.setCellValue("CITY");
                    cell094.setCellStyle(styleC3);
                    sheet.autoSizeColumn(3);
                    HSSFCell cell095 = row09.createCell(4);
                    cell095.setCellValue("PAX"); 
                    cell095.setCellStyle(styleC3);
                    sheet.autoSizeColumn(4);
                    HSSFCell cell096 = row09.createCell(5);
                    cell096.setCellValue("CLIENT NAME"); 
                    cell096.setCellStyle(styleC3);
                    sheet.autoSizeColumn(5);
                }
                
                HSSFCell cell097 = row09.createCell(6);
                cell097.setCellValue("INVOICE NO");
                cell097.setCellStyle(styleC3);
                sheet.autoSizeColumn(6);
                HSSFCell cell098 = row09.createCell(7);
                cell098.setCellValue("RECEIPT NO");
                cell098.setCellStyle(styleC3);
                sheet.autoSizeColumn(7);
                HSSFCell cell099 = row09.createCell(8);
                cell099.setCellValue("TAX INVOICE NO");
                cell099.setCellStyle(styleC3);
                sheet.autoSizeColumn(8);
                HSSFCell cell100 = row09.createCell(9);
                cell100.setCellValue("PRODUCT TYPE");
                cell100.setCellStyle(styleC3);
                sheet.autoSizeColumn(9);
                HSSFCell cell101 = row09.createCell(10);
                cell101.setCellValue("DESCRIPTION");
                cell101.setCellStyle(styleC3);
                sheet.autoSizeColumn(10);
                HSSFCell cell102 = row09.createCell(11);
                cell102.setCellValue("PAY NO");
                cell102.setCellStyle(styleC3);
                sheet.autoSizeColumn(11);
                HSSFCell cell103 = row09.createCell(12);
                cell103.setCellValue("SUPPLIER");
                cell103.setCellStyle(styleC3);
                sheet.autoSizeColumn(12);
                HSSFCell cell104 = row09.createCell(13);
                cell104.setCellValue("SALE PRICE");
                cell104.setCellStyle(styleC3);
                sheet.autoSizeColumn(13);
                HSSFCell cell105 = row09.createCell(14);
                cell105.setCellValue("COST");
                cell105.setCellStyle(styleC3);
                sheet.autoSizeColumn(14);
                HSSFCell cell106 = row09.createCell(15);
                cell106.setCellValue("PROFIT");
                cell106.setCellStyle(styleC3);
                sheet.autoSizeColumn(15);
                count = count+2;
            }  

            //set data 
            System.out.println(" Print Data Table count ::  " + count + " ==== i ==== " + i);
            HSSFRow row = sheet.createRow(count + i);
            if(!temprefno.equalsIgnoreCase(data.getRefno())){
                HSSFCell celldata01 = row.createCell(0);
                celldata01.setCellValue(data.getRefno());
                celldata01.setCellStyle(styleC29);
                
                if("OWNER".equalsIgnoreCase(groupby)){
                    HSSFCell celldata02 = row.createCell(1);
                    celldata02.setCellValue(data.getTourname());
                    celldata02.setCellStyle(styleC29);
                    HSSFCell celldata03 = row.createCell(2);
                    celldata03.setCellValue(data.getDepartdate()); 
                    celldata03.setCellStyle(styleC29);
                    HSSFCell celldata04 = row.createCell(3);
                    celldata04.setCellValue(data.getCity());
                    celldata04.setCellStyle(styleC29);
                    HSSFCell celldata05 = row.createCell(4);
                    celldata05.setCellValue("".equalsIgnoreCase(String.valueOf(data.getPax())) ? 0 : new BigDecimal(data.getPax()).doubleValue());
                    celldata05.setCellStyle(styleC26);
                    HSSFCell celldata06 = row.createCell(5);
                    celldata06.setCellValue(data.getInvto());
                    celldata06.setCellStyle(styleC29);
                    sheet.setColumnWidth(3, 256*15);
                }else if("PRODUCT TYPE".equalsIgnoreCase(groupby)){

                }else if("CITY".equalsIgnoreCase(groupby)){
                    HSSFCell celldata02 = row.createCell(1);
                    celldata02.setCellValue(data.getOwner());
                    celldata02.setCellStyle(styleC29);
                    HSSFCell celldata03 = row.createCell(2);
                    celldata03.setCellValue(data.getTourname()); 
                    celldata03.setCellStyle(styleC29);
                    HSSFCell celldata04 = row.createCell(3);
                    celldata04.setCellValue(data.getDepartdate());
                    celldata04.setCellStyle(styleC29);
                    HSSFCell celldata05 = row.createCell(4);
                    celldata05.setCellValue("".equalsIgnoreCase(String.valueOf(data.getPax())) ? 0 : new BigDecimal(data.getPax()).doubleValue());
                    celldata05.setCellStyle(styleC26);
                    HSSFCell celldata06 = row.createCell(5);
                    celldata06.setCellValue(data.getInvto());
                    celldata06.setCellStyle(styleC29);
                }else if("CLIENT NAME".equalsIgnoreCase(groupby)){
                    HSSFCell celldata02 = row.createCell(1);
                    celldata02.setCellValue(data.getOwner());
                    celldata02.setCellStyle(styleC29);
                    HSSFCell celldata03 = row.createCell(2);
                    celldata03.setCellValue(data.getTourname()); 
                    celldata03.setCellStyle(styleC29);
                    HSSFCell celldata04 = row.createCell(3);
                    celldata04.setCellValue(data.getDepartdate());
                    celldata04.setCellStyle(styleC29);
                    HSSFCell celldata05 = row.createCell(4);
                    celldata05.setCellValue(data.getCity());
                    celldata05.setCellStyle(styleC29);
                    HSSFCell celldata06 = row.createCell(5);
                    celldata06.setCellValue("".equalsIgnoreCase(String.valueOf(data.getPax())) ? 0 : new BigDecimal(data.getPax()).doubleValue());
                    celldata06.setCellStyle(styleC26);
                    sheet.setColumnWidth(4, 256*15);
                }else if("DEPARTURE DATE".equalsIgnoreCase(groupby)){
                    HSSFCell celldata02 = row.createCell(1);
                    celldata02.setCellValue(data.getOwner());
                    celldata02.setCellStyle(styleC29);
                    HSSFCell celldata03 = row.createCell(2);
                    celldata03.setCellValue(data.getTourname()); 
                    celldata03.setCellStyle(styleC29);
                    HSSFCell celldata04 = row.createCell(3);
                    celldata04.setCellValue(data.getCity());
                    celldata04.setCellStyle(styleC29);
                    HSSFCell celldata05 = row.createCell(4);
                    celldata05.setCellValue("".equalsIgnoreCase(String.valueOf(data.getPax())) ? 0 : new BigDecimal(data.getPax()).doubleValue());
                    celldata05.setCellStyle(styleC26);
                    HSSFCell celldata06 = row.createCell(5);
                    celldata06.setCellValue(data.getInvto());
                    celldata06.setCellStyle(styleC29);
                    sheet.setColumnWidth(3, 256*15);
                }
            }else{
                for(int j  = 0 ; j < 6 ; j++){
                    HSSFCell celldata02 = row.createCell(j);
                    celldata02.setCellStyle(styleC29);
                }
                sheet.addMergedRegion(CellRangeAddress.valueOf("A"+(count + i + 1)+":F"+(count + i + 1)));
            }
            
            HSSFCell celldata07 = row.createCell(6);
            celldata07.setCellValue(data.getInvno()); 
            celldata07.setCellStyle(styleC29);
            HSSFCell celldata08 = row.createCell(7);
            celldata08.setCellValue(data.getReceipt());
            celldata08.setCellStyle(styleC29);
            HSSFCell celldata09 = row.createCell(8);
            celldata09.setCellValue(data.getTaxinvoice());
            celldata09.setCellStyle(styleC29);
            HSSFCell celldata10 = row.createCell(9);
            celldata10.setCellValue(data.getProducttype());
            celldata10.setCellStyle(styleC29);
            HSSFCell celldata11 = row.createCell(10);
            celldata11.setCellValue(data.getDescription());
            celldata11.setCellStyle(styleC29);
            HSSFCell celldata12 = row.createCell(11);
            celldata12.setCellValue(data.getPayno());
            celldata12.setCellStyle(styleC29);
            HSSFCell celldata13 = row.createCell(12);
            celldata13.setCellValue(data.getSupplier());
            celldata13.setCellStyle(styleC29);
            HSSFCell celldata14 = row.createCell(13);
            celldata14.setCellValue("".equalsIgnoreCase(String.valueOf(data.getSale())) ? 0 : new BigDecimal(data.getSale()).doubleValue());
            celldata14.setCellStyle(styleC25);
            HSSFCell celldata15 = row.createCell(14);
            celldata15.setCellValue("".equalsIgnoreCase(String.valueOf(data.getCost())) ? 0 : new BigDecimal(data.getCost()).doubleValue());
            celldata15.setCellStyle(styleC25);
            HSSFCell celldata16 = row.createCell(15);
            celldata16.setCellValue("".equalsIgnoreCase(String.valueOf(data.getProfit())) ? 0 : new BigDecimal(data.getProfit()).doubleValue());
            celldata16.setCellStyle(styleC25);

            totalpax = ("".equalsIgnoreCase(String.valueOf(data.getPax())) ? new BigDecimal(BigInteger.ZERO): new BigDecimal(data.getPax()));
            totalsale = totalsale.add("".equalsIgnoreCase(String.valueOf(data.getSale())) ? new BigDecimal(BigInteger.ZERO): new BigDecimal(data.getSale()));
            totalcost = totalcost.add("".equalsIgnoreCase(String.valueOf(data.getCost())) ? new BigDecimal(BigInteger.ZERO): new BigDecimal(data.getCost()));
            totalprofit = totalprofit.add("".equalsIgnoreCase(String.valueOf(data.getProfit())) ? new BigDecimal(BigInteger.ZERO): new BigDecimal(data.getProfit()));
            
            temprefno = data.getRefno();
            
            // set total last row
            if(i == (listPayment.size()-1)) {
                
                HSSFRow rowtotal = sheet.createRow(count + i + 1);
                    // Set align Text
                    HSSFCell cellTotal = rowtotal.createCell(0);
                    cellTotal.setCellValue("Total");
                    cellTotal.setCellStyle(styleTotal);
                    if("CLIENT NAME".equalsIgnoreCase(groupby)){
                        HSSFCell cellTotal01 = rowtotal.createCell(5);
                        cellTotal01.setCellValue(totalpax.doubleValue());
                        cellTotal01.setCellStyle(styleC26);
                        sheet.addMergedRegion(CellRangeAddress.valueOf("B"+(count + i + 2)+":E"+(count + i + 2)));
                        sheet.addMergedRegion(CellRangeAddress.valueOf("G"+(count + i + 2)+":M"+(count + i + 2)));
                        for(int j = 1 ; j < 16 ; j ++){
                            if(j != 5 && j != 13 && j != 14 && j != 15 ){
                                HSSFCell cellTotal05 = rowtotal.createCell(j);
                                cellTotal05.setCellStyle(styleTotal);
                            }
                        }
                    }else{
                        HSSFCell cellTotal01 = rowtotal.createCell(4);
                        cellTotal01.setCellValue(totalpax.doubleValue());
                        cellTotal01.setCellStyle(styleC26);
                        sheet.addMergedRegion(CellRangeAddress.valueOf("B"+(count + i + 2)+":D"+(count + i + 2)));
                        sheet.addMergedRegion(CellRangeAddress.valueOf("F"+(count + i + 2)+":M"+(count + i + 2)));
                        for(int j = 1 ; j < 16 ; j ++){
                            if(j != 4 && j != 13 && j != 14 && j != 15 ){
                                HSSFCell cellTotal05 = rowtotal.createCell(j);
                                cellTotal05.setCellStyle(styleC29);
                            }
                        }
                    }
                    HSSFCell cellTotal02 = rowtotal.createCell(13);
                    cellTotal02.setCellValue(totalsale.doubleValue());
                    cellTotal02.setCellStyle(styleC25);
                    HSSFCell cellTotal03 = rowtotal.createCell(14);
                    cellTotal03.setCellValue(totalcost.doubleValue());
                    cellTotal03.setCellStyle(styleC25);
                    HSSFCell cellTotal04 = rowtotal.createCell(15);
                    cellTotal04.setCellValue(totalprofit.doubleValue());
                    cellTotal04.setCellStyle(styleC25);
                    
                    totalpaxAll = totalpaxAll.add(totalpax);
                    totalsaleAll = totalsaleAll.add(totalsale);
                    totalcostAll = totalcostAll.add(totalcost);
                    totalprofitAll = totalprofitAll.add(totalprofit);
                    
                    
//                    count = count+1;
//                    ktemp = count+2+i;
                
                    row = sheet.createRow(count + i + 2);
                    // Set align Text
                    HSSFCell cellTotalAll = row.createCell(0);
                    cellTotalAll.setCellValue("");
                    cellTotalAll.setCellStyle(styleTotal);
                    if("CLIENT NAME".equalsIgnoreCase(groupby)){
                        HSSFCell cellTotal01 = row.createCell(5);
                        cellTotal01.setCellValue(totalpaxAll.doubleValue());
                        cellTotal01.setCellStyle(styleTotal);
                        sheet.addMergedRegion(CellRangeAddress.valueOf("A"+(count + i + 3)+":E"+(count + i + 3)));
                        sheet.addMergedRegion(CellRangeAddress.valueOf("G"+(count + i + 3)+":M"+(count + i + 3)));
                        for(int j = 1 ; j < 16 ; j ++){
                            if(j != 5 && j != 13 && j != 14 && j != 15 ){
                                HSSFCell cellTotal05 = row.createCell(j);
                                cellTotal05.setCellStyle(styleC29);
                            }
                        }
                    }else{
                        HSSFCell cellTotal01 = row.createCell(4);
                        cellTotal01.setCellValue(totalpaxAll.doubleValue());
                        cellTotal01.setCellStyle(styleTotal);
                        sheet.addMergedRegion(CellRangeAddress.valueOf("A"+(count + i + 3)+":D"+(count + i + 3)));
                        sheet.addMergedRegion(CellRangeAddress.valueOf("F"+(count + i + 3)+":M"+(count + i + 3)));
                        for(int j = 1 ; j < 16 ; j ++){
                            if(j != 4 && j != 13 && j != 14 && j != 15 ){
                                HSSFCell cellTotal05 = row.createCell(j);
                                cellTotal05.setCellStyle(styleC29);
                            }
                        }
                    }
                    HSSFCell cellTotalAll02 = row.createCell(13);
                    cellTotalAll02.setCellValue(totalsaleAll.doubleValue());
                    cellTotalAll02.setCellStyle(styleTotalCurrency);
                    HSSFCell cellTotalAll03 = row.createCell(14);
                    cellTotalAll03.setCellValue(totalcostAll.doubleValue());
                    cellTotalAll03.setCellStyle(styleTotalCurrency);
                    HSSFCell cellTotalAll04 = row.createCell(15);
                    cellTotalAll04.setCellValue(totalprofitAll.doubleValue());
                    cellTotalAll04.setCellStyle(styleTotalCurrency);      
            }
        }
        sheet.setColumnWidth(6, 256*25);
        sheet.setColumnWidth(7, 256*25);
        sheet.setColumnWidth(8, 256*25);
        
        sheet.setColumnWidth(13, 256*15);
        sheet.setColumnWidth(14, 256*15);
        sheet.setColumnWidth(15, 256*15);
    }

    private void getOutboundProductSummary(HSSFWorkbook wb, List listOutboundProductSummary) {
        List<OutboundProductSummaryExcel> listOutboundProduct = listOutboundProductSummary;
        String sheetName = "Sheet1";// name of sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        UtilityExcelFunction excelFunction = new UtilityExcelFunction();
        // Set align Text
        HSSFCellStyle styleAlignRight = wb.createCellStyle();
        styleAlignRight.setAlignment(styleAlignRight.ALIGN_RIGHT);
        HSSFCellStyle styleAlignLeft = wb.createCellStyle();
        styleAlignLeft.setAlignment(styleAlignLeft.ALIGN_LEFT);
        HSSFDataFormat currency = wb.createDataFormat();
        // Set align Text
        HSSFCellStyle styleNumber = wb.createCellStyle();
        styleNumber.setAlignment(styleNumber.ALIGN_RIGHT);
        styleNumber.setDataFormat(currency.getFormat("#,##0.00"));
        
         // set Header Report (Row 1)
        HSSFCellStyle styleHeader1 = wb.createCellStyle();
        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cell1 = row1.createCell(0);
        cell1.setCellValue("Outbound Product Summary");
        styleHeader1.setFont(excelFunction.getHeaderFont(wb.createFont()));
        cell1.setCellStyle(styleHeader1);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:I1"));
        
        if(listOutboundProduct != null && listOutboundProduct.size() != 0){
            OutboundProductSummaryExcel outboundProduct = new OutboundProductSummaryExcel();
            outboundProduct = listOutboundProduct.get(0);
            
            HSSFRow row2 = sheet.createRow(1);
            HSSFCell cell21 = row2.createCell(0);
                cell21.setCellValue("Product : ");
                cell21.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(0);
            HSSFCell cell22 = row2.createCell(1);
                cell22.setCellValue(outboundProduct.getProductnamepage());
                cell22.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(1);
            HSSFCell cell23 = row2.createCell(2);
                cell23.setCellValue("Pay By : ");
                cell23.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(2);
            HSSFCell cell24 = row2.createCell(3);
                cell24.setCellValue(outboundProduct.getPaybypage());
                cell24.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(3);
                
            HSSFRow row3 = sheet.createRow(2);
            HSSFCell cell31 = row3.createCell(0);
                cell31.setCellValue("Sale Date : ");
                cell31.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(0);
            HSSFCell cell32 = row3.createCell(1);
                cell32.setCellValue(outboundProduct.getSaledatepage());
                cell32.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(1);
            HSSFCell cell33 = row3.createCell(2);
                cell33.setCellValue("Bank Transfer : ");
                cell33.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(2);
            HSSFCell cell34 = row3.createCell(3);
                cell34.setCellValue(outboundProduct.getBankpage());
                cell34.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(3);
                
            HSSFRow row4 = sheet.createRow(3);
            HSSFCell cell41 = row4.createCell(0);
                cell41.setCellValue("Sale By : ");
                cell41.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(0);
            HSSFCell cell42 = row4.createCell(1);
                cell42.setCellValue(outboundProduct.getSalebypage());
                cell42.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(1);
            HSSFCell cell43 = row4.createCell(2);
                cell43.setCellValue("Status : ");
                cell43.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(2);
            HSSFCell cell44 = row4.createCell(3);
                cell44.setCellValue(outboundProduct.getStatuspage());
                cell44.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(3);
        }
        
        // Header Table
        HSSFCellStyle styleHeader = wb.createCellStyle();
            styleHeader.setFont(excelFunction.getHeaderTable(wb.createFont()));
            styleHeader.setAlignment(styleHeader.ALIGN_CENTER);
            styleHeader.setBorderTop(styleHeader.BORDER_THIN);
            styleHeader.setBorderLeft(styleHeader.BORDER_THIN);
            styleHeader.setBorderBottom(styleHeader.BORDER_THIN);
            styleHeader.setBorderRight(styleHeader.BORDER_THIN);
            styleHeader.setVerticalAlignment(styleHeader.VERTICAL_CENTER);
        HSSFCellStyle styleDetailTable = wb.createCellStyle();
            styleDetailTable.setAlignment(styleDetailTable.ALIGN_LEFT);
            styleDetailTable.setBorderLeft(styleDetailTable.BORDER_THIN);
            styleDetailTable.setBorderRight(styleDetailTable.BORDER_THIN);
            styleDetailTable.setVerticalAlignment(styleDetailTable.VERTICAL_CENTER);
        HSSFCellStyle styleDetailTableCenter = wb.createCellStyle();
            styleDetailTableCenter.setAlignment(styleDetailTableCenter.ALIGN_CENTER);
            styleDetailTableCenter.setBorderTop(styleDetailTableCenter.BORDER_THIN);
            styleDetailTableCenter.setBorderBottom(styleDetailTableCenter.BORDER_THIN);
            styleDetailTableCenter.setBorderRight(styleDetailTableCenter.BORDER_THIN);
            styleDetailTableCenter.setBorderLeft(styleDetailTableCenter.BORDER_THIN);
            styleDetailTableCenter.setWrapText(true);
            styleDetailTableCenter.setVerticalAlignment(styleDetailTableCenter.VERTICAL_CENTER);
        HSSFCellStyle styleDetailTableNumber = wb.createCellStyle();
            styleDetailTableNumber.setAlignment(styleDetailTableNumber.ALIGN_RIGHT);
            styleDetailTableNumber.setBorderLeft(styleDetailTableNumber.BORDER_THIN);
            styleDetailTableNumber.setBorderRight(styleDetailTableNumber.BORDER_THIN);
            styleDetailTableNumber.setDataFormat(currency.getFormat("#,##0.00"));
            styleDetailTableNumber.setWrapText(true);
            styleDetailTableNumber.setVerticalAlignment(styleDetailTableNumber.VERTICAL_CENTER);
        HSSFCellStyle styleDetailTableBorderBottom = wb.createCellStyle();
            styleDetailTableBorderBottom.setBorderTop(styleDetailTableBorderBottom.BORDER_THIN);
        HSSFCellStyle styleBorderTop =  wb.createCellStyle();
            styleBorderTop.setBorderBottom(styleBorderTop.BORDER_THIN);
            styleBorderTop.setFont(excelFunction.getHeaderTable(wb.createFont()));
            styleBorderTop.setAlignment(styleBorderTop.ALIGN_CENTER);
        HSSFCellStyle styleBorderRight =  wb.createCellStyle();
            styleBorderRight.setBorderRight(styleBorderRight.BORDER_THIN);
            styleBorderRight.setAlignment(styleBorderRight.ALIGN_CENTER);
        HSSFCellStyle styleBorderBottomAndRight =  wb.createCellStyle();
            styleBorderBottomAndRight.setBorderRight(styleBorderBottomAndRight.BORDER_THIN);
            styleBorderBottomAndRight.setBorderBottom(styleBorderBottomAndRight.BORDER_THIN);
            styleBorderBottomAndRight.setAlignment(styleBorderBottomAndRight.ALIGN_CENTER);
            styleBorderBottomAndRight.setVerticalAlignment(styleBorderBottomAndRight.VERTICAL_CENTER);
            
            HSSFCellStyle styleAlignLeftBorderTopRight = wb.createCellStyle(); // use
                styleAlignLeftBorderTopRight.setAlignment(styleAlignLeftBorderTopRight.ALIGN_LEFT);
                styleAlignLeftBorderTopRight.setBorderTop(styleAlignLeftBorderTopRight.BORDER_THIN);
                styleAlignLeftBorderTopRight.setBorderRight(styleAlignLeftBorderTopRight.BORDER_THIN);
                styleAlignLeftBorderTopRight.setVerticalAlignment(styleAlignLeftBorderTopRight.VERTICAL_CENTER);
        HSSFCellStyle styleAlignLeftBorderTopLeft = wb.createCellStyle(); // use
                styleAlignLeftBorderTopLeft.setAlignment(styleAlignLeftBorderTopLeft.ALIGN_LEFT);
                styleAlignLeftBorderTopLeft.setBorderTop(styleAlignLeftBorderTopLeft.BORDER_THIN);
                styleAlignLeftBorderTopLeft.setBorderLeft(styleAlignLeftBorderTopLeft.BORDER_THIN);
                styleAlignLeftBorderTopLeft.setVerticalAlignment(styleAlignLeftBorderTopLeft.VERTICAL_CENTER);
        HSSFCellStyle styleBorderTopP = wb.createCellStyle(); // use
                styleBorderTopP.setBorderTop(styleBorderTopP.BORDER_THIN);
        HSSFCellStyle styleAlignRightBorderBottomRight = wb.createCellStyle();//use
                styleAlignRightBorderBottomRight.setAlignment(styleAlignRightBorderBottomRight.ALIGN_LEFT);
                styleAlignRightBorderBottomRight.setBorderBottom(styleAlignRightBorderBottomRight.BORDER_THIN);
                styleAlignRightBorderBottomRight.setBorderRight(styleAlignRightBorderBottomRight.BORDER_THIN);
                styleAlignRightBorderBottomRight.setVerticalAlignment(styleAlignRightBorderBottomRight.VERTICAL_CENTER);
        HSSFCellStyle styleAlignRightBorderBottomLeft = wb.createCellStyle();
                styleAlignRightBorderBottomLeft.setAlignment(styleAlignRightBorderBottomLeft.ALIGN_LEFT);
                styleAlignRightBorderBottomLeft.setBorderBottom(styleAlignRightBorderBottomLeft.BORDER_THIN);
                styleAlignRightBorderBottomLeft.setBorderLeft(styleAlignRightBorderBottomLeft.BORDER_THIN);
                styleAlignRightBorderBottomLeft.setVerticalAlignment(styleAlignRightBorderBottomLeft.VERTICAL_CENTER);
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
        HSSFCellStyle styleAlignRightBorderAllNumber = wb.createCellStyle();
                styleAlignRightBorderAllNumber.setAlignment(styleAlignRightBorderAllNumber.ALIGN_RIGHT);
                styleAlignRightBorderAllNumber.setDataFormat(currency.getFormat("#,##0.00"));
                styleAlignRightBorderAllNumber.setBorderTop(styleAlignRightBorderAllNumber.BORDER_THIN);
                styleAlignRightBorderAllNumber.setBorderBottom(styleAlignRightBorderAllNumber.BORDER_THIN);
                styleAlignRightBorderAllNumber.setBorderRight(styleAlignRightBorderAllNumber.BORDER_THIN);
                styleAlignRightBorderAllNumber.setBorderLeft(styleAlignRightBorderAllNumber.BORDER_THIN);
                styleAlignRightBorderAllNumber.setVerticalAlignment(styleAlignRightBorderAllNumber.VERTICAL_CENTER);
        HSSFCellStyle styleAlignRightBorderAll = wb.createCellStyle();
                styleAlignRightBorderAll.setAlignment(styleAlignRightBorderAll.ALIGN_LEFT);
                styleAlignRightBorderAll.setBorderTop(styleAlignRightBorderAll.BORDER_THIN);
                styleAlignRightBorderAll.setBorderBottom(styleAlignRightBorderAll.BORDER_THIN);
                styleAlignRightBorderAll.setBorderRight(styleAlignRightBorderAll.BORDER_THIN);
                styleAlignRightBorderAll.setBorderLeft(styleAlignRightBorderAll.BORDER_THIN);
                styleAlignRightBorderAll.setVerticalAlignment(styleAlignRightBorderAll.VERTICAL_CENTER);
                styleAlignRightBorderAll.setWrapText(true);
        HSSFRow row4 = sheet.createRow(6);
            row4.createCell(7).setCellStyle(styleBorderTop);
            row4.createCell(8).setCellStyle(styleBorderTop);
            row4.createCell(9).setCellStyle(styleBorderTop);
            row4.createCell(10).setCellStyle(styleBorderTop);
            row4.createCell(11).setCellStyle(styleBorderTop);
            row4.createCell(12).setCellStyle(styleBorderTop);
            row4.createCell(13).setCellStyle(styleBorderTop);
            row4.createCell(14).setCellStyle(styleBorderTop);
            row4.createCell(15).setCellStyle(styleBorderTop);
        HSSFRow row5 = sheet.createRow(7);
        HSSFCell cell61 = row5.createCell(0);
            cell61.setCellValue("SALE DATE");
            cell61.setCellStyle(styleHeader);
            sheet.autoSizeColumn(0);
        HSSFCell cell62 = row5.createCell(1);
            cell62.setCellValue("RECORD NO");
            cell62.setCellStyle(styleHeader);
            sheet.autoSizeColumn(1);
        HSSFCell cell63 = row5.createCell(2);
            cell63.setCellValue("REF NO");
            cell63.setCellStyle(styleHeader);
            sheet.autoSizeColumn(2);
//        HSSFCell cell64 = row5.createCell(3);
//            cell64.setCellValue("PRODUCT NAME");
//            cell64.setCellStyle(styleHeader);
//            sheet.autoSizeColumn(3);
        HSSFCell cell65 = row5.createCell(3);
            cell65.setCellValue("PASS NO");
            cell65.setCellStyle(styleHeader);
            sheet.autoSizeColumn(3);
        HSSFCell cell66 = row5.createCell(4);
            cell66.setCellValue("DULATION");
            cell66.setCellStyle(styleHeader);
            sheet.autoSizeColumn(4);
        HSSFCell cell67 = row5.createCell(5);
            cell67.setCellValue("INV NO");
            cell67.setCellStyle(styleHeader);
            sheet.autoSizeColumn(5);
        HSSFCell cell68 = row5.createCell(6);
            cell68.setCellValue("CUSTOMER NAME");
            cell68.setCellStyle(styleHeader);
            sheet.autoSizeColumn(6);
        HSSFCell cell59 = row5.createCell(7);
            cell59.setCellValue("PAX");
            cell59.setCellStyle(styleBorderTop);
            sheet.autoSizeColumn(7);
            sheet.addMergedRegion(CellRangeAddress.valueOf("H8:J8"));
        row5.createCell(9).setCellStyle(styleBorderRight);         
        HSSFCell cell627 = row5.createCell(10);
            cell627.setCellValue("TOTAL NETT");
            cell627.setCellStyle(styleBorderTop);
            sheet.autoSizeColumn(10);
            sheet.addMergedRegion(CellRangeAddress.valueOf("K8:M8"));
//        row5.createCell(12).setCellStyle(styleBorderTop);
        row5.createCell(12).setCellStyle(styleBorderRight);
        HSSFCell cell657 = row5.createCell(13);
            cell657.setCellValue("TOTAL SALE");
            cell657.setCellStyle(styleBorderTop);
            sheet.autoSizeColumn(13);
            sheet.addMergedRegion(CellRangeAddress.valueOf("N8:P8"));
//        row5.createCell(15).setCellStyle(styleBorderTop);
        row5.createCell(15).setCellStyle(styleBorderRight);
        HSSFCell cell78 = row5.createCell(16);
            cell78.setCellValue("PROFIT TOTAL");
            cell78.setCellStyle(styleHeader);
            sheet.autoSizeColumn(16);
        HSSFCell cell79 = row5.createCell(17);
            cell79.setCellValue("PAY BY");
            cell79.setCellStyle(styleHeader);
            sheet.autoSizeColumn(17);
        HSSFCell cell80 = row5.createCell(18);
            cell80.setCellValue("DATE TRSF");
            cell80.setCellStyle(styleHeader);
            sheet.autoSizeColumn(18);
        HSSFCell cell81 = row5.createCell(19);
            cell81.setCellValue("SALLER");
            cell81.setCellStyle(styleHeader);
            sheet.autoSizeColumn(19);
        
        sheet.addMergedRegion(CellRangeAddress.valueOf("A8:A9"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("B8:B9"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("C8:C9"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("D8:D9"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("E8:E9"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("F8:F9"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("G8:G9"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("Q8:Q9"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("R8:R9"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("S8:S9"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("T8:T9"));
            
        HSSFRow row6 = sheet.createRow(8);
        row6.createCell(0).setCellStyle(styleBorderBottomAndRight);
        row6.createCell(1).setCellStyle(styleBorderBottomAndRight);
        row6.createCell(2).setCellStyle(styleBorderBottomAndRight);
        row6.createCell(3).setCellStyle(styleBorderBottomAndRight);
        row6.createCell(4).setCellStyle(styleBorderBottomAndRight);
        row6.createCell(5).setCellStyle(styleBorderBottomAndRight);
        row6.createCell(6).setCellStyle(styleBorderBottomAndRight);
        HSSFCell cell69 = row6.createCell(7);
            cell69.setCellValue("AD");
            cell69.setCellStyle(styleHeader);
            sheet.autoSizeColumn(7);
        HSSFCell cell70 = row6.createCell(8);
            cell70.setCellValue("CH");
            cell70.setCellStyle(styleHeader);
            sheet.autoSizeColumn(8);
        HSSFCell cell71 = row6.createCell(9);
            cell71.setCellValue("IN");
            cell71.setCellStyle(styleHeader);
            sheet.autoSizeColumn(9);
        HSSFCell cell72 = row6.createCell(10);
            cell72.setCellValue("ADULT");
            cell72.setCellStyle(styleHeader);
            sheet.autoSizeColumn(10);
        HSSFCell cell73 = row6.createCell(11);
            cell73.setCellValue("CHILD");
            cell73.setCellStyle(styleHeader);
            sheet.autoSizeColumn(11);
        HSSFCell cell74 = row6.createCell(12);
            cell74.setCellValue("INFANT");
            cell74.setCellStyle(styleHeader);
            sheet.autoSizeColumn(12);
        HSSFCell cell75 = row6.createCell(13);
            cell75.setCellValue("ADULT");
            cell75.setCellStyle(styleHeader);
            sheet.autoSizeColumn(13);
        HSSFCell cell76 = row6.createCell(14);
            cell76.setCellValue("CHILD");
            cell76.setCellStyle(styleHeader);
            sheet.autoSizeColumn(14);
        HSSFCell cell77 = row6.createCell(15);
            cell77.setCellValue("INFANT");
            cell77.setCellStyle(styleHeader);
            sheet.autoSizeColumn(15);
        row6.createCell(16).setCellStyle(styleBorderBottomAndRight);
        row6.createCell(17).setCellStyle(styleBorderBottomAndRight);
        row6.createCell(18).setCellStyle(styleBorderBottomAndRight);
        row6.createCell(19).setCellStyle(styleBorderBottomAndRight);
        
        
        int count = 9 + listOutboundProduct.size();
        int num = 0;
        int end = 0;
        if(listOutboundProduct != null && listOutboundProduct.size() != 0){
            for (int r = 9 ; r < count; r++) {
             if(num <= (listOutboundProduct.size()-1)){
                if(num != 0){ // Check not row first
                    String temp = listOutboundProduct.get(num-1).getProductname();
                    if(temp.equals(listOutboundProduct.get(num).getProductname())){ // equal type	
//                        System.out.println("Num : " + num + " Last Row : " + (listOutboundProduct.size()-1));
                        if(num  != (listOutboundProduct.size()-1)){ // check not last row
                            HSSFRow row = sheet.createRow(r);
                            createCell(row,listOutboundProduct,num,styleAlignRightBorderAllNumber,styleAlignRightBorderAll,styleDetailTableCenter);
                            if(listOutboundProduct.get(num).getInvno() != null && !"".equals(listOutboundProduct.get(num).getInvno())){
                                sheet.autoSizeColumn(5);
                            }
                            num++; 
                        }else{ // last row				
//                            System.out.println("Num : " + num + " Last Row : " + (listOutboundProduct.size()-1));
                            HSSFRow row = sheet.createRow(r);
                            createCell(row,listOutboundProduct,num,styleAlignRightBorderAllNumber,styleAlignRightBorderAll,styleDetailTableCenter);                                                    
                            if(listOutboundProduct.get(num).getInvno() != null && !"".equals(listOutboundProduct.get(num).getInvno())){
                                sheet.autoSizeColumn(5);
                            }
                            num++;

                            // total
                            HSSFRow rowT = sheet.createRow(r+1);
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
                                rowT.createCell(12).setCellStyle(styleBorderBottom);
                                rowT.createCell(13).setCellStyle(styleBorderBottom);
                                rowT.createCell(14).setCellStyle(styleBorderBottom);
                                rowT.createCell(15).setCellStyle(styleBorderBottom);
                                rowT.createCell(16).setCellStyle(styleBorderBottom);
                                rowT.createCell(17).setCellStyle(styleBorderBottom);
                                rowT.createCell(18).setCellStyle(styleBorderBottom);
                                rowT.createCell(19).setCellStyle(styleAlignRightBorderBottomRight);
                        }
                    }else{ // not equal type
                        if(num  == (listOutboundProduct.size()-1)){ // check  last row				
//                            System.out.println("Num : " + num + " Last Row : " + (listOutboundProduct.size()-1));
                            HSSFRow row = sheet.createRow(r);
                            createCell(row,listOutboundProduct,num,styleAlignRightBorderAllNumber,styleAlignRightBorderAll,styleDetailTableCenter);                                                    
                            if(listOutboundProduct.get(num).getInvno() != null && !"".equals(listOutboundProduct.get(num).getInvno())){
                                sheet.autoSizeColumn(5);
                            }
                            num++;
                                // total
                                HSSFRow rowT = sheet.createRow(r+1);
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
                                    rowT.createCell(12).setCellStyle(styleBorderBottom);
                                    rowT.createCell(13).setCellStyle(styleBorderBottom);
                                    rowT.createCell(14).setCellStyle(styleBorderBottom);
                                    rowT.createCell(15).setCellStyle(styleBorderBottom);
                                    rowT.createCell(16).setCellStyle(styleBorderBottom);
                                    rowT.createCell(17).setCellStyle(styleBorderBottom);
                                    rowT.createCell(18).setCellStyle(styleBorderBottom);
                                    rowT.createCell(19).setCellStyle(styleAlignRightBorderBottomRight);
                            }else{                                          
//                                System.out.println("Num : " + num + " Last Row : " + (listOutboundProduct.size()-1));
                                // total
                                HSSFRow rowT = sheet.createRow(r);
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
                                    rowT.createCell(12).setCellStyle(styleBorderBottom);
                                    rowT.createCell(13).setCellStyle(styleBorderBottom);
                                    rowT.createCell(14).setCellStyle(styleBorderBottom);
                                    rowT.createCell(15).setCellStyle(styleBorderBottom);
                                    rowT.createCell(16).setCellStyle(styleBorderBottom);
                                    rowT.createCell(17).setCellStyle(styleBorderBottom);
                                    rowT.createCell(18).setCellStyle(styleBorderBottom);
                                    rowT.createCell(19).setCellStyle(styleAlignRightBorderBottomRight);
                                // Start New Row (Group)
                                HSSFRow row0 = sheet.createRow(r+1);
                                HSSFCell cell = row0.createCell(0);
                                    cell.setCellValue(listOutboundProduct.get(num).getProductname());
//                                    cell.setCellStyle(styleAlignRightBorderAll);
                                row0.createCell(19).setCellStyle(styleAlignRightBorderAll);
                                if(listOutboundProduct.get(num).getInvno() != null && !"".equals(listOutboundProduct.get(num).getInvno())){
                                    sheet.autoSizeColumn(5);
                                }
                                String add = "A"+(r+2)+":M"+(r+2)+"";
//                                System.out.println("Add : " + add);
                                sheet.addMergedRegion(CellRangeAddress.valueOf(add));
                                HSSFRow row122 = sheet.createRow(r+2);
                                createCell(row122,listOutboundProduct,num,styleAlignRightBorderAllNumber,styleAlignRightBorderAll,styleDetailTableCenter);
                                num++;				 
                                count = count + 2;
                                r = r + 2;
                            }
                        }
                    }else{ // row first
//                        System.out.println("Num : " + num + " Last Row : " + (listOutboundProduct.size()-1));
                        
                        HSSFRow row0 = sheet.createRow(r);
                        HSSFCell cell = row0.createCell(0);
                            cell.setCellValue(listOutboundProduct.get(num).getProductname());
//                            cell.setCellStyle(styleAlignRightBorderAll);
                        row0.createCell(19).setCellStyle(styleAlignRightBorderAll);
                        String add = "A"+(r+1)+":T"+(r+1)+"";
//                        System.out.println("Add : " + add);
                        sheet.addMergedRegion(CellRangeAddress.valueOf(add));
                        
                        HSSFRow row = sheet.createRow(r+1);
                        createCell(row,listOutboundProduct,num,styleAlignRightBorderAllNumber,styleAlignRightBorderAll,styleDetailTableCenter);
                        if(listOutboundProduct.get(num).getInvno() != null && !"".equals(listOutboundProduct.get(num).getInvno())){
                            sheet.autoSizeColumn(5);
                        }
//                        sheet.autoSizeColumn(20);
                        num = num + 1;
                        count = count + 1;
                        r = r + 1;
                    }
                    sheet.autoSizeColumn(6);
                    sheet.autoSizeColumn(9);
                }
            }
        }
//        sheet.setColumnWidth(3, 256*15);
    }
    
    private void createCell(HSSFRow row,List<OutboundProductSummaryExcel> listOutboundProduct,int num,HSSFCellStyle styleNumber,HSSFCellStyle styleDetail,HSSFCellStyle styleDetailCenter){
        
        HSSFCellStyle styleDetailTableNumber = styleNumber;
        HSSFCellStyle styleDetailTable = styleDetail;
        HSSFCellStyle styleDetailTableCenter = styleDetailCenter;
        HSSFCell cell5 = row.createCell(0);
            if(listOutboundProduct.get(num).getSaledate() != null ){
                cell5.setCellValue(listOutboundProduct.get(num).getSaledate());
            }else{
                cell5.setCellValue("");
            }
            cell5.setCellStyle(styleDetailTable);
        HSSFCell cell6 = row.createCell(1);
            if(listOutboundProduct.get(num).getRecordno() != null ){
                cell6.setCellValue(listOutboundProduct.get(num).getRecordno());
            }else{
                cell6.setCellValue("");
            }
            cell6.setCellStyle(styleDetailTable);
        HSSFCell cell7 = row.createCell(2);
            if(listOutboundProduct.get(num).getTravoxno()!= null ){
                cell7.setCellValue(listOutboundProduct.get(num).getTravoxno());
            }else{
                cell7.setCellValue("");
            }
            cell7.setCellStyle(styleDetailTable);
//        HSSFCell cell8 = row.createCell(3);
//            if(listOutboundProduct.get(num).getPasstype()!= null ){
//                cell8.setCellValue(listOutboundProduct.get(num).getPasstype());
//            }else{
//                cell8.setCellValue("");
//            }
//            cell8.setCellStyle(styleDetailTable);
            
        HSSFCell cell9 = row.createCell(3);
            if(listOutboundProduct.get(num).getPassno()!= null ){
                cell9.setCellValue(listOutboundProduct.get(num).getPassno().replaceAll(",", "\n"));
            }else{
                cell9.setCellValue("");
            }
            cell9.setCellStyle(styleDetailTable);
        HSSFCell cell10 = row.createCell(4);
            if(listOutboundProduct.get(num).getDulation()!= null ){
                cell10.setCellValue(listOutboundProduct.get(num).getDulation());
            }else{
                cell10.setCellValue("");
            }
            cell10.setCellStyle(styleDetailTableCenter);

        HSSFCell cell11 = row.createCell(5);
            if(listOutboundProduct.get(num).getInvno()!= null ){
                cell11.setCellValue(listOutboundProduct.get(num).getInvno());
            }else{
                cell11.setCellValue("");
            }
            cell11.setCellStyle(styleDetailTable);
        HSSFCell cell12 = row.createCell(6);
            if(listOutboundProduct.get(num).getInvno()!= null ){
                cell12.setCellValue(listOutboundProduct.get(num).getCustomername());
            }else{
                cell12.setCellValue("");
            }
            cell12.setCellStyle(styleDetailTable);
        HSSFCell cell13 = row.createCell(7);
            if(listOutboundProduct.get(num).getPaxad()!= null ){
                cell13.setCellValue(listOutboundProduct.get(num).getPaxad().doubleValue());
            }else{
                cell13.setCellValue("");
            }
            cell13.setCellStyle(styleDetailTableCenter);
        HSSFCell cell14 = row.createCell(8);
            if(listOutboundProduct.get(num).getPaxch() != null){
                cell14.setCellValue(listOutboundProduct.get(num).getPaxch().doubleValue());
            }else{
                cell14.setCellValue(0.00);
            }

            cell14.setCellStyle(styleDetailTableCenter);

        HSSFCell cell15 = row.createCell(9);
            if(listOutboundProduct.get(num).getPaxin() != null){
                cell15.setCellValue(listOutboundProduct.get(num).getPaxin().doubleValue());
            }else{
                cell15.setCellValue(0.00);
            }

            cell15.setCellStyle(styleDetailTableCenter);
        HSSFCell cell16 = row.createCell(10);
            if(listOutboundProduct.get(num).getTotalnettadult() != null){
                cell16.setCellValue(listOutboundProduct.get(num).getTotalnettadult().doubleValue());
            }else{
                cell16.setCellValue(0.00);
            }
            cell16.setCellStyle(styleDetailTableNumber);
        HSSFCell cell17 = row.createCell(11);
            if(listOutboundProduct.get(num).getTotalnettchild() != null){
                cell17.setCellValue(listOutboundProduct.get(num).getTotalnettchild().doubleValue());
            }else{
                cell17.setCellValue(0.00);
            }
            cell17.setCellStyle(styleDetailTableNumber);
        HSSFCell cell18 = row.createCell(12);
            if(listOutboundProduct.get(num).getTotalnettinfant() != null){
                cell18.setCellValue(listOutboundProduct.get(num).getTotalnettinfant().doubleValue());
            }else{
                cell18.setCellValue(0.00);
            }
            cell18.setCellStyle(styleDetailTableNumber);
        HSSFCell cell19 = row.createCell(13);
            if(listOutboundProduct.get(num).getTotalsaleadult() != null){
                cell19.setCellValue(listOutboundProduct.get(num).getTotalsaleadult().doubleValue());
            }else{
                cell19.setCellValue(0.00);
            }
            cell19.setCellStyle(styleDetailTableNumber);
        HSSFCell cell20 = row.createCell(14);
            if(listOutboundProduct.get(num).getTotalsalechild() != null){
                cell20.setCellValue(listOutboundProduct.get(num).getTotalsalechild().doubleValue());
            }else{
                cell20.setCellValue(0.00);
            }
            cell20.setCellStyle(styleDetailTableNumber);
        HSSFCell cell21 = row.createCell(15);
            if(listOutboundProduct.get(num).getTotalsaleinfant() != null){
                cell21.setCellValue(listOutboundProduct.get(num).getTotalsaleinfant().doubleValue());
            }else{
                cell21.setCellValue(0.00);
            }
            cell21.setCellStyle(styleDetailTableNumber);
        HSSFCell cell22 = row.createCell(16);
            if(listOutboundProduct.get(num).getProfittotal() != null){
                cell22.setCellValue(listOutboundProduct.get(num).getProfittotal().doubleValue());
            }else{
                cell22.setCellValue(0.00);
            }
            cell22.setCellStyle(styleDetailTableNumber);
        HSSFCell cell23 = row.createCell(17);
            if(listOutboundProduct.get(num).getPayby()!= null ){
                cell23.setCellValue(listOutboundProduct.get(num).getPayby());
            }else{
                cell23.setCellValue("");
            }
            cell23.setCellStyle(styleDetailTable);
        HSSFCell cell24 = row.createCell(18);
            if(listOutboundProduct.get(num).getDatetrsf()!= null ){
                cell24.setCellValue(listOutboundProduct.get(num).getDatetrsf());
            }else{
                cell24.setCellValue("");
            }
            cell24.setCellStyle(styleDetailTable);
        HSSFCell cell25 = row.createCell(19);
            if(listOutboundProduct.get(num).getSeller()!= null ){
                cell25.setCellValue(listOutboundProduct.get(num).getSeller());
            }else{
                cell25.setCellValue("");
            }
            cell25.setCellStyle(styleDetailTable);
    }
    
    private void getPaymentSummary(HSSFWorkbook wb, List listPaymentSummary) {
        List<PaymentOutboundAllDetail> paymentSummaryList = listPaymentSummary;
        String sheetName = "PaymentSummary";// name of sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        UtilityExcelFunction excelFunction = new UtilityExcelFunction();
        // Set align Text
        HSSFCellStyle styleAlignRight = wb.createCellStyle();
        styleAlignRight.setAlignment(styleAlignRight.ALIGN_RIGHT);
        HSSFCellStyle styleAlignLeft = wb.createCellStyle();
        styleAlignLeft.setAlignment(styleAlignLeft.ALIGN_LEFT);
        HSSFDataFormat currency = wb.createDataFormat();
        // Set align Text
        HSSFCellStyle styleNumber = wb.createCellStyle();
        styleNumber.setAlignment(styleNumber.ALIGN_RIGHT);
        styleNumber.setDataFormat(currency.getFormat("#,##0.00"));
        
         // set Header Report (Row 1)
        HSSFCellStyle styleHeader1 = wb.createCellStyle();
        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cell1 = row1.createCell(0);
        cell1.setCellValue("Payment Outbound Summary");
        styleHeader1.setFont(excelFunction.getHeaderFont(wb.createFont()));
        cell1.setCellStyle(styleHeader1);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:I1"));
        
        if(paymentSummaryList != null && paymentSummaryList.size() != 0){
            PaymentOutboundAllDetail poad = new PaymentOutboundAllDetail();
            poad = paymentSummaryList.get(0);
            
            HSSFRow row2 = sheet.createRow(1);
            HSSFCell cell21 = row2.createCell(0);
                cell21.setCellValue("Invoice Sup : ");
                cell21.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(0);
            HSSFCell cell22 = row2.createCell(1);
                cell22.setCellValue(poad.getHeaderinvoicesupcode());
                cell22.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(1);
            HSSFCell cell23 = row2.createCell(2);
                cell23.setCellValue("Staff : ");
                cell23.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(2);
            HSSFCell cell24 = row2.createCell(3);
                cell24.setCellValue(poad.getHeaderstaff());
                cell24.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(3);
                
            HSSFRow row3 = sheet.createRow(2);
            HSSFCell cell31 = row3.createCell(0);
                cell31.setCellValue("Pay Date : ");
                cell31.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(0);
            HSSFCell cell32 = row3.createCell(1);
                cell32.setCellValue(poad.getDatefromto());
                cell32.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(1);
                           
            HSSFRow row4 = sheet.createRow(3);
            HSSFCell cell41 = row4.createCell(0);
                cell41.setCellValue("Ref No : ");
                cell41.setCellStyle(styleAlignRight);
                sheet.autoSizeColumn(0);
            HSSFCell cell42 = row4.createCell(1);
                cell42.setCellValue(poad.getHeaderrefno());
                cell42.setCellStyle(styleAlignLeft);
                sheet.autoSizeColumn(1);
        }
        
        // Header Table
        HSSFCellStyle styleHeader = wb.createCellStyle();
            styleHeader.setFont(excelFunction.getHeaderTable(wb.createFont()));
            styleHeader.setAlignment(styleHeader.ALIGN_CENTER);
            styleHeader.setBorderTop(styleHeader.BORDER_THIN);
            styleHeader.setBorderLeft(styleHeader.BORDER_THIN);
            styleHeader.setBorderBottom(styleHeader.BORDER_THIN);
            styleHeader.setBorderRight(styleHeader.BORDER_THIN);
            styleHeader.setVerticalAlignment(styleHeader.VERTICAL_CENTER);
        HSSFCellStyle styleDetailTable = wb.createCellStyle();
            styleDetailTable.setAlignment(styleDetailTable.ALIGN_LEFT);
            styleDetailTable.setBorderLeft(styleDetailTable.BORDER_THIN);
            styleDetailTable.setBorderRight(styleDetailTable.BORDER_THIN);
            styleDetailTable.setVerticalAlignment(styleDetailTable.VERTICAL_CENTER);
        HSSFCellStyle styleDetailTableCenter = wb.createCellStyle();
            styleDetailTableCenter.setAlignment(styleDetailTableCenter.ALIGN_CENTER);
            styleDetailTableCenter.setBorderTop(styleDetailTableCenter.BORDER_THIN);
            styleDetailTableCenter.setBorderBottom(styleDetailTableCenter.BORDER_THIN);
            styleDetailTableCenter.setBorderRight(styleDetailTableCenter.BORDER_THIN);
            styleDetailTableCenter.setBorderLeft(styleDetailTableCenter.BORDER_THIN);
            styleDetailTableCenter.setWrapText(true);
            styleDetailTableCenter.setVerticalAlignment(styleDetailTableCenter.VERTICAL_CENTER);
        HSSFCellStyle styleDetailTableNumber = wb.createCellStyle();
            styleDetailTableNumber.setAlignment(styleDetailTableNumber.ALIGN_RIGHT);
            styleDetailTableNumber.setBorderLeft(styleDetailTableNumber.BORDER_THIN);
            styleDetailTableNumber.setBorderRight(styleDetailTableNumber.BORDER_THIN);
            styleDetailTableNumber.setDataFormat(currency.getFormat("#,##0.00"));
            styleDetailTableNumber.setWrapText(true);
            styleDetailTableNumber.setVerticalAlignment(styleDetailTableNumber.VERTICAL_CENTER);
        HSSFCellStyle styleDetailTableBorderBottom = wb.createCellStyle();
            styleDetailTableBorderBottom.setBorderTop(styleDetailTableBorderBottom.BORDER_THIN);
        HSSFCellStyle styleBorderTop =  wb.createCellStyle();
            styleBorderTop.setBorderBottom(styleBorderTop.BORDER_THIN);
            styleBorderTop.setFont(excelFunction.getHeaderTable(wb.createFont()));
            styleBorderTop.setAlignment(styleBorderTop.ALIGN_CENTER);
        HSSFCellStyle styleBorderRight =  wb.createCellStyle();
            styleBorderRight.setBorderRight(styleBorderRight.BORDER_THIN);
            styleBorderRight.setAlignment(styleBorderRight.ALIGN_CENTER);
        HSSFCellStyle styleBorderBottomAndRight =  wb.createCellStyle();
            styleBorderBottomAndRight.setBorderRight(styleBorderBottomAndRight.BORDER_THIN);
            styleBorderBottomAndRight.setBorderBottom(styleBorderBottomAndRight.BORDER_THIN);
            styleBorderBottomAndRight.setAlignment(styleBorderBottomAndRight.ALIGN_CENTER);
            styleBorderBottomAndRight.setVerticalAlignment(styleBorderBottomAndRight.VERTICAL_CENTER);
            
            HSSFCellStyle styleAlignLeftBorderTopRight = wb.createCellStyle(); // use
                styleAlignLeftBorderTopRight.setAlignment(styleAlignLeftBorderTopRight.ALIGN_LEFT);
                styleAlignLeftBorderTopRight.setBorderTop(styleAlignLeftBorderTopRight.BORDER_THIN);
                styleAlignLeftBorderTopRight.setBorderRight(styleAlignLeftBorderTopRight.BORDER_THIN);
                styleAlignLeftBorderTopRight.setVerticalAlignment(styleAlignLeftBorderTopRight.VERTICAL_CENTER);
        HSSFCellStyle styleAlignLeftBorderTopLeft = wb.createCellStyle(); // use
                styleAlignLeftBorderTopLeft.setAlignment(styleAlignLeftBorderTopLeft.ALIGN_LEFT);
                styleAlignLeftBorderTopLeft.setBorderTop(styleAlignLeftBorderTopLeft.BORDER_THIN);
                styleAlignLeftBorderTopLeft.setBorderLeft(styleAlignLeftBorderTopLeft.BORDER_THIN);
                styleAlignLeftBorderTopLeft.setVerticalAlignment(styleAlignLeftBorderTopLeft.VERTICAL_CENTER);
        HSSFCellStyle styleBorderTopP = wb.createCellStyle(); // use
                styleBorderTopP.setBorderTop(styleBorderTopP.BORDER_THIN);
        HSSFCellStyle styleAlignRightBorderBottomRight = wb.createCellStyle();//use
                styleAlignRightBorderBottomRight.setAlignment(styleAlignRightBorderBottomRight.ALIGN_LEFT);
                styleAlignRightBorderBottomRight.setBorderBottom(styleAlignRightBorderBottomRight.BORDER_THIN);
                styleAlignRightBorderBottomRight.setBorderRight(styleAlignRightBorderBottomRight.BORDER_THIN);
                styleAlignRightBorderBottomRight.setVerticalAlignment(styleAlignRightBorderBottomRight.VERTICAL_CENTER);
        HSSFCellStyle styleAlignRightBorderBottomLeft = wb.createCellStyle();
                styleAlignRightBorderBottomLeft.setAlignment(styleAlignRightBorderBottomLeft.ALIGN_LEFT);
                styleAlignRightBorderBottomLeft.setBorderBottom(styleAlignRightBorderBottomLeft.BORDER_THIN);
                styleAlignRightBorderBottomLeft.setBorderLeft(styleAlignRightBorderBottomLeft.BORDER_THIN);
                styleAlignRightBorderBottomLeft.setVerticalAlignment(styleAlignRightBorderBottomLeft.VERTICAL_CENTER);
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
        HSSFCellStyle styleAlignRightBorderAllNumber = wb.createCellStyle();
                styleAlignRightBorderAllNumber.setAlignment(styleAlignRightBorderAllNumber.ALIGN_RIGHT);
                styleAlignRightBorderAllNumber.setDataFormat(currency.getFormat("#,##0.00"));
                styleAlignRightBorderAllNumber.setBorderTop(styleAlignRightBorderAllNumber.BORDER_THIN);
                styleAlignRightBorderAllNumber.setBorderBottom(styleAlignRightBorderAllNumber.BORDER_THIN);
                styleAlignRightBorderAllNumber.setBorderRight(styleAlignRightBorderAllNumber.BORDER_THIN);
                styleAlignRightBorderAllNumber.setBorderLeft(styleAlignRightBorderAllNumber.BORDER_THIN);
                styleAlignRightBorderAllNumber.setVerticalAlignment(styleAlignRightBorderAllNumber.VERTICAL_CENTER);
                styleAlignRightBorderAllNumber.setWrapText(true);  
        HSSFCellStyle styleAlignRightBorderAll = wb.createCellStyle();
                styleAlignRightBorderAll.setAlignment(styleAlignRightBorderAll.ALIGN_RIGHT);
                styleAlignRightBorderAll.setBorderTop(styleAlignRightBorderAll.BORDER_THIN);
                styleAlignRightBorderAll.setBorderBottom(styleAlignRightBorderAll.BORDER_THIN);
                styleAlignRightBorderAll.setBorderRight(styleAlignRightBorderAll.BORDER_THIN);
                styleAlignRightBorderAll.setBorderLeft(styleAlignRightBorderAll.BORDER_THIN);
                styleAlignRightBorderAll.setVerticalAlignment(styleAlignRightBorderAll.VERTICAL_CENTER);
                styleAlignRightBorderAll.setWrapText(true);
        HSSFCellStyle styleAlignLeftBorderAllNumber = wb.createCellStyle();
                styleAlignLeftBorderAllNumber.setAlignment(styleAlignLeftBorderAllNumber.ALIGN_LEFT);
                styleAlignLeftBorderAllNumber.setDataFormat(currency.getFormat("#,##0.00"));
                styleAlignLeftBorderAllNumber.setBorderTop(styleAlignLeftBorderAllNumber.BORDER_THIN);
                styleAlignLeftBorderAllNumber.setBorderBottom(styleAlignLeftBorderAllNumber.BORDER_THIN);
                styleAlignLeftBorderAllNumber.setBorderRight(styleAlignLeftBorderAllNumber.BORDER_THIN);
                styleAlignLeftBorderAllNumber.setBorderLeft(styleAlignLeftBorderAllNumber.BORDER_THIN);
                styleAlignLeftBorderAllNumber.setVerticalAlignment(styleAlignLeftBorderAllNumber.VERTICAL_CENTER);
                styleAlignLeftBorderAllNumber.setWrapText(true);  
        HSSFCellStyle styleAlignLeftBorderAll = wb.createCellStyle();
                styleAlignLeftBorderAll.setAlignment(styleAlignLeftBorderAll.ALIGN_LEFT);
                styleAlignLeftBorderAll.setBorderTop(styleAlignLeftBorderAll.BORDER_THIN);
                styleAlignLeftBorderAll.setBorderBottom(styleAlignLeftBorderAll.BORDER_THIN);
                styleAlignLeftBorderAll.setBorderRight(styleAlignLeftBorderAll.BORDER_THIN);
                styleAlignLeftBorderAll.setBorderLeft(styleAlignLeftBorderAll.BORDER_THIN);
                styleAlignLeftBorderAll.setVerticalAlignment(styleAlignLeftBorderAll.VERTICAL_CENTER);
                styleAlignLeftBorderAll.setWrapText(true);        
        HSSFCellStyle styleAlignRightBorderAllNumberRate = wb.createCellStyle();
                styleAlignRightBorderAllNumberRate.setAlignment(styleAlignRightBorderAllNumberRate.ALIGN_RIGHT);
                styleAlignRightBorderAllNumberRate.setDataFormat(currency.getFormat("#,##0.0000"));
                styleAlignRightBorderAllNumberRate.setBorderTop(styleAlignRightBorderAllNumberRate.BORDER_THIN);
                styleAlignRightBorderAllNumberRate.setBorderBottom(styleAlignRightBorderAllNumberRate.BORDER_THIN);
                styleAlignRightBorderAllNumberRate.setBorderRight(styleAlignRightBorderAllNumberRate.BORDER_THIN);
                styleAlignRightBorderAllNumberRate.setBorderLeft(styleAlignRightBorderAllNumberRate.BORDER_THIN);
                styleAlignRightBorderAllNumberRate.setVerticalAlignment(styleAlignRightBorderAllNumberRate.VERTICAL_CENTER);
                styleAlignRightBorderAllNumberRate.setWrapText(true);    
                
        HSSFRow row4 = sheet.createRow(6);
        for(int x=0 ; x < 39 ; x ++){
//            if( (x > 8 && x < 12) || ( x>17 && x < 26) || x == 30 || x == 31){
                row4.createCell(x).setCellStyle(styleBorderTop);
//            }
        }
            
        HSSFRow row5 = sheet.createRow(7);
        HSSFCell cell51 = row5.createCell(0);
            cell51.setCellValue("REF NO");
            cell51.setCellStyle(styleHeader);
            sheet.autoSizeColumn(0);
        HSSFCell cell52 = row5.createCell(1);
            cell52.setCellValue("ISSUE DATE");
            cell52.setCellStyle(styleHeader);
            sheet.autoSizeColumn(1);
        HSSFCell cell53 = row5.createCell(2);
            cell53.setCellValue("TOUR CODE");
            cell53.setCellStyle(styleHeader);
            sheet.autoSizeColumn(2);
        HSSFCell cell54 = row5.createCell(3);
            cell54.setCellValue("INV NO");
            cell54.setCellStyle(styleHeader);
            sheet.autoSizeColumn(3);
        HSSFCell cell55 = row5.createCell(4);
            cell55.setCellValue("INV DATE");
            cell55.setCellStyle(styleHeader);
            sheet.autoSizeColumn(4);
        HSSFCell cell56 = row5.createCell(5);
            cell56.setCellValue("DEPARTMENT");
            cell56.setCellStyle(styleHeader);
            sheet.autoSizeColumn(5);
        HSSFCell cell57 = row5.createCell(6);
            cell57.setCellValue("STAFF");
            cell57.setCellStyle(styleHeader);
            sheet.autoSizeColumn(6);
        HSSFCell cell58 = row5.createCell(7);
            cell58.setCellValue("TERM PAY");
            cell58.setCellStyle(styleHeader);
            sheet.autoSizeColumn(7);
        HSSFCell cell59 = row5.createCell(8);
            cell59.setCellValue("INV TO");
            cell59.setCellStyle(styleHeader);
            sheet.autoSizeColumn(8);    
        HSSFCell cell60 = row5.createCell(9);
            cell60.setCellValue("PAX");
            cell60.setCellStyle(styleHeader);
            sheet.autoSizeColumn(9);
        HSSFCell cell61 = row5.createCell(12);
            cell61.setCellValue("COUNTRY");
            cell61.setCellStyle(styleHeader);
            sheet.autoSizeColumn(12); 
        HSSFCell cell62= row5.createCell(13);
            cell62.setCellValue("CITY");
            cell62.setCellStyle(styleHeader);
            sheet.autoSizeColumn(13); 
        HSSFCell cell63 = row5.createCell(14);
            cell63.setCellValue("P TYPE");
            cell63.setCellStyle(styleHeader);
            sheet.autoSizeColumn(14); 
        HSSFCell cell64 = row5.createCell(15);
            cell64.setCellValue("DEPARTTURE");
            cell64.setCellStyle(styleHeader);
            sheet.autoSizeColumn(15);
        HSSFCell cell65 = row5.createCell(16);
            cell65.setCellValue("PRICE");
            cell65.setCellStyle(styleHeader);
            sheet.autoSizeColumn(16); 
        HSSFCell cell66 = row5.createCell(17);
            cell66.setCellValue("ACC");
            cell66.setCellStyle(styleHeader);
            sheet.autoSizeColumn(17); 
        HSSFCell cell67 = row5.createCell(18);
            cell67.setCellValue("PAID");
            cell67.setCellStyle(styleHeader);
            sheet.autoSizeColumn(18); 
        HSSFCell cell68 = row5.createCell(26);
            cell68.setCellValue("AMOUNT LOCAL (P)");
            cell68.setCellStyle(styleHeader);
            sheet.autoSizeColumn(26); 
        HSSFCell cell69 = row5.createCell(27);
            cell69.setCellValue("AMOUNT LOCAL (R)");
            cell69.setCellStyle(styleHeader);
            sheet.autoSizeColumn(27); 
        HSSFCell cell70 = row5.createCell(28);
            cell70.setCellValue("VAT");
            cell70.setCellStyle(styleHeader);
            sheet.autoSizeColumn(28); 
        HSSFCell cell71 = row5.createCell(29);
            cell71.setCellValue("WHT");
            cell71.setCellStyle(styleHeader);
            sheet.autoSizeColumn(29); 
        HSSFCell cell72 = row5.createCell(30);
            cell72.setCellValue("GROSS PROFIT");
            cell72.setCellStyle(styleHeader);
            sheet.autoSizeColumn(30);
        HSSFCell cell73 = row5.createCell(32);
            cell73.setCellValue("PAYCOM");
            cell73.setCellStyle(styleHeader);
            sheet.autoSizeColumn(32);
        HSSFCell cell74 = row5.createCell(36);
            cell74.setCellValue("PROFIT BALANCE");
            cell74.setCellStyle(styleHeader);
            sheet.autoSizeColumn(36);
        HSSFCell cell75 = row5.createCell(37);
            cell75.setCellValue("RECEIPT NO");
            cell75.setCellStyle(styleHeader);
            sheet.autoSizeColumn(37);
        HSSFCell cell76 = row5.createCell(38);
            cell76.setCellValue("RECEIPT DATE");
            cell76.setCellStyle(styleHeader);
            sheet.autoSizeColumn(38);
             
        sheet.addMergedRegion(CellRangeAddress.valueOf("J8:L8"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("S8:Z8"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("AE8:AF8"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("AG8:AJ8"));
        row5.createCell(11).setCellStyle(styleBorderRight); 
        row5.createCell(25).setCellStyle(styleBorderRight); 
        row5.createCell(31).setCellStyle(styleBorderRight); 
        
        String merge[] = {"A","B","C","D","E","F","G","H","I","M","N","O","P","Q","R","AA","AB","AC","AD","AK","AL","AM"};   
        for(int x = 0 ; x < merge.length ; x++){
            sheet.addMergedRegion(CellRangeAddress.valueOf(merge[x]+"8:"+merge[x]+"9"));
        }
            
        HSSFRow row6 = sheet.createRow(8);
        for(int x = 0 ; x < 39 ; x ++){
            row6.createCell(x).setCellStyle(styleBorderBottomAndRight);
        }
        
        HSSFCell cell073 = row6.createCell(9);
            cell073.setCellValue("AD");
            cell073.setCellStyle(styleHeader);
            sheet.autoSizeColumn(9);
        HSSFCell cell074 = row6.createCell(10);
            cell074.setCellValue("CH");
            cell074.setCellStyle(styleHeader);
            sheet.autoSizeColumn(10);
        HSSFCell cell075 = row6.createCell(11);
            cell075.setCellValue("IN");
            cell075.setCellStyle(styleHeader);
            sheet.autoSizeColumn(11);
            
        HSSFCell cell076 = row6.createCell(18);
            cell076.setCellValue("PV NO");
            cell076.setCellStyle(styleHeader);
            sheet.autoSizeColumn(18);
        HSSFCell cell77 = row6.createCell(19);
            cell77.setCellValue("DATE");
            cell77.setCellStyle(styleHeader);
            sheet.autoSizeColumn(19);
        HSSFCell cell78 = row6.createCell(20);
            cell78.setCellValue("INV SUP");
            cell78.setCellStyle(styleHeader);
            sheet.autoSizeColumn(20);
        HSSFCell cell79 = row6.createCell(21);
            cell79.setCellValue("INV NO");
            cell79.setCellStyle(styleHeader);
            sheet.autoSizeColumn(21);
        HSSFCell cell80 = row6.createCell(22);
            cell80.setCellValue("AMOUNT");
            cell80.setCellStyle(styleHeader);
            sheet.autoSizeColumn(22);
        HSSFCell cell81 = row6.createCell(23);
            cell81.setCellValue("CUR");
            cell81.setCellStyle(styleHeader);
            sheet.autoSizeColumn(23);    
        HSSFCell cell82 = row6.createCell(24);
            cell82.setCellValue("PAY RATE");
            cell82.setCellStyle(styleHeader);
            sheet.autoSizeColumn(24);        
        HSSFCell cell83 = row6.createCell(25);
            cell83.setCellValue("REAL RATE");
            cell83.setCellStyle(styleHeader);
            sheet.autoSizeColumn(25);
        HSSFCell cell84 = row6.createCell(30);
            cell84.setCellValue("PAY");
            cell84.setCellStyle(styleHeader);
            sheet.autoSizeColumn(30);
        HSSFCell cell85 = row6.createCell(31);
            cell85.setCellValue("REAL");
            cell85.setCellStyle(styleHeader);
            sheet.autoSizeColumn(31);
        HSSFCell cell86 = row6.createCell(32);
            cell86.setCellValue("DATE");
            cell86.setCellStyle(styleHeader);
            sheet.autoSizeColumn(32);
        HSSFCell cell87 = row6.createCell(33);
            cell87.setCellValue("STAFF");
            cell87.setCellStyle(styleHeader);
            sheet.autoSizeColumn(33);
        HSSFCell cell88 = row6.createCell(34);
            cell88.setCellValue("PV NO");
            cell88.setCellStyle(styleHeader);
            sheet.autoSizeColumn(34);
        HSSFCell cell89 = row6.createCell(35);
            cell89.setCellValue("COM");
            cell89.setCellStyle(styleHeader);
            sheet.autoSizeColumn(35);    

        int count = 9;
        for(int i=0;i<listPaymentSummary.size();i++){
            PaymentOutboundAllDetail data = (PaymentOutboundAllDetail)listPaymentSummary.get(i);
            HSSFRow row = sheet.createRow(count + i);
            
            HSSFCell celldata00 = row.createCell(0);
                celldata00.setCellValue(String.valueOf(data.getRefno()));
                celldata00.setCellStyle(styleAlignLeftBorderAll);
            HSSFCell celldata01 = row.createCell(1);
                celldata01.setCellValue(String.valueOf(data.getIssuedate()));
                celldata01.setCellStyle(styleAlignLeftBorderAll);    
            HSSFCell celldata02 = row.createCell(2);
                celldata02.setCellValue(String.valueOf(data.getTourcode()));
                celldata02.setCellStyle(styleAlignLeftBorderAll);                    
            HSSFCell celldata03 = row.createCell(3);
                celldata03.setCellValue(String.valueOf(data.getInvno()));
                celldata03.setCellStyle(styleAlignLeftBorderAll);                    
            HSSFCell celldata04 = row.createCell(4);
                celldata04.setCellValue(String.valueOf(data.getInvdate()));
                celldata04.setCellStyle(styleAlignLeftBorderAll);    
            HSSFCell celldata05 = row.createCell(5);
                celldata05.setCellValue(String.valueOf(data.getDepartment()));
                celldata05.setCellStyle(styleAlignLeftBorderAll);    
            HSSFCell celldata06 = row.createCell(6);
                celldata06.setCellValue(String.valueOf(data.getStaff()));
                celldata06.setCellStyle(styleAlignLeftBorderAll);    
            HSSFCell celldata07 = row.createCell(7);
                celldata07.setCellValue(String.valueOf(data.getTerm()));
                celldata07.setCellStyle(styleAlignLeftBorderAll);    
            HSSFCell celldata08 = row.createCell(8);
                celldata08.setCellValue(String.valueOf(data.getInvto()));
                celldata08.setCellStyle(styleAlignLeftBorderAll);    
            HSSFCell celldata09 = row.createCell(9);
                celldata09.setCellValue(String.valueOf(data.getAdult()));
                celldata09.setCellStyle(styleDetailTableCenter);    
            HSSFCell celldata10 = row.createCell(10);
                celldata10.setCellValue(String.valueOf(data.getChild()));
                celldata10.setCellStyle(styleDetailTableCenter);    
            HSSFCell celldata11 = row.createCell(11);
                celldata11.setCellValue(String.valueOf(data.getInfant()));
                celldata11.setCellStyle(styleDetailTableCenter);
            HSSFCell celldata12 = row.createCell(12);
                celldata12.setCellValue(String.valueOf(data.getCountry()));
                celldata12.setCellStyle(styleAlignLeftBorderAll);    
            HSSFCell celldata13 = row.createCell(13);
                celldata13.setCellValue(String.valueOf(data.getCity()));
                celldata13.setCellStyle(styleAlignLeftBorderAll);                    
            HSSFCell celldata14 = row.createCell(14);
                celldata14.setCellValue(String.valueOf(data.getProducttype()));
                celldata14.setCellStyle(styleAlignLeftBorderAll);                    
            HSSFCell celldata15 = row.createCell(15);
                celldata15.setCellValue(String.valueOf(data.getDepartdate()));
                celldata15.setCellStyle(styleAlignLeftBorderAll);    
            HSSFCell celldata16 = row.createCell(16);
                celldata16.setCellValue("".equalsIgnoreCase(String.valueOf(data.getPrice())) ? 0 : (new BigDecimal(data.getPrice())).doubleValue());
                celldata16.setCellStyle(styleAlignRightBorderAllNumber);    
            HSSFCell celldata17 = row.createCell(17);
                celldata17.setCellValue(String.valueOf(data.getAcc()));
                celldata17.setCellStyle(styleDetailTableCenter);    
            HSSFCell celldata18 = row.createCell(18);
                celldata18.setCellValue(String.valueOf(data.getPvno()));
                celldata18.setCellStyle(styleAlignLeftBorderAll);    
            HSSFCell celldata19 = row.createCell(19);
                celldata19.setCellValue(String.valueOf(data.getPaydate()));
                celldata19.setCellStyle(styleDetailTableCenter);    
            HSSFCell celldata20 = row.createCell(20);
                celldata20.setCellValue(String.valueOf(data.getInvsup()));
                celldata20.setCellStyle(styleAlignLeftBorderAll);    
            HSSFCell celldata21 = row.createCell(21);
                celldata21.setCellValue(String.valueOf(data.getPayinvno()));
                celldata21.setCellStyle(styleAlignLeftBorderAll);
                
            HSSFCell celldata22 = row.createCell(22);
                celldata22.setCellValue("".equalsIgnoreCase(String.valueOf(data.getAmount())) ? 0 : (new BigDecimal(data.getAmount())).doubleValue());
                celldata22.setCellStyle(styleAlignRightBorderAllNumber);
            HSSFCell celldata23 = row.createCell(23);
                celldata23.setCellValue(String.valueOf(data.getCurprice()));
                celldata23.setCellStyle(styleDetailTableCenter);    
            HSSFCell celldata24 = row.createCell(24);
                celldata24.setCellValue("".equalsIgnoreCase(String.valueOf(data.getPayrate())) ? 0 : (new BigDecimal(data.getPayrate())).doubleValue());
                celldata24.setCellStyle(styleAlignRightBorderAllNumberRate);                    
            HSSFCell celldata25 = row.createCell(25);
                celldata25.setCellValue("".equalsIgnoreCase(String.valueOf(data.getRealrate())) ? 0 : (new BigDecimal(data.getRealrate())).doubleValue());
                celldata25.setCellStyle(styleAlignRightBorderAllNumberRate);                    
            HSSFCell celldata26 = row.createCell(26);
                celldata26.setCellValue("".equalsIgnoreCase(String.valueOf(data.getAmountlocalp())) ? 0 : (new BigDecimal(data.getAmountlocalp())).doubleValue());
                celldata26.setCellStyle(styleAlignRightBorderAllNumber);    
            HSSFCell celldata27 = row.createCell(27);
                celldata27.setCellValue("".equalsIgnoreCase(String.valueOf(data.getAmountlocalr())) ? 0 : (new BigDecimal(data.getAmountlocalr())).doubleValue());
                celldata27.setCellStyle(styleAlignRightBorderAllNumber);    
            HSSFCell celldata28 = row.createCell(28);
                celldata28.setCellValue("".equalsIgnoreCase(String.valueOf(data.getVat())) ? 0 : (new BigDecimal(data.getVat())).doubleValue());
                celldata28.setCellStyle(styleAlignRightBorderAllNumber);    
            HSSFCell celldata29 = row.createCell(29);
                celldata29.setCellValue("".equalsIgnoreCase(String.valueOf(data.getWht())) ? 0 : (new BigDecimal(data.getWht())).doubleValue());
                celldata29.setCellStyle(styleAlignRightBorderAllNumber); 
                
            HSSFCell celldata30 = row.createCell(30);
                celldata30.setCellValue("".equalsIgnoreCase(String.valueOf(data.getGrosspay())) ? 0 : (new BigDecimal(data.getGrosspay())).doubleValue());
                celldata30.setCellStyle(styleAlignRightBorderAllNumber);    
            HSSFCell celldata31 = row.createCell(31);
                celldata31.setCellValue("".equalsIgnoreCase(String.valueOf(data.getGrossreal())) ? 0 : (new BigDecimal(data.getGrossreal())).doubleValue());
                celldata31.setCellStyle(styleAlignRightBorderAllNumber);    
            HSSFCell celldata32 = row.createCell(32);
                celldata32.setCellValue(String.valueOf(data.getPaycomdate()));
                celldata32.setCellStyle(styleAlignLeftBorderAll);
            HSSFCell celldata33 = row.createCell(33);
                celldata33.setCellValue(String.valueOf(data.getPaycomstaff()));
                celldata33.setCellStyle(styleAlignLeftBorderAll);
            HSSFCell celldata34 = row.createCell(34);
                celldata34.setCellValue(String.valueOf(data.getPaycompvno()));
                celldata34.setCellStyle(styleAlignLeftBorderAll);                
            HSSFCell celldata35 = row.createCell(35);
                celldata35.setCellValue("".equalsIgnoreCase(String.valueOf(data.getPaycommission())) ? 0 : (new BigDecimal(data.getPaycommission())).doubleValue());
                celldata35.setCellStyle(styleAlignRightBorderAllNumber);                
            HSSFCell celldata36 = row.createCell(36);
                celldata36.setCellValue("".equalsIgnoreCase(String.valueOf(data.getBalanceprofit())) ? 0 : (new BigDecimal(data.getBalanceprofit())).doubleValue());
                celldata36.setCellStyle(styleAlignRightBorderAllNumber);                
            HSSFCell celldata37 = row.createCell(37);
                celldata37.setCellValue(String.valueOf(data.getReceiptno()));
                celldata37.setCellStyle(styleAlignLeftBorderAll);                
            HSSFCell celldata38 = row.createCell(38);
                celldata38.setCellValue(String.valueOf(data.getReceiptdate()));
                celldata38.setCellStyle(styleAlignLeftBorderAll);                 
        }
        sheet.setColumnWidth(3, 256*15);
        sheet.setColumnWidth(4, 256*15);
        sheet.setColumnWidth(6, 256*30);
        sheet.setColumnWidth(7, 256*15);
        sheet.setColumnWidth(12, 256*15);
        sheet.setColumnWidth(13, 256*15);
        sheet.setColumnWidth(14, 256*15);
        sheet.setColumnWidth(16, 256*15);
        sheet.setColumnWidth(19, 256*15);
        sheet.setColumnWidth(20, 256*30);
        sheet.setColumnWidth(21, 256*15);
        sheet.setColumnWidth(28, 256*10);
        sheet.setColumnWidth(29, 256*10);
        sheet.setColumnWidth(30, 256*15);
        sheet.setColumnWidth(31, 256*15);
        sheet.setColumnWidth(32, 256*15);
        sheet.setColumnWidth(35, 256*15);
    }
}
