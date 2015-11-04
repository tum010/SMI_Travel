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
public class ReceiveTableServlet implements AbstractAJAXServlet{

    @Override
    public Map process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletName = request.getParameter("servletName");
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        String periodId = request.getParameter("periodId");
        String periodDetail = request.getParameter("periodDetail");        
        String type = request.getParameter("type");
        Map result = new HashMap();
        
        result.put("fromDate", fromDate);
        result.put("toDate", toDate);
        result.put("servletName", servletName);
        result.put("periodId", periodId);
        result.put("type", type);
        result.put("periodDetail", periodDetail);
                 
        return result;   
    }
    
}
