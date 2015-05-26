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
 * @author Surachai
 */
public class BookDetailServlet implements AbstractAJAXServlet {
    
    @Override
    public Map process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String initialID = request.getParameter("initialID");
        String first = request.getParameter("first");
        String last = request.getParameter("last");
        String address = request.getParameter("address");
        String tel = request.getParameter("tel");
        String servletName = request.getParameter("servletName");
        String Type = request.getParameter("type");
        String name = request.getParameter("name");
        System.out.print("set parameter mapping");
        Map result = new HashMap();
        result.put("initialID", initialID);
        result.put("first", first);
        result.put("last", last);
        result.put("tel", tel);
        result.put("name", name);
        result.put("address", address);
        result.put("servletName", servletName);
        result.put("type", Type);
        
                 
        return result;
    }

}
