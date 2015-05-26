package com.smi.travel.filter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
/**
 *
 * @author Surachai
 */
public class SessionFilter implements Filter{
    private static final Logger log = Logger.getLogger(SessionFilter.class.getName());
   
    FilterConfig config;
    List exceptionPage;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
        exceptionPage = new ArrayList();
        exceptionPage.add("/login.smi");
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        StringBuffer basePath = new StringBuffer();
        basePath.append(httpRequest.getScheme());
        basePath.append("://");
        basePath.append(httpRequest.getServerName());
        basePath.append(":");
        basePath.append(httpRequest.getServerPort());
        basePath.append(httpRequest.getContextPath());
        basePath.append("/");
        log.info("basePath : "+basePath.toString());
        String requestURL = httpRequest.getRequestURL().toString();
        HttpSession session = httpRequest.getSession();
//        log.info("SESSION : "+session.getAttribute("USER"));
//        session.isNew();
        if (session.getAttribute("USER")==null) {
            String exceptURL = null;
            int exceptSize = getExceptionPage().size();
            for (int i = 0; i < exceptSize; i++) {
                exceptURL = (String) getExceptionPage().get(i);
                if (requestURL.indexOf(exceptURL) >= 0) {
                    chain.doFilter(request, response);
                    return;
                }
            }
            
            httpResponse.sendRedirect(basePath.toString() + "login.smi");
            return;
        }
        chain.doFilter(request, response);
    }
    
     public FilterConfig getConfig() {
        return config;
    }
    public void setConfig(FilterConfig config) {
        this.config = config;
    }
    public List getExceptionPage() {
        return exceptionPage;
    }
    public void setExceptionPage(List exceptionPage) {
        this.exceptionPage = exceptionPage;
    }
    @Override
    public void destroy() {
       
    }   
}
