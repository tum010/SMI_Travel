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
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.logging.Level;
import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
    
//    private static final String hostname = "mail.wendytour.com";
    private static final int port = 587;
    private static final String username = "finance@wendytour";
    private static final String password = "wendytr";
  
    private static final String hostname = "smtp.gmail.com";
//    private static final int port = 465;
//    private static final String username = "surachaisvn@gmail.com";
//    private static final String password = "uytjhgmnb";
    
    private static final String subject = "Test Email";
    private static final String addto = "wee.chonnasith@gmail.com";
    private static final String message = "Hello World";
    
    public static void main(String args[]) throws Exception {       
        EmailAttachment attachment = new EmailAttachment();
        HtmlEmail email = new HtmlEmail();
        try {
//            attachment.setPath("C:\\Users\\chonnasith\\Desktop\\test.txt");
//            attachment.setDescription("file attachment");
//            attachment.setName("test.txt");
//            email.attach(attachment);
            email.setHostName(hostname);
            email.setSmtpPort(port);
            email.setAuthentication(username, password);
            email.setSSLOnConnect(true);
//            email.setStartTLSEnabled(true);
//            email.setStartTLSRequired(true);
            email.setFrom(username);
            email.setSubject(subject);
            email.addTo(addto);
            email.setHtmlMsg(message);
            email.send();
            System.out.println("Email Send");
            System.out.println("Port : "+email.getSmtpPort());
        } catch (EmailException ex) {
            System.out.println("Email Exception");
            System.out.println("Port : "+email.getSmtpPort());
            ex.printStackTrace();
        } 
        
//        InetAddress host = InetAddress.getByName("mail.foobar.com");
//        System.out.println("host.isReachable(1000) = " + host.isReachable(1000));
        
//        int port = 587;
//        String host = "smtp.gmail.com";
//        String user = "username@gmail.com";
//        String pwd = "email password";
//
//        try {
//            Properties props = new Properties();
//            // required for gmail 
//            props.put("mail.smtp.starttls.enable","true");
//            props.put("mail.smtp.auth", "true");
//            // or use getDefaultInstance instance if desired...
//            Session session = Session.getInstance(props, null);
//            Transport transport = session.getTransport("smtp");
//            transport.connect(host, port, user, pwd);
//            transport.close();
//            System.out.println("success");
//         } 
//         catch(AuthenticationFailedException e) {
//               System.out.println("AuthenticationFailedException - for authentication failures");
//               e.printStackTrace();
//         }
//         catch(MessagingException e) {
//               System.out.println("for other failures");
//               e.printStackTrace();
//         }
        
//        Properties prop=new Properties();
//        prop.put("mail.smtp.auth", "true");
//        prop.put("mail.smtp.host", "smtp.gmail.com");
//        prop.put("mail.smtp.port", "587");
//        prop.put("mail.smtp.starttls.enable", "true");
//
//        Session session = Session.getDefaultInstance(prop,
//        new javax.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("finance@wendytour", "wendytr");
//          }
//        });
//        
//        try {
//            String body="Dear Renish Khunt Welcome";
//            String htmlBody = "<strong>This is an HTML Message</strong>";
//            String textBody = "This is a Text Message.";
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(username));
//            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(addto));
//            message.setSubject("Testing Subject");
//            MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
//            mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
//            mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
//            mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
//            mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
//            mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
//            CommandMap.setDefaultCommandMap(mc);
//            message.setText(htmlBody);
//            message.setContent(textBody, "text/html");
//            Transport.send(message);
//
//            System.out.println("Done");
//
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
        
//        final String fromEmail = "finance@wendytour"; //requires valid gmail id
//        final String password = "wendytr"; // correct password for gmail id
//        final String toEmail = "wee.chonnasith@gmail.com"; // can be any email id 
//         
//        System.out.println("TLSEmail Start");
//        Properties props = new Properties();
//        props.put("mail.smtp.host", ""); //SMTP Host
//        props.put("mail.smtp.port", "587"); //TLS Port
//        props.put("mail.smtp.auth", "true"); //enable authentication
//        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
//         
//                //create Authenticator object to pass in Session.getInstance argument
//        Authenticator auth = new Authenticator() {
//            //override the getPasswordAuthentication method
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(fromEmail, password);
//            }
//        };
//        Session session = Session.getInstance(props, auth);
//         
//        EmailUtil.sendEmail(session, toEmail,"TLSEmail Testing Subject", "TLSEmail Testing Body");
//         
        
                   
    }
    
}

//import java.util.Properties;
//
//import javax.mail.BodyPart;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Multipart;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//
//
//public class SendMail {
//    
//    private static final String hostname = "smtp.gmail.com";
//    private static final String port = "25";
//    private static final String username = "surachaisvn@gmail.com";
//    private static final String password = "uytjhgmnb";
//
//    public static void main(String args[]) throws Exception {
//        Properties props = System.getProperties();
//        props.put("mail.smtp.starttls.enable", true); // added this line
//        props.put("mail.smtp.host", hostname);
//        props.put("mail.smtp.user", username);
//        props.put("mail.smtp.password", password);
//        props.put("mail.smtp.port", port);
//        props.put("mail.smtp.auth", true);
//
//
//
//        Session session = Session.getInstance(props,null);
//        MimeMessage message = new MimeMessage(session);
//
//        System.out.println("Port: "+session.getProperty("mail.smtp.port"));
//
//        // Create the email addresses involved
//        try {
//            InternetAddress from = new InternetAddress(username);
//            message.setSubject("Yes we can");
//            message.setFrom(from);
//            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse("wee.chonnasith@gmail.com"));
//
//            // Create a multi-part to combine the parts
//            Multipart multipart = new MimeMultipart("alternative");
//
//            // Create your text message part
//            BodyPart messageBodyPart = new MimeBodyPart();
//            messageBodyPart.setText("some text to send");
//
//            // Add the text part to the multipart
//            multipart.addBodyPart(messageBodyPart);
//
//            // Create the html part
//            messageBodyPart = new MimeBodyPart();
//            String htmlMessage = "Our html text";
//            messageBodyPart.setContent(htmlMessage, "text/html");
//
//
//            // Add html part to multi part
//            multipart.addBodyPart(messageBodyPart);
//
//            // Associate multi-part with message
//            message.setContent(multipart);
//
//            // Send message
//            Transport transport = session.getTransport("smtp");
//            transport.connect(hostname, username, password);
//            System.out.println("Transport: "+transport.toString());
//            transport.sendMessage(message, message.getAllRecipients());
//
//
//        } catch (AddressException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (MessagingException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//}
