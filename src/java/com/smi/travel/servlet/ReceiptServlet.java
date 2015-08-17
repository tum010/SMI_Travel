/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates หห
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
public class ReceiptServlet implements AbstractAJAXServlet{

    @Override
    public Map process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String servletName = request.getParameter("servletName");
        String invoiceNo = request.getParameter("invoiceNo");
        String refNo = request.getParameter("refNo");
        String type = request.getParameter("type");
        
        Map result = new HashMap();
        
        result.put("invoiceNo", invoiceNo);
        result.put("refNo", refNo);
        result.put("servletName", servletName);
        result.put("type", type);
        
                 
        return result;    
    }
    
}
