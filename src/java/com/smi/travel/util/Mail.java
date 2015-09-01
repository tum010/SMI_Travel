/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.util;

import com.smi.travel.common.MailConfig;
import com.smi.travel.master.controller.SMITravelController;
import java.net.MalformedURLException;
import java.sql.Statement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Surachai
 */
public class Mail extends SMITravelController {
    private static final ModelAndView SendMail = new ModelAndView("SendMail");
    private MailConfig mail;

    public Mail() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/Spring-Mail.xml");
        mail = (MailConfig) applicationContext.getBean("mailSender");
    }

    public static String main(String sendTo,String subject,String content,String pathAttachfile,String attachfile,String sendCc) throws EmailException, MalformedURLException {
        String result = "";
        Mail mail = new Mail();
        result = mail.sendmailwithAttchfile(sendTo,subject,content,pathAttachfile,attachfile,sendCc);
        return result;
    }

    public String sendmailwithAttchfile(String sendTo,String subject,String content ,String pathAttachfile,String attachfile,String sendCc) throws EmailException {
        String result = "";
        boolean send = false;
        EmailAttachment attachment = new EmailAttachment();
        HtmlEmail email = new HtmlEmail();
        try {
            if ((attachfile != null) && (!attachfile.equalsIgnoreCase(""))) {
                //attachment.setPath("C:\\Users\\Surachai\\Documents\\NetBeansProjects\\SMITravel\\test.txt");
                attachment.setPath(pathAttachfile+"\\"+attachfile);
                attachment.setDisposition(EmailAttachment.ATTACHMENT);
                attachment.setDescription("file attachment");
                attachment.setName(attachfile);
                email.attach(attachment);
            }
            send = true;
        } catch (EmailException ex) {
            System.out.println("Email Exception");
            ex.printStackTrace();
            result = "send email fail";
        }   
        if(send){
            System.out.println(mail.getUsername() + mail.getPassword());
            email.setHostName(mail.getHostname());
            email.setSmtpPort(mail.getPort());
            email.setAuthentication(mail.getUsername(), mail.getPassword());
            email.setSSLOnConnect(true);
            email.setFrom(mail.getUsername());
            email.setSubject(subject);
            if(StringUtils.isNotEmpty(content)){
                content = content.replaceAll("(\r\n|\n)", "<br/>");
                content = content.replaceAll(" ", "&nbsp;");
                email.setHtmlMsg(content);
            }else{
                email.setHtmlMsg("  ");
            }
            String[] toSplit = sendTo.split("\\,");
            for(int i=0;i<toSplit.length;i++){
                System.out.println("Print toSplit" + toSplit[i]);
                email.addTo(toSplit[i]);
            }
            if(!sendCc.isEmpty()){
            String[] ccSplit = sendCc.split("\\,");
            for(int i=0;i<ccSplit.length;i++){
                System.out.println("Print ccSplit" + ccSplit[i]);
                email.addCc(ccSplit[i]);
            }}
            email.send();

            result = "send email success";
            
            
        }
        return result;
    }

    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
     return SendMail;
    }

    public Object sendmailwithAttchfile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
