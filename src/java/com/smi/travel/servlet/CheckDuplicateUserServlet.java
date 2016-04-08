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
public class CheckDuplicateUserServlet implements AbstractAJAXServlet{

    @Override
    public Map process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletName = request.getParameter("servletName");
        String operationTable = request.getParameter("operationTable");
        String operationTableId = request.getParameter("operationTableId");
        String operationAction = request.getParameter("operationAction");
        String operationUser = request.getParameter("operationUser");
        String operationDate = request.getParameter("operationDate");
        String type = request.getParameter("type");
        Map result = new HashMap();
        
        result.put("servletName", servletName);
        result.put("operationTable", operationTable);
        result.put("operationTableId", operationTableId);
        result.put("operationAction", operationAction);
        result.put("operationUser", operationUser);
        result.put("operationDate", operationDate);
        result.put("type", type);
                 
        return result; 
    }
    
}
