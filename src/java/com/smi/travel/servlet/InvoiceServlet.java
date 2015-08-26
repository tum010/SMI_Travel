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
 * @author Kanokporn
 */
public class InvoiceServlet implements AbstractAJAXServlet{

    @Override
    public Map process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map result = new HashMap();
        String servletName = request.getParameter("servletName");
        String type = request.getParameter("type");
        String searchRefNo = request.getParameter("refNo");
        String typeId = request.getParameter("typeId");
        String invType = request.getParameter("invType");
        
        result.put("servletName", servletName);
        result.put("invType", invType);
        result.put("type", type);
        result.put("typeId", typeId);
        result.put("refNo", searchRefNo);
        
        return result; 
    }
    
}
