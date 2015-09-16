package com.smi.travel.controller;
import com.smi.travel.datalayer.service.CollectionNirvanaService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.CollectionNirvana;
import com.smi.travel.master.controller.SMITravelController;
import java.util.List;
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
    private static final String CollectionList = "CollectionList";
    private CollectionNirvanaService collectionNirvanaService;
    private UtilityService utilityService;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String department = request.getParameter("collectionDepartment");
        String type = request.getParameter("collectionType");
        String status = request.getParameter("collectionStatus");
        String from = request.getParameter("inputFromDate");
        String to = request.getParameter("inputToDate");
        String invNo = request.getParameter("collectionInvNo");
        
        request.setAttribute(DEPARTMENT,department);
        request.setAttribute(TYPE,type);
        request.setAttribute(STATUS,status);
        request.setAttribute(DATEFROM,from);
        request.setAttribute(DATETO,to);
        request.setAttribute(INVNO,invNo);
        
        if ("search".equalsIgnoreCase(action)){
            List<CollectionNirvana>  collectionNirvanas = collectionNirvanaService.SearchCollectionNirvanaFromFilter(department, type, status, from, to, invNo);
            if(collectionNirvanas != null){
                request.setAttribute(CollectionList,collectionNirvanas);
            } 
        }
//        System.out.println(" action " + action);
//        System.out.println(" collectionDepartment " + collectionDepartment);
//        System.out.println(" collectionType " + collectionType);
//        System.out.println(" collectionStatus " + collectionStatus);
//        System.out.println(" collectionFromDate " + inputFromDate);
//        System.out.println(" collectionToDate " + inputToDate);
//        System.out.println(" collectionInvNo " + collectionInvNo);
        
        
        return CollectionMonitor;
    }

    public CollectionNirvanaService getCollectionNirvanaService() {
        return collectionNirvanaService;
    }

    public void setCollectionNirvanaService(CollectionNirvanaService collectionNirvanaService) {
        this.collectionNirvanaService = collectionNirvanaService;
    }

    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }
}
