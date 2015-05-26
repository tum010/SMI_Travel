/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.util;

import com.smi.travel.common.MailConfig;
import java.net.MalformedURLException;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Surachai
 */
public class Mail {

    private MailConfig mail;

    public Mail() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/Spring-Mail.xml");
        mail = (MailConfig) applicationContext.getBean("mailSender");
    }

    public static void main(String[] args) throws EmailException, MalformedURLException {
        Mail mail = new Mail();
        mail.sendmailwithAttchfile("surachai.ns@gmail.com", "test", "test mail", "C:\\Users\\Surachai\\Documents\\NetBeansProjects\\SMITravel\\test.txt");
    }

    public void sendmailwithAttchfile(String to,String Subject,String content ,String attachfile) throws EmailException {
        EmailAttachment attachment = new EmailAttachment();
        HtmlEmail email = new HtmlEmail();
        try {
            if ((attachfile != null) && (!attachfile.equalsIgnoreCase(""))) {
                //attachment.setPath("C:\\Users\\Surachai\\Documents\\NetBeansProjects\\SMITravel\\test.txt");
                attachment.setPath(attachfile);
                attachment.setDisposition(EmailAttachment.ATTACHMENT);
                attachment.setDescription("test attachment");
                attachment.setName("test.txt");
                email.attach(attachment);
            }
        } catch (EmailException ex) {
            
        }
        System.out.println(mail.getUsername() + mail.getPassword());
        email.setHostName(mail.getHostname());
        email.setSmtpPort(mail.getPort());
        email.setAuthentication(mail.getUsername(), mail.getPassword());
        email.setSSLOnConnect(true);
        email.setFrom(mail.getUsername());
        email.setSubject(Subject);
        email.setHtmlMsg(content);
        email.addTo(to);

        email.send();
    }

}
