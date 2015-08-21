/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller.report;

import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.report.model.AgentCommission;
import com.smi.travel.datalayer.report.model.GuideCommissionInfo;
import com.smi.travel.datalayer.report.model.TicketOrder;
import com.smi.travel.datalayer.service.ReportService;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;


/**
 *
 * @author Surachai
 */
public class ReportController extends SMITravelController {

    private static final String TestingPDF = "TestingReport";
    private static final String TicketOrder = "TicketOrder";
    private static final String HotelVoucher = "HotelVoucher";
    private static final String HotelInboundVoucher = "HotelInoundVoucher";
    private static final String HotelVoucherEmail = "HotelVoucherEmail";
    private static final String HotelVoucherEmailAgent = "HotelVoucherEmailAgent";
    private static final String LandVoucher = "LandVoucher";
    private static final String LandVoucherEmail = "LandVoucherEmail";
    private static final String LandVoucherEmailAgent = "LandVoucherEmailAgent";
    private static final String TicketSummary = "TicketSummary";
    private static final String StaffSummary = "StaffSummary";
    private static final String AirlineSummary = "AirlineSummary";
    private static final String ReportName = "name";
    private static final String TicketSaleSummary = "TicketSaleSummary";
    private static final String TicketProfitSummary = "TicketProfitSummary";
    private static final String InvoiceSummary = "InvoiceSummary";
    private static final String GuideJob = "GuideJob";
    private static final String TransferJob = "TransferJob";
    private static final String GuideCommission = "GuideCommission";
    private static final String AgentCommissionInfo = "AgentCommissionInfo";
    private static final String AgentCommissionSummary = "AgentCommissionSummary";
    private static final String AgentCommission = "AgentCommission";
    private static final String DaytourOther = "DaytourOther";
    private static final String OtherVouncherEmail = "otherVouncherEmail";
    private static final String ReceiptEmail = "ReceiptEmail";
    private static final String ReceiptReport = "ReceiptReport";
    private static final String ReceiveList = "ReceiveList";
    private static final String InvoiceEmail = "InvoiceEmail";
    private static final String InvoiceReport = "InvoiceReport";
    private static final String TaxInvoiceReport = "TaxInvoiceReport";
    private static final String TaxInvoiceEmailReport = "TaxInvoiceEmailReport";

    private DataSource datasource;
    private static final Logger LOG = Logger.getLogger(ReportController.class.getName());
    private ReportService reportservice;

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        LOG.info("ReportController process");
        UtilityFunction util = new UtilityFunction();
        String name = request.getParameter(ReportName);
        String hotelID = request.getParameter("hotelID");
        String refno = request.getParameter("refno");
        String pnrID = request.getParameter("pnrID");
        String ticketfrom = request.getParameter("ticketfrom");
        String tickettype = request.getParameter("tickettype");
        String startdate = request.getParameter("startdate");
        String enddate = request.getParameter("enddate");
        String billto = request.getParameter("billto");
        String passenger = request.getParameter("passenger");
        String docno = request.getParameter("docno");
        String tourDate = request.getParameter("tourdate");
        String tourCode = request.getParameter("tourcode");
        String guideID = request.getParameter("GuideID");
        String agentid = request.getParameter("agentID");
        String landId = request.getParameter("landId");
        SystemUser user = (SystemUser) session.getAttribute("USER");
        String status = request.getParameter("comfirm");
        String receiveId = request.getParameter("receiveId");
        String receiveNo = request.getParameter("receiveNo");
        String taxInvId = request.getParameter("taxInvId");
        String department = request.getParameter("department");
        String optionPrint = request.getParameter("optionPrint");
        int option = Integer.parseInt(optionPrint);

