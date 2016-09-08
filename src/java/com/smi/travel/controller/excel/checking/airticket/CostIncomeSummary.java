/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller.excel.checking.airticket;

import com.smi.travel.controller.excel.master.UtilityExcelFunction;
import com.smi.travel.datalayer.view.entity.ListSummaryTicketAdjustCostAndIncome;
import com.smi.travel.datalayer.view.entity.ListTicketCommissionReceive;
import com.smi.travel.datalayer.view.entity.SummaryTicketAdjustCostAndIncome;
import com.smi.travel.datalayer.view.entity.SummaryTicketCostAndIncomeView;
import com.smi.travel.datalayer.view.entity.TicketCommissionReceive;
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
public class CostIncomeSummary extends AbstractExcelView {

    private static final String SummaryTicketAdjustCostAndIncome = "SummaryTicketAdjustCostAndIncome";
    private static final String SummaryTicketCommissionReceive = "SummaryTicketCommissionReceive";
    private static final String SummaryTicketCostAndIncome = "SummaryTicketCostAndIncome";

    @Override
    protected void buildExcelDocument(Map model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = (String) model.get("name");
//        System.out.println("name : " + name);
        response.setHeader("Content-disposition", "attachment; filename=" + name + ".xls");
        if (name.equalsIgnoreCase(SummaryTicketCostAndIncome)) {
            System.out.println("gen report SummaryTicketCostAndIncome");
            getSummaryTicketCostAndIncome(workbook, (List) model.get(name));
        } else if (name.equalsIgnoreCase(SummaryTicketAdjustCostAndIncome)) {
            System.out.println("gen report SummaryTicketAdjustCostAndIncome");
            getSummaryTicketAdjustCostAndIncome(workbook, (List) model.get(name));
        } else if (name.equalsIgnoreCase(SummaryTicketCommissionReceive)) {
            System.out.println("gen report SummaryTicketCommissionReceive");
            getTicketCommissionReceive(workbook, (List) model.get(name));
        }

    }

    private void getSummaryTicketCostAndIncome(HSSFWorkbook wb, List sumCostIncome) {
        String sheetIncome = "CostIncome";// name of sheet
        String sheetIncomeSum = "CostIncomeSummary";

        UtilityExcelFunction excelFunction = new UtilityExcelFunction();

        HSSFSheet sheetInc = wb.createSheet(sheetIncome);
        HSSFSheet sheetIncSum = wb.createSheet(sheetIncomeSum);

        SummaryTicketCostAndIncomeView dataheader = new SummaryTicketCostAndIncomeView();

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

        HSSFCellStyle styleC31 = wb.createCellStyle();
        styleC31.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC31.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC31.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleC31.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC31.setDataFormat(currency.getFormat("#,##0.00"));

        if (!sumCostIncome.isEmpty()) {
            dataheader = (SummaryTicketCostAndIncomeView) sumCostIncome.get(0);
            for (int x = 1; x < 3; x++) {
                if (x == 1) {
                    // set Header Report (Row 1)
                    HSSFCellStyle styleC1 = wb.createCellStyle();
                    HSSFRow row1 = sheetInc.createRow(0);
                    HSSFCell cell1 = row1.createCell(0);
                    cell1.setCellValue("Summary Ticket Cost & Income");
                    styleC1.setFont(excelFunction.getHeaderFont(wb.createFont()));
                    cell1.setCellStyle(styleC1);
                    sheetInc.addMergedRegion(CellRangeAddress.valueOf("A1:E1"));

                    // Row 2
                    HSSFRow row2 = sheetInc.createRow(1);
                    HSSFCell cell21 = row2.createCell(0);
                    cell21.setCellValue("Invoice Date : ");
                    cell21.setCellStyle(styleC21);
                    HSSFCell cell22 = row2.createCell(1);
                    cell22.setCellValue(dataheader.getHeaderinvdatefrom());
                    cell22.setCellStyle(styleC22);
                    HSSFCell cell23 = row2.createCell(2);
                    if (!"".equalsIgnoreCase(dataheader.getHeaderinvdateto())) {
                        cell23.setCellValue(" to " + dataheader.getHeaderinvdateto());
                        cell23.setCellStyle(styleC22);
                    }
                    HSSFCell cell24 = row2.createCell(3);
                    cell24.setCellValue("Print On : ");
                    cell24.setCellStyle(styleC21);
                    HSSFCell cell25 = row2.createCell(4);
                    cell25.setCellValue(dataheader.getHeaderprinton());
                    cell25.setCellStyle(styleC22);

                    // Row 3
                    HSSFRow row3 = sheetInc.createRow(2);
                    HSSFCell cell31 = row3.createCell(0);
                    cell31.setCellValue("Issue Date : ");
                    cell31.setCellStyle(styleC21);
                    HSSFCell cell32 = row3.createCell(1);
                    cell32.setCellValue(dataheader.getHeaderissuedatefrom());
                    cell32.setCellStyle(styleC22);
                    HSSFCell cell33 = row3.createCell(2);
                    if (!"".equalsIgnoreCase(dataheader.getHeaderissuedateto())) {
                        cell33.setCellValue(" to " + dataheader.getHeaderissuedateto());
                        cell33.setCellStyle(styleC22);
                    }
                    HSSFCell cell34 = row3.createCell(3);
                    cell34.setCellValue("Page : ");
                    cell34.setCellStyle(styleC21);
                    HSSFCell cell35 = row3.createCell(4);
                    cell35.setCellValue("1");
                    cell35.setCellStyle(styleC22);

                    // Row 4
                    HSSFRow row4 = sheetInc.createRow(3);
                    HSSFCell cell41 = row4.createCell(0);
                    cell41.setCellValue("Department : ");
                    cell41.setCellStyle(styleC21);
                    HSSFCell cell42 = row4.createCell(1);
                    cell42.setCellValue(dataheader.getHeaderdepartment());
                    cell42.setCellStyle(styleC22);

                    // Row 5
                    HSSFRow row5 = sheetInc.createRow(4);
                    HSSFCell cell51 = row5.createCell(0);
                    cell51.setCellValue("Sale Staff : ");
                    cell51.setCellStyle(styleC21);
                    HSSFCell cell52 = row5.createCell(1);
                    cell52.setCellValue(dataheader.getHeadersalestaff());
                    cell52.setCellStyle(styleC22);

                    // Row 6
                    HSSFRow row6 = sheetInc.createRow(5);
                    HSSFCell cell61 = row6.createCell(0);
                    cell61.setCellValue("Term Pay : ");
                    cell61.setCellStyle(styleC21);
                    HSSFCell cell62 = row6.createCell(1);
                    cell62.setCellValue(dataheader.getHeadertermpay());
                    cell62.setCellStyle(styleC22);

                }
                if (x == 2) {
                    // set Header Report (Row 1)
                    HSSFCellStyle styleC1 = wb.createCellStyle();
                    HSSFRow row1 = sheetIncSum.createRow(0);
                    HSSFCell cell1 = row1.createCell(0);
                    cell1.setCellValue("Summary Ticket Cost & Income");
                    styleC1.setFont(excelFunction.getHeaderFont(wb.createFont()));
                    cell1.setCellStyle(styleC1);
                    sheetIncSum.addMergedRegion(CellRangeAddress.valueOf("A1:E1"));

                    // Row 2
                    HSSFRow row2 = sheetIncSum.createRow(1);
                    HSSFCell cell21 = row2.createCell(0);
                    cell21.setCellValue("Invoice Date : ");
                    cell21.setCellStyle(styleC21);
                    HSSFCell cell22 = row2.createCell(1);
                    cell22.setCellValue(dataheader.getHeaderinvdatefrom());
                    cell22.setCellStyle(styleC22);
                    HSSFCell cell23 = row2.createCell(2);
                    if (!"".equalsIgnoreCase(dataheader.getHeaderinvdateto())) {
                        cell23.setCellValue(" to " + dataheader.getHeaderinvdateto());
                        cell23.setCellStyle(styleC22);
                    }
                    HSSFCell cell24 = row2.createCell(3);
                    cell24.setCellValue("Print On : ");
                    cell24.setCellStyle(styleC21);
                    HSSFCell cell25 = row2.createCell(4);
                    cell25.setCellValue(dataheader.getHeaderprinton());
                    cell25.setCellStyle(styleC22);

                    // Row 3
                    HSSFRow row3 = sheetIncSum.createRow(2);
                    HSSFCell cell31 = row3.createCell(0);
                    cell31.setCellValue("Issue Date : ");
                    cell31.setCellStyle(styleC21);
                    HSSFCell cell32 = row3.createCell(1);
                    cell32.setCellValue(dataheader.getHeaderissuedatefrom());
                    cell32.setCellStyle(styleC22);
                    HSSFCell cell33 = row3.createCell(2);
                    if (!"".equalsIgnoreCase(dataheader.getHeaderissuedateto())) {
                        cell33.setCellValue(" to " + dataheader.getHeaderissuedateto());
                        cell33.setCellStyle(styleC22);
                    }
                    HSSFCell cell34 = row3.createCell(3);
                    cell34.setCellValue("Page : ");
                    cell34.setCellStyle(styleC21);
                    HSSFCell cell35 = row3.createCell(4);
                    cell35.setCellValue("1");
                    cell35.setCellStyle(styleC22);

                    // Row 4
                    HSSFRow row4 = sheetIncSum.createRow(3);
                    HSSFCell cell41 = row4.createCell(0);
                    cell41.setCellValue("Department : ");
                    cell41.setCellStyle(styleC21);
                    HSSFCell cell42 = row4.createCell(1);
                    cell42.setCellValue(dataheader.getHeaderdepartment());
                    cell42.setCellStyle(styleC22);

                    // Row 5
                    HSSFRow row5 = sheetIncSum.createRow(4);
                    HSSFCell cell51 = row5.createCell(0);
                    cell51.setCellValue("Sale Staff : ");
                    cell51.setCellStyle(styleC21);
                    HSSFCell cell52 = row5.createCell(1);
                    cell52.setCellValue(dataheader.getHeadersalestaff());
                    cell52.setCellStyle(styleC22);

                    // Row 6
                    HSSFRow row6 = sheetIncSum.createRow(5);
                    HSSFCell cell61 = row6.createCell(0);
                    cell61.setCellValue("Term Pay : ");
                    cell61.setCellStyle(styleC21);
                    HSSFCell cell62 = row6.createCell(1);
                    cell62.setCellValue(dataheader.getHeadertermpay());
                    cell62.setCellStyle(styleC22);
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

            int count = 8;
            int tempcount = 0;
            int x = 0;

            BigDecimal interpax = new BigDecimal(0);
            BigDecimal interticket = new BigDecimal(0);
            BigDecimal intercost = new BigDecimal(0);
            BigDecimal interinbound = new BigDecimal(0);
            BigDecimal interwendy = new BigDecimal(0);
            BigDecimal interoutbound = new BigDecimal(0);
            BigDecimal interrefund = new BigDecimal(0);
            BigDecimal interbusinesstrip = new BigDecimal(0);
            BigDecimal interannualleave = new BigDecimal(0);
            BigDecimal internoinv = new BigDecimal(0);
            BigDecimal intercostinv = new BigDecimal(0);
            BigDecimal interinvwendy = new BigDecimal(0);
            BigDecimal interinvoutbound = new BigDecimal(0);
            BigDecimal interinvrefund = new BigDecimal(0);
            BigDecimal interinvbusinesstrip = new BigDecimal(0);
            BigDecimal interinvannualleave = new BigDecimal(0);
            BigDecimal interinvnoinv = new BigDecimal(0);

            BigDecimal domesticpax = new BigDecimal(0);
            BigDecimal domesticticket = new BigDecimal(0);
            BigDecimal domesticcost = new BigDecimal(0);
            BigDecimal domesticinbound = new BigDecimal(0);
            BigDecimal domesticwendy = new BigDecimal(0);
            BigDecimal domesticoutbound = new BigDecimal(0);
            BigDecimal domesticrefund = new BigDecimal(0);
            BigDecimal domesticbusinesstrip = new BigDecimal(0);
            BigDecimal domesticannualleave = new BigDecimal(0);
            BigDecimal domesticnoinv = new BigDecimal(0);
            BigDecimal domesticcostinv = new BigDecimal(0);
            BigDecimal domesticinvwendy = new BigDecimal(0);
            BigDecimal domesticinvoutbound = new BigDecimal(0);
            BigDecimal domesticinvrefund = new BigDecimal(0);
            BigDecimal domesticinvbusinesstrip = new BigDecimal(0);
            BigDecimal domesticinvannualleave = new BigDecimal(0);
            BigDecimal domesticinvnoinv = new BigDecimal(0);

            BigDecimal cancelpax = new BigDecimal(0);
            BigDecimal cancelticket = new BigDecimal(0);
            BigDecimal cancelcost = new BigDecimal(0);
            BigDecimal cancelinbound = new BigDecimal(0);
            BigDecimal cancelwendy = new BigDecimal(0);
            BigDecimal canceloutbound = new BigDecimal(0);
            BigDecimal cancelrefund = new BigDecimal(0);
            BigDecimal cancelbusinesstrip = new BigDecimal(0);
            BigDecimal cancelannualleave = new BigDecimal(0);
            BigDecimal cancelnoinv = new BigDecimal(0);
            BigDecimal cancelcostinv = new BigDecimal(0);
            BigDecimal cancelinvwendy = new BigDecimal(0);
            BigDecimal cancelinvoutbound = new BigDecimal(0);
            BigDecimal cancelinvrefund = new BigDecimal(0);
            BigDecimal cancelinvbusinesstrip = new BigDecimal(0);
            BigDecimal cancelinvannualleave = new BigDecimal(0);
            BigDecimal cancelinvnoinv = new BigDecimal(0);

            String costtypepaypast = "";
            String costtypepaypresent = "";
            String costtyperoutepast = "";
            String costtyperoutepresent = "";
            String costairpast = "";
            String costairpresent = "";
            int costtypepaypastline = 0;

            String costsumtypepaypast = "";
            String costsumtypepaypresent = "";
            String costsumtyperoutepast = "";
            String costsumtyperoutepresent = "";
            int costsumtypepaypastline = 0;
            for (int i = 0; i < sumCostIncome.size(); i++) {
                SummaryTicketCostAndIncomeView data = (SummaryTicketCostAndIncomeView) sumCostIncome.get(i);
                SummaryTicketCostAndIncomeView dataChk = (i > 0 ? (SummaryTicketCostAndIncomeView) sumCostIncome.get(i - 1) : null);
                if ("I".equalsIgnoreCase(String.valueOf(data.getTyperounting()))) {
                    BigDecimal interpaxtemp = (!"".equalsIgnoreCase(String.valueOf(data.getPax())) ? new BigDecimal(String.valueOf(data.getPax())) : new BigDecimal(0));
                    interpax = interpax.add(interpaxtemp);
                    BigDecimal intertickettemp = (!"".equalsIgnoreCase(String.valueOf(data.getTicketissue())) ? new BigDecimal(String.valueOf(data.getTicketissue())) : new BigDecimal(0));
                    interticket = interticket.add(intertickettemp);
                    BigDecimal intercosttemp = (!"".equalsIgnoreCase(String.valueOf(data.getCost())) ? new BigDecimal(String.valueOf(data.getCost())) : new BigDecimal(0));
                    intercost = intercost.add(intercosttemp);
                    BigDecimal interinboundtemp = (!"".equalsIgnoreCase(String.valueOf(data.getInbound())) ? new BigDecimal(String.valueOf(data.getInbound())) : new BigDecimal(0));
                    interinbound = interinbound.add(interinboundtemp);
                    BigDecimal interwendytemp = (!"".equalsIgnoreCase(String.valueOf(data.getWendy())) ? new BigDecimal(String.valueOf(data.getWendy())) : new BigDecimal(0));
                    interwendy = interwendy.add(interwendytemp);
                    BigDecimal interoutboundtemp = (!"".equalsIgnoreCase(String.valueOf(data.getOutbound())) ? new BigDecimal(String.valueOf(data.getOutbound())) : new BigDecimal(0));
                    interoutbound = interoutbound.add(interoutboundtemp);
                    BigDecimal interrefundtemp = (!"".equalsIgnoreCase(String.valueOf(data.getRefund())) ? new BigDecimal(String.valueOf(data.getRefund())) : new BigDecimal(0));
                    interrefund = interrefund.add(interrefundtemp);
                    BigDecimal interbusinesstriptemp = (!"".equalsIgnoreCase(String.valueOf(data.getBusinesstrip())) ? new BigDecimal(String.valueOf(data.getBusinesstrip())) : new BigDecimal(0));
                    interbusinesstrip = interbusinesstrip.add(interbusinesstriptemp);
                    BigDecimal interannualleavetemp = (!"".equalsIgnoreCase(String.valueOf(data.getAnnualleave())) ? new BigDecimal(String.valueOf(data.getAnnualleave())) : new BigDecimal(0));
                    interannualleave = interannualleave.add(interannualleavetemp);
                    BigDecimal internoinvtemp = (!"".equalsIgnoreCase(String.valueOf(data.getNoinvoice())) ? new BigDecimal(String.valueOf(data.getNoinvoice())) : new BigDecimal(0));
                    internoinv = internoinv.add(internoinvtemp);
                    BigDecimal intercostinvtemp = (!"".equalsIgnoreCase(String.valueOf(data.getCostinv())) ? new BigDecimal(String.valueOf(data.getCostinv())) : new BigDecimal(0));
                    intercostinv = intercostinv.add(intercostinvtemp);
                    BigDecimal interinvwendytemp = (!"".equalsIgnoreCase(String.valueOf(data.getInvwendy())) ? new BigDecimal(String.valueOf(data.getInvwendy())) : new BigDecimal(0));
                    interinvwendy = interinvwendy.add(interinvwendytemp);
                    BigDecimal interinvoutboundtemp = (!"".equalsIgnoreCase(String.valueOf(data.getInvoutbound())) ? new BigDecimal(String.valueOf(data.getInvoutbound())) : new BigDecimal(0));
                    interinvoutbound = interinvoutbound.add(interinvoutboundtemp);
                    BigDecimal interinvrefundtemp = (!"".equalsIgnoreCase(String.valueOf(data.getInvrefund())) ? new BigDecimal(String.valueOf(data.getInvrefund())) : new BigDecimal(0));
                    interinvrefund = interinvrefund.add(interinvrefundtemp);
                    BigDecimal interinvbusinesstriptemp = (!"".equalsIgnoreCase(String.valueOf(data.getInvbusinesstrip())) ? new BigDecimal(String.valueOf(data.getInvbusinesstrip())) : new BigDecimal(0));
                    interinvbusinesstrip = interinvbusinesstrip.add(interinvbusinesstriptemp);
                    BigDecimal interinvannualleavetemp = (!"".equalsIgnoreCase(String.valueOf(data.getInvannualleave())) ? new BigDecimal(String.valueOf(data.getInvannualleave())) : new BigDecimal(0));
                    interinvannualleave = interinvannualleave.add(interinvannualleavetemp);
                    BigDecimal interinvnoinvtemp = (!"".equalsIgnoreCase(String.valueOf(data.getInvnoinvoice())) ? new BigDecimal(String.valueOf(data.getInvnoinvoice())) : new BigDecimal(0));
                    interinvnoinv = interinvnoinv.add(interinvnoinvtemp);

                } else if ("D".equalsIgnoreCase(String.valueOf(data.getTyperounting()))) {
                    BigDecimal domesticpaxtemp = (!"".equalsIgnoreCase(String.valueOf(data.getPax())) ? new BigDecimal(String.valueOf(data.getPax())) : new BigDecimal(0));
                    domesticpax = domesticpax.add(domesticpaxtemp);
                    BigDecimal domestictickettemp = (!"".equalsIgnoreCase(String.valueOf(data.getTicketissue())) ? new BigDecimal(String.valueOf(data.getTicketissue())) : new BigDecimal(0));
                    domesticticket = domesticticket.add(domestictickettemp);
                    BigDecimal domesticcosttemp = (!"".equalsIgnoreCase(String.valueOf(data.getCost())) ? new BigDecimal(String.valueOf(data.getCost())) : new BigDecimal(0));
                    domesticcost = domesticcost.add(domesticcosttemp);
                    BigDecimal domesticinboundtemp = (!"".equalsIgnoreCase(String.valueOf(data.getInbound())) ? new BigDecimal(String.valueOf(data.getInbound())) : new BigDecimal(0));
                    domesticinbound = domesticinbound.add(domesticinboundtemp);
                    BigDecimal domesticwendytemp = (!"".equalsIgnoreCase(String.valueOf(data.getWendy())) ? new BigDecimal(String.valueOf(data.getWendy())) : new BigDecimal(0));
                    domesticwendy = domesticwendy.add(domesticwendytemp);
                    BigDecimal domesticoutboundtemp = (!"".equalsIgnoreCase(String.valueOf(data.getOutbound())) ? new BigDecimal(String.valueOf(data.getOutbound())) : new BigDecimal(0));
                    domesticoutbound = domesticoutbound.add(domesticoutboundtemp);
                    BigDecimal domesticrefundtemp = (!"".equalsIgnoreCase(String.valueOf(data.getRefund())) ? new BigDecimal(String.valueOf(data.getRefund())) : new BigDecimal(0));
                    domesticrefund = domesticrefund.add(domesticrefundtemp);
                    BigDecimal domesticbusinesstriptemp = (!"".equalsIgnoreCase(String.valueOf(data.getBusinesstrip())) ? new BigDecimal(String.valueOf(data.getBusinesstrip())) : new BigDecimal(0));
                    domesticbusinesstrip = domesticbusinesstrip.add(domesticbusinesstriptemp);
                    BigDecimal domesticannualleavetemp = (!"".equalsIgnoreCase(String.valueOf(data.getAnnualleave())) ? new BigDecimal(String.valueOf(data.getAnnualleave())) : new BigDecimal(0));
                    domesticannualleave = domesticannualleave.add(domesticannualleavetemp);
                    BigDecimal domesticnoinvtemp = (!"".equalsIgnoreCase(String.valueOf(data.getNoinvoice())) ? new BigDecimal(String.valueOf(data.getNoinvoice())) : new BigDecimal(0));
                    domesticnoinv = domesticnoinv.add(domesticnoinvtemp);
                    BigDecimal domesticcostinvtemp = (!"".equalsIgnoreCase(String.valueOf(data.getCostinv())) ? new BigDecimal(String.valueOf(data.getCostinv())) : new BigDecimal(0));
                    domesticcostinv = domesticcostinv.add(domesticcostinvtemp);
                    BigDecimal domesticinvwendytemp = (!"".equalsIgnoreCase(String.valueOf(data.getInvwendy())) ? new BigDecimal(String.valueOf(data.getInvwendy())) : new BigDecimal(0));
                    domesticinvwendy = domesticinvwendy.add(domesticinvwendytemp);
                    BigDecimal domesticinvoutboundtemp = (!"".equalsIgnoreCase(String.valueOf(data.getInvoutbound())) ? new BigDecimal(String.valueOf(data.getInvoutbound())) : new BigDecimal(0));
                    domesticinvoutbound = domesticinvoutbound.add(domesticinvoutboundtemp);
                    BigDecimal domesticinvrefundtemp = (!"".equalsIgnoreCase(String.valueOf(data.getInvrefund())) ? new BigDecimal(String.valueOf(data.getInvrefund())) : new BigDecimal(0));
                    domesticinvrefund = domesticinvrefund.add(domesticinvrefundtemp);
                    BigDecimal domesticinvbusinesstriptemp = (!"".equalsIgnoreCase(String.valueOf(data.getInvbusinesstrip())) ? new BigDecimal(String.valueOf(data.getInvbusinesstrip())) : new BigDecimal(0));
                    domesticinvbusinesstrip = domesticinvbusinesstrip.add(domesticinvbusinesstriptemp);
                    BigDecimal domesticinvannualleavetemp = (!"".equalsIgnoreCase(String.valueOf(data.getInvannualleave())) ? new BigDecimal(String.valueOf(data.getInvannualleave())) : new BigDecimal(0));
                    domesticinvannualleave = domesticinvannualleave.add(domesticinvannualleavetemp);
                    BigDecimal domesticinvnoinvtemp = (!"".equalsIgnoreCase(String.valueOf(data.getInvnoinvoice())) ? new BigDecimal(String.valueOf(data.getInvnoinvoice())) : new BigDecimal(0));
                    domesticinvnoinv = domesticinvnoinv.add(domesticinvnoinvtemp);

                } else if ("C".equalsIgnoreCase(String.valueOf(data.getTyperounting()))) {
                    BigDecimal cancelpaxtemp = (!"".equalsIgnoreCase(String.valueOf(data.getPax())) ? new BigDecimal(String.valueOf(data.getPax())) : new BigDecimal(0));
                    cancelpax = cancelpax.add(cancelpaxtemp);
                    BigDecimal canceltickettemp = (!"".equalsIgnoreCase(String.valueOf(data.getTicketissue())) ? new BigDecimal(String.valueOf(data.getTicketissue())) : new BigDecimal(0));
                    cancelticket = cancelticket.add(canceltickettemp);
                    BigDecimal cancelcosttemp = (!"".equalsIgnoreCase(String.valueOf(data.getCost())) ? new BigDecimal(String.valueOf(data.getCost())) : new BigDecimal(0));
                    cancelcost = cancelcost.add(cancelcosttemp);
                    BigDecimal cancelinboundtemp = (!"".equalsIgnoreCase(String.valueOf(data.getInbound())) ? new BigDecimal(String.valueOf(data.getInbound())) : new BigDecimal(0));
                    cancelinbound = cancelinbound.add(cancelinboundtemp);
                    BigDecimal cancelwendytemp = (!"".equalsIgnoreCase(String.valueOf(data.getWendy())) ? new BigDecimal(String.valueOf(data.getWendy())) : new BigDecimal(0));
                    cancelwendy = cancelwendy.add(cancelwendytemp);
                    BigDecimal canceloutboundtemp = (!"".equalsIgnoreCase(String.valueOf(data.getOutbound())) ? new BigDecimal(String.valueOf(data.getOutbound())) : new BigDecimal(0));
                    canceloutbound = canceloutbound.add(canceloutboundtemp);
                    BigDecimal cancelrefundtemp = (!"".equalsIgnoreCase(String.valueOf(data.getRefund())) ? new BigDecimal(String.valueOf(data.getRefund())) : new BigDecimal(0));
                    cancelrefund = cancelrefund.add(cancelrefundtemp);
                    BigDecimal cancelbusinesstriptemp = (!"".equalsIgnoreCase(String.valueOf(data.getBusinesstrip())) ? new BigDecimal(String.valueOf(data.getBusinesstrip())) : new BigDecimal(0));
                    cancelbusinesstrip = cancelbusinesstrip.add(cancelbusinesstriptemp);
                    BigDecimal cancelannualleavetemp = (!"".equalsIgnoreCase(String.valueOf(data.getAnnualleave())) ? new BigDecimal(String.valueOf(data.getAnnualleave())) : new BigDecimal(0));
                    cancelannualleave = cancelannualleave.add(cancelannualleavetemp);
                    BigDecimal cancelnoinvtemp = (!"".equalsIgnoreCase(String.valueOf(data.getNoinvoice())) ? new BigDecimal(String.valueOf(data.getNoinvoice())) : new BigDecimal(0));
                    cancelnoinv = cancelnoinv.add(cancelnoinvtemp);
                    BigDecimal cancelcostinvtemp = (!"".equalsIgnoreCase(String.valueOf(data.getCostinv())) ? new BigDecimal(String.valueOf(data.getCostinv())) : new BigDecimal(0));
                    cancelcostinv = cancelcostinv.add(cancelcostinvtemp);
                    BigDecimal cancelinvwendytemp = (!"".equalsIgnoreCase(String.valueOf(data.getInvwendy())) ? new BigDecimal(String.valueOf(data.getInvwendy())) : new BigDecimal(0));
                    cancelinvwendy = cancelinvwendy.add(cancelinvwendytemp);
                    BigDecimal cancelinvoutboundtemp = (!"".equalsIgnoreCase(String.valueOf(data.getInvoutbound())) ? new BigDecimal(String.valueOf(data.getInvoutbound())) : new BigDecimal(0));
                    cancelinvoutbound = cancelinvoutbound.add(cancelinvoutboundtemp);
                    BigDecimal cancelinvrefundtemp = (!"".equalsIgnoreCase(String.valueOf(data.getInvrefund())) ? new BigDecimal(String.valueOf(data.getInvrefund())) : new BigDecimal(0));
                    cancelinvrefund = cancelinvrefund.add(cancelinvrefundtemp);
                    BigDecimal cancelinvbusinesstriptemp = (!"".equalsIgnoreCase(String.valueOf(data.getInvbusinesstrip())) ? new BigDecimal(String.valueOf(data.getInvbusinesstrip())) : new BigDecimal(0));
                    cancelinvbusinesstrip = cancelinvbusinesstrip.add(cancelinvbusinesstriptemp);
                    BigDecimal cancelinvannualleavetemp = (!"".equalsIgnoreCase(String.valueOf(data.getInvannualleave())) ? new BigDecimal(String.valueOf(data.getInvannualleave())) : new BigDecimal(0));
                    cancelinvannualleave = cancelinvannualleave.add(cancelinvannualleavetemp);
                    BigDecimal cancelinvnoinvtemp = (!"".equalsIgnoreCase(String.valueOf(data.getInvnoinvoice())) ? new BigDecimal(String.valueOf(data.getInvnoinvoice())) : new BigDecimal(0));
                    cancelinvnoinv = cancelinvnoinv.add(cancelinvnoinvtemp);

                }

                if ("income".equalsIgnoreCase(data.getPage())) {
                    //inv
                    HSSFRow row8 = sheetInc.createRow(7);
                    HSSFCell cell81 = row8.createCell(0);
                    cell81.setCellValue("Type Pay");
                    cell81.setCellStyle(styleC3);
                    HSSFCell cell82 = row8.createCell(1);
                    cell82.setCellValue("Type Route");
                    cell82.setCellStyle(styleC3);
                    HSSFCell cell83 = row8.createCell(2);
                    cell83.setCellValue("Pax");
                    cell83.setCellStyle(styleC3);
                    HSSFCell cell84 = row8.createCell(3);
                    cell84.setCellValue("Air");
                    cell84.setCellStyle(styleC3);
                    HSSFCell cell85 = row8.createCell(4);
                    cell85.setCellValue("Ticket Issue");
                    cell85.setCellStyle(styleC3);
                    HSSFCell cell86 = row8.createCell(5);
                    cell86.setCellValue("Inv. No");
                    cell86.setCellStyle(styleC3);
                    HSSFCell cell87 = row8.createCell(6);
                    cell87.setCellValue("Cost");
                    cell87.setCellStyle(styleC3);
                    HSSFCell cell88 = row8.createCell(7);
                    cell88.setCellValue("Inbound");
                    cell88.setCellStyle(styleC3);
                    HSSFCell cell89 = row8.createCell(8);
                    cell89.setCellValue("Wendy(N)");
                    cell89.setCellStyle(styleC3);
                    HSSFCell cell90 = row8.createCell(9);
                    cell90.setCellValue("Outbound");
                    cell90.setCellStyle(styleC3);
                    HSSFCell cell91 = row8.createCell(10);
                    cell91.setCellValue("Refund");
                    cell91.setCellStyle(styleC3);
                    HSSFCell cell92 = row8.createCell(11);
                    cell92.setCellValue("Business Trip");
                    cell92.setCellStyle(styleC3);
                    HSSFCell cell93 = row8.createCell(12);
                    cell93.setCellValue("Annual Leave");
                    cell93.setCellStyle(styleC3);
                    HSSFCell cell94 = row8.createCell(13);
                    cell94.setCellValue("No Inv");
                    cell94.setCellStyle(styleC3);
                    HSSFCell cell95 = row8.createCell(14);
                    cell95.setCellValue("Cost Inv");
                    cell95.setCellStyle(styleC3);
                    HSSFCell cell96 = row8.createCell(15);
                    cell96.setCellValue("Inv Wendy");
                    cell96.setCellStyle(styleC3);
                    HSSFCell cell97 = row8.createCell(16);
                    cell97.setCellValue("Inv Outbound");
                    cell97.setCellStyle(styleC3);
                    HSSFCell cell98 = row8.createCell(17);
                    cell98.setCellValue("Inv Refund");
                    cell98.setCellStyle(styleC3);
                    HSSFCell cell99 = row8.createCell(18);
                    cell99.setCellValue("Inv Business Trip");
                    cell99.setCellStyle(styleC3);
                    HSSFCell cell100 = row8.createCell(19);
                    cell100.setCellValue("Inv Annual Leave");
                    cell100.setCellStyle(styleC3);
                    HSSFCell cell101 = row8.createCell(20);
                    cell101.setCellValue("Inv No Inv");
                    cell101.setCellStyle(styleC3);

                    HSSFRow row = sheetInc.createRow(count + i);

                    costtypepaypast = (dataChk == null ? "" : String.valueOf(dataChk.getTypepayment()));
                    costtypepaypresent = String.valueOf(data.getTypepayment());
                    costtyperoutepast = (dataChk == null ? "" : String.valueOf(dataChk.getTyperounting()));
                    costtyperoutepresent = String.valueOf(data.getTyperounting());
                    costairpast = (dataChk == null ? "" : String.valueOf(dataChk.getAir()));
                    costairpresent = String.valueOf(data.getAir());
                    costtypepaypastline = ((costtypepaypast.equalsIgnoreCase(costtypepaypresent)) && ((costtyperoutepast.equalsIgnoreCase(costtyperoutepresent))) && ((costairpast.equalsIgnoreCase(costairpresent))) ? costtypepaypastline : i);

                    HSSFCell celldata0 = row.createCell(0);
                    celldata0.setCellValue(String.valueOf(data.getTypepayment()));
                    celldata0.setCellStyle(styleC29);

                    HSSFCell celldata1 = row.createCell(1);
                    celldata1.setCellValue(String.valueOf(data.getTyperounting()));
                    celldata1.setCellStyle(styleC30);

                    HSSFCell celldata2 = row.createCell(2);
                    celldata2.setCellValue("".equalsIgnoreCase(String.valueOf(data.getPax())) ? 0 : (new BigDecimal(data.getPax())).doubleValue());
                    celldata2.setCellStyle(styleC26);

                    HSSFCell celldata3 = row.createCell(3);
                    celldata3.setCellValue(String.valueOf(data.getAir()));
                    celldata3.setCellStyle(styleC26);

                    HSSFCell celldata4 = row.createCell(4);
                    if ((costtypepaypast.equalsIgnoreCase(costtypepaypresent)) && ((costtyperoutepast.equalsIgnoreCase(costtyperoutepresent))) && ((costairpast.equalsIgnoreCase(costairpresent)))) {
                        celldata4.setCellValue("".equalsIgnoreCase(String.valueOf(data.getTicketissue())) ? 0 : (new BigDecimal(data.getTicketissue())).doubleValue());
                        sheetInc.addMergedRegion(CellRangeAddress.valueOf("E" + (count + costtypepaypastline + 1) + ":E" + (count + i + 1)));
                        celldata4.setCellStyle(styleC25);
                    } else {
                        celldata4.setCellValue("".equalsIgnoreCase(String.valueOf(data.getTicketissue())) ? 0 : (new BigDecimal(data.getTicketissue())).doubleValue());
                        celldata4.setCellStyle(styleC25);
                    }

                    HSSFCell celldata5 = row.createCell(5);
                    celldata5.setCellValue(String.valueOf(data.getInvno()));
                    celldata5.setCellStyle(styleC29);

                    HSSFCell celldata6 = row.createCell(6);
                    celldata6.setCellValue("".equalsIgnoreCase(String.valueOf(data.getCost())) ? 0 : (new BigDecimal(data.getCost())).doubleValue());
                    celldata6.setCellStyle(styleC25);

                    HSSFCell celldata7 = row.createCell(7);
                    celldata7.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInbound())) ? 0 : (new BigDecimal(data.getInbound())).doubleValue());
                    celldata7.setCellStyle(styleC25);

                    HSSFCell celldata8 = row.createCell(8);
                    celldata8.setCellValue("".equalsIgnoreCase(String.valueOf(data.getWendy())) ? 0 : (new BigDecimal(data.getWendy())).doubleValue());
                    celldata8.setCellStyle(styleC25);

                    HSSFCell celldata9 = row.createCell(9);
                    celldata9.setCellValue("".equalsIgnoreCase(String.valueOf(data.getOutbound())) ? 0 : (new BigDecimal(data.getOutbound())).doubleValue());
                    celldata9.setCellStyle(styleC25);

                    HSSFCell celldata10 = row.createCell(10);
                    celldata10.setCellValue("".equalsIgnoreCase(String.valueOf(data.getRefund())) ? 0 : (new BigDecimal(data.getRefund())).doubleValue());
                    celldata10.setCellStyle(styleC25);

                    HSSFCell celldata11 = row.createCell(11);
                    celldata11.setCellValue("".equalsIgnoreCase(String.valueOf(data.getBusinesstrip())) ? 0 : (new BigDecimal(data.getBusinesstrip())).doubleValue());
                    celldata11.setCellStyle(styleC25);

                    HSSFCell celldata12 = row.createCell(12);
                    celldata12.setCellValue("".equalsIgnoreCase(String.valueOf(data.getAnnualleave())) ? 0 : (new BigDecimal(data.getAnnualleave())).doubleValue());
                    celldata12.setCellStyle(styleC25);

                    HSSFCell celldata13 = row.createCell(13);
                    celldata13.setCellValue("".equalsIgnoreCase(String.valueOf(data.getNoinvoice())) ? 0 : (new BigDecimal(data.getNoinvoice())).doubleValue());
                    celldata13.setCellStyle(styleC25);

                    HSSFCell celldata14 = row.createCell(14);
                    celldata14.setCellValue("".equalsIgnoreCase(String.valueOf(data.getCostinv())) ? 0 : (new BigDecimal(data.getCostinv())).doubleValue());
                    celldata14.setCellStyle(styleC25);

                    HSSFCell celldata15 = row.createCell(15);
                    celldata15.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInvwendy())) ? 0 : (new BigDecimal(data.getInvwendy())).doubleValue());
                    celldata15.setCellStyle(styleC25);

