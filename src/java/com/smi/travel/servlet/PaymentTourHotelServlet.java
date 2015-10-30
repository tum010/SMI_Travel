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
public class PaymentTourHotelServlet implements AbstractAJAXServlet{

    @Override
    public Map process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletName = request.getParameter("servletName");
        String tourId = request.getParameter("tourId");
        String tourName = request.getParameter("tourName");
        String tourCode = request.getParameter("tourCode");
        String tourDate = request.getParameter("tourDate");
        String type = request.getParameter("type");
        Map result = new HashMap();
        
        result.put("tourId", tourId);
        result.put("tourName", tourName);
        result.put("servletName", servletName);
        result.put("tourCode", tourCode);
        result.put("tourDate", tourDate);
        result.put("type", type);
                 
        return result;    
    }
    
}
