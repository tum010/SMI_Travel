/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller.excel;

import com.smi.travel.datalayer.entity.MDefaultData;
import com.smi.travel.datalayer.report.model.BillAirAgent;
import com.smi.travel.datalayer.report.model.TicketFareReport;
import com.smi.travel.datalayer.report.model.TicketFareSummaryByAgentStaff;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.APNirvana;
import com.smi.travel.datalayer.view.entity.ARNirvana;
import com.smi.travel.datalayer.view.entity.BillAirAgentRefund;
import com.smi.travel.datalayer.view.entity.CollectionNirvana;
import com.smi.travel.datalayer.view.entity.ListBillAirAgent;
import com.smi.travel.datalayer.view.entity.ListSummaryTicketAdjustCostAndIncome;
import com.smi.travel.datalayer.view.entity.ListTicketCommissionReceive;
import com.smi.travel.datalayer.view.entity.ListTicketSummaryCommission;
import com.smi.travel.datalayer.view.entity.OutputTaxView;
import com.smi.travel.datalayer.view.entity.RefundTicketView;
import com.smi.travel.datalayer.view.entity.SummaryAirline;
import com.smi.travel.datalayer.view.entity.SummaryAirlinePaxView;
import com.smi.travel.datalayer.view.entity.SummaryTicketAdjustCostAndIncome;
import com.smi.travel.datalayer.view.entity.SummaryTicketCostAndIncomeView;
import com.smi.travel.datalayer.view.entity.TicketCommissionReceive;
import com.smi.travel.datalayer.view.entity.TicketProfitLoss;
import com.smi.travel.datalayer.view.entity.TicketSummaryAirlineView;
import com.smi.travel.datalayer.view.entity.TicketSummaryCommissionView;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.servlet.view.document.AbstractExcelView;

/**
 *
 * @author Jittima
 */
public class ExportDataToExcelView extends AbstractExcelView {
    private UtilityService utilityService;
    
    @Override
    protected void buildExcelDocument(Map model, HSSFWorkbook workbook,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String name = (String) model.get("name");
        System.out.println("name : " + name);
        response.setHeader("Content-disposition", "attachment; filename=" + name + ".xls");

    }

    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }
     
    
}