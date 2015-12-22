/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller.mail;

import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.ReportService;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.Mail;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Surachai
 */
public class SendEmailController extends SMITravelController {

    private static final Logger LOG = Logger.getLogger(SendEmailController.class.getName());
    private static final String InvoiceReport = "Invoice";
    private static final String ReceiptEmail = "ReceiptEmail";
    private static final String TaxInvoiceEmail = "TaxInvoiceEmail";
    private static final String CreditNote = "CreditNote";
    private ModelAndView ModelMail = new ModelAndView("SendMail");
    private static final String ReportName = "reportname";
    private static final String ReportID = "reportid";
    private static final String BANKID = "bankid";
    private static final String SHOWSTAFF = "showstaff";
    private static final String SHOWLEADER = "showleader";
    private static final String OPTIONSENDMAIL = "optionsend";
    private static final String RECIPIENT = "recipient";
    private static final String SENDCC = "sendCc"; 
    private static final String SUBJECT = "subject";  
    private static final String MESSAGE = "message";       
    private static final String SIGN = "sign"; 
    private static final String InvoiceInboundRevenueEmail = "InvoiceInboundRevenueEmail";
    private static final String InvoiceInboundPerformaEmail = "InvoiceInboundPerformaEmail";        
    
    private JavaMailSender mailSender;
    private ReportService reportservice;
    private DataSource datasource;
    private Mail sendMail;
    private static  final String TransectionResult = "result";

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        // takes input from e-mail form
        String recipientAddress = request.getParameter("recipient");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");
        String cc = request.getParameter("sendCc");
        String name = request.getParameter("reportname");
        String reportid = request.getParameter("reportid");
        String bankid = request.getParameter("bankid");
        String showstaff = request.getParameter("showstaff");
        String showleader = request.getParameter("showleader");
        String reportFile = request.getParameter("file");
        String sign = request.getParameter("sign");
        String jasperFileName = "";
        String pdfFileName = "";
        String optionsend = request.getParameter("optionsend");
        int option = Integer.parseInt(optionsend == null || "".equalsIgnoreCase(optionsend) ? "0":optionsend);
        //mail.smi?recipient=surachai@iconext.co.th&subject=testingsendmail&message=helloworld
        // prints debug info
        System.out.println("To: " + recipientAddress);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);
        String result = "";
        List data = new ArrayList();
        String pathAttachfile = "";
        SystemUser user = (SystemUser) session.getAttribute("USER");
        String username = user.getUsername();
        String[] path = reportservice.getPartReport();
        if(cc == null){
            cc = user.getEmail();
        }

        if ((recipientAddress != null) && (!"".equalsIgnoreCase(recipientAddress))) {
            if (InvoiceReport.equalsIgnoreCase(name)) {
                data = reportservice.getInvoice(reportid,bankid,showstaff,showleader,sign,user.getName());
                JRDataSource dataSource = new JRBeanCollectionDataSource(data);
                jasperFileName = "InvoiceEmail.jasper";
                pdfFileName = "invoice.pdf";
                pathAttachfile = path[0] + "\\" + username;
                System.out.println("path : " + path[0] + username);
                if (checkDirectory(path[0] + username)) {
                    result = reportservice.printreport(jasperFileName, username + "\\" + pdfFileName, dataSource);
                }
            }
            if (ReceiptEmail.equalsIgnoreCase(name)) {
                data = reportservice.getReceiptEmail(reportid,option);
                JRDataSource dataSource = new JRBeanCollectionDataSource(data);
                jasperFileName = "ReceiptEmail.jasper";
                pdfFileName = "receipt.pdf";
                pathAttachfile = path[0] + "\\" + username;
                System.out.println("path : " + path[0] + username);
                if (checkDirectory(path[0] + username)) {
                    result = reportservice.printreport(jasperFileName, username + "\\" + pdfFileName, dataSource);
                }
            }
            if (TaxInvoiceEmail.equalsIgnoreCase(name)) {
                data = reportservice.getTaxInvoiceEmail(reportid,option);
                JRDataSource dataSource = new JRBeanCollectionDataSource(data);
                jasperFileName = "TaxInvoiceEmailReport.jasper";
                pdfFileName = "taxinvoice.pdf";
                pathAttachfile = path[0] + "\\" + username;
                System.out.println("path : " + path[0] + username);
                if (checkDirectory(path[0] + username)) {
                    result = reportservice.printreport(jasperFileName, username + "\\" + pdfFileName, dataSource);
                }
            }
            if (CreditNote.equalsIgnoreCase(name)) {
                data = reportservice.getCreditNoteReport(reportid);
                JRDataSource dataSource = new JRBeanCollectionDataSource(data);
                jasperFileName = "CreditNote.jasper";
                pdfFileName = "creditnote.pdf";
                pathAttachfile = path[0] + "\\" + username;
                System.out.println("path : " + path[0] + username);
                if (checkDirectory(path[0] + username)) {
                    result = reportservice.printreport(jasperFileName, username + "\\" + pdfFileName, dataSource);
                }
             }
            if (InvoiceInboundRevenueEmail.equalsIgnoreCase(name)) {
                data = reportservice.getInvoice(reportid,bankid,showstaff,showleader,sign,user.getName());
                JRDataSource dataSource = new JRBeanCollectionDataSource(data);
                jasperFileName = "InvoiceInboundRevenueEmail.jasper";
                pdfFileName = "InvoiceInboundRevenueEmail.pdf";
                pathAttachfile = path[0] + "\\" + username;
                System.out.println("path : " + path[0] + username);
                if (checkDirectory(path[0] + username)) {
                    result = reportservice.printreport(jasperFileName, username + "\\" + pdfFileName, dataSource);
                }
            }
            if (InvoiceInboundPerformaEmail.equalsIgnoreCase(name)) {
                data = reportservice.getInvoice(reportid,bankid,showstaff,showleader,sign,user.getName());
                JRDataSource dataSource = new JRBeanCollectionDataSource(data);
                jasperFileName = "InvoiceInboundPerformaEmail.jasper";
                pdfFileName = "InvoiceInboundPerformaEmail.pdf";
                pathAttachfile = path[0] + "\\" + username;
                System.out.println("path : " + path[0] + username);
                if (checkDirectory(path[0] + username)) {
                    result = reportservice.printreport(jasperFileName, username + "\\" + pdfFileName, dataSource);
                }
            }
        }             
            

        System.out.println("result : " + result);
        // sends the e-mail
        if ("success".equalsIgnoreCase(result)) {
            if ((recipientAddress != null) && (!"".equalsIgnoreCase(recipientAddress))) {

                System.out.println("pdfFileName : " + path[0] + pdfFileName);
                try {
                    result = Mail.main(recipientAddress, subject, message, pathAttachfile, pdfFileName, cc);
                    File file = new File(pathAttachfile + "\\" + pdfFileName);

                    System.out.println("delete file : " + pathAttachfile);
                    if (file.exists()) {
                        file.delete();
                    } else {
                        System.out.println("file not found");
                    }

                } catch (EmailException ex) {
                    result = "send email fail";
                    java.util.logging.Logger.getLogger(SendEmailController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    result = "send email fail";
                    java.util.logging.Logger.getLogger(SendEmailController.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("send email");
            }
        }
        request.setAttribute(TransectionResult, result);
        request.setAttribute(ReportName, name);
        request.setAttribute(ReportID, reportid);
        request.setAttribute(BANKID, bankid);
        request.setAttribute(SHOWSTAFF, showstaff);
        request.setAttribute(SHOWLEADER, showleader);
        request.setAttribute(OPTIONSENDMAIL, optionsend);
        request.setAttribute(RECIPIENT, recipientAddress);
        request.setAttribute(SENDCC, cc);
        request.setAttribute(SUBJECT, subject);
        request.setAttribute(MESSAGE, message);
        request.setAttribute(SIGN, sign);
        return ModelMail;
    }

    public boolean checkDirectory(String pathFile){
        File f = new File(pathFile);
        if (!f.exists()) {
            new File(pathFile).mkdir();
        }
        return f.exists();
    }

    
    

    public JavaMailSender getMailSender() {
        return mailSender;
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
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
