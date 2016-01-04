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
public class BookOtherServlet implements AbstractAJAXServlet {

    @Override
    public Map process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletName = request.getParameter("servletName");
        String productid = request.getParameter("productid");
        String otherdate = request.getParameter("otherdate");
        String type = request.getParameter("type");
        String couponId = request.getParameter("couponId");
        String otherDate = request.getParameter("otherDate");
        String row = request.getParameter("row");
        String agentId = request.getParameter("agentId");
        String price = request.getParameter("price");
        String name = request.getParameter("name");
        
        System.out.print("set parameter mapping");
        Map result = new HashMap();
        result.put("productid", productid);
        result.put("otherdate", otherdate);
        result.put("servletName", servletName);
        result.put("type", type);
        result.put("couponId", couponId);
        result.put("otherDate", otherDate);
        result.put("row", row);
        result.put("agentId", agentId);
        result.put("price", price);
        result.put("name", name);
        return result;
    }
    
}