                    HSSFCell celldata16 = row.createCell(16);
                    celldata16.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInvoutbound())) ? 0 : (new BigDecimal(data.getInvoutbound())).doubleValue());
                    celldata16.setCellStyle(styleC25);

                    HSSFCell celldata17 = row.createCell(17);
                    celldata17.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInvrefund())) ? 0 : (new BigDecimal(data.getInvrefund())).doubleValue());
                    celldata17.setCellStyle(styleC25);

                    HSSFCell celldata18 = row.createCell(18);
                    celldata18.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInvbusinesstrip())) ? 0 : (new BigDecimal(data.getInvbusinesstrip())).doubleValue());
                    celldata18.setCellStyle(styleC25);

                    HSSFCell celldata19 = row.createCell(19);
                    celldata19.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInvannualleave())) ? 0 : (new BigDecimal(data.getInvannualleave())).doubleValue());
                    celldata19.setCellStyle(styleC25);

                    HSSFCell celldata20 = row.createCell(20);
                    celldata20.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInvnoinvoice())) ? 0 : (new BigDecimal(data.getInvnoinvoice())).doubleValue());
                    celldata20.setCellStyle(styleC25);

                    tempcount = count + i + 1;
                }
                if ("incomesum".equalsIgnoreCase(data.getPage())) {
                    HSSFCellStyle styleTotal = wb.createCellStyle();
                    styleTotal.setFont(excelFunction.getHeaderTable(wb.createFont()));
                    styleTotal.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                    styleTotal.setBorderRight(HSSFCellStyle.BORDER_THIN);
                    styleTotal.setBorderTop(HSSFCellStyle.BORDER_THIN);
                    styleTotal.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                    styleTotal.setAlignment(styleC22.ALIGN_RIGHT);

                    //Inter
                    HSSFRow rowsinter1 = sheetInc.createRow(tempcount);
                    sheetInc.addMergedRegion(CellRangeAddress.valueOf("A" + (tempcount + 1) + ":B" + (tempcount + 1)));
                    HSSFCell cellInter00C = rowsinter1.createCell(0);
                    cellInter00C.setCellValue("Total Inter");
                    cellInter00C.setCellStyle(styleTotal);
                    HSSFCell cellInter01C = rowsinter1.createCell(2);
                    cellInter01C.setCellValue(interpax.doubleValue());
                    cellInter01C.setCellStyle(styleC26);
                    HSSFCell cellInter02C = rowsinter1.createCell(3);
                    cellInter02C.setCellValue(interticket.doubleValue());
                    cellInter02C.setCellStyle(styleC25);
                    HSSFCell cellInter03C = rowsinter1.createCell(6);
                    cellInter03C.setCellValue(intercost.doubleValue());
                    cellInter03C.setCellStyle(styleC25);
                    HSSFCell cellInter04C = rowsinter1.createCell(7);
                    cellInter04C.setCellValue(interinbound.doubleValue());
                    cellInter04C.setCellStyle(styleC25);
                    HSSFCell cellInter05C = rowsinter1.createCell(8);
                    cellInter05C.setCellValue(interwendy.doubleValue());
                    cellInter05C.setCellStyle(styleC25);
                    HSSFCell cellInter06C = rowsinter1.createCell(9);
                    cellInter06C.setCellValue(interoutbound.doubleValue());
                    cellInter06C.setCellStyle(styleC25);
                    HSSFCell cellInter07C = rowsinter1.createCell(10);
                    cellInter07C.setCellValue(interrefund.doubleValue());
                    cellInter07C.setCellStyle(styleC25);
                    HSSFCell cellInter08C = rowsinter1.createCell(11);
                    cellInter08C.setCellValue(interbusinesstrip.doubleValue());
                    cellInter08C.setCellStyle(styleC25);
                    HSSFCell cellInter09C = rowsinter1.createCell(12);
                    cellInter09C.setCellValue(interannualleave.doubleValue());
                    cellInter09C.setCellStyle(styleC25);
                    HSSFCell cellInter10C = rowsinter1.createCell(13);
                    cellInter10C.setCellValue(internoinv.doubleValue());
                    cellInter10C.setCellStyle(styleC25);
                    HSSFCell cellInter11C = rowsinter1.createCell(14);
                    cellInter11C.setCellValue(intercostinv.doubleValue());
                    cellInter11C.setCellStyle(styleC25);
                    HSSFCell cellInter12C = rowsinter1.createCell(15);
                    cellInter12C.setCellValue(interinvwendy.doubleValue());
                    cellInter12C.setCellStyle(styleC25);
                    HSSFCell cellInter13C = rowsinter1.createCell(16);
                    cellInter13C.setCellValue(interinvoutbound.doubleValue());
                    cellInter13C.setCellStyle(styleC25);
                    HSSFCell cellInter14C = rowsinter1.createCell(17);
                    cellInter14C.setCellValue(interinvrefund.doubleValue());
                    cellInter14C.setCellStyle(styleC25);
                    HSSFCell cellInter15C = rowsinter1.createCell(18);
                    cellInter15C.setCellValue(interinvbusinesstrip.doubleValue());
                    cellInter15C.setCellStyle(styleC25);
                    HSSFCell cellInter16C = rowsinter1.createCell(19);
                    cellInter16C.setCellValue(interinvannualleave.doubleValue());
                    cellInter16C.setCellStyle(styleC25);
                    HSSFCell cellInter17C = rowsinter1.createCell(20);
                    cellInter17C.setCellValue(interinvnoinv.doubleValue());
                    cellInter17C.setCellStyle(styleC25);
                    HSSFCell cellInter18C = rowsinter1.createCell(1);
                    cellInter18C.setCellStyle(styleTotal);
                    HSSFCell cellInter19C = rowsinter1.createCell(3);
                    cellInter19C.setCellStyle(styleTotal);
                    HSSFCell cellInter20C = rowsinter1.createCell(4);
                    cellInter20C.setCellStyle(styleTotal);
                    HSSFCell cellInter21C = rowsinter1.createCell(5);
                    cellInter21C.setCellStyle(styleTotal);

                    //Domestic
                    HSSFRow rowsdomestic1 = sheetInc.createRow(tempcount + 1);
                    sheetInc.addMergedRegion(CellRangeAddress.valueOf("A" + (tempcount + 2) + ":B" + (tempcount + 2)));
                    HSSFCell cellDomestic00C = rowsdomestic1.createCell(0);
                    cellDomestic00C.setCellValue("Total Domestic");
                    cellDomestic00C.setCellStyle(styleTotal);
                    HSSFCell cellDomestic01C = rowsdomestic1.createCell(2);
                    cellDomestic01C.setCellValue(domesticpax.doubleValue());
                    cellDomestic01C.setCellStyle(styleC26);
                    HSSFCell cellDomestic02C = rowsdomestic1.createCell(3);
                    cellDomestic02C.setCellValue(domesticticket.doubleValue());
                    cellDomestic02C.setCellStyle(styleC25);
                    HSSFCell cellDomestic03C = rowsdomestic1.createCell(6);
                    cellDomestic03C.setCellValue(domesticcost.doubleValue());
                    cellDomestic03C.setCellStyle(styleC25);
                    HSSFCell cellDomestic04C = rowsdomestic1.createCell(7);
                    cellDomestic04C.setCellValue(domesticinbound.doubleValue());
                    cellDomestic04C.setCellStyle(styleC25);
                    HSSFCell cellDomestic05C = rowsdomestic1.createCell(8);
                    cellDomestic05C.setCellValue(domesticwendy.doubleValue());
                    cellDomestic05C.setCellStyle(styleC25);
                    HSSFCell cellDomestic06C = rowsdomestic1.createCell(9);
                    cellDomestic06C.setCellValue(domesticoutbound.doubleValue());
                    cellDomestic06C.setCellStyle(styleC25);
                    HSSFCell cellDomestic07C = rowsdomestic1.createCell(10);
                    cellDomestic07C.setCellValue(domesticrefund.doubleValue());
                    cellDomestic07C.setCellStyle(styleC25);
                    HSSFCell cellDomestic08C = rowsdomestic1.createCell(11);
                    cellDomestic08C.setCellValue(domesticbusinesstrip.doubleValue());
                    cellDomestic08C.setCellStyle(styleC25);
                    HSSFCell cellDomestic09C = rowsdomestic1.createCell(12);
                    cellDomestic09C.setCellValue(domesticannualleave.doubleValue());
                    cellDomestic09C.setCellStyle(styleC25);
                    HSSFCell cellDomestic10C = rowsdomestic1.createCell(13);
                    cellDomestic10C.setCellValue(domesticnoinv.doubleValue());
                    cellDomestic10C.setCellStyle(styleC25);
                    HSSFCell cellDomestic11C = rowsdomestic1.createCell(14);
                    cellDomestic11C.setCellValue(domesticcostinv.doubleValue());
                    cellDomestic11C.setCellStyle(styleC25);
                    HSSFCell cellDomestic12C = rowsdomestic1.createCell(15);
                    cellDomestic12C.setCellValue(domesticinvwendy.doubleValue());
                    cellDomestic12C.setCellStyle(styleC25);
                    HSSFCell cellDomestic13C = rowsdomestic1.createCell(16);
                    cellDomestic13C.setCellValue(domesticinvoutbound.doubleValue());
                    cellDomestic13C.setCellStyle(styleC25);
                    HSSFCell cellDomestic14C = rowsdomestic1.createCell(17);
                    cellDomestic14C.setCellValue(domesticinvrefund.doubleValue());
                    cellDomestic14C.setCellStyle(styleC25);
                    HSSFCell cellDomestic15C = rowsdomestic1.createCell(18);
                    cellDomestic15C.setCellValue(domesticinvbusinesstrip.doubleValue());
                    cellDomestic15C.setCellStyle(styleC25);
                    HSSFCell cellDomestic16C = rowsdomestic1.createCell(19);
                    cellDomestic16C.setCellValue(domesticinvannualleave.doubleValue());
                    cellDomestic16C.setCellStyle(styleC25);
                    HSSFCell cellDomestic17C = rowsdomestic1.createCell(20);
                    cellDomestic17C.setCellValue(domesticinvnoinv.doubleValue());
                    cellDomestic17C.setCellStyle(styleC25);
                    HSSFCell cellDomestic18C = rowsdomestic1.createCell(1);
                    cellDomestic18C.setCellStyle(styleTotal);
                    HSSFCell cellDomestic19C = rowsdomestic1.createCell(3);
                    cellDomestic19C.setCellStyle(styleTotal);
                    HSSFCell cellDomestic20C = rowsdomestic1.createCell(4);
                    cellDomestic20C.setCellStyle(styleTotal);
                    HSSFCell cellDomestic21C = rowsdomestic1.createCell(5);
                    cellDomestic21C.setCellStyle(styleTotal);

                    //Cancel
                    HSSFRow rowscancl1 = sheetInc.createRow(tempcount + 2);
                    sheetInc.addMergedRegion(CellRangeAddress.valueOf("A" + (tempcount + 3) + ":B" + (tempcount + 3)));
                    HSSFCell cellCancel00C = rowscancl1.createCell(0);
                    cellCancel00C.setCellValue("Total Cancel");
                    cellCancel00C.setCellStyle(styleTotal);
                    HSSFCell cellCancel01C = rowscancl1.createCell(2);
                    cellCancel01C.setCellValue(cancelpax.doubleValue());
                    cellCancel01C.setCellStyle(styleC26);
                    HSSFCell cellCancel02C = rowscancl1.createCell(3);
                    cellCancel02C.setCellValue(cancelticket.doubleValue());
                    cellCancel02C.setCellStyle(styleC25);
                    HSSFCell cellCancel03C = rowscancl1.createCell(6);
                    cellCancel03C.setCellValue(cancelcost.doubleValue());
                    cellCancel03C.setCellStyle(styleC25);
                    HSSFCell cellCancel04C = rowscancl1.createCell(7);
                    cellCancel04C.setCellValue(cancelinbound.doubleValue());
                    cellCancel04C.setCellStyle(styleC25);
                    HSSFCell cellCancel05C = rowscancl1.createCell(8);
                    cellCancel05C.setCellValue(cancelwendy.doubleValue());
                    cellCancel05C.setCellStyle(styleC25);
                    HSSFCell cellCancel06C = rowscancl1.createCell(9);
                    cellCancel06C.setCellValue(canceloutbound.doubleValue());
                    cellCancel06C.setCellStyle(styleC25);
                    HSSFCell cellCancel07C = rowscancl1.createCell(10);
                    cellCancel07C.setCellValue(cancelrefund.doubleValue());
                    cellCancel07C.setCellStyle(styleC25);
                    HSSFCell cellCancel08C = rowscancl1.createCell(11);
                    cellCancel08C.setCellValue(cancelbusinesstrip.doubleValue());
                    cellCancel08C.setCellStyle(styleC25);
                    HSSFCell cellCancel09C = rowscancl1.createCell(12);
                    cellCancel09C.setCellValue(cancelannualleave.doubleValue());
                    cellCancel09C.setCellStyle(styleC25);
                    HSSFCell cellCancel10C = rowscancl1.createCell(13);
                    cellCancel10C.setCellValue(cancelnoinv.doubleValue());
                    cellCancel10C.setCellStyle(styleC25);
                    HSSFCell cellCancel11C = rowscancl1.createCell(14);
                    cellCancel11C.setCellValue(cancelcostinv.doubleValue());
                    cellCancel11C.setCellStyle(styleC25);
                    HSSFCell cellCancel12C = rowscancl1.createCell(15);
                    cellCancel12C.setCellValue(cancelinvwendy.doubleValue());
                    cellCancel12C.setCellStyle(styleC25);
                    HSSFCell cellCancel13C = rowscancl1.createCell(16);
                    cellCancel13C.setCellValue(cancelinvoutbound.doubleValue());
                    cellCancel13C.setCellStyle(styleC25);
                    HSSFCell cellCancel14C = rowscancl1.createCell(17);
                    cellCancel14C.setCellValue(cancelinvrefund.doubleValue());
                    cellCancel14C.setCellStyle(styleC25);
                    HSSFCell cellCancel15C = rowscancl1.createCell(18);
                    cellCancel15C.setCellValue(cancelinvbusinesstrip.doubleValue());
                    cellCancel15C.setCellStyle(styleC25);
                    HSSFCell cellCancel16C = rowscancl1.createCell(19);
                    cellCancel16C.setCellValue(cancelinvannualleave.doubleValue());
                    cellCancel16C.setCellStyle(styleC25);
                    HSSFCell cellCancel17C = rowscancl1.createCell(20);
                    cellCancel17C.setCellValue(cancelinvnoinv.doubleValue());
                    cellCancel17C.setCellStyle(styleC25);
                    HSSFCell cellCancel18C = rowscancl1.createCell(1);
                    cellCancel18C.setCellStyle(styleTotal);
                    HSSFCell cellCancel19C = rowscancl1.createCell(3);
                    cellCancel19C.setCellStyle(styleTotal);
                    HSSFCell cellCancel20C = rowscancl1.createCell(4);
                    cellCancel20C.setCellStyle(styleTotal);
                    HSSFCell cellCancel21C = rowscancl1.createCell(5);
                    cellCancel21C.setCellStyle(styleTotal);

                    //Total inv
                    HSSFRow rowtotal = sheetInc.createRow(tempcount + 3);
                    String totalpax = "SUM(C" + 9 + ":C" + (tempcount) + ")";
                    String totalticketissue = "SUM(E" + 9 + ":E" + (tempcount) + ")";
                    String totalcost = "SUM(G" + 9 + ":G" + (tempcount) + ")";
                    String totalinbound = "SUM(H" + 9 + ":H" + (tempcount) + ")";
                    String totalwendy = "SUM(I" + 9 + ":I" + (tempcount) + ")";
                    String totaloutbound = "SUM(J" + 9 + ":J" + (tempcount) + ")";
                    String totalrefund = "SUM(K" + 9 + ":K" + (tempcount) + ")";
                    String totalbusinesstrip = "SUM(L" + 9 + ":L" + (tempcount) + ")";
                    String totalannualleave = "SUM(M" + 9 + ":M" + (tempcount) + ")";
                    String totalnoinv = "SUM(N" + 9 + ":N" + (tempcount) + ")";
                    String totalcostinv = "SUM(O" + 9 + ":O" + (tempcount) + ")";
                    String totalinvwendy = "SUM(P" + 9 + ":P" + (tempcount) + ")";
                    String totalinvout = "SUM(Q" + 9 + ":Q" + (tempcount) + ")";
                    String totalinvrefund = "SUM(R" + 9 + ":R" + (tempcount) + ")";
                    String totalinvbusinesstrip = "SUM(S" + 9 + ":S" + (tempcount) + ")";
                    String totalinvannualleave = "SUM(T" + 9 + ":T" + (tempcount) + ")";
                    String totalinvnoinv = "SUM(U" + 9 + ":U" + (tempcount) + ")";

                    sheetInc.addMergedRegion(CellRangeAddress.valueOf("A" + (tempcount + 4) + ":B" + (tempcount + 4)));
                    //sheetInc.addMergedRegion(CellRangeAddress.valueOf("D"+(tempcount+1)+":F"+(tempcount+1)));
                    HSSFCell cellTotal00 = rowtotal.createCell(0);
                    cellTotal00.setCellValue("Total");
                    cellTotal00.setCellStyle(styleTotal);
                    HSSFCell cellTotal01 = rowtotal.createCell(2);
                    cellTotal01.setCellFormula(totalpax);
                    cellTotal01.setCellStyle(styleC26);
                    HSSFCell cellTotal02 = rowtotal.createCell(4);
                    cellTotal02.setCellFormula(totalticketissue);
                    cellTotal02.setCellStyle(styleC25);
                    HSSFCell cellTotal03 = rowtotal.createCell(6);
                    cellTotal03.setCellFormula(totalcost);
                    cellTotal03.setCellStyle(styleC25);
                    HSSFCell cellTotal04 = rowtotal.createCell(7);
                    cellTotal04.setCellFormula(totalinbound);
                    cellTotal04.setCellStyle(styleC25);
                    HSSFCell cellTotal05 = rowtotal.createCell(8);
                    cellTotal05.setCellFormula(totalwendy);
                    cellTotal05.setCellStyle(styleC25);
                    HSSFCell cellTotal06 = rowtotal.createCell(9);
                    cellTotal06.setCellFormula(totaloutbound);
                    cellTotal06.setCellStyle(styleC25);
                    HSSFCell cellTotal07 = rowtotal.createCell(10);
                    cellTotal07.setCellFormula(totalrefund);
                    cellTotal07.setCellStyle(styleC25);
                    HSSFCell cellTotal08 = rowtotal.createCell(11);
                    cellTotal08.setCellFormula(totalbusinesstrip);
                    cellTotal08.setCellStyle(styleC25);
                    HSSFCell cellTotal09 = rowtotal.createCell(12);
                    cellTotal09.setCellFormula(totalannualleave);
                    cellTotal09.setCellStyle(styleC25);
                    HSSFCell cellTotal10 = rowtotal.createCell(13);
                    cellTotal10.setCellFormula(totalnoinv);
                    cellTotal10.setCellStyle(styleC25);
                    HSSFCell cellTotal11 = rowtotal.createCell(14);
                    cellTotal11.setCellFormula(totalcostinv);
                    cellTotal11.setCellStyle(styleC25);
                    HSSFCell cellTotal12 = rowtotal.createCell(15);
                    cellTotal12.setCellFormula(totalinvwendy);
                    cellTotal12.setCellStyle(styleC25);
                    HSSFCell cellTotal13 = rowtotal.createCell(16);
                    cellTotal13.setCellFormula(totalinvout);
                    cellTotal13.setCellStyle(styleC25);
                    HSSFCell cellTotal14 = rowtotal.createCell(17);
                    cellTotal14.setCellFormula(totalinvrefund);
                    cellTotal14.setCellStyle(styleC25);
                    HSSFCell cellTotal15 = rowtotal.createCell(18);
                    cellTotal15.setCellFormula(totalinvbusinesstrip);
                    cellTotal15.setCellStyle(styleC25);
                    HSSFCell cellTotal16 = rowtotal.createCell(19);
                    cellTotal16.setCellFormula(totalinvannualleave);
                    cellTotal16.setCellStyle(styleC25);
                    HSSFCell cellTotal17 = rowtotal.createCell(20);
                    cellTotal17.setCellFormula(totalinvnoinv);
                    cellTotal17.setCellStyle(styleC25);
                    HSSFCell cellTotal18 = rowtotal.createCell(1);
                    cellTotal18.setCellStyle(styleTotal);
                    HSSFCell cellTotal19 = rowtotal.createCell(3);
                    cellTotal19.setCellStyle(styleTotal);
                    HSSFCell cellTotal20 = rowtotal.createCell(4);
                    cellTotal20.setCellStyle(styleTotal);
                    HSSFCell cellTotal21 = rowtotal.createCell(5);
                    cellTotal21.setCellStyle(styleTotal);
//                    if(tempcount != 0){
//                        rowdetail = tempcount+3;
//                    }					

                    HSSFRow row8 = sheetIncSum.createRow(7);
                    HSSFCell cell81 = row8.createCell(0);
                    cell81.setCellValue("Type Pay");
                    cell81.setCellStyle(styleC3);
                    HSSFCell cell82 = row8.createCell(1);
                    cell82.setCellValue("Type Route");
                    cell82.setCellStyle(styleC3);
                    HSSFCell cell83 = row8.createCell(2);
                    cell83.setCellValue("Pax");
                    cell83.setCellStyle(styleC3);
                    HSSFCell cell84 = row8.createCell(3);
                    cell84.setCellValue("Ticket Issue");
                    cell84.setCellStyle(styleC3);
                    HSSFCell cell85 = row8.createCell(4);
                    cell85.setCellValue("Inv. No");
                    cell85.setCellStyle(styleC3);
                    HSSFCell cell86 = row8.createCell(5);
                    cell86.setCellValue("Cost");
                    cell86.setCellStyle(styleC3);
                    HSSFCell cell87 = row8.createCell(6);
                    cell87.setCellValue("Inbound");
                    cell87.setCellStyle(styleC3);
                    HSSFCell cell88 = row8.createCell(7);
                    cell88.setCellValue("Wendy(N)");
                    cell88.setCellStyle(styleC3);
                    HSSFCell cell89 = row8.createCell(8);
                    cell89.setCellValue("Outbound");
                    cell89.setCellStyle(styleC3);
                    HSSFCell cell91 = row8.createCell(9);
                    cell91.setCellValue("Refund");
                    cell91.setCellStyle(styleC3);
                    HSSFCell cell92 = row8.createCell(10);
                    cell92.setCellValue("Business Trip");
                    cell92.setCellStyle(styleC3);
                    HSSFCell cell93 = row8.createCell(11);
                    cell93.setCellValue("Annual Leave");
                    cell93.setCellStyle(styleC3);
                    HSSFCell cell94 = row8.createCell(12);
                    cell94.setCellValue("No Inv");
                    cell94.setCellStyle(styleC3);
                    HSSFCell cell95 = row8.createCell(13);
                    cell95.setCellValue("Cost Inv");
                    cell95.setCellStyle(styleC3);
                    HSSFCell cell96 = row8.createCell(14);
                    cell96.setCellValue("Inv Wendy");
                    cell96.setCellStyle(styleC3);
                    HSSFCell cell97 = row8.createCell(15);
                    cell97.setCellValue("Inv Outbound");
                    cell97.setCellStyle(styleC3);
                    HSSFCell cell98 = row8.createCell(16);
                    cell98.setCellValue("Inv Refund");
                    cell98.setCellStyle(styleC3);
                    HSSFCell cell99 = row8.createCell(17);
                    cell99.setCellValue("Inv Business Trip");
                    cell99.setCellStyle(styleC3);
                    HSSFCell cell100 = row8.createCell(18);
                    cell100.setCellValue("Inv Annual Leave");
                    cell100.setCellStyle(styleC3);
                    HSSFCell cell101 = row8.createCell(19);
                    cell101.setCellValue("Inv No Inv");
                    cell101.setCellStyle(styleC3);

                    HSSFRow row = sheetIncSum.createRow(count + x);

                    costsumtypepaypast = (dataChk == null ? "" : String.valueOf(dataChk.getTypepaymentSum()));
                    costsumtypepaypresent = String.valueOf(data.getTypepaymentSum());
                    costsumtyperoutepast = (dataChk == null ? "" : String.valueOf(dataChk.getTyperountingSum()));
                    costsumtyperoutepresent = String.valueOf(data.getTyperountingSum());
                    costsumtypepaypastline = ((costsumtypepaypast.equalsIgnoreCase(costsumtypepaypresent)) && ((costsumtyperoutepast.equalsIgnoreCase(costsumtyperoutepresent))) ? costsumtypepaypastline : x);

                    HSSFCell celldata0 = row.createCell(0);
                    celldata0.setCellValue(String.valueOf(data.getTypepaymentSum()));
                    celldata0.setCellStyle(styleC29);

                    HSSFCell celldata1 = row.createCell(1);
                    celldata1.setCellValue(String.valueOf(data.getTyperountingSum()));
                    celldata1.setCellStyle(styleC30);

                    HSSFCell celldata2 = row.createCell(2);
                    celldata2.setCellValue("".equalsIgnoreCase(String.valueOf(data.getPaxSum())) ? 0 : (new BigDecimal(data.getPaxSum())).doubleValue());
                    celldata2.setCellStyle(styleC26);

                    HSSFCell celldata3 = row.createCell(3);
                    if ((costsumtypepaypast.equalsIgnoreCase(costsumtypepaypresent)) && ((costsumtyperoutepast.equalsIgnoreCase(costsumtyperoutepresent)))) {
                        celldata3.setCellValue("".equalsIgnoreCase(String.valueOf(data.getTicketissueSum())) ? 0 : (new BigDecimal(data.getTicketissueSum())).doubleValue());
                        sheetIncSum.addMergedRegion(CellRangeAddress.valueOf("D" + (count + costsumtypepaypastline + 1) + ":D" + (count + x + 1)));
                        celldata3.setCellStyle(styleC25);
                    } else {
                        celldata3.setCellValue("".equalsIgnoreCase(String.valueOf(data.getTicketissueSum())) ? 0 : (new BigDecimal(data.getTicketissueSum())).doubleValue());
                        celldata3.setCellStyle(styleC25);
                    }

                    HSSFCell celldata4 = row.createCell(4);
                    celldata4.setCellValue(String.valueOf(data.getInvnoSum()));
                    celldata4.setCellStyle(styleC29);

                    HSSFCell celldata5 = row.createCell(5);
                    celldata5.setCellValue("".equalsIgnoreCase(String.valueOf(data.getCostSum())) ? 0 : (new BigDecimal(data.getCostSum())).doubleValue());
                    celldata5.setCellStyle(styleC25);

                    HSSFCell celldata6 = row.createCell(6);
                    celldata6.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInboundSum())) ? 0 : (new BigDecimal(data.getInboundSum())).doubleValue());
                    celldata6.setCellStyle(styleC25);

                    HSSFCell celldata7 = row.createCell(7);
                    celldata7.setCellValue("".equalsIgnoreCase(String.valueOf(data.getWendySum())) ? 0 : (new BigDecimal(data.getWendySum())).doubleValue());
                    celldata7.setCellStyle(styleC25);

                    HSSFCell celldata8 = row.createCell(8);
                    celldata8.setCellValue("".equalsIgnoreCase(String.valueOf(data.getOutbound())) ? 0 : (new BigDecimal(data.getOutbound())).doubleValue());
                    celldata8.setCellStyle(styleC25);

                    HSSFCell celldata9 = row.createCell(9);
                    celldata9.setCellValue("".equalsIgnoreCase(String.valueOf(data.getRefundSum())) ? 0 : (new BigDecimal(data.getRefundSum())).doubleValue());
                    celldata9.setCellStyle(styleC25);

                    HSSFCell celldata10 = row.createCell(10);
                    celldata10.setCellValue("".equalsIgnoreCase(String.valueOf(data.getBusinesstripSum())) ? 0 : (new BigDecimal(data.getBusinesstripSum())).doubleValue());
                    celldata10.setCellStyle(styleC25);

                    HSSFCell celldata11 = row.createCell(11);
                    celldata11.setCellValue("".equalsIgnoreCase(String.valueOf(data.getAnnualleaveSum())) ? 0 : (new BigDecimal(data.getAnnualleaveSum())).doubleValue());
                    celldata11.setCellStyle(styleC25);

                    HSSFCell celldata12 = row.createCell(12);
                    celldata12.setCellValue("".equalsIgnoreCase(String.valueOf(data.getNoinvoiceSum())) ? 0 : (new BigDecimal(data.getNoinvoiceSum())).doubleValue());
                    celldata12.setCellStyle(styleC25);

                    HSSFCell celldata13 = row.createCell(13);
                    celldata13.setCellValue("".equalsIgnoreCase(String.valueOf(data.getCostinvSum())) ? 0 : (new BigDecimal(data.getCostinvSum())).doubleValue());
                    celldata13.setCellStyle(styleC25);

                    HSSFCell celldata14 = row.createCell(14);
                    celldata14.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInvwendySum())) ? 0 : (new BigDecimal(data.getInvwendySum())).doubleValue());
                    celldata14.setCellStyle(styleC25);

                    HSSFCell celldata15 = row.createCell(15);
                    celldata15.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInvoutboundSum())) ? 0 : (new BigDecimal(data.getInvoutboundSum())).doubleValue());
                    celldata15.setCellStyle(styleC25);

                    HSSFCell celldata16 = row.createCell(16);
                    celldata16.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInvrefundSum())) ? 0 : (new BigDecimal(data.getInvrefundSum())).doubleValue());
                    celldata16.setCellStyle(styleC25);

                    HSSFCell celldata17 = row.createCell(17);
                    celldata17.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInvbusinesstripSum())) ? 0 : (new BigDecimal(data.getInvbusinesstripSum())).doubleValue());
                    celldata17.setCellStyle(styleC25);

                    HSSFCell celldata18 = row.createCell(18);
                    celldata18.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInvannualleaveSum())) ? 0 : (new BigDecimal(data.getInvannualleaveSum())).doubleValue());
                    celldata18.setCellStyle(styleC25);

                    HSSFCell celldata19 = row.createCell(19);
                    celldata19.setCellValue("".equalsIgnoreCase(String.valueOf(data.getInvnoinvoiceSum())) ? 0 : (new BigDecimal(data.getInvnoinvoiceSum())).doubleValue());
                    celldata19.setCellStyle(styleC25);
