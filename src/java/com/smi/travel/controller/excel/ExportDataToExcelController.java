/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller.excel;

import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.ARMonitorService;
import com.smi.travel.datalayer.service.ReportService;
import com.smi.travel.datalayer.view.entity.ARNirvana;
import com.smi.travel.master.controller.SMITravelController;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Jittima
 */
public class ExportDataToExcelController  extends SMITravelController{
    
    private ReportService reportservice; 
    private static final String TicketFareReport = "TicketFareReport";
    private static final String TicketFareAirlineReport = "TicketFareAirlineReport";
    private static final String TicketFareInvoicReport = "TicketFareInvoicReport";
    private static final String TicketFareAgentReport = "TicketFareAgentReport";
    private static final String TicketFareSummaryByStaff = "TicketFareSummaryByStaff";
    private static final String TicketFareSummaryByAgent = "TicketFareSummaryByAgent";
    private static final String BillAirAgent = "BillAirAgent";
    private static final String BillAirAgentSummary = "BillAirAgentSummary";
    private static final String ChangeARReport = "ChangeARReport";
    private static final String CollectionReport = "CollectionReport";
    private static final String ApReport = "ApReport";
    private static final String ReportName = "name";
    private static final String ParaMeter = "parameter";
    private static final String SummaryAirline = "SummaryAirline";
    private static final String TicketFareSummaryAirline = "TicketFareSummaryAirline"; //Ticket Summary Airline -> List Summary Airline Issue --> TK detail
    private static final String SummaryTicketAdjustCostAndIncome = "SummaryTicketAdjustCostAndIncome";
    private static final String SummaryTicketCostAndIncome = "SummaryTicketCostAndIncome";
    private static final String SummaryTicketCommissionReceive = "SummaryTicketCommissionReceive";
    private static final String RefundTicketDetail = "RefundTicketDetail";
    private static final String SummaryAirlinePax = "SummaryAirlinePax"; //Ticket Summary Airline -> List Summary Airline Issue --> Summary airline
    private static final String TicketProfitLoss = "TicketProfitLoss";
     private static final String TicketSummaryCommission = "TicketSummaryCommission";
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String output =  request.getParameter("output");
        String name = request.getParameter(ReportName);
        String ticketType = request.getParameter("ticketType");
        String ticketBuy = request.getParameter("ticketBuy");
        String airline = request.getParameter("airline");
        String airlineCode = request.getParameter("airlineCode");
        String dateFrom = request.getParameter("dateFrom");
        String dateTo = request.getParameter("dateTo");
        String department = request.getParameter("department");
        String staff = request.getParameter("staff");
        String termPay = request.getParameter("termPay");   
        
        String issuedateFrom = request.getParameter("issuedateFrom");   
        String issuedateTo = request.getParameter("issuedateTo");   
        String invdateFrom = request.getParameter("invdateFrom");   
        String invdateTo = request.getParameter("invdateTo"); 

        
        //Ar Monitor
        String invoiceType = request.getParameter("invoiceType");
        String departmnt = request.getParameter("department");
        String type = request.getParameter("arType");
        String from = request.getParameter("arFromDate");
        String to = request.getParameter("arToDate");
        String status = request.getParameter("arStatus");
        String accno = request.getParameter("accno");
        
        String reportType = request.getParameter("reportType");
        
        //TicketFareSummaryAirline
        String typeRouting = request.getParameter("typeRouting");
        String routingDetail = request.getParameter("routingDetail");
        String passenger = request.getParameter("passenger");
        String agentId = request.getParameter("agentId");

        
        SystemUser user = (SystemUser) session.getAttribute("USER");
        String printby = user.getRole().getName(); 
        List data  = new ArrayList();
        
        String invoiceFromDate = request.getParameter("invoiceFromDate");
        String invoiceToDate = request.getParameter("invoiceToDate");
        String issueFrom = request.getParameter("issueFrom");
        String issueTo = request.getParameter("issueTo");
        String paymentType = request.getParameter("paymentType");
        String departmentt = request.getParameter("department");
        String salebyUser = request.getParameter("salebyName");
        String termPayt = request.getParameter("termPay");
        
