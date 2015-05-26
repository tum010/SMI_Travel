/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.servlet;

import com.smi.travel.datalayer.ajax.service.AbstractAJAXServices;
import com.smi.travel.datalayer.ajax.service.AbstractAJAXServlet;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author Surachai
 */
public class AJAXServlet extends HttpServlet {
    
	private AbstractAJAXServices ajaxServices;
	private AbstractAJAXServlet ajaxServlet;
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		String action 	 	= request.getParameter("action");
		String servletName 	= request.getParameter("servletName");
		String servicesName = request.getParameter("servicesName");
                System.out.println("AJAXServlet execute");
                System.out.println("servletName "+servletName);
                System.out.println("servicesName "+servicesName);
                System.out.println("map : "+request.getParameterMap());
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());

   
                ajaxServlet = (AbstractAJAXServlet) context.getBean(servletName);
		ajaxServices = (AbstractAJAXServices)context.getBean(servicesName);
		
		if(action != null && action.length() > 0) {
                        if("text".equalsIgnoreCase(action)){   
                            Map obj = ajaxServlet.process(request,response);
			    Object resultList = ajaxServices.loadSingle(obj);
			    response.setContentType("text/plain");
                            response.setHeader("Cache-Control", "no-cache");  
                            response.getWriter().write(resultList.toString());
                            System.out.println("resultList.toString() : "+resultList.toString());
                        }
		}
	}
	
        @Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		execute( request, response );
	}
	
        @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		execute( request, response );
	}    
}
