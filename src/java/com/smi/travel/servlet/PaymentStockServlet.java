/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.servlet;

import com.smi.travel.datalayer.ajax.service.AbstractAJAXServlet;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

/**
 *
 * @author Jittima
 */
public class PaymentStockServlet implements AbstractAJAXServlet {

    @Override
    public Map process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletName = request.getParameter("servletName");
        String Type = request.getParameter("type");
        String Name = request.getParameter("name");
        String stockId = request.getParameter("stockId");
        String countRowDetail = request.getParameter("countRowDetail");
        String psdId = request.getParameter("psdId");
        String psId = request.getParameter("psId");
        String noStockTable = request.getParameter("noStockTable");
        
        Map result = new HashMap();

        result.put("name", Name);
        result.put("servletName", servletName);
        result.put("type", Type);
        result.put("stockId", stockId);
        result.put("countRowDetail", countRowDetail);
        result.put("psdId", psdId);
        result.put("psId", psId);
        result.put("noStockTable", noStockTable);
        return result;
    }
}