//                    tempcount2 = count + i + 4;					

                    // set total last row
                    if (i == (sumCostIncome.size() - 1)) {
                        //Inter
                        HSSFRow rowsinter = sheetIncSum.createRow(count + x + 1);
                        sheetIncSum.addMergedRegion(CellRangeAddress.valueOf("A" + (count + x + 2) + ":B" + (count + x + 2)));
                        HSSFCell cellInter00 = rowsinter.createCell(0);
                        cellInter00.setCellValue("Total Inter");
                        cellInter00.setCellStyle(styleTotal);
                        HSSFCell cellInter01 = rowsinter.createCell(2);
                        cellInter01.setCellValue(interpax.doubleValue());
                        cellInter01.setCellStyle(styleC26);
                        HSSFCell cellInter02 = rowsinter.createCell(3);
                        cellInter02.setCellValue(interticket.doubleValue());
                        cellInter02.setCellStyle(styleC25);
                        HSSFCell cellInter03 = rowsinter.createCell(5);
                        cellInter03.setCellValue(intercost.doubleValue());
                        cellInter03.setCellStyle(styleC25);
                        HSSFCell cellInter04 = rowsinter.createCell(6);
                        cellInter04.setCellValue(interinbound.doubleValue());
                        cellInter04.setCellStyle(styleC25);
                        HSSFCell cellInter05 = rowsinter.createCell(7);
                        cellInter05.setCellValue(interwendy.doubleValue());
                        cellInter05.setCellStyle(styleC25);
                        HSSFCell cellInter06 = rowsinter.createCell(8);
                        cellInter06.setCellValue(interoutbound.doubleValue());
                        cellInter06.setCellStyle(styleC25);
                        HSSFCell cellInter07 = rowsinter.createCell(9);
                        cellInter07.setCellValue(interrefund.doubleValue());
                        cellInter07.setCellStyle(styleC25);
                        HSSFCell cellInter08 = rowsinter.createCell(10);
                        cellInter08.setCellValue(interbusinesstrip.doubleValue());
                        cellInter08.setCellStyle(styleC25);
                        HSSFCell cellInter09 = rowsinter.createCell(11);
                        cellInter09.setCellValue(interannualleave.doubleValue());
                        cellInter09.setCellStyle(styleC25);
                        HSSFCell cellInter10 = rowsinter.createCell(12);
                        cellInter10.setCellValue(internoinv.doubleValue());
                        cellInter10.setCellStyle(styleC25);
                        HSSFCell cellInter11 = rowsinter.createCell(13);
                        cellInter11.setCellValue(intercostinv.doubleValue());
                        cellInter11.setCellStyle(styleC25);
                        HSSFCell cellInter12 = rowsinter.createCell(14);
                        cellInter12.setCellValue(interinvwendy.doubleValue());
                        cellInter12.setCellStyle(styleC25);
                        HSSFCell cellInter13 = rowsinter.createCell(15);
                        cellInter13.setCellValue(interinvoutbound.doubleValue());
                        cellInter13.setCellStyle(styleC25);
                        HSSFCell cellInter14 = rowsinter.createCell(16);
                        cellInter14.setCellValue(interinvrefund.doubleValue());
                        cellInter14.setCellStyle(styleC25);
                        HSSFCell cellInter15 = rowsinter.createCell(17);
                        cellInter15.setCellValue(interinvbusinesstrip.doubleValue());
                        cellInter15.setCellStyle(styleC25);
                        HSSFCell cellInter16 = rowsinter.createCell(18);
                        cellInter16.setCellValue(interinvannualleave.doubleValue());
                        cellInter16.setCellStyle(styleC25);
                        HSSFCell cellInter17 = rowsinter.createCell(19);
                        cellInter17.setCellValue(interinvnoinv.doubleValue());
                        cellInter17.setCellStyle(styleC25);
                        HSSFCell cellInter18 = rowsinter.createCell(1);
                        cellInter18.setCellStyle(styleTotal);
                        HSSFCell cellInter19 = rowsinter.createCell(3);
                        cellInter19.setCellStyle(styleTotal);
                        HSSFCell cellInter20 = rowsinter.createCell(4);
                        cellInter20.setCellStyle(styleTotal);

                        //Domestic
                        HSSFRow rowsdomestic = sheetIncSum.createRow(count + x + 2);
                        sheetIncSum.addMergedRegion(CellRangeAddress.valueOf("A" + (count + x + 3) + ":B" + (count + x + 3)));
                        HSSFCell cellDomestic00 = rowsdomestic.createCell(0);
                        cellDomestic00.setCellValue("Total Domestic");
                        cellDomestic00.setCellStyle(styleTotal);
                        HSSFCell cellDomestic01 = rowsdomestic.createCell(2);
                        cellDomestic01.setCellValue(domesticpax.doubleValue());
                        cellDomestic01.setCellStyle(styleC26);
                        HSSFCell cellDomestic02 = rowsdomestic.createCell(3);
                        cellDomestic02.setCellValue(domesticticket.doubleValue());
                        cellDomestic02.setCellStyle(styleC25);
                        HSSFCell cellDomestic03 = rowsdomestic.createCell(5);
                        cellDomestic03.setCellValue(domesticcost.doubleValue());
                        cellDomestic03.setCellStyle(styleC25);
                        HSSFCell cellDomestic04 = rowsdomestic.createCell(6);
                        cellDomestic04.setCellValue(domesticinbound.doubleValue());
                        cellDomestic04.setCellStyle(styleC25);
                        HSSFCell cellDomestic05 = rowsdomestic.createCell(7);
                        cellDomestic05.setCellValue(domesticwendy.doubleValue());
                        cellDomestic05.setCellStyle(styleC25);
                        HSSFCell cellDomestic06 = rowsdomestic.createCell(8);
                        cellDomestic06.setCellValue(domesticoutbound.doubleValue());
                        cellDomestic06.setCellStyle(styleC25);
                        HSSFCell cellDomestic07 = rowsdomestic.createCell(9);
                        cellDomestic07.setCellValue(domesticrefund.doubleValue());
                        cellDomestic07.setCellStyle(styleC25);
                        HSSFCell cellDomestic08 = rowsdomestic.createCell(10);
                        cellDomestic08.setCellValue(domesticbusinesstrip.doubleValue());
                        cellDomestic08.setCellStyle(styleC25);
                        HSSFCell cellDomestic09 = rowsdomestic.createCell(11);
                        cellDomestic09.setCellValue(domesticannualleave.doubleValue());
                        cellDomestic09.setCellStyle(styleC25);
                        HSSFCell cellDomestic10 = rowsdomestic.createCell(12);
                        cellDomestic10.setCellValue(domesticnoinv.doubleValue());
                        cellDomestic10.setCellStyle(styleC25);
                        HSSFCell cellDomestic11 = rowsdomestic.createCell(13);
                        cellDomestic11.setCellValue(domesticcostinv.doubleValue());
                        cellDomestic11.setCellStyle(styleC25);
                        HSSFCell cellDomestic12 = rowsdomestic.createCell(14);
                        cellDomestic12.setCellValue(domesticinvwendy.doubleValue());
                        cellDomestic12.setCellStyle(styleC25);
                        HSSFCell cellDomestic13 = rowsdomestic.createCell(15);
                        cellDomestic13.setCellValue(domesticinvoutbound.doubleValue());
                        cellDomestic13.setCellStyle(styleC25);
                        HSSFCell cellDomestic14 = rowsdomestic.createCell(16);
                        cellDomestic14.setCellValue(domesticinvrefund.doubleValue());
                        cellDomestic14.setCellStyle(styleC25);
                        HSSFCell cellDomestic15 = rowsdomestic.createCell(17);
                        cellDomestic15.setCellValue(domesticinvbusinesstrip.doubleValue());
                        cellDomestic15.setCellStyle(styleC25);
                        HSSFCell cellDomestic16 = rowsdomestic.createCell(18);
                        cellDomestic16.setCellValue(domesticinvannualleave.doubleValue());
                        cellDomestic16.setCellStyle(styleC25);
                        HSSFCell cellDomestic17 = rowsdomestic.createCell(19);
                        cellDomestic17.setCellValue(domesticinvnoinv.doubleValue());
                        cellDomestic17.setCellStyle(styleC25);
                        HSSFCell cellDomestic18 = rowsdomestic.createCell(1);
                        cellDomestic18.setCellStyle(styleTotal);
                        HSSFCell cellDomestic19 = rowsdomestic.createCell(3);
                        cellDomestic19.setCellStyle(styleTotal);
                        HSSFCell cellDomestic20 = rowsdomestic.createCell(4);
                        cellDomestic20.setCellStyle(styleTotal);

                        //Cancel
                        HSSFRow rowscancl = sheetIncSum.createRow(count + x + 3);
                        sheetIncSum.addMergedRegion(CellRangeAddress.valueOf("A" + (count + x + 4) + ":B" + (count + x + 4)));
                        HSSFCell cellCancel00 = rowscancl.createCell(0);
                        cellCancel00.setCellValue("Total Cancel");
                        cellCancel00.setCellStyle(styleTotal);
                        HSSFCell cellCancel01 = rowscancl.createCell(2);
                        cellCancel01.setCellValue(cancelpax.doubleValue());
                        cellCancel01.setCellStyle(styleC26);
                        HSSFCell cellCancel02 = rowscancl.createCell(3);
                        cellCancel02.setCellValue(cancelticket.doubleValue());
                        cellCancel02.setCellStyle(styleC25);
                        HSSFCell cellCancel03 = rowscancl.createCell(5);
                        cellCancel03.setCellValue(cancelcost.doubleValue());
                        cellCancel03.setCellStyle(styleC25);
                        HSSFCell cellCancel04 = rowscancl.createCell(6);
                        cellCancel04.setCellValue(cancelinbound.doubleValue());
                        cellCancel04.setCellStyle(styleC25);
                        HSSFCell cellCancel05 = rowscancl.createCell(7);
                        cellCancel05.setCellValue(cancelwendy.doubleValue());
                        cellCancel05.setCellStyle(styleC25);
                        HSSFCell cellCancel06 = rowscancl.createCell(8);
                        cellCancel06.setCellValue(canceloutbound.doubleValue());
                        cellCancel06.setCellStyle(styleC25);
                        HSSFCell cellCancel07 = rowscancl.createCell(9);
                        cellCancel07.setCellValue(cancelrefund.doubleValue());
                        cellCancel07.setCellStyle(styleC25);
                        HSSFCell cellCancel08 = rowscancl.createCell(10);
                        cellCancel08.setCellValue(cancelbusinesstrip.doubleValue());
                        cellCancel08.setCellStyle(styleC25);
                        HSSFCell cellCancel09 = rowscancl.createCell(11);
                        cellCancel09.setCellValue(cancelannualleave.doubleValue());
                        cellCancel09.setCellStyle(styleC25);
                        HSSFCell cellCancel10 = rowscancl.createCell(12);
                        cellCancel10.setCellValue(cancelnoinv.doubleValue());
                        cellCancel10.setCellStyle(styleC25);
                        HSSFCell cellCancel11 = rowscancl.createCell(13);
                        cellCancel11.setCellValue(cancelcostinv.doubleValue());
                        cellCancel11.setCellStyle(styleC25);
                        HSSFCell cellCancel12 = rowscancl.createCell(14);
                        cellCancel12.setCellValue(cancelinvwendy.doubleValue());
                        cellCancel12.setCellStyle(styleC25);
                        HSSFCell cellCancel13 = rowscancl.createCell(15);
                        cellCancel13.setCellValue(cancelinvoutbound.doubleValue());
                        cellCancel13.setCellStyle(styleC25);
                        HSSFCell cellCancel14 = rowscancl.createCell(16);
                        cellCancel14.setCellValue(cancelinvrefund.doubleValue());
                        cellCancel14.setCellStyle(styleC25);
                        HSSFCell cellCancel15 = rowscancl.createCell(17);
                        cellCancel15.setCellValue(cancelinvbusinesstrip.doubleValue());
                        cellCancel15.setCellStyle(styleC25);
                        HSSFCell cellCancel16 = rowscancl.createCell(18);
                        cellCancel16.setCellValue(cancelinvannualleave.doubleValue());
                        cellCancel16.setCellStyle(styleC25);
                        HSSFCell cellCancel17 = rowscancl.createCell(19);
                        cellCancel17.setCellValue(cancelinvnoinv.doubleValue());
                        cellCancel17.setCellStyle(styleC25);
                        HSSFCell cellCancel18 = rowscancl.createCell(1);
                        cellCancel18.setCellStyle(styleTotal);
                        HSSFCell cellCancel19 = rowscancl.createCell(3);
                        cellCancel19.setCellStyle(styleTotal);
                        HSSFCell cellCancel20 = rowscancl.createCell(4);
                        cellCancel20.setCellStyle(styleTotal);

                        HSSFRow rows = sheetIncSum.createRow(count + x + 4);
                        //Total inv
                        String sumtotalpax = "SUM(C" + 9 + ":C" + (count + x + 1) + ")";
                        String sumtotalticketissue = "SUM(D" + 9 + ":D" + (count + x + 1) + ")";
                        String sumtotalcost = "SUM(F" + 9 + ":F" + (count + x + 1) + ")";
                        String sumtotalinbound = "SUM(G" + 9 + ":G" + (count + x + 1) + ")";
                        String sumtotalwendy = "SUM(H" + 9 + ":H" + (count + x + 1) + ")";
                        String sumtotaloutbound = "SUM(I" + 9 + ":I" + (count + x + 1) + ")";
                        String sumtotalrefund = "SUM(J" + 9 + ":J" + (count + x) + ")";
                        String sumtotalbusinesstrip = "SUM(K" + 9 + ":K" + (count + x + 1) + ")";
                        String sumtotalannualleave = "SUM(L" + 9 + ":L" + (count + x + 1) + ")";
                        String sumtotalnoinv = "SUM(M" + 9 + ":M" + (count + x + 1) + ")";
                        String sumtotalcostinv = "SUM(N" + 9 + ":N" + (count + x + 1) + ")";
                        String sumtotalinvwendy = "SUM(O" + 9 + ":O" + (count + x + 1) + ")";
                        String sumtotalinvout = "SUM(P" + 9 + ":P" + (count + x + 1) + ")";
                        String sumtotalinvrefund = "SUM(Q" + 9 + ":Q" + (count + x + 1) + ")";
                        String sumtotalinvbusinesstrip = "SUM(R" + 9 + ":R" + (count + x + 1) + ")";
                        String sumtotalinvannualleave = "SUM(S" + 9 + ":S" + (count + x + 1) + ")";
                        String sumtotalinvnoinv = "SUM(T" + 9 + ":T" + (count + x + 1) + ")";
                        sheetIncSum.addMergedRegion(CellRangeAddress.valueOf("A" + (count + x + 5) + ":B" + (count + x + 5)));
                        //sheetIncSum.addMergedRegion(CellRangeAddress.valueOf("D"+(count + x + 2)+":E"+(count + x + 2)));
                        HSSFCell cellTotalSum00 = rows.createCell(0);
                        cellTotalSum00.setCellValue("Total");
                        cellTotalSum00.setCellStyle(styleTotal);
                        HSSFCell cellTotalSum01 = rows.createCell(2);
                        cellTotalSum01.setCellFormula(sumtotalpax);
                        cellTotalSum01.setCellStyle(styleC26);
                        HSSFCell cellTotalSum02 = rows.createCell(3);
                        cellTotalSum02.setCellFormula(sumtotalticketissue);
                        cellTotalSum02.setCellStyle(styleC25);
                        HSSFCell cellTotalSum03 = rows.createCell(5);
                        cellTotalSum03.setCellFormula(sumtotalcost);
                        cellTotalSum03.setCellStyle(styleC25);
                        HSSFCell cellTotalSum04 = rows.createCell(6);
                        cellTotalSum04.setCellFormula(sumtotalinbound);
                        cellTotalSum04.setCellStyle(styleC25);
                        HSSFCell cellTotalSum05 = rows.createCell(7);
                        cellTotalSum05.setCellFormula(sumtotalwendy);
                        cellTotalSum05.setCellStyle(styleC25);
                        HSSFCell cellTotalSum06 = rows.createCell(8);
                        cellTotalSum06.setCellFormula(sumtotaloutbound);
                        cellTotalSum06.setCellStyle(styleC25);
                        HSSFCell cellTotalSum07 = rows.createCell(9);
                        cellTotalSum07.setCellFormula(sumtotalrefund);
                        cellTotalSum07.setCellStyle(styleC25);
                        HSSFCell cellTotalSum08 = rows.createCell(10);
                        cellTotalSum08.setCellFormula(sumtotalbusinesstrip);
                        cellTotalSum08.setCellStyle(styleC25);
                        HSSFCell cellTotalSum09 = rows.createCell(11);
                        cellTotalSum09.setCellFormula(sumtotalannualleave);
                        cellTotalSum09.setCellStyle(styleC25);
                        HSSFCell cellTotalSum10 = rows.createCell(12);
                        cellTotalSum10.setCellFormula(sumtotalnoinv);
                        cellTotalSum10.setCellStyle(styleC25);
                        HSSFCell cellTotalSum11 = rows.createCell(13);
                        cellTotalSum11.setCellFormula(sumtotalcostinv);
                        cellTotalSum11.setCellStyle(styleC25);
                        HSSFCell cellTotalSum12 = rows.createCell(14);
                        cellTotalSum12.setCellFormula(sumtotalinvwendy);
                        cellTotalSum12.setCellStyle(styleC25);
                        HSSFCell cellTotalSum13 = rows.createCell(15);
                        cellTotalSum13.setCellFormula(sumtotalinvout);
                        cellTotalSum13.setCellStyle(styleC25);
                        HSSFCell cellTotalSum14 = rows.createCell(16);
                        cellTotalSum14.setCellFormula(sumtotalinvrefund);
                        cellTotalSum14.setCellStyle(styleC25);
                        HSSFCell cellTotalSum15 = rows.createCell(17);
                        cellTotalSum15.setCellFormula(sumtotalinvbusinesstrip);
                        cellTotalSum15.setCellStyle(styleC25);
                        HSSFCell cellTotalSum16 = rows.createCell(18);
                        cellTotalSum16.setCellFormula(sumtotalinvannualleave);
                        cellTotalSum16.setCellStyle(styleC25);
                        HSSFCell cellTotalSum17 = rows.createCell(19);
                        cellTotalSum17.setCellFormula(sumtotalinvnoinv);
                        cellTotalSum17.setCellStyle(styleC25);
                        HSSFCell cellTotalSum18 = rows.createCell(1);
                        cellTotalSum18.setCellStyle(styleTotal);
                        HSSFCell cellTotalSum19 = rows.createCell(3);
                        cellTotalSum19.setCellStyle(styleTotal);
                        HSSFCell cellTotalSum20 = rows.createCell(4);
                        cellTotalSum20.setCellStyle(styleTotal);
//                        HSSFCell cellTotalSum19 = rows.createCell(5);
//                        cellTotalSum19.setCellStyle(styleTotal);
                    }
                    x++;
                }
                for (int j = 0; j < 30; j++) {
                    sheetInc.autoSizeColumn(j);
                    sheetIncSum.autoSizeColumn(j);
                }
            }
        }
    }

    private void getSummaryTicketAdjustCostAndIncome(HSSFWorkbook wb, List summaryTicketCostIncome) {
        List<ListSummaryTicketAdjustCostAndIncome> listTotal = summaryTicketCostIncome;

        String sheetName = "income";// name of sheet
        String sheetName1 = "income_summary";
        HSSFSheet sheet = wb.createSheet(sheetName);
        HSSFSheet sheet1 = wb.createSheet(sheetName1);
        HSSFDataFormat currency = wb.createDataFormat();
        UtilityExcelFunction excelFunction = new UtilityExcelFunction();

        //Style sum
        HSSFCellStyle styleSum = wb.createCellStyle();
        styleSum.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleSum.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleSum.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleSum.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleSum.setAlignment(styleSum.ALIGN_RIGHT);
        styleSum.setDataFormat(currency.getFormat("#,##0.00"));

        //Style total
        HSSFCellStyle styleTotal = wb.createCellStyle();
        styleTotal.setFont(excelFunction.getHeaderTable(wb.createFont()));
        styleTotal.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleTotal.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleTotal.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleTotal.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleTotal.setAlignment(styleTotal.ALIGN_RIGHT);

        //Style Pax
        HSSFCellStyle stylePax = wb.createCellStyle();
        stylePax.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        stylePax.setBorderRight(HSSFCellStyle.BORDER_THIN);
        stylePax.setBorderTop(HSSFCellStyle.BORDER_THIN);
        stylePax.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        stylePax.setDataFormat(currency.getFormat("#,##0"));
        stylePax.setAlignment(stylePax.ALIGN_CENTER);

        //Style Pax Table
        HSSFCellStyle stylePaxTable = wb.createCellStyle();
        stylePaxTable.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        stylePaxTable.setBorderRight(HSSFCellStyle.BORDER_THIN);
        stylePaxTable.setDataFormat(currency.getFormat("#,##0"));
        stylePaxTable.setAlignment(stylePaxTable.ALIGN_CENTER);

        // line table
        HSSFCellStyle styleBorderTop = wb.createCellStyle();// use
        styleBorderTop.setBorderTop(styleBorderTop.BORDER_THIN);
        HSSFCellStyle styleAlignRightBorderAllHeaderTable = wb.createCellStyle(); // use
        styleAlignRightBorderAllHeaderTable.setFont(excelFunction.getHeaderTable(wb.createFont()));
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
        HSSFCellStyle styleDetailTableCenter = wb.createCellStyle();
        styleDetailTableCenter.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleDetailTableCenter.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleDetailTableCenter.setAlignment(styleDetailTableCenter.ALIGN_CENTER);

        // Sheet Detail (2)
        // set Header Report (Row 1)
        HSSFCellStyle styleC11 = wb.createCellStyle();
        HSSFRow row01 = sheet.createRow(0);
        HSSFCell cell01 = row01.createCell(0);
        cell01.setCellValue("Summary Ticket Adjust Cost & Income");
        styleC11.setFont(excelFunction.getHeaderFont(wb.createFont()));
        cell01.setCellStyle(styleC11);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:H1"));

        // Set align Text
        HSSFCellStyle styleC21 = wb.createCellStyle();
        styleC21.setAlignment(styleC21.ALIGN_RIGHT);
        HSSFCellStyle styleC22 = wb.createCellStyle();
        styleC22.setAlignment(styleC22.ALIGN_LEFT);

        List<SummaryTicketAdjustCostAndIncome> incomeTemp = listTotal.get(0).getSummaryTicketAdjustAndIncome();
        SummaryTicketAdjustCostAndIncome income = new SummaryTicketAdjustCostAndIncome();
        if ((incomeTemp != null) && (incomeTemp.size() != 0)) {
            income = (SummaryTicketAdjustCostAndIncome) incomeTemp.get(0);
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
        
//        HSSFCell cell68 = row6.createCell(7);
//        cell68.setCellValue("Invoice Inbound");
//        cell68.setCellStyle(styleAlignRightBorderAllHeaderTable);
//        sheet.autoSizeColumn(7);
        
        HSSFCell cell69 = row6.createCell(7);
        cell69.setCellValue("Invoice Outbound");
        cell69.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(7);
        HSSFCell cell70 = row6.createCell(8);
        cell70.setCellValue("Over");
        cell70.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(8);
        HSSFCell cell71 = row6.createCell(9);
        cell71.setCellValue("Little");
        cell71.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(9);
        HSSFCell cell72 = row6.createCell(10);
        cell72.setCellValue("Discount");
        cell72.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(10);
        HSSFCell cell73 = row6.createCell(11);
        cell73.setCellValue("Cancel");
        cell73.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(11);
        HSSFCell cell74 = row6.createCell(12);
        cell74.setCellValue("Wait Pay");
        cell74.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(12);
        HSSFCell cell75 = row6.createCell(13);
        cell75.setCellValue("RC AG Com");
        cell75.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(13);
        HSSFCell cell76 = row6.createCell(14);
        cell76.setCellValue("Total Balance");
        cell76.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet.autoSizeColumn(14);

        List<SummaryTicketAdjustCostAndIncome> listSummaryTicketCostIncome = listTotal.get(0).getSummaryTicketAdjustAndIncome();

        int count = 9 + listSummaryTicketCostIncome.size();
        int start = 11;
        int end = 0;
        int num = 0;

        BigDecimal interpax = new BigDecimal(0);
        BigDecimal intercostinv = new BigDecimal(0);
        BigDecimal interinvoicewendy = new BigDecimal(0);
        BigDecimal interinvoiceinbound = new BigDecimal(0);
        BigDecimal interinvoiceoutbound = new BigDecimal(0);
        BigDecimal interover = new BigDecimal(0);
        BigDecimal interlittle = new BigDecimal(0);
        BigDecimal interdiscount = new BigDecimal(0);
        BigDecimal intercancel = new BigDecimal(0);
        BigDecimal interwaitpay = new BigDecimal(0);
        BigDecimal interrcagcom = new BigDecimal(0);
        BigDecimal intertotalbalance = new BigDecimal(0);

        BigDecimal domesticpax = new BigDecimal(0);
        BigDecimal domesticcostinv = new BigDecimal(0);
        BigDecimal domesticinvoicewendy = new BigDecimal(0);
        BigDecimal domesticinvoiceinbound = new BigDecimal(0);
        BigDecimal domesticinvoiceoutbound = new BigDecimal(0);
        BigDecimal domesticover = new BigDecimal(0);
        BigDecimal domesticlittle = new BigDecimal(0);
        BigDecimal domesticdiscount = new BigDecimal(0);
        BigDecimal domesticcancel = new BigDecimal(0);
        BigDecimal domesticwaitpay = new BigDecimal(0);
        BigDecimal domesticrcagcom = new BigDecimal(0);
        BigDecimal domestictotalbalance = new BigDecimal(0);

        BigDecimal cancelpax = new BigDecimal(0);
        BigDecimal cancelcostinv = new BigDecimal(0);
        BigDecimal cancelinvoicewendy = new BigDecimal(0);
        BigDecimal cancelinvoiceinbound = new BigDecimal(0);
        BigDecimal cancelinvoiceoutbound = new BigDecimal(0);
        BigDecimal cancelover = new BigDecimal(0);
        BigDecimal cancellittle = new BigDecimal(0);
        BigDecimal canceldiscount = new BigDecimal(0);
        BigDecimal cancelcancel = new BigDecimal(0);
        BigDecimal cancelwaitpay = new BigDecimal(0);
        BigDecimal cancelrcagcom = new BigDecimal(0);
        BigDecimal canceltotalbalance = new BigDecimal(0);

        for (int r = 9; r < count; r++) {
            if (num < listSummaryTicketCostIncome.size()) {

                if ("I".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getTyperounting())) {
                    BigDecimal interpaxtemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getPax()) ? new BigDecimal(listSummaryTicketCostIncome.get(num).getPax()) : new BigDecimal(0));
                    interpax = interpax.add(interpaxtemp);
                    BigDecimal intercostinvtemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getCostinv()) ? new BigDecimal(listSummaryTicketCostIncome.get(num).getCostinv()) : new BigDecimal(0));
                    intercostinv = intercostinv.add(intercostinvtemp);
                    BigDecimal interinvoicewendytemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getInvoicewendy()) ? new BigDecimal(listSummaryTicketCostIncome.get(num).getInvoicewendy()) : new BigDecimal(0));
                    interinvoicewendy = interinvoicewendy.add(interinvoicewendytemp);
                    BigDecimal interinvoiceinboundtemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getInvoiceinbound()) ? new BigDecimal(listSummaryTicketCostIncome.get(num).getInvoiceinbound()) : new BigDecimal(0));
                    interinvoiceinbound = interinvoiceinbound.add(interinvoiceinboundtemp);
                    BigDecimal interinvoiceoutboundtemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getInvoiceoutbound()) ? new BigDecimal(listSummaryTicketCostIncome.get(num).getInvoiceoutbound()) : new BigDecimal(0));
                    interinvoiceoutbound = interinvoiceoutbound.add(interinvoiceoutboundtemp);
                    BigDecimal interovertemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getOver()) ? new BigDecimal(listSummaryTicketCostIncome.get(num).getOver()) : new BigDecimal(0));
                    interover = interover.add(interovertemp);
                    BigDecimal interlittletemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getLitter()) ? new BigDecimal(listSummaryTicketCostIncome.get(num).getLitter()) : new BigDecimal(0));
                    interlittle = interlittle.add(interlittletemp);
                    BigDecimal interdiscounttemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getDiscount()) ? new BigDecimal(listSummaryTicketCostIncome.get(num).getDiscount()) : new BigDecimal(0));
                    interdiscount = interdiscount.add(interdiscounttemp);
                    BigDecimal intercanceltemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getCancel()) ? new BigDecimal(listSummaryTicketCostIncome.get(num).getCancel()) : new BigDecimal(0));
                    intercancel = intercancel.add(intercanceltemp);
                    BigDecimal interwaitpaytemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getWait_pay()) ? new BigDecimal(listSummaryTicketCostIncome.get(num).getWait_pay()) : new BigDecimal(0));
                    interwaitpay = interwaitpay.add(interwaitpaytemp);
                    BigDecimal interrcagcomtemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getRcagcom()) ? new BigDecimal(listSummaryTicketCostIncome.get(num).getRcagcom()) : new BigDecimal(0));
                    interrcagcom = interrcagcom.add(interrcagcomtemp);
                    BigDecimal intertotalbalancetemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getTotal_balance()) ? new BigDecimal(listSummaryTicketCostIncome.get(num).getTotal_balance()) : new BigDecimal(0));
                    intertotalbalance = intertotalbalance.add(intertotalbalancetemp);

                } else if ("D".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getTyperounting())) {
                    BigDecimal domesticpaxtemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getPax()) ? new BigDecimal(listSummaryTicketCostIncome.get(num).getPax()) : new BigDecimal(0));
                    domesticpax = domesticpax.add(domesticpaxtemp);
                    BigDecimal domesticcostinvtemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getCostinv()) ? new BigDecimal(listSummaryTicketCostIncome.get(num).getCostinv()) : new BigDecimal(0));
                    domesticcostinv = domesticcostinv.add(domesticcostinvtemp);
                    BigDecimal domesticinvoicewendytemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getInvoicewendy()) ? new BigDecimal(listSummaryTicketCostIncome.get(num).getInvoicewendy()) : new BigDecimal(0));
                    domesticinvoicewendy = domesticinvoicewendy.add(domesticinvoicewendytemp);
                    BigDecimal domesticinvoiceinboundtemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getInvoiceinbound()) ? new BigDecimal(listSummaryTicketCostIncome.get(num).getInvoiceinbound()) : new BigDecimal(0));
                    domesticinvoiceinbound = domesticinvoiceinbound.add(domesticinvoiceinboundtemp);
                    BigDecimal domesticinvoiceoutboundtemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getInvoiceoutbound()) ? new BigDecimal(listSummaryTicketCostIncome.get(num).getInvoiceoutbound()) : new BigDecimal(0));
                    domesticinvoiceoutbound = domesticinvoiceoutbound.add(domesticinvoiceoutboundtemp);
                    BigDecimal domesticovertemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getOver()) ? new BigDecimal(listSummaryTicketCostIncome.get(num).getOver()) : new BigDecimal(0));
                    domesticover = domesticover.add(domesticovertemp);
                    BigDecimal domesticlittletemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getLitter()) ? new BigDecimal(listSummaryTicketCostIncome.get(num).getLitter()) : new BigDecimal(0));
                    domesticlittle = domesticlittle.add(domesticlittletemp);
                    BigDecimal domesticdiscounttemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getDiscount()) ? new BigDecimal(listSummaryTicketCostIncome.get(num).getDiscount()) : new BigDecimal(0));
                    domesticdiscount = domesticdiscount.add(domesticdiscounttemp);
                    BigDecimal domesticcanceltemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getCancel()) ? new BigDecimal(listSummaryTicketCostIncome.get(num).getCancel()) : new BigDecimal(0));
                    domesticcancel = domesticcancel.add(domesticcanceltemp);
                    BigDecimal domesticwaitpaytemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getWait_pay()) ? new BigDecimal(listSummaryTicketCostIncome.get(num).getWait_pay()) : new BigDecimal(0));
                    domesticwaitpay = domesticwaitpay.add(domesticwaitpaytemp);
                    BigDecimal domesticrcagcomtemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getRcagcom()) ? new BigDecimal(listSummaryTicketCostIncome.get(num).getRcagcom()) : new BigDecimal(0));
                    domesticrcagcom = domesticrcagcom.add(domesticrcagcomtemp);
                    BigDecimal domestictotalbalancetemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getTotal_balance()) ? new BigDecimal(listSummaryTicketCostIncome.get(num).getTotal_balance()) : new BigDecimal(0));
                    domestictotalbalance = domestictotalbalance.add(domestictotalbalancetemp);

                } else if ("C".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getTyperounting())) {
                    BigDecimal cancelpaxtemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getPax()) ? new BigDecimal(listSummaryTicketCostIncome.get(num).getPax()) : new BigDecimal(0));
                    cancelpax = cancelpax.add(cancelpaxtemp);
                    BigDecimal cancelcostinvtemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getCostinv()) ? new BigDecimal(listSummaryTicketCostIncome.get(num).getCostinv()) : new BigDecimal(0));
                    cancelcostinv = cancelcostinv.add(cancelcostinvtemp);
                    BigDecimal cancelinvoicewendytemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getInvoicewendy()) ? new BigDecimal(listSummaryTicketCostIncome.get(num).getInvoicewendy()) : new BigDecimal(0));
                    cancelinvoicewendy = cancelinvoicewendy.add(cancelinvoicewendytemp);
                    BigDecimal cancelinvoiceinboundtemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getInvoiceinbound()) ? new BigDecimal(listSummaryTicketCostIncome.get(num).getInvoiceinbound()) : new BigDecimal(0));
                    cancelinvoiceinbound = cancelinvoiceinbound.add(cancelinvoiceinboundtemp);
                    BigDecimal cancelinvoiceoutboundtemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getInvoiceoutbound()) ? new BigDecimal(listSummaryTicketCostIncome.get(num).getInvoiceoutbound()) : new BigDecimal(0));
                    cancelinvoiceoutbound = cancelinvoiceoutbound.add(cancelinvoiceoutboundtemp);
                    BigDecimal cancelovertemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getOver()) ? new BigDecimal(listSummaryTicketCostIncome.get(num).getOver()) : new BigDecimal(0));
                    cancelover = cancelover.add(cancelovertemp);
                    BigDecimal cancellittletemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getLitter()) ? new BigDecimal(listSummaryTicketCostIncome.get(num).getLitter()) : new BigDecimal(0));
                    cancellittle = cancellittle.add(cancellittletemp);
                    BigDecimal canceldiscounttemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getDiscount()) ? new BigDecimal(listSummaryTicketCostIncome.get(num).getDiscount()) : new BigDecimal(0));
                    canceldiscount = canceldiscount.add(canceldiscounttemp);
                    BigDecimal cancelcanceltemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getCancel()) ? new BigDecimal(listSummaryTicketCostIncome.get(num).getCancel()) : new BigDecimal(0));
                    cancelcancel = cancelcancel.add(cancelcanceltemp);
                    BigDecimal cancelwaitpaytemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getWait_pay()) ? new BigDecimal(listSummaryTicketCostIncome.get(num).getWait_pay()) : new BigDecimal(0));
                    cancelwaitpay = cancelwaitpay.add(cancelwaitpaytemp);
                    BigDecimal cancelrcagcomtemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getRcagcom()) ? new BigDecimal(listSummaryTicketCostIncome.get(num).getRcagcom()) : new BigDecimal(0));
                    cancelrcagcom = cancelrcagcom.add(cancelrcagcomtemp);
                    BigDecimal canceltotalbalancetemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncome.get(num).getTotal_balance()) ? new BigDecimal(listSummaryTicketCostIncome.get(num).getTotal_balance()) : new BigDecimal(0));
                    canceltotalbalance = canceltotalbalance.add(canceltotalbalancetemp);

                }

                HSSFRow row = sheet.createRow(r);
                HSSFCell cell1 = row.createCell(0);
                cell1.setCellValue(listSummaryTicketCostIncome.get(num).getTypepayment());
                cell1.setCellStyle(styleDetailTable);
                HSSFCell cell2 = row.createCell(1);
                cell2.setCellValue(listSummaryTicketCostIncome.get(num).getTyperounting());
                cell2.setCellStyle(styleDetailTableCenter);
                HSSFCell cell3 = row.createCell(2);
                BigDecimal pax = new BigDecimal(listSummaryTicketCostIncome.get(num).getPax());
                cell3.setCellValue((pax != null) ? pax.doubleValue() : new BigDecimal("0").doubleValue());
                cell3.setCellStyle(stylePaxTable);
                HSSFCell cell4 = row.createCell(3);
                cell4.setCellValue(listSummaryTicketCostIncome.get(num).getAir());
                cell4.setCellStyle(styleDetailTable);
                HSSFCell cell55 = row.createCell(4);
                cell55.setCellValue(listSummaryTicketCostIncome.get(num).getInvno());
                cell55.setCellStyle(styleDetailTable);
                HSSFCell cell5 = row.createCell(5);
