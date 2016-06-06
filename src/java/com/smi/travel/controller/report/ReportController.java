/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller.report;

import com.smi.travel.datalayer.view.entity.OutboundStaffSummaryReport;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.report.model.AgentCommission;
import com.smi.travel.datalayer.report.model.BillAirAgentReport;
import com.smi.travel.datalayer.report.model.DailyTourReport;
import com.smi.travel.datalayer.report.model.GuideCommissionInfo;
import com.smi.travel.datalayer.report.model.HotelMonthlyReport;
import com.smi.travel.datalayer.report.model.OtherAgentCommission;
import com.smi.travel.datalayer.report.model.OtherGuideCommissionInfo;
import com.smi.travel.datalayer.report.model.OtherMonthlyReport;
import com.smi.travel.datalayer.report.model.PackageMonthlyReport;
import com.smi.travel.datalayer.report.model.PaymentAirline;
import com.smi.travel.datalayer.report.model.TicketOrder;
import com.smi.travel.datalayer.report.model.TicketSummaryList;
import com.smi.travel.datalayer.service.ReportService;
import com.smi.travel.datalayer.view.entity.BookingHeaderSummaryView;
import com.smi.travel.datalayer.view.entity.ConfirmSlipHeaderReport;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
    private static final String TicketSaleSummary = "TicketSaleVolumn";
    private static final String TicketProfitSummary = "TicketProfitVolumn";
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
    private static final String ReceiptTempReport = "ReceiptTempReport";
    private static final String ReceiptSummaryReport = "ReceiptSummaryReport";
    private static final String ReceiveList = "ReceiveList";
    private static final String InvoiceEmail = "InvoiceEmail";
    private static final String InvoiceReport = "InvoiceReport";
    private static final String InvoiceTemp = "InvoiceTemp";
    private static final String InvoiceTempEmail = "InvoiceTempEmail";
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
    private static final String HotelMonthlyTempReport = "HotelMonthlyTempReport";
    private static final String HotelMonthlyDetailReport = "HotelMonthlyDetailReport";
    private static final String CollectionReport = "CollectionReport";
    
    private static final String OtherMonthlyReport = "OtherMonthlyReport"; // Other
    private static final String DailyTourReport = "DailyTourReport";
    private static final String PackageSummaryReport = "PackageSummaryReport"; //PackageMonthly
    private static final String BookingSummaryReport = "BookingSummaryReport"; //PackageMonthly
    private static final String OtherGuideCommission = "OtherGuideCommission"; 
    private static final String OtherAgentCommission = "OtherAgentCommission";
    private static final String ConfirmSlipReport = "ConfirmSlipReport";
    private static final String BillAirAgentSummaryReport = "BillAirAgentSummaryReport";
    private static final String OutboundStaffSummaryReport = "OutboundStaffSummaryReport";
    private static final String InvoiceInboundPerformaEmail = "InvoiceInboundPerformaEmail";
    private static final String InvoiceInboundPerformaReport = "InvoiceInboundPerformaReport";
    private static final String InvoiceInboundRevenueReport = "InvoiceInboundRevenueReport";
    private static final String InvoiceInboundRevenueEmail = "InvoiceInboundRevenueEmail";
    private static final String RefundTicketSummaryReport = "RefundTicketSummaryReport";
    private static final String RefundAirticketReport = "RefundAirticketReport";
    private static final String Overdue = "OverdueSummaryReport";
    private static final String PaymentOutboundSummaryReport = "PaymentOutboundSummaryReport";
    private static final String PaymentProfitLossSummary = "PaymentProfitLossSummary";
    private static final String PaymentProfitLossVolumn = "PaymentProfitLossVolumn";
    private static final String PaymentOutboundReport = "PaymentOutboundReport";
    
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
        
        //Invoice Monthly
        String billingAttn = request.getParameter("billingAttn");
        String billingFrom = request.getParameter("billingFrom");
        String billingTel = request.getParameter("billingTel");
        String billingFax = request.getParameter("billingFax");
        String billingMail = request.getParameter("billingMail");
        String billingDate = request.getParameter("billingDate");
        
        //Bill Air Agent
        String agentCode = request.getParameter("agentCode");
        String invoiceFromDates = request.getParameter("invoiceFrom");
        String InvoiceToDates = request.getParameter("invoiceTo");
        String issueFroms = request.getParameter("issueFrom");
        String issueTos = request.getParameter("issueTo");
        String refundFrom = request.getParameter("refundFrom");
        String refundTo = request.getParameter("refundTo");
        String departments = request.getParameter("department");
        String salebyUsers = request.getParameter("salebyUser");
        String termPays = request.getParameter("termPay");
        String paymentTypes = request.getParameter("paymentType");
        String vatTemp = request.getParameter("vatTemp");
        String whtTemp = request.getParameter("whtTemp");
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
            data = reportservice.getTicketSummary(ticketfrom, tickettype, startdate, enddate, billto, passenger, user.getName(), department);
            ((TicketSummaryList) data.get(0)).setSubReportDir(getServletContext().getRealPath("/WEB-INF/report/"));
        } else if (AirlineSummary.equalsIgnoreCase(name)) {
            data = reportservice.getAirlineSummary(ticketfrom, tickettype, startdate, enddate, user.getName(), department);
        } else if (StaffSummary.equalsIgnoreCase(name)) {
            data = reportservice.getStaffSummary(ticketfrom, tickettype, startdate, enddate, user.getName(), department);
        } else if (TicketSaleSummary.equalsIgnoreCase(name)) {
            data = reportservice.getTicketSaleVolumn(ticketfrom, tickettype, startdate, enddate ,user.getName());
        } else if (TicketProfitSummary.equalsIgnoreCase(name)) {
            data = reportservice.getTicketProfitVolumn(ticketfrom, tickettype, startdate, enddate,user.getName());
        } else if (InvoiceSummary.equalsIgnoreCase(name)) {
            String subDepartment = request.getParameter("subDepartment");
            data = reportservice.getInvoiceSummary(from, to, department, typeInvoice,agent,statusInvoice,user.getUsername()+"-"+user.getName(),subDepartment);
        } else if (GuideJob.equalsIgnoreCase(name)) {
            data = reportservice.getGuildJobReport(tourDate, tourCode, user.getName());
        } else if (TransferJob.equalsIgnoreCase(name)) {
            data = reportservice.getTransferJobReport(docno);
        } else if (GuideCommission.equalsIgnoreCase(name)) {
            data = reportservice.getGuideCommissionInfoReport(startdate, enddate, user.getName(), guideID);
            // set path for loading sub-report file
            ((GuideCommissionInfo) data.get(0)).setSubReportDir(getServletContext().getRealPath("/WEB-INF/report/"));
        } else if (AgentCommissionSummary.equalsIgnoreCase(name)) {
            String datefromtemp = !"".equalsIgnoreCase(startdate) ? new SimpleDateFormat("yyyy-MM-dd", new Locale("us", "us")).format(util.convertStringToDate(startdate)) : "" ;
            String datetotemp = !"".equalsIgnoreCase(enddate) ? new SimpleDateFormat("yyyy-MM-dd", new Locale("us", "us")).format(util.convertStringToDate(enddate)) : "" ;
            data = reportservice.getAgentCommissionReportSummary(datefromtemp, datetotemp, user.getName(), agentid);
        } else if (AgentCommission.equalsIgnoreCase(name)) {
            data = reportservice.getAgentCommissionReport(startdate, enddate, user.getName(), agentid);
            // set path for loading sub-report file
            ((AgentCommission) data.get(0)).setSubReportDir(getServletContext().getRealPath("/WEB-INF/report/"));
        } else if (AgentCommissionInfo.equalsIgnoreCase(name)) {
            String datefromtemp = !"".equalsIgnoreCase(startdate) ? new SimpleDateFormat("yyyy-MM-dd", new Locale("us", "us")).format(util.convertStringToDate(startdate)) : "" ;
            String datetotemp = !"".equalsIgnoreCase(enddate) ? new SimpleDateFormat("yyyy-MM-dd", new Locale("us", "us")).format(util.convertStringToDate(enddate)) : "" ;
            data = reportservice.getAgentCommissionReportInfo(datefromtemp, datetotemp, user.getName(), agentid);
        } else if (DaytourOther.equalsIgnoreCase(name)) {
            String otherId = request.getParameter("otherId");
            String passengerId = request.getParameter("passengerId");
            String refNo = request.getParameter("refNo");
            String nameSurname = (!"".equalsIgnoreCase(user.getName()) && user.getName() != null ? user.getName() : "");
            data = reportservice.getDaytourOperationOtherReport(otherId, passengerId, refNo, status, nameSurname);
        } else if (OtherVouncherEmail.equalsIgnoreCase(name)) {
            String otherId = request.getParameter("otherId");
            String passengerId = request.getParameter("passengerId");
            String refNo = request.getParameter("refNo");
            String nameSurname = (!"".equalsIgnoreCase(user.getName()) && user.getName() != null ? user.getName() : "");
            data = reportservice.getDaytourOperationOtherReport(otherId, passengerId, refNo, status, nameSurname);
        } else if (ReceiptEmail.equalsIgnoreCase(name)) {
            data = reportservice.getReceiptEmail(receiveId,option,sign,user.getName());
        } else if (ReceiptReport.equalsIgnoreCase(name)) {
            data = reportservice.getReceipt(receiveId,option,sign,user.getName());
        } else if (ReceiptTempReport.equalsIgnoreCase(name)) {
            data = reportservice.getReceiptTemp(receiveId,option,sign,user.getName());
        } else if (ReceiptSummaryReport.equalsIgnoreCase(name)) {
            data = reportservice.getReceiptSummary(dateFrom,dateTo,departmentRec,recType,status,user.getUsername()+"-"+user.getName());
        } else if (ReceiveList.equalsIgnoreCase(name)) {
            data = reportservice.getDaytourOtherReport(refno, status);
        } else if (InvoiceEmail.equalsIgnoreCase(name)) {
            data = reportservice.getInvoice(invoiceid,bankid,showStaff,showLeader,sign,user.getName(),"0");
        } else if (InvoiceReport.equalsIgnoreCase(name)) {
            data = reportservice.getInvoice(invoiceid,bankid,showStaff,showLeader,sign,user.getName(),"0");
        }else if (InvoiceTemp.equalsIgnoreCase(name)) {
            data = reportservice.getInvoice(invoiceid,bankid,showStaff,showLeader,sign,user.getName(),"1");
        } else if (InvoiceTempEmail.equalsIgnoreCase(name)) {
            data = reportservice.getInvoice(invoiceid,bankid,showStaff,showLeader,sign,user.getName(),"1");
        } else if (InvoiceTempReport.equalsIgnoreCase(name)) {
            data = reportservice.getInvoice(invoiceid,bankid,showStaff,showLeader,sign,user.getName(),"1");
        } else if (TaxInvoiceReport.equalsIgnoreCase(name)) {
            data = reportservice.getTaxInvoice(taxInvId, option,sign,user.getName());
        } else if (TaxInvoiceEmailReport.equalsIgnoreCase(name)) {
            data = reportservice.getTaxInvoiceEmail(taxInvId, option,sign,user.getName());
        }else if(CreditNoteReport.equalsIgnoreCase(name)){
            data = reportservice.getCreditNoteReport(cnid);
        }else if(InvoiceMonthly.equalsIgnoreCase(name)){
            try {
                billingAttn = new String(billingAttn.getBytes("ISO8859_1"),"UTF-8");
                billingFrom = new String(billingFrom.getBytes("ISO8859_1"),"UTF-8");
                billingTel = new String(billingTel.getBytes("ISO8859_1"),"UTF-8");
                billingFax = new String(billingFax.getBytes("ISO8859_1"),"UTF-8");
                billingMail = new String(billingMail.getBytes("ISO8859_1"),"UTF-8");
                billingDate = new String(billingDate.getBytes("ISO8859_1"),"UTF-8");
                ClientTo = new String(ClientTo.getBytes("ISO8859_1"),"UTF-8");
                ClientName = new String(ClientName.getBytes("ISO8859_1"),"UTF-8");
                System.out.println("billingAttn :  "+billingAttn);
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            data = reportservice.getInvoiceMonthly(ClientTo, ClientName, Accno, vattype, from, to, departmentInvoice, billingAttn, billingFrom, billingTel, billingFax, billingMail, billingDate);
        }else if(RefundAirReport.equalsIgnoreCase(name)){
            data = reportservice.getRefundAirReport(refundId);
        }else if(TicketFareReport.equalsIgnoreCase(name)){
//            data = reportservice.getTicketFareReport();
        }else if(TaxInvoiceSummaryReport.equalsIgnoreCase(name)){
            data = reportservice.getTaxInvoiceSummaryReport(from, to, department, status, systemuser);
        }else if(CreditNoteSummaryReport.equalsIgnoreCase(name)){
            data = reportservice.getCreditNoteSummaryReport(from, to, department, status, user.getUsername()+"-"+user.getName());
        }else if(PaymentAirlineInfo.equalsIgnoreCase(name)){
            String payno = request.getParameter("payno");
            System.out.println(" payno " + payno);
            data = reportservice.getPaymentAirlineReport(payno,user.getUsername()+"-"+user.getName());
            ((PaymentAirline) data.get(0)).setSubReportDir(getServletContext().getRealPath("/WEB-INF/report/"));
        }else if(PaymentTourHotelSummary.equalsIgnoreCase(name)){
            data = reportservice.getPaymentTourHotelSummary(from, to, pvtype, status, invSupCode, user.getUsername()+"-"+user.getName());
        }else if(HotelSummary.equalsIgnoreCase(name)){
            data = reportservice.getHotelSummary(fromHotelSummary, toHotelSummary, departmentHotelSummary);
        }else if(HotelMonthlyTempReport.equalsIgnoreCase(name)){
            System.out.println("Detail is : " + detailHotelMonthly);
            data = reportservice.getHotelMonthlyReport(fromHotelSummary, toHotelSummary, departmentHotelSummary,detailHotelMonthly,user.getUsername()+"-"+user.getName(),getServletContext().getRealPath("/WEB-INF/report/"));         
            ((HotelMonthlyReport) data.get(0)).setSubReportDir(getServletContext().getRealPath("/WEB-INF/report/"));  
        }else if(HotelMonthlyDetailReport.equalsIgnoreCase(name)){
            System.out.println("Detail is : " + detailHotelMonthly);
//            data = reportservice.getHotelMonthlyDetail(fromHotelSummary, toHotelSummary, departmentHotelSummary,detailHotelMonthly,systemuser);
        }else if(OtherMonthlyReport.equalsIgnoreCase(name)){
            String datefrom = request.getParameter("fromdate");
            String dateto = request.getParameter("todate");
//            String department = request.getParameter("department");
            String detail = request.getParameter("detail");
            data = reportservice.getOtherMonthlyReport(datefrom,dateto,department,detail,user.getName());
            ((OtherMonthlyReport) data.get(0)).setSubReportDir(getServletContext().getRealPath("/WEB-INF/report/"));
        } else if(DailyTourReport.equalsIgnoreCase(name)){
            String detail = request.getParameter("detail");
            data = reportservice.getDailyTourReport(from,to,department,detail,user.getUsername()+" - "+user.getName());
            ((DailyTourReport) data.get(0)).setSubReportDir(getServletContext().getRealPath("/WEB-INF/report/"));
        }else if(PackageSummaryReport.equalsIgnoreCase(name)){
            String datefrom = request.getParameter("fromdate");
            String dateto = request.getParameter("todate");
            String detail = request.getParameter("detail");
            data = reportservice.getPackageMonthlyReport(datefrom,dateto,department,detail,user.getName(),getServletContext().getRealPath("/WEB-INF/report/"));
            ((PackageMonthlyReport) data.get(0)).setSubReportDir(getServletContext().getRealPath("/WEB-INF/report/"));         
        }else if(BookingSummaryReport.equalsIgnoreCase(name)){
            data = reportservice.getBookingSummaryReport(refno);
            ((BookingHeaderSummaryView) data.get(0)).setSubReportDir(getServletContext().getRealPath("/WEB-INF/report/"));
        }else if (OtherGuideCommission.equalsIgnoreCase(name)) {
            String datefromtemp = !"".equalsIgnoreCase(startdate) ? new SimpleDateFormat("yyyy-MM-dd", new Locale("us", "us")).format(util.convertStringToDate(startdate)) : "" ;
            String datetotemp = !"".equalsIgnoreCase(enddate) ? new SimpleDateFormat("yyyy-MM-dd", new Locale("us", "us")).format(util.convertStringToDate(enddate)) : "" ;
            data = reportservice.getOtherGuideCommissionInfoReport(datefromtemp, datetotemp, user.getName(), guideID);
            // set path for loading sub-report file
            ((OtherGuideCommissionInfo) data.get(0)).setSubReportDir(getServletContext().getRealPath("/WEB-INF/report/"));
        }else if (OtherAgentCommission.equalsIgnoreCase(name)) {
            String datefromtemp = !"".equalsIgnoreCase(startdate) ? new SimpleDateFormat("yyyy-MM-dd", new Locale("us", "us")).format(util.convertStringToDate(startdate)) : "" ;
            String datetotemp = !"".equalsIgnoreCase(enddate) ? new SimpleDateFormat("yyyy-MM-dd", new Locale("us", "us")).format(util.convertStringToDate(enddate)) : "" ;
            data = reportservice.getOtherAgentCommissionReport(datefromtemp, datetotemp, user.getName(), agentid);
            // set path for loading sub-report file
            ((OtherAgentCommission) data.get(0)).setSubReportDir(getServletContext().getRealPath("/WEB-INF/report/"));
        }else if(ConfirmSlipReport.equalsIgnoreCase(name)){
            data = reportservice.getConfirmSlipHeaderReport(refno,user.getUsername()+"-"+user.getName());
            ((ConfirmSlipHeaderReport) data.get(0)).setSubReportDir(getServletContext().getRealPath("/WEB-INF/report/"));
        }else if (BillAirAgentSummaryReport.equalsIgnoreCase(name)) {
            data = reportservice.getBillAirAgentReportPdf(agentCode, invoiceFromDates, InvoiceToDates, issueFroms, issueTos, refundFrom, refundTo, departments, salebyUsers, termPays,user.getUsername(),paymentTypes,vatTemp,whtTemp);
            // set path for loading sub-report file
            ((BillAirAgentReport) data.get(0)).setSubReportDir(getServletContext().getRealPath("/WEB-INF/report/"));
        }else if(OutboundStaffSummaryReport.equalsIgnoreCase(name)){
            String detail = request.getParameter("detail");
            String currency = request.getParameter("currency");
            data = reportservice.getOutboundStaffSummaryReport(from,to,salebyUsers,currency,detail,user.getUsername()+"-"+user.getName());
            ((OutboundStaffSummaryReport) data.get(0)).setSubReportDir(getServletContext().getRealPath("/WEB-INF/report/"));
        }else if(CollectionReport.equalsIgnoreCase(name)){
            String receiveDate = request.getParameter("receiveDate");
            String vatType = request.getParameter("vatType");
            data = reportservice.getCollectionReport(receiveDate,vatType,department,user.getUsername()+"-"+user.getName());
        }else if (InvoiceInboundPerformaEmail.equalsIgnoreCase(name)) {
            data = reportservice.getInvoice(invoiceid,bankid,showStaff,showLeader,sign,user.getName(),"0");
        }else if (InvoiceInboundRevenueReport.equalsIgnoreCase(name)) {
            data = reportservice.getInvoice(invoiceid,bankid,showStaff,showLeader,sign,user.getName(),"0");
        }else if (InvoiceInboundRevenueEmail.equalsIgnoreCase(name)) {
            data = reportservice.getInvoice(invoiceid,bankid,showStaff,showLeader,sign,user.getName(),"0");
        }else if (InvoiceInboundPerformaReport.equalsIgnoreCase(name)) {
            data = reportservice.getInvoice(invoiceid,bankid,showStaff,showLeader,sign,user.getName(),"0");
        }else if (RefundTicketSummaryReport.equalsIgnoreCase(name)) {
            String ticketFrom = request.getParameter("ticketFrom");
            String ticketTo = request.getParameter("ticketTo");
            String refundBy = request.getParameter("refundBy");
            data = reportservice.getRefundTicketSummary(refundFrom,refundTo,ticketFrom,ticketTo,refundBy,user.getName());
        }else if(RefundAirticketReport.equalsIgnoreCase(name)){
            data = reportservice.getRefundAirReport(refundId);
        }else if(Overdue.equals(name)){
            String from_over = request.getParameter("from");
            String to_over = request.getParameter("to");
            String department_over = request.getParameter("department");
            String staffcode_over = request.getParameter("staffcode");
            String staffname_over = request.getParameter("staffname");
            String vattype_over = request.getParameter("vattype");
            String group_over = request.getParameter("group");
            String view_over = request.getParameter("view");
            String clientcode_over = request.getParameter("clientcode");
            String clientname_over = request.getParameter("clientname");
            data = reportservice.listOverdueSummary(clientcode_over, clientname_over, staffcode_over, staffname_over, vattype_over, from_over, to_over, department_over, group_over, view_over, user.getName());
        }else if(PaymentOutboundSummaryReport.equals(name)){
            String fromdate = request.getParameter("fromdate");
            String todate = request.getParameter("todate");
            String statusP = request.getParameter("status");
            String invSupCodeP = request.getParameter("invSupCode");
            String refnoP = request.getParameter("refno");         
            data = reportservice.getPaymentOutboundSummaryReport(fromdate, todate,statusP,invSupCodeP, refnoP, user.getName());
        }else if(PaymentProfitLossSummary.equals(name)){
            String departFromDate = request.getParameter("departFromDate");
            String departToDate = request.getParameter("departToDate");
            String invFromDate = request.getParameter("invFromDate");
            String invToDate = request.getParameter("invToDate");
            String ownercode = request.getParameter("ownercode");
            String city = request.getParameter("city");
            String producttypeid = request.getParameter("producttypeid");
            String invsupcode = request.getParameter("invsupcode");
            String payFromDate = request.getParameter("payFromDate");
            String payToDate = request.getParameter("payToDate");
            String groupby = request.getParameter("groupby");
            data = reportservice.getPaymentProfitLossReport(departFromDate, departToDate, invFromDate, invToDate, ownercode, city, producttypeid, invsupcode, payFromDate, payToDate, "REF NO");
        }else if(PaymentProfitLossVolumn.equals(name)){
            String departFromDate = request.getParameter("departFromDate");
            String departToDate = request.getParameter("departToDate");
            String invFromDate = request.getParameter("invFromDate");
            String invToDate = request.getParameter("invToDate");
            String ownercode = request.getParameter("ownercode");
            String city = request.getParameter("city");
            String producttypeid = request.getParameter("producttypeid");
            String invsupcode = request.getParameter("invsupcode");
            String payFromDate = request.getParameter("payFromDate");
            String payToDate = request.getParameter("payToDate");
            String groupby = request.getParameter("groupby");
            data = reportservice.getPaymentProfitLossVolumnReport(departFromDate, departToDate, invFromDate, invToDate, ownercode, city, producttypeid, invsupcode, payFromDate, payToDate, groupby);
        }else if(PaymentOutboundReport.equals(name)){
            String paymentOutboundId = request.getParameter("paymentOutboundId");
            String optionReport = request.getParameter("optionReport");
            data = reportservice.getPaymentOutboundReport(paymentOutboundId,optionReport);
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
