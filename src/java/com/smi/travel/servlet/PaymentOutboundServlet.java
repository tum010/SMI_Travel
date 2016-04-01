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
 * @author chonnasith
 */
public class PaymentOutboundServlet implements AbstractAJAXServlet{

    @Override
    public Map process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletName = request.getParameter("servletName");
        String refNo = request.getParameter("refNo");        
        String type = request.getParameter("type");
        String payStockNo = request.getParameter("payStockNo");
        String bookdetailid = request.getParameter("bookdetailid");
        String billtype = request.getParameter("billtype");
        String rowCount = request.getParameter("rowCount");
        
        Map result = new HashMap();
        
        result.put("refNo", refNo);
        result.put("servletName", servletName);
        result.put("type", type);
        result.put("payStockNo", payStockNo);
        result.put("bookdetailid", bookdetailid);
        result.put("billtype", billtype);
        result.put("rowCount", rowCount);
        return result;   
    }
    
}