        Map model = new HashMap();
        List data = new ArrayList();
        int PrintMethod = 0; // 0 = bean 1 = pass parameter
        if (HotelVoucher.equalsIgnoreCase(name)) {
            PrintMethod = 0;
            data = reportservice.getHotelVoucher(hotelID, user.getName());
        } else if (HotelInboundVoucher.equalsIgnoreCase(name)) {
            PrintMethod = 0;
            data = reportservice.getHotelInboundVoucher(hotelID);
        } else if (HotelVoucherEmail.equalsIgnoreCase(name)) {
            PrintMethod = 0;
            data = reportservice.getHotelVoucher(hotelID, user.getName());
        } else if (HotelVoucherEmailAgent.equalsIgnoreCase(name)) {
            PrintMethod = 0;
            data = reportservice.getHotelVoucher(hotelID, user.getName());
        } else if (LandVoucher.equalsIgnoreCase(name)) {
            PrintMethod = 0;
            data = reportservice.getLandVoucher(refno, user.getName(), landId);
        } else if (LandVoucherEmailAgent.equalsIgnoreCase(name)) {
            PrintMethod = 0;
            data = reportservice.getLandVoucher(refno, user.getName(), landId);
        } else if (LandVoucherEmail.equalsIgnoreCase(name)) {
            PrintMethod = 0;
            data = reportservice.getLandVoucher(refno, user.getName(), landId);
        } else if (TicketOrder.equalsIgnoreCase(name)) {
            PrintMethod = 0;
            data = reportservice.getTicketOrde(refno, pnrID);
            // set path for loading sub-report file
            ((TicketOrder) data.get(0)).setSubReportDir(getServletContext().getRealPath("/WEB-INF/report/"));
        } else if (TicketSummary.equalsIgnoreCase(name)) {
            PrintMethod = 0;
            data = reportservice.getTicketSummary(ticketfrom, tickettype, startdate, enddate, billto, passenger, user.getName());
        } else if (AirlineSummary.equalsIgnoreCase(name)) {
            PrintMethod = 0;
            data = reportservice.getAirlineSummary(ticketfrom, tickettype, startdate, enddate, user.getName());
        } else if (StaffSummary.equalsIgnoreCase(name)) {
            PrintMethod = 0;
            data = reportservice.getStaffSummary(ticketfrom, tickettype, startdate, enddate, user.getName());
        } else if (TicketSaleSummary.equalsIgnoreCase(name)) {
            PrintMethod = 0;
            data = reportservice.getTicketSaleVolumn(ticketfrom, tickettype, startdate, enddate);
        } else if (TicketProfitSummary.equalsIgnoreCase(name)) {
            PrintMethod = 0;
            data = reportservice.getTicketProfitVolumn(ticketfrom, tickettype, startdate, enddate);
        } else if (InvoiceSummary.equalsIgnoreCase(name)) {
            PrintMethod = 0;
            data = reportservice.getInvoiceSummary(ticketfrom, tickettype, startdate, enddate);
        } else if (GuideJob.equalsIgnoreCase(name)) {
            PrintMethod = 0;
            data = reportservice.getGuildJobReport(tourDate, tourCode, user.getName());
        } else if (TransferJob.equalsIgnoreCase(name)) {
            PrintMethod = 0;
            data = reportservice.getTransferJobReport(docno);
        } else if (GuideCommission.equalsIgnoreCase(name)) {
            PrintMethod = 0;
            data = reportservice.getGuideCommissionInfoReport(startdate, enddate, user.getName(), guideID);
            // set path for loading sub-report file
            ((GuideCommissionInfo) data.get(0)).setSubReportDir(getServletContext().getRealPath("/WEB-INF/report/"));
        } else if (AgentCommissionSummary.equalsIgnoreCase(name)) {
            PrintMethod = 0;
            data = reportservice.getAgentCommissionReportSummary(startdate, enddate, user.getName(), agentid);
        } else if (AgentCommission.equalsIgnoreCase(name)) {
            PrintMethod = 0;
            data = reportservice.getAgentCommissionReport(startdate, enddate, user.getName(), agentid);
            // set path for loading sub-report file
            ((AgentCommission) data.get(0)).setSubReportDir(getServletContext().getRealPath("/WEB-INF/report/"));
        } else if (AgentCommissionInfo.equalsIgnoreCase(name)) {
            PrintMethod = 0;
            data = reportservice.getAgentCommissionReportInfo(startdate, enddate, user.getName(), agentid);
        } else if (DaytourOther.equalsIgnoreCase(name)) {
            PrintMethod = 0;
            data = reportservice.getDaytourOtherReport(refno, status);
        } else if (OtherVouncherEmail.equalsIgnoreCase(name)) {
            PrintMethod = 0;
            data = reportservice.getDaytourOtherReport(refno, status);
        } else if (ReceiptEmail.equalsIgnoreCase(name)) {
            PrintMethod = 0;
            data = reportservice.getReceiptEmail(receiveId,receiveNo,option);
        } else if (ReceiptReport.equalsIgnoreCase(name)) {
            PrintMethod = 0;
            data = reportservice.getReceipt(receiveId,receiveNo,option);
        } else if (ReceiveList.equalsIgnoreCase(name)) {
            PrintMethod = 0;
            data = reportservice.getDaytourOtherReport(refno, status);
        } else if (InvoiceEmail.equalsIgnoreCase(name)) {
            PrintMethod = 0;
            data = reportservice.getInvoiceEmail();
        } else if (InvoiceReport.equalsIgnoreCase(name)) {
            PrintMethod = 1;
            data = reportservice.getInvoice();
        } else if (TaxInvoiceReport.equalsIgnoreCase(name)) {
            PrintMethod = 0;
            data = reportservice.getTaxInvoice();
        } else if (TaxInvoiceEmailReport.equalsIgnoreCase(name)) {
            PrintMethod = 0;
            data = reportservice.getTaxInvoiceEmail();
        }

        JRDataSource dataSource = new JRBeanCollectionDataSource(data);

        if (PrintMethod == 0) { // user bean method
            model.put("JRDataSource", dataSource);
        } else if (PrintMethod == 1) { // user pass parameter method
            try {
                model.put("REPORT_CONNECTION", datasource.getConnection());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            String jasperFileName = "InvoiceReport.jasper";
            String pdfFileName = "C1_report.pdf";
            reportservice.printreport(jasperFileName, pdfFileName, dataSource);
           

        }

        return new ModelAndView(name, model);
    }

    public ReportService getReportservice() {
        return reportservice;
    }

    public void setReportservice(ReportService reportservice) {
        this.reportservice = reportservice;
    }

    public DataSource getDatasource() {
        return datasource;
    }

    public void setDatasource(DataSource datasource) {
        this.datasource = datasource;
    }
}