//                System.out.println("Cost : " + listSummaryTicketCostIncome.get(num).getCostinv());
                BigDecimal costinv = null;
                if ("".equals(listSummaryTicketCostIncome.get(num).getCostinv())) {
                    costinv = new BigDecimal(0);
                } else {
                    costinv = new BigDecimal(listSummaryTicketCostIncome.get(num).getCostinv());
                }
                cell5.setCellValue((costinv != null && !"0".equals(costinv)) ? costinv.doubleValue() : new BigDecimal("0").doubleValue());
                cell5.setCellStyle(styleDetailTableNumber);
                HSSFCell cell6 = row.createCell(6);
                BigDecimal invwendy = null;
                if ("".equals(listSummaryTicketCostIncome.get(num).getInvoicewendy())) {
                    invwendy = new BigDecimal(0);
                } else {
                    invwendy = new BigDecimal(listSummaryTicketCostIncome.get(num).getInvoicewendy());
                }
                cell6.setCellValue((invwendy != null) ? invwendy.doubleValue() : new BigDecimal("0").doubleValue());
                cell6.setCellStyle(styleDetailTableNumber);
                
//                HSSFCell cell07 = row.createCell(7);
//                BigDecimal invinbound = null;
//                if ("".equals(listSummaryTicketCostIncome.get(num).getInvoiceinbound())) {
//                    invinbound = new BigDecimal(0);
//                } else {
//                    invinbound = new BigDecimal(listSummaryTicketCostIncome.get(num).getInvoiceinbound());
//                }
//                cell07.setCellValue((invinbound != null) ? invinbound.doubleValue() : new BigDecimal("0").doubleValue());
//                cell07.setCellStyle(styleDetailTableNumber);
                
                HSSFCell cell7 = row.createCell(7);
                BigDecimal invoutbound = null;
                if ("".equals(listSummaryTicketCostIncome.get(num).getInvoiceoutbound())) {
                    invoutbound = new BigDecimal(0);
                } else {
                    invoutbound = new BigDecimal(listSummaryTicketCostIncome.get(num).getInvoiceoutbound());
                }
                cell7.setCellValue((invoutbound != null) ? invoutbound.doubleValue() : new BigDecimal("0").doubleValue());
                cell7.setCellStyle(styleDetailTableNumber);
                
                
                HSSFCell cell8 = row.createCell(8);
                cell8.setCellValue(listSummaryTicketCostIncome.get(num).getOver() != null ? (new BigDecimal(listSummaryTicketCostIncome.get(num).getOver())).doubleValue() : new BigDecimal("0").doubleValue());
                cell8.setCellStyle(styleDetailTableNumber);
                HSSFCell cell9 = row.createCell(9);
                BigDecimal little = null;
                if ("".equals(listSummaryTicketCostIncome.get(num).getLitter())) {
                    little = new BigDecimal(0);
                } else {
                    little = new BigDecimal(listSummaryTicketCostIncome.get(num).getLitter());
                }
                cell9.setCellValue((little != null) ? little.doubleValue() : new BigDecimal("0").doubleValue());
                cell9.setCellStyle(styleDetailTableNumber);
                sheet.autoSizeColumn(9);
                HSSFCell cell10 = row.createCell(10);
                BigDecimal discount = null;
                if ("".equals(listSummaryTicketCostIncome.get(num).getDiscount())) {
                    discount = new BigDecimal(0);
                } else {
                    discount = new BigDecimal(listSummaryTicketCostIncome.get(num).getDiscount());
                }
                cell10.setCellValue((discount != null) ? discount.doubleValue() : new BigDecimal("0").doubleValue());
                cell10.setCellStyle(styleDetailTableNumber);
                HSSFCell cell11 = row.createCell(11);
                BigDecimal cancel = null;
                if ("".equals(listSummaryTicketCostIncome.get(num).getCancel())) {
                    cancel = new BigDecimal(0);
                } else {
                    cancel = new BigDecimal(listSummaryTicketCostIncome.get(num).getCancel());
                }
                cell11.setCellValue((cancel != null) ? cancel.doubleValue() : new BigDecimal("0").doubleValue());
                cell11.setCellStyle(styleDetailTableNumber);
                HSSFCell cell12 = row.createCell(12);
                BigDecimal wait = null;
                if ("".equals(listSummaryTicketCostIncome.get(num).getWait_pay())) {
                    wait = new BigDecimal(0);
                } else {
                    wait = new BigDecimal(listSummaryTicketCostIncome.get(num).getWait_pay());
                }
                cell12.setCellValue((wait != null) ? wait.doubleValue() : new BigDecimal("0").doubleValue());
                cell12.setCellStyle(styleDetailTableNumber);
                HSSFCell cell13 = row.createCell(13);
                BigDecimal rc = null;
                if ("".equals(listSummaryTicketCostIncome.get(num).getRcagcom())) {
                    rc = new BigDecimal(0);
                } else {
                    rc = new BigDecimal(listSummaryTicketCostIncome.get(num).getRcagcom());
                }
                cell13.setCellValue((rc != null) ? rc.doubleValue() : new BigDecimal("0").doubleValue());
                cell13.setCellStyle(styleDetailTableNumber);
                HSSFCell cell14 = row.createCell(14);
                BigDecimal balance = null;
                if ("".equals(listSummaryTicketCostIncome.get(num).getTotal_balance())) {
                    balance = new BigDecimal(0);
                } else {
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
//        System.out.println(count);

        //Inter
        HSSFRow rowsinter1 = sheet.createRow(count);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A" + (count + 1) + ":B" + (count + 1)));
        HSSFCell cellInter00C = rowsinter1.createCell(0);
        cellInter00C.setCellValue("Total Inter");
        cellInter00C.setCellStyle(styleTotal);
        HSSFCell cellInter01C = rowsinter1.createCell(2);
        cellInter01C.setCellValue(interpax.doubleValue());
        cellInter01C.setCellStyle(stylePax);
        HSSFCell cellInter02C = rowsinter1.createCell(5);
        cellInter02C.setCellValue(intercostinv.doubleValue());
        cellInter02C.setCellStyle(styleSum);
        HSSFCell cellInter03C = rowsinter1.createCell(6);
        cellInter03C.setCellValue(interinvoicewendy.doubleValue());
        cellInter03C.setCellStyle(styleSum);
//        HSSFCell cellInter04C = rowsinter1.createCell(7);
//        cellInter04C.setCellValue(interinvoiceinbound.doubleValue());
//        cellInter04C.setCellStyle(styleSum);
        HSSFCell cellInter05C = rowsinter1.createCell(7);
        cellInter05C.setCellValue(interinvoiceoutbound.doubleValue());
        cellInter05C.setCellStyle(styleSum);
        HSSFCell cellInter005C = rowsinter1.createCell(8);
        cellInter005C.setCellValue(interover.doubleValue());
        cellInter005C.setCellStyle(styleSum);
        HSSFCell cellInter06C = rowsinter1.createCell(9);
        cellInter06C.setCellValue(interlittle.doubleValue());
        cellInter06C.setCellStyle(styleSum);
        HSSFCell cellInter07C = rowsinter1.createCell(10);
        cellInter07C.setCellValue(interdiscount.doubleValue());
        cellInter07C.setCellStyle(styleSum);
        HSSFCell cellInter08C = rowsinter1.createCell(11);
        cellInter08C.setCellValue(intercancel.doubleValue());
        cellInter08C.setCellStyle(styleSum);
        HSSFCell cellInter09C = rowsinter1.createCell(12);
        cellInter09C.setCellValue(interwaitpay.doubleValue());
        cellInter09C.setCellStyle(styleSum);
        HSSFCell cellInter10C = rowsinter1.createCell(13);
        cellInter10C.setCellValue(interrcagcom.doubleValue());
        cellInter10C.setCellStyle(styleSum);
        HSSFCell cellInter11C = rowsinter1.createCell(14);
        cellInter11C.setCellValue(intertotalbalance.doubleValue());
        cellInter11C.setCellStyle(styleSum);
        HSSFCell cellInter12C = rowsinter1.createCell(1);
        cellInter12C.setCellStyle(styleTotal);
        HSSFCell cellInter13C = rowsinter1.createCell(3);
        cellInter13C.setCellStyle(styleTotal);
        HSSFCell cellInter14C = rowsinter1.createCell(4);
        cellInter14C.setCellStyle(styleTotal);

        //Domestic
        HSSFRow rowsdomestic1 = sheet.createRow(count + 1);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A" + (count + 2) + ":B" + (count + 2)));
        HSSFCell cellDomestic00C = rowsdomestic1.createCell(0);
        cellDomestic00C.setCellValue("Total Domestic");
        cellDomestic00C.setCellStyle(styleTotal);
        HSSFCell cellDomestic01C = rowsdomestic1.createCell(2);
        cellDomestic01C.setCellValue(domesticpax.doubleValue());
        cellDomestic01C.setCellStyle(stylePax);
        HSSFCell cellDomestic02C = rowsdomestic1.createCell(5);
        cellDomestic02C.setCellValue(domesticcostinv.doubleValue());
        cellDomestic02C.setCellStyle(styleSum);
        HSSFCell cellDomestic03C = rowsdomestic1.createCell(6);
        cellDomestic03C.setCellValue(domesticinvoicewendy.doubleValue());
        cellDomestic03C.setCellStyle(styleSum);
//        HSSFCell cellDomestic04C = rowsdomestic1.createCell(7);
//        cellDomestic04C.setCellValue(domesticinvoiceinbound.doubleValue());
//        cellDomestic04C.setCellStyle(styleSum);
        HSSFCell cellDomestic05C = rowsdomestic1.createCell(7);
        cellDomestic05C.setCellValue(domesticinvoiceoutbound.doubleValue());
        cellDomestic05C.setCellStyle(styleSum);
        HSSFCell cellDomestic005C = rowsdomestic1.createCell(8);
        cellDomestic005C.setCellValue(domesticover.doubleValue());
        cellDomestic005C.setCellStyle(styleSum);
        HSSFCell cellDomestic06C = rowsdomestic1.createCell(9);
        cellDomestic06C.setCellValue(domesticlittle.doubleValue());
        cellDomestic06C.setCellStyle(styleSum);
        HSSFCell cellDomestic07C = rowsdomestic1.createCell(10);
        cellDomestic07C.setCellValue(domesticdiscount.doubleValue());
        cellDomestic07C.setCellStyle(styleSum);
        HSSFCell cellDomestic08C = rowsdomestic1.createCell(11);
        cellDomestic08C.setCellValue(domesticcancel.doubleValue());
        cellDomestic08C.setCellStyle(styleSum);
        HSSFCell cellDomestic09C = rowsdomestic1.createCell(12);
        cellDomestic09C.setCellValue(domesticwaitpay.doubleValue());
        cellDomestic09C.setCellStyle(styleSum);
        HSSFCell cellDomestic10C = rowsdomestic1.createCell(13);
        cellDomestic10C.setCellValue(domesticrcagcom.doubleValue());
        cellDomestic10C.setCellStyle(styleSum);
        HSSFCell cellDomestic11C = rowsdomestic1.createCell(14);
        cellDomestic11C.setCellValue(domestictotalbalance.doubleValue());
        cellDomestic11C.setCellStyle(styleSum);
        HSSFCell cellDomestic12C = rowsdomestic1.createCell(1);
        cellDomestic12C.setCellStyle(styleTotal);
        HSSFCell cellDomestic13C = rowsdomestic1.createCell(3);
        cellDomestic13C.setCellStyle(styleTotal);
        HSSFCell cellDomestic14C = rowsdomestic1.createCell(4);
        cellDomestic14C.setCellStyle(styleTotal);

        //Cancel
        HSSFRow rowscancel1 = sheet.createRow(count + 2);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A" + (count + 3) + ":B" + (count + 3)));
        HSSFCell cellCancel00C = rowscancel1.createCell(0);
        cellCancel00C.setCellValue("Total Cancel");
        cellCancel00C.setCellStyle(styleTotal);
        HSSFCell cellCancel01C = rowscancel1.createCell(2);
        cellCancel01C.setCellValue(cancelpax.doubleValue());
        cellCancel01C.setCellStyle(stylePax);
        HSSFCell cellCancel02C = rowscancel1.createCell(5);
        cellCancel02C.setCellValue(cancelcostinv.doubleValue());
        cellCancel02C.setCellStyle(styleSum);
        HSSFCell cellCancel03C = rowscancel1.createCell(6);
        cellCancel03C.setCellValue(cancelinvoicewendy.doubleValue());
        cellCancel03C.setCellStyle(styleSum);
//        HSSFCell cellCancel04C = rowscancel1.createCell(7);
//        cellCancel04C.setCellValue(cancelinvoiceinbound.doubleValue());
//        cellCancel04C.setCellStyle(styleSum);
        HSSFCell cellCancel05C = rowscancel1.createCell(7);
        cellCancel05C.setCellValue(cancelinvoiceoutbound.doubleValue());
        cellCancel05C.setCellStyle(styleSum);
        HSSFCell cellCancel006C = rowscancel1.createCell(8);
        cellCancel006C.setCellValue(cancelover.doubleValue());
        cellCancel006C.setCellStyle(styleSum);
        HSSFCell cellCancel06C = rowscancel1.createCell(9);
        cellCancel06C.setCellValue(cancellittle.doubleValue());
        cellCancel06C.setCellStyle(styleSum);
        HSSFCell cellCancel07C = rowscancel1.createCell(10);
        cellCancel07C.setCellValue(canceldiscount.doubleValue());
        cellCancel07C.setCellStyle(styleSum);
        HSSFCell cellCancel08C = rowscancel1.createCell(11);
        cellCancel08C.setCellValue(cancelcancel.doubleValue());
        cellCancel08C.setCellStyle(styleSum);
        HSSFCell cellCancel09C = rowscancel1.createCell(12);
        cellCancel09C.setCellValue(cancelwaitpay.doubleValue());
        cellCancel09C.setCellStyle(styleSum);
        HSSFCell cellCancel10C = rowscancel1.createCell(13);
        cellCancel10C.setCellValue(cancelrcagcom.doubleValue());
        cellCancel10C.setCellStyle(styleSum);
        HSSFCell cellCancel11C = rowscancel1.createCell(14);
        cellCancel11C.setCellValue(canceltotalbalance.doubleValue());
        cellCancel11C.setCellStyle(styleSum);
        HSSFCell cellCancel12C = rowscancel1.createCell(1);
        cellCancel12C.setCellStyle(styleTotal);
        HSSFCell cellCancel13C = rowscancel1.createCell(3);
        cellCancel13C.setCellStyle(styleTotal);
        HSSFCell cellCancel14C = rowscancel1.createCell(4);
        cellCancel14C.setCellStyle(styleTotal);

        String sumPax = "SUM(C" + 10 + ":C" + (count) + ")";
        String sumCosInv = "SUM(F" + 10 + ":F" + (count) + ")";
        String sumInvWendy = "SUM(G" + 10 + ":G" + (count) + ")";
//        String sumInvInbound = "SUM(H" + 10 + ":H" + (count) + ")";
        String sumInvOutbound = "SUM(H" + 10 + ":H" + (count) + ")";
        String sumOver = "SUM(I" + 10 + ":I" + (count) + ")";
        String sumLittle = "SUM(J" + 10 + ":J" + (count) + ")";
        String sumDiscount = "SUM(K" + 10 + ":K" + (count) + ")";
        String sumCancel = "SUM(L" + 10 + ":L" + (count) + ")";
        String sumRC = "SUM(N" + 10 + ":N" + (count) + ")";
        String sumWait = "SUM(M" + 10 + ":M" + (count) + ")";
        String sumBlance = "SUM(O" + 10 + ":O" + (count) + ")";
        
        HSSFRow row = sheet.createRow(count + 3);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A" + (count + 4) + ":B" + (count + 4)));
        HSSFCell cell60Sum = row.createCell(0);
        cell60Sum.setCellValue("Total");
        cell60Sum.setCellStyle(styleTotal);
        sheet.autoSizeColumn(0);
        HSSFCell cell6Sum = row.createCell(1);
        cell6Sum.setCellValue("");
        cell6Sum.setCellStyle(styleDetailTableNumber);
        sheet.autoSizeColumn(1);
        HSSFCell cell7Sum = row.createCell(2);
        cell7Sum.setCellFormula(sumPax);
        sheet.autoSizeColumn(2);
        cell7Sum.setCellStyle(stylePax);
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
        cell11Sum.setCellFormula(sumInvWendy);
        cell11Sum.setCellStyle(styleDetailTableNumber);
        sheet.autoSizeColumn(6);
//        HSSFCell cell12Sum = row.createCell(7);
//        cell12Sum.setCellFormula(sumInvInbound);
//        cell12Sum.setCellStyle(styleDetailTableNumber);
//        sheet.autoSizeColumn(7);
        HSSFCell cell13Sum = row.createCell(7);
        cell13Sum.setCellFormula(sumInvOutbound);
        cell13Sum.setCellStyle(styleDetailTableNumber);
        sheet.autoSizeColumn(7);
        HSSFCell cell14Sum = row.createCell(8);
        cell14Sum.setCellFormula(sumOver);
        cell14Sum.setCellStyle(styleDetailTableNumber);
        sheet.autoSizeColumn(8);
        HSSFCell cell15Sum = row.createCell(9);
        cell15Sum.setCellFormula(sumLittle);
        cell15Sum.setCellStyle(styleDetailTableNumber);
        sheet.autoSizeColumn(9);
        HSSFCell cell16Sum = row.createCell(10);
        cell16Sum.setCellFormula(sumDiscount);
        cell16Sum.setCellStyle(styleDetailTableNumber);
        sheet.autoSizeColumn(10);
        HSSFCell cell17Sum = row.createCell(11);
        cell17Sum.setCellFormula(sumCancel);
        cell17Sum.setCellStyle(styleDetailTableNumber);
        sheet.autoSizeColumn(11);
        HSSFCell cell18Sum = row.createCell(12);
        cell18Sum.setCellFormula(sumWait);
        cell18Sum.setCellStyle(styleDetailTableNumber);
        sheet.autoSizeColumn(12);
        HSSFCell cell19Sum = row.createCell(13);
        cell19Sum.setCellFormula(sumRC);
        cell19Sum.setCellStyle(styleDetailTableNumber);
        sheet.autoSizeColumn(13);
        HSSFCell cell019Sum = row.createCell(14);
        cell019Sum.setCellFormula(sumBlance);
        cell019Sum.setCellStyle(styleDetailTableNumber);
        sheet.autoSizeColumn(14);

        HSSFRow rowL1 = sheet.createRow(count + 4);
        rowL1.createCell(0).setCellStyle(styleBorderTop);
        rowL1.createCell(1).setCellStyle(styleBorderTop);
        rowL1.createCell(2).setCellStyle(styleBorderTop);
        rowL1.createCell(3).setCellStyle(styleBorderTop);
        rowL1.createCell(4).setCellStyle(styleBorderTop);
        rowL1.createCell(5).setCellStyle(styleBorderTop);
        rowL1.createCell(6).setCellStyle(styleBorderTop);
        rowL1.createCell(7).setCellStyle(styleBorderTop);
        rowL1.createCell(8).setCellStyle(styleBorderTop);
        rowL1.createCell(9).setCellStyle(styleBorderTop);
        rowL1.createCell(10).setCellStyle(styleBorderTop);
        rowL1.createCell(11).setCellStyle(styleBorderTop);
        rowL1.createCell(12).setCellStyle(styleBorderTop);
        rowL1.createCell(13).setCellStyle(styleBorderTop);
        rowL1.createCell(14).setCellStyle(styleBorderTop);
//        rowL1.createCell(15).setCellStyle(styleBorderTop);
        // Sheet
        // set Header Report (Row 1)
        HSSFCellStyle styleC110Sum = wb.createCellStyle();
        HSSFRow row010 = sheet1.createRow(0);
        HSSFCell cell010 = row010.createCell(0);
        cell010.setCellValue("Summary Ticket Adjust Cost & Income");
        styleC110Sum.setFont(excelFunction.getHeaderFont(wb.createFont()));
        cell010.setCellStyle(styleC110Sum);
        sheet1.addMergedRegion(CellRangeAddress.valueOf("A1:H1"));

        // Set align Text
        HSSFCellStyle styleC212 = wb.createCellStyle();
        styleC212.setAlignment(styleC21.ALIGN_RIGHT);
        HSSFCellStyle styleC222 = wb.createCellStyle();
        styleC222.setAlignment(styleC22.ALIGN_LEFT);

        List<SummaryTicketAdjustCostAndIncome> incomeSumTemp = listTotal.get(0).getSummaryTicketAdjustAndIncomeSum();
        SummaryTicketAdjustCostAndIncome incomeSum = new SummaryTicketAdjustCostAndIncome();
        if ((incomeSumTemp != null) && (incomeSumTemp.size() != 0)) {
            incomeSum = (SummaryTicketAdjustCostAndIncome) incomeSumTemp.get(0);
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
//        HSSFCell cell6722 = row62.createCell(6);
//        cell6722.setCellValue("Invoice Inbound");
//        cell6722.setCellStyle(styleAlignRightBorderAllHeaderTable);
//        sheet1.autoSizeColumn(6);
        HSSFCell cell682 = row62.createCell(6);
        cell682.setCellValue("Invoice Outbound");
        cell682.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet1.autoSizeColumn(6);
        HSSFCell cell692 = row62.createCell(7);
        cell692.setCellValue("Over");
        cell692.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet1.autoSizeColumn(7);
        HSSFCell cell712 = row62.createCell(8);
        cell712.setCellValue("Discount");
        cell712.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet1.autoSizeColumn(8);
        HSSFCell cell702 = row62.createCell(9);
        cell702.setCellValue("  Little  ");
        cell702.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet1.autoSizeColumn(9);
        HSSFCell cell722 = row62.createCell(10);
        cell722.setCellValue("Cancel");
        cell722.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet1.autoSizeColumn(10);
        HSSFCell cell732 = row62.createCell(11);
        cell732.setCellValue("Wait Pay");
        cell732.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet1.autoSizeColumn(11);
        HSSFCell cell742 = row62.createCell(12);
        cell742.setCellValue("RC AG Com");
        cell742.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet1.autoSizeColumn(12);
        HSSFCell cell752 = row62.createCell(13);
        cell752.setCellValue("Total Balance");
        cell752.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet1.autoSizeColumn(13);

        List<SummaryTicketAdjustCostAndIncome> listSummaryTicketCostIncomeSum = listTotal.get(0).getSummaryTicketAdjustAndIncomeSum();

        int count2 = 9 + listSummaryTicketCostIncomeSum.size();
        int start2 = 11;
        int end2 = 0;
        int num2 = 0;

        BigDecimal interpax1 = new BigDecimal(0);
        BigDecimal intercostinv1 = new BigDecimal(0);
        BigDecimal interinvoicewendy1 = new BigDecimal(0);
        BigDecimal interinvoiceinbound1 = new BigDecimal(0);
        BigDecimal interinvoiceoutbound1 = new BigDecimal(0);
        BigDecimal interover1 = new BigDecimal(0);
        BigDecimal interlittle1 = new BigDecimal(0);
        BigDecimal interdiscount1 = new BigDecimal(0);
        BigDecimal intercancel1 = new BigDecimal(0);
        BigDecimal interwaitpay1 = new BigDecimal(0);
        BigDecimal interrcagcom1 = new BigDecimal(0);
        BigDecimal intertotalbalance1 = new BigDecimal(0);

        BigDecimal domesticpax1 = new BigDecimal(0);
        BigDecimal domesticcostinv1 = new BigDecimal(0);
        BigDecimal domesticinvoicewendy1 = new BigDecimal(0);
        BigDecimal domesticinvoiceinbound1 = new BigDecimal(0);
        BigDecimal domesticinvoiceoutbound1 = new BigDecimal(0);
        BigDecimal domesticover1 = new BigDecimal(0);
        BigDecimal domesticlittle1 = new BigDecimal(0);
        BigDecimal domesticdiscount1 = new BigDecimal(0);
        BigDecimal domesticcancel1 = new BigDecimal(0);
        BigDecimal domesticwaitpay1 = new BigDecimal(0);
        BigDecimal domesticrcagcom1 = new BigDecimal(0);
        BigDecimal domestictotalbalance1 = new BigDecimal(0);

        BigDecimal cancelpax1 = new BigDecimal(0);
        BigDecimal cancelcostinv1 = new BigDecimal(0);
        BigDecimal cancelinvoicewendy1 = new BigDecimal(0);
        BigDecimal cancelinvoiceinbound1 = new BigDecimal(0);
        BigDecimal cancelinvoiceoutbound1 = new BigDecimal(0);
        BigDecimal cancelover1 = new BigDecimal(0);
        BigDecimal cancellittle1 = new BigDecimal(0);
        BigDecimal canceldiscount1 = new BigDecimal(0);
        BigDecimal cancelcancel1 = new BigDecimal(0);
        BigDecimal cancelwaitpay1 = new BigDecimal(0);
        BigDecimal cancelrcagcom1 = new BigDecimal(0);
        BigDecimal canceltotalbalance1 = new BigDecimal(0);

        for (int r = 9; r < count2; r++) {
            if (num2 < listSummaryTicketCostIncomeSum.size()) {
                if ("I".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getTyperounting())) {
                    BigDecimal interpaxtemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getPax()) ? new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getPax()) : new BigDecimal(0));
                    interpax1 = interpax1.add(interpaxtemp);
                    BigDecimal intercostinvtemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getCostinv()) ? new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getCostinv()) : new BigDecimal(0));
                    intercostinv1 = intercostinv1.add(intercostinvtemp);
                    BigDecimal interinvoicewendytemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getInvoicewendy()) ? new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getInvoicewendy()) : new BigDecimal(0));
                    interinvoicewendy1 = interinvoicewendy1.add(interinvoicewendytemp);
                    BigDecimal interinvoiceoutboundtemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getInvoiceoutbound()) ? new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getInvoiceoutbound()) : new BigDecimal(0));
                    interinvoiceoutbound1 = interinvoiceoutbound1.add(interinvoiceoutboundtemp);
                    BigDecimal interinvoiceinboundtemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getInvoiceinbound()) ? new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getInvoiceinbound()) : new BigDecimal(0));
                    interinvoiceinbound1 = interinvoiceinbound1.add(interinvoiceinboundtemp);
                    BigDecimal interlittletemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getLitter()) ? new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getLitter()) : new BigDecimal(0));
                    interlittle1 = interlittle1.add(interlittletemp);
                    BigDecimal interdiscounttemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getDiscount()) ? new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getDiscount()) : new BigDecimal(0));
                    interdiscount1 = interdiscount1.add(interdiscounttemp);
                    BigDecimal intercanceltemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getCancel()) ? new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getCancel()) : new BigDecimal(0));
                    intercancel1 = intercancel1.add(intercanceltemp);
                    BigDecimal interwaitpaytemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getWait_pay()) ? new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getWait_pay()) : new BigDecimal(0));
                    interwaitpay1 = interwaitpay1.add(interwaitpaytemp);
                    BigDecimal interrcagcomtemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getRcagcom()) ? new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getRcagcom()) : new BigDecimal(0));
                    interrcagcom1 = interrcagcom1.add(interrcagcomtemp);
                    BigDecimal intertotalbalancetemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getTotal_balance()) ? new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getTotal_balance()) : new BigDecimal(0));
                    intertotalbalance1 = intertotalbalance1.add(intertotalbalancetemp);
                    
                    BigDecimal interovertemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getOver()) ? new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getOver()) : new BigDecimal(0));
                    interover1 = interover1.add(interovertemp);

                } else if ("D".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getTyperounting())) {
                    BigDecimal domesticpaxtemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getPax()) ? new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getPax()) : new BigDecimal(0));
                    domesticpax1 = domesticpax1.add(domesticpaxtemp);
                    BigDecimal domesticcostinvtemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getCostinv()) ? new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getCostinv()) : new BigDecimal(0));
                    domesticcostinv1 = domesticcostinv1.add(domesticcostinvtemp);
                    BigDecimal domesticinvoicewendytemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getInvoicewendy()) ? new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getInvoicewendy()) : new BigDecimal(0));
                    domesticinvoicewendy1 = domesticinvoicewendy1.add(domesticinvoicewendytemp);
                    BigDecimal domesticinvoiceoutboundtemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getInvoiceoutbound()) ? new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getInvoiceoutbound()) : new BigDecimal(0));
                    domesticinvoiceoutbound1 = domesticinvoiceoutbound1.add(domesticinvoiceoutboundtemp);
                    BigDecimal domesticinvoiceinboundtemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getInvoiceinbound()) ? new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getInvoiceinbound()) : new BigDecimal(0));
                    domesticinvoiceinbound1 = domesticinvoiceinbound1.add(domesticinvoiceinboundtemp);
                    BigDecimal domesticlittletemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getLitter()) ? new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getLitter()) : new BigDecimal(0));
                    domesticlittle1 = domesticlittle1.add(domesticlittletemp);
                    BigDecimal domesticdiscounttemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getDiscount()) ? new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getDiscount()) : new BigDecimal(0));
                    domesticdiscount1 = domesticdiscount1.add(domesticdiscounttemp);
                    BigDecimal domesticcanceltemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getCancel()) ? new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getCancel()) : new BigDecimal(0));
                    domesticcancel1 = domesticcancel1.add(domesticcanceltemp);
                    BigDecimal domesticwaitpaytemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getWait_pay()) ? new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getWait_pay()) : new BigDecimal(0));
                    domesticwaitpay1 = domesticwaitpay1.add(domesticwaitpaytemp);
                    BigDecimal domesticrcagcomtemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getRcagcom()) ? new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getRcagcom()) : new BigDecimal(0));
                    domesticrcagcom1 = domesticrcagcom1.add(domesticrcagcomtemp);
                    BigDecimal domestictotalbalancetemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getTotal_balance()) ? new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getTotal_balance()) : new BigDecimal(0));
                    domestictotalbalance1 = domestictotalbalance1.add(domestictotalbalancetemp);
                    BigDecimal domesticovertemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getOver()) ? new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getOver()) : new BigDecimal(0));
                    domesticover1 = domesticover1.add(domesticovertemp);

                } else if ("C".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getTyperounting())) {
                    BigDecimal cancelpaxtemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getPax()) ? new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getPax()) : new BigDecimal(0));
                    cancelpax1 = cancelpax1.add(cancelpaxtemp);
                    BigDecimal cancelcostinvtemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getCostinv()) ? new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getCostinv()) : new BigDecimal(0));
                    cancelcostinv1 = cancelcostinv1.add(cancelcostinvtemp);
                    BigDecimal cancelinvoicewendytemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getInvoicewendy()) ? new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getInvoicewendy()) : new BigDecimal(0));
                    cancelinvoicewendy1 = cancelinvoicewendy1.add(cancelinvoicewendytemp);
                    BigDecimal cancelinvoiceoutboundtemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getInvoiceoutbound()) ? new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getInvoiceoutbound()) : new BigDecimal(0));
                    cancelinvoiceoutbound1 = cancelinvoiceoutbound1.add(cancelinvoiceoutboundtemp);
                    BigDecimal cancelinvoiceinboundtemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getInvoiceinbound()) ? new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getInvoiceinbound()) : new BigDecimal(0));
                    cancelinvoiceinbound1 = cancelinvoiceinbound1.add(cancelinvoiceinboundtemp);
                    BigDecimal cancellittletemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getLitter()) ? new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getLitter()) : new BigDecimal(0));
                    cancellittle1 = cancellittle1.add(cancellittletemp);
                    BigDecimal canceldiscounttemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getDiscount()) ? new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getDiscount()) : new BigDecimal(0));
                    canceldiscount1 = canceldiscount1.add(canceldiscounttemp);
                    BigDecimal cancelcanceltemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getCancel()) ? new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getCancel()) : new BigDecimal(0));
                    cancelcancel1 = cancelcancel1.add(cancelcanceltemp);
                    BigDecimal cancelwaitpaytemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getWait_pay()) ? new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getWait_pay()) : new BigDecimal(0));
                    cancelwaitpay1 = cancelwaitpay1.add(cancelwaitpaytemp);
                    BigDecimal cancelrcagcomtemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getRcagcom()) ? new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getRcagcom()) : new BigDecimal(0));
                    cancelrcagcom1 = cancelrcagcom1.add(cancelrcagcomtemp);
                    BigDecimal canceltotalbalancetemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getTotal_balance()) ? new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getTotal_balance()) : new BigDecimal(0));
                    canceltotalbalance1 = canceltotalbalance1.add(canceltotalbalancetemp);
                    BigDecimal cancelovertemp = (!"".equalsIgnoreCase(listSummaryTicketCostIncomeSum.get(num2).getOver()) ? new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getOver()) : new BigDecimal(0));
                    cancelover1 = cancelover1.add(cancelovertemp);

                }

                HSSFRow row22 = sheet1.createRow(r);
                HSSFCell cell1 = row22.createCell(0);
                cell1.setCellValue(listSummaryTicketCostIncomeSum.get(num2).getTypepayment());
                cell1.setCellStyle(styleDetailTable);
                HSSFCell cell2 = row22.createCell(1);
                cell2.setCellValue(listSummaryTicketCostIncomeSum.get(num2).getTyperounting());
                cell2.setCellStyle(styleDetailTableCenter);
                HSSFCell cell3 = row22.createCell(2);
                BigDecimal pax = new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getPax());
                cell3.setCellValue((pax != null) ? pax.doubleValue() : new BigDecimal("0").doubleValue());
                cell3.setCellStyle(stylePaxTable);
                HSSFCell cell4 = row22.createCell(3);
                cell4.setCellValue(listSummaryTicketCostIncomeSum.get(num2).getInvno());
                cell4.setCellStyle(styleDetailTable);
                HSSFCell cell5 = row22.createCell(4);
