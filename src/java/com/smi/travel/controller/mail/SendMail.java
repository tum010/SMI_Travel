/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.controller.mail;

import com.smi.travel.common.MailConfig;
import com.smi.travel.datalayer.service.ReportService;
import com.smi.travel.util.Mail;
import java.io.File;
import java.net.MalformedURLException;
import java.util.logging.Level;
import javax.sql.DataSource;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;

/**
 *
 * @author chonnasith
 */
public class SendMail {
    private JavaMailSender mailSender;
    private ReportService reportservice;
    private DataSource datasource;
    private Mail sendMail;
    private MailConfig mail;
    
    private static final String hostname = "mail.wendytour.com";
    private static final int port = 587;
    private static final String username = "supavadee@wendytour.com";
    private static final String password = "Pa55w0rd";
//    
//    private static final String hostname = "smtp.gmail.com";
//    private static final int port = 465;
//    private static final String username = "surachaisvn@gmail.com";
//    private static final String password = "uytjhgmnb";
    
    private static final String subject = "Test Email";
    private static final String addto = "wee.chonnasith@gmail.com";
    
    public static void main(String args[]) throws Exception {
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/Spring-Mail.xml");
//        mail = (MailConfig) applicationContext.getBean("mailSender");
        
        String result = "";
        boolean send = false;
        EmailAttachment attachment = new EmailAttachment();
        HtmlEmail email = new HtmlEmail();
        try {
//            if ((attachfile != null) && (!attachfile.equalsIgnoreCase(""))) {
                //attachment.setPath("C:\\Users\\Surachai\\Documents\\NetBeansProjects\\SMITravel\\test.txt");
                attachment.setPath("C:\\Users\\chonnasith\\Desktop\\test.txt");
//                attachment.setDisposition(EmailAttachment.ATTACHMENT);
                attachment.setDescription("file attachment");
                attachment.setName("test.txt");
                email.attach(attachment);
//            }
            send = true;
        } catch (EmailException ex) {
            System.out.println("Email Exception");
            ex.printStackTrace();
            result = "send email fail";
        }   
        if(send){
            email.setHostName(hostname);
            email.setSmtpPort(port);
            email.setAuthentication(username, password);
            email.setSSLOnConnect(true);
            email.setFrom(username);
            email.setSubject(subject);
//            if(StringUtils.isNotEmpty(content)){
//                content = content.replaceAll("(\r\n|\n)", "<br/>");
//                content = content.replaceAll(" ", "&nbsp;");
//                email.setHtmlMsg(content);
//            }else{
//                email.setHtmlMsg("  ");
//            }
//            sendTo = sendTo.replaceAll(";", "\\,");
//            String[] toSplit = sendTo.split("\\,");
//            for(int i=0;i<toSplit.length;i++){
//                System.out.println("Print toSplit" + toSplit[i]);
                email.addTo(addto);
//            }
//            if(!sendCc.isEmpty()){
//            sendCc = sendCc.replaceAll(";", "\\,");
//            String[] ccSplit = sendCc.split("\\,");
//            for(int i=0;i<ccSplit.length;i++){
//                System.out.println("Print ccSplit" + ccSplit[i]);
//                email.addCc(ccSplit[i]);
//            }}
            email.send();

//            result = "send email success";
            
            
        }
        
    }
    
}
