/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller.excel;

import com.smi.travel.datalayer.report.model.TicketFareReport;
import com.smi.travel.datalayer.service.ReportService;
import com.smi.travel.master.controller.SMITravelController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private static final String ReportName = "name";
    private static final String ParaMeter = "parameter";
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
        
        List data  = new ArrayList();
        if(TicketFareReport.equalsIgnoreCase(name)){
            System.out.println("get excel data");
            data = reportservice.getTicketFareReport(ticketType,ticketBuy,airline,airlineCode,dateFrom,dateTo,department,staff,termPay);
        }else if(TicketFareAirlineReport.equalsIgnoreCase(name)){
            System.out.println("get excel data");
            data = reportservice.getTicketFareReport(ticketType,ticketBuy,airline,airlineCode,dateFrom,dateTo,department,staff,termPay);
        }else if(TicketFareInvoicReport.equalsIgnoreCase(name)){
            System.out.println("get excel data");
            data = reportservice.getTicketFareReport(ticketType,ticketBuy,airline,airlineCode,dateFrom,dateTo,department,staff,termPay);
        }else if(TicketFareAgentReport.equalsIgnoreCase(name)){
            System.out.println("get excel data");
            data = reportservice.getTicketFareReport(ticketType,ticketBuy,airline,airlineCode,dateFrom,dateTo,department,staff,termPay);
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