//                System.out.println("Cost : " + listSummaryTicketCostIncomeSum.get(num2).getCostinv());
                BigDecimal costinv = null;
                if ("".equals(listSummaryTicketCostIncomeSum.get(num2).getCostinv())) {
                    costinv = new BigDecimal(0);
                } else {
                    costinv = new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getCostinv());
                }
                cell5.setCellValue((costinv != null && !"0".equals(costinv)) ? costinv.doubleValue() : new BigDecimal("0").doubleValue());
                cell5.setCellStyle(styleDetailTableNumber);
                HSSFCell cell6 = row22.createCell(5);
                BigDecimal invwendy = null;
                if ("".equals(listSummaryTicketCostIncomeSum.get(num2).getInvoicewendy())) {
                    invwendy = new BigDecimal(0);
                } else {
                    invwendy = new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getInvoicewendy());
                }
                cell6.setCellValue((invwendy != null) ? invwendy.doubleValue() : new BigDecimal("0").doubleValue());
                cell6.setCellStyle(styleDetailTableNumber);
//                HSSFCell cell7 = row22.createCell(6);
//                BigDecimal invinbound = null;
//                if ("".equals(listSummaryTicketCostIncomeSum.get(num2).getInvoiceinbound())) {
//                    invinbound = new BigDecimal(0);
//                } else {
//                    invinbound = new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getInvoiceinbound());
//                }
//                cell7.setCellValue((invinbound != null) ? invinbound.doubleValue() : new BigDecimal("0").doubleValue());
//                cell7.setCellStyle(styleDetailTableNumber);
                HSSFCell cell8 = row22.createCell(6);
                BigDecimal invoutbound = null;
                if ("".equals(listSummaryTicketCostIncomeSum.get(num2).getInvoiceoutbound())) {
                    invoutbound = new BigDecimal(0);
                } else {
                    invoutbound = new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getInvoiceoutbound());
                }
                cell8.setCellValue((invoutbound != null) ? invoutbound.doubleValue() : new BigDecimal("0").doubleValue());
                cell8.setCellStyle(styleDetailTableNumber);
                HSSFCell cell9 = row22.createCell(7);
                cell9.setCellValue(listSummaryTicketCostIncomeSum.get(num2).getOver() != null ? (new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getOver())).doubleValue() : new BigDecimal("0").doubleValue());
                cell9.setCellStyle(styleDetailTableNumber);
                HSSFCell cell10 = row22.createCell(8);
                BigDecimal discount = null;
                if ("".equals(listSummaryTicketCostIncomeSum.get(num2).getDiscount())) {
                    discount = new BigDecimal(0);
                } else {
                    discount = new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getDiscount());
                }
                cell10.setCellValue((discount != null) ? discount.doubleValue() : new BigDecimal("0").doubleValue());
                cell10.setCellStyle(styleDetailTableNumber);
                sheet.autoSizeColumn(9);
                HSSFCell cell11 = row22.createCell(9);
                BigDecimal little = null;
                if ("".equals(listSummaryTicketCostIncomeSum.get(num2).getLitter())) {
                    little = new BigDecimal(0);
                } else {
                    little = new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getLitter());
                }
                cell11.setCellValue((little != null) ? little.doubleValue() : new BigDecimal("0").doubleValue());
                cell11.setCellStyle(styleDetailTableNumber);
                HSSFCell cell12 = row22.createCell(10);
                BigDecimal cancel = null;
                if ("".equals(listSummaryTicketCostIncomeSum.get(num2).getCancel())) {
                    cancel = new BigDecimal(0);
                } else {
                    cancel = new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getCancel());
                }
                cell12.setCellValue((cancel != null) ? cancel.doubleValue() : new BigDecimal("0").doubleValue());
                cell12.setCellStyle(styleDetailTableNumber);
                HSSFCell cell13 = row22.createCell(11);
                BigDecimal wait = null;
                if ("".equals(listSummaryTicketCostIncomeSum.get(num2).getWait_pay())) {
                    wait = new BigDecimal(0);
                } else {
                    wait = new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getWait_pay());
                }
                cell13.setCellValue((wait != null) ? wait.doubleValue() : new BigDecimal("0").doubleValue());
                cell13.setCellStyle(styleDetailTableNumber);
                HSSFCell cell14 = row22.createCell(12);
                BigDecimal rc = null;
                if ("".equals(listSummaryTicketCostIncomeSum.get(num2).getRcagcom())) {
                    rc = new BigDecimal(0);
                } else {
                    rc = new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getRcagcom());
                }
                cell14.setCellValue((rc != null) ? rc.doubleValue() : new BigDecimal("0").doubleValue());
                cell14.setCellStyle(styleDetailTableNumber);
                HSSFCell cell15 = row22.createCell(13);
                BigDecimal balance = null;
                if ("".equals(listSummaryTicketCostIncomeSum.get(num2).getTotal_balance())) {
                    balance = new BigDecimal(0);
                } else {
                    balance = new BigDecimal(listSummaryTicketCostIncomeSum.get(num2).getTotal_balance());
                }
                cell15.setCellValue((balance != null) ? balance.doubleValue() : new BigDecimal("0").doubleValue());
                cell15.setCellStyle(styleDetailTableNumber);
                num2++;
            }
            for (int i = 0; i < listSummaryTicketCostIncomeSum.size(); i++) {
                sheet1.autoSizeColumn(i);
            }
        }

        //Inter
        HSSFRow rowsinter2 = sheet1.createRow(count2);
        sheet1.addMergedRegion(CellRangeAddress.valueOf("A" + (count2 + 1) + ":B" + (count2 + 1)));
        HSSFCell cellInter00 = rowsinter2.createCell(0);
        cellInter00.setCellValue("Total Inter");
        cellInter00.setCellStyle(styleTotal);
        HSSFCell cellInter01 = rowsinter2.createCell(2);
        cellInter01.setCellValue(interpax1.doubleValue());
        cellInter01.setCellStyle(stylePax);
        HSSFCell cellInter02 = rowsinter2.createCell(4);
        cellInter02.setCellValue(intercostinv1.doubleValue());
        cellInter02.setCellStyle(styleSum);
        HSSFCell cellInter03 = rowsinter2.createCell(5);
        cellInter03.setCellValue(interinvoicewendy1.doubleValue());
        cellInter03.setCellStyle(styleSum);
