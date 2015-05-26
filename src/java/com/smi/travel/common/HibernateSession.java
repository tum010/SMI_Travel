/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.common;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author Surachai
 */
public class HibernateSession  {

    private static SessionFactory sessionFactory;
    private Session Rsession;
    
    public HibernateSession(){
       
    }
    
    public HibernateSession(SessionFactory  sessionFactory){
        this.sessionFactory = sessionFactory;
    }
   
    public Session getConnection(){
        this.Rsession = this.sessionFactory.getCurrentSession();
        return this.Rsession;
    }

    public void closeConnection(){
        if(this.Rsession != null){
            this.Rsession.close();
        }
    }
    


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    

   
    
}
