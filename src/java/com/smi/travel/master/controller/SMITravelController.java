package com.smi.travel.master.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
public abstract class SMITravelController extends AbstractController{
    protected abstract ModelAndView process(HttpServletRequest request, HttpServletResponse response,HttpSession session);
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return process(request,response,request.getSession());
    }
    
    private String getContentTypeString() {
        // TODO Auto-generated method stub
        return "text/html; charset=tis-620";
    }
}