//        HSSFCell cellInter04 = rowsinter2.createCell(6);
//        cellInter04.setCellValue(interinvoiceinbound1.doubleValue());
//        cellInter04.setCellStyle(styleSum);
        HSSFCell cellInter05 = rowsinter2.createCell(6);
        cellInter05.setCellValue(interinvoiceoutbound1.doubleValue());
        cellInter05.setCellStyle(styleSum);
        HSSFCell cellInter005 = rowsinter2.createCell(7);
        cellInter005.setCellValue(interover1.doubleValue());
        cellInter005.setCellStyle(styleSum);
        HSSFCell cellInter06 = rowsinter2.createCell(8);
        cellInter06.setCellValue(interdiscount1.doubleValue());
        cellInter06.setCellStyle(styleSum);
        HSSFCell cellInter07 = rowsinter2.createCell(9);
        cellInter07.setCellValue(interlittle1.doubleValue());
        cellInter07.setCellStyle(styleSum);
        HSSFCell cellInter08 = rowsinter2.createCell(10);
        cellInter08.setCellValue(intercancel1.doubleValue());
        cellInter08.setCellStyle(styleSum);
        HSSFCell cellInter09 = rowsinter2.createCell(11);
        cellInter09.setCellValue(interwaitpay1.doubleValue());
        cellInter09.setCellStyle(styleSum);
        HSSFCell cellInter10 = rowsinter2.createCell(12);
        cellInter10.setCellValue(interrcagcom1.doubleValue());
        cellInter10.setCellStyle(styleSum);
        HSSFCell cellInter11 = rowsinter2.createCell(13);
        cellInter11.setCellValue(intertotalbalance1.doubleValue());
        cellInter11.setCellStyle(styleSum);
        HSSFCell cellInter12 = rowsinter2.createCell(1);
        cellInter12.setCellStyle(styleSum);
        HSSFCell cellInter13 = rowsinter2.createCell(3);
        cellInter13.setCellStyle(styleSum);
        HSSFCell cellInter15 = rowsinter2.createCell(8);
        cellInter15.setCellStyle(styleSum);

        //Domestic
        HSSFRow rowsdomestic2 = sheet1.createRow(count2 + 1);
        sheet1.addMergedRegion(CellRangeAddress.valueOf("A" + (count2 + 2) + ":B" + (count2 + 2)));
        HSSFCell cellDomestic00 = rowsdomestic2.createCell(0);
        cellDomestic00.setCellValue("Total Domestic");
        cellDomestic00.setCellStyle(styleTotal);
        HSSFCell cellDomestic01 = rowsdomestic2.createCell(2);
        cellDomestic01.setCellValue(domesticpax1.doubleValue());
        cellDomestic01.setCellStyle(stylePax);
        HSSFCell cellDomestic02 = rowsdomestic2.createCell(4);
        cellDomestic02.setCellValue(domesticcostinv1.doubleValue());
        cellDomestic02.setCellStyle(styleSum);
        HSSFCell cellDomestic03 = rowsdomestic2.createCell(5);
        cellDomestic03.setCellValue(domesticinvoicewendy1.doubleValue());
        cellDomestic03.setCellStyle(styleSum);
        HSSFCell cellDomestic04 = rowsdomestic2.createCell(6);
        cellDomestic04.setCellValue(domesticinvoiceinbound1.doubleValue());
        cellDomestic04.setCellStyle(styleSum);
