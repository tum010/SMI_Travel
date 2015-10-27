/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller.report;

import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.report.model.AgentCommission;
import com.smi.travel.datalayer.report.model.DailyTourReport;
import com.smi.travel.datalayer.report.model.GuideCommissionInfo;
import com.smi.travel.datalayer.report.model.OtherMonthlyReport;
import com.smi.travel.datalayer.report.model.PaymentAirline;
import com.smi.travel.datalayer.report.model.TicketOrder;
import com.smi.travel.datalayer.service.ReportService;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
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
    private static final String ReceiptSummaryReport = "ReceiptSummaryReport";
    private static final String ReceiveList = "ReceiveList";
    private static final String InvoiceEmail = "InvoiceEmail";
    private static final String InvoiceReport = "InvoiceReport";
    private static final String InvoiceTemp = "InvoiceTemp";
    private static final String InvoiceTempReport = "InvoiceTempReport";
    private static final String TaxInvoiceReport = "TaxInvoiceReport";
    private static final String TaxInvoiceEmailReport = "TaxInvoiceEmailReport";
    private static final String CreditNoteReport = "CreditNoteReport";
    private static final String InvoiceMonthly = "InvoiceMonthlyReport";
    private static final String RefundAirReport = "RefundAirReport";
    private static final String TicketFareReport = "TicketFareReport";
    private static final String TaxInvoiceSummaryReport = "TaxInvoiceSummaryReport";
    private static final String CreditNoteSummaryReport = "CreditNoteSummaryReport";
    private static final String PaymentAirlineInfo = "PaymentAirlineInfo";
    private static final String PaymentAirlineListReport = "PaymentAirlineListReport";
    private static final String PaymentTourHotelSummary = "PaymentTourHotelSummary";
    private static final String HotelSummary = "HotelSummary";
    private static final String HotelMonthlyReport = "HotelMonthlyReport";
    private static final String HotelMonthlyDetailReport = "HotelMonthlyDetailReport";
    
    private static final String OtherMonthlyReport = "OtherMonthlyReport"; // other
    private static final String DailyTourReport = "DailyTourReport";
    
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
        String bankid = request.getParameter("bankid");
        String invoiceid = request.getParameter("invoiceid");
        String optionPrint = request.getParameter("optionPrint");
        String showStaff  = request.getParameter("showstaff");
        String showLeader = request.getParameter("showleader");
        String cnid = request.getParameter("cnid");
        int option = Integer.parseInt(optionPrint == null ? "0":optionPrint);
        
        String BillFrom = request.getParameter("billFromName");
        String ClientTo = request.getParameter("clientCode");
        String ClientName = request.getParameter("clientName");
        String Payment  = request.getParameter("payment");
        String Accno  = request.getParameter("accNo");
        String vattype  = request.getParameter("vatType");
        String from  = request.getParameter("fromdate");
        String to  = request.getParameter("todate");
        String departmentInvoice  = request.getParameter("departmentInvoice");
        String sign = request.getParameter("sign");
        String refundId = request.getParameter("refundId");
        String typeInvoice = request.getParameter("type");
        String agent = request.getParameter("agent");
        String systemuser = request.getParameter("systemuser");
        String dateFrom = request.getParameter("dateFrom");
        String dateTo = request.getParameter("dateTo");
        String departmentRec = request.getParameter("departmentRec");
        String recType = request.getParameter("recType");
        String statusInvoice = request.getParameter("status");
        String pvtype = request.getParameter("pvtype");
        String invSupCode = request.getParameter("invSupCode");
        
        //Hotel Summary
        String fromHotelSummary = request.getParameter("fromdate");
        String toHotelSummary = request.getParameter("todate");
        String departmentHotelSummary = request.getParameter("department");
        String detailHotelMonthly = request.getParameter("detail");
        
        Map model = new HashMap();
        List data = new ArrayList();
        int PrintMethod = 0; // 0 = bean 1 = pass parameter
        System.out.println("report");
        if (HotelVoucher.equalsIgnoreCase(name)) {
            data = reportservice.getHotelVoucher(hotelID, user.getName());
        } else if (HotelInboundVoucher.equalsIgnoreCase(name)) {
            data = reportservice.getHotelInboundVoucher(hotelID);
        } else if (HotelVoucherEmail.equalsIgnoreCase(name)) {
            data = reportservice.getHotelVoucher(hotelID, user.getName());
        } else if (HotelVoucherEmailAgent.equalsIgnoreCase(name)) {
            data = reportservice.getHotelVoucher(hotelID, user.getName());
        } else if (LandVoucher.equalsIgnoreCase(name)) {
            data = reportservice.getLandVoucher(refno, user.getName(), landId);
        } else if (LandVoucherEmailAgent.equalsIgnoreCase(name)) {
            data = reportservice.getLandVoucher(refno, user.getName(), landId);
        } else if (LandVoucherEmail.equalsIgnoreCase(name)) {
            data = reportservice.getLandVoucher(refno, user.getName(), landId);
        } else if (TicketOrder.equalsIgnoreCase(name)) {
            data = reportservice.getTicketOrde(refno, pnrID);
            // set path for loading sub-report file
            ((TicketOrder) data.get(0)).setSubReportDir(getServletContext().getRealPath("/WEB-INF/report/"));
        } else if (TicketSummary.equalsIgnoreCase(name)) {
            data = reportservice.getTicketSummary(ticketfrom, tickettype, startdate, enddate, billto, passenger, user.getName());
        } else if (AirlineSummary.equalsIgnoreCase(name)) {
            data = reportservice.getAirlineSummary(ticketfrom, tickettype, startdate, enddate, user.getName());
        } else if (StaffSummary.equalsIgnoreCase(name)) {
            data = reportservice.getStaffSummary(ticketfrom, tickettype, startdate, enddate, user.getName());
        } else if (TicketSaleSummary.equalsIgnoreCase(name)) {
            data = reportservice.getTicketSaleVolumn(ticketfrom, tickettype, startdate, enddate);
        } else if (TicketProfitSummary.equalsIgnoreCase(name)) {
            data = reportservice.getTicketProfitVolumn(ticketfrom, tickettype, startdate, enddate);
        } else if (InvoiceSummary.equalsIgnoreCase(name)) {
            data = reportservice.getInvoiceSummary(from, to, department, typeInvoice,agent,statusInvoice);
        } else if (GuideJob.equalsIgnoreCase(name)) {
            data = reportservice.getGuildJobReport(tourDate, tourCode, user.getName());
        } else if (TransferJob.equalsIgnoreCase(name)) {
            data = reportservice.getTransferJobReport(docno);
        } else if (GuideCommission.equalsIgnoreCase(name)) {
            data = reportservice.getGuideCommissionInfoReport(startdate, enddate, user.getName(), guideID);
            // set path for loading sub-report file
            ((GuideCommissionInfo) data.get(0)).setSubReportDir(getServletContext().getRealPath("/WEB-INF/report/"));
        } else if (AgentCommissionSummary.equalsIgnoreCase(name)) {
            data = reportservice.getAgentCommissionReportSummary(startdate, enddate, user.getName(), agentid);
        } else if (AgentCommission.equalsIgnoreCase(name)) {
            data = reportservice.getAgentCommissionReport(startdate, enddate, user.getName(), agentid);
            // set path for loading sub-report file
            ((AgentCommission) data.get(0)).setSubReportDir(getServletContext().getRealPath("/WEB-INF/report/"));
        } else if (AgentCommissionInfo.equalsIgnoreCase(name)) {
            data = reportservice.getAgentCommissionReportInfo(startdate, enddate, user.getName(), agentid);
        } else if (DaytourOther.equalsIgnoreCase(name)) {
            data = reportservice.getDaytourOtherReport(refno, status);
        } else if (OtherVouncherEmail.equalsIgnoreCase(name)) {
            data = reportservice.getDaytourOtherReport(refno, status);
        } else if (ReceiptEmail.equalsIgnoreCase(name)) {
            data = reportservice.getReceiptEmail(receiveId,option);
        } else if (ReceiptReport.equalsIgnoreCase(name)) {
            data = reportservice.getReceipt(receiveId,option);
        } else if (ReceiptSummaryReport.equalsIgnoreCase(name)) {
            data = reportservice.getReceiptSummary(dateFrom,dateTo,departmentRec,recType,status,user.getUsername()+"-"+user.getRole().getName());
        } else if (ReceiveList.equalsIgnoreCase(name)) {
            data = reportservice.getDaytourOtherReport(refno, status);
        } else if (InvoiceEmail.equalsIgnoreCase(name)) {
            data = reportservice.getInvoice(invoiceid,bankid,showStaff,showLeader,sign);
        } else if (InvoiceReport.equalsIgnoreCase(name)) {
            data = reportservice.getInvoice(invoiceid,bankid,showStaff,showLeader,sign);
        }else if (InvoiceTemp.equalsIgnoreCase(name)) {
            data = reportservice.getInvoice(invoiceid,bankid,showStaff,showLeader,sign);
        } else if (InvoiceTempReport.equalsIgnoreCase(name)) {
            data = reportservice.getInvoice(invoiceid,bankid,showStaff,showLeader,sign);
        } else if (TaxInvoiceReport.equalsIgnoreCase(name)) {
            data = reportservice.getTaxInvoice(taxInvId, option);
        } else if (TaxInvoiceEmailReport.equalsIgnoreCase(name)) {
            data = reportservice.getTaxInvoiceEmail(taxInvId, option);
        }else if(CreditNoteReport.equalsIgnoreCase(name)){
            data = reportservice.getCreditNoteReport(cnid);
        }else if(InvoiceMonthly.equalsIgnoreCase(name)){
            data = reportservice.getInvoiceMonthly(BillFrom, ClientTo, ClientName, Payment, Accno, vattype, from, to, departmentInvoice);
        }else if(RefundAirReport.equalsIgnoreCase(name)){
            data = reportservice.getRefundAirReport(refundId);
        }else if(TicketFareReport.equalsIgnoreCase(name)){
//            data = reportservice.getTicketFareReport();
        }else if(TaxInvoiceSummaryReport.equalsIgnoreCase(name)){
            data = reportservice.getTaxInvoiceSummaryReport(from, to, department, status, systemuser);
        }else if(CreditNoteSummaryReport.equalsIgnoreCase(name)){
            data = reportservice.getCreditNoteSummaryReport(from, to, department, status, user.getUsername()+"-"+user.getRole().getName());
        }else if(PaymentAirlineInfo.equalsIgnoreCase(name)){
            String payno = request.getParameter("payno");
            System.out.println(" payno " + payno);
            data = reportservice.getPaymentAirlineReport(payno,user.getUsername()+"-"+user.getRole().getName());
            ((PaymentAirline) data.get(0)).setSubReportDir(getServletContext().getRealPath("/WEB-INF/report/"));
        }else if(PaymentTourHotelSummary.equalsIgnoreCase(name)){
            data = reportservice.getPaymentTourHotelSummary(from, to, pvtype, status, invSupCode, user.getUsername()+"-"+user.getRole().getName());
        }else if(HotelSummary.equalsIgnoreCase(name)){
            data = reportservice.getHotelSummary(fromHotelSummary, toHotelSummary, departmentHotelSummary);
        }else if(HotelMonthlyReport.equalsIgnoreCase(name)){
            System.out.println("Detail is : " + detailHotelMonthly);
                data = reportservice.getHotelMonthly(fromHotelSummary, toHotelSummary, departmentHotelSummary,detailHotelMonthly,systemuser);
        }else if(HotelMonthlyDetailReport.equalsIgnoreCase(name)){
            System.out.println("Detail is : " + detailHotelMonthly);
            data = reportservice.getHotelMonthlyDetail(fromHotelSummary, toHotelSummary, departmentHotelSummary,detailHotelMonthly,systemuser);
        }else if(OtherMonthlyReport.equalsIgnoreCase(name)){
            String datefrom = request.getParameter("fromdate");
            String dateto = request.getParameter("todate");
//            String department = request.getParameter("department");
            String detail = request.getParameter("detail");
            data = reportservice.getOtherMonthlyReport(datefrom,dateto,department,detail,user.getRole().getName());
            ((OtherMonthlyReport) data.get(0)).setSubReportDir(getServletContext().getRealPath("/WEB-INF/report/"));
        } else if(DailyTourReport.equalsIgnoreCase(name)){
            String detail = request.getParameter("detail");
            data = reportservice.getDailyTourReport(from,to,department,detail,user.getUsername()+" - "+user.getRole().getName());
            ((DailyTourReport) data.get(0)).setSubReportDir(getServletContext().getRealPath("/WEB-INF/report/"));
        }

        JRDataSource dataSource = new JRBeanCollectionDataSource(data);

        model.put("JRDataSource", dataSource);
       

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
