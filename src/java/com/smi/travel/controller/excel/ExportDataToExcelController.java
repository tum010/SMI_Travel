/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller.excel;

import com.smi.travel.datalayer.entity.MDefaultData;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.ReportService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.SMITravelController;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Jittima
 */
public class ExportDataToExcelController  extends SMITravelController{
    private UtilityService utilityService;
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
    private static final String SaleVatReport = "SaleVatReport";
    private static final String OutboundProduct = "OutboundProduct";
    private static final String OutboundPackageSummary = "OutboundPackageSummary";
    private static final String OutboundHotelSummary = "OutboundHotelSummary";
    private static final String Overdue = "Overdue";
    private static final String PaymentSummaryReport = "PaymentSummaryReport";
    private static final String BookingNonInvoiceSummary = "BookingNonInvoiceSummary";
    private static final String BookingInvoiceSummary = "BookingInvoiceSummary";     
    private static final String StockInvoiceSummary = "StockInvoiceSummary";
    private static final String StockNonInvoiceSummary = "StockNonInvoiceSummary";
    private static final String PaymentProfitLossSummary = "PaymentProfitLossSummary";
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String output =  request.getParameter("output");
        String name = request.getParameter(ReportName);
        
        SystemUser user = (SystemUser) session.getAttribute("USER");
        String printby = user.getName();
        List data  = new ArrayList();
        