//        HSSFCell cellDomestic05 = rowsdomestic2.createCell(7);
//        cellDomestic05.setCellValue(domesticinvoiceoutbound1.doubleValue());
//        cellDomestic05.setCellStyle(styleSum);
        HSSFCell cellDomestic005 = rowsdomestic2.createCell(7);
        cellDomestic005.setCellValue(domesticover1.doubleValue());
        cellDomestic005.setCellStyle(styleSum);
        HSSFCell cellDomestic06 = rowsdomestic2.createCell(8);
        cellDomestic06.setCellValue(domesticdiscount1.doubleValue());
        cellDomestic06.setCellStyle(styleSum);
        HSSFCell cellDomestic07 = rowsdomestic2.createCell(9);
        cellDomestic07.setCellValue(domesticlittle1.doubleValue());
        cellDomestic07.setCellStyle(styleSum);
        HSSFCell cellDomestic08 = rowsdomestic2.createCell(10);
        cellDomestic08.setCellValue(domesticcancel1.doubleValue());
        cellDomestic08.setCellStyle(styleSum);
        HSSFCell cellDomestic09 = rowsdomestic2.createCell(11);
        cellDomestic09.setCellValue(domesticwaitpay1.doubleValue());
        cellDomestic09.setCellStyle(styleSum);
        HSSFCell cellDomestic10 = rowsdomestic2.createCell(12);
        cellDomestic10.setCellValue(domesticrcagcom1.doubleValue());
        cellDomestic10.setCellStyle(styleSum);
        HSSFCell cellDomestic11 = rowsdomestic2.createCell(13);
        cellDomestic11.setCellValue(domestictotalbalance1.doubleValue());
        cellDomestic11.setCellStyle(styleSum);
        HSSFCell cellDomestic12 = rowsdomestic2.createCell(1);
        cellDomestic12.setCellStyle(styleSum);
        HSSFCell cellDomestic13 = rowsdomestic2.createCell(3);
        cellDomestic13.setCellStyle(styleSum);
        HSSFCell cellDomestic15 = rowsdomestic2.createCell(8);
        cellDomestic15.setCellStyle(styleSum);

        //Cancel
        HSSFRow rowscancel2 = sheet1.createRow(count2 + 2);
        sheet1.addMergedRegion(CellRangeAddress.valueOf("A" + (count2 + 3) + ":B" + (count2 + 3)));
        HSSFCell cellCancel00 = rowscancel2.createCell(0);
        cellCancel00.setCellValue("Total Cancel");
        cellCancel00.setCellStyle(styleTotal);
        HSSFCell cellCancel01 = rowscancel2.createCell(2);
        cellCancel01.setCellValue(cancelpax1.doubleValue());
        cellCancel01.setCellStyle(stylePax);
        HSSFCell cellCancel02 = rowscancel2.createCell(4);
        cellCancel02.setCellValue(cancelcostinv1.doubleValue());
        cellCancel02.setCellStyle(styleSum);
        HSSFCell cellCancel03 = rowscancel2.createCell(5);
        cellCancel03.setCellValue(cancelinvoicewendy1.doubleValue());
        cellCancel03.setCellStyle(styleSum);
        HSSFCell cellCancel04 = rowscancel2.createCell(6);
        cellCancel04.setCellValue(cancelinvoiceoutbound1.doubleValue());
        cellCancel04.setCellStyle(styleSum);
//        HSSFCell cellCancel05 = rowscancel2.createCell(7);
//        cellCancel05.setCellValue(cancelinvoiceinbound1.doubleValue());
//        cellCancel05.setCellStyle(styleSum);
        HSSFCell cellCancel005 = rowscancel2.createCell(7);
        cellCancel005.setCellValue(cancelover1.doubleValue());
        cellCancel005.setCellStyle(styleSum);
        HSSFCell cellCancel06 = rowscancel2.createCell(8);
        cellCancel06.setCellValue(canceldiscount1.doubleValue());
        cellCancel06.setCellStyle(styleSum);
        HSSFCell cellCancel07 = rowscancel2.createCell(9);
        cellCancel07.setCellValue(cancellittle1.doubleValue());
        cellCancel07.setCellStyle(styleSum);
        HSSFCell cellCancel08 = rowscancel2.createCell(10);
        cellCancel08.setCellValue(cancelcancel1.doubleValue());
        cellCancel08.setCellStyle(styleSum);
        HSSFCell cellCancel09 = rowscancel2.createCell(11);
        cellCancel09.setCellValue(cancelwaitpay1.doubleValue());
        cellCancel09.setCellStyle(styleSum);
        HSSFCell cellCancel10 = rowscancel2.createCell(12);
        cellCancel10.setCellValue(cancelrcagcom1.doubleValue());
        cellCancel10.setCellStyle(styleSum);
        HSSFCell cellCancel11 = rowscancel2.createCell(13);
        cellCancel11.setCellValue(canceltotalbalance1.doubleValue());
        cellCancel11.setCellStyle(styleSum);
        HSSFCell cellCancel12 = rowscancel2.createCell(1);
        cellCancel12.setCellStyle(styleSum);
        HSSFCell cellCancel13 = rowscancel2.createCell(3);
        cellCancel13.setCellStyle(styleSum);
        HSSFCell cellCancel15 = rowscancel2.createCell(8);
        cellCancel15.setCellStyle(styleSum);

        String sumPax2 = "SUM(C" + 10 + ":C" + (count2) + ")";
        String sumCosInv2 = "SUM(E" + 10 + ":E" + (count2) + ")";
        String sumInvWendy2 = "SUM(F" + 10 + ":F" + (count2) + ")";
//        String sumInvInbound2 = "SUM(G" + 10 + ":G" + (count2) + ")";
        String sumInvOutbound2 = "SUM(G" + 10 + ":G" + (count2) + ")";
        String sumOver2 =  "SUM(H" + 10 + ":H" + (count2) + ")";
        String sumDiscount2 = "SUM(I" + 10 + ":I" + (count2) + ")";
        String sumLittle2 = "SUM(J" + 10 + ":J" + (count2) + ")";
        String sumCancel2 = "SUM(K" + 10 + ":K" + (count2) + ")";
        String sumRC2 = "SUM(M" + 10 + ":M" + (count2) + ")";
        String sumWait2 = "SUM(L" + 10 + ":L" + (count2) + ")";
        String sumBlance2 = "SUM(N" + 10 + ":N" + (count2) + ")";

        HSSFRow row20 = sheet1.createRow(count2 + 3);
        sheet1.addMergedRegion(CellRangeAddress.valueOf("A" + (count2 + 4) + ":B" + (count2 + 4)));
        HSSFCell cell60Sum2 = row20.createCell(0);
        cell60Sum2.setCellValue("Total");
        cell60Sum2.setCellStyle(styleTotal);
        sheet1.autoSizeColumn(0);
        HSSFCell cell7Sum2 = row20.createCell(2);
        cell7Sum2.setCellFormula(sumPax2);
        sheet1.autoSizeColumn(2);
        cell7Sum2.setCellStyle(stylePax);
        HSSFCell cell8Sum2 = row20.createCell(3);
        cell8Sum2.setCellValue("");
        sheet1.autoSizeColumn(3);
        cell8Sum2.setCellStyle(styleDetailTableNumber);
        HSSFCell cell9Sum2 = row20.createCell(4);
        cell9Sum2.setCellFormula(sumCosInv2);
        cell9Sum2.setCellStyle(styleDetailTableNumber);
        sheet1.autoSizeColumn(4);
        HSSFCell cell10Sum2 = row20.createCell(5);
        cell10Sum2.setCellFormula(sumInvWendy2);
        cell10Sum2.setCellStyle(styleDetailTableNumber);
        sheet1.autoSizeColumn(5);
