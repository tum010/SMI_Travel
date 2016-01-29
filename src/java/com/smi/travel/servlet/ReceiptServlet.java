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
public class ReceiptServlet implements AbstractAJAXServlet{

    @Override
    public Map process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String servletName = request.getParameter("servletName");
        String invoiceNo = request.getParameter("invoiceNo");
        String refNo = request.getParameter("refNo");
        String type = request.getParameter("type");
        String paymentNo = request.getParameter("paymentNo");
        String department = request.getParameter("department");
        String invType = request.getParameter("invType");
        String billDescId = request.getParameter("billDescId");
        String receiptDetailId = request.getParameter("receiptDetailId");
        String recAmount = request.getParameter("recAmount");
        String paymentTourId = request.getParameter("paymentTourId");
        String invDetailId = request.getParameter("invDetailId");

        Map result = new HashMap();
        
        result.put("invoiceNo", invoiceNo);
        result.put("refNo", refNo);
        result.put("servletName", servletName);
        result.put("type", type);
        result.put("paymentNo", paymentNo);
        result.put("department", department);
        result.put("invType", invType);
        result.put("billDescId", billDescId);
        result.put("receiptDetailId", receiptDetailId);
        result.put("recAmount", recAmount);
        result.put("paymentTourId", paymentTourId);
        result.put("invDetailId", invDetailId);
        return result;    
    }
    
}
