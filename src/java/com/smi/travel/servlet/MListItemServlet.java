
package com.smi.travel.servlet;

import com.smi.travel.datalayer.ajax.service.AbstractAJAXServlet;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chonnasith
 */
public class MListItemServlet implements AbstractAJAXServlet{

    @Override
    public Map process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletName = request.getParameter("servletName");
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        
        Map result = new HashMap();       
        result.put("servletName", servletName);
        result.put("name", name);
        result.put("type", type);
                 
        return result; 
    }
    
}