//        HSSFCell cell11Sum2 = row20.createCell(6);
//        cell11Sum2.setCellFormula(sumInvInbound2);
//        cell11Sum2.setCellStyle(styleDetailTableNumber);
//        sheet1.autoSizeColumn(6);
        HSSFCell cell12Sum2 = row20.createCell(6);
        cell12Sum2.setCellFormula(sumInvOutbound2);
        cell12Sum2.setCellStyle(styleDetailTableNumber);
        sheet1.autoSizeColumn(6);
        HSSFCell cell13Sum2 = row20.createCell(7);
        cell13Sum2.setCellFormula(sumOver2);
        cell13Sum2.setCellStyle(styleDetailTableNumber);
        sheet1.autoSizeColumn(7);
        HSSFCell cell14Sum2 = row20.createCell(8);
        cell14Sum2.setCellFormula(sumDiscount2);
        cell14Sum2.setCellStyle(styleDetailTableNumber);
        sheet1.autoSizeColumn(8);
        HSSFCell cell15Sum2 = row20.createCell(9);
        cell15Sum2.setCellFormula(sumLittle2);
        cell15Sum2.setCellStyle(styleDetailTableNumber);
        sheet1.autoSizeColumn(9);
        HSSFCell cell16Sum2 = row20.createCell(10);
        cell16Sum2.setCellFormula(sumCancel2);
        cell16Sum2.setCellStyle(styleDetailTableNumber);
        sheet1.autoSizeColumn(10);
        HSSFCell cell17Sum2 = row20.createCell(11);
        cell17Sum2.setCellFormula(sumWait2);
        cell17Sum2.setCellStyle(styleDetailTableNumber);
        sheet1.autoSizeColumn(11);
        HSSFCell cell18Sum2 = row20.createCell(12);
        cell18Sum2.setCellFormula(sumRC2);
        cell18Sum2.setCellStyle(styleDetailTableNumber);
        sheet1.autoSizeColumn(12);
        HSSFCell cell19Sum2 = row20.createCell(13);
        cell19Sum2.setCellFormula(sumBlance2);
        cell19Sum2.setCellStyle(styleDetailTableNumber);
        sheet1.autoSizeColumn(13);

        HSSFRow rowLL2 = sheet1.createRow(count2 + 4);
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
//        rowLL2.createCell(14).setCellStyle(styleBorderTop);
    }

    private void getTicketCommissionReceive(HSSFWorkbook wb, List TicketCommissionReceive) {
        List<ListTicketCommissionReceive> listTotal = TicketCommissionReceive;

        String sheetName = "receive";// name of sheet
        String sheetName1 = "receive_summary";
        HSSFSheet sheet = wb.createSheet(sheetName);
        HSSFSheet sheet1 = wb.createSheet(sheetName1);
        HSSFDataFormat currency = wb.createDataFormat();
        UtilityExcelFunction excelFunction = new UtilityExcelFunction();

        // line table
        HSSFCellStyle styleBorderTop = wb.createCellStyle();// use
        styleBorderTop.setBorderTop(styleBorderTop.BORDER_THIN);
        HSSFCellStyle styleAlignRightBorderAllHeaderTable = wb.createCellStyle(); // use
        styleAlignRightBorderAllHeaderTable.setFont(excelFunction.getHeaderTable(wb.createFont()));
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

        HSSFCellStyle styleDetailPax = wb.createCellStyle(); // use
        styleDetailPax.setAlignment(styleDetailPax.ALIGN_CENTER);
        styleDetailPax.setBorderLeft(styleDetailPax.BORDER_THIN);
        styleDetailPax.setBorderRight(styleDetailPax.BORDER_THIN);
//        styleDetailPax.setBorderTop(styleDetailPax.BORDER_THIN);
//        styleDetailPax.setBorderBottom(styleDetailPax.BORDER_THIN);
        styleDetailPax.setDataFormat(currency.getFormat("#,##0"));

        HSSFCellStyle styleDetailTotal = wb.createCellStyle(); // use
        styleDetailTotal.setAlignment(styleDetailTotal.ALIGN_RIGHT);
        styleDetailTotal.setBorderLeft(styleDetailTotal.BORDER_THIN);
        styleDetailTotal.setBorderRight(styleDetailTotal.BORDER_THIN);
        styleDetailTotal.setBorderTop(styleDetailTotal.BORDER_THIN);
        styleDetailTotal.setBorderBottom(styleDetailTotal.BORDER_THIN);
        styleDetailTotal.setDataFormat(currency.getFormat("#,##0.00"));

        HSSFCellStyle styleNameTotal = wb.createCellStyle(); // use
        styleNameTotal.setFont(excelFunction.getHeaderTable(wb.createFont()));
        styleNameTotal.setAlignment(styleNameTotal.ALIGN_RIGHT);
        styleNameTotal.setBorderTop(styleNameTotal.BORDER_THIN);
        styleNameTotal.setBorderBottom(styleNameTotal.BORDER_THIN);
        styleNameTotal.setBorderRight(styleNameTotal.BORDER_THIN);
        styleNameTotal.setBorderLeft(styleNameTotal.BORDER_THIN);

        HSSFCellStyle styleTotalPax = wb.createCellStyle(); // use
        styleTotalPax.setAlignment(styleTotalPax.ALIGN_CENTER);
        styleTotalPax.setBorderLeft(styleTotalPax.BORDER_THIN);
        styleTotalPax.setBorderRight(styleTotalPax.BORDER_THIN);
        styleTotalPax.setBorderTop(styleTotalPax.BORDER_THIN);
        styleTotalPax.setBorderBottom(styleTotalPax.BORDER_THIN);
        styleTotalPax.setDataFormat(currency.getFormat("#,##0"));
        // Sheet Detail (2)
        // set Header Report (Row 1)
        HSSFCellStyle styleC11 = wb.createCellStyle();
        HSSFRow row01 = sheet.createRow(0);
        HSSFCell cell01 = row01.createCell(0);
        cell01.setCellValue("Summary Ticket Commission Receive");
        styleC11.setFont(excelFunction.getHeaderFont(wb.createFont()));
        cell01.setCellStyle(styleC11);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:H1"));

        // Set align Text
        HSSFCellStyle styleC21 = wb.createCellStyle();
        styleC21.setAlignment(styleC21.ALIGN_RIGHT);
        HSSFCellStyle styleC22 = wb.createCellStyle();
        styleC22.setAlignment(styleC22.ALIGN_LEFT);

        List<TicketCommissionReceive> receiveTemp = listTotal.get(0).getTicketCommmissionReceive();
        TicketCommissionReceive receive = new TicketCommissionReceive();
        if (!receiveTemp.isEmpty()) {
            receive = (TicketCommissionReceive) receiveTemp.get(0);
        }
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

        BigDecimal interpax = new BigDecimal(0);
        BigDecimal intercommairline = new BigDecimal(0);
        BigDecimal interlittlecom = new BigDecimal(0);
        BigDecimal interpay = new BigDecimal(0);
        BigDecimal interrc = new BigDecimal(0);
        BigDecimal interpayref = new BigDecimal(0);
        BigDecimal intercommrec = new BigDecimal(0);

        BigDecimal domesticpax = new BigDecimal(0);
        BigDecimal domesticcommairline = new BigDecimal(0);
        BigDecimal domesticlittlecom = new BigDecimal(0);
        BigDecimal domesticpay = new BigDecimal(0);
        BigDecimal domesticrc = new BigDecimal(0);
        BigDecimal domesticpayref = new BigDecimal(0);
        BigDecimal domesticcommrec = new BigDecimal(0);

        BigDecimal cancelpax = new BigDecimal(0);
        BigDecimal cancelcommairline = new BigDecimal(0);
        BigDecimal cancellittlecom = new BigDecimal(0);
        BigDecimal cancelpay = new BigDecimal(0);
        BigDecimal cancelrc = new BigDecimal(0);
        BigDecimal cancelpayref = new BigDecimal(0);
        BigDecimal cancelcommrec = new BigDecimal(0);

        for (int r = 9; r < count; r++) {
            if (num < listTicketCommissionReceive.size()) {
                HSSFRow row = sheet.createRow(r);
                HSSFCell cell1 = row.createCell(0);
                cell1.setCellValue(listTicketCommissionReceive.get(num).getTypepayment());
                cell1.setCellStyle(styleDetailTable);
                HSSFCell cell2 = row.createCell(1);
                cell2.setCellValue(listTicketCommissionReceive.get(num).getTyperounting());
                cell2.setCellStyle(styleDetailTable);
                HSSFCell cell3 = row.createCell(2);
                BigDecimal pax = new BigDecimal(listTicketCommissionReceive.get(num).getPax());
                cell3.setCellValue((pax != null) ? pax.doubleValue() : new BigDecimal("0").doubleValue());
                cell3.setCellStyle(styleDetailPax);
                HSSFCell cell4 = row.createCell(3);
                cell4.setCellValue(listTicketCommissionReceive.get(num).getAir());
                cell4.setCellStyle(styleDetailTable);
                HSSFCell cell55 = row.createCell(4);
                BigDecimal comair = null;
                if ("".equals(listTicketCommissionReceive.get(num).getComairline())) {
                    comair = new BigDecimal(0);
                } else {
                    comair = new BigDecimal(listTicketCommissionReceive.get(num).getComairline());
                }
                cell55.setCellValue((comair != null && !"0".equals(comair)) ? comair.doubleValue() : new BigDecimal("0").doubleValue());
                cell55.setCellStyle(styleDetailTableNumber);
                HSSFCell cell5 = row.createCell(5);
                BigDecimal littlecom = null;
                if ("".equals(listTicketCommissionReceive.get(num).getLittlecom())) {
                    littlecom = new BigDecimal(0);
                } else {
                    littlecom = new BigDecimal(listTicketCommissionReceive.get(num).getLittlecom());
                }
                cell5.setCellValue((littlecom != null && !"0".equals(littlecom)) ? littlecom.doubleValue() : new BigDecimal("0").doubleValue());
                cell5.setCellStyle(styleDetailTableNumber);
                HSSFCell cell6 = row.createCell(6);
                BigDecimal payagent = null;
                if ("".equals(listTicketCommissionReceive.get(num).getPayagent())) {
                    payagent = new BigDecimal(0);
                } else {
                    payagent = new BigDecimal(listTicketCommissionReceive.get(num).getPayagent());
                }
                cell6.setCellValue((payagent != null) ? payagent.doubleValue() : new BigDecimal("0").doubleValue());
                cell6.setCellStyle(styleDetailTableNumber);
                HSSFCell cell7 = row.createCell(7);
                BigDecimal rcagent = null;
                if ("".equals(listTicketCommissionReceive.get(num).getRcagent())) {
                    rcagent = new BigDecimal(0);
                } else {
                    rcagent = new BigDecimal(listTicketCommissionReceive.get(num).getRcagent());
                }
                cell7.setCellValue((rcagent != null) ? rcagent.doubleValue() : new BigDecimal("0").doubleValue());
                cell7.setCellStyle(styleDetailTableNumber);
                HSSFCell cell8 = row.createCell(8);
                BigDecimal payrefund = null;
                if ("".equals(listTicketCommissionReceive.get(num).getPayrefund())) {
                    payrefund = new BigDecimal(0);
                } else {
                    payrefund = new BigDecimal(listTicketCommissionReceive.get(num).getPayrefund());
                }
                cell8.setCellValue((payrefund != null) ? payrefund.doubleValue() : new BigDecimal("0").doubleValue());
                cell8.setCellStyle(styleDetailTableNumber);
                HSSFCell cell9 = row.createCell(9);
                BigDecimal comreceive = null;
                if ("".equals(listTicketCommissionReceive.get(num).getComreceive())) {
                    comreceive = new BigDecimal(0);
                } else {
                    comreceive = new BigDecimal(listTicketCommissionReceive.get(num).getComreceive());
                }
                cell9.setCellValue((comreceive != null) ? comreceive.doubleValue() : new BigDecimal("0").doubleValue());
                cell9.setCellStyle(styleDetailTableNumber);
                sheet.autoSizeColumn(9);

                if ("I".equalsIgnoreCase(String.valueOf(listTicketCommissionReceive.get(num).getTyperounting()))) {
                    interpax = interpax.add(pax);
                    intercommairline = intercommairline.add(comair);
                    interlittlecom = interlittlecom.add(littlecom);
                    interpay = interpay.add(payagent);
                    interrc = interrc.add(rcagent);
                    interpayref = interpayref.add(payrefund);
                    intercommrec = intercommrec.add(comreceive);
                } else if ("D".equalsIgnoreCase(String.valueOf(listTicketCommissionReceive.get(num).getTyperounting()))) {
                    domesticpax = domesticpax.add(pax);
                    domesticcommairline = domesticcommairline.add(comair);
                    domesticlittlecom = domesticlittlecom.add(littlecom);
                    domesticpay = domesticpay.add(payagent);
                    domesticrc = domesticrc.add(rcagent);
                    domesticpayref = domesticpayref.add(payrefund);
                    domesticcommrec = domesticcommrec.add(comreceive);
                } else if ("C".equalsIgnoreCase(String.valueOf(listTicketCommissionReceive.get(num).getTyperounting()))) {
                    cancelpax = cancelpax.add(pax);
                    cancelcommairline = cancelcommairline.add(comair);
                    cancellittlecom = cancellittlecom.add(littlecom);
                    cancelpay = cancelpay.add(payagent);
                    cancelrc = cancelrc.add(rcagent);
                    cancelpayref = cancelpayref.add(payrefund);
                    cancelcommrec = cancelcommrec.add(comreceive);
                }

                num++;
            }
            for (int i = 0; i < listTicketCommissionReceive.size(); i++) {
                sheet.autoSizeColumn(i);
            }
        }

//        System.out.println(count);

        HSSFRow row = sheet.createRow(count);
        HSSFCell cell000Sum = row.createCell(0);
        cell000Sum.setCellValue("Total Inter");
        cell000Sum.setCellStyle(styleNameTotal);
        HSSFCell cell001Sum = row.createCell(1);
        cell001Sum.setCellValue("");
        cell001Sum.setCellStyle(styleDetailTotal);
        HSSFCell cell002Sum = row.createCell(2);
        cell002Sum.setCellValue(interpax.doubleValue());
        cell002Sum.setCellStyle(styleTotalPax);
        HSSFCell cell003Sum = row.createCell(3);
        cell003Sum.setCellValue("");
        cell003Sum.setCellStyle(styleDetailTotal);
        HSSFCell cell004Sum = row.createCell(4);
        cell004Sum.setCellValue(intercommairline.doubleValue());
        cell004Sum.setCellStyle(styleDetailTotal);
        HSSFCell cell005Sum = row.createCell(5);
        cell005Sum.setCellValue(interlittlecom.doubleValue());
        cell005Sum.setCellStyle(styleDetailTotal);
        HSSFCell cell006Sum = row.createCell(6);
        cell006Sum.setCellValue(interpay.doubleValue());
        cell006Sum.setCellStyle(styleDetailTotal);
        HSSFCell cell007Sum = row.createCell(7);
        cell007Sum.setCellValue(interrc.doubleValue());
        cell007Sum.setCellStyle(styleDetailTotal);
        HSSFCell cell008Sum = row.createCell(8);
        cell008Sum.setCellValue(interpayref.doubleValue());
        cell008Sum.setCellStyle(styleDetailTotal);
        HSSFCell cell009Sum = row.createCell(9);
        cell009Sum.setCellValue(intercommrec.doubleValue());
        cell009Sum.setCellStyle(styleDetailTotal);

        row = sheet.createRow(count + 1);
        HSSFCell cell010Sum = row.createCell(0);
        cell010Sum.setCellValue("Total Domestic");
        cell010Sum.setCellStyle(styleNameTotal);
        HSSFCell cell011Sum = row.createCell(1);
        cell011Sum.setCellValue("");
        cell011Sum.setCellStyle(styleDetailTotal);
        HSSFCell cell012Sum = row.createCell(2);
        cell012Sum.setCellValue(domesticpax.doubleValue());
        cell012Sum.setCellStyle(styleTotalPax);
        HSSFCell cell013Sum = row.createCell(3);
        cell013Sum.setCellValue("");
        cell013Sum.setCellStyle(styleDetailTotal);
        HSSFCell cell014Sum = row.createCell(4);
        cell014Sum.setCellValue(domesticcommairline.doubleValue());
        cell014Sum.setCellStyle(styleDetailTotal);
        HSSFCell cell015Sum = row.createCell(5);
        cell015Sum.setCellValue(domesticlittlecom.doubleValue());
        cell015Sum.setCellStyle(styleDetailTotal);
        HSSFCell cell016Sum = row.createCell(6);
        cell016Sum.setCellValue(domesticpay.doubleValue());
        cell016Sum.setCellStyle(styleDetailTotal);
        HSSFCell cell017Sum = row.createCell(7);
        cell017Sum.setCellValue(domesticrc.doubleValue());
        cell017Sum.setCellStyle(styleDetailTotal);
        HSSFCell cell018Sum = row.createCell(8);
        cell018Sum.setCellValue(domesticpayref.doubleValue());
        cell018Sum.setCellStyle(styleDetailTotal);
        HSSFCell cell019Sum = row.createCell(9);
        cell019Sum.setCellValue(domesticcommrec.doubleValue());
        cell019Sum.setCellStyle(styleDetailTotal);

        row = sheet.createRow(count + 2);
        HSSFCell cell020Sum = row.createCell(0);
        cell020Sum.setCellValue("Total Cancel");
        cell020Sum.setCellStyle(styleNameTotal);
        HSSFCell cell021Sum = row.createCell(1);
        cell021Sum.setCellValue("");
        cell021Sum.setCellStyle(styleDetailTotal);
        HSSFCell cell022Sum = row.createCell(2);
        cell022Sum.setCellValue(cancelpax.doubleValue());
        cell022Sum.setCellStyle(styleTotalPax);
        HSSFCell cell023Sum = row.createCell(3);
        cell023Sum.setCellValue("");
        cell023Sum.setCellStyle(styleDetailTotal);
        HSSFCell cell024Sum = row.createCell(4);
        cell024Sum.setCellValue(cancelcommairline.doubleValue());
        cell024Sum.setCellStyle(styleDetailTotal);
        HSSFCell cell025Sum = row.createCell(5);
        cell025Sum.setCellValue(cancellittlecom.doubleValue());
        cell025Sum.setCellStyle(styleDetailTotal);
        HSSFCell cell026Sum = row.createCell(6);
        cell026Sum.setCellValue(cancelpay.doubleValue());
        cell026Sum.setCellStyle(styleDetailTotal);
        HSSFCell cell027Sum = row.createCell(7);
        cell027Sum.setCellValue(cancelrc.doubleValue());
        cell027Sum.setCellStyle(styleDetailTotal);
        HSSFCell cell028Sum = row.createCell(8);
        cell028Sum.setCellValue(cancelpayref.doubleValue());
        cell028Sum.setCellStyle(styleDetailTotal);
        HSSFCell cell029Sum = row.createCell(9);
        cell029Sum.setCellValue(cancelcommrec.doubleValue());
        cell029Sum.setCellStyle(styleDetailTotal);

        String sumPax = "SUM(C" + 10 + ":C" + (count) + ")";
        String sumComair = "SUM(E" + 10 + ":E" + (count) + ")";
        String sumlitttlecom = "SUM(F" + 10 + ":F" + (count) + ")";
        String sumPayagent = "SUM(G" + 10 + ":G" + (count) + ")";
        String sumRcagent = "SUM(H" + 10 + ":H" + (count) + ")";
        String sumPayRefund = "SUM(I" + 10 + ":I" + (count) + ")";
        String sumComReceive = "SUM(J" + 10 + ":J" + (count) + ")";

        row = sheet.createRow(count + 3);
        HSSFCell cell60Sum = row.createCell(0);
        cell60Sum.setCellValue("Total");
        cell60Sum.setCellStyle(styleNameTotal);
        sheet.autoSizeColumn(0);
        HSSFCell cell6Sum = row.createCell(1);
        cell6Sum.setCellValue("");
        cell6Sum.setCellStyle(styleDetailTotal);
        sheet.autoSizeColumn(1);
        HSSFCell cell7Sum = row.createCell(2);
        cell7Sum.setCellFormula(sumPax);
        sheet.autoSizeColumn(2);
        cell7Sum.setCellStyle(styleTotalPax);
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

        HSSFRow rowLL = sheet.createRow(count + 4);
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
        styleC110Sum.setFont(excelFunction.getHeaderFont(wb.createFont()));
        cell010.setCellStyle(styleC110Sum);
        sheet1.addMergedRegion(CellRangeAddress.valueOf("A1:H1"));

        // Set align Text
        HSSFCellStyle styleC212 = wb.createCellStyle();
        styleC212.setAlignment(styleC21.ALIGN_RIGHT);
        HSSFCellStyle styleC222 = wb.createCellStyle();
        styleC222.setAlignment(styleC22.ALIGN_LEFT);

        List<TicketCommissionReceive> receiveSumTemp = listTotal.get(0).getTicketCommmissionReceiveSum();
        TicketCommissionReceive receiveSum = new TicketCommissionReceive();
        if (!receiveSumTemp.isEmpty()) {
            receiveSum = (TicketCommissionReceive) receiveSumTemp.get(0);
        }
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
        cell0322.setCellValue(receiveSum.getIssuedatePage());
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
//        HSSFCell cell652 = row62.createCell(3);
//        cell652.setCellValue("Air");
//        cell652.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet1.autoSizeColumn(3);
        HSSFCell cell662 = row62.createCell(3);
        cell662.setCellValue("Comm Airline");
        cell662.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet1.autoSizeColumn(5);
        HSSFCell cell672 = row62.createCell(4);
        cell672.setCellValue("Little Com");
        cell672.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet1.autoSizeColumn(5);
        HSSFCell cell6722 = row62.createCell(5);
        cell6722.setCellValue("Pay [Agent]");
        cell6722.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet1.autoSizeColumn(6);
        HSSFCell cell682 = row62.createCell(6);
        cell682.setCellValue("RC [Agent]");
        cell682.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet1.autoSizeColumn(7);
        HSSFCell cell692 = row62.createCell(7);
        cell692.setCellValue("Pay Refund");
        cell692.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet1.autoSizeColumn(8);
        HSSFCell cell712 = row62.createCell(8);
        cell712.setCellValue("Comm Receive");
        cell712.setCellStyle(styleAlignRightBorderAllHeaderTable);
        sheet1.autoSizeColumn(9);

        List<TicketCommissionReceive> listTicketCommissionReceiveSum = listTotal.get(0).getTicketCommmissionReceiveSum();

        int count2 = 9 + listTicketCommissionReceiveSum.size();
        int start2 = 11;
        int end2 = 0;
        int num2 = 0;

        BigDecimal interpax2 = new BigDecimal(0);
        BigDecimal intercommairline2 = new BigDecimal(0);
        BigDecimal interlittlecom2 = new BigDecimal(0);
        BigDecimal interpay2 = new BigDecimal(0);
        BigDecimal interrc2 = new BigDecimal(0);
        BigDecimal interpayref2 = new BigDecimal(0);
        BigDecimal intercommrec2 = new BigDecimal(0);

        BigDecimal domesticpax2 = new BigDecimal(0);
        BigDecimal domesticcommairline2 = new BigDecimal(0);
        BigDecimal domesticlittlecom2 = new BigDecimal(0);
        BigDecimal domesticpay2 = new BigDecimal(0);
        BigDecimal domesticrc2 = new BigDecimal(0);
        BigDecimal domesticpayref2 = new BigDecimal(0);
        BigDecimal domesticcommrec2 = new BigDecimal(0);

        BigDecimal cancelpax2 = new BigDecimal(0);
        BigDecimal cancelcommairline2 = new BigDecimal(0);
        BigDecimal cancellittlecom2 = new BigDecimal(0);
        BigDecimal cancelpay2 = new BigDecimal(0);
        BigDecimal cancelrc2 = new BigDecimal(0);
        BigDecimal cancelpayref2 = new BigDecimal(0);
        BigDecimal cancelcommrec2 = new BigDecimal(0);
//        System.out.println("Size : " + listTicketCommissionReceiveSum.size());
        for (int r = 9; r < count2; r++) {
            if (num2 < listTicketCommissionReceiveSum.size()) {
                HSSFRow row2 = sheet1.createRow(r);
                HSSFCell cell1 = row2.createCell(0);
                cell1.setCellValue(listTicketCommissionReceiveSum.get(num2).getTypepayment());
                cell1.setCellStyle(styleDetailTable);
                HSSFCell cell2 = row2.createCell(1);
                cell2.setCellValue(listTicketCommissionReceiveSum.get(num2).getTyperounting());
                cell2.setCellStyle(styleDetailTable);
                HSSFCell cell3 = row2.createCell(2);
                BigDecimal pax = new BigDecimal(listTicketCommissionReceiveSum.get(num2).getPax());
                cell3.setCellValue((pax != null) ? pax.doubleValue() : new BigDecimal("0").doubleValue());
                cell3.setCellStyle(styleDetailPax);
//                HSSFCell cell4 = row2.createCell(3);
//                    cell4.setCellValue(listTicketCommissionReceiveSum.get(num2).getAir());
//                    cell4.setCellStyle(styleDetailTable);
                HSSFCell cell55 = row2.createCell(3);
                BigDecimal comair = null;
                if ("".equals(listTicketCommissionReceiveSum.get(num2).getComairline())) {
                    comair = new BigDecimal(0);
                } else {
                    comair = new BigDecimal(listTicketCommissionReceiveSum.get(num2).getComairline());
                }
                cell55.setCellValue((comair != null && !"0".equals(comair)) ? comair.doubleValue() : new BigDecimal("0").doubleValue());
                cell55.setCellStyle(styleDetailTableNumber);
                HSSFCell cell5 = row2.createCell(4);
                BigDecimal littlecom = null;
                if ("".equals(listTicketCommissionReceiveSum.get(num2).getLittlecom())) {
                    littlecom = new BigDecimal(0);
                } else {
                    littlecom = new BigDecimal(listTicketCommissionReceiveSum.get(num2).getLittlecom());
                }
                cell5.setCellValue((littlecom != null && !"0".equals(littlecom)) ? littlecom.doubleValue() : new BigDecimal("0").doubleValue());
                cell5.setCellStyle(styleDetailTableNumber);
                HSSFCell cell6 = row2.createCell(5);
                BigDecimal payagent = null;
                if ("".equals(listTicketCommissionReceiveSum.get(num2).getPayagent())) {
                    payagent = new BigDecimal(0);
                } else {
                    payagent = new BigDecimal(listTicketCommissionReceiveSum.get(num2).getPayagent());
                }
                cell6.setCellValue((payagent != null) ? payagent.doubleValue() : new BigDecimal("0").doubleValue());
                cell6.setCellStyle(styleDetailTableNumber);
                HSSFCell cell7 = row2.createCell(6);
                BigDecimal rcagent = null;
                if ("".equals(listTicketCommissionReceiveSum.get(num2).getRcagent())) {
                    rcagent = new BigDecimal(0);
                } else {
                    rcagent = new BigDecimal(listTicketCommissionReceiveSum.get(num2).getRcagent());
                }
                cell7.setCellValue((rcagent != null) ? rcagent.doubleValue() : new BigDecimal("0").doubleValue());
                cell7.setCellStyle(styleDetailTableNumber);
                HSSFCell cell8 = row2.createCell(7);
                BigDecimal payrefund = null;
                if ("".equals(listTicketCommissionReceiveSum.get(num2).getPayrefund())) {
                    payrefund = new BigDecimal(0);
                } else {
                    payrefund = new BigDecimal(listTicketCommissionReceiveSum.get(num2).getPayrefund());
                }
                cell8.setCellValue((payrefund != null) ? payrefund.doubleValue() : new BigDecimal("0").doubleValue());
                cell8.setCellStyle(styleDetailTableNumber);
                HSSFCell cell9 = row2.createCell(8);
                BigDecimal comreceive = null;
                if ("".equals(listTicketCommissionReceiveSum.get(num2).getComreceive())) {
                    comreceive = new BigDecimal(0);
                } else {
                    comreceive = new BigDecimal(listTicketCommissionReceiveSum.get(num2).getComreceive());
                }
                cell9.setCellValue((comreceive != null) ? comreceive.doubleValue() : new BigDecimal("0").doubleValue());
                cell9.setCellStyle(styleDetailTableNumber);
                sheet1.autoSizeColumn(8);

                if ("I".equalsIgnoreCase(String.valueOf(listTicketCommissionReceiveSum.get(num2).getTyperounting()))) {
                    interpax2 = interpax2.add(pax);
                    intercommairline2 = intercommairline2.add(comair);
                    interlittlecom2 = interlittlecom2.add(littlecom);
                    interpay2 = interpay2.add(payagent);
                    interrc2 = interrc2.add(rcagent);
                    interpayref2 = interpayref2.add(payrefund);
                    intercommrec2 = intercommrec2.add(comreceive);
                } else if ("D".equalsIgnoreCase(String.valueOf(listTicketCommissionReceiveSum.get(num2).getTyperounting()))) {
                    domesticpax2 = domesticpax2.add(pax);
                    domesticcommairline2 = domesticcommairline2.add(comair);
                    domesticlittlecom2 = domesticlittlecom2.add(littlecom);
                    domesticpay2 = domesticpay2.add(payagent);
                    domesticrc2 = domesticrc2.add(rcagent);
                    domesticpayref2 = domesticpayref2.add(payrefund);
                    domesticcommrec2 = domesticcommrec2.add(comreceive);
                } else if ("C".equalsIgnoreCase(String.valueOf(listTicketCommissionReceiveSum.get(num2).getTyperounting()))) {
                    cancelpax2 = cancelpax2.add(pax);
                    cancelcommairline2 = cancelcommairline2.add(comair);
                    cancellittlecom2 = cancellittlecom2.add(littlecom);
                    cancelpay2 = cancelpay2.add(payagent);
                    cancelrc2 = cancelrc2.add(rcagent);
                    cancelpayref2 = cancelpayref2.add(payrefund);
                    cancelcommrec2 = cancelcommrec2.add(comreceive);
                }

                num2++;
            }
            for (int i = 0; i < listTicketCommissionReceiveSum.size(); i++) {
                sheet1.autoSizeColumn(i);
            }
        }
//        System.out.println(count2);
//        HSSFRow rowL2 = sheet1.createRow(count2);
//        rowL2.createCell(0).setCellStyle(styleDetailTableBorderBottomTop);
//        rowL2.createCell(1).setCellStyle(styleDetailTableBorderBottomTop);
//        rowL2.createCell(2).setCellStyle(styleDetailTableBorderBottomTop);
//        rowL2.createCell(3).setCellStyle(styleDetailTableBorderBottomTop);
//        rowL2.createCell(4).setCellStyle(styleDetailTableBorderBottomTop);
//        rowL2.createCell(5).setCellStyle(styleDetailTableBorderBottomTop);
//        rowL2.createCell(6).setCellStyle(styleDetailTableBorderBottomTop);
//        rowL2.createCell(7).setCellStyle(styleDetailTableBorderBottomTop);
//        rowL2.createCell(8).setCellStyle(styleDetailTableBorderBottomTop);
//        rowL2.createCell(9).setCellStyle(styleDetailTableBorderBottomTop);
        HSSFRow rowS2 = sheet1.createRow(count2);
        HSSFCell s2cell000Sum = rowS2.createCell(0);
        s2cell000Sum.setCellValue("Total Inter");
        s2cell000Sum.setCellStyle(styleNameTotal);
        HSSFCell s2cell001Sum = rowS2.createCell(1);
        s2cell001Sum.setCellValue("");
        s2cell001Sum.setCellStyle(styleDetailTotal);
        HSSFCell s2cell002Sum = rowS2.createCell(2);
        s2cell002Sum.setCellValue(interpax2.doubleValue());
        s2cell002Sum.setCellStyle(styleTotalPax);
//        HSSFCell s2cell003Sum = rowS2.createCell(3);
//        s2cell003Sum.setCellValue("");
//        s2cell003Sum.setCellStyle(styleDetailTotal);
        HSSFCell s2cell004Sum = rowS2.createCell(3);
        s2cell004Sum.setCellValue(intercommairline2.doubleValue());
        s2cell004Sum.setCellStyle(styleDetailTotal);
        HSSFCell s2cell005Sum = rowS2.createCell(4);
        s2cell005Sum.setCellValue(interlittlecom2.doubleValue());
        s2cell005Sum.setCellStyle(styleDetailTotal);
        HSSFCell s2cell006Sum = rowS2.createCell(5);
        s2cell006Sum.setCellValue(interpay2.doubleValue());
        s2cell006Sum.setCellStyle(styleDetailTotal);
        HSSFCell s2cell007Sum = rowS2.createCell(6);
        s2cell007Sum.setCellValue(interrc2.doubleValue());
        s2cell007Sum.setCellStyle(styleDetailTotal);
        HSSFCell s2cell008Sum = rowS2.createCell(7);
        s2cell008Sum.setCellValue(interpayref2.doubleValue());
        s2cell008Sum.setCellStyle(styleDetailTotal);
        HSSFCell s2cell009Sum = rowS2.createCell(8);
        s2cell009Sum.setCellValue(intercommrec2.doubleValue());
        s2cell009Sum.setCellStyle(styleDetailTotal);

        rowS2 = sheet1.createRow(count2 + 1);
        HSSFCell s2cell010Sum = rowS2.createCell(0);
        s2cell010Sum.setCellValue("Total Domestic");
        s2cell010Sum.setCellStyle(styleNameTotal);
        HSSFCell s2cell011Sum = rowS2.createCell(1);
        s2cell011Sum.setCellValue("");
        s2cell011Sum.setCellStyle(styleDetailTotal);
        HSSFCell s2cell012Sum = rowS2.createCell(2);
        s2cell012Sum.setCellValue(domesticpax2.doubleValue());
        s2cell012Sum.setCellStyle(styleTotalPax);
//        HSSFCell s2cell013Sum = rowS2.createCell(3);
//        s2cell013Sum.setCellValue("");
//        s2cell013Sum.setCellStyle(styleDetailTotal);
        HSSFCell s2cell014Sum = rowS2.createCell(3);
        s2cell014Sum.setCellValue(domesticcommairline2.doubleValue());
        s2cell014Sum.setCellStyle(styleDetailTotal);
        HSSFCell s2cell015Sum = rowS2.createCell(4);
        s2cell015Sum.setCellValue(domesticlittlecom2.doubleValue());
        s2cell015Sum.setCellStyle(styleDetailTotal);
        HSSFCell s2cell016Sum = rowS2.createCell(5);
        s2cell016Sum.setCellValue(domesticpay2.doubleValue());
        s2cell016Sum.setCellStyle(styleDetailTotal);
        HSSFCell s2cell017Sum = rowS2.createCell(6);
        s2cell017Sum.setCellValue(domesticrc2.doubleValue());
        s2cell017Sum.setCellStyle(styleDetailTotal);
        HSSFCell s2cell018Sum = rowS2.createCell(7);
        s2cell018Sum.setCellValue(domesticpayref2.doubleValue());
        s2cell018Sum.setCellStyle(styleDetailTotal);
        HSSFCell s2cell019Sum = rowS2.createCell(8);
        s2cell019Sum.setCellValue(domesticcommrec2.doubleValue());
        s2cell019Sum.setCellStyle(styleDetailTotal);

        rowS2 = sheet1.createRow(count2 + 2);
        HSSFCell s2cell020Sum = rowS2.createCell(0);
        s2cell020Sum.setCellValue("Total Cancel");
        s2cell020Sum.setCellStyle(styleNameTotal);
        HSSFCell s2cell021Sum = rowS2.createCell(1);
        s2cell021Sum.setCellValue("");
        s2cell021Sum.setCellStyle(styleDetailTotal);
        HSSFCell s2cell022Sum = rowS2.createCell(2);
        s2cell022Sum.setCellValue(cancelpax2.doubleValue());
        s2cell022Sum.setCellStyle(styleTotalPax);
//        HSSFCell s2cell023Sum = rowS2.createCell(3);
//        s2cell023Sum.setCellValue("");
//        s2cell023Sum.setCellStyle(styleDetailTotal);
        HSSFCell s2cell024Sum = rowS2.createCell(3);
        s2cell024Sum.setCellValue(cancelcommairline2.doubleValue());
        s2cell024Sum.setCellStyle(styleDetailTotal);
        HSSFCell s2cell025Sum = rowS2.createCell(4);
        s2cell025Sum.setCellValue(cancellittlecom2.doubleValue());
        s2cell025Sum.setCellStyle(styleDetailTotal);
        HSSFCell s2cell026Sum = rowS2.createCell(5);
        s2cell026Sum.setCellValue(cancelpay2.doubleValue());
        s2cell026Sum.setCellStyle(styleDetailTotal);
        HSSFCell s2cell027Sum = rowS2.createCell(6);
        s2cell027Sum.setCellValue(cancelrc2.doubleValue());
        s2cell027Sum.setCellStyle(styleDetailTotal);
        HSSFCell s2cell028Sum = rowS2.createCell(7);
        s2cell028Sum.setCellValue(cancelpayref2.doubleValue());
        s2cell028Sum.setCellStyle(styleDetailTotal);
        HSSFCell s2cell029Sum = rowS2.createCell(8);
        s2cell029Sum.setCellValue(cancelcommrec2.doubleValue());
        s2cell029Sum.setCellStyle(styleDetailTotal);

        String sumPax2 = "SUM(C" + 10 + ":C" + (count2) + ")";
        String sumComair2 = "SUM(D" + 10 + ":D" + (count2) + ")";
        String sumlitttlecom2 = "SUM(E" + 10 + ":E" + (count2) + ")";
        String sumPayagent2 = "SUM(F" + 10 + ":F" + (count2) + ")";
        String sumRcagent2 = "SUM(G" + 10 + ":G" + (count2) + ")";
        String sumPayRefund2 = "SUM(H" + 10 + ":H" + (count2) + ")";
        String sumComReceive2 = "SUM(I" + 10 + ":I" + (count2) + ")";

        HSSFRow row20 = sheet1.createRow(count2 + 3);
        HSSFCell cell60Sum2 = row20.createCell(0);
        cell60Sum2.setCellValue("Total");
        cell60Sum2.setCellStyle(styleNameTotal);
        sheet1.autoSizeColumn(0);
        HSSFCell cell6Sum2 = row20.createCell(1);
        cell6Sum2.setCellValue("");
        cell6Sum2.setCellStyle(styleDetailTableNumber);
        sheet1.autoSizeColumn(1);
        HSSFCell cell7Sum2 = row20.createCell(2);
        cell7Sum2.setCellFormula(sumPax2);
        sheet1.autoSizeColumn(2);
        cell7Sum2.setCellStyle(styleTotalPax);
//        HSSFCell cell8Sum2 = row20.createCell(3);
//            cell8Sum2.setCellValue("");
//            sheet1.autoSizeColumn(3);
//            cell8Sum2.setCellStyle(styleDetailTableNumber);
        HSSFCell cell9Sum2 = row20.createCell(3);
        cell9Sum2.setCellFormula(sumComair2);
        cell9Sum2.setCellStyle(styleDetailTableNumber);
        sheet1.autoSizeColumn(3);
        HSSFCell cell10Sum2 = row20.createCell(4);
        cell10Sum2.setCellFormula(sumlitttlecom2);
        cell10Sum2.setCellStyle(styleDetailTableNumber);
        sheet1.autoSizeColumn(4);
        HSSFCell cell11Sum2 = row20.createCell(5);
        cell11Sum2.setCellFormula(sumPayagent2);
        cell11Sum2.setCellStyle(styleDetailTableNumber);
        sheet1.autoSizeColumn(5);
        HSSFCell cell12Sum2 = row20.createCell(6);
        cell12Sum2.setCellFormula(sumRcagent2);
        cell12Sum2.setCellStyle(styleDetailTableNumber);
        sheet1.autoSizeColumn(6);
        HSSFCell cell13Sum2 = row20.createCell(7);
        cell13Sum2.setCellFormula(sumPayRefund2);
        cell13Sum2.setCellStyle(styleDetailTableNumber);
        sheet1.autoSizeColumn(7);
        HSSFCell cell14Sum2 = row20.createCell(8);
        cell14Sum2.setCellFormula(sumComReceive2);
        cell14Sum2.setCellStyle(styleDetailTableNumber);
        sheet1.autoSizeColumn(8);

        HSSFRow rowLL2 = sheet1.createRow(count2 + 4);
        rowLL2.createCell(0).setCellStyle(styleBorderTop);
        rowLL2.createCell(1).setCellStyle(styleBorderTop);
        rowLL2.createCell(2).setCellStyle(styleBorderTop);
        rowLL2.createCell(3).setCellStyle(styleBorderTop);
        rowLL2.createCell(4).setCellStyle(styleBorderTop);
        rowLL2.createCell(5).setCellStyle(styleBorderTop);
        rowLL2.createCell(6).setCellStyle(styleBorderTop);
        rowLL2.createCell(7).setCellStyle(styleBorderTop);
        rowLL2.createCell(8).setCellStyle(styleBorderTop);
//        rowLL2.createCell(9).setCellStyle(styleBorderTop);

        sheet1.addMergedRegion(CellRangeAddress.valueOf("A" + (count2 + 1) + ":B" + (count2 + 1)));
        sheet1.addMergedRegion(CellRangeAddress.valueOf("A" + (count2 + 2) + ":B" + (count2 + 2)));
        sheet1.addMergedRegion(CellRangeAddress.valueOf("A" + (count2 + 3) + ":B" + (count2 + 3)));
        sheet1.addMergedRegion(CellRangeAddress.valueOf("A" + (count2 + 4) + ":B" + (count2 + 4)));

        sheet.addMergedRegion(CellRangeAddress.valueOf("A" + (count + 1) + ":B" + (count + 1)));
        sheet.addMergedRegion(CellRangeAddress.valueOf("A" + (count + 2) + ":B" + (count + 2)));
        sheet.addMergedRegion(CellRangeAddress.valueOf("A" + (count + 3) + ":B" + (count + 3)));
        sheet.addMergedRegion(CellRangeAddress.valueOf("A" + (count + 4) + ":B" + (count + 4)));
    }

}
