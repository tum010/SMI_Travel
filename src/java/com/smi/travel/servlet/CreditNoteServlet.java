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
 * @author nanthachaip
 */
public class CreditNoteServlet implements AbstractAJAXServlet {

    @Override
    public Map process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletName = request.getParameter("servletName");
        String type = request.getParameter("type");
        String cnDetailId = request.getParameter("cnDetailId");
        Map result = new HashMap();

        result.put("cnDetailId", cnDetailId);
        result.put("servletName", servletName);
        result.put("type", type);
        return result;
    }

}