        try {
        String ticketType = request.getParameter("ticketType");
        String ticketBuy = request.getParameter("ticketBuy");
        String airline = request.getParameter("airline");
        String airlineC = request.getParameter("airlineC");
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
        String agentId = (request.getParameter("agentId") != null && !"".equalsIgnoreCase(request.getParameter("agentId")) ? new String(request.getParameter("agentId").getBytes("ISO8859_1"),"UTF-8") : "");

        String invoiceFromDate = request.getParameter("invoiceFromDate");
        String invoiceToDate = request.getParameter("invoiceToDate");
        String issueFrom = request.getParameter("issueFrom");
        String issueTo = request.getParameter("issueTo");
        String paymentType = request.getParameter("paymentType");
        String departmentt = request.getParameter("department");
        String salebyUser = (request.getParameter("salebyName") != null && !"".equalsIgnoreCase(request.getParameter("salebyName")) ? new String(request.getParameter("salebyName").getBytes("ISO8859_1"),"UTF-8") : "");
        String termPayt = request.getParameter("termPay");
        
        MDefaultData mdVat = utilityService.getMDefaultDataFromType("vat");
        MDefaultData mdWht = utilityService.getMDefaultDataFromType("withholding tax");
        String vatMDE = mdVat.getValue();
        String whtMDE = mdWht.getValue();
        System.out.println(" name +++++++++++++++ " + name);
        if(TicketFareReport.equalsIgnoreCase(name)){
            System.out.println("get excel data");
            data = reportservice.getTicketFareReport(ticketType,ticketBuy,airline,airlineC,airlineCode,dateFrom,dateTo,department,staff,termPay,printby,invdateFrom,invdateTo,"Ticketfare");
            return new ModelAndView("TicketFareSummary",name,data).addObject(ReportName, name);
        }else if(TicketFareAirlineReport.equalsIgnoreCase(name)){
            System.out.println("get excel data");
            data = reportservice.getTicketFareReport(ticketType,ticketBuy,airline,airlineC,airlineCode,issuedateFrom,issuedateTo,department,staff,termPay,printby,invdateFrom,invdateTo,"TicketFareAirline");
            return new ModelAndView("TicketFareSummary",name,data).addObject(ReportName, name);
        }else if(TicketFareInvoicReport.equalsIgnoreCase(name)){
            System.out.println("get excel data");
            data = reportservice.getTicketFareReport(ticketType,ticketBuy,airline,airlineC,airlineCode,issuedateFrom,issuedateTo,department,staff,termPay,printby,invdateFrom,invdateTo,"TicketFareInvoice");
            System.out.println(" data size " + String.valueOf(data.size()));
            return new ModelAndView("TicketFareSummary",name,data).addObject(ReportName, name);
        }else if(TicketFareAgentReport.equalsIgnoreCase(name)){
            System.out.println("get excel data");
            data = reportservice.getTicketFareReport(ticketType,ticketBuy,airline,airlineC,airlineCode,issuedateFrom,issuedateTo,department,staff,termPay,printby,invdateFrom,invdateTo,"TicketFareAgent");
            return new ModelAndView("TicketFareSummary",name,data).addObject(ReportName, name);
        }else if(TicketFareSummaryByStaff.equalsIgnoreCase(name)){
            System.out.println("get excel data staff");
            data = reportservice.getTicketFareSumAgentStaff(ticketType, ticketBuy, airline, airlineCode, department, staff, termPay, printby, issuedateFrom, issuedateTo, invdateFrom, invdateTo,"staff");
            return new ModelAndView("TicketFareSummary",name,data).addObject(ReportName, name);
        }else if(TicketFareSummaryByAgent.equalsIgnoreCase(name)){
            System.out.println("get excel data agent");
            data = reportservice.getTicketFareSumAgentStaff(ticketType, ticketBuy, airline, airlineCode, department, staff, termPay, printby, issuedateFrom, issuedateTo, invdateFrom, invdateTo,"agent");
            return new ModelAndView("TicketFareSummary",name,data).addObject(ReportName, name);
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
            String paymentTypes = request.getParameter("paymentType");
            String vatTemp = request.getParameter("vatTemp");
            String whtTemp = request.getParameter("whtTemp");
            agentCode = new String(agentCode.getBytes("ISO8859_1"),"UTF-8");
            salebyUsers = new String(salebyUsers.getBytes("ISO8859_1"),"UTF-8");
            System.out.println("termPays : "+termPays);
            data = reportservice.getBillAirAgentReportSummary(agentCode, invoiceFromDates, InvoiceToDates, issueFroms, issueTos, refundFrom, refundTo, departments, salebyUsers, termPays,printby,paymentTypes,vatMDE,whtMDE);
            return new ModelAndView("BillAirAgentSummary",name,data).addObject(ReportName, name);
        }else if(ChangeARReport.equalsIgnoreCase(name)){
            System.out.println("get excel data agent");
            data = reportservice.SearchArNirvanaFromFilter(invoiceType, departmnt, type, from, to, status,accno);
            return new ModelAndView("AccountReportSummary",name,data).addObject(ReportName, name);
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
            String paymentTypes = request.getParameter("paymentType");
            String vatTemp = request.getParameter("vatTemp");
            String whtTemp = request.getParameter("whtTemp");
            agentCode = new String(agentCode.getBytes("ISO8859_1"),"UTF-8");
            salebyUsers = new String(salebyUsers.getBytes("ISO8859_1"),"UTF-8");
            data = reportservice.getBillAirAgentReportSummary(agentCode, invoiceFromDates, InvoiceToDates, issueFroms, issueTos, refundFrom, refundTo, departments, salebyUsers, termPays, printby,paymentTypes,vatMDE,whtMDE);
            return new ModelAndView("BillAirAgentSummary",name,data).addObject(ReportName, name);
        }else if(CollectionReport.equalsIgnoreCase(name)){
            //Collectipn Report
            type = request.getParameter("type");
            status = request.getParameter("status");
            from = request.getParameter("inputFromDate");
            to = request.getParameter("inputToDate");
            String invno = request.getParameter("invno");
            System.out.println("get excel data collection report");
            data = reportservice.getCollectionNirvanaFromFilter(department, type, status, from, to, invno, printby); 
            return new ModelAndView("AccountReportSummary",name,data).addObject(ReportName, name);
        }else if(ApReport.equals(name)){
            status = request.getParameter("status");
            String payment = request.getParameter("payment");
            System.out.println("get excel data ap ApReport");
            data = reportservice.getApNirvanaReport(payment, ticketType, status, dateFrom, dateTo, printby);
            return new ModelAndView("AccountReportSummary",name,data).addObject(ReportName, name);
        }else if(SummaryAirline.equals(name)){
            System.out.println("get excel data ap SummaryAirline");
            data = reportservice.listSummaryAirline();
            return new ModelAndView("AirlineSummaryReport",name,data).addObject(ReportName, name);
        }else if(TicketFareSummaryAirline.equals(name)){
            String staffss = request.getParameter("salebyUser");       
            System.out.println("get excel data TicketFareSummaryAirline " + staffss);
            data = reportservice.getTicketFareSumAirline(typeRouting,routingDetail,issuedateFrom,issuedateTo,invdateFrom,invdateTo,airlineCode,passenger,agentId,department,staffss,termPay,printby);
            return new ModelAndView("AirlineSummaryReport",name,data).addObject(ReportName, name);
        }else if(SummaryAirlinePax.equals(name)){
            String staffss = request.getParameter("salebyUser");        
            System.out.println("get excel data SummaryAirlinePax " + staffss);
            data = reportservice.getSumAirlinePax(typeRouting,routingDetail,issuedateFrom,issuedateTo,invdateFrom,invdateTo,airlineCode,passenger,agentId,department,staffss,termPay,printby);
            return new ModelAndView("AirlineSummaryReport",name,data).addObject(ReportName, name);
        }else if(SummaryTicketAdjustCostAndIncome.equals(name)){
            System.out.println("get excel data ap SummaryTicketAdjustCostAndIncome");
            System.out.println("Term : " + termPayt);          
            data = reportservice.getSummaryTicketAdjustCostAndIncome(reportType, invoiceFromDate, invoiceToDate, issueFrom, issueTo, paymentType, departmentt, salebyUser, termPayt,printby);
            return new ModelAndView("CostIncomeSummary",name,data).addObject(ReportName, name);
        }else if(SummaryTicketCostAndIncome.equals(name)){
            System.out.println("get excel data ap SummaryTicketCostAndIncome");
            data = reportservice.getSummaryTicketCostAndIncome(reportType, invoiceFromDate, invoiceToDate, issueFrom, issueTo, paymentType, departmentt, salebyUser, termPayt,printby);
            return new ModelAndView("CostIncomeSummary",name,data).addObject(ReportName, name);
        }else if(SummaryTicketCommissionReceive.equals(name)){
            System.out.println("get excel data ap SummaryTicketCommissionReceive");
            System.out.println("Term : " + termPayt);       
            data = reportservice.getTicketCommissionReceive(reportType, invoiceFromDate, invoiceToDate, issueFrom, issueTo, paymentType, departmentt, salebyUser, termPayt,printby);
            return new ModelAndView("CostIncomeSummary",name,data).addObject(ReportName, name);
        }else if(RefundTicketDetail.equals(name)){
            System.out.println("get excel data ap RefundTicketDetail");
            System.out.println("Term : " + termPayt);    
            String refundagent = request.getParameter("refundAgentId");
            String refundnameby = request.getParameter("refundByName");
            String refundBy = request.getParameter("refundBy");
            String passengername = request.getParameter("passenger");
            String receivefrom = request.getParameter("receiveFromDate");
            String receiveto = request.getParameter("receiveToDate");
            String paidfrom = request.getParameter("paidFromDate");
            String paidto = request.getParameter("paidToDate");
            String typeprint = request.getParameter("typePrint");
            String sectortoberef = request.getParameter("sectortoberef");
            refundagent = new String(refundagent.getBytes("ISO8859_1"),"UTF-8");
            refundnameby = new String(refundnameby.getBytes("ISO8859_1"),"UTF-8");
            refundBy = new String(refundBy.getBytes("ISO8859_1"),"UTF-8");
            passengername = new String(passengername.getBytes("ISO8859_1"),"UTF-8");
            data = reportservice.getRefundTicketDetail(refundagent, refundnameby, passengername, receivefrom, receiveto, paidfrom, paidto, typeprint,printby,refundBy,sectortoberef);
            return new ModelAndView("RefundAirsummary",name,data).addObject(ReportName, name);
        }else if(TicketProfitLoss.equals(name)){
            data = reportservice.getTicketProfitLoss(invoiceFromDate,invoiceToDate,printby);
            return new ModelAndView("CheckingAirOthersummary",name,data).addObject(ReportName, name);
        }else if(TicketSummaryCommission.equals(name)){
            // Ticket commission summary
            String invoicefromdate = request.getParameter("invoiceFromDate");
            String invoicetodate = request.getParameter("invoiceToDate");
            String issuefromdate = request.getParameter("issueFrom");
            String issuetodate = request.getParameter("issueTo");
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
            
            agentCodes = new String(agentCodes.getBytes("ISO8859_1"),"UTF-8");
            agentName = new String(agentName.getBytes("ISO8859_1"),"UTF-8");
            
            System.out.println(" ============ invoicefromdate =========== " + invoicefromdate);
            data = reportservice.getTicketSummaryCommission(invoicefromdate, invoicetodate, issuefromdate, issuetodate, overfromdate, overtodate, littlefromdate, littletodate,
                    agemtcomreceivefromdate, agemtcomreceivetodate, comrefundfromdate, comrefundtodate, addpayfromdate, addpaytodate, 
                    decreasepayfromdate, decreasepaytodate, typeRoutings, routingDetails, airlineCodes, agentCodes, agentName, ticketno,
                    departmentts, salebyUserts, salebyName, termPayts, printby);
            return new ModelAndView("CheckingAirOthersummary",name,data).addObject(ReportName, name);
        }else if(SaleVatReport.equals(name)){
            System.out.println("get excel data SaleVatReport");
            String selectMonth   = request.getParameter("selectMonth");   
            String selectYear   = request.getParameter("selectYear");   
            String selectDepartment   = request.getParameter("selectDepartment");   
            data = reportservice.getSaleVatReportList(selectMonth, selectYear, selectDepartment);
            return new ModelAndView("AccountReportSummary",name,data).addObject(ReportName, name);
        }else if(OutboundProduct.equals(name)){
            String productidout = request.getParameter("productid");    
            String fromout = request.getParameter("fromdate");    
            String toout = request.getParameter("todate");    
            String salebyout = request.getParameter("saleby");    
            String paybyout = request.getParameter("payby");    
            String bankout = request.getParameter("bank");
            String productname = request.getParameter("productname");
            String salename = request.getParameter("salename");
            String statusout = request.getParameter("status");
            productname = new String(productname.getBytes("ISO8859_1"),"UTF-8");
            salename = new String(salename.getBytes("ISO8859_1"),"UTF-8");
            data = reportservice.getOutboundProductSummary(productidout, fromout, toout, salebyout, paybyout, bankout, printby,productname,salename,statusout);
            return new ModelAndView("OutboundProduct",name,data).addObject(ReportName, name);
        }else if(OutboundPackageSummary.equals(name)){
            String fromdate   = request.getParameter("fromdate");   
            String todate   = request.getParameter("todate");   
            String salebyId   = request.getParameter("saleby");   
            String paybyId   = request.getParameter("payby");   
            String bankId   = request.getParameter("banktran");   
            String statusId   = request.getParameter("status");   
            String cityId   = request.getParameter("city");  
            String packageId   = request.getParameter("package");  
            data = reportservice.getOutboundPackageSummaryReportList(fromdate, todate, cityId, packageId, salebyId, paybyId, bankId, statusId);
            return new ModelAndView("OutboundPackageSummaryReport",name,data).addObject(ReportName, name);
        }else if(OutboundHotelSummary.equals(name)){
            String hotelidout = request.getParameter("hotelid");    
            String fromout = request.getParameter("fromdate");    
            String toout = request.getParameter("todate");    
            String salebyout = request.getParameter("saleby");    
            String paybyout = request.getParameter("payby");    
            String bankout = request.getParameter("bank");
            String statusout = request.getParameter("status");
            String cityout = request.getParameter("city");
            String countryout = request.getParameter("country");
            
            hotelidout = new String(hotelidout.getBytes("ISO8859_1"),"UTF-8");
            cityout = new String(cityout.getBytes("ISO8859_1"),"UTF-8");
            countryout = new String(countryout.getBytes("ISO8859_1"),"UTF-8");
            
            data = reportservice.getOutboundHotelSummary(hotelidout, fromout, toout, salebyout, paybyout, bankout, statusout, cityout, countryout, printby);
            return new ModelAndView("OutboundHotelSummaryReport",name,data).addObject(ReportName, name);
        }else if(Overdue.equals(name)){
            System.out.println("get excel data ap OverdueSummary");  
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
            
            clientcode_over = new String(clientcode_over.getBytes("ISO8859_1"),"UTF-8");
            clientname_over = new String(clientname_over.getBytes("ISO8859_1"),"UTF-8");

            data = reportservice.listOverdueSummary(clientcode_over, clientname_over, staffcode_over, staffname_over, vattype_over, from_over, to_over, department_over, group_over, view_over, printby);
            return new ModelAndView("OverdueSummaryExcel",name,data).addObject(ReportName, name);
        }else if(PaymentSummaryReport.equals(name)){
            System.out.println("get excel data ap PaymentSummaryReport");  
            String from_payments = request.getParameter("from");
            String to_payments = request.getParameter("to");
            String invSupCode = request.getParameter("invSupCode");
            String saleby = request.getParameter("salebyUser");
            String refno = request.getParameter("refno");
            String invto = request.getParameter("invto");
            String invfrom = request.getParameter("invfrom");
            String billname = request.getParameter("billname");
            String billnamedetail = request.getParameter("billnamedetail");
            String productid = request.getParameter("productid");
            String country = request.getParameter("country");
            String city = request.getParameter("city");
            String paytype = request.getParameter("paytype");
            String productname = request.getParameter("productname");
            
            invSupCode = new String(invSupCode.getBytes("ISO8859_1"),"UTF-8");
            invto = new String(invto.getBytes("ISO8859_1"),"UTF-8");
            invfrom = new String(invfrom.getBytes("ISO8859_1"),"UTF-8");
            billname = new String(billname.getBytes("ISO8859_1"),"UTF-8");
            billnamedetail = new String(billnamedetail.getBytes("ISO8859_1"),"UTF-8");
            productid = new String(productid.getBytes("ISO8859_1"),"UTF-8");
            country = new String(country.getBytes("ISO8859_1"),"UTF-8");
            city = new String(city.getBytes("ISO8859_1"),"UTF-8");
            productname = new String(productname.getBytes("ISO8859_1"),"UTF-8");
            
            data = reportservice.getPaymentSummaryReport(from_payments, to_payments,saleby,invSupCode, refno, printby ,invto,invfrom,billname,productid,country,city,paytype,billnamedetail,productname);
            return new ModelAndView("OutboundProduct",name,data).addObject(ReportName, name);
        }else if(BookingInvoiceSummary.equals(name)){
            String owner = request.getParameter("owner");
            String invto = request.getParameter("invto");
            String bookdatefrom = request.getParameter("bookdatefrom");
            String bookdateto = request.getParameter("bookdateto");
            String invdatefrom = request.getParameter("invdatefrom");
            String invdateto = request.getParameter("invdateto");
            owner = new String(owner.getBytes("ISO8859_1"),"UTF-8");
            invto = new String(invto.getBytes("ISO8859_1"),"UTF-8");
            data = reportservice.getBookingInvoiceReport(owner, invto, bookdatefrom, bookdateto, invdatefrom, invdateto, printby );
            return new ModelAndView("BookingInvoiceSummary",name,data).addObject(ReportName, name);
        }else if(BookingNonInvoiceSummary.equals(name)){
            String owner = request.getParameter("owner");
            String invsup = request.getParameter("invsup");
            String bookdatefrom = request.getParameter("bookdatefrom");
            String bookdateto = request.getParameter("bookdateto");
            String paydatefrom = request.getParameter("paydatefrom");
            String paydateto = request.getParameter("paydateto");
            owner = new String(owner.getBytes("ISO8859_1"),"UTF-8");
            invsup = new String(invsup.getBytes("ISO8859_1"),"UTF-8");
            data = reportservice.getBookingNonInvoiceReport(owner, invsup, bookdatefrom, bookdateto, paydatefrom, paydateto, printby );
            return new ModelAndView("BookingInvoiceSummary",name,data).addObject(ReportName, name);
        }else if(StockInvoiceSummary.equals(name)){
            String product = request.getParameter("product");
            String invTo = request.getParameter("invTo");
            String effectiveDateFrom = request.getParameter("effectiveDateFrom");
            String effectiveDateTo = request.getParameter("effectiveDateTo");
            String invoiceDateFrom = request.getParameter("invoiceDateFrom");
            String invoiceDateTo = request.getParameter("invoiceDateTo");
            String addDate = request.getParameter("addDate");
            product = new String(product.getBytes("ISO8859_1"),"UTF-8");
            invTo = new String(invTo.getBytes("ISO8859_1"),"UTF-8");
            data = reportservice.getStockInvoiceSummaryReport(product, invTo, effectiveDateFrom, effectiveDateTo, invoiceDateFrom, invoiceDateTo, addDate, printby);
            return new ModelAndView("OverdueSummaryExcel",name,data).addObject(ReportName, name);
        }else if(StockNonInvoiceSummary.equals(name)){
            String product = request.getParameter("product");
            String invoiceSup = request.getParameter("invoiceSup");
            String effectiveDateFrom = request.getParameter("effectiveDateFrom");
            String effectiveDateTo = request.getParameter("effectiveDateTo");
            String payDateFrom = request.getParameter("payDateFrom");
            String payDateTo = request.getParameter("payDateTo");
            String addDate = request.getParameter("addDate");
            product = new String(product.getBytes("ISO8859_1"),"UTF-8");
            invoiceSup = new String(invoiceSup.getBytes("ISO8859_1"),"UTF-8");
            data = reportservice.getStockNonInvoiceSummaryReport(product, invoiceSup, effectiveDateFrom, effectiveDateTo, payDateFrom, payDateTo, addDate, printby);
            return new ModelAndView("OverdueSummaryExcel",name,data).addObject(ReportName, name);
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
            ownercode = new String(ownercode.getBytes("ISO8859_1"),"UTF-8");
            city = new String(city.getBytes("ISO8859_1"),"UTF-8");
            producttypeid = new String(producttypeid.getBytes("ISO8859_1"),"UTF-8");
            invsupcode = new String(invsupcode.getBytes("ISO8859_1"),"UTF-8");
            data = reportservice.getPaymentProfitLossReport(departFromDate, departToDate, invFromDate, invToDate, ownercode, city, producttypeid, invsupcode, payFromDate, payToDate, groupby);
            return new ModelAndView("OutboundProduct",name,data).addObject(ReportName, name);
        }

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ExportDataToExcelController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ModelAndView("ExportDataToExcelView",name,data).addObject(ReportName, name);
        
    }

    public ReportService getReportservice() {
        return reportservice;
    }

    public void setReportservice(ReportService reportservice) {
        this.reportservice = reportservice;
    }

    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }
}