        if(TicketFareReport.equalsIgnoreCase(name)){
            System.out.println("get excel data");
            data = reportservice.getTicketFareReport(ticketType,ticketBuy,airline,airlineCode,dateFrom,dateTo,department,staff,termPay,printby,invdateFrom,invdateTo);
        }else if(TicketFareAirlineReport.equalsIgnoreCase(name)){
            System.out.println("get excel data");
            data = reportservice.getTicketFareReport(ticketType,ticketBuy,airline,airlineCode,issuedateFrom,issuedateTo,department,staff,termPay,printby,invdateFrom,invdateTo);
        }else if(TicketFareInvoicReport.equalsIgnoreCase(name)){
            System.out.println("get excel data");
            data = reportservice.getTicketFareReport(ticketType,ticketBuy,airline,airlineCode,issuedateFrom,issuedateTo,department,staff,termPay,printby,invdateFrom,invdateTo);
            System.out.println(" data size " + String.valueOf(data.size()));
        }else if(TicketFareAgentReport.equalsIgnoreCase(name)){
            System.out.println("get excel data");
            data = reportservice.getTicketFareReport(ticketType,ticketBuy,airline,airlineCode,issuedateFrom,issuedateTo,department,staff,termPay,printby,invdateFrom,invdateTo);
        }else if(TicketFareSummaryByStaff.equalsIgnoreCase(name)){
            System.out.println("get excel data staff");
            data = reportservice.getTicketFareSumAgentStaff(ticketType, ticketBuy, airline, airlineCode, department, staff, termPay, printby, issuedateFrom, issuedateTo, invdateFrom, invdateTo,"staff");
        }else if(TicketFareSummaryByAgent.equalsIgnoreCase(name)){
            System.out.println("get excel data agent");
            data = reportservice.getTicketFareSumAgentStaff(ticketType, ticketBuy, airline, airlineCode, department, staff, termPay, printby, issuedateFrom, issuedateTo, invdateFrom, invdateTo,"agent");
        }else if(BillAirAgent.equalsIgnoreCase(name)){
            System.out.println("get excel data agent");
            String agentCode = request.getParameter("agentCode");
            String invoiceFromDates = request.getParameter("invoiceFromDate");
            String InvoiceToDates = request.getParameter("InvoiceToDate");
            String issueFroms = request.getParameter("issueFrom");
            String issueTos = request.getParameter("issueTo");
            String refundFrom = request.getParameter("refundFrom");
            String refundTo = request.getParameter("refundTo");
            String departments = request.getParameter("department");
            String salebyUsers = request.getParameter("salebyUser");
            String termPays = request.getParameter("termPay");
            data = reportservice.getBillAirAgentReportSummary(agentCode, invoiceFromDates, InvoiceToDates, issueFroms, issueTos, refundFrom, refundTo, departments, salebyUsers, termPays,printby);
        }else if(ChangeARReport.equalsIgnoreCase(name)){
            System.out.println("get excel data agent");
            data = reportservice.SearchArNirvanaFromFilter(invoiceType, departmnt, type, from, to, status,accno);
        }else if(BillAirAgentSummary.equalsIgnoreCase(name)){
            System.out.println("get excel data BillAirAgentSummary");
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
            data = reportservice.getBillAirAgentReportSummary(agentCode, invoiceFromDates, InvoiceToDates, issueFroms, issueTos, refundFrom, refundTo, departments, salebyUsers, termPays, printby);
        }else if(CollectionReport.equalsIgnoreCase(name)){
            //Collectipn Report
            type = request.getParameter("type");
            status = request.getParameter("status");
            from = request.getParameter("inputFromDate");
            to = request.getParameter("inputToDate");
            String invno = request.getParameter("invno");
            System.out.println("get excel data collection report");
            data = reportservice.getCollectionNirvanaFromFilter(department, type, status, from, to, invno, printby); 
        }else if(ApReport.equals(name)){
            status = request.getParameter("status");
            String payment = request.getParameter("payment");
            System.out.println("get excel data ap ApReport");
            data = reportservice.getApNirvanaReport(payment, ticketType, status, dateFrom, dateTo, printby);
        }else if(SummaryAirline.equals(name)){
            System.out.println("get excel data ap SummaryAirline");
            data = reportservice.listSummaryAirline();
        }else if(TicketFareSummaryAirline.equals(name)){
            System.out.println("get excel data TicketFareSummaryAirline");
            data = reportservice.getTicketFareSumAirline(typeRouting,routingDetail,issuedateFrom,issuedateTo,invdateFrom,invdateTo,airlineCode,passenger,agentId,department,staff,termPay,printby);
        }else if(SummaryAirlinePax.equals(name)){
            System.out.println("get excel data SummaryAirlinePax");
            data = reportservice.getSumAirlinePax(typeRouting,routingDetail,issuedateFrom,issuedateTo,invdateFrom,invdateTo,airlineCode,passenger,agentId,department,staff,termPay,printby);
        }else if(SummaryTicketAdjustCostAndIncome.equals(name)){
            System.out.println("get excel data ap SummaryTicketAdjustCostAndIncome");
            System.out.println("Term : " + termPayt);          
            data = reportservice.getSummaryTicketAdjustCostAndIncome(reportType, invoiceFromDate, invoiceToDate, issueFrom, issueTo, paymentType, departmentt, salebyUser, termPayt,printby);
        }else if(SummaryTicketCostAndIncome.equals(name)){
            System.out.println("get excel data ap SummaryTicketCostAndIncome");
            data = reportservice.getSummaryTicketCostAndIncome(reportType, invoiceFromDate, invoiceToDate, issueFrom, issueTo, paymentType, departmentt, salebyUser, termPayt,printby);
        }else if(SummaryTicketCommissionReceive.equals(name)){
            System.out.println("get excel data ap SummaryTicketCommissionReceive");
            System.out.println("Term : " + termPayt);       
            data = reportservice.getTicketCommissionReceive(reportType, invoiceFromDate, invoiceToDate, issueFrom, issueTo, paymentType, departmentt, salebyUser, termPayt,printby);
        }else if(RefundTicketDetail.equals(name)){
            System.out.println("get excel data ap RefundTicketDetail");
            System.out.println("Term : " + termPayt);    
            String refundagent = request.getParameter("refundAgentId");
            String refundnameby = request.getParameter("refundAgentName");
            String refundBy = request.getParameter("refundBy");
            String passengername = request.getParameter("passenger");
            String receivefrom = request.getParameter("receiveFromDate");
            String receiveto = request.getParameter("receiveToDate");
            String paidfrom = request.getParameter("paidFromDate");
            String paidto = request.getParameter("paidToDate");
            String typeprint = request.getParameter("typePrint");
            data = reportservice.getRefundTicketDetail(refundagent, refundnameby, passengername, receivefrom, receiveto, paidfrom, paidto, typeprint,printby,refundBy);
        }else if(TicketProfitLoss.equals(name)){
            data = reportservice.getTicketProfitLoss(invoiceFromDate,invoiceToDate,printby);
        }else if(TicketSummaryCommission.equals(name)){
            // Ticket commission summary
            String invoicefromdate = request.getParameter("invoiceFromDate");
            String invoicetodate = request.getParameter("invoiceToDate");
            String issuefromdate = request.getParameter("issueFrom");
            String issuetodate = request.getParameter("issueTo");
//            String agentcomfromdate = request.getParameter("agentcomFrom");
//            String agentcomtodate = request.getParameter("agentcomTo");
//            String ticketcomfromdate = request.getParameter("ticketcomFrom");
//            String ticketcomtodate = request.getParameter("ticketcomTo");
            String overfromdate = request.getParameter("overFrom");
            String overtodate = request.getParameter("overTo");
            String littlefromdate = request.getParameter("littleFrom");
            String littletodate = request.getParameter("littleTo");
            String agemtcomreceivefromdate = request.getParameter("agentcomreceiveFrom");
            String agemtcomreceivetodate = request.getParameter("agentcomreceiveTo");
            String comrefundfromdate = request.getParameter("comrefundFrom");
            String comrefundtodate = request.getParameter("comrefundTo");
            String addpayfromdate = request.getParameter("addpayFrom");
            String addpaytodate = request.getParameter("addpayTo");
            String decreasepayfromdate = request.getParameter("decreasepayFrom");
            String decreasepaytodate = request.getParameter("decreasepayTo");
            String typeRoutings = request.getParameter("typeRouting");
            String routingDetails = request.getParameter("routingDetail");
            String airlineCodes = request.getParameter("airlineCode");
            String agentCodes = request.getParameter("agentCode");
            String agentName = request.getParameter("agentName");
            String ticketno = request.getParameter("ticketno");
            String departmentts = request.getParameter("department");
            String salebyUserts = request.getParameter("salebyUser");
            String salebyName = request.getParameter("salebyName");
            String termPayts   = request.getParameter("termPay");     
            System.out.println(" ============ invoicefromdate =========== " + invoicefromdate);
            data = reportservice.getTicketSummaryCommission(invoicefromdate, invoicetodate, issuefromdate, issuetodate, overfromdate, overtodate, littlefromdate, littletodate,
                    agemtcomreceivefromdate, agemtcomreceivetodate, comrefundfromdate, comrefundtodate, addpayfromdate, addpaytodate, 
                    decreasepayfromdate, decreasepaytodate, typeRoutings, routingDetails, airlineCodes, agentCodes, agentName, ticketno,
                    departmentts, salebyUserts, salebyName, termPayts, printby);
        }
		
        return new ModelAndView("ExportDataToExcelView",name,data).addObject(ReportName, name);
        
    }

    public ReportService getReportservice() {
        return reportservice;
    }

    public void setReportservice(ReportService reportservice) {
        this.reportservice = reportservice;
    }
}
