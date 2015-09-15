package com.smi.travel.controller;
import com.smi.travel.master.controller.SMITravelController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class CollectionMonitorController extends SMITravelController {
    private static final ModelAndView CollectionMonitor = new ModelAndView("CollectionMonitor");
    private static final ModelAndView CollectionMonitor_REFRESH = new ModelAndView(new RedirectView("CollectionMonitor.smi", true));
    private static final String TYPE = "collectionType";
    private static final String DEPARTMENT = "collectionDepartment";
    private static final String STATUS = "collectionStatus";
    private static final String DATEFROM = "collectionFromDate";
    private static final String DATETO = "collectionToDate";
    private static final String INVNO = "collectionInvNo";
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String collectionDepartment = request.getParameter("collectionDepartment");
        String collectionType = request.getParameter("collectionType");
        String collectionStatus = request.getParameter("collectionStatus");
        String collectionFromDate = request.getParameter("collectionFromDate");
        String collectionToDate = request.getParameter("collectionToDate");
        String collectionInvNo = request.getParameter("collectionInvNo");
        
        request.setAttribute(DEPARTMENT,collectionDepartment);
        request.setAttribute(TYPE,collectionType);
        request.setAttribute(STATUS,collectionStatus);
        request.setAttribute(DATEFROM,collectionFromDate);
        request.setAttribute(DATETO,collectionToDate);
        request.setAttribute(INVNO,collectionInvNo);
        
        System.out.println(" action " + action);
        System.out.println(" collectionDepartment " + collectionDepartment);
        System.out.println(" collectionType " + collectionType);
        System.out.println(" collectionStatus " + collectionStatus);
        System.out.println(" collectionFromDate " + collectionFromDate);
        System.out.println(" collectionToDate " + collectionToDate);
        System.out.println(" collectionInvNo " + collectionInvNo);
        return CollectionMonitor;
    }
}
