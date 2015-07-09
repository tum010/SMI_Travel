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
public class BookingStatusServlet implements AbstractAJAXServlet{

    @Override
    public Map process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String refNo = request.getParameter("refNo");
        String servletName = request.getParameter("servletName");
        String Type = request.getParameter("type");
        String selectStatus = request.getParameter("selectStatus");
        String flagAir = request.getParameter("flagAir");
        String flagHotel = request.getParameter("flagHotel");
        String flagDaytour = request.getParameter("flagDaytour");
        String flagLand = request.getParameter("flagLand");
        String flagOther = request.getParameter("flagOther");
        System.out.print("set parameter mapping");
        Map result = new HashMap();
        result.put("refNo", refNo);
        result.put("servletName", servletName);
        result.put("type", Type);   
        result.put("selectStatus", selectStatus); 
        result.put("flagAir", flagAir);
        result.put("flagHotel", flagHotel);
        result.put("flagDaytour", flagDaytour);
        result.put("flagLand", flagLand);
        result.put("flagOther", flagOther);
        return result;    
    }
    
}
