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
public class TransferJobServlet implements AbstractAJAXServlet{
    
    @Override
    public Map process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String date = request.getParameter("date");
        String tourid = request.getParameter("tourid");
        String tourcode = request.getParameter("tourcode");
        String place = request.getParameter("place");
        String other = request.getParameter("other");
        String servletName = request.getParameter("servletName");
        String Type = request.getParameter("type");
        System.out.print("set parameter mapping");
        Map result = new HashMap();
        result.put("tourcode",tourcode);
        result.put("tourid",tourid);
        result.put("date", date);
        result.put("place",place);
        result.put("other", other);
        result.put("servletName", servletName);
        result.put("type", Type);   
        return result;
    }
}
