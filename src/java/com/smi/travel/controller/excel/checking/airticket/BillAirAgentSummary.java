/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller.excel.checking.airticket;

import com.smi.travel.controller.excel.master.UtilityExcelFunction;
import com.smi.travel.datalayer.entity.MDefaultData;
import com.smi.travel.datalayer.report.model.BillAirAgent;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.BillAirAgentRefund;
import com.smi.travel.datalayer.view.entity.ListBillAirAgent;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
public class BillAirAgentSummary extends AbstractExcelView {
    private static final String BillAirAgent = "BillAirAgent";
    private static final String BillAirAgentSummary = "BillAirAgentSummary";
    private UtilityService util;
    
    @Override
    protected void buildExcelDocument(Map model, HSSFWorkbook workbook,HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = (String) model.get("name");
        System.out.println("name : " + name);
        response.setHeader("Content-disposition", "attachment; filename=" + name + ".xls");
        
        if(name.equalsIgnoreCase(BillAirAgent)){
            System.out.println("gen report BillAirAgent");
            getBillAirAgentReportSummary(workbook, (List) model.get(name));
        }else if(name.equalsIgnoreCase(BillAirAgentSummary)){
            System.out.println("gen report BillAirAgentSummary");
            getBillAirAgentReportSummary(workbook, (List) model.get(name));
        }
        
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
        UtilityExcelFunction excelFunction = new UtilityExcelFunction();
        HSSFRow row111 = sheet1.createRow(0);
        HSSFCell cell1111 = row111.createCell(0);
        cell1111.setCellValue("detail");
        
        
        // set Header Report (Row 1)
        HSSFCellStyle styleC1 = wb.createCellStyle();
        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cell1 = row1.createCell(0);
        cell1.setCellValue("Bill Agent Air Summary");
        styleC1.setFont(excelFunction.getHeaderFont(wb.createFont()));
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
                styleAlignRightBorderAllHeaderTable.setFont(excelFunction.getHeaderTable(wb.createFont()));
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
        BigDecimal sumTotalCompaySub =  new BigDecimal(0);
        BigDecimal sumPayRefundAmount =  new BigDecimal(0);
        BigDecimal sumVatComPay =  new BigDecimal(0);
        BigDecimal SumVatReceive =  new BigDecimal(0);
        BigDecimal vatComPay =  new BigDecimal(0);
        BigDecimal vatPay =  new BigDecimal(0);
        BigDecimal vatReceive =  new BigDecimal(0);
        BigDecimal totalCom =  new BigDecimal(0);
        BigDecimal balancePayment =  new BigDecimal(0);
        BigDecimal checkResult =  new BigDecimal(0);
        BigDecimal midValue =  new BigDecimal(0);
        BigDecimal withHoldingTax =  new BigDecimal(0);
        String vatMDE = "7";
        String whtMDE = "5";
        
        for (int i = 0; i < listAgent.size(); i++) {
            sumSalePrice = sumSalePrice.add(new BigDecimal(listAgent.get(i).getSaleprice()));
            sumAmountAir = sumAmountAir.add(new BigDecimal(listAgent.get(i).getAmountair()));
            sumComPay = sumComPay.add(new BigDecimal(listAgent.get(i).getCompay()));
            sumVatComPay = sumVatComPay.add(new BigDecimal(listAgent.get(i).getCompayvat()));
            sumTotalComRefundReceive = sumTotalComRefundReceive.add(new BigDecimal(listAgent.get(i).getAgentcomrefund()));
//            vatMDE = listAgent.get(i).getVattemp();
//            whtMDE = listAgent.get(i).getWhttemp();
//            System.out.println("Vat : " + vatMDE + "Wht  :" + whtMDE);
            
            System.out.println("Sale Price : " + listAgent.get(i).getSaleprice() + "  Sum Sale Price : " + sumSalePrice);
            System.out.println("Amount Air : " + listAgent.get(i).getAmountair() + "  Sum Amount Air : " + sumAmountAir);
            System.out.println("Com Pay : " + listAgent.get(i).getCompay() + "  Sum Com Pay : " + sumComPay);
            System.out.println("Com Reefund Receive : " + listAgent.get(i).getAgentcomrefund() + "  Sum Reefund Receive : " + sumTotalComRefundReceive);
            System.out.println("Pay Refund Amount : " + listAgent.get(i).getPaycusrefund() + "  Sum Refund Amount : " + sumPayRefundAmount);
        }
        for (int i = 0; i < listAgentRefund.size(); i++) {
            sumComReceive = sumComReceive.add(new BigDecimal(listAgentRefund.get(i).getComm_rec()));
            sumPayRefundAmount = sumPayRefundAmount.add(new BigDecimal(listAgentRefund.get(i).getAmountpay()));
            SumVatReceive = SumVatReceive.add(new BigDecimal(listAgentRefund.get(i).getVat()));
//            System.out.println("Com Receive : " + listAgent.get(i).getAgentcom() + "  Sum Com Receive : " + sumComReceive);
        }

        DecimalFormat df = new DecimalFormat("#,###.00");
        sumTotalPayment = sumSalePrice.add(sumComReceive);
        sumTotalCompay = sumComPay.subtract(sumComReceive);
        sumTotalCompaySub = sumComPay.multiply((BigDecimal.ZERO).subtract(BigDecimal.ONE));
        
        BigDecimal vatPa =  new BigDecimal(vatMDE );
        vatComPay = sumTotalCompay.multiply(vatPa);
        vatComPay = vatComPay.divide(new BigDecimal(100),MathContext.DECIMAL128);
        vatPay =  (vatComPay.add(SumVatReceive)).multiply((BigDecimal.ZERO).subtract(BigDecimal.ONE));
        
//        vatReceive = sumComReceive.multiply(new BigDecimal(0.07));
        totalCom = sumTotalCompaySub.add(sumComReceive);
        sumPayRefundAmount = sumPayRefundAmount.multiply((BigDecimal.ZERO).subtract(BigDecimal.ONE));
        
        balancePayment = sumTotalPayment.add(vatPay);
        balancePayment = balancePayment.add(SumVatReceive);
        balancePayment = balancePayment.subtract(sumPayRefundAmount.multiply((BigDecimal.ZERO).subtract(BigDecimal.ONE)));
        
        checkResult = sumTotalCompay.add(vatComPay);
        
        midValue =  checkResult.add(balancePayment);
        midValue = midValue.add(sumPayRefundAmount.multiply((BigDecimal.ZERO).subtract(BigDecimal.ONE)));
        
        withHoldingTax = sumTotalCompay.add(vatComPay);
        withHoldingTax = withHoldingTax.multiply(new BigDecimal(100));
        System.out.println("Vat :::: " + vatMDE + "Wht :::: " + whtMDE);
        BigDecimal vatTemp =  new BigDecimal(vatMDE );
        BigDecimal whtTemp =  new BigDecimal(whtMDE );
        vatTemp = vatTemp.add(new BigDecimal(100));
        whtTemp = whtTemp.divide(new BigDecimal(100),MathContext.DECIMAL128);
        withHoldingTax = withHoldingTax.divide(vatTemp,MathContext.DECIMAL128);
        withHoldingTax = withHoldingTax.multiply(whtTemp);
        
        System.out.println(">>>>>>>>>>> Total Sale Price : " + df.format(sumSalePrice));
        System.out.println(">>>>>>>>>>> Total Com Refund Receive : " + df.format(sumTotalComRefundReceive));
        System.out.println(">>>>>>>>>>> Total Payment : " + df.format(sumTotalPayment));
        System.out.println(">>>>>>>>>>> Com Pay (Less) : " + df.format(sumTotalCompaySub));
        System.out.println(">>>>>>>>>>> Com Receive (Less) : " + df.format(sumComReceive));
        System.out.println(">>>>>>>>>>> Total Com : " + df.format(totalCom));
        System.out.println(">>>>>>>>>>> Vat pay (Less) : " + df.format(vatPay));
        System.out.println(">>>>>>>>>>> Vat receive (Less) : " + df.format(SumVatReceive));
        System.out.println(">>>>>>>>>>> Pay Refund Amount (Less): " + df.format(sumPayRefundAmount));
        System.out.println(">>>>>>>>>>> Balance payment : " + df.format(balancePayment));
        System.out.println(">>>>>>>>>>> Amount Air Sale : " + df.format(sumAmountAir));
        System.out.println(">>>>>>>>>>> Com Pay : " + df.format(sumComPay));
        System.out.println(">>>>>>>>>>> Com Receive : " + df.format(sumComReceive));
        System.out.println(">>>>>>>>>>> Total Com Pay : " + df.format(sumTotalCompay));
        System.out.println(">>>>>>>>>>> Vat Com Pay : " + df.format(vatComPay));
        System.out.println(">>>>>>>>>>> With holding : " + df.format(withHoldingTax));
        System.out.println(">>>>>>>>>>> Mid Value : " + df.format(midValue));
        System.out.println(">>>>>>>>>>> Check Result : " + df.format(checkResult));
        
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
                        cell102.setCellValue(df.format(sumComReceive));
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
                        cell1511.setCellValue(df.format(sumTotalCompaySub));
                        cell1511.setCellStyle(styleAlignRight);
                        sheet.addMergedRegion(CellRangeAddress.valueOf("B15:D15"));
                        sheet.autoSizeColumn(1);
                HSSFCell cell152 = row15.createCell(4);
                        cell152.setCellValue("Vat Pay : ");
                        cell152.setCellStyle(styleAlignRight);
                        sheet.autoSizeColumn(4);
                HSSFCell cell1521 = row15.createCell(5);
                        cell1521.setCellValue(df.format(vatPay));
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
                        cell1621.setCellValue(df.format(SumVatReceive));
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
        styleC11.setFont(excelFunction.getHeaderFont(wb.createFont()));
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
            cell72.setCellValue("    Vat    ");
            cell72.setCellStyle(styleAlignRightBorderAllHeaderTable);
            sheet1.autoSizeColumn(11);
        HSSFCell cell73 = row6.createCell(12);
            cell73.setCellValue("    Receive    ");
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
                styleHeader03.setFont(excelFunction.getHeaderFont(wb.createFont()));
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
                cell3110.setCellValue("Amount_pay");
                cell3110.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(8);

                HSSFCell cell3111 = row39.createCell(9);
                cell3111.setCellValue("Comm Rcc");
                cell3111.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(9);

                HSSFCell cell3116 = row39.createCell(10);
                cell3116.setCellValue("  Vat    ");
                cell3116.setCellStyle(styleAlignRightBorderAllHeaderTable);
                sheet2.autoSizeColumn(10);             

            int count2 = 4 + listAgentRefund.size();
            int startrefund  = listAgentRefund.size();
            int endrefund = 5 + listAgentRefund.size();
            System.out.println("Start Refund : " + startrefund + " End Refund : " + endrefund +" Size : " + listAgentRefund.size() );

        if(listAgentRefund != null && listAgentRefund.size() != 0){
            for (int r = 5; r <= count2; r++) {
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
            String sumAmountReceive = "SUM(G" + startrefund+":G"+endrefund+")";
            String sumRefundChange = "SUM(H" + startrefund+":H"+endrefund+")";
            String sumAmount_pay = "SUM(I" + startrefund+":I"+endrefund+")";
            String sumCommRcc = "SUM(J" + startrefund+":J"+endrefund+")";
            String sumVat = "SUM(K" + startrefund+":K"+endrefund+")";

            HSSFRow rowTotalRefund = sheet2.createRow(count2+1);
            rowTotalRefund.createCell(0).setCellStyle(styleAlignRightBorderAll);
            rowTotalRefund.createCell(1).setCellStyle(styleAlignRightBorderAll);
            rowTotalRefund.createCell(2).setCellStyle(styleAlignRightBorderAll);
            rowTotalRefund.createCell(3).setCellStyle(styleAlignRightBorderAll);
            rowTotalRefund.createCell(4).setCellStyle(styleAlignRightBorderAll);
            HSSFCell cell4 = rowTotalRefund.createCell(5);
                cell4.setCellValue("TOTAL");
                cell4.setCellStyle(styleAlignRightBorderAllHeaderTable);  
                sheet2.autoSizeColumn(5);
            HSSFCell cell5 = rowTotalRefund.createCell(6);
                cell5.setCellFormula(sumAmountReceive);
                cell5.setCellStyle(styleAlignRightBorderAllNumber);  
                sheet2.autoSizeColumn(6);
            HSSFCell cell7 = rowTotalRefund.createCell(7);
                cell7.setCellFormula(sumRefundChange);
                cell7.setCellStyle(styleAlignRightBorderAllNumber);
                sheet2.autoSizeColumn(7);
            HSSFCell cell9 = rowTotalRefund.createCell(8);
                cell9.setCellFormula(sumAmount_pay);
                cell9.setCellStyle(styleAlignRightBorderAllNumber);
                sheet2.autoSizeColumn(8);
            HSSFCell cell11 = rowTotalRefund.createCell(9);
                cell11.setCellFormula(sumCommRcc);
                cell11.setCellStyle(styleAlignRightBorderAllNumber);
                sheet2.autoSizeColumn(9);
            HSSFCell cell13 = rowTotalRefund.createCell(10);
                cell13.setCellFormula(sumVat);
                cell13.setCellStyle(styleAlignRightBorderAllNumber);
                sheet2.autoSizeColumn(10);
            
      }else{
        HSSFRow row55 = sheet2.createRow(5);

        HSSFCell cell55 = row55.createCell(0);
        cell55.setCellValue("No Data");
        sheet2.autoSizeColumn(0);
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
        UtilityExcelFunction excelFunction = new UtilityExcelFunction();
        
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
            sheet.autoSizeColumn(11);
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
            sheet.autoSizeColumn(12);
    }
    
    private void createCell(HSSFRow row,List<BillAirAgent> listAgent,int num,HSSFCellStyle styleNumber,HSSFCellStyle styleDetail){
        UtilityExcelFunction excelFunction = new UtilityExcelFunction();
        
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

    public UtilityService getUtil() {
        return util;
    }

    public void setUtil(UtilityService util) {
        this.util = util;
    }
    
    
}
