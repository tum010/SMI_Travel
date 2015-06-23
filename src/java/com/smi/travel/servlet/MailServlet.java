/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.servlet;

import com.smi.travel.datalayer.ajax.service.AbstractAJAXServlet;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jittima
 */
public class MailServlet implements AbstractAJAXServlet {

    @Override
    public Map process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletName = request.getParameter("servletName");
        String Name = request.getParameter("name");
        String sendTo = request.getParameter("sendTo");
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");
        String sendCc = request.getParameter("sendCc");
        String attachfile = request.getParameter("attachfile");
        String type = request.getParameter("type");
        System.out.print("set parameter mapping");
        Map result = new HashMap();
        result.put("servletName", servletName);
        result.put("name", Name);
        result.put("sendTo", sendTo);
        result.put("subject", subject);
        result.put("content", content);
        result.put("sendCc", sendCc);
        result.put("attachfile", attachfile);
        result.put("type", type);
        return result;    
    }
    
}
